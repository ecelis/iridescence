package org.compiere.model.billing;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocation;
import org.compiere.model.PO;
import org.compiere.model.X_C_Invoice;
import org.compiere.model.X_HL7_MessageConf;
import org.compiere.util.CLogger;

/**
 * 
 * @author expert
 *
 */
public class BillingMultiple {
	
	private transient final Properties ctx;
	private transient List<MInvoice> lstInvoice;
	private transient String trxName;
	private transient int bPartnerId;
	private transient List<MInvoiceLine> lstInvoiceLine = new ArrayList<MInvoiceLine>();
	private transient MInvoice newInvoice;
	
	/**
	 * Billing Multiple
	 * @param pctx
	 */
	public BillingMultiple (final Properties pctx){
		this.ctx = pctx;
	}
	
	/**
	 * invoice
	 * @param pctx
	 * @param pBPartnerId
	 * @param lstPacientes
	 * @param pTrxName
	 * @return
	 */
	public boolean process (final int pBPartnerId, final List<MInvoice> plstInvoice, final String pTrxName){
		this.lstInvoice = plstInvoice;
		this.trxName = pTrxName;
		this.bPartnerId = pBPartnerId;
		
		if (!lstInvoice.isEmpty() && bPartnerId > 0) {
		
			newInvoice = createMasterInvoice();
			if (newInvoice != null && newInvoice.save(trxName)) {
				//
				copyingNotes();
				
				// 
				newInvoice.setDocStatus(newInvoice.completeIt());
			} else {
				CLogger.get().severe("Invoice Not saved for bPartnerId: " + bPartnerId);
				throw new MedsysException("Invoice not saved");
			}
		}
		
		return newInvoice!=null && X_C_Invoice.DOCSTATUS_Completed.equalsIgnoreCase(newInvoice.getDocStatus());
	}

	/**
	 * create Master Invoice
	 * 
	 * @param ctx
	 * @param charges
	 * @param cbPartnerID
	 * @param trxName
	 * @return
	 */
	private MInvoice createMasterInvoice() {
		final MInvoice invoice = new MInvoice(ctx, 0, trxName);
		invoice.setIsMultiple(true);
		invoice.setBackoffice(false);
		invoice.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
		invoice.setDateOrdered(new Timestamp(System.currentTimeMillis()));
		// doctype
		final MDocType[] doctype = MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_ARInvoice);
		if (doctype != null && doctype.length > 0) {
			invoice.setC_DocType_ID(doctype[0].get_ID());
		}

		// socio
		final MBPartner company = new MBPartner(ctx, bPartnerId, trxName);
		invoice.setC_BPartner_ID(company.get_ID());
		invoice.setC_BPartner_Location_ID(company.getPrimaryC_BPartner_Location_ID());
		invoice.setConfType(X_HL7_MessageConf.CONFTYPE_InstitutionalClaim);
		
		// Guardar los datos de la facturacion, a partir del socio de negocios
		invoice.setDescription(company.getName());
		// Reemplazar los guiones del RFC, esto para facturacion electronica
		invoice.setPOReference(company.getTaxID().replaceAll("-", ""));
		
		MBPartnerLocation bpLocation = new MBPartnerLocation(ctx, company.getPrimaryC_BPartner_Location_ID(), trxName);
		MLocation location = new MLocation(ctx, bpLocation.getC_Location_ID(), trxName);
		invoice.setAddress1(location.getAddress1());
		invoice.setAddress2(location.getAddress2());
		invoice.setAddress3(location.getAddress3());
		invoice.setCity(location.getCity());
		invoice.setPostal(location.getPostal());
		invoice.setC_Country_ID(location.getC_Country_ID());
		invoice.setC_Region_ID(location.getC_Region_ID());
		if (location.getNumExt() != null)
			invoice.setNumExt(location.getNumExt());
		if (location.getNumIn() != null)
			invoice.setNumIn(location.getNumIn());

		invoice.setEXME_TownCouncil_ID(location.getEXME_TownCouncil_ID());
		
		return invoice;
	}
	
	/**
	 * copying Notes
	 * @param invoiceMltp
	 * @param lstInvoice
	 */
	private void copyingNotes(){
		for (int i = 0; i < lstInvoice.size(); i++) {
			copyValuesLines(lstInvoice.get(i).getLines());
			lstInvoice.get(i).setMultiple_ID(newInvoice.getC_Invoice_ID());
			lstInvoice.get(i).save(trxName);
		}
	}
	
	/**
	 * copy Values Lines
	 * @param invoiceMltp
	 * @param arrInvoiceLine
	 */
	private void copyValuesLines(final MInvoiceLine[] arrInvoiceLine){
		for (int i = 0; i < arrInvoiceLine.length; i++) {
			final MInvoiceLine invoiceLine = line(arrInvoiceLine[i]);
			if(invoiceLine.save(trxName)){
				lstInvoiceLine.add(invoiceLine);
			}
		}
	}
	
	/**
	 * line
	 * @param invoiceMltp
	 * @param linePre
	 * @return
	 */
	private MInvoiceLine line(final MInvoiceLine linePre){
		final MInvoiceLine invoiceLine = new MInvoiceLine(ctx, 0, trxName);
		PO.copyValues(linePre, invoiceLine);
		invoiceLine.setC_Invoice_ID(newInvoice.getC_Invoice_ID());
		invoiceLine.setInvoice(newInvoice);
		return invoiceLine;
	}
	
	public List<MInvoiceLine> getLstInvoiceLine() {
		return lstInvoiceLine;
	}

	public void setLstInvoiceLine(final List<MInvoiceLine> lstInvoiceLine) {
		this.lstInvoiceLine = lstInvoiceLine;
	}

	public MInvoice getNewInvoice() {
		return newInvoice;
	}

	public void setNewInvoice(final MInvoice newInvoice) {
		this.newInvoice = newInvoice;
	}
}
