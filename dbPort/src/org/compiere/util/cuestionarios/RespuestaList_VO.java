package org.compiere.util.cuestionarios;

public class RespuestaList_VO {
	
	private Integer valor = null;
	private String valorTxt = null;
	private Integer preguntaId = null;
	private String descripcion = null;
	private Integer listaID = null;
	private String selected = "";
	private String seleccionadoTxt = "false";
	private boolean seleccionado = false;
	
	
	public RespuestaList_VO(){}
	
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValorTxt() {
		return valorTxt;
	}

	public void setValorTxt(String valorTxt) {
		this.valorTxt = valorTxt;
	}

	public Integer getPreguntaId() {
		return preguntaId;
	}

	public void setPreguntaId(Integer preguntaId) {
		this.preguntaId = preguntaId;
	}

	public Integer getListaID() {
		return listaID;
	}

	public void setListaID(Integer listaID) {
		this.listaID = listaID;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getSeleccionadoTxt() {
		return seleccionadoTxt;
	}

	public void setSeleccionadoTxt(String seleccionadoTxt) {
		this.seleccionadoTxt = seleccionadoTxt;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaID == null) ? 0 : listaID.hashCode());
		result = prime * result
				+ ((preguntaId == null) ? 0 : preguntaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespuestaList_VO other = (RespuestaList_VO) obj;
		if (listaID == null) {
			if (other.listaID != null)
				return false;
		} else if (!listaID.equals(other.listaID))
			return false;
		if (preguntaId == null) {
			if (other.preguntaId != null)
				return false;
		} else if (!preguntaId.equals(other.preguntaId))
			return false;
		return true;
	}
	
	

}
