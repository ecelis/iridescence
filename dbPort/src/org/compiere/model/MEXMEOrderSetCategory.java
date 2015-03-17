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

import com.ecaresoft.api.Generic;

public class MEXMEOrderSetCategory extends X_EXME_OrderCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger(MEXMEOrderSetCategory.class);

	public MEXMEOrderSetCategory(Properties ctx, int EXME_OrderCategory_ID,
			String trxName) {
		super(ctx, EXME_OrderCategory_ID, trxName);
	}

	public MEXMEOrderSetCategory(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Obtiene todos los order set
	 * @param ctx
	 * @param exmeMedicoID
	 * @param isActive
	 * @return
	 */
	public static List<LabelValueBean> getCategories(final Properties ctx, String isActive){

		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			sql.append("SELECT EXME_OrderCategory.EXME_OrderCategory_ID, EXME_OrderCategory.Name ")
				.append(" FROM EXME_OrderCategory ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," WHERE ",Table_Name));
			if(isActive!=null && isActive.equals("Y")){
				sql.append(" AND EXME_OrderCategory.isActive = 'Y' ");
			}

			sql.append(" ORDER BY EXME_OrderCategory.Name ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			result = pstmt.executeQuery();
			while(result.next()){
				list.add (new LabelValueBean( result.getString("Name"), String.valueOf(result.getInt("EXME_OrderCategory_ID"))) ) ;
			} 
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return list;
	}
	
	public static List<Generic> getLstCategories(final Properties ctx, String isActive){

		final List<Generic> list = new ArrayList<Generic>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			sql.append("SELECT EXME_OrderCategory.EXME_OrderCategory_ID, EXME_OrderCategory.Name ")
				.append(" FROM EXME_OrderCategory ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," WHERE ",Table_Name));
			if(isActive!=null && isActive.equals("Y")){
				sql.append(" AND EXME_OrderCategory.isActive = 'Y' ");
			}

			sql.append(" ORDER BY EXME_OrderCategory.Name ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			result = pstmt.executeQuery();
			while(result.next()){
				list.add (new Generic( result.getString("Name"), String.valueOf(result.getInt("EXME_OrderCategory_ID"))) ) ;
			} 
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		} finally {
			DB.close(result,pstmt);
		}

		return list;
	}

	
}
