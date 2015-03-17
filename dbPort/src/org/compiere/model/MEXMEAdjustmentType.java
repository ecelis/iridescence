package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.ValueNamePair;

/**
 * @author odelarosa
 * 
 */
public class MEXMEAdjustmentType extends X_EXME_AdjustmentType {

	private static final long	serialVersionUID	= 5243651466309618127L;
	/** Static Logger */
	private static CLogger		slog				= CLogger.getCLogger(MEXMEAdjustmentType.class);

	/**
	 * @param ctx
	 * @param EXME_AdjustmentType_ID
	 * @param trxName
	 */
	public MEXMEAdjustmentType(Properties ctx, int EXME_AdjustmentType_ID, String trxName) {
		super(ctx, EXME_AdjustmentType_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEAdjustmentType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Get Adjustment Type With Value
	 * 
	 * @param ctx
	 *            context
	 * @param Value
	 *            value
	 * @return MEXMEAdjustment or null
	 */
	public static MEXMEAdjustmentType get(Properties ctx, String Value) {
		if (Value == null || Value.length() == 0)
			return null;
		final String whereClause = "Value=? ";
		MEXMEAdjustmentType retValue = new Query(ctx, I_EXME_AdjustmentType.Table_Name, whereClause, null).setParameters(Value).firstOnly();
		return retValue;
	}

	/**
	 * Get Adjustment Type With Value
	 * 
	 * @param ctx
	 *            context
	 * @param Value
	 *            value
	 * @return MEXMEAdjustment or null
	 */
	public static MEXMEAdjustmentType get(Properties ctx, String whereClause, Object... parameters) {
		MEXMEAdjustmentType retValue = new Query(ctx, I_EXME_AdjustmentType.Table_Name, whereClause, null).setParameters(parameters).firstOnly();
		return retValue;
	}

	/**
	 * 
	 * @param ctx
	 * @param whereClause
	 * @param parameters
	 * @return
	 */
	public static MEXMEAdjustmentType getMEXMEAdjustmentType(Properties ctx, String whereClause, Object... parameters) {
		MEXMEAdjustmentType list = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_AdjustmentType WHERE isActive = 'Y' ");
		sql.append(whereClause != null ? whereClause : " ");
		if (DB.isOracle()) {
			sql.append(" order by to_number(regexp_substr(value,'^[0-9]+')),value ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" order by to_number(substring(value from '^[0-9]+')),value ");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, parameters);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new MEXMEAdjustmentType(ctx, rs, null);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		//
		return list;
	} // getOfBPartner



	/**
	 * Obtiene los Cargos disponibles para el tipo de Adjustment
	 * @param ctx
	 * @param type
	 * @return
	 */
	public static List<ValueNamePair> getAdjustmentType(Properties ctx, Type type) {
		List<ValueNamePair> list = new ArrayList<ValueNamePair>();

		List<MCharge> values = new Query(ctx, MCharge.Table_Name, type == null ? null : type.whereClause, null).list();
		for (MCharge mCharge : values) {
			list.add(new ValueNamePair(String.valueOf(mCharge.getC_Charge_ID()), mCharge.getName()));
		}
		return list;
	}

	/**
	 * Filtros de cargos por tipo de adjustment
	 * @author pedro
	 *
	 */
	public enum Type {
		INFORMATION_ADJUSTMENT( X_EXME_AdjustmentType.TYPE_Deductible, X_EXME_AdjustmentType.TYPE_Coinsurance, X_EXME_AdjustmentType.TYPE_CoPay),
		PROFESSION_ADJUSTMENT(X_EXME_AdjustmentType.TYPE_Adjustment, X_EXME_AdjustmentType.TYPE_BadDebtAdjustment),
		PROFESSION_ADJUSTMENT_GENERIC(X_EXME_AdjustmentType.TYPE_OthersPayment, X_EXME_AdjustmentType.TYPE_PatientAdjustments),
		PROFESSIONAL_PAYMENT(X_EXME_AdjustmentType.TYPE_InsurancePayments),
		PROFESSIONAL_PAYMENT_GENERIC(
				X_EXME_AdjustmentType.TYPE_Others,
				X_EXME_AdjustmentType.TYPE_CopayPayment,
				X_EXME_AdjustmentType.TYPE_DeductiblePayment,
				X_EXME_AdjustmentType.TYPE_CoinsurancePayment);
		public final String[]	supportedTypes;
		public final String		whereClause;

		private Type(String... supportedTypes) {
			this.supportedTypes = supportedTypes;
			StringBuilder builder = new StringBuilder(" isActive = 'Y' AND Type IN ('").append(StringUtils.join(supportedTypes, "','")).append("') ");
			whereClause = builder.toString();
		}

	}
}
