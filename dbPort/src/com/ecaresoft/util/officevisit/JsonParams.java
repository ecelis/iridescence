package com.ecaresoft.util.officevisit;

import java.util.List;

/**
 * @author odelarosa
 *
 */
public class JsonParams {
	
	private boolean addAge;
	private int age;
	private int age2;
	private List<AReportParameter> params;
	private String sex;

	/**
	 * 
	 */
	public JsonParams() {
		
	}

	public int getAge() {
		return age;
	}

	public int getAge2() {
		return age2;
	}

	public List<AReportParameter> getParams() {
		return params;
	}

	public String getSex() {
		return sex;
	}

	public boolean isAddAge() {
		return addAge;
	}

	public void setAddAge(boolean addAge) {
		this.addAge = addAge;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAge2(int age2) {
		this.age2 = age2;
	}

	public void setParams(List<AReportParameter> params) {
		this.params = params;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
