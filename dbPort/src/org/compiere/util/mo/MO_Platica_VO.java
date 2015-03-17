package org.compiere.util.mo;

public class MO_Platica_VO {
	
	private Integer id = null;
	private String name = null;
	private String description = null;
	private String value = null;
	private Integer pac = null;
	private Integer cita = null;
	private Integer platicapacienteID = null;
	
	private Boolean seleccionado = null;
	private Boolean seModifico = null;
	private Boolean disabled = null;
	private String txtDisabled = null;
	private boolean disa_bled = true;
	
public MO_Platica_VO(){}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

public Integer getPac() {
	return pac;
}

public void setPac(Integer pac) {
	
if(pac == null || pac.intValue() == 0){
		
		this.seModifico = new Boolean(false);
		this.disabled = new Boolean(false);
		this.seleccionado = new Boolean(false);
		this.txtDisabled="false";
		
	}else{
		
		
		this.seModifico = new Boolean(true);
		this.disabled = new Boolean(true);
		this.seleccionado = new Boolean(true);
		this.txtDisabled="false";
	}
	
	this.pac = pac;
}

public Integer getCita() {
	return cita;
}

public void setCita(Integer cita) {
	this.cita = cita;
}

public Integer getPlaticapacienteID() {
	return platicapacienteID;
}

public void setPlaticapacienteID(Integer platicapacienteID) {
	this.platicapacienteID = platicapacienteID;
}

public Boolean getSeleccionado() {
	
	
	return seleccionado;
}

public void setSeleccionado(Boolean seleccionado) {
	this.seleccionado = seleccionado;
}

public Boolean getSeModifico() {
	

	return seModifico;
}

public void setSeModifico(Boolean seModifico) {
	this.seModifico = seModifico;
}

public Boolean getDisabled() {
	
	
	
	return disabled;
}

public void setDisabled(Boolean disabled) {
	this.disabled = disabled;
}

public String getTxtDisabled() {
	return txtDisabled;
}

public void setTxtDisabled(String txtDisabled) {
	this.txtDisabled = txtDisabled;
}

public boolean isDisa_bled() {
	if(this.disabled.booleanValue()){
		this.disa_bled = disabled.booleanValue();
	}else{
		
		this.disa_bled = disabled.booleanValue();
	}
	return disa_bled;
}

public void setDisa_bled(boolean disa_bled) {
	this.disa_bled = disa_bled;
}


}
