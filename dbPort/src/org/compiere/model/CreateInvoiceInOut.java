package org.compiere.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CustomError;
import org.compiere.util.Utilerias;

public class CreateInvoiceInOut {

	/** Shipment */
	private static int p_M_InOut_ID = 0;
	/** Price List Version */
	//private static int p_M_PriceList_ID = 0;
	/* Document No */
	private static String p_InvoiceDocumentNo = null;

	public static List<CustomError> errores = null;

	public static String create(Properties ctx, int pMInOutID,
			int pMPriceListID, String pInvoiceDocumentNo, boolean isWeb) {

		p_M_InOut_ID = pMInOutID;
		//p_M_PriceList_ID = pMPriceListID;
		p_InvoiceDocumentNo = pInvoiceDocumentNo;
		errores = new ArrayList<CustomError>();
		Object[] param = null;

		if (p_M_InOut_ID == 0) {
			throw new IllegalArgumentException("No Shipment");
		}
		
		//
		MInOut ship = new MInOut(ctx, p_M_InOut_ID, null);
		MInvoice invoice = new MInvoice(ship, null);
		
		if (ship.get_ID() == 0){
			if (isWeb) {
				CustomError error = new CustomError(
						"error.rm.envio", param);
				errores.add(error);
			} else {
				throw new IllegalArgumentException(Utilerias.getAppMsg(ctx, "msj.EnvioNoEncontrado"));
			}
		} else if (!MInOut.DOCSTATUS_Completed.equals(ship.getDocStatus())){
			if (isWeb) {
				CustomError error = new CustomError(
						"error.rm.completado", param);
				errores.add(error);
			} else {
				throw new IllegalArgumentException(Utilerias.getAppMsg(ctx, "msj.ElEstatusDelEnvioNoEstaCompletado"));
			}
		} else{
			
			//get the document type
			MDocType dt = null;
			MDocType[] dts = 
				MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_APInvoice);
			
			for(int i = 0; i < dts.length; i++) {
				if(dts[i].getAD_Org_ID() == ship.getAD_Org_ID() && !dts[i].isSOTrx()) {
					dt = dts[i];
					break;
				}
			}
			
			if(dt != null) {
				invoice.setC_DocTypeTarget_ID(dt.getC_DocType_ID());
			}
			
			/*if (p_M_PriceList_ID != 0) {
				invoice.setM_PriceList_ID(p_M_PriceList_ID);
			}*/
			
			if (p_InvoiceDocumentNo != null && p_InvoiceDocumentNo.length() > 0) {
				invoice.setDocumentNo(p_InvoiceDocumentNo);
			}

			if (!invoice.save()){
				if (isWeb) {
					CustomError error = new CustomError(
							"error.loteExistente", param);
					errores.add(error);
				} else {
					String msg = MedsysException.getMessageFromLogger();
					if (StringUtils.isNotEmpty(msg)) {
						if ("UnknownError".equals(msg)) {
							throw new IllegalArgumentException(Utilerias.getAppMsg(ctx, "factExtension.error.extNoSave"));
						} else {
							throw new IllegalArgumentException(msg);
						}
					} else {
						throw new IllegalArgumentException(Utilerias.getAppMsg(ctx, "factExtension.error.extNoSave"));
					}
				}
			} else{
				ship.setC_Invoice_ID(invoice.getC_Invoice_ID());
				ship.save();
				MInOutLine[] shipLines = ship.getLines(false);
				for (int i = 0; i < shipLines.length; i++) {
					MInOutLine sLine = shipLines[i];
					MInvoiceLine line = new MInvoiceLine(invoice);
					line.setMaterialReceiptValues(sLine);
					line.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
//					line.setQtyEntered(sLine.getQtyEntered());
//					line.setQtyInvoiced(sLine.getMovementQty());
//					//rsolorzano
					//cambios para unidad de medida
//					line.setQtyEntered_Vol(sLine.getQtyEntered_Vol());
//					line.setQtyInvoiced_Vol(sLine.getMovementQty_Vol());
//					line.setShipLine(sLine);
//					if(line.getC_UOM_ID() != line.getC_UOMVolume_ID()){
//						BigDecimal net = line.getPriceActual_Vol().multiply(line.getQtyInvoiced_Vol());
//							net = net.setScale(line.getPrecision(), BigDecimal.ROUND_HALF_UP);
//							line.setLineNetAmt(net);
//						BigDecimal total = line.getLineNetAmt().add(line.getTaxAmt());
//							total = total.setScale(line.getPrecision(), BigDecimal.ROUND_HALF_UP);
//							line.setLineTotalAmt(total);
//					}
					if (!line.save()){
						if (isWeb) {
							CustomError error = new CustomError(
									"error.rm.recibo.linea", param);
							errores.add(error);
						} else {
							throw new IllegalArgumentException(
							"Cannot save Invoice Line");
						}
					}
				}
			}
		}
		return invoice.getDocumentNo();

	}
	//SEPTIEMBRE 23 SE AGREGARON IF-ELSE PARA ERRORES EN WEB
	//PUEDE QUE ESTO MARQUE ERRORES EN OTRO PROCESO QUE MANDE A LLAMAR ESTA CLASE.
}