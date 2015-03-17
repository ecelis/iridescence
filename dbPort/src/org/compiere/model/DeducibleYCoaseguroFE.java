package org.compiere.model;

public class DeducibleYCoaseguroFE
{
	private String coaseguro="00.00";
	private String deducible="00.00";
	
	private boolean existeInfo=false;
	
	public boolean isExisteInfo() {
		return existeInfo;
	}
	public void setExisteInfo(boolean existeInfo) {
		this.existeInfo = existeInfo;
	}
	public String getCoaseguro() {
		return coaseguro;
	}
	public void setCoaseguro(String coaseguro) {
		this.coaseguro = coaseguro;
	}
	public String getDeducible() {
		return deducible;
	}
	public void setDeducible(String deducible) {
		this.deducible = deducible;
	}
	
}