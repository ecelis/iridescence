package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.QuickSearchTables;

public class MValidCombination extends X_C_ValidCombination {

	private static CLogger s_log = CLogger.getCLogger(MValidCombination.class);

	public MValidCombination(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private static final long serialVersionUID = 1753940930570026141L;

	public MValidCombination(Properties ctx, int C_ValidCombination_ID, String trxName) {
		super(ctx, C_ValidCombination_ID, trxName);
		if (C_ValidCombination_ID == 0){
			setIsFullyQualified (false);
		}
	}

	/**
	 * Busca la combinacion que cumple con los parametros
	 * 
	 * @param ctx
	 *            Contexto
	 * @param accountId
	 *            Cuenta C_ElementValue_ID
	 * @param orgTrxId
	 *            Organizacion trx
	 * @param partnerId
	 *            Cliente/Proveedor
	 * @param trxName
	 *            Trx
	 * @return Combinacion o null si no encuentra
	 */
	public static MValidCombination getCombination(Properties ctx, int accountId, int orgTrxId, int partnerId, String trxName) {
		List<Object> params = new ArrayList<>();

		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  C_ValidCombination ");
		sql.append("WHERE ");
		sql.append("  IsActive = 'Y' ");
		sql.append("  AND AD_Org_ID = ? ");
		params.add(Env.getAD_Org_ID(ctx));

		if (accountId <= 0) {
			sql.append("  AND Account_ID is null ");
		} else {
			sql.append("  AND Account_ID = ? ");
			params.add(accountId);
		}

		if (orgTrxId <= 0) {
			sql.append("  AND AD_OrgTrx_ID is null ");
		} else {
			sql.append("  AND AD_OrgTrx_ID = ? ");
			params.add(orgTrxId);
		}

		if (partnerId <= 0) {
			sql.append("  AND C_BPartner_ID is null ");
		} else {
			sql.append("  AND C_BPartner_ID = ? ");
			params.add(partnerId);
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MValidCombination retValue = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = new MValidCombination(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "Validating combination ....", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}

	protected boolean afterSave(boolean newRecord, boolean success) {

		if (!success) {
			return success;
		}

		try {
			QuickSearchTables.checkTables(MValidCombination.class, MValidCombination.Table_Name, getC_ValidCombination_ID(), get_TrxName(), p_ctx);

		} catch (Exception ex) {
			log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
		}
		return true;
	}

	/**
	 * Revisa si ha sido usada la combinación NOTA: Revisa gl_journalline,
	 * agregar otra tabla si se requiere
	 * 
	 * @return Si/No ha sido usada
	 */
	public boolean isUsed() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  gl_journalline_id ");
		sql.append("FROM ");
		sql.append("  gl_journalline ");
		sql.append("WHERE ");
		sql.append("  c_validcombination_id = ? ");

		int id = DB.getSQLValue(null, sql.toString(), getC_ValidCombination_ID());

		return id > 0;
	}

	/**
	 * 	Get existing Account or create it 
	 *	@param ctx context
	 *	@param AD_Client_ID
	 *	@param AD_Org_ID
	 *	@param C_AcctSchema_ID
	 *	@param Account_ID
	 *	@param C_SubAcct_ID
	 *	@param M_Product_ID
	 *	@param C_BPartner_ID
	 *	@param AD_OrgTrx_ID
	 *	@param C_LocFrom_ID
	 *	@param C_LocTo_ID
	 *	@param C_SalesRegion_ID
	 *	@param C_Project_ID
	 *	@param C_Campaign_ID
	 *	@param C_Activity_ID
	 *	@param User1_ID
	 *	@param User2_ID
	 *	@param UserElement1_ID
	 *	@param UserElement2_ID
	 *	@return account or null
	 */
	public static MValidCombination getValidCombination (Properties ctx, 
			int AD_Client_ID, int AD_Org_ID, int C_AcctSchema_ID, 
			int Account_ID, int M_Product_ID, int C_BPartner_ID, int AD_OrgTrx_ID){
		return MValidCombination.getValidCombination ( ctx, 
				 AD_Client_ID,  AD_Org_ID,  C_AcctSchema_ID, 
				 Account_ID,  0,
				 M_Product_ID,  C_BPartner_ID,  AD_OrgTrx_ID, 
				 0,  0,  0, 
				 0,  0,  0,
				 0,  0,  0,  0);
	}
	private static MValidCombination getValidCombination (Properties ctx, 
		int AD_Client_ID, int AD_Org_ID, int C_AcctSchema_ID, 
		int Account_ID, int C_SubAcct_ID,
		int M_Product_ID, int C_BPartner_ID, int AD_OrgTrx_ID, 
		int C_LocFrom_ID, int C_LocTo_ID, int C_SalesRegion_ID, 
		int C_Project_ID, int C_Campaign_ID, int C_Activity_ID,
		int User1_ID, int User2_ID, int UserElement1_ID, int UserElement2_ID){
		MValidCombination existingAccount = null;
		//
		StringBuffer info = new StringBuffer();
		StringBuffer sql = new StringBuffer("SELECT * FROM C_ValidCombination "
			//	Mandatory fields
			+ "WHERE AD_Client_ID=?"		//	#1
			+ " AND AD_Org_ID=?"
			+ " AND C_AcctSchema_ID=?"
			+ " AND Account_ID=?");			//	#4
		//	Optional fields
		if (C_SubAcct_ID == 0)
			sql.append(" AND C_SubAcct_ID IS NULL");
		else
			sql.append(" AND C_SubAcct_ID=?");
		if (M_Product_ID == 0)
			sql.append(" AND M_Product_ID IS NULL");
		else
			sql.append(" AND M_Product_ID=?");
		if (C_BPartner_ID == 0)
			sql.append(" AND C_BPartner_ID IS NULL");
		else
			sql.append(" AND C_BPartner_ID=?");
		if (AD_OrgTrx_ID == 0)
			sql.append(" AND AD_OrgTrx_ID IS NULL");
		else
			sql.append(" AND AD_OrgTrx_ID=?");
		if (C_LocFrom_ID == 0)
			sql.append(" AND C_LocFrom_ID IS NULL");
		else
			sql.append(" AND C_LocFrom_ID=?");
		if (C_LocTo_ID == 0)
			sql.append(" AND C_LocTo_ID IS NULL");
		else
			sql.append(" AND C_LocTo_ID=?");
		if (C_SalesRegion_ID == 0)
			sql.append(" AND C_SalesRegion_ID IS NULL");
		else
			sql.append(" AND C_SalesRegion_ID=?");
		if (C_Project_ID == 0)
			sql.append(" AND C_Project_ID IS NULL");
		else
			sql.append(" AND C_Project_ID=?");
		if (C_Campaign_ID == 0)
			sql.append(" AND C_Campaign_ID IS NULL");
		else
			sql.append(" AND C_Campaign_ID=?");
		if (C_Activity_ID == 0)
			sql.append(" AND C_Activity_ID IS NULL");
		else
			sql.append(" AND C_Activity_ID=?");
		if (User1_ID == 0)
			sql.append(" AND User1_ID IS NULL");
		else
			sql.append(" AND User1_ID=?");
		if (User2_ID == 0)
			sql.append(" AND User2_ID IS NULL");
		else
			sql.append(" AND User2_ID=?");
		if (UserElement1_ID == 0)
			sql.append(" AND UserElement1_ID IS NULL");
		else
			sql.append(" AND UserElement1_ID=?");
		if (UserElement2_ID == 0)
			sql.append(" AND UserElement2_ID IS NULL");
		else
			sql.append(" AND UserElement2_ID=?");
		sql.append(" AND IsActive='Y'");
	//	sql.append(" ORDER BY IsFullyQualified DESC");
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			//  --  Mandatory Accounting fields
			int index = 1;
			pstmt.setInt(index++, AD_Client_ID);
			pstmt.setInt(index++, AD_Org_ID);
			info.append("AD_Client_ID=").append(AD_Client_ID).append(",AD_Org_ID=").append(AD_Org_ID);
			//	Schema
			pstmt.setInt(index++, C_AcctSchema_ID);
			info.append(",C_AcctSchema_ID=").append(C_AcctSchema_ID);
			//	Account
			pstmt.setInt(index++, Account_ID);
			info.append(",Account_ID=").append(Account_ID).append(" ");
			
			//	--  Optional Accounting fields
			if (C_SubAcct_ID != 0)
				pstmt.setInt(index++, C_SubAcct_ID);
			if (M_Product_ID != 0)
				pstmt.setInt(index++, M_Product_ID);
			if (C_BPartner_ID != 0)
				pstmt.setInt(index++, C_BPartner_ID);
			if (AD_OrgTrx_ID != 0)
				pstmt.setInt(index++, AD_OrgTrx_ID);
			if (C_LocFrom_ID != 0)
				pstmt.setInt(index++, C_LocFrom_ID);
			if (C_LocTo_ID != 0)
				pstmt.setInt(index++, C_LocTo_ID);
			if (C_SalesRegion_ID != 0)
				pstmt.setInt(index++, C_SalesRegion_ID);
			if (C_Project_ID != 0)
				pstmt.setInt(index++, C_Project_ID);
			if (C_Campaign_ID != 0)
				pstmt.setInt(index++, C_Campaign_ID);
			if (C_Activity_ID != 0)
				pstmt.setInt(index++, C_Activity_ID);
			if (User1_ID != 0)
				pstmt.setInt(index++, User1_ID);
			if (User2_ID != 0)
				pstmt.setInt(index++, User2_ID);
			if (UserElement1_ID != 0)
				pstmt.setInt(index++, UserElement1_ID);
			if (UserElement2_ID != 0)
				pstmt.setInt(index++, UserElement2_ID);
			//
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				existingAccount = new MValidCombination (ctx, rs, null);
			rs.close();
			pstmt.close();
		}
		catch(SQLException e)
		{
			s_log.log(Level.SEVERE, info + "\n" + sql, e);
		}
		//	Existing
		if (existingAccount != null)
			return existingAccount;

		//	New
		MValidCombination newAccount = new MValidCombination (ctx, 0, null);
		newAccount.setClientOrg(AD_Client_ID, AD_Org_ID);
		newAccount.setC_AcctSchema_ID(C_AcctSchema_ID);
		newAccount.setAccount_ID(Account_ID);
		//	--  Optional Accounting fields
		newAccount.setC_SubAcct_ID(C_SubAcct_ID);
		newAccount.setM_Product_ID(M_Product_ID);
		newAccount.setC_BPartner_ID(C_BPartner_ID);
		newAccount.setAD_OrgTrx_ID(AD_OrgTrx_ID);
		newAccount.setC_LocFrom_ID(C_LocFrom_ID);
		newAccount.setC_LocTo_ID(C_LocTo_ID);
		newAccount.setC_SalesRegion_ID(C_SalesRegion_ID);
		newAccount.setC_Project_ID(C_Project_ID);
		newAccount.setC_Campaign_ID(C_Campaign_ID);
		newAccount.setC_Activity_ID(C_Activity_ID);
		newAccount.setUser1_ID(User1_ID);
		newAccount.setUser2_ID(User2_ID);
		newAccount.setUserElement1_ID(UserElement1_ID);
		newAccount.setUserElement2_ID(UserElement2_ID);
		//
		if (!newAccount.save())
		{
			s_log.log(Level.SEVERE, "Could not create new account - " + info);
			return null;
		}
		s_log.fine("New: " + newAccount);
		return newAccount;
	}	//	get
	
	/**	Account Segment				*/
	private MElementValue	m_accountEV = null;
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		setValueDescription();
		return validate();
	}	//	beforeSave
	
	/**
	 * 	Set Value and Description and Fully Qualified Flag for Combination
	 */
	public void setValueDescription()
	{
		StringBuffer combi = new StringBuffer();
		StringBuffer descr = new StringBuffer();
		boolean fullyQualified = true;
		//
		MAcctSchema as = new MAcctSchema(getCtx(), getC_AcctSchema_ID(), get_TrxName());	//	In Trx!
		MAcctSchemaElement[] elements = MAcctSchemaElement.getAcctSchemaElements(as);
		for (int i = 0; i < elements.length; i++)
		{
			if (i > 0)
			{
				combi.append(as.getSeparator());
				descr.append(as.getSeparator());
			}
			MAcctSchemaElement element = elements[i];
			String combiStr = "_";		//	not defined
			String descrStr = "_";

			if (MAcctSchemaElement.ELEMENTTYPE_Organization.equals(element.getElementType()))
			{
				if (getAD_Org_ID() != 0)
				{
					MOrg org = new MOrg(getCtx(), getAD_Org_ID(), get_TrxName());	//	in Trx!
					combiStr = org.getValue();
					descrStr = org.getName();
				}
				else
				{
					combiStr = "*";
					descrStr = "*";
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Account.equals(element.getElementType()))
			{
				if (getAccount_ID() != 0)
				{
					if (m_accountEV == null)
						m_accountEV = new MElementValue(getCtx(), getAccount_ID(), get_TrxName());
					combiStr = m_accountEV.getValue();
					descrStr = m_accountEV.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Account");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_SubAccount.equals(element.getElementType()))
			{
				if (getC_SubAcct_ID() != 0)
				{
					X_C_SubAcct sa = new X_C_SubAcct(getCtx(), getC_SubAcct_ID(), get_TrxName());
					combiStr = sa.getValue();
					descrStr = sa.getName();
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Product.equals(element.getElementType()))
			{
				if (getM_Product_ID() != 0)
				{
					X_M_Product product = new X_M_Product (getCtx(), getM_Product_ID(), get_TrxName());
					combiStr = product.getValue();
					descrStr = product.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Product");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_BPartner.equals(element.getElementType()))
			{
				if (getC_BPartner_ID() != 0)
				{
					X_C_BPartner partner = new X_C_BPartner (getCtx(), getC_BPartner_ID(),get_TrxName());
					combiStr = partner.getValue();
					descrStr = partner.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Business Partner");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_OrgTrx.equals(element.getElementType()))
			{
				if (getAD_OrgTrx_ID() != 0)
				{
					MOrg org = new MOrg(getCtx(), getAD_OrgTrx_ID(), get_TrxName());	// in Trx!
					combiStr = org.getValue();
					descrStr = org.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Trx Org");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_LocationFrom.equals(element.getElementType()))
			{
				if (getC_LocFrom_ID() != 0)
				{
					MLocation loc = new MLocation(getCtx(), getC_LocFrom_ID(), get_TrxName());	//	in Trx!
					combiStr = loc.getPostal();
					descrStr = loc.getCity();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Location From");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_LocationTo.equals(element.getElementType()))
			{
				if (getC_LocTo_ID() != 0)
				{
					MLocation loc = new MLocation(getCtx(), getC_LocFrom_ID(), get_TrxName());	//	in Trx!
					combiStr = loc.getPostal();
					descrStr = loc.getCity();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Location To");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_SalesRegion.equals(element.getElementType()))
			{
				if (getC_SalesRegion_ID() != 0)
				{
					MSalesRegion loc = new MSalesRegion(getCtx(), getC_SalesRegion_ID(), get_TrxName());
					combiStr = loc.getValue();
					descrStr = loc.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: SalesRegion");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Project.equals(element.getElementType()))
			{
				if (getC_Project_ID() != 0)
				{
					X_C_Project project = new X_C_Project (getCtx(), getC_Project_ID(), get_TrxName());
					combiStr = project.getValue();
					descrStr = project.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Project");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Campaign.equals(element.getElementType()))
			{
				if (getC_Campaign_ID() != 0)
				{
					X_C_Campaign campaign = new X_C_Campaign (getCtx(), getC_Campaign_ID(), get_TrxName());
					combiStr = campaign.getValue();
					descrStr = campaign.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Campaign");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_Activity.equals(element.getElementType()))
			{
				if (getC_Activity_ID() != 0)
				{
					X_C_Activity act = new X_C_Activity (getCtx(), getC_Activity_ID(), get_TrxName());
					combiStr = act.getValue();
					descrStr = act.getName();
				}
				else if (element.isMandatory())
				{
					log.warning("Mandatory Element missing: Campaign");
					fullyQualified = false;
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserList1.equals(element.getElementType()))
			{
				if (getUser1_ID() != 0)
				{
					MElementValue ev = new MElementValue(getCtx(), getUser1_ID(), get_TrxName());
					combiStr = ev.getValue();
					descrStr = ev.getName();
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserList2.equals(element.getElementType()))
			{
				if (getUser2_ID() != 0)
				{
					MElementValue ev = new MElementValue(getCtx(), getUser2_ID(), get_TrxName());
					combiStr = ev.getValue();
					descrStr = ev.getName();
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserElement1.equals(element.getElementType()))
			{
				if (getUserElement1_ID() != 0)
				{
				}
			}
			else if (MAcctSchemaElement.ELEMENTTYPE_UserElement2.equals(element.getElementType()))
			{
				if (getUserElement2_ID() != 0)
				{
				}
			}
			combi.append(combiStr);
			descr.append(descrStr);
		}
		//	Set Values
		super.setCombination(combi.toString());
		super.setDescription(descr.toString());
		if (fullyQualified != isFullyQualified())
			setIsFullyQualified(fullyQualified);
		log.fine("Combination=" + getCombination() 
			+ " - " + getDescription()
			+ " - FullyQualified=" + fullyQualified);
	}	//	setValueDescription
	
	
	/**
	 * 	Validate combination
	 *	@return true if valid
	 */
	public boolean validate()
	{
		boolean ok = true;
		//	Validate Sub-Account
		if (getC_SubAcct_ID() != 0)
		{
			X_C_SubAcct sa = new X_C_SubAcct(getCtx(), getC_SubAcct_ID(), get_TrxName());
			if (sa.getC_ElementValue_ID() != getAccount_ID())
			{
				log.saveError("Error", "C_SubAcct.C_ElementValue_ID=" + sa.getC_ElementValue_ID()
					+ "<>Account_ID=" + getAccount_ID());
				ok = false;
			}
		}
		return ok;
	}	//	validate
	
	
	
	/**************************************************************************
	 * Return String representation
	 * @return String
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MAccount=[");
		sb.append(getC_ValidCombination_ID());
		if (getCombination() != null)
			sb.append(",")
				.append(getCombination());
		else
		{
			//	.append(",Client=").append(getAD_Client_ID())
			sb.append(",Schema=").append(getC_AcctSchema_ID())
				.append(",Org=").append(getAD_Org_ID())
				.append(",Acct=").append(getAccount_ID())
				.append(" ");
			if (getC_SubAcct_ID() != 0)
				sb.append(",C_SubAcct_ID=").append(getC_SubAcct_ID());
			if (getM_Product_ID() != 0)
				sb.append(",M_Product_ID=").append(getM_Product_ID());
			if (getC_BPartner_ID() != 0)
				sb.append(",C_BPartner_ID=").append(getC_BPartner_ID());
			if (getAD_OrgTrx_ID() != 0)
				sb.append(",AD_OrgTrx_ID=").append(getAD_OrgTrx_ID());
			if (getC_LocFrom_ID() != 0)
				sb.append(",C_LocFrom_ID=").append(getC_LocFrom_ID());
			if (getC_LocTo_ID() != 0)
				sb.append(",C_LocTo_ID=").append(getC_LocTo_ID());
			if (getC_SalesRegion_ID() != 0)
				sb.append(",C_SalesRegion_ID=").append(getC_SalesRegion_ID());
			if (getC_Project_ID() != 0)
				sb.append(",C_Project_ID=").append(getC_Project_ID());
			if (getC_Campaign_ID() != 0)
				sb.append(",C_Campaign_ID=").append(getC_Campaign_ID());
			if (getC_Activity_ID() != 0)
				sb.append(",C_Activity_ID=").append(getC_Activity_ID());
			if (getUser1_ID() != 0)
				sb.append(",User1_ID=").append(getUser1_ID());
			if (getUser2_ID() != 0)
				sb.append(",User2_ID=").append(getUser2_ID());
			if (getUserElement1_ID() != 0)
				sb.append(",UserElement1_ID=").append(getUserElement1_ID());
			if (getUserElement2_ID() != 0)
				sb.append(",UserElement2_ID=").append(getUserElement2_ID());
		}
		sb.append("]");
		return sb.toString();
	}	//	toString

}
