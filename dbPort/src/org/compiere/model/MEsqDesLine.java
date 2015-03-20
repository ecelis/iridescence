/*

 * Created on 26-may-2005

 *

 */

package org.compiere.model;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;



/**

 * Modelo de Esquema de Descuento Linea

 * <b>Modificado: </b> $Author: hassan $<p>

 * <b>En :</b> $Date: 2005/06/23 22:34:51 $<p>

 *

 * @author Hassan Reyes

 * @version $Revision: 1.2 $

 */

public class MEsqDesLine extends X_EXME_EsqDesLine {

	private static final long serialVersionUID = 1L;

//    public static final String EXME_Paciente_ID = "EXME_EsqDesLine_ID";

    

    /**	Static Logger				*/

//	private static CLogger		s_log = CLogger.getCLogger (MEsqDesLine.class);



    /**

     * @param ctx

     * @param EXME_EsqDesLine_ID

     * @param trxName

     */

    public MEsqDesLine(Properties ctx, int EXME_EsqDesLine_ID, String trxName) {
        super(ctx, EXME_EsqDesLine_ID, trxName);
        if(EXME_EsqDesLine_ID == 0){
            setValidFrom(DB.getTimestampForOrg(ctx));
        }
//        try {
//        	tipoArea = MEXMERefList.getListasTraducidas(MEsqDesLine.TIPOAREA_AD_Reference_ID, "es_MX", true);
//        	
//        } catch (Exception e) {
//        	s_log.log(Level.SEVERE, e.getMessage());
//		}
    }

//    private List<LabelValueBean>  tipoArea = new ArrayList<LabelValueBean> ();
    

//    public static MEsqDesLine copyFrom(MEsqDesLine esqDescLinea){
//
//        
//
//        MEsqDesLine newEsqDescLinea = new MEsqDesLine(esqDescLinea.getCtx(), 0, esqDescLinea.get_TrxName());
//
//        
//
//        copyValues(esqDescLinea, newEsqDescLinea);
//
//        //newEsqDescLinea.setEXME_EsqDesLine_ID(0);
//
//        
//
//        return newEsqDescLinea;
//
//    }



    /**

     * @param ctx

     * @param rs

     * @param trxName

     */

    public MEsqDesLine(Properties ctx, ResultSet rs, String trxName) {

        super(ctx, rs, trxName);

    }


//
//    private MEXMEBPartner m_BPartner = null;
//
//    
//
//    private MBPGroup m_BPartnerGroup = null;
//
//    
//
//    private MProduct m_Product = null;
//
//    
//
//    private MProductCategory m_ProductCategory = null;
//
//    
//
//    private MEXMEArea m_Area = null;
//
//    
//
//    private MUOM m_UOM = null;

//    public MEXMEArea getArea() {
//        if(m_Area == null || m_Area.getEXME_Area_ID() == 0){
//            m_Area = new MEXMEArea(getCtx(), getEXME_Area_ID(), get_TrxName());
//        }
//
//        return m_Area;
//    }

    /**
     * Etiqueta 
     * @return
     */
//    public String getNameTipoArea() {
//    	String nameTipoArea = "";
//    	
//    	if(getTipoArea()!=null){
//    		nameTipoArea = MRefList.getListName(getCtx(),MEsqDesLine.TIPOAREA_AD_Reference_ID,getTipoArea());
//    	}
////    		for (int i = 0; i < tipoArea.size(); i++) {
////    			LabelValueBean etiqueta = (LabelValueBean)tipoArea.get(i);
////    			if(etiqueta.getValue().equalsIgnoreCase(getTipoArea())){
////    				nameTipoArea = etiqueta.getLabel();
////    			}
////    		}
//    	return nameTipoArea;
//    }
    
//    public String getFecha() {
//    	return Constantes.getSdfFecha().format(getValidFrom());
//    }

//    public MEXMEBPartner getBpartner() {
//
//        
//
//        if(m_BPartner == null){
//
//            m_BPartner = new MEXMEBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
//
//        }
//
//        
//
//        return m_BPartner;
//
//    }

    

//    public MBPGroup getBpGroup() {
//
//        
//
//        if(m_BPartnerGroup == null){
//
//            m_BPartnerGroup = new MBPGroup(getCtx(), getC_BP_Group_ID(), get_TrxName());
//
//        }
//
//        
//
//        return m_BPartnerGroup;
//
//    }
//
//    
//
//    public MProduct getProduct() {
//
//        
//
//        if(m_Product == null){
//
//            m_Product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
//
//        }
//
//        
//
//        return m_Product;
//
//    }
//
//    
//
//    public MProductCategory getProductCategory() {
//
//        
//
//        if(m_ProductCategory == null){
//
//            m_ProductCategory = new MProductCategory(getCtx(), getM_Product_Category_ID(), get_TrxName());
//
//        }
//
//        
//
//        return m_ProductCategory;
//
//    }

    

//    public MUOM getUom() {
//
//        
//
//        if(m_UOM == null || m_UOM.getC_UOM_ID() == 0){
//
//            m_UOM = new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
//
//        }
//
//        
//
//        return m_UOM;
//
//    }

    

    /**

     * Actualiza las variables miembro si es que hubo algun cambio en los IDs.

     *
//
//     */
//
//    public void refreshMembers(){
//
//        
//
//        if(getC_BPartner_ID() != getBpartner().getC_BPartner_ID()){
//
//            m_BPartner = new MEXMEBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
//
//        }
//
//        
//
//        if(getC_BP_Group_ID() != getBpGroup().getC_BP_Group_ID()){
//
//            m_BPartnerGroup = new MBPGroup(getCtx(), getC_BP_Group_ID(), get_TrxName());
//
//        }
//
//        
//
//        if(getM_Product_ID() != getProduct().getM_Product_ID()){
//
//            m_Product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
//
//        }
//
//        
//
//        if(getM_Product_Category_ID() != getProductCategory().getM_Product_Category_ID()){
//
//            m_ProductCategory = new MProductCategory(getCtx(), getM_Product_Category_ID(), get_TrxName());
//
//        }
//
//        
//
//        if(getC_UOM_ID() != getUom().getC_UOM_ID()){
//
//            m_UOM = new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
//
//        }
//
//    }

    /**

     * 

     * @param ctx

     * @param whereClause

     * @param orderBy

     * @param trxName

     * @param all

     * @return

     */

//    public static MEsqDesLine[] get(Properties ctx, String whereClause, String orderBy
//
//            , String trxName, boolean all) {
//
//        ArrayList<MEsqDesLine> list = new ArrayList<MEsqDesLine>();
//
//		String sql = "SELECT * FROM EXME_EsqDesLine WHERE "; 
//
//		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_EsqDesLine");
//
//		if (whereClause != null)
//			sql +=  whereClause;
//
//		if(!all){
//		    sql += "AND isActive = 'Y' ";
//		}
//
//		if(orderBy != null && orderBy.length() > 0)
//			sql += " ORDER BY " + orderBy;
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			pstmt = DB.prepareStatement(sql, trxName);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//			    MEsqDesLine esqDescLinea = new MEsqDesLine(ctx, rs, trxName);
//				list.add(esqDescLinea);
//			}
//
//    	} catch (Exception e) {
//    		//sql = null;
//    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//    		try {
//    			if (pstmt != null)
//    				pstmt.close ();
//    			if (rs != null)
//    				rs.close ();
//    		} catch (Exception ex) {
//    			s_log.log(Level.SEVERE, sql.toString(), ex);
//    		}
//    		pstmt = null;
//    		rs = null;
//    	}finally {
//    		try {
//    			if (pstmt != null) {
//    				pstmt.close();
//    			}
//    			if (rs != null) {
//    				rs.close();
//    			}
//    			pstmt = null;
//    			rs = null;
//    		} catch (Exception e) {
//    			s_log.log(Level.SEVERE, "Closing rs and pstmt", e.getMessage());
//    			pstmt = null;
//    			rs = null;
//    		}
//    	}
//		
//		MEsqDesLine[] esqDescLineas = new MEsqDesLine[list.size()];
//		list.toArray(esqDescLineas);
//
//		return esqDescLineas;
//
//    }
//
//    

    /**

     * 

     * @param ctx

     * @param whereClause

     * @param orderBy

     * @param trxName

     * @param all

     * @return

     */

//    public static MEsqDesLine[] get(Properties ctx, String sql, String s_param1, String trxName) {
//
//        ArrayList<MEsqDesLine> list = new ArrayList<MEsqDesLine>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			pstmt = DB.prepareStatement(sql, trxName);
//
//			if(s_param1 != null)
//			    pstmt.setString(1, s_param1);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//			    int EXME_EsqDesLine_ID = rs.getInt(1);
//
//			    if(EXME_EsqDesLine_ID > 0){
//			        MEsqDesLine esqDescLinea = new MEsqDesLine(ctx, rs, trxName);
//			        list.add(esqDescLinea);
//
//			    }
//
//			}
//
//    	} catch (Exception e) {
//    		
//    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//    		try {
//    			if (pstmt != null)
//    				pstmt.close ();
//    			if (rs != null)
//    				rs.close ();
//    		} catch (Exception ex) {
//    			s_log.log(Level.SEVERE, sql.toString(), ex);
//    		}
//    		pstmt = null;
//    		rs = null;
//    	}finally {
//    		try {
//    			if (pstmt != null) {
//    				pstmt.close();
//    			}
//    			if (rs != null) {
//    				rs.close();
//    			}
//    			pstmt = null;
//    			rs = null;
//    		} catch (Exception e) {
//    			s_log.log(Level.SEVERE, "Closing rs and pstmt", e);
//    			pstmt = null;
//    			rs = null;
//    		}
//    		sql = null;
//    	}
//    	
//		MEsqDesLine[] esqDescLineas = new MEsqDesLine[list.size()];
//
//		list.toArray(esqDescLineas);
//
//		return esqDescLineas;
//
//    }
//
//    
    /**
     * 
     * @param ctx
     * @param whereClause
     * @param orderBy
     * @param trxName
     * @param all
     * @return
     */
//    public static MEsqDesLine get(Properties ctx, String whereClause, String trxName) {
//
//        MEsqDesLine esqDescLinea = null;
//        String sql = "SELECT * FROM EXME_EsqDesLine WHERE "; 
//        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_EsqDesLine");
//        if (whereClause != null){
//            sql +=  whereClause;
//            sql += "AND isActive = 'Y' ";
//        }
//        
//        PreparedStatement pstmt = null;
//		ResultSet rs = null;
//        
//		try {
//            pstmt = DB.prepareStatement(sql, trxName);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                esqDescLinea = new MEsqDesLine(ctx, rs, trxName);
//            }
//
//    	} catch (Exception e) {
//    		//sql = null;
//    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//    		try {
//    			if (pstmt != null)
//    				pstmt.close ();
//    			if (rs != null)
//    				rs.close ();
//    		} catch (Exception ex) {
//    			s_log.log(Level.SEVERE, sql.toString(), ex);
//    		}
//    		pstmt = null;
//    		rs = null;
//    	}finally {
//    		try {
//    			if (pstmt != null) {
//    				pstmt.close();
//    			}
//    			if (rs != null) {
//    				rs.close();
//    			}
//    			pstmt = null;
//    			rs = null;
//    		} catch (Exception e) {
//    			s_log.log(Level.SEVERE, "Closing rs and pstmt", e);
//    			pstmt = null;
//    			rs = null;
//    		}
//    	}
//        return esqDescLinea;
//    }

    
    /**
     * Permite hacerle referencia a EXME_Area_ID
     * en el jsp
     * @return
     */
//    public int getAreaID() {
//        return getEXME_Area_ID();
//    }
//    
//    public void setAreaID(int areaID) {
//    	this.setEXME_Area_ID(areaID);
//    }

    
}