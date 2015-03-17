package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author odelarosa
 * 
 */
public class MEXMEReportDesigner extends X_EXME_ReportDesigner {

	private static final long serialVersionUID = -1623304528201715690L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEReportDesigner.class);

	public static List<MEXMEReportDesigner> get(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * from EXME_ReportDesigner ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " where ", MEXMEReportDesigner.Table_Name));
		sql.append(" ORDER BY name ");
		List<MEXMEReportDesigner> list = new ArrayList<MEXMEReportDesigner>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEReportDesigner(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	public static MEXMEReportDesigner get(Properties ctx, String name, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * from EXME_ReportDesigner ");
		sql.append(" where value = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEReportDesigner.Table_Name));
		sql.append(" ORDER BY name ");
		MEXMEReportDesigner designer = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				designer = new MEXMEReportDesigner(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return designer;
	}

	/**
	 * @param ctx
	 * @param EXME_ReportDesigner_ID
	 * @param trxName
	 */
	public MEXMEReportDesigner(Properties ctx, int EXME_ReportDesigner_ID, String trxName) {
		super(ctx, EXME_ReportDesigner_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEReportDesigner(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
