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
public class PatientResgistrationData {
	
	private static CLogger		slog = CLogger.getCLogger (PatientResgistrationData.class);
   	/**
   	 * Patient Registration Master
   	 * @return lista de datos para el reporte de Patient Registration Master
   	 */
	public static ArrayList<PatientResgistrationData> getPatientsReportMaster() {
		ArrayList<PatientResgistrationData> values = new ArrayList<PatientResgistrationData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			String reporte = SQLFinancialRpts.patientRegisMaster();
			pstm = DB.prepareStatement(reporte, null);
			pstm.setInt(1, MEXMEConsentimientoPac.RESSTATUS_AD_Reference_ID);
			pstm.setInt(2, Env.getAD_Org_ID(Env.getCtx()));
			rs = pstm.executeQuery();

			values.addAll(createListPacienteMaster(rs));

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);

		} finally {
			DB.close(pstm);
			DB.close(rs);

		}
		return values;
	}
	/**
	 * Patient Registration Master
	 * @param rs datos
	 * @return lista de datps
	 */
	public static ArrayList<PatientResgistrationData> createListPacienteMaster(ResultSet rs){
		ArrayList<PatientResgistrationData> lst = new ArrayList<PatientResgistrationData>();
		try{
			while(rs.next()){
				PatientResgistrationData rValues = new PatientResgistrationData();
				rValues.setMrn(rs.getString(MEXMEHistExp.COLUMNNAME_DocumentNo));
				rValues.setpName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setAddress(rs.getString(MEXMELocation.COLUMNNAME_Address1));
				rValues.setCity(rs.getString(MEXMELocation.COLUMNNAME_City));
				rValues.setState(rs.getString(MEXMELocation.COLUMNNAME_RegionName));
				rValues.setPhone(rs.getString(MEXMEPaciente.COLUMNNAME_TelParticular));
				rValues.setPostal(rs.getString(MEXMELocation.COLUMNNAME_Postal));
				rValues.setdBirth(rs.getDate(MEXMEPaciente.COLUMNNAME_FechaNac));
				rValues.setSecureNum(rs.getString(MEXMEPaciente.COLUMNNAME_Imss));
				rValues.setAdvDir(rs.getString("RESSTATUS"));
				rValues.setEmail(rs.getString(MEXMEPaciente.COLUMNNAME_EMail));

				lst.add(rValues);
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}

	private String mrn;
	private String pName;
	private String address;
	private String city;
	private String state;
	private String postal;
	private String phone;
	private Date dBirth;
	private String secureNum;
	private String advDir;
	private String email;
	
	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getdBirth() {
		return dBirth;
	}

	public void setdBirth(Date dBirth) {
		this.dBirth = dBirth;
	}

	public String getSecureNum() {
		return secureNum;
	}

	public void setSecureNum(String secureNum) {
		this.secureNum = secureNum;
	}

	public String getAdvDir() {
		return advDir;
	}

	public void setAdvDir(String advDir) {
		this.advDir = advDir;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
