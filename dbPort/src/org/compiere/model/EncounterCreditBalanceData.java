package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
public class EncounterCreditBalanceData {
	
	private static CLogger		slog= CLogger.getCLogger(EncounterCreditBalanceData.class);
	
	/**
	 * Encounter Credit Balance
	 * @param params 
	 * @param filters
	 * @return lst of values a mostrar en Encounter Credit Balance
	 */
	public static ArrayList<EncounterCreditBalanceData> getEncounterCreditBalance(StringBuilder filters, List<Object> params, Object order){
		ArrayList<EncounterCreditBalanceData> values = new ArrayList<EncounterCreditBalanceData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int j = 1;
		try {
			StringBuilder reporte = new StringBuilder(SQLFinancialRpts.encounterCreditBalance());
			if(StringUtils.isNotEmpty(filters.toString()))
				reporte.append(filters);

			if(order != null && StringUtils.isNotEmpty(order.toString())){
				reporte.append("ORDER BY ").append(order);
			}
			pstm = DB.prepareStatement(reporte.toString(), null);
			
			pstm.setInt(j++, MEXMECtaPac.ENCOUNTERSTATUS_AD_Reference_ID);	
			pstm.setString(j++, MCharge.TYPE_CoPay);
			pstm.setString(j++, MCharge.TYPE_Coinsurance);
			pstm.setString(j++, MCharge.TYPE_Deductible);
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
			
			values.addAll(createListEncounterCreditBalance(rs));
			
			if(MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order)){
				 Collections.sort(values, new Comparator<EncounterCreditBalanceData>() {

                     public int compare(EncounterCreditBalanceData o1, EncounterCreditBalanceData o2) {
                             
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
	 * Encounter Credit Balance
	 * @return lst of values a mostrar en el reporte Encounter Credit Balance
	 */
	public static ArrayList<EncounterCreditBalanceData> createListEncounterCreditBalance(ResultSet rs){
		ArrayList<EncounterCreditBalanceData> lst = new ArrayList<EncounterCreditBalanceData>();
		try{
			while(rs.next()){
				EncounterCreditBalanceData rValues = new EncounterCreditBalanceData();
				rValues.setEncounter(rs.getString("ENCOUNTER"));
				rValues.setPacName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setAdmitDate(rs.getDate(MEXMECtaPac.COLUMNNAME_DateOrdered));
				rValues.setDcDate(rs.getDate(MEXMECtaPac.COLUMNNAME_FechaAlta));
				rValues.setPacType(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));
				rValues.setEncountStatus(rs.getString("STATUS"));
				rValues.setEncountBalance(rs.getBigDecimal("BALANCE"));
				rValues.setPrimaryFC(rs.getString("FCNAME"));
				rValues.setPrimaryPC(rs.getString("PCNAME"));
				rValues.setCtaPacID(rs.getInt(MEXMECtaPac.COLUMNNAME_EXME_CtaPac_ID));
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
	private Date dcDate;
	private String pacType;
	private String primaryFC;
	private String primaryPC;
	private String financialClass;
	private String encountStatus;
	private BigDecimal encountBalance;
	private int ctaPacID;
	
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
	public String getPrimaryFC() {
		return primaryFC;
	}
	public String getPrimaryPC() {
		return primaryPC;
	}
	public void setPrimaryPC(String primaryPC){
		this.primaryPC = primaryPC;
	}
	public void setPrimaryFC(String primaryFC) {
		this.primaryFC = primaryFC;
	}
	public String getFinancialClass() {
		return financialClass;
	}
	public void setFinancialClass(String financialClass) {
		this.financialClass = financialClass;
	}
	public String getEncountStatus() {
		return encountStatus;
	}
	public void setEncountStatus(String encountStatus) {
		this.encountStatus = encountStatus;
	}
	public String getEncountBalance() {
		return NumberFormat.getCurrencyInstance(Locale.CANADA).format(encountBalance);
	}
	public void setEncountBalance(BigDecimal encountBalance) {
		this.encountBalance = encountBalance;
	}
	public int getCtaPacID() {
		return ctaPacID;
	}
	public void setCtaPacID(int ctaPacID) {
		this.ctaPacID = ctaPacID;
	}
}
