package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * 
 * @author Omar de la Rosa
 *
 */
public class MEXMETValuacion extends X_EXME_T_Valuacion{
	private static final long serialVersionUID = 1L;
	private static CLogger		s_log = CLogger.getCLogger (MEXMETValuacion.class);
	
	public MEXMETValuacion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
		
	public MEXMETValuacion(Properties ctx, int EXME_T_Valuacion_ID, String trxName) {
		super(ctx, EXME_T_Valuacion_ID, trxName);
	}

	/**
	 * Filtra y guarda la lista de valuacion
	 * @param lista
	 * @param trxName
	 * @throws Exception
	 */
	public static void prepara(List <MEXMETValuacion> lista, String trxName) throws Exception{
		MEXMETValuacion pNew = null;
		for(int i = 0; i < lista.size(); i++){
			MEXMETValuacion tmpLine = lista.get(i);
			if(pNew == null){
				pNew = tmpLine;
			}else{
				if(!tmpLine.getValue().equals(pNew.getValue())){
					if(!pNew.save(trxName)){
						s_log.log(Level.SEVERE, "save ", "No se guardo la valuacion");
						throw new Exception("NoSaveVal");						
					}
				}	
				pNew = tmpLine;
			}
			if(i+1 == lista.size()){
				if(!tmpLine.save(trxName)){
					s_log.log(Level.SEVERE, "save ", "No se guardo la valuacion");
					throw new Exception("NoSaveVal");
				}
			}
		}		
	}

	/**
	 * Borramos el contenido de la tabla temporal
	 * @param ctx
	 * @param AD_Session_ID
	 * @param trxName
	 */
	public static void delete(Properties ctx, int AD_Session_ID, String trxName){
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		/*sql.append("delete from EXME_T_Valuacion where AD_Session_ID = ?");*/
		
		//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
		sql.append("SELECT EXME_T_Valuacion_ID FROM EXME_T_Valuacion WHERE AD_SESSION_ID = ? ");

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {    		
    		pstmt = DB.prepareStatement(sql.toString(), trxName);
    		pstmt.setInt(1, AD_Session_ID);
    		rs = pstmt.executeQuery();

    		while (rs.next()) {
    			MEXMETValuacion obj = new MEXMETValuacion(ctx, rs, null);
    			if (!obj.delete(true, trxName)) 
    				s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
    			obj = null;    			
    		}
    		
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);    		
    	} finally {
    		DB.close(rs, pstmt);
    	}
	}
}
