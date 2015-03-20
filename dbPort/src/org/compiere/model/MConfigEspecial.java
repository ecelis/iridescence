package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

@Deprecated /* estandar de nombrado, Lama */
public class MConfigEspecial extends MEXMEConfigEspecial {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param ctx
	 * @param ID
	 */
	public MConfigEspecial(Properties ctx, int ID, String trxName) {
		super(ctx, ID, trxName);

	}

	/**
	 * @param ctx
	 * @param rs
	 */
	public MConfigEspecial(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}
	private static CLogger      s_log = CLogger.getCLogger (MConfigEspecial.class);
	
    /**
	 * Obtiene la configuracion especial segun el cliente y/o organizacion
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MConfigEspecial get(Properties ctx, String trxName) {

		MConfigEspecial configEspecial = null;

		StringBuilder sql = new StringBuilder(100);
		
		sql.append("SELECT * FROM EXME_ConfigEspecial WHERE isActive = 'Y' "); 

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
		
		sql.append(" ORDER BY EXME_ConfigEspecial.AD_Org_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				configEspecial = new MConfigEspecial(ctx, rs, trxName);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}

			rs = null;
			pstmt = null;
		}

		return configEspecial;
	}
	
	/**
	 * Obtener la configuracion segun el cliente solamente
	 * @param ctx Contexto
	 * @param AD_Client_ID Identificador del cliente
	 * @param trxName Transacci�n
	 * @return Configurac�n o null
	 *
	public static MConfigEspecial getByClient(Properties ctx, int AD_Client_ID,
			String trxName) {

		MConfigEspecial configEspecial = null;

		StringBuilder sql = new StringBuilder(100);

		sql
				.append("SELECT * FROM EXME_ConfigEspecial WHERE isActive = 'Y' and AD_Client_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				configEspecial = new MConfigEspecial(ctx, rs, trxName);
			}
			rs.close();
			pstmt.close();
			pstmt = null;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}

			rs = null;
			pstmt = null;
		}

		return configEspecial;
	}*/
}