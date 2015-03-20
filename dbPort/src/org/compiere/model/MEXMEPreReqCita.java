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

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * @author Lorena Lama
 *
 */
public class MEXMEPreReqCita extends X_EXME_PreReqCita {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEPreReqCita.class);
	/**
	 * @param ctx
	 * @param EXME_PreReqCita_ID
	 * @param trxName
	 */
	public MEXMEPreReqCita(Properties ctx, int EXME_PreReqCita_ID, String trxName) {
		super(ctx, EXME_PreReqCita_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPreReqCita(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	private String comments = null;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	private boolean save = false;

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	/**
	 * Obtenemos los prerrequisitos ya relacionados a esa citamedica 
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param whereclause
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPreReqCita> get(Properties ctx, int EXME_CitaMedica_ID, String whereClause, String trxName) {

		List<MEXMEPreReqCita> retValue = new ArrayList<MEXMEPreReqCita>();
		MEXMEPreReqCita obj = null;
		
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			
			sql.append("SELECT EXME_PreReqCita.*, EXME_PreReqCitaRel.description as reqDesc")
				.append(" FROM EXME_PreReqCitaRel ")
				.append(" INNER JOIN EXME_PreReqCita  ON (EXME_PreReqCitaRel.EXME_PreReqCita_ID = EXME_PreReqCita.EXME_PreReqCita_ID ) ")
				.append(" INNER JOIN EXME_CitaMedica  ON (EXME_PreReqCitaRel.EXME_CitaMedica_ID = EXME_CitaMedica.EXME_CitaMedica_ID ) ")
				.append(" WHERE EXME_PreReqCita.isActive = 'Y' ")
				.append(" AND   EXME_PreReqCitaRel.isActive = 'Y' ")
				.append(" AND   EXME_CitaMedica.EXME_CitaMedica_ID = ? ");
				
				//.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_PreReqCita"));
			
			if(whereClause != null)
				sql.append(whereClause);
			
			sql.append(" ORDER BY EXME_PreReqCita.value ");
			
			psmt = DB.prepareStatement(sql.toString(),null);
			psmt.setInt(1, EXME_CitaMedica_ID);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				obj = new MEXMEPreReqCita(ctx,rs,trxName);
				obj.setComments(rs.getString("reqDesc"));
				obj.setSave(false);
				retValue.add(obj);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get: " + e.getMessage(), e);
		} finally {
			DB.close(rs, psmt);
			psmt = null;
			rs = null;
		}
		
		return retValue;
	}


	

}
