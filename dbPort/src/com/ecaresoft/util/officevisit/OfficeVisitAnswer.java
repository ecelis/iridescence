package com.ecaresoft.util.officevisit;

import java.sql.Timestamp;

/**
 * @author odelarosa
 *
 */
public class OfficeVisitAnswer {

	private String answare;
	private Timestamp appointment;
	private String ctaPacNo;
	private Timestamp dob;
	private String doctor;
	private String lastDoctor;
	private String mrn;
	private String pacName;
	private String question;
	private String sex;
	private Integer total;

	/**
	 * 
	 */
	public OfficeVisitAnswer() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfficeVisitAnswer other = (OfficeVisitAnswer) obj;
		if (answare == null) {
			if (other.answare != null)
				return false;
		} else if (!answare.equals(other.answare))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	public String getAnsware() {
		return answare;
	}

	public Timestamp getAppointment() {
		return appointment;
	}

	public String getCtaPacNo() {
		return ctaPacNo;
	}

	public Timestamp getDob() {
		return dob;
	}

	public String getDoctor() {
		return doctor;
	}

	public String getLastDoctor() {
		return lastDoctor;
	}

	public String getMrn() {
		return mrn;
	}

	public String getPacName() {
		return pacName;
	}

	public String getQuestion() {
		return question;
	}

	public String getSex() {
		return sex;
	}

	public Integer getTotal() {
		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answare == null) ? 0 : answare.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}

	public void setAnsware(String answare) {
		this.answare = answare;
	}

	public void setAppointment(Timestamp appointment) {
		this.appointment = appointment;
	}

	public void setCtaPacNo(String ctaPacNo) {
		this.ctaPacNo = ctaPacNo;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public void setLastDoctor(String lastDoctor) {
		this.lastDoctor = lastDoctor;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public void setPacName(String pacName) {
		this.pacName = pacName;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
