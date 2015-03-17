package org.compiere.model;


public class MBPPlanMed {

//	/**	Static Logger				*/
//	private static CLogger		s_log = CLogger.getCLogger (MBPPlanMed.class);
//
//	private int liDecimales = 2;
//
//	public int getLiDecimales() {
//		return liDecimales;
//	}
//	public void setLiDecimales(int liDecimales) {
//		this.liDecimales = liDecimales;
//	}
//
//	/*	
//	private List<MPlanMed> lstProgMed = null;
//	
//	public List<MPlanMed> getLstProgMed() {
//		return lstProgMed;
//	}
//	public void setLstProgMed(List<MPlanMed> lstProgMed) {
//		this.lstProgMed = lstProgMed;
//	}
//	
//	private List<List<MPlanMedLine>> lstProgMedLine = null;
//	
//	public List<List<MPlanMedLine>> getLstProgMedLine() {
//		return lstProgMedLine;
//	}
//	public void setLstProgMedLine(List<List<MPlanMedLine>> lstProgMedLine) {
//		this.lstProgMedLine = lstProgMedLine;
//	}
//	*/
//	public MProduct m_product = null;
//	
//	public MProduct getM_product() {
//		return m_product;
//	}
//	public void setM_product(MProduct mProduct) {
//		m_product = mProduct;
//	}
//	
//	public static final String AM   = "AM";
//	public static final String PM   = "PM";
//	public static final String MINUTOS = "MI";
//	public static final String HORAS   = "HR";
//	public static final String DIAS    = "DI";
//	public static final String SEMANAS = "SE";
//	
//	/**
//	 * Contexto
//	 */
//	public Properties ctx = null;
//	
//	/**
//	 * Lista de productos apartir de las pescripciones seleccionadas
//	 *
//	public List<MPlanMed> lstProductos = null;
//	
//	/**
//	 * Plan de medicamento a modificar
//	 *
//	public List<MPlanMed> lstPlanMed = null;
//	
//	/**
//	 * Nombre de transaccion
//	 */
//	public String trxName = null;
//	
//	private String endDate = null;
//	private double qtyTotPlanned = 0.0;
//	private List<MPlanMedLine> lstCalculo = null;
//	
//	
//	public String getEndDate() {
//		return endDate;
//	}
//	public void setEndDate(String endDate) {
//		this.endDate = endDate;
//	}
//	public double getQtyTotPlanned() {
//		return qtyTotPlanned;
//	}
//	public void setQtyTotPlanned(double qtyTotPlanned) {
//		this.qtyTotPlanned = qtyTotPlanned;
//	}
//	public List<MPlanMedLine> getLstCalculo() {
//		return lstCalculo;
//	}
//	public void setLstCalculo(List<MPlanMedLine> lstCalculo) {
//		this.lstCalculo = lstCalculo;
//	}
//	
//	private java.util.Date startDateValue = null;
//	
//	public java.util.Date getStartDateValue() {
//		return startDateValue;
//	}
//	public void setStartDateValue(java.util.Date startDateValue) {
//		this.startDateValue = startDateValue;
//	}
//	/**
//	 * Constructor
//	 * @param paramCtx Contexto
//	 * @param paramTrxName Nombre de transaccion
//	 */
//	public MBPPlanMed(Properties paramCtx, String paramTrxName){
//		this.ctx = paramCtx;
//		this.trxName = paramTrxName;
//	}
//
//	/**
//	 * 
//	 * @param paramLstProductos Lista de productos a agregar a la planeacion
//	 * @param paramLstPlanMed Lista de productos ya planeados
//	 */
//	public List<LabelValueBean> planApartirDeReceta(
//			List<ServicioView> lstProgMedNew,
//			MEXMECtaPac ctapac, int EXME_Especialidad_ID,
//			int EXME_Medico_ID, boolean paramIsEdit,
//			List<MPlanMed> lstProgMed, 
//			ArrayList<List<MPlanMedLine>> lstProgMedLine,int formaIndex
//	){
//
//		try {
//
//			List<LabelValueBean> lstErros = new ArrayList<LabelValueBean>();
//			//Iterar todos los productos de la receta 
//			for (int i = 0; i < lstProgMedNew.size(); i++) {
//
//				//ServicioView 
//				MPlanMed lineaReceta = new MPlanMed(this.ctx, 0, this.trxName);
//
//				lineaReceta.copyFromServicioView(lstProgMedNew.get(i), lineaReceta, 
//						ctapac.getEXME_CtaPac_ID(), EXME_Especialidad_ID, 
//						EXME_Medico_ID);
//
//
//				//Buscamos los datos necesarios para convertir el
//				//objeto ServicioView a MPlanMed y MPlanMedLine
//				int productoId = lineaReceta.getM_Product_ID();
//				int almacenId = lineaReceta.getM_Warehouse_ID();
//				int interval = lineaReceta.getIntervalo();
//				int period = lineaReceta.getDuracion(); 
//
//				//TODO: es un double 
//				int qtyPlanned = lineaReceta.getQtyPlanned().intValue();
//				String hora = lineaReceta.getHora(); 
//				String startDate = Constantes.getSdfFecha().format(lineaReceta.getStartDate()); 
//				String min = lineaReceta.getMin();
//				String ampm = lineaReceta.getAmpm(); 
//				String periodsUOM = lineaReceta.getUOMDuracion();
//				String intervalUOM = lineaReceta.getUOMIntervalo();
//
//
//				String productoNombre = lineaReceta.getProduct().getName();
//
//				LabelValueBean errors = null;
//				if(productoId>0){
//					errors = find(productoId, almacenId, productoNombre, ctapac, lstProgMed);
//					if(errors!=null){
//						lstErros.add(errors);
//						return lstErros;
//					}
//
//					calcular(productoId, interval, period, qtyPlanned, hora, 
//							startDate, min, ampm, periodsUOM, intervalUOM);
//
//					addLine(paramIsEdit, lstProgMed, 
//							lstProgMedLine, 
//							formaIndex, getLstCalculo(),
//							lineaReceta.getDescription(), ctapac.getEXME_CtaPac_ID(), almacenId, 
//							productoId, lineaReceta.getC_UOM_ID(), EXME_Medico_ID, EXME_Especialidad_ID, 
//							getStartDateValue(), lineaReceta.getQtyPlanned().intValue(), (int)getQtyTotPlanned(), 
//							this.liDecimales, interval, intervalUOM, 
//							period, periodsUOM, getEndDate());
//				}
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//	
//	/**
//	 * busquedas utilizadas en la forma
//	 *
//	 * @param mapping El ActionMapping empleado para seleccionar esta instancia
//	 * @param actionForm El bean ActionForm opcional para esta solicitud
//	 * @param request La solicitud HTTP que estamos procesando
//	 * @param response La respuesta HTTP que estamos creando
//	 */
//	public LabelValueBean find(int productID, int almacenId, String productoNombre, 
//			MEXMECtaPac ctapac, List<MPlanMed> paramLstPlanMed) {
//
//		try {
//			//El productId deber� cumplir las condiciones del metodo 
//			//Datos.getProductID(ctx, forma.getProductValue(), forma.getM_Warehouse_ID(),
//			//"I", " AND tp.value IN ('MATERIAL DESCARTABLE','MEDICAMENTO') AND p.IsSold='Y' ");
//
//			m_product = new MProduct(ctx, (int) productID, null);  //no es necesaria la transacci�n --Miguel Loera--
//
//			if(!MEXMEProductClassWhs.validProductWarehouse(ctx, almacenId, productID, null)){
//				return new LabelValueBean("error.traspasos.noExisteProd",productoNombre);
//			}
//
//			// Validamos que el paciente no sea alergico al medicamento
//			if (MEXMEPacienteAler.esAlergico(ctx, m_product.getM_Product_ID(), ctapac.getEXME_Paciente_ID())) {
//				return new LabelValueBean("error.progMed.alergico","");
//			}
//
//			// Validamos que el producto no este programado para el paciente y con el mismo estatus (CO).- Lama
//			if (MPlanMed.programado(ctx, ctapac.getEXME_CtaPac_ID(), m_product.getM_Product_ID(), null)) { 
//				return new LabelValueBean("error.progMed.programado","");
//			}
//
//			//El producto {0} ya se hab\u00EDa agregado a la lista.
//			if (alreadyAdded(m_product.getM_Product_ID(), paramLstPlanMed)) {
//				return new LabelValueBean("error.existeProd", m_product.getValue());
//			}
//
//			/*forma.setM_Product_ID(producto.getM_Product_ID());
//				forma.setProductValue(producto.getValue());
//				forma.setProductName(producto.getName());
//				forma.setLstUOM(Datos.getProdUOM(producto.getM_Product_ID(), ctx));
//			 */
//
//		} catch (Exception e) {			
//
//		} 
//		return null;
//	}
//
//	public boolean alreadyAdded(int product_ID, List<MPlanMed> paramLstPlanMed) {
//
//		for (int i = 0; i < paramLstPlanMed.size(); i++) {
//			MPlanMed plan = paramLstPlanMed.get(i);
//			if(plan.getM_Product_ID()==product_ID)
//				return true;
//		}
//
//		return false;
//	}
//	/**
//	 * Realiza los calculos de tiempo para el plan de medicamentos
//	 *
//	 * @param mapping El ActionMapping empleado para seleccionar esta instancia
//	 * @param actionForm El bean ActionForm opcional para esta solicitud
//	 * @param request La solicitud HTTP que estamos procesando
//	 * @param response La respuesta HTTP que estamos creando
//	 */
//	public List<LabelValueBean> calcular(int M_Product_ID, int interval, int period, int qtyPlanned,
//			String hora, String startDate, String min, String ampm, String periodsUOM,
//			String intervalUOM) {
//		s_log.log(Level.INFO, "***** calcular ***** ");
//		List<LabelValueBean> errores = new ArrayList<LabelValueBean>();
//	
//		List<MPlanMedLine> resultados = new ArrayList<MPlanMedLine>();
//
//
//		LabelValueBean errors = validateToCalculate(M_Product_ID, interval, period, qtyPlanned);
//		if(errores!=null){
//			errores.add(errors);
//			return errores;
//		}
//
//		try {
//			
//			//Fecha y hora de programaci�n
//			Calendar cal = Calendar.getInstance();
//			Calendar cal2 = Calendar.getInstance();
//			
//			//Intervalo sobre la programacion, tecleado por el usuario
//			int intervalo = interval;
//			
//			//Periodo a cumplir, tecleado por el usuario
//			int periodo = period;
//			
//			//Siempre truncamos la cantidad a enteros.
//			BigDecimal cantidad = new BigDecimal(qtyPlanned);
//			
//			
//			String lcQtyPlanned = String.valueOf(qtyPlanned);
//			String lcDecimales = lcQtyPlanned.substring(lcQtyPlanned.indexOf("."),lcQtyPlanned.length());
//			liDecimales = lcDecimales.length()-1;
//			
//			String am_pb = hora;
//			if ( hora.trim().equalsIgnoreCase("12"))
//				am_pb = "00";
//			
//			cal.setTime(Constantes.getSdfFecha().parse(startDate));//22/04/2009   Wed Apr 22 00:00:00 GMT-05:00 2009
//			cal.set(Calendar.HOUR,Constantes.getDosDigitos().parse(am_pb).intValue());//12
//			cal.set(Calendar.MINUTE,Constantes.getDosDigitos().parse(min).intValue());//45
//			cal.set(Calendar.AM_PM, ampm.equalsIgnoreCase(AM) ? Calendar.AM : Calendar.PM);
//			java.util.Date fechaIni = Constantes.getSdfFechaHoraAmPm().parse(Constantes.getSdfFechaHoraAmPm().format(cal.getTime()));//cal.get(Calendar.AM_PM);
//		
//			setStartDateValue(fechaIni);//Wed Apr 22 12:45:00 GMT-05:00 2009
//			
//			BigDecimal qtyTotPlanned = Env.ZERO;
//			
//			int _periodo = 0;
//			if(periodsUOM.equalsIgnoreCase(HORAS))
//				_periodo = periodo * Constantes._HR;
//			else if(periodsUOM.equalsIgnoreCase(DIAS))
//				_periodo = periodo * Constantes._DIA;
//			else if(periodsUOM.equalsIgnoreCase(SEMANAS))
//				_periodo = periodo * Constantes._SEM;
//			else
//				errores.add(new LabelValueBean("Periodo Incorrecto",""));
//			
//			int _intervalo = 0;
//			if(intervalUOM.equalsIgnoreCase(MINUTOS))
//					_intervalo = intervalo * Constantes._MIN;
//			else if(intervalUOM.equalsIgnoreCase(HORAS))
//					_intervalo = intervalo * Constantes._HR;
//			else if(intervalUOM.equalsIgnoreCase(DIAS))
//					_intervalo = intervalo * Constantes._DIA;
//			else
//				errores.add(new LabelValueBean("Intervalo Incorrecto",""));
//			
//			cal2.add(Calendar.DATE, _periodo/Constantes._DIA);
//			
//			try{
//				if(WebEnv.DEBUG)
//					s_log.log(Level.FINEST, "Division " + _periodo/_intervalo);
//			}catch(java.lang.ArithmeticException ae){
//				errores.add(new LabelValueBean("El valor del Intervalo y Periodo son Incorrectos",""));
//			}
//			java.util.Date fecha = null;
//			//Alex.- Ticket 678 Correcion en el for, realizaba una iteracion de mas se quito el = 
//			for(int i = 0 ; i < _periodo/_intervalo; i++){
//				MPlanMedLine planMedLine = new MPlanMedLine(ctx,0,null); //no es necesaria la transacci�n --Miguel Loera--
//				// Alex.- Ticket 1069 Asignar la fecha inicial si es la primer linea del plan.
//				if(i==0){
//					fecha = fechaIni;
//				}else{
//					cal.add(Calendar.SECOND, _intervalo);
//					fecha = Constantes.getSdfFechaHoraAmPm().parse(Constantes.getSdfFechaHoraAmPm().format(cal.getTime()));
//				}				
//				planMedLine.setQtyPlanned(cantidad.setScale(this.liDecimales, BigDecimal.ROUND_HALF_UP));
//				planMedLine.setProgDate(new Timestamp(fecha.getTime()));//2009-04-23 00:30:00.0
//				qtyTotPlanned = qtyTotPlanned.add(planMedLine.getQtyPlanned());
//				resultados.add(planMedLine);
//			}
//			
//			//TODO: PASAR A LA FORMA
//			setEndDate(Constantes.getSdfFechaHoraAmPm().format(cal.getTime()));
//			setQtyTotPlanned(qtyTotPlanned.doubleValue());
//			setLstCalculo(resultados);
//			if(resultados.size() > 0)
//				setCalculado(true);
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, e.getMessage());
//			errores.add(new LabelValueBean(e.getMessage(),""));
//		}		
//				
//		return errores;
//	}
//
//	
//	/**
//	 * Agrega una programacion de medicamentos a la lista
//	 *
//	 * @param mapping El ActionMapping empleado para seleccionar esta instancia
//	 * @param actionForm El bean ActionForm opcional para esta solicitud
//	 * @param request La solicitud HTTP que estamos procesando
//	 * @param response La respuesta HTTP que estamos creando
//	 */
//	public LabelValueBean addLine(	boolean isEdit, List<MPlanMed> lstProgMed, 
//			ArrayList<List<MPlanMedLine>> lstProgMedLine, 
//			int formaIndex, List<MPlanMedLine> formaLstCalculo,
//			String medicalNote, int EXME_CtaPac_ID, int M_Warehouse_ID, 
//			int M_Product_ID, int C_UOM_ID, int EXME_Medico_ID,
//			int EXME_Especialidad_ID, Date startDateValue, int qtyPlanned, 
//			int qtyTotPlanned,int liDecimales, int interval, String intervalUOM, 
//			int period, String periodsUOM, String endDate){
//		
//		s_log.log(Level.INFO, "****** addLine ****** ");
//
//		LabelValueBean error = null;
//		try {
//			//validamos los campos
//			error = validateToCalculate(M_Product_ID, interval, period, qtyPlanned);
//			
//			if(error!=null){
//				return error;
//			}	
//			
//			MPlanMed planMed = null;
//			int index = 0;
//			
//			if(!isEdit){				
//				//Nueva linea de plan de Medicamento
//				planMed = new MPlanMed(ctx,0,null);
//				index = lstProgMed.size();
//				
//				//Cargamos los datos del plan de medicamento
//				planMed = cargaInfoPlanMed//(forma, planMed);
//				( planMed, 
//						 medicalNote,  EXME_CtaPac_ID,  M_Warehouse_ID, 
//						 M_Product_ID,  C_UOM_ID,  EXME_Medico_ID,
//						 EXME_Especialidad_ID,  startDateValue,  qtyPlanned, 
//						 qtyTotPlanned, liDecimales,  interval,  intervalUOM, 
//						 period,  periodsUOM,  endDate);
//				
//				planMed.setName("Plan: " + (index+1));
//				
//				//Agregamos el plan de medicamento a la lista.
//				lstProgMed.add(planMed);
//				//Agregamos el plan de medicamento a la lista principal
//				lstProgMedLine.add(index,formaLstCalculo);
//				
//			}else{				
//				//Editar plan de Medicamento
//				index = formaIndex;
//				planMed = lstProgMed.get(index);	
//				
//				//Cargamos los datos del plan de medicamento
//				planMed = cargaInfoPlanMed( planMed, 
//						 medicalNote,  EXME_CtaPac_ID,  M_Warehouse_ID, 
//						 M_Product_ID,  C_UOM_ID,  EXME_Medico_ID,
//						 EXME_Especialidad_ID,  startDateValue,  qtyPlanned, 
//						 qtyTotPlanned, liDecimales,  interval,  intervalUOM, 
//						 period,  periodsUOM,  endDate);
//				
//				//Actualizamos el plan de medicamento a la lista.
//				lstProgMed.set(index, planMed);	
//				//Actualizamos el plan de medicamento a la lista principal
//				lstProgMedLine.set(index,formaLstCalculo);
//			}
//			
//			planMed = null;
//			
//			//resetelamos los valores del detalle
//			resetPlanMed();
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, e.getMessage());
//		}
//		return error;
//	}
//	
//
//	/**
//	 * Validamos los datos necesarios
//	 */
//    public LabelValueBean validateToCalculate(int M_Product_ID, int interval, int period, int qtyPlanned) {
//        
//        //Validamos que se haya proporcionado un producto.
//        if(M_Product_ID == 0)
//        	return new LabelValueBean("error.traspasos.noExisteProd", String.valueOf(M_Product_ID));
//   
//        //Validamos que se hayan proporcionado Intervalo, Periodo y Cantidad.
//        if(interval <= 0 || period <= 0 || qtyPlanned <= 0)
//        	return new LabelValueBean("error.progmed.calcular", String.valueOf(M_Product_ID));
//
//        return null;
//    }
//    
//	public void resetPlanMed() {
//		//resetelamos los valores del detalle
//		resetDetail();
//		setCalculado(false);
//	}
//	
//	/**
//	* Reseteo de la Forma
//	*/
//	public void resetDetail(){
//		
//		calculado = false;
//	/*	M_Product_ID = 0;
//		productValue = null;
//		productName = null;
//		medicalNote = null;
//		interval = 0;
//		period = 0;
//		qtyPlanned = 0;
//		qtyTotPlanned = 0.0;
//		C_UOM_ID = 0;
//		lstUOM = new ArrayList<LabelValueBean>();
//
//		startDate = Constantes.sdfFecha.format(cal.getTime());;
//		hora = Constantes.dosDigitos.format(cal.get(Calendar.HOUR));
//		horas = Datos.getLstHoras(1,12);
//		min = Constantes.dosDigitos.format(cal.get(Calendar.MINUTE));
//		minutos = Datos.getLstMinutos(0,59,1);
//		ampm = cal.get(Calendar.AM_PM) == 1 ? PM : AM;;
//		endDate = null;
//
//		lstCalculo = new ArrayList<MPlanMedLine>();
//		
//		edit = false;
//		*/
//	}
//	
//	private boolean calculado = false;
//	
//	public boolean isCalculado() {
//		return calculado;
//	}
//
//	public void setCalculado(boolean calculado) {
//		this.calculado = calculado;
//	}
//	
//	
//	/**
//	 * Noelia: Carga los datos del bean a la programacion de medicamento 
//	 *
//	 * @param progMedDetForm El bean ActionForm opcional para esta solicitud
//	 * @param MPlanMed Plan de medicamento al que se le cargaran los datos del bean
//	 * @return MPlanMed Plan de medicamento actualizado
//	 */
//	public MPlanMed cargaInfoPlanMed(MPlanMed planMed, 
//			String medicalNote, int EXME_CtaPac_ID, int M_Warehouse_ID, 
//			int M_Product_ID, int C_UOM_ID, int EXME_Medico_ID,
//			int EXME_Especialidad_ID, Date startDateValue, int qtyPlanned, 
//			int qtyTotPlanned,int liDecimales, int interval, String intervalUOM, 
//			int period, String periodsUOM, String endDate) throws Exception {
//		
//		planMed.setDescription(medicalNote);
//		planMed.setEXME_CtaPac_ID(EXME_CtaPac_ID);
//		planMed.setM_Warehouse_ID(M_Warehouse_ID);
//		planMed.setM_Product_ID(M_Product_ID);
//		planMed.setC_UOM_ID(C_UOM_ID);
//		planMed.setStartDate(new Timestamp(startDateValue.getTime()));
//		planMed.setEndDate(new Timestamp((Constantes.getSdfFechaHoraAmPm().parse(endDate)).getTime()));			
//		planMed.setQtyPlanned(new BigDecimal(qtyPlanned).setScale(liDecimales, BigDecimal.ROUND_HALF_UP));
//		planMed.setQtyTotPlanned(new BigDecimal(qtyTotPlanned).setScale(liDecimales, BigDecimal.ROUND_HALF_UP));
//		planMed.setQtyTotAplied(new BigDecimal(0.0));
//		planMed.setIntervalo(interval);
//		planMed.setUOMIntervalo(intervalUOM);
//		planMed.setDuracion(period);
//		planMed.setUOMDuracion(periodsUOM);
//		planMed.setEXME_Medico_ID(EXME_Medico_ID);
//		planMed.setEXME_Especialidad_ID(EXME_Especialidad_ID);
//		planMed.setDocStatus(MPlanMed.DOCSTATUS_Drafted);
//		
//		return planMed;
//	}

}
