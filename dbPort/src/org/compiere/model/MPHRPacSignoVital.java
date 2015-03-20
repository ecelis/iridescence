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
 * Modelo de la tabla PHR_PacSignoVital
 * @author raul
 *
 */
public class MPHRPacSignoVital extends X_PHR_PacSignoVital {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3707916000388342216L;
	/**
	 * Log
	 */
	private static CLogger s_log = CLogger.getCLogger (MPHRPacSignoVital.class);
	/**
	 * Construye un objeto MPHRPacSignoVital a partir del ID 
	 * @param ctx
	 * @param PHR_PACSIGNOVITALDET_ID
	 * @param trxName
	 */
	public MPHRPacSignoVital(Properties ctx, int PHR_PacSignoVital_ID,
			String trxName) {
		super(ctx, PHR_PacSignoVital_ID, trxName);
	}
	/**
	 * Construye un objeto MPHRPacSignoVital a partir del ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRPacSignoVital(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Regresa los registros en PHR_PacSignoVital
	 * del paciente pasado como parametro en una 
	 * lista de objetos MPHRPacSignoVital
	 * @return 
	 */
	public static List<MPHRPacSignoVital> get(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MPHRPacSignoVital> lstVitalSigns = new ArrayList<MPHRPacSignoVital>();

		try{
			sql.append(" SELECT PHR_PACSIGNOVITAL.PHR_PACSIGNOVITAL_ID " )
			.append(" FROM PHR_PACSIGNOVITAL ")
			.append(" WHERE PHR_PACSIGNOVITAL.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_PACSIGNOVITAL.FECHA ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstVitalSigns.add(new MPHRPacSignoVital(ctx, rs.getInt(1), trxName));
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
		return lstVitalSigns; 
	}
	/**
	 * Regresa una lista de objetos MPHRPacSignoVitalDet
	 * relacionados al objeto
	 * @return 
	 */
	public List<MPHRPacSignoVitalDet> getDetail (){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MPHRPacSignoVitalDet> lstDetail = new ArrayList<MPHRPacSignoVitalDet>();

		try{
			sql.append(" SELECT PHR_PACSIGNOVITALDET.PHR_PACSIGNOVITALDET_ID " )
			.append(" FROM PHR_PACSIGNOVITALDET ")
			.append(" WHERE PHR_PACSIGNOVITALDET.PHR_PACSIGNOVITAL_ID = ? ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), MPHRPacSignoVitalDet.Table_Name));
			sql.append(" ORDER BY PHR_PACSIGNOVITALDET.UPDATED DESC ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getPHR_PacSignoVital_ID());
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstDetail.add(new MPHRPacSignoVitalDet(getCtx(), rs.getInt(1), get_TrxName()));
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
}
