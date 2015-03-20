/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MEXMECharge {

//	private static final long	serialVersionUID	= 1L;
    /** Static Logger   */
    private static CLogger  s_log   = CLogger.getCLogger (MEXMECharge.class);
    
//    /**
//     * @param ctx
//     * @param M_Charge_ID
//     * @param trxName
//     */
//    public MEXMECharge(Properties ctx, int M_Charge_ID, String trxName) {
//        super(ctx, M_Charge_ID, trxName);
//    }

//    /**
//     * @param ctx
//     * @param rs
//     * @param trxName
//     */
//    public MEXMECharge(Properties ctx, ResultSet rs, String trxName) {
//        super(ctx, rs, trxName);
//    }
    
    /**
     * Usar {@link MCharge#getChargeList(Properties, String)} 
     * @param ctx
     * @param trxName
     * @return
     */
    public static List<MCharge> getCharge (Properties ctx, String trxName)
    {
    	return MCharge.getChargeList(ctx, trxName);
//        ArrayList<MCharge> list = new ArrayList<MCharge>();
//        
//        String sql = "SELECT C_Charge.* FROM C_Charge WHERE C_Charge.isActive = 'Y' ";
//        
//        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_Charge");
//        
//        sql = sql + " ORDER BY NAME " ;
//        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        
//        try{
//            pstmt = DB.prepareStatement(sql, null);
//            rs = pstmt.executeQuery();
//            
//            while (rs.next()){
//                list.add(new MCharge(ctx, rs, trxName));
//            }
//        }
//        catch (SQLException e){
//            s_log.log(Level.SEVERE, sql, e);
//            list = null;
//        } finally {
//        	DB.close(rs,pstmt);
//        }
//        
//        return list;
    }  
    
    public static List<MCharge> getChargeAcctType (Properties ctx, String trxName)
    {
    	return MCharge.getChargeAcctTypeList(ctx, trxName);
    }
    
    public static ArrayList<MCharge> getAllAdjustmentByType(Properties ctx, String type, String trxName) {
    	ArrayList<MCharge> list = new ArrayList<MCharge>();
    	
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append("SELECT * FROM C_CHARGE WHERE ISACTIVE = 'Y' AND TYPE = ? ");
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), null);
    		pstmt.setString(1, type);
    		
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()) {
    			list.add(new MCharge(ctx, rs, trxName));
    		}
    	} catch (SQLException e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    		list = null;
    	} finally {
    		DB.close(rs, pstmt);
    	}
    	
    	return list;
    } 
    
    public static MCharge getAdjustmentByType(Properties ctx, String type, String trxName) {
    	MCharge adj = null;
    	
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append("SELECT * FROM C_CHARGE WHERE ISACTIVE = 'Y' AND TYPE = ? ");
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), null);
    		pstmt.setString(1, type);
    		
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			adj = new MCharge(ctx, rs, trxName);
    		}
    	} catch (SQLException e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    		adj = null;
    	} finally {
    		DB.close(rs, pstmt);
    	}
    	
    	return adj;
    } 
    
    /**
     * Obtener ajuste a traves del Codigo de Razon de Ajuste. Creado para el procesamiento del mensaje 835 (Claim Payment)
     * 
     * @author Adrian Bautista
     */
    public static MCharge getAdjByReasonCode(Properties ctx, String code, String trxName) {
    	MCharge adj = null;
    	
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append("SELECT * FROM C_CHARGE WHERE ISACTIVE = 'Y' AND VALUE = ? ");
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), null);
    		pstmt.setString(1, code);
    		
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			adj = new MCharge(ctx, rs, trxName);
    		}
    	} catch (SQLException e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    		adj = null;
    	} finally {
    		DB.close(rs, pstmt);
    	}
    	
    	return adj;
    }
    
//    /**
//	 * Objeto de impuesto del cargo
//	 * 
//	 * @return <MTax> tasa de impuesto
//	 */
//	public MTax getTax() {
//		MTax impuesto = null;
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append(" SELECT C_Tax.* FROM C_CHARGE cte ")
//				.append(" INNER JOIN C_TaxCategory  ON cte.C_TaxCategory_ID = C_TaxCategory.C_TaxCategory_ID ")
//				.append("                           AND C_TaxCategory.IsActive = 'Y' ")
//				.append("      INNER JOIN C_Tax ON C_TaxCategory.C_TaxCategory_ID = C_Tax.C_TaxCategory_ID AND C_Tax.IsActive = 'Y' ")
//				.append(" WHERE cte.IsActive = 'Y' ")
//				.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
//						X_C_Charge.Table_Name, "cte"));
//		
//		//Se actualiza codigo para usar TO_DATE y evitar el TRUNC y DATE_TRUNC
//		//Jesus Cantu
//		sql.append(" AND TO_DATE(TO_CHAR(C_Tax.ValidFrom, 'dd/MM/yyyy'), 'dd/MM/yyyy') "); 
//		sql.append (" <= TO_DATE('");
//		sql.append(Constantes.getSdfFecha().format(new Date()));	
//		sql.append("', 'dd/MM/yyyy') ");
//					
//		sql.append(" AND C_Tax.IsDocumentLevel = 'Y' ")
//				.append(" AND cte.C_CHARGE_ID = ? ")
//				.append(" ORDER BY C_Tax.ValidFrom DESC, C_Tax.IsDefault DESC ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			pstmt.setInt(1, getC_Charge_ID());
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				impuesto = new MTax(getCtx(), rs, get_TrxName());
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return impuesto;
//	}
//	
	public static List<KeyNamePair> getKeyAdjustmentByType(Properties ctx, String type, String trxName) {
    	ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
    	for (MCharge adj : getAllAdjustmentByType(ctx, type, trxName)) {
    		list.add(new KeyNamePair(adj.getC_Charge_ID(), adj.getName()));
    	}
    	return list;
    }	
}