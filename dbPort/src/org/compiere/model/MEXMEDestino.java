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
public class MEXMEDestino extends X_EXME_Destino {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEDestino.class);

	/**
	 * @param ctx
	 * @param EXME_Destino_ID
	 * @param trxName
	 */
	public MEXMEDestino(Properties ctx, int EXME_Destino_ID, String trxName) {
		super(ctx, EXME_Destino_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEDestino(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 * Obtiene un listado de destinos
	 * 
	 * @param ctx
	 * @param trxName
	 * @return List<LabelValueBean>
	 */
	public static List<LabelValueBean> getList(Properties ctx, String trxName){
		
		List<LabelValueBean> retValue = new ArrayList<LabelValueBean>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT EXME_Destino.EXME_Destino_ID, EXME_Destino.Name ")
				.append("FROM EXME_Destino WHERE EXME_Destino.isActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_Destino"));
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString("Name"),rs.getString("EXME_Destino_ID"));
				retValue.add(lvb);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
			sql = null;
		}
		
		return retValue;
	}

}
