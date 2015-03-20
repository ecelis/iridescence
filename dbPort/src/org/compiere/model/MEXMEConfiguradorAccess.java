/**
 *
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * Business logic for the configurator access table.
 * @author mrojas
 */
public class MEXMEConfiguradorAccess extends X_EXME_Configurador_Access {

	/**
	 *
	 */
	private static final long serialVersionUID = -6492457614916050802L;


	private static CLogger logger = CLogger.getCLogger(MEXMEConfiguradorAccess.class);


	/**
	 * @param ctx
	 * @param EXME_Configurador_Access_ID
	 * @param trxName
	 */
	public MEXMEConfiguradorAccess(Properties ctx, int ignored, String trxName) {
		super(ctx, 0, trxName);

		if (ignored != 0) {
			throw new IllegalArgumentException("Multi-Key");
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConfiguradorAccess(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


	/**
	 * Returns the configurators assigned to the current role and for client type.
	 * @see {@link MClient#TIPOCLIENTE_AD_Reference_ID}
	 * @param adRoleId The current role
	 * @param tipoCliente The client type for the client we're login into
	 * @return a List of KeyNamePair objects with the identifier and name for
	 * the configurator, translated if we're not in the base language.
	 */
	public static List<KeyNamePair> getConfiguratorsForRoleAndType(int adRoleId, String tipoCliente) {

		List<KeyNamePair> configurators = new ArrayList<KeyNamePair>();
		StringBuilder sql = new StringBuilder("SELECT DISTINCT conf.exme_configurador_id, ");

		if(Env.getLanguage(Env.getCtx()).isBaseLanguage()) {
			sql.append("conf.name \n");
		} else {
			sql.append("COALESCE(trl.name, conf.name) AS name \n");
		}

		sql.append("FROM EXME_TipoConfiguradorDet tcd \n")
		.append("  INNER JOIN EXME_Configurador conf ON (tcd.exme_configurador_id = conf.exme_configurador_id) \n");

		if(!Env.getLanguage(Env.getCtx()).isBaseLanguage()) {
			sql.append("  LEFT JOIN EXME_Configurador_Trl trl ON (trl.exme_configurador_id = conf.exme_configurador_id) \n");
		}

		sql.append("  INNER JOIN exme_tipoconfigurador tc ON (tc.exme_tipoconfigurador_id = tcd.exme_tipoconfigurador_id) \n")
		.append("  INNER JOIN EXME_Configurador_Access ca ON (tcd.exme_configurador_id = ca.exme_configurador_id) \n")
		.append("WHERE tc.tipoarea = ? \n");

		sql =
				new StringBuilder(
						MEXMELookupInfo.addAccessLevelSQL(
								Env.getCtx(),
								sql.toString(),
								"EXME_TipoConfiguradorDet",
								"tc"
						)
				);

		sql.append("\n AND ca.AD_Role_ID = ? \n")
		.append("  AND ca.IsActive = 'Y' \n");

		if(!Env.getLanguage(Env.getCtx()).isBaseLanguage()) {
			sql.append("  AND trl.AD_Language = ? \n");
		}

		sql.append("ORDER by name");

		ResultSet rs = null;
		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);

		try {
			pstmt.setString(1, tipoCliente);
			pstmt.setInt(2, adRoleId);

			if(!Env.getLanguage(Env.getCtx()).isBaseLanguage()) {
				pstmt.setString(3, Env.getAD_Language(Env.getCtx()));
			}

			rs = pstmt.executeQuery();

			while(rs.next()) {
				KeyNamePair configurator =
						new KeyNamePair(
								rs.getInt("exme_configurador_id"),
								rs.getString("name")
						);
				configurators.add(configurator);
			}
		} catch (SQLException e) {
			logger.log(
					Level.WARNING,
					"SQL : " + sql + "\n params : " + tipoCliente + ", " + adRoleId,
					e
			);
		} finally {
			DB.close(rs, pstmt);
		}

		return configurators;
	}
}
