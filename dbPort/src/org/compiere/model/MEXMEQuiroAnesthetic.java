package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEQuiroAnesthetic extends X_EXME_QuiroAnesthetic{

	private static final long serialVersionUID = 2075941931056683909L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEQuiroAnesthetic.class);

	public MEXMEQuiroAnesthetic(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEQuiroAnesthetic(Properties ctx, int EXME_QuiroAnesthetic_ID, String trxName) {
		super(ctx, EXME_QuiroAnesthetic_ID, trxName);
	}
	
	/**
	 * Regresa los procedimientos anestesicos dependiendo de 
	 * una programacion de quirofano
	 * 
	 * @param EXME_ProgQuiro_ID
	 * 					 EL Id de la Programacion de Quirofano
	 * **/
	public static List<MEXMEQuiroAnesthetic> getProcAnesthetic(Properties ctx, int EXME_ProgQuiro_ID, String trxName){
		List<MEXMEQuiroAnesthetic> lista = new ArrayList<MEXMEQuiroAnesthetic>();
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT * FROM EXME_QuiroAnesthetic WHERE EXME_ProgQuiro_ID = ?");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_ProgQuiro_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lista.add(new MEXMEQuiroAnesthetic(ctx, rs, trxName));
			}
			
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lista;
	}

}
