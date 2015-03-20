package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Asset Callout	
 *	
 *  @author Expert Victoria JGaray
 *  @version $Id: CalloutAsset.java,v 1 2010/01/07 13:21:05 jgaray Exp $
 */
public class CalloutAsset extends CalloutEngine  {
	
	/**
	 * 	Inactive Asset Group.
	 * 	Evaluates if the pool of assets has some relation to an asset.
	 *	@param ctx context
	 *	@param WindowNo window no
	 *	@param mTab tab
	 *	@param mField field
	 *	@param value value
	 *	@return null or error message
	 */
	public String assetGroupInActive (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		setCalloutActive(true);
		
		int A_Asset_Group_ID=0;
		try{
			if(ctx.getProperty(WindowNo+"|A_Asset_Group_ID")!=null)
				A_Asset_Group_ID=Integer.parseInt(ctx.getProperty(WindowNo+"|A_Asset_Group_ID"));
		
			if(getAssetByGroup(A_Asset_Group_ID)!=0){
				setCalloutActive(true);
				mTab.setValue("IsActive", true);
				setCalloutActive(false);
				JOptionPane p=new JOptionPane(new String("MEnsaje"), JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, new String("El Grupo contiene Activos relacionados, no es posible desactivarlo"));
				p.setVisible(true);
			}else
				setCalloutActive(false);
		}catch(Exception e){
			log.log(Level.SEVERE, "assetGroupInActive", e);
		}
		
		return "";
	}	//	assetGroupInActive
	
	/**
	 * 	Count Asset by Asset Group.
	 * 	
	 *	@param AssetGroup_ID asset Group ID
	 *	@return return number of asset by group
	 */
	private int getAssetByGroup(int AssetGroup_ID){
		int NumReg = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM A_Asset WHERE IsActive = 'Y' AND A_Asset_Group_ID = ?";
		try{
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, AssetGroup_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
				NumReg=rs.getInt(1);
		}catch(SQLException e){
			log.log(Level.SEVERE, "getAssetByGroup", e);
		}finally{
			try{
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			}catch (SQLException e){
				log.log(Level.SEVERE, "getAssetByGroup", e);
			}
		}
		return NumReg;
	}
	
	public void AutomaticSelect (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{

		
		int Exme_Asset_LoanDet_ID=0;
		Boolean Value=new Boolean(false);
		if (value instanceof Boolean){
			Value=(Boolean) value;
		}else if (value instanceof String){
			if(((String) value).trim().equals("Y"))
				Value=new Boolean(true);
			else
				Value=new Boolean(false);
		}else{
			;
		}
		int Exme_Asset_Loan_ID=0;
		int NumRegTot=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			if(ctx.getProperty(WindowNo+"|EXME_Asset_LoanDet_ID")!=null)
				Exme_Asset_LoanDet_ID=Integer.parseInt(ctx.getProperty(WindowNo+"|EXME_Asset_LoanDet_ID"));
				Exme_Asset_Loan_ID=Integer.parseInt(ctx.getProperty(WindowNo+"|EXME_Asset_Loan_ID"));
				String sql= "SELECT COUNT (*) FROM Exme_Asset_LoanDet WHERE Exme_Asset_Loan_ID=? ";
					try{
						pstmt = DB.prepareStatement(sql,null);
						pstmt.setInt(1, Exme_Asset_Loan_ID);
						rs = pstmt.executeQuery();
						while (rs.next())
							NumRegTot=rs.getInt(1);
					}catch(SQLException e){
						log.log(Level.SEVERE, "EXME_LOANDET", e);
					}finally{
						try{
							if (pstmt != null)
								pstmt.close();
							if (rs != null)
								rs.close();
						}catch (SQLException e){
							log.log(Level.SEVERE, "EXME_LOANDET", e);
						}
					}
					
		}catch(Exception e){
			log.log(Level.SEVERE, "EXME_LOANDET", e);
		}
		
		int NumReg=0;
		
		try{
				String sql2 = "SELECT COUNT (*) FROM Exme_Asset_LoanDet WHERE (IsReturned='Y' OR IsStrayed='Y') AND Exme_Asset_Loan_ID=?  ";
					try{
						pstmt = DB.prepareStatement(sql2,null);
						pstmt.setInt(1, Exme_Asset_Loan_ID);
						rs = pstmt.executeQuery();
						while (rs.next())
							NumReg=rs.getInt(1);
					}catch(SQLException e){
						log.log(Level.SEVERE, "EXME_LOANDET", e);
					}finally{
						try{
							if (pstmt != null)
								pstmt.close();
							if (rs != null)
								rs.close();
						}catch (SQLException e){
							log.log(Level.SEVERE, "EXME_LOANDET", e);
						}
					}
					
		}catch(Exception e){
			log.log(Level.SEVERE, "EXME_LOANDET", e);
		}
		X_EXME_Asset_Loan loan=null;
		if (Value){
			if (NumReg+1==NumRegTot)
			loan=new X_EXME_Asset_Loan(Env.getCtx(),Exme_Asset_Loan_ID, null);
			loan.setIsClosed(true);
		}
		else
			if(!Value)
			{
			if (NumReg-1!=NumRegTot)
				loan=new X_EXME_Asset_Loan(Env.getCtx(),Exme_Asset_Loan_ID, null);
			loan.setIsClosed(false);
			}
		loan.save();
		
			//return "";
	}	//	assetGroupInActive
	
	

}
