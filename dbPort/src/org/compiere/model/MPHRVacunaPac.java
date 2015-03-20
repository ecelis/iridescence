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

public class MPHRVacunaPac extends X_PHR_VacunaPac {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -383607161394369306L;
	private static CLogger s_log = CLogger.getCLogger (MPHRVacunaPac.class);

	public MPHRVacunaPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MPHRVacunaPac(Properties ctx, int PHR_VacunaPac_ID, String trxName) {
		super(ctx, PHR_VacunaPac_ID, trxName);
	}


	/**
	 * Regresa una lista de objetos MPHRVacunaPac
	 * del paciente pasado como parametro
	 * @return 
	 */
	public static List<MPHRVacunaPac> get(Properties ctx, int EXME_Paciente_ID, String trxName){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MPHRVacunaPac> lst = new ArrayList<MPHRVacunaPac>();

		try{
			sql.append(" SELECT PHR_VACUNAPAC.PHR_VACUNAPAC_ID " )
			.append(" FROM PHR_VACUNAPAC ")
			.append(" WHERE PHR_VACUNAPAC.EXME_PACIENTE_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY PHR_VACUNAPAC.UPDATED DESC ");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while(rs.next()){
				lst.add(new MPHRVacunaPac(ctx, rs.getInt(1), trxName));
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
		return lst; 
	}
	
	/**
	 * Regresa una lista de objetos MPHRVacunaPacDet
	 * @return 
	 */
	public List<MPHRVacunaPacDet> getDetail(){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MPHRVacunaPacDet> lst = new ArrayList<MPHRVacunaPacDet>();

		try{
			sql.append(" SELECT PHR_VACUNAPACDET.PHR_VACUNAPACDET_ID " )
			.append(" FROM PHR_VACUNAPACDET ")
			.append(" WHERE PHR_VACUNAPACDET.PHR_VACUNAPAC_ID = ? ");
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), MPHRVacunaPacDet.Table_Name));
			sql.append(" ORDER BY PHR_VACUNAPACDET.FECHA ");
			
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getPHR_VacunaPac_ID());
			rs = pstmt.executeQuery();

			while(rs.next()){
				lst.add(new MPHRVacunaPacDet(getCtx(), rs.getInt(1), get_TrxName()));
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
		return lst; 
	}

	public void setMoreThanOnce(int nOT_MORE_THAN_ONE) {
		// TODO Auto-generated method stub // FIXME
	}

	public Date getFecha() {
		// TODO Auto-generated method stub FIXME
		return null;
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

	public void setKnowYear(int i) {
		// TODO Auto-generated method stub FIXME
	}
}
