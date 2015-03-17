package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;


/**
 * Modelo para Rango de Porcentaje de Alimentacion
 *
 * <b>Fecha:</b> 07/Febrero/2006<p>
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2006/04/18 14:38:02 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.1 $
 */
public class MEXMERangoPorcAlim extends X_EXME_Rango_Porc_Alim {
    /** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMERangoPorcAlim.class);
    private static final long serialVersionUID = 1L;
    /**
     * @param ctx
     * @param ID
     */
    public MEXMERangoPorcAlim(Properties ctx, int ID, String trx) {
        super(ctx, ID, trx);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMERangoPorcAlim(Properties ctx, ResultSet rs, String trx) {
        super(ctx, rs, trx);
    }
    
    /**
     * Obtenemos el rango de porcentaje de alimentacion 
     * @param ctx El contexto de la aplicacion
     * @param ingresos El total de ingresos 
     * @return El costo del producto segun la configuracion de precios. Si no lo encuentra entonces 0. 
     */
    public static MEXMERangoPorcAlim getByIndice(Properties ctx, BigDecimal porcentaje, 
            String trxName){
        MEXMERangoPorcAlim rango = null;
        
        String sql = "SELECT * FROM EXME_Rango_Porc_Alim WHERE " +
                //"AD_Client_ID = ? AND 
        		" ? BETWEEN Porcentaje_Inicial AND Porcentaje_Final AND IsActive = 'Y'";
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Rango_Porc_Alim");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            pstmt = DB.prepareStatement(sql, trxName);
            //pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            pstmt.setBigDecimal(1, porcentaje);
            rs = pstmt.executeQuery();
        
            if(rs.next()){
                rango = new MEXMERangoPorcAlim(ctx, rs, trxName);
            }
            
        } catch (Exception e){
            log.log(Level.SEVERE, "get", e);
        } finally {
        	DB.close(rs, pstmt);
        }
        
        return rango;
    }
    
}