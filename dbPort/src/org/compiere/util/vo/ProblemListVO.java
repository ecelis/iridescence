package org.compiere.util.vo;


public class ProblemListVO {
	
	private String admType = "";
	private String disDate ="";
	private String code = "";
	private String description = "";
	private String comments = "";
	private int	   actPacientDiagID = 0;
	private String user = "";
	private String estatus = "";

	public ProblemListVO(){}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getAdmType() {
		return admType;
	}

	public void setAdmType(String admType) {
		this.admType = admType;
	}

	public String getDisDate() {
		return disDate;
	}

	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getActPacientDiagID() {
		return actPacientDiagID;
	}

	public void setActPacientDiagID(int actPacientDiagID) {
		this.actPacientDiagID = actPacientDiagID;
	}
	
	
	
	
}
