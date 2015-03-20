/* Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.*/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo de Layout de Inventarios creada el 9 de Agosto de 2007
 * 
 * @author Rodrigo Montemayor
 * @version 1.0 
 */
public class MEXMELayoutUrg extends X_EXME_LayoutUrg {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static CLogger      s_log = CLogger.getCLogger (MEXMELayoutUrg.class);
    
    /**
     * Load Constructor
     *
     * @param ctx context
     * @param rs result set
     * @param trxName the transaction name
     */
    public MEXMELayoutUrg (Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    public MEXMELayoutUrg (Properties ctx, int M_Inventory_ID, String trxName) {
        super (ctx, M_Inventory_ID, trxName);
    }
    
    /**
     * Obtener el numero maximo de layout en la tabla.
     * @return
     */
    public static int getMaxLayoutNo(Properties ctx) {
        int max = -1; //Si hay algun error, regresa -1, ya que si regresara 0 se tomar�a como si no tuviera registros.
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = null;
        try{
            sql = new StringBuilder("Select MAX(No_Layout) as max from EXME_LayoutUrg ")            
            	.append(" WHERE isActive = 'Y' ");
            
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_LayoutUrg"));
                  
             pstmt = DB.prepareStatement(sql.toString(), null);
             rs = pstmt.executeQuery();
             if(rs.next()) {
                 if (rs.getString("max") != null) {
                     max = rs.getInt("max");
                 } else {
                     max = 0;
                 }
             }
            
        } catch (Exception e) {
            return -1;
        } finally {
        	DB.close(rs, pstmt);
        }

        return max;
    }
    
    
    
    /**
     * Obtener un registro por un numero de layout dado
     * @return
     */
    public static MEXMELayoutUrg getForLayoutNo(Properties ctx, int layoutNo, String trxName) {
        MEXMELayoutUrg urg = null;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = null;
        try{
            sql = new StringBuilder("Select * from EXME_LayoutUrg where No_Layout = ").append(layoutNo)
                            .append(" and isActive = 'Y'");
            
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_LayoutUrg"));
                  
             pstmt = DB.prepareStatement(sql.toString(), null);
             rs = pstmt.executeQuery();
             if(rs.next()) {
                 urg = new MEXMELayoutUrg(ctx, rs, trxName);
             }
            
            
        } catch (Exception e) {
            return null;
        } finally {
        	DB.close(rs, pstmt);
        }

        return urg;
    }
    
    
    public static Integer[] purgar(Properties ctx, int partnerId, int anio, int periodo, String trxName) throws Exception {
        
        List<Integer> lista = new ArrayList<Integer>();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = null;
        try{
            sql = new StringBuilder("Select * from EXME_LayoutUrg where C_BPartner_ID = ").append(partnerId)
                            .append(" and Anio = ").append(anio)
                            .append(" and Periodo = ").append(periodo)
                            .append(" and isActive = 'Y' ");
            
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_LayoutUrg"));
                  
             pstmt = DB.prepareStatement(sql.toString(), null);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                 if (!lista.contains(rs.getInt("No_Layout"))) {
                     lista.add(rs.getInt("No_Layout"));
                 }
                 MEXMELayoutUrg urg = new MEXMELayoutUrg(ctx, rs, trxName);
                 
                 MEXMELayoutUrgDet.purgar(ctx, urg.getEXME_LayoutUrg_ID(), trxName);
                 
                 if (!urg.delete(true, trxName)) {
                     throw new Exception("Error");
                 }
             }
           
            
        } catch (Exception e) {
            return null;
        } finally {
        	DB.close(rs, pstmt);
        }

        Integer[] retValue = new Integer[lista.size()];
        lista.toArray(retValue);
        return retValue;
                
    }
}
