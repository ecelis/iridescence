package org.compiere.util;

import java.util.Date;

/**
 * Clase para propiedades varias. Contiene propiedades que 
 * son utilizadas para crear listas de arreglos de conjuntos de tablas.
 * 
 * <p>
 * Fecha: $Date: 2009/30/08 19:10:58 $ <p>
 *
 * @author Expert Victoria
 * @version $Revision: 1.0 $
 */
public class GenericModel {
	private String exme_GL_BudgetPa_ID;
	private String exme_Partida_ID;
	private String ad_OrgTrx_ID;
	private String pre_Solicitado;
	private String pre_Autorizado;
	private String pre_Comprometido;
	private String pre_Devengado;
	private String pre_Ejercido;
	private String value;
	private String name;
	private String ad_Org_Name;
	private String isDistributed;
	
	
	
	
	//VARIABLES PARA PRESUPUESTO POR PROGRAMA
	String id_campaign=null;
	String no_documento=null;
	String nombre=null;
	String description=null;
	String solicitado=null;
	String budget_name=null;
	String autorizado=null;
	String transfered=null;
	String exme_partida=null;
	String isapproved=null;
	public String getExme_GL_BudgetPa_ID() {
		return exme_GL_BudgetPa_ID;
	}
	public void setExme_GL_BudgetPa_ID(String exme_GL_BudgetPa_ID) {
		this.exme_GL_BudgetPa_ID = exme_GL_BudgetPa_ID;
	}
	public String getExme_Partida_ID() {
		return exme_Partida_ID;
	}
	public void setExme_Partida_ID(String exme_Partida_ID) {
		this.exme_Partida_ID = exme_Partida_ID;
	}
	public String getAd_OrgTrx_ID() {
		return ad_OrgTrx_ID;
	}
	public void setAd_OrgTrx_ID(String ad_OrgTrx_ID) {
		this.ad_OrgTrx_ID = ad_OrgTrx_ID;
	}
	public String getPre_Solicitado() {
		return pre_Solicitado;
	}
	public void setPre_Solicitado(String pre_Solicitado) {
		this.pre_Solicitado = pre_Solicitado;
	}
	public String getPre_Autorizado() {
		return pre_Autorizado;
	}
	public void setPre_Autorizado(String pre_Autorizado) {
		this.pre_Autorizado = pre_Autorizado;
	}
	public String getPre_Comprometido() {
		return pre_Comprometido;
	}
	public void setPre_Comprometido(String pre_Comprometido) {
		this.pre_Comprometido = pre_Comprometido;
	}
	public String getPre_Devengado() {
		return pre_Devengado;
	}
	public void setPre_Devengado(String pre_Devengado) {
		this.pre_Devengado = pre_Devengado;
	}
	public String getPre_Ejercido() {
		return pre_Ejercido;
	}
	public void setPre_Ejercido(String pre_Ejercido) {
		this.pre_Ejercido = pre_Ejercido;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAd_Org_Name() {
		return ad_Org_Name;
	}
	public void setAd_Org_Name(String ad_Org_Name) {
		this.ad_Org_Name = ad_Org_Name;
	}
	public String getIsDistributed() {
		return isDistributed;
	}
	public void setIsDistributed(String isDistributed) {
		this.isDistributed = isDistributed;
	}
	public String getId_campaign() {
		return id_campaign;
	}
	public void setId_campaign(String id_campaign) {
		this.id_campaign = id_campaign;
	}
	public String getNo_documento() {
		return no_documento;
	}
	public void setNo_documento(String no_documento) {
		this.no_documento = no_documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSolicitado() {
		return solicitado;
	}
	public void setSolicitado(String solicitado) {
		this.solicitado = solicitado;
	}
	public String getBudget_name() {
		return budget_name;
	}
	public void setBudget_name(String budget_name) {
		this.budget_name = budget_name;
	}
	public String getAutorizado() {
		return autorizado;
	}
	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}
	public String getTransfered() {
		return transfered;
	}
	public void setTransfered(String transfered) {
		this.transfered = transfered;
	}
	public String getExme_partida() {
		return exme_partida;
	}
	public void setExme_partida(String exme_partida) {
		this.exme_partida = exme_partida;
	}
	public String getIsapproved() {
		return isapproved;
	}
	public void setIsapproved(String isapproved) {
		this.isapproved = isapproved;
	}
	
	//PARA RECEPCION DE MATERIAL
	
	private String m_inout_id;
	private String documentNo;
	private String c_doctype_id;
	private String c_bpartner_id;
	private String c_bpartner_location_id;
	private String m_warehouse_id;
	private String priorityRule;
	private String docStatus;
	private String name_0;
	private String name_1;
	private String name_2;
	private String name_3;
	private String aprovado;
	private String transferido;
	private String userType;
	
	public String getM_inout_id() {
		return m_inout_id;
	}
	public void setM_inout_id(String m_inout_id) {
		this.m_inout_id = m_inout_id;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getC_doctype_id() {
		return c_doctype_id;
	}
	public void setC_doctype_id(String c_doctype_id) {
		this.c_doctype_id = c_doctype_id;
	}
	public String getC_bpartner_id() {
		return c_bpartner_id;
	}
	public void setC_bpartner_id(String c_bpartner_id) {
		this.c_bpartner_id = c_bpartner_id;
	}
	public String getC_bpartner_location_id() {
		return c_bpartner_location_id;
	}
	public void setC_bpartner_location_id(String c_bpartner_location_id) {
		this.c_bpartner_location_id = c_bpartner_location_id;
	}
	public String getM_warehouse_id() {
		return m_warehouse_id;
	}
	public void setM_warehouse_id(String m_warehouse_id) {
		this.m_warehouse_id = m_warehouse_id;
	}
	public String getPriorityRule() {
		return priorityRule;
	}
	public void setPriorityRule(String priorityRule) {
		this.priorityRule = priorityRule;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	public String getName_0() {
		return name_0;
	}
	public void setName_0(String name_0) {
		this.name_0 = name_0;
	}
	public String getName_1() {
		return name_1;
	}
	public void setName_1(String name_1) {
		this.name_1 = name_1;
	}
	public String getName_2() {
		return name_2;
	}
	public void setName_2(String name_2) {
		this.name_2 = name_2;
	}
	public String getName_3() {
		return name_3;
	}
	public void setName_3(String name_3) {
		this.name_3 = name_3;
	}
	public String getAprovado() {
		return aprovado;
	}
	public void setAprovado(String aprovado) {
		this.aprovado = aprovado;
	}
	public String getTransferido() {
		return transferido;
	}
	public void setTransferido(String transferido) {
		this.transferido = transferido;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	//LINEA RECEPCION MATERIAL (ITERATE)
		
	public String m_inoutline_id = "0";
	public long numLinea = 0;
	public int m_prodcut_id = 0;
	public long originalQty = 1;
	public int c_uom_id = 0;
	public String prodName = null;
	public String uomName = null;
	
	//EL RESTO
	
	public String localizador="";
	public String descripcion="";
	public String proyecto="";
	public String fuenteFinanciamiento="";
	public String faseProyecto="";
	public String tareaProyecto="";
	public String orgTrx="";
	public String programa="";
	public String lote="";
	public String loteInfo="";
	public String fechaLote="";
	public String referenciaID;
	
	public long getNumLinea() {
		return numLinea;
	}
	public void setNumLinea(long numLinea) {
		this.numLinea = numLinea;
	}
	public int getM_prodcut_id() {
		return m_prodcut_id;
	}
	public void setM_prodcut_id(int m_prodcut_id) {
		this.m_prodcut_id = m_prodcut_id;
	}
	public long getOriginalQty() {
		return originalQty;
	}
	public void setOriginalQty(long originalQty) {
		this.originalQty = originalQty;
	}
	public int getC_uom_id() {
		return c_uom_id;
	}
	public void setC_uom_id(int c_uom_id) {
		this.c_uom_id = c_uom_id;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public String getM_inoutline_id() {
		return m_inoutline_id;
	}
	public void setM_inoutline_id(String m_inoutline_id) {
		this.m_inoutline_id = m_inoutline_id;
	}
	public String getLocalizador() {
		return localizador;
	}
	public void setLocalizador(String localizador) {
		this.localizador = localizador;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}
	public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}
	public String getFaseProyecto() {
		return faseProyecto;
	}
	public void setFaseProyecto(String faseProyecto) {
		this.faseProyecto = faseProyecto;
	}
	public String getTareaProyecto() {
		return tareaProyecto;
	}
	public void setTareaProyecto(String tareaProyecto) {
		this.tareaProyecto = tareaProyecto;
	}
	public String getOrgTrx() {
		return orgTrx;
	}
	public void setOrgTrx(String orgTrx) {
		this.orgTrx = orgTrx;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getLoteInfo() {
		return loteInfo;
	}
	public void setLoteInfo(String loteInfo) {
		this.loteInfo = loteInfo;
	}
	public String getFechaLote() {
		return fechaLote;
	}
	public void setFechaLote(String fechaLote) {
		this.fechaLote = fechaLote;
	}
	public String getReferenciaID() {
		return referenciaID;
	}
	public void setReferenciaID(String referenciaID) {
		this.referenciaID = referenciaID;
	}
	
//LINEA PROVIDER INVOICE (ITERATE)

	public String invoNombreProducto;
	public String invoIDProducto;
	public int invoCantidad = 1;
	public double invoPrecio = 0.0;
	public String invoNombreUOM;
	public String invoIDUOM;
	public String getInvoNombreProducto() {
		return invoNombreProducto;
	}
	public void setInvoNombreProducto(String invoNombreProducto) {
		this.invoNombreProducto = invoNombreProducto;
	}
	public String getInvoIDProducto() {
		return invoIDProducto;
	}
	public void setInvoIDProducto(String invoIDProducto) {
		this.invoIDProducto = invoIDProducto;
	}
	public int getInvoCantidad() {
		return invoCantidad;
	}
	public void setInvoCantidad(int invoCantidad) {
		this.invoCantidad = invoCantidad;
	}
	public double getInvoPrecio() {
		return invoPrecio;
	}
	public void setInvoPrecio(double invoPrecio) {
		this.invoPrecio = invoPrecio;
	}
	public String getInvoNombreUOM() {
		return invoNombreUOM;
	}
	public void setInvoNombreUOM(String invoNombreUOM) {
		this.invoNombreUOM = invoNombreUOM;
	}
	public String getInvoIDUOM() {
		return invoIDUOM;
	}
	public void setInvoIDUOM(String invoIDUOM) {
		this.invoIDUOM = invoIDUOM;
	}
	
	//Valores para obtener lista contable 'post'
	private String organizacion = null;
	private String cuenta = null;
	private String debito = null;
	private String credito = null;
	private String producto=null;
	private String socio=null;
	private Date fecha=null;
	private String periodo=null;
	private String tipo=null;
	
	public String getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getDebito() {
		return debito;
	}
	public void setDebito(String debito) {
		this.debito = debito;
	}
	public String getCredito() {
		return credito;
	}
	public void setCredito(String credito) {
		this.credito = credito;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getSocio() {
		return socio;
	}
	public void setSocio(String socio) {
		this.socio = socio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
