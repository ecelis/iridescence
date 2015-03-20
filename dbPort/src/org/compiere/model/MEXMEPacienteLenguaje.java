package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEPacienteLenguaje extends X_EXME_Paciente_Lenguaje {
	private static final CLogger	LOGGER				= CLogger.getCLogger(MEXMEPacienteLenguaje.class);

	private static final long		serialVersionUID	= 1L;

	public MEXMEPacienteLenguaje(Properties ctx, int EXME_Paciente_Lenguaje_ID, String trxName) {
		super(ctx, EXME_Paciente_Lenguaje_ID, trxName);
	}

	public MEXMEPacienteLenguaje(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MLanguage> getPatientLanguages(Properties ctx, int EXME_Paciente_ID, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  case when pac.ad_language = lan.ad_language then 'Y' ELSE 'N' END ");
		sql.append("  AS isDefault, ");
		sql.append("  lan.* ");
		sql.append("FROM ");
		sql.append("  exme_paciente pac ");
		sql.append("  INNER JOIN (exme_paciente_lenguaje pl ");
		sql.append("  INNER JOIN ad_language lan ");
		sql.append("  ON lan.ad_language_id = pl.ad_language_id) ");
		sql.append("  ON pl.exme_paciente_id = pac.exme_paciente_id AND ");
		sql.append("  pl.isactive= ");
		sql.append("'Y' WHERE ");
		sql.append("  pac.exme_paciente_id = ? ");

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MLanguage> list = new ArrayList<MLanguage>();
		try {
			pstm = DB.prepareStatement(sql.toString(), trxName);
			pstm.setInt(1, EXME_Paciente_ID);
			rs = pstm.executeQuery();
			while (rs.next()) {
				MLanguage language = new MLanguage(ctx, rs, trxName);
				language.setDefault(DB.TO_BOOLEAN(rs.getString("isDefault")));
				list.add(language);
			}
			if (list.isEmpty()) {
				MEXMEPaciente pac = new MEXMEPaciente(ctx, EXME_Paciente_ID, null);
				if (StringUtils.isNotBlank(pac.getAD_Language())) {
					MLanguage language = MLanguage.get(ctx, pac.getAD_Language());
					language.setDefault(true);
					list.add(language);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			list = null;
		} finally {
			DB.close(rs, pstm);
		}
		return list;
	}

	public static List<MEXMEPacienteLenguaje> getMEXMEPacienteLenguajes(Properties ctx, int EXME_Paciente_ID, String trxName) {
		return new Query(ctx, X_EXME_Paciente_Lenguaje.Table_Name, " EXME_Paciente_ID = ?", trxName).setParameters(EXME_Paciente_ID)
				.setOnlyActiveRecords(true).addAccessLevelSQL(true).setOrderBy("Created desc").list();
	}

	public static void save(Properties ctx, List<Integer> AD_Languajes_IDs, int EXME_Paciente_ID, String trxName) {
		// Obtenemos los lenguajes actuales
		List<MEXMEPacienteLenguaje> languages = getMEXMEPacienteLenguajes(ctx, EXME_Paciente_ID, trxName);

		// borramos los que no se encuentren dentro de las lista de ids
		// AD_Languajes_IDs
		uno : for (MEXMEPacienteLenguaje pacLang : languages) {
			for (Integer AD_Language_ID : AD_Languajes_IDs) {
				if (pacLang.getAD_Language_ID() == AD_Language_ID) {
					continue uno;
				}
			}
			if (!pacLang.delete(true, trxName)) {
				throw new MedsysException();
			}
		}
		// Agregamos los que no se encuentren dentro de las lista de ids
		// AD_Languajes_IDs
		uno : for (Integer AD_Language_ID : AD_Languajes_IDs) {
			if (AD_Language_ID > 0) {
				for (MEXMEPacienteLenguaje pacLang : languages) {
					if (pacLang.getAD_Language_ID() == AD_Language_ID) {
						continue uno;
					}
				}
				MEXMEPacienteLenguaje newMEXMEPacLang = new MEXMEPacienteLenguaje(ctx, 0, null);
				newMEXMEPacLang.setEXME_Paciente_ID(EXME_Paciente_ID);
				newMEXMEPacLang.setAD_Language_ID(AD_Language_ID);
				if (!newMEXMEPacLang.save(trxName)) {
					throw new MedsysException();
				}
			}
		}
	}

}
