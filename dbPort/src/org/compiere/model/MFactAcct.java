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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;


/**
 *	Accounting Fact Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MFactAcct.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MFactAcct extends X_Fact_Acct
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	Delete Accounting
	 *	@param AD_Table_ID table
	 *	@param Record_ID record
	 *	@param trxName transaction
	 *	@return number of rows or -1 for error
	 */
	public static int delete (int AD_Table_ID, int Record_ID, String trxName)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE Fact_Acct WHERE AD_Table_ID=").append(AD_Table_ID)
			.append(" AND Record_ID=").append(Record_ID);
		int no = DB.executeUpdate(sb.toString(), trxName);
		if (no == -1)
			s_log.log(Level.SEVERE, "failed: AD_Table_ID=" + AD_Table_ID + ", Record_ID" + Record_ID);
		else
			s_log.fine("delete - AD_Table_ID=" + AD_Table_ID 
				+ ", Record_ID=" + Record_ID + " - #" + no);
		return no;
	}	//	delete

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MFactAcct.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param Fact_Acct_ID id
	 *	@param trxName transaction
	 */
	public MFactAcct (Properties ctx, int Fact_Acct_ID, String trxName)
	{
		super (ctx, Fact_Acct_ID, trxName);
	}	//	MFactAcct

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MFactAcct (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MFactAcct

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MFactAcct[");
		sb.append(get_ID()).append("-Acct=").append(getAccount_ID())
			.append(",Dr=").append(getAmtSourceDr()).append("|").append(getAmtAcctDr())
			.append(",Cr=").append(getAmtSourceCr()).append("|").append(getAmtAcctCr())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Derive MAccount from record
	 *	@return Valid Account Combination
	 */
	public MAccount getMAccount()
	{
		MAccount acct = MAccount.get (getCtx(), getAD_Client_ID(), getAD_Org_ID(),
			getC_AcctSchema_ID(), getAccount_ID(), getC_SubAcct_ID(),
			getM_Product_ID(), getC_BPartner_ID(), getAD_OrgTrx_ID(), 
			getC_LocFrom_ID(), getC_LocTo_ID(), getC_SalesRegion_ID(), 
			getC_Project_ID(), getC_Campaign_ID(), getC_Activity_ID(),
			getUser1_ID(), getUser2_ID(), getUserElement1_ID(), getUserElement2_ID());
		if (acct != null && acct.get_ID() == 0)
			acct.save();
		return acct;
	}	//	getMAccount
	
	/**
	 * Obtiene una lista de registros en Fact_Acct segun los parametros de consulta
	 * @param ctx Contexto 
	 * @param where clausula where de sql
	 * @return list lista de MFactAcct
	 * @author rosy
	 * **/
	public static List<MFactAcct> getMFactAcct(Properties ctx, String where, String trxName){
		ArrayList<MFactAcct> list = new ArrayList<MFactAcct>();
		String sql = "SELECT * FROM Fact_Acct "
			       + "WHERE isActive= 'Y' ";
		
		if(where != null && where != ""){
			sql = sql + where;
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);			
			rs = pstmt.executeQuery();
			while (rs.next()){
				list.add (new MFactAcct(ctx, rs, trxName));
			}
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs,pstmt);
		}
		
		return list;
	}
	
	/**
	 * Obtiene una lista de registros en Fact_Acct segun los parametros de consulta
	 * @param ctx
	 * @param year
	 * @param period
	 * @param adClientId
	 * @param adOrgId
	 * @param trxName
	 * @return
	 * @author twry, basado getMFactAcct
	 */
	public static List<MFactAcct> getMFactAcct(Properties ctx, 
			final MYear year, final MPeriod period, 
			final int adClientId, int adOrgId, String trxName){
		ArrayList<MFactAcct> list = new ArrayList<MFactAcct>();

		//
		StringBuilder sql = new StringBuilder()
		.append(" SELECT * FROM Fact_Acct ")
		.append(" INNER JOIN C_ELEMENTVALUE EV ON EV.C_ELEMENTVALUE_ID = Fact_Acct.ACCOUNT_ID ")
		.append(" AND ( EV.AccountType = 'R' OR EV.AccountType = 'E' ) ")
		.append(" AND ( EV.AccountSign = 'C' OR EV.AccountSign = 'D' ) ") 
		.append(" WHERE Fact_Acct.isActive     = 'Y' ")
		.append(" AND   Fact_Acct.ad_client_id = ").append(adClientId)
		.append(" AND   Fact_Acct.ad_org_id    = ").append(adOrgId)
		.append(" AND   Fact_Acct.postingType  = ").append(DB.TO_STRING(MFactAcct.POSTINGTYPE_Actual));

		if(period==null){
			sql.append(" AND to_char("+DB.truncDate("Fact_Acct.dateacct","year")+",'yyyy') = '" + year.getYear() + "' ");
		} else {
			sql.append(" AND to_char("+DB.truncDate("Fact_Acct.dateacct","year")+",'yyyy') = '" + year.getYear() + "' ");
			sql.append(" AND to_char("+DB.truncDate("Fact_Acct.dateacct","month")+",'mm') <= '" + period.getPeriodNo() + "' ");
		}

		sql.append(" AND Fact_Acct.c_acctschema_id IN (SELECT c_acctschema_id FROM C_AcctSchema WHERE ad_client_id = ")
		.append(adClientId)
		.append(" AND ( Fact_Acct.ad_org_id = 0 OR Fact_Acct.ad_org_id = ")
		.append(adOrgId)
		.append("))");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);			
			rs = pstmt.executeQuery();
			while (rs.next()){
				list.add (new MFactAcct(ctx, rs, trxName));
			}
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}

		return list;
	}
	
	/**
	 * Obtiene el concepto según la tabla y registro
	 * 
	 * @param ctx
	 *            Contexto
	 * @param tableId
	 *            Tabla
	 * @param recordId
	 *            Registro
	 * @param trxName
	 *            Trx
	 * @return Concepto
	 */
	public static String getConcept(Properties ctx, int tableId, int recordId, String trxName) {
		String descripcion = null;
		String sql = null;

		if (X_C_BankStatement.Table_ID == tableId) {
			sql = "select description from C_BankStatement where C_BankStatement_ID = ? ";
		} else if (X_M_InOut.Table_ID == tableId) {
			sql = "select description from M_InOut where M_InOut_ID = ? ";
		} else if (X_C_Invoice.Table_ID == tableId) {
			sql = "select description from C_Invoice where C_Invoice_ID = ? ";
		} else if (X_M_MatchInv.Table_ID == tableId) {
			sql = "select description from C_Invoice where C_Invoice_ID = ? ";
		} else if (X_C_Payment.Table_ID == tableId) {
			sql = "select description from C_Payment where C_Payment_ID = ? ";
		} else if (X_M_Inventory.Table_ID == tableId) {
			sql = "select description from M_Inventory where M_Inventory_ID = ? ";
		} else if (X_M_MatchPO.Table_ID == tableId) {
			sql = "select description from M_MatchPO where M_MatchPO_ID = ? ";
		} else if (X_M_Movement.Table_ID == tableId) {
			sql = "select description from M_Movement where M_Movement_ID = ? ";
		} else if (X_GL_JournalBatch.Table_ID == tableId) {
			sql = "select description from GL_JournalBatch where GL_JournalBatch_ID = ? ";
		} else if (X_GL_Journal.Table_ID == tableId) {
			sql = "select description from GL_Journal where GL_Journal_ID = ? ";
		} else if (X_C_Order.Table_ID == tableId) {
			sql = "select description from C_Order where C_Order_ID = ? ";
		} else if (X_C_AllocationHdr.Table_ID == tableId) {
			sql = "select description from C_AllocationHdr where C_AllocationHdr_ID = ? ";
		} else if (X_C_Cash.Table_ID == tableId) {
			sql = "select name from C_Cash where C_Cash_ID = ? ";
		}

		if (sql != null) {
			descripcion = StringUtils.defaultIfEmpty(DB.getSQLValueString(trxName, sql, recordId), Utilerias.getAppMsg(ctx, "msj.noData"));
		}

		return descripcion;
	}
	
}	//	MFactAcct
