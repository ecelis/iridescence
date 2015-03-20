package org.compiere.util.cuestionarios;

import java.util.ArrayList;

public class Cuestionario_VO {
	
	private Integer cuestionarioId = null;
	private String  cuestionarioName = null;
	private ArrayList<Pregunta_VO> cuestionario = new ArrayList<Pregunta_VO> ();
	private int cuestEspID = 0;
	
	public int getCuestEspID() {
		return cuestEspID;
	}

	public void setCuestEspID(int cuestEspID) {
		this.cuestEspID = cuestEspID;
	}

	public Cuestionario_VO(){}
	
	public Integer getCuestionarioId() {
		return cuestionarioId;
	}
	public void setCuestionarioId(Integer cuestionarioId) {
		this.cuestionarioId = cuestionarioId;
	}
	public String getCuestionarioName() {
		return cuestionarioName;
	}
	public void setCuestionarioName(String cuestionarioName) {
		this.cuestionarioName = cuestionarioName;
	}

	public ArrayList<Pregunta_VO> getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(ArrayList<Pregunta_VO> cuestionario) {
		this.cuestionario = cuestionario;
	}
	

}
