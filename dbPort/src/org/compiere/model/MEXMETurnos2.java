package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMETurnos2 extends X_EXME_Turnos2 {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**	Logger							*/
	protected transient static CLogger	s_log = CLogger.getCLogger ("MEXMEActPacienteIndH");
	
	/**
     * @param ctx
     * @param C_Tax_ID
     * @param trxName
     */
    public MEXMETurnos2(Properties ctx, int EXME_Turnos2_ID, String trxName) {
        super(ctx, EXME_Turnos2_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMETurnos2(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true or false
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Nuevo registro
		boolean exito = true;
		//if (newRecord)
		//{
		
			if(getLun_Salida().before(getLun_Entrada()))
			{
				exito = false;
			}
			
			if(getMar_Salida().before(getMar_Entrada()))
			{
				exito = false;
			}
			
			if(getMie_Salida().before(getMie_Entrada()))
			{
				exito = false;
			}
			
			if(getJue_Salida().before(getJue_Entrada()))
			{
				exito = false;
			}
			
			if(getVie_Salida().before(getVie_Entrada()))
			{
				exito = false;
			}
			
			if(getSab_Salida().before(getSab_Entrada()))
			{
				exito = false;
			}
			
			if(getDom_Salida().before(getDom_Entrada()))
			{
				exito = false;
			}
		//}
		
		return exito;
	}	//	beforeSave
	
	/**
	 * isHoraLimite
	 * regresa true si las fechas pasadas como 
	 * parametro estan dentro
	 * del horario del almacen
	 * @author LRHU 
	 * @param ctx	Contexto
	 * @param almacenID	Identificador del almacén
	 * @param fecIni	Fecha Inicial
	 * @param fecFin	Fecha Final
	 * @param trxName	Transaccional
	 * @return true or false
	 */
	public static boolean isHoraLimite(Properties ctx, int almacenID, Date fecIni, Date fecFin, String trxName) {

		boolean mover = true;

		//raul: se quitaron los metodos obsoletos
		Calendar horaInicial = Calendar.getInstance();
		Calendar horaFinal = Calendar.getInstance();

		horaInicial.setTime(fecIni);
		horaFinal.setTime(fecFin);


		StringBuilder sql = new StringBuilder()
		.append(" SELECT Dom_Entrada,Dom_Salida, Lun_entrada,Lun_Salida,Mar_entrada,Mar_salida, ")
		.append(" Mie_entrada,Mie_Salida,Jue_Entrada,Jue_Salida, ")
		.append(" Vie_Entrada,Vie_Salida,Sab_Entrada,Sab_Salida ")
		.append(" FROM exme_turnos2 turnos ")
		.append(" INNER JOIN m_warehouse almacen ")
		.append(" ON (turnos.exme_turnos2_id = almacen.exme_turnos2_id) ")
		.append(" WHERE almacen.m_warehouse_id = ?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, almacenID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				int dayOfWeek = horaInicial.get(Calendar.DAY_OF_WEEK);
				//Numero de Columna del Turno Inicial: Domingo--> 1, Lunes--> 3, Martes--> 5, etc..
				int numColumna = dayOfWeek + (dayOfWeek-1);
				Calendar calTurnoInicial = Calendar.getInstance();
				Calendar calTurnoFinal = Calendar.getInstance();

				calTurnoInicial.setTimeInMillis(rs.getTimestamp(numColumna).getTime());
				calTurnoFinal.setTimeInMillis(rs.getTimestamp(numColumna+1).getTime());

				/*Compara la hora del turno contra la hora seleccionada
				si la hora del turno es mayor aun no empieza el turno*/
				if(calTurnoInicial.get(Calendar.HOUR_OF_DAY) > horaInicial.get(Calendar.HOUR_OF_DAY))
					mover = false;

				/*Si las horas son iguales, compara los minutos de igual forma*/
				if(calTurnoInicial.get(Calendar.HOUR_OF_DAY) == horaInicial.get(Calendar.HOUR_OF_DAY)){
					if(calTurnoInicial.get(Calendar.MINUTE) > horaInicial.get(Calendar.MINUTE))
						mover = false;
				}

				/*Compara la hora final del turno contra la hora final seleccionada
				si la hora final del turno es menor el turno ya terminó*/
				if(calTurnoFinal.get(Calendar.HOUR_OF_DAY) < horaFinal.get(Calendar.HOUR_OF_DAY))
					mover = false;

				/*Si las horas son iguales, compara los minutos de igual forma*/
				if(calTurnoFinal.get(Calendar.HOUR_OF_DAY) == horaFinal.get(Calendar.HOUR_OF_DAY)){
					if(calTurnoFinal.get(Calendar.MINUTE) < horaFinal.get(Calendar.MINUTE))
						mover = false;
				}
			}
			
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getEntries", e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return mover;
	}
	
	
	public static List<LabelValueBean> getTurnos(Properties ctx) throws Exception {
		//lista con las motivos de cita
		List<LabelValueBean> turnos = new ArrayList<LabelValueBean>();
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		//buscamos el motivo de cita
		sql = "SELECT * FROM EXME_Turnos2 where isActive = 'Y'";
		pstmt = null;
		rs = null;

		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean turno =
						new LabelValueBean(
						rs.getString("Value"),
						String.valueOf(rs.getInt("EXME_Turnos2_ID"))
						);
				turnos.add(turno);
			}
               
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
		return turnos;
	}
	
	/**
	 * Obtiene la hora de entrada del turno segun el dia de la semana de la fecha y turno indicado. 
	 * @param ctx
	 * @param fecha
	 * @param turnoId
	 * @return horaEntrada
	 * @author Rosy Velazquez
	 * */	
	public static Timestamp getHorarioEntrada(Properties ctx, Date fecha, int turnoId){
		
		Timestamp horaEntrada = null;			
		
		MEXMETurnos2 mTurno = new MEXMETurnos2(ctx, turnoId, null);
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		
		int diaFecha = cal.get(Calendar.DAY_OF_WEEK);		
		
		switch(diaFecha){
		
		case Calendar.MONDAY: horaEntrada = mTurno.getLun_Entrada();
							  break;
		case Calendar.TUESDAY: horaEntrada = mTurno.getMar_Entrada();
		  						break;							  
		case Calendar.WEDNESDAY: horaEntrada = mTurno.getMie_Entrada();
		  			             break;
		case Calendar.THURSDAY: horaEntrada = mTurno.getJue_Entrada();
		  					   break;
		case Calendar.FRIDAY: horaEntrada = mTurno.getVie_Entrada();
		  					  break;
		case Calendar.SATURDAY: horaEntrada = mTurno.getSab_Entrada();
		  						break;  
		case Calendar.SUNDAY: horaEntrada = mTurno.getDom_Entrada();
		  					  break;  
		};								
		
		return horaEntrada;
	}
	
	/**
	 * Obtiene la hora de salida del turno segun el dia de la semana de la fecha y turno indicado. 
	 * @param ctx
	 * @param fecha
	 * @param turnoId
	 * @return horaSalida
	 * @author Rosy Velazquez
	 * */	
	public static Timestamp getHorarioSalida(Properties ctx, Date fecha, int turnoId){
		
		Timestamp horaSalida = null;			
		
		MEXMETurnos2 mTurno = new MEXMETurnos2(ctx, turnoId, null);
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		
		int diaFecha = cal.get(Calendar.DAY_OF_WEEK);
		
		switch(diaFecha){
		
		case Calendar.MONDAY: horaSalida = mTurno.getLun_Salida();
							  break;
		case Calendar.TUESDAY: horaSalida = mTurno.getMar_Salida();
		  						break;							  
		case Calendar.WEDNESDAY: horaSalida = mTurno.getMie_Salida();
		  			             break;
		case Calendar.THURSDAY: horaSalida = mTurno.getJue_Salida();
		  					   break;
		case Calendar.FRIDAY: horaSalida = mTurno.getVie_Salida();
		  					  break;
		case Calendar.SATURDAY: horaSalida = mTurno.getSab_Salida();
		  						break;  
		case Calendar.SUNDAY: horaSalida = mTurno.getDom_Salida();
		  					  break;  
		};							
		
		return horaSalida;
	}
	
	/**
	 * Devuelve el turno completo en un objeto MEXMETurnos2
	 * 
	 * @param schedName el nombre del turno (FULL DAY o COMPLETO segun locale)
	 * @param ctx el contexto de la aplicacion
	 * @return un objeto MEXMETurnos2 con la informacón del turno encontrada 
	 * o null en caso de no encontrar nada
	 * @author Modificado por Jesus Cantu
	 */
	public static MEXMETurnos2 getFullDay(String schedName, Properties ctx) {
		StringBuilder st = new StringBuilder("select * from EXME_Turnos2 where upper(value) like ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_Turnos2"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMETurnos2 turnos2 = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			//pstmt.setString(1, "%FULL DAY");
			pstmt.setString(1, "%" + schedName);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				turnos2 = new MEXMETurnos2(ctx, rs, null);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return turnos2;
	}
}
