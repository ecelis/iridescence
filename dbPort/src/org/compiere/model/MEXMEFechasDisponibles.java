package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEFechasDisponibles {

	//TODO : optimizar SQLs y manejo de cadenas en esta clase, revisar ejemplo en metodo getIntervalosDisponibles


	private static SimpleDateFormat sdfHora  = new SimpleDateFormat("HH:mm");
	public static SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private static CLogger		s_log = CLogger.getCLogger (MEXMEFechasDisponibles.class);

	/**
	 * Lista de los intervalos disponibles
	 * @param ctx
	 * @param fecha
	 * @param intervalo
	 * @param trxName
	 * @return List
	 */
	public static List<MEXMEIntervalosView> getIntervalosDisponibles(
			Properties ctx, String fecha, int intervaloSol, int intervaloAlm,
			int almacen_ID, int turno_ID, String trxName)
			{
		long fechaAux = 0;
		int interUsados = 0;
		int interDisponibles = 0;
		String fec=null;

		//Obtenemos el almacen el turno y el intervalos
		MEXMETurnos2 turno = null;

		if(turno_ID>0) {
			turno = new MEXMETurnos2(ctx, turno_ID, trxName);
		}

		try{
			fec = Constantes.getSdfFecha().format(new Date(Constantes.getSdfFecha().parse(fecha).getTime()));//LRHU.Tomamos solo dd/mm/yyyy para enviarla como parametro.
			fechaAux = Constantes.getSdfFecha().parse(fecha).getTime();//LRHU. Le asignamos la fecha para comparar lo que trae el resultset
		}catch(Exception e){
			s_log.log(Level.SEVERE, "getIntervalosDisponibles" , e);
		}			

		List<MEXMEIntervalosView> list = new ArrayList<MEXMEIntervalosView>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT SUM(NVL(apih.Intervalo,0)) AS InterUsados, \n")
		.append(" c.Fecha, nvl(apih.M_Warehouse_ID,0) \n")
		.append("FROM EXME_Calendario c \n ")
		.append("	LEFT JOIN EXME_ActPacienteIndH apih ON ( \n")
		.append("		APIH.AD_CLIENT_ID = ? \n")
		.append("       AND  apih.Estatus IN ('S', 'P', 'C') \n")
		.append("       AND apih.DatePromised >= TO_DATE(?, 'DD/MM/YYYY')  \n")
		.append("       AND TO_CHAR(apih.DatePromised, 'DD/MM/YYYY') = TO_CHAR(c.Fecha, 'DD/MM/YYYY')) \n")
		.append("WHERE c.Fecha >= TO_DATE(?, 'DD/MM/YYYY') \n")
		.append("AND (apih.m_warehouse_id = ? or apih.m_warehouse_id is null) \n")
		.append("GROUP BY c.Fecha, apih.DatePromised, nvl(apih.M_Warehouse_ID,0) \n")
		.append("ORDER BY c.Fecha ASC \n");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{	
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, String.valueOf(Env.getAD_Client_ID(ctx)));
			pstmt.setString(2, fec);
			pstmt.setString(3, fec);
			pstmt.setInt(4, almacen_ID);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				interUsados = rs.getInt(1);

				//Fechas disponibles
				MEXMEIntervalosView m_fechas = new MEXMEIntervalosView();
				Calendar cal = Calendar.getInstance();
				cal.setFirstDayOfWeek(Calendar.SUNDAY);
				//Fechas con disponibilidad
				/*LRHU. Inicio. Validar que si la fecha que buscamos para programar un servicio esta ocupada por un almacen distinto al seleccionado
				 * esta este disponible para los demas almacenes.
				 */
				long dif = rs.getDate(2).getTime() - fechaAux;

				if(dif == 0){//LRHU.Si las fechas son iguales, setea los datos del resultset
					m_fechas.setFecha(Constantes.getSdfFecha().format(rs.getDate(2)));
					cal.setTime(rs.getDate(2));
				}else{//LRHU.Si la fecha del resultset excede uno o mas dias a fechaAux, entonces agregamos la fechaAux. Esto para que no se salte los dias.
					m_fechas.setFecha(Constantes.getSdfFecha().format(fechaAux));
					cal.setTime(new Date(fechaAux));
					interUsados = 0;
				}

				//Dia de la semana
				m_fechas.setDia(cal.get(Calendar.DAY_OF_WEEK));
				fechaAux += (24*60*60*1000);//LRHU.Incrementamos un dia la fecha para compararla con la siguiente del resultset
				//Fin. LRHU.

				if(turno!= null) {

					java.util.Date fechaMayor = null;
					java.util.Date fechaMenor = null;
					//los milisegundos
					long diferenciaMils = 0;

					switch (m_fechas.getDia()) {
					//LRHU. Se regresa el orden domingo es 1
					case 1: 
						fechaMayor = new Date(turno.getDom_Salida().getTime());
						fechaMenor = new Date(turno.getDom_Entrada().getTime());
						diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();
						break;

					case 2: 
						fechaMayor = new Date(turno.getLun_Salida().getTime());
						fechaMenor = new Date(turno.getLun_Entrada().getTime());
						diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();
						break;

					case 3: 
						fechaMayor = new Date(turno.getMar_Salida().getTime());
						fechaMenor = new Date(turno.getMar_Entrada().getTime());
						diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();
						break;

					case 4: 
						fechaMayor = new Date(turno.getMie_Salida().getTime());
						fechaMenor = new Date(turno.getMie_Entrada().getTime());
						diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();
						break;

					case 5: diferenciaMils = turno.getJue_Salida().getTime()-turno.getJue_Entrada().getTime();
					break;

					case 6: diferenciaMils = turno.getVie_Salida().getTime()-turno.getVie_Entrada().getTime();
					break;

					case 7: diferenciaMils = turno.getSab_Salida().getTime()-turno.getSab_Entrada().getTime();							
					break;
					}

					//obtenemos los segundos
					long segundos = diferenciaMils / 1000;
					//igual que el paso anterior
					long minutos = segundos /60;
					//Intervalos en minutos
					double interval = minutos/( intervaloAlm == 0 ? 1 : intervaloAlm );// 

					//Intervalos usados en el dia
					m_fechas.setIntervalUsados(interUsados);
					//num de intervalos configurados turnoSalida - turnoEntrada / interAlmacen
					m_fechas.setNumIntervalos((int)interval);
					//Intervalos disponibles por dia (numIntervalos-intervalUsados)
					interDisponibles = m_fechas.getNumIntervalos() - m_fechas.getIntervalUsados();
					m_fechas.setIntervalDisponibles(interDisponibles);

					list.add(m_fechas);						
					//Al tener todo disponible no buscamos mas dias posteriores para programar
					if(interDisponibles >= intervaloSol && interUsados == 0)//LRHU.Se agrega que los intervalos usados sean =0, por si no hay espacio disponible busque en la siguiente fecha.
						break;
				}
			}

			s_log.log(
					Level.FINEST, 
					"getIntervalosDisponibles : \n" + 
					sql.toString() + "\n " +
					"Parameters : " + fec + " - " + almacen_ID
			);

		} catch (Exception e)	{
			s_log.log(
					Level.SEVERE, 
					"getIntervalosDisponibles : \n" + 
					sql.toString() + "\n " +
					"Parameters : " + fec + " - " + almacen_ID, 
					e
			);
		} finally	{
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;

		}
		return list;

			}

	/**
	 * Horas ocupadas por solicitudes de productos
	 * @param ctx
	 * @param fecha
	 * @param intervalo
	 * @param trxName
	 * @return
	 */
	public static List  <MEXMEIntervalosView> getHorasOcupadas(Properties ctx, String fecha, int almacenID, int intervaloAlmacen, String trxName)
	{
		//Expert: LRHU. quitamos hh24:mi de 'dd/mm/yyyy hh24:mi' ya que la fecha viene en formato dd/mm/yyyy /*LRHU. Filtramos tambien por almacen*/
		List <MEXMEIntervalosView> list = new ArrayList <MEXMEIntervalosView> ();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT  to_char ( EXME_ActPacienteIndH.DatePromised , 'hh24:mi' ), EXME_ActPacienteIndH.Intervalo  ")
		.append(" FROM EXME_ActPacienteIndH  ")
		.append(" WHERE EXME_ActPacienteIndH.IsActive = 'Y'  ")
		.append(" AND EXME_ActPacienteIndH.Datepromised IS NOT NULL ")
		.append(" AND EXME_ActPacienteIndH.Intervalo IS NOT NULL ")
		.append(" AND to_date ( to_char ( EXME_ActPacienteIndH.DatePromised ,'dd/mm/yyyy' ), 'dd/mm/yyyy') ")
		.append(" = to_date ( ? , 'dd/mm/yyyy' ) ")
		.append(" AND EXME_ActPacienteIndH.M_Warehouse_ID = ?  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ActPacienteIndH"))
		.append(" ORDER BY EXME_ActPacienteIndH.DatePromised DESC  ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, fecha);
			pstmt.setInt(2, almacenID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEIntervalosView inter = new MEXMEIntervalosView();

				//Revisamos si se requiere mas de un intervalo para la orden de servicio
				int numDeIntervalos = 0;
				if(rs.getInt(2)==1){
					inter = new MEXMEIntervalosView();
					inter.setHora(rs.getString(1));
					inter.setNumIntervalos(rs.getInt(2)); //LRHU. Seteamos el numero de intervalos del resultset, ya que numDeIntervalos siempre valia 0
					inter.setOcupado(true);
					list.add(inter);
				} else {
					numDeIntervalos = rs.getInt(2); //LRHU. asignamos el numero de intervalos
					StringBuilder hora = new StringBuilder(20);
					hora.append("01/01/2009 ").append(rs.getString(1));
					//Parciamos la fecha para poder sumarles minutos
					Date fechaDate = sdfFechaHora.parse(hora.toString());

					//Se crea registro por intervalo de tiempo configurado para el almacen
					for (int i = 0; i < numDeIntervalos; i++) {
						inter = new MEXMEIntervalosView();
						inter.setHora(sdfHora.format(fechaDate));
						inter.setNumIntervalos(1);
						inter.setOcupado(true);
						list.add(inter);

						//agregar el intervalo
						Calendar cal = new GregorianCalendar();
						cal.setTimeInMillis(fechaDate.getTime());
						cal.add(Calendar.MINUTE, intervaloAlmacen);
						fechaDate = new Date(cal.getTimeInMillis()); 
					}
				}
			}

		} catch (Exception e)	{
			s_log.log(Level.SEVERE, "MEXMEFechasDisponibles.getHorasOcupadas", e);
		} finally	{
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}

		return list;

	}

	/**
	 * Productos ocupadas por solicitudes de productos
	 * @param ctx
	 * @param fecha
	 * @param intervalo
	 * @param trxName
	 * @return
	 */
	public static boolean equiposDisponibles(Properties ctx, String fechaIni, 
			String fechaFin, List  <? extends MEXMEActPacienteInd> lstServicios, String trxName) {

		String str_equipos = MEXMEEquipoH.getLstEquipos(ctx, lstServicios, trxName);

		boolean retVal = false;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_EquipoH  ")
		.append(" WHERE IsActive = 'Y'  ")
		.append(" AND Estatus_equipo IN ( 'M' , 'P' , 'S' )  ")
		.append(" AND EXME_Equipo_ID IN ( ").append(str_equipos).append(" ) ")
		.append(" AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  > ? ")//1
		.append("  AND to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' )  < ? ")//2
		.append(" AND ( ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ?  ")//3-4
		.append("  ) OR ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ?  ")//5-6
		.append("  ) OR ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ?  ")//7-8
		.append("  ) OR ( to_char ( Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ))  ")//9-10
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_EquipoH"))
		.append(" ORDER BY EXME_Equipo_ID DESC  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			//pstmt.setString(1, str_equipos);

			pstmt.setString(1, fechaIni);
			pstmt.setString(2, fechaFin);

			pstmt.setString(3, fechaIni);
			pstmt.setString(4, fechaFin);

			pstmt.setString(5, fechaIni);
			pstmt.setString(6, fechaFin);

			pstmt.setString(7, fechaIni);
			pstmt.setString(8, fechaFin);

			pstmt.setString(9, fechaIni);
			pstmt.setString(10, fechaFin);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				retVal = false;
			} else {
				retVal = true;
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEFechasDisponibles.equiposDisponibles", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}

		return retVal;
	}

	/**
	 * Productos ocupadas por solicitudes de productos
	 * @param ctx
	 * @param fecha
	 * @param intervalo
	 * @param trxName
	 * @return
	 */
	public static boolean equiposOcupados(Properties ctx, String fechaIni, 
			String fechaFin, int EXME_ActPacienteindH_ID, String trxName) {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_EquipoH.* FROM EXME_EquipoH   ")
		.append(" INNER JOIN EXME_ActPacienteInd api ON EXME_EquipoH.EXME_Equipo_ID = api.EXME_Equipo_id  ")
		.append(" WHERE EXME_EquipoH.IsActive= 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_EquipoH"))
		.append(" AND EXME_EquipoH.Estatus_equipo IN ( 'M' , 'P' , 'S' )    ")
		.append(" AND api.EXME_ActPacienteindH_ID = ?  ")
		.append(" AND to_char ( EXME_EquipoH.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  > ? ")
		.append(" AND to_char ( EXME_EquipoH.Fecha_Ini , 'dd/mm/yyyy hh24:mi' )  < ? ")
		.append(" AND ( ( to_char ( EXME_EquipoH.Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( EXME_EquipoH.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ) ")
		.append("   OR ( to_char ( EXME_EquipoH.Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( EXME_EquipoH.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ? ) ")
		.append("   OR ( to_char ( EXME_EquipoH.Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( EXME_EquipoH.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ? ) ")
		.append("   OR ( to_char ( EXME_EquipoH.Fecha_Ini , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( EXME_EquipoH.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ))  ")

		.append(" ORDER BY EXME_Equipo_ID DESC  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_ActPacienteindH_ID);

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
				return true;

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEFechasDisponibles.equiposOcupados", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}

		return false;

	}

	/**
	 * Se agrega para buscar el horario del almacen que aplica la orden de servicio.
	 * @param ctx
	 * @param fecha
	 * @param almacen
	 * @param trxName
	 * @return  Lista de Horario disponible para el almacen
	 * @author LRHU
	 */
	public static List<LabelValueBean> getHorarioAlmacen(Properties ctx, String fecha, long almacen, String trxName) 
	throws Exception {	

		Date dia = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String intervalo =  null;
		String hrInicio =  null;
		String hrFin = null;
		List <LabelValueBean> lstHoras = new ArrayList<LabelValueBean>();

		// Tomamos el intervalo de el almacen
		MWarehouse war = new MWarehouse(ctx,(int)almacen,null);
		if(war.getIntervalo()>0)
			intervalo = String.valueOf(war.getIntervalo());

		if(war.getEXME_Turnos2_ID() <= 0){
			throw new Exception("error.config.horario.almacen");
		}

		try{
			dia=sdf.parse(fecha);
		}catch (ParseException e){
			s_log.log(Level.SEVERE, "getHorarioAlmacen", e);
			dia=DB.getDateForOrg(ctx);
		}

		Calendar fecSel = Calendar.getInstance();
		fecSel.setTime(dia);
		fecSel.set(Calendar.HOUR_OF_DAY, 0);
		fecSel.set(Calendar.MINUTE,0);
		fecSel.set(Calendar.SECOND, 0);
		fecSel.set(Calendar.MILLISECOND, 0);
		//Escogemos el numero de dia necesario en el query de abajo
		int DIASeleccionado = fecSel.get(Calendar.DAY_OF_WEEK);
		//Y seteamos el valor que ve el usuario "dd/MM/yyyy"
		fecha = Constantes.getSdfFecha().format(dia);

		StringBuilder sql = new StringBuilder()
		.append(" SELECT Dom_Entrada,Dom_Salida, Lun_entrada,Lun_Salida,Mar_entrada,Mar_salida, ")
		.append(" Mie_entrada,Mie_Salida,Jue_Entrada,Jue_Salida, ")
		.append(" Vie_Entrada,Vie_Salida,Sab_Entrada,Sab_Salida ")
		.append(" FROM exme_turnos2 turnos ")
		.append(" INNER JOIN m_warehouse almacen ")
		.append(" ON (turnos.exme_turnos2_id = almacen.exme_turnos2_id) ")
		.append(" WHERE almacen.m_warehouse_id = ?");

		PreparedStatement prepa = null;
		ResultSet rs = null;
		try{
			prepa = DB.prepareStatement(sql.toString(),null);
			prepa.setInt(1, (int)almacen);
			rs = prepa.executeQuery();

			if(rs.next()){

				switch (DIASeleccionado) {
				case Calendar.SUNDAY:
					hrInicio = rs.getTime(1).toString();
					hrFin = rs.getTime(2).toString();
					break;

				case Calendar.MONDAY:
					hrInicio = rs.getTime(3).toString();
					hrFin = rs.getTime(4).toString();
					break;

				case Calendar.TUESDAY:
					hrInicio = rs.getTime(5).toString();
					hrFin = rs.getTime(6).toString();
					break;

				case Calendar.WEDNESDAY:
					hrInicio = rs.getTime(7).toString();
					hrFin = rs.getTime(8).toString();
					break;

				case Calendar.THURSDAY:
					hrInicio = rs.getTime(9).toString();
					hrFin = rs.getTime(10).toString();
					break;

				case Calendar.FRIDAY:
					hrInicio = rs.getTime(11).toString();
					hrFin = rs.getTime(12).toString();
					break;

				case Calendar.SATURDAY:
					hrInicio = rs.getTime(13).toString();
					hrFin = rs.getTime(14).toString();
				}

			}				
			DB.close(rs,prepa);

			if(hrInicio != null && hrFin != null){
				if(!hrInicio.equalsIgnoreCase(hrFin) && intervalo != null){

					//Manipulamos la cadena, ejemplo: cambiar de "16:15:00" a "1615"
					String hInicio = hrInicio.substring(0,2);
					String mInicio = hrInicio.substring(3,5);
					String hFin = hrFin.substring(0,2);
					String mFin = hrFin.substring(3,5);

					//Tomamos la hora actual del sistema.
					Calendar calTemp = Calendar.getInstance();
					String actual = (new SimpleDateFormat("HH:mm")).format(DB.getTimestampForOrg(ctx).getTime()); // Se le cambia a Fecha actual para que tome la zona horaria de la Org
					actual = actual.substring(0,2) + actual.substring(3,5);

					//Se setea a 0 para comparar con la fecha seleccionada
					calTemp.set(Calendar.HOUR_OF_DAY, 0);
					calTemp.set(Calendar.MINUTE, 0);
					calTemp.set(Calendar.SECOND,0);
					calTemp.set(Calendar.MILLISECOND, 0);

					/*Ciclo para llenar las listas de Hora Inicio y Hora Fin
				    Mientras la hora inicio sea menor que la hora final, repetir y a cada iteracion sumar el intervalo a la hora inicial*/
					for(String hora = hInicio+mInicio;Integer.valueOf(hora)< Integer.valueOf(hFin+mFin);){

						//Agregamos a la lista la hora disponible solo si es mayor o igual a la hora actual si
						//la fecha seleccionada es la igual a la fecha actual
						if(fecSel.compareTo(calTemp)==0){
							if(Integer.valueOf(hInicio+mInicio)>=Integer.valueOf(actual)){	
								lstHoras.add(new LabelValueBean(hInicio+":"+mInicio,hInicio+mInicio));							
							}
						}else {
							lstHoras.add(new LabelValueBean(hInicio+":"+mInicio,hInicio+mInicio));	
						}

						//Sumamos el intervalo entre los servicios
						mInicio= String.valueOf(Integer.valueOf(mInicio)+war.getIntervalo());

						if (Integer.valueOf(mInicio) >= 60){ //Si los minutos son mas de 60
							mInicio = String.valueOf(Integer.valueOf(mInicio)-60); //Le restamos 60
							hInicio = String.valueOf(1+ Integer.valueOf(hInicio));// Y le sumamos 1 hora
							if (Integer.valueOf(hInicio)<=9){ //Si es menor que 9 le concatenamos un 0 al principio
								hInicio = 0+hInicio;
							}
							if(Integer.valueOf(mInicio)<=9){ //Si es menor que 9 le concatenamos un 0 al principio
								mInicio = 0+mInicio;
							}
						}
						//Calculamos la nueva hora
						hora = hInicio+mInicio; 
					}

					List<MEXMEIntervalosView> lstHorasOcupadas = 
						MEXMEFechasDisponibles.getHorasOcupadas(
								ctx,
								fecha, 
								(int)almacen, 
								Integer.parseInt(intervalo), 
								trxName
						);		

					for (int j = 0; j < lstHoras.size() && !lstHorasOcupadas.isEmpty(); j++) {					

						//Hora de Agenda
						Date horaIntervalo = Constantes.getSdfHora24().parse(lstHoras.get(j).getLabel());

						//Existen horas ocupadas o todo el dia esta disponible
						if(lstHorasOcupadas!= null && !lstHorasOcupadas.isEmpty()) {
							//Iteramos las horas:minutos ocupadas para descartar
							for (int x = 0; x < lstHorasOcupadas.size(); x++) {

								MEXMEIntervalosView horaOcup = (MEXMEIntervalosView)lstHorasOcupadas.get(x);//LRHU
								//Hora ocupada
								Date horaOcupada = Constantes.getSdfHora24().parse(horaOcup.getHora());//LRHU.						
								//si la hora de agenda y la hora ocupada son iguales 
								//esa hora ya no esta disponibles
								if(horaIntervalo.equals(horaOcupada)){
									lstHoras.remove(j);
									break;
								}
							}
						}
					}
				}

			}//Fin horario de intervalos

		} catch(SQLException e) {
			s_log.log(Level.SEVERE, "getHorarioAlmacen", e);
			throw new Exception(e);
		} catch (ParseException e) {
			s_log.log(Level.SEVERE, "getHorarioAlmacen", e);
			throw new Exception(e);
		} finally {  			
			DB.close(rs,prepa);
			prepa = null;
			rs =null;
		}

		return lstHoras;
	}
}
