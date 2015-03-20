package org.compiere.model.bpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMETratamientosCues;
import org.compiere.model.MEXMETratamientosDetalle;
import org.compiere.model.MEXMETratamientosPaq;
import org.compiere.model.MEXMETratamientosPres;
import org.compiere.model.MEXMETratamientosProductos;
import org.compiere.model.MEXMETratamientosServ;
import org.compiere.model.MEXMETratamientosSesion;

/**
 * obtener para las citas el producto los servicios y los cuestionarios
 * 
 * @author Expert
 * 
 */
public class TreatmentsDetail {

	/** serialVersionUID */
	private static final long serialVersionUID = 3225915776578798655L;
	/** Id */
	private int tratSesionID = 0;
	/** */
	private Properties ctx = null;
	/** */
	private String trxName = null;
	/** Servicios */
	private List<MEXMETratamientosServ> lstServicios = new ArrayList<MEXMETratamientosServ>();
	/** Prescripciones */
	private List<MEXMETratamientosPres> lstPrescripciones = new ArrayList<MEXMETratamientosPres>();
	/** Cuestionarios */
	private List<MEXMETratamientosCues> lstCuestionarios = new ArrayList<MEXMETratamientosCues>();
	/** Servicios */
	private List<MEXMETratamientosPaq> lstMiniPack = new ArrayList<MEXMETratamientosPaq>();
	/** Prescripciones */
	private List<MEXMETratamientosProductos> lstProduct = new ArrayList<MEXMETratamientosProductos>();
	/** */
	private MEXMETratamientosDetalle detalleTrat = null;
	/** */
	private MEXMETratamientosSesion sesionTrat = null;

	/**
	 * Constructor
	 * 
	 * @param paramTipoLista
	 */
	public TreatmentsDetail(Properties ctx, int EXME_Tratamientos_Sesion_ID,
			String trxName) {
		super();

		this.ctx = ctx;
		this.tratSesionID = EXME_Tratamientos_Sesion_ID;
		this.trxName = trxName;

		sesionTrat = new MEXMETratamientosSesion(ctx, this.tratSesionID,
				trxName);
	}

	/**
	 * Buscamos el detalle de la sesion
	 * @param sesion
	 */
	public void buscarDetalle(String detTrat) {

		if (detalleTrat == null)
			detalleTrat = new MEXMETratamientosDetalle(ctx, sesionTrat
					.getEXME_Tratamientos_Detalle_ID(), null);

		// Servicios
		if(detTrat.equals("Serv"))
			consultaConfigServ();
		// Prescripciones
		if(detTrat.equals("Pres"))
			consultaConfigPres();
		// Cuestionarios
		if(detTrat.equals("Cuest"))
			consultaConfigCues();

	}

	public void buscarDetalle() {

		if (detalleTrat == null)
			detalleTrat = new MEXMETratamientosDetalle(ctx, sesionTrat
					.getEXME_Tratamientos_Detalle_ID(), null);

		// Servicios
			consultaConfigServ();
		// Prescripciones
			consultaConfigPres();
		// Cuestionarios
			consultaConfigCues();
			// Paquetes
			consultaConfigPaq();
			// Productos
			consultaConfigProductos();

	}
	
	private void consultaConfigServ() {

		if (detalleTrat != null){
			// Servicios
			lstServicios = MEXMETratamientosServ.getTratamientosDetalle(ctx,
					detalleTrat.getEXME_Tratamientos_Detalle_ID(), trxName);
		}

	}

	private void consultaConfigPres() {
		if (detalleTrat != null)
			// Prescripciones
			lstPrescripciones = MEXMETratamientosPres.getTratamientosDetalle(
					ctx, detalleTrat.getEXME_Tratamientos_Detalle_ID(), null,
					trxName);

	}

	private void consultaConfigCues() {
		if (detalleTrat != null)
			// Cuestionarios
			lstCuestionarios = MEXMETratamientosCues.getTratamientosDetalle(
					ctx, detalleTrat.getEXME_Tratamientos_Detalle_ID(), null,
					trxName);

	}

	private void consultaConfigPaq() {

		if (detalleTrat != null){
			// Servicios
			lstMiniPack = MEXMETratamientosPaq.getTratamientosDetalle(ctx,
					detalleTrat.getEXME_Tratamientos_Detalle_ID(), null, trxName);
		}

	}

	private void consultaConfigProductos() {
		if (detalleTrat != null)
			// Prescripciones
			lstProduct = MEXMETratamientosProductos.getTratamientosDetalle(
					ctx, detalleTrat.getEXME_Tratamientos_Detalle_ID(), null,
					trxName);

	}

	public List<MEXMETratamientosServ> getLstServicios() {
		return lstServicios;
	}

	public void setLstServicios(List<MEXMETratamientosServ> lstServicios) {
		this.lstServicios = lstServicios;
	}

	public List<MEXMETratamientosPres> getLstPrescripciones() {
		return lstPrescripciones;
	}

	public void setLstPrescripciones(
			List<MEXMETratamientosPres> lstPrescripciones) {
		this.lstPrescripciones = lstPrescripciones;
	}

	public List<MEXMETratamientosCues> getLstCuestionarios() {
		return lstCuestionarios;
	}

	public void setLstCuestionarios(List<MEXMETratamientosCues> lstCuestionarios) {
		this.lstCuestionarios = lstCuestionarios;
	}
	public List<MEXMETratamientosPaq> getLstMiniPack() {
		return this.lstMiniPack;
	}

	public void setLstMiniPack(List<MEXMETratamientosPaq> lstMiniPack) {
		this.lstMiniPack = lstMiniPack;
	}

	public List<MEXMETratamientosProductos> getLstProduct() {
		return this.lstProduct;
	}

	public void setLstProduct(List<MEXMETratamientosProductos> lstProduct) {
		this.lstProduct = lstProduct;
	}
}
/*
 * 
 * public void valores(List<TreatmentsStatus> listadoDetalleTrat){ for (int i =
 * 0; i < listadoDetalleTrat.size(); i++) {
 * obtenerValores(listadoDetalleTrat.get(i)); } }
 * 
 * 
 * public void obtenerValores(TreatmentsStatus status) {
 * 
 * // Lista // status.setTipoLista(); int tipoLista = 0;
 * 
 * //list.add("idColumn"); // status.setIdColumn(idColumn);
 * 
 * //int idProg =
 * obj.get_Value("M_Product_ID")!=null?(Integer)obj.get_Value("M_Product_ID"):0;
 * //MProduct objProg = new MProduct(Env.getCtx(), idProg, null);
 * 
 * // prod, almacen, fecha, estatus, solicitar, suspender if(tipoLista ==
 * TreatmentsImp.TIPOLISTA_Servicios){ list.add("productoLabel");
 * list.add("almacenLabel");
 * 
 * MEXMEActPacienteInd act = (MEXMEActPacienteInd)status.getPOExpediente();
 * 
 * X_EXME_Tratamientos_Serv serv;
 * 
 * MProduct objProd; MWarehouse objAlm; }
 * 
 * // prod, descrip, duracion uom, intervalo, uom, cantidad, // dosis, fecha,
 * estatus, solicitar, suspender if(tipoLista ==
 * TreatmentsImp.TIPOLISTA_prescripciones){ list.add("productoLabel");
 * list.add("descripcionLabel"); list.add("duracionLabel");
 * list.add("intervaloLabel"); list.add("dosisLabel");
 * 
 * MPlanMed plan = (MPlanMed)status.getPOExpediente();
 * 
 * X_EXME_Tratamientos_Pres pres;
 * 
 * MProduct objProd;
 * 
 * 
 * } // producto, fecha, estatus, solicitar, suspender if(tipoLista ==
 * TreatmentsImp.TIPOLISTA_material){ list.add("productoLabel");
 * 
 * X_EXME_SalidaGasto sal;
 * 
 * X_EXME_Tratamientos_Productos material;
 * 
 * MProduct objProd;
 * 
 * } // paquete, version, fecha, estatus, solicitar, suspender if(tipoLista ==
 * TreatmentsImp.TIPOLISTA_minipaquetes){ list.add("paqueteLabel");
 * list.add("versionLabel");
 * 
 * MCtaPacDet cargo = (MCtaPacDet)status.getPOExpediente();
 * 
 * X_EXME_Tratamientos_Paq minipaq;
 * 
 * MPaqBase objPaqBase; MPaqBaseVersion objPaqBaseVersion;
 * 
 * } // cuestionario, fecha, estatus, solicitar, suspender if(tipoLista ==
 * TreatmentsImp.TIPOLISTA_cuestionarios){ list.add("cuestionarioLabel");
 * 
 * MActPacienteDet actdet = (MActPacienteDet)status.getPOExpediente();
 * 
 * X_EXME_Tratamientos_Cues cuest;
 * 
 * MCuestionario objCuest;
 * 
 * 
 * }
 * 
 * // comentarios, fecha, estatus, realizado, suspender if(tipoLista ==
 * TreatmentsImp.TIPOLISTA_instrucciones){ list.add("descripcionLabel");
 * 
 * MEXMECuidadosPac cuidados = (MEXMECuidadosPac)status.getPOExpediente();
 * 
 * X_EXME_Tratamientos_Detalle detalle;
 * 
 * 
 * MEXMECuidadosPac objCuidados;
 * 
 * 
 * }
 * 
 * list.add("estatusLabel"); list.add("fechaLabel"); list.add("solicitar");
 * list.add("suspender"); list.add("color"); }
 * 
 * 
 * 
 * private void initColor(String estatus){ // Color.- se define por el estatus
 * if (color == null && estatus != null) { if
 * (estatus.equals(X_EXME_CtaPac.DOCSTATUS_Closed)) { color =
 * TreatmentsImp.DOCSTATUSCOLOR_CLOSED; } else if (estatus
 * .equals(X_EXME_CtaPac.DOCSTATUS_Voided)) { color =
 * TreatmentsImp.DOCSTATUSCOLOR_VOIDED; } else if (estatus
 * .equals(X_EXME_CtaPac.DOCSTATUS_Drafted)) { color =
 * TreatmentsImp.DOCSTATUSCOLOR_DRAFTED; } else if (estatus
 * .equals(X_EXME_CtaPac.DOCSTATUS_Completed)) {//TODO: Cambiar la referencia de
 * la constante ya que exma_ctapac no se utiliza en esta seccion del proceso
 * color = TreatmentsImp.DOCSTATUSCOLOR_COMPLETED; } } }
 */
