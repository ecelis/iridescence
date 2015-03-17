package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMERoleEstServ extends X_EXME_RoleEstServ {

	private static final long serialVersionUID = 5571922071493183338L;

	public MEXMERoleEstServ(Properties ctx, int EXME_RoleEstServ_ID, String trxName) {
		super(ctx, EXME_RoleEstServ_ID, trxName);
	}

	public MEXMERoleEstServ(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
