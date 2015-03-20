package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Detalle Tipo de Configurador
 * @author odelarosa
 *
 */
public class MEXMETipoConfiguradorDet extends X_EXME_TipoConfiguradorDet {

	private static final long serialVersionUID = -4528083956585507598L;

	public MEXMETipoConfiguradorDet(Properties ctx, int EXME_TipoConfiguradorDet_ID, String trxName) {
		super(ctx, EXME_TipoConfiguradorDet_ID, trxName);
	}

	public MEXMETipoConfiguradorDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
