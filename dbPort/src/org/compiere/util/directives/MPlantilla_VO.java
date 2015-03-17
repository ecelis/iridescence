package org.compiere.util.directives;

public class MPlantilla_VO {

	private int plantillaId;
	private String nombre;
	private String descripcion;
	private String clasifPlantilla;
	private String category;

	public int getPlantillaId() {
		
		return plantillaId;
		
	}
	
	public void setPlantillaId(int plantillaId) {
		
		this.plantillaId = plantillaId;
		
	}
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public String getDescripcion() {
		
		return descripcion;
		
	}
	
	public void setDescripcion(String descripcion) {
		
		this.descripcion = descripcion;
		
	}
	
	public String getClasifPlantilla() {
		
		return clasifPlantilla;
		
	}
	
	public void setClasifPlantilla(String clasifPlantilla) {
		
		this.clasifPlantilla = clasifPlantilla;
		
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
