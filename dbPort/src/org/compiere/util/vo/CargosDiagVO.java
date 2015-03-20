package org.compiere.util.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MActPacienteDiag;
import org.compiere.model.MDiagnostico;

public class CargosDiagVO {
	
	private int exme_actpacienteindh_id = 0; 
	private int exme_actpacienteind_id  = 0;
	private int m_product_id = 0;
	private String value = null;
	private String name  = null;
	private String description  = null;
	private String revenueCode = null;
	private Timestamp dateDelivered = null ;
	private int qtyDelivered = 0;
	private String producttype = null;
	private int exme_diagnostico_id = 0;
	private int exme_actpacientediag_id = 0;
	private String diagnosticoName = null;
	private String diagnosticoCode  = null;
	private int indiceDiag = 0;
	private boolean CambioIndice = false;
	private String DiagRelated = null;
	List<MActPacienteDiag> lstDiagCombo = new ArrayList<MActPacienteDiag>();
	

	public CargosDiagVO(){}
	
	public int getExme_actpacienteindh_id() {
		return exme_actpacienteindh_id;
	}
	public void setExme_actpacienteindh_id(int exme_actpacienteindh_id) {
		this.exme_actpacienteindh_id = exme_actpacienteindh_id;
	}
	public int getExme_actpacienteind_id() {
		return exme_actpacienteind_id;
	}
	public void setExme_actpacienteind_id(int exme_actpacienteind_id) {
		this.exme_actpacienteind_id = exme_actpacienteind_id;
	}
	public int getM_product_id() {
		return m_product_id;
	}
	public void setM_product_id(int m_product_id) {
		this.m_product_id = m_product_id;
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
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	public int getExme_diagnostico_id() {
		return exme_diagnostico_id;
	}
	public void setExme_diagnostico_id(int exme_diagnostico_id) {
		this.exme_diagnostico_id = exme_diagnostico_id;
	}
	public String getDiagnosticoName() {
		return diagnosticoName;
	}
	public void setDiagnosticoName(String diagnosticoName) {
		this.diagnosticoName = diagnosticoName;
	}
	public String getDiagnosticoCode() {
		return diagnosticoCode;
	}
	public void setDiagnosticoCode(String diagnosticoCode) {
		this.diagnosticoCode = diagnosticoCode;
	}

	public int getIndiceDiag() {
		return indiceDiag;
	}

	public void setIndiceDiag(int indiceDiag) {
		this.indiceDiag = indiceDiag;
	}

	public boolean isCambioIndice() {
		return CambioIndice;
	}

	public void setCambioIndice(boolean cambioIndice) {
		CambioIndice = cambioIndice;
	}

	public int getExme_actpacientediag_id() {
		return exme_actpacientediag_id;
	}

	public void setExme_actpacientediag_id(int exme_actpacientediag_id) {
		this.exme_actpacientediag_id = exme_actpacientediag_id;
	}

	public List<MActPacienteDiag> getLstDiagCombo() {
		return lstDiagCombo;
	}

	public void setLstDiagCombo(List<MActPacienteDiag> lstDiagCombo) {
		this.lstDiagCombo = lstDiagCombo;
	}
	
	public String getLstDiagComboString() {
		StringBuilder ret = new StringBuilder();
		for(MActPacienteDiag obj : this.lstDiagCombo){
//			String aux =(((MDiagnostico)Real()) != null ? ((MDiagnostico)obj.getDiagnosticoReal()).getValue() : "");
			if(obj.getDiagnostico() != null){
				ret.append(obj.getDiagnostico().getValue());
			}
			ret.append("  ");
		}
		return ret.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRevenueCode() {
		return revenueCode;
	}

	public void setRevenueCode(String revenueCode) {
		this.revenueCode = revenueCode;
	}

	public Timestamp getDateDelivered() {
		return dateDelivered;
	}

	public void setDateDelivered(Timestamp dateDelivered) {
		this.dateDelivered = dateDelivered;
	}

	public Integer getQtyDelivered() {
		return qtyDelivered;
	}

	public void setQtyDelivered(Integer qtyDelivered) {
		this.qtyDelivered = qtyDelivered;
	}

	public String getDiagRelated() {
		return DiagRelated;
	}

	public void setDiagRelated(String diagRelated) {
		DiagRelated = diagRelated;
	}


}
