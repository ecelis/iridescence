package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TemplatesFinancialRpts {
	private static CLogger log = CLogger.getCLogger (TemplatesFinancialRpts.class);
	String financialClassName;
	String balance;
	BigDecimal totalCtas;
	String allnonSelfPay;
	String insuraceName;
	
	public String getInsuraceName() {
		return insuraceName;
	}
	public void setInsuraceName(String insuraceName) {
		this.insuraceName = insuraceName;
	}
	public String getAllnonSelfPay() {
		return allnonSelfPay;
	}
	public void setAllnonSelfPay(String allnonSelfPay) {
		this.allnonSelfPay = allnonSelfPay;
	}
	public String getFinancialClassName() {
		return financialClassName;
	}
	public void setFinancialClassName(String financialClassName) {
		this.financialClassName = financialClassName;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public BigDecimal getTotalCtas() {
		return totalCtas;
	}
	public void setTotalCtas(BigDecimal totalCtas) {
		this.totalCtas = totalCtas;
	}
	
	private TemplatesFinancialRpts(ResultSet result, int type){
		NumberFormat format = new DecimalFormat(Constantes.FORMAT_AMOUNT);
		try {
			if (type == 1) {
				financialClassName = result.getString("financialClass");
				balance = format.format(result.getBigDecimal("total"));
				totalCtas = result.getBigDecimal("encounters");
			}else if (type == 2) {
				insuraceName = result.getString("stepName");
				balance = format.format(result.getBigDecimal("total"));
				totalCtas = result.getBigDecimal("encounters");
			}else if (type == 3) {
				allnonSelfPay = format.format(result.getBigDecimal("total"));
				totalCtas = result.getBigDecimal("encounters");
			}else if (type == 4) {
				totalCtas = result.getBigDecimal("encounters");
				balance = format.format(result.getBigDecimal("total"));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		}			
	}
	
	public static List<TemplatesFinancialRpts> getList(String query, int type, List<Object> params){
		final List<TemplatesFinancialRpts> array = new ArrayList<TemplatesFinancialRpts>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(query, null);
			int i = 1;			

			DB.setParameter(pstmt, i++, Env.getAD_Client_ID(Env.getCtx()));
			DB.setParameter(pstmt, i++, Env.getAD_Org_ID(Env.getCtx()));
			
			for(Object obj : params){
				DB.setParameter(pstmt, i++, obj);
			}		

			rs = pstmt.executeQuery();
			
			while (rs.next()){				
				array.add(new TemplatesFinancialRpts(rs, type));
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}finally {
			DB.close(rs, pstmt);
		}
		return array;
	}
}
