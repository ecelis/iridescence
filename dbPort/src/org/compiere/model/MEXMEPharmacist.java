package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Pharmacist Model
 */
public class MEXMEPharmacist extends X_EXME_Pharmacist {

	/** serialVersionUID */
	private static final long serialVersionUID = 4702566792187076843L;

	public MEXMEPharmacist(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MEXMEPharmacist(Properties ctx, int EXME_Pharmacist_ID, String trxName) {
		super(ctx, EXME_Pharmacist_ID, trxName);
	}

	/**
	 * Returns pharmacist's name
	 * 
	 * @param ctx
	 * @param pharmacistId
	 * @return
	 */
	public static String getFullName(Properties ctx, int pharmacistId) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT Apellido1||' '||Name AS Nombre ");
		sql.append("FROM EXME_Pharmacist ");
		sql.append("WHERE EXME_Pharmacist_ID = ? ");
		sql.append("AND isActive = 'Y' ");
		return DB.getSQLValueString(null, sql.toString(), pharmacistId);
	}
}