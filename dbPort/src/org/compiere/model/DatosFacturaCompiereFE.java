package org.compiere.model;

import java.io.Serializable;
import java.util.Properties;

public class DatosFacturaCompiereFE implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int AD_Client_ID = 0;
	private int AD_Org_ID = 0;
	private String []infoFuncion={"",""};
	private int C_Invoice_ID =0;
	private Properties ctx=new Properties();
	private String funcionFactura="";
	private String seImprimeFactura="";
	private String copiasAdicionales="";
	private String seMandaXEmail="";
	private String email="";
	
	public int getC_Invoice_ID() {
		return C_Invoice_ID;
	}
	public void setC_Invoice_ID(int invoice_ID) {
		C_Invoice_ID = invoice_ID;
	}
	public String getCopiasAdicionales() {
		return copiasAdicionales;
	}
	public void setCopiasAdicionales(String copiasAdicionales) {
		this.copiasAdicionales = copiasAdicionales;
	}
	public Properties getCtx() {
		return ctx;
	}
	public void setCtx(Properties ctx) {
		this.ctx = ctx;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFuncionFactura() {
		return funcionFactura;
	}
	public void setFuncionFactura(String funcionFactura) {
		this.funcionFactura = funcionFactura;
	}
	public String[] getInfoFuncion() {
		return infoFuncion;
	}
	public void setInfoFuncion(String[] infoFuncion) {
		this.infoFuncion = infoFuncion;
	}
	public String getSeImprimeFactura() {
		return seImprimeFactura;
	}
	public void setSeImprimeFactura(String seImprimeFactura) {
		this.seImprimeFactura = seImprimeFactura;
	}
	public String getSeMandaXEmail() {
		return seMandaXEmail;
	}
	public void setSeMandaXEmail(String seMandaXEmail) {
		this.seMandaXEmail = seMandaXEmail;
	}
	public int getAD_Client_ID() {
		return AD_Client_ID;
	}
	public void setAD_Client_ID(int client_ID) {
		AD_Client_ID = client_ID;
	}
	public int getAD_Org_ID() {
		return AD_Org_ID;
	}
	public void setAD_Org_ID(int org_ID) {
		AD_Org_ID = org_ID;
	}
	
	
}
