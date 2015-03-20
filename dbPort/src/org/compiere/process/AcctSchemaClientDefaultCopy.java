/**
 * 
 */
package org.compiere.process;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MAcctSchemaDefault;
import org.compiere.util.DB;
import org.compiere.util.WebEnv;

/**
 * @author Expert
 *
 */
public class AcctSchemaClientDefaultCopy {

	/**
	 * 
	 * @param acct
	 * @param trxName
	 * @return
	 */
	public static int insert_M_Product_Category_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();

		// Insert new Product Category
		sql.append(" INSERT INTO M_Product_Category_Acct ")
		.append("(M_Product_Category_ID, C_AcctSchema_ID,")
		.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,")
		.append(" P_Revenue_Acct, P_Expense_Acct, P_CostAdjustment_Acct, P_InventoryClearing_Acct, P_Asset_Acct, P_CoGs_Acct,")
		.append(" P_PurchasePriceVariance_Acct, P_InvoicePriceVariance_Acct,")
		.append(" P_TradeDiscountRec_Acct, P_TradeDiscountGrant_Acct) ")

		//.append(" SELECT p.M_Product_Category_ID, acct.C_AcctSchema_ID,")
		.append(" SELECT p.M_Product_Category_ID, ? , ");//#1
		params.add(acct.getC_AcctSchema_ID());

		//.append(" p.AD_Client_ID, p.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,")
		//.append(" acct.AD_Client_ID, p.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,")
		sql.append(" ?, 0, 'Y', SysDate, 0, SysDate, 0,");//#2
		params.add(acct.getAD_Client_ID());

		//.append(" acct.P_Revenue_Acct, acct.P_Expense_Acct, acct.P_CostAdjustment_Acct, acct.P_InventoryClearing_Acct, acct.P_Asset_Acct, acct.P_CoGs_Acct,")
		sql.append(" ? , ? , ? , ? , ? , ? , "); 
		params.add(acct.getP_Revenue_Acct());
		params.add(acct.getP_Expense_Acct());
		params.add(acct.getP_CostAdjustment_Acct());
		params.add(acct.getP_InventoryClearing_Acct());
		params.add(acct.getP_Asset_Acct());
		params.add(acct.getP_COGS_Acct());

		//.append(" acct.P_PurchasePriceVariance_Acct, acct.P_InvoicePriceVariance_Acct,")
		sql.append(" ? , ? , ");
		params.add(acct.getP_PurchasePriceVariance_Acct());
		params.add(acct.getP_InvoicePriceVariance_Acct());

		//.append(" acct.P_TradeDiscountRec_Acct, acct.P_TradeDiscountGrant_Acct ")
		sql.append(" ? , ? ");
		params.add(acct.getP_TradeDiscountRec_Acct());
		params.add(acct.getP_TradeDiscountGrant_Acct());

		sql.append(" FROM M_Product_Category p ")
		.append(" WHERE p.AD_Client_ID IN (0, ?) ");
		params.add(acct.getAD_Client_ID());
		
		if(WebEnv.DEBUG){
			sql.append(" AND p.M_Product_Category_ID = 1001575 ");
		}

		sql.append(" AND NOT EXISTS (SELECT * FROM M_Product_Category_Acct pa ")
		.append(" WHERE pa.M_Product_Category_ID=p.M_Product_Category_ID ");
		
		if(WebEnv.DEBUG){
			sql.append(" AND pa.isActive = 'Y' ");
		}
		
		sql.append(" AND pa.C_AcctSchema_ID = ? ) ");
		params.add(acct.getC_AcctSchema_ID());

		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	/**
	 * 
	 * @param acct
	 * @param trxName
	 * @return
	 */
	public static int insert_C_BP_Group_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();

		//Insert Business Partner Group
		sql.append("INSERT INTO C_BP_Group_Acct ")
		.append("(C_BP_Group_ID, C_AcctSchema_ID,")
		.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,")
		.append(" C_Receivable_Acct, C_Receivable_Services_Acct, C_PrePayment_Acct,")
		.append(" V_Liability_Acct, V_Liability_Services_Acct, V_PrePayment_Acct,")
		.append(" PayDiscount_Exp_Acct, PayDiscount_Rev_Acct, WriteOff_Acct,")
		.append(" NotInvoicedReceipts_Acct, UnEarnedRevenue_Acct,")
		.append(" NotInvoicedRevenue_Acct, NotInvoicedReceivables_Acct) ")
		//+ "SELECT x.C_BP_Group_ID, acct.C_AcctSchema_ID,"
		.append(" SELECT x.C_BP_Group_ID, ? ,");//#1
		params.add(acct.getC_AcctSchema_ID());

		//+ " x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
		sql.append(" ? , 0, 'Y', SysDate, 0, SysDate, 0,");// Suponemos que no esta por organizacion 
		params.add(acct.getAD_Client_ID());

		//+ " acct.C_Receivable_Acct, acct.C_Receivable_Services_Acct, acct.C_PrePayment_Acct,"
		sql.append(" ?, ?, ?, ");
		params.add(acct.getC_Receivable_Acct());
		params.add(acct.getC_Receivable_Services_Acct());
		params.add(acct.getC_Prepayment_Acct());

		//+ " acct.V_Liability_Acct, acct.V_Liability_Services_Acct, acct.V_PrePayment_Acct,"
		sql.append(" ?, ?, ?, ");
		params.add(acct.getV_Liability_Acct());
		params.add(acct.getV_Liability_Services_Acct());
		params.add(acct.getV_Prepayment_Acct());

		//+ " acct.PayDiscount_Exp_Acct, acct.PayDiscount_Rev_Acct, acct.WriteOff_Acct,"
		sql.append(" ?, ?, ?, ");
		params.add(acct.getPayDiscount_Exp_Acct());
		params.add(acct.getPayDiscount_Rev_Acct());
		params.add(acct.getWriteOff_Acct());

		//+ " acct.NotInvoicedReceipts_Acct, acct.UnEarnedRevenue_Acct,"
		sql.append(" ?, ?, ");
		params.add(acct.getNotInvoicedReceipts_Acct());
		params.add(acct.getUnEarnedRevenue_Acct());

		//+ " acct.NotInvoicedRevenue_Acct, acct.NotInvoicedReceivables_Acct ");
		sql.append(" ?, ?, ");
		params.add(acct.getNotInvoicedRevenue_Acct());
		params.add(acct.getNotInvoicedReceivables_Acct());

		sql.append(" FROM C_BP_Group x");
		//+ " INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) "
		//+ "WHERE acct.C_AcctSchema_ID= ? "// + p_C_AcctSchema_ID
		sql.append(" WHERE p.AD_Client_ID IN (0, ?) ");
		params.add(acct.getAD_Client_ID());
		
		if(WebEnv.DEBUG){
			sql.append(" AND x.C_BP_Group_ID = 1001575 ");
		}
		
		sql.append(" AND NOT EXISTS (SELECT * FROM C_BP_Group_Acct a ")
		.append(" WHERE a.C_BP_Group_ID=x.C_BP_Group_ID ");
		
		if(WebEnv.DEBUG){
			sql.append(" AND a.isActive = 'Y' ");
		}
		
		sql.append(" AND a.C_AcctSchema_ID = ? ) ");
		params.add(acct.getC_AcctSchema_ID());

		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	public static int insert_C_BP_Employee_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();

		//	Insert new Business Partner - Employee
		sql.append(" INSERT INTO C_BP_Employee_Acct ")
		.append("(C_BPartner_ID, C_AcctSchema_ID, ")
		.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ")
		.append(" E_Expense_Acct, E_Prepayment_Acct) ");
		
		sql.append(" SELECT x.C_BPartner_ID, ? , ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ? , 0, 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ?, ? ");
		params.add(acct.getE_Expense_Acct());
		params.add(acct.getE_Prepayment_Acct());
		sql.append(" FROM C_BPartner x");
		//sql.append(" INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) ");
		//sql.append(" WHERE acct.C_AcctSchema_ID=?"); //+ p_C_AcctSchema_ID
		sql.append(" WHERE x.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND x.C_BPartner_ID = 1001575 ");
		}
		sql.append(" AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct a ");
		sql.append(" WHERE a.C_BPartner_ID=x.C_BPartner_ID ");
		
		if(WebEnv.DEBUG){
			sql.append(" AND a.isActive = 'Y' ");
		}
		//sql.append(" AND a.C_AcctSchema_ID=acct.C_AcctSchema_ID)");
		sql.append(" AND a.C_AcctSchema_ID = ? ) ");
		params.add(acct.getC_AcctSchema_ID());



		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}














	public static int insert_C_BP_Customer_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();
		sql.append(" INSERT INTO C_BP_Customer_Acct ");
		sql.append("(C_BPartner_ID, C_AcctSchema_ID, ");
		sql.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ");
		sql.append(" C_Receivable_Acct, C_Receivable_Services_Acct, C_PrePayment_Acct) ");
		sql.append("SELECT p.C_BPartner_ID, ? , ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ?, 0, 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ?, ?, ? ");
		params.add(acct.getC_Receivable_Acct());
		params.add(acct.getC_Receivable_Services_Acct());
		params.add(acct.getC_Prepayment_Acct());
		sql.append("FROM C_BPartner p ");
		//sql.append(" INNER JOIN C_BP_Group_Acct acct ON (acct.C_BP_Group_ID=p.C_BP_Group_ID)");
		//sql.append("WHERE acct.C_AcctSchema_ID= ? "); // + p_C_AcctSchema_ID	 Lama		//	#
		//sql.append(" AND p.C_BP_Group_ID=acct.C_BP_Group_ID");
		sql.append(" WHERE p.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		
		if(WebEnv.DEBUG){
			sql.append(" AND p.isActive = 'Y' ");
		}
		
		sql.append(" AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct ca ");
		sql.append("WHERE ca.C_BPartner_ID=p.C_BPartner_ID");
		sql.append(" AND ca.C_AcctSchema_ID=?)");
		params.add(acct.getC_AcctSchema_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND ca.isActive = 'Y' ");
		}
		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	public static int insert_C_BP_Vendor_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();
		sql.append(" INSERT INTO C_BP_Vendor_Acct ");
		sql.append("(C_BPartner_ID, C_AcctSchema_ID, ");
		sql.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ");
		sql.append(" V_Liability_Acct, V_Liability_Services_Acct, V_PrePayment_Acct) ");
		sql.append("SELECT p.C_BPartner_ID, ? , ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ?, 0, 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ?, ?, ? ");
		params.add(acct.getV_Liability_Acct());
		params.add(acct.getV_Liability_Services_Acct());
		params.add(acct.getV_Prepayment_Acct());
		sql.append("FROM C_BPartner p");
		//sql.append(" INNER JOIN C_BP_Group_Acct acct ON (acct.C_BP_Group_ID=p.C_BP_Group_ID)");
		//sql.append("WHERE acct.C_AcctSchema_ID= ?" );// + p_C_AcctSchema_ID			Lama//	#
		//sql.append(" AND p.C_BP_Group_ID=acct.C_BP_Group_ID");
		sql.append(" WHERE p.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND x.C_BPartner_ID = 1001575 ");
		}
		sql.append(" AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct va ");
		sql.append("WHERE va.C_BPartner_ID=p.C_BPartner_ID AND va.C_AcctSchema_ID=?)");
		params.add(acct.getC_AcctSchema_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND va.isActive = 'Y' ");
		}
		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	public static int insert_M_Warehouse_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();
		sql.append(" INSERT INTO M_Warehouse_Acct ");
		sql.append("(M_Warehouse_ID, C_AcctSchema_ID, ");
		sql.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ");
		sql.append(" W_Inventory_Acct, W_Differences_Acct, W_Revaluation_Acct, W_InvActualAdjust_Acct) ");
		sql.append(" SELECT x.M_Warehouse_ID, ?, ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ?, 0, 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ?, ?, ?, ? ");
		params.add(acct.getW_Inventory_Acct());
		params.add(acct.getW_Differences_Acct());
		params.add(acct.getW_Revaluation_Acct());
		params.add(acct.getW_InvActualAdjust_Acct());
		sql.append(" FROM M_Warehouse x");
		//sql.append(" INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) ");
		//sql.append("WHERE acct.C_AcctSchema_ID=" );+ p_C_AcctSchema_ID
		sql.append(" WHERE x.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND x.M_Warehouse_ID = 1001575 ");
		}
		sql.append(" AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct a ");
		sql.append(" WHERE a.M_Warehouse_ID = x.M_Warehouse_ID");
		if(WebEnv.DEBUG){
			sql.append(" AND a.isActive = 'Y' ");
		}
		sql.append(" AND a.C_AcctSchema_ID = ?)");
		params.add(acct.getAD_Client_ID());
		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	public static int insert_C_Project_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();
		sql.append(" INSERT INTO C_Project_Acct ");
		sql.append("(C_Project_ID, C_AcctSchema_ID, ");
		sql.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ");
		sql.append(" PJ_Asset_Acct, PJ_WIP_Acct) ");
		sql.append("SELECT x.C_Project_ID, ? , ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ? , 0 , 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ?, ? ");
		params.add(acct.getPJ_Asset_Acct());
		params.add(acct.getPJ_WIP_Acct());
		sql.append("FROM C_Project x");
		//sql.append(" INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) ");
		//sql.append("WHERE acct.C_AcctSchema_ID="); + p_C_AcctSchema_ID
		sql.append(" WHERE x.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND x.C_Project_ID = 1001575 ");
		}
		sql.append(" AND NOT EXISTS (SELECT * FROM C_Project_Acct a ");
		sql.append("WHERE a.C_Project_ID=x.C_Project_ID");
		if(WebEnv.DEBUG){
			sql.append(" AND a.isActive = 'Y' ");
		}
		sql.append(" AND a.C_AcctSchema_ID = ? )");
		params.add(acct.getC_AcctSchema_ID());
		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	public static int insert_C_Tax_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();
		sql.append(" INSERT INTO C_Tax_Acct ");
		sql.append("(C_Tax_ID, C_AcctSchema_ID, ");
		sql.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ");
		sql.append(" T_Due_Acct, T_Liability_Acct, T_Credit_Acct, T_Receivables_Acct, T_Expense_Acct) ");
		sql.append(" SELECT x.C_Tax_ID, ? , ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ? , 0, 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ?, ?, ?, ?, ? ");
		params.add(acct.getT_Due_Acct());
		params.add(acct.getT_Liability_Acct());
		params.add(acct.getT_Credit_Acct());
		params.add(acct.getT_Receivables_Acct());
		params.add(acct.getT_Expense_Acct());
		sql.append(" FROM C_Tax x");
		//sql.append(" INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) ");
		//sql.append(" WHERE acct.C_AcctSchema_ID="); + p_C_AcctSchema_ID
		sql.append(" WHERE x.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND x.C_Tax_ID = 1001575 ");
		}
		sql.append(" AND NOT EXISTS (SELECT * FROM C_Tax_Acct a ");
		sql.append("WHERE a.C_Tax_ID=x.C_Tax_ID");
		if(WebEnv.DEBUG){
			sql.append(" AND a.isActive = 'Y' ");
		}
		sql.append(" AND a.C_AcctSchema_ID = ? )");
		params.add(acct.getC_AcctSchema_ID());
		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	public static int insert_C_BankAccount_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();
		sql.append(" INSERT INTO C_BankAccount_Acct ");
		sql.append("(C_BankAccount_ID, C_AcctSchema_ID, ");
		sql.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ");
		sql.append(" B_InTransit_Acct, B_Asset_Acct, B_Expense_Acct, B_InterestRev_Acct, B_InterestExp_Acct, ");
		sql.append(" B_Unidentified_Acct, B_UnallocatedCash_Acct, B_PaymentSelect_Acct, ");
		sql.append(" B_SettlementGain_Acct, B_SettlementLoss_Acct, ");
		sql.append(" B_RevaluationGain_Acct, B_RevaluationLoss_Acct) ");
		sql.append("SELECT x.C_BankAccount_ID, ?, ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ?, 0, 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ?, ?, ?, ?, ?, ");
		params.add(acct.getB_InTransit_Acct());
		params.add(acct.getB_Asset_Acct());
		params.add(acct.getB_Expense_Acct());
		params.add(acct.getB_InterestRev_Acct());
		params.add(acct.getB_InterestExp_Acct());
		sql.append(" ?, ?, ?, ");
		params.add(acct.getB_Unidentified_Acct() );
		params.add(acct.getB_Unidentified_Acct() );
		params.add(acct.getB_PaymentSelect_Acct() );
		sql.append(" ?, ?, ");
		params.add(acct.getB_SettlementGain_Acct() );
		params.add(acct.getB_SettlementLoss_Acct() );
		sql.append(" ?, ? ");
		params.add(acct.getB_RevaluationGain_Acct() );
		params.add(acct.getB_RevaluationLoss_Acct() );
		sql.append("FROM C_BankAccount x");
		//sql.append(" INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) ");
		//sql.append("WHERE acct.C_AcctSchema_ID="); + p_C_AcctSchema_ID
		sql.append(" WHERE x.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND x.C_BankAccount_ID = 1001575 ");
		}
		sql.append(" AND NOT EXISTS (SELECT * FROM C_BankAccount_Acct a ");
		sql.append("WHERE a.C_BankAccount_ID=x.C_BankAccount_ID");
		if(WebEnv.DEBUG){
			sql.append(" AND a.isActive = 'Y' ");
		}
		sql.append(" AND a.C_AcctSchema_ID=?)");;
		params.add(acct.getC_AcctSchema_ID());
		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	public static int insert_C_Withholding_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();
		//	Insert new Withholding
		sql.append(" INSERT INTO C_Withholding_Acct ");
		sql.append("(C_Withholding_ID, C_AcctSchema_ID, ");
		sql.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ");
		sql.append("	Withholding_Acct) ");
		sql.append("SELECT x.C_Withholding_ID, ? , ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ?, 0, 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ? ");
		params.add(acct.getWithholding_Acct());
		sql.append("FROM C_Withholding x");
		//sql.append(" INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) ");
		//sql.append("WHERE acct.C_AcctSchema_ID=" + p_C_AcctSchema_ID
		sql.append(" WHERE x.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND x.C_Withholding_ID = 1001575 ");
		}
		sql.append(" AND NOT EXISTS (SELECT * FROM C_Withholding_Acct a ");
		sql.append("WHERE a.C_Withholding_ID=x.C_Withholding_ID");
		if(WebEnv.DEBUG){
			sql.append(" AND a.isActive = 'Y' ");
		}
		sql.append(" AND a.C_AcctSchema_ID = ? )");
		params.add(acct.getC_AcctSchema_ID());
		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	public static int insert_C_Charge_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();
		sql.append(" INSERT INTO C_Charge_Acct ");
		sql.append("(C_Charge_ID, C_AcctSchema_ID, ");
		sql.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ");
		sql.append(" Ch_Expense_Acct, Ch_Revenue_Acct) ");
		sql.append("SELECT x.C_Charge_ID, ? , ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ?, 0, 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ?, ? ");
		params.add(acct.getCh_Expense_Acct());
		params.add(acct.getCh_Revenue_Acct());
		sql.append(" FROM C_Charge x ");
		//sql.append(" INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) ");
		//sql.append("WHERE acct.C_AcctSchema_ID=" + p_C_AcctSchema_ID
		sql.append(" WHERE x.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND x.C_Charge_ID = 1001575 ");
		}
		sql.append(" AND NOT EXISTS (SELECT * FROM C_Charge_Acct a ");
		sql.append("WHERE a.C_Charge_ID=x.C_Charge_ID");
		if(WebEnv.DEBUG){
			sql.append(" AND a.isActive = 'Y' ");
		}
		sql.append(" AND a.C_AcctSchema_ID = ? )");
		params.add(acct.getC_AcctSchema_ID());
		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

	public static int insert_C_Cashbook_Acct(MAcctSchemaDefault acct, String trxName){

		StringBuilder sql = new StringBuilder();
		int count = 0;
		List<Integer> params = new ArrayList<Integer>();
		sql.append(" INSERT INTO C_Cashbook_Acct ");
		sql.append("(C_Cashbook_ID, C_AcctSchema_ID, ");
		sql.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, ");
		sql.append(" CB_Asset_Acct, CB_Differences_Acct, CB_CashTransfer_Acct, ");
		sql.append(" CB_Expense_Acct, CB_Receipt_Acct) ");
		sql.append("SELECT x.C_Cashbook_ID, ? , ");
		params.add(acct.getC_AcctSchema_ID());
		sql.append(" ?, 0, 'Y', SysDate, 0, SysDate, 0, ");
		params.add(acct.getAD_Client_ID());
		sql.append(" ?, ?, ?, ");
		params.add(acct.getCB_Asset_Acct());
		params.add(acct.getCB_Differences_Acct());
		params.add(acct.getCB_CashTransfer_Acct());
		sql.append(" ?, ? ");
		params.add(acct.getCB_Expense_Acct());
		params.add(acct.getCB_Receipt_Acct());
		sql.append("FROM C_Cashbook x");
		//sql.append(" INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) ");
		//sql.append("WHERE acct.C_AcctSchema_ID=" + p_C_AcctSchema_ID
		sql.append(" WHERE x.AD_Client_ID IN (0, ? )");
		params.add(acct.getAD_Client_ID());
		if(WebEnv.DEBUG){
			sql.append(" AND x.C_Cashbook_ID = 1001575 ");
		}
		sql.append(" AND NOT EXISTS (SELECT * FROM C_Cashbook_Acct a ");
		sql.append("WHERE a.C_Cashbook_ID=x.C_Cashbook_ID");
		if(WebEnv.DEBUG){
			sql.append(" AND a.isActive = 'Y' ");
		}
		sql.append(" AND a.C_AcctSchema_ID=?)");
		params.add(acct.getC_AcctSchema_ID());
		count = DB.executeUpdateEx(sql.toString(), params.toArray(), trxName);

		return count;
	}

}

