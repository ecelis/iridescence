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
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.NamePair;
import org.compiere.util.vo.ChangeLogVO;

/**
 * Change Log Model
 * 
 * @author Jorg Janke
 * @version $Id: MChangeLog.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
public class MChangeLog extends X_AD_ChangeLog {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Do we track changes for this table
	 * 
	 * @param AD_Table_ID
	 *            table
	 * @return true if changes are tracked
	 */
	public static boolean isLogged(Properties ctx, int AD_Table_ID) {
		if (s_changeLog == null || s_changeLog.size() == 0)
			fillChangeLog(ctx);
		//
		int index = s_changeLog.indexOf(AD_Table_ID);
		return index >= 0;
	} // trackChanges

	/**
	 * Fill Log with tables to be logged
	 */
	private static void fillChangeLog(Properties ctx) {
		ArrayList<Integer> list = new ArrayList<Integer>(40);
		String sql = "SELECT t.AD_Table_ID FROM AD_Table t "
				+ "WHERE t.IsChangeLog='Y'" // also inactive
				+ " OR EXISTS (SELECT * FROM AD_Column c " + "WHERE t.AD_Table_ID=c.AD_Table_ID AND c.ColumnName='EntityType') "
				+ "ORDER BY t.AD_Table_ID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(rs.getInt(1));

			// Forzamos a que se guarde log de las tablas relacionadas al paciente
//			for (String id : MEXMETablasPaciente.getTables(ctx, true, null)) {
//				list.add(Integer.parseInt(id));
//			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		// Convert to Array
		s_changeLog = new ArrayList<Integer>();
		s_changeLog.addAll(list);
		s_log.info("#" + s_changeLog.size());
	} // fillChangeLog

	/** Change Log */
	private static List<Integer> s_changeLog = new ArrayList<Integer>();
	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MChangeLog.class);
	/** NULL Value */
	public static String NULL = "NULL";

	/**************************************************************************
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MChangeLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MChangeLog

	/**
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param AD_ChangeLog_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MChangeLog(Properties ctx, int AD_ChangeLog_ID, String trxName) {
		super(ctx, 0, trxName);
	} // MChangeLog

	/**
	 * Preserved for backward compatibility
	 * 
	 * @deprecated
	 */
	public MChangeLog(Properties ctx, int AD_ChangeLog_ID, String TrxName, int AD_Session_ID, int AD_Table_ID, int AD_Column_ID, int Record_ID,
			int AD_Client_ID, int AD_Org_ID, Object OldValue, Object NewValue) {
		this(ctx, AD_ChangeLog_ID, TrxName, AD_Session_ID, AD_Table_ID, AD_Column_ID, Record_ID, AD_Client_ID, AD_Org_ID, OldValue, NewValue,
				(String) null /* event */);
	} // MChangeLog

	/**
	 * Full Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param AD_ChangeLog_ID
	 *            0 for new change log
	 * @param TrxName
	 *            transaction
	 * @param AD_Session_ID
	 *            session
	 * @param AD_Table_ID
	 *            table
	 * @param AD_Column_ID
	 *            column
	 * @param Record_ID
	 *            record
	 * @param AD_Client_ID
	 *            client
	 * @param AD_Org_ID
	 *            org
	 * @param OldValue
	 *            old
	 * @param NewValue
	 *            new
	 */
	public MChangeLog(Properties ctx, int AD_ChangeLog_ID, String TrxName, int AD_Session_ID, int AD_Table_ID, int AD_Column_ID, int Record_ID,
			int AD_Client_ID, int AD_Org_ID, Object OldValue, Object NewValue, String event) {
		this(ctx, 0, TrxName);
		if (AD_ChangeLog_ID == 0) {
			AD_ChangeLog_ID = DB.getNextID(AD_Client_ID, Table_Name, TrxName);
			if (AD_ChangeLog_ID <= 0)
				log.severe("No NextID (" + AD_ChangeLog_ID + ")");
		}
		setAD_ChangeLog_ID(AD_ChangeLog_ID);
		setTrxName(TrxName);
		setAD_Session_ID(AD_Session_ID);
		//
		setAD_Table_ID(AD_Table_ID);
		setAD_Column_ID(AD_Column_ID);
		setRecord_ID(Record_ID);
		//
		setClientOrg(AD_Client_ID, AD_Org_ID);
		//
		setOldValue(OldValue);
		setNewValue(NewValue);
		setEventChangeLog(event);
	} // MChangeLog
	
	/**
	 * Guardar registro manualmente
	 * 
	 * @return Si se guardo o no
	 */
	public boolean saveLog() {
		boolean retValue = false;
		StringBuilder str = new StringBuilder("INSERT INTO AD_ChangeLog ");
		str.append("(AD_ChangeLog_ID, TrxName, AD_Session_ID, ");
		str.append("AD_Table_ID, AD_Column_ID, Record_ID, OldValue, ");
		str.append("NewValue, EventChangeLog, AD_Client_ID, AD_Org_ID, CREATEDBY, UPDATEDBY, ISACTIVE, EXME_Paciente_ID) ");
		str.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,'Y',?)");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(str.toString(), getTrxName());
			pstmt.setInt(1, getAD_ChangeLog_ID());
			pstmt.setString(2, getTrxName());
			pstmt.setInt(3, getAD_Session_ID());
			pstmt.setInt(4, getAD_Table_ID());
			pstmt.setInt(5, getAD_Column_ID());
			pstmt.setInt(6, getRecord_ID());
			pstmt.setString(7, getOldValue());
			pstmt.setString(8, getNewValue());
			pstmt.setString(9, getEventChangeLog());
			pstmt.setInt(10, getAD_Client_ID());
			pstmt.setInt(11, getAD_Org_ID());
			pstmt.setInt(12, Env.getAD_User_ID(getCtx()));
			pstmt.setInt(13, Env.getAD_User_ID(getCtx()));
			if (getEXME_Paciente_ID() > 0) {
				pstmt.setObject(14, getEXME_Paciente_ID());
			} else {
				pstmt.setObject(14, null);
			}
			int i = pstmt.executeUpdate();
			if (i > 0) {
				retValue = true;
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					s_log.log(Level.SEVERE, null, e);
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					s_log.log(Level.SEVERE, null, e);
				}
			}
		}
		return retValue;
	}

	/**
	 * Set Old Value
	 * 
	 * @param OldValue
	 *            old
	 */
	public void setOldValue(Object OldValue) {
		if (OldValue == null)
			super.setOldValue(NULL);
		else
			super.setOldValue(OldValue.toString());
	} // setOldValue

	/**
	 * Is Old Value Null
	 * 
	 * @return true if null
	 */
	public boolean isOldNull() {
		String value = getOldValue();
		return value == null || value.equals(NULL);
	} // isOldNull

	/**
	 * Set New Value
	 * 
	 * @param NewValue
	 *            new
	 */
	public void setNewValue(Object NewValue) {
		if (NewValue == null)
			super.setNewValue(NULL);
		else
			super.setNewValue(NewValue.toString());
	} // setNewValue

	/**
	 * Is New Value Null
	 * 
	 * @return true if null
	 */
	public boolean isNewNull() {
		String value = getNewValue();
		return value == null || value.equals(NULL);
	} // isNewNull

	private MTable table = null;
	private MColumn column = null;

	/**
	 * Nombre de la tabla
	 * 
	 * @return
	 */
	public String getTableName() {
		// if (table == null) {
		// setTable(new MTable(getCtx(), getAD_Table_ID(), getTrxName()));
		// }
		return getTable().getTableName();
	}

	/**
	 * Nombre de la columna
	 * 
	 * @return
	 */
	public String getColumnName() {
		// if (column == null) {
		// setColumn(new MColumn(getCtx(), getAD_Column_ID(), get_TrxName()));
		// }
		return getColumn().getColumnName();
	}

	/**
	 * Nombre de la columna
	 * 
	 * @return
	 */
	public String getTrlName() {
		// if (column == null) {
		// setColumn(new MColumn(getCtx(), getAD_Column_ID(), get_TrxName()));
		// }
		return getColumn().getTrlName();
	}

	/**
	 * Obtiene la lista de log segun un ID de sesión
	 * 
	 * @param ctx
	 * @param AD_Session_ID
	 * @param tableIni
	 * @param tableFin
	 * @param trxName
	 * @return
	 */
	public static List<ChangeLogVO> getLog(Properties ctx, int AD_Session_ID, String tableIni, String tableFin, String fechaIni, String fechaFin,
			String trxName) {
		StringBuilder st = new StringBuilder(
				"select log.ad_changelog_id as id, t.tableName, c.columnName, log.created, log.record_id, nvl(log.EXME_Paciente_ID, 0) as pac, log.oldValue, log.newValue, log.eventchangelog from AD_ChangeLog log ");
		st.append("inner join AD_Table t on t.AD_Table_ID = log.AD_Table_ID ");
		st.append("inner join AD_Column c on c.AD_Column_ID = log.AD_Column_ID ");
		st.append("where log.AD_Session_ID = ? ");
		st.append("and t.tablename >= ? and t.tablename <= ? ");
		if (fechaFin != null && fechaIni != null) {
			st.append("and log.created >= to_date(?,'DD/MM/YYYY HH24:MI') ");
			st.append("and log.created <= to_date(?,'DD/MM/YYYY HH24:MI') ");
		}
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "AD_ChangeLog", "log"));
		st.append(" order by log.created desc ");

		List<ChangeLogVO> lista = new ArrayList<ChangeLogVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, AD_Session_ID);
			pstmt.setString(2, tableIni);
			pstmt.setString(3, tableFin);
			if (fechaFin != null && fechaIni != null) {
				pstmt.setString(4, fechaIni);
				pstmt.setString(5, fechaFin);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ChangeLogVO vo = new ChangeLogVO();
				vo.setColumnName(rs.getString("columnName"));
				vo.setCreated(rs.getTimestamp("created"));
				vo.setIdColumn(new IDColumn(rs.getInt("id")));
				vo.setNewValue(rs.getString("newValue"));
				vo.setOldValue(rs.getString("oldValue"));
				vo.setRecordId(rs.getInt("record_id"));
				int pac = rs.getInt("pac");
				if (pac == 0) {
					vo.setPaciente("");
				} else {
					vo.setPaciente(String.valueOf(pac));
				}
				vo.setTableName(rs.getString("tableName"));
				vo.setType(MRefList.getListName(ctx, EVENTCHANGELOG_AD_Reference_ID, rs.getString("eventchangelog")));

				lista.add(vo);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lista;
	}

	public static List<ChangeLogVO> getLog(Properties ctx, int AD_Session_ID, String ids, String trxName) {
		StringBuilder st = new StringBuilder(
				"select log.ad_changelog_id as id, t.tableName, c.columnName, log.created, log.record_id, nvl(log.EXME_Paciente_ID, 0) as pac, log.oldValue, log.newValue, log.eventchangelog from AD_ChangeLog log ");
		st.append("inner join AD_Table t on t.AD_Table_ID = log.AD_Table_ID ");
		st.append("inner join AD_Column c on c.AD_Column_ID = log.AD_Column_ID ");
		st.append("where log.AD_Session_ID = ? ");
		st.append("and t.AD_Table_ID in (").append(ids).append(") ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "AD_ChangeLog", "log"));
		st.append(" order by log.created desc ");

		List<ChangeLogVO> lista = new ArrayList<ChangeLogVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, AD_Session_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ChangeLogVO vo = new ChangeLogVO();
				vo.setColumnName(rs.getString("columnName"));
				vo.setCreated(rs.getTimestamp("created"));
				vo.setIdColumn(new IDColumn(rs.getInt("id")));
				vo.setNewValue(rs.getString("newValue"));
				vo.setRecordId(rs.getInt("record_id"));
				int pac = rs.getInt("pac");
				if (pac == 0) {
					vo.setPaciente("");
				} else {
					vo.setPaciente(String.valueOf(pac));
				}
				vo.setTableName(rs.getString("tableName"));
				vo.setType(MRefList.getListName(ctx, EVENTCHANGELOG_AD_Reference_ID, rs.getString("eventchangelog")));
				lista.add(vo);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lista;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		return success;
	}

	private String oldValueStr = null;
	private String newValueStr = null;

	/**
	 * Gets the displayed string of old and new values
	 * Expert: Lama
	 */
	public void setvalues() {
		boolean oldValue = getOldValue() != null && !getOldValue().equals(MChangeLog.NULL);
		boolean newValue = getNewValue() != null && !getNewValue().equals(MChangeLog.NULL);
		if (!oldValue && !newValue) {
			return;
		}
		// True - False
		if (getColumn().getAD_Reference_ID() == DisplayType.YesNo) {
			if (newValue) {
				setNewValueStr(Msg.getMsg(Env.getCtx(), getNewValue()));
			}
			if (oldValue) {
				setOldValueStr(Msg.getMsg(Env.getCtx(), getOldValue()));
			}
		}
		//
		else if (!DisplayType.isLookup(getColumn().getAD_Reference_ID())) {
			return;
		}
		// List
		else if (getColumn().getAD_Reference_ID() == DisplayType.List) {
			if (newValue) {
				setNewValueStr(MRefList.getListName(getCtx(), getColumn().getAD_Reference_Value_ID(), getNewValue()));
			}
			if (oldValue) {
				setOldValueStr(MRefList.getListName(getCtx(), getColumn().getAD_Reference_Value_ID(), getOldValue()));
			}
		}
		// Lookup
		else if (DisplayType.isLookup(getColumn().getAD_Reference_ID())) {
			MLookup lookup;
			try {
				lookup = MLookupFactory.get(getCtx(), 0, getAD_Column_ID(), getColumn().getAD_Reference_ID(), Env.getLanguage(Env.getCtx()),
						getColumn().getColumnName(), getColumn().getAD_Reference_Value_ID(), getColumn().isParent(), null);
			} catch (Exception e) {
				s_log.log(Level.SEVERE, e.getMessage(), e);
				return;
			}
			if (oldValue) {
				NamePair pp = lookup.get(getOldValue());
				if (pp != null) {
					setOldValueStr(pp.getName());
				}
			}
			if (newValue) {
				NamePair pp = lookup.get(getNewValue());
				if (pp != null) {
					setNewValueStr(pp.getName());
				}
			}
		}
	}

	public String getOldValueStr() {
		if (oldValueStr == null && (getOldValue() != null && getOldValue().equals(MChangeLog.NULL))) {
			return "--";
		}
		return oldValueStr;
	}

	public void setOldValueStr(String oldValueStr) {
		this.oldValueStr = oldValueStr;
	}

	public String getNewValueStr() {
		if (newValueStr == null && (getNewValue() != null && getNewValue().equals(MChangeLog.NULL))) {
			return "--";
		}
		return newValueStr;
	}

	public void setNewValueStr(String newValueStr) {
		this.newValueStr = newValueStr;
	}

	public MTable getTable() {
		if (table == null) {
			table = MTable.get(getCtx(), getAD_Table_ID());
			// Lama: usar cache .- new MTable(getCtx(), getAD_Table_ID(), getTrxName()));
		}
		return table;
	}

	public void setTable(MTable table) {
		this.table = table;
	}

	public MColumn getColumn() {
		if (column == null) {
			column = MColumn.get(getCtx(), getAD_Column_ID());
			// Lama: usar cache .- new MColumn(getCtx(), getAD_Column_ID(), get_TrxName()));
		}
		return column;
	}

	public void setColumn(MColumn column) {
		this.column = column;
	}
	
	/**
	 * Cambios de bitacora para EXME_CtaPac
	 * 
	 * @param ctx
	 *            Contexto
	 * @param EXME_CtaPac_ID
	 *            Cuenta paciente a analizar
	 * @param columns
	 *            Columnas que se desea ver
	 * @param trxName
	 *            Nombre de transaccion
	 * @return Listado de cambios
	 */
	public static List<ChangeLogVO> getAccountEncounterLog(Properties ctx, int EXME_CtaPac_ID, List<Integer> columns, String trxName) {
		List<ChangeLogVO> lst = new ArrayList<ChangeLogVO>();
		StringBuilder str = new StringBuilder("select log.Created, log.AD_Column_ID, log.OldValue, log.NewValue from AD_ChangeLog log ");
		str.append("where log.AD_Table_ID = ? and log.AD_Column_ID in (").append(StringUtils.join(columns, ',')).append(") ");
		str.append("and log.Record_ID = ? ");
		str.append("order by log.AD_Column_ID, log.Created desc");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(str.toString(), null);
			pstmt.setInt(1, X_EXME_CtaPac.Table_ID);
			pstmt.setInt(2, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ChangeLogVO vo = new ChangeLogVO();
				vo.setCreated(rs.getTimestamp(1));
				vo.setColumnId(rs.getInt(2));
				vo.setOldValue(rs.getString(3));
				vo.setNewValue(rs.getString(4));
				lst.add(vo);
			}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
				pstmt = null;
				rs = null;
			}
		}
		return lst;
	}

} // MChangeLog
