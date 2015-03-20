/**
 * 
 */
package org.compiere.model;

import java.util.Properties;


/**
 * @author Expert
 * @deprecated
 */
public class MEXMEInventory extends MInventory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6548589911261104463L;

//	/**
//	 * @param ctx
//	 * @param M_Inventory_ID
//	 * @param trxName
//	 */
	public MEXMEInventory(Properties ctx, int M_Inventory_ID, String trxName) {
		super(ctx, M_Inventory_ID, trxName);
	}

//	/**
//	 * @param ctx
//	 * @param rs
//	 * @param trxName
//	 */
//	public MEXMEInventory(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}
//
//	/**
//	 * @param wh
//	 */
//	public MEXMEInventory(MWarehouse wh) {
//		super(wh);
//	}
//	
//	/**
//	 * Para inventario fisico, desde surtido de farmacia
//	 * @param wh
//	 */
//	public MEXMEInventory(MWarehouse wh, final String description, final int adOrgTrxID) {
//		super(wh);
//		
//		setDescription(description);
//		setMovementDate(DB.getTimestampForOrg(getCtx()));
//		setPosted(false);
//		setProcessed(false);
//		setProcessing(false);
//		setUpdateQty("N");
//		setGenerateList("N"); // GenerateList
//		setAD_OrgTrx_ID(adOrgTrxID);
//		setIsApproved(false);
//		setDocStatus(X_M_Inventory.DOCSTATUS_Drafted);
//		setDocAction(X_M_Inventory.DOCACTION_Complete);
//		setApprovalAmt(Env.ZERO);
//		
//		List<LabelValueBean> lstDocs = null;
//		try {
//			lstDocs = MEXMEDocType.getDocBaseTypeMMI(getCtx());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		if(lstDocs != null && lstDocs.size()>0)
//			setC_DocType_ID(Integer.parseInt(lstDocs.get(0).getValue()));//1000424);
//		
//		
//		//setReversal_ID(Reversal_ID); // cuando graba el ej. es null
//		//setVerPoliza(false);
//		//setReport(Report); // cuando graba el ej. es null
//		//setPrint(Print); // cuando graba el ej. es null
//		//setM_PerpetualInv_ID(M_PerpetualInv_ID); // cuando graba el ej. es null
//		//setC_Project_ID(C_Project_ID); // cuando graba el ej. es null
//		//setC_Campaign_ID(C_Campaign_ID); // cuando graba el ej. es null
//		//setC_Activity_ID(C_Activity_ID); // cuando graba el ej. es null
//		//setUser1_ID(User1_ID); // cuando graba el ej. es null
//		//setUser2_ID(); // cuando graba el ej. es null
//		
//		/****************** MO
//		MOVEMENTQTY
//		M_INVENTORYLINE_ID
//		M_ATTRIBUTESETINSTANCE_ID
//		SERNO
//		*/
//	}
}
