package org.compiere.model;

import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.bpm.GetPrice;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Calculo de los precios de venta para los productos
 * 
 * @author twry perez
 * @created 11 de febrero de 2005
 * @version $Revision: 1.36 $
 */

public class PreciosVenta {

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(PreciosVenta.class);
	/** Retorno de la clase */
	private MPrecios precios = new MPrecios();

	/**
	 * Constructor
	 */
	public PreciosVenta() {
		super();
	}

	/**
	 * Informacion del objeto MPrecios
	 */
	public String toString() {
		StringBuilder logMsg = new StringBuilder(100);
		logMsg.append(" PVH  ... priceList ").append(precios.getPriceList())
				.append(" priceStd ").append(precios.getPriceStd())
				.append(" priceLimit ").append(precios.getPriceLimit());
		return logMsg.toString();
	}

	/**
	 * Obtiene el precio PARA CARGOS A CUENTA PACIENTE en el recalculo de
	 * precios
	 * 
	 * Este proceso no buscar el precio del primer cargo esta busqueda se hace
	 * en el método que invoca
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param cargo
	 *            : Cargo MCtaPacDet
	 * @param trxName
	 *            : Nombre de transacción
	 * @return MCtaPacDet con los precios obtenidos
	 */
	public static MCtaPacDet precioProdVtaFxE(final Properties ctx,
			final MCtaPacDet cargo, final String trxName) {

		return PreciosVenta.precioProdVtaCargos(ctx, cargo, trxName)
				.preciosActual(cargo);
	}

	/**
	 * Obtiene el precio PARA CARGOS A CUENTA PACIENTE
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param cargo
	 *            : Cargo MCtaPacDet
	 * @param trxName
	 *            : Nombre de transacción
	 * @return MCtaPacDet con los precios obtenidos
	 */
	public static MPrecios precioProdVtaCargos(final Properties ctx,
			final MCtaPacDet cargo, final String trxName) {

		int listaPre = 0;
		int socioId = 0;
		if (cargo.getExtension() != null) {
			if (cargo.getExtension().getC_BPartner_ID() > 0) {
				// Solo si el socio de negocios es una aseguradora se toma su
				// lista de precios
				final MEXMEBPartner mBPartner = new MEXMEBPartner(ctx, cargo
						.getExtension().getC_BPartner_ID(), trxName);
				listaPre = mBPartner.getM_PriceList_ID();
				socioId = cargo.getExtension().getC_BPartner_ID();
			}
		}

		// Se requiere el tipo de area para tomar la lista de precios correcta
		// y el esquema de descuento
		String tipoArea = cargo.getTipoArea() == null ? cargo.getTipoArea()
				: X_EXME_TipoPaciente.TIPOAREA_Hospitalization;
		if (cargo.getCtaPac() != null) {
			if (cargo.getCtaPac().getTipoPaciente() != null) {
				tipoArea = cargo.getCtaPac().getTipoPaciente().getTipoArea();
			} else {
				tipoArea = cargo.getCtaPac().getTipoArea();
			}
		}

		return new PreciosVenta().precioProdVtaParams(ctx, listaPre, socioId, 
				cargo.getM_Product_ID(), cargo.getC_UOM_ID(),
				cargo.getDateDelivered(), tipoArea, trxName);
	}

	/**
	 * Obtiene el precio PARA SOLICITUDES DE SERVICIOS Y MEDICAMENTOS
	 * 
	 * @param ctx
	 *            : contexto
	 * @param actPacInd
	 *            : Solicitud de servicio/Prescripción médica
	 * @param trxName
	 *            : Nombre de transacción
	 * @return MPrecios con los precios obtenidos
	 */
	public static MPrecios precioProdVtaActPac(
			final MEXMEActPacienteInd actPacInd) {
		int listaPre = 0;
		if (actPacInd.getActPacienteIndH().getC_BPartner_ID() > 0) {
			MEXMEBPartner mBPartner = new MEXMEBPartner(actPacInd.getCtx(),
					actPacInd.getActPacienteIndH().getC_BPartner_ID(),
					actPacInd.get_TrxName());
//			if (mBPartner.isFacturarAseg()) {
				listaPre = mBPartner.getM_PriceList_ID();
//			}
		}

		String tipoArea = actPacInd.getEXME_ActPacienteIndH_ID() == 0 ? X_EXME_TipoPaciente.TIPOAREA_Hospitalization
				: actPacInd.getActPacienteIndH().getCtaPac().getTipoPaciente()
						.getTipoArea();
		return new PreciosVenta()
				.precioProdVtaParams(
						actPacInd.getCtx(),
						listaPre // serv.getActPacienteIndH().getM_PriceList_ID()
						,
						actPacInd.getActPacienteIndH().getC_BPartner_ID(),
						actPacInd.getM_Product_ID(),
						actPacInd.getC_UOMVolume_ID() == 0 ? actPacInd
								.getC_UOM_ID() : actPacInd.getC_UOMVolume_ID(),
						actPacInd.getActPacienteIndH().getDateOrdered() == null ? actPacInd
								.getActPacienteIndH().getDateOrdered()
								: actPacInd.getDateOrdered(), tipoArea// (hospitalizacion
																		// /
																		// Ambulatorio)
						, actPacInd.get_TrxName());
	}

	/**
	 * Obtiene el precio PARA MOVIMIENTO DE INVENTARIO
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param cargo
	 *            : Cargo MCtaPacDet
	 * @param trxName
	 *            : Nombre de transacción
	 * @return MCtaPacDet con los precios obtenidos
	 */
	public static MPrecios precioProdVtaMovement(final Properties ctx,
			final MMovementLine movement, final String trxName) {

		// Suponemos que los movimientos de inventario son en hospitalizacion
		// pero si hay cuenta paciente tomamos ese tipo de area decauerdo
		// al tipo de paciente
		String tipoArea = X_EXME_TipoPaciente.TIPOAREA_Hospitalization;
		if (movement.getMovement().getEXME_CtaPac_ID() > 0) {
			tipoArea = movement.getMovement().getCtaPac().getTipoPaciente()
					.getTipoArea();
		}
		if (movement.getEXME_CtaPac_ID() > 0) {
			tipoArea = movement.getCtaPac().getTipoPaciente().getTipoArea();
		}

		int listaPre = 0;
		if (movement.getMovement().getC_BPartner_ID() > 0) {
			MEXMEBPartner mBPartner = new MEXMEBPartner(ctx, movement
					.getMovement().getC_BPartner_ID(), trxName);
//			if (mBPartner.isFacturarAseg()) {
				listaPre = mBPartner.getM_PriceList_ID();
//			}
		}

		return new PreciosVenta().precioProdVtaParams(ctx, listaPre // cargo.getCtaPac().getM_PriceList_ID()
				, movement.getMovement().getC_BPartner_ID(), movement
						.getM_Product_ID(), movement.getOp_Uom(), movement
						.getMovement().getMovementDate(), tipoArea, trxName);
	}

	/**
	 * Obtiene el precio PARA PAQUETES, PUNTO DE VENTA (SIN CUENTA)
	 * 
	 * @param ctx
	 *            : contexto
	 * @param tipoArea
	 *            : tipo de area
	 * @param mProductID
	 *            : producto
	 * @param cUOMID
	 *            : unidad de medida
	 * @param mPriceListID
	 *            : lista de precios
	 * @param cBPartnerID
	 *            : socio de negocios
	 * @param fecha
	 *            : fecha de vigencia
	 * @param trxName
	 *            : nombre de transaccion
	 * @return MPrecios con los precios obtenidos
	 */
	public static MPrecios precioProdVta(final Properties ctx,
			final String tipoArea, final int mProductID, final int cUOMID,
			final int mPriceListID, final int cBPartnerID,
			final Timestamp fecha, final String trxName) {
		s_log.info("********** precioProdVta **********");
		int listaPre = 0;
		if (cBPartnerID > 0) {
			MEXMEBPartner mBPartner = new MEXMEBPartner(ctx, cBPartnerID,
					trxName);
//			if (mBPartner.isFacturarAseg()) {
				listaPre = mBPartner.getM_PriceList_ID();
//			}
		}
		return new PreciosVenta().precioProdVtaParams(ctx, listaPre // mPriceListID
				, cBPartnerID, mProductID, cUOMID, fecha, tipoArea // (hospitalizacion
																	// / caja)
				, trxName);
	}

	/*************************************************************************************************/

	/**
	 * Calculo de precios
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param pMPriceListID
	 *            : Lista de precios
	 * @param pCBPartnerID
	 *            : Socio de negocios
	 * @param pMProductID
	 *            : Producto
	 * @param pCUomID
	 *            : Unidad de medida de busqueda de producto
	 * @param fecha
	 *            : Fecha de vigencia
	 * @param tipoArea
	 *            : Tipo de paciente o Area en la que se busca el precio
	 * @param trxName
	 *            : nombre de transaccion
	 * @return MPrecios con los precios obtenidos
	 */
	private MPrecios precioProdVtaParams(final Properties ctx,
			final int pMPriceListID, final int pCBPartnerID,
			final int pMProductID, int pCUomID, Timestamp fecha,
			final String tipoArea, final String trxName) {

		// validamos que los datos obligatorios no vengan nulos o en cero
		if (ctx == null || pMProductID <= 0) {
			precios.setProcessMsg("No existen valores para los datos requerido: Producto");
			s_log.finer("there are no values for the required data: Product, UoM, Qty ...");
			return precios;
		}
		final MProduct mProduct = new MProduct(ctx, pMProductID, trxName);

		if (fecha == null) {
			fecha = new Timestamp(System.currentTimeMillis());
		}

		if (pCUomID == 0) {
			pCUomID = mProduct.getC_UOM_ID();
		}

		// Venta al publico
		int isSalePub = Env.getM_PriceList_Reg_ID(ctx);// isSalePub = 10001060;
		// Se inicializa con la lista de precios del parametro
		int priceList = pMPriceListID;
		// forzar busqueda
		boolean force = "Y".equals(
				Env.getContext(ctx, "#IsPriceZero"));
		// seleccion de lista de precios por proceso/tipo paciente
		// if(pMPriceListID<=0 ||
		// X_EXME_TipoPaciente.TIPOAREA_MedicalConsultation.equals(tipoArea)){

		// Lista de precios de consulta externa y farmacia
		if (X_EXME_TipoPaciente.TIPOAREA_MedicalConsultation.equals(tipoArea)
				|| tipoArea == null
				|| X_EXME_TipoPaciente.TIPOAREA_Other.equals(tipoArea)) {//

			if (mProduct.getProductClass()
					.equals(X_M_Product.PRODUCTCLASS_Drug)
					|| mProduct.getProductClass().equals(
							X_M_Product.PRODUCTCLASS_MedicalSupplies)) {
				// La lista de precios siempre debe ser
				priceList = Env.getM_PriceList_Reg_ID(ctx);
				force = false;
			} else {
				priceList = Env.getM_PriceListSE_ID(ctx);
				force = true;
				
				if(pMPriceListID > 0){
					priceList = pMPriceListID;
				}
			}
		}

		// Lista de precios de ambulatorio
		if (priceList <= 0
				&& (X_EXME_TipoPaciente.TIPOAREA_Ambulatory.equals(tipoArea) || X_EXME_TipoPaciente.TIPOAREA_Emergency
						.equals(tipoArea))) {//

			priceList = Env.getM_PriceListSE_ID(ctx);
			force = true;
		}

		if (priceList <= 0
				&& !(X_EXME_TipoPaciente.TIPOAREA_Ambulatory.equals(tipoArea)
						|| X_EXME_TipoPaciente.TIPOAREA_MedicalConsultation
								.equals(tipoArea)
						|| X_EXME_TipoPaciente.TIPOAREA_Emergency
								.equals(tipoArea)
						|| X_EXME_TipoPaciente.TIPOAREA_Other.equals(tipoArea) || tipoArea == null)) {//
			priceList = MEXMEConfigPre.getPriceList(ctx, trxName).getM_PriceList_ID();
			force = true;
		}

		// Por defecto
		if (priceList <= 0) {
			final MPriceList mPriceList = MPriceList.getDefault(ctx, true);
			priceList = mPriceList == null ? 0 : mPriceList.getM_PriceList_ID();
			force = true;
		}
		// }

		return precioProdVtaProcess(ctx, priceList, pCBPartnerID, pMProductID,
				pCUomID, fecha, force, isSalePub == priceList, trxName);
	}

	/**
	 * Calculo de precios
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param pMPriceListID
	 *            : Lista de precios
	 * @param pCBPartnerID
	 *            : Socio de negocios
	 * @param pMProductID
	 *            : Producto
	 * @param pCUomID
	 *            : Unidad de medida de busqueda de producto
	 * @param fecha
	 *            : Fecha de vigencia
	 * @param force
	 *            : Forzar el precio del producto
	 * @param isSalePub
	 *            : Si es el proceso de consulta externa/farmacia/cafeteria
	 *            //NOTA SE DEBE DE USAR LO MENOS POSIBLE
	 * @param trxName
	 *            : nombre de transaccion
	 * @return MPrecios con los precios obtenidos
	 */
	private MPrecios precioProdVtaProcess(final Properties ctx,
			final int pMPriceListID, final int pCBPartnerID,
			final int pMProductID, final int pCUomID, final Timestamp fecha,
			final boolean force, final boolean isSalePub, final String trxName) {

		s_log.info("********** precioProdVta **********");

		int lMPriceListID = pMPriceListID;
		int lCBPartnerID = pCBPartnerID;
		int lCUomID = pCUomID;

		try {

			// Producto
			final MProduct mProduct = new MProduct(ctx, pMProductID, trxName);

			// Lista de precios
			final MProductPricing mProductPrice = shipment(ctx,
					mProduct.getM_Product_ID(), lMPriceListID, lCBPartnerID,
					lCUomID, fecha, force, isSalePub,
					pCUomID == mProduct.getC_UOMVolume_ID());

			if (mProductPrice != null) {

				// Si no se definio la unidad de medida o es la unidad minima
				if (pCUomID == 0 || pCUomID == mProduct.getC_UOM_ID()) {
					// Se localiza el precio en alguna lista de precios
					precios.setPriceList(mProductPrice.getPriceList() != null ? mProductPrice
							.getPriceList() : Env.ZERO);

					if (mProductPrice.isCalculated()) {
						precios.setPriceLimit(mProductPrice.getPriceLimit() != null ? mProductPrice
								.getPriceLimit() : Env.ZERO);
						precios.setPriceStd(mProductPrice.getPriceStd() != null ? mProductPrice
								.getPriceStd() : Env.ZERO);
						precios.setSeObtuvoPrecio(mProductPrice.isCalculated());// true:
																				// Un
																				// registro
																				// en
																				// la
																				// tabla
																				// no
																				// importa
																				// que
																				// precio
																				// sea

						lMPriceListID = mProductPrice.getM_PriceList_ID();
						lCUomID = lCUomID == 0 ? mProduct.getC_UOM_ID()
								: lCUomID;
						precios.setProductValue(mProductPrice.getsProductValue());
						precios.setDescripcion(mProductPrice.getsProductDescription());
					}
				} else
				// Si la unidad de medida es la de volumen
				if (pCUomID == mProduct.getC_UOMVolume_ID()) {
					precios.setPriceList(mProductPrice.getPriceList() != null ? mProductPrice
							.getPriceList() : Env.ZERO);
					if (mProductPrice.isCalculated()) {
						precios.setPriceLimit(mProductPrice.getPriceLimit() != null ? mProductPrice
								.getPriceLimit() : Env.ZERO);
						precios.setPriceStd(mProductPrice.getPriceStd() != null ? mProductPrice
								.getPriceStd() : Env.ZERO);
						precios.setSeObtuvoPrecio(mProductPrice.isCalculated());// true:
																				// Un
																				// registro
																				// en
																				// la
																				// tabla
																				// no
																				// importa
																				// que
																				// precio
																				// sea

						lMPriceListID = mProductPrice.getM_PriceList_ID();
						lCUomID = mProduct.getC_UOMVolume_ID();
						precios.setProductValue(mProductPrice.getsProductValue());
						precios.setDescripcion(mProductPrice.getsProductDescription());
					}

				}
			}

			// Charge master
			if (precios.getPriceList().compareTo(Env.ZERO) <= 0) {
				// Se localiza el precios en el charge master
				final MEXMEProductoPrecio mProductoPrecio = GetPrice
						.calcularPrecio(ctx, pMProductID, fecha);
				if (mProductoPrecio != null
						&& mProductoPrecio.getEXME_ProductoPrecio_ID() > 0) {
					precios.setSeObtuvoPrecio(true);// true: Un registro en la
													// tabla no importa que
													// precio sea
					precios.setPriceList(mProductoPrecio.getPriceList() != null ? mProductoPrecio
							.getPriceList() : Env.ZERO);
					precios.setPriceLimit(mProductoPrecio.getPriceLimit() != null ? mProductoPrecio
							.getPriceLimit() : Env.ZERO);
					precios.setPriceStd(mProductoPrecio.getPriceStd() != null ? mProductoPrecio
							.getPriceStd() : Env.ZERO);
				}
				lCUomID = lCUomID == 0 ? mProduct.getC_UOM_ID() : lCUomID;
			}

			// // Se calcula el factor precio
			// if (precios.getPriceList().compareTo(Env.ZERO) <= 0) {
			// calculoFactorPrecio(ctx, mProduct, trxName);
			// }

			// llenamos el objeto de precio
			precios.setCtx(ctx);
//			precios.setProductValue(" ");
//			precios.setDescripcion(" ");

			// Incluyen impuestos?
			precios.setTaxIncluded(false);// REV
			final MEXMEProductoOrg prodorg = MEXMEProductoOrg.getProductoOrg(
					ctx, mProduct.getM_Product_ID(), Env.getAD_Client_ID(ctx),
					Env.getAD_Org_ID(ctx), null);
			if (prodorg != null) {
				precios.setC_TaxCategory_ID(prodorg.getC_TaxCategory_ID());
			}
			precios.setC_currency_id(Env.getC_Currency_ID(ctx));

			// Datos del producto
			precios.setM_Product_ID(mProduct.getM_Product_ID());
			precios.setC_uom_id(pCUomID);

			precios.setM_PriceList_ID(lMPriceListID);
			precios.setC_BPartner_ID(lCBPartnerID);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "precioProdVta: ", e);
		} finally {
			s_log.info(toString());

			if (Env.ZERO.compareTo(precios.getPriceStd()) == 0) {
				precios.setPriceStd(precios.getPriceList());
			}
		}

		return precios;
	}

	/**
	 * Construye el objeto para extraer el precio de la lista de precios
	 * 
	 * @param ctx
	 *            : contexto
	 * @param pProductID
	 *            : producto
	 * @param pPriceListID
	 *            : lista de precios
	 * @param socioID
	 *            : socio de negocios
	 * @param pUomID
	 *            : unidad de medida
	 * @param fecha
	 *            : fecha de vigencia
	 * @param force
	 *            : si se debe forzar a buscar un precio
	 * @param isSalePublic
	 *            : si la solicitud de precios es venta al publico
	 * @param isUomVol
	 *            : true: si la unidad de es de empaque
	 * @return
	 */
	private static MProductPricing shipment(final Properties ctx,
			final int pProductID, final int pPriceListID, final int socioID,
			final int pUomID, final Timestamp fecha, final boolean force,
			final boolean isSalePublic, final boolean isUomVol) {

		// La busqueda de precio empieza con la lista de precios @param
		// pPriceListID
		// Si el precio no se hubica en esa lista
		// y sí @param force = true,
		// busca el precio en la lista de precios del socio de negocios
		// si no se localizo el precio y sí @param isSalePublic = true:
		// busca el precio en la lista de configuración de consulta externa
		// si no se localizo el precio
		// busca el precio en la lista de configuración de precios
		final MProductPricing m_productPrice = new MProductPricing(pProductID,
				socioID, Env.ONE, true, pUomID, ctx, force, isUomVol);

		// lista de precios que se tomara
		m_productPrice.setM_PriceList_ID(pPriceListID);

		// fecha actual para vigencias (Timestamp)
		m_productPrice.setPriceDate(fecha);// 2011 12 26

		return m_productPrice;
	}
}
