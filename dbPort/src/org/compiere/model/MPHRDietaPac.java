package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPHRDietaPac extends X_PHR_DietaPac {
	
	private static CLogger log = CLogger.getCLogger (MPHRAseguradora.class);

	public MPHRDietaPac(Properties ctx, int PHR_DietaPac_ID, String trxName) {
		super(ctx, PHR_DietaPac_ID, trxName);
		
	}

	public MPHRDietaPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	
	public static ArrayList<MPHRDietaPac> get(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MPHRDietaPac> lstDietaPac = new ArrayList<MPHRDietaPac>();

		try{
			sql.append(" SELECT PHR_DIETAPAC.PHR_DIETAPAC_ID " )
			.append(" FROM PHR_DIETAPAC ")
			.append(" WHERE PHR_DIETAPAC.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_DIETAPAC.UPDATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstDietaPac.add(new MPHRDietaPac(ctx, rs.getInt(1), trxName));
			}
		}catch(Exception e){
			log.log(Level.SEVERE, sql.toString(), e);
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
				log.log(Level.SEVERE, "Closing rs and pstmt", e);
				pstmt = null;
				rs = null;
			}
		}
		return lstDietaPac;
	}
}
