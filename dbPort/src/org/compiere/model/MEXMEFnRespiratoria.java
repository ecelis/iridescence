package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

/**
 * 
 * @author lama
 *
 */
public class MEXMEFnRespiratoria extends X_EXME_FnRespiratoria {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1524392008794788490L;
	private static CLogger		s_log				= CLogger.getCLogger(MEXMEEdoConciencia.class);

	public MEXMEFnRespiratoria(Properties ctx, int EXME_FnRespiratoria_ID, String trxName) {
		super(ctx, EXME_FnRespiratoria_ID, trxName);
	}

	public MEXMEFnRespiratoria(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> get(Properties ctx, String trxName) {
		final List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT EXME_FnRespiratoria_ID, Name ");
			sql.append(" FROM EXME_FnRespiratoria ");
			sql.append(" WHERE IsActive='Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;

	}

}
