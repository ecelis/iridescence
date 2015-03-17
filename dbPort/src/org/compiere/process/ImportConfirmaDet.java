package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.ConfirmaDet;
import org.compiere.model.ConfirmaDetView;
import org.compiere.model.MConfigEC;
import org.compiere.model.MEXMEConfigInt;
import org.compiere.model.MMovementConfirm;
import org.compiere.model.X_EXME_ConfigInt;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class ImportConfirmaDet extends SvrProcess {
	
	
	private int		p_AD_Client_ID = -1;
	//private int		p_AD_User_ID = -1;
	private int		p_AD_Org_ID = -1;
	
	
	protected String doIt() throws Exception {
		
		log.info("Ejecutando importacion de confirmacion de devolucion desde tabla interfaz");
		
		Env.setContext(getCtx(), "#AD_Client_ID", p_AD_Client_ID);
		
		Env.setContext(getCtx(), "#AD_Org_ID", p_AD_Org_ID);
		
		List<ConfirmaDetView> lineasCon = new ArrayList<ConfirmaDetView>();
				        
		PreparedStatement pstmt = null;
		ResultSet rsDocument	 	= null;
		
		StringBuilder sqlSurtido = null;
		
		sqlSurtido = new StringBuilder("SELECT DISTINCT MMC.M_MOVEMENTCONFIRM_ID, MMC.DOCUMENTNO ");
		sqlSurtido.append("FROM SM_CONFIRMA SC " );
		sqlSurtido.append("INNER JOIN M_MOVEMENT MM ON MM.DOCUMENTNO = SC.DOCUMENTNO ");
		sqlSurtido.append("INNER JOIN M_MOVEMENTCONFIRM MMC ON MMC.M_MOVEMENT_ID = MM.M_MOVEMENT_ID " );
		sqlSurtido.append("WHERE SC.TRANSFERIDO = 'N' ");
		
		pstmt = DB.prepareStatement(sqlSurtido.toString(), null);
		
		//Params del constructor surtidoDet 
		//se le pasa valores por default
		String aplicar = "N";
		String ctaPac = null;
		String pacienteNom = null;
		boolean isCeye = false;
		int estServID = -1;
		
		String retValue = "Exitoso";
		MConfigEC configEC = MConfigEC.get(Env.getCtx(), null);
		ArrayList<String> regExito = new ArrayList<String>();
		ArrayList<String> regError = new ArrayList<String>();
		try{
			rsDocument = pstmt.executeQuery();
			
			while (rsDocument.next()){
				
				lineasCon = MMovementConfirm.getMovementConfirmDet(rsDocument.getInt("M_MOVEMENTCONFIRM_ID"),false);
				/*
				 * Se instancia la clase que llevara a cabo el proceso
				 * de 
				 */
				ConfirmaDet confirmaDet = new ConfirmaDet(Env.getCtx(), rsDocument.getString("DOCUMENTNO"), lineasCon,
														  aplicar, ctaPac, configEC,
														  estServID, isCeye, 
														  rsDocument.getInt("M_MOVEMENTCONFIRM_ID"),pacienteNom );
				ArrayList<String[]> confirmar = confirmaDet.complete();
				
				String documentNo = confirmaDet.getDocumentNo();
				
				if (confirmar.size()>0){
					regError.add(documentNo);
	            	actualizaConfirmaciones(documentNo,rsDocument.getString("DOCUMENTNO"), false);
	            	retValue = "Error";
	            }else{
	            	actualizaConfirmaciones(documentNo,rsDocument.getString("DOCUMENTNO"), true);
	            	regExito.add(documentNo);
	            }
				confirmaDet = null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
		
		
		return retValue;
	}

	private boolean actualizaConfirmaciones(String documentNoMov, String documentNoMovConf,  boolean b) {

		try {
			PreparedStatement psUpdate = null;
			StringBuilder sqlUpdate = null; 
			sqlUpdate = new StringBuilder("UPDATE SM_CONFIRMA SC ");
			sqlUpdate.append("SET TRANSFERIDO = ? " );
			sqlUpdate.append(", UPDATED = SYSDATE " );
			if (b){
				sqlUpdate.append(", (SC.M_MOVEMENTCONFIRM_ID, SC.M_MOVEMENTLINECONFIRM_ID) = ")
				.append("(SELECT MMC.M_MOVEMENTCONFIRM_ID, MMCL.M_MOVEMENTLINECONFIRM_ID FROM M_MOVEMENTCONFIRM MMC ")
				.append("INNER JOIN M_MOVEMENTLINECONFIRM MMCL ON MMCL.M_MOVEMENTCONFIRM_ID = MMC.M_MOVEMENTCONFIRM_ID ")
				.append("INNER JOIN M_MOVEMENTLINE MML ON MML.M_MOVEMENTLINE_ID = MMCL.M_MOVEMENTLINE_ID ")
				.append("INNER JOIN M_PRODUCT MP ON MP.M_PRODUCT_ID = MML.M_PRODUCT_ID ")
				.append("WHERE MMC.AD_CLIENT_ID = ? AND MMC.DOCUMENTNO = ? ");
				MEXMEConfigInt configInt = MEXMEConfigInt.get(getCtx(), null,null);
				if (configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_Sicome)){
					sqlUpdate.append("AND MP.M_PRODUCT_ID = SC.PRODUCTO_VALUE)  " );
				}else if(configInt.getInterfase_Inventarios().equalsIgnoreCase(X_EXME_ConfigInt.INTERFASE_INVENTARIOS_SIA)){
					sqlUpdate.append("AND MP.SKU = SC.PRODUCTO_VALUE)  " );
				}
				configInt = null;
				
			}
			sqlUpdate.append("WHERE SC.DOCUMENTNO = ? ");
			psUpdate = DB.prepareStatement(sqlUpdate.toString(), null);
			if (b){
				psUpdate.setString(1, "Y");
				psUpdate.setInt(2, p_AD_Client_ID);
				psUpdate.setString(3,documentNoMovConf);
				psUpdate.setString(4,documentNoMov);
			}else{
				psUpdate.setString(1, "Y");
				psUpdate.setString(2,documentNoMov);
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
