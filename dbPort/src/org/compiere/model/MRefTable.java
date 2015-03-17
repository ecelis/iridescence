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
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MRefTable extends X_AD_Ref_Table {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MRefTable.class);

	/**
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param AD_Reference_ID
	 *            id
	 * @param trxName
	 *            trx
	 */
	public MRefTable(Properties ctx, int AD_Reference_ID, String trxName) {
		super(ctx, AD_Reference_ID, trxName);
		if (AD_Reference_ID == 0) {
			// setAD_Table_ID (0);
			// setAD_Display (0);
			// setAD_Key (0);
			setEntityType(ENTITYTYPE_UserMaintained); // U
			setIsValueDisplayed(false);
		}
	} // MRefTable

	/**
	 * Load Cosntructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            trx
	 */
	public MRefTable(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MRefTable

	/**
	 * Gets RefTable record from AD_Reference_ID
	 * 
	 * @param ctx
	 * @param AD_Reference_ID
	 * @param trxName
	 * @return MRefTable
	 */
	public static MRefTable get(Properties ctx, int AD_Reference_ID, String trxName) {
		MRefTable retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT r.*, t.tablename, d.columnname as display, k.columnname as key ");
			sql.append("FROM AD_Ref_Table r ");
			sql.append("INNER JOIN AD_Table t ON (t.AD_Table_ID = r.AD_Table_ID) ");
			sql.append("INNER JOIN AD_Column d ON (d.AD_Column_ID = r.AD_Display) ");
			sql.append("INNER JOIN AD_Column k ON (k.AD_Column_ID = r.AD_Key) ");
			sql.append("WHERE r.AD_Reference_ID = ? and r.isActive = 'Y' ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, AD_Reference_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MRefTable(ctx, rs, trxName);
				retValue.setTableName(rs.getString("tableName"));
				retValue.setDisplayColumn(rs.getString("display"));
				retValue.setKeyColumn(rs.getString("key"));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	private String tableName = null;
	private String displayColumn = null;
	private String keyColumn = null;

	public String getTableName() {
		if (tableName == null)
			tableName = MTable.getTableName(getCtx(), getAD_Table_ID());
		return tableName;
	}

	public String getDisplayColumn() {
		if (displayColumn == null)
			displayColumn = MColumn.getColumnName(getCtx(), getAD_Display());
		return displayColumn;
	}

	public String getKeyColumn() {
		if (keyColumn == null)
			keyColumn = MColumn.getColumnName(getCtx(), getAD_Key());
		return keyColumn;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setDisplayColumn(String displayColumn) {
		this.displayColumn = displayColumn;
	}

	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
	}

} // MRefTable
