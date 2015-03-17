package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MPHRPacienteAler;
import org.compiere.model.X_PHR_EjercicioPac;
import org.compiere.util.CLogger;
import org.compiere.util.DB;


public class MPHREjercicioPac extends X_PHR_EjercicioPac {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6973700564322781400L;
	
	/**
	 * Log
	 */
	private static CLogger log = CLogger.getCLogger (MPHREjercicioPac.class);
	
	/**
	 * Constructor con id
	 * @param ctx
	 * @param PHR_EjercicioPac_ID
	 * @param trxName
	 */
	public MPHREjercicioPac(Properties ctx, int PHR_EjercicioPac_ID, String trxName) {
		super(ctx, PHR_EjercicioPac_ID, trxName);
	}
	
	/**
	 * Constructor con ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHREjercicioPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Regresa una lista de objetos MPHREjercicioPac
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @param active Indica si se requieren los regsitros activos o inactivos
	 * @return Lista de objetos MPHREjercicioPac
	 */
	public static ArrayList<MPHREjercicioPac> getExercisePac(Properties ctx, int EXME_Paciente_ID, String trxName, String active){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MPHREjercicioPac> lstEjercicioPac = new ArrayList<MPHREjercicioPac>();

		try{
			
			sql.append(" SELECT PHR_EJERCICIOPAC.PHR_EJERCICIOPAC_ID " )
			   .append("      , PHR_EJERCICIOPAC.NOMBRE ")
			   .append("      , PHR_EJERCICIOPAC.FECHAINI ")
			   .append("      , PHR_EJERCICIOPAC.FECHAFIN ")
			   .append("   FROM PHR_EJERCICIOPAC ")
			   .append("  WHERE PHR_EJERCICIOPAC.ISACTIVE = ? ")
			   .append("    AND PHR_EJERCICIOPAC.EXME_PACIENTE_ID = ? ");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_EJERCICIOPAC.FECHAINI DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, active);
			pstmt.setInt(2, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				
				lstEjercicioPac.add(new MPHREjercicioPac(ctx, rs.getInt(1), trxName));
				
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
		
		return lstEjercicioPac;
	}
	
	@Override
	public String toString(){
		return "Exercises: "+ getNombre();
	}

}
