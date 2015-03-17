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
 * rsolorzano
 * Clase registro de los par�metros de los multistix
 * 25/09/09
 */
public class MEXMEParammultistix extends X_EXME_Parammultistix {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEParammultistix.class);
	
	public MEXMEParammultistix(Properties ctx, int EXME_Parammultistix_ID,
			String trxName) {
		super(ctx, EXME_Parammultistix_ID, trxName);
	}
	
	/**
	 * Regresa el listado de par�metros existentes en el cat�logo
	 * 
	 * @param ctx Properties 
	 * @param trxName String
	 * @return List<KeyNamePair>
	 * @throws 
	 */
	public static List<KeyNamePair> get(final Properties ctx, final String trxName) {

		final List<KeyNamePair> lista = new ArrayList<KeyNamePair>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Parammultistix_ID, Name ");
		sql.append(" FROM EXME_Parammultistix ");
		sql.append(" WHERE ISACTIVE = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		sql.append(" ORDER BY Name ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final KeyNamePair valor = new KeyNamePair(rs.getInt(1), rs.getString(2));
				lista.add(valor);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lista;
	}

	

}
