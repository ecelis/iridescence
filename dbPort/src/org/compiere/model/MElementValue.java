package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.report.MReportSource;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.Utilerias;

import com.ecaresoft.acct.xml.XmlFileGenerator;
import com.ecaresoft.acct.xml.XmlValidator;
import com.ecaresoft.acct.xml.auxcuentas.AuxiliarCtas;
import com.ecaresoft.acct.xml.auxcuentas.AuxiliarCtas.Cuenta;
import com.ecaresoft.acct.xml.balanza.Balanza;
import com.ecaresoft.acct.xml.catalogo.Catalogo;
import com.ecaresoft.acct.xml.catalogo.Catalogo.Ctas;
import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * 	Natural Account
 */
public class MElementValue extends X_C_ElementValue {
	// how many account levels should exist above the current account
	public static final int MAX_LEVELS_ABOVE = 3;
	// bottom account level
	public static final int MAX_ACCT_LEVEL = 4;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**	Logger							*/
	private static CLogger		s_log = CLogger.getCLogger (MElementValue.class);

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_ElementValue_ID ID or 0 for new
	 *	@param trxName transaction
	 */
	public MElementValue(Properties ctx, int C_ElementValue_ID, String trxName) {
		super(ctx, C_ElementValue_ID, trxName);
		if (is_new()) {
			// setC_Element_ID (0); // Parent
			// setName (null);
			// setValue (null);
			setIsSummary(false);
			setAccountSign(ACCOUNTSIGN_Natural);
			setAccountType(ACCOUNTTYPE_Expense);
			setIsDocControlled(false);
			setIsForeignCurrency(false);
			setIsBankAccount(false);
			//
			setPostActual(true);
			setPostBudget(true);
			setPostEncumbrance(true);
			setPostStatistical(true);
		}
	} // MElementValue

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MElementValue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} //	MElementValue

	/**
	 * 	Full Constructor
	 *	@param ctx context
	 *	@param Value value
	 *	@param Name name
	 *	@param Description description
	 *	@param AccountType account type
	 *	@param AccountSign account sign
	 *	@param IsDocControlled doc controlled
	 *	@param IsSummary summary
	 *	@param trxName transaction
	 */
	public MElementValue(Properties ctx, String Value, String Name, //
		String Description, String AccountType, String AccountSign,//
		boolean IsDocControlled, boolean IsSummary, String trxName) {
		this(ctx, 0, trxName);
		setValue(Value);
		setName(Name);
		setDescription(Description);
		setAccountType(AccountType);
		setAccountSign(AccountSign);
		setIsDocControlled(IsDocControlled);
		setIsSummary(IsSummary);

	} // MElementValue

	/**
	 * 	Import Constructor
	 *	@param imp import
	 */
	public MElementValue(X_I_ElementValue imp) {
		this(imp.getCtx(), 0, imp.get_TrxName());
		setClientOrg(imp);
		set(imp);
	} //	MElementValue

	/**
	 * 	Set/Update Settings from import
	 *	@param imp import
	 */
	public void set(X_I_ElementValue imp) {
		setValue(imp.getValue());
		setName(imp.getName());
		setDescription(imp.getDescription());
		setAccountType(imp.getAccountType());
		setAccountSign(imp.getAccountSign());
		setIsSummary(imp.isSummary());
		setIsDocControlled(imp.isDocControlled());
		setC_Element_ID(imp.getC_Element_ID());
		//
		setPostActual(imp.isPostActual());
		setPostBudget(imp.isPostBudget());
		setPostEncumbrance(imp.isPostEncumbrance());
		setPostStatistical(imp.isPostStatistical());
		setParentElementValue_ID(imp.getParentElementValue_ID());
		
		
		setEXME_GroupAcct_ID(imp.getEXME_GroupAcct_ID());
		if(imp.getAcct_Level().compareTo(new BigDecimal(4))==0) {
			setAcct_Level(imp.getAcct_Level().intValue());
		}
		//
		// setC_BankAccount_ID(imp.getC_BankAccount_ID());
		// setIsForeignCurrency(imp.isForeignCurrency());
		// setC_Currency_ID(imp.getC_Currency_ID());
		// setIsBankAccount(imp.isIsBankAccount());
		// setValidFrom(null);
		// setValidTo(null);
	}   // set


	/**
	 * Is this a Balance Sheet Account
	 * @return boolean
	 */
	public boolean isBalanceSheet() {
		String accountType = getAccountType();
		return (ACCOUNTTYPE_Asset.equals(accountType)//
			|| ACCOUNTTYPE_Liability.equals(accountType)//
		|| ACCOUNTTYPE_OwnerSEquity.equals(accountType));
	} //	isBalanceSheet

	/**
	 * Is this an Activa Account
	 * @return boolean
	 */
	public boolean isActiva() {
		return ACCOUNTTYPE_Asset.equals(getAccountType());
	}	//	isActive

	/**
	 * Is this a Passiva Account
	 * @return boolean
	 */
	public boolean isPassiva() {
		String accountType = getAccountType();
		return (ACCOUNTTYPE_Liability.equals(accountType) || ACCOUNTTYPE_OwnerSEquity.equals(accountType));
	} //	isPassiva

	/**
	 * 	User String Representation
	 *	@return info value - name
	 */
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(getValue())) {
			sb.append(getValue());
		}
		if (StringUtils.isNotEmpty(getName())) {
			if (sb.length() > 0) {
				sb.append(" - ");
			}
			sb.append(getName());
		}
		return sb.toString();
	} // toString

	/**
	 * 	Extended String Representation
	 *	@return info
	 */
	public String toStringX() {
		StringBuffer sb = new StringBuffer("MElementValue[");
		sb.append(get_ID()).append(",").append(getValue()).append(" - ").append(getName()).append("]");
		return sb.toString();
	} // toStringX

	/**
	 * 	Before Save
	 *	@param newRecord
	 *	@return true if ir can be saved
	 */
	protected boolean beforeSave(boolean newRecord) {
		boolean retVale = true;
		if (getAD_Org_ID() != 0) {
			setAD_Org_ID(0);
		}
		// duplicated key
		if (newRecord || is_ValueChanged(COLUMNNAME_Value)) {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT C_ElementValue_ID FROM C_ElementValue ");
			sql.append("WHERE C_Element_ID=? AND C_ElementValue_ID<>? ");
			sql.append("AND value=?");

			int no = DB.getSQLValue(null, sql.toString(),//
				getC_Element_ID(), getC_ElementValue_ID(), getValue());
			if (no > 0) {
				log.saveError(null, Utilerias.getMsg(getCtx(), "msj.allergy.error"));
				return false;
			}
		}
		
		// si se esta inactivando
		if(!newRecord && is_ValueChanged(COLUMN_IsActive) && !isActive()) {
			if(!validateAccountingTables()){
				return false;
			}
		}

		if (!newRecord && isSummary() && is_ValueChanged(COLUMNNAME_IsSummary)) {
			String sql = "SELECT COUNT(*) FROM Fact_Acct WHERE Account_ID=?";

			int no = DB.getSQLValue(get_TrxName(), sql, getC_ElementValue_ID());

			if (no != 0) {
				log.saveError("Error", Msg.getMsg(getCtx(), "AlreadyPostedTo"));
				return false;
			}
		}

		if (getParentElementValue_ID() > 0) {

			int levelsAbove = 0;

			levelsAbove = MElementValue.findLevelsAbove(getParentElementValue_ID(), get_TrxName());
			setAcct_Level(levelsAbove + 1);

			log.finest("C_Element_ID = " + getC_Element_ID() + " | Parent Element ---> " + getParentElementValue_ID() + " | Acct Level ---> "
				+ getAcct_Level());

			if (getAcct_Level() == 4 && isSummary()) {
				log.saveError("Error", Msg.getMsg(getCtx(), "ElemValLevel4NoSummary"));
				// return false;
			}

			if (getAcct_Level() < MElementValue.MAX_ACCT_LEVEL) {
				setIsSummary(true);
			} else {
				setIsSummary(false);
			}

			retVale = levelsAbove <= MElementValue.MAX_LEVELS_ABOVE;
		} else {
			setAcct_Level(1);
			setIsSummary(true);
		}
		
		// agrupador sera obligatorio tiene configurado  Contabilidad Electrónica
		// y si la cuenta contable pertenece a un Nivel de cuenta > 1.
//		if(retVale && getAcct_Level() > 2 && MAcctSchema.get(getCtx(),Env.getC_AcctSchema_ID(getCtx())).isElectronicAccounting()){
//			if(getEXME_GroupAcct_ID() <= 0){
//				log.saveError("Error", super.getMandatoryMsg(COLUMNNAME_EXME_GroupAcct_ID));
//				return false;
//			}
//		}

		return retVale;
	} // beforeSave

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(success){
			if (newRecord) {
	//			insert_Tree(MTree_Base.TREETYPE_ElementValue, getC_Element_ID());
				insert_Tree(MTree_Base.TREETYPE_ElementValue);
			}
	
			// Value/Name change
			if (!newRecord && (is_ValueChanged(COLUMNNAME_Value) || is_ValueChanged(COLUMNNAME_Name))) {
				MAccount.updateValueDescription(getCtx(), "Account_ID=" + getC_ElementValue_ID(), get_TrxName());
				if ("Y".equals(Env.getContext(getCtx(), "$Element_U1")))
					MAccount.updateValueDescription(getCtx(), "User1_ID=" + getC_ElementValue_ID(), get_TrxName());
				if ("Y".equals(Env.getContext(getCtx(), "$Element_U2")))
					MAccount.updateValueDescription(getCtx(), "User2_ID=" + getC_ElementValue_ID(), get_TrxName());
			}

			if ((getAcct_Level() == 4 && newRecord && isActive())
				|| (getAcct_Level() == 4 && !newRecord && is_ValueChanged(COLUMN_IsActive) && isActive())) {
				MReportSource.createSourceForElement(this);
			} else if (getAcct_Level() == 4 && !newRecord && is_ValueChanged(COLUMN_IsActive) && !isActive()) {
				MReportSource.deleteForElementValue(getCtx(), getC_ElementValue_ID());
			}

			QuickSearchTables.checkTables(MElementValue.class, MElementValue.Table_Name, getC_ElementValue_ID(), get_TrxName(), p_ctx);
		}
		return success;
	} // afterSave

	@Override
	protected boolean insert_Tree(String treeType) {
		return insert_Tree(treeType, getC_Element_ID());
	}
	
	@Override
	protected boolean delete_Tree(String treeType) {
		StringBuilder sb = new StringBuilder();
		String tableName = MTree_Base.getNodeTableName(treeType);
		sb.append("DELETE FROM ").append(tableName).append(" t ");
		sb.append("WHERE Node_ID=? ");
		// Account Element Value handling
		sb.append(" AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=?");
		sb.append(" AND t.AD_Tree_ID=ae.AD_Tree_ID)");
		int no = DB.executeUpdate(sb.toString(), new Object[] { //
			getC_ElementValue_ID(), // node_id
				getC_Element_ID() // element_id
			}, get_TrxName());
		if (no > 0) {
			log.fine("#" + no + " - TreeType=" + treeType);
		} else {
			log.severe("#" + no + " - TreeType=" + treeType);
		}
		log.finest(sb.toString());
		return no > 0;
	}
	
	@Override
	protected boolean insert_Tree(String treeType, int C_Element_ID) {
		StringBuilder sb = new StringBuilder();
		String tableName = MTree_Base.getNodeTableName(treeType);
		sb.append("INSERT INTO ").append(tableName);
		sb.append(" (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy, ");
		sb.append("AD_Tree_ID, Node_ID, Parent_ID, SeqNo) ");
		sb.append("SELECT t.AD_Client_ID,0,'Y',SysDate,0,SysDate,0,");
		sb.append("t.AD_Tree_ID,?,?,? ");
		sb.append("FROM AD_Tree t ");
		sb.append("WHERE t.AD_Client_ID=? AND t.IsActive='Y'");
		// Account Element Value handling
		sb.append(" AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=?");
		sb.append(" AND t.AD_Tree_ID=ae.AD_Tree_ID)");
		// Duplicate Check
		sb.append(" AND NOT EXISTS (SELECT * FROM ").append(tableName).append(" e ");
		sb.append("WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=? )");
		int no = DB.executeUpdate(sb.toString(), new Object[] { //
			get_ID(), // node_id
				getParentElementValue_ID(), // parent_id
				StringUtils.isNumeric(getValue()) && getValue().length()<=10 ? Integer.valueOf(getValue()) : 999, // SeqNo
				getAD_Client_ID(), // Client_id
				C_Element_ID, // element_id
				get_ID() // node_id
			}, get_TrxName());
		if (no > 0) {
			log.fine("#" + no + " - TreeType=" + treeType);
		} else {
			log.severe("#" + no + " - TreeType=" + treeType);
		}
		log.finest(sb.toString());
		return no > 0;
	}

	@Override
	protected boolean beforeDelete() {
		boolean sucess = true;
		if (getAcct_Level() == 4) {
			// revisar que no haya referencias
			if(validateAccountingTables()){
			
				MReportSource.deleteForElementValue(getCtx(), getC_ElementValue_ID());
			} else {
				sucess = false;
			}
		} else {
			sucess =  false; // no se pueden eliminar de otros niveles
		}

		return sucess;
	}
	
	/**
	 * Para inactivar/eliminar una cuenta contable
	 * la cuenta no debera estar configurada como una cuenta predeterminada en:
	 * c_acctschema_default, c_bankaccount_acct, c_bp_customer_acct,
	 * c_bp_employee_acct, c_bp_group_acct, c_bp_vendor_acct,
	 * c_cashbook_acct, c_charge_acct, c_currency_acct, c_interorg_acct,
	 * c_tax_acct, c_withholding_acct, m_product_acct, m_product_category_acct,
	 * m_warehouse_acct
	 * 
	 * @return
	 */
	public boolean validateAccountingTables(){
		// StringBuilder sql = new StringBuilder();
		// sql.append(" AD_table.AD_table_ID IN (");
		// sql.append(" SELECT c.AD_table_ID FROM AD_Column c ");
		// sql.append(" WHERE c.ad_reference_id=? OR c.columnname='Account_ID')");
		// sql.append(" AND AD_table.tablename LIKE '%_Acct'");
		// List<Integer> list = new Query(getCtx(), X_AD_Table.Table_Name, sql.toString(), null)//
		// .setOnlyActiveRecords(true).setParameters(DisplayType.Account).listIds();

		final List<Integer> list = new ArrayList<>();
		list.add(X_C_AcctSchema_Default.Table_ID);
		list.add(X_C_BankAccount_Acct.Table_ID);
		list.add(X_C_BP_Customer_Acct.Table_ID);
		list.add(X_C_BP_Employee_Acct.Table_ID);
		list.add(X_C_BP_Group_Acct.Table_ID);
		list.add(X_C_BP_Vendor_Acct.Table_ID);
		list.add(X_C_CashBook_Acct.Table_ID);
		list.add(X_C_Charge_Acct.Table_ID);
		list.add(X_C_Currency_Acct.Table_ID);
		list.add(X_C_InterOrg_Acct.Table_ID);
		list.add(X_C_Tax_Acct.Table_ID);
		list.add(X_C_Withholding_Acct.Table_ID);
		list.add(X_M_Product_Acct.Table_ID);
		list.add(X_M_Product_Category_Acct.Table_ID);
		list.add(X_M_Warehouse_Acct.Table_ID);

		for (Integer integer : list) {
			if (!validateAccount(integer)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Valida si hay referencias del registro a inactivar borrar.
	 * 
	 * @param tableId AD_Table_ID de la tabla a validar las referencias
	 *            en todas las columnas definitidas como cuenta contable
	 * @return
	 */
	public boolean validateAccount(int tableId){
		int no = 0;
		if (tableId > 0) {
			final MTable table = MTable.get(getCtx(), tableId);
			if (table != null) {
				final StringBuilder sql = new StringBuilder();
				sql.append("SELECT count(*) FROM C_ElementValue e ");
				sql.append("INNER JOIN C_ValidCombination c ON e.C_ElementValue_ID=c.Account_ID ");
				sql.append("INNER JOIN ").append(table.getTableName());
				sql.append(" a ON (");

				int idx = 0;
				for (MColumn col : table.getColumns(false)) {
					if (DisplayType.Account == col.getAD_Reference_ID() || "Account_ID".equals(col.getColumnName())) {
						if (idx > 0) {
							sql.append(" OR ");
						}
						sql.append(" a.").append(col.getColumnName()).append("=c.C_ValidCombination_ID ");
						idx++;
					}
				}
				if (idx > 0) {
					sql.append(" ) WHERE e.C_ElementValue_ID=? ");
					sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", table.getAD_Table_ID(), "a"));
					no = DB.getSQLValue(null, sql.toString(), get_ID());
					if (no > 0) {
						log.saveError("Error",
							Utilerias.getMsg(getCtx(), "No es posible borrar la cuenta, existen referencias en: " + table.getTrlName()));// TODO
					}
				}
			}
		}
		return no<=0;
	}

	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	protected boolean afterDelete(boolean success) {
		if (success) {
			delete_Tree(MTree_Base.TREETYPE_ElementValue);
		}
		return success;
	} //	afterDelete

	/**
	 * Obtener Lista de Cuentas
	 * @param
	 * @return List<MElementValue>
	 */
	public static List<MElementValue> getListaCuentas(Properties ctx) {
		return new Query(ctx, Table_Name, null, null)//
		.addAccessLevelSQL(true)//
		.setOnlyActiveRecords(true)//
		.list();
	}
	
	/**
	 * Regresa un listado de cuentas para el elemento activo del cliente,
	 * ordernadas por el padre, y numero de cuenta
	 * 
	 * @param ctx Contexto
	 * @param where Clausula where (debe empezar en AND)
	 * @param params parametros relacionados a la clausula where
	 * @return List<MElementValue>
	 */
	public static List<MElementValue> getAccounts(Properties ctx, StringBuilder where, List<Object> params) {

		if (where == null) {
			where = new StringBuilder();
		}
		if (params == null) {
			params = new ArrayList<>();
		}

		where.insert(0, " C_Element_ID=? ");
		params.add(0, getClientElementId(ctx));

		return new Query(ctx, Table_Name, where.toString(), null)//
			.setParameters(params)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" COALESCE(ParentElementValue_ID ,0), replace(value,' ','') ")//
			.list();
	}
	
	/**
	 * Obtiene el Elemento activo del cliente
	 * 
	 * @param ctx
	 * @return
	 */
	public static int getClientElementId(Properties ctx) {
		return new Query(ctx, MElement.Table_Name, " AD_Client_ID=? AND ElementType=? ", null)//
		.setParameters(Env.getAD_Client_ID(ctx), MElement.ELEMENTTYPE_Account)//
		.setOnlyActiveRecords(true)//
		.firstId();
	}

	/** @return Obtiene el AD_Tree_ID configurado para el elemento activo del cliente */
	public static int getClientElementTreeId(Properties ctx) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.AD_Tree_ID FROM AD_Tree t ");
		sql.append("INNER JOIN C_Element e ON t.AD_Tree_ID = e.AD_Tree_ID AND e.IsActive='Y' ");
		sql.append("WHERE t.AD_Client_ID=? AND t.IsActive='Y' AND ElementType=? ");
		sql.append("ORDER BY t.IsDefault DESC, t.AD_Tree_ID");
		int AD_Tree_ID = DB.getSQLValue(null, sql.toString(), Env.getAD_Client_ID(ctx), MElement.ELEMENTTYPE_Account);
		return AD_Tree_ID;
	}
	
	/**
	 *
	 * @param ctx
	 * @param elementID
	 * @param isSummary
	 * @return
	 */
	public static List<MElementValue> getLstbyElement(Properties ctx, int elementID) {
		// sql.append("SELECT * FROM C_ELEMENTVALUE WHERE C_ELEMENT_ID = ? AND ISACTIVE = 'Y' ");
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		// sql.append(" ORDER BY VALUE ");
		return new Query(ctx, Table_Name, " C_ELEMENT_ID = ? ", null)//
			.setParameters(elementID)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" Value")//
			.list();
	}

	/**
	 * Finds the account level for the entered account id.
	 * @param acctId The account to validate
	 * @return The account level
	 */
	public static int findLevelsAbove(int acctId, String trxName) {
		int acctLevel = 1;

		String sql = "SELECT ParentElementValue_ID FROM C_ElementValue WHERE C_ElementValue_ID = ?";

		int parentElement = DB.getSQLValue(trxName, sql, acctId);

		if(parentElement > 0) {
			acctLevel++;
		}

		while(parentElement > 0 && acctLevel <= MElementValue.MAX_LEVELS_ABOVE) {
			parentElement = DB.getSQLValue(trxName, sql, parentElement);

			if(parentElement > 0) {
				acctLevel++;
			}
		}

		return acctLevel;
	}
	
	private String parentName;

	/** @return Nombre de la cuenta padre */
	public String getParentName() {
		if (getParentElementValue_ID() > 0 && parentName == null) {
			parentName = DB.getSQLValueString(null, "SELECT value||'-'||name FROM C_ElementValue WHERE C_ElementValue_ID=?", getParentElementValue_ID());
		}
		return parentName;
	}
	
	/**
	 * @return Genera el consecutivo de cuenta sugerido,
	 *         basando en el conteo de hijos o el valor maximo (+1) de las llaves de busqueda
	 */
	public String getNextChildAcct() {
		String val;
		if (getC_ElementValue_ID() > 0) {
			val = DB.getSQLValueString(null, "SELECT getElementNode_SeqNo(?) FROM dual", getC_ElementValue_ID());
			if (StringUtils.length(val) < 4) {
				val = new StringBuilder(StringUtils.defaultString(getValue())).append(StringUtils.defaultIfEmpty(val, "1")).toString();
			}
		} else {
			val = StringUtils.EMPTY;
		}
		return val;
	}

	/** @return nombre del valor de la naturaleza */
	public String getAccountSignName() {
		if (StringUtils.isNotEmpty(getAccountSign())) {
			return MRefList.getListName(getCtx(), ACCOUNTSIGN_AD_Reference_ID, getAccountSign());
		} else {
			return StringUtils.EMPTY;
		}
	}

	/** @return nombre del valor del tipo de cuenta */
	public String getAccountTypeName() {
		if (StringUtils.isNotEmpty(getAccountType())) {
			return MRefList.getListName(getCtx(), ACCOUNTTYPE_AD_Reference_ID, getAccountType());
		} else {
			return StringUtils.EMPTY;
		}
	}
	
	public String getAcctLevelStr() {
		return String.valueOf(super.getAcct_Level());
	}
	
	private String parentValue;
	public String getParentValue() {
		return parentValue;
	}
	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<XmlComponents> getAccountsXml(Properties ctx, String trxName) {
		final ArrayList<XmlComponents> lst = new ArrayList<XmlComponents>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select g.value as group, v.value as account, v.name as description, vp.name as namepatern, v.acct_level as level, ");
		sql.append(" v.AccountSign as natur");
		sql.append(" from c_elementvalue v ");
		sql.append(" inner join  c_elementvalue vp on  v.parentelementvalue_id = vp.c_elementvalue_id ");
		sql.append(" inner join exme_groupacct g on g.exme_groupacct_id = v.exme_groupacct_id ");
		sql.append(" where v.acct_level in (3,4) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "v"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				XmlComponents components = new XmlComponents();
				components.setGroupAcct(rs.getString("group"));
				components.setAccount(rs.getString("account"));
				components.setDescription(rs.getString("description"));
				components.setPaternAcct(rs.getString("namepatern"));
				components.setLevel(rs.getInt("level"));
				components.setNatur(rs.getString("natur"));
				lst.add(components);

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	/**
	 * Genera el archivo XML para el catalogo de cuentas
	 * @param ctx
	 * @param year
	 * @param period
	 * @param trxName
	 * @return
	 */
	public static File getChartOfAccountsXML(Properties ctx, int year, String period, String trxName) {
		File xml = null;
		Catalogo catalogo = new Catalogo();
		MOrgInfo orgInfo = MOrgInfo.get(ctx, Env.getAD_Org_ID(ctx));

		catalogo.setVersion("1.1");
		catalogo.setRFC(orgInfo.getTaxID());

		catalogo.setMes(period);
		catalogo.setAnio(year);
		// catalogo.setSello("");
		// catalogo.setNoCertificado(""); NO REQUERIDOS POR EL MOMENTO
		// catalogo.setCertificado("");

		for (final Object obj : getAccountsXml(ctx, null)) {
			final XmlComponents components = (XmlComponents) obj;

			Ctas ctas = new Ctas();
			ctas.setCodAgrup(components.getGroupAcct());
			ctas.setNumCta(components.getAccount());
			ctas.setDesc(StringEscapeUtils.escapeXml(components.getDescription()));

			int level = 0;
			if (components.getLevel() == 4) {
				level = 2;
				ctas.setNivel(level);
				ctas.setSubCtaDe(StringEscapeUtils.escapeXml(components.getPaternAcct()));
			} else if (components.getLevel() == 3) {
				level = 1;
				ctas.setNivel(level);
			}

			String naturaleza = null;
			if ("C".equals(components.getNatur())) {
				naturaleza = "A";
				ctas.setNatur(naturaleza);
			} else {
				naturaleza = "D";
				ctas.setNatur(naturaleza);
			}
			
			catalogo.getCtas().add(ctas);

		}

		XmlFileGenerator xmlFile = new XmlFileGenerator("www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogoCuentas http://www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogoCuentas/CatalogoCuentas_1_1.xsd");
		xmlFile.setClazz(Catalogo.class);
		xmlFile.setObj(catalogo);
		xmlFile.setPrefix("catalogo");
		xmlFile.setValidator(new ChartValidator(catalogo));

		ErrorList errorList = xmlFile.generateFile();

		if (errorList != null && errorList.isEmpty()) {
			xml = xmlFile.getXmlFile();

			System.out.println(xml.getAbsolutePath());
		} else {
			if (errorList != null) {
				System.out.println(errorList.toString());
			} else {

			}
		}
		return xml;

	}

	/**
	 * 
	 * @author aaguilar
	 *
	 */
	public static class ChartValidator implements XmlValidator {

		private Catalogo catalogo;
		public static final String RFC_REGEX = "[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?";
		public static final String[] MONTHS = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		public static final String[] TYPES = new String[] { "AF", "FC", "DE", "CO" };

		public ChartValidator(Catalogo catalogo) {
			this.catalogo = catalogo;
		}

		@Override
		public ErrorList validate() {
			ErrorList errorList = new ErrorList();
			if (!Pattern.matches(RFC_REGEX, catalogo.getRFC())) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.rfcInvalido"));
			}

			if (!Arrays.asList(MONTHS).contains(catalogo.getMes())) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.mesInvalido"));
			}

			if (catalogo.getAnio() < 2015 || catalogo.getAnio() > 2099) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.anoInvalido"));
			}

			return errorList;
		}
	}

	/**
	 * 
	 * @author aaguilar
	 *
	 */
	public static class XmlComponents {
		private String groupAcct;
		private String account;
		private String description;
		private String paternAcct;
		private int level;
		private int acctId;
		private BigDecimal saldoInicial;
		private BigDecimal debe;
		private BigDecimal haber;
		private BigDecimal saldoFinal;
		private BigDecimal faltaConfiguracion;
		
		public int getAcctId() {
			return acctId;
		}

		public void setAcctId(int acctId) {
			this.acctId = acctId;
		}

		public BigDecimal getFaltaConfiguracion() {
			return faltaConfiguracion;
		}

		public void setFaltaConfiguracion(BigDecimal faltaConfiguracion) {
			this.faltaConfiguracion = faltaConfiguracion;
		}

		public String getPaternAcct() {
			return paternAcct;
		}

		public void setPaternAcct(String paternAcct) {
			this.paternAcct = paternAcct;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		private String natur;

		public String getGroupAcct() {
			return groupAcct;
		}

		public void setGroupAcct(String groupAcct) {
			this.groupAcct = groupAcct;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getNatur() {
			return natur;
		}

		public void setNatur(String natur) {
			this.natur = natur;
		}
		
		public BigDecimal getSaldoInicial() {
			return saldoInicial;
		}

		public void setSaldoInicial(BigDecimal saldoInicial) {
			this.saldoInicial = saldoInicial;
		}

		public BigDecimal getDebe() {
			return debe;
		}

		public void setDebe(BigDecimal debe) {
			this.debe = debe;
		}

		public BigDecimal getHaber() {
			return haber;
		}

		public void setHaber(BigDecimal haber) {
			this.haber = haber;
		}

		public BigDecimal getSaldoFinal() {
			return saldoFinal;
		}

		public void setSaldoFinal(BigDecimal saldoFinal) {
			this.saldoFinal = saldoFinal;
		}

	}

	/**
	 * Genera el Archivo xml de la balanza de comprobación
	 * @param ctx
	 * @param periodId
	 * @param trxName
	 * @return
	 */
	public static List<XmlComponents> getXmlBalance(Properties ctx, int periodId, int years,boolean agrupar, String trxName) {
		final ArrayList<XmlComponents> list = new ArrayList<XmlComponents>();

		StringBuilder sql = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		String yearS = Integer.toString(years);

//		sql.append("set work_mem = '50 MB'; ");
		sql.append("with fact_acct as ");
		sql.append("( ");
		sql.append("select account_id ");
		sql.append(", c_period_id ");
		sql.append(", sum(fa.amtacctdr) as debe ");
		sql.append(", sum(fa.amtacctcr) as haber ");
		sql.append("from fact_acct fa ");
		params.add(Env.getAD_Org_ID(ctx));
		sql.append("where ad_org_id = ? ");
		params.add(periodId);
		sql.append("and c_period_id <= ?  ");
		sql.append("group by fa.account_id, c_period_id ");
		sql.append("), cuentas as ");
		sql.append("( ");
		sql.append("select ");
		sql.append("eleval4.c_elementvalue_id as e4i, eleval4.value as e4v, eleval4.name as e4n, eleval4.accounttype as e4t, eleval4.parentelementvalue_id as e4p, eleval4.accountsign as e4s, eleval4.acct_level as e4l ");
		sql.append("from c_elementvalue eleval4 ");
		params.add(Env.getAD_Client_ID(ctx));
		sql.append(" where eleval4.ad_client_id = ? ");
		sql.append("), ctas_saldos as ");
		sql.append("( ");
	    sql.append("select c.e4p, c.e4i, c.e4t, c.e4l, fa.* ");
	    sql.append(", (case when c.e4s = 'D' ");
	    sql.append("then (fa.debe) - (fa.haber)  ");
	    sql.append("else (fa.haber) - (fa.debe) ");
	    sql.append("end) as saldo ");
	    sql.append("from cuentas c ");
	    sql.append("left join fact_acct fa on c.e4i = fa.account_id "); 
	    sql.append("), Saldo_periodo as ");
	    sql.append("( ");
	    sql.append("select distinct ");
	    sql.append("s.e4i ");
	    sql.append(", s.e4p ");
	    sql.append(", s.e4l ");
	    sql.append(", s.c_period_id ");
	    sql.append(", sum(debe) over (w) as debe ");
	    sql.append(", sum(haber) over (w) as haber ");
	    sql.append(", sum(saldo) over (w) as saldo_periodo ");
	    sql.append("from ctas_saldos s ");
	    params.add(periodId);
	    sql.append("where c_period_id = ?  ");
	    sql.append("window w as (partition by e4i) ");
	    sql.append("), Saldo_inicial_APC as ");
	    sql.append("( ");
	    sql.append("select distinct ");
	    sql.append("s.e4i, s.e4p, s.e4l, sum(saldo) over (w) as saldo_inicial ");
	    sql.append("from ctas_saldos s ");
	    params.add(periodId);
	    sql.append(" where (c_period_id < ? ) ");
	    sql.append("and s.e4t in ('A', 'L', 'O') ");
	    sql.append("window w as (partition by e4i) ");
	    sql.append("), Saldo_inicial_IG as ");
	    sql.append("( ");
	    sql.append("select distinct ");
	    sql.append("s.e4i, s.e4p, s.e4l, sum(saldo) over (w) as saldo_inicial ");
	    sql.append("from ctas_saldos s ");
	    sql.append("inner join c_period p on p.c_period_id = s.c_period_id ");
	    sql.append("inner join c_year y on y.c_year_id = p.c_year_id ");
	    params.add(periodId);
	    sql.append("where (s.c_period_id < ? or s.c_period_id is null) ");
	    params.add(yearS);
	    sql.append("and y.year = ? ");
	    sql.append("and s.e4t in ('E', 'R') ");
	    sql.append("window w as (partition by e4i) ");
	    sql.append("), saldo_inicial as ");
	    sql.append("( ");
	    sql.append("select * from Saldo_inicial_IG ");
	    sql.append("union all ");
	    sql.append("select * from Saldo_inicial_APC ");
	    sql.append("), RE as ");
	    sql.append("( ");
	    sql.append("with re_ingresos as ");
	    sql.append("( ");
	    sql.append("select distinct ");
	    sql.append("y.year ");
	    sql.append(", sum(s.saldo)over (w) as saldo ");
	    sql.append("from ctas_saldos s ");
	    sql.append("inner join c_period p on p.c_period_id = s.c_period_id ");
	    sql.append("inner join c_year y on y.c_year_id = p.c_year_id ");
	    params.add(periodId);
	    sql.append("where (s.c_period_id < ? ) ");
	    params.add(years);
	    sql.append("and y.year::integer = ? -1 "); 
	    sql.append("and y.Cierre = 'N' "); 
	    sql.append("and s.e4t = 'R' ");
	    sql.append("window w as (partition by y.year) ");
	    sql.append("), re_gastos as ");
	    sql.append("(  ");
	    sql.append("select distinct ");
	    sql.append("y.year ");
	    sql.append(", sum(s.saldo)over (w) as saldo ");
	    sql.append("from ctas_saldos s ");
	    sql.append("inner join c_period p on p.c_period_id = s.c_period_id ");
	    sql.append("inner join c_year y on y.c_year_id = p.c_year_id ");
	    params.add(periodId);
	    sql.append("where (s.c_period_id < ? ) ");
	    params.add(years);
	    sql.append("and y.year::integer = ? -1 ");
	    sql.append("and s.e4t = 'E' ");
	    sql.append("and y.Cierre = 'N' ");
	    sql.append("window w as (partition by y.year) ");
	    sql.append(")  ");
	    sql.append("select  ");
	    sql.append("coalesce(ri.saldo,0) - coalesce(rg.saldo,0) as RE  ");
	    sql.append("from re_ingresos ri, re_gastos rg  ");
	    sql.append("), cuenta_re as ");
	    sql.append("( ");
	    sql.append("select distinct ev.c_elementvalue_id, ev.value as erctaV, ev.name AS erctaN ");
	    sql.append("from C_AcctSchema_GL agl ");
	    sql.append("inner join C_ValidCombination vc on agl.retainedearning_acct = vc.C_ValidCombination_id ");
	    sql.append("inner join c_elementvalue ev on ev.c_elementvalue_id = vc.account_id ");
	    params.add(Env.getAD_Client_ID(ctx));
	    sql.append("where ev.ad_client_id = ? ");
	    sql.append("), todo_ct4 as ");
	    sql.append("( ");
	    sql.append("select  ");
	    sql.append("max(sp.c_period_id) over () as c_period_id ");
	    sql.append(", coalesce(si.e4p, sp.e4p) as e4p ");
	    sql.append(", coalesce(si.e4i, sp.e4i) as e4i ");
	    sql.append(", coalesce(si.saldo_inicial,0) as saldo_inicial ");
	    sql.append(", coalesce(sp.debe,0) as debe ");
	    sql.append(", coalesce(sp.haber,0) as haber ");
	    sql.append(", coalesce(sp.saldo_periodo,0) as saldo_periodo ");
	    sql.append(", coalesce(si.saldo_inicial,0) + coalesce(sp.saldo_periodo,0) as saldo_final ");
	    sql.append(", coalesce(si.e4l,sp.e4l) as nivel ");
	    sql.append("from saldo_periodo sp ");
	    sql.append("full outer join Saldo_inicial si on sp.e4i = si.e4i ");
	    sql.append("union all  "); 
	    sql.append("select  ");
	    sql.append("null as c_period_id ");
	    sql.append(", 0 as e4p ");
	    sql.append(", (Select c_elementvalue_id from cuenta_re) as e4i ");
	    sql.append(", re as saldo_inicial ");
	    sql.append(", 0 as debe ");
	    sql.append(", 0 as haber ");
	    sql.append(", 0 as saldo_periodo ");
	    sql.append(", re as saldo_final ");
	    sql.append(", 4 as nivel ");
	    sql.append("from re ");
	    sql.append(") ,todo as ");
	    sql.append("( ");
	    sql.append("select ");
	    sql.append("eleval1.c_elementvalue_id as e1i, eleval1.value as e1v, eleval1.name as e1n, eleval1.accounttype as e1t, eleval1.parentelementvalue_id as e1p, eleval1.accountsign as e1s , eleval1.acct_level as e1l ");
	    sql.append(", eleval2.c_elementvalue_id as e2i, eleval2.value as e2v, eleval2.name as e2n, eleval2.accounttype as e2t, eleval2.parentelementvalue_id as e2p, eleval2.accountsign as e2s , eleval2.acct_level as e2l ");
	    sql.append(", eleval3.c_elementvalue_id as e3i, eleval3.value as e3v, eleval3.name as e3n, eleval3.accounttype as e3t, eleval3.parentelementvalue_id as e3p, eleval3.accountsign as e3s, eleval3.acct_level as e3l ");
	    sql.append(", eleval4.c_elementvalue_id as e4i, eleval4.value as e4v, eleval4.name as e4n, eleval4.accounttype as e4t, eleval4.parentelementvalue_id as e4p, eleval4.accountsign as e4s, eleval4.acct_level as e4l ");
	    sql.append(", (select value || ' - ' || name from exme_groupacct ga where ga.exme_groupacct_id = eleval3.exme_groupacct_id) as e3g ");
	    sql.append(" , (select value || ' - ' || name from exme_groupacct ga where ga.exme_groupacct_id = eleval4.exme_groupacct_id) as e4g ");
	    sql.append(",c.c_period_id, c.saldo_inicial, c.debe, c.haber,saldo_periodo, c.saldo_final ");
	    sql.append("from c_elementvalue eleval4  ");
	    params.add(Env.getAD_Client_ID(ctx));
	    sql.append("inner join c_elementvalue eleval3 ON eleval3.c_elementvalue_id = eleval4.parentelementvalue_id and eleval3.ad_client_id = ? and eleval3.isactive = 'Y' ");
	    params.add(Env.getAD_Client_ID(ctx));
	    sql.append("inner join c_elementvalue eleval2 ON eleval2.c_elementvalue_id = eleval3.parentelementvalue_id and eleval2.ad_client_id = ? and eleval2.isactive = 'Y' ");
	    params.add(Env.getAD_Client_ID(ctx));
	    sql.append("inner join c_elementvalue eleval1 ON eleval1.c_elementvalue_id = eleval2.parentelementvalue_id and eleval1.ad_client_id = ? and eleval1.isactive = 'Y' ");
	    sql.append("left join todo_ct4 c on eleval4.c_elementvalue_id = c.e4i ");
	    params.add(Env.getAD_Client_ID(ctx));
	    sql.append("where eleval4.ad_client_id = ? ");
	    sql.append("and eleval4.isactive = 'Y' ");
	    sql.append("), todop as ");
	    sql.append("( ");
	    params.add(yearS);
	    sql.append("select *, ? as year  ");
	    sql.append("from todo ");
	    sql.append("), cta3 as ");
	    sql.append("( ");
	    sql.append("select distinct  ");
	    sql.append("e1v as orden ");
	    sql.append(", e2v as orden2  ");
	    sql.append(", e3v as orden3 ");
	    sql.append(", '0' as orden4 ");
	    sql.append(", e3g as cta_gpo ");
	    sql.append(", e3v as cta_value ");
	    sql.append(", e3n as cta_name ");
	    sql.append(", e3i as cta_id ");
	    sql.append(", sum(coalesce(saldo_inicial,0)) over (w) as saldo_inicial ");
	    sql.append(", sum(coalesce(debe,0)) over (w) as debe ");
	    sql.append(", sum(coalesce(haber,0)) over (w) as haber ");
	    sql.append(", sum(coalesce(saldo_final,0)) over (w) as saldo_final ");
	    sql.append(", 3::numeric as nivel ");
	    sql.append("from todop  ");
	    sql.append(" window w as (partition by e3i) ");
	    sql.append("), cta4 as ");
	    sql.append("( ");
	    sql.append("select distinct  ");
	    sql.append("e1v as orden ");
	    sql.append(", e2v as orden2  ");
	    sql.append(", e3v as orden3 ");
	    sql.append(", e4v as orden4 ");
	    sql.append(", e4g as cta_gpo ");
	    sql.append(", e4v as cta_value ");
	    sql.append(", e4n as cta_name ");
	    sql.append(", e4i as cta_id ");
	    sql.append(", coalesce(saldo_inicial,0) as saldo_inicial ");
		sql.append(", coalesce(debe,0) as debe ");
		sql.append(", coalesce(haber,0) as haber ");
		sql.append(", coalesce(saldo_final,0) as saldo_final ");
		sql.append(", 4::numeric as nivel ");
		sql.append("from todop  ");
		sql.append("), x as ");
		sql.append("( ");
		sql.append("select * from cta3 ");
		sql.append("union all ");
		sql.append("select * from cta4 ");
		sql.append("), y as ");
		sql.append("( ");
		sql.append("select * ");
		sql.append("from x ");
		sql.append(" order by orden asc, orden2 asc, orden3 asc, orden4 asc  ");
		sql.append(") select cta_id, cta_name, cta_value, saldo_inicial, debe, haber, saldo_final ");
		sql.append("from y ");
			
			if (agrupar) {
				sql.append("where case when 'si' = 'si' then saldo_inicial <> 0 or debe > 0 or haber > 0 end ");
			}

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				DB.setParameters(pstmt, params);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					XmlComponents component = new XmlComponents();
					component.setAcctId(rs.getInt("cta_id"));
					component.setAccount(rs.getString("cta_value"));
					component.setDescription(rs.getString("cta_name"));
					component.setSaldoInicial(rs.getBigDecimal("saldo_inicial").setScale(2, BigDecimal.ROUND_HALF_UP));
					component.setDebe(rs.getBigDecimal("debe").setScale(2, BigDecimal.ROUND_HALF_UP));
					component.setHaber(rs.getBigDecimal("haber").setScale(2, BigDecimal.ROUND_HALF_UP));
					component.setSaldoFinal(rs.getBigDecimal("saldo_final").setScale(2, BigDecimal.ROUND_HALF_UP));
					list.add(component);
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, null, e);
			} finally {
				DB.close(rs, pstmt);
			}
		return list;

	}

	/**
	 * Regresará el número de regisros que hay sin codigo agrupador
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getRecordsWithoutCode(final Properties ctx, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" with cuentas as ( ");
		sql.append("select ");
		sql.append("eleval3.c_elementvalue_id as e3i, eleval3.value as e3v, eleval3.name as e3n, eleval3.accounttype as e3t, eleval3.parentelementvalue_id as e3p, eleval3.accountsign as e3s ");
		sql.append(", eleval3.exme_groupacct_id as e3gi ");
		sql.append(", (select value || ' - ' || name from exme_groupacct ga where ga.exme_groupacct_id = eleval3.exme_groupacct_id) as e3g ");
		sql.append(", eleval4.c_elementvalue_id as e4i, eleval4.value as e4v, eleval4.name as e4n, eleval4.accounttype as e4t, eleval4.parentelementvalue_id as e4p, eleval4.accountsign as e4s ");
		sql.append(", eleval4.exme_groupacct_id as e4gi ");
		sql.append(", (select value || ' - ' || name from exme_groupacct ga where ga.exme_groupacct_id = eleval4.exme_groupacct_id) as e4g ");
		sql.append("from c_elementvalue eleval4 ");
		sql.append("inner join c_elementvalue eleval3 ON eleval3.c_elementvalue_id = eleval4.parentelementvalue_id and eleval3.ad_client_id = ? ");
		sql.append("where eleval4.ad_client_id = ? ");
		sql.append("and eleval4.acct_level = 4 ");
		sql.append("and eleval4.isactive = 'Y' ");
		sql.append("and eleval3.isactive = 'Y'), ");
		sql.append(" cta3 as ( ");
		sql.append(" select count (distinct e3i) as falta_configuracion ");
		sql.append(" from cuentas ");
		sql.append(" where e3gi is null  ");
		sql.append(" ),cta4 as ( ");
		sql.append(" select count (distinct e4i) as falta_configuracion ");
		sql.append(" from cuentas ");
		sql.append(" where e4gi is null  ) ");
		sql.append(" select coalesce(cta3.falta_configuracion,0) + coalesce(cta4.falta_configuracion,0) as falta_configuracion  ");
		sql.append(" from cta4, cta3 ");
		final BigDecimal val = DB.getSQLValueBDEx(trxName, sql.toString(), Env.getAD_Client_ID(ctx), Env.getAD_Client_ID(ctx));
		return val == null ? Env.ZERO : val;
	}
	
	public static boolean isCorrectSetting(final Properties ctx, final String trxName){
		final StringBuilder sql = new StringBuilder();
		sql.append("select distinct ");
		sql.append("case when ev.accounttype = 'O' and ev.accountsign = 'C' and count(fa.fact_acct_id) over () = 0  ");
		sql.append("then 'T'  ");
		sql.append("else 'F'  ");
		sql.append("end as ervalidacion ");
		sql.append("from C_AcctSchema_GL agl ");
		sql.append("inner join C_ValidCombination vc on agl.retainedearning_acct = vc.C_ValidCombination_id ");
		sql.append("inner join c_elementvalue ev on ev.c_elementvalue_id = vc.account_id ");
		sql.append("left join fact_acct fa      on fa.account_id = ev.c_elementvalue_id ");
		sql.append("where ev.ad_client_id = ? ");
		final String value = DB.getSQLValueString(trxName, sql.toString(), Env.getAD_Client_ID(ctx));
		return "T".equals(value);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param periodId
	 * @param trxName
	 * @return 
	 */
	public static Balanza getXmlData(Properties ctx, int periodId,int years, boolean agrupar, String trxName) {
		Balanza balanza = new Balanza();

		MOrgInfo orgInfo = MOrgInfo.get(Env.getCtx(), Env.getAD_Org_ID(Env.getCtx()));
		MPeriod mPeriod = new MPeriod(Env.getCtx(), periodId, null);
		
		balanza.setVersion("1.1");
		balanza.setAnio(Integer.valueOf(mPeriod.getC_Year().getYear()));
		balanza.setMes(StringUtils.leftPad(Integer.toString(mPeriod.getPeriodNo()), 2, '0'));
		balanza.setRFC(orgInfo.getTaxID());

			for (final XmlComponents obj : MElementValue.getXmlBalance(ctx, periodId, years,agrupar, null)) {

				final XmlComponents component = (XmlComponents) obj;

				com.ecaresoft.acct.xml.balanza.Balanza.Ctas ctas = new com.ecaresoft.acct.xml.balanza.Balanza.Ctas();
				ctas.setSaldoIni(component.getSaldoInicial());
				ctas.setNumCta(component.getAccount());
				ctas.setDebe(component.getDebe());
				ctas.setHaber(component.getHaber());
				ctas.setSaldoFin(component.getSaldoFinal());

				balanza.getCtas().add(ctas);
			}
		return balanza;
	}

	/**
	 * Validaciones xml Balanza
	 * @author jcervantes
	 *
	 */
	public static class Prueba implements XmlValidator {

		private Balanza balanza;
		public static final String RFC_REGEX = "[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?";
		public static final String[] MONTHS = new String[] { "01", "02", "03","04", "05", "06", "07", "08", "09", "10", "11", "12" };
		public static final String[] TYPES = new String[] { "AF", "FC", "DE","CO" };
		public static final String[] TIPOENVIO = new String[] {"N", "C"};

		public Prueba(Balanza balanza) {
			this.balanza = balanza;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ecaresoft.acct.xml.XmlValidator#validate()
		 */
		public ErrorList validate() {
			ErrorList errorList = new ErrorList();

			if (!Pattern.matches(RFC_REGEX, balanza.getRFC())) {
				errorList.add(Error.VALIDATION_ERROR,Utilerias.getAppMsg(Env.getCtx(), "msj.rfcInvalido"));
			}

			if (!Arrays.asList(MONTHS).contains(balanza.getMes())) {
				errorList.add(Error.VALIDATION_ERROR,Utilerias.getAppMsg(Env.getCtx(), "msj.mesInvalido"));
			}

			if (balanza.getAnio() < 2015 || balanza.getAnio() > 2099) {
				errorList.add(Error.VALIDATION_ERROR,Utilerias.getAppMsg(Env.getCtx(), "msj.anoInvalido"));
			}
			
			if (!Arrays.asList(TIPOENVIO).contains(balanza.getTipoEnvio())) {
				errorList.add(Error.VALIDATION_ERROR,Utilerias.getAppMsg(Env.getCtx(), "msj.anoInvalido"));
			}
			
			for (com.ecaresoft.acct.xml.balanza.Balanza.Ctas ctas : balanza.getCtas()) {
				int lengthNumCta = StringUtils.length(ctas.getNumCta());

				if (lengthNumCta < 1 || lengthNumCta > 100) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.ctaInvalida", ctas.getNumCta()));
				}
				if (ctas.getDebe().compareTo(BigDecimal.ZERO) < 0) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.debeIncorrecto", ctas.getNumCta()));
				}

				if (ctas.getHaber().compareTo(BigDecimal.ZERO) < 0) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.haberIncorrecto", ctas.getNumCta()));
				}

			}

			return errorList;
		}

	}
	
	/**
	 * Obtiene pojo de XML de auxiliar de cuentas
	 * 
	 * @param ctx
	 *            Contextp
	 * @param year
	 *            Año
	 * @param period
	 *            Periodo
	 * @param trxName
	 *            Trx
	 * @return Auxiliar
	 */
	public static AuxiliarCtas getAuxAcctXML(Properties ctx, int year, int period, String trxName) {
		AuxiliarCtas aux = new AuxiliarCtas();
		MOrgInfo orgInfo = MOrgInfo.get(ctx, Env.getAD_Org_ID(ctx));
		MPeriod mPeriod = new MPeriod(Env.getCtx(), period, null);

		aux.setVersion("1.1");
		aux.setRFC(orgInfo.getTaxID());

		aux.setAnio(Integer.valueOf(mPeriod.getC_Year().getYear()));
		aux.setMes(StringUtils.leftPad(Integer.toString(mPeriod.getPeriodNo()), 2, '0'));

		for (final XmlComponents component : MElementValue.getXmlBalance(ctx, period, year, true, null)) {
			Cuenta cuenta = new Cuenta();
			cuenta.setNumCta(component.getAccount());
			cuenta.setDesCta(StringEscapeUtils.escapeXml(component.getDescription()));
			cuenta.setSaldoIni(component.getSaldoInicial());
			cuenta.setSaldoFin(component.getSaldoFinal());
			
			cuenta.getDetalleAux().addAll(MEXMEPolicyInfo.getDetalleAux(ctx, period, component.getAcctId(), trxName));

			if (!cuenta.getDetalleAux().isEmpty()) {
				aux.getCuenta().add(cuenta);
			}
		}

		return aux;
	}
	
} // MElementValue
