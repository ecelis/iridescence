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

public class MHL7FieldDef extends X_HL7_FieldDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7837085843151290038L;

	public MHL7FieldDef(Properties ctx, int HL7_FieldDef_ID, String trxName) {
		super(ctx, HL7_FieldDef_ID, trxName);

	}

	public MHL7FieldDef(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}
	
	protected transient static CLogger	s_log =CLogger.getCLogger(MHL7FieldDef.class);
	
	public List<MHL7ComponentDef> getComponentsDefs(){
		ArrayList<MHL7ComponentDef> componentDefs = new ArrayList<MHL7ComponentDef>();
		
		String sql = "SELECT * FROM HL7_ComponentDef FD WHERE FD.HL7_FieldDef_Id = ? ";
		PreparedStatement pstmt = null;
		try{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, this.getHL7_SegmentDef_ID());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				componentDefs.add(new MHL7ComponentDef(getCtx(), rs.getInt(1), null));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getComponentDefs", e);
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
		
		return componentDefs;
	}

	public static List<MHL7FieldDef> getRegByDef(Properties ctx, int HL7_SegmentDef_ID, String trxName) {
		ArrayList<MHL7FieldDef> list = new ArrayList<MHL7FieldDef>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM HL7_FIELDDEF WHERE ISACTIVE = 'Y' AND HL7_SEGMENTDEF_ID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_SegmentDef_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MHL7FieldDef(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getFieldByDef", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	public static Class<MHL7Field> mapClass = MHL7Field.class;
	public static String mapIDColumn = COLUMNNAME_HL7_Field_ID;
	public static Class<MHL7ComponentDef> childClass = MHL7ComponentDef.class;
	public static String parentIDColumn = COLUMNNAME_HL7_SegmentDef_ID;
}
