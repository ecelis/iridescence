/**
 * 
 */
package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MDocType;
import org.compiere.model.MEXMEDocType;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMENumSerie;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementConfirm;
import org.compiere.model.MMovementLine;
import org.compiere.model.MMovementLineConfirm;
import org.compiere.model.MPeriod;
import org.compiere.model.MStorage;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MWarehouse;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

/**
 * @author twry
 * * @deprecated
 */
public abstract class AbstractStockTransfer implements ProcessCallMovement {

	/** Logger */
	protected CLogger log = CLogger.getCLogger(AbstractStockTransfer.class);
	/** contexto */
	protected Properties mCtx;
	/** transaccion Considera que puede existir o no */
	protected Trx mTrx;
	/** listado de errores */
	protected List<String> errores = new ArrayList<String>();
	/** Lineas de movimientos a almacen */
	protected List<MMovementLine> mMovementLines;
	/** Movimiento Draft */
	protected MMovement mMovement;
	/** Lineas de movimientos a almacen */
	protected List<MMovementLineConfirm> mMovConfirmLines;
	/** Movimiento Draft */
	protected MMovementConfirm mMovementConfirm;
	/** parametros */
	private ProcessInfoParameter[] infoParameter = null;
	/** error */
	public static final String ERROR = "@Error@";
	/** Validar pasos por secuencia */
	public boolean validatePrevious = false;

	/**
	 * Start the process. Calls the abstract methods <code>process</code>.
	 * 
	 * @param ctx
	 *            Context
	 * @param trx
	 *            Transacción
	 * @return true if the next process should be performed
	 * @see org.compiere.model.bpm.ProcessCallMovement#startProcess(java.util.Properties,
	 *      org.compiere.util.Trx)
	 */
	@Override
	public final boolean startProcess(final Properties ctx, final Trx trx) {
		mCtx = ctx == null ? Env.getCtx() : ctx;
		mTrx = trx;
		final boolean localTrx = mTrx == null;
		errores.clear();

		try {
			if (localTrx) {
				mTrx = Trx.get(Trx.createTrxName("Stock"), true);
			}

			final boolean success = process();
			// success = false;
			if (localTrx) {
				if (success) {
					try {
						mTrx.commit(true);//
					} catch (Exception e) {
						mTrx.rollback();
						log.log(Level.SEVERE, "Commit failed.", e);//
						errores.add("Commit Failed.");
					}
				} else {
					errores.add("Process Failed.");
					mTrx.rollback();
				}
			}
		} finally {
			if (localTrx) {
				Trx.close(mTrx, true);
				mTrx = null;
			}
		}
		return errores.isEmpty();
	} // startProcess

	/**
	 * Process
	 * 
	 * @return true if success
	 */
	private boolean process() {
		String msg = null;
		boolean success = true;
		try {
			prepare();
			msg = doIt();
		} catch (Throwable e) {
			msg = e.getLocalizedMessage();
			if (msg == null) {
				msg = e.toString();
			}
			if (e.getCause() != null) {
				log.log(Level.SEVERE, msg, e.getCause());
			} else if (CLogMgt.isLevelFine()) {
				log.log(Level.WARNING, msg, e);
			} else {
				log.log(Level.SEVERE, msg, e);
			}
			success = false;
		}

		// transaction should rollback if there are error in process
		if (msg.startsWith(ERROR)) {
			success = false;
		} else if (msg.startsWith("@OK@")) {
			msg = Msg.parseTranslation(mCtx, msg);
		} else {
			msg = Msg.parseTranslation(mCtx, msg);
			success = false;
			errores.add(msg);
		}
		
		return success;
	} // process

	/**
	 * Prepare object and validations
	 * 
	 * @throws Exception
	 *             if not successful e.g. throw new CompiereUserError
	 *             ("@FillMandatory@  @C_BankAccount_ID@");
	 */
	abstract protected void prepare() throws Exception;

	/**
	 * Perform process.
	 * 
	 * @return Message (variables are parsed)
	 * @throws Exception
	 *             if not successful
	 */
	abstract protected String doIt() throws Exception;

	/**
	 * Get Parameter
	 * 
	 * @return parameter
	 */
	public ProcessInfoParameter[] getParameter() {
		return infoParameter;
	}

	/**
	 * Parametros
	 * 
	 * @param infoParameter
	 */
	public void setInfoParameter(final ProcessInfoParameter[] infoParameter) {
		// String parameterName, Nombre del parametro
		// Object parameter, Rango inicial
		// Object parameter_To, Rango final
		// String info,
		// String info_To)
		this.infoParameter = infoParameter;
	}

	/**
	 * Get Parameter
	 * 
	 * @return parameter
	 */
	public ProcessInfoParameter getParameter(final String parameterName) {
		for (int i = 0; i < infoParameter.length; i++) {
			if (parameterName.equals(infoParameter[i].getParameterName())) {
				return infoParameter[i];
			}
		}
		return new ProcessInfoParameter("", null, null, "", "");
	}

	/**********************/

	public boolean surtidoPendiente(){
		return mMovement!=null && (MMovement.DOCSTATUS_Drafted.equals(mMovement.getDocStatus()) 
				|| MMovement.DOCSTATUS_InProgress.equals(mMovement.getDocStatus())
				) && validatePrevious;// esta solicitado
				
	}
	
	public boolean confirmacionPendiente(){
		return mMovement!=null && MMovement.DOCSTATUS_InProgress.equals(mMovement.getDocStatus()) && validatePrevious;// esta surtido
	}
	
	/**
	 * Validamos la forma
	 */
	public List<String> validate(final int docStep,
			final List<? extends MMovementLine> lines) {
		log.log(Level.INFO, "******* validate ****************");
//		final List<String> errores = new ArrayList<String>();

		try {

			// validamos que la cuenta paciente este activa en caso de que
			// exista
			if (mMovement != null && mMovement.getEXME_CtaPac_ID() > 0
					&& mMovement.getCtaPac().getFechaCierre() != null) {
				log.log(Level.INFO, "validate.cta cerrada");
				errores.add(Utilerias.getLabel("error.traspasos.ctaPac"));
			}

			// Tipo de documento
			final List<LabelValueBean> tipoDoc = MEXMEDocType.getTipoDocMov(
					mCtx, null);
			MDocType docType = null;
			if (!tipoDoc.isEmpty()) {
				// asignamos el id del almacen (el primero de la lista)
				docType = new MDocType(mCtx, Integer.valueOf(tipoDoc.get(0)
						.getValue()), null);
			}

			// validar que el tipo de documento este en transito
			if (docType == null) {
				log.log(Level.INFO, "validate.sin tipo doc");
				errores.add(Utilerias.getLabel("error.traspasos.noTipoDoc"));
			} else if (!docType.isInTransit()) {
				log.log(Level.INFO, "validate.tipo doc en transito");
				errores.add(Utilerias.getLabel("error.traspasos.tipoDoc"));
			}

			// validar que el periodo se encuentre abierto
			Timestamp movementDate = DB.getTSForOrg(mCtx);
			if (mMovement != null) {
				movementDate = mMovement.getMovementDate();
			}

			// Periodo abierto
			final boolean isOpen = MPeriod.isOpen(mCtx, movementDate,
					MDocType.DOCBASETYPE_MaterialMovement,
					Env.getAD_Org_ID(mCtx));
			if (!isOpen) {
				log.log(Level.INFO,
						"validate.periodo cerrado" + movementDate.toString());
				errores.add(Utilerias.getLabel("error.traspasos.perNoAbierto"));
			}

			// validar que exista al menos una linea
			if (Constantes.SOLICITUD == docStep) {
				validateRequest(lines);
			} else if (Constantes.SURTIDO == docStep) {
				validateFill();
			} else if (Constantes.CONFIRMACION == docStep) {
				validateConfirm();
			}

			// Itera las lineas para validar cada producto
			validateLines(docStep, lines);

		} catch (Exception e) {
			log.log(Level.SEVERE, "-- validate", e);
			errores.add(Utilerias.getLabel("error.traspasos.validateForm",
					e.getMessage()));
		}

		return errores;
	}

	/**
	 * ValidateLines
	 * 
	 * @param ctx
	 * @param docStep
	 * @param mMovement
	 * @param lines
	 * @return
	 * @throws SQLException
	 */
	private boolean validateLines(final int docStep,
			final List<? extends MMovementLine> lines) throws SQLException {
		log.log(Level.INFO, "validateLines");
		BigDecimal totalQty = Env.ZERO;

		// Itera las lineas para validar cada producto
		for (final MMovementLine linea : lines) {
			BigDecimal qtyValidar = Env.ZERO;

			//
			if (Constantes.SOLICITUD == docStep) {
				if (validateLineRequest(linea)) {
					qtyValidar = linea.getOriginalQty();
				} else {
					break;
				}

			} else if (Constantes.SURTIDO == docStep) {
				if (validateLineFill(linea)) {
					qtyValidar = linea.getTargetQty();
				} else {
					break;
				}

			} else if (Constantes.CONFIRMACION == docStep) {
				log.log(Level.INFO, "validateLines.9");

			} else if (Constantes.DEVOLUCION == docStep) {
				log.log(Level.INFO, "validateLines.10");
			}

			// Vamos sumando la cantidad a surtir.
			totalQty = totalQty.add(qtyValidar);
		}

		// Que exista que surtir
		if (totalQty.compareTo(Env.ZERO) <= 0
				&& Constantes.CONFIRMACION != docStep) {
			log.log(Level.INFO, "validate.totalQty");
			errores.add(Utilerias.getLabel("error.traspasos.linesSurtir0"));
		}

		return errores.isEmpty();
	}

	/**
	 * Validar linea del surtido
	 * 
	 * @param linea
	 * @return
	 * @throws SQLException
	 */
	private boolean validateLineFill(final MMovementLine linea)
			throws SQLException {
		log.log(Level.INFO, "validateLineFill");

		// Manejo de existencias
		final boolean controlaStock = MEXMEMejoras.get(linea.getCtx())
				.isControlExistencias();

		// validamos que la cantidad surtida no sea negativa
		if (linea.getTargetQty().compareTo(Env.ZERO) < 0) {
			log.log(Level.INFO, "validateLineFill.1");
			errores.add(Utilerias.getLabel("error.traspasos.surtCero",
					String.valueOf(linea.getLine())));

		}

		// validamos q la cant. surtida(target) sea menor o
		// igual a la solicitada(original)
		if (linea.getTargetQty().compareTo(linea.getOriginalQty()) > 0) {
			log.log(Level.INFO, "validateLineFill.2");
			errores.add(Utilerias.getLabel("error.traspasos.surtSolic",
					String.valueOf(linea.getLine())));
		}

		int almacenSurte = MWarehouse.getFromLocator(Env.getCtx(),linea.getM_Locator_ID(), null).getM_Warehouse_ID();
		
		// Si controla stock se valida que tenga existencias
		if (linea.getTargetQty().compareTo(Env.ZERO) > 0
				&& controlaStock
				&& MStorage.puedeSurtir(
						(int) linea.getProduct().getM_Product_ID(),
						almacenSurte ,
						linea.getConfirmedQty()).compareTo(Env.ZERO) < 0) {
			log.log(Level.INFO, "validateLineFill.3");
			errores.add(Utilerias.getLabel("error.encCtaPac.noExistenProd",
					linea.getProduct().getName()));
		}
		// Si es cuenta paciente, numero de serie el producto la cantidad debe ser uno
		if (mMovement.getEXME_CtaPac_ID() > 0
				&& linea.getProduct().isNumSerie()
				&& linea.getTargetQty() != null
				&& linea.getTargetQty().compareTo(Env.ONE) != 0) {
			log.log(Level.INFO, "validateLineFill.4");
			errores.add(Utilerias.getLabel("error.traspasos.cantidadMaxEsUno",
					linea.getProduct().getName()));
		}
		// Si es cuenta paciente, numero de serie el producto debe tener ese dato la linea
		if (mMovement.getEXME_CtaPac_ID() > 0
				&& linea.getProduct().isNumSerie()
				// el numero de serie no debe ser nulo
				&& StringUtils.isEmpty(linea.getNumSerie())) {
			log.log(Level.INFO, "validateLineFill.5");
			errores.add(Utilerias.getLabel("error.numSerie.notNull", linea
					.getProduct().getName(), linea.getNumSerie()));
		}
		// validamos que no haya sido cargado para otra ctapac
		if (mMovement.getEXME_CtaPac_ID() > 0 && linea.getProduct().isNumSerie()
				&& !MEXMENumSerie.numSerieValido(mCtx, linea.getNumSerie(),
				(int) linea.getM_Product_ID(), null)) {
			log.log(Level.INFO, "validateLineFill.6");
			errores.add(Utilerias.getLabel("error.numSerie.existe",
					linea.getNumSerie(), linea.getProduct().getName()));
		}

		return errores.isEmpty();
	}

	/**
	 * Validar linea de la solicitud
	 * 
	 * @param linea
	 * @return
	 */
	private boolean validateLineRequest(final MMovementLine linea) {
		log.log(Level.INFO, "validateLineRequest");

		if (linea.getOriginalQty().compareTo(Env.ZERO) <= 0 /*
															 * ||
															 * linea.getMovementQty
															 * (
															 * ).compareTo(Env.
															 * ZERO ) <= 0
															 */) {
			log.log(Level.INFO, "validateLineRequest.1");
			errores.add(Utilerias.getLabel("error.traspasos.surtSolic",
					String.valueOf(linea.getLine())));
		}

		// validacion de conversiones de UDM
		if (linea.getProduct().getC_UOM_ID() != linea.getProduct()
				.getC_UOMVolume_ID()) {
			final MUOMConversion rates = MEXMEUOMConversion
					.validateConversions(Env.getCtx(), linea.getProduct()
							.getM_Product_ID(), linea.getProduct()
							.getC_UOMVolume_ID(), null);
			if (rates == null) {
				errores.add(Utilerias.getLabel("error.udm.noExisteConversion",
						linea.getProduct().getName()));
				log.log(Level.INFO, "validateLineRequest.2");
			}
		}
		return errores.isEmpty();
	}

	/**
	 * Validaciones de la solicitud
	 * 
	 * @return
	 */
	private boolean validateRequest(final List<? extends MMovementLine> lines) {
		log.log(Level.INFO, "validateRequest");
		
		if(lines == null || lines.isEmpty()){
			log.log(Level.INFO, "validateRequest.1");
			errores.add(Utilerias.getLabel("error.traspasos.lines"));
		}
		return errores.isEmpty();
	}

	/**
	 * Validaciones del surtido
	 * 
	 * @return
	 */
	private boolean validateFill() {
		log.log(Level.INFO, "validateFill");
		if (mMovementLines == null || mMovementLines.isEmpty()) {
			log.log(Level.INFO, "validateFill.1");
			errores.add(Utilerias.getLabel("error.traspasos.lines"));
		}

		if (mMovement == null) {
			log.log(Level.INFO, "validateFill.2");
			errores.add(Utilerias.getLabel("error.traspasos.noInsertMov"));
		}

		// Validamos que no halla sido surtido. debe estar en el surtido DR
		if (mMovement.getDocStatus().equalsIgnoreCase(
				MMovement.DOCSTATUS_InProgress)) {
			log.log(Level.INFO, "validateFill.3");
			errores.add(Utilerias.getLabel("error.traspasos.isSurtido2",
					mMovement.getDocumentNo()));
		}

		// Cancelado
		if (mMovement.getDocStatus().equalsIgnoreCase(
				MMovement.DOCSTATUS_Voided)) {
			log.log(Level.INFO, "validateFill.4");
			errores.add(Utilerias.getLabel("error.traspasos.isCancelado",
					mMovement.getDocumentNo()));
		}

		return errores.isEmpty();
	}

	/**
	 * Validaciones de la confirmación
	 * 
	 * @return
	 */
	private boolean validateConfirm() {
		log.log(Level.INFO, "validateConfirm");

		// Validamos que no halla sido confirmado.
		if (mMovement.getDocStatus().equalsIgnoreCase(
				MMovement.DOCSTATUS_Completed)) {
			log.log(Level.INFO, "validateConfirm.1");
			errores.add(Utilerias.getLabel("error.traspasos.isConfirmado",
					mMovement.getDocumentNo()));

		}

		// obtenemos los tipos de documento de tipo Mov
		final MDocType docType = new MDocType(mCtx,
				mMovement.getC_DocType_ID(), null);
		if (!docType.isInTransit()) {
			log.log(Level.INFO, "validateConfirm.2");
			errores.add(Utilerias.getLabel("error.traspasos.tipoDoc"));
		}

		// validar que el periodo para la fecha del movimiento se
		// encuentre abierto
		if (!MPeriod.isOpen(mCtx, mMovement.getMovementDate(),
				MDocType.DOCBASETYPE_MaterialMovement, Env.getAD_Org_ID(mCtx))) {
			log.log(Level.INFO, "validateConfirm.3");
			errores.add(Utilerias.getLabel("error.traspasos.perNoAbierto"));
		}

		// validar que el periodo para la fecha del confirmacion
		// encuentre abierto
		if (!MPeriod.isOpen(mCtx, mMovementConfirm.getCreated(),
				MDocType.DOCBASETYPE_MaterialMovement, Env.getAD_Org_ID(mCtx))) {
			log.log(Level.INFO, "validateConfirm.4");
			errores.add(Utilerias.getLabel("error.traspasos.perNoAbierto"));
		}

		return errores.isEmpty();
	}
	
	public List<String> getErrores() {
		return errores;
	}

	public void setErrores(List<String> errores) {
		this.errores = errores;
	}

}
