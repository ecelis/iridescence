package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMERegion extends X_C_Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8953239177607306783L;
	private static CLogger s_log = CLogger.getCLogger(MEXMERegion.class); 
	
	
	public MEXMERegion(Properties ctx, int C_Region_ID, String trxName) {
		super(ctx, C_Region_ID, trxName);
	}

	public MEXMERegion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<LabelValueBean> getKeyDescriptionPair(Properties ctx, int country_ID,
			String trxName) throws Exception {

		List<LabelValueBean> estados = new ArrayList<LabelValueBean>();

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT C_Region_ID, Name||' - '||Description as name ")
           .append("FROM C_Region WHERE C_Country_ID = ")
		   .append(country_ID).append(" AND IsActive='Y' ");

		try {

			String completeQuery = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_Region");

			pstmt = DB.prepareStatement(completeQuery, trxName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				estados.add(new LabelValueBean(rs.getString("name"),String.valueOf(rs.getInt("C_Region_ID"))));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getKeyDescriptionPair", e);
			
			throw new Exception(e.getMessage());

		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
			sql = null;
		}
		return estados;

	}

	/**
	 * 
	 * @param ctx
	 * @param country_ID
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getEstados(Properties ctx, int country_ID)
	throws Exception {
		return MEXMERegion.getEstados(ctx, country_ID, true);
	}
	
	public static List<LabelValueBean> getEstados(Properties ctx, int country_ID, boolean blank)
			throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<LabelValueBean> estados = new ArrayList<LabelValueBean>();

		// verificar que tipo de prod es
		sql.append("SELECT C_Region_ID, Name||' - '||Description as Name FROM C_Region WHERE C_Country_ID = ? AND IsActive='Y' ");

		try {
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_Region"));
			sql.append(" ORDER BY Name ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, country_ID);
			rs = pstmt.executeQuery();
			if(blank){
				estados.add(new LabelValueBean("", "0"));
			}
			while (rs.next()) {
				estados.add(new LabelValueBean(rs.getString("Name"), String.valueOf(rs.getInt("C_Region_ID"))));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getEstados", e);
			
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
			sql = null;
		}

		return estados;
	}
	
	public static int getDefaultRegion(Properties ctx, int country_ID) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT C_Region_ID from C_Region where isDefault = 'Y' and isActive = 'Y' and C_Country_ID = ? ");

		try {

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),"C_Region"));

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, country_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("C_Region_ID");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getDefaultRegion", e);
			
			return 0;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
			sql = null;
		}
		
		
		return 0;

	}
	
	public static MRegion[] getActiveRegions(Properties ctx, int country_ID, String trxName) {
		
		ArrayList<MRegion> list = new ArrayList<MRegion>();
		MRegion[] retVal = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		try {
		
			sql.append("SELECT * from C_Region where isActive = 'Y' and C_Country_ID = ? ");
			sql = 
				new StringBuilder(
						MEXMELookupInfo.addAccessLevelSQL(
								ctx, 
								sql.toString(), 
								"C_Region"
						)
				);
			sql.append(" order by description");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, country_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new MRegion(ctx, rs, trxName));
			}
			
			retVal = new MRegion[list.size()];
			list.toArray(retVal);
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getActiveRegions", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
			sql = null;
		}
		
		return retVal;
		
	}

	/**
	 *  Obtenemos los estados (Region) de un pa�s.
	 *
	 *@param  ctx
	 *@param  country_ID
	 *@return
	 *@throws  Exception
	 */
	public static List<LabelValueBean> get(Properties ctx, int country_ID) 
	throws Exception {

		StringBuilder sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<LabelValueBean> estados = new ArrayList<LabelValueBean>();

		//verificar que tipo de prod es
		sql = new StringBuilder("SELECT C_Region_ID, name || ' - ' ||description as name ")
							   .append("FROM C_Region WHERE ");
							   
		//Noelia: trae el country_id por default si country_id trae cero							 
		if(!(country_ID>0))
			sql.append("IsDefault='Y' ");
		else
			sql.append("C_Country_ID = ? ");
		
		sql.append(	"AND IsActive='Y' ");
		
		sql.append( MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_Region"));

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if(country_ID>0)
				pstmt.setInt(1, country_ID);
			s_log.log(Level.FINEST,"SQL : " + sql.toString() + " -- C_Country_ID = " + country_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				estados.add(new LabelValueBean(rs.getString("name"), String.valueOf(rs.getInt("C_Region_ID"))));
			}

		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
    	}finally {
    		DB.close(rs, pstmt);
    	}
		return estados;
	}
}
