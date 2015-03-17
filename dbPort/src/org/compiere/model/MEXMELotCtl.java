package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMELotCtl extends MLotCtl{

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger (MEXMELotCtl.class);
	
	/**
	 * Standard Constructor
	 * @param ctx
	 * @param M_LotCtl_ID
	 * @param trxName
	 */
	public MEXMELotCtl(Properties ctx, int M_LotCtl_ID, String trxName) {
		super(ctx, M_LotCtl_ID, trxName);
	}

	/**
	 * Load Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMELotCtl(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Actualiza Secuencias
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public String updateSeq (String trxName) throws Exception{
		StringBuilder name = new StringBuilder();
		if (getPrefix() != null)
			name.append(getPrefix());
		int no = getCurrentNext();
		name.append(no);
		if (getSuffix() != null)
			name.append(getSuffix());
		no += getIncrementNo();
		setCurrentNext(no);
		save(trxName);
		return name.toString();
	}
	
	public static MEXMELotCtl getFromID(Properties ctx, int M_LotCtl_ID, String trxName){
		MEXMELotCtl control = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append(" SELECT * FROM M_LotCtl WHERE M_LotCtl_ID = ? ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_LotCtl"));

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_LotCtl_ID);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			    control = new MEXMELotCtl(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getFromID", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
		}
		return control;
	}	
}
