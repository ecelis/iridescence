package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MHL7Segment extends X_HL7_Segment {
	
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MHL7Segment.class);


	public MHL7Segment(Properties ctx, int HL7_Segment_ID, String trxName) {
		super(ctx, HL7_Segment_ID, trxName);
		
	}

	public MHL7Segment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
		
		
	}
	
	
	public static X_HL7_Segment getByName(Properties ctx, String name){


		if (name == null) {
			return null;
		}
		
		
		X_HL7_Segment segmento = null;
	

		String sql = "SELECT * FROM HL7_SEGMENT WHERE UPPER(name)=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, name.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				segmento = new X_HL7_Segment(ctx, rs, null);
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}

		return segmento;
	
	}
	
	

}
