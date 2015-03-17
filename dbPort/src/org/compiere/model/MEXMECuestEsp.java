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
 * Modelo del detalle de cuestionario.
 * 
 * @author mrojas
 * @version $Revision: 1.2 $
 */
public class MEXMECuestEsp extends X_EXME_CuestEsp {
	private static final long serialVersionUID = 1L;
    /**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMECuestEsp.class);
	
    /**
     * @param ctx
     * @param EXME_Cuestionario_ID
     * @param trxName
     */
    public MEXMECuestEsp(Properties ctx, int EXME_CuestEsp_ID, String trxName) {
        super(ctx, EXME_CuestEsp_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMECuestEsp(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Obtenemos la relaci�n Cuestionario - Especialidad
     * por medio del ID del cuestionario                .- Expert:Lama
     *  
     * @param ctx
     * @param EXME_Cuestionario_ID
     * @param trxName
     * @return
     */
    public static MEXMECuestEsp getFromCuest(Properties ctx, int EXME_Cuestionario_ID, String trxName) {
       
        MEXMECuestEsp retValue = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        try{
            sql.append("SELECT * FROM EXME_CuestEsp WHERE EXME_Cuestionario_ID = ? AND isActive = 'Y'");
            
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CuestEsp"));

            pstmt = DB.prepareStatement(sql.toString(),trxName);
            pstmt.setInt(1,EXME_Cuestionario_ID);
            rs = pstmt.executeQuery(); 
            
            if(rs.next()){
                retValue = new MEXMECuestEsp(ctx, rs, trxName);
            }
            
        }catch (Exception e) {
          s_log.log(Level.SEVERE, sql.toString(), e);
        }finally {
            sql = null;
            DB.close(rs, pstmt);
        }
        
        return retValue;
    }
    
    public static List<MEXMECuestEsp> getDefaultCuest(Properties ctx, int EXME_Especialidad_ID,  String trxName) {
        
    	List<MEXMECuestEsp> retValue = new ArrayList<MEXMECuestEsp>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

        try{
            sql.append("SELECT * FROM EXME_CuestEsp INNER JOIN EXME_Cuestionario ON EXME_Cuestionario.EXME_Cuestionario_ID = EXME_CuestEsp.EXME_Cuestionario_ID")
            .append(" WHERE EXME_Especialidad_ID = ? AND EXME_CuestEsp.isActive = 'Y' AND EXME_CuestEsp.EsDefault = 'Y' ");
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
            sql.append(" ORDER BY EXME_Cuestionario.Value");

            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_Especialidad_ID);
            rs = pstmt.executeQuery(); 
            
            while (rs.next()){
            	retValue.add(new MEXMECuestEsp(ctx, rs, trxName));
            }
            
        } catch (Exception e) {
          s_log.log(Level.SEVERE, sql.toString(), e);
        } finally {        	
        	DB.close(rs, pstmt);
            sql = null;
            rs = null;
            pstmt = null;
        }
        
        return retValue;
    }

    /**
     * Obtenemos la relaci�n Cuestionario - Especialidad
     * por medio del ID del cuestionario                .- Expert:Lama
     *  
     * @param ctx
     * @param EXME_Cuestionario_ID
     * @param trxName
     * @return
     */
    public static List<MEXMECuestEsp> getLstFromCuest(Properties ctx, int EXME_Cuestionario_ID, String trxName) {
       
    	List<MEXMECuestEsp> retValue = new ArrayList<MEXMECuestEsp>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        try {
            sql.append("SELECT * FROM EXME_CuestEsp WHERE EXME_Cuestionario_ID = ? AND isActive = 'Y'");

            pstmt = DB.prepareStatement(sql.toString(),trxName);
            pstmt.setInt(1,EXME_Cuestionario_ID);
            rs = pstmt.executeQuery(); 
            
            while (rs.next()) {
                retValue.add(new MEXMECuestEsp(ctx, rs, trxName));
            }
            
        } catch (Exception e) {
          s_log.log(Level.SEVERE, sql.toString(), e);
        } finally {
           DB.close(rs, pstmt);
        }
        
        return retValue;
    }
    
}
