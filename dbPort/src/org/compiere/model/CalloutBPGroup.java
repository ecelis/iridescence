/**
 * 
 */
package org.compiere.model;

import java.util.Properties;

import org.compiere.process.BPGroupAcctCopy;
import org.compiere.util.Env;

/**
 * @author Expert
 * 
 */
public class CalloutBPGroup extends CalloutEngine {

	public String newGroupAcc(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		// TODO: Se puede enviar un mensaje de advertencia ???

		// Todo pasa si el cliente es diferente de system
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		if (AD_Client_ID > 0) {
			// Copiar todos lo del esquema del cliente a esta categoria

			// 1. buscamos el esquema del cliente
			MAcctSchema[] arrayAcctSchema = MAcctSchema.getClientAcctSchema(
					ctx, AD_Client_ID);
			if (arrayAcctSchema == null || arrayAcctSchema.length <= 0) {
				;// throw new
				// CompiereSystemError("Not Found - C_AcctSchema_ID for AD_Client_ID ="
				// + AD_Client_ID);
			} else {
				// for (int i = 0; i < arrayAcctSchema.length; i++) {
				// Solo el primer esquema es el que se tomara
				MAcctSchemaDefault acct = MAcctSchemaDefault.get(ctx,
						arrayAcctSchema[0].getC_AcctSchema_ID());
				if (acct == null || acct.get_ID() == 0) {
					;// throw new
					// CompiereSystemError("Default Not Found - C_AcctSchema_ID="
					// + arrayAcctSchema[i].getC_AcctSchema_ID());
				} else {
					// mTab.setValue("M_AttributeSetInstance_ID", null);
					mTab.setValue("C_AcctSchema_ID", acct.getC_AcctSchema_ID());
					// mTab.setValue("C_BP_Group_ID", null);
					mTab.setValue("C_Prepayment_Acct", acct
							.getC_Prepayment_Acct());
					mTab.setValue("C_Receivable_Acct", acct
							.getC_Receivable_Acct());
					mTab.setValue("C_Receivable_Services_Acct", acct
							.getC_Receivable_Services_Acct());
					mTab.setValue("NotInvoicedReceipts_Acct", acct
							.getNotInvoicedReceipts_Acct());
					mTab.setValue("NotInvoicedReceivables_Acct", acct
							.getNotInvoicedReceivables_Acct());
					mTab.setValue("NotInvoicedRevenue_Acct", acct
							.getNotInvoicedRevenue_Acct());
					mTab.setValue("PayDiscount_Exp_Acct", acct
							.getPayDiscount_Exp_Acct());
					mTab.setValue("PayDiscount_Rev_Acct", acct
							.getPayDiscount_Rev_Acct());
					mTab.setValue("UnEarnedRevenue_Acct", acct
							.getUnEarnedRevenue_Acct());
					mTab.setValue("V_Liability_Acct", acct
							.getV_Liability_Acct());
					mTab.setValue("V_Liability_Services_Acct", acct
							.getV_Liability_Services_Acct());
					mTab.setValue("V_Prepayment_Acct", acct
							.getV_Prepayment_Acct());
					mTab.setValue("WriteOff_Acct", acct.getWriteOff_Acct());
					mTab.setValue("EXME_Cob_NF_Acct", acct
							.getEXME_Cob_NF_Acct());

					mTab.setValue("AD_Client_ID", acct.getAD_Client_ID());
					mTab.setValue("AD_Org_ID", acct.getAD_Org_ID());

					int C_BP_Group_ID = 0;
					if (mField.getColumnName().equals(
							X_C_BP_Group_Acct.COLUMNNAME_C_BP_Group_ID))
						C_BP_Group_ID = (Integer) mField.getValue();

					if (mTab.dataSave(false)) {

						BPGroupAcctCopy copy = new BPGroupAcctCopy();
						try {
							copy.doIt(ctx, C_BP_Group_ID, acct.getC_AcctSchema_ID());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return "";
	} //	

}
