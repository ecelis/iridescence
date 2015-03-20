package org.compiere.model;

import java.util.Properties;

/**
 * Clase para la impresi�n del reporte de censo de dietas
 * Creado: 09/11/2009
 * <p>
 * 
 * @author rsolorzano
 * @deprecated Sera removida, no se utiliza en ecs
 */

public class MEXMEConfigDietaDet extends X_EXME_ConfigDieta_Det {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
//	@SuppressWarnings("unused")
//	private static CLogger		s_log				= CLogger.getCLogger(MEXMEConfigDietaDet.class);

	public MEXMEConfigDietaDet(Properties ctx, int EXME_ConfigDieta_Det_ID, String trxName) {
		super(ctx, EXME_ConfigDieta_Det_ID, trxName);
	}

	/**
	 * Regresa los valores de configuraciones disponibles por tipo de configuracion
	 * 
	 * @param ctx Propiedades
	 * @param trxName Nombre de la transaccion
	 * 
	 *
	public static List<LabelValueBean> getValorPorTipo(Properties ctx, String tipo, String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();

		try {

			sql.append("SELECT DET.EXME_CONFIGDIETA_DET_ID, DET.VALUE ");
			sql.append(" FROM EXME_CONFIGDIETA DIETA ");
			sql.append(" INNER JOIN EXME_CONFIGDIETA_DET DET ON (DIETA.EXME_CONFIGDIETA_ID = DET.EXME_CONFIGDIETA_ID) ");
			sql.append(" WHERE DIETA.TIPO = ? ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, tipo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean bean = new LabelValueBean(rs.getString("VALUE"), rs.getString("EXME_CONFIGDIETA_DET_ID"));
				lista.add(bean);
			}
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
			sql = null;
			rs = null;
			pstmt = null;
		}

		return lista;
	}*/

}
