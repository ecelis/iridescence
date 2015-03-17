package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEReferMot extends X_EXME_ReferMot {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static CLogger		s_log = CLogger.getCLogger (MEXMEPaciente.class);
	
	public MEXMEReferMot(Properties ctx, int id, String trxName){
		super(ctx,id,trxName);
	}
	
	public MEXMEReferMot(Properties ctx, ResultSet rs, String trxName){
		super(ctx,rs,trxName);
	}
	
	
	public static MEXMEReferMot getByValue(Properties ctx, String value, String trxName){
		MEXMEReferMot o = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM EXME_ReferMot WHERE IsActive = 'Y' AND UPPER(Value) LIKE '" + value + "'"  );
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
			    o = new MEXMEReferMot(ctx, rs, trxName);
			}
			
		} catch (Exception e){
			s_log.log(Level.SEVERE, "getByValue", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		
		
		return o;
	}
	
}
