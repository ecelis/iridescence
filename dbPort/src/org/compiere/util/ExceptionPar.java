package org.compiere.util;

public class ExceptionPar extends Exception {

	private static final long serialVersionUID = 1L;
	private Object customObject = null;
	
	public Object getCustomObject() {
		return customObject;
	}
	public void setCustomObject(Object customObject) {
		this.customObject = customObject;
	}
	public ExceptionPar(String message, Object customObject) {
		super(message);
		this.customObject = customObject;
	}
	
}
