package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 
 * @author Expert
 * @deprecated
 */
public class MFactorPre  extends X_EXME_FactorPre{

    /** serialVersionUID */
	private static final long serialVersionUID = 4702838604466836887L;

	public MFactorPre(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public MFactorPre(Properties ctx, int EXME_FactorPre_ID, String trxName) {
        super(ctx, EXME_FactorPre_ID, trxName);
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
    public static BigDecimal factorPrecio(Properties ctx, int EXME_FactorPre_ID, BigDecimal nivel, String trxName) 
    throws Exception {
        BigDecimal resultado = null;

        if (nivel == null)
            return Env.ZERO;
        //variable string con la sentencia sql
        String sql = null;
        //PreparedStatement con la sentencia sql
        PreparedStatement pstmt = null;
        //ResultSet utilizado para manipular los resultados
        ResultSet rs = null;

        // Se ordenan en orden acendente, se toma el "nivelsuperior" inmediato superior.
        sql = " SELECT fpd.Porcentaje FROM EXME_FactorPre " +
                " LEFT JOIN EXME_FactorPreDet fpd ON " +
                " (EXME_FactorPre.EXME_FactorPre_ID = fpd.EXME_FactorPre_ID) " +
                " WHERE EXME_FactorPre.IsActive='Y' " +
                " AND fpd.NivelSuperior <= ? ";
                
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_FactorPre");
        
        sql = sql + " ORDER BY fpd.NivelSuperior DESC  ";

        try {

            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setBigDecimal(1, nivel);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                resultado = rs.getBigDecimal("Porcentaje");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        sql = sql + " ORDER BY NivelSuperior DESC  ";

        try {

            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, EXME_FactorPre_ID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	lstDet.add(new MEXMEFactorPreDet(ctx, rs, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DB.close(rs, pstmt);
        }
  
        return lstDet;
    }

}
