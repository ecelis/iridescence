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

public class MEXMEHCPCS extends X_EXME_HCPCS{
	
	private static final long serialVersionUID = 6758790130806522394L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEHCPCS.class);
	
	/**
	 * HCPCS
	 * @param ctx
	 * @param EXME_HCPCS_ID
	 * @param trxName
	 */
	public MEXMEHCPCS(final Properties ctx, final int EXME_HCPCS_ID, final String trxName) {
		super(ctx, EXME_HCPCS_ID, trxName);
	}

	/**
	 * getLVB
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getLVB(Properties ctx, String trxName) {
		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT  * FROM ").append(X_EXME_HCPCS.Table_Name).append(" where isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			sql.append(" ORDER BY Value");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new LabelValueBean(rs.getString(COLUMNNAME_Name), rs.getString(COLUMNNAME_EXME_HCPCS_ID)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
}
