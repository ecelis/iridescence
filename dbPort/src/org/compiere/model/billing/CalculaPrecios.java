package org.compiere.model.billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MCtaPacDet;
import org.compiere.model.MCurrency;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEEsqDesLine;
import org.compiere.model.MTax;
import org.compiere.model.ModelError;
import org.compiere.model.PreciosVenta;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * CalculaPrecios Esta clase se utiliza en el proceso de recalculo de precios
 * para facturación por extensiones. Y cuando un cargo se mueve de una extension
 * a otra
 * 
 * @author twry
 * 
 */
public class CalculaPrecios {

	/** Static Logger */
	private static CLogger log = CLogger.getCLogger(CalculaPrecios.class);
	/** Extension de los cargos a cambiar precio */
	private final MEXMECtaPacExt mExtension;
	/** Nombre de transacción */
	private String trxNameCP;
	/** Contexto */
	private final Properties ctx;
	/** Listado de errores */
	private final List<ModelError> lstErrors;
	/** Listado de cargos actualizados */
	private List<MCtaPacDet> lstCargosReturn = null;
	/** Decimales */
	private final int scale;
	/** Tasas de impuestos que ya hubieran sido buscadas en la base de datos */
	private final HashMap<Integer, MTax> mapaTax = new HashMap<Integer, MTax>();

	/**
	 * Constructor
	 * 
	 * @param pCtx
	 * @param pMExtension
	 * @param pTrxName
	 */
	public CalculaPrecios(final Properties pCtx,
			final MEXMECtaPacExt pMExtension, final String pTrxName) {
		super();
		this.mExtension = pMExtension;
		this.trxNameCP = pTrxName;
		this.ctx = pCtx;
		this.lstErrors = new ArrayList<ModelError>();
		this.scale = MCurrency.getStdPrecision(ctx, Env.getC_Currency_ID(ctx)); // decimas.
	}

	/**
	 * Recalculo de precios
	 * 
	 * @param pCtx Contexto
	 * @param pMExtension Extension de los cargos 
	 * @return Listado de errores
	 */
	public static List<ModelError> recalcularPrecio(final Properties pCtx,
			final MEXMECtaPacExt pMExtension) {
		return new CalculaPrecios(pCtx, pMExtension, null).initProcessTrx();
	}

	/**
	 * Inicia el proceso de recalculo de precios
	 * creando una transacción
	 */
	private List<ModelError> initProcessTrx() {
		Trx trx = null;
		lstCargosReturn = new ArrayList<MCtaPacDet>();
		try {
			
			trx = Trx.get(Trx.createTrxName("CalP"), true);
			trxNameCP = trx.getTrxName();
			
			// Hacer el recalculo de precios
			process();

			// Si no hubo errores se hace el commit
			if (lstErrors == null || lstErrors.isEmpty()) {
				Trx.commit(trx);
			} else {
				Trx.rollback(trx);
				lstErrors.add(new ModelError(1, "msj.error"));
				
			}

		} catch (Exception ex) {
			lstErrors.add(new ModelError(1, ex.getMessage()));
			
		} finally {
			Trx.close(trx, true);
			trx = null;
		}

		return lstErrors;
	}

	/**
	 * Recalculo de precios
	 * 
	 * @param pCtx
	 *            Contexto
	 * @param pMExtension
	 *            Extension de los cargos a modificar
	 * @param pTrxName
	 *            Nombre de transacción
	 * @return Listado de errores
	 */
	public static List<ModelError> recalcularPrecio(final Properties pCtx,
			final MEXMECtaPacExt pMExtension, final String pTrxName) {
		return new CalculaPrecios(pCtx, pMExtension, pTrxName).initProcess();
	}

	/**
	 * Inicia el proceso de recalculo de precios
	 * sin crear una transacción
	 */
	private List<ModelError> initProcess() {
		lstCargosReturn = new ArrayList<MCtaPacDet>();
		try {
			process();
			if (lstErrors != null && !lstErrors.isEmpty()) {
				lstErrors.add(new ModelError(1, "msj.error"));
			}

		} catch (Exception ex) {
			lstErrors.add(new ModelError(1, ex.getMessage()));
		}
		return lstErrors;
	}

	/**
	 * Calcular el precio a partir de la lista de precios
	 */
	private void process() {
		// Cargos de la extension
		final List<MCtaPacDet> lstCargos = mExtension.getLines();
		if (lstCargos != null) {

			// Iterar los cargos
			for (int i = 0; i < lstCargos.size(); i++) {
				// Cargo con el precio
				final MCtaPacDet cargo = PreciosVenta.precioProdVtaFxE(ctx,
						lstCargos.get(i), trxNameCP);

				// Buscar los convenios
				final Object[] lst = getDiscount(mExtension,cargo);

				if (lst != null) {
					cargo.setPriceActual(((BigDecimal) lst[1])
							.compareTo(Env.ZERO) == 0 ? cargo.getPriceList()
							: (BigDecimal) lst[1]);
					cargo.setEXME_EsqDesLine_ID((Integer) lst[0]);
					cargo.setLineNetAmt(cargo.getLineGrossAmt().setScale(scale,
							BigDecimal.ROUND_HALF_UP));
					cargo.setDiscount(((BigDecimal) lst[2]).compareTo(Env.ZERO) == 0 ? Env.ZERO
							: (BigDecimal) lst[2]);
					cargo.setDiscountAmt(((BigDecimal) lst[2])
							.compareTo(Env.ZERO) == 0 ? Env.ZERO
							: (BigDecimal) lst[2]);
				}

				if (Env.ZERO.compareTo(cargo.getPriceActual()) == 0) {
					cargo.setPriceActual(cargo.getPriceList());
				}
				
				cargo.setPricesInv();
				cargo.setLineNetAmt();

				// buscar impuesto
				if (cargo.getM_Product_ID() > 0 && cargo.getProduct() != null
						&& cargo.getProduct().isProduct()) {
					cargo.getProduct().setmTax();// vaciar el objeto
					final int taxId = cargo.getProduct().getC_Tax_ID();
					if (taxId > 0) {
						cargo.setC_Tax_ID(taxId);
					}
				}

				// Si existe impuesto agregar al mapa
				if (cargo.getC_Tax_ID() > 0) {
					MTax tax = null;
					if (mapaTax.containsKey(cargo.getC_Tax_ID())) {
						tax = mapaTax.get(cargo.getC_Tax_ID());
					} else {
						tax = new MTax(ctx, cargo.getC_Tax_ID(), null);
						mapaTax.put(cargo.getC_Tax_ID(), tax);
					}

					// Calcula el impuesto solo para productos no para CCCmD ni
					// Desc
					if (tax != null && cargo.getProduct() != null
							&& cargo.getProduct().isProduct()) {
						cargo.setTaxAmt(tax.calculateTax(cargo.getLineNetAmt(),
								false, scale));// TODO
					}
				}

				// Ahora los precios
				if (cargo.save(trxNameCP)) {
					lstCargosReturn.add(cargo);
					
				} else {
					log.log(Level.INFO, "charge>" + cargo.getM_Product_ID()
							+ "CtaPacExt" + cargo.getEXME_CtaPacExt_ID());
				}
			}
		}
	}

	/**
	 * Obtener el descuento del producto
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Nombre de transacción
	 * @param ctaPacExt
	 *            Extension
	 * @param orgID
	 *            Organizacion
	 * @param clientId
	 *            Cliente
	 * @param ctaPacDet
	 *            Cargos de la cuenta paciente
	 * @return
	 */
	public Object[] getDiscount(final MEXMECtaPacExt ctaPacExt,
			final MCtaPacDet ctaPacDet) {

		BigDecimal total = Env.ZERO;
		BigDecimal discount = Env.ZERO;

		// Primero los descuentos
		final MEXMEEsqDesLine lst = MEXMEEsqDesLine.cargasCtaPacDet(ctx,
				trxNameCP, ctaPacExt.getC_BPartner_ID(), ctaPacExt.getBpartner()
						.getC_BP_Group_ID(), Env.getAD_Org_ID(ctx), Env
						.getAD_Client_ID(ctx),
				ctaPacExt.getEXME_CtaPacExt_ID(), ctaPacDet.getTipoArea(),
				ctaPacDet.getM_Product_ID(), ctaPacDet.getProduct()
						.getM_Product_Category_ID(), ctaPacDet
						.getDateDelivered());

		if (lst == null) {
			return new Object[] { 0,
					total.setScale(2, BigDecimal.ROUND_HALF_UP),
					discount.setScale(2, BigDecimal.ROUND_HALF_UP) };

		} else {
			if (lst.getList_Discount() != null
					&& lst.getList_Discount().compareTo(Env.ZERO) > 0) {
				total = ctaPacDet.getPriceList().subtract(
						ctaPacDet.getPriceList().multiply(
								lst.getList_Discount().divide(Env.ONEHUNDRED)));
				discount = ctaPacDet.getPriceList().multiply(
						lst.getList_Discount().divide(Env.ONEHUNDRED));

			} else {
				total = lst.getList_AddAmt();
				discount = Env.ZERO;
			}

			return new Object[] { lst.getEXME_EsqDesLine_ID(),
					total.setScale(2, BigDecimal.ROUND_HALF_UP),
					discount.setScale(2, BigDecimal.ROUND_HALF_UP) };
		}
	}
}
