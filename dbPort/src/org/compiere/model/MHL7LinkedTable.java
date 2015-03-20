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

public class MHL7LinkedTable extends X_HL7_LinkedTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4725707350653878888L;
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MHL7LinkedTable.class);
	
	public MHL7LinkedTable(Properties ctx, int HL7_LinkedTable_ID,
			String trxName) {
		super(ctx, HL7_LinkedTable_ID, trxName);
	}

	public MHL7LinkedTable(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
		/** Prevents saving
		log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
		log.saveError("FillMandatory", Msg.getElement(getCtx(), "PriceEntered"));
		/** Issues message
		log.saveWarning(AD_Message, message);
		log.saveInfo (AD_Message, message);
		**/
		
		
		MTable tableRel = new MTable(p_ctx, getAD_Table_Rel_ID(), null);
		
		if(this.getHL7_LinkedTable2_ID()!=0){
			
			MHL7LinkedTable lt = new MHL7LinkedTable(p_ctx, this.getHL7_LinkedTable2_ID(), null);
			setValue(lt.getValue()+"_"+getAD_Table().getTableName()+"_"+tableRel.getTableName());
			
		}else{
			setValue(getAD_Table().getTableName()+"_"+tableRel.getTableName());
		}
		
		
		

		return true;
	}	//	beforeSave
	
	public static List<MHL7LinkedTable> getLinkTables(Properties ctx, String whereClause, String trxName, Object... params) {
		List<MHL7LinkedTable> list = new ArrayList<MHL7LinkedTable>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM HL7_LINKEDTABLE T WHERE T.ISACTIVE = 'Y' ")
		   .append(whereClause != null && whereClause.length() > 0 ? whereClause : "")
		   .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "T"))
		   .append(" ORDER BY T.HL7_LINKEDTABLE_ID ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			if (params.length > 0) {
				for (Object param : params) {
					DB.setParameter(pstmt, index++, param);
				}
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MHL7LinkedTable(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLinkTables", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}
	
	public static List<Integer> getLinkTablesID(Properties ctx, String whereClause, String trxName, Object... params) {
		List<Integer> list = new ArrayList<Integer>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT T.HL7_LINKEDTABLE_ID FROM HL7_LINKEDTABLE T WHERE T.ISACTIVE = 'Y' ")
		   .append(whereClause != null && whereClause.length() > 0 ? whereClause : "")
		   .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "T"))
		   .append(" ORDER BY T.HL7_LINKEDTABLE_ID ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			if (params.length > 0) {
				for (Object param : params) {
					DB.setParameter(pstmt, index++, param);
				}
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("HL7_LINKEDTABLE_ID"));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLinkTablesID", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}
	
	public static List<MHL7LinkedTable> getLinkChains(Properties ctx, int HL7_LinkedTable_ID, String trxName) {
		List<MHL7LinkedTable> list = new ArrayList<MHL7LinkedTable>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if (DB.isOracle()) {
			sql.append("SELECT * FROM HL7_LINKEDTABLE T WHERE T.HL7_LINKEDTABLE_ID <> ? ")
			   .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "T"))
			   .append("START WITH T.HL7_LINKEDTABLE_ID = ? ")
			   .append("CONNECT BY PRIOR T.HL7_LINKEDTABLE2_ID = T.HL7_LINKEDTABLE_ID ");
		} else if (DB.isPostgreSQL()) {
			sql.append("WITH RECURSIVE LINKCHAIN AS ( ")
			   .append("  SELECT * FROM HL7_LINKEDTABLE TS WHERE TS.HL7_LINKEDTABLE_ID = ? ")
			   .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "TS"))
			   .append("  UNION ALL ")
			   .append("  SELECT TR.* FROM LINKCHAIN L, HL7_LINKEDTABLE TR ")
			   .append("  WHERE TR.HL7_LINKEDTABLE_ID = L.HL7_LINKEDTABLE2_ID ")
			   .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "TR"))
			   .append(") SELECT * FROM LINKCHAIN WHERE HL7_LINKEDTABLE_ID <> ? ")
			   ;
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			pstmt.setInt(index++, HL7_LinkedTable_ID);
			pstmt.setInt(index++, HL7_LinkedTable_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MHL7LinkedTable(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLinkTables", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}
}
