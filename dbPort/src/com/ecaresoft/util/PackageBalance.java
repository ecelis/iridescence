package com.ecaresoft.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacPaq;
import org.compiere.model.MEXMESaldoPaquete;
import org.compiere.model.X_EXME_CtaPacDet;
import org.compiere.util.Env;

/**
 * Balance de paquetes
 * 
 * @author odelarosa
 * 
 */
public class PackageBalance {

	/**
	 * Ejecuata el match de paquetes
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta paciente a revisar
	 * @param trxName
	 *            Nombre de trx
	 */
	public static void batchMatch(Properties ctx, int ctaPacId, String trxName) {

		createNewBalance(ctx, ctaPacId, trxName);

		batchMatch(ctx, MCtaPacDet.getPackagesCandidates(ctx, ctaPacId, trxName), trxName);
	}

	/**
	 * Procesado en lotes
	 * 
	 * @param ctx
	 *            Contexto
	 * @param lst
	 *            Listado de cargos
	 * @param trxName
	 *            Nombre de transacción
	 */
	public static void batchMatch(Properties ctx, List<MCtaPacDet> lst, String trxName) {
		for (MCtaPacDet det : lst) {
			new PackageBalance(ctx, det.getEXME_CtaPacDet_ID(), trxName).doIt();
		}
	}

	/**
	 * Crea el nuevo balance
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta paciente a revisar
	 * @param trxName
	 *            Nombre de trx
	 */
	public static void createNewBalance(Properties ctx, int ctaPacId, String trxName) {
		List<MEXMECtaPacPaq> packs = MEXMECtaPacPaq.get(ctx, " EXME_CtaPac_id = ? ", " order by created asc ", trxName, ctaPacId);

		for (MEXMECtaPacPaq pack : packs) {
			MCtaPacDet charge = pack.getCargo();
			
			if (charge == null || (charge != null && !charge.isFacturado() && !charge.isSeDevolvio())) {
				if (charge != null) {
					charge.setCostAverage(BigDecimal.ZERO);
					charge.setCostStandard(BigDecimal.ZERO);
					charge.setPriceLastPO(BigDecimal.ZERO);

					charge.save(trxName);
				}

				pack.createNewBalance();
			}
		}
	}

	private int chargeId;
	private Properties ctx;
	private int extZero;
	private String trxName;

	/**
	 * Balance de paquetes
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param chargeId
	 *            Cargo a revisar
	 * @param trxName
	 *            Nombre de Trx
	 */
	public PackageBalance(Properties ctx, int chargeId, String trxName) {
		super();

		this.ctx = ctx;
		this.chargeId = chargeId;
		this.trxName = trxName;

		if (StringUtils.isBlank(trxName)) {
			throw new IllegalArgumentException("Trx name must not be null");
		}
	}

	/**
	 * Revisa el balance por línea
	 * 
	 * @param list
	 *            Listado de saldo
	 * @param productId
	 *            Producto
	 * @param amount
	 *            Cantidad pendiente
	 * @return Cantidad sobrante
	 */
	private BigDecimal check(List<MEXMESaldoPaquete> list, int productId, BigDecimal amount) {
		for (MEXMESaldoPaquete saldo : list) {
			if (BigDecimal.ZERO.compareTo(amount) == 0) {
				break;
			} else {
				amount = updateBalance(saldo, productId, amount);
			}
		}

		return amount;
	}

	/**
	 * Revisión de paquetes, revisa si es parte del paquete o sistituto de algun
	 * producto del paquete
	 */
	public void doIt() {
		MCtaPacDet charge = new MCtaPacDet(ctx, chargeId, trxName);

		int productId = charge.getM_Product_ID();

		int uomId = charge.getC_UOM_ID();

		int ctaPacId = charge.getEXME_CtaPac_ID();

		extZero = MEXMECtaPac.getExtZero(ctaPacId);

		BigDecimal amount = charge.getQtyDelivered();

		BigDecimal originalAmount = charge.getQtyOrdered();

		BigDecimal tmp = BigDecimal.ZERO;

		List<MEXMECtaPacPaq> packs = MEXMECtaPacPaq.get(ctx, " EXME_CtaPac_id = ? ", " order by created asc ", trxName, ctaPacId);

		boolean checkCharge = false;

		for (MEXMECtaPacPaq pack : packs) {
			MCtaPacDet packCharge = pack.getCargo();

			// Solo hacer movimientos de paquetes no facturados
			if (packCharge == null || (packCharge != null && !packCharge.isFacturado() && !packCharge.isSeDevolvio())) {
				// Revisar primero si ya está alguno libre como sustituto (Puede
				// ser el original)
				List<MEXMESaldoPaquete> list = MEXMESaldoPaquete.getAvailableLinesSubs(ctx, ctaPacId, productId, uomId, pack.getEXME_PaqBase_Version_ID(), trxName);

				tmp = check(list, productId, amount);

				// Si sobraron hay que revisar si hay disponibles sin usarse
				if (BigDecimal.ZERO.compareTo(tmp) == -1) {
					tmp = check(MEXMESaldoPaquete.getAvailableLines(ctx, ctaPacId, productId, uomId, pack.getEXME_PaqBase_Version_ID(), trxName), productId, tmp);
				}

				// Revisar si hay sustitutos libres
				if (BigDecimal.ZERO.compareTo(tmp) == -1) {
					tmp = check(MEXMESaldoPaquete.getAvailableLinesNewSubs(ctx, ctaPacId, productId, uomId, pack.getEXME_PaqBase_Version_ID(), trxName), productId, tmp);
				}

				if (amount.compareTo(tmp) != 0) {
					MCtaPacDet newCharge = new MCtaPacDet(ctx, 0, null);

					newCharge.copyFrom(charge);

					BigDecimal value = amount.subtract(tmp);

					newCharge.setQtyEntered(value);
					newCharge.setQtyDelivered(value);
					newCharge.setQtyOrdered(value);
					newCharge.setQtyInvoiced(value);
					newCharge.setQtyPendiente(value);
					newCharge.setQtyPaquete(Env.ZERO);
					newCharge.setQtyReserved(Env.ZERO);

					newCharge.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Exempt);
					newCharge.setEXME_PaqBase_Version_ID(pack.getEXME_PaqBase_Version_ID());
					newCharge.setLine();
					newCharge.setLineNetAmt();
					newCharge.setTaxAmt(false, newCharge.getLineNetAmt());

					/**
					 * Los costos se copian del cargo original
					 */
					newCharge.setCostAverage(charge.getCostAverage());
					newCharge.setCostStandard(charge.getCostStandard());
					newCharge.setPriceLastPO(charge.getPriceLastPO());

					// Se envia a la extension 0
					newCharge.setEXME_CtaPacExt_ID(extZero);

					newCharge.save(trxName);

					// Actualizamos los costos del paquete
					if (packCharge != null) {
						packCharge.setCostAverage(packCharge.getCostAverage().add(value.multiply(newCharge.getCostAverage())));
						packCharge.setCostStandard(packCharge.getCostStandard().add(value.multiply(newCharge.getCostStandard())));
						packCharge.setPriceLastPO(packCharge.getPriceLastPO().add(value.multiply(newCharge.getPriceLastPO())));
						packCharge.save(trxName);
					}

					checkCharge = true;
				}

				amount = tmp;

				if (BigDecimal.ZERO.compareTo(amount) == 0) {
					break;
				}
			}
		}

		if (checkCharge && originalAmount.compareTo(tmp) != 0 && packs.size() > 0) {
			if (BigDecimal.ZERO.compareTo(tmp) == 0) {
				charge.setIsActive(false);
				// Se envia a la extension 0
				charge.setEXME_CtaPacExt_ID(extZero);

				charge.save(trxName);
			} else {
				charge.setQty(tmp);
				// Se envia a la extension 0
				charge.setEXME_CtaPacExt_ID(extZero);

				charge.save(trxName);
			}
		}
	}

	/**
	 * Actualiza el balance según la cantidad enviada
	 * 
	 * @param saldo
	 *            Saldo o balance
	 * @param productId
	 *            Producto
	 * @param amount
	 *            Cantidad
	 * @return Cantidad sobrante
	 */
	private BigDecimal updateBalance(MEXMESaldoPaquete saldo, int productId, BigDecimal amount) {
		saldo.setSustituto_ID(productId);

		// Si la cantidad disponible es mayor a la solicitada
		if (saldo.getRemainingAmt().compareTo(amount) >= 0) {
			saldo.setRemainingAmt(saldo.getRemainingAmt().subtract(amount));

			amount = BigDecimal.ZERO;
		} else {
			// Si la cantidad disponible es menor a la solicitada
			amount = amount.subtract(saldo.getRemainingAmt());

			saldo.setRemainingAmt(BigDecimal.ZERO);
		}

		saldo.save(trxName);

		return amount;
	}

}