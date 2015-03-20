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
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;

/**
 * @author arangel
 *
 */
public class EncountersDetailReportData {
	
	private static CLogger		slog= CLogger.getCLogger(EncountersDetailReportData.class);
	
	/**
	 * Encounters Detail Report
	 * @param params 
	 * @param filters
	 * @return lst of values a mostrar en Encounters Detail Report
	 */
	public static ArrayList<EncountersDetailReportData> getEncountersDetailReport(StringBuilder filters, List<Object> params, Object order){
		ArrayList<EncountersDetailReportData> values = new ArrayList<EncountersDetailReportData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int j = 1;
		try {
			StringBuilder reporte = new StringBuilder(SQLFinancialRpts.encountersDetailReport());
			if(StringUtils.isNotEmpty(filters.toString()))
				reporte.append(filters);
		
			if(order != null && StringUtils.isNotEmpty(order.toString())){
				reporte.append("ORDER BY ").append(order);
			}
			pstm = DB.prepareStatement(reporte.toString(), null);
			
			pstm.setInt(j++, Env.getAD_Org_ID(Env.getCtx()));
			pstm.setInt(j++, Env.getAD_Client_ID(Env.getCtx()));
			if (StringUtils.isNotEmpty(filters.toString())
					&& !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					//Solo filtros de fechas
					pstm.setTimestamp((i+j), (java.sql.Timestamp) params.get(i));
				}
			}
			rs = pstm.executeQuery();
			
			values.addAll(EncountersDetailReportData.createListEncountersDetailReport(rs));
			if(MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order)){
					Collections.sort(values, new Comparator<EncountersDetailReportData>() {	
	                    public int compare(EncountersDetailReportData o1, EncountersDetailReportData o2) {	                            
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
	 * Encounters Detail Report
	 * @return lst of values a mostrar en el reporte Encounters Detail Report
	 */
	public static ArrayList<EncountersDetailReportData> createListEncountersDetailReport(ResultSet rs){
		ArrayList<EncountersDetailReportData> lst = new ArrayList<EncountersDetailReportData>();
		try{
			while(rs.next()){
				EncountersDetailReportData rValues = new EncountersDetailReportData();
				rValues.setEncounter(rs.getString("ENCOUNTER"));
				rValues.setMrn(rs.getString("MRN"));
				rValues.setPacName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setAdmitType(rs.getString("ADMITTYPE"));
				rValues.setPacType(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));
				rValues.setAdmitSource(rs.getString("ADMITSOURCE"));
				rValues.setArrivalDate(rs.getDate("ARRIVALDATE"));
				rValues.setAdmitDate(rs.getDate(MEXMECtaPac.COLUMNNAME_DateOrdered));
				rValues.setDischDate(rs.getDate(MEXMECtaPac.COLUMNNAME_FechaAlta));
				rValues.setLos(rs.getInt("LOS")); 
				rValues.setCode(rs.getString("CODE"));
				rValues.setService(rs.getString("SERVICE"));
				rValues.setAdmit(rs.getString("ADMIT"));
				rValues.setAttending(rs.getString(MEXMECtaPacMed.COLUMNNAME_PhysicianName));
				rValues.setFinancialClass(rs.getString("FINANCIALCLASS"));
				rValues.setPayerClass(rs.getString("PAYERCLASS"));
				lst.add(rValues);
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
	
	private String encounter;
	private String mrn;
	private String pacName;
	private String admitType;
	private String pacType;
	private String admitSource;
	private Date admitDate;
	private Date arrivalDate;
	private Date dischDate;
	private int los;//lengh of stay
	private String code;
	private String service;
	private String admit;
	private String attending;
	private String financialClass;
	private String payerClass;
	
	public String getEncounter() {
		return encounter;
	}
	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
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
	public String getAdmitSource() {
		return admitSource;
	}
	public void setAdmitSource(String admitSource) {
		this.admitSource = admitSource;
	}
	public Date getAdmitDate() {
		return admitDate;
	}
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Date getDischDate() {
		return dischDate;
	}
	public void setDischDate(Date dischDate) {
		this.dischDate = dischDate;
	}
	public int getLos() {
		return los;
	}
	public void setLos(int los) {
		this.los = los;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getAdmit() {
		return admit;
	}
	public void setAdmit(String admit) {
		this.admit = admit;
	}
	public String getAttending() {
		return attending;
	}
	public void setAttending(String attending) {
		this.attending = attending;
	}
	public String getFinancialClass() {
		return financialClass;
	}
	public void setFinancialClass(String financialClass) {
		this.financialClass = financialClass;
	}
	public String getPayerClass() {
		return payerClass;
	}
	public void setPayerClass(String payerClass) {
		this.payerClass = payerClass;
	}
	public String getAdmitType() {
		return admitType;
	}
	public void setAdmitType(String admitType) {
		this.admitType = admitType;
	}
}
