package org.compiere.model.billing;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.I_EXME_ClaimPayment;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MCharge;
import org.compiere.model.MConfigPre;
import org.compiere.model.MDocType;
import org.compiere.model.MEXMEClaimPayment;
import org.compiere.model.MEXMEClaimPaymentH;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEDocType;
import org.compiere.model.MEXMEPacienteAseg;
import org.compiere.model.MEXMEPayment;
import org.compiere.model.MInvoice;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_C_AllocationHdr;
import org.compiere.model.X_C_DocType;
import org.compiere.model.X_C_Invoice;
import org.compiere.model.X_EXME_ClaimPaymentH;
import org.compiere.model.X_EXME_CtaPac;
import org.compiere.process.DocAction;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 * Clase Modelo encargada de procesar el Claim Payment y generar cargos,
 * facturas y asignaciones de estos, asi como procesar el Next Step de Cuentas
 * Paciente apartir de un {@link MEXMEClaimPaymentH} y un listado de
 * {@link IEncounterStep}. <br/>
 * 
 * Clase refactoriza de {@link ClaimPayment} sigue la misma logica pero con
 * optimizacion en el uso variables y codigo
 * 
 * @author Pedro Mendoza
 * 
 */
public class MNextStep {

	private final Properties			ctx;
	private final int					debitMemoId;
	private final int					docType;
	private final Map<Integer, Integer>	mapCtaPacInvoice	= new LinkedHashMap<Integer, Integer>();
	private final MConfigPre			mConfigPre;
	private final MEXMEClaimPaymentH	mexmeClaimPaymentH;
	private final String				trxName;

	/**
	 * 
	 * @param ctx
	 * @param mexmeClaimPaymentH
	 * @param ctaPacSteps
	 * @param trxName
	 */
	private MNextStep(final Properties ctx, final MEXMEClaimPaymentH mexmeClaimPaymentH, final String trxName) {
		this.ctx = ctx;
		this.trxName = trxName;
		this.mexmeClaimPaymentH = mexmeClaimPaymentH;
		mConfigPre = MConfigPre.get(ctx, null);
		docType = MEXMEDocType.getOfName(ctx, "AR Receipt", null);
		// documento nota de debito
		final MDocType[] docs = MEXMEDocType.getOfDocBaseType(mexmeClaimPaymentH.getCtx(), X_C_DocType.DOCBASETYPE_ARDebitMemo);
		// Exista un tipo de documento
		if (docs != null && docs.length > 0) {
			debitMemoId = docs[0].getC_DocType_ID();
		} else {
			debitMemoId = 0;
		}
	}

	/**
	 * completeInvoice
	 */
	private void completeInvoice(final MInvoice invoice) {
		final String docStatus = invoice.completeIt();
//		if (X_C_Invoice.DOCSTATUS_Completed.equals(docStatus)) {
//			invoice.setDocStatus(docStatus);
		
		if (!MInvoice.DOCSTATUS_Invalid.equals(docStatus)) {
			invoice.setDocStatus(X_C_Invoice.DOCSTATUS_Completed);
			invoice.setDocAction(X_C_Invoice.DOCSTATUS_Closed);
			
			if (!invoice.closeIt() || !invoice.save(trxName)) {
				throw new MedsysException();
			}
		} else {
			throw new MedsysException("Not completed");
		}
	}

	/**
	 * Crear un {@link MAllocationHdr} con su respectiva {@link MInvoice}
	 * para {@link MEXMECtaPac} para luego asignarle un
	 * {@link MAllocationLine} por cada {@link MPayment} de parametro o que
	 * aun no tenga un allocationLine y este relacionado al mismo CbPartner del
	 * encabezado y a la CtaPac . Al finalizar guarda el invoice que se genero
	 * para CtaPac en un {@link Map} mapCtaPacInvoice
	 * 
	 */
	private void createAllocationLines(final int EXME_CtaPac_ID, final List<MPayment> ctaPacPayments) {

		if (ctaPacPayments == null || ctaPacPayments.isEmpty()) {
			final MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, EXME_CtaPac_ID, trxName);
			throw new MedsysException("Encounter EXME_CtaPac_ID " + ctaPac.getDocumentNo() + " does not has payments to procced");
		} else {
			// Crear el encabezado de la asignacion de pagos
			final MAllocationHdr mAllocationHdr = new MAllocationHdr(ctx, false, mexmeClaimPaymentH.getDateTrx(), Env.getC_Currency_ID(ctx),
					Msg.translate(ctx, "Claim payment ID") + ": " + mexmeClaimPaymentH.getDocumentNo() + " [u]", trxName);
			mAllocationHdr.setAD_Org_ID(mexmeClaimPaymentH.getAD_Org_ID());
			mAllocationHdr.setEXME_ClaimPaymentH_ID(mexmeClaimPaymentH.getEXME_ClaimPaymentH_ID());
			mAllocationHdr.setEXME_CtaPac_ID(EXME_CtaPac_ID);

			if (mAllocationHdr.save(trxName)) {
				final MInvoice mInvoice;
				final int firstPaymentInvoiceID = ctaPacPayments.get(0).getC_Invoice_ID();
				// Buscar la Factura/nota de debito
				if (firstPaymentInvoiceID > 0) {
					mInvoice = new MInvoice(ctx, firstPaymentInvoiceID, trxName);
				} else {
					mInvoice = getAvailableInvoice(EXME_CtaPac_ID);
				}

				// Relacionar los pagos con la factura
				for (final MPayment mPayment : ctaPacPayments) {
					mAllocationHdr.createAllocationLine(ctx, mPayment, mInvoice, trxName);
				}

				// Relacionar los anticipos de la cuenta paciente para el socio
				// con la factura que aun no tienen un allocation line
				final List<MPayment> lstPays = MEXMEPayment.getPaymentCtaPac(ctx, EXME_CtaPac_ID, mexmeClaimPaymentH.getC_BPartner_ID(), trxName);
				for (final MPayment mPayment : lstPays) {
					mAllocationHdr.createAllocationLine(ctx, mPayment, mInvoice, trxName);
				}

				// Actualizar estatus 
				mAllocationHdr.processIt(DocAction.ACTION_Complete);
				String status = mAllocationHdr.completeIt();
				mAllocationHdr.setDocStatus(status);
				
				if (!X_C_AllocationHdr.DOCSTATUS_Completed.equals(mAllocationHdr.getDocStatus()) 
						|| !mAllocationHdr.save(trxName)){
					throw new MedsysException();
				}
				
				mapCtaPacInvoice.put(EXME_CtaPac_ID, mInvoice.getC_Invoice_ID());
			} else {
				throw new MedsysException();
			}
		}
	}

	/**
	 * Create allocation lines for next step
	 * 
	 * @param mInvoice
	 * @return
	 */
	private void createAllocationNextStep(final MInvoice mInvoice) {
		final List<MPayment> lstPaymentHdr = MEXMEPayment.getPaymentCtaPac(ctx, mInvoice.getEXME_CtaPac_ID(), mInvoice.getC_BPartner_ID(), trxName);
		// si existen anticipos
		if (!lstPaymentHdr.isEmpty()) {
			final int exmeCtaPacID = mInvoice.getEXME_CtaPac_ID();
			final MAllocationHdr mAllocationHdr = new MAllocationHdr(ctx, false, mInvoice.getDateInvoiced(), Env.getC_Currency_ID(ctx),
					Msg.translate(ctx, "Next") + " " + Msg.translate(ctx, "Payment ID") + ": " + mInvoice.getDocumentNo() + " [v]", trxName);
			mAllocationHdr.setAD_Org_ID(mInvoice.getAD_Org_ID());
			mAllocationHdr.setEXME_CtaPac_ID(exmeCtaPacID);
			if (!mAllocationHdr.save()) {
				throw new MedsysException();
			}

			// Total de pagos anticipados
			// BigDecimal totalPayment = Env.ZERO;
			for (final MPayment mPayment : lstPaymentHdr) {
				mAllocationHdr.createAllocationLine(ctx, mPayment, mInvoice, trxName);
			}

			// Existen pagos que se deben saldar por que ya no los paga el
			// paciente ? //#Card 144
			// En la extension cero el socio debe ser el paciente
			// BPartner->Standard
			if (MEXMECtaPacExt.getExtCero(ctx, exmeCtaPacID, mInvoice.get_TrxName()).getC_BPartner_ID() == mInvoice.getC_BPartner_ID()) {
				//
				final List<MCharge> mCharges = MCharge.getChargesForInvoice(ctx, mInvoice.getC_Invoice_ID(), trxName);
				for (int i = 0; i < mCharges.size(); i++) {
					final MCharge mCharge = mCharges.get(i);
					/*
					 * mCharge.getExcepcion() siempre sera P ya que no se modifica su valor por defecto
					 * Campo sin uso, "por si las dudas"....
					 */
					if (mCharge.getExcepcion().contains("P")) {
						// Crear el allocation con los Payment
						final MPayment mPayment = MPayment.initFromCharge(ctx, mCharge, mInvoice, docType);
						mPayment.setReciboNo("ticket " + i + " " + exmeCtaPacID);
						mPayment.setC_BankAccount_ID(mConfigPre.getC_BankAccount_ID());

						if (mPayment.save(trxName)) {
							mAllocationHdr.createAllocationLine(ctx, mPayment, mInvoice, trxName);
						} else {
							throw new MedsysException();
						}
					}
				}
			}

			// Actualizar estatus 
			mAllocationHdr.processIt(DocAction.ACTION_Complete);
			String status = mAllocationHdr.completeIt();
			mAllocationHdr.setDocStatus(status);
			
			if (!X_C_AllocationHdr.DOCSTATUS_Completed.equals(mAllocationHdr.getDocStatus()) 
					|| !mAllocationHdr.save(trxName)){
				throw new MedsysException();
			}
		}
	}

	/**
	 * create invoice
	 * 
	 * @param lstNextStep
	 * @return
	 */
	private void executeNextStep(final List<IEncounterStep> lstNextStep) {
		for (final IEncounterStep iEncounterStep : lstNextStep) {
			final MEXMECtaPac mexmeCtaPac = new MEXMECtaPac(mexmeClaimPaymentH.getCtx(), iEncounterStep.getEXME_CtaPac_ID(), trxName);
			final MEXMECtaPacExt mexmeCtaPacExt = new MEXMECtaPacExt(mexmeClaimPaymentH.getCtx(), iEncounterStep.getEXME_CtaPacExt_ID(), trxName);
			final String action = iEncounterStep.getNextStep();
			final String status = iEncounterStep.getNextStatus();
			// Factura anteriormente saldada
			final int mInvoiceID = mapCtaPacInvoice.get(mexmeCtaPac.getEXME_CtaPac_ID());

			// Cambia los estatus de la cuenta
			if (mexmeClaimPaymentH.getBillingType().equals(X_EXME_ClaimPaymentH.BILLINGTYPE_Technical)) {
				mexmeCtaPac.setInstitutionalStep(action);
				mexmeCtaPac.setInstitutionalStatus(status);
				mexmeCtaPacExt.setInstitutionalStep(action);
				mexmeCtaPacExt.setInstitutionalStatus(status);
			} else {
				mexmeCtaPac.setProfessionalStep(action);
				mexmeCtaPac.setProfessionalStatus(status);
				mexmeCtaPacExt.setProfessionalStep(action);
				mexmeCtaPacExt.setProfessionalStatus(status);
			}

			if (!mexmeCtaPac.save()) {
				throw new MedsysException();
			}
			if (!mexmeCtaPacExt.save()) {
				throw new MedsysException();
			}
			// si no se ejecuta la next accion no se cambia el estatus
			if (nextAction(mexmeCtaPac, action, mInvoiceID)) {
				if (!mexmeCtaPac.save()) {// TODO checar si hace cambios
					throw new MedsysException();
				}
			}
		}
	}

	/**
	 * Genera los C_Payment apartir de los ClaimPayment del encabezado y los
	 * agrupa por EXME_CtaPac_ID
	 * 
	 * @return Mapa con los C_Payments agrupados por EXME_CtaPac_ID
	 */
	private Map<Integer, List<MPayment>> generatePaymentsFromClaimPaymentH() {
		final Map<Integer, List<MPayment>> groupedPayments = new LinkedHashMap<Integer, List<MPayment>>();

		// Se obtienen todos los EXME_ClaimPayment de encabezado ordenados por
		// EXME_CtaPac_ID

		final List<MEXMEClaimPayment> claimPayments = new Query(ctx, I_EXME_ClaimPayment.Table_Name, " EXME_ClaimPaymentH_ID = ? ", null)
				.setParameters(new Object[] { mexmeClaimPaymentH.getEXME_ClaimPaymentH_ID() })//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" EXME_CtaPac_ID desc ")//
				.list();

		if (!claimPayments.isEmpty()) {
			MEXMECtaPac currentCtaPacID = new MEXMECtaPac(ctx, claimPayments.get(0).getEXME_CtaPac_ID(), trxName);
			List<MPayment> mPayments = new ArrayList<MPayment>();
			// Se recorre la lista de EXME_ClaimPayments y se van agrupando por
			// EXME_CtaPac_ID
			for (int i = 0; i < claimPayments.size(); i++) {
				final MEXMEClaimPayment mexmeClaimPayment = claimPayments.get(i);
				final MPayment payment = MPayment.initFromEXMEClaimPayment(ctx, mexmeClaimPayment, mexmeClaimPaymentH, docType);
				payment.setReciboNo("ticket " + i + " " + mexmeClaimPayment.getEXME_CtaPac_ID());
				payment.setC_BankAccount_ID(mConfigPre.getC_BankAccount_ID());
				if (currentCtaPacID.getEXME_CtaPac_ID() != payment.getEXME_CtaPac_ID()) {
					groupedPayments.put(currentCtaPacID.getEXME_CtaPac_ID(), mPayments);
					currentCtaPacID = new MEXMECtaPac(ctx, payment.getEXME_CtaPac_ID(), trxName);
					mPayments = new ArrayList<MPayment>();
				}
				payment.setEXME_Paciente_ID(currentCtaPacID.getEXME_Paciente_ID());
				if (!payment.save(trxName)) {
					throw new MedsysException();
				}
				mPayments.add(payment);
			}
			groupedPayments.put(currentCtaPacID.getEXME_CtaPac_ID(), mPayments);
		}
		return groupedPayments;
	}

	/**
	 * Busca la factura/nota de debito correspondiente, si no existe genera una
	 * nueva apartir de los datos de {@link MEXMEClaimPaymentH}
	 * 
	 * @param EXME_CtaPac_ID
	 * @returna MInvoice
	 * @see {@link ClaimPayment#findInvoice}
	 */
	private MInvoice getAvailableInvoice(final int EXME_CtaPac_ID) {
		final int mInvoiceID = MInvoice.getByBPartner(
				ctx, mexmeClaimPaymentH.getBillingType(), mexmeClaimPaymentH.getC_BPartner_ID(), EXME_CtaPac_ID, trxName);
		MInvoice mInvoice = new MInvoice(ctx, mInvoiceID < 0 ? 0 : mInvoiceID, trxName);
		if (mInvoice.is_new()) {
			mInvoice.setBackoffice(false);
			mInvoice.setC_BPartner_ID(mexmeClaimPaymentH.getC_BPartner_ID());
			mInvoice.setC_DocType_ID(debitMemoId);
			mInvoice.setC_DocTypeTarget_ID(debitMemoId);
			mInvoice.setEXME_CtaPac_ID(EXME_CtaPac_ID);
			mInvoice.setConfType(mexmeClaimPaymentH.getBillingType());
			mInvoice.setDateOrdered(DB.convert(ctx, new Date()));
			mInvoice.setLocations(mexmeClaimPaymentH.getC_BPartner_ID());
			
			if (mInvoice.save()) {
				final List<MEXMEClaimPayment> lstPayments = mexmeClaimPaymentH.getClaimPaymentsForAccionCtaPac(ctx, EXME_CtaPac_ID, true, trxName);
				mInvoice.toInvoiceLines(ctx, lstPayments, trxName);
				completeInvoice(mInvoice);
			} else {
				throw new MedsysException();
			}
		}
		return mInvoice;
	}


	/**
	 * Obtiene el socio de negocio para la siguiente accion dependiendo del
	 * BillingType del ClaimPaymentH, el nextAction y de la CtaPac. En caso de
	 * que no encuentre ninguna se trea el por defecto del paciente
	 * 
	 * @param nextAction
	 * @param mexmeCtaPac
	 * @return
	 */
	private int getPartnerForNextAction(final String nextAction, final MEXMECtaPac mexmeCtaPac) {
		MEXMEPacienteAseg mPacienteAseg = null;
		final int exmePacienteID = mexmeCtaPac.getEXME_Paciente_ID();
		final int exmeCtaPacID = mexmeCtaPac.getEXME_CtaPac_ID();
		if (X_EXME_ClaimPaymentH.BILLINGTYPE_Technical.equals(mexmeClaimPaymentH.getBillingType())) {
			if (X_EXME_CtaPac.INSTITUTIONALSTEP_FirstInsurance.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 1, Constantes.INST);
			} else if (X_EXME_CtaPac.INSTITUTIONALSTEP_SecondInsurance.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 2, Constantes.INST);
			} else if (X_EXME_CtaPac.INSTITUTIONALSTEP_ThirdInsurance.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 3, Constantes.INST);
			} else if (X_EXME_CtaPac.INSTITUTIONALSTEP_OtherInsurance.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 4, Constantes.INST);
			} else if (X_EXME_CtaPac.INSTITUTIONALSTEP_SelftPay.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 0, Constantes.INST);
			}
		} else {
			if (X_EXME_CtaPac.PROFESSIONALSTEP_FirstInsurance.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 1, Constantes.PROF);
			} else if (X_EXME_CtaPac.PROFESSIONALSTEP_SecondInsurance.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 2, Constantes.PROF);
			} else if (X_EXME_CtaPac.PROFESSIONALSTEP_ThirdInsurance.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 3, Constantes.PROF);
			} else if (X_EXME_CtaPac.PROFESSIONALSTEP_OtherInsurance.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 4, Constantes.PROF);
			} else if (X_EXME_CtaPac.PROFESSIONALSTEP_SelftPay.equals(nextAction)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, exmePacienteID, exmeCtaPacID, 0, Constantes.PROF);
			}
		}
		return mPacienteAseg != null ? mPacienteAseg.getC_BPartner_ID() : new org.compiere.model.MEXMEPaciente(ctx, exmePacienteID, null)
				.getC_BPartner_ID();
	}

	/**
	 * 
	 * @param ctapac
	 * @param C_BPartner_ID
	 * @param invoicehdr
	 * @param billingType
	 * @param all
	 * @return
	 */
	private boolean nextAction(final MEXMECtaPac ctapac, final int C_BPartner_ID, final MInvoice invoicehdr, final boolean all) {
		final int exmeCtaPacID = ctapac.getEXME_CtaPac_ID();

		// Factura del nuevo bpartner (nextstep)
		final int nextInvoiceID = MInvoice
				.getByBPartner(ctx, mexmeClaimPaymentH.getBillingType(), C_BPartner_ID, ctapac.getEXME_CtaPac_ID(), trxName);
		if (nextInvoiceID <= 0) {

			// Ajustes capturados en esta transaccion que pasan al sig. pagador
			final List<MEXMEClaimPayment> lstPayments = mexmeClaimPaymentH.getClaimPaymentsForAccionCtaPac(ctx, exmeCtaPacID, all, trxName);

			getAvailableInvoice(ctapac.getEXME_CtaPac_ID());
			// Nueva nota de cargo
			final MInvoice nextInvoice = new MInvoice(ctapac.getCtx(), 0, trxName);
			PO.copyValues(invoicehdr, nextInvoice);
			nextInvoice.setBackoffice(false);
			nextInvoice.setC_BPartner_ID(C_BPartner_ID);
			nextInvoice.setC_DocType_ID(debitMemoId);
			nextInvoice.setC_DocTypeTarget_ID(debitMemoId);
			nextInvoice.setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
			nextInvoice.setConfType(mexmeClaimPaymentH.getBillingType());
			if (!nextInvoice.save()) {
				throw new MedsysException();
			}

			// Si no hay mas que saldar(ajustes) aun cuando se haya elegido un
			// sig. paso
			if (lstPayments != null && !lstPayments.isEmpty()) {
				nextInvoice.toInvoiceLines(ctx, lstPayments, trxName);
				// Siguiente estatus para el documento factura hasta cerrar
				completeInvoice(nextInvoice);
				// Se marca que no hay anticipos para la factura generada
				// y saldar aquellos que no vaya a pagar el paciente
				createAllocationNextStep(nextInvoice);
			}

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Ejecuta la siguiente accion de la CtaPac con los datos de
	 * ExmeClaimPaymentH
	 * 
	 * @param mexmeCtaPac
	 * @param action
	 * @param cInvoiceID
	 * @return true: se cumple la accion, false no se cumple la accion
	 */
	private boolean nextAction(final MEXMECtaPac mexmeCtaPac, final String action, final int cInvoiceID) {
		final MInvoice nextInvoice = new MInvoice(ctx, cInvoiceID, trxName);
		final MDocType documentType = MDocType.get(ctx, debitMemoId);
		if (!MPeriod.isOpen(ctx, nextInvoice.getDateAcct(), documentType.getDocBaseType(), Env.getAD_Org_ID(ctx))) {
			throw new MedsysException(Utilerias.getAppMsg(ctx, "msj.error.postPeriod"));
		}
		final int nextActionPartnerID = getPartnerForNextAction(action, mexmeCtaPac);
		return nextAction(mexmeCtaPac, nextActionPartnerID, nextInvoice, false);
	}

	/**
	 * Metodo maestro encargado del procesamiento de claim payments y next step.
	 * Si el proceso termina correctamente se le asignara el status de Closed al
	 * enacebazado
	 * 
	 * @param ctaPacSteps
	 */
	private void process(final List<IEncounterStep> ctaPacSteps) {
		final Map<Integer, List<MPayment>> mapCtaPacPayments = generatePaymentsFromClaimPaymentH();
		for (final Entry<Integer, List<MPayment>> entry : mapCtaPacPayments.entrySet()) {
			createAllocationLines(entry.getKey(), entry.getValue());
		}
		executeNextStep(ctaPacSteps);
		mexmeClaimPaymentH.setStatus(X_C_Invoice.DOCSTATUS_Closed);
		mexmeClaimPaymentH.setPostedDate(new Timestamp(System.currentTimeMillis()));
		if (!mexmeClaimPaymentH.save(trxName)) {
			throw new MedsysException();
		}
	}

	/**
	 * Interface utilizada para indicar los Step y Status actuales y siguientes
	 * de una Cuenta Paciente
	 * 
	 * @author pedro
	 * 
	 */
	public interface IEncounterStep {
		public String getCurrentStatus();

		public String getCurrentStep();

		public int getEXME_CtaPac_ID();

		public String getNextStatus();

		public String getNextStep();
		
		public int getEXME_CtaPacExt_ID();
	}

	/**
	 * Metodo encargado de realizar la ejecucion de pagos de todos los claim
	 * payment relacionados al encabezado indicado, asi como sus facturas y
	 * asignaciones. Este metodo tambien es el encargado de procesar el Next
	 * Step de las Cuentas Paciente
	 * 
	 * @param ctx
	 *            Contexto
	 * @param mexmeClaimPaymentH
	 *            Encabezado de Claim Payments. Su status debe de estar
	 *            "InProgress" para ser ejecutado
	 *            {@link X_C_Invoice#DOCSTATUS_InProgress}
	 * @param ctaPacSteps
	 *            Lista con la informacion de los siguientes pasos a ejecutar
	 *            por cuenta paciente
	 * @param trxName
	 *            Nombre de la transacccion
	 */
	public static void process(final MEXMEClaimPaymentH mexmeClaimPaymentH, final List<IEncounterStep> ctaPacSteps,
			final String trxName) {
		if (!X_C_Invoice.DOCSTATUS_InProgress.equals(mexmeClaimPaymentH.getStatus())) {
			throw new IllegalArgumentException("The claim payment header status is not valid. It's need to be In Progress");
		}
		final MNextStep nsController = new MNextStep(mexmeClaimPaymentH.getCtx(), mexmeClaimPaymentH, trxName);
		nsController.process(ctaPacSteps);
	}
}
