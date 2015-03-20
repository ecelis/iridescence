package org.compiere.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEMirthConfig;
import org.compiere.model.MHL7MessageConf;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.vo.ChannelVO;
import org.compiere.util.vo.MessageLogVO;

public class MirthDBProcess {
	
	/**	Static Logger	*/
	private CLogger	log	= CLogger.getCLogger (MirthDBProcess.class);
	
	Connection conn = null;
	
	public void connectDB(Properties ctx) {
		try {
			MEXMEMirthConfig config = MEXMEMirthConfig.get(ctx, null);
			Class.forName("org.postgresql.Driver");
			//"jdbc:postgresql://192.168.1.251:5433/mirthdb"
			conn = DriverManager.getConnection(config.getJDBCString(), "mirthdb", "mirthdb");
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Error conexion Postgresql", e);
		} catch (ClassNotFoundException e) {
			log.log(Level.SEVERE, "Error al cargar el JDBC driver de Postgresql", e);
		}
	}
	
	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Error al cerrar conexion con Postgresql", e);
		}
	}
	
	public String getStatusChannel(String channelID) {
		String status = null;
		if (conn != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sql.append("SELECT NAME FROM EVENT WHERE ATTRIBUTES LIKE '%'||?||'%' ")
				   .append("AND (NAME LIKE 'Start%' OR NAME LIKE 'Stop%' OR NAME LIKE 'Pause%') ORDER BY DATE_CREATED DESC LIMIT 1");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, channelID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					status = rs.getString("NAME");
					status = status.split(" ")[0];
				} else {
					status = "Start";
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "getStatusChannel", e);
			}
		}
		return status;
	}
	
	public ArrayList<ChannelVO> getChannelList() {
		ArrayList<ChannelVO> list = new ArrayList<ChannelVO>();
		if (conn != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sql.append("SELECT c.name, c.revision, c.last_modified as lastdeployed, ")
				       .append("SUM(cs.received) AS RECEIVED, SUM(cs.filtered) AS FILTERED, SUM(cs.queued) AS QUEUED, ")
				       .append("SUM(cs.sent) AS SENT, SUM(cs.error) as errored, SUM(cs.alerted) AS ALERTED, ")
				       .append("'' as connection, CS.CHANNEL_ID ")
				   .append("FROM CHANNEL C INNER JOIN CHANNEL_STATISTICS CS ON CS.CHANNEL_ID = C.ID ")
				   .append("GROUP BY C.NAME, C.REVISION, C.LAST_MODIFIED, ''::VARCHAR, CS.CHANNEL_ID");
				pstmt = conn.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ChannelVO channel = new ChannelVO();
					channel.setStatus(getStatusChannel(rs.getString("Channel_ID")));
					String nameConf = StringUtils.remove(rs.getString("Name"), "out");
					channel.setName(nameConf);
					channel.setRev(rs.getInt("Revision"));
					channel.setLastDeployed(rs.getTimestamp("LastDeployed"));
					channel.setReceived(rs.getInt("received"));    
					channel.setFiltered(rs.getInt("filtered"));
					channel.setQueued(rs.getInt("queued"));
					channel.setSent(rs.getInt("sent"));
					channel.setErrored(rs.getInt("errored"));
					channel.setAlerted(rs.getInt("alerted"));
					channel.setChannelID(rs.getString("Channel_ID"));
					MHL7MessageConf conf = MHL7MessageConf.getConfByName(Env.getCtx(), nameConf, null);
					channel.setConf(conf);
					list.add(channel);
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "getChannelList", e);
			} finally {
				DB.close(rs, pstmt);
				pstmt = null;
				rs = null;
			}
		}
		return list;
	}
	
	public ArrayList<MessageLogVO> getLogByChannel(String channelID, Date startDate, Date endDate, String searchStr, String status) {
		ArrayList<MessageLogVO> list = new ArrayList<MessageLogVO>();
		if (conn != null) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sql.append("SELECT DATE_CREATED, CONNECTOR_NAME, TYPE, SOURCE, STATUS, RAW_DATA_PROTOCOL AS PROTOCOL ")
				   .append(", RAW_DATA, TRANSFORMED_DATA, ENCODED_DATA, ERRORS, CONNECTOR_MAP, CHANNEL_MAP, RESPONSE_MAP ")
				   .append("FROM MESSAGE M WHERE M.CHANNEL_ID = ? ")
				   .append(startDate != null && endDate != null ? "AND DATE_CREATED BETWEEN ? AND ? " : "")
				   .append(!StringUtils.isBlank(searchStr) ? "AND (RAW_DATA LIKE '%'||?||'%' OR TRANSFORMED_DATA LIKE '%'||?||'%' OR ENCODED_DATA LIKE '%'||?||'%') ": "")
				   .append(status != null && !status.equals("ALL") ? "AND STATUS = ? ": "")
				   .append("ORDER BY DATE_CREATED DESC");
				pstmt = conn.prepareStatement(sql.toString());
				int index = 1;
				pstmt.setString(index++, channelID);
				if (startDate != null && endDate != null) {
					pstmt.setTimestamp(index++, new Timestamp(startDate.getTime()));
					pstmt.setTimestamp(index++, new Timestamp(endDate.getTime()));
				}
				if (!StringUtils.isBlank(searchStr)) {
					pstmt.setString(index++, searchStr);
					pstmt.setString(index++, searchStr);
					pstmt.setString(index++, searchStr);
				}
				if (status != null && !status.equals("ALL")) {
					pstmt.setString(index++, status);
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MessageLogVO log = new MessageLogVO();
					log.setDateMsg(rs.getTimestamp("Date_Created"));
					log.setConnector(rs.getString("Connector_Name"));
					log.setType(rs.getString("Type"));
					log.setSource(rs.getString("Source"));
					log.setStatus(rs.getString("Status"));
					log.setProtocol(rs.getString("Protocol"));
					log.setRawMsg(rs.getString("Raw_Data"));
					log.setTransMsg(rs.getString("Transformed_Data"));
					log.setEncMsg(rs.getString("Encoded_Data"));
					log.setErrors(rs.getString("Errors"));
					log.addConnectorMap(rs.getString("Connector_Map"));
					log.addChannelMap(rs.getString("Channel_Map"));
					log.addResponseMap(rs.getString("Response_Map"));
					list.add(log);
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "getLogByChannel", e);
			} finally {
				DB.close(rs, pstmt);
				pstmt = null;
				rs = null;
			}
		}
		return list;
	}
}
