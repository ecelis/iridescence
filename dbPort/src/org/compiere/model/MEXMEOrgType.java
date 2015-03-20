package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**FIXME: MOrgType */ 
public class MEXMEOrgType extends X_AD_OrgType {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 6746262957796585410L;
	
	/** Logger  */
    private static CLogger          s_log = CLogger.getCLogger (MEXMEOrgType.class);
    
    /**
     * @param ctx
     * @param AD_OrgType_ID
     * @param trxName
     */
    public MEXMEOrgType(Properties ctx, int AD_OrgType_ID, String trxName) {
        super(ctx, AD_OrgType_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEOrgType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    

    /**
     * Otiene todos los tipos de organizaciones
     * @param ctx
     * @param trxName
     * @return
     */
    public static List<LabelValueBean> getOrganizationType(Properties ctx, String trxName, boolean active) {
    	
    	List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder st = new StringBuilder("SELECT OT.* FROM AD_ORGTYPE OT ");
		
		if(active) {
			
			st.append(" WHERE ISACTIVE = 'Y' ");
			
		}
		
		
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEOrgType org = new MEXMEOrgType(ctx, rs, null);
				lst.add(new LabelValueBean(org.getName(), String.valueOf(org.getAD_OrgType_ID())));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}
    
    public static int getIdFromName(Properties ctx, String name, String trxName){
    	int ID = 0;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	StringBuilder sql = new StringBuilder();
    	sql.append( " SELECT AD_OrgType_ID FROM AD_OrgType ")
    	.append(" WHERE Isactive = 'Y' AND name = ? ");

    	sql = 
    		new StringBuilder(
    				MEXMELookupInfo.addAccessLevelSQL(
    						ctx, 
    						sql.toString(), 
    						"AD_OrgType"
    				)
    		);
    	
    	try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) 
                ID = rs.getInt("AD_OrgType_ID");
    
        } catch (Exception e) {
        	s_log.log(Level.SEVERE, sql.toString(), e);
        } finally {
            DB.close(rs, pstmt);
        }

    	return ID;
    }
    
}
