/*
 * Created on 14-december-2011
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Modelo de Internacionalizacion
 * 
 * @author Alejandro Garza
 * 
 */
public class MEXMEI18N extends X_EXME_I18N {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEMejoras.class);

	/**
	 * @param ctx
	 * @param ID
	 */
	public MEXMEI18N(Properties ctx, int ID, String trxName) {
		super(ctx, ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 */
	public MEXMEI18N(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene la configuracion de internacionalizacion de un pais
	 * 
	 * @param ctx
	 * @return
	 */
	public static MEXMEI18N getFromCountry(Properties ctx, int C_Country_ID, String trxName) {

		MEXMEI18N retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM EXME_I18N WHERE EXME_I18N.IsActive='Y' AND C_Country_ID=? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_Country_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMEI18N(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getFromCountry", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}
}