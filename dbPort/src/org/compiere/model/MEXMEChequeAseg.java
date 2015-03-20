package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

/**
 * Tabla con las aseguradoras de cheques se mostraba el combo en pantalla de captura de pagos
 * (mtto agregado por cambios Muguerza 2007)
 * TODO: revisar si se requiere conservar tabla, no se utiliza en ningun lado
 * 
 * @author lama
 */
public class MEXMEChequeAseg extends X_EXME_ChequeAseg {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 
	 * @param ctx
	 * @param EXME_ChequeAseg_ID
	 * @param trxName
	 */
	public MEXMEChequeAseg(Properties ctx, int EXME_ChequeAseg_ID, String trxName) {
		super(ctx, EXME_ChequeAseg_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEChequeAseg(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene la lista con las aseguradoras de cheque activas
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> get(Properties ctx, String trxName) {

		final List<KeyNamePair> lista = new ArrayList<KeyNamePair>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_ChequeAseg WHERE EXME_ChequeAseg.isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ChequeAseg"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new KeyNamePair(rs.getInt("EXME_ChequeAseg_ID"), rs.getString("Name")));
			}

		} catch (SQLException e) {} finally {
			DB.close(rs, pstmt);
		}

		return lista;
	}

}
