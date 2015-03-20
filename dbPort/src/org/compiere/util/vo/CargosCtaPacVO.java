package org.compiere.util.vo;

import java.io.Serializable;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.GridItem;

public class CargosCtaPacVO  implements Serializable, GridItem {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int secuencia = 0;
	private String fecha = null;
	private String articulo = null;
	private String descripcion = null;
	private int cantidad = 0;
	private String origen = null;
	private String usuario = null;
	private String hora = null;
	
	public CargosCtaPacVO(){}
	
	public CargosCtaPacVO(int columnID){
		this.secuencia = columnID;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
		return new String[] { "secuenciaTxt","fecha", "articulo", "descripcion", "cantidadTxt", "origen", "usuario", "hora" };
	}
	
	

	public int getSecuencia() {
		return secuencia;
	}
		
	public String getSecuenciaTxt() {
		return secuencia+"";
	}

	public String getCantidadTxt() {
		return cantidad+"";
	}

	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public IDColumn getIdColumn() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
