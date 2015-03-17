package org.compiere.model;

public class MBankStatementView {
	
	private String statement;
	private String cuenta;
	private String balanceInicio;
	private String balanceFin;
	private String statementDif;
	private String statementFecha;
	private Integer bankStatementId;
	
	
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getBalanceInicio() {
		return balanceInicio;
	}
	public void setBalanceInicio(String balanceInicio) {
		this.balanceInicio = balanceInicio;
	}
	public String getBalanceFin() {
		return balanceFin;
	}
	public void setBalanceFin(String balanceFin) {
		this.balanceFin = balanceFin;
	}
	public String getStatementDif() {
		return statementDif;
	}
	public void setStatementDif(String statementDif) {
		this.statementDif = statementDif;
	}
	public String getStatementFecha() {
		return statementFecha;
	}
	public void setStatementFecha(String statementFecha) {
		this.statementFecha = statementFecha;
	}
	public Integer getBankStatementId() {
		return bankStatementId;
	}
	public void setBankStatementId(Integer bankStatementId) {
		this.bankStatementId = bankStatementId;
	}
}
