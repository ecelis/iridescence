package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** @deprecated NO UTILIZADA */
public class MBPEquipos {

	private static CLogger	s_log = CLogger.getCLogger (MBPEquipos.class);
	
	public static final int VISTA_Equipo = 1;
	public static final int VISTA_Estacion = 2;
	
	/**
	 * Buscamos las areas donde se ubica el equipo
	 * @param ctx
	 * @param EquipoId
	 * @return
	 * @deprecated use {@link KeyNamePair}
	 */
	public static List<LabelValueBean> getLstAreas(Properties ctx, int EquipoId, String fechaIni, String fechaFin){
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		
		//Todas las areas
		sql.append(" SELECT Name, EXME_Area_ID FROM EXME_Area WHERE IsActive = 'Y' ");
		
		//Area propia
		sql.append(" AND EXME_Area_ID IN ( (SELECT EXME_Area_ID FROM EXME_EstServ WHERE EXME_EstServ_ID = ? ) ");
		params.add(Env.getContextAsInt(ctx, "#EXME_EstServ_ID"));
		
		//Area del equipo
		if(EquipoId>0){
			sql.append(" UNION (SELECT EXME_Area_ID FROM EXME_Equipo WHERE EXME_Equipo_ID = ? ) ");
			params.add(EquipoId);
		}

		//Areas donde se presto el equipo
		if(EquipoId>0 && fechaIni!=null && fechaFin != null){
			sql.append(" UNION (SELECT EXME_Area_ID FROM EXME_EquipoH ")
			.append(" WHERE EXME_Equipo_ID = ? ") 
			.append(" AND (                    ")
			.append("       (   Fecha_ini <= to_date(?, 'dd/mm/yyyy hh24:mi')  ") 
			.append("       AND Fecha_Fin >= to_date(?, 'dd/mm/yyyy hh24:mi')  ")
			.append("       )                  ")
			.append("     OR                   ")
			.append("       (   Fecha_ini >= to_date(?, 'dd/mm/yyyy hh24:mi')  ") 
			.append("       AND Fecha_ini <= to_date(?, 'dd/mm/yyyy hh24:mi')  ")
			.append("       )                  ")
			.append("     )                    ")
			.append(" )                        ");
			params.add(EquipoId);
			params.add(fechaIni);
			params.add(fechaIni);
			params.add(fechaIni);
			params.add(fechaFin);
		}
		
		sql.append("     )                    ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Area.Table_ID));
		return MEXMEArea.get(ctx, sql.toString(), params, true, null);
	}
	
	/**
	 * Buscamos el primer equipo de relacionado al area de 
	 * acceso del usuario o bien el que no sea fijo
	 * @param ctx
	 * @return
	 */
	public static MEXMEEquipo getEquipoDefauld(Properties ctx){
		List<MEXMEEquipo> equipo = new ArrayList<MEXMEEquipo>();
		List<Object> params = new ArrayList<Object>();
	
		StringBuilder clausWhere = new StringBuilder();
		clausWhere.append(" AND ( EXME_Area_ID IN ( ")
		.append("                      SELECT EXME_Area_ID ")
		.append("                      FROM EXME_EstServ ")
		.append("                      WHERE EXME_EstServ_ID = ? ) ")
		.append("               OR Fijo = 'N' ) ");
		
		params.add(Env.getEXME_EstServ_ID(ctx));
		
		equipo = MEXMEEquipo.getEquipo(ctx, clausWhere.toString(), 
				params, null);

		if(equipo.size()>0)
			return equipo.get(0);
		else
			return null;
	}
	
	
	/**
	 * Buscamos la hora mas temprano deacuerdo al almacen 
	 * elegido
	 * @param forma
	 * @return
	 */
	public static int earlyHour(int EXME_Almacen_ID, boolean horaInicio) {
		
		StringBuilder sql = new StringBuilder();
		
		if(horaInicio){
			sql.append(" SELECT dom_entrada, lun_entrada, mar_entrada, mie_entrada, ")
			.append(" jue_entrada, vie_entrada, sab_entrada ");

		} else {
			sql.append(" SELECT dom_salida, lun_salida, mar_salida, mie_salida, ")
			.append(" jue_salida, vie_salida, sab_salida ");

		}

		sql.append(" FROM Exme_Turnos2 turnos ")
		.append(" INNER JOIN M_WareHouse almacen ")
		.append(" ON (turnos.Exme_Turnos2_id = almacen.Exme_Turnos2_ID) ")
		.append(" WHERE almacen.M_WareHouse_ID = ? ");

		int[] arregloDias = new int[7];
		PreparedStatement prepa = null;
		ResultSet rs = null;
		try {
			prepa = DB.prepareStatement(sql.toString(), null);
			prepa.setInt(1, EXME_Almacen_ID);
			rs = prepa.executeQuery();
			if (rs.next()) {
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(rs.getTime(i + 1));
					arregloDias[i] = cal.get(Calendar.HOUR_OF_DAY);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, prepa);
		}
		
		//Buscamos la hora mas temprano
		int horas = arregloDias[0];

		if(horaInicio){
			
			//Min
			for (int i = 0; i < arregloDias.length; i++) {
				if (arregloDias[i] < horas) {
					horas = arregloDias[i];
				}

			}
		} else {
			
			//Max
			for (int i = 0; i < arregloDias.length; i++) {
				if (arregloDias[i] > horas) {
					horas = arregloDias[i];
				}

			}
		}

		return horas;
	}
	
	/**
	 * Informaci�n para el tooltip
	 * @param EXME_ActPacienteIndH_ID
	 * @return
	 * @deprecated use {@link KeyNamePair}
	 */
	public static LabelValueBean getServEquipo(int EXME_ActPacienteIndH_ID){
		// Servicio y Equipo
		String servicio = "";
		String equipo = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder()
		.append(
				"SELECT  NVL(p.Value,' ') AS product , NVL(e.Value,' ') AS equipo ")
				.append("FROM EXME_ActPacienteInd ")
				.append(
				"INNER JOIN M_Product p ON EXME_ActPacienteInd.M_Product_ID = p.M_Product_ID ")
				.append(
				"LEFT JOIN EXME_Equipo e ON EXME_ActPacienteInd.EXME_Equipo_ID = e.EXME_Equipo_ID ")
				.append("WHERE EXME_ActPacienteInd.IsActive = 'Y' ")
				.append(
				"AND EXME_ActPacienteInd.EXME_ActPacienteIndH_ID = ? ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_ActPacienteIndH_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				servicio = servicio + " " + rs.getString(1);
				equipo = equipo + " " + rs.getString(2);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return new LabelValueBean(servicio, equipo);
	}
	
//	/**
//	 * Estaciones de servicio por area seleccionada
//	 * @param ctx
//	 * @param EXME_Area_ID
//	 * @return
//	 * @throws Exception 
//	 * @deprecated use {@link KeyNamePair}
//	 */
//	public static List<LabelValueBean> estacionesPorArea(Properties ctx, 
//			int EXME_Area_ID) throws Exception{
//		//TODO:Verificar que si no se selecciona un area que estacion se elige ?
//		
//		StringBuilder strWhere = new StringBuilder();
//		List<Object> params = new ArrayList<Object>();
//		
//		if(EXME_Area_ID>0){
//			strWhere.append(" AND EXME_EstServ.EXME_Area_ID = ? ");
//			params.add(EXME_Area_ID);
//		}
//		
//		return MEXMEEstServ.getEstServRole(ctx, strWhere.toString(), 
//				" ORDER BY EXME_EstServ.Name ", params);
//	}
	
	/**
	 * Saber si existen servicios a los que se les relaciones equipos
	 * @param ctx
	 * @param EXME_ActPacienteIndH_ID
	 * @return
	 */
	public static boolean getVerEquipos(Properties ctx, int EXME_ActPacienteIndH_ID){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder()
		.append(
				" SELECT * ")
				.append("FROM EXME_ActPacienteInd ")
				.append(
				" INNER JOIN M_Product p ON EXME_ActPacienteInd.M_Product_ID = p.M_Product_ID ")
				.append("WHERE EXME_ActPacienteInd.IsActive = 'Y' ")
				.append(
				" AND EXME_ActPacienteInd.EXME_ActPacienteIndH_ID = ? ")
				.append(
				" AND p.Isexcludeautodelivery = 'Y' ");

		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_ActPacienteIndH_ID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return false;
	}

	/**
	 * Disponibilidad de equipos
	 * @return
	 */
	public static String getEquiposOcupados(){
		StringBuilder sql = new StringBuilder()
		.append(" SELECT EXME_EquipoH.EXME_Equipo_ID FROM EXME_EquipoH ")
		.append(" WHERE EXME_EquipoH.IsActive = 'Y' ") 
		.append(" AND Estatus_Equipo NOT IN (       ")
		.append(DB.TO_STRING(X_EXME_EquipoH.ESTATUS_EQUIPO_Cancelled)).append(", ")
		.append(DB.TO_STRING(X_EXME_EquipoH.ESTATUS_EQUIPO_Available)).append(", ") 
		.append(DB.TO_STRING(X_EXME_EquipoH.ESTATUS_EQUIPO_Used))
		.append(" )                                 ")
		.append(" AND (                             ")
		.append("       (   EXME_EquipoH.Fecha_ini <= to_date(?, 'dd/mm/yyyy hh24:mi')  ") 
		.append("       AND EXME_EquipoH.Fecha_Fin >= to_date(?, 'dd/mm/yyyy hh24:mi')  ")
		.append("       )                           ")
		.append("     OR                            ")
		.append("       (   EXME_EquipoH.Fecha_ini >= to_date(?, 'dd/mm/yyyy hh24:mi')  ") 
		.append("       AND EXME_EquipoH.Fecha_ini <= to_date(?, 'dd/mm/yyyy hh24:mi')  ")
		.append("       )                           ")
		.append("     )                             ");
		return sql.toString();
	}

	/**
	 * 
	 * @param ctx
	 * @param Fecha_ini
	 * @param Fecha_fin
	 * @param verMovil
	 * @return
	 */
	public static List<LabelValueBean> getEquiposDisponibles(Properties ctx,
			String Fecha_ini, String Fecha_fin, boolean verMovil, int EXME_Equipo_ID){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		
		StringBuilder sql = new StringBuilder()
		.append(" SELECT EXME_Equipo.Name, EXME_Equipo.EXME_Equipo_ID ")
		.append(" FROM EXME_Equipo WHERE EXME_Equipo.IsActive = 'Y' ");
		
		//Equipos no disponibles
		sql.append(" AND EXME_Equipo.EXME_Equipo_ID NOT IN ( ");
		sql.append(getEquiposOcupados()).append(" ) ");
		
		//Area de logueo
		sql.append(" AND ( EXME_Area_ID IN ( (SELECT EXME_Area_ID ")
		.append("    FROM EXME_EstServ WHERE EXME_EstServ_ID = ? ) ");
		
		//Equipo movil
		if(verMovil)
			sql.append(" OR EXME_Equipo.Fijo = 'N' ");

		sql.append(") ");
		
		if(EXME_Equipo_ID>0)
			sql.append(" AND EXME_Equipo.EXME_Equipo_ID = ? ");	
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			pstmt.setString(1, Fecha_ini);
			pstmt.setString(2, Fecha_ini);
			pstmt.setString(3, Fecha_ini);
			pstmt.setString(4, Fecha_fin);
			pstmt.setInt(5, Env.getContextAsInt(ctx, "#EXME_EstServ_ID"));
			if(EXME_Equipo_ID>0)
				pstmt.setInt(6, EXME_Equipo_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lista.add(new LabelValueBean(rs.getString(1),rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lista;
	}
	
	/**
	 * Ids de los Equipos relacionados a una orden de servicio
	 * @param lstEquiposH
	 * @return
	 */
	public static String getEquipos(List<MEXMEEquipoH> lstEquiposH){
		
		int tam = lstEquiposH.size();
		StringBuilder sqlID = new StringBuilder();
		if(tam>0){
			sqlID.append(" AND EXME_Equipo_ID IN (");

			for (int i = 0; i < lstEquiposH.size(); i++) {
				sqlID.append(lstEquiposH.get(i).getEXME_Equipo_ID());
				if(tam!=i+1)
					sqlID.append(", ");
			}
			sqlID.append(") ");
		}
		return sqlID.toString();
	}

	/**
	 * Actualizar los estatus de los equipos seleccionados por el usuario
	 * @param lstEquiposH
	 */
	public void save(List<MEXMEEquipoH> lstEquiposH, int estacionLog,
			MEXMEActPacienteIndH actPacienteIndH){
		
		for (int i = 0; i < lstEquiposH.size(); i++) {
			
				
			if(lstEquiposH.get(i).getEXME_EquipoH_ID()>0 && !lstEquiposH.get(i).isActive())
				//Actualizar a equipo disponible o bien borrar la programaci�n
				lstEquiposH.get(i).setEstatus_Equipo(X_EXME_EquipoH.ESTATUS_EQUIPO_Cancelled);
			else{
				lstEquiposH.get(i).setEstatus_Equipo(X_EXME_EquipoH.ESTATUS_EQUIPO_Ordered);
				lstEquiposH.get(i).setEXME_ActPacienteIndH_ID(actPacienteIndH.getEXME_ActPacienteIndH_ID());
				lstEquiposH.get(i).setEXME_EstServ_ID(estacionLog);//Estacion de servicio de login
				lstEquiposH.get(i).setFecha_Ini(actPacienteIndH.getDatePromised());//Fecha prometida de toda la programaci�n
				lstEquiposH.get(i).setFecha_Fin(actPacienteIndH.getFecha_Fin());//Fecha final de toda la programaci�n
			}

			lstEquiposH.get(i).save();			
		}
	}
	
	/**
	 * Regresa los servicios que se corresponden a la orden
	 * siempre y cuando estos requeiran equipos
	 * @param ctx
	 * @param EXME_ActPacienteIndH_ID
	 * @return
	 */
	public static List<LabelValueBean> getServicios(Properties ctx, int EXME_ActPacienteIndH_ID){
		
		List<LabelValueBean> lstServicios = new ArrayList<LabelValueBean>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p.Name, p.m_product_id ")
		.append(" FROM EXME_ActPacienteIndH apih ")
		.append(" INNER JOIN EXME_ActPacienteInd api ON apih.exme_actpacienteindh_id = api.exme_actpacienteindh_id ")
		.append(" INNER JOIN M_Product p ON p.M_Product_ID = api.M_Product_ID ")
		.append(" WHERE apih.exme_actpacienteindh_id = ?  ")
		.append(" AND P.isexcludeautodelivery = 'Y'  ")
		.append(" group by p.m_product_id, p.Name ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			pstmt.setInt(1, EXME_ActPacienteIndH_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lstServicios.add(new LabelValueBean(rs.getString(1),rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lstServicios;
	}
	
	/**
	 * Disponibilidad de equipos seleccionados
	 * al momento de guardar
	 * @param ctx
	 * @param Fecha_ini
	 * @param Fecha_fin
	 * @param lstEquiposH
	 * @param EXME_ActPacienteIndH_ID
	 * @return List<String>
	 */
	public List<String> getEquiposNoDisponibles(Properties ctx,
			String Fecha_ini, String Fecha_fin, List<MEXMEEquipoH> lstEquiposH,
			int EXME_ActPacienteIndH_ID){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> lista = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder()
		//Todos los equipos ocupados
		.append(getEquiposOcupados())
		//De estos equipos
		.append(getEquipos(lstEquiposH))
		//Que no sean de la actividad paciente que estamos editando
		.append(" AND EXME_EquipoH.EXME_ActPacienteIndH_ID <> ? ")
		//Para el cliente y organizacion
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_EquipoH"));
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			pstmt.setString(1, Fecha_ini);
			pstmt.setString(2, Fecha_ini);
			pstmt.setString(3, Fecha_ini);
			pstmt.setString(4, Fecha_fin);
			pstmt.setInt(5, EXME_ActPacienteIndH_ID);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lista.add((new MEXMEEquipo(ctx, rs, null)).getName());
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lista;
	}
}
