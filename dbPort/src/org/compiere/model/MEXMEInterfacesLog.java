package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEInterfacesLog extends X_EXME_InterfacesLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEXMEInterfacesLog(Properties ctx, int EXME_InterfacesLog_ID, String trxName) {
		super(ctx, EXME_InterfacesLog_ID, trxName);
	}

	public MEXMEInterfacesLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


}
