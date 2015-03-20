package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.mo.MO_EjecutarCita_VO;


public class MEXMECitaMedica_MO /* extends MEXMECitaMedica */{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static CLogger s_log = CLogger.getCLogger(MEXMECitaMedica.class);

//    public static SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
//    public static SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

//	  public MEXMECitaMedica_MO(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
//	        super(ctx, EXME_CitaMedica_ID, trxName);
//	    }

//	  public MEXMECitaMedica_MO(Properties ctx, ResultSet rs, String trxName) {
//	        super(ctx, rs, trxName);
//	    }


	  /**
		 * Obtiene citas de un medico para determinada fehca y especialidad. 
		 *
		 * @param Integer medico
		 * @param Date FechaCita
		 * @param Integer Especialidad
		 * @return ArrayList citas programadas
		 * @throws Exception
		 *
	    public static ArrayList<MO_Cita_VO> getCitasMedicoMO(Integer medico, Date fecha, Integer esp) {

	    	ArrayList<MO_Cita_VO> citas = new ArrayList<MO_Cita_VO>();

			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			 sql.append("	select  exme_citamedica.exme_paciente_id,exme_paciente.value,")
				.append(" 	exme_paciente.name,exme_paciente.nombre2,exme_paciente.apellido1,exme_paciente.apellido2,")
				.append(" 	exme_paciente.c_location_id,exme_paciente.telparticular,exme_paciente.email, ")
				.append("	exme_citamedica.exme_medico_id,exme_medico.name, exme_medico.apellido1, exme_medico.apellido2, exme_medico.exme_turnos_id,")
				.append("	exme_medico.celular,exme_medico.email,	")
				.append("	exme_citamedica.exme_especialidad_id ,exme_especialidad.name, exme_especialidad.description, exme_especialidad.value,exme_especialidad.pricelist,") 
				.append("	exme_citamedica.exme_asistente_id,exme_asistente.name, exme_asistente.description, exme_asistente.value,")
				.append("	exme_citamedica.exme_citamedica_id,exme_citamedica.name, exme_citamedica.description,exme_citamedica.created,") 
				.append("	exme_citamedica.fechahrcita, exme_citamedica.confirmada, exme_citamedica.estatus,exme_citamedica.duracion,exme_citamedica.observaciones,")
				.append("	exme_citamedica.fechahrini,exme_citamedica.fechahrfin   ")
				.append("	from exme_citamedica")
				.append("	inner join exme_paciente on (exme_citamedica.exme_paciente_id = exme_paciente.exme_paciente_id)")
				.append("	inner join exme_medico on (exme_citamedica.exme_medico_id = exme_medico.exme_medico_id)")
				.append("	inner join exme_especialidad on (exme_citamedica.exme_especialidad_id = exme_especialidad.exme_especialidad_id)")
				.append("	inner join exme_asistente on (exme_citamedica.exme_asistente_id = exme_asistente.exme_asistente_id)")
				.append("	where exme_citamedica.exme_especialidad_id=?")
				.append("	and exme_citamedica.isactive = 'Y'")
				.append("	and exme_citamedica.estatus <> 8")
				.append("	and exme_citamedica.exme_medico_id = ?")
				.append("	AND TO_CHAR(EXME_CitaMedica.FechaHrCita, 'DD/MM/YYYY') = ?")
				.append("	order by exme_citamedica.fechahrcita");;

	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;

	    	MO_Cita_VO cita = null;
	    	MO_Asistente_VO asistente  = null;
	    	MO_Especialidad_VO especialidad = null;
	    	MO_Medico_VO med = null;
	    	MO_Paciente_VO paciente  = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, esp.intValue());
				pstmt.setInt(2, medico.intValue());
				pstmt.setDate(3, fecha);

				rs = pstmt.executeQuery();

				while (rs.next()) { //guardo los valores en las listas

					cita = new MO_Cita_VO();
			    	asistente  = new MO_Asistente_VO();
			    	especialidad = new MO_Especialidad_VO();
			    	med = new MO_Medico_VO();
			    	paciente  = new MO_Paciente_VO();

			    	paciente.setId(rs.getInt(1));
			    	paciente.setHistoria(rs.getString(2));
			    	paciente.setNombre(rs.getString(3));
			    	paciente.setNombre2(rs.getString(4));
			    	paciente.setApellido1(rs.getString(5));
			    	paciente.setApellido2(rs.getString(6));
			    	//paciente.setDireccion(rs.getString("exme_paciente.c_location_id"));////7
			    	paciente.setTelefono(rs.getString(8));
			    	paciente.setEmail(rs.getString(9));

			    	med.setId(rs.getInt(10));
			    	med.setNombre(rs.getString(11));
			    	med.setApellido1(rs.getString(12));
			    	med.setApellido2(rs.getString(13));
			    	//med.set(rs.getString("exme_medico.exme_turnos_id"));///14
			    	med.setCelular(rs.getString(15));
			    	med.setEmail(rs.getString(16));

			    	especialidad.setId(rs.getInt(17));
			    	especialidad.setNombre(rs.getString(18));
			    	especialidad.setDescripcion(rs.getString(19));
			    	especialidad.setValor(rs.getString(20));
			    	especialidad.setListaprecios(rs.getInt(21));

			    	asistente.setId(rs.getInt(22));
			    	asistente.setNombre(rs.getString(23));
			    	//asistente.se(rs.getString("exme_asistente.description"));///24 
			    	//asistente.s(rs.getString("exme_asistente.value"));///25

			    	cita.setId(rs.getInt(26));
			    	cita.setNombre(rs.getString(27));
			    	cita.setDescripcion(rs.getString(28));
			    	cita.setFechacreada(rs.getTimestamp(29));
			    	cita.setFechacita(rs.getTimestamp(30));
//			    	cita.setEstatusText(rs.getString(31));
			    	cita.setEstatus(rs.getInt(32));
			    	cita.setDuracion(rs.getInt(33));
			    	cita.setObservaciones(rs.getString(34));
			    	cita.setFechahrinicio(rs.getTimestamp(35));
			    	cita.setFechahrfin(rs.getTimestamp(36));


			    	med.setEspecialidad(especialidad);
			    	cita.setMedico(med);
			    	cita.setAsistente(asistente);
			    	cita.setPaciente(paciente);

			    	citas.add(cita);

				}
				

			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
			} finally {
				DB.close(rs, pstmt);
			}
			return citas;
	    }*/


	    /**
		 * Obtiene las citas programadas danto un intervalo de fechas, un medico y una especialidad. 
		 *
		 * @param Integer medico
		 * @param Date FechaInicio
		 * @param Date FechaFin
		 * @param Integer Especialidad
		 * @return ArrayList citas programadas
		 * @throws Exception
		 */
    public static ArrayList<MEXMECitaMedica> getCitasMedicoMO(Integer medico, java.util.Date fecha1, java.util.Date fecha2, Integer esp) {

	    	ArrayList<MEXMECitaMedica> citas = new ArrayList<MEXMECitaMedica>();

			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			 sql.append("	select  exme_citamedica.* ")
//			 		",exme_paciente.value,")
//				.append(" 	exme_paciente.name,exme_paciente.nombre2,exme_paciente.apellido1,exme_paciente.apellido2,")
//				.append(" 	exme_paciente.c_location_id,exme_paciente.telparticular,exme_paciente.email, ")
//				.append("	exme_citamedica.exme_medico_id,exme_medico.name, exme_medico.apellido1, exme_medico.apellido2, exme_medico.exme_turnos_id,")
//				.append("	exme_medico.celular,exme_medico.email,	exme_citamedica.exme_asistente_id,exme_citamedica.exme_especialidad_id ,")
////				.append("	exme_citamedica.exme_especialidad_id ,exme_especialidad.name, exme_especialidad.description, exme_especialidad.value,exme_especialidad.pricelist,") 
////				.append("	exme_citamedica.exme_asistente_id,exme_asistente.name, exme_asistente.description, exme_asistente.value,")
//				.append("	exme_citamedica.exme_citamedica_id,exme_citamedica.name, exme_citamedica.description,exme_citamedica.created,") 
//				.append("	exme_citamedica.fechahrcita, exme_citamedica.confirmada, exme_citamedica.estatus,exme_citamedica.duracion,exme_citamedica.observaciones,")
//				.append("	exme_citamedica.fechahrini,exme_citamedica.fechahrfin   ")
				.append("	from exme_citamedica")
				.append("	inner join exme_paciente on (exme_citamedica.exme_paciente_id = exme_paciente.exme_paciente_id)")
				.append("	inner join exme_medico on (exme_citamedica.exme_medico_id = exme_medico.exme_medico_id)")
//				.append("	inner join exme_especialidad on (exme_citamedica.exme_especialidad_id = exme_especialidad.exme_especialidad_id)")
//				.append("	inner join exme_asistente on (exme_citamedica.exme_asistente_id = exme_asistente.exme_asistente_id)")
				.append("	where exme_citamedica.exme_especialidad_id=?")
				.append("	and exme_citamedica.isactive = 'Y'")
				.append("	and exme_citamedica.estatus <> 8")
				.append("	and exme_citamedica.exme_medico_id = ?")
				.append("	AND EXME_CitaMedica.FechaHrCita > TO_DATE( ? , 'DD-MM-YYYY HH24:MI:SS')")
				.append("	AND EXME_CitaMedica.FechaHrCita < TO_DATE( ? , 'DD-MM-YYYY HH24:MI:SS')")
				.append("	order by exme_citamedica.fechahrcita");


	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;

//	    	MO_Cita_VO cita = null;
//	    	MO_Asistente_VO asistente  = null;
//	    	MO_Especialidad_VO especialidad = null;
//	    	MO_Medico_VO med = null;
//	    	MO_Paciente_VO paciente  = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, esp.intValue());
				pstmt.setInt(2, medico.intValue());
				pstmt.setString(3, Constantes.getSdfFecha().format(fecha1)+" 00:00:01");
				pstmt.setString(4, Constantes.getSdfFecha().format(fecha2)+" 23:59:59");

				rs = pstmt.executeQuery();

				while (rs.next()) { //guardo los valores en las listas

					MEXMECitaMedica cita = new MEXMECitaMedica(Env.getCtx(), rs, null);
//			    	asistente  = new MO_Asistente_VO();
//			    	especialidad = new MO_Especialidad_VO();
//			    	med = new MO_Medico_VO();
//			    	paciente  = new MO_Paciente_VO();
//
//			    	paciente.setId(rs.getInt("exme_paciente_id"));
//			    	paciente.setHistoria(rs.getString(2));
//			    	paciente.setNombre(rs.getString(3));
//			    	paciente.setNombre2(rs.getString("nombre2"));
//			    	paciente.setApellido1(rs.getString(5));
//			    	paciente.setApellido2(rs.getString(6));
//			    	//paciente.setDireccion(rs.getString("exme_paciente.c_location_id"));////7
//			    	paciente.setTelefono(rs.getString(8));
//			    	paciente.setEmail(rs.getString(9));
//
//			    	med.setId(rs.getInt("exme_medico_id"));
//			    	med.setNombre(rs.getString(11));
//			    	med.setApellido1(rs.getString(12));
//			    	med.setApellido2(rs.getString(13));
//			    	//med.set(rs.getString("exme_medico.exme_turnos_id"));///14
//			    	med.setCelular(rs.getString(15));
//			    	med.setEmail(rs.getString(16));

//			    	especialidad.setId(rs.getInt("exme_especialidad_id"));
//			    	especialidad.setNombre(rs.getString(18));
//			    	especialidad.setDescripcion(rs.getString(19)); 
//			    	especialidad.setValor(rs.getString(20));
//			    	especialidad.setListaprecios(rs.getInt("pricelist"));

//			    	asistente.setId(rs.getInt("exme_asistente_id"));
//			    	asistente.setNombre(rs.getString(23));
			    	//asistente.se(rs.getString("exme_asistente.description"));///24 
			    	//asistente.s(rs.getString("exme_asistente.value"));///25

//			    	cita.setId(rs.getInt("exme_citamedica_id"));
//			    	cita.setNombre(rs.getString(27));
//			    	cita.setDescripcion(rs.getString(28));
//			    	cita.setFechacreada(rs.getTimestamp("created"));
//			    	cita.setFechacita(rs.getTimestamp("fechahrcita")); 
////			    	cita.setEstatusText(rs.getString("confirmada"));
//			    	cita.setEst(rs.getString("estatus"));
//			    	cita.setDuracion(rs.getInt("duracion"));
//			    	cita.setObservaciones(rs.getString("observaciones"));
//			    	cita.setFechahrinicio(rs.getTimestamp("fechahrini"));
//			    	cita.setFechahrfin(rs.getTimestamp("fechahrfin"));   


//			    	med.setEspecialidadId(rs.getInt("EXME_Especialidad_ID"));
//			    	cita.setMedico(med);
//			    	cita.setAsistenteId(rs.getInt("EXME_Asistente_ID"));
//			    	cita.setPaciente(paciente);

			    	citas.add(cita);

				}
				

			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			} finally {
				DB.close(rs, pstmt);
			}
			return citas;
	    }


	    /**
		 * Obtener el detalle de una cita en particular
		 *
		 * @param Integer citaID
		 * @return MO_Cita_VO cita
		 * @throws Exception
		 *
	    public static MO_Cita_VO getCitaMO(Integer citaID) {

			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			sql.append("	select  exme_citamedica.exme_paciente_id,exme_paciente.value,")
			.append(" 	exme_paciente.name,exme_paciente.nombre2,exme_paciente.apellido1,exme_paciente.apellido2,")
			.append(" 	exme_paciente.c_location_id,exme_paciente.telparticular,exme_paciente.email, ")
			.append("	exme_citamedica.exme_medico_id,exme_medico.name, exme_medico.apellido1, exme_medico.apellido2, exme_medico.exme_turnos_id,")
			.append("	exme_medico.celular,exme_medico.email,exme_citamedica.exme_asistente_id,exme_citamedica.exme_especialidad_id ,	")
//			.append("	exme_citamedica.exme_especialidad_id ,exme_especialidad.name, exme_especialidad.description, exme_especialidad.value,exme_especialidad.pricelist,") 
//			.append("	exme_citamedica.exme_asistente_id,exme_asistente.name, exme_asistente.description, exme_asistente.value,")
			.append("	exme_citamedica.exme_citamedica_id,exme_citamedica.name, exme_citamedica.description,exme_citamedica.created,") 
			.append("	exme_citamedica.fechahrcita, exme_citamedica.confirmada, exme_citamedica.estatus,exme_citamedica.duracion,exme_citamedica.observaciones,")
			.append("	exme_citamedica.fechahrini,exme_citamedica.fechahrfin,exme_citamedica.c_invoice_id   ")
			.append("	from exme_citamedica")
			.append("	inner join exme_paciente on (exme_citamedica.exme_paciente_id = exme_paciente.exme_paciente_id)")
			.append("	inner join exme_medico on (exme_citamedica.exme_medico_id = exme_medico.exme_medico_id)")
//			.append("	inner join exme_especialidad on (exme_citamedica.exme_especialidad_id = exme_especialidad.exme_especialidad_id)")
//			.append("	inner join exme_asistente on (exme_citamedica.exme_asistente_id = exme_asistente.exme_asistente_id)")
			.append("	where exme_citamedica.exme_citamedica_id = ? ");

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			MO_Cita_VO cita = null;
//			MO_Asistente_VO asistente  = null;
//			MO_Especialidad_VO especialidad = null;
			MO_Medico_VO med = null;
			MO_Paciente_VO paciente  = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, citaID.intValue());

				rs = pstmt.executeQuery();

				while (rs.next()) { //guardo los valores en las listas

					cita = new MO_Cita_VO();
//			    	asistente  = new MO_Asistente_VO();
//			    	especialidad = new MO_Especialidad_VO();
			    	med = new MO_Medico_VO();
			    	paciente  = new MO_Paciente_VO();

			    	paciente.setId(rs.getInt(1));
			    	paciente.setHistoria(rs.getString(2));
			    	paciente.setNombre(rs.getString(3));
			    	paciente.setNombre2(rs.getString(4));
			    	paciente.setApellido1(rs.getString(5));
			    	paciente.setApellido2(rs.getString(6));
			    	//paciente.setDireccion(rs.getString("exme_paciente.c_location_id"));////7
			    	paciente.setTelefono(rs.getString(8));
			    	paciente.setEmail(rs.getString(9));

			    	med.setId(rs.getInt(10));
			    	med.setNombre(rs.getString(11));
			    	med.setApellido1(rs.getString(12));
			    	med.setApellido2(rs.getString(13));
			    	//med.set(rs.getString("exme_medico.exme_turnos_id"));///14
			    	med.setCelular(rs.getString(15));
			    	med.setEmail(rs.getString(16));

//			    	especialidad.setId(rs.getInt(17));
//			    	especialidad.setNombre(rs.getString(18));
//			    	especialidad.setDescripcion(rs.getString(19)); 
//			    	especialidad.setValor(rs.getString(20));
//			    	especialidad.setListaprecios(rs.getInt(21));

//			    	asistente.setId(rs.getInt(22));
//			    	asistente.setNombre(rs.getString(23));
			    	//asistente.se(rs.getString("exme_asistente.description"));///24 
			    	//asistente.s(rs.getString("exme_asistente.value"));///25

			    	cita.setId(rs.getInt(26));
			    	cita.setNombre(rs.getString(27));
			    	cita.setDescripcion(rs.getString(28));
			    	cita.setFechacreada(rs.getTimestamp(29));
			    	cita.setFechacita(rs.getTimestamp(30)); 
//			    	cita.setEstatusText(rs.getString(31));
			    	cita.setEstatus(rs.getInt(32));
			    	cita.setDuracion(rs.getInt(33));
			    	cita.setObservaciones(rs.getString(34));
			    	cita.setFechahrinicio(rs.getTimestamp(35));
			    	cita.setFechahrfin(rs.getTimestamp(36));   
			    	cita.setInvoice(rs.getInt(37));

			    	med.setEspecialidadId(rs.getInt("EXME_Especialidad_ID"));
			    	cita.setMedico(med);
			    	cita.setAsistenteId(rs.getInt("EXME_Asistente_ID"));
			    	cita.setPaciente(paciente);

				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
			}finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			return cita;
}
*/

	/**
	 * Regresa el Id de la especialidad de Odontologia
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static int getEspecialidadforOdonto(Properties ctx, String trxName) {
		MEXMEConfigOdonto m_configOdonto = MEXMEConfigOdonto.get(ctx, trxName);
		if (m_configOdonto == null)
			return 0;
		return m_configOdonto.getEXME_Especialidad_ID();
	}

	/**
	 * Liz de la Garza
	 * Metodo que valida la hora escogida con el turno del m�dico y que no este dentro del rango de horario de otra cita
	 * @param errores
	 * @param fecha (fecha de la cita)
	 * @param medicoId(el ID del m�dico)
	 * @param medAsistId(el ID del Asistente del m�dico)
	 * @param horaCitaIni(hora inicial de la cita)
	 * @param horaCitaFin(hora final de la cita)
	 * @param minCitaIni(minutos iniciales de la cita)
	 * @param minCitaIni(minutos finales de la cita)
	 * @return errores(errores al validar la hora de la cita seleccionada)
	 *

	public static ActionErrors validarHora(Properties ctx,ActionErrors errores, String fecha, int medicoId,int medAsistId, int horaCitaIni, int horaCitaFin
			, int minCitaIni, int minCitaFin) {

        try {
            //dia de la semana
        	Calendar cal = Calendar.getInstance();
             cal.setTime(Constantes.getSdfFecha().parse(fecha));

            int dia = cal.get(Calendar.DAY_OF_WEEK);

            //Obtenemos el horario de los turnos del m�dico
            HashMap<String, String>  rs= MEXMEMedico.getHorario(ctx, medicoId);
            boolean isOtroTurno = false;
            boolean isOtraCita = false;
            if (rs.size() > 0) {

                //horario de fin de semana
                String hora1a = rs.get("HoraEnt1Fs");
                String hora2a = rs.get("HoraSal1Fs");

                //primer horario entre semana
                String hora1b = rs.get("HoraEnt1Es");
                String hora2b = rs.get("HoraSal1Es");

                //segundo horario entre semana
                String hora1c = rs.get("HoraEnt2Es");
                String hora2c = rs.get("HoraSal2Es");

                int inicSelec = getHora(null, horaCitaIni, minCitaIni);
                int finSelec = getHora(null, horaCitaFin, minCitaFin);

                //Si el dia de la cita coincide con un turno de fin de semana
                if ((dia == Calendar.SUNDAY || dia == Calendar.SATURDAY) && hora1a != null && hora2a != null) {
                	//Validamos que la cita este dentro del horario del turno del m�dico
                	isOtroTurno = validaTurnoCita(hora1a, hora2a, inicSelec, finSelec);
                	if (isOtroTurno) { //La cita no esta dentro del turno del m�dico
                		errores.add("SalRangoTurno", new ActionError("agendar.odontologia.turnoMedicoCita"));
                	}
                } else {	//Tiene dos turnos
                		if(hora1b != null && hora2b != null && hora1c != null && hora2c != null){
                			//Validamos que la cita este dentro del horario del turno del m�dico
                			isOtroTurno = validaTurnoCita(hora1b, hora2b, inicSelec, finSelec);
                			if (isOtroTurno){
                				//Validamos que la cita este dentro del horario del turno del m�dico
                				isOtroTurno = validaTurnoCita(hora1c, hora2c, inicSelec, finSelec);
                				if (isOtroTurno) { //La cita no esta dentro de los turnos del m�dico
                					errores.add("SalRangoTurno", new ActionError("agendar.odontologia.turnoMedicoCita"));
                				}
                			}
                		} else {
	                		//tiene el 1er horario
	                		if (hora1b != null && hora2b != null)//Validamos que la cita este dentro del horario del turno del m�dico
	                			isOtroTurno = validaTurnoCita(hora1b, hora2b, inicSelec, finSelec);
	                		else {
	                		//tiene el 2do horario
	                			if (hora1c != null && hora2c != null) {
	                				//Validamos que la cita este dentro del horario del turno del m�dico
	                				isOtroTurno = validaTurnoCita(hora1c, hora2c, inicSelec, finSelec);	
	                			} else {//El m�dico no tiene turnos asignados
	                				errores.add("noTurnosMedico", new ActionError("agendar.odontologia.noTurnosMedico"));
	                			}
	                		}
	                		if (isOtroTurno) {//La cita no esta dentro de los turnos del m�dico
            					errores.add("SalRangoTurno", new ActionError("agendar.odontologia.turnoMedicoCita"));
            				}
                		}
                } //fin else sin hoarario de fin
                if (errores.isEmpty()) { //Validamos que la hora de la cita no coincida con otra cita ya agendada
                	isOtraCita = validaCitasPrevias(ctx, medicoId, fecha, medAsistId, horaCitaIni, horaCitaFin, minCitaIni, minCitaFin);
                	if (isOtraCita) { //El horario no es valido
                		errores.add("SalRangoCitas", new ActionError("agendar.odontologia.horarioOcupado"));
                	}
                }

            }
            
        } catch (Exception e) {
            s_log.log(Level.SEVERE, "getHorasConsulta", e);
            
            errores.add("Exception", new ActionError(e.getMessage()));
        }
        return errores;
    } */

	/**
	 * Liz de la Garza
	 * Metodo que valida la hora escogida de la cita con el turno del m�dico 
	 * @param strHoraIni(horario Inicial del turno del m�dico)
	 * @param strHoraFin(horario Final del turno del m�dico)
	 * @param inicSelec(el horario inicial de la cita seleccionada)
	 * @param finSelec(el horario final de la cita seleccionada)
	 * @return isOtroTurno(Si la cita esta dentro del turno o no)
	 *
	public static boolean validaTurnoCita(String strHoraIni ,String  strHoraFin, int inicSelec, int finSelec) { 

        boolean isOtroTurno = false;

            int inicCitas = getHora(strHoraIni, 0, 0);
            int finCitas = getHora(strHoraFin, 0, 0);

			if (!(inicSelec >= inicCitas && inicSelec < finCitas && finSelec > inicCitas && finSelec <= finCitas)) {
            	isOtroTurno = true;
            }

				return isOtroTurno;

	} */
	/**
	 * Liz de la Garza
	 * Metodo que convierte el horario a minutos (Se envia un String para separar el horario 
	 * o se envian la hora y los minutos ya separados)
	 * @param horaStn(hora)
	 * @param horaInt (hora)
	 * @param min(minuto)
	 * @return hora(la hora convertida a minutos)
	 *
	
	public static int getHora(String horaStn , int horaInt, int min ) {
		int hora = 0;
		//Si la hora es un string se separa a horas y minutos
		if (horaStn != null) {
			int dosPuntos = horaStn.indexOf(':');
            if (dosPuntos < 0) {
            	horaInt = Integer.parseInt(horaStn.trim());
            } else {
            	horaInt = Integer.parseInt(horaStn.substring(0, dosPuntos));
            	min = Integer.parseInt(horaStn.substring(dosPuntos + 1, horaStn.length()));
            }

		}
		//Se convierte a minutos
		hora = horaInt * 60 + min;
 		return hora;
	}*/

	/**
	 * Liz de la Garza
	 * Metodo que valida que el horario de la cita seleccionada no coincida con el horario de otra cita ya agendada 
	 * @param fecha (fecha de la cita)
	 * @param medicoId(el ID del medico)
	 * @param medAsistId(el ID del Asistente del medico)
	 * @param inicSelec(hora inicial de la cita)
	 * @param finSelec(hora final de la cita)
	 * @return isNoValido(si la cita es valida dentro del horario escogido)
	 *

	public static boolean validaCitasPrevias(Properties ctx, int medicoId, 
			String fecha, int medAsistID, int horaIni, int horaFin, 
			int minIni, int minFin) {

		boolean isNoValido = false;
		String minInis;
		String minFins;
		
		NumberFormat formatter = new DecimalFormat("00"); 
		
		try {
			
			minInis = formatter.format(minIni);
			minFins = formatter.format(minFin);
			
			AgendaMedicaModel.isOcupado(
					ctx, 
					medicoId, 
					0, 
					fecha + " " +String.valueOf(horaIni) + ":" + minInis  + ":00", 
					fecha + " " + String.valueOf(horaFin) + ":" + minFins + ":00",
					"C",
					false
			);
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getHorasConsul", e);
			isNoValido = true;
		} 

		return isNoValido;
	}*/

    /**
	 * Modifica el estatus de la cita
	 *
	 * @param Properties ctx
	 * @param Integer cita
	 *  
	 * @return boolean 
     * @throws Exception 
	 * 
	 * @throws Exception
	 */

// 	public static void cerrarCita(Properties ctx, Integer cita) throws Exception{
//
// 		Trx trx = Trx.get(Trx.createTrxName("CECI"), true);
// 		
//
//        try {
//
//        	MEXMECitaMedica datos = new MEXMECitaMedica(ctx, cita.intValue(), null);
//
//        	datos.setEstatus(MEXMECitaMedica.ESTATUS_Closed);
//
//        	
//        	 if(!datos.save(trx.getTrxName()))
//         		throw new Exception ("msg.odontologia.agendamedica.errorCerrar");
//
//        } catch (Exception e) {
//
//			s_log.log(Level.SEVERE, "-- saveNew : ", e);
//			Trx.rollback(trx);
//			throw e;
//
//		} finally {
//                Trx.close(trx);
//		}
//		
//
//	}

    /**
	 * Modifica el estatus de la cita
	 *
	 * @param Properties ctx
	 * @param Integer cita
	 *  
	 * @return boolean 
     * @throws Exception 
	 * 
	 * @throws Exception
	 *

	public static void confirmarCita(Properties ctx, Integer cita) throws Exception {

        Trx trx = Trx.get(Trx.createTrxName("CECI"), true);

        try {

        	MEXMECitaMedica datos = new MEXMECitaMedica(ctx, cita.intValue(), null);

        	datos.setEstatus(MEXMECitaMedica.ESTATUS_Confirmed);
        	datos.setConfirmada(true);
        	
        	datos.setFechaConfirm(DB.getTimestampForOrg(ctx));
        	
        	if(!datos.save(trx.getTrxName()))
        		throw new Exception ("error.citas.noConfirmarCita");

        } catch (Exception e) {
			s_log.log(Level.SEVERE, "-- saveNew : ", e);
			Trx.rollback(trx);
			throw e;

		} finally {
                Trx.close(trx);
		}
		

	}*/
	
//	public static void cambiarFechatrxCita(Properties ctx, Integer cita, long fecha) throws Exception {
//
//		Trx trx = Trx.get(Trx.createTrxName("CECI"), true);
//
//        String whereClause = "";
//
//        try {
//        	
//        	String horastr = null;
//        	Calendar cal = Calendar.getInstance(DateTimeZone.forID(Env.getTimezone(ctx)).toTimeZone());
//        	//Calendar cal = Calendar.getInstance();
//        	cal.setTimeInMillis(fecha);
//        	horastr =Constantes.getSdfFechaHora().format(cal.getTime());
//			
//        	MEXMECitaMedica datos = new MEXMECitaMedica(ctx, cita.intValue(),null);
//        	
//			boolean horarioMedDisp = false;
//			horarioMedDisp = AgendaMedicaModel.horarioMedDisp(ctx, datos.getEXME_Medico_ID(),Constantes.getSdfFechaHora().parse(horastr));
//			if(!horarioMedDisp)
//				throw new Exception("error.citasDet.horarioMed");
//			
//			//validamos con respecto a la duracion, segun la agenda del  medico (que no haya otras actividades)
//				String fechafin = Constantes.getSdfFechaHora().format(AgendaMedicaModel.getFechaFinal(horastr,datos.getDuracion()))+":00";
//				AgendaMedicaModel.isOcupado(ctx,datos.getEXME_Medico_ID(),cita.intValue(),horastr, fechafin, "C", false);
//			
//
//			// excluimos la misma cita .- Lama
//			if (cita.intValue() > 0)
//				whereClause += " AND EXME_CitaMedica_ID <> " + cita.intValue();
//
//			if (MEXMECitaMedica.IsOcupado(datos.getEXME_Medico_ID(), horastr, whereClause, false, false,
//					false, null)) {
//				throw new Exception("error.citasDetalle.otraCita");
//			}
//
//			//Validar que el paciente seleccionado no tenga cita a la hora
//			// seleccionada con otro m�dico.
//			whereClause += " AND EXME_Paciente_ID = " + datos.getEXME_Paciente_ID();
//			if (MEXMECitaMedica.IsOcupado(datos.getEXME_Medico_ID(), horastr, whereClause, 
//					false, false, true, null)) {
//				throw new Exception("error.citasdetalle.tieneOtraCita");
//			}
//        	
//        	datos.setFechaHrCita(new Timestamp(fecha));
//	        if(!datos.save(trx.getTrxName())){
//	        		throw new Exception ("error.diarioEnfermeria.registro.guardar.fecha");
//	        }else{
//	        	Trx.commit(trx);
//			}
//
//        } catch (Exception e) {
//        	Trx.rollback(trx);
//			s_log.log(Level.SEVERE, "-- saveNew : ", e);
//			
//			throw e;
//
//		} finally {
//             Trx.close(trx);
//		}
//		
//
//	}

    /**
	 * Modifica el estatus de la cita
	 *
	 * @param Properties ctx
	 * @param Integer cita
	 *  
	 * @return boolean 
     * @throws Exception 
	 * @throws Exception
	 *

	public static void cancelarCita(Properties ctx, Integer cita) throws Exception{

		Trx trx = Trx.get(Trx.createTrxName("CECI"), true);

       
        try {

        	MEXMECitaMedica datos = new MEXMECitaMedica(ctx, cita.intValue(), trx.getTrxName());

        	datos.setEstatus(MEXMECitaMedica.ESTATUS_Cancelled);
        	datos.setFechaCancel(DB.getTimestampForOrg(ctx));// .- Lama

        	if(!datos.save())
        		throw new Exception ("msg.odontologia.agendamedica.errorCancelar");

        } catch (Exception e) {
        	Trx.rollback(trx);
			s_log.log(Level.SEVERE, "-- saveNew : ", e);
			
			throw e;

		} finally {
             Trx.close(trx);
		}
	

	}*/
	/**
	 * Liz de la Garza
	 * m�todo que obtiene la informacion de las citas para cierta especialidad
	 * y opcionalmente para un m�dico y/o un paciente
	 * @param ctx-Contexto
	 * @param especialidad_id - Especialidad del m�dico
	 * @param medico_id - ID del m�dico
	 * @param paciente_id - ID del Paciente
	 * @param fechaIni - fecha inicial de busqueda
	 * @param fechaFin - fecha final de busqueda
	 * @return List - lista con la info. de citas
	 */

	public static List<CitaMedica_MO_View> getHistorialCitas(Properties ctx, int especialidad_id, int medico_id, int paciente_id, String fechaIni, String fechaFin){
		List<CitaMedica_MO_View> lista = new ArrayList<CitaMedica_MO_View>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

   	 sql.append("SELECT cita.EXME_CitaMedica_ID AS CitaMedicaID, TO_CHAR(cita.FechaHrCita,'dd/MM/yyyy') AS FechaProgramada, ")
   	 .append(" TO_CHAR(cita.FechaHrCita,'HH24:mi') AS HoraProgramada, TO_CHAR(cita.FechaHrIni,'dd/MM/yyyy') AS FechaInicita, ")
   	 .append(" TO_CHAR(cita.FechaHrIni,'HH24:mi') AS HoraIniCita,TO_CHAR(cita.FechaHrFin,'dd/MM/yyyy') AS FechaFinCita, ")
   	 .append(" TO_CHAR(cita.FechaHrFin,'HH24:mi') AS HoraFinCita , med.Nombre_Med AS Medico , ")
   	 .append(" pac.Nombre_Pac AS Paciente, pac.EXME_Paciente_ID AS PacienteID, cita.Estatus ")
   	 .append(" FROM EXME_CitaMedica cita ")
//   	 .append(" INNER JOIN EXME_Especialidad esp ON (esp.EXME_Especialidad_ID=cita.EXME_Especialidad_ID) ")
   	 .append(" INNER JOIN EXME_Medico med ON (med.EXME_Medico_ID= cita.EXME_Medico_ID) ")
   	 .append(" INNER JOIN EXME_Paciente pac ON (cita.EXME_Paciente_ID = pac.EXME_Paciente_ID ) ")
   	 .append(" WHERE cita.EXME_Especialidad_ID = ? ");
   	 //Liz dlGarza-Si se filtrar� por m�dico
   	 if (medico_id != 0) {
   		 sql.append(" AND med.EXME_Medico_ID = ? ");
   	 }
   	 //Liz dlGarza-Si se filtrar� por paciente
   	 if (paciente_id != 0) {
   		 sql.append(" AND pac.EXME_Paciente_ID = ? ");
   	 }
   	 if (DB.isOracle()) {
   		 sql.append(" AND TRUNC(cita.FechaHrCitA, 'DD') ");
   	 } else if (DB.isPostgreSQL()) {
   		sql.append(" AND DATE_TRUNC('day', cita.FechaHrCitA) "); 
   	 }
   	 sql.append(" BETWEEN TO_DATE(?, 'DD/MM/YYYY') AND TO_DATE(?, 'DD/MM/YYYY') ")
   	 //Liz dlGarza-Traer citas con estatus diferente a pend. por confirmar o confirmada
   	 .append(" AND cita.Estatus NOT IN ('").append(MEXMECitaMedica.ESTATUS_ToBeConfirmed).append("','").append(MEXMECitaMedica.ESTATUS_Confirmed).append("')")
   	 .append(" ORDER BY cita.FechaHrCita DESC");

   	PreparedStatement pstmt = null;
   	ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, especialidad_id);
			if (medico_id != 0) {
				pstmt.setInt(2, medico_id);
				if (paciente_id != 0) {
					pstmt.setInt(3, paciente_id);
					pstmt.setString(4, fechaIni);
					pstmt.setString(5, fechaFin);
				} else {
					pstmt.setString(3, fechaIni);
					pstmt.setString(4, fechaFin);
				}
			} else if (paciente_id != 0) {
				pstmt.setInt(2, paciente_id);
				pstmt.setString(3, fechaIni);
				pstmt.setString(4, fechaFin);
			} else {
				pstmt.setString(2, fechaIni);
				pstmt.setString(3, fechaFin);
			}


			rs = pstmt.executeQuery();

			while (rs.next()) {

				//Vista para la cita medica
				CitaMedica_MO_View citas = new CitaMedica_MO_View();

				if(rs.getString("FechaIniCita")!=null)
					citas.setFechaIniCita(rs.getString("FechaIniCita"));
				else
					citas.setHoraIniCita("");

				if(rs.getString("HoraIniCita")!=null)
				 citas.setHoraIniCita((rs.getString("HoraIniCita")));
				else
					citas.setHoraIniCita("");

				if(rs.getString("FechaFinCita")!=null)
					citas.setFechaFinCita((rs.getString("FechaFinCita")));
				else
					citas.setFechaFinCita("");

				if(rs.getString("HoraFinCita")!=null)
					citas.setHoraFinCita((rs.getString("HoraFinCita")));
				else
					citas.setHoraFinCita("");

				citas.setCitaMedicaID(rs.getInt("CitaMedicaID"));
				citas.setFechaProgramada(rs.getString("FechaProgramada"));
				citas.setHoraProgramada(rs.getString("HoraProgramada"));
				citas.setMedico(rs.getString("Medico"));
				citas.setPaciente(rs.getString("Paciente"));
				citas.setPacienteID(rs.getInt("PacienteID"));

				//Liz dlGarza-Obtener el Estatus de la Cita medica(lista de referencia)
				if(rs.getString("Estatus").equals(MEXMECitaMedica.ESTATUS_Cancelled))
					citas.setEstatus(MRefList.getListName(ctx, MEXMECitaMedica.ESTATUS_AD_Reference_ID, MEXMECitaMedica.ESTATUS_Cancelled));
				else {
					if(rs.getString("Estatus").equals(MEXMECitaMedica.ESTATUS_Closed))
						citas.setEstatus(MRefList.getListName(ctx, MEXMECitaMedica.ESTATUS_AD_Reference_ID, MEXMECitaMedica.ESTATUS_Closed));
					else if(rs.getString("Estatus").equals(MEXMECitaMedica.ESTATUS_Executed))
						citas.setEstatus(MRefList.getListName(ctx, MEXMECitaMedica.ESTATUS_AD_Reference_ID, MEXMECitaMedica.ESTATUS_Executed));
				}
				lista.add(citas);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
			
		}


		return lista;
	}

//	public static void modificaMActPaciente(Properties ctx, MO_EjecutarCita_VO vo, String trxName ) throws Exception{
//		
//		vo.setTrx(trxName);
//		//String error = null;
//		try {
//						
//			MEXMEEstServ estServLogeo = new MEXMEEstServ(ctx, vo.getEstacionID(), null);
//			MPriceList listaPrecios = new MPriceList(ctx, Env.getContextAsInt(ctx, "#M_PriceListSO_ID"), null);
//			MEXMEMedico medico = new MEXMEMedico(ctx, vo.getExme_Medico_ID().intValue(), null);
//			MConfigEC configEC = MConfigEC.get(ctx, null);
//			MConfigPre configPre = MConfigPre.get(ctx, null);
//			Hashtable<Integer,BigDecimal> toDeliver = new Hashtable<Integer,BigDecimal>();
//			
//			//Obtenemos el precio de la consulta
//			int almacen = Env.getContextAsInt(ctx, "#M_Warehouse_ID");
//			MEXMECitaMedica cita = new MEXMECitaMedica(ctx, vo.getExme_CitaMedica_ID(), null); 
//			MEXMEPaciente paciente = new MEXMEPaciente(ctx, vo.getExme_Paciente_ID(), null);
//
//			MPrecios precios = MEXMECitaMedica.getPrecioCitaMedica(
//					ctx, 
//					vo.getExme_Medico_ID().intValue(), 
//					vo.getExme_Paciente_ID().intValue(), 
//					estServLogeo.getTipoArea(), 
//					estServLogeo.getEXME_Area_ID(),
//					cita.getEXME_Especialidad_ID(), 
//					PreciosVenta.PVH, 
//					paciente.getC_BPartner_ID(), 
//					almacen, 
//					configPre, 
//					configEC, 
//					paciente, 
//					medico, 
//					null);
//			// Expert. Precios en cero
//	/*		if (Env.ZERO.compareTo(precios.getPriceList())>=0) {
//				throw new Exception("error.citas.noExistePreCons");
//				
//			}
//*/
//			MProduct product = new MProduct(ctx, precios.getM_Product_ID(), null);
//			if(product.getM_Product_ID()==0){
//				throw new Exception("error.citas.noExisteProdConsulta");
//				
//			}
//			MEXMEConfigOdonto m_configOdonto = MEXMEConfigOdonto.get(ctx, null);
//			MEXMEActPacienteIndH consulta = new MEXMEActPacienteIndH(ctx, 0, trxName);
//			consulta.setIsService(true);
//			consulta.setC_BPartner_ID(paciente.getC_BPartner_ID());
//			consulta.setM_PriceList_ID(listaPrecios.getM_PriceList_ID());
//			consulta.setEXME_ActPaciente_ID(vo.getActPacienteID());
////			consulta.setC_DocType_ID(MEXMEDocType.getOfName(ctx, MEXMEActPacienteIndH.DOC_TYPE_SERVICIO, null));
////			consulta.setC_DocTypeTarget_ID(MEXMEDocType.getOfName(ctx, MEXMEActPacienteIndH.DOC_TYPE_SERVICIO, null));
//			consulta.setDocumentNo(MSequence.getDocumentNo ((int)consulta.getC_DocType_ID(), null, false));
//			consulta.setM_Warehouse_ID(m_configOdonto.getM_Warehouse_ID()); //almacen de logeo
//			consulta.setC_Location_ID(paciente.getC_Location_ID());
//			consulta.calculateTaxTotal();
//			consulta.setDocStatus(DocAction.STATUS_Completed);
//			consulta.setDocAction(DocAction.ACTION_None);
//			consulta.setIsDelivered(true);
//			consulta.setDateDelivered(consulta.getDateOrdered());
//			consulta.setProcessed(true);
//			consulta.setM_Warehouse_Sol_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID")); //almacen de logeo
//			consulta.setEXME_Medico_ID(vo.getExme_Medico_ID().intValue());
//			consulta.setMedicoNombre(medico.getNombre_Med()==null?(medico.getName()+" "+medico.getApellido1()+" "+medico.getApellido2()):medico.getNombre_Med());
//			consulta.setAD_OrgTrx_ID(estServLogeo.getAD_OrgTrx_ID());
//
//			
//			if(!consulta.save(trxName)){
//				throw new Exception("error.citas.noExisteIndicacion");
//				
//			}
//			
//			MEXMEActPacienteInd indicacion = new MEXMEActPacienteInd(ctx, 0, trxName);
//			indicacion.setActPacienteIndH(consulta);
//			indicacion.setM_Product_ID(precios.getM_Product_ID());
//			indicacion.setC_UOM_ID(product.getC_UOM_ID());
//			indicacion.setC_Currency_ID(listaPrecios.getC_Currency_ID());
//			indicacion.setLine(10);
//			indicacion.setQtyOrdered(Env.ONE);
//			indicacion.setSurtir(true);
//			indicacion.setAD_OrgTrx_ID(estServLogeo.getAD_OrgTrx_ID()); 
//			indicacion.setDateDelivered(indicacion.getDateOrdered());
//			indicacion.setQtyDelivered(Env.ONE);
//			indicacion.setEXME_Area_ID(estServLogeo.getEXME_Area_ID());/////
//			indicacion.setTipoArea(estServLogeo.getTipoArea());
////			indicacion = precios.preciosActual(indicacion);
//			BigDecimal taxAmt = Env.ZERO;
//			if(precios.getTax()!=null)
//				taxAmt = indicacion.getLineNetAmt().multiply(precios.getTax().getRate().divide(Env.ONEHUNDRED));
//
//			if(!indicacion.save(trxName)){
//				throw new Exception("error.citas.noExisteIndic");
//				
//			}
//			
//			consulta.setTotalLines(indicacion.getLineNetAmt());
//			consulta.calculateTaxTotal();
//			consulta.setGrandTotal((consulta.getTotalLines()).add(taxAmt));
//			//Modifique el parametro enviado por el de la variable local trxName --Julio Gutierrez
//			if(!consulta.save(trxName)){
//				throw new Exception("error.citas.noExisteIndicacion");
//				
//			}
//			toDeliver.put(indicacion.getEXME_ActPacienteInd_ID(), Env.ONE);
//			
//			MEXMEInOut m_InOut = MEXMEInOut.createFrom(
//					consulta, DB.getTimestampForOrg(ctx), 
//					toDeliver, false, null, true, estServLogeo, 
//					trxName);
//			
//			if(m_InOut == null){
//				throw new Exception("error.citas.noLocation");
//				
//			}
//			
//			String status = m_InOut.prepareIt();
//			if(!DocAction.STATUS_InProgress.equals(status)){
//				throw new Exception("surtPedidoAction.error.surtir");
//				
//			}
//			
//			status = m_InOut.completeIt();
//			m_InOut.setDocStatus(status);
//			if(!DocAction.STATUS_Completed.equals(status)){
//				throw new Exception("surtPedidoAction.error.surtir");
//				
//			}
//
//			if(!m_InOut.save(trxName)){
//				throw new Exception("surtPedidoAction.error.surtir");
//				
//			}
//			
//			//Se relaciona y cambian estatus
//			if(configEC.isReqFactCE())
//				if(cita.getC_Invoice_ID()>0){
//					consulta.setDocStatus(DocAction.STATUS_Completed);
//					consulta.setDocAction(DocAction.ACTION_None);
//					consulta.setIsInvoiced(true);
//					consulta.setProcessed(true);
//					consulta.setC_Invoice_ID(cita.getC_Invoice_ID());
//					if(!consulta.save(trxName)){
//						s_log.log(Level.SEVERE,"No se actualizo EXME_ActPacienteIndH.IsInvoiced = 'Y'.");
//						throw new Exception("error.citas.noExisteIndicacion");
//						
//					}
//					
//					m_InOut.setC_Invoice_ID(cita.getC_Invoice_ID());
//					if(!m_InOut.save(trxName)){
//						throw new Exception("surtPedidoAction.error.surtir");
//						
//					}
//					
//				}
//		}catch (Exception ex) {
//				s_log.log(Level.SEVERE, "", ex);
//				throw ex;
//			
//		}
//		
//		
//	}
	
//	public static MO_EjecutarCita_VO facturarCita_MO(Properties ctx, MO_EjecutarCita_VO vo){   
//		
//		Trx trx = Trx.get(Trx.createTrxName("MActF"), true);
//		
//		//String error = null;
//		
//		try {
//
//			MEXMEEstServ estServLogeo = new MEXMEEstServ(ctx, vo.getEstacionID(), null);
//			MPriceList listaPrecios = new MPriceList(ctx, Env.getContextAsInt(ctx, "#M_PriceList_ID"), null);
//			MEXMEMedico medico = new MEXMEMedico(ctx, vo.getExme_Medico_ID().intValue(), null);
//			MConfigEC configEC = MConfigEC.get(ctx, null);
//			MConfigPre configPre = MConfigPre.get(ctx, null);
//			Hashtable<Integer,BigDecimal> toDeliver = new Hashtable<Integer,BigDecimal>();
//			
//			//Obtenemos el precio de la consulta
//			int almacen = Env.getContextAsInt(ctx, "#M_Warehouse_ID");
//			MEXMECitaMedica cita = new MEXMECitaMedica(ctx, vo.getExme_CitaMedica_ID(), null); 
//			MEXMEPaciente paciente = new MEXMEPaciente(ctx, vo.getExme_Paciente_ID(), null);
//
//			MPrecios precios = MEXMECitaMedica.getPrecioCitaMedica(
//					ctx, 
//					vo.getExme_Medico_ID().intValue(), 
//					vo.getExme_Paciente_ID().intValue(), 
//					estServLogeo.getTipoArea(), 
//					estServLogeo.getEXME_Area_ID(),
//					cita.getEXME_Especialidad_ID(), 
//					PreciosVenta.PVH, 
//					paciente.getC_BPartner_ID(), 
//					almacen, 
//					configPre, 
//					configEC, 
//					paciente, 
//					medico, 
//					null);
//			// Expert. Precios en cero
//		/*	if (Env.ZERO.compareTo(precios.getPriceList())>=0) {
//				vo.getErrores().add("error.citas.noExistePreCons");
//				return vo;
//			}
//*/
//			MProduct product = new MProduct(ctx, precios.getM_Product_ID(), null);
//			if(product.getM_Product_ID()==0){
//				vo.getErrores().add("error.citas.noExisteProdConsulta");
//				return vo;
//			}
//			MEXMEConfigOdonto m_configOdonto = MEXMEConfigOdonto.get(ctx, null);
//			MEXMEActPacienteIndH consulta = new MEXMEActPacienteIndH(ctx, 0, trx.getTrxName());
//			consulta.setIsService(true);
//			consulta.setC_BPartner_ID(paciente.getC_BPartner_ID());
//			consulta.setM_PriceList_ID(listaPrecios.getM_PriceList_ID());
//			consulta.setEXME_ActPaciente_ID(vo.getActPacienteID());
////			consulta.setC_DocType_ID(MEXMEDocType.getOfName(ctx, MEXMEActPacienteIndH.DOC_TYPE_SERVICIO, null));
////			consulta.setC_DocTypeTarget_ID(MEXMEDocType.getOfName(ctx, MEXMEActPacienteIndH.DOC_TYPE_SERVICIO, null));
//			consulta.setDocumentNo(MSequence.getDocumentNo ((int)consulta.getC_DocType_ID(), null, false));
//			consulta.setM_Warehouse_ID(m_configOdonto.getM_Warehouse_ID()); //almacen de logeo
//			consulta.setC_Location_ID(paciente.getC_Location_ID());
//			consulta.calculateTaxTotal();
//			consulta.setDocStatus(DocAction.STATUS_Completed);
//			consulta.setDocAction(DocAction.ACTION_None);
//			consulta.setIsDelivered(true);
//			consulta.setDateDelivered(consulta.getDateOrdered());
//			consulta.setProcessed(true);
//			consulta.setM_Warehouse_Sol_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID")); //almacen de logeo
//			consulta.setEXME_Medico_ID(vo.getExme_Medico_ID().intValue());
//			consulta.setMedicoNombre(medico.getNombre_Med()==null?(medico.getName()+" "+medico.getApellido1()+" "+medico.getApellido2()):medico.getNombre_Med());
//			consulta.setAD_OrgTrx_ID(estServLogeo.getAD_OrgTrx_ID());
//
//			if(!consulta.save()){
//				Trx.rollback(trx);
//				vo.getErrores().add("error.citas.noExisteIndicacion");
//				return vo;
//			}
//			
//			MEXMEActPacienteInd indicacion = new MEXMEActPacienteInd(ctx, 0, trx.getTrxName());
//			indicacion.setM_Product_ID(precios.getM_Product_ID());
//			indicacion.setActPacienteIndH(consulta);
//			indicacion.setC_UOM_ID(product.getC_UOM_ID());
//			indicacion.setC_Currency_ID(listaPrecios.getC_Currency_ID());
//			indicacion.setLine(10);
//			indicacion.setQtyOrdered(Env.ONE);
//			indicacion.setSurtir(true);
//			indicacion.setAD_OrgTrx_ID(estServLogeo.getAD_OrgTrx_ID()); 
//			indicacion.setDateDelivered(indicacion.getDateOrdered());
//			indicacion.setQtyDelivered(Env.ONE);
//			indicacion.setEXME_Area_ID(estServLogeo.getEXME_Area_ID());
//			indicacion.setTipoArea(estServLogeo.getTipoArea());
////			indicacion = precios.preciosActual(indicacion);
//			BigDecimal taxAmt = Env.ZERO;
//			if(precios.getTax()!=null)
//				taxAmt = indicacion.getLineNetAmt().multiply(precios.getTax().getRate().divide(Env.ONEHUNDRED));
//
//			if(!indicacion.save()){
//				Trx.rollback(trx);
//				vo.getErrores().add("error.citas.noExisteIndic");
//				return vo;
//			}
//			
//			consulta.setTotalLines(indicacion.getLineNetAmt());
//			consulta.calculateTaxTotal();
//			consulta.setGrandTotal((consulta.getTotalLines()).add(taxAmt));
//			//Modifique el parametro enviado por el de la variable local trxName --Julio Gutierrez
//			if(!consulta.save()){
//				Trx.rollback(trx);
//				vo.getErrores().add("error.citas.noExisteIndicacion");
//				return vo;
//			}
//			toDeliver.put(indicacion.getEXME_ActPacienteInd_ID(), Env.ONE);
//			
//			MEXMEInOut m_InOut = MEXMEInOut.createFrom(
//					consulta,DB.getTimestampForOrg(ctx), 
//					toDeliver, false, null, true, estServLogeo, 
//					trx.getTrxName());
//			
//			if(m_InOut == null){
//				Trx.rollback(trx);
//				vo.getErrores().add("error.citas.noLocation");
//				return vo;
//			}
//			
//			String status = m_InOut.prepareIt();
//			if(!DocAction.STATUS_InProgress.equals(status)){
//				Trx.rollback(trx);
//				vo.getErrores().add("surtPedidoAction.error.surtir");
//				return vo;
//			}
//			
//			status = m_InOut.completeIt();
//			m_InOut.setDocStatus(status);
//			if(!DocAction.STATUS_Completed.equals(status)){
//				Trx.rollback(trx);
//				vo.getErrores().add("surtPedidoAction.error.surtir");
//				return vo;
//			}
//
//			if(!m_InOut.save(trx.getTrxName())){
//				Trx.rollback(trx);
//				vo.getErrores().add("surtPedidoAction.error.surtir");
//				return vo;
//			}
//			
//			//Se relaciona y cambian estatus
//			if(configEC.isReqFactCE())
//				if(cita.getC_Invoice_ID()>0){
//					consulta.setDocStatus(DocAction.STATUS_Completed);
//					consulta.setDocAction(DocAction.ACTION_None);
//					consulta.setIsInvoiced(true);
//					consulta.setProcessed(true);
//					consulta.setC_Invoice_ID(cita.getC_Invoice_ID());
//					if(!consulta.save()){
//						s_log.log(Level.SEVERE,"No se actualizo EXME_ActPacienteIndH.IsInvoiced = 'Y'.");
//						Trx.rollback(trx);
//						vo.getErrores().add("error.citas.noExisteIndicacion");
//						return vo;
//					}
//					
//					m_InOut.setC_Invoice_ID(cita.getC_Invoice_ID());
//					if(!m_InOut.save(trx.getTrxName())){
//						Trx.rollback(trx);
//						vo.getErrores().add("surtPedidoAction.error.surtir");
//						return vo;
//					}
//					
//				}
//		} catch (Exception e) {
//			
//			Trx.rollback(trx);
//			vo.getErrores().add(e.getMessage());
//			return vo;
//		} finally {
//			Trx.close(trx);
//		}
//		return vo;
//		
//}
	
//	public static MEXMEActPacienteIndH setDatosIndicacionesH_MO(Properties ctx, MO_EjecutarCita_VO vo, String trxName) throws Exception {
//		
//		//Modifique el parametro enviado por el de la variable local trxName
//		MEXMEActPacienteIndH datos = new MEXMEActPacienteIndH(ctx, 0, trxName);		
//		try {			
//			MEXMEPaciente paciente = new MEXMEPaciente(ctx, vo.getExme_Paciente_ID(), null);
//			//asignamos la lista de precios del contexto
//			datos.setM_PriceList_ID(Env.getContextAsInt(ctx, "#M_PriceList_ID"));
//			//la moneda y si incluye impuesto de la lista de precios //Modifique el parametro enviado por el de la variable local trxName
//			MPriceList lista = new MPriceList(ctx, datos.getM_PriceList_ID(), trxName);
//			datos.setC_Currency_ID(lista.getC_Currency_ID());
//			datos.setIsTaxIncluded(lista.isTaxIncluded());
//			//el socio y ubicacion del paciente
//			datos.setC_BPartner_ID(paciente.getC_BPartner_ID());
//			datos.setC_Location_ID(paciente.getC_Location_ID());
//			//asignamos el tipo de documento 'Receta Medica'
////			datos.setC_DocType_ID(MEXMEDocType.getOfName(ctx, MEXMEActPacienteIndH.DOC_TYPE_SERVICIO, null));
////			datos.setC_DocTypeTarget_ID(MEXMEDocType.getOfName(ctx, MEXMEActPacienteIndH.DOC_TYPE_SERVICIO, null));
//			//para aquellos que no sea surtir en farmacia
//			if(vo.getLstIndicacionesMO().size()==1){
//				if(vo.getSurFarm().equalsIgnoreCase("Y"))
//					datos.setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Drafted);
//				else {
//					datos.setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Completed);
//					datos.setIsDelivered(true);
//				}
//			}else 
//				datos.setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Drafted);
//			datos.setDocAction(MEXMEActPacienteIndH.DOCACTION_Complete);
//			datos.setDateAcct(DB.getTimestampForOrg(ctx));
//			//act paciente
//			datos.setEXME_ActPaciente_ID(vo.getActPacienteID());
//			//el consecutivo de la secuencia //Modifique el parametro enviado por el de la variable local trxName
//			datos.setDocumentNo(MSequence.getDocumentNo ((int)datos.getC_DocType_ID(), null, false));
//			//tipo de dato selecc0ionado
//			datos.setIsService(false);
//			//y el almacen seleccionado
//			datos.setM_Warehouse_ID(Env.getContextAsInt(ctx,"#M_Warehouse_ID"));
//			datos.setM_Warehouse_Sol_ID(Env.getContextAsInt(ctx,"#M_Warehouse_ID"));
//			//la organizacion transaccional de la estacion de servicio de logeo es decir quien esta solicitando
//			int org = MEXMEEstServ.getEstServAlmOrgTrx(ctx, Env.getContextAsInt(ctx,"#M_Warehouse_ID"));
//			datos.setAD_OrgTrx_ID(org);
//			//la prioridad del pedido
//			datos.setPriorityRule(vo.getPrioridadInd());
//			datos.setDateOrdered(DB.getTimestampForOrg(ctx));
//			
//			datos.setEXME_Medico_ID(vo.getExme_Medico_ID().intValue());
//			datos.setMedicoNombre(vo.getExme_Medico_Name());
//
//			if (vo.getTxtfecha()!= null && !vo.getTxtfecha().equalsIgnoreCase("") && vo.getHoraCadena() != null && !vo.getHoraCadena().equalsIgnoreCase("")){
//				String fecha = vo.getTxtfecha()+ " " + vo.getHoraCadena().substring(0,2)+ ":" + vo.getHoraCadena().substring(2,4);//+":"+forma.getMinCadena();
//				datos.setDatePromised(new Timestamp(Constantes.getSdfFechaHora().parse(fecha).getTime()));
//			}else{
//				datos.setDatePromised(DB.getTimestampForOrg(ctx));
//			}
//
//			//Modifique el parametro enviado por el de la variable local trxName
//			if (!datos.save(trxName)) {
//				
//				throw new SQLException("error.citas.noInsertEjec");
//			}
//			
//		} catch (Exception e) {
//			
//			
//			throw new Exception("error.citas.setDatosIndicacionesH");
//		}
//		
//		return datos;
//		
//	}
	
	
	/**
	 * Recupera la ultima cita de Odontologia de un paciente
	 * @param ctx
	 * @param pacienteID
	 * @param atm - recupera la ultima cita con el cuestionario ATM
	 * @param trxName
	 * @return
	 * @author Lorena Lama
	 *
	public static MEXMECitaMedica getLastCitaMO(Properties ctx, int pacienteID, boolean atm, String trxName){
		MEXMECitaMedica retValue = null;
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			MEXMEConfigOdonto configOdonto = MEXMEConfigOdonto.get(ctx, trxName);
			
			if(configOdonto == null)
				return null;
			
			sql.append("SELECT * FROM EXME_CitaMedica WHERE isActive = 'Y' AND estatus IN ( ")
			.append(DB.TO_STRING(ESTATUS_Executed)).append(",").append(DB.TO_STRING(ESTATUS_Closed))
			.append(") AND EXME_Especialidad_ID = ? ")
			.append(" AND EXME_Paciente_ID = ? ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
			
			//para el reporte de ATM
			if(atm) {
				sql.append(" AND EXME_CitaMedica_ID IN ( SELECT DISTINCT EXME_CitaMedica_ID ")
					.append(" FROM EXME_ActPaciente actPac INNER JOIN EXME_ActPacienteDet actPacDet ON actPac.EXME_ActPaciente_ID = actPacDet.EXME_ActPaciente_ID ")
					.append(" AND actPac.EXME_Paciente_ID = EXME_Paciente_ID AND actPacDet.EXME_Cuestionario_ID = ? ")
					.append(" AND actPac.EXME_CitaMedica_id IS NOT NULL ) ");
			}
			
			sql.append(" ORDER BY Created DESC ");
			
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, configOdonto.getEXME_Especialidad_ID());
			psmt.setInt(2, pacienteID);
			if(atm)
				psmt.setInt(3, configOdonto.getEXME_Cuestionario_ATM_ID());
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				retValue = new MEXMECitaMedica(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}
		
		return retValue;
		
	}*/
	
//public static int getActPacienteCita (Integer paciente, Integer citaID) throws Exception{
//        
//    	int ret = 0;
//
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
// sql.append("	select EXME_ACTPACIENTE_ID") 
//	.append("	from exme_actpaciente")
//	.append("	where exme_Paciente_id= ?")
//	.append("	AND EXME_CITAMEDICA_ID = ?");
//             
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), null);
//            pstmt.setInt(1, paciente.intValue());
//            pstmt.setInt(2, citaID.intValue());            
//            
//            rs = pstmt.executeQuery();
//            
//            if(rs.next()) {
//            	
//            	ret = rs.getInt(1);
//            	 
//            }
//           
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "MPregunta.getPreguntasCuestionario", e);
//			
//		}
//        finally
//        {
//        	DB.close(rs, pstmt);
//   			rs = null;
//   			pstmt = null;
//        }
//
//        return ret;
//    }

}
