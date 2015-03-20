package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPHRRecordatorioPac extends X_PHR_RecordatorioPac {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5972731490326306467L;
	
	/**
	 * Log
	 */
	private static CLogger log = CLogger.getCLogger (MPHRRecordatorioPac.class);
	
	/**
	 * Constructor con id
	 * @param ctx
	 * @param PHR_RecordatorioPac_ID
	 * @param trxName
	 */
	public MPHRRecordatorioPac(Properties ctx, int PHR_RecordatorioPac_ID, String trxName) {
		super(ctx, PHR_RecordatorioPac_ID, trxName);
	}
	
	/**
	 * Constructor con ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRRecordatorioPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Regresa una lista de objetos MPHRRecordatorioPac
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @param active Indica si se requieren los regsitros activos o inactivos
	 * @return Lista de objetos MPHRRecordatorioPac
	 */
	public static ArrayList<MPHRRecordatorioPac> getReminderPac(Properties ctx, int EXME_Paciente_ID, String trxName, String active){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MPHRRecordatorioPac> lstRecordatorioPac = new ArrayList<MPHRRecordatorioPac>();

		try{
			
			sql.append(" SELECT PHR_RECORDATORIOPAC.PHR_RECORDATORIOPAC_ID " )
			   .append("      , PHR_RECORDATORIOPAC.NOMBRE ")
			   .append("      , PHR_RECORDATORIOPAC.FECHAINI ")
			   .append("      , PHR_RECORDATORIOPAC.FECHAFIN ")
			   .append("   FROM PHR_RECORDATORIOPAC ")
			   .append("  WHERE PHR_RECORDATORIOPAC.ISACTIVE = ? ")
			   .append("    AND PHR_RECORDATORIOPAC.EXME_PACIENTE_ID = ? ");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_RECORDATORIOPAC.FECHAINI DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, active);
			pstmt.setInt(2, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				
				lstRecordatorioPac.add(new MPHRRecordatorioPac(ctx, rs.getInt(1), trxName));
				
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
		
		return lstRecordatorioPac;
	}
	
	@Override
	public String toString(){
		return "Remeinder: "+ getNombre();
	}
}
