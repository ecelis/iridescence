package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Minutes;

public class MEXMEBlockTime extends X_EXME_BlockTime {

	private static final long serialVersionUID = -6078068149231146822L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEBlockTime.class);

	public MEXMEBlockTime(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
    public MEXMEBlockTime(Properties ctx, int EXME_BlockTime_ID, String trxName) {
    	super(ctx, EXME_BlockTime_ID, trxName);
    }
    
    /**
     * Obtiene las blockstime segun la tabla y el registro indicado en el rango de fechas que se indique
     * @param ad_table_id tabla
     * @param record_id registro
     * @param fechaHrIni fecha inicial
     * @param fechaHrFin fecha final
     * @return Lista de blockstime
     */
    public static List<MEXMEBlockTime> getBlocksTime(Properties ctx, int ad_table_id, int record_id, Date fechaHrIni, Date fechaHrFin) {
//    	List<MEXMEBlockTime> blocksTime = new ArrayList<MEXMEBlockTime>();
//    	StringBuilder sql = new StringBuilder();
//		//String dateBeing = Constantes.getSdfFechaHoraBD24().format(fechaHrIni);
//		//String dateEnd = Constantes.getSdfFechaHoraBD24().format(fechaHrFin);
//		//Calendar cal = Calendar.getInstance();
//		//cal.setTimeInMillis(fechaHrIni.getTime());
//		//cal.add(Calendar.YEAR, -2);
//    	
//		sql.append(" SELECT * FROM EXME_BLOCKTIME BT WHERE BT.AD_TABLE_ID = ?  ")
//			.append(" AND BT.RECORD_ID = ? AND BT.ISACTIVE = 'Y' ")
//			.append(" AND (? BETWEEN BT.fechahrini AND BT.fechahrfin ")
//			.append(" OR ? BETWEEN BT.fechahrini AND BT.fechahrfin ")
//			.append(" OR BT.fechahrini BETWEEN ? AND ? ")
//			.append(" OR  BT.fechahrfin BETWEEN ? AND ?) ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", Table_ID, "BT"));
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, ad_table_id);
//			pstmt.setInt(2, record_id);
//			pstmt.setTimestamp(3, new Timestamp(fechaHrIni.getTime()));
//			pstmt.setTimestamp(4, new Timestamp(fechaHrFin.getTime()));
//			pstmt.setTimestamp(5, new Timestamp(fechaHrIni.getTime()));
//			pstmt.setTimestamp(6, new Timestamp(fechaHrFin.getTime()));
//			pstmt.setTimestamp(7, new Timestamp(fechaHrIni.getTime()));
//			pstmt.setTimestamp(8, new Timestamp(fechaHrFin.getTime()));
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				blocksTime.add(new MEXMEBlockTime(Env.getCtx(), rs, null));
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "MEXMEBlockTime.getBlocksTime - sql: " + sql, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
    	return getBlocksTime(ctx, ad_table_id, new int[]{record_id}, fechaHrIni, fechaHrFin);
    }
    
    /**
     * Obtiene las blockstime segun la tabla y el registro indicado en el rango de fechas que se indique
     * @param ad_table_id tabla
     * @param record_id arreglo de record_id (obligatorio, no vacio)
     * @param fechaHrIni fecha inicial
     * @param fechaHrFin fecha final
     * @return Lista de blockstime
     */
	public static List<MEXMEBlockTime> getBlocksTime(Properties ctx, int ad_table_id, int[] recordArray, Date fechaHrIni, Date fechaHrFin) {
		final List<MEXMEBlockTime> blocksTime = new ArrayList<MEXMEBlockTime>();
		if (recordArray == null || recordArray.length == 0) {
			return blocksTime;
		}

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT * FROM EXME_BLOCKTIME BT ");
		sql.append("WHERE BT.AD_TABLE_ID=? ");
		params.add(ad_table_id);

		sql.append(" AND BT.RECORD_ID IN (");
		for (int i = 0; i < recordArray.length; i++) {
			if (i > 0) {
				sql.append(",");
			}
			sql.append(" ? ");// array[i]
			params.add(recordArray[i]);
		}
		sql.append(")");

		sql.append("AND BT.ISACTIVE = 'Y' ");
		sql.append(" AND (? BETWEEN BT.fechahrini AND BT.fechahrfin ");
		sql.append(" OR ? BETWEEN BT.fechahrini AND BT.fechahrfin ");
		sql.append(" OR BT.fechahrini BETWEEN ? AND ? ");
		sql.append(" OR  BT.fechahrfin BETWEEN ? AND ?) ");

		params.add(new Timestamp(fechaHrIni.getTime()));
		params.add(new Timestamp(fechaHrFin.getTime()));
		params.add(new Timestamp(fechaHrIni.getTime()));
		params.add(new Timestamp(fechaHrFin.getTime()));
		params.add(new Timestamp(fechaHrIni.getTime()));
		params.add(new Timestamp(fechaHrFin.getTime()));

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_ID, "BT"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				blocksTime.add(new MEXMEBlockTime(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEBlockTime.getBlocksTime - sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return blocksTime;
	}
    
	/**
	 * Borra todos los eventos relacionados por su UUID
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param uuId
	 *            UUID a borrar
	 * @return Si/No pudo borrar
	 */
	public static boolean deleteAll(Properties ctx, String uuId) {
		boolean deleted = true;

		Trx trx = null;
		PreparedStatement pstmt = null;

		try {
			trx = Trx.get(Trx.createTrxName("blockDelete"), true);

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE ");
			sql.append("FROM ");
			sql.append("  exme_blocktime ");
			sql.append("WHERE ");
			sql.append("  uuid= ? AND ");
			sql.append("  fechaHrIni > ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trx.getTrxName());
			pstmt.setString(1, uuId);
			pstmt.setTimestamp(2, TimeUtil.getInitialRangeT(ctx, new Date()));

			deleted = pstmt.executeUpdate() > 0;
		} catch (final Exception ex) {
			s_log.log(Level.SEVERE, null, ex);
			Trx.rollback(trx);
		} finally {
			Trx.close(trx);
		}
		return deleted;
	}
    
    @Override
    protected boolean beforeSave(boolean newRecord) {
    	return validaBT();
    }
    
    /**
     * Valida que un blockTime no se empalme con alguna cita medica
     * @return true si es valido
     */
	private boolean validaBT() {
		boolean valid = true;

		int minutes = Minutes.minutesBetween(new DateTime(getFechaHrIni()), new DateTime(getFechaHrFin())).getMinutes();

		if (minutes < 5) {
			s_log.saveError("Error", Utilerias.getAppMsg(getCtx(), "error.bloquePequeno"));
			return false;
		}

//		SimpleDateFormat sdf = Constantes.getSdfFechaHoraBD24();

		Interval interval = new Interval(new DateTime(getFechaHrIni()), new DateTime(getFechaHrFin()));

		List<MEXMECitaMedica> lst = MEXMECitaMedica.getCitasCalendar(getCtx(), getRecord_ID(), 0, 0, //
				TimeUtil.getInitialRange(getCtx(), getFechaHrIni()), //
				TimeUtil.getFinalRange(getCtx(), getFechaHrFin()), null);

		if (lst != null && !lst.isEmpty()) {
			for (MEXMECitaMedica cita : lst) {
				Date cMDateF = new Timestamp(cita.getFechaHrCita().getTime() + (cita.getDuracion() * 60000));

				Interval interval2 = new Interval(new DateTime(cita.getFechaHrCita()), new DateTime(cMDateF));
				if (interval.overlaps(interval2)) {
					StringBuilder sb = new StringBuilder();
					sb.append(Utilerias.getAppMsg(getCtx(), "error.citasDetalle.duracion", cita.getMedico().getNombre_Med()));
					sb.append("\n").append(Utilerias.getMsg(getCtx(), "progMed.ProgDate"));
					sb.append(Constantes.SPACE);
					sb.append(Constantes.getSdfFechaMH(getCtx()).format(cita.getFechaHrCita()));

					s_log.saveError("Error", sb.toString());

					valid = false;
					break;
				}
			}
		}

		if (valid) {
			List<MEXMEBlockTime> list = getBlocksTime(getCtx(), getAD_Table_ID(), getRecord_ID(), //
					TimeUtil.getInitialRange(getCtx(), getFechaHrIni()), TimeUtil.getFinalRange(getCtx(), getFechaHrFin()));

			for (MEXMEBlockTime blockTime : list) {

				if (getEXME_BlockTime_ID() != blockTime.getEXME_BlockTime_ID()) {

					Interval interval2 = new Interval(new DateTime(blockTime.getFechaHrIni()), new DateTime(blockTime.getFechaHrFin()));

					if (interval.overlaps(interval2)) {
						StringBuilder sb = new StringBuilder();
						sb.append(Utilerias.getAppMsg(getCtx(), "msj.bloqueoExistente"));
						sb.append("\n").append(Utilerias.getMsg(getCtx(), "progMed.ProgDate"));
						sb.append(Constantes.SPACE);
						sb.append(Constantes.getSdfFechaMH(getCtx()).format(blockTime.getFechaHrIni()));

						s_log.saveError("Error", sb.toString());

						valid = false;
						break;
					}
				}
			}
		}

		return valid;
	}
	
	@Override
	public String toString() {
		return Constantes.getSDFDateTime(getCtx()).format(getFechaHrIni()) + " - " + Constantes.getSDFDateTime(getCtx()).format(getFechaHrFin());
	}
	
}
