package org.compiere.util.mo;

import java.util.ArrayList;
import java.util.List;
/**
 * Vista para las Radiograf�as<p>
 *  
 * Creado:10/Junio/2009<p>
 * @author Lizeth de la Garza
 * 
 */
public class MO_Radiografias_VO {
	
	public int getRadioID() {
		return radioID;
	}
	public void setRadioID(int radioID) {
		this.radioID = radioID;
	}

	private int radioID = 0;
	
	private String nombreRadio = null;

	public String getNombreRadio() {
		return nombreRadio;
	}
	public void setNombreRadio(String nombreRadio) {
		this.nombreRadio = nombreRadio;
	}
	
	private String nota = null;
	
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	private String nombreTratam = null;
	public String getNombreTratam() {
		return nombreTratam;
	}
	public void setNombreTratam(String nombreTratam) {
		this.nombreTratam = nombreTratam;
	}
	
	private String fecha = null;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public int getTratamPacID() {
		return tratamPacID;
	}
	public void setTratamPacID(int tratamPacID) {
		this.tratamPacID = tratamPacID;
	}
	private int tratamPacID= 0;

	private String nombrePieza = null;



	public String getNombrePieza() {
		return nombrePieza;
	}
	public void setNombrePieza(String nombrePieza) {
		this.nombrePieza = nombrePieza;
	}
	
	public String getDescriptionTratam() {
		return descriptionTratam;
	}
	public void setDescriptionTratam(String descriptionTratam) {
		this.descriptionTratam = descriptionTratam;
	}
	private int tratamientoID = 0;
	public int getTratamientoID() {
		return tratamientoID;
	}
	public void setTratamientoID(int tratamientoID) {
		this.tratamientoID = tratamientoID;
	}
	
	private String descriptionTratam = null;
	
	private List<MO_Radiografias_VO> piezaDental = new ArrayList<MO_Radiografias_VO>();
	public List<MO_Radiografias_VO> getPiezaDental() {
		return piezaDental;
	}
	public void setPiezaDental(List<MO_Radiografias_VO> piezaDental) {
		this.piezaDental = piezaDental;
	}
	
	private List<MO_Radiografias_VO> tratamientoPac = new ArrayList<MO_Radiografias_VO>();

	public List<MO_Radiografias_VO> getTratamientoPac() {
		return tratamientoPac;
	}
	public void setTratamientoPac(List<MO_Radiografias_VO> tratamientoPac) {
		this.tratamientoPac = tratamientoPac;
	}


}
