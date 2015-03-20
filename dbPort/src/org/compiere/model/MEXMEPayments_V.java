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

public class MEXMEPayments_V extends X_EXME_Payments_V{

	private static final long serialVersionUID = -3811589211609982241L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEPayments_V.class);
	
	public MEXMEPayments_V(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MEXMEPayments_V(Properties ctx, int EXME_Payments_V_ID, String trxName) {
		super(ctx, EXME_Payments_V_ID, trxName);
	}
	
	public static List<MEXMEPayments_V> getAll(Properties ctx, String trxName) {
		ArrayList<MEXMEPayments_V> ret = new ArrayList<MEXMEPayments_V>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			sql.append(" SELECT * FROM  EXME_Payments_V WHERE IsActive = 'Y'");			
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY Fecha DESC ");

			psmt = DB.prepareStatement(sql.toString(), trxName);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ret.add(new MEXMEPayments_V(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return ret;
	}

}
