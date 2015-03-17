/**
 * 
 */
package org.compiere.process;


/**
 * @author Lorena Lama
 *
 */
public class MoverAgendaMedica{}
//extends SvrProcess {
//
//	/**
//	 * 
//	 */
//	public MoverAgendaMedica() {
//		super();
//	}
//
//	/* (non-Javadoc)
//	 * @see org.compiere.process.SvrProcess#prepare()
//	 */
//	@Override
//	protected void prepare() {
//	}
//	
//	private int medicoID = 0;
//	private int actividadID = 0;
//	
//
//	/* (non-Javadoc)
//	 * @see org.compiere.process.SvrProcess#doIt()
//	 */
//	@Override
//	protected String doIt() throws Exception {
//
//		//recuperamos los parametros del contexto
//		Properties ctx = Env.getCtx();
//		ResultSet rs = null;
//		MEXMEMedicoSust medicoSustituto = null;
//		List<MoveModel> lstTareas = new ArrayList<MoveModel>(); // tareas del medico			
//		List<MoveModel> lstError = new ArrayList<MoveModel>();  // tareas que no se pudieron mover
//		List<MoveModel> lstMoved = new ArrayList<MoveModel>();  // tareas a cambiar de medico
//		
//		try {
//			//Obtenemos la info del contexto
//			actividadID = getRecord_ID();
//			if(actividadID<=0)
//				throw new Exception(Msg.getMsg(getCtx(),"error.moveAgenda.activiyID"));
//			
//			//la actividad que ejecuta el proceso mover
//			MEXMEActividad actividad = new MEXMEActividad(Env.getCtx(),actividadID,null);
//			//rango de fechas que abarca la actividad
//			Date fechaRangoInicial = actividad.getStartDate();
//			Date fechaRangoFinal = actividad.getEndDate();
//			Date fechaActual = DB.getTimestampForOrg(Env.getCtx());
//			medicoID= actividad.getEXME_Medico_ID();
//			
//			//revisamos que la actividad, inicie o termine despues del dia actual
//			if (fechaRangoInicial.getTime() < fechaActual.getTime() && fechaRangoFinal.getTime() < fechaActual.getTime()) {
//				throw new Exception(Msg.getMsg(getCtx(),"error.moveAgenda.dateBefore"));
//			} 
//			
//			//buscamos el medico sustituto relacionado
//			List<MEXMEMedicoSust> lista = MEXMEMedicoSust.get(ctx, medicoID, 0, null);
//			
//			medicoSustituto = !lista.isEmpty() ? lista.get(0) : null;
//			//validamos los datos actuales del medico sustito guardado
//			if(medicoSustituto== null || !medicoSustituto.validate(false))
//				throw new Exception(Msg.getMsg(getCtx(),"error.moveAgenda.sustituto"));
//			
//			//Obtenemos el horario del medico sustituto
//			HashMap<String, String> horarioMedico = MEXMEMedico.getHorarioMap(getCtx(), medicoSustituto.getSubstitute_ID());
//			if (horarioMedico.isEmpty()) {
//				throw new Exception(Msg.getMsg(getCtx(),"error.moveAgenda.schedule"));
//			}
//			
//			//buscamos las tareas del medico para el rango de fechas correspondiente
//			rs = 
//				AgendaMedicaModel.getDetail(
//						ctx,
//						medicoID,
//						Constantes.getSdfFecha().format(fechaRangoInicial),
//						Constantes.getSdfFecha().format(fechaRangoFinal)
//				); //modifica SQL
//
//			boolean error = false;
//			//creamos las listas
//			if(rs == null)
//				throw new Exception(Msg.getMsg(getCtx(),"error.moveAgenda.noEvents"));
//			
//			while(rs.next()){
//				//excluyendo la actividad guardada
//				if(rs.getInt("llave")==actividadID)
//					continue;
//				MoveModel record = new MoveModel();
//				record.setRecordID(rs.getInt("llave"));
//				record.setRecordType(rs.getString("tipo"));
//				record.setStartDate(rs.getTimestamp("fechaini"));
//				record.setEndDate(rs.getTimestamp("fechafin"));
//				
//				if(record.getRecordType().equals("A"))
//					error = true;  // las actividades no se mueven
//				else if(record.getRecordType().equals("C")){
//					//validamos que la actividad corresponda a la especialidad del medico sustituto
//					if(!validEspecialidad(medicoSustituto.getSubstitute_ID(), rs.getInt("key")))
//						error = true;
//				} 
//				if(error)
//					lstError.add(record);
//				else	
//					lstTareas.add(record);
//			}
//
//			if(lstTareas.isEmpty()){
//				if(lstError.isEmpty())
//					throw new Exception(Msg.getMsg(getCtx(),"error.moveAgenda.noEvents"));
//				else
//					throw new Exception(Msg.getMsg(getCtx(),"error.moveAgenda.invalidEvents"));
//			}
//			fechaRangoInicial = lstTareas.get(0).getStartDate(); //primer fecha de las actividades
//			fechaRangoFinal = lstTareas.get(lstTareas.size()-1).getEndDate(); //ultima fecha de las actividades
//			
//			//para cada dia contenido en el rango de fechas
//			Calendar fechaRango = Calendar.getInstance();
//			fechaRango.setTime(fechaRangoInicial); // setea el calendario con la fecha inicial.
//
//			//solo para el rango de dias que abarca la actividad
//			while (fechaRango.getTimeInMillis() <= fechaRangoFinal.getTime()) {
//				// Obtenmos las horas disponibles del medico sustituto
//				HashMap<String, List<String>> horasDesocupadas = Programador.getHorasdisponibles(ctx, horarioMedico, fechaRango.getTime(), 0);
//				List<String> lstHorasIni = horasDesocupadas.get("Horaini"); //hora inicial de espacio libre
//				List<String> lstHorasFin = horasDesocupadas.get("Horafin"); //hora final para cada espacio libre
//				
//				//si no hay horas disponibles para ese dia
//				if(lstHorasIni.isEmpty()){
//					// movemos las actividades de ese dia a la lista de error
//					notMoved(lstError,fechaRango,lstTareas);
//				} else {
//					// iteramos entre los bloques de horas disponibles
//					for (int i = 0; i < lstHorasIni.size(); i++) {
//						//iteramos para cada actividad
//						for (int j = 0; j < lstTareas.size(); j++) {							
//							if(!Constantes.getSdfFecha().format(fechaRango.getTime()).equals(Constantes.getSdfFecha().format(lstTareas.get(j).getStartDate())))
//								continue;
//							//comparamos la hora de inicio de la actividad con el espacio disponible del medico, si coincide en #dia y hora
//							if (lstHorasIni.get(i).compareTo(Constantes.getSdfHora24().format(lstTareas.get(j).getStartDate())) <= 0
//									&& lstHorasFin.get(i).compareTo(Constantes.getSdfHora24().format(lstTareas.get(j).getEndDate())) >= 0) {
//
//								if(lstTareas.get(j).getRecordType().equals("C")){
//									MEXMECitaMedica cita = new MEXMECitaMedica(ctx,lstTareas.get(j).getRecordID(),null);
//									if(cita.getEXME_CitaMedica_ID()<=0)
//										continue;
//									cita.setEXME_Medico_ID(medicoSustituto.getSubstitute_ID());
//									if(!cita.save()){
//										remove(lstTareas,lstError,j);
//										continue;
//									}
//								} else {
//									MProgQuiro progQuiro = new MProgQuiro(ctx,lstTareas.get(j).getRecordID(),null);
//									if(progQuiro.getEXME_ProgQuiro_ID()<=0)
//										continue;
//									progQuiro.setEXME_Medico_ID(medicoSustituto.getSubstitute_ID());
//									if(!progQuiro.save()){
//										remove(lstTareas,lstError,j);
//										continue;
//									}
//								}
//								// asignamos el nuevo medico al evento, y cambiamos la lista de horas disponibles
//								lstHorasIni.set(i,Constantes.getSdfHora24().format(lstTareas.get(j).getEndDate()));
//								add(lstTareas,lstMoved,j);
//								--j;
//							} else {
//								//crea un mensaje de error, indicando las actividaddes que no se pudieron mover
//								remove(lstTareas,lstError,j);
//								--j;
//							}
//						}
//					}
//				}
//				// le suma un dia a la fecha del rango a evaluar
//				fechaRango.add(Calendar.DATE, 1);
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "Processing error ", e);
//			//throw new Exception ("doIt", e);
//			addLog (0, null, null, "@Errors@ - "+ e.getMessage());
//		} finally {
//			if(rs!=null)
//				rs.close();
//		}
//		
//		addLog (0, null, null, "@Substitute_ID@ - "+medicoSustituto.getMedico(false).getNombre_Med());
//		//barremos las actividades no actualizadas
//		for (int i = 0; i < lstError.size(); i++) {
//			addLog (0, null, null, "@Errors@ - "+Constantes.getSdfFechaHora().format(lstError.get(i).getStartDate()));
//		}
//		
//		for (int i = 0; i < lstMoved.size(); i++) {
//			addLog (0, null, null, "@Updated@ - "+Constantes.getSdfFechaHora().format(lstMoved.get(i).getStartDate()));
//		}
//	
//		return "";
//	}
//
//	private void notMoved(List<MoveModel> lstError, Calendar fechaRango, List<MoveModel> lstTareas) throws Exception {
//		try {
//			// iteramos para cada actividad
//			for (int j = 0; j < lstTareas.size(); j++) {
//				// valida solo para los registros del mismo dia
//				Timestamp fecha = 
//					new Timestamp(lstTareas.get(j).getStartDate().getTime());
//				
//				if (!Constantes.getSdfFecha().format(fechaRango.getTime()).equals(
//						Constantes.getSdfFecha().format(fecha)))
//					continue;
//				remove(lstTareas,lstError,j);
//				--j;
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "", e);
//			throw e;
//		}
//	}
//	
//	private void remove(List<MoveModel> lstTareas,List<MoveModel> lstError,int j) {
//		lstError.add(lstTareas.get(j));
//		lstTareas.remove(j); //las quitamos de la lista
//	}
//	
//	private void add(List<MoveModel> lstTareas,List<MoveModel> lstMoved,int j) {
//		lstMoved.add(lstTareas.get(j));
//		lstTareas.remove(j); //las quitamos de la lista
//	}
//	
//	public boolean validEspecialidad(int substituteID, int especialidadID) {
//		StringBuilder sql = new StringBuilder("SELECT EXME_MedicoEsp_ID FROM ")
//		.append(" EXME_MedicoEsp WHERE EXME_Medico_ID = ? AND EXME_Especialidad_ID = ? ");		
//		int count = DB.getSQLValue(get_TrxName(),sql.toString(),substituteID,especialidadID);		
//		if(count<=0){
//			return false;
//		}
//		return true;
//	}
//	
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
//}
