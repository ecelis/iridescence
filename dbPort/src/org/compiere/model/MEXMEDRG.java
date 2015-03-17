package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEDRG extends X_EXME_DRG {
	

	/**
	 * @author gvaldez
	 */

	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEDRG.class);
	private static final long serialVersionUID = 5389162537907899673L;



	public MEXMEDRG(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	public MEXMEDRG(Properties ctx, int id, String trxName) {
		super(ctx, id, trxName);
	}
	
	public static MEXMEDRG get(Properties ctx, String trxName, String whereClause, StringBuilder joins,
			Object... params) {
			return new Query(ctx, Table_Name, whereClause, trxName)//
				.setParameters(params)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" created Desc")
				.first();
		}	
	

	public static MEXMEDRG getFromValue(Properties ctx, String trxName, Object... params) {
		
		return get(ctx, trxName, " "+MEXMEDRG.COLUMNNAME_Value+"=? AND "+MEXMEDRG.COLUMNNAME_Version+"=? ", null, params);
		
	}


}
