package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;

/**
 * @author arangel
 * 
 */
public class EncountersNotDischargedData {
	
	private static CLogger		slog = CLogger.getCLogger (EncountersNotDischargedData.class);
	/**
	 * Report Encounters Not Discharged
	 * @return lst of values a mostrar en el reporte Encounters Not Discharged
	 */
	public static ArrayList<EncountersNotDischargedData> getEncountersNotDisData(){
		ArrayList<EncountersNotDischargedData> values = new ArrayList<EncountersNotDischargedData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
	
		try {
			String reporte = SQLFinancialRpts.encountNotDisch();
			pstm = DB.prepareStatement(reporte, null);
			pstm.setInt(1, Env.getAD_Org_ID(Env.getCtx()));
			pstm.setInt(2, Env.getAD_Client_ID(Env.getCtx()));
			rs = pstm.executeQuery();
			
			values.addAll(createListEncountersNotDisData(rs));
			
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
			
		}finally {
			DB.close(pstm);
			DB.close(rs);
		
		}
		return values;		
	}
	/**
	 * Report Encounters Not Discharged
	 * @param rs
	 * @return
	 */
	public static ArrayList<EncountersNotDischargedData> createListEncountersNotDisData(ResultSet rs){
		ArrayList<EncountersNotDischargedData> lst = new ArrayList<EncountersNotDischargedData>();
		try{
			while(rs.next()){
				EncountersNotDischargedData rValues = new EncountersNotDischargedData();
				rValues.setEncounter(rs.getString(MEXMECtaPac.COLUMNNAME_DocumentNo));
				rValues.setMrn(rs.getString("MRN"));
				rValues.setPatName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setPatType(rs.getString("Patienttype"));
				rValues.setService(rs.getString("Service"));
				rValues.setAdminDat(rs.getDate(MEXMECtaPac.COLUMNNAME_DateOrdered));
				//rValues.setFinaciClass(rs.getString("Financialclass"));
				rValues.setAdmitting(rs.getString(MEXMEMedico.COLUMNNAME_Nombre_Med));				
				lst.add(rValues);
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
	
	private String encounter;
	private String patName;
	private String patType;
	private String service;
	private Date adminDat;
	private String finaciClass;
	private String admitting;
	private String mrn;

	public String getEncounter() {
		return encounter;
	}

	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getPatType() {
		return patType;
	}

	public void setPatType(String patType) {
		this.patType = patType;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Date getAdminDat() {
		return adminDat;
	}

	public void setAdminDat(Date adminDat) {
		this.adminDat = adminDat;
	}

	public String getFinaciClass() {
		return finaciClass;
	}

	public void setFinaciClass(String finaciClass) {
		this.finaciClass = finaciClass;
	}

	public String getAdmitting() {
		return admitting;
	}

	public void setAdmitting(String admitting) {
		this.admitting = admitting;
	}
	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

}
