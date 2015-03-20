/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 * Presupuesto
 * 
 * @author twry
 * 
 */
public class MEXMEPresupuestoEgr extends X_EXME_PresupuestoEgr implements
		DocAction {

	/** serialVersionUID */
	private static final long serialVersionUID = -4740201329572201288L;
	/** Static Logger */
	protected static CLogger slog = CLogger.getCLogger(MEXMEPresupuestoEgr.class);
	/** Just Prepared Flag */
	protected transient boolean mJustPrepared = false;
	/** Process Message */
	protected transient String mProcessMsg = null;
	/** Invoice Lines */
	protected transient MEXMEPresupuestoDet[] mLines;

	/**
	 * Constructor
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pPresupEgrID
	 *            Id del presupuesto
	 * @param trxName
	 *            Nombre de transacción
	 */
	public MEXMEPresupuestoEgr(final Properties ctx, final int pPresupEgrID,
			final String trxName) {
		super(ctx, pPresupEgrID, trxName);
		if (pPresupEgrID == 0) {
			setDocStatus(DOCSTATUS_Drafted); // Draft
			setDocAction(DOCACTION_Complete);
			setDateAcct(DB.convert(ctx,
					new Timestamp(System.currentTimeMillis())));
			setDateTrx(DB.convert(ctx,
					new Timestamp(System.currentTimeMillis())));
			setIsSOTrx(true);
			setIsApproved(false);
			setPosted(false);
			super.setProcessed(false);
			setProcessing(false);

			setTipo(TIPO_Federal);// Por defecto
			setVersion("1");// cuando es nuevo es la primera version
			setEstatus(ESTATUS_ByApproving);

			if (getC_DocTypeTarget_ID() <= 0) {
				final MDocType[] docTypes = MDocType.getOfDocBaseType(ctx,
						X_C_DocType.DOCBASETYPE_GLDocument);
				if (docTypes != null && docTypes.length > 0) {
					setC_DocTypeTarget_ID(docTypes[0].get_ID());

				}
			}
		}
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 *            Contexto
	 * @param rs
	 *            Resultset
	 * @param trxName
	 *            Nombre de transacción
	 */
	public MEXMEPresupuestoEgr(final Properties ctx, final ResultSet rset,
			final String trxName) {
		super(ctx, rset, trxName);
	}


	/**
	 * Get Invoice Lines
	 * 
	 * @param requery
	 * @return lines
	 */
	public MEXMEPresupuestoDet[] getLines(final boolean requery,
			final String trxName) {
		if (mLines == null || mLines.length == 0 || requery) {

			final StringBuilder sql = new StringBuilder(" ")
					.append(X_EXME_PresupuestoEgr.Table_Name).append("_ID = ")
					.append(getEXME_PresupuestoEgr_ID()).append(" AND ")
					.append(sqlOnlyBudget());

			final List<MEXMEPresupuestoDet> list = new org.compiere.model.Query(
					getCtx(), X_EXME_PresupuestoDet.Table_Name, sql.toString(),
					get_TrxName()).setOnlyActiveRecords(true)
					.setOrderBy(" Line ").list();

			//
			final MEXMEPresupuestoDet[] lines = new MEXMEPresupuestoDet[list
					.size()];
			list.toArray(lines);

			mLines = lines;
		}
		return mLines;
	} // getLines

	/**
	 * Traer solo las lineas del presupuesto
	 * 
	 * @return
	 */
	public static String sqlOnlyBudget() {
		return new StringBuilder(" EXME_PresupuestoModif_ID IS NULL ").append(
				" AND EXME_PartidaPres_ID > 0 ").toString();
	}
	
	/**
	 * Complete Document
	 * 
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	@Override
	public String completeIt() {
		// Re-Check
		if (!mJustPrepared) {
			final String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		// Implicit Approval
		if (!isApproved()) {
			approveIt();
		}

		log.info(toString());
		final StringBuffer info = new StringBuffer();

		// Importación de presupuesto
		if (!MEXMEPresupuestoEgr.copyCtrl(getCtx(),
				getEXME_PresupuestoEgr_ID(), get_TrxName())) {
			info.append(" @EXME_CtrlPresup_ID@#").append("NotLines");
			return DOCACTION_Invalidate;
		}

		// Cambiar estatus
		setDocStatus(DOCACTION_Complete);
		if (!save(get_TrxName())) {
			info.append(" @EXME_PresupuestoEgr_ID@#").append("UpdateStatus");
			return DOCACTION_Invalidate;
		}

		// User Validation
		final String valid = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			mProcessMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		mProcessMsg = info.toString().trim();
		setProcessed(true);
		setDocAction(DOCACTION_Close);

		return DOCACTION_Complete;
	}

	/**************************************************************************
	 * Process document
	 * 
	 * @param processAction
	 *            document action
	 * @return true if performed
	 */
	@Override
	public boolean processIt(final String processAction) {
		mProcessMsg = null;
		final DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());
	} // process

	/**
	 * Unlock Document.
	 * 
	 * @return true if success
	 */
	@Override
	public boolean unlockIt() {
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	} // unlockIt

	/**
	 * Invalidate Document
	 * 
	 * @return true if success
	 */
	@Override
	public boolean invalidateIt() {
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	} // invalidateIt

	/**
	 * Prepare Document
	 * 
	 * @return new status (In Progress or Invalid)
	 */
	@Override
	public String prepareIt() {
		log.info(toString());
		mProcessMsg = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_BEFORE_PREPARE);
		if (mProcessMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		final MDocType dtype = MDocType.get(getCtx(), getC_DocTypeTarget_ID());

		// Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dtype.getDocBaseType(),
				getAD_Org_ID())) {
			mProcessMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		// Lines
		final MEXMEPresupuestoDet[] lines = getLines(true, get_TrxName());
		if (lines.length == 0) {
			mProcessMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}

		// Convert/Check DocType
		if (getC_DocType_ID() != getC_DocTypeTarget_ID()) {
			setC_DocType_ID(getC_DocTypeTarget_ID());
		}
		if (getC_DocType_ID() == 0) {
			mProcessMsg = "No Document Type";
			return DocAction.STATUS_Invalid;
		}

		// Add up Amounts
		mJustPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction())) {
			setDocAction(DOCACTION_Complete);
		}
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/**
	 * Approve Document
	 * 
	 * @return true if success
	 */
	@Override
	public boolean approveIt() {
		log.info(toString());
		setIsApproved(true);
		return true;
	} // approveIt

	/**
	 * Reject Approval
	 * 
	 * @return true if success
	 */
	@Override
	public boolean rejectIt() {
		log.info(toString());
		setIsApproved(false);
		return true;
	} // rejectIt

	/**
	 * Void Document.
	 * 
	 * @return true if success
	 */
	@Override
	public boolean voidIt() {
		log.info(toString());
		if (DOCSTATUS_Closed.equals(getDocStatus())
				|| DOCSTATUS_Reversed.equals(getDocStatus())
				|| DOCSTATUS_Voided.equals(getDocStatus())) {
			mProcessMsg = "Document Closed: " + getDocStatus();
			setDocAction(DOCACTION_None);
			return false;
		}

		// Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
				|| DOCSTATUS_Invalid.equals(getDocStatus())
				|| DOCSTATUS_InProgress.equals(getDocStatus())
				|| DOCSTATUS_Approved.equals(getDocStatus())
				|| DOCSTATUS_NotApproved.equals(getDocStatus())) {
			// Set lines to 0
			final MEXMEPresupuestoDet[] lines = getLines(false, get_TrxName());
			for (int i = 0; i < lines.length; i++) {
				final MEXMEPresupuestoDet line = lines[i];
				line.setEstatus(DOCSTATUS_Voided);
				line.save(get_TrxName());
			}
			addDescription(Msg.getMsg(getCtx(), "Voided"));
		} else {
			return reverseCorrectIt();
		}

		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	} // voidIt

	/**
	 * Add to Description
	 * 
	 * @param description
	 *            text
	 */
	public void addDescription(final String description) {
		final String desc = getDescription();
		if (desc == null) {
			setDescription(description);

		} else {
			setDescription(desc + " | " + description);
		}
	} // addDescription

	/**
	 * Close Document.
	 * 
	 * @return true if success
	 */
	@Override
	public boolean closeIt() {
		log.info(toString());
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	} // closeIt

	/**
	 * Reverse Correction - same date
	 * 
	 * @return true if success
	 */
	@Override
	public boolean reverseCorrectIt() {
		log.info(toString());
		final MDocType dtype = MDocType.get(getCtx(), getC_DocType_ID());
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dtype.getDocBaseType(),
				getAD_Org_ID())) {
			mProcessMsg = "@PeriodClosed@";
			return false;
		}
		// TODO: Implementar

		return true;
	} // reverseCorrectIt

	/**
	 * Reverse Accrual - none
	 * 
	 * @return false
	 */
	@Override
	public boolean reverseAccrualIt() {
		log.info(toString());
		return false;
	} // reverseAccrualIt

	/**
	 * Re-activate
	 * 
	 * @return false
	 */
	@Override
	public boolean reActivateIt() {
		log.info(toString());
		return false;
	} // reActivateIt

	/*************************************************************************
	 * Get Summary
	 * 
	 * @return Summary of Document
	 */
	@Override
	public String getSummary() {
		final StringBuilder sbuff = new StringBuilder();
		sbuff.append(getDocumentNo());
		sbuff.append(": ").append(Msg.translate(getCtx(), "Amount"))
				.append("=").append(getAmount()).append(" (#")
				.append(getLines(false, get_TrxName()).length).append(")");
		// - Description
		if (getDescription() != null && getDescription().length() > 0) {
			sbuff.append(" - ").append(getDescription());
		}
		return sbuff.toString();
	} // getSummary

	/**
	 * Get Document Info
	 * 
	 * @return document info (untranslated)
	 */
	@Override
	public String getDocumentInfo() {
		final MDocType dtype = MDocType.get(getCtx(), getC_DocType_ID());
		return dtype.getName() + " " + getDocumentNo();
	} // getDocumentInfo

	/**************************************************************************
	 * Create PDF
	 * 
	 * @return File or null
	 */
	@Override
	public File createPDF() {
		try {
			final File temp = File.createTempFile(get_TableName() + get_ID()
					+ "_", ".pdf");
			return createPDF(temp);
		} catch (Exception e) {
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	} // getPDF

	/**
	 * Create PDF file
	 * 
	 * @param file
	 *            output file
	 * @return file if success
	 */
	public File createPDF(final File file) {
		final ReportEngine reng = ReportEngine.get(getCtx(),
				ReportEngine.INVOICE, getEXME_PresupuestoEgr_ID());// TODO:
		// Asignar
		// el
		// formato
		if (reng == null) {
			return null;
		}
		return reng.getPDF(file);
	} // createPDF

	/**
	 * Get Process Message
	 * 
	 * @return clear text error message
	 */
	@Override
	public String getProcessMsg() {
		return mProcessMsg;
	} // getProcessMsg

	/**
	 * Get Document Owner (Responsible)
	 * 
	 * @return AD_User_ID
	 */
	@Override
	public int getDoc_User_ID() {
		return getAD_User_ID();
	} // getDoc_User_ID

	/**
	 * Get Document Approval Amount
	 * 
	 * @return amount
	 */
	@Override
	public BigDecimal getApprovalAmt() {
		return getAmount();
	} // getApprovalAmt

	/**
	 * Get PDF File Name
	 * 
	 * @param documentDir
	 *            directory
	 * @return file name
	 */
	public String getPDFFileName(final String documentDir) {
		return getPDFFileName(documentDir, getEXME_PresupuestoEgr_ID());
	} // getPDFFileName

	/**
	 * Get ISO Code of Currency
	 * 
	 * @return Currency ISO
	 */
	public String getCurrencyISO() {
		return MCurrency.getISO_Code(getCtx(), getC_Currency_ID());
	} // getCurrencyISO

	/**
	 * Get PDF File Name
	 * 
	 * @param documentDir
	 *            directory
	 * @param C_Invoice_ID
	 *            invoice
	 * @return file name
	 */
	public static String getPDFFileName(final String documentDir,
			final int pPresupuestoEgrID) {
		final StringBuilder sbuf = new StringBuilder(documentDir);
		if (sbuf.length() == 0) {
			sbuf.append(".");
		}
		if (!sbuf.toString().endsWith(File.separator)) {
			sbuf.append(File.separator);
		}
		sbuf.append("EXME_PresupuestoEgr_ID_").append(pPresupuestoEgrID)
				.append(".pdf");
		return sbuf.toString();
	} // getPDFFileName

	/**
	 * Presupuesto
	 * 
	 * @param ctx
	 * @param sqlWhere
	 * @return
	 */
	public static List<MEXMEPresupuestoDet> getPresupuestoDet(
			final Properties ctx, final String sql) {
		return MEXMEPresupuestoDet.getAll(ctx, sql);
	}

	/**
	 * Traer solo las lieneas de las modificaciones
	 * 
	 * @return
	 */
	public static String sqlReallocation() {
		return new StringBuilder(" EXME_PresupuestoModif_ID IS NOT NULL ")
				.append(" AND EXME_PartidaPres_ID > 0 ").toString();
	}

	/**
	 * Formacion de la seccion SELECT para las columnas de la consulta que
	 * servira para mostrar el presupuesto
	 * 
	 * @param nivel
	 *            : Nivel en que se mostrar la consulta 0. COMO SE MUESTRA EL
	 *            EXCEL 1. UR cliente/organizacion 2. FI finalida 3. FU Funcion
	 *            4. SF Subfuncion 5. RG Reasignacion 6. AI Actividad
	 *            institucional 7. PP Programa presupuestal 8. PI Programa
	 *            institucional 9. PTDA Partida 10. TG Tipo de gasto 11. FF
	 *            Fuente de Financiamiento 12. EF Entidad federativa 13. PPI
	 *            Proyecto 14. LINEA Descripcion
	 * @param index
	 *            : nivel que se esta evaluando
	 * @param alias
	 *            : alias de la tabla
	 * @return Fraccion del select ej.
	 *         ", fi.value as fivalue , fi.name as finame"
	 */
	private String getColumnas(final int nivel, final int index,
			final String alias) {
		final StringBuilder sql = new StringBuilder().append(", ")
				.append(alias).append(".Value as ").append(alias)
				.append("value , ").append(alias).append(".Name  as ");

		if (nivel == index) {
			sql.append("description");
		} else {
			sql.append(alias).append("name    ");
		}
		return sql.toString();
	}

	/**
	 * Todas las columnas de la consulta del presupuesto
	 * 
	 * @param ctx
	 * @param nivel
	 * @param solonivel
	 * @return
	 */
	public StringBuilder sqlPorNivel(final Properties ctx, final int nivel,
			final int reasignacion, final String pWhere) {

		final StringBuilder select = new StringBuilder();
		final StringBuilder where = new StringBuilder();
		final StringBuilder groupby = new StringBuilder();
		// StringBuilder orderby = new StringBuilder();

		String alias = null;
		if (nivel >= 1) { // 1. UR Unidad responsable
			if (nivel == 1) {
				select.append(" ur.UnidadResponsable, pd.Name as description, ur.AD_Client_ID ");
				groupby.append("ur.UnidadResponsable, pd.Name, ur.AD_Client_ID ");
				where.append(" AND pd.Tipo = ").append(
						DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Unidad));
			} else {
				select.append("ur.UnidadResponsable, ur2.Name as urname, ur.AD_Client_ID ");
				groupby.append("ur.UnidadResponsable, ur2.Name, ur.AD_Client_ID ");
			}
			groupby.append(nivel > 1 ? ", " : " ");
		}
		if (nivel >= 2) { // 2. FI finalidad
			alias = "fi";
			select.append(", ").append(alias).append(".EXME_ClasFuncional_ID ");
			select.append(getColumnas(nivel, 2, alias));
			groupby.append(alias + ".Value," + alias + ".Name ");
			groupby.append(", ").append(alias)
					.append(".EXME_ClasFuncional_ID ");
			groupby.append(nivel > 2 ? ", " : " ");

			if (nivel == 2) {
				where.append(" AND pd.Tipo = ").append(
						DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Purpose));
			}
		}
		if (nivel >= 3) { // 3. FU Funcion
			alias = "fu";
			select.append(", ").append(alias)
					.append(".EXME_ClasFuncional_fun_ID ");
			select.append(getColumnas(nivel, 3, alias));
			groupby.append(alias + ".Value," + alias + ".Name ").append(", ")
					.append(alias).append(".EXME_ClasFuncional_fun_ID ");
			//
			groupby.append(nivel > 3 ? ", " : " ");

			if (nivel == 3) {
				where.append(" AND pd.Tipo = ").append(
						DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Function));
			}
		}
		if (nivel >= 4) { // 4. SF Subfuncion
			alias = "sf";
			select.append(", ").append(alias)
					.append(".EXME_ClasFuncional_sfu_ID ");
			select.append(getColumnas(nivel, 4, alias));
			groupby.append(alias + ".Value," + alias + ".Name ").append(", ")
					.append(alias).append(".EXME_ClasFuncional_sfu_ID ");
			groupby.append(nivel > 4 ? ", " : " ");

			if (nivel == 4) {
				where.append(" AND pd.Tipo = ").append(
						DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Subfunction));
			}
		}
		if (nivel >= 5) { // 5. RG Reasignacion
			alias = "rg";
			select.append(", ").append(alias).append(".EXME_Reasignacion_ID ");
			select.append(getColumnas(nivel, 5, alias));
			groupby.append(alias + ".Value," + alias + ".Name ").append(", ")
					.append(alias).append(".EXME_Reasignacion_ID ");
			groupby.append(nivel > 5 ? ", " : " ");

			if (nivel == 5) {
				where.append(" AND pd.Tipo = ").append(
						DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Reassignment));
			}

		}
		if (nivel >= 6) { // 6. AI Actividad institucional
			alias = "ai";
			select.append(", ").append(alias)
					.append(".EXME_ActInstitucional_ID ");
			select.append(getColumnas(nivel, 6, alias));
			groupby.append(alias + ".Value," + alias + ".Name ").append(", ")
					.append(alias).append(".EXME_ActInstitucional_ID ");
			groupby.append(nivel > 6 ? ", " : " ");

			if (nivel == 6) {
				where.append(" AND pd.Tipo = ").append(
						DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Contracts));
			}

		}
		if (nivel >= 7) { // 7. PP Programa presupuestal
			alias = "pp";
			select.append(", ").append(alias)
					.append(".EXME_ProgPresupuestal_ID ");
			select.append(getColumnas(nivel, 7, alias));
			groupby.append(alias + ".Value," + alias + ".Name ").append(", ")
					.append(alias).append(".EXME_ProgPresupuestal_ID ");
			groupby.append(nivel > 7 ? ", " : " ");

			if (nivel == 7) {
				where.append(" AND pd.Tipo = ").append(
						DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Prosupuestal));
			}

		}
		if (nivel >= 8) { // 8. PI Programa institucional
			alias = "pi";
			select.append(", ").append(alias)
					.append(".EXME_ProgInstitucional_ID ");
			select.append(getColumnas(nivel, 8, alias));
			groupby.append(alias + ".Value," + alias + ".Name ").append(", ")
					.append(alias).append(".EXME_ProgInstitucional_ID ");
			groupby.append(nivel > 8 ? ", " : " ");

			if (nivel == 8) {
				where.append(" AND pd.Tipo = ").append(
						DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Institutional));
			}
		}

		// ULTIMO NIVEL
		String orderby = "";
		if (nivel == 9) { // 9. PTDA Partida
			select.append(", ");
			select.append(" ptda.Value as ptdavalue, ptda.Name AS Description,  ");
			select.append(" pd.Line,                 pd.EXME_PresupuestoDet_ID, ");
			select.append(" tg.Value as tgvalue,     tg.Name as tgName,  ");// 10.
			// TG
			// Tipo
			// de
			// gasto
			select.append(" ff.Value as ffvalue,     ff.Name as ffName,  ");// 11.
			// FF
			// Fuente
			// de
			// Financiamiento
			select.append(" ef.Entidad,              tg.Name as efname,  ");// 12.
			// EF
			// Entidad
			// federativa
			select.append(" ppi.Value as ppivalue,   ppi.Name as ppiname ");// 13.
			// PPI
			// Proyecto

			groupby.append(" ptda.Value, ptda.Name, ");
			groupby.append(" ptda.EXME_PartidaPres_ID, ");
			groupby.append(" pd.Line,    pd.EXME_PresupuestoDet_ID, ");
			groupby.append(" tg.Value,   tg.Name, ");// 10. TG Tipo de gasto
			groupby.append(" ff.Value,   ff.Name, ");// 11. FF Fuente de
			// Financiamiento
			groupby.append(" ef.Entidad, tg.Name, ");// 12. EF Entidad
			// federativa
			groupby.append(" ppi.Value,  ppi.Name ");// 13. PPI Proyecto

			orderby = " ORDER BY pd.Line ASC, pd.EXME_PresupuestoDet_ID ";

			where.append(" AND pd.Tipo = ").append(
					DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_PTDA));
		}

		if (nivel == 14) { // 14. Todo
			select.append(", pd.description, ");
			select.append(" ptda.Value AS ptdavalue, ptda.Name AS ptdaName, ");
			select.append(" pd.Line,                 pd.EXME_PresupuestoDet_ID, ");
			select.append(" tg.Value   AS tgvalue,   tg.Name   AS tgName,  ");// 10.
			// TG
			// Tipo
			// de
			// gasto
			select.append(" ff.Value   AS ffvalue,   ff.Name   AS ffName,  ");// 11.
			// FF
			// Fuente
			// de
			// Financiamiento
			select.append(" ef.Entidad,              tg.Name   AS efname,  ");// 12.
			// EF
			// Entidad
			// federativa
			select.append(" ppi.Value  AS ppivalue,  ppi.Name  AS ppiname  ");// 13.
			// PPI
			// Proyecto
			groupby.append(" pd.description, ");
			groupby.append(" ptda.Value, ptda.Name, ");
			groupby.append(" pd.Line,    pd.EXME_PresupuestoDet_ID, ");
			groupby.append(" tg.Value,   tg.Name, ");// 10. TG Tipo de gasto
			groupby.append(" ff.Value,   ff.Name, ");// 11. FF Fuente de
			// Financiamiento
			groupby.append(" ef.Entidad, tg.Name, ");// 12. EF Entidad
			// federativa
			groupby.append(" ppi.Value,  ppi.Name ");// 13. PPI Proyecto

			orderby = " ORDER BY pd.Line ASC, pd.EXME_PresupuestoDet_ID ";
		}

		if (nivel == 10) { // 10. TG Tipo de gasto
			alias = "tg";
			select.append(getColumnas(nivel, 10, alias));
			groupby.append(alias + ".Value," + alias + ".Name ");

			where.append(" AND pd.EXME_TipoGasto_ID > 0 ");
		}
		if (nivel == 11) { // 11. FF Fuente de Financiamiento
			alias = "ff";
			select.append(getColumnas(nivel, 11, alias));
			groupby.append(alias + ".Value," + alias + ".Name ");

			where.append(" AND pd.EXME_FteFinanciamiento_ID > 0 ");
		}
		if (nivel == 12) { // 12. EF Entidad federativa
			select.append(", ef.Entidad, tg.Name as ");
			if (nivel == 12) {
				select.append("description ");
			} else {
				select.append("efname ");
			}
			groupby.append("ef.Entidad, tg.Name ");

			where.append(" AND pd.C_Region_ID > 0 ");
		}
		if (nivel == 13) { // 13. PPI Proyecto
			alias = "ppi";
			select.append(getColumnas(nivel, 13, alias));
			groupby.append(alias + ".Value," + alias + ".Name ");

			where.append(" AND pd.C_Project_ID > 0 ");
		}

		where.append(" ").append(pWhere == null ? "" : pWhere);
		return MEXMEPresupuestoDet.consulta(ctx, nivel, reasignacion,
				select.toString(), where.toString(), groupby.toString(),
				orderby);
	}

	/**
	 * Obtenemos el detalle de la cuenta paciente.
	 * 
	 * @return
	 */
	public List<PresupuestoView> getDetalle(final Properties ctx,
			final int nivel, final String where, final int reasignacion,
			final String trxName) {

		final List<PresupuestoView> list = new ArrayList<PresupuestoView>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// Creación de sql
		final String sql = sqlPorNivel(Env.getCtx(), nivel, reasignacion, where)
				.toString();
		try {
			slog.log(Level.WARNING, sql);
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, getEXME_PresupuestoEgr_ID());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int nivelVista = 1;
				final PresupuestoView presupView = new PresupuestoView();

				presupView.setStrUnidadResponsable(rset
						.getString("UnidadResponsable"));
				presupView.setStrDescripcion(rset.getString("description"));
				presupView.setAmtSum(rset.getBigDecimal("amount"));
				presupView.setAmt1(rset.getBigDecimal("amountene"));
				presupView.setAmt2(rset.getBigDecimal("amountfeb"));
				presupView.setAmt3(rset.getBigDecimal("amountmar"));
				presupView.setAmt4(rset.getBigDecimal("amountabr"));
				presupView.setAmt5(rset.getBigDecimal("amountmay"));
				presupView.setAmt6(rset.getBigDecimal("amountjun"));
				presupView.setAmt7(rset.getBigDecimal("amountjul"));
				presupView.setAmt8(rset.getBigDecimal("amountago"));
				presupView.setAmt9(rset.getBigDecimal("amountsep"));
				presupView.setAmt10(rset.getBigDecimal("amountoct"));
				presupView.setAmt11(rset.getBigDecimal("amountnov"));
				presupView.setAmt12(rset.getBigDecimal("amountdic"));

				if (nivel >= 2) {
					presupView.setStrFinalidad(rset.getString("fivalue"));
					presupView.setFinalidad(rset
							.getInt("EXME_ClasFuncional_ID"));
				}
				if (presupView.getStrFinalidad() != null
						&& !presupView.getStrFinalidad().isEmpty()) {
					nivelVista = 2;
				}

				if (nivel >= 3) {
					presupView.setStrFuncion(rset.getString("fuvalue"));
					presupView.setFuncion(rset
							.getInt("EXME_ClasFuncional_fun_ID"));
				}
				if (presupView.getStrFuncion() != null
						&& !presupView.getStrFuncion().isEmpty()) {
					nivelVista = 3;
				}

				if (nivel >= 4) {
					presupView.setStrSubfuncion(rset.getString("sfvalue"));
					presupView.setSubfuncion(rset
							.getInt("EXME_ClasFuncional_sfu_ID"));
				}
				if (presupView.getStrSubfuncion() != null
						&& !presupView.getStrSubfuncion().isEmpty()) {
					nivelVista = 4;
				}

				if (nivel >= 5) {
					presupView.setStrReasignacion(rset.getString("rgvalue"));
					presupView.setReasignacion(rset
							.getInt("EXME_Reasignacion_ID"));
				}
				if (presupView.getStrReasignacion() != null
						&& !presupView.getStrReasignacion().isEmpty()) {
					nivelVista = 5;
				}
				if (nivel >= 6) {
					presupView
							.setStrActInstitucional(rset.getString("aivalue"));
					presupView.setActInstitucional(rset
							.getInt("EXME_ActInstitucional_ID"));
				}
				if (presupView.getStrActInstitucional() != null
						&& !presupView.getStrActInstitucional().isEmpty()) {
					nivelVista = 6;
				}
				if (nivel >= 7) {
					presupView
							.setStrProgPresupuestal(rset.getString("ppvalue"));
					presupView.setProgPresupuestal(rset
							.getInt("EXME_ProgPresupuestal_ID"));
				}
				if (presupView.getStrProgPresupuestal() != null
						&& !presupView.getStrProgPresupuestal().isEmpty()) {
					nivelVista = 7;
				}
				if (nivel >= 8) {
					presupView.setStrProgInstitucional(rset
							.getString("pivalue"));
					presupView.setProgInstitucional(rset
							.getInt("EXME_ProgInstitucional_ID"));
				}
				if (presupView.getStrProgInstitucional() != null
						&& !presupView.getStrProgInstitucional().isEmpty()) {
					nivelVista = 8;
				}

				if (nivel == 9 || nivel == 14) {
					presupView.setStrPartidaPres(rset.getString("ptdavalue"));
					presupView.setLinea(rset.getInt("Line"));
					presupView.setMPresupuestoDet(new MEXMEPresupuestoDet(ctx,
							rset.getInt("EXME_PresupuestoDet_ID"), trxName));

				}
				if (presupView.getStrPartidaPres() != null
						&& !presupView.getStrPartidaPres().isEmpty()) {
					nivelVista = 9;
				}

				if (nivel == 10 || nivel == 9 || nivel == 14) {
					presupView.setStrTipoGasto(rset.getString("tgvalue"));
				}
				if (presupView.getStrTipoGasto() != null
						&& !presupView.getStrTipoGasto().isEmpty()) {
					nivelVista = 9;
				}

				if (nivel == 11 || nivel == 9 || nivel == 14) {
					presupView.setStrFteFinanciamiento(rset
							.getString("ffvalue"));
				}
				if (presupView.getStrFteFinanciamiento() != null
						&& !presupView.getStrFteFinanciamiento().isEmpty()) {
					nivelVista = 9;
				}

				if (nivel == 12 || nivel == 9 || nivel == 14) {
					presupView.setStrRegion(rset.getString("entidad"));
				}
				if (presupView.getStrRegion() != null
						&& !presupView.getStrRegion().isEmpty()) {
					nivelVista = 9;
				}

				if (nivel == 13 || nivel == 9 || nivel == 14) {
					presupView.setStrProyecto(rset.getString("ppivalue"));
				}
				if (presupView.getStrProyecto() != null
						&& !presupView.getStrProyecto().isEmpty()) {
					nivelVista = 9;
				}

				presupView.setNivelGrupo(nivel);
				presupView.setNivelVista(nivelVista);
				presupView.setTipo(rset.getString("Tipo"));

				list.add(presupView);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rset, pstmt);
		}
		return list;
	}

	/**
	 * Version mas reciente
	 * 
	 * @param ctx
	 * @return
	 */
	public static MEXMEPresupuestoEgr getNewVersion(final Properties ctx) {
		return new org.compiere.model.Query(ctx,
				MEXMEPresupuestoEgr.Table_Name, null, null)
				.setOnlyActiveRecords(true).setApplyAccessFilter(true)
				.setOrderBy(" revision desc ").first();
	}

	/**
	 * Version mas reciente
	 * 
	 * @param ctx
	 * @return
	 */
	public static List<MEXMEPresupuestoEgr> getVersiones(final Properties ctx) {
		return new org.compiere.model.Query(ctx,
				MEXMEPresupuestoEgr.Table_Name, null, null)
				.setOnlyActiveRecords(true).setApplyAccessFilter(true)
				.setOrderBy(" revision desc ").list();
	}

	/**
	 * Copiar el presupuesto a ctrl presupuestal
	 * 
	 * @param ctx
	 */
	public static boolean copyCtrl(final Properties ctx,
			final int pPresupuestoEgrId, final String trxName) {
		boolean success = true;

		final List<MEXMEPresupuestoDet> lstDet = getPresupuestoDet(ctx,
				" EXME_PresupuestoEgr_ID  =  " + pPresupuestoEgrId + " AND "
						+ sqlOnlyBudget());

		for (final MEXMEPresupuestoDet bean : lstDet) {
			final MEXMECtrlPresup ctrlPresupBean = new MEXMECtrlPresup(ctx, 0,
					trxName);
			PO.copyValues(bean, ctrlPresupBean);
			ctrlPresupBean.setEXME_PresupuestoDet_ID(bean
					.getEXME_PresupuestoDet_ID());
			// Se agrega le monto del presupuesto como autorizado
			ctrlPresupBean.copyAmountMa(bean);
			ctrlPresupBean.copyAmountDi(bean);
			if (!ctrlPresupBean.save(trxName)) {
				success = false;
				break;
			}
		}
		return success;
	}
	
	/**
	 * Desde Unidad responsable hasta el
	 * 
	 * @param tipo
	 *            mas un nivel
	 * @param bean
	 *            Tipo de linea actual
	 * @return Tipo de linea actual mas un nivel
	 */
	public static ValueNamePair toNIVEL(final PresupuestoView bean) {
		final StringBuilder result = new StringBuilder();
		int nivelGrupo = 0;

		if (X_EXME_PresupuestoDet.TIPO_Unidad.equals(bean.getTipo())) {
			result.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Purpose));
			nivelGrupo = 2;
		}
		if (X_EXME_PresupuestoDet.TIPO_Purpose.equals(bean.getTipo())) {
			result.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Function));
			result.append(" AND pd.EXME_ClasFuncional_ID = ").append(
					bean.getFinalidad());
			nivelGrupo = 3;
		}
		if (X_EXME_PresupuestoDet.TIPO_Function.equals(bean.getTipo())) {
			result.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Subfunction));
			result.append(" AND pd.EXME_ClasFuncional_ID = ").append(
					bean.getFinalidad());
			result.append(" AND pd.EXME_ClasFuncional_Fun_ID = ").append(
					bean.getFuncion());
			nivelGrupo = 4;
		}
		if (X_EXME_PresupuestoDet.TIPO_Subfunction.equals(bean.getTipo())) {
			result.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Reassignment));
			result.append(" AND pd.EXME_ClasFuncional_ID = ").append(
					bean.getFinalidad());
			result.append(" AND pd.EXME_ClasFuncional_Fun_ID = ").append(
					bean.getFuncion());
			result.append(" AND pd.EXME_ClasFuncional_Sfu_ID = ").append(
					bean.getSubFuncion());
			nivelGrupo = 5;
		}
		if (X_EXME_PresupuestoDet.TIPO_Reassignment.equals(bean.getTipo())) {
			result.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Contracts));
			result.append(" AND pd.EXME_ClasFuncional_ID = ").append(
					bean.getFinalidad());
			result.append(" AND pd.EXME_ClasFuncional_Fun_ID = ").append(
					bean.getFuncion());
			result.append(" AND pd.EXME_ClasFuncional_Sfu_ID = ").append(
					bean.getSubFuncion());
			result.append(" AND pd.EXME_Reasignacion_ID = ").append(
					bean.getReasignacion());
			nivelGrupo = 6;
		}
		if (X_EXME_PresupuestoDet.TIPO_Contracts.equals(bean.getTipo())) {
			result.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Prosupuestal));
			result.append(" AND pd.EXME_ClasFuncional_ID = ").append(
					bean.getFinalidad());
			result.append(" AND pd.EXME_ClasFuncional_Fun_ID = ").append(
					bean.getFuncion());
			result.append(" AND pd.EXME_ClasFuncional_Sfu_ID = ").append(
					bean.getSubFuncion());
			result.append(" AND pd.EXME_Reasignacion_ID = ").append(
					bean.getReasignacion());
			result.append(" AND pd.EXME_ActInstitucional_ID = ").append(
					bean.getActInstitucional());
			nivelGrupo = 7;
		}
		if (X_EXME_PresupuestoDet.TIPO_Prosupuestal.equals(bean.getTipo())) {
			result.append(DB
					.TO_STRING(X_EXME_PresupuestoDet.TIPO_Institutional));
			result.append(" AND pd.EXME_ClasFuncional_ID = ").append(
					bean.getFinalidad());
			result.append(" AND pd.EXME_ClasFuncional_Fun_ID = ").append(
					bean.getFuncion());
			result.append(" AND pd.EXME_ClasFuncional_Sfu_ID = ").append(
					bean.getSubFuncion());
			result.append(" AND pd.EXME_Reasignacion_ID = ").append(
					bean.getReasignacion());
			result.append(" AND pd.EXME_ActInstitucional_ID = ").append(
					bean.getActInstitucional());
			result.append(" AND pd.EXME_ProgPresupuestal_ID = ").append(
					bean.getProgPresupuestal());
			nivelGrupo = 8;
		}
		if (X_EXME_PresupuestoDet.TIPO_Institutional.equals(bean.getTipo())) {
			result.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_PTDA));
			result.append(" AND pd.EXME_ClasFuncional_ID = ").append(
					bean.getFinalidad());
			result.append(" AND pd.EXME_ClasFuncional_Fun_ID = ").append(
					bean.getFuncion());
			result.append(" AND pd.EXME_ClasFuncional_Sfu_ID = ").append(
					bean.getSubFuncion());
			result.append(" AND pd.EXME_Reasignacion_ID = ").append(
					bean.getReasignacion());
			result.append(" AND pd.EXME_ActInstitucional_ID = ").append(
					bean.getActInstitucional());
			result.append(" AND pd.EXME_ProgPresupuestal_ID = ").append(
					bean.getProgPresupuestal());
			result.append(" AND pd.EXME_ProgInstitucional_ID = ").append(
					bean.getProgInstitucional());
			nivelGrupo = 9;
		}
		return new ValueNamePair(String.valueOf(nivelGrupo), result.toString());
	}
}
