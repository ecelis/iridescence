package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Modelo de Notificaciones del Paciente
 * 
 * @author odelarosa
 * 
 */
public class MEXMEPatientNotice extends X_EXME_PatientNotice {

	private static CLogger s_log = CLogger.getCLogger(MEXMEPatientNotice.class);
	private static final long serialVersionUID = 8015365865692887429L;

	/**
	 * Listado de Alertas Relacionadas a una Cita
	 * 
	 * @param ctx
	 *            Context de la App
	 * @param citaId
	 *            Cita
	 * @param trxName
	 *            Trx
	 * @return Una lista de las alertas que se dieron en la cita
	 */
	public static List<MEXMEPatientNotice> getByAppointment(Properties ctx, int citaId, String trxName) {
		List<MEXMEPatientNotice> lst = new ArrayList<MEXMEPatientNotice>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_patientnotice ");
		sql.append("WHERE ");
		sql.append("  exme_citamedica_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append("ORDER BY ");
		sql.append("  created desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, citaId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEPatientNotice(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	/**
	 * Listado de Alertas Relacionadas a un Paciente
	 * 
	 * @param ctx
	 *            Context de la App
	 * @param patientId
	 *            Paciente
	 * @param trxName
	 *            Trx
	 * @return Una lista de las alertas que se dieron al paciente
	 */
	public static List<MEXMEPatientNotice> getByPatient(Properties ctx, int patientId, String trxName) {
		List<MEXMEPatientNotice> lst = new ArrayList<MEXMEPatientNotice>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_patientnotice ");
		sql.append("WHERE ");
		sql.append("  exme_paciente_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append("ORDER BY ");
		sql.append("  created desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, patientId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEPatientNotice(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	/**
	 * Ultima notificacion del paciente
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param pacId
	 *            Id de Paciente
	 * @param alertId
	 *            Alerta a revisar
	 * @param trxName
	 *            Trx
	 * @return Ultima notificacion o nulo en caso de no haber
	 */
	public static MEXMEPatientNotice getLast(Properties ctx, int pacId, int alertId, String trxName) {
		MEXMEPatientNotice notice = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  ac.* ");
		sql.append("FROM ");
		sql.append("  EXME_PatientNotice ac ");
		sql.append("WHERE ");
		sql.append("  ac.exme_paciente_id = ? AND ");
		sql.append("  ac.exme_alerta_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "ac"));
		sql.append("ORDER BY ");
		sql.append("  ac.valid_to desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacId);
			pstmt.setInt(2, alertId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				notice = new MEXMEPatientNotice(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return notice;
	}

	/**
	 * @param ctx
	 * @param EXME_PatientNotice_ID
	 * @param trxName
	 */
	public MEXMEPatientNotice(Properties ctx, int EXME_PatientNotice_ID, String trxName) {
		super(ctx, EXME_PatientNotice_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPatientNotice(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
