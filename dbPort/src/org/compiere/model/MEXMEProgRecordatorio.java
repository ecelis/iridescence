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
import org.compiere.util.Utilerias;

/**
 * Clase para la programacion de recordatorios
 * 
 * @author Lizeth de la Garza
 */
public class MEXMEProgRecordatorio extends X_EXME_ProgRecordatorio {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static CLogger		log					= CLogger.getCLogger(MEXMEProgRecordatorio.class);

	public MEXMEProgRecordatorio(Properties ctx, int EXME_ProgRecordatorio_ID, String trxName) {
		super(ctx, EXME_ProgRecordatorio_ID, trxName);
	}

	public MEXMEProgRecordatorio(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private String	unidad	= null;

	public String getUnidad() {
		if (unidad == null && MEXMEProgRecordatorio.UNIDAD_Day.equals(StringUtils.trimToEmpty(super.getUnidad()))) {
			unidad = Utilerias.getMsg(getCtx(), "msj.edad.dd");
		}
		return unidad;
	}

	private String	email	= null;

	public String getEmail() {
		if (email == null) {
			email = Utilerias.getYesNoMsg(getCtx(), isEMail());
		}
		return email;
	}

	private String	sms	= null;

	public String getSms() {
		if (sms == null) {
			sms = email = Utilerias.getYesNoMsg(getCtx(), isSMS());
		}
		return sms;
	}

	private MEXMECDS	cds	= null;

	public MEXMECDS getCds() {
		if (cds == null) {
			cds = new MEXMECDS(getCtx(), getRecord_ID(), null);
		}
		return cds;
	}

	private String	reminders	= null;

	/**
	 * Obtener el mensaje para mostrar del documento pendiente
	 * 
	 * @return
	 */
	public String getDocumentTrans() {
		String string = null;
		if (getTipoRecordatorio() != null) {
			if (getTipoRecordatorio().equals(TIPORECORDATORIO_EXME_CitaMedica)) {
				MEXMECitaMedica cita = new MEXMECitaMedica(getCtx(), getRecord_ID(), null);
				string = cita.getName();
			} else if (getTipoRecordatorio().equals(TIPORECORDATORIO_EXME_ActPacienteIndH)) {
				MEXMEActPacienteIndH actPac = new MEXMEActPacienteIndH(getCtx(), getRecord_ID(), null);
				string = (Utilerias.getMsg(getCtx(), "tab.servicios") + ":" + actPac.getDocumentNo());
			} else if (getTipoRecordatorio().equals(TIPORECORDATORIO_EXME_Interconsulta)) {
				MEXMEInterconsulta inter = new MEXMEInterconsulta(getCtx(), getRecord_ID(), null);
				string = (Utilerias.getMsg(getCtx(), "msg.histRecord.solicitudEncame") + ":" + inter.getDocumentNo());
			} else if (getTipoRecordatorio().equals(TIPORECORDATORIO_EXME_PreOperatorio)) {
				MEXMEPreOperatorio pre = new MEXMEPreOperatorio(getCtx(), getRecord_ID(), null);
				string = (Utilerias.getMsg(getCtx(), "title.solicitudCirugia") + ":" + pre.getValue());
			} else if (getTipoRecordatorio().equals(TIPORECORDATORIO_ClinicalDecison) || getTipoRecordatorio().equals(TIPORECORDATORIO_Others)) {
				string = getObservaciones();
			}
		}
		return string;
	}

	public String getReminders() {
		if (getTipoRecordatorio() != null) {
			if (getTipoRecordatorio().equals(TIPORECORDATORIO_EXME_CitaMedica)) {
				reminders = getMsjCitaEmail(getCtx(), getRecord_ID());
			} else if (getTipoRecordatorio().equals(TIPORECORDATORIO_EXME_ActPacienteIndH)) {
				reminders = getMsjActEmail(getCtx(), getRecord_ID());
			} else if (getTipoRecordatorio().equals(TIPORECORDATORIO_EXME_Interconsulta)) {
				reminders = getMsjInterEmail(getCtx(), getRecord_ID());
			} else if (getTipoRecordatorio().equals(TIPORECORDATORIO_EXME_PreOperatorio)) {
				reminders = getMsjQuiroEmail(getCtx(), getRecord_ID());
			} else if (getTipoRecordatorio().equals(TIPORECORDATORIO_ClinicalDecison) || getTipoRecordatorio().equals(TIPORECORDATORIO_Others)) {
				reminders = getObservaciones();
			}
		}
		return reminders;
	}

	/** @deprecated use {@link MEXMECitaMedica#getMsjEmail()} instead */
	public static String getMsjCitaEmail(Properties ctx, int recordID) {
//		String msj = null;
//		MEXMECitaMedica cita = new MEXMECitaMedica(ctx, recordID, null);
//		msj =
//			Utilerias.getMsg(ctx, "citas.citaDe") + cita.getEspecialidad().getName() + " " + Utilerias.getMsg(ctx, "msj.fechaProgramada") + ":"
//				+ Constantes.getSdfFechaHora().format(new Date(cita.getFechaHrCita().getTime()));
		return new MEXMECitaMedica(ctx, recordID, null).getMsjEmail();
	}

	/** @deprecated use {@link MEXMECitaMedica#getMsjSMS()} instead */
	public static String getMsjCitaSMS(Properties ctx, int recordID) {
//		String msj = null;
//		MEXMECitaMedica cita = new MEXMECitaMedica(ctx, recordID, null);
//		msj =
//			Utilerias.getMsg(ctx, "msg.histRecord.citaDe") + StringUtils.replaceChars(cita.getEspecialidad().getName(), " ", "+") + "+"
//				+ Utilerias.getMsg(ctx, "msg.histRecord.FechaProg") + ":"
//				+ StringUtils.replaceChars(Constantes.getSdfFechaHora().format(new Date(cita.getFechaHrCita().getTime())), " ", "+");
		return new MEXMECitaMedica(ctx, recordID, null).getMsjSMS();
	}

	/** @deprecated use {@link MEXMEActPacienteIndH#getMsjEmail()} instead */
	public static String getMsjActEmail(Properties ctx, int recordID) {
//		String msj = null;
//		MEXMEActPacienteIndH actPacIndH = new MEXMEActPacienteIndH(ctx, recordID, null);
//		msj =
//			Utilerias.getMsg(ctx, "title.solicitudServ") + ":" + actPacIndH.getDocumentNo() + " " + Utilerias.getMsg(ctx, "msj.fechaProgramada")
//				+ ":" + Constantes.getSdfFecha().format(new Date(actPacIndH.getDateOrdered().getTime()));
		return new MEXMEActPacienteIndH(ctx, recordID, null).getMsjEmail();
	}

	/** @deprecated use {@link MEXMEActPacienteIndH#getMsjSMS()} instead */
	public static String getMsjActSMS(Properties ctx, int recordID) {
//		String msj = null;
//		MEXMEActPacienteIndH actPacIndH = new MEXMEActPacienteIndH(ctx, recordID, null);
//		msj =
//			Utilerias.getMsg(ctx, "msg.histRecord.solServSMS") + ":" + actPacIndH.getDocumentNo() + "+"
//				+ Utilerias.getMsg(ctx, "msg.histRecord.FechaProg") + ":"
//				+ StringUtils.replaceChars(Constantes.getSdfFecha().format(new Date(actPacIndH.getDateOrdered().getTime())), " ", "+");
		return new MEXMEActPacienteIndH(ctx, recordID, null).getMsjSMS();
	}

	/** @deprecated use {@link MEXMEInterconsulta#getMsjEmail()} instead */
	public static String getMsjInterEmail(Properties ctx, int recordID) {
//		String msj = null;
//		MEXMEInterconsulta inter = new MEXMEInterconsulta(ctx, recordID, null);
//		msj =
//			Utilerias.getMsg(ctx, "msg.histRecord.solicitudEncame") + ":" + inter.getDocumentNo() + " "
//				+ Utilerias.getMsg(ctx, "msj.fechaProgramada") + ":" + Constantes.getSdfFecha().format(new Date(inter.getDateDoc().getTime()));
		return new MEXMEInterconsulta(ctx, recordID, null).getMsjEmail();
	}

	/** @deprecated use {@link MEXMEInterconsulta#getMsjSMS()} instead */
	public static String getMsjInterSMS(Properties ctx, int recordID) {
//		String msj = null;
//		MEXMEInterconsulta inter = new MEXMEInterconsulta(ctx, recordID, null);
//		msj =
//			Utilerias.getMsg(ctx, "msg.histRecord.solicitudEncameSMS") + ":" + inter.getDocumentNo() + "+"
//				+ Utilerias.getMsg(ctx, "msg.histRecord.FechaProg") + ":"
//				+ StringUtils.replaceChars(Constantes.getSdfFecha().format(new Date(inter.getDateDoc().getTime())), " ", "+");
		return new MEXMEInterconsulta(ctx, recordID, null).getMsjSMS();
	}

	/** @deprecated use {@link MEXMEPreOperatorio#getMsjEmail()} instead */
	public static String getMsjQuiroEmail(Properties ctx, int recordID) {
//		String msj = null;
//		MEXMEPreOperatorio quiro = new MEXMEPreOperatorio(ctx, recordID, null);
//		msj =
//			Utilerias.getMsg(ctx, "title.solicitudCirugia") + ":" + quiro.getValue() + " " + Utilerias.getMsg(ctx, "msj.fechaProgramada") + ":"
//				+ Constantes.getSdfFecha().format(new Date(quiro.getFechaProg().getTime()));
		return new MEXMEPreOperatorio(ctx, recordID, null).getMsjEmail();
	}

	/** @deprecated use {@link MEXMEPreOperatorio#getMsjSMS()} instead */
	public static String getMsjQuiroSMS(Properties ctx, int recordID) {
//		String msj = null;
//		MEXMEPreOperatorio quiro = new MEXMEPreOperatorio(ctx, recordID, null);
//		msj =
//			Utilerias.getMsg(ctx, "msg.histRecord.solCirugiaSMS") + ":" + quiro.getValue() + "+" + Utilerias.getMsg(ctx, "msg.histRecord.FechaProg")
//				+ ":" + StringUtils.replaceChars(Constantes.getSdfFecha().format(new Date(quiro.getFechaProg().getTime())), " ", "+");
		return new MEXMEPreOperatorio(ctx, recordID, null).getMsjSMS();
	}

	/**
	 * Obtener la programacion de recordatorio dado el paciente y el documento configurado
	 * 
	 * @param ctx
	 * @param pacID
	 * @param tipoRecord
	 * @param recordID
	 * @param trxName
	 * @return
	 */
	public static MEXMEProgRecordatorio getProgRecordatorio(Properties ctx, int pacID, String tipoRecord, int recordID, String trxName) {
		MEXMEProgRecordatorio progRec = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT EXME_ProgRecordatorio.* FROM EXME_ProgRecordatorio  ")
				.append(" INNER JOIN EXME_HistRecordatorio hist ON (hist.EXME_ProgRecordatorio_ID = EXME_ProgRecordatorio.EXME_ProgRecordatorio_ID)")
				.append(" WHERE hist.EXME_Paciente_ID = ? AND EXME_ProgRecordatorio.TipoRecordatorio = ? AND EXME_ProgRecordatorio.Record_ID = ?")
				.append(" AND EXME_ProgRecordatorio.IsActive = 'Y'");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ProgRecordatorio"));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacID);
			pstmt.setString(2, tipoRecord);
			pstmt.setInt(3, recordID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				progRec = new MEXMEProgRecordatorio(ctx, rs, trxName);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return progRec;
	}

	public static List<MEXMEProgRecordatorio> getHistoria(Properties ctx, String tipoRecordatorio, String trxName) {
		List<MEXMEProgRecordatorio> lst = new ArrayList<MEXMEProgRecordatorio>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT prog.* FROM EXME_ProgRecordatorio prog WHERE  prog.TipoRecordatorio = ? AND prog.IsActive = 'Y'");
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, tipoRecordatorio);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEProgRecordatorio prog = new MEXMEProgRecordatorio(ctx, rs, trxName);
				lst.add(prog);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	public static List<MEXMEProgRecordatorio> getPendingProg(Properties ctx, int pacID, String trxName) {

		List<MEXMEProgRecordatorio> list = new ArrayList<MEXMEProgRecordatorio>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT DISTINCT EXME_ProgRecordatorio.* FROM EXME_ProgRecordatorio ")
			.append(" INNER JOIN EXME_HistRecordatorio hist ON (hist.EXME_ProgRecordatorio_ID = EXME_ProgRecordatorio.EXME_ProgRecordatorio_ID) ")
			.append(" WHERE hist.EXME_Paciente_ID = ? AND EXME_ProgRecordatorio.IsActive = 'Y'  ")
			.append(
				" AND TO_DATE(TO_CHAR(hist.FechaProg, 'DD/MM/YYYY'), 'DD/MM/YYYY') >=  TO_DATE(TO_CHAR(" + DB.TO_DATE(DB.getTimestampForOrg(ctx))
					+ ", 'DD/MM/YYYY'), 'DD/MM/YYYY') ");

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEProgRecordatorio prog = new MEXMEProgRecordatorio(ctx, rs, trxName);
				list.add(prog);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
}
