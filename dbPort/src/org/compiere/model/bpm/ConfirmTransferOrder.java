/**
 * 
 */
package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.ConfirmaDetView;
import org.compiere.model.MConfigEC;
import org.compiere.model.MEXMENumSerie;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MInventory;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementConfirm;
import org.compiere.model.MMovementLine;
import org.compiere.model.MMovementLineConfirm;
import org.compiere.model.MStorage;
import org.compiere.model.MUOMConversion;
import org.compiere.model.PO;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Confirmación de pedido
 * 
 * @author twry
 * @deprecated
 */
public class ConfirmTransferOrder extends AbstractStockTransfer {
	/** Se hicieron los cargos */
	private boolean isCharged = true;
	/** Es esterilizacion */
	private boolean isSterilization;
	/** Lineas de la confirmación */
	private List<ConfirmaDetView> linesConfView;
	/** Configuracion de expediente clinico */
	private MConfigEC configEC = null;
	/** Si es ceye o no */
	private boolean isCeye = false;
	/** Id de backorder */
	private int backOrderID = 0;

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
				mMovement = ((MMovement) para[i].getParameter());// final

			} else if ("Sterilization".equals(name)) {
				isSterilization = ((Boolean) para[i].getParameter());// final

			} else if ("isCeye".equals(name)) {
				isCeye = ((Boolean) para[i].getParameter());// final

			} else {
				log.log(Level.INFO,
						"ConfirmTransferOrder.prepare - Unknown Parameter: "
								+ name);
			}
		}

		// llenar objetos
		setValues();

		// Validar los paramertos de la solicitud especificados por el usuario
		validateConfirm();
	}

	/**
	 * Consultar valores a partir de los parametros
	 * 
	 * @throws Exception
	 */
	private void setValues() throws Exception {
		log.log(Level.INFO, "setValues");
		// Movimiento
		mMovement.set_TrxName(mTrx.getTrxName());
		// Id de confirmación
		int movementConfirmId = MMovementConfirm.getMovementId(mCtx,
				mMovement.getM_Movement_ID(), mTrx.getTrxName());
		// Objeto de confirmación
		mMovementConfirm = new MMovementConfirm(mCtx, movementConfirmId,
				mTrx.getTrxName());
		// Lineas a confirmar
		linesConfView = getMovementConfirmDet();

	}

	/**
	 * 
	 * @param mapping
	 * @param request
	 * @return
	 */
	private boolean validateConfirm() {
		log.log(Level.INFO, "validateConfirm");

		final boolean success = super.validate(Constantes.CONFIRMACION,
				mMovementLines).isEmpty();

		try {

			if (success) {

				// // Validamos que no halla sido confirmado.
				// if (mMovement.getDocStatus().equalsIgnoreCase(
				// MMovement.DOCSTATUS_Completed)) {
				// log.log(Level.INFO, "validateConfirm.2");
				// errores.add(Utilerias.getLabel(
				// "error.traspasos.isConfirmado",
				// mMovement.getDocumentNo()));
				//
				// }

				configEC = MConfigEC.get(mCtx, null);
				if (configEC == null) {
					log.log(Level.INFO, "validateConfirm.3");
					errores.add(Utilerias.getLabel("error.citas.noConfigEC"));
				}

				// validar que exista al menos una linea
				if (linesConfView.isEmpty()) {
					log.log(Level.INFO, "validateConfirm.4");
					errores.add(Utilerias.getLabel("error.traspasos.lines"));
				}

				// Validar linea por linea
				for (int i = 0; i < linesConfView.size(); i++) {
					final ConfirmaDetView linea = (ConfirmaDetView) linesConfView
							.get(i);
					linea.ctx = Env.getCtx();
					// validamos q la cant. confirmada no sea negativa
					if (linea.getConfirmedQty().compareTo(BigDecimal.ZERO) < 0) { // linea.getConfirmedQty()<0
						log.log(Level.INFO, "validateConfirm.5");
						errores.add(Utilerias.getLabel(
								"error.traspasos.confCero",
								String.valueOf(linea.getLine())));

					} else if (linea.getConfirmedQty().compareTo(
							linea.getTargetQty()) < 0) { // linea.getConfirmedQty()<linea.getTargetQty()
						// validamos q la cant. confirmada sea menor o igual a
						// la surtida(target)
						log.log(Level.INFO, "validateConfirm.6");
						errores.add(Utilerias.getLabel(
								"error.traspasos.confSurt",
								String.valueOf(linea.getLine())));
					}

					// validacion de conversiones de UDM
					if (linea.getProd().getC_UOM_ID() != linea.getProd()
							.getC_UOMVolume_ID()) {
						final MUOMConversion rates = MEXMEUOMConversion
								.validateConversions(Env.getCtx(), linea
										.getProd().getM_Product_ID(), linea
										.getProd().getC_UOMVolume_ID(), null);
						if (rates == null) {
							log.log(Level.INFO, "validateConfirm.7");
							errores.add(Utilerias.getLabel(
									"error.udm.noExisteConversion", linea
											.getProd().getName()));
						}
					}
				}// fin for de lineas
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "-- validando: ", e);
			errores.add(Utilerias.getLabel(e.getMessage()));
		}

		return errores.isEmpty() && success;
	}

	@Override
	protected String doIt() throws Exception {
		log.log(Level.INFO, "doIt");

		// Confirmar
		if (errores.isEmpty()) {
			confirmar();
		}

		// Si no hubo errores
		// y Si aplica cargos
		// y no es devolucion
		// y no es esterilización
		// y tiene cuenta paciente
		// y no es la aplicacion de medicamento
		if (errores.isEmpty()) {
			isCharged = crearCargos(configEC, isSterilization, mMovement,
					mMovementLines);// NUEVA TRANSACCION
		}

		return errores.isEmpty() ? "@OK@" + mMovement.getDocumentNo() : ERROR
				+ errores.toString();
	}

	/**
	 * Crear cargos si toda ha sido un exito y demas reglas de negocio ( Si no
	 * hubo errores y Si aplica cargos y no es devolucion y no es esterilización
	 * y tiene cuenta paciente y no es la aplicacion de medicamento ) PROPIA
	 * TRANSACCION
	 * 
	 * @return
	 * @throws Exception
	 * @deprecated 
	 * - El objeto BeanView no cuenta con documentacion
	 * - Este codigo esta duplicado en {@link MMovement#updateQtyCharge() }
	 */
	public boolean crearCargos(final MConfigEC configEC,
			final boolean isSterilization, final MMovement mMovement,
			final List<MMovementLine> mMovementLines) throws Exception {

		log.log(Level.INFO, "crearCargos");
		boolean success = true;

		if (configEC.isAplicaPedCtaPac() && !mMovementLines.isEmpty()
				&& !isSterilization && mMovement.getEXME_CtaPac_ID() > 0
				&& !mMovement.isDevol()
				&& mMovement.getEXME_PrescRXDet_ID() < 1) {
			final HashMap<Integer, MMovementLine> map = new HashMap<Integer, MMovementLine>();

			for (final MMovementLine line : mMovementLines) {

				BigDecimal qty = BigDecimal.ZERO;
				if (line.getOp_Uom() == 0
						|| line.getOp_Uom() == line.getProduct().getC_UOM_ID()) {
					qty = line.getConfirmedQty().add(line.getScrappedQty());
					// qty = line.getTargetQty();
				} else if (line.getProduct().getC_UOMVolume_ID() == line
						.getOp_Uom()) {
					qty = line.getConfirmedQty_Vol().add(
							line.getScrappedQty_Vol());
					qty = line.getTargetQty_Vol();
				}

				if (qty.compareTo(Env.ZERO) > 0) {
					line.setBeanView(qty);
					map.put(line.getM_Product_ID(),line);
				}
			}

			// crear los cargos
			final String error = mMovement.insertCharges(null, map);
			if (StringUtils.isNotEmpty(error)) {
				errores.add(error);
			}
		}
		return success;
	}

	/**
	 * Obtenemos el detalle para un movimiento pendiente de confirmar
	 * 
	 * @return Una lista con los movimientos pendientes de confirmaro
	 */
	public List<ConfirmaDetView> getMovementConfirmDet() throws Exception {
		log.log(Level.INFO, "getMovementConfirmDet");

		final boolean isWeb = true;
		final List<ConfirmaDetView> lista = new ArrayList<ConfirmaDetView>();
		final String sql = MMovementConfirm.queryMovementConfirmDet(isWeb);
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql, mTrx.getTrxName());
			pstmt.setLong(1, mMovementConfirm.getM_MovementConfirm_ID());
			rset = pstmt.executeQuery();

			log.info(sql + "   movementConfirmID: "
					+ mMovementConfirm.getM_MovementConfirm_ID() + "\n\n");

			int count = 1;
			while (rset.next()) {
				final ConfirmaDetView confirma = new ConfirmaDetView();
				confirma.setRSet(rset, isWeb, count);
				lista.add(confirma);
				count++;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "", e);
			errores.add(Utilerias.getLabel("error.confirma.complete")
					+ e.getMessage());

		} finally {
			DB.close(rset, pstmt);
		}
		return lista;
	}

	/**
	 * Confirmar
	 * 
	 * @return
	 */
	public boolean confirmar() {
		log.log(Level.INFO, "confirmar");
		try {

			log.info("movimiento (documentNo): " + mMovement.getDocumentNo());

			final BigDecimal faltantes = qtyBackorder();

			// Completar movement
			if (errores.isEmpty()) {
				completeItConfirm();
			}

			// Crear backorder
			if (errores.isEmpty()) {
				createBackorder(faltantes);
			}

			// Crear inventario
			if (errores.isEmpty()) {
				confirmInventory();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
			errores.add(Utilerias.getLabel("error.confirma.complete")
					+ e.getMessage());
		}

		return errores.isEmpty(); // Lama .- Regresamos los errores
	}

	/**
	 * Recorrer las lineas para encontrar si hay faltantes
	 * 
	 * @return
	 * @throws Exception
	 */
	private BigDecimal qtyBackorder() throws Exception {
		log.log(Level.INFO, "qtyBackorder");

		// actualizamos la confirmacion del movimiento antes de iniciar el
		// workflow
		BigDecimal faltantes = BigDecimal.ZERO;

		for (int i = 0; i < linesConfView.size(); i++) {

			// Actualizar las cantidades del bean.
			final ConfirmaDetView mConfirmaDetView = (ConfirmaDetView) linesConfView
					.get(i);

			// Linea del producto a confirmar
			final MMovementLine mMovementLine = new MMovementLine(mCtx,
					(int) mConfirmaDetView.getMovementLineID(),
					mTrx.getTrxName());

			/*
			 * MStorage para almacen que surte. Solamente se le resta la
			 * cantidad surtida a la columna qtyReserved de manera que la
			 * cantidad quede igual que antes de haber hecho la confirmacion, y
			 * asi evitar conflictos con el proceso natural de Compiere
			 */
			// Alejandro. Si no se pudo actualizar la cantidad reservada,
			// regresa el error.
			if (!MStorage.add(mCtx, mMovementLine.getM_LocatorTo_ID(),
					mMovementLine.getM_Locator_ID(),
					(int) mConfirmaDetView.getProductID(), 0, 0, Env.ZERO,
					mConfirmaDetView.getTargetQty().negate(), Env.ZERO,
					mTrx.getTrxName())) {// cantidad Original 10
				log.log(Level.INFO, "confirmar.1");
				errores.add(Utilerias.getLabel("error.confirma.cantReservada"));
				break;
			}

			/*
			 * Noelia: si es ceye se solicita la scrapped, sino ConfirmedQty
			 * scrapped = enviada - recibida devuelta = solicitada - confirmada
			 */
			if (isCeye) {
				List<String> ltsErrorCeye = mConfirmaDetView
						.setQtyCeye(mMovementLine);
				if (!ltsErrorCeye.isEmpty()) {
					for (int j = 0; j < ltsErrorCeye.size(); j++) {
						errores.add(Utilerias.getLabel(ltsErrorCeye.get(j)));
					}
					break;
				}

			} else {
				mConfirmaDetView.setQtyConfirm();
			}// fin Noelia

			// Falto ??
			faltantes = faltantes.add(mConfirmaDetView.getOriginalQty()
					.subtract(mConfirmaDetView.getTargetQty()));// (solicitada
			// -
			// enviada)
			// //FIXME

			if (!updateMovConfirm(mConfirmaDetView, i)) {
				break;
			}

			// si es devolucion, y la linea es de numero de serie eliminamos
			// el registro de la tabla
			if (!deleteNumSerie(mConfirmaDetView)) {
				break;
			}

		} // fin for
		return faltantes;
	}

	/**
	 * 
	 * @param mConfirmaDetView
	 * @return
	 * @throws Exception
	 */
	private boolean deleteNumSerie(final ConfirmaDetView mConfirmaDetView)
			throws Exception {
		log.log(Level.INFO, "deleteNumSerie");
		// si es devolucion, y la linea es de numero de serie eliminamos
		// el registro de la tabla
		if (mMovement.isDevol() && mConfirmaDetView.getNumSerie() != null) {
			final MEXMENumSerie numSerie = MEXMENumSerie.getFromNumSerie(mCtx,
					mConfirmaDetView.getNumSerie(),
					(int) mConfirmaDetView.getProductID(), mTrx.getTrxName());

			if (!numSerie.delete(false, mTrx.getTrxName())) {
				log.log(Level.INFO, "deleteNumSerie.5");
				errores.add(Utilerias.getLabel("error.numSerie.noSave",
						String.valueOf(mConfirmaDetView.getLine())));
			}
		}
		return errores.isEmpty();
	}

	/**
	 * Actualizar confirmacion de liena
	 * 
	 * @param mConfirmaDetView
	 * @param index
	 * @return
	 * @throws SQLException
	 */
	private boolean updateMovConfirm(final ConfirmaDetView mConfirmaDetView,
			final int index) throws SQLException {
		// Linea original del movimiento
		final MMovementLineConfirm mMovtLineConfirm = MMovementLineConfirm
				.getFromMovLine(mCtx,
						(int) mConfirmaDetView.getMovementLineID(),
						mTrx.getTrxName());
		// Que exista la linea original
		if (mMovtLineConfirm == null) {
			log.log(Level.SEVERE, "mMovtLineConfirm == null" + index);
			errores.add(Utilerias.getLabel("error.confirma.guardaLinea"));

		} else {

			log.log(Level.INFO, "confirmar.3");

			// if(difference.equals("Y")) {linesView
			if (configEC.isCargaDiferAlm()) {

				// Al almacen que surte
				mMovtLineConfirm.setDifferenceQty(mConfirmaDetView
						.getScrappedQty());// Se genera el inv fisico en el
											// almacen que surte
				mMovtLineConfirm.setScrappedQty(BigDecimal.ZERO);
				mMovtLineConfirm.setConfirmedQty(mConfirmaDetView
						.getTargetQty());
				/**
				 * Cambios generados por confirmacion de productos con
				 * diferencia que genera una salida al gasto
				 */

				mMovtLineConfirm.setDifferenceQty_Vol(mConfirmaDetView
						.getScrappedQty_Vol());
				mMovtLineConfirm.setScrappedQty_Vol(BigDecimal.ZERO);
				mMovtLineConfirm.setConfirmedQty_Vol(mConfirmaDetView
						.getTargetQty_Vol());
				/**
				 * TWRY: Cambios generados por confirmacion de productos con
				 * diferencia que genera una salida al gasto
				 */
				mMovement.setProdOrTray(true);
				/**
				 * Cambios generados por confirmacion de productos con
				 * diferencia que genera una salida al gasto
				 */

			} else {

				mMovtLineConfirm.setDifferenceQty(BigDecimal.ZERO);
				mMovtLineConfirm.setScrappedQty(mConfirmaDetView
						.getScrappedQty());
				mMovtLineConfirm.setConfirmedQty(mConfirmaDetView
						.getConfirmedQty());
				/**
				 * Cambios generados por confirmacion de productos con
				 * diferencia que genera una salida al gasto
				 */

				mMovtLineConfirm.setDifferenceQty_Vol(BigDecimal.ZERO);
				mMovtLineConfirm.setScrappedQty_Vol(mConfirmaDetView
						.getScrappedQty_Vol());
				mMovtLineConfirm.setConfirmedQty_Vol(mConfirmaDetView
						.getConfirmedQty_Vol());
				/**
				 * Cambios generados por confirmacion de productos con
				 * diferencia que genera una salida al gasto
				 */
				// Coloca la cantidad solicitada en cero al completar el
				// movimiento
				mMovement.setProdOrTray(true);
			}

			mMovtLineConfirm.setDescription(mConfirmaDetView.getDescription());

			if (!mMovtLineConfirm.save(mTrx.getTrxName())) {
				log.log(Level.INFO, "confirmar.4");
				errores.add(Utilerias.getLabel("error.confirma.guardaLinea"));
			}

		}
		return errores.isEmpty();
	}

	/**
	 * Completar la confirmación
	 * 
	 * @param mov
	 * @return
	 */
	private boolean completeItConfirm() {
		log.log(Level.INFO, "completeIt");

		String status = mMovementConfirm.completeIt();
		mMovementConfirm.setDocStatus(status);
		if (!mMovementConfirm.save(mTrx.getTrxName())
				|| !status.equals(DocAction.STATUS_Completed)) {
			log.log(Level.INFO, "completeIt.1");
			errores.add(Utilerias.getLabel("error.order.complete"));
		}

		status = mMovement.completeIt();
		mMovement.setDocStatus(status);
		if (!mMovement.save(mTrx.getTrxName())
				|| !status.equals(DocAction.STATUS_Completed)) {
			log.log(Level.INFO, "completeIt.2");
			errores.add(Utilerias.getLabel("error.order.complete"));
		}

		return errores.isEmpty();
	}

	/**
	 * Crear backorder
	 * 
	 * @param faltantes
	 * @return
	 * @throws Exception
	 */
	private boolean createBackorder(final BigDecimal faltantes)
			throws Exception {
		log.log(Level.INFO, "backorder");

		// si hubo diferencias generar back orders
		if (faltantes.compareTo(BigDecimal.ZERO) != 0 && configEC.isGeneraBO() // hubo
																				// diferencia
																				// entre
																				// lo
																				// pedido
																				// y
				// confirmado -- > 0
				&& !mMovement.isBackorder() /* && mMovement.isInTransit() */// FIXME
																			// ????
				&& !mMovement.isDevol()// el mov.
		// actual no es
		// un back order
		) {

			// insertar el movimiento
			final MMovement movBackOrdr = new MMovement(mCtx, 0,
					mTrx.getTrxName());
			PO.copyValues(mMovement, movBackOrdr);

			// Propios del backorder
			movBackOrdr.setDescription("BackOrder conf. ="
					+ mMovement.getDocumentNo());
			movBackOrdr.setBackorder(true);
			movBackOrdr.setMovementDate(new Timestamp(System
					.currentTimeMillis()));
			movBackOrdr.setProcessing(false);
			movBackOrdr.setDocAction(MMovement.ACTION_Complete);
			movBackOrdr.setDocStatus(MMovement.STATUS_Drafted);
			movBackOrdr.setApprovalAmt(Env.ZERO);

			// Guardamos el usuario que solicita el backorder - Jesus Cantu
			if (movBackOrdr.save(mTrx.getTrxName())) {
				backOrderID = movBackOrdr.getM_Movement_ID();
				if (!MMovement.createBackorder(movBackOrdr, linesConfView)) {
					log.log(Level.INFO, "backorder.2");
					errores.add(Utilerias
							.getLabel("error.traspasos.noInsertMovLine"));
				}

			} else {
				log.log(Level.INFO, "backorder.1");
				errores.add(Utilerias.getLabel("error.traspasos.noInsertMov"));
			}

		}
		return errores.isEmpty();
	}

	/**
	 * Completa el inventario fisico en caso de que se haya generado
	 * 
	 * @return listado de errores
	 */
	private boolean confirmInventory() {
		log.log(Level.INFO, "confirInv");

		//
		if (mMovementConfirm.getM_Inventory_ID() > 0) {
			final MInventory inventory = new MInventory(mCtx,
					mMovementConfirm.getM_Inventory_ID(), mTrx.getTrxName());
			inventory.setDocStatus(inventory.completeIt());
			inventory.setProcess("Confirmacion");
			if (!inventory.save(mTrx.getTrxName())
					|| !DocAction.STATUS_Completed.equals(inventory
							.getDocStatus())) {
				errores.add(Utilerias.getLabel("error.order.complete"));
			}
			/**
			 * Cambios generados por confirmacion de productos con diferencia
			 * que genera una salida al gasto
			 */
			// Como no se valido lass existencias en el before se valida en este
			// punto
			for (int i = 0; i < inventory.getLines(true).length; i++) {
				if(!inventory.getLines(false)[i].validarExistencias(false)){
					errores.add(Utilerias.getLabel("error.order.complete"));
					break;
				}
			}
		}// Fin Noelia.
		return errores.isEmpty();
	}

	/**************************************************************/
	public int getBackOrderID() {
		return backOrderID;
	}

	public boolean isCharged() {
		return isCharged;
	}

	public void setCharged(final boolean isCharged) {
		this.isCharged = isCharged;
	}
}
