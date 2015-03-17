package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.KeyNamePair;

public class MEXMEAccompaniedBy extends X_EXME_AccompaniedBy {

	private static final long serialVersionUID = 1L;
	private static final CLogger s_log = CLogger.getCLogger(MEXMEAccompaniedBy.class);

	public MEXMEAccompaniedBy(Properties ctx, int EXME_AccompaniedBy_ID, String trxName) {
		super(ctx, EXME_AccompaniedBy_ID, trxName);
	}

	public MEXMEAccompaniedBy(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static List<KeyNamePair> getAllAccompaniedBy(final Properties ctx) {
		 List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		try {
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT EXME_ACCOMPANIEDBY_ID, NAME ");
			sql.append(" FROM exme_accompaniedby ");
			sql.append(" WHERE IsActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			retValue =  Query.getListKN(sql.toString(), null);
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
		}
		return retValue;
	}

}
