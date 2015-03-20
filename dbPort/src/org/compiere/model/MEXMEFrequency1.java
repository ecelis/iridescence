package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.KeyNamePair;

/**
 * Frequencies
 * 
 * @author lhernandez
 */
public class MEXMEFrequency1 extends X_EXME_Frequency1 {

	/** serialVersionUID */
	private static final long	serialVersionUID	= 1L;
	/** class log */
	private static CLogger		slog					= CLogger.getCLogger(MEXMEFrequency1.class);

	/**
	 * 
	 * @param ctx
	 * @param exmeFreq1Id
	 * @param trxName
	 */
	public MEXMEFrequency1(final Properties ctx, final int exmeFreq1Id, final String trxName) {
		super(ctx, exmeFreq1Id, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param result
	 * @param trxName
	 */
	public MEXMEFrequency1(final Properties ctx, final ResultSet result, final String trxName) {
		super(ctx, result, trxName);
	}
	
	/**
	 * Lista de frecuencias
	 * 
	 * @param ctx
	 * @param onlyActive
	 * @return
	 */
	public static List<KeyNamePair> getAll(final Properties ctx) {
		return getAll(ctx, null);
	}
	
	/**
	 * Lista de frecuencias
	 * 
	 * @param ctx
	 * @param onlyActive
	 * @return
	 */
	public static List<KeyNamePair> getByType2(final Properties ctx, String type) {
		slog.info("getByType2");
		return getAll(ctx, " AND Type=? ", type);
	}

	/**
	 * Lista de frecuencias
	 * 
	 * @param ctx
	 * @param onlyActive
	 * @return
	 */
	public static List<KeyNamePair> getAll(final Properties ctx, String whereClause, Object... params) {
		slog.info("getAll");
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_Frequency1.EXME_Frequency1_ID, lower(EXME_Frequency1.Name) as name ");
		sql.append(" FROM EXME_Frequency1 ");
		sql.append(" WHERE EXME_Frequency1.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		if (StringUtils.isNotBlank(whereClause)) {
			sql.append(whereClause);
		}
		sql.append(" ORDER BY EXME_Frequency1.Name ASC ");
		return Query.getListKN(sql.toString(), null, params);
	}

	/**
	 * Obtiene la lista de horarios para frecuencia 3 seleccionada
	 * 
	 * @return
	 *         List<MEXMEFrequency3>
	 */
	public List<KeyNamePair> getFreq2() {
		slog.info("getFreq2");
		return MEXMEFrequency2.getAll(getCtx(), getEXME_Frequency1_ID());
	}
	
	/**
	 * Lista de frecuencias
	 * @param ctx
	 * @param whereClause
	 * @return
	 */
	public static List<MEXMEFrequency1> getAllFreq1(final Properties ctx, String whereClause, List<Object> params) {
		slog.info("List<MEXMEFrequency1> getAllFreq1");
		return new Query(ctx, MEXMEFrequency1.Table_Name, whereClause, null)//
		.setParameters(params)//
		.addAccessLevelSQL(true)//
		.setOrderBy("EXME_Frequency1.Name")//
		.list();
	}

	public String getTypeStr() {
		return MRefList.getListName(getCtx(), TYPE_AD_Reference_ID, StringUtils.trim(getType()));
	}
	
	@Override
	protected boolean beforeDelete() {
		if (!is_new()) {
			final List<MEXMEFrequency2> lst = MEXMEFrequency2.getAllFreq2(getCtx(), getEXME_Frequency1_ID());
			for (MEXMEFrequency2 frequency2 : lst) {
				if (!frequency2.delete(true, get_TrxName())) {
					return false;
				}
			}
		}
		return super.beforeDelete();
	}
}
