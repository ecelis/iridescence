/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author mrojas
 *@deprecated se deja de usar en eCS
 */
public class MEXMEConfigDer extends X_EXME_ConfigDer {

	/**
	 * Seria No.
	 */
	private static final long serialVersionUID = -2069981612143574547L;

	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigDer.class);
	
	
	/**
	 * Constructor from Record Id.
	 * @param ctx The application context.
	 * @param EXME_ConfigDer_ID The record id.
	 * @param trxName the transaction name.
	 */
	public MEXMEConfigDer(Properties ctx, int EXME_ConfigDer_ID, String trxName) {
		super(ctx, EXME_ConfigDer_ID, trxName);
	}

	/**
	 * Constructor from ResultSet.
	 * @param ctx The application context.
	 * @param rs The ResultSet.
	 * @param trxName The transaction name.
	 */
	public MEXMEConfigDer(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Loads the rightholders configuration for the current client. The client
	 * is obtained from the context attributes.
	 * @param ctx The application context.
	 * @param trxName The transaction name.
	 * @return The rightholders configuration or null if there is no record for
	 * the current client.
	 */
	public static MEXMEConfigDer get(Properties ctx, String trxName) {
		
		s_log.finest("Loading rightholders configuration ...");
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
		sql.append("SELECT * FROM EXME_ConfigDer WHERE IsActive = 'Y'")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		sql.append(" ORDER BY AD_Org_ID DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEConfigDer config = null;
		
		pstmt = DB.prepareStatement(sql.toString(), trxName);
		
		try {
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				config = new MEXMEConfigDer(ctx, rs, trxName);
			}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "While loading right holder ....", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		s_log.finest("Configuration loaded? " + (config == null ? "N" : "Y"));
		
		return config;
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
		// config. derechohabiencia
		MEXMEConfigDer m_configDer = get(ctx, trxName);
		if (m_configDer == null) {
			ctx.setProperty("#RightHolder",DB.TO_STRING(false));
			ctx.setProperty("#AllowsRightHolder",DB.TO_STRING(false));
			ctx.setProperty("#AllowsTitleHolder",DB.TO_STRING(false));
			return;
		}
		m_configDer.resetCtx();
	}

	/**
	 *  Agrega al ctx propiedades requeridas para el mtto de ingreso paciente
	 * @author lorena lama
	 */
	private void resetCtx() {
		Env.setContext(getCtx(), "#RightHolder", DB.TO_STRING(isDerechohabiente()));
		Env.setContext(getCtx(), "#AllowsRightHolder", DB.TO_STRING(isPermiteAltaD()));
		Env.setContext(getCtx(), "#AllowsTitleHolder", DB.TO_STRING(isPermiteAltaT()));
	}
	
}
