package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * 
 * @author Unknown
 * 		Modificado Pedro Mendoza 07/08/2012 
 *			Se agregaron validaciones beforeSave usadas en ApptReferral
 *			Se agregaron procesos afterSave usadas en ApptReferral
 */

public class MEXMEReferInpatient extends X_EXME_ReferInpatient {

	public MEXMEReferInpatient(Properties ctx, int EXME_ReferInpatient_ID, String trxName) {
		super(ctx, EXME_ReferInpatient_ID, trxName);
	}

	public MEXMEReferInpatient(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private static final long serialVersionUID = 1L;

	private static CLogger LOGGER = CLogger.getCLogger(MEXMEReferInpatient.class);

	/**
	 * Borrar logicamente los referrals inpatient que se tengan en un Cita Medica.
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 * @return
	 */
	public static boolean deleteAppointmentHospitalReferrals(Properties ctx, int EXME_CitaMedica_ID, String trxName){
		final MEXMEReferInpatient oldReferI = MEXMEReferInpatient.getReferInpatient(ctx, EXME_CitaMedica_ID, trxName);
		if (oldReferI != null) {
			oldReferI.setIsActive(false);
			return oldReferI.save();
		}
		return true;
	}
	public static MEXMEReferInpatient getReferInpatient(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
		MEXMEReferInpatient obj = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_REFERINPATIENT WHERE EXME_CitaMedica_ID = ? AND ISACTIVE = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				obj = new MEXMEReferInpatient(ctx, rs, trxName);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return obj;
	}

	public static MEXMEReferInpatient getReferInpatientFromPatient(Properties ctx, int EXME_PACIENTE_ID, int AD_ORG_DEST_ID, String trxName) {
		MEXMEReferInpatient obj = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_REFERINPATIENT WHERE EXME_PACIENTE_ID = ? AND AD_ORG_DEST_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_PACIENTE_ID);
			pstmt.setInt(2, AD_ORG_DEST_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				obj = new MEXMEReferInpatient(ctx, rs, trxName);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return obj;
	}

	/**
	 * Returns a referrals list for the current organization (from the context)
	 * @param ctx The application context
	 * @param trxName A transaction name
	 * @return A referrals list.
	 */
	public static List<MEXMEReferInpatient> getForOrg(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_referinpatient ");
		sql.append("WHERE ");
		sql.append("  isactive = 'Y' AND ");
		sql.append("  status <> ? AND ");
		sql.append("  ad_org_dest_id = ?");
		
		List<MEXMEReferInpatient> list = new ArrayList<MEXMEReferInpatient>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, MEXMEReferInpatient.STATUS_CN);
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEReferInpatient(ctx, rs, trxName));
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	
	public static MEXMEReferInpatient get(Properties ctx, int referId, String trxName) {
		MEXMEReferInpatient retVal = null;
		
		String sql = "SELECT * FROM EXME_ReferInpatient WHERE EXME_ReferInpatient_ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, referId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				retVal = new MEXMEReferInpatient(ctx, rs, trxName);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}
		
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (getFecha() == null) {
			LOGGER.saveError("Error", Utilerias.getMsg(getCtx(), "validacion.fecha.requeridaPac"));
			return false;
		} else if (getFecha().before(DB.convert(getCtx(), new Date()))) {
			LOGGER.saveError("Error", Utilerias.getMsg(getCtx(), "validacion.fecha.mayorPac"));
			return false;
		}
		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(isActive()){
			/**
			 * Solo puede haber un tipo de referral activo por cita medica
			 */
			return MEXMEReferPhysician.deleteAppointmentPhysicianReferrals(getCtx(), getEXME_CitaMedica_ID(), get_TrxName());
		}
		return true;
	}
	
}
