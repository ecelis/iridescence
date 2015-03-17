package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.QuickSearchTables;

/**
 * Vista de datos del paciente y la cuenta
 * 
 * @author odelarosa
 * 
 */
public class MEXMECtaPacV extends X_EXME_CtaPac_V {

	/**
	 * 
	 */
	private static final long serialVersionUID = 97662094376485266L;

	/**
	 * @param ctx
	 * @param EXME_CtaPac_V_ID
	 * @param trxName
	 */
	public MEXMECtaPacV(Properties ctx, int EXME_CtaPac_V_ID, String trxName) {
		super(ctx, EXME_CtaPac_V_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECtaPacV(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Actualiza los indices de la vista
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pacId
	 *            Id de paciente
	 * @param adOrgId
	 *            Organizacion o -1 si es en general
	 * @param trxName
	 *            Trx name
	 */
	public static void updateIndex(Properties ctx, int pacId, int adOrgId, String trxName) {
		for (Integer ctaPacID : MEXMECtaPac.getOfPatientByOrg(ctx, pacId, trxName, adOrgId)) {
			QuickSearchTables.checkTables(MEXMECtaPacV.class, Table_Name, ctaPacID, trxName, ctx);
		}
	}

}
