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
public class ObservationLogData {
	
	private static CLogger		slog= CLogger.getCLogger(ObservationLogData.class);
	
	/**
	 * Observation Log
	 * @param params 
	 * @param filters
	 * @return lst of values a mostrar en Observation Log
	 */
	public static ArrayList<ObservationLogData> getObservationLog(StringBuilder filters, List<Object> params, Object order, boolean totals){
		ArrayList<ObservationLogData> values = new ArrayList<ObservationLogData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int j = 1;
		try {
			StringBuilder reporte = new StringBuilder(SQLFinancialRpts.observationLog(totals, filters));
			if(StringUtils.isNotEmpty(filters.toString()) && !totals)
				reporte.append(filters);
				
			if(order != null && StringUtils.isNotEmpty(order.toString()) && !totals){
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
				if(totals){
					values.addAll(ObservationLogData.createListObservationLogTot(rs));
				}else{
						values.addAll(ObservationLogData.createListObservationLog(rs));
				}
			if(MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order)){
				 Collections.sort(values, new Comparator<ObservationLogData>() {

                     public int compare(ObservationLogData o1, ObservationLogData o2) {
                             
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
	 * Observation Log
	 * @return lst of values a mostrar en el reporte Observation Log
	 */
	public static ArrayList<ObservationLogData> createListObservationLog(ResultSet rs){
		ArrayList<ObservationLogData> lst = new ArrayList<ObservationLogData>();

		try{
			while(rs.next()){
				ObservationLogData rValues = new ObservationLogData();
				rValues.setEncounter(rs.getString("ENCOUNTER"));
				rValues.setPacName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setAdmitDate(rs.getDate(MEXMECtaPac.COLUMNNAME_DateOrdered));
				rValues.setAdminTime(rs.getString("TIMEADMIT"));
				rValues.setDcDate(rs.getDate(MEXMECtaPac.COLUMNNAME_FechaAlta));
				rValues.setDcTime(rs.getString("DCTIME"));
				if(DB.isPostgreSQL()){
					rValues.setHours(rs.getInt("HORASVAL"));
				}else{
					rValues.setHours(rs.getInt("HORAS"));
				}
				rValues.setPacType(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));
				rValues.setDcStatus(rs.getString("STATUS")); 
				rValues.setPrimaryFC(rs.getString("FINANCIALCLASS"));
				rValues.setAdmit(rs.getString("ADMITSOURCE"));
				rValues.setMrn(rs.getString("MRN"));
				lst.add(rValues);
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 */
	public static ArrayList<ObservationLogData> createListObservationLogTot(ResultSet rs){
		ArrayList<ObservationLogData> lst = new ArrayList<ObservationLogData>();
		try{
			while(rs.next()){
				ObservationLogData rValues =  new ObservationLogData();
				rValues.setName(rs.getString("NAME"));
				rValues.setHoras(rs.getInt("HORAS"));
				rValues.setEncountot(rs.getInt("ENCOUNTOT"));
				lst.add(rValues);
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
	
	private String encounter;
	private String pacName;
	private Date admitDate;
	private String adminTime;
	private Date dcDate;
	private String dcTime;
	private int hours;
	private String dcStatus;
	private String pacType;
	private String primaryFC;
	private String admit;
	private String mrn;
	private int patTotal;
	private int HourTotal;
	
	public int getPatTotal() {
		return patTotal;
	}
	public void setPatTotal(int patTotal) {
		this.patTotal = patTotal;
	}
	public int getHourTotal() {
		return HourTotal;
	}
	public void setHourTotal(int hourTotal) {
		HourTotal = hourTotal;
	}
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
	public Date getAdmitDate() {
		return admitDate;
	}
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}
	public String getAdminTime() {
		return adminTime;
	}
	public void setAdminTime(String adminTime) {
		this.adminTime = adminTime;
	}
	public Date getDcDate() {
		return dcDate;
	}
	public void setDcDate(Date dcDate) {
		this.dcDate = dcDate;
	}
	public String getDcTime() {
		return dcTime;
	}
	public void setDcTime(String dcTime) {
		this.dcTime = dcTime;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getDcStatus() {
		return dcStatus;
	}
	public void setDcStatus(String dcStatus) {
		this.dcStatus = dcStatus;
	}
	public String getPrimaryFC() {
		return primaryFC;
	}
	public void setPrimaryFC(String primaryFC) {
		this.primaryFC = primaryFC;
	}
	public String getAdmit() {
		return admit;
	}
	public void setAdmit(String admit) {
		this.admit = admit;
	}
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}
	public String getPacType() {
		return pacType;
	}
	public void setPacType(String pacType) {
		this.pacType = pacType;
	}

	private int horas;
	private int encountot;
	private String name;

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getEncountot() {
		return encountot;
	}

	public void setEncountot(int encountot) {
		this.encountot = encountot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}		
}
