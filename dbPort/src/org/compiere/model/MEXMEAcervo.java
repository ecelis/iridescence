package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEAcervo extends X_EXME_Acervo{
	
	private static final long serialVersionUID = 1L;
	private static CLogger      log = CLogger.getCLogger (MEXMEArchCli.class);
	
	public MEXMEAcervo(Properties ctx, int EXME_Acervo_ID, String trxName) {
		super(ctx, EXME_Acervo_ID, trxName);
	}

	public MEXMEAcervo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEAcervo getMovimientos(Properties ctx, int EXME_ArchCliMov_ID, String trxName){
        final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);        
		MEXMEAcervo mov = null;
        
         sql.append(" SELECT * FROM EXME_Acervo ")
            .append(" WHERE EXME_Acervo.Isactive = 'Y' AND EXME_Acervo.EXME_ArchCliMov_ID = ? ")
         	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setInt(1, EXME_ArchCliMov_ID);
            rs = pstmt.executeQuery();
			if (rs.next()) {
				mov = new MEXMEAcervo(ctx, rs, trxName);
			}
            
    	} catch (Exception e) {    		
    		log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
		return mov;
	}
	
}
