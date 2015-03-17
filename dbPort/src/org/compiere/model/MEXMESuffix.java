/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.KeyNamePair;

/**
 * Mantenimiento de sufijos
 * 
 * @author lama
 */
public class MEXMESuffix extends X_EXME_Suffix {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param ctx
	 * @param EXME_Suffix_ID
	 * @param trxName
	 */
	public MEXMESuffix(Properties ctx, int EXME_Suffix_ID, String trxName) {
		super(ctx, EXME_Suffix_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMESuffix(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene la lista con los sufijos
	 * 
	 * @param ctx
	 * @param addEmpty
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getList(Properties ctx, boolean addEmpty, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT EXME_Suffix_ID, Name ");
		sql.append("FROM EXME_Suffix ");
		sql.append("WHERE IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append("ORDER BY Name ");
		final List<KeyNamePair> list = Query.getListKN(sql.toString(), trxName);
		if (addEmpty) {
			list.add(0, new KeyNamePair(0, " "));
		}
		return list;
	}

}
