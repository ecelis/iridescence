/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;

import org.compiere.model.MSequence.GetIDs;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GenericModel;
import org.compiere.util.OptionItem;
import org.compiere.util.Trx;


/**
 *	Campaign model
 *	
 *  @author Jorg Janke
 *  @version $Id: MCampaign.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MCampaign extends X_C_Campaign
{

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Campaign_ID id
	 *	@param trxName transaction
	 */
	public MCampaign (Properties ctx, int C_Campaign_ID, String trxName)
	{
		super (ctx, C_Campaign_ID, trxName);
	}	//	MCampaign

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MCampaign (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCampaign
	
	/**
	 * 	After Save.
	 * 	Insert
	 * 	- create tree
	 *	@param newRecord insert
	 *	@param success save success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		if (newRecord)
			insert_Tree(MTree_Base.TREETYPE_Campaign);
		//	Value/Name change
		if (!newRecord && (is_ValueChanged("Value") || is_ValueChanged("Name")))
			MAccount.updateValueDescription(getCtx(), "C_Campaign_ID=" + getC_Campaign_ID(), get_TrxName());

		return true;
	}	//	afterSave

	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	protected boolean afterDelete (boolean success)
	{
		if (success)
			delete_Tree(MTree_Base.TREETYPE_Campaign);
		return success;
	}	//	afterDelete
	
	
	
	public Vector<GenericModel> getCampaignsData(int ad_client)
	{
		//MCampaign resulset_campaign[];
		Vector<GenericModel> list = new Vector<GenericModel>();
		String sql="SELECT DISTINCT CC.c_campaign_id,cc.documentno as DOCUMENT_NO,cc.name as Campaign,cc.description as description,"
                     + "cc.pre_solicitado as PRESUPUESTO_SOLITADO, cc.pre_autorizado as PRESUPUESTO_AUTORIZADO, gb.name as BUDGET,cc.istransfered,cc.isapproved FROM C_CAMPAIGN CC,GL_BUDGET GB"
                     +" WHERE CC.gl_budget_id = gb.Gl_budget_id and cc.istransfered = 'N' ORDER BY CC.c_campaign_id DESC";
		//System.out.println("query para lista: "+sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			GenericModel part = null;
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.clearParameters();				
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
			part=new GenericModel();
			part.setId_campaign(rs.getString(1));
			part.setNo_documento(rs.getString(2));
			part.setNombre(rs.getString(3));
			part.setDescription(rs.getString(4));
			part.setSolicitado(rs.getString(5));
			part.setPre_Solicitado(rs.getString(6));
			part.setBudget_name(rs.getString(7));
			part.setTransfered(rs.getString(8));
			part.setIsapproved(rs.getString(9));
			list.add(part);			
				
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}		
		
		return list;
	}
	
	public Vector<GenericModel> getCampaignsTransfered(int ad_client)
	{
		//MCampaign resulset_campaign[];
		Vector<GenericModel> list = new Vector<GenericModel>();
		String sql="SELECT DISTINCT CC.c_campaign_id,cc.documentno,cc.name,cc.description,"
                     + "cc.pre_solicitado,cc.pre_autorizado,gb.name,cc.istransfered,cc.isapproved FROM C_CAMPAIGN CC,GL_BUDGET GB"
                     +" WHERE CC.gl_budget_id = gb.Gl_budget_id and cc.istransfered='Y' ORDER BY CC.c_campaign_id DESC";
		//System.out.println("query para lista: "+sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			GenericModel part = null;
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.clearParameters();				
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
			part=new GenericModel();
			part.setId_campaign(rs.getString(1));
			part.setNo_documento(rs.getString(2));
			part.setNombre(rs.getString(3));
			part.setDescription(rs.getString(4));
			part.setSolicitado(rs.getString(5));
			part.setAutorizado(rs.getString(6));		
			part.setBudget_name(rs.getString(7));
			part.setTransfered(rs.getString(8));
			part.setIsapproved(rs.getString(9));
			list.add(part);			
				
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}		
		
		return list;
	}
	
	public List<OptionItem> getCampaigns(){
		List<OptionItem> campaigns = null;
		StringBuilder sb = new StringBuilder("SELECT C_CAMPAIGN_ID,NAME FROM C_CAMPAIGN")
			.append(" WHERE C_Campaign.IsActive='Y' AND C_Campaign.IsSummary='N' ");
		StringBuilder sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sb.toString(), this.get_TableName()));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql.toString(),null);
			rs = pstmt.executeQuery();
			campaigns = new ArrayList<OptionItem>();
			while (rs.next())
				campaigns.add(new OptionItem(rs.getString(1),rs.getString(2)));
			
		}catch(SQLException e){
			log.log(Level.SEVERE, "getTrxCampaigns", e);
		}finally{
			try{
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			}catch(Exception e){
				//unreported exception
			}		
		}
		return campaigns;
	}	
	
	public static boolean checkCampaings(BigDecimal amount, int campaign_ID, String tableName, String trxName, int partida_ID){
		
		boolean valido = false;
		MCampaign mCampaign = null;
		MEXME_C_CampaignPA mCampaignPa = null;
		
		String sql = " SELECT * FROM C_CAMPAIGN "+
					 " WHERE C_CAMPAIGN_ID = ? "; 
		
		String sql2 = " SELECT EXME_C_CAMPAIGNPA_ID "+
					  " FROM EXME_C_CAMPAIGNPA "+
					  " WHERE C_CAMPAIGN_ID= ? "+
					  " AND EXME_PARTIDA_ID = ? ";
		
		PreparedStatement pstmt= null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, campaign_ID);
			rs = pstmt.executeQuery();
			//======================================
			pstmt2 = DB.prepareStatement(sql2, null);
			pstmt2.setInt(1, campaign_ID);
			pstmt2.setInt(2, partida_ID);
			rs2 = pstmt2.executeQuery();
			
			if(rs.next())
				mCampaign = new MCampaign(Env.getCtx(), rs, trxName);
			
			if(rs2.next())
				mCampaignPa = new MEXME_C_CampaignPA(Env.getCtx(), rs2.getInt(1), trxName);
			
			
			if(mCampaign == null)
			{
				valido = false;
				System.out.println("mCampaign es igual a null");
			}
			
			else if(mCampaignPa == null){
				valido = false;
				System.out.println("mCampaignPa es igual a null");
			}	
			else
			{
				if (tableName.equalsIgnoreCase(MOrder.Table_Name)){
					
					if((mCampaign.getPre_Comprometido().doubleValue()+amount.doubleValue()) <= mCampaign.getPre_Autorizado().doubleValue() &&
						(mCampaignPa.getPre_Comprometido().doubleValue()+amount.doubleValue()) <= mCampaignPa.getPre_Autorizado().doubleValue())
					{
						mCampaign.setPre_Comprometido(new BigDecimal(mCampaign.getPre_Comprometido().doubleValue()+amount.doubleValue()));
						mCampaignPa.setPre_Comprometido(new BigDecimal(mCampaignPa.getPre_Comprometido().doubleValue()+amount.doubleValue()));
						mCampaign.save();
						mCampaignPa.save();
                        valido = true;
					}
					else
						valido = false;
				
				}else if (tableName.equalsIgnoreCase(MInvoice.Table_Name)){
					
					if((mCampaign.getPre_Devengado().doubleValue()+amount.doubleValue()) <= mCampaign.getPre_Autorizado().doubleValue() &&
							(mCampaignPa.getPre_Devengado().doubleValue()+amount.doubleValue()) <= mCampaignPa.getPre_Autorizado().doubleValue())
					{
						mCampaign.setPre_Devengado(new BigDecimal(mCampaign.getPre_Devengado().doubleValue()+amount.doubleValue()));
						mCampaignPa.setPre_Devengado(new BigDecimal(mCampaignPa.getPre_Devengado().doubleValue()+amount.doubleValue()));
						mCampaign.save();
						mCampaignPa.save();
                        valido = true;
					}
					else
						valido = false;
					
				}else if (tableName.equalsIgnoreCase(MPayment.Table_Name)){
					if((mCampaign.getPre_Ejercido().doubleValue()+amount.doubleValue()) <= mCampaign.getPre_Autorizado().doubleValue() &&
							(mCampaignPa.getPre_Ejercido().doubleValue()+amount.doubleValue()) <= mCampaignPa.getPre_Autorizado().doubleValue())
					{
						 mCampaign.setPre_Ejercido(new BigDecimal(mCampaign.getPre_Ejercido().doubleValue()+amount.doubleValue()));
						 mCampaignPa.setPre_Ejercido(new BigDecimal(mCampaignPa.getPre_Ejercido().doubleValue()+amount.doubleValue()));
						 mCampaign.save();
						 mCampaignPa.save();
						 valido = true;
					}
					else
						valido = false;
				}
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return valido;
	}
	
	
}	//	MCampaign
