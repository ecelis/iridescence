/*
 * Created on 5/06/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

/**
 * @author YWRY
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * Modificado por Lorena Lama,Julio 2013.
 *  - Revision de codigo: se quitan metodos comentados, se comentan metodos obsoletos, se eliminan warnings
 */
 public class MProgQuiroDet extends X_EXME_ProgQuiroDet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MProgQuiroDet.class);

	// private static SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Formatea a horas (am/pm) hh:mm aa
	 */
	// public static DecimalFormat dfHora = new DecimalFormat("00.00");

	public MProgQuiroDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MProgQuiroDet(Properties ctx, int EXME_ProgQuiroDet_ID, String trxName) {
		super(ctx, EXME_ProgQuiroDet_ID, trxName);
		if (EXME_ProgQuiroDet_ID != 0) {
			throw new IllegalArgumentException("Multi-Key");
		}
	}

	public MIntervencion getIntervencion() {
		if (getEXME_Intervencion_ID() <= 0) {
			return null;
		}
		return new MIntervencion(getCtx(), getEXME_Intervencion_ID(), get_TrxName());
	}

	public MProgQuiro getProgQuiro() {
		if (getEXME_ProgQuiro_ID() <= 0) {
			return null;
		}
		return new MProgQuiro(getCtx(), getEXME_ProgQuiro_ID(), get_TrxName());
	}
        
	/**
	 * Devolvemos una programacion especifica.
	 *
	 * @param xxQuirofanoId El quirofano del que se obtiene 
	 * la programaci&oacute;n
	 * @param fecha La fecha de la programaci&oacute;n
	 * @param ctx El contexto de la aplicaci&oacute;n
	 * @param cierre Si es para cierre la programación
	 *
	 * @return Un valor ProgramacionView
	 * @throws Exception en caso de ocurrir un error al procesar la consulta
	 * @deprecated usada en CierreQuirofanosAction / ProgQuirofanosAction
	 */
//	public static ProgramacionView getProgramacion(Properties ctx, long xxQuirofanoId, java.util.Date fecha, 
//			boolean cierre, String trxName)throws Exception {
//	    
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		
//		//PreparedStatement con la sentencia sql
//		PreparedStatement pstmt = null;
//		//ResultSet utilizado para manipular los resultados
//		ResultSet rs = null;
//	    
//		ProgramacionView pv = new ProgramacionView();
//		
//		try {
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(fecha);
//
//			sql.append(" SELECT m.Name||' '||m.Apellido1||' '||m.Apellido2 AS nomMedico, ")
//			.append(" m.EXME_Medico_ID as medicoId, m.Value, ")
//			.append(" i.Name as nomInterv, ")
//			.append(" p.Name||' '||p.Apellido1||' '||p.Apellido2 AS nomPac, ")
//			.append(" cp.EXME_CtaPac_ID as ctaPacId,")
//			.append(" pq.EXME_ProgQuiro_ID AS progQuiroId, ")
//			.append(" pq.EXME_Quirofano_ID AS quiroId, ")
//			.append(" pq.EXME_CtaPac_id AS ctaPacId, ")
//			.append(" pq.FechaProg, ")
//			.append(" pq.HoraInicio, ")
//			.append(" pq.DocStatus AS Estatus, ")
//			.append(" pq.description, ")
//			.append(" pq.nombrepac AS Paciente ")//Se agrego para obtener el nombre del paciente si es que se guardo por medico, eruiz
//			.append(" , po.EXME_PreOperatorio_ID ")
//			.append(" FROM EXME_ProgQuiroDet LEFT JOIN EXME_ProgQuiro pq ON (EXME_ProgQuiroDet.EXME_ProgQuiro_ID = pq.EXME_ProgQuiro_ID) ")
//			.append(" LEFT JOIN  EXME_Medico m ON (m.EXME_Medico_ID = pq.EXME_Medico_ID) ")
//			.append(" LEFT JOIN EXME_CtaPac cp  ON (cp.EXME_CtaPac_ID = pq.EXME_CtaPac_ID AND cp.isActive = 'Y' ) ")//.-Lama
//			.append(" LEFT JOIN EXME_Paciente p ON (p.EXME_Paciente_ID = cp.EXME_Paciente_ID) ")
//			.append(" LEFT JOIN EXME_Intervencion i ON (i.EXME_Intervencion_ID = EXME_ProgQuiroDet.EXME_Intervencion_ID) ")
//			//Solicitud de cirugia si es que esta existe
//			.append(" LEFT JOIN EXME_PreOperatorio po ON ( pq.EXME_ProgQuiro_ID = po.EXME_ProgQuiro_ID ) ")
//			
//			.append(" WHERE pq.EXME_Quirofano_ID = ? ")
//			.append(" AND TO_DATE(TO_CHAR(pq.FechaProg, 'dd/MM/yyyy'), 'dd/MM/yyyy') = TO_DATE(?, 'dd/MM/yyyy') ")
//			.append("AND (pq.FechaProg + (")//en lugar de comparar solamente la hora debemos determinar si cae dentro del rango de fecha/hora inicial y final
//			.append(cal.get(Calendar.HOUR_OF_DAY))
//			.append("/24)) BETWEEN (pq.FechaProg + (pq.HorainIcio/24)) AND (pq.FechaFin+ (pq.HoraInicio/24)) ");
//
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ProgQuiroDet"));
//
//			if (cierre) {
//				sql.append("AND TRIM(pq.DocStatus) = "); // '3'
//				sql.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_ActiveAndNonClose));
//			}
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//
//			pstmt.setLong(1, xxQuirofanoId);
//			pstmt.setString(2, Constantes.getSdfFecha().format(fecha));
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				pv.setXxProgQuiroId(rs.getLong("progQuiroId"));
//				pv.setXxQuirofanoId(rs.getLong("quiroId"));
//				pv.setXxMedicoId(rs.getLong("medicoId"));
//				pv.setNomMedico(rs.getString("nomMedico"));
//				pv.setCtaPacId(rs.getLong("ctaPacId"));
//				// Verificamos si se guardo por ctapac, eruiz
//				if (pv.getCtaPacId() > 0)
//					pv.setNomPaciente(rs.getString("nomPac"));
//				else
//					pv.setNomPaciente(rs.getString("Paciente"));
//				pv.setNomIntervencion(rs.getString("nomInterv"));
//				pv.setFechaInicio(new java.util.Date(rs.getDate("FechaProg").getTime()));
//				pv.setEstatus(rs.getString("Estatus").trim());
//				double hora = rs.getDouble("HoraInicio");
//				pv.setHoraInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(0, 2)));
//				pv.setMinInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(3, 5)));
//				pv.setObservaciones(rs.getString("description"));
//				pv.setMedValue(rs.getString("Value").trim());
//				pv.setPreOperatorio(rs.getInt("EXME_PreOperatorio_ID"));
//
//			} else {
//				DB.close(rs, pstmt);
//				// no tenemos una cuenta paciente relacionada, asi que no la consideramos
//				sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//				sql.append("SELECT m.Name||' '||m.Apellido1||' '||m.Apellido2 AS nomMedico, ")
//				.append("m.EXME_Medico_ID AS medicoId, i.Name as nomInterv, pq.EXME_ProgQuiro_ID ")
//				.append("AS progQuiroId, pq.EXME_Quirofano_ID AS quiroId, pq.FechaProg,")
//				.append("pq.HoraInicio, pq.DocStatus AS Estatus, pq.nombrepac, ")
//				.append("pq.description, m.Value ")
//				.append(" , po.EXME_PreOperatorio_ID ")
//				.append(" FROM EXME_ProgQuiroDet ")
//				.append(" LEFT JOIN EXME_ProgQuiro pq ON (pq.EXME_Progquiro_id = EXME_ProgQuiroDet.EXME_progquiro_id) ") 
//				.append(" LEFT JOIN EXME_Medico m ON (pq.EXME_Medico_id = m.EXME_medico_id) ")
//				.append(" LEFT JOIN EXME_Intervencion i ON (i.EXME_Intervencion_id = EXME_ProgQuiroDet.EXme_intervencion_id) ")
//				
//				//Solicitud de cirug�a si es que esta existe
//				.append(" LEFT JOIN EXME_PreOperatorio po ON ( pq.EXME_ProgQuiro_ID = po.EXME_ProgQuiro_ID ) ")
//			
//				.append("WHERE pq.EXME_Quirofano_ID = ? ")
//				.append("AND TO_DATE(TO_CHAR(pq.FechaProg, 'dd/MM/yyyy'), 'dd/MM/yyyy') = ")
//				.append("TO_DATE(?, 'dd/MM/yyyy') ")
//				.append("AND (pq.FechaProg + (")//en lugar de comparar solamente la hora debemos determinar si cae dentro del rango de fecha/hora inicial y final
//				.append(cal.get(Calendar.HOUR_OF_DAY))
//				.append("/24)) BETWEEN (pq.FechaProg + (pq.HoraInicio/24)) AND (pq.FechaFin+ (pq.HoraInicio/24)) ");
//				
//				if (DB.isOracle()) {
//					sql.append("AND ROWNUM < 2 ");
//				}
//
//				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//
//				if (cierre) {
//					sql.append("AND TRIM(pq.DocStatus) = "); // '3'
//					sql.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_ActiveAndNonClose));
//				}
//				
//				if (DB.isPostgreSQL()) {
//					sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
//				}
//				
//				pstmt = DB.prepareStatement(sql.toString(), trxName);
//
//				pstmt.setLong(1, xxQuirofanoId);
//				pstmt.setString(2, Constantes.getSdfFecha().format(fecha));
//
//				rs = pstmt.executeQuery();
//
//				if (rs.next()) {
//					pv.setXxProgQuiroId(rs.getLong("progQuiroId"));
//					pv.setXxQuirofanoId(rs.getLong("quiroId"));
//					pv.setXxMedicoId(rs.getLong("medicoId"));
//					pv.setNomMedico(rs.getString("nomMedico"));
//					pv.setCtaPacId(0);
//					pv.setNomPaciente(rs.getString("nombrepac"));
//					pv.setNomIntervencion(rs.getString("nomInterv"));
//					pv.setFechaInicio(new java.util.Date(rs.getDate("FechaProg").getTime()));
//					pv.setEstatus(rs.getString("Estatus").trim());
//					double hora = rs.getDouble("HoraInicio");
//					pv.setHoraInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(0, 2)));
//					pv.setMinInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(3, 5)));
//					pv.setObservaciones(rs.getString("description"));
//					pv.setMedValue(rs.getString("Value").trim());
//					pv.setPreOperatorio(rs.getInt("EXME_PreOperatorio_ID"));
//
//				} else {
//					DB.close(rs, pstmt);
//					// probablemente no se han relacionado intervenciones aun.
//					sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//					sql.append("SELECT EXME_Medico.Name||' '||EXME_Medico.Apellido1||' '||EXME_Medico.Apellido2 AS nomMedico, ")
//					.append("EXME_Medico.EXME_Medico_ID AS medicoId, pq.EXME_ProgQuiro_ID ")
//					.append("AS progQuiroId, pq.EXME_Quirofano_ID AS quiroId, pq.FechaProg,")
//					.append("pq.HoraInicio, pq.DocStatus AS Estatus, pq.nombrepac, ")
//					.append("pq.description, EXME_Medico.Value ")
//					.append(" , po.EXME_PreOperatorio_ID ")
//					.append("FROM  EXME_Medico LEFT JOIN EXME_ProgQuiro pq ON (EXME_Medico.EXME_Medico_ID = pq.EXME_Medico_ID) ")
//					//Solicitud de cirug�a si es que esta existe
//					.append(" LEFT JOIN EXME_PreOperatorio po ON ( pq.EXME_ProgQuiro_ID = po.EXME_ProgQuiro_ID ) ")
//					.append("WHERE pq.EXME_Quirofano_ID = ? ")
//					.append("AND TO_DATE(TO_CHAR(pq.FechaProg, 'dd/MM/yyyy'), 'dd/MM/yyyy') = ")
//					.append("TO_DATE(?, 'dd/MM/yyyy') ")
//					.append("AND (pq.FechaProg + (")//en lugar de comparar solamente la hora debemos determinar si cae dentro del rango de fecha/hora inicial y final
//					.append(cal.get(Calendar.HOUR_OF_DAY))
//					.append("/24)) BETWEEN (pq.FechaProg + (pq.HorainIcio/24)) AND (pq.FechaFin+ (pq.HoraInicio/24)) ");
//					
//					if (DB.isOracle()) {
//					    sql.append(" AND ROWNUM < 2 ");
//					}
//
//					sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedico.Table_Name));
//
//					if (cierre) {
//						sql.append("AND TRIM(pq.DocStatus) = "); // '3'
//						sql.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_ActiveAndNonClose));
//					}
//					
//					if (DB.isPostgreSQL()) {
//						sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
//					}
//
//					pstmt = DB.prepareStatement(sql.toString(), trxName);
//
//					pstmt.setLong(1, xxQuirofanoId);
//					pstmt.setString(2, Constantes.getSdfFecha().format(fecha));
//
//					rs = pstmt.executeQuery();
//
//					if (rs.next()) {
//						pv.setXxProgQuiroId(rs.getLong("progQuiroId"));
//						pv.setXxQuirofanoId(rs.getLong("quiroId"));
//						pv.setXxMedicoId(rs.getLong("medicoId"));
//						pv.setNomMedico(rs.getString("nomMedico"));
//						pv.setCtaPacId(0);
//						pv.setNomPaciente(rs.getString("nombrepac"));
//						pv.setNomIntervencion("");
//						pv.setFechaInicio(new java.util.Date(rs.getDate("FechaProg").getTime()));
//						pv.setEstatus(rs.getString("Estatus").trim());
//						double hora = rs.getDouble("HoraInicio");
//						pv.setHoraInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(0, 2)));
//						pv.setMinInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(3, 5)));
//						pv.setObservaciones(rs.getString("description"));
//						pv.setMedValue(rs.getString("Value").trim());
//						pv.setPreOperatorio(rs.getInt("EXME_PreOperatorio_ID"));
//
//					} else {
//
//						DB.close(rs, pstmt);
//						// Query para programaciones que no empiezan en minutos mayores a 0
//						sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//    					sql.append("SELECT EXME_Medico.Name||' '||EXME_Medico.Apellido1||' '||EXME_Medico.Apellido2 AS nomMedico, EXME_Medico.EXME_Medico_ID AS medicoId, ")
//    					.append(" pq.EXME_ProgQuiro_ID AS progQuiroId, pq.EXME_Quirofano_ID AS quiroId, pq.FechaProg, pq.fechafin,pq.HoraInicio, ")
//    					.append(" pq.DocStatus AS Estatus, pq.nombrepac, pq.description, EXME_Medico.Value ")
//    					.append(" , po.EXME_PreOperatorio_ID ")
//    					.append(" FROM  EXME_Medico ")
//    					.append(" LEFT JOIN EXME_ProgQuiro pq ON (EXME_Medico.EXME_Medico_ID = pq.EXME_Medico_ID) ")
//    					//Solicitud de cirugia si es que esta existe
//    					.append(" LEFT JOIN EXME_PreOperatorio po ON ( pq.EXME_ProgQuiro_ID = po.EXME_ProgQuiro_ID ) ")
//    					.append(" WHERE pq.EXME_Quirofano_ID = ? ") // #1 .append(xxQuirofanoId)
//    					.append(" AND TO_DATE(TO_CHAR(pq.FechaProg, 'dd/MM/yyyy'), 'dd/MM/yyyy') = TO_DATE(?, 'dd/MM/yyyy') ")//#2 -sdfFecha.format(fecha)
//    					.append(" AND (TO_DATE(TO_CHAR(pq.FechaProg, 'dd/MM/yyyy HH24'), 'dd/MM/yyyy HH24')) = (TO_DATE(?, 'dd/MM/yyyy HH24')) ") // #3 -  sdfFecha.format(fecha) + " "+ cal.get(Calendar.HOUR_OF_DAY)
//    					.append(" AND (TO_DATE(TO_CHAR(pq.FechaProg, 'dd/MM/yyyy HH24'), 'dd/MM/yyyy HH24'))")
//    					.append(" BETWEEN (TO_DATE(?, 'dd/MM/yyyy HH24:Mi')) ") // #4 sdfFecha.format(fecha) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":00"
//    					.append(" AND (TO_DATE(?, 'dd/MM/yyyy HH24:Mi')) "); //#5 - sdfFecha.format(fecha))+ " "+ cal.get(Calendar.HOUR_OF_DAY)+":59" 
//
//    					if (DB.isOracle()) {
//    					    sql.append(" AND ROWNUM < 2 ");
//    					}
//
//						sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedico.Table_Name));
//
//						if (cierre) {
//							sql.append("AND TRIM(pq.DocStatus) = "); // '3'
//							sql.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_ActiveAndNonClose));
//						}
//						
//						if (DB.isPostgreSQL()) {
//							sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
//						}
//
//						pstmt = DB.prepareStatement(sql.toString(), trxName);
//						// Lama
//						pstmt.setLong(1, xxQuirofanoId);
//						pstmt.setString(2, Constantes.getSdfFecha().format(fecha));
//						pstmt.setString(3, Constantes.getSdfFecha().format(fecha) + " " + cal.get(Calendar.HOUR_OF_DAY));
//						pstmt.setString(4, Constantes.getSdfFecha().format(fecha) + " " + cal.get(Calendar.HOUR_OF_DAY)
//								+ ":00");
//						pstmt.setString(5, Constantes.getSdfFecha().format(fecha) + " " + cal.get(Calendar.HOUR_OF_DAY)
//								+ ":59");
//
//						rs = pstmt.executeQuery();
//
//						if (rs.next()) {
//							pv.setXxProgQuiroId(rs.getLong("progQuiroId"));
//							pv.setXxQuirofanoId(rs.getLong("quiroId"));
//							pv.setXxMedicoId(rs.getLong("medicoId"));
//							pv.setNomMedico(rs.getString("nomMedico"));
//							pv.setCtaPacId(0);
//							pv.setNomPaciente(rs.getString("nombrepac"));
//							pv.setNomIntervencion("");
//							pv.setFechaInicio(new java.util.Date(rs.getDate("FechaProg").getTime()));
//							pv.setEstatus(rs.getString("Estatus").trim());
//							double hora = rs.getDouble("HoraInicio");
//							pv.setHoraInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(0, 2)));
//							pv.setMinInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(3, 5)));
//							pv.setObservaciones(rs.getString("description"));
//							pv.setMedValue(rs.getString("Value").trim());
//							pv.setPreOperatorio(rs.getInt("EXME_PreOperatorio_ID"));
//
//						} else {
//							// definitivamente no hay programa para esta fecha/hora
//							pv.setXxProgQuiroId(0);
//						}
//					}
//				}
//			}
//
//			MEXMEMedico medico = null;
//			// tenemos medico auxiliar
//			if (pv.getXxMedico2Id() > 0) {
//				medico = new MEXMEMedico(ctx, (int) pv.getXxMedico2Id(), trxName);
//				pv.setNomMedico2(medico.getFullName());
//			}
//			// tenemos anestesiologo
//			if (pv.getXxMedico3Id() > 0) {
//				medico = new MEXMEMedico(ctx, (int) pv.getXxMedico2Id(), trxName);
//				pv.setNomMedico3(medico.getFullName());
//			}
//			// tenemos auxiliar 2
//			if (pv.getXxMedico4Id() > 0) {
//				medico = new MEXMEMedico(ctx, (int) pv.getXxMedico4Id(), trxName);
//				pv.setNomMedico4(medico.getFullName());
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getProgramacion", e);
//			throw e;
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//
//		return pv;
//	}
    	
    	
	/**
	 * Recupera un programa de quirofano especifico.
	 *
	 * @param xxProgQuiroId El identificador de la programacion que
	 * deseamos cargar
	 * @param ctx El contexto de la aplicacion.
	 *
	 * @return Un valor ProgramacionView con los datos de la
	 * programacion especifica
	 *
	 * @throws Exception en caso de ocurrir un error al procesar la consulta.
	 * @deprecated usada en CierreQuirDetAction
	 */
//	public static ProgramacionView getProgQuiro(Properties ctx, long xxProgQuiroId, String trxName) throws Exception {
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		// PreparedStatement con la sentencia sql
//		PreparedStatement pstmt = null;
//		// ResultSet utilizado para manipular los resultados
//		ResultSet rs = null;
//
//		ProgramacionView pv = new ProgramacionView();
//
//		try {
//
//			// probablemente aun no tiene intervenciones relacionadas
//			sql.append("SELECT EXME_Medico.Nombre_Med AS nomMedico, ")
//    	    	.append("EXME_Medico.EXME_Medico_ID as medicoId, ")
//    	    	.append("pq.EXME_Medico2_ID as medico2Id, ")
//    	    	.append("pq.EXME_Medico3_ID as medico3Id, ")
//    	    	.append("pq.EXME_Medico4_ID as medico4Id, ")
//    	    	.append("pq.EXME_ProgQuiro_ID AS progQuiroId, ")
//    	    	.append("pq.EXME_Quirofano_ID AS quiroId, ")
//    	    	.append("pq.FechaProg AS fecha, ")
//    	    	.append("pq.HoraInicio AS hora, ")
//    	    	.append("pq.HoraFin AS horaF, ")
//    	    	.append("pq.DocStatus AS Estatus, ")
//    	    	.append("pq.NombrePac, ")
//    	    	.append("pq.Description, ")
//    	    	.append("pq.FechaFin AS fechaF, ")
//    	    	.append("pq.EXME_CtaPac_ID ctaPac, ")
//    	    	.append("q.Name AS nomQuiro ")
//    	    	.append(" , po.EXME_PreOperatorio_ID ")
//    	    	.append("FROM EXME_ProgQuiro pq ")
//    	    	.append(" INNER JOIN EXME_Medico  ON (EXME_Medico.EXME_Medico_ID = pq.EXME_Medico_ID AND pq.EXME_ProgQuiro_ID = ? )")
//    	    	.append(" INNER JOIN EXME_Quirofano q ON (q.EXME_Quirofano_ID = pq.EXME_Quirofano_ID) ")
//    	    	//Solicitud de cirugia si es que esta existe - Lama : Left Join, no siempre tiene solicitud.
//    	    	.append(" LEFT JOIN  EXME_PreOperatorio po ON (pq.EXME_ProgQuiro_ID = po.EXME_ProgQuiro_ID) ")
//
//    	    	.append("WHERE pq.IsActive = 'Y' ");
//
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MProgQuiro.Table_Name));
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setLong(1, xxProgQuiroId);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				pv.setXxProgQuiroId(rs.getLong("progQuiroId"));
//				pv.setXxQuirofanoId(rs.getLong("quiroId"));
//				pv.setXxMedicoId(rs.getLong("medicoId"));
//				pv.setNomMedico(rs.getString("nomMedico"));
//				pv.setNomPaciente(rs.getString("NombrePac"));
//				pv.setFechaInicio(new java.util.Date(rs.getDate("fecha").getTime()));
//				pv.setEstatus(rs.getString("Estatus").trim());
//				double hora = rs.getDouble("hora");
//				pv.setHoraInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(0, 2)));
//				pv.setMinInicio(Integer.parseInt(Constantes.getDfHora().format(hora).substring(3, 5)));
//				pv.setFechaFin(new java.util.Date(rs.getDate("fechaF").getTime()));
//				hora = rs.getDouble("horaF");
//				pv.setHoraFin(Integer.parseInt(Constantes.getDfHora().format(hora).substring(0, 2)));
//
//				/*El resultset tiene la hora final en formato decimal y no en date,
//				 * ocasionando que los minutos con 0 a la izquierda (09 para abajo)
//				 * aparezcan sin el cero y al hacer el substring de 3, 5, se agrega
//				 * un cero a la izquierda. Para estos casos, se debe de hacer un 
//				 * substring de 3, 4 para que tome el �nico d�gito de los minutos
//				 * que no sea cero.
//				 *
//				if (String.valueOf(hora).length() == 4){
//					pv.setMinFin(Integer.parseInt(dfHora.format(hora).substring(3, 4)));
//				} else {
//				 *Carmen .- Se cambio para que se guarde el cero a la izquiera (09 para abajo)*/
//					pv.setMinFin(Integer.parseInt(Constantes.getDfHora().format(hora).substring(3, 5)));
//				//}
//    				
//				pv.setNomQuirofano(rs.getString("nomQuiro"));
//				pv.setXxMedico2Id(rs.getLong("medico2Id"));
//				pv.setXxMedico3Id(rs.getLong("medico3Id"));
//				pv.setXxMedico4Id(rs.getLong("medico4Id"));
//				pv.setObservaciones(rs.getString("description"));
//				pv.setCtaPacId(rs.getLong("ctaPac"));
//
//				// en caso de tener cuenta paciente, recuperamos el nombre del mismo
//				if (pv.getCtaPacId() > 0 && (pv.getNomPaciente() != null && pv.getNomPaciente().length() == 0)) {
//					MEXMEPaciente paciente = new MEXMEPaciente(ctx, (int) pv.getCtaPacId(), trxName);
//					pv.setNomPaciente(paciente.getNombre_Pac());
//				}
//
//				// tenemos medico
//				MEXMEMedico medico = null;
//				if (pv.getXxMedicoId() > 0) {
//					medico = new MEXMEMedico(ctx, (int) pv.getXxMedicoId(), trxName);
//					pv.setNomMedico(medico.getFullName());
//				}
//
//				// tenemos medico auxiliar
//				if (pv.getXxMedico2Id() > 0) {
//					medico = new MEXMEMedico(ctx, (int) pv.getXxMedico2Id(), trxName);
//					pv.setNomMedico2(medico.getFullName());
//				}
//
//				// tenemos anestesiologo
//				if (pv.getXxMedico3Id() > 0) {
//					medico = new MEXMEMedico(ctx, (int) pv.getXxMedico3Id(), trxName);
//					pv.setNomMedico3(medico.getFullName());
//				}
//
//				// tenemos auxiliar 2
//				if (pv.getXxMedico4Id() > 0) {
//					medico = new MEXMEMedico(ctx, (int) pv.getXxMedico4Id(), trxName);
//					pv.setNomMedico4(medico.getFullName());
//				}
//
//				// determinamos la duracion de la intervencion
//				// fecha de inicio o programada
//				Calendar calIni = Calendar.getInstance();
//				calIni.setTime(pv.getFechaInicio());
//				calIni.set(Calendar.HOUR_OF_DAY, pv.getHoraInicio());
//				calIni.set(Calendar.MINUTE, pv.getMinInicio());
//
//				Calendar calFin = Calendar.getInstance();
//				calFin.setTime(pv.getFechaFin());
//				calFin.set(Calendar.HOUR_OF_DAY, pv.getHoraFin());
//				calFin.set(Calendar.MINUTE, pv.getMinFin());
//				calFin.add(Calendar.MINUTE, 1); // compensamos el minuto restado al guardar
//
//				String duracion = Utilerias.calcHoraMin(calIni, calFin);
//				pv.setDuraHora(duracion.substring(0, 2));
//				pv.setDuraMin(duracion.substring(3, 5));
//
//				DB.close(rs, pstmt);
//				// necesitamos buscar las intervenciones relacionadas con esta programacion
//				sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//				
//				sql.append("SELECT * FROM EXME_ProgQuiroDet ")
//					.append("WHERE EXME_ProgQuiro_ID = ? ");
//				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//
//				pstmt = DB.prepareStatement(sql.toString(), trxName);
//				pstmt.setLong(1, pv.getXxProgQuiroId());
//
//				rs = pstmt.executeQuery();
//
//				MIntervencion inter = null;
//				while (rs.next()) {
//					IntervencionView iv = new IntervencionView();
//					iv.setXxIntervencionId(rs.getLong("EXME_Intervencion_ID"));
//					iv.setObservaciones(rs.getString("Description"));
//					inter = new MIntervencion(ctx, (int) iv.getXxIntervencionId(), trxName);
//					iv.setName(inter.getName());
//					iv.setValue(inter.getValue());
//					pv.getLstInterv().add(iv);
//				}
//
//				if (pv.getLstInterv().size() > 0) {
//					IntervencionView iv = (IntervencionView) pv.getLstInterv().get(0);
//					inter = new MIntervencion(ctx, (int) iv.getXxIntervencionId(), trxName);
//					pv.setNomIntervencion(inter.getName());
//				}
//				
//				DB.close(rs, pstmt);
//				// y el equipo seleccionado
//				sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//				sql.append(" SELECT e.EXME_Equipo_ID, e.Name  ")
//    				.append(" FROM EXME_ProgQuiroEq  ")
//    				.append(" INNER JOIN EXME_Equipo e ON EXME_ProgQuiroEq.EXME_Equipo_ID = e.EXME_Equipo_ID ")
//    				.append(" WHERE EXME_ProgQuiroEq.EXME_ProgQuiro_ID = ? ");
//
//				pstmt = DB.prepareStatement(sql.toString(), trxName);
//				pstmt.setLong(1, pv.getXxProgQuiroId());
//
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					QuirEquiposView view = new QuirEquiposView(ctx, 0, trxName);
//					view.setEquipoID(rs.getLong("EXME_Equipo_ID"));
//					view.setEXME_Equipo_ID(rs.getInt("EXME_Equipo_ID"));
//					view.setEquipoName(rs.getString("Name"));
//					view.setSelected(true);
//					pv.getLstEquipo().add(view);
//				}
//
//				/* Agregamos los dem�s equipos disponibles para el quir�fano */
//				ArrayList<QuirEquiposView> equiposDisponibles = MEqQuirofano.getEquiposQuirofano(ctx,
//						pv.getXxQuirofanoId(), pv.getLstEquipo(), trxName);
//				for (int x = 0; x < equiposDisponibles.size(); x++) {
//					pv.getLstEquipo().add(equiposDisponibles.get(x));
//				}
//
//			} else {
//				// definitivamente no existe este programa de quirofano
//				throw new Exception("No existe el programa " + xxProgQuiroId);
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getProgQuiro", e);
//			throw e;
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//
//		return pv;
//	}

	/**
	 * 
	 * @param ctx
	 * @param QuirofanoId
	 * @param fecha
	 * @param cierre
	 * @param trxName
	 * @return
	 * @throws Exception
	 * @deprecated usado en CierreQuirofanosAction / ProgQuirofanosAction
	 */
//	public static int progNumPerID(Properties ctx, long QuirofanoId, java.util.Date fecha, boolean cierre,
//			String trxName) throws Exception {
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int retValue = 0;
//
//		try {
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(fecha);
//			int hour = cal.get(Calendar.HOUR_OF_DAY);
//			int hour2 = hour + 1;
//
//			cal.setTime(fecha);
//
//			sql.append("select EXME_ProgQuiro.exme_progquiro_id, m.description as medico, EXME_ProgQuiro.nombrepac as paciente, ") 
//    			.append(" to_char(EXME_ProgQuiro.fechainicio, 'HH24:mi')as horainicio, to_char(EXME_ProgQuiro.fechafin, 'HH24:mi')as horafin ") 
//    			.append(" from exme_progquiro ") 
//    			.append(" left join exme_medico m on (m.exme_medico_id = EXME_ProgQuiro.exme_medico_id) ") 
//    			.append(" where to_date(to_char(EXME_ProgQuiro.fechaprog, 'dd/MM/yyyy'), 'dd/MM/yyyy') = to_date(?, 'dd/MM/yyyy') ")  //#1
//    			.append(" and to_char(EXME_ProgQuiro.fechainicio, 'HH24') >= ? ") // #2.append(hour)
//    			.append(" and to_char(EXME_ProgQuiro.fechainicio, 'HH24') < ? ")// #3 .append(hour2)
//    			.append(" and EXME_ProgQuiro.exme_quirofano_id = ? "); //#4
//    			//.append(" AND EXME_ProgQuiro.AD_Client_ID = ? ")
//    			//.append(" AND EXME_ProgQuiro.AD_Org_ID = ? ");
//
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MProgQuiro.Table_Name));
//
//			if (cierre) {
//				sql.append("AND TRIM(EXME_ProgQuiro.DocStatus) = "); // '3'
//				sql.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_ActiveAndNonClose));
//			}
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//
//			pstmt.setString(1, Constantes.getSdfFecha().format(fecha));
//			pstmt.setInt(2, hour);
//			pstmt.setInt(3, hour2);
//			pstmt.setLong(4, QuirofanoId);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				retValue++;
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "progNumPerID", e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//
//		return retValue;
//	}

}
