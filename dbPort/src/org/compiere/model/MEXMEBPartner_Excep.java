package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Modelo de Exepcion de Socio de Negocios
 */

public class MEXMEBPartner_Excep extends X_EXME_BPartner_Excep {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String EXME_Paciente_ID = "EXME_BPartner_Excep_ID";

    /**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEBPartner_Excep.class);

    /**
     * @param ctx
     * @param EXME_EsqDesLine_ID
     * @param trxName
     */

    public MEXMEBPartner_Excep(Properties ctx, int EXME_BPartner_Excep_ID, String trxName) {
    	
        super(ctx, EXME_BPartner_Excep_ID, trxName);
        if(EXME_BPartner_Excep_ID == 0){
            setValidFrom(DB.getTimestampForOrg(ctx));
            
        }
    }
    
    public MEXMEBPartner_Excep(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    private MEXMEBPartner m_BPartner = null; 
    private MBPGroup m_BPartnerGroup = null;
    private MProduct m_Product = null;
    private MProductCategory m_ProductCategory = null;
    private MWarehouse m_Warehouse = null;

    public MEXMEBPartner getBpartner() {

        if(m_BPartner == null){
            m_BPartner = new MEXMEBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
        }
        
        return m_BPartner;
    }

   
    public MBPGroup getBpGroup() {  

        if(m_BPartnerGroup == null){
            m_BPartnerGroup = new MBPGroup(getCtx(), getC_BP_Group_ID(), get_TrxName());
        }

        return m_BPartnerGroup;
    }


    public MProduct getProduct() {

    	if(m_Product == null){
            m_Product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
        }

        return m_Product;
    }

    
    public MProductCategory getProductCategory() {

        if(m_ProductCategory == null){
            m_ProductCategory = new MProductCategory(getCtx(), getM_Product_Category_ID(), get_TrxName());
        }

        return m_ProductCategory;
    }
    
    public MWarehouse getWarehouse() {

        if(m_Warehouse == null){
            m_Warehouse = new MWarehouse(getCtx(), getM_Warehouse_ID(), get_TrxName());
        }

        return m_Warehouse;
    } 

    /**
     * Actualiza las variables miembro si es que hubo algun cambio en los IDs.
     *
     */

    public void refreshMembers(){      

        if(getC_BPartner_ID() != getBpartner().getC_BPartner_ID()){
            m_BPartner = new MEXMEBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
        }

        if(getC_BP_Group_ID() != getBpGroup().getC_BP_Group_ID()){
            m_BPartnerGroup = new MBPGroup(getCtx(), getC_BP_Group_ID(), get_TrxName());
        }
        
        if(getM_Product_ID() != getProduct().getM_Product_ID()){
            m_Product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
        }
        
        if(getM_Product_Category_ID() != getProductCategory().getM_Product_Category_ID()){
            m_ProductCategory = new MProductCategory(getCtx(), getM_Product_Category_ID(), get_TrxName());
        }
        if(getM_Warehouse_ID() != getWarehouse().getM_Warehouse_ID()){
            m_Warehouse = new MWarehouse(getCtx(), getM_Warehouse_ID(), get_TrxName());
        }

    }

    /**
     * 
     * @param ctx
     * @param whereClause
     * @param orderBy
     * @param trxName
     * @param all
     * @return
     */

    public static MEXMEBPartner_Excep[] get(Properties ctx, String whereClause, String orderBy, String trxName, boolean all) {

        ArrayList<MEXMEBPartner_Excep> list = new ArrayList<MEXMEBPartner_Excep>();
		String sql = "SELECT * FROM EXME_BPartner_Excep WHERE "; 
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_BPartner_Excep");

		if (whereClause != null)
			sql +=  whereClause;

		if(!all){
		    sql += "AND isActive = 'Y' ";
		}

		if(orderBy != null && orderBy.length() > 0)
			sql += " ORDER BY " + orderBy;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			    MEXMEBPartner_Excep esqDescLinea = new MEXMEBPartner_Excep(ctx, rs, trxName);
				list.add(esqDescLinea);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
		}

		MEXMEBPartner_Excep[] partnerExceptionLineas = new MEXMEBPartner_Excep[list.size()];

		list.toArray(partnerExceptionLineas);

		return partnerExceptionLineas;

    }

    /**
     * 
     * @param ctx
     * @param whereClause
     * @param orderBy
     * @param trxName
     * @param all
     * @return
     */
    public static MEXMEBPartner_Excep[] get(Properties ctx, String sql, String s_param1, String trxName) {

        ArrayList<MEXMEBPartner_Excep> list = new ArrayList<MEXMEBPartner_Excep>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql, trxName);
			
			if(s_param1 != null)
			    pstmt.setString(1, s_param1);

			rs = pstmt.executeQuery();
			while (rs.next()) {
			    int EXME_EsqDesLine_ID = rs.getInt(1);
			    if(EXME_EsqDesLine_ID > 0){
			        MEXMEBPartner_Excep esqDescLinea = new MEXMEBPartner_Excep(ctx, EXME_EsqDesLine_ID, trxName);
			        list.add(esqDescLinea);
			    }
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
		}

		MEXMEBPartner_Excep[] partnerExceptionLineas = new MEXMEBPartner_Excep[list.size()];
		list.toArray(partnerExceptionLineas);
		return partnerExceptionLineas;

    }
    
    /**
     * 
     * @param ctx
     * @param whereClause
     * @param orderBy
     * @param trxName
     * @param all
     * @return
     */
    public static MEXMEBPartner_Excep get(Properties ctx, String whereClause, String trxName) {

        MEXMEBPartner_Excep esqDescLinea = null;
        String sql = "SELECT * FROM EXME_BPartner_Excep WHERE "; 
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_BPartner_Excep");
        if (whereClause != null){
            sql +=  whereClause;
            sql += "AND isActive = 'Y' ";
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                esqDescLinea = new MEXMEBPartner_Excep(ctx, rs, trxName);
            }
           
        } catch (Exception e) {
            s_log.log(Level.SEVERE, "get", e);
        } finally {
        	DB.close(rs,pstmt);
            pstmt = null;
        }
        return esqDescLinea;
    }
    
    public static MEXMEBPartner_Excep copyFrom(MEXMEBPartner_Excep partExcep){        

        MEXMEBPartner_Excep newPartExcep = new MEXMEBPartner_Excep(partExcep.getCtx(), 0, partExcep.get_TrxName());       

        copyValues(partExcep, newPartExcep);     

        return newPartExcep;

    }

}

