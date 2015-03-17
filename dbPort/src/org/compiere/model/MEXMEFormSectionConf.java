package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

public class MEXMEFormSectionConf extends X_EXME_FormSectionConf {

	private static final long serialVersionUID = 1L;

	private static CLogger slog = CLogger.getCLogger(MEXMEFormSectionConf.class);

	public MEXMEFormSectionConf(Properties ctx, int EXME_FormSectionConf_ID, String trxName) {
		super(ctx, EXME_FormSectionConf_ID, trxName);
	}

	public MEXMEFormSectionConf(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene las opciones del menu dinamico segun los permisos del rol
	 * 
	 * @param tableName
	 * @param childType
	 * @return SQL
	 * @author Lorena Lama Rocio Solorzano
	 */
	private static StringBuilder getSQLMenu(Properties ctx, String tableName, boolean access) {

		final boolean translate = !Language.isBaseLanguage(Env.getAD_Language(ctx));

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT DISTINCT m.*,");
		sql.append(" ch").append(translate ? "T" : "").append(".name AS label");
		sql.append(" FROM EXME_FormSectionConf m");
		sql.append(" INNER JOIN ").append(tableName).append(" ch ON (");
		sql.append(" m.").append(tableName).append("Child_ID=ch.").append(tableName).append("_ID");
		sql.append(" AND ch.isActive='Y')");

		if (translate) {
			sql.append(" INNER JOIN ").append(tableName).append("_Trl chT ON (");
			sql.append(" chT.AD_Language='").append(Env.getAD_Language(ctx)).append("'");
			sql.append(" AND  ch.").append(tableName).append("_ID=chT.").append(tableName).append("_ID)");
		}
		if (access) {
			sql.append(" INNER JOIN ").append(tableName).append("_Access cha ON (");
			sql.append("      ch.").append(tableName).append("_ID>0");
			sql.append(" AND cha.").append(tableName).append("_ID=ch.").append(tableName).append("_ID");
			sql.append(" AND cha.AD_Role_ID=?");
			sql.append(" AND cha.isActive='Y')");
		}
		return sql;
	}

	/**
	 * Obtiene las opciones configuradas para mandarse llamar desde una forma
	 * 
	 * @param ctx
	 *            <Properties>
	 * @param parentID
	 *            <int>
	 * @param roleID
	 *            <int>
	 * @param trxName
	 *            <String>
	 * @return List<MEXMEMenuDinamico>
	 * @author Lorena Lama Rocio Solorzano
	 */
	public static List<MEXMEFormSectionConf> getChildren(Properties ctx, int headerID, Integer roleID, String trxName) {

		final List<MEXMEFormSectionConf> retValue = new ArrayList<MEXMEFormSectionConf>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final boolean access = roleID != null;

		try {

			sql.append("SELECT m.* FROM ( ");
			sql.append(getSQLMenu(ctx, I_AD_Form.Table_Name, access));
			sql.append(" ) m ");
			sql.append(" WHERE m.isActive = 'Y' ");
			sql.append(" AND m.EXME_FormSectionHeader_ID = ? ");
			sql.append("ORDER BY m.sequence ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 1;
			if (access) {
				pstmt.setInt(i, roleID == 0 ? Env.getAD_Role_ID(ctx) : roleID);
				i++;
			} else {
				i = 1;
			}

			pstmt.setInt(i, headerID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEFormSectionConf section = new MEXMEFormSectionConf(ctx, rs, trxName);
				section.setName(rs.getString("label"));
				retValue.add(section);

			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Obtiene los detalles que tienen cuestionario
	 * 
	 * @param ctx
	 * @param headerID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEFormSectionConf> getForms(Properties ctx, int headerID, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_formsectionconf ");
		sql.append("WHERE ");
		sql.append("  exme_formsectionheader_id = ? AND ");
		sql.append("  exme_cuestionario_id IS NOT ");
		sql.append("  NULL ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		sql.append(" ORDER BY SEQUENCE");

		List<MEXMEFormSectionConf> list = new ArrayList<MEXMEFormSectionConf>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, headerID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEFormSectionConf(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * @param ctx
	 * @param optionName
	 * @param image
	 * @param questID
	 * @return
	 * @author Lorena Lama
	 */
	public static String getText(Properties ctx, String optionName, String title, int questID, boolean concat) {
		final StringBuilder retValue = new StringBuilder();
		// Find Image name
		if (StringUtils.isEmpty(title)) {
			retValue.append(optionName);
		} else {
			final String text = getText(ctx, title);
			if (StringUtils.isNotBlank(text)) {
				retValue.append(text);
			} else {
				retValue.append(optionName);
			}
		}
		return retValue.toString();
	}

	/**
	 * Busca la traduccion en AD_Message, AD_Element o en ApplicationResources
	 * 
	 * @param ctx
	 *            - contexto de la aplicacion
	 * @param text
	 *            - valor a traducir
	 * @return el text traducido, si no existe regresa text
	 * @author Lorena Lama
	 */
	public static String getText(Properties ctx, String text) {
		if (StringUtils.isBlank(text)) {
			return text;
		}
		try {
			// ResourceBundle
			text = Utilerias.getApplicationResources(ctx, Env.getLanguage(ctx).getLocale()).getString(text);
		} catch (MissingResourceException mre) {
			try {
				// Message / Element
				text = Msg.translate(Env.getAD_Language(ctx), text);
			} catch (Exception e) {
				slog.log(Level.SEVERE, "Exception NOT found: " + text, e);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "Exception NOT found: " + text, e);
		}
		return text;
	}

	/**
	 * Busca el nombre del cuestionario
	 * 
	 * @param ctx
	 *            - contexto de la aplicacion
	 * @param questID
	 *            - id del cuestionario
	 * @return el nombre del cuestionario
	 * @author Lorena Lama
	 */
	public static String getText(Properties ctx, int questID) {
		if (questID <= 0) {
			return StringUtils.EMPTY;
		} else {
			return DB.getSQLValueString(null, "SELECT Name FROM EXME_Cuestionario WHERE EXME_Cuestionario_ID=?", questID);
		}
	}

	public String getTitleText() {
		return getText(getCtx(), getName(), getTitle(), getEXME_Cuestionario_ID(), false);
	}

}
