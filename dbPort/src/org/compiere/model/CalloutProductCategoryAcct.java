/**
 * 
 */
package org.compiere.model;

import java.util.Properties;

import org.compiere.process.ProductCategoryAcctCopy;
import org.compiere.util.Env;


/**
 * @author Expert
 * 
 */
public class CalloutProductCategoryAcct extends CalloutEngine {

	public String newProductCategoryAcct(Properties ctx, int WindowNo,
			GridTab mTab, GridField mField, Object value) {

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
					mTab.setValue("C_AcctSchema_ID", acct.getC_AcctSchema_ID());
					mTab.setValue("CostingLevel", arrayAcctSchema[0]
							.getCostingLevel());
					mTab.setValue("CostingMethod", arrayAcctSchema[0]
							.getCostingMethod());
					mTab.setValue("P_Asset_Acct", acct.getP_Asset_Acct());
					mTab.setValue("P_Burden_Acct", acct.getP_Burden_Acct());
					mTab.setValue("P_COGS_Acct", acct.getP_COGS_Acct());
					mTab.setValue("P_CostAdjustment_Acct", acct
							.getP_CostAdjustment_Acct());
					mTab.setValue("P_CostOfProduction_Acct", acct
							.getP_CostOfProduction_Acct());
					mTab.setValue("P_Expense_Acct", acct.getP_Expense_Acct());
					mTab.setValue("P_FloorStock_Acct", acct
							.getP_FloorStock_Acct());
					mTab.setValue("P_InventoryClearing_Acct", acct
							.getP_InventoryClearing_Acct());
					mTab.setValue("P_InvoicePriceVariance_Acct", acct
							.getP_InvoicePriceVariance_Acct());
					mTab.setValue("P_Labor_Acct", acct.getP_Labor_Acct());
					mTab.setValue("P_MethodChangeVariance_Acct", acct
							.getP_MethodChangeVariance_Acct());
					mTab.setValue("P_MixVariance_Acct", acct
							.getP_MixVariance_Acct());
					mTab.setValue("P_OutsideProcessing_Acct", acct
							.getP_OutsideProcessing_Acct());
					mTab.setValue("P_Overhead_Acct", acct.getP_Overhead_Acct());
					mTab.setValue("P_PurchasePriceVariance_Acct", acct
							.getP_PurchasePriceVariance_Acct());
					mTab.setValue("P_RateVariance_Acct", acct
							.getP_RateVariance_Acct());
					mTab.setValue("P_Revenue_Acct", acct.getP_Revenue_Acct());
					mTab.setValue("P_Scrap_Acct", acct.getP_Scrap_Acct());
					mTab.setValue("P_TradeDiscountGrant_Acct", acct
							.getP_TradeDiscountGrant_Acct());
					mTab.setValue("P_TradeDiscountRec_Acct", acct
							.getP_TradeDiscountRec_Acct());
					mTab.setValue("P_UsageVariance_Acct", acct
							.getP_UsageVariance_Acct());
					mTab.setValue("P_WIP_Acct", acct.getP_WIP_Acct());
					mTab.setValue("EXME_Prov_Vta_Acct", acct
							.getEXME_Prov_Vta_Acct());
					mTab.setValue("AD_Client_ID", acct.getAD_Client_ID());
					mTab.setValue("AD_Org_ID", acct.getAD_Org_ID());

					int productCategoryID = 1;
					if (mField
							.getColumnName()
							.equals(
									X_M_Product_Category_Acct.COLUMNNAME_M_Product_Category_ID))
						productCategoryID = (Integer) mField.getValue();

					if (mTab.dataSave(false)) {

						ProductCategoryAcctCopy copy = new ProductCategoryAcctCopy();
						try {
							copy.doIt(ctx, productCategoryID, acct
									.getC_AcctSchema_ID());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return "";
	} // bankAccount

}
