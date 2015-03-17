package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEHeridaQca extends X_EXME_HeridaQCA {
/**
	 * 
	 */
	private static final long serialVersionUID = -3246209868966043870L;
private static CLogger		s_log = CLogger.getCLogger (MEXMEParteCorporal.class);
	
	public MEXMEHeridaQca(Properties ctx, int EXME_HeridaQca_ID, String trxName) {
		super(ctx, EXME_HeridaQca_ID, trxName);
	}
	
	 /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEHeridaQca(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
	

	
	 /**
     * Lizeth de la Garza- Obtener el listado de Procedimientos de Enfermer�a del Paciente
     * @param Properties ctx
     * @param String valor - valor de busqueda
     * @param String buscar  - opci�n de busqueda
     * @param trxName
     * @return List- Procedimientos
     *
    public static List<EnfermeriaView> getHeridaQca(Properties ctx,  String valor, String buscar, String trxName) {

		List<EnfermeriaView> list = new ArrayList<EnfermeriaView>();
		StringBuilder sql = new StringBuilder("SELECT herida.value AS Valor , herida.name AS Nombre, herida.Description AS Descripcion, ");
		sql.append(" herida.EXME_HeridaQca_ID AS heridaID FROM EXME_HeridaQca herida ");
		sql.append(" WHERE herida.IsActive = 'Y'");


		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_HeridaQca", "herida"));

		// si el valor no es nulo
		if (valor != null && valor.trim().length() > 0) {
			//valor = valor.replaceAll("\\*", "%");//Lama: comodin estandar %

			sql.append(" AND UPPER(").append(buscar).append(") LIKE UPPER('").append(valor).append("') ");
		}
		sql.append(" ORDER BY herida.Value ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EnfermeriaView object = new EnfermeriaView();

				object.setValue(rs.getString("Valor"));
				object.setNombre(rs.getString("Nombre"));
				object.setDescripcion(rs.getString("Descripcion"));
				object.setHeridaQcaID(rs.getInt("heridaID"));

				list.add(object);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
			sql = null;
		}
		return list;
	}*/


}
