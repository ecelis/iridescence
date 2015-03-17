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

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.util.*;

/**
 *	Business Partner Group Model 
 *	
 *  @author Jorg Janke
 *  @version $Id: MBPGroup.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MBPGroup extends X_C_BP_Group
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9004505817747249598L;

	/**
	 * 	Get MBPGroup from Cache
	 *	@param ctx context
	 *	@param C_BP_Group_ID id
	 *	@return MBPGroup
	 */
	public static MBPGroup get (Properties ctx, int C_BP_Group_ID, String trxName)
	{
		Integer key = new Integer (C_BP_Group_ID);
		MBPGroup retValue = (MBPGroup) s_cache.get (key);
		if (retValue != null
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		retValue = new MBPGroup (ctx, C_BP_Group_ID, trxName);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**
	 * 	Get MBPGroup from Business Partner
	 *	@param ctx context
	 *	@param C_BPartner_ID business partner id
	 *	@return MBPGroup
	 */
	public static MBPGroup getOfBPartner (Properties ctx, int C_BPartner_ID)
	{
		MBPGroup retValue = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM C_BP_Group g "
			+ "WHERE EXISTS (SELECT * FROM C_BPartner p "
				+ "WHERE p.C_BPartner_ID=? AND p.C_BP_Group_ID=g.C_BP_Group_ID)";
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				retValue = new MBPGroup (ctx, rs, null);
				Integer key = new Integer (retValue.getC_BP_Group_ID());
				if (retValue.get_ID () != 0)
					s_cache.put (key, retValue);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
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
		
		return retValue;
	}	//	getOfBPartner
	
	/**	Cache						*/
	private static CCache<Integer,MBPGroup>	s_cache
		= new CCache<Integer,MBPGroup>("BP_Group", 10);
	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MBPGroup.class);
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_BP_Group_ID id
	 *	@param trxName transaction
	 */
	public MBPGroup (Properties ctx, int C_BP_Group_ID, String trxName)
	{
		super (ctx, C_BP_Group_ID, trxName);
		if (C_BP_Group_ID == 0)
		{
		//	setValue (null);
		//	setName (null);
			setIsConfidentialInfo (false);	// N
			setIsDefault (false);
			setPriorityBase(PRIORITYBASE_Same);
		}	
	}	//	MBPGroup

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MBPGroup (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MBPGroup
	
	
	/**
	 * 	Get Credit Watch Percent
	 *	@return 90 or defined percent
	 */
	public BigDecimal getCreditWatchPercent ()
	{
		//super.getCreditWatchPercent();
		BigDecimal bd = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT CreditWatchPercent FROM C_BP_Group_Cte WHERE C_BP_Group_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "C_BP_Group_Cte"));
		bd = DB.getSQLValueBD(get_TrxName(), sql.toString(), getC_BP_Group_ID());
		if (bd != null)
			return bd;
		return new BigDecimal(90);
	}	//	getCreditWatchPercent

	/**
	 * 	Get Credit Watch Ratio
	 *	@return 0.90 or defined percent
	 */
	public BigDecimal getCreditWatchRatio()
	{
		BigDecimal bd = getCreditWatchPercent();
		if (bd != null)
			return bd.divide(Env.ONEHUNDRED, 2, BigDecimal.ROUND_HALF_UP);
		return new BigDecimal(0.90);
	}	//	getCreditWatchRatio

	
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * 	After Save
	 *	@param newRecord new record
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord & success){
			success = insert_Accounting("C_BP_Group_Acct", "C_AcctSchema_Default", null);
			if(!success)return false;
			success = createBPGroupCte();
		}
		return success;
	}	//	afterSave
	
	
	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return delete_Accounting("C_BP_Group_Acct");
	}	//	beforeDelete
	
	
	
	/**
	 * Obtiene una lista con todos los grupos de business partner
	 * @param Ctx Contexto
	 * @return lista de todos los grupos de business partner
	 */
	public static List<OptionItem> getBPartnerGroup(Properties Ctx) {
		List<OptionItem> ls = new ArrayList<OptionItem>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_BP_Group.C_BP_Group_ID,C_BP_Group.Name,C_BP_Group.IsActive" + " FROM C_BP_Group WHERE ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(Ctx, " ", MBPGroup.Table_Name));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OptionItem oi = new OptionItem(rs.getString(1), rs.getString(2));
				ls.add(oi);
			}
		} catch (SQLException ept) {
			s_log.log(Level.SEVERE, sql.toString(), ept);
		} finally {
			DB.close(rs, pstmt);
		}
		return ls;
	}
	
	
	/**
	 * 
	 */
	public boolean createBPGroupCte(){
		X_C_BP_Group_Cte cte = new X_C_BP_Group_Cte(getCtx(), 0, null);
				
		cte.setC_BP_Group_ID(getC_BP_Group_ID());
		cte.setC_Dunning_ID(super.getC_Dunning_ID());
		cte.setCreditWatchPercent(super.getCreditWatchPercent());
		cte.setM_DiscountSchema_ID(super.getM_DiscountSchema_ID());
		cte.setM_PriceList_ID(super.getM_PriceList_ID());
		cte.setPriceMatchTolerance(super.getPriceMatchTolerance());
		cte.setPO_DiscountSchema_ID(super.getPO_DiscountSchema_ID());
		cte.setPO_PriceList_ID(super.getPO_PriceList_ID());

		return cte.save(get_TrxName());
	}
	
	/**
	 * Metodo que crea un X_C_BP_Group_Cte en el wizard
	 * 
	 * @param AD_Client_id, el id del cliente que se acaba de crear
	 * @param M_Price_List_id, el id de la lista de precios del cliente que se acaba de crear.
	 * @return un boleano con tru si se salvo con exito.
	 * @author Jesus Cantu
	 */
	public boolean createBPGroupCteClient(int AD_Client_id, int M_Price_List_id) {
		X_C_BP_Group_Cte cte = new X_C_BP_Group_Cte(getCtx(), 0, get_TrxName());
		
		// Se coloca el ID del cliente que se esta crerando.
		cte.setAD_Client_ID(AD_Client_id);
		
		cte.setC_BP_Group_ID(getC_BP_Group_ID());
		cte.setC_Dunning_ID(super.getC_Dunning_ID());
		cte.setCreditWatchPercent(super.getCreditWatchPercent());
		cte.setM_DiscountSchema_ID(super.getM_DiscountSchema_ID());

		// Se coloca la lista de precios que se creo para el cliente en curso. Previamente seteada en metodo llamador
		cte.setM_PriceList_ID(M_Price_List_id);
		
		cte.setPriceMatchTolerance(super.getPriceMatchTolerance());
		cte.setPO_DiscountSchema_ID(super.getPO_DiscountSchema_ID());
		cte.setPO_PriceList_ID(super.getPO_PriceList_ID());

		return cte.save();
	}
	
	//'PRICEMATCHTOLERANCE','C_DUNNING_ID','PO_PRICELIST_ID','CREDITWATCHPERCENT','PO_DISCOUNTSCHEMA_ID', 
	//'M_DISCOUNTSCHEMA_ID','M_PRICELIST_ID'
	/**
	 * Get Dunning.
	 * 
	 * @return Dunning Rules for overdue invoices
	 */
	public int getC_Dunning_ID() {
		// super.getC_Dunning_ID();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_Dunning_ID FROM C_BP_Group_Cte WHERE C_BP_Group_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "C_BP_Group_Cte"));
		int val = DB.getSQLValue(get_TrxName(), sql.toString(), getC_BP_Group_ID());
		return val;
	}


	/**
	 * Get Discount Schema.
	 * 
	 * @return Schema to calculate the trade discount percentage
	 */
	public int getM_DiscountSchema_ID() {
		// super.getM_DiscountSchema_ID();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT M_DiscountSchema_ID FROM C_BP_Group_Cte WHERE C_BP_Group_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "C_BP_Group_Cte"));
		int val = DB.getSQLValue(get_TrxName(), sql.toString(), getC_BP_Group_ID());
		return val;
	}

	/**
	 * Get Price List.
	 * 
	 * @return Unique identifier of a Price List
	 */
	public int getM_PriceList_ID() {
		// super.getM_PriceList_ID();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT M_PriceList_ID FROM C_BP_Group_Cte WHERE C_BP_Group_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "C_BP_Group_Cte"));
		int val = DB.getSQLValue(get_TrxName(), sql.toString(), getC_BP_Group_ID());
		return val;
	}

	/**
	 * Get PO Discount Schema.
	 * 
	 * @return Schema to calculate the purchase trade discount percentage
	 */
	public int getPO_DiscountSchema_ID() {
		// super.getPO_DiscountSchema_ID();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT PO_DiscountSchema_ID FROM C_BP_Group_Cte WHERE C_BP_Group_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "C_BP_Group_Cte"));
		int val = DB.getSQLValue(get_TrxName(), sql.toString(), getC_BP_Group_ID());
		return val;
	}

	/**
	 * Get Purchase Pricelist.
	 * 
	 * @return Price List used by this Business Partner
	 */
	public int getPO_PriceList_ID() {
		// super.getPO_PriceList_ID();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT PO_PriceList_ID FROM C_BP_Group_Cte WHERE C_BP_Group_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "C_BP_Group_Cte"));
		int val = DB.getSQLValue(get_TrxName(), sql.toString(), getC_BP_Group_ID());
		return val;
	}

	/**
	 * Get Price Match Tolerance.
	 * 
	 * @return PO-Invoice Match Price Tolerance in percent of the purchase price
	 */
	public BigDecimal getPriceMatchTolerance() {
//		super.getPriceMatchTolerance();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT PriceMatchTolerance FROM C_BP_Group_Cte WHERE C_BP_Group_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "C_BP_Group_Cte"));
		BigDecimal val = DB.getSQLValueBD(get_TrxName(), sql.toString(), getC_BP_Group_ID());
		return val;
	}
	/**
	 * 
	 * @param ctx
	 * @param value
	 * @param trx
	 * @return Retorna el id de el value dado
	 */
	public static int getIdbyValue(Properties ctx, String value, Trx trx){
		int id = 0;
		PreparedStatement pstmt= null;
		String sql = "select * from  C_BP_Group where value = ?";
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setString(1, value);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ()){
				id = rs.getInt(COLUMNNAME_C_BP_Group_ID);
			}
		}catch( SQLException sqle){
			s_log.log (Level.SEVERE, sql, sqle);
		}
		return id;
		
	}

	public boolean insertAccountingClient() {

	     return insert_Accounting_Client("C_BP_Group_Acct", "C_AcctSchema_Default", null);
	}
	
	/**
	 * Listado de grupos de socios
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Trx Name
	 * @return Listado de grupo de socios
	 */
	public static List<KeyNamePair> get(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  c_bp_group_id, ");
		sql.append("  name ");
		sql.append("FROM ");
		sql.append("  c_bp_group ");
		sql.append("WHERE ");
		sql.append("  isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append("ORDER BY name");

		return DB.getKeyNamePairsList(trxName, sql.toString(), false);
	}


	
}	//	MBPGroup
