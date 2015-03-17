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

/**
 * 
 * @author LLama
 *
 */
public class MEXMETPaqCtaPac extends X_EXME_T_PaqCtaPac {

    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** static logger   */
    private static CLogger s_log = CLogger.getCLogger(MEXMETPaqCtaPac.class);
    
    public MEXMETPaqCtaPac(Properties ctx, int EXME_T_PaqCtaPac_ID, String trxName) {
        super(ctx, EXME_T_PaqCtaPac_ID, trxName);
    }
    
    public MEXMETPaqCtaPac(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    
    /**
     * Elimina los registros de la tabla temporal, para una determinada sesion
     * @param ctx
     * @param AD_Session_ID
     * @param trxName
     * @throws Exception
     */
    public static void borrarLineas(Properties ctx, int AD_Session_ID, String trxName)
            throws Exception {
    	
    	//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_T_PaqCtaPac_ID FROM EXME_T_PaqCtaPac ")
                .append("WHERE EXME_T_PaqCtaPac.AD_Session_ID = ?");

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), trxName);
    		pstmt.setInt(1, AD_Session_ID);
    		rs = pstmt.executeQuery();

    		while (rs.next()) {
    			MEXMETPaqCtaPac obj = new MEXMETPaqCtaPac(ctx, rs, null);
    			if (!obj.delete(true, trxName)) {
    				s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
    				throw new Exception();
    			}    					   				
    			obj = null;    			
    		}

    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);    		
    		throw new Exception();
    	} finally {
    		DB.close(rs, pstmt);
    	}	
        
        //PIRUET
        /*try {
            StringBuilder cadena = new StringBuilder("DELETE FROM EXME_T_PaqCtaPac ")
                .append("WHERE EXME_T_PaqCtaPac.AD_Session_ID = ? ");
            
            String sql = MEXMELookupInfo.addAccessLevelSQL(ctx, cadena.toString(), "EXME_T_PaqCtaPac");
            
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, AD_Session_ID);

            int noReg = pstmt.executeUpdate();
            
            
        } catch (Exception e) {
            throw new Exception();
        } finally {
            DB.close(pstmt);
            pstmt = null;
        }*/
        
    }
    
    /**
     * Regresa los regsitros de la tabla temporal, segun la sesion
     * @param ctx
     * @param AD_Session_ID
     * @param whereclause
     * @param trxName
     * @return
     * @throws Exception
     */
    public static MEXMETPaqCtaPac[] get(Properties ctx, int AD_Session_ID, 
            String whereclause, String trxName) throws Exception {
        
        MEXMETPaqCtaPac[] retValue = null;
        List<MEXMETPaqCtaPac> list = new ArrayList<MEXMETPaqCtaPac>();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            StringBuilder cadena = new StringBuilder(" SELECT EXME_T_PaqCtaPac.* ")
                    .append("FROM EXME_T_PaqCtaPac WHERE AD_Session_ID = ?");
            
            if( whereclause != null )
                cadena.append(whereclause);
            
            String sql = MEXMELookupInfo.addAccessLevelSQL(ctx, cadena.toString(),
                    "EXME_T_PaqCtaPac");
            
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, AD_Session_ID);

            rs = pstmt.executeQuery();

            while (rs.next()){
                MEXMETPaqCtaPac paqCtaPac = new MEXMETPaqCtaPac(ctx, rs, trxName);
                list.add(paqCtaPac);               
            }

        } catch (Exception e) {
            e.printStackTrace();//FIXME
            retValue = null;
        } finally {
        	DB.close(rs, pstmt);
            pstmt = null;
            rs = null;
        }
        
        retValue = new MEXMETPaqCtaPac[list.size()];
        list.toArray(retValue);
        
        return retValue;
    }
    
}
