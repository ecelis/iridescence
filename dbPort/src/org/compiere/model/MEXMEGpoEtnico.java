/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;

/**
 * @author mrojas
 *
 */
public class MEXMEGpoEtnico extends X_EXME_GpoEtnico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2553207763585049694L;
	private static CLogger logger = CLogger.getCLogger(MEXMEGpoEtnico.class);
	
	/**
	 * @param ctx
	 * @param EXME_GpoEtnico_ID
	 * @param trxName
	 */
	public MEXMEGpoEtnico(Properties ctx, int EXME_GpoEtnico_ID, String trxName) {
		super(ctx, EXME_GpoEtnico_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEGpoEtnico(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get all the Value / Identifier pairs for the EXME_GpoEtnico table.
	 * @param ctx The application context.
	 * @param onlyActives Returns active only or all.
	 * @return A HashMap of String/Integer values.
	 */
	public static HashMap<String, Integer> getValueId(Properties ctx, boolean onlyActives) {
		HashMap<String, Integer> retVal = null;
		
		StringBuffer sql = new StringBuffer("SELECT EXME_GpoEtnico_ID, Value FROM EXME_GpoEtnico");
		
		if(onlyActives) {
			sql.append(" WHERE IsActive = 'Y'");
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			rs = pstmt.executeQuery();
			
			retVal = new HashMap<String, Integer>();
			
			while(rs.next()) {
				retVal.put(rs.getString(2), rs.getInt(1));
			}
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "", e);
		}
		
		return retVal;
	}
	
	public static List<MEXMEGpoEtnico> getAll(Properties ctx, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEGpoEtnico> list = new ArrayList<MEXMEGpoEtnico>();

		try{
			sql.append("SELECT EXME_GPOETNICO.EXME_GPOETNICO_ID" )
			.append(" FROM EXME_GPOETNICO")
			.append(" WHERE EXME_GPOETNICO.ISACTIVE = 'Y' ")
			.append(" ORDER BY EXME_GPOETNICO.NAME ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while(rs.next()){
				list.add(new MEXMEGpoEtnico(ctx, rs.getInt(1), trxName));
			}
		}catch(Exception e){
			logger.log(Level.SEVERE, sql.toString(), e);
		}finally{
			DB.close(rs, pstmt);
		} 
		return list; 
	
	}
	
	public static MEXMEGpoEtnico getDefault(Properties ctx){
		MEXMEGpoEtnico gpoEtc =  new Query(ctx, Table_Name, " value = 'U'", null)//
		.setOnlyActiveRecords(true)
		.first();		
		return gpoEtc;
	}
	
	public static MEXMEGpoEtnico get(Properties ctx,String where){
		MEXMEGpoEtnico gpoEtc =  new Query(ctx, Table_Name, where, null)//
		.setOnlyActiveRecords(true)
		.first();		
		return gpoEtc;
	}
	
	/**
	 * 
	 * @return Lista con los grupos etnicos disponibles
	 */
	public static List<LabelValueBean> getGpoEtnicos(Properties ctx, boolean addEmpty) {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		if(addEmpty){
			list.add(new LabelValueBean(Utilerias.getLabel("msg.decliced.to.specify"), "0"));
		}
		final List<MEXMEGpoEtnico> mexmeList = MEXMEGpoEtnico.getAll(ctx, null);
		for (MEXMEGpoEtnico mexme : mexmeList) {
			list.add(new LabelValueBean(mexme.getName(), String.valueOf(mexme.getEXME_GpoEtnico_ID())));
		}
		return list;
	}

}
