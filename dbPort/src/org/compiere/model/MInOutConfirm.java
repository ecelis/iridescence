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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Shipment Confirmation Model
 * 
 * @author Jorg Janke
 * @version $Id: MInOutConfirm.java,v 1.4 2006/09/28 21:47:22 llama Exp $
 */
public class MInOutConfirm extends X_M_InOutConfirm implements DocAction {
	private static final long serialVersionUID = 372896189578635538L;

	/**
	 * Create Confirmation or return existing one
	 * 
	 * @param ship
	 *            shipment
	 * @param confirmType
	 *            confirmation type
	 * @param checkExisting
	 *            if false, new confirmation is created
	 * @return Confirmation
	 */
	public static MInOutConfirm create(MInOut ship, String confirmType,
			boolean checkExisting) {
		if (checkExisting) {
			MInOutConfirm[] confirmations = ship.getConfirmations(false);
			for (int i = 0; i < confirmations.length; i++) {
				MInOutConfirm confirm = confirmations[i];
				if (confirm.getConfirmType().equals(confirmType)) {
					s_log.info("create - existing: " + confirm);
					return confirm;
				}
			}
		}

		MInOutConfirm confirm = new MInOutConfirm(ship, confirmType);
		confirm.save(ship.get_TrxName());
		MInOutLine[] shipLines = ship.getLines(false);
		for (int i = 0; i < shipLines.length; i++) {
			MInOutLine sLine = shipLines[i];
			MInOutLineConfirm cLine = new MInOutLineConfirm(confirm);
			cLine.setInOutLine(sLine);
			cLine.save(ship.get_TrxName());
		}
		s_log.info("New: " + confirm);
		return confirm;
	} // MInOutConfirm

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MInOutConfirm.class);

	/**************************************************************************
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param M_InOutConfirm_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MInOutConfirm(Properties ctx, int M_InOutConfirm_ID, String trxName) {
		super(ctx, M_InOutConfirm_ID, trxName);
		if (M_InOutConfirm_ID == 0) {
			// setConfirmType (null);
			setDocAction(DOCACTION_Complete); // CO
			setDocStatus(DOCSTATUS_Drafted); // DR
			setIsApproved(false);
			setIsCancelled(false);
			setIsInDispute(false);
			super.setProcessed(false);
		}
	} // MInOutConfirm

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MInOutConfirm(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MInOutConfirm

	/**
	 * Parent Constructor
	 * 
	 * @param ship
	 *            shipment
	 * @param confirmType
	 *            confirmation type
	 */
	public MInOutConfirm(MInOut ship, String confirmType) {
		this(ship.getCtx(), 0, ship.get_TrxName());
		setClientOrg(ship);
		setM_InOut_ID(ship.getM_InOut_ID());
		setConfirmType(confirmType);
	} // MInOutConfirm

	/** Confirm Lines */
	private MInOutLineConfirm[] m_lines = null;
	/** Credit Memo to create */
	private MInvoice m_creditMemo = null;
	/** Physical Inventory to create */
	private MInventory m_inventory = null;

	/**
	 * Get Lines
	 * 
	 * @param requery
	 *            requery
	 * @return array of lines
	 */
	public MInOutLineConfirm[] getLines(boolean requery) {
		if (m_lines != null && !requery)
			return m_lines;
		String sql = "SELECT * FROM M_InOutLineConfirm "
				+ "WHERE M_InOutConfirm_ID=?";

		ArrayList<MInOutLineConfirm> list = new ArrayList<MInOutLineConfirm>();
		PreparedStatement pstmt = null;

		sql = MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql,
				"M_InOutLineConfirm");

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getM_InOutConfirm_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MInOutLineConfirm(getCtx(), rs, get_TrxName()));
			rs.close();
			pstmt.close();
			pstmt = null;
			rs = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		m_lines = new MInOutLineConfirm[list.size()];
		list.toArray(m_lines);
		return m_lines;
	} // getLines

	/**
	 * Add to Description
	 * 
	 * @param description
	 *            text
	 */
	public void addDescription(String description) {
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	} // addDescription

	/**
	 * Get Name of ConfirmType
	 * 
	 * @return confirm type
	 */
	public String getConfirmTypeName() {
		return MRefList.getListName(getCtx(), CONFIRMTYPE_AD_Reference_ID,
				getConfirmType());
	} // getConfirmTypeName

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("MInOutConfirm[");
		sb.append(get_ID()).append("-").append(getSummary()).append("]");
		return sb.toString();
	} // toString

	/**
	 * Get Document Info
	 * 
	 * @return document info (untranslated)
	 */
	public String getDocumentInfo() {
		return Msg.getElement(getCtx(), "M_InOutConfirm_ID") + " "
				+ getDocumentNo();
	} // getDocumentInfo

	/**
	 * Create PDF
	 * 
	 * @return File or null
	 */
	public File createPDF() {
		try {
			File temp = File.createTempFile(get_TableName() + get_ID() + "_",
					".pdf");
			return createPDF(temp);
		} catch (Exception e) {
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	} // getPDF

	/**
	 * Create PDF file
	 * 
	 * @param file
	 *            output file
	 * @return file if success
	 */
	public File createPDF(File file) {
		// ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE,
		// getC_Invoice_ID());
		// if (re == null)
		return null;
		// return re.getPDF(file);
	} // createPDF

	/**
	 * Set Approved
	 * 
	 * @param IsApproved
	 *            approval
	 */
	public void setIsApproved(boolean IsApproved) {
		if (IsApproved && !isApproved()) {
			int AD_User_ID = Env.getAD_User_ID(getCtx());
			MUser user = MUser.get(getCtx(), AD_User_ID);
			String info = user.getName() + ": "
					+ Msg.translate(getCtx(), "IsApproved") + " - "
					+ DB.getTimestampForOrg(getCtx());// new
														// Timestamp(System.currentTimeMillis());
			addDescription(info);
		}
		super.setIsApproved(IsApproved);
	} // setIsApproved

	/**************************************************************************
	 * Process document
	 * 
	 * @param processAction
	 *            document action
	 * @return true if performed
	 */
	public boolean processIt(String processAction) {
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());
	} // processIt

	/** Process Message */
	private String m_processMsg = null;
	/** Just Prepared Flag */
	private boolean m_justPrepared = false;

	/**
	 * Unlock Document.
	 * 
	 * @return true if success
	 */
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
	public String prepareIt() {
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		/**
		 * MDocType dt = MDocType.get(getCtx(), getC_DocTypeTarget_ID());
		 * 
		 * // Std Period open? if (!MPeriod.isOpen(getCtx(), getDateAcct(),
		 * dt.getDocBaseType())) { m_processMsg = "@PeriodClosed@"; return
		 * DocAction.STATUS_Invalid; }
		 **/

		MInOutLineConfirm[] lines = getLines(true);
		if (lines.length == 0) {
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		// Set dispute if not fully confirmed
		boolean difference = false;
		for (int i = 0; i < lines.length; i++) {
			if (!lines[i].isFullyConfirmed()) {
				difference = true;
				break;
			}
		}
		setIsInDispute(difference);

		//
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/**
	 * Approve Document
	 * 
	 * @return true if success
	 */
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
	public String completeIt() {
		// Re-Check
		if (!m_justPrepared) {
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		// Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		//
		MInOut inout = new MInOut(getCtx(), getM_InOut_ID(), get_TrxName());
		MInOutLineConfirm[] lines = getLines(false);

		// Check if we need to split Shipment
		if (isInDispute()) {
			MDocType dt = MDocType.get(getCtx(), inout.getC_DocType_ID());
			if (dt.isSplitWhenDifference()) {
				if (dt.getC_DocTypeDifference_ID() == 0) {
					m_processMsg = "No Split Document Type defined for: "
							+ dt.getName();
					return DocAction.STATUS_Invalid;
				}
				splitInOut(inout, dt.getC_DocTypeDifference_ID(), lines);
				m_lines = null;
			}
		}

		// All lines
		for (int i = 0; i < lines.length; i++) {
			MInOutLineConfirm confirmLine = lines[i];
			confirmLine.set_TrxName(get_TrxName());
			if (!confirmLine.processLine(inout.isSOTrx(), getConfirmType())) {
				m_processMsg = "ShipLine not saved - " + confirmLine;
				return DocAction.STATUS_Invalid;
			}
			if (confirmLine.isFullyConfirmed()) {
				confirmLine.setProcessed(true);
				confirmLine.save(get_TrxName());
			} else {
				if (createDifferenceDoc(inout, confirmLine)) {
					confirmLine.setProcessed(true);
					confirmLine.save(get_TrxName());
				} else {
					log.log(Level.SEVERE,
							"Scrapped=" + confirmLine.getScrappedQty()
									+ " - Difference="
									+ confirmLine.getDifferenceQty());
					return DocAction.STATUS_Invalid;
				}
			}
		} // for all lines

		if (m_creditMemo != null)
			m_processMsg += " @C_Invoice_ID@=" + m_creditMemo.getDocumentNo();
		if (m_inventory != null)
			m_processMsg += " @M_Inventory_ID@=" + m_inventory.getDocumentNo();

		// Try to complete Shipment
		// if (inout.processIt(DocAction.ACTION_Complete))
		// m_processMsg = "@M_InOut_ID@ " + inout.getDocumentNo() +
		// ": @Completed@";

		// User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	} // completeIt

	/**
	 * Split Shipment into confirmed and dispute
	 * 
	 * @param original
	 *            original shipment
	 * @param C_DocType_ID
	 *            target DocType
	 * @param confirmLines
	 *            confirm lines
	 */
	private void splitInOut(MInOut original, int C_DocType_ID,
			MInOutLineConfirm[] confirmLines) {
		MInOut split = new MInOut(original, C_DocType_ID,
				original.getMovementDate());
		split.addDescription("Splitted from " + original.getDocumentNo());
		split.setIsInDispute(true);
		if (!split.save(get_TrxName()))
			throw new IllegalStateException("Cannot save Split");
		original.addDescription("Split: " + split.getDocumentNo());
		if (!original.save(get_TrxName()))
			throw new IllegalStateException("Cannot update original Shipment");

		// Go through confirmations
		for (int i = 0; i < confirmLines.length; i++) {
			MInOutLineConfirm confirmLine = confirmLines[i];
			BigDecimal differenceQty = confirmLine.getDifferenceQty();
			if (differenceQty.compareTo(Env.ZERO) == 0)
				continue;
			//
			MInOutLine oldLine = confirmLine.getLine();
			log.fine("Qty=" + differenceQty + ", Old=" + oldLine);
			//
			MInOutLine splitLine = new MInOutLine(split);
			splitLine.setC_OrderLine_ID(oldLine.getC_OrderLine_ID());
			splitLine.setC_UOM_ID(oldLine.getC_UOM_ID());
			splitLine.setDescription(oldLine.getDescription());
			splitLine.setIsDescription(oldLine.isDescription());
			splitLine.setLine(oldLine.getLine());
			splitLine.setM_AttributeSetInstance_ID(oldLine
					.getM_AttributeSetInstance_ID());
			splitLine.setM_Locator_ID(oldLine.getM_Locator_ID());
			splitLine.setM_Product_ID(oldLine.getM_Product_ID());
			splitLine.setM_Warehouse_ID(oldLine.getM_Warehouse_ID());
			splitLine.setRef_InOutLine_ID(oldLine.getRef_InOutLine_ID());
			splitLine.addDescription("Split: from " + oldLine.getMovementQty());
			// Qtys
			splitLine.setQty(differenceQty); // Entered/Movement
			if (!splitLine.save(get_TrxName()))
				throw new IllegalStateException("Cannot save Split Line");
			// Old
			oldLine.addDescription("Splitted: from " + oldLine.getMovementQty());
			oldLine.setQty(oldLine.getMovementQty().subtract(differenceQty));
			if (!oldLine.save(get_TrxName()))
				throw new IllegalStateException("Cannot save Splited Line");
			// Update Confirmation Line
			confirmLine.setTargetQty(confirmLine.getTargetQty().subtract(
					differenceQty));
			confirmLine.setDifferenceQty(Env.ZERO);
			if (!confirmLine.save(get_TrxName()))
				throw new IllegalStateException(
						"Cannot save Split Confirmation");
		} // for all confirmations

		m_processMsg = "Split @M_InOut_ID@=" + split.getDocumentNo()
				+ " - @M_InOutConfirm_ID@=";

		// Create Dispute Confirmation
		split.processIt(DocAction.ACTION_Prepare);
		// split.createConfirmation();
		split.save(get_TrxName());
		MInOutConfirm[] splitConfirms = split.getConfirmations(true);
		if (splitConfirms.length > 0) {
			int index = 0;
			if (splitConfirms[index].isProcessed()) {
				if (splitConfirms.length > 1)
					index++; // try just next
				if (splitConfirms[index].isProcessed()) {
					m_processMsg += splitConfirms[index].getDocumentNo()
							+ " processed??";
					return;
				}
			}
			splitConfirms[index].setIsInDispute(true);
			splitConfirms[index].save(get_TrxName());
			m_processMsg += splitConfirms[index].getDocumentNo();
			// Set Lines to unconfirmed
			MInOutLineConfirm[] splitConfirmLines = splitConfirms[index]
					.getLines(false);
			for (int i = 0; i < splitConfirmLines.length; i++) {
				MInOutLineConfirm splitConfirmLine = splitConfirmLines[i];
				splitConfirmLine.setScrappedQty(Env.ZERO);
				splitConfirmLine.setConfirmedQty(Env.ZERO);
				splitConfirmLine.save(get_TrxName());
			}
		} else
			m_processMsg += "??";

	} // splitInOut

	/**
	 * Create Difference Document
	 * 
	 * @param inout
	 *            shipment/receipt
	 * @param confirm
	 *            confirm line
	 * @return true if created
	 */
	private boolean createDifferenceDoc(MInOut inout, MInOutLineConfirm confirm) {
		if (m_processMsg == null)
			m_processMsg = "";
		else if (m_processMsg.length() > 0)
			m_processMsg += "; ";
		// Credit Memo if linked Document
		if (confirm.getDifferenceQty().signum() != 0 && !inout.isSOTrx()
				&& inout.getRef_InOut_ID() != 0) {
			log.info("Difference=" + confirm.getDifferenceQty());
			if (m_creditMemo == null) {
				m_creditMemo = new MInvoice(inout, null);
				m_creditMemo.setDescription(Msg.translate(getCtx(),
						"M_InOutConfirm_ID") + " " + getDocumentNo());
				m_creditMemo
						.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_APCreditMemo);
				if (!m_creditMemo.save(get_TrxName())) {
					m_processMsg += "Credit Memo not created";
					return false;
				}
				setC_Invoice_ID(m_creditMemo.getC_Invoice_ID());
			}
			MInvoiceLine line = new MInvoiceLine(m_creditMemo);
			line.setShipLine(confirm.getLine());
			line.setQty(confirm.getDifferenceQty()); // Entered/Invoiced
			if (!line.save(get_TrxName())) {
				m_processMsg += "Credit Memo Line not created";
				return false;
			}
			confirm.setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
		}

		// Create Inventory Difference
		if (confirm.getScrappedQty().signum() != 0) {
			log.info("Scrapped=" + confirm.getScrappedQty());
			if (m_inventory == null) {
				MWarehouse wh = MWarehouse.get(getCtx(),
						inout.getM_Warehouse_ID());
				m_inventory = new MInventory(wh);
				m_inventory.setDescription(Msg.translate(getCtx(),
						"M_InOutConfirm_ID") + " " + getDocumentNo());
				if (!m_inventory.save(get_TrxName())) {
					m_processMsg += "Inventory not created";
					return false;
				}
				setM_Inventory_ID(m_inventory.getM_Inventory_ID());
			}
			MInOutLine ioLine = confirm.getLine();
			MInventoryLine line = new MInventoryLine(m_inventory,
					ioLine.getM_Locator_ID(), ioLine.getM_Product_ID(),
					ioLine.getM_AttributeSetInstance_ID(),
					confirm.getScrappedQty(), Env.ZERO);
			if (!line.save(get_TrxName())) {
				m_processMsg += "Inventory Line not created";
				return false;
			}
			confirm.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
		}

		//
		if (!confirm.save(get_TrxName())) {
			m_processMsg += "Confirmation Line not saved";
			return false;
		}
		return true;
	} // createDifferenceDoc

	/**
	 * Void Document.
	 * 
	 * @return false
	 */
	public boolean voidIt() {
		log.info(toString());
		return false;
	} // voidIt

	/**
	 * Close Document.
	 * 
	 * @return true if success
	 */
	public boolean closeIt() {
		log.info(toString());

		setDocAction(DOCACTION_None);
		return true;
	} // closeIt

	/**
	 * Reverse Correction
	 * 
	 * @return false
	 */
	public boolean reverseCorrectIt() {
		log.info(toString());
		return false;
	} // reverseCorrectionIt

	/**
	 * Reverse Accrual - none
	 * 
	 * @return false
	 */
	public boolean reverseAccrualIt() {
		log.info(toString());
		return false;
	} // reverseAccrualIt

	/**
	 * Re-activate
	 * 
	 * @return false
	 */
	public boolean reActivateIt() {
		log.info(toString());
		return false;
	} // reActivateIt

	/*************************************************************************
	 * Get Summary
	 * 
	 * @return Summary of Document
	 */
	public String getSummary() {
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		// : Total Lines = 123.00 (#1)
		sb.append(": ").append(Msg.translate(getCtx(), "ApprovalAmt"))
				.append("=").append(getApprovalAmt()).append(" (#")
				.append(getLines(false).length).append(")");
		// - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	} // getSummary

	/**
	 * Get Process Message
	 * 
	 * @return clear text error message
	 */
	public String getProcessMsg() {
		return m_processMsg;
	} // getProcessMsg

	/**
	 * Get Document Owner (Responsible)
	 * 
	 * @return AD_User_ID
	 */
	public int getDoc_User_ID() {
		return getUpdatedBy();
	} // getDoc_User_ID

	/**
	 * Get Document Currency
	 * 
	 * @return C_Currency_ID
	 */
	public int getC_Currency_ID() {
		// MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
		// return pl.getC_Currency_ID();
		return 0;
	} // getC_Currency_ID

	/**
	 * 
	 * @param ctx
	 * @param almacenSolicita
	 * @param almacenSurte
	 * @param nivel
	 * @return
	 * @throws Exception
	 */
	public static List<MovementLine> getPedidoAuto(final Properties ctx, final int almacenSolicita,
			final int almacenSurte, final String nivel) throws Exception {
		final List<MovementLine> lista = new ArrayList<MovementLine>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		sql.append(
				" SELECT p.M_Product_ID, p.Value, p.Name ProdName, p.Description, p.UPC, p.SKU, p.C_Uom_ID, ")
				.append(" NVL(SUM(s.QtyOnHand - s.QtyReserved ),0) AS DisponibleQty, ")
				.append(" NVL(SUM(s.QtyOnHand),0) AS QtyOnHand , NVL( SUM(s.QtyReserved),0) AS QtyReserved, ")
				.append(" NVL(SUM(s.QtyOrdered),0) AS QtyOrdered, u.Name UomName, r.Level_Max, r.Level_min, ")

				// unidad de medida de volumen
				.append(" p.C_UOMVolume_ID, r.Level_Max_Vol, r.Level_Min_Vol, uv.Name AS uomNameVol, ")
				
				// Columna cantidad confirmada
				.append(" (NVL((SELECT SUM(c.TargetQty) FROM M_InOutLineConfirm c ")
				.append(" INNER JOIN M_InOutLine il ON (c.M_InOutLine_ID=il.M_InOutLine_ID)  ")
				.append(" INNER JOIN M_Product prod ON (il.M_Product_ID = prod.M_Product_ID) ")
				.append(" INNER JOIN M_InOut i ON (il.M_InOut_ID=i.M_InOut_ID) ")
				.append(" WHERE c.Processed='N' AND i.M_Warehouse_ID = ")
				.append(almacenSolicita)
				.append(" AND il.M_Product_ID = p.M_Product_ID), 0)) ConfirmedQty, ")

				// Columna cantidad en transito
				.append("(NVL((SELECT SUM(CASE m.DocStatus WHEN 'DR' THEN ml.OriginalQty WHEN 'IP' THEN ml.TargetQty END) ")
				.append("FROM M_MovementLine ml, M_Movement m, M_Locator loc  ")
				.append("WHERE  p.M_Product_ID = ml.M_Product_ID AND ml.m_movement_id = m.m_movement_id AND ml.m_locatorto_id = loc.m_locator_id ")
				.append("AND loc.m_warehouse_id = ")
				.append(almacenSolicita)
				.append(" AND (m.DocStatus = 'DR' OR m.DocStatus = 'IP')), 0)) EnTransito, ");

		// Cantidad a pedir, Nivel
		if (("min").equalsIgnoreCase(nivel)) {
			sql.append("NVL(((r.Level_Min - ");
		} else {
			sql.append("NVL(((r.Level_Max - ");
		}

		// *La linea anterior menos (la cantidad disponible menos la reservada)
		sql.append(" (NVL(SUM(s.QtyOnHand - s.QtyReserved), 0))) - ")

				// *Las lineas anteriores menos la cantidad ordenada
				.append(" (NVL(SUM(s.QtyOrdered), 0) +  (NVL(( ")

				// *Las lineas anteriores mas la cantidad confirmada
				.append("SELECT NVL( SUM(c.TargetQty),0)  FROM M_InOutLineConfirm c ")
				.append("INNER JOIN M_InOutLine il ON (c.M_InOutLine_ID=il.M_InOutLine_ID) ")
				.append("INNER JOIN M_Product prod ON (il.M_Product_ID = prod.M_Product_ID) ")
				.append("INNER JOIN M_InOut i ON (il.M_InOut_ID=i.M_InOut_ID) ")
				.append("WHERE c.Processed='N' AND i.M_Warehouse_ID = ")
				.append(almacenSolicita)
				.append(" AND il.M_Product_ID = p.M_Product_ID ")

				// *Menos Cantidad en transito
				.append("), 0)))) - (NVL((SELECT NVL(SUM( ")
				.append("CASE m.DocStatus WHEN '"
						+ MInOutConfirm.STATUS_Drafted
						+ "' THEN ml.OriginalQty WHEN '")
				.append(MInOutConfirm.STATUS_InProgress
						+ "' THEN ml.TargetQty END),0) ")
				.append("FROM M_MovementLine ml, M_Movement m, M_Locator loc ")
				.append("WHERE  p.M_Product_ID = ml.M_Product_ID AND ml.m_movement_id = m.m_movement_id AND ml.m_locatorto_id = loc.m_locator_id ")
				.append("AND loc.m_warehouse_id = ")
				.append(almacenSolicita)
				.append(" AND (m.DocStatus = '" + MInOutConfirm.STATUS_Drafted
						+ "' OR m.DocStatus = '")
				.append(MInOutConfirm.STATUS_InProgress
						+ "')), 0)), 0) AS CantidadPed ")

				// FROM
				.append("FROM M_Product p ")
				.append("LEFT JOIN C_UOM  u ON (u.C_UOM_ID = p.C_UOM_ID) ")
				.append("LEFT JOIN C_UOM uv ON (uv.C_UOM_ID = p.C_UOMVolume_ID) ")
				.append("LEFT JOIN M_Replenish r ON (r.M_Product_ID = p.M_Product_ID) ")
				.append("LEFT JOIN M_Locator   l ON (l.M_Warehouse_ID = r.M_Warehouse_ID) ")
				.append("LEFT JOIN M_Storage   s ON (s.M_Locator_ID = l.M_Locator_ID AND s.M_Product_ID = p.M_Product_ID) ")

				// WHERE
				.append("WHERE  l.M_Warehouse_ID = ")
				.append(almacenSolicita)
//				.append(" AND p.M_Product_ID IN (SELECT pr.M_Product_ID FROM M_Product pr ")
//				.append("INNER JOIN EXME_ProductClassWhs re ON (re.ProductClass = pr.ProductClass) ")
//				.append("WHERE re.M_warehouse_id = ").append(almacenSurte)
//				.append(" AND pr.IsActive='Y') ")
				.append(" AND p.IsActive='Y' ");

		if (("min").equalsIgnoreCase(nivel)) {
			sql.append("AND r.Level_Min > 0 ");
		} else {
			sql.append("AND r.Level_Max > 0 ");
		}

		// GROUP
		sql.append(" GROUP BY p.M_Product_ID, p.Value, p.Name, p.Description, p.UPC, p.SKU, p.C_Uom_ID, u.Name, r.Level_Max, r.Level_min, ")
		.append(" p.C_UOMVolume_ID, r.Level_Max_Vol, r.Level_Min_Vol, uv.Name ");

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		pstmt = null;
		rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			// int i = 1;
			while (rs.next()) {
				MovementLine pedido = new MovementLine();
				if (rs.getLong("CantidadPed") >= 1) {
					pedido.setProductID(rs.getLong("M_Product_ID"));
					pedido.setValue(rs.getString("Value"));
					pedido.setProdName(rs.getString("ProdName"));
					pedido.setDescripLine(rs.getString("Description"));
					pedido.setUpC(rs.getString("UPC"));
					pedido.setSku(rs.getString("SKU"));
					
					// Unidad de medida
					pedido.setUOM(rs.getLong("C_Uom_ID"));
					pedido.setUomName(rs.getString("UomName"));
					pedido.setDisponibleQty(rs.getLong("DisponibleQty"));
					pedido.setQtyOnHand(rs.getLong("QtyOnHand"));
					pedido.setQtyReserved(rs.getLong("QtyReserved"));
					pedido.setQtyOrdered(rs.getLong("QtyOrdered"));
					if (("min").equalsIgnoreCase(nivel)) {
						pedido.setLevel_MaxMin(rs.getLong("Level_Min"));
					} else {
						pedido.setLevel_MaxMin(rs.getLong("Level_Max"));
					}
					pedido.setConfirmedQty(rs.getLong("ConfirmedQty"));
					pedido.setEnTransito(rs.getBigDecimal("EnTransito"));
					pedido.setOriginalQty(rs.getLong("CantidadPed"));
					

					// Unidad de medida de volumen
//					.append(" p.C_UOMVolume_ID, r.Level_Max_Vol, r.Level_Min_Vol, ")
					pedido.setcUOMVolumeID(rs.getInt("C_UOMVolume_ID"));
					pedido.setUomNameVolume(rs.getString("uomNameVol"));
					pedido.setLevelMaxVol(rs.getLong("Level_Max_Vol"));
					pedido.setLevelMinVol(rs.getLong("Level_Min_Vol"));
					
					if (("min").equalsIgnoreCase(nivel)) {
						pedido.setLevel_MaxMinVol(rs.getLong("Level_Min_Vol"));
					} else {
						pedido.setLevel_MaxMinVol(rs.getLong("Level_Max_Vol"));
					}
					
					// Conversiones
					/*
					pedido.getDisponibleQtyVol()//rs.getLong("DisponibleQty"));
					pedido.getQtyOnHandVol()//rs.getLong("QtyOnHand"));
					pedido.getQtyReservedVol()//rs.getLong("QtyReserved"));
					pedido.getQtyOrderedVol()//rs.getLong("QtyOrdered"));
					pedido.getConfirmedQtyVol()//rs.getLong("ConfirmedQty"));
					pedido.getEnTransitoVol()//rs.getBigDecimal("EnTransito"));
					pedido.getOriginalQtyVol()//rs.getLong("CantidadPed"));
					*/
					
					MProduct  product = new MProduct(ctx, (int)pedido.getProductID(),null);
					
					BigDecimal[] qtysDis = product.qtyConversionTo(new BigDecimal(pedido.getDisponibleQty()), rs.getInt("C_UOMVolume_ID"));
					pedido.setDisponibleQtyVol(qtysDis[1]);
					
					BigDecimal[] qtysOnh = product.qtyConversionTo(new BigDecimal(pedido.getQtyOnHand()), rs.getInt("C_UOMVolume_ID"));
					pedido.setQtyOnHandVol(qtysOnh[1]);
					
					BigDecimal[] qtysRes = product.qtyConversionTo(new BigDecimal(pedido.getQtyReserved()), rs.getInt("C_UOMVolume_ID"));
					pedido.setQtyReservedVol(qtysRes[1]);
					
					BigDecimal[] qtysOrd = product.qtyConversionTo(new BigDecimal(pedido.getQtyOrdered()), rs.getInt("C_UOMVolume_ID"));
					pedido.setQtyOrderedVol(qtysOrd[1]);
					
					BigDecimal[] qtysCon = product.qtyConversionTo(new BigDecimal(pedido.getConfirmedQty()), rs.getInt("C_UOMVolume_ID"));
					pedido.setConfirmedQtyVol(qtysCon[1]);
					
					BigDecimal[] qtysTra = product.qtyConversionTo(pedido.getEnTransito(), rs.getInt("C_UOMVolume_ID"));
					pedido.setEnTransitoVol(qtysTra[1]);
					
					BigDecimal[] qtysOri = product.qtyConversionTo(new BigDecimal(pedido.getOriginalQty()), rs.getInt("C_UOMVolume_ID"));
					pedido.setOriginalQtyVol(qtysOri[1]);
						
					lista.add(pedido);
				}
				// i++;
			}
			/*
			 * rs.close(); pstmt.close(); rs = null; pstmt = null;
			 */
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getPedidoAuto: ", e);
			throw new Exception("error.getMovementConfirm");
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	} // expert

} // MInOutConfirm