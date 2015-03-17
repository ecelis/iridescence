/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class ChargeRecapData {
	
	private static CLogger		slog= CLogger.getCLogger(ChargeRecapData.class);
	
	/**
	 * Charge Recap
	 * @param params 
	 * @param filters
	 * @return lst of values a mostrar en Charge Recap
	 */
	public static ArrayList<ChargeRecapData> getChargeRecap(StringBuilder filters, List<Object> params, Object order){
		ArrayList<ChargeRecapData> values = new ArrayList<ChargeRecapData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
	
		try {
			StringBuilder reporte = new StringBuilder(SQLFinancialRpts.chargeRecap());
			if(StringUtils.isNotEmpty(filters.toString()) && !params.isEmpty()){
				reporte.append("WHERE ").append(filters);
			}else if(filters.toString().contains("'"))
				reporte.append("WHERE ").append(filters);
			
			if(order != null && StringUtils.isNotEmpty(order.toString())){
				reporte.append("ORDER BY ").append(order);
			}
			pstm = DB.prepareStatement(reporte.toString(), null);
			
			pstm.setInt(1, Env.getAD_Org_ID(Env.getCtx()));
			if (StringUtils.isNotEmpty(filters.toString())
					&& !params.isEmpty()) {
				for (int i = 2; i <= params.size()+1; i++) {
					//Solo filtros de fechas
					pstm.setTimestamp(i, (java.sql.Timestamp) params.get(i-2));
				}
			}
			rs = pstm.executeQuery();
			
			values.addAll(ChargeRecapData.createListgetChargeRecap(rs));
			
			if(MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order)){
				 Collections.sort(values, new Comparator<ChargeRecapData>() {

                     public int compare(ChargeRecapData o1, ChargeRecapData o2) {
                             
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
	 * Report Charge Recap
	 * @return lst of values a mostrar en el reporte Charge Recap
	 */
	public static ArrayList<ChargeRecapData> createListgetChargeRecap(ResultSet rs){
		ArrayList<ChargeRecapData> lst = new ArrayList<ChargeRecapData>();
		try{
			while(rs.next()){
				ChargeRecapData rValues = new ChargeRecapData();
				rValues.setEncounter(rs.getString("ENCOUNTER"));
				rValues.setPacName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setDcDate(rs.getDate(MEXMECtaPac.COLUMNNAME_FechaAlta));
//				rValues.setPosDate(rs.getDate(""));
				rValues.setPacType(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));
				rValues.setFinancialClass(rs.getString("FINANCIALCLASS"));
				rValues.setAttending(rs.getString("PHYSICIANNAME"));	
				rValues.setItemNum(rs.getString("ITEMNUM"));
				rValues.setItemName(rs.getString("ITEMNAME"));
				rValues.setCode(rs.getString("REVENUECODE"));
//				rValues.setQuantity(rs.getString("")); 
//				rValues.setCharge(rs.getBigDecimal(""));
				lst.add(rValues);
			}
		}catch(Exception e){
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
	
	private String encounter;
	private String pacName;
	private Date dcDate;
	private Date posDate;
	private String pacType;
	private String financialClass;
	private String attending;
	private String itemNum;
	private String itemName;
	private String code;
	private String quantity;
	private BigDecimal charge;
	
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
	public Date getDcDate() {
		return dcDate;
	}
	public void setDcDate(Date dcDate) {
		this.dcDate = dcDate;
	}
	public Date getPosDate() {
		return posDate;
	}
	public void setPosDate(Date posDate) {
		this.posDate = posDate;
	}
	public String getPacType() {
		return pacType;
	}
	public void setPacType(String pacType) {
		this.pacType = pacType;
	}
	public String getFinancialClass() {
		return financialClass;
	}
	public void setFinancialClass(String financialClass) {
		this.financialClass = financialClass;
	}
	public String getAttending() {
		return attending;
	}
	public void setAttending(String attending) {
		this.attending = attending;
	}
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
}
