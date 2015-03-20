package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.vo.ClaimLogVO;

public class MEXMEClaimLog extends X_EXME_ClaimLog {
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEClaimLog.class);

	public MEXMEClaimLog(Properties ctx, int EXME_ClaimLog_ID, String trxName) {
		super(ctx, EXME_ClaimLog_ID, trxName);
	}
	
	public MEXMEClaimLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get a list of logs by HL7 Dashboard ID
	 * @param ctx
	 * @param HL7_Dashboard_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static ArrayList<MEXMEClaimLog> getByHL7Dashboard(Properties ctx, int HL7_Dashboard_ID, String whereClause, String trxName) {
		ArrayList<MEXMEClaimLog> list = new ArrayList<MEXMEClaimLog>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_CLAIMLOG WHERE IsActive = 'Y' AND HL7_DASHBOARD_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(whereClause == null ? "" : whereClause);

		sql.append(" ORDER BY EXME_ClaimLog.EXME_ClaimLog_ID DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, HL7_Dashboard_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEClaimLog reg = new MEXMEClaimLog(ctx, rs, trxName);
				list.add(reg);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getByHL7Dashboard: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	/**
	 * Get a list of logs by ctapac
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	private static ArrayList<MEXMEClaimLog> getsByCtaPac(Properties ctx, int EXME_CtaPac_ID, 
			int EXME_CtaPacExt_ID,String trxName) {
		ArrayList<MEXMEClaimLog> list = new ArrayList<MEXMEClaimLog>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT * FROM EXME_CLAIMLOG WHERE IsActive = 'Y' ");
		if (EXME_CtaPac_ID >0){
			sql.append(" AND EXME_CTAPAC_ID = ? ");
		}
		if (EXME_CtaPacExt_ID >0){
			sql.append(" AND EXME_CTAPACEXT_ID = ? ");
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		sql.append(" ORDER BY EXME_ClaimLog.C_BPartner_ID, EXME_ClaimLog.Date_Submitted ASC, EXME_ClaimLog.EXME_ClaimLog_ID DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 1;
			if (EXME_CtaPac_ID >0){
				pstmt.setInt(i++, EXME_CtaPac_ID);
			}
			if (EXME_CtaPacExt_ID >0){
				pstmt.setInt(i++, EXME_CtaPacExt_ID);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEClaimLog reg = new MEXMEClaimLog(ctx, rs, trxName);
				list.add(reg);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getByCtaPac: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	/**
	 * Get a log by ctapac
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static MEXMEClaimLog getByCtaPac(Properties ctx, int EXME_CtaPac_ID, String whereClause, String trxName) {
		MEXMEClaimLog reg = null;
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_CLAIMLOG WHERE IsActive = 'Y' AND EXME_CTAPAC_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(whereClause == null ? "" : whereClause);

		sql.append(" ORDER BY EXME_ClaimLog.C_BPartner_ID, EXME_ClaimLog.Date_Submitted ASC, EXME_ClaimLog.EXME_ClaimLog_ID DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				 reg = new MEXMEClaimLog(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getByCtaPac: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return reg;
	}
	
	/**
	 * Get a log by a list of parameters
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static MEXMEClaimLog get(Properties ctx, String whereClause, String trxName, Object...params) {
		MEXMEClaimLog reg = null;
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_CLAIMLOG WHERE IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(whereClause == null ? "" : whereClause);

		sql.append(" ORDER BY EXME_ClaimLog.C_BPartner_ID, EXME_ClaimLog.Date_Submitted ASC, EXME_ClaimLog.EXME_ClaimLog_ID DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				 reg = new MEXMEClaimLog(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return reg;
	}
	
	/**
	 * Get a list of logs by ctapac and grouped by Insurance
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static ArrayList<ClaimLogVO> getByCtaPac_Grouped(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		ArrayList<ClaimLogVO> list = new ArrayList<ClaimLogVO>();
		ArrayList<MEXMEClaimLog> listLogs = getsByCtaPac(ctx, EXME_CtaPac_ID, 0, trxName);
		
		for (MEXMEClaimLog log : listLogs) {
			if (log.getC_BPartner_ID() > 0) {
				ClaimLogVO regFind = null;
				for (ClaimLogVO logvo : list) {
					if (logvo.getBpartner().getC_BPartner_ID() == log.getC_BPartner_ID()) {
						regFind = logvo;
					}
				}
				
				if (regFind == null) {
					regFind = new ClaimLogVO(ctx, log);
					list.add(regFind);
				} else {
					int indice = list.indexOf(regFind);
					regFind.setDate_Resubmitted(log.getDate_Submitted());
					regFind.setStatus(log.getStatus());
					if (X_HL7_MessageConf.CONFTYPE_ProfessionalClaim.equals(log.getConfType())) {
						regFind.setEnableProfessional(true);
					}
					if (X_HL7_MessageConf.CONFTYPE_InstitutionalClaim.equals(log.getConfType())) {
						regFind.setEnableInstitutional(true);
					}
					list.set(indice, regFind);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Get a list of logs by ctapac
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static ArrayList<ClaimLogVO> getByCtaPac_Individual(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		ArrayList<ClaimLogVO> list = new ArrayList<ClaimLogVO>();
		ArrayList<MEXMEClaimLog> listLogs = getsByCtaPac(ctx, EXME_CtaPac_ID, 0, trxName);
		
		for (MEXMEClaimLog log : listLogs) {
			if (log.getC_BPartner_ID() > 0) {
				ClaimLogVO regFind = null;
				regFind = new ClaimLogVO(ctx, log);
				list.add(regFind);
			}
		}
		
		return list;
	}
	
	/**
	 * Get a list of logs by ctapac
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static ArrayList<ClaimLogVO> getByCtaPacExt(Properties ctx, int EXME_CtaPacExt_ID, String trxName) {
		ArrayList<ClaimLogVO> list = new ArrayList<ClaimLogVO>();
		ArrayList<MEXMEClaimLog> listLogs = getsByCtaPac(ctx, 0, EXME_CtaPacExt_ID, trxName);
		
		for (MEXMEClaimLog log : listLogs) {
			if (log.getC_BPartner_ID() > 0) {
				ClaimLogVO regFind = null;
				regFind = new ClaimLogVO(ctx, log);
				list.add(regFind);
			}
		}
		
		return list;
	}
	
	
}
