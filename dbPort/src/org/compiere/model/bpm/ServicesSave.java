package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.ActPacienteIndView;
import org.compiere.model.I_EXME_ActPacienteIndH;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MConfigEC;
import org.compiere.model.MCountry;
import org.compiere.model.MEXMEActPaciente;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMEConfigPre;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEDocType;
import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MEXMEI18N;
import org.compiere.model.MEXMEInterfaceSender;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MIntervencion;
import org.compiere.model.MPrecios;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MWarehouse;
import org.compiere.model.ServicioView;
import org.compiere.model.X_EXME_ActPacienteInd;
import org.compiere.model.X_EXME_ActPacienteIndH;
import org.compiere.model.X_EXME_Area;
import org.compiere.model.X_M_Warehouse;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;
import org.compiere.util.confHL7.MessageGenerator;

import com.mirth.connect.client.core.ClientException;

/**
 * Guarda las solicitudes de servicios
 * 
 * @author Twry
 *         Modified by Lama
 *         Modified by Lorena Lama, Oct 2013
 *         <a href="http://control.ecaresoft.com/card/931/">Card <b>#931</b> <i>Interfases de Radiologia con TeleradTech</i></a>
 */
public class ServicesSave {

	/**
	 * Utilizado para generacion de encabezados, agrupa la lista por Almacen/fecha/prioridad
	 * 
	 * @author Lorena Lama
	 */
	public static class OrderHeader {

		/** Agrupa la lista por Almacen(interno/externo)/fecha/prioridad */
		public static List<OrderHeader> getGroupedList(final List<ServicioView> lst) {
			final List<OrderHeader> list = new ArrayList<OrderHeader>();
			for (final ServicioView view : lst) {
				final OrderHeader header = new OrderHeader(view);
				final int index = list.indexOf(header);
				if (index < 0) {
					header.getDetail().add(view);
					list.add(header);
				} else {
					list.get(index).getDetail().add(view);
				}
			}
			return list;
		}

		private final int					warehouse;
		private final long					orderDate;
		private final List<ServicioView>	detail	= new ArrayList<ServicioView>();
		private final String				priority;
		private String						provider;

		public OrderHeader(final ServicioView serv) {
			super();
			this.orderDate = serv.getFecha() == null ? 0 : Long.valueOf(Constantes.getCustom("yyyyMMddHHmm").format(serv.getFecha()));
			this.priority = serv.getPrioridadID();
			if (serv.getSurtir()) {// RQM #4161
				this.warehouse = serv.getAlmaServ();
			} else if (serv.getAD_Org_Dest_ID() > 0) {
				this.provider = String.valueOf(serv.getAD_Org_Dest_ID());
				this.warehouse = 0;
			} else if (StringUtils.isNotBlank(serv.getProvider())) {
				this.provider = StringUtils.replaceEach(serv.getProvider().toLowerCase(), Constantes.arr1, Constantes.arr2).replaceAll("[^\\w]", "");
				this.warehouse = 0;
			} else {
				this.provider = "";
				this.warehouse = serv.getAlmaServ();
			}
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final OrderHeader other = (OrderHeader) obj;
			if (warehouse != other.warehouse || orderDate != other.orderDate || !StringUtils.equals(priority, other.priority)
				|| !StringUtils.equals(provider, other.provider)) {
				return false;
			}
			return true;
		}

		public List<ServicioView> getDetail() {
			return detail;
		}

		public int getWarehouse() {
			return warehouse;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + warehouse;
			result = prime * result + (int) (orderDate ^ orderDate >>> 32);
			result = prime * result + (priority == null ? 0 : priority.hashCode());
			result = prime * result + (provider == null ? 0 : provider.hashCode());
			return result;
		}

		@Override
		public String toString() {
			return "OrderHeader [warehouse=" + warehouse + ", orderDate=" + orderDate + ", priority=" + priority + ",provider=" + provider + "]";
		}
	}

	/** log */
	private static CLogger						log				= CLogger.getCLogger(ServicesSave.class);
	/** contexto */
	private transient final Properties					ctx;
	/** siempre debe existir una cuenta paciente */
	private transient MEXMECtaPac						objCtaPac;
	/** id encabezado de la cuenta paciente en el expediente */
	private transient int								actvPacienteID;
	/** nombre de transaccion */
	private transient String							trxName;
	/** ID de la referencia medica **/
	private transient int								referralID;
	/** id del medico */
	private transient int								physicianId;
	/** id del especialidad a la que se solicita */
	private transient int								especialidadID;
	/** id del especialidad que solicita */
	private transient int								especialidadSolID;
	/** @deprecated siempre es falso si el registro es editable */
	@Deprecated
//	private transient boolean							esEditable;
	/** lista de precios a utilizar */
	private transient MPriceList						listaPrecios;
	/** actualizacion u insertcion */
	private transient boolean							update;
	/** listado de servicios */
	private List<ServicioView>							servicesAdded;
	/** estacion del contexto como objeto */
	private transient final MEXMEEstServ				estServLogeo;
	/** listado de objetos creados */
	private final transient List<MEXMEActPacienteIndH>	lstActPacIndH	= new ArrayList<MEXMEActPacienteIndH>();
	/** folio por orden */
	private transient int								folio;
	private int											locationId;
	private final int									docType;
	private final MEXMEInterfaceSender					sender;

	private String										orderType;
	private String										readBack;
	/** nombre de transaccion */
	private MEXMEActPacienteIndH						oldActPacIndHToCancel;
	private Timestamp									dateOrdered;

	/**
	 * Constructor.
	 * 
	 * @param ctx contexto
	 */
	public ServicesSave(final Properties pCtx) {
		super();
		ctx = pCtx;
		estServLogeo = new MEXMEEstServ(ctx, Env.getEXME_EstServ_ID(ctx), null);
		sender = MEXMEInterfaceSender.getSenderEstServicio(ctx, null, estServLogeo.get_ID());
		docType = MEXMEDocType.getOfName(ctx, Constantes.SERVICIO, null);
	}

	/** Cierra la transaccion si fue creada localmente */
	private void closeTrx(final Trx trx, final boolean commit, final boolean isLocalTrx) {
		if (isLocalTrx) {
			if (commit) {
				Trx.commit(trx, true);
			} else {
				Trx.rollback(trx, true);
			}
		}
	}

	/** Busca la actividad paciente de la cuenta paciente si no existe la crea */
	private void createActPacienteID() {
		if (objCtaPac == null || objCtaPac.getEXME_CtaPac_ID() <= 0) {
			throw new MedsysException(Utilerias.getMsg(ctx, "captMov.error.getExpediente"));
		} else {
			if (actvPacienteID <= 0) {
				actvPacienteID = MEXMEActPaciente.getForCtaPac(objCtaPac).get_ID();
			}
			if (actvPacienteID <= 0) {
				log.log(Level.WARNING, "***** Insertando en Act. Paciente ***** ");
				if (especialidadID <= 0) {
					throw new MedsysException(Utilerias.getMsg(ctx, "error.citas.especialidad"));
				}
				// Se crea la actividad paciente para el paciente
				final MEXMEActPaciente actPac = new MEXMEActPaciente(ctx, 0, trxName);
				actPac.setEXME_Paciente_ID(objCtaPac.getPaciente().getEXME_Paciente_ID());
				actPac.setFecha(Env.getCurrentDate());
				actPac.setName(Msg.translate(ctx, "EXME_ActPaciente_ID") + ": ");
				actPac.setTipoArea(Env.getTipoArea(ctx));// MActPaciente.TIPOAREA_Ambulatory
				actPac.setEXME_Especialidad_ID(especialidadID);
				if (!actPac.save()) {
					actvPacienteID = 0;
					throw new MedsysException();// "error.citas.noGuardarSolicitud"
				}
				actPac.setName(actPac.getName() + actPac.getEXME_ActPaciente_ID() + " - " + Msg.translate(ctx, "SolServ"));
				if (!actPac.save()) {
					actvPacienteID = 0;
					throw new MedsysException();// "error.citas.noGuardarSolicitud"
				}
				actvPacienteID = actPac.getEXME_ActPaciente_ID();
			}
		}
	}

	/**
	 * Guarda el servicio de la cita medica
	 * 
	 * @param actPacienteID
	 * @param ctaPacID
	 * @param intervencionID
	 * @param configEC
	 * @param appointment
	 * @param trxName
	 * @return
	 */
	public void createAppointmentCharge(final int actPacienteID, 
		final int ctaPacID, final int intervencionID, final MConfigEC configEC,
		final MEXMECitaMedica appointment, final String trxName) throws Exception {
		log.log(Level.INFO, "***** saveConsulta ***** ");
		
		// Card 643 Juan Carranza - Se valido que se generaran correctamente los cargos de los procedimientos y del level of services
		final MEXMEI18N valMex = MEXMEI18N.getFromCountry(ctx, Env.getC_Country_ID(ctx), null);
		final int productId;
		
		if (MCountry.isUSA(ctx) && intervencionID > 0) {
			final MIntervencion intervencion = new MIntervencion(ctx, intervencionID, null);
			productId = intervencion.getM_Product_ID();
		} else if(valMex.isSurtidoInterno()){
			// Buscamos cual seria el producto para facturar como cita del medico
			final MEXMEMedico medico = new MEXMEMedico(ctx, appointment.getEXME_Medico_ID(), null);
			productId = medico.getM_Product_ID();
		} else {
			productId = 0;
		}
//		if (MCountry.isMexico(ctx) && valMex != null && ) {
//			// Buscamos cual seria el producto para facturar como cita del medico
//			final MEXMEMedico medico = new MEXMEMedico(ctx, appointment.getEXME_Medico_ID(), null);
//			productId = medico.getM_Product_ID();
//		} else if (intervencionID > 0) {
//			final MIntervencion intervencion = new MIntervencion(ctx, intervencionID, null);
//			productId = intervencion.getM_Product_ID();
//		} else {
//			productId = 0;
//		}
		// si existe el cargo
		if (productId > 0) {
			// listado con los servicios
			final List<ServicioView> lst = new ArrayList<ServicioView>();
			lst.add(new ServicioView(new MProduct(ctx, productId, null)));
			
			// se lleno getLstActPacienteIndH()
			if (!save(lst, actPacienteID, new MEXMECtaPac(ctx, ctaPacID, trxName), false, trxName)) {
				throw new MedsysException(Utilerias.getMsg(ctx, "error.citas.noExisteIndicacion"));
			}
			// Previamente se lleno getLstActPacienteIndH()
			if (lstActPacIndH == null || lstActPacIndH.isEmpty()) {
				throw new MedsysException(Utilerias.getMsg(ctx, "error.citas.noExisteIndicacion"));
			}
			final MEXMEActPacienteIndH header = lstActPacIndH.get(0);
			// Card #1387 - No duplicar cargo
			appointment.setEXME_ActPacienteIndH_ID(header.getEXME_ActPacienteIndH_ID());
			if (!appointment.save(trxName)) {
				throw new MedsysException();
			}
			
			final List<ActPacienteIndView> lst2 = MEXMEActPacienteInd.getLineasServicio(ctx, header.get_ID(), trxName);
			for (final ActPacienteIndView actPacienteIndView : lst2) {
				actPacienteIndView.setAccion(X_EXME_ActPacienteInd.ESTATUS_CompletedService);
			}
			// Como es un servicio de cita no agrega resultados
			if (!ServicesDelivered.surtir(ctx, false, header, lst2, trxName)) {
				throw new MedsysException(Utilerias.getMsg(ctx, "error.citas.noExisteIndicacion"));
			}
			// Se relaciona y cambian estatus
			if (configEC.isReqFactCE() && appointment.getC_Invoice_ID() > 0) {
				// La cita ya esta facturada por lo que la actividad paciente
				// indh debe indicar que tambien ya esta facturada
				header.setIsInvoiced(true);
				header.setC_Invoice_ID(appointment.getC_Invoice_ID());
				if (!header.save(trxName)) {
					throw new MedsysException();
				}
			}
		} else {
			throw new MedsysException(Utilerias.getMsg(ctx, "error.citas.noExistePreCons"));
		}
	}

	/**
	 * Crea el encabezado de la actividad paciente para el producto
	 * 
	 * @param servView ServicioView con la informacion del header
	 * @param warehouseTo ServicioView que aplica
	 * @return MEXMEActPacienteIndH insertada
	 * @throws Exception
	 */
	private MEXMEActPacienteIndH createHeader(final ServicioView servView, final MWarehouse warehouseTo) {
		// Lama .- Card #931
		if (warehouseTo != null && warehouseTo.isHL7ORM()) {
			if (servView.getHeader() != null //
				&& servView.getHeader().isApproved()) {
				servView.getHeader().set_TrxName(trxName);
				if (servView.getHeader().voidIt() && servView.getHeader().save()) {
					oldActPacIndHToCancel = servView.getHeader();
				} else {
					throw new MedsysException(servView.getHeader().getProcessMsg());
				}
				servView.setEXME_ActPacienteIndH_ID(0);
			}
		} else if (!update) {
			servView.setEXME_ActPacienteIndH_ID(0);
		}
		// creamos la actpaciente header con los detalles de la
		// solicitud de servicio por almacen
		final MEXMEActPacienteIndH header = new MEXMEActPacienteIndH(ctx, servView.getEXME_ActPacienteIndH_ID(), trxName);
		header.setC_DocType_ID(docType);
		header.setC_DocTypeTarget_ID(docType);
		header.setIsService(true);
		header.setEstatus(X_EXME_ActPacienteIndH.ESTATUS_OrdersOfServiceRequested);

		// ctx
		header.setAD_OrgTrx_ID(estServLogeo.getAD_OrgTrx_ID());
		header.setEXME_ActPaciente_ID(actvPacienteID);
		header.setM_Warehouse_Sol_ID(Env.getM_Warehouse_ID(ctx)); // almacen de logeo

		// almacen que aplica / solicita
		if (warehouseTo != null) {
			header.setM_Warehouse_ID(warehouseTo.getM_Warehouse_ID());
			header.setIntervalo(new BigDecimal(warehouseTo.getIntervalo()));
			if (sender != null && sender.isImprimir_CodZebra() && warehouseTo.isHL7ORM() && servView.getImpresoraID() > 0) {
				header.setEXME_Impresora_ID(servView.getImpresoraID());
			}
		}
		// Dates (no editar las existentes)
		if(dateOrdered != null) {
			header.setDatePromised(dateOrdered);
			header.setDateOrdered(dateOrdered);
		} else if (servView.getFecha() == null && header.is_new()) {
			header.setDatePromised(Env.getCurrentDate());
			header.setDateOrdered(Env.getCurrentDate());
		} else if (servView.getFecha() != null) {
			header.setDatePromised(servView.getFecha());
			header.setDateOrdered(servView.getFecha());
		}
		// lista de precios
		if (listaPrecios != null) {
			header.setM_PriceList_ID(listaPrecios.getM_PriceList_ID());
			header.setC_Currency_ID(listaPrecios.getC_Currency_ID());
			header.setIsTaxIncluded(listaPrecios.isTaxIncluded());
		}
		// paciente
		if (objCtaPac != null) {
			header.setC_BPartner_ID(objCtaPac.getC_BPartner_ID());
			header.setC_Location_ID(locationId);
			// Apartir de la cuenta paciente
			header.setEXME_Medico_ID(objCtaPac.getEXME_Medico_ID());
			header.setMedicoNombre(objCtaPac.getMedico().getNombre_Med());
			header.setEXME_CtaPac_ID(objCtaPac.getEXME_CtaPac_ID());
		}
		// Parametros
		header.setEXME_MedicoSol_ID(physicianId);
		header.setEXME_EspecialidadSol_ID(especialidadSolID);

		// Apartir del objeto del header
		header.setDescription(servView.getDescripcion());
		header.setPriorityRule(servView.getPrioridadID());
//		if (esEditable) {
//			header.setParent_ID(servView.getParentID());
//		}
		header.setProtocolo(servView.getProtocolo());
		header.setDiagnostico(servView.getDiagnostico());
		if (header.getDescription() == null) {
			header.setDescription(servView.getNotes());
		} else {
			header.setDescription(header.getDescription() + " " + servView.getNotes());
		}
		header.setEXME_Especimen_ID(servView.getEspecimenID());
		header.setEXME_EspecimenCondicion_ID(servView.getEspecimenCondicionID());
		header.setCompleto(!servView.getSurtir());

		// Lama, card #460 - OrderType, Prescriber, ReadBack
		header.setReadBack(readBack);
		header.setOrderType(orderType);

		// Lama: Agregar encounterForm
		header.setEXME_EncounterForms_ID(servView.getEncounterFormId());

		// Guardamos el header
		if (!header.save()) {
			throw new MedsysException();// "error.citas.noGuardarSolicitud"
		}
		servView.setEXME_ActPacienteIndH_ID(header.getEXME_ActPacienteIndH_ID());
		// El primer encabezado de la orden
		folio = folio == 0 ? header.getEXME_ActPacienteIndH_ID() : folio;

		return header;
	}

	/**
	 * Insertamos la linea del servicios
	 * 
	 * @param header MEXMEActPacienteIndH encabezado
	 * @param servView ServicioView con los datos a insertar
	 * @param warehouseTo MWarehouse que aplica
	 * @return MEXMEActPacienteInd insertada
	 * @throws Exception
	 */
	private MEXMEActPacienteInd createline(final MEXMEActPacienteIndH header, final ServicioView servView, final MWarehouse warehouseTo) {
		//Lama .- Card #931
		if(warehouseTo != null && warehouseTo.isHL7ORM()) {
			if (servView.getModel() != null //
				&& servView.getHeader() != null //
				&& servView.getHeader().isApproved()) {
				servView.getModel().set_TrxName(trxName);
				if (!servView.getModel().voidIt()) {
					throw new MedsysException(servView.getModel().getProcessMsg());
				}
				servView.setEXME_ActPacienteInd_ID(0);
			}
		} else if (!update) { 
			servView.setEXME_ActPacienteInd_ID(0);
		}
		final MEXMEActPacienteInd actPacInd = new MEXMEActPacienteInd(header.getCtx(), servView.getEXME_ActPacienteInd_ID(), header.get_TrxName());
		actPacInd.setLine(1);// (index + 1) * 10);

		// header (ctx)
		actPacInd.setEXME_Medico_ID(servView.getEXME_Medico_ID());
		actPacInd.setActPacienteID(header.getEXME_ActPaciente_ID());
		actPacInd.setC_Currency_ID(header.getC_Currency_ID());
		actPacInd.setEXME_ActPacienteIndH_ID(header.getEXME_ActPacienteIndH_ID());

		// Date : Lama
		if (header.getDatePromised() == null && servView.getFecha() == null) {
			if (actPacInd.getDatePromised() == null) {
				actPacInd.setDatePromised(Env.getCurrentDate());
			}
			if (actPacInd.getDateOrdered() == null) {
				actPacInd.setDateOrdered(Env.getCurrentDate());
			}
		} else if (servView.getFecha() == null) {
			actPacInd.setDatePromised(header.getDatePromised());
			actPacInd.setDateOrdered(header.getDatePromised());
		} else {
			actPacInd.setDatePromised(servView.getFecha());
			actPacInd.setDateOrdered(servView.getFecha());
		}

		// Estacion y almacen
		actPacInd.setEXME_Area_ID(estServLogeo.getEXME_Area_ID());
		final String tipoArea = estServLogeo.getTipoArea();
		actPacInd.setTipoArea(tipoArea == null ? X_EXME_Area.TIPOAREA_Other : tipoArea);

		if (warehouseTo != null) {
			actPacInd.setAD_OrgTrx_ID(warehouseTo.getAD_OrgTrx_ID());// aplica
			actPacInd.setWarehouseName(warehouseTo.getName());
			actPacInd.setWarehouseLocation(warehouseTo.getLocationStr(true));// direccion
			actPacInd.setM_Warehouse_ID(warehouseTo.getM_Warehouse_ID());
		}
		// Producto
		if (servView.getMed() != null) {
			actPacInd.setNameServ(servView.getMed().getName());
			actPacInd.setM_Product_ID(servView.getMed().getM_Product_ID());
			actPacInd.setC_UOM_ID(servView.getMed().getC_UOM_ID());
			actPacInd.setUomName(servView.getMed().getUom().getName());
		}

		// Indicaciones
		actPacInd.setDescripcion(servView.getDescripcion());
		actPacInd.setQtyOrdered(servView.getCantidad() <= 0 ? Env.ONE : new BigDecimal(servView.getCantidad()));
		actPacInd.setEXME_Diagnostico_ID(servView.getDiagnosis1ID());
		actPacInd.setEXME_Diagnostico2_ID(servView.getDiagnosis2ID());
		actPacInd.setEXME_Diagnostico3_ID(servView.getDiagnosis3ID());
		actPacInd.setPriorityRule(servView.getPrioridadID());
		actPacInd.setEXME_Modifiers_ID(servView.getEXME_Modifier());

		// Consideraciones de ordenes
		actPacInd.setBillable(servView.isBillable());
		actPacInd.setOtherInstructions(servView.getOtherInstructions());
		actPacInd.setConsultingProvider(servView.getConsultingProvider());
		actPacInd.setIsTodayService(servView.isTodayService());
		actPacInd.setComments(servView.getComments());
		actPacInd.setProveedor(servView.getProvider());
		actPacInd.setEXME_Institucion_ID(servView.getEXME_Institucion_ID());
		actPacInd.setSurtir(servView.getSurtir());// Externo / Interno
		actPacInd.setEXME_ReferInpatient_ID(referralID);
		actPacInd.setEXME_ReferInpatient_ID(servView.getAD_Org_Dest_ID());
		actPacInd.setFolio(folio);// Un folio por orden
		actPacInd.setEXME_OrderSet_ID(servView.getExmeOrderSetId());

		// Precios
		if (actPacInd.isSurtir() || actPacInd.isBillable()) {
			// calcula los precios
			final MPrecios precios = GetPrice.getPriceActPac(actPacInd);
			precios.updatePrices(actPacInd);// referencia
		}
		if (actPacInd.save()) {
			servView.setEXME_ActPacienteInd_ID(actPacInd.getEXME_ActPacienteInd_ID());
		} else {
			throw new MedsysException();// "error.notasMed.servicios.detalle"
		}
		// Actualiza el encabezado
		header.setTotalLines(header.getTotalLines().add(actPacInd.getLineNetAmt()));
		header.setGrandTotal(header.getGrandTotal().add(actPacInd.getLineNetAmt().add(actPacInd.getTotalImp())));
		return actPacInd;
	}

	/**
	 * Insertamos en el encabezado y en las lineas
	 * 
	 * @param orderHeader encabezado y detalle
	 * @return true si todo salio bien
	 * @throws Exception
	 */
	private MEXMEActPacienteIndH createOrders(final OrderHeader orderHeader) {
		if (orderHeader.getDetail().isEmpty()) {
			return null;
		}
		MWarehouse warehouseTo = null;
		// se crea la solicitud para ese grupo de servicios por fecha y almacen
		if (orderHeader.getWarehouse() > 0) {
			warehouseTo = new MWarehouse(ctx, orderHeader.getWarehouse(), null);
		}
		// Create header (first)
		final MEXMEActPacienteIndH header = createHeader(orderHeader.getDetail().get(0), warehouseTo);
		if (header == null) {
			return null;
		}
		// Inserta las lineas
		for (final ServicioView servAdded : orderHeader.getDetail()) {
			createline(header, servAdded, warehouseTo);
		}
		// Guardamos el header
		header.setSendOrder(folio);
		header.calculateTaxTotal();

		if (!header.save()) {
			throw new MedsysException();// "error.notasMed.servicios.header"
		}
		// Lama> Si es una orden de CPOE, se valida si es un medico quien esta creando la orden,
		// se manda llamar el metodo para autenticar, o esta por escrito
		if (Env.getEXME_Medico_ID(ctx) > 0 || MEXMEActPacienteIndH.ORDERTYPE_WrittenOrder.equals(orderType)) {
			header.approve();
		}
		// solicitudes
		lstActPacIndH.add(header);
		return header;
	}

	public List<MEXMEActPacienteIndH> getLstActPacienteIndH() {
		return this.lstActPacIndH;
	}

	public List<ServicioView> getServicesAdded() {
		return servicesAdded;
	}
	
	public static String confirm(final MEXMEActPacienteIndH order, final Timestamp datePromised) {
		// currentDate.before(selected) : Utils.getLabel("enfermeria.proc.fechaPostOrHrPost")
		String msg = "";
		if(datePromised != null) {
			msg = order.approve();
			if (StringUtils.isEmpty(msg)) {
				order.approveIt();
				order.setDatePromised(datePromised);
				order.setFecha_Fin(null);
				if (order.save()) {
					ServicesSave.createHl7(order);
				} else {
					msg = new MedsysException().getLocalizedMessage();
				}
			}
		}
		return msg;
	}

	/** MessageGenerator: Create message according configuration in Warehouse */
	public static void createHl7(List<MEXMEActPacienteIndH> lstActPacIndH) {
		for (final MEXMEActPacienteIndH header : lstActPacIndH) {
			createHl7(header);
		}
	}
	
	/** MessageGenerator: Create message according configuration in Warehouse */
	public static void createHl7(MEXMEActPacienteIndH header) {
		try {
			final MWarehouse warehouse = header.getWarehouse();
			// Laboratory Warehouse
			if (warehouse.isHL7ORM() && X_M_Warehouse.TYPE_Laboratory.equals(warehouse.getType().trim())) {
				new MessageGenerator(header.getCtx(), true)
					.generateMessage(header.get_ID(), I_EXME_ActPacienteIndH.Table_Name, warehouse.getInterfaz_HL7(), null);
			}
			// Radiology Warehouse
			if (warehouse.isHL7ORM() && X_M_Warehouse.TYPE_Radiology.equalsIgnoreCase(warehouse.getType().trim())) {
				new MessageGenerator(header.getCtx(), true)
					.generateMessage(header.get_ID(), I_EXME_ActPacienteIndH.Table_Name, warehouse.getInterfaz_HL7(), null);
			}
		} catch (final ClientException e) {
			log.log(Level.SEVERE, "MEXMEActPacienteIndH - SmartConnector Fail", e);
		}
	}

	/**
	 * Guarda las solicitudes de servicio, consideran el almacen
	 * @param toRemove 
	 * 
	 * @return true si todo salio bien
	 */
	private String processAll(List<MEXMEActPacienteInd> toRemove) throws Exception {
		Trx mTrx = null;// solo si la transaccion no existe
		final StringBuilder str = new StringBuilder();
		boolean islocalTrx = false;
		try {
			final List<OrderHeader> lst = OrderHeader.getGroupedList(servicesAdded);
			// Nombre de transaccion, respeta si ya existe alguna
			if (StringUtils.isNotBlank(trxName)) {
				mTrx = Trx.get(trxName, false);
			}
			if (mTrx == null) {
				mTrx = Trx.get(Trx.createTrxName("ServSave"), true);
				islocalTrx = true;
				trxName = mTrx.getTrxName();
			}
			// creamos la actividad si no existe
			if (actvPacienteID <= 0) {
				createActPacienteID();
			}
			for (final OrderHeader orderHeader : lst) {
				final MEXMEActPacienteIndH header = createOrders(orderHeader);
				if (header == null) {
					break;
				}
				if(str.length()>0) {
					str.append(", ");
				}
				str.append(header.getDocumentNo());
			}
			// cancelado - RQM #5214 > se cancelan los servicios eliminados al editar la orden
			if (toRemove != null && !toRemove.isEmpty() && oldActPacIndHToCancel == null) {
				for (MEXMEActPacienteInd ind : toRemove) {
					if (ind != null && !ind.is_new()) {
						ind.set_TrxName(trxName);
						if (!ind.voidIt()) {
							throw new MedsysException(ind.getProcessMsg());
						}
						str.append(Constantes.NEWLINE);
						str.append(Utilerias.getAppMsg(ctx, "cancelaServ.msg.cancelado", ind.getProductName()));
					}
				}
			}

			closeTrx(mTrx, true, islocalTrx);
		} catch (final Exception e) {
			closeTrx(mTrx, false, islocalTrx);
			log.log(Level.SEVERE, "ServicesSave#processAll", e);
			throw e;
		} finally {
			trxName = null;
			if (str.length() > 1 && oldActPacIndHToCancel != null) {
				// cancelado
				str.append(Constantes.NEWLINE);
				str.append(Utilerias.getAppMsg(ctx, "salidaGasto.msg.cancelo", oldActPacIndHToCancel.getDocumentNo()));
				ServicesSave.createHl7(oldActPacIndHToCancel);
			}
			oldActPacIndHToCancel = null;
		}
		return str.toString();
	}
	
	/** @return Ids saved */
	public StringBuilder getIds() {
//		if (ids == null) {
//			ids = new StringBuilder();
//		}
		StringBuilder ids = new StringBuilder();
		for (final MEXMEActPacienteIndH pair : getLstActPacienteIndH()) {
			if (ids.length() > 0) {
				ids.append(", ");
			}
			ids.append(pair.getDocumentNo());
		}
		return ids;
	}

//	/**
//	 * @deprecated use {@link #save(List, int, MEXMECtaPac, boolean, String)}, {@link #setReferralID(int)}, {@link #setEsEditable(boolean)}
//	 */
//	@Deprecated
//	public boolean save(final List<ServicioView> pLstProduct, final int pActPacienteID, final MEXMECtaPac pMexmeCtaPac, final boolean pEsEditable,
//		final int pReferralID, final String trx) throws Exception {
//		return save(pLstProduct, pActPacienteID, pMexmeCtaPac, false, trx);
//	}

	/**
	 * Inserta en MEXMEActPacienteIndH y MEXMEActPacienteInd para las solicitudes de
	 * servicios
	 * 
	 * @param pLstProduct Lista de productos servicios de tipo ServicioView (<b>requerido</b>)
	 * @param pActPacienteID Actividad paciente, normalmente el ligado a la cuenta paciente, si no existe se crea
	 * @param pMexmeCtaPac Cuenta paciente (<b>requerido</b>)
	 * @param pupdate true cuando se actualiza y no se inserta por primera vez
	 * @param trx nombre de transaccion, puede ser null y se crea una, solo para guardar la solictud
	 */
	public String saveAll(final List<ServicioView> pLstProduct, 
		final int pActPacienteID, final MEXMECtaPac pMexmeCtaPac, final boolean update,
		final List<MEXMEActPacienteInd> toRemove,
		final String trx) throws Exception {
		this.servicesAdded = pLstProduct;
		this.actvPacienteID = pActPacienteID;
		this.update = update;
		this.trxName = trx;
		// si se cambio de cuenta paciente, o es la primera vez que se dispara el proceso
		if (pMexmeCtaPac != null && (objCtaPac == null || objCtaPac.getEXME_CtaPac_ID() != pMexmeCtaPac.getEXME_CtaPac_ID())) {
			this.objCtaPac = pMexmeCtaPac;
			this.locationId = MBPartnerLocation.getBPartnerLocation(ctx, objCtaPac.getC_BPartner_ID());
			if (locationId <= 0) {
				this.locationId = objCtaPac.getPaciente().getC_Location_ID();
			}
			this.listaPrecios = MEXMEConfigPre.getPriceList(ctx, null); 
					//MPriceList.getPriceList(ctx, objCtaPac.getPaciente());
			if (this.actvPacienteID <= 0) {
				this.actvPacienteID = MEXMEActPaciente.getForCtaPac(objCtaPac).get_ID();
			}
			this.especialidadID = objCtaPac.getEXME_Especialidad_ID();
			if (especialidadSolID <= 0) {
				this.especialidadSolID = objCtaPac.getEXME_Especialidad_ID();
			}
			if (physicianId <= 0) {
				this.physicianId = objCtaPac.getEXME_Medico_ID();
			}
		}
		// start process Save/Update
		lstActPacIndH.clear();
		this.folio = 0;
		validate();
		return processAll(toRemove);
	}
	
	/**
	 * Inserta en MEXMEActPacienteIndH y MEXMEActPacienteInd para las solicitudes de
	 * servicios
	 * 
	 * @param pLstProduct Lista de productos servicios de tipo ServicioView (<b>requerido</b>)
	 * @param pActPacienteID Actividad paciente, normalmente el ligado a la cuenta paciente, si no existe se crea
	 * @param pMexmeCtaPac Cuenta paciente (<b>requerido</b>)
	 * @param pupdate true cuando se actualiza y no se inserta por primera vez
	 * @param trx nombre de transaccion, puede ser null y se crea una, solo para guardar la solictud
	 */
	public boolean save(final List<ServicioView> pLstProduct, final int pActPacienteID, final MEXMECtaPac pMexmeCtaPac, final boolean update,
		final String trx) throws Exception {
		return saveAll(pLstProduct, pActPacienteID, pMexmeCtaPac, update, null, trx)!=null;
	}

//	/**
//	 * @deprecated no se requiere enviar la lista como {@link Map} ya que la agrupacion de servicios se hace posteriormente en el {@link #save()}<br>
//	 *             use {@link #save(List, int, MEXMECtaPac, boolean, String)}, {@link #setReferralID(int)}, {@link #setEsEditable(boolean)},
//	 *             {@link #setPhysicianId(int)}
//	 */
//	@Deprecated
//	public boolean save(final Map<Integer, List<ServicioView>> pServicesAddedMap, final int pEspecialidadID, final int pMedicoID,
//		final int pEspSolID, final int pActPacienteID, final MEXMECtaPac pMexmeCtaPac, final ServicioView pServicioHdr, final boolean pEsEditable,
//		final String trx) throws Exception {
//		// Iteramos el mapa //FIXME: porque se recibe como mapa, para convertirlo en lista,
//		// si en el metodo save, lo vuelve a iterar para agruparlo ?????
//		final List<ServicioView> lstServices = new ArrayList<ServicioView>();
//		for (final Entry<Integer, List<ServicioView>> lineas : pServicesAddedMap.entrySet()) {
//			if (lineas.getValue() != null && !lineas.getValue().isEmpty()) { // si
//				lstServices.addAll(lineas.getValue());
//			}
//		}
//		return save(lstServices, pActPacienteID, pMexmeCtaPac, false, trx);
//	}

//	/** @deprecated */
//	@Deprecated
//	public void setEsEditable(final boolean esEditable) {
//		this.esEditable = esEditable;
//	}

	public void setOrderType(final String orderType) {
		this.orderType = orderType;
	}

	/** @param pMedicoID Medico quien solicita el servicio */
	public void setPhysicianId(final int physicianId) {
		this.physicianId = physicianId;
	}

	public void setReadBack(final String readBack) {
		this.readBack = readBack;
	}

	/** @param pReferralID ID de referencia medica; si proviene de ella */
	public void setReferralID(final int referralID) {
		this.referralID = referralID;
	}
	
	public void setDateOrdered(Timestamp dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	/** Guarda la solicitud de servicio */
	private void validate() {
		// Validamos los datos necesarios
		if (servicesAdded == null || servicesAdded.isEmpty()) {
			throw new MedsysException(Utilerias.getMsg(ctx, "error.noMovementLine"));
		}
		if (especialidadID <= 0 || especialidadSolID <= 0) {
			throw new MedsysException(Utilerias.getMsg(ctx, "error.citas.especialidad"));
		}
		if (objCtaPac == null) {
			throw new MedsysException(Utilerias.getMsg(ctx, "msj.errorPacNoCta"));
		}
		if (listaPrecios == null) {
			throw new MedsysException(Utilerias.getMsg(ctx, "error.getPriceListInfo"));
		}
	}
}
