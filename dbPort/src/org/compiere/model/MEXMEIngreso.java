package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

/**
 * Intake & Meals
 * 
 * @author lama
 */
public class MEXMEIngreso extends X_EXME_Ingreso {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		slog				= CLogger.getCLogger(MEXMEIngreso.class);

	public MEXMEIngreso(final Properties ctx, final int EXME_Ingreso_ID, final String trxName) {
		super(ctx, EXME_Ingreso_ID, trxName);
	}

	public MEXMEIngreso(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * List of intake
	 * @param ctx
	 * @param exmeCtaPacId
	 * @param date
	 * @param exmeDiarioEnfId
	 * @param exmeEncounterFormsId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEIngreso> getIntake(final Properties ctx, final int exmeCtaPacId, final Date date, final int exmeDiarioEnfId,
		final int exmeEncounterFormsId, final String trxName) {
		return getList(ctx, true, exmeCtaPacId, date, exmeDiarioEnfId, exmeEncounterFormsId, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param date
	 * @param EXME_CtaPac_ID
	 * @param EXME_diarioEnf_ID
	 * @param isIntake
	 * @param exmeEncounterFormsId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEIngreso> getList(final Properties ctx, final boolean isIntake, final int exmeCtaPacId, final Date date,
		final int exmeDiarioEnfId, final int exmeEncounterFormsId, final String trxName) {
		slog.fine("MEXMEIngreso#getList >> Intake: " + isIntake + " ctapac: " + exmeCtaPacId + " diarioEnf: " + exmeDiarioEnfId);
		slog.fine(" encForms: " + exmeEncounterFormsId + " trxName: " + trxName);

		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" EXME_CtaPac_ID=? ");
		params.add(exmeCtaPacId);

		if (exmeDiarioEnfId > 0) {
			sql.append("AND EXME_DiarioEnf_ID=? ");
			params.add(exmeDiarioEnfId);
		}
		if (exmeEncounterFormsId > 0) {
			sql.append(" AND exme_encounterforms_id=?  ");
			params.add(exmeEncounterFormsId);
		}
		if (date != null) {
			sql.append(" AND fechaAplica BETWEEN ? AND ? ");
			params.add(TimeUtil.getInitialRange(ctx, date));
			params.add(TimeUtil.getFinalRange(ctx, date));
		}
		sql.append(" AND TYPE IN (");
		if (isIntake) {
			sql.append(DB.TO_STRING(TYPE_PO_Fluid)).append(",");
			sql.append(DB.TO_STRING(TYPE_IV_Fluid)).append(",");
			sql.append(DB.TO_STRING(TYPE_Blood)).append(",");
			sql.append(DB.TO_STRING(TYPE_Tube)).append(",");
			sql.append(DB.TO_STRING(TYPE_Other));
		} else {
			sql.append(DB.TO_STRING(TYPE_Meal_Breakfast)).append(",");
			sql.append(DB.TO_STRING(TYPE_Meal_Lunch)).append(",");
			sql.append(DB.TO_STRING(TYPE_Meal_Dinner)).append(",");
			sql.append(DB.TO_STRING(TYPE_Meal_Snack));
		}
		sql.append(" ) ");

		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(params)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" fechaAplica ")//
			.list();
	}

	/**
	 * 
	 * @param ctx
	 * @param exmeCtaPacId
	 * @param date
	 * @param exmeDiarioEnfId
	 * @param exmeEncounterFormsId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEIngreso> getMeals(final Properties ctx, final int exmeCtaPacId, final Date date, final int exmeDiarioEnfId,
		final int exmeEncounterFormsId, final String trxName) {
		return getList(ctx, false, exmeCtaPacId, date, exmeDiarioEnfId, exmeEncounterFormsId, trxName);
	}

	private MUOM			uom			= null;

	private MEXMEEnfermeria	enfermeria	= null;

	private MEXMECtaPac		ctaPac		= null;

	public MEXMECtaPac getCtaPac() {
		if (ctaPac == null) {
			ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctaPac;
	}

	public MEXMEEnfermeria getEnfermeria() {
		if (enfermeria == null) {
			enfermeria = new MEXMEEnfermeria(getCtx(), getEXME_Enfermeria_ID(), null);
		}
		return enfermeria;
	}

	public MUOM getUom() {
		if (uom == null) {
			uom = new MUOM(getCtx(), getC_UOM_ID(), null);
		}
		return uom;
	}
}
