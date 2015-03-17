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

public class MEXMEConfigMedico extends X_EXME_ConfigMedico{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMECondMedica.class);
	
	public MEXMEConfigMedico(Properties ctx, int EXME_ConfigMedico_ID,
			String trxName) {
		super(ctx, EXME_ConfigMedico_ID, trxName);
	}

	public MEXMEConfigMedico(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	/**
	 * Regresa la configuraci�n del m�dico
	 * @param ctx                Propiedades
	 * @param pacienteID         ID del paciente
	 * @param trxName            Nombre de la transaccion
	 * 
	 */
	public static MEXMEConfigMedico getConfig(Properties ctx, int medicoID, String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		MEXMEConfigMedico obj = null;

		try {
			sql.append("SELECT * FROM EXME_ConfigMedico ");
			sql.append(" WHERE EXME_Medico_ID=? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, medicoID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				obj = new MEXMEConfigMedico(ctx, rs, trxName);
			}
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
			sql = null;
			pstmt = null;
			rs = null;
		}

		return obj;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		boolean save = true;
		if (!this.isMultiWindow() && !this.isSimpleWindow()) {
			s_log.saveError(Utilerias.getMessage(getCtx(), null, "fdm.md.configuration.tipo.error"), "");
			save = false;
		}

		return save;
	}
    
	public static MEXMEConfigMedico getConfigDefault(Properties ctx) {

		MEXMEConfigMedico obj = null;

		try {
			obj = new MEXMEConfigMedico(ctx, 0, null);
			final MEXMEMejoras config = MEXMEMejoras.get(Env.getCtx());
			boolean value = config != null && config.isMDS();
			obj.setDrugDrug(value); // DRUGDRUG
			obj.setDrugFood(value);// DRUGFOOD
			obj.setDrugAller(value);// DRUGALLER
			obj.setMedicalCondition(value);// MEDICALCONDITION
			obj.setDuplicateTherapy(value);// DUPLICATETHERAPY
			obj.setDrugDrugLevel(DRUGDRUGLEVEL_MajorDrugInteraction);// DRUGDRUGLEVEL
			obj.setDrugDrugLevel(DRUGDRUGLEVEL_MajorDrugInteraction);// DRUGFOODLEVEL
			// obj.setDrugAllerLevel(Drug);//DRUGALLERLEVEL
			obj.setMedicalConditionLevel(MEDICALCONDITIONLEVEL_SeverePotentialHazard);// MEDICALCONDITIONLEVEL
			obj.setDuplicateTherapyAllow(value);// DUPLICATETHERAPYALLOW
			obj.setMultiWindow(value);// MULTIWINDOW
			obj.setSimpleWindow(true);// SIMPLEWINDOW
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}

		return obj;
	}

}
