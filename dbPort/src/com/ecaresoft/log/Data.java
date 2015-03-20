package com.ecaresoft.log;

import org.apache.commons.lang.StringUtils;

/**
 * @author odelarosa
 * 
 */
public class Data {

	private Column	column;
	private Object	newValue;
	private Object	oldValue;

	public Data() {}

	public Data(final Column column, final Object newValue, final Object oldValue) {
		super();
		this.column = column;
		this.newValue = newValue;
		this.oldValue = oldValue;
	}

	public Column getColumn() {
		return column;
	}

	public Object getNewValue() {
		return newValue;
	}

	public Object getOldValue() {
		return oldValue;
	}
	
	public String getNewValueStr(){
		return newValue == null ? StringUtils.EMPTY : newValue.toString();
		
	}
	
	public String getOldValueStr(){
		return oldValue == null ? StringUtils.EMPTY : oldValue.toString();
	}

	public void setColumn(final Column column) {
		this.column = column;
	}

	public void setNewValue(Object newValue) {
		if (newValue == null) {
			newValue = "NULL";
		}
		this.newValue = newValue;
	}

	public void setOldValue(Object oldValue) {
		if (oldValue == null) {
			oldValue = "NULL";
		}
		this.oldValue = oldValue;
	}

}
