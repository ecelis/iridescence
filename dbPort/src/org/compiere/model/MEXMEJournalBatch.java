package org.compiere.model;

import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEJournalBatch extends MJournalBatch {
	
	private static final long serialVersionUID = 1L;

	public MEXMEJournalBatch (Properties ctx, int GL_JournalBatch_ID, String trxName)
	{
		super (ctx, GL_JournalBatch_ID, trxName);
		if (GL_JournalBatch_ID == 0)
		{
			setPostingType (POSTINGTYPE_Actual);
			setDocAction (DOCACTION_Complete);
			setDocStatus (DOCSTATUS_Drafted);
			setTotalCr (Env.ZERO);
			setTotalDr (Env.ZERO);
			setProcessed (false);
			setProcessing (false);
			setIsApproved(false);
		}
	}	//	MJournalBatch

	public static boolean docNoExist(Properties ctx, int AD_Org_ID, int C_Period_ID, String documentNo, String trxName){
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select GL_JournalBatch_ID from GL_JournalBatch ")
		   .append(" where AD_ORG_ID = ?")
		   .append(" and C_PERIOD_ID = ?")
		   .append(" and DocumentNo = ?");
		
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "GL_JournalBatch"));
		
		return DB.getSQLValue(trxName, sql.toString(), AD_Org_ID,C_Period_ID,documentNo) > 0;
	}
}
