package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

@SuppressWarnings("serial")
public class MBPGroupAcct extends X_C_BP_Group_Acct{

	private static CLogger		s_log = CLogger.getCLogger (MBPGroupAcct.class);
	
	public MBPGroupAcct(Properties ctx, int C_BP_Group_Acct_ID, String trxName) {
		super(ctx, C_BP_Group_Acct_ID, trxName);
	}

	public static int getValidCombinationID(Properties ctx, int C_BP_Group, String trxName){
		int id = 0;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select EXME_COB_NF_ACCT as valid from C_BP_GROUP_ACCT where C_BP_GROUP_ID = ?");
		
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_BP_Group_Acct"));
		PreparedStatement pstmt = null;

        try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);
        	pstmt.setInt(1, C_BP_Group);
        	ResultSet rs = pstmt.executeQuery();
        	if(rs.next()){
        		id = rs.getInt("valid");
        	}
        	rs.close();
            pstmt.close();
            pstmt = null;
            rs=null;

        	} catch (Exception e){
        		s_log.log(Level.SEVERE, "getValidCombinationID ", e);
        	} finally{
        		try{
    				if (pstmt != null)
    					pstmt.close ();
    			}catch (Exception e){}
    			pstmt = null;
        	}

		return id;
	}

}
