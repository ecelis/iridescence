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
 * 
 * @author Lorena Lama
 *
 */
public class MEXMEProcedPac extends X_EXME_ProcedPac {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2662674469086444525L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEProcedPac.class);

	public MEXMEProcedPac(Properties ctx, int EXME_ProcedPac_ID, String trxName) {
		super(ctx, EXME_ProcedPac_ID, trxName);
	}

	public MEXMEProcedPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}

	public MEXMECtaPac getCtaPac (){
		return new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(),get_TrxName());
	}
	
	

	/**
	 * returns a list of procedures of an specific patient account
	 * @param ctx
	 * @param ctaPacID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEProcedPac> getFromCtaPac(Properties ctx, int ctaPacID, String trxName) {
		
		List<MEXMEProcedPac>  retValue = new ArrayList<MEXMEProcedPac>();
		
		MEXMEProcedPac procPac = null;
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT * FROM ").append(Table_Name).append(" WHERE EXME_CtaPac_ID = ? ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, ctaPacID);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				procPac = new MEXMEProcedPac(ctx, rs, trxName);
				retValue.add(procPac);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}
		return retValue;
	}

	
	
}
