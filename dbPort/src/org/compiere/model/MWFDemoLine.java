/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Standard Approval Workflow (Demo)
 * 
 * @author Lorena Lama
 * 
 */
public class MWFDemoLine extends X_WF_DemoLine {

	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param WF_Demo_ID
	 * @param trxName
	 */
	public MWFDemoLine(Properties ctx, int WF_Demo_ID, String trxName) {
		super(ctx, WF_Demo_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MWFDemoLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private MWFDemo header;

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success) {
			if (getHeader() != null && getHeader().isApproved()) {
				// validate edited lines
				boolean requireApproval = //
				((!is_new() && is_ValueChanged("IsActive") && !isActive()))//
						|| is_new() //
						|| (!is_new() && is_ValueChanged(COLUMNNAME_Description));
				if (requireApproval) {
					// edit header
					getHeader().setDocStatus(MWFDemo.DOCSTATUS_NotApproved);
					getHeader().setDocAction(MWFDemo.DOCACTION_Prepare);
					getHeader().setIsApproved(false);
					success = getHeader().save();
				}
			}
		}
		return success;
	}

	/**
	 * 
	 * @return
	 */
	private MWFDemo getHeader() {
		if (getWF_Demo_ID() > 0 && header == null) {
			header = new MWFDemo(getCtx(), getWF_Demo_ID(), get_TrxName());
		}
		return header;
	}//FIXME:beforedelete

}
