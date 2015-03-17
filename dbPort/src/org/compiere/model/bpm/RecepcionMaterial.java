package org.compiere.model.bpm;

import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MEXMEUser;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPriceList;
import org.compiere.model.X_C_Order;
import org.compiere.model.X_M_Inventory;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *
 * @author
 *
 */
public class RecepcionMaterial {

	private int orderID;
	private final Properties ctx;
	private String actionMessage;
	private final String documentNo;
	private final String description;
	private final int cDocTypeID;
	private final Timestamp fecha;
	private final int cbPartnerID;
	private final int wareHouseID;
	private final int adOrgTrxID;
	private List<MInOutLine> orderLineLst = null;
	private MOrder order = null;
	private MInOut mInOut = null;

	private static CLogger logger = CLogger.getCLogger(RecepcionMaterial.class);

	/**
	 *
	 * @param ctx
	 * @param documentNo
	 * @param description
	 * @param cDocTypeID
	 * @param timestamp
	 * @param cbPartnerID
	 * @param priceListID
	 * @param wareHouseID
	 * @param adOrgTrxID
	 * @param orderLineLst
	 * @param isWeb
	 */
	public RecepcionMaterial(final Properties ctx, final String documentNo,
			final String description, final int cDocTypeID, final Timestamp timestamp,
			final int cbPartnerID, final int wareHouseID, final int adOrgTrxID,
			final List<MInOutLine> orderLineLst) {
		logger.info("RecepcionMaterial");
		this.ctx = ctx;
		this.documentNo = documentNo;
		this.description = description;
		this.adOrgTrxID = adOrgTrxID;
		this.cbPartnerID = cbPartnerID;
		this.fecha = timestamp;
		this.orderLineLst = orderLineLst;
		this.cDocTypeID = cDocTypeID;
		this.wareHouseID = wareHouseID;

	}

	public RecepcionMaterial(final Properties ctx, final MInOut mInOut, final String description, final int cDocTypeID,
			final List<MInOutLine> orderLineLst) {
		logger.info("RecepcionMaterial");
		this.ctx = ctx;
		this.mInOut = mInOut;
		this.documentNo = mInOut.getDocumentNo();
		this.description = description;
		this.adOrgTrxID = mInOut.getAD_OrgTrx_ID();
		this.cbPartnerID = mInOut.getC_BPartner_ID();
		this.fecha = mInOut.getDateOrdered();
		this.orderLineLst = orderLineLst;
		this.cDocTypeID = cDocTypeID;
		this.wareHouseID = mInOut.getM_Warehouse_ID();

	}

	/**
	 * Socio de negocios y lista de precios no deben ser obligatorios
	 *
	 * @return
	 */
	public boolean saveOrders(final String trxName, final boolean consigna) {
		logger.info("RecepcionMaterial.saveOrders");
		boolean success = false;
		try {
			// guardamos el encabezado de la orden de compra
			if (consigna) {
				order = getPoConsigna(trxName);
				if(order.getC_Order_ID()<= 0){
					setOrderInfo(trxName);
				}
			} else {
				order = new MOrder(ctx, 0, trxName);
				setOrderInfo(trxName);
			}

			if (order.save(trxName)) {
				logger.info("RecepcionMaterial.saveOrders.if(order.save(trxName)) = true");
				success = saveLine(order);
			} else {
				logger.info("RecepcionMaterial.saveOrders.if(order.save(trxName)) = false");
				success = false;
			}

//			 success = order.save(trxName) && saveLine(order);

		} catch (final Exception e) {
			logger.severe("RecepcionMaterial.saveOrders.catch >> " + e.getLocalizedMessage());
			success = false;
		}
		return success;
	}

	private void setOrderInfo(final String trxName) {
		order.setIsSOTrx(false);

//			if (this.documentNo != null && !this.documentNo.equals("")) {
//				order.setDocumentNo("Ref"+this.documentNo);
//			}
		order.setDescription(this.description);
		order.setDocStatus(X_C_Order.DOCSTATUS_Drafted);
		order.setDocAction(X_C_Order.DOCACTION_Complete);
		order.setIsTransferred(X_C_Order.ISTRANSFERRED_Transferred);
		order.setC_DocType_ID(this.cDocTypeID);
		order.setC_DocTypeTarget_ID(this.cDocTypeID);
		order.setDateOrdered(this.fecha == null ? new Timestamp(System.currentTimeMillis()) : this.fecha);
		order.setDateAcct(this.fecha == null ? new Timestamp(System.currentTimeMillis()) : this.fecha);

		// Socio de negocios
		if (cbPartnerID > 0) {
			order.setC_BPartner_ID(this.cbPartnerID);

			final MBPartner bp = new MBPartner(ctx, cbPartnerID, trxName);
			if (bp != null) {
				if (mInOut.getM_PriceList_ID() > 0) {
					order.setM_PriceList_ID(mInOut.getM_PriceList_ID());
					if (mInOut.getC_Currency_ID() > 0) {
						order.setC_Currency_ID(mInOut.getC_Currency_ID());
					} else {
						final MPriceList pl = new MPriceList(ctx, mInOut.getM_PriceList_ID(), null);
						order.setC_Currency_ID(pl != null && pl.getC_Currency_ID() > 0 ? pl.getC_Currency_ID() : Env.getC_Currency_ID(ctx));
					}

				} else if (bp.getPO_PriceList_ID() > 0) {
					order.setM_PriceList_ID(bp.getPO_PriceList_ID());

					final MPriceList pl = new MPriceList(this.ctx, bp.getPO_PriceList_ID(), null);
					order.setC_Currency_ID(pl != null && pl.getC_Currency_ID() > 0 ? pl.getC_Currency_ID() : Env.getC_Currency_ID(ctx));
				}

				final int ilPBPLocationId = bp.getPrimaryC_BPartner_Location_ID();
				if (ilPBPLocationId > 0) {
					order.setC_BPartner_Location_ID(ilPBPLocationId);
				}
			}
		}

		order.setC_Currency_ID(order.getC_Currency_ID() > 0 ? order.getC_Currency_ID() : Env.getC_Currency_ID(ctx));
		order.setM_Warehouse_ID(this.wareHouseID);

		// representante de ventas (admin)
		order.setSalesRep_ID(MEXMEUser.getSalesRepAdmin(this.ctx) > 0 ? MEXMEUser.getSalesRepAdmin(this.ctx) : Env.getAD_User_ID(ctx));

		// Organizacion de quien surte
		order.setAD_OrgTrx_ID(this.adOrgTrxID);
	}

	/**
	 * Save line order
	 *
	 * @param order
	 * @return
	 */
	public boolean saveLine(final MOrder order) {
		logger.info("RecepcionMaterial.saveLine");

		// insertamos las lineas de la orden de compra
		for (int i = 0; i < this.orderLineLst.size(); i++) {

			final MInOutLine movto = this.orderLineLst.get(i);
			logger.info("RecepcionMaterial.saveLine.prod="+movto.getM_Product_ID());

			final MOrderLine ol = new MOrderLine(this.ctx, 0, order.get_TrxName());
			ol.setOrder (order);
			ol.setC_Order_ID(order.getC_Order_ID());
//			ol.setLine((i + 1) * 1);
			ol.setM_Product_ID(movto.getM_Product_ID());
			ol.setDateOrdered(order.getDateOrdered());
			ol.setQtyEntered(movto.getQtyEntered());
			ol.setQtyOrdered(movto.getQtyEntered());// Rev.
			ol.setC_UOM_ID(movto.getC_UOM_ID());
			ol.setC_Currency_ID(order.getC_Currency_ID());
			ol.setPriceActual(movto.getPriceActual());// Rev.
			ol.setPriceList(movto.getPriceList());// Rev.
			ol.setPriceLimit(movto.getPriceLimit());// Rev.

			//rsolorzano cambios para unidades de medida
			ol.setQtyEntered_Vol(movto.getQtyEntered_Vol());
			ol.setQtyOrdered_Vol(movto.getQtyEntered_Vol());
			ol.setC_UOMVolume_ID(movto.getC_UOMVolume_ID());
			ol.setPriceActual_Vol(movto.getPriceActual_Vol());
			ol.setPriceList_Vol(movto.getPriceList_Vol());
			ol.setPriceEntered_Vol(movto.getPriceActual_Vol());


			// Agregamos el priceEntered, eruiz
			ol.setPriceEntered(movto.getPriceActual());
			ol.setC_Tax_ID(movto.getC_Tax_ID());// Rev.
			ol.setC_BPartner_ID(order.getC_BPartner_ID());
			ol.setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());

			ol.setAD_OrgTrx_ID(this.adOrgTrxID);// Organizacion de quien
			// surte

			ol.setDiscount(movto.getDiscount());
			ol.setLotInfo(movto.getLotInfo());// Lote Omar de la Rosa
			ol.setM_AttributeSetInstance_ID(movto
					.getM_AttributeSetInstance_ID());// Lote Omar de la Rosa
			ol.setAutomatic(true);
			if (ol.save(order.get_TrxName())) {
				logger.info("RecepcionMaterial.saveLine.if (ol.save(order.get_TrxName()))=true");

				// Creamos las referencias de order line con inoutline
				this.orderLineLst.get(i).setC_OrderLine_ID(ol.getC_OrderLine_ID());

				//
				if((this.orderLineLst.get(i)).save(order.get_TrxName())){
					logger.info("RecepcionMaterial.saveLine.if(!(this.orderLineLst.get(i)).save(order.get_TrxName()))=true");
				} else {
					logger.info("RecepcionMaterial.saveLine.if(!(this.orderLineLst.get(i)).save(order.get_TrxName()))=false");
					return false;
				}
				// se elimino el calculo de costo promedio por orden de Gerardo
				// Camargo 12 Ago 2008

			} else {
				logger.info("RecepcionMaterial.saveLine.if (ol.save(order.get_TrxName()))=false");
				return false;
			}
		}
		return true;
	}

	/**
	 * Obtenemos la orden de compra donde se insertan las requisiciones
	 * @param trxName
	 * @return orden de compra de consigna
	 */
	private MOrder getPoConsigna(final String trxName) {
		MOrder order;
		final String sql = "SELECT c_order_id FROM c_order WHERE process= ? AND docstatus = ? AND ad_client_id = ? AND ad_org_id = ? ";
		final int id = DB.getSQLValue(trxName, sql, new Object[] { "N", X_M_Inventory.DOCSTATUS_Drafted, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx) });
		if (id > 0 ) {
			order = new MOrder(ctx, id, trxName);
		}else{
			order = new MOrder(ctx, 0, trxName);
			order.setisConsigna(true);
		}

		return order;
	}


	/**
	 * Complete order
	 *
	 * @return
	 */
	public String orderComplete() {
		logger.info("RecepcionMaterial.orderComplete");

		String success = order.completeIt();
		order.setDocStatus(success);
		logger.info("RecepcionMaterial.orderComplete.order.setDocStatus"+success);

		success = order.save() ? success : DocAction.STATUS_Invalid;
		return success;
	}

	public MOrder getOrder() {
		return order;
	}

	public void setOrder(final MOrder order) {
		this.order = order;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public int getOrderID() {
		return orderID;
	}

	public String getActionMessage() {
		return actionMessage;
	}
}
