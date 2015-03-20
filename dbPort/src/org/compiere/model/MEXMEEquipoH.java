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
import org.compiere.util.Utilerias;

public class MEXMEEquipoH extends X_EXME_EquipoH {

	private static final long			serialVersionUID	= 4261176962626128734L;
	/** Logger */
	protected transient static CLogger	s_log				= CLogger.getCLogger(MEXMEEquipoH.class);

	private MEXMEMotivoCita				motivoCita;
	private MEXMECtaPac					ctaPac;
	private MEXMEEquipo					equipo;

	public MEXMEEquipoH(final Properties ctx, final int EXME_EquipoH_ID, final String trxName) {
		super(ctx, EXME_EquipoH_ID, trxName);
	}

	public MEXMEEquipoH(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Productos ocupadas por solicitudes de productos
	 * 
	 * @param ctx
	 * @param fecha
	 * @param intervalo
	 * @param trxName
	 * @return
	 *         ** no hay referencias en codigo **
	 */
	public static boolean equiposDisponibles(final Properties ctx, final String fechaIni, //
		final String fechaFin, final List<MEXMEActPacienteInd> lstServicios, final String trxName) {
		// revisar twry
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_EquipoH  ");
		sql.append(" WHERE IsActive = 'Y'  ");
		sql.append(" AND Estatus_equipo IN ( 'M' , 'P' , 'S' )  ");
		sql.append(" AND EXME_Equipo_ID IN ( ? ) ");
		sql.append(" AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  > ?  ");
		sql.append(" AND to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' )  < ?  ");
		sql.append(" AND ( ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ?  ");
		sql.append("  ) OR ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ?  ");
		sql.append("  ) OR ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ?  ");
		sql.append("  ) OR ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ))  ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_Equipo_ID DESC  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			final String equipos = MEXMEEquipoH.getLstEquipos(ctx, lstServicios, trxName);
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, equipos);

			pstmt.setString(2, fechaIni);
			pstmt.setString(3, fechaFin);

			pstmt.setString(4, fechaIni);
			pstmt.setString(5, fechaFin);

			pstmt.setString(6, fechaIni);
			pstmt.setString(7, fechaFin);

			pstmt.setString(8, fechaIni);
			pstmt.setString(9, fechaFin);

			pstmt.setString(10, fechaIni);
			pstmt.setString(11, fechaFin);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			}

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, "getEntries", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return true;

	}

	/**
	 * Nos traemos todos los equipos corrrespondientes a los servicios
	 * que se tienen en la lista(parametro)
	 * 
	 * @param ctx
	 * @param lstServicios
	 * @param trxName
	 * @return
	 */
	public static String getLstEquipos(final Properties ctx, final List<? extends MEXMEActPacienteInd> lstServicios, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT");
		if (DB.isOracle()) { // Note: LISTAGG para Oracle11 !!
			sql.append(" wm_concat(distinct EXME_Equipo_ID) value ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" array_to_string(ARRAY_AGG(distinct EXME_Equipo_ID), ',') AS value ");
		} else {
			sql.append(" null ");
		}
		sql.append(" FROM EXME_EquipoServ ");
		sql.append(" WHERE IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_EquipoServ"));
		if (lstServicios != null && !lstServicios.isEmpty()) {
			sql.append(" AND M_Product_ID IN ( ");
			for (int i = 0; i < lstServicios.size(); i++) {
				if (i > 0) {
					sql.append(", ");
				}
				sql.append(lstServicios.get(i).getM_Product_ID());
			}
			sql.append(" ) ");
		}
		final StringBuilder cdnEquipos = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final String value = DB.getSQLValueString(trxName, sql.toString());
		if (value != null) {
			cdnEquipos.append(value);
		}
		// while (rs.next()) {
		// cdnEquipos.append(rs.getInt(1)).append(",");
		// }
		// cdnEquipos.append("0 ");
		cdnEquipos.append(",0 ");
		return cdnEquipos.toString();

	}

	/**
	 * Lista para actualizar el estatus de los equiposH relacionados a un EXME_ActPacienteIndH_ID
	 * 
	 * @param ctx
	 * @param actPacIndH_ID
	 * @param trxName
	 * @author LRHU
	 * @return
	 */
	public static List<MEXMEEquipoH> getEquiposH(final Properties ctx, final int actPacIndH_ID, final String trxName) {
		List<MEXMEEquipoH> list = new ArrayList<MEXMEEquipoH>();
		try {
			list = new Query(ctx, Table_Name, " EXME_EquipoH.EXME_ActPacienteIndH_ID=? ", trxName)//
				.setParameters(actPacIndH_ID)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" EXME_EquipoH.EXME_EquipoH_ID ")//
				.list();

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}
		return list;
	}
	/**
	 * Lista para actualizar el estatus de los equiposH relacionados a un EXME_ActPacienteIndH_ID
	 * 
	 * @param ctx
	 * @param actPacIndH_ID
	 * @param trxName
	 * @author LRHU
	 * @return
	 */
	public static List<MEXMEEquipoH> getActive(final Properties ctx, final int actPacIndH_ID, final String trxName) {
		List<MEXMEEquipoH> list = new ArrayList<MEXMEEquipoH>();
		try {
			list = new Query(ctx, Table_Name, " EXME_EquipoH.EXME_ActPacienteIndH_ID=? AND trim(Estatus_equipo) NOT IN (?) ", trxName)//
				.setParameters(actPacIndH_ID, ESTATUS_EQUIPO_Cancelled)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" EXME_EquipoH.EXME_EquipoH_ID ")//
				.list();

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}
		return list;
	}

	@Override
	public MEXMEEquipo getEXME_Equipo() throws RuntimeException {
		if (equipo == null) {
			equipo = new MEXMEEquipo(getCtx(), getEXME_Equipo_ID(), get_TrxName());
		}
		return equipo;
	}

	/**
	 * Recupera las programaciones de equipo en un rango de fecha determinado
	 * 
	 * @param ctx
	 * @param equipoId
	 * @param fechaIni
	 * @param fechaFin
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEquipoH> get(final Properties ctx, final int equipoId, final Date fechaIni, final Date fechaFin, final String trxName) {
		return get(ctx, equipoId, fechaIni, fechaFin, false, trxName);
	}
	
	/**
	 * Recupera las programaciones de equipo en un rango de fecha determinado
	 * 
	 * @param ctx
	 * @param equipoId
	 * @param fechaIni
	 * @param fechaFin
	 * @param excludeCancel si es <b>true</b> excluye los registros con estatus "Cancelado"
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEquipoH> get(final Properties ctx, final int equipoId, final Date fechaIni, final Date fechaFin,
		final boolean excludeCancel, final String trxName) {
		return get(ctx, equipoId, 0, fechaIni, fechaFin, excludeCancel, trxName);
	}
		

	/**
	 * Recupera las programaciones de equipo en un rango de fecha determinado
	 * 
	 * @param ctx
	 * @param equipoId
	 * @parame areaId
	 * @param fechaIni
	 * @param fechaFin
	 * @param excludeCancel si es <b>true</b> excluye los registros con estatus "Cancelado"
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEquipoH> get(final Properties ctx, final int equipoId, final int areaId, final Date fechaIni, final Date fechaFin,
		final boolean excludeCancel, final String trxName) {
//		List<MEXMEEquipoH> lst = new ArrayList<MEXMEEquipoH>();
//		try {
			final List<Object> params = new ArrayList<Object>();
			final StringBuilder sql = new StringBuilder();
			// Valida que se haya seleccionado el equipo
			if (equipoId > 0) {
				params.add(equipoId);
				sql.append(" exme_equipo_id = ? AND ");
			}
			// si se indico un area
			else if (areaId > 0) {
				params.add(areaId);
				sql.append(" exme_Area_id = ? AND ");
			}

			sql.append(" fecha_ini >= ? AND fecha_fin <= ? ");
			params.add(fechaIni == null ? null : new Timestamp(fechaIni.getTime()));
			params.add(fechaFin == null ? null : new Timestamp(fechaFin.getTime()));
			if (excludeCancel) {
				sql.append(" AND trim(estatus_equipo) <> ? ");
				params.add(ESTATUS_EQUIPO_Cancelled);
			}

			return new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setParameters(params)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.list();

//		} catch (final Exception e) {
//			s_log.log(Level.SEVERE, null, e);
//		}

//		return lst;
	}

	/**
	 * Valida la disponibilidad del equipo segun el rango de fechas proporcionado
	 * excluye los registros con estatus "Cancelado"
	 * 
	 * @param ctx
	 * @param equipoId excluye el quirofano seleccionado (puede ser 0)
	 * @param equipoHId excluye un registro especifico de programacion
	 * @param fechaIni
	 * @param fechaFin
	 * @param trxName
	 * @return
	 */
	public static boolean isAvailable(final Properties ctx, final int equipoId, final int equipoHId, final Date fechaIni, final Date fechaFin,
		final String trxName) {
		boolean retValue = true;
		try {
			final List<Object> params = new ArrayList<Object>();
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			// sql.append("SELECT exme_equipo_id ");
			// sql.append("FROM exme_equipoh ");
			// sql.append("WHERE ");
			sql.append("( ");
			sql.append("  (? BETWEEN fecha_ini AND fecha_fin) ");
			sql.append("OR ");
			sql.append("  (? BETWEEN fecha_ini AND fecha_fin) ");
			sql.append("OR ");
			sql.append("  (fecha_ini BETWEEN ? AND ?) ");
			sql.append("OR ");
			sql.append("  (fecha_fin BETWEEN ? AND ?) ");
			sql.append(") ");
			// rango de fechas
			params.add(new Timestamp(fechaIni.getTime()));
			params.add(new Timestamp(fechaFin.getTime()));
			params.add(new Timestamp(fechaIni.getTime()));
			params.add(new Timestamp(fechaFin.getTime()));
			params.add(new Timestamp(fechaIni.getTime()));
			params.add(new Timestamp(fechaFin.getTime()));
			// excluye el quirofano seleccionado (puede ser 0)
			sql.append("AND exme_equipo_id=? ");
			params.add(equipoId);
			// excluye canceladas
			sql.append(" AND trim(estatus_equipo)<>? ");
			params.add(X_EXME_EquipoH.ESTATUS_EQUIPO_Cancelled);
			// excluye un registro especifico de programacion
			if (equipoHId > 0) {
				sql.append(" AND exme_equipoh_id<>? ");
				params.add(equipoHId);
			}
			retValue = new Query(ctx, Table_Name, sql.toString(), null)//
				.setParameters(params)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.firstId() <= 0;

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}
		return retValue;
	}

	/**
	 * Valida si tiene o no comentarios en AD_Note relacionados al registro
	 * 
	 * @return
	 */
	public boolean hasComments() {
		return !is_new() && DB.getSQLValue(get_TrxName(), //
			"SELECT count(*) FROM AD_Note WHERE AD_Table_ID=? AND Record_ID=? AND isActive='Y'",//
			get_Table_ID(), get_ID()) > 0;
	}

	/**
	 * Comentarios relacionados a la programacion de equipos
	 * 
	 * @return
	 */
	public List<MNote> getComments() {
//		return is_new() ? new ArrayList<MNote>() : MNote.getList(getCtx(), get_Table_ID(), get_ID(), get_TrxName());
		return super.getAD_Notes();
	}

	@Override
	public MEXMEMotivoCita getEXME_MotivoCita() {
		if (motivoCita == null || getEXME_MotivoCita_ID() <= 0) {
			motivoCita = new MEXMEMotivoCita(getCtx(), getEXME_MotivoCita_ID(), get_TrxName());
		}
		return motivoCita;
	}

	@Override
	public MEXMECtaPac getEXME_CtaPac() {
		if (ctaPac == null || getEXME_CtaPac_ID() <= 0) {
			ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctaPac;
	}
	
	/** Marca todas las programaciones de quirofano (que no esten canceladas) como USED */
	public static boolean completeAll(MEXMEActPacienteIndH indH) {
		// Se completan los equipos relacionados con la orden
		final List<MEXMEEquipoH> listaEquipos = MEXMEEquipoH.getActive(indH.getCtx(), indH.getEXME_ActPacienteIndH_ID(), indH.get_TrxName());
		for (MEXMEEquipoH equipo : listaEquipos) {
			equipo.setEstatus_Equipo(MEXMEEquipoH.ESTATUS_EQUIPO_Used);
			if (!equipo.save()) {
				return false;
			}
		}
		return true;
	}
	/** Marca todas las programaciones de quirofano (que no esten canceladas) como CANCELLED */
	public static boolean voidAll(MEXMEActPacienteIndH indH) {
		// Se completan los equipos relacionados con la orden
		final List<MEXMEEquipoH> listaEquipos = MEXMEEquipoH.getActive(indH.getCtx(), indH.getEXME_ActPacienteIndH_ID(), indH.get_TrxName());
		for (MEXMEEquipoH equipo : listaEquipos) {
			equipo.setEstatus_Equipo(MEXMEEquipoH.ESTATUS_EQUIPO_Cancelled);
			if (!equipo.save()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (!ESTATUS_EQUIPO_Cancelled.equals(getEstatus_Equipo())) {
			if (!MEXMEEquipoH.isAvailable(getCtx(), getEXME_Equipo_ID(), getEXME_EquipoH_ID(), getFecha_Ini(), getFecha_Fin(), get_TrxName())) {
				s_log.log(Level.SEVERE, Utilerias.getMsg(getCtx(), "error.equipment.overlap"));
				return false;//referente a indice de DB IDX_EXME_EQUIPOH01
			}
		}
		return super.beforeSave(newRecord);
	}
}
