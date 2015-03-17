package org.compiere.util.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MEXMEActPacICD9V3;
import org.compiere.model.MEXMEClaimCodes;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacMed;
import org.compiere.model.X_EXME_ICD9V3;
import org.compiere.util.Env;


public class ActPacICD9V3VO {
	
	private Timestamp dateOrdered = null;
	private String value = null;
	private String name = null;
	private String description = null;
	private String hcpcs = null;
	private int actPac_ICD9V3_ID = 0;
	private int line = 10;
	private int icd9V3_ID = 0;
	private int sequence = 0;
	private int ctaPacMed_ID = 0;
	private int claimCodesID = 0;
	private boolean isActive = Boolean.TRUE;
	private MEXMECtaPacMed ctaPacMed = null;
	
	
	public int getActPac_ICD9V3_ID() {
		return actPac_ICD9V3_ID;
	}
	public void setActPac_ICD9V3_ID(int actPacICD9V3ID) {
		actPac_ICD9V3_ID = actPacICD9V3ID;
	}
	public int getIcd9V3_ID() {
		return icd9V3_ID;
	}
	public void setIcd9V3_ID(int icd9v3ID) {
		icd9V3_ID = icd9v3ID;
	}
	public int getClaimCodesID() {
		return claimCodesID;
	}
	public void setClaimCodesID(int claimCodesID) {
		this.claimCodesID = claimCodesID;
	}
	public int getCtaPacMed_ID() {
		return ctaPacMed_ID;
	}
	public void setCtaPacMed_ID(int ctaPacMedID) {
		ctaPacMed_ID = ctaPacMedID;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	
	public Timestamp getDateOrdered() {
		return dateOrdered;
	}
	public void setDateOrdered(Timestamp dateOrdered) {
		this.dateOrdered = dateOrdered;
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
	
	public boolean saveSeq() {
		if (getActPac_ICD9V3_ID()>0){
			MEXMEActPacICD9V3 icd9 = new MEXMEActPacICD9V3(Env.getCtx(), getActPac_ICD9V3_ID(), null);
			icd9.setSequence(getSequence());
			if (icd9.save()){
				return Boolean.TRUE;
			}else{
				return Boolean.FALSE;
			}
		}
		return false;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public static ActPacICD9V3VO create (int procID, Timestamp date){
		X_EXME_ICD9V3 icd = new X_EXME_ICD9V3(Env.getCtx(), procID, null);
		ActPacICD9V3VO obj = new ActPacICD9V3VO();
		obj.setActPac_ICD9V3_ID(0);
		obj.setIcd9V3_ID(procID);
		obj.setDateOrdered(date);
		obj.setName(icd.getName());
		obj.setValue(icd.getValue());
		obj.setDescription(icd.getDescription());
		obj.setSequence(0);
		obj.setClaimCodesID(0);
		obj.setCtaPacMed_ID(0);
		icd = null;
		return obj;
		
	}
	
	public MEXMECtaPacMed getCtaPacMed() {
		return ctaPacMed;
	}
	public void setCtaPacMed(MEXMECtaPacMed ctaPacMed) {
		this.ctaPacMed = ctaPacMed;
	}
	
	
	public boolean save(MEXMECtaPac ctaPac, String trxName){
		
		//Si el registro ya existe 
		//Y cambio Performing actualizamos
		if (actPac_ICD9V3_ID>0 ){
			if (ctaPacMed_ID !=  ctaPacMed.getEXME_CtaPacMed_ID()){
				//Desactivamos el claimcode anterior si existe
				if (claimCodesID>0){
					MEXMEClaimCodes regOld = new MEXMEClaimCodes(Env.getCtx(), claimCodesID, null);
					regOld.setIsActive(Boolean.FALSE);
					if (!regOld.save(trxName)){
						return false;
					}
				}
				return MEXMEClaimCodes.createClaimCode(ctaPac, MEXMECtaPacMed.Table_ID,ctaPacMed.getEXME_CtaPacMed_ID(),
						MEXMEActPacICD9V3.Table_ID, actPac_ICD9V3_ID, trxName);
			}
		}else{
			//Creamos registro nuevo
			MEXMEActPacICD9V3 actPacICD9 = new MEXMEActPacICD9V3(Env.getCtx(), 0, null);
			actPacICD9.setDateOrdered(dateOrdered);
			actPacICD9.setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
			actPacICD9.setEXME_ICD9V3_ID(icd9V3_ID);
			actPacICD9.setLine(10);
			actPacICD9.setQtyOrdered(BigDecimal.ONE);
			if (actPacICD9.save(trxName)){
				actPac_ICD9V3_ID = actPacICD9.getEXME_ActPac_ICD9V3_ID();
				if (ctaPacMed != null){
					return MEXMEClaimCodes.createClaimCode(ctaPac, MEXMECtaPacMed.Table_ID,ctaPacMed.getEXME_CtaPacMed_ID(),
							MEXMEActPacICD9V3.Table_ID, actPac_ICD9V3_ID, trxName);	
				}else{
					return true;
				}
				
			}else{
				return false;
			}
		}
		return true;
	}
	

}
