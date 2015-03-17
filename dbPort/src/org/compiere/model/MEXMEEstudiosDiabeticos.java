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
 * Clase registro de estudios diab�ticos
 * 22/10/09
 */
public class MEXMEEstudiosDiabeticos extends X_EXME_EstudiosDiabeticos {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6588164586030820184L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEEstudiosDiabeticos.class);
	
	public MEXMEEstudiosDiabeticos(Properties ctx,
			int EXME_EstudiosDiabeticos_ID, String trxName) {
		super(ctx, EXME_EstudiosDiabeticos_ID, trxName);
	}
	
	/**
	 * Regresa el listado de estudios existentes en el cat�logo
	 * 
	 * @param ctx Properties
	 * @param trxName String
	 * @return List<LabelValueBean>
	 * @throws
	 */
	public static List<KeyNamePair> get(Properties ctx, String trxName) {

		final List<KeyNamePair> lista = new ArrayList<KeyNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT EXME_EstudiosDiabeticos_ID, Name ");
		sql.append(" FROM EXME_EstudiosDiabeticos ");
		sql.append(" WHERE ISACTIVE = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lista;
	}
}
