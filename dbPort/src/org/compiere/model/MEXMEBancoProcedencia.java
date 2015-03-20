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

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author LLama
 *
 */
public class MEXMEBancoProcedencia extends X_EXME_BancoProcedencia {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEBancoProcedencia.class);
	
	/**
	 * @param ctx
	 * @param EXME_BancoProcedencia_ID
	 * @param trxName
	 */
	public MEXMEBancoProcedencia(Properties ctx, int EXME_BancoProcedencia_ID, String trxName) {
		super(ctx, EXME_BancoProcedencia_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEBancoProcedencia(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene un listado de bancos de procedencias
	 * 
	 * @param ctx
	 * @param trxName
	 * @return List<LabelValueBean>
	 */
	public static List<LabelValueBean> getList(Properties ctx, String trxName){
		
		List<LabelValueBean> retValue = new ArrayList<LabelValueBean>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT EXME_BancoProcedencia.EXME_BancoProcedencia_ID, EXME_BancoProcedencia.Name ")
				.append("FROM EXME_BancoProcedencia WHERE EXME_BancoProcedencia.isActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_BancoProcedencia"));
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString("Name"),rs.getString("EXME_BancoProcedencia_ID"));
				retValue.add(lvb);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,psmt);
			psmt = null;
			rs =null;
			sql = null;
		}
		
		return retValue;
	}

}
