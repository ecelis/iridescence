package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEPacienteExpedienteV extends X_EXME_Paciente_Expediente_V {

	private static final long serialVersionUID = 1L;

	// private static CLogger log = CLogger.getCLogger(MEXMEPacienteMRN_V.class);

	public MEXMEPacienteExpedienteV(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static String[] patientCols = new String[] { MEXMEPacienteExpedienteV.COLUMNNAME_MRN,
			MEXMEPacienteExpedienteV.COLUMNNAME_Nombre_Pac, MEXMEPacienteExpedienteV.COLUMNNAME_SuffixNSS,
			MEXMEPacienteExpedienteV.COLUMNNAME_FechaNac, MEXMEPacienteExpedienteV.COLUMNNAME_Sexo, 
			MEXMEPacienteExpedienteV.COLUMNNAME_TelParticular };

}
