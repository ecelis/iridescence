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
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.OptionItem;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

/**
 *  Tax Model
 *
 *	@author Jorg Janke
 *	@version $Id: MTax.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MTax extends X_C_Tax
{	
	/** serialVersionUID */
	private static final long serialVersionUID = 8512324109578697324L;


	/**
	 * 	Get All Tax codes (for AD_Client)
	 *	@param ctx context
	 *	@return MTax
	 */
	public static MTax[] getAll (Properties ctx)
	{
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
//		Integer key = new Integer (AD_Client_ID);
//		MTax[] retValue = (MTax[])s_cacheAll.get(key);
//		if (retValue != null 
//				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
//				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue[0].getCtx(), "#AD_Session_ID"))) //lama
//			return retValue;
		
		//	Create it
		String sql = "SELECT * FROM C_Tax WHERE AD_Client_ID=?"
			+ "ORDER BY C_Country_ID, C_Region_ID, To_Country_ID, To_Region_ID";
		ArrayList<MTax> list = new ArrayList<MTax>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MTax tax = new MTax(ctx, rs, null);
//				s_cache.put (new Integer(tax.getC_Tax_ID()), tax);
				list.add (tax);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} 
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		} 
		catch (Exception e)
		{
			pstmt = null;
		}
		
		//	Create Array
		MTax[] retValue = new MTax[list.size ()];
		list.toArray (retValue);
//		//
//		s_cacheAll.put(key, retValue);
		return retValue;
	}	//	getAll

	
	/**
	 * 	Get Tax from Cache
	 *	@param ctx context
	 *	@param C_Tax_ID id
	 *	@return MTax
	 */
	public static MTax get (Properties ctx, int C_Tax_ID)
	{
		Integer key = new Integer (C_Tax_ID);
		MTax retValue = (MTax) s_cache.get (key);
		if (retValue != null 
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		retValue = new MTax (ctx, C_Tax_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**	Cache						*/
	private static CCache<Integer,MTax>		s_cache	= new CCache<Integer,MTax>("C_Tax", 5);
	/**	Cache of Client						*/
	private static CCache<Integer,MTax[]>	s_cacheAll = new CCache<Integer,MTax[]>("C_Tax", 5);
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MTax.class);

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Tax_ID id
	 *	@param trxName transaction
	 */
	public MTax (Properties ctx, int C_Tax_ID, String trxName)
	{
		super (ctx, C_Tax_ID, trxName);
		if (C_Tax_ID == 0)
		{
		//	setC_Tax_ID (0);		PK
			setIsDefault (false);
			setIsDocumentLevel (true);
			setIsSummary (false);
			setIsTaxExempt (false);
		//	setName (null);
			setRate (Env.ZERO);
			setRequiresTaxCertificate (false);
		//	setC_TaxCategory_ID (0);	//	FK
			setSOPOType (SOPOTYPE_All);
			setValidFrom (TimeUtil.getDay(1990,1,1));
			setIsSalesTax(false);
		}
	}	//	MTax

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MTax (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MTax

	/**
	 * 	New Constructor
	 *	@param ctx
	 *	@param Name
	 *	@param Rate
	 *	@param C_TaxCategory_ID
	 *	@param trxName transaction
	 */
	public MTax (Properties ctx, String Name, BigDecimal Rate, int C_TaxCategory_ID, String trxName)
	{
		this (ctx, 0, trxName);
		setName (Name);
		setRate (Rate == null ? Env.ZERO : Rate);
		setC_TaxCategory_ID (C_TaxCategory_ID);	//	FK
	}	//	MTax

	/**	100					*/
	private static BigDecimal ONEHUNDRED = new BigDecimal(100);
	/**	Child Taxes			*/
	private MTax[]			m_childTaxes = null;
	/** Postal Codes		*/
	private MTaxPostal[]	m_postals = null;
	
	
	/**
	 * 	Get Child Taxes
	 * 	@param requery reload
	 *	@return array of taxes or null
	 */
	public MTax[] getChildTaxes (boolean requery)
	{
		if (!isSummary())
			return null;
		if (m_childTaxes != null && !requery)
			return m_childTaxes;
		//
		String sql = "SELECT * FROM C_Tax WHERE Parent_Tax_ID=? AND AD_Client_ID = ? ";
		ArrayList<MTax> list = new ArrayList<MTax>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getC_Tax_ID());
			pstmt.setInt(2, Env.getAD_Client_ID(getCtx()));
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MTax(getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		m_childTaxes = new MTax[list.size ()];
		list.toArray (m_childTaxes);
		return m_childTaxes;
	}	//	getChildTaxes
	
	/**
	 *  Method to obtain the list of taxes
	 *  @return Vector<OptionItem>	 
	 */
	public Vector<OptionItem> getTaxes(){
		StringBuilder sb = new StringBuilder("SELECT C_Tax_id,Name ");
		sb.append("FROM C_Tax ");
		sb.append("WHERE Isactive='Y'");
		StringBuilder sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sb.toString(),this.get_TableName()));
		Vector<OptionItem> taxes=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
	
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.clearParameters();				
			rs = pstmt.executeQuery();
			taxes = new Vector<OptionItem>();
			taxes.add(new OptionItem("0",""));
		while (rs.next()){
			
					taxes.add(new OptionItem(rs.getString(1),rs.getString(2)));
				}
			}catch(SQLException e){
					e.printStackTrace();
				}
			finally{
				try{
					if(pstmt!=null)
						pstmt.close();
					if(rs!=null)
						rs.close();
				}catch(SQLException e){
					log.log(Level.SEVERE, "getTaxes", e);
				}
			}
		
		return taxes;
	}
	
	/**
	 * 	Get Postal Qualifiers
	 *	@param requery requery
	 *	@return array of postal codes
	 */
	public MTaxPostal[] getPostals (boolean requery)
	{
		if (m_postals != null && !requery)
			return m_postals;
	
		String sql = "SELECT * FROM C_TaxPostal WHERE C_Tax_ID=? AND AD_Client_ID =? ORDER BY Postal, Postal_To";
		ArrayList<MTaxPostal> list = new ArrayList<MTaxPostal>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getC_Tax_ID());
			pstmt.setInt (2, Env.getAD_Client_ID(getCtx()));
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		} catch (Exception e)
		{
			pstmt = null;
		}
		
		m_postals = new MTaxPostal[list.size ()];
		list.toArray (m_postals);
		return m_postals;
	}	//	getPostals
	
	/**
	 * 	Do we have Postal Codes
	 *	@return true if postal codes exist
	 */
	public boolean isPostal()
	{
		return getPostals(false).length > 0;
	}	//	isPostal
	
	/**
	 * 	Is Zero Tax
	 *	@return true if tax rate is 0
	 */
	public boolean isZeroTax()
	{
		return Env.ZERO.compareTo(getRate()) == 0;
	}	//	isZeroTax
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MTax[");
		sb.append(get_ID()).append(",").append(getName())
			.append(", SO/PO=").append(getSOPOType())
			.append(",Rate=").append(getRate())
			.append(",C_TaxCategory_ID=").append(getC_TaxCategory_ID())
			.append(",Summary=").append(isSummary())
			.append(",Parent=").append(getParent_Tax_ID())
			.append(",Country=").append(getC_Country_ID()).append("|").append(getTo_Country_ID())
			.append(",Region=").append(getC_Region_ID()).append("|").append(getTo_Region_ID())
			.append("]");
		return sb.toString();
	}	//	toString

	
	/**
	 * 	Calculate Tax - no rounding
	 *	@param amount amount
	 *	@param taxIncluded if true tax is calculated from gross otherwise from net 
	 *	@param scale scale 
	 *	@return  tax amount
	 */
	public BigDecimal calculateTax (BigDecimal amount, boolean taxIncluded, int scale)
	{
		//	Null Tax
		if (isZeroTax())
			return Env.ZERO;
		
		BigDecimal multiplier = getRate().divide(ONEHUNDRED, 12, BigDecimal.ROUND_HALF_UP);		

		BigDecimal tax = null;		
		if (!taxIncluded)	//	$100 * 6 / 100 == $6 == $100 * 0.06
		{
			tax = amount.multiply (multiplier);
		}
		else			//	$106 - ($106 / (100+6)/100) == $6 == $106 - ($106/1.06)
		{
			multiplier = multiplier.add(Env.ONE);
			BigDecimal base = amount.divide(multiplier, 12, BigDecimal.ROUND_HALF_UP); 
			tax = amount.subtract(base);
		}
		BigDecimal finalTax = tax.setScale(scale, BigDecimal.ROUND_HALF_UP);
		log.fine("calculateTax " + amount 
			+ " (incl=" + taxIncluded + ",mult=" + multiplier + ",scale=" + scale 
			+ ") = " + finalTax + " [" + tax + "]");
		return finalTax;
	}	//	calculateTax

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord & success)
			insert_Accounting("C_Tax_Acct", "C_AcctSchema_Default", null);

		return success;
	}	//	afterSave

	public boolean insertAccountingClient() {
			return insert_Accounting_Client("C_Tax_Acct", "C_AcctSchema_Default", null);
	}
	
	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return delete_Accounting("C_Tax_Acct"); 
	}	//	beforeDelete

	/**
	 *	Get Account
	 *  @param AcctType see ACCTTYPE_*
	 *  @param as account schema
	 *  @return Account
	 */
	public static MAccount getAccount (int AcctType, MAcctSchema as, int m_C_Tax_ID)
	{
		if (AcctType < 0 || AcctType > 4)
			return null;

		//
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT T_Due_Acct, T_Liability_Acct, T_Credit_Acct, T_Receivables_Acct, T_Expense_Acct ")
		.append(" FROM C_Tax_Acct WHERE C_Tax_ID=? AND AD_Client_ID=? AND C_AcctSchema_ID IN (?,?) ")
		.append(" ORDER BY AD_Client_ID DESC ");
		
		int validCombination_ID = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, m_C_Tax_ID);
			pstmt.setInt(2, as.getAD_Client_ID());
			pstmt.setInt(3, as.getC_AcctSchema_ID());
			
			// Expert: Proyecto #102 Posteo, costos y precios 
			// Cuentas contables de System por defecto
			int defaultAcctSchemaID = Env.getContextAsInt(as.getCtx(), "$C_DefaultAcctSchema_ID");
			if(defaultAcctSchemaID<=0)
				defaultAcctSchemaID = MAcctSchema.loadDefaultAcctSchema();
			pstmt.setInt(4, defaultAcctSchemaID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				validCombination_ID = rs.getInt(AcctType+1);    //  1..5
				if(validCombination_ID>0){
					break;
				}
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		if (validCombination_ID == 0)
			return null;
		return MAccount.get(as.getCtx(), validCombination_ID);
	}   //  getAccountBigDecimal
	
	
	/**
	 *  Desglosar iva
	 * @return BigDecimal rate / 100 + 1
	 */
	public BigDecimal getDesglosar(){
		return getMultiplier().add(Env.ONE);//
	}

	public BigDecimal getMultiplier(){
		return getRate().divide(Env.ONEHUNDRED, 12, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Tasas de impuesto de medicamentos
	 * Los medicamentos son gravado al cero y no exentos (arely)
	 */
	public static int getTaxProductVta(final Properties ctx, final int productID){
		final MProduct product = new MProduct (ctx,productID,null);

		// Si eres medicamento de PVCE y tiene configurado que no
		// genere impuesto
		int C_Tax_ID = 0;
		if (X_M_Product.PRODUCTCLASS_Drug.equals(product.getProductClass())
				&& !MEXMEMejoras.isCalcImpFact(Env.getCtx())) {
			// se obtiene la tasa de impuesto del producto
			final MTax mTax = MEXMETax.getZeroTax(product.getCtx(), null);// 
			if(mTax!=null && mTax.getC_Tax_ID()>0){
				C_Tax_ID = mTax.getC_Tax_ID();
			}
		}
		return C_Tax_ID;
	}
	
	/**
	 * Listado de impuestos de compra (Temporal hasta proximo desarrollo)
	 * @param ctx: Contexto
	 * @return Listado de impuestos sin importar la categoria del producto
	 */
	public static List<MTax> getImpuestoCompra(final Properties ctx, int clientID) {
		final List<MTax> tax = new ArrayList<MTax>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT * FROM C_Tax WHERE IsActive = 'Y' ");
		if (clientID > 0) {
			sql.append(" AND AD_Client_ID = ")
			.append(clientID);
		}else {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Tax.Table_Name));
		}
		
		sql.append(MEXMETax.sqlValidDate())
		.append(" AND IsDocumentLevel = 'Y' ")
		.append(" AND SOPOType IN (")
		.append(DB.TO_STRING(X_C_Tax.SOPOTYPE_All)).append(",").append(DB.TO_STRING(X_C_Tax.SOPOTYPE_PurchaseAndDirectSalesTax)).append(") ")
		.append(" ORDER BY ValidFrom DESC, IsDefault DESC ");

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rSet = pstmt.executeQuery();
			
			while (rSet.next()){
				tax.add(new MTax(ctx, rSet, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rSet, pstmt);
		}
		return tax;
	}
	
	/********************************************************************************/
	/**
	 * Monto del impuesto calculado
	 */
	private BigDecimal amount = Env.ZERO;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}
	
	
	/**
	 * 	Get All Tax codes (for Params)
	 *	@param ctx context
	 * @return 
	 */
	public static List<MTax> getTax(Properties ctx, BigDecimal rate, String name, String type) {
		List<Object> params = new ArrayList<Object>();
		final String AD_Language = Env.getAD_Language(ctx);
		boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, "AD_Ref_List");
		
		// Create it
		StringBuilder sql = new StringBuilder();

		sql.append(isBaseLanguage?"SELECT r.Name AS SPType, ":"SELECT t.Name AS SPType, ");
		sql.append("  tax.* ");
		sql.append("FROM ");
		sql.append("  C_Tax tax ");
		sql.append(" INNER JOIN AD_Ref_List     r ON tax.sopotype = r.value AND r.ad_reference_id = ? ");//#1
		params.add(X_C_Tax.SOPOTYPE_AD_Reference_ID);
		
		if (!isBaseLanguage) {
			sql.append(" INNER JOIN AD_Ref_List_Trl t ON r.AD_Ref_List_ID = t.AD_Ref_List_ID AND t.AD_Language = ? ");//#2
			params.add(AD_Language);
		}
		
		sql.append(" WHERE tax.AD_Client_ID = ? ");
		params.add(Env.getAD_Client_ID(ctx));
		if(type.equals(SOPOTYPE_All)){
			sql.append(" AND tax.sopotype in (?,?,?) ");
			params.add(SOPOTYPE_All);
			params.add(SOPOTYPE_PurchaseAndDirectSalesTax);
			params.add(SOPOTYPE_SalesByExtensionTax);
		}else {
			sql.append(" AND tax.sopotype=? ");
			params.add(type);
		}
		if (rate != null) {
			sql.append("  AND tax.rate=? ");
			params.add(rate);
		}

		if (StringUtils.isNotBlank(name)) {
			sql.append("  AND upper(tax.name) like ?  ");
			params.add("%" + StringUtils.defaultIfEmpty(name, StringUtils.EMPTY).toUpperCase() + "%");
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "tax"));

		sql.append(" ORDER BY ");
		sql.append("  tax.C_Country_ID, ");
		sql.append("  tax.C_Region_ID, ");
		sql.append("  tax.To_Country_ID, ");
		sql.append("  tax.To_Region_ID ");

		List<MTax> list = new ArrayList<MTax>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MTax mtax = new MTax(ctx, rs, null);
				mtax.setSOPOTypeTrl(rs.getString(1));
				list.add(mtax);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}

	
	
	/**
	 *	Get Account
	 *  @param AcctType see ACCTTYPE_*
	 *  @param as account schema
	 *  @return Account
	 */
	public MTaxAcct getAccount(final MAcctSchema as,final  int m_C_Tax_ID)
	{
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT * ")
		.append(" FROM C_Tax_Acct        ")
		.append(" WHERE C_Tax_ID     = ? ")// #1
		.append(" AND   AD_Client_ID = ? ")// #2
		.append(" AND   C_AcctSchema_ID = ? ")// #3
		.append(" ORDER BY AD_Client_ID DESC ");
		
		MTaxAcct acct = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Tax_ID());
			pstmt.setInt(2, as.getAD_Client_ID());
			pstmt.setInt(3, as.getC_AcctSchema_ID());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				acct = new MTaxAcct(getCtx(), rs, get_TrxName());
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return acct;
	}   //  getAccountBigDecimal
	
	private String sOPOTypeTrl = null;


	public String getSOPOTypeTrl() {
		return sOPOTypeTrl==null?"":sOPOTypeTrl;
	}


	public void setSOPOTypeTrl(String soPOTypeTrl) {
		this.sOPOTypeTrl = soPOTypeTrl;
	}
	
	/**
	 * Verificar referencias
	 * @param tax
	 * @return
	 */
	public static boolean canInactiveTax(MTax tax){
		boolean retValue = true;
		// NUNCA HACER COMMIT
		final Trx trx =  Trx.get("ITx", true);
		try {
			// NO QUITAR EL NOMBRE DE LA TRANSACCION, PARA PODER HACER ROLLBACK DESPUES
			final MTax xtax = new MTax(Env.getCtx(), tax.getC_Tax_ID(), null);
			retValue = xtax.delete(false, trx.getTrxName());
		} catch(Exception e) {
			Trx.rollback(trx, true);	
			retValue = false;
		} finally {
			Trx.rollback(trx, true);//SIEMPRE ROLLBACK POR QUE NO SE BORRA	
		}
		// Si retValue es falso no permitir hacer ningun cambio por que tiene referencias
		// Si retValue es verdadero permitir hacer los cambio ya que no tiene referencias.
		return retValue;
	}
	
	/**
	 * Validar que no se repita una cuenta contable con otros registros de la misma columna
	 * @param ctx
	 * @param key
	 * @param ctaImpuestoTraslado
	 * @param ctaImpuestoTrasladoCobrado
	 * @param ctaImpuestoRetenidoFavor
	 * @param ctaImpuestoAcreditar
	 * @param ctaImpuestoAcreditable
	 * @param ctaImpuestoRetenidoPagar
	 * @param isWithholdingTax
	 * @param type
	 * @param trxName
	 * @return
	 */
	public static String validateAcct(Properties ctx, int key, int ctaImpuestoTraslado, int ctaImpuestoTrasladoCobrado,
			int ctaImpuestoRetenidoFavor, int ctaImpuestoAcreditar, int ctaImpuestoAcreditable
			, int ctaImpuestoRetenidoPagar, final boolean isWithholdingTax, final String type, final String trxName){
		final StringBuilder values = new StringBuilder();
		final List<Object> params = new ArrayList<>();
		final StringBuilder sql = new StringBuilder("SELECT c_tax_acct.* ");
		sql.append(" FROM c_tax_acct ");
		sql.append(" INNER JOIN c_tax ON c_tax.c_tax_id = c_tax_acct.c_tax_id ");
		sql.append(" WHERE c_tax_acct.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MTaxAcct.Table_Name));
		sql.append(" AND c_tax_acct.C_tax_ID <> ? ");
		params.add(key);
		sql.append(" AND c_tax.SOPOType = ?  ");
		params.add(type);
		sql.append(" AND ( ");
		if(X_C_Tax.SOPOTYPE_All.equalsIgnoreCase(type) || X_C_Tax.SOPOTYPE_SalesByExtensionTax.equalsIgnoreCase(type)){
			sql.append("    c_tax_acct.T_Due_Acct = ? ");
			params.add(ctaImpuestoTraslado);
		    sql.append(" OR c_tax_acct.T_Liability_Acct = ? ");
		    params.add(ctaImpuestoTrasladoCobrado);
		    if(isWithholdingTax){
		    	sql.append(" OR c_tax_acct.T_HoldRec_Acct = ? ");	
		    	params.add(ctaImpuestoRetenidoFavor);
		    }
		}
		if(X_C_Tax.SOPOTYPE_All.equalsIgnoreCase(type)){
			sql.append(" OR ");
		}
		if(X_C_Tax.SOPOTYPE_All.equalsIgnoreCase(type) || X_C_Tax.SOPOTYPE_PurchaseAndDirectSalesTax.equalsIgnoreCase(type)){
		    sql.append("    c_tax_acct.T_Credit_Acct = ? ");
		    params.add(ctaImpuestoAcreditar);
		    sql.append(" OR c_tax_acct.T_Receivables_Acct = ? ");
		    params.add(ctaImpuestoAcreditable);
		    if(isWithholdingTax){
		    	sql.append(" OR c_tax_acct.T_HoldLiab_Acct = ? ");
		    	params.add(ctaImpuestoRetenidoPagar);
		    }
		} 	
		sql.append(" ) ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				values.append(Utilerias.getLabel("msj.theAccount")).append(" ");//TODO Etiqueta
				MTaxAcct acct = new MTaxAcct(ctx, rs, trxName);
				if (X_C_Tax.SOPOTYPE_All.equalsIgnoreCase(type) || X_C_Tax.SOPOTYPE_SalesByExtensionTax.equalsIgnoreCase(type)
						&& ctaImpuestoTraslado== acct.getT_Due_Acct()){ 
					values.append(Utilerias.getLabel("label.tax.transfer")).append(" ");// 
				}
				if (X_C_Tax.SOPOTYPE_All.equalsIgnoreCase(type) || X_C_Tax.SOPOTYPE_SalesByExtensionTax.equalsIgnoreCase(type)
						&& ctaImpuestoTrasladoCobrado == acct.getT_Liability_Acct() ){ 
					values.append(Utilerias.getLabel("label.tax.transferCollected")).append(" ");// 
				}
				if (X_C_Tax.SOPOTYPE_All.equalsIgnoreCase(type) || X_C_Tax.SOPOTYPE_SalesByExtensionTax.equalsIgnoreCase(type)
						&& isWithholdingTax && ctaImpuestoRetenidoFavor == acct.getT_HoldRec_Acct() ){ 
					values.append(Utilerias.getLabel("label.tax.retainedFavor")).append(" ");
				}
				if (X_C_Tax.SOPOTYPE_All.equalsIgnoreCase(type) || X_C_Tax.SOPOTYPE_PurchaseAndDirectSalesTax.equalsIgnoreCase(type)
						&& ctaImpuestoAcreditar == acct.getT_Credit_Acct() ){ 
					values.append(Utilerias.getLabel("label.tax.toAccredit")).append(" ");// 
				}
				if (X_C_Tax.SOPOTYPE_All.equalsIgnoreCase(type) || X_C_Tax.SOPOTYPE_PurchaseAndDirectSalesTax.equalsIgnoreCase(type)
						&& ctaImpuestoAcreditable == acct.getT_Receivables_Acct() ){ 
					values.append(Utilerias.getLabel("label.tax.creditable")).append(" ");// 
				}
				if (X_C_Tax.SOPOTYPE_All.equalsIgnoreCase(type) || X_C_Tax.SOPOTYPE_PurchaseAndDirectSalesTax.equalsIgnoreCase(type)
						&& isWithholdingTax && ctaImpuestoRetenidoPagar == acct.getT_HoldLiab_Acct() ){ 
					values.append(Utilerias.getLabel("label.tax.retainedToPay")).append(" ");// 
				}
						
				values.append(Utilerias.getLabel("msj.tax.usedAnother", acct.getC_Tax().getName()));//TODO Etiqueta
			}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return values.toString();
	}
	
	public static MTax getTaxDefault(String customWhere, List <Object> customParams, String trxName){
		StringBuilder whereClause = new StringBuilder(" isDefault = ? ");
		if (customWhere != null && !customWhere.isEmpty()) {
			whereClause.append(whereClause);
		}

		List<Object> params = new ArrayList<Object>();
		params.add("Y");
		if (customParams != null && !customParams.isEmpty()) {
			params.addAll(customParams);
		}
		
		return new Query(Env.getCtx(), Table_Name, whereClause.toString(), trxName).setParameters(params).addAccessLevelSQL(true)
				.setOnlyActiveRecords(true).first();
	}
	
}	//	MTax