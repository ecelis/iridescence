package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * 
 * @author LLama
 *
 */
public class MEXMEBitacora extends X_EXME_Bitacora{

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMEBitacora.class);
    
   /** Constructor de MEXMEBitacora
     * @param ctx               
     * @param EXME_Bitacora_ID  
     * @param trxName           
     *
     */
    public MEXMEBitacora(Properties ctx, int EXME_Bitacora_ID, String trxName) {
        super(ctx, EXME_Bitacora_ID, trxName);
    }
    
    /**
     * Constructor de MEXMEDiarioEnf
     * @param ctx                Propiedades
     * @param rs                 Resultset con que se crea el objeto
     * @param trxName            Nombre de la transaccion
     *
     */
   public MEXMEBitacora(Properties ctx, ResultSet rs, String trxName) {
       super(ctx, rs, trxName);
   }
   
   
   public static MEXMEBitacora getFromExt(Properties ctx, int EXME_CtaPacExt_ID, int EXME_CtaPac_ID, String trxName ){
       
       MEXMEBitacora retValue = null;
       
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       
       String sql = null;
       
       try{
           
           sql = "SELECT * FROM EXME_Bitacora WHERE isActive = 'Y' "
               + " AND EXME_CtaPacExt_ID = ? AND EXME_CtaPac_ID = ? ";
           
           sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Bitacora");
           
           pstmt = DB.prepareStatement(sql, trxName);
           pstmt.setInt(1, EXME_CtaPacExt_ID);
           pstmt.setInt(2, EXME_CtaPac_ID);
           rs = pstmt.executeQuery();
           
           if (rs.next()) {
               retValue = new MEXMEBitacora(ctx,rs,trxName);
           }
           
       }catch (Exception e) {
           s_log.log(Level.SEVERE, sql.toString(), e);

       } finally {
    	   DB.close(rs,pstmt);
           pstmt = null;
           rs = null;
       }

       return retValue;
   }
   
   public String getIsAutorizado(){
       String autorizado = "N";
       if(isAutorizada())
           autorizado = "Y";
       return autorizado;
   }
   
}
