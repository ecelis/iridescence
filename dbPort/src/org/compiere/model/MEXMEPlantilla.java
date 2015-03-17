package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Clase para lasm Plantillas
 * @author mvrodriguez
 *
 */
public class MEXMEPlantilla extends X_EXME_Plantilla  {

	
	private String nameTemplate;
	private String descriptionTemplate;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6954583330894469676L;

	/**
	 * Log
	 */
	private static CLogger log = CLogger.getCLogger (MPHRRecordatorioPac.class);
	
	/**
	 * Constructor con id
	 * @param ctx
	 * @param EXME_Plantilla_ID
	 * @param trxName
	 */
	public MEXMEPlantilla(Properties ctx, int EXME_Plantilla_ID, String trxName) {
		super(ctx, EXME_Plantilla_ID, trxName);
	}
	
	/**
	 * Constructor con ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPlantilla(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public void setNameTemplate(String nameTemplate) {
		this.nameTemplate = nameTemplate;
	}
	
	public String getNameTemplate() {
		return this.nameTemplate;
	}
	
	public void setDescriptionTemplate(String descriptionTemplate) {
		this.descriptionTemplate = descriptionTemplate;
	}
	
	public String getDescriptionTemplate() {
		return this.descriptionTemplate;
	}
	
	/**
	 * Nos trae la lista de las plantillas existentes
	 * @param ctx Contexto
	 * @return List
	 */
	public static List<MEXMEPlantilla> getListTemplate(Properties ctx, boolean isActive, String[] category) {
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<MEXMEPlantilla> lstConsentimientoPac = new ArrayList<MEXMEPlantilla>();

		try {
			sql.append(" SELECT * FROM EXME_PLANTILLA ");

			if (isActive) {
				sql.append("  WHERE EXME_PLANTILLA.ISACTIVE = 'Y' ");
			}
			if (category != null && category.length > 0) {
				sql.append("AND EXME_PLANTILLA.Category in (");
				List<Character> list = new ArrayList<Character>();
				char signo = '?';
				for (int i = 0; i < category.length; i++) {
					list.add(signo);
				}
				sql.append(StringUtils.join(list, ','));
				sql.append(")");
			}

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY EXME_PLANTILLA.NAME ASC ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			
			if (category != null) {
				DB.setParameters(pstmt, category);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEPlantilla template = new MEXMEPlantilla(ctx, rs, null);

				template.setNameTemplate(template.getName());
				template.setDescriptionTemplate(template.getDescription());

				lstConsentimientoPac.add(template);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstConsentimientoPac;
	}

	
	public String getCategoryStr() {
		return MRefList.getListName(getCtx(), CATEGORY_AD_Reference_ID, super.getCategory());
	}
	
}
