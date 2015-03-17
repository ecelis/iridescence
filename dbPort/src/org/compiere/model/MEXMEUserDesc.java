package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEUserDesc extends X_EXME_UserDesc{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMEBitacora.class);
    
    	
	public MEXMEUserDesc (Properties ctx, int EXME_UserDesc_ID, String trxName)
	{
		super (ctx, EXME_UserDesc_ID, trxName);
	/** if (EXME_UserDesc_ID == 0)
	{
	setAD_User_ID (0);
	setEXME_Descuentos_ID (0);
	setEXME_UserDesc_ID (0);
	setValue (null);
	}
	 */
	}
	/** Load Constructor 
	@param ctx context
	@param rs result set 
	@param trxName transaction
	*/
	public MEXMEUserDesc (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
	
	//busca un registro en la tabla exme_userdesc y valida que el descuento ingresado en facturaExt sea menor que el
	//descuento maximo asignado para este user
	public static MEXMEUserDesc getFromDesc(Properties ctx, int AD_User_ID, String trxName ){//BigDecimal aValidar, String trxName ){
	       
	       MEXMEUserDesc retValue = null;
	       
	       PreparedStatement pstmt = null;
	       ResultSet rs = null;
	       
	       StringBuilder sql = null;
	       
	       try{
	           
	           sql = new StringBuilder().append(" SELECT EXME_UserDesc.EXME_UserDesc_ID, EXME_Descuentos.Max_Desc, EXME_Descuentos.Min_Desc FROM EXME_UserDesc ")
	           	.append(" INNER JOIN EXME_Descuentos on (EXME_Descuentos.EXME_Descuentos_ID =EXME_UserDesc.EXME_Descuentos_ID) ")
	        	.append(" WHERE EXME_UserDesc.isActive = 'Y' ")
	            .append(" AND EXME_UserDesc.AD_User_ID = ? ");//AND EXME_Descuentos.Max_Desc >= ? AND EXME_Descuentos.Min_Desc <= ?");
	           
	           sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_UserDesc"));
	           
	           pstmt = DB.prepareStatement(sql.toString(), trxName);
	           pstmt.setInt(1, AD_User_ID);
	           //pstmt.setBigDecimal(2, aValidar);
	           //pstmt.setBigDecimal(3, aValidar);
	           rs = pstmt.executeQuery();
	           
	           if (rs.next()) {
	               retValue = new MEXMEUserDesc(ctx, rs.getInt("EXME_UserDesc_ID"), trxName);
	               retValue.setMaxDesc(rs.getBigDecimal("Max_Desc"));
	               retValue.setMinDesc(rs.getBigDecimal("Min_Desc"));
	           
	           }
	           
	       }catch (Exception e) {
	           s_log.log(Level.SEVERE, sql.toString(), e);

	       } finally {
	    	   DB.close(rs, pstmt);
	           pstmt = null;
	           rs = null;
	       }

	       return retValue;
	   }
	
	
	private BigDecimal maxDesc = null;


	public BigDecimal getMaxDesc() {
		return maxDesc;
	}
	public void setMaxDesc(BigDecimal valorMax) {
		this.maxDesc = valorMax;
	}
	
	private BigDecimal minDesc = null;


	public BigDecimal getMinDesc() {
		return minDesc;
	}
	public void setMinDesc(BigDecimal minDesc) {
		this.minDesc = minDesc;
	}
	

}
