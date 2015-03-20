package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Obtener acumulados de los asientos contables por empresa, organizacion,
 *  esquema, cuenta, tipo de posteo, periodo y opcionalmente, org trx
 *
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2006/01/19 20:04:30 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.1 $
 */
public class FactAcctAcum
{
    /** Logger                  */
    private static CLogger  log = CLogger.getCLogger (FactAcctAcum.class);
    
    /**
     * Obtenemos el acumulado por periodo de la tabla Acct_Fact por los siguientes parametros
     * 
     *  @param  AD_Client_ID empresa
     *  @param AD_Org_ID organizacion
     *  @param C_AcctSchema_ID esquema contable
     *  @param Account_ID cuenta contable
     *  @param postingType tipo de posteo
     *  @param C_Period_ID, periodo
     *  @param AD_OrgTrx_ID organizacion transaccional
     *  @param trxName transcation
     * @return El valor acumulado hasta el periodo
     */
    public static BigDecimal getFactAcctPer(int AD_Client_ID, int AD_Org_ID, int C_AcctSchema_ID,
            int Account_ID, String postingType, MPeriod period, int AD_OrgTrx_ID, 
            String trxName) {
        BigDecimal acum = Env.ZERO;
        
        //obtenemos el a&ntilde;p del periodo
        MYear year = new MYear(period.getCtx(), period.getC_Year_ID(), trxName);
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        sql.append(" SELECT COALESCE(SUM(AMTACCT),0) FROM EXME_RV_FACT_ACCT_PERIOD ")
        .append("WHERE AD_Client_ID = ? AND C_AcctSchema_ID = ? ")
        .append(" AND Account_ID=? AND PostingType=? AND ")
        .append(" Year = ? AND PeriodNo = ? ");
        if(AD_Org_ID!=0)
        	sql.append(" AND AD_Org_ID = ?");
        if(AD_OrgTrx_ID!=0)
            sql.append(" AND AD_OrgTrx_ID = ?");
        try
        {
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, AD_Client_ID);
            pstmt.setInt(2, C_AcctSchema_ID);
            pstmt.setInt(3, Account_ID);
            pstmt.setString(4, postingType);
            pstmt.setInt(5, year.getYearAsInt());
            pstmt.setInt(6, period.getPeriodNo());
            if(AD_Org_ID!=0)
                pstmt.setInt(7, AD_Org_ID);
            if(AD_OrgTrx_ID!=0)
                pstmt.setInt(8, AD_OrgTrx_ID);
            
            log.fine(sql + "AD_Client_ID=" + AD_Client_ID + 
                    ", C_AcctSchema_ID=" + C_AcctSchema_ID +
                    ", Account_ID=" + Account_ID +
                    ", PostingType=" + postingType +
                    ", Year=" + year.getYearAsInt() +
                    ", PeriodNo=" + period.getPeriodNo() +
                    ", AD_Org_ID=" + AD_Org_ID +
                    ", AD_OrgTrx_ID=" + AD_OrgTrx_ID);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                acum = rs.getBigDecimal(1);
            rs.close();
            pstmt.close();
            //expert
            rs = null;
            pstmt = null;
            //expert
        }
        catch (SQLException e)
        {
            log.log(Level.SEVERE, sql.toString(), e);
        }
        
        return acum;
    }
    
    /**
     * Obtenemos el acumulado del ejercicio de la tabla Acct_Fact por los siguientes parametros
     * 
     *  @param  AD_Client_ID empresa
     *  @param AD_Org_ID organizacion
     *  @param C_AcctSchema_ID esquema contable
     *  @param Account_ID cuenta contable
     *  @param postingType tipo de posteo
     *  @param C_Period_ID, periodo
     *  @param AD_OrgTrx_ID organizacion transaccional
     *  @param trxName transcation
     * @return El valor acumulado hasta el periodo
     */
    public static BigDecimal getFactAcctEjer(int AD_Client_ID, int AD_Org_ID, int C_AcctSchema_ID,
            int Account_ID, String postingType, MPeriod period, int AD_OrgTrx_ID, 
            String trxName) {
        BigDecimal acum = Env.ZERO;
        
        //obtenemos el a&ntilde;p del periodo
        MYear year = new MYear(period.getCtx(), period.getC_Year_ID(), trxName);
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        sql.append(" SELECT COALESCE(SUM(AMTACCT),0) FROM EXME_RV_FACT_ACCT_PERIOD ")
        .append(" WHERE AD_Client_ID = ? AND C_AcctSchema_ID = ? ")
        .append(" AND Account_ID=? AND PostingType=? AND ")
        .append(" Year = ? AND PeriodNo <= ? ");
        if(AD_Org_ID!=0)
        	sql.append(" AND AD_Org_ID = ?");
        if(AD_OrgTrx_ID!=0)
            sql.append(" AND AD_OrgTrx_ID = ?");
        try
        {
            PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, AD_Client_ID);
            pstmt.setInt(2, C_AcctSchema_ID);
            pstmt.setInt(3, Account_ID);
            pstmt.setString(4, postingType);
            pstmt.setInt(5, year.getYearAsInt());
            pstmt.setInt(6, period.getPeriodNo());
            if(AD_Org_ID!=0)
                pstmt.setInt(7, AD_Org_ID);
            if(AD_OrgTrx_ID!=0)
                pstmt.setInt(8, AD_OrgTrx_ID);
            
            log.fine(sql + "AD_Client_ID=" + AD_Client_ID + 
                    ", C_AcctSchema_ID=" + C_AcctSchema_ID +
                    ", Account_ID=" + Account_ID +
                    ", PostingType=" + postingType +
                    ", Year=" + year.getYearAsInt() +
                    ", PeriodNo=" + period.getPeriodNo() +
                    ", AD_Org_ID=" + AD_Org_ID +
                    ", AD_OrgTrx_ID=" + AD_OrgTrx_ID);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                acum = rs.getBigDecimal(1);
            rs.close();
            pstmt.close();
            //expert
            rs = null;
            pstmt = null;
            //expert
        }
        catch (SQLException e)
        {
            log.log(Level.SEVERE, sql.toString(), e);
        }
        
        return acum;
    }    
}
