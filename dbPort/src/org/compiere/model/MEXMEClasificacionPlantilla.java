/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * 
 *
 */
public class MEXMEClasificacionPlantilla extends X_EXME_ClasificacionPlantilla {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9150412464865035969L;
	private static CLogger s_log = CLogger.getCLogger(MEXMECampoPlantilla.class);

	/**
	 * @param ctx
	 * @param EXME_ClasificacionPlantilla_ID
	 * @param trxName
	 */
	public MEXMEClasificacionPlantilla(Properties ctx,
			int EXME_ClasificacionPlantilla_ID, String trxName) {
		super(ctx, EXME_ClasificacionPlantilla_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEClasificacionPlantilla(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Metodo que debuelve una lista de objetos con el resultado de todos los elementos de la tabla
	 * */
	public static List<MEXMEClasificacionPlantilla> obtenerClasificaciones(Properties ctx, String trxName){

		StringBuilder st = new StringBuilder("select * from EXME_ClasificacionPlantilla ");
		st.append(" where NAME IS NOT NULL");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_ClasificacionPlantilla"));
		st.append(" order by name");
		List<MEXMEClasificacionPlantilla> lst = new ArrayList<MEXMEClasificacionPlantilla>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMEClasificacionPlantilla(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lst;
	}
	
//	/**
//	 * Itera el contenido de la tabla y pone los elementos en otra lista de tipo LabelValueBean
//	 * */
//	public static List<LabelValueBean> listaValores(Properties ctx, String trxName){
//
//		List<LabelValueBean> lsValueBean = new ArrayList<LabelValueBean>();
//		for(MEXMEClasificacionPlantilla clasifcaciones : obtenerClasificaciones(ctx, trxName)){
//			lsValueBean.add(new LabelValueBean(clasifcaciones.getName(), 
//					String.valueOf(clasifcaciones.getEXME_ClasificacionPlantilla_ID())));
//			
//		}
//		
//		return lsValueBean;
//	} 
	
	

}
