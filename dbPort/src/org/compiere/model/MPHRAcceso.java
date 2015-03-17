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
 * Modelo de la tabla de accesos al Expediente Personal
 * @author raul 23/07/2010
 *
 */
public class MPHRAcceso extends X_PHR_Acceso {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2355817000430095912L;
	/**
	 * Log
	 */
	private static CLogger s_log = CLogger.getCLogger (MPHRAcceso.class);
	/**
	 * Construye un objeto MPHRAcceso a partir del ID
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRAcceso(Properties ctx, int PHR_Acceso_ID, String trxName) {
		super(ctx, PHR_Acceso_ID, trxName);
	}
	/**
	 * Construye un objeto MPHRAcceso a partir del ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRAcceso(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Regresa una lista de objetos MPHRAcceso
	 * del paciente pasado como parametro
	 * @return 
	 */
	public static List<MPHRAcceso> get(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MPHRAcceso> lstAcceso = new ArrayList<MPHRAcceso>();

		try{
			sql.append(" SELECT PHR_ACCESO.PHR_ACCESO_ID " )
			.append(" FROM PHR_ACCESO ")
			.append(" WHERE PHR_ACCESO.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_ACCESO.UPDATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstAcceso.add(new MPHRAcceso(ctx, rs.getInt(1), trxName));
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
		return lstAcceso; 
	}
	
	
	/**
	 * Regresa una lista de objetos MPHRAccesoDet
	 * @return 
	 */
	public List<MPHRAccesoDet> getDetail(){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MPHRAccesoDet> lstDetail = new ArrayList<MPHRAccesoDet>();

		try{
			sql.append(" SELECT PHR_ACCESODET.PHR_ACCESODET_ID " )
			.append(" FROM PHR_ACCESODET ")
			.append(" WHERE PHR_ACCESODET.PHR_ACCESO_ID = ? ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), MPHRAccesoDet.Table_Name));
			sql.append(" ORDER BY PHR_ACCESODET.UPDATED DESC ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getPHR_Acceso_ID());
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstDetail.add(new MPHRAccesoDet(getCtx(), rs.getInt(1), get_TrxName()));
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
		return lstDetail; 
	}
	
	/**
	 * Regresa una lista de medicos que no tengan configuracion de acceso
	 * y el usuario los tenga configurados como medicos que lo estan atendiendo
	 * @return 
	 */
	public static List<MPHRMedicoPac> getDoctorsWithoutAccessYet(Properties ctx, int patientId, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MPHRMedicoPac> lstMedicos = new ArrayList<MPHRMedicoPac>();

		try{
			sql.append(" SELECT PHR_MEDICOPAC.PHR_MEDICOPAC_ID FROM PHR_MEDICOPAC PHR_MEDICOPAC ")
			.append(" WHERE PHR_MEDICOPAC.EXME_MEDICO_ID NOT IN (SELECT EXME_MEDICO_ID  FROM PHR_ACCESO WHERE EXME_PACIENTE_ID = ?) ")
			.append(" AND PHR_MEDICOPAC.EXME_PACIENTE_ID = ? " );

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MPHRMedicoPac.Table_ID));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, patientId);
			pstmt.setInt(2, patientId);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstMedicos.add(new MPHRMedicoPac(ctx, rs.getInt(1), trxName));
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
		return lstMedicos; 
	}
	
	public List<String> getDetailValues(){
		List<String> lstDetail = new ArrayList<String>();
		for(MPHRAccesoDet detail : getDetail()){
			lstDetail.add(detail.getAccessLevel());
		}
		return lstDetail;
	}
}
