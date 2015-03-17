package org.compiere.model;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;


public class MEXME_C_CampaignPA extends X_EXME_C_CampaignPa{
	private static CLogger		slog = CLogger.getCLogger (MEXME_C_CampaignPA.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MEXME_C_CampaignPA(Properties ctx, int EXME_C_CampaignPA_ID,
			String trxName) {
		super(ctx, EXME_C_CampaignPA_ID, trxName);
	}
	
	public MEXME_C_CampaignPA(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	// METODO PARA TRAER LAS LINEAS
	public List<MEXME_C_CampaignPA> getCampaignsData(int campaign)
	{
		//MCampaign resulset_campaign[];
		List<MEXME_C_CampaignPA> list = new ArrayList<MEXME_C_CampaignPA>();
		String sql="SELECT * FROM EXME_C_CAMPAIGNPA WHERE c_campaign_id=? AND ISACTIVE='Y'";
//		String sql_query="SELECT NAME,EXME_C_CAMPAIGNPA_ID,AD_ORGTRX_ID,PRE_SOLICITADO,exme_partida_id FROM EXME_C_CAMPAIGNPA WHERE c_campaign_id="+campaign+" AND ISACTIVE='Y'";
		//System.out.println("el query para partidas: "+sql_query);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.clearParameters();
			pstmt.setInt(1, campaign);			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				list.add(new MEXME_C_CampaignPA(getCtx(),rs,null));		
			}
			
		}catch(SQLException e)
		{
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		//resulset_campaign = new MCampaign[list.size()];
		//list.to
		return list;
	}
	
	public String getAutorizado (int campaign,int partidaID, int adorgtrx)
	{
		String autorizado=null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql2="SELECT PRE_AUTORIZADO FROM EXME_C_CAMPAIGNPA WHERE c_campaign_id=? AND EXME_PARTIDA_ID=? AND AD_ORGTRX_ID=? AND ISACTIVE='Y'";
		
		try{
			
			pstmt = DB.prepareStatement(sql2, get_TrxName());
			pstmt.clearParameters();
			pstmt.setInt(1,campaign);
			pstmt.setInt(2,partidaID);
			pstmt.setInt(3,adorgtrx);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				//System.out.println("Obteniendo presupuesto: ");
				autorizado=rs.getString(1);
			}
			
		}catch(SQLException e)
		{
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}
		if(autorizado==null)
			autorizado="0.0";
		
		return autorizado;
	}
	/**
	 * SUMA EL DEL PRESUPUESTO PRE_AUTORIZADO DE UN PROGRAMA
	 * @param campaign campaign_id
	 * @param partida partida_id
	 * @return BigDecimal Suma de Presupuesto
	 * @throws SQLException 
	 */
	public static  BigDecimal getSumPreAutorizadoxPartida(int campaign,int partidaID) throws SQLException
	{
		BigDecimal total=null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="SELECT SUM (pre_autorizado) " +
		"FROM exme_c_campaignpa " +
		"WHERE c_campaign_id="+campaign+ 
		"AND exme_partida_id ="+partidaID;
		
		try{
			
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while(rs.next()){
				//System.out.println("Obteniendo presupuesto: ");
				total=rs.getBigDecimal(1);
			}
			
		}catch(NullPointerException e)
		{
			total=BigDecimal.ZERO;
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}
			return total;
	}
}
