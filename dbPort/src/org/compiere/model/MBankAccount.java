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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.OptionItem;


/**
 *  Bank Account Model
 *
 *  @author Jorg Janke
 *  @version $Id: MBankAccount.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MBankAccount extends X_C_BankAccount
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7986712078069995028L;

	/**
	 * 	Get BankAccount from Cache
	 *	@param ctx context
	 *	@param C_BankAccount_ID id
	 *	@return MBankAccount
	 */
	public static MBankAccount get (Properties ctx, int C_BankAccount_ID)
	{
		Integer key = new Integer (C_BankAccount_ID);
		MBankAccount retValue = (MBankAccount) s_cache.get (key);
		if (retValue != null
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		retValue = new MBankAccount (ctx, C_BankAccount_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get

	/**	Cache						*/
	private static CCache<Integer,MBankAccount>	s_cache
		= new CCache<Integer,MBankAccount>("C_BankAccount", 5);
	
	/**
	 * 	Bank Account Model
	 *	@param ctx context
	 *	@param C_BankAccount_ID bank account
	 *	@param trxName transaction
	 */
	public MBankAccount (Properties ctx, int C_BankAccount_ID, String trxName)
	{
		super (ctx, C_BankAccount_ID, trxName);
		if (C_BankAccount_ID == 0)
		{
			setIsDefault (false);
			setBankAccountType (BANKACCOUNTTYPE_Checking);
			setCurrentBalance (Env.ZERO);
		//	setC_Currency_ID (0);
			setCreditLimit (Env.ZERO);
		//	setC_BankAccount_ID (0);
		}
	}	//	MBankAccount

	/**
	 * 	Bank Account Model
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MBankAccount (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MBankAccount

	/**
	 * 	String representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MBankAccount[")
			.append (get_ID())
			.append("-").append(getAccountNo())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Bank
	 *	@return bank parent
	 */
	public MBank getBank()
	{
		return MBank.get(getCtx(), getC_Bank_ID());
	}	//	getBank
	
	/**
	 * 	Get Bank Name and Account No
	 *	@return Bank/Account
	 */
	public String getName()
	{
		return getBank().getName() + " " + getAccountNo();
	}	//	getName
	
	/**
	 * 	After Save
	 *	@param newRecord new record
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord & success)
			return insert_Accounting("C_BankAccount_Acct", "C_AcctSchema_Default", null);
		return success;
	}	//	afterSave
	
	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return delete_Accounting("C_BankAccount_Acct");
	} // beforeDelete

	/**
	 * Obtiene numero de documento siguiente de cuenta de banco.
	 * @param BankAccountID Id de la cuenta de pago seleccionada.
	 * @param PaymentRule  rol de pago de la cuenta de banco seleccionada.
	 * @param Ctx Contexto.
	 * @return String valor de numero de documento siguiente
	 */

	public static String getCurrentNextBankAccount(int BankAccountID, String PaymentRule,
			Properties Ctx) {
		String res = null;
		String sql = "SELECT CurrentNext "  
					 +"FROM C_BankAccountDoc "
				     +"WHERE C_BankAccount_ID=? AND PaymentRule=? AND IsActive='Y'";
		StringBuilder sb = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(
				Ctx, sql, "C_BankAccountDoc"));
		String sbs = sb.toString();
		try {
			PreparedStatement pstmt = DB.prepareStatement(sbs, null);
			pstmt.setInt(1, BankAccountID);
			pstmt.setString(2, PaymentRule);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	
	/**
	 * Obtiene lista de todos los bancos mas su nùmero de cuenta
	 * @param Ctx Contexto
	 * @return Lista tipo OptionItem
	 * @deprecated use {@link KeyNamePair} {@link #getBankAccounts(Properties)} instead
	 */
	public static final List<OptionItem> getBankAccount(Properties Ctx) {
		String sql = "SELECT C_BankAccount.C_BankAccount_ID, "
				+ "C_Bank.Name || ' ' || C_BankAccount.AccountNo AS Name "
				+ "FROM C_Bank, C_BankAccount, C_Currency "
				+ "WHERE C_Bank.C_Bank_ID=C_BankAccount.C_Bank_ID "
				+ "AND C_BankAccount.C_Currency_ID=C_Currency.C_Currency_ID "
				+ "AND EXISTS (SELECT * FROM C_BankAccountDoc WHERE C_BankAccountDoc.C_BankAccount_ID=C_BankAccount.C_BankAccount_ID)";
		StringBuilder sb = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(
				Ctx, sql, MBankAccount.Table_ID));
		String sbs = sb.toString();

		List<OptionItem> lba = new ArrayList<OptionItem>();

		try {
			PreparedStatement pstmt = DB.prepareStatement(sbs, null);
			ResultSet rs = pstmt.executeQuery();
//			lba.add(new OptionItem("0"," "));//parametrizar
			while (rs.next()) {
				OptionItem oi = new OptionItem(rs.getString(1), rs.getString(2));
				lba.add(oi);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lba;
	}

	/**
	 * Obtiene lista de todos los bancos mas su nùmero de cuenta
	 * @param Ctx Contexto
	 * @return Lista tipo OptionItem
	 */
	public static final List<KeyNamePair> getBankAccounts(Properties ctx) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT C_BankAccount.C_BankAccount_ID, ");
		sql.append("C_Bank.Name || ' ' || C_BankAccount.AccountNo AS Name ");
		sql.append("FROM C_Bank, C_BankAccount, C_Currency ");
		sql.append("WHERE C_Bank.C_Bank_ID=C_BankAccount.C_Bank_ID ");
		sql.append("AND C_BankAccount.C_Currency_ID=C_Currency.C_Currency_ID ");
		sql.append("AND EXISTS (SELECT * FROM C_BankAccountDoc WHERE C_BankAccountDoc.C_BankAccount_ID=C_BankAccount.C_BankAccount_ID)");
		sql.append((MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MBankAccount.Table_ID)));

		return Query.getListKN(sql.toString(), null);
	}

	/**
	 * Obtiene Balace Actual e ISO Code de Cuenta de Banco
	 * @param BankSelected ID de el banco seleccionado
	 * @return Lista tipo OptionItem
	 */

	public static List<OptionItem> balanceCurrency(int BankSelected,
			Properties Ctx) {
		String sql = "SELECT C_BankAccount.c_bankaccount_id, C_BankAccount.CurrentBalance, "
				   + "C_Currency.Iso_Code "
				   + "FROM C_BankAccount, C_Currency "
				   + "WHERE C_BankAccount.c_bankaccount_id="
				   + BankSelected
				   + " AND C_BankAccount.C_Currency_Id=C_Currency.C_Currency_Id";
		StringBuilder sb = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(
				Ctx, sql, MBankAccount.Table_ID));
		String sbs = sb.toString();
		List<OptionItem> ls = new ArrayList<OptionItem>();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = DB.prepareStatement(sbs, null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OptionItem oi = new OptionItem(rs.getString(2), rs.getString(3));
				ls.add(oi);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public static List<KeyNamePair> getKeyNamePairList(Properties ctx, int bankId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  c_bankaccount_id, ");
		sql.append("  accountno ");
		sql.append("FROM ");
		sql.append("  c_bankaccount ");
		sql.append("WHERE ");
		sql.append("  isactive = 'Y' AND ");
		sql.append("  c_bank_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append("  order by accountno ");

		return DB.getKeyNamePairsList(sql.toString(), false, bankId);
	}

	/**
	 * Obtiene lista de todos los bancos mas su nùmero de cuenta
	 * @param Ctx Contexto
	 * @return Lista tipo OptionItem
	 */
	public static final List<KeyNamePair> getBankAccountsReceipt (final Properties ctx) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT C_BankAccount.C_BankAccount_ID ");
		sql.append(" , C_Bank.Name || ' ' || C_BankAccount.AccountNo AS Name ");
		sql.append(" FROM C_Bank ");
		sql.append(" INNER JOIN C_BankAccount ON C_Bank.C_Bank_ID = C_BankAccount.C_Bank_ID ");
		sql.append("                         AND C_BankAccount.IsActive = 'Y' ");
		sql.append(" INNER JOIN C_Currency    ON C_BankAccount.C_Currency_ID = C_Currency.C_Currency_ID ");
		sql.append("                         AND C_Currency.IsActive = 'Y' ");
		sql.append(" WHERE C_Bank.IsActive = 'Y' ");
		sql.append((MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MBankAccount.Table_ID)));
//		sql.append("AND EXISTS (SELECT * FROM C_BankAccountDoc WHERE C_BankAccountDoc.C_BankAccount_ID=C_BankAccount.C_BankAccount_ID)");
		
		return Query.getListKN(sql.toString(), null);
	}
} // MBankAccount
