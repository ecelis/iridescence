package org.compiere.util.mo;

public class MO_ProblemaDental_VO {
	
	private Integer problemaId = null;
	private String name = null;
	private String desc = null;
	private String value = null;
	
	private String color1 = null;
	private String color2 = null;
	private String color3 = null;
	private String imagen = null;
	private String paraDiagrama = null;
	private String esOdonto = null;
	private Boolean isAdded = new Boolean(false);
	private String sano = "N";
	
	
	public MO_ProblemaDental_VO(){}
	
	public Integer getProblemaId() {
		return problemaId;
	}
	public void setProblemaId(Integer problemaId) {
		this.problemaId = problemaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor1() {
		return color1;
	}
	public void setColor1(String color1) {
		this.color1 = color1;
	}
	public String getColor2() {
		return color2;
	}
	public void setColor2(String color2) {
		this.color2 = color2;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getParaDiagrama() {
		return paraDiagrama;
	}
	public void setParaDiagrama(String paraDiagrama) {
		this.paraDiagrama = paraDiagrama;
	}
	public String getEsOdonto() {
		return esOdonto;
	}
	public void setEsOdonto(String esOdonto) {
		this.esOdonto = esOdonto;
	}

	public Boolean getIsAdded() {
		return isAdded;
	}

	public void setIsAdded(Boolean isAdded) {
		this.isAdded = isAdded;
	}

	public String getColor3() {
		return color3;
	}

	public void setColor3(String color3) {
		this.color3 = color3;
	}

	public String getSano() {
		return sano;
	}

	public void setSano(String sano) {
		this.sano = sano;
	}
	

}
