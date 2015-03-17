/**
 * 
 */
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
 * Tabla con motivos de muerte
 * TODO: Revisar si se requiere conservar esta tabla, 
 * ya que actualmente existe otra llamada {@link X_EXME_MotivoMuerte}
 * 
 * @author Lorena Lama
 */
public class MEXMECausaMuerte extends X_EXME_CausaMuerte {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** Static Logger */
	private static CLogger		s_log				= CLogger.getCLogger(MEXMECausaMuerte.class);

	/**
	 * @param ctx
	 * @param EXME_CausaMuerte_ID
	 * @param trxName
	 */
	public MEXMECausaMuerte(Properties ctx, int EXME_CausaMuerte_ID, String trxName) {
		super(ctx, EXME_CausaMuerte_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECausaMuerte(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene un listado de causas de muerte
	 * 
	 * @param ctx
	 * @param trxName
	 * @return List<KeyNamePair>
	 */
	public static List<KeyNamePair> getAll(Properties ctx, String trxName) {
		List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT EXME_CausaMuerte.EXME_CausaMuerte_ID, EXME_CausaMuerte.Name ")
				.append("FROM EXME_CausaMuerte WHERE EXME_CausaMuerte.isActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CausaMuerte"));

			psmt = DB.prepareStatement(sql.toString(), trxName);
			rs = psmt.executeQuery();

			while (rs.next()) {
				KeyNamePair lvb = new KeyNamePair(rs.getInt("EXME_CausaMuerte_ID"), rs.getString("Name"));
				retValue.add(lvb);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
			rs = null;
			psmt = null;
			sql = null;
		}

		return retValue;
	}

}
