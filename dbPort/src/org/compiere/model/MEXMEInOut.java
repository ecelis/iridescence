/*
 * Created on 01-mar-2005
 *
 */

package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.bpm.GetPrice;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Form para la pantalla principal de Facturacion Directa
 *
 * <b>Fecha:</b> 16/Febrero/2005<p>
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/09/05 23:18:54 $<p>
 *
 * @author hassan reyes
 * @version $Revision: 1.30 $
 */
/**
 * Form para la pantalla principal de Facturacion Directa
 * 
 * <b>Fecha:</b> 16/Febrero/2005
 * <p>
 * <b>Modificado: </b> $Author: taniap $
 * <p>
 * <b>En :</b> $Date: 2006/09/05 23:18:54 $
 * <p>
 * 
 * @author hassan reyes
 * @version $Revision: 1.30 $
 * 
 */
public class MEXMEInOut {
// extends MInOut {

	/** serialVersionUID */
//	private static final long serialVersionUID = 8905262673082520343L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEInOut.class);
//	/** */
//	private boolean m_IsFromActPacienteIndH = false;
//	/** */
//	private MEXMEActPacienteIndH m_ActPacienteIndH = null;
	/** Lines */
//	private MInOutLine[] m_lines = null;

//	/**
//	 * Si procede de una devolucion
//	 * 
//	 * private boolean devolucion;
//	 * 
//	 * /** Si procede de un cargo por devolucion
//	 * 
//	 * public boolean isDevolucion() { return devolucion; } public void
//	 * setDevolucion(boolean devolucion) { this.devolucion = devolucion; } /**
//	 * 
//	 * @param ctx
//	 * @param M_InOut_ID
//	 * @param trxName
//	 */
//	public MEXMEInOut(Properties ctx, int M_InOut_ID, String trxName) {
//		super(ctx, M_InOut_ID, trxName);
//		if(M_InOut_ID==0){
//			setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
//		}
//	}

//	public MEXMEInOut(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}
	
//	/**
//	 * Constructor
//	 * @param ctx
//	 * @param M_InOut_ID
//	 * @param M_warehouse_ID
//	 * @param trxName
//	 */
//	public MEXMEInOut(Properties ctx, int M_InOut_ID, int M_warehouse_ID, String trxName) {
//		super(ctx, M_InOut_ID, trxName);
//		if(M_InOut_ID==0){
//			setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
//			setM_Warehouse_ID(M_warehouse_ID);
//			setIsSOTrx(true);
//			setC_DocType_ID(MEXMEDocType.getOfName(getCtx(), MM_SHIPMENT,
//					get_TrxName()));
//			setDeliveryRule(MOrder.DELIVERYRULE_Availability);
//			setDeliveryViaRule(MOrder.DELIVERYVIARULE_Pickup);
//			setFreightCostRule(MOrder.FREIGHTCOSTRULE_FreightIncluded);
//			setFreightAmt(Env.ZERO);
//			setMovementType(MOVEMENTTYPE_CustomerShipment);
//			setC_Charge_ID(0);
//			setChargeAmt(Env.ZERO);
//			setDocAction(DOCACTION_Complete);
//		}
//	}

//	/**
//	 * @deprecated
//	 */
//	public static MEXMEInOut createFrom(MEXMEActPacienteIndH indH,
//			Timestamp movementDate,
//			Hashtable<Integer, BigDecimal> linesToDeliver,
//			boolean allAttributeInstances, Timestamp minGuaranteeDate,
//			boolean complete, MEXMEEstServ estServ, String trxName) {
//
//		s_processMsg = null;
//
//		if (indH == null) {
//			throw new IllegalArgumentException("No IndicacionH");
//		}
//		// Create Meader
//		final MEXMEInOut retValue = new MEXMEInOut(indH, 0, movementDate);
//		retValue.setDocAction(complete ? DOCACTION_Complete : DOCACTION_Prepare);
//		retValue.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID());
//		if (retValue.getC_BPartner_Location_ID() <= 0) {
//			// throw new IllegalArgumentException("No BPartner Location");
//			s_processMsg = "No BPartner Location.";
//			return null;
//		}
//
//		// Create Line
//		if (retValue.get_ID() == 0 && // ) {// not saved yet if(
//				!retValue.save(trxName)) {
//			s_processMsg = "No se genero la salida del Almacen.";
//			return null;
//			// }
//		}
//		// Check if we can create the lines
//		final MEXMEActPacienteInd[] aLines = indH.getLineas();
//		for (int i = 0; i < aLines.length; i++) {
//
//			BigDecimal totalQty = Env.ZERO;
//
//			// Stock Info
//			final boolean stocked = MProduct.isProductStocked(indH.getCtx(),
//					aLines[i].getM_Product_ID());
//			MStorage[] storage = null;
//
//			if (stocked) {
//
//				BigDecimal qty = null;
//				if (linesToDeliver != null) {
//					qty = (BigDecimal) linesToDeliver.get(new Integer(aLines[i]
//							.getEXME_ActPacienteInd_ID()));
//
//					if (qty == null || qty.compareTo(Env.ZERO) <= 0) {
//						continue; // Si no esta considerada para ser surtida no
//									// se genera linea en InOutLine.
//					}
//				}
//
//				if (qty == null) {
//					qty = aLines[i].getQtyOrdered().subtract(
//							aLines[i].getQtyDelivered());
//				}
//				// Combertimos a UOM de almacenamiento.
//				qty = MEXMEUOMConversion.convertProductFrom(indH.getCtx(),
//						aLines[i].getM_Product_ID(), aLines[i].getC_UOM_ID(),
//						qty, null);
//
//				// Nothing to deliver
//				if (qty.compareTo(Env.ZERO) == 0) {
//					continue;
//				}
//				storage = MStorage.getWarehouse(indH.getCtx(),
//						indH.getM_Warehouse_ID(), aLines[i].getM_Product_ID(),
//						0, 0, allAttributeInstances, minGuaranteeDate, false,
//						trxName);
//
//				BigDecimal maxQty = Env.ZERO;
//
//				for (int ll = 0; ll < storage.length; ll++) {
//					maxQty = maxQty.add(storage[ll].getQtyOnHand());
//				}
//				if (DELIVERYRULE_Availability
//						.equals(MOrder.DELIVERYRULE_Availability)) {
//					if (maxQty.compareTo(qty) < 0) {
//						qty = maxQty;
//					}
//				}
//
//				// Create Line
//				if (retValue.get_ID() == 0) { // not saved yet
//					retValue.save(trxName);
//				}
//				// Create a line until qty is reached
//				for (int ll = 0; ll < storage.length; ll++) {
//
//					BigDecimal lineQty = storage[ll].getQtyOnHand();
//					if (lineQty.compareTo(qty) > 0) {
//						lineQty = qty;
//					}
//					final MEXMEInOutLine line = new MEXMEInOutLine(retValue);
//					line.setOrderLine(aLines[i], storage[ll].getM_Locator_ID());
//					line.setQty(lineQty); // Qty - En unidad de almacenamiento
//
//					// Qty - En unidad de venta.
//					BigDecimal qtyEntred = MEXMEUOMConversion.convertProductTo(
//							indH.getCtx(), aLines[i].getM_Product_ID(),
//							aLines[i].getC_UOM_ID(), lineQty, null);
//
//					if (qtyEntred == null || qtyEntred.compareTo(Env.ZERO) == 0) {
//						qtyEntred = line.getMovementQty();
//					}
//					line.setQtyEntered(qtyEntred);
//
//					if (!line.save(trxName)) {
//						s_processMsg = "No se genero la salida del Almacen.";
//						return null;
//					}
//
//					totalQty = totalQty.add(lineQty);
//
//					if (aLines[i].getM_InOutLine_ID() == 0) {
//						aLines[i].setM_InOutLine_ID(line.getM_InOutLine_ID());
//					}
//
//					// Delivered everything ?
//					qty = qty.subtract(lineQty);
//
//					if (qty.compareTo(Env.ZERO) == 0) {
//						break;
//					}
//				}// storages
//
//			}// isStock
//				// Si e sservicio.
//			else if (MProduct.PRODUCTTYPE_Service.equals(aLines[i].getProduct()
//					.getProductType())) {
//				// Los servicios no crean movimiento de inventario.
//				// totalQty = aLines[i].getQtyOrdered();
//				final MEXMEInOutLine line = new MEXMEInOutLine(retValue);
//				line.setOrderLine(aLines[i], 0);
//				totalQty = aLines[i].getQtyDelivered();
//				line.setQty(totalQty); // Qty - En unidad de almacenamiento
//				line.setQtyEntered(aLines[i].getQtyDelivered());
//				if (line.save(trxName)) {
//					if (aLines[i].getM_InOutLine_ID() == 0) {
//						aLines[i].setM_InOutLine_ID(line.getM_InOutLine_ID());
//					}
//				} else {
//					s_processMsg = "No se genero la salida del Almacen.";
//					return null;
//				}
//			}
//
//			aLines[i].setDateDelivered(DB.getTimestampForOrg(indH.getCtx()));
//
//			BigDecimal qtyDelivered = MEXMEUOMConversion.convertProductTo(
//					indH.getCtx(), aLines[i].getM_Product_ID(),
//					aLines[i].getC_UOM_ID(), totalQty, null);
//
//			if (qtyDelivered == null || Env.ZERO.compareTo(qtyDelivered) == 0) {
//				qtyDelivered = totalQty;
//			}
//			aLines[i].setQtyDelivered(qtyDelivered);
//			/*
//			 * aLines[i].setLineNetAmt( aLines[i].getPriceList().multiply(
//			 * aLines[i].getQtyDelivered() ) );
//			 */
//			// aLines[i].setEXME_Area_ID(estServ.getEXME_Area_ID());
//			// aLines[i].setTipoArea(estServ.getTipoArea());
//
//			if (!aLines[i].save(trxName)) {
//				s_processMsg = "No se actualizo la linea del cargo @EXME_ActPacienteInd@ = "
//						+ aLines[i].toString();
//				return null;
//			}
//
//		} // for all order lines
//
//		// No Lines saved
//		if (retValue.get_ID() == 0) {
//			return null;
//		}
//		retValue.setActPacienteIndH(indH);
//
//		return retValue;
//	} // createFrom

//	/**
//	 * Order Constructor - create header only
//	 * 
//	 * @param order
//	 *            order
//	 * @param movementDate
//	 *            optional movement date (default today)
//	 * @param C_DocTypeShipment_ID
//	 *            document type or 0
//	 */
//	public MEXMEInOut(final MEXMEActPacienteIndH indH, final int C_DocTypeShipment_ID,
//			final Timestamp movementDate) {
//
//		super(indH.getCtx(), 0, indH.getM_Warehouse_ID(), indH.get_TrxName());
//		setIsCreatedFromActPacienteIndH(true);
//		setClientOrg(indH);
//		setC_BPartner_ID(indH.getC_BPartner_ID());
//		setEXME_CtaPac_ID(indH.getEXME_CtaPac_ID());
////		setAD_User_ID(Env.getAD_User_ID(getCtx()));
////		setM_Warehouse_ID(indH.getM_Warehouse_ID());
////		setIsSOTrx(true);
////		setMovementType(MOVEMENTTYPE_CustomerShipment);
////		setC_DocType_ID(MEXMEDocType.getOfName(getCtx(), MM_SHIPMENT,
////				get_TrxName()));
//		setMovementDate(movementDate==null?new Timestamp(System.currentTimeMillis()):movementDate);
//		setDateAcct(getMovementDate());
//		setDateOrdered(indH.getDateOrdered());
////		setDeliveryRule(MOrder.DELIVERYRULE_Availability);
////		setDeliveryViaRule(MOrder.DELIVERYVIARULE_Pickup);
////		setFreightCostRule(MOrder.FREIGHTCOSTRULE_FreightIncluded);
////		setFreightAmt(Env.ZERO);
////		setC_Charge_ID(indH.getC_Charge_ID());
////		setChargeAmt(indH.getChargeAmt());
//		setDescription(indH.getDescription());
//
//		// Direccion de la cuenta paciente o del paciente
//		MBPartnerLocation mBPLocation = null;
//		if(indH.getCtaPac()!=null && indH.getCtaPac().getC_BPartner_ID()>0){
//			mBPLocation = MEXMEBPartner.getLocations(getCtx(), indH.getCtaPac().getC_BPartner_ID(), get_TrxName());
//		}
//		if(mBPLocation==null && indH.getPaciente().getLocationPac() != null){
//			mBPLocation = indH.getPaciente().getLocationPac();
//		}
//		if (mBPLocation!=null && mBPLocation.getC_BPartner_Location_ID()>0){
//			setC_BPartner_Location_ID(mBPLocation.getC_BPartner_Location_ID());
//		}
//	} // MInOut

//	/**
//	 * Nombre del Tipo de Documento MM Shipment
//	 */
//	private final static String MM_SHIPMENT = "MM SHIPMENT";

//	/**
//	 * Order Constructor - create header only
//	 * 
//	 * @param order
//	 *            order
//	 * @param movementDate
//	 *            optional movement date (default today)
//	 * @param C_DocTypeShipment_ID
//	 *            document type or 0
//	 */
//	@SuppressWarnings("deprecation")
//	private MEXMEInOut(final MEXMECtaPac ctaPac, final int M_Warehouse_ID,
//			final int C_DocTypeShipment_ID, final Timestamp movementDate, final boolean devolucion) {
//		super(ctaPac.getCtx(), 0, M_Warehouse_ID, ctaPac.get_TrxName());
//		setIsCreatedFromActPacienteIndH(false);
//		setClientOrg(ctaPac);
//		setC_BPartner_ID(ctaPac.getC_BPartner_ID());
////		setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
//		setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
////		setM_Warehouse_ID(M_Warehouse_ID);
////		setIsSOTrx(true);
////		setC_DocType_ID(MEXMEDocType.getOfName(getCtx(), MM_SHIPMENT, get_TrxName()));
//		setMovementDate(movementDate==null?new Timestamp(System.currentTimeMillis()):movementDate);
//		setDateAcct(getMovementDate());
//		setDateOrdered(getMovementDate());
////		setDeliveryRule(MOrder.DELIVERYRULE_Availability);
////		setDeliveryViaRule(MOrder.DELIVERYVIARULE_Pickup);
////		setFreightCostRule(MOrder.FREIGHTCOSTRULE_FreightIncluded);
////		setFreightAmt(Env.ZERO);
////		setC_Charge_ID(0);
////		setChargeAmt(Env.ZERO);
////		setDocAction(DOCACTION_Complete);
//
//		// Direccion de la cuenta paciente o del paciente
//		MBPartnerLocation mBPLocation = null;
//		if(ctaPac.getC_BPartner_ID()>0){
//			mBPLocation = MEXMEBPartner.getLocations(getCtx(), ctaPac.getC_BPartner_ID(), get_TrxName());
//		}
//		if(mBPLocation==null && ctaPac.getPaciente().getLocationPac() != null){
//			mBPLocation = ctaPac.getPaciente().getLocationPac();
//		}
//		if (mBPLocation!=null && mBPLocation.getC_BPartner_Location_ID()>0){
//			setC_BPartner_Location_ID(mBPLocation.getC_BPartner_Location_ID());
//		}
//
//		// Alejandro.- Si es devolucion asignar el tipo de Movimiento C+, de lo
//		// contrario es embarque, asignar el tipo de movimiento (C-)
//		if (devolucion) {
//			setMovementType(MOVEMENTTYPE_CustomerReturns);
//			setDescription(Msg.getMsg(getCtx(), Env.getLanguage(getCtx())
//					.getLocale(), "msj.ctaPac.devol", new String[] { ctaPac
//				.getDocumentNo() }));
//		} else {
////			setMovementType(MOVEMENTTYPE_CustomerShipment);
//			setDescription(Msg.getMsg(getCtx(), Env.getLanguage(getCtx())
//					.getLocale(), "msj.ctaPac.cargo", new String[] { ctaPac
//				.getDocumentNo() }));
//		}
//	} // MInOut

	/**
	 * Este método se creo para la Generacion de Cargos Diarios Creamos el
	 * embarque a partir de una lista de cargos de una CtaPac. Todos los cargos
	 * deberias provenir del mismo almacen y Locator. SOLO CARGOS DIARIOS
	 * 
	 * @param ctaPacExt
	 * @param movementDate
	 * @param linesToDeliver
	 * @param allAttributeInstances
	 * @param minGuaranteeDate
	 * @param devolucion
	 * @param estServLog
	 * @param M_Warehouse_ID
	 * @param trxName
	 * @param esCargoAutomatico
	 * @return
	 */
	public static MInOut createFrom(final Properties ctx,
			final MEXMECtaPac ctaPac, final MCtaPacDet[] linesToDeliver,
			final MEXMEEstServ estServLog, final int M_Warehouse_ID,
			final Timestamp date, final String trxName) {

		s_processMsg = null;

		if (ctaPac == null) {
			throw new IllegalArgumentException("No CtaPacExt");
		} else {
			s_log.info("cDiario MInOut.createFrom "+ ctaPac.getEXME_CtaPac_ID());
		}

		// Create Meader
		final MInOut retValue = new MInOut(ctaPac, M_Warehouse_ID, Env.getCurrentDate(), false);
		retValue.setAD_OrgTrx_ID(estServLog.getAD_OrgTrx_ID());// Estacion de
																// logueo quien
																// solicita los
																// productos

		// Create Line
		if (retValue.get_ID() == 0 && // ) {// not saved yet if(!
				!retValue.save(trxName)) {
			s_processMsg = "No se genero la salida del Almacen.";
			return null;
		}
		
		// Barrido del detalle de las cuentas pacientes.
		for (MCtaPacDet mCtaPacDet : linesToDeliver) {
				
			BigDecimal totalQty = Env.ZERO;

			// Determinar si el producto al que se le va a dar salida esta en
			// stock.
			final boolean stocked = MProduct.isProductStocked(ctx, mCtaPacDet.getM_Product_ID());
			if (stocked) {
				s_log.info("cDiario CtaPac:"+ ctaPac.getEXME_CtaPac_ID() + " stocked ");
				
				// Determinar la cantidad a entregar (Cantidad ordenada para el
				// paciente - Cantidad que ya se le ha enviado)
				BigDecimal qty = mCtaPacDet.getQtyOrdered().subtract(mCtaPacDet.getQtyDelivered());

				// Convertir a UOM de almacenamiento la cantidad a entregar.
				qty = MEXMEUOMConversion.convertProductFrom(ctx,
						mCtaPacDet.getM_Product_ID(),
						mCtaPacDet.getC_UOM_ID(), qty, null);

				// Nothing to deliver
				if (qty.compareTo(Env.ZERO) == 0) {
					continue;
				}
				// Obtener lista de MStorage en base al almacen y al producto de
				// ctaPacDet
				final MStorage[] storage = MStorage.getWarehouse(ctx,
						M_Warehouse_ID, mCtaPacDet.getM_Product_ID(), 0,
						0, false, null, false, trxName);

				BigDecimal maxQty = Env.ZERO;
				 // Obtener el  total de cantidad a la mano
				for (MStorage mStorage : storage) {
					maxQty = maxQty.add(mStorage.getQtyOnHand());
				}
				if (X_M_InOut.DELIVERYRULE_Availability
						.equals(MOrder.DELIVERYRULE_Availability) && // ) {if (
						maxQty.compareTo(qty) < 0) {
					qty = maxQty;
					// }
				}

				// Create an M_InOutLine until M_Storage's qty is reached
				for (MStorage mStorage : storage) {
					BigDecimal lineQty = mStorage.getQtyOnHand();
					if (lineQty.compareTo(qty) > 0) {
						lineQty = qty;
					}
					// if(devolucion)
					// lineQty = qty.abs();

					final MInOutLine line = new MInOutLine(retValue);
					line.setOrderLine(mCtaPacDet, mStorage.getM_Locator_ID(), lineQty);
					line.setQty(lineQty); // Qty - En unidad de almacenamiento
					// Qty - En unidad de venta.
					BigDecimal qtyEntred = MEXMEUOMConversion.convertProductTo(
							ctx, mCtaPacDet.getM_Product_ID(),
							mCtaPacDet.getC_UOM_ID(), lineQty, null);

					if (qtyEntred == null || qtyEntred.compareTo(Env.ZERO) == 0) {
						qtyEntred = line.getMovementQty();
					}
					line.setQtyEntered(qtyEntred);
					line.save(trxName);

					totalQty = totalQty.add(lineQty);

					if (mCtaPacDet.getM_InOutLine_ID() == 0) {
						mCtaPacDet.setM_InOutLine_ID(line.getM_InOutLine_ID());
					}

					// Delivered everything ?
					qty = qty.subtract(lineQty);

					if (qty.compareTo(Env.ZERO) == 0) {
						break;
					}
				}// storages
					// isStock
			} else {
				s_log.info("cDiario CtaPac:"+ ctaPac.getEXME_CtaPac_ID() + " sevice ? ");
				
				// si es un servicio
				if (MProduct.PRODUCTTYPE_Service.equals(mCtaPacDet.getProduct().getProductType())) {

					s_log.info("cDiario CtaPac:"+ ctaPac.getEXME_CtaPac_ID() + " service ");
					
					final MInOutLine line = new MInOutLine(retValue);
					line.setOrderLine(mCtaPacDet, 0, Env.ZERO);
					totalQty = mCtaPacDet.getQtyOrdered();
					line.setQty(totalQty); // Qty - En unidad de almacenamiento
					line.setQtyEntered(mCtaPacDet.getQtyDelivered());
					if (line.save(trxName) && mCtaPacDet.getM_InOutLine_ID() == 0) {
						mCtaPacDet.setM_InOutLine_ID(line.getM_InOutLine_ID());
					}
				}
			}
			
			if(mCtaPacDet.getDateDelivered()==null){
				mCtaPacDet.setDateDelivered(Env.getCurrentDate());
			}
			
			BigDecimal qtyDelivered = MEXMEUOMConversion.convertProductTo(ctx,
					mCtaPacDet.getM_Product_ID(),
					mCtaPacDet.getC_UOM_ID(), totalQty, null);

			if (qtyDelivered == null || Env.ZERO.compareTo(qtyDelivered) == 0) {
				qtyDelivered = totalQty;
			}
			mCtaPacDet.setQtyDelivered(qtyDelivered);
			mCtaPacDet.setEXME_Area_ID(estServLog.getEXME_Area_ID());// de logueo
			mCtaPacDet.setTipoArea(estServLog.getTipoArea());// de logeo

			if (mCtaPacDet.getPriceActual() == null
					|| mCtaPacDet.getPriceActual().compareTo(Env.ZERO) <= 0
					|| mCtaPacDet.getPriceActualInv()
							.compareTo(Env.ZERO) <= 0) {

				final MPrecios priceV = GetPrice.getPriceCargo(retValue.getCtx(), mCtaPacDet);

				if (priceV.getPriceStd().compareTo(Env.ZERO) <= 0) {
					s_processMsg = "No se calcularon correctamente los precios para la linea: "
							+ mCtaPacDet.toString()
							+ " - "
							+ priceV.getProcessMsg();
					s_log.severe("cDiario priceV.getPriceStd().compareTo(Env.ZERO) <= 0 "+ ctaPac.getEXME_CtaPac_ID()
					+", product: "+ mCtaPacDet.getM_Product_ID());
					return null;
				} else {
					s_log.info("cDiario priceV.getPriceStd().compareTo(Env.ZERO) > 0 "+ ctaPac.getEXME_CtaPac_ID()
					+", product: "+ mCtaPacDet.getM_Product_ID());
				}

				mCtaPacDet = priceV.preciosActual(mCtaPacDet);
			}

			mCtaPacDet.setCgoProcesado(true);
			mCtaPacDet.setQtyPendiente(mCtaPacDet.getQtyDelivered());
			mCtaPacDet.setQtyPaquete(Env.ZERO);
			mCtaPacDet.setLineNetAmt();
//			Env.getC_Country_ID(ctx);
			
			if (mCtaPacDet.save(trxName)) {
				s_log.info("cDiario "+ ctaPac.getEXME_CtaPac_ID()+" Cargo OK "+ mCtaPacDet.getEXME_CtaPacDet_ID());
			} else {
				s_processMsg = "No se actualizo la linea del cargo @EXME_CtaPacDet@ = "+ mCtaPacDet.toString();
				s_log.severe("cDiario !mCtaPacDet.save(trxName) "+ ctaPac.getEXME_CtaPac_ID()
				+", product: "+ mCtaPacDet.getM_Product_ID());
				return null;
			}

		} // for all order lines

		// No Lines saved
		if (retValue.get_ID() == 0) {
			s_log.severe("M_InOut_ID: 0");
			return null;
		}
		return retValue;

	} // createFrom

	/**
	 * Obtenemos el embarque a partir de una factura (CANCELACION DE FACTURA
	 * DIRECTA <<DEVOLPAGOACTION>>)
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param C_Invoice_ID
	 *            El identificador de la factura
	 * @param trxName
	 *            El nombre de la transaccion
	 * @return Un objeto de tipo MEXMEInvoice
	 */
	public static MInOut getOfInvoice(Properties ctx, int C_InvoiceID,
			String trxName) {
		MInOut inOut = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(
					" SELECT DISTINCT (iol.M_inout_id) as ident FROM C_InvoiceLine ")
					.append(" INNER JOIN M_InoutLine iol ON (iol.M_InoutLine_ID = C_InvoiceLine.M_InoutLine_ID) ")
					.append(" WHERE C_InvoiceLine.IsActive = 'Y' ")
					.append(" AND C_InvoiceLine.C_Invoice_ID = ? ");

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
					"C_InvoiceLine"));

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, C_InvoiceID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				inOut = new MInOut(ctx, rs.getInt("ident"), trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return inOut;
	}

	/**
	 * Implementacion de la superclase. Solo que este no restringe a que pueda
	 * ser una Orden de Venta y sin traer referencia a un registro C_Order.
	 * Basado en version: $Id: MEXMEInOut.java,v 1.30 2006/09/05 23:18:54 taniap
	 * Exp $
	 * 
	 * protected boolean beforeSave(boolean newRecord) { return true; }
	 * 
	 * 
	 * 
	 * /** Process Message
	 */
//	private String m_processMsg = null;
//
	/** Process Message Estatico */
	private static String s_processMsg = null;

	/** Just Prepared Flag */
	// private boolean m_justPrepared = false;

	/**
	 * 
	 * Prepare Document
	 * 
	 * @return new status (In Progress or Invalid)
	 * 
	 * 
	 * 
	 *         public String prepareIt()
	 * 
	 *         {
	 * 
	 *         log.info(toString()); m_processMsg =
	 *         ModelValidationEngine.get().fireDocValidate(this,
	 *         ModelValidator.TIMING_BEFORE_PREPARE); if (m_processMsg != null){
	 *         return DocAction.STATUS_Invalid; } final MDocType docType =
	 *         MDocType.get(getCtx(), getC_DocType_ID());
	 * 
	 *         // Std Period open? if (!MPeriod.isOpen(getCtx(), getDateAcct(),
	 *         docType.getDocBaseType(), getAD_Org_ID())) { m_processMsg =
	 *         "@PeriodClosed@"; return DocAction.STATUS_Invalid; }
	 * 
	 *         // Credit Check if (isSOTrx() && !isReversal()) { final MBPartner
	 *         bpart = new MBPartner (getCtx(), getC_BPartner_ID(), null); if
	 *         (MBPartner
	 *         .SOCREDITSTATUS_CreditStop.equals(bpart.getSOCreditStatus())) {
	 *         m_processMsg = "@BPartnerCreditStop@ - @TotalOpenBalance@=" +
	 *         bpart.getTotalOpenBalance() + ", @SO_CreditLimit@=" +
	 *         bpart.getSO_CreditLimit(); return DocAction.STATUS_Invalid; } if
	 *         (MBPartner.SOCREDITSTATUS_CreditHold.equals(bpart.
	 *         getSOCreditStatus())) { m_processMsg =
	 *         "@BPartnerCreditHold@ - @TotalOpenBalance@=" +
	 *         bpart.getTotalOpenBalance() + ", @SO_CreditLimit@=" +
	 *         bpart.getSO_CreditLimit(); return DocAction.STATUS_Invalid; }
	 *         //BigDecimal notInvoicedAmt =
	 *         MBPartner.getNotInvoicedAmt(getC_BPartner_ID()); //if
	 *         (MBPartner.SOCREDITSTATUS_CreditHold
	 *         .equals(bp.getSOCreditStatus(notInvoicedAmt))) //{ //
	 *         m_processMsg = "@BPartnerOverSCreditHold@ - @TotalOpenBalance@="
	 *         // + bp.getTotalOpenBalance() + ", @NotInvoicedAmt@=" +
	 *         notInvoicedAmt // + ", @SO_CreditLimit@=" +
	 *         bp.getSO_CreditLimit(); // return DocAction.STATUS_Invalid; //} }
	 * 
	 *         // Lines final MEXMEInOutLine[] lines = getLines(true); if (lines
	 *         == null || lines.length == 0) { m_processMsg = "@NoLines@";
	 *         s_processMsg = "@NoLines@"; return DocAction.STATUS_Invalid; }
	 *         BigDecimal Volume = Env.ZERO; BigDecimal Weight = Env.ZERO;
	 * 
	 *         // Mandatory Attributes for (int i = 0; i < lines.length; i++) {
	 *         final MEXMEInOutLine line = lines[i]; final MProduct product =
	 *         line.getProduct(); if (product != null) { Volume =
	 *         Volume.add(product.getVolume().multiply(line.getMovementQty()));
	 *         Weight =
	 *         Weight.add(product.getWeight().multiply(line.getMovementQty()));
	 *         } // if (line.getM_AttributeSetInstance_ID() != 0){ continue; }
	 *         if (product != null) { final int M_AttributeSet_ID =
	 *         product.getM_AttributeSet_ID(); if (M_AttributeSet_ID != 0) {
	 *         final MAttributeSet mas = MAttributeSet.get(getCtx(),
	 *         M_AttributeSet_ID); if (mas != null && ((isSOTrx() &&
	 *         mas.isMandatory()) || (!isSOTrx() && mas.isMandatoryAlways())) )
	 *         { m_processMsg = "@M_AttributeSet_ID@ @IsMandatory@"; return
	 *         DocAction.STATUS_Invalid; } } } } setVolume(Volume);
	 *         setWeight(Weight);
	 * 
	 *         if (!isReversal()) // don't change reversal {
	 *         checkMaterialPolicy(); // set MASI //REV
	 *         if(!isCreatedFromActPacienteIndH()){ createConfirmation(); } }
	 * 
	 *         m_justPrepared = true; if
	 *         (!DOCACTION_Complete.equals(getDocAction())){
	 *         setDocAction(DOCACTION_Complete); } return
	 *         DocAction.STATUS_InProgress; } // prepareIt
	 */

	/**
	 * 
	 * Complete Document
	 * 
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */

	/*
	 * public String completeIt() { // Re-Check if (!m_justPrepared) { final
	 * String status = prepareIt(); if
	 * (!DocAction.STATUS_InProgress.equals(status)){ return status; } }
	 * 
	 * //Rev if(!isCreatedFromActPacienteIndH()){ // Outstanding (not processed)
	 * Incoming Confirmations ? final MInOutConfirm[] confirmations =
	 * getConfirmations(true); for (int i = 0; i < confirmations.length; i++) {
	 * final MInOutConfirm confirm = confirmations[i]; if
	 * (!confirm.isProcessed()) { if
	 * (MInOutConfirm.CONFIRMTYPE_CustomerConfirmation
	 * .equals(confirm.getConfirmType())){ continue; } // m_processMsg =
	 * "Open @M_InOutConfirm_ID@: " + confirm.getConfirmTypeName() + " - " +
	 * confirm.getDocumentNo(); return DocAction.STATUS_InProgress; } }
	 * 
	 * // Implicit Approval if (!isApproved()){ approveIt(); }
	 * log.info(toString());
	 * 
	 * }
	 * 
	 * final StringBuffer info = new StringBuffer();
	 * 
	 * // For all lines final MEXMEInOutLine[] lines = getLines(false); for (int
	 * lineIndex = 0; lineIndex < lines.length; lineIndex++) { final
	 * MEXMEInOutLine sLine = lines[lineIndex]; final MProduct product =
	 * sLine.getProduct();
	 * 
	 * // Qty & Type final String MovementType = getMovementType(); BigDecimal
	 * Qty = sLine.getMovementQty(); if (MovementType.charAt(1) == '-' ||
	 * this.isDevolucion()){ // C+ Customer Return from Charge, C- Customer
	 * Shipment - V- Vendor Return Qty = Qty.negate(); }
	 * 
	 * BigDecimal QtySO = Env.ZERO; BigDecimal QtyPO = Env.ZERO;
	 * 
	 * MCtaPacDet oLine = null;
	 * 
	 * //if(isCreatedFromActPacienteIndH()){ // Update Order Line if
	 * (sLine.getEXME_CtaPacDet_ID() != 0) { oLine = new MCtaPacDet (getCtx(),
	 * sLine.getEXME_CtaPacDet_ID(), get_TrxName()); // Convert UoM Sale to UoM
	 * Storage //oLine = MCtaPacDet.convertLinesbyUoM(oLine);
	 * 
	 * log.fine("OrderLine - Reserved=" + oLine.getQtyReserved()
	 * 
	 * + ", Delivered=" + oLine.getQtyReserved()); if (isSOTrx()) { QtySO =
	 * sLine.getMovementQty(); } else { QtyPO = sLine.getMovementQty(); } }
	 * /*}else{ QtySO = sLine.getMovementQty(); }
	 */

	/*
	 * log.info("Line=" + sLine.getLine() + " - Qty=" + sLine.getMovementQty());
	 * 
	 * 
	 * // Stock Movement - Counterpart MOrder.reserveStock if (product != null
	 * && product.isStocked() ) { log.fine("Material Transaction");
	 * MEXMETransaction mtrx = null; //Expert:Hassan final
	 * List<MEXMETransaction> mtrxs = new ArrayList<MEXMETransaction>();
	 * //Expert:Hassan - Para guardar la referencia de cada transaccion por
	 * SAcct // Reservation ASI - assume none int
	 * reservationAttributeSetInstance_ID = 0; //
	 * sLine.getM_AttributeSetInstance_ID(); if (oLine != null) {
	 * reservationAttributeSetInstance_ID =
	 * oLine.getM_AttributeSetInstance_ID(); } // if
	 * (sLine.getM_AttributeSetInstance_ID() == 0) { final MInOutLineMA mas[] =
	 * MInOutLineMA.get(getCtx(), //Cuando el istance = 0 twry........
	 * sLine.getM_InOutLine_ID(), get_TrxName()); for (int j = 0; j <
	 * mas.length; j++) { final MInOutLineMA ma = mas[j]; BigDecimal QtyMA =
	 * ma.getMovementQty(); if (MovementType.charAt(1) == '-') {// C- Customer
	 * Shipment - V- Vendor Return QtyMA = QtyMA.negate(); } BigDecimal QtySOMA
	 * = Env.ZERO; BigDecimal QtyPOMA = Env.ZERO; if
	 * (sLine.getEXME_CtaPacDet_ID() != 0) { if (isSOTrx()) { QtySOMA =
	 * ma.getMovementQty(); } else { QtyPOMA = ma.getMovementQty(); } } //
	 * Update Storage - see also VMatch.createMatchRecord if
	 * (!MStorage.add(getCtx(), getM_Warehouse_ID(), sLine.getM_Locator_ID(),
	 * sLine.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(),
	 * reservationAttributeSetInstance_ID, QtyMA, QtySOMA.negate(),
	 * QtyPOMA.negate(), get_TrxName())) { m_processMsg =
	 * "Cannot correct Inventory (MA)"; return DocAction.STATUS_Invalid; } /*
	 * Expert:Hassan - Registramos una tranzaccion por cada Esquema contable del
	 * Cliente (Bloque Modificado)
	 */
	/*
	 * final MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(),
	 * getAD_Client_ID()); for(int h = 0; h < acctss.length; h++){ final
	 * MAcctSchema as = acctss[h]; // Create Transaction
	 * 
	 * //Current Costs String costingMethod = as.getCostingMethod();
	 * 
	 * // Expert: #Post,Cost And Price MProductCategoryAcct pca =
	 * MProductCategoryAcct.getAcct( product, as);
	 * 
	 * if(pca == null) { //Lama: valida que exista la cuenta de la categoria del
	 * producto. m_processMsg = "Cannot find account from product category";
	 * return DocAction.STATUS_Invalid; }
	 * 
	 * if (pca.getCostingMethodDefault() != null) { costingMethod =
	 * pca.getCostingMethodDefault(); } // Create Transaction mtrx = new
	 * MEXMETransaction ( getCtx(), product, ma.getM_AttributeSetInstance_ID(),
	 * as, sLine.getAD_Org_ID(), costingMethod, QtyMA, 0, false, MovementType,
	 * sLine.getM_Locator_ID(), getMovementDate(),
	 * get_TrxName());//Expert:Hassan - Se pasa el esquema contable
	 * 
	 * mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
	 * 
	 * if (!mtrx.save()) { m_processMsg =
	 * "Could not create Material Transaction (MA)"; return
	 * DocAction.STATUS_Invalid; } mtrxs.add(mtrx); }/* Expert:Hassan - Fin del
	 * Bloque
	 */
	/*
	 * } } // sLine.getM_AttributeSetInstance_ID() != 0 if (mtrx == null) { //
	 * Fallback: Update Storage - see also VMatch.createMatchRecord if
	 * (!MStorage.add(getCtx(), getM_Warehouse_ID(), sLine.getM_Locator_ID(),
	 * sLine.getM_Product_ID(), sLine.getM_AttributeSetInstance_ID(),
	 * reservationAttributeSetInstance_ID, Qty, QtySO.negate(), QtyPO.negate(),
	 * get_TrxName())) { m_processMsg = "Cannot correct Inventory"; return
	 * DocAction.STATUS_Invalid; } /* Expert:Hassan - Registramos una
	 * tranzaccion por cada Esquema contable del Cliente (Bloque Modificado)
	 */
	/*
	 * final MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(),
	 * getAD_Client_ID()); for(int h = 0; h < acctss.length; h++){ final
	 * MAcctSchema as = acctss[h]; // FallBack: Create Transaction
	 * 
	 * 
	 * //Current Costs String costingMethod = as.getCostingMethod();
	 * 
	 * // Expert: #Post,Cost And Price MProductCategoryAcct pca =
	 * MProductCategoryAcct.getAcct( product, as);
	 * 
	 * if (pca.getCostingMethodDefault() != null) costingMethod =
	 * pca.getCostingMethodDefault();
	 * 
	 * // Create Transaction mtrx = new MEXMETransaction ( getCtx(), product,
	 * sLine.getM_AttributeSetInstance_ID(), as, sLine.getAD_Org_ID(),
	 * costingMethod, Qty, 0, false, MovementType, sLine.getM_Locator_ID(),
	 * getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema
	 * contable
	 * 
	 * mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID()); if (!mtrx.save()) {
	 * m_processMsg = "Could not create Material Transaction"; return
	 * DocAction.STATUS_Invalid; } }/* Expert:Hassan - Fin del Bloque
	 */
	/*
	 * } } // stock movement
	 * 
	 * // Correct Order Line if (product != null && oLine != null){ // other in
	 * VMatch.createMatchRecord BigDecimal qtyOReserved =
	 * MEXMEUOMConversion.convertProductTo(sLine.getCtx(),
	 * sLine.getM_Product_ID(), oLine.getC_UOM_ID(), sLine.getMovementQty(),
	 * null); //Alejandro.Asigna a qtyReserved 0, si no recupera un valor en el
	 * metodo convertProductTo, //ya que mas adelante realiza operaciones con
	 * esta cantidad if(qtyOReserved==null){ qtyOReserved=BigDecimal.ZERO; }
	 * oLine.setQtyReserved(oLine.getQtyReserved().subtract(qtyOReserved)); } //
	 * Update Sales Order Line if (oLine != null) { if (isSOTrx() // PO is done
	 * by Matching || sLine.getM_Product_ID() == 0) // PO Charges, empty lines {
	 * 
	 * //Convertir la la cantidad entregada correspondiente a la cantidad de la
	 * unidad de medida del cargo (UoM de Venta). Qty =
	 * MEXMEUOMConversion.convertProductTo(sLine.getCtx(),
	 * sLine.getM_Product_ID(), oLine.getC_UOM_ID(), Qty, null); if(Qty!=null){
	 * if (isSOTrx()) {
	 * oLine.setQtyDelivered(oLine.getQtyDelivered().subtract(Qty)); } else {
	 * oLine.setQtyDelivered(oLine.getQtyDelivered().add(Qty)); } } //Este
	 * timestamp sirve para guardar la fecha de MovementDate y la hora de
	 * Created para almacenarla en DateDelivered de CtaPacDet final Timestamp
	 * time = Timestamp.valueOf(getMovementDate().toString().substring(0, 10) +
	 * " " + getCreated().toString().substring(11,
	 * getCreated().toString().length())); oLine.setDateDelivered(time); //
	 * overwrite=last } if (!oLine.save()) { m_processMsg =
	 * "Could not update Order Line"; return DocAction.STATUS_Invalid; } else {
	 * log.fine("OrderLine -> Reserved=" + oLine.getQtyReserved() +
	 * ", Delivered=" + oLine.getQtyReserved()); } }
	 * 
	 * // Create Asset for SO if (product != null && isSOTrx() &&
	 * product.isCreateAsset() && sLine.getMovementQty().signum() > 0 &&
	 * !isReversal()) { log.fine("Asset"); info.append("@A_Asset_ID@: "); int
	 * noAssets = sLine.getMovementQty().intValue(); if
	 * (!product.isOneAssetPerUOM()){ noAssets = 1; } for (int i = 0; i <
	 * noAssets; i++) { if (i > 0) { info.append(" - "); } int deliveryCount =
	 * i+1; if (!product.isOneAssetPerUOM()) { deliveryCount = 0; } final MAsset
	 * asset = new MAsset (this, sLine, deliveryCount); if
	 * (!asset.save(get_TrxName())) { m_processMsg = "Could not create Asset";
	 * return DocAction.STATUS_Invalid; } info.append(asset.getValue()); } } //
	 * Asset
	 * 
	 * } // for all lines
	 * 
	 * // Counter Documents final MEXMEInOut counter = createCounterDoc(); if
	 * (counter != null) {
	 * info.append(" - @CounterDoc@: @M_InOut_ID@=").append(counter
	 * .getDocumentNo()); } if(isCreatedFromActPacienteIndH()){
	 * 
	 * if(m_ActPacienteIndH == null){ m_processMsg =
	 * "No se encontro la Indicacion a partir de la cual se esta generando el embarque."
	 * ; return DocAction.STATUS_Invalid; }
	 * 
	 * m_ActPacienteIndH.setDateDelivered(getMovementDate());
	 * m_ActPacienteIndH.setProcessed(true);
	 * m_ActPacienteIndH.setIsDelivered(true);
	 * m_ActPacienteIndH.setDocStatus(DocAction.STATUS_Completed);
	 * m_ActPacienteIndH.setDocAction(DocAction.ACTION_None);
	 * 
	 * if(!m_ActPacienteIndH.save()){ m_processMsg =
	 * "No fue posible actualizar el estatus de la indicacion."; return
	 * DocAction.STATUS_Invalid;
	 * 
	 * } }
	 * 
	 * // User Validation final String valid =
	 * ModelValidationEngine.get().fireDocValidate(this,
	 * ModelValidator.TIMING_AFTER_COMPLETE);
	 * 
	 * if (valid != null) { m_processMsg = valid; return
	 * DocAction.STATUS_Invalid; }
	 * 
	 * 
	 * m_processMsg = info.toString(); setProcessed(true);
	 * setDocAction(DOCACTION_Close); return DocAction.STATUS_Completed; } //
	 * completeIt
	 */

	/**
	 * 
	 * Complete Document
	 * 
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 * 
	 * 
	 * 
	 *         public String completeIt(List<MCtaPacDet> lista) { // Re-Check if
	 *         (!m_justPrepared) { final String status = prepareIt(); if
	 *         (!DocAction.STATUS_InProgress.equals(status)){ return status; } }
	 * 
	 *         if(!isCreatedFromActPacienteIndH()){ // Outstanding (not
	 *         processed) Incoming Confirmations ? final MInOutConfirm[]
	 *         confirmations = getConfirmations(true); for (int i = 0; i <
	 *         confirmations.length; i++) { final MInOutConfirm confirm =
	 *         confirmations[i]; if (!confirm.isProcessed()) { if
	 *         (MInOutConfirm.
	 *         CONFIRMTYPE_CustomerConfirmation.equals(confirm.getConfirmType
	 *         ())) { continue; } // m_processMsg = "Open @M_InOutConfirm_ID@: "
	 *         + confirm.getConfirmTypeName() + " - " + confirm.getDocumentNo();
	 *         return DocAction.STATUS_InProgress; } }
	 * 
	 *         // Implicit Approval if (!isApproved()) { approveIt(); }
	 *         log.info(toString());
	 * 
	 *         }
	 * 
	 *         final StringBuffer info = new StringBuffer();
	 * 
	 *         // For all lines final MEXMEInOutLine[] lines = getLines(false);
	 *         for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
	 *         final MEXMEInOutLine sLine = lines[lineIndex]; final MProduct
	 *         product = sLine.getProduct();
	 * 
	 *         // Qty & Type final String MovementType = getMovementType();
	 *         BigDecimal Qty = sLine.getMovementQty(); if
	 *         (MovementType.charAt(1) == '-') { // C- Customer Shipment - V-
	 *         Vendor Return Qty = Qty.negate(); } BigDecimal QtySO = Env.ZERO;
	 *         BigDecimal QtyPO = Env.ZERO;
	 * 
	 *         MCtaPacDet oLine = null;
	 * 
	 *         //if(isCreatedFromActPacienteIndH()){ // Update Order Line
	 * 
	 *         List<MInOutLine> linesByLots = new ArrayList<MInOutLine>(); for
	 *         (int i = 0; i < lista.size(); i++) {
	 * 
	 *         linesByLots = lista.get(i).getLstSurtidoDet();
	 * 
	 *         for (int j = 0; j < linesByLots.size(); j++) { if
	 *         (linesByLots.get(j).getM_InOutLine_ID() ==
	 *         sLine.getM_InOutLine_ID()){ oLine = lista.get(i); } }
	 * 
	 *         }
	 * 
	 *         //Procesar la linea de MInOutLine que esta relacionada con la
	 *         orden de venta: aaranda if (oLine==null){ continue; }
	 * 
	 *         log.fine("OrderLine - Reserved=" + oLine.getQtyReserved() +
	 *         ", Delivered=" + oLine.getQtyDelivered());
	 * 
	 *         if (isSOTrx()) { QtySO = sLine.getMovementQty(); } else { QtyPO =
	 *         sLine.getMovementQty(); }
	 * 
	 *         log.info("Line=" + sLine.getLine() + " - Qty=" +
	 *         sLine.getMovementQty());
	 * 
	 *         // Stock Movement - Counterpart MOrder.reserveStock if (product
	 *         != null && product.isStocked() ) {
	 *         log.fine("Material Transaction"); MEXMETransaction mtrx = null;
	 *         //Expert:Hassan final List<MEXMETransaction> mtrxs = new
	 *         ArrayList<MEXMETransaction>(); //Expert:Hassan - Para guardar la
	 *         referencia de cada transaccion por SAcct // Reservation ASI -
	 *         assume none final int reservationAttributeSetInstance_ID = 0; //
	 *         sLine.getM_AttributeSetInstance_ID(); /*if (oLine != null)
	 *         reservationAttributeSetInstance_ID =
	 *         oLine.getM_AttributeSetInstance_ID();
	 */
	//
	/*
	 * if (sLine.getM_AttributeSetInstance_ID() == 0) { final MInOutLineMA mas[]
	 * = MInOutLineMA.get(getCtx(), //Cuando el istance = 0 twry........
	 * sLine.getM_InOutLine_ID(), get_TrxName()); for (int j = 0; j <
	 * mas.length; j++) { final MInOutLineMA ma = mas[j]; BigDecimal QtyMA =
	 * ma.getMovementQty(); if (MovementType.charAt(1) == '-'){ // C- Customer
	 * Shipment - V- Vendor Return QtyMA = QtyMA.negate(); } BigDecimal QtySOMA
	 * = Env.ZERO; BigDecimal QtyPOMA = Env.ZERO; if
	 * (sLine.getEXME_CtaPacDet_ID() != 0) { if (isSOTrx()) { QtySOMA =
	 * ma.getMovementQty(); } else { QtyPOMA = ma.getMovementQty(); } } //
	 * Update Storage - see also VMatch.createMatchRecord if
	 * (!MStorage.add(getCtx(), getM_Warehouse_ID(), sLine.getM_Locator_ID(),
	 * sLine.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(),
	 * reservationAttributeSetInstance_ID, QtyMA, QtySOMA.negate(),
	 * QtyPOMA.negate(), get_TrxName())) { m_processMsg =
	 * "Cannot correct Inventory (MA)"; return DocAction.STATUS_Invalid; } /*
	 * Expert:Hassan - Registramos una tranzaccion por cada Esquema contable del
	 * Cliente (Bloque Modificado)
	 */
	/*
	 * final MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(),
	 * getAD_Client_ID()); for(int h = 0; h < acctss.length; h++){ final
	 * MAcctSchema as = acctss[h]; // Create Transaction
	 * 
	 * //Current Costs String costingMethod = as.getCostingMethod();
	 * 
	 * // Expert: #Post,Cost And Price MProductCategoryAcct pca =
	 * MProductCategoryAcct.getAcct( product, as); if
	 * (pca.getCostingMethodDefault() != null) costingMethod =
	 * pca.getCostingMethodDefault();
	 * 
	 * // Create Transaction mtrx = new MEXMETransaction ( getCtx(), product,
	 * ma.getM_AttributeSetInstance_ID(), as, sLine.getAD_Org_ID(),
	 * costingMethod, QtyMA, 0, false, MovementType, sLine.getM_Locator_ID(),
	 * getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema
	 * contable
	 * 
	 * mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
	 * 
	 * if (!mtrx.save()) { m_processMsg =
	 * "Could not create Material Transaction (MA)"; return
	 * DocAction.STATUS_Invalid; } mtrxs.add(mtrx); }/* Expert:Hassan - Fin del
	 * Bloque
	 */
	/*
	 * } } // sLine.getM_AttributeSetInstance_ID() != 0 if (mtrx == null) { //
	 * Fallback: Update Storage - see also VMatch.createMatchRecord if
	 * (!MStorage.add(getCtx(), getM_Warehouse_ID(), sLine.getM_Locator_ID(),
	 * sLine.getM_Product_ID(), sLine.getM_AttributeSetInstance_ID(),
	 * reservationAttributeSetInstance_ID, Qty, QtySO.negate(), QtyPO.negate(),
	 * get_TrxName())) { m_processMsg = "Cannot correct Inventory"; return
	 * DocAction.STATUS_Invalid; } /* Expert:Hassan - Registramos una
	 * tranzaccion por cada Esquema contable del Cliente (Bloque Modificado)
	 */
	/*
	 * final MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(),
	 * getAD_Client_ID()); for(int h = 0; h < acctss.length; h++){ final
	 * MAcctSchema as = acctss[h]; // FallBack: Create Transaction
	 * 
	 * 
	 * //Current Costs String costingMethod = as.getCostingMethod(); // Expert:
	 * #Post,Cost And Price MProductCategoryAcct pca =
	 * MProductCategoryAcct.getAcct( product, as); if
	 * (pca.getCostingMethodDefault() != null) costingMethod =
	 * pca.getCostingMethodDefault();
	 * 
	 * // Create Transaction mtrx = new MEXMETransaction ( getCtx(), product,
	 * sLine.getM_AttributeSetInstance_ID(), as, sLine.getAD_Org_ID(),
	 * costingMethod, Qty, 0, false, MovementType, sLine.getM_Locator_ID(),
	 * getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema
	 * contable
	 * 
	 * mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID()); if (!mtrx.save()) {
	 * m_processMsg = "Could not create Material Transaction"; return
	 * DocAction.STATUS_Invalid; } }/* Expert:Hassan - Fin del Bloque
	 */
	/*
	 * } } // stock movement
	 * 
	 * // Correct Order Line if (product != null && oLine != null){ // other in
	 * VMatch.createMatchRecord
	 * oLine.setQtyReserved(oLine.getQtyReserved().subtract
	 * (sLine.getMovementQty())); }
	 * 
	 * // Update Sales Order Line if (oLine != null) { if (isSOTrx() // PO is
	 * done by Matching || sLine.getM_Product_ID() == 0) // PO Charges, empty
	 * lines { if (isSOTrx()) {
	 * oLine.setQtyDelivered(oLine.getQtyDelivered().subtract(Qty)); } else {
	 * oLine.setQtyDelivered(oLine.getQtyDelivered().add(Qty)); } //Este
	 * timestamp sirve para guardar la fecha de MovementDate y la hora de
	 * Created para almacenarla en DateDelivered de CtaPacDet final Timestamp
	 * time = Timestamp.valueOf(getMovementDate().toString().substring(0, 10) +
	 * " " + getCreated().toString().substring(11,
	 * getCreated().toString().length())); oLine.setDateDelivered(time); //
	 * overwrite=last } if (!oLine.save()) { m_processMsg =
	 * "Could not update Order Line"; return DocAction.STATUS_Invalid; } else {
	 * log.fine("OrderLine -> Reserved=" + oLine.getQtyReserved() +
	 * ", Delivered=" + oLine.getQtyDelivered()); } }
	 * 
	 * // Create Asset for SO if (product != null && isSOTrx() &&
	 * product.isCreateAsset() && sLine.getMovementQty().signum() > 0 &&
	 * !isReversal()) { log.fine("Asset"); info.append("@A_Asset_ID@: "); int
	 * noAssets = sLine.getMovementQty().intValue(); if
	 * (!product.isOneAssetPerUOM()){ noAssets = 1; } for (int i = 0; i <
	 * noAssets; i++) { if (i > 0){ info.append(" - "); } int deliveryCount =
	 * i+1; if (!product.isOneAssetPerUOM()){ deliveryCount = 0; } final MAsset
	 * asset = new MAsset (this, sLine, deliveryCount); if
	 * (!asset.save(get_TrxName())) { m_processMsg = "Could not create Asset";
	 * return DocAction.STATUS_Invalid; } info.append(asset.getValue()); } } //
	 * Asset
	 * 
	 * 
	 * // Matching /* if (!isSOTrx() && sLine.getM_Product_ID() != 0 &&
	 * !isReversal()) { BigDecimal matchQty = sLine.getMovementQty(); // Invoice
	 * - Receipt Match (requires Product) MInvoiceLine iLine =
	 * MInvoiceLine.getOfInOutLine (sLine); if (iLine != null &&
	 * iLine.getM_Product_ID() != 0) { MMatchInv[] matches =
	 * MMatchInv.get(getCtx(), sLine.getM_InOutLine_ID(),
	 * iLine.getC_InvoiceLine_ID(), get_TrxName()); if (matches == null ||
	 * matches.length == 0) { MMatchInv inv = new MMatchInv (iLine,
	 * getMovementDate(), matchQty); if (sLine.getM_AttributeSetInstance_ID() !=
	 * iLine.getM_AttributeSetInstance_ID()) {
	 * iLine.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
	 * iLine.save(); // update matched invoice with ASI
	 * inv.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID()); }
	 * if (!inv.save(get_TrxName())) { m_processMsg =
	 * "Could not create Inv Matching"; return DocAction.STATUS_Invalid; } } }
	 * 
	 * // Link to Order if (sLine.getC_OrderLine_ID() != 0) {
	 * log.fine("PO Matching"); // Ship - PO MMatchPO po = MMatchPO.create
	 * (null, sLine, getMovementDate(), matchQty); if (!po.save(get_TrxName()))
	 * { m_processMsg = "Could not create PO Matching"; return
	 * DocAction.STATUS_Invalid; } // Update PO with ASI if (oLine != null &&
	 * oLine.getM_AttributeSetInstance_ID() == 0) {
	 * oLine.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
	 * oLine.save(get_TrxName()); } } else // No Order - Try finding links via
	 * Invoice { // Invoice has an Order Link if (iLine != null &&
	 * iLine.getC_OrderLine_ID() != 0) { // Invoice is created before Shipment
	 * log.fine("PO(Inv) Matching"); // Ship - Invoice MMatchPO po =
	 * MMatchPO.create (iLine, sLine, getMovementDate(), matchQty); if
	 * (!po.save(get_TrxName())) { m_processMsg =
	 * "Could not create PO(Inv) Matching"; return DocAction.STATUS_Invalid; }
	 * // Update PO with ASI oLine = new MOrderLine (getCtx(),
	 * po.getC_OrderLine_ID(), get_TrxName()); if (oLine != null &&
	 * oLine.getM_AttributeSetInstance_ID() == 0) {
	 * oLine.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
	 * oLine.save(get_TrxName()); } } } // No Order
	 * 
	 * //Expert:Hassan - Inicia el bloque - Para actualizar costos y Lista de
	 * Pecios de Venta, por cada linea de la orden de compra
	 * 
	 * if(oLine != null){ //no entra try{
	 * 
	 * MConfigPre cp = MConfigPre.get(getCtx(), get_TrxName());
	 * 
	 * if(cp!=null) {
	 * 
	 * //Actualizamos M_ProductCosting.
	 * cp.updateProductInfo(Env.getContextAsInt(getCtx(),"$C_AcctSchema_ID"),
	 * oLine);
	 * 
	 * //Calculamos la version de la lista de precios de venta. (Siempre se
	 * actualiza la mas nueva). if(!cp.calculateSalesPL(oLine)){ m_processMsg =
	 * "No se completo el calculo de la lista de precios de venta.";
	 * DB.rollback(false, cp.get_TrxName()); return DocAction.STATUS_Invalid; }
	 * DB.commit(false, cp.get_TrxName());
	 * 
	 * }
	 * 
	 * }catch (SQLException sqle) { s_log.log(Level.SEVERE, sqle.getMessage(),
	 * sqle); } }
	 * 
	 * //Expert:Hassan - Fin del bloque. }
	 */// PO Matching
	/*
	 * } // for all lines
	 * 
	 * // Counter Documents final MEXMEInOut counter = createCounterDoc(); if
	 * (counter != null){
	 * info.append(" - @CounterDoc@: @M_InOut_ID@=").append(counter
	 * .getDocumentNo()); } if(isCreatedFromActPacienteIndH()){
	 * 
	 * if(m_ActPacienteIndH == null){ m_processMsg =
	 * "No se encontro la Indicacion a partir de la cual se esta generando el embarque."
	 * ; return DocAction.STATUS_Invalid; }
	 * 
	 * m_ActPacienteIndH.setDateDelivered(getMovementDate());
	 * m_ActPacienteIndH.setProcessed(true);
	 * m_ActPacienteIndH.setIsDelivered(true);
	 * m_ActPacienteIndH.setDocStatus(DocAction.STATUS_Completed);
	 * m_ActPacienteIndH.setDocAction(DocAction.ACTION_None);
	 * 
	 * if(!m_ActPacienteIndH.save()){ m_processMsg =
	 * "No fue posible actualizar el estatus de la indicacion."; return
	 * DocAction.STATUS_Invalid;
	 * 
	 * } }
	 * 
	 * // User Validation final String valid =
	 * ModelValidationEngine.get().fireDocValidate(this,
	 * ModelValidator.TIMING_AFTER_COMPLETE);
	 * 
	 * if (valid != null) { m_processMsg = valid; return
	 * DocAction.STATUS_Invalid; }
	 * 
	 * 
	 * m_processMsg = info.toString(); setProcessed(true);
	 * setDocAction(DOCACTION_Close); return DocAction.STATUS_Completed; } //
	 * completeIt
	 */

	/**
	 * Para afectar ActPacienteIndH Complete Document
	 * 
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 * 
	 *         public String completeItAct() { int actPacienteIndH = 0;
	 * 
	 *         // Re-Check if (!m_justPrepared) { final String status =
	 *         prepareIt(); if (!DocAction.STATUS_InProgress.equals(status)){
	 *         return status; } }
	 * 
	 *         if(!isCreatedFromActPacienteIndH()){ // Outstanding (not
	 *         processed) Incoming Confirmations ? final MInOutConfirm[]
	 *         confirmations = getConfirmations(true); for (int i = 0; i <
	 *         confirmations.length; i++) { final MInOutConfirm confirm =
	 *         confirmations[i]; if (!confirm.isProcessed()) { if
	 *         (MInOutConfirm.
	 *         CONFIRMTYPE_CustomerConfirmation.equals(confirm.getConfirmType
	 *         ())){ continue; } // m_processMsg = "Open @M_InOutConfirm_ID@: "
	 *         + confirm.getConfirmTypeName() + " - " + confirm.getDocumentNo();
	 *         return DocAction.STATUS_InProgress; } }
	 * 
	 *         // Implicit Approval if (!isApproved()){ approveIt(); }
	 *         log.info(toString());
	 * 
	 *         }
	 * 
	 *         final StringBuffer info = new StringBuffer();
	 * 
	 *         // For all lines final MEXMEInOutLine[] lines = getLines(false);
	 *         for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
	 *         final MEXMEInOutLine sLine = lines[lineIndex]; final MProduct
	 *         product = sLine.getProduct();
	 * 
	 *         // Qty & Type final String MovementType = getMovementType();
	 *         BigDecimal Qty = sLine.getMovementQty(); if
	 *         (MovementType.charAt(1) == '-') {// C- Customer Shipment - V-
	 *         Vendor Return Qty = Qty.negate(); } BigDecimal QtySO = Env.ZERO;
	 *         BigDecimal QtyPO = Env.ZERO;
	 * 
	 *         MEXMEActPacienteInd oLine = null;
	 * 
	 *         //if(isCreatedFromActPacienteIndH()){ // Update Order Line if
	 *         (sLine.getEXME_ActPacienteInd_ID() != 0) { oLine = new
	 *         MEXMEActPacienteInd (getCtx(), sLine.getEXME_ActPacienteInd_ID(),
	 *         get_TrxName()); log.fine("OrderLine - Reserved=" +
	 *         oLine.getQtyReserved() + ", Delivered=" +
	 *         oLine.getQtyReserved()); actPacienteIndH =
	 *         oLine.getEXME_ActPacienteIndH_ID(); if (isSOTrx()) { QtySO =
	 *         sLine.getMovementQty(); } else { QtyPO = sLine.getMovementQty();
	 *         } } /*}else{ QtySO = sLine.getMovementQty(); }
	 */

	/*
	 * log.info("Line=" + sLine.getLine() + " - Qty=" + sLine.getMovementQty());
	 * 
	 * 
	 * // Stock Movement - Counterpart MOrder.reserveStock if (product != null
	 * && product.isStocked() ) { log.fine("Material Transaction");
	 * MEXMETransaction mtrx = null; //Expert:Hassan final
	 * List<MEXMETransaction> mtrxs = new ArrayList<MEXMETransaction>();
	 * //Expert:Hassan - Para guardar la referencia de cada transaccion por
	 * SAcct // Reservation ASI - assume none final int
	 * reservationAttributeSetInstance_ID = 0; //
	 * sLine.getM_AttributeSetInstance_ID(); /*if (oLine != null)
	 * reservationAttributeSetInstance_ID =
	 * oLine.getM_AttributeSetInstance_ID();
	 */
	//
	/*
	 * if (sLine.getM_AttributeSetInstance_ID() == 0) { final MInOutLineMA mas[]
	 * = MInOutLineMA.get(getCtx(), //Cuando el istance = 0 twry........
	 * sLine.getM_InOutLine_ID(), get_TrxName()); for (int j = 0; j <
	 * mas.length; j++) { final MInOutLineMA ma = mas[j]; BigDecimal QtyMA =
	 * ma.getMovementQty(); if (MovementType.charAt(1) == '-') {// C- Customer
	 * Shipment - V- Vendor Return QtyMA = QtyMA.negate(); } BigDecimal QtySOMA
	 * = Env.ZERO; BigDecimal QtyPOMA = Env.ZERO; if
	 * (sLine.getEXME_ActPacienteInd_ID() != 0) { if (isSOTrx()){ QtySOMA =
	 * ma.getMovementQty(); }else{ QtyPOMA = ma.getMovementQty(); } } // Update
	 * Storage - see also VMatch.createMatchRecord if (!MStorage.add(getCtx(),
	 * getM_Warehouse_ID(), sLine.getM_Locator_ID(), sLine.getM_Product_ID(),
	 * ma.getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID,
	 * QtyMA, QtySOMA.negate(), QtyPOMA.negate(), get_TrxName())) { m_processMsg
	 * = "Cannot correct Inventory (MA)"; return DocAction.STATUS_Invalid; } /*
	 * Expert:Hassan - Registramos una tranzaccion por cada Esquema contable del
	 * Cliente (Bloque Modificado)
	 */
	/*
	 * final MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(),
	 * getAD_Client_ID()); for(int h = 0; h < acctss.length; h++){ final
	 * MAcctSchema as = acctss[h]; // Create Transaction
	 * 
	 * //Current Costs String costingMethod = as.getCostingMethod(); // Expert:
	 * #Post,Cost And Price MProductCategoryAcct pca =
	 * MProductCategoryAcct.getAcct( product, as); if
	 * (pca.getCostingMethodDefault() != null) costingMethod =
	 * pca.getCostingMethodDefault();
	 * 
	 * // Create Transaction mtrx = new MEXMETransaction ( getCtx(), product,
	 * ma.getM_AttributeSetInstance_ID(), as, sLine.getAD_Org_ID(),
	 * costingMethod, QtyMA, 0, false, MovementType, sLine.getM_Locator_ID(),
	 * getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema
	 * contable
	 * 
	 * mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
	 * 
	 * if (!mtrx.save()) { m_processMsg =
	 * "Could not create Material Transaction (MA)"; return
	 * DocAction.STATUS_Invalid; } mtrxs.add(mtrx); }/* Expert:Hassan - Fin del
	 * Bloque
	 */
	/*
	 * } } // sLine.getM_AttributeSetInstance_ID() != 0 if (mtrx == null) { //
	 * Fallback: Update Storage - see also VMatch.createMatchRecord if
	 * (!MStorage.add(getCtx(), getM_Warehouse_ID(), sLine.getM_Locator_ID(),
	 * sLine.getM_Product_ID(), sLine.getM_AttributeSetInstance_ID(),
	 * reservationAttributeSetInstance_ID, Qty, QtySO.negate(), QtyPO.negate(),
	 * get_TrxName())) { m_processMsg = "Cannot correct Inventory"; return
	 * DocAction.STATUS_Invalid; } /* Expert:Hassan - Registramos una
	 * tranzaccion por cada Esquema contable del Cliente (Bloque Modificado)
	 */
	/*
	 * final MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(),
	 * getAD_Client_ID()); for(int h = 0; h < acctss.length; h++){ final
	 * MAcctSchema as = acctss[h]; // FallBack: Create Transaction
	 * 
	 * 
	 * //Current Costs String costingMethod = as.getCostingMethod(); // Expert:
	 * #Post,Cost And Price MProductCategoryAcct pca =
	 * MProductCategoryAcct.getAcct( product, as); if
	 * (pca.getCostingMethodDefault() != null) costingMethod =
	 * pca.getCostingMethodDefault();
	 * 
	 * // Create Transaction mtrx = new MEXMETransaction ( getCtx(), product,
	 * sLine.getM_AttributeSetInstance_ID(), as, sLine.getAD_Org_ID(),
	 * costingMethod, Qty, 0, false, MovementType, sLine.getM_Locator_ID(),
	 * getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema
	 * contable
	 * 
	 * mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID()); if (!mtrx.save()) {
	 * m_processMsg = "Could not create Material Transaction"; return
	 * DocAction.STATUS_Invalid; } }/* Expert:Hassan - Fin del Bloque
	 */
	/*
	 * } } // stock movement
	 * 
	 * // Correct Order Line if (product != null && oLine != null){ // other in
	 * VMatch.createMatchRecord
	 * oLine.setQtyReserved(oLine.getQtyReserved().subtract
	 * (sLine.getMovementQty())); } // Update Sales Order Line if (oLine !=
	 * null) { if (isSOTrx() // PO is done by Matching ||
	 * sLine.getM_Product_ID() == 0) // PO Charges, empty lines { if
	 * (isSOTrx()){
	 * oLine.setQtyDelivered(oLine.getQtyDelivered().subtract(Qty)); }else{
	 * oLine.setQtyDelivered(oLine.getQtyDelivered().add(Qty)); }
	 * oLine.setDateDelivered(getMovementDate()); // overwrite=last } if
	 * (!oLine.save()) { m_processMsg = "Could not update Order Line"; return
	 * DocAction.STATUS_Invalid; } else { log.fine("OrderLine -> Reserved=" +
	 * oLine.getQtyReserved() + ", Delivered=" + oLine.getQtyReserved()); } }
	 * 
	 * // Create Asset for SO if (product != null && isSOTrx() &&
	 * product.isCreateAsset() && sLine.getMovementQty().signum() > 0 &&
	 * !isReversal()) { log.fine("Asset"); info.append("@A_Asset_ID@: "); int
	 * noAssets = sLine.getMovementQty().intValue(); if
	 * (!product.isOneAssetPerUOM()){ noAssets = 1; } for (int i = 0; i <
	 * noAssets; i++) { if (i > 0) { info.append(" - "); } int deliveryCount =
	 * i+1; if (!product.isOneAssetPerUOM()) { deliveryCount = 0; } final MAsset
	 * asset = new MAsset (this, sLine, deliveryCount); if
	 * (!asset.save(get_TrxName())) { m_processMsg = "Could not create Asset";
	 * return DocAction.STATUS_Invalid; } info.append(asset.getValue()); } } //
	 * Asset
	 * 
	 * 
	 * // Matching /* if (!isSOTrx() && sLine.getM_Product_ID() != 0 &&
	 * !isReversal()) { BigDecimal matchQty = sLine.getMovementQty(); // Invoice
	 * - Receipt Match (requires Product) MInvoiceLine iLine =
	 * MInvoiceLine.getOfInOutLine (sLine); if (iLine != null &&
	 * iLine.getM_Product_ID() != 0) { MMatchInv[] matches =
	 * MMatchInv.get(getCtx(), sLine.getM_InOutLine_ID(),
	 * iLine.getC_InvoiceLine_ID(), get_TrxName()); if (matches == null ||
	 * matches.length == 0) { MMatchInv inv = new MMatchInv (iLine,
	 * getMovementDate(), matchQty); if (sLine.getM_AttributeSetInstance_ID() !=
	 * iLine.getM_AttributeSetInstance_ID()) {
	 * iLine.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
	 * iLine.save(); // update matched invoice with ASI
	 * inv.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID()); }
	 * if (!inv.save(get_TrxName())) { m_processMsg =
	 * "Could not create Inv Matching"; return DocAction.STATUS_Invalid; } } }
	 * 
	 * // Link to Order if (sLine.getC_OrderLine_ID() != 0) {
	 * log.fine("PO Matching"); // Ship - PO MMatchPO po = MMatchPO.create
	 * (null, sLine, getMovementDate(), matchQty); if (!po.save(get_TrxName()))
	 * { m_processMsg = "Could not create PO Matching"; return
	 * DocAction.STATUS_Invalid; } // Update PO with ASI if (oLine != null &&
	 * oLine.getM_AttributeSetInstance_ID() == 0) {
	 * oLine.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
	 * oLine.save(get_TrxName()); } } else // No Order - Try finding links via
	 * Invoice { // Invoice has an Order Link if (iLine != null &&
	 * iLine.getC_OrderLine_ID() != 0) { // Invoice is created before Shipment
	 * log.fine("PO(Inv) Matching"); // Ship - Invoice MMatchPO po =
	 * MMatchPO.create (iLine, sLine, getMovementDate(), matchQty); if
	 * (!po.save(get_TrxName())) { m_processMsg =
	 * "Could not create PO(Inv) Matching"; return DocAction.STATUS_Invalid; }
	 * // Update PO with ASI oLine = new MOrderLine (getCtx(),
	 * po.getC_OrderLine_ID(), get_TrxName()); if (oLine != null &&
	 * oLine.getM_AttributeSetInstance_ID() == 0) {
	 * oLine.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
	 * oLine.save(get_TrxName()); } } } // No Order
	 * 
	 * //Expert:Hassan - Inicia el bloque - Para actualizar costos y Lista de
	 * Pecios de Venta, por cada linea de la orden de compra
	 * 
	 * if(oLine != null){ //no entra try{
	 * 
	 * MConfigPre cp = MConfigPre.get(getCtx(), get_TrxName());
	 * 
	 * if(cp!=null) {
	 * 
	 * //Actualizamos M_ProductCosting.
	 * cp.updateProductInfo(Env.getContextAsInt(getCtx(),"$C_AcctSchema_ID"),
	 * oLine);
	 * 
	 * //Calculamos la version de la lista de precios de venta. (Siempre se
	 * actualiza la mas nueva). if(!cp.calculateSalesPL(oLine)){ m_processMsg =
	 * "No se completo el calculo de la lista de precios de venta.";
	 * DB.rollback(false, cp.get_TrxName()); return DocAction.STATUS_Invalid; }
	 * DB.commit(false, cp.get_TrxName());
	 * 
	 * }
	 * 
	 * }catch (SQLException sqle) { s_log.log(Level.SEVERE, sqle.getMessage(),
	 * sqle); } }
	 * 
	 * //Expert:Hassan - Fin del bloque. }
	 */// PO Matching
	/*
	 * } // for all lines
	 * 
	 * // Counter Documents final MEXMEInOut counter = createCounterDoc(); if
	 * (counter != null){
	 * info.append(" - @CounterDoc@: @M_InOut_ID@=").append(counter
	 * .getDocumentNo()); } if(isCreatedFromActPacienteIndH()){
	 * 
	 * m_ActPacienteIndH = new MEXMEActPacienteIndH(getCtx(), actPacienteIndH,
	 * get_TrxName());
	 * 
	 * if(m_ActPacienteIndH == null){ m_processMsg =
	 * "No se encontro la Indicacion a partir de la cual se esta generando el embarque."
	 * ; return DocAction.STATUS_Invalid; }
	 * 
	 * m_ActPacienteIndH.setDateDelivered(getMovementDate());
	 * m_ActPacienteIndH.setProcessed(true);
	 * m_ActPacienteIndH.setIsDelivered(true);
	 * m_ActPacienteIndH.setDocStatus(DocAction.STATUS_Completed);
	 * m_ActPacienteIndH.setDocAction(DocAction.ACTION_None);
	 * 
	 * if(!m_ActPacienteIndH.save()){ m_processMsg =
	 * "No fue posible actualizar el estatus de la indicacion."; return
	 * DocAction.STATUS_Invalid;
	 * 
	 * } }
	 * 
	 * // User Validation final String valid =
	 * ModelValidationEngine.get().fireDocValidate(this,
	 * ModelValidator.TIMING_AFTER_COMPLETE);
	 * 
	 * if (valid != null) { m_processMsg = valid; return
	 * DocAction.STATUS_Invalid; }
	 * 
	 * m_processMsg = info.toString(); setProcessed(true);
	 * setDocAction(DOCACTION_Close); return DocAction.STATUS_Completed; } //
	 * completeIt
	 * 
	 * 
	 * 
	 * 
	 * /** Reversal Flag private boolean m_reversal = false;
	 * 
	 * /** Set Reversal
	 * 
	 * @param reversal reversal
	 * 
	 * @SuppressWarnings("unused") private void setReversal(boolean reversal) {
	 * m_reversal = reversal; } // setReversal /** Is Reversal
	 * 
	 * @return reversal
	 * 
	 * private boolean isReversal() { return m_reversal; } // isReversal
	 * 
	 * /** Check Material Policy Sets line ASI
	 * 
	 * private void checkMaterialPolicy() { final int no =
	 * MInOutLineMA.deleteInOutMA(getM_InOut_ID(), get_TrxName()); if (no > 0) {
	 * log.config("Delete old #" + no); } final MEXMEInOutLine[] lines =
	 * getLines(false);
	 * 
	 * // Incoming Trx final String MovementType = getMovementType(); final
	 * boolean inTrx = MovementType.charAt(1) == '+'; // V+ Vendor Receipt final
	 * MClient client = MClient.get(getCtx());
	 * 
	 * // Check Lines for (int i = 0; i < lines.length; i++) { final
	 * MEXMEInOutLine line = lines[i]; boolean needSave = false; final MProduct
	 * product = line.getProduct();
	 * 
	 * // Need to have Location if (product != null && line.getM_Locator_ID() ==
	 * 0) { line.setM_Warehouse_ID(getM_Warehouse_ID());
	 * line.setM_Locator_ID(inTrx ? Env.ZERO : line.getMovementQty()); //
	 * default Locator needSave = true; }
	 * 
	 * // Attribute Set Instance if (product != null &&
	 * line.getM_AttributeSetInstance_ID() == 0) { if (inTrx) { final
	 * MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), 0,
	 * get_TrxName()); asi.setClientOrg(getAD_Client_ID(), 0);
	 * if(product.isLot()){ //Lote Omar de la Rosa asi.setM_AttributeSet_ID(0);
	 * final String palabra = line.getLotInfo(); final String arr[] =
	 * MEXMELot.getLotInformationArr(palabra);
	 * 
	 * asi.setLot(arr[0]); asi.setDescription(arr[1]); if(arr[2] != null &&
	 * arr[2].length() > 0){ try { final Date fecha =
	 * Constantes.getSdfFecha().parse(arr[2]); asi.setGuaranteeDate(new
	 * Timestamp(fecha.getTime())); } catch (ParseException e) {
	 * s_log.log(Level.SEVERE, e.getMessage(), e); } } //Lote Fin Omar de la
	 * Rosa }else{ asi.setM_AttributeSet_ID(product.getM_AttributeSet_ID()); }
	 * 
	 * if (asi.save()) {
	 * line.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
	 * log.config("New ASI=" + line); needSave = true; } } else // Outgoing Trx
	 * { final MProductCategory pc = MProductCategory.get(getCtx(),
	 * product.getM_Product_Category_ID()); String MMPolicy = pc.getMMPolicy();
	 * if (MMPolicy == null || MMPolicy.length() == 0){ MMPolicy =
	 * client.getMMPolicy(); } // final MStorage[] storages =
	 * MStorage.getAllWithASI(getCtx(), line.getM_Product_ID(),
	 * line.getM_Locator_ID(), MClient.MMPOLICY_FiFo.equals(MMPolicy),
	 * get_TrxName()); BigDecimal qtyToDeliver = line.getMovementQty(); for (int
	 * ii = 0; ii < storages.length; ii++) { final MStorage storage =
	 * storages[ii]; if (ii == 0) { if
	 * (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0) {
	 * line.setM_AttributeSetInstance_ID
	 * (storage.getM_AttributeSetInstance_ID()); needSave = true;
	 * log.config("Direct - " + line); qtyToDeliver = Env.ZERO; } else {
	 * log.config("Split - " + line); final MInOutLineMA ma = new MInOutLineMA
	 * (line, storage.getM_AttributeSetInstance_ID(), storage.getQtyOnHand());
	 * // if (!ma.save()) ma.save(); qtyToDeliver =
	 * qtyToDeliver.subtract(storage.getQtyOnHand()); log.fine("#" + ii + ": " +
	 * ma + ", QtyToDeliver=" + qtyToDeliver); } } else // create addl material
	 * allocation { final MInOutLineMA ma = new MInOutLineMA (line,
	 * storage.getM_AttributeSetInstance_ID(), qtyToDeliver); if
	 * (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0) { qtyToDeliver =
	 * Env.ZERO; } else { ma.setMovementQty(storage.getQtyOnHand());
	 * qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand()); } // if
	 * (!ma.save()) ma.save(); log.fine("#" + ii + ": " + ma + ", QtyToDeliver="
	 * + qtyToDeliver); } if (qtyToDeliver.signum() == 0){ break; } } // for all
	 * storages
	 * 
	 * // No AttributeSetInstance found for remainder if (qtyToDeliver.signum()
	 * != 0) { final MInOutLineMA ma = new MInOutLineMA (line, 0, qtyToDeliver);
	 * // if (!ma.save()) ma.save(); log.fine("##: " + ma); } } // outgoing Trx
	 * } // attributeSetInstance
	 * 
	 * if (needSave && !line.save()) { log.severe("NOT saved " + line); } } //
	 * for all lines } // checkMaterialPolicy
	 * 
	 * 
	 * /**
	 * 
	 * NO Modificado - Basado en la version : MInOut.java,v 1.52 2005/03/11
	 * 20:26:03 jjanke Exp $
	 * 
	 * Create Counter Document
	 * 
	 * 
	 * 
	 * private MEXMEInOut createCounterDoc()
	 * 
	 * {
	 * 
	 * // Is this a counter doc ?
	 * 
	 * if (getRef_InOut_ID() != 0) {
	 * 
	 * return null; }
	 * 
	 * 
	 * // Org Must be linked to BPartner
	 * 
	 * final MOrg org = MOrg.get(getCtx(), getAD_Org_ID());
	 * 
	 * final int counterC_BPartner_ID = org.getLinkedC_BPartner_ID();
	 * 
	 * if (counterC_BPartner_ID == 0){
	 * 
	 * return null; } // Business Partner needs to be linked to Org
	 * 
	 * final MBPartner bp = new MBPartner (getCtx(), getC_BPartner_ID(), null);
	 * 
	 * final int counterAD_Org_ID = bp.getAD_OrgBP_ID_Int();
	 * 
	 * if (counterAD_Org_ID == 0){
	 * 
	 * return null; }
	 * 
	 * 
	 * final MBPartner counterBP = new MBPartner (getCtx(),
	 * counterC_BPartner_ID, null);
	 * 
	 * final MOrgInfo counterOrgInfo = MOrgInfo.get(getCtx(), counterAD_Org_ID);
	 * 
	 * log.info("Counter BP=" + counterBP.getName());
	 * 
	 * 
	 * 
	 * // Document Type
	 * 
	 * int C_DocTypeTarget_ID = 0;
	 * 
	 * final MDocTypeCounter counterDT =
	 * MDocTypeCounter.getCounterDocType(getCtx(), getC_DocType_ID());
	 * 
	 * if (counterDT != null)
	 * 
	 * {
	 * 
	 * log.fine(counterDT.toString());
	 * 
	 * if (!counterDT.isCreateCounter() || !counterDT.isValid()) {
	 * 
	 * return null; } C_DocTypeTarget_ID = counterDT.getCounter_C_DocType_ID();
	 * 
	 * }
	 * 
	 * else // indirect
	 * 
	 * {
	 * 
	 * C_DocTypeTarget_ID = MDocTypeCounter.getCounterDocType_ID(getCtx(),
	 * getC_DocType_ID());
	 * 
	 * log.fine("Indirect C_DocTypeTarget_ID=" + C_DocTypeTarget_ID);
	 * 
	 * if (C_DocTypeTarget_ID <= 0) {
	 * 
	 * return null; } }
	 * 
	 * 
	 * 
	 * // Deep Copy
	 * 
	 * final MEXMEInOut counter = copyFrom(this, getMovementDate(), !isSOTrx(),
	 * true, get_TrxName(), true);
	 * 
	 * 
	 * 
	 * //
	 * 
	 * counter.setAD_Org_ID(counterAD_Org_ID);
	 * 
	 * counter.setM_Warehouse_ID(counterOrgInfo.getM_Warehouse_ID());
	 * 
	 * //
	 * 
	 * counter.setBPartner(counterBP);
	 * 
	 * // Refernces (Should not be required
	 * 
	 * counter.setSalesRep_ID(getSalesRep_ID());
	 * 
	 * counter.save(get_TrxName());
	 * 
	 * 
	 * 
	 * // Update copied lines
	 * 
	 * final MEXMEInOutLine[] counterLines = counter.getLines(true);
	 * 
	 * for (int i = 0; i < counterLines.length; i++)
	 * 
	 * {
	 * 
	 * final MEXMEInOutLine counterLine = counterLines[i];
	 * 
	 * counterLine.setM_Warehouse_ID(counter.getM_Warehouse_ID());
	 * 
	 * counterLine.setM_Locator_ID(0);
	 * 
	 * counterLine.setM_Locator_ID(Env.ZERO);
	 * 
	 * //
	 * 
	 * counterLine.save(get_TrxName());
	 * 
	 * }
	 * 
	 * 
	 * 
	 * log.fine(counter.toString());
	 * 
	 * 
	 * 
	 * // Document Action
	 * 
	 * if (counterDT != null)
	 * 
	 * {
	 * 
	 * if (counterDT.getDocAction() != null)
	 * 
	 * {
	 * 
	 * counter.setDocAction(counterDT.getDocAction());
	 * 
	 * counter.processIt(counterDT.getDocAction());
	 * 
	 * counter.save(get_TrxName());
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return counter;
	 * 
	 * } // createCounterDoc
	 */

//	public boolean isCreatedFromActPacienteIndH() {
//		return m_IsFromActPacienteIndH;
//	}
//
//	public void setIsCreatedFromActPacienteIndH(boolean isFromActPacienteIndH) {
//		m_IsFromActPacienteIndH = isFromActPacienteIndH;
//	}
//
//	public MEXMEActPacienteIndH getActPacienteIndH() {
//		return m_ActPacienteIndH;
//	}
//
//	public void setActPacienteIndH(MEXMEActPacienteIndH actPacienteIndH) {
//		m_ActPacienteIndH = actPacienteIndH;
//	}

//	public String getM_processMsg() {
//		return m_processMsg;
//	}
//
//	public void setM_processMsg(String msg) {
//		m_processMsg = msg;
//	}
	/** @deprecated */
	public static String getS_ProcessMsg() {
		return s_processMsg;
	}

//	/**
//	 * Crea una nueva linea de pago a partir de otra (CANCELACION DE FACTURA
//	 * DIRECTA <<DEVOLPAGOACTION>>)
//	 * 
//	 * @param ctx
//	 *            contexto
//	 * @param C_Cash_ID
//	 *            El identificador de la caja abierta
//	 * @param trxName
//	 *            El nombre de la transaccion
//	 * @return la linea del pago
//	 */
//	private static MInOut copyFrom(MEXMEInOut from, Timestamp dateDoc,
//			boolean isSOTrx, boolean counter, String trxName, boolean setOrder)
//
//	{
//		final MInOut to = new MInOut(from.getCtx(), 0, null);
//		to.set_TrxName(trxName);
//		copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
//		// to.setM_InOut_ID(0);
//		to.set_ValueNoCheck("DocumentNo", null);
//		to.setDocStatus(DOCSTATUS_Drafted); // Draft
//		to.setDocAction(DOCACTION_Complete);
//		to.setC_DocType_ID(from.getC_DocType_ID());
//		to.setIsSOTrx(isSOTrx);
//		if (counter) {
//			to.setMovementType(isSOTrx ? MOVEMENTTYPE_CustomerShipment
//					: MOVEMENTTYPE_VendorReceipts);
//		}
//		to.setDateOrdered(dateDoc);
//		to.setDateAcct(dateDoc);
//		to.setMovementDate(dateDoc);
//		to.setDatePrinted(null);
//		to.setIsPrinted(false);
//		to.setDateReceived(null);
//		to.setNoPackages(0);
//		to.setShipDate(null);
//		to.setPickDate(null);
//		to.setIsInTransit(false);
//		to.setIsApproved(false);
//		to.setC_Invoice_ID(0);
//		to.setTrackingNo(null);
//		to.setIsInDispute(false);
//		to.setPosted(false);
//		to.setProcessed(false);
//		to.setC_Order_ID(0); // Overwritten by setOrder
//
//		if (counter) {
//			to.setC_Order_ID(0);
//			to.setRef_InOut_ID(from.getM_InOut_ID());
//			// Try to find Order/Invoice link
//			if (from.getC_Order_ID() != 0) {
//				final MOrder peer = new MOrder(from.getCtx(),
//						from.getC_Order_ID(), from.get_TrxName());
//				if (peer.getRef_Order_ID() != 0) {
//					to.setC_Order_ID(peer.getRef_Order_ID());
//				}
//			}
//			if (from.getC_Invoice_ID() != 0) {
//				final MInvoice peer = new MInvoice(from.getCtx(),
//						from.getC_Invoice_ID(), from.get_TrxName());
//				if (peer.getRef_Invoice_ID() != 0) {
//					to.setC_Invoice_ID(peer.getRef_Invoice_ID());
//				}
//			}
//		} else {
//			to.setRef_InOut_ID(0);
//			if (setOrder) {
//				to.setC_Order_ID(from.getC_Order_ID());
//			}
//		}
//		//
//		if (!to.save(trxName)) {
//			throw new IllegalStateException("Could not create Shipment");
//		}
//		if (counter) {
//			from.setRef_InOut_ID(to.getM_InOut_ID());
//		}
//		if (to.copyLinesFrom(from, counter, setOrder) == 0) {
//			throw new IllegalStateException("Could not create Shipment Lines");
//		}
//		return to;
//
//	} // copyFrom

	/**
	 * Copy Lines From other Shipment
	 * 
	 * @param otherShipment
	 *            shipment
	 * @param counter
	 *            set counter info
	 * @param setOrder
	 *            set order link
	 * @return number of lines copied
	 * 
	 *         public int copyLinesFrom(MEXMEInOut otherShipment, boolean
	 *         counter, boolean setOrder) { if (isProcessed() || isPosted() ||
	 *         otherShipment == null){ return 0; }
	 * 
	 *         final MEXMEInOutLine[] fromLines = otherShipment.getLines(false);
	 *         int count = 0;
	 * 
	 *         for (int i = 0; i < fromLines.length; i++) { final MEXMEInOutLine
	 *         line = new MEXMEInOutLine(this); final MEXMEInOutLine fromLine =
	 *         fromLines[i]; line.set_TrxName(get_TrxName()); if (counter) {//
	 *         header PO.copyValues(fromLine, line, getAD_Client_ID(),
	 *         getAD_Org_ID()); } else { PO.copyValues(fromLine, line,
	 *         fromLine.getAD_Client_ID(), fromLine.getAD_Org_ID()); }
	 *         line.setM_InOut_ID(getM_InOut_ID()); //
	 *         line.setM_InOutLine_ID(0); // new // Reset if (!setOrder){
	 *         line.setC_OrderLine_ID(0); }
	 * 
	 *         if (!counter){ line.setM_AttributeSetInstance_ID(0); } //
	 *         line.setS_ResourceAssignment_ID(0); line.setRef_InOutLine_ID(0);
	 *         line.setIsInvoiced(false); // line.setConfirmedQty(Env.ZERO);
	 *         line.setPickedQty(Env.ZERO); line.setScrappedQty(Env.ZERO);
	 *         line.setTargetQty(Env.ZERO); // Set Locator based on header
	 *         Warehouse if (getM_Warehouse_ID() !=
	 *         otherShipment.getM_Warehouse_ID()) { line.setM_Locator_ID(0);
	 *         line.setM_Locator_ID(Env.ZERO); } // if (counter) {
	 *         line.setRef_InOutLine_ID(fromLine.getM_InOutLine_ID()); if
	 *         (fromLine.getC_OrderLine_ID() != 0) { final MOrderLine peer = new
	 *         MOrderLine(getCtx(), fromLine.getC_OrderLine_ID(),
	 *         get_TrxName()); if (peer.getRef_OrderLine_ID() != 0){
	 *         line.setC_OrderLine_ID(peer.getRef_OrderLine_ID()); } } } //
	 *         line.setProcessed(false); if (line.save(get_TrxName())){ count++;
	 *         } // Cross Link if (counter) {
	 *         fromLine.setRef_InOutLine_ID(line.getM_InOutLine_ID());
	 *         fromLine.save(get_TrxName()); } } if (fromLines.length != count){
	 *         log.log(Level.SEVERE, "Line difference - From=" +
	 *         fromLines.length + " <> Saved=" + count); } return count; } //
	 *         copyLinesFrom
	 * 
	 * 
	 * 
	 *         /** Get Lines of Shipment
	 * @return lines
	 */
//	public MInOutLine[] getLines(boolean requery) {
//		if (m_lines != null && !requery) {
//			return m_lines;
//		}
//		final ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
//
//		final StringBuilder sql = new StringBuilder(
//				Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT * FROM M_InOutLine WHERE M_InOut_ID=? ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
//				MInOutLine.Table_Name));
//		sql.append(" ORDER BY Line ");
//
//		PreparedStatement pstmt = null;
//
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			pstmt.setInt(1, getM_InOut_ID());
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				list.add(new MInOutLine(getCtx(), rs, get_TrxName()));
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		//
//		m_lines = new MInOutLine[list.size()];
//		list.toArray(m_lines);
//
//		return m_lines;
//	} // getMInOutLines

//	/**
//	 * 
//	 * Get Lines of Shipment
//	 * 
//	 * @return lines
//	 */
//
//	public MInOutLine[] getLines() {
//		return getLines(false);
//	} // getLines

	/**
	 * Reverse Correction - same date
	 * 
	 * @return true if success
	 * 
	 *         public boolean reverseCorrectIt() { log.info(toString()); final
	 *         MDocType dt = MDocType.get(getCtx(), getC_DocType_ID()); if
	 *         (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType())) {
	 *         m_processMsg = "@PeriodClosed@"; return false; }
	 * 
	 *         // Reverse/Delete Matching /* if (!isSOTrx()) { MMatchInv[] mInv
	 *         = MMatchInv.getInOut(getCtx(), getM_InOut_ID(), get_TrxName());
	 *         for (int i = 0; i < mInv.length; i++) mInv[i].delete(true);
	 *         MMatchPO[] mPO = MMatchPO.getInOut(getCtx(), getM_InOut_ID(),
	 *         get_TrxName()); for (int i = 0; i < mPO.length; i++) { if
	 *         (mPO[i].getC_InvoiceLine_ID() == 0) mPO[i].delete(true); else {
	 *         mPO[i].setM_InOutLine_ID(0); mPO[i].save();
	 * 
	 *         } } }
	 */

	// Deep Copy
	/*
	 * final MEXMEInOut reversal = copyFrom (this, getMovementDate(), isSOTrx(),
	 * false, get_TrxName(), false);//false if (reversal == null) { m_processMsg
	 * = "Could not create Ship Reversal"; return false; }
	 * //reversal.setReversal(true);
	 * 
	 * // Reverse Line Qty final MEXMEInOutLine[] sLines = getLines(false);
	 * final MEXMEInOutLine[] rLines = reversal.getLines(false); for (int i = 0;
	 * i < rLines.length; i++) { final MEXMEInOutLine rLine = rLines[i];
	 * rLine.setQtyEntered(rLine.getQtyEntered().negate());
	 * rLine.setMovementQty(rLine.getMovementQty().negate());
	 * rLine.setM_AttributeSetInstance_ID
	 * (sLines[i].getM_AttributeSetInstance_ID()); if
	 * (!rLine.save(get_TrxName())) { m_processMsg =
	 * "Could not correct Ship Reversal Line"; return false; } // We need to
	 * copy MA if (rLine.getM_AttributeSetInstance_ID() == 0) { final
	 * MInOutLineMA mas[] = MInOutLineMA.get(getCtx(),
	 * sLines[i].getM_InOutLine_ID(), get_TrxName()); for (int j = 0; j <
	 * mas.length; j++) { final MInOutLineMA ma = new MInOutLineMA (rLine,
	 * mas[j].getM_AttributeSetInstance_ID(), mas[j].getMovementQty().negate());
	 * // if (!ma.save()) ma.save(); } } // De-Activate Asset final MAsset asset
	 * = MAsset.getFromShipment(getCtx(), sLines[i].getM_InOutLine_ID(),
	 * get_TrxName()); if (asset != null) { asset.setIsActive(false);
	 * asset.addDescription("(" + reversal.getDocumentNo() + " #" +
	 * rLine.getLine() + "<-)"); asset.save(); } }
	 * reversal.setC_Order_ID(getC_Order_ID()); reversal.addDescription("{->" +
	 * getDocumentNo() + ")"); // if
	 * (!reversal.processIt(DocAction.ACTION_Complete) ||
	 * !reversal.getDocStatus().equals(DocAction.STATUS_Completed)) {
	 * m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg(); return
	 * false; } reversal.closeIt(); reversal.setDocStatus(DOCSTATUS_Reversed);
	 * reversal.setDocAction(DOCACTION_None); reversal.save(get_TrxName()); //
	 * addDescription("(" + reversal.getDocumentNo() + "<-)");
	 * 
	 * m_processMsg = reversal.getDocumentNo(); setProcessed(true);
	 * setDocStatus(DOCSTATUS_Reversed); // may come from void
	 * setDocAction(DOCACTION_None); return true; } // reverseCorrectionIt
	 * 
	 * /**
	 * 
	 * Desde aplicacion de servicios
	 * 
	 * @param indH
	 * 
	 * @param movementDate
	 * 
	 * @param linesToDeliver
	 * 
	 * @param allAttributeInstances
	 * 
	 * @param minGuaranteeDate
	 * 
	 * @param complete
	 * 
	 * @param estServ
	 * 
	 * @param trxName
	 * 
	 * @return
	 */
//	public static MEXMEInOut createFromApServ(MEXMEActPacienteIndH indH,
//			Timestamp movementDate, Map<Integer, BigDecimal> linesToDeliver,
//			boolean allAttributeInstances, Timestamp minGuaranteeDate,
//			boolean complete, MEXMEEstServ estServ, String trxName) {
//
//		s_processMsg = null;
//
//		if (indH == null) {
//			throw new IllegalArgumentException("No IndicacionH");
//		}
//		// Create Meader
//		final MEXMEInOut retValue = new MEXMEInOut(indH, 0, movementDate);
//		retValue.setDocAction(complete ? DOCACTION_Complete : DOCACTION_Prepare);
//		retValue.setAD_OrgTrx_ID(indH.getAD_OrgTrx_ID());// Organizacion de
//															// quien solicita
//		retValue.setEXME_CtaPac_ID(indH.getEXME_CtaPac_ID());
//		if (retValue.getC_BPartner_Location_ID() <= 0) {
//			// throw new IllegalArgumentException("No BPartner Location");
//			s_processMsg = "No BPartner Location.";
//			return null;
//		}
//
//		// Create Line
//		if (retValue.get_ID() == 0) {// not saved yet
//			if (!retValue.save(trxName)) {
//				s_processMsg = "No se genero la salida del Almacen.";
//				return null;
//			}
//		}
//		// Check if we can create the lines
//		final MEXMEActPacienteInd[] aLines = indH.getLineas();
//		for (int i = 0; i < aLines.length; i++) {
//
//			BigDecimal totalQty = Env.ZERO;
//
//			// Stock Info
//			final boolean stocked = MProduct.isProductStocked(indH.getCtx(),
//					aLines[i].getM_Product_ID());
//			MStorage[] storage = null;
//
//			if (stocked) {
//
//				BigDecimal qty = null;
//				if (linesToDeliver != null) {
//					// ActPacienteIndView view = linesToDeliver.get(new
//					// Integer(aLines[i].getEXME_ActPacienteInd_ID()));
//					// qty = new BigDecimal(view.getQtyDelivered());
//
//					qty = aLines[i].getQtyDelivered();
//
//					if (qty == null || qty.compareTo(Env.ZERO) <= 0) {
//						continue; // Si no esta considerada para ser surtida no
//									// se genera linea en InOutLine.
//					}
//				}
//
//				if (qty == null) {
//					qty = aLines[i].getQtyOrdered().subtract(
//							aLines[i].getQtyDelivered());
//				}
//				// Combertimos a UOM de almacenamiento.
//				qty = MEXMEUOMConversion.convertProductFrom(indH.getCtx(),
//						aLines[i].getM_Product_ID(), aLines[i].getC_UOM_ID(),
//						qty, null);
//
//				// Nothing to deliver
//				if (qty == null || Env.ZERO.compareTo(qty) == 0) {
//					continue;
//				}
//
//				if (aLines[i].getM_AttributeSetInstance_ID() == 0) {
//					storage = MStorage.getWarehouse(indH.getCtx(),
//							indH.getM_Warehouse_ID(),
//							aLines[i].getM_Product_ID(), 0, 0,
//							allAttributeInstances, minGuaranteeDate, false,
//							trxName);
//				} else {
//					storage = MStorage.getWarehouse(indH.getCtx(),
//							aLines[i].getM_Warehouse_ID(),
//							aLines[i].getM_Product_ID(),
//							aLines[i].getM_AttributeSetInstance_ID(), 0,
//							allAttributeInstances, minGuaranteeDate, false,
//							trxName);
//				}
//
//				BigDecimal maxQty = Env.ZERO;
//
//				for (int ll = 0; ll < storage.length; ll++) {
//					maxQty = maxQty.add(storage[ll].getQtyOnHand());
//				}
//				if (DELIVERYRULE_Availability
//						.equals(MOrder.DELIVERYRULE_Availability)) {
//					if (maxQty.compareTo(qty) < 0) {
//						qty = maxQty;
//					}
//				}
//
//				// Create Line
//				if (retValue.get_ID() == 0) {// not saved yet
//					retValue.save(trxName);
//				}
//				// Create a line until qty is reached
//				for (int ll = 0; ll < storage.length; ll++) {
//
//					BigDecimal lineQty = storage[ll].getQtyOnHand();
//					if (lineQty.compareTo(qty) > 0) {
//						lineQty = qty;
//					}
//					final MEXMEInOutLine line = new MEXMEInOutLine(retValue);
//					line.setOrderLine(aLines[i], storage[ll].getM_Locator_ID());
//					line.setQty(lineQty); // Qty - En unidad de almacenamiento
//					// Qty - En unidad de venta.
//					BigDecimal qtyEntred = MEXMEUOMConversion.convertProductTo(
//							indH.getCtx(), aLines[i].getM_Product_ID(),
//							aLines[i].getC_UOM_ID(), lineQty, null);
//
//					if (qtyEntred == null || qtyEntred.compareTo(Env.ZERO) == 0) {
//						qtyEntred = line.getMovementQty();
//					}
//					line.setQtyEntered(qtyEntred);
//					line.setAD_OrgTrx_ID(aLines[i].getAD_OrgTrx_ID()); // Noelia:
//																		// guarda
//																		// en
//																		// salida
//																		// de
//																		// inventario
//																		// la
//																		// orgtrx
//																		// de
//																		// quien
//																		// surte
//
//					if (!line.save(trxName)) {
//						s_processMsg = "No se genero la salida del Almacen.";
//						return null;
//					}
//
//					totalQty = totalQty.add(lineQty);
//
//					if (aLines[i].getM_InOutLine_ID() == 0) {
//						aLines[i].setM_InOutLine_ID(line.getM_InOutLine_ID());
//					}
//
//					// Delivered everything ?
//					qty = qty.subtract(lineQty);
//
//					if (qty.compareTo(Env.ZERO) == 0) {
//						break;
//					}
//				}// storages
//
//			}// isStock
//				// Si es servicio.
//			else if (MProduct.PRODUCTTYPE_Service.equals(aLines[i].getProduct()
//					.getProductType())) {
//				// Los servicios no crean movimiento de inventario.
//				totalQty = aLines[i].getQtyDelivered();
//				final MEXMEInOutLine line = new MEXMEInOutLine(retValue);
//				line.setOrderLine(aLines[i], 0);
//				line.setQty(totalQty); // Qty - En unidad de almacenamiento
//				line.setQtyEntered(aLines[i].getQtyDelivered());
//				line.setEXME_ActPacienteInd_ID(aLines[i]
//						.getEXME_ActPacienteInd_ID());
//				line.setAD_OrgTrx_ID(aLines[i].getAD_OrgTrx_ID()); // Noelia:
//																	// guarda en
//																	// salida de
//																	// inventario
//																	// la orgtrx
//																	// de quien
//																	// surte
//				if (line.save(trxName)) {
//					if (aLines[i].getM_InOutLine_ID() == 0) {
//						aLines[i].setM_InOutLine_ID(line.getM_InOutLine_ID());
//					}
//				} else {
//					s_processMsg = "No se genero la salida del Almacen.";
//					return null;
//				}
//			}
//			totalQty = linesToDeliver
//					.get(aLines[i].getEXME_ActPacienteInd_ID());
//
//			// ActPacienteIndView view = linesToDeliver.get(new
//			// Integer(aLines[i].getEXME_ActPacienteInd_ID()));
//
//			aLines[i].setDateDelivered(DB.getTimestampForOrg(indH.getCtx()));
//			retValue.setEXME_ActPacienteIndH_ID(aLines[i]
//					.getEXME_ActPacienteIndH_ID());
//			BigDecimal qtyDelivered = MEXMEUOMConversion.convertProductTo(
//					indH.getCtx(), aLines[i].getM_Product_ID(),
//					aLines[i].getC_UOM_ID(), totalQty, null);
//
//			if (qtyDelivered == null || Env.ZERO.compareTo(qtyDelivered) == 0) {
//				qtyDelivered = totalQty;
//			}
//			aLines[i].setQtyDelivered(qtyDelivered);
//
//			if (!aLines[i].save(trxName)) {
//				s_processMsg = "No se actualizo la linea del cargo @EXME_ActPacienteInd@ = "
//						+ aLines[i].toString();
//				return null;
//			}
//
//		} // for all order lines
//
//		// No Lines saved
//		if (retValue.get_ID() == 0) {
//			return null;
//		}
//
//		retValue.setActPacienteIndH(indH);
//
//		return retValue;
//	} // createFrom

	/**
	 * Verificamos si la cuenta tiene al menos 1 linea
	 * 
	 * @return True si tiene al menos 1 linea, false si no.
	 */

	public static boolean getForCtaPacId(Properties ctx, int ctaPacId,
			String trxName) {

		// boolean retValue = true;
		// final StringBuffer sql = new
		// StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		// sql.append("SELECT M_InOut_ID FROM M_InOut ");
		// sql.append(" WHERE M_InOut.isActive = 'Y' AND M_InOut.EXME_CtaPac_ID = ? ");//.append(ctaPacId);
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		//
		// // PreparedStatement pstmt = null;
		// // ResultSet rs = null;
		//
		// try {
		//
		// // pstmt = DB.prepareStatement(sql.toString(), null);
		// // pstmt.setInt(1, ctaPacId);
		// // rs = pstmt.executeQuery();
		//
		// // if (!rs.next()){
		// // retValue = false;
		// // }
		// retValue = DB.getSQLValue(trxName, sql.toString(), ctaPacId) > 0;
		//
		// } catch (Exception e) {
		// s_log.log(Level.SEVERE, e.getMessage(), e);
		// throw e;
		// }
		// // finally {
		// // DB.close(rs, pstmt);
		// // rs = null;
		// // pstmt = null;
		// // }
		// return retValue;
		return new Query(ctx, X_M_InOut.Table_Name, " EXME_CtaPac_ID=? ", trxName)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setParameters(ctaPacId)//
				.firstId() > 0;
	}

	/**
	 * Order Constructor - create header only Se utiliza para crear el InOut a
	 * partir de una "Orden de venta" generada por la facturacion directa
	 * 
	 * @param order
	 *            order
	 * @param movementDate
	 *            optional movement date (default today)
	 * @param C_DocTypeShipment_ID
	 *            document type or 0
	 * 
	 *            public MEXMEInOut (MOrder order, int C_DocTypeShipment_ID,
	 *            Timestamp movementDate) { this (order.getCtx(), 0,
	 *            order.get_TrxName()); setClientOrg(order); setC_BPartner_ID
	 *            (order.getC_BPartner_ID()); setC_BPartner_Location_ID
	 *            (order.getC_BPartner_Location_ID()); // shipment address
	 *            setAD_User_ID(order.getAD_User_ID()); // setM_Warehouse_ID
	 *            (order.getM_Warehouse_ID()); setIsSOTrx (order.isSOTrx());
	 *            setMovementType (order.isSOTrx() ?
	 *            MOVEMENTTYPE_CustomerShipment : MOVEMENTTYPE_VendorReceipts);
	 *            if (C_DocTypeShipment_ID == 0){ C_DocTypeShipment_ID =
	 *            DB.getSQLValue(null,
	 *            "SELECT C_DocTypeShipment_ID FROM C_DocType WHERE C_DocType_ID=?"
	 *            , order.getC_DocType_ID()); } setC_DocType_ID
	 *            (C_DocTypeShipment_ID);
	 * 
	 *            // Default - Today if (movementDate != null){ setMovementDate
	 *            (movementDate); } setDateAcct (getMovementDate());
	 * 
	 *            // Copy from Order if(order.getC_Order_ID()>0){
	 *            setC_Order_ID(order.getC_Order_ID()); } setDeliveryRule
	 *            (order.getDeliveryRule()); setDeliveryViaRule
	 *            (order.getDeliveryViaRule());
	 *            setM_Shipper_ID(order.getM_Shipper_ID()); setFreightCostRule
	 *            (order.getFreightCostRule());
	 *            setFreightAmt(order.getFreightAmt());
	 *            setSalesRep_ID(order.getSalesRep_ID()); //
	 *            setC_Activity_ID(order.getC_Activity_ID());
	 *            setC_Campaign_ID(order.getC_Campaign_ID());
	 *            setC_Charge_ID(order.getC_Charge_ID());
	 *            setChargeAmt(order.getChargeAmt()); //
	 *            setC_Project_ID(order.getC_Project_ID());
	 *            setDateOrdered(order.getDateOrdered());
	 *            setDescription(order.getDescription());
	 *            setPOReference(order.getPOReference());
	 *            setSalesRep_ID(order.getSalesRep_ID());
	 *            setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());//De quien solicita
	 *            el Operador.. setUser1_ID(order.getUser1_ID());
	 *            setUser2_ID(order.getUser2_ID()); } // MInOut
	 */

	/**
	 * Complete Document
	 * 
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 * @deprecated
	 * 
	 *             public String completeItOrder(List<MEXMEOrderLine> lista) {
	 *             // Re-Check if (!m_justPrepared) { final String status =
	 *             prepareIt(); if
	 *             (!DocAction.STATUS_InProgress.equals(status)){ return status;
	 *             } } // Outstanding (not processed) Incoming Confirmations ?
	 *             final MInOutConfirm[] confirmations = getConfirmations(true);
	 *             for (int i = 0; i < confirmations.length; i++) { final
	 *             MInOutConfirm confirm = confirmations[i]; if
	 *             (!confirm.isProcessed()) { if
	 *             (MInOutConfirm.CONFIRMTYPE_CustomerConfirmation
	 *             .equals(confirm.getConfirmType())){ continue; } //
	 *             m_processMsg = "Open @M_InOutConfirm_ID@: " +
	 *             confirm.getConfirmTypeName() + " - " +
	 *             confirm.getDocumentNo(); return DocAction.STATUS_InProgress;
	 *             } }
	 * 
	 * 
	 *             // Implicit Approval if (!isApproved()){ approveIt(); }
	 *             log.info(toString()); final StringBuffer info = new
	 *             StringBuffer();
	 * 
	 *             // For all lines final MEXMEInOutLine[] lines =
	 *             getLines(false); for (int lineIndex = 0; lineIndex <
	 *             lines.length; lineIndex++) { final MEXMEInOutLine sLine =
	 *             lines[lineIndex]; final MProduct product =
	 *             sLine.getProduct();
	 * 
	 *             // Qty & Type final String MovementType = getMovementType();
	 *             BigDecimal Qty = sLine.getMovementQty(); if
	 *             (MovementType.charAt(1) == '-'){ // C- Customer Shipment - V-
	 *             Vendor Return Qty = Qty.negate(); } BigDecimal QtySO =
	 *             Env.ZERO; BigDecimal QtyPO = Env.ZERO;
	 * 
	 *             // Update Order Line MEXMEOrderLine oLine = null; //if
	 *             (sLine.getC_OrderLine_ID() != 0) //{ //oLine = new
	 *             MEXMEOrderLine (getCtx(), sLine.getC_OrderLine_ID(),
	 *             get_TrxName()); /*for (int x = 0; x < lista.size(); x++){
	 *             if(sLine.getM_InOutLine_ID()==
	 *             ((MEXMEOrderLine)lista.get(x)).getM_InOutLine_ID()){ oLine =
	 *             (MEXMEOrderLine)lista.get(x); } }
	 */

	/*
	 * List<MInOutLine> linesByLots = new ArrayList<MInOutLine>(); for (int i =
	 * 0; i < lista.size(); i++) {
	 * 
	 * linesByLots = ((MEXMEOrderLine)lista.get(i)).getLstSurtidoDet();
	 * 
	 * for (int j = 0; j < linesByLots.size(); j++) { if
	 * (linesByLots.get(j).getM_InOutLine_ID() == sLine.getM_InOutLine_ID()) {
	 * oLine = (MEXMEOrderLine)lista.get(i); } }
	 * 
	 * }
	 * 
	 * //Procesar la linea de MInOutLine que esta relacionada con la orden de
	 * venta: aaranda if (oLine==null){ continue; }
	 * 
	 * log.fine("OrderLine - Reserved=" + oLine.getQtyReserved() +
	 * ", Delivered=" + oLine.getQtyDelivered()); if (isSOTrx()){ QtySO =
	 * sLine.getMovementQty(); }else{ QtyPO = sLine.getMovementQty(); } //}
	 * 
	 * log.info("Line=" + sLine.getLine() + " - Qty=" + sLine.getMovementQty());
	 * 
	 * // Stock Movement - Counterpart MOrder.reserveStock if (product != null
	 * && product.isStocked() ) { log.fine("Material Transaction");
	 * MEXMETransaction mtrx = null; //Expert:Hassan final
	 * List<MEXMETransaction> mtrxs = new ArrayList<MEXMETransaction>();
	 * //Expert:Hassan - Para guardar la referencia de cada transaccion por
	 * SAcct // Reservation ASI - assume none int
	 * reservationAttributeSetInstance_ID = 0; //
	 * sLine.getM_AttributeSetInstance_ID(); if (oLine != null){
	 * reservationAttributeSetInstance_ID =
	 * oLine.getM_AttributeSetInstance_ID(); } // if
	 * (sLine.getM_AttributeSetInstance_ID() == 0) { final MInOutLineMA mas[] =
	 * MInOutLineMA.get(getCtx(), sLine.getM_InOutLine_ID(), get_TrxName()); for
	 * (int j = 0; j < mas.length; j++) { final MInOutLineMA ma = mas[j];
	 * BigDecimal QtyMA = ma.getMovementQty(); if (MovementType.charAt(1) ==
	 * '-') {// C- Customer Shipment - V- Vendor Return QtyMA = QtyMA.negate();
	 * } BigDecimal QtySOMA = Env.ZERO; BigDecimal QtyPOMA = Env.ZERO; //if
	 * (sLine.getC_OrderLine_ID() != 0) //{ if (isSOTrx()){ QtySOMA =
	 * ma.getMovementQty(); }else{ QtyPOMA = ma.getMovementQty(); } //} //
	 * Update Storage - see also VMatch.createMatchRecord if
	 * (!MStorage.add(getCtx(), getM_Warehouse_ID(), sLine.getM_Locator_ID(),
	 * sLine.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(),
	 * reservationAttributeSetInstance_ID, QtyMA, QtySOMA.negate(),
	 * QtyPOMA.negate(), get_TrxName())) { m_processMsg =
	 * "Cannot correct Inventory (MA)"; return DocAction.STATUS_Invalid; } /*
	 * Expert:Hassan - Registramos una tranzaccion por cada Esquema contable del
	 * Cliente (Bloque Modificado)
	 */
	/*
	 * final MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(),
	 * getAD_Client_ID()); for(int h = 0; h < acctss.length; h++){ final
	 * MAcctSchema as = acctss[h]; // Create Transaction
	 * 
	 * //Current Costs String costingMethod = as.getCostingMethod();
	 * 
	 * // Expert: #Post,Cost And Price MProductCategoryAcct pca =
	 * MProductCategoryAcct.getAcct( product, as); if
	 * (pca.getCostingMethodDefault() != null) costingMethod =
	 * pca.getCostingMethodDefault();
	 * 
	 * // Create Transaction mtrx = new MEXMETransaction (getCtx(), product,
	 * ma.getM_AttributeSetInstance_ID(), as, sLine.getAD_Org_ID(),
	 * costingMethod, QtyMA, 0, false, MovementType, sLine.getM_Locator_ID(),
	 * getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema
	 * contable
	 * 
	 * mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID()); if (!mtrx.save()) {
	 * m_processMsg = "Could not create Material Transaction (MA)"; return
	 * DocAction.STATUS_Invalid; } mtrxs.add(mtrx); }/* Expert:Hassan - Fin del
	 * Bloque
	 */
	/*
	 * } } // sLine.getM_AttributeSetInstance_ID() != 0 if (mtrx == null) { //
	 * Fallback: Update Storage - see also VMatch.createMatchRecord if
	 * (!MStorage.add(getCtx(), getM_Warehouse_ID(), sLine.getM_Locator_ID(),
	 * sLine.getM_Product_ID(), sLine.getM_AttributeSetInstance_ID(),
	 * reservationAttributeSetInstance_ID, Qty, QtySO.negate(), QtyPO.negate(),
	 * get_TrxName())) { m_processMsg = "Cannot correct Inventory"; return
	 * DocAction.STATUS_Invalid; } /* Expert:Hassan - Registramos una
	 * tranzaccion por cada Esquema contable del Cliente (Bloque Modificado)
	 */
	/*
	 * final MAcctSchema[] acctss = MAcctSchema.getClientAcctSchema(getCtx(),
	 * getAD_Client_ID()); for(int h = 0; h < acctss.length; h++){ final
	 * MAcctSchema as = acctss[h]; // FallBack: Create Transaction
	 * 
	 * // Current Costs String costingMethod = as.getCostingMethod(); // Expert:
	 * #Post,Cost And Price MProductCategoryAcct pca =
	 * MProductCategoryAcct.getAcct( product, as); if
	 * (pca.getCostingMethodDefault() != null) costingMethod =
	 * pca.getCostingMethodDefault();
	 * 
	 * // Create Transaction mtrx = new MEXMETransaction (getCtx(), product,
	 * sLine.getM_AttributeSetInstance_ID(), as, sLine.getAD_Org_ID(),
	 * costingMethod, Qty, 0, false, MovementType, sLine.getM_Locator_ID(),
	 * getMovementDate(), get_TrxName());//Expert:Hassan - Se pasa el esquema
	 * contable
	 * 
	 * 
	 * mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID()); if (!mtrx.save()) {
	 * m_processMsg = "Could not create Material Transaction"; return
	 * DocAction.STATUS_Invalid; } }/* Expert:Hassan - Fin del Bloque
	 */
	/*
	 * } } // stock movement
	 * 
	 * // Correct Order Line if (product != null && oLine != null) { // other in
	 * VMatch.createMatchRecord
	 * oLine.setQtyReserved(oLine.getQtyReserved().subtract
	 * (sLine.getMovementQty())); }
	 * 
	 * // Update Sales Order Line if (oLine != null) { if (isSOTrx() // PO is
	 * done by Matching || sLine.getM_Product_ID() == 0) // PO Charges, empty
	 * lines { if (isSOTrx()){
	 * oLine.setQtyDelivered(oLine.getQtyDelivered().subtract(Qty)); }else{
	 * oLine.setQtyDelivered(oLine.getQtyDelivered().add(Qty)); }
	 * oLine.setDateDelivered(getMovementDate()); // overwrite=last } /*@ FIXME
	 * Por que esta comentado donde se guardan las lineas de la orden
	 */
	// if (!oLine.save())
	// {
	// m_processMsg = "Could not update Order Line";
	// return DocAction.STATUS_Invalid;
	// }
	// else
	// log.fine("OrderLine -> Reserved=" + oLine.getQtyReserved()
	// + ", Delivered=" + oLine.getQtyReserved());
	/*
	 * }
	 * 
	 * // Create Asset for SO if (product != null && isSOTrx() &&
	 * product.isCreateAsset() && sLine.getMovementQty().signum() > 0 &&
	 * !isReversal()) { log.fine("Asset"); info.append("@A_Asset_ID@: "); int
	 * noAssets = sLine.getMovementQty().intValue(); if
	 * (!product.isOneAssetPerUOM()){ noAssets = 1; } for (int i = 0; i <
	 * noAssets; i++) { if (i > 0){ info.append(" - "); } int deliveryCount =
	 * i+1; if (!product.isOneAssetPerUOM()){ deliveryCount = 0; } final MAsset
	 * asset = new MAsset (this, sLine, deliveryCount); if
	 * (!asset.save(get_TrxName())) { m_processMsg = "Could not create Asset";
	 * return DocAction.STATUS_Invalid; } info.append(asset.getValue()); } } //
	 * Asset } // for all lines
	 * 
	 * // Counter Documents final MEXMEInOut counter = createCounterDoc(); if
	 * (counter != null){
	 * info.append(" - @CounterDoc@: @M_InOut_ID@=").append(counter
	 * .getDocumentNo()); } // User Validation final String valid =
	 * ModelValidationEngine.get().fireDocValidate(this,
	 * ModelValidator.TIMING_AFTER_COMPLETE); if (valid != null) { m_processMsg
	 * = valid; return DocAction.STATUS_Invalid; }
	 * 
	 * m_processMsg = info.toString(); setProcessed(true);
	 * setDocAction(DOCACTION_Close); return DocAction.STATUS_Completed; }
	 */

//	/**
//	 * Get Process Message
//	 * 
//	 * @return clear text error message
//	 */
//	public String getProcessMsg() {
//		return m_processMsg;
//	} // getProcessMsg

	/**
	 * Crea la salida de inventario apartir de cargos a cuenta paciente
	 * 
	 * @param ctx
	 * @param ctapac
	 * @param M_Warehouse_ID
	 * @param lines
	 * @param trxName
	 * @return
	 */
	public static MInOut salidaDesdeCargos(Properties ctx,
			MEXMECtaPac ctapac, int M_Warehouse_ID, MCtaPacDet[] lines,
			String trxName) {

		// Generar el embarque
		MInOut m_InOut = MEXMEInOut.createFrom(ctx, ctapac, lines,
				new MEXMEEstServ(ctx, ctapac.getEXME_EstServ_ID(), null),
				M_Warehouse_ID, new Timestamp(System.currentTimeMillis()),
				trxName);

		if (m_InOut == null) {
			// log.log(Level.SEVERE, "error.cargosdiarios",
			// MEXMEInOut.getS_ProcessMsg());
			return null;
		}

		String status = m_InOut.prepareIt(); // Validar el registro de M_InOut
		if (!DocAction.STATUS_InProgress.equals(status)) {
			if ("@NoLines@".equals(MEXMEInOut.getS_ProcessMsg())) {
				// log.log(Level.SEVERE, "error.cargosDiarios.noLineas",
				// MEXMEInOut.getS_ProcessMsg());
			} else {
				// log.log(Level.SEVERE, "error.cargosdiarios",
				// MEXMEInOut.getS_ProcessMsg());
			}
			return null;
		}

		status = m_InOut.completeIt(); // Guardar el registro. Solo est�
										// pendiente el commit.
		m_InOut.setDocStatus(status);

		if (!DocAction.STATUS_Completed.equals(status)) {
			// log.log(Level.SEVERE, "error.cargosdiarios",
			// MEXMEInOut.getS_ProcessMsg());
			return null;
		}

		if (!m_InOut.save(trxName)) {
			// log.log(Level.SEVERE, "error.cargosdiarios",
			// MEXMEInOut.getS_ProcessMsg());
			return null;
		}

		return m_InOut;
	}

//	/**
//	 * 
//	 * @param ctx
//	 * @param C_Invoice_ID
//	 * @param trxName
//	 * @return
//	 */
//	public String entradaInventarioPorCancelacion(final Properties ctx,
//			final String trxName) {
//		String errores = "";
//		final Timestamp now = new Timestamp(System.currentTimeMillis());
//		// Afectar inventario
//		// Traer el embarque relacionado a la factura
//
//		// crear una copia a partir de original
//		MInOut devol = null;
//		try {
//			devol = MEXMEInOut.copyFrom(this, now, true, true, trxName, false);
//		} catch (Exception e) {
//			return "error.caja.inout.copia";
//		}
//		// Tipo de movimiento Entrada
//		devol.setMovementType(MInOut.MOVEMENTTYPE_CustomerReturns);
//		// Guardamos el Embarque.
//		if (!devol.save(trxName)) {
//			return "error.factDirecta.facturar.noSaveInOut";
//		}
//
//		// Completamos los InOut.
//		String status = devol.completeIt();
//		devol.setDocStatus(status);
//		if (!status.equals(DocAction.STATUS_Completed)) {
//			// No se completo el Embarque.
//			return "error.factDirecta.facturar.noInOut";
//		} else {
//			if (devol.getDocStatus().equals(DocAction.STATUS_Completed))
//				devol.setDocAction(DocAction.ACTION_Close);
//			if (!devol.save(trxName)) {
//				return "error.factDirecta.facturar.noSaveInOut";
//			}
//		}
//		return errores;
//	}

	/**
	 * Obtenemos el embarque a partir de una factura (CANCELACION DE FACTURA
	 * DIRECTA <<DEVOLPAGOACTION>>)
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param C_Invoice_ID
	 *            El identificador de la factura
	 * @param trxName
	 *            El nombre de la transaccion
	 * @return Un objeto de tipo MEXMEInvoice
	 */
	public static List<MInOut> getInOutOfInvoice(Properties ctx,
			int C_InvoiceID, String trxName) {
		List<MInOut> inOut = new ArrayList<MInOut>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(" SELECT *  ")
					.append(" FROM M_InOut ")
					.append(" WHERE M_InOut.IsActive = 'Y' ")
					.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
							"M_InOut"))
					.append(" AND M_InOut.C_Invoice_ID = ? ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, C_InvoiceID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				inOut.add(new MInOut(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return inOut;
	}
}