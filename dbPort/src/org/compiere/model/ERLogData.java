package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author arangel
 *
 */
public class ERLogData {
	
	private static CLogger slog = CLogger.getCLogger (ERLogData.class);
	
	/**
	 * Report ER Log
	 * @param params 
	 * @param filters
	 * @return lst of values a mostrar en el reporte Census by Date
	 */
	public static ArrayList<ERLogData> getERLog(String filters, List<Object> params, Object order){
		ArrayList<ERLogData> values = new ArrayList<ERLogData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int j = 1;
		try {
			StringBuilder reporte = new StringBuilder(SQLFinancialRpts.eRLog());
			if(StringUtils.isNotEmpty(filters))
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
			
			values.addAll(ERLogData.createListERLog(rs));
			
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
			
		}finally {
			DB.close(rs, pstm);		
		}
		return values;
	}	
	
	public static ArrayList<ERLogData> createListERLog(ResultSet rs){
		ArrayList<ERLogData> lst = new ArrayList<ERLogData>();
		try{
			while(rs.next()){
				ERLogData rValues = new ERLogData();
				rValues.setMrn(rs.getString("MRN"));
				rValues.setEncounter(rs.getString("ENCOUNTER"));
				rValues.setArrival(rs.getDate("ARRIVALDATE"));
				rValues.setArrivalTime(rs.getString("TIMEARRIVE"));
				rValues.setAdmit(rs.getDate("ADMIT"));
				rValues.setAdmitTime(rs.getString("TIMEADMIT"));
				rValues.setDischarge(rs.getDate(MEXMECtaPac.COLUMNNAME_FechaAlta));
				rValues.setDischargeTime(rs.getString("TIMEDISCHAR"));
				rValues.setDischargeStatus(rs.getString(MEXMEDischargeStatus.COLUMNNAME_Name));
				rValues.setPacTipo(rs.getString("PATIENTTYPE"));
				rValues.setAdminTipo(rs.getString("ADMITTYPE"));
				rValues.setAdminSource(rs.getString("ADMITSOURCE"));
				rValues.setFinancialClass(rs.getString("FINANCIALCLASS"));
				lst.add(rValues);
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}

	private String mrn;
	private String encounter;
	private Date arrival;
	private String arrivalTime;
	private Date admit;
	private String admitTime;
	private Date discharge;
	private String dischargeTime;
	private String dischargeStatus;
	private String pacTipo;
	private String adminTipo;
	private String adminSource;
	private String financialClass;
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}
	public String getEncounter() {
		return encounter;
	}
	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Date getAdmit() {
		return admit;
	}
	public void setAdmit(Date admit) {
		this.admit = admit;
	}
	public String getAdmitTime() {
		return admitTime;
	}
	public void setAdmitTime(String admitTime) {
		this.admitTime = admitTime;
	}
	public Date getDischarge() {
		return discharge;
	}
	public void setDischarge(Date discharge) {
		this.discharge = discharge;
	}
	public String getDischargeTime() {
		return dischargeTime;
	}
	public void setDischargeTime(String dischargeTime) {
		this.dischargeTime = dischargeTime;
	}
	public String getDischargeStatus() {
		return dischargeStatus;
	}
	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}
	public String getPacTipo() {
		return pacTipo;
	}
	public void setPacTipo(String pacTipo) {
		this.pacTipo = pacTipo;
	}
	public String getAdminTipo() {
		return adminTipo;
	}
	public void setAdminTipo(String adminTipo) {
		this.adminTipo = adminTipo;
	}
	public String getAdminSource() {
		return adminSource;
	}
	public void setAdminSource(String adminSource) {
		this.adminSource = adminSource;
	}
	public String getFinancialClass() {
		return financialClass;
	}
	public void setFinancialClass(String financialClass) {
		this.financialClass = financialClass;
	}	
}
