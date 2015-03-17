package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.QuickSearchTables;

/**
 * Institucion Model
 * 
 * @author Valentin Garcia
 * @version $Id: MEXMEInstitucion.java,$
 */
public final class MEXMEInstitucion extends X_EXME_Institucion {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEInstitucion.class);

	/** Institucion Cache */
//	private static CCache<String, MEXMEInstitucion> s_institucion = null;

	/**
	 * Create Empty Institucion
	 * 
	 * @param ctx
	 * @param EXME_Institucion_ID
	 * @param trxName
	 */
	public MEXMEInstitucion(Properties ctx, int EXME_Institucion_ID, String trxName) {
		super(ctx, EXME_Institucion_ID, trxName);
		
	}

	/**
	 * Create Institucion from current row in ResultSet
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEInstitucion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} 

	/**
	 *Obtenemos Todas las Instituciones.
	 * 
	 * @param ctx
	 * @param trxName
	 * @param orderBy (opcional)
	 * @return
	 */
	public static MEXMEInstitucion[] getInstituciones(Properties ctx, String whereClause, String orderBy, String trxName) {

		// Noelia: Agrego parametro whereClause. Cambio sql a StringBuilder
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ArrayList<Object> list = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			sql.append("SELECT * FROM EXME_Institucion WHERE IsActive='Y' ");

			if (whereClause != null && whereClause.length() > 0)
				sql.append(whereClause);

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			if (orderBy != null && orderBy.length() > 0)
				sql.append(" ORDER BY " + orderBy);

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEInstitucion instituciones = new MEXMEInstitucion(ctx, rs, trxName);
				list.add(instituciones);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		MEXMEInstitucion[] instituciones = new MEXMEInstitucion[list.size()];
		list.toArray(instituciones);
		return instituciones;
	}

	/**
     * Obtenemos todas las instituciones
     * @param ctx
     * @param blankLine
     * @param trxName
     * @return
     * @throws SQLException
     */
	public static ArrayList<LabelValueBean> getLst(Properties ctx, boolean blankLine, String trxName)
			throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		ArrayList<LabelValueBean> lstEspec = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT Name, EXME_Institucion_ID FROM EXME_Institucion ").append("WHERE IsActive = 'Y'  ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			lstEspec = new ArrayList<LabelValueBean>();

			if (blankLine)
				lstEspec.add(new LabelValueBean("", "0"));

			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString("Name"), String.valueOf(rs.getInt("EXME_Institucion_ID")));
				lstEspec.add(lvb);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lstEspec;
	}

	
	/**
	 * Returns a MEXMEInstitucion based on the search key (value)
	 * @param ctx The application context
	 * @param value The search key
	 * @param trxName The transaction name
	 * @return a MEXMEInstitucion if the value exists or null otherwise.
	 */
	public static MEXMEInstitucion getByValue(Properties ctx, String value, String trxName) {
		MEXMEInstitucion retVal = null;
		
		StringBuffer sql = new StringBuffer("SELECT * FROM EXME_Institucion ");
		sql.append("WHERE Value = ? ");
		//String st = sql.toString();
		MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, value);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				retVal = new MEXMEInstitucion(ctx, rs, trxName);
			}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getByValue ...", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return retVal;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param Name
	 * @param trxName
	 * @return
	 */
	public static MEXMEInstitucion getByName(Properties ctx, String name, String trxName) {
		MEXMEInstitucion retVal = null;
		
		StringBuffer sql = new StringBuffer("SELECT * FROM EXME_Institucion ");
		sql.append("WHERE name = ? ");
		//String st = sql.toString();
		MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				retVal = new MEXMEInstitucion(ctx, rs, trxName);
			}
			
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getByValue ...", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return retVal;
	}
	
	/**
	 * Actualiza los archivos de indices de lucene
	 * para la tabla MEXMEInstitucion
	 * filtrando por cuenta paciente
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 */
	public static void updateIndex(Properties ctx, int EXME_Institucion_ID, String trxName){
		try {
			QuickSearchTables.checkTables(X_EXME_Institucion.class,
					MEXMEInstitucion.Table_Name, EXME_Institucion_ID, trxName , ctx);
		} catch (Exception ex) {
			s_log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
		}
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		updateIndex(p_ctx, getEXME_Institucion_ID(), get_TrxName());
		return super.afterSave(newRecord, success);
	}
}