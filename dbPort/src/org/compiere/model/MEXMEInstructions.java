package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;


public class MEXMEInstructions extends X_EXME_Instructions {
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger (MEXMEInstructions.class);

	/**
	 * @param ctx
	 * @param EXME_Instructions_ID
	 * @param trxName
	 */
	public MEXMEInstructions(Properties ctx, int EXME_Instructions_ID,
			String trxName) {
		super(ctx, EXME_Instructions_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEInstructions(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static List<MEXMEInstructions> getInstructions(Properties ctx, boolean access, String trxName){
		List<MEXMEInstructions> instructions = new ArrayList<MEXMEInstructions>();
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
		sql.append(" SELECT EXME_INSTRUCTIONS.* FROM EXME_INSTRUCTIONS ");
		if (access == true) {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", Table_Name));
		}
		sql.append(" ORDER BY VALUE ");
		
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEInstructions inst = new MEXMEInstructions(ctx, rs, trxName);
				instructions.add(inst);
			} 
			
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}        
        return instructions;
	}
	
	/**
	 * @param ctx
	 * @param citaMedicaId
	 * @param trxName
	 * @return Instructions temporales de la cita medica
	 */
	public static List<MEXMEInstructions> getInstructions(Properties ctx, int citaMedicaId, String trxName) {
		List<MEXMEInstructions> instructions = new ArrayList<MEXMEInstructions>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		sql.append("select inst.* from exme_citamedicadet cm ")
		.append(" left join exme_instructions inst on (cm.exme_instructions_id = inst.exme_instructions_id) ")
		.append(" WHERE cm.exme_citamedica_id = ? and cm.isactive = 'Y' and cm.isinstruction = 'Y' and inst.isactive = 'Y' ");
		try {
			pstm = DB.prepareStatement(sql.toString(), trxName);
			pstm.setInt(1, citaMedicaId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				instructions.add(new MEXMEInstructions(ctx, rs, trxName));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		
		return instructions; 
	}

}
