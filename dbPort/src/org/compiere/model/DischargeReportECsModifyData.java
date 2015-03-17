package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;

/**
 * @author arangel
 *
 */
public class DischargeReportECsModifyData {
	
	private static CLogger		slog= CLogger.getCLogger(DischargeReportECsModifyData.class);
	/**
	 * Report Discharge Report eCs-Modify
	 * @param params 
	 * @param filters
	 * @return lst of values a mostrar en el reporte Discharge Report eCs-Modify 
	 */
	public static ArrayList<DischargeReportECsModifyData> getDischargeECModify(StringBuilder filters, List<Object> params, Object order){
		ArrayList<DischargeReportECsModifyData> values = new ArrayList<DischargeReportECsModifyData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int j= 1;
		try {
			
			List<Object> lsPrm = new ArrayList<Object>();
			
			lsPrm.add(Env.getAD_Client_ID(Env.getCtx()));
			lsPrm.add(Env.getAD_Org_ID(Env.getCtx()));
			if (StringUtils.isNotEmpty(filters.toString()) && !params.isEmpty()) {
				lsPrm.addAll(params);
			}
			
			StringBuilder reporte = new StringBuilder(SQLFinancialRpts.dischargeECmodify(filters.toString()));
						
			if(order != null && StringUtils.isNotEmpty(order.toString())){
				reporte.append("ORDER BY ").append(order);
			}
			
			pstm = DB.prepareStatement(reporte.toString(), null);			
			DB.setParameters(pstm, lsPrm);			
			
			rs = pstm.executeQuery();
			
			values.addAll(createListDischargeECModify(rs));
			if (MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order)) {
				Collections.sort(values, new Comparator<DischargeReportECsModifyData>() {
							public int compare(DischargeReportECsModifyData o1, DischargeReportECsModifyData o2) {
								return o1.getPacName().compareTo(o2.getPacName());
							}
						});
					}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
			
		}finally {
			DB.close(rs,pstm);
		}
		return values;
	}

	
	/**
	 * Report Discharge Report eCs-Modify
	 * @param rs
	 * @return lista de valores del reporte
	 */	
	public static ArrayList<DischargeReportECsModifyData> createListDischargeECModify(ResultSet rs){
		ArrayList<DischargeReportECsModifyData> lst = new ArrayList<DischargeReportECsModifyData>();
		try{
			while(rs.next()){
				DischargeReportECsModifyData rValues = new DischargeReportECsModifyData();
				rValues.setEncounter(rs.getString("ENCOUNTER"));
				rValues.setPacName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setPacType(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));
				rValues.setService(rs.getString("SERVICE"));
				rValues.setAdminDate(rs.getDate(MEXMECtaPac.COLUMNNAME_DateOrdered));
				rValues.setDischDate(rs.getDate(MEXMECtaPac.COLUMNNAME_FechaAlta));
				rValues.setEstadiaDias(rs.getInt("LOS"));//Lenght of stay
				rValues.setCode(rs.getString("CODE")); 
				rValues.setFinanciClass(rs.getString("FINANCIALCLASS"));
				rValues.setAttending(rs.getString(MEXMECtaPacMed.COLUMNNAME_PhysicianName));
				rValues.setMrn(rs.getString("MRN"));
				lst.add(rValues);
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
	
	private String encounter;
	private String pacName;
	private String pacType;
	private String service;	
	private Date adminDate;
	private Date dischDate;
	private int estadiaDias;
	private String code;
	private String financiClass;
	private String attending;
	private String mrn;
	
	public String getEncounter() {
		return encounter;
	}
	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}
	public String getPacName() {
		return pacName;
	}
	public void setPacName(String pacName) {
		this.pacName = pacName;
	}
	public String getPacType() {
		return pacType;
	}
	public void setPacType(String pacType) {
		this.pacType = pacType;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getAdminDate() {
		return Constantes.sdfFecha(Env.getCtx()).format(adminDate);
	}
	public void setAdminDate(Date adminDate) {
		this.adminDate = adminDate;
	}
	public String getDischDate() {
		return Constantes.sdfFecha(Env.getCtx()).format(dischDate);
	}
	public void setDischDate(Date dischDate) {
		this.dischDate = dischDate;
	}
	public String getEstadiaDias() {
		return String.valueOf(estadiaDias);
	}
	public void setEstadiaDias(int estadiaDias) {
		this.estadiaDias = estadiaDias;
	}
	public String getFinanciClass() {
		return financiClass;
	}
	public void setFinanciClass(String financiClass) {
		this.financiClass = financiClass;
	}
	public String getAttending() {
		return attending;
	}
	public void setAttending(String attending) {
		this.attending = attending;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}
}
