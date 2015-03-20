package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.CreatedUpdatedInfo;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GridItem;
import org.compiere.util.KeyNamePair;
import org.compiere.util.MTranslation;

/**
 * Patient diagnostics
 * 
 * @author Lorena Lama
 */
public class MActPacienteDiag extends X_EXME_ActPacienteDiag implements GridItem {

	private static final long serialVersionUID = 9143744646779955183L;
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MActPacienteDiag.class);

	/**
	 * @param ctx
	 * @param EXME_ActPaciente_ID
	 * @param trxName
	 */
	public MActPacienteDiag(Properties ctx, int EXME_ActPacienteDiag_ID, String trxName) {
		super(ctx, EXME_ActPacienteDiag_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MActPacienteDiag(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Before Save Trigger
	 * 
	 * @param newRecord
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		/*No Validar, pueden ir nulos  gderreza***A SOLICITUD DE GERARDO CAMARGO***
		if(getEXME_Diagnostico_ID() <= 0 && StringUtils.isBlank(getDiagnosis())){
			return false;
		}*/
		// Guardar la estacion de servicio para la impresion del reporte de casos nosocomiales
		if (newRecord) {
			if (StringUtils.isBlank(getType())) {
				setType(TYPE_MedicalInitial);
			}
			if (StringUtils.isBlank(getAdmitting())) {
				setAdmitting(ADMITTING_Yes);
			}
			setEXME_EstServ_ID(Env.getEXME_EstServ_ID(getCtx()));
		
			if (getSequenceDiag() == 0) {
				int sequence = getActPacienteDiagCount(getCtx(),
						getCtaPacRelated(getCtx(), getEXME_ActPaciente_ID(), get_TrxName()),
						(getType() == null ? MActPacienteDiag.TYPE_MedicalFinal : getType()), get_TrxName());
				if (getType().equalsIgnoreCase(TYPE_CoderFinal)) {
					sequence = sequence + 0;
				} else {
					sequence = sequence + 1;
				}
				setSequenceDiag(sequence);
			}
			
		}// GDerreza
		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (newRecord && success) {
			final int patientID = DB.getSQLValue(get_TrxName(),
					"SELECT EXME_Paciente_ID FROM EXME_ActPaciente WHERE EXME_ActPaciente_ID=?", 
					getEXME_ActPaciente_ID());
			if(patientID > 0){
				final MEXMEPaciente mPatient = new MEXMEPaciente(getCtx(), patientID, get_TrxName());
				mPatient.setDiagConocido(true);
				mPatient.save();
			}
		}

		if (success && !isActive()) {
			List<MEXMEClaimCodes> codes = MEXMEClaimCodes.getCodeTableRecord(Table_ID, getEXME_ActPacienteDiag_ID(), getCtx(), get_TrxName());
			if (codes != null && !codes.isEmpty()) {
				for (MEXMEClaimCodes claimCode : codes) {
					claimCode.setIsActive(false);
					claimCode.save();
				}
			}
		}

		return super.afterSave(newRecord, success);
	}
	
	private IDColumn idColumn = null;
	/* List Model Table - Patient's problem list */
	private String[] columns = null;
	private String statusDiag = null;
	private String record = null;
	private String createdByName = null;
	private boolean changeSequenceDiag = false;
	private MDiagnostico diagnostico = null;
	private String diagnosticValue = null;

	@Override
	public String[] getColumns() {
		if (columns == null) {
			columns = new String[] { //
			"idColumn", // ID // "updated", // last date ¿?
					"fecha_Diagnostico", // diagnosed date
					"diagnosticValue", // cod
					"diagnosis", // diagnosis Name
					"statusDiag", // diagnostic date
					"description", // diagnostic notes
					"record", // reference table
					"createdByName", // user name
					"fecha_Estatus",// status Date
					"obs",// comments Name
					"padecimiento"// physical condition
			};
		}
		return columns;
	}

	@Override
	public IDColumn getIdColumn() {
		if (idColumn == null) {
			idColumn = new IDColumn(getEXME_ActPacienteDiag_ID());
		}
		return idColumn;
	}

	public String getStatusDiag() {
		if (statusDiag == null) {
			statusDiag = MRefList.getListName(getCtx(), ESTATUS_AD_Reference_ID, getEstatus());
		}
		return statusDiag;
	}

	public String getRecord() {
		if (record == null && getAD_Table_ID() > 0) {
			// obtenemos el nombre de la traduccion de la tabla
			record = MTranslation.getTable_Name(getCtx(), MTable.getTableName(getCtx(), getAD_Table_ID()),
					Env.getAD_Language(getCtx()));
		}
		return record;
	}

	public String getCreatedByName() {
		if (createdByName == null) {
			createdByName = MUser.getUserName(getCtx(), getCreatedBy());
		}
		return createdByName;
	}

	public MDiagnostico getDiagnostico() {
		if (diagnostico == null && getEXME_Diagnostico_ID() > 0) {
			diagnostico = new MDiagnostico(getCtx(), getEXME_Diagnostico_ID(), get_TrxName());
		}
		return diagnostico;
	}
 	/** @deprecated duplicado de getDiagnostico */
	public MDiagnostico getDiagnosticoReal() {
		if (diagnostico == null) {
			if (getEXME_Diagnostico_ID()>0)
				diagnostico = new MDiagnostico(getCtx(), getEXME_Diagnostico_ID(), get_TrxName());
		}
		return diagnostico;
	}

	public void setDiagnostico(MDiagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getObs() {
		return super.getObservaciones();

	}

	public boolean isChangeSequenceDiag() {
		return changeSequenceDiag;
	}

	public void setChangeSequenceDiag(boolean changeSeqDiag) {
		this.changeSequenceDiag = changeSeqDiag;
	}
	
	public String getDiagnosticValue() {
		if (getDiagnostico() == null) {
			diagnosticValue = "";
		} else {
			diagnosticValue = getDiagnostico().getValue();
		}
		return diagnosticValue;
	}


	
	public static List<MActPacienteDiag> getDiagCtaPacForms(Properties ctx, int actPac, int formID) {
		final List<MActPacienteDiag> lista = new ArrayList<MActPacienteDiag>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
   	 sql.append(" select *  ")
		.append(" from exme_actpacientediag ")
		.append(" where exme_actpaciente_id = ? ")
		.append(" and exme_encounterforms_id = ? ")
		.append(" and IsActive = 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, actPac);
			pstmt.setLong(2, formID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MActPacienteDiag exp = new MActPacienteDiag(ctx, rs, null);
				lista.add(exp);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getDiagCtaPac", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	/**
	 * Regresa el listado de diagnosticos asignados en la ejecucion de la cita medica
	 * 
	 * @author Lorena Lama
	 * @param ctx
	 * @param citaMedID
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> get(Properties ctx, int citaMedID, String trxName) {
		final List<LabelValueBean> retValue = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append(" SELECT diag.EXME_Diagnostico_ID, diag.Name ");
			sql.append(" FROM EXME_ActPacienteDiag ");
			sql.append(" INNER JOIN EXME_ActPaciente act ON ( ");
			sql.append("     EXME_ActPacienteDiag.EXME_ActPaciente_ID = act.EXME_ActPaciente_ID ");
			sql.append("     AND act.EXME_CitaMedica_ID = ?)");
			sql.append(" INNER JOIN EXME_Diagnostico diag ON ( ");
			sql.append("     EXME_ActPacienteDiag.EXME_Diagnostico_ID = diag.EXME_Diagnostico_ID ) ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, citaMedID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue.add(new LabelValueBean(rs.getString(2), rs.getString(1)));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;

	}

	

	/**
	 * 
	 * @param ctx
	 * @param recordID
	 * @param tableID
	 * @param actPacienteID
	 * @param trxName
	 * @return
	 */
	public static List<MActPacienteDiag> get(Properties ctx, int recordID, int tableID, int actPacienteID,
			String trxName) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> parameters = new ArrayList<Object>();

		if (recordID > 0 && tableID > 0) {
			sql.append(" record_ID = ? AND AD_Table_ID = ? ");
			parameters.add(recordID);
			parameters.add(tableID);
		}
		if (actPacienteID > 0) {
			sql.append(sql.length() > 0 ? Constantes.SQL_AND : "");
			sql.append(" EXME_ActPaciente_ID = ? ");
			parameters.add(actPacienteID);
		}

		final List<MActPacienteDiag> lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setOrderBy("Created") //
				.setParameters(parameters) //
				.setOnlyActiveRecords(true) //
				.addAccessLevelSQL(true).list();

		return lista;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param recordID
	 * @param tableID
	 * @param actPacienteID
	 * @param diagID
	 * @param trxName
	 * @return
	 */
	public static boolean existe(Properties ctx, int recordID, int tableID, int actPacienteID,
			int diagID, String type, String trxName) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> parameters = new ArrayList<Object>();

		if (recordID > 0 && tableID > 0) {
			sql.append(" record_ID = ? AND AD_Table_ID = ? ");
			parameters.add(recordID);
			parameters.add(tableID);
		}
		if (actPacienteID > 0) {
			sql.append(sql.length() > 0 ? Constantes.SQL_AND : "");
			sql.append(" EXME_ActPaciente_ID = ? ");
			parameters.add(actPacienteID);
		}
		if (diagID > 0) {
			sql.append(sql.length() > 0 ? Constantes.SQL_AND : "");
			sql.append(" EXME_Diagnostico_ID = ? ");
			parameters.add(diagID);
		}
		if (StringUtils.isNotBlank(type)) {
			sql.append(sql.length() > 0 ? Constantes.SQL_AND : "");
			sql.append(" Type = ? ");
			parameters.add(type);
		}
		
		final List<MActPacienteDiag> lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setOrderBy("Created") //
				.setParameters(parameters) //
				.setOnlyActiveRecords(true) //
				.addAccessLevelSQL(true).list();

		return lista.size()>0;
	}

	/**
	 * Obtenemos una lista con los datos correspondientes del diagnostico
	 * 
	 * @param actPac
	 *            Description of the Parameter
	 * @return Una lista con lod diagnosticos del paciente
	 */
	public static List<MDiagnostico> getOfCtaPac(Properties ctx, int ctaPac, String trxName) {
		final List<MDiagnostico> lista = new ArrayList<MDiagnostico>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT i.* , EXME_ActPacienteDiag.FECHA_DIAGNOSTICO as fechaReg ");
		sql.append("FROM EXME_ActPacienteDiag ");
		sql.append("INNER JOIN EXME_Diagnostico i ON (i.EXME_Diagnostico_ID = EXME_ActPacienteDiag.EXME_Diagnostico_ID) ");
		sql.append("INNER JOIN EXME_ActPaciente act ON EXME_ActPacienteDiag.EXME_ActPaciente_ID = act.EXME_ActPaciente_ID ");
		sql.append("INNER JOIN EXME_CtaPac cp ON act.EXME_CtaPac_ID = cp.EXME_CtaPac_ID ");
		sql.append("WHERE cp.EXME_CtaPac_ID = ? ");
		sql.append("AND EXME_ActPacienteDiag.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MActPacienteDiag.Table_ID));

		sql.append(" ORDER by fechaReg DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPac);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MDiagnostico exp = new MDiagnostico(ctx, rs, trxName);
				exp.setFechaReg(rs.getTimestamp("fechaReg"));
				lista.add(exp);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getOfCtaPac", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * rsolorzano 28/10/2009 Regresa �ltimo diagn�stico registrado
	 * 
	 * @param ctx
	 *            Propiedades
	 * @param trxName
	 *            Nombre de la transaccion
	 * @deprecated Solo usada en WNursingJournal
	 *
	public static int getLastDiag(Properties ctx, int ctaPacID, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		int diagnosticoID = 0;
		try {
			sql.append("SELECT MAX(ACTDIAG.EXME_ACTPACIENTEDIAG_ID),  ACTDIAG.EXME_DIAGNOSTICO_ID ");
			sql.append(" FROM EXME_CTAPAC CTA  ");
			sql.append(" INNER JOIN EXME_ACTPACIENTE ACTPAC ON ACTPAC.EXME_CTAPAC_ID = CTA.EXME_CTAPAC_ID ");
			sql.append(" INNER JOIN EXME_ACTPACIENTEDIAG ACTDIAG ON ACTPAC.EXME_ACTPACIENTE_ID = ACTDIAG.EXME_ACTPACIENTE_ID");
			sql.append(" WHERE CTA.EXME_CTAPAC_ID = ? ");
			sql.append(" GROUP BY ACTDIAG.EXME_DIAGNOSTICO_ID ");
			diagnosticoID = DB.getSQLValue(null, sql.toString(), ctaPacID);
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e.getMessage());
		}
		return diagnosticoID;
	}*/

	/**
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getFromCtaPac(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		final List<Integer> retValue = new ArrayList<Integer>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT EXME_ActPacienteDiag.EXME_Diagnostico_ID  FROM EXME_ActPacienteDiag  ");
		sql.append("INNER JOIN EXME_ActPaciente ap on EXME_ActPacienteDiag.exme_actpaciente_id = ap.exme_actpaciente_id ");
		sql.append("INNER JOIN exme_ctapac cta on ap.exme_ctapac_id = cta.exme_ctapac_id ");
		sql.append("WHERE ap.exme_ctapac_id=? AND EXME_ActPacienteDiag.EXME_Diagnostico_ID > 0 ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", Table_Name));

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(rs.getInt("EXME_Diagnostico_ID"));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getFromCtaPac", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**
	 * Obtiene los diagnosticos desde un Paciente ID
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getFromPac(Properties ctx, int EXME_Paciente_ID, String trxName) {
		final List<Integer> lista = new ArrayList<Integer>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT DISTINCT EXME_ActPacienteDiag.EXME_Diagnostico_ID FROM EXME_ActPacienteDiag ")
		.append("INNER JOIN EXME_ActPaciente ap ON EXME_ActPacienteDiag.EXME_ActPaciente_ID = ap.EXME_ActPaciente_ID ")
		.append("INNER JOIN EXME_Paciente pac ON ap.EXME_Paciente_ID = pac.EXME_Paciente_ID ")
		.append("WHERE pac.EXME_Paciente_ID = ? ")
		.append("AND EXME_ActPacienteDiag.EXME_DIAGNOSTICO_ID IS NOT NULL ")
		.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", Table_Name));

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(rs.getInt("EXME_Diagnostico_ID"));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getFromPac", e);
		} finally {
			DB.close(rs, pstmt);
			 rs = null;
			 pstmt = null;
		}
		return lista;
	}

	/**@deprecated use {@link #get(Properties, int, int, int, int, String, String, String)} */
	public static MActPacienteDiag get(Properties ctx, int recordID, int tableID, int actPacienteID, int diagnosticoID,
			String columnName, String trxName) {
		return get(ctx, recordID, tableID, actPacienteID, diagnosticoID, null, columnName, trxName);
	}
	
	/**
	 * Get an specific diagnostic of a patient
	 * 
	 * @param ctx
	 * @param recordID
	 * @param tableID
	 * @param actPacienteID
	 * @param diagnosticoID
	 * @param trxName
	 * @return
	 * @author lorena lama
	 */
	public static MActPacienteDiag get(Properties ctx, 
			int recordID, 
			int tableID, 
			int actPacienteID, 
			int diagnosticoID,
			String diagnosticoStr,
			String columnName, 
			String trxName) {

		MActPacienteDiag retValue = null;

		if ((recordID > 0 && tableID > 0) || actPacienteID > 0 || diagnosticoID > 0) {
			final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			final List<Object> params = new ArrayList<Object>();

			if (recordID > 0 && tableID > 0) {
				params.add(recordID);
				params.add(tableID);
				where.append(" record_ID=? AND AD_Table_ID=? ");
			}
			if (actPacienteID > 0) {
				where.append(params.isEmpty() ? "" : Constantes.SQL_AND);
				where.append(" EXME_ActPaciente_ID=? ");
				params.add(actPacienteID);
			}
			if (diagnosticoID > 0) {
				where.append(params.isEmpty() ? "" : Constantes.SQL_AND);
				where.append(" EXME_Diagnostico_ID=? ");
				params.add(diagnosticoID);
			} 
			if (StringUtils.isNotBlank(diagnosticoStr)) {
				where.append(params.isEmpty() ? "" : Constantes.SQL_AND);
				where.append(" Diagnosis=? ");
				params.add(diagnosticoStr);
			}
			if (columnName != null) {
				where.append(params.isEmpty() ? "" : Constantes.SQL_AND);
				where.append(" ColumnName=? ");
				params.add(columnName);
			}
			retValue = new Query(ctx, Table_Name, where.toString(), trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(Env.getUserPatientID(ctx) < 1)//
			.setParameters(params)//
			.first();
		}
		return retValue;
	}
	
	/**
	 * Retorna una lista de ActPacienteDiag siempre y cuando tengan un EXME_Diagnostico_ID
	 * @param ctx
	 * @param ctaPac
	 * @param trxName
	 * @return  List<KeyNamePair> 
	 */
	public static List<KeyNamePair> getOfCtaPacLst(Properties ctx, int pacId, int ctaPac, 
			String type,boolean related,  String trxName) {
		List<KeyNamePair> lst = new ArrayList<KeyNamePair>();
		for (MActPacienteDiag diag:
			getOfCtaPacSequence(ctx, pacId, ctaPac, type, related, trxName)){
			if (diag.getEXME_Diagnostico_ID()>0){
				KeyNamePair reg = new KeyNamePair(diag.getEXME_ActPacienteDiag_ID(), diag.getDiagnosticValue() 
						+" - " + diag.getDiagnostico().getName());
				lst.add(reg);
			}
		}
		return lst;
	}


	/**
	 * 
	 * @param ctx
	 * @param ctaPac
	 * @param trxName
	 * @return FIXME
	 */
	public static List<MActPacienteDiag> getOfCtaPacSequence(Properties ctx, int pacId, int ctaPac, String type, boolean related,  String trxName) {
		final List<MActPacienteDiag> lista = new ArrayList<MActPacienteDiag>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" select actdiag.* ");
		sql.append(" from exme_ctapac cta ");
		sql.append(" INNER JOIN exme_actpaciente actpac on actpac.exme_ctapac_id = cta.exme_ctapac_id ");
		sql.append(" INNER JOIN exme_actpacientediag actdiag on actdiag.exme_actpaciente_id = actpac.exme_actpaciente_id ");
		sql.append(" INNER JOIN exme_paciente pac on pac.exme_paciente_id = cta.exme_paciente_id ");
		if (related)
			sql.append(" where cta.exme_ctapac_id = ? ");
		else{
			sql.append(" where pac.exme_paciente_id = ? ")
			   .append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", MEXMECtaPac.Table_Name, "cta"))
			   .append(" and cta.exme_ctapac_id <> ? ");
		}
		sql.append(" and actdiag.estatus in ('A', 'R') ");
		sql.append(" and actdiag.isactive = 'Y' ");
		sql.append(" and (");
		sql.append(" (actdiag.exme_diagnostico_id is not null and actdiag.exme_diagnostico_id >0 )");
		sql.append(" or (actdiag.diagnosis is not null )");
		sql.append(" )");
		if (type != null){
			sql.append(" and actdiag.type = ? ");
		}
		sql.append(" order by actdiag.sequencediag ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (related){
				pstmt.setInt(1, ctaPac);
			}else{
				pstmt.setInt(1, pacId);
			}
			if (type != null){
				pstmt.setString(2, type);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MActPacienteDiag exp = new MActPacienteDiag(ctx, rs, trxName);
				lista.add(exp);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getOfCtaPacSequence", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	

	/**
	 * Save / Update a record of patient's diagnostic
	 * 
	 * @param poModel
	 * @param actPacID
	 * @param diagColumn
	 * @param diagTxt
	 * @param columnNames
	 * @return
	 */
	public static boolean saveDiagnostic(PO poModel, int actPacID, String diagColumn, String diagTxt,
			Map<String, Object> columnNames) {
		if(poModel == null){
			return false;
		}
		final int oldDiagID = poModel.is_new() ? 0 : StringUtils.isNotBlank(diagColumn) ? poModel
				.get_ValueOldAsInt(diagColumn) : 0;
		return saveDiagnostic(poModel, actPacID, diagColumn, oldDiagID, diagTxt, columnNames);
	}
	/**
	 * Save / Update a record of patient's diagnostic
	 * 
	 * @param poModel
	 * @param actPacID
	 * @param diagColumn
	 * @param diagTxt
	 * @param columnNames
	 * @return
	 */
	public static boolean saveDiagnostic(PO poModel, int actPacID, String diagColumn, int diagID, String diagTxt,
			Map<String, Object> columnNames) {
		boolean saved;
		final MActPacienteDiag diag = saveDiag(poModel, actPacID, diagColumn, diagID, diagTxt, columnNames);
		if (diag == null) {
			saved = false;
		} else {
			saved = diag.save();
			if (!saved) {
				slog.log(Level.SEVERE, "EXME_ActPacienteDiag.save");
			}
		}
		return saved;
	}
	/**
	 * Save / Update a record of patient's diagnostic
	 * 
	 * @param poModel
	 * @param actPacID
	 * @param diagColumn
	 * @param diagTxt
	 * @param columnNames
	 * @return
	 */
	public static MActPacienteDiag saveDiag(PO poModel, int actPacID, String diagColumn, int diagID, String diagTxt,
			Map<String, Object> columnNames) {
		MActPacienteDiag diag = null;
		if (actPacID > 0 && poModel != null) {// if new value is null return
			diag = getModel(poModel, actPacID, diagID, diagTxt, diagColumn);
			final int newDiagID = poModel.get_ValueAsInt(poModel.get_ColumnIndex(diagColumn));
			if(newDiagID > 0) {
				diag.setEXME_Diagnostico_ID(newDiagID);
			} else {
				diag.setEXME_Diagnostico_ID(diagID);
			}
			diag.setDiagnosis(diagTxt);
			diag.setColumns(columnNames);
		} 
		return diag;
	}
	
	/**
	 * 
	 * @param poModel
	 * @param actPacID
	 * @param diagColumn
	 * @return
	 */
	private static MActPacienteDiag getModel(PO poModel, int actPacID, int oldDiagID, String oldStr, String diagColumn) {
		MActPacienteDiag diag = null;
		diag = MActPacienteDiag.get(poModel.getCtx(), poModel.get_ID(), poModel.get_Table_ID(), actPacID, oldDiagID, oldStr,
				diagColumn, poModel.get_TrxName());
		if (diag == null) {
			diag = new MActPacienteDiag(poModel.getCtx(), 0, poModel.get_TrxName());
			diag.setEXME_ActPaciente_ID(actPacID);
			diag.setRecord_ID(poModel.get_ID());
			diag.setAD_Table_ID(poModel.get_Table_ID());
			diag.setColumnName(diagColumn);
		}
		return diag;
	}

	/**
	 * Save / Update a record of patient's diagnostic
	 * @param cols
	 *            - HashMap<String (ColumnBD),Object (value)>
	 * @return
	 * @author lorena lama
	 */
	public void setColumns(Map<String, Object> cols) {
		if (cols == null) {
			return;
		}
		for (int i = 0; i < get_ColumnCount(); i++) {
			final String columnName = get_ColumnName(i);
			final Object value = cols.get(columnName);
			if (value == null) {
				continue;
			} else {
				set_CustomColumn(columnName, value);
			}
		}
	}
	
	/**
	 * Return the number of Diagnoses for an Account
	 * 
	 * @param ctapacID Account identification
	 * @return Number of diagnoses
	 */
	public static int getActPacienteDiagCount(Properties ctx, int ctaPacID, String type, String trxName) {
		 int count = 0;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT count(*) cuantos ");
		sql.append(" FROM exme_ctapac cta ");
		sql.append(" INNER JOIN exme_actpaciente actpac on actpac.exme_ctapac_id = cta.exme_ctapac_id ");
		sql.append(" INNER JOIN exme_actpacientediag actdiag on actdiag.exme_actpaciente_id = actpac.exme_actpaciente_id ");

		sql.append(" where cta.exme_ctapac_id = ? ");
		sql.append(" and actdiag.estatus in ('A', 'R') ");
		sql.append(" and actdiag.isactive = 'Y' ");
		sql.append(" and (");
		sql.append(" (actdiag.exme_diagnostico_id is not null and actdiag.exme_diagnostico_id >0 )");
		sql.append(" or (actdiag.diagnosis is not null )) ");
		sql.append(" and actdiag.Type = ? ");
		
		try {
			count = DB.getSQLValue(trxName, sql.toString(), ctaPacID, type);
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getCtaPacRelated", e);
		}
		return count;
	}
	
	/**
	 * Return the  Account related
	 * 
	 * @param ctapacID Account identification
	 * @return Number of diagnoses
	 */
	public static int getCtaPacRelated(Properties ctx, int actPacDiagID, String trxName) {
		int count = 0;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT c.exme_ctapac_id ");
		sql.append("FROM exme_actpaciente a ");
		sql.append("INNER JOIN exme_ctapac c ON c.exme_ctapac_id = a.exme_ctapac_id ");
		sql.append("WHERE a.exme_actpaciente_id = ? ");
		try {
			count = DB.getSQLValue(trxName, sql.toString(), actPacDiagID);
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getCtaPacRelated", e);
		}
		return count;
	}
	
	public static List<Integer> getDiagnosticPacRpt(Properties ctx, String AndWhere,String fechaini, String fechafin, String trxName) {
		final List<Integer>contador = new ArrayList<Integer>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT DISTINCT(AD.EXME_DIAGNOSTICO_ID)  AS DIAG, EXME_DIAGNOSTICO.VALUE, P.NOMBRE_PAC, ")
		.append(" AD.FECHA_DIAGNOSTICO, EXME_DIAGNOSTICO.NAME ")
		.append(" FROM EXME_ACTPACIENTEDIAG AD ")
		.append(" INNER JOIN EXME_DIAGNOSTICO ON (AD.EXME_DIAGNOSTICO_ID=EXME_DIAGNOSTICO.EXME_DIAGNOSTICO_ID) ")
		.append(" INNER JOIN EXME_ACTPACIENTE AP ON (AD.EXME_ACTPACIENTE_ID = AP.EXME_ACTPACIENTE_ID) ")
		.append(" INNER JOIN EXME_PACIENTE P ON (AP.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID) ")
		.append(" WHERE EXME_DIAGNOSTICO.ESEPIDEMIOLOGICO = 'Y' ")
		.append(" AND TO_DATE(TO_CHAR(AD.FECHA_DIAGNOSTICO,'DD/MM/YYYY'),'DD/MM/YYYY') ")
		.append(" BETWEEN TO_DATE( ?, 'DD/MM/YYYY') AND TO_DATE( ?, 'DD/MM/YYYY') ")
		.append( AndWhere )
		.append(" ORDER BY AD.FECHA_DIAGNOSTICO ASC, EXME_DIAGNOSTICO.NAME ASC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, fechaini);
			pstmt.setString(2, fechafin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				contador.add(rs.getInt("DIAG"));				
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getCtaPacRelated", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return contador;
	}
	
	/**
	 * Trae informacion de usuarios que crearon y/o actualizaron el registro del diagnostico
	 * 
	 * @param ctx
	 * @param Exme_ActPacienteDiag_ID
	 * @return
	 * @throws Exception
	 */
	public static String getInfoDiag(Properties ctx, int Exme_ActPacienteDiag_ID) throws Exception {
		CreatedUpdatedInfo infoDiag = new CreatedUpdatedInfo();
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT ")
		.append("EXME_ActPacienteDiag.EXME_ActPacienteDiag_ID AS Paciente_Diag_ID, EXME_ActPacienteDiag.CreatedBy AS Created_By, EXME_ActPacienteDiag.UpdatedBy AS Updated_By, ")
		.append("TO_CHAR(EXME_ActPacienteDiag.Created, 'dd/mm/yyyy hh24:mi') AS Fecha_C, TO_CHAR(EXME_ActPacienteDiag.Updated, 'dd/mm/yyyy hh24:mi') AS Fecha_U, ")
		.append("medC.Nombre_Med AS Med_C, medU.Nombre_Med AS Med_U, moC.Ad_User_Id AS Med_Org_C, moU.Ad_User_Id AS Med_Org_U, ")
		.append("asiC.Name AS Asi_C, asiU.Name AS Asi_U, Enfc.nombre_enf AS Enf_C, Enfu.nombre_enf AS Enf_U ")
		.append("FROM EXME_ActPacienteDiag ")
		.append("INNER JOIN EXME_ActPaciente on EXME_ActPaciente.EXME_ActPaciente_ID = EXME_ActPacienteDiag.EXME_ActPaciente_ID ")
		.append("LEFT JOIN EXME_Medico_Org moC ON (moC.AD_User_ID = EXME_ActPacienteDiag.CreatedBy AND moC.AD_Org_ID = EXME_ActPaciente.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Medico_Org moU ON (moU.AD_User_ID = EXME_ActPacienteDiag.UpdatedBy AND moU.AD_Org_ID = EXME_ActPaciente.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Asistente asiC ON (asiC.AD_User_ID = EXME_ActPacienteDiag.CreatedBy AND asiC.AD_Org_ID = EXME_ActPaciente.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Asistente asiU ON (asiU.AD_User_ID = EXME_ActPacienteDiag.UpdatedBy AND asiU.AD_Org_ID = EXME_ActPaciente.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Enfermeria enfC ON (enfC.AD_User_ID = EXME_ActPacienteDiag.CreatedBy AND enfC.AD_Org_ID = EXME_ActPaciente.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Enfermeria enfU ON (enfU.AD_User_ID = EXME_ActPacienteDiag.UpdatedBy AND enfU.AD_Org_ID = EXME_ActPaciente.AD_Org_ID) ")
		.append("LEFT JOIN EXME_Medico medC ON medC.EXME_Medico_ID = moC.EXME_Medico_ID ")
		.append("LEFT JOIN EXME_Medico medU ON medU.EXME_Medico_ID = moU.EXME_Medico_ID ")
		.append("WHERE EXME_ActPacienteDiag.IsActive = 'Y' AND EXME_ActPacienteDiag.EXME_ActPacienteDiag_ID = ? ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		.append(" ORDER BY EXME_ActPacienteDiag.CreatedBy");


		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Exme_ActPacienteDiag_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				infoDiag.setCreatedBy(rs.getInt(2));
				infoDiag.setUpdatedBy(rs.getInt(3));
				infoDiag.setDateCreated(rs.getString(4));
				infoDiag.setDateUpdated(rs.getString(5));
				infoDiag.setMedCreated(rs.getString(6));
				infoDiag.setMedUpdated(rs.getString(7));
				infoDiag.setAsiCreated(rs.getString(10));
				infoDiag.setAsiUpdated(rs.getString(11));
				infoDiag.setEnfCreated(rs.getString(12));
				infoDiag.setEnfUpdated(rs.getString(13));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getInfoDiag", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return infoDiag.getCreatedUpdatedInfo(ctx);
	}
	
	/**
	 * Si ha tenido el diagnostico enviado
	 * 
	 * @param ctx
	 *            App Context
	 * @param pacId
	 *            Paciente
	 * @param diagId
	 *            Diagnostico
	 * @param trxName
	 *            Trx Name
	 * @return Si ha tenido o no el diagnostico enviado
	 */
	public static boolean hasIt(Properties ctx, int pacId, int diagId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  diag.exme_actpacientediag_id ");
		sql.append("FROM ");
		sql.append("  exme_actpacientediag diag ");
		sql.append("  INNER JOIN exme_actpaciente act ");
		sql.append("  ON diag.exme_actpaciente_id = act.exme_actpaciente_id ");
		sql.append("WHERE ");
		sql.append("  diag.exme_diagnostico_id = ? AND ");
		sql.append("  act.exme_paciente_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MActPacienteDiag.Table_Name, "diag"));
		
		boolean hasIt = false;
		try {
			hasIt = DB.getSQLValue(trxName, sql.toString(), diagId, pacId) > 0;
		} catch (Exception e) {
			slog.log(Level.SEVERE, "hasit", e);
		}
		return hasIt;
	}
	
	public String getEstatusLbl() {
		return MRefList.getListName(getCtx(), ESTATUS_AD_Reference_ID, super.getEstatus());
	}
	
	public String getDiagToString() {
		return StringUtils.defaultString(getDiagnosticValue()) + " " + StringUtils.defaultString(getDiagnosis());
	}
	
	public static MActPacienteDiag create(int ctaPacID, int actPacID, int diagID ){
		MActPacienteDiag ctaPacFinDiag = new MActPacienteDiag(Env.getCtx(), 0, null);

		ctaPacFinDiag.setEXME_ActPaciente_ID(actPacID);	
		ctaPacFinDiag.setEXME_Diagnostico_ID(diagID);
		ctaPacFinDiag.setAD_Table_ID(MEXMECtaPac.Table_ID);
		ctaPacFinDiag.setRecord_ID(ctaPacID);
		ctaPacFinDiag.setType(MActPacienteDiag.TYPE_CoderFinal);
		ctaPacFinDiag.setAdmitting(ADMITTING_Yes);
		return ctaPacFinDiag;
	}
	
}
