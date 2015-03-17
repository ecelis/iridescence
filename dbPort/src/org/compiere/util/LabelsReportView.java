package org.compiere.util;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Clase bean para generar el reporte labels.jrxm para que funcione el query en Oracle 
 * y en Postgresql. Este reporte se ejecuta en la creacion de la cuenta paciente.
 * La clase donde se usa este bean es WDocumentsPrinting
 * 
 * @author Omar de la Rosa
 */
public class LabelsReportView {

	private BigDecimal age;
	private Timestamp dob;
	private String documentNo;
	private String mrn;
	private String name;
	private String sex;
	private String admitting;
	private String admit;

	/**
	 * Obtiene la edad del paciente
	 * 
	 * @return age la edad del paciente
	 */
	public BigDecimal getAge() {
		return age;
	}

	/**
	 * Obtiene la fecha de nacimiento
	 * 
	 * @return dob la fecha de nacimiento
	 */
	public Timestamp getDob() {
		return dob;
	}

	/**
	 * Obtiene el Numero de documento
	 * 
	 * @return documentNo el numero de documento del paciente
	 */
	public String getDocumentNo() {
		return documentNo;
	}
	
	/**
	 * Obtiene el Numero de registro medico
	 * 
	 * @return mrn el Numero de registro medico
	 */
	public String getMrn() {
		return mrn;
	}

	/**
	 * Obtiene el Nombre completo del paciente
	 * 
	 * @return name el Nombre completo del paciente
	 */
	public String getName() {
		return name;
	}

	/**
	 * Obtiene el Sexo del paciente
	 * 
	 * @return name el Sexo del paciente
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Setea la edad del paciente en la variable age.
	 * 
	 * @param age la edad del paciente.
	 */
	public void setAge(BigDecimal age) {
		this.age = age;
	}

	/**
	 * Setea la fecha de nacimiento del paciente en la variable dob.
	 * 
	 * @param dob el numero de documento del paciente.
	 */
	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	/**
	 * Setea el numero de documento del paciente en la variable documentNo.
	 * 
	 * @param documentNo el numero de documento del paciente.
	 */
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	/**
	 * Setea el mrn del paciente en la variable mrn.
	 * 
	 * @param mrn el registro medico del paciente.
	 */
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	/**
	 * Setea el nombre del paciente en la variable name.
	 * 
	 * @param name el nombre del paciente.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setea el sexo del paciente en la variable sex.
	 * 
	 * @param sex el sexo del paciente.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * Obtiene el medico tratante del paciente
	 * 
	 * @return attending el medico tratante del paciente
	 */
	public String getAdmitting() {
		return admitting;
	}
	
	/**
	 * Setea el medico tratante del paciente.
	 * 
	 * @param attending el medico tratante del paciente.
	 */
	public void setAdmitting(String admitting) {
		this.admitting = admitting;
	}

	/**
	 * Obtiene la fecha de ingreso del paciente
	 * 
	 * @return admit la fecha de ingreso del paciente
	 */
	public String getAdmit() {
		return admit;
	}

	/**
	 * Setea la fecha de ingreso del paciente.
	 * 
	 * @param admit la fecha de ingreso del paciente.
	 */
	public void setAdmit(String admit) {
		this.admit = admit;
	}	
}
