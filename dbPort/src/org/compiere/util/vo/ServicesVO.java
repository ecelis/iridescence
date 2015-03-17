package org.compiere.util.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MDiagnostico;
import org.compiere.model.MEXMEClaimCodes;
import org.compiere.model.MEXMECtaPacMed;
import org.compiere.model.MEXMEModifiers;
import org.compiere.model.MEXMEProductoOrg;
import org.compiere.model.MProduct;
import org.compiere.util.Env;


public class ServicesVO {

	private int sequence = 0;
	private int ctaPacDetID  = 0;
	private int actPacienteIndID = 0;
	private int productID = 0;
	private String value = null;
	private String name = null;
	private String description  = null;
	private String hcpcs = null;
	private String revCode = null;
	private int qtyDelivered = 0;
	private Timestamp dateDelivered = null ;
	private Double charges = new Double(0);	
	private BigDecimal price=null;	
	private int ctaPacMed_ID = 0;
	private int claimCodesID = 0;
	private int posID = 0;
	private String hcpcsLevel;
	private String condition;
	private MEXMECtaPacMed ctaPacMed;
	private MCtaPacDet ctaPacDet;
	private String billingType;
	private List<MEXMEClaimCodes> modifiers = new ArrayList<MEXMEClaimCodes>();
	private List<MEXMEClaimCodes> procdiag = new ArrayList<MEXMEClaimCodes>();
	
	
	public ServicesVO(){}
	
	public static ServicesVO create(MProduct prod, MEXMEProductoOrg prodOrg, Timestamp dateDelivered){
		
		
		ServicesVO obj = new ServicesVO();
		obj.setProductID(prod.getM_Product_ID());
		obj.setValue(prod.getValue());
		obj.setName(prod.getName());
		obj.setDescription(prod.getDescription());
		obj.setHcpcs(prod.getIntevencion().getValue());
		obj.setRevCode(prodOrg.getEXME_RevenueCode() != null 
				? prodOrg.getEXME_RevenueCode().getValue() : "" );
		obj.setQtyDelivered(1);
		obj.setDateDelivered(dateDelivered);
		obj.setCharges(prodOrg.getPrecioStd(dateDelivered).doubleValue());
		return obj;
		
	}
	
	public static ServicesVO create(MProduct prod, MEXMEProductoOrg prodOrg, 
			Timestamp dateDelivered, BigDecimal price, int qty , String billingType ){
		
		
		ServicesVO obj = new ServicesVO();
		obj.setProductID(prod.getM_Product_ID());
		obj.setValue(prod.getValue());
		obj.setName(prod.getName());
		obj.setDescription(prod.getDescription());
		obj.setHcpcs(prod.getIntevencion().getValue());
		obj.setRevCode(prodOrg.getEXME_RevenueCode() != null 
				? prodOrg.getEXME_RevenueCode().getValue() : "" );
		obj.setQtyDelivered(qty);
		obj.setDateDelivered(dateDelivered);
		obj.setPrice(price);
		obj.setCharges(price.multiply(new BigDecimal(qty)).doubleValue());
		obj.setBillingType(billingType);
		return obj;
		
	}
		
	public int getSequence() {
		return sequence;
	}



	public void setSequence(int sequence) {
		this.sequence = sequence;
	}



	public int getCtaPacDetID() {
		return ctaPacDetID;
	}



	public void setCtaPacDetID(int ctaPacDetID) {
		this.ctaPacDetID = ctaPacDetID;
	}



	public int getActPacienteIndID() {
		return actPacienteIndID;
	}



	public void setActPacienteIndID(int actPacienteIndID) {
		this.actPacienteIndID = actPacienteIndID;
	}



	public int getProductID() {
		return productID;
	}



	public void setProductID(int productID) {
		this.productID = productID;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getHcpcs() {
		return hcpcs;
	}



	public void setHcpcs(String hcpcs) {
		this.hcpcs = hcpcs;
	}



	public String getRevCode() {
		return revCode;
	}



	public void setRevCode(String revCode) {
		this.revCode = revCode;
	}



	public int getQtyDelivered() {
		return qtyDelivered;
	}



	public void setQtyDelivered(int qtyDelivered) {
		this.qtyDelivered = qtyDelivered;
	}



	public Timestamp getDateDelivered() {
		return dateDelivered;
	}



	public void setDateDelivered(Timestamp dateDelivered) {
		this.dateDelivered = dateDelivered;
	}



	public Double getCharges() {
		return charges;
	}



	public void setCharges(Double charges) {
		this.charges = charges;
	}



	public int getCtaPacMed_ID() {
		return ctaPacMed_ID;
	}



	public void setCtaPacMed_ID(int ctaPacMedID) {
		ctaPacMed_ID = ctaPacMedID;
	}


	public int getClaimCodesID() {
		return claimCodesID;
	}



	public void setClaimCodesID(int claimCodesID) {
		this.claimCodesID = claimCodesID;
	}



	public String getModifiers() {
		String retVal = "";
		for (MEXMEClaimCodes mod : modifiers){
			if (StringUtils.isNotEmpty(retVal)){
				retVal = retVal + ", ";
			}
			if (mod.getEXME_ClaimCodes_ID()>0){
				retVal = retVal + mod.getValue(MEXMEModifiers.Table_Name, 
						MEXMEModifiers.COLUMNNAME_Value, Env.getCtx(), null).toString();
			}else{
				retVal = retVal + new MEXMEModifiers(Env.getCtx(), mod.getRecord_ID(), null).getValue();
			}
			
			
		}
		return retVal;
	}

	public void addAllMod(List<MEXMEClaimCodes> lista){
		if (lista != null){
			modifiers.clear();
			modifiers.addAll(lista);
		}
	}
	
	public List<MEXMEClaimCodes> getModifiersLst (){
		return modifiers;
	}
	
	public String getProcdiag() {
		String retVal = "";
		for (MEXMEClaimCodes claimCode : procdiag){
			if (StringUtils.isNotEmpty(retVal)){
				retVal = retVal + ", ";
			}
			int id = 0;
			
			if (claimCode.getEXME_ClaimCodes_ID()>0){
				try{
					id = Integer.valueOf(claimCode.getValue(MActPacienteDiag.Table_Name, 
							MActPacienteDiag.COLUMNNAME_EXME_Diagnostico_ID, Env.getCtx(), null).toString());
					if (id>0){
						retVal = retVal + MDiagnostico.getValue(Env.getCtx(), id, null);
					}
				}catch(Exception e){
					id = -1;
				}
			}else{
				retVal = retVal + claimCode.getDiag().getDiagnosticValue();
			}
			
			
		}
		return retVal;
	}
	
	public void addAllProcDiag(List<MEXMEClaimCodes> lista){
		if (lista != null){
			procdiag.clear();
			procdiag.addAll(lista);
		}
	}
	
	public List<MEXMEClaimCodes> getProcDiagLst (){
		return procdiag;
	}

	public int getPosID() {
		return posID;
	}



	public void setPosID(int posID) {
		this.posID = posID;
	}



	public String getHcpcsLevel() {
		return hcpcsLevel;
	}



	public void setHcpcsLevel(String hcpcsLevel) {
		this.hcpcsLevel = hcpcsLevel;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public MEXMECtaPacMed getCtaPacMed() {
		return ctaPacMed;
	}

	public void setCtaPacMed(MEXMECtaPacMed ctaPacMed) {
		this.ctaPacMed = ctaPacMed;
	}

	public MCtaPacDet getCtaPacDet() {
		return ctaPacDet;
	}

	public void setCtaPacDet(MCtaPacDet ctaPacDet) {
		this.ctaPacDet = ctaPacDet;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
