package org.compiere.util;

public class XYValues {
	private Number xValue;
	private Number yValue;

	public XYValues (Number xValue, Number yValue) {
		this.xValue = xValue;
		this.yValue = yValue;		
	}
	
	public Number getxValue() {
		return xValue;
	}

	public void setxValue(Number xValue) {
		this.xValue = xValue;
	}

	public Number getyValue() {
		return yValue;
	}

	public void setyValue(Number yValue) {
		this.yValue = yValue;
	}	
}
