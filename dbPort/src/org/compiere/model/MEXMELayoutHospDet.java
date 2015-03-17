/* Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.*/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Modelo de Layout de Hospitalizacion creada el 13 de Agosto de 2007
 * 
 * @author Rodrigo Montemayor
 * @version 1.0 
 */
public class MEXMELayoutHospDet extends X_EXME_LayoutHospDet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static CLogger      s_log = CLogger.getCLogger (MEXMELayoutHospDet.class);
    
    /**
     * Load Constructor
     *
     * @param ctx context
     * @param rs result set
     * @param trxName the transaction name
     */
    public MEXMELayoutHospDet (Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    public MEXMELayoutHospDet (Properties ctx, int EXME_LayoutHospDet_ID, String trxName) {
        super (ctx, EXME_LayoutHospDet_ID, trxName);
    }

    public static Integer[] purgar(Properties ctx, int layoutHospID, String trxName) throws Exception {
        
        List<Integer> lista = new ArrayList<Integer>();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = null;
        try{
            sql = new StringBuilder("Select * from EXME_LayoutHospDet where EXME_LayoutHosp_ID = ").append(layoutHospID)
            	.append(" AND isActive = 'Y' ");
            
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_LayoutHospDet"));
                  
             pstmt = DB.prepareStatement(sql.toString(), null);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                 
                 MEXMELayoutHospDet hospDet = new MEXMELayoutHospDet(ctx, rs, trxName);
                 if (!hospDet.delete(true, trxName)) {
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
