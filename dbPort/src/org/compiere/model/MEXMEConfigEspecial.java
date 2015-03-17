/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author lorena
 *@deprecated
 */
public class MEXMEConfigEspecial extends X_EXME_ConfigEspecial {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param EXME_ConfigEspecial_ID
	 * @param trxName
	 */
	public MEXMEConfigEspecial(Properties ctx, int EXME_ConfigEspecial_ID, String trxName) {
		super(ctx, EXME_ConfigEspecial_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConfigEspecial(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	/**
	 * Obtiene la configuracion especial segun el cliente y/o organizacion
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	
	public static MEXMEConfigEspecial get(Properties ctx, String trxName) {
		return MConfigEspecial.get(ctx, trxName);
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		if (!success){
			return success;
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
		return success;
	}
	@Override
	protected boolean afterDelete(boolean success) {
		deactivate();
		return success;
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
	 * @param ctxMEXMEConfigFE.
	 * @param trxName
	 * @author lorena lama
	 */
	public static void setCtx(Properties ctx, String trxName) {
		// config. derechohabiencia
		MEXMEConfigEspecial m_configEsp = get(ctx, trxName);
		if (m_configEsp == null) {
			ctx.setProperty("#EditValuePac",DB.TO_STRING(false));
			ctx.setProperty("#INFO_LABORAL",DB.TO_STRING(false));
			ctx.setProperty("#INFO_MILITAR",DB.TO_STRING(false));
			return;
		}
		m_configEsp.resetCtx();
	}
	/**
	 * Agrega al ctx propiedades requeridas para el mtto de ingreso paciente
	 * @author lorena lama
	 */
	private void resetCtx() {
		Env.setContext(getCtx(), "#EditValuePac", DB.TO_STRING(isEditValuePac()));
		Env.setContext(getCtx(), "#INFO_LABORAL", DB.TO_STRING(isCaptDatosLab()));
		Env.setContext(getCtx(), "#INFO_MILITAR", DB.TO_STRING(isVerInfMilitar()));
	}
}
