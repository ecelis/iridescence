package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Modelo MEXMEActPacienteIndCgo
 * 
 * @author twry
 * 
 */
public class MEXMEActPacienteIndCgo extends X_EXME_ActPacienteIndCgo {

	/** serialVersionUID */
	private static final long serialVersionUID = 1621050559412853958L;
	/** Logger */
	protected transient static CLogger slog = CLogger
			.getCLogger(MEXMEActPacienteIndCgo.class);
	/** Cuando no existe un NDC valido */
	public static final String NDCNOTVALID = "NN";
	/** Cuando el producto no es de formulario */
	public static final String FORMULARY = "NF";
	/** Cuando el producto no tiene precio */
	public static final String NOPRICE = "NP";
	/** Cuando el error es producido por falta de configuracion */
	public static final String CONFIGURATION = "CF";
	/** Cuando el error no es conocido */
	public static final String OTHER = "OT";
	/** Cuando es por proceso */
	public static final String BUSSINESSRULE = "BR";
	/** Cuando al hacer el save falla */
	public static final String SAVEFALSE = "SF";
	/** Cuando hay datos mandatorios */
	public static final String MANDATORY = "MY";
	
	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param EXME_ActPacienteIndCgo_ID
	 * @param trxName
	 */
	public MEXMEActPacienteIndCgo(final Properties ctx,
			final int EXME_ActPacienteIndCgo_ID, final String trxName) {
		super(ctx, EXME_ActPacienteIndCgo_ID, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEActPacienteIndCgo(final Properties ctx, final ResultSet rs,
			final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene el log para la actividad del paciente
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param EXME_ActPacienteInd_ID
	 *            : id actividad del paciente
	 * @param process
	 *            : true si es un proc medico
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return obj MEXMEActPacienteIndCgo
	 */
	public static MEXMEActPacienteIndCgo get(Properties ctx,
			int EXME_ActPacienteInd_ID, boolean process, String trxName) {
		MEXMEActPacienteIndCgo returno = null;
		
		if (process) {
			final StringBuilder sql = new StringBuilder(
					Constantes.INIT_CAPACITY_ARRAY).append(" SELECT *  ")
					.append(" FROM EXME_ActPacienteIndCgo ")
					.append(" WHERE IsActive = 'Y' ")
					.append(" AND   EXME_ActPacienteInd_ID = ? ");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, EXME_ActPacienteInd_ID);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					returno = new MEXMEActPacienteIndCgo(ctx, rs, trxName);
				}

			} catch (Exception e) {
				slog.log(Level.SEVERE, "get", e);
			} finally {
				DB.close(rs, pstmt);
			}
		}
		return returno;
	}
}
