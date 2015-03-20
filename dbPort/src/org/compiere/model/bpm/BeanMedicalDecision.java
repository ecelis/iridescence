package org.compiere.model.bpm;

public class BeanMedicalDecision {

	private StringBuilder difwkComments = new StringBuilder();
	private String comentarios = null;

	public StringBuilder getDifwkComments() {
		return difwkComments;
	}

	public void setDifwkComments(StringBuilder difwkComments) {
		this.difwkComments = difwkComments;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

}
