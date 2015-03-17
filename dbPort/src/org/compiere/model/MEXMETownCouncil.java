package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MEXMETownCouncil extends X_EXME_TownCouncil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMETownCouncil.class);
	
	public MEXMETownCouncil(Properties ctx, int EXME_TownCouncil_ID, String trxName) {
		super(ctx, EXME_TownCouncil_ID, trxName);
	}

	public MEXMETownCouncil(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static int getDefaultTownCouncil(Properties ctx, int region_ID) {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT EXME_TownCouncil_ID from EXME_TownCouncil where isDefault = 'Y' ")
			.append(" and isActive = 'Y' and C_Region_ID = ? ");
		
		if (DB.isOracle()) {
			sql.append(" and rownum=1");
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
		}
		
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1,region_ID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("EXME_TownCouncil_ID");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getDefaultTownCouncil - sql: " + sql, e);
			return 0;
		} finally {
			DB.close(rs, pstmt);
		}
		
		return 0;
	}
	
	public static MTownCouncil[] getActiveTownCouncils(Properties ctx, int region_ID, String trxName) {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		ArrayList<MTownCouncil> list = new ArrayList<MTownCouncil>();
		MTownCouncil[] retVal = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT * FROM EXME_TownCouncil where isActive = 'Y' and C_Region_ID = ? ");			
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		sql.append(" order by isDefault desc, description");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1,region_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MTownCouncil(ctx, rs, trxName));
			}
			retVal = new MTownCouncil[list.size()];
			list.toArray(retVal);
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getActiveTownCounils", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
		
	}
	public static List<LabelValueBean> getAllActiveByRegion(Properties ctx, int region_ID, boolean empty) {
		
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append("SELECT Name, EXME_TOWNCOUNCIL_ID FROM EXME_TownCouncil where isActive = 'Y' and C_Region_ID = ? ");			
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

//		sql.append(" order by isDefault desc, description");
		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		if (empty) {
			lista.add(new LabelValueBean("", ""));
		}
		final MTownCouncil[] councils = MTownCouncil.getTownCouncil(ctx, region_ID);
		for(MTownCouncil council : councils){
			lista.add(new LabelValueBean(council.getName(), String.valueOf(council.getEXME_TownCouncil_ID())));
		}
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1,region_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new LabelValueBean(rs.getString(COLUMNNAME_Name), rs.getString(COLUMNNAME_EXME_TownCouncil_ID)));
//			}			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getActiveTownCounils", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return lista;
	}
	
	public static List<KeyNamePair> getList(Properties ctx, int region_ID, boolean empty) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_TownCouncil_ID, Name FROM EXME_TownCouncil where isActive = 'Y' and C_Region_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" order by isDefault desc, description");
		
		List<KeyNamePair> list = Query.getListKN(sql.toString(), null, region_ID);
		if (empty) {
			list.add(0, new KeyNamePair(0, " "));
		}
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param region_ID
	 * @param value
	 * @param trxName
	 * @return
	 */
	public static int getTownByNameAndRegion(Properties ctx, int region_ID, String value, String trxName){
		String query = "SELECT EXME_TownCouncil_ID FROM EXME_TownCouncil WHERE isActive = 'Y' AND C_Region_ID = ? AND UPPER(name) like UPPER(?)";
		String sql = MEXMELookupInfo.addAccessLevelSQL(ctx, query, Table_Name);
		return DB.getSQLValue(trxName, sql.toString(), region_ID, value);
	}

}
