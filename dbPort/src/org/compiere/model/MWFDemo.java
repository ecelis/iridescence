/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Msg;

/**
 * Standard Approval Workflow (Demo)
 * 
 * @author Lorena Lama
 * 
 */
public class MWFDemo extends X_WF_Demo implements DocAction {
	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param WF_Demo_ID
	 * @param trxName
	 */
	public MWFDemo(Properties ctx, int WF_Demo_ID, String trxName) {
		super(ctx, WF_Demo_ID, trxName);
		if (WF_Demo_ID == 0) {
			setDocStatus(DocAction.STATUS_NotApproved); // NA
			setDocAction(DocAction.ACTION_Complete); // CO
			setIsApproved(false);
			setProcessed(false);
		}
	}

	private List<MWFDemoLine> lines;

	/** Process Message */
	private String mProcessMsg = null;
	/** Just Prepared Flag */
	private boolean mJustPrepared = false;

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MWFDemo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Demo detail lines.
	 * 
	 * @param requery
	 * @return
	 */
	public List<MWFDemoLine> getLines(boolean requery) {
		if (requery || (lines == null && getWF_Demo_ID() > 0)) {
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(getWF_Demo_ID());
			lines = new Query(getCtx(), MWFDemoLine.Table_Name, "isActive='Y' AND WF_Demo_ID=?", null)//
					.setParameters(params) //
					.setOrderBy(MWFDemoLine.COLUMNNAME_WF_DemoLine_ID) //
					.list();
		}
		return lines;
	}

	/**
	 * @see org.compiere.model.PO#beforeSave(boolean)
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (is_new()) {
			setDocStatus(DocAction.STATUS_NotApproved); // NA
			setDocAction(DocAction.ACTION_Complete); // CO
			setProcessed(false);
			setIsApproved(false);
		}
		return true;
	}//FIXME:beforedelete

	/**
	 * @see org.compiere.process.DocAction#processIt(java.lang.String)
	 */
	@Override
	public boolean processIt(String action) throws Exception {
		mProcessMsg = null;
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(action, getDocAction());
	}

	/**
	 * @see org.compiere.process.DocAction#unlockIt()
	 */
	@Override
	public boolean unlockIt() {
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}

	/**
	 * @see org.compiere.process.DocAction#invalidateIt()
	 */
	@Override
	public boolean invalidateIt() {
		log.info("invalidateIt - " + toString());
		// TODO: invalidation process ?
		return true;
	}

	/**
	 * @see org.compiere.process.DocAction#prepareIt()
	 */
	@Override
	public String prepareIt() {
		log.info(toString());
		mProcessMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (mProcessMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		// Invalid .... TODO: define validation rules
		boolean isInvalid = getLines(true) == null || getLines(false).isEmpty();
		if (isInvalid) {
			mProcessMsg = "@NoLines@";
			// ... TODO: do something ?
			return DocAction.STATUS_Invalid;
		}
		if( getApprovalUser() == getCreatedBy() && getUpdatedBy() == getCreatedBy()){
			approveIt();
		}
		mJustPrepared = true;
		return DocAction.STATUS_InProgress;
	}

	/**
	 * @see org.compiere.process.DocAction#approveIt()
	 */
	@Override
	public boolean approveIt() {
		log.info("approveIt - " + toString());
		// TODO: approval process ?
		setIsApproved(true);
		return isApproved();
	}

	/**
	 * @see org.compiere.process.DocAction#rejectIt()
	 */
	@Override
	public boolean rejectIt() {
		log.info("rejectIt - " + toString());
		// TODO: approval process ?
		setDocStatus(DOCSTATUS_NotApproved);
		setDocAction(DOCACTION_None);
		setIsApproved(false);
		return true;
	}

	/**
	 * @see org.compiere.process.DocAction#completeIt()
	 */
	@Override
	public String completeIt() {
		// Re-Check
		if (!mJustPrepared) {
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		// Implicit Approval
		if (!isApproved() && !approveIt()) {
			setDocAction(ACTION_Prepare);
			return DocAction.STATUS_Invalid;
		}
		log.info(toString());

		// User Validation
		mProcessMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (mProcessMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		// TODO: complete process ?
		setProcessed(true);
		setDocAction(ACTION_Close);
		return DocAction.STATUS_Completed;
	}

	/**
	 * @see org.compiere.process.DocAction#voidIt()
	 */
	@Override
	public boolean voidIt() {
		log.info("voidIt - " + toString());
		// TODO: void process ?
		return closeIt();
	}

	/**
	 * @see org.compiere.process.DocAction#closeIt()
	 */
	@Override
	public boolean closeIt() {
		log.info("closeIt - " + toString());
		// TODO: close it process ?
		return true;
	}

	/**
	 * @see org.compiere.process.DocAction#reverseCorrectIt()
	 */
	@Override
	public boolean reverseCorrectIt() {
		log.info("reverseCorrectIt - " + toString());
		// TODO: reverse correction process ?
		return false;
	}

	/**
	 * @see org.compiere.process.DocAction#reverseAccrualIt()
	 */
	@Override
	public boolean reverseAccrualIt() {
		log.info("reverseAccrualIt - " + toString());
		// TODO: reverse accrual process ?
		return false;
	}

	/**
	 * @see org.compiere.process.DocAction#reActivateIt()
	 */
	@Override
	public boolean reActivateIt() {
		log.info("reActivateIt - " + toString());
		// setProcessed(false);
		// TODO: reactivation process ?
		if (reverseCorrectIt()) {
			return true;
		}
		return false;
	}

	/**
	 * @see org.compiere.process.DocAction#getSummary()
	 */
	@Override
	public String getSummary() {
		StringBuffer strb = new StringBuffer();
		strb.append(getDocumentNo());
		// - User
		strb.append(" - ").append(getDoc_User_ID());
		// TODO: aditional message ?
		// - Description
		if (getName() != null && getName().length() > 0) {
			strb.append(" - ").append(getName());
		}
		return strb.toString();
	}

	/**
	 * @see org.compiere.process.DocAction#getDocumentInfo()
	 */
	@Override
	public String getDocumentInfo() {
		return Msg.getElement(getCtx(), Table_Name + "_ID") + " " + getDocumentNo();
	}

	/**
	 * @see org.compiere.process.DocAction#createPDF()
	 */
	@Override
	public File createPDF() {
		// try {
		// File temp = File.createTempFile(get_TableName() + get_ID() + "_", ".pdf");
		// FIXME: report ?
		// ReportEngine re = ReportEngine.get(getCtx(), ReportEngine.DUNNING, get_ID());
		// if (re == null) {
		// return null;
		// }
		// return re.getPDF(temp);
		// } catch (Exception e) {
		// log.severe("Could not create PDF - " + e.getMessage());
		// }
		return null;
	}

	/**
	 * @see org.compiere.process.DocAction#getProcessMsg()
	 */
	@Override
	public String getProcessMsg() {
		return mProcessMsg;
	}

	/**
	 * @see org.compiere.process.DocAction#getDoc_User_ID()
	 */
	@Override
	public int getDoc_User_ID() {
		// TODO: ?
		return getApprovalUser();
	}

	/**
	 * @see org.compiere.process.DocAction#getC_Currency_ID()
	 */
	@Override
	public int getC_Currency_ID() {
		// TODO: ?
		return 0;
	}

	/**
	 * @see org.compiere.process.DocAction#getApprovalAmt()
	 */
	@Override
	public BigDecimal getApprovalAmt() {
		// TODO: ?
		return null;
	}

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	public String toString() {
		StringBuffer strb = new StringBuffer(100);
		strb.append(getClass().getName());
		strb.append("[");
		strb.append(get_ID()).append("-").append(getDocumentNo());
		strb.append(",Status=").append(getDocStatus());
		strb.append(",Action=").append(getDocAction()).append("]");
		return strb.toString();
	} // toString
}
