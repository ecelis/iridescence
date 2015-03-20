package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Clase que extiende de MCashLine.
 * <p>
 * Basado en version: MCashLine,v 1.16 200O/02/18 13:16:29 vharcq Exp
 * 
 * <b>Modificado: </b> $Author: mrojas $
 * <p>
 * <b>En :</b> $Date: 2006/08/11 02:26:22 $
 * <p>
 * 
 * @author Tania P.
 * @version $Revision: 1.15 $
 * @deprecated NO UTILIZADO
 */

public class MTCtaPacDet 
{
//extends X_EXME_T_CtaPacDet {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private static CLogger s_log = CLogger.getCLogger(MTCtaPacDet.class);
//
//	private int REDONDEO2 = 2;
//	private int REDONDEO4 = 4;
//	private int REDONDEO6 = 6;
//	
//    /*
//     * Cuenta las LP que hacen match para esta l�nea de extensi�n, con el fin
//     * de que al momento de calcular los descuentos s�lo tome en cuenta las l�neas 
//     * que tienen un valor mayor a 0
//     ***/
//    private int esqDescLineCount;
//
//	public MTCtaPacDet(Properties ctx, int EXME_T_CtaPacDet_ID, String trxName) {
//		super(ctx, EXME_T_CtaPacDet_ID, trxName);
//	}
//
//	public MTCtaPacDet(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}
//
//	/**
//	 * 
//	 * Copiamos un registro de EXME_CtaPacDet a EXME_T_CtaPacDet
//	 * @param ctx
//	 * @param EXME_T_CtaPacDet_ID
//	 * @param m_ctaPacDet
//	 * @param secuencia
//	 * @param trxName
//	 * @deprecated NO UTILIZADO
//	 */
////
////	public MTCtaPacDet(Properties ctx, int EXME_T_CtaPacDet_ID,
////			MCtaPacDet m_ctaPacDet, int secuencia, boolean facturado, String trxName) {
////
////		super(ctx, EXME_T_CtaPacDet_ID, trxName);
////		setEXME_CtaPacDet_ID(m_ctaPacDet.getEXME_CtaPacDet_ID());
////		setEXME_CtaPac_ID(m_ctaPacDet.getEXME_CtaPac_ID());
////		setLine(m_ctaPacDet.getLine());
////		setDateOrdered(m_ctaPacDet.getDateOrdered());
////		setDatePromised(m_ctaPacDet.getDatePromised());
////		setDateDelivered(m_ctaPacDet.getDateDelivered());
////		setDateInvoiced(m_ctaPacDet.getDateInvoiced());
////		setDescription(m_ctaPacDet.getDescription());
////		setM_Product_ID(m_ctaPacDet.getM_Product_ID());
////		setM_Warehouse_ID(m_ctaPacDet.getM_Warehouse_ID());
////		setC_UOM_ID(m_ctaPacDet.getC_UOM_ID());
////		
////		setQtyOrdered(m_ctaPacDet.getQtyOrdered());
////		setQtyReserved(m_ctaPacDet.getQtyReserved());
////		setQtyDelivered(m_ctaPacDet.getQtyDelivered());
////		setQtyPendiente(m_ctaPacDet.getQtyPendiente());
////		setQtyPaquete(m_ctaPacDet.getQtyPaquete());
////		setQtyInvoiced(m_ctaPacDet.getQtyPendiente());
////		
////		setM_Shipper_ID(m_ctaPacDet.getM_Shipper_ID());
////		setC_Currency_ID(m_ctaPacDet.getC_Currency_ID());
////		setFreightAmt(m_ctaPacDet.getFreightAmt().setScale(REDONDEO2,BigDecimal.ROUND_HALF_UP));
////		setC_Charge_ID(m_ctaPacDet.getC_Charge_ID());
////		setChargeAmt(m_ctaPacDet.getChargeAmt().setScale(REDONDEO2,BigDecimal.ROUND_HALF_UP));
////		setS_ResourceAssignment_ID(m_ctaPacDet.getS_ResourceAssignment_ID());
////		setM_AttributeSetInstance_ID(m_ctaPacDet.getM_AttributeSetInstance_ID());
////		setIsDescription(m_ctaPacDet.isDescription());
////		setRef_CtaPacDet_ID(m_ctaPacDet.getRef_CtaPacDet_ID());
////		setEXME_CtaPacExt_ID(m_ctaPacDet.getEXME_CtaPacExt_ID());
////		setM_InOutLine_ID(m_ctaPacDet.getM_InOutLine_ID());
////		setAD_OrgTrx_ID(m_ctaPacDet.getAD_OrgTrx_ID());
////		setEXME_CDiarioDet_ID(m_ctaPacDet.getEXME_CDiarioDet_ID());
////		setEXME_PlanMedLine_ID(m_ctaPacDet.getEXME_PlanMedLine_ID());
////		setM_MovementLine_ID(m_ctaPacDet.getM_MovementLine_ID());
////		setTipoArea(m_ctaPacDet.getTipoArea());
////		setEXME_Area_ID(m_ctaPacDet.getEXME_Area_ID());
////		setTipoLinea(m_ctaPacDet.getTipoLinea());  // Asignar el tipo de Linea Expert:aaranda 21082010
////		
////		
////		setInvoice_UOM_ID(m_ctaPacDet.getC_UOM_ID());
////		setIsFacturado(facturado);
////		setAD_User_ID(Env.getContextAsInt(ctx, "#AD_User_ID"));
////		setAD_Session_ID(Env.getContextAsInt(ctx, "#AD_Session_ID"));
////		setSecuencia(secuencia);
////		
////		setC_Tax_ID(m_ctaPacDet.getC_Tax_ID());
////		setCosto(m_ctaPacDet.getCosto());
////		setPriceList(m_ctaPacDet.getPriceList());
////		setPriceActual(m_ctaPacDet.getPriceActual());
////		setPriceLimit(m_ctaPacDet.getPriceLimit());
////		setUsarFactor(m_ctaPacDet.isUsarFactor());
////		setLineNetAmt(m_ctaPacDet.getLineNetAmt());
////		//setDiscount(m_ctaPacDet.getDiscount().setScale(REDONDEO2,BigDecimal.ROUND_HALF_UP));//Monto
////		//Correciones de descuentos Expert:aaranda 17082010
////		setDiscount(m_ctaPacDet.getDiscount());//Monto
////		setDiscountAmt(m_ctaPacDet.getDiscountAmt());
////		setDiscountFam(m_ctaPacDet.getDiscountFam());
////		setPriceListInv(m_ctaPacDet.getPriceListInv());
////		setPriceActualInv(m_ctaPacDet.getPriceActualInv());
////		setPriceLimitInv(m_ctaPacDet.getPriceLimitInv()); //Este Precio sirve para conservar historico
////
////		
////		MEXMETax m_tax = new MEXMETax(ctx, m_ctaPacDet.getC_Tax_ID(), trxName);
////		if (m_tax != null){
////			setTaxAmt(m_tax.calculateTax(m_ctaPacDet.getLineNetAmt(), false, REDONDEO6));
////		}else
////			setTaxAmt(Env.ZERO);
////
////       
////       //gl - campos para facturacion especial
////       setProductCategory(m_ctaPacDet.getProductCategory());
////       setProductDescription(m_ctaPacDet.getProductDescription());
////       setPrecioPublico(m_ctaPacDet.getPrecioPublico());
////       setC_TaxPublico_ID(m_ctaPacDet.getC_TaxPublico_ID());
////       
////       setEXME_PaqBase_Version_ID(m_ctaPacDet.getEXME_PaqBase_Version_ID());
////       //contener el convenio que se aplica
////       setEXME_EsqDesLine_ID(m_ctaPacDet.getEXME_EsqDesLine_ID());
////	}
//
//    /**
//     * 
//     * Copiamos un registro de EXME_CtaPacDet a EXME_T_CtaPacDet
//     * @param ctx
//     * @param EXME_T_CtaPacDet_ID
//     * @param m_ctaPacDet
//     * @param secuencia
//     * @param trxName
//     * @throws Exception 
//     * @deprecated NO UTILIZADO
//     */
//
////	public MTCtaPacDet(Properties ctx, int EXME_T_CtaPacDet_ID,
////			MCtaPacDet m_ttctaPacDet, int secuencia, String trxName) throws Exception {
////
////		super(ctx, EXME_T_CtaPacDet_ID, trxName);
////		setEXME_CtaPacDet_ID(m_ttctaPacDet.getEXME_CtaPacDet_ID());
////		setEXME_CtaPac_ID(m_ttctaPacDet.getEXME_CtaPac_ID());
////		setLine(m_ttctaPacDet.getLine());
////		setDateOrdered(m_ttctaPacDet.getDateOrdered());
////		setDatePromised(m_ttctaPacDet.getDatePromised());
////		setDateDelivered(m_ttctaPacDet.getDateDelivered());
////		setDateInvoiced(m_ttctaPacDet.getDateInvoiced());
////		setDescription(m_ttctaPacDet.getDescription());
////		setM_Product_ID(m_ttctaPacDet.getM_Product_ID());
////		setM_Warehouse_ID(m_ttctaPacDet.getM_Warehouse_ID());
////		setC_UOM_ID(m_ttctaPacDet.getC_UOM_ID());
////		
////		setQtyOrdered(m_ttctaPacDet.getQtyOrdered());
////		setQtyReserved(m_ttctaPacDet.getQtyReserved());
////		setQtyDelivered(m_ttctaPacDet.getQtyDelivered());
////		//setQtyInvoiced(m_ttctaPacDet.getQtyDelivered());
////		setQtyInvoiced(m_ttctaPacDet.getQtyInvoiced());
////		setM_Shipper_ID(m_ttctaPacDet.getM_Shipper_ID());
////		setC_Currency_ID(m_ttctaPacDet.getC_Currency_ID());
////		
////		setCosto(m_ttctaPacDet.getCosto().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////		setPriceList(m_ttctaPacDet.getPriceList().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////		setPriceActual(m_ttctaPacDet.getPriceActual().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////		setPriceLimit(m_ttctaPacDet.getPriceLimit().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////		setUsarFactor(m_ttctaPacDet.isUsarFactor());//Revisar se agrego enla sincronizacion
////		setLineNetAmt(m_ttctaPacDet.getLineNetAmt().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////		setDiscount(m_ttctaPacDet.getDiscount().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////		
////		setFreightAmt(m_ttctaPacDet.getFreightAmt().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////		setC_Charge_ID(m_ttctaPacDet.getC_Charge_ID());
////		setChargeAmt(m_ttctaPacDet.getChargeAmt().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////
////
////		MTax m_tax;
////
////		m_tax = new MEXMETax(ctx, m_ttctaPacDet.getC_Tax_ID(), trxName);
////		setC_Tax_ID(m_ttctaPacDet.getC_Tax_ID());
////
////
////		setS_ResourceAssignment_ID(m_ttctaPacDet.getS_ResourceAssignment_ID());
////		setM_AttributeSetInstance_ID(m_ttctaPacDet.getM_AttributeSetInstance_ID());
////		setIsDescription(m_ttctaPacDet.isDescription());
////		setRef_CtaPacDet_ID(m_ttctaPacDet.getRef_CtaPacDet_ID());
////		setEXME_CtaPacExt_ID(m_ttctaPacDet.getEXME_CtaPacExt_ID());
////		setM_InOutLine_ID(m_ttctaPacDet.getM_InOutLine_ID());
////		setAD_OrgTrx_ID(m_ttctaPacDet.getAD_OrgTrx_ID());
////		setEXME_CDiarioDet_ID(m_ttctaPacDet.getEXME_CDiarioDet_ID());
////		setEXME_PlanMedLine_ID(m_ttctaPacDet.getEXME_PlanMedLine_ID());
////		setM_MovementLine_ID(m_ttctaPacDet.getM_MovementLine_ID());
////		setTipoArea(m_ttctaPacDet.getTipoArea());
////		setEXME_Area_ID(m_ttctaPacDet.getEXME_Area_ID());
////		setPriceListInv(m_ttctaPacDet.getPriceListInv().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////		setPriceActualInv(m_ttctaPacDet.getPriceActualInv().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP));
////		setPriceLimitInv(m_ttctaPacDet.getPriceLimitInv().setScale(REDONDEO2,
////				BigDecimal.ROUND_HALF_UP)); //Este Precio sirve para conservar historico
////				//cuando son descuentos por familia
////
////		setQtyPendiente(m_ttctaPacDet.getQtyPendiente());
////		setQtyPaquete(Env.ZERO);
////		setInvoice_UOM_ID(m_ttctaPacDet.getC_UOM_ID());
////		setIsFacturado(m_ttctaPacDet.isFacturado());
////		setAD_User_ID(Env.getContextAsInt(ctx, "#AD_User_ID"));
////		setAD_Session_ID(Env.getContextAsInt(ctx, "#AD_Session_ID"));
////		setSecuencia(secuencia);
////		setC_Tax_ID(m_ttctaPacDet.getC_Tax_ID());
////		
////		if (m_tax != null){
////			BigDecimal total = getQtyPendiente().multiply(getPriceActualInv());
////			setTaxAmt(m_tax.calculateTax(total, false, 2)
////					.setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP));
////		}else
////			setTaxAmt(Env.ZERO);
////
////		//Nuevas columnas agregadas en la tabla EXME_CtaPacDet
////		//y tambi�n se deben agregar a la tabla de EXME_T_CtaPacDet
////		setDiscountAmt(m_ttctaPacDet.getDiscountAmt());
////		setDiscountFam(m_ttctaPacDet.getDiscountFam());
////		setUsarFactor(m_ttctaPacDet.isUsarFactor());
////		setEXME_PaqBase_Version_ID(m_ttctaPacDet.getEXME_PaqBase_Version_ID());
////		setNoLine(m_ttctaPacDet.getNoLine());
////		setTipoLinea(m_ttctaPacDet.getTipoLinea());
////
////		//gl - campos para facturaci�n especial
////		setProductCategory(m_ttctaPacDet.getProductCategory());
////		setProductDescription(m_ttctaPacDet.getProductDescription());
////		setPrecioPublico(m_ttctaPacDet.getPrecioPublico());
////		setC_TaxPublico_ID(m_ttctaPacDet.getC_TaxPublico_ID());
////
////		//Nuevas columnas agregadas en la tabla EXME_CtaPacDet
////		//y tambien se deben agregar a la tabla de EXME_T_CtaPacDet
////		setCalcularPrecio(m_ttctaPacDet.isCalcularPrecio());
////		setSerie(m_ttctaPacDet.getSerie());
////		setVisible(m_ttctaPacDet.isVisible());
////
////		//contener el convenio que se aplica
////		setEXME_EsqDesLine_ID(m_ttctaPacDet.getEXME_EsqDesLine_ID());
////
////	}
//
//	/**
//	 * Creamos un cargo a la cuenta paciente.
//	 * @param ctaPac
//	 * @deprecated NO UTILIZADO
//	 */
//
//	public MTCtaPacDet(MEXMECtaPac ctaPac, int EXME_CtaPacExt_ID,
//			int EXME_Area_ID, String trxName) {
//
//		this(ctaPac.getCtx(), 0, trxName);
//
//		setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
//
//		if (EXME_Area_ID > 0) {
//			MArea area = new MArea(getCtx(), EXME_Area_ID, get_TrxName());
//			setTipoArea(area.getTipoArea());
//			setEXME_Area_ID(EXME_Area_ID);
//
//		}
//
//		setDateOrdered(new Timestamp(System.currentTimeMillis()));
//
//		if (EXME_CtaPacExt_ID == 0) {
//			// Determinamos la extension cero.
//			setEXME_CtaPacExt_ID(
//			MCtaPacExt.getExtCero(
//			getCtx(), getEXME_CtaPac_ID(), get_TrxName())
//					.getEXME_CtaPacExt_ID());
//		} else {
//			setEXME_CtaPacExt_ID(EXME_CtaPacExt_ID);
//		}
//
//	}
//
//	/**
//	 * Borra registros para determinada cuenta paciente
//	 * 
//	 * @param ctx
//	 * @param EXME_CtaPac_ID
//	 * @param trxName
//	 * @return
//	 * @deprecated NO UTILIZADO
//	 */
//	public static void delete(Properties ctx, int EXME_CtaPac_ID, String trxName)
//			throws SQLException {
//
//		StringBuilder sqlRef = new StringBuilder(80);
//		sqlRef.append(" SELECT * FROM EXME_T_CtaPacDet WHERE EXME_CtaPac_ID = ? ")
//		.append(" AND AD_User_ID = ?  AND AD_Session_ID = ? ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_T_CtaPacDet"))
//		.append(" AND Ref_CtaPacDet_ID IS NOT NULL ");
//		
//		StringBuilder sql = new StringBuilder(80);
//		sql.append(" SELECT * FROM EXME_T_CtaPacDet WHERE EXME_CtaPac_ID = ? ")
//		.append(" AND AD_User_ID = ?  AND AD_Session_ID = ? ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_T_CtaPacDet"))
//		.append(" AND Ref_CtaPacDet_ID IS NULL ");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sqlRef.toString(), trxName);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_User_ID"));
//			pstmt.setInt(3, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//			
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MTCtaPacDet ctaPac = new MTCtaPacDet(ctx, rs, null);
//				if(!ctaPac.delete(true)){
//					throw new Exception("error.getMovement");
//				}
//			}
//			DB.close(rs,pstmt);
//			
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_User_ID"));
//			pstmt.setInt(3, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//			
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MTCtaPacDet ctaPac = new MTCtaPacDet(ctx, rs, null);
//				if(!ctaPac.delete(true)){
//					throw new Exception("error.getMovement");
//				}
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, e.getMessage(), e);
//			throw new SQLException("error.getMovement");
//		} finally {
//			DB.close(rs,pstmt);
//			pstmt = null;
//			rs = null;
//		}
//
//	}
//
//	/**
//	 * Obtiene la sumatoria de los cargos relacionados a una extension
//	 * 
//	 * @param ctx
//	 * @param EXME_CtaPacExt_ID
//	 * @param trxName
//	 * @return lineNetAmtSum
//	 * @deprecated NO UTILIZADO
//	 */
//	public double getLineNetAmtSum(Properties ctx, int EXME_CtaPacExt_ID, int AD_Session_ID,
//			String trxName) throws SQLException {
//
//		double lineNetAmtSum = 0;
//
//		StringBuffer sql = new StringBuffer();
//
//		sql.append("SELECT SUM(NVL(LineNetAmt,0)+ NVL(TaxAmt,0)) as total ")
//			.append(" FROM EXME_T_CtaPacDet WHERE EXME_CtaPacExt_ID = ? ")
//			.append(" AND isActive = 'Y' AND AD_Session_ID = ? ")
//			.append(" AND EXME_T_CtaPacDet.visible ='Y' ")
//			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_T_CtaPacDet"));
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1,EXME_CtaPacExt_ID);
//			pstmt.setInt(2,AD_Session_ID);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				lineNetAmtSum = rs.getDouble("total");
//			}
//
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, e.getMessage(), e);
//		} finally {
//			DB.close(rs,pstmt);
//		}
//		return lineNetAmtSum;
//	}
//	
//	/*newRegistry indica si el registro se acaba de agregar a la extensi�n*/
///*	private boolean newRegistry = false;
//	
//	public void setIsNewRegistry(boolean b){
//		newRegistry = b;
//	}
//	
//	public boolean isNewRegistry(){
//		return newRegistry;
//	}
//*/
//	/** @deprecated NO UTILIZADO */
//	BigDecimal importeActualInv = Env.ZERO;
//
//	/** @deprecated NO UTILIZADO*/
//	public BigDecimal getImporteActualInv() {
//		setImporteActualInv();
//		return importeActualInv;
//	}
//
//	/** @deprecated NO UTILIZADO */
//	public void setImporteActualInv() {
//        importeActualInv = getPriceActualInv().multiply(getQtyInvoiced()).setScale(2, BigDecimal.ROUND_HALF_UP);
//    }
//    
// /*   public void setImporteActualInvRecalcular() {
//            importeActualInv = getPriceActualInv().multiply(getQtyInvoiced()).setScale(2, BigDecimal.ROUND_HALF_UP);
//	}
//
//	public BigDecimal getTotal() {
//        return getImporteActualInv().add(getTaxAmt()).setScale(2, BigDecimal.ROUND_HALF_UP);
//    }
//    */
// /*   String cotizado = null;
//
//    public String getCotizado() {
//        return cotizado;
//    }
//
//    public void setCotizado(String cotizado) {
//        this.cotizado = cotizado;
//	}
//
//    public String getCotizado(int esqDesLine_ID){
//    	
//    	if (esqDesLine_ID > 0){
//	    	MEsqDesLine edl = new MEsqDesLine(getCtx(), esqDesLine_ID, get_TrxName());
//	    
//	    	if (edl.getList_AddAmt().compareTo(Env.ZERO)==1){	//Si List_AddAmt es mayor a 0, entonces es fijo (cotizado).
//	        	this.cotizado = "Y";
//	        } 
//	    	
//	    	if (edl.getList_Discount().compareTo(Env.ZERO)==1){	//Si List_Discount es mayor a 0, entonces es porcentual (no cotizado).
//	        	this.cotizado = "N";        	
//	        }
//    	} else {	//Si esqDesLine_ID es igual a 0, entonces no aplica.
//    		this.cotizado="NA";
//    	}
//    	
//    	return this.cotizado;
//    }
//    */
///*    public MTax getTaxPublico() {
//        MTax taxPublico = null;
//        
//        if(getC_TaxPublico_ID()!=0)
//            taxPublico = new MTax(getCtx(), getC_TaxPublico_ID(), get_TrxName());
//        
//        return taxPublico;
//    }
//    
//    public int M_ProductCategory_ID = 0;
//
//	public int getM_ProductCategory_ID() {
//		return M_ProductCategory_ID;
//	}
//
//	public void setM_ProductCategory_ID(int productCategory_ID) {
//		M_ProductCategory_ID = productCategory_ID;
//	}
//*/
//	/** @deprecated NO UTILIZADO */
//	public boolean factEsp = false;
//	/** @deprecated NO UTILIZADO*/
//	public boolean isFactEsp() {
//		return factEsp;
//	}
//	/** @deprecated NO UTILIZADO */
//	public void setFactEsp(boolean factEsp) {
//		this.factEsp = factEsp;
//	}
//
//	
//	/**
//     * Permite traer todas las lineas de la tabla t_ctapacdet correspondiente a la
//     * cuenta paciente que sean de la extension cero
//     * 
//     * @param ctx
//     * @param EXME_CtaPac_ID
//     * @param trxName
//     * @return
//     * @deprecated NO UTILIZADO
//     */
//    public static MTCtaPacDet[] getExtCero(Properties ctx,
//            int EXME_CtaPac_ID, String trxName) {
//
//        ArrayList<MTCtaPacDet> list = new ArrayList<MTCtaPacDet>();
//
//        StringBuffer sql = new StringBuffer();
//        sql.append("SELECT EXME_T_CtaPacDet.* FROM EXME_T_CtaPacDet INNER JOIN EXME_CtaPacExt ext ON ")
//                    .append(" (EXME_T_CtaPacDet.EXME_CtaPacExt_ID = ext.EXME_CtaPacExt_ID) ")
//                    .append(" WHERE EXME_T_CtaPacDet.EXME_CtaPac_ID = ? ")
//                    .append(" AND ext.ExtensionNo = 0 ");
//
//        sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
//                .toString(), "EXME_T_CtaPacDet"))
//
//        .append(" ORDER BY EXME_T_CtaPacDet.EXME_CtaPacDet_ID ASC");
//        s_log.log(Level.SEVERE,"SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//       // System.out.println("SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, EXME_CtaPac_ID);
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                MTCtaPacDet refValue = new MTCtaPacDet(ctx, rs, trxName);
//                list.add(refValue); // lista sin lineas de devolucion
//            }
//
//        } catch (Exception e) {
//            s_log.log(Level.SEVERE, "getExtCero ", e);
//        } finally {
//        	DB.close(rs,pstmt);
//            pstmt = null;
//        }
//
//        MTCtaPacDet[] lineas = new MTCtaPacDet[list.size()];
//        list.toArray(lineas);
//        return lineas;
//    }
//    /** @deprecated NO UTILIZADO */
//    public int getIdProduct(){
//    	return getM_Product_ID();
//    }
//    /** @deprecated NO UTILIZADO */
//    public int getIdUOM(){
//    	return getC_UOM_ID();
//    }
//    
//    /**
//     * Obtiene los descuentos de una cta pac
//     * @param ctx Contexto
//     * @param EXME_CtaPac_ID Cta Paciente
//     * @param trxName Nombre de la transaccion
//     * @return BigDecimal Descuentos
//     * @deprecated NO UTILIZADO
//     */
//    public static BigDecimal getSaldoDescuentos(Properties ctx,
//            int EXME_CtaPac_ID, String trxName) {
//
//        BigDecimal descuento = Env.ZERO;
//
//        StringBuffer sql = new StringBuffer(); 
//        sql.append("select sum(nvl(e.DESCTOGLOBALAMT,0)) as monto from EXME_CtaPacExt e ") 
//	        .append(" where e.exme_ctapac_id = ?")    
//        	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPacExt", "e"));
//        s_log.log(Level.SEVERE,"SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, EXME_CtaPac_ID);
//            
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				descuento = rs.getBigDecimal("monto");
//			}
//			
//
//        } catch (Exception e) {
//            s_log.log(Level.SEVERE, "getExtCero ", e);
//        } finally {
//        	DB.close(rs,pstmt);
//            pstmt = null;
//        }
//
//        return descuento;
//    }
//    
//    /** @deprecated NO UTILIZADO*/
//    public static BigDecimal getSaldoCargos(Properties ctx,
//            int EXME_CtaPac_ID, String trxName) {
//
//        BigDecimal saldoCargos = Env.ZERO;
//
//        StringBuffer sql = new StringBuffer(); 
//        sql.append("SELECT SUM(NVL(EXME_T_CtaPacDet.LineNetAmt,0)+ NVL(EXME_T_CtaPacDet.TaxAmt,0)) AS saldo ")
//        	//"SELECT NVL(SUM(EXME_T_CtaPacDet.LineNetAmt),0) AS saldo FROM EXME_T_CtaPacDet ")
//        	//.append(" INNER JOIN EXME_CtaPacExt ext ON ")
//            //.append(" (EXME_T_CtaPacDet.EXME_CtaPacExt_ID = ext.EXME_CtaPacExt_ID) ")
//        	.append(" FROM EXME_T_CtaPacDet ")
//            .append(" WHERE EXME_T_CtaPacDet.IsActive = 'Y' ")
//            .append(" AND EXME_T_CtaPacDet.EXME_CtaPac_ID = ? ")
//            .append(" AND EXME_T_CtaPacDet.AD_User_ID = ? ")
//            .append(" AND EXME_T_CtaPacDet.AD_Session_ID = ? ")
//            .append(" AND EXME_T_CtaPacDet.visible ='Y' ")
//            //  .append(" AND ((EXME_T_CtaPacDet.exme_paqbase_version_id is not null and EXME_T_CtaPacDet.visible='N') " +
//            //  "OR (EXME_T_CtaPacDet.exme_paqbase_version_id is null and EXME_T_CtaPacDet.visible='Y'))");
//        
//        	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_T_CtaPacDet"))
//        	.append(" ORDER BY EXME_T_CtaPacDet.EXME_CtaPacDet_ID ASC");
//        s_log.log(Level.SEVERE,"SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//       // System.out.println("SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, EXME_CtaPac_ID);
//			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_User_ID"));
//			pstmt.setInt(3, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//            
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				saldoCargos = rs.getBigDecimal("saldo");
//			}
//			
//            
//
//        } catch (Exception e) {
//            s_log.log(Level.SEVERE, "getExtCero ", e);
//        } finally {
//        	DB.close(rs,pstmt);
//            pstmt = null;
//        }
//
//        return saldoCargos;
//    }
//    
//    /**
//     * Obtiene cargos e iva
//     * @param ctx Contexto
//     * @param EXME_CtaPac_ID Cta Paciente
//     * @param trxName Nombre de transaccion
//     * @return HashMap Cargo e iva
//     * @deprecated NO UTILIZADO
//     */
//    public static HashMap<String, BigDecimal> getSaldoCargosIVA(Properties ctx,
//            int EXME_CtaPac_ID, String trxName) {
//    	HashMap<String, BigDecimal> map = new HashMap<String, BigDecimal>();
//
//        StringBuffer sql = new StringBuffer(); 
//        sql.append("Select sum((PRICELIMITINV)*(QTYPENDIENTE)) as saldo, sum(TAXAMT) as iva")
//    	   	.append(" FROM EXME_T_CtaPacDet ")
//	   		.append(" WHERE EXME_T_CtaPacDet.IsActive = 'Y' ")
//            .append(" AND EXME_T_CtaPacDet.EXME_CtaPac_ID = ? ")
//            .append(" AND EXME_T_CtaPacDet.AD_User_ID = ? ")
//            .append(" AND EXME_T_CtaPacDet.AD_Session_ID = ? ")
//            .append(" AND EXME_T_CtaPacDet.visible ='Y' ")
//            .append(" AND EXME_T_CtaPacDet.m_product_id > 0 ")        
//        	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_T_CtaPacDet"))
//        	.append(" ORDER BY EXME_T_CtaPacDet.EXME_CtaPacDet_ID ASC");
//        s_log.log(Level.SEVERE,"SQL> " + sql + " ID > " + EXME_CtaPac_ID);
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, EXME_CtaPac_ID);
//			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_User_ID"));
//			pstmt.setInt(3, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//            
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				map.put("saldo", rs.getBigDecimal("saldo"));
//				map.put("iva", rs.getBigDecimal("iva"));
//			}
//			
//
//        } catch (Exception e) {
//            s_log.log(Level.SEVERE, "getExtCero ", e);
//        } finally {
//        	DB.close(rs,pstmt);
//            pstmt = null;
//        }
//
//        return map;
//    }
//    
//	public void setDiscountAmt (BigDecimal DiscountAmt)
//	{
//		super.setDiscountAmt(DiscountAmt.setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP));
//	}
//	public BigDecimal getDiscountAmt() 
//	{
//		return super.getDiscountAmt().setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP);
//	}
//	/** @deprecated NO UTILIZADO*/
//	public void setEsqDescLineCount(int esqDescLineCount) {
//		this.esqDescLineCount = esqDescLineCount;
//	}
//	/** @deprecated NO UTILIZADO */
//	public int getEsqDescLineCount() {
//		return esqDescLineCount;
//	}
//	
//	public boolean equals (Object obj) {
//
//		boolean found = false;
//		
//		if (obj instanceof MTCtaPacDet &&
//			this.getEXME_CtaPacDet_ID() == ((MTCtaPacDet)obj).getEXME_CtaPacDet_ID()) {
//			found = true;
//		}
//		
//		return found;
//	}
//	/** @deprecated NO UTILIZADO*/
//	public void setLineNetAmt() 
//	{
//		super.setLineNetAmt((getQtyInvoiced().multiply(getPriceActualInv())).setScale(2, BigDecimal.ROUND_HALF_UP));
//	}
//
//	/**
//	 * Obtenemos la cama asignada a la cuenta paciente
//	 * @return
//	 */
//	private MEXMECtaPacExt extension = null;
//	/** @deprecated NO UTILIZADO*/
//	public MEXMECtaPacExt getExtension() {
//		if( this.extension == null || getEXME_CtaPacExt_ID()<=0 )
//			this.extension =  new MEXMECtaPacExt(getCtx(), getEXME_CtaPacExt_ID(), get_TrxName());
//		return this.extension;
//	}
//	/**
//	 * 
//	 * @param ctx
//	 * @param EXME_CtaPacExt_ID
//	 * @param configPre
//	 * @param consideraPack
//	 * @param tabla
//	 * @param trxName
//	 * @return  List<MTCtaPacDet> 
//	 * @throws SQLException 
//	 * @deprecated NO UTILIZADO
//	 */
//	public static List<MTCtaPacDet> getLineasExtensionEdoCtaPac(Properties ctx,
//			boolean soloDescGlobal, int EXME_CtaPac_ID, String trxName) 
//			throws SQLException {
//		// Servicios de Coaseguro y Deducible. no se recalcula el Tax.
//		ArrayList<MTCtaPacDet> list = new ArrayList<MTCtaPacDet>();
//
//		StringBuffer sql = new StringBuffer();
//		sql.append(" SELECT EXME_CtaPacDet.* ")
//		.append(" FROM EXME_T_CtaPacDet EXME_CtaPacDet ")
//		.append(" INNER JOIN EXME_CtaPacExt cpe ON EXME_CtaPacDet.EXME_CtaPacExt_ID = cpe.EXME_CtaPacExt_ID ")
//		.append(" WHERE EXME_CtaPacDet.isActive= 'Y' ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPacDet"))
//		//.append(" AND EXME_TTCtaPacDet.M_Product_ID NOT IN (")
//		//.append(configPre.getCoaseguro_ID()).append(", ")
//		//.append(configPre.getDeducible_ID()).append(") ")//Se consideran para que se calcule su impuesto por eso se comentaron las lineas
//		.append(" AND EXME_CtaPacDet.QtyInvoiced > 0 ")
//		.append(" AND EXME_CtaPacDet.M_Product_ID > 0 ")
//		.append(" AND cpe.C_Invoice_ID IS NULL ")//Que no esten facturados
//		.append(" AND cpe.IsInvoiced <> 'Y'  ");
//		if(soloDescGlobal)
//			sql.append(" AND cpe.DesctoGlobal IS NOT NULL AND cpe.DesctoGlobal > 0 ");
//		sql.append(" AND ( cpe.Coaseguro IS NULL OR cpe.Coaseguro = 0 ) ")
//		.append(" AND ( cpe.Deducible IS NULL OR cpe.Deducible = 0 ) ")
//        .append(" AND EXME_CtaPacDet.AD_User_ID = ? AND EXME_CtaPacDet.AD_Session_ID = ? ")
//        .append(" AND EXME_CtaPacDet.EXME_CtaPac_ID = ? ")
//        .append(" ORDER BY cpe.EXME_CtaPacExt_ID, EXME_CtaPacDet.EXME_T_CtaPacDet_ID ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//
//			pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_User_ID"));
//			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Session_ID"));
//			pstmt.setInt(3, EXME_CtaPac_ID);
//			
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//					MTCtaPacDet plan = new MTCtaPacDet(ctx, rs, trxName);
//					list.add(plan);
//			}
//		} catch (Exception e) {    		
//    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//    	}finally {
//    		DB.close(rs,pstmt);
//    			pstmt = null;
//    			rs = null;
//    	}
//		return list;
//
//	}
//	
//	/**
//	 * 
//	 * @param ctx
//	 * @param secuencia
//	 * @param pack
//	 * @param EXME_CtaPac_ID
//	 * @param EXME_CtaPacExt_ID Extension Cero de la Cuenta paciente
//	 * @param trxName
//	 * @return
//	 * @deprecated NO UTILIZADO
//	 */
//	public static MTCtaPacDet getLineasPaquete(Properties ctx, int secuencia,
//			MPaqBaseVersion pack, int EXME_CtaPac_ID, int EXME_CtaPacExt_ID, 
//			String trxName) {
//
//		MTCtaPacDet chargePaq = new MTCtaPacDet(ctx, 0, trxName);
//
//		chargePaq.setEXME_CtaPac_ID(EXME_CtaPac_ID);
//		chargePaq.setEXME_CtaPacExt_ID(EXME_CtaPacExt_ID);
//		
//		chargePaq.setC_Currency_ID(pack
//				.getC_Currency_ID());
//		chargePaq.setCosto(pack.getBaseAmt().setScale(
//				2, BigDecimal.ROUND_HALF_UP));
//		chargePaq.setPriceList(pack.getTotalAmt()//modifique
//				.setScale(2, BigDecimal.ROUND_HALF_UP));
//		chargePaq.setPriceActual(pack.getTotalAmt()
//				.setScale(2, BigDecimal.ROUND_HALF_UP));
//		chargePaq.setPriceLimit(pack.getTotalAmt()
//				.setScale(2, BigDecimal.ROUND_HALF_UP));
//		chargePaq.setLineNetAmt(pack.getTotalAmt()
//				.setScale(2, BigDecimal.ROUND_HALF_UP));
//		chargePaq.setC_Tax_ID(pack.getC_Tax_ID());
//
//		chargePaq.setPriceActualInv(pack.getTotalAmt()
//				.setScale(2, BigDecimal.ROUND_HALF_UP));
//		chargePaq.setPriceListInv(pack.getBaseAmt()
//				.setScale(2, BigDecimal.ROUND_HALF_UP));
//		chargePaq.setPriceLimitInv(pack.getTotalAmt()
//				.setScale(2, BigDecimal.ROUND_HALF_UP));
//		chargePaq.setTaxAmt(pack.getTaxAmt().setScale(
//				2, BigDecimal.ROUND_HALF_UP));
//		chargePaq.setM_Product_ID(pack
//				.getM_Product_ID());
//		chargePaq.setC_UOM_ID(pack.getProduct()
//				.getC_UOM_ID());
//		chargePaq.setInvoice_UOM_ID(pack.getProduct()
//				.getC_UOM_ID());
//		chargePaq.setDiscount(pack.getDiscount()
//				.setScale(2, BigDecimal.ROUND_HALF_UP));
//        chargePaq.setDiscountAmt(Env.ZERO);
//        chargePaq.setDiscountFam(Env.ZERO);
//		chargePaq.setDescription("Cargo por concepto de Paquete - ["
//				+ pack.get_ID() + ", "
//				+ pack.getName() + "]");
//		chargePaq.setTipoLinea("PB");
//        chargePaq.setEXME_PaqBase_Version_ID(pack.getEXME_PaqBase_Version_ID());
//        chargePaq.setLine(0);
//        chargePaq.setDateOrdered(new Timestamp(System.currentTimeMillis()));
//        chargePaq.setQtyOrdered(Env.ONE);
//        chargePaq.setQtyReserved(Env.ONE);
//        chargePaq.setQtyDelivered(Env.ONE);
//        chargePaq.setQtyInvoiced(Env.ONE);
//        chargePaq.setFreightAmt(Env.ZERO);
//        chargePaq.setChargeAmt(Env.ZERO);
//        chargePaq.setIsDescription(false);
//        chargePaq.setQtyPaquete(Env.ZERO);
//        chargePaq.setQtyPendiente(Env.ONE);
//        chargePaq.setIsFacturado(false);
//        chargePaq.setAD_User_ID(Env.getContextAsInt(ctx, "#AD_User_ID"));
//        chargePaq.setAD_Session_ID(Env.getContextAsInt(ctx, "#AD_Session_ID"));
//        chargePaq.setSecuencia(secuencia);
//        chargePaq.setM_Warehouse_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID"));// El
//																				// almacen
//																				// lo
//		// tomamos del
//		// contexto.
//		MEXMEEstServ estServ = new MEXMEEstServ(ctx, Env.getContextAsInt(ctx,
//				"#EXME_EstServ_ID"), trxName);
//		chargePaq.setTipoArea(estServ.getTipoArea());
//		chargePaq.setEXME_Area_ID(estServ.getEXME_Area_ID());
//		chargePaq.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID());
//		return chargePaq;
//	}

}