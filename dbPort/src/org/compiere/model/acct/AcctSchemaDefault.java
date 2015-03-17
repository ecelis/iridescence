package org.compiere.model.acct;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaDefault;
import org.compiere.model.POInfo;
import org.compiere.model.X_C_AcctSchema_Default;

/**
 * Clase que valida las cuentas por defecto del esquema contable
 * @author twrytania
 *
 */
public class AcctSchemaDefault {
	
	/**
	 * 	Get Accounting Schema Default Info
	 *	@param ctx context
	 *	@param C_AcctSchema_ID id
	 *	@return defaults
	 */
	public static boolean isFullCatalog (final Properties ctx, final int C_AcctSchema_ID, final String trxName)
	{
		final MAcctSchema mAcctSchema = new MAcctSchema(ctx, C_AcctSchema_ID, trxName);
		boolean isCE = mAcctSchema.isTaxCorrection();//TODO  Cambiar a columna Real
		
		final MAcctSchemaDefault mAcctSchemaDefault = new MAcctSchemaDefault(ctx, C_AcctSchema_ID, trxName);
		final POInfo mPOInfo = mAcctSchemaDefault.getP_info();
		
		for (int index = 0; index < mPOInfo.getColumnCount(); index++) {// numero de columnas del PO
			String columnName = mPOInfo.getColumnName(index);


			if(AccountSchemaDefault.DEFAULT_MANDATORY.isMandatory(columnName)
					|| AccountSchemaDefault.ELECTRONICACCOUNTING_MANDATORY.isMandatory(columnName)){
				if (mAcctSchemaDefault.get_ValueAsInt(index)<=0){
					return false;
				}
			}


			if(isCE && AccountSchemaDefault.ELECTRONICACCOUNTING_NOMANDATORY.isMandatory(columnName)){
				if (mAcctSchemaDefault.get_ValueAsInt(index)<=0){
					return false;
				}
			}
			
		}
		return true;
	}	//	get
	

	public enum AccountSchemaDefault {
		ELECTRONICACCOUNTING_NOMANDATORY(
				X_C_AcctSchema_Default.COLUMNNAME_P_ExpenseFgn_Acct,// = "P_ExpenseFgn_Acct";
				X_C_AcctSchema_Default.COLUMNNAME_P_RevenueTECash_Acct,// = "P_RevenueTECash_Acct";
				X_C_AcctSchema_Default.COLUMNNAME_P_RevenueTECredit_Acct,// = "P_RevenueTECredit_Acct";
				X_C_AcctSchema_Default.COLUMNNAME_P_RevenueTGCash_Acct,// = "P_RevenueTGCash_Acct";
				X_C_AcctSchema_Default.COLUMNNAME_P_RevenueTGCredit_Acct,// = "P_RevenueTGCredit_Acct";
				X_C_AcctSchema_Default.COLUMNNAME_P_RevenueTZCash_Acct,// = "P_RevenueTZCash_Acct";
				X_C_AcctSchema_Default.COLUMNNAME_P_RevenueTZCredit_Acct,// = "P_RevenueTZCredit_Acct";
				X_C_AcctSchema_Default.COLUMNNAME_P_TradeDiscountGrantE_Acct,// = "P_TradeDiscountGrantE_Acct";
				X_C_AcctSchema_Default.COLUMNNAME_P_TradeDiscountGrantG_Acct,// = "P_TradeDiscountGrantG_Acct";
				X_C_AcctSchema_Default.COLUMNNAME_P_TradeDiscountGrantZ_Acct), // = "P_TradeDiscountGrantZ_Acct";

				ELECTRONICACCOUNTING_MANDATORY(
						X_C_AcctSchema_Default.COLUMNNAME_B_AssetFgn_Acct, // "B_AssetFgn_Acct";
						X_C_AcctSchema_Default.COLUMNNAME_B_InTransitFgn_Acct, // "B_InTransitFgn_Acct";
						X_C_AcctSchema_Default.COLUMNNAME_C_ReceivableFgn_Acct, // "C_ReceivableFgn_Acct";
						X_C_AcctSchema_Default.COLUMNNAME_V_LiabilityFgn_Acct), // "V_LiabilityFgn_Acct";

						DEFAULT_MANDATORY(
								X_C_AcctSchema_Default.COLUMNNAME_B_Asset_Acct, // "B_Asset_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_B_Expense_Acct, // "B_Expense_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_B_InterestExp_Acct, // "B_InterestExp_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_B_InterestRev_Acct, // "B_InterestRev_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_B_InTransit_Acct, // "B_InTransit_Acct";
								
								X_C_AcctSchema_Default.COLUMNNAME_B_PaymentSelect_Acct, // "B_PaymentSelect_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_B_RevaluationGain_Acct, // "B_RevaluationGain_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_B_RevaluationLoss_Acct, // "B_RevaluationLoss_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_B_SettlementGain_Acct, // "B_SettlementGain_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_B_SettlementLoss_Acct, // "B_SettlementLoss_Acct";
								// #10
								X_C_AcctSchema_Default.COLUMNNAME_B_UnallocatedCash_Acct, // "B_UnallocatedCash_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_B_Unidentified_Acct, // "B_Unidentified_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_CB_Asset_Acct, // "CB_Asset_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_CB_CashTransfer_Acct, // "CB_CashTransfer_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_CB_Differences_Acct, // "CB_Differences_Acct";
								
								X_C_AcctSchema_Default.COLUMNNAME_CB_Expense_Acct, // "CB_Expense_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_CB_Receipt_Acct, // "CB_Receipt_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_Ch_Expense_Acct, // "Ch_Expense_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_Ch_Revenue_Acct, // "Ch_Revenue_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_C_Prepayment_Acct, // "C_Prepayment_Acct";
								// #20
								X_C_AcctSchema_Default.COLUMNNAME_C_Receivable_Acct, // "C_Receivable_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_C_Receivable_Services_Acct, // "C_Receivable_Services_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_E_Expense_Acct, // "E_Expense_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_E_Prepayment_Acct, // "E_Prepayment_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_NotInvoicedReceipts_Acct, // "NotInvoicedReceipts_Acct";

								X_C_AcctSchema_Default.COLUMNNAME_NotInvoicedReceivables_Acct, // "NotInvoicedReceivables_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_NotInvoicedRevenue_Acct, // "NotInvoicedRevenue_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_Asset_Acct, // "P_Asset_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_PayDiscount_Exp_Acct, // "PayDiscount_Exp_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_PayDiscount_Rev_Acct, // "PayDiscount_Rev_Acct";
								// #30
								X_C_AcctSchema_Default.COLUMNNAME_P_Burden_Acct, // "P_Burden_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_COGS_Acct, // "P_COGS_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_CostAdjustment_Acct, // "P_CostAdjustment_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_CostOfProduction_Acct, // "P_CostOfProduction_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_Expense_Acct, // "P_Expense_Acct";
								
								X_C_AcctSchema_Default.COLUMNNAME_P_FloorStock_Acct, // "P_FloorStock_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_InventoryClearing_Acct, // "P_InventoryClearing_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_InvoicePriceVariance_Acct, // "P_InvoicePriceVariance_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_PJ_Asset_Acct, // "PJ_Asset_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_PJ_WIP_Acct, // "PJ_WIP_Acct";
								// #40
								X_C_AcctSchema_Default.COLUMNNAME_P_Labor_Acct, // "P_Labor_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_MethodChangeVariance_Acct, // "P_MethodChangeVariance_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_MixVariance_Acct, // "P_MixVariance_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_OutsideProcessing_Acct, // "P_OutsideProcessing_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_Overhead_Acct, // "P_Overhead_Acct";
								
								X_C_AcctSchema_Default.COLUMNNAME_P_PurchasePriceVariance_Acct, // "P_PurchasePriceVariance_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_RateVariance_Acct, // "P_RateVariance_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_Revenue_Acct, // "P_Revenue_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_Scrap_Acct, // "P_Scrap_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_TradeDiscountGrant_Acct, // "P_TradeDiscountGrant_Acct";
								// #50
								X_C_AcctSchema_Default.COLUMNNAME_P_TradeDiscountRec_Acct, // "P_TradeDiscountRec_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_UsageVariance_Acct, // "P_UsageVariance_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_P_WIP_Acct, // "P_WIP_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_RealizedGain_Acct, // "RealizedGain_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_RealizedLoss_Acct, // "RealizedLoss_Acct";
								
								X_C_AcctSchema_Default.COLUMNNAME_T_Credit_Acct, // "T_Credit_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_T_Due_Acct, // "T_Due_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_T_Expense_Acct, // "T_Expense_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_T_Liability_Acct, // "T_Liability_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_T_Receivables_Acct, // "T_Receivables_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_UnEarnedRevenue_Acct, // "UnEarnedRevenue_Acct";
								// #60
								X_C_AcctSchema_Default.COLUMNNAME_UnrealizedGain_Acct, // "UnrealizedGain_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_UnrealizedLoss_Acct, // "UnrealizedLoss_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_V_Liability_Acct, // "V_Liability_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_V_Liability_Services_Acct, // "V_Liability_Services_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_V_Prepayment_Acct, // "V_Prepayment_Acct";
								
								X_C_AcctSchema_Default.COLUMNNAME_W_Differences_Acct, // "W_Differences_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_W_InvActualAdjust_Acct, // "W_InvActualAdjust_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_W_Inventory_Acct, // "W_Inventory_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_Withholding_Acct, // "Withholding_Acct";
								X_C_AcctSchema_Default.COLUMNNAME_W_Revaluation_Acct, // "W_Revaluation_Acct";
								// #70
								X_C_AcctSchema_Default.COLUMNNAME_WriteOff_Acct // "WriteOff_Acct";
								),

								DEFAULT_NOMANDATORY(
										X_C_AcctSchema_Default.COLUMNNAME_EXME_Cob_NF_Acct, // "EXME_Cob_NF_Acct";
										X_C_AcctSchema_Default.COLUMNNAME_EXME_Prov_Vta_Acct // "EXME_Prov_Vta_Acct";

										);


		final List<String> mandatory = new ArrayList<String>();
		
		private AccountSchemaDefault(String... supportedTypes) {
			for (String data:supportedTypes) {
				mandatory.add(data);
			}
		}

		public boolean isMandatory(String field){
			return mandatory.contains(field);
		}
	}
}
