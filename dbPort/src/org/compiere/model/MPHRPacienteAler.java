package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPHRPacienteAler extends X_PHR_PacienteAler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006290870910660572L;
	/**
	 * Log
	 */
	private static CLogger log = CLogger.getCLogger (MPHRPacienteAler.class);
	
	/**
	 * Constructor con id
	 * @param ctx
	 * @param PHR_MedicoPac_ID
	 * @param trxName
	 */
	public MPHRPacienteAler(Properties ctx, int PHR_PacienteAler_ID, String trxName) {
		super(ctx, PHR_PacienteAler_ID, trxName);
	}
	
	/**
	 * Constructor con ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRPacienteAler(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Regresa una lista de objetos MPHRPacienteAler
	 * del paciente pasado como parametro
	 * @return 
	 */
	public static ArrayList<MPHRPacienteAler> getPacienteAler(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MPHRPacienteAler> lstPacienteAler = new ArrayList<MPHRPacienteAler>();

		try{
			sql.append(" SELECT PHR_PACIENTEALER.PHR_PACIENTEALER_ID " )
			.append(" FROM PHR_PACIENTEALER ")
			.append(" WHERE PHR_PACIENTEALER.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_PACIENTEALER.UPDATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstPacienteAler.add(new MPHRPacienteAler(ctx, rs.getInt(1), trxName));
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
		return lstPacienteAler;
	}
	
	@Override
	public String toString(){
		return "Doctor:"+getEXME_Alergia().getDescription();
	}
	
}
