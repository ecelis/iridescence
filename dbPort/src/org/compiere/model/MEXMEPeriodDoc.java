package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;

/**
 * @author odelarosa
 * 
 */
public class MEXMEPeriodDoc extends X_EXME_PeriodDoc {

	private static final long serialVersionUID = 3910174252398998140L;

	/**
	 * 
	 * @param ctx
	 * @param periodId
	 * @param policyType
	 * @param orgId
	 * @return
	 */
	public static synchronized int getCurrentNext(Properties ctx, int periodId, String policyType, int orgId) {
		int retValue = -1;

		List<Object> params = new ArrayList<Object>();

		params.add(periodId);
		params.add(policyType);

		StringBuilder sql = new StringBuilder();
		sql.append(" c_period_id = ? and ");
		sql.append(" policytype = ? ");
		
		if (orgId > 0) {
			sql.append(" and ad_org_id = ? ");
			params.add(orgId);
		}

		Query query = new Query(ctx, Table_Name, sql.toString(), null);
		query.setOnlyActiveRecords(true);
		
		if (orgId <= 0) {
			query.addAccessLevelSQL(true);
		}
		
		query.setParameters(params);

		MEXMEPeriodDoc periodDoc = query.first();

		if (periodDoc != null) {
			retValue = periodDoc.getCurrentNext();
			int currentNext = retValue + 1;
			periodDoc.setCurrentNext(currentNext);

			periodDoc.save();
		}

		return retValue;
	}

	/**
	 * Revisa si hay documentos para el periodo
	 * 
	 * @param periodId
	 *            Periodo
	 * @param policyType
	 *            Tipo de Póliza
	 * @param orgId
	 *            Organización
	 * @param trxName
	 *            Trx
	 * @return Si/No hay documentos
	 */
	public static boolean hasDocuments(int periodId, String policyType, int orgId, String trxName) {
		boolean retValue = false;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  exme_perioddoc_id ");
		sql.append("FROM ");
		sql.append("  exme_perioddoc ");
		sql.append("WHERE ");
		sql.append("  c_period_id = ? AND ");
		sql.append("  isactive = 'Y' AND ");
		sql.append("  policyType = ? AND ");
		sql.append("  ad_org_id = ? ");

		retValue = DB.getSQLValue(trxName, sql.toString(), periodId, policyType, orgId) > 0;

		return retValue;
	}

	/**
	 * @param ctx
	 * @param EXME_PeriodDoc_ID
	 * @param trxName
	 */
	public MEXMEPeriodDoc(Properties ctx, int EXME_PeriodDoc_ID, String trxName) {
		super(ctx, EXME_PeriodDoc_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPeriodDoc(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
