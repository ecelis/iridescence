/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
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
public class EncountersPayerGroupData {
	
	private static CLogger		slog= CLogger.getCLogger(ObservationLogData.class);
	
	/**
	 * Encounters Payer Group
	 * @return lst of values a mostrar en el reporte Encounters Payer Group
	 */
	public static ArrayList<EncountersPayerGroupData> createListEncountersPayerGroup(ResultSet rs, boolean totals){
		ArrayList<EncountersPayerGroupData> lst = new ArrayList<EncountersPayerGroupData>();
		try{
			if(totals){
				while(rs.next()){
					EncountersPayerGroupData rValues = new EncountersPayerGroupData();
					rValues.setName(rs.getString(MEXMEPayerClass.COLUMNNAME_Name));
					rValues.setCharge(rs.getBigDecimal("CHARGE"));
					rValues.setCounter(rs.getInt("COUNTER"));
					lst.add(rValues);
				}
			}else{
				while(rs.next()){
				EncountersPayerGroupData rValues = new EncountersPayerGroupData();
				rValues.setEncounter(rs.getString("ENCOUNTER"));
				rValues.setPatName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setAdmitDat(rs.getDate(MEXMECtaPac.COLUMNNAME_DateOrdered));
				rValues.setDcDate(rs.getDate(MEXMECtaPac.COLUMNNAME_FechaAlta));
				rValues.setPacType(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));
				rValues.setPayerGroup(rs.getString("PAYERCLASS"));	
				rValues.setEncountStepI(rs.getString("ENCINSSTEP"));
				rValues.setEncountStepP(rs.getString("ENCPROFSTEP"));
				rValues.setStatus(rs.getString("STATUS"));
				rValues.setClaimType(rs.getString("CLAIMTYPE")); 
				rValues.setBalance(rs.getBigDecimal("BALANCE"));
				lst.add(rValues);
				}
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
	
	/**
	 * Encounters Payer Group
	 * @param params 
	 * @param filters
	 * @return lst of values a mostrar en Encounters Payer Group
	 */
	public static ArrayList<EncountersPayerGroupData> getEncountersPayerGroup(StringBuilder filters, List<Object> params, Object order, boolean totals){
		ArrayList<EncountersPayerGroupData> values = new ArrayList<EncountersPayerGroupData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int j = 1;
		try {
			StringBuilder reporte = new StringBuilder(SQLFinancialRpts.encountersPayerGroup(totals, filters));
			
			if(order != null && StringUtils.isNotEmpty(order.toString()) && !totals){
				reporte.append("ORDER BY ").append(order);
			}
			pstm = DB.prepareStatement(reporte.toString(), null);
			
			if(!totals){
				pstm.setInt(j++, MEXMECtaPacExt.INSTITUTIONALSTATUS_AD_Reference_ID);
				pstm.setInt(j++, MEXMECtaPacExt.PROFESSIONALSTATUS_AD_Reference_ID);
				pstm.setInt(j++, MEXMECtaPac.ENCOUNTERSTATUS_AD_Reference_ID);
				pstm.setInt(j++, X_EXME_ClaimPaymentH.BILLINGTYPE_AD_Reference_ID);
			}
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
			
			values.addAll(createListEncountersPayerGroup(rs, totals));
			
			if(MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order) && !totals){
				 Collections.sort(values, new Comparator<EncountersPayerGroupData>() {

                     public int compare(EncountersPayerGroupData o1, EncountersPayerGroupData o2) {
                             
                             return o1.getPatName().compareTo(o2.getPatName());
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
	private String encounter;
	private String patName;
	private Date admitDat;
	private Date dcDate;
	private String pacType;
	private String payerGroup;
	private String encountStepI;
	private String encountStepP;
	private String status;
	private String claimType;
	private BigDecimal balance;
	
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
	public Date getAdmitDat() {
		return admitDat;
	}
	public void setAdmitDat(Date admitDat) {
		this.admitDat = admitDat;
	}
	public Date getDcDate() {
		return dcDate;
	}
	public void setDcDate(Date dcDate) {
		this.dcDate = dcDate;
	}
	public String getPacType() {
		return pacType;
	}
	public void setPacType(String pacType) {
		this.pacType = pacType;
	}
	public String getPayerGroup() {
		return payerGroup;
	}
	public void setPayerGroup(String payerGroup) {
		this.payerGroup = payerGroup;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getEncountStepI() {
		return encountStepI;
	}
	public void setEncountStepI(String encountStepI) {
		this.encountStepI = encountStepI;
	}
	public String getEncountStepP() {
		return encountStepP;
	}
	public void setEncountStepP(String encountStepP) {
		this.encountStepP = encountStepP;
	}
	
	private String name;
	private BigDecimal charge;
	private int counter;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
}
