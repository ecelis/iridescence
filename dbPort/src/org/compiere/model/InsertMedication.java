package org.compiere.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Insert en ActPacienteIndH
 * 
 * @author twry
 * 
 */
public class InsertMedication {
	/** Static Logger */
	protected static CLogger sLog = CLogger.getCLogger(InsertMedication.class);
	/** contexto */
	protected transient Properties ctx;
	/** Nombre de transaccion */
	protected transient String trxName;
	/** prealta */
	protected transient boolean preAlta = false;
	/** Listado de errores */
	protected transient List<ModelError> lstErrors = new ArrayList<ModelError>();
	/** Listado de lineas creadas */
	protected transient final List<MEXMEActPacienteInd> lstActPacInd = new ArrayList<MEXMEActPacienteInd>();
	/** Mensaje de error */
	private static final String NO_INSERT_EJEC = "error.citas.noInsertEjec";
	/** estacion de login */
	private transient final MEXMEEstServ estServLogeo;
	/** confg mejora */
	private transient final MEXMEMejoras mejoras;
	/** */
	private transient final String cantTomar;
	/** */
	private transient final String vecesDia;
	/** */
	private transient final String numDias;
	/** exme_actpacienteindh.datepromised (Lama) */
	private transient Timestamp datePromised;
	/** Bandera que define si es un servicio (Lama) */
	private transient boolean isServiceTP = false;
	/**
	 * Constructor
	 */
	public InsertMedication(final Properties pCtx, final String pTrxName) {
		super();
		this.ctx = pCtx;
		this.trxName = pTrxName;

		estServLogeo = new MEXMEEstServ(ctx, Env.getEXME_EstServ_ID(ctx), null);
		mejoras = MEXMEMejoras.get(ctx);

		cantTomar = Utilerias.getMessage(ctx, null, "citas.indica.cantTomar");
		vecesDia = Utilerias.getMessage(ctx, null, "citas.indica.vecesDiaSA");
		numDias = Utilerias.getMessage(ctx, null, "citas.indica.numDiasSA");

	}

	/**
	 * Inserta la solicitudes de productos para el almacen de configuracion
	 * (mejoras.getM_Warehouse_ID())
	 * 
	 */

	/**
	 * 
	 * @param ctx
	 * @param paciente
	 * @param actPacienteID
	 * @param prioridadInd
	 * @param medicoID
	 * @param medName
	 * @param ctaPacID
	 * @param farmaciaId
	 * @param fechaValida
	 * @param lstDetalle
	 *            : siempre debe existir
	 * @param trxName
	 * @return null si el MEXMEActPacienteIndH no se creo
	 * @throws Exception
	 */
//	protected MEXMEActPacienteIndH insertarMovimientosMed(
//			final MEXMECtaPac ctapac, 
//			final int actPacienteID,
//			final String prioridadInd, //final int medicoID,
//			//final String medName, 
////			final int mWarehouseID,
//			//final int ctaPacID, 
//			final int farmaciaId,
//			final Timestamp fechaValida, 
//			final List<ServicioView> lstDetalle
//			//,final boolean isServiceTP
//			) throws Exception {
//
//		// Crea las lineas del encabezado
//		final MEXMEActPacienteIndH actPacIndH = insertActPacientInd(ctx,
//				ctapac.getPaciente(), actPacienteID, //0,//
//				prioridadInd, ctapac.getMedico().getEXME_Medico_ID(), 
//				ctapac.getMedico().getNombre_Med(),
//				ctapac.getEXME_CtaPac_ID(), farmaciaId, fechaValida,
//				Constantes.RECETA, lstDetalle, trxName);
//
//		if (actPacIndH == null) {
//			sLog.log(Level.SEVERE, "if(actPacIndH==null) ");
//		} else {
//			actPacIndH.setPreAlta(this.preAlta);
//			actPacIndH.setLstActPacInd(lstActPacInd);
//			actPacIndH.save();
//		}
//
//		return actPacIndH;
//	}

	/**
	 * Inserta la solicitudes de productos para el almacen de configuracion
	 * (mejoras.getM_Warehouse_ID())
	 * 
	 * @param ctx
	 * @param paciente
	 * @param surFarm
	 * @param EXME_ActPaciente_ID
	 * @param prioridadInd
	 * @param EXME_Medico_ID
	 * @param medName
	 * @param fechaform
	 * @param horaCadena
	 * @param EXME_CtaPac_ID
	 * @param planMed
	 * @param farmaciaId
	 * @param fechaValida
	 * @param trxName
	 * @return EXME_ActPacienteIndH_ID
	 * @throws Exception
	 */
	// protected MEXMEActPacienteIndH insertarMovimientosImm(Properties ctx,
	// MEXMEPaciente paciente, int EXME_ActPaciente_ID,
	// int EXME_Medico_ID, String medName, int EXME_CtaPac_ID,
	// ServicioView serv, String trxName) throws Exception {
	//
	// MEXMEMejoras mejoras = MEXMEMejoras.get(ctx);
	// List<ServicioView> list = new ArrayList<ServicioView>();
	// list.add(serv);
	//
	// // Se crea el encabezado de la indicacion medica los detalles
	// MEXMEActPacienteIndH actPacIndH = insertActPacientInd(ctx, paciente,
	// EXME_ActPaciente_ID, mejoras.getM_Warehouse_ID(), "5",
	// EXME_Medico_ID, medName, "", "", EXME_CtaPac_ID, 0, null,
	// Constantes.VACUNA, list, trxName);
	//
	// // Crea las lineas del encabezado
	// if (actPacIndH == null) {
	// // TODO Marcar los errores
	// // setLstErrors(getErrors());
	// return null;
	// }
	//
	// // Guardamos el encabezado
	// if (!actPacIndH.save()) {
	// throw new SQLException("error.citas.noInsertEjec");
	// }
	//
	// // Lineas creadas para este header
	// actPacIndH.setLstActPacInd(lstActPacInd);
	// return actPacIndH;
	// }

	/**
	 * insertActPacientIndH
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param paciente
	 *            : Paciente
	 * @param actPacienteID
	 *            : Expediente medico
	 * @param warehouseID
	 *            : Almacen que surtira o aplicara
	 * @param prioridadInd
	 *            : Prioridad medica
	 * @param medicoID
	 *            : Medico que prescribe o solicita
	 * @param medName
	 *            : Nombre del medico
	 * @param ctaPacID
	 *            : Cuenta paciente
	 * @param farmaciaId
	 *            : Farmacia
	 * @param fechaValid
	 *            : Fecha de vigencia
	 * @param tipoDoc
	 *            : Tipo de prescripcion (servicio, medicamento, vacuna)
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return object MEXMEActPacienteIndH
	 * @throws Exception
	 */
	private MEXMEActPacienteIndH insertActPacientIndH(final Properties ctx,
			final MEXMEPaciente paciente, final int actPacienteID,
			final int warehouseID, final String prioridadInd,
			final int medicoID, final String medName,
			// Lama: se quitan parametros ya que agregaron como atributos
			//final String fechaform, final String horaCadena,
			final int ctaPacID, final int farmaciaId,
			final Timestamp fechaValid, final String tipoDoc,
			final String trxName,// final boolean isSerxvTP,
			final ServicioView bean) {

		MEXMEActPacienteIndH mActPacIndH = new MEXMEActPacienteIndH(ctx, 0, trxName);
		final MEXMEActPacienteIndCgo logCgo = new MEXMEActPacienteIndCgo(ctx,
				0, null);
		try {

			insertActPacientIndH(mActPacIndH, ctx, paciente, actPacienteID,
					warehouseID, prioridadInd, medicoID, medName, ctaPacID,
					farmaciaId, fechaValid, tipoDoc, trxName,  bean);

			// la organizacion transaccional de la estacion de servicio de
			// acceso es decir quien esta solicitando
			mActPacIndH.setAD_OrgTrx_ID(MEXMEEstServ.getEstServAlmOrgTrx(ctx,
					Env.getM_Warehouse_ID(ctx)));

			// Date promised -- ya no se requiere, ya que el atributo es Timestamp
//			datos.promisedDate(fechaform, horaCadena);
			if(getDatePromised() == null){
				mActPacIndH.setDatePromised(Env.getCurrentDate());
			} else {
				mActPacIndH.setDatePromised(getDatePromised());
			}

			if (!mActPacIndH.save()) {
				logCgo.setType(MEXMEActPacienteIndCgo.SAVEFALSE);
				logCgo.setErrorMsg("Save");// TODO
				throw new SQLException(NO_INSERT_EJEC);
			}

		} catch (IllegalArgumentException ie) {

			sLog.log(Level.SEVERE, "insertActPacientIndH : ", ie);
			logCgo.setType(MEXMEActPacienteIndCgo.MANDATORY);
			logCgo.setErrorMsg(ie.getMessage());
			mActPacIndH = null;

		} catch (SQLException se) {

			if (logCgo.getErrorMsg() == null) {
				logCgo.setType(MEXMEActPacienteIndCgo.SAVEFALSE);
				logCgo.setErrorMsg(se.getMessage());
			}
			sLog.log(Level.SEVERE, "insertActPacientIndH : ", se);
			mActPacIndH = null;

		} catch (Exception e) {

			sLog.log(Level.SEVERE, "insertActPacientIndH : ", e);
			logCgo.setType(MEXMEActPacienteIndCgo.OTHER);
			logCgo.setErrorMsg(e.getMessage());
			mActPacIndH = null;

		} finally {
			if (logCgo.getType() != null && logCgo != null) {
				logCgo.setEXME_PlanMed_ID(bean == null
						|| bean.getPlanMed() == null ? 0 : bean.getPlanMed()
						.getEXME_PlanMed_ID());
				logCgo.setEXME_PlanMedLine_ID(bean == null
						|| bean.getPMLine() == null ? 0 : bean.getPMLine()
						.getEXME_PlanMedLine_ID());
				logCgo.setClassname(getClass().getName());
				logCgo.save(trxName);
			}
		}

		return mActPacIndH;

	}

	/**
	 * insertActPacientIndH
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param paciente
	 *            : Paciente
	 * @param pActPacienteID
	 *            : Expediente medico
	 * @param pWarehouseID
	 *            : Almacen que surtira o aplicara
	 * @param prioridadInd
	 *            : Prioridad medica
	 * @param pMedicoID
	 *            : Medico que prescribe o solicita
	 * @param medName
	 *            : Nombre del medico
	 * @param pCtaPacID
	 *            : Cuenta paciente
	 * @param farmaciaId
	 *            : Farmacia
	 * @param fechaValid
	 *            : Fecha de vigencia
	 * @param tipoDoc
	 *            : Tipo de prescripcion (servicio, medicamento, vacuna)
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return object MEXMEActPacienteIndH
	 * @throws Exception
	 */
	private void insertActPacientIndH(final MEXMEActPacienteIndH datos,
			final Properties ctx, final MEXMEPaciente paciente,
			final int pActPacienteID, final int pWarehouseID,
			final String prioridadInd, final int pMedicoID,
			final String medName, final int pCtaPacID, final int farmaciaId,
			final Timestamp fechaValid, final String tipoDoc,
			final String trxName, //final boolean isServTP,
			final ServicioView bean) {

		if (datos.get_TrxName() == null) {
			datos.set_TrxName(trxName);
		}

		datos.set_Value(MEXMEActPacienteIndH.COLUMNNAME_M_PriceList_ID,
				datos.getMPriceListID(MEXMEConfigPre.getPriceList(ctx, trxName).getM_PriceList_ID()));

		// asignamos la lista de precios del contexto
		if (datos.getM_PriceList_ID() <= 0) {
			datos.setM_PriceList_ID(MEXMEConfigPre.getPriceList(ctx, trxName).getM_PriceList_ID());
		}
		// la moneda y si incluye impuesto de la lista de precios
		final MPriceList lista = new MPriceList(ctx, datos.getM_PriceList_ID(),
				null);// Lama
		datos.setC_Currency_ID(lista.getC_Currency_ID());
		datos.setIsTaxIncluded(lista.isTaxIncluded());

		// asignamos el tipo de documento 'Receta Medica'
		datos.typeDocument(tipoDoc);
		// SOLO para pruebas
//		datos.setC_DocType_ID(-1);// TODO Comentar esta linea NOSE TE VAYA A
		// PASAR
		datos.setC_DocTypeTarget_ID(datos.getC_DocType_ID());
		datos.setDateAcct(Env.getCurrentDate());

		// act paciente
		datos.setEXME_ActPaciente_ID(pActPacienteID);

		// tipo de dato selecc0ionado
		datos.setIsService(isServiceTP());

		// y el almacen seleccionado
		datos.setM_Warehouse_ID(pWarehouseID);
		datos.setM_Warehouse_Sol_ID(Env.getM_Warehouse_ID(ctx));

		// la prioridad del pedido
		datos.setPriorityRule(prioridadInd);
		datos.setDateOrdered(bean == null || bean.getPMLine() == null ? Env.getCurrentDate() : bean.getPMLine().getProgDate());
		datos.setEXME_Medico_ID(pMedicoID);
		datos.setMedicoNombre(medName);
		datos.setEXME_CtaPac_ID(pCtaPacID);

		if (fechaValid != null) {
			datos.setValidFrom(fechaValid);
		}

		if (farmaciaId > 0) {
			datos.setEXME_Farmacia_ID(farmaciaId);
		}
		
		// el socio y ubicacion del paciente
		datos.setC_BPartner_ID(datos.setBPartner());
		datos.setC_Location_ID(paciente.getC_Location_ID());
	}

	/**
	 * 
	 * @param ctx
	 * @param paciente
	 * @param pActPacienteID
	 * @param pWarehouseID
	 * @param prioridadInd
	 * @param pMedicoID
	 * @param medName
	 * @param pCtaPacID
	 * @param farmaciaId
	 * @param fechaValida
	 * @param doc
	 * @param lstIndica
	 * @param trxName
	 * @return
	 */
	protected MEXMEActPacienteIndH insertActPacientInd(final Properties ctx,
			final MEXMEPaciente paciente, final int pActPacienteID,
			final int pWarehouseID, 
			final String prioridadInd,
			final int pMedicoID, final String medName,
			// Lama: se quitan parametros ya que agregaron como atributos
			//final String fechaform, final String horaCadena,
			final int pCtaPacID, final int farmaciaId,
			final Timestamp fechaValida, final String doc,
			final List<ServicioView> lstIndica, final String trxName
			//,final boolean isServTP
			)
	// throws Exception {
	{

		MEXMEActPacienteIndH encab = null;
		BigDecimal totalSinImp = Env.ZERO;
		BigDecimal totalConImp = Env.ZERO;
		boolean surtir = false;
		int orgTrx = 0;
		try {

			// ahora guardamos el detalle de la ejecucion de la consulta
			for (int i = 0; i < lstIndica.size(); i++) {

				// Encabezado EXME_ActPacienteIndH
				if (encab == null) {
					encab = insertActPacientIndH(ctx, paciente, pActPacienteID,
							pWarehouseID <= 0 ? mejoras.getM_Warehouse_ID()
									: pWarehouseID, prioridadInd, pMedicoID,
							medName, pCtaPacID, farmaciaId, fechaValida, doc, trxName,
							lstIndica.get(i));

					if (encab == null) {
						sLog.log(Level.SEVERE, "insertActPacientIndH : null ");
						break;
					}
				}

				// recuperamos los valores de la lista
				final ServicioView indica = (ServicioView) lstIndica.get(i);
				// Organizacion transaccional
				orgTrx = orgTRX((int) encab.getM_Warehouse_ID());
				// nuevo objeto de tipo ActPacienteInd
				final MEXMEActPacienteInd datos =
				// Linea
				linea(i, orgTrx, encab, indica, ctx, trxName);

				if (datos == null) {
					encab = null;
					break;
				} else {
					// Actualiza el origen
					indica.setEXME_ActPacienteInd_ID(datos
							.getEXME_ActPacienteInd_ID());
					indica.updatePrescRxDet(datos.get_ID(), trxName);

					lstActPacInd.add(datos);

					// Totales del encabezado
					totalSinImp = totalSinImp.add(datos.getLineNetAmt());
					totalConImp = totalConImp.add(datos.getLineNetAmt()).add(
							datos.getTotalImp());

					if (!surtir && indica.getSurtir()) {
						surtir = true; // con solo uno se uqeda como drafted
					}
				}// fin lineas

			}

		} finally {

			if (encab == null) {
				lstErrors.add(new ModelError(ModelError.TIPOERROR_Error,
						"error.citas.setDatosIndicaciones"));
			} else {
				encab.setTotalsComplete(totalSinImp, totalConImp, lstIndica,
						surtir);
			}
		}

		return encab;
	}

	/**
	 * linea
	 * @param index
	 * @param orgTrx
	 * @param encab
	 * @param indica
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	private MEXMEActPacienteInd linea(final int index, final int orgTrx,
			final MEXMEActPacienteIndH encab, final ServicioView indica,
			final Properties ctx, final String trxName) {

		final MEXMEActPacienteIndCgo logCgo = new MEXMEActPacienteIndCgo(ctx,
				0, null);
		MEXMEActPacienteInd datos = null;

		try {
			
			MEXMEProduct mProd = new MEXMEProduct(ctx, (int) indica.getProdID(),trxName);
			// Card #1547 - ProMujer - Medicamentos por país (Lama)
			if(mProd.isProduct() || (indica.getEXME_GenProduct_ID() > 0 && indica.getPlanMed()==null)){
				datos = setActPacientInd(encab, indica, ctx, trxName);

				datos.setLine((index + 1) * 10);
				datos.setAD_OrgTrx_ID(orgTrx);

				if (!datos.save()) {
					sLog.log(Level.SEVERE,
							"insertarMovimientosMed : Error en : if (!datos.save()) ");
					logCgo.setErrorMsg("save() false");
					logCgo.setType(MEXMEActPacienteIndCgo.SAVEFALSE);
					datos = null;
				}
			} else {
				sLog.log(Level.SEVERE,
						"insertarMovimientosMed : Error en : if(mProd.isProduct()) ");
				logCgo.setErrorMsg("Product no valid ID" + mProd.getM_Product_ID());
				logCgo.setType(MEXMEActPacienteIndCgo.NDCNOTVALID);
				datos = null;
			}
			
		} catch (IllegalArgumentException se) {
			sLog.log(Level.SEVERE, se.getMessage(), se);
			logCgo.setErrorMsg(se.getMessage());
			logCgo.setType(MEXMEActPacienteIndCgo.MANDATORY);
			datos = null;

		} finally {
			if (logCgo != null && logCgo.getType() != null) {
				logCgo.setEXME_PlanMed_ID(indica == null
						|| indica.getPlanMed() == null ? 0 : indica.getPlanMed()
								.getEXME_PlanMed_ID());
				logCgo.setEXME_PlanMedLine_ID(indica == null
						|| indica.getPMLine() == null ? 0 : indica.getPMLine()
								.getEXME_PlanMedLine_ID());

				logCgo.setClassname(getClass().getName());
//				logCgo.setEXME_ActPacienteIndH_ID(encab == null ? 0 : encab
//						.getEXME_ActPacienteIndH_ID());
				logCgo.save(trxName);
			}
		}

		return datos;
	}

	/**
	 * Organizacion transaccional
	 * 
	 * @param warehouseID
	 * @return
	 */
	private int orgTRX(final int warehouseID) {
		int orgTrx = 0;
		try {
			orgTrx = MEXMEEstServ.getEstServAlmOrgTrx(ctx, warehouseID);

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "insertActPacienteInd", e);
		}
		return orgTrx;
	}

	/**
	 * 
	 * @param encab
	 * @param indica
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	private MEXMEActPacienteInd setActPacientInd(final MEXMEActPacienteIndH encab, final ServicioView indica,final Properties ctx, final String trxName) {
		//
		MEXMEActPacienteInd datos = new MEXMEActPacienteInd(ctx, 0, trxName);
		MProduct product = new MProduct(ctx, indica.getProdID(), trxName);
		datos.referenciar(ctx, indica, encab, trxName);
		// if(!(indica.getEXME_ActPacienteInd_ID()>0 &&
		// indica.getMotivoCancel()>0)){

		datos.setDates(indica);

		// se obtienen los mensajes desde el inicio .- Lama
		datos.setDescription(indica, cantTomar, vecesDia, numDias);

		datos.setM_Product_ID((int) indica.getProdID());
		datos.setM_Warehouse_ID((int) encab.getM_Warehouse_ID());
		datos.setC_UOM_ID(product.getC_UOM_ID());
//		datos.setC_UOM_ID((int) indica.getUdm());
		datos.setC_UOMVolume_ID(product.getC_UOMVolume_ID());		
		datos.setQtyDelivered(new BigDecimal(indica.getCantidad()));
		datos.setQtyOrdered(indica.getQtyOrdered());
		datos.setQtyOrdered_Vol(indica.getQtyOrderedVol());
		datos.setQuantity_txt(indica.getQuantity_txt());
		datos.setC_Currency_ID((int) encab.getC_Currency_ID());
		datos.setTipoArea(estServLogeo.getTipoArea());
		datos.setEXME_Area_ID(estServLogeo.getEXME_Area_ID());

		datos.setEXME_Dosis_ID(indica.getEXME_Dosis_ID());
		datos.setMotivoCancelacion(indica.getMotivoCancelacion());
		datos.setEXME_ViasAdministracion_ID(indica
				.getEXME_ViaAdministracion_ID());
		datos.setTipoSurtido(indica.getTipoSurtidoValue());
		// datos.setEXME_MotivoCancel_ID(indica.getMotivoCancel());
		datos.setResurtidos(indica.getResurtidos());
		datos.setCantidadToma(new BigDecimal(indica.getCantTomar()));
		datos.setVecesDia(new BigDecimal(indica.getVecesDia()));
		datos.setNumDias(new BigDecimal(indica.getNumDias()));
		datos.setEXME_Medico_ID(encab.getEXME_Medico_ID());
		datos.setTomadoCasa(indica.isTomadoCasa());
		datos.setIsTodayService(indica.isTodayService());
		datos.setNameServ(indica.getProdName());
		if (datos.getM_Product_ID() > 0 && indica.getSurtir()) {
			datos = datos.setPrices(encab, indica);
		}
		datos.setIsDAW(indica.isDAW());
		datos.setIsDEA(indica.isDEA());
		datos.setIsPRN(indica.isPRN());
		datos.setEXME_Farmacia_ID(indica.getEXME_Farmacia_ID());
		datos.setNumDias(new BigDecimal(indica.getNumDias()));
		datos.setEstatus(indica.getEstatus() == null ? MEXMEActPacienteInd.ESTATUS_CompletedService
				: indica.getEstatus());// MEXMEActPacienteInd.ESTATUS_RequestedService);
		datos.setEXME_ActPacienteIndH_ID(encab.getEXME_ActPacienteIndH_ID());

		datos.setSurtir(indica.getSurtir());
		datos.getProdRevCode(indica);

		// datos.setEstatus(MEXMEActPacienteInd.ESTATUS_CompletedService);
		datos.setLote(indica.getLote());
		if (indica.isProcedure()) {
			datos.setAD_Org_Dest_ID(indica.getAD_Org_Dest_ID());
			datos.setDocStatus(MEXMEActPacienteInd.DOCSTATUS_Completed);
			datos.setDateOrdered(indica.getFecha() == null ? DB
					.getTSForOrg(ctx) : indica.getFecha());
			datos.setProveedor(indica.getProvider());
			datos.setConsultingProvider(indica.getConsultingProvider());
			datos.setEXME_Medico_ID(indica.getEXME_Medico_ID());
			datos.setPriorityRule(indica.getPrioridadID());
			datos.setComments(indica.getComments());
			datos.setOtherInstructions(indica.getOtherInstructions());
			datos.setEXME_Diagnostico_ID(indica.getDiagnosis1ID());
			datos.setEXME_Diagnostico2_ID(indica.getDiagnosis2ID());
			datos.setEXME_Diagnostico3_ID(indica.getDiagnosis3ID());
			datos.setEXME_Modifiers_ID(indica.getEXME_Modifier());
			datos.setBillable(indica.isBillable());
		}
		datos.setEXME_OrderSet_ID(indica.getExmeOrderSetId());
		return datos;
	}

	public boolean isPreAlta() {
		return preAlta;
	}

	public void setPreAlta(final boolean preAlta) {
		this.preAlta = preAlta;
	}

	/** Fecha planeada o prometida */
	public void setDatePromised(Timestamp datePromised) {
		this.datePromised = datePromised;
	}

	public Timestamp getDatePromised() {
		return datePromised;
	}
	
	public boolean isServiceTP() {
		return isServiceTP;
	}
	/** true servicio */
	public void setServiceTP(boolean isServiceTP) {
		this.isServiceTP = isServiceTP;
	}
}

/* Clase original */

//
// package org.compiere.model;
//
// import java.math.BigDecimal;
// import java.sql.SQLException;
// import java.sql.Timestamp;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.Properties;
// import java.util.logging.Level;
//
// import org.apache.struts.action.ActionErrors;
// import org.apache.struts.util.LabelValueBean;
// import org.compiere.util.CLogger;
// import org.compiere.util.Constantes;
// import org.compiere.util.DB;
// import org.compiere.util.Env;
// import org.compiere.util.Utilerias;
//
// public class MEXMEPedidosRecetas {
//
// private boolean preAlta = false;
//
// public MEXMEPedidosRecetas() {
// super();
// }
//
// public MEXMEPedidosRecetas(boolean alta) {
// super();
// this.preAlta = alta;
// }
//
// /**
// * A partir de una lista de productos a pedir se crean varios pedidos para
// * su solicitud
// *
// * @param lstMovimientos
// * @throws Exception
// *
// * public HashMap<Integer, List<MovementLine>> getPedidos(
// * List<MovementLine> lstMovimientos) throws Exception{
// *
// * //pasamos los movimientos HashMap<Integer,
// * List<MovementLine>> hashMapPedidos = new HashMap<Integer,
// * List<MovementLine>>();
// *
// * //busqueda de almacenes for(int i=0; i <
// * lstMovimientos.size(); i++) {
// *
// * boolean almacenExiste = false; MovementLine linea =
// * (MovementLine) lstMovimientos.get(i); int almacen =
// * linea.getAlmacenID();
// *
// * if(hashMapPedidos.size()!=0) {
// * if(hashMapPedidos.containsKey(almacen)) { almacenExiste =
// * true;
// *
// * //Agregar el producto List<MovementLine> lstMovNew =
// * hashMapPedidos.get(almacen); lstMovNew.add(linea);
// * hashMapPedidos.remove(almacen);
// * hashMapPedidos.put(almacen,lstMovNew); //break; } }
// *
// * if(!almacenExiste) { List<MovementLine> lstMovNew = new
// * ArrayList<MovementLine>(); lstMovNew.add(linea);
// * hashMapPedidos.put(almacen, lstMovNew); } }
// *
// * return hashMapPedidos; }
// *
// * /** A partir de una lista de productos a pedir se crean
// * varios pedidos para su solicitud dependiendo del nivel de
// * control de cada medicamento y de donde es surtido cada
// * producto
// * @param lstMovimientos
// * @throws Exception
// *
// * public static HashMap<Integer, HashMap<String,
// * List<ServicioView>>> getPedidosActPac( List<ServicioView>
// * lstMovimientos) throws Exception{
// *
// * //pasamos los movimientos HashMap<Integer, HashMap<String,
// * List<ServicioView>>> hshMpPedidos = new HashMap<Integer,
// * HashMap<String, List<ServicioView>>>();
// *
// *
// * // Los pedidos ser�n por medicamento controlado y por almacen
// * que surte
// *
// *
// * //Lista de productos a solicitar for(int i=0; i <
// * lstMovimientos.size(); i++) {
// *
// * //Existe el nivel de control del producto boolean
// * controlExiste = false;
// *
// * //Existe el almacen del producto boolean almacenExiste =
// * false;
// *
// * //La l�nea del producto de la lista de movimientos
// * ServicioView linea = (ServicioView) lstMovimientos.get(i);
// *
// * //Almac�n de la l�nea int almacen = linea.getAlmaServ();
// *
// * //Nivel de control de la l�nea String control =
// * linea.getNivelControl();
// *
// * //Si existen elementos del hshMpPedidos //Si existe el
// * almacen en el mapa if(hshMpPedidos.size()!=0 &&
// * hshMpPedidos.containsKey(almacen)) { almacenExiste = true;
// *
// *
// * //Si existe el nivel de control en el mapa
// * if(hshMpPedidos.get(almacen).containsKey(control)) {
// * controlExiste = true;
// *
// *
// * //Respaldos de info
// *
// * //HashMap de nivel de control - producto HashMap<String,
// * List<ServicioView>> hshMpControl_Prod =
// * hshMpPedidos.get(almacen);
// *
// * //Lista de productos List<ServicioView> lstMovNew =
// * hshMpPedidos.get(almacen).get(control);
// *
// * //Removemos la informacion
// *
// * //Removemos el control
// * hshMpPedidos.get(almacen).remove(control);
// *
// * //Removemos el almacen hshMpPedidos.remove(almacen);
// *
// * //Agregamos la info
// *
// * //Agregar el producto lstMovNew.add(linea);
// *
// * //Agregamos el control hshMpControl_Prod.put(control,
// * lstMovNew);
// *
// * //Agregamos al hasMap hshMpPedidos.put(almacen,
// * hshMpControl_Prod);
// *
// *
// * //break; }
// *
// * //Sino existe el nivel de control if(!controlExiste) {
// * //HashMap de nivel de control - producto HashMap<String,
// * List<ServicioView>> hshMpControl_Prod =
// * hshMpPedidos.get(almacen); // new HashMap<String,
// * List<ServicioView>>();
// *
// * //Lista con el producto List<ServicioView> lstMovNew = new
// * ArrayList<ServicioView>(); lstMovNew.add(linea);
// *
// * //Agregamos el elemento Control - Producto
// * hshMpControl_Prod.put(control, lstMovNew);
// *
// * //Removemos el elemento anterior
// * hshMpPedidos.remove(almacen);
// *
// * //Cargamos el nuevo elemento Almac�n - Controles - Productos
// * hshMpPedidos.put(almacen, hshMpControl_Prod);
// *
// *
// * }
// *
// * }
// *
// * //Sino existe el almacen if(!almacenExiste) { //HashMap de
// * nivel de control - producto HashMap<String,
// * List<ServicioView>> hshMpControl_Prod = new HashMap<String,
// * List<ServicioView>>();
// *
// * //Lista de productos List<ServicioView> lstMovNew = new
// * ArrayList<ServicioView>(); lstMovNew.add(linea);
// *
// * //control - producto hshMpControl_Prod.put(control,
// * lstMovNew);
// *
// * //almacen - controles - productos hshMpPedidos.put(almacen,
// * hshMpControl_Prod); } }
// *
// * return hshMpPedidos; }
// *
// *
// * /** A partir de una lista de productos a pedir se crean
// * varios pedidos para su solicitud dependiendo del nivel de
// * control de cada medicamento y de donde es surtido cada
// * producto
// * @param lstMovimientos
// * @throws Exception
// *
// * public static HashMap<Integer, HashMap<String,
// * List<MEXMEActPacienteInd>>> getPedidosActPac2(
// * List<MEXMEActPacienteInd> lstMovimientos) throws Exception{
// *
// * //pasamos los movimientos HashMap<Integer, HashMap<String,
// * List<MEXMEActPacienteInd>>> hshMpPedidos = new
// * HashMap<Integer, HashMap<String,
// * List<MEXMEActPacienteInd>>>();
// *
// *
// * // Los pedidos ser�n por medicamento controlado y por almacen
// * que surte
// *
// *
// * //Lista de productos a solicitar for(int i=0; i <
// * lstMovimientos.size(); i++) {
// *
// * //Existe el nivel de control del producto boolean
// * controlExiste = false;
// *
// * //Existe el almacen del producto boolean almacenExiste =
// * false;
// *
// * //La l�nea del producto de la lista de movimientos
// * MEXMEActPacienteInd linea = (MEXMEActPacienteInd)
// * lstMovimientos.get(i);
// *
// * //Almac�n de la l�nea int almacen =
// * linea.getM_Warehouse_ID();
// *
// * //Nivel de control de la l�nea String control =
// * linea.getProduct().getItemClass();
// *
// * //Si existen elementos del hshMpPedidos //Si existe el
// * almacen en el mapa if(hshMpPedidos.size()!=0 &&
// * hshMpPedidos.containsKey(almacen)) { almacenExiste = true;
// *
// *
// * //Si existe el nivel de control en el mapa
// * if(hshMpPedidos.get(almacen).containsKey(control)) {
// * controlExiste = true;
// *
// *
// * //Respaldos de info
// *
// * //HashMap de nivel de control - producto HashMap<String,
// * List<MEXMEActPacienteInd>> hshMpControl_Prod =
// * hshMpPedidos.get(almacen);
// *
// * //Lista de productos List<MEXMEActPacienteInd> lstMovNew =
// * hshMpPedidos.get(almacen).get(control);
// *
// * //Removemos la informacion
// *
// * //Removemos el control
// * hshMpPedidos.get(almacen).remove(control);
// *
// * //Removemos el almacen hshMpPedidos.remove(almacen);
// *
// * //Agregamos la info
// *
// * //Agregar el producto lstMovNew.add(linea);
// *
// * //Agregamos el control hshMpControl_Prod.put(control,
// * lstMovNew);
// *
// * //Agregamos al hasMap hshMpPedidos.put(almacen,
// * hshMpControl_Prod);
// *
// *
// * //break; }
// *
// * //Sino existe el nivel de control if(!controlExiste) {
// * //HashMap de nivel de control - producto HashMap<String,
// * List<MEXMEActPacienteInd>> hshMpControl_Prod =
// * hshMpPedidos.get(almacen); // new HashMap<String,
// * List<ServicioView>>();
// *
// * //Lista con el producto List<MEXMEActPacienteInd> lstMovNew =
// * new ArrayList<MEXMEActPacienteInd>(); lstMovNew.add(linea);
// *
// * //Agregamos el elemento Control - Producto
// * hshMpControl_Prod.put(control, lstMovNew);
// *
// * //Removemos el elemento anterior
// * hshMpPedidos.remove(almacen);
// *
// * //Cargamos el nuevo elemento Almac�n - Controles - Productos
// * hshMpPedidos.put(almacen, hshMpControl_Prod);
// *
// *
// * }
// *
// * }
// *
// * //Sino existe el almacen if(!almacenExiste) { //HashMap de
// * nivel de control - producto HashMap<String,
// * List<MEXMEActPacienteInd>> hshMpControl_Prod = new
// * HashMap<String, List<MEXMEActPacienteInd>>();
// *
// * //Lista de productos List<MEXMEActPacienteInd> lstMovNew =
// * new ArrayList<MEXMEActPacienteInd>(); lstMovNew.add(linea);
// *
// * //control - producto hshMpControl_Prod.put(control,
// * lstMovNew);
// *
// * //almacen - controles - productos hshMpPedidos.put(almacen,
// * hshMpControl_Prod); } }
// *
// * return hshMpPedidos; }
// */
// /**
// * Inserta los movimientos generados desde traspasos entre almacenes
// *
// * @param ctx
// * @param lstMovimientos
// * @param documentNo
// * @param devol
// * @param projectID
// * @param activity
// * @param campaign
// * @param orgTrx
// * @param user1
// * @param user2
// * @param ctaPac
// * @param docType
// * @param description
// * @param isMostrarNumSerie
// * @param almacenSolID
// * @param trxName
// * @throws Exception
// *
// * public void insertarMovimientosMov( Properties ctx,
// * List<MovementLine> lstMovimientos, String documentNo, boolean
// * devol, int projectID, int activity, int campaign, int orgTrx,
// * int user1, int user2, int ctaPac, int docType, String
// * description, boolean isMostrarNumSerie, int almacenSolID,
// * String trxName)
// *
// * throws Exception{
// *
// * //pasamos los movimientos HashMap<Integer,
// * List<MovementLine>> hshMpPedidos =
// * getPedidos(lstMovimientos);
// *
// * this.lstNumPedidos=new ArrayList<LabelValueBean>();
// *
// * // Obtener Collection de almacenes a procesar Set<Integer>
// * ctaSet = hshMpPedidos.keySet(); for (Integer iKeyAlmacen :
// * ctaSet) {
// *
// * //Encabezado del movimiento MEXMEMovement mov =
// * AlmacenesDB.insertMovement(ctx, documentNo, devol, projectID,
// * activity, campaign, orgTrx, user1, user2, ctaPac, docType,
// * description, trxName, null);
// *
// * //Lista de productos de solicitud para el almacen
// * List<MovementLine> lstPedido = hshMpPedidos.get(iKeyAlmacen);
// *
// * //convertimos la lista a hashtable List<MovementLine>
// * htLineas = new ArrayList<MovementLine>(); for(int i=0;
// * i<lstPedido.size(); i++) { MovementLine lineas =
// * (MovementLine) lstPedido.get(i); if(isMostrarNumSerie){
// * lineas.setLine((i+1)*10); htLineas.add(lineas); } else {
// * htLineas.add(lineas); } }
// *
// * this.actionErrors = AlmacenesDB.insertMovementLine(ctx,
// * htLineas, devol, mov.getM_Movement_ID(), iKeyAlmacen,
// * almacenSolID, ctaPac, trxName);
// *
// * //Result this.lstNumPedidos.add(new
// * LabelValueBean(mov.getDocumentNo(),
// * String.valueOf(mov.getM_Movement_ID())));
// *
// * } }
// */
// private List<LabelValueBean> lstNumPedidos = new ArrayList<LabelValueBean>();
//
// public List<LabelValueBean> getLstNumPedidos() {
// return lstNumPedidos;
// }
//
// public void setLstNumPedidos(List<LabelValueBean> lstNumPedidos) {
// this.lstNumPedidos = lstNumPedidos;
// }
//
// private ActionErrors actionErrors = null;
//
// public ActionErrors getActionErrors() {
// return actionErrors;
// }
//
// public void setErrors(ActionErrors errors) {
// this.actionErrors = errors;
// }
//
// private List<ModelError> lstErrors = new ArrayList<ModelError>();
//
// public List<ModelError> getLstErrors() {
// return this.lstErrors;
// }
//
// public void setLstErrors(List<ModelError> errors) {
// this.lstErrors = errors;
// }
//
// /**
// * Mascara para recibir fecha como date y surtir como boolean de
// * insertarMovimientosCE
// *
// * @param ctx
// * @param paciente
// * @param isSurFarm
// * @param EXME_ActPaciente_ID
// * @param prioridadInd
// * @param EXME_Medico_ID
// * @param medName
// * @param date
// * @param EXME_CtaPac_ID
// * @param lstIndicaciones
// * @param farmaciaId
// * @param fechaValida
// * @param trxName
// * @throws Exception
// *
// * public void insertarMovimientosCE(Properties ctx,
// * MEXMEPaciente paciente, int EXME_ActPaciente_ID, String
// * prioridadInd, int EXME_Medico_ID, String medName, Date date,
// * int EXME_CtaPac_ID, List<? extends ServicioView>
// * lstIndicaciones, int farmaciaId, Timestamp fechaValida,
// * String trxName) throws Exception {
// *
// *
// * insertarMovimientosCE(ctx, paciente, EXME_ActPaciente_ID,
// * prioridadInd, EXME_Medico_ID, medName, fechaform, horaCadena,
// * EXME_CtaPac_ID, lstIndicaciones, farmaciaId, fechaValida,
// * trxName);
// *
// * } /** Modificación para crear un header por almacen (si son
// * medicamentos no controlados), y por medicamento de nivel 1,
// * 2, 3 o vacuna.
// * @param ctx
// * @param paciente
// * @param surFarm
// * @param EXME_ActPaciente_ID
// * @param prioridadInd
// * @param EXME_Medico_ID
// * @param medName
// * @param fechaform
// * @param horaCadena
// * @param EXME_CtaPac_ID
// * @param lstIndicaciones
// * @param trxName
// * @throws Exception
// *
// * public boolean insertarMovimientosCE(Properties ctx,
// * MEXMEPaciente paciente,int EXME_ActPaciente_ID, String
// * prioridadInd, int EXME_Medico_ID, String medName, String
// * fechaform, String horaCadena, int EXME_CtaPac_ID, List<?
// * extends ServicioView> lstIndicaciones, int farmaciaId,
// * Timestamp fechaValida, String trxName ) throws Exception {
// *
// * if (fechaform == null || horaCadena == null ||
// * fechaform.length()<=0 || horaCadena.length()<=0) { fechaform
// * = Constantes.getSdfFecha().format(new Date()); horaCadena =
// * Constantes.getSdfHora24().format(new Date()); }
// *
// * HashMap<Integer, List<ServicioView>> porAlmacen = new
// * HashMap<Integer, List<ServicioView>>(); List<ServicioView>
// * lstPorProducto = new ArrayList<ServicioView>();
// *
// * actionErrors = new ActionErrors();
// *
// * // Separar servicios for (ServicioView view :
// * lstIndicaciones) {
// *
// * String control = view.getNivelControl(); MProduct prod = new
// * MProduct(ctx, (int) view.getProdID(), null);
// *
// * // Valor por defecto en caso de null control =
// * StringUtils.defaultIfEmpty(control, "");
// *
// * // Agrupados por almacen si no son controlados o vacunas if
// * (!control.equalsIgnoreCase(X_M_Product.
// * ITEMCLASS_Misc_Controlled_1) &&
// * !control.equalsIgnoreCase(X_M_Product
// * .ITEMCLASS_Misc_Controlled_2) &&
// * !control.equalsIgnoreCase(X_M_Product.ITEMCLASS_Schedule_II)
// * && !control.equalsIgnoreCase(X_M_Product.
// * ITEMCLASS_Schedule_III) && !prod.isEsVacuna()) { int
// * almacenId = view.getAlmaServ(); List<ServicioView> servicios
// * = null; // Buscamos el almacen en el mapa if
// * (porAlmacen.containsKey(almacenId)) { servicios =
// * porAlmacen.get(almacenId); } else { servicios = new
// * ArrayList<ServicioView>(); }
// *
// * servicios.add(view);
// *
// * // Actualizando la informacion porAlmacen.put(almacenId,
// * servicios); } else { // Productos controlados o vacunas
// * lstPorProducto.add(view); } }
// *
// * Set<Integer> almacenes = porAlmacen.keySet();
// * Iterator<Integer> it = almacenes.iterator();
// *
// * // Iteramos los almacenes while (it.hasNext()) { int almacen
// * = it.next();
// *
// * // Medicamentos del almacen List<ServicioView> local =
// * porAlmacen.get(almacen);
// *
// * // Guardamos los detalles MEXMEActPacienteIndH actPacIndH =
// * insertActPacientInd(ctx, paciente, EXME_ActPaciente_ID,
// * almacen, prioridadInd, EXME_Medico_ID, medName, fechaform,
// * horaCadena, EXME_CtaPac_ID, farmaciaId, fechaValida,
// * Constantes.RECETA, local, trxName);
// *
// * // Si no se genero if(actPacIndH==null){
// * setLstErrors(getErrors()); return false; }
// * actPacIndH.setPreAlta(this.preAlta);
// *
// * // Guardamos el encabezado if (!actPacIndH.save(trxName)) {
// * throw new SQLException("error.citas.noInsertEjec"); }
// * this.lstNumPedidos.add(new
// * LabelValueBean(actPacIndH.getDocumentNo(),
// * String.valueOf(actPacIndH.getEXME_ActPacienteIndH_ID()))); }
// *
// * // Iteramos los medicamentos controlados o vacunas for
// * (ServicioView view : lstPorProducto) { int almacen =
// * view.getAlmaServ();
// *
// * // Creamos una lista con un solo registro List<ServicioView>
// * local = new ArrayList<ServicioView>(); local.add(view);
// *
// * // MEXMEActPacienteIndH actPacIndH = insertActPacientInd(ctx,
// * paciente, EXME_ActPaciente_ID, almacen, prioridadInd,
// * EXME_Medico_ID, medName, fechaform, horaCadena,
// * EXME_CtaPac_ID, farmaciaId, null, Constantes.RECETA, local,
// * trxName);
// *
// * // Insertamos el detalle if(actPacIndH==null){
// * setLstErrors(getErrors()); return false; }
// * actPacIndH.setPreAlta(this.preAlta);
// *
// * // Guardamos el ecabezado if (!actPacIndH.save(trxName)) {
// * throw new SQLException("error.citas.noInsertEjec"); }
// * this.lstNumPedidos.add(new
// * LabelValueBean(actPacIndH.getDocumentNo(),
// * String.valueOf(actPacIndH.getEXME_ActPacienteIndH_ID()))); }
// *
// * return true; }
// */
//
// /**
// * Inserta la solicitudes de productos
// *
// * @param ctx
// * @param paciente
// * @param lstIndicaciones
// * @param surFarm
// * @param EXME_ActPaciente_ID
// * @param M_Warehouse_ID
// * @param prioridadInd
// * @param EXME_Medico_ID
// * @param medName
// * @param fechaform
// * @param horaCadena
// * @param trxName
// * @throws Exception
// *
// * public boolean insertarMovimientosCE( Properties ctx,
// * MEXMEPaciente paciente, List<ServicioView> lstIndicaciones,
// * int EXME_ActPaciente_ID, int M_Warehouse_ID, String
// * prioridadInd, int EXME_Medico_ID, String medName, String
// * fechaform, String horaCadena, int EXME_CtaPac_ID, int
// * farmaciaId, Timestamp fechaValid, String trxName)
// *
// * throws Exception {
// *
// * ActionErrors errors = new ActionErrors();
// *
// * this.lstNumPedidos=new ArrayList<LabelValueBean>();
// *
// * //pasamos los movimientos HashMap<Integer, HashMap<String,
// * List<ServicioView>>> hshMpPedidos =
// * getPedidosActPac(lstIndicaciones);
// *
// *
// * // Obtener Collection de almacenes a procesar Set<Integer>
// * almSet = hshMpPedidos.keySet();
// *
// * for (Integer iKeyAlmacen : almSet){ //Itera el almacen
// *
// * HashMap<String, List<ServicioView>> hshMpControl_Prod =
// * hshMpPedidos.get(iKeyAlmacen);
// *
// * // Obtener Collection de almacenes a procesar Set<String>
// * contSet = hshMpControl_Prod.keySet();
// *
// * boolean unaSolaVez = false; for (String iKeyControl :
// * contSet) //Itera el nivel de control { // List<ServicioView>
// * lstLocalIndicaciones = hshMpControl_Prod.get(iKeyControl);
// *
// * // MEXMEActPacienteIndH actPacIndH = insertActPacientInd(ctx,
// * paciente, EXME_ActPaciente_ID, iKeyAlmacen, prioridadInd,
// * EXME_Medico_ID, medName, fechaform, horaCadena,
// * EXME_CtaPac_ID, farmaciaId, fechaValid, Constantes.RECETA,
// * lstLocalIndicaciones, trxName);
// *
// * // if(actPacIndH == null){ setLstErrors(getErrors()); return
// * false; }
// *
// * //Si es el nivel de control 1 se marca para que no se imprima
// * reporte if(iKeyControl!=null &&
// * iKeyControl.equalsIgnoreCase(X_M_Product
// * .ITEMCLASS_Misc_Controlled_1) && !unaSolaVez) {
// * actPacIndH.setIsDiscountPrinted(true); unaSolaVez = true;
// * //Define que tiene el mayor nivel de control de medicamentos
// * if (!actPacIndH.save(trxName)) { throw new
// * SQLException("error.citas.noInsertEjec"); } } else { //Result
// * this.lstNumPedidos.add( new LabelValueBean(
// * actPacIndH.getDocumentNo(),
// * String.valueOf(actPacIndH.getEXME_ActPacienteIndH_ID()) ) );
// * } }
// *
// * this.actionErrors = errors;
// *
// * }
// *
// * return true; }
// *
// * /** Static Logger
// */
// private static CLogger s_log = CLogger
// .getCLogger(MEXMEPedidosRecetas.class);
//
// /**
// * Busqueda de productos en receta individual y receta colectiva
// *
// * @param liga
// * @return
// *
// * public static String busquedaProdCE(String liga) { StringBuilder
// * cadena = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
// *
// * //Productos para doctores de CE que pueden recetar cualquier
// * producto //"ProdCESinControl", "ProdRecInd1" cadena.append(
// *
// " SELECT p.m_product_id, p.value, p.name, p.description, SUM( s.QtyOnHand - s.QtyReserved ), SUM( s.QtyOnHand ) "
// * ) .append("  FROM M_Replenish r ") .append(
// *
// "  INNER JOIN M_Product p ON r.M_Product_ID = p.M_Product_ID AND p.IsSold='Y' AND p.IsActive = 'Y' AND p.ProductType = '"
// * ) .append(X_M_Product.PRODUCTTYPE_Item).append("' ") .append(
// *
// "  LEFT  JOIN EXME_TipoProd tp  ON p.EXME_TipoProd_ID = tp.EXME_TipoProd_ID AND tp.value IN ('"
// * ) .append(MTipoProd.MEDICAMENTOS_VALUE).append("','")
// * .append(MTipoProd.DESCARTABLES_VALUE).append("') ") .append(
// * "  INNER JOIN M_Warehouse w     ON r.M_Warehouse_ID   = w.M_Warehouse_ID "
// * ) // Lama .- Product_Cte .append(
// *
// " LEFT JOIN  M_Product_Cte cte ON (p.M_Product_ID = cte.M_Product_ID AND cte.AD_Client_ID =  w.AD_Client_ID ) "
// * ) // #1 .append(
// * "  LEFT  JOIN M_Locator l       ON w.M_Warehouse_ID   = l.M_Warehouse_ID "
// * ) .append(
// *
// "  LEFT  JOIN M_Storage s       ON r.M_Product_ID     = s.M_Product_ID AND l.M_locator_id = s.m_locator_id "
// * ) .append(
// *
// "  LEFT  JOIN M_AttributeSetInstance ai ON s.M_AttributeSetInstance_ID = ai.M_AttributeSetInstance_ID AND ai.isactive='Y'  "
// * )
// *
// * .append(
// *
// " INNER JOIN exme_productoorg prodOrg on (p.m_product_id = prodorg.m_product_id AND prodorg.ad_org_id  = "
// * + Env.getAD_Org_ID(Env.getCtx())+" )  ")
// *
// * .append("  WHERE r.IsActive = 'Y' ")
// * .append("  AND r.M_Product_ID IN ( ")
// * .append(" 		 SELECT DISTINCT wr.M_Product_ID ")
// * .append(" 			 FROM M_Replenish wr ")
// * .append(" 			 WHERE wr.IsActive = 'Y' AND wr.M_Warehouse_ID = ?1  "
// * ) .append("  ) ");
// *
// * if(liga.length()>0) cadena.append("  AND r.M_Product_ID IN (  ")
// * .append(" 		 SELECT DISTINCT r.M_Product_ID ")
// * .append(" 			 FROM M_Replenish r  ") .append(
// *
// " 			 INNER JOIN EXME_WarehouseRel wr ON wr.M_WarehouseRel_ID = r.M_Warehouse_ID AND wr.M_Warehouse_ID = ?1  "
// * ) .append(
// *
// "             INNER JOIN M_Warehouse wa ON wr.M_WarehouseRel_ID = wa.M_Warehouse_ID  "
// * );
// *
// * /* Tipo de Almac�n
// */
//
// // Parte del sub consulta
// /*
// * if(liga.equalsIgnoreCase("ProdCENoControl"))
// * cadena.append(" AND wa.TipoAlmacen = '"
// * +MWarehouse.TIPOALMACEN_CollectivePrescription+"' "); else
// * if(liga.length()>0) cadena.append(" AND wa.TipoAlmacen = '"+MWarehouse.
// * TIPOALMACEN_IndividualPrescription+"' ");
// */
// /*
// * if(liga.length()>0) { cadena.append(" 			 WHERE r.IsActive = 'Y' ")
// * .append("  )				 ") //Termina subconsulta
// *
// * /* Productos por lotes y fecha de vigencia
// */
// /*
// * .append(
// *
// " AND ( NVL(cte.IsLot,prodOrg.IsLot)='N' OR trunc(ai.GuaranteeDate) >= trunc("
// * ).append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(") ) ");
// * }
// *
// * /* Productos por Especialidades
// */
//
// /*
// * if( !liga.equalsIgnoreCase("ProdRecInd1")
// * &&!liga.equalsIgnoreCase("ProdRecInd2") &&
// * !liga.equalsIgnoreCase("ProdRecInd3") &&
// * !liga.equalsIgnoreCase("ProdRecInd6") &&
// * !liga.equalsIgnoreCase("ProdFromOneWhsRC") &&
// * !liga.equalsIgnoreCase("ProdRecInd4") &&
// * !liga.equalsIgnoreCase("ProdRecInd5") &&
// * !liga.equalsIgnoreCase("ProdCEControl5") &&
// * !liga.equalsIgnoreCase("ProdCEControl4") && liga.length()>0) {
// * cadena.append(" AND ( ") .append("  	 ( r.M_Product_ID IN (  ")
// * .append("  	 SELECT pe.M_Product_ID FROM EXME_ProductEsp pe  ")
// * .append("  	 WHERE pe.EXME_Especialidad_ID = ?2 ) ") .append("  	 ) ")
// * .append("  	 OR  ") .append("  	 ( r.M_Product_ID NOT IN (  ")
// * .append("  	 SELECT pe.M_Product_ID FROM EXME_ProductEsp pe  ")
// * .append("  	 WHERE pe.EXME_Especialidad_ID <> ?3 )  ") .append("  	 )  ")
// * .append("  ) "); }
// *
// * /*Productos por nivel de control del m�dico
// */
//
// // Productos para doctores de CE que pueden recetar de nivel 2 hacia a tras
// /*
// * if(liga.equalsIgnoreCase("ProdCEControl2") ||
// * liga.equalsIgnoreCase("ProdRecInd2")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("') ) "); }
// * //Productos para doctores de CE que pueden recetar de nivel 3 hacia a
// * tras if(liga.equalsIgnoreCase("ProdCEControl3") ||
// * liga.equalsIgnoreCase("ProdRecInd3")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("' ) ) "); }
// *
// * if(liga.equalsIgnoreCase("ProdCEControl4") ||
// * liga.equalsIgnoreCase("ProdRecInd4")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_III).append("') ) "); }
// *
// * if(liga.equalsIgnoreCase("ProdCEControl5") ||
// * liga.equalsIgnoreCase("ProdRecInd5")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_III).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_IV).append("') ) "); }
// *
// * //Productos para doctores de CE que pueden recetar productos no
// * controlados if(liga.equalsIgnoreCase("ProdCENoControl") ||
// * liga.equalsIgnoreCase("ProdRecInd6")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_III).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_IV).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_V).append("') ) "); }
// *
// * cadena.append(" GROUP BY p.m_product_id, p.value, p.name, p.description ")
// * ;
// *
// * s_log.log(Level.FINEST, "MEXMEPedidosRectas.busquedaProdCE.sql > " +
// * cadena.toString()); //System.out.println("Query >"+ cadena.toString());
// *
// * return cadena.toString(); }//Fin metodo
// */
// /*
// * public static String busquedaProdCESF(String liga) { StringBuilder cadena
// * = new StringBuilder();
// *
// * //Productos para doctores de CE que pueden recetar cualquier producto
// * //"ProdCESinControl", "ProdRecInd1"
// * cadena.append(" SELECT p.m_product_id, p.value, p.name, p.description")
// * //.append(", SUM( s.QtyOnHand - s.QtyReserved ), SUM( s.QtyOnHand ) ")
// * .append("  FROM M_Product p  ") .append(
// *
// "  LEFT  JOIN EXME_TipoProd tp  ON p.EXME_TipoProd_ID = tp.EXME_TipoProd_ID AND tp.value IN ('"
// * ) .append(MTipoProd.MEDICAMENTOS_VALUE).append("','")
// * .append(MTipoProd.DESCARTABLES_VALUE).append("') ")
// * .append("  WHERE p.IsSold='Y' AND p.IsActive = 'Y' AND p.ProductType = '"
// * ) .append(X_M_Product.PRODUCTTYPE_Item).append("' ") ;
// *
// * /*Productos por nivel de control del m�dico
// */
//
// // Productos para doctores de CE que pueden recetar de nivel 2 hacia a tras
// /*
// * if(liga.equalsIgnoreCase("ProdCEControl2SF") ||
// * liga.equalsIgnoreCase("ProdRecInd2SF")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("') ) "); }
// * //Productos para doctores de CE que pueden recetar de nivel 3 hacia a
// * tras if(liga.equalsIgnoreCase("ProdCEControl3SF") ||
// * liga.equalsIgnoreCase("ProdRecInd3SF")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("' ) ) "); }
// *
// * if(liga.equalsIgnoreCase("ProdCEControl4SF") ||
// * liga.equalsIgnoreCase("ProdRecInd4SF")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_III).append("') ) "); }
// *
// * if(liga.equalsIgnoreCase("ProdCEControl5SF") ||
// * liga.equalsIgnoreCase("ProdRecInd5SF")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_III).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_IV).append("') ) "); }
// *
// * //Productos para doctores de CE que pueden recetar productos no
// * controlados if(liga.equalsIgnoreCase("ProdCENoControlSF") ||
// * liga.equalsIgnoreCase("ProdRecInd6SF")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_III).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_IV).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_V).append("') ) "); }
// *
// * cadena.append(" GROUP BY p.m_product_id, p.value, p.name, p.description ")
// * ;
// *
// * s_log.log(Level.FINEST, "MEXMEPedidosRectas.busquedaProdCE.sql > " +
// * cadena.toString()); //System.out.println("Query >"+ cadena.toString());
// *
// * return cadena.toString(); }//Fin metodo
// */
// /*
// * public ServicioView copyServiciosView(MEXMEActPacienteInd
// * actPacienteInd){
// *
// * ServicioView obj = new ServicioView(); MProduct product = null;
// *
// * ServicioView indica = new ServicioView();
// * indica.setProdID(actPacienteInd.getM_Product_ID());
// *
// * if(product==null){ product = new MProduct(actPacienteInd.getCtx(),
// * actPacienteInd.getM_Product_ID(), null); }
// *
// * indica.setProdValue(product.getValue());
// * indica.setProdName(product.getName());
// *
// * indica.setDescripcion(actPacienteInd.getDescription());
// * indica.setUdm(actPacienteInd.getC_UOM_ID());
// *
// * indica.setCantidad(actPacienteInd.getQtyDelivered().intValue());
// * indica.setQtyOrdered(actPacienteInd.getQtyOrdered());
// * indica.setCantTomar(actPacienteInd.getCantidadToma().doubleValue());
// * indica.setVecesDia(actPacienteInd.getVecesDia().intValue());
// * indica.setNumDias(actPacienteInd.getNumDias().intValue());
// * indica.setSurtir(actPacienteInd.isSurtir());
// * indica.setAlmaServ(actPacienteInd.getM_Warehouse_ID());
// * indica.setNivelControl(product.getItemClass());
// * if(actPacienteInd.getM_Product_ID() !=0 && indica.getUdm() > 0){ try {
// * indica
// * .setUdmName(MEXMEUOM.nombreUDM(actPacienteInd.getCtx(),indica.getUdm()));
// * } catch (Exception e) { s_log.log(Level.SEVERE, e.getMessage(), e);//Lama
// * //e.printStackTrace(); } }
// *
// * //Tipo de dosis
// * indica.setEXME_Dosis_ID(actPacienteInd.getEXME_Dosis_ID());
// * indica.setTipoDosis("");
// *
// * //via de administracion
// * indica.setEXME_ViaAdministracion_ID(actPacienteInd
// * .getEXME_ViasAdministracion_ID()); indica.setViaAdministracion("");
// *
// * //ripo surtido indica.setTipoSurtido(actPacienteInd.getTipoSurtido());
// * indica.setTipoSurtidoValue("");
// *
// * //fecha de vigencia
// * //indica.setFechaVig(Constantes.sdfFecha.format(actPacienteInd
// * .ge//tValidFrom()));
// *
// * //resurtidos indica.setResurtidos(actPacienteInd.getResurtidos());
// *
// * return obj; }
// */
// /**
// *
// * @param ctx
// * @param resourseKey
// * @param almacen
// * @param especialidad
// * @return
// *
// * public static List<MEXMEProduct> getMedicationList(Properties
// * ctx, String resourseKey, int almacen, int especialidad){
// * List<MEXMEProduct> list = new ArrayList<MEXMEProduct>();
// * PreparedStatement pstmt = null; ResultSet res = null;
// *
// * StringBuilder cadena = new
// * StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
// *
// * if(resourseKey.endsWith("SF")){
// * cadena.append(busquedaProdCESF(resourseKey)); }else{ //Productos
// * para doctores de CE que pueden recetar cualquier producto
// * //"ProdCESinControl", "ProdRecInd1" cadena.append(
// *
// " SELECT p.m_product_id, p.value, p.name, p.description, SUM( s.QtyOnHand - s.QtyReserved ), SUM( s.QtyOnHand ) "
// * ) .append("  FROM M_Replenish r ") .append(
// *
// "  INNER JOIN M_Product p ON r.M_Product_ID = p.M_Product_ID AND p.IsSold='Y' AND p.IsActive = 'Y' AND p.ProductType = '"
// * ) .append(X_M_Product.PRODUCTTYPE_Item).append("' ") // Lama .-
// * Product_Cte .append(
// *
// " LEFT JOIN  M_Product_Cte cte ON (p.M_Product_ID = cte.M_Product_ID AND cte.AD_Client_ID = ? ) "
// * ) // #1 .append(
// *
// "  LEFT  JOIN EXME_TipoProd tp  ON p.EXME_TipoProd_ID = tp.EXME_TipoProd_ID AND tp.value IN ('"
// * ) .append(MTipoProd.MEDICAMENTOS_VALUE).append("','")
// * .append(MTipoProd.DESCARTABLES_VALUE).append("') ") .append(
// * "  INNER JOIN M_Warehouse w     ON r.M_Warehouse_ID   = w.M_Warehouse_ID "
// * ) .append(
// * "  LEFT  JOIN M_Locator l       ON w.M_Warehouse_ID   = l.M_Warehouse_ID "
// * ) .append(
// *
// "  LEFT  JOIN M_Storage s       ON r.M_Product_ID     = s.M_Product_ID AND l.M_locator_id = s.m_locator_id "
// * ) .append(
// *
// "  LEFT  JOIN M_AttributeSetInstance ai ON s.M_AttributeSetInstance_ID = ai.M_AttributeSetInstance_ID AND ai.isactive='Y'  "
// * )
// *
// * .append(
// *
// " INNER JOIN exme_productoorg prodOrg on (p.m_product_id = prodorg.m_product_id AND prodorg.ad_org_id  = "
// * + Env.getAD_Org_ID(Env.getCtx())+" )  ")
// *
// * .append("  WHERE r.IsActive = 'Y' ")
// * .append("  AND r.M_Product_ID IN ( ")
// * .append(" 		 SELECT DISTINCT wr.M_Product_ID ")
// * .append(" 			 FROM M_Replenish wr ")
// * .append(" 			 WHERE wr.IsActive = 'Y' AND wr.M_Warehouse_ID = ? "
// * )//.append(almacen) #2 // Lama .append("  ) ")
// * .append("  AND r.M_Product_ID IN (  ")
// * .append(" 		 SELECT DISTINCT r.M_Product_ID ")
// * .append(" 			 FROM M_Replenish r  ") .append(
// *
// " 			 INNER JOIN EXME_WarehouseRel wr ON wr.M_WarehouseRel_ID = r.M_Warehouse_ID AND wr.M_Warehouse_ID = ? "
// * )// .append(almacen) #3 // Lama .append(
// *
// "             INNER JOIN M_Warehouse wa ON wr.M_WarehouseRel_ID = wa.M_Warehouse_ID  "
// * );
// *
// * /* Tipo de Almac�n
// */
//
// // Parte del sub consulta
// /*
// * if(resourseKey.equalsIgnoreCase("ProdCENoControl"))
// * cadena.append(" AND wa.TipoAlmacen = '"
// * +MWarehouse.TIPOALMACEN_CollectivePrescription+"' "); else
// * cadena.append(" AND wa.TipoAlmacen = '"
// * +MWarehouse.TIPOALMACEN_IndividualPrescription+"' ");
// */
// /*
// * cadena.append(" 			 WHERE r.IsActive = 'Y' ") .append("  )				 ")
// * //Termina subconsulta
// *
// * /* Productos por lotes y fecha de vigencia
// */
// /*
// * .append(
// *
// " AND ( NVL(cte.IsLot,prodOrg.IsLot)='N' OR trunc(ai.GuaranteeDate) >= trunc("
// * ).append(DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx()))).append(") ) ");
// *
// * /* Productos por Especialidades
// */
//
// /*
// * if( !resourseKey.equalsIgnoreCase("ProdRecInd1")
// * &&!resourseKey.equalsIgnoreCase("ProdRecInd2") &&
// * !resourseKey.equalsIgnoreCase("ProdRecInd3") &&
// * !resourseKey.equalsIgnoreCase("ProdRecInd6") &&
// * !resourseKey.equalsIgnoreCase("ProdFromOneWhsRC") &&
// * !resourseKey.equalsIgnoreCase("ProdRecInd4") &&
// * !resourseKey.equalsIgnoreCase("ProdRecInd5") &&
// * !resourseKey.equalsIgnoreCase("ProdCEControl5") &&
// * !resourseKey.equalsIgnoreCase("ProdCEControl4")) {
// * cadena.append(" AND ( ") .append("  	 ( r.M_Product_ID IN (  ")
// * .append("  	 SELECT pe.M_Product_ID FROM EXME_ProductEsp pe  ")
// * .append("  	 WHERE pe.EXME_Especialidad_ID =  ? "
// * )//.append(especialidad)#4 // Lama .append("  	 ) ") .append("  	 OR  ")
// * .append("  	 ( r.M_Product_ID NOT IN (  ")
// * .append("  	 SELECT pe.M_Product_ID FROM EXME_ProductEsp pe  ")
// * .append("  	 WHERE pe.EXME_Especialidad_ID <>  ? "
// * )//.append(especialidad)#5 // Lama .append("  	 )  ") .append("  ) "); }
// *
// * /*Productos por nivel de control del m�dico
// */
//
// // Productos para doctores de CE que pueden recetar de nivel 2 hacia a tras
// /*
// * if(resourseKey.equalsIgnoreCase("ProdCEControl2") ||
// * resourseKey.equalsIgnoreCase("ProdRecInd2")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("') ) "); }
// * //Productos para doctores de CE que pueden recetar de nivel 3 hacia a
// * tras if(resourseKey.equalsIgnoreCase("ProdCEControl3") ||
// * resourseKey.equalsIgnoreCase("ProdRecInd3")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("' ) ) "); }
// *
// * if(resourseKey.equalsIgnoreCase("ProdCEControl4") ||
// * resourseKey.equalsIgnoreCase("ProdRecInd4")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_III).append("') ) "); }
// *
// * if(resourseKey.equalsIgnoreCase("ProdCEControl5") ||
// * resourseKey.equalsIgnoreCase("ProdRecInd5")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_III).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_IV).append("') ) "); }
// *
// * //Productos para doctores de CE que pueden recetar productos no
// * controlados if(resourseKey.equalsIgnoreCase("ProdCENoControl") ||
// * resourseKey.equalsIgnoreCase("ProdRecInd6")){
// * cadena.append(" AND ( p.Itemclass IS NULL OR p.Itemclass NOT IN ('")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_1).append("', '")
// * .append(X_M_Product.ITEMCLASS_Misc_Controlled_2).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_III).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_II).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_IV).append("', '")
// * .append(X_M_Product.ITEMCLASS_Schedule_V).append("') ) "); }
// *
// * cadena.append(" GROUP BY p.m_product_id, p.value, p.name, p.description ")
// * ; }
// *
// * s_log.log(Level.FINEST, "MEXMEPedidosRectas.getMedicationList.sql > " +
// * cadena.toString()); //System.out.println("Query >"+ cadena.toString());
// *
// * try { pstmt = DB.prepareStatement(cadena.toString(), null);
// * if(!resourseKey.endsWith("SF")){ pstmt.setInt(1,
// * Env.getAD_Client_ID(ctx)); pstmt.setInt(2, almacen); pstmt.setInt(3,
// * almacen); if( !resourseKey.equalsIgnoreCase("ProdRecInd1")
// * &&!resourseKey.equalsIgnoreCase("ProdRecInd2") &&
// * !resourseKey.equalsIgnoreCase("ProdRecInd3") &&
// * !resourseKey.equalsIgnoreCase("ProdRecInd6") &&
// * !resourseKey.equalsIgnoreCase("ProdFromOneWhsRC") &&
// * !resourseKey.equalsIgnoreCase("ProdRecInd4") &&
// * !resourseKey.equalsIgnoreCase("ProdRecInd5") &&
// * !resourseKey.equalsIgnoreCase("ProdCEControl5") &&
// * !resourseKey.equalsIgnoreCase("ProdCEControl4")) { pstmt.setInt(4,
// * especialidad); pstmt.setInt(5, especialidad); } } res =
// * pstmt.executeQuery();
// *
// * while (res.next()) { list.add(new MEXMEProduct(ctx, res.getInt(1),
// * null)); }
// *
// * } catch (Exception e) { s_log.log(Level.SEVERE, e.getLocalizedMessage(),
// * e); }finally { DB.close(res, pstmt); }
// *
// * return list;
// *
// * }
// */
// private static final String NO_INSERT_EJEC = "error.citas.noInsertEjec";
//
// /**
// * Inserta la solicitudes de productos para el almacen de configuracion
// * (mejoras.getM_Warehouse_ID())
// *
// * @param ctx
// * @param paciente
// * @param surFarm
// * @param EXME_ActPaciente_ID
// * @param prioridadInd
// * @param EXME_Medico_ID
// * @param medName
// * @param fechaform
// * @param horaCadena
// * @param EXME_CtaPac_ID
// * @param pServView
// * @param farmaciaId
// * @param fechaValida
// * @param trxName
// * @return EXME_ActPacienteIndH_ID
// * @throws Exception
// *
// * public MEXMEActPacienteIndH insertarMovimientosMed(Properties
// * ctx, MEXMEPaciente paciente, int EXME_ActPaciente_ID, String
// * prioridadInd, int EXME_Medico_ID, String medName, String
// * fechaform, String horaCadena, int EXME_CtaPac_ID, int
// * farmaciaId, Timestamp fechaValida,List<ServicioView>
// * lstDetalle , String trxName) throws Exception { return
// * insertarMovimientosMed( ctx, paciente, EXME_ActPaciente_ID,
// * prioridadInd, EXME_Medico_ID, medName, fechaform, horaCadena,
// * EXME_CtaPac_ID, farmaciaId, fechaValida, lstDetalle ,
// * trxName, false); }
// */
// public MEXMEActPacienteIndH insertarMovimientosMed(Properties ctx,
// MEXMEPaciente paciente, int EXME_ActPaciente_ID,
// String prioridadInd, int EXME_Medico_ID, String medName,
// String fechaform, String horaCadena, int EXME_CtaPac_ID,
// int farmaciaId, Timestamp fechaValida,
// List<ServicioView> lstDetalle, String trxName, boolean isServiceTP)
// throws Exception {
//
// if (lstDetalle == null || lstDetalle.size() <= 0)
// return null;
//
// // Crea las lineas del encabezado
// MEXMEActPacienteIndH actPacIndH = insertActPacientInd(ctx, paciente,
// EXME_ActPaciente_ID, 0, prioridadInd, EXME_Medico_ID, medName,
// fechaform, horaCadena, EXME_CtaPac_ID, farmaciaId, fechaValida,
// Constantes.RECETA, lstDetalle, trxName, isServiceTP);
//
// if (actPacIndH == null) {
// setLstErrors(getErrors());
// return null;
// }
//
// actPacIndH.setPreAlta(this.preAlta);
//
// // Guardamos el encabezado
// if (!actPacIndH.save()) {
// getLstErrors().add(
// new ModelError(ModelError.TIPOERROR_Error, "msj.error"));
// return null;
// }
//
// // Lineas creadas para este header
// actPacIndH.setLstActPacInd(getLstActPacInd());
// return actPacIndH;
// }
//
// /**
// * Inserta la solicitudes de productos para el almacen de configuracion
// * (mejoras.getM_Warehouse_ID())
// *
// * @param ctx
// * @param paciente
// * @param surFarm
// * @param EXME_ActPaciente_ID
// * @param prioridadInd
// * @param EXME_Medico_ID
// * @param medName
// * @param fechaform
// * @param horaCadena
// * @param EXME_CtaPac_ID
// * @param planMed
// * @param farmaciaId
// * @param fechaValida
// * @param trxName
// * @return EXME_ActPacienteIndH_ID
// * @throws Exception
// */
// public MEXMEActPacienteIndH insertarMovimientosImm(Properties ctx,
// MEXMEPaciente paciente, int EXME_ActPaciente_ID,
// int EXME_Medico_ID, String medName, int EXME_CtaPac_ID,
// ServicioView serv, String trxName) throws Exception {
//
// MEXMEMejoras mejoras = MEXMEMejoras.get(ctx);
//
// List<ServicioView> list = new ArrayList<ServicioView>();
// list.add(serv);
//
// // Se crea el encabezado de la indicacion medica los detalles
// MEXMEActPacienteIndH actPacIndH = insertActPacientInd(ctx, paciente,
// EXME_ActPaciente_ID, mejoras.getM_Warehouse_ID(), "5",
// EXME_Medico_ID, medName, "", "", EXME_CtaPac_ID, 0, null,
// Constantes.VACUNA, list, trxName);
//
// // Crea las lineas del encabezado
// if (actPacIndH == null) {
// setLstErrors(getErrors());
// return null;
// }
//
// // Guardamos el encabezado
// if (!actPacIndH.save()) {
// throw new SQLException("error.citas.noInsertEjec");
// }
//
// // Lineas creadas para este header
// actPacIndH.setLstActPacInd(getLstActPacInd());
// return actPacIndH;
// }
//
// /********/
//
// /*
// * Properties ctx, paciente, true, int EXME_ActPaciente_ID,
// * Env.getM_Warehouse_ID(ctx), String prioridadInd, int EXME_Medico_ID,
// * String medName,
// *
// * null,null, int EXME_CtaPac_ID, int farmaciaId, null, Constantes.VACUNA,
// * String trxName)
// */
// private MEXMEActPacienteIndH insertActPacientIndH(Properties ctx,
// MEXMEPaciente paciente, int EXME_ActPaciente_ID,
// int M_Warehouse_ID, String prioridadInd, int EXME_Medico_ID,
// String medName, String fechaform, String horaCadena,
// int EXME_CtaPac_ID, int farmaciaId, Timestamp fechaValid,
// String tipoDoc, String trxName, boolean isServTP)
//
// throws Exception {
//
// MEXMEActPacienteIndH datos = new MEXMEActPacienteIndH(ctx, 0, trxName);
// try {
// if (datos.get_TrxName() == null) {
// datos.set_TrxName(trxName);
// }
//
// datos.set_Value(MEXMEActPacienteIndH.COLUMNNAME_M_PriceList_ID,
// datos.getMPriceListID(Env.getM_PriceList_ID(ctx)));
//
// // asignamos la lista de precios del contexto
// if(datos.getM_PriceList_ID()<=0){
// datos.setM_PriceList_ID(Env.getM_PriceList_ID(ctx));
// }
// // la moneda y si incluye impuesto de la lista de precios
// MPriceList lista = new MPriceList(ctx, datos.getM_PriceList_ID(),
// null);// Lama
// datos.setC_Currency_ID(lista.getC_Currency_ID());
// datos.setIsTaxIncluded(lista.isTaxIncluded());
//
// // el socio y ubicacion del paciente
// datos.setC_BPartner_ID(paciente.getC_BPartner_ID());
// datos.setC_Location_ID(paciente.getC_Location_ID());
//
// // asignamos el tipo de documento 'Receta Medica'
// if (tipoDoc.equals(Constantes.RECETA)) {
// datos.setC_DocType_ID(MEXMEDocType.getOfName(ctx,
// Constantes.RECETA, null));// Lama
// } else if (tipoDoc.equals(Constantes.SERVICIO)) {
// datos.setC_DocType_ID(MEXMEDocType.getOfName(ctx,
// Constantes.SERVICIO, null));// Lama
// } else if (tipoDoc.equals(Constantes.VACUNA)) {
// MEXMEConfigDocType mDocType = MEXMEConfigDocType.get(ctx, null);
// if (mDocType != null) {
// datos.setC_DocType_ID(mDocType.getC_DocTypeVacuna_ID());
// }
// }
//
// datos.setC_DocTypeTarget_ID(datos.getC_DocType_ID());
// datos.setDateAcct(DB.getTimestampForOrg(ctx));
//
// // act paciente
// datos.setEXME_ActPaciente_ID(EXME_ActPaciente_ID);
//
// // el consecutivo de la secuencia
// // datos.setDocumentNo(DB.getDocumentNo((int)datos.getC_DocType_ID(),
// // null));
//
// // tipo de dato selecc0ionado
// datos.setIsService(isServTP);
//
// // y el almacen seleccionado
// datos.setM_Warehouse_ID(M_Warehouse_ID);
// datos.setM_Warehouse_Sol_ID(Env.getM_Warehouse_ID(ctx));
//
// // la organizacion transaccional de la estacion de servicio de
// // acceso es decir quien esta solicitando
// int org = MEXMEEstServ.getEstServAlmOrgTrx(ctx,
// Env.getM_Warehouse_ID(ctx));
// datos.setAD_OrgTrx_ID(org);
//
// // la prioridad del pedido
// datos.setPriorityRule(prioridadInd);
// datos.setDateOrdered(DB.getTimestampForOrg(ctx));
// datos.setEXME_Medico_ID(EXME_Medico_ID);
// datos.setMedicoNombre(medName);
// datos.setEXME_CtaPac_ID(EXME_CtaPac_ID);
//
// if (fechaform != null && !fechaform.equalsIgnoreCase("")
// && horaCadena != null && !horaCadena.equalsIgnoreCase("")) {
//
// StringBuilder fecha = new StringBuilder(fechaform);
//
// if (horaCadena.length() > 4) {
// fecha.append(" ").append(horaCadena.substring(0, 2))
// .append(":").append(horaCadena.substring(3, 5));
// } else {
// fecha.append(" ").append(horaCadena.substring(0, 2))
// .append(":").append(horaCadena.substring(2, 4));
// }
//
// datos.setDatePromised(new Timestamp(Constantes
// .getSdfFechaHora().parse(fecha.toString()).getTime()));
// } else {
// datos.setDatePromised(DB.getTimestampForOrg(ctx));
// }
//
// if (fechaValid != null) {
// datos.setValidFrom(fechaValid);
// }
//
// if (farmaciaId > 0) {
// datos.setEXME_Farmacia_ID(farmaciaId);
// }
//
// if (!datos.save()) {
// throw new SQLException(NO_INSERT_EJEC);
// }
//
// } catch (Exception e) {
// s_log.log(Level.SEVERE, "insertActPacientIndH : ", e);
// throw new Exception("error.citas.setDatosIndicacionesH");
// }
//
// return datos;
//
// }
//
// /**
// * Establecemos los datos del encabezado de indicaciones
// *
// * @param ctx
// * El contexto de la aplicacion
// * @param forma
// * El bean de forma
// * @param ctx
// * El contexto de la aplicacion
// * @param request
// * La solicitud HTTP que estamos procesando
// * @return Una lista de objetos ActPacienteInd con los datos a insertar
// */
// private MEXMEActPacienteIndH insertActPacientInd(Properties ctx,
// MEXMEPaciente paciente, int EXME_ActPaciente_ID,
// int M_Warehouse_ID, String prioridadInd, int EXME_Medico_ID,
// String medName, String fechaform, String horaCadena,
// int EXME_CtaPac_ID, int farmaciaId, Timestamp fechaValida,
// String doc, List<ServicioView> lstIndica, String trxName)
// throws Exception {
// return insertActPacientInd(ctx, paciente, EXME_ActPaciente_ID,
// M_Warehouse_ID, prioridadInd, EXME_Medico_ID, medName,
// fechaform, horaCadena, EXME_CtaPac_ID, farmaciaId, fechaValida,
// doc, lstIndica, trxName, false);
// }
//
// private MEXMEActPacienteIndH insertActPacientInd(Properties ctx,
// MEXMEPaciente paciente, int EXME_ActPaciente_ID,
// int M_Warehouse_ID, String prioridadInd, int EXME_Medico_ID,
// String medName, String fechaform, String horaCadena,
// int EXME_CtaPac_ID, int farmaciaId, Timestamp fechaValida,
// String doc, List<ServicioView> lstIndica, String trxName,
// boolean isServTP) throws Exception {
//
// MEXMEEstServ estServLogeo = new MEXMEEstServ(ctx,
// Env.getEXME_EstServ_ID(ctx), null);
// MEXMEActPacienteIndH encab = null;
// MEXMEMejoras mejoras = MEXMEMejoras.get(ctx);
//
// String cantTomar = Utilerias.getMessage(ctx, null,
// "citas.indica.cantTomar");
// String vecesDia = Utilerias.getMessage(ctx, null,
// "citas.indica.vecesDiaSA");
// String numDias = Utilerias.getMessage(ctx, null,
// "citas.indica.numDiasSA");
//
// try {
// /*
// * if(encab.get_TrxName() == null){ encab.set_TrxName(trxName); }
// */
//
// BigDecimal totalSinImp = Env.ZERO;
// BigDecimal totalConImp = Env.ZERO;
// boolean surtir = false;
//
// // ahora guardamos el detalle de la ejecucion de la consulta
// for (int i = 0; i < lstIndica.size(); i++) {
//
// if (i == 0) {
// encab = // Se crea el encabezado de la indicacion medica los
// // detalles
// insertActPacientIndH(ctx, paciente, EXME_ActPaciente_ID,
// M_Warehouse_ID <= 0 ? mejoras.getM_Warehouse_ID()
// : M_Warehouse_ID, prioridadInd,
// EXME_Medico_ID, medName, fechaform, horaCadena,
// EXME_CtaPac_ID, farmaciaId, fechaValida, doc,
// trxName, isServTP);
// }
//
// // nuevo objeto de tipo ActPacienteInd
// MEXMEActPacienteInd datos = new MEXMEActPacienteInd(ctx, 0, trxName);
//
// // recuperamos los valores de la lista
// ServicioView indica = (ServicioView) lstIndica.get(i);
//
// if (indica.getEXME_ActPacienteInd_ID() > 0) {
// MEXMEActPacienteInd lineOrig = new MEXMEActPacienteInd(ctx,
// indica.getEXME_ActPacienteInd_ID(), trxName);
//
// MEXMEActPacienteIndH headerOrig = new MEXMEActPacienteIndH(ctx,
// lineOrig.getEXME_ActPacienteIndH_ID(), trxName);
// headerOrig.setRef_ActPacienteIndH_ID(encab
// .getEXME_ActPacienteIndH_ID());
//
// if (!headerOrig.save()) {
// s_log.log(Level.SEVERE, "Can not save headerOrig",
// CLogger.retrieveError());
// }
// }
//
// // if(!(indica.getEXME_ActPacienteInd_ID()>0 &&
// // indica.getMotivoCancel()>0)){
// datos.setLine((i + 1) * 10);
// datos.setDateOrdered(indica.getFecha() != null ? indica
// .getFecha() : DB.getTimestampForOrg(ctx));
// datos.setDateDelivered(indica.getFechaApli() != null ? indica
// .getFechaApli() : DB.getTimestampForOrg(ctx));
//
// // se obtienen los mensajes desde el inicio .- Lama
// datos.setDescription(cantTomar + ": " + indica.getCantTomar()
// + ". " + vecesDia + ": " + indica.getVecesDia() + ". "
// + numDias + ": " + indica.getNumDias() + ". "
// + indica.getDescripcion());
//
// datos.setM_Product_ID((int) indica.getProdID());
// datos.setM_Warehouse_ID((int) encab.getM_Warehouse_ID());
// datos.setC_UOM_ID((int) indica.getUdm());
// datos.setQtyDelivered(new BigDecimal(indica.getCantidad()));
// datos.setQtyOrdered(indica.getQtyOrdered());
// datos.setQuantity_txt(indica.getQuantity_txt());
// datos.setC_Currency_ID((int) encab.getC_Currency_ID());
// datos.setTipoArea(estServLogeo.getTipoArea());
// datos.setEXME_Area_ID(estServLogeo.getEXME_Area_ID());
//
// datos.setEXME_Dosis_ID(indica.getEXME_Dosis_ID());
// datos.setMotivoCancelacion(indica.getMotivoCancelacion());
// datos.setEXME_ViasAdministracion_ID(indica
// .getEXME_ViaAdministracion_ID());
// datos.setTipoSurtido(indica.getTipoSurtidoValue());
// // datos.setEXME_MotivoCancel_ID(indica.getMotivoCancel());
// datos.setResurtidos(indica.getResurtidos());
// datos.setCantidadToma(new BigDecimal(indica.getCantTomar()));
// datos.setVecesDia(new BigDecimal(indica.getVecesDia()));
// datos.setNumDias(new BigDecimal(indica.getNumDias()));
// datos.setEXME_Medico_ID(encab.getEXME_Medico_ID());
// datos.setTomadoCasa(indica.isTomadoCasa());
// datos.setIsTodayService(indica.isTodayService());
// datos.setNameServ(indica.getProdName());
//
// if (datos.getM_Product_ID() > 0 && indica.getSurtir()) {
//
// MPrecios precios = PreciosVenta.precioProdVta(ctx,
// datos.getTipoArea(), datos.getM_Product_ID(),
// new BigDecimal(indica.getCantidad()),
// datos.getC_UOM_ID(), PreciosVenta.PVCE,
// encab.getM_Warehouse_Sol_ID(),
// encab.getM_Warehouse_ID(), datos.getEXME_Area_ID(),
// datos.getDateOrdered(), true, null);
//
// // Expert. Precios en cero
// /*
// * if (Env.ZERO.compareTo(precios.getPriceStd())>=0) {
// * errors.add( ActionErrors.GLOBAL_ERROR, new
// * ActionError("error.preciosVenta.precios") ); return
// * errors; }
// */
// datos = precios.preciosActual(datos);
// }
//
// datos.setIsDAW(indica.isDAW());
// datos.setIsDEA(indica.isDEA());
// datos.setIsPRN(indica.isPRN());
// datos.setEXME_Farmacia_ID(indica.getEXME_Farmacia_ID());
// datos.setNumDias(new BigDecimal(indica.getNumDias()));
// datos.setEstatus(indica.getEstatus() != null ? indica
// .getEstatus()
// : MEXMEActPacienteInd.ESTATUS_CompletedService);//
// MEXMEActPacienteInd.ESTATUS_RequestedService);
//
// datos.setEXME_ActPacienteIndH_ID(encab
// .getEXME_ActPacienteIndH_ID());
//
// // Organizacion del almacen al que se le esta solicitando
// int org = MEXMEEstServ.getEstServAlmOrgTrx(ctx,
// (int) encab.getM_Warehouse_ID());
// datos.setAD_OrgTrx_ID(org);
// datos.setSurtir(indica.getSurtir());
//
// // lhernandez.12042011: se agrega genProduct
// if ((int) indica.getProdID() > 0) {
// MProduct prod = new MProduct(ctx, (int) indica.getProdID(),
// null);
// datos.setEXME_GenProduct_ID(prod.getEXME_GenProduct_ID());
// if (prod.getProdOrg() != null){
// datos.setEXME_RevenueCode_ID(prod.getProdOrg()
// .getEXME_RevenueCode_ID());
// }
// } else {
// datos.setEXME_GenProduct_ID(indica.getEXME_GenProduct_ID());
// }
//
// // datos.setEstatus(MEXMEActPacienteInd.ESTATUS_CompletedService);
// datos.setLote(indica.getLote());
// if (indica.isProcedure()) {
// datos.setAD_Org_Dest_ID(indica.getAD_Org_Dest_ID());
// if (indica.getFecha() != null) {
// datos.setDateOrdered(indica.getFecha());
// } else {
// datos.setDateOrdered(DB.getTimestampForOrg(ctx));
// }
// datos.setProveedor(indica.getProvider());
// datos.setConsultingProvider(indica.getConsultingProvider());
// datos.setEXME_Medico_ID(indica.getEXME_Medico_ID());
// datos.setPriorityRule(indica.getPrioridadID());
// datos.setComments(indica.getComments());
// datos.setOtherInstructions(indica.getOtherInstructions());
// datos.setEXME_Diagnostico_ID(indica.getDiagnosis1ID());
// datos.setEXME_Diagnostico2_ID(indica.getDiagnosis2ID());
// datos.setEXME_Diagnostico3_ID(indica.getDiagnosis3ID());
// }
// if (!datos.save()) {
// errors.add(new ModelError(ModelError.TIPOERROR_Error,
// "error.citas.setDatosIndicaciones"));
// return null;
// }
// indica.setEXME_ActPacienteInd_ID(datos
// .getEXME_ActPacienteInd_ID());
//
// lstActPacInd.add(datos);
//
// totalSinImp = totalSinImp.add(datos.getLineNetAmt());
// totalConImp = totalConImp.add(datos.getLineNetAmt()).add(
// datos.getTotalImp());
//
// if (indica.getSurtir()) {
// surtir = true; // con solo uno se uqeda como drafted
// }
//
// // Lama: se guarda la relacion PrescRxDet / ActPacienteInd
// // (Office Visit)
// // if (indica.getPrescRxDet() != null &&
// // !indica.getPrescRxDet().relatePrescInd(datos.get_ID())) {
// // errors.add(new ModelError(ModelError.TIPOERROR_Error,
// // "error.notasMed.servicios.detalle"));
// // return null;
// // }
// if (indica.getPrescRxDet() != null) {
// indica.getPrescRxDet().setEXME_ActPacienteInd_ID(
// datos.get_ID());
// indica.getPrescRxDet().save(trxName);
// }
//
// // }
// }// fin lineas
//
// if (encab != null) {
// encab.setTotalLines(totalSinImp);
// encab.setGrandTotal(totalConImp);
//
// // Si existen lineas a agregar
// if (lstIndica != null && lstIndica.size() > 0) {
// if (surtir) {
// encab.setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Completed);
// encab.setDocAction(MEXMEActPacienteIndH.DOCACTION_Close);
// encab.setEstatus(MEXMEActPacienteIndH.ESTATUS_ServiceOrdersCompleted);
// encab.setIsDelivered(true);
// encab.setCompleto(true);
// } else {
// encab.setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Drafted);
// encab.setDocAction(MEXMEActPacienteIndH.DOCACTION_Complete);
// encab.setEstatus(MEXMEActPacienteIndH.ESTATUS_OrdersOfServiceRequested);
// encab.setIsDelivered(false);
// encab.setCompleto(false);
// }
// }
//
// if (!encab.save()) {
// errors.add(new ModelError(ModelError.TIPOERROR_Error,
// "error.citas.setDatosIndicaciones"));
// return null;
// }
// }
// } catch (Exception e) {
// s_log.log(Level.SEVERE, "insertActPacienteInd", e);
// errors.add(new ModelError(ModelError.TIPOERROR_Error,
// "error.citas.setDatosIndicaciones"));
// return null;
// }
// return encab;
// }
//
// private List<ModelError> errors = new ArrayList<ModelError>();
//
// public List<ModelError> getErrors() {
// return this.errors;
// }
//
// public void setErrors(List<ModelError> errors) {
// this.errors = errors;
// }
//
// private List<MEXMEActPacienteInd> lstActPacInd = new
// ArrayList<MEXMEActPacienteInd>();
//
// public List<MEXMEActPacienteInd> getLstActPacInd() {
// return this.lstActPacInd;
// }
//
// public void setLstActPacInd(List<MEXMEActPacienteInd> lstActPacInd) {
// this.lstActPacInd = lstActPacInd;
// }
//
// /*
// * public static void insertaActPacienteDet(Properties ctx,
// * ArrayList<Pregunta_VO> map, Integer medico, Integer paciente, Integer
// * folio, String com, Integer cita, int EXME_Enfermeria_ID,int
// * EXME_ActPaciente_ID,int EXME_Especialidad_ID, int liga, String
// * estadoActual, String trxName) throws Exception {
// *
// * s_log.log(Level.INFO, "***** Insertando en EXME_ActPaciente ***** ");
// *
// * try {
// *
// * Iterator<Pregunta_VO> itrValor = map.iterator(); Pregunta_VO vo = null;
// * while (itrValor.hasNext()) { vo = (Pregunta_VO) itrValor.next();
// *
// * //tcuest.setEXME_Paciente_ID(paciente.intValue()); //if (cita != null &&
// * cita.intValue() > 0) { //tcuest.setEXME_CitaMedica_ID(cita.intValue());
// * //}
// *
// * try{ //MEXMETCuest temp = (MEXMETCuest)cuestionario.get(i);
// * MActPacienteDet det = new MActPacienteDet(ctx, 0, trxName);
// * det.setDescription("");//tcuest.setDescription("");
// *
// * if (medico != null) det.setEXME_Medico_ID(medico.intValue());
// * //tcuest.setEXME_Medico_ID(medico.intValue());
// *
// * if (vo.getCuestionarioId() != null) {
// * det.setEXME_Cuestionario_ID(vo.getCuestionarioId().intValue()); }
// *
// * det.setEXME_Pregunta_ID(vo.getId());//tcuest.setEXME_Pregunta_ID(vo.getId(
// * ));
// *
// * if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_Image) &&
// * (vo.getRespuestaI() != null || vo.getByteArr() != null)) { String
// * fileName = vo.saveImagen();
// * det.setRespuesta(fileName);//tcuest.setRespuesta(fileName);
// * det.setRutaImagen(fileName);//tcuest.setRutaImagen(fileName); } else { if
// * (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) &&
// * "0".equalsIgnoreCase(vo.getRespuestaCombo())){
// * det.setRespuesta(null);//tcuest.setRespuesta(null); }else{
// * det.setRespuesta
// * (vo.getRespuesta());////tcuest.setRespuesta(vo.getRespuesta()); }
// *
// * if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_TextArea))
// * det.setTextBinary
// * (vo.getRespuesta());//tcuest.setTextBinary(vo.getRespuesta()); }
// *
// * if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_FixedImage)) {
// *
// * det.setRespuesta(vo.getName()); // nombre de la pregunta
// *
// * CuestionarioModel cuest = new CuestionarioModel(ctx, null); // actualizar
// * ID en nombre de archivo (throws exception) int tCuestID =
// * det.getEXME_ActPacienteDet_ID(); int pregID = vo.getId() != null
// * ?vo.getId().intValue() : 0; int seq = vo.getSecuencia() != null ?
// * vo.getSecuencia().intValue() : 0; int cuestID = vo.getCuestionarioId() !=
// * null ? vo.getCuestionarioId().intValue() : 0;
// *
// * det.setRutaImagen(cuest.updateLine(tCuestID, pregID, cuestID, seq)); }
// *
// * if(EXME_ActPaciente_ID > 0)
// * det.setEXME_Especialidad_ID(EXME_Especialidad_ID);
// *
// * if(com == null) det.setConfidencial("T"); //tcuest.setConfidencial(com !=
// * null ? com : "T"); else det.setConfidencial(com);
// * //tcuest.setConfidencial(com != null ? com : "T");
// *
// *
// * if (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_OptionList) &&
// * vo.getRespuestaCombo() != null) {
// * det.setPregunta_Lista_Value(vo.getRespuestaCombo
// * ());//tcuest.setPregunta_Lista_Value(vo.getRespuestaCombo()); }
// *
// * if (vo.getSecuencia() != null) { det.setSecuencia(new
// * BigDecimal(vo.getSecuencia
// * ().toString()).intValue());//tcuest.setSecuencia(new
// * BigDecimal(vo.getSecuencia().toString())); } else { det.setSecuencia(new
// * BigDecimal(0).intValue());//tcuest.setSecuencia(new BigDecimal("0")); }
// *
// * if (vo.getComentario() != null) { det.setDescription(vo.getComentario());
// * }
// *
// * det.setEXME_ActPaciente_ID(EXME_ActPaciente_ID); det.setFecha(new
// * Timestamp(System.currentTimeMillis()));//tcuest.setFecha(new
// * Timestamp(System.currentTimeMillis()));
// * det.setFolio(folio.intValue());//tcuest.setFolio(folio.intValue());
// * det.setEXME_EstServ_ID
// * (Env.getContextAsInt(ctx,"#EXME_EstServ_ID"));//tcuest
// * .setEXME_EstServ_ID(Env.getContextAsInt(ctx, "#EXME_EstServ_ID"));
// * det.setEstadoSalud(estadoActual);
// *
// * if(liga == 3 || liga == 4 || liga == 5 || liga == 6)//Lama : merge
// * revision #12212 det.setEsNotaMedica(true);//tcuest.setEsNotaMedica(true);
// *
// * if(EXME_Enfermeria_ID > 0)
// * det.setEXME_Enfermeria_ID(EXME_Enfermeria_ID);/
// * /tcuest.setEXME_Enfermeria_ID(EXME_Enfermeria_ID); if
// * (!det.save(trxName)) {
// *
// * throw new Exception("error.cuest.guardar");
// *
// * } else {
// *
// * //Liz de la Garza- Actualizacion de las imagenes if
// * (vo.getTipoDato().equalsIgnoreCase(MPregunta.TIPODATO_FixedImage) &&
// * det.getRutaImagen()!=null) {
// *
// * CuestionarioModel cuest = new CuestionarioModel(ctx, null); // actualizar
// * ID en nombre de archivo (throws exception) int tCuestID =
// * det.getEXME_ActPacienteDet_ID(); int pregID = vo.getId() != null
// * ?vo.getId().intValue() : 0; int seq = vo.getSecuencia() != null ?
// * vo.getSecuencia().intValue() : 0; int cuestID = vo.getCuestionarioId() !=
// * null ? vo.getCuestionarioId().intValue() : 0; // recupera la imagen
// * temporal a insertar InputStream input =
// * cuest.getFile(det.getRutaImagen());// (throws exception)
// * MEXMETCuest.updateImagen(ctx, MActPacienteDet.Table_Name, "ImagenBinary",
// * tCuestID, paciente.intValue(), medico.intValue(), cuestID, pregID, seq,
// * input, trxName,Env.getAD_User_ID(ctx)); }
// *
// *
// * } } catch(Exception e){ s_log.log(Level.SEVERE, "insertaActPacienteDet",
// * e); throw new Exception(e.getMessage()); }
// *
// *
// *
// * }
// *
// * } catch (Exception e) { s_log.log(Level.SEVERE, "insertaActPacienteDet",
// * e); throw new Exception(e.getMessage()); }
// *
// *
// * }
// */
// }

