/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * @author Twry Perez
 * 
 */
public class MEXMEEspecimen extends X_EXME_Especimen {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5752323305418700245L;

	/**
	 * @param ctx
	 * @param EXME_Especimen_ID
	 * @param trxName
	 */
	public MEXMEEspecimen(Properties ctx, int EXME_Especimen_ID, String trxName) {
		super(ctx, EXME_Especimen_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEEspecimen(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Devolvemos una lista de objetos LabelValueBean con los especimenes activos
	 * 
	 * 
	 * @param ctx El contexto de la aplicaci&oacute;n
	 * @return Lista de objetos LabelValueBean con las especimens
	 *         relacionadas
	 * @throws Exception en caso de ocurrir un error al procesar la consulta.
	 */
	public static List<KeyNamePair> getLstEspecimen(Properties ctx, Boolean isLstEspecimenCondicion) throws Exception {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<KeyNamePair> resultados = new ArrayList<KeyNamePair>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT Name, ");
			if (isLstEspecimenCondicion) {
				sql.append("EXME_EspecimenCondicion_ID FROM EXME_EspecimenCondicion ");
			} else {
				sql.append("EXME_Especimen_ID FROM EXME_Especimen ");
			}
			sql.append("WHERE IsActive = 'Y' ORDER BY Name");

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultados.add(new KeyNamePair(rs.getInt(2), rs.getString(1)));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}
		return resultados;
	}
}
