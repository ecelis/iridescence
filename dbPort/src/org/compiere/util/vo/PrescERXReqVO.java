package org.compiere.util.vo;

import java.sql.Timestamp;

import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEGenProduct;
import org.compiere.model.MEXMEPrescRXDet;
import org.compiere.model.MEXMEProduct;
import org.compiere.util.ValueName;

public class PrescERXReqVO {
	 
    int EXME_PrescRXDet_ID;
    int EXME_GenProduct_ID;
    int M_Product_ID;
    String DocumentNo;
    String FechaHrIni;
    String FechaHrFin;
    String Value;
    String Generic_Product_Name;
    String Description;
    String Help;
    
    public PrescERXReqVO(){
    	
    }

	public int getEXME_PrescRXDet_ID() {
		return EXME_PrescRXDet_ID;
	}

	public void setEXME_PrescRXDet_ID(int eXME_PrescRXDet_ID) {
		EXME_PrescRXDet_ID = eXME_PrescRXDet_ID;
	}

	public int getEXME_GenProduct_ID() {
		return EXME_GenProduct_ID;
	}

	public void setEXME_GenProduct_ID(int eXME_GenProduct_ID) {
		EXME_GenProduct_ID = eXME_GenProduct_ID;
	}

	public int getM_Product_ID() {
		return M_Product_ID;
	}

	public void setM_Product_ID(int m_Product_ID) {
		M_Product_ID = m_Product_ID;
	}

	public String getDocumentNo() {
		return DocumentNo;
	}

	public void setDocumentNo(String documentNo) {
		DocumentNo = documentNo;
	}

	public String getFechaHrIni() {
		return FechaHrIni;
	}

	public void setFechaHrIni(String fechaHrIni) {
		FechaHrIni = fechaHrIni;
	}

	public String getFechaHrFin() {
		return FechaHrFin;
	}

	public void setFechaHrFin(String fechaHrFin) {
		FechaHrFin = fechaHrFin;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getGeneric_Product_Name() {
		return Generic_Product_Name;
	}

	public void setGeneric_Product_Name(String generic_Product_Name) {
		Generic_Product_Name = generic_Product_Name;
	}

	public String getDescription() {
		if(getHelp().isEmpty())
			return Description;
		else
			return getHelp();
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getHelp() {
		return Help;
	}

	public void setHelp(String help) {
		Help = help;
	}
    
	
}
