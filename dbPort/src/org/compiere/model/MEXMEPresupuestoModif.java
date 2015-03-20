/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Msg;

/**
 * EncaBezado de las modificaciones que se hacen al presupuesto
 * 
 * @author twry
 * 
 */
public class MEXMEPresupuestoModif extends X_EXME_PresupuestoModif implements
		DocAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 216305657123768194L;
	/** Static Logger */
	private static CLogger slog = CLogger
			.getCLogger(MEXMEPresupuestoModif.class);
	/** Just Prepared Flag */
	private transient boolean mJustPrepared = false;
	/** Process Message */
	private transient String mProcessMsg = null;
	/** Invoice Lines */
	private transient MEXMEPresupuestoDet[] mLines;
	/** Presupuesto */
	private MEXMEPresupuestoEgr presupuestoEgr = null;

	/**
	 * @param ctx
	 * @param pPresupModifID
	 * @param trxName
	 */
	public MEXMEPresupuestoModif(final Properties ctx,
			final int pPresupModifID, final String trxName) {
		super(ctx, pPresupModifID, trxName);
		if (pPresupModifID == 0) {
			setDocStatus(DOCSTATUS_Drafted); // Draft
			setDocAction(DOCACTION_Complete);
			setDateAcct(DB.convert(ctx,
					new Timestamp(System.currentTimeMillis())));
			setDateTrx(DB.convert(ctx,
					new Timestamp(System.currentTimeMillis())));
			setIsSOTrx(true);
			setIsApproved(false);
			setTipo(TIPO_Original);
			setPosted(false);
			super.setProcessed(false);
			setProcessing(false);
			if (getC_DocTypeTarget_ID() <= 0) {
				final MDocType[] docTypes = MDocType.getOfDocBaseType(ctx,
						X_C_DocType.DOCBASETYPE_GLDocument);
				if (docTypes != null && docTypes.length > 0) {
					setC_DocTypeTarget_ID(docTypes[0].get_ID());

				}
			}

			setC_DocType_ID(getC_DocTypeTarget_ID());
		}
	}

	/**
	 * @param ctx
	 * @param rset
	 * @param trxName
	 */
	public MEXMEPresupuestoModif(final Properties ctx, final ResultSet rset,
			final String trxName) {
		super(ctx, rset, trxName);
	}

	/**
	 * Version mas reciente
	 * 
	 * @param ctx
	 * @return
	 */
	public static List<MEXMEPresupuestoModif> getVersiones(
			final Properties ctx, final int presupuesto) {
		slog.log(Level.INFO, "getVersiones");
		return new org.compiere.model.Query(ctx,
				MEXMEPresupuestoModif.Table_Name,
				" EXME_PresupuestoEgr_ID = ? ", null)
				.setOnlyActiveRecords(true).setApplyAccessFilter(true)
				.setParameters(presupuesto).setOrderBy(" fecha desc ").list();
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
			slog.log(Level.INFO, "getLines");
			final List<MEXMEPresupuestoDet> list = new org.compiere.model.Query(
					getCtx(), X_EXME_PresupuestoDet.Table_Name,
					X_EXME_PresupuestoEgr.Table_Name + "_ID = "
							+ getEXME_PresupuestoEgr_ID() + " AND "
							+ X_EXME_PresupuestoModif.Table_Name + "_ID = "
							+ getEXME_PresupuestoModif_ID()
							+ "  AND EXME_PartidaPres_ID > 0 ", get_TrxName())
					.setOnlyActiveRecords(true).setOrderBy(" Line ").list();

			//
			final MEXMEPresupuestoDet[] lines = new MEXMEPresupuestoDet[list
					.size()];
			list.toArray(lines);

			mLines = lines;
		}
		return mLines;
	} // getLines

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

		if (MEXMEPresupuestoModif.TIPO_Adding.equals(getTipo())
				|| MEXMEPresupuestoModif.TIPO_Enlargement.equals(getTipo())
				|| MEXMEPresupuestoModif.TIPO_Reduction.equals(getTipo())
				|| MEXMEPresupuestoModif.TIPO_ReductionInProcess
						.equals(getTipo())) {

			// Importacion de reasignación
			final List<MEXMECtrlPresup> lst = MEXMECtrlPresup.getModAutirizado(
					getCtx(), getEXME_PresupuestoEgr_ID(),
					getEXME_PresupuestoModif_ID(), get_TrxName());

			final boolean sumar = MEXMEPresupuestoModif.TIPO_Adding
					.equals(getTipo())
					|| MEXMEPresupuestoModif.TIPO_Enlargement.equals(getTipo());

			for (final MEXMECtrlPresup bean : lst) {
				bean.setMA(sumar);
				bean.setDI(sumar);
				if (!bean.save(get_TrxName())) {
					// success = ERROR;
					return DOCACTION_Invalidate;
				}
			}
		}

		// Cambiar estatus
		setDocStatus(DOCACTION_Complete);
		if (!save(get_TrxName())) {
			info.append(" @EXME_PresupuestoModif_ID@#").append("UpdateStatus");
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
		sbuff.append(": ").append(Msg.translate(getCtx(), "Value")).append("=")
				.append(getDocumentNo()).append(" (#")
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
				ReportEngine.INVOICE, getEXME_PresupuestoModif_ID());// TODO:
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
		return getPDFFileName(documentDir, getEXME_PresupuestoModif_ID());
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
			final int pPresupModifID) {
		final StringBuffer sbuf = new StringBuffer(documentDir);
		if (sbuf.length() == 0) {
			sbuf.append(".");
		}
		if (!sbuf.toString().endsWith(File.separator)) {
			sbuf.append(File.separator);
		}
		sbuf.append("EXME_PresupuestoModif_ID_").append(pPresupModifID)
				.append(".pdf");
		return sbuf.toString();
	} // getPDFFileName

	/**
	 * Objeto Presupuesto
	 * 
	 * @return
	 */
	public MEXMEPresupuestoEgr getPresupuestoEgr() {
		if (presupuestoEgr == null) {
			presupuestoEgr = new MEXMEPresupuestoEgr(getCtx(),
					getEXME_PresupuestoEgr_ID(), get_TrxName());
		}
		return presupuestoEgr;
	}
}
