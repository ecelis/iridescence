package org.compiere.model;

import java.util.ArrayList;
import java.util.List;

import org.compiere.util.ValueNamePair;

public class EncounterReport {

	private List<CuestionarioView> lstCuestionario = new ArrayList<CuestionarioView>();
	private List<ValueNamePair> lsDiagnostics = new ArrayList<ValueNamePair>();
	private List<String> lstAllergys = new ArrayList<String>();
	private List<Questionnaire> lstQuestionnaires = new ArrayList<Questionnaire>();
	private String lstPrescriptions = null;
	private List<ValueNamePair> lstSigVital = new ArrayList<ValueNamePair>();
	private MEXMEPaciente paciente = null;
	private int ctapac = 0;
	private int actPaciente = 0;
	private String title = null;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public MEXMEPaciente getPaciente() {
		return paciente;
	}
	public void setPaciente(MEXMEPaciente paciente) {
		this.paciente = paciente;
	}
	public List<CuestionarioView> getLstCuestionario() {
		return lstCuestionario;
	}
	public void setLstCuestionario(List<CuestionarioView> lstCuestionario) {
		this.lstCuestionario = lstCuestionario;
	}
	public List<ValueNamePair> getLsDiagnostics() {
		return lsDiagnostics;
	}
	public void setLsDiagnostics(List<ValueNamePair> lsDiagnostics) {
		this.lsDiagnostics = lsDiagnostics;
	}
	public String getLstPrescriptions() {
		return lstPrescriptions;
	}
	public void setLstPrescriptions(String lstPrescriptions) {
		this.lstPrescriptions = lstPrescriptions;
	}
	public List<ValueNamePair> getLstSigVital() {
		return lstSigVital;
	}
	public void setLstSigVital(List<ValueNamePair> lstSigVital) {
		this.lstSigVital = lstSigVital;
	}
	public int getCtapac() {
		return ctapac;
	}
	public void setCtapac(int ctapac) {
		this.ctapac = ctapac;
	}
	public int getActPaciente() {
		return actPaciente;
	}
	public void setActPaciente(int actPaciente) {
		this.actPaciente = actPaciente;
	}
	public List<String> getLstAllergys() {
		return lstAllergys;
	}
	public void setLstAllergys(List<String> lstAllergys) {
		this.lstAllergys = lstAllergys;
	}
	public List<Questionnaire> getLstQuestionnaires() {
		return lstQuestionnaires;
	}
	public void setLstQuestionnaires(List<Questionnaire> lstQuestionnaires) {
		this.lstQuestionnaires = lstQuestionnaires;
	}
	
	
}
