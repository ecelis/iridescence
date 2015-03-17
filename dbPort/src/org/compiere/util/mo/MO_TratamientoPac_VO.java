package org.compiere.util.mo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import org.compiere.model.MEXMETratamientos;

public class MO_TratamientoPac_VO {
	
	private ArrayList<String> tratamientosPacienteID = new ArrayList<String>();;
	private String nombreTratamiento = null;
	private ArrayList<String> piezasDentalesID = new ArrayList<String>();;
	private ArrayList<String> piezasDentalesValue = new ArrayList<String>();;
	private int citaID = 0;
	private String esOdontogama = "Y";
	private String programarCitas = "N";
	private int tratamientoID = 0;
	private Timestamp fechaTratamiento = null;
	private String descripcion = null;
	private String iniciado = "N";
	private int pacienteID = 0;
	private String value="";
	private ArrayList<MEXMETratamientos> detTratamientoLst = new ArrayList<MEXMETratamientos>();
	private String code = null;
	public int citaNo = 0;
	private int subEspecialidadID = 0;
	private String nombreSubEspecialidad = null;
		
	public String getNombreSubEspecialidad() {
		return nombreSubEspecialidad;
	}
	public void setNombreSubEspecialidad(String nombreSubEspecialidad) {
		this.nombreSubEspecialidad = nombreSubEspecialidad;
	}
	public int getSubEspecialidadID() {
		return subEspecialidadID;
	}
	public void setSubEspecialidadID(int subEspecialidadID) {
		this.subEspecialidadID = subEspecialidadID;
	}
	public ArrayList<String> getTratamientosPacienteID() {
		return tratamientosPacienteID;
	}
	public void setTratamientosPacienteID(ArrayList<String> tratamientosPacienteID) {
		this.tratamientosPacienteID = tratamientosPacienteID;
	}
	public String getNombreTratamiento() {
		return nombreTratamiento;
	}
	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
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
	public int getCitaID() {
		return citaID;
	}
	public void setCitaID(int citaID) {
		this.citaID = citaID;
	}
	public String getEsOdontogama() {
		return esOdontogama;
	}
	public void setEsOdontogama(String esOdontogama) {
		this.esOdontogama = esOdontogama;
	}
	public String getProgramarCitas() {
		return programarCitas;
	}
	public void setProgramarCitas(String programarCitas) {
		this.programarCitas = programarCitas;
	}
	public int getTratamientoID() {
		return tratamientoID;
	}
	public void setTratamientoID(int tratamientoID) {
		this.tratamientoID = tratamientoID;
	}
	public Timestamp getFechaTratamiento() {
		return fechaTratamiento;
	}
	public void setFechaTratamiento(Timestamp fechaTratamiento) {
		this.fechaTratamiento = fechaTratamiento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIniciado() {
		return iniciado;
	}
	public void setIniciado(String iniciado) {
		this.iniciado = iniciado;
	}
	public int getPacienteID() {
		return pacienteID;
	}
	public void setPacienteID(int pacienteID) {
		this.pacienteID = pacienteID;
	}
	public ArrayList<MEXMETratamientos> getDetTratamientoLst() {
		return detTratamientoLst;
	}
	public void setDetTratamientoLst(ArrayList<MEXMETratamientos> detTratamientoLst) {
		this.detTratamientoLst = detTratamientoLst;
	}
	public int getCitaNo() {
		return citaNo;
	}
	public void setCitaNo(int citaNo) {
		this.citaNo = citaNo;
	}
	public MO_TratamientoPac_VO copiar(){
		MO_TratamientoPac_VO nuevo = new MO_TratamientoPac_VO();
		
		nuevo.setCitaID(citaID);
		nuevo.setEsOdontogama(esOdontogama);
		nuevo.setNombreTratamiento(nombreTratamiento);
		nuevo.setPiezasDentalesID(piezasDentalesID);
		nuevo.setPiezasDentalesValue(piezasDentalesValue);
		nuevo.setProgramarCitas(programarCitas);
		nuevo.setTratamientosPacienteID(tratamientosPacienteID);
		nuevo.setTratamientoID(tratamientoID);
		nuevo.setFechaTratamiento(fechaTratamiento);
		nuevo.setDescripcion(descripcion);
		nuevo.setIniciado(iniciado);
		nuevo.setPacienteID(pacienteID);
		nuevo.setDetTratamientoLst(detTratamientoLst);
		nuevo.setCitaNo(citaNo);

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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
