package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 * Patient Log
 * 
 * @author Lorena Lama
 * 
 */
public class MEXMEPatientLog extends X_EXME_PatientLog {

	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MEXMEPatientLog.class);

	/**
	 * 
	 * @param ctx
	 * @param EXME_PatientLog_ID
	 * @param trxName
	 */
	private MEXMEPatientLog(Properties ctx, int EXME_PatientLog_ID, String trxName) {
		super(ctx, EXME_PatientLog_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 */
	public MEXMEPatientLog(Properties ctx, String trxName) {
		super(ctx, 0, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPatientLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private List<MChangeLog> changelog = null;
	private MEXMEPaciente patient = null;
	private String optionName = null;

	/**
	 * 
	 * @param ctx
	 * @param EXME_Patient_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPatientLog> getList(Properties ctx, int EXME_Patient_ID, String trxName) {
		return getList(ctx, EXME_Patient_ID, trxName, null);
	}

	/**
	 * Gets the patient log
	 * 
	 * @param ctx
	 * @param EXME_Patient_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPatientLog> getList(Properties ctx, int EXME_Patient_ID, String trxName, StringBuffer whereclause, Object... params) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<MEXMEPatientLog> retvalue = new ArrayList<MEXMEPatientLog>();
		try {
			sql.append("SELECT EXME_PatientLog.* FROM EXME_PatientLog ");
			sql.append("WHERE EXME_PatientLog.isActive = 'Y' ");
			sql.append(" AND EXME_Paciente_ID = ? ");
			if (whereclause != null) {
				sql.append(whereclause);
			}
			sql.append(" ORDER BY Created DESC ");
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			pstmt.setInt(index++, EXME_Patient_ID);
			for (int i = 0; i < params.length; i++) {
				DB.setParameter(pstmt, index++, params[i]);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retvalue.add(new MEXMEPatientLog(ctx, rs, null));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retvalue;
	}

	/**
	 * Gets change log records related to the patient log
	 * 
	 * @param reload
	 * @return
	 */
	public List<MChangeLog> getList(boolean reload) {
		if ((changelog == null || reload) && getEventChangeLog().equals(EVENTCHANGELOG_Update)) {
			changelog = getList();
		}
		return changelog;
	}

	/**
	 * Gets change log records related to the patient log
	 * 
	 * @param ctx
	 * @param EXME_Patient_ID
	 * @param trxName
	 * @return
	 */
	public List<MChangeLog> getList() {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		final List<MChangeLog> retvalue = new ArrayList<MChangeLog>();
		try {
			sql.append("SELECT AD_ChangeLog.* FROM AD_ChangeLog ");
			sql.append("WHERE AD_Table_ID = ? ");
			sql.append("AND Record_ID = ? ");
			sql.append("AND AD_Session_ID = ? ");
			sql.append("AND EventChangeLog = ? ");
			// 1 second difference
			if(DB.isOracle()){
				sql.append("AND abs(to_number(? - created) * (24*60*60*1000)) <= 1000 ");
			} else if(DB.isPostgreSQL()){
				sql.append("AND (? - created) <= interval '1 second' "); 
			}
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, new Object[] { MEXMEPaciente.Table_ID, getEXME_Paciente_ID(), getAD_Session_ID(), getEventChangeLog(),
					getCreated() });

			rs = pstmt.executeQuery();
			while (rs.next()) {
				retvalue.add(new MChangeLog(getCtx(), rs, null));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retvalue;
	}

	/**
	 * Register when a patient record is created / updated / reviewed
	 * 
	 * @param EXME_Patiend_ID
	 *            (mandatory)
	 * @param eventChangeLog
	 *            created / updated / reviewed (mandatory)
	 * @param action
	 *            window / form / struts (mandatory when eventChangeLog is 'reviewed')
	 * @param recordID
	 *            AD_Window_ID / AD_Form_ID (mandatory when eventChangeLog is 'reviewed')
	 * @return
	 */
	public boolean saveLog(int EXME_Patient_ID, String eventChangeLog, String action, int recordID) {
		if (EXME_Patient_ID > 0 && eventChangeLog != null) {
			setEXME_Paciente_ID(EXME_Patient_ID);
			setAD_Table_ID(MEXMEPaciente.Table_ID);
			setAD_Session_ID(Env.getAD_Session_ID(getCtx()));
			setEventChangeLog(eventChangeLog);
			setRecord_ID(recordID);
			setAction(action);

			return save();
		}
		return false;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success){
			return success;
		}
//		if (newRecord && getEventChangeLog().equals(EVENTCHANGELOG_Update)
//				// get patient
//				&& getPatient() != null
//				// notification email
//				&& !StringUtils.isBlank(getPatient().getSupportEMail())) {
//			sendNotification(getPatient().getSupportEMail());
//		} // TODO: 20110507 JCamargo (pendiente esta funcionalidad) 
		return success;
	}

	/**
	 * 
	 * @return
	 */
	public MEXMEPaciente getPatient() {
		if (patient == null && getEXME_Paciente_ID() > 0) {
			patient = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
		}
		return patient;
	}

	/**
	 * Send notification email
	 * 
	 * @author Lorena Lama
	 * @param password
	 * @return
	 */
	public boolean sendNotification(String email) {
		boolean retValue = false;
		try {
			String html_msg = StringUtils.replaceEach(Msg.translate(getCtx(), "patientlog.mailBody"), new String[] { "{0}" },
					new String[] { Constantes.getSDFDateTime(getCtx()).formatConvert(getCreated()) });
			retValue = Utilerias.sendMail(getCtx(), html_msg, true, Msg.translate(getCtx(), "patientlog.mailSubj"), email);
		} catch (Exception ex) {
			log.log(Level.SEVERE, "sendPassword" + ex.getMessage(), ex);
		}
		return retValue;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOptionName() {
		if (optionName == null && getEventChangeLog().equals(EVENTCHANGELOG_View)) {
			String table = null;
			if (getAction().equals(MEXMEPatientLog.ACTION_Form)) {
				table = MForm.Table_Name;
			} else if (getAction().equals(MEXMEPatientLog.ACTION_Window)) {
				table = MWindow.Table_Name;
//			} else if (getAction().equals(MEXMEPatientLog.ACTION_Struts)) {
//				table = MEXMEStruts.Table_Name;
			}
			if (table != null) {
				boolean base = Language.isBaseLanguage(Env.getAD_Language(getCtx()));
				StringBuffer sql = new StringBuffer("SELECT Name FROM ");
				sql.append(table).append(base ? "" : "_Trl");
				sql.append(" WHERE ").append(table).append("_ID = ? ");
				sql.append(base ? "" : " AND AD_Language = ? ");
				if (base) {
					optionName = DB.getSQLValueString(null, sql.toString(), getRecord_ID());
				} else {
					optionName = DB.getSQLValueString(null, sql.toString(), getRecord_ID(), Env.getAD_Language(getCtx()));
				}
			}
		}
		return optionName;
	}

}
