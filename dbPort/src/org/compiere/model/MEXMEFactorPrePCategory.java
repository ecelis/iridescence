/**
 * 
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
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * @author Expert
 *
 */
public class MEXMEFactorPrePCategory extends X_EXME_FactorPrePCategory {
	/** s_log */
	private static CLogger      s_log = CLogger.getCLogger (MEXMEFactorPrePCategory.class);
	/** serialVersionUID */
	private static final long serialVersionUID = 2684214420739767886L;
	/** nombre de la categoria */
    private String categoryName=null;
    
	/**
	 * @param ctx
	 * @param EXME_FactorPrePCategory_ID
	 * @param trxName
	 */
	public MEXMEFactorPrePCategory(Properties ctx,
			int EXME_FactorPrePCategory_ID, String trxName) {
		super(ctx, EXME_FactorPrePCategory_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEFactorPrePCategory(Properties ctx, ResultSet rs, String trxName) {
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
    public static List<MEXMEFactorPrePCategory> factorPrecioCategory(Properties ctx, int EXME_FactorPre_ID, String trxName) 
    throws Exception {
        List<MEXMEFactorPrePCategory> resultado = new ArrayList<MEXMEFactorPrePCategory>();

        //variable string con la sentencia sql
        StringBuilder sql = new StringBuilder();
        //PreparedStatement con la sentencia sql
        PreparedStatement pstmt = null;
        //ResultSet utilizado para manipular los resultados
        ResultSet rs = null;

        sql.append(" SELECT fppc.*, pc.Name AS Category FROM EXME_FactorPrePCategory fppc ")
        .append(" LEFT JOIN M_Product_Category pc ON ")
        .append(" fppc.M_Product_Category_ID = pc.M_Product_Category_ID ")
        .append(" WHERE fppc.IsActive='Y' ")
        .append(" AND fppc.EXME_FactorPre_ID = ? ")
        .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_FactorPrePCategory", "fppc"))
        .append(" ORDER BY pc.Name ");

        try {

            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_FactorPre_ID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	MEXMEFactorPrePCategory fact = new MEXMEFactorPrePCategory(ctx, rs, trxName);
            	fact.setCategoryName(rs.getString("Category"));
                resultado.add(fact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DB.close(rs, pstmt);
        }
  
        return resultado;
    }
    
    /**
     * Obtiene el factor id del factor precio
     * deacuerdo a la categoria de parametro
     *
     * @param ctx
     * @param productCategoryID
     * @param trxName
     * @return
     */
    public static int factorPrecioid(final Properties ctx, final int productCategoryID, final String trxName) {
        int resultado = 0;
        //variable string con la sentencia sql
        StringBuilder sql = new StringBuilder();
        //PreparedStatement con la sentencia sql
        PreparedStatement pstmt = null;
        //ResultSet utilizado para manipular los resultados
        ResultSet rs = null;

        sql.append(" SELECT fppc.EXME_FactorPre_ID        ")
        .append("    FROM   EXME_FactorPrePCategory  fppc ")
        .append("    WHERE  fppc.IsActive='Y'             ")
        .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_FactorPrePCategory", "fppc"))
        .append("    AND    fppc.M_Product_Category_ID IN ( null, ? ) ");

        try {

            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, productCategoryID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	resultado = rs.getInt("EXME_FactorPre_ID");
            }
        } catch (SQLException e) {
        	s_log.log(Level.SEVERE, "get - closing", e);
        } finally {
        	DB.close(rs, pstmt);
        }
  
        return resultado;
    }



	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * se obtiene el registro de factorPRePCategory filtrado por categoriaID
	 * @param ctx
	 * @param catID
	 * @return
	 */
	public static MEXMEFactorPrePCategory getByCategory(Properties ctx, int catID){
		
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
	        }
	        
	    } catch (SQLException e) {
	    	s_log.log(Level.SEVERE, e.getMessage(), e);
	    	
	    } finally {
	    	DB.close(rs, pstmt);
	    	
	    }
	    
	    return factorCat;
	    
	}
	
	/**
	 * se obtiene el registro de factorPRePCategory filtrado por productoID
	 * @param ctx
	 * @param productID
	 * @return
	 */
	public static MEXMEFactorPrePCategory getByProduct(Properties ctx, int productID){
		
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
				
			}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			
		}
		
		return factorCat;
		
	}
	 
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		//SE VALIDA QUE LA CATEGORIA O EL PRODUCTO NO EXISTA EN OTRO FACTOR
		MEXMEFactorPrePCategory factorCat = MEXMEFactorPrePCategory.getByCategory(Env.getCtx(), getM_Product_Category_ID());
		if(factorCat!=null && factorCat.getEXME_FactorPrePCategory_ID() != getEXME_FactorPrePCategory_ID()){
			log.saveError(Utilerias.getMsg(getCtx(), "factorPre.unique.cat"), "");
			return false;
		}
		
		//SE VALIDA QUE EL PRODUCTO NO EXISTA EN OTRO FACTOR
		MEXMEFactorPrePCategory factorProd = MEXMEFactorPrePCategory.getByProduct(Env.getCtx(), getM_Product_ID());
		if(factorProd!=null && factorProd.getEXME_FactorPrePCategory_ID() != getEXME_FactorPrePCategory_ID()){
			log.saveError(Utilerias.getMsg(getCtx(), "factorPre.unique.prod"), "");
			return false;
		}
		
		
		return true;
	}
	
	
	
}
