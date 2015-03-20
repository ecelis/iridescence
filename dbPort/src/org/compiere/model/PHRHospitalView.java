package org.compiere.model;

public class PHRHospitalView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int PHR_Hospital_ID = 0;
	private int C_Location_ID = 0;
	private String nombre = null;
	private String telefono = null;
	private String fax = null;
	private boolean principal = false;
	private String address1 = null;
	private String address2 = null;
	private String postal = null;
	private int C_Country_ID = 0;
	private int C_Region_ID = 0;
	private int EXME_TownCouncil_ID = 0;
	public int getPHR_Hospital_ID() {
		return PHR_Hospital_ID;
	}
	public void setPHR_Hospital_ID(int pHRHospitalID) {
		PHR_Hospital_ID = pHRHospitalID;
	}
	public int getC_Location_ID() {
		return C_Location_ID;
	}
	public void setC_Location_ID(int cLocationID) {
		C_Location_ID = cLocationID;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public boolean isPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public int getC_Country_ID() {
		return C_Country_ID;
	}
	public void setC_Country_ID(int cCountryID) {
		C_Country_ID = cCountryID;
	}
	public int getC_Region_ID() {
		return C_Region_ID;
	}
	public void setC_Region_ID(int cRegionID) {
		C_Region_ID = cRegionID;
	}
	public int getEXME_TownCouncil_ID() {
		return EXME_TownCouncil_ID;
	}
	public void setEXME_TownCouncil_ID(int eXMETownCouncilID) {
		EXME_TownCouncil_ID = eXMETownCouncilID;
	}
	

}
