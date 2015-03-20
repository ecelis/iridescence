package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Configuraciones del modulo de odontologia
 * 
 * @author Lorena Lama
 *
 */
public class MEXMEConfigOdonto extends X_EXME_ConfigOdonto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static logger			*/
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigOdonto.class);
	
	public MEXMEConfigOdonto(Properties ctx, int EXME_ConfigOdonto_ID, String trxName) {
		super(ctx, EXME_ConfigOdonto_ID, trxName);
	}

	public MEXMEConfigOdonto(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	protected static CCache<String,MEXMEConfigOdonto> s_cache = new CCache<String,MEXMEConfigOdonto>(Table_Name, 1);
	
	
	 /**
	 * Obtenemos la configuracion de odontologia
	 * de un hospital en particular. (Cliente + Organizacion).
	 * @author Lama
	 * @param ctx
	 * @return
	 */
    public static MEXMEConfigOdonto get(Properties ctx, String trxName) {
		if (ctx == null) {
			return null;
		}
		return get(ctx);
	}
    
    /**
	 * Si existe, obtiene la configuracion de la cache
	 * @author Lama
	 * @param ctx
	 * @return
	 */
	public static MEXMEConfigOdonto get(Properties ctx) {
		MEXMEConfigOdonto retValue = (MEXMEConfigOdonto) s_cache.get(Table_Name);
		if (retValue != null
				// Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID") == Env.getContextAsInt(retValue.getCtx(), "#AD_Session_ID")))
			return retValue;
		return find(ctx, null);
    }

    
    /**
     * Obtenemos la configuracion de odontologia de un hospital en particular.
     * (Cliente + Organizacion).
     * 
     * @param ctx
     * @return
     */
    public static MEXMEConfigOdonto find(Properties ctx, String trxName){
        
        MEXMEConfigOdonto retValue = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append("SELECT * FROM EXME_ConfigOdonto WHERE EXME_ConfigOdonto.IsActive='Y' ")
        	.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name))
        	.append(" ORDER BY EXME_ConfigOdonto.AD_Org_ID DESC ");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
        try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MEXMEConfigOdonto(ctx, rs, trxName);
	
        }catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
        } finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
    
    /**
	 *  After Save - Actualizamos la cache
	 *  @author Lama
	 *  @param newRecord new
	 *  @param success success
	 *  @return true
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success){
			return success;
		}
		if(is_Changed()){
			resetCache();
		}			
		return true;
	} //  afterSave
	
	/**
	 *  After Detete - - Actualizamos la cache
	 *  @author Lama
	 *  @param success success
	 *  @return true
	 */
	protected boolean afterDelete(boolean success) {
		resetCache();
		return true;
	}

	/**
	 *  Reset Cache
	 *  @author Lama
	 */
	public void resetCache(){
		//validamos que el objeto sea de la misma organizacion que el ctx, o cero
		MEXMEConfigOdonto retValue = (MEXMEConfigOdonto) s_cache.get(Table_Name);
		if (retValue != null
				&& (retValue.getAD_Org_ID() == getAD_Org_ID() || retValue.getAD_Org_ID() == 0)) {
			s_cache.remove(retValue);
			s_cache.put(Table_Name, this);
		}
	}

}
