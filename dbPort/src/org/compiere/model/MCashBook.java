/******************************************************************************
 * The contents of this file are subject to the Compiere License Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc. All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Cash Book Model
 * 
 * @author Jorg Janke
 * @version $Id: MCashBook.java,v 1.2 2006/09/05 23:18:54 taniap Exp $
 */
public class MCashBook extends X_C_CashBook {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Get MCashBook from Cache
	 * @param ctx context
	 * @param C_CashBook_ID id
	 * @return MCashBook
	 */
	public static MCashBook get(Properties ctx, int C_CashBook_ID) {
		Integer key = new Integer(C_CashBook_ID);
		MCashBook retValue = (MCashBook) s_cache.get(key);
		if (retValue != null
		// Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
			&& (Env.getContextAsInt(ctx, "#AD_Session_ID") == Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) { // lama
			return retValue;
		}
		retValue = new MCashBook(ctx, C_CashBook_ID, null);
		if (retValue.get_ID() != 0) {
			s_cache.put(key, retValue);
		}
		return retValue;
	} // get

	/**
	 * Get CashBook for Org and Currency
	 * @param ctx context
	 * @param AD_Org_ID org
	 * @param C_Currency_ID currency
	 * @return cash book or null
	 */
	public static MCashBook get(Properties ctx, int AD_Org_ID, int C_Currency_ID, String trxName) {
		// Try from cache
		Iterator<?> it = s_cache.values().iterator();
		while (it.hasNext()) {
			MCashBook cb = (MCashBook) it.next();
			if (cb.getAD_Org_ID() == AD_Org_ID && cb.getC_Currency_ID() == C_Currency_ID) {
				return cb;
			}
		}

		// Get from DB
		MCashBook retValue = new Query(ctx, Table_Name, " AD_Org_ID=? AND C_Currency_ID=? ", trxName)//
			.setParameters(AD_Org_ID, C_Currency_ID)//
			.setOrderBy(" IsDefault DESC ")//
			.first();
		// String sql = "SELECT * FROM C_CashBook WHERE AD_Org_ID=? AND C_Currency_ID=? ORDER BY IsDefault DESC";
		Integer key = new Integer(retValue.getC_CashBook_ID());
		s_cache.put(key, retValue);
		return retValue;
	} // get

	/** Cache */
	private static CCache<Integer, MCashBook>	s_cache	= new CCache<Integer, MCashBook>("", 20);
	/** Static Logger */
	private static CLogger						s_log	= CLogger.getCLogger(MCashBook.class);

	/**************************************************************************
	 * Standard Constructor
	 * @param ctx context
	 * @param C_CashBook_ID id
	 */
	public MCashBook(Properties ctx, int C_CashBook_ID, String trxName) {
		super(ctx, C_CashBook_ID, trxName);
	} // MCashBook

	/**
	 * Load Constructor
	 * @param ctx context
	 * @param rs result set
	 */
	public MCashBook(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MCashBook

	/**
	 * After Save
	 * @param newRecord new
	 * @param success success
	 * @return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (newRecord) {
			insert_Accounting("C_CashBook_Acct", "C_AcctSchema_Default", null);
		}
		return success;
	} // afterSave

	/**
	 * Before Save
	 * @param newRecord new
	 * @return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		// Liz-validar que no se agreguen cajas con nombres ya existentes
		// Registro encontrado. No puede grabar.

		if (getC_CashBook_ID() == 0 && existeNombre(getCtx(), getName(), null)) {
			s_log.severe("El nombre seleccionado ya existe: "+getName());
			log.saveError("SaveError", Msg.getElement(getCtx(), "Caja.") // gderreza para modificar
				+ " El nombre seleccionado ya existe.");
			return false;
		}
		return true;
	} // beforeSave

	/**
	 * Before Delete
	 * @return true
	 */
	protected boolean beforeDelete() {
		return delete_Accounting("C_Cashbook_Acct");
	} // beforeDelete

	// expert
	public int getId() {
		return getC_CashBook_ID();
	}

	/**
	 * Validar que no se agreguen cajas con nombres ya existentes
	 * @param ctx
	 * @param name
	 * @param trxName
	 * @return
	 */
	public static boolean existeNombre(Properties ctx, String name, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT C_CashBook_ID FROM C_CashBook WHERE name = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_CashBook"));
		return DB.getSQLValue(trxName, sql.toString(), name) > 0;
	}

	// fin expert
	// MCashBook

	/**
	 * Obtenemos el identificador del area de la caja para una caja en especifico
	 * 
	 * @return EXME_AreaCaja_ID
	 */
	public static int getAreaCajaID(int C_CashBook_ID) {
		return DB.getSQLValue(null, "SELECT EXME_AreaCaja_ID FROM C_CashBook WHERE C_CashBook_ID=?",C_CashBook_ID);
	}
	
	/**
	 * Lista de mcash
	 * @param whereClause
	 * @param params
	 * @param trxName
	 * @return
	 */
	public static List<MCashBook> getCashBookLst(String whereClause, List<Object> params, String trxName){
		return new Query(Env.getCtx(), Table_Name, whereClause, trxName)
		.addAccessLevelSQL(true).setParameters(params).list();
	}
	
	/**
	 * Area de caja 
	 * @param whereClause
	 * @param EXMEAreaCajaID
	 * @param trxName
	 * @return
	 */
	public static MEXMEAreaCaja getEXME_AreaCaja(String whereClause, int EXMEAreaCajaID, String trxName){
		return new Query(Env.getCtx(), Table_Name, "C_CashBook_ID=?", trxName)
				.setOnlyActiveRecords(true).addAccessLevelSQL(true).setParameters(EXMEAreaCajaID).first();
	}
	
	private String areaName = "";

	public String getAreaName(){
		final MEXMEAreaCaja area = new MEXMEAreaCaja(getCtx(), getEXME_AreaCaja_ID(), null);

		if(area.getEXME_AreaCaja_ID() > 0){
			areaName =	area.getName();
		}
		return areaName;
	}
}