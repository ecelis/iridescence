package org.compiere.model;

public class EncabezadoFacturaElectronica 
{
	/*
	 * Informacion Genenal de Facturacion
	 * Electronica
	 */
     
	private String	nombreSocioNeg	=	"";
	private String	direccionSocioNeg	=	"";
	private String	noExterior	=	"";
	private String	noInterior	=	"";
	private String	tipoSocioNeg	=	"";
	private String	colonia	=	"";
	private String	delegacion	=	"";
	private String	fechaFactura	=	"";
	private String	noFactura	=	"";
	private String	codPostalSocioNeg	=	"";
	private String	ciudadSocioNeg	=	"";
	private String	estadoSocioNeg	=	"";
	private String	paisSocioNeg	=	"";
	private String	claveSocioNeg	=	"";
	private String	centroCostos	=	"";
	private String	folioFiscal	=	"";
	private String	RFCSocioNeg	=	"";
	private String	nombreMedico	=	"";
	private String	nombreEntidad	=	"";
	private String	noCtaPac	=	"";
	private String	nombrePaciente	=	"";
	private String	fechaNacPac	=	"";
	private String	edadPac	=	"";
	private String	sexoPac	=	"";
	private String	telefonoPac	=	"";
	private String	tipoDoc	=	"";
	private String  claveUsuario="";
	private String  nombreUsuario="";
	private String  claveUsuarioFact="";
	private String  nombreUsuarioFact="";
	private String  noSiniestro="";
	private String  horaMin="";
	private String descuentoGlobalFact="";
	private String descuentoFijoSigno="";
	private String cargoNombre="";	
	private String etiquetaTipoDoc="";
	//series que forman parte del folio fiscal
	private String serieDocFac="";
	private String serieDocNC="";
	private String serieDocND="";
	//campos que determinas las copias a facturar 
	//Y si se envian por email
	private	String imprimeFactura="";
	private String copias="";
	private String enviafacturaEmail="";
	private String email="";
	private String paramsFE_ImprimeFactYEnviaMail="";
	private String facturaWEB_Compiere="";	
	private String refenciaDoc="";
	private String leyendaCompiere="";
	
	//0011-008 TTPR Desc. Fijo
	private int c_Charge_ID=0;
	
	public int getC_Charge_ID() {
		return c_Charge_ID;
	}
	public void setC_Charge_ID(int charge_ID) {
		c_Charge_ID = charge_ID;
	}
	//0011-008 Fin
	
	public String getLeyendaCompiere() {
		return leyendaCompiere;
	}
	public void setLeyendaCompiere(String leyendaCompiere) {
		this.leyendaCompiere = leyendaCompiere;
	}
	
	public String getCargoNombre() {
		return cargoNombre;
	}
	public void setCargoNombre(String cargoNombre) {
		this.cargoNombre = cargoNombre;
	}

	public String getDescuentoFijoSigno() {
		return descuentoFijoSigno;
	}
	public void setDescuentoFijoSigno(String descuentoFijoSigno) {
		this.descuentoFijoSigno = descuentoFijoSigno;
	}

	public String getFacturaWEB_Compiere() {
		return facturaWEB_Compiere;
	}
	public void setFacturaWEB_Compiere(String facturaWEB_Compiere) {
		this.facturaWEB_Compiere = facturaWEB_Compiere;
	}
	
	public String getRefenciaDoc() {
		return refenciaDoc;
	}
	public void setRefenciaDoc(String refenciaDoc) {
		this.refenciaDoc = refenciaDoc;
	}
	public String getCopias() {
		return copias;
	}
	public void setCopias(String copias) {
		this.copias = copias;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnviafacturaEmail() {
		return enviafacturaEmail;
	}
	public void setEnviafacturaEmail(String enviafacturaEmail) {
		this.enviafacturaEmail = enviafacturaEmail;
	}
	public String getImprimeFactura() {
		return imprimeFactura;
	}
	public void setImprimeFactura(String imprimeFactura) {
		this.imprimeFactura = imprimeFactura;
	}
	public String getSerieDocFac() {
		return serieDocFac;
	}
	public void setSerieDocFac(String serieDocFac) {
		this.serieDocFac = serieDocFac;
	}
	public String getSerieDocNC() {
		return serieDocNC;
	}
	public void setSerieDocNC(String serieDocNC) {
		this.serieDocNC = serieDocNC;
	}
	public String getSerieDocND() {
		return serieDocND;
	}
	public void setSerieDocND(String serieDocND) {
		this.serieDocND = serieDocND;
	}
	public String getDescuentoGlobalFact() {
		return descuentoGlobalFact;
	}
	public void setDescuentoGlobalFact(String descuentoGlobalFact) {
		this.descuentoGlobalFact = descuentoGlobalFact;
	}	
	public String getNoSiniestro() {
		return noSiniestro;
	}
	public void setNoSiniestro(String noSiniestro) {
		this.noSiniestro = noSiniestro;
	}
	public String getCentroCostos() {
		return centroCostos;
	}
	public void setCentroCostos(String centroCostos) {
		this.centroCostos = centroCostos;
	}
	public String getCiudadSocioNeg() {
		return ciudadSocioNeg;
	}
	public void setCiudadSocioNeg(String ciudadSocioNeg) {
		this.ciudadSocioNeg = ciudadSocioNeg;
	}
	public String getClaveSocioNeg() {
		return claveSocioNeg;
	}
	public void setClaveSocioNeg(String claveSocioNeg) {
		this.claveSocioNeg = claveSocioNeg;
	}
	public String getCodPostalSocioNeg() {
		return codPostalSocioNeg;
	}
	public void setCodPostalSocioNeg(String codPostalSocioNeg) {
		this.codPostalSocioNeg = codPostalSocioNeg;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getDelegacion() {
		return delegacion;
	}
	public String getDireccionSocioNeg() {
		return direccionSocioNeg;
	}
	public void setDireccionSocioNeg(String direccionSocioNeg) {
		this.direccionSocioNeg = direccionSocioNeg;
	}
	public String getEdadPac() {
		return edadPac;
	}
	public void setEdadPac(String edadPac) {
		this.edadPac = edadPac;
	}
	public String getEstadoSocioNeg() {
		return estadoSocioNeg;
	}
	public void setEstadoSocioNeg(String estadoSocioNeg) {
		this.estadoSocioNeg = estadoSocioNeg;
	}
	public String getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public String getFechaNacPac() {
		return fechaNacPac;
	}
	public void setFechaNacPac(String fechaNacPac) {
		this.fechaNacPac = fechaNacPac;
	}
	public String getFolioFiscal() {
		return folioFiscal;
	}
	public void setFolioFiscal(String folioFiscal) {
		this.folioFiscal = folioFiscal;
	}
	public String getNoCtaPac() {
		return noCtaPac;
	}
	public void setNoCtaPac(String noCtaPac) {
		this.noCtaPac = noCtaPac;
	}
	public String getNoExterior() {
		return noExterior;
	}
	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}
	public String getNoFactura() {
		return noFactura;
	}
	public void setNoFactura(String noFactura) {
		this.noFactura = noFactura;
	}
	public String getNoInterior() {
		return noInterior;
	}
	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public String getNombreSocioNeg() {
		return nombreSocioNeg;
	}
	public void setNombreSocioNeg(String nombreSocioNeg) {
		this.nombreSocioNeg = nombreSocioNeg;
	}
	public String getPaisSocioNeg() {
		return paisSocioNeg;
	}
	public void setPaisSocioNeg(String paisSocioNeg) {
		this.paisSocioNeg = paisSocioNeg;
	}
	public String getRFCSocioNeg() {
		return RFCSocioNeg;
	}
	public void setRFCSocioNeg(String socioNeg) {
		RFCSocioNeg = socioNeg;
	}
	public String getSexoPac() {
		return sexoPac;
	}
	public void setSexoPac(String sexoPac) {
		this.sexoPac = sexoPac;
	}
	public String getTelefonoPac() {
		return telefonoPac;
	}
	public void setTelefonoPac(String telefonoPac) {
		this.telefonoPac = telefonoPac;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getTipoSocioNeg() {
		return tipoSocioNeg;
	}
	public void setTipoSocioNeg(String tipoSocioNeg) {
		this.tipoSocioNeg = tipoSocioNeg;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}
	public String getHoraMin() {
		return horaMin;
	}
	public void setHoraMin(String horaMin) {
		this.horaMin = horaMin;
	}
	public String getClaveUsuario() {
		return claveUsuario;
	}
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}
	public String getEtiquetaTipoDoc() {
		return etiquetaTipoDoc;
	}
	public void setEtiquetaTipoDoc(String etiquetaTipoDoc) {
		this.etiquetaTipoDoc = etiquetaTipoDoc;
	}
	public String getParamsFE_ImprimeFactYEnviaMail() {
		return paramsFE_ImprimeFactYEnviaMail;
	}
	public void setParamsFE_ImprimeFactYEnviaMail(
			String paramsFE_ImprimeFactYEnviaMail) {
		this.paramsFE_ImprimeFactYEnviaMail = paramsFE_ImprimeFactYEnviaMail;
	}
	public String getClaveUsuarioFact() {
		return claveUsuarioFact;
	}
	public void setClaveUsuarioFact(String claveUsuarioFact) {
		this.claveUsuarioFact = claveUsuarioFact;
	}
	public String getNombreUsuarioFact() {
		return nombreUsuarioFact;
	}
	public void setNombreUsuarioFact(String nombreUsuarioFact) {
		this.nombreUsuarioFact = nombreUsuarioFact;
	}
	
}
