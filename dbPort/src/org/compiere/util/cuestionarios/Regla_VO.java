package org.compiere.util.cuestionarios;

public class Regla_VO {
	
	private Integer cuestionarioId = null;
	private Integer preguntaId = null;
	private Integer pregCondicionante = null;
	private Integer pregModifica = null;
	private Integer tipoPregunta = null;
	private String banderaResp = null;
	private String banderaResp2 = null;
	private Integer banderaRespLstId = null;
	private Integer listaId = null;
	private String respLista = null;
	
	public Regla_VO(){}
	
	public Integer getCuestionarioId() {
		return cuestionarioId;
	}
	public void setCuestionarioId(Integer cuestionarioId) {
		this.cuestionarioId = cuestionarioId;
	}
	public Integer getPreguntaId() {
		return preguntaId;
	}
	public void setPreguntaId(Integer preguntaId) {
		this.preguntaId = preguntaId;
	}
	public Integer getPregCondicionante() {
		return pregCondicionante;
	}
	public void setPregCondicionante(Integer pregCondicionante) {
		this.pregCondicionante = pregCondicionante;
	}
	public Integer getPregModifica() {
		return pregModifica;
	}
	public void setPregModifica(Integer pregModifica) {
		this.pregModifica = pregModifica;
	}
	public Integer getTipoPregunta() {
		return tipoPregunta;
	}
	public void setTipoPregunta(Integer tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}
	public String getBanderaResp() {
		return banderaResp;
	}
	public void setBanderaResp(String banderaResp) {
		this.banderaResp = banderaResp;
	}
	public Integer getListaId() {
		return listaId;
	}
	public void setListaId(Integer listaId) {
		this.listaId = listaId;
	}
	public String getRespLista() {
		return respLista;
	}
	public void setRespLista(String respLista) {
		this.respLista = respLista;
	}

	public String getBanderaResp2() {
		return banderaResp2;
	}

	public void setBanderaResp2(String banderaResp2) {
		this.banderaResp2 = banderaResp2;
	}

	public Integer getBanderaRespLstId() {
		return banderaRespLstId;
	}

	public void setBanderaRespLstId(Integer banderaRespLstId) {
		this.banderaRespLstId = banderaRespLstId;
	}

}
