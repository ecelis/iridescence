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
import org.compiere.util.Env;

@SuppressWarnings("serial")
public class MEXMETTProvision extends X_EXME_TT_Provision{

	private static CLogger		s_log = CLogger.getCLogger (MEXMETTProvision.class);
	
	public MEXMETTProvision(Properties ctx, int EXME_TT_Provision_ID, String trxName) {
		super(ctx, EXME_TT_Provision_ID, trxName);
	}
	
	public MEXMETTProvision(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static void delete(Properties ctx, int AD_Session_ID, String trxName){
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
		sql.append("SELECT EXME_TT_Provision_ID FROM EXME_TT_Provision WHERE AD_SESSION_ID = ? ");

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {    		
    		pstmt = DB.prepareStatement(sql.toString(), trxName);
    		pstmt.setInt(1, AD_Session_ID);
    		rs = pstmt.executeQuery();

    		while (rs.next()) {
    			MEXMETTProvision obj = new MEXMETTProvision(ctx, rs, null);
    			if (!obj.delete(true, trxName))
    				s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
    			obj = null;    			
    		}
   
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	} finally {
    		DB.close(rs, pstmt);
    	}
		
	}

	/**
	 * Obtenemos el listado de Ctapac
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param ctaPacInicial
	 * @param ctaPacFinal
	 * @return
	 */
	public static List<MJournalLine> getLstCtaPac(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder(100);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MJournalLine> retValue = new ArrayList<MJournalLine>();
		
		try {
			sql.append("SELECT credito, debito, c_currency_id, cta_cont_id ")
				.append(" FROM EXME_TT_Provision ")
				.append(" WHERE IsActive = 'Y' ")
				.append(" AND AD_Session_ID = ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_TT_Provision"));
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Session_ID"));
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				MJournalLine journalLine = new MJournalLine(ctx, 0, null);
				journalLine.setAmtSourceCr(rs.getBigDecimal(1));
				journalLine.setAmtSourceDr(rs.getBigDecimal(2));
				journalLine.setC_Currency_ID(rs.getInt(3));
				journalLine.setC_ValidCombination_ID(rs.getInt(4));
				retValue.add(journalLine);
			}
			
		} catch (Exception e) {
			retValue = null;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
			sql = null;
		}

		return retValue;
	}
}

