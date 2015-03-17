package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEInterventionDiag extends X_EXME_InterventionDiag {
	private static CLogger log = CLogger.getCLogger(MEXMEInterventionDiag.class);
	private static final long serialVersionUID = 1L;
	public MEXMEInterventionDiag(Properties ctx, int EXME_InterventionDiag_ID, String trxName) {
		super(ctx, EXME_InterventionDiag_ID, trxName);
	}

	public MEXMEInterventionDiag(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static List<MDiagnostico> getDiagByIntervention(final Properties ctx, final int exmeIntervencionID, String trxName){

		final List<MDiagnostico> list = new ArrayList<MDiagnostico>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT diag.* FROM EXME_Diagnostico  diag")
				.append(" INNER JOIN EXME_InterventionDiag inter ON (inter.EXME_Diagnostico_ID = diag.EXME_Diagnostico_ID) ")
				.append(" WHERE inter.EXME_Intervencion_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			pstmt.setInt(1, exmeIntervencionID);
			
			result = pstmt.executeQuery();
			while (result.next()) {
				list.add(new MDiagnostico(ctx, result, trxName));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return list;
	}
	
	public static boolean isDiagRelatedToHCPCS(final Properties ctx, final int ctaPacDetID, final int diagnosticoID,  String trxName){

		boolean retVal = Boolean.FALSE;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			sql.append("SELECT * ")
				.append(" FROM EXME_CtaPacDet cpd ")
				.append(" INNER JOIN M_Product mp ON mp.M_Product_ID = cpd.M_Product_ID ")
				.append(" INNER JOIN EXME_InterventionDiag inter ON inter.EXME_Intervencion_ID = mp.EXME_Intervencion_ID ")
				.append(" WHERE cpd.EXME_CtaPacDet_ID = ? ")
				.append(" AND inter.EXME_Diagnostico_ID = ? ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			pstmt.setInt(1, ctaPacDetID);
			pstmt.setInt(2, diagnosticoID);
			
			result = pstmt.executeQuery();
			while (result.next()) {
				retVal = Boolean.TRUE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return retVal;
	}
	
}
