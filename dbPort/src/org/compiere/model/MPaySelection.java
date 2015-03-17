/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.DynamicModel;
import org.compiere.util.Env;
import org.compiere.util.OptionItem;

/**
 *	AP Payment Selection
 *	
 *  @author Jorg Janke
 *  @version $Id: MPaySelection.java,v 1.4 2006/08/11 02:26:22 mrojas Exp $
 */
public class MPaySelection extends X_C_PaySelection
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8891771060304682761L;

	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param C_PaySelection_ID id
	 */
	public MPaySelection (Properties ctx, int C_PaySelection_ID, String trxName)
	{
		super(ctx, C_PaySelection_ID, trxName);
		if (C_PaySelection_ID == 0)
		{
		//	setC_BankAccount_ID (0);
		//	setName (null);	// @#Date@
		//	setPayDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setTotalAmt (Env.ZERO);
			setIsApproved (false);
			setProcessed (false);
			setProcessing (false);
		}
	}	//	MPaySelection

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MPaySelection(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPaySelection

	/**	Lines						*/
	private MPaySelectionLine[]	m_lines = null;
	/**	Currency of Bank Account	*/
	private int					m_C_Currency_ID = 0;
	
	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return lines
	 */
	public MPaySelectionLine[] getLines(boolean requery)
	{
		if (m_lines != null && !requery)
			return m_lines;
		ArrayList<MPaySelectionLine> list = new ArrayList<MPaySelectionLine>();
		String sql = "SELECT * FROM C_PaySelectionLine WHERE C_PaySelection_ID=? ORDER BY Line";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getC_PaySelection_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MPaySelectionLine(getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
			//expert:
			rs = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getLines", e); 
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
		//
		m_lines = new MPaySelectionLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines
	
	/**
	 * 	Get Currency of Bank Account
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
		if (m_C_Currency_ID == 0)
		{
			String sql = "SELECT C_Currency_ID FROM C_BankAccount " 
				+ "WHERE C_BankAccount_ID=?";
			m_C_Currency_ID = DB.getSQLValue(null, sql, getC_BankAccount_ID());
		}
		return m_C_Currency_ID;
	}	//	getC_Currency_ID
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MPaySelection[");
		sb.append(get_ID()).append(",").append(getName())
			.append("]");
		return sb.toString();
	}	//	toString

	
	//Expert *****Juan
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID == 0) set_Value ("C_AcctSchema_ID", null);
		else 
			set_Value ("C_AcctSchema_ID", new Integer(C_AcctSchema_ID));
	}
	
	public int getC_AcctSchema_ID() 
	{
		Integer ii = (Integer)get_Value("C_AcctSchema_ID");
		if (ii == null) return 0;
		return ii.intValue();
	}
	//Expert*****Juan
	
	/**
	 * @param AD_Client_ID ID del Cliente
	 * @return Lista de Seleccion de Pagos Procesados y Activos
	 */
	public static List<OptionItem> getPaySelection(int AD_Client_ID) {

		String sql = "SELECT C_PaySelection_ID, Name || ' - ' || TotalAmt "
					+"FROM C_PaySelection "
					+"WHERE AD_Client_ID=? AND Processed='Y' AND IsActive='Y' "
					+"ORDER BY PayDate DESC";
		List<OptionItem> lps = new ArrayList<OptionItem>();
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				OptionItem psl = new OptionItem(rs.getString(1), rs
						.getString(2));
				lps.add(psl);
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lps;
	}
	
	/** 
	 * Obtiene Informaciòn de el Pago Seleccionado
	 * @param PaySelectID ID de PaySelectionID
	 * @return  Nombre y Número de Cuenta,ISO Code, Balance Actual de la Cuenta
	 */

	public static List<DynamicModel> loadPaySelectInfo(int C_PaySelectionID, Properties Ctx) {
		
		
		String sql = "SELECT ps.C_BankAccount_ID, b.Name || ' ' || ba.AccountNo AS NAME_ACC," //rsolorzano correccion mantis 06415
				+ " c.ISO_Code, CurrentBalance " 
				+ " FROM C_PaySelection ps"
				+ " INNER JOIN C_BankAccount ba ON (ps.C_BankAccount_ID=ba.C_BankAccount_ID)"
				+ " INNER JOIN C_Bank b ON (ba.C_Bank_ID=b.C_Bank_ID)"
				+ " INNER JOIN C_Currency c ON (ba.C_Currency_ID=c.C_Currency_ID) "
				+ " WHERE ps.C_PaySelection_ID=? AND ps.Processed='Y' AND ba.IsActive='Y'";
		StringBuilder sb=new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(Ctx, sql,MPaySelection.Table_Name,"ps"));
		String sbs=sb.toString();
		List<DynamicModel> l = null;
		l = new ArrayList<DynamicModel>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			pstmt = DB.prepareStatement(sbs, null);
			pstmt.setInt(1, C_PaySelectionID);
			rs=pstmt.executeQuery();
			rsmd = rs.getMetaData();
			while (rs.next()) {
				DynamicModel dynamicmodel = new DynamicModel();
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
					dynamicmodel.setValue(rsmd.getColumnName(i), rs
							.getString(i), rsmd.getColumnClassName(i));
				l.add(dynamicmodel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				// unreported exception
			}
		}
		return l;
	}
	
} // MPaySelection
