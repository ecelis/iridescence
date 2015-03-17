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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.Compiere;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.TimeUtil;
import org.compiere.util.vo.SessionVO;
import org.joda.time.DateTimeZone;

/**
 * Session Model. Maintained in AMenu.
 *
 * @author Jorg Janke
 * @version $Id: MSession.java,v 1.3 2006/07/30 00:58:05 jjanke Exp $
 */
public class MSession extends X_AD_Session {
	private static CLogger s_log = CLogger.getCLogger(MSession.class);

	/**
	 * Get existing or create local session
	 *
	 * @param ctx
	 *            context
	 * @param createNew
	 *            create if not found
	 * @return session session
	 */
	public static MSession get(Properties ctx, boolean createNew) {
		int AD_Session_ID = Env.getContextAsInt(ctx, "#AD_Session_ID");
		MSession session = null;
		if (AD_Session_ID > 0)
			session = (MSession) s_sessions.get(new Integer(AD_Session_ID));
		// Try to load
		if (session == null && AD_Session_ID > 0) {
			session = new MSession(ctx, AD_Session_ID, null);
			if (session.get_ID() != AD_Session_ID) {
				Env.setContext(ctx, "#AD_Session_ID", AD_Session_ID);
			}
			s_sessions.put(AD_Session_ID, session);
		}
		// Create New
		if (session == null && createNew) {
			session = new MSession(ctx, null); // local session
			session.save();
			AD_Session_ID = session.getAD_Session_ID();
			Env.setContext(ctx, "#AD_Session_ID", AD_Session_ID);
			s_sessions.put(new Integer(AD_Session_ID), session);
		}
		return session;
	} // get

	/**
	 * Get existing or create remote session
	 *
	 * @param ctx
	 *            context
	 * @param Remote_Addr
	 *            remote address
	 * @param Remote_Host
	 *            remote host
	 * @param WebSession
	 *            web session
	 * @return session
	 */
	public static MSession get(Properties ctx, String Remote_Addr,
			String Remote_Host, String WebSession) {
		return get(ctx, Remote_Addr, Remote_Host, WebSession, null);
	} // get

	/**
	 * Get existing or create remote session
	 *
	 * @param ctx
	 *            context
	 * @param Remote_Addr
	 *            remote address
	 * @param Remote_Host
	 *            remote host
	 * @param WebSession
	 *            web session
	 * @param timeZone
	 *            from the remote client
	 * @return session
	 */
	public static MSession get(Properties ctx, String Remote_Addr,
			String Remote_Host, String WebSession, TimeZone timeZone) {
		int AD_Session_ID = Env.getContextAsInt(ctx, "#AD_Session_ID");
		MSession session = null;
		if (AD_Session_ID > 0)
			session = (MSession) s_sessions.get(new Integer(AD_Session_ID));
		if (session == null) {
			session = new MSession(ctx, Remote_Addr, Remote_Host, WebSession,
					null); // remote session

			if (timeZone != null) {
				session.setTimeZone(timeZone.getDisplayName());
			}

			session.save();
			AD_Session_ID = session.getAD_Session_ID();
			Env.setContext(ctx, "#AD_Session_ID", AD_Session_ID);
			s_sessions.put(new Integer(AD_Session_ID), session);
		}
		return session;
	}

	/** Sessions */
	private static CCache<Integer, MSession> s_sessions = Ini.isClient() ? new CCache<Integer, MSession>(
			"AD_Session_ID", 1, 0) // one client session
			: new CCache<Integer, MSession>("AD_Session_ID", 30, 0); // no
																		// time-out

	/**************************************************************************
	 * Standard Constructor
	 *
	 * @param ctx
	 *            context
	 * @param AD_Session_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MSession(Properties ctx, int AD_Session_ID, String trxName) {
		super(ctx, AD_Session_ID, trxName);
		if (AD_Session_ID == 0) {
			setProcessed(false);
		}
	} // MSession

	/**
	 * Load Costructor
	 *
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MSession(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MSession

	/**
	 * New (remote) Constructor
	 *
	 * @param ctx
	 *            context
	 * @param Remote_Addr
	 *            remote address
	 * @param Remote_Host
	 *            remote host
	 * @param WebSession
	 *            web session
	 * @param trxName
	 *            transaction
	 */
	public MSession(Properties ctx, String Remote_Addr, String Remote_Host,
			String WebSession, String trxName) {
		this(ctx, 0, trxName);

		if (Remote_Addr != null) {
			setRemote_Addr(Remote_Addr);
		}

		if (Remote_Host != null) {
			setRemote_Host(Remote_Host);
		}

		if (WebSession != null) {
			setWebSession(WebSession);
		}

		setDescription(Compiere.getMainVersion() + "_"
				+ Compiere.getDateVersion() + "_" + Compiere.getDeployVersion()
				+ " " + Compiere.getImplementationVersion());

		setAD_Role_ID(Env.getContextAsInt(ctx, "#AD_Role_ID"));
		setLoginDate(Env.getContextAsDate(ctx, "#Date"));
	} // MSession

	/**
	 * New (local) Constructor
	 *
	 * @param ctx
	 *            context
	 * @param trxName
	 *            transaction
	 */
	public MSession(Properties ctx, String trxName) {
		this(ctx, 0, trxName);
		try {
			InetAddress lh = InetAddress.getLocalHost();

			setRemote_Addr(lh.getHostAddress());
			setRemote_Host(lh.getHostName());
			setDescription(Compiere.getMainVersion() + "_"
					+ Compiere.getDateVersion() + "_"
					+ Compiere.getDeployVersion() + " "
					+ Compiere.getImplementationVersion());

			setAD_Role_ID(Env.getContextAsInt(ctx, "#AD_Role_ID"));
			setLoginDate(Env.getContextAsDate(ctx, "#Date"));

			MClient client = MClient.get(getCtx());

			if (client != null && client.getTimeZone() != null) {
				setTimeZone(client.getTimeZone());
			} else {
				setTimeZone(Calendar.getInstance().getTimeZone()
						.getDisplayName());
			}
		} catch (UnknownHostException e) {
			log.log(Level.SEVERE, "No Local Host", e);
		}
	} // MSession

	/** Web Store Session */
	private boolean m_webStoreSession = false;

	/**
	 * Is it a Web Store Session
	 *
	 * @return Returns true if Web Store Session.
	 */
	public boolean isWebStoreSession() {
		return m_webStoreSession;
	} // isWebStoreSession

	/**
	 * Set Web Store Session
	 *
	 * @param webStoreSession
	 *            The webStoreSession to set.
	 */
	public void setWebStoreSession(boolean webStoreSession) {
		m_webStoreSession = webStoreSession;
	} // setWebStoreSession

	/**
	 * String Representation
	 *
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("MSession[")
				.append(getAD_Session_ID()).append(",AD_User_ID=")
				.append(getCreatedBy()).append(",").append(getCreated())
				.append(",Remote=").append(getRemote_Addr());

		String s = getRemote_Host();
		if (s != null && s.length() > 0) {
			sb.append(",").append(s);
		}

		if (m_webStoreSession) {
			sb.append(",WebStoreSession");
		}

		sb.append("]");

		return sb.toString();
	} // toString

	/**
	 * Session Logout
	 */
	public void logout() {
		setProcessed(true);
		save();
		s_sessions.remove(new Integer(getAD_Session_ID()));
		log.info(TimeUtil.formatElapsed(getCreated(), getUpdated()));
	} // logout

	/**
	 * Preserved for backward compatibility
	 *
	 * @deprecated
	 */
	public MChangeLog changeLog(String TrxName, int AD_ChangeLog_ID,
			int AD_Table_ID, int AD_Column_ID, int Record_ID, int AD_Client_ID,
			int AD_Org_ID, Object OldValue, Object NewValue) {
		return changeLog(TrxName, getCtx(), AD_ChangeLog_ID, AD_Table_ID,
				AD_Column_ID, Record_ID, AD_Client_ID, AD_Org_ID, OldValue,
				NewValue, (String) null, false);
	} // changeLog

	/**
	 * Create Change Log only if table is logged
	 *
	 * @param TrxName
	 *            transaction name
	 * @param AD_ChangeLog_ID
	 *            0 for new change log
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
	 * @return saved change log or null
	 */
	public MChangeLog changeLog(String TrxName, Properties ctx,
			int AD_ChangeLog_ID, int AD_Table_ID, int AD_Column_ID,
			int Record_ID, int AD_Client_ID, int AD_Org_ID, Object OldValue,
			Object NewValue, String event, boolean shouldLogPatient) {
		// Null handling
		if (OldValue == null && NewValue == null)
			return null;
		// Equal Value
		if (OldValue != null && NewValue != null && OldValue.equals(NewValue))
			return null;

		// Role Logging
		MRole role = MRole.getDefault(ctx, false);
		// Do we need to log
		if (m_webStoreSession // log if WebStore
				|| MChangeLog.isLogged(ctx, AD_Table_ID) // im/explicit log
				|| (role != null && role.isChangeLog()) || shouldLogPatient)// Role
																			// Logging
			;
		else
			return null;
		//
		log.finest("AD_ChangeLog_ID=" + AD_ChangeLog_ID + ", AD_Session_ID="
				+ getAD_Session_ID() + ", AD_Table_ID=" + AD_Table_ID
				+ ", AD_Column_ID=" + AD_Column_ID + ": " + OldValue + " -> "
				+ NewValue);

		try {
			MChangeLog cl = new MChangeLog(getCtx(), AD_ChangeLog_ID, TrxName,
					getAD_Session_ID(), AD_Table_ID, AD_Column_ID, Record_ID,
					AD_Client_ID, AD_Org_ID, OldValue, NewValue, event);

			// if (!cl.isInPatientTables()) { //Lama
			// log.log(Level.WARNING, "No Patient ID saved" + ", AD_Session_ID="
			// + getAD_Session_ID()
			// + ", AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" +
			// AD_Column_ID);
			// }

			// cl.isInPatientTables();

			// if (cl.saveLog()) {
			// System.out.println(cl.getAD_ChangeLog_ID());
			return cl;
			// }
		} catch (Exception e) {
			log.log(Level.SEVERE, "AD_ChangeLog_ID=" + AD_ChangeLog_ID
					+ ", AD_Session_ID=" + getAD_Session_ID()
					+ ", AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID="
					+ AD_Column_ID, e);
			return null;
		}
		// log.log(Level.SEVERE, "AD_ChangeLog_ID=" + AD_ChangeLog_ID
		// + ", AD_Session_ID=" + getAD_Session_ID()
		// + ", AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID);
		// return null;
	} // changeLog

	/**
	 * Obtiene el registro de sesión para los parámetros datos
	 *
	 * @param ctx
	 * @param userIni
	 * @param userFin
	 * @param tableIni
	 * @param tableFin
	 * @param fechaIni
	 * @param fechaFin
	 * @param trxName
	 * @return
	 */
	public static List<SessionVO> getLog(Properties ctx, String userIni,
			String userFin, String tableIni, String tableFin, String fechaIni,
			String fechaFin, String trxName) {
		StringBuilder st = new StringBuilder(
				"select distinct(s.ad_session_id) as id, u.name, s.created, s.description, remote_Addr from AD_Session s ");
		st.append("inner join AD_User u on s.createdby = u.AD_User_ID ");
		st.append("inner join AD_ChangeLog ch on ch.AD_Session_ID = s.AD_Session_ID ");
		st.append("inner join AD_Table t on t.AD_Table_ID = ch.AD_Table_ID ");
		st.append("where u.name >= ? and u.name <= ? ");
		st.append("and t.tablename >= ? and t.tablename <= ? ");
		st.append("and s.created >= to_date(?,'DD/MM/YYYY HH24:MI') ");
		st.append("and s.created <= to_date(?,'DD/MM/YYYY HH24:MI') ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				st.toString(), "AD_Session", "s"));
		st.append(" order by u.name, s.created desc ");

		List<SessionVO> lista = new ArrayList<SessionVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, userIni);
			pstmt.setString(2, userFin);
			pstmt.setString(3, tableIni);
			pstmt.setString(4, tableFin);
			pstmt.setString(5, fechaIni);
			pstmt.setString(6, fechaFin);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				SessionVO vo = new SessionVO();
				vo.setCreated(rs.getTimestamp("created"));
				vo.setDescription(rs.getString("description"));
				vo.setIdColumn(new IDColumn(rs.getInt("id")));
				vo.setRemoteAdress(rs.getString("remote_Addr"));
				vo.setUserName(rs.getString("name"));
				lista.add(vo);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Log por emergencia
	 *
	 * @param ctx
	 * @param EXME_Emergencia_ID
	 * @param tableIni
	 * @param tableFin
	 * @param trxName
	 * @return
	 */
	public static List<SessionVO> getLogByEmergency(Properties ctx,
			int EXME_Emergencia_ID, String tableIni, String tableFin,
			String trxName) {

		String fechaIni = Constantes.getSdfFechaHora().format(new Date());
		String fechaFin = fechaIni;

		if (EXME_Emergencia_ID > 0) {
			MEXMEEmergencia emergencia = new MEXMEEmergencia(ctx,
					EXME_Emergencia_ID, trxName);
			fechaIni = Constantes.getSdfFechaHora().format(
					emergencia.getCreated());
			if (emergencia.getFecha_Fin() == null) {
				fechaFin = Constantes.getSdfFechaHora().format(new Date());
			} else {
				fechaFin = Constantes.getSdfFechaHora().format(
						emergencia.getFecha_Fin());
			}
		}

		List<MEXMEEmergenciaUsuarios> usuarios = MEXMEEmergenciaUsuarios.get(
				ctx, EXME_Emergencia_ID, trxName);

		List<Integer> idsUsuarios = new ArrayList<Integer>();

		for (MEXMEEmergenciaUsuarios usuario : usuarios) {
			idsUsuarios.add(usuario.getAD_User_ID());
		}

		List<MEXMEEmergenciaRoles> roles = MEXMEEmergenciaRoles.get(ctx,
				EXME_Emergencia_ID, trxName);

		List<Integer> idsRoles = new ArrayList<Integer>();

		for (MEXMEEmergenciaRoles rol : roles) {
			idsRoles.add(rol.getAD_Role_ID());
		}

		StringBuilder st = new StringBuilder(
				"select distinct(s.ad_session_id) as id, u.name, s.created, s.description, remote_Addr from AD_Session s ");
		st.append("inner join AD_User u on s.createdby = u.AD_User_ID ");
		st.append("inner join AD_ChangeLog ch on ch.AD_Session_ID = s.AD_Session_ID ");
		st.append("inner join AD_Table t on t.AD_Table_ID = ch.AD_Table_ID ");
		st.append("where u.AD_User_ID in (")
				.append(StringUtils.join(idsUsuarios, ",")).append(") ");
		st.append("and s.AD_Role_ID in (")
				.append(StringUtils.join(idsRoles, ",")).append(") ");
		st.append("and t.tablename >= ? and t.tablename <= ? ");
		st.append("and s.created >= to_date(?,'DD/MM/YYYY HH24:MI') ");
		st.append("and s.created <= to_date(?,'DD/MM/YYYY HH24:MI') ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				st.toString(), "AD_Session", "s"));
		st.append(" order by u.name, s.created desc ");

		List<SessionVO> lstSessiones = new ArrayList<SessionVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, tableIni);
			pstmt.setString(2, tableFin);
			pstmt.setString(3, fechaIni);
			pstmt.setString(4, fechaFin);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				SessionVO vo = new SessionVO();
				vo.setCreated(rs.getTimestamp("created"));
				vo.setDescription(rs.getString("description"));
				vo.setIdColumn(new IDColumn(rs.getInt("id")));
				vo.setRemoteAdress(rs.getString("remote_Addr"));
				vo.setUserName(rs.getString("name"));
				lstSessiones.add(vo);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstSessiones;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		boolean retVal = true;

		if (newRecord) {
			if (getTimeZone() == null) {
				// get client to find time zone
				MClient client = new MClient(getCtx(), null);

				if (client.getTimeZone() == null) {
					setTimeZone(DateTimeZone.UTC.getShortName(0));
				} else {
					//FIXME : use time zone from organization
					setTimeZone(client.getTimeZone());
				}
			}
		}

		return retVal;
	}


	/**
	 * Returns the current number of sessions, excluding the "Server" one.
	 * @return The current number of sessions.
	 */
	public static int getCurrentSessions() {
		int currentSessions = -1;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =
				"SELECT count(*) FROM AD_Session WHERE Processed = 'N' "
				+ " AND Created >= ? "
				+ " AND WebSession <> 'Server'";

		Timestamp initialRange = TimeUtil.getInitialRangeT(Env.getCtx(), new Date());

		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setTimestamp(1, initialRange);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				currentSessions = rs.getInt(1);
			}
		} catch (SQLException e) {
			s_log.log(
					Level.SEVERE,
					"SQL : " + sql + "\n - timestamp : " + initialRange,
					e
			);
		} finally {
			DB.close(rs, pstmt);
		}



		return currentSessions;
	}
} // MSession

