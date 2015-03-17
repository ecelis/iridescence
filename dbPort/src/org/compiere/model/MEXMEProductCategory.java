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

public class MEXMEProductCategory extends MProductCategory {
	private static final long serialVersionUID = 1L;
    /** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMEProductCategory.class);
    
    public MEXMEProductCategory(Properties ctx, int M_Product_Category_ID, String trxName) {
        super(ctx, M_Product_Category_ID, trxName);
    }

    public MEXMEProductCategory(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Devolvemos una categoria de producto en base al nombre
     *  
     * @param ctx
     * @param documentNo
     * @param trxName
     * @return
     * @throws SQLException
     */
    public static MEXMEProductCategory getByName(Properties ctx, String description, String trxName) 
    throws SQLException {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MEXMEProductCategory productCat = null;

        try {
            //description = description.replaceAll("\\*", "%");//Lama: comodin estandar %

            sql.append("SELECT * FROM M_Product_Category WHERE UPPER(Description) LIKE UPPER(")
            .append(description).append(")");

            sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Product_Category"));

            pstmt = DB.prepareStatement(sql.toString(), trxName);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                productCat = new MEXMEProductCategory(ctx, rs, trxName);
            }
           

        } catch (SQLException e) {
            s_log.log(Level.SEVERE, "getByName (" + sql + ")", e);
            throw e;
        } finally {
        	DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }

        return productCat;
    }
    
//    /**
//	 * findCategorys
//	 */
//	public static List<MEXMEProductCategory> categoriasPaquetes(Properties ctx, int paqBaseVersionID) 
//	{
//		
//		
//		List<MEXMEProductCategory> listaCat =  new ArrayList<MEXMEProductCategory>();
//		
//		// trae las lineas del detalle de la version del paquete
//		MPaqBaseVersion ver = new MPaqBaseVersion(ctx,
//				paqBaseVersionID, null);
//		List<MPaqBaseDet> lista = ver.getLineas(ctx, null);
//
//		for (int g = 0; g < lista.size(); g++) {
//
//			MPaqBaseDet pacc = (MPaqBaseDet) lista.get(g);
//			if (pacc.isActive()) {
//				MProduct m_product = new MProduct(ctx, pacc
//						.getM_Product_ID(), null);
//				// agregamos la categoria de productos para los Descuentos si es
//				// que no existe
//				if (m_product.getM_Product_Category_ID() > 0) {
//
//					if (listaCat.size() > 0) {
//						boolean existe = false;
//						for (int i = 0; i < listaCat.size(); i++) {
//							MEXMEProductCategory cat = (MEXMEProductCategory) listaCat
//									.get(i);
//
//							if (cat.getM_Product_Category_ID() == m_product
//									.getM_Product_Category_ID())
//								existe = true;
//
//						}
//						if (!existe)
//							listaCat
//									.add(new MEXMEProductCategory(
//											ctx,
//											m_product
//													.getM_Product_Category_ID(),
//											null));
//
//					} else {
//						listaCat = new ArrayList<MEXMEProductCategory>();
//						listaCat.add(new MEXMEProductCategory(ctx,
//								((MPaqBaseDet) lista.get(0)).getProduct()
//										.getM_Product_Category_ID(), null));
//
//					}
//
//				}
//			}
//		}
//		return listaCat;
//	}

}