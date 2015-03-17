package org.compiere.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CustomError;
import org.compiere.util.Env;

public class CreateInOutInvoice {

	/** Warehouse */
	private static int p_M_Warehouse_ID = 0;
	/** Invoice */
	private static int p_C_Invoice_ID = 0;

	public static List<CustomError> errores = null;

	public static String create(Properties ctx, int pCInvoiceID,
			int pMWarehouseID, boolean isWeb) {

		p_C_Invoice_ID = pCInvoiceID;
		p_M_Warehouse_ID = pMWarehouseID;
		errores = new ArrayList<CustomError>();
		Object[] param = null;

		if (p_C_Invoice_ID == 0) {
			if (isWeb) {
				CustomError error = new CustomError("error.layout.noFactura", param);
				errores.add(error);
			} else {
				throw new IllegalArgumentException("@NotFound@ @C_Invoice_ID@");
			}
		}
		if (p_M_Warehouse_ID == 0) {
			if (isWeb) {
				CustomError error = new CustomError("msg.err.loteProd.nowarehouse", param);
				errores.add(error);
			} else {
				throw new IllegalArgumentException("@NotFound@ @M_Warehouse_ID@");
			}
		}//
		MInvoice invoice = new MInvoice(ctx, p_C_Invoice_ID, null);
		if (invoice.get_ID() == 0) {
			if (isWeb) {
				CustomError error = new CustomError("error.layout.noFactura", param);
				errores.add(error);
			} else {
				throw new IllegalArgumentException("@NotFound@ @C_Invoice_ID@");
			}
		}
		if (!MInvoice.DOCSTATUS_Completed.equals(invoice.getDocStatus())) {
			if (isWeb) {
				CustomError error = new CustomError("error.rm.completado", param);
				errores.add(error);
			} else {
				throw new IllegalArgumentException("@InvoiceCreateDocNotCompleted@");
			}
		}//
		MInOut ship = new MInOut(invoice, 0, null, p_M_Warehouse_ID);
		if (!ship.save()) {
			if (isWeb) {
				CustomError error = new CustomError("error.rm.almacen", param);
				errores.add(error);
			} else {
				throw new IllegalArgumentException("@SaveError@ Receipt");
			}
		}
		//
		MInvoiceLine[] invoiceLines = invoice.getLines(false);
		for (int i = 0; i < invoiceLines.length; i++) {
			
			MInvoiceLine invoiceLine = invoiceLines[i];
			MInOutLine sLine = new MInOutLine(ship);
			
			sLine.setInvoiceLine(invoiceLine, 0, // Locator
				invoice.isSOTrx() ? invoiceLine.getQtyInvoiced(): Env.ZERO);
			
			sLine.setQtyEntered(invoiceLine.getQtyEntered());
			sLine.setMovementQty(invoiceLine.getQtyInvoiced());
			
			//rsolorzano
			//cambios para unidades de medida
			sLine.setQtyEntered_Vol(invoiceLine.getQtyEntered_Vol());
			sLine.setMovementQty_Vol(invoiceLine.getQtyInvoiced_Vol());
			sLine.setPriceActual(invoiceLine.getPriceActual());
			sLine.setPriceActual_Vol(invoiceLine.getPriceActual_Vol());
			
			if (invoice.isCreditMemo()) {
				sLine.setQtyEntered(sLine.getQtyEntered().negate());
				sLine.setMovementQty(sLine.getMovementQty().negate());
				
				sLine.setQtyEntered_Vol(invoiceLine.getQtyEntered_Vol().negate());
				sLine.setMovementQty_Vol(invoiceLine.getQtyInvoiced_Vol().negate());
				
			}
			
			if (!sLine.save()) {
				if (isWeb) {
					CustomError error = new CustomError("error.rm.recepcion.linea", param);
					errores.add(error);
				} else {
					throw new IllegalArgumentException("@SaveError@ @M_InOutLine_ID@");
				}
			}
			//
			invoiceLine.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
			if (!invoiceLine.save()) {
				if (isWeb) {
					CustomError error = new CustomError("error.rm.recibo.linea", param);
					errores.add(error);
				} else {
					throw new IllegalArgumentException("@SaveError@ @C_InvoiceLine_ID@");
				}
			}
		}

		return ship.getDocumentNo();
	}

}
