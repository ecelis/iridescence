package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MEXMEConfigInt;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.SurtidoDet;
import org.compiere.model.X_EXME_ConfigInt;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 ** @deprecated Puesto que el proceso de traspaso entre almacenes ha cambiado y esta clase no se ha actualizado
 */
public class ImportSurtidoDet extends SvrProcess {
	
	
	private int		p_AD_Client_ID = -1;
	//private int		p_AD_User_ID = -1;
	private int		p_AD_Org_ID = -1;
	
	
	protected String doIt() throws Exception {
		
		log.info("Ejecutando importacion de surtido de productos desde tabla interfaz");
		
		Env.setContext(getCtx(), "#AD_Client_ID", p_AD_Client_ID);
		
		Env.setContext(getCtx(), "#AD_Org_ID", p_AD_Org_ID);
		
		List<MMovementLine> lineasSol = new ArrayList<MMovementLine>();
				        
		PreparedStatement pstmt = null;
		ResultSet rsDocument	 	= null;
		
		StringBuilder sqlSurtido = null;
		
		sqlSurtido = new StringBuilder("SELECT DISTINCT MM.M_MOVEMENT_ID, MM.DOCUMENTNO ");
		sqlSurtido.append("FROM SM_SURTIDO SS " );
		sqlSurtido.append("INNER JOIN M_MOVEMENT MM ON MM.DOCUMENTNO = SS.DOCUMENTNO " );
		sqlSurtido.append("WHERE SS.TRANSFERIDO = 'N' ");
		
		
		pstmt = DB.prepareStatement(sqlSurtido.toString(), null);
		
		//Params del constructor surtidoDet 
		//se le pasa valores por default
		int ctaPacID = 0;
		boolean isConsigna = false;
		int locatorID = 0;
		
		
		String retValue = "Exitoso";
		
		try{
			rsDocument = pstmt.executeQuery();
			while (rsDocument.next()){
				lineasSol = MMovement.getDetail(getCtx(),rsDocument.getInt("M_MOVEMENT_ID"),null, false,null);
				SurtidoDet surtidoDet = new SurtidoDet(getCtx(),
						   rsDocument.getString("DOCUMENTNO"), lineasSol,
						   ctaPacID, isConsigna, 
						   rsDocument.getInt("M_MOVEMENT_ID"), locatorID, null);
				ArrayList<String[]>   complete = surtidoDet.complete();
				
				if (complete.size()>0){
	            	actualizaSurtidos(rsDocument.getString("DOCUMENTNO"), false);
	            	retValue =  "Error";
	            }else{
	            	actualizaSurtidos(rsDocument.getString("DOCUMENTNO"), true);
	            }
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
		
		return retValue;
	}

	private boolean actualizaSurtidos(String documentNo, boolean b) {

		try {
			PreparedStatement psUpdate = null;
			StringBuilder sqlUpdate = null; 
			sqlUpdate = new StringBuilder("UPDATE SM_SURTIDO SS ");
			sqlUpdate.append("SET TRANSFERIDO = ? " );
			sqlUpdate.append(", UPDATED = SYSDATE " );
			if (b){
				sqlUpdate.append(",(SS.M_MOVEMENT_ID, SS.M_MOVEMENTLINE_ID) = ")
				.append("(SELECT MM.M_MOVEMENT_ID, MML.M_MOVEMENTLINE_ID FROM M_MOVEMENT MM  ")
				.append("INNER JOIN M_MOVEMENTLINE MML ON MML.M_MOVEMENT_ID = MM.M_MOVEMENT_ID ")
				.append("INNER JOIN M_PRODUCT MP ON MP.M_PRODUCT_ID = MML.M_PRODUCT_ID ")
				.append("WHERE MM.AD_CLIENT_ID = ? AND MM.DOCUMENTNO = ? ");
				MEXMEConfigInt configInt = MEXMEConfigInt.get(getCtx(), null,null);
				if (configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_Sicome)){
					sqlUpdate.append("AND MP.M_PRODUCT_ID = SS.PRODUCTO_VALUE)  " );
				}else if(configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_SIA)){
					sqlUpdate.append("AND MP.SKU = SS.PRODUCTO_VALUE)  " );
				}
				configInt = null;
				
			}
			sqlUpdate.append("WHERE SS.DOCUMENTNO = ?  ");
			psUpdate = DB.prepareStatement(sqlUpdate.toString(), null);
			if (b){
				psUpdate.setString(1, "Y");
				psUpdate.setInt(2, p_AD_Client_ID);
				psUpdate.setString(3,documentNo);
				psUpdate.setString(4,documentNo);
			}else{
				psUpdate.setString(1, "Y");
				psUpdate.setString(2,documentNo);
			}

			psUpdate.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
			if (name.equals("AD_Client_ID"))
				p_AD_Client_ID = (new BigDecimal(para[i].getParameter().toString())).intValue();
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = (new BigDecimal(para[i].getParameter().toString())).intValue();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		
	}

}
