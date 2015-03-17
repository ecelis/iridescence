package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MHL7SegmentDef extends X_HL7_SegmentDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2766489194639197888L;

	public MHL7SegmentDef(Properties ctx, int HL7_SegmentDef_ID, String trxName) {
		super(ctx, HL7_SegmentDef_ID, trxName);
		
	}

	public MHL7SegmentDef(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	protected transient static CLogger	s_log =CLogger.getCLogger(MHL7SegmentDef.class);
	
	public boolean afterSave (boolean newRecord, boolean success){
		
		
		if (newRecord  && success && this.getHL7_Segment().getName().equals("MSH")){
			
		}
		
		return success;
	}
	
	public List<MHL7FieldDef> getFieldDefs(){
		ArrayList<MHL7FieldDef> fieldDefs = new ArrayList<MHL7FieldDef>();
		
		String sql = "SELECT * FROM HL7_FieldDef FD WHERE FD.HL7_SegmentDef_Id = ? ";
		PreparedStatement pstmt = null;
		try{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, this.getHL7_SegmentDef_ID());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				fieldDefs.add(new MHL7FieldDef(getCtx(), rs.getInt(1), null));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getFiledDefs", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		return fieldDefs;
	}
	
	public static List<MHL7SegmentDef> getRegByDef(Properties ctx, int HL7_MessageDef_ID, String trxName) {
		ArrayList<MHL7SegmentDef> list = new ArrayList<MHL7SegmentDef>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM HL7_SEGMENTDEF WHERE ISACTIVE = 'Y' AND HL7_MESSAGEDEF_ID = ? ORDER BY SEQUENCE");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_MessageDef_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MHL7SegmentDef(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getSegmentByDef", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	public static Class<MHL7Segment> mapClass = MHL7Segment.class;
	public static String mapIDColumn = COLUMNNAME_HL7_Segment_ID;
	public static Class<MHL7FieldDef> childClass = MHL7FieldDef.class;
	public static String parentIDColumn = COLUMNNAME_HL7_MessageDef_ID;
}
