/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Alejandro
 * 
 */
public class MEXMEConfigFE extends X_EXME_ConfigFE {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigFE.class);

	/**
	 * @param ctx
	 * @param EXME_Colonia_ID
	 * @param trxName
	 */

	public MEXMEConfigFE(Properties ctx, int EXME_ConfigFE_ID, String trxName) {
		super(ctx, EXME_ConfigFE_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConfigFE(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEConfigFE get(Properties ctx, String trxName) {

		if (ctx == null) {
			return null;
		}

		MEXMEConfigFE retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * ");
		sql.append("FROM EXME_ConfigFE ");
		sql.append("WHERE EXME_ConfigFE.IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY AD_Org_ID DESC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MEXMEConfigFE(ctx, rs, trxName);
			

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
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
	 * @param ctx
	 * @param trxName
	 * @author lorena lama
	 */
	public static void setCtx(Properties ctx, String trxName) {
		// config. facturacion electronica
		MEXMEConfigFE m_configFE = get(ctx, trxName);
		if (m_configFE == null) {
			ctx.setProperty("#ConfigFE",DB.TO_STRING(false));
			return;
		}
		m_configFE.resetCtx();
	}

	/**
	 *  Agrega al ctx propiedades requeridas para el mtto de ingreso paciente
	 * @author lorena lama
	 */
	private void resetCtx() {
		Env.setContext(getCtx(), "#ConfigFE", DB.TO_STRING(true));
	}

}

