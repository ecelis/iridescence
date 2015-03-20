package org.compiere.model;

public  class MEXMEBancoDeSangreDetView{
	
	
	private String estudioSolicitado=null;
	private String folioAnalisis = null;
	private String CURP=null;
	private String observacionesAnalisis=null;
	private String analisis=null;
	private String resultadosAnalisis = null;
	
	public String getAnalisis() {
		return analisis;
	}
	public void setAnalisis(String analisis) {
		this.analisis = analisis;
	}
	public String getCURP() {
		return CURP;
	}
	public void setCURP(String CURP) {
		this.CURP = CURP;
	}
	public String getEstudioSolicitado() {
		return estudioSolicitado;
	}
	public void setEstudioSolicitado(String estudioSolicitado) {
		this.estudioSolicitado = estudioSolicitado;
	}
	public String getFolioAnalisis() {
		return folioAnalisis;
	}
	public void setFolioAnalisis(String folioAnalisis) {
		this.folioAnalisis = folioAnalisis;
	}
	public String getObservacionesAnalisis() {
		return observacionesAnalisis;
	}
	public void setObservacionesAnalisis(String observacionesAnalisis) {
		this.observacionesAnalisis = observacionesAnalisis;
	}
	public String getResultadosAnalisis() {
		return resultadosAnalisis;
	}
	public void setResultadosAnalisis(String resultadosAnalisis) {
		this.resultadosAnalisis = resultadosAnalisis;
	}
	
}