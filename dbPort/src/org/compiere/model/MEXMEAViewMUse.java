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
public class MEXMEAViewMUse extends X_EXME_AView_MUse {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -1623960634158215114L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEAViewMUse.class);
	
	/**
	 * Costructor
	 * @param ctx Constexto
	 * @param EXME_BeneficiosH_ID ID del registro de la tabla EXME_BeneficiosH_ID
	 * @param trxName Nombre de la transaccion
	 */
	public MEXMEAViewMUse(Properties ctx, int EXME_AView_MUse_ID, String trxName) {
		super(ctx, EXME_AView_MUse_ID, trxName);
	}
	
	public MEXMEAViewMUse(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}	
	
	public static List<MEXMEAViewMUse> getViewsByFolder(Properties ctx, int EXME_FView_MUse_ID) {
		ArrayList<MEXMEAViewMUse> list = new ArrayList<MEXMEAViewMUse>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT EXME_AVIEW_MUSE_ID FROM EXME_AVIEW_MUSE WHERE IsActive = 'Y' AND EXME_FVIEW_MUSE_ID = ? ORDER BY SEQUENCE ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_FView_MUse_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEAViewMUse reg = new MEXMEAViewMUse(ctx, rs.getInt(1), null);
				list.add(reg);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getViewsByFolder: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
}
