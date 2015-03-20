package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.KeyNamePair;

public class MEXMEDischarged_Via extends X_EXME_Discharged_Via {

	private static final long serialVersionUID = 1L;
	private static final CLogger s_log = CLogger.getCLogger(MEXMETransportMode.class);

	public MEXMEDischarged_Via(Properties ctx, int EXME_Discharged_Via_ID, String trxName) {
		super(ctx, EXME_Discharged_Via_ID, trxName);
	}

	public MEXMEDischarged_Via(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<KeyNamePair> getAllDischarged_Via(final Properties ctx) {
		 List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		try {
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT EXME_DISCHARGED_VIA.EXME_DISCHARGED_VIA_ID, EXME_DISCHARGED_VIA.NAME ");
			sql.append(" FROM EXME_DISCHARGED_VIA EXME_DISCHARGED_VIA ");
			sql.append(" WHERE EXME_DISCHARGED_VIA.IsActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			retValue =  Query.getListKN(sql.toString(), null);
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
		}
		return retValue;
	}
}
