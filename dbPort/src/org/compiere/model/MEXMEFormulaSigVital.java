package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.GridItem;

public class MEXMEFormulaSigVital extends X_EXME_FormulaSigVital implements GridItem {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4840484199433147785L;
	/** Static Logger */
	private static CLogger		slog				= CLogger.getCLogger(MEXMEFormulaSigVital.class);

	private MEXMESignoVital		mVitalSign;

	public MEXMEFormulaSigVital(Properties ctx, int EXME_FormulaSigVital_ID, String trxName) {
		super(ctx, EXME_FormulaSigVital_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEFormulaSigVital(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos todas las formulas existentes
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public static List<MEXMEFormulaSigVital> getListAll(Properties ctx, String trxName) {
		slog.finest("getListAll");
		return getFormulas(ctx, false, false, trxName);
	}

	/**
	 * Obtenemos el Signo Vital relacionado con la formula.
	 * 
	 * @return
	 */
	public MEXMESignoVital getSignoVital() {
		return getSignoVital(false);
	}

	/**
	 * Obtenemos el Signo Vital relacionado con la formula.
	 * 
	 * @return
	 */
	public MEXMESignoVital getSignoVital(boolean requery) {
		if ((mVitalSign == null || requery) && getEXME_FormulaSigVital_ID() > 0) {
			mVitalSign = getSVbyFormula(getCtx(), getEXME_FormulaSigVital_ID(), get_TrxName());
			// sql = "SELECT * FROM EXME_SignoVital WHERE EXME_FormulaSigVital_ID = ?";pstmt.setInt(1, getEXME_FormulaSigVital_ID());
		}
		return mVitalSign;
	}

	/**
	 * Obtenemos el signo vital donde se utiliza la f�rmula
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public static MEXMESignoVital getSVbyFormula(Properties ctx, int formulaID, String trxName) {
		slog.finest("getSVbyFormula");
		return new Query(ctx, MEXMESignoVital.Table_Name, " EXME_FormulaSigVital_ID=? ", trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(formulaID)//
			.first();
	}

	/**
	 * verifica si hay un signo vital que tiene una fórmula asociada.
	 * 
	 * @param ctx
	 * @param EXME_SignoVital_ID
	 * @return
	 */
	public static boolean findSignoById(Properties ctx, int EXME_SignoVital_ID) {
		slog.finest("findSignoById");
		return DB.getSQLValue(null,//
			"SELECT EXME_FormulaSigVital_ID FROM exme_signovital WHERE EXME_SignoVital_ID=?",//
			EXME_SignoVital_ID) > 0;
	}

	/**
	 * 
	 * @param ctx
	 * @param name
	 * @param formulaId
	 * @param trxName
	 * @deprecated
	 * @return
	 */
	public static boolean existeNameFormula(Properties ctx, String name, int formulaId, String trxName) {
		slog.finest("existeNameFormula");
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT EXME_FormulaSigVital_ID FROM EXME_FormulaSigVital ");
		sql.append("LEFT JOIN EXME_SignoVital sv on sv.EXME_FormulaSigVital_ID = EXME_FormulaSigVital.EXME_FormulaSigVital_ID ");
		sql.append(" WHERE sv.Name LIKE ? AND EXME_FormulaSigVital.EXME_FormulaSigVital_ID <> ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
		return DB.getSQLValue(trxName, " ", name, formulaId) > 0;
	}

	/**
	 * 
	 * @param ctx
	 * @param formula
	 * @param formulaId
	 * @param trxName
	 * @deprecated
	 * @return
	 */
	public static boolean existeFormula(Properties ctx, String formula, int formulaId, String trxName) {
		// slog.finest("existeFormula");
		// final StringBuilder sql = new StringBuilder();
		// sql.append("SELECT Formula FROM EXME_FormulaSigVital ");
		// sql.append("WHERE Formula LIKE ? AND EXME_FormulaSigVital_ID <> ? AND isActive = 'Y' ");
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		// return DB.getSQLValueString(trxName, sql.toString(), formula, formulaId) != null;
		return getBy(ctx, COLUMNNAME_Formula, formula, formulaId, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param formula
	 * @param formulaId
	 * @param trxName
	 * @return
	 */
	public static boolean getBy(Properties ctx, String columnName, String value, int formulaId, String trxName) {
		slog.finest("getBy");
		final StringBuilder sql = new StringBuilder();
		sql.append("           EXME_FormulaSigVital.EXME_FormulaSigVital_ID<>? ");
		sql.append(" AND UPPER(EXME_FormulaSigVital.").append(columnName).append(") LIKE UPPER(?) ");
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(formulaId, value)//
			.firstId() > 0;
	}

	/**
	 * Obtener las formulas de acuerdo al nivel de acceso
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public static List<MEXMEFormulaSigVital> getFormulas(Properties ctx, boolean onlyActive, boolean accessLevel, String trxName) {
		return new Query(ctx, Table_Name, null, trxName)//
		.setOnlyActiveRecords(onlyActive)
		.addAccessLevelSQL(accessLevel)//
		.setOrderBy(" EXME_FormulaSigVital.EXME_FormulaSigVital_ID ")//
		.list();
	}

	/** @deprecated */
	private IDColumn	idColumn	= null;

	/** @deprecated */
	public IDColumn getIdColumn() {
		if (idColumn == null) {
			idColumn = new IDColumn(getEXME_FormulaSigVital_ID());
		}
		return idColumn;
	}

	/** @deprecated */
	public String getName() {
		return getSignoVital().getName();
	}

	/** @deprecated */
	public String[] getColumns() {
		return new String[] { "idColumn", "value", "name", "formula", "active" };
	}

	@Override
	protected boolean beforeDelete() {
		// update related vital sign
		return changeVital();
	}

	/** updates related vital sign */
	public boolean changeVital() {
		if (getSignoVital(true) != null) {
			getSignoVital().setEXME_FormulaSigVital_ID(0);
			return getSignoVital().save();
		}
		return true;
	}

}
