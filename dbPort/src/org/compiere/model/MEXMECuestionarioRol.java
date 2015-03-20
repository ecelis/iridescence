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
public class MEXMECuestionarioRol extends X_EXME_CuestionarioRol {
	private static CLogger s_log = CLogger.getCLogger(MEXMECuestionarioRol.class);
	private static final long serialVersionUID = 4045463007863673720L;

	public static List<MEXMECuestionarioRol> get(Properties ctx, int cuestId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_cuestionariorol ");
		sql.append(" WHERE ");
		sql.append("  exme_cuestionario_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		List<MEXMECuestionarioRol> list = new ArrayList<MEXMECuestionarioRol>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMECuestionarioRol(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	public MEXMECuestionarioRol(Properties ctx, int EXME_CuestionarioRol_ID, String trxName) {
		super(ctx, EXME_CuestionarioRol_ID, trxName);
	}
	
	public MEXMECuestionarioRol(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
