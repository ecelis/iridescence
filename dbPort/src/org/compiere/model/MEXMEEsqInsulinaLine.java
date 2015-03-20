package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEEsqInsulinaLine extends X_EXME_EsqInsulinaLine {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		slog				= CLogger.getCLogger(MEXMEEsqInsulinaLine.class);

	public MEXMEEsqInsulinaLine(Properties ctx, int EXME_EsqInsulina_ID, String trxName) {
		super(ctx, EXME_EsqInsulina_ID, trxName);
	}

	public MEXMEEsqInsulinaLine(MEXMEEsqInsulina scale) {
		super(scale.getCtx(), 0, scale.get_TrxName());
		setEXME_EsqInsulina(scale);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEEsqInsulinaLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private MEXMEEsqInsulina	slidingScale;

	public MEXMEEsqInsulina getEXME_EsqInsulina() {
		if (slidingScale == null) {
			slidingScale = new MEXMEEsqInsulina(getCtx(), getEXME_EsqInsulina_ID(), get_TrxName());
		}
		return slidingScale;
	}

	public void setEXME_EsqInsulina(MEXMEEsqInsulina scale) {
		this.slidingScale = scale;
		if (slidingScale != null) {
			setEXME_EsqInsulina_ID(slidingScale.getEXME_EsqInsulina_ID());
		}
	}

	/**
	 * Recupera todos los registros de EXME_EsqInsulinaLine relacionados
	 * 
	 * @param ctx
	 * @param medicoID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEsqInsulinaLine> getDetail(Properties ctx, int esqInsulinaId, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" EXME_EsqInsulina_ID=? ");

		return new Query(ctx, Table_Name, //
				sql.toString(), trxName)//
				.setParameters(esqInsulinaId)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" Lim_Superior, Lim_Inferior ")//
				.list();
	}
}