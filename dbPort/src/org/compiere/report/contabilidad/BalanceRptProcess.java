package org.compiere.report.contabilidad;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MElementValue;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class BalanceRptProcess {
	
	/**	Logger							*/
	private static CLogger		s_log = CLogger.getCLogger (MElementValue.class);

	
	/**
	 * 
	 * @param ctx
	 * @param data
	 * @param periodNo
	 * @param year
	 * @return
	 */
	public static BalanceRptView getAmts(Properties ctx, BalanceRptView data, int periodNo, String year){
		
		
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT saldo_inicial(?,?,?,'A',?), sum(V.amtacctdr) AS Debe, ")
        .append(" sum(V.amtacctcr) AS Haber, ")
        .append(" sum(V.amtacct) AS SaldoPeriodo, ")
        .append(" (saldo_inicial(?,?, ?,'A', ?) + coalesce(sum(V.amtacct),0)) AS SaldoFinal ")
        .append(" FROM exme_rv_fact_acct_period V ")
        .append(" INNER JOIN c_elementvalue CE ON (CE.c_elementvalue_id = V.account_id)")
        .append(" WHERE v.account_id=? and periodno = ? ")
        .append(" AND year = ? ")
        .append(" AND V.ad_client_id = ? ")
        .append(" AND V.postingtype = 'A' ");
        
   
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, data.getElementValueID());
			pstmt.setString(3, year);
			pstmt.setInt(4, periodNo);
			
			pstmt.setInt(5, Env.getAD_Client_ID(ctx));
			pstmt.setInt(6, data.getElementValueID());
			pstmt.setString(7, year);
			pstmt.setInt(8, periodNo);
			pstmt.setInt(9, data.getElementValueID());
			pstmt.setInt(10, periodNo);
			pstmt.setString(11, year);
			pstmt.setInt(12, Env.getAD_Client_ID(ctx));
			
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				data.setInitAmt(rs.getBigDecimal(1));
				data.setDebitAmt((rs.getBigDecimal(2) == null) ? BigDecimal.ZERO:rs.getBigDecimal(2));
				data.setCreditAmt((rs.getBigDecimal(3) == null) ? BigDecimal.ZERO:rs.getBigDecimal(3));
				data.setPeriodAmt((rs.getBigDecimal(4) == null) ? BigDecimal.ZERO:rs.getBigDecimal(4));
				data.setFinalAmt((rs.getBigDecimal(5) == null) ? BigDecimal.ZERO:rs.getBigDecimal(5));
			}
			
		}catch(Exception e){
			s_log.log(Level.SEVERE, sql.toString(), e);
		}finally{
			DB.close(rs,pstmt);
		}
		return data;
	}

	
}

