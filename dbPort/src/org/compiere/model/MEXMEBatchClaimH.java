package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEBatchClaimH extends X_EXME_BatchClaimH {
	private static final long	serialVersionUID	= 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEBatchClaimH.class);

	public MEXMEBatchClaimH(Properties ctx, int EXME_ClaimCodes_ID, String trxName) {
		super(ctx, EXME_ClaimCodes_ID, trxName);
	}
	
	public MEXMEBatchClaimH(Properties ctx, ResultSet rs, String trxName) {
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
	
	public static List<MEXMEBatchClaimH> getListBatch(Properties ctx, String where, String trxName, Object...params) {
		ArrayList<MEXMEBatchClaimH> list = new ArrayList<MEXMEBatchClaimH>();
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_BATCHCLAIMH H WHERE H.ISACTIVE = 'Y' ");
		if(where != null){
			sql.append(where);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "H"));
		sql.append(" ORDER BY H.DOCUMENTNO desc ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEBatchClaimH batch = new MEXMEBatchClaimH(ctx, rs, trxName);
				list.add(batch);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public static MEXMEBatchClaimH[] gets(Properties ctx, String buscar, String valor, String whereClause, String trxName) {
		ArrayList<MEXMEBatchClaimH> list = new ArrayList<MEXMEBatchClaimH>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_BATCHCLAIMH.* FROM EXME_BATCHCLAIMH ")
		   .append("LEFT JOIN AD_REF_LIST arl1 ON arl1.AD_REFERENCE_ID = ? AND arl1.VALUE = EXME_BATCHCLAIMH.CONFTYPE  ")
		   .append("LEFT JOIN AD_REF_LIST arl2 ON arl2.AD_REFERENCE_ID = ? AND arl2.VALUE = EXME_BATCHCLAIMH.STATUS  ")
		   .append("WHERE EXME_BATCHCLAIMH.IsActive = 'Y' ");
		if ("EXME_BatchClaimH.Created".equalsIgnoreCase(buscar)) {
			if (DB.isOracle()) {
				sql.append(" AND TRUNC(").append(buscar).append(",'DD') = TO_DATE(?,");
			} else if (DB.isPostgreSQL()) {
				sql.append(" AND DATE_TRUNC('day', ").append(buscar).append(") = TO_DATE(?,");
			}

			if (StringUtils.isBlank(valor) || (valor.length() != 10 && valor.length() != 8)) {
				valor = Constantes.sdfFecha(ctx).format(DB.convert(ctx, new Date()));
				sql.append(DB.TO_STRING(Constantes.sdfFecha(ctx).toPattern()));
			} else {
				sql.append(valor.length() == 8 ? " 'MM/DD/YY' " : " 'MM/DD/YYYY' ");
			}
			sql.append(")  ");
		} else if (buscar != null) {
			valor = valor.replaceAll("//*", "%");
			sql.append(" AND UPPER(").append(buscar).append(") LIKE ? ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(whereClause == null ? "" : whereClause);

		sql.append(" ORDER BY EXME_BatchClaimH.DocumentNo DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, MEXMEBatchClaimH.CONFTYPE_AD_Reference_ID);
			pstmt.setInt(2, STATUS_AD_Reference_ID);
			if (valor != null) {
				pstmt.setString(3, valor.toUpperCase());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEBatchClaimH batch = new MEXMEBatchClaimH(ctx, rs, trxName);
				list.add(batch);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "gets: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		MEXMEBatchClaimH[] batchs = new MEXMEBatchClaimH[list.size()];
		list.toArray(batchs);
		
		return batchs;
	}
	
	public static MEXMEBatchClaimH getLastBatch(Properties ctx, String trxName, String where, Object... parameters) {
		MEXMEBatchClaimH batchH = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_BATCHCLAIMH.* ")
		   .append("FROM   EXME_BATCHCLAIMH INNER JOIN EXME_BATCHCLAIMD ON EXME_BATCHCLAIMD.EXME_BATCHCLAIMH_ID = EXME_BATCHCLAIMH.EXME_BATCHCLAIMH_ID ")
		   .append("WHERE  EXME_BATCHCLAIMH.ISACTIVE = 'Y' ")
		   .append(where != null ? where : " ")
		   .append("ORDER BY EXME_BATCHCLAIMH.CREATED DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), null);
        	DB.setParameters(pstmt, parameters);
			rs = pstmt.executeQuery();
        	if (rs.next()) {
        		batchH = new MEXMEBatchClaimH(ctx, rs, trxName);
        	}

    	} catch (Exception e){
    		s_log.log(Level.SEVERE, "getLastBatch: ", e);
    	} finally{
    		DB.close(rs, pstmt);
    	}
		
		return batchH;
	}
	
	public int getTotalClaims() {
		int total = 0;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT COUNT(*) AS TOTAL ")
		   .append("FROM   EXME_BATCHCLAIMD ")
		   .append("WHERE  EXME_BATCHCLAIMD.ISACTIVE = 'Y' ")
		   .append("AND    EXME_BATCHCLAIMD.EXME_BATCHCLAIMH_ID = ?	");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), null);
        	pstmt.setInt(1, getEXME_BatchClaimH_ID());
			rs = pstmt.executeQuery();
        	if (rs.next()) {
        		total = rs.getInt("TOTAL");
        	}

    	} catch (Exception e){
    		s_log.log(Level.SEVERE, "getTotalClaims: ", e);
    	} finally{
    		DB.close(rs, pstmt);
    	}
		
		return total;
	}
}
