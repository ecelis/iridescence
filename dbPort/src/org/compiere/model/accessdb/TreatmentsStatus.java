package org.compiere.model.accessdb;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.minigrid.IDColumn;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MCuestionario;
import org.compiere.model.MEXMEActPacienteDet;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMECuidadosPac;
import org.compiere.model.MEXMESalidaGasto;
import org.compiere.model.MEXMETratamientosCues;
import org.compiere.model.MEXMETratamientosDetalle;
import org.compiere.model.MEXMETratamientosPaq;
import org.compiere.model.MEXMETratamientosPres;
import org.compiere.model.MEXMETratamientosProductos;
import org.compiere.model.MEXMETratamientosServ;
import org.compiere.model.MPaqBase;
import org.compiere.model.MPaqBaseVersion;
import org.compiere.model.MPlanMed;
import org.compiere.model.MProduct;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.util.GridItem;

/**
 * Al iniciar esta clase solo se requiere el valor de POExpediente y si existe
 * informacion para POConfiguracion si no este puede ser null Despues debera ser
 * invoicada la clase TreatmentsDetail para que se llene este Bean
 * 
 * @author Expert
 * 
 */
public class TreatmentsStatus { //implements GridItem {

//	/** serialVersionUID */
//	private static final long serialVersionUID = 1909358942069048796L;
//	/** */
//	private Object poExpediente = null;
//	/** */
//	private Object poConfiguracion = null;
//	/** Id de la columna */
//	public Integer idColumna = 0;
//	/** tipo de lista */
//	private int tipoLista = 0;
//	/** Producto */
//	private String productoLabel = null;
//	/** Almacen */
//	private String almacenLabel = null;
//	/** Descripcion */
//	private String descripcionLabel = null;
//	/** Duracion */
//	private String duracionLabel = null;
//	/** Intervalo */
//	private String intervaloLabel = null;
//	/** Dosis */
//	private String dosisLabel = null;
//	/** Paquete */
//	private String paqueteLabel = null;
//	/** Version */
//	private String versionLabel = null;
//	/** Cuestionario */
//	private String cuestionarioLabel = null;
//	/** Estatus */
//	private String estatusLabel = null;
//	/** Fecha */
//	private String fechaLabel = null;
//	/** Color */
//	private Color color = null;
//	/** Listas */
//	private List<String> list = new ArrayList<String>();
//	/** */
//	private Properties ctx = null;
//	/** */
//	private PO pod = null;
//	/** */
//	private String classstr = null;
//
//	/**
//	 * Constructor
//	 */
//	public TreatmentsStatus(Properties ctx, PO po) {
//		super();
//		this.ctx = ctx;
//		pod = po;
//	}
//
//	@SuppressWarnings("unchecked")
//	public TreatmentsStatus(Properties ctx, Class name, PO po, int detalle) {
//		super();
//		this.ctx = ctx;
//		pod = po;
//		classstr = name.toString();
//		this.detalle = detalle;
//		cual();
//
//	}
//
//	/************************/
//
//	public Object getPOExpediente() {
//		return poExpediente;
//	}
//
//	public void setPOExpediente(Object pOExpediente) {
//		//if (pOExpediente != null && !(pOExpediente instanceof TreatmentsImp))
//		//	return;
//
//		poExpediente = pOExpediente;
//	}
//
//	public Object getPOConfiguracion() {
//		return poConfiguracion;
//	}
//
//	public void setPOConfiguracion(Object pOConfiguracion) {
//		poConfiguracion = pOConfiguracion;
//	}
//
//	/******************************/
//
//	public Integer getIdColumna() {
//		return idColumna;
//	}
//
//	public void setIdColumna(Integer idColumn) {
//		this.idColumna = idColumn;
//	}
//
//	public int getTipoLista() {
//		return tipoLista;
//	}
//
//	public void setTipoLista(int tipoLista) {
//		this.tipoLista = tipoLista;
//	}
//
//	public String getProductoLabel() {
//		return productoLabel;
//	}
//
//	public void setProductoLabel(String productoLabel) {
//		this.productoLabel = productoLabel;
//	}
//
//	public String getAlmacenLabel() {
//		return almacenLabel;
//	}
//
//	public void setAlmacenLabel(String almacenLabel) {
//		this.almacenLabel = almacenLabel;
//	}
//
//	public String getDescripcionLabel() {
//		return descripcionLabel;
//	}
//
//	public void setDescripcionLabel(String descripcionLabel) {
//		this.descripcionLabel = descripcionLabel;
//	}
//
//	public String getDuracionLabel() {
//		return duracionLabel;
//	}
//
//	public void setDuracionLabel(String duracionLabel) {
//		this.duracionLabel = duracionLabel;
//	}
//
//	public String getIntervaloLabel() {
//		return intervaloLabel;
//	}
//
//	public void setIntervaloLabel(String intervaloLabel) {
//		this.intervaloLabel = intervaloLabel;
//	}
//
//	public String getDosisLabel() {
//		return dosisLabel;
//	}
//
//	public void setDosisLabel(String dosisLabel) {
//		this.dosisLabel = dosisLabel;
//	}
//
//	public String getPaqueteLabel() {
//		return paqueteLabel;
//	}
//
//	public void setPaqueteLabel(String paqueteLabel) {
//		this.paqueteLabel = paqueteLabel;
//	}
//
//	public String getVersionLabel() {
//		return versionLabel;
//	}
//
//	public void setVersionLabel(String versionLabel) {
//		this.versionLabel = versionLabel;
//	}
//
//	public String getCuestionarioLabel() {
//		return cuestionarioLabel;
//	}
//
//	public void setCuestionarioLabel(String cuestionarioLabel) {
//		this.cuestionarioLabel = cuestionarioLabel;
//	}
//
//	public String getEstatusLabel() {
//		return estatusLabel;
//	}
//
//	public void setEstatusLabel(String estatusLabel) {
//		this.estatusLabel = estatusLabel;
//	}
//
//	public String getFechaLabel() {
//		return fechaLabel;
//	}
//
//	public void setFechaLabel(String fechaLabel) {
//		this.fechaLabel = fechaLabel;
//	}
//
//	public Color getColor() {
//		return color;
//	}
//
//	public void setColor(Color color) {
//		this.color = color;
//	}
//
//	public List<String> getList() {
//		return list;
//	}
//
//	public void setList(List<String> list) {
//		this.list = list;
//	}
//
//	/***********************/
//
//	private MCuestionario objCuestionario = null;
//	private MProduct objProduct = null;
//	private MWarehouse objAlmacen = null;
//	private MPaqBase objPaqBase = null;
//	private MPaqBaseVersion objPaqBaseVersion = null;
//	private MEXMECuidadosPac objCuidados = null;
//
//	private MEXMETratamientosDetalle objTratDetalle = null;
//	private MEXMETratamientosCues objTratCuest = null;
//	private MEXMETratamientosPaq objTratPaq = null;
//	private MEXMETratamientosPres objTratPres = null;
//	private MEXMETratamientosProductos objTratProductos = null;
//	private MEXMETratamientosServ objTratServ = null;
//
//	private int detalle = 0;
//
//	public int getDetalle() {
//		return detalle;
//	}
//
//	public void setDetalle(int detalle) {
//		this.detalle = detalle;
//	}
//
//	private void cual() {
//		if (classstr.equals(objTratDetalle.getClass().toString())) {
//			valor = new String[] { "descripcionLabel", "caden" };
//			objTratDetalle = (MEXMETratamientosDetalle) pod;
//			descripcionLabel = objTratDetalle.getDescription();
//
//			List<MEXMECitaMedica> lst = MEXMECitaMedica.getTratamientosDetalle(
//					ctx, detalle, null);
//			for (int i = 0; i < lst.size(); i++) {
//				caden = caden + " Cita para la fecha "
//						+ lst.get(i).getCreated();
//			}
//		} else
//
//		if (classstr.equals(objTratCuest.getClass().toString())) {
//			valor = new String[] { "cuestionarioLabel", "caden" };
//			objTratCuest = (MEXMETratamientosCues) pod;
//			cuestionarioLabel = objTratCuest.getCuestionario().getName();
//
//			List<MEXMEActPacienteDet> lst = MEXMEActPacienteDet.getTratamientosDetalle(
//					ctx, detalle, null);
//			for (int i = 0; i < lst.size(); i++) {
//				caden = " Aplicado el " + lst.get(i).getCreated();
//			}
//		} else if (classstr.equals(objTratPaq.getClass().toString())) {
//			valor = new String[] { "paqueteLabel", "caden" };
//			objTratPaq = (MEXMETratamientosPaq) pod;
//			paqueteLabel = objTratPaq.getPaquete().getName();
//
//			List<MCtaPacDet> lst = null;// MCtaPacDet.getTratamientosDetalle(ctx,
//					//detalle, null);
//			for (int i = 0; i < lst.size(); i++) {
//				caden = " Aplicado el " + lst.get(i).getCreated();
//			}
//		} else if (classstr.equals(objTratPres.getClass().toString())) {
//			valor = new String[] { "productoLabel", "caden" };
//			objTratPres = (MEXMETratamientosPres) pod;
//			productoLabel = objTratPres.getProducto().getName();
//
//			List<MPlanMed> lst = MPlanMed.getTratamientosDetalle(ctx, detalle,
//					null);
//			for (int i = 0; i < lst.size(); i++) {
//				caden = " Aplicado el " + lst.get(i).getCreated();
//			}
//		} else if (classstr.equals(objTratProductos.getClass().toString())) {
//			valor = new String[] { "productoLabel", "caden" };
//			objTratProductos = (MEXMETratamientosProductos) pod;
//			productoLabel = objTratProductos.getProducto().getName();
//
//			List<MEXMESalidaGasto> lst = MEXMESalidaGasto
//					.getTratamientosDetalle(ctx, detalle, null);
//			for (int i = 0; i < lst.size(); i++) {
//				caden = " Aplicado el " + lst.get(i).getCreated();
//			}
//		} else if (classstr.equals(objTratServ.getClass().toString())) {
//			valor = new String[] { "productoLabel", "caden" };
//			objTratServ = (MEXMETratamientosServ) pod;
//			productoLabel = objTratServ.getProducto().getName();
//
//			List<MEXMEActPacienteIndH> lst = MEXMEActPacienteIndH
//					.getTratamientosDetalle(ctx, detalle, null);
//			for (int i = 0; i < lst.size(); i++) {
//				caden = " Aplicado el " + lst.get(i).getCreated();
//			}
//		}
//	}
//
//	private String caden = null;
//
//	public String getCaden() {
//		return caden;
//	}
//
//	public void setCaden(String caden) {
//		this.caden = caden;
//	}
//
//	public MCuestionario getObjCuestionario() {
//		return objCuestionario;
//	}
//
//	public void setObjCuestionario(MCuestionario objCuestionario) {
//		this.objCuestionario = objCuestionario;
//	}
//
//	public MProduct getObjProduct() {
//		return objProduct;
//	}
//
//	public void setObjProduct(MProduct objProduct) {
//		this.objProduct = objProduct;
//	}
//
//	public MWarehouse getObjAlmacen() {
//		return objAlmacen;
//	}
//
//	public void setObjAlmacen(MWarehouse objAlmacen) {
//		this.objAlmacen = objAlmacen;
//	}
//
//	public MPaqBase getObjPaqBase() {
//		return objPaqBase;
//	}
//
//	public void setObjPaqBase(MPaqBase objPaqBase) {
//		this.objPaqBase = objPaqBase;
//	}
//
//	public MPaqBaseVersion getObjPaqBaseVersion() {
//		return objPaqBaseVersion;
//	}
//
//	public void setObjPaqBaseVersion(MPaqBaseVersion objPaqBaseVersion) {
//		this.objPaqBaseVersion = objPaqBaseVersion;
//	}
//
//	public MEXMECuidadosPac getObjCuidados() {
//		return objCuidados;
//	}
//
//	public void setObjCuidados(MEXMECuidadosPac objCuidados) {
//		this.objCuidados = objCuidados;
//	}
//
//	public MEXMETratamientosDetalle getObjTratDetalle() {
//		return objTratDetalle;
//	}
//
//	public void setObjTratDetalle(MEXMETratamientosDetalle objTratDetalle) {
//		this.objTratDetalle = objTratDetalle;
//	}
//
//	public MEXMETratamientosCues getObjTratCuest() {
//		return objTratCuest;
//	}
//
//	public void setObjTratCuest(MEXMETratamientosCues objTratCuest) {
//		this.objTratCuest = objTratCuest;
//	}
//
//	public MEXMETratamientosPaq getObjTratPaq() {
//		return objTratPaq;
//	}
//
//	public void setObjTratPaq(MEXMETratamientosPaq objTratPaq) {
//		this.objTratPaq = objTratPaq;
//	}
//
//	public MEXMETratamientosPres getObjTratPres() {
//		return objTratPres;
//	}
//
//	public void setObjTratPres(MEXMETratamientosPres objTratPres) {
//		this.objTratPres = objTratPres;
//	}
//
//	public MEXMETratamientosProductos getObjTratProductos() {
//		return objTratProductos;
//	}
//
//	public void setObjTratProductos(MEXMETratamientosProductos objTratProductos) {
//		this.objTratProductos = objTratProductos;
//	}
//
//	public MEXMETratamientosServ getObjTratServ() {
//		return objTratServ;
//	}
//
//	public void setObjTratServ(MEXMETratamientosServ objTratServ) {
//		this.objTratServ = objTratServ;
//	}
//
//	private String[] valor = null;
//
//	@Override
//	public String[] getColumns() {
//
//		return valor;
//	}
//
//	@Override
//	public IDColumn getIdColumn() {
//		return new IDColumn(idColumna);
//	}

}
