/**
 * 
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MElementValue;
import org.compiere.model.X_I_ElementValue;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Import Accounts from I_ElementValue
 */
public class ImportElecAccount extends ImportAccount {

	private static final int UPDATE_ERROR = 0;
	private static final int UPDATE_YES = 1;
	private static final int UPDATE_SAME = 2;

//	/** Client to be imported to */
//	private transient int adClientId = 0;
//	/** Default Element */
//	private transient int cElementId = 0;
//	/** Update Default Accounts */
//	private transient boolean updateDefaultAcct = false;
//	/** Create New Combination */
//	private transient boolean createNewCombinat = true;
//	/** Delete old Imported */
//	private transient boolean   deleteOldImported = false;
//	/** Effective */
//	private transient Timestamp dateValue = null;
	/** Cliente */
	private transient String clientCheck = null;
	/** Table a actualizar */
	private transient final String UPDATE_I_ElementValue = " UPDATE I_ElementValue ";
	/** Test */
	public void setParamsTest(final Properties ctx, final int padClientId
			, final int pcElementId
			, final boolean pupdateDefaultAcct
			, final boolean pcreateNewCombinat
			, final boolean pdeleteOldImported){
		setCtx(ctx);
		adClientId = padClientId;
		cElementId = pcElementId;
		updateDefaultAcct = pupdateDefaultAcct;
		createNewCombinat = pcreateNewCombinat;
		deleteOldImported = pdeleteOldImported;
		if (dateValue == null) {
			dateValue = DB.convert(getCtx(),
					new Timestamp(System.currentTimeMillis()));
		}

		clientCheck = " AND AD_Client_ID = " + adClientId;
	}
	/** Test */
	public String doItTest() throws java.lang.Exception {
		return doIt();
	}
	
	/**
	 * Prepare - e.g., get Parameters.
	 */
	@Override
	protected void prepare() {
		final ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			final String name = para[i].getParameterName();
			if (para[i].getParameter() != null) {
				if ("AD_Client_ID".equals(name)) {
					adClientId = ((BigDecimal) para[i].getParameter())
							.intValue();
				} else if ("C_Element_ID".equals(name)) {
					cElementId = ((BigDecimal) para[i].getParameter())
							.intValue();
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
			dateValue = DB.convert(getCtx(),
					new Timestamp(System.currentTimeMillis()));
		}

		clientCheck = " AND AD_Client_ID = " + adClientId;	
	} // prepare

	/**
	 * Perform process.
	 * 
	 * @return Message
	 * @throws Exception
	 */
	@Override
	protected String doIt() throws java.lang.Exception {
		MAcctSchema[] mAcctSchema = MAcctSchema.getClientAcctSchema(getCtx(), adClientId);
		if(mAcctSchema!=null && mAcctSchema[0] !=null && mAcctSchema[0].isImportElecAcct()){

			// **** Prepare ****
			prepareIt();// delete old / Set Client, Org, IsActive, Created/Updated
			// asignar el elemento (Encabezado)
			setElement();// Set Element
			// Set Column
			setColumn();
			// Set Post* Defaults (ignore errors)
			setPosts();
			// Summary
			setSummary();
			// Doc Controlled (Y en cuentas default)
			setDocControlled();
			// Check Account Type A (E) L M O R
			setAccountType();
			// Check Account Sign (N) C B
			setAccountSign();
			// No Value
			setErrorNoValue();
			// Group
			setGroupAcct();
			// Validar
			setErrorNoLevelCorrect();
			// **** Update ElementValue from existing
			updateElementValueFromExisting();
			commitEx();

			importAccount(getCtx(), get_TrxName());
			commitEx();// Importado el catalogo de cuentas

			updateParentElementValueID(get_TrxName());
			updateTreeNode(adClientId, get_TrxName());
			resetProcessingFlag(get_TrxName());

			if (updateDefaultAcct) {
				updateDefaults();
			}

			updateDone(get_TrxName());
			commitEx();

			setParentElement(null);// TODO trxName
			setTree(null);// TODO trxName
			return "";

		} else {
			return super.doIt();
		}
	}
	
	/**
	 *  No Value
	 * @return
	 */
	private boolean setErrorNoValue(){
		// No Value
		final StringBuilder sql = new StringBuilder(UPDATE_I_ElementValue)
		.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Key, ' ")
		.append(" WHERE (Value IS NULL OR Value='')")
		.append(" AND I_IsImported<>'Y'")
		.append(clientCheck);
		final int num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Invalid Key=" + num);
		return true;
	}

	/**
	 * Update ElementValue from existing
	 * @return
	 */
	private boolean updateElementValueFromExisting(){
		// **** Update ElementValue from existing
		final StringBuilder sql = new StringBuilder(UPDATE_I_ElementValue).append(" i ")
				.append(" SET C_ElementValue_ID=(SELECT C_ElementValue_ID ")
				.append("                        FROM C_ElementValue ev ")
				.append("                        INNER JOIN C_Element e ON (ev.C_Element_ID=e.C_Element_ID) ")
				.append("                        WHERE i.C_Element_ID = e.C_Element_ID ")
				.append("                        AND   i.AD_Client_ID = e.AD_Client_ID")
				.append("                        AND   i.Value = ev.Value) ")
				.append(" WHERE C_ElementValue_ID IS NULL")
				.append(" AND I_IsImported='N'")
				.append(clientCheck);
		final int num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Found ElementValue=" + num);
		return true;
	}

	/**
	 * prepareIt
	 */
	private void prepareIt() {
		StringBuilder sql = null;
		int num = 0;

		// **** Prepare ****

		// Delete Old Imported
		if (deleteOldImported) {
			sql = new StringBuilder("DELETE I_ElementValue ")
			.append(" WHERE I_IsImported='Y' ")
			.append(clientCheck);
			num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + num);
		}

		// Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET AD_Client_ID = COALESCE (AD_Client_ID, ")
				.append(adClientId).append("),")
				.append(" AD_Org_ID  = COALESCE (AD_Org_ID, 0),")
				.append(" IsActive   = COALESCE (IsActive, 'Y'),")
				.append(" Created    = COALESCE (Created, SysDate),")
				.append(" CreatedBy  = COALESCE (CreatedBy, 0), ")
				.append(" Updated    = COALESCE (Updated, SysDate),")
				.append(" UpdatedBy  = COALESCE (UpdatedBy, 0),")
				.append(" I_ErrorMsg = ' ',").append(" Processed = 'N', ")
				.append(" Processing = 'Y', ").append(" I_IsImported = 'N' ")
				.append(" WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Reset=" + num);
	}

	/**
	 * setElement
	 */
	private void setElement() {
		StringBuilder sql = null;
		int num = 0;

		// **** Prepare ****
		// Si no existe el elemento es creado
		// Set Element
		if (cElementId != 0) {
			sql = new StringBuilder(UPDATE_I_ElementValue)
					.append(" SET ElementName = (SELECT Name FROM C_Element WHERE C_Element_ID= ? ) ")
					.append(" WHERE ElementName IS NULL ")
					.append(" AND C_Element_ID  IS NULL ")
					.append(" AND I_IsImported<>'Y'")
					.append(clientCheck);
			num = DB.executeUpdate(sql.toString(), cElementId, get_TrxName());
			log.fine("Set Element Default=" + num);
		}
		//
		sql = new StringBuilder(UPDATE_I_ElementValue).append(" i ")
				.append(" SET C_Element_ID = (SELECT C_Element_ID ")
				.append("                     FROM  C_Element e ")
				.append("                     WHERE i.ElementName=e.Name ")
				.append("                     AND   i.AD_Client_ID=e.AD_Client_ID ) ")
				.append(" WHERE C_Element_ID IS NULL")
				.append(" AND I_IsImported<>'Y'")
				.append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Element=" + num);
		//
		sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Element, ' ")
				.append(" WHERE C_Element_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Invalid Element=" + num);

		// No Name, Value
		sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Name, ' ")
				.append(" WHERE (Value IS NULL OR Name IS NULL)")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Invalid Name=" + num);
	}

	/**
	 * Actualizar la columna cuando es predeterminada
	 */
	private void setColumn() {
		StringBuilder sql = null;
		int num = 0;

		// Set Column
		sql = new StringBuilder(UPDATE_I_ElementValue).append(" i ")
				.append(" SET AD_Column_ID = (SELECT AD_Column_ID ")
				.append("                     FROM AD_Column c ")
				.append("                     WHERE upper(i.Default_Account)=upper(c.ColumnName) ")
				.append("                     AND c.AD_Table_ID IN (315,266) ")
				.append("                     AND AD_Reference_ID=25) ")
				.append(" WHERE Default_Account IS NOT NULL ")
				.append(" AND AD_Column_ID IS NULL")
				.append(" AND I_IsImported<>'Y'")
				.append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Column=" + num);
		/*
		 * Las tablas 266 "C_AcctSchema_GL" 315 "C_AcctSchema_Default"
		 * Referencia 25 "Account"
		 */
		//
		sql = new StringBuilder(UPDATE_I_ElementValue)
		.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Column, ' ")
		.append("WHERE AD_Column_ID IS NULL AND Default_Account IS NOT NULL")
		.append(" AND UPPER(Default_Account)<>'DEFAULT_ACCT'") // ignore
		// default
		// account
		.append(" AND I_IsImported<>'Y'").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Invalid Column=" + num);
	}

	/**
	 * Set Post* Defaults (ignore errors)
	 * @return
	 */
	private boolean setPosts(){
		StringBuilder sql = null;
		int num = 0;
		// Set Post* Defaults (ignore errors)
		final String[] yColumns = new String[] { "PostActual", "PostBudget",
				"PostStatistical", "PostEncumbrance" };
		for (int i = 0; i < yColumns.length; i++) {
			sql = new StringBuilder(UPDATE_I_ElementValue).append(" SET ")
					.append(yColumns[i]).append("='Y' WHERE ")
					.append(yColumns[i]).append(" IS NULL OR ")
					.append(yColumns[i]).append(" NOT IN ('Y','N')")
					.append(" AND I_IsImported<>'Y'").append(clientCheck);
			num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set " + yColumns[i] + " Default=" + num);
		}
		return true;
	}

	/**
	 * Summary
	 * @return
	 */
	private boolean setSummary(){
		int num = 0;
		// Summary
		final StringBuilder sql = new StringBuilder(UPDATE_I_ElementValue)
		.append(" SET IsSummary='N' ")
		.append(" WHERE IsSummary IS NULL OR IsSummary NOT IN ('Y','N')")
		.append(" AND I_IsImported<>'Y'")
		.append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set IsSummary Default=" + num);
		return true;
	}
	
	/**
	 * Doc Controlled (Y en cuentas default)
	 * @return
	 */
	private boolean setDocControlled(){
		int num = 0;
		// Doc Controlled (Y en cuentas default)
		final StringBuilder sql = new StringBuilder(UPDATE_I_ElementValue)
		.append(" SET IsDocControlled = CASE WHEN AD_Column_ID IS NOT NULL THEN 'Y' ELSE 'N' END ")
		.append(" WHERE IsDocControlled IS NULL OR IsDocControlled NOT IN ('Y','N')")
		.append(" AND I_IsImported='N'")
		.append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set IsDocumentControlled Default=" + num);
		return true;
	}

	/**
	 * importAccount
	 * @param ctx
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	private boolean importAccount(final Properties ctx, final String trxName)
			throws Exception {
		StringBuilder sql = null;
		int num = 0;

		// -------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;

		final StringBuilder sqlErrorInsert = new StringBuilder
				(UPDATE_I_ElementValue).append(" i ")
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
				.append(DB.TO_STRING("Insert ElementValue "))
				.append(" WHERE I_ElementValue_ID = ? ");

		final StringBuilder sqlErrorUpdate = new StringBuilder
				(UPDATE_I_ElementValue).append(" i ")
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
				.append(DB.TO_STRING("Update ElementValue"))
				.append(" WHERE I_ElementValue_ID = ? ");

		// Go through Records
		sql = new StringBuilder("SELECT * ")
		.append(" FROM I_ElementValue ")
		.append(" WHERE I_IsImported='N'")
		.append(clientCheck)
		.append(" ORDER BY I_ElementValue_ID");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				final X_I_ElementValue impEV = new X_I_ElementValue(ctx, rset,
						trxName);
				final int C_ElementValue_ID = impEV.getC_ElementValue_ID();
				final int I_ElementValue_ID = impEV.getI_ElementValue_ID();

				// **** Create/Update ElementValue
				if (C_ElementValue_ID == 0) // New
				{
					final MElementValue mElemValue = new MElementValue(impEV);
					if (mElemValue.save()) {
						noInsert++;
						impEV.setC_ElementValue_ID(mElemValue
								.getC_ElementValue_ID());
						impEV.setI_IsImported(true);
						impEV.saveEx();

					} else {
						// .append(DB.TO_STRING("Insert ElementValue "))
						DB.executeUpdate(sqlErrorInsert.toString(),
								I_ElementValue_ID, trxName);
					}
				} else // Update existing
				{
					final MElementValue mEleVal = new MElementValue(ctx,
							C_ElementValue_ID, trxName);
					// if (ev.get_ID() != C_ElementValue_ID)
					// {
					//
					// }
					mEleVal.set(impEV);
					if (mEleVal.save()) {
						noUpdate++;
						impEV.setI_IsImported(true);
						impEV.saveEx();
						
					} else {
						// .append(DB.TO_STRING("Update ElementValue"))
						DB.executeUpdate(sqlErrorUpdate.toString(),
								I_ElementValue_ID, trxName);
					}
				}
			} // for all I_Product
			// rs.close();
			// pstmt.close();
		} catch (SQLException e) {
			throw new Exception("create", e);
			
		} finally {
			DB.close(rset, pstmt);
		}

		// Set Error to indicator to not imported
		sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET I_IsImported='N', Updated=SysDate ")
				.append(" WHERE I_IsImported<>'Y'").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), trxName);
		addLog(0, null, new BigDecimal(num), "@Errors@");
		addLog(0, null, new BigDecimal(noInsert),"@C_ElementValue_ID@: @Inserted@");
		addLog(0, null, new BigDecimal(noUpdate),"@C_ElementValue_ID@: @Updated@");
		return true;

	}

	/**
	 * updateParentElementValueID
	 * 
	 * @param trxName
	 * @return
	 */
	private boolean updateParentElementValueID(final String trxName) {
		StringBuilder sql = null;
		int num = 0;

		// ***** Set Parent
		sql = new StringBuilder(UPDATE_I_ElementValue).append(" i ")
				.append(" SET ParentElementValue_ID=(SELECT C_ElementValue_ID")
				.append("                            FROM C_ElementValue ev ")
				.append("                            WHERE i.C_Element_ID=ev.C_Element_ID")
				.append("                            AND i.ParentValue=ev.Value ")
				.append("                            AND i.AD_Client_ID=ev.AD_Client_ID) ")
				.append(" WHERE ParentElementValue_ID IS NULL")
				.append(" AND I_IsImported='Y'")
				.append(clientCheck);
		num = DB.executeUpdate(sql.toString(), trxName);
		log.fine("Found Parent ElementValue=" + num);
		//
		sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET I_ErrorMsg=I_ErrorMsg||'Info=ParentNotFound, ' ")
				.append(" WHERE ParentElementValue_ID IS NULL ")
				.append(" AND ParentValue IS NOT NULL")
				.append(" AND I_IsImported='Y' ")
				.append(" AND Processed='N'")
				.append(clientCheck);
		num = DB.executeUpdate(sql.toString(), trxName);
		log.fine("Not Found Parent ElementValue=" + num);
		return true;
	}

	/**
	 * updateTreeNode
	 * 
	 * @param ctx
	 * @param adClientId
	 * @param trxName
	 * @return
	 */
	private boolean updateTreeNode(final int adClientId, final String trxName) {
		StringBuilder sql = null;
		int num = 0;

		// Actualizar el arbol
		sql = new StringBuilder(
				" SELECT i.ParentElementValue_ID ")
				.append(", i.I_ElementValue_ID   ")
				.append(", e.AD_Tree_ID          ")
				.append(", i.C_ElementValue_ID   ")
				.append(", i.Value||'-'||i.Name AS Info ")
				.append(" FROM I_ElementValue i  ")
				.append(" INNER JOIN C_Element e ON (i.C_Element_ID=e.C_Element_ID) ")
				.append(" WHERE i.C_ElementValue_ID   IS NOT NULL ")
				.append(" AND   e.AD_Tree_ID          IS NOT NULL")
				.append(" AND i.ParentElementValue_ID IS NOT NULL")
				.append(" AND i.I_IsImported ='Y' ")
				.append(" AND Processed      ='N' ")
				.append(" AND i.AD_Client_ID = ?  ");

		int noParentUpdate = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, adClientId);
			rset = pstmt.executeQuery();
			//
			final StringBuilder updateSQL = new StringBuilder(
					"UPDATE AD_TreeNode SET Parent_ID=?, SeqNo=? ")
					.append("WHERE AD_Tree_ID=? AND Node_ID=?");

			final PreparedStatement updateStmt = DB.prepareStatement(
					updateSQL.toString(), ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE, trxName);

			while (rset.next()) {
				updateStmt.setInt(1, rset.getInt(1)); // Parent
				updateStmt.setInt(2, rset.getInt(2)); // SeqNo (assume sequenec
														// in import is the
														// same)
				updateStmt.setInt(3, rset.getInt(3)); // Tree
				updateStmt.setInt(4, rset.getInt(4)); // Node
				try {
					num = updateStmt.executeUpdate();
					noParentUpdate += num;
				} catch (SQLException ex) {
					log.log(Level.SEVERE, "(ParentUpdate)", ex);
					num = 0;
				}

				if (num == 0) {
					log.info("Parent not found for " + rset.getString(5));
				}
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "(ParentUpdateLoop) " + sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		addLog(0, null, new BigDecimal(noParentUpdate),
				"@ParentElementValue_ID@: @Updated@");
		return true;
	}

	/**
	 * resetProcessingFlag
	 * 
	 * @param trxName
	 * @return
	 */
	private boolean resetProcessingFlag(final String trxName) {
		StringBuilder sql = null;
		int num = 0;

		// Reset Processing Flag
		sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET Processing='-'")
				.append(" WHERE I_IsImported='Y' ")
				.append(" AND Processed='N' ")
				.append(" AND Processing='Y'")
				.append(" AND C_ElementValue_ID IS NOT NULL")
				.append(clientCheck);
		if (updateDefaultAcct) {
			sql.append(" AND AD_Column_ID IS NULL");
		}
		num = DB.executeUpdate(sql.toString(), trxName);
		log.fine("Reset Processing Flag=" + num);
		return true;
	}

	/**
	 * updateDone
	 * 
	 * @param ctx
	 * @param adClientId
	 * @param trxName
	 * @return
	 */
	private boolean updateDone(final String trxName) {
		StringBuilder sql = null;
		int num = 0;
		// Done
		sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
				.append(UPDATE_I_ElementValue)
				.append(" SET Processing='N', Processed='Y' ")
				.append(" WHERE I_IsImported='Y' ")
				.append(clientCheck);
		num = DB.executeUpdate(sql.toString(), trxName);
		log.fine("Processed=" + num);

		return true;
	} // doIt

	/**************************************************************************
	 * Update Default Accounts
	 * 
	 * @param clientCheck
	 *            client where cluase
	 */
	private void updateDefaults() {
		log.fine("CreateNewCombination=" + createNewCombinat);

		// **** Update Defaults
		StringBuilder sql = new StringBuilder(
				"SELECT C_AcctSchema_ID FROM C_AcctSchema_Element ").append(
				"WHERE C_Element_ID=?").append(clientCheck);

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(),get_TrxName());
			pstmt.setInt(1, cElementId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				updateDefaultAccounts(rset.getInt(1));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		// Default Account DEFAULT_ACCT
		sql = new StringBuilder("UPDATE C_AcctSchema_Element e ")
				.append("SET C_ElementValue_ID=(SELECT C_ElementValue_ID FROM I_ElementValue i")
				.append(" WHERE e.C_Element_ID=i.C_Element_ID AND i.C_ElementValue_ID IS NOT NULL")
				.append(" AND UPPER(i.Default_Account)='DEFAULT_ACCT') ")
				.append("WHERE EXISTS (SELECT * FROM I_ElementValue i")
				.append(" WHERE e.C_Element_ID=i.C_Element_ID AND i.C_ElementValue_ID IS NOT NULL")
				.append(" AND UPPER(i.Default_Account)='DEFAULT_ACCT' ")
				.append("	AND i.I_IsImported='Y' AND i.Processing='-')")
				.append(clientCheck);
		final int num = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog(0, null, new BigDecimal(num),
				"@C_AcctSchema_Element_ID@: @Updated@");
	} // updateDefaults

	/**
	 * Update Default Accounts. _Default.xxxx = C_ValidCombination_ID =>
	 * Account_ID=C_ElementValue_ID
	 * 
	 * @param C_AcctSchema_ID
	 *            Accounting Schema
	 */
	private void updateDefaultAccounts(final int C_AcctSchema_ID) {
		log.fine("C_AcctSchema_ID=" + C_AcctSchema_ID);

		final MAcctSchema mAcctSchema = new MAcctSchema(getCtx(), C_AcctSchema_ID,
				get_TrxName());
		if (mAcctSchema.getAcctSchemaElement("AC").getC_Element_ID() != cElementId) {
			log.log(Level.SEVERE, "C_Element_ID=" + cElementId
					+ " not in AcctSchema=" + mAcctSchema);
			return;
		}

		int[] counts = new int[] { 0, 0, 0 };
		
		final StringBuilder sqlProcessing = new StringBuilder(
				UPDATE_I_ElementValue)
				.append(" SET Processing='N' ")
				.append("WHERE I_ElementValue_ID = ? ");
		

		final StringBuilder sql = new StringBuilder(
				"SELECT i.C_ElementValue_ID, t.TableName, c.ColumnName, i.I_ElementValue_ID ")
				.append("FROM I_ElementValue i")
				.append(" INNER JOIN AD_Column c ON (i.AD_Column_ID=c.AD_Column_ID)")
				.append(" INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID) ")
				.append("WHERE i.I_IsImported='Y' AND Processing='Y'")
				.append(" AND i.C_ElementValue_ID IS NOT NULL AND C_Element_ID=?");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, cElementId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				final int C_ElementValue_ID = rset.getInt(1);
				final String TableName = rset.getString(2);//C_AcctSchema_Default
				final String ColumnName = rset.getString(3);//CB_CashTransfer_Acct
				final int I_ElementValue_ID = rset.getInt(4);
				
				// Update it
				final int iUpdate = updateDefaultAccount(TableName, ColumnName,
						C_AcctSchema_ID, C_ElementValue_ID);
				counts[iUpdate]++;
				if (iUpdate != UPDATE_ERROR) {
//					sql = new StringBuilder(
//							"UPDATE I_ElementValue SET Processing='N' ")
//							.append("WHERE I_ElementValue_ID=").append(
//									I_ElementValue_ID);
					final int num = DB.executeUpdate(sqlProcessing.toString(), I_ElementValue_ID, get_TrxName());
					if (num != 1) {
						log.log(Level.SEVERE, "Updated=" + num);
					}
				}
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rset, pstmt);
		}
		
		addLog(0, null, new BigDecimal(counts[UPDATE_ERROR]), mAcctSchema.toString()
				+ ": @Errors@");
		addLog(0, null, new BigDecimal(counts[UPDATE_YES]), mAcctSchema.toString()
				+ ": @Updated@");
		addLog(0, null, new BigDecimal(counts[UPDATE_SAME]), mAcctSchema.toString()
				+ ": OK");

	} // createDefaultAccounts

	/**
	 * Update Default Account. This is the sql to delete unused accounts - with
	 * the import still in the table(!): DELETE C_ElementValue e WHERE NOT
	 * EXISTS (SELECT * FROM Fact_Acct f WHERE f.Account_ID=e.C_ElementValue_ID)
	 * AND NOT EXISTS (SELECT * FROM C_ValidCombination vc WHERE
	 * vc.Account_ID=e.C_ElementValue_ID) AND NOT EXISTS (SELECT * FROM
	 * I_ElementValue i WHERE i.C_ElementValue_ID=e.C_ElementValue_ID);
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
	 * Actualiza el parent_id en c_ElementValue de acuerdo al seleccionado en el
	 * mantenimiento (Importar Cuentas)
	 * 
	 * @param trxName
	 */
	public void setParentElement(final String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY)

		.append(" SELECT cval.c_elementvalue_id AS cvalid ")
		.append(", ival.parentelementvalue_id AS parentid ")
		.append(" FROM C_elementvalue cval ")
		.append(" INNER JOIN i_elementvalue ival ON (cval.c_elementvalue_id =ival.c_elementvalue_id ")
		.append("                               AND ival.parentelementvalue_id >0) ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				final int cval = rset.getInt("cvalid");
				final int parentid = rset.getInt("parentid");

				final MElementValue elementVal = new MElementValue(getCtx(),
						cval, trxName);
				elementVal.setParentElementValue_ID(parentid);
				elementVal.save(trxName);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "setParentElement (" + sql + ")", e);
		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Obtiene los registros en c_elementvalue que han sido creados con un
	 * parent_id != 0
	 * 
	 * @param trxName
	 */
	public void setTree(final String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT cval.c_elementvalue_id AS cvalid ")
		.append(", ival.parentelementvalue_id AS parentid ")
		.append(", e.AD_Tree_ID as treeID ")
		.append(" FROM C_elementvalue cval ")
		.append(" INNER JOIN i_elementvalue ival ON (cval.c_elementvalue_id = ival.c_elementvalue_id ")
		.append("                               AND ival.parentelementvalue_id >0 )  ")
		.append(" INNER JOIN C_Element e ON (ival.C_Element_ID=e.C_Element_ID) ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				final int cElement = rset.getInt("cvalid");
				final int parentid = rset.getInt("parentid");
				final int treeID = rset.getInt("treeid");
				// Itera la lista y guarda su ad_treenode
				saveTree(cElement, parentid, treeID, trxName);

			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "setTree (" + sql + ")", e);
		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Actualizar los registros en ad_treenode de acuerdo al parent_Id
	 * seleccionado en el mantenimiento (Importar Cuentas)
	 * 
	 * @param element
	 *            int - Nodo a Actualizar
	 * @param parent
	 *            int - Nuevo padre en el arbol
	 * @param treeID
	 *            int - Id del arbol
	 * @param trxName
	 *            String - trx
	 */
	public void saveTree(final int element, final int parent, final int treeID,
			final String trxName) {
		final int num = DB
				.executeUpdate(
						"UPDATE AD_TreeNode SET Parent_ID=?  WHERE AD_Tree_ID=? AND Node_ID=?",
						new Object[] { parent, treeID, element }, trxName);

		log.log(Level.SEVERE, "ImportAccount.saveTree - #" + num + " - ");
	}

	/**
	 * Check Account Type A (E) L M O R
	 */
	private void setAccountType() {
		int num = 0;
		StringBuilder sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET AccountType='E' ")
				.append(" WHERE AccountType IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set AccountType Default=" + num);
		//
		sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid AccountType, ' ")
				.append(" WHERE AccountType NOT IN ('A','E','L','M','O','R')")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Invalid AccountType=" + num);
	}

	/**
	 * Check Account Sign (N) C B
	 */
	private void setAccountSign() {
		int num = 0;
		// Check Account Sign (N) C B
		StringBuilder sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET AccountSign='N' ")
				.append(" WHERE AccountSign IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set AccountSign Default=" + num);
		//
		sql = new StringBuilder(UPDATE_I_ElementValue)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid AccountSign, ' ")
				.append(" WHERE AccountSign NOT IN ('N','C','D')")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Invalid AccountSign=" + num);
	}

	
	/**
	 * importar cuentas
	 */
	private void setGroupAcct() {
		StringBuilder sql = null;
		int num = 0;

		// Set GroupAcct
		sql = new StringBuilder(UPDATE_I_ElementValue).append(" i ")
				.append(" SET EXME_GroupAcct_ID = (SELECT EXME_GroupAcct_ID ")
				.append("                     FROM EXME_GroupAcct c   ")
				.append("                     WHERE upper(i.groupacct_value)=upper(c.Value) ")
				.append("                     AND c.AD_Client_ID = 0  ")
				.append("                    )                        ")
				.append(" WHERE groupacct_value IS NOT NULL ")
				.append(" AND EXME_GroupAcct_ID IS NULL")
				.append(" AND I_IsImported<>'Y'")
				.append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Column=" + num);
		
		//
		sql = new StringBuilder(UPDATE_I_ElementValue)
		.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Group Acct, ' ")
		.append("WHERE EXME_GroupAcct_ID IS NULL AND groupacct_value IS NOT NULL")
		
		// default
		// account
		.append(" AND I_IsImported<>'Y' ").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Invalid Column=" + num);
	}
		
	/**
	 *  No Value
	 * @return
	 */
	private boolean setErrorNoLevelCorrect(){
		// No Value
		final StringBuilder sql = new StringBuilder(UPDATE_I_ElementValue)
		.append(" SET I_IsImported='E', I_ErrorMsg= COALESCE (I_ErrorMsg,' ') || 'ERR=Acct level incorrect (Only 4), ' ")
		.append(" WHERE acct_level <> 4 ")
		.append(" AND I_IsImported<>'Y'")
		.append(clientCheck);
		final int num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Invalid Key=" + num);
		return true;
	}
} // ImportAccount
