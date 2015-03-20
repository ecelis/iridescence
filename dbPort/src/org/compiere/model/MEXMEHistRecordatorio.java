package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;

/**
 * Clase para obtener el historial de recordatorios del paciente
 * @author Lizeth de la Garza
 *
 */
public class MEXMEHistRecordatorio extends X_EXME_HistRecordatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2980272956602784010L;
	private static CLogger log = CLogger.getCLogger(MEXMEHistRecordatorio.class);

	public MEXMEHistRecordatorio(Properties ctx, int EXME_HistRecordatorio_ID, String trxName) {
		super(ctx, EXME_HistRecordatorio_ID, trxName);
	}

	public MEXMEHistRecordatorio(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	private MUser user = null;

	public MUser getUser() {
		if (user == null){
			user = new MUser(getCtx(), getProg().getAD_User_ID(), null);
		}
		return user;
	}
	
	private MEXMEProgRecordatorio prog = null;

	public MEXMEProgRecordatorio getProg() {
		if (prog == null){
			prog = new MEXMEProgRecordatorio(getCtx(), getEXME_ProgRecordatorio_ID(), null);
		}
		return prog;
	}
	
	private MEXMEPaciente pac = null;

	public MEXMEPaciente getPac() {
		if (pac == null){
			pac = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), null);
		}
		return pac;
	}

	/**
	 * Obtener los documentos pendientes del paciente
	 * (Cita Medica, Sol. de Cirugia, Sol. de Servicios, Sol. de Encame)
	 * @param ctx
	 * @param pacienteID
	 * @param trxName
	 * @return List<ValueNamePair>
	 */
	public static List<ValueNamePair> getPendingDoc(Properties ctx, int pacienteID, String trxName) {
		List<ValueNamePair> list = new ArrayList<ValueNamePair>();
		list.add(new ValueNamePair("-","-"));
		list.addAll(MEXMECitaMedica.getCitasPacPending(ctx, pacienteID, trxName));
		list.addAll(MEXMEInterconsulta.getPacSolPending(ctx, pacienteID, trxName));
		list.addAll(MEXMEPreOperatorio.getPacSolPending(ctx, pacienteID, trxName));
		list.addAll(MEXMEActPacienteIndH.getPacSolPending(ctx, pacienteID, trxName));
		return list;

	}

	/**
	 * Obtiene el Historial de recordatorio del paciente
	 * @param ctx
	 * @param pacID
	 * @param trxName
	 * @return List<MEXMEHistRecordatorio>
	 */

	public static List<MEXMEHistRecordatorio> getHistRecordatorio(Properties ctx, int pacID, String trxName) {
		List<MEXMEHistRecordatorio> list = new ArrayList<MEXMEHistRecordatorio>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT EXME_HistRecordatorio.* FROM EXME_HistRecordatorio ")
			.append(" INNER JOIN EXME_ProgRecordatorio prog ON (prog.EXME_ProgRecordatorio_ID = EXME_HistRecordatorio.EXME_ProgRecordatorio_ID)")
			.append(" WHERE EXME_HistRecordatorio.EXME_Paciente_ID = ?  AND prog.IsActive = 'Y'");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_HistRecordatorio"));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEHistRecordatorio histRec = new MEXMEHistRecordatorio(ctx, rs, trxName);
				list.add(histRec);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return list;
	}

	/**
	 * Obtiene todos los recordatorios pendientes
	 * @param ctx
	 * @param fecha
	 * @param AD_Client_ID
	 * @param AD_Org_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEHistRecordatorio> getRecordatorios(Properties ctx, String fecha, int AD_Client_ID, int AD_Org_ID, String trxName) {
		List<MEXMEHistRecordatorio> list = new ArrayList<MEXMEHistRecordatorio>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fechaIni = null;
		String fechaFin = null;
		if (fecha != null) {
			Calendar c1 = Calendar.getInstance(); 
			Calendar c2 = Calendar.getInstance();
			c1.add(Calendar.MINUTE, -5);
			c2.add(Calendar.MINUTE, 5);
			fechaIni = Constantes.getSdfFechaHora().format(c1.getTime());
			fechaFin = Constantes.getSdfFechaHora().format(c2.getTime());
		}
		try {
			sql.append("SELECT EXME_HistRecordatorio.* FROM EXME_HistRecordatorio ")
			.append(" INNER JOIN EXME_ProgRecordatorio prog ON (prog.EXME_ProgRecordatorio_ID = EXME_HistRecordatorio.EXME_ProgRecordatorio_ID)")
			.append(" WHERE prog.IsActive = 'Y' AND EXME_HistRecordatorio.AD_Client_ID = ? AND EXME_HistRecordatorio.AD_Org_ID = ?");
			if (fecha != null) {
				 sql.append(" AND TO_DATE(TO_CHAR(EXME_HistRecordatorio.FechaProg, 'DD/MM/YYYY HH24:MI'), 'DD/MM/YYYY HH24:MI') ")
			   	 .append(" BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI')  AND TO_DATE(?, 'DD/MM/YYYY HH24:MI') AND EXME_HistRecordatorio.FechaEnv IS NULL");
			}
			//sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_HistRecordatorio"));
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, AD_Org_ID);
			if (fecha != null) {
				pstmt.setString(3, fechaIni);
				pstmt.setString(4, fechaFin);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEHistRecordatorio histRec = new MEXMEHistRecordatorio(ctx, rs, trxName);
				list.add(histRec);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return list;
	}
	
	@Override
	public void setIsEMail(boolean IsEMail) {
		if(IsEMail && getFechaEnv() == null) {
			setFechaEnv(Env.getCurrentDate());
		}
		super.setIsEMail(IsEMail);
	}
	
	@Override
	public void setIsSMS(boolean IsSMS) {
		if(IsSMS && getFechaEnv() == null) {
			setFechaEnv(Env.getCurrentDate());
		}
		super.setIsSMS(IsSMS);
	}
}
