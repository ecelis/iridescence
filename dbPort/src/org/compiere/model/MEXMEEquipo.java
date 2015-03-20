package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

/**
 * 
 * 
 */
public class MEXMEEquipo extends X_EXME_Equipo {

	/** Equipos */
	private static final long	serialVersionUID	= 1L;
	/** log */
	private static CLogger		s_log				= CLogger.getCLogger(MEXMEEquipo.class);

	public MEXMEEquipo(Properties ctx, int EXME_Equipo_ID, String trxName) {
		super(ctx, EXME_Equipo_ID, trxName);
	}

	public MEXMEEquipo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @author Expert: Raul Montemayor
	 * @param assetId
	 * @param trxName
	 * @return retorno Regresa true si no existe un registro con el A_Asset_ID igual a parametro assetId
	 */
	public static boolean isNew(int assetId, String trxName) {
		boolean retorno = true;
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM EXME_Equipo ");
		sql.append("WHERE A_Asset_ID = ? ");
		sql.append("AND IsActive = 'Y' ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, assetId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retorno = false;
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEEquipo.isNew", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retorno;
	}

	/***** merge */
	/**
	 * Equipos por cada orden de servicio
	 * @param ctx
	 * @param clausWhere
	 * @param trxName
	 * @return Regresa un listado de historico de equipos con el equipo y el servicio
	 *         con el que se le relaciono
	 */
	public static List<MEXMEEquipoH> getEquiposPorOrden(Properties ctx, String clausWhere, List<Object> params, String trxName) {

		final List<MEXMEEquipoH> list = new ArrayList<MEXMEEquipoH>();

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT EXME_EquipoH.*, e.Name AS NameEquipo, e.Value AS ValueEquipo, ");
		sql.append("    e.EXME_Equipo_ID AS IdEquipo, p.Name AS NameProduct ");
		sql.append("    FROM EXME_EquipoH                        ");
		sql.append("    LEFT JOIN EXME_Equipo e ON EXME_EquipoH.EXME_Equipo_ID = e.EXME_Equipo_ID  ");
		sql.append("    LEFT JOIN M_Product   p ON EXME_EquipoH.M_Product_ID   = p.M_Product_ID  ");
		sql.append("    WHERE EXME_EquipoH.IsActive = 'Y'        ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_EquipoH"));

		if (clausWhere != null) {
			sql.append(clausWhere);
		}
		sql.append(" ORDER BY EXME_EquipoH.EXME_EquipoH_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEXMEEquipoH equipo = new MEXMEEquipoH(ctx, rs, trxName);
				// BasicoTresProps equipoBTP = new BasicoTresProps();
				// equipoBTP.setId(rs.getInt("IdEquipo"));
				// equipoBTP.setNombre(rs.getString("NameEquipo"));
				// equipoBTP.setValue(rs.getString("ValueEquipo"));
				// equipoBTP.setDescripcion(rs.getString("NameProduct"));
				// equipo.setEquipoBTP(equipoBTP);
				list.add(equipo);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEEquipo.getEquiposPorOrden", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Listado de equipos
	 * @param ctx
	 * @param clausWhere
	 * @param params
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEquipo> getEquipo(Properties ctx, String clausWhere, List<Object> params, String trxName) {

		final List<MEXMEEquipo> equipo = new ArrayList<MEXMEEquipo>();

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM EXME_Equipo ");
		sql.append(" WHERE EXME_Equipo.IsActive = 'Y'  ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Equipo"));

		if (clausWhere != null) {
			sql.append(clausWhere);
		}
		sql.append(" ORDER BY EXME_Equipo.Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				equipo.add(new MEXMEEquipo(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEEquipo.getEquipo", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return equipo;
	}

	/**
	 * 
	 * @param ctx
	 * @param areaId
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> get(Properties ctx, int areaId, String trxName) {
		final List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT EXME_Equipo_ID, NAME ");
		sql.append("FROM exme_equipo ");
		sql.append("WHERE exme_area_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, areaId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new KeyNamePair(rs.getInt("EXME_EQUIPO_ID"), rs.getString("NAME")));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}

	/**
	 * Regresa los equipos disponibles en un rango de tiempo
	 * @param ctx
	 * @param EXME_Area_ID
	 * @param fechaIni
	 * @param fechaFin
	 * @param EXME_ActPacienteIndH_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEquipo> getAvailable(final Properties ctx, final int EXME_Area_ID, final Timestamp fechaIni, final Timestamp fechaFin,
		final String trxName) {

		final StringBuilder sql = new StringBuilder();
		// sql.append("SELECT eq.* ");
		// sql.append("FROM ");
		// sql.append("  exme_equipo e ");
		// sql.append("WHERE ");
		sql.append("  EXME_equipo_ID NOT IN ");
		sql.append("  ( ");
		sql.append("    SELECT eh.EXME_equipo_ID ");
		sql.append("    FROM EXME_equipoh eh ");
		sql.append("    WHERE ");
		sql.append("      ( ");
		sql.append("         (? BETWEEN eh.fecha_ini AND eh.fecha_fin) ");// Ini
		sql.append("          OR ");
		sql.append("         (? BETWEEN eh.fecha_ini AND eh.fecha_fin) ");// Fin
		sql.append("          OR ");
		sql.append("         (eh.fecha_ini BETWEEN ? AND ?) "); // ini - fin
		sql.append("          OR ");
		sql.append("         (eh.fecha_fin BETWEEN ? AND ?) ");// ini - fin
		sql.append("      )  ");
		sql.append("      AND trim(eh.estatus_equipo) <> ? ");
		sql.append("  ) ");
		sql.append("  AND EXME_Area_ID = ? ");
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEqQuirofano.Table_Name, "eq"));

		final List<Object> parameters = new ArrayList<Object>();

		parameters.add(fechaIni);
		parameters.add(fechaFin);
		parameters.add(fechaIni);
		parameters.add(fechaFin);
		parameters.add(fechaIni);
		parameters.add(fechaFin);
		parameters.add(MEXMEEquipoH.ESTATUS_EQUIPO_Cancelled);
		parameters.add(EXME_Area_ID);

		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setOnlyActiveRecords(true)//
			.setParameters(parameters)//
			.addAccessLevelSQL(true)//
			.list();
	}

}
