package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEAddendaPara extends X_EXME_Addenda_Para {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8560115987219319374L;
	/**	Static Logger				*/
	private static CLogger log = CLogger.getCLogger (MEXMEAddendaPara.class);

	public MEXMEAddendaPara(Properties ctx, int EXME_Addenda_Para_ID, String trxName) {
		super(ctx, EXME_Addenda_Para_ID, trxName);
	}

	public MEXMEAddendaPara(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static ArrayList<MEXMEAddendaPara> getParamByAddenda(Properties ctx, int EXME_Addenda_ID, String trxName) {
		ArrayList<MEXMEAddendaPara> list = new ArrayList<MEXMEAddendaPara>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_ADDENDA_PARA WHERE ISACTIVE = 'Y' AND EXME_ADDENDA_ID = ?");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Addenda_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEAddendaPara(ctx, rs, trxName));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMEAddendaPara.getParamByAddenda",e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
}
