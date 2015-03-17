package org.compiere.model.bpm;

import java.sql.SQLException;
import java.util.Properties;

/**
 * El medico solicita un medicamento en unidosis el farmaceutico cambie el
 * producto generico por el adeucuado el producto debe estar en el charge master
 * la salida de inventario en pastillas la enfermera confirma en unidosis el
 * sistema ingresa el stock a su almacen en unidosis la enfermera aplica en emar
 * en unidosis
 * 
 * @author twry
 * 
 */
public class TraspasosUnidosis extends Traspasos {

	public TraspasosUnidosis(Properties pCtx, int movementID)
			throws SQLException {
		super(pCtx, movementID);
	}
//
//	/**
//	 * Solicitud y surtido de productos para unidosis
//	 * 
//	 * @param ctx
//	 * @param prescripciones
//	 * @param trxName
//	 * @return
//	 * @throws SQLException 
//	 */
//	public static List<String> solicitudSurtido(final Properties ctx,
//			final List<MEXMEPrescRXDet> prescripciones, final int almacenLogId, final String trxName) throws SQLException {
//		List<String> lstError = new ArrayList<String>();
//		TraspasosUnidosis process = new TraspasosUnidosis(ctx, 0);
//		// Convertir la prescripcion en solicitud de producto
//		final List<MMovementLine> movementLines = new ArrayList<MMovementLine>();
//		for (int i = 0; i < prescripciones.size(); i++) {
//
//			if (prescripciones.get(i).getM_Product_ID() <= 0
//					|| prescripciones.get(i).getQuantity()
//					.compareTo(Env.ZERO) <= 0) {
//				return lstError;
//			}
//
//			MMovementLine line = new MMovementLine(ctx, 0, trxName);
//			PO.copyValues(prescripciones.get(i), line);
//			final MProduct mProduct = new MProduct(ctx, prescripciones.get(i).getM_Product_ID(), null);
//			if(mProduct!=null && mProduct.getM_Product_ID()>0){
//				if(mProduct.getProdOrg()!=null && mProduct.getProdOrg().getAD_Org_ID()>0){
//					line.setUom(mProduct.getUom());
//					line.setM_Product_ID(mProduct.getM_Product_ID());
//					line.setM_AttributeSetInstance_ID(0);
//					line.setM_AttributeSetInstanceTo_ID(0);
//					line.setOriginalQty(prescripciones.get(i).getQtyPlan());
//					line.setTargetQty(prescripciones.get(i).getQtyPlan());
//					line.setC_UOM_ID(mProduct.getC_UOM_ID());
//					line.setC_UOMVolume_ID (mProduct.getC_UOMVolume_ID());
//					line.setOp_Uom(mProduct.getC_UOM_ID());
//					movementLines.add(line);
//				} else {
//					line = null;
//				}
//			}
//		}
//
//
//		return process.crearSolicitudSurtido(ctx, movementLines, almacenLogId, trxName);
//	}		
//
//	/**
//	 * 
//	 * @param ctx
//	 * @param movementLines
//	 * @param almacenLogId
//	 * @param trxName
//	 * @return
//	 */
//	protected List<String> crearSolicitudSurtido(final Properties ctx,
//			final List<MMovementLine> movementLines, final int almacenLogId, final String trxName){
//		List<String> lstError = new ArrayList<String>();
////		try {
////
////			// almacen de surtido el que tiene el farmaceutico
////			int reorderWHId = Env.getM_Warehouse_ID(ctx);
////
////			// Encabezado de solicitud de producto
//////			MMovement solicitud = TraspasosUnidosis.guardarSolicitud(ctx,
//////					reorderWHId, almacenLogId, // almacen surte solicita
//////					0,    // ctapac
//////					null, // descripcion
//////					movementLines, X_M_Movement.PRIORITYRULE_Urgent, // priorityRule
//////					0,    // progQuiroId
//////					false,// isDevol
//////					null);
////
////			// Surtido de producto por la misma cantidad que la solicitud
//////			lstError = Traspasos.complete(ctx, solicitud, solicitud.getLines(),
//////					trxName);
////
////		} catch (ExpertException e) {
//////			log.log(Level.SEVERE, "-- solicitudSurtido : ", e);
////		}
//
//		return lstError;
//	}
}
