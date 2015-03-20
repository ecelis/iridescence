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
/**
 * Modificado por Lorena Lama,Julio 2013.
 *  - Revision de codigo: se quitan metodos comentados, se comentan metodos obsoletos, se eliminan warnings
 */
public class MEqQuirofano extends X_EXME_EqQuirofano {

	/** serialVersionUID **/
	private static final long serialVersionUID = 1L;
	
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEqQuirofano.class);
	
	public MEqQuirofano(Properties ctx, ResultSet rs, String trxName) {
	    super(ctx, rs, trxName);
	 }
	
	public MEqQuirofano(Properties ctx, int EXME_EqQuirofano_ID, String trxName) {
	    super(ctx, EXME_EqQuirofano_ID, trxName);
	}
	
	/**
	 * Devolvemos una lista con el id y el nombre de los
	 * equipos relacionados a un quirofano determinado, 
	 * excluyendo a los que se encuentran en la lista
	 * proporcionada..
	 *
	 * @param xxQuirofanoId El quirofano a consultar
	 * @param lista La lista de equipos a excluir
	 * @param ctx El contexto de la aplicacion
	 *
	 * @return Un valor ArrayList con los equipos del quirofano
	 *
	 * @throws Exception en caso de ocurrir un error al procesar
	 * la consulta o no existir equipos relacionados con el
	 * quirofano.
	 */
//	public static ArrayList<LabelValueBean> getEquiposQuiro(Properties ctx, long xxQuirofanoId, ArrayList<LabelValueBean> lista,
//			String trxName) throws Exception {
//	    StringBuilder sql = new StringBuilder();
//		//PreparedStatement con la sentencia sql
//		PreparedStatement pstmt = null;
//		//ResultSet utilizado para manipular los resultados
//		ResultSet rs = null;
//		ArrayList<LabelValueBean> equipos = new ArrayList<LabelValueBean>();
//	    try {
//			
//
//			 sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//			 sql.append("SELECT EXME_Equipo.EXME_Equipo_ID, EXME_Equipo.Name ")
//			.append("FROM EXME_Equipo , EXME_EqQuirofano eq ")
//			.append("WHERE eq.EXME_Quirofano_ID = ? ")
//			.append("AND eq.EXME_Equipo_ID = EXME_Equipo.EXME_Equipo_ID ")
//			.append("AND EXME_Equipo.AD_Client_ID = ? ")
//			.append("AND EXME_Equipo.AD_Org_ID = ? ");
//			 
//			 sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_Equipo"));
//
//			if(lista != null && lista.size() > 0) {
//				sql.append("AND EXME_Equipo.EXME_Equipo_ID NOT IN(");
//
//				for(int i = 0; i < lista.size(); i++) {
//					LabelValueBean equipo = (LabelValueBean)lista.get(i);
//					sql.append(equipo.getValue());
//
//					if(i < lista.size() - 1) {
//						sql.append(", ");
//					}
//				}
//				sql.append(") ");
//			}
//
//			sql.append("ORDER BY EXME_Equipo.Name");
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setLong(1, xxQuirofanoId);
//			pstmt.setLong(2, Env.getContextAsInt(ctx, "#AD_Client_ID"));
//			pstmt.setLong(3, Env.getContextAsInt(ctx, "#AD_Org_ID"));
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				LabelValueBean equipo =
//				        new LabelValueBean(rs.getString("Name"),
//				                           String.valueOf(rs.getLong("EXME_Equipo_ID"))
//				                          );
//				equipos.add(equipo);
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getEquiposQuiro", e);
//			throw new Exception(e.getMessage());
//		} finally {
//			DB.close(rs, pstmt);
//            rs = null;
//            pstmt = null;
//        }
//		return lista.isEmpty() ? null : equipos;
//	}
	
	/**
	 * Devolvemos una lista con el id y el nombre de los
	 * equipos relacionados a un quirofano determinado, 
	 * excluyendo a los que se encuentran en la lista
	 * proporcionada..
	 *
	 * @param xxQuirofanoId El quirofano a consultar
	 * @param lista La lista de equipos a excluir
	 * @param ctx El contexto de la aplicacion
	 *
	 * @return Un valor ArrayList con los equipos del quirofano
	 *
	 * @throws Exception en caso de ocurrir un error al procesar
	 * la consulta o no existir equipos relacionados con el
	 * quirofano.
	 */
//	public static ArrayList<QuirEquiposView> getEquiposQuirofano(Properties ctx, long quirofanoID, ArrayList<QuirEquiposView> lista, String trxName) {
//		
//	    StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		PreparedStatement pstmt = null;
//
//		ResultSet rs = null;
//		
//		ArrayList<QuirEquiposView> equipos = new ArrayList<QuirEquiposView>();
//		
//	    try {
//
//			sql.append("SELECT EXME_Equipo.EXME_Equipo_ID, EXME_Equipo.Name, eq.EXME_Quirofano_ID ")
//				.append(" FROM EXME_Equipo , EXME_EqQuirofano eq ")
//				.append(" WHERE eq.EXME_Quirofano_ID = ").append(quirofanoID)
//				.append(" AND eq.EXME_Equipo_ID = EXME_Equipo.EXME_Equipo_ID ")
//				.append(" AND EXME_Equipo.disponible > 0 ");
//				//.append(" AND EXME_Equipo.AD_Client_ID = ").append(Env.getContextAsInt(ctx, "#AD_Client_ID"))
//				//.append(" AND EXME_Equipo.AD_Org_ID = ").append(Env.getContextAsInt(ctx, "#AD_Org_ID"));
//			
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_Equipo"));
//
//			if(lista != null && lista.size() > 0) {
//				sql.append(" AND EXME_Equipo.EXME_Equipo_ID NOT IN(");
//
//				for(int i = 0; i < lista.size(); i++) {
//					QuirEquiposView equiposView = (QuirEquiposView)lista.get(i);
//					sql.append(equiposView.getEquipoID());
//
//					if(i < lista.size() - 1) {
//						sql.append(", ");
//					}
//				}
//				sql.append(") ");
//			}
//
//			sql.append(" ORDER BY EXME_Equipo.Name ");
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				QuirEquiposView equipo = new QuirEquiposView(ctx, rs.getInt("EXME_Quirofano_ID"), trxName);
//				equipo.setEquipoName(rs.getString("Name"));
//				equipo.setEquipoID(rs.getLong("EXME_Equipo_ID"));
//				equipos.add(equipo);
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.FINE, "getEquiposQuiro", e);
//		} finally {
//			DB.close(rs, pstmt);
//            rs = null;
//            pstmt = null;
//        }
//		return equipos;
//	}
	
	/**
	 * Devolvemos una lista con el id y el nombre de los
	 * equipos relacionados a un quirofano determinado, 
	 * @param ctx El contexto de la aplicacion
	 * @param quirofanoID El quirofano a consultar
	 * @param fechaIni - dd/mm/yyyy hh24:mi
	 * @param fechaFin - dd/mm/yyyy hh24:mi
	 * @param EXME_ProgQuiro_ID
	 * @return Un valor ArrayList con los equipos del quirofano
	 */
//	public static ArrayList<QuirEquiposView> getEquiposQuirofano(Properties ctx, int quirofanoID, 
//			String fechaIni, String fechaFin, int EXME_ProgQuiro_ID,
//			String trxName) {
//		
//	    StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ArrayList<QuirEquiposView> equipos = new ArrayList<QuirEquiposView>();
//		
//	    try {
//
//			sql.append("  SELECT EXME_EqQuirofano.EXME_EqQuirofano_ID, e.Name, e.EXME_Equipo_ID , e.Value   ")
//				.append(" FROM EXME_EqQuirofano   ")
//				.append(" INNER JOIN EXME_Equipo e ON  EXME_EqQuirofano.EXME_Equipo_ID = e.EXME_Equipo_ID    ")
//				.append(" WHERE EXME_EqQuirofano.IsActive = 'Y' AND EXME_EqQuirofano.EXME_Quirofano_ID = ? ")
//				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_EqQuirofano"))
//				.append(" AND EXME_EqQuirofano.EXME_Equipo_ID NOT IN (  ")
//				.append(" SELECT EXME_Equipo_ID FROM EXME_EquipoH   ")
//				.append(" WHERE IsActive = 'Y'   ")
//				.append(" AND Estatus_equipo IN ( 'M' , 'P' , 'S' )    ")
//				.append(" AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  > ?  ")
//				.append(" AND to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' )  < ?  ")
//				.append(" AND ( ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ")
//				.append(" ) OR  ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ? ")
//				.append(" ) OR  ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ? ")
//				.append(" ) OR  ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ")
//				.append(" ) ) AND EXME_ProgQuiro_ID <> ? ) ORDER BY e.Name ");
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1,quirofanoID);
//			pstmt.setString(2, fechaIni);
//			pstmt.setString(3, fechaFin);
//			
//			pstmt.setString(4, fechaIni);
//			pstmt.setString(5, fechaFin);
//			
//			pstmt.setString(6, fechaIni);
//			pstmt.setString(7, fechaFin);
//			
//			pstmt.setString(8, fechaIni);
//			pstmt.setString(9, fechaFin);
//			
//			pstmt.setString(10, fechaIni);
//			pstmt.setString(11, fechaFin);
//			
//			pstmt.setInt(12, EXME_ProgQuiro_ID);
//			
//			rs = pstmt.executeQuery();
//			
//			List<MProgQuiroEq> lst = MProgQuiroEq.getByProgQuiroID(ctx, EXME_ProgQuiro_ID, trxName);
//			List<Integer> ids = new ArrayList<Integer>();
//			for(MProgQuiroEq pqe : lst){
//				ids.add(pqe.getEXME_Equipo_ID());
//			}
//			
//			while (rs.next()) {
//				QuirEquiposView equipo = new QuirEquiposView(ctx, rs.getInt("EXME_EqQuirofano_ID"), trxName);
//				equipo.setEquipoName(rs.getString("Name"));
//				equipo.setEquipoID(rs.getLong("EXME_Equipo_ID"));
//				equipo.setEquipoValue(rs.getString("Value"));
//				equipo.setEXME_Equipo_ID(rs.getInt("EXME_Equipo_ID"));
//				if (ids.contains(rs.getInt("EXME_Equipo_ID"))) {
//					equipo.setSelected(true);
//				}
//				equipos.add(equipo);
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.FINE, "getEquiposQuiro", e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return equipos;
//	}
	
	public static List<QuirEquiposView> getEquiposQuirofano(Properties ctx, int quirofanoID, Date fechaIni, Date fechaFin, int EXME_ProgQuiro_ID, String trxName) {
		List<QuirEquiposView> equipos = new ArrayList<QuirEquiposView>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  eq.exme_eqquirofano_id, ");
			sql.append("  e.Name, ");
			sql.append("  e.EXME_Equipo_ID, ");
			sql.append("  e.Value ");
			sql.append("FROM ");
			sql.append("  exme_eqquirofano eq ");
			sql.append("  INNER JOIN exme_equipo e ");
			sql.append("  ON eq.exme_equipo_id = e.exme_equipo_id ");
			sql.append("WHERE ");
			sql.append("  eq.exme_equipo_id NOT ");
			sql.append("  IN ");
			sql.append("  (SELECT ");
			sql.append("      eh.exme_equipo_id ");
			sql.append("    FROM ");
			sql.append("      exme_equipoh eh ");
			sql.append("    WHERE ");
			sql.append("      ((TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) BETWEEN ");
			sql.append("      eh.fecha_ini AND ");
			sql.append("      eh.fecha_fin) OR ");
			sql.append("      (TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) BETWEEN ");
			sql.append("      eh.fecha_ini AND ");
			sql.append("      eh.fecha_fin) OR ");
			sql.append("      (eh.fecha_ini BETWEEN ");
			sql.append("      TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) AND ");
			sql.append("      TO_DATE(? ,'dd/mm/yyyy HH24:MI' )) OR ");
			sql.append("      (eh.fecha_fin BETWEEN ");
			sql.append("      TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) AND ");
			sql.append("      TO_DATE(? ,'dd/mm/yyyy HH24:MI' ))) AND ");
			sql.append("      eh.exme_progquiro_id <> ? AND ");
			sql.append("      eh.estatus_equipo <> ");
			sql.append("? ) AND ");
			sql.append("  eq.exme_quirofano_id = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEqQuirofano.Table_Name, "eq"));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, Constantes.getSdfFechaHora().format(fechaIni));
			pstmt.setString(2, Constantes.getSdfFechaHora().format(fechaFin));
			pstmt.setString(3, Constantes.getSdfFechaHora().format(fechaIni));
			pstmt.setString(4, Constantes.getSdfFechaHora().format(fechaFin));
			pstmt.setString(5, Constantes.getSdfFechaHora().format(fechaIni));
			pstmt.setString(6, Constantes.getSdfFechaHora().format(fechaFin));
			pstmt.setInt(7, EXME_ProgQuiro_ID);
			pstmt.setString(8, MEXMEEquipoH.ESTATUS_EQUIPO_Cancelled);
			pstmt.setInt(9, quirofanoID);
			
			rs = pstmt.executeQuery();

			List<MProgQuiroEq> lst = MProgQuiroEq.getByProgQuiroID(ctx, EXME_ProgQuiro_ID, null);
			List<Integer> ids = new ArrayList<Integer>();
			for(MProgQuiroEq pqe : lst){
				ids.add(pqe.getEXME_Equipo_ID());
			}
			
			while (rs.next()) {
				QuirEquiposView equipo = new QuirEquiposView(ctx, rs.getInt("exme_eqquirofano_id"), trxName);
				equipo.setEquipoName(rs.getString("Name"));
				equipo.setEquipoID(rs.getLong("EXME_Equipo_ID"));
				equipo.setEquipoValue(rs.getString("Value"));
				equipo.setEXME_Equipo_ID(rs.getInt("EXME_Equipo_ID"));
				if (ids.contains(rs.getInt("EXME_Equipo_ID"))) {
					equipo.setSelected(true);
				}
				equipos.add(equipo);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return equipos;
	}
	
	public static boolean isBusy(Properties ctx, int equipoId, Date fechaIni, Date fechaFin, int EXME_ProgQuiro_ID, String trxName) {
		boolean retValue = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  eh.exme_equipo_id ");
			sql.append("FROM ");
			sql.append("  exme_equipoh eh ");
			sql.append("WHERE ");
			sql.append("  ((TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) BETWEEN ");
			sql.append("  eh.fecha_ini AND ");
			sql.append("  eh.fecha_fin) OR ");
			sql.append("  (TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) BETWEEN ");
			sql.append("  eh.fecha_ini AND ");
			sql.append("  eh.fecha_fin) OR ");
			sql.append("  (eh.fecha_ini BETWEEN ");
			sql.append("  TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) AND ");
			sql.append("  TO_DATE(? ,'dd/mm/yyyy HH24:MI' )) OR ");
			sql.append("  (eh.fecha_fin BETWEEN ");
			sql.append("  TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) AND ");
			sql.append("  TO_DATE(? ,'dd/mm/yyyy HH24:MI' ))) AND ");
			sql.append("  eh.exme_progquiro_id <> ? AND ");
			sql.append("  eh.exme_equipo_id = ? AND ");
			sql.append("  eh.estatus_equipo <> ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEEquipoH.Table_Name, "eh"));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, Constantes.getSdfFechaHora().format(fechaIni));
			pstmt.setString(2, Constantes.getSdfFechaHora().format(fechaFin));
			pstmt.setString(3, Constantes.getSdfFechaHora().format(fechaIni));
			pstmt.setString(4, Constantes.getSdfFechaHora().format(fechaFin));
			pstmt.setString(5, Constantes.getSdfFechaHora().format(fechaIni));
			pstmt.setString(6, Constantes.getSdfFechaHora().format(fechaFin));
			pstmt.setInt(7, EXME_ProgQuiro_ID);
			pstmt.setInt(8, equipoId);
			pstmt.setString(9, MEXMEEquipoH.ESTATUS_EQUIPO_Cancelled);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				retValue = true;
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}

	
	/**
	 * Devolvemos una lista con el id y el nombre de los
	 * equipos relacionados a un quirofano determinado, 
	 *
	 * @param xxQuirofanoId El quirofano a consultar
	 * @param ctx El contexto de la aplicacion
	 * @return Un valor ArrayList con los equipos del quirofano
	 * @throws Exception en caso de ocurrir un error al procesar
	 * la consulta o no existir equipos relacionados con el
	 * quirofano.
	 */
	public static ArrayList<QuirEquiposView> getEquiposDisponibles(Properties ctx, int quirofanoID, 
			String fechaIni, String fechaFin, String strEquipos, int EXME_ProgQuiro_ID,
			String trxName) {
		
	    StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QuirEquiposView> equipos = new ArrayList<QuirEquiposView>();
		
	    try {

			sql.append("  SELECT EXME_EqQuirofano.EXME_EqQuirofano_ID, e.Name, e.EXME_Equipo_ID     ")
				.append(" FROM EXME_EqQuirofano   ")
				.append(" INNER JOIN EXME_Equipo e ON  EXME_EqQuirofano.EXME_Equipo_ID = e.EXME_Equipo_ID    ")
				.append(" WHERE EXME_EqQuirofano.IsActive = 'Y' AND EXME_EqQuirofano.EXME_Quirofano_ID = ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_EqQuirofano"))
				.append(" AND EXME_EqQuirofano.EXME_Equipo_ID IN (  ").append(strEquipos)
				.append(" ) AND EXME_EqQuirofano.EXME_Equipo_ID NOT IN (  ")
				.append(" 	SELECT EXME_Equipo_ID FROM EXME_EquipoH   ")
				.append(" 	WHERE IsActive = 'Y'   ")
				.append(" 	AND Estatus_equipo IN ( 'M' , 'P' , 'S' )    ")
				.append(" 	AND EXME_ProgQuiro_ID <> ?   ")
				.append(" 	AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  > ?  ")
				.append("	AND to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' )  < ?  ")
				.append(" 	AND ( ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ")
				.append(" 	) OR  ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ? ")
				.append(" 	) OR  ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ? ")
				.append(" 	) OR  ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ")
				.append(" 	) ) ) ORDER BY e.Name ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1,quirofanoID);
			pstmt.setInt(2,EXME_ProgQuiro_ID);
			pstmt.setString(3, fechaIni);
			pstmt.setString(4, fechaFin);
			
			pstmt.setString(5, fechaIni);
			pstmt.setString(6, fechaFin);
			
			pstmt.setString(7, fechaIni);
			pstmt.setString(8, fechaFin);
			
			pstmt.setString(9, fechaIni);
			pstmt.setString(10, fechaFin);
			
			pstmt.setString(11, fechaIni);
			pstmt.setString(12, fechaFin);
			
			rs = pstmt.executeQuery();
			//twry
			while(rs.next()) {
				QuirEquiposView equipo = new QuirEquiposView(ctx, rs.getInt("EXME_EqQuirofano_ID"), trxName);
				equipo.setEquipoName(rs.getString("Name"));
				equipo.setEquipoID(rs.getLong("EXME_Equipo_ID"));
				equipos.add(equipo);
			}

		} catch (SQLException e) {
			s_log.log(Level.FINE, "getEquiposQuiro", e);
		} finally {
			DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
		
		return equipos;
		
	}
}
