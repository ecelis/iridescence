/**
 * 
 */
package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMENumSerie;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementConfirm;
import org.compiere.model.MMovementLine;
import org.compiere.model.MStorage;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * 2. Transferencia
 * 
 * @author twry
 * * @deprecated
 */
public class TransferOrder extends AbstractStockTransfer {

	/** Define si el movimiento es de consigna */
	private boolean isConsigna;

	@SuppressWarnings("unchecked")
	@Override
	protected void prepare() throws Exception {
		log.log(Level.INFO, "prepare");
		final ProcessInfoParameter[] para = getParameter();

		// Parametros
		for (int i = 0; i < para.length; i++) {
			final String name = para[i].getParameterName();

			if ("MovementLines".equals(name)) {
				mMovementLines = (List<MMovementLine>) para[i].getParameter();

			} else if ("Movement".equals(name)) {
				mMovement = ((MMovement) para[i].getParameter());

			} else if ("isConsigna".equals(name)) {
				isConsigna = ((Boolean) para[i].getParameter());// final

			} else {
				log.log(Level.SEVERE,
						"TransferOrder.prepare - Unknown Parameter: " + name);
			}
		}

		// Validaciones
		errores = validate(Constantes.SURTIDO, mMovementLines);
	}

	/**
	 * Validar que no esten pendiente en este paso
	 * @return
	 */
	private boolean existe(){
		if (mMovement != null) {
			// Id de confirmación
			int movementConfirmId = MMovementConfirm.getMovementId(mCtx,
					mMovement.getM_Movement_ID(), mTrx.getTrxName());
			// Objeto de confirmación
			mMovementConfirm = new MMovementConfirm(mCtx, movementConfirmId,
					mTrx.getTrxName());
		}
		return confirmacionPendiente() && mMovementConfirm!=null && MMovement.DOCSTATUS_Drafted.equals(mMovementConfirm.getDocStatus());
	}
	
	@Override
	protected String doIt() throws Exception {
		log.log(Level.INFO, "doIt");

		if (errores.isEmpty() && !existe()) {

//			if (mMovement == null) {
//				mMovement = new MMovement(mCtx, mMovementLines.get(0)
//						.getM_Movement_ID(), mTrx.getTrxName());
//			}
			mMovementConfirm = null;
			mMovement.setWarehouseIds();
			completeOrder();
		}
		return errores.isEmpty() ? "@OK@" + mMovement.getDocumentNo() : ERROR
				+ errores.toString();
	}

	/**
	 * Hacer el surtido
	 * 
	 * @return
	 */
	private boolean completeOrder() {
		log.log(Level.INFO, "completeOrder");
		final int locatorID = mMovement.getMAlmSurt().getM_Warehouse_ID();

		try {
			// LRHU. Verificamos si el almacen controla existencias de productos
			final boolean controlaStock = MEXMEMejoras.inventories(mCtx); // por
			// default
			// todos
			// los
			// almacenes
			// controlan
			// existencias

			// actualizamos el movimiento con la cantidad capturada
			for (int i = 0; i < mMovementLines.size(); i++) {
				final MMovementLine linea = mMovementLines.get(i);

				linea.setMovementQty(linea.getTargetQty());
				linea.setMovementQty_Vol(linea.getTargetQty_Vol());
				// Guardamos el precio para almacenes en consigna,
				// si el traspaso es entre almacenes de la organizacion padre,
				// el precio no se podra escribir y
				// no actualizaremos costos, eruiz
				if (isConsigna) {
					linea.setPriceList(new BigDecimal(linea.getPrice()));
				}

				// validamos el numero de serie
				if (mMovement.getEXME_CtaPac_ID() > 0
						&& linea.getProduct().isNumSerie()) {

					// validamos que el Numero de serie no haya sido ya tecleado
					// en el resto del detalle
					for (int j = 0; j < mMovementLines.size(); j++) {

						final MMovementLine line2 = mMovementLines.get(j);

						if (linea.getLine() != line2.getLine()
								&& linea.getM_Product_ID() == line2
										.getM_Product_ID()
								&& linea.getNumSerie().equalsIgnoreCase(
										line2.getNumSerie())) {
							log.log(Level.INFO, "completeOrder.1");
							errores.add(Utilerias.getLabel(
									"error.numSerie.lista",
									linea.getNumSerie(), linea.getProduct()
											.getName()));
							break;
						}

					}
				}

				if (linea.save(mTrx.getTrxName())) {
					// LRHU. Si el almacen NO controla las existencias de
					// productos, �stas no se afectan
					// sumamos la cantidad surtida a la columna de qtyOnHand
					if (controlaStock) {
						// MStorage para almacen que surte. Solamente se le suma
						// la cantidad surtida a la columna
						// qtyReserved
						MStorage.add(mCtx, locatorID, linea.getM_Locator_ID(),
								linea.getM_Product_ID(),
								linea.getM_AttributeSetInstance_ID(),
								linea.getM_AttributeSetInstance_ID(), Env.ZERO,
								linea.getTargetQty(), Env.ZERO,
								mTrx.getTrxName());

					} else {
						MStorage.add(mCtx, locatorID, linea.getM_Locator_ID(),
								linea.getM_Product_ID(), 0, 0,
								linea.getTargetQty(), linea.getTargetQty(),
								Env.ZERO, mTrx.getTrxName());

					}

				} else {
					// FIXME el mensaje no es correcto
					log.log(Level.INFO, "completeOrder.2");
					errores.add(Utilerias.getLabel("error.numSerie.existe",
							"error.traspasos.guardaLinea",
							String.valueOf(linea.getLine())));
					break;
				}

				// si es numero de serie guardamos el numero de serie en la
				// tabla
				if (mMovement.getEXME_CtaPac_ID() > 0
						&& linea.getProduct().isNumSerie()) {
					final MEXMENumSerie numSerie = new MEXMENumSerie(mCtx, 0,
							mTrx.getTrxName());

					numSerie.setNumSerie(linea.getNumSerie());
					numSerie.setM_Product_ID(linea.getM_Product_ID());
					numSerie.setM_MovementLine_ID(linea.getM_MovementLine_ID());
					numSerie.setEXME_CtaPac_ID(mMovement.getEXME_CtaPac_ID());
					numSerie.setDocStatus(MEXMENumSerie.DOCSTATUS_Surtido);

					if (!numSerie.save()) {
						log.log(Level.INFO, "completeOrder.3");
						errores.add(Utilerias.getLabel("error.numSerie.noSave",
								String.valueOf(linea.getLine())));
						break;
					}
				}
			} // fin for

			if (errores.isEmpty()) {

//				// nombre de transaccion
//				final Workflow flujo = new Workflow(mTrx.getTrxName());
//				flujo.getInfo(MMovement.Table_Name);
//				flujo.startWorkflow(mCtx, mMovement.get_ID());

				// asignamos el numero de documento generado
				mMovementConfirm = MMovementConfirm.get(mCtx,
						mMovement.get_ID(), mTrx.getTrxName());

				if (mMovementConfirm.get_ID() < 1) {
					log.log(Level.INFO, "completeOrder.4");
					errores.add(Utilerias.getLabel("abstracting.error.save"));
				}
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "completeOrder.5" , e);
			errores.add(Utilerias.getLabel("error.traspasos.completando",
					e.getMessage()));
		}

		return errores.isEmpty();// && mMovementConfirm.get_ID() > 0;
	}
}