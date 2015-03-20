package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPHRAseguradora extends X_PHR_Aseguradora {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1459050214644368914L;
	/**
	 * Log
	 */
	private static CLogger log = CLogger.getCLogger (MPHRAseguradora.class);
	/**
	 * Constructor 
	 * @param ctx
	 * @param PHR_Aseguradora_ID
	 * @param trxName
	 */
	public MPHRAseguradora(Properties ctx, int PHR_Aseguradora_ID, String trxName) {
		super(ctx, PHR_Aseguradora_ID, trxName);
	}

	/**
	 * Regresa una lista de objetos MPHRAseguradora
	 * del paciente pasado como parametro
	 * @return 
	 */
	public static ArrayList<MPHRAseguradora> get(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MPHRAseguradora> lstAseguradora = new ArrayList<MPHRAseguradora>();

		try{
			sql.append(" SELECT PHR_ASEGURADORA.PHR_ASEGURADORA_ID " )
			.append(" FROM PHR_ASEGURADORA ")
			.append(" WHERE PHR_ASEGURADORA.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_ASEGURADORA.UPDATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstAseguradora.add(new MPHRAseguradora(ctx, rs.getInt(1), trxName));
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
		return lstAseguradora;
	}
}
