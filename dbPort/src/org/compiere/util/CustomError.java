package org.compiere.util;


public class CustomError {
	private String error;
	private Object[] params;	
	
	public CustomError() {
		this.params = new Object[0];
	}
	
	public CustomError(String error, Object[] params) {
		this.error = error;
		this.params = params;
	}

	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public Object[] getParams() {
		return params;
	}
	
	public void setParams(Object[] params) {
		this.params = params;
	}
}
