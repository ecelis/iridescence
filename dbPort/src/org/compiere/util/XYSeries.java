package org.compiere.util;

import java.awt.Color;

public class XYSeries extends org.jfree.data.xy.XYSeries {
	
	private Color color = null;	
	private boolean addAnnotation = false;
	private double xAnnotation = 0;
	

	public XYSeries(Comparable key) {
		super(key);		
	}
	
	public double getxAnnotation() {
		return xAnnotation;
	}

	public void setxAnnotation(double xAnnotation) {
		this.xAnnotation = xAnnotation;
	}

	public boolean isAddAnnotation() {
		return addAnnotation;
	}

	public void setAddAnnotation(boolean addAnnotation) {
		this.addAnnotation = addAnnotation;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
