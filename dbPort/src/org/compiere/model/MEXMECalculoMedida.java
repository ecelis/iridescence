package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author odelarosa
 * 
 */
public class MEXMECalculoMedida extends X_EXME_CalculoMedida {
	private static final long serialVersionUID = -7611532449659679281L;
	private static CLogger s_log = CLogger.getCLogger(MEXMECalculoMedida.class);
	private String user = null;

	/**
	 * @param ctx
	 * @param EXME_CalculoMedida_ID
	 * @param trxName
	 */
	public MEXMECalculoMedida(Properties ctx, int EXME_CalculoMedida_ID, String trxName) {
		super(ctx, EXME_CalculoMedida_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECalculoMedida(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene el usuario que lo creo
	 * 
	 * @return
	 */
	public String getUser() {
		if (user == null) {
			MUser u = new MUser(getCtx(), getCreatedBy(), null);
			user = u.getName();
		}
		return user;
	}

	/**
	 * Obtiene todos los calculos de medida almacenados
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECalculoMedida> get(Properties ctx, boolean isInpatient, String trxName) {
		StringBuilder st = new StringBuilder("select * from EXME_CalculoMedida where ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_CalculoMedida"));
		if(isInpatient){
			st.append(" AND ISINPATIENT = 'Y'");
		}else{
			st.append(" AND ISINPATIENT = 'N'");
		}
		st.append(" order by created desc");
		List<MEXMECalculoMedida> lstDiv = new ArrayList<MEXMECalculoMedida>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstDiv.add(new MEXMECalculoMedida(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lstDiv;
	}

}
