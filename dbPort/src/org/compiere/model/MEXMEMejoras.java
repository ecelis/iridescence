/*
 * Created on 30-april-2007
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;
import org.compiere.util.WebEnv;

/**
 * Modelo de Mejoras
 *
 * @author Lorena Lama
 *
 */
public class MEXMEMejoras extends X_EXME_Mejoras {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static logger			*/
	private static CLogger s_log = CLogger.getCLogger(MEXMEMejoras.class);
    
    /**
     * @param ctx
     * @param ID
     */
    public MEXMEMejoras(Properties ctx, int ID, String trxName) {
        super(ctx, ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMEMejoras(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    protected static CCache<String,MEXMEMejoras> s_cache = new CCache<String,MEXMEMejoras>(Table_Name, 1);
	
    /**
     * MEXMEMejoras.isControlExistencias());
     * @param ctx
     * @return
     */
    public static boolean inventories(Properties ctx) {
    	MEXMEMejoras mej = get(ctx);
    	return mej != null && mej.isControlExistencias();
	}
    
    /**
	 * Obtenemos la configuracion de mejoras
	 * de un hospital en particular. (Cliente + Organizacion).
	 * @author Lama
	 * @param ctx
	 * @return
	 */
    public static MEXMEMejoras get(Properties ctx) {
    	return get(ctx, null);
	}
    
    /**
	 * Si existe, obtiene la configuracion de la cache
	 * @author Lama
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MEXMEMejoras get(Properties ctx, String trxName) {
		MEXMEMejoras retValue = null;
		if (ctx != null) {
			retValue = getFromCache(ctx);
			if (retValue == null) {
				retValue = getFromBD(ctx, trxName);
			}
		}
		return retValue;
	}

	/**
	 * Si existe, obtiene la configuracion de la cache
	 * @author Lama
	 * @param ctx
	 * @return
	 */
	private static MEXMEMejoras getFromCache(Properties ctx) {
		MEXMEMejoras retValue = s_cache.get(getKeyName(ctx, Table_Name, false));//Org
		if (retValue == null) {
			retValue = s_cache.get(getKeyName(ctx, Table_Name, true));// Client
		}
		return retValue;
	}

  	/**
	 * Obtenemos la configuracion de mejoras de un hospital en particular.
	 * (Cliente + Organizacion).
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	private static MEXMEMejoras getFromBD(final Properties ctx, final String trxName) {
		MEXMEMejoras retValue = new Query(ctx, Table_Name, "", trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" EXME_Mejoras.AD_Org_ID DESC ")//
			.first();
		if (retValue != null) {
			s_cache.put(getKeyName(retValue), retValue);
		}
		return retValue;
	}
	
//    /**
//     * Obtenemos la configuracion de mejoras de un hospital en particular.
//     * (Cliente + Organizacion).
//     * 
//     * @param ctx
//     * @return
//     */
//    public static MEXMEMejoras find(final Properties ctx, final String trxName){
//        MEXMEMejoras retValue = null;
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        sql.append("SELECT * FROM EXME_Mejoras WHERE EXME_Mejoras.IsActive='Y' ");
//        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
//        sql.append(" ORDER BY EXME_Mejoras.AD_Org_ID DESC ");
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				retValue = new MEXMEMejoras(ctx, rs, trxName);
//				s_cache.put(getKeyName(retValue), retValue);
//			}
//        }catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		} finally {
//			DB.close(rs,pstmt);
//		}
//		return retValue;
//	}
    
    /**
	 *  After Save - Actualizamos la cache
	 *  @author Lama
	 *  @param newRecord new
	 *  @param success success
	 *  @return true
	 */
    @Override
	protected boolean afterSave(boolean newRecord, boolean success) {
    	if (!success){
			return success;
		}
    	if(is_Changed()){
			resetCache();
		}
		//actualiza las propiedades si es nuevo registro, o si se activa la configuracion - Lama
		if (newRecord ||is_Changed()){
			//si se desactiva la cofig se resetea el ctx - Lama
			if(is_ValueChanged("isActive") && !isActive()){
				deactivate();
			} else {
				// solo si la config aplica para la organizacion de logueo
				if (getAD_Org_ID() == Env.getAD_Org_ID(getCtx()) 
						|| getAD_Org_ID() == get(getCtx(), get_TrxName()).getAD_Org_ID()){
					resetCtx();
				}
			}
		}
		return true;
	} //  afterSave
	
	/**
	 *  After Detete - - Actualizamos la cache
	 *  @author Lama
	 *  @param success success
	 *  @return true
	 */
	@Override
	protected boolean afterDelete(boolean success) {
		resetCache();
		deactivate();
		return true;
	}

	/**
	 *  Reset Cache
	 *  @author Lama
	 */
	public void resetCache(){
		//validamos que el objeto sea de la misma organizacion que el ctx, o cero
		final String key = getKeyName(this);
		MEXMEMejoras retValue = (MEXMEMejoras) s_cache.get(key);
		if (retValue != null
				//&& (retValue.getAD_Org_ID() == getAD_Org_ID() || retValue.getAD_Org_ID() == 0)
				) {
			s_cache.remove(key);
			s_cache.put(key, this);
		}
	}
	
	/**
	 * Metodo para retornar la version de diagnosticos configurada
	 * 
	 * @author Armando
	 * @param Properties ctx
	 * @return Int
	 */
	// public static int getDiagnosticoHdr(Properties ctx) {
	// MEXMEMejoras mejora = MEXMEMejoras.get(ctx, null);
	// if (mejora == null)
	// return 0;
	// return mejora.getEXME_DiagnosticoHdr_ID();
	// }

	/**
	 * Metodo para retornar la version de intervenciones configurada
	 * 
	 * @author Armando
	 * @param Properties  ctx
	 * @return Int
	 */
	// public static int getIntervencionHdr(Properties ctx) {
	// MEXMEMejoras mejora = MEXMEMejoras.get(ctx, null);
	// if (mejora == null)
	// return 0;
	// return mejora.getEXME_IntervencionHdr_ID();
	// }
	
	/**
	 * beforeSave
	 * @author Alejandro
	 * @param boolean newRecord
	 * @return boolean
	 */
	protected boolean beforeSave(boolean newRecord) {
		//Si no esta configurada la CURP como mandatoria, que guarde el registro de
		//copiar CURP en historia como false
		if(!isCURPMandatory()){
			setCurpHist(false);
		}
		return true;
	}
	/**
	 * Agrega al ctx propiedades requeridas para el mtto de ingreso paciente
	 * @param ctx
	 * @author lorena lama
	 */
	public static void setCtx(Properties ctx) {
		setCtx(ctx,null);
	}
	/**
	 * Remueve o actualiza la configuracion, que aplica para la organizacion
	 * @author lorena lama
	 */
	public void deactivate() {
		if (getAD_Org_ID() == Env.getAD_Org_ID(getCtx()) || getAD_Org_ID() == 0) {
			setCtx(getCtx(), get_TrxName());
		}
	}
	/**
	 * Actualiza/Elimina las propiedades del ctx
	 * @param ctx
	 * @param trxName
	 * @author lorena lama
	 */
	public static void setCtx(Properties ctx, String trxName) {
		// config. derechohabiencia
		MEXMEMejoras m_mejoras = get(ctx, trxName);
		if (m_mejoras == null) {
//			ctx.setProperty("#CURPMandatory",DB.TO_STRING(false));
//			ctx.setProperty("#CURPHist",DB.TO_STRING(false));
			ctx.setProperty("#MAXQUERYRECORDS", "500");//default
//			ctx.setProperty("#INVENTORIES", DB.TO_STRING(false));
			return;
		}
		m_mejoras.resetCtx();
	}
	/**
	 *  Agrega al ctx propiedades requeridas para el mtto de ingreso paciente
	 * @author lorena lama
	 */
	private void resetCtx() {
//		Env.setContext(getCtx(), "#CURPMandatory", DB.TO_STRING(isCURPMandatory()));
//		Env.setContext(getCtx(), "#CURPHist", DB.TO_STRING(isCurpHist()));
		Env.setContext(getCtx(), "#MAXQUERYRECORDS", super.getMaxQueryRecords());
//		Env.setContext(getCtx(), "#INVENTORIES", super.isControlExistencias());
	}
	
	/**
	 * Numero de registros permitidos en tablas configuradas como de alto volumen
	 * @param ctx
	 * @return
	 */
	public static int getMaxQueryRecords(Properties ctx){
		int no = Env.getContextAsInt(ctx, "#MAXQUERYRECORDS");
		if (no <= 0) {
			MEXMEMejoras retValue = MEXMEMejoras.get(ctx);
			if (retValue != null)
				no = retValue.getMaxQueryRecords();
			if (no <= 0) {
				String value = Utilerias.getPropertiesFromXPT(WebEnv.NoRegistros);
				if (value != null)
					try {
						no = Integer.parseInt(value);
					} catch (NumberFormatException e) {
						s_log.severe(e.getMessage());
					}
			}
			if (no <= 0)
				no = 500;// default
			ctx.setProperty("#MAXQUERYRECORDS", String.valueOf(no));// Actualiza la prop.
		}
		return no;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param productID
	 * @return
	 */
	public static boolean isNDC(Properties ctx, int productID) {
		boolean retValue = productID > 0;
		if (retValue) {
			MEXMEMejoras config = get(ctx);
			if (config == null) {
				retValue = false;
			}
			else {
				retValue = config.getTemporaryNDC() != productID && config.getNotAvailableNDC() != productID;
			}
		}
		return retValue;
	}


	/** Get Recalculate Taxes when Billing.
	@return Recalculate Taxes when Billing	  */
	public static boolean isCalcImpFact (final Properties ctx) 
	{
		boolean retValue = true;
		final MEXMEMejoras config = get(ctx);
		if (config == null) {
			retValue = false;
		} else {
			retValue = config.isCalcImpFact();
		}
		return retValue;
	}

	 /**
     * Obtenemos la configuracion de mejoras de un hospital en particular.
     * (Cliente + Organizacion).
     * 
     * @param ctx
     * @return
     */
    public static boolean isControlaExistencias(
    		final int AD_Client_ID, final int AD_Org_ID, final String trxName){
        
//        boolean retValue = false;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
        .append("SELECT ControlExistencias FROM EXME_Mejoras WHERE EXME_Mejoras.IsActive='Y' ")
        .append(" AND AD_Client_ID = ? AND AD_Org_ID = ? ")
        .append(" ORDER BY EXME_Mejoras.AD_Org_ID DESC ");
        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//		
//        try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, AD_Client_ID);
//			pstmt.setInt(2, AD_Org_ID);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				retValue = rs.getString(1).equals("Y");
//			}
//			
//        }catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		} finally {
//			DB.close(rs,pstmt);
//		}
		return StringUtils.equals("Y", 
			DB.getSQLValueString(trxName, sql.toString(), AD_Client_ID, AD_Org_ID));
	}
    
}