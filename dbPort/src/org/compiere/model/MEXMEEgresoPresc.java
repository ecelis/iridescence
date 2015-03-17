package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.TimeUtil;

/**
 * Output
 * 
 * @author lama
 */
public class MEXMEEgresoPresc extends X_EXME_EgresoPresc {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		slog				= CLogger.getCLogger(MEXMEEgresoPresc.class);

	public MEXMEEgresoPresc(final Properties ctx, final int EXME_EgresoPresc_ID, final String trxName) {
		super(ctx, EXME_EgresoPresc_ID, trxName);
	}

	public MEXMEEgresoPresc(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * List of output
	 * @param ctx
	 * @param exmeCtaPacId encounter id
	 * @param date Day
	 * @param exmeDiarioEnfId nurse journal id
	 * @param exmeEncounterFormsId encounter form id
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEgresoPresc> getList(final Properties ctx, final int exmeCtaPacId, final Date date, final int exmeDiarioEnfId,
		final int exmeEncounterFormsId, final String trxName) {
		slog.fine("MEXMEEgresoPresc#getList >> ctapac: " + exmeCtaPacId + " diarioEnf: " + exmeDiarioEnfId);
		slog.fine(" encForms: " + exmeEncounterFormsId + " trxName: " + trxName);

		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" EXME_CtaPac_ID=? ");
		params.add(exmeCtaPacId);

		if (exmeDiarioEnfId > 0) {
			sql.append("AND EXME_DiarioEnf_ID=? ");
			params.add(exmeDiarioEnfId);
		}
		if (date != null) {
			sql.append(" AND fechaAplica BETWEEN ? AND ? ");
			params.add(TimeUtil.getInitialRange(ctx, date));
			params.add(TimeUtil.getFinalRange(ctx, date));
		}

		if (exmeEncounterFormsId > 0) {
			sql.append(" AND exme_encounterforms_id=?  ");
			params.add(exmeEncounterFormsId);
		}
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(params)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" fechaAplica ")//
			.list();
	}

	private MEXMECtaPac	ctaPac	= null;

	public MEXMECtaPac getCtaPac() {
		if (ctaPac == null) {
			ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctaPac;
	}

}
