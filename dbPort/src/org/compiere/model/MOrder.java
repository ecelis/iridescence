/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.TransferBean;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.DynamicModel;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Order Model. Please do not set DocStatus and C_DocType_ID directly. They are
 * set in the process() method. Use DocAction and C_DocTypeTarget_ID instead.
 * 
 * @author Jorg Janke
 * @version $Id: MOrder.java,v 1.4 2006/08/11 02:26:22 mrojas Exp $
 */
public class MOrder extends X_C_Order implements DocAction {

	/** serialVersionUID */
	private static final long serialVersionUID = -2899797672994858330L;
	/** log */
	static CLogger log = CLogger.getCLogger(MOrder.class);

	/**
	 * Create new Order by copying
	 * 
	 * @param from
	 *            order
	 * @param dateDoc
	 *            date of the document date
	 * @param C_DocTypeTarget_ID
	 *            target document type
	 * @param isSOTrx
	 *            sales order
	 * @param counter
	 *            create counter links
	 * @param copyASI
	 *            copy line attributes Attribute Set Instance, Resaouce
	 *            Assignment
	 * @param trxName
	 *            trx
	 * @return Order
	 */
	public static MOrder copyFrom(MOrder from, Timestamp dateDoc,
			int C_DocTypeTarget_ID, boolean isSOTrx, boolean counter,
			boolean copyASI, String trxName) {
		MOrder to = new MOrder(from.getCtx(), 0, trxName);
		to.set_TrxName(trxName);
		PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.set_ValueNoCheck("C_Order_ID", I_ZERO);
		to.set_ValueNoCheck("DocumentNo", null);
		//
		to.setDocStatus(DOCSTATUS_Drafted); // Draft
		to.setDocAction(DOCACTION_Complete);
		//
		to.setC_DocType_ID(0);
		to.setC_DocTypeTarget_ID(C_DocTypeTarget_ID);
		to.setIsSOTrx(isSOTrx);
		//
		to.setIsSelected(false);
		to.setDateOrdered(dateDoc);
		to.setDateAcct(dateDoc);
		to.setDatePromised(dateDoc); // assumption
		to.setDatePrinted(null);
		to.setIsPrinted(false);
		//
		to.setIsApproved(false);
		to.setIsCreditApproved(false);
		to.setC_Payment_ID(0);
		to.setC_CashLine_ID(0);
		// Amounts are updated when adding lines
		to.setGrandTotal(Env.ZERO);
		to.setTotalLines(Env.ZERO);
		//
		to.setIsDelivered(false);
		to.setIsInvoiced(false);
		to.setIsSelfService(false);
		// to.setIsTransferred (false); // MODIFICACION: SE CAMBIO EN COMPIERE
		// DE YES/NO A LISTA
		to.setIsTransferred(ISTRANSFERRED_PendingTransfer);
		to.setPosted(false);
		to.setProcessed(false);
		to.setisBeingUsedInOut(false);
		if (counter) {
			to.setRef_Order_ID(from.getC_Order_ID());
		} else {
			to.setRef_Order_ID(0);
		}

		if (!to.save(trxName)) {
			throw new IllegalStateException("Could not create Order");
		}
		if (counter) {
			from.setRef_Order_ID(to.getC_Order_ID());
		}

		if (to.copyLinesFrom(from, counter, copyASI) == 0) {
			throw new IllegalStateException("Could not create Order Lines");
		}

		return to;
	} // copyFrom

	/**************************************************************************
	 * Default Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param C_Order_ID
	 *            order to load, (0 create new order)
	 * @param trxName
	 *            trx name
	 */
	public MOrder(Properties ctx, int C_Order_ID, String trxName) {
		super(ctx, C_Order_ID, trxName);
		// New
		if (C_Order_ID == 0) {
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DOCACTION_Prepare);
			setDeliveryRule(DELIVERYRULE_Availability);
			setFreightCostRule(FREIGHTCOSTRULE_FreightIncluded);
			setInvoiceRule(INVOICERULE_Immediate);
			setPaymentRule(PAYMENTRULE_OnCredit);
			setPriorityRule(PRIORITYRULE_Medium);
			setDeliveryViaRule(DELIVERYVIARULE_Pickup);
			// to.setIsTransferred (false); // MODIFICACION: SE CAMBIO EN
			// COMPIERE DE YES/NO A LISTA
			setIsTransferred(ISTRANSFERRED_PendingTransfer);
			setIsDiscountPrinted(false);
			setIsSelected(false);
			setIsTaxIncluded(false);
			setIsSOTrx(true);
			setIsDropShip(false);
			setSendEMail(false);
			setIsApproved(false);
			setIsPrinted(false);
			setIsCreditApproved(false);
			setIsDelivered(false);
			setIsInvoiced(false);
			setIsSelfService(false);
			super.setProcessed(false);
			setProcessing(false);
			setPosted(false);

			setDateAcct(new Timestamp(System.currentTimeMillis()));
			setDatePromised(new Timestamp(System.currentTimeMillis()));
			setDateOrdered(new Timestamp(System.currentTimeMillis()));

			setFreightAmt(Env.ZERO);
			setChargeAmt(Env.ZERO);
			setTotalLines(Env.ZERO);
			setGrandTotal(Env.ZERO);
		}
	} // MOrder

	/**************************************************************************
	 * Project Constructor
	 * 
	 * @param project
	 *            Project to create Order from
	 * @param IsSOTrx
	 *            sales order
	 * @param DocSubTypeSO
	 *            if SO DocType Target (default DocSubTypeSO_OnCredit)
	 */
	public MOrder(MProject project, boolean IsSOTrx, String DocSubTypeSO) {
		this(project.getCtx(), 0, project.get_TrxName());
		setAD_Client_ID(project.getAD_Client_ID());
		setAD_Org_ID(project.getAD_Org_ID());
		setC_Campaign_ID(project.getC_Campaign_ID());
		setSalesRep_ID(project.getSalesRep_ID());
		setC_Project_ID(project.getC_Project_ID());
		setDescription(project.getName());
		Timestamp ts = project.getDateContract();

		if (ts != null) {
			setDateOrdered(ts);
		}

		ts = project.getDateFinish();
		if (ts != null) {
			setDatePromised(ts);
		}

		setC_BPartner_ID(project.getC_BPartner_ID());
		setC_BPartner_Location_ID(project.getC_BPartner_Location_ID());
		setAD_User_ID(project.getAD_User_ID());
		setM_Warehouse_ID(project.getM_Warehouse_ID());
		setM_PriceList_ID(project.getM_PriceList_ID());
		setC_PaymentTerm_ID(project.getC_PaymentTerm_ID());
		setIsSOTrx(IsSOTrx);
		if (IsSOTrx) {
			if (DocSubTypeSO == null || DocSubTypeSO.length() == 0) {
				setC_DocTypeTarget_ID(DocSubTypeSO_OnCredit);
			} else {
				setC_DocTypeTarget_ID(DocSubTypeSO);
			}
		} else {
			setC_DocTypeTarget_ID();
		}
	} // MOrder

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
	public MOrder(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MOrder

	/**
	 * Metodo para obtener las ordenes de compra activas a travez de la
	 * condicion generado por el filtro de busqueda avanzada
	 * 
	 * @param String
	 *            where_condition
	 * @return Vector<DynamicModel>
	 */
	public static Vector<DynamicModel> getAllOrders(String where_condition) {
		Vector<DynamicModel> data = null;

		/*
		 * String sql="select "+ "co.documentno, "+ "ac.name as ad_client, "+
		 * "ao.name as ad_org, "+ "dtt.name as doctypetarget, "+
		 * "cb.name as cbpartner, "+ "cbl.name as cbpartnerlocation, "+
		 * "mpl.name as mpricelist, "+ "co.docstatus, "+ "co.istransferred, "+
		 * "co.isapproved, "+ "co.posted, "+ "mw.name as m_warehouse, "+
		 * "co.totallines, "+ "co.dateordered, "+ "co.c_order_id "+
		 * "from c_order co "+
		 * "inner join AD_Client ac on co.ad_client_id = ac.ad_client_id "+
		 * "inner join AD_ORG ao on co.ad_org_id = ao.ad_org_id "+
		 * "inner join C_Doctype dtt on co.c_doctypetarget_id = dtt.c_doctype_id "
		 * + "inner join c_bpartner cb on co.c_bpartner_id = cb.c_bpartner_id "+
		 * "inner join c_bpartner_location cbl on co.c_bpartner_location_id = cbl.c_bpartner_location_id "
		 * +
		 * "inner join m_warehouse mw on co.m_warehouse_id = mw.m_warehouse_id "
		 * + "left join ad_user adu on co.ad_user_id = adu.ad_user_id "+
		 * "left join m_pricelist mpl on co.m_pricelist_id = mpl.m_pricelist_id "
		 * +where_condition+ "ORDER BY co.c_order_id DESC ";
		 */

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select distinct co.documentno, ac.name as ad_client, ");
		sql.append("ao.name as ad_org, dtt.name as doctypetarget, ");
		sql.append("cb.name as cbpartner, cbl.name as cbpartnerlocation, ");
		sql.append("mpl.name as mpricelist, co.docstatus, co.istransferred, ");
		sql.append("co.isapproved, co.posted, mw.name as m_warehouse, ");
		sql.append("co.totallines, co.dateordered, co.c_order_id ");
		sql.append("from c_order co ");
		sql
				.append("inner join AD_Client ac on co.ad_client_id = ac.ad_client_id ");
		sql.append("inner join AD_ORG ao on co.ad_org_id = ao.ad_org_id ");
		sql
				.append("inner join C_Doctype dtt on co.c_doctypetarget_id = dtt.c_doctype_id ");
		sql
				.append("inner join c_bpartner cb on co.c_bpartner_id = cb.c_bpartner_id ");
		sql
				.append("inner join c_bpartner_location cbl on co.c_bpartner_location_id = cbl.c_bpartner_location_id ");
		sql
				.append("inner join m_warehouse mw on co.m_warehouse_id = mw.m_warehouse_id ");
		sql.append("left join ad_user adu on co.ad_user_id = adu.ad_user_id ");
		sql
				.append("left join m_pricelist mpl on co.m_pricelist_id = mpl.m_pricelist_id ");
		sql.append(where_condition);
		sql.append(" ORDER BY co.c_order_id DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			data = new Vector<DynamicModel>();

			while (rs.next()) {

				DynamicModel dynamicModel = new DynamicModel();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					dynamicModel.setValue(rsmd.getColumnName(i), rs
							.getString(i), rsmd.getColumnClassName(i));
				}

				data.add(dynamicModel);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MOrder.getAllOrders - sql = " + sql, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log
						.log(
								Level.SEVERE,
								"MOrder.getAllOrders - while closing rs and pstmt objects",
								e);
			}
		}

		return data;
	}

	// TODO:Documentar Metodo
	public static Vector<DynamicModel> getLinesByOrder(int c_order_id) {
		Vector<DynamicModel> data_lines = null;

		String sql = "select * from c_orderline where c_order_id = ? and c_orderline.isactive='Y'";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {

			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, c_order_id);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			data_lines = new Vector<DynamicModel>();

			while (rs.next()) {

				DynamicModel dynamicModel = new DynamicModel();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					dynamicModel.setValue(rsmd.getColumnName(i), rs
							.getString(i), rsmd.getColumnClassName(i));
				}
				data_lines.add(dynamicModel);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MOrder.getLinesByOrder - sql = " + sql, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log
						.log(
								Level.SEVERE,
								"MOrder.getLinesByOrder - while closing rs and pstmt objects",
								e);
			}
		}

		return data_lines;
	}

	/** Order Lines */
	private MOrderLine[] m_lines = null;
	/** Tax Lines */
	private MOrderTax[] m_taxes = null;
	/** Force Creation of order */
	private boolean m_forceCreation = false;

	/**
	 * Overwrite Client/Org if required
	 * 
	 * @param AD_Client_ID
	 *            client
	 * @param AD_Org_ID
	 *            org
	 */
	public void setClientOrg(int AD_Client_ID, int AD_Org_ID) {
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	} // setClientOrg

	/**
	 * Add to Description
	 * 
	 * @param description
	 *            text
	 */
	public void addDescription(String description) {
		String desc = getDescription();
		if (desc == null) {
			setDescription(description);
		} else {
			setDescription(desc + " | " + description);
		}
	} // addDescription

	/**
	 * Set Business Partner (Ship+Bill)
	 * 
	 * @param C_BPartner_ID
	 *            bpartner
	 */
	public void setC_BPartner_ID(int C_BPartner_ID) {
		super.setC_BPartner_ID(C_BPartner_ID);
		super.setBill_BPartner_ID(C_BPartner_ID);
	} // setC_BPartner_ID

	/**
	 * Set Business Partner Location (Ship+Bill)
	 * 
	 * @param C_BPartner_Location_ID
	 *            bp location
	 */
	public void setC_BPartner_Location_ID(int C_BPartner_Location_ID) {
		super.setC_BPartner_Location_ID(C_BPartner_Location_ID);
		super.setBill_Location_ID(C_BPartner_Location_ID);
	} // setC_BPartner_Location_ID

	/**
	 * Set Business Partner Contact (Ship+Bill)
	 * 
	 * @param AD_User_ID
	 *            user
	 */
	public void setAD_User_ID(int AD_User_ID) {
		super.setAD_User_ID(AD_User_ID);
		super.setBill_User_ID(AD_User_ID);
	} // setAD_User_ID

	/**
	 * Set Ship Business Partner
	 * 
	 * @param C_BPartner_ID
	 *            bpartner
	 */
	public void setShip_BPartner_ID(int C_BPartner_ID) {
		super.setC_BPartner_ID(C_BPartner_ID);
	} // setShip_BPartner_ID

	/**
	 * Set Ship Business Partner Location
	 * 
	 * @param C_BPartner_Location_ID
	 *            bp location
	 */
	public void setShip_Location_ID(int C_BPartner_Location_ID) {
		super.setC_BPartner_Location_ID(C_BPartner_Location_ID);
	} // setShip_Location_ID

	/**
	 * Set Ship Business Partner Contact
	 * 
	 * @param AD_User_ID
	 *            user
	 */
	public void setShip_User_ID(int AD_User_ID) {
		super.setAD_User_ID(AD_User_ID);
	} // setShip_User_ID

	/**
	 * Set Warehouse
	 * 
	 * @param M_Warehouse_ID
	 *            warehouse
	 */
	public void setM_Warehouse_ID(int M_Warehouse_ID) {
		super.setM_Warehouse_ID(M_Warehouse_ID);
	} // setM_Warehouse_ID

	/**
	 * Set Drop Ship
	 * 
	 * @param IsDropShip
	 *            drop ship
	 */
	public void setIsDropShip(boolean IsDropShip) {
		super.setIsDropShip(IsDropShip);
	} // setIsDropShip

	/*************************************************************************/

	/** Sales Order Sub Type - SO */
	public static final String DocSubTypeSO_Standard = "SO";
	/** Sales Order Sub Type - OB */
	public static final String DocSubTypeSO_Quotation = "OB";
	/** Sales Order Sub Type - ON */
	public static final String DocSubTypeSO_Proposal = "ON";
	/** Sales Order Sub Type - PR */
	public static final String DocSubTypeSO_Prepay = "PR";
	/** Sales Order Sub Type - WR */
	public static final String DocSubTypeSO_POS = "WR";
	/** Sales Order Sub Type - WP */
	public static final String DocSubTypeSO_Warehouse = "WP";
	/** Sales Order Sub Type - WI */
	public static final String DocSubTypeSO_OnCredit = "WI";
	/** Sales Order Sub Type - RM */
	public static final String DocSubTypeSO_RMA = "RM";

	/**
	 * Set Target Sales Document Type
	 * 
	 * @param DocSubTypeSO_x
	 *            SO sub type - see DocSubTypeSO_*
	 */
	public void setC_DocTypeTarget_ID(String DocSubTypeSO_x) {
		/*
		 * String sql = "SELECT C_DocType_ID FROM C_DocType " +
		 * "WHERE AD_Client_ID=? AND AD_Org_ID IN (0," + getAD_Org_ID() +
		 * ") AND DocSubTypeSO=? " + "ORDER BY AD_Org_ID DESC, IsDefault DESC";
		 */
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_DocType_ID FROM C_DocType ");
		sql.append("WHERE AD_Client_ID=? AND AD_Org_ID IN (0, ");
		sql.append(getAD_Org_ID());
		sql.append(") AND DocSubTypeSO=? ");
		sql.append("ORDER BY AD_Org_ID DESC, IsDefault DESC ");

		int C_DocType_ID = DB.getSQLValue(null, sql.toString(),
				getAD_Client_ID(), DocSubTypeSO_x);
		if (C_DocType_ID <= 0) {
			log.severe("Not found for AD_Client_ID=" + getAD_Client_ID()
					+ ", SubType=" + DocSubTypeSO_x);
		} else {
			log.fine("(SO) - " + DocSubTypeSO_x);
			setC_DocTypeTarget_ID(C_DocType_ID);
			setIsSOTrx(true);
		}
	} // setC_DocTypeTarget_ID

	/**
	 * Set Target Document Type. Standard Order or PO
	 */
	public void setC_DocTypeTarget_ID() {
		if (isSOTrx()) { // SO = Std Order
			setC_DocTypeTarget_ID(DocSubTypeSO_Standard);
			return;
		}
		// PO
		/*
		 * String sql = "SELECT C_DocType_ID FROM C_DocType " +
		 * "WHERE AD_Client_ID=? AND AD_Org_ID IN (0," + getAD_Org_ID() +
		 * ") AND DocBaseType='POO' " +
		 * "ORDER BY AD_Org_ID DESC, IsDefault DESC";
		 */

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_DocType_ID FROM C_DocType ");
		sql.append("WHERE AD_Client_ID=? AND AD_Org_ID IN (0, ");
		sql.append(getAD_Org_ID());
		sql.append(") AND DocBaseType='POO' ");
		sql.append("ORDER BY AD_Org_ID DESC, IsDefault DESC ");

		int C_DocType_ID = DB.getSQLValue(null, sql.toString(),
				getAD_Client_ID());
		if (C_DocType_ID <= 0) {
			log.severe("No POO found for AD_Client_ID=" + getAD_Client_ID());
		} else {
			log.fine("(PO) - " + C_DocType_ID);
			setC_DocTypeTarget_ID(C_DocType_ID);
		}
	} // setC_DocTypeTarget_ID

	/**
	 * Set Business Partner Defaults & Details. SOTrx should be set.
	 * 
	 * @param bp
	 *            business partner
	 */
	public void setBPartner(MBPartner bp) {
		if (bp == null) {
			return;
		}

		setC_BPartner_ID(bp.getC_BPartner_ID());
		// Defaults Payment Term
		int ii = 0;

		if (isSOTrx()) {
			ii = bp.getC_PaymentTerm_ID();
		} else {
			ii = bp.getPO_PaymentTerm_ID();
		}

		if (ii != 0) {
			setC_PaymentTerm_ID(ii);
		}
		// Default Price List

		if (isSOTrx()) {
			ii = bp.getM_PriceList_ID();
		} else {
			ii = bp.getPO_PriceList_ID();
		}

		if (ii != 0) {
			setM_PriceList_ID(ii);
		}

		// Default Delivery/Via Rule
		String ss = bp.getDeliveryRule();

		if (ss != null) {
			setDeliveryRule(ss);
		}

		ss = bp.getDeliveryViaRule();

		if (ss != null) {
			setDeliveryViaRule(ss);
		}

		// Default Invoice/Payment Rule
		ss = bp.getInvoiceRule();

		if (ss != null) {
			setInvoiceRule(ss);
		}

		ss = bp.getPaymentRule();

		if (ss != null) {
			setPaymentRule(ss);
		}

		// Sales Rep
		ii = bp.getSalesRep_ID();

		if (ii != 0) {
			setSalesRep_ID(ii);
		}

		// Set Locations
		MBPartnerLocation[] locs = bp.getLocations(false);
		if (locs != null) {
			for (int i = 0; i < locs.length; i++) {
				if (locs[i].isShipTo()) {
					super.setC_BPartner_Location_ID(locs[i]
							.getC_BPartner_Location_ID());
				}
				if (locs[i].isBillTo()) {
					setBill_Location_ID(locs[i].getC_BPartner_Location_ID());
				}
			}
			// set to first
			if (getC_BPartner_Location_ID() == 0 && locs.length > 0) {
				super.setC_BPartner_Location_ID(locs[0]
						.getC_BPartner_Location_ID());
			}
			if (getBill_Location_ID() == 0 && locs.length > 0) {
				setBill_Location_ID(locs[0].getC_BPartner_Location_ID());
			}
		}
		if (getC_BPartner_Location_ID() == 0) {
			log.log(Level.SEVERE,
					"MOrder.setBPartner - Has no Ship To Address: " + bp);
		}
		if (getBill_Location_ID() == 0) {
			log.log(Level.SEVERE,
					"MOrder.setBPartner - Has no Bill To Address: " + bp);
		}

		// Set Contact
		MUser[] contacts = bp.getContacts(false);
		if (contacts != null && contacts.length == 1) {
			setAD_User_ID(contacts[0].getAD_User_ID());
		}
	} // setBPartner

	/**
	 * Copy Lines From other Order
	 * 
	 * @param otherOrder
	 *            order
	 * @param counter
	 *            set counter info
	 * @param copyASI
	 *            copy line attributes Attribute Set Instance, Resaouce
	 *            Assignment
	 * @return number of lines copied
	 */
	public int copyLinesFrom(MOrder otherOrder, boolean counter, boolean copyASI) {
		if (isProcessed() || isPosted() || otherOrder == null) {
			return 0;
		}

		MOrderLine[] fromLines = otherOrder.getLines(false, null);
		int count = 0;
		for (int i = 0; i < fromLines.length; i++) {
			MOrderLine line = new MOrderLine(this);
			PO
					.copyValues(fromLines[i], line, getAD_Client_ID(),
							getAD_Org_ID());
			line.setC_Order_ID(getC_Order_ID());
			line.setOrder(this);
			line.set_ValueNoCheck("C_OrderLine_ID", I_ZERO); // new
			// References
			if (!copyASI) {
				line.setM_AttributeSetInstance_ID(0);
				line.setS_ResourceAssignment_ID(0);
			}
			if (counter) {
				line.setRef_OrderLine_ID(fromLines[i].getC_OrderLine_ID());
			} else {
				line.setRef_OrderLine_ID(0);
			}
			//
			line.setQtyDelivered(Env.ZERO);
			line.setQtyInvoiced(Env.ZERO);
			line.setQtyReserved(Env.ZERO);
			line.setDateDelivered(null);
			line.setDateInvoiced(null);
			// Tax
			if (getC_BPartner_ID() != otherOrder.getC_BPartner_ID()) {
				line.setTax(); // recalculate
			}

			line.setProcessed(false);
			if (line.save(get_TrxName())) {
				count++;
			}
			// Cross Link
			if (counter) {
				fromLines[i].setRef_OrderLine_ID(line.getC_OrderLine_ID());
				fromLines[i].save(get_TrxName());
			}
		}
		if (fromLines.length != count) {
			log.log(Level.SEVERE, "Line difference - From=" + fromLines.length
					+ " <> Saved=" + count);
		}
		return count;
	} // copyLinesFrom

	/**************************************************************************
	 * String Representation
	 * 
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("MOrder[").append(get_ID()).append(
				"-").append(getDocumentNo()).append(",IsSOTrx=").append(
				isSOTrx()).append(",C_DocType_ID=").append(getC_DocType_ID())
				.append("]");
		return sb.toString();
	} // toString

	/**
	 * Get Document Info
	 * 
	 * @return document info (untranslated)
	 */
	public String getDocumentInfo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
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
			log.log(Level.SEVERE, "MOrder.createPDF - Could not create PDF - "
					+ e);
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
		ReportEngine re = ReportEngine.get(getCtx(), ReportEngine.ORDER,
				getC_Invoice_ID());
		if (re == null) {
			return null;
		}
		return re.getPDF(file);
	} // createPDF

	/**
	 * Set Price List (and Currency, TaxIncluded) when valid
	 * 
	 * @param M_PriceList_ID
	 *            price list
	 */
	public void setM_PriceList_ID(int M_PriceList_ID) {
		MPriceList pl = MPriceList.get(getCtx(), M_PriceList_ID, null);
		if (pl.get_ID() == M_PriceList_ID && M_PriceList_ID>0 && M_PriceList_ID>0) {
			super.setM_PriceList_ID(M_PriceList_ID);
			setC_Currency_ID(pl.getC_Currency_ID());
			setIsTaxIncluded(pl.isTaxIncluded());
		} else {
			MPriceList plLogin = MPriceList.get(getCtx(), Env.getM_PriceList_ID(getCtx()), null);
			super.setM_PriceList_ID(plLogin.getM_PriceList_ID());
			setC_Currency_ID(plLogin.getC_Currency_ID());
			setIsTaxIncluded(plLogin.isTaxIncluded());
		}
	} // setM_PriceList_ID

	/**************************************************************************
	 * Get Lines of Order
	 * 
	 * @param whereClause
	 *            where clause or null (starting with AND)
	 * @param orderClause
	 *            order clause
	 * @return lines
	 */
	public MOrderLine[] getLines(String whereClause, String orderClause) {
		List<MOrderLine> list = new ArrayList<MOrderLine>();
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM C_OrderLine WHERE C_Order_ID=? ");
		if (whereClause != null) {
			sql.append(whereClause);
		}
		if (orderClause != null) {
			sql.append(" ").append(orderClause);
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MOrderLine ol = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Order_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ol = new MOrderLine(getCtx(), rs, get_TrxName());
				ol.setHeaderInfo(this);
				list.add(ol);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MOrder.getLines - sql = " + sql, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"MOrder.getLines - while closing rs and pstmt objects",
						e);
			}
		}
		//
		MOrderLine[] lines = new MOrderLine[list.size()];
		list.toArray(lines);
		return lines;
	} // getLines

	/**
	 * Get Lines of Order
	 * 
	 * @param requery
	 *            requery
	 * @param orderBy
	 *            optional order by column
	 * @return lines
	 */
	public MOrderLine[] getLines(boolean requery, String orderBy) {
		if (m_lines != null && !requery) {
			return m_lines;
		}
		//
		String orderClause = "ORDER BY ";
		if (orderBy != null && orderBy.length() > 0) {
			orderClause += orderBy;
		} else {
			orderClause += "Line";
		}

		m_lines = getLines(null, orderClause);
		return m_lines;
	} // getLines

	/**
	 * Get Lines of Order. (useb by web store)
	 * 
	 * @return lines
	 */
	public MOrderLine[] getLines() {
		return getLines(false, null);
	} // getLines

	/**
	 * Renumber Lines
	 * 
	 * @param step
	 *            start and step
	 */
	public void renumberLines(int step) {
		int number = step;
		MOrderLine[] lines = getLines(true, null); // Line is default
		for (int i = 0; i < lines.length; i++) {
			MOrderLine line = lines[i];
			line.setLine(number);
			line.save(get_TrxName());
			number += step;
		}
		m_lines = null;
	} // renumberLines

	/**
	 * Does the Order Line belong to this Order
	 * 
	 * @param C_OrderLine_ID
	 *            line
	 * @return true if part of the order
	 */
	public boolean isOrderLine(int C_OrderLine_ID) {
		if (m_lines == null) {
			getLines();
		}
		for (int i = 0; i < m_lines.length; i++) {
			if (m_lines[i].getC_OrderLine_ID() == C_OrderLine_ID) {
				return true;
			}
		}

		return false;
	} // isOrderLine

	/**
	 * Get Taxes of Order
	 * 
	 * @param requery
	 *            requery
	 * @return array of taxes
	 */
	public MOrderTax[] getTaxes(boolean requery) {
		if (m_taxes != null && !requery) {
			return m_taxes;
		}
		//
		List<MOrderTax> list = new ArrayList<MOrderTax>();
		String sql = "SELECT * FROM C_OrderTax WHERE C_Order_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_Order_ID());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MOrderTax(getCtx(), rs, get_TrxName()));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "MOrder.getTaxes - sql = " + sql, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"MOrder.getTaxes - while closing rs and pstmt objects",
						e);
			}
		}
		//
		m_taxes = new MOrderTax[list.size()];
		list.toArray(m_taxes);
		return m_taxes;
	} // getTaxes

	/**
	 * Get Invoices of Order
	 * 
	 * @return invoices
	 */
	public MInvoice[] getInvoices() {
		List<MInvoice> list = new ArrayList<MInvoice>();
		String sql = "SELECT * FROM C_Invoice WHERE C_Order_ID=? ORDER BY DATEINVOICED DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_Order_ID());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MInvoice(getCtx(), rs, get_TrxName()));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MOrder.getInvoices - sql = " + sql, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log
						.log(
								Level.SEVERE,
								"MOrder.getInvoices - while closing rs and pstmt objects",
								e);
			}
		}
		//
		MInvoice[] retValue = new MInvoice[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getInvoices

	/**
	 * Get latest Invoice of Order
	 * 
	 * @return invoice id or 0
	 */
	public int getC_Invoice_ID() {
		int C_Invoice_ID = 0;
		// ArrayList list = new ArrayList();
		/*
		 * String sql = "SELECT C_Invoice_ID FROM C_Invoice " +
		 * "WHERE C_Order_ID=? AND DocStatus IN ('CO','CL') " +
		 * "ORDER BY Created DESC";
		 */

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_Invoice_ID FROM C_Invoice ");
		sql.append("WHERE C_Order_ID=? AND DocStatus IN ('CO','CL') ");
		sql.append("ORDER BY DATEINVOICED DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Order_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				C_Invoice_ID = rs.getInt(1);
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "MOrder.getC_Invoice_ID - sql = " + sql, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log
						.log(
								Level.SEVERE,
								"MOrder.getC_Invoice_ID - while closing rs and pstmt objects",
								e);
			}
		}
		return C_Invoice_ID;
	} // getC_Invoice_ID

	/**
	 * Get Shipments of Order
	 * 
	 * @return shipments
	 */
	public MInOut[] getShipments() {
		List<MInOut> list = new ArrayList<MInOut>();
		String sql = "SELECT * FROM M_InOut WHERE C_Order_ID=? ORDER BY MOVEMENTDATE DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_Order_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MInOut(getCtx(), rs, get_TrxName()));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "MOrder.getShipments - sql = " + sql, e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log
						.log(
								Level.SEVERE,
								"MOrder.getShipments - while closing rs and pstmt objects",
								e);
			}
		}
		//
		MInOut[] retValue = new MInOut[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getShipments

	/**
	 * Get ISO Code of Currency
	 * 
	 * @return Currency ISO
	 */
	public String getCurrencyISO() {
		return MCurrency.getISO_Code(getCtx(), getC_Currency_ID());
	} // getCurrencyISO

	/**
	 * Get Currency Precision
	 * 
	 * @return precision
	 */
	public int getPrecision() {
		return MCurrency.getStdPrecision(getCtx(), getC_Currency_ID());
	} // getPrecision

	/**
	 * Get Document Status
	 * 
	 * @return Document Status Clear Text
	 */
	public String getDocStatusName() {
		return MRefList.getListName(getCtx(), 131, getDocStatus());
	} // getDocStatusName

	/**
	 * Set DocAction
	 * 
	 * @param DocAction
	 *            doc action
	 */
	public void setDocAction(String DocAction) {
		setDocAction(DocAction, false);
	} // setDocAction

	/**
	 * Set DocAction
	 * 
	 * @param DocAction
	 *            doc oction
	 * @param forceCreation
	 *            force creation
	 */
	public void setDocAction(String DocAction, boolean forceCreation) {
		super.setDocAction(DocAction);
		m_forceCreation = forceCreation;
	} // setDocAction

	/**
	 * Set Processed. Propergate to Lines/Taxes
	 * 
	 * @param processed
	 *            processed
	 */
	public void setProcessed(boolean processed) {
		super.setProcessed(processed);
		if (get_ID() == 0) {
			return;
		}
		String set = "SET Processed='" + (processed ? "Y" : "N")
				+ "' WHERE C_Order_ID=" + getC_Order_ID();
		int noLine = DB.executeUpdate("UPDATE C_OrderLine " + set,
				get_TrxName());
		int noTax = DB.executeUpdate("UPDATE C_OrderTax " + set, get_TrxName());
		m_lines = null;
		m_taxes = null;
		log.fine("setProcessed - " + processed + " - Lines=" + noLine
				+ ", Tax=" + noTax);
	} // setProcessed

	/**************************************************************************
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return save
	 */
	protected boolean beforeSave(boolean newRecord) {
		// Client/Org Check
		if (getAD_Org_ID() == 0) {
			int context_AD_Org_ID = Env.getAD_Org_ID(getCtx());
			if (context_AD_Org_ID != 0) {
				setAD_Org_ID(context_AD_Org_ID);
				log.warning("Changed Org to Context=" + context_AD_Org_ID);
			}
		}
		if (getAD_Client_ID() == 0) {
			m_processMsg = "AD_Client_ID = 0";
			return false;
		}

		// New Record Doc Type - make sure DocType set to 0
		if (newRecord && getC_DocType_ID() == 0) {
			setC_DocType_ID(0);
		}

		// Default Warehouse
		if (getM_Warehouse_ID() == 0) {
			int ii = Env.getContextAsInt(getCtx(), "#M_Warehouse_ID");
			if (ii != 0) {
				setM_Warehouse_ID(ii);
			} else {
				log.saveError("FillMandatory", Msg.getElement(getCtx(),
						"M_Warehouse_ID"));
				return false;
			}
		}
		// Warehouse Org
		if (newRecord || is_ValueChanged("AD_Org_ID")
				|| is_ValueChanged("M_Warehouse_ID")) {
			MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			if (wh.getAD_Org_ID() != getAD_Org_ID()) {
				log.saveWarning("WarehouseOrgConflict", "");
			}
		}
		// Reservations in Warehouse
		if (!newRecord && is_ValueChanged("M_Warehouse_ID")) {
			MOrderLine[] lines = getLines(false, null);
			for (int i = 0; i < lines.length; i++) {
				if (!lines[i].canChangeWarehouse()) {
					return false;
				}
			}
		}

		// No Partner Info - set Template
		// El socio es obligatorio solo al completar el documento
		/*
		 * if (getC_BPartner_ID() == 0) {
		 * setBPartner(MBPartner.getTemplate(getCtx(), getAD_Client_ID())); }
		 * */ 
		if(getC_BPartner_ID()>0){
			if (getC_BPartner_Location_ID() == 0) { 
				setBPartner(new
						MBPartner(getCtx(), getC_BPartner_ID(), null)); 
			} // No Bill - get from Ship 
			if (getBill_BPartner_ID() == 0) {
				setBill_BPartner_ID(getC_BPartner_ID());
				setBill_Location_ID(getC_BPartner_Location_ID()); 
			} 
			if
			(getBill_Location_ID() == 0) {
				setBill_Location_ID(getC_BPartner_Location_ID()); 
			}
		}
		
		// Default Currency
		if (getC_Currency_ID() == 0) {
			
			// Default Price List 
			int _PriceList_ID = getM_PriceList_ID();
			if (_PriceList_ID == 0) { 
				StringBuilder sqlPriceList = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sqlPriceList.append("SELECT M_PriceList_ID FROM M_PriceList ");
				sqlPriceList.append("WHERE AD_Client_ID=? AND IsSOPriceList=? ");
				sqlPriceList.append("ORDER BY IsDefault DESC ");

				int ii = DB.getSQLValue(null, sqlPriceList.toString(),
						getAD_Client_ID(), isSOTrx() ? "Y" : "N"); 
				if (ii != 0) {
					_PriceList_ID = ii; 
				} 
			}
			
			String sql = "SELECT C_Currency_ID FROM M_PriceList WHERE M_PriceList_ID=?";
			int ii = DB.getSQLValue(null, sql, _PriceList_ID);
			if (ii != 0) {
				setC_Currency_ID(ii);
			} else {
				setC_Currency_ID(Env
						.getContextAsInt(getCtx(), "#C_Currency_ID"));
			}
		}

		// Default Sales Rep
		if (getSalesRep_ID() == 0) {
			int ii = Env.getContextAsInt(getCtx(), "#SalesRep_ID");
			if (ii != 0) {
				setSalesRep_ID(ii);
			}
		}

		// Default Document Type
		if (getC_DocTypeTarget_ID() == 0) {
			setC_DocTypeTarget_ID(DocSubTypeSO_Standard);
		}

		// Default Payment Term
		if (getC_PaymentTerm_ID() == 0) {
			int ii = Env.getContextAsInt(getCtx(), "#C_PaymentTerm_ID");
			if (ii != 0) {
				setC_PaymentTerm_ID(ii);
			} else {
				String sql = "SELECT C_PaymentTerm_ID FROM C_PaymentTerm WHERE AD_Client_ID=? AND IsDefault='Y'";
				ii = DB.getSQLValue(null, sql, getAD_Client_ID());
				if (ii != 0) {
					setC_PaymentTerm_ID(ii);
				}
			}
		}

		// expert : gisela lee : asignar por default la org trx logueada (est
		// serv)
		if (getAD_OrgTrx_ID() == 0) {
			setAD_OrgTrx_ID(Env.getContextAsInt(getCtx(), "#AD_OrgTrx_ID"));
		}
		// expert : gisela lee : asignar por default la org trx logueada (est
		// serv)

		String sql = "SELECT * FROM C_ORDER WHERE DOCUMENTNO = ?";
		int res = DB.getSQLValue(null, sql, getDocumentNo());
		if (newRecord && res > 0) {
			return false;
		}

		return true;
	} // beforeSave

	/**
	 * After Save
	 * 
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return true if can be saved
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success || newRecord) {
			return success;
		}

		// Propagate Description changes
		if (is_ValueChanged("Description") || is_ValueChanged("POReference")) {
			/*
			 * String sql = "UPDATE C_Invoice i" +
			 * " SET (Description,POReference)=" +
			 * "(SELECT Description,POReference " +
			 * "FROM C_Order o WHERE i.C_Order_ID=o.C_Order_ID) " +
			 * "WHERE DocStatus NOT IN ('RE','CL') AND C_Order_ID=" +
			 * getC_Order_ID();
			 */

			StringBuilder sql = new StringBuilder(
					Constantes.INIT_CAPACITY_ARRAY);
			sql.append("UPDATE C_Invoice i ");
			sql.append(" SET (Description,POReference)=");
			sql.append("(SELECT Description,POReference ");
			sql.append("FROM C_Order o WHERE i.C_Order_ID=o.C_Order_ID) ");
			sql.append("WHERE DocStatus NOT IN ('RE','CL') AND C_Order_ID=");
			sql.append(getC_Order_ID());
			int no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Description -> #" + no);
		}

		// Propagate Changes of Payment Info to existing (not reversed/closed)
		// invoices
		if (is_ValueChanged("PaymentRule")
				|| is_ValueChanged("C_PaymentTerm_ID")
				|| is_ValueChanged("DateAcct")
				|| is_ValueChanged("C_Payment_ID")
				|| is_ValueChanged("C_CashLine_ID")) {

			/*
			 * String sql = "UPDATE C_Invoice i " +
			 * "SET (PaymentRule,C_PaymentTerm_ID,DateAcct,C_Payment_ID,C_CashLine_ID)="
			 * +
			 * "(SELECT PaymentRule,C_PaymentTerm_ID,DateAcct,C_Payment_ID,C_CashLine_ID "
			 * + "FROM C_Order o WHERE i.C_Order_ID=o.C_Order_ID)" +
			 * "WHERE DocStatus NOT IN ('RE','CL') AND C_Order_ID=" +
			 * getC_Order_ID();
			 */
			// Don't touch Closed/Reversed entries

			StringBuilder sql = new StringBuilder(
					Constantes.INIT_CAPACITY_ARRAY);
			sql.append("UPDATE C_Invoice i ");
			sql
					.append("SET (PaymentRule,C_PaymentTerm_ID,DateAcct,C_Payment_ID,C_CashLine_ID)=");
			sql
					.append("(SELECT PaymentRule,C_PaymentTerm_ID,DateAcct,C_Payment_ID,C_CashLine_ID ");
			sql.append("FROM C_Order o WHERE i.C_Order_ID=o.C_Order_ID)");
			sql.append("WHERE DocStatus NOT IN ('RE','CL') AND C_Order_ID=");
			sql.append(getC_Order_ID());

			int no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Payment -> #" + no);
		}

		// Sync Lines
		afterSaveSync("AD_Org_ID");
		afterSaveSync("C_BPartner_ID");
		afterSaveSync("C_BPartner_Location_ID");
		afterSaveSync("DateOrdered");
		afterSaveSync("DatePromised");
		afterSaveSync("M_Warehouse_ID");
		afterSaveSync("M_Shipper_ID");
		afterSaveSync("C_Currency_ID");
		//
		return true;
	} // afterSave

	private void afterSaveSync(String columnName) {
		if (is_ValueChanged(columnName)) {
			/*
			 * String sql = "UPDATE C_OrderLine ol" + " SET " + columnName +
			 * " =" + "(SELECT " + columnName +
			 * " FROM C_Order o WHERE ol.C_Order_ID=o.C_Order_ID) " +
			 * "WHERE C_Order_ID=" + getC_Order_ID();
			 */

			StringBuilder sql = new StringBuilder(
					Constantes.INIT_CAPACITY_ARRAY);
			sql.append("UPDATE C_OrderLine ol set ");
			sql.append(columnName);
			sql.append(" = (SELECT ");
			sql.append(columnName);
			sql.append(" FROM C_Order o WHERE ol.C_Order_ID=o.C_Order_ID) ");
			sql.append("WHERE C_Order_ID = ");
			sql.append(getC_Order_ID());

			int no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine(columnName + " Lines -> #" + no);
		}
	} // afterSaveSync

	/**
	 * Before Delete
	 * 
	 * @return true of it can be deleted
	 */
	protected boolean beforeDelete() {
		if (isProcessed()) {
			return false;
		}

		getLines();
		
		for (int i = 0; i < m_lines.length; i++) {
			if (!m_lines[i].delete(false, get_TrxName())) {
				return false;
			}
		}
		
		DB.executeUpdate("delete from C_OrderTax where c_order_id = ?", new Object[] { getC_Order_ID() }, get_TrxName());

		return true;
	} // beforeDelete

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
		log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	} // invalidateIt

	/**************************************************************************
	 * Prepare Document
	 * 
	 * @return new status (In Progress or Invalid)
	 */
	public String prepareIt() {
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null) {
			return DocAction.STATUS_Invalid;
		}

		MDocType dt = MDocType.get(getCtx(), getC_DocTypeTarget_ID());

		// Std Period open?
		/*if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType(), getAD_Org_ID())) {
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}*/

		// Lines
		MOrderLine[] lines = getLines(true, "M_Product_ID");
		if (lines.length == 0) {
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}

		boolean priceZero = false;
		final StringBuilder errorPrice = new StringBuilder();
		// Price Lines
		for (MOrderLine oLine: lines) {
			if(oLine.getC_Order_ID()>0){
				priceZero = true;
			}
			
			if(Env.ZERO.compareTo(oLine.getPriceActual())>=0){
				errorPrice.append(oLine.getM_Product().getValue()).append("-")
				.append(oLine.getM_Product().getName()).append(Constantes.NEWLINE);
			}
			
			if(oLine.getC_Tax_ID()<=0){
				m_processMsg = "Error calculating tax";
				return DocAction.STATUS_Invalid;
			}
		}

		if(!priceZero && StringUtils.isNotEmpty(errorPrice.toString())){
			m_processMsg = "@ProductNotPrice@";
			log.log(Level.WARNING, "El Precio Actual (Unidad Mínima) del Producto " + errorPrice.toString());
			return DocAction.STATUS_Invalid;
		}
		
		
		if (getC_BPartner_ID() <= 0) {
			m_processMsg = "@BPartner@";
			return DocAction.STATUS_Invalid;
		}
		
		// Convert DocType to Target
		if (getC_DocType_ID() != getC_DocTypeTarget_ID()) {
			// Cannot change Std to anything else if different warehouses
			if (getC_DocType_ID() != 0) {
				MDocType dtOld = MDocType.get(getCtx(), getC_DocType_ID());
				if (MDocType.DOCSUBTYPESO_StandardOrder.equals(dtOld
						.getDocSubTypeSO()) // From SO
						&& !MDocType.DOCSUBTYPESO_StandardOrder.equals(dt
								.getDocSubTypeSO())) { // To !SO
					for (int i = 0; i < lines.length; i++) {
						if (lines[i].getM_Warehouse_ID() != getM_Warehouse_ID()) {
							log.warning("different Warehouse " + lines[i]);
							m_processMsg = "@CannotChangeDocType@";
							return DocAction.STATUS_Invalid;
						}
					}
				}
			}

			// New or in Progress/Invalid
			if (DOCSTATUS_Drafted.equals(getDocStatus())
					|| DOCSTATUS_InProgress.equals(getDocStatus())
					|| DOCSTATUS_Invalid.equals(getDocStatus())
					|| getC_DocType_ID() == 0) {
				setC_DocType_ID(getC_DocTypeTarget_ID());
			} else { // convert only if offer
				if (dt.isOffer()) {
					setC_DocType_ID(getC_DocTypeTarget_ID());
				} else {
					m_processMsg = "@CannotChangeDocType@";
					return DocAction.STATUS_Invalid;
				}
			}
		} // convert DocType

		// Mandatory Product Attribute Set Instance
		String mandatoryType = "='Y'"; // IN ('Y','S')

		/*
		 * String sql = "SELECT COUNT(*) " + "FROM C_OrderLine ol" +
		 * " INNER JOIN M_Product p ON (ol.M_Product_ID=p.M_Product_ID)" +
		 * " INNER JOIN M_AttributeSet pas ON (p.M_AttributeSet_ID=pas.M_AttributeSet_ID) "
		 * + "WHERE pas.MandatoryType" + mandatoryType +
		 * " AND ol.M_AttributeSetInstance_ID IS NULL" + " AND ol.C_Order_ID=?";
		 */

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM C_OrderLine ol");
		sql.append(" INNER JOIN        M_Product p  ON (ol.M_Product_ID=p.M_Product_ID)");
		sql.append(" INNER JOIN EXME_ProductoOrg po ON (p.M_Product_ID=po.M_Product_ID AND po.IsActive='Y' AND po.AD_Org_ID = "+Env.getAD_Org_ID(Env.getCtx())+ ") ");
		sql.append(" INNER JOIN  M_AttributeSet pas ON (po.M_AttributeSet_ID=pas.M_AttributeSet_ID) ");
		sql.append("WHERE pas.MandatoryType");
		sql.append(mandatoryType);
		sql.append(" AND ol.M_AttributeSetInstance_ID IS NULL");
		sql.append(" AND ol.C_Order_ID=?");

		int no = DB.getSQLValue(get_TrxName(), sql.toString(), getC_Order_ID());
		if (no != 0) {
			m_processMsg = "@LinesWithoutProductAttribute@ (" + no + ")";
			return DocAction.STATUS_Invalid;
		}

		// Lines
		explodeBOM();
		if (!reserveStock(dt, lines)) {
			m_processMsg = "Cannot reserve Stock";
			return DocAction.STATUS_Invalid;
		}
		if (!calculateTaxTotal()) {
			m_processMsg = "Error calculating tax";
			return DocAction.STATUS_Invalid;
		}

		// Credit Check
//		Card 1124: GC solicito quitar esta seccion ya que no es requerido validar el credito 
//		if (isSOTrx()) {
//			MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), null);
//			if (MBPartner.SOCREDITSTATUS_CreditStop.equals(bp
//					.getSOCreditStatus())) {
//				m_processMsg = "@BPartnerCreditStop@ - @TotalOpenBalance@="
//						+ bp.getTotalOpenBalance() + ", @SO_CreditLimit@="
//						+ bp.getSO_CreditLimit();
//				return DocAction.STATUS_Invalid;
//			}
//			if (MBPartner.SOCREDITSTATUS_CreditHold.equals(bp
//					.getSOCreditStatus())) {
//				m_processMsg = "@BPartnerCreditHold@ - @TotalOpenBalance@="
//						+ bp.getTotalOpenBalance() + ", @SO_CreditLimit@="
//						+ bp.getSO_CreditLimit();
//				return DocAction.STATUS_Invalid;
//			}
//			BigDecimal grandTotal = MConversionRate
//					.convertBase(getCtx(), getGrandTotal(), getC_Currency_ID(),
//							getDateOrdered(), getC_ConversionType_ID(),
//							getAD_Client_ID(), getAD_Org_ID());
//			if (MBPartner.SOCREDITSTATUS_CreditHold.equals(bp
//					.getSOCreditStatus(grandTotal))) {
//				m_processMsg = "@BPartnerOverOCreditHold@ - @TotalOpenBalance@="
//						+ bp.getTotalOpenBalance()
//						+ ", @GrandTotal@="
//						+ grandTotal
//						+ ", @SO_CreditLimit@="
//						+ bp.getSO_CreditLimit();
//				return DocAction.STATUS_Invalid;
//			}
//		}

		m_justPrepared = true;
		// if (!DOCACTION_Complete.equals(getDocAction())) don't set for just
		// prepare
		// setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/**
	 * Explode non stocked BOM.
	 */
	private void explodeBOM() {
		/*
		 * String where = "AND IsActive='Y' AND EXISTS " +
		 * "(SELECT * FROM M_Product p WHERE C_OrderLine.M_Product_ID=p.M_Product_ID"
		 * + " AND	p.IsBOM='Y' AND p.IsVerified='Y' AND p.IsStocked='N')";
		 */

		StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		where.append("AND IsActive='Y' AND EXISTS ");
		where
				.append("(SELECT * FROM M_Product p WHERE C_OrderLine.M_Product_ID=p.M_Product_ID");
		where
				.append(" AND p.IsBOM='Y' AND p.IsVerified='Y' AND p.IsStocked='N')");

		//
		/*
		 * String sql = "SELECT COUNT(*) FROM C_OrderLine " +
		 * "WHERE C_Order_ID=? " + where;
		 */

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT COUNT(*) FROM C_OrderLine ");
		sql.append("WHERE C_Order_ID=? ");
		sql.append(where.toString());

		int count = DB.getSQLValue(get_TrxName(), sql.toString(),
				getC_Order_ID());
		while (count != 0) {
			renumberLines(1000); // max 999 bom items

			// Order Lines with non-stocked BOMs
			MOrderLine[] lines = getLines(where.toString(), "ORDER BY Line");
			for (int i = 0; i < lines.length; i++) {
				MOrderLine line = lines[i];
				MProduct product = MProduct.get(getCtx(), line
						.getM_Product_ID());
				log.fine(product.getName());
				// New Lines
				int lineNo = line.getLine();
				MProductBOM[] boms = MProductBOM.getBOMLines(product);
				for (int j = 0; j < boms.length; j++) {
					MProductBOM bom = boms[j];
					MOrderLine newLine = new MOrderLine(this);
					newLine.setLine(++lineNo);
					newLine.setM_Product_ID(bom.getProduct().getM_Product_ID());
					newLine.setC_UOM_ID(bom.getProduct().getC_UOM_ID());
					newLine.setQty(line.getQtyOrdered().multiply(
							bom.getBOMQty()));
					if (bom.getDescription() != null) {
						newLine.setDescription(bom.getDescription());
					}
					//
					newLine.setPrice();
					newLine.save(get_TrxName());
				}
				// Convert into Comment Line
				line.setM_Product_ID(0);
				line.setM_AttributeSetInstance_ID(0);
				line.setPrice(Env.ZERO);
				line.setPriceLimit(Env.ZERO);
				line.setPriceList(Env.ZERO);
				line.setLineNetAmt(Env.ZERO);
				line.setFreightAmt(Env.ZERO);
				//
				String description = product.getName();
				if (product.getDescription() != null) {
					description += " " + product.getDescription();
				}
				if (line.getDescription() != null) {
					description += " " + line.getDescription();
				}
				line.setDescription(description);
				line.save(get_TrxName());
			} // for all lines with BOM

			m_lines = null; // force requery
			count = DB.getSQLValue(get_TrxName(), sql.toString(),
					getC_Invoice_ID());
			renumberLines(10);
		} // while count != 0
	} // explodeBOM

	/**
	 * Reserve Inventory. Counterpart: MInOut.completeIt()
	 * 
	 * @param dt
	 *            document type or null
	 * @param lines
	 *            order lines (ordered by M_Product_ID for deadlock prevention)
	 * @return true if (un) reserved
	 */
	private boolean reserveStock(MDocType dt, MOrderLine[] lines) {
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
						.equals(getDocAction())) || isDropShip()) {
			binding = false;
		}

		boolean isSOTrx = isSOTrx();
		log.fine("Binding=" + binding + " - IsSOTrx=" + isSOTrx);
		// Force same WH for all but SO/PO
		int header_M_Warehouse_ID = getM_Warehouse_ID();
		if (MDocType.DOCSUBTYPESO_StandardOrder.equals(dt.getDocSubTypeSO())
				|| MDocType.DOCBASETYPE_PurchaseOrder.equals(dt
						.getDocBaseType())) {
			header_M_Warehouse_ID = 0; // don't enforce
		}

		// Always check and (un) Reserve Inventory
		for (int i = 0; i < lines.length; i++) {
			MOrderLine line = lines[i];
			// Check/set WH/Org
			if (header_M_Warehouse_ID != 0) { // enforce WH
				if (header_M_Warehouse_ID != line.getM_Warehouse_ID()) {
					line.setM_Warehouse_ID(header_M_Warehouse_ID);
				}
				if (getAD_Org_ID() != line.getAD_Org_ID()) {
					line.setAD_Org_ID(getAD_Org_ID());
				}
			}
			// Binding
			BigDecimal target = binding ? line.getQtyOrdered() : Env.ZERO;
			BigDecimal difference = target.subtract(line.getQtyReserved())
					.subtract(line.getQtyDelivered());
			BigDecimal targetVol = binding ? line.getQtyOrdered_Vol() : Env.ZERO;
			BigDecimal differenceVol = targetVol.subtract(line.getQtyReserved_Vol()).subtract(line.getQtyDelivered_Vol());
			if (difference.signum() == 0 && differenceVol.signum() == 0) {
				continue;
			}

			log.fine("Line=" + line.getLine() + " - Target=" + target
					+ ",Difference=" + difference + " - Ordered="
					+ line.getQtyOrdered() + ",Reserved="
					+ line.getQtyReserved() + ",Delivered="
					+ line.getQtyDelivered());

			// Check Product - Stocked and Item
			MProduct product = line.getProduct();
			if (product != null) {
				if (product.isStocked()) {
					BigDecimal ordered = isSOTrx ? Env.ZERO : difference;
					BigDecimal reserved = isSOTrx ? difference : Env.ZERO;
					int M_Locator_ID = 0;
					// Get Locator to reserve
					if (line.getM_AttributeSetInstance_ID() != 0) { // Get
																	// existing
																	// Location
						M_Locator_ID = MStorage.getMLocatorID(line
								.getM_Warehouse_ID(), line.getM_Product_ID(),
								line.getM_AttributeSetInstance_ID(), ordered,
								get_TrxName());
					}
					// Get default Location
					if (M_Locator_ID == 0) {
						MWarehouse wh = MWarehouse.get(getCtx(), line
								.getM_Warehouse_ID());
						M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
					}
					// Update Storage
					if (isDropShip() && isConsigna()) {// Consigna no afecta existencias
						log.fine("No debe afectar existencias");
					} else {
						if (!MStorage.add(getCtx(), line.getM_Warehouse_ID(),
								M_Locator_ID, line.getM_Product_ID(), line
								.getM_AttributeSetInstance_ID(), line
								.getM_AttributeSetInstance_ID(), Env.ZERO,
								reserved, ordered, get_TrxName())) {
							return false;
						}
					}
				} // stockec
				// update line
				line.setQtyReserved(line.getQtyReserved().add(difference));
				line.setQtyReserved_Vol(line.getQtyReserved_Vol().add(differenceVol));
				if (!line.save(get_TrxName())) {
					return false;
				}
			} // product
		} // reverse inventory
		return true;
	} // reserveStock

	/**
	 * Calculate Tax and Total
	 * 
	 * @return true if tax total calculated
	 */
	private boolean calculateTaxTotal() {
		log.fine("");
		// Delete Taxes
		DB.executeUpdate("DELETE C_OrderTax WHERE C_Order_ID="
				+ getC_Order_ID(), get_TrxName());
		m_taxes = null;

		// Lines
		BigDecimal totalLines = Env.ZERO;
		List<Integer> taxList = new ArrayList<Integer>();
		MOrderLine[] lines = getLines();
		for (int i = 0; i < lines.length; i++) {
			MOrderLine line = lines[i];
			Integer taxID = new Integer(line.getC_Tax_ID());
			if (!taxList.contains(taxID)) {
				MOrderTax oTax = MOrderTax.get(line, getPrecision(), false,
						get_TrxName()); // current Tax
				oTax.setIsTaxIncluded(isTaxIncluded());

				if (!oTax.calculateTaxFromLines()) {
					return false;
				}

				if (!oTax.save(get_TrxName())) {
					return false;
				}

				taxList.add(taxID);
			}
			totalLines = totalLines.add(line.getLineNetAmt());
		}

		// Taxes
		BigDecimal grandTotal = totalLines;
		MOrderTax[] taxes = getTaxes(true);
		for (int i = 0; i < taxes.length; i++) {
			MOrderTax oTax = taxes[i];
			MTax tax = oTax.getTax();
			if (tax.isSummary()) {
				MTax[] cTaxes = tax.getChildTaxes(false);
				for (int j = 0; j < cTaxes.length; j++) {
					MTax cTax = cTaxes[j];
					BigDecimal taxAmt = cTax.calculateTax(oTax.getTaxBaseAmt(),
							isTaxIncluded(), getPrecision());
					//
					MOrderTax newOTax = new MOrderTax(getCtx(), 0,
							get_TrxName());
					newOTax.setClientOrg(this);
					newOTax.setC_Order_ID(getC_Order_ID());
					newOTax.setC_Tax_ID(cTax.getC_Tax_ID());
					newOTax.setPrecision(getPrecision());
					newOTax.setIsTaxIncluded(isTaxIncluded());
					newOTax.setTaxBaseAmt(oTax.getTaxBaseAmt());
					newOTax.setTaxAmt(taxAmt);
					if (!newOTax.save(get_TrxName())) {
						return false;
					}
					//
					if (!isTaxIncluded()) {
						grandTotal = grandTotal.add(taxAmt);
					}
				}
				if (!oTax.delete(true, get_TrxName())) {
					return false;
				}
			} else {
				if (!isTaxIncluded()) {
					grandTotal = grandTotal.add(oTax.getTaxAmt());
				}
			}
		}
		//
		setTotalLines(totalLines);
		setGrandTotal(grandTotal);
		return true;
	} // calculateTaxTotal

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

	/**************************************************************************
	 * Complete Document
	 * 
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	/**************************************************************************
	 * Complete Document
	 * 
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt() {

		
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		String DocSubTypeSO = dt.getDocSubTypeSO();

		if (getC_BPartner_ID() <= 0) {
			m_processMsg = "@BPartner@";
			return DocAction.STATUS_Invalid;
		}
		
		// Just prepare
		if (DOCACTION_Prepare.equals(getDocAction())) {
			setProcessed(false);
			return DocAction.STATUS_InProgress;
		}
		// Offers
		if (MDocType.DOCSUBTYPESO_Proposal.equals(DocSubTypeSO)
				|| MDocType.DOCSUBTYPESO_Quotation.equals(DocSubTypeSO)) {
			// Binding
			if (MDocType.DOCSUBTYPESO_Quotation.equals(DocSubTypeSO))
				reserveStock(dt, getLines(true, "M_Product_ID"));
			setProcessed(true);
			return DocAction.STATUS_Completed;
		}
		// Waiting Payment - until we have a payment
		if (!m_forceCreation
				&& MDocType.DOCSUBTYPESO_PrepayOrder.equals(DocSubTypeSO)
				&& getC_Payment_ID() == 0 && getC_CashLine_ID() == 0) {
			setProcessed(true);
			return DocAction.STATUS_WaitingPayment;
		}

		// Re-Check
		if (!m_justPrepared) {
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		// Implicit Approval
		if (!isApproved())
			approveIt();

		/*
		 * Control Presupuestal
		 */
		MAcctSchema as = MClient.get(getCtx(),
				Integer.parseInt(getCtx().getProperty("#AD_Client_ID")))
				.getAcctSchema();
		/*
		 * Si commitmentType incluye OC
		 */
		if (as != null) {
			if ((as.getCommitmentType()
					.equals(X_C_AcctSchema.COMMITMENTTYPE_CommitmentReservation))
					|| (as.getCommitmentType()
							.equals(X_C_AcctSchema.COMMITMENTTYPE_CommitmentOnly))) {
				SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
				SimpleDateFormat simpleMonth = new SimpleDateFormat("MM");
				MGL_Budget budget = MGL_Budget.getBudgetByYear(Integer
						.valueOf(simpleDateformat.format(getDateAcct())));
				if (budget == null) {
					// FIXME: Revisar cuando el Tipo de Compromiso != Ninguno y
					// no hay prespuesto activo para el a�o del documento
					/*
					 * Si no existe presupuesto y estaba establecido algun tipo
					 * de compromiso deberia de marcar error y lanzar una
					 * leyenda de que necesita darse de alta el presupuesto
					 * realista Sin embargo algunos clientes necesitan postear
					 * para llevar su contabilidad y en algunos casos el posteo
					 * solo se llevo a cabo dependiendo del tipo de compromiso
					 * Si no hay presupuesto en este caso se dejan las cosas
					 * como estaban.
					 */
				} else {

					MEXME_GL_BudgetPa budgetPa = MEXME_GL_BudgetPa.getBudgetPa(
							budget.getGL_Budget_ID(), MOrder.Table_Name, 0, 0);
					if (budgetPa == null) {

						// FIXME: Si el presupuesto no cuenta con la partida
						// ligada a los productos
						/*
						 * Si no existe presupuesto y estaba establecido algun
						 * tipo de compromiso deberia de marcar error y lanzar
						 * una leyenda de que necesita darse de alta el
						 * presupuesto realista Sin embargo algunos clientes
						 * necesitan postear para llevar su contabilidad y en
						 * algunos casos el posteo solo se llevo a cabo
						 * dependiendo del tipo de compromiso Si no hay
						 * presupuesto en este caso se dejan las cosas como
						 * estaban.
						 */
					} else {
						MEXME_GL_BudgetPaPe budgetPaPe = null;

						boolean flag = false;
						if (budgetPa
								.getLines(budgetPa.getEXME_GL_BudgetPa_ID()).length > 0)
							flag = true;

						if (Integer.valueOf(
								simpleDateformat.format(getDatePromised()))
								.equals(budget.getEjercicio())) {
							budgetPaPe = MEXME_GL_BudgetPaPe
									.getBudgetPaPe(budgetPa
											.getEXME_GL_BudgetPa_ID(), Integer
											.valueOf(simpleMonth
													.format(getDatePromised())));
						}

						boolean valido = false;

						if (budgetPaPe != null)
							valido = (budget.getPre_Comprometido()
									.doubleValue() + getGrandTotal()
									.doubleValue()) <= budget
									.getPre_Autorizado().doubleValue()
									&& (budgetPa.getPre_Comprometido()
											.doubleValue() + getGrandTotal()
											.doubleValue()) <= budgetPa
											.getPre_Autorizado().doubleValue()
									&& (budgetPaPe.getPre_Comprometido()
											.doubleValue()
											+ getGrandTotal().doubleValue() <= budgetPaPe
											.getPre_Autorizado().doubleValue());
						else
							valido = (budget.getPre_Comprometido()
									.doubleValue() + getGrandTotal()
									.doubleValue()) <= budget
									.getPre_Autorizado().doubleValue()
									&& (budgetPa.getPre_Comprometido()
											.doubleValue() + getGrandTotal()
											.doubleValue()) <= budgetPa
											.getPre_Autorizado().doubleValue();

						if (!valido) {

							String partida;
							try {
								partida = MEXMEPartida.getPartida(getCtx(),
										budgetPa.getEXME_GL_BudgetPa_ID());
							} catch (Exception e) {
								partida = "";
							}
							return "ErrPresupuesto-" + partida;

						} else {

							valido = MCampaign.checkCampaings(getGrandTotal(),
									getC_Campaign_ID(), MOrder.Table_Name,
									get_TrxName(), budgetPa
											.getEXME_Partida_ID());
							if (valido) {
								budget
										.setPre_Comprometido(new BigDecimal(
												budget.getPre_Comprometido()
														.doubleValue()
														+ getGrandTotal()
																.doubleValue()));
								budgetPa
										.setPre_Comprometido(new BigDecimal(
												budgetPa.getPre_Comprometido()
														.doubleValue()
														+ getGrandTotal()
																.doubleValue()));

								if (flag)
									budgetPaPe
											.setPre_Comprometido(new BigDecimal(
													budgetPaPe
															.getPre_Comprometido()
															.doubleValue()
															+ getGrandTotal()
																	.doubleValue()));
								// SAVE
								budget.save();
								budgetPa.save();

								if (flag)
									budgetPaPe.save();
							} else {
								String programa;
								try {
									MCampaign mc = new MCampaign(Env.getCtx(),
											getC_Campaign_ID(), null);
									programa = mc.getName();
								} catch (Exception e) {
									programa = "";
								}
								return "ErrPrograma-" + programa;
							}
						}

					}
				}
			}

		}

		// expert : gisela lee : actualizar m_product_po y m_product_costing
		// recuperar las lineas
		MOrderLine[] lines = getLines(true, null);
		if (!isSOTrx()) {
			for (int i = 0; i < lines.length; i++) {

				if (lines[i].getM_Product_ID() > 0) {  // Ealvarez Ticket 04972 
					
//					Card #1063 - Orden de Compra - Validación
//					incluir una validación para que no se permita COMPLETAR una Orden de Compra si 
//					los productos que incluye no son parte del Maestro de Productos. 		
					
					if (MEXMEProductoOrg.getProductoOrg(getCtx(), lines[i].getM_Product_ID(), lines[i].getAD_Client_ID(), lines[i].getAD_Org_ID(), null)==null){
						m_processMsg = Utilerias.getLabel("msj.ligarProducto");
						return DocAction.STATUS_Invalid;
					}

					// recuperamos la informacion de compras para actualizar
					// pricelist y pricepo
					MProductPO po = MProductPO.getOfProduct(getCtx(), lines[i]
							.getM_Product_ID(), getC_BPartner_ID());
					if (po != null) {
						po.setPriceLastPO(lines[i].getPriceActual());
						po.save();
					} else {
						po = new MProductPO(getCtx(), 0, null);
						MProduct prod = lines[i].getProduct();

						po.setM_Product_ID(lines[i].getM_Product_ID());
						po.setC_BPartner_ID(getC_BPartner_ID());
//						po.setPriceList(lines[i].getPriceList());
						// RQM #5921 precio de lista que este configurado, si no se tiene poner cero. 
				    	MProductPrice prdo = MProductPrice.getProductPrice(getC_BPartner_ID(), 0, lines[i].getM_Product_ID(), false);
				    	if(prdo == null) {
				    		po.setPriceList(BigDecimal.ZERO);
				    	} else {
				    		po.setPriceList(prdo.getPriceList());
				    	}
						po.setPricePO(lines[i].getPriceActual());
						po.setPriceLastPO(lines[i].getPriceActual());
						po.setIsCurrentVendor(true);
						po.setVendorProductNo(prod.getValue());
						po.setC_UOM_ID(lines[i].getC_UOM_ID());
						po.setC_Currency_ID(getC_Currency_ID());
						// RQM #5921 cantidad que se registro en la orden de compra
						po.setOrder_Min(lines[i].getQtyEntered());
						if (lines[i].getC_UOMVolume_ID() > 0 && lines[i].getC_UOMVolume_ID() != lines[i].getC_UOM_ID()) {
							po.setOrder_Pack(lines[i].getQtyEntered_Vol());
						} else {
							po.setOrder_Pack(BigDecimal.ZERO);
						}
						po.save();
					}//

				}
				// actualizamos la informacion de costos (m_product_costing)
				/*
				 * MProductCosting costing =
				 * MProductCosting.get(getCtx(),lines[i].getM_Product_ID(),
				 * Integer
				 * .parseInt(getCtx().getProperty("$C_AcctSchema_ID")),get_TrxName
				 * ()); if(costing != null){
				 * costing.setPriceLastPO(lines[i].getPriceActual()); }//Si no
				 * se encontro un MProductCosting, es necesario crear un nuevo
				 * registro: eruiz else{ costing = new
				 * MProductCosting(getCtx(),0, get_TableName());
				 * 
				 * //Asignamos llaves foraneas
				 * costing.setC_AcctSchema_ID(Integer
				 * .parseInt(getCtx().getProperty("$C_AcctSchema_ID")));
				 * costing.setM_Product_ID(lines[i].getM_Product_ID());
				 * 
				 * //Asignamos los precios a los datos correspondientes a la
				 * actualizaci�n costing.setPriceList(lines[i].getPriceList());
				 * costing.setPriceActual(lines[i].getPriceActual());
				 * 
				 * //Asignamos los costos que se llenan comunmente
				 * costing.setCurrentCostPrice(lines[i].getPriceEntered());
				 * costing.setFutureCostPrice(lines[i].getPriceEntered());
				 * costing.setCostStandard(lines[i].getPriceEntered());
				 * costing.setCostAverage(lines[i].getPriceEntered());
				 * 
				 * //Como los campos son obligatorios, la otras cantidades se
				 * llenaran con 0 costing.setTotalInvQty(new BigDecimal(0));
				 * costing.setTotalInvAmt(new BigDecimal(0));
				 * costing.setPriceLastPO(new BigDecimal(0));
				 * costing.setPriceLastInv(new BigDecimal(0));
				 * costing.setCostStandardCumAmt(new BigDecimal(0));
				 * costing.setCostStandardCumQty(new BigDecimal(0));
				 * costing.setCostStandardPOAmt(new BigDecimal(0));
				 * costing.setCostStandardPOQty(new BigDecimal(0));
				 * costing.setCostAverageCumAmt(new BigDecimal(0));
				 * costing.setCostAverageCumQty(new BigDecimal(0)); }
				 * 
				 * costing.save();
				 */
			}// for
		} // if
		// getLines(true,null);
		// expert : gisela lee : actualizar m_product_po y m_product_costing

		log.info(toString());
		StringBuffer info = new StringBuffer();

		boolean realTimePOS = false;

		// Create SO Shipment - Force Shipment
		MInOut shipment = null;
		if (MDocType.DOCSUBTYPESO_OnCreditOrder.equals(DocSubTypeSO) // (W)illCall(I)nvoice
				|| MDocType.DOCSUBTYPESO_WarehouseOrder.equals(DocSubTypeSO) // (W)illCall(P)ickup
				|| MDocType.DOCSUBTYPESO_POSOrder.equals(DocSubTypeSO) // (W)alkIn(R)eceipt
				|| MDocType.DOCSUBTYPESO_PrepayOrder.equals(DocSubTypeSO)) {
			if (!DELIVERYRULE_Force.equals(getDeliveryRule()))
				setDeliveryRule(DELIVERYRULE_Force);
			//
			shipment = createShipment(dt, realTimePOS ? null : getDateOrdered());
			if (shipment == null)
				return DocAction.STATUS_Invalid;
			info.append("@M_InOut_ID@: ").append(shipment.getDocumentNo());
			String msg = shipment.getProcessMsg();
			if (msg != null && msg.length() > 0)
				info.append(" (").append(msg).append(")");
		} // Shipment

		// Create SO Invoice - Always invoice complete Order
		if (MDocType.DOCSUBTYPESO_POSOrder.equals(DocSubTypeSO)
				|| MDocType.DOCSUBTYPESO_OnCreditOrder.equals(DocSubTypeSO)
				|| MDocType.DOCSUBTYPESO_PrepayOrder.equals(DocSubTypeSO)) {
			MInvoice invoice = createInvoice(dt, shipment, realTimePOS ? null
					: getDateOrdered());
			if (invoice == null)
				return DocAction.STATUS_Invalid;
			info.append(" - @C_Invoice_ID@: ").append(invoice.getDocumentNo());
			String msg = invoice.getProcessMsg();
			if (msg != null && msg.length() > 0)
				info.append(" (").append(msg).append(")");
		} // Invoice

		// Counter Documents
		MOrder counter = createCounterDoc();
		if (counter != null)
			info.append(" - @CounterDoc@: @Order@=").append(
					counter.getDocumentNo());
		// User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			if (info.length() > 0)
				info.append(" - ");
			info.append(valid);
			m_processMsg = Utilerias.getLabel("egresos.reg.docstatus").concat(" ").concat(Utilerias.getLabel(getDocAction()));
			return DocAction.STATUS_Invalid;
		}

		setProcessed(true);
		m_processMsg = Utilerias.getLabel("egresos.reg.docstatus").concat(" ").concat(Utilerias.getLabel(getDocAction()));
		//
		setDocAction(DOCACTION_Close);
		//Card 955 reporte de Ordenes de compra se setea la fecha cuando se completa la orden
		setDateCompleted(new Timestamp(System.currentTimeMillis()));

		return DocAction.STATUS_Completed;
	} // completeIt

	/**
	 * Create Shipment
	 * 
	 * @param dt
	 *            order document type
	 * @param movementDate
	 *            optional movement date (default today)
	 * @return shipment or null
	 */
	private MInOut createShipment(MDocType dt, Timestamp movementDate) {
		log.info("For " + dt);
		MInOut shipment = new MInOut(this, dt.getC_DocTypeShipment_ID(),
				movementDate);
		// shipment.setDateAcct(getDateAcct());
		if (!shipment.save(get_TrxName())) {
			m_processMsg = "Could not create Shipment";
			return null;
		}
		//
		MOrderLine[] oLines = getLines(true, null);
		for (int i = 0; i < oLines.length; i++) {
			MOrderLine oLine = oLines[i];
			//
			MInOutLine ioLine = new MInOutLine(shipment);
			// Qty = Ordered - Delivered
			BigDecimal MovementQty = oLine.getQtyOrdered().subtract(
					oLine.getQtyDelivered());
			// Location
			int M_Locator_ID = MStorage
					.getMLocatorID(oLine.getM_Warehouse_ID(), oLine
							.getM_Product_ID(), oLine
							.getM_AttributeSetInstance_ID(), MovementQty,
							get_TrxName());

			if (M_Locator_ID == 0) { // Get default Location
				MWarehouse wh = MWarehouse.get(getCtx(), oLine
						.getM_Warehouse_ID());
				M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
			}
			//
			ioLine.setOrderLine(oLine, M_Locator_ID, MovementQty);
			ioLine.setQty(MovementQty);

			if (oLine.getQtyEntered().compareTo(oLine.getQtyOrdered()) != 0) {
				ioLine.setQtyEntered(MovementQty
						.multiply(oLine.getQtyEntered()).divide(
								oLine.getQtyOrdered(), 6,
								BigDecimal.ROUND_HALF_UP));
			}

			if (!ioLine.save(get_TrxName())) {
				m_processMsg = "Could not create Shipment Line";
				return null;
			}
		}
		// Manually Process Shipment
		String status = shipment.completeIt();
		shipment.setDocStatus(status);
		shipment.save(get_TrxName());
		if (!DOCSTATUS_Completed.equals(status)) {
			m_processMsg = "@M_InOut_ID@: " + shipment.getProcessMsg();
			return null;
		}
		return shipment;
	} // createShipment

	/**
	 * Create Invoice
	 * 
	 * @param dt
	 *            order document type
	 * @param shipment
	 *            optional shipment
	 * @param invoiceDate
	 *            invoice date
	 * @return invoice or null
	 */
	private MInvoice createInvoice(MDocType dt, MInOut shipment,
			Timestamp invoiceDate) {
		log.info(dt.toString());
		MInvoice invoice = new MInvoice(this, dt.getC_DocTypeInvoice_ID(),
				invoiceDate);
		if (!invoice.save(get_TrxName())) {
			m_processMsg = "Could not create Invoice";
			return null;
		}

		// If we have a Shipment - use that as a base
		if (shipment != null) {
			if (!INVOICERULE_AfterDelivery.equals(getInvoiceRule())) {
				setInvoiceRule(INVOICERULE_AfterDelivery);
			}
			//
			MInOutLine[] sLines = shipment.getLines(false);
			for (int i = 0; i < sLines.length; i++) {
				MInOutLine sLine = sLines[i];
				//
				MInvoiceLine iLine = new MInvoiceLine(invoice);
				iLine.setShipLine(sLine);
				// Qty = Delivered
				iLine.setQtyEntered(sLine.getQtyEntered());
				iLine.setQtyInvoiced(sLine.getMovementQty());
				if (!iLine.save(get_TrxName())) {
					m_processMsg = "Could not create Invoice Line from Shipment Line";
					return null;
				}
				//
				sLine.setIsInvoiced(true);
				if (!sLine.save(get_TrxName())) {
					log.warning("Could not update Shipment line: " + sLine);
				}
			} // for
		} else { // Create Invoice from Order
			if (!INVOICERULE_Immediate.equals(getInvoiceRule())) {
				setInvoiceRule(INVOICERULE_Immediate);
			}
			//
			MOrderLine[] oLines = getLines();
			for (int i = 0; i < oLines.length; i++) {
				MOrderLine oLine = oLines[i];
				//
				MInvoiceLine iLine = new MInvoiceLine(invoice);
				iLine.setOrderLine(oLine);
				// Qty = Ordered - Invoiced
				iLine.setQtyInvoiced(oLine.getQtyOrdered().subtract(
						oLine.getQtyInvoiced()));
				if (oLine.getQtyOrdered().compareTo(oLine.getQtyEntered()) == 0) {
					iLine.setQtyEntered(iLine.getQtyInvoiced());
				} else {
					iLine.setQtyEntered(iLine.getQtyInvoiced().multiply(
							oLine.getQtyEntered())
							.divide(oLine.getQtyOrdered(), 12,
									BigDecimal.ROUND_HALF_UP));
				}
				if (!iLine.save(get_TrxName())) {
					m_processMsg = "Could not create Invoice Line from Order Line";
					return null;
				}
			} // for
		} // if
		// Manually Process Invoice
		String status = invoice.completeIt();
		invoice.setDocStatus(status);
		invoice.save(get_TrxName());
		setC_CashLine_ID(invoice.getC_CashLine_ID());
		if (!DOCSTATUS_Completed.equals(status)) {
			m_processMsg = "@C_Invoice_ID@: " + invoice.getProcessMsg();
			return null;
		}
		return invoice;
	} // createInvoice

	/**
	 * Create Counter Document
	 * 
	 * @return counter order
	 */
	private MOrder createCounterDoc() {
		// Is this itself a counter doc ?
		if (getRef_Order_ID() != 0) {
			return null;
		}

		// Org Must be linked to BPartner
		MOrg org = MOrg.get(getCtx(), getAD_Org_ID());
		int counterC_BPartner_ID = org.getLinkedC_BPartner_ID();
		if (counterC_BPartner_ID == 0) {
			return null;
		}
		// Business Partner needs to be linked to Org
		MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), null);
		int counterAD_Org_ID = bp.getAD_OrgBP_ID_Int();
		if (counterAD_Org_ID == 0) {
			return null;
		}

		MBPartner counterBP = new MBPartner(getCtx(), counterC_BPartner_ID,
				null);
		MOrgInfo counterOrgInfo = MOrgInfo.get(getCtx(), counterAD_Org_ID);
		log.info("Counter BP=" + counterBP.getName());

		// Document Type
		int C_DocTypeTarget_ID = 0;
		MDocTypeCounter counterDT = MDocTypeCounter.getCounterDocType(getCtx(),
				getC_DocType_ID());
		if (counterDT != null) {
			log.fine(counterDT.toString());
			if (!counterDT.isCreateCounter() || !counterDT.isValid()) {
				return null;
			}
			C_DocTypeTarget_ID = counterDT.getCounter_C_DocType_ID();
		} else { // indirect
			C_DocTypeTarget_ID = MDocTypeCounter.getCounterDocType_ID(getCtx(),
					getC_DocType_ID());
			log.fine("Indirect C_DocTypeTarget_ID=" + C_DocTypeTarget_ID);
			if (C_DocTypeTarget_ID <= 0) {
				return null;
			}
		}
		// Deep Copy
		MOrder counter = copyFrom(this, getDateOrdered(), C_DocTypeTarget_ID,
				!isSOTrx(), true, false, get_TrxName());
		//
		counter.setAD_Org_ID(counterAD_Org_ID);
		counter.setM_Warehouse_ID(counterOrgInfo.getM_Warehouse_ID());
		//
		counter.setBPartner(counterBP);
		counter.setDatePromised(getDatePromised()); // default is date ordered
		// Refernces (Should not be required
		counter.setSalesRep_ID(getSalesRep_ID());
		counter.save(get_TrxName());

		// Update copied lines
		MOrderLine[] counterLines = counter.getLines(true, null);
		for (int i = 0; i < counterLines.length; i++) {
			MOrderLine counterLine = counterLines[i];
			counterLine.setOrder(counter); // copies header values (BP, etc.)
			counterLine.setPrice();
			counterLine.setTax();
			counterLine.save(get_TrxName());
		}
		log.fine(counter.toString());

		// Document Action
		if (counterDT != null) {
			if (counterDT.getDocAction() != null) {
				counter.setDocAction(counterDT.getDocAction());
				counter.processIt(counterDT.getDocAction());
				counter.save(get_TrxName());
			}
		}
		return counter;
	} // createCounterDoc

	/**
	 * Void Document. Set Qtys to 0 - Sales: reverse all documents
	 * 
	 * @return true if success
	 */
	public boolean voidIt() {
		MOrderLine[] lines = getLines(true, "M_Product_ID");
		log.info(toString());
		for (int i = 0; i < lines.length; i++) {
			MOrderLine line = lines[i];
			BigDecimal old = line.getQtyOrdered();
			if (old.signum() != 0) {
				line.addDescription(Msg.getMsg(getCtx(), "Voided") + " (" + old
						+ ")");
				line.setQty(Env.ZERO);
				line.setLineNetAmt(Env.ZERO);
				line.save(get_TrxName());
			}
		}
		addDescription(Msg.getMsg(getCtx(), "Voided"));
		// Clear Reservations
		if (!reserveStock(null, lines)) {
			m_processMsg = "Cannot unreserve Stock (void)";
			return false;
		}

		if (!createReversals()) {
			return false;
		}

		setProcessed(true);
		m_processMsg = Utilerias.getLabel("egresos.reg.docstatus").concat(" ").concat(Utilerias.getLabel(getDocAction()));
		setDocAction(DOCACTION_None);
		return true;
	} // voidIt

	/**
	 * Create Shipment/Invoice Reversals
	 * 
	 * @return true if success
	 */
	private boolean createReversals() {
		// Cancel only Sales
		if (!isSOTrx()) {
			return true;
		}

		log.info("createReversals");
		StringBuffer info = new StringBuffer();

		// Reverse All *Shipments*
		info.append("@M_InOut_ID@:");
		MInOut[] shipments = getShipments();
		for (int i = 0; i < shipments.length; i++) {
			MInOut ship = shipments[i];
			// if closed - ignore
			if (MInOut.DOCSTATUS_Closed.equals(ship.getDocStatus())
					|| MInOut.DOCSTATUS_Reversed.equals(ship.getDocStatus())
					|| MInOut.DOCSTATUS_Voided.equals(ship.getDocStatus())) {
				continue;
			}
			ship.set_TrxName(get_TrxName());

			// If not completed - void - otherwise reverse it
			if (!MInOut.DOCSTATUS_Completed.equals(ship.getDocStatus())) {
				if (ship.voidIt()) {
					ship.setDocStatus(MInOut.DOCSTATUS_Voided);
				}
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

		// Reverse All *Invoices*
		info.append(" - @C_Invoice_ID@:");
		MInvoice[] invoices = getInvoices();
		for (int i = 0; i < invoices.length; i++) {
			MInvoice invoice = invoices[i];
			// if closed - ignore
			if (MInvoice.DOCSTATUS_Closed.equals(invoice.getDocStatus())
					|| MInvoice.DOCSTATUS_Reversed.equals(invoice
							.getDocStatus())
					|| MInvoice.DOCSTATUS_Voided.equals(invoice.getDocStatus())) {
				continue;
			}

			invoice.set_TrxName(get_TrxName());

			// If not compleded - void - otherwise reverse it
			if (!MInvoice.DOCSTATUS_Completed.equals(invoice.getDocStatus())) {
				if (invoice.voidIt()) {
					invoice.setDocStatus(MInvoice.DOCSTATUS_Voided);
					MFactAcct.delete(MInvoice.Table_ID, invoice.getC_Invoice_ID(), invoice.get_TrxName());
				}
			} else if (invoice.reverseCorrectIt()) { // completed invoice
				invoice.setDocStatus(MInvoice.DOCSTATUS_Reversed);
				info.append(" ").append(invoice.getDocumentNo());
			} else {
				m_processMsg = "Could not reverse Invoice " + invoice;
				return false;
			}
			invoice.setDocAction(MInvoice.DOCACTION_None);
			invoice.save(get_TrxName());
		} // for all shipments

		m_processMsg = info.toString();
		return true;
	} // createReversals

	/**
	 * Close Document. Cancel not delivered Qunatities
	 * 
	 * @return true if success
	 */
	public boolean closeIt() {
		log.info(toString());

		// Close Not delivered Qty - SO/PO
		MOrderLine[] lines = getLines(true, "M_Product_ID");
		for (int i = 0; i < lines.length; i++) {
			MOrderLine line = lines[i];
			BigDecimal old = line.getQtyOrdered();
			if (old.compareTo(line.getQtyDelivered()) != 0) {
				line.setQtyLostSales(line.getQtyOrdered().subtract(
						line.getQtyDelivered()));
				line.setQtyOrdered(line.getQtyDelivered());
				// QtyEntered unchanged
				line.addDescription("Close (" + old + ")");
				line.save(get_TrxName());
			}
		}
		// Clear Reservations
		if (!reserveStock(null, lines)) {
			m_processMsg = "Cannot unreserve Stock (close)";
			return false;
		}
		setProcessed(true);
		m_processMsg = Utilerias.getLabel("egresos.reg.docstatus").concat(" ").concat(Utilerias.getLabel(getDocAction()));
		setDocAction(DOCACTION_None);
		return true;
	} // closeIt

	/**
	 * Reverse Correction - same void
	 * 
	 * @return true if success
	 */
	public boolean reverseCorrectIt() {
		log.info(toString());
		return voidIt();
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
	 * Re-activate.
	 * 
	 * @return true if success
	 */
	public boolean reActivateIt() {
		log.info(toString());

		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		String DocSubTypeSO = dt.getDocSubTypeSO();

		// PO - just re-open
		if (!isSOTrx()) {
			log.info("Existing documents not modified - " + dt);
			// Reverse Direct Documents
		} else if (MDocType.DOCSUBTYPESO_OnCreditOrder.equals(DocSubTypeSO) // (W)illCall(I)nvoice
				|| MDocType.DOCSUBTYPESO_WarehouseOrder.equals(DocSubTypeSO) // (W)illCall(P)ickup
				|| MDocType.DOCSUBTYPESO_POSOrder.equals(DocSubTypeSO)) { // (W)alkIn(R)eceipt
			if (!createReversals()) {
				return false;
			}
		} else {
			log.info("Existing documents not modified - SubType="
					+ DocSubTypeSO);
		}

		setDocAction(DOCACTION_Complete);
		setProcessed(false);
		return true;
	} // reActivateIt

	/*************************************************************************
	 * Get Summary
	 * 
	 * @return Summary of Document
	 */
	public String getSummary() {
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		// : Grand Total = 123.00 (#1)
		sb.append(": ").append(Msg.translate(getCtx(), "GrandTotal")).append(
				"=").append(getGrandTotal());
		if (m_lines != null) {
			sb.append(" (#").append(m_lines.length).append(")");
		}
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
		return getSalesRep_ID();
	} // getDoc_User_ID

	/**
	 * Get Document Approval Amount
	 * 
	 * @return amount
	 */
	public BigDecimal getApprovalAmt() {
		return getGrandTotal();
	} // getApprovalAmt

	/*
	 * POS
	 */
	public static List<MOrder> get(Properties ctx, int cliente,
			Timestamp fechaInicio, Timestamp fechaFin, String pedidoTxt,

			String trxName) {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MOrder> numSerie = new ArrayList<MOrder>();

		try {
			sql.append(" SELECT *  ").append(
					" FROM C_Order where isActive = 'Y' ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
					.toString(), "C_Order"));

			if (cliente > 0) {
				sql.append(" AND  C_BPartner_ID = ?  ");
			}
			if (fechaInicio != null) {
				sql.append(" AND  DateOrdered = ?  ");
			}
			if (fechaFin != null) {
				sql.append(" AND  DateOrdered = ?  ");
			}
			if (pedidoTxt != null) {
				sql.append(" AND DocumentNo = ?  ");
			}

			pstmt = DB.prepareStatement(sql.toString(), null);
			int count = 0;
			if (cliente > 0) {
				count++;
				pstmt.setInt(count, cliente);
			}
			if (fechaInicio != null) {
				count++;
				pstmt.setTimestamp(count, fechaInicio);
			}
			if (fechaFin != null) {
				count++;
				pstmt.setTimestamp(count, fechaInicio);
			}
			if (pedidoTxt != null) {
				count++;
				pstmt.setString(count, pedidoTxt);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				numSerie.add(new MOrder(ctx, rs, trxName));
			}

		} catch (SQLException e) {
			;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return numSerie;
	}
	
	public static List<MOrder> getPurchaseOrders(Properties ctx, String where, String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MOrder> result = new ArrayList<MOrder>();

		try {
			sql.append(" SELECT c.*  ").append(" FROM C_Order c ");
			
			if(where != null && !where.isEmpty()){
				sql.append(where);
			}
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_Order", "c"));
			sql.append(" order by c.created desc ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rset = pstmt.executeQuery();			

			while (rset.next()) {
				result.add(new MOrder(ctx, rset, trxName));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "MOrder.getPurchaseOrders - sql = " + sql, e);
		} finally {
			DB.close(rset, pstmt);
			rset = null;
			pstmt = null;
		}
		return result;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param where
	 * @param params
	 * @param trxName
	 * @return
	 */
	public static List<MOrder> getPurchaseOrdersToMatReceipt(Properties ctx, String where, List<Object> params, boolean invoice, String trxName) {
		StringBuilder sql = new StringBuilder(" SELECT c.*, sum(1)  ").append(" FROM C_Order c ")
		.append(" INNER JOIN c_orderline ol on ");
		if(invoice){
			sql.append(" (c.c_order_id = ol.c_order_id AND ol.qtyordered > ol.qtyinvoiced ) ");
		}else{
			sql.append(" (c.c_order_id = ol.c_order_id AND ol.qtyordered > ol.qtydelivered ) ");
		}
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MOrder> result = new ArrayList<MOrder>();

		try {
			
			if(where != null && !where.isEmpty()){
				sql.append(where);
			}
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_Order", "c"));
			sql.append("  GROUP BY c.c_order_id ORDER BY c.documentno desc ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (params.size() > 0) {
				DB.setParameters(pstmt, params);
			}
			rset = pstmt.executeQuery();			

			while (rset.next()) {
				result.add(new MOrder(ctx, rset, trxName));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "MOrder.getPurchaseOrdersToMatReceipt " + sql, e);
		} finally {
			DB.close(rset, pstmt);
			rset = null;
			pstmt = null;
		}
		return result;
	}
	
//	private String estatus = "";
	public String getEstatus(){
		return MRefList.getListName(getCtx(), X_M_Requisition.DOCSTATUS_AD_Reference_ID, getDocStatus());
	}
	
//	private String prioridad = "";
	public String getPrioridad(){
		return MRefList.getListName(getCtx(), X_M_Requisition.PRIORITYRULE_AD_Reference_ID, getPriorityRule());
	}
	
	/**
	 * Crear una recepción de material a partir de un inventario fisico o salida al gasto
	 * @param from: inventario fisico o salida al gasto, en estatus de "Completo"
	 * @return ErrorList con los errores
	 */
    public static ErrorList createMaterialReceipt(final MMovement from, final List<TransferBean> lines) {
    	final ErrorList errors = new ErrorList();
    	if (from == null || from.getM_Movement_ID() <= 0) {
    		errors.add(new Error(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "error.exp.tipoDocumento")));
    	} else if (lines.isEmpty()) {// Lineas del documento requerido para continuar
    		errors.add(new Error(Error.VALIDATION_ERROR, Utilerias.getMsg(from.getCtx(), "error.oc.nolines")));
    	} else if (from.getWarehouseCosigna() == null) {
    		errors.add(new Error(Error.VALIDATION_ERROR, Utilerias.getMsg(from.getCtx(), "error.requerido.warehouseID")));
    	} else if (from.getWarehouseCosigna().getC_BPartner_ID()<=0) {
    		errors.add(new Error(Error.VALIDATION_ERROR, Utilerias.getMsg(from.getCtx(), "error.noExisteSocio")));
    	} else {

    		final MOrder mOrder = new MOrder(from);
			if(mOrder.save()){
				final MInOut mInOut = mOrder.createInOutConsignment(
						(new MDocType(from.getCtx(), from.getC_DocType_ID(), from.get_TrxName())).getC_DocTypeShipment_ID()
						, from.getMovementDate());
				
				if(mInOut==null){
					errors.add(new Error(Error.EXCEPTION_ERROR
							, MedsysException.getMessageFromLogger(Utilerias.getLabel("error.crearRecepcionMaterial", mOrder.getDocumentNo()))));
				} else {

					if(mOrder.addLines(lines, mInOut)){
						mOrder.startWorkflow(errors,  DocAction.ACTION_Complete, DocAction.STATUS_Completed, from.get_TrxName());
						mInOut.startWorkflow(errors,  DocAction.ACTION_Complete, DocAction.STATUS_Completed, from.get_TrxName());
						
						if(errors.isEmpty()) {
							mInOut.setM_Movement_ID(from.getM_Movement_ID());
							mInOut.save();// 
						}
					} else {
						errors.add(new Error(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger(Utilerias.getMsg(from.getCtx(), "error.oc.nolines"))));
					}
				}
			} else {
				errors.add(new Error(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger(Utilerias.getMsg(from.getCtx(), "error.CPORequisition.Order"))));
			}
    	}
    	return errors;
    }

	/**
	 * Crear una recepcion de material a partir de un inventario fisico o salida al gasto
	 * @param from: inventario fisico o salida al gasto, en estatus de "Completo"
	 * @return null si es correcto el proceso en caso contrario regresa el error
	 */
	public static ErrorList createMaterialReceipt(final MInventory from, final List<TransferBean> lineas){
		final ErrorList errors = new ErrorList();
		if(from==null || from.getM_Inventory_ID()<=0){
			errors.add(new Error(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "error.exp.tipoDocumento")));
		} else if (from.getWarehouse() == null || !from.getWarehouse().isConsigna() || !from.getWarehouse().isVirtual()) {
			errors.add(new Error(Error.VALIDATION_ERROR, Utilerias.getMsg(from.getCtx(), "error.requerido.warehouseID")));
		} else if (from.getWarehouse().getC_BPartner_ID()<=0) {
			errors.add(new Error(Error.VALIDATION_ERROR, Utilerias.getMsg(from.getCtx(), "error.noExisteSocio")));
		} else if(lineas.isEmpty() && from.isPhysicalInventory()){
			log.info("No es necesario generar una OC");
		} else if(lineas.isEmpty() && !from.isPhysicalInventory()){
			errors.add(new Error(Error.VALIDATION_ERROR, Utilerias.getMsg(from.getCtx(), "error.oc.nolines")));
		
		} else {
			final MOrder mOrder = new MOrder(from);
			if(mOrder.save()){
				final MInOut mInOut = mOrder.createInOutConsignment(
						(new MDocType(from.getCtx(), from.getC_DocType_ID(), from.get_TrxName())).getC_DocTypeShipment_ID()
						, from.getMovementDate());
				
				if(mInOut==null){
					errors.add(new Error(Error.EXCEPTION_ERROR
							, MedsysException.getMessageFromLogger(Utilerias.getLabel("error.crearRecepcionMaterial", mOrder.getDocumentNo()))));
				} else {

					if(mOrder.addLines(lineas, mInOut)){
						mOrder.startWorkflow(errors,  DocAction.ACTION_Complete, DocAction.STATUS_Completed, from.get_TrxName());
						mInOut.startWorkflow(errors,  DocAction.ACTION_Complete, DocAction.STATUS_Completed, from.get_TrxName());
						
						if(errors.isEmpty()) {
							mInOut.setM_Inventory_ID(from.getM_Inventory_ID());
							mInOut.save();// 
						}
					} else {
						errors.add(new Error(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger(Utilerias.getMsg(from.getCtx(), "error.oc.nolines"))));
					}
				}
			} else {
				errors.add(new Error(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger(Utilerias.getMsg(from.getCtx(), "error.CPORequisition.Order"))));
			}
		}
		return errors;
	}
	
	/**
	 * Crear una orden de compra a partir de una recepción de material
	 * @param from: Recepción de material
	 * @param lines: Lineas de tipo TransferBean con los datos necesarios para crear las lineas de orden
	 * @return ErrorList con los errores
	 */
    public static MOrder createMaterialReceipt(final ErrorList errors, final MInOut from, final List<TransferBean> lines) {
    	MOrder mOrder = null;
    	if (from == null || from.getM_InOut_ID() <= 0) {
    		errors.add(new Error(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "error.exp.tipoDocumento")));
    	} else if (lines.isEmpty()) {// Lineas del documento requerido para continuar
    		log.info("No es necesario generar una OC");

    	} else {

    		mOrder = new MOrder(from);
    		if (mOrder.save()) {
    			// Convertir las lineas del movimiento a la orden
    			if(mOrder.addLines(lines, null)){
					mOrder.startWorkflow(errors,  DocAction.ACTION_Complete, DocAction.STATUS_Completed, from.get_TrxName());
    			} else {
					errors.add(new Error(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger(Utilerias.getMsg(from.getCtx(), "error.oc.nolines"))));
				}
    		} else {
				errors.add(new Error(Error.EXCEPTION_ERROR, MedsysException.getMessageFromLogger(Utilerias.getMsg(from.getCtx(), "error.CPORequisition.Order"))));
			}
    	}
    	return mOrder;
    }
    
	/** Crear encabezado de la orden a partir de un inventario */
	private MOrder (final MInventory from) {
		this(from.getCtx(), 0, from.get_TrxName());
		setClientOrg (from.getAD_Client_ID (), from.getAD_Org_ID());
		setBPartner (from.getWarehouse().getBPartner());// Proveedor de consigna
		setC_BPartner_Location_ID(from.getWarehouse().getBPartner().getLastAddressForBPartner());
		setM_PriceList_ID(from.getWarehouse().getBPartner().getPO_PriceList_ID());
		setAD_User_ID(from.getCreatedBy());
		setM_Warehouse_ID(from.getM_Warehouse_ID());// Almacen de consigna
		setisConsigna(true);
    	setIsDropShip(true);// No afecte existencias
    	setDescription(Utilerias.getLabel("msj.EnConsigna"));
    	setDateOrdered(new Timestamp(System.currentTimeMillis()));
    	setDatePromised(new Timestamp(System.currentTimeMillis()));
    	setIsSOTrx(false);
    	setC_DocTypeTarget_ID();
    	setDocStatus(DOCSTATUS_Drafted);
		setDocAction(DOCACTION_Complete);
	}

	/** Crear encabezado de la orden a partir de un movimiento de productos */
	private MOrder(final MMovement from) {
		this(from.getCtx(), 0, from.get_TrxName());
		setClientOrg (from.getAD_Client_ID (), from.getAD_Org_ID());
		setAD_User_ID(from.getAD_User_ID());
		setBPartner (from.getWarehouseCosigna().getBPartner());// Proveedor de consigna
		setC_BPartner_Location_ID(from.getWarehouseCosigna().getBPartner().getLastAddressForBPartner());
		setM_PriceList_ID(from.getWarehouseCosigna().getBPartner().getPO_PriceList_ID());
		setM_Warehouse_ID(from.getWarehouse().isConsigna()?from.getM_WarehouseTo_ID():from.getM_Warehouse_ID());// Almacen de propio
    	setisConsigna(true);// Es consigna
    	setIsDropShip(true);// No afecte existencias
    	setDescription(Utilerias.getLabel("msj.EnConsigna"));
    	setDateOrdered(new Timestamp(System.currentTimeMillis()));
    	setDatePromised(new Timestamp(System.currentTimeMillis()));
    	setIsSOTrx(false);
    	setC_DocTypeTarget_ID();
    	setDocStatus(DOCSTATUS_Drafted);
		setDocAction(DOCACTION_Complete);
	}
	
	/** Crear encabezado de la orden a partir de una recepción de material */
	private MOrder(final MInOut from) {
		this(from.getCtx(), 0, from.get_TrxName());
	
		setClientOrg (from.getAD_Client_ID (), from.getAD_Org_ID());
		setAD_User_ID(from.getAD_User_ID());
		setBPartner (from.getBPartner());
		
		if(from.getC_BPartner_Location_ID()>0){
			setC_BPartner_Location_ID(from.getC_BPartner_Location_ID());
		} else {
			final int ilPBPLocationId = from.getBPartner().getPrimaryC_BPartner_Location_ID();
			if (ilPBPLocationId > 0) {
				setC_BPartner_Location_ID(ilPBPLocationId);
			}
		}
		
		if (from.getM_PriceList_ID() > 0) {
			setM_PriceList_ID(from.getM_PriceList_ID());
			if (from.getC_Currency_ID() > 0) {
				setC_Currency_ID(from.getC_Currency_ID());
			}
		} else if (from.getBPartner().getPO_PriceList_ID() > 0) {
			setM_PriceList_ID(from.getBPartner().getPO_PriceList_ID());
			final MPriceList pl = new MPriceList(getCtx(), from.getBPartner().getPO_PriceList_ID(), null);
			setC_Currency_ID(pl != null && pl.getC_Currency_ID() > 0 ? pl.getC_Currency_ID() : Env.getC_Currency_ID(getCtx()));
		}
		
		setM_Warehouse_ID(from.getM_Warehouse_ID());
    	setisConsigna(false);// No es consigna
    	setIsDropShip(false);// Si afecte existencias
    	
    	setDateOrdered(from.getDateOrdered()==null?new Timestamp(System.currentTimeMillis()):from.getDateOrdered());
    	setDatePromised(from.getDateReceived()==null?new Timestamp(System.currentTimeMillis()):from.getDateReceived());
    	
    	setIsSOTrx(from.isSOTrx());
    	setC_DocTypeTarget_ID();
    	setC_DocType_ID(getC_DocTypeTarget_ID());
    	setDocStatus(DOCSTATUS_Drafted);
		setDocAction(DOCACTION_Complete);
		
		//ETS 06973 — Orden de compra urgente - leyenda null.
		//Jesús Cantú el 06 Mayo 2014.
		final String slDescriptionFinal;
		if (from.getDescription() == null) {
			slDescriptionFinal = Msg.getMsg(getCtx(), "ExpressMaterialRequest", true);
		} else {
			slDescriptionFinal = Msg.getMsg(getCtx(), "ExpressMaterialRequest", true) +
					StringUtils.defaultIfEmpty(" - " + from.getDescription(), StringUtils.EMPTY);
		}
		setDescription(slDescriptionFinal);
		setIsTransferred(X_C_Order.ISTRANSFERRED_Transferred);
	
		// representante de ventas (admin)
		setSalesRep_ID(
				MEXMEUser.getSalesRepAdmin(getCtx()) > 0 
				? MEXMEUser.getSalesRepAdmin(getCtx()) 
						: Env.getAD_User_ID(getCtx()));
		setAD_OrgTrx_ID(from.getAD_OrgTrx_ID());
	}
	
	/** Crear un inout a partir de una orden */
    private MInOut createInOutConsignment(final int C_DocTypeShipment_ID, final Timestamp movementDate){
    	MInOut mInOut = new MInOut(this, C_DocTypeShipment_ID, movementDate);
		mInOut.setIsDropShip(true);// No afecte existencias
		mInOut.setSetpoint(true);// Es de consigna
		if (!mInOut.save(get_TrxName())) {
			mInOut = null;
		}
		return mInOut;
    }
    
    /**
	 * Crear las lineas de consigna a partir de un movimeinto
	 * @param from
	 * @return null si hay error o listado 
	 */
    private boolean addLines(final List<TransferBean> soLines, final MInOut mInOut) {
    	boolean success = true;
    	for (final TransferBean soBean: soLines) {
    		final MOrderLine poLine = createOrderLine(soBean);
    		
    		if(poLine==null){
    			success = false;
				break;
				
    		} else {
    			final MInOutLine ioLine;
    			if(soBean.getInOutLine()>0){
    				ioLine = new MInOutLine(getCtx(), soBean.getInOutLine(), get_TrxName());
    			} else {
    				ioLine = createInOutLine(poLine, mInOut);
    			}
    			
    			if(ioLine==null){
					success = false;
					break;
    			} else {
    				ioLine.setC_OrderLine_ID(poLine.getC_OrderLine_ID());
    				ioLine.save();
    			}
    		}
    	}
    	return success;
    }
    
    /**
	 * Crear las lineas de consigna a partir de un movimeinto
	 * @param from
	 * @return null si hay error o listado 
	 */
    private MOrderLine createOrderLine(final TransferBean soBean) {
    	MOrderLine poLine = new MOrderLine (this);
    	poLine.setAD_OrgTrx_ID(this.getAD_OrgTrx_ID());
    	poLine.setDescription(soBean.getDescription());
    	poLine.setDatePromised(soBean.getDatePromised());

    	poLine.setM_Product_ID(soBean.getM_Product_ID(), true);
    	poLine.setM_AttributeSetInstance_ID(soBean.getM_AttributeSetInstance_ID());
    	poLine.setC_UOMVolume_ID(soBean.getC_UOMVolume_ID());
    	poLine.setC_UOM_ID(soBean.getC_UOM_ID());

    	poLine.setQty(soBean.getQtyEntered());
    	poLine.setQtyEntered_Vol (soBean.getQtyEntered_Vol());
    	poLine.setQtyOrdered_Vol (soBean.getQtyOrdered_Vol());

    	poLine.setPriceActual (soBean.getPriceActual());
    	poLine.setPriceList (soBean.getPriceList());
    	poLine.setPriceLimit (soBean.getPriceList());
    	poLine.setPriceEntered(soBean.getPriceActual());
    	poLine.setDiscount(soBean.getDiscount());

    	if(soBean.getC_UOM_ID() == soBean.getC_UOMVolume_ID()){
    		poLine.setPriceActual_Vol (soBean.getPriceActual());
    		poLine.setPriceList_Vol   (soBean.getPriceList());
    		poLine.setPriceEntered_Vol(soBean.getPriceEntered());

    	}else {
    		poLine.setPriceActual_Vol (soBean.getPriceActual_Vol());
    		poLine.setPriceList_Vol   (soBean.getPriceList_Vol());
    		poLine.setPriceEntered_Vol(soBean.getPriceEntered_Vol());
    	}

    	if(soBean.getcTaxId()>0){
    		poLine.setC_Tax_ID(soBean.getcTaxId());
    	} else {
    		poLine.setTax();
    	}

    	poLine.setLineNetAmt();
    	poLine.setAutomatic(true);// iDENTIFICA de donde es el proceso


    	if(!poLine.save()){
    		poLine = null;
    	}

    	return poLine;
    }
    
    /** Crear linea de inout a partir de una linea de orden */
	private MInOutLine createInOutLine(final MOrderLine oLine, final MInOut mInOut){
		if(oLine==null || mInOut==null){
			return null;
			
		} else {
			MInOutLine ioLine = new MInOutLine(mInOut);
			PO.copyValues(oLine, ioLine);
			ioLine.set_TrxName(get_TrxName());
			ioLine.setProcessed(false);
			// Qty = Ordered - Delivered
			BigDecimal MovementQty = oLine.getQtyOrdered().subtract(
					oLine.getQtyDelivered());
			// Location
			int M_Locator_ID = MStorage
					.getMLocatorID(oLine.getM_Warehouse_ID(), oLine
							.getM_Product_ID(), oLine
							.getM_AttributeSetInstance_ID(), MovementQty,
							get_TrxName());

			if (M_Locator_ID == 0) { // Get default Location
				MWarehouse wh = MWarehouse.get(getCtx(), oLine
						.getM_Warehouse_ID());
				M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
			}
			//
			ioLine.setOrderLine(oLine, M_Locator_ID, MovementQty);
			ioLine.setQty(MovementQty);

			if (oLine.getQtyEntered().compareTo(oLine.getQtyOrdered()) != 0) {
				ioLine.setQtyEntered(MovementQty
						.multiply(oLine.getQtyEntered()).divide(
								oLine.getQtyOrdered(), 6,
								BigDecimal.ROUND_HALF_UP));
			}


			ioLine.setMovementQty_Vol(oLine.getQtyOrdered_Vol());
			ioLine.setQtyEntered_Vol(oLine.getQtyEntered_Vol());
//			ioLine.setPriceActual_Vol(oLine.getPriceActual_Vol());

			if (!ioLine.save(get_TrxName())) {
				ioLine = null;
			}
			return ioLine;
		}
	}
	
	public boolean timedMessage;

	/**
	 * @return the timedMessage
	 */
	public boolean isTimedMessage() {
		return timedMessage;
	}

	/**
	 * @param timedMessage the timedMessage to set
	 */
	public void setTimedMessage(boolean timedMessage) {
		this.timedMessage = timedMessage;
	}
	
	

} // MOrder
