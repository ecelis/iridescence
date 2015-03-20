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

/**
 * Elegibilidad
 *
 */
public class MEXMEFViewMUse extends X_EXME_FView_MUse {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -1623960634158215114L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEFViewMUse.class);
	
	/**
	 * Costructor
	 * @param ctx Constexto
	 * @param EXME_BeneficiosH_ID ID del registro de la tabla EXME_BeneficiosH_ID
	 * @param trxName Nombre de la transaccion
	 */
	public MEXMEFViewMUse(Properties ctx, int EXME_FView_MUse_ID, String trxName) {
		super(ctx, EXME_FView_MUse_ID, trxName);
	}
	
	public MEXMEFViewMUse(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}	
	
	public static List<MEXMEFViewMUse> getFoldersByParent(Properties ctx, int EXME_FView_MUse_ID) {
		ArrayList<MEXMEFViewMUse> list = new ArrayList<MEXMEFViewMUse>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_FVIEW_MUSE_ID FROM EXME_FVIEW_MUSE WHERE IsActive = 'Y'");
		if (EXME_FView_MUse_ID > 0) {
			sql.append(" AND PARENT_ID = ? ");
		} else {
			sql.append(" AND PARENT_ID IS NULL ");
		}
		sql.append(" ORDER BY SEQUENCE ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (EXME_FView_MUse_ID > 0) {
				pstmt.setInt(1, EXME_FView_MUse_ID);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEFViewMUse reg = new MEXMEFViewMUse(ctx, rs.getInt(1), null);
				list.add(reg);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getFoldersByParent: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
}
