package org.compiere.util.mo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class MO_Indicacion_VO {

	private int indicacionID = 0;
	private Timestamp fechaIndicacion = null;
	private int pacienteID = 0;
	private String tipoIndicacion = null;
	private String descripcion = null;
	private int citaID = 0;
	ArrayList<String> piezasDentalesID = new ArrayList<String>();
	ArrayList<String> indicacionesID = new ArrayList<String>();
	ArrayList<String> piezasDentalesValue = new ArrayList<String>();
	private String esOdontograma = "Y";
	
	public int getIndicacionID() {
		return indicacionID;
	}
	public void setIndicacionID(int indicacionID) {
		this.indicacionID = indicacionID;
	}
	public Timestamp getFechaIndicacion() {
		return fechaIndicacion;
	}
	public void setFechaIndicacion(Timestamp fechaIndicacion) {
		this.fechaIndicacion = fechaIndicacion;
	}
	public int getPacienteID() {
		return pacienteID;
	}
	public void setPacienteID(int pacienteID) {
		this.pacienteID = pacienteID;
	}
	public String getTipoIndicacion() {
		return tipoIndicacion;
	}
	public void setTipoIndicacion(String tipoIndicacion) {
		this.tipoIndicacion = tipoIndicacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCitaID() {
		return citaID;
	}
	public void setCitaID(int citaID) {
		this.citaID = citaID;
	}
	public ArrayList<String> getPiezasDentalesID() {
		return piezasDentalesID;
	}
	public void setPiezasDentalesID(ArrayList<String> piezasDentalesID) {
		this.piezasDentalesID = piezasDentalesID;
	}
	public ArrayList<String> getIndicacionesID() {
		return indicacionesID;
	}
	public void setIndicacionesID(ArrayList<String> indicacionesID) {
		this.indicacionesID = indicacionesID;
	}
	public ArrayList<String> getPiezasDentalesValue() {
		return piezasDentalesValue;
	}
	public void setPiezasDentalesValue(ArrayList<String> piezasDentalesValue) {
		this.piezasDentalesValue = piezasDentalesValue;
	}	
	public String getEsOdontograma() {
		return esOdontograma;
	}
	public void setEsOdontograma(String esOdontograma) {
		this.esOdontograma = esOdontograma;
	}
	public MO_Indicacion_VO copiar(){
		MO_Indicacion_VO nuevo = new MO_Indicacion_VO();
		
		nuevo.setIndicacionID(indicacionID);
		nuevo.setCitaID(citaID);
		nuevo.setPacienteID(pacienteID);
		nuevo.setDescripcion(descripcion);
		nuevo.setTipoIndicacion(tipoIndicacion);
		nuevo.setPiezasDentalesID(piezasDentalesID);
		nuevo.setFechaIndicacion(fechaIndicacion);
		nuevo.setIndicacionesID(indicacionesID);
		nuevo.setPiezasDentalesValue(piezasDentalesValue);
		nuevo.setEsOdontograma(esOdontograma);
		
		return nuevo;
	}
	
	public void copiarPiezasDentales(String dientesSeleccionados) {
		
		ArrayList<String> piezasLst = new ArrayList<String>();
				
		if(dientesSeleccionados!=null){
			Scanner tokens = new Scanner(dientesSeleccionados); 
			tokens.useDelimiter("@@");
			while(tokens.hasNext()) {
				piezasLst.add(tokens.next()); 
	        }
			tokens.close();
		}
		
		piezasDentalesID = piezasLst;
		
	}
	
	
	
	
	
	
}
