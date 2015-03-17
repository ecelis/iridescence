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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * 	Bank Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MBank.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class MBank extends X_C_Bank
{
	 /**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MBank.class);

	/**
	 * 	Get MBank from Cache
	 *	@param ctx context
	 *	@param C_Bank_ID id
	 *	@return MBank
	 */
	public static MBank get (Properties ctx, int C_Bank_ID)
	{
		Integer key = new Integer (C_Bank_ID);
		MBank retValue = (MBank)s_cache.get (key);
		if (retValue != null
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		retValue = new MBank (ctx, C_Bank_ID, null);
		if (retValue.get_ID() != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get

	/**	Cache						*/
	private static CCache<Integer,MBank> s_cache = 
		new CCache<Integer,MBank> ("C_Bank", 3);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Bank_ID bank
	 *	@param trxName trx
	 */
	public MBank (Properties ctx, int C_Bank_ID, String trxName)
	{
		super (ctx, C_Bank_ID, trxName);
	}	//	MBank

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MBank (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MBank

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MBank[");
		sb.append (get_ID ()).append ("-").append(getName ()).append ("]");
		return sb.toString ();
	}	//	toString
	
	public static MBank getBankIDName(Properties ctx, String name, String trxName) {
		MBank ret = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("select * from C_Bank where name = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ret = new MBank(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getBankIDName", e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				rs = null;
				pstmt = null;
			} catch (SQLException e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			}

		}
		return ret;
	} //
	
	/**
	 * Bancos por nivel de acceso ordenados por nombre
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Trx
	 * @return Listado de bancos
	 */
	public static List<MBank> getList(Properties ctx, String trxName){
		Query query = new Query(ctx, Table_Name, null, trxName);
		query.setOnlyActiveRecords(true);
		query.addAccessLevelSQL(true);
		query.setOrderBy(" name ");
		
		return query.list();
	}
	
	/**
	 * Bancos por nivel de acceso ordenados por nombre
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Trx
	 * @return Listado de bancos
	 */
	public static List<KeyNamePair> getKeyNamePairList(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  c_bank_id, ");
		sql.append("  name ");
		sql.append("FROM ");
		sql.append("  c_bank ");
		sql.append("WHERE ");
		sql.append("  isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append("  order by name ");

		return DB.getKeyNamePairsList(sql.toString(), false);
	}
	
	private List<KeyNamePair> accounts;
	
	public List<KeyNamePair> getAccounts(boolean requery) {
		if (accounts == null || requery) {
			accounts = MBankAccount.getKeyNamePairList(getCtx(), getC_Bank_ID(), null);
		}

		return accounts;
	}
	
	public MBankAccount getAccount(String trxName) {
		StringBuilder st = new StringBuilder("select * from C_BankAccount where c_bank_id = ? ");
		;
		MBankAccount lstDiv = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			pstmt.setInt(1, getC_Bank_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lstDiv = new MBankAccount(getCtx(), rs, null);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
				pstmt = null;
				rs = null;
			}
		}
		return lstDiv;
	}
	
}	//	MBank
