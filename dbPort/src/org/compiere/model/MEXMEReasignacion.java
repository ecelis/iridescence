package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Catalogo de asignación
 * 
 * @author twry
 * 
 */
public class MEXMEReasignacion extends X_EXME_Reasignacion {
	/** serialVersionUID */
	private static final long serialVersionUID = -3122773058296063148L;

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param exmeReasigID
	 * @param trxName
	 */
	public MEXMEReasignacion(final Properties ctx,
			final int exmeReasigID, final String trxName) {
		super(ctx, exmeReasigID, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEReasignacion(final Properties ctx, final ResultSet rs,
			final String trxName) {
		super(ctx, rs, trxName);
	}
}
