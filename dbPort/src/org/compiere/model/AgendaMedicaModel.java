package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.SMS;
import org.compiere.util.Utilerias;


/**
 * Modelo para la Agenda Medica
 * @author Lorena Lama
 * 
 */
public class AgendaMedicaModel { 

	
	 private static CLogger s_log = CLogger.getCLogger(AgendaMedicaModel.class);

	 /**
	  * Obtiene los colores usuados en la agenda medica, para un determinado asistente
	  * @param ctx
	  * @param asistenteID
	  * @param trxName
	  * @return
	  *
	 public static List<BasicoTresProps> getColores(Properties ctx, int asistenteID, int medicoID, String fechaIni, String fechaFin, String trxName) {
			List<BasicoTresProps> retValue = new ArrayList<BasicoTresProps>();
			
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			PreparedStatement psmt = null;
			ResultSet rs = null;
			boolean isAsistente = asistenteID>0?true:false;
			try {
				//Colores para prog. quirofanos, citas y actividades
				sql.append(sqlColorEspecialidad(ctx,isAsistente))
					.append(" UNION ")
					.append(sqlColorQuirofano(ctx,isAsistente))
					.append(" UNION  ")
					.append(sqlColorTipoActividad(ctx,1,isAsistente))
					.append(" ORDER BY tipo ");
					
				psmt = DB.prepareStatement(sql.toString(), trxName);				
				for (int j = 1; j <= 9; j++) {
					psmt.setInt(j, isAsistente?asistenteID:medicoID);
					psmt.setString(++j,fechaIni);
					psmt.setString(++j,fechaFin);
				}
				rs = psmt.executeQuery();
				String defaultColor = Utilerias.convertHex(255,255,255);
				while (rs.next()) {
					retValue.add(new BasicoTresProps(rs.getInt("ad_color_id"), 
							rs.getString("red")==null?defaultColor:Utilerias.convertHex(rs.getInt("red"),rs.getInt("green"),rs.getInt("blue")),
							 rs.getString("name"), 
							 rs.getString("tipo")));
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			} finally {
				try {
					if ( rs != null )
						rs.close();
					if ( psmt != null )
						psmt.close();
				} catch (Exception e) {
					s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
				}
			}
			
			return retValue;
		}
*/
	/**
	 * Obtiene todas las tareas relacionadas a un medico determinado
	 * Busca la informacion relacionada al medico(s):
	 * Cita Medica: 
	 *  1- Texto: No. Cita:  y Numero de Cita
	 *  2- Color: color de Especialidad
	 *  3- Tooltip: Tipo de cita,  Historia, expediente y Nombre de Paciente, sexo, edad, icono de enlace al ECE del paciente
	 * Programacion de Quirofanos: 
	 * 1- Texto: Programacion de Quirofano: y Numero de Programacion
	 * 2- Color: color de Quirofanos
	 * 3- Tooltip: Historia (si tiene), Nombre de Paciente, Estatus de la programacion, Rol del Medico (Anestesiologo, Auxiliar, etc.)
	 * Actividad: 
	 * 1- Texto: Nombre de la Actividad
	 * 2- Color: color de Tipo de actividad
	 * 3- Tooltip: Nombre y descripcion de la Actividad
	 * @param ctx
	 * @param medicoID
	 * @param fechaIni
	 * @param fechaFin
	 * @param excludeCancel
	 * @param trxName
	 * @return
	 *
	public static List<AgendaMedicaView> getData(Properties ctx, int medicoID, String fechaIni, String fechaFin, boolean excludeCancel) {
		List<AgendaMedicaView> relList = new ArrayList<AgendaMedicaView>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			//Agregamos las citas
			sql = getSQL(ctx,true, excludeCancel);
			psmt = DB.prepareStatement(sql.toString(), null);
		
			for (int j = 1; j <= 25; j++) {
				if(j<=21){
					psmt.setInt(j, medicoID);
					psmt.setString(++j, fechaIni);
				} else {
					psmt.setString(j, fechaIni);
				}
				psmt.setString(++j, fechaFin);
			}
			rs = psmt.executeQuery();

			String defaultColor = Utilerias.convertHex(255,255,255);
			while (rs.next()) {
				AgendaMedicaView agenda = new AgendaMedicaView();
				agenda.setMedicoID(medicoID);
				agenda.setRecordID(rs.getInt("llave"));
				agenda.setRecordType(rs.getString("tipo"));
				agenda.setRecordInfo(rs.getString("value"));
				if(rs.getString("red")==null)
					agenda.setRecordColor(defaultColor);
				else
					agenda.setRecordColor(Utilerias.convertHex(rs.getInt("red"),rs.getInt("green"),rs.getInt("blue")));
				agenda.setRecordEstatus(rs.getString("estatus"));
				//ToolTip.-Info
				agenda.setToolTip1(rs.getString("uno"));
				agenda.setToolTip2(rs.getString("dos"));
				agenda.setToolTip3(rs.getString("tres"));
				agenda.setRecordRefID(rs.getInt("cuatro"));
				agenda.setStartDate(rs.getTimestamp("fechaini"));
				agenda.setEndDate(rs.getTimestamp("fechafin"));
				agenda.setRecordDays(rs.getInt("dias"));
				agenda.setRepeated(rs.getString("repeated"));
				relList.add(agenda);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			}
		}

		return relList;
	}*/
	
	public static StringBuilder getSQL(Properties ctx, boolean isAgenda, boolean excludeCancel){
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		//Agregamos las citas
		sql.append(sqlCitas(ctx,isAgenda,false, excludeCancel))//citas medicas
			.append("\n UNION  \n")
			.append(sqlCitas(ctx,isAgenda,true, excludeCancel))//citas odonto
			//.append(sqlCitasOdontologia(ctx,true))
			.append("\n UNION  \n");
			/* Agregamos las programaciones de quirofanos para los 4 roles de medico
			 * 1 - Medico  * 2 - Med. Auxiliar  * 3 - Anestesiologo * 4 - Med. Auxiliar2 */
			for (int i = 1; i <= 4; i++) {
				sql.append(sqlProgQuiro(ctx,i,0, 0, isAgenda))
					.append("\n UNION  \n");
			}
			//Agregamos las actividades
			sql.append(sqlActividades(ctx, isAgenda));
			if(!isAgenda)	
				sql.append(" ORDER BY fechaIni, hora, minutos ");
			else
				sql.append(" ORDER BY tipo,fechaini ");
		return sql;
	}
	
	
	/**
	 * Valida si algo esta agendado para esa hora segun la duracion y fecha inicial de una cita
	 * @param ctx
	 * @param medicoID		Id del medico de la agenda a validar
	 * @param recordID		ID del registro, para excluirse de la validacion
	 * @param fechaIni		fecha formato: DD/MM/YYYY  HH24:MI:SS
	 * @param fechaFin		fecha formato: DD/MM/YYYY  HH24:MI:SS
	 * @param tipo				C: Cita, A: Actividad, Q: Quirofano
	 * @param excludeMedicalAppointments Excluir citas medicas
	 * @throws Exception
	 *
	public static void isOcupado(Properties ctx, int medicoID, int recordID, String fechaIni, String fechaFin, String tipo, boolean excludeMedicalAppointments) throws Exception {

		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append(
			" SELECT llave, tipo, fechaIni, medico  FROM (");
			
			// CITAS
			if (!excludeMedicalAppointments) {
				sql.append(" SELECT EXME_CitaMedica.EXME_CitaMedica_ID as llave, ");

				// FECHAINI
				sql.append(" NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita) AS fechaini, ");

				// FECHAFIN
				sql.append(" CASE WHEN EXME_CitaMedica.fechahrfin IS NULL   ");
				sql.append("     THEN (NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)) + (EXME_CitaMedica.duracion/1440)  ");
				sql.append("     ELSE EXME_CitaMedica.fechahrfin END AS fechafin,  ");

				sql.append(" 'C' tipo , m.value||'-'||m.nombre_med as medico ");
				sql.append(" FROM  EXME_CitaMedica ");
				sql.append(" INNER JOIN EXME_Medico m ON ( m.EXME_Medico_ID = EXME_CitaMedica.EXME_Medico_ID AND m.EXME_Medico_ID = ?  )");
				sql.append(" WHERE EXME_CitaMedica.isActive = 'Y' AND EXME_CitaMedica.estatus <> '" + MEXMECitaMedica.ESTATUS_Cancelled + "' ");
				// " AND EXME_CitaMedica.EXME_CitaMedica_ID <> ? " +
				sql.append(" UNION ");

				// QUIROFANO
				sql.append(" SELECT EXME_ProgQuiro.EXME_ProgQuiro_ID as llave, (CASE WHEN EXME_ProgQuiro.docstatus = " + MProgQuiro.DOCSTATUS_Closed + " THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END) as fechaini, ");
				sql.append(" nvl(EXME_ProgQuiro.fechacierre, EXME_ProgQuiro.fechafin) as fechafin, 'Q' tipo , ");
				sql.append(" m.value||'-'||m.nombre_med as medico ");
				sql.append(" FROM  EXME_ProgQuiro ");
				sql.append(" INNER JOIN EXME_Medico m ON ( ( m.EXME_Medico_ID = EXME_ProgQuiro.EXME_Medico_ID ");
				sql.append(" OR m.EXME_Medico_ID = EXME_ProgQuiro.EXME_Medico2_ID  ");
				sql.append(" OR m.EXME_Medico_ID = EXME_ProgQuiro.EXME_Medico3_ID  ");
				sql.append(" OR m.EXME_Medico_ID = EXME_ProgQuiro.EXME_Medico4_ID ) AND m.EXME_Medico_ID = ? ) ");
				sql.append(" WHERE EXME_ProgQuiro.isActive = 'Y' ");
				// " AND EXME_ProgQuiro.EXME_ProgQuiro_ID <> ? " +
				sql.append(" UNION ");
			}
			
			// ACTIVIDAD
			sql.append(" SELECT EXME_Actividad.EXME_Actividad_ID as llave, EXME_Actividad.startdate as fechaini, ");
			sql.append(" EXME_Actividad.enddate as fechafin, 'A' tipo , ");
			sql.append(" m.value||'-'||m.nombre_med as medico ");
			sql.append(" FROM  EXME_Actividad ");
			sql.append(" INNER JOIN EXME_Medico m ON ( m.EXME_Medico_ID = EXME_Actividad.EXME_Medico_ID AND m.EXME_Medico_ID = ?  )  ");
			//" AND EXME_Actividad.EXME_Actividad_ID <> ? " +
			sql.append("  ) WHERE ");
			if(tipo!=null)
				sql.append(" NOT ( llave = ? AND tipo = ? ) AND");
			
			sql.append("( fechaini BETWEEN (to_Date(?,'DD/MM/YYYY  HH24:MI:SS')+1/1440) " +  //ini6
								     " AND (to_Date(?,'DD/MM/YYYY  HH24:MI:SS')-1/1440) " + //fin7
			         " OR fechafin BETWEEN (to_Date(?,'DD/MM/YYYY  HH24:MI:SS')+1/1440)  "+ //ini8
			         				 " AND (to_Date(?,'DD/MM/YYYY  HH24:MI:SS')-1/1440) " + //fin9
			             " OR ( fechaini < (to_Date(?,'DD/MM/YYYY  HH24:MI:SS')+1/1440) " + //ini10
			              " AND fechafin > (to_Date(?,'DD/MM/YYYY  HH24:MI:SS')-1/1440) ) )" //fin11
			);
			
			psmt = DB.prepareStatement(sql.toString(), null);
			
			if (excludeMedicalAppointments) {
				psmt.setInt(1, medicoID);
				psmt.setInt(2, recordID);
				psmt.setString(3, tipo);

				psmt.setString(4, fechaIni);
				psmt.setString(5, fechaFin);
				psmt.setString(6, fechaIni);
				psmt.setString(7, fechaFin);
				psmt.setString(8, fechaIni);
				psmt.setString(9, fechaFin);
			} else {
				psmt.setInt(1, medicoID);
				psmt.setInt(2, medicoID);
				psmt.setInt(3, medicoID);
				psmt.setInt(4, recordID);
				psmt.setString(5, tipo);

				psmt.setString(6, fechaIni);
				psmt.setString(7, fechaFin);
				psmt.setString(8, fechaIni);
				psmt.setString(9, fechaFin);
				psmt.setString(10, fechaIni);
				psmt.setString(11, fechaFin);
			}
			
			rs = psmt.executeQuery();

			if (rs.next()) {
				throw new MedsysException("error.citasDetalle.duracion");				
			}

		} catch (Exception e) {
			if (!(e instanceof MedsysException)) {//LAMA: mandar al log solo las otras excepciones
				s_log.log(Level.SEVERE, sql.toString(), e);
			}
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			}
		}
	}
	*/
	/**
	 * Esta Ocupado
	 * 
	 * @param ctx
	 *            Contexto
	 * @param datos
	 *            Cita
	 * @param medico
	 *            Medico
	 * @param tipo
	 *            Tipo
	 * @param excludeMedicalAppointments
	 *            Excluir citas
	 * @throws MedsysException
	 *
	public static void isOcupado(Properties ctx, MEXMECitaMedica datos, MEXMEMedico medico, String tipo, boolean excludeMedicalAppointments) throws MedsysException {
		String fechaIni = Constantes.getSdfFechaHora().format(datos.getFechaHrCita()) + ":00";
		DateTime dt = new DateTime(datos.getFechaHrCita());
		dt = dt.plusMinutes(datos.getDuracion());
		String fechaFin = Constantes.getSdfFechaHora().format(dt.toDate()) + ":00";

		try {
			AgendaMedicaModel.isOcupado(ctx, medico.getEXME_Medico_ID(), datos.getEXME_CitaMedica_ID(), fechaIni, fechaFin, tipo, excludeMedicalAppointments);
		} catch (Exception ex) {
			if (ex instanceof MedsysException) {
				throw (MedsysException) ex;
			}
		}
	}
	
	/**
	 * Cadena SQL que regresa los colores configurados
	 * a desplegar en la Agenda Medica  para los tipo de actividades 
	 * @param ctx
	 * @param i
	 * @param isAsistente
	 * @return
	 *
	 private static StringBuilder sqlColorTipoActividad(Properties ctx, int i, boolean isAsistente) {
		 StringBuilder sqlColorTipoActividad = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			
		 sqlColorTipoActividad.append(" SELECT distinct c.ad_color_id,  c.red, c.green, c.blue, upper(t.name) as name, 'T' as tipo ")
		.append(" FROM EXME_Actividad a ")
		.append(" inner join exme_tipoactividad t on ( t.exme_tipoactividad_id = a.exme_tipoactividad_id ) ")
		.append(" inner join exme_medicoAsist me on (a.exme_medico_id = me.exme_medico_id ")
		.append(isAsistente?"and me.exme_asistente_id = ?":"and me.exme_medico_id = ? ").append( ")")
		.append(" left join ad_color c on (t.ad_color_id  = c.ad_color_id) ")
		.append(" WHERE trunc(a.startdate,'DD') BETWEEN to_date(?, 'DD/MM/YYYY') AND to_date(?, 'DD/MM/YYYY') ");

		return sqlColorTipoActividad;
	}

	/**
	 * Cadena SQL que regresa los colores configurados
	 * a desplegar en la Agenda Medica  para los quirofanos 
	 * @param ctx
	 * @param isAsistente
	 * @return
	 *
	private static StringBuilder sqlColorQuirofano(Properties ctx, boolean isAsistente) {
		StringBuilder sqlColorQuirofano = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				
		sqlColorQuirofano.append(" SELECT distinct c.ad_color_id,  c.red, c.green, c.blue, upper(q.name) as name, 'Q' as tipo ")
		.append(" FROM EXME_Progquiro p ")
		.append(" inner join exme_quirofano q on ( q.exme_quirofano_id = p.exme_quirofano_id  ) ")
		.append(" inner join exme_medicoAsist me on (p.exme_medico_id  = me.exme_medico_id ")
		.append(isAsistente?"and me.exme_asistente_id = ?":"and me.exme_medico_id = ? ").append( ")")
		.append(" left join ad_color c on (q.ad_color_id  = c.ad_color_id) ")
		.append(" WHERE trunc(p.fechainicio,'DD') BETWEEN to_date(?, 'DD/MM/YYYY') AND to_date(?, 'DD/MM/YYYY') ");

		return sqlColorQuirofano;
	}
	
	/**
	 * Cadena SQL que regresa los colores configurados
	 * a desplegar en la Agenda Medica  para las especialidades  
	 * @param ctx
	 * @param isAsistente
	 * @return
	 *
	private static StringBuilder sqlColorEspecialidad(Properties ctx, boolean isAsistente) {
		StringBuilder sqlColorEspecialidad = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sqlColorEspecialidad.append(" SELECT distinct c.ad_color_id, c.red, c.green, c.blue, upper(e.name) as name, 'E' as tipo ")
		.append(" FROM EXME_CitaMedica cm ")
		.append(" inner join exme_especialidad e on ( e.exme_especialidad_id = cm.exme_especialidad_id  ) ")
		.append(" inner join exme_medicoAsist me on (cm.exme_medico_id = me.exme_medico_id ")
		.append(isAsistente?"and me.exme_asistente_id = ?":"and me.exme_medico_id = ? ").append( ")")
		.append(" left join ad_color c on (e.ad_color_id  = c.ad_color_id) ")
		.append(" WHERE trunc(nvl(cm.fechahrini, cm.fechahrcita),'DD') BETWEEN to_date(?, 'DD/MM/YYYY') AND to_date(?, 'DD/MM/YYYY') ")
		.append(" AND cm.estatus <> ").append(DB.TO_STRING(MEXMECitaMedica.ESTATUS_Cancelled));

		return sqlColorEspecialidad;	
	}
	
	/**
	 * Cadena SQL que regresa la informacion de las Citas Medicas
	 * a desplegar en la Agenda Medica de acuerdo a un medico 	
	 * @param ctx
	 * @param excludeCancel
	 * @return
	 */
	public static StringBuilder sqlCitas(Properties ctx, boolean agenda, boolean odonto, boolean excludeCancel){
		StringBuilder sqlCitas = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		try {
			
				sqlCitas.append("SELECT DISTINCT EXME_CitaMedica.EXME_CitaMedica_ID AS llave, ")
						.append(odonto?"'CO'":"'C'").append(" as tipo, ")
						.append("NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita) AS fechaini, ")
						.append("CASE WHEN EXME_CitaMedica.fechahrfin IS NULL ");
				
				if (DB.isOracle()) {
					sqlCitas.append("THEN (NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)) + (EXME_CitaMedica.duracion/1440) ");
				} else if (DB.isPostgreSQL()) {
					// La duracion esta en minutos, castearla
					// Jesus Cantu
					sqlCitas.append("THEN (NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)) + cast (EXME_CitaMedica.duracion || ' minutes' as interval) ");
				}
				
				sqlCitas.append("ELSE EXME_CitaMedica.fechahrfin END AS fechafin, ")
						.append(sqlRepeatedCitas() + ',');
				if (agenda) {
					//Nunca alcanzara este bloque ya que siempre manda false en agenda. Jesus Cantu
					sqlCitas.append("EXME_CitaMedica.name AS value,  c.red, c.green, c.blue, ")
					//.append("CASE WHEN EXME_CitaMedica.confirmada = 'Y' AND EXME_CitaMedica.estatus = 0 THEN '3' ELSE EXME_CitaMedica.estatus END AS estatus, ")
					.append("CASE WHEN EXME_CitaMedica.processing = 'Y' THEN '3' ")
					.append("WHEN EXME_CitaMedica.estatus <> '3' AND EXME_CitaMedica.fechahrini IS NOT NULL AND EXME_CitaMedica.fechahrfin IS NULL  THEN '1' ")
					.append("ELSE EXME_CitaMedica.estatus END AS estatus, ");
					
					if (DB.isOracle()) {
						sqlCitas.append("trunc((CASE WHEN EXME_CitaMedica.fechahrfin IS NULL  ")					
						.append("THEN (NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)) + (EXME_CitaMedica.duracion/1440)  ")
						.append("ELSE EXME_CitaMedica.fechahrfin END ),'DD') - trunc(NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita),'DD') + 1 AS dias, ");
					} else if (DB.isPostgreSQL()) {
						//FIXME: No funciona asi el DATE_TRUNC para este caso, sin embargo siempre viene false en agenda. No se corrige
						sqlCitas.append("DATE_TRUNC('day',(CASE WHEN EXME_CitaMedica.fechahrfin IS NULL  ")					
						.append("THEN (NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)) + (EXME_CitaMedica.duracion/1440)  ")
						.append("ELSE EXME_CitaMedica.fechahrfin END )) - DATE_TRUNC('day', NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)) + 1 AS dias, ");
					}
					sqlCitas.append("to_char(EXME_CitaMedica.citaDe) AS uno, pac.value||' - '||pac.nombre_pac||' ' AS dos,")
					.append("pac.sexo as tres, pac.EXME_Paciente_ID AS cuatro ");
				} else {
					sqlCitas
					.append("TO_CHAR(NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita), 'HH24') AS Hora,")
		    		.append("TO_CHAR(NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita), 'MI') AS Minutos, ")
		    		.append("EXME_CitaMedica.EXME_Especialidad_ID AS key, Exme_citamedica.duracion ");
				}
				
				sqlCitas.append(" FROM EXME_CitaMedica ");
				
				if(agenda) {
					//Nunca alcanzara este bloque ya que siempre manda false en agenda. Jesus Cantu
					sqlCitas.append(" INNER JOIN exme_paciente pac on EXME_CitaMedica.exme_paciente_id = pac.exme_paciente_id ")
					.append(" INNER JOIN EXME_especialidad e on EXME_CitaMedica.exme_especialidad_id = e.exme_especialidad_id ")
					.append(" LEFT JOIN ad_color c on (e.ad_color_id  = c.ad_color_id) ");
				}
				
				sqlCitas.append(" WHERE EXME_CitaMedica.exme_medico_id = ? ");
				if (DB.isOracle()) {
					sqlCitas.append(" AND trunc((NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)),'DD') BETWEEN to_Date(?,'DD/MM/YYYY') AND to_Date(?,'DD/MM/YYYY') ");
				} else if (DB.isPostgreSQL()) {
					sqlCitas.append(" AND DATE_TRUNC('day', (NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita))) BETWEEN to_Date(?,'DD/MM/YYYY') AND to_Date(?,'DD/MM/YYYY') ");
				}	
				
				sqlCitas.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MEXMECitaMedica.Table_Name));
				
				if(!agenda || excludeCancel) {
					sqlCitas.append(" AND EXME_CitaMedica.estatus <> "+DB.TO_STRING(MEXMECitaMedica.ESTATUS_Cancelled));
				}
		
				if(!odonto) {
					sqlCitas.append(" AND EXME_CitaMedica.EXME_Especialidad_id <> "+MEXMECitaMedica_MO.getEspecialidadforOdonto(ctx, null));
				} else {
					    String subEspecialidades = MEXMEEspecialidad.getLstSubEspecialidadesID_String(new Integer(MEXMECitaMedica_MO.getEspecialidadforOdonto(ctx, null)));
						sqlCitas.append(" AND EXME_CitaMedica.EXME_Especialidad_id in ("+ 
								MEXMECitaMedica_MO.getEspecialidadforOdonto(ctx, null) +
								(subEspecialidades.equalsIgnoreCase("") ? new String(") ") : new String(", "+subEspecialidades+") ")) )
								.append(" AND EXME_CitaMedica.estatus <> ").append(DB.TO_STRING(MEXMECitaMedica.ESTATUS_Closed));
					
				}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "sqlCitas antes de ejecutar: " + sqlCitas, e);
		}
		return sqlCitas;
	}
	
	
	/**
	 * Cadena SQL que regresa la informacion de las Citas Medicas Odontologicas
	 * a desplegar en la Agenda Medica de acuerdo a un medico 	
	 * @param ctx
	 * @return
	 *
	public static StringBuilder sqlCitasOdontologia(Properties ctx, boolean agenda){
		StringBuilder sqlCitas = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sqlCitas.append("SELECT DISTINCT EXME_CitaMedica.EXME_CitaMedica_ID AS llave, ")
				.append("'CO' as tipo, ")
				.append("NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita) AS fechaini, ")
				.append("CASE WHEN EXME_CitaMedica.fechahrini IS NULL AND EXME_CitaMedica.fechahrfin IS NULL ")
				.append("THEN EXME_CitaMedica.fechahrcita + (EXME_CitaMedica.duracion/1440) ")
				.append("WHEN EXME_CitaMedica.fechahrini IS NOT NULL AND EXME_CitaMedica.fechahrfin IS NULL ")
				.append("THEN EXME_CitaMedica.fechahrini + (EXME_CitaMedica.duracion/1440) ")
				.append("ELSE EXME_CitaMedica.fechahrfin END AS fechafin, ");
		
		if(agenda)
			sqlCitas.append("EXME_CitaMedica.name AS value,  c.red, c.green, c.blue, ")
			//.append("CASE WHEN EXME_CitaMedica.confirmada = 'Y' AND EXME_CitaMedica.estatus = 0 THEN '3' ELSE EXME_CitaMedica.estatus END AS estatus, ")
			.append("EXME_CitaMedica.estatus AS estatus, ")
			.append("trunc( (CASE WHEN EXME_CitaMedica.fechahrini IS NULL AND EXME_CitaMedica.fechahrfin IS NULL ")
			.append("THEN EXME_CitaMedica.fechahrcita + (EXME_CitaMedica.duracion/1440) ")
			.append("WHEN EXME_CitaMedica.fechahrini IS NOT NULL AND EXME_CitaMedica.fechahrfin IS NULL ")
			.append("THEN EXME_CitaMedica.fechahrini + (EXME_CitaMedica.duracion/1440) ")
			.append("ELSE EXME_CitaMedica.fechahrfin END),'DD') - trunc(NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita),'DD') + 1 AS dias, ")
			.append("to_char(EXME_CitaMedica.citaDe) AS uno, pac.value||' - '||pac.nombre_pac||' ' AS dos,")
			.append("pac.sexo as tres, pac.EXME_Paciente_ID AS cuatro ");
		else
			sqlCitas
			.append("TO_CHAR(NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita), 'HH24') AS Hora,")
    		.append("TO_CHAR(NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita), 'MI') AS Minutos, ")
    		.append("EXME_CitaMedica.EXME_Especialidad_ID AS key, Exme_citamedica.duracion ");
		
		sqlCitas.append(" FROM EXME_CitaMedica ");
		
		if(agenda)
			sqlCitas.append(" INNER JOIN exme_paciente pac on EXME_CitaMedica.exme_paciente_id = pac.exme_paciente_id ")
			.append(" INNER JOIN EXME_especialidad e on EXME_CitaMedica.exme_especialidad_id = e.exme_especialidad_id ")
			.append(" LEFT JOIN ad_color c on (e.ad_color_id  = c.ad_color_id) ");
		
		sqlCitas.append(" WHERE EXME_CitaMedica.exme_medico_id = ?  ")
			.append(" AND trunc((NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)),'DD') BETWEEN to_Date(?,'DD/MM/YYYY') AND to_Date(?,'DD/MM/YYYY') ")
			.append(" AND EXME_CitaMedica.EXME_ESpecialidad_id = "+MEXMECitaMedica_MO.getEspecialidadforOdonto(ctx, null))
			.append(" AND EXME_CitaMedica.estatus <> 8")
			
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MEXMECitaMedica.Table_Name));
		
		return sqlCitas;
	}*/

	
	/**
	 * Cadena SQL que regresa la informacion de las Actividades
	 * a desplegar en la Agenda Medica de acuerdo a un medico 
	 * @param ctx
	 * @return
	 */
	public static StringBuilder sqlActividades(Properties ctx, boolean agenda) {
		StringBuilder sqlActividades = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sqlActividades.append("SELECT DISTINCT EXME_Actividad.EXME_Actividad_ID as llave, ")
			.append("'A' as tipo, ")
			.append("EXME_Actividad.startdate as fechaini, ")
			.append("EXME_Actividad.enddate as fechafin, ")
			.append(sqlRepeatedActividad()+',');
		
		if(agenda) {
			//Agenda siempre es falso, nunca entrara a esta parte del codigo. Jesus Cantu.
			//Por tanto no se analizo la parte de Postgresql.
			sqlActividades.append("EXME_Actividad.value as value, c.red, c.green, c.blue, ");
			if (DB.isOracle()) {
				sqlActividades.append("'-' as estatus, trunc(EXME_Actividad.enddate,'DD')-trunc(EXME_Actividad.startdate,'DD')+1 as dias, "); // Formato
			} else if (DB.isPostgreSQL()) {
				sqlActividades.append("'-' as estatus, DATE_TRUNC('day', EXME_Actividad.enddate)-DATE_TRUNC('day', EXME_Actividad.startdate)+1 as dias, "); // Formato
			}
			sqlActividades.append("TO_CHAR(EXME_Actividad.name) as uno, EXME_Actividad.description as dos, TO_CHAR(ta.name) as tres, 0 as cuatro "); //Tooltip
	   }
		else
			sqlActividades.append("TO_CHAR(EXME_Actividad.startdate, 'HH24') Hora, ")
    		.append("TO_CHAR(EXME_Actividad.startdate, 'MI') Minutos, 0 as key, ");
		if (DB.isOracle()) {
			sqlActividades.append("trunc((EXME_Actividad.enddate - EXME_Actividad.startdate)*1440,0) as duracion ");
		} else if (DB.isPostgreSQL()) {
			//Se extrae dato epoch y luego se divide entre 60 para obtener la duracion en minutos.
			sqlActividades.append("extract('epoch' from (EXME_Actividad.enddate - EXME_Actividad.startdate)) / 60 as duracion ");
		}
		
		sqlActividades.append(" FROM EXME_Actividad ");
		
		if (agenda) //Nunca alcanzara este bloque ya que siempre manda false en agenda.
			sqlActividades.append(" INNER JOIN exme_tipoActividad ta on (EXME_Actividad.exme_tipoactividad_id = ta.exme_tipoactividad_id) ")
			.append(" INNER JOIN ad_color c on (ta.ad_color_id  = c.ad_color_id) ");
			
		sqlActividades.append(" WHERE EXME_Actividad.exme_medico_id = ? ");
		if (DB.isOracle()) {
			sqlActividades.append(" AND (trunc(EXME_Actividad.Startdate, 'DD') BETWEEN to_Date(?,'DD/MM/YYYY')  AND to_Date(?,'DD/MM/YYYY') OR ")
			.append(" trunc(EXME_Actividad.Enddate, 'DD') BETWEEN to_Date(?,'DD/MM/YYYY') AND to_Date(?,'DD/MM/YYYY') OR ")
			.append(" (trunc(EXME_Actividad.startdate, 'DD') < to_Date(?,'DD/MM/YYYY') AND ")
			.append(" trunc(EXME_Actividad.Enddate, 'DD') > to_Date(?,'DD/MM/YYYY'))) ");
		} else if (DB.isPostgreSQL()) {
			sqlActividades.append(" AND (DATE_TRUNC('day', EXME_Actividad.Startdate) BETWEEN to_Date(?,'DD/MM/YYYY')  AND to_Date(?,'DD/MM/YYYY') OR ")
			.append(" DATE_TRUNC('day', EXME_Actividad.Enddate) BETWEEN to_Date(?,'DD/MM/YYYY') AND to_Date(?,'DD/MM/YYYY') OR ")
			.append(" (DATE_TRUNC('day', EXME_Actividad.startdate) < to_Date(?,'DD/MM/YYYY') AND ")
			.append(" DATE_TRUNC('day', EXME_Actividad.Enddate) > to_Date(?,'DD/MM/YYYY'))) ");
		}
		sqlActividades.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MEXMEActividad.Table_Name));
		 
		return sqlActividades;
	}
	
	/**
	 * Cadena SQL que regresa la informacion de Prog. Quirofanos 
	 * a desplegar en la Agenda Medica de acuerdo a un medico 
	 * (este puede estar asignado como 4 diferentes roles)
	 * @param ctx
	 * @param medico
	 * @param estServ
	 * @param quirofano
	 * @return
	 */
	public static StringBuilder sqlProgQuiro(Properties ctx, int medico, int estServ, int quirofano, boolean agenda){
		StringBuilder sqlQuiro = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sqlQuiro
			.append("SELECT DISTINCT EXME_ProgQuiro.EXME_ProgQuiro_ID as llave, ")
			.append("'Q' as tipo, ")
			.append(" CASE WHEN EXME_ProgQuiro.docstatus = ");
		sqlQuiro.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_Closed));
		sqlQuiro.append(" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END AS fechaini, ")
			.append("nvl(EXME_ProgQuiro.fechacierre, EXME_ProgQuiro.fechafin) as fechafin, ")
			.append(sqlRepeatedProgQuiro()+',');
		
		if (agenda) {
			//Nunca alcanzara este bloque ya que siempre manda false en agenda.
			//Jesus Cantu. No se corrige la parte de postgresql, ya que el date_trunc asi no va a funcionar.
			sqlQuiro
				.append("EXME_ProgQuiro.documentno as value, ")
				.append(medico > 0 ? " c.red, c.green, c.blue, " : " null as red, null as green, null as blue, ")
				.append("to_char(EXME_ProgQuiro.docstatus) as estatus, ");
			if (DB.isOracle()) {
				sqlQuiro.append("trunc((nvl(EXME_ProgQuiro.fechacierre, EXME_ProgQuiro.fechafin)),'DD')-trunc((CASE WHEN EXME_ProgQuiro.docstatus = "+MProgQuiro.DOCSTATUS_Closed+" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END),'DD')  +1 as dias, ");
			} else if (DB.isPostgreSQL()) {
				sqlQuiro.append("DATE_TRUNC('day', (nvl(EXME_ProgQuiro.fechacierre, EXME_ProgQuiro.fechafin)))-DATE_TRUNC('day',(CASE WHEN EXME_ProgQuiro.docstatus = "+MProgQuiro.DOCSTATUS_Closed+" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END))  +1 as dias, ");
			}
				//.append("'").append(medico).append("' as uno, ")
			sqlQuiro.append(
						(quirofano > 0 || estServ > 0) ? " med.nombre_med " : DB.TO_STRING(String.valueOf(medico))
				).append(" as uno, ")
				.append("nvl(pac.value,'')|| ' ' ||nvl(pac.nombre_pac,EXME_ProgQuiro.nombrepac) as dos,TO_CHAR(cp.documentNo) as tres, q.exme_quirofano_id as cuatro ");
		
		} else {
			sqlQuiro
				.append("TO_CHAR((CASE WHEN EXME_ProgQuiro.docstatus = ");
			sqlQuiro.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_Closed));
			sqlQuiro.append(" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END), 'HH24') Hora,")
	    		.append("TO_CHAR((CASE WHEN EXME_ProgQuiro.docstatus = ");
			sqlQuiro.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_Closed));
			sqlQuiro.append(" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END), 'MI') Minutos, 0 as key,");
			if (DB.isOracle()) {
				sqlQuiro.append("trunc(((nvl(EXME_ProgQuiro.fechacierre, EXME_ProgQuiro.fechafin)) - (CASE WHEN EXME_ProgQuiro.docstatus = "+MProgQuiro.DOCSTATUS_Closed+" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END))*1440) as duracion ");
			} else if (DB.isPostgreSQL()) {
				/* 
				 * La diferencia entre dos timestamps en PostgreSQL regresa un dato de tipo interval
				 * Del interval se puede extraer el dato epoch
 				 * Este epoch se divide entre 60 para obtener el número de minutos transcurridos en el el interval 
				 */
				sqlQuiro.append(" extract('epoch' from (nvl(EXME_ProgQuiro.fechacierre, EXME_ProgQuiro.fechafin) - (CASE WHEN EXME_ProgQuiro.docstatus = ");
				sqlQuiro.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_Closed));
				sqlQuiro.append(" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END))) / 60 as duracion ");
			}
		}
		sqlQuiro
				.append(" FROM EXME_ProgQuiro ")
				.append(agenda ||  (quirofano > 0 || estServ > 0) ? " INNER JOIN EXME_Quirofano q on EXME_ProgQuiro.exme_quirofano_id = q.exme_quirofano_id " : "")
				.append(agenda && (quirofano > 0 || estServ > 0) ? " INNER JOIN EXME_Medico med on EXME_ProgQuiro.exme_medico_id = med.exme_medico_id " : "");
		
		if(agenda) {
			//Nunca alcanzara este bloque ya que siempre manda false en agenda.
			//Jesus Cantu
			sqlQuiro
				.append(medico > 0 ? " INNER JOIN ad_color c on (q.ad_color_id  = c.ad_color_id) " : "")
				.append(" LEFT JOIN exme_ctapac cp on EXME_ProgQuiro.exme_ctapac_id = cp.exme_ctapac_id  ")
				.append(" LEFT JOIN exme_paciente pac on cp.exme_paciente_id = pac.exme_paciente_id ");
		}
		sqlQuiro
			.append(" WHERE ")
			.append(medico > 0 ? "EXME_ProgQuiro.EXME_Medico"+(medico > 1 ? medico : "") 
										  : "q." + ( estServ > 0 ? "EXME_EstServ" : "EXME_quirofano" ))
			.append("_ID = ? ");
		
		//	sqlQuiro.append(" EXME_ProgQuiro.EXME_Medico").append(medico > 1 ? medico : "").append("_ID = ? AND ");
		if (DB.isOracle()) {
			sqlQuiro.append(" AND trunc((CASE WHEN EXME_ProgQuiro.docstatus = "+MProgQuiro.DOCSTATUS_Closed+" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END),'DD') BETWEEN to_Date(?,'DD/MM/YYYY') AND to_Date(?,'DD/MM/YYYY') ");
		} else if (DB.isPostgreSQL()) {
			sqlQuiro.append(" AND DATE_TRUNC('day', (CASE WHEN EXME_ProgQuiro.docstatus = ");
			sqlQuiro.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_Closed));
			sqlQuiro.append(" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END)) BETWEEN to_Date(?,'DD/MM/YYYY') AND to_Date(?,'DD/MM/YYYY') ");
		}
		sqlQuiro.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MProgQuiro.Table_Name));
		return sqlQuiro;
	}

	/**
	 * Obtenemos la hora inicial en que se mostrara la agenda del medico
	 * @author Laura Hernandez
	 * @param medico
	 * @return
	 *
	public static int getEarlyHour(Properties ctx, int medico){

		
		//lhernandez. Obtener el id del turno del médico
		int turnoId = MEXMEMedicoOrg.getTurnoMedico(ctx, medico);
		
		 StringBuilder sql = new StringBuilder(" SELECT HoraEnt1ES, HoraEnt2ES, HoraEnt3ES, HoraEnt1FS " )
		 .append(" FROM EXME_Turnos ")
		 .append(" WHERE EXME_Turnos_ID = ? ");
		 
		 List<Integer> lstHoras = new ArrayList<Integer>();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try{
			 pstmt = DB.prepareStatement(sql.toString(),null);
			 pstmt.setInt(1, turnoId);
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				 if(rs.getString(1) != null){
					 lstHoras.add((Integer.parseInt(rs.getString(1).replaceAll(":", ""))/100)); //Le quitamos los : para convertirlo a int y le quitamos dos ceros dividiendo entre 100.
				 }
				 if(rs.getString(2) != null){
					 lstHoras.add((Integer.parseInt(rs.getString(2).replaceAll(":", ""))/100));
				 }
				 if(rs.getString(3) != null){
					 lstHoras.add((Integer.parseInt(rs.getString(3).replaceAll(":", ""))/100));
				 }
				 if(rs.getString(4) != null){
					 lstHoras.add((Integer.parseInt(rs.getString(4).replaceAll(":", ""))/100));
				 }
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 try{
				 if(pstmt!=null){
					 pstmt.close();
					 pstmt = null;
				 }
				 if(rs!=null){
					 rs.close();
					 rs = null;
				 }
			 }catch(Exception e){
				 
			 }
		 }
		 int min = 0;
		if (!lstHoras.isEmpty()) {
			min = Integer.parseInt(lstHoras.get(0).toString());
			if (lstHoras.size() > 1) {
				for (int i = 0; i < lstHoras.size(); i++) {
					if (Integer.parseInt(lstHoras.get(i).toString()) < min) {
						min = Integer.parseInt(lstHoras.get(i).toString());
					}
				}
			}
		}
		return min;
	}

	/**
	 * Obtenemos la hora final en que se mostrara la agenda del medico
	 * @author Lorena Lama
	 * @param medico
	 * @return
	 *
	public static int getLastHour(Properties ctx, int medico){

		//lhernandez. Obtener el id del turno del médico
		int turnoId = MEXMEMedicoOrg.getTurnoMedico(ctx, medico);
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT HoraSal1ES, HoraSal2ES, HoraSal3ES, HoraSal1FS ")
		.append(" FROM EXME_Turnos ")
		.append(" WHERE EXME_Turnos_ID = ? ");

		List<Integer> lstHoras = new ArrayList<Integer>();
		PreparedStatement prepa = null;
		ResultSet rs = null;
	 	
		 try{
			 prepa = DB.prepareStatement(sql.toString(),null);
			 prepa.setInt(1,turnoId);
			 rs = prepa.executeQuery();
			 if(rs.next()){
				//Le quitamos los : para convertirlo a int y le quitamos dos ceros dividiendo entre 100.
				 if(rs.getString(1) != null){
					 lstHoras.add((Integer.parseInt(rs.getString(1).replaceAll(":", ""))/100)); 
				 }
				 if(rs.getString(2) != null){
					 lstHoras.add((Integer.parseInt(rs.getString(2).replaceAll(":", ""))/100));
				 }
				 if(rs.getString(3) != null){
					 lstHoras.add((Integer.parseInt(rs.getString(3).replaceAll(":", ""))/100));
				 }
				 if(rs.getString(4) != null){
					 lstHoras.add((Integer.parseInt(rs.getString(4).replaceAll(":", ""))/100));
				 }
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 try{
				 if(prepa!=null){
					 prepa.close();
					 prepa = null;
				 }
				 if(rs!=null){
					 rs.close();
					 rs = null;
				 }
			 }catch(Exception e){
				 
			 }
		 }
		 int max = 0;
		 if (!lstHoras.isEmpty()) {
			max = Integer.parseInt(lstHoras.get(0).toString());
			if (lstHoras.size() > 1) {
				for (int i = 0; i < lstHoras.size(); i++) {
					if (Integer.parseInt(lstHoras.get(i).toString()) > max) {
						max = Integer.parseInt(lstHoras.get(i).toString());
					}
				}
			}
		}
		 return max;	 
	 }
	
	
	
	/**
	 * Obtiene todas las tareas relacionadas a un medico determinado
	 * Busca la informacion relacionada al medico(s):
	 * Cita Medica: 
	 * Programacion de Quirofanos: 
	 * Actividad: 
	 * @param ctx
	 * @param medicoID
	 * @param fechaIni
	 * @param fechaFin
	 * @param trxName
	 * @return
	 */
	public static ResultSet getDetail(Properties ctx, int medicoID, String fechaIni, String fechaFin) {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			//Agregamos las citas
			sql = getSQL(ctx,false, true);			
			psmt = DB.prepareStatement(sql.toString(), null);
		
			for (int j = 1; j <= 25; j++) {
				if(j<=21){
					psmt.setInt(j, medicoID);
					psmt.setString(++j, fechaIni);
				} else {
					psmt.setString(j, fechaIni);
				}
				psmt.setString(++j, fechaFin);
			}
			rs = psmt.executeQuery();

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}

		return rs;
	}

	 /**
     * Metodo auxiliar que saca la fecha final apartir de una inicial y duracion
     * 
     * @author Julio Gutierrez
     * @param fechaini
     * @param fechafin
     * @return valor en minutos.
     *
    public static Date getFechaFinal(String fechaini, int duracion) throws Exception {
	
    	Date fechainial = Constantes.getSdfFechaHora().parse(fechaini);
    	Calendar calfin = Calendar.getInstance();
    	calfin.setTime(fechainial);
    	calfin.add(Calendar.MINUTE, duracion);

    	return calfin.getTime();
    }*/
    
    
    /**
     * Enviamos el correo dde notificacion de cita.
     * @author Lorena Lama
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public static boolean sendMail(Properties ctx, String message, String subject, String requestMail) {
		if (StringUtils.isBlank(requestMail)) {
			s_log.log(Level.SEVERE, Utilerias.getMsg(ctx, "error.notifica.notMail.email"));
			return false;
		}
		return Utilerias.sendMail(ctx, message, false, subject, requestMail);
    }

    /**
     * Envia el sms de notificacion de cita
     * @author Lorena Lama
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public static boolean sendSMS(Properties ctx, String message, String from, String telephone) {
		if (StringUtils.isBlank(message)) {
			s_log.log(Level.SEVERE, "Message is mandatory");
			return false;
		}
		if (StringUtils.isBlank(telephone) || StringUtils.isNumeric(telephone)) {
			s_log.log(Level.SEVERE, Utilerias.getMsg(ctx, "error.notifica.notTel.sms"));
			return false;
		}
		try {
			final SMS sms = new SMS(ctx);
			if (from == null) {
				from = sms.email;
			}
			return EMail.SENT_OK.equals(sms.send(telephone, from, message));

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "Error en sendMail: " + e.getMessage(), e);
			return false;
		}
	}
    

    /**
     * Obtiene las citas contiguas para intercalar citas
     * @author Lorena Lama
     * @param ctx
     * @param fechaSelec
     * @param medicoID
     * @param durMin
     * @param trxName
     * @return
     *
	public static List<MEXMECitaMedica> intercalar(Properties ctx, String fechaSelec, int medicoID, int durMin, String trxName) {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<MEXMECitaMedica> retValue = new ArrayList<MEXMECitaMedica>();
		
		try {

			StringBuilder sql_fechaFin = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			StringBuilder sql_fechaIni = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			
			sql_fechaIni.append("NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)");
			
			sql_fechaFin.append("CASE WHEN fechahrfin IS NULL ")
						.append("THEN (NVL(EXME_CitaMedica.fechahrini, EXME_CitaMedica.fechahrcita)) + (EXME_CitaMedica.duracion/1440) ")
						.append("ELSE fechahrfin END ");
			
			sql.append("SELECT EXME_CitaMedica.*, ")
				.append(sql_fechaFin).append(" AS fechaFinal, ")
				.append(sql_fechaIni).append(" AS fechaInicial ")
				
				.append(" FROM EXME_CitaMedica ")
				.append(" WHERE ").append(sql_fechaIni).append(" > "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " ")
				.append(" AND exme_medico_id = ? AND estatus not in ('")
				.append(MEXMECitaMedica.ESTATUS_Executed+"','")
				.append(MEXMECitaMedica.ESTATUS_Cancelled+"','")
				.append(MEXMECitaMedica.ESTATUS_Closed+"')")
				
				.append(" AND ( ( SELECT (").append(sql_fechaFin).append(")+(").append(durMin-1).append("/1440) as fechaFin ")
						.append(" FROM exme_citamedica ")
						.append(" WHERE ").append(sql_fechaIni).append(" > "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " ")
						.append(" AND exme_medico_id = ? ")
						.append(" AND estatus not in ('")
						.append(MEXMECitaMedica.ESTATUS_Executed+"','")
						.append(MEXMECitaMedica.ESTATUS_Cancelled+"','")
						.append(MEXMECitaMedica.ESTATUS_Closed+"')")
						.append(" AND to_Date(?,'DD/MM/YYYY  HH24:MI:SS') ")
						.append(" BETWEEN ").append(sql_fechaIni).append(" AND (").append(sql_fechaFin).append(") ")
						
				.append(" ) BETWEEN ").append(sql_fechaIni).append(" AND (").append(sql_fechaFin).append(") ")
				.append(" OR  to_Date(?,'DD/MM/YYYY  HH24:MI:SS') BETWEEN ").append(sql_fechaIni).append(" AND (").append(sql_fechaFin).append(") ) ")
				
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MEXMECitaMedica.Table_Name))
				.append(" ORDER BY fechaInicial ");
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, medicoID);
			psmt.setInt(2, medicoID);
			psmt.setString(3, fechaSelec);
			psmt.setString(4, fechaSelec);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				MEXMECitaMedica cita = new MEXMECitaMedica(ctx, rs, trxName);
				cita.setFechaFinal(rs.getTimestamp("fechaFinal"));
				cita.setFechaInicial(rs.getTimestamp("fechaInicial"));
				retValue.add(cita);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			try {
				if ( rs != null )
					rs.close();
				if ( psmt != null )
					psmt.close();
			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			}
		}		
		return retValue;
	}*/
	
	
	/**
	 * Metodo que indica si se mueve a una hora dentro del rango del horario del medico.
	 * @author Laura Hernández
	 * @param medicoID
	 * @param fechaIniCita
	 * @return disponible
	 *
	public static boolean horarioMedDisp(Properties ctx, int medicoID,Date fechaIniCita){
		
		boolean disponible = false;
		try{
			//Obtenemos los turnos del medico.
			HashMap<String, String> horarioMed = MEXMEMedico.getHorarioMap(ctx, medicoID);
			//Horario de entrada y salida del medico
			String ent1Es = horarioMed.get("HoraEnt1Es"); 
			String sal1Es = horarioMed.get("HoraSal1Es"); 

			String ent2Es = horarioMed.get("HoraEnt2Es"); 
			String sal2Es = horarioMed.get("HoraSal2Es");

			String ent3Es = horarioMed.get("HoraEnt3Es"); 
			String sal3Es = horarioMed.get("HoraSal3Es"); 

			//fin de semana.
			String ent1Fs = horarioMed.get("HoraEnt1Fs");
			String sal1Fs = horarioMed.get("HoraSal1Fs");
			
			String fecha = Constantes.getSdfFecha().format(fechaIniCita);

			// Verificamos si cae dentro de los horarios establecidos para el medico.
			// Utilizamos la fecha en la que se quiere reagendar el evento le concatenamos la hora de entrada y de salida 
			// y comparamos la fecha inicial y final del evento contra la hora de entrada y salida del medico.
			Calendar calIni = Calendar.getInstance();
			calIni.setTime(fechaIniCita);
			//Si es fin de semana y tiene configurado un horario
			if (calIni.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calIni.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				if(ent1Fs != null && sal1Fs != null){
					if((Constantes.getSdfFechaHora().parse(fecha + " " + ent1Fs)).getTime() <= fechaIniCita.getTime() && 
							(Constantes.getSdfFechaHora().parse(fecha + " " +sal1Fs)).getTime() >= fechaIniCita.getTime()){
						return true;						
					}
				}
				return disponible;
			}
			//Horario 1
			if(ent1Es != null && sal1Es != null){
				if((Constantes.getSdfFechaHora().parse(fecha + " " + ent1Es)).getTime() <= fechaIniCita.getTime() && 
						(Constantes.getSdfFechaHora().parse(fecha + " " +sal1Es)).getTime() >= fechaIniCita.getTime()){
					disponible = true;
				}
			}
			//Horario 2
			if(ent2Es != null && sal2Es != null){
				if((Constantes.getSdfFechaHora().parse(fecha + " " + ent2Es)).getTime() <= fechaIniCita.getTime() && 
						(Constantes.getSdfFechaHora().parse(fecha + " " +sal2Es)).getTime() >= fechaIniCita.getTime()){
					disponible = true;
				}
			}
			//Horario 3
			if(ent3Es != null && sal3Es != null){
				if((Constantes.getSdfFechaHora().parse(fecha + " " + ent3Es)).getTime() <= fechaIniCita.getTime() && 
						(Constantes.getSdfFechaHora().parse(fecha + " " +sal3Es)).getTime() >= fechaIniCita.getTime()){
					disponible = true;
				}
			}
		}catch(Exception e){
			
		}
		return disponible;
	}*/
	
	/**
	 * 
	 * @param ctx
	 * @param actividadID
	 * @param trxName
	 * @return
	 *
	public HashMap<String,Object> moveAgenda(Properties ctx, int actividadID, String trxName){
		
		ResultSet rs = null;
		MEXMEMedicoSust medicoSustituto = null;
		List<MoveModel> lstTareas = new ArrayList<MoveModel>(); // tareas del medico			
		List<MoveModel> lstError = new ArrayList<MoveModel>();  // tareas que no se pudieron mover
		List<MoveModel> lstMoved = new ArrayList<MoveModel>();  // tareas a cambiar de medico
		
		HashMap<String,Object> retValue = new HashMap<String,Object>();
		
		try {
			//Obtenemos la info del contexto
			//actividadID = getRecord_ID();
			if(actividadID<=0)
				throw new Exception(Msg.getMsg(ctx,"error.moveAgenda.activiyID"));
			
			//la actividad que ejecuta el proceso mover
			MEXMEActividad actividad = new MEXMEActividad(Env.getCtx(),actividadID,null);
			//rango de fechas que abarca la actividad
			Date fechaRangoInicial = actividad.getStartDate();
			Date fechaRangoFinal = actividad.getEndDate();
			Date fechaActual = DB.getDateForOrg(Env.getCtx());
			int medicoID= actividad.getEXME_Medico_ID();
			
			//revisamos que la actividad, inicie o termine despues del dia actual
			if (fechaRangoInicial.getTime() < fechaActual.getTime() && fechaRangoFinal.getTime() < fechaActual.getTime()) {
				throw new Exception(Msg.getMsg(ctx,"error.moveAgenda.dateBefore"));
			} 
			
			//buscamos el medico sustituto relacionado
			List<MEXMEMedicoSust> lista = MEXMEMedicoSust.get(ctx, medicoID, 0, null);
			
			medicoSustituto = !lista.isEmpty() ? lista.get(0) : null;
			//validamos los datos actuales del medico sustito guardado
			if(medicoSustituto== null || !medicoSustituto.validate(false))
				throw new Exception(Msg.getMsg(ctx,"error.moveAgenda.sustituto"));
			
			//Obtenemos el horario del medico sustituto
			HashMap<String, String> horarioMedico = MEXMEMedico.getHorarioMap(ctx, medicoSustituto.getSubstitute_ID());
			if (horarioMedico.isEmpty()) {
				throw new Exception(Msg.getMsg(ctx,"error.moveAgenda.schedule"));
			}
			
			//buscamos las tareas del medico para el rango de fechas correspondiente
			rs = AgendaMedicaModel.getDetail(ctx,medicoID,Constantes.getSdfFecha().format(fechaRangoInicial),Constantes.getSdfFecha().format(fechaRangoFinal)); //modifica SQL

			boolean error = false;
			//creamos las listas
			if(rs == null)
				throw new Exception(Msg.getMsg(ctx,"error.moveAgenda.noEvents"));
			
			while(rs.next()){
				//excluyendo la actividad guardada
				if(rs.getInt("llave")==actividadID)
					continue;
				MoveModel record = new MoveModel();
				record.setRecordID(rs.getInt("llave"));
				record.setRecordType(rs.getString("tipo"));
				record.setStartDate(rs.getTimestamp("fechaini"));
				record.setEndDate(rs.getTimestamp("fechafin"));
				
				if(record.getRecordType().equals("A"))
					error = true;  // las actividades no se mueven
				else if(record.getRecordType().equals("C")){
					//validamos que la actividad corresponda a la especialidad del medico sustituto
					if(!validEspecialidad(medicoSustituto.getSubstitute_ID(), rs.getInt("key"),trxName))
						error = true;
				} 
				if(error)
					lstError.add(record);
				else	
					lstTareas.add(record);
			}

			if(lstTareas.isEmpty()){
				if(lstError.isEmpty())
					throw new Exception(Msg.getMsg(ctx,"error.moveAgenda.noEvents"));
				else
					throw new Exception(Msg.getMsg(ctx,"error.moveAgenda.invalidEvents"));
			}
			fechaRangoInicial = lstTareas.get(0).getStartDate(); //primer fecha de las actividades
			fechaRangoFinal = lstTareas.get(lstTareas.size()-1).getEndDate(); //ultima fecha de las actividades
			
			//para cada dia contenido en el rango de fechas
			Calendar fechaRango = Calendar.getInstance();
			fechaRango.setTime(fechaRangoInicial); // setea el calendario con la fecha inicial.

			//solo para el rango de dias que abarca la actividad
			while (fechaRango.getTimeInMillis() <= fechaRangoFinal.getTime()) {
				// Obtenmos las horas disponibles del medico sustituto
				HashMap<String, List<String>> horasDesocupadas = Programador.getHorasdisponibles(ctx, horarioMedico, fechaRango.getTime(), 0);
				List<String> lstHorasIni = horasDesocupadas.get("Horaini"); //hora inicial de espacio libre
				List<String> lstHorasFin = horasDesocupadas.get("Horafin"); //hora final para cada espacio libre
				
				//si no hay horas disponibles para ese dia
				if(lstHorasIni.isEmpty()){
					// movemos las actividades de ese dia a la lista de error
					notMoved(lstError,fechaRango,lstTareas);
				} else {
					// iteramos entre los bloques de horas disponibles
					for (int i = 0; i < lstHorasIni.size(); i++) {
						//iteramos para cada actividad
						for (int j = 0; j < lstTareas.size(); j++) {							
							if(!Constantes.getSdfFecha().format(fechaRango.getTime()).equals(Constantes.getSdfFecha().format(lstTareas.get(j).getStartDate())))
								continue;
							//comparamos la hora de inicio de la actividad con el espacio disponible del medico, si coincide en dia y hora
							if (lstHorasIni.get(i).compareTo(Constantes.getSdfHora24().format(lstTareas.get(j).getStartDate())) <= 0
									&& lstHorasFin.get(i).compareTo(Constantes.getSdfHora24().format(lstTareas.get(j).getEndDate())) >= 0) {

								if(lstTareas.get(j).getRecordType().equals("C")){
									MEXMECitaMedica cita = new MEXMECitaMedica(ctx,lstTareas.get(j).getRecordID(),null);
									if(cita.getEXME_CitaMedica_ID()<=0)
										continue;
									cita.setEXME_Medico_ID(medicoSustituto.getSubstitute_ID());
									if(!cita.save(trxName)){
										remove(lstTareas,lstError,j);
										continue;
									}
								} else {
									MProgQuiro progQuiro = new MProgQuiro(ctx,lstTareas.get(j).getRecordID(),null);
									if(progQuiro.getEXME_ProgQuiro_ID()<=0)
										continue;
									progQuiro.setEXME_Medico_ID(medicoSustituto.getSubstitute_ID());
									if(!progQuiro.save(trxName)){
										remove(lstTareas,lstError,j);
										continue;
									}
								}
								// asignamos el nuevo medico al evento, y cambiamos la lista de horas disponibles
								lstHorasIni.set(i,Constantes.getSdfHora24().format(lstTareas.get(j).getEndDate()));
								add(lstTareas,lstMoved,j);
								--j;
							} else {
								//crea un mensaje de error, indicando las actividaddes que no se pudieron mover
								remove(lstTareas,lstError,j);
								--j;
							}
						}
					}
				}
				// le suma un dia a la fecha del rango a evaluar
				fechaRango.add(Calendar.DATE, 1);
			}
		} catch (Exception e) {
			//log.log(Level.SEVERE, "Processing error ", e);
			//throw new Exception ("doIt", e);
			retValue.put("@Errors@ - ",e.getMessage());
		} finally {
			try {
				if(rs!=null)
					rs.close();
			} catch (Exception e) {
				rs = null;
			}
			
		}
		
		retValue.put("@Substitute_ID@ - ",medicoSustituto.getMedico(false).getNombre_Med());
		//barremos las actividades no actualizadas
		for (int i = 0; i < lstError.size(); i++) {
			retValue.put("@Errors@ - ",Constantes.getSdfFechaHora().format(lstError.get(i).getStartDate()));
		}
		
		for (int i = 0; i < lstMoved.size(); i++) {
			retValue.put("@Updated@ - ",Constantes.getSdfFechaHora().format(lstMoved.get(i).getStartDate()));
		}
	
		return retValue;
		
	}*/
	
	/**
	 * 
	 * @param lstError
	 * @param fechaRango
	 * @param lstTareas
	 * @throws Exception
	 *
	private void notMoved(List<MoveModel> lstError, Calendar fechaRango, List<MoveModel> lstTareas) throws Exception {
		try {
			// iteramos para cada actividad
			for (int j = 0; j < lstTareas.size(); j++) {
				// valida solo para los registros del mismo dia
				Timestamp fecha = 
					new Timestamp(lstTareas.get(j).getStartDate().getTime());
				
				if (!Constantes.getSdfFecha().format(fechaRango.getTime()).equals(
						Constantes.getSdfFecha().format(fecha)))
					continue;
				remove(lstTareas,lstError,j);
				--j;
			}
		} catch (Exception e) {
			//log.log(Level.SEVERE, "", e);
			throw e;
		}
	}
	
	/**
	 * 
	 * @param lstTareas
	 * @param lstError
	 * @param j
	 *
	private void remove(List<MoveModel> lstTareas,List<MoveModel> lstError,int j) {
		lstError.add(lstTareas.get(j));
		lstTareas.remove(j); //las quitamos de la lista
	}
	
	/**
	 * 
	 * @param lstTareas
	 * @param lstMoved
	 * @param j
	 *
	private void add(List<MoveModel> lstTareas,List<MoveModel> lstMoved,int j) {
		lstMoved.add(lstTareas.get(j));
		lstTareas.remove(j); //las quitamos de la lista
	}
	
	/**
	 * 
	 * @param substituteID
	 * @param especialidadID
	 * @param trxName
	 * @return
	 *
	public static boolean validEspecialidad(int substituteID, int especialidadID, String trxName) {
		StringBuilder sql = new StringBuilder("SELECT EXME_MedicoEsp_ID FROM ")
		.append(" EXME_MedicoEsp WHERE EXME_Medico_ID = ? AND EXME_Especialidad_ID = ? ");		
		int count = DB.getSQLValue(trxName,sql.toString(),substituteID,especialidadID);		
		if(count<=0){
			return false;
		}
		return true;
	}*/


	/************** Agenda Quirofanos ********************/
	
	/**
	 * Obtiene todas las tareas relacionadas a un medico determinado
	 * Busca la informacion relacionada a la estacion o quirofano
	 * Programacion de Quirofanos: 
	 * 1- Texto: Programacion de Quirofano y Numero de Programacion
	 * 2- Color: color de estatus
	 * 3- Tooltip: Historia (si tiene), Nombre de Paciente, cuenta paciente
	 * @param ctx
	 * @param medicoID
	 * @param fechaIni
	 * @param fechaFin
	 * @param excludeCancel
	 * @param trxName
	 * @return
	 *
	public static List<AgendaMedicaView> getData(Properties ctx, int estServID, int quirofanoID, String fechaIni, String fechaFin) {
		List<AgendaMedicaView> relList = new ArrayList<AgendaMedicaView>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			sql.append(sqlProgQuiro(ctx, 0, estServID, quirofanoID, true));
			sql.append(" ORDER BY cuatro, fechaIni ");

			psmt = DB.prepareStatement(sql.toString(), null);

			int j = 0;// 3
			psmt.setInt(++j, estServID > 0 ? estServID : quirofanoID);
			psmt.setString(++j, fechaIni);
			psmt.setString(++j, fechaIni);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				AgendaMedicaView agenda = new AgendaMedicaView();
				
				agenda.setRecordID(rs.getInt("llave"));
				agenda.setRecordType(rs.getString("tipo"));
				agenda.setRecordInfo(rs.getString("value"));
				//agenda.setRecordColor(defaultColor);
				agenda.setRecordEstatus(rs.getString("estatus"));
				//ToolTip.-Info
				agenda.setToolTip1(rs.getString("uno"));
				agenda.setToolTip2(rs.getString("dos"));
				agenda.setToolTip3(rs.getString("tres"));
				agenda.setRecordRefID(rs.getInt("cuatro"));
				agenda.setStartDate(rs.getTimestamp("fechaini"));
				agenda.setEndDate(rs.getTimestamp("fechafin"));
				agenda.setRecordDays(rs.getInt("dias"));
				agenda.setRepeated(rs.getString("repeated"));
				relList.add(agenda);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.toString());
			if(WebEnv.DEBUG)
				e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			}
		}

		return relList;
	}
	
	/*
     * SQL para conteo de actividades/eventos programados en la misma fecha/hora
     * @param tableName - Nombre de la tabla
     * @author lorena
     * @return
     *
	private static String sqlRepeated(String tableName) {

		String sql = StringUtils.EMPTY;
		if(tableName.equals(MEXMECitaMedica.Table_Name)){
			sql = sqlRepeatedCitas();
		} else if(tableName.equals(MProgQuiro.Table_Name)){
			sql = sqlRepeatedProgQuiro();
		} else if (tableName.equals(MEXMEActividad.Table_Name)) {
			sql = sqlRepeatedActividad();
		}

		return sql;
	}*/

	/**
	 * SQL para conteo de Programaciones que empiezan en la misma hora
	 * 
	 * @return
	 */
	private static String sqlRepeatedProgQuiro() {		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("(SELECT COUNT(*) as repetidas FROM EXME_ProgQuiro prog WHERE ");
		sql.append(" CASE WHEN prog.docstatus = ");
		sql.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_Closed));
		sql.append(" THEN prog.fechainicio else prog.fechaProg END = ");
		sql.append(" CASE WHEN EXME_ProgQuiro.docstatus = ");
		sql.append(DB.TO_STRING(MProgQuiro.DOCSTATUS_Closed));
		sql.append(" THEN EXME_ProgQuiro.fechainicio else EXME_ProgQuiro.fechaProg END ");
		sql.append(" ) AS repeated ");
		return sql.toString();
	}

	/**
	 * SQL para conteo de Actividades que empiezan en la misma hora
	 * 
	 * @return
	 */
	private static String sqlRepeatedActividad() {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("(SELECT COUNT(*) as repetidas FROM EXME_Actividad act WHERE ");
		sql.append("act.StartDate = EXME_Actividad.StartDate ");
		sql.append(") AS repeated ");
		return sql.toString();
	}
	/**
	 * SQL para conteo de Citas que empiezan en la misma hora
	 * 
	 * @return
	 */
	private static String sqlRepeatedCitas() {
		StringBuilder sql = new StringBuilder();
		sql.append("(SELECT COUNT(*) as repetidas FROM EXME_CitaMedica cita WHERE  ");
		sql.append(" NVL(cita.FechahrIni,cita.FechahrCita) = NVL(EXME_CitaMedica.FechahrIni,EXME_CitaMedica.FechahrCita))as repeated ");
		return sql.toString();
	}
//	public class MoveModel {
//		
//		private int recordID = 0;
//		private String recordType = null;
//		private Date startDate = null;
//		private Date endDate = null;
//		
//		public Date getEndDate() {
//			return endDate;
//		}
//		public void setEndDate(Date endDate) {
//			this.endDate = endDate;
//		}
//		public int getRecordID() {
//			return recordID;
//		}
//		public void setRecordID(int recordID) {
//			this.recordID = recordID;
//		}
//		public String getRecordType() {
//			return recordType;
//		}
//		public void setRecordType(String recordType) {
//			this.recordType = recordType;
//		}
//		public Date getStartDate() {
//			return startDate;
//		}
//		public void setStartDate(Date startDate) {
//			this.startDate = startDate;
//		}
//	}
	
	/**
	 * Metodo que indica si se mueve a una hora dentro del rango del horario del medico.
	 * @author Laura Hernández
	 * @param medicoID
	 * @param fechaIniCita
	 * @return disponible
	 *
	public static boolean horarioMedDisp(int medicoID,Date fechaIniCita){
		
		boolean disponible = false;
		try{
			//Obtenemos los turnos del medico.
			HashMap<String, String> horarioMed = MEXMEMedico.getHorarioMap(medicoID);
			//Horario de entrada y salida del medico
			String ent1Es = horarioMed.get("HoraEnt1Es"); 
			String sal1Es = horarioMed.get("HoraSal1Es"); 

			String ent2Es = horarioMed.get("HoraEnt2Es"); 
			String sal2Es = horarioMed.get("HoraSal2Es");

			String ent3Es = horarioMed.get("HoraEnt3Es"); 
			String sal3Es = horarioMed.get("HoraSal3Es"); 

			//fin de semana.
			String ent1Fs = horarioMed.get("HoraEnt1Fs");
			String sal1Fs = horarioMed.get("HoraSal1Fs");
			
			String fecha = Constantes.getSdfFecha().format(fechaIniCita);

			// Verificamos si cae dentro de los horarios establecidos para el medico.
			// Utilizamos la fecha en la que se quiere reagendar el evento le concatenamos la hora de entrada y de salida 
			// y comparamos la fecha inicial y final del evento contra la hora de entrada y salida del medico.
			Calendar calIni = Calendar.getInstance();
			calIni.setTime(fechaIniCita);
			//Si es fin de semana y tiene configurado un horario
			if (calIni.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calIni.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				if(ent1Fs != null && sal1Fs != null){
					if((Constantes.getSdfFechaHora().parse(fecha + " " + ent1Fs)).getTime() <= fechaIniCita.getTime() && 
							(Constantes.getSdfFechaHora().parse(fecha + " " +sal1Fs)).getTime() >= fechaIniCita.getTime()){
						return true;						
					}
				}
				return disponible;
			}
			//Horario 1
			if(ent1Es != null && sal1Es != null){
				if((Constantes.getSdfFechaHora().parse(fecha + " " + ent1Es)).getTime() <= fechaIniCita.getTime() && 
						(Constantes.getSdfFechaHora().parse(fecha + " " +sal1Es)).getTime() >= fechaIniCita.getTime()){
					disponible = true;
				}
			}
			//Horario 2
			if(ent2Es != null && sal2Es != null){
				if((Constantes.getSdfFechaHora().parse(fecha + " " + ent2Es)).getTime() <= fechaIniCita.getTime() && 
						(Constantes.getSdfFechaHora().parse(fecha + " " +sal2Es)).getTime() >= fechaIniCita.getTime()){
					disponible = true;
				}
			}
			//Horario 3
			if(ent3Es != null && sal3Es != null){
				if((Constantes.getSdfFechaHora().parse(fecha + " " + ent3Es)).getTime() <= fechaIniCita.getTime() && 
						(Constantes.getSdfFechaHora().parse(fecha + " " +sal3Es)).getTime() >= fechaIniCita.getTime()){
					disponible = true;
				}
			}
		}catch(Exception e){
			
		}
		return disponible;
	}*/
}
