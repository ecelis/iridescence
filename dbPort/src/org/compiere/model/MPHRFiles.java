package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPHRFiles extends X_PHR_Files {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3655617479082688346L;
	/**
	 * Log
	 */
	private static CLogger s_log = CLogger.getCLogger (MPHRFiles.class);
	
	public MPHRFiles(Properties ctx, int PHR_Files_ID, String trxName) {
		super(ctx, PHR_Files_ID, trxName);
	}
	
	public MPHRFiles(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Regresa una lista de objetos MPHRFiles
	 * del paciente pasado como parametro
	 * @return 
	 */
	public static List<MPHRFiles> get(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MPHRFiles> lstFiles = new ArrayList<MPHRFiles>();

		try{
			sql.append(" SELECT PHR_FILES.PHR_FILES_ID " )
			.append(" FROM PHR_FILES ")
			.append(" WHERE PHR_FILES.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_FILES.UPDATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstFiles.add(new MPHRFiles(ctx, rs.getInt(1), trxName));
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, sql.toString(), e);
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				rs = null;
				pstmt = null;
			}catch(Exception e){
				s_log.log(Level.SEVERE, "Closing rs and pstmt", e);
				pstmt = null;
				rs = null;
			}
		}
		return lstFiles;
	}
}
