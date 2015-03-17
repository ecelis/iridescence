package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo para la tabla de dietas capturadas por
 * el paciente
 * @author raul
 *
 */
public class MEXMEPacDieta extends X_EXME_PacDieta {
	
	/*serialVersionUID*/
	private static final long serialVersionUID = 5758836490207328412L;
	
	/*Log*/
	private static CLogger s_log = CLogger.getCLogger(MEXMEPacDieta.class);
	/**
	 * Constructor que recibe un EXME_PacDieta_ID
	 * @param ctx
	 * @param EXME_PacDieta_ID
	 * @param trxName
	 */
	public MEXMEPacDieta(Properties ctx, int EXME_PacDieta_ID, String trxName) {
		super(ctx, EXME_PacDieta_ID, trxName);
	}
	
	/**
	 * Constructor que recibe un ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPacDieta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	/**
	 * Regresa una lista de objetos MEXMEPacDieta
	 * que son las dietas capturadas por el paciente
	 * cuyo ID sea el pasado como parametro
	 * @param ctx
	 * @param pacienteId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPacDieta> getPacientDiets(Properties ctx, int pacienteId,boolean derechoHabiente, String trxName){
		ArrayList<MEXMEPacDieta> lstPacDiet = new ArrayList<MEXMEPacDieta>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT EXME_PacDieta.* FROM EXME_PacDieta ")
		.append(" WHERE EXME_PacDieta.IsActive='Y' ")
		.append(" AND EXME_PacDieta.EXME_Paciente_ID = ? ");
		//Card #1545 ProMujer 
		if (Env.getUserPatientID(ctx) < 0 && !derechoHabiente) {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_PacDieta"));
		} else if (derechoHabiente) {
			sql.append(MClientInfo.getClientSQL(ctx, "EXME_PacDieta"));
		}

		sql.append(" ORDER BY EXME_PacDieta.Name ");

		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameter(pstmt, 1, pacienteId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEPacDieta obj = new MEXMEPacDieta(ctx, rs, trxName);          	
				lstPacDiet.add(obj);
			}
			
		}catch(Exception e){
			s_log.log(Level.SEVERE, "getPacientDiets", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lstPacDiet;
	}

}
