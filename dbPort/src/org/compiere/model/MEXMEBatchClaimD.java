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

public class MEXMEBatchClaimD extends X_EXME_BatchClaimD {
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEBatchClaimD.class);

	public MEXMEBatchClaimD(Properties ctx, int EXME_ClaimCodes_ID, String trxName) {
		super(ctx, EXME_ClaimCodes_ID, trxName);
	}
	
	public MEXMEBatchClaimD(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public String getTextStatus() {
		String text = "";
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT DESCRIPTION FROM HL7_RESPONSE WHERE VALUE = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), null);
        	pstmt.setString(1, "997_".concat(getStatus()));
        	rs = pstmt.executeQuery();
        	if(rs.next()){
        		text = rs.getString("DESCRIPTION");
        	}

    	} catch (Exception e){
    		s_log.log(Level.SEVERE, "Receive997 ", e);
    	} finally{
    		DB.close(rs, pstmt);
    	}
    	
    	return text;
	}
	
	public static List<MEXMEBatchClaimD> getListDetail(Properties ctx, String where, String trxName, Object...params) {
		ArrayList<MEXMEBatchClaimD> list = new ArrayList<MEXMEBatchClaimD>();
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_BATCHCLAIMD D WHERE D.ISACTIVE = 'Y' ");
		if(where != null){
			sql.append(where);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "D"));
		sql.append(" ORDER BY D.CREATED desc ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEBatchClaimD detail = new MEXMEBatchClaimD(ctx, rs, trxName);
				list.add(detail);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public static MEXMEBatchClaimD[] gets(Properties ctx, int EXME_BatchClaimH_ID, String trxName) {
		ArrayList<MEXMEBatchClaimD> list = new ArrayList<MEXMEBatchClaimD>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_BATCHCLAIMD WHERE IsActive = 'Y' AND EXME_BATCHCLAIMH_ID = ? ");
		//sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_BatchClaimD.Created DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_BatchClaimH_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEBatchClaimD batchD = new MEXMEBatchClaimD(ctx, rs, trxName);
				list.add(batchD);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "gets: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		MEXMEBatchClaimD[] batchs = new MEXMEBatchClaimD[list.size()];
		list.toArray(batchs);
		
		return batchs;
	}
	
	public static MEXMECtaPacExt[] getsCtaPacExt(Properties ctx, int EXME_BatchClaimH_ID, String trxName) {
		ArrayList<MEXMECtaPacExt> list = new ArrayList<MEXMECtaPacExt>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_CtaPacExt_ID FROM EXME_BATCHCLAIMD WHERE IsActive = 'Y' AND EXME_BATCHCLAIMH_ID = ? ");
		//sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_BatchClaimD.Created DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_BatchClaimH_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacExt extension = new MEXMECtaPacExt(ctx, rs.getInt("EXME_CtaPacExt_ID"), trxName);
				list.add(extension);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getsCtaPac: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		MEXMECtaPacExt[] batchs = new MEXMECtaPacExt[list.size()];
		list.toArray(batchs);
		
		return batchs;
	}
}
