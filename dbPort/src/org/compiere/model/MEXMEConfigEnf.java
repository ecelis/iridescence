package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEConfigEnf extends X_EXME_ConfigEnf {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigEnf.class);

	/**
	 * @param ctx
	 * @param EXME_ConfigEnf_ID
	 * @param trxName
	 */
	public MEXMEConfigEnf(Properties ctx, int EXME_ConfigEnf_ID, String trxName) {
		super(ctx, EXME_ConfigEnf_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConfigEnf(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene la configuraci�n de enfermer�a
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Transacci�n
	 * @return Configuracion o null si no cuenta con ella
	 */
	public static MEXMEConfigEnf get(Properties ctx, String trxName) {
		return new Query(ctx, Table_Name, null, trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" AD_Org_ID DESC ")//
			.first();
	}

}
