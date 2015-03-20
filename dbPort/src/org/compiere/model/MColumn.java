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

import java.sql.*;
import java.util.*;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.*;

/**
 *	Persistent Column Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MColumn.java,v 1.6 2006/08/09 05:23:49 jjanke Exp $
 */
public class MColumn extends X_AD_Column
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 	Get M_Column from Cache
	 *	@param ctx context
	 * 	@param AD_Column_ID id
	 *	@return M_Column
	 */
	public static MColumn get (Properties ctx, int AD_Column_ID)
	{
		Integer key = new Integer (AD_Column_ID);
		MColumn retValue = (MColumn) s_cache.get (key);
		if (retValue != null ){
				//Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
//				&& (Env.getContextAsInt(ctx, "#AD_Session_ID")==Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) //lama
			return retValue;
		}
		retValue = new MColumn (ctx, AD_Column_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**
	 * 	Get Column Name
	 *	@param ctx context
	 *	@param AD_Column_ID id
	 *	@return Column Name or null
	 */
	public static String getColumnName (Properties ctx, int AD_Column_ID)
	{
		MColumn col = MColumn.get(ctx, AD_Column_ID);
		if (col.get_ID() == 0)
			return null;
		return col.getColumnName();
	}	//	getColumnName
	
	/**	Cache						*/
	private static CCache<Integer,MColumn>	s_cache	= new CCache<Integer,MColumn>("AD_Column", 20);
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MColumn.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Column_ID
	 *	@param trxName transaction
	 */
	public MColumn (Properties ctx, int AD_Column_ID, String trxName)
	{
		super (ctx, AD_Column_ID, trxName);
		if (AD_Column_ID == 0)
		{
		//	setAD_Element_ID (0);
		//	setAD_Reference_ID (0);
		//	setColumnName (null);
		//	setName (null);
		//	setEntityType (null);	// U
			setIsAlwaysUpdateable (false);	// N
			setIsEncrypted (false);
			setIsIdentifier (false);
			setIsKey (false);
			setIsMandatory (false);
			setIsParent (false);
			setIsSelectionColumn (false);
			setIsTranslated (false);
			setIsUpdateable (true);	// Y
			setVersion (Env.ZERO);
		}
	}	//	MColumn

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MColumn (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MColumn
	
	/**
	 * 	Parent Constructor
	 *	@param parent table
	 */
	public MColumn (MTable parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setAD_Table_ID (parent.getAD_Table_ID());
		setEntityType(parent.getEntityType());
	}	//	MColumn
	
	
	/**
	 * 	Is Standard Column
	 *	@return true for AD_Client_ID, etc.
	 */
	public boolean isStandardColumn()
	{
		String columnName = getColumnName();
		if (columnName.equals("AD_Client_ID") 
			|| columnName.equals("AD_Org_ID")
			|| columnName.equals("IsActive")
			|| columnName.startsWith("Created")
			|| columnName.startsWith("Updated") )
			return true;
		
		return false;
	}	//	isStandardColumn
	
	/**
	 * 	Is Virtual Column
	 *	@return true if virtual column
	 */
	public boolean isVirtualColumn()
	{
		String s = getColumnSQL();
		return s != null && s.length() > 0;
	}	//	isVirtualColumn
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		int displayType = getAD_Reference_ID();
		
		if (DisplayType.isLOB(displayType))	//	LOBs are 0
		{
			if (getFieldLength() != 0)
				setFieldLength(0);
		}
		else if (getFieldLength() == 0) 
		{
			if (DisplayType.isID(displayType))
				setFieldLength(10);
			else if (DisplayType.isNumeric (displayType))
				setFieldLength(14);
			else if (DisplayType.isDate (displayType))
				setFieldLength(7);
			else
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "FieldLength"));
			return false;
		}
		}
		
		/** Views are not updateable
		UPDATE AD_Column c
		SET IsUpdateable='N', IsAlwaysUpdateable='N'
		WHERE AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Table WHERE IsView='Y')
		**/
		
		//	Virtual Column
		if (isVirtualColumn())
		{
			if (isMandatory())
				setIsMandatory(false);
			if (isUpdateable())
				setIsUpdateable(false);
		}
		//	Updateable
		if (isParent() || isKey())
			setIsUpdateable(false);
		if (isAlwaysUpdateable() && !isUpdateable())
			setIsAlwaysUpdateable(false);
		//	Encrypted
		if (isEncrypted()) {
			int dt = getAD_Reference_ID();
			if (isKey() || isParent() || isStandardColumn() || isVirtualColumn() || isIdentifier() || isTranslated() || DisplayType.isLookup(dt)
			// || DisplayType.isLOB(dt)
					|| DisplayType.isID(dt) || DisplayType.isNumeric(dt) || DisplayType.isDate(dt) || DisplayType.isBoolean(dt)
			// || "DocumentNo".equalsIgnoreCase(getColumnName())
			// || "Value".equalsIgnoreCase(getColumnName())
			// || "Name".equalsIgnoreCase(getColumnName())
			) {
				// log.warning("Encryption not sensible - " + getColumnName());
				// setIsEncrypted(false);
				log.saveError(Utilerias.getMessage(getCtx(), null, "error.encriptacion"), Utilerias.getMessage(getCtx(), null, "msj.tipoNoValido"));
				return false;
			} else if (getColumnName().equals("Value") || getColumnName().equals("Description") || getColumnName().equals("DocumentNo")) {
				log.saveError(Utilerias.getMessage(getCtx(), null, "error.encriptacion"), Utilerias.getMessage(getCtx(), null, "columnaInvalida"));
				return false;
			}
		}
		
		//	Sync Terminology
		if ((newRecord || is_ValueChanged ("AD_Element_ID")) 
			&& getAD_Element_ID() != 0)
		{
			M_Element element = new M_Element (getCtx(), getAD_Element_ID (), get_TrxName());
			setColumnName (element.getColumnName());
			setName (element.getName());
			setDescription (element.getDescription());
			setHelp (element.getHelp());
		}
		return true;
	}	//	beforeSave

	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//	Update Fields
		if (!newRecord)
		{
			if (   is_ValueChanged(MColumn.COLUMNNAME_Name)
				|| is_ValueChanged(MColumn.COLUMNNAME_Description)
				|| is_ValueChanged(MColumn.COLUMNNAME_Help)
				) {
			StringBuffer sql = new StringBuffer("UPDATE AD_Field SET Name=")
				.append(DB.TO_STRING(getName()))
				.append(", Description=").append(DB.TO_STRING(getDescription()))
				.append(", Help=").append(DB.TO_STRING(getHelp()))
				.append(" WHERE AD_Column_ID=").append(get_ID())
				.append(" AND IsCentrallyMaintained='Y'");
			int no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("afterSave - Fields updated #" + no);
		}
		}
		return success;
	}	//	afterSave
	
	/**
	 * 	Get SQL Add command
	 *	@param table table
	 *	@return sql
	 */
	public String getSQLAdd (MTable table)
	{
		StringBuffer sql = new StringBuffer ("ALTER TABLE ")
			.append(table.getTableName())
			.append(" ADD ").append(getSQLDDL());
		String constraint = getConstraint(table.getTableName());
		if (constraint != null && constraint.length() > 0) {
			sql.append(DB.SQLSTATEMENT_SEPARATOR).append("ALTER TABLE ")
			.append(table.getTableName())
			.append(" ADD ").append(constraint);
		}
		return sql.toString();
	}	//	getSQLAdd

	/**
	 * 	Get SQL DDL
	 *	@return columnName datataype ..
	 */
	public String getSQLDDL()
	{
		if (isVirtualColumn())
			return null;
		
		StringBuffer sql = new StringBuffer (getColumnName())
			.append(" ").append(getSQLDataType());
		//	Default
		if (getDefaultValue() != null && getDefaultValue().length() > 0)
		{
			sql.append(" DEFAULT ");
			// if (DisplayType.isText(getAD_Reference_ID()))
				// sql.append(DB.TO_STRING(getDefaultValue()));
			// else
				// sql.append(getDefaultValue());
			String defaultValue = getDefaultValue();
			if (defaultValue != null 
				&& defaultValue.length() > 0
				&& defaultValue.indexOf('@') == -1)		//	no variables
			{
				if (DisplayType.isText(getAD_Reference_ID()) 
					|| getAD_Reference_ID() == DisplayType.List
					|| getAD_Reference_ID() == DisplayType.YesNo
					// Two special columns: Defined as Table but DB Type is String 
					|| getColumnName().equals("EntityType") || getColumnName().equals("AD_Language")
					|| (getAD_Reference_ID() == DisplayType.Button &&
						!(getColumnName().endsWith("_ID"))))
				{
					if (!defaultValue.startsWith("'") && !defaultValue.endsWith("'"))
						defaultValue = DB.TO_STRING(defaultValue);
				}
			} else {
				defaultValue = " NULL ";
			}
			sql.append(defaultValue);
		}
		//	Inline Constraint
		if (getAD_Reference_ID() == DisplayType.YesNo)
			sql.append(" CHECK (").append(getColumnName()).append(" IN ('Y','N'))");

		//	Null
		if (isMandatory())
			sql.append(" NOT NULL");
		return sql.toString();
	}	//	getSQLDDL	
	
	/**
	 * 	Get SQL Modify command
	 *	@param table table
	 *	@param setNullOption generate null / not null statement
	 *	@return sql separated by ;
	 */
	public String getSQLModify (MTable table, boolean setNullOption)
	{
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlBase = new StringBuffer ("ALTER TABLE ")
			.append(table.getTableName())
			.append(" MODIFY ").append(getColumnName());
		
		//	Default
		StringBuffer sqlDefault = new StringBuffer(sqlBase)
			.append(" ").append(getSQLDataType())
			.append(" DEFAULT ");
		String defaultValue = getDefaultValue();
		if (defaultValue != null 
			&& defaultValue.length() > 0
			&& defaultValue.indexOf('@') == -1)		//	no variables
		{
			if (DisplayType.isText(getAD_Reference_ID()) 
				|| getAD_Reference_ID() == DisplayType.List
				|| getAD_Reference_ID() == DisplayType.YesNo
				// Two special columns: Defined as Table but DB Type is String 
				|| getColumnName().equals("EntityType") || getColumnName().equals("AD_Language")
				|| (getAD_Reference_ID() == DisplayType.Button &&
						!(getColumnName().endsWith("_ID"))))
			{
				if (!defaultValue.startsWith("'") && !defaultValue.endsWith("'"))
					defaultValue = DB.TO_STRING(defaultValue);
			}
			sqlDefault.append(defaultValue);
		}
		else
		{
			sqlDefault.append(" NULL ");
			defaultValue = null;
		}
		sql.append(sqlDefault);
		
		//	Constraint

		//	Null Values
		if (isMandatory() && defaultValue != null && defaultValue.length() > 0)
		{
			StringBuffer sqlSet = new StringBuffer("UPDATE ")
				.append(table.getTableName())
				.append(" SET ").append(getColumnName())
				.append("=").append(defaultValue)
				.append(" WHERE ").append(getColumnName()).append(" IS NULL");
			sql.append(DB.SQLSTATEMENT_SEPARATOR).append(sqlSet);
		}
		
		//	Null
		if (setNullOption)
		{
			StringBuffer sqlNull = new StringBuffer(sqlBase);
			if (isMandatory())
				sqlNull.append(" NOT NULL");
			else
				sqlNull.append(" NULL");
			sql.append(DB.SQLSTATEMENT_SEPARATOR).append(sqlNull);
		}
		//
		return sql.toString();
	}	//	getSQLModify

	/**
	 * 	Get SQL Data Type
	 *	@return e.g. NVARCHAR2(60)
	 */
	public String getSQLDataType()
	{
		String columnName = getColumnName();
		int dt = getAD_Reference_ID();
		return DisplayType.getSQLDataType (dt, columnName, getFieldLength());
	}	//	getSQLDataType
	
	/**
	 * 	Get SQL Data Type
	 *	@return e.g. NVARCHAR2(60)
	 */
	/*
	private String getSQLDataType()
	{
		int dt = getAD_Reference_ID();
		if (DisplayType.isID(dt) || dt == DisplayType.Integer)
			return "NUMBER(10)";
		if (DisplayType.isDate(dt))
			return "DATE";
		if (DisplayType.isNumeric(dt))
			return "NUMBER";
		if (dt == DisplayType.Binary)
			return "BLOB";
		if (dt == DisplayType.TextLong)
			return "CLOB";
		if (dt == DisplayType.YesNo)
			return "CHAR(1)";
		if (dt == DisplayType.List)
			return "NVARCHAR2(" + getFieldLength() + ")";
		if (dt == DisplayType.Button)
			return "CHAR(" + getFieldLength() + ")";
		else if (!DisplayType.isText(dt))
			log.severe("Unhandled Data Type = " + dt);
			
		return "NVARCHAR2(" + getFieldLength() + ")";
	}	//	getSQLDataType
	*/
	
	/**
	 * 	Get Table Constraint
	 *	@param tableName table name
	 *	@return table constraint
	 */
	public String getConstraint(String tableName)
	{
		if (isKey()) {
			String constraintName;
			if (tableName.length() > 26)
				// Oracle restricts object names to 30 characters
				constraintName = tableName.substring(0, 26) + "_Key";
			else
				constraintName = tableName + "_Key";
			return "CONSTRAINT " + constraintName + " PRIMARY KEY (" + getColumnName() + ")";
		}
		/**
		if (getAD_Reference_ID() == DisplayType.TableDir 
			|| getAD_Reference_ID() == DisplayType.Search)
			return "CONSTRAINT " ADTable_ADTableTrl
				+ " FOREIGN KEY (" + getColumnName() + ") REFERENCES "
				+ AD_Table(AD_Table_ID) ON DELETE CASCADE
		**/
		
		return "";
	}	//	getConstraint
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("MColumn[");
		sb.append (get_ID()).append ("-").append (getColumnName()).append ("]");
		return sb.toString ();
	}	//	toString
	
	//begin vpj-cd e-evolution
	/**
	 * 	get Column ID
	 *  @param String windowName
	 *	@param String columnName
	 *	@return int retValue
	 */
	public static int getColumn_ID(String tableName, String columnName) {
//		int m_table_id = MTable.getTable_ID(TableName);
//		if (m_table_id == 0)
//			return 0;
			
//		int retValue = 0;
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT AD_Column.AD_Column_ID FROM AD_Column ");
		sql.append("INNER JOIN AD_Table ON (AD_Column.AD_Table_ID=AD_Table.AD_Table_ID) ");
		sql.append("WHERE AD_Table.TableName=?");
		sql.append("  AND AD_Column.ColumnName=?");
		return DB.getSQLValue(null, sql.toString(), tableName, columnName);
//		try
//		{
//			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
//			pstmt.setInt(1, m_table_id);
//			pstmt.setString(2, columnName);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next())
//				retValue = rs.getInt(1);
//			rs.close();
//			pstmt.close();
//		}
//		catch (SQLException e)
//		{
//			s_log.log(Level.SEVERE, SQL, e);
//			retValue = -1;
//		}
//		return retValue;
	}
	//end vpj-cd e-evolution
	
	/**
	* Get Table Id for a column
	* @param ctx context
	* @param AD_Column_ID id
	* @param trxName transaction
	* @return MColumn
	*/
	public static int getTable_ID(Properties ctx, int AD_Column_ID, String trxName)
	{
		String sqlStmt = "SELECT AD_Table_ID FROM AD_Column WHERE AD_Column_ID=?";
		return DB.getSQLValue(trxName, sqlStmt, AD_Column_ID);
	}
	
	@Override
	protected boolean beforeDelete() {
		
		if (getColumnName().equals("IsGenerateMessage")
				&& getAD_Table().isGenerateMessage()) {
			log.saveError("Error", Msg.getMsg(getCtx(), "CannotDelete"));
			return false;
		}
		
		
		return true;		
	}
	
	private String m_trlName = null;

	/**
	 * Nombre traducido
	 * 
	 * @return
	 */
	public String getTrlName() {
		if (m_trlName == null) {
			m_trlName = get_Translation(COLUMNNAME_Name, Env.getAD_Language(getCtx()));
			if (m_trlName == null) {
				m_trlName = getName();
			}
		}
		return m_trlName;
	}
	
	// Expert: odelarosa / lama
	private Boolean referencePatient;

	public boolean isReferencePatient() {
		if (referencePatient == null) {
			final int dType = getAD_Reference_ID();
			referencePatient = false;
			// Si la columna es ID
			if (DisplayType.isLookup(dType) || dType == DisplayType.ID) { // Lama DisplayType.isID(dType)
				// Si la referencia es Table o Search
				if (dType == DisplayType.Table || dType == DisplayType.Search) {
					final X_AD_Reference ref = new X_AD_Reference(getCtx(), getAD_Reference_Value_ID(), null);
					// Si la referencia es a EXME_Paciente
					if (ref.getName() != null && ref.getName().equals(X_EXME_Paciente.Table_Name)) {
						referencePatient = true;
					} else {
						final int index = StringUtils.indexOf(getColumnName(), "_ID");
						// Si la referencia es a EXME_Paciente
						// if (index > 0 && X_EXME_Paciente.Table_Name.equals(getColumnName().substring(0, index))) {
						referencePatient = index > 0
								&& X_EXME_Paciente.Table_Name.equals(getColumnName().substring(0, index));
						// }
					}
					// Si la referencia es TableDir
				} else if (dType == DisplayType.TableDir || dType == DisplayType.ID) {
					final int index = StringUtils.indexOf(getColumnName(), "_ID");
					// Si la referencia es a EXME_Paciente
					// if (index > 0 && X_EXME_Paciente.Table_Name.equals(getColumnName().substring(0, index))) {
					referencePatient = index > 0
							&& X_EXME_Paciente.Table_Name.equals(getColumnName().substring(0, index));
					// }
				}
			}
		}
		return referencePatient;
	}

	public void setReferencePatient(boolean referencePatient) {
		this.referencePatient = referencePatient;
	}
	
	public static boolean isEncrypted(Properties ctx, String tableName, String columnName, String trxName) {
		boolean retValue = false;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  c.isencrypted ");
		sql.append("FROM ");
		sql.append("  ad_column c ");
		sql.append("WHERE ");
		sql.append("  ad_table_id = ");
		sql.append("  (SELECT ");
		sql.append("      ad_table_id ");
		sql.append("    FROM ");
		sql.append("      ad_table ");
		sql.append("    WHERE ");
		sql.append("      upper(tablename) = ");
		sql.append("? ) AND ");
		sql.append("  upper(columnname) = ? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, StringUtils.upperCase(tableName));
			pstmt.setString(2, StringUtils.upperCase(columnName));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = "Y".equals(rs.getString(1));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs);
			DB.close(null, pstmt);
		}

		return retValue;
	}
	
	public static String getColumnName(int AD_Column_ID) {
		return DB.getSQLValueString(null, "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=? ", AD_Column_ID);
	} //	getColumnName

	/**
	 * El largo del campo
	 * @param pTableName : Nombre de table
	 * @param pTableName : Nombre de columna
	 * @return
	 */
	public static int fieldLength(final String pTableName, final String pColumnName) {
		int length = 2000;
		MColumn column = new MColumn(Env.getCtx(), MColumn.getColumn_ID(pTableName, pColumnName), null);
		if(column!=null) {
			length = column.getFieldLength();	
		}
		return length;
	}
	
	public static String getColumnName(Properties ctx, String tableName, String columName, String trxName) {
		String retValue = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  Ad_Column C ");
		sql.append("  INNER JOIN Ad_Table T ");
		sql.append("  ON C.Ad_Table_Id = T.Ad_Table_Id ");
		sql.append("WHERE ");
		sql.append("  UPPER(c.columnname) = ? AND ");
		sql.append("  UPPER(t.tableName) = ? ");


		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, StringUtils.upperCase(columName));
			pstmt.setString(2, StringUtils.upperCase(tableName));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				MColumn column = new MColumn(ctx, rs, null);
				retValue = column.getName();
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}
	
	/**
	 * 	get Column ID
	 *  @param String tableId
	 *	@param String columnName
	 *	@return int retValue
	 */
	public static int getColumn_ID(int tableId, String columnName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT AD_Column_ID FROM AD_Column ");
		sql.append("WHERE AD_Table_ID=?");
		sql.append("  AND ColumnName=?");
		return DB.getSQLValue(null, sql.toString(), tableId, columnName);
	}

	/**
	 * Tipo de dato para reporte
	 * 
	 * @return
	 */
	public String getReportDataType() {
		String type = null;
		if (DisplayType.isNumeric(getAD_Reference_ID()) || DisplayType.isID(getAD_Reference_ID())) {
			type = Integer.toString(Types.INTEGER);
		} else if (DisplayType.isDate(getAD_Reference_ID())) {
			type = Integer.toString(Types.TIME);
		} else {
			type = Integer.toString(Types.NVARCHAR);
		}
		return type;
	}
	
	public M_Element getElement(){
		return new M_Element (getCtx(), getAD_Element_ID (), get_TrxName());
	}
	
	public static List<KeyNamePair> getColumnsKN(Properties ctx, int AD_Table_ID, String whereClause, String trxName, Object... params) {
		List<KeyNamePair> list = new ArrayList<KeyNamePair>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT AD_COLUMN_ID, NAME FROM AD_COLUMN WHERE ISACTIVE = 'Y' AND AD_TABLE_ID = ? ")
		   .append(whereClause != null && whereClause.length() > 0 ? whereClause : "")
		   .append(" ORDER BY AD_COLUMN_ID ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			pstmt.setInt(index++, AD_Table_ID);
			if (params.length > 0) {
				for (Object param : params) {
					DB.setParameter(pstmt, index++, param);
				}
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt("AD_COLUMN_ID"), rs.getString("NAME")));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getColumnsKN", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}
}	//	MColumn
