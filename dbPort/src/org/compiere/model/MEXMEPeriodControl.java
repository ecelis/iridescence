package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEPeriodControl extends MPeriodControl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEXMEPeriodControl(Properties ctx, int C_PeriodControl_ID, String trxName) {
		super(ctx, C_PeriodControl_ID, trxName);
	}

	public MEXMEPeriodControl(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEPeriodControl(MPeriod period, String DocBaseType) {
		super(period, DocBaseType);
	}

	public MEXMEPeriodControl(Properties ctx, int AD_Client_ID, int C_Period_ID, String DocBaseType, String trxName) {
		super(ctx, AD_Client_ID, C_Period_ID, DocBaseType, trxName);
	}
	
	/**
	 * Obtiene el estatus del per�odo contable.
	 * @param C_Period_ID
	 * @param docBaseType
	 * @return status
	 */
	public String getPeriodStatus(Properties ctx, int C_Period_ID, String docBaseType) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT periodStatus FROM C_PeriodControl ").append(" WHERE C_Period_ID = ? ")
				.append(" AND docBaseType = ?").append(" AND IsActive = 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_PeriodControl"));

		return DB.getSQLValueString(get_TrxName(), sql.toString(), C_Period_ID, docBaseType);
	}

}
