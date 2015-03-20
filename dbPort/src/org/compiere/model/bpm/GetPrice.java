/**
 * 
 */
package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMEEsqDesLine;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MEXMEProductoPrecio;
import org.compiere.model.MPrecios;
import org.compiere.model.MProduct;
import org.compiere.model.PreciosVenta;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Localiza el precio (Venta) para los productos con convenios y sin
 * convenios
 * 
 * @author Twry
 * 
 */
public class GetPrice {
	/**	Logger */
	public static CLogger slog = CLogger.getCLogger (GetPrice.class);

	/**
	 * Obtener el precio desde el charge master
	 * 
	 * @param ctx
	 * @param pProductID
	 * @param valid
	 * @return
	 */

	public static MEXMEProductoPrecio calcularPrecio(final Properties ctx,
			final int pProductID, Timestamp valid) {
		if (pProductID == 0) {
			return null;
		}

		if (valid == null) {
			valid = DB.convert(ctx, new Date());
		}

		final MEXMEProductoPrecio precio = MEXMEProductoPrecio.calcularPrecio(
				ctx, pProductID, valid);
		return precio;
	}

	/**
	 * discount
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Nombre de transaccion
	 * @param pBPartnerID
	 *            Socio de negocio
	 * @param pBPGroupID
	 *            Grupo de socio de negocio
	 * @param pOrgID
	 *            Organizacion
	 * @param pClientID
	 *            Cliente
	 * @param tipoArea
	 *            Tipo de area
	 * @param pProductID
	 *            Producto
	 * @param pProdCategoryID
	 *            Categoria de producto
	 * @param date
	 *            Fecha
	 * @param price
	 *            Precio de lista
	 * @return Object[] = [0]Esquema de descuento [1]Precio menos descuento
	 *         [2]Monto descuento
	 */
	private static Object[] discount(final Properties ctx,    final String trxName,
			final int pBPartnerID,     final int pBPGroupID,  final int pOrgID,
			final int pClientID,       final String tipoArea, final int pProductID,
			final int pProdCategoryID, final Timestamp date,
			final BigDecimal price) {

		// [0]Esquema de descuento [1]Precio menos descuento [2]Monto descuento
		Object[] values = new Object[] { 0, Env.ZERO, Env.ZERO };

		// Primero los descuentos
		final MEXMEEsqDesLine lst = MEXMEEsqDesLine.esquemaDescuento(ctx,
				trxName, pBPartnerID, pBPGroupID, pOrgID, pClientID, tipoArea,
				pProductID, pProdCategoryID, date);

		// No existen esquemas de descuento
		if (lst != null) {
			BigDecimal total = Env.ZERO;
			BigDecimal discount = Env.ZERO;

			if (lst.getList_Discount() != null
					&& lst.getList_Discount().compareTo(Env.ZERO) > 0) {
				total = price.subtract(price.multiply(lst.getList_Discount()
						.divide(Env.ONEHUNDRED)));
				discount = price.multiply(lst.getList_Discount().divide(
						Env.ONEHUNDRED));
			} else {
				total = lst.getList_AddAmt();
				discount = Env.ZERO;
			}

			values = new Object[] { lst.getEXME_EsqDesLine_ID(),
					total.setScale(2, BigDecimal.ROUND_HALF_UP),
					discount.setScale(2, BigDecimal.ROUND_HALF_UP) };
		}

		return values;
	}

	/**
	 * Llenar un objeto de tipo MCtaPacDet para ontener el Precio de venta
	 * 
	 * @param ctx
	 * @param mProductID
	 * @param cBPartnerID
	 * @param cUomId
	 * @param value
	 * @param exmeCtaPacExtID
	 * @param tipoArea
	 * @param exmeCtaPacId
	 * @param trxName
	 * @return
	 */
	public static MPrecios getPriceCgo(final Properties ctx,
			final int mProductID, final int cBPartnerID, final int cUomId,
			final Timestamp value, final int exmeCtaPacExtID,
			final String tipoArea, final int exmeCtaPacId, final String trxName) {

		final MCtaPacDet mCtaPacDet = new MCtaPacDet(ctx, 0, null);
		mCtaPacDet.setM_Product_ID(mProductID);
		mCtaPacDet.setC_UOM_ID(cUomId);
		mCtaPacDet.setDateDelivered(value);
		mCtaPacDet.set_TrxName(trxName);

		if (exmeCtaPacExtID > 0) {
			mCtaPacDet.setEXME_CtaPacExt_ID(exmeCtaPacExtID);
		}
		if (tipoArea != null && !tipoArea.isEmpty()) {
			mCtaPacDet.setTipoArea(tipoArea);
		}
		if (exmeCtaPacId > 0) {
			mCtaPacDet.setEXME_CtaPac_ID(exmeCtaPacId);
		}
		return GetPrice.getPriceCargo(ctx, mCtaPacDet);
	}

	/**
	 * Precio de venta cargos a cuenta paciente
	 * 
	 * @param ctx
	 *            Contexto
	 * @param mCtaPacDet
	 *            Cargo a cuenta paciente
	 * @return MPrecios
	 */
	public static MPrecios getPriceCargo(final Properties ctx,
			final MCtaPacDet mCtaPacDet) {
		slog.info("GetPrice CtaPacDet:"+ mCtaPacDet.toString());

		MPrecios cargo = null;
		final GetPrice mGetPrice = new GetPrice();
		
		// facturacion directa es un cargo
		if (mCtaPacDet.getEXME_CtaPacExt_ID() == 0) {
			cargo = GetPrice.getPriceVta(ctx, mCtaPacDet.getM_Product_ID(), 0,
					mCtaPacDet.getC_UOM_ID(), mCtaPacDet.getDateDelivered(),
					Env.getTipoArea(ctx));
		} else {
			cargo = mGetPrice.getPriceCargo(ctx, mCtaPacDet, null);
		}
		return cargo;
	}

	/**
	 * Calculo del precio de venta
	 * 
	 * @param ctx
	 * @param mCtaPacDet
	 * @param trxName
	 * @return
	 */
	private MPrecios getPriceCargo(final Properties ctx,
			final MCtaPacDet mCtaPacDet, final String trxName) {
		MPrecios cargo = null;
		BigDecimal precio = Env.ZERO;

		// Buscar el precio en cargos anteriores
		if (!Env.getContextAsBooelan(ctx, "#RecalculaPre")
				&& mCtaPacDet.getEXME_CtaPac_ID()>0) {
			precio = MCtaPacDet.setPriceListFirstCharge(ctx,
					mCtaPacDet.getEXME_CtaPac_ID(),
					mCtaPacDet.getM_Product_ID(), mCtaPacDet.getC_UOM_ID(),
					trxName);
			if (precio.compareTo(Env.ZERO) != 0) {
				cargo = new MPrecios();// El precio creado con anterioridad
				cargo.setValuesDefault(ctx, precio, mCtaPacDet.getM_Product_ID(),
						mCtaPacDet.getC_UOM_ID());
			}
		}
		Object[] lst = null;

		// Suponiendo que cero no es un precio.
		if (precio.compareTo(Env.ZERO) == 0) {
			// Obtener el precio
			cargo = PreciosVenta.precioProdVtaCargos(ctx, mCtaPacDet,
					trxName);
		}

		// Descuentos
		if (mCtaPacDet.getExtension().getC_BPartner_ID() > 0) {
			// Obtener el grupo de socio
			final MBPartner socio = mCtaPacDet.getExtension().getBpartner();
			// Obtener la categoria de producto
			final MProduct product = mCtaPacDet.getProduct();
			lst = GetPrice.discount(ctx, null, socio.getC_BPartner_ID(),
					socio.getC_BP_Group_ID(), mCtaPacDet.getAD_Org_ID(),
					mCtaPacDet.getAD_Client_ID(), mCtaPacDet.getTipoArea(),
					product.getM_Product_ID(),
					product.getM_Product_Category_ID(),
					mCtaPacDet.getDateDelivered(), cargo.getPriceList());
		}

		setDiscount(cargo, lst);

		// Impuesto de precio unitario (Sobre el objeto MEXMETax el metodo
		// getAmount() regresa el importe)
		cargo.getTax(cargo.getPriceStd());

		return cargo;
	}

	/**
	 * Iniciar 
	 * @param mPrice
	 * @param lst
	 */
	private void setDiscount(final MPrecios mPrice, final Object[] lst){
		if(mPrice==null){
			return;
		}
		// Si existen descuentos// 0 : EsquemaID, 1: Total, 2: Descuento
		mPrice.setPriceStd(lst == null ? mPrice.getPriceList()
				: ((BigDecimal) lst[1]).compareTo(Env.ZERO) == 0 
				? mPrice.getPriceList() : (BigDecimal) lst[1]);
		
		mPrice.setEXME_EsqDesLine_ID(lst == null ? 0 
				: (Integer) lst[0]);
		
		mPrice.setDiscountAmt(lst == null ? Env.ZERO 
				: ((BigDecimal) lst[2]).compareTo(Env.ZERO) == 0 
				? Env.ZERO : (BigDecimal) lst[2]);

		mPrice.setDiscountPorc(lst == null ? Env.ZERO 
				: ((BigDecimal) lst[2]).compareTo(Env.ZERO) == 0 
				? Env.ZERO : (BigDecimal) lst[2]);
		
		if (Env.ZERO.compareTo(mPrice.getPriceStd()) == 0) {
			mPrice.setPriceStd(mPrice.getPriceList());
		}

	}
	
	/**
	 * Precio para una actividad paciente
	 * @param serv
	 * @return
	 */
	public static MPrecios getPriceActPac(final MEXMEActPacienteInd serv) {
		final GetPrice mGetPrice = new GetPrice();
		
		// buscar precio
		MPrecios cargo = null;
		BigDecimal precio = Env.ZERO;
		
		if(!Env.getContextAsBooelan(serv.getCtx(), "#RecalculaPre")
				&& serv.getActPacienteIndH()!=null
				&& serv.getActPacienteIndH().getEXME_CtaPac_ID()>0) {
			precio = MCtaPacDet.setPriceListFirstCharge(serv.getCtx(),
					serv.getActPacienteIndH().getEXME_CtaPac_ID(),
					serv.getM_Product_ID(), serv.getC_UOM_ID(),
					serv.get_TrxName());
			if (precio.compareTo(Env.ZERO) != 0) {
				cargo = new MPrecios();// El precio creado con anterioridad
				cargo.setValuesDefault(serv.getCtx(), precio, serv.getM_Product_ID(),
						serv.getC_UOMVolume_ID() == 0 ? serv
								.getC_UOM_ID() : serv.getC_UOMVolume_ID());
			}
		} 
		
		
		if (precio.compareTo(Env.ZERO) == 0) {
			// buscar precio
			cargo = PreciosVenta.precioProdVtaActPac(serv);
		}
		
		if(cargo!=null){
			// aplicar descuento
			mGetPrice.aplicarDescuentoAct(serv, cargo); 
			// Impuesto de precio unitario (Sobre el objeto MEXMETax el metodo
			// getAmount() regresa el importe)
			cargo.getTax(cargo.getPriceStd());
		}
		return cargo;
	}
	
	
	/**
	 * Buscar y aplicar el descuento
	 * @param serv
	 *            MEXMEActPacienteInd
	 * @return
	 */
	public void aplicarDescuentoAct(final MEXMEActPacienteInd serv, final MPrecios cargo) {
		
		// Descuentos
		Object[] lst = null;
		if (serv.getActPacienteIndH().getC_BPartner_ID() > 0) {
			
			// Obtener el grupo de socio
			final MBPartner socio = new MBPartner(serv.getCtx(), serv
					.getActPacienteIndH().getC_BPartner_ID(), null);
			// Obtener la categoria de producto
			final MProduct product = new MProduct(serv.getCtx(),
					serv.getM_Product_ID(), null);
			
			lst = GetPrice.discount(
					serv.getCtx(),
					null,
					socio.getC_BPartner_ID(),
					socio.getC_BP_Group_ID(),
					Env.getAD_Org_ID(serv.getCtx()),
					Env.getAD_Client_ID(serv.getCtx()),
					Env.getTipoArea(serv.getCtx()),
					product.getM_Product_ID(),
					product.getM_Product_Category_ID(),
					serv.getActPacienteIndH().getDateDelivered() == null ? serv
							.getActPacienteIndH().getDateOrdered() : serv
							.getDateDelivered(), cargo==null?Env.ZERO:cargo.getPriceList());
		}
		
		setDiscount(cargo, lst);
	}

	/**
	 * Precio de venta cargos a cuenta paciente
	 * 
	 * @param ctx
	 * @param M_Product_ID
	 * @param C_BPartner_ID
	 * @param C_UOM_ID
	 * @param fechaCargo
	 * @param exme_ctapacext_id
	 * @return
	 */
	public static MPrecios getPriceVta(final Properties ctx,
			final int M_Product_ID, final int C_BPartner_ID,
			final int C_UOM_ID, final Timestamp fechaCargo,
			final String tipoArea) {
		final GetPrice mGetPrice = new GetPrice();
		// Obtener objeto del producto
		final MProduct product = new MProduct(ctx, M_Product_ID, null);
		// Obtener el precio
		final MPrecios mPrices = PreciosVenta.precioProdVta(ctx,
				tipoArea == null ? null : tipoArea, M_Product_ID,
				C_UOM_ID <= 0 ? product.getC_UOM_ID() : C_UOM_ID, 0,
						C_BPartner_ID, fechaCargo, null);
		// Obtener el descuento
		mGetPrice.aplicasDescuentoVta(ctx, product, C_BPartner_ID, 
				null, fechaCargo, tipoArea, mPrices);
		// Impuesto de precio unitario (Sobre el objeto MEXMETax el metodo
		// getAmount() regresa el importe)
		mPrices.getTax(mPrices.getPriceStd());
		return mPrices;
	}

	/**
	 * Buscar y aplicar el descuento
	 * @param ctx
	 * @param product
	 * @param C_BPartner_ID
	 * @param C_UOM_ID
	 * @param fechaCargo
	 * @param tipoArea
	 * @param cargo
	 */
	private void aplicasDescuentoVta(final Properties ctx,
			final MProduct product, final int C_BPartner_ID,
			final String trxName,   final Timestamp fechaCargo,
			final String tipoArea,  final MPrecios cargo) {

		// Descuentos
		Object[] lst = null;
		if (C_BPartner_ID > 0) {
			// Obtener el grupo de socio
			final MBPartner socio = new MBPartner(ctx, C_BPartner_ID, trxName);

			lst = GetPrice.discount(ctx, null, C_BPartner_ID,
					socio.getC_BP_Group_ID(), Env.getAD_Org_ID(ctx),
					Env.getAD_Client_ID(ctx), tipoArea,
					product.getM_Product_ID(), product.getM_Product_Category_ID(),
					fechaCargo, cargo.getPriceList());
		}

		setDiscount(cargo, lst);
	}

	/**
	 * Precio de movimiento
	 * @param ctx
	 * @param mProductID
	 * @param cBPartnerID
	 * @param cUomId
	 * @param value
	 * @param exmeCtaPacExtID
	 * @return
	 */
	public static MPrecios getPriceMov(final Properties ctx,
			final int mProductID, final int cBPartnerID, final int cUomId,
			final Timestamp value, final int exmeCtaPacId,
			final String tipoArea, final String trxName) {

		final MMovementLine mMovement = new MMovementLine(ctx, 0, null);
		mMovement.setM_Product_ID(mProductID);
		mMovement.setC_UOM_ID(cUomId);
		mMovement.setOp_Uom(cUomId);
		mMovement.setEXME_CtaPac_ID(exmeCtaPacId);
		mMovement.setMovement(new MMovement(ctx, 0, null));
		mMovement.getMovement().setEXME_CtaPac_ID(exmeCtaPacId);
		mMovement.getMovement().setMovementDate(value);
		mMovement.getMovement().setC_BPartner_ID(cBPartnerID);
		mMovement.set_TrxName(trxName);
		return GetPrice.getPriceMov(ctx, mMovement, trxName);
	}

	/**
	 * Precio de venta act paciente
	 * 
	 * @param ctx
	 * @param M_Product_ID
	 * @param C_BPartner_ID
	 * @param C_UOM_ID
	 * @param fechaCargo
	 * @param exme_ctapacext_id
	 * @return
	 */
	public static MPrecios getPriceMov(final Properties ctx,
			final MMovementLine serv, final String trxName) {
		//
		final GetPrice mGetPrice = new GetPrice();
		serv.set_TrxName(trxName);// Obtener el grupo de socio
		MPrecios   cargo = null;
		BigDecimal precio = Env.ZERO;
		
		// Buscar el precio en cargos anteriores
		if(serv.getEXME_CtaPac_ID()>0
				&& !Env.getContextAsBooelan(ctx, "#RecalculaPre")) {
			precio = MCtaPacDet.setPriceListFirstCharge(ctx,
					serv.getEXME_CtaPac_ID(),
					serv.getM_Product_ID(), serv.getC_UOM_ID(),
					trxName);
			if(precio==null)precio = Env.ZERO;
			if (precio.compareTo(Env.ZERO) != 0) {
				cargo = new MPrecios();// El precio creado con anterioridad
				cargo.setValuesDefault(ctx, precio, serv.getM_Product_ID(),
						serv.getOp_Uom());
			}
		}
		
		if (precio.compareTo(Env.ZERO) == 0) {
			// Obtener el precio
			cargo = PreciosVenta.precioProdVtaMovement(ctx, serv, trxName);
		}

		if(cargo!=null){
			mGetPrice.aplicarDescuentoMov(ctx,serv,cargo,trxName);
			// Impuesto de precio unitario (Sobre el objeto MEXMETax el metodo
			// getAmount() regresa el importe)
			cargo.getTax(cargo.getPriceStd());
		}
		return cargo;

	}

	/**
	 * Buscar y aplicar el descuento
	 * @param ctx
	 * @param serv
	 * @param cargo
	 * @param trxName
	 * @return
	 */
	private void aplicarDescuentoMov(final Properties ctx,
				final MMovementLine serv, final MPrecios cargo, final String trxName) {
		
		// Descuentos
		Object[] lst = null;
		if (serv.getMovement().getC_BPartner_ID() > 0) {
			final MBPartner socio = new MBPartner(ctx, serv.getMovement()
					.getC_BPartner_ID(), trxName);
			// Obtener la categoria de producto
			final MProduct product = new MProduct(ctx, serv.getM_Product_ID(),
					trxName);
			
			lst = GetPrice.discount(ctx, null, socio.getC_BPartner_ID(), socio
					.getC_BP_Group_ID(), Env.getAD_Org_ID(ctx), Env
					.getAD_Client_ID(ctx), Env.getTipoArea(ctx), product
					.getM_Product_ID(), product.getM_Product_Category_ID(),
					serv.getMovement().getMovementDate(), cargo.getPriceList());
		}
		
		setDiscount(cargo, lst);
	}
}
