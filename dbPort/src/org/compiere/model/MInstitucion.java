package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Institucion Model
 * 
 * @author Valentin Garcia
 * @version $Id: MInstitucion.java,$
 */
public final class MInstitucion extends X_EXME_Institucion {

	/**
	 * 
	 * @param ctx
	 * @param EXME_Institucion_ID
	 * @return
	 *
	public static MInstitucion get(Properties ctx, int EXME_Institucion_ID) {
		MInstitucion i = null;
		String sql = "Select * from EXME_Intitucion where EXME_Institucion_ID=? ";

		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Intitucion");

		try {
			ps = DB.prepareStatement(sql);
			ps.setInt(1, EXME_Institucion_ID);

			rs = ps.executeQuery();

			while (rs.next()) {
				i = new MInstitucion(ctx, rs, null);
			}
			rs.close();
			ps.close();
			rs = null;
			ps = null;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			ps = null;
			rs = null;
		}

		return i;
	} // get

	/**
	 * get All Institucioines get all Inst
	 * 
	 * @param ctx
	 * @return
	 *
	public static MInstitucion[] getInstituciones(Properties ctx) {
		if (s_institucion == null || s_institucion.size() == 0)
			loadAllInstituciones(ctx);
		MInstitucion[] retValue = new MInstitucion[s_institucion.size()];
		s_institucion.values().toArray(retValue);
		Arrays.sort(retValue, new MInstitucion(ctx, 0, null));
		return retValue;
	}

	/**
 * 
 *
	private static void loadAllInstituciones(Properties ctx) {
		MClient client = MClient.get(ctx);
		MLanguage lang = MLanguage.get(ctx, client.getAD_Language());
		MInstitucion i = null;
		//
		s_institucion = new CCache<String, MInstitucion>("EXME_Institucion", 70);
		String sql = "SELECT * FROM EXME_Institucion WHERE IsActive='Y'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				i = new MInstitucion(ctx, rs, null);
				s_institucion.put(String.valueOf(i.getEXME_Institucion_ID()), i);
			}
			rs.close();
			stmt.close();
			rs = null;
			stmt = null;
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			stmt = null;
			rs = null;
		}
		s_log.fine("#" + s_institucion.size());
	} // loadAllCountries

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MTownCouncil.class);

	/** Institucion Cache */
	private static CCache<String, MInstitucion> s_institucion = null;

	/**
	 * Create Empty Institucion
	 * 
	 * @param ctx
	 * @param EXME_Institucion_ID
	 * @param trxName
	 */
	public MInstitucion(Properties ctx, int EXME_Institucion_ID, String trxName) {
		super(ctx, EXME_Institucion_ID, trxName);
		if (EXME_Institucion_ID == 0) {
		}
	} // MInstitucion

	/**
	 * Create Institucion from current row in ResultSet
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MInstitucion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MMunicipio

	/**
	 *Obtenemos Todas las Instituciones.
	 * 
	 * @param ctx
	 * @param trxName
	 * @param orderBy (opcional)
	 * @return
	 */
	public static MInstitucion[] getInstituciones(Properties ctx, String whereClause, String orderBy, String trxName) {

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
				MInstitucion instituciones = new MInstitucion(ctx, rs, trxName);
				list.add(instituciones);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "closing PreparedStatement", e);
			}
			pstmt = null;
			rs = null;
		}
		MInstitucion[] instituciones = new MInstitucion[list.size()];
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
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "Closing rs and pstmt", e.getMessage());
			}
			pstmt = null;
			rs = null;
		}
		return lstEspec;
	}

}