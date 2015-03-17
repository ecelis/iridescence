package org.compiere.util.vo;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.GridItem;

public class ExtensionVO implements GridItem{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String linea = null; 	
	private String extencion = null; 	
	private String concepto = null;
	private String descripcion = null; 	
	private String cantidad = null; 	
	private String unidMedida = null; 	
	private String preUnit = null; 	
	private String porcDesc = null; 	
	private String precioNeto = null; 	
	private String estServOrig = null; 	
	private String fecha = null; 	
	private String hora = null;
	
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getExtencion() {
		return extencion;
	}
	public void setExtencion(String extencion) {
		this.extencion = extencion;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getUnidMedida() {
		return unidMedida;
	}
	public void setUnidMedida(String unidMedida) {
		this.unidMedida = unidMedida;
	}
	public String getPreUnit() {
		return preUnit;
	}
	public void setPreUnit(String preUnit) {
		this.preUnit = preUnit;
	}
	public String getPorcDesc() {
		return porcDesc;
	}
	public void setPorcDesc(String porcDesc) {
		this.porcDesc = porcDesc;
	}
	public String getPrecioNeto() {
		return precioNeto;
	}
	public void setPrecioNeto(String precioNeto) {
		this.precioNeto = precioNeto;
	}
	public String getEstServOrig() {
		return estServOrig;
	}
	public void setEstServOrig(String estServOrig) {
		this.estServOrig = estServOrig;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	@Override
	public String[] getColumns() {
		// TODO Auto-generated method stub
		return new String[] { 
				"linea", 	
				"extencion",
				"concepto",	
				"descripcion",	
				"cantidad", 	
				"unidMedida", 	
				"preUnit", 	
				"porcDesc", 	
				"precioNeto", 	
				"estServOrig", 	
				"fecha", 	
				"hora" };
	}
	@Override
	public IDColumn getIdColumn() {
		// TODO Auto-generated method stub
		return null;
	}

}
