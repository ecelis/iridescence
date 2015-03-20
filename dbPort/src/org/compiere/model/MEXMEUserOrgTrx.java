/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author LLama
 *
 */
public class MEXMEUserOrgTrx extends X_EXME_UserOrgTrx{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    @SuppressWarnings("unused")
	private static CLogger      s_log = CLogger.getCLogger (MEXMEUserOrgTrx.class);
    
   /** Constructor de MEXMEBitacora
     * @param ctx               
     * @param EXME_Bitacora_ID  
     * @param trxName           
     *
     */
    public MEXMEUserOrgTrx(Properties ctx, int EXME_UserOrgTrx_ID, String trxName) {
        super(ctx, EXME_UserOrgTrx_ID, trxName);
    }
    
    /**
     * Constructor de MEXMEDiarioEnf
     * @param ctx                Propiedades
     * @param rs                 Resultset con que se crea el objeto
     * @param trxName            Nombre de la transaccion
     *
     */
   public MEXMEUserOrgTrx(Properties ctx, ResultSet rs, String trxName) {
       super(ctx, rs, trxName);
   }
   
   /**
    * 
    * @param ctx
    * @param AD_User_ID
    * @param trxName
    * @return
    */
   public static MEXMEUserOrgTrx[] getFromUser(Properties ctx, int AD_User_ID, String trxName){
       
       MEXMEUserOrgTrx[] retValue = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       List<MEXMEUserOrgTrx> list = new ArrayList<MEXMEUserOrgTrx>();
       
       String sql = "SELECT * FROM EXME_UserOrgTrx WHERE EXME_UserOrgTrx.isActive = 'Y' " +
                    "AND EXME_UserOrgTrx.AD_User_ID = ? ";
       
       try{
           
           sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_UserOrgTrx");
           
           pstmt = DB.prepareStatement (sql, trxName);
           pstmt.setInt(1,AD_User_ID);
           rs = pstmt.executeQuery();
           
           while(rs.next()){
               MEXMEUserOrgTrx obj = new MEXMEUserOrgTrx(ctx,rs,trxName);
               list.add(obj);
           }
           
           
       }catch (Exception e) {
           retValue = null;
       }finally{
    	   DB.close(rs, pstmt);
           rs = null;
           pstmt = null;
       }
       
       retValue = new MEXMEUserOrgTrx[list.size()];
       list.toArray(retValue);
       
       return retValue;
   }
   
   /**
    * 
    * @param ctx
    * @param AD_OrgTrx_ID
    * @param trxName
    * @return
    */
   public static MEXMEUserOrgTrx[] getFromOrgTrx(Properties ctx, int AD_OrgTrx_ID, String trxName){
       
       MEXMEUserOrgTrx[] retValue = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       List<MEXMEUserOrgTrx> list = new ArrayList<MEXMEUserOrgTrx>();
       
       String sql = "SELECT * FROM EXME_UserOrgTrx WHERE EXME_UserOrgTrx.isActive = 'Y' " +
                    "AND EXME_UserOrgTrx.AD_OrgTrx_ID = ? OR EXME_UserOrgTrx.AD_OrgTrx_ID = 0 ";
       
       try{
           
           sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_UserOrgTrx");
           
           pstmt = DB.prepareStatement (sql, trxName);
           pstmt.setInt(1,AD_OrgTrx_ID);
           rs = pstmt.executeQuery();
           
           while(rs.next()){
               MEXMEUserOrgTrx obj = new MEXMEUserOrgTrx(ctx,rs,trxName);
               list.add(obj);
           }
           
       }catch (Exception e) {
           retValue = null;
       }finally{
    	   DB.close(rs, pstmt);
           rs = null;
           pstmt = null;
       }
       
       retValue = new MEXMEUserOrgTrx[list.size()];
       list.toArray(retValue);
       
       return retValue;
   }
   
   
   /**
    * 
    * @param ctx
    * @param AD_User_ID
    * @param trxName
    * @return
    */
   public static MEXMEUserOrgTrx get(Properties ctx, int AD_User_ID, int AD_OrgTrx_ID, String trxName){
       
       MEXMEUserOrgTrx retValue = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;

       String sql = "SELECT * FROM EXME_UserOrgTrx WHERE EXME_UserOrgTrx.isActive = 'Y' " +
                    "AND EXME_UserOrgTrx.AD_User_ID = ? " +
                    "AND EXME_UserOrgTrx.AD_OrgTrx_ID = ? ";
       try{
           
           sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_UserOrgTrx");
           
           pstmt = DB.prepareStatement (sql, trxName);
           pstmt.setInt(1,AD_User_ID);
           pstmt.setInt(2,AD_OrgTrx_ID);
           rs = pstmt.executeQuery();
           
           if(rs.next())
               retValue = new MEXMEUserOrgTrx(ctx,rs,trxName);
           
       }catch (Exception e) {
           retValue = null;
       }finally{
    	   DB.close(rs, pstmt);
           rs = null;
           pstmt = null;
       }
       
       return retValue;
   }

}
