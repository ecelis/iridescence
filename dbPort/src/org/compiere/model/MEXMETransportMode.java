package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.KeyNamePair;

public class MEXMETransportMode extends X_EXME_Transport_Mode {

	private static final long serialVersionUID = 1L;
	private static final CLogger s_log = CLogger.getCLogger(MEXMETransportMode.class);

	public MEXMETransportMode(Properties ctx, int EXME_Transport_Mode_ID, String trxName) {
		super(ctx, EXME_Transport_Mode_ID, trxName);
	}

	public MEXMETransportMode(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	

	public static List<KeyNamePair> getAllTransportMode(final Properties ctx) {
		 List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		try {
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT EXME_TRANSPORT_MODE_ID, NAME ");
			sql.append(" FROM exme_transport_mode ");
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
