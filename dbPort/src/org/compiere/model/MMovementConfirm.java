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

import static org.compiere.util.Utilerias.getAppMsg;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.MovementLine;
import org.compiere.model.bean.TransferBean;
import org.compiere.model.bpm.Inventory;
import org.compiere.model.movements.MovementBean;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.SecureEngine;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Inventory Movement Confirmation
 * 
 * @author Jorg Janke
 * @version $Id: MMovementConfirm.java,v 1.5 2006/11/02 21:36:39 taniap Exp $
 * Modificado por Lorena Lama (Marzo 2014)
 */
public class MMovementConfirm extends X_M_MovementConfirm implements DocAction {
	

	/** serialVersionUID */
	private static final long serialVersionUID = -1259330451933503247L;

	/**
	 * Create Confirmation or return existing one
	 * 
	 * @param move
	 *            movement
	 * @param checkExisting
	 *            if false, new confirmation is created
	 * @return Confirmation
	 */
	// false
	public static MMovementConfirm create(final MMovement move, final boolean checkExisting) {
		if (checkExisting) {
			final MMovementConfirm[] confirmations = move.getConfirmations(false);
			for (MMovementConfirm confirm : confirmations) {
				return confirm;
			}
		}

		final MMovementConfirm confirm = new MMovementConfirm(move);
		if (confirm.save()) { //ya lleva trx
			// trae las lineas del movimiento
			for (MMovementLine mLine : move.getLines(false)) {
				// expert : gisela lee : si es medsys, es decir, tiene originalqty,
				// entonces validar que el targetqty sea != 0
				if (mLine.getOriginalQty().compareTo(Env.ZERO) == 0 //Solicitada 
					|| mLine.getTargetQty().compareTo(Env.ZERO) != 0) {//Surtida
					if (!new MMovementLineConfirm(confirm, mLine).save()) {//ya lleva trx
						s_log.severe("No pude guardar una linea del movimiento al pasarla a la confirmacion");
					}
				}
			}
		} else {
			s_log.severe("el header de la conformacion no fue posible guardarla");
		}

		return confirm;
	} // MInOutConfirm

	/**************************************************************************
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param M_MovementConfirm_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MMovementConfirm(final Properties ctx, final int M_MovementConfirm_ID, String trxName) {
		super(ctx, M_MovementConfirm_ID, trxName);
		if (is_new()) {
			// setM_Movement_ID (0);
			setDocAction(DOCACTION_Complete);
			setDocStatus(DOCSTATUS_Drafted);
			setIsApproved(false); // N
			setProcessed(false);
		}
	} // MMovementConfirm

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rset
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MMovementConfirm(final Properties ctx, final ResultSet rset, final String trxName) {
		super(ctx, rset, trxName);
	} // MMovementConfirm

	/**
	 * Parent Constructor
	 * 
	 * @param move
	 *            movement
	 */
	public MMovementConfirm(final MMovement move) {
		this(move.getCtx(), 0, move.get_TrxName());
		setClientOrg(move);
		setM_Movement_ID(move.getM_Movement_ID());
		setAD_OrgTrx_ID(move.getAD_OrgTrx_ID()); // OrgTrx .-Lama
		setDocumentNo("C_" + move.getDocumentNo());
	} // MInOutConfirm

	/** Confirm Lines */
	private MMovementLineConfirm[] m_lines = null;

	/** Physical Inventory From */
	private MInventory m_inventoryFrom = null;
	/** Physical Inventory To */
	private MInventory m_inventoryTo = null;
	/** Physical Inventory Info */
	private String m_inventoryInfo = null;

	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(MMovementConfirm.class);

	/**
	 * Get Lines
	 * 
	 * @param requery
	 *            requery
	 * @return array of lines
	 */
	public MMovementLineConfirm[] getLines(final boolean requery) {
		if (m_lines != null && !requery) {
			return m_lines;
		}
		
		List<MMovementLineConfirm> list = getLines();
		m_lines = new MMovementLineConfirm[list.size()];
		list.toArray(m_lines);
		
		return m_lines;
	} // getLines
	
	public List<MMovementLineConfirm> getLines() {
		Query query = new Query(getCtx(), MMovementLineConfirm.Table_Name, " M_MovementConfirm_ID=? ", get_TrxName());
		query.setParameters(getM_MovementConfirm_ID());
		return query.list();
	}

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
	 * Set Approved
	 * 
	 * @param IsApproved
	 *            approval
	 */
	public void setIsApproved(final boolean IsApproved) {
		if (IsApproved && !isApproved()) {
			final int AD_User_ID = Env.getAD_User_ID(getCtx());
			final MUser user = MUser.get(getCtx(), AD_User_ID);
			final String info = user.getName() + ": "
					+ Msg.translate(getCtx(), "IsApproved") + " - "
					+ DB.getTimestampForOrg(getCtx());
			addDescription(info);
		}
		super.setIsApproved(IsApproved);
	} // setIsApproved

	/**
	 * Get Document Info
	 * 
	 * @return document info (untranslated)
	 */
	public String getDocumentInfo() {
		return Msg.getElement(getCtx(), "M_MovementConfirm_ID") + " " + getDocumentNo();
	} // getDocumentInfo

	/**
	 * Create PDF
	 * 
	 * @return File or null
	 */
	public File createPDF() {
		try {
			final File temp = File.createTempFile(get_TableName() + get_ID() + "_", ".pdf");
			return createPDF(temp);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Could not create PDF - " + e.getMessage(), e);
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
	public File createPDF(final File file) {
		// ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE,
		// getC_Invoice_ID());
		// if (re == null)
		return null;
		// return re.getPDF(file);
	} // createPDF

	/**************************************************************************
	 * Process document
	 * 
	 * @param processAction
	 *            document action
	 * @return true if performed
	 */
	public boolean processIt(final String processAction) {
		m_processMsg = null;
		final DocumentEngine engine = new DocumentEngine(this, getDocStatus());
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
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	} // unlockIt

	/**
	 * Invalidate Document
	 * 
	 * @return true if success
	 */
	public boolean invalidateIt() {
		log.info("invalidateIt - " + toString());
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
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null) {
			return DocAction.STATUS_Invalid;
		}

		// Std Period open?
		if (!MPeriod.isOpen(getCtx(), getUpdated(), MDocType.DOCBASETYPE_MaterialMovement, Env.getAD_Org_ID(getCtx()))) {
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		final List<MMovementLineConfirm> lines = getLines();
		if (lines.size() == 0) {
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		@SuppressWarnings("unused")
		boolean difference = false;
		for (int i = 0; i < lines.size(); i++) {
			if (!lines.get(i).isFullyConfirmed()) {
				difference = true;
				break;
			}
		}
		// User Validation
		final String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		//
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction())) {
			setDocAction(DOCACTION_Complete);
		}
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/**
	 * Approve Document
	 * 
	 * @return true if success
	 */
	public boolean approveIt() {
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	} // approveIt

	/**
	 * Reject Approval
	 * 
	 * @return true if success
	 */
	public boolean rejectIt() {
		log.info("rejectIt - " + toString());
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
			final String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		// Implicit Approval
		if (!isApproved()) {
			approveIt();
		}
		log.info("completeIt - " + toString());
		//
		final MMovement move = new MMovement(getCtx(), getM_Movement_ID(), get_TrxName());
		final List<MMovementLineConfirm>lines = getLines();
		for (MMovementLineConfirm confirm : lines) {
			confirm.set_TrxName(get_TrxName());
			if (!confirm.processLine()) {
				m_processMsg = "ShipLine not saved - " + confirm;
				return DocAction.STATUS_Invalid;
			}
			if (confirm.isFullyConfirmed()) {
				confirm.setProcessed(true);
				confirm.save(get_TrxName());
			} else {
				if (createDifferenceDoc(move, confirm)) {
					confirm.setProcessed(true);
					confirm.save(get_TrxName());
				} else {
					log.log(Level.SEVERE, "completeIt - Scrapped=" + confirm.getScrappedQty() + " - Difference=" + confirm.getDifferenceQty());
					String logMsg = MedsysException.getMessageFromLogger();
					m_processMsg = StringUtils.isBlank(logMsg) ? "Difference Doc not created. " : logMsg;
					return DocAction.STATUS_Invalid;
				}
			}
		} // for all lines

		if (m_inventoryInfo != null) {
			m_processMsg = " @M_Inventory_ID@: " + m_inventoryInfo;
			addDescription(Msg.translate(getCtx(), "M_Inventory_ID") + ": " + m_inventoryInfo);
		}

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	} // completeIt

	/**
	 * Create Difference Document. Creates one or two inventory lines
	 * 
	 * @param move
	 *            movement
	 * @param confirm
	 *            confirm line
	 * @return true if created
	 */
	private boolean createDifferenceDoc(MMovement move,
			MMovementLineConfirm confirm) {
		final MMovementLine mLine = confirm.getLine();

		// Difference - Create Inventory Difference for Source Location
		if (Env.ZERO.compareTo(confirm.getDifferenceQty()) != 0) {
			// Get Warehouse for Source
			final MLocator loc = MLocator.get(getCtx(), mLine.getM_Locator_ID());
			if (m_inventoryFrom != null && m_inventoryFrom.getM_Warehouse_ID() != loc.getM_Warehouse_ID())
				m_inventoryFrom = null;

			if (m_inventoryFrom == null) {
				final MWarehouse wh = MWarehouse.get(getCtx(), loc.getM_Warehouse_ID());
				m_inventoryFrom = new MInventory(wh);
				m_inventoryFrom.setInventory(false);//salida al gasto
				m_inventoryFrom.setDescription(Msg.translate(getCtx(), "M_MovementConfirm_ID") + " " + getDocumentNo());
				if (!m_inventoryFrom.save(get_TrxName())) {
					m_processMsg += "Inventory not created";
					return false;
				}
				// First Inventory
				if (getM_Inventory_ID() == 0) {
					setM_Inventory_ID(m_inventoryFrom.getM_Inventory_ID());
					m_inventoryInfo = m_inventoryFrom.getDocumentNo();
				} else {
					m_inventoryInfo += "," + m_inventoryFrom.getDocumentNo();
				}
			}

			log.info("createDifferenceDoc - Difference="
					+ confirm.getDifferenceQty());
			final MInventoryLine line = new MInventoryLine(m_inventoryFrom,
					mLine.getM_Locator_ID(), mLine.getM_Product_ID(),
					mLine.getM_AttributeSetInstance_ID(),
					confirm.getDifferenceQty(), Env.ZERO);
			//Se vuelve negativa para no modficar el proceso de compiere
			line.setQtyInternalUse_Uom(confirm.getDifferenceQty().negate());
			line.setDescription(Msg.translate(getCtx(), "DifferenceQty"));
			line.setProcessWF(true);
			if (!line.save(get_TrxName())) {
				m_processMsg += "Inventory Line not created";
				return false;
			}
			confirm.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
		} // Difference

		// Scrapped - Create Inventory Difference for Target Location
		if (Env.ZERO.compareTo(confirm.getScrappedQty()) != 0) {
			// Get Warehouse for Target
			final MLocator loc = MLocator.get(getCtx(),
					mLine.getM_LocatorTo_ID());
			if (m_inventoryTo != null
					&& m_inventoryTo.getM_Warehouse_ID() != loc
							.getM_Warehouse_ID())
				m_inventoryTo = null;

			if (m_inventoryTo == null) {
				final MWarehouse wh = MWarehouse.get(getCtx(),
						loc.getM_Warehouse_ID());
				m_inventoryTo = new MInventory(wh);
				m_inventoryTo.setDescription(Msg.translate(getCtx(),
						"M_MovementConfirm_ID") + " " + getDocumentNo());
				m_inventoryTo.setInventory(false);
				if (!m_inventoryTo.save(get_TrxName())) {
					m_processMsg += "Inventory not created";
					return false;
				}
				// First Inventory
				if (getM_Inventory_ID() == 0) {
					setM_Inventory_ID(m_inventoryTo.getM_Inventory_ID());
					m_inventoryInfo = m_inventoryTo.getDocumentNo();
				} else
					m_inventoryInfo += "," + m_inventoryTo.getDocumentNo();
			}

			log.info("createDifferenceDoc - Scrapped="
					+ confirm.getScrappedQty());
			final MInventoryLine line = new MInventoryLine(m_inventoryTo,
					mLine.getM_LocatorTo_ID(), mLine.getM_Product_ID(),
					mLine.getM_AttributeSetInstance_ID(),
					confirm.getScrappedQty(), Env.ZERO);
			//Se vuelve negativa para no modficar el proceso de compiere
			line.setQtyInternalUse_Uom(confirm.getScrappedQty().negate());
			line.setDescription(Msg.translate(getCtx(), "ScrappedQty"));
			line.setProcessWF(true);
			if (!line.save(get_TrxName())) {
				m_processMsg += "Inventory Line not created";
				return false;
			}
			confirm.setM_InventoryLine_ID(line.getM_InventoryLine_ID());
		} // Scrapped

		return true;
	} // createDifferenceDoc

	/**
	 * Void Document.
	 * 
	 * @return false
	 */
	public boolean voidIt() {
		log.info("voidIt - " + toString());
		return false;
	} // voidIt

	/**
	 * Close Document. Cancel not delivered Qunatities
	 * 
	 * @return true if success
	 */
	public boolean closeIt() {
		log.info("closeIt - " + toString());

		// Close Not delivered Qty
		setDocAction(DOCACTION_None);
		return true;
	} // closeIt

	/**
	 * Reverse Correction
	 * 
	 * @return false
	 */
	public boolean reverseCorrectIt() {
		log.info("reverseCorrectIt - " + toString());
		return false;
	} // reverseCorrectionIt

	/**
	 * Reverse Accrual - none
	 * 
	 * @return false
	 */
	public boolean reverseAccrualIt() {
		log.info("reverseAccrualIt - " + toString());
		return false;
	} // reverseAccrualIt

	/**
	 * Re-activate
	 * 
	 * @return false
	 */
	public boolean reActivateIt() {
		log.info("reActivateIt - " + toString());
		return false;
	} // reActivateIt

	/*************************************************************************
	 * Get Summary
	 * 
	 * @return Summary of Document
	 */
	public String getSummary() {
		final StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		// : Total Lines = 123.00 (#1)
		sb.append(": ").append(Msg.translate(getCtx(), "ApprovalAmt")).append("=").append(getApprovalAmt()).append(" (#")
			.append(getLines(false).length).append(")");
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
	 * Devolvemos la confirmaci&oacute;n relacionada a un movimiento
	 * especificado.
	 * 
	 * @param ctx
	 * @param movementID
	 * @param trxName
	 * @return La confirmaci&oacute;n del movimiento.
	 */
	public static MMovementConfirm get(final Properties ctx, final int movementID, final String trxName) {
		return new Query(ctx, MMovementConfirm.Table_Name, " M_Movement_ID=? ", trxName)//
			.setParameters(movementID)//
			.addAccessLevelSQL(true)//
			.first();
	}

	// /**
	// * Devolvemos la confirmaci&oacute;n relacionada a un movimiento
	// * especificado.
	// * @param ctx
	// * @param movementID
	// * @param trxName
	// * @return La confirmaci&oacute;n del movimiento.
	// * @throws SQLException
	// */
	// public static MMovementConfirm get(Properties ctx, String document,
	// String trxName)
	// throws SQLException {
	// StringBuffer sql = new StringBuffer();
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// MMovementConfirm mc = null;
	//
	// try {
	//
	// sql.append("SELECT * FROM M_MovementConfirm ")
	// .append("WHERE documentNo = ")
	// .append(document)
	// .append(" AND AD_Client_ID = ")
	// .append(Env.getAD_Client_ID(ctx));
	//
	//
	// pstmt = DB.prepareStatement(sql.toString(), trxName);
	//
	// rs = pstmt.executeQuery();
	//
	// if(rs.next()) {
	// mc = new MMovementConfirm(ctx, rs, trxName);
	// }
	//
	// } catch (SQLException e) {
	// s_log.log(Level.SEVERE, "get (" + sql + ")", e);
	// throw e;
	// } finally {
	// DB.close(rs,pstmt);
	// rs = null;
	// pstmt = null;
	// }
	//
	// return mc;
	// }

//	/**
//	 * 
//	 * @param ctx
//	 * @param almacen
//	 * @param tipo
//	 * @param consigna
//	 * @param fechaIni
//	 * @param fechaFin
//	 * @return
//	 * @throws SQLException
//	 * @deprecated
//	 */
//	public static List<ConfirmaView> getMovementConfirm(final Properties ctx,
//			final int almacen, final String tipo, final boolean consigna, Timestamp fechaIni, Timestamp fechaFin)
//			throws SQLException {
//		return getMovementConfirm(ctx, almacen, 0, tipo, consigna, true, null, fechaIni, fechaFin);
//	}

//	/**
//	 * 
//	 * @param ctx
//	 * @param almacen
//	 * @param tipo
//	 * @param consigna
//	 * @param typeWhs
//	 * @return
//	 * @throws SQLException
//	 * @deprecated
//	 */
//	public static List<ConfirmaView> getMovementConfirm(final Properties ctx,
//			final int almacen, final String tipo, final boolean consigna,
//			final String typeWhs, Timestamp fechaIni, Timestamp fechaFin) throws SQLException {
//		return getMovementConfirm(ctx, almacen, 0, tipo, consigna, true,
//				typeWhs, fechaIni, fechaFin);
//	}

	// expert
//	/**
//	 * Obtenemos los movimientos pendientes de confirmar
//	 * 
//	 * @param empresa
//	 *            la empresa con la que se logeo
//	 * @param organizacion
//	 *            la organizacion con la que se logeo
//	 * @param locatorTo
//	 *            localizador del almacen
//	 * @param tipo
//	 *            Si es una solicitud o una devolucion
//	 * @param fechaIni
//	 * 			  Fecha inicial del rango
//	 * @param fechaFin
//	 * 			  Fecha final del rango
//	 * @return Una lista con los movimientos pendientes de confirmar
//	 * @throws SQLException
//	 * @deprecated
//	 */
//	public static List<ConfirmaView> getMovementConfirm(final Properties ctx,
//			final int almacen, final int almSurte, final String tipo,
//			final boolean consigna, final boolean inProgress,
//			final String typeWhs, Timestamp fechaIni, Timestamp fechaFin) throws SQLException {
//		final List<ConfirmaView> lista = new ArrayList<ConfirmaView>();
//
//		// Quitar los de abasto por que se quieren ver los de charola
//		String onlyTrays = StringUtils.EMPTY;
//		if(X_M_Warehouse.TYPE_WarehouseSupply.equalsIgnoreCase(typeWhs)){
//			onlyTrays = " AND m.EXME_ProgQuiro_ID IS NULL ";
//			
//		} else if(X_M_Warehouse.TYPE_Sterilization.equalsIgnoreCase(typeWhs)){
//			onlyTrays = " AND m.EXME_ProgQuiro_ID IS NOT NULL ";
//		}
//		
//		// movimientos por empresa, organizacion y almacen (localizado)
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT DISTINCT c.M_MovementConfirm_ID, c.DocumentNo, ");
//		sql.append("c.M_Movement_ID, m.Description, c.DocStatus, c.DocAction, ");
//		sql.append("m.DocumentNo DocNoMovement, a.Name as Almacen, sol.Name solicita, surte.Name surte ");
//		sql.append("FROM M_MovementConfirm c ");
//		sql.append("INNER JOIN M_Movement m ON c.M_Movement_ID = m.M_Movement_ID AND m.IsActive = 'Y' ");
//		sql.append("INNER JOIN M_MovementLine l ON m.M_Movement_ID = l.M_Movement_ID AND l.IsActive = 'Y' ");
//		sql.append("INNER JOIN M_Locator ll ON l.M_LocatorTo_ID = ll.M_Locator_ID AND ll.IsActive = 'Y' ");
//		sql.append("INNER JOIN M_Locator ls ON l.M_Locator_ID = ls.M_Locator_ID ");
//		sql.append("INNER JOIN M_Warehouse a ON ls.M_Warehouse_ID = a.M_Warehouse_ID ");
//		sql.append("INNER JOIN AD_User sol ON m.soli_user_id = sol.AD_User_ID ");
//		sql.append("LEFT JOIN AD_User surte ON surte.AD_User_ID =m.surt_user_id ");
//		sql.append(" WHERE ll.M_Warehouse_ID = ? ");// #1
//		// sql.append(" AND m.isDevol " + tipo);
//		// verificamos que tipo de almacen solicito, eruiz
//		sql.append(" AND a.Consigna = ? ");// #2
//		sql.append(" AND c.IsActive = 'Y' ");
//		sql.append(" AND m.DocStatus = ? ");// #3
//											// MMovementConfirm.STATUS_InProgress
//		if (almSurte > 0) {
//			sql.append(" AND ls.M_Warehouse_ID = ? ");
//		}
//		if (typeWhs != null) {
//			//sql.append(" AND (a.type <> ?  or a.type is null)");// #2 
//			sql.append(onlyTrays);
//		}
//		// agregar validacion para que se filtren solo los movimientos que
//		// tengan detalle
//		sql.append("AND c.M_MovementConfirm_ID IN (SELECT distinct M_MovementConfirm_ID FROM M_MovementLineConfirm Where IsActive = 'Y') ");
//		if (fechaIni != null && fechaFin != null) {
//			sql.append("AND m.MovementDate between ? and ? ");
//		}
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "c"));
//		sql.append(" ORDER BY c.M_MovementConfirm_ID DESC");
//
//		List<Object> params = new ArrayList<Object>();
//		params.add(almacen);
//		params.add(consigna);
//		if (inProgress) {
//			params.add(MMovementConfirm.STATUS_InProgress);
//		} else {
//			params.add(MMovementConfirm.STATUS_Completed);
//		}
//		if (almSurte > 0) {
//			params.add(almSurte);
//		}
////		if (typeWhs != null) {
////			params.add(typeWhs);
////		}
//		
//		if (fechaIni != null && fechaFin != null) {
//			if (fechaIni.after(fechaFin)) {
//				params.add(TimeUtil.getInitialRangeT(ctx, fechaFin));
//				params.add(TimeUtil.getFinalRange(ctx, fechaIni));
//			} else {
//				params.add(TimeUtil.getInitialRangeT(ctx, fechaIni));
//				params.add(TimeUtil.getFinalRange(ctx, fechaFin));
//			}
//		}
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			/*
//			 * pstmt.setLong(1, almacen); pstmt.setString(2,
//			 * DB.TO_STRING(consigna)); if (inProgress) { pstmt.setString(3,
//			 * MMovementConfirm.STATUS_InProgress); } else { pstmt.setString(3,
//			 * MMovementConfirm.STATUS_Completed); } if (almSurte > 0) {
//			 * pstmt.setInt(4, almSurte); } if (typeWhs != null) {
//			 * pstmt.setString(2, DB.TO_STRING(typeWhs)); }
//			 */
//			DB.setParameters(pstmt, params);
//			rs = pstmt.executeQuery();
//
//			s_log.fine(sql + "   almacen: " + almacen + "\n\n");
//
//			while (rs.next()) {
//				final ConfirmaView confirma = new ConfirmaView();
//				confirma.setMovementConfirmID(rs
//						.getLong("M_MovementConfirm_ID"));
//				confirma.setDocumentNo(rs.getString("DocumentNo"));
//				confirma.setDocNoMovement(rs.getString("DocNoMovement"));
//				confirma.setDescription(rs.getString("Description"));
//				confirma.setLocator(rs.getString("Almacen"));
//				confirma.setMovementID(rs.getLong("M_Movement_ID"));
//				// confirma.setUser(rs.getString("Usuario"));
//				confirma.setUserSol(rs.getString("solicita"));
//				confirma.setUserSurte(rs.getString("surte"));
//				lista.add(confirma);
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "-- getMovementConfirm ", e);
//			throw e;
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return lista;
//	}

	// /**
	// * Obtenemos los movimientos pendientes de confirmar generados a partir de
	// una cuenta paciente
	// *
	// * @param empresa la empresa con la que se logeo
	// * @param organizacion la organizacion con la que se logeo
	// * @param locatorTo localizador del almacen
	// * @param tipo Si es una solicitud o una devolucion
	// * @param ctaPac Cuenta Paciente que origino el movimiento
	// * @return Una lista con los movimientos pendientes de confirmar
	// */
	// public static List<ConfirmaView> getMovementConfirmCtaPac(Properties ctx,
	// long almacen, String tipo, int ctaPac)
	// throws SQLException {
	// List<ConfirmaView> lista = new ArrayList<ConfirmaView>();
	//
	// //movimientos por empresa, organizacion y almacen (localizado)
	// String sql = "SELECT DISTINCT c.M_MovementConfirm_ID, c.DocumentNo, "
	// + "c.M_Movement_ID, m.Description, c.DocStatus, c.DocAction, "
	// +
	// "m.DocumentNo DocNoMovement, a.Name as Almacen, sol.Name solicita, surte.Name surte "
	// + "FROM M_MovementConfirm c "
	// + "INNER JOIN M_Movement m ON c.M_Movement_ID = m.M_Movement_ID "
	// + "INNER JOIN M_MovementLine l ON m.M_Movement_ID = l.M_Movement_ID "
	// + "INNER JOIN M_Locator ll ON l.M_LocatorTo_ID = ll.M_Locator_ID "
	// + "INNER JOIN M_Locator ls ON l.M_Locator_ID = ls.M_Locator_ID "
	// + "INNER JOIN M_Warehouse a ON ls.M_Warehouse_ID = a.M_Warehouse_ID "
	// + "INNER JOIN AD_User sol ON m.soli_user_id = sol.AD_User_ID "
	// + "INNER JOIN AD_User surte ON m.surt_user_id = surte.AD_User_ID "
	// + "WHERE c.AD_Client_ID IN(0, ?) AND "
	// + "c.AD_Org_ID IN(0, ?) AND "
	// + "ll.M_Warehouse_ID = ? AND "
	// + "m.EXME_CtaPac_ID = ? AND "
	// + "m.isDevol " + tipo + " AND "
	// + " c.IsActive = 'Y' AND m.IsActive = 'Y' AND "
	// + "l.IsActive = 'Y' AND ll.IsActive = 'Y' AND "
	// + "m.DocStatus = '" + MMovementConfirm.STATUS_InProgress + "' "
	// + "ORDER BY c.DocumentNo DESC";
	//
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// try {
	// pstmt = DB.prepareStatement(sql, null);
	// pstmt.setLong(1, Env.getAD_Client_ID(ctx));
	// pstmt.setLong(2, Env.getAD_Org_ID(ctx));
	// pstmt.setLong(3, almacen);
	// pstmt.setLong(4, ctaPac);
	// rs = pstmt.executeQuery();
	//
	// s_log.fine(sql + "   almacen: " + almacen + "\n\n");
	//
	// while (rs.next()){
	// ConfirmaView confirma = new ConfirmaView();
	// confirma.setMovementConfirmID(rs.getLong("M_MovementConfirm_ID"));
	// confirma.setDocumentNo(rs.getString("DocumentNo"));
	// confirma.setDocNoMovement(rs.getString("DocNoMovement"));
	// confirma.setDescription(rs.getString("Description"));
	// confirma.setLocator(rs.getString("Almacen"));
	// //confirma.setUser(rs.getString("Usuario"));
	// confirma.setUserSol(rs.getString("solicita"));
	// confirma.setUserSurte(rs.getString("surte"));
	// lista.add(confirma);
	// }
	//
	// } catch (SQLException e) {
	// s_log.log(Level.SEVERE, "-- getMovementConfirmCtaPac ", e);
	//
	// throw e;
	// } finally {
	// DB.close(rs,pstmt);
	// rs = null;
	// pstmt = null;
	// }
	// return lista;
	// }

	// GValdez se agrego un tercer al metodo getMovementConfirmDet(P) de
	// MMovementConfirm para controlar los detalles a confirmar
	// dependiendo del entorno en el que se mande llamar el metodo
	// Por MedSys (true) por Interfaz entre Almacenes Virtuales(false)
	/**
	 * Obtenemos el detalle para un movimiento pendiente de confirmar
	 * 
	 * @param movementConfirmID
	 *            el movimiento pendiente
	 * @param isWeb
	 *            si el entorno de llamado es a traves de MedSys
	 * @return Una lista con los movimientos pendientes de confirmaro
	 */
	public static List<ConfirmaDetView> getMovementConfirmDet(
			final long movementConfirmID, final boolean isWeb) throws Exception {
		return MMovementConfirm.getMovementConfirmDet(movementConfirmID, isWeb,
				null);
	}

	/**
	 * 
	 * @param isWeb false: es de interfaz
	 * @return
	 */
	public static String queryMovementConfirmDet(final boolean isWeb) {
		// movimientos por empresa, organizacion y almacen (localizado)
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ml.Description, ");
		sql.append(" ml.TargetQty, ml.TargetQty_Vol, ");
		sql.append(" l.ConfirmedQty, l.ConfirmedQty_Vol, ");
		sql.append(" l.ScrappedQty, l.ScrappedQty_Vol, ");
		sql.append(" ml.OriginalQty, ml.OriginalQty_Vol, ");
		sql.append(" p.M_Product_ID, p.value, ");
		sql.append(" p.Name ProdName, u.Name UomName, l.M_MovementLine_ID, ");
		sql.append("ml.numserie, "); // numero de serie

		// GValdez Si el metodo es llamado por interfaz agregamos los campos
		// necesarios al select
		if (!isWeb) {
			sql.append(" NVL(SC.Cantidad,0) cantidad, ");
			sql.append(" SC.Lot, SC.GuaranteeDate, ");
		}

		sql.append("p.Name ProdName, u.Name UomName, ");
		sql.append("l.M_MovementLine_ID, asi.LOT as lote, ");
		sql.append("to_char(asi.GuaranteeDate,'dd/MM/yyyy') as fechaLote, ");
		sql.append("ml.numserie, ml.pricelist as priceList, ");
		// numero de serie
		sql.append(" ml.op_uom, ");
		sql.append(" uSel.Name as UOMSel ");
		sql.append("FROM M_MovementLineConfirm l ");
		sql.append("INNER JOIN M_MovementLine ml ON l.M_MovementLine_ID = ml.M_MovementLine_ID ");
		sql.append("INNER JOIN M_Product p ON ml.M_Product_ID = p.M_Product_ID ");
		sql.append("INNER JOIN C_Uom u ON p.C_Uom_ID = u.C_Uom_ID ");

		// GValdez Si el metodo es llamado por interfaz agregamos la tabla de
		// interfaz
		if (!isWeb) {
			sql.append("INNER JOIN M_Movement MM on MM.M_MOVEMENT_ID = ml.m_movement_id ");
			sql.append("INNER JOIN SM_Confirma SC ON SC.DocumentNo = MM.DoumentNo  ");
			sql.append("		AND SC.PRODUCTO_VALUE = p.value ");
			sql.append("		AND SC.uom_value = u.name ");
		}
		sql.append("LEFT JOIN C_Uom uSel ON ml.op_uom  = uSel.C_Uom_ID  ");
		sql.append("LEFT JOIN M_AttributeSetInstance asi ON asi.M_ATTRIBUTESETINSTANCE_ID = ml.M_ATTRIBUTESETINSTANCE_ID ");
		sql.append("WHERE l.M_MovementConfirm_ID = ? ");
		sql.append("AND l.IsActive = 'Y' ");
		sql.append("ORDER BY p.Name ASC");
		return sql.toString();
	}

	/**
	 * 
	 * @param movementConfirmID
	 * @param isWeb
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<ConfirmaDetView> getMovementConfirmDet(
			final long movementConfirmID, final boolean isWeb,
			final String trxName) throws Exception {
		final List<ConfirmaDetView> lista = new ArrayList<ConfirmaDetView>();

		// movimientos por empresa, organizacion y almacen (localizado)
		final String sql = MMovementConfirm.queryMovementConfirmDet(isWeb);
		// StringBuilder sql = new StringBuilder("SELECT ml.Description, ")
		// .append(" ml.TargetQty, ml.TargetQty_Vol, ")
		// .append(" l.ConfirmedQty, l.ConfirmedQty_Vol, ")
		// .append(" l.ScrappedQty, l.ScrappedQty_Vol, ")
		// .append(" ml.OriginalQty, ml.OriginalQty_Vol, ")
		// .append(" p.M_Product_ID, p.value, ")
		// .append(" p.Name ProdName, u.Name UomName, l.M_MovementLine_ID, ")
		// .append("ml.numserie, "); //numero de serie
		//
		// //GValdez Si el metodo es llamado por interfaz agregamos los campos
		// necesarios al select
		// ((!isWeb) ?
		// sql.append(" NVL(SC.Cantidad,0) cantidad, SC.Lot, SC.GuaranteeDate, "):sql.append("")
		// )
		//
		// .append("p.Name ProdName, u.Name UomName, l.M_MovementLine_ID, asi.LOT as lote, to_char(asi.GuaranteeDate,'dd/MM/yyyy') as fechaLote, ")
		// .append("ml.numserie, ml.pricelist as priceList, ") //numero de serie
		// .append(" ml.op_uom, ")
		// .append(" uSel.Name as UOMSel ")
		// .append("FROM M_MovementLineConfirm l ")
		// .append("INNER JOIN M_MovementLine ml ON l.M_MovementLine_ID = ml.M_MovementLine_ID ")
		// .append("INNER JOIN M_Product p ON ml.M_Product_ID = p.M_Product_ID ")
		// .append("INNER JOIN C_Uom u ON p.C_Uom_ID = u.C_Uom_ID ");
		//
		// //GValdez Si el metodo es llamado por interfaz agregamos la tabla de
		// interfaz
		// ((!isWeb) ?
		// sql.append("INNER JOIN M_Movement MM on MM.M_MOVEMENT_ID = ml.m_movement_id "
		// +
		// "INNER JOIN SM_Confirma SC ON SC.DocumentNo = MM.DocumentNo  " +
		// "		AND SC.PRODUCTO_VALUE = p.value " +
		// "		AND SC.uom_value = u.name "):sql.append(" ") )
		// .append("LEFT JOIN C_Uom uSel ON ml.op_uom  = uSel.C_Uom_ID  ")
		// .append("LEFT JOIN M_AttributeSetInstance asi ON asi.M_ATTRIBUTESETINSTANCE_ID = ml.M_ATTRIBUTESETINSTANCE_ID ")
		// .append("WHERE l.M_MovementConfirm_ID = ? ")
		// .append("AND l.IsActive = 'Y' ")
		// .append("ORDER BY l.M_MovementLine_ID DESC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setLong(1, movementConfirmID);
			rs = pstmt.executeQuery();

			s_log.fine(sql + "   movementConfirmID: " + movementConfirmID
					+ "\n\n");

			int i = 1;
			while (rs.next()) {

				ConfirmaDetView confirma = new ConfirmaDetView();
				confirma.setRSet(rs, isWeb, i);
				// confirma.setMovementLineID(rs.getLong("M_MovementLine_ID"));
				// confirma.setLine(i*10);
				// confirma.setProductID(rs.getLong("M_Product_ID"));
				// confirma.setProdValue(rs.getString("value"));
				// confirma.setProdName(rs.getString("ProdName"));
				// confirma.setUomName(rs.getString("UomName"));
				//
				// confirma.setOriginalQty(rs.getBigDecimal("OriginalQty"));
				// confirma.setTargetQty(rs.getBigDecimal("TargetQty"));
				//
				// if (!isWeb){
				//
				// confirma.setGuaranteeDate(rs.getString("GuaranteeDate"));
				// confirma.setConfirmedQty(rs.getBigDecimal("Cantidad").compareTo(BigDecimal.ZERO)>0
				// ? rs.getBigDecimal("Cantidad") : BigDecimal.ZERO);
				// } else {
				// confirma.setConfirmedQty(rs.getBigDecimal("ConfirmedQty").subtract(rs.getBigDecimal("ScrappedQty")));
				// }
				//
				// confirma.setScrappedQty(rs.getBigDecimal("ScrappedQty"));
				// confirma.setDescription(rs.getString("Description"));
				// confirma.setDevueltaQty(confirma.getOriginalQty().subtract(confirma.getConfirmedQty()));//Noelia
				// se agrega cantidad devuelta para ceye
				// confirma.setDevolverQty(rs.getBigDecimal("OriginalQty"));
				// confirma.setNumSerie(rs.getString("numSerie")); //numero de
				// serie
				// confirma.setPrice(rs.getDouble("priceList"));//precio del
				// producto
				//
				// confirma.setLote(rs.getString("lote"));
				// confirma.setFechaLote(rs.getString("fechaLote"));
				//
				// /**
				// * @rsolorzano
				// * campos para unidad de medida
				// */
				// if (isWeb){
				// confirma.setConfirmedQty_Vol(rs.getBigDecimal("ConfirmedQty_Vol").subtract(rs.getBigDecimal("ScrappedQty_Vol")));
				// }else{
				// confirma.setConfirmedQty_Vol(BigDecimal.ZERO);
				// }
				// confirma.setOriginalQty_Vol(rs.getBigDecimal("OriginalQty_Vol"));
				// confirma.setTargetQty_Vol(rs.getBigDecimal("TargetQty_Vol"));
				// confirma.setScrappedQty_Vol(rs.getBigDecimal("ScrappedQty_Vol"));
				// confirma.setDevueltaQty_Vol(rs.getBigDecimal("OriginalQty_Vol").subtract(confirma.getConfirmedQty_Vol()));
				// confirma.setDevolverQty_Vol(rs.getBigDecimal("OriginalQty_Vol"));
				// confirma.setUomSel(rs.getInt("op_uom"));
				// confirma.setUomSelName(rs.getString("UOMSel"));

				lista.add(confirma);
				i++;
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "", e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
	}

	// /**
	// * Devolvemos el almacen desde el que se va a surtir
	// * la confirmacion.
	// * @param ctx
	// * @param M_MovementConfirm_ID
	// * @param trxName
	// * @return
	// * @throws SQLException
	// */
	// public static MWarehouse getWarehouse(Properties ctx, int
	// M_MovementConfirm_ID, String trxName) throws SQLException {
	// StringBuffer sql = new StringBuffer();
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// MWarehouse almacen = null;
	//
	// try {
	//
	// sql.append("SELECT DISTINCT(ml.M_Locator_ID), w.* ")
	// .append(" FROM M_Warehouse w ")
	// .append("INNER JOIN M_Locator l on l.M_Warehouse_ID = w.M_Warehouse_ID ")
	// .append("INNER JOIN M_MovementLine ml ON ml.M_Locator_ID = l.M_Locator_ID ")
	// .append("INNER JOIN M_MovementLineConfirm mlc ON mlc.M_MovementLine_ID = ml.M_MovementLine_ID ")
	// .append("WHERE mlc.M_MovementConfirm_ID = ")
	// .append(M_MovementConfirm_ID);
	//
	// pstmt = DB.prepareStatement(sql.toString(), trxName);
	//
	// rs = pstmt.executeQuery();
	//
	// if(rs.next()) {
	// almacen = new MWarehouse(ctx, rs, trxName);
	// }
	//
	// } catch (SQLException e) {
	// s_log.log(Level.SEVERE, "getWarehouse (" + sql + ")", e);
	// throw e;
	// } finally {
	// DB.close(rs,pstmt);
	// rs = null;
	// pstmt = null;
	// }
	//
	// return almacen;
	// }
	// expert

	// //expert
	// /**
	// * Obtenemos el detalle del movimiento.
	// * @param ctx
	// * @param M_Movement_ID
	// * @param trxName
	// * @return
	// * @throws SQLException
	// */
	// public static boolean getDetailNumSerie(Properties ctx, int
	// M_MovementConfirm_ID, String trxName)
	// throws SQLException {
	// StringBuffer sql = new StringBuffer();
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// boolean numSerie = false;
	//
	// try {
	//
	// sql.append("SELECT ml.* ")
	// .append("FROM M_MovementLineConfirm l ")
	// .append("INNER JOIN M_MovementLine ml ON l.M_MovementLine_ID = ml.M_MovementLine_ID ")
	// .append("INNER JOIN M_Product p ON ( ml.M_Product_ID = p.M_Product_ID) ")
	// .append("WHERE l.M_MovementConfirm_ID = ? AND l.IsActive = 'Y' AND ")
	// .append("p.IsActive = 'Y' AND p.IsNumSerie = 'Y' ORDER BY ml.Line");
	//
	// s_log.finer("getDetail : " + sql);
	//
	// pstmt = DB.prepareStatement(sql.toString(), trxName);
	// pstmt.setInt(1, M_MovementConfirm_ID);
	// rs = pstmt.executeQuery();
	//
	// if (rs.next()) {
	// numSerie = true;
	// }
	//
	// } catch (SQLException e) {
	// s_log.log(Level.SEVERE, "getDetailNumSerie (" + sql + ")", e);
	// throw e;
	// } finally {
	// DB.close(rs,pstmt);
	// rs = null;
	// pstmt = null;
	// }
	//
	// return numSerie;
	// }

	// GValdez se agrego un tercer al metodo getMovementConfirmDet(P) de
	// MMovementConfirm para controlar los detalles a confirmar
	// dependiendo del entorno en el que se mande llamar el metodo
	// Por MedSys (true) por Interfaz entre Almacenes Virtuales(false)
	/**
	 * Obtenemos el detalle para un movimiento de almacen en consigna pendiente
	 * de confirmar
	 * 
	 * @param movementConfirmID
	 *            el movimiento pendiente
	 * @param isWeb
	 *            si el entorno de llamado es a traves de MedSys
	 * @return Una lista con los movimientos pendientes de confirmar
	 * @deprecated
	 */
	@Deprecated
	public static List<ConfirmaDetView> getMovementConfirmDetP(
			long movementConfirmID, boolean isWeb) throws Exception {
		List<ConfirmaDetView> lista = new ArrayList<ConfirmaDetView>();

		// movimientos por empresa, organizacion y almacen (localizado)
		StringBuilder sql = new StringBuilder(
				"SELECT ml.Description, ml.TargetQty, ")
				.append("l.ConfirmedQty, l.ScrappedQty, ml.OriginalQty, p.M_Product_ID, ")
				.append("p.Name ProdName, p.value as ProdValue, u.Name UomName, l.M_MovementLine_ID ")
				.append(", ml.numserie, ml.pricelist as priceList "); // numero
																		// de
																		// serie
																		// y
																		// precio
																		// de
																		// lista

		// GValdez Si el metodo es llamado por interfaz agregamos los campos
		// necesarios al select
		((!isWeb) ? sql
				.append(", NVL(SC.Cantidad,0), SC.Lote, SC.GuaranteeDate ")
				: sql.append(""))
				.append(", asi.LOT as lote, to_char(asi.GuaranteeDate,'dd/MM/yyyy') as fechaLote ")
				.append("FROM M_MovementLineConfirm l ");

		// GValdez Si el metodo es llamado por interfaz agregamos la tabla de
		// interfaz
		((!isWeb) ? sql
				.append("LEFT Join SM_Confirma SC ON SC.DocumentNo = l.M_MovementConfirm_ID")
				: sql.append(""))
				.append("INNER JOIN M_MovementLine ml ON l.M_MovementLine_ID = ml.M_MovementLine_ID ")
				.append("INNER JOIN M_Product p ON ml.M_Product_ID = p.M_Product_ID ")
				.append("INNER JOIN C_Uom u ON p.C_Uom_ID = u.C_Uom_ID ")
				.append("LEFT JOIN M_AttributeSetInstance asi ON asi.M_ATTRIBUTESETINSTANCE_ID = ml.M_ATTRIBUTESETINSTANCE_ID ")
				.append("WHERE l.M_MovementConfirm_ID = ? ")
				.append("AND l.IsActive = 'Y' ")
				.append("ORDER BY l.M_MovementLine_ID DESC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, movementConfirmID);
			rs = pstmt.executeQuery();

			s_log.fine(sql + "   movementConfirmID: " + movementConfirmID
					+ "\n\n");

			int i = 1;
			while (rs.next()) {
				ConfirmaDetView confirma = new ConfirmaDetView();
				confirma.setMovementLineID(rs.getLong("M_MovementLine_ID"));
				confirma.setLine(i * 10);
				confirma.setProductID(rs.getLong("M_Product_ID"));
				confirma.setProdName(rs.getString("ProdName"));
				confirma.setProdValue(rs.getString("ProdValue"));
				confirma.setUomName(rs.getString("UomName"));
				confirma.setOriginalQty(rs.getBigDecimal("OriginalQty"));
				confirma.setTargetQty(rs.getBigDecimal("TargetQty"));
				confirma.setConfirmedQty(rs.getBigDecimal("ConfirmedQty")
						.subtract(rs.getBigDecimal("ScrappedQty")));

				// GValdez Si el metodo es llamado por interfaz agregamos los
				// campos necesarios al MMovementLine
				// Y el targetQty pasa a ser lo especificado en interfaz
				if (!isWeb) {

					confirma.setGuaranteeDate(rs.getString("GuaranteeDate"));
					confirma.setConfirmedQty((rs.getBigDecimal("Cantidad")
							.compareTo(BigDecimal.ZERO) > 0 ? rs
							.getBigDecimal("Cantidad") : BigDecimal.ZERO));
				} else
					confirma.setConfirmedQty(rs.getBigDecimal("ConfirmedQty")
							.subtract(rs.getBigDecimal("ScrappedQty")));

				// confirma.setScrappedQty(rs.getLong("ScrappedQty"));
				confirma.setDescription(rs.getString("Description"));
				confirma.setDevueltaQty(rs.getBigDecimal("OriginalQty")
						.subtract(confirma.getConfirmedQty()));// Noelia se
																// agrega
																// cantidad
																// devuelta para
																// ceye
				confirma.setDevolverQty(rs.getBigDecimal("OriginalQty"));
				confirma.setNumSerie(rs.getString("numSerie")); // numero de
																// serie
				confirma.setPrice(rs.getDouble("priceList"));// precio del
																// producto

				confirma.setLote(rs.getString("lote"));
				confirma.setFechaLote(rs.getString("fechaLote"));

				lista.add(confirma);
				i++;
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "", e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
	}

//	/**
//	 * Before Save
//	 * 
//	 * @param newRecord
//	 *            new
//	 * @return true
//	 */
//	protected boolean beforeSave(final boolean newRecord) {
//		// actualizamos el movimiento con el usuarioque confirmar
//		// si estatus es CO, y no es nuevo .- Lama
//		if (!newRecord && is_ValueChanged("DocStatus") && MMovementConfirm.DOCSTATUS_Completed.equals(getDocStatus())) {
//			MMovement mov = new MMovement(getCtx(), getM_Movement_ID(), get_TrxName());
//			mov.setConf_User_ID(Env.getAD_User_ID(getCtx()));
//			if (!mov.save()) {
//				return false;
//			}
//		}
//		// fin Lama
//
//		return true;
//	} // beforeSave
	
	/**
	 * 
	 * @param ctx
	 * @param warehouseId
	 * @param docNo
	 * @param date
	 * @param date2
	 * @param trxName
	 * @return
	 */
	public static List<MMovement> getMovementConfirmations(Properties ctx, int warehouseId, String docNo, Timestamp date, Timestamp date2, String trxName) {
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(warehouseId);
		params.add(MMovement.DOCSTATUS_InProgress);
		
		StringBuilder sql = new StringBuilder();
		sql.append("  mc.isactive = 'Y' AND ");
		sql.append("  m_movement.m_warehouseto_id = ? ");
		sql.append("  AND m_movement.DocStatus = ? ");
		
		if(StringUtils.isNotBlank(docNo)){
			sql.append("  AND lower(m_movement.documentno) = ? ");
			params.add(docNo.toLowerCase());
		}else {
			params.add(date);
			params.add(date2);
			sql.append("  and m_movement.movementdate BETWEEN ");
			sql.append("  ? AND ? ");
			
		}
		
		Query query = new Query(ctx, MMovement.Table_Name, sql.toString(),trxName);
		query.setParameters(params);
		query.setOnlyActiveRecords(true);
		query.addAccessLevelSQL(true);
		query.setJoins(new StringBuilder(" INNER JOIN M_MovementConfirm mc ON m_movement.m_movement_id = mc.m_movement_id "));
		query.setOrderBy(" m_movement.documentno desc ");
		return query.list();
	}

	/**
	 * Id del movimiento
	 * 
	 * @param ctx
	 * @param mMovementID
	 * @param trxName
	 * @return
	 */
	public static int getMovementId(final Properties ctx,
			final int mMovementID, final String trxName) {
		return new Query(ctx, MMovementConfirm.Table_Name, " M_Movement_ID=? ",
				trxName)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setParameters(mMovementID)//
				.firstId();

	}
	
	
	/**
	 * Confirmacion de la solicitud de movimiento
	 * 
	 * @param ctx Contexto
	 * @param movement Movimiento (Encabezado)
	 * @param lines Lineas del movimiento
	 * @param trxName
	 *            Nombre de trx
	 * @return ErrorList
	 */
	public static ErrorList confirm(Properties ctx, MMovement movement, List<MovementLine> lines, String trxName) {
		final ErrorList errorList = new ErrorList();
		try {
			// buscamos la confirmacion
			final MMovementConfirm movConfirm = get(ctx, movement.getM_Movement_ID(), trxName);
			if (movConfirm == null || movConfirm.is_new()) {
				errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(ctx, "error.traspasos.noMovementConfirm", movement.getDocumentNo())));
			} else { // validamos los encabezado
				movement.setDateReceived(Env.getCurrentDate());
				movConfirm.validateConfirm(errorList, movement);
			}
			// Configuracion de expediente clinico
			final MConfigEC configEC = MConfigEC.get(ctx);
			if (configEC == null) {
				errorList.add(new Error(Error.CONFIGURATION_ERROR, getAppMsg(ctx, "error.citas.noConfigEC")));
			} else if (lines.isEmpty()) {// has detail
				errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(ctx, "error.traspasos.lines")));
			}
			// si no hay errores
			if (errorList.isEmpty()) {
//				// en caso que requiera backorder
//				final List<MovementLine> backorderLines = new ArrayList<MovementLine>();
				final List<TransferBean> orderLines = new ArrayList<TransferBean>();
				final List<MCtaPacDet> charges = new ArrayList<MCtaPacDet>();
				final boolean createOrder = movement.getWarehouseCosigna()!=null;
				for (MovementLine line : lines) {
									
					// omitimos si se surtio en 0
					if(line.getTargetQty().compareTo(BigDecimal.ZERO) <= 0) {
						// si Genera Back Order y si hay diferencias entre la cantidad surtida y la cantidad conformada 
//						if (errorList.isEmpty() && configEC.isGeneraBO()) {
//							backorderLines.add(line);
//						}
						continue;
					}
					final MMovementLineConfirm movLineConf = MMovementLineConfirm.getFromMovLine(ctx, line.getId(), trxName);
					//Validar que los localizadores no sean iguales
					if(line.getLocatorFromId() == line.getLocatorToId()){ 
						errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(ctx, "error.noTransferLocator", line.getProduct().getName())));
						break;
					} else if (movLineConf == null || movLineConf.is_new()) {
						errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(ctx, "error.traspasos.noMovementConfirm", movement.getDocumentNo())));
						break;
					}
					// M_MovementLine.OriginalQty  - Solicitado
					// M_MovementLine.MovementQty/TargeQty/ConfirmedQty  - Surtido
					// M_MovementLine.ScrappedQty - Diferencia entre surtido - confirmado (lo guarda el complete)
					// M_MovementLineConfirm.TargetQty/ConfirmedQty - Surtido
					// M_MovementLineConfirm.ScrappedQty - Diferencia entre surtido - confirmado
					
					if (MLocator.getWarehouse(ctx, line.getLocatorFromId(), null).isControlExistencias()) {
						/* MStorage para almacen que surte. Solamente se le resta la cantidad surtida a la columna qtyReserved
						 * de manera que la cantidad quede igual que antes de haber hecho la confirmacion, y asi evitar conflictos
						 * con el proceso natural de Compiere */
						// Alejandro. Si no se pudo actualizar la cantidad reservada, regresa el error.
						if (!MStorage.add(ctx, //
							movement.getM_Warehouse_ID(),// warehouse 
							line.getLocatorFromId(), //
							line.getProduct().getM_Product_ID(), //
							line.getLotId(), line.getLotId(), BigDecimal.ZERO, line.getConfirmedQty().negate(), BigDecimal.ZERO, trxName)) {// cantidad Original 10
							errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(ctx, "error.confirma.cantReservada")));
							break;
						}
					}
					BigDecimal confirmedQty,confirmedQtyVol ;
					/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
					if(configEC.isCargaDiferAlm()){// Al que surte
						//M_MovementLineConfirm.ConfirmedQty-M_MovementLineConfirm.ScrappedQty
						confirmedQty = movLineConf.getConfirmedQty().subtract(movLineConf.getScrappedQty());//0-0
						confirmedQtyVol = movLineConf.getConfirmedQty_Vol().subtract(movLineConf.getScrappedQty_Vol());
					} else { // Al que confirma/solicita
						//M_MovementLineConfirm.ConfirmedQty > 0 . else 0
						confirmedQty = movLineConf.getConfirmedQty().compareTo(BigDecimal.ZERO) > 0 ? movLineConf.getConfirmedQty() : BigDecimal.ZERO;
						confirmedQtyVol = movLineConf.getConfirmedQty_Vol().compareTo(BigDecimal.ZERO) > 0 ? movLineConf.getConfirmedQty_Vol() : BigDecimal.ZERO;
					}
					// confirmado por el usario - M_MovementLineConfirm.ConfirmedQty
					BigDecimal scrappedQty = line.getConfirmedQty().subtract(confirmedQty);//140-0
					BigDecimal scrappedVolQty = line.getConfirmedVolQty().subtract(confirmedQtyVol);

					// Cambios generados por confirmacion de productos 
					// con diferencia que genera una salida al gasto
					// A quien se le cargaran las diferencias
					// Se genera el inv fisico en el almacen (arangel)
					if (configEC.isCargaDiferAlm()) { // para almacen que surte
						movLineConf.setDifferenceQty(scrappedQty);
						movLineConf.setDifferenceQty_Vol(scrappedVolQty);
						
						movLineConf.setScrappedQty(BigDecimal.ZERO);
						movLineConf.setScrappedQty_Vol(BigDecimal.ZERO);
						// confirmado por el usario
						movLineConf.setConfirmedQty(line.getConfirmedQty());
						movLineConf.setConfirmedQty_Vol(line.getConfirmedVolQty());
					} else { // para almacen que solicita/confirma
						movLineConf.setDifferenceQty(BigDecimal.ZERO);
						movLineConf.setDifferenceQty_Vol(BigDecimal.ZERO);
						
						movLineConf.setScrappedQty(scrappedQty);
						movLineConf.setScrappedQty_Vol(scrappedVolQty);

						movLineConf.setConfirmedQty(confirmedQty);
						movLineConf.setConfirmedQty_Vol(confirmedQtyVol);
					}
					// se actualiza el moveLine
					if (!movLineConf.save(trxName)) {
						errorList.add(new Error(Error.EXCEPTION_ERROR, getAppMsg(ctx, "error.confirma.guardaLinea", line.getProduct().getName())));
						break;
					}
					// Hacer los cargos a la cuenta paciente
					if (errorList.isEmpty() 
						&& configEC.isAplicaPedCtaPac() // esta configurado
						&& movement.getEXME_CtaPac_ID() > 0 // tiene cuenta paciente
						&& movement.getEXME_PrescRXDet_ID() <= 0 // no es de farmacia TODO
						&& movement.getEXME_ProgQuiro_ID() <= 0 // no es charolas/esterilizacion/etc
						&& !movement.isDevol()
						&& line.getConfirmedQty().compareTo(BigDecimal.ZERO) > 0) {
						// se carga la cantidad confirmada por usuario
						line.setChargeQty(line.getConfirmedQty());
						line.setChargeVolQty(line.getConfirmedVolQty());
						final MCtaPacDet charge = movement.createMovementLineCharge(errorList, line, trxName);
						if(errorList.isEmpty() && charge != null){
							charges.add(charge);
						}						
					}
//					// si Genera Back Order y si hay diferencias entre la cantidad surtida y la cantidad conformada 
//					if (errorList.isEmpty() && configEC.isGeneraBO() && line.getDifferenceQty().compareTo(BigDecimal.ZERO) > 0) {
//						backorderLines.add(line);
//					}
					// si genera M_Order y producto es Consigna
					if(errorList.isEmpty() && createOrder && line.getProduct().getProdOrg().isConsigna()) {
						MMovementLine movLine = new MMovementLine(ctx, line.getId(), trxName);
						movLine.setMovement(movement);
						orderLines.add(movLine.createTransferBean(movLineConf));
					}
				}
				movement.setProdOrTray(true);//arangel: se usa para cargos. ¿?
				// se completa la confirmacion
				completeModelWF(errorList, movConfirm, trxName);
				// se completa el encabezado
				completeModelWF(errorList, movement, trxName);
//				// Generar backorder 
//				if(errorList.isEmpty() && !backorderLines.isEmpty()) {
//					errorList.getList().addAll(MMovement.createBackorder(ctx, movement, backorderLines, trxName).getList());
//				}
				//Completa el inventario fisico en caso de que se haya generado (arangel)
				if(errorList.isEmpty() && movConfirm.getM_Inventory_ID() > 0) {
					MInventory inventory = new MInventory(ctx, movConfirm.getM_Inventory_ID(), trxName);
					completeModelWF(errorList, inventory, trxName);
					// se comenta, porque las existencias ya se ajustaron a este punto,
					// siempre marca error y depende de la configuracion para brincar la validacion,
					// la cantidad disponible es la misma para ambos casos (ConfigEc.isCargaDiferAlm)
					// if(errorList.isEmpty()) {
					// Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto
					// for (MInventoryLine invLines : inventory.getLines(true)) {
					// // Como no se valido lass existencias en el before se valida en este punto
					// if(!invLines.validarExistencias(false)){
					// }
					// }
				}
				// FIXME: Cambiar --> crear objetos inout por linea inout x cada linea
//				if (errorList.isEmpty() && !charges.isEmpty()) {
//					final Inventory mInventory = new Inventory(ctx, movement.getEXME_CtaPac_ID(), X_EXME_ActPacienteInd.Table_ID);
//					if (!mInventory.createLinesInOut(charges, false, trxName)) {
//						errorList.add(Error.EXCEPTION_ERROR, mInventory.getErrors().toString());
//					} else if(!mInventory.getErrores().isEmpty()) {
//						errorList.add(Error.EXCEPTION_ERROR, mInventory.getErrors().toString());
//					}
//				}
//				
				// Consigna (generar MOrder)
				if (errorList.isEmpty() && createOrder && !orderLines.isEmpty()) {
					errorList.getList().addAll(MOrder.createMaterialReceipt(movement, orderLines).getList());
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "-- confirm : ", e);
			errorList.add(Error.EXCEPTION_ERROR, e.getMessage());
		}
		return errorList;
	}
	
	/**
	 * Validaciones previas a la confirmacion
	 * 
	 * @param errorList
	 * @param movement
	 * @param lines
	 */
	private void validateConfirm(ErrorList errorList, MMovement movement) {
		// movement exist
		if (movement == null || movement.is_new()) {
			// no se encontro la solicitud relacionada al a confrimacion
			errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.noMovement", getDocumentNo())));
		}
		// movement is not confirmed already.
		else if (MMovement.DOCSTATUS_Voided.equals(movement.getDocStatus()) || MMovement.DOCSTATUS_Voided.equals(getDocStatus())) {
			// no se puede confirmar la solicitud de productos porque esta cancelada
			errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.isCanceled", movement.getDocumentNo())));
		}
		// movement is not confirmed already.
		else if (MMovement.DOCSTATUS_Completed.equals(movement.getDocStatus()) || MMovement.DOCSTATUS_Closed.equals(getDocStatus())) {
			errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.isConfirmado", movement.getDocumentNo())));
		}
		// encounter is not closed
		else if (movement.getEXME_CtaPac_ID() > 0
			&& DB.getSQLValueTS(null, "SELECT FechaCierre FROM EXME_CtaPac WHERE EXME_CtaPac_ID=?", movement.getEXME_CtaPac_ID()) != null) {
			errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.actualizaCama.ctapac.closed")));
		} else {
			// validar que el periodo para la fecha del movimiento y la fecha del confirmacion se encuentre abierto
			String docBaseType = MDocType.DOCBASETYPE_MaterialMovement;
			int adOrgId = Env.getAD_Org_ID(getCtx());
			// se valida solo la fecha de recepcion (confirmacion) , para que se poste esa fecha, en cambio de mes
			boolean openPeriod = // MPeriod.isOpen(getCtx(), movement.getMovementDate(), docBaseType, adOrgId) &&//
				 MPeriod.isOpen(getCtx(), movement.getDateReceived(), docBaseType, adOrgId);
			if (!openPeriod) {
				errorList.add(new Error(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.perNoAbierto")));
			}
		}
	}

	/**
	 * Dispara el workflow con la accion: Completar
	 * valida que el estatus sea el correcto y actualiza la informacion del objeto
	 * 
	 * @param errorList Listado de errores
	 * @param poModel Objeto de tipo DocAction y PO
	 * @param trxName Transaccion
	 */
	public static void completeModelWF(ErrorList errorList, DocAction poModel, String trxName) {
		((PO) poModel).startWorkflow(errorList,  DocAction.ACTION_Complete, DocAction.STATUS_Completed, trxName);
	}

	/**
	 * Buscar los datos de nombres de un movimiento en especifico
	 * @param ctx: Contexto
	 * @param movementId: Movimiento
	 * @return Movimiento con sus datos de confirmacion
	 */
	public static List<MMovement> getMovementsConfirm(final Properties ctx, final int movementId) {
		final List<Object> paramsWhere = new ArrayList<Object>();
		paramsWhere.add(movementId);
		return MMovementConfirm.getMovementsConfirm(ctx, " AND m.M_Movement_ID = ? ", paramsWhere);
	}

	/**
	 * Buscar de movimientos confirmados
	 * @param ctx: Contexto
	 * @param where: condiciones de la consulta
	 * @param params: parametros
	 * @return Listado de movimientos 
	 */
	public static List<MMovement> getMovementsConfirm(final Properties ctx
			, final String where
			, final List<?>  params) {
		
		final List<MMovement> lista = new ArrayList<MMovement>();
		// movimientos por empresa, organizacion y almacen (localizado)
		final StringBuilder sql = new StringBuilder()
		.append("SELECT m.M_Movement_ID ")
		.append(", a1.Name as Almacen1 ")
		.append(", a2.Name as Almacen2 ")
		.append(", sol.Name solicita ")//#1
		.append(", surte.Name surte  ")//#2
		.append(", COALESCE(cta.DocumentNo,' ') as ctapac ")//#3
		.append(", COALESCE(pac.Nombre_Pac,' ') as paciente ")//#4
		.append(", prog.FechaProg               as fechaProg ")//#5
		.append(", COALESCE(quir.Name,' ')      as quirofano ")//#6
		.append(", COALESCE(med.Nombre_Med,' ') as medico ")//#7
		.append(" FROM M_MovementConfirm c ")
		.append(" INNER JOIN M_Movement   m ON c.M_Movement_ID = m.M_Movement_ID AND m.IsActive = 'Y' AND m.DocStatus IN ('CO') ")
		.append(" INNER JOIN M_Warehouse a1 ON m.M_Warehouse_ID = a1.M_Warehouse_ID ")
		.append(" INNER JOIN M_Warehouse a2 ON m.M_WarehouseTo_ID = a2.M_Warehouse_ID ")//TODO EN ESPERA DE SCRIPT
		.append(" INNER JOIN AD_User    sol ON m.soli_user_id = sol.AD_User_ID ")
		.append(" INNER JOIN AD_User  surte ON surte.AD_User_ID =m.surt_user_id ")
		.append(" LEFT JOIN EXME_CtaPac     cta ON cta.EXME_CtaPac_ID     = m.EXME_CtaPac_ID ")
		.append( "LEFT JOIN EXME_Paciente   pac ON pac.EXME_Paciente_ID   = cta.EXME_Paciente_ID ")
		.append(" LEFT JOIN EXME_ProgQuiro prog ON prog.EXME_ProgQuiro_ID = m.EXME_ProgQuiro_ID ")
		.append(" LEFT JOIN EXME_Quirofano quir ON quir.EXME_Quirofano_ID = prog.EXME_Quirofano_ID ")
		.append(" LEFT JOIN EXME_Medico     med ON med.EXME_Medico_ID     = prog.EXME_Medico_ID ")
		.append(" WHERE c.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_M_MovementConfirm.Table_Name, "c"))
		.append(" AND   c.DocStatus IN ('CO') ")//
		.append(where)
		.append(" ORDER BY c.M_MovementConfirm_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				final MMovement mMovement = new MMovement(ctx, rs.getInt("M_Movement_ID"), null);
				
				final MovementBean movementBean = new MovementBean();
				movementBean.setSoliUserName(rs.getString("solicita"));
				movementBean.setSurtUserName(rs.getString("surte"));
				movementBean.setCtaPacDocumentNo(rs.getString("ctapac") + " " + SecureEngine.decrypt(rs.getString("paciente")));
				movementBean.setQuirofanoName(rs.getString("quirofano"));
				movementBean.setMedicoNombreMed(rs.getString("medico"));
				movementBean.setDateMovProg(rs.getTimestamp("fechaProg") == null ? Constantes.getSDFDateTime(ctx).format(mMovement.getMovementDate()) : Constantes.sdfFecha(ctx).format(rs.getTimestamp("fechaProg")));
				movementBean.setSoliWarehouseName(rs.getString("Almacen1"));
				movementBean.setSurtWarehouseName(rs.getString("Almacen2"));
				
				mMovement.setConfirmationData(movementBean);
				lista.add(mMovement);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
} // MMovementConfirm
