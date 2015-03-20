package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPHRVacunaPacDet extends X_PHR_VacunaPacDet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6955815592813767612L;
	
	public MPHRVacunaPacDet(Properties ctx, int PHR_VacunaPacDet_ID,
			String trxName) {
		super(ctx, PHR_VacunaPacDet_ID, trxName);
	}
	public MPHRVacunaPacDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}



}
