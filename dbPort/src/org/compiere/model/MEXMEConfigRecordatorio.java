package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;
/**
 * Lizeth de la Garza
 * Clase para obtener la configuracion de recordatorios
 * del paciente
 */
public class MEXMEConfigRecordatorio extends X_EXME_ConfigRecordatorio {
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MEXMEConfigRecordatorio.class);
	private boolean validateReq = true;
	
	public MEXMEConfigRecordatorio(Properties ctx, int EXME_ConfigRecordatorio_ID, String trxName) {
		super(ctx, EXME_ConfigRecordatorio_ID, trxName);
	}
	
	public MEXMEConfigRecordatorio(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public boolean isValidateReq() {
		return validateReq;
	}

	public void setValidateReq(boolean validateReq) {
		this.validateReq = validateReq;
	}

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		
		//Verificar que no exista una configuracion
		MEXMEConfigRecordatorio config = MEXMEConfigRecordatorio.getConfigPac(Env.getCtx(), getEXME_Paciente_ID(), true, null);
		if (config != null && config.getEXME_ConfigRecordatorio_ID() != getEXME_ConfigRecordatorio_ID()) {			
			log.saveError(" ", Utilerias.getMessage(Env.getCtx(), null, "msg.ConfigRecordatorio.existe"));
			return false;			
		}
		MEXMEPaciente pac = new MEXMEPaciente(Env.getCtx(), getEXME_Paciente_ID(), get_TrxName());

		//Verifica que el paciente tenga un correo electronico si se especifica que se mande recordatorio por este medio
		if (this.isEMail()) {
			if (pac.getEMail() == null && validateReq) {
				log.saveError(" ", Utilerias.getMessage(Env.getCtx(), null, "msg.ConfigRecordatorio.EMail"));
				return false;
			}
		}
		//Verifica que el paciente tenga un telefono si se especifica que se mande recordatorio por este medio
		if (this.isSMS()) {
			if (pac.getTelCelular() == null && validateReq) {
				log.saveError(" ", Utilerias.getMessage(Env.getCtx(), null, "msg.ConfigRecordatorio.SMS"));
				return false;
			}
		}
		return true;
	} // beforeSave
	
	/**
	 * Lizeth de la Garza
	 * Verifica que el paciente tenga una configuracion
	 * ya establecida
	 * @param ctx Contexto de la aplicacion
	 * @param trxName Nombre de la transaccion
	 * @return boolean
	 * @deprecated 
	 *
	private boolean getConfigRecordatorio(Properties ctx, String trxName) {
		boolean success = true;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT EXME_ConfigRecordatorio_ID FROM EXME_ConfigRecordatorio ")
			.append(" WHERE EXME_Paciente_ID = ? "); 
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ConfigRecordatorio"));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, getEXME_Paciente_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				success = false;				
			}		
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}		
		return success;
	}*/
	
	/**
	 * Lizeth de la Garza
	 * Obtener la configuracion del paciente
	 * @param ctx Contexto de la aplicacion
	 * @param pacID ID del paciente
	 * @param trxName Nombre de la transaccion
	 * @return boolean
	 */
	public static MEXMEConfigRecordatorio getConfigPac(Properties ctx, int pacID, boolean active, String trxName) {
		MEXMEConfigRecordatorio config = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT * FROM EXME_ConfigRecordatorio ")
			.append(" WHERE EXME_Paciente_ID = ? ");
			
			if (active) {
				sql.append(" AND IsActive = 'Y'");
			}
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ConfigRecordatorio"));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				config = new MEXMEConfigRecordatorio(ctx, rs, trxName);				
			}		
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}		
		return config;
	}
}
