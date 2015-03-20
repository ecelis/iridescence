package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPHRMedicoPac extends X_PHR_MedicoPac {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7531503170846026530L;
	/**
	 * Log
	 */
	private static CLogger log = CLogger.getCLogger (MPHRMedicoPac.class);
	
	/**
	 * Constructor con id
	 * @param ctx
	 * @param PHR_MedicoPac_ID
	 * @param trxName
	 */
	public MPHRMedicoPac(Properties ctx, int PHR_MedicoPac_ID, String trxName) {
		super(ctx, PHR_MedicoPac_ID, trxName);
	}
	
	/**
	 * Constructor con ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRMedicoPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Regresa una lista de objetos MPHRMedicoPac
	 * del paciente pasado como parametro
	 * @return 
	 */
	public static ArrayList<MPHRMedicoPac> get(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MPHRMedicoPac> lstMedicoPac = new ArrayList<MPHRMedicoPac>();

		try{
			sql.append(" SELECT PHR_MEDICOPAC.PHR_MEDICOPAC_ID " )
			.append(" FROM PHR_MEDICOPAC ")
			.append(" WHERE PHR_MEDICOPAC.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_MEDICOPAC.UPDATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstMedicoPac.add(new MPHRMedicoPac(ctx, rs.getInt(1), trxName));
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
		return lstMedicoPac;
	}
	
	@Override
	public String toString(){
		return "Doctor:"+getEXME_Medico().getNombre_Med()+ "- Patient:"+ getEXME_Paciente().getNombre_Pac();
	}


	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		boolean retValue = true;
		
		//Solo puede existir un registro marcado como principal
		if(isEsPrincipal() && success){
			StringBuilder sql = new StringBuilder();
				sql.append(" UPDATE PHR_MEDICOPAC SET ESPRINCIPAL = 'N' ")
						.append(" WHERE PHR_MEDICOPAC_ID <> ? AND ESPRINCIPAL = 'Y' ")
						.append(" AND EXME_PACIENTE_ID = ? ");
				
				Object[] params = {getPHR_MedicoPac_ID(), getEXME_Paciente_ID()};
				DB.executeUpdateEx(sql.toString(), params, get_TrxName());
		}
		return retValue;
	}
	

}
