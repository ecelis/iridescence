package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.QuickSearchTables;

/**
 * Modelo para vista de encuentro y aseguradora
 * 
 * @author lherrera
 * 
 */
public class MEXMECtaPayer extends X_EXME_Cta_Payer {
	private static CLogger s_log = CLogger.getCLogger(MEXMECtaPayer.class);
	private static final long serialVersionUID = -2181291543101643799L;

	/**
	 * Actualiza el indice de la búsqueda, ya que es una vista y no es capaz de hacerlo por si mismo
	 * 
	 * @param p_ctx
	 *            Contexto de la App
	 * @param pacId
	 *            Paciente a revisar
	 * @param trxName
	 *            trx
	 */
	public static void checkIndex(Properties p_ctx, int pacId, String trxName) {
		List<MEXMEPacienteAseg> lst = MEXMEPacienteAseg.getAllByPatient(p_ctx, pacId, trxName);
		for (MEXMEPacienteAseg aseg : lst) {
			try {
				QuickSearchTables.checkTables(MEXMECtaPayer.class, Table_Name, aseg.getEXME_PacienteAseg_ID(), trxName, p_ctx);
			} catch (Exception ex) {
				s_log.log(Level.WARNING, null, ex);
			}
		}
	}

	/**
	 * @param ctx
	 * @param EXME_Cta_Payer_ID
	 * @param trxName
	 */
	public MEXMECtaPayer(Properties ctx, int EXME_Cta_Payer_ID, String trxName) {
		super(ctx, EXME_Cta_Payer_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECtaPayer(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
