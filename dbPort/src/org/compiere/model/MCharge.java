/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Charge Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MCharge.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MCharge extends X_C_Charge
{
	/** serialVersionUID. */
	private static final long serialVersionUID = 6413157549410863438L;


	/**
	 *  Get Charge Account
	 *  @param C_Charge_ID charge
	 *  @param as account schema
	 *  @param amount amount for expense(+)/revenue(-)
	 *  @return Charge Account or null
	 */
	public static MAccount getAccount (int C_Charge_ID, MAcctSchema as, BigDecimal amount)
	{
		if (C_Charge_ID == 0 || as == null)
			return null;

		int acct_index = 1;     //  Expense (positive amt)
		if (amount != null && amount.signum() < 0)
			acct_index = 2;     //  Revenue (negative amt)
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT CH_Expense_Acct, CH_Revenue_Acct ")
		.append(" FROM C_Charge_Acct ")
		.append(" WHERE C_Charge_ID=? AND C_AcctSchema_ID IN (?,?) ")
		.append(" ORDER BY AD_Client_ID DESC ");
		
		int Account_ID = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt (1, C_Charge_ID);
			pstmt.setInt (2, as.getC_AcctSchema_ID());

			// Expert: Proyecto #102 Posteo, costos y precios 
			// Cuentas contables de System por defecto
			int defaultAcctSchemaID = Env.getContextAsInt(as.getCtx(), "$C_DefaultAcctSchema_ID");
			if(defaultAcctSchemaID<=0)
				defaultAcctSchemaID = MAcctSchema.loadDefaultAcctSchema();
			pstmt.setInt (3, defaultAcctSchemaID);
						
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Account_ID = rs.getInt(acct_index);
				
				if(Account_ID>0){
					break;
				}
			}
			
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql.toString(), e);
			return null;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		//	No account
		if (Account_ID == 0)
		{
			s_log.severe ("NO account for C_Charge_ID=" + C_Charge_ID);
			return null;
		}

		//	Return Account
		MAccount acct = MAccount.get (as.getCtx(), Account_ID);
		return acct;
	}   //  getAccount

	/**
	 * 	Get MCharge from Cache
	 *	@param ctx context
	 *	@param C_Charge_ID id
	 *	@return MCharge
	 */
	public static MCharge get (Properties ctx, int C_Charge_ID)
	{
		Integer key = new Integer (C_Charge_ID);
		MCharge retValue = (MCharge)s_cache.get (key);
		if (retValue != null
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		retValue = new MCharge (ctx, C_Charge_ID, null);
		if (retValue.get_ID() != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**	Cache						*/
	private static CCache<Integer, MCharge> s_cache 
		= new CCache<Integer, MCharge> ("C_Charge", 10);
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MCharge.class);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Charge_ID id
	 *	@param trxName transaction
	 */
	public MCharge (Properties ctx, int C_Charge_ID, String trxName)
	{
		super (ctx, C_Charge_ID, null);
		if (C_Charge_ID == 0)
		{
			setChargeAmt (Env.ZERO);
			setIsSameCurrency (false);
			setIsSameTax (false);
			setIsTaxIncluded (false);	// N
		//	setName (null);
		//	setC_TaxCategory_ID (0);
		}
	}	//	MCharge

	/**
	 * 	Load Constructor
	 *	@param ctx ctx
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MCharge (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCharge

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord & success)
			insert_Accounting("C_Charge_Acct", "C_AcctSchema_Default", null);

		return success;
	}	//	afterSave

	public boolean insertAccountingClient() {
		return insert_Accounting_Client("C_Charge_Acct", "C_AcctSchema_Default", null);
	}

	
	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return delete_Accounting("C_Charge_Acct"); 
	}	//	beforeDelete

	
//	public Vector<OptionItem> getCharges() {
//
//		Vector<OptionItem> charge = null;
//
//		String sql = "select c_charge_id, name from c_charge order by name";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			
//			rs = pstmt.executeQuery();
//			charge = new Vector<OptionItem>();
//			charge.add(new OptionItem("0", " "));
//			while (rs.next()) {
//				charge.add(new OptionItem(rs.getString(1), rs.getString(2)));
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "getLines", e);
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//				pstmt = null;
//				if (rs != null)
//					rs.close();
//				rs = null;
//			} catch (Exception e) {
//				log.log(Level.SEVERE, "getLines", e);
//			}
//		}
//		return charge;
//	}
	
	public static List<LabelValueBean> getCharges(final Properties ctx, final String type, final String trxname) {

		List<LabelValueBean> charges = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select name, c_charge_id from c_charge where isactive = 'Y' and type = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_Charge"));
		sql.append(" order by name ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxname);
			pstmt.setString(1, type);
			
			rs = pstmt.executeQuery();			
			
			while (rs.next()) {				
				charges.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCharges", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return charges;
	}
	
	/**
	 * Devuelve el objeto Mcharge correspondiente.
	 * @param chargeName el nombre del cargo a buscar.
	 * @param ctx el contexto de la aplicacion.
	 * @return el objeto MCharge encontrado o null en caso de no encontrar nada.
	 * @author Jesus Cantu.
	 */
	public static MCharge getCharge(String chargeName, Properties ctx) {
		StringBuilder st = new StringBuilder("select * from C_Charge where upper(name) like ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "C_Charge"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MCharge charge = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, "%" + chargeName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				charge = new MCharge(ctx, rs, null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, "While closing objects MCharge.getcharge", e);
			}
		}
		return charge;
	}
	
	/**
	 * Listado de cargos por nivel de acceso
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Trx Name
	 * @return Listado de cargos
	 */
	public static List<MCharge> getChargeList(Properties ctx, String trxName) {
		ArrayList<MCharge> list = new ArrayList<MCharge>();

		StringBuilder sql = new StringBuilder("SELECT C_Charge.* FROM C_Charge WHERE C_Charge.isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, "C_Charge"));
		sql.append(" ORDER BY NAME ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MCharge(ctx, rs, trxName));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Listado de cargos por nivel de acceso y Tipo Egreso
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Trx Name
	 * @return Listado de cargos con Egreso
	 */
	public static List<MCharge> getChargeAcctTypeList(Properties ctx, String trxName) {
		ArrayList<MCharge> list = new ArrayList<MCharge>();

		StringBuilder sql = new StringBuilder("SELECT C_Charge.* FROM C_Charge WHERE C_Charge.isActive = 'Y' AND C_Charge.acctType = 'E' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, "C_Charge"));
		sql.append(" ORDER BY NAME ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MCharge(ctx, rs, trxName));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * Tipo de ajuste
	 * @param ctx
	 * @param cChargeID
	 * @return
	 */
	public static boolean createFactCharge(Properties ctx, int cChargeID) {
		/*
		 * IMPORTANTE
		 * Se reutilizo metodo para determinar si un cargo es pago o no.
		 * Si se cambia este metodo revisar WEnterPayments
		 */
	
		boolean type = true;
		if (cChargeID>0){
			
			// si es un pago o es informativo no se genera asiento contable
			MCharge charge = new MCharge(ctx, cChargeID, null);
			if(charge.getType().equals(X_C_Charge.TYPE_InsurancePayments)//m
					|| charge.getType().equals(X_C_Charge.TYPE_CoinsurancePayment)//n
					|| charge.getType().equals(X_C_Charge.TYPE_CopayPayment)//y
					|| charge.getType().equals(X_C_Charge.TYPE_DeductiblePayment)//e
					|| charge.getType().equals(X_C_Charge.TYPE_OthersPayment)//t
					|| charge.getType().equals(X_C_Charge.TYPE_Payment)//p
					/*|| charge.getType().equals(X_EXME_AdjustmentType.TYPE_Coinsurance) 
					|| charge.getType().equals(X_EXME_AdjustmentType.TYPE_CoPay)
					|| charge.getType().equals(X_EXME_AdjustmentType.TYPE_Deductible)*/
				)
			{
				type = false;
			}
		}
		return type;
	}
	
	/**
	 * Tipo de ajuste
	 * @param ctx
	 * @param cChargeID
	 * @return
	 */
	public static boolean createFactChargeInf(Properties ctx, int cChargeID) {
		boolean type = true;
		if (cChargeID>0){
			
			// si es un pago o es informativo no se genera asiento contable
			MCharge charge = new MCharge(ctx, cChargeID, null);
			if(charge.getType().equals(X_EXME_AdjustmentType.TYPE_Coinsurance) 
					|| charge.getType().equals(X_EXME_AdjustmentType.TYPE_CoPay)
					|| charge.getType().equals(X_EXME_AdjustmentType.TYPE_Deductible)
				)
			{
				type = false;
			}
		}
		return type;
	}

	private BigDecimal payAmt = Env.ZERO;
	public void setPayAmt(BigDecimal bigDecimal) {
		payAmt = bigDecimal;
	}
	private int EXME_CtaPac_ID = 0;
	public void setEXME_CtaPac_ID(int int1) {
		EXME_CtaPac_ID = int1;
	}
	private int C_Invoice_ID = 0;
	public void setC_Invoice_ID(int int1) {
		C_Invoice_ID = int1;
	}
	public BigDecimal getPayAmt() {
		return payAmt;
	}

	public int getEXME_CtaPac_ID() {
		return EXME_CtaPac_ID;
	}

	public int getC_Invoice_ID() {
		return C_Invoice_ID;
	}
	
	/**
	 * Objeto de impuesto del cargo
	 * 
	 * @return <MTax> tasa de impuesto
	 */
	
	public MTax getTax() {
		return MEXMETax.getImpuestoCargo(getCtx(), getC_Charge_ID(), get_TrxName());
	}
	
	
	/**
	 * Objeto de impuesto del cargo
	 * 
	 * @return <MTax> tasa de impuesto
	 */
	
	public int getTaxID() {
		MTax mTax = getTax();
		if(mTax==null){
			return 0;
		} else {
			return mTax.getC_Tax_ID();
		}
	}
	
	/**
	 * Factura
	 * 
	 * @param billingType
	 * @param C_BPartner_ID
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	public static List<MCharge> getChargesForInvoice(Properties ctx,int C_Invoice_ID, String trxName) {
		List<MCharge> lst = new ArrayList<MCharge>();
		String sql = " SELECT c.*, il.PRICELIST, i.EXME_CtaPac_ID, i.C_Invoice_ID "
				+ " FROM C_InvoiceLine il      "
				+ " inner join C_Charge  c on  il.C_Charge_ID = c.C_Charge_ID  "
				+ " inner join C_Invoice i on il.C_Invoice_ID = i.C_Invoice_ID "
				+ " WHERE il.C_Invoice_ID = ? and c.Excepcion like '%P%' and c.NEXTINVOICE ='Y' ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Invoice_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCharge charge = new MCharge(ctx, rs, null);
				charge.setPayAmt(rs.getBigDecimal("PRICELIST"));
				charge.setEXME_CtaPac_ID(rs.getInt("EXME_CtaPac_ID"));
				charge.setC_Invoice_ID(rs.getInt("C_Invoice_ID"));
				lst.add(charge);
			}
		} catch (Exception e) {
			DB.close(rs, pstmt);
			throw new MedsysException(e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	/** El cargo deacuerdo a la tasa de impuesto */
	public static MCharge getChargeTax(Properties ctx, int cTaxId, String trxName) {
		return new Query(ctx, MCharge.Table_Name, " C_TaxCategory_ID = ? AND TYPE = ? ", trxName)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setParameters(cTaxId, TYPE_GlobalInvoice)
				.first();
	}
	

	/**
	 * Objeto de impuesto del cargo
	 * 
	 * @return <MTax> tasa de impuesto
	 */
	public MTax getMTax() {
		MTax impuesto = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT C_Tax.* FROM C_CHARGE cte ")
		.append(" INNER JOIN C_TaxCategory  ON cte.C_TaxCategory_ID = C_TaxCategory.C_TaxCategory_ID ")
		.append("                           AND C_TaxCategory.IsActive = 'Y' ")
		.append("      INNER JOIN C_Tax ON C_TaxCategory.C_TaxCategory_ID = C_Tax.C_TaxCategory_ID AND C_Tax.IsActive = 'Y' ")
		.append(" WHERE cte.IsActive = 'Y' AND C_Tax.AD_Client_ID=? ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
				X_C_Charge.Table_Name, "cte"));

		//Se actualiza codigo para usar TO_DATE y evitar el TRUNC y DATE_TRUNC
		//Jesus Cantu
		sql.append(" AND TO_DATE(TO_CHAR(C_Tax.ValidFrom, 'dd/MM/yyyy'), 'dd/MM/yyyy') "); 
		sql.append (" <= TO_DATE('");
		sql.append(Constantes.getSdfFecha().format(new Date()));	
		sql.append("', 'dd/MM/yyyy') ");

		sql.append(" AND C_Tax.IsDocumentLevel = 'Y' ")
		.append(" AND cte.C_CHARGE_ID = ? ")
		.append(" ORDER BY C_Tax.ValidFrom DESC, C_Tax.IsDefault DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(getCtx()));
			pstmt.setInt(2, getC_Charge_ID());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				impuesto = new MTax(getCtx(), rs, get_TrxName());
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return impuesto;
	}
	
	public static List<MCharge> getMChargesLst(String whereClause, List<Object> params, String trxName){
		return new Query(Env.getCtx(), Table_Name, whereClause, trxName)
		.addAccessLevelSQL(true)
		.setParameters(params)
		.setOrderBy(" isactive DESC")
		.list();
	}
	
	private MProduct product;
	
	public MProduct getProduct() {
		if(product == null){
			product = new MProduct(getCtx(), getM_Product_ID(), null);
		}
		return product;
	}	
	
	private X_C_Charge_Acct chargeBAcc;
	
	public X_C_Charge_Acct getChargeAcct(){
		if(chargeBAcc == null){
			chargeBAcc = MChargeAcct.chargeAcct(Env.getCtx(), getC_Charge_ID(),
					Env.getC_AcctSchema_ID(Env.getCtx()), null);
		}
		return chargeBAcc;
	}

	private MValidCombination validComb;
	
	public MValidCombination getValidComb(){
		if(X_C_Charge.ACCTTYPE_Expense.equals(getAcctType())){
			validComb = new MValidCombination(Env.getCtx(), getChargeAcct().getCh_Expense_Acct(), null);
		} else if(X_C_Charge.ACCTTYPE_Revenue.equals(getAcctType())){
			validComb = new MValidCombination(Env.getCtx(), getChargeAcct().getCh_Revenue_Acct(), null);
		} else{
			validComb = null;
		}
		return validComb;
	}
	
	private String validCombination = StringUtils.EMPTY;
	
	public String getValidCombination(){
		if(getValidComb() != null){
			validCombination = getValidComb().getDescription();
		}
		return validCombination;
	}
	
	private String typeCharge = null;
	
	public String getTypeCharge(){
		if(typeCharge==null){
			typeCharge = MRefList.getListName(getCtx(), X_C_Charge.TYPE_AD_Reference_ID, getType());
		}
		return typeCharge;
	}
	
    private String acctTypeCharge = null;
	
	public String getAcctTypeCharge(){
		if(acctTypeCharge==null){
			acctTypeCharge = MRefList.getListName(getCtx(), X_C_Charge.ACCTTYPE_AD_Reference_ID, getAcctType());
		}
		return acctTypeCharge;
	}
	
	 private String productClassCharge = null;
		
		public String getProductClassCharge(){
			if(productClassCharge==null){
				productClassCharge = MRefList.getListName(getCtx(), X_M_Product.PRODUCTCLASS_AD_Reference_ID, getProductClass());
			}
			return productClassCharge;
		}
}	//	MCharge