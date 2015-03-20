package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEPrescDietaDet2 extends X_EXME_PrescDietaDet2 {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@SuppressWarnings("unused")
	private static CLogger		log					= CLogger.getCLogger(MEXMEPrescDietaDet.class);

	public MEXMEPrescDietaDet2(Properties ctx, int EXME_PrescDietaDet2_ID, String trxName) {
		super(ctx, EXME_PrescDietaDet2_ID, trxName);
	}

	public MEXMEPrescDietaDet2(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

//	private String	tipo	= null;
//
//	public String getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(String tipo) {
//		this.tipo = tipo;
//	}

	/**
	 * rsolorzano
	 * Regresa el valor seleccionado de la configuracion de dietas
	 * 
	 * @param Properties ctx
	 * @param int prescID
	 * @param String trxName
	 * @return String
	 * @deprecated
	 *
	public static String getValorSeleccionado(Properties ctx, int prescID, int configDietaID, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT EXME_CONFIGDIETA_DET_ID AS VALOR ");
		sql.append("FROM EXME_PRESCDIETADET2 ");
		sql.append("WHERE EXME_PRESCDIETA_ID = ? AND EXME_CONFIGDIETA_ID = ? ");
		return DB.getSQLValueStringEx(trxName, sql.toString(), prescID, configDietaID);
	}*/

}
