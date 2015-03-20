package com.ecaresoft.util.officevisit;

import java.util.List;

/**
 * @author odelarosa
 *
 */
public abstract class AReportParameter {
	public static final int DATE = 1;
	public static final int LOGICAL = 2;
	public static final int MULTIPLE = 3;
	public static final int NUMERIC = 5;
	public static final int OPTION = 4;
	private int id;

	private String name;
	private int type;
	public AReportParameter(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public abstract List<Object> getParams();

	public abstract String getSql();

	public abstract String getStrValue();

	public int getType() {
		return type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(int type) {
		this.type = type;
	}

}
