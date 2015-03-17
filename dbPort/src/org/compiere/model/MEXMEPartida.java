package org.compiere.model;

import java.util.Properties;

import org.compiere.util.DB;

public class MEXMEPartida extends X_EXME_Partida {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public MEXMEPartida(Properties ctx, int EXME_Partida_ID, String trxName) {
		super(ctx, EXME_Partida_ID, trxName);
	}
	
	public static String getPartida(Properties ctx, int exmeGlBudgetPAID) throws Exception{
		
		String sql = " SELECT value||' - '||name  "
			+ " FROM EXME_Partida "
			+ " Where EXME_Partida_ID = (SELECT EXME_Partida_ID " 
									 + " FROM EXME_GL_BudgetPA"
									 + " Where EXME_GL_BudgetPA_ID = ?)" +
		 MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_Partida.Table_Name);
		
		return DB.getSQLValueString(null, sql.toString(), exmeGlBudgetPAID);
	}
	
    
	public static String getOrg(Properties ctx, int exmeGlBudgetPAID) throws Exception{

		String sql = " SELECT AO.Name" +
				 " FROM EXME_GL_BudgetPa " +
				 " INNER JOIN AD_Org AO ON AO.AD_Org_ID = EXME_GL_BudgetPa.AD_OrgTrx_ID" +
				 " WHERE EXME_GL_BudgetPa.EXME_GL_BudgetPA_ID = ?" +
				 MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_GL_BudgetPa.Table_Name);
		
		return DB.getSQLValueString(null, sql.toString(), exmeGlBudgetPAID);
	}
	
}
