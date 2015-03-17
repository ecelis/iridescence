/**
 * 
 */
package org.compiere.model;

/**
 * @author twry
 *
 */
public class NivelBean {
	
	public NivelBean (final int pNivel, final String pNombre,
			final String pColumna, final String pTabla){
		nombre  = pNombre;
		nivel   = pNivel;
		columna = pColumna;
		tabla   = pTabla;
	}
	
	private String nombre = null;
	private int nivel = 0;
	private String columna = null;
	private String tabla = null;
	private String select = null;
	private String where = null;
	private String group = null;
		
	public String getSelect() {
		return select;
	}
	public void setSelect(final String select) {
		this.select = select;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(final String where) {
		this.where = where;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(final String group) {
		this.group = group;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(final int nivel) {
		this.nivel = nivel;
	}
	public String getColumna() {
		return columna;
	}
	public void setColumna(final String columna) {
		this.columna = columna;
	}
	public String getTabla() {
		return tabla;
	}
	public void setTabla(final String tabla) {
		this.tabla = tabla;
	}

}
