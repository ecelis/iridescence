package org.compiere.util;

public class ReglaVistaCuest {


	public ReglaVistaCuest(){	
	}
	
	private int exme_reglacuestionario_id=0;
	
	public int getExme_reglacuestionario_id() {
		return exme_reglacuestionario_id;
	}
	public void setExme_reglacuestionario_id(int exmeReglacuestionarioId) {
		exme_reglacuestionario_id = exmeReglacuestionarioId;
	}
	public int getExme_tipo_pregunta() {
		return exme_tipo_pregunta;
	}
	public void setExme_tipo_pregunta(int exmeTipoPregunta) {
		exme_tipo_pregunta = exmeTipoPregunta;
	}
	public int getExme_cuestionario_id() {
		return exme_cuestionario_id;
	}
	public void setExme_cuestionario_id(int exmeCuestionarioId) {
		exme_cuestionario_id = exmeCuestionarioId;
	}
	public int getExme_pregunta_id() {
		return exme_pregunta_id;
	}
	public void setExme_pregunta_id(int exmePreguntaId) {
		exme_pregunta_id = exmePreguntaId;
	}
	public int getPreg_condicionante() {
		return preg_condicionante;
	}
	public void setPreg_condicionante(int pregCondicionante) {
		preg_condicionante = pregCondicionante;
	}
	public int getExme_pregunta_lista_id() {
		return exme_pregunta_lista_id;
	}
	public void setExme_pregunta_lista_id(int exmePreguntaListaId) {
		exme_pregunta_lista_id = exmePreguntaListaId;
	}
	public boolean isRespuesta() {
		if(this.respuestaTxt.equalsIgnoreCase("N")){
			return false;
		}else{
			return true;
		}
		
	}
	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	private int exme_tipo_pregunta=0;
	private int exme_cuestionario_id=0;
	private int exme_pregunta_id=0;
	private int preg_condicionante=0;
	
	private int exme_pregunta_lista_id=0;
	private String exme_pregunta_lista_str="";
	private boolean respuesta;
	private String respuestaTxt = "N";
	
	private String exme_pregunta_str="";
	public String getExme_pregunta_str() {
		return exme_pregunta_str;
	}
	public void setExme_pregunta_str(String exmePreguntaStr) {
		exme_pregunta_str = exmePreguntaStr;
	}
	public String getTipoPregSelStr() {
		return tipoPregSelStr;
	}
	public void setTipoPregSelStr(String tipoPregSelStr) {
		this.tipoPregSelStr = tipoPregSelStr;
	}
	public String getPregCondicionante() {
		return pregCondicionante;
	}
	public void setPregCondicionante(String pregCondicionante) {
		this.pregCondicionante = pregCondicionante;
	}

	private String tipoPregSelStr="";
	private String pregCondicionante="";
	
	private String pregSeleccionada="";

	public String getPregSeleccionada() {
		return pregSeleccionada;
	}
	public void setPregSeleccionada(String pregSeleccionada) {
		this.pregSeleccionada = pregSeleccionada;
	}
	
	public String preCondCombo="";

	public String getPreCondCombo() {
		return preCondCombo;
	}
	public void setPreCondCombo(String preCondCombo) {
		this.preCondCombo = preCondCombo;
	}
	public String getExme_pregunta_lista_str() {
		return exme_pregunta_lista_str;
	}
	public void setExme_pregunta_lista_str(String exmePreguntaListaStr) {
		exme_pregunta_lista_str = exmePreguntaListaStr;
	}
	public String getRespuestaTxt() {
		return respuestaTxt;
	}
	public void setRespuestaTxt(String respuestaTxt) {
		this.respuestaTxt = respuestaTxt;
	}


}
