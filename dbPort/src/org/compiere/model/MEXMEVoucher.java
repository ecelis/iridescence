package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.DB;

/**
 * 
 * @author LLama
 *
 */
public class MEXMEVoucher extends X_EXME_Voucher{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     * @param ctx
     * @param EXME_Voucher_ID
     * @param trxName
     */
    public MEXMEVoucher(Properties ctx, int EXME_Voucher_ID, String trxName) {
        super(ctx, EXME_Voucher_ID, trxName);
    }

    /**
     * 
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEVoucher(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Obtiene la lista con las voucheras activas
     * 
     * @param ctx
     * @param trxName
     * @return
     */
    public static ArrayList<LabelValueBean> get(Properties ctx, String trxName){
        
        ArrayList<LabelValueBean> lista = new ArrayList<LabelValueBean>();
        
        String sql = " SELECT * FROM EXME_Voucher WHERE EXME_Voucher.isActive = 'Y' ";
        
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Voucher");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql,trxName);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                lista.add(new LabelValueBean(rs.getString("Name"), rs.getString("Value")));
            }
           
        } catch (SQLException e) {
        } finally {
        	DB.close(rs, pstmt);
        }
        
        return lista;
    }
}
