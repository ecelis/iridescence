package org.compiere.util;

import java.util.List;

import org.jfree.data.xy.XYSeriesCollection;

public class CustomGraphicValues extends XYSeriesCollection {
	
	public CustomGraphicValues() {
		super();
	}	

	public void addSeries(String label, List<XYValues> list ) {

		XYSeries series = new XYSeries(label);
		for (XYValues value: list) {
			series.add(value.getxValue(), value.getyValue());
		}
		addSeries(series);
	}	
	
	public void addSerie(XYSeries series) {
		addSeries(series);
	}
	
}
