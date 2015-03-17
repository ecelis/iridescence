package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEProductEsp extends X_EXME_ProductEsp {

    /** Static logger           */
    private static CLogger s_log = CLogger.getCLogger(MEXMEProductEsp.class);
    
	/*** Version serial */
	private static final long serialVersionUID = 1L;
	
	public MEXMEProductEsp(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}

	

    public static List<Integer> getProdEsp(Properties ctx, int prodID, String trxName) 
    throws Exception{
        
        StringBuffer sql = new StringBuffer();
                
        sql.append("SELECT pe.EXME_Especialidad_ID FROM EXME_ProductEsp pe ")
        .append(" WHERE pe.IsActive = 'Y' ")
        .append(" AND pe.M_Product_ID = ? ")
        .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ProductEsp", "pe"));
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> lista = new ArrayList<Integer>();
        try {
        	
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, prodID);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                lista.add(rs.getInt(1));
            }
                
        
        } catch (Exception e)
        {
            s_log.log(Level.SEVERE, "getProdEnAlm", e);
            throw new Exception(e.getMessage());
        } finally{
        	DB.close(rs, pstmt);
        }
        
        return lista;
    }
    
}
