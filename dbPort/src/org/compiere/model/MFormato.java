package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MFormato extends X_EXME_Formato {

    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
     * @param ctx
     * @param ID
     * @param trxName
     */
    public MFormato(Properties ctx, int ID, String trxName) {
        super(ctx, ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MFormato(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

}
