package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

public class MHL7Process extends X_HL7_Process {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2617320373399172833L;
	/** log */
	private static CLogger log = CLogger.getCLogger(MHL7Process.class);

	public MHL7Process(Properties ctx, int HL7_Process_ID, String trxName) {
		super(ctx, HL7_Process_ID, trxName);
	}

	public MHL7Process(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static ArrayList<KeyNamePair> getAllHL7Process(Properties ctx, String trxName) {
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT HL7_PROCESS_ID, (fnc_gettrlreg('AD_Menu', 'Name', P.AD_MENU_ID, ?) || ' - ' || T.TABLENAME) AS NAME ")
			   .append("FROM HL7_PROCESS P INNER JOIN AD_TABLE T ON T.AD_TABLE_ID = P.AD_TABLE_ID");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, Env.getAD_Language(ctx));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt("HL7_Process_ID"), rs.getString("Name")));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getAllHL7Process", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}

}
