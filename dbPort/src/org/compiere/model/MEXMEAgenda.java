package org.compiere.model;


/** @deprecated will be removed */
public class MEXMEAgenda {
	
//	/**	Static Logger				*/
//	private static CLogger		s_log = CLogger.getCLogger (MEXMEAgenda.class);
//	
//	//private static SimpleDateFormat sdfHora  = new SimpleDateFormat("HH:mm");//Constantes.sdfHora24
//	//public static SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");//Constantes sdfFechaHora
//	
//	/**
//	 * 
//	 * @param ctx
//	 * @param fecha
//	 * @param almacen_Id
//	 * @param lstIndicaciones
//	 * @param trxName
//	 * @return
//	 */
//	public String disponibilidadFechaHora(Properties ctx, String fecha, int almacen_Id, List<? extends MActPacienteInd> lstIndicaciones,
//			String trxName)
//	{
//		//LRHU. Para separar la fecha y la hora ya que la fecha viene en formato dd/MM/yy hh:mm
//		//String fec = fecha.substring(0,10);
//		//String hora = fecha.substring(11,16);
//		String fechaDisponible = fecha.substring(0,10);
//		String horaDisponible = "";
//		boolean fechaEncontrada = false;
//		int  numInterSolicitud = lstIndicaciones.size();
//		try
//		{
//			//Almacen que se filtra en el opcion
//			MWarehouse almacen = new MWarehouse(ctx, almacen_Id, trxName);
//			
//			//Fechas con suficientes intervalos disponibles apartir de la fecha de solicitud
//			//LRHU. Se envia la fecha en formato completo dd/mm/yyyy hh:mm
//			List<MEXMEIntervalosView> lstFechas = MEXMEFechasDisponibles.getIntervalosDisponibles(
//					ctx, fecha, numInterSolicitud, almacen.getIntervalo(), 
//					almacen_Id, almacen.getEXME_Turnos2_ID(), trxName);
//
//			//iteramos las fechas posibles de programacion
//			for (int i = 0; i < lstFechas.size(); i++) 
//			{
//
//				//Fecha disponible de iteracion
//				MEXMEIntervalosView m_fechaDisponible = (MEXMEIntervalosView)lstFechas.get(i);
//				fechaDisponible =  m_fechaDisponible.getFecha();
//				
//				//Obtenemos el arreglo de horas del dia para agendar
//				List<MEXMEIntervalosView> lstIntervalos = getIntervalos(ctx, m_fechaDisponible.getFecha() ,  
//						almacen.getIntervalo() ,  horaInicio (ctx, almacen.getEXME_Turnos2_ID(), m_fechaDisponible.getDia(), trxName) , 
//						m_fechaDisponible.getNumIntervalos() , trxName);
//
//				//De los horarios de la agenda x dia obtenemos cuales estan ocupados /*LRHU. Filtramos tambien por almacen*/
//				lstIntervalos = horariosOcupados(ctx, lstIntervalos, m_fechaDisponible, almacen_Id, almacen.getIntervalo(), 
//						trxName);
//
//
//				int cont = 0;
//				//Buscamos los intervalos seguidos requeridos
//				for (int j = 0; j < lstIntervalos.size(); j++) 
//				{
//
//					MEXMEIntervalosView horario = (MEXMEIntervalosView)lstIntervalos.get(j);
//					//LRHU. Buscar Fecha disponible a partir de la fecha y hora dadas.
//					if(Constantes.getSdfFechaHora().parse(fechaDisponible + " " + horario.getHora()).getTime() >= Constantes.getSdfFechaHora().parse(fecha).getTime()){
//								if(!horario.isOcupado())
//								{
//									cont++;
//								}
//								else
//								{
//									cont=0;
//									horaDisponible = "";
//								}
//
//								//Cuando inicia el contador es el primer intervalo
//								if(cont == 1)
//									horaDisponible = horario.getHora();
//					
//								//cuando el intervalos es igual a lo solicitados tenemos la fecha
//								if(cont == numInterSolicitud)
//								{
//									fechaDisponible = m_fechaDisponible.getFecha();
//									fechaEncontrada =  true;
//									break;
//								}
//						}
//				}
//				
//				//Terminar ciclo de fechas
//				if(fechaEncontrada)
//				{
//					//Fecha inical
//					String fechaIni = fechaDisponible+" "+horaDisponible;
//					//Minutos
//					int minutos = numInterSolicitud*almacen.getIntervalo() ;
//					GregorianCalendar calendar = new GregorianCalendar();
//					calendar.setTime(Constantes.getSdfFechaHora().parse(fechaIni));
//					calendar.add(Calendar.MINUTE, minutos);
//					
//					//Buscamos equipo en fecha y hora
//					if(MEXMEFechasDisponibles.equiposDisponibles(ctx, fechaIni, 
//							Constantes.getSdfFechaHora().format(calendar.getTime()), lstIndicaciones, trxName))
//						//Encontramos la fecha y hora
//						break;
//				}
//			}//Fin fechas
//
//		}
//		catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		}
//		
//		return fechaDisponible+" "+horaDisponible;
//	}
//	
//	/**
//	 * 
//	 * @param ctx
//	 * @param fecha              Fecha del dia a dividir en intervalos
//	 * @param intervaloAlmacen   Intervalo configurado para el almacen
//	 * @param horaInicio		 Primera hora de servicio (Turno)
//	 * @param intervalosTurno    numero de intervalos deacuerdo al horario del almacen
//	 * @param trxName
//	 * @return
//	 */
//	public List<MEXMEIntervalosView> getIntervalos(Properties ctx, String fecha,  
//			int intervaloAlmacen, Timestamp horaInicio,
//			int intervalosTurno, String trxName)
//	{
//		List<MEXMEIntervalosView> horas = new ArrayList<MEXMEIntervalosView>();
//		try
//		{
//			//Intervalo configurado para el almacen
//			int intervalo = intervaloAlmacen;
//			//numero de intervalos deacuerdo al horario del almacen
//			int intervalos = intervalosTurno;
//			
//			if(horaInicio!=null){
//				//a la fecha se le concatena la hora del primer intervalo
//				fecha = fecha+" "+Constantes.getSdfHora24().format(horaInicio);
//	
//				//Parciamos la fecha para poder sumarles minutos
//				Date fechaDate = Constantes.getSdfFechaHora().parse(fecha);
//	
//				//Debe realizar una cadena con todas las fechas posibles
//				for (int i = 0; i < intervalos; i++) {
//					//Debe ser la fecha  mas la hora
//					MEXMEIntervalosView vista  = new MEXMEIntervalosView();
//					vista.setHora(Constantes.getSdfHora24().format(fechaDate));
//					horas.add(vista);
//					//agregar el intervalo
//					Calendar cal = new GregorianCalendar();
//					cal.setTimeInMillis(fechaDate.getTime());
//					cal.add(Calendar.MINUTE, intervalo);
//					fechaDate = new Date(cal.getTimeInMillis()); 
//					//fecha = Constantes.sdfFechaHora.format(fechaDate);
//				}
//			}
//		}catch (Exception e) 
//		{
//			s_log.log(Level.SEVERE, "get", e);
//		}
//		
//		return horas;
//	}
//
//	/**
//	 * Obtencio del horario correspondiente al dia
//	 * @param ctx
//	 * @param almacen_Id
//	 * @param dia
//	 * @param trxName
//	 * @return
//	 */
//	public Timestamp horaInicio (Properties ctx, int turno_Id, 
//			int dia, String trxName)
//	{
//		Timestamp horaInicio = null;
//		MEXMETurnos2 turno = new MEXMETurnos2(ctx, turno_Id, trxName);
//		if(turno!=null && turno.getEXME_Turnos2_ID()>0){
//			switch (dia) 
//			{
//			//LRHU. se regresa el orden, domingo es 1
//				case 1: horaInicio = turno.getDom_Entrada();
//				break;
//				case 2: horaInicio = turno.getLun_Entrada();
//				break;
//				case 3:horaInicio = turno.getMar_Entrada();
//				break;
//				case 4:horaInicio = turno.getMie_Entrada();
//				break;
//				case 5:horaInicio = turno.getJue_Entrada();
//				break;
//				case 6:horaInicio = turno.getVie_Entrada();
//				break;
//				case 7:horaInicio = turno.getSab_Entrada();
//				break;			
//			}
//		}
//		return horaInicio;
//	}
//	
//	/**
//	 * Marcacion de horarios ocupados
//	 * @param ctx
//	 * @param lstIntervalos
//	 * @param m_fechaDisponible
//	 * @param intervalAlm
//	 * @param trxName
//	 * @return
//	 */
//	public List<MEXMEIntervalosView> horariosOcupados(Properties ctx, List<MEXMEIntervalosView> lstIntervalos, 
//			MEXMEIntervalosView m_fechaDisponible, int almacenID, int intervalAlm, 
//			String trxName)
//	{
//		try
//		{
//			//Obtenemos el arreglo de horas del dia ocupadas de las horas del dia para agendar /*LRHU. Filtramos tambien por almacen*/
//			List<MEXMEIntervalosView> lstHorasOcupadas = MEXMEFechasDisponibles.getHorasOcupadas(ctx, 
//					m_fechaDisponible.getFecha(), almacenID, intervalAlm, trxName);
//			
//			for (int j = 0; j < lstIntervalos.size(); j++)
//			{
//				MEXMEIntervalosView m_horaIntervalo = (MEXMEIntervalosView)lstIntervalos.get(j);
//	
//				//Hora de Agenda
//				Date horaIntervalo = Constantes.getSdfHora24().parse(m_horaIntervalo.getHora());
//				
//				//Existen horas ocupadas o todo el dia esta disponible
//				if(lstHorasOcupadas!= null && lstHorasOcupadas.size()>0)
//				{
//					//Iteramos las horas:minutos ocupadas para descartar
//					for (int x = 0; x < lstHorasOcupadas.size(); x++)
//					{MEXMEIntervalosView horaOcup = (MEXMEIntervalosView)lstHorasOcupadas.get(x);//LRHU
//						//Hora ocupada
//						Date horaOcupada = Constantes.getSdfHora24().parse(horaOcup.getHora());//LRHU.						
//						//si la hora de agenda y la hora ocupada son iguales 
//						//esa hora ya no esta disponibles
//						if(horaIntervalo.equals(horaOcupada))
//						{
//							//Agregar a la lista que intervalo esta ocupado
//							((MEXMEIntervalosView)lstIntervalos.get(j)).setOcupado(true);
//							break;
//						}
//					}
//				}
//				else
//				{
//					lstIntervalos.get(j).setOcupado(false);
//				}
//			}//Fin horario de intervalos
//		}
//		catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		}
//		return lstIntervalos;
//	}
//	
//	
//	/**
//	 * Devolvemos una lista con el id y el nombre de los
//	 * equipos relacionados a un quirofano determinado, 
//	 *
//	 * @param xxQuirofanoId El quirofano a consultar
//	 * @param ctx El contexto de la aplicacion
//	 * @return Un valor ArrayList con los equipos del quirofano
//	 * @throws Exception en caso de ocurrir un error al procesar
//	 * la consulta o no existir equipos relacionados con el
//	 * quirofano.
//	 *///PARA RAUL
//	public static boolean hayDisponibilidadFehaHoraEspec(Properties ctx, int EXME_ActPacienteIndH_ID, 
//			String fechaIni, String fechaFin, 
//			String trxName) {
//		
//	    StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		//ArrayList<MEXMEIntervalosView> sol = new ArrayList<MEXMEIntervalosView>();
//		
//	    try {
//
//			sql.append("  SELECT EXME_ActPacienteInd.EXME_ActPacienteInd_ID   ")
//				.append(" FROM EXME_ActPacienteInd   ")
//				.append(" INNER JOIN EXME_ActPacienteIndH apih ON EXME_ActPacienteInd.EXME_ActPacienteIndH_ID = apih.EXME_ActPacienteIndH_ID   ")
//				.append(" WHERE EXME_ActPacienteInd.IsActive = 'Y' ")
//				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_EqQuirofano"))
//				.append(" AND EXME_ActPacienteInd.Estatus IS NOT NULL  ")
//
//				.append(" AND EXME_ActPacienteInd.Estatus IN ('S','P')    ")
//				.append(" AND EXME_ActPacienteInd.EXME_ActPacienteIndH_ID <> ?  ")
//				.append(" AND EXME_ActPacienteInd.DatePromised IS NOT NULL   ")
//				.append(" AND apih.DatePromised IS NOT NULL   ")
//				.append(" AND apih.Fecha_Fin IS NOT NULL   ")
//				.append(" AND to_char ( apih.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  > ? ")
//				.append(" AND to_char ( apih.DatePromised , 'dd/mm/yyyy hh24:mi' )  <  ?  ")
//				.append(" AND (( to_char ( apih.DatePromised , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( apih.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ) ")
//				.append("  OR  ( to_char ( apih.DatePromised , 'dd/mm/yyyy hh24:mi' ) <= ? AND to_char ( apih.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ? ) ")
//				.append("  OR  ( to_char ( apih.DatePromised , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( apih.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  <= ? ) ")
//				.append("  OR  ( to_char ( apih.DatePromised , 'dd/mm/yyyy hh24:mi' ) >= ? AND to_char ( apih.Fecha_Fin , 'dd/mm/yyyy hh24:mi' )  >= ? ) ")
//				.append(" ) ORDER BY EXME_ActPacienteInd.EXME_ActPacienteInd_ID ");
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1,EXME_ActPacienteIndH_ID);
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
//			rs = pstmt.executeQuery();
//			
//			//Si regresa tuplas esta ocupado el rango de tiempo
//			if(!rs.next()) {
//				//Buscamos equipo en fecha y hora
//				if(!MEXMEFechasDisponibles.equiposOcupados(ctx, fechaIni, 
//						fechaFin, EXME_ActPacienteIndH_ID, trxName))
//					//Encontramos la fecha y hora
//					return true;
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.FINE, "getEquiposQuiro", e);
//		} finally {
//			DB.close(rs,pstmt);
//            rs = null;
//            pstmt = null;
//        }
//		
//		return false;
//		
//	}
}
