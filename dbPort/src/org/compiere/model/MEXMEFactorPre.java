/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Expert
 *
 */
public class MEXMEFactorPre extends X_EXME_FactorPre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7651708483059981619L;

	private static CLogger s_log = CLogger.getCLogger(MEXMEFactorPre.class); 

	/**
	 * @param ctx
	 * @param EXME_FactorPre_ID
	 * @param trxName
	 */
	public MEXMEFactorPre(Properties ctx, int EXME_FactorPre_ID, String trxName) {
		super(ctx, EXME_FactorPre_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEFactorPre(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

    
    /**
     *  obtiene el porcentaje de aumento en el factor-precio
     *
     *@param  productID
     *@param  ctx         Description of the Parameter
     *@param  precio      Description of the Parameter
     *@return
     *@throws  Exception
     */
    public static BigDecimal factorPrecio(Properties ctx, int pFactorPreID, BigDecimal nivel, String trxName) 
    throws Exception {
        BigDecimal resultado = null;

        if (nivel == null)
            return Env.ZERO;
        
        //PreparedStatement con la sentencia sql
        PreparedStatement pstmt = null;
        //ResultSet utilizado para manipular los resultados
        ResultSet rs = null;

      //variable string con la sentencia sql
        StringBuilder sql = new StringBuilder()
        // Se ordenan en orden acendente, se toma el "nivelsuperior" inmediato superior.
        .append(" SELECT fpd.Porcentaje ")
        .append(" FROM EXME_FactorPre ")
        .append(" LEFT JOIN EXME_FactorPreDet fpd ON ")
        .append("           EXME_FactorPre.EXME_FactorPre_ID = fpd.EXME_FactorPre_ID) ")
        .append(" WHERE EXME_FactorPre.IsActive='Y' ")
        .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_FactorPre"))
        .append(" AND   fpdEXME_FactorPre = ? ")
        .append(" AND   fpd.NivelSuperior <= ? ")
        .append(" ORDER BY fpd.NivelSuperior DESC  ");

        try {

            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, pFactorPreID);
            pstmt.setBigDecimal(2, nivel);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                resultado = rs.getBigDecimal("Porcentaje");
            }
        } catch (SQLException e) {
        	s_log.log(Level.SEVERE, e.getMessage(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
  
        return resultado;
    }

    
    /**
     *  obtiene el porcentaje de aumento en el factor-precio
     *
     *@param  productID
     *@param  ctx         Description of the Parameter
     *@param  precio      Description of the Parameter
     *@return
     *@throws  Exception
     */
    public static List<MEXMEFactorPreDet> factorPrecioDetalle(Properties ctx, int EXME_FactorPre_ID, String trxName){ 
    	List<MEXMEFactorPreDet> lstDet = new ArrayList<MEXMEFactorPreDet>();
    	
        //variable string con la sentencia sql
        String sql = null;
        //PreparedStatement con la sentencia sql
        PreparedStatement pstmt = null;
        //ResultSet utilizado para manipular los resultados
        ResultSet rs = null;

        // Se ordenan en orden acendente, se toma el "nivelsuperior" inmediato superior.
        sql = " SELECT * FROM EXME_FactorPreDet WHERE IsActive='Y' AND EXME_FactorPre_ID = ? ";
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_FactorPreDet");
        sql = sql + " ORDER BY NivelSuperior ASC  ";

        try {

            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, EXME_FactorPre_ID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	lstDet.add(new MEXMEFactorPreDet(ctx, rs, null));
            }
        } catch (SQLException e) {
        	s_log.log(Level.SEVERE, e.getMessage(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
  
        return lstDet;
    }

    
    /**
     *  obtiene listado de factor-precio
     *
     *@param  ctx         Description of the Parameter
     *@param  trxName      Description of the Parameter
     *@return  List<MEXMEFactorPre> por organizacion
     *@throws  Exception
     */
    public static List<MEXMEFactorPre> factorPrecio(Properties ctx, String trxName){ 
    	List<MEXMEFactorPre> lstDet = new ArrayList<MEXMEFactorPre>();
    	
        //variable string con la sentencia sql
        String sql = null;
        //PreparedStatement con la sentencia sql
        PreparedStatement pstmt = null;
        //ResultSet utilizado para manipular los resultados
        ResultSet rs = null;

        // Se ordenan en orden acendente, se toma el "nivelsuperior" inmediato superior.
        sql = " SELECT * FROM EXME_FactorPre WHERE IsActive='Y' ";
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_FactorPre");
        sql = sql + " ORDER BY Name DESC  ";

        try {

            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	lstDet.add(new MEXMEFactorPre(ctx, rs, null));
            }
        } catch (SQLException e) {
        	s_log.log(Level.SEVERE, e.getMessage(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
  
        return lstDet;
    }
    
    
    /**
     * obtiene el factor precio utilizado por un producto (solo puede existir uno configurado)
     * @param ctx
     * @param productID
     * @return
     */
    public static MEXMEFactorPre getFactorByProduct(Properties ctx, int productID){
    	
    	MEXMEFactorPre factor = null;
    	MEXMEFactorPrePCategory factorCat = null;
    	
        StringBuilder sql = new StringBuilder();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // Se ordenan en orden acendente, se toma el "nivelsuperior" inmediato superior.
        sql.append(" SELECT * FROM EXME_FactorPrePCategory WHERE IsActive='Y' AND M_Product_ID = ? ");
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_FactorPrePCategory"));
        sql.append(" ORDER BY Created DESC  ");
        
        try {

            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, productID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	factorCat = new MEXMEFactorPrePCategory(ctx, rs, null);
            	factor = new MEXMEFactorPre(ctx, factorCat.getEXME_FactorPre_ID(), null);
            	
            }
            
        } catch (SQLException e) {
        	s_log.log(Level.SEVERE, e.getMessage(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
        
        if(factor!=null && factor.isActive()){
        	return factor;
        }else{
        	return null;
        }
    	
		
	}
    
    /**
     * obtiene el factor precio utilizado por una categoria (solo puede existir uno configurado)
     * @param ctx
     * @param catID
     * @return
     */
    public static MEXMEFactorPre getFactorByCategory(Properties ctx, int catID){
    	
    	MEXMEFactorPre factor = null;
    	MEXMEFactorPrePCategory factorCat = null;
    	
        StringBuilder sql = new StringBuilder();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // Se ordenan en orden acendente, se toma el "nivelsuperior" inmediato superior.
        sql.append(" SELECT * FROM EXME_FactorPrePCategory WHERE IsActive='Y' AND M_Product_Category_ID = ? ");
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_FactorPrePCategory"));
        sql.append(" ORDER BY Created DESC  ");
        
        try {

            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, catID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	
            	factorCat = new MEXMEFactorPrePCategory(ctx, rs, null);
            	factor = new MEXMEFactorPre(ctx, factorCat.getEXME_FactorPre_ID(), null);
            
            }
            
        } catch (SQLException e) {
        	s_log.log(Level.SEVERE, e.getMessage(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
    	
        if(factor!=null &&  factor.isActive()){
        	return factor;
        }else{
        	return null;
        }
		
	}
}
