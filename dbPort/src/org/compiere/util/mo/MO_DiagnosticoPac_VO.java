package org.compiere.util.mo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class MO_DiagnosticoPac_VO {
	
	private ArrayList<String> diagnosticosPacID = new ArrayList<String>();
	private int pacienteID = 0;
	private int citaID = 0;
	private Timestamp fechaDiagnostico = null;
	private int diagnosticoID = 0;
	private String nombreDiagnostico = null;
	private ArrayList<String> piezasDentalesID = new ArrayList<String>();
	private ArrayList<String> piezasDentalesValue = new ArrayList<String>();
	private String value="";
	private String descripcion = null;
	private String esOdontogama = "Y";
	
	
	public ArrayList<String> getDiagnosticosPacID() {
		return diagnosticosPacID;
	}
	public void setDiagnosticosPacID(ArrayList<String> diagnosticosPacID) {
		this.diagnosticosPacID = diagnosticosPacID;
	}
	public int getPacienteID() {
		return pacienteID;
	}
	public void setPacienteID(int pacienteID) {
		this.pacienteID = pacienteID;
	}
	public int getCitaID() {
		return citaID;
	}
	public void setCitaID(int citaID) {
		this.citaID = citaID;
	}
	public Timestamp getFechaDiagnostico() {
		return fechaDiagnostico;
	}
	public void setFechaDiagnostico(Timestamp fechaDiagnostico) {
		this.fechaDiagnostico = fechaDiagnostico;
	}
	public int getDiagnosticoID() {
		return diagnosticoID;
	}
	public void setDiagnosticoID(int diagnosticoID) {
		this.diagnosticoID = diagnosticoID;
	}
	public String getNombreDiagnostico() {
		return nombreDiagnostico;
	}
	public void setNombreDiagnostico(String nombreDiagnostico) {
		this.nombreDiagnostico = nombreDiagnostico;
	}
	public ArrayList<String> getPiezasDentalesID() {
		return piezasDentalesID;
	}
	public void setPiezasDentalesID(ArrayList<String> piezasDentalesID) {
		this.piezasDentalesID = piezasDentalesID;
	}
	public ArrayList<String> getPiezasDentalesValue() {
		return piezasDentalesValue;
	}
	public void setPiezasDentalesValue(ArrayList<String> piezasDentalesValue) {
		this.piezasDentalesValue = piezasDentalesValue;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEsOdontogama() {
		return esOdontogama;
	}
	public void setEsOdontogama(String esOdontogama) {
		this.esOdontogama = esOdontogama;
	}
	
	public MO_DiagnosticoPac_VO copiar(){
		MO_DiagnosticoPac_VO nuevo = new MO_DiagnosticoPac_VO();
		
		nuevo.setCitaID(citaID);
		nuevo.setEsOdontogama(esOdontogama);
		nuevo.setNombreDiagnostico(nombreDiagnostico);
		nuevo.setPiezasDentalesID(piezasDentalesID);
		nuevo.setPiezasDentalesValue(piezasDentalesValue);
		nuevo.setDiagnosticoID(diagnosticoID);
		nuevo.setFechaDiagnostico(fechaDiagnostico);
		nuevo.setDescripcion(descripcion);
		nuevo.setPacienteID(pacienteID);

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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	

}
