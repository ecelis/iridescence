/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.acct.Doc;
import org.compiere.acct.Doc_InOut;
import org.compiere.model.bean.TransferBean;
import org.compiere.model.bpm.Inventariado;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Shipment Model
 *
 * @author Jorg Janke
 * @version $Id: MInOut.java,v 1.13 2006/09/05 18:24:13 mrojas Exp $
 */
public class MInOut extends X_M_InOut implements DocAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 7904086932369612799L;
	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MInOut.class);
	/** almacen */
	private MWarehouse warehouse = null;
	/** Tipo de documento */
	private MDocType docType = null;
	/** Nombre del Tipo de Documento MM Shipment */
	protected final static String MM_SHIPMENT = "MM SHIPMENT";
	/** Lines */
	private MInOutLine[] m_lines = null;
	/** Confirmations */
	private MInOutConfirm[] m_confirms = null;
	/** BPartner */
	private MBPartner m_partner = null;
	/** Reversal Flag */
	private boolean m_reversal = false;
	// Merge ADempiere trunk_rev14653
	/* Save array of documents to process AFTER completing this one */
	private final ArrayList<PO> docsPostProcess = new ArrayList<PO>();
	/** */
	private boolean m_IsFromActPacienteIndH = false;
	/** */
//	private MEXMEActPacienteIndH m_ActPacienteIndH = null;
	private MDocType mDocType = null;

	/** condicion para la recepcion de material con el tipo de documento */
	public static String getWhereMaterialReceipt(){
		return "  IsSOTrx = 'N' AND DocSubTypeSO IS NULL AND C_DocType.DocBaseType IN('" + X_C_DocType.DOCBASETYPE_MaterialReceipt + "') ";
	}
	/** condicion para la devolución a proveedor con el tipo de documento */
	public static String getWhereMaterialReturn(){
		return "  IsSOTrx = 'N' AND DocSubTypeSO = 'RT'  AND C_DocType.DocBaseType IN('" + X_C_DocType.DOCBASETYPE_MaterialReceipt + "') ";
	}

	/**
	 *
	 * @param ctx
	 * @param DocBaseType
	 * @param trxName
	 * @return
	 */
	public static int getFromDocBaseType(Properties ctx, String trxName) {
		String sql = MInOut.getWhereMaterialReceipt();
		MDocType mDoc = new Query(ctx, X_C_DocType.Table_Name,sql, trxName)
				.setOnlyActiveRecords(true)
				.addAccessLevelSQL(true)
				.first();
		return mDoc.getC_DocType_ID();
	}

	/**
	 * Create new Shipment by copying
	 *
	 * @param from
	 *            shipment
	 * @param dateDoc
	 *            date of the document date
	 * @param C_DocType_ID
	 *            doc type
	 * @param isSOTrx
	 *            sales order
	 * @param counter
	 *            create counter links
	 * @param trxName
	 *            trx
	 * @param setOrder
	 *            set the order link
	 * @return Shipment
	 */
	public static MInOut copyFrom(final MInOut from
			, final Timestamp dateDoc
			, final int C_DocType_ID
			, final boolean isSOTrx
			, final boolean counter
			, final String trxName
			, final boolean setOrder
			, final MInvoice mNota) {

		final MInOut minOutTo = new MInOut(from.getCtx(), 0, null);
		minOutTo.set_TrxName(trxName);
		copyValues(from, minOutTo, from.getAD_Client_ID(), from.getAD_Org_ID());
		minOutTo.set_ValueNoCheck("M_InOut_ID", I_ZERO);
		minOutTo.set_ValueNoCheck("DocumentNo", null);
		//
		minOutTo.setDocStatus(DOCSTATUS_Drafted); // Draft
		minOutTo.setDocAction(DOCACTION_Complete);
		//
		minOutTo.setC_DocType_ID(C_DocType_ID);
		minOutTo.setIsSOTrx(isSOTrx);
		if (counter) {
			minOutTo.setMovementType(isSOTrx ? MOVEMENTTYPE_CustomerShipment
					: MOVEMENTTYPE_VendorReceipts);
		}
		//
		minOutTo.setDateOrdered(dateDoc);
		minOutTo.setDateAcct(dateDoc);
		minOutTo.setMovementDate(dateDoc);
		minOutTo.setDatePrinted(null);
		minOutTo.setIsPrinted(false);
		minOutTo.setDateReceived(null);
		minOutTo.setNoPackages(0);
		minOutTo.setShipDate(null);
		minOutTo.setPickDate(null);
		minOutTo.setIsInTransit(false);
		//
		minOutTo.setIsApproved(false);
		minOutTo.setC_Invoice_ID(0);
		minOutTo.setTrackingNo(null);
		minOutTo.setIsInDispute(false);
		//
		minOutTo.setPosted(false);
		minOutTo.setProcessed(false);
		minOutTo.setC_Order_ID(0); // Overwritten by setOrder
		if (counter) {
			minOutTo.setC_Order_ID(0);
			minOutTo.setRef_InOut_ID(from.getM_InOut_ID());
			// Try to find Order/Invoice link
			if (from.getC_Order_ID() != 0) {
				final MOrder peer = new MOrder(from.getCtx(), from.getC_Order_ID(),
						from.get_TrxName());
				if (peer.getRef_Order_ID() != 0) {
					minOutTo.setC_Order_ID(peer.getRef_Order_ID());
				}
			}
			if (from.getC_Invoice_ID() != 0) {
				final MInvoice peer = new MInvoice(from.getCtx(), from
						.getC_Invoice_ID(), from.get_TrxName());
				if (peer.getRef_Invoice_ID() != 0) {
					minOutTo.setC_Invoice_ID(peer.getRef_Invoice_ID());
				}
			}
		} else {
			minOutTo.setRef_InOut_ID(0);
			if (setOrder) {
				minOutTo.setC_Order_ID(from.getC_Order_ID());
			}
		}
		//
		if (!minOutTo.save(trxName)) {
			throw new IllegalStateException("Could not create Shipment");
		}
		if (counter) {
			from.setRef_InOut_ID(minOutTo.getM_InOut_ID());
		}

		if (minOutTo.copyLinesFrom(from
				, counter, setOrder, mNota) == 0) {
			throw new IllegalStateException("Could not create Shipment Lines");
		}

		return minOutTo;
	} // copyFrom

	/**************************************************************************
	 * Standard Constructor
	 *
	 * @param ctx
	 *            context
	 * @param M_InOut_ID
	 * @param trxName
	 *            rx name
	 */

	public MInOut(final Properties ctx, final int M_InOut_ID, final String trxName) {
		super(ctx, M_InOut_ID, trxName);
		if (M_InOut_ID == 0) {
			setIsSOTrx(false);
			setMovementDate(DB.convert(ctx, new Timestamp(System.currentTimeMillis())));
			setDateAcct(getMovementDate());
			setDeliveryRule(DELIVERYRULE_Availability);
			setDeliveryViaRule(DELIVERYVIARULE_Pickup);
			setFreightCostRule(FREIGHTCOSTRULE_FreightIncluded);
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DOCACTION_Complete);
			setPriorityRule(PRIORITYRULE_Medium);
			setNoPackages(0);
			setIsInTransit(false);
			setIsPrinted(false);
			setSendEMail(false);
			setIsInDispute(false);
			//
			setIsApproved(false);
			super.setProcessed(false);
			setProcessing(false);
			setPosted(false);
		}
	} // MInOut

	/**
	 * Load Constructor
	 *
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set record
	 * @param trxName
	 *            transaction
	 */
	public MInOut(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	} // MInOut

	/**
	 * Order Constructor - create header only
	 *
	 * @param order
	 *            order
	 * @param movementDate
	 *            optional movement date (default today)
	 * @param C_DocTypeShipment_ID
	 *            document type or 0
	 */
	public MInOut(final MOrder order, int C_DocTypeShipment_ID, final Timestamp movementDate) {
		this(order.getCtx(), 0, order.get_TrxName());
		PO.copyValues(order, this);
		setClientOrg(order);
		setDocStatus(DOCSTATUS_Drafted);
		setDocAction(DOCACTION_Complete);
		setC_BPartner_ID(order.getC_BPartner_ID());
		setC_BPartner_Location_ID(order.getC_BPartner_Location_ID()); // shipment
		setAD_User_ID(order.getAD_User_ID());
		setM_PriceList_ID(order.getM_PriceList_ID()<=0?getBPartner().getPO_PriceList_ID():order.getM_PriceList_ID());
		setM_Warehouse_ID(order.getM_Warehouse_ID());
		setIsSOTrx(order.isSOTrx());
		setMovementType(order.isSOTrx() ? MOVEMENTTYPE_CustomerShipment
				: MOVEMENTTYPE_VendorReceipts);
		if (C_DocTypeShipment_ID == 0) {
			C_DocTypeShipment_ID = DB
					.getSQLValue(
							null,
							"SELECT C_DocTypeShipment_ID FROM C_DocType WHERE C_DocType_ID=?",
							order.getC_DocType_ID());
		}
		setC_DocType_ID(C_DocTypeShipment_ID);

		if(getC_DocType_ID()<=0){
            setC_DocType_ID();
		}

		// Default - Today
		if (movementDate != null) {
			setMovementDate(movementDate);
		}
		setDateAcct(getMovementDate());

		// Copy from Order
		setC_Order_ID(order.getC_Order_ID());
		setDeliveryRule(order.getDeliveryRule());
		setDeliveryViaRule(order.getDeliveryViaRule());
		setM_Shipper_ID(order.getM_Shipper_ID());
		setFreightCostRule(order.getFreightCostRule());
		setFreightAmt(order.getFreightAmt());
		setSalesRep_ID(order.getSalesRep_ID());
		//
		setC_Activity_ID(order.getC_Activity_ID());
		setC_Campaign_ID(order.getC_Campaign_ID());
		setC_Charge_ID(order.getC_Charge_ID());
		setChargeAmt(order.getChargeAmt());
		//
		setC_Project_ID(order.getC_Project_ID());
		setDateOrdered(order.getDateOrdered());
		setDescription(order.getDescription());
		setPOReference(order.getPOReference());
		setSalesRep_ID(order.getSalesRep_ID());
		setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
		setUser1_ID(order.getUser1_ID());
		setUser2_ID(order.getUser2_ID());
	} // MInOut

	/**
	 * Invoice Constructor - create header only
	 *
	 * @param invoice
	 *            invoice
	 * @param C_DocTypeShipment_ID
	 *            document type or 0
	 * @param movementDate
	 *            optional movement date (default today)
	 * @param M_Warehouse_ID
	 *            warehouse
	 */
	public MInOut(final MInvoice invoice, int C_DocTypeShipment_ID,
			final Timestamp movementDate, final int M_Warehouse_ID) {
		this(invoice.getCtx(), 0, invoice.get_TrxName());
		setClientOrg(invoice);
		setC_BPartner_ID(invoice.getC_BPartner_ID());
		setC_BPartner_Location_ID(invoice.getC_BPartner_Location_ID()); // shipment
																		// address
		setAD_User_ID(invoice.getAD_User_ID());
		//
		setM_Warehouse_ID(M_Warehouse_ID);
		setIsSOTrx(invoice.isSOTrx());
		setMovementType(invoice.isSOTrx() ? MOVEMENTTYPE_CustomerShipment
				: MOVEMENTTYPE_VendorReceipts);
		MOrder order = null;
		if (invoice.getC_Order_ID() != 0) {
			order = new MOrder(invoice.getCtx(), invoice.getC_Order_ID(),
					invoice.get_TrxName());
		}
		if (C_DocTypeShipment_ID == 0 && order != null) {
			C_DocTypeShipment_ID = DB
					.getSQLValue(
							null,
							"SELECT C_DocTypeShipment_ID FROM C_DocType WHERE C_DocType_ID=?",
							order.getC_DocType_ID());
		}
		if (C_DocTypeShipment_ID == 0) {
			setC_DocType_ID();
		} else {
			setC_DocType_ID(C_DocTypeShipment_ID);
		}

		// Default - Today
		if (movementDate != null) {
			setMovementDate(movementDate);
		}
		setDateAcct(getMovementDate());

		// Copy from Invoice
		setC_Order_ID(invoice.getC_Order_ID());
		setSalesRep_ID(invoice.getSalesRep_ID());
		//
		setC_Activity_ID(invoice.getC_Activity_ID());
		setC_Campaign_ID(invoice.getC_Campaign_ID());
		setC_Charge_ID(invoice.getC_Charge_ID());
		setChargeAmt(invoice.getChargeAmt());
		//
		setC_Project_ID(invoice.getC_Project_ID());
		setDateOrdered(invoice.getDateOrdered());
		setDescription(invoice.getDescription());
		setPOReference(invoice.getPOReference());
		setAD_OrgTrx_ID(invoice.getAD_OrgTrx_ID());
		setUser1_ID(invoice.getUser1_ID());
		setUser2_ID(invoice.getUser2_ID());

		if (order != null) {
			setDeliveryRule(order.getDeliveryRule());
			setDeliveryViaRule(order.getDeliveryViaRule());
			setM_Shipper_ID(order.getM_Shipper_ID());
			setFreightCostRule(order.getFreightCostRule());
			setFreightAmt(order.getFreightAmt());
		}
	} // MInOut

	/**
	 * Copy Constructor - create header only
	 *
	 * @param original
	 *            original
	 * @param movementDate
	 *            optional movement date (default today)
	 * @param C_DocTypeShipment_ID
	 *            document type or 0
	 */
	public MInOut(final MInOut original, final int C_DocTypeShipment_ID,
			final Timestamp movementDate) {
		this(original.getCtx(), 0, original.get_TrxName());
		setClientOrg(original);
		setC_BPartner_ID(original.getC_BPartner_ID());
		setC_BPartner_Location_ID(original.getC_BPartner_Location_ID()); // shipment
																			// address
		setAD_User_ID(original.getAD_User_ID());
		//
		setM_Warehouse_ID(original.getM_Warehouse_ID());
		setIsSOTrx(original.isSOTrx());
		setMovementType(original.getMovementType());
		if (C_DocTypeShipment_ID == 0) {
			setC_DocType_ID(original.getC_DocType_ID());
		} else {
			setC_DocType_ID(C_DocTypeShipment_ID);
		}

		// Default - Today
		if (movementDate != null) {
			setMovementDate(movementDate);
		}
		setDateAcct(getMovementDate());

		// Copy from Order
		setC_Order_ID(original.getC_Order_ID());
		setDeliveryRule(original.getDeliveryRule());
		setDeliveryViaRule(original.getDeliveryViaRule());
		setM_Shipper_ID(original.getM_Shipper_ID());
		setFreightCostRule(original.getFreightCostRule());
		setFreightAmt(original.getFreightAmt());
		setSalesRep_ID(original.getSalesRep_ID());
		//
		setC_Activity_ID(original.getC_Activity_ID());
		setC_Campaign_ID(original.getC_Campaign_ID());
		setC_Charge_ID(original.getC_Charge_ID());
		setChargeAmt(original.getChargeAmt());
		//
		setC_Project_ID(original.getC_Project_ID());
		setDateOrdered(original.getDateOrdered());
		setDescription(original.getDescription());
		setPOReference(original.getPOReference());
		setSalesRep_ID(original.getSalesRep_ID());
		setAD_OrgTrx_ID(original.getAD_OrgTrx_ID());
		setUser1_ID(original.getUser1_ID());
		setUser2_ID(original.getUser2_ID());
	} // MInOut

	/**
	 * Get Document Status
	 *
	 * @return Document Status Clear Text
	 */
	public String getDocStatusName() {
		return MRefList.getListName(getCtx(), 131, getDocStatus());
	} // getDocStatusName

	/**
	 * Add to Description
	 *
	 * @param description
	 *            text
	 */
	public void addDescription(final String description) {
		final String desc = getDescription();
		if (desc == null) {
			setDescription(description);
		} else {
			setDescription(desc + " | " + description);
		}
	} // addDescription

	/**
	 * String representation
	 *
	 * @return info
	 */
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("MInOut[").append(get_ID()).append(
				"-").append(getDocumentNo()).append(",DocStatus=").append(
				getDocStatus()).append("]");
		return sb.toString();
	} // toString

	/**
	 * Get Document Info
	 *
	 * @return document info (untranslated)
	 */
	@Override
	public String getDocumentInfo() {
		final MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
		return docType.getName() + " " + getDocumentNo();
	} // getDocumentInfo



	public MDocType getDocType(){
		if(docType==null && getC_DocType_ID()>0){
			setDocType();
		}
		return docType;
	}

	/**
	 *
	 * @param forceIt
	 * @return
	 */
	public void setDocType(){
		if (getC_DocType_ID() > 0) {
			docType = MDocType.get(getCtx(), getC_DocType_ID());
		}
	}

	/**
	 * Create PDF
	 *
	 * @return File or null
	 */
	@Override
	public File createPDF() {
		File temp = null;
		try {
			temp = File.createTempFile(get_TableName() + get_ID() + "_",
					".pdf");
//			return createPDF(temp);
		} catch (final Exception e) {
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return temp;
	} // getPDF

	/**
	 * Create PDF file
	 *
	 * @param file
	 *            output file
	 * @return file if success
	 */
	public File createPDF(final File file) {
		final ReportEngine reportEngin = ReportEngine.get(getCtx(), ReportEngine.SHIPMENT,
				getC_Invoice_ID());
		return reportEngin == null ? null : reportEngin.getPDF(file);
	} // createPDF

	/**
	 * Get Lines of Order
	 *
	 * @param requery
	 *            requery
	 * @param orderBy
	 *            optional order by column
	 * @return lines
	 */
	public MInOutLine[] getLines(final boolean requery, final String orderBy) {

		ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
		if (m_lines != null && !requery){
			return m_lines.clone();
		}

		final StringBuilder sql = new StringBuilder("SELECT * FROM M_InOutLine WHERE M_InOut_ID=? ORDER BY ");
		if (orderBy != null && orderBy.length() > 0) {
			sql.append(orderBy);
		} else {
			sql.append("Line");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());


			pstmt.setInt(1, getM_InOut_ID());
			rs = pstmt.executeQuery();
			while (rs.next()){
				list.add(new MInOutLine(getCtx(), rs, get_TrxName()));
			}
		} catch (final SQLException ex) {
			log.log(Level.SEVERE, sql.toString(), ex);
			list = null;
		} finally {
			DB.close(rs,pstmt);
		}
		//
		if (list == null){
			return null;
		}
		//
		m_lines = new MInOutLine[list.size()];
		return list.toArray(m_lines);
	} // getMInOutLines

	/**
	 * Get Lines of Shipment
	 *
	 * @return lines
	 */
	public MInOutLine[] getLines() {
		return getLines(false);
	} // getLines

	/**
	 * Get Lines of Shipment
	 *
	 * @param requery
	 *            refresh from db
	 * @return lines
	 */
	public MInOutLine[] getLines(final boolean requery) {
		return getLines(requery, null);
	} // getLines

	/**
	 * Get Confirmations
	 *
	 * @param requery
	 *            requery
	 * @return array of Confirmations
	 */
	public MInOutConfirm[] getConfirmations(final boolean requery) {
		if (m_confirms != null && !requery) {
			return m_confirms;
		}

		final ArrayList<MInOutConfirm> list = new ArrayList<MInOutConfirm>();
		final String sql = "SELECT * FROM M_InOutConfirm WHERE M_InOut_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getM_InOut_ID());
			rs = pstmt.executeQuery();
			while (rs.next()){
				list.add(new MInOutConfirm(getCtx(), rs, get_TrxName()));
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs,pstmt);
		}
		m_confirms = new MInOutConfirm[list.size()];
		list.toArray(m_confirms);
		return m_confirms;
	} // getConfirmations

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
	 */
	public int copyLinesFrom(final MInOut otherShipment, final boolean counter,
			final boolean setOrder, final MInvoice nota) {
		if (isProcessed() || isPosted() || otherShipment == null) {
			return 0;
		}
		final MInOutLine[] fromLines = otherShipment.getLines(false);
		int count = 0;
			for (final MInOutLine fromLine : fromLines) {


				MInvoiceLine lNota = nota==null ? null : nota.getLines(fromLine.getM_InOutLine_ID());


				final MInOutLine line = new MInOutLine(this);
				line.set_TrxName(get_TrxName());
				if (counter) {
					PO.copyValues(fromLine, line, getAD_Client_ID(), getAD_Org_ID());
				} else {
					PO.copyValues(fromLine, line, fromLine.getAD_Client_ID(), fromLine.getAD_Org_ID());
				}
				line.setM_InOut_ID(getM_InOut_ID());
				line.set_ValueNoCheck("M_InOutLine_ID", I_ZERO); // new
				// Reset
				if (!setOrder) {
					line.setC_OrderLine_ID(0);
				}
				if (!counter) {
					line.setM_AttributeSetInstance_ID(0);
				}
				// line.setS_ResourceAssignment_ID(0);
				line.setRef_InOutLine_ID(0);
				line.setIsInvoiced(false);
				//
				line.setConfirmedQty(Env.ZERO);
				line.setPickedQty(Env.ZERO);
				line.setScrappedQty(Env.ZERO);
				line.setTargetQty(Env.ZERO);
				// Set Locator based on header Warehouse
				if (getM_Warehouse_ID() != otherShipment.getM_Warehouse_ID()) {
					line.setM_Locator_ID(0);
					line.setM_Locator_ID(Env.ZERO);
				}
				//
				if (counter) {
					line.setRef_InOutLine_ID(fromLine.getM_InOutLine_ID());
					if (fromLine.getC_OrderLine_ID() != 0) {
						final MOrderLine peer = new MOrderLine(getCtx(), fromLine.getC_OrderLine_ID(), get_TrxName());
						if (peer.getRef_OrderLine_ID() != 0) {
							line.setC_OrderLine_ID(peer.getRef_OrderLine_ID());
						}
					}
				}
				//
				line.setProcessed(false);
				if (line.save(get_TrxName())) {
					count++;
					if(lNota!=null){
						lNota.setM_InOutLine_ID(line.getM_InOutLine_ID());
						if (!lNota.save(get_TrxName())) {
							throw new MedsysException();
						}
					}
				} else {
					throw new MedsysException();
				}

				// Cross Link
				if (counter) {
					fromLine.setRef_InOutLine_ID(line.getM_InOutLine_ID());
					if (!fromLine.save(get_TrxName())) {
						throw new MedsysException();
					}
				}
			}
		if (fromLines.length != count) {
			log.log(Level.SEVERE, "Line difference - From=" + fromLines.length + " <> Saved=" + count);
		}
		return count;
	} // copyLinesFrom

	/**
	 * Set Reversal
	 *
	 * @param reversal
	 *            reversal
	 */
	private void setReversal(final boolean reversal) {
		m_reversal = reversal;
	} // setReversal

	/**
	 * Is Reversal
	 *
	 * @return reversal
	 */
	private boolean isReversal() {
		return m_reversal;
	} // isReversal

	/**
	 * Set Processed. Propergate to Lines/Taxes
	 *
	 * @param processed
	 *            processed
	 */
	@Override
	public void setProcessed(final boolean processed) {
		super.setProcessed(processed);
		if (get_ID() == 0) {
			return;
		}
		final String sql = "UPDATE M_InOutLine SET Processed='"
				+ (processed ? "Y" : "N") + "' WHERE M_InOut_ID="
				+ getM_InOut_ID();
		final int noLine = DB.executeUpdate(sql, get_TrxName());
		m_lines = null;
		log.fine(processed + " - Lines=" + noLine);
	} // setProcessed

	/**
	 * Get BPartner
	 *
	 * @return partner
	 */
	public MBPartner getBPartner() {
		if (m_partner == null) {
			m_partner = new MBPartner(getCtx(), getC_BPartner_ID(),
					get_TrxName());
		}
		return m_partner;
	} // getPartner

	/**
	 * Set Document Type
	 *
	 * @param DocBaseType
	 *            doc type MDocType.DOCBASETYPE_
	 */
	public void setC_DocType_ID(final String DocBaseType) {
		final String sql = "SELECT C_DocType_ID FROM C_DocType "
				+ "WHERE AD_Client_ID=? AND DocBaseType=?"
				+ " AND IsActive='Y'" + " AND IsSOTrx='"
				+ (isSOTrx() ? "Y" : "N") + "' "
				+ "ORDER BY IsDefault DESC, C_DocType_ID ";
		final int C_DocType_ID = DB.getSQLValue(null, sql, getAD_Client_ID(),
				DocBaseType);
		if (C_DocType_ID <= 0) {
			log.log(Level.SEVERE, "Not found for AC_Client_ID="
					+ getAD_Client_ID() + " - " + DocBaseType);
		} else {
			log.fine("DocBaseType=" + DocBaseType + " - C_DocType_ID="
					+ C_DocType_ID);
			setC_DocType_ID(C_DocType_ID);
			final boolean isSOTrx = X_C_DocType.DOCBASETYPE_MaterialDelivery
					.equals(DocBaseType);
			setIsSOTrx(isSOTrx);
		}
	} // setC_DocType_ID

	/**
	 * Set Default C_DocType_ID. Based on SO flag
	 */
	public void setC_DocType_ID() {
		if (isSOTrx()) {
			setC_DocType_ID(X_C_DocType.DOCBASETYPE_MaterialDelivery);
		} else {
			setC_DocType_ID(X_C_DocType.DOCBASETYPE_MaterialReceipt);
		}
	} // setC_DocType_ID

	/**
	 * Set Business Partner Defaults & Details
	 *
	 * @param bp
	 *            business partner
	 */
	public void setBPartner(final MBPartner bp) {
		if (bp == null) {
			return;
		}

		setC_BPartner_ID(bp.getC_BPartner_ID());

		// Set Locations
		final MBPartnerLocation[] locs = bp.getLocations(false);
		if (locs != null) {
			for (final MBPartnerLocation loc : locs) {
				if (loc.isShipTo()) {
					setC_BPartner_Location_ID(loc
							.getC_BPartner_Location_ID());
				}
			}
			// set to first if not set
			if (getC_BPartner_Location_ID() == 0 && locs.length > 0) {
				setC_BPartner_Location_ID(locs[0].getC_BPartner_Location_ID());
			}
		}
		if (getC_BPartner_Location_ID() == 0) {
			log.log(Level.SEVERE, "Has no To Address: " + bp);
		}

		// Set Contact
		final MUser[] contacts = bp.getContacts(false);
		if (contacts != null && contacts.length > 0) {
			setAD_User_ID(contacts[0].getAD_User_ID());
		}
	} // setBPartner

	/**
	 * Create the missing next Confirmation
	 */
	public void createConfirmation() {
		final MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		final boolean pick = dt.isPickQAConfirm();
		final boolean ship = dt.isShipConfirm();
		// Nothing to do
		if (!pick && !ship) {
			log.fine("No need");
			return;
		}

		// Create Both .. after each other
		if (pick && ship) {
			boolean havePick = false;
			boolean haveShip = false;
			final MInOutConfirm[] confirmations = getConfirmations(false);
			for (final MInOutConfirm confirmation : confirmations) {
				final MInOutConfirm confirm = confirmation;
				if (X_M_InOutConfirm.CONFIRMTYPE_PickQAConfirm.equals(confirm
						.getConfirmType())) {
					if (!confirm.isProcessed()) // wait intil done
					{
						log.fine("Unprocessed: " + confirm);
						return;
					}
					havePick = true;
				} else if (X_M_InOutConfirm.CONFIRMTYPE_ShipReceiptConfirm
						.equals(confirm.getConfirmType())) {
					haveShip = true;
				}
			}
			// Create Pick
			if (!havePick) {
				MInOutConfirm.create(this,
						X_M_InOutConfirm.CONFIRMTYPE_PickQAConfirm, false);
				return;
			}
			// Create Ship
			if (!haveShip) {
				MInOutConfirm.create(this,
						X_M_InOutConfirm.CONFIRMTYPE_ShipReceiptConfirm, false);
				return;
			}
			return;
		}
		// Create just one
		if (pick) {
			MInOutConfirm.create(this, X_M_InOutConfirm.CONFIRMTYPE_PickQAConfirm,
					true);
		} else if (ship) {
			MInOutConfirm.create(this,
					X_M_InOutConfirm.CONFIRMTYPE_ShipReceiptConfirm, true);
		}
	} // createConfirmation

	/**
	 * Set Warehouse and check/set Organization
	 *
	 * @param M_Warehouse_ID
	 *            id
	 */
	@Override
	public void setM_Warehouse_ID(final int M_Warehouse_ID) {
		if (M_Warehouse_ID == 0) {
			log.severe("Ignored - Cannot set AD_Warehouse_ID to 0");
			return;
		}
		super.setM_Warehouse_ID(M_Warehouse_ID);
		//
		final MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		if (wh.getAD_Org_ID() != getAD_Org_ID()) {
			log.warning("M_Warehouse_ID=" + M_Warehouse_ID
					+ ", Overwritten AD_Org_ID=" + getAD_Org_ID() + "->"
					+ wh.getAD_Org_ID());
			setAD_Org_ID(wh.getAD_Org_ID());
		}
	} // setM_Warehouse_ID

	/**
	 * Before Save
	 *
	 * @param newRecord
	 *            new
	 * @return true or false
	 */
	@Override
	protected boolean beforeSave(final boolean newRecord) {
		// Warehouse Org
		if (newRecord) {
			final MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			if (wh.getAD_Org_ID() != getAD_Org_ID()) {
				log.saveError("WarehouseOrgConflict", "");
				return false;
			}
		}
		// Shipment - Needs Order
		if (isSOTrx() && getC_Order_ID() == 0) {
			log.severe("MInOut#beforeSave: [isSOTrx && C_Order_ID== 0]");
			log.saveError("FillMandatory", Msg.translate(getCtx(), "C_Order_ID"));
			//return false;
		}

		// Contabilizar o no contabilizar ?
		setNotPosted();

		return true;
	} // beforeSave

	/**
	 * After Save
	 *
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return success
	 */
	@Override
	protected boolean afterSave(final boolean newRecord, final boolean success) {
		if (!success || newRecord) {
			return success;
		}

		if (is_ValueChanged("AD_Org_ID")) {
			final String sql = "UPDATE M_InOutLine ol" + " SET AD_Org_ID ="
					+ "(SELECT AD_Org_ID"
					+ " FROM M_InOut o WHERE ol.M_InOut_ID=o.M_InOut_ID) "
					+ "WHERE M_InOut_ID = " + getM_InOut_ID();
			final int no = DB.executeUpdate(sql, get_TrxName());
			log.fine("Lines -> #" + no);
		}
		return true;
	} // afterSave

	/**************************************************************************
	 * Process document
	 *
	 * @param processAction
	 *            document action
	 * @return true if performed
	 */
	@Override
	public boolean processIt(final String processAction) {
		m_processMsg = null;
		final DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());
	} // process

	/** Process Message */
	private String m_processMsg = null;
	/** Just Prepared Flag */
	private boolean m_justPrepared = false;

	/**
	 * Unlock Document.
	 *
	 * @return true if success
	 */
	@Override
	public boolean unlockIt() {
		log.info(toString());
		setProcessing(false);
		return true;
	} // unlockIt

	/**
	 * Invalidate Document
	 *
	 * @return true if success
	 */
	@Override
	public boolean invalidateIt() {
		log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	} // invalidateIt

	/**
	 * Prepare Document
	 *
	 * @return new status (In Progress or Invalid)
	 */
	@Override
	public String prepareIt() {
		log.info(toString());

		if(invalidWhilePrepareit()){
			return DocAction.STATUS_Invalid;
		}

		if (!isReversal()) // don't change reversal
		{
			checkMaterialPolicy(); // set MASI
			createConfirmation();
		}

		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction())) {
			setDocAction(DOCACTION_Complete);
		}
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/**
	 * Metodo extraido de prepareit
	 * @return regresa verdadero para invalidar
	 */
	private boolean invalidWhilePrepareit() {
		boolean retValue = false;
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null && !retValue) {
			retValue = true;
//			return DocAction.STATUS_Invalid;
		}

		final MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		// Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType(), getAD_Org_ID()) && !retValue) {
			m_processMsg = "@PeriodClosed@";
			retValue = true;
//			return DocAction.STATUS_Invalid;
		}

		// Credit Check
//		if (isSOTrx() && !isReversal() && !retValue) {
//			final MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), null);
//			if (X_C_BPartner.SOCREDITSTATUS_CreditStop.equals(bp
//					.getSOCreditStatus())) {
//				m_processMsg = "@BPartnerCreditStop@ - @TotalOpenBalance@="
//						+ bp.getTotalOpenBalance() + ", @SO_CreditLimit@="
//						+ bp.getSO_CreditLimit();
//				retValue = true;
////				return DocAction.STATUS_Invalid;
//			}
//			if (X_C_BPartner.SOCREDITSTATUS_CreditHold.equals(bp
//					.getSOCreditStatus()) && !retValue) {
//				m_processMsg = "@BPartnerCreditHold@ - @TotalOpenBalance@="
//						+ bp.getTotalOpenBalance() + ", @SO_CreditLimit@="
//						+ bp.getSO_CreditLimit();
//				retValue = true;
////				return DocAction.STATUS_Invalid;
//			}
//			final BigDecimal notInvoicedAmt = MBPartner
//					.getNotInvoicedAmt(getC_BPartner_ID());
//			if (X_C_BPartner.SOCREDITSTATUS_CreditHold.equals(bp
//					.getSOCreditStatus(notInvoicedAmt)) && !retValue) {
//				m_processMsg = "@BPartnerOverSCreditHold@ - @TotalOpenBalance@="
//						+ bp.getTotalOpenBalance()
//						+ ", @NotInvoicedAmt@="
//						+ notInvoicedAmt
//						+ ", @SO_CreditLimit@="
//						+ bp.getSO_CreditLimit();
//				retValue = true;
////				return DocAction.STATUS_Invalid;
//			}
//		}

		// Lines
		final MInOutLine[] lines = getLines(true);
		if (lines == null || lines.length == 0 && !retValue) {
			m_processMsg = "@NoLines@";
			retValue = true;
//			return DocAction.STATUS_Invalid;
		}
		BigDecimal volume = Env.ZERO;
		BigDecimal weight = Env.ZERO;

		// Mandatory Attributes
		for (final MInOutLine line : lines) {
			final MProduct product = line.getProduct();

//			Card #1063 - Orden de Compra - Validación
//			incluir una validación para que no se permita COMPLETAR una Orden de Compra si
//			los productos que incluye no son parte del Maestro de Productos.

			if (MEXMEProductoOrg.getProductoOrg(getCtx(), product.getM_Product_ID(), line.getAD_Client_ID(), line.getAD_Org_ID(), null)==null){
				m_processMsg = Utilerias.getLabel("msj.ligarProducto");
				retValue = true;
			} else {

				if (product != null) {
					volume = volume.add(product.getVolume().multiply(
							line.getMovementQty()));
					weight = weight.add(product.getWeight().multiply(
							line.getMovementQty()));
				}
				//
				if (line.getM_AttributeSetInstance_ID() != 0) {
					continue;
				}
				if (product != null && !retValue) {
					final int M_AttributeSet_ID = product.getM_AttributeSet_ID();
					if (M_AttributeSet_ID != 0) {
						final MAttributeSet mas = MAttributeSet.get(getCtx(),
								M_AttributeSet_ID);
						if (mas != null
								&& ((isSOTrx() && mas.isMandatory()) || (!isSOTrx() && mas
										.isMandatoryAlways()))) {
							m_processMsg = "@M_AttributeSet_ID@ @IsMandatory@";
							retValue = true;
							//						return DocAction.STATUS_Invalid;
						}
					}
				}
			}
		}
		if (!retValue) {
			setVolume(volume);
			setWeight(weight);
		}
		return retValue;
	}

	/**
	 * Approve Document
	 *
	 * @return true if success
	 */
	@Override
	public boolean approveIt() {
		log.info(toString());
		setIsApproved(true);
		return true;
	} // approveIt

	/**
	 * Reject Approval
	 *
	 * @return true if success
	 */
	@Override
	public boolean rejectIt() {
		log.info(toString());
		setIsApproved(false);
		return true;
	} // rejectIt

	/**
	 * Complete Document
	 *
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	@Override
	public String completeIt() {

		// Crear una orden cuando esta no exista, no exista una factura, cuando no exista cuenta paciente y cuando no sea un almacen de consigna
		if (getC_Invoice_ID()<=0 && getEXME_CtaPac_ID()<=0 && !isSOTrx()
				&& !isSetpoint()
					&& !DocAction.STATUS_Completed.equals(createOrder(get_TrxName()))) {
						return DocAction.STATUS_Invalid;
		}

		// Re-Check
		if (!m_justPrepared) {
			final String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		// Outstanding (not processed) Incoming Confirmations ?
		final MInOutConfirm[] confirmations = getConfirmations(true);
		for (final MInOutConfirm confirmation : confirmations) {
			final MInOutConfirm confirm = confirmation;
			if (!confirm.isProcessed()) {
				if (X_M_InOutConfirm.CONFIRMTYPE_CustomerConfirmation
						.equals(confirm.getConfirmType())) {
					continue;
				}
				//
				m_processMsg = "Open @M_InOutConfirm_ID@: "
						+ confirm.getConfirmTypeName() + " - "
						+ confirm.getDocumentNo();
				return DocAction.STATUS_InProgress;
			}
		}

		// Implicit Approval
		if (!isApproved()) {
			approveIt();
		}

		log.info(toString());
		final StringBuffer info = new StringBuffer();
		final MEXMEConfigPre configPre = MEXMEConfigPre.get(getCtx(), null);
		final int listSalesID = configPre.getM_PriceList_ID();

		final MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
		final List<MInOutLine> alines = new ArrayList<MInOutLine>();

		// For all lines
		final MInOutLine[] lines = getLines(true);
		for (final MInOutLine sLine : lines) {

//			final MInOutLine sLine = line;
			final MProduct product = sLine.getProduct();

			// Qty & Type
			final String MovementType = getMovementType();
			BigDecimal Qty = sLine.getMovementQty();
			BigDecimal QtyVol = sLine.getMovementQty_Vol();
			
			// Customer Shipment = C- 
			// Customer Returns  = C+ 
			// Vendor Receipts   = V+ 
			// Vendor Returns    = V- 
			if (MovementType.charAt(1) == '-' && Qty.signum() > 0){//-1 (negative), 0 (zero), or 1 (positive)
				Qty = Qty.negate();
				QtyVol = QtyVol.negate();
				
			}  else if (MovementType.charAt(1) == '+' && Qty.signum() < 0){
				Qty = Qty.abs(); 
				QtyVol = QtyVol.abs();
			}
			
			BigDecimal QtySO = Env.ZERO;
			BigDecimal QtyPO = Env.ZERO;

			// Update Order Line
			MOrderLine oLine = null;
			if (sLine.getC_OrderLine_ID() != 0) {
				oLine = new MOrderLine(getCtx(), sLine.getC_OrderLine_ID(),
						get_TrxName());
				log.fine("OrderLine - Reserved=" + oLine.getQtyReserved()
						+ ", Delivered=" + oLine.getQtyDelivered());
				if (isSOTrx()) {
					QtySO = sLine.getMovementQty();
				} else {
					QtyPO = sLine.getMovementQty();
				}
			}

			log.info("Line=" + sLine.getLine() + " - Qty="
					+ sLine.getMovementQty());

			// Stock Movement - Counterpart MOrder.reserveStock
			if (product != null
					&& (product.isStocked() || product.isEsVacuna())) {
				String status = insertTransaction(oLine, sLine, MovementType, product, Qty, QtySO, QtyPO, ass);
				if(status!=null)
					return status;
			}

			// Correct Order Line
			if (product != null && oLine != null) {
				// VMatch.createMatchRecord
				oLine.setQtyReserved(oLine.getQtyReserved().subtract(
						sLine.getMovementQty()));
				oLine.setQtyReserved_Vol(oLine.getQtyReserved_Vol().subtract(
						sLine.getMovementQty_Vol()));
			}

			// Update Sales Order Line
			if (oLine != null) {
				if (isSOTrx() // PO is done by Matching
						|| sLine.getM_Product_ID() == 0) // PO Charges, empty
															// lines
				{
					if (isSOTrx()) {
						oLine.setQtyDelivered(oLine.getQtyDelivered().subtract(
								Qty));
						oLine.setQtyDelivered_Vol(oLine.getQtyDelivered_Vol().subtract(
								QtyVol));
					} else {
						oLine.setQtyDelivered(oLine.getQtyDelivered().add(Qty));
						oLine.setQtyDelivered_Vol(oLine.getQtyDelivered_Vol().add(QtyVol));
					}
					oLine.setDateDelivered(getMovementDate()); // overwrite=last
				}
				if (oLine.save()) {
					log.fine("OrderLine -> Reserved=" + oLine.getQtyReserved()
							+ ", Delivered=" + oLine.getQtyReserved());
				} else {
					m_processMsg = "Could not update Order Line";
					return DocAction.STATUS_Invalid;

				}
			}

			// Create Asset for SO
			if (product != null && isSOTrx() && product.isCreateAsset()
					&& sLine.getMovementQty().signum() > 0 && !isReversal()) {
				log.fine("Asset");
				info.append("@A_Asset_ID@: ");
				int noAssets = sLine.getMovementQty().intValue();
				if (!product.isOneAssetPerUOM()) {
					noAssets = 1;
				}
				for (int i = 0; i < noAssets; i++) {
					if (i > 0) {
						info.append(" - ");
					}
					int deliveryCount = i + 1;
					if (!product.isOneAssetPerUOM()) {
						deliveryCount = 0;
					}
					final MAsset asset = new MAsset(this, sLine, deliveryCount);
					if (!asset.save(get_TrxName())) {
						m_processMsg = "Could not create Asset";
						return DocAction.STATUS_Invalid;
					}
					info.append(asset.getValue());
				}
			} // Asset

			// Matching
			if (!isSOTrx() && sLine.getM_Product_ID() != 0 && !isReversal()) {
				final String status = matching(oLine, sLine);
				if(status!=null) {
					return status;
				}
			} // PO Matching

			// Expert: Raul Montemayor- Inicio
			if (product != null) {
				final String status = processAsset(product, sLine);
				if(status!=null){
					return status;
				}
			}

			// rsolorzano 20130507
			// Card 716 Calculo del precio en base al precio de compra
			// ETS 06949: Decremento en precios. Jesus Cantu 23 Abril 14.
			if(X_C_DocType.DOCBASETYPE_MaterialReceipt.equals(getDocType().getDocBaseType())
					&& !isSetpoint() &&listSalesID > 0 && configPre.isUsarFactor() && sLine.getM_Product_ID()>0) {
				//buscamos el factor precio por producto, si no existe se usa por categoria
				MEXMEFactorPre factor = MEXMEFactorPre.getFactorByProduct(getCtx(), product.getM_Product_ID());
				if (factor == null) {
					factor = MEXMEFactorPre.getFactorByCategory(getCtx(), product.getM_Product_Category_ID());
				}
				//si existe el factor precio se actualizan los precios
				if (factor != null) {
					if(!updatePriceProduct(listSalesID, factor, product, sLine)){
						m_processMsg = Utilerias.getLabel("msj.factor.precio");
						return DocAction.STATUS_Invalid;
					}
				}
			} //

			alines.add(sLine);
		} // for all lines

		// Counter Documents
		final MInOut counter = createCounterDoc();
		if (counter != null) {
			info.append(" - @CounterDoc@: @M_InOut_ID@=").append(
					counter.getDocumentNo());
		}
		// User Validation
		final String valid = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		m_processMsg = Utilerias.getLabel("egresos.reg.docstatus").concat(" ").concat(Utilerias.getLabel(getDocAction()));
		setProcessed(true);
		setDocAction(DOCACTION_Close);

		setDateReceived(new Timestamp(System.currentTimeMillis()));//Fecha de recibido
		releaseOrder();

		// Actualizar los costos de la transacción
		postMatchPO(ass, alines);
		return DocAction.STATUS_Completed;
	}

	/** Recepción de activos */
	private String processAsset(final MProduct product, final MInOutLine sLine){

		// Configuracion de interfaces para la organizacion
		final MConfigInt configuracion = MConfigInt.get(getCtx(), null);
		// Si existe configuracion y utiliza interfaz de equipos
		if (configuracion != null
				&& configuracion.isInterfase_Equipos()) {
			// Categoria del producto
			final MEXMEProductCategory categoria = new MEXMEProductCategory(
					getCtx(), product.getM_Product_Category_ID(), null);
			// Si existe la categoria y la categoria tiene un grupo de
			// activos y no existe el registro
			if (categoria != null
					&& categoria.getA_Asset_Group_ID() > 0
					&& MAsset.isNew(product.getM_Product_ID(), null)) {

				// Creamos el registro del activo en A_Asset para el
				// producto(equipo medico)
				final MAsset objetoAsset = new MAsset(getCtx(), 0,
						get_TrxName());
				// objetoAsset.setValue(product.getValue());
				objetoAsset.setName(product.getName());
				objetoAsset.setM_Product_ID(product.get_ID());
				objetoAsset.setA_Asset_Group_ID(categoria
						.getA_Asset_Group_ID());
				objetoAsset.setIsOwned(true);
				objetoAsset.setIsInPosession(true);
				objetoAsset.setIsDepreciated(false);
				objetoAsset.setIsDisposed(false);
				objetoAsset.setIsFullyDepreciated(false);
				if (!objetoAsset.save(get_TrxName())) {
					// Si existen problemas para guardar no completa el
					// documento
					return DocAction.STATUS_Invalid;
				}
				// Buscamos si no existe un registro en EXME_Equipo con
				// el mismo A_Asset_ID
				if (MEXMEEquipo.isNew(objetoAsset.get_ID(), null)) {

					// Buscamos si el grupo de activos de la categoria
					// es "Creado como Activo"
					StringBuilder sql = new StringBuilder()
							.append(
									" SELECT aag.IsCreateAsActive FROM A_Asset_Group aag, ")
							.append(
									" M_Product_Category categoria, M_Product producto ")
							.append(
									" WHERE producto.M_Product_Category_id = categoria.M_Product_Category_id ")
							.append(" AND producto.M_Product_id = ? ")
							.append(
									" AND aag.A_Asset_Group_id = categoria.A_Asset_Group_id ")
							.append(" AND aag.IsCreateAsActive = 'Y' ");

					PreparedStatement prepa = null;
					ResultSet rs = null;
					try {
						prepa = DB.prepareStatement(sql.toString(),
								get_TrxName());
						prepa.setInt(1, product.getM_Product_ID());
						rs = prepa.executeQuery();
						if (rs.next()) {
							final MEXMEEquipo equipo = new MEXMEEquipo(
									getCtx(), 0, get_TrxName());
							equipo.setValue(objetoAsset.getValue());
							equipo.setFijo(sLine.isFijo());
							equipo.setM_Product_ID(sLine
									.getM_Product_ID());
							equipo.setName(objetoAsset.getName());
							equipo.setTodos(false);
							equipo.setA_Asset_ID(objetoAsset
									.getA_Asset_ID());

							// Buscamos el area
							sql = null;
							prepa = null;
							rs = null;
							sql = new StringBuilder()
									.append(" SELECT es.EXME_Area_ID  ")
									.append(" FROM EXME_EstServAlm  ")
									.append(
											" INNER JOIN EXME_EstServ es ON (es.EXME_EstServ_ID = EXME_EstServAlm.EXME_EstServ_ID) ")
									.append(
											" WHERE EXME_EstServAlm.IsActive = 'Y' ")
									.append(
											" AND EXME_EstServAlm.M_Warehouse_ID = ? AND es.EXME_Area_ID IS NOT NULL ");

							prepa = DB.prepareStatement(sql.toString(),
									get_TrxName());
							prepa.setInt(1, sLine.getM_Warehouse_ID());
							rs = prepa.executeQuery();
							if (rs.next()) {
								equipo.setEXME_Area_ID(rs.getInt(1));
								if (equipo.getEXME_Area_ID() < 0
										&& !equipo.save(get_TrxName())) {
									return DocAction.STATUS_Invalid;
								}
							}
							rs.close();
							prepa.close();
							prepa = null;
							rs = null;
						}
					} catch (final Exception e) {
						s_log.log(Level.SEVERE, Arrays.toString(e.getStackTrace()), e);
						return DocAction.STATUS_Invalid;
					} finally {
						DB.close(rs, prepa);
							sql = null;
					}
				}
			}
		}// Expert: Ra�l Montemayor- fin
		return null;
	}

	/** Creación de Conciliaciones */
	private String matching(MOrderLine oLine, final MInOutLine sLine){

		final BigDecimal matchQty = sLine.getMovementQty();
		// Invoice - Receipt Match (requires Product)
		final MInvoiceLine iLine = MInvoiceLine.getOfInOutLine(sLine);
		if (iLine != null && iLine.getM_Product_ID() != 0) {
			final MMatchInv[] matches = MMatchInv.get(getCtx(), sLine
					.getM_InOutLine_ID(), iLine.getC_InvoiceLine_ID(),
					get_TrxName());
			if (matches == null || matches.length == 0) {
				final MMatchInv inv = new MMatchInv(iLine, getMovementDate(),
						matchQty);
				if (sLine.getM_AttributeSetInstance_ID() != iLine
						.getM_AttributeSetInstance_ID()) {
					iLine.setM_AttributeSetInstance_ID(sLine
							.getM_AttributeSetInstance_ID());
					iLine.save(); // update matched invoice with ASI
					inv.setM_AttributeSetInstance_ID(sLine
							.getM_AttributeSetInstance_ID());
				}
				if (!inv.save(get_TrxName())) {
					m_processMsg = "Could not create Inv Matching";
					return DocAction.STATUS_Invalid;
				}
			}
		}

		// Link to Order
		if (sLine.getC_OrderLine_ID() == 0) {
			// No Order - Try finding links via Invoice
			// Invoice has an Order Link
			if (iLine != null && iLine.getC_OrderLine_ID() != 0) {
				// Invoice is created before Shipment
				log.fine("PO(Inv) Matching");
				// Ship - Invoice
				final MMatchPO po = MMatchPO.create(iLine, sLine,getMovementDate(), matchQty);
				if (po.save(get_TrxName())) {
					sLine.getlMatchPO().add(po);

				} else {
					m_processMsg = "Could not create PO(Inv) Matching";
					return DocAction.STATUS_Invalid;
				}
				// Update PO with ASI
				oLine = new MOrderLine(getCtx(),
						po.getC_OrderLine_ID(), get_TrxName());
				if (oLine != null
						&& oLine.getM_AttributeSetInstance_ID() == 0) {
					oLine.setM_AttributeSetInstance_ID(sLine
							.getM_AttributeSetInstance_ID());
					oLine.save(get_TrxName());
				}
			}
			 // No Order
		} else {
			log.fine("PO Matching");
			// Ship - PO
			final MMatchPO po = MMatchPO.create(null, sLine,getMovementDate(), matchQty);
			if (po.save(get_TrxName())) {
				sLine.getlMatchPO().add(po);

			} else {
				m_processMsg = "Could not create PO Matching";
				return DocAction.STATUS_Invalid;
			}
			// Update PO with ASI
			if (oLine != null
					&& oLine.getM_AttributeSetInstance_ID() == 0) {
				oLine.setM_AttributeSetInstance_ID(sLine
						.getM_AttributeSetInstance_ID());
				oLine.save(get_TrxName());
			}

		}
		return null;
	}

	/** Calcular costo promedio al completar Recepción/Dev Material // Card#1429 */
	private void postMatchPO(final MAcctSchema[] ass, final List<MInOutLine> lines){
		// Material receipt
		if(!getM_Warehouse().isConsigna()) {
			// For all lines
			for (final MInOutLine line : lines) {


				for (final MAcctSchema as : ass) {
					// Contabilizar la conciliación de la orden
					if(!isSOTrx()){
						for (final MMatchPO po : line.getlMatchPO()) {
							po.createUpdateCost(as);
						}
						
					} else {

						BigDecimal costoUnitario  = line.getTransactionalCost(as
								, X_M_InOut.MOVEMENTTYPE_CustomerReturns.equals(getMovementType()));
						line.setCosto(costoUnitario);
						line.save();
						
						
//						final StringBuilder sql = new StringBuilder() 
//						.append(" UPDATE M_InOutLine         ")
//						.append(" SET Costo = ?              ")
//						.append(" WHERE M_InOutLine_ID = ?   ");
//						
//						final int num = DB.executeUpdate(sql.toString(), new Object[]{costoUnitario, line.getM_InOutLine_ID()}, get_TrxName());
//
//						if (num != 1){
//							log.warning("1 #" + num);
//						}
//						
						
						BigDecimal _costs  = costoUnitario.multiply(line.getQtyEntered());
						

						if (X_M_InOut.MOVEMENTTYPE_CustomerReturns.equals(getMovementType())
								&& _costs.signum()<0){ // C- Customer Shipment - V-
							_costs = _costs.abs();
						}


						// Embarque de salida
						final BigDecimal qtyPost = Doc.DOCTYPE_MatShipment.equals(getDocType().getDocBaseType())
								&& getMovementType().charAt(1) == '-'
								?line.getQtyEntered().negate()
										:line.getQtyEntered().abs();

//						// Al ser una Entrada al almacen por devolución de cliente debe actualizar el costo
//						if(X_M_InOut.MOVEMENTTYPE_CustomerReturns.equals(getMovementType().trim())){
//						} else if(X_M_InOut.MOVEMENTTYPE_CustomerShipment.equals(getMovementType().trim())){
//						}

						if(line.getProduct().isItem())
							// Al ser una salida del almacen por venta al cliente debe actualizar las cantidades en costo
							MCostDetail.createShipment(as
									, line.getAD_Org_ID()
									, line.getM_Product_ID()
									, line.getM_AttributeSetInstance_ID()
									, line.get_ID()
									, 0
									, _costs
									, qtyPost
									, line.getDescription()
									, true // Debe actualizar el M_Cost PERO Si es venta solo las cantidades si es devolución de compra las cantidades y el costo
									, get_TrxName());
					}
				}// forEsquema contable

				// Actualizar los costos después de haber contabilizado las conciliaciones de la orden
				for (final MTransaction trxs : line.getlTransaction()) {
					trxs.updateCost(line.getProduct(), trxs.getAcctSchema());
					if(!trxs.save(get_TrxName())){
						s_log.log(Level.SEVERE, "MTransaction :"+ trxs.getM_Transaction_ID()+" Error:"+"");
					}
				}
			}// fin iterarlineas

		}//fincompra
	}

	/**
	 * Actualiza los precios de venta del producto comparando contra las versiones anteriores
	 *
	 * @param listID el id de la Lista de Precios a revisar sus versiones
	 * @param factor el objeto Factor Precio ya sea por Producto o Categoría
	 * @param product el Objeto Producto de la línea actual
	 * @param line la Línea actual de la Recepción.
	 * @return <code>true</code> si la actualización fue exitosa, de otra manera false.
	 */
	private boolean updatePriceProduct(final int listID, final MEXMEFactorPre factor, final MProduct product, final MInOutLine line){
		boolean success = true;

		BigDecimal percentageVol = BigDecimal.ZERO;
		BigDecimal percentageMin = BigDecimal.ZERO;
		boolean hasFactorVol = false;
		boolean hasFactorMin= false;

		BigDecimal precioVolumen = line.getPriceActual_Vol();
		if(line.getC_UOM_ID()==line.getC_UOMVolume_ID()){
			// La transacción es en minima
			BigDecimal cantidadEnMinima = MEXMEUOMConversion.convertProductFrom(getCtx()
					, product.getM_Product_ID()
					, product.getC_UOMVolume_ID()
					, Env.ONE
					, null
					, true);
			precioVolumen = line.getPriceActual_Vol().multiply(cantidadEnMinima);
		}

		//Obtenemos la tabla de Factor Precio
		final List<MEXMEFactorPreDet> lstFactorPreDet = MEXMEFactorPre.factorPrecioDetalle(
				getCtx(), factor.getEXME_FactorPre_ID(), null);

		BigDecimal nivelInferior = BigDecimal.ZERO;

		// obtenemos el porcentaje de incremento para el precio de volumen del producto
		for (final MEXMEFactorPreDet det: lstFactorPreDet) {
			//si es mayor que el nivel inferior y ademas
			//es igual o menor que el nivel superiorinvitar
			//se encuentra dentro del rango
			if (precioVolumen.compareTo(nivelInferior) == 1
					&& (precioVolumen.compareTo(det.getNivelSuperior()) == 0
							|| precioVolumen.compareTo(det.getNivelSuperior()) == -1) ) {
				percentageVol = det.getPorcentaje();
				hasFactorVol = true;
				break;
			}

			nivelInferior = det.getNivelSuperior();
		}


		nivelInferior = BigDecimal.ZERO;

		// obtenemos el porcentaje de incremento para el precio minimo del producto
		for(final MEXMEFactorPreDet det: lstFactorPreDet){
			//si es mayor que el nivel inferior y ademas
			//es igual o menor que el nivel superior
			//se encuentra dentro del rango
			if (line.getPriceActual().compareTo(nivelInferior) == 1
					&& (line.getPriceActual().compareTo(det.getNivelSuperior()) == 0
							|| line.getPriceActual().compareTo(det.getNivelSuperior()) == -1)) {
				percentageMin = det.getPorcentaje();
				hasFactorMin = true;
				break;
			}

			nivelInferior = det.getNivelSuperior();
		}

		//Buscar el precio del Producto en las diferentes listas de precios.
		final List<MProductPrice> lstPricesTmp = MProductPrice.getByProduct(getCtx(), product.getM_Product_ID(), true,  null);

		BigDecimal priceMaxVol = BigDecimal.ZERO;
		BigDecimal priceMinVol = BigDecimal.ZERO;

		for (final MProductPrice price:lstPricesTmp) {

			final boolean isSalesPrice = price.getM_PriceList_Version().getM_PriceList().isSOPriceList();

			//Si es lista de Precios de Venta
			if (isSalesPrice) {
				// Si la Lista de Precios de venta donde se encontró el Producto es igual a la configurada en Configuración de Precios,
				// entonces es la ultima versión encontrada y traemos sus precios.
				if (price.getM_PriceList_Version().getM_PriceList().getM_PriceList_ID() == listID) {
					priceMaxVol = price.getPriceList_Vol();
					priceMinVol = price.getPriceList();

					//Al estar ordenados por Updated, el primero que encuentra terminamos el ciclo.
					break;
				}

			}
		}

		//Obtenemos la ultima versión de lista de precios Configurada en Configuración de Precios
		MEXMEPriceListVersion lastVersion = MEXMEPriceListVersion.getLastPriceListCreated(getCtx(), listID, true);

		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		final String validoDesde = lastVersion == null ? "" : formato.format(lastVersion.getValidFrom());
		final Date fechaActual = new Date();
		final String fechaStr = formato.format(fechaActual);

		// Si existe el registro en una version de lista de precios igual a la Configurada en Configuración de Precios.
		if (priceMaxVol.compareTo(BigDecimal.ZERO) > 0) {

			if (success && hasFactorVol) {
				//si cuenta con un porcentaje de volumen, se calcula el nuevo precio del producto
				BigDecimal percentagemultiplyFactor = percentageVol.divide(new BigDecimal(100));
				percentagemultiplyFactor = percentagemultiplyFactor.add(BigDecimal.ONE);
				final BigDecimal newPrice_Vol = precioVolumen.multiply(percentagemultiplyFactor);

				//Si el nuevo precio es mayor que el actual encontrado entonces proceder.
				if (newPrice_Vol.compareTo(priceMaxVol) > 0 || factor.isPriceReduction()) {

					//Validar si existe una Lista de precios del día actual
					if (lastVersion == null || !validoDesde.equals(fechaStr)) {
						final List<MDiscountSchema> lstSchema = MDiscountSchema.getList(getCtx(), null);

						//se crea la version del día actual
						lastVersion = new MEXMEPriceListVersion(getCtx(), 0, get_TrxName());
						lastVersion.setM_PriceList_ID(listID);
						lastVersion.setName(DB.convert(getCtx(), new Timestamp(System.currentTimeMillis())).toString());
						lastVersion.setValidFrom(DB.convert(getCtx(), new Timestamp(System.currentTimeMillis())));

						if (lstSchema != null && !lstSchema.isEmpty()) {
							lastVersion.setM_DiscountSchema_ID(lstSchema.get(0).getM_DiscountSchema_ID());
						}

						lastVersion.setIsActive(true);
						if (!lastVersion.save(get_TrxName())) {
							success = false;
						}
					} //Si existe LP del día actual

					MEXMEProductPrice productPrice = MEXMEProductPrice.get(getCtx(), product.getM_Product_ID()
							, lastVersion.getM_PriceList_Version_ID(), get_TrxName());

					if (productPrice == null) {
						productPrice = new MEXMEProductPrice(getCtx(), 0, get_TrxName());
						productPrice.setM_Product_ID(product.getM_Product_ID());
						productPrice.setC_UOM_ID(product.getC_UOM_ID());
						productPrice.setC_UOMVolume_ID(product.getC_UOMVolume_ID());
						productPrice.setM_PriceList_Version_ID(lastVersion.getM_PriceList_Version_ID());
					}

					//Actualizar el precio con el nuevo.
					productPrice.setPriceList_Vol(newPrice_Vol);
					productPrice.setPriceStd_Vol(newPrice_Vol);
					productPrice.setPriceLimit_Vol(newPrice_Vol);

					//Revisar ahora los precios mínimos
					if (success && hasFactorMin) {
						//si cuenta con un porcentaje, se calcula el nuevo precio del producto
						BigDecimal percentagemultiplyFactorMin = percentageMin.divide(new BigDecimal(100));
						percentagemultiplyFactorMin = percentagemultiplyFactorMin.add(BigDecimal.ONE);
						final BigDecimal newPriceMin = line.getPriceActual().multiply(percentagemultiplyFactorMin);

						//Si el nuevo precio minimo es mayor que el actual encontrado
						if (newPriceMin.compareTo(priceMinVol) > 0 || factor.isPriceReduction()) {

							//Actualizar el precio con el nuevo.
							productPrice.setPriceList(newPriceMin);
							productPrice.setPriceStd(newPriceMin);
							productPrice.setPriceLimit(newPriceMin);
						} else {
							//Poner precio anterior ya que no se aumentó el mínimo y se convertiría en el before save de MProductPrice, dejar el q tenía la lista de venta.
							productPrice.setPriceList(priceMinVol);
							productPrice.setPriceStd(priceMinVol);
							productPrice.setPriceLimit(priceMinVol);
						}
					}

					if (!productPrice.save(get_TrxName())) {
						success = false;
					}

				} //Fin precio volumen mayor al actual

			} // Fin success y hasFactorVol

		} else {  //if si existe registro en la lista de precios
			//Si no existe el registro en una version de una lista de precios, entonces crearlo.

			//Validar si existe una Lista de precios del día actual
			if (lastVersion == null || !validoDesde.equals(fechaStr)) {
				final List<MDiscountSchema> lstSchema = MDiscountSchema.getList(Env.getCtx(), null);

				//se crea la version del día actual
				lastVersion = new MEXMEPriceListVersion(Env.getCtx(), 0, null);
				lastVersion.setM_PriceList_ID(listID);
				lastVersion.setName(DB.convert(Env.getCtx(), new Timestamp(System.currentTimeMillis())).toString());
				lastVersion.setValidFrom(DB.convert(Env.getCtx(), new Timestamp(System.currentTimeMillis())));

				if (lstSchema != null && !lstSchema.isEmpty()) {
					lastVersion.setM_DiscountSchema_ID(lstSchema.get(0).getM_DiscountSchema_ID());
				}

				lastVersion.setIsActive(true);

				if (!lastVersion.save(get_TrxName())) {
					success = false;
				}
			} //Si existe LP del día actual


			final MEXMEProductPrice productPrice = new MEXMEProductPrice(getCtx(), 0, null);

			productPrice.setM_Product_ID(product.getM_Product_ID());
			productPrice.setC_UOM_ID(product.getC_UOM_ID());
			productPrice.setC_UOMVolume_ID(product.getC_UOMVolume_ID());
			productPrice.setM_PriceList_Version_ID(lastVersion.getM_PriceList_Version_ID());

			if (success && hasFactorVol) {
			//si cuenta con un porcentaje de volumen, se calcula el nuevo precio del producto

				BigDecimal percentagemultiplyFactor = percentageVol.divide(new BigDecimal(100));
				percentagemultiplyFactor = percentagemultiplyFactor.add(BigDecimal.ONE);
				final BigDecimal newPrice_Vol = precioVolumen.multiply(percentagemultiplyFactor);

				productPrice.setPriceList_Vol(newPrice_Vol);
				productPrice.setPriceStd_Vol(newPrice_Vol);
				productPrice.setPriceLimit_Vol(newPrice_Vol);
			}

			if (success && hasFactorMin) {
				//si cuenta con un porcentaje, se calcula el nuevo precio del producto
				BigDecimal percentagemultiplyFactorMin = percentageMin.divide(new BigDecimal(100));
				percentagemultiplyFactorMin = percentagemultiplyFactorMin.add(BigDecimal.ONE);
				final BigDecimal newPriceMin = line.getPriceActual().multiply(percentagemultiplyFactorMin);

				//Actualizar el precio con el nuevo.
				productPrice.setPriceList(newPriceMin);
				productPrice.setPriceStd(newPriceMin);
				productPrice.setPriceLimit(newPriceMin);

			}

			if (!productPrice.save(get_TrxName())) {
				success = false;
			}

		} //Fin Si no existe el registro en una version de una lista de precios.

		return success;
	}


	/**
	 * Check Material Policy Sets line ASI
	 */
	private void checkMaterialPolicy() {
		final int no = MInOutLineMA.deleteInOutMA(getM_InOut_ID(), get_TrxName());
		if (no > 0) {
			log.config("Delete old #" + no);
		}
		final MInOutLine[] lines = getLines(false);

		// Incoming Trx
		final String MovementType = getMovementType();
		final boolean inTrx = MovementType.charAt(1) == '+'; // V+ Vendor Receipt
		final MClient client = MClient.get(getCtx());

		// Check Lines
		for (final MInOutLine line2 : lines) {
			final MInOutLine line = line2;
			boolean needSave = false;
			final MProduct product = line.getProduct();

			// Need to have Location
			if (product != null && line.getM_Locator_ID() == 0) {
				line.setM_Warehouse_ID(getM_Warehouse_ID());
				line.setM_Locator_ID(inTrx ? Env.ZERO : line.getMovementQty()); // default
				// Locator
				needSave = true;
			}

			// Attribute Set Instance
			if (product != null && line.getM_AttributeSetInstance_ID() == 0 && !inTrx) {
//				if (inTrx) {// Se comenta ya que actualmente el lote se crea previamente
//			....
					final MProductCategory pc = MProductCategory.get(getCtx(),
							product.getM_Product_Category_ID());
					String MMPolicy = pc.getMMPolicy();
					if (MMPolicy == null || MMPolicy.length() == 0) {
						MMPolicy = client.getMMPolicy();
					}
					//
					final MStorage[] storages = new MStorage[]{};
//							MStorage.getAllWithASI(getCtx(), line
//							.getM_Product_ID(), line.getM_Locator_ID(),
//							X_AD_Client.MMPOLICY_FiFo.equals(MMPolicy),
//							get_TrxName());
					BigDecimal qtyToDeliver = line.getMovementQty();
					for (int ii = 0; ii < storages.length; ii++) {
						final MStorage storage = storages[ii];
						if (ii == 0) {
							if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0) {
								line.setM_AttributeSetInstance_ID(storage
										.getM_AttributeSetInstance_ID());
								needSave = true;
								log.config("Direct - " + line);
								qtyToDeliver = Env.ZERO;
							} else {
								log.config("Split - " + line);
								final MInOutLineMA ma = new MInOutLineMA(line,
										storage.getM_AttributeSetInstance_ID(),
										storage.getQtyOnHand());
								if (!ma.save()) {
									s_log.log(Level.SEVERE, "MInOut checkMaterialPolicy() 1");
								}
								qtyToDeliver = qtyToDeliver.subtract(storage
										.getQtyOnHand());
								log.fine("#" + ii + ": " + ma
										+ ", QtyToDeliver=" + qtyToDeliver);
							}
						} else // create addl material allocation
						{
							final MInOutLineMA ma = new MInOutLineMA(line, storage
									.getM_AttributeSetInstance_ID(),
									qtyToDeliver);
							if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0) {
								qtyToDeliver = Env.ZERO;
							} else {
								ma.setMovementQty(storage.getQtyOnHand());
								qtyToDeliver = qtyToDeliver.subtract(storage
										.getQtyOnHand());
							}
							if (!ma.save()) {
								s_log.log(Level.SEVERE, "MInOut checkMaterialPolicy() 2");
							}
							log.fine("#" + ii + ": " + ma + ", QtyToDeliver="
									+ qtyToDeliver);
						}
						if (qtyToDeliver.signum() == 0) {
							break;
						}
					} // for all storages

					// No AttributeSetInstance found for remainder
					if (qtyToDeliver.signum() != 0) {
						final MInOutLineMA ma = new MInOutLineMA(line, 0,
								qtyToDeliver);
						if (!ma.save()) {
							s_log.log(Level.SEVERE, "MInOut checkMaterialPolicy() 3");
						}
						log.fine("##: " + ma);
					}
//				} // outgoing Trx
			} // attributeSetInstance

			if (needSave && !line.save()) {
				log.severe("NOT saved " + line);
			}
		} // for all lines
	} // checkMaterialPolicy

	/**************************************************************************
	 * Create Counter Document
	 *
	 * @return InOut
	 */
	private MInOut createCounterDoc() {
		// Is this a counter doc ?
		if (getRef_InOut_ID() != 0) {
			return null;
		}

		// Org Must be linked to BPartner
		final MOrg org = MOrg.get(getCtx(), getAD_Org_ID());
		final int counterC_BPartner_ID = org.getLinkedC_BPartner_ID();
		if (counterC_BPartner_ID == 0) {
			return null;
		}
		// Business Partner needs to be linked to Org
		final MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), null);
		final int counterAD_Org_ID = bp.getAD_OrgBP_ID_Int();
		if (counterAD_Org_ID == 0) {
			return null;
		}

		final MBPartner counterBP = new MBPartner(getCtx(), counterC_BPartner_ID,
				null);
		final MOrgInfo counterOrgInfo = MOrgInfo.get(getCtx(), counterAD_Org_ID);
		log.info("Counter BP=" + counterBP.getName());

		// Document Type
		int C_DocTypeTarget_ID;
		final MDocTypeCounter counterDT = MDocTypeCounter.getCounterDocType(getCtx(),
				getC_DocType_ID());
		if (counterDT == null) {
			C_DocTypeTarget_ID = MDocTypeCounter.getCounterDocType_ID(getCtx(), getC_DocType_ID());
			log.fine("Indirect C_DocTypeTarget_ID=" + C_DocTypeTarget_ID);
			if (C_DocTypeTarget_ID <= 0) {
				return null;
			}

		} else {// indirect
			log.fine(counterDT.toString());
			if (!counterDT.isCreateCounter() || !counterDT.isValid()) {
				return null;
			}
			C_DocTypeTarget_ID = counterDT.getCounter_C_DocType_ID();
		}

		// Deep Copy
		final MInOut counter = copyFrom(this, getMovementDate(), C_DocTypeTarget_ID,
				!isSOTrx(), true, get_TrxName(), true, null);

		//
		counter.setAD_Org_ID(counterAD_Org_ID);
		counter.setM_Warehouse_ID(counterOrgInfo.getM_Warehouse_ID());
		//
		counter.setBPartner(counterBP);
		// Refernces (Should not be required
		counter.setSalesRep_ID(getSalesRep_ID());
		counter.save(get_TrxName());

		final String MovementType = counter.getMovementType();
		final boolean inTrx = MovementType.charAt(1) == '+'; // V+ Vendor Receipt

		// Update copied lines
		final MInOutLine[] counterLines = counter.getLines(true);
		for (final MInOutLine counterLine : counterLines) {
			counterLine.setClientOrg(counter);
			counterLine.setM_Warehouse_ID(counter.getM_Warehouse_ID());
			counterLine.setM_Locator_ID(0);
			counterLine.setM_Locator_ID(inTrx ? Env.ZERO : counterLine
					.getMovementQty());
			//
			counterLine.save(get_TrxName());
		}

		log.fine(counter.toString());

		// Document Action
		if (counterDT != null && counterDT.getDocAction() != null) {
			counter.setDocAction(counterDT.getDocAction());
			counter.processIt(counterDT.getDocAction());
			counter.save(get_TrxName());
		}
		return counter;
	} // createCounterDoc

	/**
	 * Void Document.
	 *
	 * @return true if success
	 */
	@Override
	public boolean voidIt() {
		log.info(toString());

		if (DOCSTATUS_Closed.equals(getDocStatus())
				|| DOCSTATUS_Reversed.equals(getDocStatus())
				|| DOCSTATUS_Voided.equals(getDocStatus())) {
			m_processMsg = "Document Closed: " + getDocStatus();
			return false;
		}

		// Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
				|| DOCSTATUS_Invalid.equals(getDocStatus())
				|| DOCSTATUS_InProgress.equals(getDocStatus())
				|| DOCSTATUS_Approved.equals(getDocStatus())
				|| DOCSTATUS_NotApproved.equals(getDocStatus())) {
			// Set lines to 0
			final MInOutLine[] lines = getLines(false);
			for (final MInOutLine line : lines) {
				final BigDecimal old = line.getMovementQty();
				if (old.signum() != 0) {
					line.setQty(Env.ZERO);
					line.addDescription("Void (" + old + ")");
					line.save(get_TrxName());
				}
			}
		} else {
			return reverseCorrectIt();
		}

		setProcessed(true);
		m_processMsg = Utilerias.getLabel("egresos.reg.docstatus").concat(" ").concat(Utilerias.getLabel(getDocAction()));
		setDocAction(DOCACTION_None);
		releaseOrder();
		return true;
	} // voidIt

	/**
	 * Close Document.
	 *
	 * @return true if success
	 */
	@Override
	public boolean closeIt() {
		log.info(toString());
		setProcessed(true);
		m_processMsg = Utilerias.getLabel("egresos.reg.docstatus").concat(" ").concat(Utilerias.getLabel(getDocAction()));
		setDocAction(DOCACTION_None);
		releaseOrder();
		return true;
	} // closeIt

	/**
	 * Reverse Correction - same date
	 *
	 * @return true if success
	 */
	@Override
	public boolean reverseCorrectIt() {
		log.info(toString());
		final MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType(), getAD_Org_ID())) {
			m_processMsg = "@PeriodClosed@";
			return false;
		}

		// Reverse/Delete Matching
		if (!isSOTrx()) {
			final MMatchInv[] mInv = MMatchInv.getInOut(getCtx(), getM_InOut_ID(),
					get_TrxName());
			for (final MMatchInv element : mInv) {
				element.delete(true);
			}
			final MMatchPO[] mPO = MMatchPO.getInOut(getCtx(), getM_InOut_ID(),
					get_TrxName());
			for (final MMatchPO element : mPO) {
				if (element.getC_InvoiceLine_ID() == 0) {
					element.delete(true);
				} else {
					element.setM_InOutLine_ID(0);
					element.save();

				}
			}
		}

		// Deep Copy
		final MInOut reversal = copyFrom(this, getMovementDate(), getC_DocType_ID(),
				isSOTrx(), false, get_TrxName(), true, null);
		if (reversal == null) {
			m_processMsg = "Could not create Ship Reversal";
			return false;
		}
		reversal.setReversal(true);

		// Reverse Line Qty
		final MInOutLine[] sLines = getLines(false);
		final MInOutLine[] rLines = reversal.getLines(false);
		for (int i = 0; i < rLines.length; i++) {
			final MInOutLine rLine = rLines[i];
			rLine.setQtyEntered(rLine.getQtyEntered().negate());
			rLine.setMovementQty(rLine.getMovementQty().negate());
			rLine.setQtyEntered_Vol(rLine.getQtyEntered_Vol().negate());
			rLine.setMovementQty_Vol(rLine.getMovementQty_Vol().negate());
			rLine.setM_AttributeSetInstance_ID(sLines[i]
					.getM_AttributeSetInstance_ID());
			if (!rLine.save(get_TrxName())) {
				m_processMsg = "Could not correct Ship Reversal Line";
				return false;
			}
			// We need to copy MA
			if (rLine.getM_AttributeSetInstance_ID() == 0) {
				final MInOutLineMA mas[] = MInOutLineMA.get(getCtx(), sLines[i]
						.getM_InOutLine_ID(), get_TrxName());
				for (final MInOutLineMA ma2 : mas) {
					final MInOutLineMA ma = new MInOutLineMA(rLine, ma2
							.getM_AttributeSetInstance_ID(), ma2
							.getMovementQty().negate());
					if (!ma.save()) {
						s_log.log(Level.SEVERE, "MInOut reverseCorrectIt()");
					}
				}
			}
			// De-Activate Asset
			final MAsset asset = MAsset.getFromShipment(getCtx(), sLines[i]
					.getM_InOutLine_ID(), get_TrxName());
			if (asset != null) {
				asset.setIsActive(false);
				asset.addDescription("(" + reversal.getDocumentNo() + " #"
						+ rLine.getLine() + "<-)");
				asset.save();
			}
		}
		reversal.setC_Order_ID(getC_Order_ID());
		reversal.addDescription("{->" + getDocumentNo() + ")");
		//
		if (!reversal.processIt(DocAction.ACTION_Complete)
				|| !reversal.getDocStatus().equals(DocAction.STATUS_Completed)) {
			m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
			return false;
		}
		reversal.closeIt();
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.save(get_TrxName());
		//
		addDescription("(" + reversal.getDocumentNo() + "<-)");

		m_processMsg = reversal.getDocumentNo();
		setProcessed(true);
		setDocStatus(DOCSTATUS_Reversed); // may come from void
		setDocAction(DOCACTION_None);
		return true;
	} // reverseCorrectionIt

	/**
	 * Reverse Accrual - none
	 *
	 * @return false
	 */
	@Override
	public boolean reverseAccrualIt() {
		log.info(toString());
		return false;
	} // reverseAccrualIt

	/**
	 * Re-activate
	 *
	 * @return false
	 */
	@Override
	public boolean reActivateIt() {
		log.info(toString());
		return false;
	} // reActivateIt

	/*************************************************************************
	 * Get Summary
	 *
	 * @return Summary of Document
	 */
	@Override
	public String getSummary() {
		final StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo())
		// : Total Lines = 123.00 (#1)
//		.append(":")
		// .append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
				.append(": (#").append(getLines(false).length).append(")");
		// - Description
		if (getDescription() != null && getDescription().length() > 0) {
			sb.append(" - ").append(getDescription());
		}
		return sb.toString();
	} // getSummary

	/**
	 * Get Process Message
	 *
	 * @return clear text error message
	 */
	@Override
	public String getProcessMsg() {
		return m_processMsg;
	} // getProcessMsg

	/**
	 * Get Document Owner (Responsible)
	 *
	 * @return AD_User_ID
	 */
	@Override
	public int getDoc_User_ID() {
		return getSalesRep_ID();
	} // getDoc_User_ID

	/**
	 * Get Document Approval Amount
	 *
	 * @return amount
	 */
	@Override
	public BigDecimal getApprovalAmt() {
		return Env.ZERO;
	} // getApprovalAmt

	/**
	 * Get C_Currency_ID
	 *
	 * @return Accounting Currency
	 */
	@Override
	public int getC_Currency_ID() {
		return Env.getContextAsInt(getCtx(), "$C_Currency_ID ");
	} // getC_Currency_ID

	/**
	 * Document Status is Complete or Closed
	 *
	 * @return true if CO, CL or RE
	 */
	public boolean isComplete() {
		final String ds = getDocStatus();
		return DOCSTATUS_Completed.equals(ds) || DOCSTATUS_Closed.equals(ds)
				|| DOCSTATUS_Reversed.equals(ds);
	} // isComplete

	/**
	 * Obtiene una lista de registros
	 *
	 * @param where
	 *            filtro para query inicia con "AND"
	 * @param ctx
	 *            Contexto
	 * @return list
	 * @author rosy velazquez
	 *
	 * */
	public static List<MInOut> getMInOuts(final String where, final Properties ctx, final List<Object> params) {

		final ArrayList<MInOut> list = new ArrayList<MInOut>();
		final StringBuilder sql = new StringBuilder("SELECT * FROM M_InOut WHERE M_InOut.isActive= 'Y' ");
//TODO: Falta nivel de acceso

		if (where != null) {
			sql.append(where);
		}
		sql.append(" ORDER BY documentno desc ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (!params.isEmpty()) {
				DB.setParameters(pstmt, params);
			}

			rs = pstmt.executeQuery();
			while (rs.next()){
				list.add(new MInOut(ctx, rs, null));
			}
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}

		return list;
	} // getMInOuts

	@SuppressWarnings("unused")
	private void addDocsPostProcess(final PO doc) {
		docsPostProcess.add(doc);
	}

	public ArrayList<PO> getDocsPostProcess() {
		return docsPostProcess;
	}

	public void completeObject(){
		setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(getCtx()));
		setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
		setDeliveryRule(X_M_InOut.DELIVERYRULE_Availability);
		setDeliveryViaRule(X_M_InOut.DELIVERYVIARULE_Pickup);
		setDescription(X_M_InOut.MOVEMENTTYPE_InventoryOut+" ");
		setDocAction(X_M_InOut.DOCACTION_Complete);
		setDocStatus(X_M_InOut.DOCSTATUS_InProgress);
		setFreightAmt(Env.ZERO);
		setFreightCostRule(X_M_InOut.FREIGHTCOSTRULE_FreightIncluded);
		setIsSOTrx(true);
		setC_Order_ID(0);
		setC_Invoice_ID(0);
		//setM_RMA_ID(m_rma.getM_RMA_ID());
		//setC_Project_ID(originalIO.getC_Project_ID());
		//setC_Campaign_ID(originalIO.getC_Campaign_ID());
		//setC_Activity_ID(originalIO.getC_Activity_ID());
		//setUser1_ID(originalIO.getUser1_ID());
		//setUser2_ID(originalIO.getUser2_ID());
		setMovementType(isSOTrx()?X_M_InOut.MOVEMENTTYPE_CustomerShipment:X_M_InOut.MOVEMENTTYPE_VendorReceipts);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param M_InOut_ID
	 * @param M_warehouse_ID
	 * @param trxName
	 */
	public MInOut(final Properties ctx, final int M_InOut_ID, final int M_warehouse_ID, final String trxName) {
		this(ctx, M_InOut_ID, trxName);
		if(M_InOut_ID==0){
			setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
			setM_Warehouse_ID(M_warehouse_ID);
			setIsSOTrx(true);
			setC_DocType_ID(MEXMEDocType.getOfName(getCtx(), MM_SHIPMENT, get_TrxName()));
			setFreightAmt(Env.ZERO);
			setMovementType(MOVEMENTTYPE_CustomerShipment);
			setC_Charge_ID(0);
			setChargeAmt(Env.ZERO);
			setDocAction(DOCACTION_Complete);
		}
	}



	public boolean isCreatedFromActPacienteIndH() {
		return m_IsFromActPacienteIndH;
	}

	public void setIsCreatedFromActPacienteIndH(final boolean isFromActPacienteIndH) {
		m_IsFromActPacienteIndH = isFromActPacienteIndH;
	}

//	public MEXMEActPacienteIndH getActPacienteIndH() {
//		return m_ActPacienteIndH;
//	}
//
//	public void setActPacienteIndH(final MEXMEActPacienteIndH actPacienteIndH) {
//		m_ActPacienteIndH = actPacienteIndH;
//	}

	public String getM_processMsg() {
		return m_processMsg;
	}


	/**
	 * Order Constructor - create header only
	 *
	 * @param order
	 *            order
	 * @param movementDate
	 *            optional movement date (default today)
	 * @param C_DocTypeShipment_ID
	 *            document type or 0
	 */
	public MInOut(final MEXMEActPacienteIndH indH, final String trxName) {
		this(indH.getCtx(), 0, indH.getM_Warehouse_ID(), trxName);
		setIsCreatedFromActPacienteIndH(true);
		setClientOrg(indH);
		setC_BPartner_ID(indH.getC_BPartner_ID());
		setEXME_CtaPac_ID(indH.getEXME_CtaPac_ID());
		setMovementDate(indH.getMovementDate()==null?new Timestamp(System.currentTimeMillis()):indH.getMovementDate());
		setDateAcct(getMovementDate());
		setDateOrdered(indH.getDateOrdered());
		setDescription(indH.getDescription()==null || indH.getDescription().contains("null")?"":indH.getDescription());

		// Direccion de la cuenta paciente o del paciente
		MBPartnerLocation mBPLocation = null;
		final MBPartnerLocation locationPac = MEXMEBPartner.getLocationPac(getCtx(),
				indH.getAD_Client_ID(), indH.getC_BPartner_ID(), get_TrxName());

		if(indH.getCtaPac()!=null && indH.getCtaPac().getC_BPartner_ID()>0){
			mBPLocation = MEXMEBPartner.getLocations(getCtx(), indH.getCtaPac().getC_BPartner_ID(), get_TrxName());
		}
		if(mBPLocation==null && locationPac != null){
			mBPLocation = locationPac;
		}
		if (mBPLocation!=null && mBPLocation.getC_BPartner_Location_ID()>0){
			setC_BPartner_Location_ID(mBPLocation.getC_BPartner_Location_ID());
		}
		setAD_OrgTrx_ID(indH.getAD_OrgTrx_ID());
		setDocAction(indH.isComplete() ? X_M_InOut.DOCACTION_Complete : X_M_InOut.DOCACTION_Prepare);
//		setActPacienteIndH(indH);
		setEXME_ActPacienteIndH_ID(indH.getEXME_ActPacienteIndH_ID());
	} // MInOut

	/**
	 * Order Constructor - create header only
	 *
	 * @param order
	 *            order
	 * @param movementDate
	 *            optional movement date (default today)
	 * @param C_DocTypeShipment_ID
	 *            document type or 0
	 */
	public MInOut(final MEXMECtaPac ctaPac, final int M_Warehouse_ID,
			final Timestamp movementDate, final boolean devolucion) {
		this(ctaPac.getCtx(), 0, M_Warehouse_ID, ctaPac.get_TrxName());
		setIsCreatedFromActPacienteIndH(false);
		setClientOrg(ctaPac);
		setC_BPartner_ID(ctaPac.getC_BPartner_ID());
		setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
		setMovementDate(movementDate == null ? Env.getCurrentDate() : movementDate);
		setDateAcct(getMovementDate());
		setDateOrdered(getMovementDate());
		// Error en produccion: la OrgTrx es obligatoria, para el caso de CDiario es -1 .- Lama
		// se valida antes si hay valor en el contexto
		int orgTrxId = Env.getAD_OrgTrx_ID(getCtx());
		if(orgTrxId > 0) {
			setAD_OrgTrx_ID(orgTrxId);
		}
		// Direccion de la cuenta paciente o del paciente
		MBPartnerLocation mBPLocation = null;
		if(ctaPac.getC_BPartner_ID()>0){
			mBPLocation = MEXMEBPartner.getLocations(getCtx(), ctaPac.getC_BPartner_ID(), get_TrxName());
		}

		final MBPartnerLocation locationPac = MEXMEBPartner.getLocationPac(getCtx(),
				ctaPac.getAD_Client_ID(), ctaPac.getC_BPartner_ID(), get_TrxName());
		if(mBPLocation==null && locationPac != null){
			mBPLocation = locationPac;
		}
		if (mBPLocation!=null && mBPLocation.getC_BPartner_Location_ID()>0){
			setC_BPartner_Location_ID(mBPLocation.getC_BPartner_Location_ID());
		}

		// Alejandro.- Si es devolucion asignar el tipo de Movimiento C+, de lo
		// contrario es embarque, asignar el tipo de movimiento (C-)
		if (devolucion) {
			setMovementType(MOVEMENTTYPE_CustomerReturns);
			setDescription(Msg.getMsg(getCtx(), Env.getLanguage(getCtx())
					.getLocale(), "msj.ctaPac.devol", new String[] { ctaPac
				.getDocumentNo() }));
		} else {
//			setMovementType(MOVEMENTTYPE_CustomerShipment);
			setDescription(Msg.getMsg(getCtx(), Env.getLanguage(getCtx())
					.getLocale(), "msj.ctaPac.cargo", new String[] { ctaPac
				.getDocumentNo() }));
		}
	} // MInOut

	/** Embarque desde factura, sin orden de compra */
	public MInOut(final MInvoice mInvoice, final MWarehouse wh) {
		this(mInvoice.getCtx(), 0, wh.getM_Warehouse_ID(), mInvoice.get_TrxName());
		setIsCreatedFromActPacienteIndH(false);
		setClientOrg(mInvoice);
		setC_BPartner_ID(mInvoice.getC_BPartner_ID());
		setC_BPartner_Location_ID(mInvoice.getC_BPartner_Location_ID());
		setEXME_CtaPac_ID(mInvoice.getEXME_CtaPac_ID());
		setMovementDate(mInvoice.getDateInvoiced()==null?new Timestamp(System.currentTimeMillis()):mInvoice.getDateInvoiced());
		setDateAcct(getMovementDate());
		setDateOrdered(getMovementDate());
		setDateReceived(getMovementDate());
		setDescription(Msg.getMsg(getCtx(), Env.getLanguage(getCtx())
				.getLocale(), "msj.ctaPac.cargo", new String[] { mInvoice
			.getDocumentNo() }));
		completeObject();//solo ventas
		setC_Invoice_ID(mInvoice.getC_Invoice_ID());
	} // MInOut
	/**
	 * Obtener las Recepciones de una Orden de Compra
	 * @param ctx
	 * @param orderID
	 * @param trxName
	 * @return ID de la Recepcion
	 **/
	public static int getFromOrder(final Properties ctx, final int orderID, final String trxName){
		int inOutID = 0;
		final StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT M_InOut_ID FROM M_InOut WHERE C_Order_ID = ?");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, orderID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				inOutID = rs.getInt("M_InOut_ID");
			}

			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return inOutID;
	}

	/**
	 *
	 * @param ctx
	 * @param C_Invoice_ID
	 * @param trxName
	 * @return
	 */
	public String entradaInventarioPorCancelacion(final Properties ctx,
			final MInvoice nota, final String trxName) {
		final String errores = "";
		final Timestamp now = new Timestamp(System.currentTimeMillis());
		// Afectar inventario
		// Traer el embarque relacionado a la factura

		// crear una copia a partir de original
		MInOut devol = null;
		try {
			devol = MInOut.copyFrom(this, now, getC_DocType_ID(), true, true, trxName, false, nota);
		} catch (final Exception e) {
			return Utilerias.getAppMsg(getCtx(), "error.caja.inout.copia");
		}
		// Tipo de movimiento Entrada
		devol.setMovementType(X_M_InOut.MOVEMENTTYPE_CustomerReturns);

		if(nota!=null)
			devol.setC_Invoice_ID(nota.getC_Invoice_ID());

		// Guardamos el Embarque.
		if (!devol.save(trxName)) {
			return Utilerias.getAppMsg(getCtx(), "error.factDirecta.facturar.noSaveInOut", MedsysException.getMessageFromLogger());
		}

		// Completamos los InOut.
		final String status = devol.completeIt();
		devol.setDocStatus(status);
		if (!status.equals(DocAction.STATUS_Completed)) {
			// No se completo el Embarque.
			return Utilerias.getAppMsg(getCtx(), "error.factDirecta.facturar.noInOut", devol.getProcessMsg());
		} else {
			if (devol.getDocStatus().equals(DocAction.STATUS_Completed)) {
				devol.setDocAction(DocAction.ACTION_Close);
			}
			if (!devol.save(trxName)) {
				return Utilerias.getAppMsg(getCtx(), "error.factDirecta.facturar.noSaveInOut", MedsysException.getMessageFromLogger());
			}
		}
		return errores;
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
//	private static MInOut copyFrom(final MInOut from, final Timestamp dateDoc,
//			final boolean isSOTrx, final boolean counter, final String trxName, final boolean setOrder, final MInvoice mNota)
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
//		if (to.copyLinesFrom(from, counter, setOrder, mNota) == 0) {
//			throw new IllegalStateException("Could not create Shipment Lines");
//		}
//		return to;
//
//	} // copyFrom


	/** No permitir postear el movimiento cuando el almacen es de consigna */
	public void setNotPosted(){
		if(X_M_InOut.DOCSTATUS_Completed.equals(getDocStatus())){
			if(getWarehouse().isConsigna()){
				setPost(X_M_Movement.POST_Consignment);
			}
		}
	}

	/** Obtener el obj. del almacen de transaccion */
	public MWarehouse getWarehouse() {
		if (warehouse == null) {
			warehouse = new MWarehouse(getCtx(), getM_Warehouse_ID(), null);
		}
		return warehouse;
	}

	public static List<Consumption> getConsumption(Properties ctx, int partnerId, Timestamp date,boolean agrupados){
		final ArrayList<Consumption> lst = new ArrayList<Consumption>();
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();


		params.add("Y");
		params.add(partnerId);
		params.add(date);
		params.add("N");
		params.add(DOCSTATUS_Completed);
		params.add(X_C_Order.DOCSTATUS_Completed);//CO
		params.add("Y");

//		sql.append("SELECT ");
//		sql.append("  ord.c_order_id, ");
//		sql.append("  lin.c_orderline_id, ");
//		sql.append("  lin.m_inout_id, ");
//		sql.append("  lin.m_inoutline_id, ");
//		sql.append("  ino.movementdate, ");
//		sql.append("  prod.m_product_id, ");
//		sql.append("  prod.value, ");
//		sql.append("  prod.name, ");
//		sql.append("  ino.m_inventory_id, ");
//		sql.append("  lin.movementqty_vol ");
//		sql.append("  AS qty, ");
//		sql.append("  uom.c_uom_id, ");
//		sql.append("  uom.name ");
//		sql.append("  AS uom, ");
//		sql.append("  lin.priceactual_vol ");
//		sql.append("  AS price,(lin.movementqty_vol * priceactual_vol) ");
//		sql.append("  AS total ");
//		sql.append("FROM ");
//		sql.append("  m_inout ino ");
//		sql.append("  INNER JOIN m_inoutline lin ");
//		sql.append("  ON lin.m_inout_id = ino.m_inout_id ");
//		sql.append("  INNER JOIN m_product prod ");
//		sql.append("  ON lin.m_product_id = prod.m_product_id ");
//		sql.append("  INNER JOIN c_order ord ");
//		sql.append("  ON ord.c_order_id = ino.c_order_id ");
//		sql.append("  INNER JOIN c_uom uom ");
//		sql.append("  ON uom.c_uom_id = lin.c_uomvolume_id ");
//		sql.append("WHERE ");
//		sql.append("  ino.isactive = 'Y' AND ");
//		sql.append("  ino.c_bpartner_id = ? AND ");
//		sql.append("  ino.movementdate <= ? AND ");
//		sql.append("  ino.issotrx = ? AND ");
//		sql.append("  ino.docstatus = ? AND ");
//		sql.append("  ord.docstatus = ? AND ");
//		sql.append("  ord.isconsigna = ? AND ");
//		sql.append("  ino.c_invoice_id IS NULL ");
//
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "ino"));
//
//		sql.append(" order by ino.documentno desc ");


		if (agrupados) {
			sql.append("SELECT ");
			sql.append("  name, ");
			sql.append("  uom_volume, ");
			sql.append("  price, ");
			sql.append("  value, ");
			sql.append("  SUM(qty_vol) ");
			sql.append("  AS qty_vol, ");
			sql.append("  uom, ");
			sql.append("  SUM(total) ");
			sql.append("  AS total ");
			sql.append("FROM ");
			sql.append("  ( ");
		}
		sql.append("WITH match_inv AS ( \n")
		.append("	SELECT M_InOutLine_ID, SUM(Qty) as mi_qty \n")
		.append("	FROM m_matchinv \n")
		.append("	GROUP BY M_InOutLine_ID \n")
		.append(")\n")
		.append("SELECT \n")
		.append("	ord.c_order_id, \n")
		.append("	ord.documentno as documentNo, \n")
		.append("   lin.ad_orgtrx_id,\n")
		.append("	lin.c_orderline_id, \n")
		.append("	lin.m_inout_id, \n")
		.append("	lin.m_inoutline_id, \n")
		.append("	ino.movementdate, \n")
		.append("	prod.m_product_id, \n")
		.append("	prod.value, \n")
		.append("	prod.name, \n")
		.append("	ino.m_inventory_id, \n")
		.append("	inv.inventory, \n")
		.append("	uom1.c_uom_id as c_uomvolume_id, \n")
		.append("	uom1.name AS uom_volume, \n")
		.append("	lin.movementqty_vol as qty_vol, \n")
		.append("	uom2.c_uom_id as c_uom_id, \n")
		.append("	uom2.name AS uom, \n")
		.append("	lin.MovementQty - coalesce(mi.mi_qty, 0) as qty, \n")
		.append("	lin.priceactual_vol AS price, \n")
		.append("	(lin.movementqty_vol * priceactual_vol) AS total \n")
		.append("FROM m_inout ino  \n")
		.append("	INNER JOIN m_inoutline lin ON (lin.m_inout_id = ino.m_inout_id) \n")
		.append("	INNER JOIN m_product prod ON (lin.m_product_id = prod.m_product_id) \n")
		.append("	INNER JOIN c_order ord ON (ord.c_order_id = ino.c_order_id) \n")
		.append("	INNER JOIN c_uom uom1 ON (uom1.c_uom_id = lin.c_uomvolume_id) \n")
		.append("	INNER JOIN c_uom uom2 ON (uom2.c_uom_id = lin.c_uom_id) \n")
		.append("	LEFT JOIN match_inv mi ON (lin.M_InOutLine_ID=mi.M_InOutLine_ID) \n")
		.append("	LEFT JOIN m_inventory inv on (ino.m_inventory_id = inv.m_inventory_id) \n")
		.append("WHERE \n")
		//.append("	(inv.inventory IS NULL OR inv.inventory = 'N') AND \n")
		.append("	ino.isactive = ? AND \n")
		.append("	ino.c_bpartner_id = ? AND \n")
		.append("	ino.movementdate <= ? AND \n")
		.append("	ino.issotrx = ? AND \n")
		.append("	ino.docstatus = ? AND  \n")
		.append("	ino.c_invoice_id IS NULL AND \n")
		.append("	ord.docstatus = ? AND  \n")
		.append("	ord.isconsigna = ? \n");

		sql.append(
				MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "ino")
		).append('\n');
		sql.append("ORDER BY ino.documentno desc");
		if (agrupados) {
			sql.append(")  aGroup ");
			sql.append("Group by name, uom_volume, price, value, uom ");

		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final Consumption consumption = new Consumption();
				if (!agrupados) {
					consumption.setInOutId(rs.getInt("m_inout_id"));
					consumption.setInOutLineId(rs.getInt("m_inoutline_id"));
					consumption.setOrderId(rs.getInt("c_order_id"));
					consumption.setTrasnOrg(rs.getInt("ad_orgtrx_id"));
					consumption.setOrderLineId(rs.getInt("c_orderline_id"));
					consumption.setDocumentNo(rs.getString("documentNo"));
					consumption.setMovementDate(rs.getTimestamp("movementDate"));
					consumption.setProdId(rs.getInt("m_product_id"));

				}
				consumption.setValue(rs.getString("value"));
				consumption.setName(rs.getString("name"));

				if (!agrupados) {
					if (rs.getInt("m_inventory_id") > 0) {
						if ("Y".equals(rs.getString("inventory"))) {
							consumption.setType(MRefList.getListName(ctx, 183, "MMI"));
						} else {
							consumption.setType(MRefList.getListName(ctx, 189, "S+"));
						}
					} else {
						consumption.setType(Msg.getMsg(ctx, "ConsumptionReturn"));
					}
				}

				if(!agrupados){
					consumption.setQty(rs.getBigDecimal("qty"));
				}
				consumption.setQtyVol(rs.getBigDecimal("qty_vol"));
				if (!agrupados) {
					consumption.setUomId(rs.getInt("c_uom_id"));
				}
				consumption.setUom(rs.getString("uom"));
				if (!agrupados) {
					consumption.setUomVolumeId(rs.getInt("c_uomvolume_id"));
				}
				consumption.setUomVolume(rs.getString("uom_volume"));
				consumption.setPrice(rs.getBigDecimal("price"));
				consumption.setTotal(rs.getBigDecimal("total"));
				lst.add(consumption);
			}
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;

	}

	public static class Consumption{
		private Timestamp movementDate;
		private int orderId;
		private int orderLineId;
		private int inOutId;
		private int inOutLineId;
		private int prodId;
		private String value;
		private String name;
		private String type;
		private BigDecimal qty;
		private BigDecimal qtyVol;
		private int uomId;
		private int uomVolumeId;
		private String uom;
		private String uomVolume;
		private BigDecimal price;
		private BigDecimal total;
		private int trasnOrg;
		private String documentNo;

		public String getDocumentNo() {
			return documentNo;
		}
		public void setDocumentNo(String documentNo) {
			this.documentNo = documentNo;
		}
		public int getTrasnOrg() {
			return trasnOrg;
		}
		public void setTrasnOrg(int trasnOrg) {
			this.trasnOrg = trasnOrg;
		}
		public Timestamp getMovementDate() {
			return movementDate;
		}
		public void setMovementDate(final Timestamp movementDate) {
			this.movementDate = movementDate;
		}
		public int getOrderId() {
			return orderId;
		}
		public void setOrderId(final int orderId) {
			this.orderId = orderId;
		}
		public int getOrderLineId() {
			return orderLineId;
		}
		public void setOrderLineId(final int orderLineId) {
			this.orderLineId = orderLineId;
		}
		public int getInOutId() {
			return inOutId;
		}
		public void setInOutId(final int inOutId) {
			this.inOutId = inOutId;
		}
		public int getInOutLineId() {
			return inOutLineId;
		}
		public void setInOutLineId(final int inOutLineId) {
			this.inOutLineId = inOutLineId;
		}
		public int getProdId() {
			return prodId;
		}
		public void setProdId(final int prodId) {
			this.prodId = prodId;
		}
		public String getValue() {
			return value;
		}
		public void setValue(final String value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(final String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(final String type) {
			this.type = type;
		}
		public BigDecimal getQty() {
			return qty;
		}
		public void setQty(final BigDecimal qty) {
			this.qty = qty;
		}
		public int getUomId() {
			return uomId;
		}
		public void setUomId(final int uomId) {
			this.uomId = uomId;
		}
		public String getUom() {
			return uom;
		}
		public void setUom(final String uom) {
			this.uom = uom;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(final BigDecimal price) {
			this.price = price;
		}
		public BigDecimal getTotal() {
			return total;
		}
		public void setTotal(final BigDecimal total) {
			this.total = total;
		}

		public BigDecimal getQtyVol() {
			return qtyVol;
		}
		public void setQtyVol(BigDecimal qtyVol) {
			this.qtyVol = qtyVol;
		}
		public int getUomVolumeId() {
			return uomVolumeId;
		}
		public void setUomVolumeId(int uomVolumeId) {
			this.uomVolumeId = uomVolumeId;
		}
		public String getUomVolume() {
			return uomVolume;
		}
		public void setUomVolume(String uomVolume) {
			this.uomVolume = uomVolume;
		}
	}

	public static String getPosted(final int inoutId) {
		return
				DB.getSQLValueString(
						null,
						" SELECT Posted FROM M_Inout WHERE M_Inout_ID = ? ",
						new Object[]{inoutId}
				);
	}

	/**
	 * Match Requirement
	 * @param inout shipment/receipt
	 * @return String
	 * Basado en {@link Doc_InOut#loadLines(MInOut)}.
	 */
	public static String getMatchRequirementR(final MInOut inout){
		// Obtener si es requerida la conciliación antes de contabilizar
		String mMatchRequirementR = X_AD_ClientInfo.MATCHREQUIREMENTR_None;
		if (!inout.isSOTrx()){
			mMatchRequirementR = MClientInfo.get (inout.getCtx(), inout.getAD_Client_ID()).getMatchRequirementR();
			final String mr = inout.getMatchRequirementR();//Prioridad
			if (mr != null){
				mMatchRequirementR = mr;
			}
		}
		return mMatchRequirementR;
	}

	/**
	 *	Load Invoice Line
	 *	@param inout shipment/receipt
	 *  @return true Faltan conciliación
	 *  Basado en {@link Doc_InOut#loadLines(MInOut)}.
	 */
	public static boolean isNecessaryTheReconcilingLines (final MInOut inout)
	{	boolean mmatchProblem = false;

		// Obtener si es requerida la conciliación antes de contabilizar
		final String mMatchRequirementR = MInOut.getMatchRequirementR(inout);

		// Validar la linea
		final MInOutLine[] lines = inout.getLines(false);
		for (final MInOutLine line2 : lines) {
			final MInOutLine line = line2;
			if (line.isDescription()
				|| line.getM_Product_ID() == 0
				|| line.getMovementQty().signum() == 0)
			{
				continue;
			}
			//expert : gisela lee : cambios de jjanke en version 26b
			//	PO Matching
			if (mMatchRequirementR.equals (X_M_InOut.MATCHREQUIREMENTR_PurchaseOrder)
				|| mMatchRequirementR.equals (X_M_InOut.MATCHREQUIREMENTR_PurchaseOrderAndInvoice))
			{
				final BigDecimal poDiff = line.getMatchPODifference();
				if (poDiff.signum() != 0){
					mmatchProblem = true;
				} else if (!line.isMatchPOPosted()) {
					mmatchProblem = true;
				}
			}
			//	Inv Matching
			else if (mMatchRequirementR.equals (X_M_InOut.MATCHREQUIREMENTR_Invoice)
				|| mMatchRequirementR.equals (X_M_InOut.MATCHREQUIREMENTR_PurchaseOrderAndInvoice))
			{
				final BigDecimal invDiff = line.getMatchInvDifference();
				if (invDiff.signum() != 0) {
					mmatchProblem = true;
				} else if (!line.isMatchInvPosted()) {
					mmatchProblem = true;
				}
			}

		}
		return mmatchProblem;
	}	//	loadLines

	/**
	 * Liberar la OC por si aun le faltan lineas por recibir
	 */
	private void releaseOrder(){
		if (getC_Order_ID() > 0) {
			final MOrder order = (MOrder) getC_Order();
			order.setisBeingUsedInOut(false);
			order.save();
		}
	}

	/**
	 * Obtener el total de las lineas de la recepcion
	 */
	public BigDecimal getSumLines(){
		final StringBuilder sql = new StringBuilder("SELECT DISTINCT")
			.append(" CASE WHEN l.pricelist_vol > 0 THEN pricelst.pricelst ELSE priceact.priceact END")
			.append(" FROM m_inoutline l")
			.append(" INNER JOIN (SELECT m_inout_id, SUM(priceactual_vol*qtyentered_vol -((priceactual_vol * qtyentered_vol)*(discount/100))")//discount
			.append(" +((priceactual_vol*qtyentered_vol - ((priceactual_vol * qtyentered_vol)*(discount/100)))*(rate/100)))")//taxes
			.append(" AS priceact  FROM m_inoutline l INNER JOIN c_tax c ON (c.c_tax_id = l.c_tax_id) WHERE m_inout_id = ? GROUP BY m_inout_id)")
			.append(" priceact ON priceact.m_inout_id = l.m_inout_id ")
			.append(" INNER JOIN (select m_inout_id, SUM(pricelist_vol * qtyentered_vol -((pricelist_vol * qtyentered_vol)*(discount/100)) ")//discount
			.append(" +((pricelist_vol * qtyentered_vol - ((pricelist_vol * qtyentered_vol)*(discount/100)))*(rate/100)))")//taxes
			.append(" as pricelst FROM m_inoutline l INNER JOIN c_tax c ON (c.c_tax_id = l.c_tax_id) WHERE m_inout_id = ? GROUP BY m_inout_id)" )
			.append(" pricelst ON pricelst.m_inout_id = priceact.m_inout_id WHERE l.m_inout_id = ?" );
		BigDecimal value = DB.getSQLValueBD(get_TrxName(), sql.toString(), new Object[] { getM_InOut_ID(), getM_InOut_ID(), getM_InOut_ID() });
		if (value == null) {
			value = BigDecimal.ZERO;
		} else {
			try {
				value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
			} catch (final ArithmeticException e) {
				log.log(Level.SEVERE, "MinOut getSumLines() documentNo=" + getDocumentNo(), e);
				value = BigDecimal.ZERO;
			}
		}
		return value;
	}


	/**
	 * Crear una orden de compra a partir de una recepción de material
	 * cuando no exista previamente la orden
	 * @param docType
	 * @param priceList
	 * @param orderLineLst
	 * @param isWeb
	 */
	private String createOrder(final String trxName) {
		log.info("MInOut.createOrder");
		final ErrorList errors = new ErrorList();

		final MOrder mOrder = MOrder.createMaterialReceipt(errors, this, getInventoryLineForRM());
		if(errors.isEmpty()){
			if(getC_Order_ID()<=0 && mOrder!=null){
				setC_Order_ID(mOrder.getC_Order_ID());
			}
			return mOrder == null ? DOCSTATUS_Completed : mOrder.getDocStatus();

		} else {
			m_processMsg = errors.toString();
			return DocAction.ACTION_Invalidate;
		}
	}

	/** Enlistar los productos que se vana recepcionar que no provienen
	 * de una orden previa*/
	public List<TransferBean> getInventoryLineForRM() {
		final List<TransferBean> lines = new ArrayList<TransferBean>();

		final MInOutLine[] invLine = getLines (true);
		for(final MInOutLine line:invLine) {

			TransferBean mTransferBean=null;
			if(line.getC_OrderLine_ID()<=0) {
				mTransferBean = new TransferBean();
				mTransferBean.setM_Product_ID(line.getM_Product_ID());
				mTransferBean.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());

				mTransferBean.setC_UOM_ID(line.getC_UOM_ID());
				mTransferBean.setQtyEntered(line.getQtyEntered());
				mTransferBean.setQtyOrdered(line.getQtyEntered());
				mTransferBean.setPriceActual(line.getPriceActual());
				mTransferBean.setPriceList(line.getPriceList());
				mTransferBean.setPriceEntered(line.getPriceActual());
				mTransferBean.setPriceLimit(line.getPriceLimit());

				mTransferBean.setC_UOMVolume_ID(line.getC_UOMVolume_ID());// :
				mTransferBean.setQtyEntered_Vol(line.getQtyEntered_Vol());
				mTransferBean.setQtyOrdered_Vol(line.getQtyEntered_Vol());
				mTransferBean.setPriceActual_Vol(line.getPriceActual_Vol());// :
				mTransferBean.setPriceList_Vol(line.getPriceList_Vol());// :
				mTransferBean.setPriceEntered_Vol(line.getPriceActual_Vol());// :confirmar con gus

				mTransferBean.setDescription(line.getDescription());
				mTransferBean.setDatePromised(getDateReceived()==null?new Timestamp(System.currentTimeMillis()):getDateReceived());

				mTransferBean.setcTaxId(line.getC_Tax_ID());

				mTransferBean.setDiscount(line.getDiscount());
				mTransferBean.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
				mTransferBean.setInOutLine(line.getM_InOutLine_ID());
				lines.add(mTransferBean);
			}
		}

		return lines;
	}

	/** Insertar en MTransaction */
	private String insertTransaction(final MOrderLine oLine
			, final MInOutLine sLine
			, final String MovementType
			, final MProduct product
			, final BigDecimal Qty
			, final BigDecimal QtySO
			, final BigDecimal QtyPO, final MAcctSchema[] acctss ){

		log.fine("Material Transaction");
		MTransaction mtrx = null; // Expert:Hassan
		final List<MTransaction> mtrxs = new ArrayList<MTransaction>();

		// Reservation ASI - assume none
		int reservationAttributeSetInstance_ID = sLine
				.getM_AttributeSetInstance_ID();
		if (oLine != null) {
			reservationAttributeSetInstance_ID = oLine
					.getM_AttributeSetInstance_ID();
		}

		//
		if (sLine.getM_AttributeSetInstance_ID() == 0) {
			final MInOutLineMA mas[] = MInOutLineMA.get(getCtx(), sLine
					.getM_InOutLine_ID(), get_TrxName());

			//
			for (final MInOutLineMA ma2 : mas)
			{
				final MInOutLineMA ma = ma2;
				BigDecimal QtyMA = ma.getMovementQty();
//				if (MovementType.charAt(1) == '-') {
//					// Shipment - V-
//													// Vendor Return
//					QtyMA = QtyMA.negate();
//				}
//				
				// Customer Shipment = C- 
				// Customer Returns  = C+ 
				// Vendor Receipts   = V+ 
				// Vendor Returns    = V- 
				if (MovementType.charAt(1) == '-' && QtyMA.signum() > 0){//-1 (negative), 0 (zero), or 1 (positive)
					QtyMA = QtyMA.negate();
					
				}  else if (MovementType.charAt(1) == '+' && QtyMA.signum() < 0){
					QtyMA = QtyMA.abs();
				}
				BigDecimal QtySOMA = Env.ZERO;
				BigDecimal QtyPOMA = Env.ZERO;
				if (sLine.getC_OrderLine_ID() != 0) {
					if (isSOTrx()) {
						QtySOMA = ma.getMovementQty();
					} else {
						QtyPOMA = ma.getMovementQty();
					}
				}

				// Update Storage - see also VMatch.createMatchRecord
				if (isDropShip() && isSetpoint()) {// Consigna no afecta existencias
					log.fine("No debe afectar existencias");
				} else {
					if (!MStorage.add(getCtx()
							, getM_Warehouse_ID()
							, sLine.getM_Locator_ID()
							, sLine.getM_Product_ID()
							, ma.getM_AttributeSetInstance_ID()
							, reservationAttributeSetInstance_ID
							, QtyMA
							, QtySOMA.negate()
							, QtyPOMA.negate()
							, get_TrxName())) {
						m_processMsg = "Cannot correct Inventory (MA)";
						return DocAction.STATUS_Invalid;
					}
				}

				// Por esquema
				for (final MAcctSchema as : acctss) {
					// Create Transaction
					mtrx = new MTransaction(getCtx()
							, product
							, ma.getM_AttributeSetInstance_ID()
							, as
							, sLine.getAD_Org_ID()
							, product.getCostingMethod(as)
							, QtyMA
							, 0
							, false
							, MovementType
							, sLine.getM_Locator_ID()
							, getMovementDate()
							, get_TrxName());

					mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
					if (mtrx.save()) {
						mtrxs.add(mtrx);
					} else {
						m_processMsg = "Could not create Material Transaction (MA)";
						return DocAction.STATUS_Invalid;
					}
				}/* Expert:Hassan - Fin del Bloque */
			}// finfor
		}


		// sLine.getM_AttributeSetInstance_ID() != 0
		if (mtrx == null) {

			// Fallback: Update Storage - see also
			// VMatch.createMatchRecord
			if (isDropShip() && isSetpoint()) {// Consigna no afecta existencias
				log.fine("No debe afectar existencias");
			} else {
				if (!MStorage.add(getCtx()
						, getM_Warehouse_ID()
						, sLine.getM_Locator_ID()
						, sLine.getM_Product_ID()
						, sLine.getM_AttributeSetInstance_ID()
						, reservationAttributeSetInstance_ID
						, Qty
						, QtySO.negate()
						, QtyPO.negate()
						, get_TrxName())) {
					m_processMsg = "Cannot correct Inventory";
					return DocAction.STATUS_Invalid;
				}
			}

			for (final MAcctSchema as : acctss) {
				// Create Transaction
				mtrx = new MTransaction(getCtx()
						, product
						, sLine.getM_AttributeSetInstance_ID()
						, as
						, sLine.getAD_Org_ID()
						, product.getCostingMethod(as)
						, Qty
						, 0
						, false
						, MovementType
						, sLine.getM_Locator_ID()
						, getMovementDate()
						, get_TrxName());

				mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
				if (mtrx.save()) {
					mtrxs.add(mtrx);
				} else {
					m_processMsg = "Could not create Material Transaction";
					return DocAction.STATUS_Invalid;
				}
			}/* Expert:Hassan - Fin del Bloque */
		}// fin mtrx is null

		//
		sLine.setlTransaction(mtrxs);
		return null;
	}

	/** 0 Sin registros, -1 Registros negativos, 1 Registros positivos */
	public int hasNegativeRecords(int recordId){
		String where = recordId>0?" M_InoutLine_ID <> "+recordId:StringUtils.EMPTY;
		MInOutLine[]  arr = getLines(true, where);
		int records =  recordId>0?arr.length+1:arr.length;
		if(records>0){
			records = arr[0].getQtyEntered().compareTo(Env.ZERO)>=0?1:-1;
		}
		return records;
	}

	/** Linea de la salida de inventario desde una venta */
	public MInOutLine addInventariadoLines(Inventariado sLine, final String uProcess){
		if(sLine.getiCargo().getM_Locator_ID()<=0){
			return null;
		}
		final MInOutLine line = new MInOutLine(this);
		line.set_TrxName(get_TrxName());
//		line.setLine();

		if(sLine.getApIndLine()!=null){
			line.setOrderLine(sLine.getApIndLine(), 0);
			line.setEXME_ActPacienteInd_ID(sLine.getApIndLine().getEXME_ActPacienteInd_ID());
			line.setAD_OrgTrx_ID(sLine.getApIndLine().getAD_OrgTrx_ID());
			line.setDescription(line.getDescription()==null?uProcess:line.getDescription());//msj.ajustesInventario"));

		} else {
			line.setM_Product_ID(sLine.getiCargo().getProduct().getM_Product_ID());
			line.setAD_OrgTrx_ID(sLine.getiCargo().getAD_OrgTrx_ID()==0?getAD_OrgTrx_ID():sLine.getiCargo().getAD_OrgTrx_ID());
			line.setDescription(uProcess);//msj.ajustesInventario"));
		}

		line.setM_AttributeSetInstance_ID(sLine.getmAttribSetInstID());
		line.setM_Locator_ID(sLine.getiCargo().getM_Locator_ID());
		line.setC_UOM_ID(sLine.getiCargo().getProduct().getC_UOM_ID());// Unidad minima
		line.setC_UOMVolume_ID(sLine.getiCargo().getC_UOM_ID());// Unidad de venta
		//TODO Validar CAntidades
		// la cantidad se encuentra en unidad minima, se establecen las
		// cantidades y se calculan las de volumen
		line.setQty(sLine.getTargetQty()); // Qty - En unidad de almacenamiento
		//TODO: AQui poner los costos

		line.setMovementQty_Vol(sLine.getiCargo().getQtyDelivered());
		line.setQtyEntered_Vol(sLine.getiCargo().getQtyDelivered());

		// El cargo actual es una devolución
		if(sLine.getiCargo()!=null && sLine.getiCargo().getRef_CtaPacDet_ID()>0 && sLine.getiCargo().isSeDevolvio()) {
			final MCtaPacDet cargoOriginal = new MCtaPacDet(getCtx(), sLine.getiCargo().getRef_CtaPacDet_ID(),null);
			line.setRef_InOutLine_ID(cargoOriginal.getM_InOutLine_ID());
		}
		
		if(!line.save()){
			throw new MedsysException();
		}
		return line;
	}

	/** Obtener el tipo de documento para la devolución de productos a proveedor */
	public void setVendorReturns(){
		final String sql = MInOut.getWhereMaterialReturn();
		mDocType = new Query(getCtx(), X_C_DocType.Table_Name,sql, get_TrxName())
				.setOnlyActiveRecords(true)
				.addAccessLevelSQL(true)
				.first();
		super.setC_DocType_ID(mDocType.getC_DocType_ID());
		super.setIsSOTrx(false);
		super.setMovementType(X_M_InOut.MOVEMENTTYPE_CustomerShipment);//X_M_InOut.MOVEMENTTYPE_VendorReturns);
	}

	/** Consideraciones para la devolución de productos */
	public boolean isDocReturn(){
		return getDocType()==null?false:getDocType().getDocSubTypeSO() != null;
	}

	public enum ProcessesCharges {
		FACTURACIONDIRECTA
		, CARGOSACUENTA
		, CARGOSDIARIOS
		, APLICARSERVICIOS
		, TRASPASOSENTREALMACENES
		, ADMONDEMEDICAMENTO
	}

	public static String getTittleProcess(final ProcessesCharges process){
		String tittle;
		switch (process) {
		case FACTURACIONDIRECTA:
			tittle = Utilerias.getLabel("factDirecta.title");
			break;
		case CARGOSACUENTA:
			tittle = Utilerias.getLabel("encCtaPac.title");
			break;
		case CARGOSDIARIOS:
			tittle = Utilerias.getLabel("cDiarios.cDiarios");
			break;
		case APLICARSERVICIOS:
			tittle = Utilerias.getLabel("title.aplicacion");
			break;
		case TRASPASOSENTREALMACENES:
			tittle = Utilerias.getLabel("cancelAudit.traspaso");
			break;
		case ADMONDEMEDICAMENTO:
			tittle = Utilerias.getLabel("msg.emar");
			break;
		default:
			tittle = Utilerias.getLabel("bioestadistica.notificacionIngreso.cargos");
			break;
		}
		return tittle;

	}

	/**
	 * Actualizar el stock, para inventario fisico si fuera necesario
	 * y para salida de inventario hacia el cliente FACTURACIÓN DIRECTA
	 * @param lstStock: Lineas para afectar inventario
	 * @param error: Error
	 * @param mInvoice: Factura
	 */
	public static MInOut updateStock(final List<Inventariado> lstStock, final ErrorList errors, final PO mPOHdr
			, final ProcessesCharges process, final boolean devolucion) {
		MInOut mInOut = null;

		if (MEXMEMejoras.inventories(mPOHdr.getCtx()) && !lstStock.isEmpty()) {
			MInventory mInventory = null;

			final MWarehouse whLogin = MWarehouse.get(mPOHdr.getCtx(), Env.getM_Warehouse_ID(mPOHdr.getCtx()));
//			whLogin.set_TrxName(mPOHdr.get_TrxName());

			for (final Inventariado sLine: lstStock) {
				sLine.getiCargo().set_TrxName(mPOHdr.get_TrxName());

				// Solo si la linea no tiene existencias total o parcial
				if(sLine.isNoStock() && mPOHdr!=null && sLine.getiCargo()!=null){
					sLine.updateNoStocks(sLine.getiCargo(), sLine.getiCargo().getmLocatorUserID()>0, sLine.getiCargo().isReloadStorage());// si es consigan siempre tiene existencias
					if(sLine.isNoStock()){

						if(mInventory==null){
							mInventory = new MInventory(whLogin, mPOHdr.get_ID(), MInOut.getTittleProcess(process));
							if(!mInventory.save(mPOHdr.get_TrxName())){
								throw new MedsysException();
							}
						}

						if(!mInventory.addInventariadoLines(sLine)){
							errors.add(new Error(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger(Utilerias.getLabel("error.complete.inOut"))));
							break;
						}
					}
				}// Fin_sinstock

				if(mInOut==null){
					if(mPOHdr instanceof MInvoice){
						mInOut = new MInOut((MInvoice)mPOHdr, whLogin);
					}

					if(mPOHdr instanceof MEXMECtaPac){
						final MEXMECtaPac mCtaPac = (MEXMECtaPac)mPOHdr;

						if(mCtaPac.getActPacienteIndH() == null){
							mInOut = new MInOut(mCtaPac, whLogin.getM_Warehouse_ID(), Env.getCurrentDate(), devolucion);
							mInOut.setDateReceived(mInOut.getMovementDate());

						} else {
							mInOut = new MInOut(mCtaPac.getActPacienteIndH(), mPOHdr.get_TrxName());
							// Organizacion de quien solicita
							mInOut.setAD_OrgTrx_ID(mCtaPac.getActPacienteIndH().getAD_OrgTrx_ID());
//							mInOut.setActPacienteIndH(mCtaPac.getActPacienteIndH());
						}
					}

					if(mInOut==null || !mInOut.save()){
						throw new MedsysException();
					}
				}

				final MInOutLine ioLine = mInOut.addInventariadoLines(sLine, MInOut.getTittleProcess(process));
				if(ioLine==null){
					errors.add(new Error(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger(Utilerias.getLabel("error.complete.inOut"))));
					break;

				} else {
					if(sLine.getiCargo()!=null && sLine.getiCargo().getEXME_CtaPac_ID()>0){
						sLine.getiCargo().setM_InOutLine_ID(ioLine.getM_InOutLine_ID());
						sLine.getiCargo().setM_InOut_ID(mInOut.getM_InOut_ID());// guardar la relacion .- Lama
						sLine.getiCargo().setCost();
						if(!sLine.getiCargo().save()){
							throw new MedsysException();
						}

						mInOut.updateLineInvoice(sLine,  ioLine, mPOHdr.get_TrxName());
					} else {
						mInOut.updateLineInvoice(sLine,  ioLine, mPOHdr.get_TrxName());
					}
				}
			}// fin_for

			if(errors.isEmpty() && mInventory!=null){
				mInventory.setProcess("Directa");// siempre SOLO Directa
				mInventory.startWorkflow(errors,  DocAction.ACTION_Complete, DocAction.STATUS_Completed, mPOHdr.get_TrxName());
			}

			if(errors.isEmpty() && mInOut!=null){
				mInOut.startWorkflow(errors,  DocAction.ACTION_Complete, DocAction.STATUS_Completed, mPOHdr.get_TrxName());
			}

		}// fin_manejoinventario
		return mInOut;
	}

	/**
	 * Actualizar las lienas de las referencias de ambarque y costos
	 * @param sLine Lineas a afactar el inventario
	 * @param ioLine Linea del embarque
	 * @param mInvoice Linea de la factura
	 */
	protected void updateLineInvoice(final Inventariado sLine, final MInOutLine ioLine, final String trxName){
		if(sLine.getmInvoiceLine()!=null){
			sLine.getmInvoiceLine().setM_InOutLine_ID(ioLine.getM_InOutLine_ID());
			sLine.getmInvoiceLine().setCost();//TODO Aqui poner los costos
			if(!sLine.getmInvoiceLine().save(trxName)){
				throw new MedsysException();
			}
		}
	}

	/**
	 * Agregar una linea para la salida de inventario
	 * @param errorList: Mensajes de error
	 * @param cargo: Linea que se va a facturar
	 * @param line: Linea de la factura
	 * @return Linea a facturar que debe afectar inventarios
	 */
	public static Inventariado addLineInventory(final ErrorList errorList, final MCtaPacDet cargo, final PO poLine) {

		if(poLine!=null && poLine instanceof MInvoiceLine){
			// Objeto con la información Producto, Cantidades, Unidad
			if(cargo.getM_Locator_ID()<=0){
				cargo.setmLocatorSurID();
			}//
		}

		Inventariado mInventariado = null;
		if(MEXMEMejoras.inventories(Env.getCtx()) && cargo.isValidInventory(errorList)){
			mInventariado = new Inventariado(cargo.getCtx(), cargo, cargo.get_TrxName());
			mInventariado.setTargetQty(cargo.getTargetQty());//Cantidad del usuario en minima
			mInventariado.updateNoStocks(cargo, cargo.getmLocatorUserID()>0, cargo.isReloadStorage());
			if(poLine!=null && poLine instanceof MInvoiceLine){
				mInventariado.setmInvoiceLine((MInvoiceLine)poLine);

			} else if(poLine!=null && poLine instanceof MEXMEActPacienteInd){
				final MEXMEActPacienteInd mLine = (MEXMEActPacienteInd)poLine;
				mInventariado.setApIndLine(mLine);
				mInventariado.setQty(mLine.getQtyDelivered()); // Qty - En unidad de almacenamiento
			}

		}
		return mInventariado;
	}

	/**
	 * Returns if the movement is a vendor return based on the base document type
	 * and the movement type.
	 * @param docBaseType The base document type.
	 * @param movementType The movment type.
	 * @return true if is a vendor return, false otherwise.
	 */
	public static boolean isVendorReturn(String docBaseType, String movementType) {
		boolean retVal = false;

		if(X_C_DocType.DOCBASETYPE_MaterialReceipt.equals(docBaseType) &&
				(MOVEMENTTYPE_VendorReturns.equals(movementType) ||
				MOVEMENTTYPE_CustomerShipment.equals(movementType))) {
			retVal = true;
		}

		return retVal;
	}

} // MInOut
