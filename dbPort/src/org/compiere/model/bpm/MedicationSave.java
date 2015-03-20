package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPMedication;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMEVacuna;
import org.compiere.model.MInOut;
import org.compiere.model.MPlanMed;
import org.compiere.model.MPlanMedLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.ModelError;
import org.compiere.model.ServicioView;
import org.compiere.model.MInOut.ProcessesCharges;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.MTranslation;

import com.ecaresoft.util.ErrorList;

/**
 * Guarda las prescripciones de medicamentos generando el expediente e invocando
 * la creacion del cargo
 * 
 * @author Expert
 * 
 */
public class MedicationSave extends MBPMedication {
	/** Static Logger */
	private static CLogger log = CLogger.getCLogger(MedicationSave.class);
	/** cuenta paciente */
	private transient final MEXMECtaPac mCtaPac;
	/** listado de productos a procesar */
	private transient List<ServicioView> lstInsert;
	/** expediente */
	private transient MEXMEActPacienteIndH actPacIndH = null;

	/**
	 * Constructor
	 * 
	 * @param pCtx
	 * @param objCtaPac
	 * @param warehouseId
	 */
	public MedicationSave(final Properties pCtx, final MEXMECtaPac objCtaPac,
			final String trxName) {
		super(pCtx, trxName);
		this.mCtaPac = objCtaPac;
	}

	/**
	 * AdmonView Se genera el expediente a partir de una plan medico y crea el
	 * cargo a la cuenta paciente
	 * 
	 * @param plan
	 *            Plan
	 * @param line
	 *            Aplicacion
	 * @return "CO" cuando el expediente se ha cerado correctamente
	 */
	public MEXMEActPacienteIndH insertActPacIndH(final MPlanMed plan,
			final MPlanMedLine line) {
		return insertActPacIndH(plan, line, true);
	}
	
	/**
	 * AdmonView Se genera el expediente a partir de una plan medico y crea el
	 * cargo a la cuenta paciente. Si el cargo es en administracion de medicamentos 
	 * y no cuenta con la autorizacion para hacer el cargo este no se genera.
	 * 
	 * @param plan
	 *            Plan
	 * @param line
	 *            Aplicacion
	 * @param isNursingCharges
	 *            Mantenimiento Mejoras opcion Cargos en Administracion de Medicamentos
	 * @return "CO" cuando el expediente se ha cerado correctamente
	 */
	public MEXMEActPacienteIndH insertActPacIndH(final MPlanMed plan,
			final MPlanMedLine line, boolean isNursingCharges) {
		log.log(Level.INFO, "MedicationSave.insertActPacIndH");
		
		if (plan == null || line == null) {
			log.log(Level.INFO, "if(plan == null || line == null)");
			
		} else {
			
			setDatePromised(Env.getCurrentDate());
			// Crear las lineas como objetos ServicioView
			lstInsert = MBPMedication.createServicioView(ctx, plan, line);
			
			// Insert ActPacienteIndH
			if (insertActPacIndH()) {
				
				// Actualizar estatus PlanMed
				updatePlan(plan, line, actPacIndH.getEXME_ActPacienteIndH_ID());
				
				if(isNursingCharges){
					// Insert Charge
					final List<MCtaPacDet> charge = createCharge(plan, line, isNursingCharges, trxName);//20_eMAR__4a23599c-34fc-4757-996b-d18ad0d0d939

					// Create shipment
					if(charge!=null && !charge.isEmpty() &&MEXMEMejoras.get(ctx).isControlExistencias()){
						if(physicalInventory(plan, charge, trxName)){// Validar si es necesario un ajuste de inventario, si es asi se crea en este mismo punto
//							updateShipment(plan, line, charge, trxName);// -stock
						}
					}

					// Actualizar estatus ActPacienteIndH
					updateStatus(charge!=null, line);
				} else {
					updateStatus(true, line);
				}
			}
		}
		return getActPacIndH();
	}
	
	/**
	 * Inventario fisico en caso de no tener existencias en almacen
	 * @param plan
	 * @param line
	 * @param charge
	 * @return
	 */
	private boolean physicalInventory(final MPlanMed plan, final List<MCtaPacDet> lstCharge, final String trxName) {
		log.info("MedicationSave.physicalInventory");
		boolean success = !lstCharge.isEmpty();
		final ErrorList errorList = new ErrorList();
		final List<Inventariado> lstSinExistencia = new ArrayList<Inventariado>();
		plan.getCtaPac().set_TrxName(trxName);
		for (final MCtaPacDet charge: lstCharge) {
//			final Inventory mInventory = new Inventory(ctx,plan.getEXME_CtaPac_ID(),MPlanMed.Table_ID);
//			
//			// cantidad en el almacen en minima 
//			BigDecimal QtyAvailable = MStorage.getQtyAvailable(charge.getM_Warehouse_ID(),
//					charge.getM_Product_ID(), 
//					charge.getM_AttributeSetInstance_ID(), 
//					trxName);
//
//			if (QtyAvailable == null) {
//				QtyAvailable = Env.ZERO;
//			}
//
//			// Revisa si es suficiente el stock
//			if (charge.getQtyDelivered().compareTo(QtyAvailable) > 0) {
//				final Inventariado mInventariado = new Inventariado(ctx,charge/*,plan.getCtaPac(),trxName*/);
//				mInventariado.fill();
//				lstSinExistencia.add(mInventariado);
//			}
//
//			if (lstSinExistencia != null && !lstSinExistencia.isEmpty()) {
//				success = mInventory.ajusteInventario(lstSinExistencia, charge.getM_Warehouse_ID(),
//						trxName);
//			}// 
			
			
			// Salida de inventario
			if (charge.getM_Product_ID() > 0 && charge.getProduct().isProduct()) {
				final Inventariado lInv = MInOut.addLineInventory(errorList, charge, null);
				if(lInv==null) {
					if(!errorList.isEmpty()){
						break;
					}
				}  else {
					lInv.fill();
					lstSinExistencia.add(lInv);
				}
			}
		}
		if(!lstSinExistencia.isEmpty())
			MInOut.updateStock(lstSinExistencia, errorList, plan.getCtaPac(), ProcessesCharges.ADMONDEMEDICAMENTO, false);
		success = errorList.isEmpty();
		return success;
	}

	/**
	 * Generar cargos a la cuenta paciente
	 * desde un plan medico
	 * @param plan: Plan medico
	 * @param line: Medicamentos
	 * @param isNursingCharges: Lugar de origen del cargo
	 * @return obj MCtaPacDet
	 */
	private List<MCtaPacDet> createCharge(final MPlanMed plan,
			final MPlanMedLine line, final boolean isNursingCharges, final String trxName){
		log.info("MedicationSave.createCharge");

		// Cargo previo
		List<MCtaPacDet> charge = updateCharge(plan,line);
		// Si no se ha cargado previamente, generar el cargo
		if (isNursingCharges && (charge==null || charge.isEmpty())) {
			final CreateCharge createCharge = new CreateCharge(ctx, actPacIndH);
			createCharge.setPlanMedView(plan, line);
			createCharge.setUpdateInventory(false);
			createCharge.insertActPacIndHCharges(trxName);//Lama: cargos 2014
			charge = createCharge.getCtaPacDet();
			
			if(!createCharge.getErrores().isEmpty())
				log.severe(ModelError.getMsgError(ctx, createCharge.getErrores()));
		}
		return charge;
	}
	
	/**
	 * Si existe el cargo actualiza las referencias
	 * con el expediente medico
	 * @param plan
	 * @param line
	 * @return
	 */
	private List<MCtaPacDet> updateCharge(final MPlanMed plan,final MPlanMedLine line){
		log.info("MedicationSave.updateCharge");
		
		// Validar que no exista un cargo previamente realizado
		final List<MCtaPacDet> lstCargos = charged(line.getEXME_PlanMedLine_ID());
//		if (cargo == null) {
//			cargo = chargedProd(plan.getM_Product_ID());
//		}// Validar este cambio, actualmente no deja cargar dos veces el mismo producto para la misma cuenta paciente...
		for (MCtaPacDet cargo: lstCargos) {
			// Actualizar el cargo con el expediente (Solo existe una linea del header)
			if (cargo!=null && !actPacIndH.getLstActPacInd().isEmpty()) {
				cargo.setEXME_ActPacienteInd_ID(actPacIndH.getLstActPacInd().get(0).getEXME_ActPacienteInd_ID());
				cargo.save();
			}
		}
		
		return lstCargos;
	}
	
//	/**
//	 * Crea la confirmacion del movimiento ocasionado 
//	 * por la prescripcion en el nodo del farmaceutico
//	 * @param plan Encabezado del plan
//	 * @param line Linea de la planeación del medicamento
//	 * @return true: si ha salido todo bien
//	 */
//	private boolean updateConfirm(final MPlanMed plan,final MPlanMedLine line){
//		log.info("MedicationSave.updateConfirm");
//		final AbstractStockTransfer transf = new ConfirmTransferOrder();
//		transf.setInfoParameter(getParameter(plan));
//		return transf.startProcess(ctx, Trx.get(trxName, false));
//	}
	
//	/**
//	 * updateShipment
//	 * @param plan
//	 * @param line
//	 * @return
//	 */
//	private boolean updateShipment(final MPlanMed plan,final MPlanMedLine line, 
//			List<MCtaPacDet> charge, final String trxName ){
//		log.info("MedicationSave.updateCharge");//Lama: cargos 2014
//		final EnterCharge mEnterCharge = new EnterCharge(ctx, -1, -1, plan.getEXME_CtaPac_ID());
////		mEnterCharge.setCtaPac(new MEXMECtaPac(ctx, plan.getEXME_CtaPac_ID(), null));
//		
//		// Busca el cargo previamente hecho
//		if(charge==null || charge.isEmpty()){
//			charge = MCtaPacDet.charged(ctx, plan.getEXME_CtaPac_ID(),
//					line.getEXME_PlanMedLine_ID(), plan.getM_Product_ID(), true, trxName);
//		}
//		// FIXME: Cambiar Inventario --> inout x cada linea
//		return mEnterCharge.inventory(charge, false, trxName);
//	}

	/**
	 * Actualizacion de estatus del expediente
	 * @param update
	 * @param planMedline
	 */
	private void updateStatus(final boolean update, final MPlanMedLine planMedline){
		log.info("MedicationSave.updateStatus");
		// Ticket #0101220. Se completa el documento. Nota> revisado con Twry Sep 26, 2012 (Lama)
		if (update && MPlanMedLine.ESTATUS_Administered.equals(planMedline.getEstatus())) {
			actPacIndH.setDateDelivered(planMedline.getApliedDate());
			actPacIndH.setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Completed);
			actPacIndH.setDocAction(MEXMEActPacienteIndH.DOCACTION_None);
			actPacIndH.setProcessed(true);
			actPacIndH.setIsDelivered(true);
			
			boolean success = actPacIndH.save();
			log.info("MedicationSave.updateStatus= "+success);
		}
	}
	/**
	 * charged X EXME_PlanMedLine_ID
	 * @return
	 */
	public List<MCtaPacDet> charged(final int EXME_PlanMedLine_ID){
		return MCtaPacDet.charged(ctx, actPacIndH.getEXME_CtaPac_ID(), EXME_PlanMedLine_ID, 0, false, trxName);
	}
	
	/**
	 * charged X M_Product_ID
	 * @return
	 */
	public List<MCtaPacDet> chargedProd(final int M_Product_ID){
		return MCtaPacDet.charged(ctx, actPacIndH.getEXME_CtaPac_ID(),0, M_Product_ID, false, trxName);
	}
	
	/**
	 * ApptMedication, NewRx, RxMedication Crea el registro EXME_ActPacienteIndH
	 * y EXME_ActPacienteInd
	 * 
	 * @param trxName
	 * @return
	 */
	public MEXMEActPacienteIndH createActPacIndH(final List<ServicioView> lst,
			final Date pDate, final String pTrxName) {
		trxName = pTrxName;
		setDatePromised(pDate == null ? Env.getCurrentDate() :new Timestamp(pDate.getTime()) );
		lstInsert = lst;
		insertActPacIndH();
		return getActPacIndH();
	}

	/**
	 * AppointmentModel, ProcedureSave Crea la registro en EXME_ActPaciente y
	 * EXME_ActPacienteIndH
	 * 
	 * @param trxName
	 * @return
	 */
	public MEXMEActPacienteIndH createActPacIndH(
			final List<MProduct> pLstProduct, final boolean completa,
			final String pTrxName, final boolean pIsServiceTP) {
		trxName = pTrxName;
		setServiceTP(pIsServiceTP);
		setDatePromised(Env.getCurrentDate());
		lstInsert = MBPMedication.createServicioView(ctx, pLstProduct);
		insertActPacIndH();
		return getActPacIndH();
	}
	
	/**
	 * ProcedureSave Crea la registro en EXME_ActPaciente y EXME_ActPacienteIndH
	 * para una lista de procedimientos
	 * @param trxName
	 * @return
	 */
	public MEXMEActPacienteIndH createActPacIndHProc(
			final List<ServicioView> pLstProduct, final boolean completa,
			final String pTrxName, final boolean pIsServiceTP) {
		trxName = pTrxName;
		setServiceTP(pIsServiceTP);
		setDatePromised(Env.getCurrentDate());
		lstInsert = pLstProduct;
		insertActPacIndH();
		return getActPacIndH();
	}

	/**
	 * Immnunization Crea la registro en EXME_ActPaciente y EXME_ActPacienteIndH
	 * para el registro de la vacuna (producto) aplicada
	 * 
	 * @param trxName
	 *            Nombre de transaccion no es necesaria
	 * @return
	 */
	public MEXMEActPacienteIndH insertActPacIndH(final int specialtyID,
			final MEXMEMedico doctor, final MEXMEVacuna vaccine) {
		setDatePromised(Env.getCurrentDate());
		lstInsert = MBPMedication.createServicioView(ctx, vaccine);

		if (insertActPacIndH()) {
			actPacIndH.setDescription(MTranslation.getTable_Name(ctx,
					MEXMEVacuna.Table_Name, Env.getAD_Language(ctx)));
			actPacIndH.save(trxName);
		}
		return getActPacIndH();
	}

	/**
	 * Insert ActPacienIndH
	 * 
	 * @return
	 */
	private boolean insertActPacIndH() {
		actPacIndH = super.insertActPaciente(mCtaPac, lstInsert);
		return actPacIndH != null;
	}

	/**
	 * Creacion de cargos deacuerdo al expediente
	 * 
	 * @param mActPacienteIndH
	 *            expediente medico
	 * @return "CO" Al haber el hecho el cargo correctamente
	 */
	public String insertCharge(final MEXMEActPacienteIndH mActPacienteIndH) {
		return insertCharge(mActPacienteIndH, null);
	}

	/**
	 * Creacion de cargos deacuerdo al expediente
	 * 
	 * @param mActPacienteIndH
	 *            expediente medico
	 * @param pPlanMed
	 *            MPlanMed
	 * @return "CO" Al haber el hecho el cargo correctamente
	 */
	public String insertCharge(final MEXMEActPacienteIndH mActPacienteIndH,
			final MPlanMed pPlanMed) {
		final CreateCharge createCharge = new CreateCharge(ctx, mActPacienteIndH);
		createCharge.setPlanMedView(pPlanMed, null);
		final String success = createCharge.insertActPacIndHCharges(trxName);//Lama: cargos 2014
		if(!createCharge.getErrores().isEmpty()){
			lstErrors.addAll(createCharge.getErrores());
			createCharge.getErrores().clear();
		}
		return success;
	}
	
//	/**
//	 * Creacion de cargos deacuerdo al expediente SOLO NIMBO
//	 *  @param mActPacienteIndH
//	 *            expediente medico
//	 * @return"CO" Al haber el hecho el cargo correctamente
//	 */
//	public String insertChargeNimbo(final MEXMEActPacienteIndH mActPacienteIndH) {
//		final CreateCharge createCharge = new CreateCharge(ctx, mActPacienteIndH);
//		return createCharge.createNimboCharges(ctx, mActPacienteIndH, trxName);
//	}
	
	/**
	 * A partir de la actividad paciente (MEXMEActPacienteIndH), se crean los
	 * cargos a la cuenta paciente Es necesario el objeto MEXMEActPacienteIndH
	 * del constructor El nombre de la transaccion y el Contexto del
	 * constructor.
	 * 
	 * Clases: MEXMEActPacienteIndH, MedicationSave, ServicesDelivered
	 * 
	 * @param trxName
	 *            Nombre de transaccion
	 * @return null: No cumple las validaciones o no se genero el cargo
	 */
	public String insertChargeNimbo(final MEXMEActPacienteIndH pActPacIndH) {
		if (pActPacIndH == null || trxName == null) {
			log.log(Level.SEVERE, "if (actPacIndH == null || trxName == null)");
			return null;
		} else {
			return pActPacIndH.createNimboCharges(trxName);
		} //Lama: cargos 2014
	}

	/**
	 * 
	 * @return
	 */
	private MEXMEActPacienteIndH getActPacIndH() {
		if (actPacIndH == null) {
			log.log(Level.INFO, "if(actPacIndH==null)");
		}
		return actPacIndH;
	}

	/**
	 * Errores
	 * 
	 * @return
	 */
	public List<ModelError> getErrores() {
		return this.lstErrors;
	}

	/**
	 * AdmonView
	 * 
	 * @param trxName
	 */
	public void setTrxName(final String trxName) {
		this.trxName = trxName;
	}
/*********************************************************************************************/
	/**
	 * Test
	 * 
	 * @param pCtx
	 * @param objCtaPac
	 * @param trxName
	 * @param plan
	 * @param line
	 * @return
	 */
	public static boolean medicationSaveTest(final Properties pCtx,
			final MEXMECtaPac objCtaPac, final String trxName,
			final MPlanMed plan, final MPlanMedLine line) {
		final MedicationSave mMedicationSave = new MedicationSave(pCtx, objCtaPac,
				trxName);
		final MEXMEActPacienteIndH encab = mMedicationSave.insertActPacIndH(plan,
				line);
		return mMedicationSave.insertCharge(encab, plan) != null;
	}

	/**
	 * Test
	 * 
	 * @param pCtx
	 * @param objCtaPac
	 * @param trxName
	 * @param lst
	 * @return
	 */
	public static boolean medicationSaveTest(final Properties pCtx,
			final MEXMECtaPac objCtaPac, final String trxName,
			final List<ServicioView> lst) {
		final MedicationSave mMedicationSave = new MedicationSave(pCtx, objCtaPac,
				trxName);
		return mMedicationSave.createActPacIndH(lst, null, trxName) != null;
	}

	/**
	 * Test
	 * 
	 * @param pCtx
	 * @param objCtaPac
	 * @param trxName
	 * @param pLstProduct
	 * @param completa
	 * @param pTrxName
	 * @param pIsServiceTP
	 * @return
	 */
	public static boolean medicationSaveTest(final Properties pCtx,
			final MEXMECtaPac objCtaPac, final String trxName,
			final List<MProduct> pLstProduct, final boolean completa,
			final boolean pIsServiceTP) {

		final MedicationSave mMedicationSave = new MedicationSave(pCtx, objCtaPac,
				trxName);
		return mMedicationSave.createActPacIndH(pLstProduct, completa,
				trxName, pIsServiceTP) != null;
	}

	/**
	 * Test
	 * 
	 * @param pCtx
	 * @param objCtaPac
	 * @param trxName
	 * @param specialtyID
	 * @param doctor
	 * @param vaccine
	 * @return
	 */
	public static boolean medicationSaveTest(final Properties pCtx,
			final MEXMECtaPac objCtaPac, final String trxName,
			final int specialtyID, final MEXMEMedico doctor,
			final MEXMEVacuna vaccine) {
		final MedicationSave mMedicationSave = new MedicationSave(pCtx, objCtaPac,
				trxName);
		return mMedicationSave.insertActPacIndH(specialtyID, doctor, vaccine) != null;
	}
}