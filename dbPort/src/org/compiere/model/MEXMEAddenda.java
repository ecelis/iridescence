package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEAddenda extends X_EXME_Addenda {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7929272324067101073L;
	/**	Static Logger				*/
	private static CLogger log = CLogger.getCLogger (MEXMEAddenda.class);
	
	public MEXMEAddenda(Properties ctx, int EXME_Addenda_ID, String trxName) {
		super(ctx, EXME_Addenda_ID, trxName);
	}

	public MEXMEAddenda(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static ArrayList<MEXMEAddenda> getAllAddendaByProvider(Properties ctx, int C_BPartner_ID, String trxName) {
		ArrayList<MEXMEAddenda> list = new ArrayList<MEXMEAddenda>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_ADDENDA A WHERE ISACTIVE = 'Y' ");
		if (C_BPartner_ID > 0) {
			sql.append("AND C_BPARTNER_ID = ? ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEAddenda.Table_Name, "A"));
		sql.append(" ORDER BY CREATED DESC");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (C_BPartner_ID > 0) {
				pstmt.setInt(1, C_BPartner_ID);
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEAddenda(ctx, rs, trxName));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMEAddenda.getAddendaByProvider",e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	public static MEXMEAddenda getAddendaByProvider(Properties ctx, int C_BPartner_ID, String trxName) {
		MEXMEAddenda addenda = null;
		ArrayList<MEXMEAddenda> list = getAllAddendaByProvider(ctx, C_BPartner_ID, trxName);
		if (list.size() > 0) {
			addenda = list.get(0);
		}
		return addenda;
	}
	
	public boolean deleteAll(boolean force, String trxName) {
		boolean success = true;
		try {
			for (MEXMEAddendaPara param : MEXMEAddendaPara.getParamByAddenda(getCtx(), getEXME_Addenda_ID(), trxName)) {
				param.delete(force, trxName);
			}
			delete(force, trxName);
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMEAddenda.deleteAll",e);
			success = false;
		}
		return success;
	}
}
