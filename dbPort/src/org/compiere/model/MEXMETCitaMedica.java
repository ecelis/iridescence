package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;
/**
 * 
 * @author Lizeth de la Garza
 *
 */
public class MEXMETCitaMedica extends X_EXME_T_CitaMedica {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static CLogger s_log = CLogger.getCLogger(MEXMETCitaMedica.class);

	public MEXMETCitaMedica(Properties ctx, int EXME_T_CitaMedica_ID,	String trxName) {
		super(ctx, EXME_T_CitaMedica_ID, trxName);
	}
	
	public MEXMETCitaMedica(Properties ctx, ResultSet rs,	String trxName) {
		super(ctx, rs, trxName);
	}

	
}
