package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MHL7Version extends X_HL7_Version {

	/**
	 * autogenerado
	 */
	private static final long serialVersionUID = -3349317827507907073L;

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MHL7Version.class);

	public MHL7Version(Properties ctx, int HL7_Version_ID, String trxName) {
		super(ctx, HL7_Version_ID, trxName);
	}

	public MHL7Version(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * metodo para pedir una version por nombre si el name que se pide es nulo
	 * regresa nada
	 */
	public static MHL7Version get(Properties ctx, String name) {

		if (name == null) {
			return null;
		}
		MHL7Version version = null;

		String sql = "SELECT * FROM HL7_Version WHERE UPPER(name)=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, name.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				version = new MHL7Version(ctx, rs, null);
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}

		return version;
	}

	public static ArrayList<KeyNamePair> getVersionListByMsgType(Properties ctx, String msgType, String trxName) {
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT V.HL7_VERSION_ID, V.NAME FROM HL7_VERSION V INNER JOIN HL7_MESSAGINGSTANDARD D ON D.HL7_MESSAGINGSTANDARD_ID = ")
			   .append("V.HL7_MESSAGINGSTANDARD_ID WHERE D.VALUE = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int index = 1;
			pstmt.setString(index++, msgType);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt("HL7_Version_ID"), rs.getString("Name")));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getVersionListByMsgType", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}
}
