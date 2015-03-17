package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MElementValue;
import org.compiere.model.X_I_ElementValue;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 *	Import Accounts from I_ElementValue
 */
public class ImportAccount extends SvrProcess
{
	/**	Client to be imported to		*/
	protected transient int				adClientId = 0;
	/** Default Element					*/
	protected transient int				cElementId = 0;
	/**	Update Default Accounts			*/
	protected transient boolean			updateDefaultAcct = false;
	/** Create New Combination			*/
	protected transient boolean			createNewCombinat = true;

	/**	Delete old Imported				*/
	protected transient boolean			deleteOldImported = false;

	/** Organization to be imported to	*/
	private transient int				adOrgId = 0;

	/** Effective						*/
	protected transient Timestamp		dateValue = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			final String name = para[i].getParameterName();
			if (para[i].getParameter() != null) {
				if ("AD_Client_ID".equals(name)) {
					adClientId = ((BigDecimal)para[i].getParameter()).intValue();
				} else if ("C_Element_ID".equals(name)) {
					cElementId = ((BigDecimal)para[i].getParameter()).intValue();
				} else if ("UpdateDefaultAccounts".equals(name)) {
					updateDefaultAcct = "Y".equals(para[i].getParameter());
				} else if ("CreateNewCombination".equals(name)) {
					createNewCombinat = "Y".equals(para[i].getParameter());
				} else if ("DeleteOldImported".equals(name)) {
					deleteOldImported = "Y".equals(para[i].getParameter());
				} else {
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
				}
			}

		}

		if (dateValue == null) {
			dateValue = DB.getTimestampForOrg(getCtx());
		}
	}	//	prepare


	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		StringBuffer sql = null;
		int no = 0;
		final String clientCheck = " AND AD_Client_ID=" + adClientId;

		//	****	Prepare	****

		//	Delete Old Imported
		if (deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_ElementValue "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(adClientId).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, SysDate),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, SysDate),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = ' ',"
			+ " Processed = 'N', "
			+ " Processing = 'Y', "
			+ " I_IsImported = 'N' "
			+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Reset=" + no);

		//	****	Prepare	****

		//	Set Element
		if (cElementId != 0)
		{
			sql = new StringBuffer ("UPDATE I_ElementValue "
				+ "SET ElementName=(SELECT Name FROM C_Element WHERE C_Element_ID=").append(cElementId).append(") "
				+ "WHERE ElementName IS NULL AND C_Element_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set Element Default=" + no);
		}
		//
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET C_Element_ID = (SELECT C_Element_ID FROM C_Element e"
			+ " WHERE i.ElementName=e.Name AND i.AD_Client_ID=e.AD_Client_ID)"
			+ "WHERE C_Element_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Element=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Element, ' "
			+ "WHERE C_Element_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid Element=" + no);

		//	No Name, Value
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Name, ' "
			+ "WHERE (Value IS NULL OR Name IS NULL)"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid Name=" + no);


		//	Set Column
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET AD_Column_ID = (SELECT AD_Column_ID FROM AD_Column c"
			+ " WHERE upper(i.Default_Account)=upper(c.ColumnName)" //expert sin upper()
			+ " AND c.AD_Table_ID IN (315,266) AND AD_Reference_ID=25) "
			+ "WHERE Default_Account IS NOT NULL AND AD_Column_ID IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Column=" + no);		
		
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Column, ' "
			+ "WHERE AD_Column_ID IS NULL AND Default_Account IS NOT NULL"
			+ " AND UPPER(Default_Account)<>'DEFAULT_ACCT'"		//	ignore default account
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid Column=" + no);

		//	Set Post* Defaults (ignore errors)
		String[] yColumns = new String[] {"PostActual", "PostBudget", "PostStatistical", "PostEncumbrance"};
		for (int i = 0; i < yColumns.length; i++)
		{
			sql = new StringBuffer ("UPDATE I_ElementValue SET ")
				.append(yColumns[i]).append("='Y' WHERE ")
				.append(yColumns[i]).append(" IS NULL OR ")
				.append(yColumns[i]).append(" NOT IN ('Y','N')"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set " + yColumns[i] + " Default=" + no);
		}
		//	Summary
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET IsSummary='N' "
			+ "WHERE IsSummary IS NULL OR IsSummary NOT IN ('Y','N')"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set IsSummary Default=" + no);

		//	Doc Controlled
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET IsDocControlled = CASE WHEN AD_Column_ID IS NOT NULL THEN 'Y' ELSE 'N' END "
			+ "WHERE IsDocControlled IS NULL OR IsDocControlled NOT IN ('Y','N')"
			+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set IsDocumentControlled Default=" + no);

		//	Check Account Type A (E) L M O R
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET AccountType='E' "
			+ "WHERE AccountType IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set AccountType Default=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid AccountType, ' "
			+ "WHERE AccountType NOT IN ('A','E','L','M','O','R')"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid AccountType=" + no);

		//	Check Account Sign (N) C B
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET AccountSign='N' "
			+ "WHERE AccountSign IS NULL"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set AccountSign Default=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid AccountSign, ' "
			+ "WHERE AccountSign NOT IN ('N','C','D')"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid AccountSign=" + no);

		//	No Value
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Key, ' "
			+ "WHERE (Value IS NULL OR Value='')"
			+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid Key=" + no);

		//	****	Update ElementValue from existing
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET C_ElementValue_ID=(SELECT C_ElementValue_ID FROM C_ElementValue ev"
			+ " INNER JOIN C_Element e ON (ev.C_Element_ID=e.C_Element_ID)"
			+ " WHERE i.C_Element_ID=e.C_Element_ID AND i.AD_Client_ID=e.AD_Client_ID"
			+ " AND i.Value=ev.Value) "
			+ "WHERE C_ElementValue_ID IS NULL"
			+ " AND I_IsImported='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Found ElementValue=" + no);

		// set GroupAcct
		sql = new StringBuffer("UPDATE I_ElementValue i "
				+ " SET EXME_GroupAcct_ID = (SELECT EXME_GroupAcct_ID "
				+ " FROM EXME_GroupAcct c "
				+ " WHERE upper(i.groupacct_value)=upper(c.Value) "
				+ " AND c.AD_Client_ID = 0) "
				+ " WHERE groupacct_value IS NOT NULL "
				+ " AND EXME_GroupAcct_ID IS NULL " + "AND I_IsImported<>'Y'")
				.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Column=" + no);

		//
		sql = new StringBuffer(
				"UPDATE I_ElementValue "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Group Acct, ' "
						+ "WHERE EXME_GroupAcct_ID IS NULL AND groupacct_value IS NOT NULL");

		commitEx();

		//	-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;

		//	Go through Records
		sql = new StringBuffer ("SELECT * "
			+ "FROM I_ElementValue "
			+ "WHERE I_IsImported='N'").append(clientCheck)
			.append(" ORDER BY I_ElementValue_ID");
		


		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
		ResultSet rs = null;

		try
		{

			rs = pstmt.executeQuery();
			while (rs.next())
			{
				X_I_ElementValue impEV = new X_I_ElementValue(getCtx(), rs, get_TrxName());
				int C_ElementValue_ID = impEV.getC_ElementValue_ID();
				int I_ElementValue_ID = impEV.getI_ElementValue_ID();

				//	****	Create/Update ElementValue
				if (C_ElementValue_ID == 0)		//	New
				{
					MElementValue ev = new MElementValue(impEV);
					if (ev.save())
					{
						noInsert++;
						impEV.setC_ElementValue_ID(ev.getC_ElementValue_ID());
						impEV.setI_IsImported(true);
						impEV.saveEx();
					}
					else
					{
						sql = new StringBuffer ("UPDATE I_ElementValue i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Insert ElementValue "))
							.append("WHERE I_ElementValue_ID=").append(I_ElementValue_ID);
						DB.executeUpdate(sql.toString(), get_TrxName());
					}
				}
				else							//	Update existing
				{
					MElementValue ev = new MElementValue (getCtx(), C_ElementValue_ID, get_TrxName());
//					if (ev.get_ID() != C_ElementValue_ID)
//					{
//
//					}
					ev.set(impEV);
					if (ev.save())
					{
						noUpdate++;
						impEV.setI_IsImported(true);
						impEV.saveEx();
					}
					else
					{
						sql = new StringBuffer ("UPDATE I_ElementValue i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update ElementValue"))
							.append("WHERE I_ElementValue_ID=").append(I_ElementValue_ID);
						DB.executeUpdate(sql.toString(), get_TrxName());
					}
				}
			}	//	for all I_Product
//			rs.close();
//			pstmt.close();
		}
		catch (SQLException e)
		{
			throw new Exception ("create", e);
		} finally {
			DB.close(rs, pstmt);
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@C_ElementValue_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@C_ElementValue_ID@: @Updated@");

		commitEx();

		//	*****	Set Parent
		sql = new StringBuffer ("UPDATE I_ElementValue i "
			+ "SET ParentElementValue_ID=(SELECT C_ElementValue_ID"
			+ " FROM C_ElementValue ev WHERE i.C_Element_ID=ev.C_Element_ID"
			+ " AND i.ParentValue=ev.Value AND i.AD_Client_ID=ev.AD_Client_ID) "
			+ "WHERE ParentElementValue_ID IS NULL"
			+ " AND I_IsImported='Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Found Parent ElementValue=" + no);
		//
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET I_ErrorMsg=I_ErrorMsg||'Info=ParentNotFound, ' "
			+ "WHERE ParentElementValue_ID IS NULL AND ParentValue IS NOT NULL"
			+ " AND I_IsImported='Y' AND Processed='N'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Not Found Parent ElementValue=" + no);
		//
		sql = new StringBuffer ("SELECT i.ParentElementValue_ID, i.I_ElementValue_ID,"
			+ " e.AD_Tree_ID, i.C_ElementValue_ID, i.Value||'-'||i.Name AS Info "
			+ "FROM I_ElementValue i"
			+ " INNER JOIN C_Element e ON (i.C_Element_ID=e.C_Element_ID) "
			+ "WHERE i.C_ElementValue_ID IS NOT NULL AND e.AD_Tree_ID IS NOT NULL"
			+ " AND i.ParentElementValue_ID IS NOT NULL"
			+ " AND i.I_IsImported='Y' AND Processed='N' AND i.AD_Client_ID=").append(adClientId);
		int noParentUpdate = 0;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			//
			String updateSQL = "UPDATE AD_TreeNode SET Parent_ID=?, SeqNo=? "
				+ "WHERE AD_Tree_ID=? AND Node_ID=?";

			PreparedStatement updateStmt =
					DB.prepareStatement(updateSQL, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE, get_TrxName());

			while (rs.next())
			{
				updateStmt.setInt(1, rs.getInt(1));		//	Parent
				updateStmt.setInt(2, rs.getInt(2));		//	SeqNo (assume sequenec in import is the same)
				updateStmt.setInt(3, rs.getInt(3));		//	Tree
				updateStmt.setInt(4, rs.getInt(4));		//	Node
				try
				{
					no = updateStmt.executeUpdate();
					noParentUpdate += no;
				}
				catch (SQLException ex)
				{
					log.log(Level.SEVERE, "(ParentUpdate)", ex);
					no = 0;
				}

				if (no == 0) {
					log.info("Parent not found for " + rs.getString(5));
				}
			}

		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "(ParentUpdateLoop) " + sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
		}


		addLog (0, null, new BigDecimal (noParentUpdate), "@ParentElementValue_ID@: @Updated@");


		//	Reset Processing Flag
		sql = new StringBuffer ("UPDATE I_ElementValue "
			+ "SET Processing='-'"
			+ "WHERE I_IsImported='Y' AND Processed='N' AND Processing='Y'"
			+ " AND C_ElementValue_ID IS NOT NULL")
			.append(clientCheck);
		if (updateDefaultAcct) {
			sql.append(" AND AD_Column_ID IS NULL");
		}
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Reset Processing Flag=" + no);

		if (updateDefaultAcct) {
			updateDefaults(clientCheck);
		}

		//	Update Description
		sql = new StringBuffer("SELECT * FROM C_ValidCombination vc "
			+ "WHERE EXISTS (SELECT * FROM I_ElementValue i "
				+ "WHERE vc.Account_ID=i.C_ElementValue_ID)");

		//	Done
		sql = new StringBuffer (Constantes.INIT_CAPACITY_ARRAY)
			.append("UPDATE I_ElementValue SET Processing='N', Processed='Y' WHERE I_IsImported='Y' ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Processed=" + no);

		commitEx();

		setParentElement(null);
		setTree(null);

		return "";
	}	//	doIt


	/**************************************************************************
	 * 	Update Default Accounts
	 * 	@param clientCheck client where cluase
	 */
	private void updateDefaults (String clientCheck)
	{
		log.config("CreateNewCombination=" + createNewCombinat);

		//	****	Update Defaults
		StringBuffer sql = new StringBuffer ("SELECT C_AcctSchema_ID FROM C_AcctSchema_Element "
			+ "WHERE C_Element_ID=?").append(clientCheck);

		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
		ResultSet rs = null;

		try
		{
			pstmt.setInt(1, cElementId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				updateDefaultAccounts (rs.getInt(1));
			}


		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		//	Default Account		DEFAULT_ACCT
		sql = new StringBuffer ("UPDATE C_AcctSchema_Element e "
			+ "SET C_ElementValue_ID=(SELECT C_ElementValue_ID FROM I_ElementValue i"
			+ " WHERE e.C_Element_ID=i.C_Element_ID AND i.C_ElementValue_ID IS NOT NULL"
			+ " AND UPPER(i.Default_Account)='DEFAULT_ACCT') "
			+ "WHERE EXISTS (SELECT * FROM I_ElementValue i"
			+ " WHERE e.C_Element_ID=i.C_Element_ID AND i.C_ElementValue_ID IS NOT NULL"
			+ " AND UPPER(i.Default_Account)='DEFAULT_ACCT' "
			+ "	AND i.I_IsImported='Y' AND i.Processing='-')")
			.append(clientCheck);
		int no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@C_AcctSchema_Element_ID@: @Updated@");
	}	//	updateDefaults

	/**
	 * 	Update Default Accounts.
	 *	_Default.xxxx = C_ValidCombination_ID  =>  Account_ID=C_ElementValue_ID
	 * 	@param C_AcctSchema_ID Accounting Schema
	 */
	private void updateDefaultAccounts (int C_AcctSchema_ID)
	{
		log.config("C_AcctSchema_ID=" + C_AcctSchema_ID);

		MAcctSchema as = new MAcctSchema (getCtx(), C_AcctSchema_ID, get_TrxName());
		if (as.getAcctSchemaElement("AC").getC_Element_ID() != cElementId)
		{
			log.log(Level.SEVERE, "C_Element_ID=" + cElementId + " not in AcctSchema=" + as);
			return;
		}

		int[] counts = new int[] {0, 0, 0};

		String sql = "SELECT i.C_ElementValue_ID, t.TableName, c.ColumnName, i.I_ElementValue_ID "
			+ "FROM I_ElementValue i"
			+ " INNER JOIN AD_Column c ON (i.AD_Column_ID=c.AD_Column_ID)"
			+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID) "
			+ "WHERE i.I_IsImported='Y' AND Processing='Y'"
			+ " AND i.C_ElementValue_ID IS NOT NULL AND C_Element_ID=?";

		PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());
		ResultSet rs = null;

		try
		{

			pstmt.setInt(1, cElementId);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int C_ElementValue_ID = rs.getInt(1);
				String TableName = rs.getString(2);
				String ColumnName = rs.getString(3);
				int I_ElementValue_ID = rs.getInt(4);
				//	Update it
				int u = updateDefaultAccount(TableName, ColumnName, C_AcctSchema_ID, C_ElementValue_ID);
				counts[u]++;
				if (u != UPDATE_ERROR)
				{
					sql = "UPDATE I_ElementValue SET Processing='N' "
						+ "WHERE I_ElementValue_ID=" + I_ElementValue_ID;
					int no = DB.executeUpdate(sql, get_TrxName());
					if (no != 1) {
						log.log(Level.SEVERE, "Updated=" + no);
					}
				}
			}

		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		addLog (0, null, new BigDecimal (counts[UPDATE_ERROR]), as.toString() + ": @Errors@");
		addLog (0, null, new BigDecimal (counts[UPDATE_YES]), as.toString() + ": @Updated@");
		addLog (0, null, new BigDecimal (counts[UPDATE_SAME]), as.toString() + ": OK");

	}	//	createDefaultAccounts


	private static final int	UPDATE_ERROR = 0;
	private static final int	UPDATE_YES = 1;
	private static final int	UPDATE_SAME = 2;

	/**
	 * 	Update Default Account.
	 *  This is the sql to delete unused accounts - with the import still in the table(!):
		DELETE C_ElementValue e
		WHERE NOT EXISTS (SELECT * FROM Fact_Acct f WHERE f.Account_ID=e.C_ElementValue_ID)
		 AND NOT EXISTS (SELECT * FROM C_ValidCombination vc WHERE vc.Account_ID=e.C_ElementValue_ID)
		 AND NOT EXISTS (SELECT * FROM I_ElementValue i WHERE i.C_ElementValue_ID=e.C_ElementValue_ID);
	 * 	@param TableName Table Name
	 * 	@param ColumnName Column Name
	 * 	@param C_AcctSchema_ID Account Schema
	 * 	@param C_ElementValue_ID new Account
	 * 	@return UPDATE_* status
	 */
//	private int updateDefaultAccount (String TableName, String ColumnName, int C_AcctSchema_ID, int C_ElementValue_ID)
//	{
//		log.fine(TableName + "." + ColumnName + " - " + C_ElementValue_ID);
//		int retValue = UPDATE_ERROR;
//		StringBuffer sql = new StringBuffer ("SELECT x.")
//			.append(ColumnName).append(",Account_ID FROM ")
//			.append(TableName).append(" x INNER JOIN C_ValidCombination vc ON (x.")
//			.append(ColumnName).append("=vc.C_ValidCombination_ID) ")
//			.append("WHERE x.C_AcctSchema_ID=").append(C_AcctSchema_ID);
//
//		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//		ResultSet rs = null;
//		try
//		{
//
//			rs = pstmt.executeQuery();
//			if (rs.next())
//			{
//				int C_ValidCombination_ID = rs.getInt(1);
//				int Account_ID = rs.getInt(2);
//				//	The current account value is the same
//				if (Account_ID == C_ElementValue_ID)
//				{
//					retValue = UPDATE_SAME;
//					log.fine("Account_ID same as new value");
//				}
//				//	We need to update the Account Value
//				else
//				{
//					if (createNewCombinat)
//					{
//						MAccount acct = MAccount.get(getCtx(), C_ValidCombination_ID);
//						acct.setAccount_ID(C_ElementValue_ID);
//						if (acct.save())
//						{
//							retValue = UPDATE_YES;
//							int newC_ValidCombination_ID = acct.getC_ValidCombination_ID();
//							if (C_ValidCombination_ID != newC_ValidCombination_ID)
//							{
//								sql = new StringBuffer ("UPDATE ").append(TableName)
//									.append(" SET ").append(ColumnName).append("=").append(newC_ValidCombination_ID)
//									.append(" WHERE C_AcctSchema_ID=").append(C_AcctSchema_ID);
//								int no = DB.executeUpdate(sql.toString(), get_TrxName());
//								log.fine("ImportAccount.updateDefaultAccount - #" + no + " - "
//									+ TableName + "." + ColumnName + " - " + C_ElementValue_ID
//									+ " -- " + C_ValidCombination_ID + " -> " + newC_ValidCombination_ID);
//								if (no == 1) {
//									retValue = UPDATE_YES;
//								}
//							}
//						}
//						else {
//							log.log(Level.SEVERE, "Account not saved - " + acct);
//						}
//					}
//					else	//	Replace Combination
//					{
//						//	Only Acct Combination directly
//						sql = new StringBuffer ("UPDATE C_ValidCombination SET Account_ID=")
//							.append(C_ElementValue_ID).append(" WHERE C_ValidCombination_ID=").append(C_ValidCombination_ID);
//						int no = DB.executeUpdate(sql.toString(), get_TrxName());
//						log.fine("ImportAccount.updateDefaultAccount - Replace #" + no + " - "
//								+ "C_ValidCombination_ID=" + C_ValidCombination_ID + ", New Account_ID=" + C_ElementValue_ID);
//						if (no == 1)
//						{
//							retValue = UPDATE_YES;
//							//	Where Acct was used
//							sql = new StringBuffer ("UPDATE C_ValidCombination SET Account_ID=")
//								.append(C_ElementValue_ID).append(" WHERE Account_ID=").append(Account_ID);
//							no = DB.executeUpdate(sql.toString(), get_TrxName());
//							log.fine("ImportAccount.updateDefaultAccount - Replace VC #" + no + " - "
//									+ "Account_ID=" + Account_ID + ", New Account_ID=" + C_ElementValue_ID);
//							sql = new StringBuffer ("UPDATE Fact_Acct SET Account_ID=")
//								.append(C_ElementValue_ID).append(" WHERE Account_ID=").append(Account_ID);
//							no = DB.executeUpdate(sql.toString(), get_TrxName());
//							log.fine("ImportAccount.updateDefaultAccount - Replace Fact #" + no + " - "
//									+ "Account_ID=" + Account_ID + ", New Account_ID=" + C_ElementValue_ID);
//						}
//					}	//	replace combination
//				}	//	need to update
//			}	//	for all default accounts
//			else {
//				log.log(Level.SEVERE, "Account not found " + sql);
//			}
//		}
//		catch (SQLException e)
//		{
//			log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return retValue;
//	}	//	updateDefaultAccount

	/**
	 * Update Default Account. 
	 * This is the sql to delete unused accounts - with the import still in the table(!):
	 * DELETE C_ElementValue e 
	 * WHERE NOT EXISTS (SELECT * FROM Fact_Acct f WHERE f.Account_ID=e.C_ElementValue_ID)
	 * AND NOT EXISTS (SELECT * FROM C_ValidCombination vc WHERE vc.Account_ID=e.C_ElementValue_ID) 
	 * AND NOT EXISTS (SELECT * FROM I_ElementValue i WHERE i.C_ElementValue_ID=e.C_ElementValue_ID);
	 * 
	 * @param TableName
	 *            Table Name
	 * @param ColumnName
	 *            Column Name
	 * @param C_AcctSchema_ID
	 *            Account Schema
	 * @param C_ElementValue_ID
	 *            new Account
	 * @return UPDATE_* status
	 */
	private int updateDefaultAccount(final String TableName
			, final String ColumnName
			, final int C_AcctSchema_ID
			, final int C_ElementValue_ID) {
		
		log.fine(TableName + "." + ColumnName + " - " + C_ElementValue_ID);
		int retValue = UPDATE_ERROR;
		StringBuilder sql = new StringBuilder("SELECT x.").append(ColumnName)
				.append(", Account_ID ")
				.append(" FROM ").append(TableName).append(" x ")
				.append(" INNER JOIN C_ValidCombination vc ON (x.").append(ColumnName).append("=vc.C_ValidCombination_ID) ")
				.append(" WHERE x.C_AcctSchema_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
//	SELECT x.CB_CashTransfer_Acct, Account_ID  FROM C_AcctSchema_Default x  INNER JOIN C_ValidCombination vc ON (x.CB_CashTransfer_Acct=vc.C_ValidCombination_ID)  WHERE x.C_AcctSchema_ID = ? 
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, C_AcctSchema_ID);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				final int C_ValidCombi_ID = rset.getInt(1);
				final int Account_ID = rset.getInt(2);
				// The current account value is the same
				if (Account_ID == C_ElementValue_ID) {
					retValue = UPDATE_SAME;
					log.fine("Account_ID same as new value");
				}
				// We need to update the Account Value
				else {
					if (createNewCombinat) {
						final MAccount acct = MAccount.get(getCtx(),
								C_ValidCombi_ID);
						acct.setAccount_ID(C_ElementValue_ID);
						if (acct.save()) {
							retValue = UPDATE_YES;
							final int new_CValidCombiID = acct
									.getC_ValidCombination_ID();
							if (C_ValidCombi_ID != new_CValidCombiID) {
								sql = new StringBuilder("UPDATE ")
										.append(TableName).append(" SET ")
										.append(ColumnName).append("=")
										.append(new_CValidCombiID)
										.append(" WHERE C_AcctSchema_ID=")
										.append(C_AcctSchema_ID);
								final int num = DB.executeUpdate(sql.toString(),
										get_TrxName());
								log.fine("ImportAccount.updateDefaultAccount - #"
										+ num
										+ " - "
										+ TableName
										+ "."
										+ ColumnName
										+ " - "
										+ C_ElementValue_ID
										+ " -- "
										+ C_ValidCombi_ID
										+ " -> "
										+ new_CValidCombiID);
								if (num == 1) {
									retValue = UPDATE_YES;
								}
							}
						} else {
							log.log(Level.SEVERE, "Account not saved - " + acct);
						}
					} else // Replace Combination
					{
						// Only Acct Combination directly
						sql = new StringBuilder(
								"UPDATE C_ValidCombination SET Account_ID=")
								.append(C_ElementValue_ID)
								.append(" WHERE C_ValidCombination_ID=")
								.append(C_ValidCombi_ID);
						int num = DB.executeUpdate(sql.toString(),
								get_TrxName());
						log.fine("ImportAccount.updateDefaultAccount - Replace #"
								+ num
								+ " - "
								+ "C_ValidCombination_ID="
								+ C_ValidCombi_ID
								+ ", New Account_ID="
								+ C_ElementValue_ID);
						if (num == 1) {
							retValue = UPDATE_YES;
							// Where Acct was used
							sql = new StringBuilder(
									"UPDATE C_ValidCombination SET Account_ID=")
									.append(C_ElementValue_ID)
									.append(" WHERE Account_ID=")
									.append(Account_ID);
							num = DB.executeUpdate(sql.toString(),
									get_TrxName());
							log.fine("ImportAccount.updateDefaultAccount - Replace VC #"
									+ num
									+ " - "
									+ "Account_ID="
									+ Account_ID
									+ ", New Account_ID=" + C_ElementValue_ID);
							sql = new StringBuilder(
									"UPDATE Fact_Acct SET Account_ID=")
									.append(C_ElementValue_ID)
									.append(" WHERE Account_ID=")
									.append(Account_ID);
							num = DB.executeUpdate(sql.toString(),
									get_TrxName());
							log.fine("ImportAccount.updateDefaultAccount - Replace Fact #"
									+ num
									+ " - "
									+ "Account_ID="
									+ Account_ID
									+ ", New Account_ID=" + C_ElementValue_ID);
						}
					} // replace combination
				} // need to update
			} // for all default accounts
			else {
				log.log(Level.SEVERE, "Account not found " + sql);
				
				
					final MAccount acct = MAccount.get(getCtx(),0);//TODO
					acct.setC_AcctSchema_ID(C_AcctSchema_ID);
					acct.setAccount_ID(C_ElementValue_ID);
					if (acct.save()) {
						retValue = UPDATE_YES;
						final int new_CValidCombiID = acct.getC_ValidCombination_ID();
						sql = new StringBuilder("UPDATE ")
						.append(TableName).append(" SET ")
						.append(ColumnName).append("=")
						.append(new_CValidCombiID)
						.append(" WHERE C_AcctSchema_ID=")
						.append(C_AcctSchema_ID);
						final int num = DB.executeUpdate(sql.toString(),
								get_TrxName());
						log.fine("ImportAccount.updateDefaultAccount - #"
								+ num
								+ " - "
								+ TableName
								+ "."
								+ ColumnName
								+ " - "
								+ C_ElementValue_ID
								+ " -- "
								+ new_CValidCombiID
								+ " INSERT ");
						if (num == 1) {
							retValue = UPDATE_YES;
						}
					} else {
						log.log(Level.SEVERE, "Account not saved - " + acct);
					}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return retValue;
	} // updateDefaultAccount
	/**
	 * Actualiza el parent_id en c_ElementValue
	 * de acuerdo al seleccionado en el mantenimiento (Importar Cuentas)
	 * @param trxName
	 */
	public void setParentElement(String trxName){

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT cval.c_elementvalue_id AS cvalid, ival.parentelementvalue_id AS parentid ");
		sql.append(" FROM C_elementvalue cval INNER JOIN i_elementvalue ival ON  ");
		sql.append(" (cval.c_elementvalue_id =ival.c_elementvalue_id and ival.parentelementvalue_id >0) ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final int cval = rs.getInt("cvalid");
				final int parentid = rs.getInt("parentid");

				final MElementValue elementVal = new MElementValue(getCtx(), cval, null) ;
				elementVal.setParentElementValue_ID(parentid);
				elementVal.save();
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "setParentElement (" + sql + ")", e);
		} finally {
			DB.close(rs, pstmt);
		}
	}

	/**
	 * Obtiene los registros en c_elementvalue que han sido creados
	 * con un parent_id != 0
	 * @param trxName
	 */
	public void setTree(String trxName){
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT cval.c_elementvalue_id AS cvalid, ival.parentelementvalue_id AS parentid ,  e.AD_Tree_ID as treeID ");
		sql.append(" FROM C_elementvalue cval INNER JOIN i_elementvalue ival ON  ");
		sql.append(" (cval.c_elementvalue_id =ival.c_elementvalue_id and ival.parentelementvalue_id >0 )  ");
		sql.append(" INNER JOIN C_Element e ON (ival.C_Element_ID=e.C_Element_ID) ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final int cElement = rs.getInt("cvalid");
				final int parentid = rs.getInt("parentid");
				final int treeID = rs.getInt("treeid");
				//Itera la lista y guarda su ad_treenode
				saveTree(cElement,parentid,treeID, null);

			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "setTree (" + sql + ")", e);
		} finally {
			DB.close(rs, pstmt);
		}
	}

	/**
	 * Actualizar los registros en ad_treenode
	 * de acuerdo al parent_Id seleccionado en el mantenimiento (Importar Cuentas)
	 * @param element int - Nodo a Actualizar
	 * @param parent int - Nuevo padre en el arbol
	 * @param treeID int - Id del arbol
	 * @param trxName String - trx
	 */
	public void saveTree(int element, int parent, int treeID, String trxName){
		int no =
				DB.executeUpdate(
						"UPDATE AD_TreeNode SET Parent_ID=?  WHERE AD_Tree_ID=? AND Node_ID=?",
						new Object[]{parent, treeID, element},
						trxName
				);

		log.log(Level.SEVERE, "ImportAccount.saveTree - #" + no + " - ");
	}
}	//	ImportAccount