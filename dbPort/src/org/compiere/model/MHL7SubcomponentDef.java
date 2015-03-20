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

public class MHL7SubcomponentDef extends X_HL7_SubcomponentDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5107304989983547166L;

	public MHL7SubcomponentDef(Properties ctx, int HL7_SubcomponentDef_ID,
			String trxName) {
		super(ctx, HL7_SubcomponentDef_ID, trxName);
	}

	public MHL7SubcomponentDef(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	protected transient static CLogger	s_log =CLogger.getCLogger(MHL7SubcomponentDef.class);
	
	public static List<MHL7SubcomponentDef> getRegByDef(Properties ctx, int HL7_FieldDef_ID, String trxName) {
		ArrayList<MHL7SubcomponentDef> list = new ArrayList<MHL7SubcomponentDef>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM HL7_SUBCOMPONENTDEF WHERE ISACTIVE = 'Y' AND HL7_COMPONENTDEF_ID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_FieldDef_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MHL7SubcomponentDef(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getSubcompByDef", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	public static Class<X_HL7_Subcomponent> mapClass = X_HL7_Subcomponent.class;
	public static String mapIDColumn = COLUMNNAME_HL7_Subcomponent_ID;
	public static Class<?> childClass = null;
	public static String parentIDColumn = COLUMNNAME_HL7_ComponentDef_ID;
}
