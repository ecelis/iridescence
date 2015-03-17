package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MHL7MessageDef extends X_HL7_MessageDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6570919044872877648L;
	/**	Static Logger				*/
	private static CLogger log = CLogger.getCLogger (MHL7MessageDef.class);
			
	public MHL7MessageDef(Properties ctx, int HL7_MessageDef_ID, String trxName) {
		super(ctx, HL7_MessageDef_ID, trxName);

	}

	public MHL7MessageDef(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}

	/**
	 * Called after Save for Post-Save Operation
	 * 
	 * @param newRecord
	 *            new record
	 * @param success
	 *            true if save operation was success
	 * @return if save was a success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {

		if (newRecord & success) {

//			MHL7SegmentDef defaultMSH = new MHL7SegmentDef(getCtx(), 0, this
//					.get_TrxName());
//
//			defaultMSH.setHL7_MessageDef_ID(this.getHL7_MessageDef_ID());
//
//			defaultMSH.setHL7_Segment_ID(MHL7Segment.getByName(getCtx(), "MSH")
//					.getHL7_Segment_ID());
//
//			defaultMSH.save();
//
//			try {
//				DB.commit(false, get_TrxName());
//			} catch (IllegalStateException e) {
//
//				e.printStackTrace();
//			} catch (SQLException e) {
//
//				e.printStackTrace();
//			}

		}
		return success;
	} // afterSave

	public List<MHL7SegmentDef> getSegmentsDefs(){
		ArrayList<MHL7SegmentDef> segmentDefs = new ArrayList<MHL7SegmentDef>();
		
		String sql = "SELECT * from HL7_SegmentDef SD WHERE SD.HL7_MessageDef_Id = ? ";
		PreparedStatement pstmt = null;
		try{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, this.getHL7_MessageDef_ID());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				segmentDefs.add(new MHL7SegmentDef(getCtx(), rs.getInt(1), null));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getSegmentsDefs", e);
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
		
		return segmentDefs;
	}
	
	public static ArrayList<MHL7MessageDef> getAllByOrg(Properties ctx, int AD_Org_ID, String trxName) {
		ArrayList<MHL7MessageDef> list = new ArrayList<MHL7MessageDef>();
		
		String sql = "SELECT * FROM HL7_MESSAGEDEF WHERE ISACTIVE = 'Y' AND AD_ORG_ID = ? ORDER BY HL7_MESSAGEDEF_ID";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, AD_Org_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MHL7MessageDef(ctx, rs, trxName));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, "MHL7MessageDef.getAllByOrg", e);
		}
		try {
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		
		return list;
	}
	
}
