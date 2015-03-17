package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.AlmacenesDB;
import org.compiere.model.ConfirmaDet;
import org.compiere.model.ConfirmaDetView;
import org.compiere.model.MConfigEC;
import org.compiere.model.MDocType;
import org.compiere.model.MEXMEDocType;
import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MMovement;
import org.compiere.model.MEXMENumSerie;
import org.compiere.model.MEstServAlm;
import org.compiere.model.MMovementConfirm;
import org.compiere.model.MMovementLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MPeriod;
import org.compiere.model.MProcess;
import org.compiere.model.MStorage;
import org.compiere.model.ModelError;
import org.compiere.model.MovementLine;
import org.compiere.model.X_M_Warehouse;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ExpertException;
import org.compiere.util.Trx;
import org.compiere.wf.MWorkflow;

/**
 * 
 * @author twry
 * @deprecated
 */
public class TransferProduct {

	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(TransferProduct.class);
	private transient String trxName = null;
	private transient Properties ctx = null;
	private transient StringBuilder stError = new StringBuilder();
	private transient int movementID = -1;
	private transient String documentNo = "";
	private transient static CLogger log = CLogger
			.getCLogger(TransferProduct.class);
	private transient int movementConfirmID = -1;
	private transient MMovement movement;
	private transient final List<ModelError> errores = new ArrayList<ModelError>();

	/**
	 * Constructor
	 */
	public TransferProduct() {
		super();
	}

	/**
	 * Constructor
	 */
	public TransferProduct(final Properties pctx, final String ptrxName) {
		super();
		ctx = pctx;
		trxName = ptrxName;
	}

	/**
	 * Guarda un nuevo movimiento de almacen (incluyendo detalle)
	 * 
	 */
	public boolean internalTransfer(final int reorderWHId,
			final int almacenLogId, final int ctaPacId, final int productId,
			final long targetQty, final long originalQty,
			final String loteInfo, final int orgTrx,
			final int attribSetInst, final String serie) {
		boolean success = true;

		try {

			final List<MovementLine> movementLines = new ArrayList<MovementLine>();
			final MovementLine movementLine = new MovementLine();
			movementLine.setOriginalQty(originalQty);
			movementLine.setProductID(productId);
			movementLine.setDescription("");
			movementLine.setTargetQty(targetQty);
			movementLine.setSerie(serie);
			movementLine.setOrgTrx(orgTrx);
			movementLine.setLoteInfo(loteInfo);
			movementLine.setCtaPacID(ctaPacId);
			movementLine.setM_AttributeSetInstance(attribSetInst);
			movementLines.add(movementLine);

			movement = generateMovement(reorderWHId, almacenLogId, "",
					ctaPacId, "", movementLines);
			if (movement == null) {
				success = false;
			} else {
				setDocumentNo(movement.getDocumentNo());
				setMovementID(movement.getM_Movement_ID());

				// Empiza Wf
				if (complete()) {
					movement.setReady(true);
				}

				// Actualiza status
				if (movement.save()) {
					success = confirmComplete();
				}
			}
		} catch (ExpertException e) {
			success = false;
		} catch (SQLException e) {
			success = false;
		}

		return success;
	}

	/**
	 * Genera movimiento MMovement
	 * 
	 * @param ctx
	 * @param reorderWHId
	 * @param almacenLogId
	 * @param documentNo
	 * @param ctaPacId
	 * @param description
	 * @param movementLines
	 * @param priorityRule
	 * @param progQuiroId
	 * @param isDevol
	 * @return
	 * @throws ExpertException
	 */
	private MMovement generateMovement(final int reorderWHId,
			final int almacenLogId, final String documentNo,
			final int ctaPacId, final String description,
			final List<MovementLine> movementLines) throws ExpertException {
		Trx mTrxObj = null;
		try {

			if (trxName == null) {
				mTrxObj = Trx.get(Trx.createTrxName("Sol"), true);
				trxName = mTrxObj.getTrxName();
			}

			final MEXMEEstServ est = MEstServAlm.getEstServ(ctx,
					(int) reorderWHId, trxName);

			final String almacenV = new X_M_Warehouse(ctx, almacenLogId,
					trxName).getName();

			movement = AlmacenesDB.insertMovement(ctx, documentNo, false, 0, 0,
					0, est.getAD_OrgTrx_ID(), 0, 0, ctaPacId, 0, description,
					trxName, almacenV);

			final List<MovementLine> htLineas = new ArrayList<MovementLine>();
			for (int i = 0; i < movementLines.size(); i++) {

				final MovementLine lineas = movementLines.get(i);
				lineas.setLine((i + 1) * 10);
				htLineas.add(lineas);
			}

			// insertamos el detalle del movimiento
			final boolean success = AlmacenesDB.insertLine(Env.getCtx(), htLineas,
					false, movement.getM_Movement_ID(), reorderWHId,
					almacenLogId, ctaPacId, trxName);

			if (!success) {
				throw new ExpertException("error.solicitudCharolas");
			}

			if (movement != null) {
				movement.setWarehouseIds();
				movement.setPriorityRule("5");

				if (!movement.save(trxName)) {
					throw new ExpertException(
							"error.traspasos.noUpdateMovPriority");
				}
			}

			if (mTrxObj != null) {
				Trx.commit(mTrxObj);
			}

		} catch (Exception e) {
			throw new ExpertException("error.solicitudCharolas");
		} finally {
			Trx.close(mTrxObj, true);
		}

		return movement;
	}

	/**
	 * el moviento es surtido
	 * 
	 * @param lines
	 * @param trxName
	 * @return
	 */
	private boolean complete() {
		try {

			if (!validate(movement.getLines())) {
				return false;
			}

			// Parametros
			final SurtidoDet surtidoDet = new SurtidoDet(ctx,
					movement.getDocumentNo(), movement.getLines(),
					movement.getEXME_CtaPac_ID(), false, movement.get_ID(),
					movement.getMAlmSurt().getM_Warehouse_ID(), trxName);

			// Genera Workflow
			final List<String[]> complete = surtidoDet.complete();
			if (complete.isEmpty()) {
				movementConfirmID = surtidoDet.getMovementConfirmID();

			} else {
				errores.clear();
				for (int i = 0; i < complete.size(); i++) {

					if (complete.get(i)[0].equalsIgnoreCase("THROW_EXCEPTION")) {
						errores.add(new ModelError(1, complete.get(i)[1],
								complete.get(i)[2]));
						throw new Exception();
					} else {
						if (!"GLOBAL_MESSAGE".equals(complete.get(i)[0])) {
							errores.add(new ModelError(1, complete.get(i)[1],
									complete.get(i)[2]));
						}
					}
				}
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "-- complete : ", e);
			errores.add(new ModelError(1, "error.traspasos.completando", e
					.getMessage()));
		}

		return errores.isEmpty();
	}

	/**
	 * Validamos las lineas de moviemiento
	 * 
	 * @param lines
	 * @return true se puede hacer el movimiento
	 */
	private boolean validate(final List<MMovementLine> lines) {
		try {
			if (movement == null) {
				errores.add(new ModelError(1, "error.traspasos.isSurtido2",
						movement.getDocumentNo()));
				return errores.isEmpty();
			}

			movement.setWarehouseIds();

			// Validamos que no halla sido surtido.
			if (movement.getDocStatus().equalsIgnoreCase(
					MMovement.DOCSTATUS_InProgress)) {
				errores.add(new ModelError(1, "error.traspasos.isSurtido2",
						movement.getDocumentNo()));
			}

			double totalQty = 0.0;
			if (lines.isEmpty()) {
				errores.add(new ModelError(1, "error.traspasos.lines"));
			} else {

				// validar que exista al menos una linea
				for (MMovementLine linea : lines) {

					// validamos que la cantidad surtida no sea negativa
					if (linea.getTargetQty().doubleValue() < 0) {
						errores.add(new ModelError(1,
								"error.traspasos.surtCero", String
										.valueOf(linea.getLine())));

					} else if (linea.getTargetQty().doubleValue() > linea
							.getOriginalQty().doubleValue()) {

						// validamos q la cant. surtida(target) sea menor o
						// igual a la solicitada(original)
						errores.add(new ModelError(1,
								"error.traspasos.surtSolic", String
										.valueOf(linea.getLine())));

					}
					/*
					 * else if (linea.getTargetQty().doubleValue() > 0 &&
					 * MStorage.puedeSurtir((int)
					 * linea.getProduct().getM_Product_ID(),
					 * movement.getM_AlmSurt().getM_Warehouse_ID(),
					 * linea.getTargetQty()).compareTo(Env.ZERO) < 0 &&
					 * MEXMEMejoras.get(ctx).isControlExistencias()) { // LRHU.
					 * Si controla stock se valida que existan productos //
					 * validar que tenga existencias errores.add(new
					 * ModelError(1, "error.encCtaPac.noExistenProd",
					 * linea.getProduct().getName())); }
					 */

					// Vamos sumando la cantidad a surtir.
					totalQty += linea.getTargetQty().doubleValue();
				}
			}

			if (totalQty <= 0.0) {
				errores.add(new ModelError(1, "error.traspasos.linesSurtir0"));
			}

			final List<LabelValueBean> tipoDoc = MEXMEDocType.getTipoDocMov(
					ctx, trxName);
			MDocType docType = null;

			if (!tipoDoc.isEmpty()) {
				// asignamos el id del almacen (el primero de la lista)
				docType = new MDocType(ctx, Integer.valueOf(tipoDoc.get(0)
						.getValue()), trxName);
			}

			// validar que el tipo de documento este en transito
			if (docType == null) {
				errores.add(new ModelError(1, "error.traspasos.noTipoDoc"));
			} else if (!docType.isInTransit()) {
				errores.add(new ModelError(1, "error.traspasos.tipoDoc"));
			}

			// validar que el periodo se encuentre abierto
			final boolean isOpen = MPeriod.isOpen(ctx,
					movement.getMovementDate(),
					MDocType.DOCBASETYPE_MaterialMovement,
					movement.getAD_Org_ID());

			if (!isOpen) {
				errores.add(new ModelError(1, "error.traspasos.perNoAbierto"));
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "-- validate", e);
			errores.add(new ModelError(1, "error.traspasos.validate", e
					.getMessage()));
		}

		return errores.isEmpty();
	}

	// //************************** PASO 2 ***************************//

	/**
	 * Completar el movimiento de inventario con la confirmacion
	 * 
	 * @return true se ha hecho la confirmacion
	 * @throws SQLException
	 */
	private boolean confirmComplete() throws SQLException {
		try {

			if (movementConfirmID <= 0 || movement == null) {
				return false;
			}

			final List<ConfirmaDetView> lines = MMovementConfirm
					.getMovementConfirmDet(movementConfirmID, true, trxName);
			if (lines.isEmpty() || !validateConfirm(lines)) {
				return false;
			} else {

				// Validamos que no halla sido confirmado.
				final MConfigEC configEC = MConfigEC.get(ctx, null);
				if (configEC == null) {
					errores.add(new ModelError(1, "error.citas.noConfigEC"));
				}

				// Se instancia la clase que llevara a cabo el proceso de
				final ConfirmaDet confirmaDet = new ConfirmaDet(ctx,
						movement.getDocumentNo(), lines, "Y",
						String.valueOf(movement.getEXME_CtaPac_ID()), configEC,
						0, false, movementConfirmID, "", trxName);

				final ArrayList<String[]> confirmaMM = confirmaDet.complete();
				if (confirmaMM != null && !confirmaMM.isEmpty()) {

					// validar que el producto no se encuentre en la lista
					for (int i = 0; i < confirmaMM.size(); i++) {

						if (confirmaMM.get(i)[0]
								.equalsIgnoreCase("THROW_EXCEPTION")) {
							errores.add(new ModelError(1, confirmaMM.get(i)[1],
									confirmaMM.get(i)[2]));
							throw new Exception();
						} else {
							errores.add(new ModelError(1, confirmaMM.get(i)[1],
									confirmaMM.get(i)[2]));
						}

					}

				}

			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "-- complete : ", e);
			errores.add(new ModelError(1, "error.confirma.complete", e
					.getMessage()));
		}
		return errores.isEmpty();
	}

	/**
	 * Validar la confirmacion
	 * 
	 * @param lines
	 *            lineas de confirmacion
	 * @return true es posible hacer la confirmacion
	 */
	private boolean validateConfirm(final List<ConfirmaDetView> lines) {

		try {

			if (movement.getDocStatus().equalsIgnoreCase(
					MMovement.DOCSTATUS_Completed)) {
				errores.add(new ModelError(1, "error.traspasos.isConfirmado",
						movement.getDocumentNo()));
			}

			// validar que exista al menos una linea
			if (lines == null || lines.isEmpty()) {
				errores.add(new ModelError(1, "error.traspasos.lines"));
			} else {

				for (int i = 0; i < lines.size(); i++) {

					final ConfirmaDetView linea = (ConfirmaDetView) lines
							.get(i);

					// validamos q la cant. confirmada no sea negativa
					if (linea.getConfirmedQty().compareTo(BigDecimal.ZERO) < 0) { //linea.getConfirmedQty() < 0
						errores.add(new ModelError(1,
								"error.traspasos.confCero", String
										.valueOf(linea.getLine())));

					} else if (linea.getConfirmedQty().compareTo(linea.getTargetQty())<0) { // linea.getConfirmedQty() < linea.getTargetQty()
						// validamos q la cant. confirmada sea menor o igual a
						// la surtida(target)
						errores.add(new ModelError(1,
								"error.traspasos.confSurt", String
										.valueOf(linea.getLine())));
					}
				}
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "-- validando: ", e);
			errores.add(new ModelError(1, e.getMessage()));
		}

		return errores.isEmpty();

	}

	/*******************************************************************************/
	public void setStError(final StringBuilder stError) {
		this.stError = stError;
	}

	public StringBuilder getStError() {
		return stError;
	}

	public void setMovementID(final int movementID) {
		this.movementID = movementID;
	}

	public int getMovementID() {
		return movementID;
	}

	public void setDocumentNo(final String documentNo) {
		this.documentNo = documentNo;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public int getMovementConfirmID() {
		return movementConfirmID;
	}

	public MMovement getMovement() {
		return movement;
	}

	public void setMovement(final MMovement movement) {
		this.movement = movement;
	}

	/**
	 * 
	 * @author twry
	 * 
	 */
	public class SurtidoDet {

		private transient final Properties ctx;
		private transient String documentNo;
		private List<MMovementLine> lstSurtidoDet = null;
		private transient int ctaPacID = -1;
		private final transient boolean isConsigna;
		private transient int movementID = -1;
		private transient int movementConfirmID = -1;
		private transient int locatorID = -1;
		private transient Trx mtrx = null;
		private String trxName = null;
		private transient String numSerie = null;
		private transient String processed = null;

		/**
		 * Constructor
		 * 
		 * @param ctx
		 * @param documentNo
		 * @param lstSurtidoDet
		 * @param ctaPacID
		 * @param isConsigna
		 * @param movementID
		 * @param locatorID
		 * @param trxName
		 */
		public SurtidoDet(final Properties ctx, final String documentNo,
				final List<MMovementLine> lstSurtidoDet, final int ctaPacID,
				final boolean isConsigna, final int movementID, final int locatorID,
				final String trxName) {

			this.ctx = ctx;
			this.documentNo = documentNo;
			this.lstSurtidoDet = lstSurtidoDet;
			this.ctaPacID = ctaPacID;
			this.isConsigna = isConsigna;
			this.movementID = movementID;
			this.locatorID = locatorID;
			if (trxName == null) {
				mtrx = Trx.get(Trx.createTrxName("po"), true);
				this.trxName = mtrx.getTrxName();
			} else {
				this.trxName = trxName;
			}

		}

		/**
		 * Completa el movimiento de inventario
		 * 
		 * @return
		 */
		public List<String[]> complete() {

			final List<String[]> listaErrores = new ArrayList<String[]>();

			try {
				// TODO: Validacion de que no haya sido conformado aun.
				final MMovement movement = new MMovement(ctx, movementID, trxName);
				if (movement.getDocStatus().equalsIgnoreCase(
						MMovement.DOCSTATUS_Voided)) {
					listaErrores.add(listaErrores.size(), new String[] {
							"GLOBAL_ERROR", "error.traspasos.isCancelado",
							movement.getDocumentNo() });
					return listaErrores;
				}

				// LRHU. Verificamos si el almacen controla existencias de
				// productos
				final boolean controlaStock = MEXMEMejoras.inventories(ctx); // por
				// default
				// todos
				// los
				// almacenes
				// controlan
				// existencias

				// actualizamos el movimiento con la cantidad capturada
				for (int i = 0; i < lstSurtidoDet.size(); i++) {
					final MMovementLine linea = lstSurtidoDet.get(i);
					if (ctaPacID > 0 && linea.getProduct().isNumSerie()
							&& linea.getTargetQty() != null
							&& linea.getTargetQty().compareTo(Env.ONE) != 0) {
						listaErrores.add(listaErrores.size(), new String[] {
								"GLOBAL_ERROR",
								"error.traspasos.cantidadMaxEsUno",
								linea.getProduct().getName() });
						return listaErrores;
						// return new ActionForward(mapping.getInput());

					} else {
						linea.setMovementQty(linea.getTargetQty());
						// Guardamos el precio para almacenes en consigna,
						// si el traspaso es entre almacenes de la organizacion
						// padre, el precio no se podra escribir y
						// no actualizaremos costos, eruiz
						if (isConsigna) {
							linea.setPriceList(new BigDecimal(linea.getPrice()));
						}
					}

					// validamos el numero de serie
					if (ctaPacID > 0 && linea.getProduct().isNumSerie()) {

						// el numero de serie no debe ser nulo
						if (StringUtils.isEmpty(linea.getNumSerie())) {
							listaErrores.add(
									listaErrores.size(),
									new String[] { "GLOBAL_ERROR",
											"error.numSerie.notNull",
											linea.getProduct().getName(),
											linea.getNumSerie() });
							linea.setNumSerie(null);
							return listaErrores;
							// return new ActionForward(mapping.getInput());
						}

						// validamos uqe el Numero de serie no haya sifo ya
						// tecleado en el resto del detalle
						for (int j = 0; j < lstSurtidoDet.size(); j++) {

							final MMovementLine line2 = lstSurtidoDet.get(j);

							if (linea.getLine() != line2.getLine()
									&& linea.getM_Product_ID() == line2
											.getM_Product_ID()
									&& linea.getNumSerie().equalsIgnoreCase(
											line2.getNumSerie())) {

								listaErrores.add(
										listaErrores.size(),
										new String[] { "GLOBAL_ERROR",
												"error.numSerie.lista",
												linea.getNumSerie(),
												linea.getProduct().getName() });
								linea.setNumSerie(null);
								return listaErrores;
								// return new ActionForward(mapping.getInput());
							}

						}
						// validamos que no haya sido cargado para otra ctapac
						if (!MEXMENumSerie.numSerieValido(ctx,
								linea.getNumSerie(),
								(int) linea.getM_Product_ID(), null)) {

							linea.setNumSerie(null);
							listaErrores.add(listaErrores.size(), new String[] {
									"GLOBAL_ERROR", "error.numSerie.existe",
									linea.getNumSerie(),
									linea.getProduct().getName() });
							return listaErrores;
							// return new ActionForward(mapping.getInput());
						}
					}

					if (!linea.save(trxName)) {
						if (mtrx != null) {
							Trx.rollback(mtrx);
						}
						listaErrores.add(
								listaErrores.size(),
								new String[] { "THROW_EXCEPTION",
										"error.numSerie.existe",
										"error.traspasos.guardaLinea",
										String.valueOf(linea.getLine()) });
						return listaErrores;
					} else {
						// LRHU. Si el almacen NO controla las existencias de
						// productos, �stas no se afectan
						// sumamos la cantidad surtida a la columna de qtyOnHand
						if (!controlaStock) {
							MStorage.add(ctx, locatorID,
									linea.getM_Locator_ID(),
									linea.getM_Product_ID(), 0, 0,
									linea.getTargetQty(), linea.getTargetQty(),
									Env.ZERO, trxName);
						} else {
							// MStorage para almacen que surte. Solamente se le
							// suma la cantidad surtida a la columna
							// qtyReserved
							MStorage.add(ctx, locatorID,
									linea.getM_Locator_ID(),
									linea.getM_Product_ID(),
									linea.getM_AttributeSetInstance_ID(),
									linea.getM_AttributeSetInstance_ID(),
									Env.ZERO, linea.getTargetQty(), Env.ZERO,
									trxName);
						}
					}

					// si es numero de serie guardamos el numero de serie en la
					// tabla
					if (ctaPacID > 0 && linea.getProduct().isNumSerie()) {
						final MEXMENumSerie numSerie = new MEXMENumSerie(ctx, 0,
								trxName);

						numSerie.setNumSerie(linea.getNumSerie());
						numSerie.setM_Product_ID(linea.getM_Product_ID());
						numSerie.setM_MovementLine_ID(linea
								.getM_MovementLine_ID());
						numSerie.setEXME_CtaPac_ID(ctaPacID);
						numSerie.setDocStatus(MEXMENumSerie.DOCSTATUS_Surtido);

						if (!numSerie.save()) {
							if (mtrx != null) {
								Trx.rollback(mtrx);
							}
							listaErrores.add(listaErrores.size(), new String[] {
									"THROW_EXCEPTION", "error.numSerie.noSave",
									String.valueOf(linea.getLine()) });
							return listaErrores;
						}
					}
				}

				if (listaErrores.isEmpty()) {
					if (mtrx != null) {
						Trx.commit(mtrx);
					}

					// Workflow
					getInfo(MMovement.Table_Name);
					startWorkflow(ctx, movementID);

					// actualizamos la fecha de ultima actualizacion del
					// movimiento (SYSDATE - Servidor)
					// AlmacenesDB.updateFechaAct(forma.getMovementID());

					// asignamos el numero de documento generado
					MMovementConfirm confirma = MMovementConfirm.get(ctx,
							movementID, trxName);
					if (confirma != null) {
						this.documentNo = confirma.getDocumentNo();
						this.movementConfirmID = confirma.get_ID();
					}
					// forma procesada
					this.processed = "Y";

				} else {
					if (mtrx != null) {
						Trx.rollback(mtrx);
					}
				}
			} catch (Exception e) {
				listaErrores
						.add(listaErrores.size(), new String[] { "Exception",
								"error.traspasos.completando", e.getMessage() });
			} finally {
				if (listaErrores.isEmpty()) {
					if (mtrx != null) {
						Trx.commit(mtrx);
					}
				} else if (mtrx != null) {
					Trx.rollback(mtrx);
				}
				Trx.close(mtrx);
				trxName = null;
			}
			return listaErrores; // Lama .- Regresamos los errores
		}

		public String getProcessed() {
			return processed;
		}

		public String getDocumentNo() {
			return documentNo;
		}

		public int getMovementConfirmID() {
			return movementConfirmID;
		}

		public String getNumSerie() {
			return numSerie;
		}

		public String getTrxName() {
			return trxName;
		}

		public void setTrxName(final String trxName) {
			this.trxName = trxName;
		}

		public List<MMovementLine> getLstSurtidoDet() {
			return lstSurtidoDet;
		}

		public void setLstSurtidoDet(final List<MMovementLine> lstSurtidoDet) {
			this.lstSurtidoDet = lstSurtidoDet;
		}

		// identificador del proceso
		private transient int proceso = 0;

		// identificador del workflow
		private transient int workflow = 0;

		// el nombre del workflow
		private transient String nombre = null;

		// identificador de la tabla
		private transient int tablaID = 0;

		/**
		 * Obtiene la informacion necesaria del flujo de trabajo para la tabla
		 * indicada
		 */
		public void getInfo(final String tabla) throws Exception {

			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rSet = null;

			// obtener el identificador y el nombre del proceso
			sql = "SELECT AD_Process.AD_Process_ID, AD_Process.AD_Workflow_ID, "
					+ "AD_Process.Name, AD_Table.AD_Table_ID "
					+ "FROM AD_Process, AD_Column, AD_Table "
					+ "WHERE AD_Process.AD_Process_ID = AD_Column.AD_Process_ID AND "
					+ "AD_Column.AD_Table_ID = AD_Table.AD_Table_ID AND "
					+ "ColumnName = 'DocAction' AND TableName = ?";

			try {
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setString(1, tabla);
				rSet = pstmt.executeQuery();

				if (rSet.next()) {
					proceso = rSet.getInt("AD_Process_ID");
					workflow = rSet.getInt("AD_Workflow_ID");
					nombre = rSet.getString("Name");
					tablaID = rSet.getInt("AD_Table_ID");

				} else {
					throw new Exception("error.noExisteProceso");
				}
			} catch (SQLException e) {
				slog.log(Level.SEVERE, sql + "   tabla: " + tabla);
				throw new Exception("error.getProcessInfo");
			} finally {
				DB.close(rSet, pstmt);
			}
		}

		/**
		 * Inicia el flujo de trabajo
		 * 
		 * @throws Exception
		 */
		public void startWorkflow(final Properties ctx, final int recordID)
				throws Exception {

			// Crear un nuevo ProcessInfo (nombre del proceso, id. proceso)
			ProcessInfo pInfo = new ProcessInfo(nombre, proceso, tablaID, recordID);
			pInfo.setTransactionName(trxName);

			// Creamos una instancia del Proceso
			MProcess process = MProcess.get(ctx, proceso);

			if (process == null) {
				slog.log(Level.SEVERE, " --Workflow.startWorkflow 02 ");
				throw new Exception("Workflow does not exists.");
			}

			// instancia de proceso
			final MPInstance pInstance = new MPInstance(process, recordID);

			// asignarle el identificador de la instancia
			pInfo.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());

			// y del registro
			pInfo.setRecord_ID(recordID);

			// usuario
			pInfo.setAD_User_ID(Env.getAD_User_ID(ctx));

			// obtener un objeto MWorkflow a partir del contexto e id. workflow
			MWorkflow wflow = MWorkflow.get(ctx, workflow);
			if (wflow != null) {
				wflow.startWait(pInfo);
			} else {
				slog.log(Level.SEVERE, "Workflow is null.");
				throw new Exception("Workflow does not exists.");
			}
		}
	}// ddd
}
