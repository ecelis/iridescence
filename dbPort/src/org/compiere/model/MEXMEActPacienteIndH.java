package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams;
import org.compiere.model.MEXMEDiscontinueOrder.IDiscontinueOrder;
import org.compiere.model.bpm.CreateCharge;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.Worklist;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * 
 * @author
 * 
 */
public class MEXMEActPacienteIndH extends X_EXME_ActPacienteIndH implements
		DocAction, Worklist, IDiscontinueOrder {

	/** serialVersionUID */
	private static final long serialVersionUID = -1893865428295974647L;
	/** Logger */
	private static CLogger s_log = CLogger
			.getCLogger(MEXMEActPacienteIndH.class);
	/** mensajes */
	private String m_processMsg = null;
	/** lineas detalle en arreglo */
	private MEXMEActPacienteInd[] lineas = null;
	/** objeto de apciente */
	private MEXMEPaciente paciente = null;
	/** objeto de actividad paciente header */
	private MEXMEActPaciente actPaciente = null;
	/** almacen */
	private MWarehouse warehouse = null;
	/** cuenta paciente */
	private MEXMECtaPac ctaPac = null;
	/** Just Prepared Flag */
//	@SuppressWarnings("unused")
//	private boolean m_justPrepared = false;
	/** lines */
//	public List<MEXMEActPacienteInd> lines = new ArrayList<MEXMEActPacienteInd>();
	/** ruta */
//	private String ruta = null;
	/** Lista de medicamentos que se le prescribieron al paciente por evento */
	private List<MEXMEActPacienteInd> medicationList = new ArrayList<MEXMEActPacienteInd>();
	/** especialidad */
	private MEXMEEspecialidad especialidad = null;
	/** suspendido */
	private boolean suspendido = false;
//	/** inOut */
//	private MEXMEInOut inOut = null;
	/** almacen */
	private MWarehouse almacen = null;
	/** Order Lines */
	private MEXMEActPacienteInd[] m_lines = null;
	/**
	 * Lineas de la actividad paciente creadas por primera vez, (aun no estan en
	 * la base de datos)
	 */
	private List<MEXMEActPacienteInd> lstActPacInd = new ArrayList<MEXMEActPacienteInd>();
	/**
	 * Define si las lineas ya fueron surtidas o aplicadas y el estatus debe
	 * cambiar
	 * 
	 * @param completo
	 */
	private boolean completo = true;

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param EXME_ActPacienteIndH_ID
	 * @param trxName
	 */
	public MEXMEActPacienteIndH(final Properties ctx,
			final int EXME_ActPacienteIndH_ID, final String trxName) {
		super(ctx, EXME_ActPacienteIndH_ID, trxName);

		if (is_new()) {
			// Valores por default.
			setDateOrdered(Env.getCurrentDate());

			setChargeAmt(Env.ZERO);
			setIsInvoiced(false);
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DOCACTION_Complete);

			setProcessing(false);
			setProcessed(false);
			setIsApproved(false);//Aprobacion manual

			setDateAcct(getDateOrdered());
			setIsDiscountPrinted(false);
			setTotalLines(Env.ZERO);
			setGrandTotal(Env.ZERO);
			setIsTaxIncluded(false);
			// setPosted(false);
			setIsDelivered(false);
			// setC_DocType_ID(); Receta o Servicios.
			// setC_DocTypeTarget_ID()

			setPriorityRule(PRIORITYRULE_ROUTINE); // medio.
		}
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEActPacienteIndH(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

//	/**
//	 * constructor
//	 * 
//	 * @param ctx
//	 * @param actPaciente
//	 * @param trxName
//	 */
//	public MEXMEActPacienteIndH(final Properties ctx,
//			final MEXMEActPaciente actPaciente, final String trxName) {
//		this(ctx, 0, trxName);
//
//		if (actPaciente != null) {
//			setEXME_ActPaciente(actPaciente);
//			setEXME_CtaPac_ID(actPaciente.getEXME_CtaPac_ID());
//
//			paciente = new MEXMEPaciente(getCtx(),
//					actPaciente.getEXME_Paciente_ID(), get_TrxName());
//			// socio de negocios a nivel de system
//			setC_BPartner_ID(setBPartner());
//		}
//	}

	/**
	 * LA programacion por semana
	 * 
	 * @param ctx
	 * @param fechaIni
	 * @param fechaFin
	 * @param trxName
	 * @return
	 * @throws ParseException
	 * @deprecated use {@link MEXMEEquipoH#getEquiposH(Properties, int, String)} or
	 * {@link MEXMEEquipoH#getActive(Properties, int, String)}
	 * {@link MEXMEEquipoH#get(Properties, int, int, Date, Date, boolean, String)}
	 */
	public List<MEXMEEquipoH> getEquiposProgramados() {
//		final StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT EXME_EquipoH.* FROM EXME_EquipoH ")
//				.append(" WHERE EXME_EquipoH.IsActive = 'Y'  ")
//				.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
//						"EXME_EquipoH"))
//				.append(" AND EXME_EquipoH.EXME_ActPacienteIndH_ID = ? ")
//				.append(" ORDER BY EXME_EquipoH.EXME_EquipoH_ID ");
		return MEXMEEquipoH.getEquiposH(getCtx(), getEXME_ActPacienteIndH_ID(), get_TrxName());
	}

//	/**
//	 * 
//	 * @param ctx
//	 * @param trxName
//	 * @return
//	 * @throws Exception
//	 * @deprecated use {@link MEXMEActPacienteIndH#completeIt()}
//	 */
//	public boolean aplicarSolicitudServ(final Properties ctx,
//			final List<ActPacienteIndView> lineasPedido, final String trxName)
//			throws Exception {
//		boolean retValue = false;
//
//		Hashtable<Integer, BigDecimal> aSurtir = new Hashtable<Integer, BigDecimal>();
//		List<ActPacienteIndView> toDeliver = new ArrayList<ActPacienteIndView>();
//		List<MEXMEEquipoH> equiposH = new ArrayList<MEXMEEquipoH>();
//
//		try {
//			// Obtenemos la Est. de Servicio de logeo, de donde se esta
//			// aplicando el Servicio.
//			MEXMEEstServ estServ = new MEXMEEstServ(ctx,
//					Env.getEXME_EstServ_ID(ctx), trxName);
//			MEXMEActPacienteIndH actPacIndH = null;
//			// validar
//			actPacIndH = validate(ctx, lineasPedido, toDeliver, trxName);
//			/*
//			 * PreciosVenta.m_configPre = MEXMEConfigPre.get(ctx, trxName);
//			 * PreciosVenta.m_Paciente =
//			 * actPacIndH.getActPaciente().getPaciente();
//			 * PreciosVenta.m_ConfigEC = MConfigEC.get(ctx, trxName);
//			 */
//			// StringBuilder whereClause = new StringBuilder(100);
//			// whereClause
//			// .append(" AD_Org_ID = " + Env.getAD_Org_ID(ctx))
//			// //
//			// .append(" AD_Client_ID = " + Env.getAD_Client_ID(ctx) + " ");
//
//			/*
//			 * sending whereClause to the methdo to avoid problems with the
//			 * values attached from the MLookUpInfo tables. ALINES "alines" es
//			 * la lista MEXMEActPacienteIndH con todos los servicios solicitados
//			 */
//			MEXMEActPacienteInd[] aLines = actPacIndH.getLineas();
//
//			if (aLines.length <= 0) {
//				s_log.log(
//						Level.WARNING,
//						"Error MEXMEActPacienteIndH.aplicarSolicitudServ() no hay lineas de servicios loggeadas");
//			}
//			/*
//			 * BARRE LAS LINEAS DEL HEADER
//			 */
//			for (int j = 0; j < aLines.length; j++) {
//
//				MEXMEActPacienteInd line = aLines[j];
//
//				/*
//				 * SETEA LAS LINEAS todeliver es una lista que contine elementos
//				 * ActPacienteIndView iterados a partir de las lineas de pedido,
//				 * siendo que si existen 2 lineas de pedido entonces habr� 2
//				 * elementos en todeliver
//				 */
//				/* armando */// for (int h = 0; h < toDeliver.size(); h++) {
//
//				/*
//				 * @Mandrake: el probelma es que consideraba H y h siempre era
//				 * la misma por eso se cambia. asi en el ciclo entra con cada
//				 * nueva linea de serv. solicitado para el mismo paciente.
//				 */
//				// LabelValueBean lvb = (LabelValueBean)toDeliver.get(h);
//				ActPacienteIndView apv = (ActPacienteIndView) toDeliver.get(j);
//				if (apv.getCantSurtir() <= 0.0)
//					break;
//
//				validaTeView(apv, line);
//
//				BigDecimal qty = new BigDecimal(apv.getCantSurtir());
//				InputStream stream = null;
//				// retrieve the file data
//				stream = loadImagen(apv, line);
//				line.setImagen(apv.getLigaFoto());
//				apv.setConFoto(true);
//
//				line.setAnotaciones(apv.getAnotaciones());
//				if (line.getQtyOrdered().compareTo(Env.ZERO) <= 0)
//					if (apv.getQtyOrdered() <= 0)
//						line.setQtyOrdered(qty);
//					else
//						line.setQtyOrdered(new BigDecimal(apv.getQtyOrdered()));
//
//				line.setQtyDelivered(qty);
//				line.setDateDelivered(DB.getTimestampForOrg(ctx));
//
//				// Validamos que los servicios tenga precio. Todo o nada.
//				// String priceList = PreciosVenta.PVH;
//
//				// Revisamos si existe una cuenta paciente.
//				// MEXMECtaPac[] ctasPac = MEXMECtaPac.getOfPaciente(ctx,
//				// actPacIndH
//				// .getActPaciente().getEXME_Paciente_ID(),
//				// MEXMECtaPac.ESTATUS_Open, trxName);
//
//				// if (ctasPac == null || ctasPac.length <= 0)
//				// priceList = PreciosVenta.PVCE; // Proviene de Consulta
//				// Externa.
//
//				// MPrecios precios = PreciosVenta.precioProdVta(ctx,
//				// estServ.getTipoArea(), line.getM_Product_ID(),
//				// line.getQtyDelivered(), line.getC_UOM_ID(), priceList,
//				// 0, 0, actPacIndH.getM_Warehouse_Sol_ID(),
//				// actPacIndH.getM_Warehouse_ID(),
//				// estServ.getEXME_Area_ID(), actPacIndH.getDateOrdered(),
//				// false, trxName);
//
//				// if (Env.ZERO.compareTo(precios.getPriceList()) >= 0) {
//				// s_log.log(Level.WARNING,
//				// "Error de MEXMEActPacienteIndH.aplicarSolicitudServ precios.getPriceList() = 0");
//				// throw new Exception("error.servicios.noPrice"
//				// + line.getProduct().getName());
//				// }
//				// Guardamos la ruta para desplegar en RIS
//				line.setRisID(apv.getRisID());
//
//				if (line.save(trxName)) {
//					if (stream != null) {
//						OutputStream bos = new FileOutputStream(this.ruta);
//
//						int bytesRead = 0;
//						byte[] buffer = new byte[8192];
//						while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
//							bos.write(buffer, 0, bytesRead);
//						}
//						bos.close();
//						stream.close();
//					}
//				}
//				aSurtir.put(new Integer(line.getEXME_ActPacienteInd_ID()),
//						line.getQtyDelivered());
//			}
//			// }// fin for aLines (Lineas originales)armando
//
//			actPacIndH.setProcessed(true);
//			actPacIndH.setDocStatus(DocAction.STATUS_Completed);
//			actPacIndH.setDocAction(DocAction.ACTION_None);
//			actPacIndH.setDateDelivered(DB.getTimestampForOrg(ctx));
//			actPacIndH.setIsDelivered(true);
//			actPacIndH
//					.setEstatus(MEXMEActPacienteIndH.ESTATUS_ServiceOrdersCompleted); // EXPERT:
//			// LRHU.-
//			// Cambiamos
//			// el estatus de la orden de
//			// Servicio.
//
//			// EXPERT: Inicio LRHU.- Obtenemos los equiposH y Actualizamos el
//			// estatus de �stos
//			equiposH = MEXMEEquipoH.getEquiposH(ctx,
//					actPacIndH.getEXME_ActPacienteIndH_ID(), null);
//			if (!equiposH.isEmpty()) {
//				Iterator<MEXMEEquipoH> k = equiposH.iterator();
//				while (k.hasNext()) {
//					MEXMEEquipoH lista = (MEXMEEquipoH) k.next();
//					if (lista.getEXME_EquipoH_ID() > 0) {
//						MEXMEEquipoH eqpH = new MEXMEEquipoH(ctx,
//								lista.getEXME_EquipoH_ID(), trxName);
//						eqpH.setEstatus_Equipo(MEXMEEquipoH.ESTATUS_EQUIPO_Used);
//						if (!eqpH.save(trxName)) {
//							s_log.log(Level.ALL,
//									"Error no se pudo guardar los equipos");
//							throw new Exception("error.servicios.surtir");
//						}
//					}
//				}
//			}// fin LRHU
//
//			// Verificamos que pueda aplicar el Servicio.
//			// Que no haya sido anteriormente aplicada
//			if (!MEXMEActPacienteIndH.canDeliver(ctx,
//					actPacIndH.getEXME_ActPacienteIndH_ID(), trxName, true)) {
//				s_log.log(Level.WARNING, "error.servicios.cantApply"
//						+ " Error servicio ya aplicado");
//				throw new Exception("error.servicios.cantApply");
//			}
//
//			if (!actPacIndH.save(trxName)) {
//				s_log.log(Level.WARNING,
//						"Error no se pudo guardar actPacienteIndH.save()");
//				throw new Exception("error.servicios.surtir");
//			}
//
//			/**
//			 * Se invirtio el orden de crear las lineas de salida inventario y
//			 * cargos para guardar la relacion entre ambos Noelia
//			 */
//			// inventario
//			MEXMEInOut m_InOut = MEXMEInOut.createFromApServ(actPacIndH,
//					DB.getTimestampForOrg(ctx), aSurtir, false, null, true,
//					estServ, trxName);
//
//			if (m_InOut == null) {
//				s_log.log(Level.WARNING,
//						"Error MEXMEActPacienteIndH.aplicarSolicitudServ() null value pro m_InOut "
//								+ MEXMEInOut.getS_ProcessMsg());
//				throw new Exception(
//						"surtPedidoAction.error.surtir"
//								+ " Error MEXMEActPacienteIndH.aplicarSolicitudServ() null value pro m_InOut "
//								+ MEXMEInOut.getS_ProcessMsg());
//			}
//
//			String status = m_InOut.prepareIt();
//			if (!DocAction.STATUS_InProgress.equals(status)) {
//				s_log.log(
//						Level.WARNING,
//						"Error MEXMEActPacienteIndH.aplicarSolicitudServ() DocAction.STATUS_InProgress not equals to "
//								+ status + " " + m_InOut.getM_processMsg());
//
//				throw new Exception(
//						"surtPedidoAction.error.surtir"
//								+ " Error MEXMEActPacienteIndH.aplicarSolicitudServ() DocAction.STATUS_InProgress not equals to "
//								+ status + " " + m_InOut.getM_processMsg());
//			}
//
//			status = m_InOut.completeIt();
//			m_InOut.setDocStatus(status);
//			if (!DocAction.STATUS_Completed.equals(status)) {
//				s_log.log(
//						Level.WARNING,
//						"Error MEXMEActPacienteIndH.aplicarSolicitudServ() DocAction.STATUS_Completed not equals to "
//								+ status + " " + m_InOut.getM_processMsg());
//
//				throw new Exception(
//						"surtPedidoAction.error.surtir"
//								+ " Error MEXMEActPacienteIndH.aplicarSolicitudServ() DocAction.STATUS_Completed not equals to "
//								+ status + " " + m_InOut.getM_processMsg());
//			}
//
//			if (!m_InOut.save(trxName)) {
//				s_log.log(
//						Level.WARNING,
//						"Error MEXMEActPacienteIndH.aplicarSolicitudServ() DocAction.STATUS_Completed not equals to "
//								+ status + " " + m_InOut.getM_processMsg());
//
//				throw new Exception(
//						"surtPedidoAction.error.surtir"
//								+ " Error MEXMEActPacienteIndH.aplicarSolicitudServ() DocAction.STATUS_Completed not equals to "
//								+ status + " " + m_InOut.getM_processMsg());
//			}
//
//			// cargoCtapac(ctx,actPacIndH,trxName);
//			// retValue = true;
//
//			// OJO ARMANDO }
//			if (actPacIndH.getEXME_CtaPac_ID() > 0) {
//				cargoCtapac(ctx, actPacIndH, trxName);
//			}
//			retValue = true;
//		} catch (Exception e) {
//			s_log.log(Level.WARNING,
//					"Error MEXMEActPacienteIndH.aplicarSolicitudServ() Exception - "
//							+ e.toString());
//			throw new Exception("surtPedidoAction.error.surtir"
//					+ " surtPedidoAction.error.surtir : " + e.toString());
//		}
//
//		return retValue;
//	}

//	/**
//	 * 
//	 * @param apv
//	 * @param line
//	 * @throws Exception
//	 * @deprecated
//	 */
//	private void validaTeView(final ActPacienteIndView apv,
//			final MEXMEActPacienteInd line) throws Exception {
//		if (apv == null) {
//			s_log.log(Level.WARNING,
//					"Error MEXMEActPacienteIndH.validaTeView() Exception - ActPacienteView null");
//			throw new Exception("error.servicios.noPrice");
//		}
//
//		if (apv.getCantSurtir() <= 0.0) {
//			s_log.log(
//					Level.WARNING,
//					"Error MEXMEActPacienteIndH.validaTeView() Exception - Delivery qty zero or negative");
//			throw new Exception(
//					"error.servicios.noPrice"
//							+ " No se aplicar el servicio, el producto  no cuenta con precio");
//		}
//
//		/*
//		 * @Mandrake: se hace esta modificacion para cuando es mas de un
//		 * producto los coincidentes deben EXME_ActPacienteIndH tanto de la
//		 * vista como del detalle
//		 */
//		if (line.getEXME_ActPacienteIndH_ID() != apv.getXXActPacienteIndHID()) {
//			s_log.log(
//					Level.WARNING,
//					"error.servicios.noPrice"
//							+ " Error MEXMEActPacienteIndH.validaTeView() Exception - Delivery qty zero or negative");
//			throw new Exception(
//					"error.servicios.noPrice"
//							+ " Error MEXMEActPacienteIndH.validaTeView() Exception - Delivery qty zero or negative");
//		}
//	}

//	/**
//	 * validate
//	 * 
//	 * @param ctx
//	 * @param lineasPedido
//	 * @param toDeliver
//	 * @param trxName
//	 * @return
//	 * @throws Exception
//	 * @deprecated
//	 */
//	private MEXMEActPacienteIndH validate(final Properties ctx,
//			final List<ActPacienteIndView> lineasPedido,
//			final List<ActPacienteIndView> toDeliver, final String trxName)
//			throws Exception {
//		double cantTotal = 0.0;
//		long xXActPacienteIndHID = -1;
//
//		if (lineasPedido.size() <= 0) {
//			throw new Exception("error.servicios.surtir.cantSurt");
//		}
//		final Iterator<ActPacienteIndView> i = lineasPedido.iterator();
//		while (i.hasNext()) {
//			final ActPacienteIndView view = (ActPacienteIndView) i.next();
//			double cantSurtir = view.getCantSurtir();
//
//			if (cantSurtir > view.getCantPedida()) {
//				throw new Exception("error.servicios.surtir.cantSurt");
//			}
//			toDeliver.add(view);
//			cantTotal = cantTotal + cantSurtir;
//			xXActPacienteIndHID = view.getXXActPacienteIndHID();
//		}
//		if (xXActPacienteIndHID <= 0) {
//			throw new Exception("error.servicios.noPrice");
//		}
//		if (toDeliver.isEmpty()) {
//			throw new Exception("error.servicios.noPrice");
//		}
//		if (cantTotal <= 0.0) {
//			throw new Exception("error.traspasos.lines.qty");
//		}
//		return new MEXMEActPacienteIndH(ctx, (int) xXActPacienteIndHID, trxName);
//
//	}

//	/**
//	 * Crear cargos de aplicacion de servicios
//	 * 
//	 * @param ctx
//	 * @param actPacIndH
//	 * @param trxName
//	 * @throws Exception
//	 * @deprecated use
//	 *             {@link org.compiere.model.bpm.ServicesDelivered.ServicesDelivered}
//	 */
//	public void cargoCtapac(Properties ctx, MEXMEActPacienteIndH actPacIndH,
//			String trxName) throws Exception {
//
//		// Hacemos el cargo a la cuenta paciente (si existe).
//		MEXMECtaPac[] ctasPac = MEXMECtaPac.getOfPaciente(ctx, actPacIndH
//				.getPaciente().getEXME_Paciente_ID(),
//				MEXMECtaPac.ENCOUNTERSTATUS_Admission, trxName);
//
//		if (ctasPac == null || ctasPac.length <= 0) {
//			throw new Exception("error.servicios.noPrice");
//		}
//		// solo deberia haber una ctaPac abierta.
//		MEXMECtaPac ctaPac = ctasPac[0];
//
//		// Hacemos el cargo a la ctaPac.
//		String info = null;
//
//		try {
//			info = actPacIndH.createCharges(MEXMECtaPacExt.getExtCero(ctx,
//					ctaPac.getEXME_CtaPac_ID(), trxName), true, trxName);
//
//		} catch (Exception pre_e) {
//			throw new Exception("error.servicios.noPrice" + info);
//		}
//
//		if (info != null) {
//			throw new Exception("error.servicios.cargar" + info);
//		}
//	}

//	/**
//	 * loadImagen
//	 * 
//	 * @param apv
//	 * @param line
//	 * @return
//	 * @throws Exception
//	 * @deprecated
//	 */
//	public InputStream loadImagen(final ActPacienteIndView apv,
//			final MEXMEActPacienteInd line) throws Exception {
//		// la variable MEDSYS_PATH_IMG
//
//		// retrieve the file representation
//		final FormFile file = apv.getImagen();
//
//		// extraemos la extension del archivo
//		String extension = "jpg";
//		final int pos = file.getFileName().indexOf('.');
//		if (pos > -1)
//			extension = file.getFileName().substring(pos + 1,
//					file.getFileName().length());
//
//		// nombre del archivo
//		final String fileName = "serv_" + apv.getXXActPacienteIndID() + "."
//				+ extension;
//
//		// write the file to the file specified
//		// directorio config + act paciente id + fileName
//		this.ruta = Utilerias.getPropertiesFromEnv("MEDSYS_PATH_IMG")
//				+ File.separator + fileName;
//
//		line.setImagen(fileName);
//		apv.setLigaFoto("/eCareSoftWeb/ece/imagenes/" + fileName);
//
//		return file.getInputStream();
//	}

	/**
	 * Metodo que obtiene una lista de Indicaciones pendientes de Facturar: Si
	 * isReqFactCE es true: Entonces se obtiene las solicitudes de servicio y
	 * producto de ejecucion de cita y las solicitudes de servicio a pacientes
	 * externos Si isReqFactCE es false: Entonces solo obtiene las solicitudes
	 * de servicio a pacientes externos
	 * 
	 * @param ctx
	 * @param isReqFactCE
	 * @param trxName
	 * @param fecha
	 * @return List<ActPacienteIndHView>, lista con las indicaciones pendientes
	 *         de facturar
	 * @author Noelia
	 */
	public static List<ActPacienteIndHView> getIndHFact(final Properties ctx,
			final boolean isReqFactCE, final int pacienteID,
			final String fecha, String tipoArea, final String trxName)
			throws SQLException {

		final List<ActPacienteIndHView> resultados = new ArrayList<ActPacienteIndHView>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT SOL.* FROM  ( ");

		// Servicios de Pacientes
		sql.append(
				"( SELECT distinct(apih.EXME_ActPacienteIndH_ID), apih.documentNo, apih.DateOrdered, apih.DatePromised, ")
				.append("pac.EXME_Paciente_ID, pac.nombre_pac AS paciente, pac.value, ")
				.append("apih.description, wh.name as almacen, us.name as user_name, apih.docstatus, apih.IsService, ")
				.append("apih.M_Warehouse_ID, apih.C_Invoice_ID, apih.isInvoiced, apih.created, exp.documentno as mrn, apih.EXME_CtaPac_ID ")
				//.append(" ,api.exme_referinpatient_id ")
				.append("FROM EXME_ActPacienteIndH apih ")
				.append("INNER JOIN EXME_CtaPac cp ON (cp.EXME_CtaPac_ID = apih.EXME_CtaPac_ID)");

		if (StringUtils.isBlank(tipoArea)) {
			sql.append("INNER JOIN EXME_CtaPacDet cpd ON (cpd.EXME_CtaPac_ID = cp.EXME_CtaPac_ID AND cpd.IsFacturado = 'N' )");
		}

		sql.append(
				"INNER JOIN EXME_ActPaciente ap ON (ap.EXME_ActPaciente_ID = apih.EXME_ActPaciente_ID) ")
				
				.append("INNER JOIN EXME_Paciente pac ON (pac.EXME_Paciente_ID = ap.EXME_Paciente_ID  AND pac.IsActive = 'Y' ) ")
				.append("INNER JOIN EXME_Hist_Exp exp ON (exp.EXME_Paciente_ID = pac.EXME_Paciente_ID AND exp.AD_Org_ID = ? AND exp.AD_Client_ID = ?) ")
				.append("INNER JOIN M_Warehouse wh ON (wh.M_Warehouse_ID = apih.M_Warehouse_ID  AND wh.IsActive = 'Y' ) ")
				.append("INNER JOIN AD_User us ON (us.AD_User_ID = apih.CreatedBy AND us.IsActive = 'Y' ) ")
				.append("WHERE apih.IsActive = 'Y' ")
				.append(" AND apih.EXME_CtaPac_ID IS NOT NULL AND ap.EXME_CtaPac_ID IS NOT NULL ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						MEXMEActPacienteIndH.Table_Name, "apih"));
		if (StringUtils.isNotBlank(tipoArea)) {
			sql.append(" AND cp.tipoArea = " + DB.TO_STRING(tipoArea));
		}
		sql.append(" )");

		sql.append(
				") SOL WHERE ((SOL.C_Invoice_ID IS NULL AND SOL.isInvoiced = 'N') ")// Pendientes de Facturar
				.append(" OR 1=(Select distinct(1) From C_Invoice C Where C.Ref_Invoice_ID = SOL.C_Invoice_ID AND C.IsActive='Y')) ");

		if (pacienteID > 0)
			sql.append("AND SOL.EXME_Paciente_ID = ? ");

		if (fecha != null) {
			sql.append(" AND to_date(to_char(SOL.DatePromised,'DD/MM/YYYY'),'DD/MM/YYYY') >= to_Date(?,'DD/MM/YYYY') ");

		}
		
		if (StringUtils.isBlank(tipoArea)) {
			sql.append(" AND SOL.DocStatus = ")
				.append(DB.TO_STRING(MEXMEActPacienteIndH.DOCSTATUS_Completed))
				.append(" ORDER BY SOL.Created DESC");
		}
		

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			int j = 1;
			pstmt.setInt(j, Env.getAD_Org_ID(ctx));
			j++;
			pstmt.setInt(j, Env.getAD_Client_ID(ctx));
			j++;
			
			if (pacienteID > 0) {
				pstmt.setInt(j, pacienteID);
				j++;
			}
			if (fecha != null) {
				pstmt.setString(j, fecha);
			}

			rs = pstmt.executeQuery();
			final List<ValueNamePair> lstStatus = new ArrayList<ValueNamePair>();
			lstStatus.add(new ValueNamePair("AP", Msg.translate(ctx, "Solicitados")));
			lstStatus.add(new ValueNamePair("DR", Msg.translate(ctx, "Solicitados")));			
			lstStatus.add(new ValueNamePair("IP", Msg.translate(ctx, "Surtidos")));
			lstStatus.add(new ValueNamePair("VO", Msg.translate(ctx, "Cancelados"))); // voided
			lstStatus.add(new ValueNamePair("CO", Msg.translate(ctx, "AppliedAmt")));
			while (rs.next()) {
				final ActPacienteIndHView view = new ActPacienteIndHView();
				view.setIndHID(Long.toString(rs
						.getLong("EXME_ActPacienteIndH_ID")));
				
				view.setDocumentNo(rs.getString("documentno"));
				view.setFechaOrd(rs.getDate("dateordered"));
				view.setFechaProm(rs.getDate("datepromised"));
				view.setPaciente(rs.getString("paciente"));
//				view.setNoHistoria(rs.getString("value"));
				view.setDescription(rs.getString("description"));
				view.setAlmacen(rs.getString("almacen"));
				view.setUsuario(rs.getString("user_name"));
				view.setMrn(rs.getString("mrn"));
				view.setCtaPac(Long.toString(rs.getLong("EXME_CtaPac_ID")));
				
				// Encontrar el nombre del estatus del documento
				String estado = "";
				if (rs.getString("docstatus") != null) {
					for (ValueNamePair pair : lstStatus) {
						if (pair.getValue().equals(rs.getString("docstatus"))) {
							estado = pair.getName();
						}
					}
				}
				view.setDocStatus(estado);

				/*
				 * Y: Indica que es una solicitud de Servicio N: Indica que es
				 * una solicitud de Producto
				 */
				view.setIsService(rs.getString("IsService"));

				resultados.add(view);
			}
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return resultados;
	}

	/**
	 * Obtener solicitudes pendientes (para recordatorios)
	 * 
	 * @author Lizeth de la Garza
	 * @param ctx
	 * @param pacienteID
	 * @param trxName
	 * @return
	 */
	public static List<ValueNamePair> getPacSolPending(Properties ctx,
			int pacienteID, String trxName) {

		List<ValueNamePair> list = new ArrayList<ValueNamePair>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT actPacIndH.*  ");
		sql.append(" FROM EXME_ActPacienteIndH actPacIndH ");
		sql.append(" INNER JOIN EXME_ActPaciente actPac ON (actPac.EXME_ActPaciente_ID = actPacIndH.EXME_ActPaciente_ID ) ");
		sql.append(" WHERE actPac.EXME_Paciente_ID = ? ");
		sql.append(" AND actPacIndH.DocStatus IN (?,?) ");
		if (DB.isOracle()) {
			sql.append("AND trunc(actPacIndH.DateOrdered,'DD')>= ? ");
		} else if (DB.isPostgreSQL()) {
			sql.append("AND DATE_TRUNC('day', actPacIndH.DateOrdered)>= ? ");
		}

		sql.append(" ORDER BY actPacIndH.DateOrdered DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null; //

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacienteID);
			pstmt.setString(2, DOCSTATUS_Drafted);
			pstmt.setString(3, DOCSTATUS_WaitingConfirmation);
			pstmt.setTimestamp(4, TimeUtil.getDay(Env.getCurrentDate()));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				final StringBuilder str = new StringBuilder();
				str.append(Utilerias.getMsg(ctx, "title.solicitudServ"));
				str.append(rs.getString("DocumentNo"));
				str.append("-");
				str.append(Constantes.getSDFDateTime(ctx).formatConvert(rs.getTimestamp("DateOrdered")));

				final StringBuilder value = new StringBuilder();
				value.append(MEXMEProgRecordatorio.TIPORECORDATORIO_EXME_ActPacienteIndH);
				value.append("-").append(rs.getInt("EXME_ActPacienteIndH_ID"));

				list.add(new ValueNamePair(value.toString(), str.toString()));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Citas totales de un tratamiento
	 * 
	 * @return
	 */
	public int getVisitsOfTreatment() {
		return MEXMETratamientos.getNoCitas(getCtx(),
				getEXME_Tratamientos_ID(), get_TrxName());
	}

	/**
	 * Ultima cita de un tratamiento
	 * 
	 * @return
	 */
	public MEXMECitaMedica getLastVisitOfTreatment() {
		return MEXMETratamientos.getLastVisit(getCtx(),
				getEXME_Tratamientos_ID(), get_TrxName());
	}

	/**
	 * Siguiente cita de un tratamiento
	 * 
	 * @return
	 */
	public MEXMECitaMedica getNextVisitOfTreatment() {
		return MEXMETratamientos.getNextVisit(getCtx(),
				getEXME_Tratamientos_ID(), get_TrxName());
	}

	/**
	 * 
	 * @return
	 */
	public List<MEXMEActPacienteInd> getMedicationList() {
		return this.medicationList;
	}

	/**
	 * 
	 * @param medicationList
	 */
	public void setMedicationList(final List<MEXMEActPacienteInd> medicationList) {
		this.medicationList = medicationList;
	}

	/**
	 * Regresa en un mapa las prescripciones de medicamentos que se realizaron
	 * para un paciente en especifico
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public static List<MEXMEActPacienteIndH> getLstPrescripciones(
			final Properties ctx, final int EXME_Paciente_ID,
			final boolean todas, final boolean inactivas, final Date fecha,
			final String trxName) throws SQLException {

		List<MEXMEActPacienteIndH> list = new ArrayList<MEXMEActPacienteIndH>();
		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT apih.EXME_ActPacienteIndH_ID, api.EXME_ActPacienteInd_ID  ")
				.append(" FROM EXME_ActPaciente ap   ")
				.append(" INNER JOIN EXME_ActPacienteIndH apih ON apih.IsActive = 'Y'   ")
				.append("       AND ap.AD_Client_ID = apih.AD_Client_ID   ")
				.append("       AND ap.AD_Org_ID = apih.AD_Org_ID   ")
				.append("       AND ap.EXME_ActPaciente_ID = apih.EXME_ActPaciente_ID   ")
				.append("       AND apih.IsService = 'N' ")
				.append("       AND (apih.VALIDFROM > "
						+ DB.TO_DATE(DB.convert(ctx, new Timestamp(System.currentTimeMillis())))
						+ " OR apih.VALIDFROM is null) ")
				.append(" INNER JOIN EXME_ActPacienteInd  api  ON apih.EXME_ActPacienteIndH_ID = api.EXME_ActPacienteIndH_ID  ")
				.append(" WHERE ap.IsActive = 'Y'   ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						"EXME_ActPaciente", "ap"))
				.append(" AND ap.EXME_Paciente_ID = ?   ");

		// Inactivas
		if (!todas) {
			if (inactivas) {
				sql.append(" AND apih.EXME_MotivoCancel_ID IS NOT NULL  ");
			} else {
				sql.append(" AND apih.EXME_MotivoCancel_ID IS NULL  ");
			}
		}

		if (fecha != null) {
			sql.append(" AND apih.DateOrdered >= to_date(?, 'dd/MM/yyyy') ");
		}

		sql.append(" ORDER BY ap.created DESC, apih.created DESC, api.created DESC  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			if (fecha != null)
				pstmt.setString(2, Constantes.getSdfFecha().format(fecha));

			rs = pstmt.executeQuery();

			//
			MEXMEActPacienteIndH actPacIndH = null;
			int old_ID = 0;
			while (rs.next()) {

				if (rs.getInt(1) != old_ID) {
					if (actPacIndH != null
							&& actPacIndH.getEXME_ActPacienteIndH_ID() > 0) {
						list.add(actPacIndH);
					}
					actPacIndH = new MEXMEActPacienteIndH(ctx, rs.getInt(1),
							trxName);
				}

				// if(actPacIndH!=null && actPacIndH.getMedicationList()!=null
				// ){
				if (actPacIndH != null) {
					if (actPacIndH.getMedicationList() == null) {
						actPacIndH
								.setMedicationList(new ArrayList<MEXMEActPacienteInd>());
					}
					MEXMEActPacienteInd actPacInd = new MEXMEActPacienteInd(
							ctx, rs.getInt(2), trxName);
					if (actPacInd.getEXME_MotivoCancel_ID() > 0
							|| actPacIndH.getEXME_MotivoCancel_ID() > 0) {
						actPacIndH.setSuspendido(true);
					}
					actPacIndH.getMedicationList().add(actPacInd);
				}

				old_ID = rs.getInt(1);
			}

			// Agrega el ultimo registro
			if (actPacIndH != null) {
				list.add(actPacIndH);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getEntries", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * 
	 * @return
	 */
	public MEXMEEspecialidad getEspecialidad() {

		if (especialidad == null && getEXME_EspecialidadSol_ID() > 0)
			especialidad = new MEXMEEspecialidad(getCtx(),
					getEXME_EspecialidadSol_ID(), null);

		return especialidad;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSuspendido() {
		return suspendido;
	}

	public void setSuspendido(final boolean suspendido) {
		this.suspendido = suspendido;
	}

	/**
	 * Metodo que revisa si la factura a ser cancelada es una Factura de
	 * servicios. En caso de que si, se actualizan los campos correctos de la
	 * tabla de servicios.
	 * 
	 * @param facturaId
	 *            el ID de la factura a revisar en la tabla de Servicios.
	 * @param trxName
	 *            el nombre de la transaccion.
	 * @author Jesus Cantu el 31 de Marzo 2010.
	 */
	public static boolean isFactServicio(final Properties ctx,
			final int facturaId, final String trxName) {

		boolean resultado = true;

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * ").append(" FROM EXME_ActPacienteIndH  ")
				.append(" WHERE IsActive = 'Y' ")
				.append(" AND C_Invoice_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "EXME_ActPacienteIndH"));
		// sql.append(" ORDER BY EXME_ActPacienteIndH.DatePromised ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, facturaId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// trae resultados, por lo que si es un servicio el que se
				// facturo.
				// Se procede a actualizar la informacion de la tabla
				MEXMEActPacienteIndH objServ = new MEXMEActPacienteIndH(ctx,
						rs, trxName);
				objServ.setIsInvoiced(false);
				objServ.setC_Invoice_ID(0);
				if (!objServ.save())
					return false;

			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "isFactServicio", e);
			resultado = false;
		} finally {
			DB.close(rs, pstmt);
		}

		return resultado;
	}

	public String getDocStatusName() {
		return MRefList.getListName(getCtx(),
				DOCSTATUS_AD_Reference_ID, getDocStatus());
	}

	public String getTable_Name() {
		return Table_Name;
	}

	/**
	 * 
	 * @return
	 */
	public String getTitulo() {
		StringBuilder sb = new StringBuilder();

		if (getDateOrdered() != null)
			sb.append(Constantes.getSdfFecha().format(getDateOrdered()))
					.append(" ");
		if (getMedicoNombre() != null)
			sb.append(getMedicoNombre()).append(" ");
		if (getValidFrom() != null)
			sb.append(Constantes.getSdfFecha().format(getValidFrom()));
		return sb.toString();
	}

	/**
	 * 
	 * @return
	 */
	public MEXMEActPaciente getActPaciente() {
		if (actPaciente == null) {
			actPaciente = new MEXMEActPaciente(getCtx(),
					getEXME_ActPaciente_ID(), get_TrxName());
		}
		return actPaciente;
	}

	public void setActPaciente(final MEXMEActPaciente actPaciente) {
		this.actPaciente = actPaciente;
	}

	/**
	 * Datos de la activida paciente encabezado
	 * 
	 * @param actPaciente
	 */
//	private void setEXME_ActPaciente(final MEXMEActPaciente actPaciente) {
//		if (actPaciente == null) {
//			return;
//		}
//		this.actPaciente = actPaciente;
//
//		// Establecemos valores a partir de la Actividad Paciente.
//		setEXME_ActPaciente_ID(actPaciente.getEXME_ActPaciente_ID());
//		setAD_Org_ID(actPaciente.getAD_Org_ID());
//		setEXME_CtaPac_ID(actPaciente.getEXME_CtaPac_ID());
//	}

//	/**
//	 * 
//	 * @return
//	 */
//	public MEXMEInOut getInOut() {
//		return inOut;
//	}
//
//	public void setInOut(final MEXMEInOut inOut) {
//		this.inOut = inOut;
//	}

	/**
	 * 
	 * @param indH
	 * @return
	 * @deprecated not used
	 */
//	public MEXMEInOut createInOut() {
//		final MEXMEInOut m_InOut = new MEXMEInOut(getCtx(), 0, get_TrxName());
//		final Timestamp dateMovement = DB.getTimestampForOrg(getCtx());
//		final int docTypeID = MEXMEDocType.getOfName(getCtx(), "MM SHIPMENT",
//				null);
//
//		m_InOut.setEXME_ActPacienteIndH_ID(getEXME_ActPacienteIndH_ID());
//		m_InOut.setAD_OrgTrx_ID(getAD_OrgTrx_ID());
//		m_InOut.setAD_User_ID(Env.getAD_User_ID(getCtx()));
//		m_InOut.setC_BPartner_ID(getC_BPartner_ID());
//		if (getPaciente().getLocationPac() != null)
//			m_InOut.setC_BPartner_Location_ID(getPaciente().getLocationPac()
//					.getC_BPartner_Location_ID());
//		m_InOut.setC_DocType_ID(docTypeID);
//		m_InOut.setDateOrdered(dateMovement);
//		m_InOut.setDateAcct(dateMovement);
//		m_InOut.setDateReceived(dateMovement);
//		m_InOut.setDeliveryRule(MInOut.DELIVERYRULE_Availability);
//		m_InOut.setDeliveryViaRule(MInOut.DELIVERYVIARULE_Pickup);
//
//		m_InOut.setDocAction(MInOut.DOCACTION_Complete);
//		m_InOut.setDocStatus(MInOut.DOCSTATUS_InProgress);
//		m_InOut.setFreightAmt(Env.ZERO);
//		m_InOut.setFreightCostRule(MInOut.FREIGHTCOSTRULE_FreightIncluded);
//		m_InOut.setIsSOTrx(true);
//		m_InOut.setM_Warehouse_ID(getM_Warehouse_ID());
//		m_InOut.setMovementDate(dateMovement);
//		m_InOut.setMovementType(MEXMEInOut.MOVEMENTTYPE_CustomerShipment);
//		setInOut(m_InOut);
//		return m_InOut;
//	}

	/**
	 * 
	 * @return
	 */
	public MWarehouse getAlmacen() {
		return almacen;
	}

	/**
	 * 
	 * @param almacen
	 */
	public void setAlmacen(MWarehouse almacen) {
		this.almacen = almacen;
	}

	/**
	 * Merge Hrae 15384 FIN
	 */
	public static List<MEXMEActPacienteIndH> getTratamientosDetalle(
			final Properties ctx, final int id, final String trxName) {

		List<Integer> params = new ArrayList<Integer>();
		params.add(id);
		String sql = " SELECT * FROM EXME_ActPacienteIndH "
		// +
		// " inner join exme_cuestionario c on EXME_ActPacienteDet.exme_cuestionario_id = c.exme_cuestionario_id  "
				+ " WHERE EXME_TRATAMIENTOS_SESION_ID = ? ";
//		return MEXMEActPacienteIndH.get(ctx, sql, params, trxName);
		List<MEXMEActPacienteIndH> retorno = new ArrayList<MEXMEActPacienteIndH>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEActPacienteIndH mtd = new MEXMEActPacienteIndH(ctx, rs, trxName);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retorno;

	}

	/**
	 * 
	 * @param ctx
	 * @param sql
	 * @param params
	 * @param trxName
	 * @return
	 */
//	public static List<MEXMEActPacienteIndH> get(final Properties ctx,
//			final String sql, final List<?> params, final String trxName) {
//
//		List<MEXMEActPacienteIndH> retorno = new ArrayList<MEXMEActPacienteIndH>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			DB.setParameters(pstmt, params);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MEXMEActPacienteIndH mtd = new MEXMEActPacienteIndH(ctx, rs,
//						null);
//				retorno.add(mtd);
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getCitasMedico", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return retorno;
//	}

	/**
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @return
	 */
	public static List<LabelValueBean> getLVBPprescripciones(
			final Properties ctx, final int EXME_Paciente_ID) {
		ArrayList<LabelValueBean> list = new ArrayList<LabelValueBean>();

		try {
			List<MEXMEActPacienteIndH> indhs = getLstPrescripciones(ctx,
					EXME_Paciente_ID, true, true, null, null);
			for (MEXMEActPacienteIndH indh : indhs) {
				list.add(new LabelValueBean(indh.getDocumentNo()
						+ " "
						+ Constantes.getSdfFecha()
								.format(indh.getDateOrdered()), String
						.valueOf(indh.getEXME_ActPacienteIndH_ID())));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}

		return list;
	}

	@Override
	public boolean afterSave(final boolean newRecord, final boolean success) {
		if (!success) {
			return success;
		}
		// si se cancela la solicitud se inactivan los diagnosticos
		if ((is_ValueChanged(COLUMNNAME_DocStatus) && DOCSTATUS_Voided.equals(getDocStatus()))) {
			cancelDiag();
		}
		// actualizar orden de equipo med
		startDate = null;
		endDate = null;
		for (MEXMEEquipoH equip : MEXMEEquipoH.getActive(getCtx(), get_ID(), get_TrxName())) {
			if (MEXMEEquipoH.isAvailable(getCtx(), equip.getEXME_Equipo_ID(), equip.getEXME_EquipoH_ID(), getStartDate(), getEndDate(), get_TrxName())) {
				equip.setFecha_Ini(new Timestamp(getStartDate().getTime()));
				equip.setFecha_Fin(new Timestamp(getEndDate().getTime()));
				if (!equip.save()) {
					return false;
				}
			} else {
				s_log.log(Level.SEVERE, Utilerias.getMsg(getCtx(), "equipment overlap"));// TODO
			}
		}
		return success;
	}

	/**
	 * 
	 * @param ctx
	 * @param id
	 * @param trxName
	 * @return
	 */
	public static List<ServicioView> getDetalleMed(final Properties ctx,
			final int id, final String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" select ")
				.append(" rx.GENERIC_PRODUCT_NAME AS Med,  ")
				.append(" pd.Quantity AS Qty,   ")
				.append(" ro.Abrev AS Route,  ")
				.append(" f1.Name AS Freq1,   ")
				.append(" f2.Name AS Freq2,   ")
				.append(" pd.Indicaciones AS Indicaciones,  ")
				.append(" pd.Notas AS Notas, ")
				.append(" pd.EXME_PrescRXDet_ID AS Num ")
				.append(" 	, cta.DocumentNo ")
				.append(" 	, pac.Fechanac ")
				.append(" 	, NVL(rlt.Name, rl.Name) AS Sexo ")
				.append("   , REPLACE(REPLACE(REPLACE(PACVIEW.edad, '@d@' ,' days '), '@m@' ,' months '), '@y@' ,' year ') AS edocivil ")
				.append(" 	, pac.Nombre_pac ")
				.append(" 	, NVL(ctad.ARRIVALDATE, cta.Created) as Created ")
				.append(" , med.Nombre_med ")
				.append(" , pac.Value ")
				.append(" from EXME_PrescRXDet pd  ")
				.append(" 	LEFT JOIN EXME_PrescRx pr ON pd.EXME_PrescRx_ID = pr.EXME_PrescRx_ID  ")
				.append(" 	LEFT JOIN EXME_Frequency1 f1 ON pd.EXME_Frequency1_ID = f1.EXME_Frequency1_ID  ")
				.append(" 	LEFT JOIN EXME_Frequency2 f2 ON pd.EXME_Frequency2_ID = f2.EXME_Frequency2_ID  ")
				.append(" 	LEFT JOIN EXME_Route ro ON pd.EXME_Route_ID = ro.EXME_Route_ID  ")
				.append(" 	LEFT JOIN EXME_GenProduct rx ON pd.EXME_GenProduct_ID = rx.EXME_GenProduct_ID  ")
				.append(" LEFT JOIN EXME_CtaPac cta ON pr.EXME_CtaPac_ID = cta.EXME_CtaPac_ID  ")
				.append(" LEFT JOIN EXME_CtaPacDatos ctad ON cta.EXME_CtaPac_ID = ctad.EXME_CtaPac_ID   ")
				.append(" LEFT JOIN EXME_Paciente pac ON cta.EXME_Paciente_ID = pac.EXME_Paciente_ID  ")
				.append(" LEFT JOIN EXME_Medico med ON cta.EXME_Medico_ID = med.EXME_Medico_ID  ")
				.append(" LEFT JOIN AD_Ref_List rl ON rl.VALUE = pac.sexo AND rl.AD_Reference_ID = ?  ")
				.append(" LEFT JOIN AD_Ref_List_trl rlt ON rl.AD_Ref_List_ID = rlt.AD_Ref_List_ID AND AD_Language = ? ")
				.append(" LEFT JOIN EXME_EDADPACIENTE_V PACVIEW ON pac.exme_paciente_id = pacview.exme_paciente_id ")

				.append(" WHERE pd.IsActive = 'Y'  ")
				.append(" AND pd.EXME_PrescRx_ID = ? ");

		final List<Object> params = new ArrayList<Object>();
		params.add(X_EXME_Paciente.SEXO_AD_Reference_ID);
		params.add(Env.getContext(ctx, "#AD_Language"));
		params.add(id);

		final List<ServicioView> retorno = new ArrayList<ServicioView>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
				ServicioView serv = new ServicioView();
				serv.setSecuencia(count);
				serv.setProdName(rs.getString("Med"));
				serv.setCantidad(rs.getInt("Qty")); // dose
				serv.setQtyOrdered(rs.getBigDecimal("Qty")); // dose
				serv.setTipoDosis(rs.getString("Route"));
				serv.setFreq1(rs.getString("Freq1"));
				serv.setFreq2(rs.getString("Freq2"));
				serv.setDescripcion(rs.getString("Indicaciones"));
				serv.setComments(rs.getString("Notas"));

				retorno.add(serv);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retorno;

	}

	/**
	 * 
	 * @param ctx
	 * @param EXME_EstServ_ID
	 * @param fechaIni
	 * @param fechaFin
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEActPacienteIndH> get(final Properties ctx,
			final int EXME_EstServ_ID, final Date fechaIni,
			final Date fechaFin, final String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEActPacienteIndH> lst = new ArrayList<MEXMEActPacienteIndH>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  (SELECT ");
			sql.append("      indh.*, ");
			sql.append("      CASE WHEN indh.docstatus = '");
			sql.append(MEXMEActPacienteIndH.DOCSTATUS_Drafted);
			sql.append("' THEN indh.dateordered ELSE indh.datepromised END myDate ");
			sql.append("    FROM ");
			sql.append("      exme_actpacienteindh indh ");
			sql.append("      INNER JOIN m_warehouse w ");
			sql.append("      ON indh.m_warehouse_id = w.m_warehouse_id ");
			sql.append("      INNER JOIN exme_estservalm esa ");
			sql.append("      ON w.m_warehouse_id = esa.m_warehouse_id ");
			sql.append("      INNER JOIN exme_estserv es ");
			sql.append("      ON esa.exme_estserv_id = es.exme_estserv_id ");
			sql.append("    WHERE ");
			sql.append("      es.exme_estserv_id = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
					MEXMEActPacienteIndH.Table_Name, "indh"));
			sql.append("  ) as subIndh ");
			sql.append("WHERE ");
			sql.append("  mydate BETWEEN ");
			sql.append("  ? AND ");
			sql.append("  ? ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_EstServ_ID);
			pstmt.setTimestamp(2, new Timestamp(fechaIni.getTime()));
			pstmt.setTimestamp(3, new Timestamp(fechaFin.getTime()));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEActPacienteIndH(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}

	/**
	 * Obtener solicitudes pendientes (para recordatorios)
	 * 
	 * @author Lizeth de la Garza
	 * @param ctx
	 * @param pacienteID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEActPacienteIndH> getPacienteSolPending(
			final Properties ctx, final int ctaPacID, 
			final boolean chargesCompleted, final String trxName) {

		List<MEXMEActPacienteIndH> list = new ArrayList<MEXMEActPacienteIndH>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT DISTINCT indH.*  ");
		sql.append("FROM EXME_ActPacienteIndH indH ");
		sql.append("INNER JOIN EXME_ActPacienteInd ind ON (ind.EXME_ActPacienteIndH_ID=indH.EXME_ActPacienteIndH_ID \n");
		sql.append("                                  AND ind.Surtir='Y') ");// solo internas
		if(chargesCompleted) { // Card #1173 - Lama
			sql.append("INNER JOIN M_Warehouse w ON (indH.M_Warehouse_ID=w.M_Warehouse_ID) \n");
			sql.append("WHERE indH.isActive='Y' AND indH.EXME_CtaPac_ID=? \n");
			// cualquier orden no completada
			sql.append("AND (indH.DocStatus NOT IN (?,?,?) \n");
			// cuyo almacen NO sea hl7
			sql.append("     AND (w.HL7ORM ='N' \n");
			sql.append("         OR indH.EXME_ActPacienteIndH_ID IN (\n");
			sql.append("             SELECT DISTINCT ind.EXME_ActPacienteIndH_ID FROM EXME_ActPacienteInd ind \n");
			sql.append("             LEFT JOIN EXME_CtaPacDet det ON (ind.EXME_ActPacienteInd_ID=det.EXME_ActPacienteInd_ID)\n");
			sql.append("             WHERE ind.EXME_ActPacienteIndH_ID=indH.EXME_ActPacienteIndH_ID\n");
			// o NO tenga cargo
			sql.append("             AND det.EXME_CtaPacDet_ID IS NULL)\n");
			sql.append("         )\n");
			sql.append("    )\n");
		} else {
			sql.append("WHERE indH.isActive='Y' AND indH.EXME_CtaPac_ID=? ");
			// cualquier orden no completada
			sql.append("AND indH.DocStatus NOT IN (?,?,?) ");
		}
		sql.append("AND indH.IsService='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "indH"));
		sql.append(" ORDER BY indH.DateOrdered DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null; //

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			pstmt.setString(2, DOCSTATUS_Completed);
			pstmt.setString(3, DOCSTATUS_Voided);
			pstmt.setString(4, DOCSTATUS_Closed);
//			pstmt.setString(2, DOCSTATUS_Drafted);
//			pstmt.setString(3, DOCSTATUS_WaitingConfirmation);
//			pstmt.setString(4, DOCSTATUS_InProgress);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMEActPacienteIndH(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * 
	 * @param newDate
	 * @param endDate
	 * @return
	 */
	public boolean canMove(final Date newDate, final Date endDate) {
//		List<MEXMEEquipoH> lst = MEXMEEquipoH.getEquiposH(getCtx(), getEXME_ActPacienteIndH_ID(), null);
//		int min = getM_Warehouse().getIntervalo();
//		if (min == 0) {
//			min = 30;
//		}
//		Date end = new DateTime(new Date(newDate.getTime())).plusMinutes(min).toDate();
		for (MEXMEEquipoH lb : MEXMEEquipoH.getActive(getCtx(), getEXME_ActPacienteIndH_ID(), null)) {
			if (!MEXMEEquipoH.isAvailable(getCtx(), lb.getEXME_Equipo_ID(), lb.getEXME_EquipoH_ID(), newDate, endDate, null)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param newDate
	 * @param endDate
	 */
	public void move(final Date newDate, final Date endDate) {
//		Trx trx = null;
//		try {
//			int min = getM_Warehouse().getIntervalo();
//			if (min == 0) {
//				min = 30;
//			}
//			Date end = new DateTime(new Date(newDate.getTime())).plusMinutes(min).toDate();
			setDatePromised(new Timestamp(newDate.getTime()));
			setFecha_Fin(new Timestamp(endDate.getTime()));
			save(); //se movio al after save
//			trx = Trx.get(Trx.createTrxName("er"), true);
//			if (save(trx.getTrxName())) {
//				final List<MEXMEEquipoH> lst = MEXMEEquipoH.getEquiposH(getCtx(), getEXME_ActPacienteIndH_ID(), null);
//
//				for (MEXMEEquipoH equipoH : lst) {
//					equipoH.setFecha_Ini(new Timestamp(getDatePromised().getTime()));
//					equipoH.setFecha_Fin(new Timestamp(endDate.getTime()));
//					if (!equipoH.save(trx.getTrxName())) {
//						Trx.rollback(trx, true);
//						return;
//					}
//				}
//				Trx.commit(trx, true);
//			}
//		} catch (Exception ex) {
//			Trx.rollback(trx, true);
//		} finally {
//			Trx.close(trx, true);
//		}
	}

	/**
	 * 
	 * @param ctx
	 * @param PacienteID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEActPacienteIndH> getStudiesRef(
			final Properties ctx, final int PacienteID, final String trxName) {
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		List<MEXMEActPacienteIndH> list = new ArrayList<MEXMEActPacienteIndH>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			sql.append(
					"SELECT DISTINCT EXME_ActPacienteIndH.* from EXME_ActPacienteIndH  ")
					.append("INNER JOIN EXME_ActPacienteInd ON (EXME_ActPacienteIndH.EXME_ActPacienteIndH_ID = EXME_ActPacienteInd.EXME_ActPacienteIndH_ID) ")
					.append("INNER JOIN M_Product ON (EXME_ActPacienteInd.M_Product_ID = M_Product.M_Product_ID AND M_Product.ProductClass IN ('XR','LA')) ")
					.append("INNER JOIN EXME_ActPaciente ON (EXME_ActPacienteIndH.EXME_ActPaciente_ID = EXME_ActPaciente.EXME_ActPaciente_ID AND EXME_ActPaciente.EXME_Paciente_ID = ?) ")
					.append("WHERE EXME_ActPacienteIndH.IsService = 'Y' AND EXME_ActPacienteIndH.EXME_Tratamientos_ID IS NULL ")
					.append("and exme_actpacienteindh.status = 'CO' and EXME_ActPacienteInd.exme_referinpatient_ID > 0 ")
					.append("ORDER BY EXME_ActPacienteIndH.DateOrdered DESC ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, PacienteID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMEActPacienteIndH(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return list;
	}
	
//    /**
//	 * Prioridades de la solicitud de servicios
//	 * @param ctx Contexto
//	 * @param priorityMap <Value,Color> Value de la lista de referencia y color de la prioridad
//	 * @return <Value,<Name, Color>>Values de la lista, Nombre en idioma de login y color
//	 */
//    public static Map<String,ValueNamePair> getPriorityVN(Properties ctx, Map<String, String>	priorityMap){
//    	Map<String,ValueNamePair>	priority = new HashMap<String,ValueNamePair>();
//    	List<ValueNamePair> lst = MRefList.getList(ctx, X_EXME_ActPacienteIndH.PRIORITYRULE_AD_Reference_ID, false, null);
//    	for (ValueNamePair pair : lst) {
//    		if(!priority.containsKey(pair.getValue()) && priorityMap.containsKey(pair.getValue())){
//    			priority.put(pair.getValue(), 
//    				new ValueNamePair(priorityMap.get(pair.getValue()), pair.getName()));
//    		}
//		}
//    	return priority;
//    }
	/**
	 * 
	 * @author 
	 * 
	 */
	public static class Service {
		private String service;
		private Date datePromised;
		private String almacenSurte;
		private String almacenSol;
		private String priorityRule;
		private String status;

		public String getService() {
			return service;
		}

		public void setService(String service) {
			this.service = service;
		}

		public Date getDatePromised() {
			return datePromised;
		}

		public void setDatePromised(Date datePromised) {
			this.datePromised = datePromised;
		}

		public String getAlmacenSurte() {
			return almacenSurte;
		}

		public void setAlmacenSurte(String almacenSurte) {
			this.almacenSurte = almacenSurte;
		}

		public String getAlmacenSol() {
			return almacenSol;
		}

		public void setAlmacenSol(String almacenSol) {
			this.almacenSol = almacenSol;
		}

		public String getPriorityRule() {
			return priorityRule;
		}

		public void setPriorityRule(String priorityRule) {
			this.priorityRule = priorityRule;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}
	
	public static List<Service> get(Properties ctx, int encounterFormId, String trxName) {
		return get(ctx, encounterFormId, -1, -1, trxName);
	}
    
	public static List<Service> get(Properties ctx, int encounterFormId, int patientId, int ctapacId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  p.Name ");
		sql.append("  AS service, ");
		sql.append("  actInd.DatePromised, ");
		sql.append("  war.Name ");
		sql.append("  AS almacenSurte, ");
		sql.append("  warSol.Name ");
		sql.append("  AS almacenSol, ");
		sql.append("  EXME_ActPacienteIndH.priorityRule, ");
		sql.append("  actInd.EStatus ");
		sql.append("FROM ");
		sql.append("  EXME_ActPacienteIndH ");
		sql.append("  INNER JOIN EXME_ActPacienteInd actInd ");
		sql.append("  ON (actInd.EXME_ActPacienteIndH_id = EXME_ActPacienteIndH.EXME_ActPacienteIndH_id AND ");
		sql.append("  actind.IsActive ='Y' ) ");
		sql.append("  INNER JOIN EXME_ActPaciente act ");
		sql.append("  ON (act.EXME_actpaciente_id = EXME_ActPacienteIndH.EXME_actpaciente_id AND ");
		sql.append("  act.IsActive ='Y' ) ");
		sql.append("  INNER JOIN M_Product p ");
		sql.append("  ON (actInd.M_Product_ID = p.M_Product_ID) LEFT ");
		sql.append("  JOIN EXME_CtaPac ctapac ");
		sql.append("  ON (ctapac.EXME_ctapac_id = EXME_ActPacienteIndH.EXME_ctapac_id) LEFT ");
		sql.append("  JOIN EXME_Paciente pac ");
		sql.append("  ON (pac.EXME_paciente_id = act.EXME_paciente_id) LEFT ");
		sql.append("  JOIN EXME_Medico med ");
		sql.append("  ON (med.EXME_medico_id = EXME_ActPacienteIndH.EXME_medicoSol_id) LEFT ");
		sql.append("  JOIN M_Warehouse warSol ");
		sql.append("  ON (warSol.M_Warehouse_ID = EXME_ActPacienteIndH.M_Warehouse_Sol_ID) LEFT ");
		sql.append("  JOIN M_Warehouse war ");
		sql.append("  ON (war.M_Warehouse_ID = EXME_ActPacienteIndH.M_Warehouse_ID) ");
		sql.append("WHERE ");
		sql.append("  EXME_ActPacienteIndH.IsActive = 'Y' AND ");
		sql.append("  exme_ActPacienteIndH.IsService = 'Y' ");
		final int paramId;
		if(encounterFormId > 0){
			paramId = encounterFormId;
			sql.append(" AND EXME_ActPacienteIndH.exme_encounterforms_id = ? ");
		} else if(ctapacId > 0){
			paramId = ctapacId;
			sql.append(" AND ctapac.exme_ctapac_id = ? ");
		} else {
			paramId = patientId;
			sql.append(" AND ctapac.exme_paciente_id = ? ");
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		List<Service> list = new ArrayList<Service>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, paramId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Service presc = new Service();
				presc.setAlmacenSol(rs.getString("almacenSol"));
				presc.setAlmacenSurte(rs.getString("almacenSurte"));
				presc.setDatePromised(rs.getTimestamp("DatePromised"));
				presc.setPriorityRule(MRefList.getListName(ctx, MEXMEActPacienteIndH.PRIORITYRULE_AD_Reference_ID, StringUtils.defaultIfEmpty(rs.getString("priorityRule"), "").trim()));
				presc.setService(rs.getString("service"));
				presc.setStatus(MRefList.getListName(ctx, MEXMEActPacienteInd.ESTATUS_AD_Reference_ID, rs.getString("EStatus")));
				list.add(presc);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * listado de indicaciones medicas encabezado
	 * 
	 * @param ctx
	 * @param trxName
	 * @param whereClause
	 * @param orderBy
	 * @return
	 */
	public static List<MEXMEActPacienteIndH> getList(final Properties ctx,
			final String trxName, final String whereClause, final String orderBy) {
		return getList(ctx, trxName, whereClause, orderBy, null);
	}

	/**
	 * listado de indicaciones medicas encabezado
	 * 
	 * @param ctx
	 * @param trxName
	 * @param whereClause
	 * @param orderBy
	 * @return
	 */
	public static List<MEXMEActPacienteIndH> getList(final Properties ctx,
			final String trxName, final String whereClause,
			final String orderBy, final List<Object> params) {

		final List<MEXMEActPacienteIndH> list = new ArrayList<MEXMEActPacienteIndH>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_ActPacienteIndH ");
		if (whereClause != null) {
			sql.append(" WHERE ").append(whereClause);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				X_EXME_ActPacienteIndH.Table_Name));

		if (orderBy != null) {
			sql.append(" ORDER BY ").append(orderBy);
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (params != null && params.size() > 0) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEActPacienteIndH(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;

	}

	/**
	 * arreglo de indicaciones medicas encabezado
	 * 
	 * @param ctx
	 * @param trxName
	 * @param whereClause
	 * @param orderBy
	 * @return
	 */
	public static MEXMEActPacienteIndH[] get(final Properties ctx, final String trxName, final String whereClause, final String orderBy) {

		final List<MEXMEActPacienteIndH> lista = MEXMEActPacienteIndH.getList(ctx, trxName, whereClause, orderBy);
		final MEXMEActPacienteIndH[] actPacIndHs = new MEXMEActPacienteIndH[lista.size()];
		lista.toArray(actPacIndHs);
		return actPacIndHs;
	}

	/**
	 * Obtiene una lista con toda la informacion del encabezado de las
	 * solicitudes de servicio filtradas por almacen y fecha
	 * 
	 * @param ctx
	 * @param almacenSol
	 *            El almacen que solicita los servicios
	 * @param almacenSur
	 *            El almacen que aplica los servicios
	 * @param reqFactIndH
	 *            Indica si se requiere que el servicio haya sido facturado
	 * @param fecha
	 *            La fecha de aplicacion del servicio
	 * @param completed
	 *            - sol. con estado completado
	 * @return Una lista de objetos ActPacienteIndHView
	 * @throws Exception
	 */
	public static List<ActPacienteIndHView> getServicios(final Properties ctx,
			final int almacenSol, final int almacenSur,
			final boolean reqFactIndH, final Timestamp fecha,
			final Timestamp end, final boolean completed, final String where,
			final Object... params) {

		final List<ActPacienteIndHView> resultados = new ArrayList<ActPacienteIndHView>();
		final List<Object> parameters = new ArrayList<Object>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT DISTINCT EXME_ActPacienteIndH.*, ");
		sql.append(" med.Nombre_Med AS medico, ");
		sql.append(" pac.Nombre_Pac AS paciente, pac.EXME_Paciente_ID, ");
		sql.append(" ctapac.DocumentNo AS ctapac, ");
		sql.append("  war.Name AS almacenSol, ");
		sql.append("ind.exme_referinpatient_id as referral");

		sql.append(" FROM EXME_ActPacienteIndH ");
		sql.append(" INNER JOIN EXME_ActPacienteInd ind on (ind.EXME_ActPacienteIndh_id = EXME_ActPacienteIndh.EXME_ActPacienteIndh_id) ");
		sql.append(" INNER JOIN EXME_ActPaciente act ON (act.EXME_actpaciente_id = EXME_ActPacienteIndH.EXME_actpaciente_id AND act.IsActive = 'Y') ");
		sql.append(" LEFT JOIN EXME_CtaPac ctapac ON (ctapac.EXME_ctapac_id = EXME_ActPacienteIndH.EXME_ctapac_id) ");
		sql.append(" LEFT JOIN EXME_Paciente pac ON (pac.EXME_paciente_id = act.EXME_paciente_id) ");
		sql.append(" LEFT JOIN EXME_Medico med ON (med.EXME_medico_id = EXME_ActPacienteIndH.EXME_medicoSol_id) ");
		sql.append(" LEFT JOIN M_Warehouse war ON (war.M_Warehouse_ID = EXME_ActPacienteIndH.M_Warehouse_Sol_ID) ");

		sql.append(" WHERE EXME_ActPacienteIndH.IsActive = 'Y' ");
		sql.append("AND EXME_ActPacienteIndH.IsService = 'Y' ");
		sql.append(" AND ind.Surtir = 'Y'");// Ticket #08004 jcervantes
		if (almacenSur > 0) {
			sql.append(" AND EXME_ActPacienteIndH.M_Warehouse_ID = ? "); // #1
			// sql.append(" AND Ind.M_Warehouse_ID IS NOT NULL ");
			parameters.add(almacenSur);
		}
		if (fecha != null || end != null) {
			if (DB.isOracle()) {
				sql.append(" AND TRUNC(EXME_ActPacienteIndH.DatePromised,'DD')");
			} else if (DB.isPostgreSQL()) {
				sql.append(" AND DATE_TRUNC('day', EXME_ActPacienteIndH.DatePromised)");
			}
			if (end == null) {
				sql.append("=? "); // #2
				parameters.add(fecha);
			} else {
				sql.append("BETWEEN ? AND ? ");
				parameters.add(fecha);
				parameters.add(end);
			}
		}
		// sql.append(" AND EXME_ActPacienteIndH.IsDelivered = ? ");// 'N' #3
		if (almacenSol > 0) {
			sql.append(" AND EXME_ActPacienteIndH.M_Warehouse_Sol_ID=? "); // #4
			parameters.add(almacenSol);
		}
		sql.append(" AND EXME_ActPacienteIndH.DocStatus IN ");
		if (completed) {
			sql.append("(?)"); // #...n
			parameters.add(DOCSTATUS_Completed);
		} else {
			sql.append("(?,?,?)"); // #...n
			parameters.add(DOCSTATUS_Drafted);
			parameters.add(DOCSTATUS_InProgress);
			parameters.add(DOCSTATUS_Approved);
		}

		// Si esta configurado como requiere facturar indicaciones antes de
		// aplicarlos,
		// solo muestra: (Noelia)
		// a. Solicitudes facturadas a pacientes externos o citas medicas
		// b. Solicitudes a pacientes internos, cuenta paciente
		if (reqFactIndH) {
			sql.append("AND ((EXME_ActPacienteIndH.C_Invoice_ID IS NOT NULL ");
			sql.append("AND EXME_ActPacienteIndH.isInvoiced = 'Y') ");
			sql.append("OR EXME_ActPacienteIndH.EXME_CtaPac_ID IS NOT NULL  ");
			sql.append("OR act.EXME_CtaPac_ID IS NOT NULL) ");
		}

		if (StringUtils.isNotBlank(where)) {
			if (!where.trim().startsWith("AND")) {
				sql.append(" AND ");
			}
			sql.append(where);
			if (params != null) {
				parameters.addAll(Arrays.asList(params));
			}
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_ActPacienteIndH.DateDelivered DESC, EXME_ActPacienteIndH.DatePromised, PriorityRule");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, parameters);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final ActPacienteIndHView view = new ActPacienteIndHView();
				final MEXMEActPacienteIndH model = new MEXMEActPacienteIndH(ctx, rs, null);// Lama
				view.setValues(model);
				view.setValues(rs, 2);
				view.setMedico(rs.getString("medico"));
				view.setReferralID(rs.getInt("referral"));
				resultados.add(view);
			}

		} catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sql.toString(), sqle);
			resultados.clear();
		} finally {
			DB.close(rs, pstmt);
		}

		return resultados;
	}

//	/**
//	 * Obtenemos los datos de una solicitud de servicios
//	 * 
//	 * @param medico
//	 *            El medico a obtener sus citas
//	 * @param fecha
//	 *            La fecha de las citas
//	 * @return El resultset con la informacion de las citas
//	 * @deprecated not used
//	 */
//	public static ServicioHdrView getSolicitudServ(final Properties ctx,
//			final long indHID) {
//		ServicioHdrView servicio = null;
//
//		final StringBuilder sql = new StringBuilder(
//				Constantes.INIT_CAPACITY_ARRAY);
//		// buscamos el medico y paciente en citas
//		sql.append("SELECT ind.EXME_ActPacienteIndH_ID AS indID, ")
//				.append(" med.Nombre_Med AS medico, med.noConsultorio, ")
//				.append(" pac.Nombre_Pac AS paciente,  exp.DocumentNo as Expediente, ")
//				.append(" pac.Value, pac.esAsegurado, EXME_ActPacienteIndH.DocumentNo, esp.Name AS especialidad, ctapac.DocumentNo AS ctapac, ")
//				.append(" doc.Name AS documentType, EXME_ActPacienteIndH.DatePromised fechaOrd, ")
//				.append(" EXME_ActPacienteIndH.PriorityRule, EXME_ActPacienteIndH.DatePromised Fecha, ")
//				.append(" EXME_ActPacienteIndH.Diagnostico, EXME_ActPacienteIndH.Description, ")
//				.append(" EXME_ActPacienteIndH.DatePromised Hora, EXME_ActPacienteIndH.DatePromised Minutos, war.Name AS almacenSol ")
//				.append(" FROM EXME_ActPacienteIndH INNER JOIN EXME_ActPacienteInd ind ")
//				.append(" ON (EXME_ActPacienteIndH.EXME_ActPacienteIndH_ID = ind.EXME_ActPacienteIndH_ID AND ind.IsActive = 'Y') ")
//				.append(" LEFT JOIN C_DocType doc ON (doc.C_DocType_ID = EXME_ActPacienteIndH.C_DocType_ID) ")
//				.append(" LEFT JOIN EXME_CtaPac ctapac ON (ctapac.EXME_ctapac_id = EXME_ActPacienteIndH.EXME_ctapac_id AND ctapac.isActive = 'Y' )  ")
//				.append(" INNER JOIN EXME_ActPaciente act ON (act.EXME_actpaciente_id = EXME_ActPacienteIndH.EXME_actpaciente_id AND act.IsActive = 'Y' ) ")
//				.append(" LEFT JOIN EXME_Paciente pac ON (pac.EXME_paciente_id = act.EXME_paciente_id AND pac.IsActive = 'Y' ) ")
//				// freyes solo un mrn activo por Org
//				.append(" LEFT JOIN EXME_Hist_Exp exp ON (exp.EXME_Paciente_ID = pac.EXME_Paciente_ID AND exp.isActive ='Y' ")
//				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
//						MEXMEHistExp.Table_Name, "exp"))
//				.append(") ")
//				.append(" LEFT JOIN EXME_Medico med ON (med.EXME_medico_id = act.EXME_medico_id) ")
//				.append(" LEFT JOIN EXME_Especialidad esp ON (esp.EXME_especialidad_id = act.EXME_especialidad_id) ")
//				.append(" LEFT JOIN M_Warehouse war ON (war.M_Warehouse_ID = EXME_ActPacienteIndH.M_Warehouse_Sol_ID) ")
//				.append(" WHERE EXME_ActPacienteIndH.IsActive = 'Y' AND EXME_ActPacienteIndH.EXME_ActPacienteIndH_ID = ? ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setLong(1, indHID);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				// asignamos el numero de documento
//				servicio = new ServicioHdrView();
//				servicio.setDocumentNo(rs.getString("DocumentNo"));
//				servicio.setHistoria(rs.getString("Value"));
//				servicio.setPaciente(SecureEngine.decrypt(rs
//						.getString("Paciente")));
//				servicio.setAsegurado(rs.getString("EsAsegurado")
//						.equalsIgnoreCase("N") ? Msg.translate(ctx, "No") : Msg
//						.translate(ctx, "Yes"));
//				servicio.setMedico(rs.getString("Medico"));
//				servicio.setEspecialidad(rs.getString("Especialidad"));
//				servicio.setConsultorio(rs.getString("NoConsultorio"));
//				servicio.setFechaOrd(Constantes.getSDFDateTime(ctx).format(
//						DB.convert(ctx, rs.getTimestamp("FechaOrd"))));
//				servicio.setFechaAplica(Constantes.getSDFDateTime(ctx).format(
//						DB.convert(ctx, rs.getTimestamp("Fecha"))));
//				servicio.setHoraAplica(Constantes.getSdfHora24().format(
//						DB.convert(ctx, rs.getTimestamp("Hora"))));
//				servicio.setMinutosAplica(Constantes.getSdfMinutos().format(
//						DB.convert(ctx, rs.getTimestamp("Minutos"))));
//				servicio.setPrioridad(rs.getString("PriorityRule"));
//				servicio.setAlmacenSol(rs.getString("almacenSol"));
//				servicio.setCtaPac(rs.getString("ctapac"));
//				servicio.setExpediente(rs.getString("Expediente"));
//				servicio.setDescription(rs.getString("Description"));
//				servicio.setDiagnostico(rs.getString("Diagnostico"));
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, e.getMessage(), e);
//			servicio = null;
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return servicio;
//	}

//	/**
//	 * Consulta de servicios
//	 * 
//	 * @param ctx
//	 * @param client
//	 *            NOT USED
//	 * @param org
//	 *            NOT USED
//	 * @param ctaPac
//	 * @param paciente
//	 * @param status
//	 * @param allStatus
//	 * @param parentID
//	 * @return
//	 * @deprecated not used 
//	 *  use
//	 *             {@link #getServicios(Properties, int, int, String, boolean, int, String, Object...)}
//	 */
//	public static List<ActPacienteIndHView> getServicios(Properties ctx,
//			long client, long org, long ctaPac, long paciente, String status,
//			boolean allStatus, int parentID) {
//		return getServicios(ctx, ctaPac, paciente, status, allStatus, parentID,
//				null);
//	}

	/**
	 * Consulta de servicios
	 * 
	 * @param ctx
	 * @param ctaPac
	 * @param paciente
	 * @param status
	 * @param allStatus
	 * @param parentID
	 * @param whereClause
	 * @param params
	 * @return
	 */
	public static StringBuilder getSQLServicios(final Properties ctx,
			final int ctaPac, final int paciente, final boolean allStatus,
			final int parentID, /* String encounterStatus, */
			final String whereClause, final boolean count) {

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		if (count) {
			sql.append(" SELECT count(EXME_ActPacienteIndH.EXME_ActPacienteIndH_ID) as total ");
		} else {
			sql.append(" SELECT DISTINCT EXME_ActPacienteIndH.*, ")
					.append(" pac.Nombre_Pac AS paciente, pac.EXME_Paciente_ID, ")
					.append(" pac.value, pac.esAsegurado, ")
					.append(" esp.name AS especialidad, doc.name AS documentType, ")
					.append(" ctapac.documentNo AS ctapac, ")
					.append(" us.name AS user_name, ")
					.append(" wh.name AS almacen, war.Name AS almacenSol, ")

					.append(" (SELECT Count(obx.EXME_EstudiosOBX_ID) FROM EXME_EstudiosOBX obx ")
					.append(" INNER JOIN EXME_ACTPACIENTEIND ind ON (")
					// Lama
					.append(" obx.EXME_ActPacienteInd_ID = ind.EXME_ActPacienteInd_ID ")
					.append(" AND ind.IsActive = 'Y')")
					.append(" WHERE ind.EXME_actpacienteindh_id = EXME_ACTPACIENTEINDH.EXME_ACTPACIENTEINDH_id ")
					.append(" ) obxCount ");
		}

		sql.append(" FROM EXME_ACTPACIENTEINDH  ");
		sql.append(" INNER JOIN EXME_ACTPACIENTE act ON (act.EXME_actpaciente_id = EXME_ActPacienteIndH.EXME_actpaciente_id  AND act.IsActive = 'Y' ) ");
		if (!count) {
			sql.append(
					" LEFT JOIN C_DOCTYPE doc ON (doc.c_doctype_id = EXME_ActPacienteIndH.c_doctype_id  ) ")
					.append(" LEFT JOIN EXME_CTAPAC ctapac ON (ctapac.EXME_ctapac_id = EXME_ActPacienteIndH.EXME_ctapac_id  AND ctapac.IsActive = 'Y') ")
					.append(" LEFT JOIN EXME_PACIENTE pac ON (pac.EXME_paciente_id = act.EXME_paciente_id AND pac.IsActive = 'Y' ) ")
					.append(" LEFT JOIN M_WAREHOUSE wh ON (wh.m_warehouse_id = EXME_ActPacienteIndH.m_warehouse_id ) ")
					.append(" LEFT JOIN M_Warehouse war ON (war.M_Warehouse_ID = EXME_ActPacienteIndH.M_Warehouse_Sol_ID ) ")
					.append(" LEFT JOIN EXME_ESPECIALIDAD esp ON (esp.EXME_especialidad_id = act.EXME_especialidad_id ) ")
					.append(" LEFT JOIN AD_USER us ON (us.ad_user_id = EXME_ActPacienteIndH.createdby ) ");
		}
		sql.append(" WHERE EXME_ActPacienteIndH.isservice='Y' AND EXME_ActPacienteIndH.isActive='Y' ");

		if (ctaPac > 0) {
			sql.append(" AND EXME_ActPacienteIndH.EXME_CtaPac_ID=? ");// Solo
																		// las
																		// solicitudes
																		// de la
																		// cuenta
																		// paciente
		} else if (paciente > 0) {
			sql.append(" AND act.EXME_Paciente_ID=? ");// Solicitudes ligadas al
														// paciente (Correccion
														// de SQLException)
		}
		if (!allStatus) {
			sql.append(" AND EXME_ActPacienteIndH.docstatus=? ");
		}
		if (parentID > 0) {
			sql.append(" AND (EXME_ActPacienteIndH.Parent_ID=? OR EXME_ActPacienteIndH.EXME_ActPacienteIndH_ID=?) ");
		}
		// if (StringUtils.isNotEmpty(encounterStatus)) {
		// sql.append(" AND ctaPac.EncounterStatus = ? ");// Solo si la cuenta
		// Pac esta en un status en particular
		// }
		if (StringUtils.isNotBlank(whereClause)) {
			sql.append(whereClause);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		if (!count) {
			// this one blows in postgresql if we're counting :
			// org.postgresql.util.PSQLException: ERROR: column
			// "exme_actpacienteindh.documentno"
			// must appear in the GROUP BY clause or be used in an aggregate
			// function
			sql.append(" ORDER BY EXME_ActPacienteIndH.documentno DESC");
		}

		return sql;
	}

	/**
	 * Busca los servicios (M_Product)
	 * 
	 * @param ctx
	 * @param ctaPac
	 *            - id de la cuenta paciente: Para obtener solo las solicitudes
	 *            de la cuenta paciente
	 * @param paciente
	 *            - id del paciente: Para obtener solo las solicitudes del
	 *            paciente
	 * @param status
	 *            - estatus del documento MEXMEActPacienteIndH.DOCSTATUS_
	 * @param allStatus
	 *            - todos los estatus
	 * @return
	 */
	public static List<ActPacienteIndHView> getServicios(final Properties ctx,
			final long ctaPac, final long paciente, final String status,
			final boolean allStatus, final int parentID,
			final String whereClause, final Object... params) {
		final List<ActPacienteIndHView> resultados = new ArrayList<ActPacienteIndHView>();

		final StringBuilder sql = MEXMEActPacienteIndH.getSQLServicios(ctx,
				(int) ctaPac, (int) paciente, allStatus, parentID, whereClause,
				false);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			int j = 1;
			if (ctaPac > 0) {
				pstmt.setLong(j++, ctaPac);
			} else if (paciente > 0) {
				pstmt.setLong(j++, paciente);
			}
			if (!allStatus) {
				pstmt.setString(j++, status);
			}
			// pstmt.setString(j++, MEXMECtaPac.ENCOUNTERSTATUS_Admission);
			if (parentID > 0) {
				pstmt.setInt(j++, parentID);
				pstmt.setInt(j++, parentID);
			}
			for (int i = 0; i < params.length; i++) {
				DB.setParameter(pstmt, j++, params[i]);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final ActPacienteIndHView view = new ActPacienteIndHView();
				final MEXMEActPacienteIndH model = new MEXMEActPacienteIndH(
						ctx, rs, null);// Lama
				view.setValues(model);
				view.setValues(rs, 1);
				resultados.add(view);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			resultados.clear();
		} finally {
			DB.close(rs, pstmt);
		}

		return resultados;

	}

	/**
	 * Busca los servicios (M_Product)
	 * 
	 * @param ctx
	 * @param ctaPac
	 *            - id de la cuenta paciente: Para obtener solo las solicitudes
	 *            de la cuenta paciente
	 * @param paciente
	 *            - id del paciente: Para obtener solo las solicitudes del
	 *            paciente
	 * @param status
	 *            - estatus del documento MEXMEActPacienteIndH.DOCSTATUS_
	 * @return
	 */
	public static int getCountServices(final Properties ctx, final int ctaPac,
			final int paciente, final String status, final String whereClause,
			final Object... params) {
		int resultados = 0;

		final boolean allStatus = StringUtils.isBlank(status);
		final StringBuilder sql = MEXMEActPacienteIndH.getSQLServicios(ctx,
				ctaPac, paciente, allStatus, 0, whereClause, true);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			int j = 1;
			if (ctaPac > 0) {
				pstmt.setLong(j++, ctaPac);
			} else if (paciente > 0) {
				pstmt.setLong(j++, paciente);
			}
			if (!allStatus) {
				pstmt.setString(j++, status);
			}
			for (Object param : params) {
				DB.setParameter(pstmt, j++, param);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				resultados = rs.getInt("total");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return resultados;
	}

	/**
	 * Return the number of Activity for an Account with a specified Estatus
	 * from Extension No = 0
	 * 
	 * @param ctapacID
	 *            Account identification
	 * @return Number of diagnoses
	 */
	public static int getCount(final Properties ctx, final int ctaPacID,
			final int actPacIndID, final String docStatus,
			final String estatus, final String type, final String trxName) {
		int count = 0;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" select count(*) cuantos ")
				.append(" from exme_ctapac cta ")
				.append(" inner join exme_actpacienteindh H on H.exme_ctapac_id = cta.exme_ctapac_id ")
				.append(" inner join exme_actpacienteind I  on I.exme_actpacienteindH_id = H.exme_actpacienteindH_id ")
				.append(" inner join exme_ctapacdet d on d.exme_actpacienteind_id = I.exme_actpacienteind_id ")
				.append(" inner join exme_ctapacext e on e.exme_Ctapacext_id = d.exme_ctapacext_id and e.extensionno = 0 ")
				.append(" where cta.exme_ctapac_id = ? ");
		if (StringUtils.isNotEmpty(docStatus)) {
			sql.append(" and h.docstatus = ? ");
		}
		if (StringUtils.isNotEmpty(estatus)) {
			sql.append(" and i.estatus = ? ");
		}

		// if
		// (type.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_ProfessionalClaim)){
		// sql.append("AND EM.EXME_Modifiers_ID IN (NVL(PO.EXME_Modifier1_id,0), NVL(PO.EXME_Modifier2_id,0), NVL( PO.EXME_Modifier3_id,0), NVL( PO.EXME_Modifier4_id,0))");
		// }else
		// if(type.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_InstitutionalClaim)){
		// sql.append("AND EM.EXME_Modifiers_ID NOT IN (NVL(PO.EXME_Modifier1_id,0), NVL(PO.EXME_Modifier2_id,0), NVL( PO.EXME_Modifier3_id,0), NVL( PO.EXME_Modifier4_id,0))");
		// }
		sql.append(
				" and (SELECT FNC_isProfessional(PO.EXME_ProductoOrg_ID, CTA.AD_Org_ID) ")
				.append(" FROM EXME_ProductoOrg PO  ")
				.append(" WHERE PO.EXME_ProductoOrg_ID = fnc_getProductOrg(i.m_product_id, i.ad_org_id)) = ?  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 1;
			pstmt.setLong(i++, ctaPacID);
			if (StringUtils.isNotEmpty(docStatus)) {
				pstmt.setString(i++, docStatus);
			}
			if (StringUtils.isNotEmpty(estatus)) {
				pstmt.setString(i++, estatus);
			}

			pstmt.setString(
					i++,
					type.equals(MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? Constantes.REG_NOT_ACTIVE
							: Constantes.REG_ACTIVE);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("cuantos");
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCount", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return count;
	}

	/**
	 * Indica si la Indicaci�n puede cancelarse o no. Se puede cancelar solo si
	 * esta ya fue surtida pero no facturada.
	 * @deprecated
	 * @return
	 */
	public static boolean canDeliver(final Properties ctx,
			final int EXME_ActPacienteIndH_ID, final String trxName,
			final boolean isHl7ORM) {
		boolean canDeliver = false;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_ActPacienteIndH_ID FROM EXME_ActPacienteIndH ")
				.append("WHERE EXME_ActPacienteIndH_ID =  ? ")
//				.append(EXME_ActPacienteIndH_ID)
				.append(" AND DocStatus = 'CO' AND Processing = 'N' AND IsInvoiced = 'N'");

		if (!isHl7ORM) {
			sql.append(" AND IsDelivered = 'N' ");
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				"EXME_ActPacienteIndH"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE,
					trxName);
			pstmt.setInt(1, EXME_ActPacienteIndH_ID);
			rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				canDeliver = true;
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return canDeliver;
	}
	/**
	 * Indica si la Indicaci�n puede cancelarse o no. Se puede cancelar solo si
	 * esta ya fue surtida pero no facturada.
	 * @return
	 * FIXME @param isHl7ORM ???
	 */
	public boolean canDeliver(final boolean isHl7ORM) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_ActPacienteIndH_ID FROM EXME_ActPacienteIndH ");
		sql.append(" WHERE EXME_ActPacienteIndH_ID = ? ");
		sql.append(" AND DocStatus = 'CO' AND Processing = 'N' AND IsInvoiced = 'N'");
		if (!isHl7ORM) {
			sql.append(" AND IsDelivered = 'N' ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "EXME_ActPacienteIndH"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE, get_TrxName());
			pstmt.setInt(1, getEXME_ActPacienteIndH_ID());
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return false;
	}

	/**
	 * Obtiene la cantidad de servicios completos
	 * 
	 * @param exme_CitaMedica_ID
	 *            cita medica
	 */
	public static int getServicios(final int exme_CitaMedica_ID) {
		int count = 0;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append("Select count(*) count ")
				.append("FROM exme_actpacienteindh apindh ")
				.append("INNER JOIN exme_actpaciente ap on apindh.exme_actpaciente_id = ap.exme_actpaciente_id ")
				.append("WHERE ap.exme_citamedica_id = ? AND apindh.isdelivered = 'Y'");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			pstmt.setInt(index++, exme_CitaMedica_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sqle.getMessage(), sqle);
		} finally {
			DB.close(rs, pstmt);
		}
		return count;
	}

	
	
	
	/**
	 * Obtenemos las Indicaciones que se hicieron en una actividad paciente.
	 * 
	 * @return
	 */
	public MEXMEActPacienteInd[] getLineas() {
		return getLineas(false, false);
	}

	/**
	 * Obtenemos las Indicaciones que se hicieron en una actividad paciente.
	 * 
	 * @param requery
	 *            Re-ejecuta el query?
	 * @return
	 */
	public MEXMEActPacienteInd[] getLineas(final boolean requery) {
		return getLineas(requery, false);
	}

	/**
	 * listado de indicaciones medicas encabezado activas
	 * 
	 * @param fromReferral
	 * @return
	 */
	public MEXMEActPacienteInd[] getActPacienteInd(final boolean fromReferral) {
		final List<MEXMEActPacienteInd> lista = getLineas(" IsActive = 'Y' ", null, fromReferral);// JGaray 14309
		final MEXMEActPacienteInd[] actPacInds = new MEXMEActPacienteInd[lista.size()];
		lista.toArray(actPacInds);
		return actPacInds;
	}

	/**
	 * Obtenemos las Indicaciones que se hicieron en una actividad paciente.
	 * 
	 * @param whereClause
	 * @return
	 */
	public List<MEXMEActPacienteInd> getLineas(final String whereClause,
			final String orderBy, final boolean fromReferral) {

		final List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_ActPacienteInd WHERE EXME_ActPacienteIndH_ID = ? ");
		if (whereClause != null) {
			sql.append(" AND ").append(whereClause);// JGaray 14309
		}

		if (!fromReferral) {
			if (Env.getUserPatientID(getCtx()) < 0) {// Si es un usuario
														// paciente no agrega el
														// accessLevel
				sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEActPacienteInd.Table_Name));
			}
		}

		if (orderBy != null) {
			sql.append(orderBy);
		} else {
			sql.append(" ORDER BY Line");
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_ActPacienteIndH_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEActPacienteInd actPac = new MEXMEActPacienteInd(getCtx(), rs, get_TrxName());
				actPac.setActPacienteIndH(new MEXMEActPacienteIndH(getCtx(), get_ID(), get_TrxName()));// TODO
				list.add(actPac);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "getActPacienteInd", e);
		} finally {
			DB.close(rs, pstmt);
		}

		//
		return list;
	}

	/**
	 * Set Price List (and Currency) when valid
	 * 
	 * @param M_PriceList_ID
	 *            price list
	 */
	public void setM_PriceList_ID(final int M_PriceList_ID) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT M_PriceList_ID, C_Currency_ID ").append(" FROM M_PriceList WHERE M_PriceList_ID = ? AND IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "M_PriceList"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, M_PriceList_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				super.setM_PriceList_ID(rs.getInt(1));
				setC_Currency_ID(rs.getInt(2));
			} else {
				log.log(Level.SEVERE, sql+"#setM_PriceList_ID: NOT VALID M_PriceList_ID: "
						+ M_PriceList_ID);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
	} // setM_PriceList_ID

	/**
	 * Calculate Tax and Total
	 */
	public boolean calculateTaxTotal() {
		log.fine("");

		// Lines
		BigDecimal totalLines = Env.ZERO;
		BigDecimal grandTotal = totalLines;
		BigDecimal taxAmt = Env.ZERO;
		final MEXMEActPacienteInd[] lines = getLineas();
		for (int i = 0; i < lines.length; i++) {
			final MEXMEActPacienteInd line = lines[i];
			final MTax tax = new MTax(getCtx(), line.getC_Tax_ID(), get_TrxName());

			taxAmt = taxAmt.add(tax.calculateTax(line.getLineNetAmt(), isTaxIncluded(), 2));

			totalLines = totalLines.add(line.getLineNetAmt());

			grandTotal = grandTotal.add(totalLines);
		}

		grandTotal = grandTotal.add(taxAmt);

		// Taxes
		setTotalLines(totalLines);
		setGrandTotal(grandTotal);
		return true;
	} // calculateTaxTotal

	/**
	 * Obtenemos el paciente relacionado.
	 * 
	 * @return
	 */
	public MEXMEPaciente getPaciente() {
		if (paciente == null) {
			paciente = getActPaciente().getPaciente();
		}
		return paciente;
	}

//	/**
//	 * Genera los cargos para la extension dada a partir de las lineas de al
//	 * indicacion. SERVICIOS
//	 * 
//	 * @param ext
//	 * @param trxName
//	 * @return
//	 * @deprecated use {@link MEXMEActPacienteIndH#charges()}
//	 */
//	public String createCharges(MEXMECtaPacExt ext, boolean inOut,
//			String trxName) throws Exception {
//		String info = null;
//
//		final MEXMEActPacienteInd[] lineas = getLineas(true);
//
//		for (int i = 0; i < lineas.length; i++) {
//
//			final MEXMEActPacienteInd linea = lineas[i];
//			int EXME_ActPacienteInd_ID = linea.getEXME_ActPacienteInd_ID();
//			/*
//			 * Vaidamos que la linea haya sido surtida.
//			 */
//			if (linea.getQtyDelivered() != null
//					&& linea.getQtyDelivered().compareTo(Env.ZERO) > 0
//					&& linea.getPriceActual().compareTo(Env.ZERO) > 0
//					&& linea.getPriceList().compareTo(Env.ZERO) > 0) {
//
//				// Noelia se crea el inOutLine correspondiente al cargo
//				MEXMEInOutLine inOutLine = null;
//
//				MCtaPacDet charge = new MCtaPacDet(ext.getCtaPac(),
//						ext.getEXME_CtaPacExt_ID(), linea.getEXME_Area_ID(),
//						trxName);
//				charge.setLine();
//				charge.setM_Product_ID(linea.getM_Product_ID());
//				MProduct product = linea.getProduct();
//				charge.setC_UOM_ID(product.getC_UOM_ID());
//				charge.setDateDelivered(charge.getDateOrdered());
//				charge.setM_Warehouse_ID(getM_Warehouse_ID());// El almacen lo
//																// tomamos del
//																// contexto.
//				charge.setM_Warehouse_Sol_ID(getM_Warehouse_Sol_ID());
//				charge.setC_Currency_ID(linea.getC_Currency_ID());
//				charge.setCgoProcesado(true);
//
//				// Noelia: Se guarda la orgtrx del almacen que surte
//				charge.setAD_OrgTrx_ID(linea.getAD_OrgTrx_ID());
//
//				if (charge.getQtyOrdered().compareTo(Env.ZERO) <= 0)
//					charge.setQtyOrdered(linea.getQtyDelivered());
//				charge.setQtyDelivered(linea.getQtyDelivered());
//				charge.setQtyPaquete(Env.ZERO);
//				charge.setQtyPendiente(linea.getQtyDelivered());
//
//				// Noelia: Mantener la relacion entre la salida de inventario y
//				// el cargo
//				if (inOut) {
//					inOutLine = new MEXMEInOutLine(getCtx(), linea.getM_InOutLine_ID(), trxName);
//					charge.setM_InOutLine_ID(inOutLine.getM_InOutLine_ID());
//				}
//
//				// Se mantiene el precio
//				int scale = MCurrency.getStdPrecision(charge.getCtx(), Env.getC_Currency_ID(charge.getCtx())); // decimas.
//
//				charge.setEXME_EsqDesLine_ID(linea.getEXME_EsqDesLine_ID());
//				charge.setCosto(linea.getCosto().setScale(scale, BigDecimal.ROUND_HALF_UP));
//				charge.setUsarFactor(false);
//				charge.setPriceList(linea.getPriceList().setScale(scale, BigDecimal.ROUND_HALF_UP));
//				charge.setPriceListInv(linea.getPriceList().setScale(scale, BigDecimal.ROUND_HALF_UP));
//				charge.setPriceLimit(linea.getPriceLimit().setScale(scale, BigDecimal.ROUND_HALF_UP));
//				charge.setPriceLimitInv(linea.getPriceLimit().setScale(scale, BigDecimal.ROUND_HALF_UP));
//				charge.setPriceActual(linea.getPriceActual().setScale(scale, BigDecimal.ROUND_HALF_UP));
//				charge.setPriceActualInv(linea.getPriceActual().setScale(scale, BigDecimal.ROUND_HALF_UP));
//				charge.setDiscount(linea.getDiscount());// Se cambia a monto por
//														// porcentaje
//														// Expert:aaranda
//														// 15092010
//				charge.setLineNetAmt();
//				charge.setProductValue(linea.getProduct().getValue());
//				charge.setProductDescription(linea.getProduct().getDescription());
//				charge.setChargeAmt(linea.getPriceList().setScale(scale, BigDecimal.ROUND_HALF_UP));
//				charge.setC_Tax_ID(linea.getC_Tax_ID());
//
//				if (EXME_ActPacienteInd_ID > 0)
//					charge.setEXME_ActPacienteInd_ID(EXME_ActPacienteInd_ID);
//				if (!charge.save(trxName)) {
//					info = "No fue posible generar el cargo " + getDocumentNo()
//							+ " - " + linea.getDescripcion()
//							+ " a la CtaPac - "
//							+ ext.getCtaPac().getDocumentNo();
//				}
//
//				// Noelia: Mantener la relacion entre la salida de inventario y
//				// el cargo
//				if (inOut) {
//
//					if (charge != null && charge.getEXME_CtaPacDet_ID() > 0){
//						inOutLine.setEXME_CtaPacDet_ID(charge.getEXME_CtaPacDet_ID());
//					}
//					if (!inOutLine.save(trxName)) {
//						info = "No fue posible actualiza la salida de inventario "
//								+ inOutLine.getM_InOutLine_ID();
//						return info;
//					}
//				}
//
//			}// qtyDelivered > 0
//
//		}// lineas.
//
//		return info;
//	}

	/**
	 * Reserve Inventory. Counterpart: MInOut.completeIt()
	 * 
	 * @param dt
	 *            document type or null
	 * @param lines
	 *            order lines (ordered by M_Product_ID for deadlock prevention)
	 * @return true if (un) reserved
	 */
	private boolean reserveStock(MDocType dt, final MEXMEActPacienteInd[] lines) {
		if (dt == null) {
			dt = MDocType.get(getCtx(), getC_DocType_ID());
		}
		// Binding
		boolean binding = !dt.isProposal();
		// Not binding - i.e. Target=0
		if (DOCACTION_Void.equals(getDocAction())
				// Closing Binding Quotation
				|| (MDocType.DOCSUBTYPESO_Quotation
						.equals(dt.getDocSubTypeSO()) && DOCACTION_Close
						.equals(getDocAction())))
			binding = false;

		boolean isSOTrx = true;

		log.fine("Binding=" + binding + " - IsSOTrx=" + isSOTrx);

		// Force same WH for all but SO/PO
		int header_M_Warehouse_ID = getM_Warehouse_ID();
		if (MDocType.DOCSUBTYPESO_StandardOrder.equals(dt.getDocSubTypeSO())
				|| MDocType.DOCBASETYPE_PurchaseOrder.equals(dt
						.getDocBaseType()))
			header_M_Warehouse_ID = 0; // don't enforce

		// Always check and (un) Reserve Inventory
		for (int i = 0; i < lines.length; i++) {
			final MEXMEActPacienteInd line = lines[i];
			// Check/set WH/Org
			if (header_M_Warehouse_ID != 0) {
				if (header_M_Warehouse_ID != line.getM_Warehouse_ID())
					line.setM_Warehouse_ID(header_M_Warehouse_ID);
				if (getAD_Org_ID() != line.getAD_Org_ID())
					line.setAD_Org_ID(getAD_Org_ID());
			}
			// Binding
			final BigDecimal target = binding ? line.getQtyOrdered() : Env.ZERO;
			final BigDecimal difference = target
					.subtract(line.getQtyReserved()).subtract(
							line.getQtyDelivered());
			if (difference.compareTo(Env.ZERO) == 0)
				continue;

			log.fine("Line=" + line.getLine() + " - Target=" + target
					+ ",Difference=" + difference + " - Ordered="
					+ line.getQtyOrdered() + ",Reserved="
					+ line.getQtyReserved() + ",Delivered="
					+ line.getQtyDelivered());

			// Check Product - Stocked and Item
			final MProduct product = line.getProduct();
			if (product != null && product.isStocked()) {
				// Get existing Location
				int M_Locator_ID = MStorage.getMLocatorID(
						line.getM_Warehouse_ID(), line.getM_Product_ID(), 0,
						line.getQtyOrdered(), get_TrxName());
				// Get default Location
				if (M_Locator_ID == 0) {
					final MWarehouse wh = MWarehouse.get(getCtx(),
							line.getM_Warehouse_ID());
					M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
				}
				final BigDecimal reserved = isSOTrx ? difference : Env.ZERO;
				final BigDecimal ordered = isSOTrx ? Env.ZERO : difference;
				//
				if (!MStorage.add(getCtx(), line.getM_Warehouse_ID(),
						M_Locator_ID, line.getM_Product_ID(), 0, 0, Env.ZERO,
						reserved, ordered, get_TrxName())) {
					return false;
				}
				// update line
				line.setQtyReserved(line.getQtyReserved().add(difference));
				if (!line.save(get_TrxName()))
					return false;
			}
		} // reverse inventory
		return true;
	} // reserveStock

	/**
	 * Create Shipment/Invoice Reversals
	 * 
	 * @param true if success
	 */
	private boolean createReversals() {
		log.info("createReversals");
		final StringBuffer info = new StringBuffer();

		// Creamos los contra cargos (-) si hubo CtaPac.
		if (getEXME_CtaPac_ID() != 0) {

			info.append("... @EXME_CtaPacDet_ID@:");

			try {
				// Buscamos los cargos que coincidan en Producto, UOM y
				// Cantidad.
				String sql = "SELECT * FROM EXME_CtaPacDet WHERE EXME_CtaPac_ID = "
						+ getEXME_CtaPac_ID()
						+ " AND M_Product_ID = ?  AND C_UOM_ID = ? AND QtyDelivered =  ?";

				final MEXMEActPacienteInd[] lineas = getLineas(false);

				// Cargos coincidentes.
				final Hashtable<MEXMEActPacienteInd, MCtaPacDet> cargos = new Hashtable<MEXMEActPacienteInd, MCtaPacDet>(
						lineas.length);

				for (int i = 0; i < lineas.length; i++) {

					final MEXMEActPacienteInd linea = lineas[i];
					PreparedStatement pstmt = null;

					if (linea.getQtyDelivered().compareTo(Env.ZERO) != 0) {
						pstmt = DB.prepareStatement(sql, get_TrxName());
						pstmt.setInt(1, linea.getM_Product_ID());
						pstmt.setInt(2, linea.getC_UOM_ID());
						pstmt.setBigDecimal(3, linea.getQtyDelivered());

						ResultSet rs = pstmt.executeQuery();

						if (rs.next()) {
							final MCtaPacDet cargo = new MCtaPacDet(getCtx(), rs, get_TrxName());
							if (cargo.getEXME_ActPacienteInd_ID() == linea.getEXME_ActPacienteInd_ID()) {
								cargos.put(linea, cargo);
							}
						} else {
							// No existe un solo cargo que coinsida con la
							// linea.
							final MProduct product = new MProduct(getCtx(),
									linea.getM_Product_ID(), null);
							final MUOM uom = new MUOM(getCtx(),
									linea.getC_UOM_ID(), null);
							m_processMsg += " No existe un solo cargo que coinsida con la linea ["
									+ (product != null ? (" Producto-" + product
											.getName()) : "")
									+ ", "
									+ (uom != null ? ("UOM-" + uom.getName())
											: "")
									+ ", QtyDelivered-"
									+ linea.getQtyDelivered() + ". ";
							return false;
						}
					}
				}// lineas

				if (cargos.size() != lineas.length) {
					m_processMsg += " No fue posible dar reversa a los cargos. "
							+ cargos.size()
							+ " cargos != "
							+ lineas.length
							+ " por cancelar. ";
					return false;
				}

				// Creamos contracargos.
				final Enumeration<MCtaPacDet> enums = cargos.elements();
				while (enums.hasMoreElements()) {

					final MCtaPacDet cargo = (MCtaPacDet) enums.nextElement();

					final MCtaPacDet contraCargo = MCtaPacDet.copyFrom(cargo,
							cargo.getEXME_CtaPacExt_ID(), cargo.get_TrxName());

					contraCargo.setQtyDelivered(contraCargo.getQtyDelivered().negate());
					contraCargo.setQtyInvoiced(contraCargo.getQtyInvoiced().negate());
					contraCargo.setQtyOrdered(contraCargo.getQtyOrdered().negate());

					contraCargo.setDescription("Contracargo ("
							+ cargo.getLine() + " - "
							+ cargo.getCtaPac().getDocumentNo() + ") : "
							+ cargo.getDescription());
					contraCargo.setLineNetAmt();
					if (!contraCargo.save(get_TrxName())) {
						m_processMsg += " No fue posible dar reversa al cargo. [CtaPacDet_ID - "
								+ cargo.getEXME_CtaPacDet_ID() + "]. ";
						return false;
					}
					info.append(" ");
					info.append("Cargo - " + cargo.getEXME_CtaPacDet_ID()
							+ ", ContraCargo - "
							+ contraCargo.getEXME_CtaPacDet_ID());
				}// enum

			} catch (SQLException sqle) {
				m_processMsg += "No fue posible dar reversa a los cargos. ";
				sqle.printStackTrace();
				return false;
			}
		}

		// Reverse All *Shipments*
		info.append("@M_InOut_ID@:");
		final MInOut[] shipments = getShipments();
		for (int i = 0; i < shipments.length; i++) {
			final MInOut ship = shipments[i];

			// if closed - ignore
			if (MInOut.DOCSTATUS_Closed.equals(ship.getDocStatus())
					|| MInOut.DOCSTATUS_Reversed.equals(ship.getDocStatus())
					|| MInOut.DOCSTATUS_Voided.equals(ship.getDocStatus()))
				continue;
			ship.set_TrxName(get_TrxName());

			// If not completed - void - otherwise reverse it
			if (!MInOut.DOCSTATUS_Completed.equals(ship.getDocStatus())) {
				if (ship.voidIt())
					ship.setDocStatus(MInOut.DOCSTATUS_Voided);
			} else if (ship.reverseCorrectIt()) { // completed shipment
				ship.setDocStatus(MInOut.DOCSTATUS_Reversed);
				info.append(" ").append(ship.getDocumentNo());
			} else {
				m_processMsg = "Could not reverse Shipment " + ship;
				return false;
			}
			ship.setDocAction(MInOut.DOCACTION_None);
			ship.save(get_TrxName());
		} // for all shipments

		m_processMsg = info.toString();
		return true;
	} // createReversals

	/**
	 * Get Shipments of Order
	 * 
	 * @return shipments
	 */
	public MInOut[] getShipments() {
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		final ArrayList<MInOut> list = new ArrayList<MInOut>();
		sql.append(" SELECT M_InOut.* FROM M_InOut ")
				.append(" INNER JOIN M_InOutLine iol ON (iol.m_inout_id = M_InOut.m_inout_id AND iol.IsActive = 'Y' ) ")
				.append(" INNER JOIN EXME_ActPacienteInd i ON (i.m_inoutline_id = iol.m_inoutline_id AND i.exme_actpacienteindh_id = ? AND i.IsActive = 'Y' ) ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "M_InOut"));

		sql.append(" ORDER BY M_InOut.Created DESC ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_ActPacienteIndH_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MInOut(getCtx(), rs, get_TrxName()));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getShipments", e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		final MInOut[] retValue = new MInOut[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getShipments

	/**
	 * getWarehouse
	 * 
	 * @return
	 */
	public MWarehouse getWarehouse() {
		if (warehouse == null) {
			warehouse = new MWarehouse(getCtx(), getM_Warehouse_ID(),
					get_TrxName());
		}
		return warehouse;
	}

	/**
	 * getWarehouseSol
	 * 
	 * @return
	 */
	public MWarehouse getWarehouseSol() {
		return new MWarehouse(getCtx(), getM_Warehouse_Sol_ID(), get_TrxName());
	}

	/**
	 * getM_processMsg
	 * 
	 * @return
	 */
	public String getM_processMsg() {
		return m_processMsg;
	}

	/**
	 * Get Lines of Order
	 * 
	 * @param requery
	 *            requery
	 * @param orderBy
	 *            optional order by column
	 * @return lines
	 */
	public MEXMEActPacienteInd[] getLines(final boolean requery,
			final String orderBy) {
		if (m_lines != null && !requery)
			return m_lines;
		//
		String orderClause = "ORDER BY ";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += "Line";
		m_lines = getLines(" AND CgoProcesado = 'N' ", orderClause);
		return m_lines;
	} // getLines

	/**
	 * Get Lines of Order
	 * 
	 * @param whereClause
	 *            where clause or null (starting with AND)
	 * @param orderClause
	 *            order clause
	 * @return lines
	 */
	public MEXMEActPacienteInd[] getLines(final String whereClause, final String orderClause) {
		final ArrayList<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_ActPacienteInd WHERE EXME_ActPacienteIndH_ID=? AND IsActive = 'Y' ");

		if (whereClause != null) {
			sql.append(whereClause);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "EXME_ActPacienteInd"));

		if (orderClause != null) {
			sql.append(" ").append(orderClause);
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_ActPacienteIndH_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEActPacienteInd ol = new MEXMEActPacienteInd(getCtx(), rs, get_TrxName());
				// ol.setHeaderInfo (this);
				list.add(ol);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		final MEXMEActPacienteInd[] lines = new MEXMEActPacienteInd[list.size()];
		list.toArray(lines);
		return lines;
	} // getLines

	/**
	 * Cuenta paciente
	 * 
	 * @return
	 */
	public MEXMECtaPac getCtaPac() {
		if (ctaPac == null && getEXME_CtaPac_ID() > 0) {
			ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctaPac;
	}

	public void setCtaPac(final MEXMECtaPac ctaPac) {
		this.ctaPac = ctaPac;
	}

	/**
	 * Void Document. PENSADO SOLO PARA LA CENCELACION DE SERVICIOS Set Qtys to
	 * 0 - Sales: reverse all documents
	 * 
	 * @return true if success
	 */
	public boolean voidIt() {
		final MEXMEActPacienteInd[] lines = getLineas(true);
		log.info(toString());
		setDocStatus(DOCSTATUS_Drafted);
		for (MEXMEActPacienteInd line : lines) {
			if (X_EXME_ActPacienteInd.ESTATUS_CancelledService.equals(line.getEstatus())) {
				//Lama .- Card #931 > No se puede cancelar una orden procesada si es hl7
				if(getWarehouse().isHL7ORM()){
					setDocStatus(DOCSTATUS_InProgress);
					break;
				} else {
					continue;// no hace nada si ya esta cancelado
				}
			} else if (X_EXME_ActPacienteInd.ESTATUS_CompletedService.equals(line.getEstatus())) {
				setDocStatus(DOCSTATUS_InProgress);
				break;
			}
			// Cancelar el detalle (voidIt) RQM #4168
			final StringBuilder desc = new StringBuilder();
			desc.append(line.getDescripcion() == null ? getDocumentNo() : line.getDescripcion());
			desc.append(" : Voided (").append(line.getQtyDelivered()).append(")");
			line.setDescription(desc.toString());
			if (get_TrxName() != line.get_TrxName()) {
				line.set_TrxName(get_TrxName());
			}
			if (!line.voidIt()) {
				return false;
			}
		}
		if (DOCSTATUS_Completed.equals(getDocStatus()) || DOCSTATUS_InProgress.equals(getDocStatus())) {
			m_processMsg = Utilerias.getMsg(getCtx(), "error.solicitudServ.cantDelete");//RQM #4112
			log.saveError("Error", m_processMsg);
			return false;
		}
		// Los servicios no tienen stock
		if (!isService() &&
		// Clear Reservations
				!reserveStock(null, lines)) {
			m_processMsg = "No se reservo al inventario (VoidIt)";
			return false;
		}
		if (!DOCSTATUS_Drafted.equals(getDocStatus()) //
				&& !DOCSTATUS_Approved.equals(getDocStatus()) //
				&& !createReversals()) {
			return false;
		}
		// Se cancelan los equipos relacionados con la orden
		if (!MEXMEEquipoH.voidAll(this)) {
			log.severe(Utilerias.getMessageArgs(getCtx(), null, "cancelaServ.error.cancelar.voidIt", getM_processMsg()));
			return false;
		}

		setDocStatus(DOCSTATUS_Voided);
		setProcessed(true);
		setDocAction(DOCACTION_None);
		setCanceledBy(Env.getAD_User_ID(Env.getCtx()));
		setDateCanceled(Env.getCurrentDate());
		return true;
	} // voidIt

	/**
	 * cancelDiag
	 */
	public void cancelDiag() {
		// busca los actpacind
		for (MEXMEActPacienteInd ind : getLineas(true)) {
			// busca los diag asociados a ese registro y los cancela (isActive =
			// false)
			final List<MActPacienteDiag> lst = MActPacienteDiag.get(getCtx(),
					ind.get_ID(), ind.get_Table_ID(), getEXME_ActPaciente_ID(),
					get_TrxName());
			for (MActPacienteDiag diag : lst) {
				diag.setIsActive(false);
				if (!diag.save(get_TrxName())) {
					s_log.log(Level.SEVERE, "MActPacienteDiag#IsActive:false");
				}
			}
		}
	}

	/**
	 * Lineas de la actividad paciente creadas por primera vez, (aun no estan en
	 * la base de datos)
	 */
	public List<MEXMEActPacienteInd> getLstActPacInd() {
		return this.lstActPacInd;
	}

	/**
	 * Lineas de la actividad paciente creadas por primera vez, (aun no estan en
	 * la base de datos)
	 */
	public void setLstActPacInd(final List<MEXMEActPacienteInd> lstActPacInd) {
		this.lstActPacInd = lstActPacInd;
	}

	/**
	 * Define si las lineas ya fueron surtidas o aplicadas y el estatus debe
	 * cambiar
	 * 
	 * @param completo
	 */
	public void setCompleto(final boolean completo) {
		this.completo = completo;
	}

	/**
	 * Define si las lineas ya fueron surtidas o aplicadas y el estatus debe
	 * cambiar
	 * 
	 * @param completo
	 */
	public boolean isCompleto() {
		return completo;
	}

	/**
	 * status Complete
	 */
	public void statusComplete() {
		setIsDelivered(true);
		setProcessed(false);
		setDocStatus(X_EXME_ActPacienteIndH.DOCSTATUS_Completed);
		setDocAction(X_EXME_ActPacienteIndH.DOCACTION_Close);
		setEstatus(MEXMEActPacienteInd.ESTATUS_CompletedService);
	}

	/**
	 * status Draft
	 */
	public void statusDraft() {
		setIsDelivered(false);
		setProcessed(true);
		setDocStatus(X_EXME_ActPacienteIndH.DOCSTATUS_Drafted);
		setDocAction(X_EXME_ActPacienteIndH.DOCACTION_Complete);
		setEstatus(MEXMEActPacienteInd.ESTATUS_RequestedService);
	}

	
	/**
	 * Estatus de completo sin transaccion
	 * @return
	 */
	public String approve() {
		if (isActive()) {
			if (DOCSTATUS_Drafted.equals(getDocStatus()) || DOCSTATUS_NotApproved.equals(getDocStatus())) {
				setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Approved);
			}// no cambiar si ya esta completado / cancelado / en progreso
			if (getDateApproved() == null && Env.getEXME_Medico_ID(getCtx()) > 0) {
				setDateApproved(Env.getCurrentDate());// no sobre escribir la fecha
			}
			// autenticacion solo para medicos
			if (getAuthenticatedBy() <= 0 && Env.getEXME_Medico_ID(getCtx()) > 0) {
				setAuthenticatedBy(Env.getAD_User_ID(getCtx()));
			}
			if (!save()) {
				return Utilerias.getMsg(getCtx(), "error.oc.notaproved");
			}
		} else if(Env.getEXME_Medico_ID(getCtx()) > 0){
			// Card #746 - Authentication of Discontinuation Orders (physicians only)
			MEXMEDiscontinueOrder.authenticate(this, get_TrxName());
		}
		return null;
	}


	/**
	 * Obtenemos las Indicaciones que se hicieron en una actividad paciente.
	 * 
	 * @param requery
	 *            Re-ejecuta el query?
	 * @param fromReferral
	 *            cuando proviene de referencia medica
	 * @return
	 */
	public MEXMEActPacienteInd[] getLineas(final boolean requery,
			final boolean fromReferral) {
		if (lineas == null || lineas.length == 0 || requery) {
			lineas = getActPacienteInd(fromReferral);
		}
		return lineas;
	}

	
	/**
	 * setData
	 * @deprecated estandarizar en metodo {@link MEXMEActPacienteIndH#completeIt()}
	 */
	public void setData() {
		setProcessed(true);
		setDocStatus(DocAction.STATUS_Completed);
		setStatus(MEXMEActPacienteIndH.STATUS_FinalResult);
		setDocAction(DocAction.ACTION_None);
		setDateDelivered(Env.getCurrentDate());
		setIsDelivered(true);
		setEstatus(MEXMEActPacienteIndH.ESTATUS_ServiceOrdersCompleted);
	}

	/**
	 * statusEquipment
	 * 
	 * @return
	 * @deprecated {@link MEXMEEquipoH#completeAll(MEXMEActPacienteIndH)}
	 */
	public ModelError statusEquipment() {
		final List<MEXMEEquipoH> equiposH = MEXMEEquipoH.getEquiposH(getCtx(), getEXME_ActPacienteIndH_ID(), null);
		if (!equiposH.isEmpty()) {
			final Iterator<MEXMEEquipoH> jEq = equiposH.iterator();
			while (jEq.hasNext()) {
				final MEXMEEquipoH lista = jEq.next();
				if (lista.getEXME_EquipoH_ID() > 0) {
					final MEXMEEquipoH eqpH = new MEXMEEquipoH(getCtx(), lista.getEXME_EquipoH_ID(), get_TrxName());
					eqpH.setEstatus_Equipo(MEXMEEquipoH.ESTATUS_EQUIPO_Used);
					if (!eqpH.save(get_TrxName())) {
						return new ModelError(ModelError.TIPOERROR_Error, "error.servicios.surtir");
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean processIt(final String action) throws Exception {
		final DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		return true;
	}

	@Override
	public boolean invalidateIt() {
		return true;
	}

	@Override
	public String prepareIt() {
		return DocAction.STATUS_InProgress;
	}

	@Override
	public boolean approveIt() {
		setIsApproved(true);//solo confirmacion de orden (tecnico)
		return true;
	}

	@Override
	public boolean rejectIt() {
		return true;
	}

	@Override
	public String completeIt() {

		if (MEXMEActPacienteIndH.DOCSTATUS_Voided.equals(getDocStatus())) {
			setDocAction(ACTION_None);
			return DocAction.STATUS_Voided;// TODO ??
		}

		// Prepare
		if (DocAction.STATUS_Drafted.equals(getDocStatus())) {
			final String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		// can deliver
		if(!canDeliver(false)){
			return STATUS_Invalid;
		}

		String str = getDetailStatus();
		// tiene que tener detalle
		if (StringUtils.isBlank(str)) {
			setDocAction(ACTION_Void);
			return DocAction.STATUS_Invalid;
		}
		final String status;
		// si hay un pendiente ( S / P ) no se puede completar
		if (str.contains(MEXMEActPacienteInd.ESTATUS_RequestedService)
				|| str.contains(MEXMEActPacienteInd.ESTATUS_ScheduleService)) {
			setDocAction(ACTION_Complete);
			setIsDelivered(false);
			setDocStatus(DocAction.STATUS_InProgress);
			if (save()) {
				status = getDocStatus();
			} else {
				status = DocAction.STATUS_Invalid;
			}
		}
		// si tiene completado ( C )
		else if (str.contains(MEXMEActPacienteInd.ESTATUS_CompletedService)) {
			setProcessed(true);
			setDocAction(ACTION_None);
			setIsDelivered(true);
			if (getDateDelivered() == null) {
				setDateDelivered(Env.getCurrentDate());
			}
			setDocStatus(DocAction.STATUS_Completed);
			if (save()) {
				status = getDocStatus();
			} else {
				status = DocAction.STATUS_Invalid;
			}
			if(DocAction.STATUS_Completed.equals(status)) {
				// Se completan los equipos relacionados con la orden
				if(!MEXMEEquipoH.completeAll(this)){
					s_log.severe("MEXMEEquipoH.completeAll >> process not completed");
				}
			}
		}
		// si solo esta cancelado ( A )
		else if (str.contains(MEXMEActPacienteInd.ESTATUS_CancelledService)) {
			voidIt();
			status = getDocStatus();
		} else {
			status = DocAction.STATUS_Invalid;
		}
		return status;
	}
	
	public String getDetailStatus() {
		return getDetailStatus(getEXME_ActPacienteIndH_ID(), get_TrxName());
	}
	
	public static String getDetailStatus(int exmeActPacienteIndHId, String trxName) {
		// validar si todo el detalle esta cancelado ?
		final StringBuilder sql = new StringBuilder();
		// SELECT ARRAY_AGG(distinct Estatus) FROM EXME_ActPacienteInd ;
		sql.append(" SELECT ");
		if (DB.isOracle()) { // Note: LISTAGG para Oracle11 !!
			sql.append(" wm_concat(distinct Estatus) ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" array_to_string(ARRAY_AGG(distinct Estatus), ',') ");
		} else {
			sql.append(" null ");
		}
		sql.append(" FROM EXME_ActPacienteInd ");
		sql.append(" WHERE EXME_ActPacienteIndH_ID=? ");

		return DB.getSQLValueString(trxName, sql.toString(), exmeActPacienteIndHId);
	}

	public void afterCompleteIt() {
		Trx trx = null;
		try{
			// Para generar cargos para solicitudes completadas parcialmente se agrega el InProgress. GValdez
			if (DocAction.STATUS_Completed.equals(getDocStatus()) || DocAction.STATUS_InProgress.equals(getDocStatus())) {
				if (get_TrxName() == null) {
					trx = Trx.get(Trx.createTrxName("ICSv"), true);
					set_TrxName(trx.getTrxName());
					// regenerar las lineas
					lineas = null;
				} else {
					//No hnay que hacer nada ya la trae de ServiceDelivered
				}
//				try { // El Embarque se hace a partir del cargo
//					if (inventoryServices()) {
//						Trx.commit(trx);
//					} else {
//						Trx.rollback(trx);
//						s_log.severe("afterCompleteIt() >> Inventory process not completed");
//					}
//				} catch (Exception e) {
//					Trx.rollback(trx);
//					s_log.log(Level.SEVERE, e.getMessage(), e);
//				}
				
				setMovementDate(Env.getCurrentDate());
				setComplete(true);
				
				// Cargos a cuenta paciente
				if(MCountry.isMexico(getCtx())){
					if (!this.getCtaPac().getTipoArea().equals(MEXMECtaPac.TIPOAREA_Ambulatory)) {
						if(charges()){
							Trx.commit(trx);
						}else{
							Trx.rollback(trx);
							s_log.severe("afterCompleteIt() >> Charges process not completed");
						}							
					} else {
						if(!this.isInvoiced() && charges()){
							Trx.commit(trx);
						}else{
							Trx.rollback(trx);
							s_log.severe("afterCompleteIt() >> Charges process not completed : TIPOAREA = Ambulatory");
						}
					}
				}else{
					if (charges()) {
						Trx.commit(trx);
					} else {
						Trx.rollback(trx);
						s_log.severe("afterCompleteIt() >> Charges process not completed");
					}	
				}
			}
		} catch (Exception e) {
			Trx.rollback(trx, true);
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			Trx.rollback(trx, true);
			if(trx != null){
				set_TrxName(null);
			}
		}
	}
	
	/**
	 * Cargos para servicios
	 * Para hospitalizacion y cita
	 * 
	 * @return False: No se genero el cargo, True: El cargos se genero
	 */
	public boolean charges() {
		boolean retValue = true;
		// Hacemos el cargo a la cuenta paciente (si existe).
		final MEXMEConfigOV confOV = MEXMEConfigOV.findActPac(getCtx(), getEXME_ActPaciente_ID(), null);
		// Charges
		if (getEXME_CtaPac_ID() > 0 && getCtaPac() != null
		// si es de cita q este configurado que genera cargos
			&& (getCtaPac().getCita() == null || confOV.isGenCharges())
		) {
			if (X_EXME_CtaPac.ENCOUNTERSTATUS_Admission.equals(getCtaPac().getEncounterStatus()) || //
				X_EXME_CtaPac.ENCOUNTERSTATUS_Predischarge.equals(getCtaPac().getEncounterStatus()) || //
				X_EXME_CtaPac.ENCOUNTERSTATUS_Discharge.equals(getCtaPac().getEncounterStatus())//
			) {
				try {
					final CreateCharge createCharge = new CreateCharge(getCtx(), this);
					final String info = createCharge.insertActPacIndHCharges(get_TrxName());//Lama: cargos 2014
					if (info == null) {
						log.severe(Utilerias.getMsg(getCtx(), "error.servicios.cargar"));
						retValue = false;
					}
				} catch (Exception pre_e) {
					log.log(Level.SEVERE, Utilerias.getAppMsg(getCtx(), "error.servicios.noPrice"), pre_e);
					retValue = false;
				}
			} else {
				// No se creo el cargo se guarda en el log
				final MEXMEActPacienteIndCgo logCgo = new MEXMEActPacienteIndCgo(getCtx(), 0, get_TrxName());
				logCgo.setEXME_ActPacienteIndH_ID(getEXME_ActPacienteIndH_ID());
				logCgo.setErrorMsg("Status encounter" + getCtaPac().getEncounterStatus());
				logCgo.setType(MEXMEActPacienteIndCgo.BUSSINESSRULE);
				logCgo.setClassname(getClass().getName());
				logCgo.save();
			}
		}

		return retValue;
	}
//
//	/**
//	 * Inventario para servicios
//	 * @deprecated
//	 * @return
//	 */
//	public boolean inventoryServices() {
//		boolean retValue = false;
//		setMovementDate(Env.getCurrentDate());
//		setComplete(true);
//		final MInOut inOut = Inventory.createFromApServ(this
//			, getCompletedLines()
//			, false
//			, null
//			, MEXMEEstServ.getFromCtx(getCtx()));
//
//		if (inOut == null) {
//			log.severe(Utilerias.getMessage(getCtx(), null, "surtPedidoAction.error.surtir", MEXMEInOut.getS_ProcessMsg()));
//			
//		} else if (DocAction.STATUS_InProgress.equals(inOut.prepareIt())) {
//			final String status = inOut.completeIt();
//			inOut.setDocStatus(status);
//			if (DocAction.STATUS_Completed.equals(status) && !inOut.save()) {
//				log.severe(Utilerias.getMessage(getCtx(), null, "surtPedidoAction.error.surtir", inOut.getM_processMsg()));
//	} else {
//				retValue = true;
//			}
//		} else {
//			log.severe(Utilerias.getMessage(getCtx(), null, "surtPedidoAction.error.surtir", inOut.getM_processMsg()));
//		}
//		return retValue;
//	}

	/**
	 * Mapa con EXME_ActPacienteInd_ID, QtyDelivered
	 * 
	 * @return
	 */
	private HashMap<Integer, BigDecimal> getCompletedLines() {
		final HashMap<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_ActPacienteInd_ID, QtyDelivered ");
		sql.append(" FROM EXME_ActPacienteInd ");
		sql.append(" WHERE EXME_ActPacienteInd.IsActive = 'Y' ");
		sql.append(" AND EXME_ActPacienteInd.EXME_ActPacienteIndH_ID = ? ");
		sql.append(" AND EXME_ActPacienteInd.Estatus = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, get_ID());
			pstmt.setString(2, MEXMEActPacienteInd.ESTATUS_CompletedService);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getInt(1), rs.getBigDecimal(2));
			}
		} catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sql.toString(), sqle);
			map.clear();
		} finally {
			DB.close(rs, pstmt);
		}
		return map;
	}

	@Override
	public boolean closeIt() {
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		return true;
	}

	@Override
	public boolean reverseAccrualIt() {
		return true;
	}

	@Override
	public boolean reActivateIt() {
		return true;
	}

	@Override
	public String getSummary() {
		return null;
	}

	@Override
	public String getDocumentInfo() {
		return null;
	}

	@Override
	public File createPDF() {
		return null;
	}

	@Override
	public String getProcessMsg() {
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		return null;
	}

	/**
	 * Returns all diets included in the prescription
	 * 
	 * @param includeMain
	 *            - Incluides the main diet (sequence=0)
	 * @param asHTML
	 *            returns the string with HTML tags
	 * @return
	 */
	public String getDetailStr(boolean asHTML) {
		final StringBuilder str = new StringBuilder();
		final List<MEXMEActPacienteInd> detail = getLineas(null, null, false);
		final String newLine = asHTML ? "<br>" : Constantes.NEWLINE;
		final String italic1 = asHTML ? "<i>" : "";
		final String italic2 = asHTML ? "</i>" : "";
		// Card #1173 - Lama
		final String cargoMsg = getWarehouse().isHL7ORM() ? Utilerias.getMsg(getCtx(), "ingresoPaciente.msg.cargoEmpresa") : "";
		
		if (!detail.isEmpty()) {
			for (MEXMEActPacienteInd mDetail : detail) {
				if (mDetail.getM_Product_ID() <= 0) {
					continue;
				}
				if(str.length()>0) {
					str.append(newLine);
				}
				str.append("* ");
				str.append(mDetail.getProduct().getName());
				if(mDetail.isSurtir()){
					str.append(italic1).append(" (");
					str.append(MRefList.getListName(getCtx(), MEXMEActPacienteInd.ESTATUS_AD_Reference_ID, mDetail.getEstatus()));
					str.append(")").append(italic2);
					// Card #1173 Mostrar si tiene cargo la fecha del mismo - Lama
					if (StringUtils.isNotEmpty(cargoMsg) && mDetail.getCargo() != null) {
						str.append(" ").append(italic1);
						str.append(cargoMsg).append(": ");
						str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(mDetail.getCargo().getCreated()));
						str.append(italic2);
					}
				} else {
					str.append(italic1).append(" (").append(Utilerias.getMsg(getCtx(), "msj.external"));
					str.append(")").append(italic2);
				}
			}
		}
		return str.toString();
	}	

	/**
	 * Set Price List (and Currency) when valid
	 * 
	 * @param M_PriceList_ID
	 *            price list
	 */
	public int getMPriceListID(final int M_PriceList_ID) {
		int idPL = M_PriceList_ID;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT M_PriceList_ID ").append(" FROM M_PriceList WHERE IsActive = 'Y' AND isDefault = 'Y' ")
			.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", "M_PriceList"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idPL = rs.getInt(1);
			} else {
				log.log(Level.SEVERE, sql + "#getMPriceListID: NOT VALID M_PriceList_ID: " + M_PriceList_ID);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return idPL;
	} // setM_PriceList_ID

	/**
	 * Tipo de documento deacuerdo al tipo de evento
	 * 
	 * @param tipoDoc
	 */
	public void typeDocument(final String tipoDoc) {
		// asignamos el tipo de documento 'Receta Medica'
		if (tipoDoc.equals(Constantes.RECETA)) {
			setC_DocType_ID(MEXMEDocType.getOfName(getCtx(), Constantes.RECETA, null));// Lama
		} else if (tipoDoc.equals(Constantes.SERVICIO)) {
			setC_DocType_ID(MEXMEDocType.getOfName(getCtx(), Constantes.SERVICIO, null));// Lama
		} else if (tipoDoc.equals(Constantes.VACUNA)) {
			final MEXMEConfigDocType mDocType = MEXMEConfigDocType.get(getCtx(), null);
			if (mDocType != null) {
				setC_DocType_ID(mDocType.getC_DocTypeVacuna_ID());
			}
		}
	}

	/**
	 * Calculo de la fecha prometida
	 * 
	 * @param fechaform
	 * @param horaCadena
	 * @throws ParseException
	 * @deprecated No utilizado
	 */
//	public void promisedDate(final String fechaform, final String horaCadena) throws ParseException {
//		if (fechaform != null && !fechaform.equalsIgnoreCase("") && horaCadena != null && !horaCadena.equalsIgnoreCase("")) {
//			final StringBuilder fecha = new StringBuilder(fechaform);
//			if (horaCadena.length() > 4) {
//				fecha.append(" ").append(horaCadena.substring(0, 2)).append(":").append(horaCadena.substring(3, 5));
//			} else {
//				fecha.append(" ").append(horaCadena.substring(0, 2)).append(":").append(horaCadena.substring(2, 4));
//			}
//			setDatePromised(new Timestamp(Constantes.getSdfFechaHora().parse(fecha.toString()).getTime()));
//		} else {
//			setDatePromised(DB.getTimestampForOrg(getCtx()));
//		}
//	}

	/**
	 * Completo el documento
	 * 
	 * @param totalSinImp
	 * @param totalConImp
	 * @param lstIndica
	 * @param surtir
	 */
	public boolean setTotalsComplete(final BigDecimal totalSinImp,
			final BigDecimal totalConImp, final List<?> lstIndica,
			final boolean surtir) {
		setTotalLines(totalSinImp);
		setGrandTotal(totalConImp);
		// Si existen lineas a agregar
		if (lstIndica != null && !lstIndica.isEmpty()) {
			if (surtir) {
				final MEXMEI18N valMex = MEXMEI18N.getFromCountry(getCtx(), Env.getC_Country_ID(getCtx()), null);
				if ((valMex == null || (valMex != null && !valMex.isSurtidoInterno()))) {
					setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Completed);
					setDocAction(MEXMEActPacienteIndH.DOCACTION_Close);
					setEstatus(MEXMEActPacienteIndH.ESTATUS_ServiceOrdersCompleted);
					setIsDelivered(true);
					setCompleto(true);
				} else {
					setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Drafted);
					setDocAction(MEXMEActPacienteIndH.DOCACTION_Complete);
					setEstatus(MEXMEActPacienteIndH.ESTATUS_OrdersOfServiceRequested);
					setIsDelivered(false);
					setCompleto(false);
				}
			} else {
				setDocStatus(MEXMEActPacienteIndH.DOCSTATUS_Drafted);
				setDocAction(MEXMEActPacienteIndH.DOCACTION_Complete);
				setEstatus(MEXMEActPacienteIndH.ESTATUS_OrdersOfServiceRequested);
				setIsDelivered(false);
				setCompleto(false);
			}
		}
		return save();
	}

	/**
	 * getMsjSMS
	 * 
	 * @return
	 */
	public String getMsjSMS() {
		StringBuilder msj = new StringBuilder();
		msj.append(Utilerias.getMsg(getCtx(), "msg.histRecord.solServSMS"));
		msj.append(getDocumentNo());
		msj.append("+");
		msj.append(Utilerias.getMsg(getCtx(), "msg.histRecord.FechaProg"));
		msj.append(":");
		msj.append(StringUtils.replaceChars(Constantes.getSDFDateTime(getCtx()).formatConvert(getDateOrdered()), " ", "+"));
		return msj.toString();
	}

	/**
	 * getMsjEmail
	 * 
	 * @return
	 */
	public String getMsjEmail() {
		StringBuilder msj = new StringBuilder();
		msj.append(Utilerias.getMsg(getCtx(), "title.solicitudServ"));
		msj.append(getDocumentNo());
		msj.append(" ");
		msj.append(Utilerias.getMsg(getCtx(), "msj.fechaProgramada"));
		msj.append(":");
		msj.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getDateOrdered()));
		return msj.toString();
	}
	
	/**
	 * @return  Read back label: Yes / No
	 */
	public String getReadBackStr() {
		return MEXMECuidadosPac.getReadBackLabel(this);
	}

	/**
	 * Noted label: User Pin (or name) and date (dd/MM HH:mm)
	 * @return
	 */
	public String getNotedStr() {
		return MEXMECuidadosPac.getUserPINDate(getNotedBy(), getNotedDate());
	}

	@Override
	public String getPatientName() {
		return getCtaPac().getPatientName();
	}

	@Override
	public String getSummaryDetail() {
		final StringBuilder str = new StringBuilder();
		// Card #746 - Authentication of Discontinuation Orders
		if(DOCSTATUS_Voided.equals(getDocStatus())){
			for (MEXMEActPacienteInd item : getLineas(null, null, false)) {
				str.append(item.getProductName()).append("<br>");	
			}
		} else {
			str.append(getDetailStr(true)).append("<br>");
		}
		// dates
		if (getDateOrdered() != null) {
			str.append("<b>").append(getMsgTranslate(COLUMNNAME_DateOrdered)).append("</b>: ");
			str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getDateOrdered()));
		}
		// order type
		if (StringUtils.isNotBlank(getOrderType())) {
			str.append("<br><b>").append(getMsgTranslate(COLUMNNAME_OrderType)).append("</b>: ");
			str.append(getOrderType());
			if (StringUtils.isNotBlank(getReadBack())) {
				str.append("  <b>").append(getMsgTranslate(COLUMNNAME_ReadBack)).append("</b>: ");
				str.append(getReadBackStr());
			}
		}
		return str.toString();
	}

	/** @return requested by and requested to warehouses .- Lama Card #1173 */
	public String getDischargeDetail() {
		final StringBuilder str = new StringBuilder();
		// Requested by
		if (getM_Warehouse_Sol_ID() > 0) {
			str.append("<b>").append(Utilerias.getMsg(getCtx(), "billing.ordersIncomplete.requestedBy")).append("</b>: ");
			str.append(DB.getSQLValueString(null, "SELECT Name FROM M_Warehouse WHERE M_Warehouse_ID=?", getM_Warehouse_Sol_ID()));
			str.append(", ");
		}
		// Requested to
		if (getM_Warehouse_ID() > 0) {
			str.append(" <b>").append(Utilerias.getMsg(getCtx(), "msj.almacenApli")).append("</b>: ");
			str.append(DB.getSQLValueString(null, "SELECT Name FROM M_Warehouse WHERE M_Warehouse_ID=?", getM_Warehouse_ID()));
			str.append("<br>");
		}
		// studies
		if (DOCSTATUS_Voided.equals(getDocStatus())) {
			for (MEXMEActPacienteInd item : getLineas(null, null, false)) {
				str.append(item.getProductName()).append("<br>");
			}
		} else {
			str.append(getDetailStr(true)).append("<br>");
		}
		return str.toString();
	}

	@Override
	public boolean setAction(String action) {
		if (ACTION_AUTHENTICATE.equals(action)) {
			return approve() == null;
		} else if (ACTION_DISCONTINUE.equals(action)) {
			return voidIt() && save();
		}
		return true;
	}
	
	@Override
	public String getRecordType() {
		return Utilerias.getMsg(getCtx(),"abstracting.services");
	}
	
	/**
	 * Obtener solicitudes pendientes
	 * 
	 * @param ctx
	 * @param pacienteID
	 * @param trxName
	 * @return
	 */
	public static int getCtasSolPending(final Properties ctx, final String ctasI, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT count(DISTINCT actPacIndH.EXME_ActPacienteIndH_ID) ");
		sql.append(" FROM   EXME_ActPacienteIndH actPacIndH ");
		sql.append(" INNER JOIN   EXME_ActPacienteInd ind ON (actPacIndH.EXME_ActPacienteIndH_ID=ind.EXME_ActPacienteIndH_ID ");
		sql.append("                                         AND ind.Surtir='Y' AND ind.IsActive='Y') ");
		sql.append(" WHERE  actPacIndH.EXME_CtaPac_ID IN (").append(ctasI).append(") ");
		sql.append(" AND    actPacIndH.IsActive='Y' ");
		sql.append(" AND    actPacIndH.DocStatus  NOT IN (?,?,?) ");// Validar estatus correctos Lama
		return DB.getSQLValue(trxName, sql.toString(), DOCSTATUS_Completed, DOCSTATUS_Closed, DOCSTATUS_Voided);
	}
	
//	/**
//	 * Obtener solicitudes pendientes
//	 * 
//	 * @param ctx
//	 * @param pacienteID
//	 * @param trxName
//	 * @return
//	 */
//	public static List<ValueNamePair> getCtasSolPending(final Properties ctx,
//			final String  ctasI, final String trxName) {
//		final List<ValueNamePair> list = new ArrayList<ValueNamePair>();
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT actPacIndH.*                    ");
//		sql.append(" FROM   EXME_ActPacienteIndH actPacIndH ");
//		sql.append(" WHERE  actPacIndH.EXME_CtaPac_ID IN (").append(ctasI).append(") ");
//		sql.append(" AND    actPacIndH.DocStatus  NOT IN (?,?,?) ");//Validar estatus correctos Lama
//		sql.append(" ORDER BY actPacIndH.DateOrdered DESC ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null; //
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setString(1, DOCSTATUS_Completed);
//			pstmt.setString(2, DOCSTATUS_Closed);
//			pstmt.setString(2, DOCSTATUS_Voided);
//
//			rs = pstmt.executeQuery();
//			final String str = Utilerias.getMsg(ctx, "title.solicitudServ");
//			while (rs.next()) {
//				final StringBuilder name = new StringBuilder();
//				name.append(str).append(" ").append(rs.getString("DocumentNo")).append(" - ");
//				name.append(Constantes.getSDFDateTime(ctx).formatConvert(rs.getTimestamp("DateOrdered")));
//				
//				final StringBuilder value = new StringBuilder();
//				value.append(MEXMEProgRecordatorio.TIPORECORDATORIO_EXME_ActPacienteIndH).append("-");
//				value.append(rs.getString("EXME_ActPacienteIndH_ID"));
//				
//				list.add(new ValueNamePair(value.toString(), name.toString()));
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return list;
//	}
	
	private Date	startDate;
	private Date	endDate;
	public static int minDuration = 15;//GC

	/** Fecha inicial del evento, utilizado para la agenda WServiceSchedule (Card 635)*/
	public Date getStartDate() {
		if (startDate == null) { //RQM #4100
			DateTime datetime = new DateTime(getDatePromised() == null ? getDateOrdered() : getDatePromised());
			// si la orden esta completada, tomamos como fecha de inicio la fecha prometida, y la final en que se completo
			if (DOCSTATUS_Completed.equals(getDocStatus())
			// si la diferencia de fechas, se pasa en tiempo,
			// tomamos como fecha de inicio la resta de la fecha en que se completo y el intervalo del almacen
				&& getDateDelivered() != null && Days.daysBetween(datetime, new DateTime(getDateDelivered())).getDays() <= 1) {
				datetime = new DateTime(getDateDelivered().getTime()).minusMinutes(getWarehouse().getIntervalo(true));
			}
			startDate = TimeUtil.roundMinutes(datetime, minDuration);
		}
		return startDate;
	}

	/** Fecha final del evento, utilizado para la agenda WServiceSchedule (Card 635)*/
	public Date getEndDate() {
		if (endDate == null) {
			DateTime datetime = null; //RQM #4100
			// si el evento esta completado, la fecha final sera cuando se completo
			if (DOCSTATUS_Completed.equals(getDocStatus()) && getDateDelivered() != null) {
				datetime = new DateTime(getDateDelivered());
			}
			if (datetime == null) {
				// calcula la fecha final en base al intervalo del almacen
				if (getFecha_Fin() == null || getStartDate().after(getFecha_Fin())) {
					datetime = new DateTime(getStartDate().getTime()).plusMinutes(getWarehouse().getIntervalo(true));
				} else {
					// si tiene fecha final (definida desde agenda)
					datetime = new DateTime(getFecha_Fin());
				}
			}
			endDate = TimeUtil.roundMinutes(datetime, minDuration);
		}
		return endDate;
	}
	
	public int getEXME_Area_ID() {
		if (getM_Warehouse_ID() > 0) {
			return MEstServAlm.getEstServ(Env.getCtx(), getM_Warehouse_ID(), null).getEXME_Area_ID();
		}
		return -1;
	}

	/**
	 * Create a MEXMEEquipoH object 
	 * @param equipment
	 * @return
	 */
	public MEXMEEquipoH scheduleEquipment(final int EXME_Equipo_ID) {
		if (MEXMEEquipoH.isAvailable(getCtx(), EXME_Equipo_ID, 0, getStartDate(), getEndDate(), get_TrxName())) {
			final MEXMEEquipoH equipoH = new MEXMEEquipoH(getCtx(), 0, get_TrxName());
			equipoH.setEXME_Equipo_ID(EXME_Equipo_ID);
			equipoH.setEXME_Area_ID(getEXME_Area_ID());
			equipoH.setEstatus_Equipo(MEXMEEquipoH.ESTATUS_EQUIPO_Sacheduled);
			equipoH.setFecha_Ini(new Timestamp(getStartDate().getTime()));
			equipoH.setFecha_Fin(new Timestamp(getEndDate().getTime()));
			equipoH.setEXME_ActPacienteIndH_ID(getEXME_ActPacienteIndH_ID());
			return equipoH;
		} else {
			s_log.log(Level.SEVERE, Utilerias.getMsg(getCtx(), "equipment overlap"));// TODO
		}
		return null;
	}

	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (type == COLUMNNAME_DocumentNo.hashCode()) {
			comparable = getDocumentNo();
		} else if (type == MEXMEPaciente.COLUMNNAME_EXME_Paciente_ID.hashCode()) {
			comparable = getPatientName();
		} else if (type == COLUMNNAME_DateOrdered.hashCode()) {
			comparable = getDateOrdered();
		} else if (type == COLUMNNAME_DatePromised.hashCode()) {
			comparable = getDatePromised();
		} else if (type == COLUMNNAME_M_Warehouse_ID.hashCode()) {
			comparable = getWarehouse().getName();
		} else if (type == COLUMNNAME_MedicoNombre.hashCode() || type == COLUMNNAME_EXME_Medico_ID.hashCode()) {
			comparable = getMedicoNombre();
		} else if (type == COLUMNNAME_DateApproved.hashCode()) {
			comparable = getDateApproved();
		} else if (type == COLUMNNAME_AuthenticatedBy.hashCode()) {
			comparable = getUserAuthenticated();
		} else if (type == COLUMNNAME_DateCanceled.hashCode()) {
			comparable = getDateCanceled();
		} else if (type == COLUMNNAME_NotedBy.hashCode()) {
			comparable = getUserNoted();
		} else if (type == COLUMNNAME_NotedDate.hashCode()) {
			comparable = getNotedDate();
		} else if (type == COLUMNNAME_OrderType.hashCode()) {
			comparable = getOrderTypeStr();
		} else if (type == COLUMNNAME_ReadBack.hashCode()) {
			comparable = getReadBackStr();
		} else if (DOCSTATUS_Voided.equals(getDocStatus()) && getDis() != null) {
			comparable = getDis().onCompare(type);
		} else {
			comparable = super.onCompare(type);
		}
		return comparable;
	}
	
	@Override
	public boolean isAuthenticatedDisc() {
		// Card #746 - Authentication of Discontinuation Orders
		return MEXMEDiscontinueOrder.isAuthenticatedDisc(this);
	}
	
	@Override
	public void discontinue(String trxName, DiscontinueParams params) {
		// Card #746 - Authentication of Discontinuation Orders
		if (get_ID() > 0) {
			if (params != null) {
				setEXME_MotivoCancel_ID(params.getMotivoCancel());
				setMotivoCancel(params.getComments());
			}
			set_TrxName(trxName);
			setStatus(MEXMEActPacienteIndH.STATUS_Cancelation);
			if (!voidIt()) { // RQM #4112
				throw new MedsysException(getM_processMsg());
			}
			if (params != null) {
				setDateCanceled(params.getDate());
			}
			if (!save()) {// Utils.getLabel("cancelaServ.error.cancelar.voidIt")
				throw new MedsysException();
			}
		}
	}
	
	/**
	 * Selección de socio de negocios
	 * El socio de negocios del paciente esta a nivel de system
	 */
	public int setBPartner(){
		int socio = 0;
		if(getEXME_CtaPac_ID()>0){
			socio = getCtaPac().getExtCero().getC_BPartner_ID();
		}
		
		if(socio==0 && getActPaciente().getEXME_CtaPac_ID()>0){
			socio = getActPaciente().getCtaPac().getExtCero().getC_BPartner_ID();
		}
		
		if(socio==0 && getActPaciente().getEXME_Paciente_ID()>0){
			socio = getActPaciente().getPaciente().getC_BPartner_ID();
		}
		return socio;
	}
	
	/**
	 * @return 	enteredBy
	 */
	public String getUserEntered(){
		return MUser.getUserName(getCtx(), getCreatedBy());
	}
	/**
	 * @return 	AuthenticatedBy
	 */
	public String getUserAuthenticated(){
		return getDateApproved() == null ? "" : MUser.getUserName(getCtx(), getAuthenticatedBy());
	}
	/**
	 * @return 	NotedBy
	 */
	public String getUserNoted(){
		return getNotedDate() == null ? "" : MUser.getUserName(getCtx(), getNotedBy());
	}
	/**
	 * @return 	OrderType
	 */
	public String getOrderTypeStr(){
		return MRefList.getListName(getCtx(), X_EXME_PrescRXDet.ORDERTYPE_AD_Reference_ID, getOrderType());
	}
	
	public MEXMEDiscontinueOrder	dis	= null;

	public MEXMEDiscontinueOrder getDis() {
		if (!isActive() && dis == null) {
			dis = MEXMEDiscontinueOrder.getFrom(this);
		}
		return dis;
	}
	
	/** @return Discontinued date */
	public Timestamp getDiscontinuedDate() {
		return getDis() == null ? null : dis.getDiscontinuedDate();
	}

	/**  @return AuthenticatedBy */
	public String getDiscontinuedBy() {
		return getDis() == null ? "" : MUser.getUserName(getCtx(), dis.getCreatedBy());
	}

	/** @return DiscontinuedOrderTypeStr() */
	public String getDiscontinuedOrderTypeStr() {
		return getDis() == null ? "" : dis.getOrderTypeStr();
	}

	/** @return DiscontinuedNombre_Med */
	public String getDiscontinuedNombre_Med() {
		return getDis() == null ? "" : dis.getEXME_Medico().getNombre_Med();
	}

	/** @return Authenticated date */
	public Timestamp getDiscontinuedAuthenticatedDate() {
		return getDis() == null ? null : dis.getAuthenticated_Date();
	}
	
	/** @return DiscontinuedAuthenticatedBy */
	public String getUserDiscontinuedAuthenticatedBy() {
		return getDis() == null || dis.getAuthenticated_Date() == null ? "" : MUser.getUserName(getCtx(), dis.getAuthenticatedBy());
	}

	/**  @return DiscontinuedReadBackStr */
	public String getDiscontinuedReadBackStr() {
		return getDis() == null ? "" : MEXMECuidadosPac.getReadBackLabel(dis);
	}

	public static List<Integer> getProducts(int headerID) {
		List<Integer> prods = new ArrayList<Integer>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		try {
			
			sql.append(" SELECT M_Product_ID FROM EXME_ActPacienteInd ")
			.append(" WHERE EXME_ActPacienteIndH_ID = ?");
								
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, headerID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prods.add(rs.getInt(1));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}finally{
			DB.close(rs, pstmt);
		}
		
		return prods;
	}
	
	/**
	 * Valida si una order cuyo almacen esta configurado que maneje HL7 puede ser editado
	 * DocStatus=VO,CL,CO,IP
	 * @param validateApproved TRUE: valida IsApproved='Y'
	 * @return TRUE si la orden es de solo lectura.
	 */
	public boolean isHL7OrderReadOnly(boolean validateApproved) {
		//Lama .- Card #931
		if(getWarehouse().isHL7ORM()) {
			return (validateApproved && isApproved()) || (DOCSTATUS_Voided.equals(getDocStatus()) //
				|| DOCSTATUS_Completed.equals(getDocStatus()) //
				|| DOCSTATUS_Closed.equals(getDocStatus())//
			|| DOCSTATUS_InProgress.equals(getDocStatus()));
		} else {
			return false;
		}
	}
	
	
	/** Actualizar desde cargos a cuenta paciente */
	public void updateActPacIndH() {
		setDateDelivered(Env.getCurrentDate());
		setProcessed(true);
		setIsDelivered(true);
		setDocStatus(DocAction.STATUS_Completed);
		setDocAction(DocAction.ACTION_None);
	}

	/**
	 * Partiendo del hecho de que al actividad solo tendra productos de esa naturaleza
	 * @return true: si no es un servicio de gabinete
	 */
	public boolean isPhysicianServices() {
		boolean isPhysicianServices = false;

		// Partiendo del hecho de que al actividad solo tendra productos de esa naturaleza
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT p.M_Product_id ");
		sql.append(" FROM   EXME_ActPacienteInd actPacInd ");
		sql.append(" INNER  JOIN M_Product              p  ON p.M_Product_id = actPacInd.M_Product_id ");
		sql.append(" 						              AND p.ProductClass IN (?,?) ");
		sql.append(" WHERE  actPacInd.EXME_ActPacienteIndH_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setString(1, X_M_Product.PRODUCTCLASS_OfficeVisit);
			pstmt.setString(2, X_M_Product.PRODUCTCLASS_PhysicianServices);
			pstmt.setInt(3, getEXME_ActPacienteIndH_ID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				isPhysicianServices = true;
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "getActPacienteInd", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return isPhysicianServices;
	}
	
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
	 * Lama: cargos 2014 (se movio de CreateCharges)
	 */
	public String createNimboCharges(String trxName) {
		MEXMEConfigOV conf = MEXMEConfigOV.findActPac(getCtx(), getEXME_ActPaciente_ID(), null);
		if (conf != null && conf.getM_Warehouse_ID() > 0) {
			setM_Warehouse_ID(conf.getM_Warehouse_ID());
			save(trxName);
		}
		String success = null;
		final StringBuilder where = new StringBuilder(" EXME_ACTPACIENTEIND_ID NOT IN ");
		where.append(" (SELECT EXME_ACTPACIENTEIND_ID FROM EXME_CTAPACDET ");
		where.append(" WHERE EXME_ActPacienteInd_ID IS NOT NULL AND IsActive = 'Y') ");
		where.append(" AND SURTIR = 'Y' ");
		// Lineas de la actividad paciente
		final List<MEXMEActPacienteInd> lstActPacienteInd = getLineas(where.toString(), null, false);

		final CreateCharge createCharge = new CreateCharge(getCtx(), this);
		// Validar si existen lineas
		if (lstActPacienteInd == null || lstActPacienteInd.isEmpty()) {
			log.log(Level.SEVERE, "if(lstActPacienteInd==null){");
		} else {
			// Carga cada linea a la cuenta paciente
			for (MEXMEActPacienteInd mInd : lstActPacienteInd) {
				if (!createCharge.insertActPacIndCharges(mInd, true, true, trxName)) {
					if(createCharge.getErrores().isEmpty()){
						success = MedsysException.getMessageFromLogger();
					} else {
						success = ModelError.getMsgError(getCtx(), createCharge.getErrores());
					}
				}
			}
		}
		log.log(Level.INFO, "status=" + success);
		return success;
	}
	
	private Timestamp movementDate = null;
	
	public Timestamp getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(Timestamp movementDate) {
		this.movementDate = movementDate;
	}

	private boolean complete = false;

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	/**
	 * 
	 * @param ctx
	 * @param appointementId
	 * @param trxName
	 * @return MEXMEActPacienteIndH
	 */
	public static MEXMEActPacienteIndH getTodayServicesHeader(Properties ctx, int appointementId, String trxName) {
		StringBuilder join = new StringBuilder();
		String sql = " pac.EXME_citamedica_id = ? and EXME_ActPacienteIndH.isService = 'Y' and EXME_ActPacienteIndH.isGenerated = 'Y' ";
		join.append(" INNER JOIN EXME_ActPaciente pac ON EXME_ActPacienteIndH.EXME_ActPaciente_id = pac.EXME_ActPaciente_id ");

		return new Query(ctx, MEXMEActPacienteIndH.Table_Name, sql, trxName)//
		.addAccessLevelSQL(true)//
		.setOnlyActiveRecords(true)//
		.setParameters(appointementId)//
		.setJoins(join)//
		.first();
		
	}
}