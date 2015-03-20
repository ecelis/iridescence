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

public class MEXMEParteCorporal extends X_EXME_ParteCorporal{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger		s_log = CLogger.getCLogger (MEXMEParteCorporal.class);
	
	public MEXMEParteCorporal(Properties ctx, int EXME_ParteCorporal_ID, String trxName) {
		super(ctx, EXME_ParteCorporal_ID, trxName);
	}
	
	 /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEParteCorporal(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

	
	public static List<KeyNamePair> getPartesCorporales(Properties ctx, String trxName){
		
		final List<KeyNamePair> lista = new ArrayList<KeyNamePair>();
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_PARTECORPORAL_ID, NAME FROM EXME_PARTECORPORAL WHERE ISACTIVE = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY NAME");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				KeyNamePair obj = new KeyNamePair(rs.getInt(1),rs.getString(2));
				lista.add(obj);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}finally{
			DB.close(rs, pstmt);
    		rs = null;
    		pstmt = null;
		}
		
		return lista;

	}	
	
	  /**
     * Lizeth de la Garza- Obtener el listado de Procedimientos de Enfermer�a del Paciente
     * @param Properties ctx
     * @param String valor - valor de busqueda
     * @param String buscar  - opci�n de busqueda
     * @param trxName
     * @return List- Procedimientos
     *
    public static List<EnfermeriaView> getParteCorporal(Properties ctx,  String valor, String buscar, String trxName) {

		List<EnfermeriaView> list = new ArrayList<EnfermeriaView>();
		StringBuilder sql = new StringBuilder("SELECT parte.value AS Valor , parte.name AS Nombre, parte.Description AS Descripcion, ");
		sql.append(" parte.EXME_ParteCorporal_ID AS parteID FROM EXME_ParteCorporal parte ");
		sql.append(" WHERE parte.IsActive = 'Y'");


		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), X_EXME_ParteCorporal.Table_Name, "parte"));

		// si el valor no es nulo
		if (valor != null && valor.trim().length() > 0) {
			//valor = valor.replaceAll("\\*", "%");//Lama: comodin estandar %

			sql.append(" AND UPPER(").append(buscar).append(") LIKE UPPER('").append(valor).append("') ");
		}
		sql.append(" ORDER BY parte.Value ");

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
				proc.setParteCorpID(rs.getInt("parteID"));

				list.add(proc);
			}


		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}finally {
			sql = null;
			DB.close(rs, pstmt);
		}
		return list;
	} */
    
	public static String[] parteCorporal = { MEXMEParteCorporal.COLUMNNAME_Name,
		MEXMEParteCorporal.COLUMNNAME_Value
		};
	
//	public static String getNameById(Properties ctx, int id, String trxName) {
//
//		String retValue = null;
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append("select * from exme_partecorporal where exme_partecorporal_id = ?");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, id);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				retValue = rs.getString("name");
//			}
//
//		} catch (SQLException ex) {
//			s_log.log(Level.SEVERE, "Error: " + ex.getMessage() + " " + sql, ex);
//		} finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs = null;
//			sql = null;
//		}
//		return retValue;
//	}

}
