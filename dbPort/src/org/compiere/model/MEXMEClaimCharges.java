package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEClaimCharges extends X_EXME_ClaimCharges{

	/** serialVersionUID */
	private static final long serialVersionUID = 4887038683222189027L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMEClaimCharges.class);
    
    /**
     * Constructor
     * @param ctx
     * @param EXME_ClaimCharges_ID
     * @param trxName
     */
	public MEXMEClaimCharges(Properties ctx, int EXME_ClaimCharges_ID,
			String trxName) {
		super(ctx, EXME_ClaimCharges_ID, trxName);
	}

	public MEXMEClaimCharges(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Columnas de busqueda
	 */
	public static final String[] columns = { MEXMEClaimCharges.COLUMNNAME_Code, MEXMEClaimCharges.COLUMNNAME_RevenueCode,
			MEXMEClaimCharges.COLUMNNAME_DateDelivered, MEXMEClaimCharges.COLUMNNAME_Description};
	
	/**
	 * detail Charge
	 * 
	 * @param EXME_CtaPac_ID
	 * @param code
	 * @param dateDelivered
	 * @return
	 * 
	 */
	public static int detailCharge(int EXME_CtaPac_ID, String code,
			Timestamp dateDelivered) {

		int lst = -1;
		if (StringUtils.isNotBlank(code) && dateDelivered != null) {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ")
					.append("   CASE WHEN Code IS NULL THEN RevenueCode  ELSE code END AS RevCode ")
					.append(" , EXME_CLAIMCHARGES_ID")
					.append(" , AD_CLIENT_ID        ")
					.append(" , AD_ORG_ID           ")
					.append(" , ISACTIVE            ")
					.append(" , CREATED             ")
					.append(" , CREATEDBY           ")
					.append(" , UPDATED             ")
					.append(" , UPDATEDBY           ")
					.append(" , REVENUECODE         ")
					.append(" , DESCRIPTION         ")
					.append(" , CODE                ")
					.append(" , DATEDELIVERED       ")
					.append(" , QTY                 ")
					.append(" , CHARGEAMT           ")
					.append(" , NON_COVERED_CHARGES ")
					.append(" , EXME_CTAPAC_ID      ")
					.append(" , LISTDETAILS         ")
					.append(" FROM EXME_ClaimCharges")
					.append(" WHERE IsActive = 'Y'  ")
					.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(),
							" ", "EXME_ClaimCharges"))
					.append(" AND EXME_CtaPac_ID = ?")
					.append(" AND DATEDELIVERED = ? AND CODE = ? ");
			sql.append(" ORDER BY Code, RevenueCode, DATEDELIVERED ");
			PreparedStatement pstmt = null;
			ResultSet rSet = null;

			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, EXME_CtaPac_ID);
				pstmt.setTimestamp(2, dateDelivered);
				pstmt.setString(3, code);

				rSet = pstmt.executeQuery();

				if (rSet.next()) {
					lst = rSet.getInt(2);
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, e.getMessage(), e);
			} finally {
				DB.close(rSet, pstmt);
			}

		}
		return lst;

	}
}
