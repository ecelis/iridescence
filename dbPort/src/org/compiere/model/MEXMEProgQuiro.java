package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;
import org.joda.time.DateTime;

import com.ecaresoft.util.ErrorList;

/**
 * @author odelarosa
 * 
 */
public class MEXMEProgQuiro extends MProgQuiro {

	private static CLogger s_log = CLogger.getCLogger(MEXMEProgQuiro.class);
	private static final long serialVersionUID = -7697913380254028827L;

	public static List<MEXMEProgQuiro> get(Properties ctx, int quiroId, String status, Date start, Date end, int estServId, String trxName) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEProgQuiro> lst = new ArrayList<MEXMEProgQuiro>();
		try {
			List<Object> params = new ArrayList<Object>();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  EXME_ProgQuiro pq ");
			sql.append("  INNER JOIN exme_quirofano q ");
			sql.append("  ON pq.exme_quirofano_id = q.exme_quirofano_id ");
			sql.append("WHERE ");
			sql.append("  pq.fechaProg BETWEEN ");
			sql.append("  to_date(? ,'DD/MM/YYYY HH24:MI' ) AND ");
			sql.append("  to_date(? ,'DD/MM/YYYY HH24:MI' ) AND ");
			
			params.add(sdf.format(start) + " 00:00");
			params.add(sdf.format(end) + " 23:59");
			
			if(estServId > 0){
				sql.append("  q.exme_estserv_id = ? AND ");
				params.add(estServId);
			}
			
			if (status != null) {
				sql.append("  trim(pq.docstatus) = ? AND ");
				params.add(status);
			}

			if (quiroId != -1) {
				sql.append("  pq.EXME_Quirofano_ID = ? AND ");
				params.add(quiroId);
			}

			sql.append("  trim(pq.docstatus) NOT ");
			sql.append("  IN (? ,? ) ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MEXMEProgQuiro.Table_Name, "pq"));
			sql.append("ORDER BY ");
			sql.append("  pq.EXME_Quirofano_ID, ");
			sql.append("  pq.fechaProg ");

			params.add(MEXMEProgQuiro.DOCSTATUS_Closed);
			params.add(MEXMEProgQuiro.DOCSTATUS_Canceled);

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEProgQuiro(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}

	public static List<MEXMEProgQuiro> getFromQuirofano(Properties ctx, int quiroId, int medId, Date fechaIni, Date fechaFin, String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEProgQuiro> lst = new ArrayList<MEXMEProgQuiro>();
		try {
			List<Object> params = new ArrayList<Object>();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  EXME_ProgQuiro ");
			sql.append("WHERE ");
			sql.append("  case when trim(docstatus) = ? then fechaInicio >= ? else fechaProg >= ? end AND ");
			sql.append("  case when trim(docstatus) = ? then fechaInicio <= ? else fechaProg <= ? end AND ");
			sql.append("  trim(docStatus) <> ? ");

			params.add(DOCSTATUS_Closed);
			params.add(new Timestamp(fechaIni.getTime()));
			params.add(new Timestamp(fechaIni.getTime()));

			params.add(DOCSTATUS_Closed);
			params.add(new Timestamp(fechaFin.getTime()));
			params.add(new Timestamp(fechaFin.getTime()));

			params.add(DOCSTATUS_Canceled);

			if (quiroId > 0) {
				sql.append(" AND EXME_Quirofano_ID = ? ");
				params.add(quiroId);
			}

			if (medId > 0) {
				sql.append(" AND EXME_Medico_ID = ? ");
				params.add(medId);
			}

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MEXMEProgQuiro.Table_Name));

			sql.append(" ORDER BY ");
			sql.append("  fechaProg DESC ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEProgQuiro(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}

	/**
	 * @param ctx
	 * @param EXME_ProgQuiro_ID
	 * @param trxName
	 */
	public MEXMEProgQuiro(Properties ctx, int EXME_ProgQuiro_ID, String trxName) {
		super(ctx, EXME_ProgQuiro_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEProgQuiro(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public List<Integer> getInterventions() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> lst = new ArrayList<Integer>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select i.EXME_Intervencion_ID from  EXME_ProgQuiroDet pqd");
			sql.append(" inner join EXME_Intervencion i on (pqd.EXME_Intervencion_ID = i.EXME_Intervencion_ID) ");
			sql.append(" where pqd.EXME_ProgQuiro_ID = ? and pqd.isActive = 'Y' and i.isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MProgQuiroDet.Table_Name, "pqd"));

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_ProgQuiro_ID());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(rs.getInt(1));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}
	
	public List<Integer> getEquipment() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> lst = new ArrayList<Integer>();
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  exme_equipo_id ");
			sql.append("FROM ");
			sql.append("  exme_equipoh ");
			sql.append("WHERE ");
			sql.append("  exme_progquiro_id = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEEquipoH.Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_ProgQuiro_ID());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(rs.getInt(1));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}
	
	public List<MEXMEEquipoH> getEquipmentH() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEEquipoH> lst = new ArrayList<MEXMEEquipoH>();
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  exme_equipoh ");
			sql.append("WHERE ");
			sql.append("  exme_progquiro_id = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEEquipoH.Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_ProgQuiro_ID());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEEquipoH(getCtx(), rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}
	
	public static List<Integer> getQuestionnaires(int progId, String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> lst = new ArrayList<Integer>();
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT ");
			sql.append("  (exme_cuestionario_id) ");
			sql.append("FROM ");
			sql.append("  exme_respuestacuestionario ");
			sql.append("WHERE ");
			sql.append("  ad_table_id = ? AND ");
			sql.append("  record_id = ? ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Table_ID);
			pstmt.setInt(2, progId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(rs.getInt(1));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	public boolean isEditable() {
		return !(DOCSTATUS_ActiveAndNonClose.equals(getDocStatus().trim()) || DOCSTATUS_Closed.equals(getDocStatus().trim()));
	}

	public String validate(String trxName) {
		StringBuilder str = new StringBuilder();
		if (!MEXMEProgQuiro.DOCSTATUS_ActiveAndNonClose.equals(getDocStatus())) {

			if (MProgQuiro.isOccupied(Env.getCtx(), getEXME_Quirofano_ID(), getEXME_ProgQuiro_ID(), getFechaProg(), getFechaFin(), trxName)) {
				str.append(Utilerias.getAppMsg(getCtx(), "error.progQuir.ocupado"));
			}

			if (StringUtils.isEmpty(str.toString())) {
				int horas = getDuracion().intValue() / 60;
				int minutos = getDuracion().intValue() % 60;

				String error = new MEXMEQuirofano(getCtx(), getEXME_Quirofano_ID(), null).valido(String.valueOf(horas), String.valueOf(minutos), getFechaProg());
				if (error != null) {
					str.append(Utilerias.getAppMsg(getCtx(), error));
				}
			}
			
			for (int id : getEquipment()) {
				if (MEqQuirofano.isBusy(getCtx(), id, getFechaProg(), getFechaFin(), getEXME_ProgQuiro_ID(), trxName)) {
					str.append(Utilerias.getAppMsg(getCtx(), "msj.equipoNoDisponible", new MEXMEEquipo(getCtx(), id, null).getName()));
				}
			}
			
		}
		return str.toString();
	}
	
	public String move(Date fechaIni, Date fechaFin, String trxName){
		String error = null;
		DateTime dateTime = new DateTime(fechaIni);
		DateTime dateTime2 = new DateTime(fechaFin);
		
		setFechaProg(new Timestamp(fechaIni.getTime()));
		setHoraInicio(new BigDecimal(dateTime.getHourOfDay()));
		long time = fechaFin.getTime() - fechaIni.getTime();
		time = (time / 1000) / 60;
		setDuracion(new BigDecimal(time));
		
		setFechaFin(new Timestamp(dateTime2.toDate().getTime()));
		setHoraFin(new BigDecimal(dateTime2.getHourOfDay()));
		
		error = validate(trxName);
		
		if (StringUtils.isEmpty(error)) {
			if (save(trxName)) {
				for (MEXMEEquipoH equipoH : getEquipmentH()) {
					equipoH.setFecha_Ini(getFechaProg());
					equipoH.setFecha_Fin(new Timestamp(new DateTime(getFechaProg()).plusMinutes(getDuracion().intValue()).toDate().getTime()));

					if (!equipoH.save(trxName)) {
						error = Utilerias.getAppMsg(getCtx(), "error.guardar");
						break;
					}
				}
			} else {
				error = Utilerias.getAppMsg(getCtx(), "error.guardar");
			}
		}
		
		return error;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		if (success && DOCSTATUS_Canceled.equals(getDocStatus())) {
			final List<MMovement> lst = MMovement.getFromProgQuirofano(getCtx(), getEXME_ProgQuiro_ID(), null);
			for (final MMovement movement : lst) {
				if(MMovement.DOCSTATUS_Drafted.equals(movement.getDocStatus())){
					final ErrorList errorList = new ErrorList();
					if(X_M_Movement.DOCSTATUS_Drafted.equals(movement.getDocStatus()) && !movement.cancelMovement(errorList)){
						log.saveError("Error", errorList.toString());
						break;
					}
					success  = errorList.isEmpty();
				}
//				if (!MMovement.DOCSTATUS_Closed.equals(movement.getDocStatus()) 
//						|| !MMovement.DOCSTATUS_Reversed.equals(movement.getDocStatus()) 
//						|| !MMovement.DOCSTATUS_Voided.equals(movement.getDocStatus())) {
//					success = movement.voidIt();
//					if (success) {
//						movement.setDocStatus(MMovement.DOCACTION_Void);
//						movement.save(get_TrxName());
//					} else {
//						break;
//					}
//				}
			}
		}
		return success;
	}
	
	/**
	 * Obtener movimientos en estado borrador, en progreso y completados relacionados a la cirugíaa
	 * 
	 * @return Listado de movimientos o vacío
	 */
	public List<MMovement> getMovements() {
		List<Object> params = new ArrayList<Object>();

		params.add(getEXME_ProgQuiro_ID());
		params.add(X_M_Movement.DOCSTATUS_Drafted);
		params.add(X_M_Movement.DOCSTATUS_InProgress);
		params.add(X_M_Movement.DOCSTATUS_Completed);

		StringBuilder sql = new StringBuilder();

		sql.append("  exme_progquiro_id = ? ");
		sql.append("  and docstatus in (?, ?, ?) ");

		Query query = new Query(getCtx(), MMovement.Table_Name, sql.toString(), null);
		query.setParameters(params);
		query.setOnlyActiveRecords(true);
		query.addAccessLevelSQL(true);
		
		return query.list();
	}

	/**
	 * Metodo para obtener listado de quirofanos que no esten pendientes con la
	 * cirugia 
	 * 
	 * @param Properties
	 *            ctx
	 * @param int ctaPacID
	 * @param String
	 *            trxName
	 **/
	public static List<MEXMEProgQuiro> getSurgery(Properties ctx, int ctaPacID, String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEProgQuiro> lst = new ArrayList<MEXMEProgQuiro>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT * FROM  EXME_ProgQuiro");
			sql.append(" WHERE DocStatus not in (");
			sql.append(DB.TO_STRING(DOCSTATUS_Closed) + "," + DB.TO_STRING(DOCSTATUS_Canceled));
			sql.append(" ) AND EXME_CtaPac_ID = ?");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MProgQuiro.Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEProgQuiro(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}

}
