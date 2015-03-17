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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * @author Lorena Lama
 */
public class MEXMEAlergia extends X_EXME_Alergia {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** Static Logger */
	private static CLogger		log					= CLogger.getCLogger(MEXMEAlergia.class);

	/**
	 * @param ctx
	 * @param EXME_Alergia_ID
	 * @param trxName
	 */
	public MEXMEAlergia(Properties ctx, int EXME_Alergia_ID, String trxName) {
		super(ctx, EXME_Alergia_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEAlergia(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Find the allergy
	 * 
	 * @param ctx
	 * @param value
	 * @param name
	 * @param trxName
	 * @return true if the value/name already exists.
	 */
	public static int getBy(Properties ctx, String whereClause, String trxName, Object... param) {
		int retValue = 0;
		try {
			retValue = new Query(ctx, Table_Name, whereClause, trxName)//
				.setOnlyActiveRecords(true)//
				.setParameters(param)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" DESCRIPTION DESC ")//
				.firstId();
		}
		catch (Exception ex) {
			log.log(Level.SEVERE, "MEXMEAlergia at method getAlergia ", ex);
		}
		return retValue;
	}

	/**
	 * Método que regresa una lista de objetos LabelValueBean con las alergias correspondientes
	 * de la tabla EXME_Alergia, se utiliza en el módulo de Enfermería en el botón de Alergias
	 * 
	 * @param ctx el Contexto de la aplicación
	 * @param where la clausula where a ejecutar como condición
	 * @param orderby el ordenamiento que llevará
	 * @return una Lista con objetos de tipo LabelValueBean
	 * @author Jesús Cantú
	 */
	public static List<LabelValueBean> getList(Properties ctx, String whereClause, String orderby) {
		final List<LabelValueBean> retValue = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_Alergia.EXME_Alergia_ID, EXME_Alergia.Name ");
		sql.append("FROM EXME_Alergia WHERE EXME_Alergia.isActive = 'Y' ");
		if (whereClause != null) {
			sql.append(whereClause);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY ");
		if (StringUtils.isNotEmpty(orderby)) {
			sql.append(orderby);
		} else {
			sql.append("Name");
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean combo = new LabelValueBean(rs.getString("Name"), rs.getString("EXME_Alergia_ID"));
				retValue.add(combo);
			}
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "getList", e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**
	 * Valida si el nombre de la alergia ya existe 
	 * @param ctx
	 * @param allergyName
	 * @param trxName
	 * @return
	 */
	private boolean isAlreadyAdded(Properties ctx, String allergyName, String trxName) {
		boolean isAlreadyAdded= false;
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT * FROM EXME_Alergia ")
		.append(" WHERE UPPER(EXME_Alergia.Name)  = UPPER(?) AND EXME_Alergia.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, allergyName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				isAlreadyAdded = true;
				break;
			}
		} catch (SQLException sqle) {
			log.log(Level.SEVERE, sql.toString(), sqle);
		} finally {
			DB.close(rs, pstmt);
		}
		return isAlreadyAdded;
	}
	
	@Override
    protected boolean beforeSave(boolean newRecord) {
		boolean ret = false;
    	if (isAlreadyAdded(Env.getCtx(), getName(), null)) {
    		log.saveError(null, Utilerias.getAppMsg(Env.getCtx(), "msj.allergy.error"));
    		ret = false;
    	} else {
    		ret = super.beforeSave(newRecord);
    	}
		return ret;
    }

}
