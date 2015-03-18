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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Journal Line Model
 *
 *	@author Jorg Janke
 *	@version $Id: MJournalLine.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MJournalLine extends X_GL_JournalLine
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MJournalLine.class);

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param GL_JournalLine_ID id
	 *	@param trxName transaction
	 */
	public MJournalLine (Properties ctx, int GL_JournalLine_ID, String trxName)
	{
		super (ctx, GL_JournalLine_ID, trxName);
		if (GL_JournalLine_ID == 0)
		{
		//	setGL_JournalLine_ID (0);		//	PK
		//	setGL_Journal_ID (0);			//	Parent
		//	setC_Currency_ID (0);
		//	setC_ValidCombination_ID (0);
			setLine (0);
			setAmtAcctCr (Env.ZERO);
			setAmtAcctDr (Env.ZERO);
			setAmtSourceCr (Env.ZERO);
			setAmtSourceDr (Env.ZERO);
			setCurrencyRate (Env.ONE);
		//	setC_ConversionType_ID (0);
			setDateAcct (DB.getTimestampForOrg(getCtx()));
			setIsGenerated (true);
		}
	}	//	MJournalLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MJournalLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MJournalLine

	/**
	 * 	Parent Constructor
	 *	@param parent journal
	 */
	public MJournalLine (MJournal parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setGL_Journal_ID(parent.getGL_Journal_ID());
		setC_Currency_ID(parent.getC_Currency_ID());
		setC_ConversionType_ID(parent.getC_ConversionType_ID());
		setDateAcct(parent.getDateAcct());

	}	//	MJournalLine

	/**	Currency Precision		*/
	private int					m_precision = 2;
	/**	Account Combination		*/
	private MAccount		 	m_account = null;
	/** Account Element			*/
	private MElementValue		m_accountElement = null;

	/**
	 * 	Set Currency Info
	 *	@param C_Currency_ID currenct
	 *	@param C_ConversionType_ID type
	 *	@param CurrencyRate rate
	 */
	public void setCurrency (int C_Currency_ID, int C_ConversionType_ID, BigDecimal CurrencyRate)
	{
		setC_Currency_ID(C_Currency_ID);
		if (C_ConversionType_ID != 0)
			setC_ConversionType_ID(C_ConversionType_ID);
		if (CurrencyRate != null && CurrencyRate.signum() == 0)
			setCurrencyRate(CurrencyRate);
	}	//	setCurrency

	/**
	 * 	Set C_Currency_ID and precision
	 *	@param C_Currency_ID currency
	 */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID == 0)
			return;
		super.setC_Currency_ID (C_Currency_ID);
		m_precision = MCurrency.getStdPrecision(getCtx(), C_Currency_ID);
	}	//	setC_Currency_ID

	/**
	 * 	Get Currency Precision
	 *	@return precision
	 */
	public int getPrecision()
	{
		return m_precision;
	}	//	getPrecision

	/**
	 * 	Set Currency Rate
	 *	@param CurrencyRate check for null (->one)
	 */
	public void setCurrencyRate (BigDecimal CurrencyRate)
	{
		if (CurrencyRate == null)
		{
			log.warning("was NULL - set to 1");
			super.setCurrencyRate (Env.ONE);
		}
		else if (CurrencyRate.signum() < 0)
		{
			log.warning("negative - " + CurrencyRate + " - set to 1");
			super.setCurrencyRate (Env.ONE);
		}
		else
			super.setCurrencyRate (CurrencyRate);
	}	//	setCurrencyRate

	/**
	 * 	Set Accounted Amounts only if not 0.
	 * 	Amounts overwritten in beforeSave - set conversion rate
	 *	@param AmtAcctDr Dr
	 *	@param AmtAcctCr Cr
	 */
	public void setAmtAcct (BigDecimal AmtAcctDr, BigDecimal AmtAcctCr)
	{
		//	setConversion
		double rateDR = 0;
		if (AmtAcctDr != null && AmtAcctDr.signum() != 0)
		{
			rateDR = AmtAcctDr.doubleValue() / getAmtSourceDr().doubleValue();
			super.setAmtAcctDr(AmtAcctDr);
		}
		double rateCR = 0;
		if (AmtAcctCr != null && AmtAcctCr.signum() != 0)
		{
			rateCR = AmtAcctCr.doubleValue() / getAmtSourceCr().doubleValue();
			super.setAmtAcctCr(AmtAcctCr);
		}
		if (rateDR != 0 && rateCR != 0 && rateDR != rateCR)
		{
			log.warning("Rates Different DR=" + rateDR + "(used) <> CR=" + rateCR + "(ignored)");
			rateCR = 0;
		}
		if (rateDR < 0 || Double.isInfinite(rateDR) || Double.isNaN(rateDR))
		{
			log.warning("DR Rate ignored - " + rateDR);
			return;
		}
		if (rateCR < 0 || Double.isInfinite(rateCR) || Double.isNaN(rateCR))
		{
			log.warning("CR Rate ignored - " + rateCR);
			return;
		}

		if (rateDR != 0)
			setCurrencyRate(new BigDecimal(rateDR));
		if (rateCR != 0)
			setCurrencyRate(new BigDecimal(rateCR));
	}	//	setAmtAcct


	/**
	 * 	Set C_ValidCombination_ID
	 *	@param C_ValidCombination_ID id
	 */
	public void setC_ValidCombination_ID (int C_ValidCombination_ID)
	{
		super.setC_ValidCombination_ID (C_ValidCombination_ID);
		m_account = null;
		m_accountElement = null;
	}	//	setC_ValidCombination_ID

	/**
	 * 	Set C_ValidCombination_ID
	 *	@param acct account
	 */
	public void setC_ValidCombination_ID (MAccount acct)
	{
		if (acct == null)
			throw new IllegalArgumentException("Account is null");
		super.setC_ValidCombination_ID (acct.getC_ValidCombination_ID());
		m_account = acct;
		m_accountElement = null;
	}	//	setC_ValidCombination_ID

	/**
	 * 	Get Account (Valid Combination)
	 *	@return combination or null
	 */
	public MAccount getAccount()
	{
		if (m_account == null && getC_ValidCombination_ID() != 0)
			m_account = new MAccount (getCtx(), getC_ValidCombination_ID(), get_TrxName());
		return m_account;
	}	//	getValidCombination
	
	public String getAccountName() {
		return getAccountElementValue() != null ? getAccountElementValue().getName() : StringUtils.EMPTY;
	}
	
	private MBPartner partner;

	public MBPartner getPartner() {
		if (partner == null && getC_BPartner_ID() > 0) {
			partner = new MBPartner(getCtx(), getC_BPartner_ID(), null);
		}
		return partner;
	}
	
	private String currency;

	public String getCurrency() {
		if (StringUtils.isBlank(currency)) {
			currency = DB.getSQLValueString(null, "select iso_code from c_currency where c_currency_id = ?", getC_Currency_ID());
		}
		return currency;
	}

	/**
	 * 	Get Natural Account Element Value
	 *	@return account
	 */
	public MElementValue getAccountElementValue()
	{
		if (m_accountElement == null)
		{
			MAccount vc = getAccount();
			if (vc != null && vc.getAccount_ID() != 0)
				m_accountElement = new MElementValue (getCtx(), vc.getAccount_ID(), get_TrxName());
		}
		return m_accountElement;
	}	//	getAccountElement

	/**
	 * 	Is it posting to a Control Acct
	 *	@return true if control acct
	 */
	public boolean isDocControlled()
	{
		MElementValue acct = getAccountElementValue();
		if (acct == null)
		{
			log.warning ("Account not found for C_ValidCombination_ID=" + getC_ValidCombination_ID());
			return false;
		}
		return acct.isDocControlled();
	}	//	isDocControlled


	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Set Line Org to Acct Org
		if (newRecord
			|| is_ValueChanged("C_ValidCombination_ID")
			|| is_ValueChanged("AD_Org_ID"))
			setAD_Org_ID(getAccount().getAD_Org_ID());
		return true;
	}	//	beforeSave

	/**
	 * 	After Save.
	 * 	Update Journal/Batch Total
	 *	@param newRecord true if new record
	 *	@param success true if success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		return updateJournalTotal();
	}	//	afterSave


	/**
	 * 	After Delete
	 *	@param success true if deleted
	 *	@return true if success
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;
		return updateJournalTotal();
	}	//	afterDelete


	/**
	 * 	Update Journal and Batch Total
	 *	@return true if success
	 */
	private boolean updateJournalTotal()
	{
		//	Update Journal Total
		String sql = "UPDATE GL_Journal j"
			+ " SET (TotalDr, TotalCr) = (SELECT COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0)"
				+ " FROM GL_JournalLine jl WHERE jl.IsActive='Y' AND j.GL_Journal_ID=jl.GL_Journal_ID) "
			+ "WHERE GL_Journal_ID=" + getGL_Journal_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1)
			log.warning("afterSave - Update Journal #" + no);

		//	Update Batch Total
		sql = "UPDATE GL_JournalBatch jb"
			+ " SET (TotalDr, TotalCr) = (SELECT COALESCE(SUM(TotalDr),0), COALESCE(SUM(TotalCr),0)"
				+ " FROM GL_Journal j WHERE jb.GL_JournalBatch_ID=j.GL_JournalBatch_ID) "
			+ "WHERE GL_JournalBatch_ID="
				+ "(SELECT DISTINCT GL_JournalBatch_ID FROM GL_Journal WHERE GL_Journal_ID="
				+ getGL_Journal_ID() + ")";
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1)
			log.warning("Update Batch #" + no);
		return true;//no == 1;
	}	//	updateJournalTotal

	/**
	 * Metodo para insertar registros en JournalLine invirtiendo cargos por creditos desde EXME_T_Cierre
	 * @param cierre Registro de MEXMETCierre desde donde se obtienen los campos
	 * @return exito si la copia fue exitosa
	 *
	 * **/
	public static boolean copiaCierre(Properties ctx, MEXMETCierre cierre, MJournal journal, String trxName){
		//buscar el c_validCombination segun indice de cierre
		MJournalLine journalLine = new MJournalLine(ctx, 0, trxName);
		//journalLine.setDescription(Description);
		try{
		MAccount combinacion = MAccount.get(ctx,
											cierre.getAD_Client_ID(),
											cierre.getAD_Org_ID(),
											cierre.getC_AcctSchema_ID(),
											cierre.getAccount_ID(),
											cierre.getC_SubAcct_ID(),
											cierre.getM_Product_ID(),
											cierre.getC_BPartner_ID(),
											cierre.getAD_OrgTrx_ID(),
											cierre.getC_LocFrom_ID(),
											cierre.getC_LocTo_ID(),
											cierre.getC_SalesRegion_ID(),
											cierre.getC_Project_ID(),
											cierre.getC_Campaign_ID(),
											cierre.getC_Activity_ID(),
											cierre.getUser1_ID(),
											cierre.getUser2_ID(),
											cierre.getUserElement1_ID(),
											cierre.getUserElement2_ID());

		journalLine.setC_ValidCombination_ID(combinacion.getC_ValidCombination_ID()); //Buscar esta combinacion con el indice de cierre
		journalLine.setGL_Journal_ID(journal.getGL_Journal_ID());    //El creado para el cierre
		journalLine.setDateAcct(cierre.getDateAcct());
		journalLine.setAmtAcctCr(cierre.getAmtAcctDr()); //invertidos
		journalLine.setAmtAcctDr(cierre.getAmtAcctCr()); //invertidos
		journalLine.save(trxName);

		}catch(Exception e){
			s_log.log(Level.SEVERE, "No se pudo guardar GL_JournalLine" + e);
			return false;
		}

		return true;
	}

	public static List<MJournalLine> getLines(Properties ctx, int gl_Journal_ID, String trxName) {
		List<MJournalLine> lstLines = new ArrayList<MJournalLine>();
		StringBuilder sql =
				new StringBuilder("SELECT * FROM GL_JournalLine WHERE IsActive = 'Y' AND GL_Journal_ID = ? order by line asc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MJournalLine line = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, gl_Journal_ID);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				line = new MJournalLine(ctx, rs, trxName);
				lstLines.add(line);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "SQL : " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lstLines;
	}

}	//	MJournalLine