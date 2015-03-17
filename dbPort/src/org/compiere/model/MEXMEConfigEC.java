/*
 * Created on 08-feb-2005
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo para acceso a configuraci&oacute;n de expediente cl&iacute;nico.<p>
 *
 * <b>Modificado: </b> $Author: mrojas $<p>
 * <b>En :</b> $Date: 2006/09/14 21:37:23 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.10 $
 */
public class MEXMEConfigEC extends MConfigEC {

	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	/** Static logger */
	@SuppressWarnings("unused")
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigEC.class);

	/**
	 * @param ctx
	 * @param ID
	 */
	public MEXMEConfigEC(Properties ctx, int ID, String trxName) {
		super(ctx, ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 */
	public MEXMEConfigEC(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	/**
	 *  After Save
	 *  @author Lorena Lama
	 *  @param newRecord new
	 *  @param success success
	 *  @return true
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success){
			return success;
		}
		if (is_Changed()) {
			resetCache();
		}
		//actualiza las propiedades si es nuevo registro, o si se activa la configuracion - Lama
		if (newRecord || is_Changed()) {
			// si se desactiva la cofig se resetea el ctx - Lama
			if (is_ValueChanged("isActive") && !isActive()) {
				deactivate();
			}
			else {
				// solo si la config aplica para la organizacion de logueo
				if (getAD_Org_ID() == Env.getAD_Org_ID(getCtx()) 
						|| getAD_Org_ID() == get(getCtx(), get_TrxName()).getAD_Org_ID()) {
					resetCtx();
				}
			}
		}
		return true;
	} //  afterSave
	
	/**
	 *  After Detete
	 *  * @author Lorena Lama
	 *  @param success success
	 *  @return true
	 */
	protected boolean afterDelete(boolean success) {
		resetCache();
		deactivate();
		return true;
	}
	
	public void resetCache(){
		//validamos que el objeto sea de la misma organizacion que el ctx, o cero
		final String key = getKeyName(this);
		MConfigEC retValue = (MConfigEC) s_cache.get(key);
		if (retValue != null
				//&& (retValue.getAD_Org_ID() == getAD_Org_ID() || retValue.getAD_Org_ID() == 0)
				) {
			s_cache.remove(key);
			s_cache.put(key, this);
		}
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
		if (getAD_Org_ID() == Env.getAD_Org_ID(getCtx()) || getAD_Org_ID() == 0)
			setCtx(getCtx(), get_TrxName());
	}
	/**
	 * Actualiza/Elimina las propiedades del ctx
	 * @param ctx
	 * @param trxName
	 * @author lorena lama
	 */
	public static void setCtx(Properties ctx, String trxName) {
		// config. precios
		MConfigEC m_configEC = get(ctx, trxName);
		if (m_configEC == null) {
			ctx.setProperty("#Privado",DB.TO_STRING(false));
			ctx.setProperty("#M_PriceListSE_ID", "0");
			ctx.setProperty("#M_PriceListReg_ID", "0");
			return;
		}
		m_configEC.resetCtx();
	}
}
