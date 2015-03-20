package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPHRSurgeries extends X_PHR_Surgeries {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5984304819413498115L;
	/**
	 * Log
	 */
	private static CLogger s_log = CLogger.getCLogger (MPHRSurgeries.class);
	
	public MPHRSurgeries(Properties ctx, int PHR_Surgeries_ID, String trxName) {
		super(ctx, PHR_Surgeries_ID, trxName);
	}
	public MPHRSurgeries(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Regresa una lista de objetos MPHRSurgeries
	 * del paciente pasado como parametro
	 * @return 
	 */
	public static List<MPHRSurgeries> get(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MPHRSurgeries> lstAcceso = new ArrayList<MPHRSurgeries>();

		try{
			sql.append(" SELECT PHR_SURGERIES.PHR_SURGERIES_ID " )
			.append(" FROM PHR_SURGERIES ")
			.append(" WHERE PHR_SURGERIES.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_SURGERIES.UPDATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstAcceso.add(new MPHRSurgeries(ctx, rs.getInt(1), trxName));
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
	 * Regresa una lista de objetos MPHRSurgeriesDet
	 * @return 
	 */
	public List<MPHRSurgeriesDet> getDetail(){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MPHRSurgeriesDet> lstDetail = new ArrayList<MPHRSurgeriesDet>();

		try{
			sql.append(" SELECT PHR_SURGERIESDET.PHR_SURGERIESDET_ID " )
			.append(" FROM PHR_SURGERIESDET ")
			.append(" WHERE PHR_SURGERIESDET.PHR_SURGERIES_ID = ? ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), MPHRSurgeriesDet.Table_Name));
			sql.append(" ORDER BY PHR_SURGERIESDET.UPDATED DESC ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getPHR_Surgeries_ID());
			rs = pstmt.executeQuery();

			while(rs.next()){
				lstDetail.add(new MPHRSurgeriesDet(getCtx(), rs.getInt(1), get_TrxName()));
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
	public void setKnowYear(int uNKNOW_YEAR) {
		// TODO Auto-generated method stub FIXME
	}
	public void setMoreThanOnce(int nOT_SURE) {
		// TODO Auto-generated method stub FIXME
	}
	public int getMoreThanOnce() {
		// TODO Auto-generated method stub FIXME
		return 0;
	}
	public int getKnowYear() {
		// TODO Auto-generated method stub FIXME
		return 0;
	}
	public void setFecha(Timestamp timestamp) {
		// TODO Auto-generated method stub FIXME
	}
	public Date getFecha() {
		// TODO Auto-generated method stub FIXME
		return null;
	}
	

}
