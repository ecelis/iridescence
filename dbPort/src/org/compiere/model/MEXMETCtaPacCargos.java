package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.WebEnv;

public class MEXMETCtaPacCargos {
//extends X_EXME_T_CtaPacCargos {
//	/** Static Logger */
//	private static CLogger s_log = CLogger.getCLogger(MEXMETCtaPacCargos.class);
//	
//
//	public MEXMETCtaPacCargos(Properties ctx, int EXME_CtaPacDet_ID, String trxName) {
//		super(ctx, EXME_CtaPacDet_ID, trxName);
//	}
//	
//	public MEXMETCtaPacCargos(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}
//	
//	
//	/**
//	 * Limpiar la tabla por cuenta paciente y sesion
//	 * @param ctx
//	 * @param trxName
//	 * @return
//	 */
//
//	public static void isClear(Properties ctx, int EXME_CtaPac_ID, 
//			String trxName) throws Exception {
//		////PIRUETA DELETE
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);	
//
//		sql.append(" DELETE FROM EXME_T_CtaPacCargos ")
//		.append(" WHERE EXME_T_CtaPacCargos.IsActive = 'Y' ")
//		.append(" AND EXME_T_CtaPacCargos.EXME_CtaPac_ID = ? ")
//		.append(" AND EXME_T_CtaPacCargos.AD_User_ID = ? ")
//		.append(" AND EXME_T_CtaPacCargos.AD_Session_ID = ? ");
//
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_CtaPacCargos"));
//
//		PreparedStatement pstmt = null;
//
//		try	{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_User_ID"));
//			pstmt.setInt(3, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//			int noReg = pstmt.executeUpdate();
//
//			s_log.fine("EXME_T_CtaPacCargos Num de Registros borrados = " + noReg);
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//			throw new Exception("error.medico.noOrg");
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (SQLException e) {
//				if (WebEnv.DEBUG)
//					e.printStackTrace();
//				s_log.log(Level.SEVERE, "closing rs / pstmt", e);
//			}
//		}
//
//	}
//	
//	/**
//	 * Consulta 
//	 * @param ctx
//	 * @param trxName
//	 * @return
//	 */
//
//	public static void copyFromCtaPacDet(Properties ctx, int EXME_CtaPac_ID, 
//			int secuencia, String trxName) throws Exception {
//
//		//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		/*sql.append("SELECT * FROM EXME_CtaPacDet WHERE EXME_CtaPac_ID = ?");
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try	{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				MCtaPacDet ctaPacDet = new MCtaPacDet(ctx, rs, null);
//				MEXMETCtaPacCargos tCtaPacCargo = new MEXMETCtaPacCargos(ctx, 0, null);
//				tCtaPacCargo.setEXME_CtaPacDet_ID(ctaPacDet.getEXME_CtaPacDet_ID());
//				tCtaPacCargo.setAD_Client_ID(ctaPacDet.getAD_Client_ID());
//				tCtaPacCargo.setAD_Org_ID(ctaPacDet.getAD_Org_ID());
//				tCtaPacCargo.setIsActive(ctaPacDet.isActive());
//				tCtaPacCargo.setEXME_CtaPac_ID(ctaPacDet.getEXME_CtaPac_ID());
//				tCtaPacCargo.setLine(ctaPacDet.getLine());
//				tCtaPacCargo.setDateOrdered(ctaPacDet.getDateOrdered());
//				tCtaPacCargo.setDatePromised(ctaPacDet.getDatePromised());
//				tCtaPacCargo.setDateDelivered(ctaPacDet.getDateDelivered());
//				tCtaPacCargo.setDateInvoiced(ctaPacDet.getDateInvoiced());
//				tCtaPacCargo.setDescription(ctaPacDet.getDescription());
//				tCtaPacCargo.setM_Product_ID(ctaPacDet.getM_Product_ID());
//				tCtaPacCargo.setM_Warehouse_ID(ctaPacDet.getM_Warehouse_ID());
//				tCtaPacCargo.setC_UOM_ID(ctaPacDet.getC_UOM_ID());
//				tCtaPacCargo.setQtyOrdered(ctaPacDet.getQtyOrdered());
//				tCtaPacCargo.setQtyReserved(ctaPacDet.getQtyReserved());
//				tCtaPacCargo.setQtyDelivered(ctaPacDet.getQtyDelivered());
//				tCtaPacCargo.setQtyInvoiced(ctaPacDet.getQtyInvoiced());
//				tCtaPacCargo.setM_Shipper_ID(ctaPacDet.getM_Shipper_ID());
//				tCtaPacCargo.setC_Currency_ID(ctaPacDet.getC_Currency_ID());
//				tCtaPacCargo.setCosto(ctaPacDet.getCosto());
//				tCtaPacCargo.setPriceList(ctaPacDet.getPriceList());
//				tCtaPacCargo.setPriceActual(ctaPacDet.getPriceActual());
//				tCtaPacCargo.setPriceLimit(ctaPacDet.getPriceLimit());
//				tCtaPacCargo.setLineNetAmt(ctaPacDet.getLineNetAmt());
//				tCtaPacCargo.setDiscount(ctaPacDet.getDiscount());
//				tCtaPacCargo.setFreightAmt(ctaPacDet.getFreightAmt());
//				tCtaPacCargo.setC_Charge_ID(ctaPacDet.getC_Charge_ID());
//				tCtaPacCargo.setChargeAmt(ctaPacDet.getChargeAmt());
//				tCtaPacCargo.setC_Tax_ID(ctaPacDet.getC_Tax_ID());
//				tCtaPacCargo.setS_ResourceAssignment_ID(ctaPacDet.getS_ResourceAssignment_ID());
//				tCtaPacCargo.setM_AttributeSetInstance_ID(ctaPacDet.getM_AttributeSetInstance_ID());
//				tCtaPacCargo.setIsDescription(ctaPacDet.isDescription());
//				tCtaPacCargo.setRef_CtaPacDet_ID(ctaPacDet.getRef_CtaPacDet_ID());
//				tCtaPacCargo.setEXME_CtaPacExt_ID(ctaPacDet.getEXME_CtaPacExt_ID());
//				tCtaPacCargo.setM_InOutLine_ID(ctaPacDet.getM_InOutLine_ID());
//				tCtaPacCargo.setAD_OrgTrx_ID(ctaPacDet.getAD_OrgTrx_ID());
//				tCtaPacCargo.setEXME_CDiarioDet_ID(ctaPacDet.getEXME_CDiarioDet_ID());
//				tCtaPacCargo.setEXME_PlanMedLine_ID(ctaPacDet.getEXME_PlanMedLine_ID());
//				tCtaPacCargo.setM_MovementLine_ID(ctaPacDet.getM_MovementLine_ID());
//				tCtaPacCargo.setTipoArea(ctaPacDet.getTipoArea());
//				tCtaPacCargo.setEXME_Area_ID(ctaPacDet.getEXME_Area_ID());
//				tCtaPacCargo.setPriceListInv(ctaPacDet.getPriceListInv());
//				tCtaPacCargo.setPriceActualInv(ctaPacDet.getPriceActualInv());
//				tCtaPacCargo.setPriceLimitInv(ctaPacDet.getPriceLimitInv());
//				tCtaPacCargo.setUsarFactor(ctaPacDet.isUsarFactor());
//				tCtaPacCargo.setEXME_PaqBase_Version_ID(ctaPacDet.getEXME_PaqBase_Version_ID());
//				tCtaPacCargo.setSecuencia(ctaPacDet.getSecuencia());
//				tCtaPacCargo.setM_Warehouse_Sol_ID(ctaPacDet.getM_Warehouse_Sol_ID());
//				tCtaPacCargo.setQtyEntered(ctaPacDet.getQtyEntered());
//				tCtaPacCargo.setCgoProcesado(ctaPacDet.isCgoProcesado());
//				tCtaPacCargo.setEXME_ConceptoFac_ID(ctaPacDet.getEXME_ConceptoFac_ID());
//				tCtaPacCargo.setEXME_ProductFam_ID(ctaPacDet.getEXME_ProductFam_ID());
//				tCtaPacCargo.setPrecioPublico(ctaPacDet.getPrecioPublico());
//				tCtaPacCargo.setC_TaxPublico_ID(ctaPacDet.getC_TaxPublico_ID());
//				tCtaPacCargo.setProductCategory(ctaPacDet.getProductCategory());
//				tCtaPacCargo.setProductDescription(ctaPacDet.getProductDescription());
//				tCtaPacCargo.setProductValue(ctaPacDet.getProductValue());
//				tCtaPacCargo.setSeDevolvio(ctaPacDet.isSeDevolvio());
//				tCtaPacCargo.setQtyPendiente(ctaPacDet.getQtyPendiente());
//				tCtaPacCargo.setQtyPaquete(ctaPacDet.getQtyPaquete());
//				tCtaPacCargo.setAD_User_ID(Env.getContextAsInt(ctx, "#AD_User_ID"));
//				tCtaPacCargo.setAD_Session_ID(Env.getContextAsInt(ctx, "#AD_Session_ID"));
//				tCtaPacCargo.setSerie(secuencia);
//				if (!tCtaPacCargo.save(trxName)) {
//					s_log.log(Level.SEVERE, "insertTemptable");				
//				}
//				ctaPacDet = null;
//				tCtaPacCargo = null;
//				
//			}
//		*/
//			sql.append(" INSERT INTO EXME_T_CtaPacCargos ( ")
//			.append(" exme_ctapacdet_id, ad_client_id, ad_org_id, isactive, created, ")
//			.append(" createdby, updated, updatedby, exme_ctapac_id, line, dateordered, datepromised, ")
//			.append(" datedelivered, dateinvoiced, description, m_product_id, m_warehouse_id, c_uom_id, qtyordered, ")
//			.append(" qtyreserved, qtydelivered, qtyinvoiced, m_shipper_id, c_currency_id, costo, pricelist, ")
//			.append(" priceactual, pricelimit, linenetamt, discount, freightamt, c_charge_id, chargeamt, c_tax_id, ")
//			.append(" s_resourceassignment_id, m_attributesetinstance_id, isdescription, ref_ctapacdet_id, ")
//			.append(" exme_ctapacext_id, m_inoutline_id, ad_orgtrx_id, exme_cdiariodet_id, exme_planmedline_id, ")
//			.append(" m_movementline_id, tipoarea, exme_area_id, pricelistinv, priceactualinv, pricelimitinv, ")
//			.append(" usarfactor, exme_paqbase_version_id, secuencia, m_warehouse_sol_id, qtyentered, cgoprocesado, ")
//			.append(" exme_conceptofac_id, exme_productfam_id, preciopublico, c_taxpublico_id, productcategory, ")
//			.append(" productdescription, productvalue, sedevolvio, qtypendiente, qtypaquete, ad_user_id, ad_session_id, serie")
//			.append(" )  ")
//			.append(" SELECT EXME_CtaPacDet.exme_ctapacdet_id, EXME_CtaPacDet.ad_client_id, EXME_CtaPacDet.ad_org_id, EXME_CtaPacDet.isactive, EXME_CtaPacDet.created, ")
//			.append(" EXME_CtaPacDet.createdby, EXME_CtaPacDet.updated, EXME_CtaPacDet.updatedby, EXME_CtaPacDet.exme_ctapac_id, EXME_CtaPacDet.line, EXME_CtaPacDet.dateordered, EXME_CtaPacDet.datepromised, ")
//			.append(" EXME_CtaPacDet.datedelivered, EXME_CtaPacDet.dateinvoiced, EXME_CtaPacDet.description, EXME_CtaPacDet.m_product_id, EXME_CtaPacDet.m_warehouse_id, EXME_CtaPacDet.c_uom_id, EXME_CtaPacDet.qtyordered, ")
//			.append(" EXME_CtaPacDet.qtyreserved, EXME_CtaPacDet.qtydelivered, EXME_CtaPacDet.qtyinvoiced, EXME_CtaPacDet.m_shipper_id, EXME_CtaPacDet.c_currency_id, EXME_CtaPacDet.costo, EXME_CtaPacDet.pricelist, ")
//			.append(" EXME_CtaPacDet.priceactual, EXME_CtaPacDet.pricelimit, EXME_CtaPacDet.linenetamt, EXME_CtaPacDet.discount, EXME_CtaPacDet.freightamt, EXME_CtaPacDet.c_charge_id, EXME_CtaPacDet.chargeamt, EXME_CtaPacDet.c_tax_id, ")
//			.append(" EXME_CtaPacDet.s_resourceassignment_id, EXME_CtaPacDet.m_attributesetinstance_id, EXME_CtaPacDet.isdescription, EXME_CtaPacDet.ref_ctapacdet_id, ")
//			.append(" EXME_CtaPacDet.exme_ctapacext_id, EXME_CtaPacDet.m_inoutline_id, EXME_CtaPacDet.ad_orgtrx_id, EXME_CtaPacDet.exme_cdiariodet_id, EXME_CtaPacDet.exme_planmedline_id, ")
//			.append(" EXME_CtaPacDet.m_movementline_id, EXME_CtaPacDet.tipoarea, EXME_CtaPacDet.exme_area_id, EXME_CtaPacDet.pricelistinv, EXME_CtaPacDet.priceactualinv, EXME_CtaPacDet.pricelimitinv, ")
//			.append(" EXME_CtaPacDet.usarfactor, EXME_CtaPacDet.exme_paqbase_version_id, EXME_CtaPacDet.secuencia, EXME_CtaPacDet.m_warehouse_sol_id, EXME_CtaPacDet.qtyentered, EXME_CtaPacDet.cgoprocesado, ")
//			.append(" EXME_CtaPacDet.exme_conceptofac_id, EXME_CtaPacDet.exme_productfam_id, EXME_CtaPacDet.preciopublico, EXME_CtaPacDet.c_taxpublico_id, EXME_CtaPacDet.productcategory, ")
//			.append(" EXME_CtaPacDet.productdescription, EXME_CtaPacDet.productvalue, EXME_CtaPacDet.sedevolvio, EXME_CtaPacDet.qtypendiente, EXME_CtaPacDet.qtypaquete, ")
//			.append(" ?, ?, ? FROM EXME_CtaPacDet WHERE IsActive = 'Y' AND EXME_CtaPac_ID = ?  ");
//			
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacDet"));
//		
//		
//		/*
//		StringBuilder sql = new StringBuilder(100); 
//		sql.append(" INSERT INTO EXME_T_CtaPacCargos  ")
//		.append(" SELECT EXME_CtaPacDet.*, ?, ?, ? FROM EXME_CtaPacDet WHERE IsActive = 'Y' AND EXME_CtaPac_ID = ?  ");
//		*/
//		PreparedStatement pstmt = null;
//
//		try
//		{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_User_ID"));
//			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//			pstmt.setInt(3, secuencia);
//			pstmt.setInt(4, EXME_CtaPac_ID);
//			int noReg = pstmt.executeUpdate();
//
//			s_log.fine("EXME_T_CtaPacCargos Num de Registros insertados = " + noReg);
//		
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "update", e);			
//		} finally {			
//			DB.close(pstmt);
//		}
//	}
//
//	
//	/** @deprecated NO UTILIZADO*/
// 	public static List getCargos(Properties ctx, int EXME_CtaPac_ID, String trxName) {
//
//       List retValue = new ArrayList();
//        
// 		StringBuilder sql = new StringBuilder("Select * from EXME_T_CtaPacDet ")
// 		                            .append(" where EXME_CtaPac_ID = ? ")
//                                    .append(" and isActive = 'Y' ANd AD_Session_ID = ? ");
// 		
// 		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_T_CtaPacDet"));
// 		
// 		PreparedStatement pstmt = null;
// 		ResultSet rs = null;
//
// 		try {
//
// 			pstmt = DB.prepareStatement(sql.toString(), null);
// 			pstmt.setInt(1, EXME_CtaPac_ID);
//			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Session_ID"));
// 			rs = pstmt.executeQuery();
//
// 			while (rs.next()){
// 				retValue.add(new MTCtaPacDet(ctx, rs, trxName));
// 				
// 			}
//
// 		} catch (SQLException e) {
// 			return null;
// 		} finally {
// 			DB.close(rs, pstmt);
//             rs = null;
//             pstmt = null;
//         }
//
// 		return retValue;
//
// 	}
// 	
//
//
//	public int getProductoId() {
//		return super.getM_Product_ID();
//	}
//	/**- jcruz -
//	 * obtener un cargo de la tabla temporal
//	 * @param ctx
//	 * @param exme_ctapacdet_id
//	 * @param ad_session_id
//	 * @param ad_user_id
//	 * @param trxName
//	 * @return
//	 */
//	public static MEXMETCtaPacCargos getCtaPacDet(Properties ctx, int exme_ctapacdet_id, int ad_session_id, int ad_user_id, String trxName){
//		MEXMETCtaPacCargos retValCPD = null;
//		StringBuilder sql = new StringBuilder(" SELECT * FROM EXME_T_CtaPacCargos WHERE EXME_CtaPacDet_ID = ")
//												.append(exme_ctapacdet_id)
//												.append(" AND AD_Session_ID = ")
//												.append(ad_session_id)
//												.append(" AND AD_User_ID = ")
//												.append(ad_user_id);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
// 			pstmt = DB.prepareStatement(sql.toString(), null);
// 			
// 			rs = pstmt.executeQuery();
//
// 			if(rs.next()){
// 				retValCPD= new MEXMETCtaPacCargos(ctx, rs, trxName);
// 			}
//
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		} finally {
// 			DB.close(rs, pstmt);
//             rs = null;
//             pstmt = null;
//         }
// 		return retValCPD;
//	}
}
