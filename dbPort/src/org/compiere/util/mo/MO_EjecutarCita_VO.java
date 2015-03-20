package org.compiere.util.mo;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.compiere.model.ServicioView;

public class MO_EjecutarCita_VO {
	
	private String name = null;
	private String description = null;
	private Integer exme_Paciente_ID = new Integer(0);
	private Integer exme_Medico_ID =  new Integer(0);
	private String exme_Medico_Name = null;
	private Integer exme_Especialidad_ID =  new Integer(0);
	private Integer exme_MotivoCita_ID =  new Integer(0);
	private Timestamp fecha = null;
	private String txtfecha = null;
	private Integer exme_CitaMedica_ID =  new Integer(0);
	private String tipoArea = null;
	private Integer actPacienteID =  new Integer(0);
	private Integer exme_ActPaciente_ID =  new Integer(0);
	private Integer estacionID =  new Integer(0);
	private ArrayList<ServicioView> lstIndicacionesMO = new ArrayList<ServicioView>();
	private String surFarm = null;
	private String prioridadInd = "5";
	private String horaCadena = null;
	private ArrayList<String> errores = new ArrayList<String>();
	private String trx = null;
	
	public MO_EjecutarCita_VO(){}
	
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
	public Integer getExme_Paciente_ID() {
		return exme_Paciente_ID;
	}
	public void setExme_Paciente_ID(Integer exme_Paciente_ID) {
		this.exme_Paciente_ID = exme_Paciente_ID;
	}
	public Integer getExme_Medico_ID() {
		return exme_Medico_ID;
	}
	public void setExme_Medico_ID(Integer exme_Medico_ID) {
		this.exme_Medico_ID = exme_Medico_ID;
	}
	public Integer getExme_Especialidad_ID() {
		return exme_Especialidad_ID;
	}
	public void setExme_Especialidad_ID(Integer exme_Especialidad_ID) {
		this.exme_Especialidad_ID = exme_Especialidad_ID;
	}
	public Integer getExme_MotivoCita_ID() {
		return exme_MotivoCita_ID;
	}
	public void setExme_MotivoCita_ID(Integer exme_MotivoCita_ID) {
		this.exme_MotivoCita_ID = exme_MotivoCita_ID;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public Integer getExme_CitaMedica_ID() {
		return exme_CitaMedica_ID;
	}
	public void setExme_CitaMedica_ID(Integer exme_CitaMedica_ID) {
		this.exme_CitaMedica_ID = exme_CitaMedica_ID;
	}
	public String getTipoArea() {
		return tipoArea;
	}
	public void setTipoArea(String tipoArea) {
		this.tipoArea = tipoArea;
	}
	public Integer getActPacienteID() {
		return actPacienteID;
	}
	public void setActPacienteID(Integer actPacienteID) {
		this.actPacienteID = actPacienteID;
	}
	public Integer getExme_ActPaciente_ID() {
		return exme_ActPaciente_ID;
	}
	public void setExme_ActPaciente_ID(Integer exme_ActPaciente_ID) {
		this.exme_ActPaciente_ID = exme_ActPaciente_ID;
	}

	public Integer getEstacionID() {
		return estacionID;
	}

	public void setEstacionID(Integer estacionID) {
		this.estacionID = estacionID;
	}

	public ArrayList<ServicioView> getLstIndicacionesMO() {
		return lstIndicacionesMO;
	}

	public void setLstIndicacionesMO(ArrayList<ServicioView> lstIndicacionesMO) {
		this.lstIndicacionesMO = lstIndicacionesMO;
	}

	public String getSurFarm() {
		return surFarm;
	}

	public void setSurFarm(String surFarm) {
		this.surFarm = surFarm;
	}

	public String getPrioridadInd() {
		return prioridadInd;
	}

	public void setPrioridadInd(String prioridadInd) {
		this.prioridadInd = prioridadInd;
	}

	public String getTxtfecha() {
		return txtfecha;
	}

	public void setTxtfecha(String txtfecha) {
		this.txtfecha = txtfecha;
	}

	public String getHoraCadena() {
		return horaCadena;
	}

	public void setHoraCadena(String horaCadena) {
		this.horaCadena = horaCadena;
	}

	public String getExme_Medico_Name() {
		return exme_Medico_Name;
	}

	public void setExme_Medico_Name(String exme_Medico_Name) {
		this.exme_Medico_Name = exme_Medico_Name;
	}

	public String getTrx() {
		return trx;
	}

	public void setTrx(String trx) {
		this.trx = trx;
	}

	public ArrayList<String> getErrores() {
		return errores;
	}

	public void setErrores(ArrayList<String> errores) {
		this.errores = errores;
	}

	
}
