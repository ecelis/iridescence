package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * 
 * @author rsolorzano
 * 
 */
public class CalloutPriceList extends CalloutEngine {

	/**
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String product(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (isCalloutActive()) {
			return "";
		}

		Integer M_Product_ID = null;

		if (value instanceof BigDecimal) {
			M_Product_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			M_Product_ID = (Integer) value;
		}

		if (M_Product_ID == null || M_Product_ID.intValue() == 0) {
			return "";
		}

//		// Validar que este dentro del charge master
//		final MProduct product = new MProduct(ctx, M_Product_ID, null);
//		if (M_Product_ID>0 && MEXMEMejoras.isControlaExistencias(
//				Env.getAD_Client_ID(Env.getCtx()),
//				Env.getAD_Org_ID(Env.getCtx()), null)
//				&& (product.getProdOrg() == null || (product.getProdOrg() != null && product
//						.getProdOrg().getAD_Org_ID() <= 0))) {
//			setCalloutActive(false);
//			return Utilerias.getLabel("msj.ligarProducto");
//		}
		
		// Validar si el producto este a nivel de organización
		final MProduct product = new MProduct(ctx, M_Product_ID, null);
		final String error = MProduct.isValidProductOrg(ctx, M_Product_ID, false);
		if(error!=null){
			mTab.setValue("M_Product_ID", null);
			setCalloutActive(false);
			return error;
		}

		setCalloutActive(true);

		mTab.setValue("C_UOM_ID", new Integer(product.getC_UOM_ID()));
		mTab.setValue("C_UOMVolume_ID",
				new Integer(product.getC_UOMVolume_ID()));

		setCalloutActive(false);
		return "";
	}

	/**
	 * calcula el precio de lista de la unidad de volumen en base al precio de
	 * lista de unidad minima capturado
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String priceList(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (isCalloutActive()) {
			return "";
		}

		setCalloutActive(true);

		String msg = "";
		BigDecimal PriceList = null;
		BigDecimal priceLstTmp = null;
		Integer M_Product_ID = 0;
		Integer MPriceListVersionID = 0;

		if (value instanceof BigDecimal && value != null) {
			if (((BigDecimal) value).compareTo(BigDecimal.ZERO) < 0) {
				PriceList = new BigDecimal(0);
				mTab.setValue("PriceList", new BigDecimal(0));
			} else {
				PriceList = (BigDecimal) value;
			}

		} else if (value == null) {
			PriceList = new BigDecimal(0);
			mTab.setValue("PriceList", new BigDecimal(0));
		}

		if (mTab.getValue("M_PriceList_Version_ID") instanceof BigDecimal
				&& mTab.getValue("M_PriceList_Version_ID") != null) {
			MPriceListVersionID = Integer.valueOf(mTab.getValue(
					"M_PriceList_Version_ID").toString());
		} else if (mTab.getValue("M_PriceList_Version_ID") instanceof Integer
				&& mTab.getValue("M_PriceList_Version_ID") != null) {
			MPriceListVersionID = (Integer) mTab
					.getValue("M_PriceList_Version_ID");
		}

		if (mTab.getValue("M_Product_ID") instanceof BigDecimal) {
			M_Product_ID = Integer.valueOf(mTab.getValue("M_Product_ID")
					.toString());
		} else if (mTab.getValue("M_Product_ID") instanceof Integer) {
			M_Product_ID = (Integer) mTab.getValue("M_Product_ID");
		}

		if (MPriceListVersionID > 0) {

			MPriceListVersion version = new MPriceListVersion(Env.getCtx(),
					MPriceListVersionID, null);

			MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);

			boolean isSOList = version.getM_PriceList().isSOPriceList(); // lista
																			// de
																			// precios
																			// de
																			// venta

			if (!isSOList && product != null) { // si es lista de precios de
												// compra, si se convierten los
												// valores

				if (product.getC_UOM_ID() == product.getC_UOMVolume_ID()) {

					mTab.setValue("PriceList_Vol", PriceList);

				} else {

					MUOMConversion rates = MEXMEUOMConversion
							.validateConversions(Env.getCtx(),
									product.getM_Product_ID(),
									product.getC_UOMVolume_ID(), null);

					if (rates == null) {
						log.saveError(Utilerias.getMsg(Env.getCtx(),
								"error.udm.noExisteConversion"), product
								.getName());
						msg = Utilerias.getMsg(Env.getCtx(),
								"error.udm.noExisteConversion");
						mField.setValue(mField.getOldValue(), true);
						setCalloutActive(false);
						return msg;
					}

					if (PriceList.compareTo(new BigDecimal(0)) > 0) {
						priceLstTmp = MEXMEUOMConversion.convertProductFrom(
								Env.getCtx(), product.getM_Product_ID(),
								product.getC_UOMVolume_ID(), PriceList, null,
								false);
						mTab.setValue("PriceList_Vol", priceLstTmp);
					} else {
						mTab.setValue("PriceList_Vol", 0);
					}
				}

			}

		}

		setCalloutActive(false);
		return "";

	}

	/**
	 * calcula el precio de lista de la unidad minima en base al precio de lista
	 * de unidad de volumen capturado
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String priceListVol(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (isCalloutActive()) {
			return "";
		}

		setCalloutActive(true);

		String msg = "";
		BigDecimal PriceListVol = null;
		BigDecimal priceLstTmp = null;
		Integer M_Product_ID = 0;
		Integer MPriceListVersionID = 0;

		if (value instanceof BigDecimal && value != null) {
			if (((BigDecimal) value).compareTo(BigDecimal.ZERO) < 0) {
				PriceListVol = new BigDecimal(0);
				mTab.setValue("PriceList_Vol", new BigDecimal(0));
			} else {
				PriceListVol = (BigDecimal) value;
			}

		} else if (value == null) {
			PriceListVol = new BigDecimal(0);
			mTab.setValue("PriceList_Vol", new BigDecimal(0));
		}

		if (mTab.getValue("M_PriceList_Version_ID") instanceof BigDecimal
				&& mTab.getValue("M_PriceList_Version_ID") != null) {
			MPriceListVersionID = Integer.valueOf(mTab.getValue(
					"M_PriceList_Version_ID").toString());
		} else if (mTab.getValue("M_PriceList_Version_ID") instanceof Integer
				&& mTab.getValue("M_PriceList_Version_ID") != null) {
			MPriceListVersionID = (Integer) mTab
					.getValue("M_PriceList_Version_ID");
		}

		if (mTab.getValue("M_Product_ID") instanceof BigDecimal) {
			M_Product_ID = Integer.valueOf(mTab.getValue("M_Product_ID")
					.toString());
		} else if (mTab.getValue("M_Product_ID") instanceof Integer) {
			M_Product_ID = (Integer) mTab.getValue("M_Product_ID");
		}

		if (MPriceListVersionID > 0) {

			MPriceListVersion version = new MPriceListVersion(Env.getCtx(),
					MPriceListVersionID, null);

			MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);

			boolean isSOList = version.getM_PriceList().isSOPriceList(); // lista
																			// de
																			// precios
																			// de
																			// venta

			if (!isSOList && product != null) { // si es lista de precios de
												// compra, si se convierten los
												// valores

				if (product.getC_UOM_ID() == product.getC_UOMVolume_ID()) {
					mTab.setValue("PriceList", PriceListVol);

				} else {

					MUOMConversion rates = MEXMEUOMConversion
							.validateConversions(Env.getCtx(),
									product.getM_Product_ID(),
									product.getC_UOMVolume_ID(), null);

					if (rates == null) {
						log.saveError(Utilerias.getMsg(Env.getCtx(),
								"error.udm.noExisteConversion"), product
								.getName());
						msg = Utilerias.getMsg(Env.getCtx(),
								"error.udm.noExisteConversion");
						mField.setValue(mField.getOldValue(), true);
						setCalloutActive(false);
						return msg;
					}

					if (PriceListVol.compareTo(new BigDecimal(0)) > 0) {
						priceLstTmp = MEXMEUOMConversion.convertProductTo(
								Env.getCtx(), product.getM_Product_ID(),
								product.getC_UOMVolume_ID(), PriceListVol,
								null, false);
						mTab.setValue("PriceList", priceLstTmp);
					} else {
						mTab.setValue("PriceList", Env.ZERO);
					}

				}

			}

		}

		setCalloutActive(false);
		return "";

	}

}
