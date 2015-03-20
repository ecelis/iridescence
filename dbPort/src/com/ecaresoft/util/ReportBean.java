package com.ecaresoft.util;

import java.util.HashMap;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author odelarosa
 * 
 */
public class ReportBean {
	private JRDataSource dataSource;
	private HashMap<String, Object> params;
	private String subReport;

	public ReportBean(String subReport) {
		this.subReport = subReport;
	}

	public ReportBean(String subReport, JRDataSource dataSource) {
		this(subReport);
		this.dataSource = dataSource;
	}
	
	public ReportBean(String subReport, JRDataSource dataSource, HashMap<String, Object> params) {
		super();
		this.dataSource = dataSource;
		this.params = params;
		this.subReport = subReport;
	}

	public JRDataSource getDataSource() {
		return dataSource;
	}

	public HashMap<String, Object> getParams() {
		return params;
	}

	public String getSubReport() {
		return subReport;
	}

	public void setDataSource(JRDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}

	public void setSubReport(String subReport) {
		this.subReport = subReport;
	}
}
