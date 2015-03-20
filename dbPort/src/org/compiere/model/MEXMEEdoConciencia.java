package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MEXMEEdoConciencia extends X_EXME_EdoConciencia {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private static CLogger		s_log				= CLogger.getCLogger(MEXMEEdoConciencia.class);

	public MEXMEEdoConciencia(Properties ctx, int edoConcienciaID, String trxName) {
		super(ctx, edoConcienciaID, trxName);
	}

	public MEXMEEdoConciencia(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

	}

	/**
	 * 
	 * @param ctx
	 * @param valueAsKey
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> get(Properties ctx, boolean valueAsKey, String trxName) {

		final List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		final StringBuffer sql = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append(" SELECT EXME_EdoConciencia_ID, Valor, Name||' ('||Valor||')' as FullName ");
			sql.append(" FROM EXME_EdoConciencia ");
			sql.append(" WHERE IsActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append("ORDER BY Valor");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final int key = valueAsKey ? rs.getInt("Valor") : rs.getInt("EXME_EdoConciencia_ID");
				final KeyNamePair pair = new KeyNamePair(key, rs.getString("FullName"));
				retValue.add(pair);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return retValue;
	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEdoConciencia> get(Properties ctx, String trxName) {
		List<MEXMEEdoConciencia> retValue = new ArrayList<MEXMEEdoConciencia>();
		try {
			retValue = new Query(ctx, Table_Name, null, trxName)//
					.addAccessLevelSQL(true)//
					.setOnlyActiveRecords(true)//
					.setOrderBy(" EXME_EdoConciencia.Valor ")//
					.list();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.toString());
		}
		return retValue;
	}

	public String getNameStr() {
		final StringBuilder str = new StringBuilder();
		str.append(getName());
		str.append(" (");
		str.append(getValor());
		str.append(")");
		return str.toString();
	}

}
