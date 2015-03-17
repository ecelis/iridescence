package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 * Configuracion por Cliente/Organizacion del menu dinamico
 * 
 * @author Lorena Lama
 */
public class MEXMEMenuDinamicoConf extends X_EXME_MenuDinamicoConf {

	private static final long serialVersionUID = 1L;
	private static CLogger slog = CLogger.getCLogger(MEXMEMenuDinamicoConf.class);
	private String name = null;

	public MEXMEMenuDinamicoConf(Properties ctx, int EXME_Menu_Dinamico_ID, String trxName) {
		super(ctx, EXME_Menu_Dinamico_ID, trxName);
	}

	public MEXMEMenuDinamicoConf(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public boolean setValueNoCheck(String ColumnName, Object value) {
		return super.set_ValueNoCheck(ColumnName, value);
	}

	/**
	 * @param ctx
	 * @param formID
	 * @param whereString
	 * @param params
	 * @return
	 */
	public static List<MEXMEMenuDinamicoConf> getChilds(Properties ctx, int formID, final String whereString,
			Object... params) {

		final List<MEXMEMenuDinamicoConf> retValue = new ArrayList<MEXMEMenuDinamicoConf>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT * FROM ( ");
//			sql.append(getSQLMenu(ctx, MEXMEStruts.Table_Name, MEXMEMenuDinamico.CHILDTYPE_Struts));
//			sql.append(" UNION ALL ");
			sql.append(getSQLMenu(ctx, MForm.Table_Name, MEXMEMenuDinamico.CHILDTYPE_Form));
			sql.append(" UNION ALL ");
			sql.append(getSQLMenu(ctx, MWindow.Table_Name, MEXMEMenuDinamico.CHILDTYPE_Window));
			sql.append(" UNION ALL ");
			sql.append(getSQLMenu(ctx, MProcess.Table_Name, MEXMEMenuDinamico.CHILDTYPE_Process));
			sql.append(" ) dual ");
			
			sql.append(" WHERE isActive='Y' ");
			sql.append(" AND AD_Form_ID=? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name).replace(Table_Name+".", ""));
			sql.append(" ").append(whereString != null ? whereString : "");
			sql.append(" ORDER BY visible, sequence ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			int i = 1;
			pstmt.setInt(i++, formID);
			for (Object param : params) {
				if (param != null) {
					DB.setParameter(pstmt, i++, param);
				}
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEMenuDinamicoConf obj = new MEXMEMenuDinamicoConf(ctx, rs, null);
				final MEXMEMenuDinamico menu = (MEXMEMenuDinamico)obj.getEXME_Menu_Dinamico();
				menu.setOptionName(rs.getString("label"));
				obj.setName(menu.getImageName());
				retValue.add(obj);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param childType
	 * @param translate
	 * @return
	 */
	private static StringBuilder getSQLMenu(Properties ctx, String tableName, String childType){
		final boolean translate = !Language.isBaseLanguage(Env.getAD_Language(ctx));
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT mc.*, m.image, m.EXME_Cuestionario_ID,");
		sql.append(" ch").append(translate ? "T" : "").append(".name AS label");
		sql.append(" FROM EXME_MenuDinamicoConf mc ");
		sql.append(" INNER JOIN EXME_Menu_Dinamico m ON (m.EXME_Menu_Dinamico_ID=mc.EXME_Menu_Dinamico_ID ");
		sql.append(" AND m.isActive='Y') ");
		
		sql.append(" INNER JOIN ").append(tableName).append(" ch ON (");
		sql.append("      m.ChildType=").append(DB.TO_STRING(childType));
		sql.append(" AND  m.").append(tableName).append("Child_ID=ch.").append(tableName).append("_ID");
		sql.append(" AND ch.isActive='Y')");
		if (translate) {
			sql.append(" INNER JOIN ").append(tableName).append("_Trl chT ON (");
			sql.append("       m.ChildType=").append(DB.TO_STRING(childType));
			sql.append(" AND chT.AD_Language=").append(DB.TO_STRING(Env.getAD_Language(ctx)));
			sql.append(" AND  ch.").append(tableName).append("_ID=chT.").append(tableName).append("_ID)");
		}
		return sql;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
