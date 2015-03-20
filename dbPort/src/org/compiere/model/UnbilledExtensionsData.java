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
 * @author aaguilar
 * 
 */
public class UnbilledExtensionsData {

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(UnbilledExtensionsData.class);

	/**
	 * Report Unbilled Extensions
	 * 
	 * @param params
	 * @param filters
	 * @return lst of values a mostrar en el reporte Unbilled Extensions
	 */
	public static ArrayList<UnbilledExtensionsData> getUnbilledExtensions(StringBuilder filters, List<Object> params, String order, String value) {
		ArrayList<UnbilledExtensionsData> values = new ArrayList<UnbilledExtensionsData>();
		
		List<Object> params2 = new ArrayList<Object>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder reporte = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			// si selecciona todo
			if(StringUtils.isEmpty(value)) {
				reporte.append(SQLFinancialRpts.getNotCharges());
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				reporte.append("\n WHERE 1=1 \n");
				
				if (filters.length() > 0) {
					reporte.append(filters);
					params2.addAll(params);
				}
				
				reporte.append(" UNION ");
				
				reporte.append(SQLFinancialRpts.getCharges());
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				reporte.append("\n WHERE 1=1 \n");
				
				if (filters.length() > 0) {
					reporte.append(filters);
					params2.addAll(params);
				}
				
			
			} else if("NC".equals(value)){
				reporte.append(SQLFinancialRpts.getNotCharges());
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				reporte.append("\n WHERE 1=1 \n");
				if (filters.length() > 0) {
					reporte.append(filters);
					params2.addAll(params);
				}
				
			
			} else {
				reporte.append(SQLFinancialRpts.getCharges());
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				params2.add(Env.getAD_Client_ID(Env.getCtx()));
				params2.add(Env.getAD_Org_ID(Env.getCtx()));
				
				reporte.append("\n WHERE TYPERECORD =? \n");
				params2.add(value);
				
				if (filters.length() > 0) {
					reporte.append(filters);
					params2.addAll(params);
				}
			}
			
			
			if (StringUtils.isNotEmpty(order)) {
					reporte.append("\n ORDER BY ").append(order);
			}
			
			pstm = DB.prepareStatement(reporte.toString(), null);
			DB.setParameters(pstm, params2);
			rs = pstm.executeQuery();
			while (rs.next()) {
				final UnbilledExtensionsData rValues = new UnbilledExtensionsData();

				rValues.setPacName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
				rValues.setEncounter(rs.getString("ENCOUNTER"));
				rValues.setPacTipo(rs.getString("patienType"));												 
				rValues.setMrn(rs.getString("MRN"));
				rValues.setAdmit(rs.getTimestamp(MEXMECtaPac.COLUMNNAME_DateOrdered));
				rValues.setDisDate(rs.getTimestamp("DISDATE"));
				rValues.setFclass(rs.getString("FCLASS"));
				rValues.setTotal(rs.getString("linetotal"));
				rValues.setQtyDelivered(rs.getString("qtytotal"));
				rValues.setPayer(rs.getString("PAYERNAME"));
				rValues.setClaim(rs.getString("CLAIMTYPE"));
				rValues.setCoding(rs.getString("CODINGDATE"));
				rValues.setChargeType(rs.getString("TYPERECORD"));
				values.add(rValues);
			}
			if (MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order)) {
				Collections.sort(values, new Comparator<UnbilledExtensionsData>() {
					public int compare(UnbilledExtensionsData o1, UnbilledExtensionsData o2) {
						return o1.getPacName().compareTo(o2.getPacName());
					}
				});
			}
		
	} catch (Exception e) {
			s_log.log(Level.SEVERE, reporte.toString(), e);

		} finally {
			DB.close(rs, pstm);
		}
		return values;
	}
	private Date dis;
	private Date admit;
	private String encounter;
	private String mrn;
	private String pacName;
	private String pacTipo;
	private String primaryFC;
	private String qtyDelivered;
	private String total;
	private String payer;
	private String chargeType;
	
	private String claimType;
	private String coding;
	

	public String getChargeType(){
		String retValue = null;
		if("CNP".equals(chargeType)){
			retValue = "Charge with no price";
		}else if("CU".equals(chargeType)){
			retValue = "Charges unbilled";
		}else if("NC".equals(chargeType)){
			retValue = "No charges";
		}
		return retValue;
	}
	public String getCoding() {
		return coding;
	}
	
	public String getPayer() {
		return payer;
	}
	
	
	
	public String getClaim(){
		return claimType;
	} 
	public Date getAdmit() {
		return admit;
	}
	
	public Date getDis(){
		return dis;
	}

	public String getEncounter() {
		return encounter;
	}

	public String getMrn() {
		return mrn;
	}

	public String getPacName() {
		return pacName;
	}

	public String getPacTipo() {
		return pacTipo;
	}
	
	public String getFinancialClass() {

		return primaryFC;
	}
	
	public String getTotal() {
		return total;
	}
	public String getQtyDelivered() {
		return qtyDelivered;
	}
	
	public void setPayer (String payer){
		this.payer = payer;
	}
	
	
	
	public void setClaim (String claimType){
		this.claimType = claimType;
	}
	
	public void setCoding (String coding){
		this.coding = coding;
	}
	
	public void setFclass(String primaryFC){
		this.primaryFC = primaryFC;
	}
	
	public void setTotal(String total){
		this.total = total;
	}
	
	public void setQtyDelivered(String qtyDelivered){
		this.qtyDelivered = qtyDelivered;
	}
	
	public void setAdmit(Date admit) {
		this.admit = admit;
	}

	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public void setPacName(String pacName) {
		this.pacName = pacName;
	}

	public void setPacTipo(String pacTipo) {
		this.pacTipo = pacTipo;
	}
	
	public void setDisDate(Date dis){
		this.dis = dis;
	}
	
	public void setChargeType(String chargeType){
		this.chargeType = chargeType;
	}
}
