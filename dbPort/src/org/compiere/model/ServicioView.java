/*

 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.

 * Sistema MedSys

 */

package org.compiere.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.MTranslation;
import org.compiere.util.ValueNamePair;

/**
 * Modelo para la pantalla de captura de servicios
 * <p>
 * Modificado por: $Author: gisela $
 * Fecha: $Date: 2006/08/25 16:05:41 $
 *
 * @author $Author: gisela $
 * @version $Revision: 1.1 $
 */

public class ServicioView implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = -6419955282857214097L;
	/** Static Logger */
	private static CLogger sLog = CLogger.getCLogger(ServicioView.class);
	
	/**
	 * Constructor simple
	 */
	public ServicioView(){
		super();
	}
	
	/**
	 * Constructor de la planeacion medica MPlanMed
	 * @param ctx : contexto
	 * @param planMed : Planeacion Medica
	 * @param EXME_ActPacienteInd_ID : Id Acividad paciente
	 */
	public ServicioView(Properties ctx, MPlanMed planMed, 
			MPlanMedLine line, int EXME_ActPacienteInd_ID){
		super();
		
		copyFrom(ctx, planMed, line, EXME_ActPacienteInd_ID);
	}
	
	/**
	 * Constructor para vacuna
	 * @param ctx : contexto
	 * @param vac : MEXMEVacuna vacuna
	 */
	public ServicioView(Properties ctx, MEXMEVacuna vac){
		super();
		setDescripcion(MTranslation.getTable_Name(ctx, MEXMEVacuna.Table_Name, Env.getAD_Language(ctx)));
		setProdID(vac.getM_Product_ID());
		MProduct product = new MProduct(ctx, vac.getM_Product_ID(), null);
		setMed(product);
		setUdm(vac.getC_UOM_ID());
		setCantidad(vac.getCantidad().intValue());
		setQtyOrdered(vac.getCantidad());
		setEXME_Dosis_ID(product.getEXME_DoseForm_ID());
		setEXME_ViaAdministracion_ID(product.getEXME_Route_ID());
		setTomadoCasa(false);
		setSurtir(true);
	}
	
	/**
	 * constructor cuando lo unico que se tiene es el productos
	 * @param ctx : Contexto
	 * @param pProduct : Producto
	 */
	public ServicioView(Properties ctx, MProduct pProduct) {
		super();
		setMed(pProduct);
		setProdID(pProduct.getM_Product_ID());
		setDescripcion(pProduct.getComment());
		setUdm(pProduct.getC_UOM_ID());
		setSurtir(!pProduct.isExternal());
		setCantidad(1);
		setQtyOrdered(Env.ONE);
	}
	
	/**
	 * constructor cuando lo unico que se tiene es el productos
	 * @param ctx : Contexto
	 * @param pProduct : Producto
	 */
	public ServicioView(MProduct pProduct) {
		super();
		setMed(pProduct);
		setProdID(pProduct.getM_Product_ID());
		setDescripcion(pProduct.getComment());
		setUdm(pProduct.getC_UOM_ID());
		setSurtir(!pProduct.isExternal());
		setCantidad(1);
		setQtyOrdered(Env.ONE);
		setAlmaServ(Env.getM_Warehouse_ID(pProduct.getCtx()));
	}
	
	public ServicioView(Properties ctx, MEXMEActPacienteInd ind) {
		super();
		setModel(ind);
		setEXME_ActPacienteInd_ID(ind.getEXME_ActPacienteInd_ID());
		setEXME_ActPacienteIndH_ID(ind.getEXME_ActPacienteIndH_ID());
		setAlmaServ(ind.getM_Warehouse_ID());
		if(ind.getM_Warehouse_ID() > 0){
			setAlmaServName(ind.getAlmacen().getName());
		}
		MProduct product = ind.getProduct();
		if (product != null) {
			setMed(product);
			setProdValue(product.getValue()); 
			setProdName(product.getName());
			setNivelControl(product.getItemClass());
		}
		
		setDescripcion(ind.getComments());
		setUdm(ind.getC_UOM_ID());
		setUdmName(ind.getUomName());
		setSurtir(ind.isSurtir());
		setCantidad(ind.getQtyOrdered().intValue());
		setCantTomar(ind.getCantidadToma().doubleValue());
		setVecesDia(ind.getVecesDia().intValue());
		setNumDias(ind.getNumDias().intValue());
		setTomadoCasa(ind.isTomadoCasa());
		setQtyOrdered(ind.getQtyOrdered());
		setSurtir(ind.isSurtir());
		setResurtidos(ind.getResurtidos());
		
		setEXME_Dosis_ID(ind.getEXME_Dosis_ID());
		setEXME_ViaAdministracion_ID(ind.getEXME_ViasAdministracion_ID());
		setEXME_Institucion_ID(ind.getEXME_Institucion_ID());
		setEXME_Medico_ID(ind.getEXME_Medico_ID());
		if(ind.getMedico()!=null) {
			setMedicoName(ind.getMedico().getNombre_Med());
		}
		setEXME_Modifier(ind.getEXME_Modifiers_ID());
		if (ind.getEXME_Modifiers_ID() > 0) {
			setModifier(ind.getEXME_Modifiers().getName());
		} else {
			setModifier(null);
		}
		
		setDiagnosis1ID(ind.getEXME_Diagnostico_ID());
		setDiagnosis2ID(ind.getEXME_Diagnostico2_ID());
		setDiagnosis3ID(ind.getEXME_Diagnostico3_ID());
		setDiagnosis1Name(ind.getDiag1() == null ? "" : ind.getDiag1().getName());
		setDiagnosis2Name(ind.getDiag2() == null ? "" : ind.getDiag2().getName());
		setDiagnosis3Name(ind.getDiag3() == null ? "" : ind.getDiag3().getName());
		
		setProvider(ind.getProveedor());
		setOtherInstructions(ind.getOtherInstructions());
		setConsultingProvider(ind.getConsultingProvider());
		setTodayService(ind.isTodayService());
		setComments(ind.getComments());
		setFecha(ind.getDatePromised()==null?ind.getDateOrdered():ind.getDatePromised());
//		setFecha(ind.getDateOrdered());
		setAD_Org_Dest_ID(ind.getAD_Org_Dest_ID());
		
		setExmeOrderSetId(ind.getEXME_OrderSet_ID());
		setFromDB(true);
		setBillable(ind.isBillable());
		
		setPrioridadID(ind.getPriorityRule());
		if (ind.getPriorityRule() != null) {
			setPriorityComboStr(MRefList.getListName(ctx, MEXMEActPacienteInd.PRIORITYRULE_AD_Reference_ID, ind.getPriorityRule()));
		}
		// Tipo surtido
		String cad = MRefList.getListName(ctx, MEXMEActPacienteInd.TIPOSURTIDO_AD_Reference_ID, ind.getTipoSurtido());
		setTipoSurtido(cad);// El nombre se saca de la lista de referencia -raul
		setTipoSurtidoValue(ind.getTipoSurtido()); // El value se saca de la bd

		setMotivoCancel(ind.getEXME_MotivoCancel_ID());
		setMotivoCancelacion(ind.getMotivoCancelacion());
	}
	
	
	/**
	 * Suspension de prescripcion
	 */
	private int motivoCancel= 0;
	private String motivoCancelName = null;
	private String motivoCancelacion = null;

	/**
	 * Medico
	 */
	protected int EXME_Medico_ID = 0;
	protected String medicoName = null;

	/**
	 * Producto
	 */
	protected int prodID = 0;
	protected String prodValue = null;
	protected String prodName = null;
	protected String nivelControl = null;
	protected MProduct med = null;
	
	/**
	 * Diagnosticos
	 */
	protected int diagnosis1ID = 0;
	protected int diagnosis2ID = 0;
	protected int diagnosis3ID = 0;
	protected String diagnosis1Name = null;
	protected String diagnosis2Name = null;
	protected String diagnosis3Name = null;
	private String priorityComboStr = new String();
	
	/**
	 * Unidad de medida
	 */
	protected int udm = 0;
	protected int udmVol = 0;
	protected String udmName = null;
	protected int cantidad = 0;  
	protected MUOM uom = null;
	protected BigDecimal qtyOrdered = Env.ZERO;
	protected BigDecimal qtyOrderedVol = Env.ZERO; 
	/**
	 * Datos de dosis
	 */
	protected String tipoDosis = null;
	protected int EXME_Dosis_ID = 0;
	protected MEXMEDoseForm dosis = null;

	/**
	 * Vias de administraci�n
	 */
//	protected MEXMERoute viaAdmon = null;
	protected String viaAdministracion = null;
	protected int EXME_ViaAdministracion_ID = 0;

	/**
	 * Indicacion
	 */
	protected double cantTomar = 0;
	protected int vecesDia = 0;
	protected int numDias = 0;
	protected String descripcion= null; 

	/**
	 * Almacen
	 */
	protected String almaServName = null;
	protected int almaServ = 0;
	protected int almacenSolID = 0;
	protected int localizadorID = 0;

	/**
	 * Tipo de surtido
	 */
	protected String tipoSurtidoValue = null;
	protected String tipoSurtido = null;

	/**
	 * Datos de solicitud
	 */
	protected int EXME_ActPacienteInd_ID = 0;
	protected int EXME_ActPacienteIndH_ID = 0;
	protected boolean tomadoCasa = false;
	protected boolean surtir = true;
	protected String fechaVig = null;
	protected int resurtidos = 0;
	private int EXME_WareHouse_ID = 0;
	private int EXME_Modifier = 0;
	private boolean billable = false;
	private String modifier = null;
	private String statusName = null;
	private String docStatus = null;

	/**
	 * Unidades de medida
	 */
//	protected String UOMIntervalo = null;
//	protected String UOMDuracion = null;
//	protected String UOMDuracionName = null;
//	protected String UOMIntervaloName = null;
	
	/**
	 * FDB FRAMEWORK
	 */
	protected boolean existDifwk = true;
	
	/**
	 * Prioridad
	 */
	protected List<ValueNamePair> prioridadLst;
	protected String prioridadID = "5";
	protected String prioridadName = null;
	
	/**
	 * Fechas 
	 */
	protected Timestamp fecha = null;
//	protected String hora = null;
	protected Timestamp fechaApli = null;

	/**
	 * Servicios
	 */
	protected int especimenID = 0;
	protected int especimenCondicionID = 0;
	protected int parentID = 0;
	protected int impresoraID = 0;
	protected String protocolo = null;
	protected String diagnostico = null;
	protected String notes = null;
	private boolean isTempNew = true;
	
	/**
	 * 
	 * Referrals
	 */
	protected String comments = null;
	protected String otherInstructions = null;
	protected String provider = null;
	protected String consultingProvider = null;
	protected int EXME_Institucion_ID = 0;
	private int AD_Org_Dest_ID = 0;
	private boolean isTodayService = false;
	private boolean isProcedure = false;

	/**
	 * Frecuencias
	 */
	protected String freq1 = null;
	protected String freq2 = null;
	
	/**
	 * Lotes
	 */
	protected int lotID = 0;
	
	/** 
	 * secuencia 
	 */
	private int secuencia = 0;
	
	/**
	 * cita
	 */
	private String quantity_txt = null;
	private String dose_txt = null;
	private String estatus = null;
	private String anotaciones = null;
	private int EXME_GenProduct_ID = 0;
	private boolean isFromDB = false;
	
	/**
	 * Indica si la linea esta activa en la pantalla.
	 * @return
	 */
	
	private boolean activo = false;
	// Lama: usar modelo
	private MEXMEActPacienteInd model ;
	private MEXMEActPacienteIndH header ;

	/**
	 * Indica la accion a realizar para el servicio.
	 * @return
	 */
	
	private String accion = "N";
	/**
	 * Contiene la lista de valores de el estado.
	 * @return
	 */
	
	private ArrayList<LabelValueBean> estado = new ArrayList<LabelValueBean>();
	
	/**
	 * Planeacion de medicamentos
	 */
	private MPlanMed planMed = null; 
	private MPlanMedLine pmline = null;
	
	
	
	public MPlanMed getPlanMed() {
		return planMed;
	}

	public void setPlanMed(MPlanMed planMed) {
		this.planMed = planMed;
	}

	public MPlanMedLine getPMLine() {
		return pmline;
	}

	public void setPMLine(MPlanMedLine line) {
		this.pmline = line;
	}

	public int getLotID() {
		return lotID;
	}

	public void setLotID(int lotID) {
		this.lotID = lotID;
	}

	public int getEXME_Institucion_ID() {
		return EXME_Institucion_ID;
	}

	public void setEXME_Institucion_ID(int eXMEInstitucionID) {
		EXME_Institucion_ID = eXMEInstitucionID;
	}
	
	public String getFreq1() {
		return this.freq1;
	}

	public void setFreq1(String freq1) {
		this.freq1 = freq1;
	}

	public String getFreq2() {
		return this.freq2;
	}

	public void setFreq2(String freq2) {
		this.freq2 = freq2;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getAlmacenSolID() {
		return this.almacenSolID;
	}

	public void setAlmacenSolID(int almacenSolID) {
		this.almacenSolID = almacenSolID;
	}

	public String getProtocolo() {
		return this.protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getDiagnostico() {
		return this.diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public int getEspecimenID() {
		return this.especimenID;
	}

	public void setEspecimenID(int especimenID) {
		this.especimenID = especimenID;
	}

	public int getEspecimenCondicionID() {
		return this.especimenCondicionID;
	}

	public void setEspecimenCondicionID(int especimenCondicionID) {
		this.especimenCondicionID = especimenCondicionID;
	}

	public int getParentID() {
		return this.parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public int getImpresoraID() {
		return this.impresoraID;
	}

	public void setImpresoraID(int impresoraID) {
		this.impresoraID = impresoraID;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
//	public String getHora() {
//		return this.hora;
//	}
//
//	public void setHora(String hora) {
//		this.hora = hora;
//	}

	public String getPrioridadName() {
		return this.prioridadName;
	}

	public void setPrioridadName(String prioridadName) {
		this.prioridadName = prioridadName;
	}

	public List<ValueNamePair> getPrioridadLst() {
		return this.prioridadLst;
	}

	public void setPrioridadLst(List<ValueNamePair> prioridadLst) {
		this.prioridadLst = prioridadLst;
	}
 
	public String getPrioridadID() {
		return this.prioridadID;
	}

	public void setPrioridadID(int prioridadID) {
		this.prioridadID = String.valueOf(prioridadID);
	}
	
	public void setPrioridadID(String prioridadID) {
		this.prioridadID = StringUtils.trim(prioridadID);
	}

//	public String getUOMIntervaloName() {
//		return UOMIntervaloName;
//	}
//
//	public void setUOMIntervaloName(String uOMIntervaloName) {
//		UOMIntervaloName = uOMIntervaloName;
//	}
//
//	public String getUOMDuracionName() {
//		return UOMDuracionName;
//	}
//
//	public void setUOMDuracionName(String uOMDuracionName) {
//		UOMDuracionName = uOMDuracionName;
//	}

//	public String getUOMIntervalo() {
//		return UOMIntervalo;
//	}
//
//	public void setUOMIntervalo(String uOMIntervalo) {
//		UOMIntervalo = uOMIntervalo;
//	}

//	public String getUOMDuracion() {
//		return UOMDuracion;
//	}
//
//	public void setUOMDuracion(String uOMDuracion) {
//		UOMDuracion = uOMDuracion;
//	}

	/**
	 * Obtener la propiedad prodID.
	 * 
	 *@return La propiedad ProdID.
	 */

	public int getProdID() {
		return this.prodID;
	}

	/**
	 * Establecer la propiedad prodID.
	 * 
	 *@param prodID Nueva propiedad prodID.
	 */
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}

	/**
	 * Obtener la propiedad prodValue.
	 * 
	 *@return La propiedad ProdValue.
	 */
	public String getProdValue() {
		return this.prodValue;
	}

	/**
	 * Establecer la propiedad prodValue.
	 * 
	 *@param prodValue Nueva propiedad prodValue.
	 */
	public void setProdValue(String prodValue) {
		this.prodValue = prodValue;
	}

	/**
	 * Obtener la propiedad prodName.
	 * 
	 *@return La propiedad ProdName.
	 */
	public String getProdName() {
		return this.prodName;
	}

	/**
	 * Establecer la propiedad prodName.
	 *
	 *@param prodName Nueva propiedad prodName.
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	/**
	 * Devuelve el valor de la propiedad udm .
	 * 
	 *@return  El valor actual de Udm
	 */

	public int getUdm() {
		return this.udm;
	}

	/**
	 * Establece el nuevo valor para la propiedad udm .
	 * 
	 *@param udm El nuevo valor para udm .
	 */
	public void setUdm(int udm) {
		this.udm = udm;
	}
	
	/**
	 * Establece el nuevo valor para la propiedad udm del paquete.
	 * 
	 *@param udm El nuevo valor para udm del paquete.
	 */
	public void setUdmVol(int udmVol) {
		this.udmVol = udmVol;
	}

	/**
	 * Devuelve el valor de la propiedad udm del paquete
	 * 
	 *@return  El valor actual de Udm del paquete
	 */

	public int getUdmVol() {
		return this.udmVol;
	}


	/**
	 * Obtener la propiedad udmName.
	 * 
	 *@return La propiedad UdmName.
	 */
	public String getUdmName() {
		return this.udmName;
	}

	/**
	 * Establecer la propiedad udmName.
	 * 
	 *@param udmName Nueva propiedad udmName.
	 */
	public void setUdmName(String udmName) {
		this.udmName = udmName;
	}


	/**
	 * Devuelve el valor de la propiedad cantidad .
	 * 
	 *@return  El valor actual de Cantidad
	 */
	public int getCantidad() {
		return this.cantidad;
	}

	/**
	 * Establece el nuevo valor para la propiedad cantidad .
	 * 
	 *@param cantidad El nuevo valor para cantidad .
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	/**
	 * Devuelve el valor de la propiedad descripcion
	 * 
	 *@return  El valor actual de descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Establece el nuevo valor para la propiedad descripcion
	 * 
	 *@param descripcion El nuevo valor para descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion= descripcion;
	}

	/**
	 * Devuelve el valor de la propiedad cantTomar .
	 * 
	 * @return  El valor actual de CantTomar
	 */
	public double getCantTomar() {
		return this.cantTomar;
	}

	/**
	 * Establece el nuevo valor para la propiedad cantTomar .
	 * 
	 * @param cantTomar El nuevo valor para cantTomar .
	 */
	public void setCantTomar(double cantTomar) {
		this.cantTomar = cantTomar;
	}



	/**
	 * Devuelve el valor de la propiedad vecesDia .
	 * 
	 * @return  El valor actual de VecesDia
	 */
	public int getVecesDia() {
		return this.vecesDia;
	}

	/**
	 * Establece el nuevo valor para la propiedad vecesDia .
	 * 
	 * @param vecesDia El nuevo valor para vecesDia .
	 */
	public void setVecesDia(int vecesDia) {
		this.vecesDia = vecesDia;
	}


	/**
	 * Devuelve el valor de la propiedad numDias .
	 * 
	 * @return  El valor actual de NumDias
	 */
	public int getNumDias() {
		return this.numDias;
	}

	/**
	 * Establece el nuevo valor para la propiedad numDias .
	 * 
	 * @param numDias El nuevo valor para numDias .
	 */
	public void setNumDias(int numDias) {
		this.numDias = numDias;
	}


	/**
	 * Devuelve el valor de la propiedad almaServName .
	 * 
	 *@return  El valor actual de almaServName
	 */
	public String getAlmaServName() {
		return this.almaServName;
	}

	/**
	 * Establece el nuevo valor para la propiedad almaServName .
	 * 
	 *@param almaServName El nuevo valor para almaServName .
	 */
	public void setAlmaServName(String almaServName) {
		this.almaServName = almaServName;
	}


	/**
	 * Devuelve el valor de la propiedad almaServ .
	 * 
	 *@return  El valor actual de almaServ
	 */
	public int getAlmaServ() {
		return this.almaServ;
	}

	/**
	 * Establece el nuevo valor para la propiedad almaServ .
	 * 
	 *@param almaServ El nuevo valor para almaServ .
	 */
	public void setAlmaServ(int almaServ) {
		this.almaServ = almaServ;
	}


	/**
	 * @return Returns the surtir.
	 */
	public boolean getSurtir() {
		return surtir;
	}

	/**
	 * @param surtir The surtir to set.
	 */
	public void setSurtir(boolean surtir) {
		this.surtir = surtir;
	}



	public String getNivelControl() {
		return nivelControl;
	}

	public void setNivelControl(String nivelControl) {
		this.nivelControl = nivelControl;
	}

//	public MEXMERoute getViaAdmon() {
//		return viaAdmon;
//	}
//
//	public void setViaAdmon(MEXMERoute viaAdmon) {
//		this.viaAdmon = viaAdmon;
//	}



	public MEXMEDoseForm getDosis() {
		return dosis;
	}

	public void setDosis(MEXMEDoseForm dosis) {
		this.dosis = dosis;
	}

	public String getTipoSurtidoValue() {
		return tipoSurtidoValue;
	}

	public void setTipoSurtidoValue(String tipoSurtidoValue) {
		this.tipoSurtidoValue = tipoSurtidoValue;
	}

	public String getTipoDosis() {
		return tipoDosis;
	}

	public void setTipoDosis(String tipoDosis) {
		this.tipoDosis = tipoDosis;
	}

	public int getEXME_Dosis_ID() {
		return EXME_Dosis_ID;
	}

	public void setEXME_Dosis_ID(int eXMEDosisID) {
		EXME_Dosis_ID = eXMEDosisID;
	}

	public String getViaAdministracion() {
		return viaAdministracion;
	}

	public void setViaAdministracion(String viaAdministracion) {
		this.viaAdministracion = viaAdministracion;
	}

	public int getEXME_ViaAdministracion_ID() {
		return EXME_ViaAdministracion_ID;
	}

	public void setEXME_ViaAdministracion_ID(int eXMEViaAdministracionID) {
		EXME_ViaAdministracion_ID = eXMEViaAdministracionID;
	}

	public String getTipoSurtido() {
		return tipoSurtido;
	}

	public void setTipoSurtido(String tipoSurtido) {
		this.tipoSurtido = tipoSurtido;
	}

	public String getFechaVig() {
		return fechaVig;
	}

	public void setFechaVig(String fechaVig) {
		this.fechaVig = fechaVig;
	}

	public int getResurtidos() {
		return resurtidos;
	}

	public void setResurtidos(int resurtidos) {
		this.resurtidos = resurtidos;
	}


	public int getMotivoCancel() {
		return motivoCancel;
	}



	public String getMotivoCancelName() {
		return motivoCancelName;
	}

	public void setMotivoCancelName(String motivoCancelName) {
		this.motivoCancelName = motivoCancelName;
	}

	public void setMotivoCancel(int motivoCancel) {
		this.motivoCancel = motivoCancel;
	}


	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}


	public boolean isTomadoCasa() {
		return tomadoCasa;
	}

	public void setTomadoCasa(boolean tomadoCasa) {
		this.tomadoCasa = tomadoCasa;
	}


	public int getEXME_Medico_ID() {
		return EXME_Medico_ID;
	}

	public void setEXME_Medico_ID(int EXME_Medico_ID) {
		this.EXME_Medico_ID = EXME_Medico_ID;
	}



	public String getMedicoName() {
		return medicoName;
	}

	public void setMedicoName(String medicoName) {
		this.medicoName = medicoName;
	}
	public int getDiagnosis1ID() {
		return this.diagnosis1ID;
	}

	public void setDiagnosis1ID(int diagnosis1id) {
		this.diagnosis1ID = diagnosis1id;
	}

	public int getDiagnosis2ID() {
		return this.diagnosis2ID;
	}

	public void setDiagnosis2ID(int diagnosis2id) {
		this.diagnosis2ID = diagnosis2id;
	}

	public int getDiagnosis3ID() {
		return this.diagnosis3ID;
	}

	public void setDiagnosis3ID(int diagnosis3id) {
		this.diagnosis3ID = diagnosis3id;
	}

	public String getDiagnosis1Name() {
		if(diagnosis1ID > 0 && StringUtils.isBlank(diagnosis1Name)){
			this.diagnosis1Name = MDiagnostico.getName(Env.getCtx(), diagnosis1ID, null);
		}
		return this.diagnosis1Name;
	}

	public void setDiagnosis1Name(String diagnosis1Name) {
		this.diagnosis1Name = diagnosis1Name;
	}

	public String getDiagnosis2Name() {
		if(diagnosis2ID > 0 && StringUtils.isBlank(diagnosis2Name)){
			this.diagnosis2Name = MDiagnostico.getName(Env.getCtx(), diagnosis2ID, null);
		}
		return this.diagnosis2Name;
	}

	public void setDiagnosis2Name(String diagnosis2Name) {
		this.diagnosis2Name = diagnosis2Name;
	}

	public String getDiagnosis3Name() {
		if(diagnosis3ID > 0 && StringUtils.isBlank(diagnosis3Name)){
			this.diagnosis3Name = MDiagnostico.getName(Env.getCtx(), diagnosis3ID, null);
		}
		return this.diagnosis3Name;
	}

	public void setDiagnosis3Name(String diagnosis3Name) {
		this.diagnosis3Name = diagnosis3Name;
	}
	public int getEXME_ActPacienteInd_ID() {
		return EXME_ActPacienteInd_ID;
	}

	public void setEXME_ActPacienteInd_ID(int eXMEActPacienteIndID) {
		EXME_ActPacienteInd_ID = eXMEActPacienteIndID;
	}
	
	public int getLocalizadorID() {
		return localizadorID;
	}

	public void setLocalizadorID(int localizadorID) {
		this.localizadorID = localizadorID;
	}
	public int getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}
	public MProduct getMed() {
		return med;
	}

	public void setMed(MProduct med) {
		this.med = med;
	}
	
	/**
	 * Id del encabezado de la linea de la 
	 * prescripcion
	 * @return
	 */
	public int getEXME_ActPacienteIndH_ID() {
		return EXME_ActPacienteIndH_ID;
	}

	public void setEXME_ActPacienteIndH_ID(int eXMEActPacienteIndHID) {
		EXME_ActPacienteIndH_ID = eXMEActPacienteIndHID;
	}
	
	/**
	 * Uom
	 * @return
	 */
	public MUOM getUom() {
		return uom;
	}

	public void setUom(MUOM uom) {
		this.uom = uom;
	}


	/**
	 * 
	 * @param ctx
	 * @param indica
	 * @param m_actPacienteInd
	 * @return
	 */
	public void copyFrom (Properties ctx, MEXMEActPacienteInd m_actPacienteInd){
		setProdID(m_actPacienteInd.getM_Product_ID());

		MProduct product = m_actPacienteInd.getProduct();
		if (product != null) {
			setMed(product);
			setProdValue(product.getValue());
			setProdName(product.getName());
			setNivelControl(product.getItemClass());
		}

		setDescripcion(m_actPacienteInd.getDescription());
		setUdm(m_actPacienteInd.getC_UOM_ID());

		setCantidad(m_actPacienteInd.getQtyDelivered().intValue());
		setQtyOrdered(m_actPacienteInd.getQtyOrdered());
		
		setCantTomar(m_actPacienteInd.getCantidadToma().doubleValue());
		setVecesDia(m_actPacienteInd.getVecesDia().intValue());
		setNumDias(m_actPacienteInd.getNumDias().intValue());
		setSurtir(m_actPacienteInd.isSurtir());
		setAlmaServ(m_actPacienteInd.getM_Warehouse_ID());
		setUdmName(m_actPacienteInd.getUomName());

		//Tipo de dosis
		setEXME_Dosis_ID(m_actPacienteInd.getEXME_Dosis_ID());
//		if(m_actPacienteInd.getDosis()!=null)
//			setTipoDosis(m_actPacienteInd.getDosis().getName());

		//via de administraci�n
		setEXME_ViaAdministracion_ID(m_actPacienteInd.getEXME_ViasAdministracion_ID());
//		if(m_actPacienteInd.getViaAdmon()!=null)
//			setViaAdministracion(m_actPacienteInd.getViaAdmon().getName());

		//Tipo surtido
		String cad = MRefList.getListName(ctx, MEXMEActPacienteInd.TIPOSURTIDO_AD_Reference_ID,
				m_actPacienteInd.getTipoSurtido());
		setTipoSurtido(cad);// El nombre se saca de la lista de referencia -raul
		setTipoSurtidoValue(m_actPacienteInd.getTipoSurtido()); // El value se saca de la bd

		//resurtidos
		setResurtidos(m_actPacienteInd.getResurtidos());
		setTomadoCasa(m_actPacienteInd.isTomadoCasa());
		setEXME_Medico_ID(m_actPacienteInd.getEXME_Medico_ID());
		if(m_actPacienteInd.getMedico()!=null)
			setMedicoName(m_actPacienteInd.getMedico().getNombre_Med());
		setMotivoCancel(m_actPacienteInd.getEXME_MotivoCancel_ID());
		setMotivoCancelacion(m_actPacienteInd.getMotivoCancelacion());

		setEXME_ActPacienteInd_ID(m_actPacienteInd.getEXME_ActPacienteInd_ID());
		setFecha(m_actPacienteInd.getActPacienteIndH().getDatePromised());
	}

	/**
	 * 
	 * @param ctx
	 * @param indica
	 * @param planMed
	 * @return
	 */
	public void copyFrom (final Properties ctx, final MPlanMed planMed, 
			final MPlanMedLine line, final int EXME_ActPacienteInd_ID){
		
		setProdID(planMed.getM_Product_ID());
		setPlanMed(planMed);
		setPMLine(line);
		
		MProduct product = planMed.getProduct();
		setMed(product);
		
		setProdValue(product.getValue());
		setProdName(product.getName());

		setDescripcion(planMed.getDescription());
		setUdm(planMed.getC_UOM_ID());

		if(line!=null){
			setCantidad(line.getQtyAplied().intValue()); // Aplicada
			setQtyOrdered(line.getQtyPlanned());// Planeada
			setLote(line.getLot());
		} else {
			setCantidad(planMed.getQtyPlanned().intValue());
			setQtyOrdered(planMed.getQtyPlanned());
		}
		
		// Prescripcion
		setCantTomar(planMed.getQtyPlanned().doubleValue());
		setVecesDia(planMed.getIntervalo());
		setNumDias(planMed.getDuracion());
		
		setSurtir(planMed.isSurtir());
		setAlmaServ(planMed.getM_Warehouse_ID());
		setNivelControl(product.getItemClass());
		setUdmName(product.getUom().getName());

		//Tipo de dosis
		setEXME_Dosis_ID(planMed.getEXME_Dosis_ID());
		if(planMed.getEXME_Dosis()!=null){
			setTipoDosis(planMed.getEXME_Dosis().getName());
		}
		//via de administraci�n
		setEXME_ViaAdministracion_ID(planMed.getEXME_ViasAdministracion_ID());
		if(planMed.getEXME_ViasAdministracion()!=null){
			setViaAdministracion(planMed.getEXME_ViasAdministracion().getName());
		}
		//resurtidos
		setTomadoCasa(planMed.isTomadoCasa());
		setEXME_Medico_ID(planMed.getEXME_Medico_ID());

		if(planMed.getEXME_Medico_ID()>0){
			setMedicoName(MEXMEMedico.nombreMedico(ctx, planMed.getEXME_Medico_ID()));
		}
		setMotivoCancel(planMed.getEXME_MotivoCancel_ID());
//		setUOMIntervalo( planMed.getUOMIntervalo() );
//		setUOMDuracion(planMed.getUOMDuracion() );
		setEXME_ActPacienteInd_ID(EXME_ActPacienteInd_ID);
		
		// Fecha orden 
		if(line!=null){
			setFecha(line.getProgDate());
			setFechaApli(line.getApliedDate());
		}
			
	}
	
	/**
	 * 
	 * @param ctx
	 * @param indica
	 * @param m_actPacienteInd
	 * @return
	 */
	public void copyFrom (ServicioView from){
	
		setSecuencia(from.getSecuencia());
		//Producto
		setProdID(from.getProdID());
		if (from.getMed() != null) {
			setMed(from.getMed());
			setProdValue(from.getMed().getValue());
			setProdName(from.getMed().getName());
			setNivelControl(from.getMed().getItemClass());
		}
		//Unidad de medida
		setUdm(from.getUdm());
		setUdmName(from.getUdmName());
		setCantidad(from.getCantidad());
		setUom(from.getUom());
		setQtyOrdered(from.getQtyOrdered());
		//Indicacion
		setCantTomar(from.getCantTomar());
		setVecesDia(from.getVecesDia());
		setNumDias(from.getNumDias());
		setDescripcion(from.getDescripcion());
		//Almacen
		setAlmaServ(from.getAlmaServ());
		setAlmaServName(from.getAlmaServName());
		setAlmacenSolID(from.getAlmacenSolID());
		setLocalizadorID(from.getLocalizadorID());
		//Datos de dosis
		setEXME_Dosis_ID(from.getEXME_Dosis_ID());
		setTipoDosis(from.getTipoDosis());
		setDosis(from.getDosis());
		//Vias de administraci�n
//		setViaAdmon(from.getViaAdmon());
		setEXME_ViaAdministracion_ID(from.getEXME_ViaAdministracion_ID());
		setViaAdministracion(from.getViaAdministracion());
		// Tipo de surtido
		setTipoSurtido(from.getTipoSurtido());// El nombre se saca de la lista de referencia -raul
		setTipoSurtidoValue(from.getTipoSurtidoValue()); // El value se saca de la bd
		//Diagnosticos
		setDiagnosis1ID(from.getDiagnosis1ID());
		setDiagnosis2ID(from.getDiagnosis2ID());
		setDiagnosis3ID(from.getDiagnosis3ID());
		setDiagnosis1Name(from.getDiagnosis1Name());
		setDiagnosis2Name(from.getDiagnosis2Name());
		setDiagnosis3Name(from.getDiagnosis3Name());
		// Prioridad
		setPriorityComboStr(from.getPriorityComboStr());
		setPrioridadName(from.getPrioridadName());
		setPrioridadID(from.getPrioridadID());
		//Medico		
		setEXME_Medico_ID(from.getEXME_Medico_ID());
		setMedicoName(from.getMedicoName());
		//Suspension de prescripcion
		setMotivoCancel(from.getMotivoCancel());
		setMotivoCancelacion(from.getMotivoCancelacion());
		setMotivoCancelName(from.getMotivoCancelName());
		//Datos de solicitud
		setEXME_ActPacienteInd_ID(from.getEXME_ActPacienteInd_ID());
		setEXME_ActPacienteIndH_ID(from.getEXME_ActPacienteIndH_ID());
		setTomadoCasa(from.isTomadoCasa());
		setSurtir(from.getSurtir());
		setFechaVig(from.getFechaVig());
		setResurtidos(from.getResurtidos());
		setEXME_WareHouse_ID(from.getEXME_WareHouse_ID());
		//
		setAccion(from.getAccion());
		setActivo(from.isActivo());
		setLotID(from.getLotID());
		setLote(from.getLote());
		// Model
		setModel(from.getModel());
		// cita
		setQuantity_txt(from.getQuantity_txt());
		setDose_txt(from.getDose_txt());
		setEstatus(from.getEstatus());
		setAnotaciones(from.getAnotaciones());
		setEXME_GenProduct_ID(from.getEXME_GenProduct_ID());
		setFromDB(from.isFromDB());
		// Frequency
		setFreq1(from.getFreq1());
		setFreq2(from.getFreq2());
		//Referrals
		setComments(from.getComments());
		setOtherInstructions(from.getOtherInstructions());
		setProvider(from.getProvider());
		setConsultingProvider(from.getConsultingProvider());
		setEXME_Institucion_ID(from.getEXME_Institucion_ID());
		setAD_Org_Dest_ID(from.getAD_Org_Dest_ID());
		setTodayService(from.isTodayService());
		setProcedure(from.isProcedure());
		// Servicios
		setEspecimenID(from.getEspecimenID());
		setEspecimenCondicionID(from.getEspecimenCondicionID());
		setParentID(from.getParentID());
		setImpresoraID(from.getImpresoraID());
		setProtocolo(from.getProtocolo());
		setDiagnostico(from.getDiagnostico());
		setNotes(from.getNotes());
		// Unidades de medida
//		setUOMIntervalo(from.getUOMIntervalo());
//		setUOMDuracion(from.getUOMDuracion());
//		setUOMDuracionName(from.getUOMDuracionName());
//		setUOMIntervaloName(from.getUOMIntervaloName());
		//FDB FRAMEWORK
		 setExistDifwk(from.isExistDifwk());
		// Fechas 
		setFecha(from.getFecha());
//		setHora(from.getHora());
		setFechaApli(from.getFechaApli());
		//Lama
		setEncounterFormId(from.getEncounterFormId());
	}

	
	public void setLote(String lot) {
		lote = lot;
	}

	public Timestamp getFechaApli() {
		return fechaApli;
	}

	public void setFechaApli(Timestamp fechaApli) {
		this.fechaApli = fechaApli;
	}
	
	public boolean isExistDifwk() {
		return existDifwk;
	}

	public void setExistDifwk(boolean existDifwk) {
		this.existDifwk = existDifwk;
	}

	public void setEXME_WareHouse_ID(int eXME_WareHouse_ID) {
		EXME_WareHouse_ID = eXME_WareHouse_ID;
	}

	public int getEXME_WareHouse_ID() {
		return EXME_WareHouse_ID;
	}
	
	public void setEXME_GenProduct_ID(int exmeGenProductID) {
		this.EXME_GenProduct_ID = exmeGenProductID;
		
	}
	
	public void setEstatus(String estatusScheduleservice) {
		this.estatus = estatusScheduleservice;
		
	}
	
	public void setAnotaciones(String notes) {
		this.anotaciones = notes;
		
	}
	public void setQuantity_txt(String text) {
		this.quantity_txt = text;
		
	}
	
	public int getEXME_GenProduct_ID() {
		return this.EXME_GenProduct_ID;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public String getAnotaciones() {
		return this.anotaciones;
	}

	public String getQuantity_txt() {
		return this.quantity_txt;
	}

	public String getDose_txt() {
		return this.dose_txt;
	}

	public void setDose_txt(String text) {
		this.dose_txt = text;
		
	}
	
	public MEXMEActPacienteInd getModel(Properties ctx) {
		if(getModel() == null && getEXME_ActPacienteInd_ID() > 0){
			model = new MEXMEActPacienteInd(ctx, getEXME_ActPacienteInd_ID(), null);
		}
		return model;
	}

	public MEXMEActPacienteInd getModel() {
		if (model != null && EXME_ActPacienteInd_ID > 0 && EXME_ActPacienteInd_ID != model.getEXME_ActPacienteInd_ID()) {
			model = null;
			header = null;
		}
		return model;
	}

	public void setModel(MEXMEActPacienteInd model) {
		this.model = model;
	}

	public MEXMEActPacienteIndH getHeader() {
		if (header == null && getModel() != null) {
			header = getModel().getActPacienteIndH();
		}
		return header;
	}

	public void setHeader(MEXMEActPacienteIndH header) {
		this.header = header;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public ArrayList<LabelValueBean> getEstado() {
		return estado;
	}

	public void setEstado(ArrayList<LabelValueBean> estado) {
		this.estado = estado;
	}
	
	public BigDecimal getQtyOrdered() {
		return this.qtyOrdered;
	}

	public void setQtyOrdered(BigDecimal qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}
	
	public BigDecimal getQtyOrderedVol() {
		return this.qtyOrderedVol;
	}

	public void setQtyOrderedVol(BigDecimal qtyOrderedVol) {
		this.qtyOrderedVol = qtyOrderedVol;
	}

	/**
	 * Dispense as written
	 * @return
	 */
	protected boolean daw = false;
	public boolean isDAW() {
		return daw;
	}

	/**
	 * DEA Number (Drug enforcement agency)
	 * @return
	 */
	protected boolean dea = false;
	public boolean isDEA() {
		return dea;
	}

	/**
	 * Por necesidad medica
	 * @return
	 */
	protected boolean prn = false;
	public boolean isPRN() {
		return prn;
	}

	/**
	 * Farmacia
	 * @return
	 */
	protected int EXME_Farmacia_ID = 0;
	public boolean isDaw() {
		return this.daw;
	}

	public void setDaw(boolean daw) {
		this.daw = daw;
	}

	public boolean isDea() {
		return this.dea;
	}

	public void setDea(boolean dea) {
		this.dea = dea;
	}

	public boolean isPrn() {
		return this.prn;
	}

	public void setPrn(boolean prn) {
		this.prn = prn;
	}

	public void setEXME_Farmacia_ID(int eXMEFarmaciaID) {
		this.EXME_Farmacia_ID = eXMEFarmaciaID;
	}

	public int getEXME_Farmacia_ID() {
		return EXME_Farmacia_ID;
	}

	public void setOtherInstructions(String otherInstructions) {
		this.otherInstructions = otherInstructions;
	}

	public String getOtherInstructions() {
		return this.otherInstructions;
	}

	public void setConsultingProvider(String consultingProvider) {
		this.consultingProvider = consultingProvider;
	}

	public String getConsultingProvider() {
		return this.consultingProvider;
	}

	public void setTodayService(boolean isTodayService) {
		this.isTodayService = isTodayService;
	}

	public boolean isTodayService() {
		return isTodayService;
	}

	public void setValues(MEXMEActPacienteInd linea) {
		diagnosis1ID = linea.getEXME_Diagnostico_ID();
		diagnosis2ID = linea.getEXME_Diagnostico2_ID();
		diagnosis3ID = linea.getEXME_Diagnostico3_ID();
		diagnosis1Name = linea.getDiag1()!=null?linea.getDiag1().getName():"";
		diagnosis2Name = linea.getDiag2()!=null?linea.getDiag2().getName():"";
		diagnosis3Name = linea.getDiag3()!=null?linea.getDiag3().getName():"";
		prioridadID = linea.getPriorityRule();
	}

	private MEXMEPrescRXDet prescRxDet;

	public MEXMEPrescRXDet getPrescRxDet() {
		return prescRxDet;
	}

	public void setPrescRxDet(MEXMEPrescRXDet prescRxDet) {
		this.prescRxDet = prescRxDet;
	}

	public void setPriorityComboStr(String priorityComboStr) {
		this.priorityComboStr = priorityComboStr;
	}

	public String getPriorityComboStr() {
		return priorityComboStr;
	}

	private String lote = null;
	
	public String getLote() {
		return lote;
	}

	/**
	 * @param aD_Org_Dest_ID the aD_Org_Dest_ID to set
	 */
	public void setAD_Org_Dest_ID(int aD_Org_Dest_ID) {
		AD_Org_Dest_ID = aD_Org_Dest_ID;
	}

	/**
	 * @return the aD_Org_Dest_ID
	 */
	public int getAD_Org_Dest_ID() {
		return AD_Org_Dest_ID;
	}

	public boolean isFromDB() {
		return isFromDB;
	}

	public void setFromDB(boolean isFromDB) {
		this.isFromDB = isFromDB;
	}

	public boolean isProcedure() {
		return isProcedure;
	}

	public void setProcedure(boolean isProcedure) {
		this.isProcedure = isProcedure;
	}
	
	public void setProcedure() {
		if (getMed() != null) {
			final String prodClass = getMed().getProductClass();
			final boolean isLabRx = X_M_Product.PRODUCTCLASS_Laboratory.equals(prodClass) || X_M_Product.PRODUCTCLASS_XRay.equals(prodClass);
			this.isProcedure = !isLabRx;
		} else {
			this.isProcedure = false;
		}
	}
	
	private int encounterFormId;
	
	public void setEncounterFormId(int encounterFormId) {
		this.encounterFormId = encounterFormId;
	}
	
	public int getEncounterFormId() {
		return encounterFormId;
	}
	
	public String findStudyClass(){
		MProduct studie = getMed();
		if ((MProduct.PRODUCTTYPE_Service.equals(studie.getProductType()) || MProduct.PRODUCTTYPE_Package.equals(studie.getProductType()))) {
			if(MProduct.PRODUCTCLASS_Laboratory.equals(studie.getProductClass())){
				return studie.getProductClass();
			}else if (MProduct.PRODUCTCLASS_XRay.equals(studie.getProductClass())){
				return studie.getProductClass();
			}
		}
		return MProduct.PRODUCTCLASS_Others;		
	}
	
	/**
	 * Actualiza la prescripcion
	 * @param idActPacInd
	 * @param trxName
	 */
	public void updatePrescRxDet(final int idActPacInd, final String trxName){
		if (getPrescRxDet() != null) {
			getPrescRxDet().setEXME_ActPacienteInd_ID(idActPacInd);
			if (!getPrescRxDet().save(trxName)) {
				sLog.log(
						Level.SEVERE,
						"insertarMovimientosMed : Error en : if(!indica.getPrescRxDet().save(trxName)){ ");
			}
		}
	}
	
	private int exmeOrderSetId = 0;

	public int getExmeOrderSetId() {
		return exmeOrderSetId;
	}

	public void setExmeOrderSetId(int exmeOrderSetId) {
		this.exmeOrderSetId = exmeOrderSetId;
	}

	public int getEXME_Modifier() {
		return EXME_Modifier;
	}

	public void setEXME_Modifier(int eXME_Modifier) {
		EXME_Modifier = eXME_Modifier;
	}

	public boolean isBillable() {
		return billable;
	}

	public void setBillable(boolean billable) {
		this.billable = billable;
	}
	public String getModifier() {
		return modifier;
	}
	
	public void setModifier(String modifier){
		this.modifier = modifier;
	}

	public String getStatus() {
		return statusName;
	}

	public void setStatusName(String status) {
		this.statusName = status;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String statusId) {
		this.docStatus = statusId;
	}

	public boolean isTempNew() {
		return isTempNew;
	}

	public void setTempNew(boolean isTempNew) {
		this.isTempNew = isTempNew;
	}
	private long getDateLong(){
		long longDate;
		if(fecha == null){
			longDate = 0;
		} else {
			longDate = Long.valueOf(Constantes.getCustom("yyyyMMddHHmm").format(fecha));
		}
		return longDate;
	}
	
	public boolean isDuplicated(ServicioView other) {
		if (isDuplicatedProduct(other)) {
			if (almaServ == other.almaServ //
				&& getDateLong() == other.getDateLong() //
				&& StringUtils.equals(priorityComboStr, other.priorityComboStr)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isDuplicatedProduct(ServicioView other) {
		if (other == null || this == other || citaMedicaDetId == other.citaMedicaDetId) {
			return false;
		}
		return prodID == other.prodID;
	}
	
//	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		long date = getDateLong();
//		int result = 1;
//		result = prime * result + almaServ;
//		result = prime * result + (int) (date ^ date >>> 32);
//		result = prime * result + (priorityComboStr == null ? 0 : priorityComboStr.hashCode());
//		result = prime * result + (provider == null ? 0 : provider.hashCode());
//		return result;
//	}
	
	private int citaMedicaDetId = 0;
	public void setCitaMedicaDetId(int citaMedicaDetId) {
		this.citaMedicaDetId = citaMedicaDetId;
	}
	public int getCitaMedicaDetId() {
		return citaMedicaDetId;
	}
	
	/**
	 * 
	 * @param studies
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 */
	public MEXMECitaMedicaDet getTemporaryRecord(MEXMECitaMedica appointment) {
		MEXMECitaMedicaDet temp;
		if(citaMedicaDetId > 0){
			temp = new MEXMECitaMedicaDet(appointment.getCtx(), citaMedicaDetId, null);
		} else {
			temp = new MEXMECitaMedicaDet(appointment.getCtx(), appointment, null);
		}
		temp.setIsService(true);
		temp.setEstatus(MEXMECitaMedicaDet.ESTATUS_Drafted);
		temp.setAplicar(false);
		temp.setTomadoCasa(false);
		temp.setOtherInstructions(getOtherInstructions());
		temp.setConsultingProvider(getConsultingProvider());
		temp.setDescription(getComments());

		temp.setProductType(MEXMECitaMedicaDet.PRODUCTTYPE_Studie);
		temp.setM_Product_ID(getProdID());
		temp.setSurtir(getSurtir());
		temp.setEXME_Diagnostico_ID(getDiagnosis1ID());
		temp.setEXME_Diagnostico2_ID(getDiagnosis2ID());
		temp.setEXME_Diagnostico3_ID(getDiagnosis3ID());
		if (getEXME_Medico_ID() > 0) {
			temp.setEXME_Medico_ID(getEXME_Medico_ID());
		}
		temp.setEXME_Institucion_ID(getEXME_Institucion_ID());
		temp.setProveedor(getProvider());
		temp.setDatePromised(getFecha());
		temp.setPriorityRule(getPrioridadID());
		temp.setM_Warehouse_ID(getAlmaServ());
		temp.setAD_Org_Dest_ID(getAD_Org_Dest_ID());
		temp.setEXME_Modifiers_ID(getEXME_Modifier());
		temp.setBillable(isBillable());
		temp.setEXME_OrderSet_ID(getExmeOrderSetId());
		return temp;
	}
	
	
	/**
	 * 
	 * @param studies
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 */
	public MEXMEActPacienteInd getIndication(Properties ctx) {
		MEXMEActPacienteInd temp = new MEXMEActPacienteInd(ctx, getEXME_ActPacienteInd_ID(), null);
		temp.setTomadoCasa(false);
		temp.setOtherInstructions(getOtherInstructions());
		temp.setConsultingProvider(getConsultingProvider());
		temp.setDescription(getComments());
		temp.setM_Product_ID(getProdID());
		temp.setSurtir(getSurtir());
		temp.setEXME_Diagnostico_ID(getDiagnosis1ID());
		temp.setEXME_Diagnostico2_ID(getDiagnosis2ID());
		temp.setEXME_Diagnostico3_ID(getDiagnosis3ID());
		if (getEXME_Medico_ID() > 0) {
			temp.setEXME_Medico_ID(getEXME_Medico_ID());
		}
		temp.setEXME_Institucion_ID(getEXME_Institucion_ID());
		temp.setProveedor(getProvider());
		temp.setDatePromised(getFecha());
		temp.setPriorityRule(getPrioridadID());
		temp.setM_Warehouse_ID(getAlmaServ());
		temp.setAD_Org_Dest_ID(getAD_Org_Dest_ID());
		temp.setEXME_Modifiers_ID(getEXME_Modifier());
		temp.setBillable(isBillable());
		temp.setEXME_OrderSet_ID(getExmeOrderSetId());
		return temp;
	}
}