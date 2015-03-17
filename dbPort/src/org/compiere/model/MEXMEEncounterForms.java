/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.Worklist;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Utilerias;

/**
 * @author lama
 * 
 */
public class MEXMEEncounterForms extends X_EXME_EncounterForms implements DocAction, Worklist {

	private static final long serialVersionUID = 1L;
	private static CLogger slog = CLogger.getCLogger(MEXMEEncounterForms.class);

	public static final int PHYSICIAN = 1;
	public static final int NURSE = 2;
	
	private MEXMEFormSectionHeader header;

	/**
	 * @param ctx
	 * @param EXME_EncounterForms_ID
	 * @param trxName
	 */
	public MEXMEEncounterForms(Properties ctx, int EXME_EncounterForms_ID, String trxName) {
		super(ctx, EXME_EncounterForms_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEEncounterForms(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	@Override
	public MEXMEFormSectionHeader getEXME_FormSectionHeader() throws RuntimeException {
		if(header == null) {
			header = new MEXMEFormSectionHeader(getCtx(), getEXME_FormSectionHeader_ID(), get_TrxName());
		}
		return header;
	}

	/**
	 * 
	 * @param ctx
	 * @param where
	 * @param trxName
	 * @param params
	 * @return
	 */
	public static MEXMEEncounterForms getForm(Properties ctx, StringBuilder where, String trxName, Object... params) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEEncounterForms retValue = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// consulta clasica

		sql.append(" SELECT EXME_EncounterForms.* FROM EXME_EncounterForms ");
		sql.append(" WHERE EXME_EncounterForms.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(where != null ? where : "");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (params != null && params.length > 0) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMEEncounterForms(ctx, rs, trxName);
			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, "getActPaciente (" + sql + ")", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Obtenemos ela forma de una cuenta paciente.
	 * 
	 * @param whereClause
	 * @return
	 */
	public static MEXMEEncounterForms getFromCtaPac(Properties ctx, int ctaPacId, String type, String trxName) {
		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		where.append(" AND EXME_EncounterForms.EXME_CtaPac_ID = ? ");
		where.append(" AND EXME_EncounterForms.Type = ? ");
		return getForm(ctx, where, trxName, ctaPacId, type);
	}

	@Override
	public String getDocStatus() {
		String docStatus = STATUS_Drafted;
		if (!isActive()) {
			docStatus = STATUS_Voided;
		} else if (isAuthenticated() && !isProcessed()) {
			docStatus = STATUS_InProgress;
		} else if (isProcessed()) {
			docStatus = STATUS_Completed;
		}
		return docStatus;
	}

	@Override
	public boolean processIt(String action) throws Exception {
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		return false;
	}

	@Override
	public boolean invalidateIt() {
		return false;
	}

	@Override
	public String prepareIt() {

		int type;
		String status = STATUS_InProgress;

		if (!isActive()) {
			status = STATUS_Voided;
		}

		if (Env.getEXME_Medico_ID(getCtx()) > 0) {
			type = PHYSICIAN;
		} else if (Env.getEXME_Enfermeria_ID(getCtx()) > 0) {
			type = NURSE;
		} else {
			type = 0;
			status = STATUS_Voided;
		}

		// if doctor: Authenticate
		if (type == PHYSICIAN && !isAuthenticated()) {
			setAuthenticated(true);
			setAuthenticated_Date(Env.getCurrentDate());
			if (is_Changed() && save()) {
				status = STATUS_InProgress; // el metodo prepare siempre debe regresar estatus in_progress
			}
		}

		// if nurse: Note
		else if (type == NURSE) {
			setNotedDate(Env.getCurrentDate());
			setNotedBy(Env.getAD_User_ID(getCtx()));

			if (is_Changed() && save()) {
				status = STATUS_InProgress;
			}
		}

		return status;
	}

	@Override
	public boolean approveIt() {
		return false;

	}

	@Override
	public boolean rejectIt() {
		return false;
	}

	@Override
	public String completeIt() {
		setProcessed(true);
		if(getAuthenticated_Date() == null){
			setAuthenticated_Date(Env.getCurrentDate());
		}
		return save() ? DocAction.STATUS_Completed : DocAction.STATUS_Invalid;
	}

	@Override
	public boolean voidIt() {
		return false;
	}

	@Override
	public boolean closeIt() {
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		return false;
	}

	@Override
	public boolean reActivateIt() {
		return false;
	}

	@Override
	public String getSummary() {
		return null;
	}

	@Override
	public String getDocumentInfo() {
		return null;
	}

	@Override
	public File createPDF() {
		return null;
	}

	@Override
	public String getProcessMsg() {
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		return null;
	}

	@Override
	public String getDocAction() {
		String docAction = ACTION_Prepare;
		if (!isActive()) {
			docAction = ACTION_None;
		} else if (isAuthenticated() && getDocStatus().equals(STATUS_InProgress)) {
			docAction = ACTION_Complete;
		}
		return docAction;
	}
	
	/**
	 * Returns the encounter forms
	 * @param ctx
	 * @param ctaPacId  Patient encounter
	 * @param patientId Patient
	 * @param completed (true)Only completed forms
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEncounterForms> get(Properties ctx, int ctaPacId, int patientId, boolean completed,
			String trxName) {
		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final StringBuilder joins = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();

		where.append(" EXME_EncounterForms.isComplete=? ");
		params.add(DB.TO_STRING(completed));
		if (ctaPacId > 0) {
			where.append(" AND EXME_EncounterForms.EXME_CtaPac_ID=? ");
			params.add(ctaPacId);
		} else if (patientId > 0) {
			joins.append(" INNER JOIN EXME_CtaPac cta ON (EXME_EncounterForms.EXME_CtaPac_ID=cta.EXME_CtaPac_ID) ");
			where.append(" AND cta.EXME_Paciente_ID=? ");
			params.add(patientId);
		}
		return new Query(ctx, Table_Name, where.toString(), trxName)//
				.setJoins(joins)//
				.setOnlyActiveRecords(true)//
				// .addAccessLevelSQL(true)// TODO> agregar nivel de acceso para el expediente ??
				.setParameters(params)//
				.list();
	}
	
	/**
	 * Get PDF report. <br>
	 * Saves the report if the encounter form does not have an attachment
	 * 
	 * @param report
	 * @return saved report
	 */
	public File getAttachmentFile(File report) {
		if (getAttachment(true) == null && report != null) {
			createAttachment();
			getAttachment().setTextMsg(report.getName());
			getAttachment().addEntry(report);
			getAttachment().save();
		}
		return getAttachmentFile();
	}

	/**
	 * Get attached PDF.
	 * 
	 * @return saved report
	 */
	public File getAttachmentFile() {
		File file = null;
		if (getAttachment() != null) {
			file = getAttachment().getEntryFile(0, getAttachment().getTextMsg());
		}
		return file;
	}

	/**
	 * Get the pdf of each encounter form in the list, and merge all files into a single pdf file
	 * 
	 * @param forms
	 * @return
	 */
	public static File getMergedFiles(List<MEXMEEncounterForms> forms) {
		File output = null;
		try {
			final StringBuilder pdfFilePath = new StringBuilder(Ini.getCompiereHome());
			pdfFilePath.append(File.separatorChar).append(Utilerias.getPropertiesFromXPT("PDFFilePath"));
			output = File.createTempFile("merge", ".pdf", new File(pdfFilePath.toString()));
			final List<File> files = new ArrayList<File>();
			for (MEXMEEncounterForms form : forms) {
				File file = form.getAttachmentFile();
				if (file != null) {
					files.add(file);
				}
			}
			if(!files.isEmpty()) {
				org.compiere.util.MergePDF.merge(output, false, files.toArray(new File[files.size()]));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getMergedFiles", e);
		}
		return output;
	}

	@Override
	public String getPatientName() {
		int exmePacienteID = getEXME_CtaPac().getEXME_Paciente_ID();
		return MEXMEPaciente.getNombre_Pac(exmePacienteID, null);
	}

	@Override
	public String getSummaryDetail() {
		
		final StringBuilder str = new StringBuilder();
		str.append( Utilerias.getAppMsg(getCtx(), "citas.encounterForms"));
		str.append(": ");
		str.append(getEXME_FormSectionHeader().getName());
		str.append("<br>");
		str.append( Utilerias.getAppMsg(getCtx(), "factExtension.msg.documentNo"));
		str.append(": ");
		str.append(getDocumentNo());
		return str.toString();
	}

	@Override
	public boolean setAction(String action) {
		return false;
	}

	@Override
	public String getRecordType() {
		return Utilerias.getMsg(getCtx(), "citas.encounterForms");
	}
}
