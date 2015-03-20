/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Modelo del Cat�logo de Colonias y Codigos Postales
 * 
 * @author eruiz
 * 
 */
public class MEXMEColonia extends X_EXME_Colonia {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEColonia.class);

	/**
	 * @param ctx
	 * @param EXME_Colonia_ID
	 * @param trxName
	 */

	public MEXMEColonia(Properties ctx, int EXME_Colonia_ID, String trxName) {
		super(ctx, EXME_Colonia_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEColonia(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene EL PRIMER REGISTRO de EXME_Colonia apartir de ese su codigo postal
	 * @deprecated Es arriesgado obtener la colonia de esta forma, puede haber mas de una colonia con el mismo codigo postal
	 * es mas recomendable obtener la colonia apartir de su id
	 * @param ctx
	 * @param codigoPostal
	 * @param trxName
	 * @return
	 */
	public static MEXMEColonia getFromValue(Properties ctx, int codigoPostal, String trxName) {
		MEXMEColonia retVal = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT * FROM EXME_Colonia ")
				.append("WHERE EXME_Colonia.Codigo_Postal = ? ")
				.append("AND EXME_Colonia.isActive = 'Y' ");
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setString(1, String.valueOf(codigoPostal));

			rs = psmt.executeQuery();

			if (rs.next()) {
				retVal = new MEXMEColonia(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,psmt);
			rs = null;
			psmt = null;
			sql = null;
		}

		return retVal;
	}
	
	
	private MCountry m_country = null;
	private MRegion m_region = null;
	private MTownCouncil m_town = null;
	private String country = null;
	private String region = null;
	private String town = null;
	
	/**
	 * Get Country / Pa�s
	 * @return country
	 */
	public MCountry getCountry() {
		if (m_country == null) {
			if (getC_Country_ID() != 0)
				m_country = MCountry.get(getCtx(), getC_Country_ID());
			else
				m_country = MCountry.getDefault(getCtx());
		}
		return m_country;
	}
	
	/**
	 * Get Region / Estado
	 * @return region
	 */
	public MRegion getRegion() {
		if (m_region == null && getC_Region_ID() != 0)
			m_region = MRegion.get(getCtx(), getC_Region_ID());
		return m_region;
	}
	
	/**
	 * Get Town Council / Municipio
	 * @return townCouncil
	 */
	public MTownCouncil getTownCouncil() {
		if (m_town == null && getEXME_TownCouncil_ID() != 0)
			m_town = MTownCouncil.get(getCtx(), getEXME_TownCouncil_ID());
		return m_town;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegionName() {
		return region;
	}
	public String getCountryName() {
		return country;
	}
	
	/**
	 * 
	 * @param ctx The environment context.
	 * 
	 * @param where Query conditions starting every single one of them with "AND"
	 * instead of "WHERE".
	 * 
	 * @param maxRowNum Specifies to the database the maximum number of rows that we want to
	 * get (to get a faster results use lower values).
	 * 
	 * @param lst The matching list of values for the query parameters to get filled.
	 * 
	 * @return a List<MEXMEColonia> 
	 * 
	 * @author David Nuncio. modified by @Lorena Lama
	 * @date 3/12/2010
	 */
	public static List<MEXMEColonia> get(Properties ctx, String where, int maxRowNum, List<String> lst){

		final List<MEXMEColonia> list = new ArrayList<MEXMEColonia>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT EXME_Colonia.*, cou.name as pais, reg.description as estado, ")
		.append(" tc.name as municipio ")
		.append(" FROM EXME_Colonia ")
		.append(" INNER JOIN exme_towncouncil tc on EXME_Colonia.exme_towncouncil_id = tc.exme_towncouncil_id ")
		.append(" INNER JOIN c_region reg on EXME_Colonia.c_region_id = reg.c_region_id ")
		.append(" INNER JOIN c_country cou on EXME_Colonia.c_country_id = cou.c_country_id ")
		.append(" WHERE EXME_Colonia.isactive = 'Y' ");

		if(StringUtils.isNotEmpty(where)){
			sql.append(where);
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEColonia.Table_Name));
		
		if (DB.isOracle()) {
			sql.append(" AND rownum <= " + maxRowNum);
		} else if(DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, maxRowNum));
		}

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (lst != null && !lst.isEmpty()) {
				DB.setParameters(pstmt, lst);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				final MEXMEColonia retVal = new MEXMEColonia(ctx, rs, null);
				retVal.setTown(rs.getString("municipio"));
				retVal.setRegion(rs.getString("estado"));
				retVal.setCountry(rs.getString("pais"));
				list.add(retVal);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEColonia.get - sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
}

