package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPHRContacto extends X_PHR_Contacto {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4270242229377763348L;
	/**
	 * Log
	 */
	private static CLogger log = CLogger.getCLogger (MPHRContacto.class);
	/**
	 * Constructor ID
	 * @param ctx
	 * @param PHR_Contacto_ID
	 * @param trxName
	 */
	public MPHRContacto(Properties ctx, int PHR_Contacto_ID, String trxName) {
		super(ctx, PHR_Contacto_ID, trxName);
	}
	/**
	 * Constructor ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRContacto(Properties ctx, ResultSet rs, String trxName){
		super(ctx, rs, trxName);
	}
	
	/**
	 * Regresa una lista de objetos MPHRContacto
	 * del paciente pasado como parametro
	 * @return 
	 */
	public static ArrayList<MPHRContacto> get(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MPHRContacto> lstContacto = new ArrayList<MPHRContacto>();

		try{
			sql.append(" SELECT PHR_CONTACTO.PHR_CONTACTO_ID " )
			.append(" FROM PHR_CONTACTO ")
			.append(" WHERE PHR_CONTACTO.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_CONTACTO.UPDATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstContacto.add(new MPHRContacto(ctx, rs.getInt(1), trxName));
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
		return lstContacto; 
	}
	/**
	 * El objeto MLocation del contacto
	 * o null si no tiene C_Location_ID
	 * @return
	 */
	public MLocation getC_Location(){
		MLocation location = null;
		if(getC_Location_ID() > 0){
			location = new MLocation(getCtx(),getC_Location_ID(), get_TrxName());
		} 
		return location;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		boolean retValue = true;
		
		//Solo puede existir un registro marcado como principal
		if(isEsPrincipal() && success){
			StringBuilder sql = new StringBuilder();
				sql.append(" UPDATE PHR_CONTACTO SET ESPRINCIPAL = 'N' ")
						.append(" WHERE PHR_CONTACTO_ID <> ? AND ESPRINCIPAL = 'Y' ")
						.append(" AND EXME_PACIENTE_ID = ? ");
				
				Object[] params = {getPHR_Contacto_ID(), getEXME_Paciente_ID()};
				DB.executeUpdateEx(sql.toString(), params, get_TrxName());
		}
		return retValue;
	}
	
	@Override
	protected boolean afterDelete(boolean success) {
		//Borrar la direccion 
		if(success){
			if(getC_Location() != null){
				return getC_Location().delete(false, get_TrxName());
			}
		}
		return true;
	}

}
