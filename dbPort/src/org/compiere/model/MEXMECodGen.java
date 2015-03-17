package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author LLama
 *
 */
public class MEXMECodGen extends X_EXME_CodGen {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static CLogger s_log = CLogger.getCLogger(MEXMECodGen.class);

	/**
     * @param ctx
     * @param EXME_CodGen_ID
     * @param trxName
     */
    public MEXMECodGen(Properties ctx, int EXME_CodGen_ID, String trxName) {
        super(ctx, EXME_CodGen_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
	public MEXMECodGen(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}    
    
    /**
     * 
     */
    public static MEXMECodGen[] getAll(Properties ctx, String whereClause, String trxName){
        
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        PreparedStatement psmt = null;
        MEXMECodGen[] retValue = null;
        ArrayList<MEXMECodGen> lista = new ArrayList<MEXMECodGen>();
        
        try {
            sql.append("SELECT EXME_CodGen.* FROM EXME_CodGen WHERE EXME_CodGen.isActive = 'Y' ");
            
            if (whereClause != null){
                sql.append(whereClause);
            }
            sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_CodGen"));
            
            psmt = DB.prepareStatement(sql.toString(), trxName);
            rs = psmt.executeQuery();
            
            while (rs.next()) {
                MEXMECodGen value = new MEXMECodGen(ctx, rs, null);
               lista.add(value);                
            }
            
        } catch (Exception e) {
            s_log.log(Level.SEVERE, e.getMessage(), e);
        } finally {
        	DB.close(rs,psmt);
			psmt = null;
			rs =null;
        }
        
        retValue = new MEXMECodGen[lista.size()];
        lista.toArray(retValue);
        
        return retValue;
        
    }

}
