package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

/**
 * Creado: Agosto/2009<p>
 * Clase para especificar procedimientos de enfermeria
 * @author Lizeth de la Garza
 */
public class MEXMEProcEnfermeria extends X_EXME_ProcEnfermeria {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEProcEnfermeria.class);
	
	public  MEXMEProcEnfermeria(Properties ctx, int EXME_ProcEnfermeria_ID, String trxName) {
        super(ctx, EXME_ProcEnfermeria_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public  MEXMEProcEnfermeria(Properties ctx, ResultSet rs, String trxName) {
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
    public static List<EnfermeriaView> getProcedimientoEnfermeria(Properties ctx,  String valor, String buscar, String trxName) {

		List<EnfermeriaView> list = new ArrayList<EnfermeriaView>();
		StringBuilder sql = new StringBuilder("SELECT proc.value AS Valor , proc.name AS Nombre, proc.Description AS Descripcion, ");
		sql.append(" proc.EXME_ProcEnfermeria_ID AS procID FROM EXME_ProcEnfermeria proc ");
		sql.append(" WHERE proc.IsActive = 'Y'");


		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ProcEnfermeria", "proc"));

		// si el valor no es nulo
		if (valor != null && valor.trim().length() > 0) {
			//valor = valor.replaceAll("\\*", "%");//Lama: comodin estandar %

			sql.append(" AND UPPER(").append(buscar).append(") LIKE UPPER('").append(valor).append("') ");
		}
		sql.append(" ORDER BY proc.Value ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EnfermeriaView proc = new EnfermeriaView();

				proc.setValue(rs.getString("Valor"));
				proc.setNombre(rs.getString("Nombre"));
				proc.setDescripcion(rs.getString("Descripcion"));
				proc.setProcID(rs.getInt("procID"));

				list.add(proc);
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
