package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MEXMEProductoOrg;
import org.compiere.model.MEXMEProductoPrecio;
import org.compiere.model.MProduct;
import org.compiere.model.X_I_EXME_ImportaFormulary;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Clase de Importacion de Productos-Formulario
 * @author gderreza
 * created : 16/01/2012
 **/
public class ImportToFormulary extends SvrProcess{
	
	private static CLogger s_log = CLogger.getCLogger(ImportToFormulary.class);
	private List<X_I_EXME_ImportaFormulary> lstFormulary = null;

	@Override
	protected void prepare() {
		int no = 0;
		lstFormulary = new ArrayList<X_I_EXME_ImportaFormulary>();
		StringBuffer sqlbf = null;

		try {
			sqlbf = new StringBuffer("DELETE I_EXME_IMPORTAFORMULARY ").append("WHERE I_IsImported='Y'");

			no = DB.executeUpdate(sqlbf.toString(), this.get_TrxName());
			DB.commit(true, this.get_TrxName());
			log.info("Delete Old Imported =" + no);
			
			loadListBD();			

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "Error:", e);
			sqlbf = new StringBuffer("UPDATE I_EXME_ImportaFormulary i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
			.append(DB.TO_STRING("Error") + " " + Utilerias.getMessage(getCtx(), null, "imagen.Insertar"));
			DB.executeUpdate(sqlbf.toString(), get_TrxName());
		} 

		for (int i = 0; i < lstFormulary.size(); i++) {
			updateProductID(lstFormulary.get(i).getNDC(), lstFormulary.get(i).getI_EXME_ImportaFormulary_ID());
		}
		commit();
		lstFormulary.clear();
	}

	private void loadListBD() {
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			sql.append("SELECT * FROM I_EXME_ImportaFormulary");

			pstm = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstm.executeQuery();

			while (rs.next()) {
				X_I_EXME_ImportaFormulary tmp = new X_I_EXME_ImportaFormulary(Env.getCtx(), rs, get_TrxName());
				lstFormulary.add(tmp);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstm);
		}		
	}

	private void updateProductID(String NDC, int importaFormularyID) {
		
		StringBuffer sql = null;
		int prodID = MProduct.getIDByValue(NDC);
		MProduct prd = new MProduct(getCtx(), prodID, get_TrxName());
		
		if (prd != null) {
			sql = new StringBuffer ("UPDATE I_EXME_ImportaFormulary  SET M_Product_ID = ")
			.append(prd.getM_Product_ID())
			.append(" WHERE I_EXME_ImportaFormulary_ID = ")
			.append(importaFormularyID);
			DB.executeUpdate(sql.toString(), get_TrxName());
		}
	}

	@Override
	protected String doIt() throws Exception {
		MEXMEProductoOrg pOrg = null;
		MEXMEProductoPrecio precio = null;
		StringBuffer sql = null;
		int no = 0;
		loadListBD();
		
		for (int i = 0; i < lstFormulary.size(); i++) {			
			if (!lstFormulary.get(i).isI_IsImported()) {
				pOrg = MEXMEProductoOrg.getObj(getCtx(), lstFormulary.get(i).getNDC(), get_TrxName());
				if (pOrg == null) {
					pOrg = new MEXMEProductoOrg(getCtx(), 0, get_TrxName());

					pOrg.setM_Product_ID(lstFormulary.get(i).getM_Product_ID());
					if (!pOrg.save()) {
						sql = new StringBuffer("UPDATE I_EXME_ImportaFormulary i " + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
								.append(DB.TO_STRING("Error") + " " + Utilerias.getMessage(getCtx(), null, "imagen.Insertar")).append("WHERE I_EXME_ImportaFormulary_ID=")
								.append(lstFormulary.get(i).getI_EXME_ImportaFormulary_ID());
						DB.executeUpdate(sql.toString(), get_TrxName());
					} else {
						precio = new MEXMEProductoPrecio(getCtx(), 0, get_TrxName());

						precio.setM_Product_ID(lstFormulary.get(i).getM_Product_ID());
						if (lstFormulary.get(i).getPriceLimit() != null) {
							precio.setPriceLimit(lstFormulary.get(i).getPriceLimit());
						}
						if (lstFormulary.get(i).getPriceList() != null) {
							precio.setPriceList(lstFormulary.get(i).getPriceList());
							precio.setPriceStd(lstFormulary.get(i).getPriceList());
						}

						precio.setValidFrom(lstFormulary.get(i).getFecha() != null ? lstFormulary.get(i).getFecha() : DB.getTimestampForOrg(Env.getCtx()));

						if (!precio.save()) {
							s_log.warning(Utilerias.getMessage(getCtx(), null, "error.precio.guardar") + " " + lstFormulary.get(i).getNDC());
						}
						sql = new StringBuffer("UPDATE I_EXME_ImportaFormulary i " + "SET Processed = 'Y', I_IsImported='Y', I_ErrorMsg= ")
								.append(DB.TO_STRING(Utilerias.getMessage(getCtx(), null, "advancedDirectives.msj.documentoGuardado"))).append("WHERE I_EXME_ImportaFormulary_ID=")
								.append(lstFormulary.get(i).getI_EXME_ImportaFormulary_ID());
						no = DB.executeUpdate(sql.toString(), get_TrxName());
						log.info("Update =" + no);
					}
				}
			}
		}	
		commit();
		return "";
	}

}
