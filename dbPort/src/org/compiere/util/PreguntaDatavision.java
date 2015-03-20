package org.compiere.util;

import java.util.Date;

public class PreguntaDatavision {

	private String pregunta;
	private String respuesta;
	private String tableName;
	private String columnName;
	private String paramName;
	private String tipoPregunta;
	private Date fechaCreacion = null;
	private int ctaPacID = 0;
	private int pacID = 0;
	private int citaID = 0;

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getCtaPacID() {
		return ctaPacID;
	}

	public void setCtaPacID(int ctaPacID) {
		this.ctaPacID = ctaPacID;
	}

	public int getPacID() {
		return pacID;
	}

	public void setPacID(int pacID) {
		this.pacID = pacID;
	}

	public int getCitaID() {
		return citaID;
	}

	public void setCitaID(int citaID) {
		this.citaID = citaID;
	}

	public String getTipoPregunta() {
		return tipoPregunta;
	}

	public void setTipoPregunta(String tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public PreguntaDatavision() {
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
