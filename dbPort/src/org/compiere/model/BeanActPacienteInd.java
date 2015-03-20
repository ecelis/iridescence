/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Extends MEXMEActPacienteInd
 * @author twry
 *
 */
public class BeanActPacienteInd extends MEXMEActPacienteInd {

	/** serialVersionUID */
	private static final long serialVersionUID = 7038517511958535280L;

	/**
	 * @param ctx
	 * @param EXME_ActPacienteInd_ID
	 * @param trxName
	 */
	public BeanActPacienteInd(Properties ctx, int EXME_ActPacienteInd_ID,
			String trxName) {
		super(ctx, EXME_ActPacienteInd_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public BeanActPacienteInd(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/** Cargo de la cuenta paciente */
	private int EXME_CtaPacDet_ID = 0;
	/** Plan medico */
	private int EXME_PlanMedLine_ID = 0;
	/** Fecha de aplicacion de la planeacion  */
	private Timestamp aplicadaExp = null;
	/** Fecha de aplicacion del cargo */
	private Timestamp aplicadaChar = null;
	/** Proceso que origiba el expdiente */
	private String process = null;
	/** Motivo del cargo o asusencial del mismo */
	private String razon = null;
	/** codigo del producto */
	private String prodValue = null;
	/** nombre/descripcion del producto */
	private String prodDescript = null;
	/** codigo serv */
	private String prodCPTHCPCS = null;
	
	public String getProdValue() {
		return prodValue;
	}

	public void setProdValue(String prodValue) {
		this.prodValue = prodValue;
	}

	public String getProdDescript() {
		return prodDescript;
	}

	public void setProdDescript(String prodDescript) {
		this.prodDescript = prodDescript;
	}

	public String getProdCPTHCPCS() {
		return prodCPTHCPCS;
	}

	public void setProdCPTHCPCS(String prodCPTHCPCS) {
		this.prodCPTHCPCS = prodCPTHCPCS;
	}

	public int getEXME_CtaPacDet_ID() {
		return EXME_CtaPacDet_ID;
	}

	public void setEXME_CtaPacDet_ID(int eXME_CtaPacDet_ID) {
		EXME_CtaPacDet_ID = eXME_CtaPacDet_ID;
	}

	public int getEXME_PlanMedLine_ID() {
		return EXME_PlanMedLine_ID;
	}

	public void setEXME_PlanMedLine_ID(int eXME_PlanMedLine_ID) {
		EXME_PlanMedLine_ID = eXME_PlanMedLine_ID;
	}

	public Timestamp getAplicadaExp() {
		return aplicadaExp;
	}

	public void setAplicadaExp(Timestamp aplicadaExp) {
		this.aplicadaExp = aplicadaExp;
	}

	public Timestamp getAplicadaChar() {
		return aplicadaChar;
	}

	public void setAplicadaChar(Timestamp aplicadaChar) {
		this.aplicadaChar = aplicadaChar;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}
	
	/**
	 * Planeacion de medicamentos
	 */
	private MPlanMed planMed = null; 
	private MPlanMedLine pmline = null;
	
	
	public MPlanMed getPlanMed() {
		return planMed;
	}

	public void setPlanMed(MPlanMed planMed) {
		this.planMed = planMed;
	}

	public MPlanMedLine getPMLine() {
		return pmline;
	}

	public void setPMLine(MPlanMedLine line) {
		this.pmline = line;
	}
}
