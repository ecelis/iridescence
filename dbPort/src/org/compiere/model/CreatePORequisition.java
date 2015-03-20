package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CompiereUserError;
import org.compiere.util.CustomError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * 
 * @deprecated
 *
 */
public class CreatePORequisition {

	static String docno = null;
	private static Properties m_ctx = null;

	private static String m_trxName = null;
	
	/** Is Call of Action */
	private static boolean p_isWeb = false;
	
	private static int p_AD_Org_ID = 0;
	/** Warehouse */
	private static int p_M_Warehouse_ID = 0;
	/** Doc Date From */
	private static Timestamp p_DateDoc_From;
	/** Doc Date To */
	private static Timestamp p_DateDoc_To;
	/** Doc Date From */
	private static Timestamp p_DateRequired_From;
	/** Doc Date To */
	private static Timestamp p_DateRequired_To;
	/** Priority */
	private static String p_PriorityRule = null;
	/** User */
	private static int p_AD_User_ID = 0;
	/** Product */

	private static int p_M_Product_ID = 0;
	/** Requisition */
	private static int p_M_Requisition_ID = 0;

	/** Consolidate */
	private static boolean p_ConsolidateDocument = false;

	/** Order */
	private static MOrder m_order = null;
	/** Order Line */
	private static MOrderLine m_orderLine = null;

	private static int m_M_Requisition_ID = 0;
	private static int m_M_Product_ID = 0;
	private static int m_M_AttributeSetInstance_ID = 0;
	/** BPartner */
	private static MBPartner m_bpartner = null;

	private static boolean success = false;
	
	/* Para envi� de errores hacia ServerApps */
    private static List<CustomError> errores = null;
	// protected static CLogger log = CLogger.getCLogger (getClass());

	/**
	 * Metodo para crear la orden de compra apartir de la requisicion
	 * 
	 * @param Properties
	 *            ctx, String trxName, int AD_Org_ID, int M_Warehouse_ID,
	 *            Timestamp DateDoc_From, Timestamp DateDoc_To, Timestamp
	 *            DateRequired_From, Timestamp DateRequired_To, String
	 *            PriorityRule, int AD_User_ID, int M_Product_ID, int
	 *            M_Requisition_ID, boolean ConsolidateDocument
	 * @return String
	 */
	public static String create(Properties ctx, String trxName, int AD_Org_ID,
			int M_Warehouse_ID, Timestamp DateDoc_From, Timestamp DateDoc_To,
			Timestamp DateRequired_From, Timestamp DateRequired_To,
			String PriorityRule, int AD_User_ID, int M_Product_ID,
			int M_Requisition_ID, boolean ConsolidateDocument, boolean isWeb)
			throws Exception {
		success = false;
		
		m_ctx = ctx;
		m_trxName = trxName;
		p_isWeb = isWeb;
		p_AD_Org_ID = AD_Org_ID;
		p_M_Warehouse_ID = M_Warehouse_ID;
		p_DateDoc_From = DateDoc_From;
		p_DateDoc_To = DateDoc_To;
		p_DateRequired_From = DateRequired_From;
		p_DateRequired_To = DateRequired_To;
		p_PriorityRule = PriorityRule;
		p_AD_User_ID = AD_User_ID;
		p_M_Product_ID = M_Product_ID;
		p_M_Requisition_ID = M_Requisition_ID;
		p_ConsolidateDocument = ConsolidateDocument;
		m_order = null;
		m_orderLine = null;
		m_M_Requisition_ID = 0;
		m_M_Product_ID = 0;
		m_M_AttributeSetInstance_ID = 0;
		m_bpartner = null;
		success = false;
		errores = new ArrayList<CustomError>();
		if (p_M_Requisition_ID != 0) {
			// log.info("M_Requisition_ID=" + p_M_Requisition_ID);
			// System.out.println("DEBUG: Desde requisicion != 0");
			MRequisition req = new MRequisition(m_ctx, p_M_Requisition_ID,
					m_trxName);
			if (!MRequisition.DOCSTATUS_Completed.equals(req.getDocStatus()))
				if (p_isWeb) {
					CustomError error = new CustomError(
							"error.CPORequisition.RNOCompleted", null);
					errores.add(error);
				} else
					throw new CompiereUserError("@DocStatus@ = "
							+ req.getDocStatus());
			MRequisitionLine[] lines = req.getLines(false);
			for (int i = 0; i < lines.length; i++) {
				//Se revisa si la Requisicion ya ha generado OC 
				if (lines[i].getC_OrderLine_ID() == 0) {
					success = true;
					process(lines[i]);
				} else {
					success = false;
					if (p_isWeb) {
						CustomError error = new CustomError(
								"error.CPORequisition.HOrder", null);
						errores.add(error);
						//Para forzar la salida al primer error de este tipo
						i=lines.length;
					}
				}
			}
			if (success)
			{
				req.setIsMostrado(false);
				if(!req.save()){
					if (p_isWeb) {
						CustomError error = new CustomError(
								"error.CPORequisition.Order", null);
						errores.add(error);
					}
				}
			}
			closeOrder();
			return "";
		} // single Requisition
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM M_RequisitionLine rl ")
				.append("WHERE rl.C_OrderLine_ID IS NULL");
		if (p_AD_Org_ID != 0)
			sql.append(" AND AD_Org_ID=?");
		if (p_M_Product_ID != 0)
			sql.append(" AND M_Product_ID=?");
		// Requisition Header
		sql
				.append(
						" AND EXISTS (SELECT * FROM M_Requisition r WHERE rl.M_Requisition_ID=r.M_Requisition_ID")
				.append(" AND r.DocStatus='CO'");
		if (p_M_Warehouse_ID != 0)
			sql.append(" AND r.M_Warehouse_ID=?");
		//
		if (p_DateDoc_From != null && p_DateDoc_To != null)
			sql.append(" AND r.DateDoc BETWEEN ? AND ?");
		else if (p_DateDoc_From != null)
			sql.append(" AND r.DateDoc => ?");
		else if (p_DateDoc_To != null)
			sql.append(" AND r.DateDoc <= ?");
		//
		if (p_DateRequired_From != null && p_DateRequired_To != null)
			sql.append(" AND r.DateRequired BETWEEN ? AND ?");
		else if (p_DateRequired_From != null)
			sql.append(" AND r.DateRequired => ?");
		else if (p_DateRequired_To != null)
			sql.append(" AND r.DateRequired <= ?");
		//
		if (p_PriorityRule != null)
			sql.append(" AND r.PriorityRule => ?");
		if (p_AD_User_ID != 0)
			sql.append(" AND r.AD_User_ID=?");
		//
		sql.append(") ORDER BY ");
		if (!p_ConsolidateDocument)
			sql.append("M_Requisition_ID, ");
		sql.append("M_Product_ID, C_Charge_ID, M_AttributeSetInstance_ID");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), m_trxName);
			int index = 1;
			if (p_AD_Org_ID != 0)
				pstmt.setInt(index++, p_AD_Org_ID);
			if (p_M_Product_ID != 0)
				pstmt.setInt(index++, p_M_Product_ID);
			if (p_M_Warehouse_ID != 0)
				pstmt.setInt(index++, p_M_Warehouse_ID);
			if (p_DateDoc_From != null && p_DateDoc_To != null) {
				pstmt.setTimestamp(index++, p_DateDoc_From);
				pstmt.setTimestamp(index++, p_DateDoc_To);
			} else if (p_DateDoc_From != null)
				pstmt.setTimestamp(index++, p_DateDoc_From);
			else if (p_DateDoc_To != null)
				pstmt.setTimestamp(index++, p_DateDoc_To);
			if (p_DateRequired_From != null && p_DateRequired_To != null) {
				pstmt.setTimestamp(index++, p_DateRequired_From);
				pstmt.setTimestamp(index++, p_DateRequired_To);
			} else if (p_DateRequired_From != null)
				pstmt.setTimestamp(index++, p_DateRequired_From);
			else if (p_DateRequired_To != null)
				pstmt.setTimestamp(index++, p_DateRequired_To);
			if (p_PriorityRule != null)
				pstmt.setString(index++, p_PriorityRule);
			if (p_AD_User_ID != 0)
				pstmt.setInt(index++, p_AD_User_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				success = true; // si almenos entra una vez
				// System.out.println("llamando a process...");
				process(new MRequisitionLine(m_ctx, rs, m_trxName));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			rs = null;
		} catch (Exception e) {
			// log.log (Level.SEVERE, sql.toString(), e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
			if (rs != null)
				rs.close();
			rs = null;
		} catch (Exception e) {
			pstmt = null;
			rs = null;
		}
		closeOrder();
		return "";
	}

	/**
	 * Process Line
	 * 
	 * @param rLine
	 *            request line
	 * @throws Exception
	 */
	private static void process(MRequisitionLine rLine) throws Exception {
		if (rLine.getM_Product_ID() == 0 && rLine.getC_Charge_ID() == 0)

			// log.warning("Ignored Line" + rLine.getLine()
			// + " " + rLine.getDescription()
			// + " - " + rLine.getLineNetAmt());

			return;
		if (!p_ConsolidateDocument
				&& rLine.getM_Requisition_ID() != m_M_Requisition_ID)
			closeOrder();
		if (m_orderLine == null
				|| rLine.getM_Product_ID() != m_M_Product_ID
				|| rLine.getM_AttributeSetInstance_ID() != m_M_AttributeSetInstance_ID
				|| rLine.getC_Charge_ID() != 0) // single line per charge
			newLine(rLine);
		// Update Order Line
		m_orderLine.setQty(m_orderLine.getQtyOrdered().add(rLine.getQty()));
		// Update Requisition Line
		rLine.setC_OrderLine_ID(m_orderLine.getC_OrderLine_ID());
		if (!rLine.save()) {
			success = false;
			if (p_isWeb) {
				CustomError error = new CustomError(
						"error.CPORequisition.RLine", null);
				errores.add(error);
				success = false;
			} else
				//throw new CompiereSystemError("Cannot update Request Line");
				throw new Exception(Msg.getMsg(Env.getCtx(), " error.producto.preciolista"));
		} else
			success = true;

	} // process

	/**
	 * Create new Order
	 * 
	 * @param rLine
	 *            request line
	 * @param C_BPartner_ID
	 *            b.partner
	 * @throws Exception
	 */
	private static void newOrder(MRequisitionLine rLine, int C_BPartner_ID)
			throws Exception {
		// System.out.println("DEBUG: NEWORDER");
		if (m_order != null)
			closeOrder();

		// BPartner
		if (m_bpartner == null
				|| C_BPartner_ID != m_bpartner.getC_BPartner_ID())
			m_bpartner = new MBPartner(m_ctx, C_BPartner_ID, null);
		// Order
		m_order = new MOrder(m_ctx, 0, m_trxName);
		m_order.setIsSOTrx(false);
		m_order.setC_DocTypeTarget_ID();
		
		//Se valida la existencia del m_bpartner
		try{
			m_order.setBPartner(m_bpartner);
		}catch(Exception e){
			if (p_isWeb){
				MRequisition req = new MRequisition(m_ctx, p_M_Requisition_ID,m_trxName);
				CustomError error = new CustomError("error.noExisteSocioProd", new Object[]{req.getDocumentNo()});
				req = null;
				errores.add(error);
			}
			return;
		}
		//Se valida la existencia de moneda en el contexto
		try{
			m_order.setC_Currency_ID(MEXMECurrency.getMoneda(Env.getCtx()).get(0)
				.getKey());
		}catch(Exception e){
			if (p_isWeb){
				CustomError error = new CustomError("error.caja.moneda", null);
				errores.add(error);
			}
			return;
		}
		
		try {
			// Considerando que la lista de precios DEBE ser la de default
			if(p_isWeb)
			{
				m_order.setM_PriceList_ID(MEXMEPriceList.getDefault(m_ctx, true).getM_PriceList_ID());
			}
			else
				m_order.setM_PriceList_ID(MEXMEPriceList.getDefault(Env.getCtx(), true).getM_PriceList_ID());
		} catch (Exception e) {
			success = false;
			if (p_isWeb){
				CustomError error = new CustomError("error.getPriceListInfo", null);
				errores.add(error);
			}
			return;
		}
		
		//La organizacion transaccional se obtiene del contexto
		m_order.setAD_OrgTrx_ID(MEXMEEstServ.getEstServOrgTrx(Env.getCtx(), Env
					.getContextAsInt(Env.getCtx(), "#EXME_EstServ_ID")));
		// default po document type
		if (!p_ConsolidateDocument)
			m_order.setDescription(Msg.getElement(m_ctx, "M_Requisition_ID")
					+ ": " + rLine.getParent().getDocumentNo());
		// Prepare Save
		m_M_Requisition_ID = rLine.getM_Requisition_ID();
		// expert : gisela lee : guardar el almacen de parametro
		m_order.setM_Warehouse_ID(p_M_Warehouse_ID);
		// expert : gisela lee : guardar el almacen de parametro
		if (!m_order.save()) {
			if (p_isWeb) {
				CustomError error = new CustomError("error.CPORequisition.Order", null);
				errores.add(error);
				success = false;
			} else {
				success = false;
				//throw new CompiereSystemError("Cannot save Order");
				throw new Exception(Msg.getMsg(Env.getCtx(), "error.producto.vendor"));
			}

		} else {
			System.out.println("saving order..." + m_order.getC_Order_ID()
					+ "\n No. Documento: " + m_order.getDocumentNo());
			docno = m_order.getDocumentNo();
			success = true;
		}

	} // newOrder

	/**
	 * Close Order
	 * 
	 * @throws Exception
	 */
	private static void closeOrder() throws Exception {
		// System.out.println("DEBUG: CLOSEORDER");
		if (m_orderLine != null) {
			if (!m_orderLine.save()) {
				if (p_isWeb) {
					CustomError error = new CustomError("error.CPORequisition.OrderLine", null);
					errores.add(error);
					success = false;
				} else {
					success = false;
					//throw new CompiereSystemError("Cannot update Order Line");
					throw new Exception(Msg.getMsg(Env.getCtx(), "error.producto.preciolista"));
				}
			} else
				success = true;
		}
		if (m_order != null)
			m_order.load(m_trxName);

		m_order = null;
		m_orderLine = null;
	} // closeOrder

	/**
	 * New Order Line (different Product)
	 * 
	 * @param rLine
	 *            request line
	 * @throws Exception
	 */
	private static void newLine(MRequisitionLine rLine) throws Exception {
		
		/* 
		 * Siempre orderLine deberia de venir null
		 * Sin embargo si sucediese que no, 
		 * lo primero es guardar el objeto como venga
		 */
		if (m_orderLine != null)
			if (!m_orderLine.save()) {
				if (p_isWeb) {
					CustomError error = new CustomError("error.CPORequisition.OrderLine", null);
					errores.add(error);
					success = false;
				} else {
					success = false;
					//throw new CompiereSystemError("Cannot update Order Line");
					throw new Exception(Msg.getMsg(Env.getCtx(), " error.producto.preciolista"));
				}
			} else
				// System.out.println("updating new order line...");
				m_orderLine = null;
		MProduct product = null;
		// Get Business Partner
		
		int C_BPartner_ID = rLine.getC_BPartner_ID();
		if (C_BPartner_ID != 0)
			;
		else if (rLine.getC_Charge_ID() != 0) {
			MCharge charge = MCharge.get(m_ctx, rLine.getC_Charge_ID());
			C_BPartner_ID = charge.getC_BPartner_ID();
			//Si la linea de la Req es un cargo forzosamente debio haber seleccionado un proveedor
			if (C_BPartner_ID == 0)
				if (p_isWeb) {
					CustomError error = new CustomError("error.CPORequisition.NOVendor", null);
					errores.add(error);
				} else
					throw new CompiereUserError("No Vendor for Charge "
							+ charge.getName());
		} else {
			// Find Vendor from Produt
			product = MProduct.get(m_ctx, rLine.getM_Product_ID());
			MProductPO[] ppos = MProductPO.getOfProduct(m_ctx, product
					.getM_Product_ID(), null);
			for (int i = 0; i < ppos.length; i++) {
				if (ppos[i].isCurrentVendor()
						&& ppos[i].getC_BPartner_ID() != 0) {
					C_BPartner_ID = ppos[i].getC_BPartner_ID();
					break;
				}
			}
			if (C_BPartner_ID == 0 && ppos.length > 0)
				C_BPartner_ID = ppos[0].getC_BPartner_ID();
			//Si no se encontro proveedor en Producto por Proveedores
			if (C_BPartner_ID == 0)
				if (p_isWeb) {
					CustomError error = new CustomError("error.CPORequisition.NOVendorforProduct", new Object[]{product.getName()});
					errores.add(error);
				} else
					throw new CompiereUserError("No Vendor for "
							+ product.getName());
		}
		// New Order - Different Vendor
		if (m_order == null || m_order.getC_BPartner_ID() != C_BPartner_ID)
			newOrder(rLine, C_BPartner_ID);
		// No Order Line
		m_orderLine = new MOrderLine(m_order);
		if (product != null) {
			m_orderLine.setProduct(product);
			m_orderLine.setM_AttributeSetInstance_ID(rLine
					.getM_AttributeSetInstance_ID());
		} else {
			m_orderLine.setC_Charge_ID(rLine.getC_Charge_ID());
			m_orderLine.setPriceActual(rLine.getPriceActual());
		}
		m_orderLine.setAD_Org_ID(rLine.getAD_Org_ID());
		m_orderLine.setAD_OrgTrx_ID(MEXMEEstServ.getEstServOrgTrx(Env.getCtx(),
					Env.getContextAsInt(Env.getCtx(), "#EXME_EstServ_ID")));
		// Prepare Save
		m_M_Product_ID = rLine.getM_Product_ID();
		m_M_AttributeSetInstance_ID = rLine.getM_AttributeSetInstance_ID();
		if (!m_orderLine.save()) {
			if (p_isWeb) {
				CustomError error = new CustomError("error.entradasAlm.noCalcCost",null);
				CustomError errors = new CustomError("error.CPORequisition.NOVendorforProduct",null);
				errores.add(error);
				errores.add(errors);
				success = false;
			} else {
				success = false;
				//throw new CompiereSystemError("Cannot save Order Line");
				throw new Exception(Msg.getMsg(Env.getCtx(), "error.producto.socio.negocio"));
			}
		}

	} // newLine

	

	public static String getDocNo() {
		return docno;
	}

	public static List<CustomError> getErrorsWeb() {
		return errores;
	}

}
