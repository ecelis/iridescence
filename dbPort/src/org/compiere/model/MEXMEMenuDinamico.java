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
import org.compiere.util.Evaluator;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 * Clase de modelo para la tabla EXME_Menu_Dinamico
 * Creado: 17/08/2010
 * <p>
 * 
 * @author rsolorzano
 * @author Lorena Lama
 */
public class MEXMEMenuDinamico extends X_EXME_Menu_Dinamico {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		slog				= CLogger.getCLogger(MEXMEMenuDinamico.class);

	private String				imgName				= null;

	public MEXMEMenuDinamico(Properties ctx, int EXME_Menu_Dinamico_ID, String trxName) {
		super(ctx, EXME_Menu_Dinamico_ID, trxName);
	}

	public MEXMEMenuDinamico(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public String getImgName(boolean reload) {
		if (imgName == null || reload) {
			imgName = getImageName();
		}
		return imgName;
	}

	public String getImgName() {
		return getImgName(false);
	}

	@Override
	public String getImageURL() {
		if (StringUtils.isEmpty(super.getImageURL())) {
			setImageURL("images/76x76/" + getChildIcon());
		}
		return super.getImageURL();
	}

	@Override
	public void setChildType(String ChildType) {
		super.setChildType(ChildType);
		this.getImageURL();// set default
	}

	public String getChildIcon() {
		if (getChildType().equals(CHILDTYPE_Form)) {
			return "mForm.png";
		} else if (getChildType().equals(CHILDTYPE_Window)) {
			return "mWindow.png";
		} else if (getChildType().equals(CHILDTYPE_Process)) {
			return "mProcess.png";
		} else {
			return "mWindow.png";
		}
	}

	/**
	 * Obtiene las opciones configuradas para mandarse llamar desde una forma
	 * 
	 * @param ctx
	 * @param parentID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEMenuDinamico> getChildren(Properties ctx, int parentID, String trxName) {
		return getChildren(ctx, parentID, null, trxName, false);
	}

	/**
	 * Obtiene las opciones configuradas para mandarse llamar desde una forma
	 * @param ctx <Properties>
	 * @param parentID <int>
	 * @param roleID <int>
	 * @param trxName <String>
	 * @return List<MEXMEMenuDinamico>
	 * @author Lorena Lama
	 */
	public static List<MEXMEMenuDinamico> getChildren(Properties ctx, int parentID, int roleID, String trxName) {
		return getChildren(ctx, parentID, roleID, trxName, true);
	}

	/**
	 * Obtiene las opciones configuradas para mandarse llamar desde una forma
	 * @param ctx <Properties>
	 * @param parentID <int>
	 * @param roleID <int>
	 * @param trxName <String>
	 * @return List<MEXMEMenuDinamico>
	 * @author Lorena Lama
	 */
	public static List<MEXMEMenuDinamico> getChildren(Properties ctx, int parentID, Integer roleID, String trxName, boolean validateMenuConfig) {

		final List<MEXMEMenuDinamico> retValue = new ArrayList<MEXMEMenuDinamico>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final List<Object> list = new ArrayList<Object>();
		try {
			// Opciones de Menu
			final boolean validateAccessRole = roleID != null;
			final StringBuilder sqlFrom = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sqlFrom.append(" FROM ( \n");
			sqlFrom.append(getSQLMenu(ctx, I_AD_Form.Table_Name, CHILDTYPE_Form, validateAccessRole));
			sqlFrom.append("\n UNION ALL ");
			sqlFrom.append(getSQLMenu(ctx, I_AD_Window.Table_Name, CHILDTYPE_Window, validateAccessRole));
			sqlFrom.append("\n UNION ALL ");
			sqlFrom.append(getSQLMenu(ctx, I_AD_Process.Table_Name, CHILDTYPE_Process, validateAccessRole));
			sqlFrom.append("\n ) m ");
			if (validateAccessRole) {
				for (int i = 1; i <= 3; i++) {
					list.add(roleID == 0 ? Env.getAD_Role_ID(ctx) : roleID);
				}
			}

			// Organizacion para configuracion
			final int orgID;
			if (validateMenuConfig && roleID != null) {
				orgID =
					DB.getSQLValue(null,
						"SELECT NVL(MAX(AD_org_ID),-1) FROM EXME_MenuDinamicoConf WHERE isActive='Y' AND AD_Client_ID=? AND AD_Form_ID=?",
						Env.getAD_Client_ID(ctx), parentID);
			} else {
				orgID = -1;
			}

			if (orgID < 0) {
				// DEFAULT QUERY
				sql.append("SELECT m.* ");
				sql.append(sqlFrom);
				sql.append("\n WHERE m.isActive = 'Y' ");
				sql.append("\n AND m.AD_FormParent_ID=? ");
				sql.append("\n ORDER BY m.sequence ");
				list.add(parentID);
			} else {
				// QUERY CONSIDERANDO LA CONFIGURACION DEL CLIENTE
				sql.append("SELECT m.*, \n");
				sql.append("\n CASE WHEN conf.EXME_Menu_Dinamico_ID IS NULL");
				sql.append("\n THEN m.sequence ELSE conf.sequence END AS sequenceMenu \n");
				sql.append(sqlFrom);
				// Lama: configuracion de menu
				sql.append("\n LEFT JOIN EXME_MenuDinamicoconf conf ON ");
				sql.append("\n (   conf.EXME_Menu_Dinamico_ID=m.EXME_Menu_Dinamico_ID");
				sql.append("\n AND conf.AD_Form_ID=m.AD_FormParent_ID");
				sql.append("\n AND conf.isactive='Y'");
				// nivel de acceso
				sql.append("\n AND conf.AD_Client_ID=?");
				sql.append("\n AND conf.AD_Org_ID=?");
				sql.append("\n ) ");
				sql.append("\n WHERE m.isActive = 'Y' ");
				sql.append("\n AND m.AD_FormParent_ID=? ");
				// opcion nueva, o esta como visible
				sql.append("\n AND (conf.EXME_Menu_Dinamico_ID IS NULL");
				sql.append("\n OR conf.Visible='Y') ");
				sql.append("\n ORDER BY sequenceMenu ");

				list.add(Env.getAD_Client_ID(ctx));
				list.add(orgID);
				list.add(parentID);
			}

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, list);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEMenuDinamico menu = new MEXMEMenuDinamico(ctx, rs, trxName);
				if (menu.isDisplay()) {
					menu.setOptionName(rs.getString("label"));
					retValue.add(menu);
				} else {
					continue;
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Obtiene las opciones del menu dinamico segun los permisos del rol
	 * @param tableName
	 * @param childType
	 * @return SQL
	 * @author Lorena Lama
	 */
	private static StringBuilder getSQLMenu(Properties ctx, String tableName, String childType, boolean access) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT DISTINCT m.*, \n");
		getSQLOptionName(ctx, sql, tableName, childType);
		if (access) {
			sql.append("\n INNER JOIN ").append(tableName).append("_Access cha ON (");
			sql.append("\n      ch.").append(tableName).append("_ID>0");
			sql.append("\n AND cha.").append(tableName).append("_ID=ch.").append(tableName).append("_ID");
			sql.append("\n AND cha.AD_Role_ID=?");
			sql.append("\n AND cha.isActive='Y')");
		}
		return sql;
	}
	
	private static StringBuilder getSQLOptionName(Properties ctx, StringBuilder sql, String tableName, String childType) {
		final boolean translate = !Language.isBaseLanguage(Env.getAD_Language(ctx));
		if(sql == null ){
			sql = new StringBuilder("SELECT ");
		} else if(sql.length() == 0) {
			sql.append("SELECT ");
		}
		sql.append(" ch").append(translate ? "T" : "").append(".name AS label");
		sql.append("\n FROM EXME_Menu_Dinamico m");
		sql.append("\n INNER JOIN ").append(tableName).append(" ch ON (");
		sql.append("\n      m.ChildType=").append(DB.TO_STRING(childType));
		sql.append("\n AND  m.").append(tableName).append("Child_ID=ch.").append(tableName).append("_ID ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_ID, "m"));//Ticket#05171
		sql.append("\n AND ch.isActive='Y')");
		if (translate) {
			sql.append("\n INNER JOIN ").append(tableName).append("_Trl chT ON (");
			sql.append("\n       m.ChildType=").append(DB.TO_STRING(childType));
			sql.append("\n AND chT.AD_Language=").append(DB.TO_STRING(Env.getAD_Language(ctx)));
			sql.append("\n AND  ch.").append(tableName).append("_ID=chT.").append(tableName).append("_ID)");
		}
		return sql;
	}
	
	/**
	 * Busca la traduccion en AD_Message, AD_Element o en ApplicationResources
	 * @return el text traducido, si no existe regresa text
	 * @author Lorena Lama
	 */
	public String getTranslatedText() {
		if (StringUtils.isBlank(getImage())) {
			return getImage();
		}
		String retValue = getImage();
		try {
			// ResourceBundle
			retValue = Utilerias.getApplicationResources(getCtx(), Env.getLanguage(getCtx()).getLocale()).getString(getImage());
		} catch (MissingResourceException mre) {
			try {
				// Message / Element
				retValue = Msg.translate(Env.getAD_Language(getCtx()), getImage());
			} catch (Exception e) {
				slog.log(Level.SEVERE, "Exception NOT found: " + getImage(), e);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "Exception NOT found: " + getImage(), e);
		}
		return retValue;
	}

	/**
	 * Valida la logica para desplegar
	 * 
	 * @return
	 * @author Lorena Lama
	 */
	private boolean isDisplay() {
		final String logic = this.getDisplayLogic();
		if (logic != null && logic.length() > 0) {
			boolean display = Evaluator.evaluateLogic(this, logic);
			if (!display) {
				slog.info("Not displayed - " + logic);
				return false;
			}
		}
		return true;
	}

	/**
	 * Regresa el valor de una columna o propiedad del ctx
	 * @author Lorena Lama
	 */
	@Override
	public String get_ValueAsString(String variableName) {
		// PO - obtiene el valor de la columna
		String retValue = super.get_ValueAsString(variableName);
		if (retValue == null || retValue.equals(""))
			// busca el valor en el ctx --> @Key@
			retValue = Env.getContext(getCtx(), 0, variableName);
		return retValue;
	} // get_ValueAsString

	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		setParentType(PARENTTYPE_Form);
		if (StringUtils.isEmpty(getChildType())) {
			return false;
		}
		if (getChildType().equals(CHILDTYPE_Form)) {
			setAD_WindowChild_ID(0);
			setAD_ProcessChild_ID(0);
		} else if (getChildType().equals(CHILDTYPE_Window)) {
			setAD_FormChild_ID(0);
			setAD_ProcessChild_ID(0);
		} else if (getChildType().equals(CHILDTYPE_Process)) {
			setAD_FormChild_ID(0);
			setAD_WindowChild_ID(0);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Obtiene las opciones configuradas para mandarse llamar desde una forma
	 * @param ctx <Properties>
	 * @param parentType <String> (deprecated)
	 * @param parentID <int>
	 * @return List<KeyNamePair>
	 * @author Lorena Lama
	 */
	public static List<KeyNamePair> getAllChildren(Properties ctx, int parentID) {

		final List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT * FROM ( ");
			sql.append(getSQLMenu(ctx, I_AD_Form.Table_Name, CHILDTYPE_Form, false));
			sql.append(" UNION ALL ");
			sql.append(getSQLMenu(ctx, I_AD_Window.Table_Name, CHILDTYPE_Window, false));
			sql.append(" UNION ALL ");
			sql.append(getSQLMenu(ctx, I_AD_Process.Table_Name, CHILDTYPE_Process, false));
			sql.append(" ) dual ");
			sql.append(" WHERE isActive = 'Y' ");
			sql.append(" AND AD_FormParent_ID = ? ");
			sql.append(" ORDER BY sequence ");

			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setInt(1, parentID);//RQM #3907
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEMenuDinamico menu = new MEXMEMenuDinamico(ctx, rs, null);
				menu.setOptionName(rs.getString("label"));
				final KeyNamePair item = new KeyNamePair(menu.getEXME_Menu_Dinamico_ID(), menu.getImgName());
				retValue.add(item);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Obtiene las opciones configuradas para mandarse llamar desde una forma
	 * @param ctx <Properties>
	 * @return List<KeyNamePair>
	 * @author Lorena Lama
	 */
	public static List<KeyNamePair> getAllParents(Properties ctx) {

		final List<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			final String AD_Language = Env.getAD_Language(ctx);
			final boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, Table_Name);
			sql.append("SELECT DISTINCT AD_Form.AD_Form_ID, ");
			if(isBaseLanguage) {
				sql.append(" AD_Form.name as Name ");
				sql.append("FROM AD_Form ");
			} else {
				sql.append("tForm.name as Name ");
				sql.append("FROM AD_Form ");
				sql.append("INNER JOIN AD_Form_Trl tForm ON (AD_Form.AD_Form_ID=tForm.AD_Form_ID ");
				sql.append("                             AND tForm.AD_Language=? ) ");
			}
			sql.append("INNER JOIN EXME_Menu_Dinamico m ON (AD_Form.AD_Form_ID=m.AD_FormParent_ID ");
			sql.append("                                 AND m.isActive='Y') ");
			sql.append("WHERE AD_Form.isActive='Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "m"));
			sql.append("ORDER BY Name ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			if(!isBaseLanguage) {
				pstmt.setString(1, AD_Language);	
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final KeyNamePair menu = new KeyNamePair(rs.getInt(1), rs.getString(2));
				retValue.add(menu);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	public String getImageName() {
		String retValue = "";
		// si no tiene texto ...
		if (StringUtils.isBlank(getImage())) {
			if (getEXME_FormSectionHeader_ID() > 0) {
				retValue = getEXME_FormSectionHeader().getName();
			} else if (getEXME_Cuestionario_ID() > 0) {
				retValue = getQuestName();
			}
		} else {
			retValue = getTranslatedText();
		}
		
		if(StringUtils.isBlank(retValue)){
			retValue = getOptionName();//defecto
		}
		return retValue;
		
	}
	
	public String getChildTableName() {
		if (CHILDTYPE_Form.equals(getChildType())) {
			return I_AD_Form.Table_Name;
		} else if (CHILDTYPE_Window.equals(getChildType())) {
			return I_AD_Window.Table_Name;
		} else if (CHILDTYPE_Process.equals(getChildType())) {
			return I_AD_Process.Table_Name;
		} else {
			return "";
		}
	}
	
	/**
	 * @param ctx
	 * @param optionName
	 * @param image
	 * @param questID
	 * @return
	 * @author Lorena Lama
	 *
	public static String getText(Properties ctx, String optionName, String image, int questID, boolean concat) {
		final StringBuilder retValue = new StringBuilder();
		// Find Image name
		if (questID <= 0 || concat) {
			final String text = getText(ctx, image);
			if (StringUtils.isBlank(text) || text.equals(image)) {
				retValue.append(optionName);
			} else {
				retValue.append(text);
			}
		}
		// Find questionnarie name
		if (questID > 0) {
			if (concat) {
				retValue.append(" - ");
			}
			retValue.append(getText(ctx, questID));
		}
		return retValue.toString();
	}*/

	/**
	 * Busca el nombre del cuestionario
	 * @return el nombre del cuestionario
	 * @author Lorena Lama
	 */
	public String getQuestName() {
		if (getEXME_Cuestionario_ID() <= 0) {
			return StringUtils.EMPTY;
		} else {
			return DB.getSQLValueString(null, "SELECT Name FROM EXME_Cuestionario WHERE EXME_Cuestionario_ID=?", getEXME_Cuestionario_ID());
		}
	}

	
	private String optionName = null;
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionName() {
		if(optionName == null) {
			final StringBuilder sql = getSQLOptionName(getCtx(), null, getChildTableName(), getChildType());
			sql.append(" WHERE m.EXME_Menu_Dinamico_ID=? ");
			optionName = DB.getSQLValueString(null, sql.toString(), getEXME_Menu_Dinamico_ID());
		}
		return optionName;
	}
	public MForm getAD_FormParent() {
		return getAD_FormParent_ID() > 0 ? new MForm(getCtx(), getAD_FormParent_ID(), get_TrxName()) : null;
	}
	public MForm getAD_FormChild() {
		return getAD_FormChild_ID() > 0 ? new MForm(getCtx(), getAD_FormChild_ID(), get_TrxName()) : null;
	}
}
