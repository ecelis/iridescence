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

public class MHL7ComponentDef extends X_HL7_ComponentDef {

	
	private static final long serialVersionUID = 2866355226008394259L;

	public MHL7ComponentDef(Properties ctx, int HL7_ComponentDef_ID,
			String trxName) {
		super(ctx, HL7_ComponentDef_ID, trxName);
	}

	public MHL7ComponentDef(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	protected transient static CLogger	s_log =CLogger.getCLogger(MHL7ComponentDef.class);
	
	public List<MHL7SubcomponentDef> getSubcomponentsDefs(){
		ArrayList<MHL7SubcomponentDef> subcomponentDefs = new ArrayList<MHL7SubcomponentDef>();
		
		String sql = "SELECT * FROM HL7_SubcomponentDef FD WHERE FD.HL7_ComponentDef_Id = ? ";
		PreparedStatement pstmt = null;
		try{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, this.getHL7_ComponentDef_ID());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				subcomponentDefs.add(new MHL7SubcomponentDef(getCtx(), rs.getInt(1), null));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getSubcomponentDefs", e);
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
		
		return subcomponentDefs;
	}
	
	public static List<MHL7ComponentDef> getRegByDef(Properties ctx, int HL7_FieldDef_ID, String trxName) {
		ArrayList<MHL7ComponentDef> list = new ArrayList<MHL7ComponentDef>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM HL7_COMPONENTDEF WHERE ISACTIVE = 'Y' AND HL7_FIELDDEF_ID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_FieldDef_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MHL7ComponentDef(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCompByDef", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}

	public static Class<X_HL7_Component> mapClass = X_HL7_Component.class;
	public static String mapIDColumn = COLUMNNAME_HL7_Component_ID;
	public static Class<MHL7SubcomponentDef> childClass = MHL7SubcomponentDef.class;
	public static String parentIDColumn = COLUMNNAME_HL7_FieldDef_ID;
}
