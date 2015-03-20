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
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.apps.form.BeanPaySelect;
import org.compiere.model.bean.TaxConcepts;
import org.compiere.model.bpm.BeanView;
import org.compiere.model.bpm.BillPayments;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.DynamicModel;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;
import org.compiere.util.vo.CreditNoteDet;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Invoice Model. Please do not set DocStatus and C_DocType_ID directly. They
 * are set in the process() method. Use DocAction and C_DocTypeTarget_ID
 * instead.
 *
 * @author Jorg Janke
 * @version $Id: MInvoice.java,v 1.4 2006/09/05 23:18:54 taniap Exp $
 */
public class MInvoice extends X_C_Invoice implements DocAction {
	/** serialVersionUID */
	private static final long serialVersionUID = -5399177930705835031L;
	/** Logger */
	private static CLogger sLog = CLogger.getCLogger(MInvoice.class);
	/** Cache */
	private static CCache<Integer, MInvoice> sCache = new CCache<Integer, MInvoice>(
			"C_Invoice", 20, 2); // 2
	// minutes
	/** Cuenta paciente */
	private transient MEXMECtaPac mCtapac = null;
	/** Open Amount */
	private transient BigDecimal mOpenAmt = null;
	/** Invoice Lines */
	private transient MInvoiceLine[] mLines;
	/** Invoice Taxes */
	private transient MInvoiceTax[] mTaxes;
	/** Process Message */
	private transient String mProcessMsg = null;
	/** Just Prepared Flag */
	private transient boolean mJustPrepared = false;
	/** cliente */
	private transient MBPartner mCliente = null;
	/** Reversal Flag */
	private transient boolean mReversal = false;
	/** match PO */
	private transient int matchPO = 0;
	/** match Inv */
	private transient int matchInv = 0;
	/** fecha de la factura Cadena */
	private String strDateInvoiced = null;
	/** formato */
	private final SimpleDateFormat stringDate = new SimpleDateFormat(
			"dd/MM/yyyy");
	/** Monto de venta */
	private BigDecimal totalVta = Env.ZERO;
	/** Lista con los cargos de CCCmD y descuento */
	private Map<Integer, BigDecimal> basesGravAseg = null;
	/** Lista con los cargos de CCCmD y descuento */
	private List<MInvoiceLine> linesNotMatch = new ArrayList<MInvoiceLine>();
	/** Extension de la factura */
	private transient MEXMECtaPacExt mCtaPacExt = null;
	/** Termino o condicion de pago */
	private transient MPaymentTerm mPaymentTerm = null;
	/**  */
	private boolean printCM = false;
	private BigDecimal amountToApply = Env.ZERO;
	private Timestamp expirationDate = null;
	private BigDecimal amountDiscountPxP = Env.ZERO;
	private BigDecimal amountDue = Env.ZERO;
	/** Termino o condicion de pago */
	private MDocType mDocType = null;
	
	

	public boolean isPrintCM() {
		return printCM;
	}

	public void setPrintCM(boolean printCM) {
		this.printCM = printCM;
	}

	private InfoInvoice info = null;
	public InfoInvoice getInfo() {
		return info;
	}

//	public void setInfo(InfoInvoice info) {
//		this.info = info;
//	}

	public MInvoice(final Properties ctx, final int C_Invoice_ID, final InfoInvoice mi, final String trxName){
		this(ctx, C_Invoice_ID, trxName);
		info = mi;
	}
	
	/**************************************************************************
	 * Invoice Constructor
	 *
	 * @param ctx
	 *            context
	 * @param C_Invoice_ID
	 *            invoice or 0 for new
	 * @param trxName
	 *            trx name
	 */
	public MInvoice(final Properties ctx, final int C_Invoice_ID,
			final String trxName) {
		super(ctx, C_Invoice_ID, trxName);
		if (C_Invoice_ID == 0) {
			setBackoffice(true);
			setDocStatus(DOCSTATUS_Drafted); // Draft
			setDocAction(DOCACTION_Complete);
			//
			setPaymentRule(PAYMENTRULE_OnCredit); // Payment Terms

			setDateInvoiced(new Timestamp(System.currentTimeMillis()));
			setDateAcct(new Timestamp(System.currentTimeMillis()));
			//
			setChargeAmt(Env.ZERO);
			setTotalLines(Env.ZERO);
			setGrandTotal(Env.ZERO);
			//
			setIsSOTrx(true);
			setIsTaxIncluded(false);
			setIsApproved(false);
			setIsDiscountPrinted(false);
			setIsPaid(false);
			setSendEMail(false);
			setIsPrinted(false);
			setIsMultiple(false);
			// setIsTransferred (false); //MODIFICADO DE CAMPO YES/NO A LISTA
			setIsTransferred(ISTRANSFERRED_PendingTransfer);
			setIsSelfService(false);
			setIsPayScheduleValid(false);
			setIsInDispute(false);
			setPosted(false);
			super.setProcessed(false);
			setProcessing(false);

			// expert: para asignar la estacion de servicio del contexto a la
			// factura
			if (getEXME_EstServ_ID() == 0
					&& Env.getContextAsInt(getCtx(), "#EXME_EstServ_ID") != 0) {
				setEXME_EstServ_ID(Env.getContextAsInt(getCtx(),
						"#EXME_EstServ_ID"));
			}
			// expert: para asignar la estacion de servicio del contexto a la
			// factura
		}
	} // MInvoice

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
	public MInvoice(final Properties ctx, final ResultSet rSet,
			final String trxName) {
		super(ctx, rSet, trxName);
	} // MInvoice

	/**
	 * Get Payments Of BPartner
	 *
	 * @param ctx
	 *            context
	 * @param C_BPartner_ID
	 *            id
	 * @param trxName
	 *            transaction
	 * @return array
	 */
	public static MInvoice[] getOfBPartner(final Properties ctx,
			final int C_BPartner_ID, final String trxName) {
		final ArrayList<MInvoice> list = new ArrayList<MInvoice>();
		String sql = "SELECT * FROM C_Invoice WHERE C_BPartner_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_BPartner_ID);
			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				list.add(new MInvoice(ctx, rSet, trxName));
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		//
		final MInvoice[] retValue = new MInvoice[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfBPartner

	/**
	 * Create new Invoice by copying
	 *
	 * @param from
	 *            invoice
	 * @param dateDoc
	 *            date of the document date
	 * @param docTypeTargetID
	 *            target doc type
	 * @param isSOTrx
	 *            sales order
	 * @param counter
	 *            create counter links
	 * @param trxName
	 *            trx
	 * @param setOrder
	 *            set Order links
	 * @return Invoice
	 */
	public static MInvoice copyFrom(final MInvoice from,
			final Timestamp dateDoc, final int docTypeTargetID,
			final boolean isSOTrx, final boolean counter, final String trxName,
			final boolean setOrder) {
		final MInvoice toInv = new MInvoice(from.getCtx(), 0, null);
		toInv.set_TrxName(trxName);
		PO.copyValues(from, toInv, from.getAD_Client_ID(), from.getAD_Org_ID());
		toInv.set_ValueNoCheck("C_Invoice_ID", I_ZERO);
		toInv.set_ValueNoCheck("DocumentNo", null);
		//
		toInv.setDocStatus(DOCSTATUS_Drafted); // Draft
		toInv.setDocAction(DOCACTION_Complete);
		//
		toInv.setC_DocType_ID(0);
		toInv.setC_DocTypeTarget_ID(docTypeTargetID);
		toInv.setIsSOTrx(isSOTrx);
		//
		toInv.setDateInvoiced(dateDoc);
		toInv.setDateAcct(dateDoc);
		toInv.setDatePrinted(null);
		toInv.setIsPrinted(false);
		//
		toInv.setIsApproved(false);
		toInv.setC_Payment_ID(0);
		toInv.setC_CashLine_ID(0);
		toInv.setIsPaid(false);
		toInv.setIsInDispute(false);
		//
		// Amounts are updated by trigger when adding lines
		toInv.setGrandTotal(Env.ZERO);
		toInv.setTotalLines(Env.ZERO);
		//
		// to.setIsTransferred (false); //MODIFICADO DE CAMPO YES/NO A LISTA
		toInv.setIsTransferred(ISTRANSFERRED_PendingTransfer);
		toInv.setPosted(false);
		toInv.setProcessed(false);
		// delete references
		toInv.setIsSelfService(false);
		if (!setOrder) {
			toInv.setC_Order_ID(0);
		}
		if (counter) {
			toInv.setRef_Invoice_ID(from.getC_Invoice_ID());
			// Try to find Order link
			if (from.getC_Order_ID() != 0) {
				final MOrder peer = new MOrder(from.getCtx(),
						from.getC_Order_ID(), from.get_TrxName());
				if (peer.getRef_Order_ID() != 0) {
					toInv.setC_Order_ID(peer.getRef_Order_ID());
				}
			}
		} else {
			toInv.setRef_Invoice_ID(0);
		}

		if (!toInv.save(trxName)) {
			throw new IllegalStateException("Could not create Invoice");
		}
		if (counter) {
			from.setRef_Invoice_ID(toInv.getC_Invoice_ID());
		}
		// Lines
		if (toInv.copyLinesFrom(from, counter, setOrder) == 0) {
			throw new IllegalStateException("Could not create Invoice Lines");
		}

		return toInv;
	} // copyFrom

	/**
	 * Get PDF File Name
	 *
	 * @param documentDir
	 *            directory
	 * @param C_Invoice_ID
	 *            invoice
	 * @return file name
	 */
	public static String getPDFFileName(final String documentDir,
			final int C_Invoice_ID) {
		final StringBuffer sbuf = new StringBuffer(documentDir);
		if (sbuf.length() == 0) {
			sbuf.append(".");
		}
		if (!sbuf.toString().endsWith(File.separator)) {
			sbuf.append(File.separator);
		}
		sbuf.append("C_Invoice_ID_").append(C_Invoice_ID).append(".pdf");
		return sbuf.toString();
	} // getPDFFileName

	/**
	 * Get MInvoice from Cache
	 *
	 * @param ctx
	 *            context
	 * @param C_Invoice_ID
	 *            id
	 * @return MInvoice
	 */
	public static MInvoice get(final Properties ctx, final int C_Invoice_ID,
			final String trxName)// expert
	// :
	// gisela
	// lee
	// :
	// recibir
	// nombre
	// de
	// transaccion
	{
		final Integer key = Integer.valueOf(C_Invoice_ID);
		MInvoice retValue = (MInvoice) sCache.get(key);
		if (retValue != null
		// Expert : Lama (verificar la sesion, para usar o no el objeto del
		// cache)
				&& (Env.getContextAsInt(ctx, "#AD_Session_ID") == Env
						.getContextAsInt(retValue.getCtx(), "#AD_Session_ID"))) { // lama
			return retValue;
		}
		retValue = new MInvoice(ctx, C_Invoice_ID, trxName); // expert : gisela
		// lee : enviar
		// nombre de
		// transaccion
		if (retValue.get_ID() != 0) {
			sCache.put(key, retValue);
		}
		return retValue;
	} // get

	/**
	 * Create Invoice from Order
	 *
	 * @param order
	 *            order
	 * @param docTypeTargetID
	 *            target document type
	 * @param invoiceDate
	 *            date or null
	 */
	public MInvoice(final MOrder order, int docTypeTargetID,
			final Timestamp invoiceDate) {
		this(order.getCtx(), 0, order.get_TrxName());
		setClientOrg(order);
		setOrder(order); // set base settings
		//
		if (docTypeTargetID == 0) {
			docTypeTargetID = DB
					.getSQLValue(
							null,
							"SELECT C_DocTypeInvoice_ID FROM C_DocType WHERE C_DocType_ID=?",
							order.getC_DocType_ID());
		}

		setC_DocTypeTarget_ID(docTypeTargetID);
		if (invoiceDate != null) {
			setDateInvoiced(invoiceDate);
		}
		setDateAcct(getDateInvoiced());
		//
		setSalesRep_ID(order.getSalesRep_ID());
		//
		setC_BPartner_ID(order.getBill_BPartner_ID());
		setC_BPartner_Location_ID(order.getBill_Location_ID());
		setAD_User_ID(order.getBill_User_ID());
	} // MInvoice

	/**
	 * Create Invoice from Shipment
	 *
	 * @param ship
	 *            shipment
	 * @param invoiceDate
	 *            date or null
	 */
	public MInvoice(final MInOut ship, final Timestamp invoiceDate) {
		this(ship.getCtx(), 0, ship.get_TrxName());
		setClientOrg(ship);
		setShipment(ship); // set base settings
		//
		setC_DocTypeTarget_ID();
		if (invoiceDate != null) {
			setDateInvoiced(invoiceDate);
		}
		setDateAcct(getDateInvoiced());
		//
		setSalesRep_ID(ship.getSalesRep_ID());
		setAD_User_ID(ship.getAD_User_ID());
	} // MInvoice

	/**
	 * Create Invoice from Batch Line
	 *
	 * @param batch
	 *            batch
	 * @param line
	 *            batch line
	 */
	public MInvoice(final MInvoiceBatch batch, final MInvoiceBatchLine line) {
		this(line.getCtx(), 0, line.get_TrxName());
		setClientOrg(line);
		setDocumentNo(line.getDocumentNo());
		//
		setIsSOTrx(batch.isSOTrx());
		final MBPartner bpart = new MBPartner(line.getCtx(),
				line.getC_BPartner_ID(), line.get_TrxName());
		setBPartner(bpart); // defaults
		//
		setIsTaxIncluded(line.isTaxIncluded());
		// May conflict with default price list
		setC_Currency_ID(batch.getC_Currency_ID());
		setC_ConversionType_ID(batch.getC_ConversionType_ID());
		//
		// setPaymentRule(order.getPaymentRule());
		// setC_PaymentTerm_ID(order.getC_PaymentTerm_ID());
		// setPOReference("");
		setDescription(batch.getDescription());
		// setDateOrdered(order.getDateOrdered());
		//
		setAD_OrgTrx_ID(line.getAD_OrgTrx_ID());
		setC_Project_ID(line.getC_Project_ID());
		// setC_Campaign_ID(line.getC_Campaign_ID());
		setC_Activity_ID(line.getC_Activity_ID());
		setUser1_ID(line.getUser1_ID());
		setUser2_ID(line.getUser2_ID());
		//
		setC_DocTypeTarget_ID(line.getC_DocType_ID());
		setDateInvoiced(line.getDateInvoiced());
		setDateAcct(line.getDateAcct());
		//
		setSalesRep_ID(batch.getSalesRep_ID());
		//
		setC_BPartner_ID(line.getC_BPartner_ID());
		setC_BPartner_Location_ID(line.getC_BPartner_Location_ID());
		setAD_User_ID(line.getAD_User_ID());
	} // MInvoice

	/**
	 * Metodo que regresa los items de MInvoice disponibles para mostrar
	 *
	 * @return List<DynamicModel> lista de objetos dinamicos
	 * */
	public static List<DynamicModel> getInvoiceAll(final String and) {
		List<DynamicModel> listDM = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ResultSetMetaData rsmd = null;
		final StringBuilder sql = new StringBuilder(" select ")
				.append(" ci.c_invoice_id, ")
				.append(" ac.name as ad_client, ")
				.append(" ao.name as ad_org, ")
				.append(" dtt.name as doctypetarget, ")
				.append(" ci.documentno, ")
				.append(" ci.dateinvoiced, ")
				.append(" ci.dateacct,  ")
				.append(" cb.name as cbpartner, ")
				.append(" cbl.name as cbpartnerlocation, ")
				.append(" adu.name as aduser, ")
				.append(" mpl.name as mpricelist, ")
				.append(" ci.issotrx, ")
				.append(" ci.c_cashbook_id, ")
				.append(" ci.docstatus, ")
				.append(" ci.processed, ")
				.append(" ci.updated, ")
				.append(" ci.isapproved, ")
				.append(" ci.istransferred, ")
				.append(" ci.totallines ")
				.append(" from c_invoice ci  ")
				.append(" inner join AD_Client ac on ci.ad_client_id = ac.ad_client_id ")
				.append(" inner join AD_ORG ao on ci.ad_org_id = ao.ad_org_id ")
				.append(" inner join C_Doctype dtt on ci.c_doctypetarget_id = dtt.c_doctype_id ")
				.append(" inner join c_bpartner cb on ci.c_bpartner_id = cb.c_bpartner_id ")
				.append(" inner join c_bpartner_location cbl on ci.c_bpartner_location_id = cbl.c_bpartner_location_id ")
				.append(" left join ad_user adu on ci.ad_user_id = adu.ad_user_id ")
				.append(" left join m_pricelist mpl on ci.m_pricelist_id = mpl.m_pricelist_id ")
				.append(" where ci.IsSOTRX='N' AND (ci.C_CashBook_ID IS NULL OR ci.DocStatus = 'CO')  AND ci.IsActive='Y' "
						+ and + " ORDER BY ci.c_invoice_id desc ");
		// AND ci.Processed='N' SE QUITO ESTA AND PARA EL BUEN FUNCIONAMIENTO

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rset = pstmt.executeQuery();
			rsmd = rset.getMetaData();
			listDM = new ArrayList<DynamicModel>();

			while (rset.next()) {
				final DynamicModel dynamicModel = new DynamicModel();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					dynamicModel.setValue(rsmd.getColumnName(i),
							rset.getString(i), rsmd.getColumnClassName(i));
				}
				listDM.add(dynamicModel);
			}
		} catch (SQLException e) {
			sLog.log(Level.SEVERE, e.getMessage());
		} finally {
			DB.close(rset, pstmt);
		}
		return listDM;
	}

	/**
	 * Metodo que regresa los items de MInvoice disponibles para mostrar en
	 * autorizacion
	 *
	 * @return List<DynamicModel> lista de objetos dinamicos
	 * */
	public static List<DynamicModel> getInvoiceAllAut(final String AD_User_ID) {
		List<DynamicModel> listDM = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ResultSetMetaData rsmd = null;
		final StringBuilder sql = new StringBuilder(" select ")
				.append(" ci.c_invoice_id, ")
				.append(" ac.name as ad_client, ")
				.append(" ao.name as ad_org, ")
				.append(" dtt.name as doctypetarget, ")
				.append(" ci.documentno, ")
				.append(" ci.dateinvoiced, ")
				.append(" ci.dateacct,  ")
				.append(" cb.name as cbpartner, ")
				.append(" cbl.name as cbpartnerlocation, ")
				.append(" adu.name as aduser, ")
				.append(" mpl.name as mpricelist, ")
				.append(" ci.issotrx, ")
				.append(" ci.c_cashbook_id, ")
				.append(" ci.docstatus, ")
				.append(" ci.processed, ")
				.append(" ci.updated, ")
				.append(" ci.isapproved, ")
				.append(" ci.istransferred ")
				.append(" from c_invoice ci  ")
				.append(" inner join AD_Client ac on ci.ad_client_id = ac.ad_client_id ")
				.append(" inner join AD_ORG ao on ci.ad_org_id = ao.ad_org_id ")
				.append(" inner join C_Doctype dtt on ci.c_doctypetarget_id = dtt.c_doctype_id ")
				.append(" inner join c_bpartner cb on ci.c_bpartner_id = cb.c_bpartner_id ")
				.append(" inner join c_bpartner_location cbl on ci.c_bpartner_location_id = cbl.c_bpartner_location_id ")
				.append(" left join ad_user adu on ci.ad_user_id = adu.ad_user_id ")
				.append(" left join m_pricelist mpl on ci.m_pricelist_id = mpl.m_pricelist_id ")
				.append(" INNER JOIN EXME_USERAUT EU ON EU.C_DOCTYPE_ID = ci.C_DOCTYPETARGET_ID ")
				.append(" WHERE EU.AD_USER_ID = ? AND EU.AD_USERAUT_ID = ci.CREATEDBY ")
				.append(" AND ci.TOTALLINES BETWEEN EU.IMPORTEMIN AND EU.IMPORTEMAX ")
				.append(" AND ci.IsSOTRX='N' AND (ci.C_CashBook_ID IS NULL OR ci.DocStatus = 'CO')  AND ci.IsActive='Y' ORDER BY ci.c_invoice_id desc ");
		// AND ci.Processed='N' SE QUITO ESTA AND PARA EL BUEN FUNCIONAMIENTO

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, AD_User_ID);
			rset = pstmt.executeQuery();
			rsmd = rset.getMetaData();
			listDM = new ArrayList<DynamicModel>();

			while (rset.next()) {
				final DynamicModel dynamicModel = new DynamicModel();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					dynamicModel.setValue(rsmd.getColumnName(i),
							rset.getString(i), rsmd.getColumnClassName(i));
				}
				listDM.add(dynamicModel);
			}
		} catch (SQLException e) {
			sLog.log(Level.SEVERE, e.getMessage());
		} finally {
			DB.close(rset, pstmt);
		}
		return listDM;
	}

	/**
	 * Metodo que regresa una lista de M_Invoice_ID la cual se utiliza para el
	 * desplazamiento entre registros
	 *
	 * @return List<Integer> contiene M_Invoice_ID
	 * */
	public static List<Integer> getMInvoiceIDS() {
		List<Integer> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		final StringBuilder sql = new StringBuilder(" SELECT ")
				.append(" ci.c_invoice_id ")
				.append(" FROM c_invoice ci  ")
				.append(" INNER JOIN AD_Client ac on ci.ad_client_id = ac.ad_client_id ")
				.append(" INNER JOIN AD_ORG ao on ci.ad_org_id = ao.ad_org_id ")
				.append(" INNER JOIN C_Doctype dtt on ci.c_doctypetarget_id = dtt.c_doctype_id ")
				.append(" INNER JOIN c_bpartner cb on ci.c_bpartner_id = cb.c_bpartner_id ")
				.append(" INNER JOIN c_bpartner_location cbl on ci.c_bpartner_location_id = cbl.c_bpartner_location_id ")
				.append(" LEFT JOIN ad_user adu on ci.ad_user_id = adu.ad_user_id ")
				.append(" LEFT JOIN m_pricelist mpl on ci.m_pricelist_id = mpl.m_pricelist_id ")
				.append(" WHERE ci.IsSOTRX='N' AND (ci.C_CashBook_ID IS NULL OR ci.DocStatus = 'CO') AND ci.Processed='N' AND ci.IsActive='Y' ORDER BY ci.c_invoice_id desc ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rset = pstmt.executeQuery();
			list = new ArrayList<Integer>();
			while (rset.next()) {
				list.add(rset.getInt(1));
			}
		} catch (SQLException e) {
			sLog.log(Level.SEVERE, "getMInvoiceIDS", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return list;
	}

	/**
	 * Overwrite Client/Org if required
	 *
	 * @param AD_Client_ID
	 *            client
	 * @param AD_Org_ID
	 *            org
	 */
	public void setClientOrg(final int AD_Client_ID, final int AD_Org_ID) {
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	} // setClientOrg

	/**
	 * Set Business Partner Defaults & Details
	 *
	 * @param bpar
	 *            business partner
	 */
	public void setBPartner(final MBPartner bpar) {
		if (bpar == null) {
			return;
		}

		setC_BPartner_ID(bpar.getC_BPartner_ID());

		// Tomamos los siguientes datos el BP.
		final String paymentRule = bpar.getPaymentRule();
		// Si el BP no tiene una regla de pago, entonces efectivo.
		setPaymentRule((paymentRule == null ? PAYMENTRULE_Cash : paymentRule));

		// Set Defaults
		int iid = 0;
		if (isSOTrx()) {
			iid = bpar.getC_PaymentTerm_ID();
		} else {
			iid = bpar.getPO_PaymentTerm_ID();
		}
		//
		if (iid == 0) {
			final MPaymentTerm payment = MPaymentTerm.getDefault(getCtx(), get_TrxName());
			if (payment == null) {
				return;
			}
			setC_PaymentTerm_ID(payment.getC_PaymentTerm_ID());
		} else {
			setC_PaymentTerm_ID(iid);
		}

		//
		if (isSOTrx()) {
			iid = bpar.getM_PriceList_ID();
		} else {
			iid = bpar.getPO_PriceList_ID();
		}
		if (iid != 0) {
			setM_PriceList_ID(iid);
		}
		//
		final String ssPayRule = bpar.getPaymentRule();
		if (ssPayRule == null) {
			setPaymentRule(PAYMENTRULE_Cash);
		} else {
			setPaymentRule(ssPayRule);
		}
		// Set Locations
		setLocations(bpar);
	}

	public void setLocations(final int cBPartner){
		setLocations(new MBPartner(getCtx(), cBPartner, get_TrxName()));
	}

	/**
	 * Set Locations
	 */
	private void setLocations(final MBPartner bpar) {
		// Set Locations
		final MBPartnerLocation[] locs = bpar.getLocations(false);
		if (locs != null) {
			for (int i = 0; i < locs.length; i++) {
				if ((locs[i].isBillTo() && isSOTrx())
						|| (locs[i].isPayFrom() && !isSOTrx())) {
					setC_BPartner_Location_ID(locs[i]
							.getC_BPartner_Location_ID());
				}
			}
			// set to first
			if (getC_BPartner_Location_ID() == 0 && locs.length > 0) {
				setC_BPartner_Location_ID(locs[0].getC_BPartner_Location_ID());
			}
		}
		if (getC_BPartner_Location_ID() == 0) {
			log.log(Level.SEVERE, "Has no To Address: " + bpar);
		}

		setContact(bpar);
	}

	/**
	 * Set Contacts
	 */
	private void setContact(final MBPartner bpart) {
		// Set Contact
		final MUser[] contacts = bpart.getContacts(false);
		if (contacts != null && contacts.length > 0) { // get first User
			setAD_User_ID(contacts[0].getAD_User_ID());
		}
	} // setBPartner

	/**
	 * Set Order References
	 *
	 * @param order
	 *            order
	 */
	public void setOrder(final MOrder order) {
		if (order == null) {
			return;
		}

		setC_Order_ID(order.getC_Order_ID());
		setIsSOTrx(order.isSOTrx());
		setIsDiscountPrinted(order.isDiscountPrinted());
		setIsSelfService(order.isSelfService());
		setSendEMail(order.isSendEMail());
		//
		setM_PriceList_ID(order.getM_PriceList_ID());
		setIsTaxIncluded(order.isTaxIncluded());
		setC_Currency_ID(order.getC_Currency_ID());
		setC_ConversionType_ID(order.getC_ConversionType_ID());
		//
		setPaymentRule(order.getPaymentRule());
		setC_PaymentTerm_ID(order.getC_PaymentTerm_ID());
		setPOReference(order.getPOReference());
		setDescription(order.getDescription());
		setDateOrdered(order.getDateOrdered());
		//
		setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
		setC_Project_ID(order.getC_Project_ID());
		setC_Campaign_ID(order.getC_Campaign_ID());
		setC_Activity_ID(order.getC_Activity_ID());
		setUser1_ID(order.getUser1_ID());
		setUser2_ID(order.getUser2_ID());
	} // setOrder

	/**
	 * Set Shipment References
	 *
	 * @param ship
	 *            shipment
	 */
	public void setShipment(final MInOut ship) {
		if (ship == null) {
			return;
		}

		setIsSOTrx(ship.isSOTrx());
		//
		final MBPartner bpart = new MBPartner(getCtx(),
				ship.getC_BPartner_ID(), null);
		setBPartner(bpart);
		//
		setSendEMail(ship.isSendEMail());
		//
		setPOReference(ship.getPOReference());
		setDescription(ship.getDescription());
		setDateOrdered(ship.getDateOrdered());
		//
		setAD_OrgTrx_ID(ship.getAD_OrgTrx_ID());
		setC_Project_ID(ship.getC_Project_ID());
		setC_Campaign_ID(ship.getC_Campaign_ID());
		setC_Activity_ID(ship.getC_Activity_ID());
		setUser1_ID(ship.getUser1_ID());
		setUser2_ID(ship.getUser2_ID());
		//
		if (ship.getC_Order_ID() != 0) {
			setC_Order_ID(ship.getC_Order_ID());
			final MOrder order = new MOrder(getCtx(), ship.getC_Order_ID(),
					get_TrxName());
			setIsDiscountPrinted(order.isDiscountPrinted());
			setM_PriceList_ID(order.getM_PriceList_ID());
			setIsTaxIncluded(order.isTaxIncluded());
			setC_Currency_ID(order.getC_Currency_ID());
			setC_ConversionType_ID(order.getC_ConversionType_ID());
			setPaymentRule(order.getPaymentRule());
			setC_PaymentTerm_ID(order.getC_PaymentTerm_ID());
			//
			final MDocType dtype = MDocType.get(getCtx(),
					order.getC_DocType_ID());
			if (dtype.getC_DocTypeInvoice_ID() != 0) {
				setC_DocTypeTarget_ID(dtype.getC_DocTypeInvoice_ID());
			}
			// Overwrite Invoice Address
			setC_BPartner_Location_ID(order.getBill_Location_ID());
		}
	} // setShipment

	/**
	 * Set Target Document Type
	 * Nota de remision
	 * @param DocBaseType
	 *            doc type MDocType.DOCBASETYPE_
	 */
	public void setC_DocTypeTarget_ID(final String DocBaseType) {
		setC_DocTypeTarget_ID(DocBaseType, null);
	}

	/**
	 * Set Target Document Type
	 * @param docBaseType
	 * @param docSubtypeSO
	 */
	public void setC_DocTypeTarget_ID(final String docBaseType, final String docSubtypeSO) {
		final StringBuilder sql = new StringBuilder(" SELECT C_DocType_ID FROM C_DocType ")
		.append(" WHERE IsActive='Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ","C_DocType"))
		.append(" AND DocBaseType = ?")
		.append(docSubtypeSO==null?" AND DocSubtypeSO IS NULL ":" AND DocSubtypeSO = ? ")
		.append(" ORDER BY IsDefault DESC ");

		int cDocTypeID = -1;
		if(docSubtypeSO==null){
			cDocTypeID =DB.getSQLValue(null, sql.toString(), docBaseType);//NOTA DE REMISION
		} else {
			cDocTypeID =DB.getSQLValue(null, sql.toString(), docBaseType, docSubtypeSO);
		}

		if (cDocTypeID < 1) {
			log.log(Level.SEVERE, "Not found for AC_Client_ID="+ getAD_Client_ID() + " - " + docBaseType);

		} else {
			log.fine(docBaseType);
			setC_DocTypeTarget_ID(cDocTypeID);
			setIsSOTrx(MDocType.DOCBASETYPE_ARInvoice.equals(docBaseType) || MDocType.DOCBASETYPE_ARCreditMemo.equals(docBaseType));
		}
	} // setC_DocTypeTarget_ID

	/**
	 * Set Target Document Type. Based on SO flag AP/AP Invoice
	 */
	public void setC_DocTypeTarget_ID() {
		if (getC_DocTypeTarget_ID() > 0) {
			return;
		}
		if (isSOTrx()) {
			setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice);
		} else {
			setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_APInvoice);
		}
	} // setC_DocTypeTarget_ID

	/**
	 * Get Grand Total
	 *
	 * @param credMemoAdj
	 *            adjusted for CM (negative)
	 * @return grand total
	 */
	public BigDecimal getGrandTotal(final boolean credMemoAdj) {
		if (!credMemoAdj) {
			return super.getGrandTotal();
		}
		//
		final BigDecimal amt = getGrandTotal();
		if (isCreditMemo()) {
			return amt.negate();
		}
		return amt;
	} // getGrandTotal

	/**
	 * Get Invoice Lines of Invoice
	 *
	 * @param whereClause
	 *            starting with AND
	 * @return lines
	 */
	public MInvoiceLine[] getLines(final String whereClause,
			final boolean filtrarCargos, final String trxName) {
		// System.out.println(" busco las lineas  ");
		final ArrayList<MInvoiceLine> list = new ArrayList<MInvoiceLine>();
		final StringBuilder sql = new StringBuilder(
				"SELECT * FROM C_InvoiceLine WHERE C_Invoice_ID = ? ");
		// Expert twry: Que no considere los cargos ocasionados por descuento
		// global de convenios y de familia
		if (filtrarCargos) {
			sql.append(" AND C_InvoiceLine_ID NOT IN ( ")
					.append(" 			SELECT il.C_InvoiceLine_ID FROM C_InvoiceLine il WHERE il.IsActive = 'Y' ")
					.append("      		AND il.C_Charge_id > 0 AND il.PriceActual <= 0 AND il.C_Invoice_ID = ? ")
					.append("       ) ");
		}// Expert: Fin
		if (whereClause != null) {
			sql.append(whereClause);
		}

		sql.append(" ORDER BY Line ");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, getC_Invoice_ID());
			if (filtrarCargos) {
				pstmt.setInt(2, getC_Invoice_ID());
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				final MInvoiceLine iline = new MInvoiceLine(getCtx(), rset,
						trxName);
				iline.setInvoice(this);
				// System.out.println("Lineas -> " + il.getLineNetAmt() +
				// " getLineTotalAmt " + il.getLineTotalAmt());
				list.add(iline);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getLines : " + sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		//
		final MInvoiceLine[] lines = new MInvoiceLine[list.size()];
		list.toArray(lines);
		return lines;
	} // getLines

	/**
	 * Get Invoice Lines
	 * Desde Post No quitar
	 * @param requery
	 * @return lines
	 */
	public MInvoiceLine[] getLines(final boolean requery,
			final boolean filtrarCargos) {
		if (mLines == null || mLines.length == 0 || requery) {
			mLines = getLines(null, filtrarCargos, get_TrxName());
		}
		return mLines;
	} // getLines

	/**
	 * Get Invoice Lines
	 *
	 * @param requery
	 * @return lines
	 */
	public MInvoiceLine[] getLines(final boolean requery) {
		if (mLines == null || mLines.length == 0 || requery) {
			mLines = getLines(null, false, get_TrxName());// Expert twry
		}
		return mLines;
	} // getLines

	/**
	 * Get Invoice Lines
	 *
	 * @param requery
	 * @return lines
	 */
	public MInvoiceLine[] getLines(final boolean requery, final String trxName) {
		if (mLines == null || mLines.length == 0 || requery) {
			mLines = getLines(null, false, trxName);
		}
		return mLines;
	} // getLines

	/**
	 * Get Lines of Invoice
	 *
	 * @return lines
	 */
	public MInvoiceLine[] getLines() {
		return getLines(false);
	} // getLines

	/**
	 * Get Invoice Lines
	 *
	 * @param requery
	 * @return lines
	 */
	public MInvoiceLine[] getLines(final String trxName) {
		mLines = getLines(null, false, trxName);
		return mLines;
	} // getLines

	/**
	 * Renumber Lines
	 *
	 * @param step
	 *            start and step
	 */
	public void renumberLines(final int step) {
		int number = step;
		final MInvoiceLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++) {
			final MInvoiceLine line = lines[i];
			line.setLine(number);
			line.save();
			number += step;
		}
		mLines = null;
	} // renumberLines

	/**
	 * Copy Lines From other Invoice.
	 *
	 * @param otherInvoice
	 *            invoice
	 * @param counter
	 *            create counter links
	 * @param setOrder
	 *            set order links
	 * @param copyRef
	 *
	 * @return number of lines copied
	 */
	public int copyLinesFrom(final MInvoice otherInvoice,
			final boolean counter, final boolean setOrder) {

		if (otherInvoice == null) {
			return 0;
		}

		// si es una factura de proveedor
		if (!otherInvoice.isSOTrx() && (isProcessed() || isPosted())) {
			return 0;
		}

		//
		final MInvoiceLine[] fromLines = otherInvoice.getLines(false);
		int count = 0;
		for (int i = 0; i < fromLines.length; i++) {
			final MInvoiceLine line = new MInvoiceLine(getCtx(), 0,
					get_TrxName());
			final MInvoiceLine fromLine = fromLines[i];
			if (counter) { // header
				PO.copyValues(fromLine, line, getAD_Client_ID(), getAD_Org_ID());
			} else {
				PO.copyValues(fromLine, line, fromLine.getAD_Client_ID(),
						fromLine.getAD_Org_ID());
			}
			line.setC_Invoice_ID(getC_Invoice_ID());
			line.setInvoice(this);
			line.set_ValueNoCheck("C_InvoiceLine_ID", I_ZERO); // new
			// Reset
			if (!setOrder) {
				line.setC_OrderLine_ID(0);
			}
			line.setRef_InvoiceLine_ID(0);
			line.setM_InOutLine_ID(0);
			line.setA_Asset_ID(0);
			line.setM_AttributeSetInstance_ID(0);
			line.setS_ResourceAssignment_ID(0);
			// New Tax
			if (getC_BPartner_ID() != otherInvoice.getC_BPartner_ID()) {
				line.setTax(); // recalculate
			}
			//
			if (counter) {
				line.setRef_InvoiceLine_ID(fromLine.getC_InvoiceLine_ID());
				if (fromLine.getC_OrderLine_ID() != 0) {
					final MOrderLine peer = new MOrderLine(getCtx(),
							fromLine.getC_OrderLine_ID(), get_TrxName());
					if (peer.getRef_OrderLine_ID() != 0) {
						line.setC_OrderLine_ID(peer.getRef_OrderLine_ID());
					}
				}
				line.setM_InOutLine_ID(0);
				if (fromLine.getM_InOutLine_ID() != 0) {
					final MInOutLine peer = new MInOutLine(getCtx(),
							fromLine.getM_InOutLine_ID(), get_TrxName());
					if (peer.getRef_InOutLine_ID() != 0) {
						line.setM_InOutLine_ID(peer.getRef_InOutLine_ID());
					}
				}
			}
			//
			line.setProcessed(false);
			if (line.save(get_TrxName())) {
				count++;
			} else {
				throw new MedsysException();
			}
			
			// Cross Link
			if (counter) {
				fromLine.setRef_InvoiceLine_ID(line.getC_InvoiceLine_ID());
				if(!fromLine.save(get_TrxName())){
					throw new MedsysException();
				}
			}
		}
		if (fromLines.length != count) {
			log.log(Level.SEVERE, "Line difference - From=" + fromLines.length
					+ " <> Saved=" + count);
		}
		return count;
	} // copyLinesFrom

	/**
	 * Copy Lines From other Invoice.
	 *
	 * @param otherInvoice
	 *            invoice
	 * @param counter
	 *            create counter links
	 * @param setOrder
	 *            set order links
	 * @param copyRef
	 *
	 * @return number of lines copied
	 */
	public int copyLinesFrom(final MInvoice otherInvoice, final String trxName) {

		if (otherInvoice == null || getC_Invoice_ID() <= 0) {
			return 0;
		}

		//
		final MInvoiceLine[] fromLines = otherInvoice.getLines(null, false,
				trxName);
		int count = 0;
		for (int i = 0; i < fromLines.length; i++) {

			final MInvoiceLine line = new MInvoiceLine(getCtx(), 0, trxName);
			final MInvoiceLine fromLine = fromLines[i];

			PO.copyValues(fromLine, line, getAD_Client_ID(), getAD_Org_ID());
			// Mantener la referencia
			line.setC_Invoice_ID(getC_Invoice_ID());
			// Datos del encabezado
			line.setInvoice(this);
			// Limpiar el id
			line.set_ValueNoCheck("C_InvoiceLine_ID", I_ZERO); // new
			line.setProcessed(false);
			if (line.save(trxName)) {
				count++;
			} else {
				throw new MedsysException();
			}
		}
		if (fromLines.length != count) {
			log.log(Level.SEVERE, "Line difference - From=" + fromLines.length
					+ " <> Saved=" + count);
		}
		return count;
	} // copyLinesFrom
	
	/**
	 * Copy Lines From other Invoice.
	 *
	 * @param otherInvoice
	 *            invoice
	 * @param counter
	 *            create counter links
	 * @param setOrder
	 *            set order links
	 * @param copyRef
	 *
	 * @return number of lines copied
	 */
	public List<MInvoiceLine> copyLines(final MInvoice otherInvoice, final String trxName) {
		List<MInvoiceLine> lstLineas = new ArrayList<MInvoiceLine>();;

		if (otherInvoice == null)  {
			return lstLineas;
		}

		//
		final MInvoiceLine[] fromLines = otherInvoice.getLines(null, false,
				trxName);
		int count = 0;
		for (int i = 0; i < fromLines.length; i++) {

			final MInvoiceLine line = new MInvoiceLine(getCtx(), 0, trxName);
			final MInvoiceLine fromLine = fromLines[i];

			PO.copyValues(fromLine, line, getAD_Client_ID(), getAD_Org_ID());
			lstLineas.add(line);
			count++;
			
		}
		if (fromLines.length != count) {
			log.log(Level.SEVERE, "Line difference - From=" + fromLines.length
					+ " <> Saved=" + count);
		}
		return lstLineas;
	} // copyLinesFrom

	/**
	 * Set Reversal
	 *
	 * @param reversal
	 *            reversal
	 */
	private void setReversal(final boolean reversal) {
		mReversal = reversal;
	} // setReversal

	/**
	 * Is Reversal
	 *
	 * @return reversal
	 */
	private boolean isReversal() {
		return mReversal;
	} // isReversal

	/**
	 * Get Taxes
	 *
	 * @param requery
	 *            requery
	 * @return array of taxes
	 */
	public MInvoiceTax[] getTaxes(final boolean requery) {
		if (mTaxes != null && !requery) {
			return mTaxes;
		}
		String sql = "SELECT * FROM C_InvoiceTax WHERE C_Invoice_ID=?";
		final ArrayList<MInvoiceTax> list = new ArrayList<MInvoiceTax>();
		PreparedStatement pstmt = null;
		ResultSet rsset = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_Invoice_ID());
			rsset = pstmt.executeQuery();
			while (rsset.next()) {
				list.add(new MInvoiceTax(getCtx(), rsset, get_TrxName()));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getTaxes", e);
		} finally {
			DB.close(rsset, pstmt);
		}

		mTaxes = new MInvoiceTax[list.size()];
		list.toArray(mTaxes);
		return mTaxes;
	} // getTaxes

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
	 * Is it a Credit Memo?
	 *
	 * @return true if CM
	 */
	public boolean isCreditMemo() {
		final MDocType dtype = MDocType.get(getCtx(),
				getC_DocType_ID() == 0 ? getC_DocTypeTarget_ID()
						: getC_DocType_ID());
		return MDocType.DOCBASETYPE_APCreditMemo.equals(dtype.getDocBaseType())
				|| MDocType.DOCBASETYPE_ARCreditMemo.equals(dtype
						.getDocBaseType());
	} // isCreditMemo

	private MDocType dtype = null;
	
	/**
	 * Is it a Customer invoice?
	 *
	 * @return true if ARI
	 */
	public boolean isCustomerInvoice(){
		if(dtype==null) {
			dtype = MDocType.get(getCtx(),getC_DocType_ID() == 0 ? getC_DocTypeTarget_ID(): getC_DocType_ID());
		}
		if(dtype==null)
			return false;
		else 
			return MDocType.DOCBASETYPE_ARInvoice.equals(dtype.getDocBaseType())
					&& MDocType.DOCSUBTYPESO_SalesReceipt.equals(dtype.getDocSubTypeSO());
	}
	
	/**
	 * Set Processed. Propergate to Lines/Taxes
	 *
	 * @param processed
	 *            processed
	 */
	public void setProcessed(final boolean processed) {
		super.setProcessed(processed);
		if (get_ID() == 0) {
			return;
		}
		String set = "SET Processed='" + (processed ? "Y" : "N")
				+ "' WHERE C_Invoice_ID=" + getC_Invoice_ID();
		int noLine = DB.executeUpdate("UPDATE C_InvoiceLine " + set,
				get_TrxName());
		int noTax = DB.executeUpdate("UPDATE C_InvoiceTax " + set,
				get_TrxName());
		mLines = null;
		mTaxes = null;
		log.fine(processed + " - Lines=" + noLine + ", Tax=" + noTax);
	} // setProcessed

	/**
	 * Set Processed. Propergate to Lines/Taxes
	 *
	 * @param processed
	 *            processed
	 */
	public void setProcessed(final boolean processed, final String trxName) {
		super.setProcessed(processed);
		if (get_ID() == 0) {
			return;
		}
		String set = "SET Processed='" + (processed ? "Y" : "N")
				+ "' WHERE C_Invoice_ID=" + getC_Invoice_ID();
		int noLine = DB.executeUpdate("UPDATE C_InvoiceLine " + set, trxName);
		int noTax = DB.executeUpdate("UPDATE C_InvoiceTax " + set, trxName);
		mLines = null;
		mTaxes = null;
		log.fine(processed + " - Lines=" + noLine + ", Tax=" + noTax);
	} // setProcessed

	/**
	 * Validate Invoice Pay Schedule
	 *
	 * @return pay schedule is valid
	 */
	public boolean validatePaySchedule(final String trxName) {
		final MInvoicePaySchedule[] schedule = MInvoicePaySchedule
				.getInvoicePaySchedule(getCtx(), getC_Invoice_ID(), 0, trxName);
		log.fine("#" + schedule.length);
		if (schedule.length == 0) {
			setIsPayScheduleValid(false);
			return false;
		}
		// Add up due amounts
		BigDecimal total = Env.ZERO;
		for (int i = 0; i < schedule.length; i++) {
			schedule[i].setParent(this);
			final BigDecimal due = schedule[i].getDueAmt();
			if (due != null) {
				total = total.add(due);
			}
		}

		final boolean valid = getGrandTotal().compareTo(total) == 0;
		setIsPayScheduleValid(valid);

		// Update Schedule Lines
		for (int i = 0; i < schedule.length; i++) {
			if (schedule[i].isValid() != valid) {
				schedule[i].setIsValid(valid);
				if (schedule[i].save(trxName)){
					log.fine(schedule[i].toString());
				} else {
					log.severe("Imposible PaySchedule doc:" + getDocumentNo());
				}
			}
		}
		return valid;
	}

	// public boolean validatePaySchedule() {
	// final MInvoicePaySchedule[] schedule = MInvoicePaySchedule
	// .getInvoicePaySchedule(getCtx(), getC_Invoice_ID(), 0,
	// get_TrxName());
	// log.fine("#" + schedule.length);
	// if (schedule.length == 0) {
	// setIsPayScheduleValid(false);
	// return false;
	// }
	// // Add up due amounts
	// BigDecimal total = Env.ZERO;
	// for (int i = 0; i < schedule.length; i++) {
	// schedule[i].setParent(this);
	// final BigDecimal due = schedule[i].getDueAmt();
	// if (due != null) {
	// total = total.add(due);
	// }
	// }
	//
	// final boolean valid = getGrandTotal().compareTo(total) == 0;
	// setIsPayScheduleValid(valid);
	//
	// // Update Schedule Lines
	// for (int i = 0; i < schedule.length; i++) {
	// if (schedule[i].isValid() != valid) {
	// schedule[i].setIsValid(valid);
	// schedule[i].save(get_TrxName());
	// }
	// }
	// return valid;
	// } // validatePaySchedule

	/**************************************************************************
	 * Before Save
	 *
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave(final boolean newRecord) {
		log.fine("");
		// No Partner Info - set Template
		if (getC_BPartner_ID() == 0) {
			MBPartner template = MBPartner.getTemplate(getCtx(),
					getAD_Client_ID());
			if (template != null) {
				template.set_TrxName(get_TrxName());
				setBPartner(template);
			}
		}
		if (getC_BPartner_Location_ID() == 0) {
			setBPartner(new MBPartner(getCtx(), getC_BPartner_ID(),
					get_TrxName()));
		}

		// Currency
		if (getC_Currency_ID() == 0) {
			setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
		}

		// Sales Rep
		if (getSalesRep_ID() == 0) {
			int iiSRep = Env.getContextAsInt(getCtx(), "#SalesRep_ID");
			if (iiSRep != 0) {
				setSalesRep_ID(iiSRep);
			}
		}

		// Document Type
		if (getC_DocType_ID() == 0) {
			setC_DocType_ID(0); // make sure it's set to 0
		}
		if (getC_DocTypeTarget_ID() == 0) {
			setC_DocTypeTarget_ID(isSOTrx() ? MDocType.DOCBASETYPE_ARInvoice
					: MDocType.DOCBASETYPE_APInvoice);
		}

		// Payment Term
		if (getC_PaymentTerm_ID() == 0) {
			int iiPayTerm = Env.getContextAsInt(getCtx(), "#C_PaymentTerm_ID");
			if (iiPayTerm > 0) {
				setC_PaymentTerm_ID(iiPayTerm);
			} else {
				String sql = "SELECT C_PaymentTerm_ID FROM C_PaymentTerm WHERE AD_Client_ID=? AND IsDefault='Y'";
				iiPayTerm = DB.getSQLValue(null, sql, getAD_Client_ID());
				if (iiPayTerm != 0) {
					setC_PaymentTerm_ID(iiPayTerm);
				}
			}
		}

		// expert : gisela lee : asignar por default la org trx logueada (est
		// serv)
		if (getAD_OrgTrx_ID() == 0) {
			setAD_OrgTrx_ID(Env.getContextAsInt(getCtx(), "#AD_OrgTrx_ID"));
		}

		// Nombre de la aseguradora/paciente
		if (getC_BPartner_ID() > 0) {
			setPartner(getCliente().getName());
		}
		//gderreza:Asignar la fecha contable igual a la fecha del documento, solicitado por GCB
		if (getDateInvoiced() != null) {
			setDateAcct(getDateInvoiced());
		}
		
		if (!isSOTrx() && StringUtils.isNotBlank(getDocumentNo())) {
			String patron = "[A-Za-z0-9]+";
			Pattern p = Pattern.compile(patron);
			Matcher matcher = p.matcher(getDocumentNo()); 
			
			if(!matcher.matches()){
				log.saveError(" ", Utilerias.getAppMsg(getCtx(), "msj.campoAlfaNumerico",Utilerias.getAppMsg(getCtx(), "msj.documentNo")));
				return false;
			}
		}
		
		// Las facturas globales SIEMPRE deben queda como pagadas
		// ya que las notas de remision SIEMPRE estan pagadas para ser integradas a la factura global
		if (isMultiple()){
			setIsPaid(true);
		}
		

		if ((X_C_Invoice.DOCSTATUS_Drafted.equals(getDocStatus()) 
				&& getC_Currency_ID()!=Env.getC_Currency_ID(getCtx()))
				&& (getRate().compareTo(Env.ZERO)<=0 || is_ValueChanged(COLUMNNAME_DateInvoiced) || is_ValueChanged(COLUMNNAME_C_Currency_ID)) ){
			
			setRate (MConversionRate.getRate(getC_Currency_ID()
					, Env.getC_Currency_ID(getCtx())
					, getDateInvoiced()
					, getC_ConversionType_ID()
					, getAD_Client_ID()
					, getAD_Org_ID()));
			if(getRate().compareTo(Env.ZERO)<=0){
				log.saveError("Error", Utilerias.getLabel("error.caja.conversionRate", getCurrencyISO()) );
				return false;
			}
		}
		return true;
	} // beforeSave

	/**
	 * Before Delete
	 *
	 * @return true if it can be deleted
	 */
	protected boolean beforeDelete() {
		if (getC_Order_ID() != 0 && !(DOCSTATUS_Drafted.equalsIgnoreCase(getDocStatus()))) {
			log.saveError("Error", Msg.getMsg(getCtx(), "CannotDelete"));
			return false;
		}
		return true;
	} // beforeDelete

	/**
	 * String Representation
	 *
	 * @return info
	 */
	public String toString() {
		final StringBuffer sbuff = new StringBuffer("MInvoice[")
				.append(get_ID()).append("-").append(getDocumentNo())
				.append(",GrandTotal=").append(getGrandTotal());
		if (mLines != null) {
			sbuff.append(" (#").append(mLines.length).append(")");
		}
		sbuff.append("]");
		return sbuff.toString();
	} // toString

	/**
	 * Get Document Info
	 *
	 * @return document info (untranslated)
	 */
	public String getDocumentInfo() {
		final MDocType dtype = MDocType.get(getCtx(), getC_DocType_ID());
		return dtype.getName() + " " + getDocumentNo();
	} // getDocumentInfo

	/**
	 * After Save
	 *
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return success
	 */
	protected boolean afterSave(final boolean newRecord, final boolean success) {
		if (success && newRecord) {
			MEXMEPolicyInfo policyInfo = new MEXMEPolicyInfo(getCtx(), 0, null);
			policyInfo.setAD_Table_ID(Table_ID);
			policyInfo.setRecord_ID(getC_Invoice_ID());
			policyInfo.setDateTrx(getDateAcct());
			I_C_DocType docType = null;
			
			// Si no tiene tipo de documento, se saca del target
			if (getC_DocType_ID() > 0) {
				docType = getC_DocType();
			} else {
				docType = new MDocType(getCtx(), getC_DocTypeTarget_ID(), null);
			}

			I_GL_Category glCategory = docType.getGL_Category();
	
			policyInfo.setAuxType(MEXMEPolicyInfo.AUXTYPE_Invoice);
			policyInfo.setGL_Category_ID(glCategory.getGL_Category_ID());
			policyInfo.setPolicyType(glCategory.getPolicyType());

			if (!policyInfo.save(get_TrxName())) {
				return false;
			}
		}
		
		if (!success || newRecord) {
			return success;
		}

		if (is_ValueChanged("AD_Org_ID")) {
			String sql = "UPDATE C_InvoiceLine ol"
					+ " SET AD_Org_ID ="
					+ "(SELECT AD_Org_ID"
					+ " FROM C_Invoice o WHERE ol.C_Invoice_ID=o.C_Invoice_ID) "
					+ "WHERE C_Invoice_ID=" + getC_Order_ID();
			int num = DB.executeUpdate(sql, get_TrxName());
			log.fine("Lines -> #" + num);
		}

		//
		if(X_C_Invoice.DOCSTATUS_Drafted.equals(getDocStatus())
				|| X_C_Invoice.DOCSTATUS_InProgress.equals(getDocStatus())){
			setPosted(false);
			setProcessed(false, get_TrxName());
		}
		return true;
	} // afterSave

	/**
	 * Set Price List (and Currency) when valid
	 *
	 * @param M_PriceList_ID
	 *            price list
	 */
	public void setM_PriceList_ID(final int M_PriceList_ID) {
		String sql = "SELECT M_PriceList_ID, C_Currency_ID "
				+ "FROM M_PriceList WHERE M_PriceList_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_PriceList_ID);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				super.setM_PriceList_ID(rset.getInt(1));
				setC_Currency_ID(rset.getInt(2));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "setM_PriceList_ID", e);
		} finally {
			DB.close(rset, pstmt);
		}
	} // setM_PriceList_ID

	/**
	 * Get Allocated Amt in Invoice Currency
	 *
	 * @return pos/neg amount or null
	 */
	public BigDecimal getAllocatedAmt() {
//		BigDecimal retValue = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(currencyConvert(al.Amount+al.DiscountAmt+al.WriteOffAmt,");
		sql.append("ah.C_Currency_ID, i.C_Currency_ID,ah.DateTrx,i.C_ConversionType_ID, al.AD_Client_ID,al.AD_Org_ID)) ");
		sql.append("FROM C_AllocationLine al");
		sql.append(" INNER JOIN C_AllocationHdr ah ON (al.C_AllocationHdr_ID=ah.C_AllocationHdr_ID)");
		sql.append(" INNER JOIN C_Invoice i ON (al.C_Invoice_ID=i.C_Invoice_ID) ");
		sql.append("WHERE al.C_Invoice_ID=?");
		sql.append(" AND ah.IsActive='Y' AND al.IsActive='Y'");
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		try {
//			pstmt = DB.prepareStatement(sql, get_TrxName());
//			pstmt.setInt(1, getC_Invoice_ID());
//			rset = pstmt.executeQuery();
//			if (rset.next()) {
//				retValue = rset.getBigDecimal(1);
			BigDecimal retValue = DB.getSQLValueBD(get_TrxName(), sql.toString(), getC_Invoice_ID());
				// pstmt.close();
				// pstmt = null;
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, sql, e);
//		} finally {
//			DB.close(rset, pstmt);
//		}
		// log.fine("getAllocatedAmt - " + retValue);
		// ? ROUND(NVL(v_AllocatedAmt,0), 2);
		return retValue;
	} // getAllocatedAmt

	/**
	 * Test Allocation (and set paid flag)
	 *
	 * @return true if updated
	 */
	public boolean testAllocation() {
		BigDecimal alloc = getAllocatedImp(); // absolute
		if (alloc == null) {
			alloc = Env.ZERO;
		}
		BigDecimal total = getGrandTotal();// 570 ()
		if (!isSOTrx()) {
			total = total.negate();
		}
		if (isCreditMemo()) {
			total = total.negate();
		}
		final boolean test = total.compareTo(alloc) == 0;
		final boolean change = test != isPaid();
		// En caso de ser nota de credito Siempre es PAID='Y'
		if (isCreditMemo()) {
			searchIsPaid(true);
		} else if (change) { //si cambio el valor
			searchIsPaid(test);
		}
		log.fine("Paid=" + test + " (" + alloc + "=" + total + ")");
		return change;
	} // testAllocation

	/** Solo para facturas de clientes se deben considerar los pagos y notas de credito*/
	private void searchIsPaid(final boolean isPaid) {
		if(getC_Invoice_ID()>0 && isCustomerInvoice()){
			// Buscar las referencias en las asignaciones, la finalidad es el resultado de la base de datos
			final BigDecimal saldo = this.getGrandTotal().subtract(calculaPago(get_TrxName()).add(getCredits(false, get_TrxName())));
			// Solo si es factura cliente
			super.setIsPaid(saldo.compareTo(Env.ZERO)<=0);
		} else {
			super.setIsPaid(isPaid);
		}
	}
	
	/**
	 * Set Paid Flag for invoices
	 *
	 * @param ctx
	 *            context
	 * @param C_BPartner_ID
	 *            if 0 all
	 * @param trxName
	 *            transaction
	 */
	public static void setIsPaid(final Properties ctx, final int C_BPartner_ID,
			final String trxName) {
		int counter = 0;
		final StringBuilder sql = new StringBuilder("SELECT * FROM C_Invoice ")
				.append("WHERE IsPaid='N' AND DocStatus IN ('CO','CL')");
		if (C_BPartner_ID > 1) {
			sql.append(" AND C_BPartner_ID=?");
		} else {
			sql.append(" AND AD_Client_ID=").append(Env.getAD_Client_ID(ctx));
		}

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (C_BPartner_ID > 1) {
				pstmt.setInt(1, C_BPartner_ID);
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				final MInvoice invoice = new MInvoice(ctx, rset, trxName);
				if (invoice.testAllocation() && invoice.save()) {
					counter++;
				}
			}
			// rs.close ();
			// pstmt.close ();
			// pstmt = null;
		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}
		sLog.config("#" + counter);
		/**/
	} // setIsPaid

	/**
	 * Get Open Amount. Used by web interface
	 *
	 * @return Open Amt
	 */
	public BigDecimal getOpenAmt() {
		return getOpenAmt(true, null);
	} // getOpenAmt

	/**
	 * Get Open Amount
	 *
	 * @param credMemoAdj
	 *            adjusted for CM (negative)
	 * @param paymentDate
	 *            ignored Payment Date
	 * @return Open Amt
	 */
	public BigDecimal getOpenAmt(final boolean credMemoAdj,
			final Timestamp paymentDate) {
		if (isPaid()) {
			return Env.ZERO;
		}
		//
		if (mOpenAmt == null) {
			mOpenAmt = getGrandTotal();
			// if (paymentDate != null)
			// {
			// // Payment Discount
			// // Payment Schedule
			// }
			BigDecimal allocated = getAllocatedAmt();
			if (allocated != null) {
				allocated = allocated.abs(); // is absolute
				mOpenAmt = mOpenAmt.subtract(allocated);
			}
		}
		//
		if (!credMemoAdj) {
			return mOpenAmt;
		}
		if (isCreditMemo()) {
			return mOpenAmt.negate();
		}
		return mOpenAmt;
	} // getOpenAmt

	/**
	 * Get Document Status
	 *
	 * @return Document Status Clear Text
	 */
	public String getDocStatusName() {
		return MRefList.getListName(getCtx(), 131, getDocStatus());
	} // getDocStatusName

	/**************************************************************************
	 * Create PDF
	 *
	 * @return File or null
	 */
	public File createPDF() {
		try {
			final File temp = File.createTempFile(get_TableName() + get_ID()
					+ "_", ".pdf");
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
	public File createPDF(final File file) {
		final ReportEngine reng = ReportEngine.get(getCtx(),
				ReportEngine.INVOICE, getC_Invoice_ID());
		if (reng == null) {
			return null;
		}
		return reng.getPDF(file);
	} // createPDF

	/**
	 * Get PDF File Name
	 *
	 * @param documentDir
	 *            directory
	 * @return file name
	 */
	public String getPDFFileName(final String documentDir) {
		return getPDFFileName(documentDir, getC_Invoice_ID());
	} // getPDFFileName

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

	/**************************************************************************
	 * Process document
	 *
	 * @param processAction
	 *            document action
	 * @return true if performed
	 */
	public boolean processIt(final String processAction) {
		mProcessMsg = null;
		final DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());
	} // process

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

	public String prepareIt() {
		if(isSOTrx()){
			return prepareItSO();

		} else {
			return prepareItPO();
		}

	}
	/**
	 * Prepare Document
	 *
	 * @return new status (In Progress or Invalid)
	 */
	public String prepareItSO() {
		log.info(toString());
		mProcessMsg = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_BEFORE_PREPARE);
		if (mProcessMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		final MDocType dtype = MDocType.get(getCtx(), getC_DocTypeTarget_ID());

		// Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dtype.getDocBaseType(),
				getAD_Org_ID())) {
			mProcessMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		// Lines
		final MInvoiceLine[] lines;
		if(X_C_DocType.DOCBASETYPE_ARInvoice.equals(dtype.getDocBaseType())){
			// Factura
			lines = getLines(true, get_TrxName());
			if (lines.length == 0) {
				mProcessMsg = "@NoLines@";
				return DocAction.STATUS_Invalid;
			}
		} else {
			// Nota de remision
			lines = getLines(get_TrxName());
			if (lines.length == 0) {
				mProcessMsg = "@NoLines@";
				return DocAction.STATUS_Invalid;
			}
		}

		// Mandatory Attributes
		BigDecimal totalTax = Env.ZERO;
		BigDecimal totalLines = Env.ZERO;
		BigDecimal desctoConv = Env.ZERO;
		for (int i = 0; i < lines.length; i++) {
			desctoConv = desctoConv.add(lines[i].getDiscount().multiply(lines[i].getQtyInvoiced()));
			totalTax = totalTax.add(lines[i].getTaxAmt());
			totalLines = totalLines.add(lines[i].getLineNetAmt());
		}

		// Total de venta (solo cargos sin CCCmD ni desc.)
		getTotalVnta(lines);
		// Descuento por convenio
		setGlobalDiscount(desctoConv);
		// Total de lineas sin impuesto
		setTotalLines(totalLines);
		// Impuestos
		setTaxAmt(totalTax);
		// Total de lineas con impuesto
		setGrandTotal(totalLines.add(totalTax));

		// Redondeo
		setScaleTotals();
		
		// Convert/Check DocType
		if (getC_DocType_ID() != getC_DocTypeTarget_ID()) {
			setC_DocType_ID(getC_DocTypeTarget_ID());
		}
		if (getC_DocType_ID() == 0) {
			mProcessMsg = "No Document Type";
			return DocAction.STATUS_Invalid;
		}

		explodeBOM();
		if (!calculateTaxTotal()) // setTotals
		{
			mProcessMsg = "Error calculating Tax";
			return DocAction.STATUS_Invalid;
		}

		createPaySchedule();

		// Credit Status
//		if (isSOTrx() && !isReversal()) {
//			final MBPartner bpart = new MBPartner(getCtx(), getC_BPartner_ID(),
//					null);
//			if (MBPartner.SOCREDITSTATUS_CreditStop.equals(bpart
//					.getSOCreditStatus())) {
//				mProcessMsg = "@BPartnerCreditStop@ - @TotalOpenBalance@="
//						+ bpart.getTotalOpenBalance() + ", @SO_CreditLimit@="
//						+ bpart.getSO_CreditLimit();
//				return DocAction.STATUS_Invalid;
//			}
//		}

		// Landed Costs
		if(isSOTrx() && !isBackoffice()){
			for (int i = 0; i < lines.length; i++) {
				final MInvoiceLine line = lines[i];
				final String error = line.allocateLandedCosts();
				if (error != null && error.length() > 0) {
					mProcessMsg = error;
					return DocAction.STATUS_Invalid;
				}
			}
		}

		// Add up Amounts
		mJustPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction())) {
			setDocAction(DOCACTION_Complete);
		}
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/** Actualiza los estatus a completos el documento */
	public void updateStatusComplete(){
		setDocStatus(DOCSTATUS_Completed);
		setDocAction(DOCACTION_Close);
	}

	/** Actualiza los estatus del documento para no ser considerado */
	public void updateStatusDead(){
		setDocStatus(DOCSTATUS_Unknown);
		setDocAction(DOCACTION_None);
	}
	/**
	 * Update Tax & Header
	 */
	public boolean updateHeader() {
		int no = 0;
		// Update Invoice Header//
		String sql = "UPDATE C_Invoice i"
				+ " SET TotalLines = "
				+ " (SELECT COALESCE(SUM(LineNetAmt),0) FROM C_InvoiceLine il WHERE i.C_Invoice_ID=il.C_Invoice_ID) "
				+ " WHERE C_Invoice_ID = " + getC_Invoice_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1) {
			log.warning("(1) #" + no);
		}

		sql = "UPDATE C_Invoice i "
				+ " SET TaxAmt = (SELECT SUM(  COALESCE(TaxAmt,0) ) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID) "
				+ " WHERE C_Invoice_ID = ? ";

		no = DB.executeUpdate(sql, getC_Invoice_ID(), get_TrxName());
		if (no != 1) {
			log.warning("(2) #" + no);
		}
		
		if (isTaxIncluded()) {
			sql = "UPDATE C_Invoice i " + " SET GrandTotal=TotalLines "
					+ "WHERE C_Invoice_ID=" + getC_Invoice_ID();
		} else {

			sql = "UPDATE C_Invoice i "
					+ " SET GrandTotal=TotalLines+"
					+ "(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID) "
					+ "WHERE C_Invoice_ID=" + getC_Invoice_ID();
		}
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1) {
			log.warning("(3) #" + no);
		}
		
		return no == 1;
	} // updateHeaderTax

	/**
	 * Para ajustar las facturas de venta
	 *
	 * @return
	 */
	public BigDecimal getBalancePrePost() {
		BigDecimal retValue = Env.ZERO;
		// Total
		retValue = retValue.add(getGrandTotal());
		// - Header Charge
		retValue = retValue.subtract(getChargeAmt());
		// - Tax
		MInvoiceTax[] m_taxes = getTaxes(true);
		for (int i = 0; i < m_taxes.length; i++) {
			retValue = retValue.subtract(m_taxes[i].getTaxAmt().setScale(2,
					BigDecimal.ROUND_HALF_UP));
		}
		// - Lines
		MInvoiceLine[] p_lines = getLines(true);
		for (int i = 0; i < p_lines.length; i++) {
			retValue = retValue.subtract(p_lines[i].getLineNetAmt());
		}
		//
		log.fine(toString() + " Balance=" + retValue);

		// if(retValue.compareTo(Env.ZERO)!=0){
		// if(retValue.abs().compareTo(Env.ONE)< 0){
		// for (int i = 0; i < m_taxes.length; i++) {
		// if(m_taxes[i].getTaxAmt().compareTo(Env.ZERO)>0){
		// BigDecimal ajuste = m_taxes[i].getTaxAmt();
		// ajuste = ajuste.add(retValue);
		// // que no se haga negativo o cero
		// if(ajuste.compareTo(Env.ZERO)>0){
		// m_taxes[i].setTaxAmt(ajuste);
		// m_taxes[i].save(get_TrxName());
		// break;
		// }
		// }
		// }
		// }
		// }
		// log.fine(toString() + " Balance=" + retValue);
		return retValue;
	} // getBalance

	/**
	 * Explode non stocked BOM.
	 */
	private void explodeBOM() {
		final String where = "AND IsActive='Y' AND EXISTS "
				+ "(SELECT * FROM M_Product p WHERE C_InvoiceLine.M_Product_ID=p.M_Product_ID"
				+ " AND	p.IsBOM='Y' AND p.IsVerified='Y' AND p.IsStocked='N')";
		//
		String sql = "SELECT COUNT(*) FROM C_InvoiceLine "
				+ "WHERE C_Invoice_ID=? " + where;
		int count = DB.getSQLValue(get_TrxName(), sql, getC_Invoice_ID());
		while (count != 0) {
			renumberLines(100);

			// Order Lines with non-stocked BOMs
			final MInvoiceLine[] lines = getLines(where, false, get_TrxName());
			for (int i = 0; i < lines.length; i++) {
				final MInvoiceLine line = lines[i];
				final MProduct product = MProduct.get(getCtx(),
						line.getM_Product_ID());
				log.fine(product.getName());
				// New Lines
				int lineNo = line.getLine();
				final MProductBOM[] boms = MProductBOM.getBOMLines(product);
				for (int j = 0; j < boms.length; j++) {
					final MProductBOM bom = boms[j];
					final MInvoiceLine newLine = new MInvoiceLine(this);
					newLine.setLine(++lineNo);
					newLine.setM_Product_ID(bom.getProduct().getM_Product_ID(),
							bom.getProduct().getC_UOM_ID());
					newLine.setQty(line.getQtyInvoiced().multiply(
							bom.getBOMQty())); // Invoiced/Entered
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
				line.setPriceEntered(Env.ZERO);
				line.setPriceActual(Env.ZERO);
				line.setPriceLimit(Env.ZERO);
				line.setPriceList(Env.ZERO);
				line.setLineNetAmt(Env.ZERO);
				//
				StringBuilder description = new StringBuilder(product.getName());
				if (product.getDescription() != null) {
					description.append(" ").append(product.getDescription());
				}
				if (line.getDescription() != null) {
					description.append(" ").append(line.getDescription());
				}
				line.setDescription(description.toString());
				line.save(get_TrxName());
			} // for all lines with BOM

			mLines = null;
			count = DB.getSQLValue(get_TrxName(), sql, getC_Invoice_ID());
			renumberLines(10);
		} // while count != 0
	} // explodeBOM

	/**
	 * Calculate Tax and Total
	 *
	 * @return true if calculated
	 */
	private boolean calculateTaxTotal() {
		log.fine("calculateTaxTotal ...");

		// Delete Taxes
		int no = DB.executeUpdate("DELETE C_InvoiceTax WHERE C_Invoice_ID="
				+ getC_Invoice_ID(), get_TrxName());
		if (no != 1) {
			log.warning("(1) #" + no);
		}

		mTaxes = null;

		// Lines
		// Tasas de impuesto
		final BigDecimal totalTaxs = getTaxs(); //taxs();
		if (totalTaxs == null) {
			return false;
		}
		
//		// Ajuste de redondeo
//		if(totalTaxs.compareTo(getTaxAmt())!=0){
//			final BigDecimal differences = totalTaxs.subtract(getTaxAmt());
//			final String where = "";
//			final List<MInvoiceTax> arrTaxs = MInvoiceTax.getTaxes(getCtx(), where, getC_Invoice_ID(), get_TrxName());
//			for (final MInvoiceTax bean: arrTaxs) {
//				if(bean.getTaxAmt().compareTo(Env.ZERO)>0 && ){
//					bean.setTaxAmt(bean.getTaxAmt().subtract(differences));
//					break;
//				}
//			}
//		}
		return true;
	} // calculateTaxTotal

	/**
	 * Calculo de impuestos con CCCmD y desc
	 * 
	 * @param lstCCCmDDesc
	 * @param totalVta
	 * @param totalVtaImp
	 * @return Suma de todas las lineas por la columna LineNetAmt
	 */
	private BigDecimal getTaxs() {
		ErrorList errorList = new ErrorList();
		BigDecimal totalLines = Env.ZERO;
		
		if(!X_C_Invoice.TRXTYPE_PartialCreditNoteCustomer.equals(getTrxType()) || getTrxType()==null || getTrxType().isEmpty()){
			
			if(isMultiple()){
				totalLines = createLineTax();
			} else {
				totalLines = getAmountTax(errorList, get_TrxName());
			}
		} else {
			totalLines = createLineTax();
		}

		return totalLines;
	}
	
	
//	/**
//	 * Calculo de impuestos con CCCmD y desc
//	 *
//	 * @param lstCCCmDDesc
//	 * @param totalVta
//	 * @param totalVtaImp
//	 * @return Suma de todas las lineas por la columna LineNetAmt
//	 */
//	private BigDecimal taxs() {
//		// final BigDecimal totalVta, final BigDecimal totalVtaAseg){
//		// Lines
//		BigDecimal totalLines = Env.ZERO;
//		final ArrayList<Integer> taxList = new ArrayList<Integer>();
//
//		if(!(X_C_Invoice.TRXTYPE_PartialCreditNoteCustomer.equals(getTrxType()) || X_C_Invoice.TRXTYPE_CreditNotePromptPayment.equals(getTrxType())) || getTrxType()==null || getTrxType().isEmpty()){
//
//			if(isMultiple()){
//				totalLines = createLineTax();
//			} else {
//				// Todas las lineas de la factura a generarse
//				// se iteran para buscar las posibles tasas de impuesto que existen
//				final MInvoiceLine[] lines = getLines(false);
//				for (int i = 0; i < lines.length; i++) {
//
//					// Linea de la factura
//					final MInvoiceLine line = lines[i];
//
//					// Por tasa de impuesto
//					if (!taxList.contains(line.getC_Tax_ID())) {
//						final Integer taxID = calcularTasaPorLineas(line);
//						if (taxID == 0) {
//							return null;
//						}
//						taxList.add(taxID);
//					}
//					totalLines = totalLines.add(line.getLineNetAmt());
//				}
//
//				if (isSOTrx()) {
//					// Podria existir una tasa de impuesto que no este entre las lineas
//					// de la factura
//					// pero que si este en la factura de la aseguradora
//					calcularTasaPorLineasCCCmD();
//				}
//			}
//		} else {
//			totalLines = createLineTax();
//		}
//
//		return totalLines;
//	}

	/**
	 * Crea linea de impuesto por cada linea de la factura
	 * UTILIZAR SOLO CUANDO EN LAS LINEAS NO SE REPITA LA TASA DE IMPUESTO
	 * como es el caso de la factura global y la nota de credito por pronto pago
	 * @return
	 */
	private BigDecimal createLineTax(){
		BigDecimal totalLines = Env.ZERO;
		final MInvoiceLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++) {
			// Linea de la factura
			final MInvoiceLine line = lines[i];
			final MInvoiceTax mInvoiceTax = new MInvoiceTax(getCtx(), 0, get_TrxName());
			mInvoiceTax.setC_Invoice_ID(line.getC_Invoice_ID());
			mInvoiceTax.setTaxAmt(line.getTaxAmt());
			mInvoiceTax.setTaxBaseAmt(line.getLineNetAmt());
			mInvoiceTax.setC_Tax_ID(line.getC_Tax_ID());
			if(mInvoiceTax.save(get_TrxName())){
				totalLines = totalLines.add(line.getLineNetAmt());
			}
		}
		return totalLines;
	}

	/**
	 *
	 * @param line
	 * @return
	 */
	public int calcularTasaPorLineas(final MInvoiceLine line) {

		// Tasa de impuesto anterior sin monto
		final MInvoiceTax iTax = MInvoiceTax.get(line, getPrecision(), false,
				get_TrxName()); // current Tax

		if (iTax == null) {
			return 0;
		}

		iTax.setIsTaxIncluded(isTaxIncluded());

		// Calculo del monto de impuesto
		if (!iTax.calculateTaxFromLines()) {
			return 0;
		}

		//
		if (!iTax.save(get_TrxName())) {
			return 0;
		}
		return iTax.getC_Tax_ID();
	}

	/**
	 * taxs()
	 * Los montos de los impuesto generados por CCCmD
	 * dependen de los cargos de la factura de la aseguradora
	 */
	public void calcularTasaPorLineasCCCmD() {
		// Llenar la lista linesNotMatch con las Lineas cccmd y descuento
		calculateTaxFromLinesCCCmD();

		// Impuestos de particular o aseguradora ?
		boolean particular = !getCliente().isFacturarAseg();
//		if (linesNotMatch!=null && !linesNotMatch.isEmpty() && linesNotMatch.get(0)!=null
//				&& linesNotMatch.get(0).getM_Product_ID()>0) {
//			particular  = linesNotMatch.get(0).getLineNetAmt().compareTo(Env.ZERO) > 0;
//		}
//

		// Lineas de impuestos de la factura actual si es factura de aseguradora
		MInvoiceTax[] mTaxs = null;
//		if(!particular){
//			mTaxs = getTaxes(true);
//		}

		// Iterar las lineas de la factura Descuento
		if (particular){
			for (int i = 0; i < linesNotMatch.size(); i++) {
				// Taxas de descuentos
				if(linesNotMatch.get(i).getC_Charge_ID()>0){
					mTaxs = getTaxes(true);

					if(mTaxs == null){
						break;
					}
					// Iterar las tasas de impuestos de los cargos
					for (int x = 0; x < mTaxs.length; x++) {

						// Impuestos taxBaseAmt, taxAmt
						// Impuesto prorrateado por base grabada
						// Proporcion con respecto al total de venta (suma de
						// solo cargos)
						if (mTaxs[x].getTaxBaseAmt().compareTo(BigDecimal.ZERO) > 0) {

							final BigDecimal ratio = mTaxs[x].getTaxBaseAmt().divide(
									totalVta, 6, BigDecimal.ROUND_HALF_UP);
							// Base proporcional de CCCmDDesc
							final BigDecimal baseGrav = mTaxs[x].getTaxBaseAmt()
									.add(linesNotMatch.get(i).getLineNetAmt().multiply(ratio));// 1
							// Impuesto proporcional de CCCmDDesc
							final BigDecimal impuesto = baseGrav.multiply(mTaxs[x]
									.getTax()
									.getRate()
									.divide(Env.ONEHUNDRED, 6,
											BigDecimal.ROUND_HALF_UP));

							// Proporcional CCCmD
							mTaxs[x].setTaxAmt(impuesto
									.setScale(getPrecision(),
											BigDecimal.ROUND_HALF_UP));//
							mTaxs[x].setTaxBaseAmt(baseGrav
									.setScale(getPrecision(),
											BigDecimal.ROUND_HALF_UP));
						}
						//
						if (!mTaxs[x].save(get_TrxName())) {
							continue;
						}
					}
					break;// solo una linea de descuento
				}
			}
		} else {
			mTaxs = getTaxes(true);
		}

		// Iterar las lineas de la factura CCCmD
		for (int i = 0; i < linesNotMatch.size(); i++) {

			// Linea de la factura
			MInvoiceLine mInvoiceLineCCCmD = linesNotMatch.get(i);

			// PARTICULAR: Si el monto del CCCmD es positivo la factura es de un PARTICULAR
			if (particular ){
				if(mInvoiceLineCCCmD.getM_Product_ID()>0 ){
					imp(mInvoiceLineCCCmD);
				}
			} else {
				imp(mTaxs,mInvoiceLineCCCmD);
			}// Negativo
		}
	}

	/**
	 * Bases gravables de mi factura actual (ES DECIR LA PARTE NEGATIVA)
	 * @param mTaxs
	 * @param mInvoiceLineCCCmD
	 */
	public void imp(final MInvoiceTax[] mTaxs, final MInvoiceLine mInvoiceLineCCCmD){
		if (mTaxs == null){
			return;
		}

		// Iterar las tasas de impuestos de los cargos
		for (int x = 0; x < mTaxs.length; x++) {

			// Tasa de impuesto anterior sin monto
			final MInvoiceTax iTax = MInvoiceTax.get(mInvoiceLineCCCmD,
					mTaxs[x].getC_Tax_ID(), getPrecision(), false,
					get_TrxName()); // current Tax

			if (iTax == null) {
				continue;
			}

			iTax.setIsTaxIncluded(isTaxIncluded());

			// Impuestos taxBaseAmt, taxAmt
			// Impuesto prorrateado por base grabada
			// Proporcion con respecto al total de venta (suma de
			// solo cargos)
			if (mTaxs[x].getTaxBaseAmt().compareTo(BigDecimal.ZERO) > 0) {

				BigDecimal ratio = mTaxs[x].getTaxBaseAmt().divide(
						totalVta, 6, BigDecimal.ROUND_HALF_UP);
				// Base proporcional de CCCmDDesc
				BigDecimal baseGrav = mInvoiceLineCCCmD.getLineNetAmt()
						.multiply(ratio);// 1
				// Impuesto proporcional de CCCmDDesc
				BigDecimal impuesto = baseGrav.multiply(mTaxs[x]
						.getTax()
						.getRate()
						.divide(Env.ONEHUNDRED, 6,
								BigDecimal.ROUND_HALF_UP));

				// Proporcional CCCmD
				iTax.setTaxAmt((iTax.getTaxAmt().add(impuesto))
						.setScale(getPrecision(),
								BigDecimal.ROUND_HALF_UP));//
				iTax.setTaxBaseAmt((iTax.getTaxBaseAmt()
						.add(baseGrav)).setScale(getPrecision(),
								BigDecimal.ROUND_HALF_UP));
			}
			//
			if (!iTax.save(get_TrxName())) {
				continue;
			}
		}
	}

	/**
	 * Impuestos de CCCmD del particular
	 * @param mInvoiceLine
	 */
	private boolean imp(final MInvoiceLine mInvoiceLine){
		List<MInvoiceTax> hMbasesGravAseg = null;

		// con cuenta paciente (FACTURACION DIRECTA Y POR EXTENSIONES)
		if (mInvoiceLine.getEXME_CtaPacDet_ID() > 0) {
			// Las bases gravables pueden venir de la factura de la aseguradora
			hMbasesGravAseg = MInvoiceTax.tasasImpFactura(getCtx(),
					mInvoiceLine.getEXME_CtaPacDet_ID(), get_TrxName());

			// Si no se ha facturado la extension de la aseguradora ...
			if (hMbasesGravAseg == null || hMbasesGravAseg.isEmpty()) {
				// Las bases gravables pueden venir de los cargos de la extension de la aseguradora
				hMbasesGravAseg = tasas(mInvoiceLine);
			}
		}

		// sin cuenta paciente
		// Las bases gravables pueden venir de un objeto, y aplicaria
		// para toda la factura que se esta generando (FACTURACION DIRECTA)
		if (hMbasesGravAseg == null && basesGravAseg != null) {
			hMbasesGravAseg = new ArrayList<MInvoiceTax>();

			// mapa con los impuestos
			Iterator<Entry<Integer, BigDecimal>> itEntriesTotal = basesGravAseg
					.entrySet().iterator();
			while (itEntriesTotal.hasNext()) {
				Entry<Integer, BigDecimal> element = itEntriesTotal
						.next();
				MInvoiceTax imp = new MInvoiceTax(getCtx(), 0,
						get_TrxName());
				imp.setC_Tax_ID(element.getKey());
				imp.setTaxBaseAmt(element.getValue());
				hMbasesGravAseg.add(imp);
			}
		}


		if(mInvoiceLine.getC_Charge_ID()>0){

		}

		if(hMbasesGravAseg==null || hMbasesGravAseg.isEmpty()){
			return true;
		} else {
			return createInvoiceTax(mInvoiceLine, hMbasesGravAseg);
		}
	}

	/**
	 * Crear los impuestos
	 * @param mInvoiceLine
	 * @param hMbasesGravAseg
	 * @return
	 */
	private boolean createInvoiceTax(final MInvoiceLine mInvoiceLine, final List<MInvoiceTax> hMbasesGravAseg){
		//
		if (hMbasesGravAseg == null || hMbasesGravAseg.isEmpty()) {
			return true;
		}
		// Bases gravables de la factura de la aseguradora
		BigDecimal totalVtaAseg = Env.ZERO;
		// Crear los impuestos a partir de las 2 busquedas posibles anteriores

		// Obtener el total de venta
		for (int x = 0; x < hMbasesGravAseg.size(); x++) {
			totalVtaAseg = totalVtaAseg.add(hMbasesGravAseg.get(x)
					.getTaxBaseAmt());
		}

		//
		for (int x = 0; x < hMbasesGravAseg.size(); x++) {

			// Tasa de impuesto anterior sin monto
			final MInvoiceTax iTax = MInvoiceTax.get(mInvoiceLine,
					hMbasesGravAseg.get(x).getC_Tax_ID(),
					getPrecision(), false, get_TrxName()); // current
			// Tax

			if (iTax == null) {
				continue;
			}

			iTax.setIsTaxIncluded(isTaxIncluded());

			// Impuestos
			// taxBaseAmt, taxAmt
			// Impuesto prorrateado por base grabada
			// Proporcion con respecto al total de venta (suma de
			// solo cargos)
			BigDecimal ratio = hMbasesGravAseg
					.get(x)
					.getTaxBaseAmt().abs()
					.divide(totalVtaAseg, 6,
							BigDecimal.ROUND_HALF_UP);
			// Base proporcional de CCCmDDesc
			BigDecimal baseGrav = mInvoiceLine.getLineNetAmt()
					.multiply(ratio);
			// Impuesto proporcional de CCCmDDesc
			BigDecimal impuesto = baseGrav.multiply(iTax
					.getTax()
					.getRate()
					.divide(Env.ONEHUNDRED, 6,
							BigDecimal.ROUND_HALF_UP));

			// Proporcional CCCmD
			iTax.setTaxAmt((iTax.getTaxAmt().add(impuesto))
					.setScale(getPrecision(),
							BigDecimal.ROUND_HALF_UP));//
			iTax.setTaxBaseAmt((iTax.getTaxBaseAmt().add(baseGrav))
					.setScale(getPrecision(),
							BigDecimal.ROUND_HALF_UP));

			//
			if (!iTax.save(get_TrxName())) {
				continue;
			}
		}
		return true;
	}

	/**
	 * Obtener el total de impuestos de la extension de la aseguradora
	 * Prorrateando los CCCmD a las bases grabables de la extension
	 * @param mInvoiceLine
	 * @return
	 */
	private List<MInvoiceTax> tasas(final MInvoiceLine mInvoiceLine){

		BigDecimal lcTotalVtaAseg = Env.ZERO;

		// Tasas de impuestos de aseguradora sin CCCmD ni Descuento
		final List<MInvoiceTax> lstInvoiceTaxAseg = MInvoiceTax.tasasImpCargos(getCtx(),
				mInvoiceLine.getEXME_CtaPacDet_ID(),
				get_TrxName());

		if(lstInvoiceTaxAseg==null || lstInvoiceTaxAseg.isEmpty()){
			return lstInvoiceTaxAseg;
		}

		// Cargos de la extension de la aseguradora solo los calculos
		final List<MCtaPacDet> lstCCCmDDescAseg = MInvoiceTax.tasasImpCargosCCCmD(getCtx(),
				mInvoiceLine.getEXME_CtaPacDet_ID(),
				get_TrxName());

		if(lstCCCmDDescAseg==null || lstCCCmDDescAseg.isEmpty()){
			return lstInvoiceTaxAseg;
		}

		for (int i = 0; i < lstCCCmDDescAseg.size(); i++) {
			if(lstCCCmDDescAseg.get(i).getExtension()!=null){
				lcTotalVtaAseg = lstCCCmDDescAseg.get(i).getExtension().getGrandTotal();
				break;
			}
		}


		// Obtener el total de impuestos de la extension de la aseguradora
		// Prorrateando los CCCmD a las bases grabables de la extension


		// Barrer los CCCmD negativos para prorratear
		for (int z = 0; z < lstCCCmDDescAseg.size(); z++) {
			// Barrer los cargos de la aseguradora
			for (int x = 0; x < lstInvoiceTaxAseg.size(); x++) {

				// Impuestos taxBaseAmt, taxAmt
				// Impuesto prorrateado por base grabada
				// Proporcion con respecto al total de venta (suma de
				// solo cargos)
				if (lstInvoiceTaxAseg.get(x).getTaxBaseAmt().compareTo(BigDecimal.ZERO) > 0) {
					// proporcion
					final BigDecimal ratio = lstInvoiceTaxAseg.get(x).getTaxBaseAmt().divide(
							lcTotalVtaAseg, 6, BigDecimal.ROUND_HALF_UP);
					// Base lcTotalVta de CCCmDDesc
					final BigDecimal baseGrav = lstCCCmDDescAseg.get(z).getLineNetAmt()
							.multiply(ratio);// 1
					// Impuesto proporcional de CCCmDDesc
					final BigDecimal impuesto = baseGrav.multiply(lstInvoiceTaxAseg.get(x)
							.getTax()
							.getRate()
							.divide(Env.ONEHUNDRED, 6,
									BigDecimal.ROUND_HALF_UP));

					// Proporcional CCCmD
					lstInvoiceTaxAseg.get(x).setTaxAmt((lstInvoiceTaxAseg.get(x).getTaxAmt().add(impuesto))
							.setScale(getPrecision(),
									BigDecimal.ROUND_HALF_UP));//
					lstInvoiceTaxAseg.get(x).setTaxBaseAmt((lstInvoiceTaxAseg.get(x).getTaxBaseAmt().add(baseGrav))
							.setScale(getPrecision(),
									BigDecimal.ROUND_HALF_UP));
				}
				//
			}// fin for
		}
		return lstInvoiceTaxAseg;
	}

//	/**
//	 * Tax isSummary
//	 *
//	 * @param totalLines
//	 *            : sum totalLines
//	 * @return BigDecimal
//	 */
//	private BigDecimal taxsSummary(final BigDecimal totalLines) {
//		// Taxes
//		BigDecimal grandTotal = totalLines;
//		// Lineas de impuestp
//		final MInvoiceTax[] taxes = getTaxes(true);
//		for (int i = 0; i < taxes.length; i++) {
//			final MInvoiceTax iTax = taxes[i];
//			final MTax tax = iTax.getTax();
//
//			// Si es resumen
//			if (tax.isSummary()) {
//				final MTax[] cTaxes = tax.getChildTaxes(false); // Multiple
//				// taxes
//				for (int j = 0; j < cTaxes.length; j++) {
//					final MTax cTax = cTaxes[j];
//					final BigDecimal taxAmt = cTax.calculateTax(
//							iTax.getTaxBaseAmt(), isTaxIncluded(),
//							getPrecision());
//					//
//					final MInvoiceTax newITax = new MInvoiceTax(getCtx(), 0,
//							get_TrxName());
//					newITax.setClientOrg(this);
//					newITax.setC_Invoice_ID(getC_Invoice_ID());
//					newITax.setC_Tax_ID(cTax.getC_Tax_ID());
//					newITax.setPrecision(getPrecision());
//					newITax.setIsTaxIncluded(isTaxIncluded());
//					newITax.setTaxBaseAmt(iTax.getTaxBaseAmt());
//					newITax.setTaxAmt(taxAmt);
//					if (!newITax.save(get_TrxName())) {
//						return null;
//					}
//					//
//					if (!isTaxIncluded()) {
//						grandTotal = grandTotal.add(taxAmt);
//					}
//				}
//
//				// Borra la linea summary y deja el desglose
//				if (!iTax.delete(true, get_TrxName())) {
//					return null;
//				}
//			} else {
//				// Suma el impuesto calculado
//				if (!isTaxIncluded()) {
//					grandTotal = grandTotal.add(iTax.getTaxAmt());
//				}
//			}
//		}
//		return grandTotal;
//	}

	/**
	 * (Re) Create Pay Schedule
	 *
	 * @return true if valid schedule
	 */
	private boolean createPaySchedule() {
		if (getC_PaymentTerm_ID() == 0) {
			return false;
		}
		final MPaymentTerm pt = new MPaymentTerm(getCtx(),
				getC_PaymentTerm_ID(), null);
		log.fine(pt.toString());
		return pt.apply(this); // calls validate pay schedule
	} // createPaySchedule

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
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		if(isSOTrx()){
			return completeItSO();
		} else {
			return completeItPO();
		}
	}
	
	/**
	 * Liberar la OC/RM por si aun le faltan lineas por recibir
	 */
	private void releaseInOutOrder(){
		for(MInvoiceLine line : Arrays.asList(getLines())){
			if(line.getC_OrderLine_ID() > 0){
				MOrderLine orderLine = (MOrderLine) line.getC_OrderLine();
				orderLine.setisBeingUsedInvoice(false);
				orderLine.save(get_TrxName());
			}
			if(line.getM_InOutLine_ID() > 0){
				MInOutLine inOutLine = (MInOutLine) line.getM_InOutLine();
				inOutLine.setisBeingUsedInvoice(false);
				inOutLine.save(get_TrxName());
			}
		}
	}

	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeItPO()
	{
		//	Re-Check
		if (!mJustPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		//	Implicit Approval
		if (!isApproved()){
			approveIt();
		}

		log.info(toString());
		StringBuffer info = new StringBuffer();

		//	Create Cash
		// LAS FACTURAS PROVEEDOR EN EFECTIVO SE PAGARAN DIRECTO EN CAPTURA DE PAGOS
//		if (PAYMENTRULE_Cash.equals(getPaymentRule()))
//		{
//			final MCash cash = MCash.get (getCtx(), getAD_Org_ID(),
//				getDateInvoiced(), getC_Currency_ID(), get_TrxName());
//			if (cash == null || cash.get_ID() == 0)
//			{
//				mProcessMsg = "@NoCashBook@";
//				return DocAction.STATUS_Invalid;
//			}
//			final MCashLine cl = new MCashLine (cash);
//			cl.setInvoice(this);
//			if (!cl.save(get_TrxName()))
//			{
//				mProcessMsg = "Could not save Cash Journal Line";
//				return DocAction.STATUS_Invalid;
//			}
//			info.append("@C_Cash_ID@: " + cash.getName() +  " #" + cl.getLine());
//			setC_CashLine_ID(cl.getC_CashLine_ID());
//		}	//	CashBook

		//	Update Order & Match
		int matchInv = 0;
		int matchPO = 0;
		MInvoiceLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MInvoiceLine line = lines[i];

			//	Update Order Line
			MOrderLine ol = null;
			if (line.getC_OrderLine_ID() != 0)
			{
				if (isSOTrx()
					|| line.getM_Product_ID() == 0)
				{
					ol = new MOrderLine (getCtx(), line.getC_OrderLine_ID(), get_TrxName());
					if (line.getQtyInvoiced() != null)
						ol.setQtyInvoiced(ol.getQtyInvoiced().add(line.getQtyInvoiced()));
					if (!ol.save(get_TrxName()))
					{
						mProcessMsg = Utilerias.getLabel("error.CPORequisition.OrderLine");//"Could not update Order Line";
						return DocAction.STATUS_Invalid;
					}
				}
				//	Order Invoiced Qty updated via Matching Inv-PO
				else
					if (!isSOTrx()
					&& line.getM_Product_ID() != 0
					&& !isReversal())
				{
					//	MatchPO is created also from MInOut when Invoice exists before Shipment
					BigDecimal matchQty = line.getQtyInvoiced();
					MMatchPO po = MMatchPO.create (line, null,
						getDateInvoiced(), matchQty);
					if (!po.save(get_TrxName())){
						mProcessMsg = Utilerias.getLabel("msj.concProd")+" "+MedsysException.getMessageFromLogger(null);//"Could not create PO Matching";
						return DocAction.STATUS_Invalid;
					}
					else
						matchPO++;
				}
			}

			//	Matching - Inv-Shipment
			if (!isSOTrx()
				&& line.getM_InOutLine_ID() != 0
				&& line.getM_Product_ID() != 0
				&& !isReversal())
			{
				BigDecimal matchQty = line.getQtyInvoiced();
				MMatchInv inv = new MMatchInv(line, getDateInvoiced(), matchQty);
				if (!inv.save(get_TrxName())) {
					mProcessMsg = Utilerias.getLabel("msj.concInv")+" "+MedsysException.getMessageFromLogger(null);//"Could not create Invoice Matching"
					return DocAction.STATUS_Invalid;
				}
				else
					matchInv++;
				
				MInOutLine il = new MInOutLine (getCtx(), line.getM_InOutLine_ID(), get_TrxName());
				if (line.getQtyInvoiced() != null)
					il.setQtyInvoiced(il.getQtyInvoiced().add(line.getQtyInvoiced()));
				if (!il.save(get_TrxName())) {
					mProcessMsg = Utilerias.getLabel("error.CPORequisition.OrderLine")+" "+MedsysException.getMessageFromLogger(null);//"Could not create Invoice Matching"
					return DocAction.STATUS_Invalid;
				}
				
				
			}
		}	//	for all lines
		if (matchInv > 0){
//			info.append(" @M_MatchInv_ID@#").append(matchInv).append(" ");
			info.append(" ").append(Utilerias.getMsg(getCtx(), "msj.concInv")).append(" ").append(getDocumentNo());
		}
		if (matchPO > 0){
//			info.append(" @M_MatchPO_ID@#").append(matchPO).append(" ");
			MMatchPO[] match = MMatchPO.getInvoice (getCtx(), getC_Invoice_ID(), get_TrxName());
			if (match.length > 0) {
				info.append(" ").append(Utilerias.getMsg(getCtx(), "msj.concProd")).append(" ").append(match[0].getDocumentNo());
			}
		}


		//	Update BP Statistics
		final MClientInfo clientInfo = MClientInfo.get(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()), null);
		final MAcctSchema schema = MAcctSchema.get(Env.getCtx(), clientInfo.getC_AcctSchema1_ID());
		MBPartner bp = new MBPartner (getCtx(), getC_BPartner_ID(), get_TrxName());
		//	Update total revenue and balance / credit limit (reversed on AllocationLine.processIt)
		BigDecimal invAmt = MConversionRate.convertBase(getCtx(), getGrandTotal(true),	//	CM adjusted
			getC_Currency_ID(), getDateAcct(), schema.getC_Currency_ID(), getAD_Client_ID(), getAD_Org_ID());
		if (invAmt == null){
			mProcessMsg = Utilerias.getLabel("error.caja.conversionRate", getCurrencyISO() );//"Could not convert C_Currency_ID=" + getC_Currency_ID()
				//+ " to base C_Currency_ID=" + MClient.get(Env.getCtx()).getC_Currency_ID();
			return DocAction.STATUS_Invalid;
		}
		//	Total Balance
		BigDecimal newBalance = bp.getTotalOpenBalance(false);
		if (newBalance == null)
			newBalance = Env.ZERO;
//		if (isSOTrx())
//		{
//			newBalance = newBalance.add(invAmt);
//			//
//			if (bp.getFirstSale() == null)
//				bp.setFirstSale(getDateInvoiced());
//			BigDecimal newLifeAmt = bp.getActualLifeTimeValue();
//			if (newLifeAmt == null)
//				newLifeAmt = invAmt;
//			else
//				newLifeAmt = newLifeAmt.add(invAmt);
//			BigDecimal newCreditAmt = bp.getSO_CreditUsed();
//			if (newCreditAmt == null)
//				newCreditAmt = invAmt;
//			else
//				newCreditAmt = newCreditAmt.add(invAmt);
//			//
//			log.fine("GrandTotal=" + getGrandTotal(true) + "(" + invAmt
//				+ ") BP Life=" + bp.getActualLifeTimeValue() + "->" + newLifeAmt
//				+ ", Credit=" + bp.getSO_CreditUsed() + "->" + newCreditAmt
//				+ ", Balance=" + bp.getTotalOpenBalance(false) + " -> " + newBalance);
//			bp.setActualLifeTimeValue(newLifeAmt);
//			bp.setSO_CreditUsed(newCreditAmt);
//		}	//	SO
//		else
		if (!isSOTrx()){
			newBalance = newBalance.subtract(invAmt);
			log.fine("GrandTotal=" + getGrandTotal(true) + "(" + invAmt
				+ ") Balance=" + bp.getTotalOpenBalance(false) + " -> " + newBalance);
		}
		bp.setTotalOpenBalance(newBalance);
		bp.setSOCreditStatus();
		if (!bp.save(get_TrxName()))
		{
			mProcessMsg = Utilerias.getLabel("error.ActualizarSocio");//"Could not update Business Partner";
			return DocAction.STATUS_Invalid;
		}

		//	User - Last Result/Contact
		if (getAD_User_ID() != 0)
		{
			MUser user = new MUser (getCtx(), getAD_User_ID(), get_TrxName());
			user.setLastContact(new Timestamp(System.currentTimeMillis()));
			user.setLastResult(Msg.translate(getCtx(), "C_Invoice_ID") + ": " + getDocumentNo());
			if (!user.save(get_TrxName()))
			{
				mProcessMsg = Utilerias.getLabel("error.ActualizarUsuarioSocio");//"Could not update Business Partner User";
				return DocAction.STATUS_Invalid;
			}
		}	//	user

//		//	Update Project
//		if (isSOTrx() && getC_Project_ID() != 0)
//		{
//			MProject project = new MProject (getCtx(), getC_Project_ID(), get_TrxName());
//			BigDecimal amt = getGrandTotal(true);
//			int C_CurrencyTo_ID = project.getC_Currency_ID();
//			if (C_CurrencyTo_ID != getC_Currency_ID())
//				amt = MConversionRate.convert(getCtx(), amt, getC_Currency_ID(), C_CurrencyTo_ID,
//					getDateAcct(), 0, getAD_Client_ID(), getAD_Org_ID());
//			if (amt == null)
//			{
//				mProcessMsg = "Could not convert C_Currency_ID=" + getC_Currency_ID()
//					+ " to Project C_Currency_ID=" + C_CurrencyTo_ID;
//				return DocAction.STATUS_Invalid;
//			}
//			BigDecimal newAmt = project.getInvoicedAmt();
//			if (newAmt == null)
//				newAmt = amt;
//			else
//				newAmt = newAmt.add(amt);
//			log.fine("GrandTotal=" + getGrandTotal(true) + "(" + amt
//				+ ") Project " + project.getName()
//				+ " - Invoiced=" + project.getInvoicedAmt() + "->" + newAmt);
//			project.setInvoicedAmt(newAmt);
//			if (!project.save(get_TrxName()))
//			{
//				mProcessMsg = "Could not update Project";
//				return DocAction.STATUS_Invalid;
//			}
//		}	//	project

		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null){
			mProcessMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		//	Counter Documents
		if(!isReversal()){
			final MInvoice counter = createCounterDoc();
			if (counter != null){
				info.append(" - @CounterDoc@: @C_Invoice_ID@=").append(counter.getDocumentNo());
			}
		}

		mProcessMsg = info.toString().trim();
		setProcessed(true);
		setDocAction(DOCACTION_Close);

		//Inicio Facturacion Electronica desde Compiere
//		MEXMEConfigFE cfe = MEXMEConfigFE.get(getCtx(), null);
//
//		if (cfe!=null && MDocType.get(getCtx(), getC_DocType_ID()).getDocBaseType().contains("AR"))
//		{   //Variable que determina si la impresion se realizo de manera correcta o fallo al imprimir
//			boolean errorImpresion=false;
//
//			try
//			{
//			    errorImpresion=MEXMEImpresora.enviaCompiereSpoolFE(getC_Invoice_ID(), getCtx(),
//					bp.isImprimeFactura()?"Y":"N", String.valueOf(bp.getCopias().intValue()),bp.isSendEMail()?"Y":"N"
//						, bp.getEMail(), getAD_Client_ID(),getAD_Org_ID(),MDocType.get(getCtx(), getC_DocType_ID()).getDocBaseType());
//			}
//			catch(Exception e1)
//			{
//				e1.printStackTrace();
//				errorImpresion=true;
//			}
//
//
//		   String msg="";
//
//		   if(errorImpresion)
//			  msg= Msg.getMsg(getCtx(), "ErrorImpresionFE");
//		   else
//			  msg= Msg.getMsg(getCtx(), "OKImpresionFE");
//
//
////		   new ADialogDialog (new JFrame(), null,msg,JOptionPane.INFORMATION_MESSAGE);
//
//
//		}
		//Final Facturacion Electronica desde Compiere
		releaseInOutOrder();
		return DocAction.STATUS_Completed;
	}	//	completeIt

	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid)
	 */
	public String prepareItPO()
	{
		log.info(toString());
		mProcessMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (mProcessMsg != null)
			return DocAction.STATUS_Invalid;
		MDocType dt = MDocType.get(getCtx(), getC_DocTypeTarget_ID());

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			mProcessMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		//	Lines
		MInvoiceLine[] lines = getLines(true);
		if (lines.length == 0)
		{
			mProcessMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		//	No Cash Book
		if (isSOTrx() && PAYMENTRULE_Cash.equals(getPaymentRule())
			&& MCashBook.get(getCtx(), getAD_Org_ID(), getC_Currency_ID(), get_TrxName()) == null)
		{
			mProcessMsg = "@NoCashBook@";
			return DocAction.STATUS_Invalid;
		}

		//	Convert/Check DocType
		if (getC_DocType_ID() != getC_DocTypeTarget_ID() )
			setC_DocType_ID(getC_DocTypeTarget_ID());
		if (getC_DocType_ID() == 0)
		{
			mProcessMsg = Utilerias.getLabel("error.exp.tipoDocumento");//"No Document Type";
			return DocAction.STATUS_Invalid;
		}

		explodeBOM();
		if (!calculateTaxTotalPO())	//	setTotals
		{
			mProcessMsg = Utilerias.getLabel("error.getImpuesto");//"Error calculating Tax";
			return DocAction.STATUS_Invalid;
		}

		createPaySchedule();

//		//	Credit Status
//		if (isSOTrx() && !isReversal())
//		{
//			MBPartner bp = new MBPartner (getCtx(), getC_BPartner_ID(), null);
//			if (MBPartner.SOCREDITSTATUS_CreditStop.equals(bp.getSOCreditStatus()))
//			{
//				mProcessMsg = "@BPartnerCreditStop@ - @TotalOpenBalance@="
//					+ bp.getTotalOpenBalance()
//					+ ", @SO_CreditLimit@=" + bp.getSO_CreditLimit();
//				return DocAction.STATUS_Invalid;
//			}
//		}

		//	Landed Costs
		if (!isSOTrx())
		{
			for (int i = 0; i < lines.length; i++)
			{
				final MInvoiceLine line = lines[i];
				
				// Asignacion de costo de embarque, 
				// A la fecha no se esta utilizando como criterio para generar la factura
				if(!line.isProcessed()){
					final String error = line.allocateLandedCosts();
					if (error != null && error.length() > 0)
					{
						mProcessMsg = error;
						return DocAction.STATUS_Invalid;
					}
				}
			}
		}

		//	Add up Amounts
		mJustPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt

	/**	Invoice Taxes			*/
//	private MInvoiceTax[]	m_taxes;

	/**
	 * 	Calculate Tax and Total
	 * 	@return true if calculated
	 */
	private boolean calculateTaxTotalPO()
	{
		log.fine("");
		//	Delete Taxes
		DB.executeUpdate("DELETE C_InvoiceTax WHERE C_Invoice_ID=" + getC_Invoice_ID(), get_TrxName());
//		m_taxes = null;

		//	Lines
		BigDecimal totalLines = Env.ZERO;
		ArrayList<Integer> taxList = new ArrayList<Integer>();
		MInvoiceLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MInvoiceLine line = lines[i];
			/**	Sync ownership for SO
			if (isSOTrx() && line.getAD_Org_ID() != getAD_Org_ID())
			{
				line.setAD_Org_ID(getAD_Org_ID());
				line.save();
			}	**/
			Integer taxID = new Integer(line.getC_Tax_ID());
			if (!taxList.contains(taxID))
			{
				MInvoiceTax iTax = MInvoiceTax.get (line, getPrecision(),
					false, get_TrxName());	//	current Tax
				if (iTax != null)
				{
					iTax.setIsTaxIncluded(isTaxIncluded());
					if (!iTax.calculateTaxFromLines())
						return false;
					if (!iTax.save())
						return false;
					taxList.add(taxID);
				}
			}
			totalLines = totalLines.add(line.getLineNetAmt());
		}

		//	Taxes
		BigDecimal grandTotal = totalLines;
		MInvoiceTax[] taxes = getTaxes(true);
		for (int i = 0; i < taxes.length; i++)
		{
			MInvoiceTax iTax = taxes[i];
			MTax tax = iTax.getTax();
			if (tax.isSummary())
			{
				MTax[] cTaxes = tax.getChildTaxes(false);	//	Multiple taxes
				for (int j = 0; j < cTaxes.length; j++)
				{
					MTax cTax = cTaxes[j];
					BigDecimal taxAmt = cTax.calculateTax(iTax.getTaxBaseAmt(), isTaxIncluded(), getPrecision());
					//
					MInvoiceTax newITax = new MInvoiceTax(getCtx(), 0, get_TrxName());
					newITax.setClientOrg(this);
					newITax.setC_Invoice_ID(getC_Invoice_ID());
					newITax.setC_Tax_ID(cTax.getC_Tax_ID());
					newITax.setPrecision(getPrecision());
					newITax.setIsTaxIncluded(isTaxIncluded());
					newITax.setTaxBaseAmt(iTax.getTaxBaseAmt());
					newITax.setTaxAmt(taxAmt);
					if (!newITax.save(get_TrxName()))
						return false;
					//
					if (!isTaxIncluded())
						grandTotal = grandTotal.add(taxAmt);
				}
				if (!iTax.delete(true, get_TrxName()))
					return false;
			}
			else
			{
				if (!isTaxIncluded())
					grandTotal = grandTotal.add(iTax.getTaxAmt());
			}
		}
		//
		setTotalLines(totalLines);
		setGrandTotal(grandTotal);
		return true;
	}	//	calculateTaxTotal

	/**
	 * Complete Document
	 *
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeItSO() {
		// Re-Check
		if (!mJustPrepared) {
			String status = prepareItSO();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		// Implicit Approval
		if (!isApproved()) {
			approveIt();
		}

		log.info(toString());
		final StringBuffer info = new StringBuffer();

		// Update Order & Match
		matchInv = 0;
		matchPO = 0;

		//
		// String retValue = null;
		final MInvoiceLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++) {
			final MInvoiceLine line = lines[i];

			if (updateOrderLine(line) != null
					|| matchingInvShipment(line) != null) {
				return DocAction.STATUS_Invalid;
			}
		} // for all lines

		//
		if (matchInv > 0) {
			info.append(" @M_MatchInv_ID@#").append(matchInv).append(" ");
		}
		if (matchPO > 0) {
			info.append(" @M_MatchPO_ID@#").append(matchPO).append(" ");
		}

		String docAction = DocAction.STATUS_Invalid;
		if (updateUser() == null) {

			// User Validation
			final String valid = ModelValidationEngine.get().fireDocValidate(
					this, ModelValidator.TIMING_AFTER_COMPLETE);
			if (valid != null) {
				mProcessMsg = valid;
				return docAction;
			}
			// Las facturas no tienen referencia a otra factura y la nota de credito si 
			// por lo que podria ejecutarse este metodo y crear una factura adicional a la creada
			// Counter Documents
//			final MInvoice counter = createCounterDoc();
//			if (counter != null) {
//				info.append(" - @CounterDoc@: @C_Invoice_ID@=").append(
//						counter.getDocumentNo());
//			}

			mProcessMsg = info.toString().trim();
			
			// NO crea la recepcion de efectivo (Cash) todavia por lo que es una "Nota de remision"
			// Se estable que el estatus es DR hasta que se haga factura IP
			final MDocType mDocType = new MDocType(getCtx(), getC_DocTypeTarget_ID(), get_TrxName());
			if(isBackoffice() 
					&& X_C_DocType.DOCBASETYPE_ARInvoice.equals(mDocType.getDocBaseType())
					&& X_C_DocType.DOCSUBTYPESO_SalesReceipt.equals(mDocType.getDocSubTypeSO())){
				setProcessed(true);
				setDocAction(DOCACTION_Close);
				docAction = DocAction.STATUS_Completed;
				
			} else {
				// La ponemos como no procesado para que no postee, sino hasta corte de
				// caja.
				setProcessed(false, get_TrxName());
				// El siguiente paso es completar la factura.
				setDocAction(DOCACTION_WaitComplete);
				// Retornamos DR para ser una nota de remision
				setDocStatus(DOCSTATUS_Drafted);
				docAction = DocAction.STATUS_Drafted;
			}
		} 
		return docAction;
	}// completeIt

	/**
	 * updateOrderLine
	 *
	 * @param line
	 * @return
	 */
	private String updateOrderLine(final MInvoiceLine line) {

		// Update Order Line
		MOrderLine orline = null;
		if (line.getC_OrderLine_ID() != 0) {
			if (isSOTrx() || line.getM_Product_ID() == 0) {
				orline = new MOrderLine(getCtx(), line.getC_OrderLine_ID(),
						get_TrxName());
				if (line.getQtyInvoiced() != null) {
					orline.setQtyInvoiced(orline.getQtyInvoiced().add(
							line.getQtyInvoiced()));
				}
				if (!orline.save(get_TrxName())) {
					mProcessMsg = Utilerias.getLabel("error.CPORequisition.OrderLine");//"Could not update Order Line";
					return DocAction.STATUS_Invalid;
				}
			}
			// Order Invoiced Qty updated via Matching Inv-PO
			else if (!isSOTrx() && line.getM_Product_ID() != 0 && !isReversal()) {
				// MatchPO is created also from MInOut when Invoice exists
				// before Shipment
				final BigDecimal matchQty = line.getQtyInvoiced();
				final MMatchPO mmPo = MMatchPO.create(line, null,
						getDateInvoiced(), matchQty);
				if (mmPo.save(get_TrxName())) {
					matchPO++;
				} else {
					mProcessMsg = Utilerias.getLabel("error.crearConciliacionDeRecepcion");//"Could not create PO Matching";
					return DocAction.STATUS_Invalid;
				}
			}
		}

		return null;

	}

	/**
	 * matchingInvShipment
	 *
	 * @param line
	 * @return
	 */
	private String matchingInvShipment(final MInvoiceLine line) {

		// Matching - Inv-Shipment
		if (!isSOTrx() && line.getM_InOutLine_ID() != 0
				&& line.getM_Product_ID() != 0 && !isReversal()) {
			final BigDecimal matchQty = line.getQtyInvoiced();
			final MMatchInv inv = new MMatchInv(line, getDateInvoiced(),
					matchQty);
			if (inv.save(get_TrxName())) {
				matchInv++;
			} else {
				mProcessMsg = Utilerias.getLabel("error.crearConciliacionDeFactura");//"Could not create Invoice Matching";
				return DocAction.STATUS_Invalid;
			}
		}
		return null;
	}

//	/**
//	 * updateInv
//	 *
//	 * @return
//	 * @deprecated
//	 */
//	private String updateInv() {
//		if (!isSOTrx()) {
//			// Update BP Statistics
//			final MBPartner bpart = new MBPartner(getCtx(), getC_BPartner_ID(),
//					get_TrxName());
//			// Update total revenue and balance / credit limit (reversed on
//			// AllocationLine.processIt)
//			final BigDecimal invAmt = MConversionRate.convertBase(
//					getCtx(),
//					getGrandTotal(true), // CM
//					// adjusted
//					getC_Currency_ID(), getDateAcct(), 0, getAD_Client_ID(),
//					getAD_Org_ID());
//			if (invAmt == null) {
//				mProcessMsg = "Could not convert C_Currency_ID="
//						+ getC_Currency_ID() + " to base C_Currency_ID="
//						+ MClient.get(getCtx()).getC_Currency_ID();
//				return DocAction.STATUS_Invalid;
//			}
//
//			// Total Balance
//			BigDecimal newBalance = bpart.getTotalOpenBalance(false);
//			if (newBalance == null) {
//				newBalance = Env.ZERO;
//			}
//			if (isSOTrx()) {
//				newBalance = newBalance.add(invAmt);
//				//
//				if (bpart.getFirstSale() == null) {
//					bpart.setFirstSale(getDateInvoiced());
//				}
//				BigDecimal newLifeAmt = bpart.getActualLifeTimeValue();
//				if (newLifeAmt == null) {
//					newLifeAmt = invAmt;
//				} else {
//					newLifeAmt = newLifeAmt.add(invAmt);
//				}
//				BigDecimal newCreditAmt = bpart.getSO_CreditUsed();
//				if (newCreditAmt == null) {
//					newCreditAmt = invAmt;
//				} else {
//					newCreditAmt = newCreditAmt.add(invAmt);
//				}
//
//				log.fine("GrandTotal=" + getGrandTotal(true) + "(" + invAmt
//						+ ") BP Life=" + bpart.getActualLifeTimeValue() + "->"
//						+ newLifeAmt + ", Credit=" + bpart.getSO_CreditUsed()
//						+ "->" + newCreditAmt + ", Balance="
//						+ bpart.getTotalOpenBalance(false) + " -> "
//						+ newBalance);
//				bpart.setActualLifeTimeValue(newLifeAmt);
//				bpart.setSO_CreditUsed(newCreditAmt);
//			} // SO
//			else {
//				newBalance = newBalance.subtract(invAmt);
//				log.fine("GrandTotal=" + getGrandTotal(true) + "(" + invAmt
//						+ ") Balance=" + bpart.getTotalOpenBalance(false)
//						+ " -> " + newBalance);
//			}
//			bpart.setTotalOpenBalance(newBalance);
//			bpart.setSOCreditStatus();
//			if (!bpart.save(get_TrxName())) {
//				mProcessMsg = "Could not update Business Partner";
//				return DocAction.STATUS_Invalid;
//			}
//		}
//		return null;
//	}

	/**
	 * updateUser
	 *
	 * @return
	 */
	private String updateUser() {
		// User - Last Result/Contact
		if (!isSOTrx() && getAD_User_ID() != 0) {// Evitar bloqueos
			final MUser user = new MUser(getCtx(), getAD_User_ID(),
					get_TrxName());
			user.setLastContact(new Timestamp(System.currentTimeMillis()));
			user.setLastResult(Msg.translate(getCtx(), "C_Invoice_ID") + ": "
					+ getDocumentNo());
			if (!user.save(get_TrxName())) {
				mProcessMsg = Utilerias.getLabel("error.ActualizarUsuarioSocio");//"Could not update Business Partner User";
				return DocAction.STATUS_Invalid;
			}
		} // user
		return null;
	}

//	/**
//	 * updateProject
//	 *
//	 * @return
//	 * @deprecated
//	 */
//	private String updateProject() {
//		// Update Project
//		if (isSOTrx() && getC_Project_ID() != 0) {
//			final MProject project = new MProject(getCtx(), getC_Project_ID(),
//					get_TrxName());
//			BigDecimal amt = getGrandTotal(true);
//			int C_CurrencyTo_ID = project.getC_Currency_ID();
//			if (C_CurrencyTo_ID != getC_Currency_ID()) {
//				amt = MConversionRate.convert(getCtx(), amt,
//						getC_Currency_ID(), C_CurrencyTo_ID, getDateAcct(), 0,
//						getAD_Client_ID(), getAD_Org_ID());
//			}
//			if (amt == null) {
//				mProcessMsg = "Could not convert C_Currency_ID="
//						+ getC_Currency_ID() + " to Project C_Currency_ID="
//						+ C_CurrencyTo_ID;
//				return DocAction.STATUS_Invalid;
//			}
//			BigDecimal newAmt = project.getInvoicedAmt();
//			if (newAmt == null) {
//				newAmt = amt;
//			} else {
//				newAmt = newAmt.add(amt);
//			}
//			log.fine("GrandTotal=" + getGrandTotal(true) + "(" + amt
//					+ ") Project " + project.getName() + " - Invoiced="
//					+ project.getInvoicedAmt() + "->" + newAmt);
//			project.setInvoicedAmt(newAmt);
//			if (!project.save(get_TrxName())) {
//				mProcessMsg = "Could not update Project";
//				return DocAction.STATUS_Invalid;
//			}
//		} // project
//		return null;
//	}

	/**
	 * Create Counter Document
	 *
	 * @return counter invoice
	 */
	private MInvoice createCounterDoc() {
		// Is this a counter doc ?
		if (getRef_Invoice_ID() != 0) {
			return null;
		}

		// Org Must be linked to BPartner
		final MOrg org = MOrg.get(getCtx(), getAD_Org_ID());
		int counterBPartID = org.getLinkedC_BPartner_ID();
		if (counterBPartID == 0) {
			return null;
		}
		// Business Partner needs to be linked to Org
		final MBPartner bpart = new MBPartner(getCtx(), getC_BPartner_ID(),
				null);
		int counterAD_Org_ID = bpart.getAD_OrgBP_ID_Int();
		if (counterAD_Org_ID == 0) {
			return null;
		}

		final MBPartner counterBP = new MBPartner(getCtx(), counterBPartID,
				null);
		// MOrgInfo counterOrgInfo = MOrgInfo.get(getCtx(), counterAD_Org_ID);
		log.info("Counter BP=" + counterBP.getName());

		// Document Type
		int docTypeTargetID = 0;
		final MDocTypeCounter counterDT = MDocTypeCounter.getCounterDocType(
				getCtx(), getC_DocType_ID());
		if (counterDT == null) {// indirect
			docTypeTargetID = MDocTypeCounter.getCounterDocType_ID(getCtx(),
					getC_DocType_ID());
			log.fine("Indirect C_DocTypeTarget_ID=" + docTypeTargetID);
			if (docTypeTargetID <= 0) {
				return null;
			}
		} else {
			log.fine(counterDT.toString());
			if (!counterDT.isCreateCounter() || !counterDT.isValid()) {
				return null;
			}
			docTypeTargetID = counterDT.getCounter_C_DocType_ID();
		}

		// Deep Copy
		final MInvoice counter = copyFrom(this, getDateInvoiced(),
				docTypeTargetID, !isSOTrx(), true, get_TrxName(), true);
		//
		counter.setAD_Org_ID(counterAD_Org_ID);
		// counter.setM_Warehouse_ID(counterOrgInfo.getM_Warehouse_ID());
		//
		counter.setBPartner(counterBP);
		// Refernces (Should not be required
		counter.setSalesRep_ID(getSalesRep_ID());
		counter.save(get_TrxName());

		// Update copied lines
		final MInvoiceLine[] counterLines = counter.getLines(true);
		for (int i = 0; i < counterLines.length; i++) {
			final MInvoiceLine counterLine = counterLines[i];
			counterLine.setClientOrg(counter);
			counterLine.setInvoice(counter); // copies header values (BP, etc.)
			counterLine.setPrice();
			counterLine.setTax();
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
	public boolean voidIt() {
		log.info(toString());
		if (DOCSTATUS_Closed.equals(getDocStatus())
				|| DOCSTATUS_Reversed.equals(getDocStatus())
				|| DOCSTATUS_Voided.equals(getDocStatus())) {
			mProcessMsg = "Document Closed: " + getDocStatus();
			setDocAction(DOCACTION_None);
			return false;
		}

		// Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
				|| DOCSTATUS_Invalid.equals(getDocStatus())
				|| DOCSTATUS_InProgress.equals(getDocStatus())
				|| DOCSTATUS_Approved.equals(getDocStatus())
				|| DOCSTATUS_NotApproved.equals(getDocStatus())) {
			// Set lines to 0
			final MInvoiceLine[] lines = getLines(false);
			for (int i = 0; i < lines.length; i++) {
				final MInvoiceLine line = lines[i];
				BigDecimal old = line.getQtyInvoiced();
				if (old.compareTo(Env.ZERO) != 0) {
					line.setQty(Env.ZERO);
					line.setTaxAmt(Env.ZERO);
					line.setLineNetAmt(Env.ZERO);
					line.setLineTotalAmt(Env.ZERO);
					line.addDescription(Msg.getMsg(getCtx(), "Voided") + " ("
							+ old + ")");
					// Unlink Shipment
					if (line.getM_InOutLine_ID() != 0) {
						MInOutLine ioLine = new MInOutLine(getCtx(),
								line.getM_InOutLine_ID(), get_TrxName());
						ioLine.setIsInvoiced(false);
						ioLine.save(get_TrxName());
						line.setM_InOutLine_ID(0);
					}
					line.save(get_TrxName());
				}
			}
			addDescription(Msg.getMsg(getCtx(), "Voided"));
			setIsPaid(true);
			setC_Payment_ID(0);
		} else {
			releaseInOutOrder();
			return reverseCorrectIt();
		}

		setProcessed(true);
		setDocAction(DOCACTION_None);
		releaseInOutOrder();
		return true;
	} // voidIt

	/**
	 * Close Document.
	 *
	 * @return true if success
	 */
	public boolean closeIt() {
		boolean retValue = true;
		log.info(toString());

		// Proveedor
		if (!isSOTrx()) {
			final MDocType dtype = MDocType.get(getCtx(), getC_DocType_ID() == 0 ? getC_DocTypeTarget_ID() : getC_DocType_ID());
			final BigDecimal saldo = getInvoiceOpenAmt();
			final boolean isPayed = saldo.compareTo(BigDecimal.ZERO) == 0;
			
			// si la nota de debito no fue creada desde una cancelacion o no ha sido totalmente pagada
			if (MDocType.DOCBASETYPE_APDebitMemo.equals(dtype.getDocBaseType())) {
				if (!MEXMEInvoiceRel.isCancellation(getCtx(), getC_Invoice_ID(), get_TrxName()) && !isPayed) {
					mProcessMsg = "error.invoice.close.debit";
					retValue = false;
				}
			} 
			// La Nota de credito no esta asignada a 1 o mas Facturas
			else if (MDocType.DOCBASETYPE_APCreditMemo.equals(dtype.getDocBaseType())
				&& MEXMEInvoiceRel.getRefInvoices(getCtx(), getC_Invoice_ID(), get_TrxName()).isEmpty()) {
				mProcessMsg = "error.invoice.close.credit";
				retValue = false;
			}
			// La Factura no ha sido completamente pagada
			else if (MDocType.DOCBASETYPE_APInvoice.equals(dtype.getDocBaseType()) && !isPayed) {
				mProcessMsg = "error.invoice.close.invoice";
				retValue = false;
			}
		}
		
		if (retValue) {
			setProcessed(true);
			releaseInOutOrder();
			setDocAction(DOCACTION_None);
		}
		return retValue;
	} // closeIt

	public MDocType getDocType(){
		if (mDocType == null && getC_DocType_ID()>0){
			mDocType = new MDocType(getCtx(), getC_DocType_ID(), get_TrxName());
		}
		return mDocType;
	}
	
	/**
	 * Reverse Correction - same date
	 *
	 * @return true if success
	 */
	public boolean reverseCorrectIt() {
		log.info(toString());
		final MDocType dtype = MDocType.get(getCtx(), getC_DocType_ID());
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dtype.getDocBaseType(),
				getAD_Org_ID())) {
			mProcessMsg = "@PeriodClosed@";
			return false;
		}
		// Invoice has payments
		final BigDecimal payamt = MAllocationLine.getSumAmountOfInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
		if(payamt!=null && payamt.compareTo(Env.ZERO)!=0){
			mProcessMsg = Utilerias.getMsg(getCtx(), "error.invoice.revert.payment");
			return false;
		}
		
		// Invoice has credit notes allocations
		if (!MEXMEInvoiceRel.getRefAllocation(getCtx(), getC_Invoice_ID(), get_TrxName()).isEmpty()) {
			mProcessMsg = Utilerias.getMsg(getCtx(), "error.invoice.revert.creditnotes");
			return false;
		}
		// Reverse/Delete Matching
		if (!isSOTrx()) {
			//
			final MMatchInv[] mInv = MMatchInv.getInvoice(getCtx(),
					getC_Invoice_ID(), get_TrxName());
			for (int i = 0; i < mInv.length; i++) {
				mInv[i].delete(true);
			}
			//
			final MMatchPO[] mPO = MMatchPO.getInvoice(getCtx(),
					getC_Invoice_ID(), get_TrxName());
			for (int i = 0; i < mPO.length; i++) {
				if (mPO[i].getM_InOutLine_ID() == 0) {
					mPO[i].delete(true);
				} else {
					mPO[i].setC_InvoiceLine_ID(null);
					mPO[i].save(get_TrxName());
				}
			}
		}
		//
		load(get_TrxName()); // reload allocation reversal info
		String documentName = null;
		// Deep Copy
		final MInvoice reversal = copyFrom(this, getDateInvoiced(),
				getC_DocType_ID(), isSOTrx(), false, get_TrxName(), true);
		if (reversal == null) {
			mProcessMsg = Utilerias.getLabel("cancelaServ.error.cancelar");//"Could not create Invoice Reversal";
			return false;
		}
		reversal.setReversal(true);
		try {
			documentName = setReversalDocType(reversal);
			reversal.setC_DocTypeTarget_ID(reversal.getC_DocType_ID());
		
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			mProcessMsg = Utilerias.getLabel("cancelaServ.error.cancelar.voidIt", e.getMessage());
			return false;
		}
		// Reverse Line Qty // eCareSoft se genera una nota de credito en lugar de una factura negativa
		// reversal.setRef_Invoice_ID(getC_Invoice_ID());
		reversal.setC_Order_ID(getC_Order_ID());
		reversal.addDescription("{->" + getDocumentNo() + ")");
		//
		if (!reversal.processIt(DocAction.ACTION_Complete)) {
			mProcessMsg = Utilerias.getLabel("cancelaServ.error.cancelar.voidIt",reversal.getProcessMsg());
			return false;
		}
		reversal.setC_Payment_ID(0);
		reversal.setIsPaid(true);
		reversal.closeIt();
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.save(get_TrxName());
		mProcessMsg =  reversal.getDocumentNo();
		//
		addDescription("(" + reversal.getDocumentNo() + "<-)");

		// Clean up Reversed (this)
		final MInvoiceLine[] iLines = getLines(false);
		for (int i = 0; i < iLines.length; i++) {
			final MInvoiceLine iLine = iLines[i];
			if (iLine.getM_InOutLine_ID() != 0) {
				MInOutLine ioLine = new MInOutLine(getCtx(),
						iLine.getM_InOutLine_ID(), get_TrxName());
				ioLine.setIsInvoiced(false);
				ioLine.save(get_TrxName());
				// Reconsiliation
				iLine.setM_InOutLine_ID(0);
				iLine.save(get_TrxName());
			}
		}
		setProcessed(true);
		setDocStatus(DOCSTATUS_Reversed); // may come from void
		setDocAction(DOCACTION_None);
		setC_Payment_ID(0);
		setIsPaid(false);

		// Card 1318
		final MEXMEInvoiceRel invRel = new MEXMEInvoiceRel(getCtx(), 0, get_TrxName());
		invRel.setC_Invoice_ID(getC_Invoice_ID());
		invRel.setRef_Invoice_ID(reversal.getC_Invoice_ID());
		invRel.setAmount(getGrandTotal());
		invRel.setTrxType(MEXMEInvoiceRel.TRXTYPE_Cancellation);
		if (!invRel.save(get_TrxName())) {
			mProcessMsg = Utilerias.getMsg(getCtx(), "error.invoice.revert.saveRel");
			return false;
		}

		// Create Allocation
//		final MAllocationHdr alloc = new MAllocationHdr(getCtx(), false,
//				getDateAcct(), getC_Currency_ID(), Msg.translate(getCtx(),
//						"C_Invoice_ID")
//						+ ": "
//						+ getDocumentNo()
//						+ "/"
//						+ reversal.getDocumentNo(), get_TrxName());
//		alloc.setAD_Org_ID(getAD_Org_ID());
//		if (alloc.save()) {
//			// Amount
//			BigDecimal gtotal = getGrandTotal(true);
//			if (!isSOTrx()) {
//				gtotal = gtotal.negate();
//			}
//			// Orig Line
//			final MAllocationLine aLine = new MAllocationLine(alloc, gtotal,
//					Env.ZERO, Env.ZERO, Env.ZERO);
//			aLine.setC_Invoice_ID(getC_Invoice_ID());
//			aLine.save();
//			// Reversal Line
//			final MAllocationLine rLine = new MAllocationLine(alloc,
//					gtotal.negate(), Env.ZERO, Env.ZERO, Env.ZERO);
//			rLine.setC_Invoice_ID(reversal.getC_Invoice_ID());
//			rLine.save();
//			// Process It
//			if (alloc.processIt(DocAction.ACTION_Complete)) {
//				alloc.save();
//			}
//		}
		
		if(reversal!=null && reversal.getC_Invoice_ID()>0 && reversal.getC_DocType_ID()>0){
			mProcessMsg = documentName + " " + reversal.getDocumentNo();
		}
		return true;
	} // reverseCorrectIt

	
	private String setReversalDocType(final MInvoice reversal) throws Exception{
		String documentName = StringUtils.EMPTY;
		if(isSOTrx() && X_C_DocType.DOCBASETYPE_ARInvoice.equals(getDocType().getDocBaseType())) {
			reversal.setC_DocType_ID(MEXMEDocType.notasCreditoDebito(getCtx(), X_C_DocType.DOCBASETYPE_ARCreditMemo));
			documentName = Utilerias.getLabel("msj.numeronotacre");//=N\u00FAmero Documento Nota de Cr\u00E9dito
		}
		if (!isSOTrx() && X_C_DocType.DOCBASETYPE_APInvoice.equals(getDocType().getDocBaseType())){
			reversal.setC_DocType_ID(MEXMEDocType.notasCreditoDebito(getCtx(), X_C_DocType.DOCBASETYPE_APCreditMemo));
			documentName = Utilerias.getLabel("msj.numeronotacre");//=N\u00FAmero Documento Nota de Cr\u00E9dito
		}
		// Notas de credito
		if (isSOTrx() && X_C_DocType.DOCBASETYPE_ARCreditMemo.equals(getDocType().getDocBaseType())){
			reversal.setC_DocType_ID(MEXMEDocType.notasCreditoDebito(getCtx(), X_C_DocType.DOCBASETYPE_ARDebitMemo));
			documentName = Utilerias.getLabel("msj.numernotacargo");//=N\u00FAmero de Documento Nota Cargo
		}
		if (!isSOTrx() && X_C_DocType.DOCBASETYPE_APCreditMemo.equals(getDocType().getDocBaseType())){
			reversal.setC_DocType_ID(MEXMEDocType.notasCreditoDebito(getCtx(), X_C_DocType.DOCBASETYPE_APDebitMemo));
			documentName = Utilerias.getLabel("msj.numernotacargo");//=N\u00FAmero de Documento Nota Cargo
		}
		// Notas de debito
		if (isSOTrx() && X_C_DocType.DOCBASETYPE_ARDebitMemo.equals(getDocType().getDocBaseType())){
			reversal.setC_DocType_ID(MEXMEDocType.notasCreditoDebito(getCtx(), X_C_DocType.DOCBASETYPE_ARCreditMemo));
			documentName = Utilerias.getLabel("msj.numeronotacre");//=N\u00FAmero Documento Nota de Cr\u00E9dito
		}
		if (!isSOTrx() && X_C_DocType.DOCBASETYPE_APDebitMemo.equals(getDocType().getDocBaseType())){
			reversal.setC_DocType_ID(MEXMEDocType.notasCreditoDebito(getCtx(), X_C_DocType.DOCBASETYPE_APCreditMemo));
			documentName = Utilerias.getLabel("msj.numeronotacre");//=N\u00FAmero Documento Nota de Cr\u00E9dito
		}
		return documentName;
	}
	
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
		final StringBuffer sbuff = new StringBuffer();
		sbuff.append(getDocumentNo());
		// : Grand Total = 123.00 (#1)
		sbuff.append(": ").append(Msg.translate(getCtx(), "GrandTotal"))
				.append("=").append(getGrandTotal()).append(" (#")
				.append(getLines(false).length).append(")");// twry
		// - Description
		if (getDescription() != null && getDescription().length() > 0) {
			sbuff.append(" - ").append(getDescription());
		}
		return sbuff.toString();
	} // getSummary

	/**
	 * Get Process Message
	 *
	 * @return clear text error message
	 */
	public String getProcessMsg() {
		return mProcessMsg;
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

	/****************************** eCareSoft *******************************************/
	// TODO_ Documentar Metodo
	/**
	 *
	 * @return Facturas pendientes de pagar...
	 *
	 *         public static List<DynamicModel> getPayment()
	 *
	 *         { List<DynamicModel> lista = null; PreparedStatement pstmt =
	 *         null; ResultSet rs = null; ResultSetMetaData rsmd = null; //
	 *         Consulta Table String SQL_table =
	 *         "SELECT * FROM (SELECT i.C_Invoice_ID, i.DateInvoiced+p.NetDays AS DateDue,"
	 *         + "bp.Name, i.DocumentNo, c.ISO_Code, i.GrandTotal," +
	 *         "paymentTermDiscount(i.GrandTotal, c.C_Currency_ID, i.C_PaymentTerm_ID, i.DateInvoiced, SysDate) AS Discount,"
	 *         +
	 *         "SysDate-paymentTermDueDays(i.C_PaymentTerm_ID, i.DateInvoiced, SysDate) AS DiscountDate,"
	 *         +
	 *         "i.GrandTotal-paymentTermDiscount(i.GrandTotal, c.C_Currency_ID, i.C_PaymentTerm_ID, i.DateInvoiced, SysDate) AS DueAmount,"
	 *         +
	 *         "currencyConvert(i.GrandTotal-paymentTermDiscount(i.GrandTotal, c.C_Currency_ID, i.C_PaymentTerm_ID, SysDate, i.DateInvoiced),"
	 *         +
	 *         "i.C_Currency_ID, i.C_Currency_ID, SysDate, i.C_ConversionType_ID, i.AD_Client_ID, i.AD_Org_ID) AS PayAmt, i.c_bpartner_id "
	 *         +
	 *         "FROM C_Invoice i, C_BPartner bp, C_Currency c, C_PaymentTerm p "
	 *         + "WHERE i.IsSOTrx='N' " +
	 *         "AND i.C_BPartner_ID=bp.C_BPartner_ID " +
	 *         "AND i.C_Currency_ID=c.C_Currency_ID " +
	 *         "AND i.C_PaymentTerm_ID=p.C_PaymentTerm_ID " +
	 *         "AND i.DocStatus IN ('CO','CL') " + "AND NOT EXISTS " +
	 *         "(SELECT * FROM C_PaySelectionLine psl WHERE i.C_Invoice_ID=psl.C_Invoice_ID "
	 *         + "AND psl.C_PaySelectionCheck_ID IS NOT NULL) " +
	 *         "ORDER BY 2,3) " ; //WHERE C_BPARTNER_ID="
	 *
	 *         try{ pstmt = DB.prepareStatement(SQL_table,null); rs =
	 *         pstmt.executeQuery(); rsmd = rs.getMetaData(); lista = new
	 *         ArrayList<DynamicModel>();
	 *
	 *         while (rs.next()){ DynamicModel dynamicModel = new
	 *         DynamicModel(); for(int i = 1; i <= rsmd.getColumnCount(); i++)
	 *         dynamicModel.setValue(rsmd.getColumnName(i), rs.getString(i),
	 *         rsmd.getColumnClassName(i)); lista.add(dynamicModel); }
	 *         }catch(SQLException e){ e.printStackTrace(); }finally{
	 *         DB.close(rs, pstmt); } return lista; }
	 */
	/**
	 * Obtiene la suma de los elementos seleccionados para ser pagados
	 *
	 * @param SQLids
	 *            Lista de C_Invoice_ID
	 * @param Ctx
	 *            Contexto
	 * @return Monto Total de Elementos Seleccionados
	 *
	 *
	 *         public static BigDecimal payAmountofInvoice(String
	 *         SQLids,Properties Ctx) { StringBuilder sql = new
	 *         StringBuilder("SELECT SUM (currencyConvert ") .append(
	 *         "(i.GrandTotal-paymentTermDiscount (i.GrandTotal, i.C_Currency_ID, "
	 *         ) .append("i.C_PaymentTerm_ID, SysDate, ")
	 *         .append("i.DateInvoiced),i.C_Currency_ID, i.C_Currency_ID, SysDate, "
	 *         ) .append(
	 *         "i.C_ConversionType_ID, i.AD_Client_ID, i.AD_Org_ID)) AS PayAmt "
	 *         ) .append("From C_Invoice i ")
	 *         .append("WHERE  i.C_Invoice_ID IN ("+SQLids+ ")")
	 *         .append(MEXMELookupInfo.addAccessLevelSQL(Ctx,
	 *         " ",MInvoice.Table_Name, "i")); BigDecimal payAmount =
	 *         DB.getSQLValueBD(null, sql.toString()); return payAmount == null
	 *         ? Env.ZERO : payAmount; }
	 */
	/** Obtiene una lista de MInvoice
	 * @param where
	 *            filtro para query inicia con "AND"
	 * @param ctx
	 *            Contexto
	 * @return list
	 * @author rosy velazquez
	 * @deprecated recibe en where de parametro, pero debe ir de acuerdo a los parametros nombrepac y documentno
	 * Sera eliminado . No usar
	 * */
	public static List<MInvoice> getInvoices(final String where,
			final String join, final String DocumentNo, final String nombrePac,
			final Properties ctx) {

		final ArrayList<MInvoice> list = new ArrayList<MInvoice>();
		StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM C_Invoice ");

		if (join != null) {
			sql.append(join);
		}
		sql.append("WHERE C_Invoice.isActive= 'Y' ");

		if (where != null) {
			sql.append(where);
		}
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
		sql.append(" Order By C_Invoice.DocumentNo desc");

		ResultSet rset = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			if (DocumentNo != null) {
				pstmt.setString(1, DocumentNo);
			}

			if (nombrePac != null) {
				pstmt.setString(2, nombrePac);
				pstmt.setString(3, nombrePac);
			}

			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new MInvoice(ctx, rset, null));
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return list;
	}// getInvoices

	/**
	 * Obtiene una lista de MInvoice
	 *
	 * @param where  filtro NO debe iniciar en AND
	 * @param ctx Contexto
	 * @return list
	 * */
	public static List<MInvoice> getInvoices(final Properties ctx,
											 final StringBuilder join,
											 final String where,
											 final Object[] params) {
		return new Query(ctx, Table_Name, where, null) //
			.setJoins(join)//
			.setOnlyActiveRecords(true)//
			.setParameters(params)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" C_Invoice.Created DESC ").list();
	}// getInvoices

	/**
	 * Metodo que verifica si la factura ha sido cancelada
	 *
	 * @param ref_invoice_id
	 *            es el id de la factura a cancelar
	 * @return true si esta cancelada la factura
	 */
	public static boolean isCancelInvoice(final int ref_invoice_id) {
		String sql = "Select C_Invoice_ID from C_Invoice where ref_invoice_id=? and isActive='Y'";
		return DB.getSQLValue(null, sql, ref_invoice_id) > 0;
	}

	// Merge ADempiere trunk_rev14653
	/* Save array of documents to process AFTER completing this one */
	transient ArrayList<PO> docsPostProcess = new ArrayList<PO>();

	@SuppressWarnings("unused")
	private void addDocsPostProcess(final PO doc) {
		docsPostProcess.add(doc);
	}

	public ArrayList<PO> getDocsPostProcess() {
		return docsPostProcess;
	}

	@SuppressWarnings("unchecked")
	public void createLines(final List<?> linesFrom) {
		if (linesFrom != null && !linesFrom.isEmpty()) {
			final Object obj = linesFrom.get(0);
			if (obj instanceof MCtaPacDet) {
				charges((List<MCtaPacDet>) linesFrom);
			} else if (obj instanceof MEXMEClaimPayment) {
				claimPayment((List<MEXMEClaimPayment>) linesFrom);
			}
		}
	}

	/**
	 *
	 * @param total
	 */
	public void createLine(final BigDecimal total) {
		// Line
		final MInvoiceLine line = new MInvoiceLine(this);
		// line.setM_Product_ID(M_Product_ID)//TODO: algun prod generico ?
		line.setLine(10);
		line.setQtyInvoiced(Env.ONE);
		line.setQtyEntered(Env.ONE);

		line.setPriceList(total);// ?c
		line.setPriceLimit(total);
		line.setPriceActual(total);
		line.setPriceEntered(total);

		line.setLineTotalAmt(total);
		if (getTotalLines().compareTo(Env.ZERO) < 0) {
			setGrandTotal(Env.ZERO);
		} else {
			setGrandTotal(total.setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		// setGrandTotal(total);

		if (!line.save()) {
			throw new MedsysException("Invoice line not saved");
		}
	}

	/** (TODO: no esta probado) */
	public void charges(final List<MCtaPacDet> linesFrom) {
		int lineNum = 0;
		for (MCtaPacDet charge : linesFrom) {
			final MInvoiceLine line = new MInvoiceLine(this);
			MInvoiceLine.copyValues(charge, line);

			line.setLine(++lineNum * 10);
			line.setEXME_CtaPacDet_ID(charge.get_ID());
			// line.setQtyInvoiced(charge.getQtyEntered());
			line.setQtyInvoiced(charge.getQtyDelivered());
			line.setQtyEntered(charge.getQtyDelivered());// TODO
			line.setPriceActual(charge.getPriceList());
			line.setPriceList(charge.getPriceList());
			line.setPriceLimit(charge.getPriceLimit());
			line.setPriceEntered(charge.getPriceList());
			line.setLineTotalAmt(charge.getPriceList().multiply(
					charge.getQtyEntered()));
			// line.setPrice();// diferencias entre el precio del cargo y el
			// precio segun productPricing
			// line.setTax();// TODO ??
			setTotalLines(getTotalLines().add(line.getLineTotalAmt()));
			if (!line.save(get_TrxName())) {
				throw new MedsysException();// FIXME
			}
		}

		if (getTotalLines().compareTo(Env.ZERO) < 0) {
			setGrandTotal(Env.ZERO);
		} else {
			setGrandTotal(getTotalLines().setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		// setGrandTotal(getTotalLines());
	}

	/**
	 * Crea la lineas de la factura a partir del detalle de la respuesta de pago
	 * de la aseguradora. (TODO: no esta probado)
	 *
	 * @param linesFrom
	 */
	public void claimPayment(final List<MEXMEClaimPayment> linesFrom) {
		int lineNum = 0;

		double totalDeduc = 0.0;
		double totalCoins = 0.0;
		double total = 0.0;

		MInvoiceLine line = null;

		for (int i = 0; i < linesFrom.size(); i++) {
			final MEXMEClaimPayment detail = linesFrom.get(i);

			if (line == null) {
				line = new MInvoiceLine(this);
				copyValues(detail, line);
			}
			// Excluye las lineas de ajuste contraactual
			/*
			 * if (!MEXMEAdjustmentType.TYPE_Others.equals(detail.
			 * getEXME_AdjustmentType().getType())) { // pago + deducible +
			 * coaseguro + copago. if(detail.getC_Charge_ID() > 0){ // deducible
			 * / coaseguro / copago. total = total +
			 * detail.getAmt_Adjust().doubleValue(); } else { // pago total =
			 * total + detail.getAmt_Paid().doubleValue(); }
			 *
			 * if (MEXMEAdjustmentType.TYPE_Coinsurance.equals(detail.
			 * getEXME_AdjustmentType().getType())) { totalDeduc = totalDeduc +
			 * detail.getAmt_Adjust().doubleValue(); } else if
			 * (MEXMEAdjustmentType
			 * .TYPE_Deductible.equals(detail.getEXME_AdjustmentType
			 * ().getType())) { totalCoins = totalCoins +
			 * detail.getAmt_Adjust().doubleValue(); } }
			 */
			// crea una linea por cada producto.
			if (i == linesFrom.size() - 1
					|| line.getM_Product_ID() != linesFrom.get(i + 1)
							.getM_Product_ID()) {
				line.setLine(++lineNum * 10);
				line.setQtyInvoiced(detail.getQtyInvoiced());
				line.setQtyEntered(detail.getQtyInvoiced());

				// precio X producto.
				final BigDecimal linePrice = new BigDecimal(total).divide(
						detail.getQtyInvoiced(), 6, BigDecimal.ROUND_HALF_UP);
				line.setPriceList(linePrice);// ?
				line.setPriceLimit(linePrice);
				line.setPriceActual(linePrice);
				line.setPriceEntered(linePrice);

				line.setDeductible(new BigDecimal(totalDeduc));
				line.setCoInsurance(new BigDecimal(totalCoins));
				line.setLineTotalAmt(new BigDecimal(total));

				// line.setTaxAmt();// TODO ??
				// line.setPrice();// TODO ??
				// line.setTax();// TODO ??

				setTotalLines(getTotalLines().add(line.getLineTotalAmt()));

				if (!line.save(get_TrxName())) {
					throw new MedsysException("Invoice Line not saved");
				}
				total = 0.0;
				totalCoins = 0.0;
				totalDeduc = 0.0;
				line = null;
			}
		}
		// setGrandTotal(getTotalLines());
		if (getTotalLines().compareTo(Env.ZERO) < 0) {
			setGrandTotal(Env.ZERO);
		} else {
			setGrandTotal(getTotalLines().setScale(2, BigDecimal.ROUND_HALF_UP));
		}
	}

	/**
	 * setExtensionInvoiced
	 */
	public void setExtensionInvoiced() {
		if (!is_new() && getEXME_CtaPacExt_ID() > 0) {
			final MEXMECtaPacExt ext = new MEXMECtaPacExt(getCtx(),
					getEXME_CtaPacExt_ID(), get_TrxName());
			ext.setC_Invoice_ID(getC_Invoice_ID());
			if (!ext.save()) {
				throw new MedsysException("Extension not invoiced");
			}
		}
	}

	/**
	 * Obtenemos la cuenta paciente extension relacionada Si no existe una
	 * factura relacionada, obtendremos un template.
	 *
	 * @return
	 */
	public MEXMECtaPacExt getCtaPacExt() {

		if (mCtaPacExt == null && mCtaPacExt.getEXME_CtaPacExt_ID() > 0) {
			mCtaPacExt = new MEXMECtaPacExt(getCtx(), getEXME_CtaPacExt_ID(),
					get_TrxName());
		}
		return mCtaPacExt;
	}

	/**
	 * getMInvoice
	 *
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param C_BPartner_ID
	 * @return
	 */
	public static MInvoice getMInvoice(final Properties ctx,
			final int EXME_CtaPac_ID, final int C_BPartner_ID) {
		return new Query(ctx, Table_Name,
				"EXME_CtaPac_ID=? and C_BPartner_ID = ?", null)
				.setOnlyActiveRecords(true)
				.setParameters(EXME_CtaPac_ID, C_BPartner_ID).first();
	}

	/**
	 * Obtenemos la cuenta paciente relacionada Si no existe una factura
	 * relacionada, obtendremos un template.
	 *
	 * @return
	 */
	public MEXMECtaPac getCtaPac() {

		if (mCtapac == null || mCtapac.getEXME_CtaPacExt_ID() == 0) {
			mCtapac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(),
					get_TrxName());
		}

		return mCtapac;
	}

	/**
	 * Monto de inpuesto
	 *
	 * @return
	 */
	public BigDecimal getTaxAmt() {
		BigDecimal bDTaxAmt = Env.ZERO;
		
		if(super.getTaxAmt().compareTo(Env.ZERO)==0){
			bDTaxAmt = getGrandTotal().subtract(getTotalLines());
		} else {
			bDTaxAmt = super.getTaxAmt();
		}
		
		if (bDTaxAmt.scale() > getPrecision()){
			bDTaxAmt = bDTaxAmt.setScale(getPrecision(),BigDecimal.ROUND_HALF_UP);
		}
		
		return bDTaxAmt;
	}

	/**
	 * Obtenemos el cliente asociado a la factura
	 *
	 * @return
	 */
	public MBPartner getCliente() {

		if (mCliente != null) {
			return mCliente;
		}

		if (getC_BPartner_ID() <= 0) {
			return null;
		}

		mCliente = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());

		return mCliente;

	}

	/**
	 * Establecemos el cliente de la factura
	 *
	 * @param cleinte
	 *            el cliente MBPartner a establecer a la factura
	 */
	public void setCliente(final MBPartner cliente) {
		mCliente = cliente;
	}

	/**
	 * Multiple
	 *
	 * @return
	 */
	public MInvoice getMultiple() {
		return new MInvoice(getCtx(), getMultiple_ID(), get_TrxName());
	}

	/**
	 * a) Pacientes de Hospital (inpatient) - la Org Trx que se guarde en la
	 * factura debe ser la que tenga asignada el tipo de paciente (no la de cada
	 * service unit). b) Pacientes de Ambulatorio (outpatient) - la Org Trx que
	 * se guarde en la factura será la que tenga el Service Unit la del
	 * paciente.
	 *
	 * En la tabla EXME_TipoPaciente, el campo TipoArea indica si es OutPatient
	 * para que tome la orgtrx del service unit (los tipos de pacientes
	 * Outpatient y NonPatient son los únicos con el TipoArea OutPatient). En el
	 * caso del tipo de paciente llamado ER, el TipoArea es ER el cual puede
	 * tener diferentes tipos de estudios/servicios de diferentes service units,
	 * por lo que no podemos manejarlo como OutPatient, sino como Inpatient.
	 */
	public void setADOrgTrxID(final MCtaPacDet charge) {
		if (getAD_OrgTrx_ID() == 0 && charge.getCtaPac() != null) {
			if (charge.getCtaPac().getTipoPaciente() != null
					&& charge.getCtaPac().getTipoPaciente().getAD_OrgTrx_ID() > 0
					&& charge.getCtaPac().getTipoArea() != null
					&& (charge.getCtaPac().getTipoArea()
							.equals(X_EXME_CtaPac.TIPOAREA_Hospitalization) || charge
							.getCtaPac().getTipoArea()
							.equals(X_EXME_CtaPac.TIPOAREA_Emergency))) {
				setAD_OrgTrx_ID(charge.getCtaPac().getTipoPaciente()
						.getAD_OrgTrx_ID());
			} else if (charge.getCtaPac().getEstServ() != null) {
				setAD_OrgTrx_ID(charge.getCtaPac().getEstServ()
						.getAD_OrgTrx_ID());
			}
		}

		if (getAD_OrgTrx_ID() == 0 && Env.getAD_OrgTrx_ID(getCtx()) > 0) {
			setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(getCtx()));
		}
	}

	/**
	 * Metodo que regresa la factura de un socio de negocios determinado segun
	 * la cuenta paciente y el tipo de aseguradora
	 **/
	public static int getByBPartner(final Properties ctx,
			final String billingType, final int C_BPartner_ID,
			final int EXME_CtaPac_ID, final String trxName) {
		String sql = " SELECT C_Invoice_ID FROM C_Invoice WHERE C_BPartner_ID=? AND EXME_CtaPac_ID=? AND ConfType = ? ";
		return DB.getSQLValue(trxName, sql, C_BPartner_ID, EXME_CtaPac_ID,
				billingType);
	}

	/**
	 * Obtenemos si la cuenta paciente a generado sus facturas
	 *
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param EXME_CtaPac_ID
	 *            El identificador de la cuenta paciente
	 * @param trxName
	 *            El nombre de la transaccion
	 * @return hasInvoice
	 */
	public static boolean hasInvoice(final Properties ctx,
			final int EXME_CtaPac_ID, final String trxName) {
		boolean hasInvoice = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		try {

			sql.append("SELECT * FROM C_INVOICE I ")
					.append("INNER JOIN EXME_CTAPAC CTA ON  CTA.EXME_CTAPAC_ID = I.EXME_CTAPAC_ID ")
					.append("INNER JOIN C_DOCTYPE T ON  I.C_DOCTYPETARGET_ID = T.C_DOCTYPE_ID AND ")
					.append("(T.DOCBASETYPE = ")
					.append(DB.TO_STRING(MDocType.DOCBASETYPE_ARInvoice))
					.append(" OR t.DOCBASETYPE = ")
					.append(DB.TO_STRING(MDocType.DOCBASETYPE_ARDebitMemo))
					.append(") WHERE CTA.EXME_CTAPAC_ID = ? ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_CtaPac_ID);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				hasInvoice = true;
			} else {
				hasInvoice = false;
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return hasInvoice;
	}

	/**
	 * Factura de tipo, factura, socio, cuenta
	 *
	 * @param ctx
	 * @param billingType
	 * @param C_BPartner_ID
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public final static int getOfBPartner(final Properties ctx,
			final String billingType, final int C_BPartner_ID,
			final int EXME_CtaPac_ID, final String trxName) {
		final StringBuilder where = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		where.append(" C_BPartner_ID = ? AND EXME_CtaPac_ID = ? AND ConfType = ?  ");
		where.append(" AND C_DocType_ID IN ( SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID = ? AND DocBaseType IN ( ?,?) )  ");
		return new Query(ctx, Table_Name, where.toString(), trxName)//
				.setParameters(C_BPartner_ID, EXME_CtaPac_ID, billingType,
						Env.getAD_Client_ID(ctx),
						MDocType.DOCBASETYPE_ARInvoice,
						MDocType.DOCBASETYPE_ARDebitMemo)//
				.setOnlyActiveRecords(true).addAccessLevelSQL(true).firstId();
	} // getOfBPartner

	/**
	 * getGrandTotal
	 */
	public BigDecimal getGrandTotal() {
		if (super.getGrandTotal().scale() > getPrecision()){
			return super.getGrandTotal().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP);
		} else {
			return super.getGrandTotal();
		}
	}
	
	public BigDecimal getTotalLines() {
		if (super.getTotalLines().scale() > getPrecision()){
			return super.getTotalLines().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP);
		} else {
			return super.getTotalLines();
		}
	}

	public static BigDecimal[] getTotals(final Properties ctx, final String whereClause, final List<Object> params, int cTaxID, String trxname){
		BigDecimal[] ret = new BigDecimal[3];

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);


		sql.append(" SELECT SUM(t.taxbaseamt) AS subtotal,SUM(t.taxamt) AS impuesto, SUM(t.taxbaseamt+t.taxamt) as total ");
		sql.append(" FROM C_Invoice ");
		if(cTaxID > 0){
			sql.append(" INNER JOIN C_INVOICETAX t on (t.c_invoice_id = C_Invoice.C_Invoice_ID and t.c_tax_id = ").append(cTaxID).append(" )");
		}else{
			sql.append(" INNER JOIN C_INVOICETAX t on (t.c_invoice_id = C_Invoice.C_Invoice_ID )");
		}
		sql.append(" WHERE ( ");
		sql.append(whereClause);
		sql.append(" ) AND C_Invoice.IsActive='Y'  ");
		sql.append(" AND t.AD_Client_ID = ").append(Env.getAD_Client_ID(ctx));
		sql.append(" AND t.AD_Org_ID = ").append(Env.getAD_Org_ID(ctx));
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", "C_Invoice"));

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxname);
			int index = 1;
			for(Object param : params){
				pstmt.setTimestamp(index, (Timestamp)param);
				index++;
			}

			rset = pstmt.executeQuery();
			BigDecimal subtotal = BigDecimal.ZERO;
			BigDecimal impuesto = BigDecimal.ZERO;
			BigDecimal total = BigDecimal.ZERO;
			if (rset.next()) {
				subtotal = (BigDecimal)rset.getBigDecimal(1) != null ? (BigDecimal)rset.getBigDecimal(1) : BigDecimal.ZERO;
				impuesto = (BigDecimal)rset.getBigDecimal(1) != null ? (BigDecimal)rset.getBigDecimal(2) : BigDecimal.ZERO;
				total = (BigDecimal)rset.getBigDecimal(1) != null ? (BigDecimal)rset.getBigDecimal(3) : BigDecimal.ZERO;
			}
			ret[0] = subtotal;
			ret[1] = impuesto;
			ret[2] = total;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(rset, pstmt);
		}
		return ret;
	}

	public static String getLabelNRs(final Properties ctx, final String whereClause, final List<Object> params, int cTaxID, String trxname){
		String ret = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT array_to_string(array_agg(documentno), ',') as texto ");
		sql.append(" FROM C_Invoice ");
		if(cTaxID > 0){
			sql.append(" INNER JOIN C_INVOICETAX t on (t.c_invoice_id = C_Invoice.C_Invoice_ID and t.c_tax_id = ").append(cTaxID).append(" )");
		}else{
			sql.append(" INNER JOIN C_INVOICETAX t on (t.c_invoice_id = C_Invoice.C_Invoice_ID )");
		}
		sql.append(" WHERE ( ");
		sql.append(whereClause);
		sql.append(" ) AND C_Invoice.IsActive='Y'  ");
		sql.append(" AND t.AD_Client_ID = ").append(Env.getAD_Client_ID(ctx));
		sql.append(" AND t.AD_Org_ID = ").append(Env.getAD_Org_ID(ctx));
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", "C_Invoice"));

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxname);
			int index = 1;
			for(Object param : params){
				pstmt.setTimestamp(index, (Timestamp)param);
				index++;
			}

			rset = pstmt.executeQuery();

			if (rset.next()) {
				ret = rset.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(rset, pstmt);
		}
		return ret;
	}


	public static int updateNRs(final Properties ctx, final String whereClause, final List<Object> params, int cInvoiceID, String trxname){
		int ret = -1;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" UPDATE C_Invoice  SET multiple_id = ?");
		sql.append(" WHERE ( ");
		sql.append(whereClause);
		sql.append(" ) AND C_Invoice.IsActive='Y'  ");


		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", "C_Invoice"));

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxname);
			pstmt.setInt(1, cInvoiceID);

			int index = 2;
			for(Object param : params){
				pstmt.setTimestamp(index, (Timestamp)param);
				index++;
			}

			ret = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(rset, pstmt);
		}
		return ret;
	}

	public static int reversUpdateNRs(final Properties ctx, int cInvoiceID, String trxName){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" UPDATE C_Invoice          ");
		sql.append(" SET    multiple_id = null ");
		sql.append(" WHERE  multiple_id = ?    ");
		sql.append(" AND    IsActive='Y'  ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", "C_Invoice"));

		// se actualiza la referencia de los allocation y los cash directos a la factura
		return DB.executeUpdate(sql.toString(),
				new Object[] {cInvoiceID}, trxName);
	}

	public static final String FACTURA = "FACTURA";
	
	public static MInvoice getInvoice(final Properties ctx, final String docInv, final String docBaseType, final boolean factElect) {
		final StringBuilder sql = new StringBuilder("SELECT * FROM C_Invoice ")
		.append(" WHERE  IsActive= 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", "C_Invoice"))
		.append(factElect?" AND    Afecta_Caja  = 'Y' ":"")
		.append(" AND    DocumentNo   = ?   ")
		.append(" AND    docstatus in ('CO', 'IP')  ")
		.append(" AND    C_DocType_ID IN (  ")
		.append("                     SELECT C_DocType_ID ")
		.append("                     FROM   C_DocType ")
		.append("                     WHERE  IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", "C_DocType"))
		.append("                     AND    DocBaseType IN ( ? ) ")
		.append(" ) ");

		MInvoice invoice = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, docInv);
			pstmt.setString(2, docBaseType);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				invoice = new MInvoice(ctx, rset, null);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return invoice;
	}

	/**
	 * getInvoice
	 *
	 * @param ctx
	 * @param docInv
	 * @param docBaseType 'ARI' (Remision),'ARC'(Nota de credito), 'FACTURA'(Factura)
	 * @return
	 */
	public static MInvoice getInvoiceAsDocument(final Properties ctx, final String docInv, final String docBaseType, final boolean factElect) {
		
		final StringBuilder sql = new StringBuilder("SELECT * ")
		.append(" FROM C_Invoice i ")
		.append(" INNER JOIN C_DocType dt  ON dt.C_DocType_ID = i.C_DocType_ID ")
		.append("                         AND dt.DocBaseType IN ( ? )  ")// ARI, ARC
		
		.append(FACTURA.equals(docBaseType)
				?" AND dt.DocSubTypeSO = 'SR' "
						:" AND dt.DocSubTypeSO IS NULL ")
		
		.append(" WHERE  i.IsActive= 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", "C_Invoice", "i"))
		.append(factElect
				?" AND    i.Afecta_Caja  = 'Y' "
						:"")
		.append(" AND    i.DocumentNo   = ?   ")
		.append(" AND    i.docstatus in ('CO', 'IP', 'CL')  ");
		

		MInvoice invoice = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, docInv);
			pstmt.setString(2, docBaseType);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				invoice = new MInvoice(ctx, rset, null);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return invoice;
	}

	/**
	 * Se necesita saber si una factura fue relacionada a la cita, cita que es
	 * parte del concepto de la factura
	 *
	 * @return El objeto de la cita que esta relacionada a la factura
	 */
	public MEXMECitaMedica getCitaMedica() {

		MEXMECitaMedica citaMedica = null;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		sql.append(
				" SELECT * FROM EXME_CitaMedica WHERE IsActive = 'Y' AND C_Invoice_Id = ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
						"EXME_CitaMedica"));

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Invoice_ID());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				citaMedica = new MEXMECitaMedica(getCtx(), rset, null);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getEjecucion", e);
		} finally {
			DB.close(rset, pstmt);
		}

		return citaMedica;
	}

	/**
	 * @return
	 */
	public String getStrDateInvoiced() {
		if (getDateInvoiced() != null) {
			strDateInvoiced = stringDate.format(getDateInvoiced());
		}
		return strDateInvoiced;
	}

	/**
	 * @return
	 */
	public void setStrDateInvoiced(final String strDate) {
		strDateInvoiced = strDate;
		try {
			setDateInvoiced(new Timestamp(stringDate.parse(strDateInvoiced)
					.getTime()));
		} catch (ParseException pe) {
			log.log(Level.SEVERE, "MEXMEInvoice.setStrDateInvoiced", pe);
		}
	}

	/**
	 * suma de la venta (solo cargos sin CCCmD ni desc.)
	 *
	 * @param lines
	 */
	public void getTotalVnta(final MInvoiceLine[] lines) {
		totalVta = Env.ZERO;

		if (lines == null || lines.length == 0) {
			return;
		}

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].getM_Product_ID() > 0
					&& lines[i].getProduct().isProduct()) {
				totalVta = totalVta.add(lines[i].getLineNetAmt());
			}
		}// fin for
	}

	public void setBasesGravAseg(Map<Integer, BigDecimal> basesGravAseg) {
		this.basesGravAseg = basesGravAseg;
	}

	/**************************************************************************
	 * Calculate/Set Tax Base Amt from Invoice Lines
	 */
	public List<MInvoiceLine> calculateTaxFromLinesCCCmD() {
		final MConfigPre configPre = MConfigPre.get(getCtx(), get_TrxName());
		linesNotMatch.clear();
		
		// Sin CCCmD ni descuento
		final StringBuilder sql = new StringBuilder().append(" SELECT * ")
				.append(" FROM C_InvoiceLine ")
				.append(" WHERE C_Invoice_ID=? ")
				.append(" AND ( C_Charge_ID > 0 ")
				.append(" OR M_Product_ID IN ( ")
				.append(configPre.getCoaseguro_ID()).append(", ")
				.append(configPre.getDeducible_ID()).append(", ")
				.append(configPre.getCoaseguroMed_ID()).append(", ")
				.append(configPre.getCoPago_ID()).append(")) ");

		//
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Invoice_ID());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				linesNotMatch.add(new MInvoiceLine(getCtx(), rset,
						get_TrxName()));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "setTaxBaseAmt", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return linesNotMatch;
	} // calculateTaxFromLines

	/**
	 * Primer asignacion de la factura
	 *
	 * @return
	 */
	public MAllocationHdr getAllocation() {
		MAllocationHdr retValue = null;
		StringBuilder sql = new StringBuilder()
				.append(" SELECT * ")
				.append(" FROM  C_AllocationLine al")
				.append(" INNER JOIN C_AllocationHdr ah ON (al.C_AllocationHdr_ID=ah.C_AllocationHdr_ID)")
				.append(" INNER JOIN C_Invoice        i ON (al.C_Invoice_ID=i.C_Invoice_ID) ")
				.append(" WHERE al.IsActive='Y'     ")
				.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
						X_C_Invoice.Table_Name, "i"))
				.append(" AND   al.C_Invoice_ID = ? ")
				.append(" AND   ah.IsActive='Y'     ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Invoice_ID());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				retValue = new MAllocationHdr(getCtx(), rset, get_TrxName());
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}
		return retValue;
	}

	/*** @deprecated no tiene comentarios ni referencias en codigo */
	public static List<BeanInvoiceXLS> getAllInfo(String trxName,
			Timestamp fechaIni, Timestamp fechaFin) {
		List<BeanInvoiceXLS> retValue = new ArrayList<BeanInvoiceXLS>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder()
				.append("SELECT  ")
				.append("cp.DocumentNo  ctaPacNo, cbp.value as CVECTE, i.Description as CLIENTE, i.docstatus as Status, ")
				.append("cp.dateordered as INGRESO, cp.fechaalta as ALTA, ")
				.append("CASE ")
				.append("WHEN i.Nombre_Paciente IS NULL ")
				.append("THEN i.Nombre_Paciente ")
				.append("WHEN i.Nombre_Paciente IS NOT NULL ")
				.append("THEN i.Nombre_Paciente ")
				.append("END            paciente, ")
				.append("i.Nombre_Medico as medico, ")
				.append("CASE WHEN pd.M_Product_ID is not null THEN  pd.name ELSE ch.name END Producto, ")
				.append("i.documentno as FACTURANO, i.DateInvoiced dateFac ")
				.append(",il.PriceList * il.qtyinvoiced linenetamt ")
				.append(", i.TotalLines, il.taxamt , i.GrandTotal GRANDTOTAL ")
				.append(",quiro.fechainicio as inicio, quiro.fechafin as fin, quiro.duracion  as duracion, serv.name as estacion ")

				.append(",coalesce((select sum(ln.linetotalamt) FROM c_invoiceline ln  ")
				.append("inner join m_product xr on ln.m_product_id = xr.m_product_id and xr.PRODUCTCLASS = 'XR'  ")
				.append("where ln.c_invoice_id = i.c_invoice_id  ")
				.append("),0) XRAY  ")
				.append(",coalesce((select sum(ln.linetotalamt) FROM c_invoiceline ln  ")
				.append("inner join m_product lb on ln.m_product_id = lb.m_product_id and lb.PRODUCTCLASS = 'LA'  ")
				.append("where ln.c_invoice_id = i.c_invoice_id  ")
				.append("),0) LAB  ")

				.append("FROM C_Invoice i ")
				.append("LEFT JOIN EXME_CtaPac cp ON (I.Exme_ctapac_Id = cp.Exme_ctapac_Id) ")
				.append("LEFT JOIN EXME_Paciente p On (P.Exme_Paciente_Id = I.Exme_Paciente_Id) ")
				.append("LEFT JOIN exme_anticipo ant on ant.exme_ctapacext_id = cp.exme_ctapacext_id ")
				.append("inner join c_bpartner cbp on i.c_bpartner_id = cbp.c_bpartner_id ")
				.append("left join exme_progquiro quiro on (I.Exme_Paciente_Id = quiro.Exme_Paciente_Id) ")
				.append("inner join c_invoiceline il on i.c_invoice_id = il.c_invoice_id ")
				.append("left JOIN M_Product pd ON (Pd.M_Product_Id = Il.M_Product_Id) ")
				.append("left join c_charge ch ON (ch.c_charge_id = il.c_charge_id) ")
				.append("left join exme_estserv serv on (cp.exme_estserving_id = serv.exme_estserv_id) ")
				.append("WHERE I.Isactive = 'Y' ")
				.append(" AND i.DateInvoiced between ? AND ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ",
						X_C_Invoice.Table_Name, "i"))
				.append("ORDER BY i.created desc,i.C_Invoice_ID ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setTimestamp(1, fechaIni);
			pstmt.setTimestamp(2, fechaFin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BeanInvoiceXLS bean = new BeanInvoiceXLS();

				bean.setCtaPac(rs.getString("ctaPacNo"));
				bean.setValueCte(rs.getString("CVECTE"));
				bean.setNameCte(rs.getString("CLIENTE"));
				bean.setIngreso(rs.getTimestamp("INGRESO"));
				bean.setEgreso(rs.getTimestamp("ALTA"));
				bean.setPacienteName(rs.getString("paciente"));
				bean.setMedicoName(rs.getString("medico"));
				bean.setDetalle(rs.getString("Producto"));
				bean.setFacturaNo(rs.getString("FACTURANO"));
				bean.setFechaFac(rs.getTimestamp("dateFac"));
				bean.setImporte(rs.getBigDecimal("linenetamt"));
				bean.setIva(rs.getBigDecimal("taxamt"));
				bean.setTotal(rs.getBigDecimal("GRANDTOTAL"));
				bean.setStatus(MRefList.getListName(Env.getCtx(),
						MInvoice.DOCSTATUS_AD_Reference_ID,
						rs.getString("Status")));
				bean.setIngresoQuir(rs.getTimestamp("inicio"));
				bean.setEgresoQuir(rs.getTimestamp("fin"));
				bean.setEstIngreso(rs.getString("estacion"));
				bean.setDuracion(rs.getBigDecimal("duracion"));
				bean.setxRay(rs.getBigDecimal("XRAY"));
				bean.setLab(rs.getBigDecimal("LAB"));

				retValue.add(bean);
			}
		} catch (Exception e) {
			 sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}
	/*** @deprecated no tiene comentarios ni referencias en codigo */
	public static List<BeanInvoiceXLS> getAllInfoFilter(String trxName,
			Timestamp fechaIni, Timestamp fechaFin) {
		List<BeanInvoiceXLS> retValue = new ArrayList<BeanInvoiceXLS>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder()
				.append("(SELECT  cp.DocumentNo  ctaPacNo, cbp.value as CVECTE, i.Description as CLIENTE, i.docstatus as Status,  ")
				.append("cp.dateordered as INGRESO, cp.fechaalta as ALTA,  ")
				.append("CASE   ")
				.append("WHEN i.Nombre_Paciente IS NULL  ")
				.append("THEN i.Nombre_Paciente  ")
				.append("WHEN i.Nombre_Paciente IS NOT NULL  ")
				.append("THEN i.Nombre_Paciente END            paciente,  ")
				.append("i.Nombre_Medico as medico, pr.name Producto,  ")
				.append("i.documentno as FACTURANO, i.DateInvoiced dateFac  ")
				.append(", i.TotalLines subtotal,  ")
				.append("coalesce((select sum(il.taxamt)  ")
				.append("from c_invoiceline il where il.c_invoice_id = i.c_invoice_id),0) IVA  ")
				.append(",i.GrandTotal GRANDTOTAL ,quiro.fechainicio as inicio, quiro.fechafin as fin,  ")
				.append("quiro.duracion  as duracion, serv.name  as estacion")

				.append(",coalesce((select sum(ln.linetotalamt) FROM c_invoiceline ln  ")
				.append("inner join m_product xr on ln.m_product_id = xr.m_product_id and xr.PRODUCTCLASS = 'XR'  ")
				.append("where ln.c_invoice_id = i.c_invoice_id  ")
				.append("),0) XRAY  ")
				.append(",coalesce((select sum(ln.linetotalamt) FROM c_invoiceline ln  ")
				.append("inner join m_product lb on ln.m_product_id = lb.m_product_id and lb.PRODUCTCLASS = 'LA'  ")
				.append("where ln.c_invoice_id = i.c_invoice_id  ")
				.append("),0) LAB  ")

				.append("FROM C_Invoice i  ")
				.append("LEFT JOIN EXME_CtaPac cp ON (I.Exme_ctapac_Id = cp.Exme_ctapac_Id)  ")
				.append("LEFT JOIN EXME_Paciente p On (P.Exme_Paciente_Id = I.Exme_Paciente_Id)   ")
				.append("inner join c_bpartner cbp on i.c_bpartner_id = cbp.c_bpartner_id  ")
				.append("left join exme_progquiro quiro on (I.Exme_Paciente_Id = quiro.Exme_Paciente_Id)  ")
				.append("inner join exme_ctapacdet det on I.exme_ctapac_id = det.exme_ctapac_id and det.tipolinea = 'PB'  ")
				.append("inner join m_product pr on det.m_product_id = pr.m_product_id and pr.ProductType = 'P'  ")
				.append("left join exme_estserv serv on (cp.exme_estserving_id = serv.exme_estserv_id) ")
				.append("WHERE I.Isactive = 'Y' AND i.DateInvoiced between ? AND ?  )")
				.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ",
						X_C_Invoice.Table_Name, "i"))

				.append(" UNION  ")

				.append("(SELECT   ")
				.append("cp.DocumentNo  ctaPacNo, cbp.value as CVECTE, i.Description as CLIENTE, i.docstatus as Status,  ")
				.append("cp.dateordered as INGRESO, cp.fechaalta as ALTA,  ")
				.append("CASE  ")
				.append("WHEN i.Nombre_Paciente IS NULL  ")
				.append("THEN i.Nombre_Paciente  ")
				.append("WHEN i.Nombre_Paciente IS NOT NULL  ")
				.append("THEN i.Nombre_Paciente  ")
				.append("END            paciente,   ")
				.append("i.Nombre_Medico as medico,	  ")
				.append("				CASE WHEN pd.M_Product_ID is not null THEN  pd.name ELSE ch.name END Producto,  ")
				.append("i.documentno as FACTURANO, i.DateInvoiced dateFac  ")
				.append(",il.PriceList * il.qtyinvoiced  subtotal,  ")
				.append("il.taxamt as IVA,  (il.PriceList * il.qtyinvoiced) + il.taxamt GRANDTOTAL  ")
				.append(",quiro.fechainicio as inicio, quiro.fechafin as fin, quiro.duracion  as duracion, serv.name  as estacion")

				.append(",coalesce((select sum(ln.linetotalamt) FROM c_invoiceline ln  ")
				.append("inner join m_product xr on ln.m_product_id = xr.m_product_id and xr.PRODUCTCLASS = 'XR'  ")
				.append("where ln.c_invoice_id = i.c_invoice_id  ")
				.append("),0) XRAY  ")
				.append(",coalesce((select sum(ln.linetotalamt) FROM c_invoiceline ln  ")
				.append("inner join m_product lb on ln.m_product_id = lb.m_product_id and lb.PRODUCTCLASS = 'LA'  ")
				.append("where ln.c_invoice_id = i.c_invoice_id  ")
				.append("),0) LAB  ")

				.append("FROM C_Invoice i  ")
				.append("LEFT JOIN EXME_CtaPac cp ON (I.Exme_ctapac_Id = cp.Exme_ctapac_Id)  ")
				.append("LEFT JOIN EXME_Paciente p On (P.Exme_Paciente_Id = I.Exme_Paciente_Id)  ")
				.append("inner join c_bpartner cbp on i.c_bpartner_id = cbp.c_bpartner_id  ")
				.append("left join exme_progquiro quiro on (I.Exme_Paciente_Id = quiro.Exme_Paciente_Id)  ")
				.append("inner join c_invoiceline il on i.c_invoice_id = il.c_invoice_id  ")
				.append("left JOIN M_Product pd ON (Pd.M_Product_Id = Il.M_Product_Id)  ")
				.append("left join c_charge ch ON (ch.c_charge_id = il.c_charge_id)  ")
				.append("left join exme_ctapacdet det on I.exme_ctapac_id = det.exme_ctapac_id and det.tipolinea IN ('CG', 'MP', 'CO', 'DE', 'DS','AJ', 'LD', 'EX', 'CP', 'CM' )")
				.append("left join exme_estserv serv on (cp.exme_estserving_id = serv.exme_estserv_id) ")
				.append("WHERE I.Isactive = 'Y'  ")
				.append(" AND i.DateInvoiced between ? AND ?  ")
				.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ",
						X_C_Invoice.Table_Name, "i"));

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setTimestamp(1, fechaIni);
			pstmt.setTimestamp(2, fechaFin);
			pstmt.setTimestamp(3, fechaIni);
			pstmt.setTimestamp(4, fechaFin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BeanInvoiceXLS bean = new BeanInvoiceXLS();

				bean.setCtaPac(rs.getString("ctaPacNo"));
				bean.setValueCte(rs.getString("CVECTE"));
				bean.setNameCte(rs.getString("CLIENTE"));
				bean.setIngreso(rs.getTimestamp("INGRESO"));
				bean.setEgreso(rs.getTimestamp("ALTA"));
				bean.setPacienteName(rs.getString("paciente"));
				bean.setMedicoName(rs.getString("medico"));
				bean.setDetalle(rs.getString("Producto"));
				bean.setFacturaNo(rs.getString("FACTURANO"));
				bean.setFechaFac(rs.getTimestamp("dateFac"));
				bean.setImporte(rs.getBigDecimal("subtotal"));
				bean.setIva(rs.getBigDecimal("taxamt"));
				bean.setTotal(rs.getBigDecimal("GRANDTOTAL"));
				bean.setStatus(MRefList.getListName(Env.getCtx(),
						MInvoice.DOCSTATUS_AD_Reference_ID,
						rs.getString("Status")));
				bean.setIngresoQuir(rs.getTimestamp("inicio"));
				bean.setEgresoQuir(rs.getTimestamp("fin"));
				bean.setDuracion(rs.getBigDecimal("duracion"));
				bean.setxRay(rs.getBigDecimal("XRAY"));
				bean.setLab(rs.getBigDecimal("LAB"));

				retValue.add(bean);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}

	/**
	 * Crea la factura
	 *
	 * @param invoicehdr
	 * @param lstPayments
	 * @param C_BPartner_ID
	 * @param ctapac
	 */
	public void toInvoiceLines(Properties ctx,
			List<MEXMEClaimPayment> lstPayments, String trxName) {// T,P

		// Itera cada pago para que sea la linea de la nota de debito
		// (C_Invoice)
		if (getC_Invoice_ID() > 0) {
			for (int i = 0; i < lstPayments.size(); i++) {

				MInvoiceLine line = new MInvoiceLine(ctx, 0, trxName);
				line.setLine(i);
				line.setC_Invoice_ID(getC_Invoice_ID());
				line.setDescription(""
						+ lstPayments.get(i).getEXME_ClaimPayment_ID());
				line.setC_Charge_ID(lstPayments.get(i).getC_Charge_ID());
				if (lstPayments.get(i).getCharge() != null
						&& lstPayments.get(i).getCharge().getTaxID() > 0) {
					line.setC_Tax_ID(lstPayments.get(i).getCharge().getTaxID());
				} else {
					line.setC_Tax_ID(MEXMETax.getDefaultTaxID(ctx, trxName));
				}
				line.setQtyInvoiced(Env.ONE);
				line.setPrice(lstPayments.get(i).getAmt_Paid());
				line.setPriceLimit(lstPayments.get(i).getAmt_Paid());
				line.setPriceList(lstPayments.get(i).getAmt_Paid());
				line.setLineNetAmt();
				line.setTaxAmt();
				line.setLineTotalAmt(line.getLineNetAmt().add(line.getTaxAmt()));
				line.setProcessed(false);
				line.setQtyEntered(Env.ONE);
				line.setPriceEntered(lstPayments.get(i).getAmt_Paid());

				// Guardar la linea
				if (!line.save()) {
					throw new MedsysException();
				}
			}
		}
	}

	/**
	 *
	 * @return
	 */
	public MPaymentTerm getPaymentTerm() {
		if (mPaymentTerm == null && getC_PaymentTerm_ID() > 0) {
			mPaymentTerm = new MPaymentTerm(getCtx(), getC_PaymentTerm_ID(),
					get_TrxName());
		}
		return mPaymentTerm;
	}

	// public BigDecimal[] invoiceOpenDiscount(final int
	// C_InvoicePaySchedule_ID, final Timestamp ts){
	// String sql = "SELECT C_BPartner_ID,C_Currency_ID," // 1..2
	// + " invoiceOpen(C_Invoice_ID, ?)," // 3 #1
	// + " invoiceDiscount(C_Invoice_ID,?,?), IsSOTrx " // 4..5 #2/3
	// + "FROM C_Invoice WHERE C_Invoice_ID=?"; // #4
	// BigDecimal discount[] = null;
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// try
	// {
	// pstmt = DB.prepareStatement(sql, null);
	// pstmt.setInt(1, C_InvoicePaySchedule_ID);
	// pstmt.setTimestamp(2, ts);
	// pstmt.setInt(3, C_InvoicePaySchedule_ID);
	// pstmt.setInt(4, getC_Invoice_ID());
	// rs = pstmt.executeQuery();
	// if (rs.next()){
	// BigDecimal InvoiceOpen = rs.getBigDecimal(3); // Set Invoice OPen Amount
	// if (InvoiceOpen == null)
	// InvoiceOpen = Env.ZERO;
	//
	// BigDecimal DiscountAmt = rs.getBigDecimal(4); // Set Discount Amt
	// if (DiscountAmt == null){
	// DiscountAmt = Env.ZERO;
	// }
	// discount = new BigDecimal[]{InvoiceOpen, DiscountAmt};
	// }
	// }
	// catch (SQLException e)
	// {
	// log.log(Level.SEVERE, sql, e);
	// discount = new BigDecimal[]{Env.ZERO, Env.ZERO};
	// } finally {
	// DB.close(rs, pstmt);
	// }
	//
	// return discount;
	// }


	/**
	 * Relacion de la extension facturada y la factura
	 *
	 * @param ctx
	 * @param pCtaPacExtID
	 * @param newInvoiceId
	 * @param trxName
	 * @return
	 */
	public String extensiones(final Properties ctx, final int pCtaPacExtID,
			final int newInvoiceId, final String trxName) {
		String errores = "";
		if (pCtaPacExtID > 0) {
			final MEXMECtaPacExt extn = new MEXMECtaPacExt(ctx, pCtaPacExtID,
					trxName);
			extn.setC_Invoice_ID(newInvoiceId);
			if (!extn.save(trxName)) {
				errores = errores.concat(Utilerias
						.getLabel("error.caja.prefactura.noSave"));

			}
		}
		return errores;
	}

	/**
	 * Relacion de nueva factura y la cita medica
	 *
	 * @param ctx
	 * @param oldInvoiceId
	 * @param newInvoiceId
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public String citaMedica(final Properties ctx, final int oldInvoiceId,
			final int newInvoiceId, final String trxName) throws SQLException {
		String errores = "";
		// Actualizamos la cita se asigna el ID de la nueva factura.
		final MEXMECitaMedica cita = MEXMECitaMedica.getForInvoice(ctx,
				oldInvoiceId, trxName);
		if (cita != null && cita.getC_Invoice_ID() > 0) {
			cita.setC_Invoice_ID(newInvoiceId);
			if (!cita.save(trxName)) {
				errores = errores.concat(Utilerias
						.getLabel("error.caja.prefactura.noSave"));
			}
		}

		return errores;
	}

	/**
	 * Relacionar embarque con nueva factura
	 *
	 * @param ctx
	 * @param oldInvoiceId
	 * @param newInvoiceId
	 * @param trxName
	 * @return
	 */
	public String embarque(final Properties ctx, final int oldInvoiceId,
			final int newInvoiceId, final String trxName) {
		String errores = "";
		// Traer el embarque relacionado a la factura
		final MInOut original = MEXMEInOut.getOfInvoice(ctx, oldInvoiceId,
				trxName);
		// Si encontro un embarque
		if (original != null) {
			// asociar la nueva prefactura al embarque original
			original.setC_Invoice_ID(newInvoiceId);
			// se cambia parametro correcto de nombre de transaccion.
			// Jesus Cantu.
			if (!original.save(trxName)) {
				errores = errores.concat(Utilerias.getLabel(
						"error.factDirecta.facturar.noSaveInOut",
						String.valueOf(original.getDocumentNo())));
			}
		}

		return errores;
	}

	/**
	 * Generacion de nota de credito
	 *
	 * @param ctx
	 * @param oldInvoice
	 * @param C_Cash_ID
	 * @param C_CashBook_ID
	 * @param motivoCancel
	 * @param motivoCancelid
	 * @param dateFactura
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public MInvoice notaCredito(final Properties ctx,
			final MInvoice oldInvoice, final int C_Cash_ID,
			final int C_CashBook_ID, final String motivoCancel,
			final int motivoCancelid, final Timestamp dateFactura, MDocType C_DocType,
			final String trxName) throws Exception {

		// genera nota de credito con los datos de la factura
		MInvoice notaCred = MInvoice.createNotaCredFromInvoice(
				oldInvoice, new Timestamp(System.currentTimeMillis()),
				C_CashBook_ID, C_Cash_ID, true, C_DocType, trxName);

		if (notaCred == null) {
			return notaCred;
		}

		if (!checaFactCanceladas(notaCred.getC_Invoice_ID())) {
			return null;
		}

		// se guardan los motivos de cancelacion capturados por el
		// usario.
		notaCred.setCanceledBy(Env.getAD_User_ID(ctx));
		notaCred.setMotivoCancel(motivoCancel);
		notaCred.setEXME_MotivoCancel_ID(motivoCancelid);
		// Seteamos la fecha del campo fecha de facturacion.#04634 —
		// La refacturacion se imprime con fecha original de factura
		// (Fecha Actual) Ealvarez
		notaCred.setDateInvoiced(dateFactura);
		if (!notaCred.save(trxName)) {
			notaCred = null;
		} else {
			notaCred.completeIt();
			if(!X_C_Invoice.DOCSTATUS_Drafted.equals(notaCred.getDocStatus())){
				throw new MedsysException(
						Utilerias.getLabel("error.tableroCamas.invalidStatus")+" "
								+ Msg.parseTranslation(ctx, notaCred.getProcessMsg()) +" "
								+ Utilerias.getLabel("factExtension.error.createInvoiceExt", notaCred.getDocumentNo()));
			}
			
			if(C_Cash_ID>0){
				notaCred.setProcessed(false, trxName);
				notaCred.setStatusInProgress();
			} else {
				notaCred.setProcessed(true, trxName);
				notaCred.updateStatusComplete();
			}
			// Copiar el total a dos decimales
			notaCred.setGrandTotal(oldInvoice.getGrandTotal().setScale(2,
					BigDecimal.ROUND_HALF_UP));
			if(!notaCred.save(trxName)){
				sLog.log(Level.SEVERE, "No se actualizo la nota de credito en el gran total "+notaCred.getDocumentNo());
			}
		}
		return notaCred;
	}

	/**
	 * Generacion de la nueva factura
	 *
	 * @param ctx
	 * @param oldInvoice
	 * @param C_Cash_ID
	 * @param C_CashBook_ID
	 * @param isCtaPac
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public MInvoice createPreFactura(final Properties ctx,
			final MInvoice oldInvoice, final int C_Cash_ID,
			final int C_CashBook_ID, final boolean isCtaPac,
			final String trxName) throws Exception {
		// Generamos la prefactura (copia de la factura cancelada
		final MInvoice newInvoice = new MInvoice(ctx, 0, trxName);
		// Datos a partir de la factura original
		PO.copyValues(oldInvoice, newInvoice);
		// Tipo de documento factura
		newInvoice.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice, MDocType.DOCSUBTYPESO_SalesReceipt);
		newInvoice.setC_DocType_ID(newInvoice.getC_DocTypeTarget_ID());

		// Fecha actual 
				newInvoice.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
				newInvoice.setDateAcct(new Timestamp(System.currentTimeMillis()));
				newInvoice.setDateOrdered(oldInvoice.getDateOrdered());
				
		newInvoice.setBackoffice(false);
		newInvoice.setC_Order_ID(0);
		newInvoice.setIsApproved(false);
		newInvoice.setIsPrinted(false);
		newInvoice.setPosted(false);
		newInvoice.setProcessed(false);
		newInvoice.setRef_Invoice_ID(oldInvoice.getC_Invoice_ID());
		// Actualizar la caja que genera la refacturacion
		newInvoice.setC_Cash_ID(C_Cash_ID);
		newInvoice.setIsTaxIncluded(false);
		newInvoice.setTrxType(X_C_Invoice.TRXTYPE_CustomerInvoice);
		if (oldInvoice.getEXME_CtaPacExt_ID() > 0) {
			newInvoice.setEXME_CtaPacExt_ID(oldInvoice.getEXME_CtaPacExt_ID());
		}

		if (newInvoice.save(trxName)) {
			// Copiar las lienas
			newInvoice.copyLinesFrom(oldInvoice, trxName);
			// Terminar el documento
			newInvoice.completeIt(); 
			if(!X_C_Invoice.DOCSTATUS_Drafted.equals(newInvoice.getDocStatus())){
				throw new MedsysException(
						Utilerias.getLabel("error.tableroCamas.invalidStatus")+" "
								+ Msg.parseTranslation(ctx, newInvoice.getProcessMsg()) +" "
								+ Utilerias.getLabel("factExtension.error.createInvoiceExt", newInvoice.getDocumentNo()));
			}
			// Actualiza el estatus a Factura
			newInvoice.setProcessed(false, trxName);
			newInvoice.setStatusInProgress();
			// Copiar el total a dos decimales
			newInvoice.setGrandTotal(oldInvoice.getGrandTotal().setScale(2,
					BigDecimal.ROUND_HALF_UP));
			if(!newInvoice.save(trxName)){
				sLog.log(Level.SEVERE, "No se actualizo la prefactura en el gran total "+newInvoice.getDocumentNo());
				throw new MedsysException();
			}
		} else {
			sLog.log(Level.SEVERE, "No se actualizo gererar la prefactura de la factura : " + oldInvoice.getDocumentNo());
			throw new MedsysException();
		}
		return newInvoice;
	}

	/**
	 * Metodo que verifica si la factura ya ha sido cancelada
	 *
	 * @param notaId
	 *            es el id de la factura a cancelar
	 * @return true si es posible cancelar la factura
	 */
	private boolean checaFactCanceladas(final int notaId) {
		boolean band = true;

		ResultSet rs = null;
		PreparedStatement pstm = null;

		final StringBuilder sql = new StringBuilder()
		.append(" SELECT * ")
		.append(" FROM C_Invoice ")
		.append(" WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", X_C_Invoice.Table_Name))
		.append(" AND   TrxType<> 'P' ")// que no sea nota de credito parcial
		.append(" AND   DocStatus IN ('IP','CO','CL') ")// el estatus que este activa
		.append(" AND   Ref_Invoice_ID = ? ")// Factura
		.append(" AND   C_Invoice_ID <> ? ");//Nota actual
		

		try {
			pstm = DB.prepareStatement(sql.toString(), get_TrxName());
			pstm.setInt(1, getC_Invoice_ID());// Factura 
			pstm.setInt(2, notaId);// Nota actual
			rs = pstm.executeQuery();

			while (rs.next()){
//				final MInvoice mInvoice = new MInvoice(getCtx(), rs, null);
				band = false; //FIXME porque itera todos los registros?
			}				
		} catch (Exception e) {
			sLog.log(Level.SEVERE, "checaFactCanceladas(int fact_id) " + sql.toString(), e);
			
		} finally {
			DB.close(rs, pstm);
		}

		return band;
	}

	MInvoice mInvoiceTarget = null;
	/** Crear una factura a partir de la nota de remisión */
	public MInvoice getInvoiceTarget(final int cCashId, final String trxName){
		// Factura
		mInvoiceTarget = existInvoiceTarget(trxName);

		if(mInvoiceTarget == null || !(mInvoiceTarget.getC_Invoice_ID() > 0)){
			mInvoiceTarget = createInvoiceTarget(cCashId, trxName);
		}
		return mInvoiceTarget;
	}
	
	/** Crear una factura a partir de la nota de remisión */
	private MInvoice createInvoiceTarget(final int cCashId, final String trxName){
		// Factura
		final MInvoice mInvoiceTarget = new MInvoice(getCtx(), 0, null);
		PO.copyValues(this, mInvoiceTarget);
		mInvoiceTarget.setBackoffice(false);
		mInvoiceTarget.setIsApproved(false);
		mInvoiceTarget.setIsPrinted(false);
		mInvoiceTarget.setPosted(false);
		mInvoiceTarget.setProcessed(false);
		mInvoiceTarget.setTrxType(TRXTYPE_CustomerInvoice);
		mInvoiceTarget.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
		// Tipo de documento factura
		mInvoiceTarget.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice, MDocType.DOCSUBTYPESO_SalesReceipt);
		mInvoiceTarget.setC_DocType_ID(mInvoiceTarget.getC_DocTypeTarget_ID());
		// Referencia a la nota de remision
		mInvoiceTarget.setRef_Invoice_Sales_ID(getC_Invoice_ID());
		mInvoiceTarget.setC_Cash_ID(cCashId);// Caja que timbro
		if(!mInvoiceTarget.save(trxName)){// 1 sola vez la transaccion
			throw new MedsysException();
		}
		// Copiar las lineas
		mInvoiceTarget.copyLinesFrom(this, trxName);
		// Terminar el documento
		if (mInvoiceTarget.completeIt().equals(DocAction.STATUS_Drafted)) {
			mInvoiceTarget.setGrandTotal(this.getGrandTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
			mInvoiceTarget.setProcessed(false, trxName);
			mInvoiceTarget.setStatusInProgress();
			mInvoiceTarget.setIsPaid(isPaid());// Pasar si esta pagada o no la nota de remision
			if (!mInvoiceTarget.save()) {
				throw new MedsysException();
			}
		} else {
			return null;
		}
		return mInvoiceTarget;
	}
	
	/** Crear una factura a partir de la nota de remisión para timbrarla*/
	public MInvoice getInvTarget(final int cCashId, final String trxName){
		// Factura
		mInvoiceTarget = existInvoiceTarget(trxName);

		if(mInvoiceTarget == null || !(mInvoiceTarget.getC_Invoice_ID() > 0)){
			mInvoiceTarget = createInvTarget(cCashId, trxName);
		}
		return mInvoiceTarget;
	}
	
	private List<MInvoiceLine> lstInvLines = null;	
	

	public void setLstInvLines(List<MInvoiceLine> lstInvLines) {
		this.lstInvLines = lstInvLines;
	}

	public List<MInvoiceLine> getLstInvLines() {
		return lstInvLines;
	}

	/** Crear una factura a partir de la nota de remisión para timbrarla*/
	private MInvoice createInvTarget(final int cCashId, final String trxName){
		// Factura
		final MInvoice mInvoiceTarget = new MInvoice(getCtx(), 0, null);
		PO.copyValues(this, mInvoiceTarget);
		
		mInvoiceTarget.setBackoffice(false);
		mInvoiceTarget.setIsApproved(false);
		mInvoiceTarget.setIsPrinted(false);
		mInvoiceTarget.setPosted(false);
		mInvoiceTarget.setProcessed(false);
		mInvoiceTarget.setTrxType(TRXTYPE_CustomerInvoice);
		mInvoiceTarget.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
		// Tipo de documento factura
		mInvoiceTarget.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice, MDocType.DOCSUBTYPESO_SalesReceipt);
		mInvoiceTarget.setC_DocType_ID(mInvoiceTarget.getC_DocTypeTarget_ID());
		mInvoiceTarget.setDocumentNo(DB.getDocumentNo(mInvoiceTarget.getC_DocTypeTarget_ID(), trxName, false));
		// Referencia a la nota de remision
		mInvoiceTarget.setRef_Invoice_Sales_ID(getC_Invoice_ID());
		mInvoiceTarget.setC_Cash_ID(cCashId);// Caja que timbro
		
		// Copiar las lineas
		lstInvLines = mInvoiceTarget.copyLines(this, trxName);
		// Terminar el documento
		
		return mInvoiceTarget;
	}

//	/**
//	 * Mueve los pagos (C_CashLine / C_AllocationLine) de una factura a otra.
//	 * Card #1087 Relacionar pagos de remisiones a facturas
//	 *
//	 * @param mInvoiceTarget Factura a la que se relacionaran los pagos.
//	 * @param trxName nombre de transaccion
//	 */
//	public boolean movePaymtsToInvoice(final MInvoice mInvoiceTarget, String trxName) {
//		final String sqlAllocation = "UPDATE C_AllocationLine SET C_Invoice_ID=? WHERE C_Invoice_ID=?";
//		final String sqlCashline = "UPDATE C_CashLine SET C_Invoice_ID=? WHERE C_Invoice_ID=?";
//		final Object[] params = new Object[] { mInvoiceTarget.getC_Invoice_ID(), getC_Invoice_ID() };
//
//		// se actualiza la referencia de los allocation y los cash directos a la factura
//		return DB.executeUpdate(sqlAllocation, params, trxName) >= 0 //
//			&& DB.executeUpdate(sqlCashline, params, trxName) >= 0;
//		return true;
//	}

	/** Cuando el documento deja de ser un borrador */
	public void setStatusInProgress(){
//		setTrxType(TRXTYPE_CustomerInvoice);
		setDocStatus(DOCSTATUS_InProgress);
		setDocAction(DOCACTION_Complete);
	}

	/** serialVersionUID */
//	private static final long serialVersionUID = 8705251594345184617L;
	/** lineas de pagos directos de caja */
	private ArrayList<MCashLine> lstCashLines = new ArrayList<MCashLine>();
	/** monto de pagos */
	private BigDecimal montoPagoDirecto = Env.ZERO;
//	/** lineas de pagos relacionados a la factura */
//	private ArrayList<MAllocationLine> lstAllocationLine = new ArrayList<MAllocationLine>();
//	/** monto de pagos */
//	private BigDecimal montoAnticipos = Env.ZERO;

//	/**
//	 * @param ctx
//	 * @param C_Invoice_ID
//	 * @param trxName
//	 */
//	public MEXMEInvoice(final Properties ctx, final int C_Invoice_ID,
//			final String trxName) {
//		super(ctx, C_Invoice_ID, trxName);
//	}
//
//	/**
//	 * @param ctx
//	 * @param rs
//	 */
//	public MEXMEInvoice(final Properties ctx, final ResultSet rset,
//			final String trxName) {
//		super(ctx, rset, trxName);
//	}

	/**
	 * Retorna el ID del registro.
	 *
	 * @return
	 */
	public int getId() {
		return getC_Invoice_ID();
	}

//	/**
//	 * NO crea la recepcion de efectivo (Chash) todavia. Le establece un estatus
//	 * de que todavia falta pasar a caja. Complete Document
//	 *
//	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
//	 */
//
//	public String completeIt() {
//
//		final String status = super.completeIt();
//
//		if (STATUS_Invalid.equals(status)) {
//			return status;
//		}
//		// La ponemos como no procesado para que no postee, sino hasta corte de
//		// caja.
//		setProcessed(false, get_TrxName());
//		// El siguiente paso es completar la factura.
//		setDocAction(DOCACTION_Complete);
//		// Retornamos En Proceso (IP) ya que aun es una pre-factura , pues
//		// todavia no tiene DocumentNoExt.
//		return DocAction.STATUS_InProgress;
//	} // completeIt

	/**
	 * Devuelve un array de objetos MEXMEInvoice con la informacion de las
	 * facturas pagadas en un rango de fechas en la caja especificada (siempre y
	 * cuando no tengan una nota de credito asociaada a ellas)
	 *
	 * @param ctx
	 *            Contexto
	 * @param C_CashBook_ID
	 *            el identificador de la caja
	 * @param fechaIni
	 *            fecha inicial para filtrar las facturas
	 * @param fechaFin
	 *            fecha final para filtrar las facturas
	 * @param trxName
	 *            El nombre de la transaccion
	 *
	 * @return un lista de objetos PagoFact
	 * @throws Exception
	 *             en caso de ocurrir un error en la consulta
	 */
	public static MInvoice[] getFromCash(final Properties ctx,
			final String condicion,
			final String trxName, final boolean verFacturasEnCero) {
		final ArrayList<MInvoice> lista = new ArrayList<MInvoice>();

		StringBuilder sql = new StringBuilder();

		// Debe considerar tambien las facturas pagadas con anticipos
		sql.append(" SELECT C_Invoice.* ")
				.append(" FROM C_Invoice ")
				.append(" INNER JOIN C_DocType      d  ON d.C_DocType_ID = C_Invoice.C_DocType_ID ")
				.append(" LEFT JOIN EXME_CtaPacExt cpe ON cpe.EXME_CtaPacExt_ID =  C_Invoice.EXme_CtaPacExt_ID ")
				.append(" LEFT JOIN EXME_CtaPac     cp ON cp.EXME_CtaPac_ID = cpe.EXme_CtaPac_ID AND cp.isActive = 'Y'  ")
				.append(" LEFT JOIN EXME_Paciente    p ON p.EXME_Paciente_id =  C_Invoice.EXME_Paciente_ID ")
				// Facturas Activas
				.append(" WHERE C_Invoice.IsActive = 'Y' ")
				// Que sean facturas
				.append(" AND  d.DocBaseType = ").append(DB.TO_STRING(MDocType.DOCBASETYPE_ARInvoice))
				// Que no este cancelada
				.append(" AND  C_Invoice.C_Invoice_ID NOT IN (SELECT Ref_Invoice_ID FROM C_Invoice WHERE Ref_Invoice_ID IS NOT NULL) ");

		// Debe considerar tambien las facturas pagadas con anticipos
//				sql.append(" SELECT C_Invoice.* ")
//						.append(" FROM C_Invoice ")
//						.append(" INNER JOIN C_DocType      d  ON d.C_DocType_ID = C_Invoice.C_DocType_ID ")
//						.append(" LEFT JOIN EXME_CtaPacExt cpe ON cpe.EXME_CtaPacExt_ID =  C_Invoice.EXme_CtaPacExt_ID ")
//						.append(" LEFT JOIN EXME_CtaPac     cp ON cp.EXME_CtaPac_ID = cpe.EXme_CtaPac_ID AND cp.isActive = 'Y'  ")
//						.append(" LEFT JOIN EXME_Paciente    p ON p.EXME_Paciente_id =  C_Invoice.EXME_Paciente_ID ")
//						.append(" WHERE C_Invoice.IsActive = 'Y' ")
//						.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx," ", "C_Invoice")))
//						.append(" AND  d.DocBaseType = ").append(DB.TO_STRING(MDocType.DOCBASETYPE_ARInvoice))
//						.append(" AND  C_Invoice.C_Invoice_ID NOT IN (SELECT Ref_Invoice_ID FROM C_Invoice WHERE Ref_Invoice_ID IS NOT NULL) ");
//
//

		if (!verFacturasEnCero) {
			sql.append(" AND C_Invoice.GrandTotal > 0 ");
		}

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "C_Invoice"));

		if (condicion != null) {
			sql.append(condicion);
		}

		sql.append(" GROUP BY  C_Invoice.C_Invoice_ID  ORDER BY  C_Invoice.C_Invoice_ID DESC");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				final MInvoice fact = new MInvoice(ctx,
						rset, trxName);
				lista.add(fact);
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "MEXMEInvoice.getFromCash", e);
		} finally {
			DB.close(rset, pstmt);
		}

		final MInvoice[] retValue = new MInvoice[lista.size()];
		lista.toArray(retValue);
		return retValue;
	}

	public static MInvoice[] getNotasRemision(final Properties ctx,
			final String condicion,
			final String trxName, final boolean verFacturasEnCero, final boolean facturada) {
		final ArrayList<MInvoice> lista = new ArrayList<MInvoice>();

		StringBuilder sql = new StringBuilder();

		// Debe considerar tambien las facturas pagadas con anticipos
		sql.append(" SELECT C_Invoice.* ")
		   .append(" FROM C_Invoice ")
		   .append(" LEFT JOIN EXME_Paciente p ON (p.EXME_Paciente_id = C_Invoice.EXME_Paciente_ID) ")
		   // NR Activas
		   .append(" WHERE C_Invoice.IsActive = 'Y' ")
		   // NR
		   .append(" AND  C_Invoice.docstatus = '").append(X_C_Invoice.DOCSTATUS_Drafted).append("' ")
		   .append(" AND to_char(C_Invoice.DateInvoiced,'MMYYYY') = to_char(now(),'MMYYYY') ");

		if(facturada){
//			sql.append(" AND C_Invoice.C_Invoice_id in (select Ref_Invoice_sales_id from c_invoice_customer_v ");
//			sql.append(" 	where isactive = 'Y'");
//			sql.append("	and ad_client_id = ").append(Env.getAD_Client_ID(ctx));
//			sql.append(" 	and ad_org_id = ").append(Env.getAD_Org_ID(ctx));
//			sql.append(" 	and Ref_Invoice_sales_id > 0 ) ");
			sql.append(" AND ( C_Invoice.C_Invoice_id in (select Ref_Invoice_sales_id from ").append(MInvoice.getCInvoiceCustomerV(ctx, true));
			sql.append(" 	                             where Ref_Invoice_sales_id > 0 ) ");
			sql.append("       OR C_Invoice.Multiple_ID IS NOT NULL ) ");// Considerar que la nota de remision estaria pagada como factura global

		}else{
//			sql.append(" AND C_Invoice.C_Invoice_id not in (select Ref_Invoice_sales_id from c_invoice_customer_v ");

//			sql.append("	where isactive = 'Y' ");
//			sql.append(" 	and ad_client_id = ").append(Env.getAD_Client_ID(ctx));
//			sql.append("	and ad_org_id = ").append(Env.getAD_Org_ID(ctx));
//			sql.append(" 	and Ref_Invoice_sales_id > 0 ) ");
			sql.append(" AND C_Invoice.C_Invoice_id not in (select Ref_Invoice_sales_id from ").append(MInvoice.getCInvoiceCustomerV(ctx, true));
			sql.append(" 	                                where Ref_Invoice_sales_id > 0 ) ");
			sql.append(" AND C_Invoice.Multiple_ID IS NULL ");// Considerar que la nota de remision estaria pagada como factura global
		}

		if (!verFacturasEnCero) {
			sql.append(" AND C_Invoice.GrandTotal > 0 ");
		}

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "C_Invoice"));

		if (condicion != null) {
			sql.append(condicion);
		}

		sql.append(" GROUP BY  C_Invoice.C_Invoice_ID  ORDER BY  C_Invoice.C_Invoice_ID DESC");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				final MInvoice fact = new MInvoice(ctx,
						rset, trxName);
				lista.add(fact);
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "MEXMEInvoice.getFromCash", e);
		} finally {
			DB.close(rset, pstmt);
		}

		final MInvoice[] retValue = new MInvoice[lista.size()];
		lista.toArray(retValue);
		return retValue;
	}

	/**
	 *
	 * @param ctx
	 * @param condicion
	 * @param trxName
	 * @param verFacturasEnCero
	 * @return An Array with the non voided invoices.
	 */
	public static MInvoice[] getFacturasNoCanceladas(final Properties ctx,
			final String condicion, String joins,
			final String trxName, final boolean verFacturasEnCero) {
		final ArrayList<MInvoice> lista = new ArrayList<MInvoice>();

		StringBuilder sql = new StringBuilder();

		// Debe considerar tambien las facturas pagadas con anticipos
		sql.append(" SELECT C_Invoice.* FROM c_invoice_customer_v C_Invoice " );
		if (StringUtils.isNotEmpty(joins)) {
			sql.append(joins);
		}
		sql.append("where C_Invoice.isactive = 'Y' ");
		if (!verFacturasEnCero) {
			sql.append(" AND C_Invoice.GrandTotal > 0 ");
		}
		if (condicion != null) {
			sql.append(condicion);
		}
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_Invoice"));

		sql.append(" ORDER BY  C_Invoice.C_Invoice_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				final MInvoice fact = new MInvoice(ctx,	rset, trxName);
				lista.add(fact);
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "SQL : " + sql, e);
		} finally {
			DB.close(rset, pstmt);
		}

		final MInvoice[] retValue = new MInvoice[lista.size()];
		lista.toArray(retValue);
		return retValue;
	}


	/**
	 * Genera una nota de cr&eacute;dito a partir de una factura
	 *
	 * @param pOldInvoice
	 *            La factura original
	 * @param dateDoc
	 *            La fecha de la nota de cr&eacut;dito
	 * @param C_CashBook_ID
	 *            El identificador de la caja f&iacut;sica
	 * @param C_Cash_ID
	 *            El identificador de la caja (0 si es null)
	 * @param trxName
	 *            el nombre de la transacci&oacute;n
	 * @return
	 * @throws Exception
	 */
	public static MInvoice createNotaCredFromInvoice(
			final MInvoice pOldInvoice, final Timestamp dateDoc,
			final int C_CashBook_ID, final int C_Cash_ID,
			final boolean asignarFolio, MDocType C_DocType, final String trxName) throws Exception {
		MInvoice newNota = null;
		if (C_DocType.getDocBaseType().equals(MDocType.DOCBASETYPE_ARCreditMemo)) {
			if (C_Cash_ID <= 0) {
				return newNota;
			}
		}

		try {
			// Tipo de documento nota de credito
			/*final MDocType[] types = MEXMEDocType.getOfDocBaseType(
					pOldInvoice.getCtx(), MDocType.DOCBASETYPE_ARCreditMemo);
			int C_DocType_ID = 0;
			if (types.length > 0) { // get first
				C_DocType_ID = types[0].getC_DocType_ID();
			} else {
				throw new Exception("error.caja.tipoDoc.notaCred");
			}*/

			if (C_DocType.getC_DocType_ID() <= 0) {
				throw new Exception("error.caja.tipoDoc.notaCred");
			}

			// Generacion de nota de credito
			newNota = new MInvoice(C_DocType.getCtx(), 0, trxName);
			// Datos de la factura original
			PO.copyValues(pOldInvoice, newNota);
			// Asignamos el tipo de documento
			newNota.setC_DocType_ID(C_DocType.getC_DocType_ID());
			newNota.setC_DocTypeTarget_ID(C_DocType.getC_DocType_ID());
			newNota.setBackoffice(false);
			// Fecha actual
			newNota.setDateAcct(new Timestamp(System.currentTimeMillis()));// Fecha actual
			newNota.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
			// asignamos la caja
			if (C_Cash_ID > 0) {
				newNota.setC_Cash_ID(C_Cash_ID);
			}
			
			newNota.setRef_Invoice_ID(pOldInvoice.getC_Invoice_ID());
			newNota.setAfecta_Caja(pOldInvoice.isAfecta_Caja());// solo cuando se cobra en caja o es
			// pagada totalmente con anticipos
			newNota.setIsPaid(true);// se paga con la nota de credito
			newNota.setIsTaxIncluded(false);
			newNota.setTrxType(X_C_Invoice.TRXTYPE_TotalCreditNoteCustomer);
			newNota.setIsApproved(false);
			newNota.setIsPrinted(false);
			newNota.setPosted(false);
			newNota.setProcessed(false);

			newNota.setSELLO(null);
			newNota.setUUID(null);
			newNota.setCadena(null);

			if (!newNota.save(trxName)) {
				throw new Exception("error.caja.notaCred.noSave");
			}

			newNota.copyLinesFrom(pOldInvoice, trxName);

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "MEXMEInvoice.createNotaCredFromInvoice", e);
			newNota = null;
		}

		return newNota;
	}

	/**
	 * Paciente
	 *
	 * @return
	 */
	public MEXMEPaciente getPaciente() {

		if (getEXME_Paciente_ID() <= 0) {
			return null;
		}
		return new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());

	}

	/**
	 * Devuelve un array de objetos MEXMEInvoice con la informacion de las
	 * facturas no pagadas en un rango de fechas en la caja especificada
	 * (siempre y cuando no tengan una nota de credito asociada a ellas)
	 *
	 * @param ctx
	 *            Contexto
	 * @param C_CashBook_ID
	 *            el identificador de la caja
	 * @param fechaIni
	 *            fecha inicial para filtrar las facturas
	 * @param fechaFin
	 *            fecha final para filtrar las facturas
	 * @param trxName
	 *            El nombre de la transaccion
	 *
	 * @return un lista de objetos PagoFact
	 * @throws Exception
	 *             en caso de ocurrir un error en la consulta
	 */
	public static List<MInvoice> getPreFacturas(final Properties ctx,
			final int C_CashBook_ID, final String condicion,
			final String trxName) {
		final ArrayList<MInvoice> lista = new ArrayList<MInvoice>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT C_Invoice.* ")
		.append(" FROM C_Invoice ")
		.append(" INNER JOIN C_Cash c ON (c.C_Cash_ID = C_Invoice.C_Cash_ID) ")
		.append(" INNER JOIN C_DocType d ON (d.C_DocType_ID = C_Invoice.C_DocType_ID ) ")
		.append(" LEFT JOIN EXME_Paciente p ON (p.EXME_Paciente_id = C_Invoice.EXME_Paciente_ID) ")
		.append(" WHERE C_Invoice.IsActive = 'Y' ");

		if (C_CashBook_ID > 0) {
			sql.append(" AND C_Invoice.C_CashBook_ID = ? ");
		}
		// Sin pagos o solo anticipos
		sql.append(" AND C_Invoice.Afecta_Caja = 'N' ")
		// Facturas
		.append(" AND d.DocBaseType = '").append(MDocType.DOCBASETYPE_ARInvoice).append("' ")
		// Sin notas de remision
		.append(" AND d.DocSubTypeSO IS NOT NULL ")
		// Que no este cancelada (sin nota de credito)
		.append(" AND C_Invoice.C_Invoice_ID NOT IN (SELECT Ref_Invoice_ID FROM C_Invoice WHERE Ref_Invoice_ID IS NOT NULL) ")
		// Que no tenga lineas en caja
		.append(" AND C_Invoice.C_Invoice_ID NOT IN (SELECT C_Invoice_ID FROM C_CashLine WHERE C_Invoice_ID IS NOT NULL) ");

		if (condicion != null) {
			sql.append(condicion);
		}

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "C_Invoice"));

		sql.append(" ORDER BY C_Invoice.Updated DESC");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (C_CashBook_ID > 0) {
				pstmt.setInt(1, C_CashBook_ID);
			}

			rset = pstmt.executeQuery();

			while (rset.next()) {
				final MInvoice fact = new MInvoice(ctx, rset, trxName);
				lista.add(fact);
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "MEXMEInvoice.getPreFacturas", e);
		} finally {
			DB.close(rset, pstmt);
		}

		return lista;
	}

	/**
	 * Devuelve un modelo con los datos basicos para un documento de acuerdo al
	 * identificador proporcionado, facturas que no esten canceladas.
	 *
	 * @param factura
	 *            El identificador de la factura a obtener
	 * @param caja
	 *            El identificador de la caja a la que pertenece la factura
	 *
	 * @return Un objeto de tipo DocumentView
	 * @throws Exception
	 *             en caso de ocurrir un error al hacer la consulta o no
	 *             localizar el registro especificado.
	 */
	public static DocumentView getInvoiceFromID(final Properties ctx,
			final int factura, final long caja) throws Exception {
		final DocumentView doc = new DocumentView();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		try {
			sql = "SELECT C_Invoice.DocumentNo, C_Invoice.C_BPartner_ID, bp.Value, bp.Name, "
					+ "C_Invoice.GrandTotal, p.Apellido1 || ' ' || p.Apellido2 || ' ' || "
					+ "p.Name || ' ' || p.Nombre2 AS Paciente, "
					+ "C_Invoice.C_Currency_ID, C_Invoice.C_ConversionType_ID, C_Invoice.C_DocType_ID "
					+ "FROM C_Invoice INNER JOIN C_BPartner bp ON (C_Invoice.C_BPartner_ID = bp.C_BPartner_ID) "
					+ "LEFT JOIN EXME_CtaPacExt e ON (C_Invoice.EXME_CtaPacExt_ID = e.EXME_CtaPacExt_ID) "
					+ "LEFT JOIN EXME_CtaPac cta ON (e.EXME_CtaPac_ID = cta.EXME_CtaPac_ID AND cta.isActive = 'Y' ) "
					+ // .-Lama
					"LEFT JOIN EXME_Paciente p ON (cta.EXME_Paciente_ID = p.EXME_Paciente_ID) "
					+ "WHERE C_Invoice.C_Invoice_ID = "
					+ factura
					+ " AND C_Invoice.DocStatus <> 'VO'";
			// " AND d.C_CashBook_ID = " + caja + (PUEDE ESTAR ASOCIADA A OTRA
			// CAJA!!)
			// " AND d.Afecta_Caja = 'N' AND d.Processed = 'N'"; (PUEDE TENER
			// DEVOLUCION)

			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_Invoice");

			pstmt = DB.prepareStatement(sql, null);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				doc.setDocumentID(factura);
				doc.setDocumentNo(rset.getString("DocumentNo"));
				doc.setClienteID(rset.getInt("C_BPartner_ID"));
				doc.setClienteValue(rset.getString("Value"));
				doc.setClienteName(rset.getString("Name"));
				doc.setPacienteName(rset.getString("Paciente"));
				doc.setTotalDocto(rset.getBigDecimal("GrandTotal"));
				doc.setSaldo(rset.getBigDecimal("GrandTotal"));
				doc.setMonedaID(rset.getInt("C_Currency_ID"));
				doc.setConversionTypeID(rset.getLong("C_ConversionType_ID"));
				doc.setDocTypeID(rset.getInt("C_DocType_ID"));
			} else {

				throw new Exception("error.caja.noDocNo");
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "MEXMEInvoice.getInvoiceFromID", e);
			throw e;
		} finally {
			DB.close(rset, pstmt);
		}
		return doc;
	}

	// /**
	// * Calculate Tax and Total
	// */
	// public boolean calcularTotales() {
	// // Lines
	// BigDecimal totalLines = Env.ZERO;
	//
	// // Barre todas las lineas de la factura
	// final MInvoiceLine[] lines = getLines(true, false);// lee las lineas de
	// // los cargos
	// for (int i = 0; i < lines.length; i++) {
	// final MInvoiceLine line = lines[i];
	// totalLines = totalLines.add(line.getPriceList().multiply(
	// line.getQtyInvoiced()));
	// }
	//
	// // Taxes
	// BigDecimal grandTotal = totalLines;
	// final MInvoiceTax[] taxes = getTaxes(true);
	// for (int i = 0; i < taxes.length; i++) {
	// final MInvoiceTax iTax = taxes[i];
	// final MTax tax = iTax.getTax();
	// if (tax.isSummary()) {
	// BigDecimal taxAmt = iTax.getTaxAmt();
	// taxAmt = taxAmt.setScale(2, BigDecimal.ROUND_HALF_UP); // Redondeamos
	// // a 4
	// // decimales.
	// //
	// if (!isTaxIncluded()) {
	// grandTotal = grandTotal.add(taxAmt);
	// }
	// } else {
	// if (!isTaxIncluded()) {
	// grandTotal = grandTotal.add(iTax.getTaxAmt());
	// }
	// }
	//
	// }
	// //
	// setTotalLines(totalLines);
	// if (grandTotal.compareTo(Env.ZERO) < 0) {
	// setGrandTotal(Env.ZERO);
	// } else {
	// setGrandTotal(grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
	// }
	// return true;
	// } // calculateTaxTotal

	/**
	 * Cambia el estatus de la factura al cerrar la caja
	 *
	 * @param ctx
	 * @param encabezado
	 * @param trxName
	 * @return
	 */
	public static boolean cambiarEstatus(final Properties ctx,
			final MCash encabezado, final String trxName) {
		final MInvoice[] facturas = MInvoice.facturasEnCash(ctx,
				encabezado.getC_Cash_ID(), trxName);

		for (int i = 0; i < facturas.length; i++) {
			facturas[i].setProcessed(true);
			facturas[i].setDocStatus(MInvoice.DOCSTATUS_Completed);
			facturas[i].setDocAction(MInvoice.DOCACTION_Close);
			if (!facturas[i].save(trxName)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Devuelve un arreglo con las facturas destinadas a la apertura/cierre de
	 * la caja
	 *
	 * @param ctx
	 * @param C_CashBook_ID
	 *            Caja
	 * @param C_Cash_ID
	 *            Linea de Apertura de caja
	 * @param trxName
	 * @return
	 */
	public static MInvoice[] facturasEnCash(final Properties ctx,
			final int C_Cash_ID, final String trxName) {
		if (ctx == null) {
			return null;
		}

		final ArrayList<MInvoice> lista = new ArrayList<MInvoice>();
		final StringBuilder sql = new StringBuilder()
				.append(" SELECT C_Invoice.* ")
				.append(" FROM   C_Cash      ")
				// .append(" INNER  C_Invoice ON C_Invoice.C_Invoice_ID = C_Cash.C_Invoice_ID AND C_Invoice.DocStatus <> 'VO' ")
				.append(" INNER JOIN C_CashLine ON C_Cash.C_Cash_ID = C_CashLine.C_Cash_ID ")
				.append(" INNER JOIN C_Invoice ON C_Invoice.C_Invoice_ID = C_CashLine.C_Invoice_ID AND C_Invoice.DocStatus <> 'VO' ")
				.append(" WHERE  C_Cash.IsActive = 'Y' ")
				.append(" AND    C_Cash.C_Cash_ID = ?     ")
				.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(
						ctx, " ", "C_Cash")));

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_Cash_ID);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lista.add(new MInvoice(ctx, rset, trxName));
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "facturasEnCash", e);
		} finally {
			DB.close(rset, pstmt);
		}
		final MInvoice[] listFact = new MInvoice[lista.size()];
		lista.toArray(listFact);
		return listFact;
	}

	/**
	 * Actualizamos las facturas a estatus de No Pagadas cuando al menos una de
	 * las lineas del pago sean cuentas x cobrar (X)
	 *
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param C_Cash_ID
	 *            La caja
	 * @param trxName
	 *            El nombre de la transaccion
	 * @return
	 */
	public static boolean setIsPaidCxC(final Properties ctx,
			final int C_Cash_ID, final String trxName) {

		boolean success = true;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);

		// Liz de la Garza - Cambio del proceso para utilizarlo dentro del
		// modelo de persistencia (para log de cambios)
		// Mantis #4108 : Correcci�n de SQL .- Lama
		sql.append("SELECT DISTINCT C_Invoice.* FROM C_Invoice ");
		sql.append("INNER JOIN C_CashLine ON (C_CashLine.C_Invoice_ID = C_Invoice.C_Invoice_ID ");
		sql.append("AND C_CashLine.C_Cash_ID = ? ");
		sql.append("AND C_CashLine.CashType = ? ) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ",
				MCashLine.Table_Name));
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				MInvoice.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_Cash_ID);
			pstmt.setString(2, MCashLine.CASHTYPE_CuentasXCobrar);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				MInvoice obj = new MInvoice(ctx, rset, null);
				obj.setIsPaid(false);
				if (!obj.save(trxName)) {
					sLog.log(Level.SEVERE, "diarioEnf.error.noSave");
					success = false;
					break;
				}
				obj = null;
			}

		} catch (Exception e) {
			success = false;
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return success;

	}

	/**
	 * Establecemos los valores para la factura a partir de un Extension.
	 *
	 * @param ext
	 */
	private boolean setLocationExt(final MEXMECtaPacExt ext) {
		// Informacion sobre la persona a quien se le va a facturar.
		if (ext.getC_Location_ID() > 0) {
			final MLocation location = new MLocation(getCtx(),
					ext.getC_Location_ID(), ext.get_TrxName());
			setAddress1(location.getAddress1());
			setAddress2(location.getAddress2());
			setAddress3(location.getAddress3());
			setCity(location.getCity());
			setPostal(location.getPostal());
			setC_Country_ID(location.getC_Country_ID());
			setC_Region_ID(location.getC_Region_ID());
			if (location.getNumExt() != null) {
				setNumExt(location.getNumExt());
			}
			if (location.getNumIn() != null) {
				setNumIn(location.getNumIn());
			}
			setEXME_TownCouncil_ID(location.getEXME_TownCouncil_ID());
		}
		return ext.getC_Location_ID() > 0;
	}

	/**
	 * invoice
	 *
	 * @param EXME_CtaPac_ID
	 * @param ctx
	 * @return
	 */
	public static MInvoice invoice(final int EXME_CtaPac_ID,
			final Properties ctx) {
		MInvoice obj = null;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM C_Invoice WHERE IsActive = 'Y' AND EXME_CtaPac_ID = ? AND Posted = 'Y' ");
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_CtaPac_ID);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				obj = new MInvoice(ctx, rset, null);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}
		return obj;
	}

	/**
	 * Obtiene la lista de facturas no saldadas en su totalidad
	 *
	 * @param ctx
	 *            Contexto App
	 * @param date
	 *            Fecha Inicial
	 * @param date1
	 *            Fecha Final
	 * @param trxName
	 *            TrxName
	 * @return Listado de facturas que no estan totalmente saldadas
	 */
	public static List<ArchingBean> getArching(final Properties ctx,
			final Timestamp date, final Timestamp date1, final String trxName) {
		final List<ArchingBean> lst = new ArrayList<ArchingBean>();

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  invoice.c_invoice_id, ");
		sql.append("  invoice.linetotalamt, ");
		sql.append("  i.DateInvoiced, ");
		sql.append("  i.documentno ");
		sql.append("FROM ");
		sql.append("  (SELECT ");
		sql.append("      cl.c_invoice_id, ");
		sql.append("      SUM(cl.linenetamt - COALESCE(cl.discount, ");
		sql.append("      0) + cl.taxamt) ");
		sql.append("      AS linetotalamt ");
		sql.append("    FROM ");
		sql.append("      C_Invoice c ");
		sql.append("      INNER JOIN C_InvoiceLine cl ");
		sql.append("      ON (cl.C_Invoice_ID = c.C_Invoice_ID) ");
		sql.append("      INNER JOIN C_BPARTNER cb ");
		sql.append("      ON c.C_BPARTNER_ID = cb.C_BPARTNER_ID ");
		sql.append("    WHERE ");
		sql.append("      c.DateInvoiced BETWEEN ");
		sql.append("      ? AND ");
		sql.append("      ? AND ");
		sql.append("      c.AD_Client_ID = ? AND ");
		sql.append("      c.AD_Org_ID = ? AND ");
		sql.append("      cb.iscustomer = 'Y' AND ");
		sql.append("      cb.FACTURARASEG = ");
		sql.append("'N' GROUP BY ");
		sql.append("      cl.c_invoice_id ");
		sql.append("  ) invoice ");
		sql.append("  INNER JOIN c_invoice i ");
		sql.append("  ON invoice.c_invoice_id = i.c_invoice_id ");
		sql.append("ORDER BY ");
		sql.append("  i.DateInvoiced, i.documentno  ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setTimestamp(1, date);
			pstmt.setTimestamp(2, date1);
			pstmt.setInt(3, Env.getAD_Client_ID(ctx));
			pstmt.setInt(4, Env.getAD_Org_ID(ctx));
			rset = pstmt.executeQuery();
			final DecimalFormat dformat = new DecimalFormat("#,##0.00");
			while (rset.next()) {
				final int iid = rset.getInt(1);
				final BigDecimal total = rset.getBigDecimal(2);
				final BigDecimal payment = getPagos(ctx, iid, trxName);

				if (total.subtract(payment).compareTo(BigDecimal.ZERO) == 1) {
					String label = null;
					final ArchingBean bean = new ArchingBean();
					bean.setDate(rset.getTimestamp(3));
					bean.setFactNo(rset.getString(4));
					bean.setAmount(total);
					bean.setPayments(payment);
					bean.setBalance(total.subtract(payment));

					final String fmt = dformat.format(total);

					if (new BigDecimal(fmt).subtract(payment).compareTo(
							BigDecimal.ZERO) == 0) {
						label = "P";// Pagado
					} else if (new BigDecimal(fmt).subtract(payment).compareTo(
							total) == 0) {
						label = "NP";// No pagado
					} else {
						label = "PP";// Pago Parcial
					}

					bean.setStatus(label);

					lst.add(bean);
				}

			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rset, pstmt);
		}
		return lst;
	}

	/**
	 * Obtiene los pagos de una factura
	 *
	 * @param ctx
	 *            Contexto App
	 * @param iid
	 *            Id de la factura
	 * @param trxName
	 *            Trx Name
	 * @return Cantidad de pagos realizados
	 */
	private static BigDecimal getPagos(final Properties ctx, final int iid,
			final String trxName) {
		BigDecimal value = BigDecimal.ZERO;

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  amount ");
		sql.append("FROM ");
		sql.append("  c_Cashline ");
		sql.append("WHERE ");
		sql.append("  c_invoice_id = ? AND ");
		sql.append("  c_payment_id IS NULL ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("  p.payamt ");
		sql.append("FROM ");
		sql.append("  c_payment p ");
		sql.append("  INNER JOIN c_allocationline al ");
		sql.append("  ON p.c_payment_id = al.c_payment_id ");
		sql.append("WHERE ");
		sql.append("  al.c_invoice_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, iid);
			pstmt.setInt(2, iid);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				value = rset.getBigDecimal(1);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rset, pstmt);
		}
		return value;
	}

	/**
	 *
	 * @author
	 *
	 */
	public static class ArchingBean {
		private BigDecimal amount = BigDecimal.ZERO;
		private BigDecimal balance = BigDecimal.ZERO;
		private Timestamp date;
		private String factNo;
		private BigDecimal payments = BigDecimal.ZERO;
		private String serie;
		private String status;

		public BigDecimal getAmount() {
			return amount;
		}

		public BigDecimal getBalance() {
			return balance;
		}

		public Timestamp getDate() {
			return date;
		}

		public String getFactNo() {
			return factNo;
		}

		public BigDecimal getPayments() {
			return payments;
		}

		public String getSerie() {
			return serie;
		}

		public String getStatus() {
			return status;
		}

		public void setAmount(final BigDecimal amount) {
			this.amount = amount;
		}

		public void setBalance(final BigDecimal balance) {
			this.balance = balance;
		}

		public void setDate(final Timestamp date) {
			this.date = date;
		}

		public void setFactNo(final String factNo) {
			this.factNo = factNo;
		}

		public void setPayments(final BigDecimal payments) {
			this.payments = payments;
		}

		public void setSerie(final String serie) {
			this.serie = serie;
		}

		public void setStatus(final String status) {
			this.status = status;
		}
	}

	/**
	 * Facturacion directa al crear una factura
	 * Antes de este metodo debe tener el valor del socio de negocios
	 *
	 * @param extension
	 */
	public void setValues(final int extension, final String trxName) {

		if(getC_BPartner_ID()<=0){
			throw new MedsysException(Utilerias.getLabel("factExtension.error.findBPartner"));
		}
		
		setClientOrg(Env.getAD_Client_ID(getCtx()), Env.getAD_Org_ID(getCtx()));
		setEXME_EstServ_ID(Env.getEXME_EstServ_ID(getCtx()));
		setEstServ(new MEXMEEstServ(getCtx(), getEXME_EstServ_ID(), null).getName());
		setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(getCtx()));
		setAD_User_ID(Env.getAD_User_ID(getCtx()));
		setM_PriceList_ID(MEXMEConfigPre.getPriceList(getCtx(), trxName).getM_PriceList_ID());
		setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice);// Nota de remision
		setC_DocType_ID(0);
		
		setTrxType(X_C_Invoice.TRXTYPE_SalesReceipt);
		setTipoOperacion(X_C_Invoice.TIPOOPERACION_Others);
		
		setBackoffice(false);
		
		setIsSelfService(false);
		setSendEMail(false);
		setIsInDispute(false);
		setIsTaxIncluded(false);
		setIsDiscountPrinted(true);
		setIsApproved(false);
		setIsPrinted(false);
		setPosted(false);
		setProcessed(false);
		
		setDateOrdered(new Timestamp(System.currentTimeMillis()));
		setDateInvoiced(new Timestamp(System.currentTimeMillis()));
		setDateAcct(new Timestamp(System.currentTimeMillis()));
		
		final MBPartner bPartner = new MBPartner(getCtx(), getC_BPartner_ID(), trxName);
		setBPartner(bPartner);
		validatePaySchedule(trxName);
		
		if (getC_Currency_ID() == 0) {
			setC_Currency_ID(Env.getC_Currency_ID(getCtx()));
		}
		
	}

	public MPriceList getMPriceList() {
		final MPriceList plist = new MPriceList(getCtx(), getM_PriceList_ID(),
				get_TrxName());
		return plist;
	}

//	public ArrayList<MCashLine> getLstCashLines() {
//		return lstCashLines;
//	}
//
//	public void setLstCashLines(final ArrayList<MCashLine> lstChashLines) {
//		this.lstCashLines = lstChashLines;
//	}
//
//	public BigDecimal getMontoPagoDirecto() {
//		if (montoPagoDirecto.compareTo(Env.ZERO) == 0) {
//			getLstCashLine(true);
//		}
//		return montoPagoDirecto;
//	}
//
//	public void setMontoPagoDirecto(final BigDecimal montoPagoDirecto) {
//		this.montoPagoDirecto = montoPagoDirecto;
//	}

	/**
	 * Lineas de caja relacionadas a la factura con respecto a los pagos hechos
	 * en caja
	 *
	 * @param reload
	 *            : forzar a que se ejecute la consulta nuevamente
	 * @return listado de lineas de caja con dif C_Cash_ID
	 */
	public ArrayList<MCashLine> getLstCashLine(final boolean reload) {
		montoPagoDirecto = Env.ZERO;

		if (lstCashLines == null || lstCashLines.size() <= 0 || reload) {
			// Buscar si existen pagos asociados a la factura en C_CashLine
			final List<MCashLine> cashLine = MCashLine.getOfInvoiceList(
					getCtx(), getC_Invoice_ID(), get_TrxName());
			for (MCashLine mCashLine : cashLine) {
				lstCashLines.add(mCashLine);
				// aumentar el monto del pago
				montoPagoDirecto = montoPagoDirecto
						.add(mCashLine.getAmount());
			}// cashLine
		}
		return lstCashLines;
	}

//	public ArrayList<MAllocationLine> getLstAllocationLine() {
//		if (montoAnticipos.compareTo(Env.ZERO) == 0) {
//			getLstAllocation(true);
//		}
//		return lstAllocationLine;
//	}

//	public void setLstAllocationLine(
//			final ArrayList<MAllocationLine> lstAllocationLine) {
//		this.lstAllocationLine = lstAllocationLine;
//	}

//	public BigDecimal getMontoAnticipos() {
//		return montoAnticipos;
//	}
//
//	public void setMontoAnticipos(final BigDecimal montoAnticipos) {
//		this.montoAnticipos = montoAnticipos;
//	}

//	/**
//	 * Lineas de caja relacionadas a la factura con respecto a los pagos hechos
//	 * en caja
//	 *
//	 * @param reload
//	 *            : forzar a que se ejecute la consulta nuevamente
//	 * @return listado de lineas de caja con dif C_Cash_ID
//	 */
//	public List<MAllocationLine> getLstAllocation(final boolean reload) {
//		montoAnticipos = Env.ZERO;
//
//		if (lstCashLines == null || lstCashLines.size() <= 0 || reload) {
//			// Buscamos si existen Anticipos (AllocationLine) para la factura.
//			final MAllocationLine[] allocationLine = MEXMEAllocationLine
//					.getOfInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
//			for (int i = 0; i < allocationLine.length; i++) {
//				// Solo anticipos
//				if (allocationLine[i].getC_Payment_ID() > 0) {
//					lstAllocationLine.add(allocationLine[i]);
//					// aumentar el monto del pago
//					montoAnticipos = montoAnticipos.add(allocationLine[i]
//							.getAmount());
//				}
//			}// allocationline
//		}
//		return lstAllocationLine;
//	}

	/**
	 * getCtasFacturadasg
	 *
	 * @param ctx
	 * @param ctasI
	 * @param trxName
	 * @return
	 */
	public static int getCtasFacturadasg(final Properties ctx, final String ctasI, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT COUNT(DISTINCT si.C_Invoice_ID)  ")
		.append(" FROM c_invoice si ")
		.append(" INNER JOIN c_doctype sd ON sd.c_doctype_id = si.c_doctype_id \n")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_DocType.Table_Name, "sd"))
		.append(" 	AND sd.docbasetype = ? \n")//#1 ARI (factura - remision)
		.append(" WHERE si.isactive = 'Y' \n")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_Invoice.Table_Name, "si"))
		.append("\n AND   si.docstatus <> ? ")//#2 VO
		.append("   AND   si.issotrx = 'Y'  ")
		.append("   AND   si.EXME_CtaPac_ID IN (").append(ctasI).append(") ")
		.append("   AND   si.c_invoice_id NOT IN ( SELECT ssi.ref_invoice_id   ")
		.append("                                FROM c_invoice ssi          \n")
		.append("                                INNER JOIN c_doctype sd ON sd.c_doctype_id = ssi.c_doctype_id \n")
		.append("                                	AND sd.docbasetype = ?      ")//#3 ARC
		.append("                                	AND sd.docsubtypeso IS NULL \n")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_DocType.Table_Name, "sd"))
		.append("\n                              WHERE ssi.isactive = 'Y'       \n")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_Invoice.Table_Name, "ssi"))
		.append("                                AND   ssi.docstatus <> ? ")//#4 VO
		.append("                                AND   ssi.TrxType = 'N'  ")
		.append("                                AND   ssi.issotrx = 'Y') ");	
		return DB.getSQLValue(trxName, sql.toString(), //
			X_C_DocType.DOCBASETYPE_ARInvoice, // #1 ARI
			X_C_Invoice.DOCSTATUS_Voided, // #2 VO
			X_C_DocType.DOCBASETYPE_ARCreditMemo,// #3 ARC
			X_C_Invoice.DOCSTATUS_Voided// #2 VO
			);
	}

//	/**
//	 * Generacion de nota de credito
//	 *
//	 * @param ctx
//	 * @param oldInvoice
//	 * @param C_Cash_ID
//	 * @param C_CashBook_ID
//	 * @param motivoCancel
//	 * @param motivoCancelid
//	 * @param dateFactura
//	 * @param trxName
//	 * @return
//	 * @throws Exception
//	 */
//	public MInvoice notaCredito(final Properties ctx,
//			final MInvoice oldInvoice, final int C_Cash_ID,
//			final int C_CashBook_ID, final String motivoCancel,
//			final int motivoCancelid, final Timestamp dateFactura,
//			final String trxName) throws Exception {
//
//		// genera nota de credito con los datos de la factura
//		MInvoice notaCred = MEXMEInvoice.createNotaCredFromInvoice(
//				oldInvoice, new Timestamp(System.currentTimeMillis()),
//				C_CashBook_ID, C_Cash_ID, true, trxName);
//
//		if (notaCred == null) {
//			return notaCred;
//		}
//
//		if (!checaFactCanceladas(notaCred.getRef_Invoice_ID())) {
//			return null;
//		}
//
//		// se guardan los motivos de cancelacion capturados por el
//		// usario.
//		notaCred.setCanceledBy(Env.getAD_User_ID(ctx));
//		notaCred.setMotivoCancel(motivoCancel);
//		notaCred.setEXME_MotivoCancel_ID(motivoCancelid);
//		// Seteamos la fecha del campo fecha de facturacion.#04634 —
//		// La refacturacion se imprime con fecha original de factura
//		// (Fecha Actual) Ealvarez
//		notaCred.setDateInvoiced(dateFactura);
//		if (!notaCred.save(trxName)) {
//			notaCred = null;
//		} else {
//			notaCred.completeIt();
//			// Copiar el total a dos decimales
//			notaCred.setGrandTotal(oldInvoice.getGrandTotal().setScale(2,
//					BigDecimal.ROUND_HALF_UP));
//			if(!notaCred.save(trxName)){
//				sLog.log(Level.SEVERE, "No se actualizo la nota de credito en el gran total "+notaCred.getDocumentNo());
//			}
//		}
//		return notaCred;
//	}

//	/**
//	 * Generacion de la nueva factura
//	 *
//	 * @param ctx
//	 * @param oldInvoice
//	 * @param C_Cash_ID
//	 * @param C_CashBook_ID
//	 * @param isCtaPac
//	 * @param trxName
//	 * @return
//	 * @throws Exception
//	 */
//	public MInvoice prefactura(final Properties ctx,
//			final MInvoice oldInvoice, final int C_Cash_ID,
//			final int C_CashBook_ID, final boolean isCtaPac,
//			final String trxName) throws Exception {
//		// Generamos la prefactura (copia de la factura cancelada
//		MInvoice preFact = new MInvoice(ctx, 0, trxName);
//		// Datos a partir de la factura original
//		PO.copyValues(oldInvoice, preFact);
//		preFact.setC_Order_ID(0);
//		preFact.setRef_Invoice_ID(oldInvoice.getC_Invoice_ID());
//		// Actualizar la caja que genera la refacturacion
//		preFact.setC_Cash_ID(C_Cash_ID);
//		// Establecemos el valor del folio fiscal para la prefactura
//		preFact.setDocumentNoExt(MEXMEAreaCaja.createCounterDocExt(ctx,
//				MEXMECashBook.getAreaCajaID(C_CashBook_ID), trxName));
//		preFact.setIsTaxIncluded(false);
//
//		if (oldInvoice.getEXME_CtaPacExt_ID() > 0) {
//			preFact.setEXME_CtaPacExt_ID(oldInvoice.getEXME_CtaPacExt_ID());
//		}
//		// Calcula los totales de las lineas
//		// preFact.calcularTotales();
//		// MEXMETax.factura(preFact,oldInvoice.getGrandTotal());
//		if (!preFact.save(trxName)) {
//			preFact = null;
//			sLog.log(Level.SEVERE, "No se actualizo gererar la prefactura de la factura : " + oldInvoice.getDocumentNo());
//		} else {
//			// Copiar las lienas
//			preFact.copyLinesFrom(oldInvoice, trxName);
//			// Terminar el documento
//			preFact.completeIt();
//			// Copiar el total a dos decimales
//			preFact.setGrandTotal(oldInvoice.getGrandTotal().setScale(2,
//					BigDecimal.ROUND_HALF_UP));
//			if(!preFact.save(trxName)){
//				sLog.log(Level.SEVERE, "No se actualizo la prefactura en el gran total "+preFact.getDocumentNo());
//			}
//		}
//		return preFact;
//	}

	/**
	 *
	 * @param cashBookID
	 * @param cash
	 * @param operador
	 * @param extension
	 * @param trxName
	 * @return
	 */
	private void setValuesInv(final int cashBookID, final int cash,
			final MEXMEOperador operador, final MEXMECtaPacExt extension,
			final String trxName) {

		PO.copyValues(extension, this);
		setValues(0, trxName);
		setC_CashBook_ID(cashBookID);
		setC_Cash_ID(cash);
		setDocStatus(MInvoice.DOCSTATUS_InProgress);
		setAD_OrgTrx_ID(operador.getAD_OrgTrx_ID());
		setBackoffice(false);
		// Usuario que genera la factura
		// Por
		// defecto
		// efectivo
		setClientOrg(extension.getAD_Client_ID(), extension.getAD_Org_ID());
		setBPartner(extension.getBpartner());

		// Estacion de servicio de la cuenta paciente
		// Reemplazar por el nuevo
		final MEXMEEstServ estServ = new MEXMEEstServ(extension.getCtx(),
				extension.getCtaPac().getEXME_EstServ_ID(), null);
		setEstServ(estServ.getName());

		// fecha de facturacion
		setDateInvoiced(new Timestamp(System.currentTimeMillis()));
		setDateAcct(new Timestamp(System.currentTimeMillis()));

		// paciente y medico
		setEXME_Paciente_ID(extension.getCtaPac().getEXME_Paciente_ID());
		setNombre_Paciente(MEXMEPaciente.getNombre_PacByCtaPac(extension.getEXME_CtaPac_ID(), null));//extension.getCtaPac().getPaciente().getFullName());
		setEXME_Medico_ID(extension.getCtaPac().getEXME_Medico_ID());
		setNombre_Medico(extension.getCtaPac().getMedico().getFullName());
		setSexo(extension.getCtaPac().getPaciente().getSexo());

		// Guardar la Cuenta Paciente
		setEXME_CtaPac_ID(extension.getEXME_CtaPac_ID());
		setEXME_CtaPacExt_ID(extension.getEXME_CtaPacExt_ID());

		// setGrandTotal(extension.getGrandTotal());
		// setDiscountAmt(extension.getDiscountAmt()); //
		setDiscountAmt(extension.getDesctoGlobalAmt()); //

		// Establecemos el RFC.
		setPOReference(extension.getRfc());
		// La descripcion guarda el Nombre completo del BPartner al que se le va
		// a facturar.
		setDescription(extension.getDescription());
		setObservation(extension.getObservacion()); //Card 1052 Comentarios en la Factura(Estándar)
		setIsOrderFacLineCategory(extension.isOrderFacLineCategory()); //RQM 9437  Reporte no esta ordenado por categoría // Se setea el valor

		if (extension.isDiscPercent()) {
			setDiscountPorcent(extension.getDesctoGlobal()); // Solo del Desc. Global.
		} else {
			setDiscountPorcent(Env.ZERO);
		}
		if(!setLocationExt(extension)){
			throw new MedsysException(Utilerias.getLabel("factExtension.error.createInvoiceExt"));
		}

		setFechaNac(new Timestamp(extension.getCtaPac().getPaciente()
				.getFechaNac().getTime()));
		if (getC_BPartner().isFacturarAseg()) {
			setInvoicePhone(getC_BPartner_Location().getPhone());
		} else {
			setInvoicePhone(extension.getCtaPac().getPaciente()
					.getTelParticular());
		}
		
//		setTaxAmt(extension.getTaxAmt());//FIXME Impuesto despues o antes de ??

		if (!save(trxName)) {
			throw new MedsysException(Utilerias.getLabel("factExtension.error.createInvoiceExt"));
		}
	}

//	/**
//	 * Impuestos de la extension y cargos
//	 *
//	 * @param ctx
//	 * @param pCtaPacExtID
//	 * @param trxName
//	 * @throws Exception
//	 */
//	public void impuestos(final Properties ctx, final int pCtaPacExtID,
//			final String trxName) throws Exception {
//		if (pCtaPacExtID > 0) {
//			// Actualizamos la extension con su nuevo impuesto
//			MEXMETax.extension(ctx, pCtaPacExtID, trxName);
//		}
//	}
//
//	/**
//	 * Relacion de la extension facturada y la factura
//	 *
//	 * @param ctx
//	 * @param pCtaPacExtID
//	 * @param newInvoiceId
//	 * @param trxName
//	 * @return
//	 */
//	public String extensiones(final Properties ctx, final int pCtaPacExtID,
//			final int newInvoiceId, final String trxName) {
//		String errores = "";
//		if (pCtaPacExtID > 0) {
//			final MEXMECtaPacExt extn = new MEXMECtaPacExt(ctx, pCtaPacExtID,
//					trxName);
//			extn.setC_Invoice_ID(newInvoiceId);
//			if (!extn.save(trxName)) {
//				errores = errores.concat(Utilerias
//						.getLabel("error.caja.prefactura.noSave"));
//
//			}
//		}
//		return errores;
//	}
//
//	/**
//	 * Relacion de nueva factura y la cita medica
//	 *
//	 * @param ctx
//	 * @param oldInvoiceId
//	 * @param newInvoiceId
//	 * @param trxName
//	 * @return
//	 * @throws SQLException
//	 */
//	public String citaMedica(final Properties ctx, final int oldInvoiceId,
//			final int newInvoiceId, final String trxName) throws SQLException {
//		String errores = "";
//		// Actualizamos la cita se asigna el ID de la nueva factura.
//		final MEXMECitaMedica cita = MEXMECitaMedica.getForInvoice(ctx,
//				oldInvoiceId, trxName);
//		if (cita != null && cita.getC_Invoice_ID() > 0) {
//			cita.setC_Invoice_ID(newInvoiceId);
//			if (!cita.save(trxName)) {
//				errores = errores.concat(Utilerias
//						.getLabel("error.caja.prefactura.noSave"));
//			}
//		}
//
//		return errores;
//	}
//
//	/**
//	 * Relacionar embarque con nueva factura
//	 *
//	 * @param ctx
//	 * @param oldInvoiceId
//	 * @param newInvoiceId
//	 * @param trxName
//	 * @return
//	 */
//	public String embarque(final Properties ctx, final int oldInvoiceId,
//			final int newInvoiceId, final String trxName) {
//		String errores = "";
//		// Traer el embarque relacionado a la factura
//		final MInOut original = MEXMEInOut.getOfInvoice(ctx, oldInvoiceId,
//				trxName);
//		// Si encontro un embarque
//		if (original != null) {
//			// asociar la nueva prefactura al embarque original
//			original.setC_Invoice_ID(newInvoiceId);
//			// se cambia parametro correcto de nombre de transaccion.
//			// Jesus Cantu.
//			if (!original.save(trxName)) {
//				errores = errores.concat(Utilerias.getLabel(
//						"error.factDirecta.facturar.noSaveInOut",
//						String.valueOf(original.getDocumentNo())));
//			}
//		}
//
//		return errores;
//	}

//	/**
//	 * Metodo que verifica si la factura ya ha sido cancelada
//	 *
//	 * @param fact_id
//	 *            es el id de la factura a cancelar
//	 * @return true si es posible cancelar la factura
//	 */
//	private boolean checaFactCanceladas(int fact_id) {
//		boolean band = true;
//
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//
//		String sql = "SELECT * from C_Invoice where ref_invoice_id = ? "
//				+ " and isActive = 'Y'";
//
//		try {
//			pstm = DB.prepareStatement(sql, null);
//			pstm.setInt(1, fact_id);
//			rs = pstm.executeQuery();
//
//			if (rs.next())
//				band = false;
//
//		} catch (Exception e) {
//
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (pstm != null)
//					pstm.close();
//			} catch (Exception e) {
//			}
//		}
//
//		return band;
//	}

	/**
	 *
	 * @param extension
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	private void createLines(final MEXMECtaPacExt extension,
			final String trxName) throws SQLException {
		// Cargar los lineas a facturar
		extension.cargarLineasDeExtension(true, false, true);
		final MCtaPacDet[] eLines = extension.getLineasDeExtension();
		// Iterar para crear las lineas de la factura
		for (int i = 0; i < eLines.length; i++) {
			final MCtaPacDet cLine = eLines[i];
			final MInvoiceLine iLine = new MInvoiceLine(getCtx(), 0, trxName);
			iLine.setValuesCtaPacDet(cLine);
			iLine.setC_Invoice_ID(getC_Invoice_ID());// Oblig
			iLine.setInvoice(this);
			iLine.setEXME_CtaPacDet_ID(cLine.getEXME_CtaPacDet_ID());

			// Se guarda la linea de factura
			if (!iLine.save(trxName)) {
				throw new MedsysException(Utilerias.getLabel("error.bitacoraBanco.save")+": "
						+Utilerias.getLabel("factExtension.error.createInvoice"));
			}
		}
	}

	/**
	 * Agregar a las remisiones el estatus de DR
	 * @param extension: Extension
	 * @param trxName: Nombre de transacción
	 */
	private void complete(final MEXMECtaPacExt extension,
			final String trxName) {
		// Manually Process Invoice
		setDocStatus(completeIt());
		if(DocAction.STATUS_Invalid.equals(getDocStatus())){
			throw new MedsysException(
					Utilerias.getLabel("error.tableroCamas.invalidStatus")+" "+
					Utilerias.getLabel("factExtension.error.createInvoiceExt", getDocumentNo()));
		}
		setPosted(false);
		setProcessed(false, trxName);
		setScaleTotals();
		setIsPrintedPre(true);// antes de mandar a SHCP
		if (!save(trxName)) {
			throw new MedsysException(Utilerias.getLabel("factExtension.error.createInvoiceExt"));
		}
	}

	/** Poner los totales de la factura a 2 decimales */
	public void setScaleTotals(){
		// Si el subtotal viene menor o igual a cero, setear el de la extension.
		if (super.getTotalLines().scale() > getPrecision()){
			setTotalLines(getTotalLines().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP));
		}
		// Dos decimales
		if(super.getGrandTotal().scale() > getPrecision()){
			setGrandTotal(getGrandTotal().setScale(getPrecision(),BigDecimal.ROUND_HALF_UP));
		}
		// Si el gran total viene menor a cero, setear el valor de cero.
		if (getGrandTotal().compareTo(Env.ZERO) <= 0) {
			setGrandTotal(new BigDecimal(0.01));
		}
	}

	/**
	 * Genera una nota de cr&eacute;dito a partir de una factura
	 *
	 * @param mInvoice
	 *            La factura original
	 * @param dateDoc
	 *            La fecha de la nota de cr&eacut;dito
	 * @param C_CashBook_ID
	 *            El identificador de la caja f&iacut;sica
	 * @param C_Cash_ID
	 *            El identificador de la caja (0 si es null)
	 * @param trxName
	 *            el nombre de la transacci&oacute;n
	 * @return
	 * @throws Exception
	 */
	public static MInvoice createNotaCredCxC(
			final MInvoice mInvoice,
			final Timestamp  dateDoc,
			final BigDecimal discountAmt,
			final String trxName) {

		MInvoice notaCred = null;
		if(mInvoice==null || discountAmt.compareTo(Env.ZERO)==0){
			return null;
		}

		try {

			final BigDecimal factor = (discountAmt
					.divide(mInvoice.getGrandTotal(),6,BigDecimal.ROUND_HALF_UP))
					.multiply(Env.ONEHUNDRED);//25%


//			final BigDecimal taxamt = mInvoice.getTaxAmt().multiply(factor.divide(Env.ONEHUNDRED));// 4424.66816000
			final BigDecimal totalLines  = mInvoice.getTotalLines().multiply(factor.divide(Env.ONEHUNDRED));// 27654.17600000

			// Tipo de documento nota de credito
			final MDocType[] types = MEXMEDocType.getOfDocBaseType(
					mInvoice.getCtx(), MDocType.DOCBASETYPE_ARCreditMemo);
			int C_DocType_ID = 0;
			if (types.length > 0) { // get first
				C_DocType_ID = types[0].getC_DocType_ID();
			} else {
				throw new Exception("error.caja.tipoDoc.notaCred");
			}

			// Generacion de nota de credito
			notaCred = new MInvoice(mInvoice.getCtx(), 0, trxName);
			// Datos de la factura original
			PO.copyValues(mInvoice, notaCred);
			//Lama : cuando se copia los valores si la fact ya esta posteada marca como posted la nota de credito
			notaCred.setBackoffice(false);
			notaCred.setIsApproved(false);
			notaCred.setIsPrinted(false);
			notaCred.setPosted(false);
			notaCred.setProcessed(false);
			// Asignamos el tipo de documento
			notaCred.setC_DocType_ID(C_DocType_ID);
			notaCred.setC_DocTypeTarget_ID(C_DocType_ID);
			// Fecha actual
			notaCred.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
			notaCred.setDateAcct(new Timestamp(System.currentTimeMillis()));
			notaCred.setDateOrdered(mInvoice.getDateOrdered());
			//			// asignamos la caja
			//			notaCred.setC_Cash_ID(C_Cash_ID);
			notaCred.setRef_Invoice_ID(mInvoice.getC_Invoice_ID());
			notaCred.setAfecta_Caja(mInvoice.isAfecta_Caja());// solo cuando se cobra en caja o es
			// pagada totalmente con anticipos
			notaCred.setIsPaid(true);// se paga con la nota de credito
			notaCred.setIsTaxIncluded(false);
			notaCred.setDescription(Utilerias.getLabel("caja.msg.notaCred",mInvoice.getDocumentNo()));
			notaCred.setTrxType(X_C_Invoice.TRXTYPE_CreditNotePromptPayment);
			notaCred.setTotalLines(totalLines);
			notaCred.setGrandTotal(discountAmt);
			notaCred.setSELLO(null);
			notaCred.setUUID(null);
			notaCred.setCadena(null);

			if (!notaCred.save(trxName)) {
				throw new Exception("error.caja.notaCred.noSave");
			}
			// Leyenda de referencia
			mInvoice.getTaxes(true);
			for (int i = 0; i < mInvoice.getLinesTaxes().length; i++) {
				final MInvoiceTax mBean = mInvoice.getLinesTaxes()[i];

				if(mBean.getTaxBaseAmt().compareTo(Env.ZERO)>0){
					final BigDecimal taxamtLn = mBean.getTaxAmt().multiply(factor.divide(Env.ONEHUNDRED));// 4424.66816000
					final BigDecimal totalLinesLn  = mBean.getTaxBaseAmt().multiply(factor.divide(Env.ONEHUNDRED));// 27654.17600000

					final MInvoiceLine line = new MInvoiceLine(mInvoice.getCtx(), 0, trxName);
					line.setAD_Client_ID(notaCred.getAD_Client_ID());
					line.setAD_Org_ID(notaCred.getAD_Org_ID());
					line.setInvoice(notaCred);
					line.setC_Invoice_ID(notaCred.getC_Invoice_ID());
					line.setDescription(notaCred.getDescription());
					line.setC_Charge_ID(notaCred.getPaymentTerm().getC_Charge_ID());
					line.setC_Tax_ID(mBean.getC_Tax_ID());
					line.setQty(Env.ONE);
					line.setPriceList(totalLinesLn);// Oblig
					line.setPriceLimit(totalLinesLn);// Oblig
					line.setPriceActual(totalLinesLn);// Oblig
					line.setPriceEntered(totalLinesLn);// Oblig
					line.setTaxAmt(taxamtLn);
					line.setLineNetAmt();// qty x price
					line.setLineTotalAmt(totalLinesLn.add(taxamtLn));

					if (!line.save(trxName)) {
						throw new Exception("error.caja.notaCred.noSave");
					}
				}
			}

			if(notaCred!=null){
				notaCred.completeIt();
				notaCred.setProcessed(true);// no tiene caja se completa
				notaCred.updateStatusComplete();//no tiene caja se completa
				notaCred.save(trxName);
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "MEXMEInvoice.createNotaCredFromInvoice", e);
			notaCred = null;
		}

		return notaCred;
	}

	/**
	 * Genera una nota de cr&eacute;dito a partir de una factura
	 *
	 * @param mInvoice
	 *            La factura original
	 * @param dateDoc
	 *            La fecha de la nota de cr&eacut;dito
	 * @param C_CashBook_ID
	 *            El identificador de la caja f&iacut;sica
	 * @param C_Cash_ID
	 *            El identificador de la caja (0 si es null)
	 * @param trxName
	 *            el nombre de la transacci&oacute;n
	 * @return
	 * @throws Exception
	 */
	public static MInvoice createNotaCreditoParcial(
			final MInvoice mInvoice,
			final Timestamp  dateDoc,
			final BigDecimal discountAmt,
			final boolean ispaid,
			final int chargeID,
			final String descripcion,
			final String trxName) {

		MInvoice notaCred = null;
		if(mInvoice==null || discountAmt.compareTo(Env.ZERO)==0){
			return null;
		}

		try {

			final BigDecimal factor = (discountAmt
					.divide(mInvoice.getGrandTotal(),6,BigDecimal.ROUND_HALF_UP))
					.multiply(Env.ONEHUNDRED);//25%


//			final BigDecimal taxamt = mInvoice.getTaxAmt().multiply(factor.divide(Env.ONEHUNDRED));// 4424.66816000
			final BigDecimal totalLines  = mInvoice.getTotalLines().multiply(factor.divide(Env.ONEHUNDRED));// 27654.17600000

			// Tipo de documento nota de credito
			final MDocType[] types = MEXMEDocType.getOfDocBaseType(mInvoice.getCtx(), MDocType.DOCBASETYPE_ARCreditMemo);
			int C_DocType_ID = 0;
			if (types.length > 0) { // get first
				C_DocType_ID = types[0].getC_DocType_ID();
			} else {
				throw new Exception("error.caja.tipoDoc.notaCred");
			}

			// Generacion de nota de credito
			notaCred = new MInvoice(mInvoice.getCtx(), 0, trxName);
			// Datos de la factura original
			PO.copyValues(mInvoice, notaCred);
			//Lama : cuando se copia los valores si la fact ya esta posteada marca como posted la nota de credito
			notaCred.setBackoffice(false);
			notaCred.setIsApproved(false);
			notaCred.setIsPrinted(false);
			notaCred.setPosted(false);
			notaCred.setProcessed(false);
			// Asignamos el tipo de documento
			notaCred.setC_DocType_ID(C_DocType_ID);
			notaCred.setC_DocTypeTarget_ID(C_DocType_ID);
			// Fecha actual
			notaCred.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
			notaCred.setDateAcct(new Timestamp(System.currentTimeMillis()));
			notaCred.setDateOrdered(mInvoice.getDateOrdered());
			//			// asignamos la caja
			//			notaCred.setC_Cash_ID(C_Cash_ID);
			notaCred.setRef_Invoice_ID(mInvoice.getC_Invoice_ID());
			notaCred.setAfecta_Caja(mInvoice.isAfecta_Caja());// solo cuando se cobra en caja o es
			// pagada totalmente con anticipos
			notaCred.setIsPaid(ispaid);// se paga con la nota de credito
			notaCred.setRebate(!ispaid);
			notaCred.setIsTaxIncluded(false);
//			notaCred.setDescription(Utilerias.getLabel("caja.msg.notaCred",mInvoice.getDocumentNo()));
			notaCred.setTrxType(X_C_Invoice.TRXTYPE_PartialCreditNoteCustomer);
			notaCred.setTotalLines(totalLines);
			notaCred.setGrandTotal(discountAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
			notaCred.setSELLO(null);
			notaCred.setUUID(null);
			notaCred.setCadena(null);
			notaCred.setIsOrderFacLineCategory(false);

			if (!notaCred.save(trxName)) {
				throw new Exception("error.caja.notaCred.noSave");
			}
			// Leyenda de referencia
			mInvoice.getTaxes(true);
			for (int i = 0; i < mInvoice.getLinesTaxes().length; i++) {
				final MInvoiceTax mBean = mInvoice.getLinesTaxes()[i];
				//FIXME Verificar proceso con twry GADC
				if(mBean.getTaxBaseAmt().compareTo(Env.ZERO) != 0){
					final BigDecimal taxamtLn = mBean.getTaxAmt().multiply(factor.divide(Env.ONEHUNDRED)).setScale(6, BigDecimal.ROUND_HALF_UP);// 4424.66816000
					final BigDecimal totalLinesLn  = mBean.getTaxBaseAmt().multiply(factor.divide(Env.ONEHUNDRED)).setScale(6, BigDecimal.ROUND_HALF_UP);// 27654.17600000

					final MInvoiceLine line = new MInvoiceLine(mInvoice.getCtx(), 0, trxName);
					line.setAD_Client_ID(notaCred.getAD_Client_ID());
					line.setAD_Org_ID(notaCred.getAD_Org_ID());
					line.setInvoice(notaCred);
					line.setC_Invoice_ID(notaCred.getC_Invoice_ID());
					line.setDescription(notaCred.getDescription());
					if(!StringUtils.isEmpty(descripcion)){
						line.setDescriptionServ(descripcion.trim());
					}
					line.setC_Charge_ID(chargeID);
					line.setC_Tax_ID(mBean.getC_Tax_ID());
					line.setQty(Env.ONE);
					line.setPriceList(totalLinesLn);// Oblig
					line.setPriceLimit(totalLinesLn);// Oblig
					line.setPriceActual(totalLinesLn);// Oblig
					line.setPriceEntered(totalLinesLn);// Oblig
					line.setTaxAmt(taxamtLn);
					line.setLineNetAmt();// qty x price
					line.setLineTotalAmt(totalLinesLn.add(taxamtLn));

					if (!line.save(trxName)) {
						throw new Exception("error.caja.notaCred.noSave");
					}
				}
			}

			if(notaCred!=null){
				notaCred.completeIt();
				notaCred.setProcessed(true);// no tiene caja se completa
				notaCred.updateStatusComplete();//no tiene caja se completa
				notaCred.save(trxName);
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, "MEXMEInvoice.createNotaCredFromInvoice", e);
			notaCred = null;
		}

		return notaCred;
	}

	/**
	 * Crear factura a partir de la extension
	 * @param extension: Extension a facturar
	 * @param cashBookID: Caja
	 * @param openCash: Diario de caja y operador
	 * @param trxName: Nombre de transacción
	 * @return Id de la factura
	 * @throws SQLException
	 */
	public int createInvoice(final MEXMECtaPacExt extension,
			final int cashBookID,final MEXMEOperador openCash,
			final String trxName) throws SQLException{
		// Crear encabezado a partir de la extension
		setValuesInv(cashBookID
				, openCash.getCashId()
				, openCash
				, extension
				, trxName);
		// Crear linas a partir de los cargos
		createLines(extension, trxName);
		// Completa la factura
		complete(extension, trxName);
		return getC_Invoice_ID();
	}

	/**
	 * getFormasDePago
	 *
	 * @param ctx
	 * @param ctasI
	 * @param trxName
	 * @return
	 */
	public static List<MFormaPago> getFormasDePago(final Properties ctx,
			final int CinvoiceID, final String trxName) {
		// Fecha actual
		final List<MFormaPago> list = new ArrayList<MFormaPago>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT * FROM EXME_FormaPago WHERE EXME_FormaPago_ID IN ( ")//
			// Asignaciones de la "FACTURA" por medio de pagos
			.append(" ( SELECT c.exme_formapago_id FROM c_cashline c")//
			.append(" 	INNER JOIN c_allocationline l ON l.c_payment_id = c.c_payment_id ")//
			.append(" 	WHERE l.c_invoice_id = ?  ")//
			.append("   group by c.exme_formapago_id ) ")//

			.append(" UNION ")//
			// Cobros realizados en caja hacia la nota de remision de CXC de esta manera sabremos si es a credito
			.append(" ( SELECT C.exme_formapago_id ")//
			.append("   FROM C_CashLine c  ")//
			.append("   INNER JOIN C_Invoice i ON (i.C_Invoice_id=? AND ")//
			.append("      (i.C_Invoice_id=c.c_Invoice_id OR i.Ref_Invoice_Sales_ID=c.c_Invoice_id)) ")//
			.append("   WHERE c.isActive='Y' AND c.cashtype=?  ")//
			.append("   group by c.exme_formapago_id ) ") //

			.append(" UNION ")//
			// Asignaciones de la "FACTURA" por medio de cobros en caja
			.append(" ( SELECT c.exme_formapago_id FROM c_cashline c ")//
			.append(" 	INNER JOIN c_allocationline l ON l.c_cashline_id = c.c_cashline_id ")//
			.append(" 	WHERE l.c_invoice_id = ?  ")//
			.append("   group by c.exme_formapago_id ) ) ");//

		PreparedStatement pstmt = null;
		ResultSet rs = null; //

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, CinvoiceID);
			pstmt.setInt(2, CinvoiceID);
			pstmt.setString(3, MCashLine.CASHTYPE_CuentasXCobrar);
			pstmt.setInt(4, CinvoiceID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MFormaPago(ctx, rs, null));
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * getFormasDePago
	 *
	 * @param ctx
	 * @param ctasI
	 * @param trxName
	 * @return
	 */
	public static List<MCashLine> getCashLines(final Properties ctx,
			final int CinvoiceID, final String trxName) {
		// Fecha actual
		final List<MCashLine> list = new ArrayList<MCashLine>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY)			
		.append(" (select * from c_cashline where c_payment_id in  ")
		.append(" (select c_payment_id from c_allocationline where c_invoice_id = ? ) ")
		.append(" ) ")
		.append(" union ")
		.append(" (select * from c_cashline where c_cashline_id in ") 
		.append(" (select c_cashline_id from c_allocationline where c_invoice_id = ? ) ")
		.append(" ) ");

		PreparedStatement pstmt = null;
		ResultSet rs = null; //

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, CinvoiceID);
			pstmt.setInt(2, CinvoiceID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MCashLine(ctx, rs, null));
			}

		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Obtiene la factura de la remision
	 * @param trxName Nombre de transacción
	 * @return
	 */
	public MInvoice existInvoiceTarget(final String trxName) {
		return existInvoiceTarget(trxName, true);
	}

	/**
	 * Obtiene la factura de la remision
	 * @param verFactGlobal true: Devolver la factura global
	 * @param trxName Nombre de transacción
	 * @return
	 */
	public MInvoice existInvoiceTarget(final String trxName, final boolean verFactGlobal) {
		// Fecha actual
		MInvoice invoice = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(MInvoice.getCInvoiceCustomerV(getCtx(), false))
		.append(" AND si.Ref_Invoice_Sales_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null; //

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, getC_Invoice_ID());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				 invoice = new MInvoice(getCtx(), rs, trxName);
			}

			// Factura global
			if(invoice==null && verFactGlobal && getMultiple_ID()>0){
				invoice = new MInvoice(getCtx(), getMultiple_ID(), trxName);
			}


		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return invoice;
	}

	/** Buscar los métodos de pago */
	public static String getMetodoPago(MInvoice invoice){
		String metodoPago = StringUtils.EMPTY;
		if(invoice != null){
			metodoPago = getMetodoPagoStr(invoice);
		}
		return metodoPago;
	}

	/** Nombre del método de pago */
	private static String getMetodoPagoStr(MInvoice invoice){
		List<MFormaPago> lstPagos = MInvoice.getFormasDePago(invoice.getCtx(), invoice.getC_Invoice_ID(), null);
		if(lstPagos.isEmpty() || lstPagos.size() > 1){ //#06153 ealvarez si es una factura se usa el mtdo getRef_Invoice_Sales_ID()
			lstPagos = MInvoice.getFormasDePago(invoice.getCtx(), invoice.getRef_Invoice_Sales_ID(), null);
		}	
		if(lstPagos.isEmpty() || lstPagos.size() > 1){
			return "NO IDENTIFICADO";

		}else{
			if(MFormaPago.PAYMENTRULE_OnCredit.equalsIgnoreCase(lstPagos.get(0).getPaymentRule())){
				if(invoice.getCliente() != null){
					MPaymentTerm term = (MPaymentTerm) invoice.getCliente().getC_PaymentTerm();
					return term != null && term.getName() != null ? term.getName() : "NO IDENTIFICADO";
				}
			}else{
				if(lstPagos.get(0).getName() == null){
					return "NO IDENTIFICADO";
				}else{
					return lstPagos.get(0).getName();
				}
			}
		}
		return "NO IDENTIFICADO";
	}


	BigDecimal saldo = BigDecimal.ZERO;
	BigDecimal pagos = BigDecimal.ZERO;
	BigDecimal creditos = BigDecimal.ZERO;

	/**
	 * Obtenemos la suma de todas las formas de pago relacionadas a un pago agrupadas
	 * si es que se repiten las formas de pago y sumamos sus totales
	 *
	 * @param ID de la factura
	 * @param trxName
	 * @return Sumatoria de los pagos a la factura
	 */
	public BigDecimal calculaPago(String trxName) {
			BigDecimal ret = BigDecimal.ZERO;
//			ret.setScale(2,  BigDecimal.ROUND_HALF_UP);
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			sql.append(" SELECT COALESCE( SUM (Amount),0 ) FROM  ");
			sql.append(" ( ");
//			sql.append(" --pagos directos a facturas ");
			sql.append(" SELECT COALESCE( SUM (l.Amount),0 ) as Amount ");
			sql.append(" FROM C_AllocationLine l ");
			sql.append(" INNER JOIN c_cashline c ON l.c_cashline_id=c.c_cashline_id");
			sql.append(" WHERE l.isactive='Y' AND l.c_invoice_id=? ");
			sql.append(" and c.cashtype<>? ");
			sql.append(" UNION ");
//			sql.append(" --anticipos relacionados a la factura ");
			sql.append(" SELECT COALESCE( SUM (l.Amount),0 ) as Amount ");
			sql.append(" FROM c_allocationline l ");
			sql.append(" INNER JOIN c_payment P ON l.c_payment_id=p.c_payment_id");
			sql.append(" WHERE l.isactive='Y' AND l.c_invoice_id=? ");
//			sql.append(" UNION ");
//			sql.append(" --notas de credito parciales ");
//			sql.append(" SELECT COALESCE( SUM (ssi.Grandtotal),0 ) as Amount ");
//			sql.append(" FROM c_invoice ssi ");
//			sql.append(" INNER JOIN c_doctype sd ON sd.c_doctype_id = ssi.c_doctype_id ");
//			sql.append(" AND sd.isactive = 'Y'       ");
//			sql.append(" AND sd.docbasetype = 'ARC' ");
//			sql.append(" AND sd.docsubtypeso IS NULL ");
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx()," ", X_C_DocType.Table_Name, "sd"));
//			sql.append(" WHERE ssi.isactive  = 'Y' and ssi.Ref_Invoice_ID = ? and ssi.trxtype = ? ");
//			if(isPaid) {
//				sql.append(" AND ssi.isPaid='Y' ");
//			}
			sql.append(" ) as pagos ");

			java.sql.PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, getC_Invoice_ID());
				pstmt.setString(2, MCashLine.CASHTYPE_CuentasXCobrar);
				pstmt.setInt(3, getC_Invoice_ID());				

				rs = pstmt.executeQuery();
				if (rs.next()) {
					ret = rs.getBigDecimal(1);
				}
			} catch (final Exception e) {
				sLog.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}

			return ret==null?Env.ZERO:ret;
		}
	
	public BigDecimal getCredits(boolean isPaid, String trxName) {
		BigDecimal ret = BigDecimal.ZERO;
//		ret.setScale(2,  BigDecimal.ROUND_HALF_UP);
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT COALESCE( SUM (Amount),0 ) FROM  ");
		sql.append(" ( ");
//		sql.append(" --pagos directos a facturas ");
//		sql.append(" SELECT COALESCE( SUM (l.Amount),0 ) as Amount ");
//		sql.append(" FROM C_AllocationLine l ");
//		sql.append(" INNER JOIN c_cashline c ON l.c_cashline_id=c.c_cashline_id");
//		sql.append(" WHERE l.isactive='Y' AND l.c_invoice_id=? ");
//		sql.append(" and c.cashtype<>? ");
//		sql.append(" UNION ");
//		sql.append(" --anticipos relacionados a la factura ");
//		sql.append(" SELECT COALESCE( SUM (l.Amount),0 ) as Amount ");
//		sql.append(" FROM c_allocationline l ");
//		sql.append(" INNER JOIN c_payment P ON l.c_payment_id=p.c_payment_id");
//		sql.append(" WHERE l.isactive='Y' AND l.c_invoice_id=? ");
//		sql.append(" UNION ");
//		sql.append(" --notas de credito parciales ");
		sql.append(" SELECT COALESCE( SUM (ssi.Grandtotal),0 ) as Amount ");
		sql.append(" FROM c_invoice ssi ");
		sql.append(" INNER JOIN c_doctype sd ON sd.c_doctype_id = ssi.c_doctype_id ");
		sql.append(" AND sd.isactive = 'Y'       ");
		sql.append(" AND sd.docbasetype = 'ARC' ");
		sql.append(" AND sd.docsubtypeso IS NULL ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx()," ", X_C_DocType.Table_Name, "sd"));
		sql.append(" WHERE ssi.isactive  = 'Y' ");
		
		sql.append(" AND ssi.c_invoice_id not in ( ");
		sql.append("				select ard.Ref_Invoice_ID  ");
		sql.append("				from c_invoice ard ");
		sql.append("				INNER JOIN c_doctype sdd ON sdd.c_doctype_id = ard.c_doctype_id  AND sd.isactive = 'Y' ");
		sql.append("				AND sdd.docbasetype = 'ARD'   ");
		sql.append("				AND sdd.docsubtypeso IS NULL   ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx()," ", X_C_DocType.Table_Name, "sdd"));
		sql.append("			) ");
		
		sql.append(" and ssi.Ref_Invoice_ID = ? ");
		sql.append(" and ssi.trxtype = ? ");
		if(isPaid) {
			sql.append(" AND ssi.isPaid='Y' ");
		}
		sql.append(" ) as pagos ");

		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, getC_Invoice_ID());
//			pstmt.setString(2, MCashLine.CASHTYPE_CuentasXCobrar);
//			pstmt.setInt(3, getC_Invoice_ID());
			pstmt.setInt(1, getC_Invoice_ID());
			pstmt.setString(2, TRXTYPE_PartialCreditNoteCustomer);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				ret = rs.getBigDecimal(1);
			}
		} catch (final Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ret==null?Env.ZERO:ret;
	}
	
	/**
	 * Obtiene el total de los montos de las facturas de credito parciales
	 * 
	 * @param isPaid
	 * @param trxName
	 * @return
	 */
	public BigDecimal getTotalPartialCreditNote(boolean isPaid, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT COALESCE( SUM (ssi.Grandtotal),0 ) as Amount ");
		sql.append(" FROM c_invoice ssi ");
		sql.append(" INNER JOIN c_doctype sd ON sd.c_doctype_id = ssi.c_doctype_id ");
		sql.append(" AND sd.isactive = 'Y'       ");
		sql.append(" AND sd.docbasetype = 'ARC' ");//nota credito
		sql.append(" AND sd.docsubtypeso IS NULL ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", X_C_DocType.Table_Name, "sd"));
		sql.append(" WHERE ssi.isactive='Y' ");
		sql.append(" AND ssi.Ref_Invoice_ID=? ");// factura
		sql.append(" AND ssi.trxtype=? ");// nota credito parcial
		if (isPaid) {
			sql.append(" AND ssi.isPaid='Y' ");
		}
		return DB.getSQLValueBD(trxName, sql.toString(), getC_Invoice_ID(), TRXTYPE_PartialCreditNoteCustomer);
	}

	/**
	 * Obtiene el total de los pagos directos hechos a la factura/nota
	 * 
	 * @param trxName
	 * @return
	 */
	public BigDecimal getTotalCashline(String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT COALESCE( SUM (l.Amount),0 ) as Amount ");
		sql.append(" FROM C_AllocationLine l ");
		sql.append(" LEFT JOIN c_cashline c ON l.c_cashline_id=c.c_cashline_id AND c.cashtype=? ");
		sql.append(" LEFT JOIN c_payment  p ON l.c_payment_id=p.c_payment_id   AND p.type=? ");
		sql.append(" WHERE l.isactive='Y' ");
		sql.append(" AND l.c_invoice_id=? ");
		sql.append(" AND COALESCE(c.c_cashline_id,p.c_payment_id)>0 ");
		return DB.getSQLValueBD(trxName, sql.toString(), //
			MCashLine.CASHTYPE_Invoice, MPayment.TYPE_InvoicePayment, getC_Invoice_ID());
	}

	/**
	 * Obtiene el total de anticipos relacionados a la factura/nota
	 * 
	 * @param trxName
	 * @return
	 */
	public BigDecimal getTotalAdvancePayment(final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT SUM( COALESCE( l.Amount,0) ) as Amount ");
		sql.append(" FROM c_allocationline l ");
		sql.append(" INNER JOIN c_payment p ON l.c_payment_id=p.c_payment_id AND p.type=? ");
		sql.append(" WHERE l.isactive='Y' AND l.c_invoice_id=? ");
		sql.append(" AND p.exme_ctapac_id IS NOT NULL ");
		BigDecimal amt = DB.getSQLValueBD(trxName, sql.toString(), MPayment.TYPE_AdvancePayment, getC_Invoice_ID());
		return amt==null?Env.ZERO:amt;
	}

	/**
	 * Obtiene el total de las devoluciones por notas de credito
	 * 
	 * @param trxName
	 * @return
	 */
	public BigDecimal getTotalPaymentRefund(String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT COALESCE( SUM (p.Amount),0 ) as Amount ");
		sql.append(" FROM C_Payment p ");
		sql.append(" INNER JOIN C_PaySelectionCheck c ON (c.C_Payment_ID=p.C_Payment_ID) ");
		sql.append(" INNER JOIN C_Invoice i ON (i.C_Invoice_ID=p.Ref_Invoice_ID AND i.TrxType=?) ");
		sql.append(" INNER JOIN C_DocType d ON (d.C_DocType_ID=i.C_DocType_ID AND d.DocBaseType=?) ");
		sql.append(" WHERE p.CheckNo IS NOT NULL AND p.TenderType=?  ");
		sql.append(" AND i.Ref_Invoice_ID=?  AND i.Rebate='Y' ");

		return DB.getSQLValueBD(trxName, sql.toString(), //
			TRXTYPE_PartialCreditNoteCustomer, X_C_DocType.DOCBASETYPE_ARCreditMemo,//
			X_C_Payment.TENDERTYPE_Check, getC_Invoice_ID());
	}

	public static String getNumCtaPago(MInvoice invoice){	// Se elimina parametro para cuando es para impresión Card 1090
		if(invoice != null /*&& invoice.getC_Invoice_ID() > 0*/){
			List<MFormaPago> lstPagos = MInvoice.getFormasDePago(invoice.getCtx(), invoice.getC_Invoice_ID(), null);
			if(lstPagos.isEmpty() || lstPagos.size() > 1){//#06153 ealvarez si es una factura se usa el mtdo getRef_Invoice_Sales_ID()
				lstPagos = MInvoice.getFormasDePago(invoice.getCtx(), invoice.getRef_Invoice_Sales_ID(), null);
			}
			
			//
			if(lstPagos.isEmpty() || lstPagos.size() > 1){
				return Utilerias.getMsg(invoice.getCtx(), "msg.noIdentidicado");
				
			} else {
				
				if(MFormaPago.PAYMENTRULE_Cash.equalsIgnoreCase(lstPagos.get(0).getPaymentRule())){
					// Efectivo
					return Utilerias.getMsg(invoice.getCtx(), "msg.noIdentidicado");
					
				} else if(MFormaPago.PAYMENTRULE_CreditCard.equalsIgnoreCase(lstPagos.get(0).getPaymentRule())) {
					// Con tarjeta bancaria
					List<MCashLine> lstlines = MInvoice.getCashLines(invoice.getCtx(), invoice.getC_Invoice_ID(), null);
					if(lstlines.isEmpty() || lstlines.size() > 1){//#06194 ealvarez si es una factura se usa el mtdo getRef_Invoice_Sales_ID()
						lstlines = MInvoice.getCashLines(invoice.getCtx(), invoice.getRef_Invoice_Sales_ID(), null);
					}
					
					if(lstlines != null && !lstlines.isEmpty()
							&& !StringUtils.isEmpty(lstlines.get(0).getCreditCardNumber())
							&& lstlines.get(0).getCreditCardNumber().length() >= 4){
						return lstlines.get(0).getCreditCardNumber().substring((lstlines.get(0).getCreditCardNumber().length()-4), 
								lstlines.get(0).getCreditCardNumber().length());
					} else {
						return Utilerias.getMsg(invoice.getCtx(), "msg.noIdentidicado");
					}
					
				} else if(MFormaPago.PAYMENTRULE_Check.equalsIgnoreCase(lstPagos.get(0).getPaymentRule())){
					// Con cheque
					List<MCashLine> lstlines = MInvoice.getCashLines(invoice.getCtx(), invoice.getC_Invoice_ID(), null);
					if(lstlines.isEmpty() || lstlines.size() > 1){//#06194 ealvarez si es una factura se usa el mtdo getRef_Invoice_Sales_ID()
						lstlines = MInvoice.getCashLines(invoice.getCtx(), invoice.getRef_Invoice_Sales_ID(), null);
					}
					
					final StringBuilder accountBank = new StringBuilder();
					if(lstlines != null && !lstlines.isEmpty()
							&& !StringUtils.isEmpty(lstlines.get(0).getAccountNo())
							&& lstlines.get(0).getAccountNo().length() >= 4){

						accountBank.append(lstlines.get(0).getAccountNo().substring((lstlines.get(0).getAccountNo().length()-4),
								 lstlines.get(0).getAccountNo().length()));
						 if (!StringUtils.isEmpty(lstlines.get(0).getA_Name())){
							 accountBank.append(" ").append(lstlines.get(0).getA_Name());
						 }

						return accountBank.toString();

					} else {
						return Utilerias.getMsg(invoice.getCtx(), "msg.noIdentidicado");
					}
					
				}else if(MFormaPago.PAYMENTRULE_OnCredit.equalsIgnoreCase(lstPagos.get(0).getPaymentRule())){
					// A crédito
					final StringBuilder accountBank = new StringBuilder();
					if(invoice.getCliente() != null){
						MBPBankAccount[] counts = invoice.getCliente().getBankAccounts(false);
						if(counts.length > 0 ){
							
							// Numero de cuenta
							if(counts[0].getAccountNo() != null && !counts[0].getAccountNo().isEmpty()){
								if(counts[0].getAccountNo().length() > 4){

									accountBank.append(counts[0].getAccountNo().substring(counts[0].getAccountNo().length()-4,
											counts[0].getAccountNo().length()));
									 if (counts[0].getBank() != null){
										accountBank.append(" ").append(counts[0].getBank().getName());
									 }
									return accountBank.toString();
								}else{
									accountBank.append(counts[0].getAccountNo());
									 if (counts[0].getBank() != null){
										accountBank.append(" ").append(counts[0].getBank().getName());
									 }
									return accountBank.toString();
								}
								
							// Numero de tarjeta
							} else if(counts[0].getCreditCardNumber() != null && !counts[0].getCreditCardNumber().isEmpty()){
								if(counts[0].getCreditCardNumber().length() > 4){
									accountBank.append(counts[0].getCreditCardNumber().substring(counts[0].getCreditCardNumber().length()-4,
											counts[0].getCreditCardNumber().length()));

									if (counts[0].getBank() != null){
										accountBank.append(" ").append(counts[0].getBank().getName());
									}
									return accountBank.toString();
								}else{
									accountBank.append(counts[0].getCreditCardNumber());
									if (counts[0].getBank() != null){
										accountBank.append(" ").append(counts[0].getBank().getName());
									}
									return accountBank.toString();
								}

							// Por defecto
							}else{
								accountBank.append(Utilerias.getMsg(invoice.getCtx(), "msg.noIdentidicado"));
								if (counts[0].getBank() != null){
									accountBank.append(" ").append(counts[0].getBank().getName());
								}
								return accountBank.toString();
							}

						}else{
							return Utilerias.getMsg(invoice.getCtx(), "msg.noIdentidicado");
						}

					}
				}else{
					return Utilerias.getMsg(invoice.getCtx(), "msg.noIdentidicado");
				}
			}
		}
		return Utilerias.getMsg(invoice.getCtx(), "msg.noIdentidicado");
	}


//	private MInvoice originalInvoice = null;
//
//	public MInvoice getOriginalInvoice(){
//		if(getRef_Invoice_Sales_ID() > 0 && (originalInvoice == null || originalInvoice.getC_Invoice_ID() <= 0)){
//			originalInvoice = new MInvoice(getCtx(), getRef_Invoice_Sales_ID(), null);
//		}
//		return originalInvoice;
//	}

	/**
	 * Notas de credito de la factura
	 * @return
	 */
	public List<MInvoice> getRefInvoice() {
		return new Query(getCtx(),
				MInvoice.Table_Name, " Ref_invoice_id = ? AND TRXTYPE = '"+X_C_Invoice.TRXTYPE_CreditNotePromptPayment +"'", get_TrxName())//
		.setParameters(getC_Invoice_ID())
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.list();
	}

	public MInvoiceTax[] getLinesTaxes() {
		return mTaxes;
	}

//	/**
//	 * getUnbilledReferralNote
//	 *
//	 * @param trxName
//	 * @return
//	 */
//	public static List<MInvoice> getUnbilledReferralNote(final String trxName) {
//		// Fecha actual
//		List<MInvoice> invoices= new ArrayList<MInvoice>();
//		final StringBuilder sql = new StringBuilder(
//				Constantes.INIT_CAPACITY_ARRAY)
//			.append(" select * from c_invoice  ")
//			.append(" where (ref_invoice_sales_id is null or ref_invoice_sales_id <= 0) ")
//			.append(" and DocStatus = 'DR' ")
//			.append(" and c_invoice_id not in (select ref_invoice_sales_id from c_invoice ")
//			.append(" where ref_invoice_sales_id > 0 ")
//			.append(" and (DocStatus = 'IP' or DocStatus = 'CO')) ")
//			.append(" and isactive = 'Y' ")
//			.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ","C_Invoice"));
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null; //
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				invoices.add(new MInvoice(Env.getCtx(), rs, trxName));
//			}
//
//		} catch (Exception e) {
//			sLog.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return invoices;
//	}

	/**
	 * Estado de la Factura:
	 * A. Remision con Factura
	 * B. Factura sin remision
	 * @return
	 */
	public String getEstatusFactura(){
		final StringBuilder lblRemision = new StringBuilder();
		final StringBuilder lblFactura = new StringBuilder();
		// Si esta facturado
		if (getC_Invoice_ID()>0 && getDocumentNo() != null) {

			if (X_C_Invoice.DOCACTION_Invalidate.equals(getDocStatus())) {
				lblRemision.append(Utilerias.getLabel("error.tableroCamas.invalidStatus")).append(" ");
				lblRemision.append(getDocumentNo());

			} else {
				// Es nota de remision
				if (X_C_Invoice.DOCACTION_WaitComplete.equals(getDocAction())) {
					lblRemision.append(Utilerias.getLabel("msj.notaRemision")).append(" : ")
					.append(getDocumentNo());

					// Es factura
					final MInvoice mInvoice = existInvoiceTarget(get_TrxName());
					if(mInvoice!=null){
						if(mInvoice.isMultiple()){
							lblFactura.append(" ").append(Utilerias.getLabel("msj.factura")).append(" ")
							.append(Utilerias.getLabel("global")).append(" : ")
							.append(mInvoice.getDocumentNo());
						} else {
							lblFactura.append(" ").append(Utilerias.getLabel("msj.factura")).append(" : ")
							.append(mInvoice.getDocumentNo());
						}
						docFactura = lblFactura.toString();
					}

				} else {
					// Factura y Remision es el mismo numero
					lblRemision.append(Utilerias.getLabel("reportes.inp.factura")).append(" : ");
					lblRemision.append(getDocumentNo());
				}
			}
		}
		return lblRemision.toString();
	}

	private String docFactura = "";
	public String getDocFactura(){
		return docFactura;
	}

	/**
	 * Facturas de tipo ARI, subtipo SR, estado CO
	 *
	 * @param ctx
	 *            Contexto de la app
	 * @param dInit
	 *            Fecha inicial
	 * @param dEnd
	 *            Fecha final
	 * @param trxName
	 *            Nombre de transacción
	 * @return Listado de facturas
	 */
	public static List<MInvoice> getCompletedInvoices(Properties ctx, Date dInit, Date dEnd, String trxName) {
		List<MInvoice> list = new ArrayList<MInvoice>();

		if (dInit.after(dEnd)) {
			Date tmp = dInit;
			dInit = dEnd;
			dEnd = tmp;
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  c_invoice i ");
		sql.append("  INNER JOIN c_doctype dt ");
		sql.append("  ON i.c_doctype_id = dt.c_doctype_id ");
		sql.append("WHERE ");
		sql.append("  dt.DOCBASETYPE = ? AND ");
		sql.append("  dt.DOCSUBTYPESO = ? AND ");
		sql.append("  i.docstatus = ? AND ");
		sql.append("  i.DateInvoiced BETWEEN ");
		sql.append("  ? AND ");
		sql.append("  ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "i"));
		sql.append(" ORDER BY ");
		sql.append("  i.DateInvoiced ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, MDocType.DOCBASETYPE_ARInvoice);
			pstmt.setString(2, MDocType.DOCSUBTYPESO_SalesReceipt);
			pstmt.setString(3, DOCSTATUS_Completed);
			pstmt.setTimestamp(4, TimeUtil.getInitialRangeT(ctx, dInit));
			pstmt.setTimestamp(5, TimeUtil.getFinalRangeT(ctx, dEnd));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MInvoice(ctx, rs, null));
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Desglose de un cobro por base gravable
	 * @param cobro Monto del cobro a desglosar
	 * @return BigDecimal con Base16, ImpBase16, BaseCero, BaseExcento
	 */
	public BigDecimal[] getDesgloceImp(BigDecimal cobro){

		if(cobro.compareTo(Env.ZERO)<=0){
			return new BigDecimal[]{Env.ZERO
					,Env.ZERO
					,Env.ZERO
					,Env.ZERO};
		}
		// Tasas de impuesto
		final MInvoiceTax[] taxs = getTaxes(true);
		//
		BigDecimal amt0 = Env.ZERO;
		BigDecimal amtE = Env.ZERO;
		BigDecimal amt1611 = Env.ZERO;
		BigDecimal imp1611 = Env.ZERO;

		for (int i = 0; i < taxs.length; i++) {
			if(taxs[i].getTax().getRate().compareTo(Env.ZERO) >0){
				amt1611 = amt1611.add(taxs[i].getTaxBaseAmt());
				imp1611 = imp1611.add(taxs[i].getTaxAmt());

			} else {
				// Sin monto de impuesto
				if(taxs[i].getTax().isTaxExempt()){
					// Excento
					amtE = amtE.add(taxs[i].getTaxBaseAmt());
				} else {
					// Tasa cero
					amt0 = amt0.add(taxs[i].getTaxBaseAmt());
				}
			}
		}

		// Total gravado
		BigDecimal gravda = amt1611.add(imp1611);//50
		// Proporcion
		BigDecimal factor = gravda.multiply(cobro).divide(getGrandTotal(), 6, BigDecimal.ROUND_HALF_UP);//50*100/200
		// Monto base proporcional (gravado)
		BigDecimal poramt = Env.ZERO;
		if(amt1611.compareTo(Env.ZERO)>0 && factor.compareTo(Env.ZERO)>0 && gravda.compareTo(Env.ZERO)>0){
				poramt = amt1611.multiply(factor).divide(gravda, 6, BigDecimal.ROUND_HALF_UP);//43.11*25/50
		}
		// Monto imp proporcional (gravado)
		BigDecimal poriva = Env.ZERO;
		if(imp1611.compareTo(Env.ZERO)>0 && factor.compareTo(Env.ZERO)>0 && gravda.compareTo(Env.ZERO)>0){
			poriva =imp1611.multiply(factor).divide(gravda, 6, BigDecimal.ROUND_HALF_UP);//6.89*25/50
		}
		// Monto base proporcional (no gravado)
		BigDecimal poramt0 = Env.ZERO;
		if(amt0.compareTo(Env.ZERO)>0 && cobro.compareTo(Env.ZERO)>0 && getGrandTotal().compareTo(Env.ZERO)>0){
			poramt0 = amt0.multiply(cobro).divide(getGrandTotal(), 6, BigDecimal.ROUND_HALF_UP);//150*100/200
		}
		BigDecimal poramtE = Env.ZERO;
		if(amtE.compareTo(Env.ZERO)>0 && cobro.compareTo(Env.ZERO)>0 && getGrandTotal().compareTo(Env.ZERO)>0){
			poramtE = amtE.multiply(cobro).divide(getGrandTotal(), 6, BigDecimal.ROUND_HALF_UP);//150*100/200
		}

		return new BigDecimal[]{poramt.setScale(2,  BigDecimal.ROUND_HALF_UP)
				,poriva.setScale(2,  BigDecimal.ROUND_HALF_UP)
				,poramt0.setScale(2,  BigDecimal.ROUND_HALF_UP)
				,poramtE.setScale(2,  BigDecimal.ROUND_HALF_UP)};
	}

	/**
	 * Reporte Concentrado de Facturas (CSV)
	 *
	 * @param ctx
	 *            Contexto
	 * @param typeInvoiceIds
	 * @param date
	 *            Fecha Inicial
	 * @param date2
	 *            Fecha Final
	 * @param ctaPacId
	 *            Cuenta paciente, puede ser vacia (-1 o 0)
	 * @param posted
	 *            Estado de posteo
	 * @param typeNoteId
	 *            Id de nota de credito
	 * @param costIndex
	 *            Tipo de costo
	 * @param trxName
	 *            Nombre de trx
	 * @return Archivo generado
	 */
	public static File getReporteConcentradoCSV(Properties ctx, List<Integer> typeInvoiceIds, List<Integer> typeDocsIds, Date date, Date date2, int ctaPacId, String posted, int typeNoteId, int costIndex, String trxName) {
		File file = null;

		String str = StringUtils.join(typeInvoiceIds, ",");
		String docs = StringUtils.join(typeDocsIds, ",");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  i.c_invoice_id, ");
		sql.append("  i.ref_invoice_id, ");
		sql.append("  i.c_doctype_id, ");
		sql.append("  i.exme_paciente_id ");
		sql.append("  AS PACIENTE_ID, ");
		sql.append("  cp.DocumentNo ctaPacNo, ");
		sql.append("  cbp.value ");
		sql.append("  AS CVECTE, ");
		sql.append("  i.Description ");
		sql.append("  AS CLIENTE, ");
		sql.append("  i.docstatus ");
		sql.append("  AS Status, ");
		sql.append("  cp.dateordered ");
		sql.append("  AS INGRESO, ");
		sql.append("  cp.fechaalta ");
		sql.append("  AS ALTA,((extract(epoch ");
		sql.append("FROM ");
		sql.append("  (cp.fechaalta - cp.dateordered))) /60) ::integer ");
		sql.append("  AS estadia, ");
		sql.append("  i.Nombre_Paciente paciente, ");
		sql.append("  i.Nombre_Medico ");
		sql.append("  AS medico, ");
		sql.append("  CASE WHEN pd.M_Product_ID IS NOT ");
		sql.append("  NULL THEN pd.name ELSE ch.name END Producto, pd.value as prodvalue, ");
		sql.append("  i.documentno ");
		sql.append("  AS FACTURANO, ");
		sql.append("  i.DateInvoiced dateFac, ");
		sql.append("  il.PriceList * il.qtyinvoiced linenetamt, ");
		sql.append("  il.taxamt,(il.PriceList * il.qtyinvoiced) + il.taxamt total, ");
		sql.append("  i.GrandTotal GRANDTOTAL, ");
		sql.append("  serv.name ");
		sql.append("  AS estacion, ");
		sql.append("  pd.PRODUCTCLASS, ");
		sql.append("  acj.name ");
		sql.append("  AS AREA, ");
		sql.append("  crn.documentno ");
		sql.append("  AS factrel, ");
		sql.append("  i.posted ");
		sql.append("  AS posted, ");
		sql.append("  uom.name ");
		sql.append("  AS uomName, ");
		sql.append("  il.qtyinvoiced ");
		sql.append("  AS qtyInvoiced, ");
		sql.append("  CASE WHEN i.c_doctype_id IN (").append(str).append(") THEN il.taxamt ELSE il.taxamt * -1 END TAXFAC, ");
		sql.append("  CASE WHEN i.c_doctype_id IN (").append(str).append(") THEN priceactual * il.qtyinvoiced ELSE(priceactual * il.qtyinvoiced) * -1 END LINEFAC, ");
		sql.append("  CASE WHEN i.c_doctype_id IN (").append(str).append(") THEN(priceactual * il.qtyinvoiced) + il.taxamt ELSE((priceactual * il.qtyinvoiced) + il.taxamt) * -1 END TOTALFAC, ");
		sql.append("  i.processed ");
		sql.append("  AS PROCESO, ");
		sql.append("  i.docStatus ");
		sql.append("  AS status, ");
		sql.append("  case when ");
		sql.append("  (il.c_uom_id = pd.c_uom_id ) then  ");
		sql.append("  prodPo.pricelastpo  ");
		sql.append("  else ");
		sql.append("  	case when (COALESCE(con.c_uom_id,con2.c_uom_id) = il.c_uom_id ) THEN ");
		sql.append("  	Round((prodPo.pricelastpo)*COALESCE(con.MultiplyRate,con2.MultiplyRate),2) ");
		sql.append("  	else null end ");
		sql.append("  end  ");
		sql.append("  AS pricelastpo, ");
		sql.append("  CASE ");
		sql.append("  WHEN elemtClient.currentcostprice IS NULL OR elemtClient.currentcostprice = 0 ");
		sql.append("    THEN null ");
		sql.append("  ELSE ");
		sql.append("    case when (COALESCE(con.c_uom_id,con2.c_uom_id) = il.c_uom_id ) THEN ");
		sql.append("  	Round((elemtClient.currentcostprice)*COALESCE(con.MultiplyRate,con2.MultiplyRate),2) ");
		sql.append("	else elemtClient.currentcostprice end ");
		sql.append("  end ");
		sql.append("  AS CURRENTCOSTPRICE, cat.name as categoria ");
		sql.append("FROM ");
		sql.append("  C_Invoice i ");
		sql.append("  INNER JOIN c_bpartner cbp ");
		sql.append("  ON (i.c_bpartner_id = cbp.c_bpartner_id AND ");
		sql.append("  cbp.iscustomer ='Y' ) ");
		sql.append("  INNER JOIN c_invoiceline il ");
		sql.append("  ON i.c_invoice_id = il.c_invoice_id left ");
		sql.append("  JOIN c_cashbook cbk ");
		sql.append("  ON I.c_cashbook_id = cbk.c_cashbook_id left ");
		sql.append("  JOIN exme_areacaja acj ");
		sql.append("  ON cbk.exme_areacaja_id = acj.exme_areacaja_id left ");
		sql.append("  JOIN M_Product pd ");
		sql.append("  ON (Pd.M_Product_Id = Il.M_Product_Id) left ");
		sql.append("  JOIN M_Product_Category cat ");
		sql.append("  ON (Pd.m_product_category_id = cat.m_product_category_id) LEFT ");
		sql.append("  JOIN C_UOM_CONVERSION con ");
		sql.append("  ON (con.C_Uom_ID=pd.C_Uom_ID AND ");
		sql.append("  con.C_Uom_To_ID =pd.C_UomVolume_ID) LEFT ");
		sql.append("  JOIN C_UOM_CONVERSION con2 ");
		sql.append("  ON (con2.C_Uom_ID=pd.C_UomVolume_ID AND ");
		sql.append("  con2.C_Uom_To_ID =pd.C_Uom_ID) LEFT ");
		sql.append("  JOIN EXME_CtaPac cp ");
		sql.append("  ON (I.Exme_ctapac_Id = cp.Exme_ctapac_Id) LEFT ");
		sql.append("  JOIN EXME_Paciente p ");
		sql.append("  ON (P.Exme_Paciente_Id = I.Exme_Paciente_Id) left ");
		sql.append("  JOIN c_charge ch ");
		sql.append("  ON (ch.c_charge_id = il.c_charge_id) left ");
		sql.append("  JOIN exme_estserv serv ");
		sql.append("  ON (cp.exme_estserving_id = serv.exme_estserv_id) left ");
		sql.append("  JOIN c_invoice crn ");
		sql.append("  ON i.ref_invoice_id = crn.c_invoice_id left ");
		sql.append("  JOIN c_uom uom ");
		sql.append("  ON il.c_uom_id = uom.c_uom_id left ");
		sql.append("  JOIN ");
		sql.append("  (SELECT ");
		sql.append("      po.m_product_id, ");
		sql.append("      po.pricelastpo ");
		sql.append("    FROM ");
		sql.append("      m_product_po po ");
		sql.append("      INNER JOIN ");
		sql.append("      (SELECT ");
		sql.append("          m_product_id, ");
		sql.append("          MAX(created) ");
		sql.append("          AS maxdate ");
		sql.append("        FROM ");
		sql.append("          m_product_po ");
		sql.append("        WHERE ");
		sql.append("          isactive = 'Y' AND ");
		sql.append("          AD_Client_ID = ? ");
		sql.append("        GROUP BY ");
		sql.append("          m_product_id ");
		sql.append("      ) lastprice ");
		sql.append("      ON Po.m_product_id = lastprice.m_product_id AND ");
		sql.append("      po.created = lastprice.maxdate ");
		sql.append("  ) prodPo ");
		sql.append("  ON /* prodPo.c_bpartner_id = cbp.c_bpartner_id and*/prodPo.m_product_id = pd.m_product_id LEFT ");
		sql.append("  JOIN ");
		sql.append("  (SELECT ");
		sql.append("      cos.currentcostprice, ");
		sql.append("      cos.M_product_ID ");
		sql.append("    FROM ");
		sql.append("      C_AcctSchema sh ");
		sql.append("      INNER JOIN M_COSTELEMENT ment ");
		sql.append("      ON (sh.costingmethod = ment.costingmethod AND ");
		sql.append("      sh.AD_Client_ID = ?) ");
		sql.append("      INNER JOIN M_Cost cos ");
		sql.append("      ON (ment.M_COSTELEMENT_ID = cos.M_COSTELEMENT_ID AND ");
		sql.append("      sh.C_AcctSchema_ID =cos.C_AcctSchema_ID AND cos.AD_Client_ID = ?) ");
		sql.append("  ) ");
		sql.append("  AS elemtClient ");
		sql.append("  ON elemtClient.M_product_ID = pd.M_product_ID ");
		sql.append("WHERE ");
		sql.append("  I.Isactive = 'Y' AND ");
		sql.append("  i.DateInvoiced BETWEEN ");
		sql.append("  ? AND ");
		sql.append("  ? AND ");
		sql.append("  i.AD_Client_ID = ? AND ");
		sql.append("  i.AD_Org_ID = ? ");
//		sql.append("  i.docStatus IN ('CO' ,'IP' ,'CL' ,'VO' ) AND ");
		sql.append("  AND (i.c_invoice_id in (select c_invoice_id from c_invoice where i.ismultiple='Y') or 1=1) ");
		sql.append("  AND i.issotrx = 'Y' ");
		sql.append(" AND I.C_Doctype_ID IN (").append(docs).append(")");

		if (ctaPacId > 0) {
			sql.append(" AND cp.exme_ctapac_id = ? ");
		}

		if (StringUtils.isNotBlank(posted) && !StringUtils.EMPTY.equals(posted)) {
			sql.append(" AND I.Posted = ? ");
		}

		sql.append(" ORDER BY ");
		sql.append("  i.created desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			file = File.createTempFile("cons", ".csv");

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, Env.getAD_Client_ID(ctx));
			pstmt.setTimestamp(4, TimeUtil.getInitialRangeT(ctx, date));
			pstmt.setTimestamp(5, TimeUtil.getFinalRangeT(ctx, date2));
			pstmt.setInt(6, Env.getAD_Client_ID(ctx));
			pstmt.setInt(7, Env.getAD_Org_ID(ctx));

			if (ctaPacId > 0) {
				pstmt.setInt(8, ctaPacId);

				if (StringUtils.isNotBlank(posted) && !StringUtils.EMPTY.equals(posted)) {
					pstmt.setString(9, posted);
				}
			} else {
				if (StringUtils.isNotBlank(posted) && !StringUtils.EMPTY.equals(posted)) {
					pstmt.setString(8, posted);
				}
			}

			rs = pstmt.executeQuery();

			CSVWriter writer = new CSVWriter(new FileWriter(file));

			List<String[]> lst = new ArrayList<String[]>();

			String[] titles = new String[26];

			titles[0] = Utilerias.getMsg(ctx, "msj.ctaPac");
			titles[1] = Utilerias.getMsg(ctx, "msj.documentNo");
			titles[2] = Utilerias.getMsg(ctx, "msg.bill.related");
			titles[3] = Utilerias.getMsg(ctx, "msj.clave");
			titles[4] = Utilerias.getMsg(ctx, "msj.cliente");
			titles[5] = Utilerias.getMsg(ctx, "msj.fechaIngreso");
			titles[6] = Utilerias.getMsg(ctx, "msj.fechaAlta");
			titles[7] = Utilerias.getMsg(ctx, "msg.duracion");
			titles[8] = Utilerias.getMsg(ctx, "msj.paciente");
			titles[9] = Utilerias.getMsg(ctx, "msj.medico");
			titles[10] = Utilerias.getMsg(ctx, "msj.productValue");
			titles[11] = Utilerias.getMsg(ctx, "msj.producto");
			titles[12] = Utilerias.getMsg(ctx, "egresos.rm.cantidad.facturada");
			titles[13] = Utilerias.getMsg(ctx, "msj.udm");
			titles[14] = Utilerias.getMsg(ctx, "reportes.categoriaProducto");
			titles[15] = Utilerias.getMsg(ctx, "msj.precioCompra");
			titles[16] = Utilerias.getMsg(ctx, "pedidoAuto.msj.tipo");
			titles[17] = Utilerias.getMsg(ctx, "caja.fechaFactura");
			titles[18] = Utilerias.getMsg(ctx, "msj.subtotal");
			titles[19] = "IVA";
			titles[20] = Utilerias.getMsg(ctx, "msj.total");
			titles[21] = Utilerias.getMsg(ctx, "msj.admission");
			titles[22] = Utilerias.getMsg(ctx, "msj.estatus");
			titles[23] = Utilerias.getMsg(ctx, "label.cp.documento");
			titles[24] = Utilerias.getMsg(ctx, "egresos.oc.boton.post");
			titles[25] = Utilerias.getMsg(ctx, "msj.procesado");

			lst.add(titles);

			BigDecimal value = BigDecimal.ZERO;
			BigDecimal value2 = BigDecimal.ZERO;
			BigDecimal value3 = BigDecimal.ZERO;

			while (rs.next()) {
				String[] arr = new String[26];

				arr[0] = rs.getString("CTAPACNO");
				arr[1] = rs.getString("FACTURANO");
				arr[2] = rs.getString("factrel");
				arr[3] = rs.getString("CVECTE");
				arr[4] = rs.getString("CLIENTE");
				arr[5] = rs.getString("INGRESO");
				arr[6] = rs.getString("ALTA");
				arr[7] = rs.getString("ESTADIA");
				arr[8] = rs.getString("PACIENTE");
				arr[9] = rs.getString("MEDICO");
				arr[10] = rs.getString("PRODVALUE");
				arr[11] = rs.getString("PRODUCTO");
				arr[12] = rs.getString("qtyInvoiced");
				arr[13] = rs.getString("uomName");
				arr[14] = rs.getString("categoria");

				double currenCost = rs.getDouble("CURRENTCOSTPRICE");
				double lastPrice = rs.getDouble("PRICELASTPO");

				if (costIndex == 3) {
					if (currenCost > 0) {
						arr[15] = Double.toString(currenCost);
					} else {
						arr[15] = Double.toString(lastPrice);
					}
				} else if (costIndex == 1) {
					arr[15] = Double.toString(lastPrice);
				} else {
					arr[15] = Double.toString(currenCost);
				}

				String productClass = rs.getString("PRODUCTCLASS");
				arr[16] = MRefList.getListName(ctx, MProduct.PRODUCTCLASS_AD_Reference_ID, productClass);

				arr[17] = Constantes.getSDFFechaCortaDMA(ctx).format(DB.convert(ctx, rs.getTimestamp("DATEFAC")));

				String status = rs.getString("status");

				if (DOCSTATUS_Voided.equals(status)) {
					arr[18] = Double.toString(0);
				} else {
					BigDecimal tmp = rs.getBigDecimal("LINEFAC");
					value = value.add(tmp);
					arr[18] = tmp.toString();
				}

				if (DOCSTATUS_Voided.equals(status)) {
					arr[19] = Double.toString(0);
				} else {
					BigDecimal tmp = rs.getBigDecimal("TAXFAC");
					value2 = value2.add(tmp);
					arr[19] = tmp.toString();
				}

				if (DOCSTATUS_Voided.equals(status)) {
					arr[20] = Double.toString(0);
				} else {
					BigDecimal tmp = rs.getBigDecimal("TOTALFAC");
					value3 = value3.add(tmp);
					arr[20] = tmp.toString();
				}

				String estacion = rs.getString("ESTACION");

				if (StringUtils.isNotBlank(estacion)) {
					arr[21] = estacion;
				} else {
					arr[21] = rs.getString("AREA");
				}

				arr[22] = MRefList.getListName(ctx, DOCSTATUS_AD_Reference_ID, rs.getString("status"));

				int docTypeId = rs.getInt("C_DOCTYPE_ID");

				if (docTypeId == typeNoteId) {
					arr[23] = Utilerias.getMsg(ctx, "msj.notaCredito");
				} else {
					arr[23] = Utilerias.getMsg(ctx, "caja.invoice");
				}

				arr[24] = MRefList.getListName(ctx, 234, rs.getString("posted"));
				arr[25] = "Y".equals(rs.getString("PROCESO")) ? Utilerias.getMsg(ctx, "imag.checked") : Utilerias.getMsg(ctx, "imag.notChecked");

				lst.add(arr);
			}

			String[] totals = new String[25];
			totals[18] = value.toString();
			totals[19] = value2.toString();
			totals[20] = value3.toString();

			lst.add(totals);

			writer.writeAll(lst);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return file;
	}
	/*
	private void allDoc() throws Exception{
		// crear nota de remision
		setValuesInv(0, 0, null, null, "");
		// Crear Factura
		createInvoiceTarget(null);
		// Crear una nota de credito por anulacion del timbrado
		createNotaCredFromInvoice(null, new Timestamp(System.currentTimeMillis()), 0, 0, false, null);
		// Crear nota de credito por cancelación
		notaCredito(getCtx(), null, 0, 0, "", 0, new Timestamp(System.currentTimeMillis()), null);
		// Crear una factura a partir de otra factura
		createPreFactura(getCtx(), null, 0, 0, false, "");
		// Crear nota de credito por pronto pago
		createNotaCredCxC(null, new Timestamp(System.currentTimeMillis()), Env.ZERO, null);
	}
	*/

	/** Crear una factura a partir un rango (fechas) de notas de remision */
	public static MInvoice getInvoiceTargetMultiple(final Properties ctx, final int cashId, final String trxName, List<BeanView> lstBeans){
		final MInvoice mInvoiceTarget = new MInvoice(ctx, 0, trxName);
		mInvoiceTarget.createInvoiceTargetMultiple(cashId, trxName, lstBeans);
		return mInvoiceTarget;
	}

	/** Crea una factura multiple */
	private void createInvoiceTargetMultiple(final int cashId, final String trxName, final List<BeanView> lstBeans){
		// Las facturas multiples tiene un socio en especifico
		final MBPartner bPartner = new MBPartner(getCtx(),
				MEXMEConfigPre.get(getCtx(), trxName).getC_BPartner_Sales_ID(), trxName);

		if (bPartner == null || bPartner.getC_BPartner_ID()<1) {
			throw new MedsysException(Utilerias.getLabel("error.noExisteSocio"));
		}

		setC_BPartner_ID(bPartner.getC_BPartner_ID());
		setValues(-1, trxName);

		// Asignar los valores a la factura
		setDescription(bPartner.getName());
		setPOReference(bPartner.getTaxID());

		// Caja que crea la factura
		setC_Cash_ID(cashId);

		// Direccion: La mas actual
		final List<MBPartnerLocation> bPlocation = bPartner.getForBPartner ();
		MLocation mLocation = null;
		if(bPlocation!=null && !bPlocation.isEmpty()){
			setC_BPartner_Location_ID(bPlocation.get(0).getC_BPartner_Location_ID());
			mLocation = bPlocation.get(0).getBPLocation();
			if(mLocation!=null){
				setAddress2(mLocation.getAddress2());
				setAddress3(mLocation.getAddress3());
				setNumExt(mLocation.getNumExt());
				setNumIn(mLocation.getNumIn());
				setCity(mLocation.getCity() );
				setPostal(mLocation.getPostal() );
				setEXME_TownCouncil_ID(mLocation.getEXME_TownCouncil_ID() );
				setC_Country_ID(mLocation.getC_Country_ID() );
				setC_Region_ID(mLocation.getC_Region_ID() );
			}
		}

		// Datos requeridos
		setTrxType(TRXTYPE_CustomerInvoice);
		setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice, MDocType.DOCSUBTYPESO_SalesReceipt);
		setC_DocType_ID(getC_DocTypeTarget_ID());

		// LAS FACTURAS MULTIPLES NO TIENEN REFERENCIA DE REMISION
		setIsMultiple(true);//setRef_Invoice_Sales_ID(getC_Invoice_ID());

		if(!save(trxName)){// 1 sola vez la transaccion
			throw new MedsysException(Utilerias.getLabel("factExtension.error.extNoSave"));
		}

		// Crear las lineas
		createLinesMultiple(trxName, lstBeans);

		// Terminar el documento
		if (completeIt().equals(DocAction.STATUS_Drafted)) {
			setScaleTotals();
			setPosted(false);
			setProcessed(false, trxName);
			setStatusInProgress();
			setIsPaid(true);//TODO: Notas de remision en estatus de pagada?
			setAfecta_Caja(true);//TODO: Notas de remision en estatus de pagada?
			if (!save()) {
				throw new MedsysException(
						Utilerias.getLabel("factExtension.error.createInvoiceExt", getDocumentNo()));
			}

		} else {
			throw new MedsysException(
					Utilerias.getLabel("factExtension.error.createInvoiceExt")+" "+
					Utilerias.getLabel("factExtension.error.createInvoiceExt", getDocumentNo()));
		}
	}

	/** Generacion de lineas de factura global
	 * @param trxName Nombre de transacción
	 * @param lstBeans listado de BeanView con Monto de impuesto, monto base, id de impuesto y descripción
	 */
	private void createLinesMultiple(final String trxName, final List<BeanView> lstBeans){
		for (final BeanView bean: lstBeans) {

			if(bean.getDcimal().compareTo(Env.ZERO)>0){
				final MInvoiceLine iLine = new MInvoiceLine(this);
				iLine.createLineCharge(bean);
				if (!iLine.save()) {
					throw new MedsysException(
							Utilerias.getLabel("factExtension.error.createInvoiceExt", getDocumentNo()));
				}
			}
		}// Fin for

		getLines(true, trxName);
	}

	/** Consulta para obtener solo facturas no canceladas o si fueron refacturadas regresa la factura nueva */
	public static final StringBuilder getCInvoiceCustomerV(final Properties ctx, final boolean subtable){
		final StringBuilder sql = new StringBuilder()
		.append(subtable?" ( ":"")


		.append(" SELECT si.* ")
		.append(" FROM c_invoice si ")
		.append(" INNER JOIN c_doctype sd ON sd.c_doctype_id = si.c_doctype_id \n")
		.append(" 	AND sd.isactive = 'Y' \n")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_DocType.Table_Name, "sd"))
		.append("\n 	AND sd.docbasetype = 'ARI' \n")
		.append(" 	AND sd.docsubtypeso = 'SR' \n")
		.append(" WHERE si.isactive = 'Y' \n")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_Invoice.Table_Name, "si"))
		.append("\n AND   si.docstatus IN ('CO', 'CL', 'IP') \n")
		.append("   AND   si.issotrx = 'Y' \n")
		.append("   AND   si.c_invoice_id NOT IN ( SELECT ssi.ref_invoice_id   \n")
		.append("                                FROM c_invoice ssi          \n")
		.append("                                INNER JOIN c_doctype sd ON sd.c_doctype_id = ssi.c_doctype_id \n")
		.append("                                	AND sd.isactive = 'Y'       \n")
		.append("                                	AND sd.docbasetype = 'ARC'  \n")
		.append("                                	AND sd.docsubtypeso IS NULL \n")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_DocType.Table_Name, "sd"))
		.append("\n                              WHERE ssi.isactive = 'Y'       \n")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_Invoice.Table_Name, "ssi"))
		.append("                                AND   ssi.docstatus IN ('CO', 'CL', 'IP') \n")
		.append("                                AND   ssi.TrxType = 'N'                  \n")
		.append("                                AND   ssi.issotrx = 'Y')       \n")


		.append(subtable?" ) AS c_invoice_customer_v ":"");
		return sql;
	}

	public BigDecimal getSaldo(boolean recalcular, boolean ispaid) {
		if(creditos.equals(BigDecimal.ZERO) || saldo.equals(BigDecimal.ZERO) || pagos.equals(BigDecimal.ZERO) || recalcular){
			pagos = calculaPago(null);
			creditos = getCredits(ispaid, null);
			saldo = this.getGrandTotal().subtract(pagos.add(creditos));
		}
		return saldo;
	}

	public BigDecimal getPagos(boolean recalcular, boolean ispaid) {
		if(creditos.equals(BigDecimal.ZERO) || saldo.equals(BigDecimal.ZERO) || pagos.equals(BigDecimal.ZERO) || recalcular){
			pagos = calculaPago(null);
			creditos = getCredits(ispaid, null);
			saldo = this.getGrandTotal().subtract(pagos.add(creditos));
		}
		return pagos;
	}
	
	public BigDecimal getCreditos(boolean recalcular, boolean ispaid) {
		if(creditos.equals(BigDecimal.ZERO) || saldo.equals(BigDecimal.ZERO) || pagos.equals(BigDecimal.ZERO) || recalcular){
			pagos = calculaPago(null);
			creditos = getCredits(ispaid, null);
			saldo = this.getGrandTotal().subtract(pagos.add(creditos));
		}
		return creditos;
	}

	/**
	 * Obtenemos la lista de todas las formas de pago relacionadas a un pago agrupadas
	 * si es que se repiten las formas de pago
	 *
	 * @param ID de la factura
	 * @param trxName
	 * @return Sumatoria de los pagos a la factura
	 */
	public static List<CreditNoteDet> getDetallePagos(final Properties ctx, final MInvoice cInvoice, String trxName) {
			List<CreditNoteDet> ret = new ArrayList<CreditNoteDet>();
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			sql.append(" select fecha, monto, formaID, NC_ID from   (   ");
			sql.append(" SELECT l.created as fecha, l.Amount as monto, c.exme_formapago_id as formaID, 0 as NC_ID ");
			sql.append(" FROM C_AllocationLine l  ");
			sql.append(" INNER JOIN c_cashline c ON l.c_cashline_id=c.c_cashline_id ");
			sql.append(" WHERE l.isactive='Y'  ");
			sql.append(" and l.c_invoice_id = ?  ");
			sql.append(" and c.cashtype<>'X'   ");
			sql.append(" union   ");
			sql.append(" select a.created as fecha, a.Amount as monto, c.exme_formapago_id as formaID, 0 as NC_ID ");
			sql.append(" from c_allocationline a ");
			sql.append(" inner join c_cashline c on c.c_payment_id = a.c_payment_id ");
			sql.append(" where a.isactive  = 'Y'  ");
			sql.append(" and a.c_invoice_id = ? ");
			sql.append(" and  a.c_payment_id is not null ");
			sql.append(" union   ");
			sql.append(" select ssi.created as fecha, ssi.Grandtotal as monto, 0 as formaID, ssi.c_invoice_id as NC_ID ");
			sql.append(" from c_invoice ssi   ");
			sql.append(" INNER JOIN c_doctype sd ON sd.c_doctype_id = ssi.c_doctype_id  AND sd.isactive = 'Y' AND sd.docbasetype = 'ARC' ");
			sql.append(" AND sd.docsubtypeso IS NULL    ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_DocType.Table_Name, "sd"));
			sql.append(" where ssi.isactive  = 'Y' and ssi.Ref_Invoice_ID = ? ");			
			sql.append(" and ssi.C_Invoice_ID not in (select ssid.Ref_Invoice_ID from c_invoice ssid ");    
			sql.append(" INNER JOIN c_doctype sdd ON sdd.c_doctype_id = ssid.c_doctype_id  AND sdd.isactive = 'Y' AND sdd.docbasetype = 'ARD' ");  
			sql.append(" AND sdd.docsubtypeso IS NULL      ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_DocType.Table_Name, "sdd"));
			sql.append(" where ssid.isactive  = 'Y'  ");
			sql.append(" and ssid.Ref_Invoice_ID = ssi.C_Invoice_ID ");
			sql.append(" ) ");			
			sql.append("and ssi.trxtype = 'P'  ) as pagos ");
			sql.append(" order by fecha   ");

			java.sql.PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, cInvoice.getC_Invoice_ID());
				pstmt.setInt(2, cInvoice.getC_Invoice_ID());
				pstmt.setInt(3, cInvoice.getC_Invoice_ID());

				rs = pstmt.executeQuery();
				CreditNoteDet det = null;
				int i = 1;
				BigDecimal pendiente = cInvoice.getGrandTotal();
				while (rs.next()) {
					det = new CreditNoteDet();
					det.setIndice(i);
					i++;
					det.setFecha(rs.getTimestamp(1));
					det.setMonto(rs.getBigDecimal(2));
					det.setPendiente(pendiente.subtract(det.getMonto()));
					pendiente = pendiente.subtract(det.getMonto());
					if(rs.getInt(3) > 0){
						det.setPago(new MFormaPago(ctx, rs.getInt(3), null));
					}
					det.setNcID(rs.getInt(4));
					ret.add(det);
				}
			} catch (final Exception e) {
				sLog.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}

			return ret;
		}

	private String ctapacDocumentNo = StringUtils.EMPTY;


	public String getCtapacDocumentNo() {
		if (StringUtils.isEmpty(ctapacDocumentNo) && getEXME_CtaPac_ID() > 0) {
			MEXMECtaPac ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), null);
			setCtapacDocumentNo(ctaPac.getDocumentNo());
		}
		return ctapacDocumentNo;
	}

	public void setCtapacDocumentNo(String ctapacDocumentNo) {
		this.ctapacDocumentNo = ctapacDocumentNo;
	}

public static List<CreditNoteDet> getNotasParciales(final Properties ctx, final String condicion, String trxName) {
		List<CreditNoteDet> lstNC = new ArrayList<CreditNoteDet>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT ");
		sql.append("ssi.documentno as nota, i.documentno as fact, ssi.dateinvoiced as fechafact, i.description as RS, ");
		sql.append("ssi.POReference as RFC, ssi.Nombre_Paciente as PAC,  ssi.Grandtotal AS monto, ssi.c_Invoice_id as notaID, nc.c_Invoice_ID as Ncargo ");
		sql.append("FROM c_invoice ssi ");
		sql.append("INNER JOIN c_doctype sd ON sd.c_doctype_id = ssi.c_doctype_id ");
		sql.append("INNER JOIN C_Invoice i on ssi.Ref_Invoice_ID = i.c_Invoice_ID ");
		sql.append("LEFT JOIN C_INVOICE nc on nc.Ref_Invoice_ID = ssi.c_Invoice_ID ");
		sql.append("AND sd.isactive = 'Y' AND sd.docbasetype = 'ARC' AND sd.docsubtypeso IS NULL ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_DocType.Table_Name, "sd"));
		sql.append("WHERE ssi.isactive = 'Y'  ");
		sql.append("AND ssi.trxtype = 'P' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_DocType.Table_Name, "ssi"));

		if (StringUtils.isNotEmpty(condicion)) {
			sql.append(condicion);
		}

		sql.append(" ORDER BY i.documentno ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			CreditNoteDet det = null;
			while (rs.next()) {
				det = new CreditNoteDet();
				det.setNotaNo(rs.getString("nota"));
				det.setFacturaNo(rs.getString("fact"));
				det.setFecha(rs.getTimestamp("fechafact"));
				det.setCliente(rs.getString("RS"));
				det.setRfc(rs.getString("RFC"));
				det.setPacName(rs.getString("PAC"));
				det.setMonto(rs.getBigDecimal("monto"));
				det.setNcID(rs.getInt("notaID"));
				if (rs.getInt("Ncargo") > 0) {
					det.setNotaCargoID(rs.getInt("Ncargo"));
				}
				lstNC.add(det);
			}
		} catch (final Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstNC;
	}

/*
	/**
	 * Calculo de impuestos con CCCmD y desc
	 * 
	 * @param lstCCCmDDesc
	 * @param totalVta
	 * @param totalVtaImp
	 * @return Suma de todas las lineas por la columna LineNetAmt
	 /
	private BigDecimal getAmountTax(final String trxName) {
		final BigDecimal totalTaxAmt;
		final boolean isInsurance   = getCliente().isFacturarAseg();
		
		if(isInsurance){
			totalTaxAmt = getInsuranceTaxs();
		} else {
			totalTaxAmt = getPatientTaxs();
		}
		return totalTaxAmt;
	}*
	
	/** Impuesto prorrateado del CCCmD negativo */
	private BigDecimal getInsuranceTaxs(){
		
		// Sin CCCmD
		BigDecimal bDTotalLines = Env.ZERO;
		BigDecimal bDTaxAmt = Env.ZERO;
		final List<MInvoiceTax>   lstTaxs = MEXMEInvoiceLine.getLinesGroupTax(getCtx(), getC_Invoice_ID(), get_TrxName());
		for (final MInvoiceTax bean: lstTaxs) {
			bDTotalLines = bDTotalLines.add(bean.getTaxBaseAmt());
			bDTaxAmt     = bDTaxAmt.add(bean.getTaxAmt());
			bean.save(get_TrxName());
		}
		BigDecimal totalTaxAmt = bDTaxAmt;
		// Son CCCmD
		final List<MInvoiceLine> lstCCCmD = MEXMEInvoiceLine.getLinesCCCmD(getCtx(), getC_Invoice_ID(), get_TrxName());
		
		// Desglose de impuesto
		for (final MInvoiceLine mInvLine : lstCCCmD) {
			
			if(mInvLine.getDetailTax()!=null && mInvLine.getDetailTax().length()>0){
				return null;
			}
			
			
			totalTaxAmt = totalTaxAmt.add(mInvLine.taxBreakdown(
					true
					, lstTaxs
					, bDTotalLines
					, bDTaxAmt
					, get_TrxName()));
		}
		
		// Dos decimales
		if(totalTaxAmt.scale() > getPrecision()){
			totalTaxAmt = totalTaxAmt.setScale(getPrecision(),BigDecimal.ROUND_HALF_UP);
		}
				
		return totalTaxAmt;
	}
	
	/** Impuesto prorrateado del CCCmD positivo */
	private BigDecimal getPatientTaxs(){
		BigDecimal totalTaxAmt = Env.ZERO;

		final List<MInvoiceTax>  lstTaxs  = MEXMEInvoiceLine.getLinesGroupTax(getCtx(), getC_Invoice_ID(), get_TrxName());
		final List<MInvoiceLine> lstCCCmD = MEXMEInvoiceLine.getLinesCCCmD(getCtx(), getC_Invoice_ID(), get_TrxName());

		// 
		for (int i = 0; i < lstTaxs.size(); i++) {
			if(lstTaxs.get(i).save(get_TrxName())){
				totalTaxAmt = totalTaxAmt.add(lstTaxs.get(i).getTaxAmt());
			} else {
				throw new MedsysException(Utilerias.getLabel("factExtension.error.createInvoiceExt"));
			}
		}

		// Desglose de impuesto
		boolean calcular = !lstCCCmD.isEmpty();
		for (final MInvoiceLine mInvLine : lstCCCmD) {
			
			if(mInvLine.getDetailTax()!=null && mInvLine.getDetailTax().length()>0){
				return null;
			}
			
			
			final List<MInvoiceTax> lstTxInsurance = MEXMEInvoice.getLinesInsuranceTaxes(getCtx(), mInvLine, get_TrxName());
			for (final MInvoiceTax mInvTxInsurance : lstTxInsurance) {
				totalTaxAmt = totalTaxAmt.add(taxBreakdownVsInsurance(mInvTxInsurance, mInvLine, lstTaxs));
				calcular = false;
			}
		}
		
		// Impuesto de CCCmD para facturación directa
		BigDecimal totalOther = Env.ZERO;
		if(basesGravAseg!=null && !basesGravAseg.isEmpty() && calcular){
			// Obtener el total de la factura de la aseguradora 
			Iterator<Entry<Integer, BigDecimal>> itEntriesTotal1 = basesGravAseg
					.entrySet().iterator();
			while (itEntriesTotal1.hasNext()) {
				Entry<Integer, BigDecimal> element = itEntriesTotal1
						.next();
				totalOther = totalOther.add(element.getValue());//964.80
			}
			
			///
			if(totalOther.compareTo(Env.ZERO)!=0){
				for (final MInvoiceLine mInvLine : lstCCCmD) {

					// mapa con los impuestos
					final Iterator<Entry<Integer, BigDecimal>> itEntriesTotal2 = basesGravAseg
							.entrySet().iterator();
					while (itEntriesTotal2.hasNext()) {
						final Entry<Integer, BigDecimal> element = itEntriesTotal2
								.next();


						MInvoiceTax mInvTax = MInvoiceTax.getInvoiceTax(getCtx(), element.getKey(), getC_Invoice_ID(), get_TrxName());
						if(mInvTax!=null && mInvTax.getC_Invoice_ID()>0 && mInvTax.getC_Tax_ID()>0){

							//104.09*872.00/1072.00 = 84.670224
							final BigDecimal base = (mInvLine.getLineNetAmt().multiply(element.getValue())).divide(totalOther,6,BigDecimal.ROUND_HALF_UP);
							mInvTax.setTaxBaseAmt(mInvTax.getTaxBaseAmt().add(base));
							
							if(mInvLine.getC_Tax_ID()==element.getKey()){
								mInvTax.setTaxAmt(mInvTax.getTaxAmt().add(mInvLine.getTaxAmt()));
							}

							if(mInvTax.save(get_TrxName())){
								totalTaxAmt = totalTaxAmt.add(mInvTax.getTaxAmt());

							} else {
								throw new MedsysException(Utilerias.getLabel("factExtension.error.createInvoiceExt"));
							}
							
							
						} else {
							mInvTax = new MInvoiceTax(getCtx(), 0,
									get_TrxName());
							mInvTax.setC_Tax_ID(element.getKey());
							mInvTax.setC_Invoice_ID(getC_Invoice_ID());

							//86.83*784.80/964.80 = 70.630373 -86.83*180.00/964.80 = 16.199627
							final BigDecimal base = (mInvLine.getLineNetAmt().multiply(element.getValue())).divide(totalOther,6,BigDecimal.ROUND_HALF_UP);
							mInvTax.setTaxBaseAmt(base);
							if(mInvLine.getC_Tax_ID()==element.getKey()){//16%
								mInvTax.setTaxAmt(mInvLine.getTaxAmt());
							}

							if(mInvTax.save(get_TrxName())){
								totalTaxAmt = totalTaxAmt.add(mInvTax.getTaxAmt());

							} else {
								throw new MedsysException(Utilerias.getLabel("factExtension.error.createInvoiceExt"));
							}
						}
					}
				}
			}
		}
		return totalTaxAmt;
	}
	
	/** Prorrate contra los impuestos de la contraparte */
	private BigDecimal taxBreakdownVsInsurance(final MInvoiceTax mInvTxInsurance, final MInvoiceLine mInvLine, final List<MInvoiceTax>  lstTaxs){
		BigDecimal totalTaxAmt = Env.ZERO;
		BeanView totalInsurance = getTotalInsuranceInvoice(mInvLine);

		final BigDecimal bDTotalLines = totalInsurance.getDcimal(); 
		final BigDecimal bDTaxAmt = totalInsurance.getDcimal2(); 

		totalTaxAmt = Env.ZERO;
		BigDecimal base = bDTotalLines==null || bDTotalLines.compareTo(Env.ZERO)==0 ? Env.ZERO// Prorrate contra los impuestos de la contraparte
				: (mInvTxInsurance.getTaxBaseAmt().multiply(mInvLine.getPriceActual())).divide(bDTotalLines, 6, BigDecimal.ROUND_HALF_UP);
		BigDecimal imps = bDTaxAmt==null || bDTaxAmt.compareTo(Env.ZERO)==0 ? Env.ZERO 
				: (mInvTxInsurance.getTaxAmt().multiply(mInvLine.getTaxAmt())).divide(bDTaxAmt, 6, BigDecimal.ROUND_HALF_UP);
		// Afactar la linea de impuesto de la factura particular
		MInvoiceTax mInvTax = null;
		for (int i = 0; i < lstTaxs.size(); i++) {
			if(lstTaxs.get(i).getC_Tax_ID()==mInvTxInsurance.getC_Tax_ID()){
				mInvTax = lstTaxs.get(i);
				break;
			}
		}

		// Si no existe esa tasa de impuesto se crea, si existe se afecta agregandole el prorrateo
		if(mInvTax==null){
			mInvTax = new MInvoiceTax(getCtx(), 0, get_TrxName());
			mInvTax.setTaxBaseAmt(base);
			mInvTax.setTaxAmt(imps);
			mInvTax.setC_Tax_ID(mInvTxInsurance.getC_Tax_ID());
			mInvTax.setC_Invoice_ID(getC_Invoice_ID());

		} else {
			mInvTax.setTaxBaseAmt(mInvTax.getTaxBaseAmt().add(base));
			mInvTax.setTaxAmt(mInvTax.getTaxAmt().add(imps));
		}
		boolean isNew =  mInvTax.is_new(); // #06222 -Si es nuevo se agrega a lstTaxs
		if(mInvTax.save(get_TrxName())){
			totalTaxAmt = totalTaxAmt.add(imps);
			if(isNew){
				lstTaxs.add(mInvTax);
			}

		} else {
			throw new MedsysException(Utilerias.getLabel("factExtension.error.createInvoiceExt"));
		}
		return totalTaxAmt;
	}
	
	private BeanView getTotalInsuranceInvoice(final MInvoiceLine mInvLine){
		// Impuesto y base gravable de la contraparte (factura o extension de la aseguradora (-)),
		// NOTA: Una linea de CCCmD  puede ser de varias facturas de aseguradora por eso esta dentro del for
		BigDecimal bDTotalLines = Env.ZERO;
		BigDecimal bDTaxAmt = Env.ZERO;
		final List<MInvoiceTax> lstTxInsurance = MEXMEInvoice.getLinesInsuranceTaxes(getCtx(), mInvLine, get_TrxName());
		for (final MInvoiceTax  bean: lstTxInsurance) {
			bDTotalLines = bDTotalLines.add(bean.getTaxBaseAmt());
			bDTaxAmt     = bDTaxAmt.add(bean.getTaxAmt());
		}
		return new BeanView (bDTotalLines, bDTaxAmt);
	}
	
	public boolean hasCreditNotePayment(final Properties ctx, final String trxName) {
		boolean ret = true;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * ");
		sql.append(" from c_payment ");
		sql.append(" where ref_invoice_id = ").append(getC_Invoice_ID());
		sql.append(" and docstatus = '").append(X_C_Payment.DOCSTATUS_Reversed).append("'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Payment.Table_Name));
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);			
			rs = pstmt.executeQuery();			
			ret = !rs.next();			
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ret;
	}
	
	/** cheque de la nota */
	public MPayment getChequeDeNota(final Properties ctx, final String trxName) {
		MPayment ret = null;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * ");
		sql.append(" from c_payment ");
		sql.append(" where ref_invoice_id = ").append(getC_Invoice_ID());
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Payment.Table_Name));
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);			
			rs = pstmt.executeQuery();			
			if(rs.next()){
				ret = new MPayment(ctx, rs.getInt("C_Payment_ID"), trxName);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ret;
	}

	public void setPaciente(final int pacienteId){
		final MEXMEPaciente paciente = new MEXMEPaciente(getCtx()
				, pacienteId
				, get_TrxName());
		// Paciente
		if (paciente != null && pacienteId>0 ) {
			setEXME_Paciente_ID(paciente.getEXME_Paciente_ID());
			setNombre_Paciente(paciente.getNombre_Pac());
			setInvoicePhone(paciente.getTelParticular());
		}
	}
	
	public void setMedico(final int medicoId){
		final MEXMEMedico medico = new MEXMEMedico(getCtx(), medicoId, get_TrxName());
		if (medico != null && medicoId >0) {
			setEXME_Medico_ID(medico.getEXME_Medico_ID());
			setNombre_Medico(medico.getNombre_Med());
		}
	}
	
	/**
	 * Metodo para Obtener el tipo de conversion relacionado a la moneda de la
	 * factura
	 * 
	 * @param c_Currency_ID
	 *            el ID de la moneda de Factura
	 **/
	public static String getConversionRate(final MInvoice invoice) {
//		Timestamp datePost = invoice.getDateInvoiced();
//		if(X_C_DocType.DOCBASETYPE_ARCreditMemo.equals(invoice.getC_DocType().getDocBaseType())){
//			final MInvoice invoiceref = new MInvoice(invoice.getCtx(), invoice.getRef_Invoice_ID(), invoice.get_TrxName());
//			if(invoiceref!=null && invoiceref.getDateInvoiced()!=null)
//				datePost = invoiceref.getDateInvoiced();
//		}
		return MCurrency.getConversionRate(invoice.getCtx(), invoice.getC_Currency_ID(), invoice.getDateForConversionRate());
	}
	
	/** Obtener la fecha para buscar la tasa de impuestos */
	public Timestamp getDateForConversionRate() {
		Timestamp datePost = getDateInvoiced();
		if(X_C_DocType.DOCBASETYPE_ARCreditMemo.equals(getC_DocType().getDocBaseType())){
			final MInvoice invoiceref = new MInvoice(getCtx(), getRef_Invoice_ID(), get_TrxName());
			if(invoiceref!=null && invoiceref.getDateInvoiced()!=null)
				datePost = invoiceref.getDateInvoiced();
		}
		return datePost;
	}
	
	/** Fecha de factura */
	public static Timestamp getDateInvoicedForId(int cInvoiceId) {
		Timestamp retVal =
				DB.getSQLValueTS(
						null,
						"SELECT DateInvoiced FROM C_Invoice WHERE C_Invoice_ID = ?",
						new Object[]{cInvoiceId}
				);

		return retVal;
	}
	
	/** Regresar inventario al almacén */
	public boolean returnInout(final ErrorList errorList, final MInvoice nota) {
		boolean success =  errorList.isEmpty();
		if(success){
			final List<MInOut> original = MEXMEInOut.getInOutOfInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
			//hay un embarque relacionado a la factura??
			if(original!=null && !original.isEmpty()) {
				for (int i = 0; i < original.size(); i++) {
					final String devCancel = original.get(i).entradaInventarioPorCancelacion(getCtx(), nota, get_TrxName());
					if(devCancel!=null && !devCancel.isEmpty()){
						errorList.add(Error.VALIDATION_ERROR, devCancel);//Utilerias.getAppMsg(getCtx(), devCancel));
					}	
				}
			}//
			success = errorList.isEmpty();
		}
		return success;
	}
	
	/** Abrir la extension */
	public boolean openExtension(final ErrorList errorList) throws Exception{
		boolean success =  errorList.isEmpty();
		if(success){
			if (getEXME_CtaPacExt_ID() > 0) {
				MEXMECtaPacExt.reaperturaExtension(getCtx(), getEXME_CtaPacExt_ID(), this, get_TrxName());
			}
		}
		return success;
	}
	
	/** Regresar pedidos de consigna La referencia es por la remisi&oacuten*/
	public boolean returnMovementConsig(final ErrorList errorList) throws Exception{
		boolean success =  errorList.isEmpty();
		if(success){
			// Consigna #1225 si tiene movimiento entre almacen de consigna virtual .-Lama
			MMovement.returnInvoiceProd(getCtx(), getC_Invoice_ID(), get_TrxName());
		}
		return success;
	}
	
	/** Regresar los pagos */
	public boolean returnPayments(final ErrorList errorList, final boolean isPaid, final List<MCashLine> cashLine, final int cCashID) {
		boolean success =  errorList.isEmpty();
		if(success){
			//System.out.println(" ¿Debe cancelar pagos? : " + cashLine.size());
			List<MAllocationHdr> asignaciones = MAllocationHdr.getAllocationHrdInv(getCtx(), getC_Invoice_ID(), false, null);
			//System.out.println(" Asignaciones : " + asignaciones.size());
			List<MCashLine> lineasDeCaja = MCashLine.getOfInvoiceList(getCtx(), getC_Invoice_ID(), null);
			//System.out.println(" LineasEnCaja : " + lineasDeCaja.size());
			
			if (isPaid 
					|| !asignaciones.isEmpty() 
					|| !lineasDeCaja.isEmpty()) {
				
				//System.out.println(" Se cancelan los pagos : " + cashLine.size());
				success = returnPayments(errorList, cashLine, cCashID);
			}
		}
		return success;
	}
	
	/** Regresar los pagos */
	private boolean returnPayments(final ErrorList errorList, final List<MCashLine> cashLine, final int cCashID) {
		boolean success =  errorList.isEmpty();
		if(success){
			final String error = BillPayments.actualizarPagos(getCtx(), cashLine, cCashID, this, get_TrxName());
			if(StringUtils.isNotEmpty(error)){
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), error));
			}
		}
		return success;
	}
	
	/** Regresar los pagos 
	public boolean returnPaymentsAnti(final ErrorList errorList, final boolean isPaid
//			, final List<MCashLine> cashLine
			, final int cCashID) {
		boolean success =  errorList.isEmpty();
		if(success){
			//System.out.println(" ¿Debe cancelar pagos? : " + cashLine.size());
			List<MAllocationHdr> asignaciones = MAllocationHdr.getAllocationHrdInv(getCtx(), getC_Invoice_ID(), false, null);
			//System.out.println(" Asignaciones : " + asignaciones.size());
			List<MCashLine> lineasDeCaja = MCashLine.getOfInvoiceList(getCtx(), getC_Invoice_ID(), null);
			//System.out.println(" LineasEnCaja : " + lineasDeCaja.size());
			
			if (isPaid 
					|| !asignaciones.isEmpty() 
					|| !lineasDeCaja.isEmpty()) {
				
				//System.out.println(" Se cancelan los pagos : " + cashLine.size());
//				success = returnPayments(errorList, cashLine, cCashID);
				final String error = BillPayments.actualizarPagos(getCtx()
						, cCashID
						, this
						, get_TrxName());
				
				if(StringUtils.isNotEmpty(error)){
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), error));
				}
			}
		}
		return success;
	}*/
	
//	/** Regresar los pagos */
//	private boolean returnPayments(final ErrorList errorList, final List<MCashLine> cashLine, final int cCashID) {
//		boolean success =  errorList.isEmpty();
//		if(success){
//			final String error = BillPayments.actualizarPagos(getCtx(), cashLine, cCashID, this, get_TrxName());
//			if(StringUtils.isNotEmpty(error)){
//				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), error));
//			}
//		}
//		return success;
//	}
	
	/** Actualizar la nota de credito con los totales exactos de la factura 
	 * para evitar centavos de mas
	 * @param idnotacredito
	 */
	public void totalUpdateNote(final int idnotacredito){
		final MInvoice notaOver = new MInvoice(getCtx(), idnotacredito, null);
		notaOver.setGrandTotal(getGrandTotal());
		notaOver.setTotalLines(getTotalLines());
		notaOver.save();
	}
	
	/** Mensaje de cuenta paciente/Extension */
	public String getCtaMessage(){
		String message = null;
		if (getEXME_CtaPacExt_ID() > 0) {
			final MEXMECtaPacExt ext = new MEXMECtaPacExt(getCtx(), getEXME_CtaPacExt_ID(), null);
			message = Utilerias.getLabel("caja.msg.nocta", ext.getEXME_CtaPac().getDocumentNo(), String.valueOf(ext.getExtensionNo()));
		}
		return message;
	}
	
	/** Cancelar una nota de remision 
	 *  NOTA: como no todas las facturas tienen remision,
	 *  el proceso de cancelación de remisión tambien repite algunas tareas del proceso de cancelación de facturas  */
	public boolean cancelSale(final ErrorList errorList, final MInvoice nota, final String txtMotivocancel, final int motivoCancelId, final int cCashID) throws Exception {
		boolean success =  errorList.isEmpty();
		
		if(success && getRef_Invoice_Sales_ID()>0){
			final MInvoice remision = new MInvoice(getCtx(), getRef_Invoice_Sales_ID(), get_TrxName());
			success =  remision.cancelSale(errorList, nota, txtMotivocancel, motivoCancelId, cCashID, get_TrxName());
			
		} else if(success && X_C_Invoice.DOCSTATUS_Drafted.equals(getDocStatus())) {
			success =  cancelSale(errorList, null, txtMotivocancel, motivoCancelId, cCashID, get_TrxName());
		}				
		return success;
	}
	
	/** Cancelar una nota de remision 
	 *  NOTA: como no todas las facturas tienen remision,
	 *  el proceso de cancelación de remisión tambien repite algunas tareas del proceso de cancelación de facturas  */
	private boolean cancelSale(final ErrorList errorList, final MInvoice nota, final String txtMotivocancel, final int motivoCancelId, final int cCashID, final String trxName) throws Exception {
		boolean success =  errorList.isEmpty();
		if(success){
			set_TrxName(trxName);
			openExtension(errorList);
			returnInout(errorList, nota);
			returnMovementConsig(errorList);
			returnPayments(errorList, isPaid(), 
					// Ticket #07429 - Se quitan anticipos
					MCashLine.getOfInvoiceList(getCtx(), getC_Invoice_ID(), true, null),cCashID);
			returnService(errorList);
			updateCancelSale(errorList, txtMotivocancel, motivoCancelId, cCashID);
		}
		return success;
	}
	
	
	/** Actualizar el estatus de cancelación si es remision */
	public boolean updateCancelSale(final ErrorList errorList, final String txtMotivocancel, final int motivoCancelId, final int cCashID) throws Exception {
		boolean success =  errorList.isEmpty();
		if(success){
			setDocStatus(MInvoice.ACTION_Void);
			setDocAction(MInvoice.ACTION_None);
			setProcessed(false, get_TrxName());
			setMotivoCancel(txtMotivocancel);
			setEXME_MotivoCancel_ID(motivoCancelId);
			setC_Cash_ID(cCashID);// se actualiza el folio que cancela la nota de remision
			if(!save(get_TrxName())) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("consultaPac.error.refreshFactArea")+CLogger.toStringError());
				throw new MedsysException();
			}
		}
		return success;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param cBPartnerId
	 * @param dateInvoice
	 * @param payRule
	 * @param currencyId
	 * @param trxName
	 * @return
	 */
	public static List<BeanPaySelect> getInvoiceSelection(Properties ctx, int cBPartnerId, Timestamp dateInvoice, String payRule, 
			int currencyId, int currencyId_view, String trxName){
		List<BeanPaySelect> list = new ArrayList<BeanPaySelect>();
		List<Object> params = new ArrayList<Object>();
		int currencyToId = Env.getC_Currency_ID(Env.getCtx());
		
		params.add(dateInvoice);//#1
		params.add(dateInvoice);//#2
		params.add(dateInvoice);//#3
		params.add(currencyId_view>0?currencyId_view:currencyToId);//#4
		params.add(dateInvoice);//#5
		params.add(dateInvoice);//#6
		params.add(currencyToId);//#7
		params.add(dateInvoice);//#8
		params.add(dateInvoice);//#9
		
		params.add(dateInvoice);//#9.1
		params.add(currencyId_view>0?currencyId_view:currencyToId);//#9.2
		params.add(dateInvoice);//#9.3
		
		params.add(X_C_DocType.DOCBASETYPE_APInvoice);//#10
		params.add(X_C_DocType.DOCBASETYPE_APDebitMemo);//#11
		params.add(currencyId);//#12
		params.add(dateInvoice);//#13
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append(" i.C_Invoice_ID, i.DateInvoiced, " );
		sql.append(" paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) AS DateDue, dt.c_doctype_id, ");
		sql.append(" CASE  ");
		sql.append(" WHEN paymentTermDueDays(i.C_PaymentTerm_ID, i.DateInvoiced, ?) < 0 ");//#1
		sql.append(" THEN 0 ");
		sql.append(" ELSE paymentTermDueDays(i.C_PaymentTerm_ID, i.DateInvoiced, ? ) ");//#2
		sql.append(" END  AS DaysDue, ");
		sql.append(" bp.Name,i.C_BPartner_ID, i.DocumentNo as documentNo, c.ISO_Code as isoCode, i.C_Currency_ID, i.GrandTotal, ");
		// Importe descuento
		sql.append(" paymentTermDiscount(i.GrandTotal,i.C_Currency_ID, ");
		sql.append(" i.C_PaymentTerm_ID,i.DateInvoiced, ?) AS DiscountAmt, ");//#3
		// Fecha descuento
		sql.append(" current_date-paymentTermDueDays(i.C_PaymentTerm_ID,i.DateInvoiced, ");
		sql.append(" current_date) AS DiscountDate, ");		
		// Importe vencido
		sql.append(" currencyConvert(invoiceOpenInvoice(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID), ");
		sql.append(" i.C_Currency_ID, ?, ?, i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID) as AmountDue, ");//#4 #5
		
		// Saldo en moneda del esquema
		sql.append(" currencyConvert(invoiceOpenInvoice(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID)-");
		sql.append(" paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?), ");//6
		sql.append(" i.C_Currency_ID, ?, ?, i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID) AS AmountPay ");//#7 #8
		// Saldo en moneda de la factura
		sql.append(" , (invoiceOpenInvoice(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID)-");
		sql.append(" paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?)) AS OpenInvoice ");//#9
		// Saldo moneda de la cuenta
		sql.append(" , currencyConvert(invoiceOpenInvoice(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID)-");
		sql.append(" paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?), ");//9.1
		sql.append(" i.C_Currency_ID, ?, ?, i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID) AS ViewAmtPay ");//#9.2 #9.3
		
		sql.append(" FROM c_invoice_v i ");
		sql.append(" INNER JOIN C_DocType dt ON (i.C_DocType_ID = dt.C_DocType_ID ");
		sql.append("                              AND dt.docbasetype IN (?,?)  ");//#10 #11
		sql.append("                              AND dt.AD_Org_ID=i.AD_Org_ID) ");
		sql.append(" INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID) ");
		sql.append(" INNER JOIN C_Currency c ON (i.C_Currency_ID=c.C_Currency_ID) ");
		sql.append(" INNER JOIN C_PaymentTerm p ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID) ");

		sql.append(" WHERE i.IsSOTrx='N' ");
		sql.append(" AND IsPaid='N' ");
//		sql.append(" AND NOT EXISTS (SELECT * FROM C_PaySelectionLine psl ");
//		sql.append(" INNER JOIN C_PaySelectionCheck psck on (psck.c_payselectioncheck_id = psl.c_payselectioncheck_id) ");
//		sql.append(" LEFT JOIN C_Payment pay on (pay.c_payment_id = psck.c_payment_id) ");
//		sql.append(" WHERE i.C_Invoice_ID=psl.C_Invoice_ID ");
//		sql.append(" AND psl.C_PaySelectionCheck_ID IS NOT NULL ");
//		sql.append(" AND pay.docstatus not in ('DR', 'RE')) ");
		sql.append(" AND i.DocStatus IN ('CO','CL') ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "i"));
		sql.append(" AND (currencyConvert(invoiceOpenInvoice(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID), " );  
		sql.append(" i.C_Currency_ID, ?, ?, i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID)) > 0 ");//#12 #13
		if (cBPartnerId > 0){
			params.add(cBPartnerId);
			sql.append(" AND bp.c_bpartner_id = ? ");//#14
		}
		
		if (StringUtils.isNotBlank(payRule)){
			params.add(payRule);
			sql.append(" AND i.PaymentRule= ? ");//#15
		}
		params.add(currencyId);
		sql.append(" AND i.C_Currency_ID = ? ");//#16
		
		sql.append(" ORDER BY bp.Name asc");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				BeanPaySelect paySelect = new BeanPaySelect();
				paySelect.setInvoiceId(rs.getInt("c_invoice_id"));
				paySelect.setDateInvoiced(rs.getTimestamp("DateInvoiced"));
				paySelect.setClient(rs.getString("name"));
				paySelect.setCurrency(rs.getString("isoCode"));

				paySelect.setDateDiscount(Constantes.sdfFecha(ctx).formatConvert(rs.getTimestamp("DiscountDate")));
				paySelect.setDaysDue(rs.getInt("daysDue"));
				paySelect.setDateDue(rs.getTimestamp("dateDue"));
				paySelect.setDiscount(rs.getBigDecimal("discountAmt"));
				paySelect.setDocumentNo(rs.getString("documentNo"));
				paySelect.setDueAmt(rs.getBigDecimal("amountDue"));
				paySelect.setPayAmt(rs.getBigDecimal("amountPay"));
				paySelect.setTotal(rs.getBigDecimal("grandTotal"));
				paySelect.setTypeDoc(rs.getInt("c_doctype_id"));
				paySelect.setOpenInvoice(rs.getBigDecimal("openInvoice"));
				paySelect.setConversionAmt(rs.getBigDecimal("ViewAmtPay"));
				list.add(paySelect);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}

	/**
	 * Obtiene el total de facturas pendientes por proveedor
	 * 
	 * @param ctx Contexto
	 * @param invoiceDate (obligatorio)
	 * @param payRule Regla de pago (no obligatorio)
	 * @param currencyId moneda (obligatorio)
	 * @param trxName Nombre de transaccion
	 * @return
	 */
	public static List<BeanPaySelect> getPartnerTotals(Properties ctx, Timestamp invoiceDate, String payRule, int currencyId, Boolean dueBill, String trxName){
		final List<BeanPaySelect> list = new ArrayList<BeanPaySelect>();
		final List<Object> params = new ArrayList<Object>();
		
		final StringBuilder sql = new StringBuilder();
		
		// obtiene las facturas pendientes del cliente
		sql.append("WITH invoice AS ( \n");
		sql.append("	SELECT i.*, invoiceOpenInvoice(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID) AS dueAmt \n");
		sql.append("	FROM C_Invoice_v i \n");
		sql.append("	INNER JOIN c_doctype dt ON (dt.c_doctype_id=i.c_doctype_id \n");
		sql.append("	AND dt.DOCBASETYPE IN (?,?) AND dt.AD_Org_ID=i.AD_Org_ID) \n");
		sql.append("	WHERE  i.isActive='Y' \n");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, "	", MInvoice.Table_Name, "i"));
		sql.append("	AND i.IsSOTrx='N' AND i.IsPaid<>'Y' \n");
		sql.append("	AND i.DocStatus IN (?,?) \n");
		sql.append(") \n");
		params.add(X_C_DocType.DOCBASETYPE_APInvoice);
		params.add(X_C_DocType.DOCBASETYPE_APDebitMemo);
		params.add(DOCSTATUS_Completed);
		params.add(DOCSTATUS_Closed);
		
		// Agrupa las facturas por proveedor	
		sql.append("SELECT c.Name,i.C_BPartner_ID,i.C_Currency_ID, \n");
		sql.append("       COUNT(i.c_invoice_id) AS countInvoice, \n");
		// Calcula el total de facturas vencidas
		sql.append("SUM(CASE WHEN paymenttermduedays(i.C_PaymentTerm_ID,i.DateInvoiced,?) > 0 \n");//#1
		sql.append("    THEN 1 ELSE 0 END) AS vencidas, \n");
		params.add(invoiceDate);
		// AmountDue
		sql.append("SUM(currencyConvert(i.dueAmt,i.C_Currency_ID,?,?, \n");//#2,3
		sql.append("                    i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID) \n");
		sql.append("                   ) as AmountDue,  \n");
		params.add(currencyId);
		params.add(invoiceDate);
		// AmountPay	
		sql.append("SUM(currencyConvert(i.dueAmt- paymentTermDiscount(i.GrandTotal, \n");
		sql.append("                                                  i.C_Currency_ID, \n");
		sql.append("                                                  i.C_PaymentTerm_ID, \n");
		sql.append("                                                  i.DateInvoiced, \n");
		sql.append("                                                  ?), \n");//#4
		sql.append("                    i.C_Currency_ID,?,?,i.C_ConversionType_ID, \n");//#5,6
		sql.append("                    i.AD_Client_ID,i.AD_Org_ID)) AS AmountPay \n");
		params.add(invoiceDate);
		params.add(currencyId);
		params.add(invoiceDate);
		
		sql.append("FROM invoice i \n");
		sql.append("INNER JOIN c_bpartner c ON (i.c_bpartner_id=c.c_bpartner_id) \n");
		sql.append("WHERE i.dueAmt > 0 \n");// que tengan un saldo
		// Regla de pago		
		if(StringUtils.isNotBlank(payRule)){
			params.add(payRule);
			sql.append(" AND i.PaymentRule=? \n");
		}
		// vencidas
		if (dueBill) {
			sql.append(" AND paymenttermduedays(i.C_PaymentTerm_ID,i.DateInvoiced,now()) > 0 \n");
		}
		
		params.add(currencyId);
		sql.append(" AND i.C_Currency_ID = ? ");//15
		
		sql.append("GROUP BY c.name, i.C_BPartner_ID, i.C_Currency_ID \n");
		sql.append("ORDER BY c.name  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BeanPaySelect paySelect = new BeanPaySelect();
				paySelect.setPartner(rs.getString("name"));
				paySelect.setPartnerId(rs.getInt("C_BPartner_ID"));
				paySelect.setCurrencyId(rs.getInt("C_Currency_ID"));
				paySelect.setPayAmt(rs.getBigDecimal("AmountPay"));
				paySelect.setDueAmt(rs.getBigDecimal("AmountDue"));
				paySelect.setCountInvoice(rs.getInt("countInvoice"));
				paySelect.setVencidas(rs.getInt("vencidas"));
				list.add(paySelect);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Genera una nota de cr&eacute;dito a partir de una factura
	 *
	 * @param pOldInvoice
	 *            La factura original
	 * @param dateDoc
	 *            La fecha de la nota de cr&eacut;dito
	 * @param C_CashBook_ID
	 *            El identificador de la caja f&iacut;sica
	 * @param C_Cash_ID
	 *            El identificador de la caja (0 si es null)
	 * @param trxName
	 *            el nombre de la transacci&oacute;n
	 * @return
	 * @throws Exception
	 *///CARD #1299
	public static MInvoice createNotaCredFromInvoice(
			final MInvoice pOldInvoice, final int C_Cash_ID,
			MDocType C_DocType, final String trxName) throws Exception {
		MInvoice newNota = null;
		if (C_DocType.getDocBaseType().equals(MDocType.DOCBASETYPE_ARCreditMemo)) {
			if (C_Cash_ID <= 0) {
				return newNota;
			}
		}

		if (C_DocType.getC_DocType_ID() <= 0) {
			throw new Exception("error.caja.tipoDoc.notaCred");
		}

		// Generacion de nota de credito
		newNota = new MInvoice(C_DocType.getCtx(), 0, trxName);
		// Datos de la factura original
		PO.copyValues(pOldInvoice, newNota);
		// Asignamos el tipo de documento
		newNota.setC_DocType_ID(C_DocType.getC_DocType_ID());
		newNota.setC_DocTypeTarget_ID(C_DocType.getC_DocType_ID());
		newNota.setBackoffice(false);
		// Fecha actual
		newNota.setDateInvoiced(new Timestamp(System.currentTimeMillis()));
		// asignamos la caja
		if (C_Cash_ID > 0) {
			newNota.setC_Cash_ID(C_Cash_ID);
		}

		newNota.setRef_Invoice_ID(pOldInvoice.getC_Invoice_ID());
		newNota.setAfecta_Caja(pOldInvoice.isAfecta_Caja());// solo cuando se cobra en caja o es
		// pagada totalmente con anticipos
		newNota.setIsPaid(true);// se paga con la nota de credito
		newNota.setIsTaxIncluded(false);
		newNota.setTrxType(X_C_Invoice.TRXTYPE_TotalCreditNoteCustomer);
		newNota.setIsApproved(false);
		newNota.setIsPrinted(false);
		newNota.setPosted(false);
		newNota.setProcessed(false);

		newNota.setSELLO(null);
		newNota.setUUID(null);
		newNota.setCadena(null);

		if (!newNota.save(trxName)) {
			throw new Exception("error.caja.notaCred.noSave");
		}

		newNota.copyLinesFrom(pOldInvoice, trxName);

		return newNota;
	}
	
	public BigDecimal getOpenAmtInvoice(){
		return getOpenAmt(false, new Timestamp(System.currentTimeMillis()));
	}
	
	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public BigDecimal getAmountDiscountPxP() {
		return amountDiscountPxP;
	}

	public void setAmountDiscountPxP(BigDecimal amountDiscountPxP) {
		this.amountDiscountPxP = amountDiscountPxP;
	}

	public BigDecimal getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}

	public BigDecimal getAmountToApply() {
		return amountToApply;
	}
	public void setAmountToApply(BigDecimal amountToApply) {
		this.amountToApply = amountToApply;
	}
	
	/**
	 * Listado de facturas 
	 * @param ctx
	 * @param socio
	 * @param trxName
	 * @return
	 */
	public static List<MInvoice> getInvoiceOfBPartner(
			final Properties ctx, final int socio, final String trxName) {

		final List<MInvoice> list = new ArrayList<MInvoice>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		
		.append(" SELECT paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) AS DateDue ")
		.append(" , paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?) AS DiscountAmt ")//#1
		.append(" , currencyConvert(invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID),i.C_Currency_ID, ?,?,i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID) as AmountDue ")//#2, 3
		.append(" , currencyConvert(invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID)-paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?),i.C_Currency_ID, ?,?,i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID) AS AmountPay ")//#4, 5, 6
		.append(" , i.* ")
		.append(" FROM C_Invoice i ")
		.append(" WHERE i.isActive= 'Y'      ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "i"))
		.append(" AND i.isSOTrx = 'N'  ")
		.append(" AND i.C_BPartner_ID = ?  ")//# 7
		.append(" ORDER BY i.DateDue ASC ");

		ResultSet rset = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(7, socio);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				final MInvoice mInvoice = new MInvoice(ctx, rset, null);
				
				mInvoice.setExpirationDate(rset.getTimestamp("DateDue"));
				mInvoice.setAmountDiscountPxP(rset.getBigDecimal("AmountDue") );
				mInvoice.setAmountDue(rset.getBigDecimal("AmountPay") );
						
				list.add(mInvoice);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return list;
	}// getInvoices

	/** Asignar los anticipos al crear la remision */
	public BigDecimal allocateAdvancePaymentOfCustomer(){
		final BigDecimal amtAdvance;
		if(getEXME_CtaPacExt_ID()>0){
			final MEXMEAnticipo mAnt = getCtaPacExt().getMAnticipo();
			amtAdvance = mAnt==null?Env.ZERO:mAnt.allocateAdvancePaymentCustomer(this, get_TrxName());
			setIsPaid(amtAdvance.compareTo(getGrandTotal())>=0);// solo cuando esta pagada sin cxc
			setAfecta_Caja(amtAdvance.compareTo(getGrandTotal())>=0);// solo cuando se cobra en caja o es pagada totalmente con anticipos
			if(!save(get_TrxName())){
				throw new MedsysException();
			}
		} else {
			amtAdvance = Env.ZERO;
		}
		return amtAdvance;
	}
	
	/** Devolver los anticipos a la extensión cero */
	public void cancelAdvancePaymentOfCustomer(){
		if(getEXME_CtaPacExt_ID()>0){
			final MEXMEAnticipo mAnt = getCtaPacExt().getMAnticipo();
			if(mAnt!=null){
				mAnt.cancelAdvancePaymentCustomer();
			}
		}
	}
	
	/** Reversa por error de timbrado 
	 * @throws Exception */
	public void reversePostmark(final ErrorList errorList, final int C_Cash_ID) throws Exception {
		// Nota de crédito
		final MInvoice notaCred = createNotaCredFromInvoicePostmark(errorList, C_Cash_ID, get_TrxName());
		if(!errorList.isEmpty() || notaCred==null){
			return;
		}
		setIsPrintedPre(true);
		// Factura sin timbrar
		setDocStatus(MInvoice.ACTION_Void);
		setDocAction(MInvoice.ACTION_None);
		setProcessed(false, get_TrxName());
		setIsPaid(true);// se paga con la nota de credito
		MFactAcct.delete(MInvoice.Table_ID, getC_Invoice_ID(), get_TrxName());
		if (!save(get_TrxName())) {
			throw new MedsysException();
		}

		// Factura global
		if(isMultiple()
			&& MInvoice.reversUpdateNRs(getCtx(), getC_Invoice_ID(), get_TrxName())<0){
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("consultaPac.error.refreshFactArea")); 
				return;//TODO: TWRY reactivar los pagos de las remisiones asignados a la factura global
		}
	}
	
	/** Crear la Reversa al timbrado con una nota de crédito 
	 * @throws Exception */
	private MInvoice createNotaCredFromInvoicePostmark(final ErrorList errorList, final int C_Cash_ID, final String trxName) throws Exception{
		// genera nota de credito con los datos de la factura
		final MInvoice notaCred = MInvoice.createNotaCredFromInvoice(
						this
						, C_Cash_ID
						, new MDocType(getCtx(),MEXMEDocType.notasCreditoDebito(getCtx(), MDocType.DOCBASETYPE_ARCreditMemo),trxName)
						, trxName);
		
		if(notaCred==null){
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("error.caja.notaCred.noSave")); 
			return null;
		}
		
		notaCred.setBackoffice(false);
		notaCred.setTrxType(X_C_Invoice.TRXTYPE_CustomerInvoiceCanceled);

		// solo cuando se cobra en caja o es
		// pagada totalmente con anticipos
		notaCred.setAfecta_Caja(false);
		notaCred.setIsPaid(true);// se paga con la nota de credito

		// ESTO ES LO QUE DIFIERE CON EL PROCESO DE CANCELACION DE FACTURAS
		notaCred.setDocStatus(MInvoice.DOCSTATUS_Voided);
		notaCred.setDocAction(MInvoice.DOCACTION_None);
		notaCred.setProcessed(false, trxName);
		MFactAcct.delete(MInvoice.Table_ID, notaCred.getC_Invoice_ID(),trxName);
		// 
		if (!notaCred.save(trxName)) {
			throw new MedsysException();
		}
		
		return notaCred;
	}

	/** Asignar datos del paciente */
	public void setPaciente(final MEXMEPaciente paciente){
		if (paciente != null && paciente.getEXME_Paciente_ID() > 0) {
			setEXME_Paciente_ID(paciente.getEXME_Paciente_ID());
			setNombre_Paciente(paciente.getNombre_Pac());
			setInvoicePhone(paciente.getTelParticular());
		}
	}
	/** Asignar datos del medico */
	public void setMedico(final MEXMEMedico medico){
		if (medico != null && medico.getEXME_Medico_ID() > 0) {
			setEXME_Medico_ID(medico.getEXME_Medico_ID());
			setNombre_Medico(medico.getNombre_Med());
		}
	}
	/** Asignar datos para una factura directa sin cuenta paciente */
	public void setValuesDirectBilling(
			final int extension
			) {
		
		int pacienteId = info.getEXME_Paciente_ID();
		int medicoId =  info.getEXME_Medico_ID();
		MCash mCash = info.getmCash();
		int currencyId = info.getC_Currency_ID();
		String addenda = info.getAddenda();
		
		setPaciente(new MEXMEPaciente(getCtx(), pacienteId, get_TrxName()));
		setMedico(new MEXMEMedico(getCtx(), medicoId, get_TrxName()));
		setGlobalDiscount(Env.ZERO);
		setAddenda(addenda);
		setValues(extension, get_TrxName());
		setC_Cash_ID(mCash.getC_Cash_ID());
		setC_CashBook_ID(mCash.getC_CashBook_ID());
		setAfecta_Caja(false);
//		setBackoffice(false);
//		setTrxType(X_C_Invoice.TRXTYPE_SalesReceipt);
		setC_Currency_ID(currencyId);
		if (!save(get_TrxName())) {
			throw new MedsysException();
		} 
	}
	/** Asignar datos para una factura directa sin cuenta paciente */
	public void setInfoPatient(/*final InfoInvoice info*/){
		setC_BPartner_ID(info.getC_BPartner_ID());
		setDescription(info.getDescription());
		setPOReference(info.getPOReference());
		setAddress2(info.getAddress2());
		setAddress3(info.getAddress3());
		setNumExt(info.getNumExt());
		setNumIn(info.getNumIn());
		setCity(info.getCity());
		setPostal(info.getPostal());
		setEXME_TownCouncil_ID(info.getEXME_TownCouncil_ID());
		setC_Country_ID(info.getC_Country_ID());
		setC_Region_ID(info.getC_Region_ID());
		//setC_BPartner_ID(info.getC_BPartner_ID());
		setObservation(info.getObservation());
		setC_BPartner_Location_ID(info.getC_BPartner_Location_ID());
		setIsOrderFacLineCategory(info.isOrderFacLineCategory());
		setSortBy(info.getSortBy());
		setDiscountAmt(info.getDiscountAmt()); //
		setDiscountPorcent(info.getDiscountPorcent());
	}
	
	
	/**
	 * Get Invoice Lines
	 *
	 * @param requery
	 * @return lines
	 */
	public MInvoiceLine getLines(final int M_InoutLine_ID) {
		if (mLines == null || mLines.length == 0) {
			mLines = getLines(null, false, get_TrxName());// Expert twry
		}
		MInvoiceLine lNota = null;
		for (MInvoiceLine line: mLines) {
			if(line.getM_InOutLine_ID()==M_InoutLine_ID){
				lNota = line;
				break;
			}
		}
		return lNota;
	} // getLines
	
	
	/** Quitar la relación servicio factura
	 * Se Verifican si la factura a cancelar es un servicio para
	 * actualizar la informacion en la tabla de servicios y que quede
	 * nuevamente preparado para ser facturado en caso de que asi se
	 * requiera. Esto ayudara para poder tambien cancelar un servicio de
	 * una factura que ya fue cancelada, reportado en HRAE. Jesus Cantu
	 * el 31 Marzo 2010. Devuelve true si es un servicio y pudo ser
	 * cancelado o si no encontro nada que cancelar tambien. Devuelve
	 * false si ocurre un error.
	 */

	public boolean returnService(final ErrorList errorList) {
		boolean success =  errorList.isEmpty();
		if (success && getEXME_CtaPacExt_ID() == 0 && !MEXMEActPacienteIndH.isFactServicio(getCtx(), getC_Invoice_ID(), get_TrxName())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("error.factDirecta.facturar.cancelarFacYServ"));
		}
		return success;
	}
	
	/** Permitir correr jUnit */
	public static boolean cancelInvoiceTest(final MInvoice mInvoice_o_Remision, MCash mCash, final String trxName){
		final ErrorList errorList = new ErrorList();
		MInvoice nota = null;
		try {
			final MDocType[] types = MEXMEDocType.getOfDocBaseType(mInvoice_o_Remision.getCtx(), MDocType.DOCBASETYPE_ARCreditMemo);
			final List<KeyNamePair> motivo = MMotivoCancel.getList(mCash.getCtx(), MMotivoCancel.MODULO_Billing);
			final int motivoId = Integer.valueOf(motivo.get(0).getID());

			// Cancelar factura
			if(mInvoice_o_Remision.getRef_Invoice_Sales_ID()>0){
				final MInvoice notaCred = mInvoice_o_Remision.notaCredito(
						mInvoice_o_Remision.getCtx()
						, mInvoice_o_Remision
						, mCash.getC_Cash_ID()
						, mCash.getC_CashBook_ID()
						, " CANCELACIÓN DE PRUEBA"
						, motivoId
						, new Timestamp(System.currentTimeMillis())
						, types[0]
								, trxName);
				if (notaCred == null) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("error.caja.notaCred.noSave"));

				} else {
					
					System.out.println("NOTA  ID : " + notaCred.getC_Invoice_ID());
					mInvoice_o_Remision.returnService(errorList);
					mInvoice_o_Remision.returnPayments(errorList
							, mInvoice_o_Remision.isPaid() 
							, MCashLine.getOfInvoiceList(mInvoice_o_Remision.getCtx(), mInvoice_o_Remision.getC_Invoice_ID(), true, null)
							, mCash.getC_Cash_ID());
					if(errorList.isEmpty()){
						nota = notaCred;
					} else {
						return false;
					}
					
				}

				if(errorList.isEmpty() && notaCred!=null){
					// Cancelar el documento nota de remision
					System.out.println("Remision de la factura ID : " + mInvoice_o_Remision.getRef_Invoice_Sales_ID());
					mInvoice_o_Remision.cancelSale( errorList
							, notaCred
							, " CANCELACIÓN DE PRUEBA"
							, motivoId
							, mCash.getC_Cash_ID());

				}
			} else {
				System.out.println("Solo Remision  ID : " + mInvoice_o_Remision.getC_Invoice_ID());
				mInvoice_o_Remision.cancelSale( errorList
						, null
						, " CANCELACIÓN DE PRUEBA"
						, motivoId
						, mCash.getC_Cash_ID());
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add(Error.VALIDATION_ERROR, "Error Catch");
		}
		return errorList.isEmpty() && nota!=null;
	}

	
	/**
	 * Despues de generar una remisión y esta es enviada a timbrar 
	 * se deberán pasar los pagos a esta.
	 * @param errorList Listado de errores
	 * @return TRUE: Si no se genero ningún error
	 */
	public boolean processingPostmark(final ErrorList errorList, final MCash mCash){
		sLog.log(Level.WARNING, "%MInvoice l-9926 - Entrada al metodo processingPostmark%");
		if(StringUtils.isNotEmpty(getUUID()) && errorList.isEmpty()){
			 
			Trx mTrx = Trx.get(Trx.createTrxName("FTAl"), true);
			set_TrxName(mTrx.getTrxName());
			
			try {

				if (save(mTrx.getTrxName())) {
					for (int i = 0; i < lstInvLines.size(); i++) {
						lstInvLines.get(i).setC_Invoice_ID(getC_Invoice_ID());
						lstInvLines.get(i).setInvoice(this);
						
						if(!lstInvLines.get(i).save(mTrx.getTrxName())){
							throw new MedsysException();
						}
					}
					 
				} else {
					sLog.log(Level.WARNING, "%MInvoice l-9945 - processingPostmark "+ this.getDocumentNo() +" No se pudo guardar la factura %");
					throw new MedsysException();
				}
				
				
				// Terminar el documento
				if (completeIt().equals(DocAction.STATUS_Drafted)) {
					final MInvoice mRemision = new MInvoice (getCtx(), getRef_Invoice_Sales_ID(), mTrx.getTrxName());
					setGrandTotal(this.getGrandTotal());
					setProcessed(false, mTrx.getTrxName());
					setStatusInProgress();
					setIsPaid(mRemision.isPaid());// Pasar si esta pagada o no la nota de remision
					
					if (!save(mTrx.getTrxName())) {
						sLog.log(Level.WARNING, "%MInvoice l-9959 - processingPostmark "+ this.getDocumentNo() +" No se pudo guardar la factura %");
						throw new MedsysException();
					}

					// Crear asignaciones hacia la factura y cancelar las de la remisión
					boolean success = MAllocationLine.createAllocationsLines(getCtx()
						, mRemision
						, getC_Invoice_ID() //Factura
						, mTrx.getTrxName());				

					if(success && errorList.isEmpty()){
						mTrx.commit();
					} else {						
						errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("error.caja.noSaveAllocation"));
						sLog.log(Level.WARNING, "Realizacion de asignaciones " + success + "-" + errorList.toString());
					}

				} else {
					sLog.log(Level.WARNING, "%MInvoice l-9976 - processingPostmark "+ this.getDocumentNo() +" No se pudo completar la factura %");
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("error.factDirecta.refresh")+" "+getDocStatus());
				}
			} catch (Exception e) {
				errorList.add(Error.UNKNOWN_ERROR, Utilerias.getLabel("error.factDirecta.refresh")+e.getLocalizedMessage());
				
			} finally {
				if(!errorList.isEmpty()) {
					try {
						// Cancela la factura pero no la remision
						reversePostmark(errorList, mCash.getC_Cash_ID());
						updateStatusDead();
						setPosted(Boolean.TRUE);
						if(!save(mTrx.getTrxName())){
							throw new MedsysException();
						}
						mTrx.commit();
						
						// TODO Mandar mensaje de cancelación
					} catch (Exception e) {
						errorList.add(Error.UNKNOWN_ERROR, "Error Catch"+e.getLocalizedMessage());
						throw new MedsysException(e);
						
					} finally {
						Trx.close(mTrx, true);
					}
				}
				Trx.close(mTrx, true);
			}
		} else {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("msg.error.uuid"));
		}
		
		sLog.log(Level.SEVERE, "- Normal -" + errorList.toString());
		return errorList.isEmpty();
	}
	
//	/**
//	 * Despues de generar una factura global (en memoria) y esta es enviada a timbrar 
//	 * se deberán pasar los pagos a esta de todas las remisiones
//	 * y dejar como facturadas las remisiones
//	 * @param errorList Listado de errores
//	 * @return TRUE: Si no se genero ningún error
//	 */
//	public boolean processingPostmark(final ErrorList errorList, final List<MInvoice> getList, final MCash mCash){
//		sLog.log(Level.WARNING, "%MInvoice l-9926 - Global - Entrada al metodo processingPostmarkGlobal%");
//		if(StringUtils.isNotEmpty(getUUID()) && errorList.isEmpty()){
//			 
//			final Trx mTrx = Trx.get(Trx.createTrxName("FGAll"), true);
//			set_TrxName(mTrx.getTrxName());
//			
//			try {
//
//				// Guardar la factura y sus lineas
//				if (save(mTrx.getTrxName())) {
//					for (int i = 0; i < lstInvLines.size(); i++) {
//						lstInvLines.get(i).setC_Invoice_ID(getC_Invoice_ID());
//						lstInvLines.get(i).setInvoice(this);
//						
//						if(!lstInvLines.get(i).save(mTrx.getTrxName())){
//							throw new MedsysException();
//						}
//					}
//				} else {
//					sLog.log(Level.WARNING, "%MInvoice l-9945 - Global - processingPostmarkGlobal "+ this.getDocumentNo() +" No se pudo guardar la factura %");
//					throw new MedsysException();
//				}
//				
//				// Asignar estatus de completo al documento
//				if (completeIt().equals(DocAction.STATUS_Drafted)) {
//					
//					boolean isPaidAllInvoices = false;
//					boolean success = true;
//					
//					for(final MInvoice remision : getList){
//
//						// Actualizar las remisiones a que esta facturada
//						remision.setMultiple_ID( getC_Invoice_ID());//MInvoice.updateNRs(getCtx()
//						if (!remision.save(mTrx.getTrxName())) {
//							sLog.log(Level.WARNING, "%MInvoice l-9983 - processingPostmarkGlobal "+ remision.getC_Invoice_ID() +" No se actualiza la remision %");
//							throw new MedsysException();
//						}
//
//						// Cancelar la asignacion de la remision y crear una pra la factra
//						if(!MAllocationLine.createAllocationsLines(getCtx()
//								, remision
//								, getC_Invoice_ID()
//								, mTrx.getTrxName())){
//							success = false;
//							break;
//						}
//						
//						if(isPaidAllInvoices && !remision.isPaid()){
//							isPaidAllInvoices =  false;
//						}
//					}// fin for
//					
//					// Actualizar la factura 
//					setProcessed(false, mTrx.getTrxName());
//					setStatusInProgress();
//					setIsPaid(isPaidAllInvoices);// False mientras en global
//					if (!save(mTrx.getTrxName())) {
//						sLog.log(Level.WARNING, "%MInvoice l-9983 - processingPostmarkGlobal "+ this.getDocumentNo() +" No se pudo guardar la factura %");
//						throw new MedsysException();
//					}
//
//					if(success && errorList.isEmpty()){
//						mTrx.commit();
//						
//					} else {						
//						errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("error.caja.noSaveAllocation"));
//						sLog.log(Level.WARNING, "processingPostmarkGlobal: Realizacion de asignaciones " + success + "-" + errorList.toString());
//					}
//
//				} else {
//					sLog.log(Level.WARNING, "%MInvoice l-9976 - Global - processingPostmarkGlobal "+ this.getDocumentNo() +" No se pudo completar la factura %");
//					errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("error.factDirecta.refresh")+" "+getDocStatus());
//				}
//				
//			} catch (Exception e) {
//				errorList.add(Error.UNKNOWN_ERROR, Utilerias.getLabel("error.factDirecta.refresh")+e.getLocalizedMessage());
//				
//			} finally {
//				if(!errorList.isEmpty()) {
//					try {
//						// Cancela la factura pero no la remision
//						reversePostmark(errorList, mCash.getC_Cash_ID());
//						updateStatusDead();
//						setPosted(Boolean.TRUE);
//						if(!save(mTrx.getTrxName())){
//							throw new MedsysException();
//						}
//						mTrx.commit();
//						
//						// TODO_ Mandar mensaje de cancelación
//					} catch (Exception e) {
//						errorList.add(Error.UNKNOWN_ERROR, "processingPostmarkGlobal: Error Catch - Global - "+e.getLocalizedMessage());
//						throw new MedsysException(e);
//						
//					} finally {
//						Trx.close(mTrx, true);
//					}
//				}
//				Trx.close(mTrx, true);
//			}
//		} else {
//			errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("msg.error.uuid"));
//		}
//		
//		sLog.log(Level.SEVERE, "processingPostmarkGlobal Global - "+ errorList.toString());
//		return errorList.isEmpty();
//	}
	
	
	/**
	 * Calculo de impuestos con CCCmD y desc
	 * 
	 * @param lstCCCmDDesc
	 * @param totalVta
	 * @param totalVtaImp
	 * @return Suma de todas las lineas por la columna LineNetAmt
	 */
	private BigDecimal getAmountTax(final ErrorList errorList, final String trxName) {

		int no = DB.executeUpdate("DELETE C_InvoiceTax WHERE C_Invoice_ID="+ getC_Invoice_ID(), get_TrxName());
		if (no != 1) {
			log.warning("(1) #" + no);
		}
		
		BigDecimal totalTaxAmt;
		if(isDetailTax()){
			totalTaxAmt = detailedTaxCalculation();
		} else {
			totalTaxAmt = taxCalculation();
		}
		return totalTaxAmt;
	}
	
	/** Calcular el impuesto de las lineas sin detallar coaseguros/deducibles */
	private BigDecimal taxCalculation(){
		BigDecimal totalTaxAmt;
		final boolean isInsurance = getCliente().isFacturarAseg();
		if(isInsurance){
			totalTaxAmt = getInsuranceTaxs();
		} else {
			totalTaxAmt = getPatientTaxs();
		}
		return totalTaxAmt;
	}
	
	/** Calcular el impuesto de las lineas cuando los coaseguros/deducible estan deatllados */
	private BigDecimal detailedTaxCalculation(){
		// Primero NO conciderar las lineas de coaseguros/deducibles ( CCCmD )
		BigDecimal totalTaxAmt =Env.ZERO;
		final List<MInvoiceTax>   lstTaxs = MEXMEInvoiceLine.getLinesGroupTax(getCtx(), getC_Invoice_ID(), get_TrxName());
		for (final MInvoiceTax bean: lstTaxs) {
			totalTaxAmt = totalTaxAmt.add(bean.getTaxAmt());
			if(!bean.save(get_TrxName())){
				throw new MedsysException();
			}
		}
		
		// Ahora considerando posibles lineas de coaseguros/deducibles ( CCCmD )
		totalTaxAmt = totalTaxAmt.add(incluirImpuestoCCCmD());
		return totalTaxAmt;
	}
	
	/** Determinar si el impuesto de los coaseguro/deducibles ya han sido detallados */
	private boolean isDetailTax(){
		// Debido a un error y al no poder arreglar los datos de producción se incluye este codigo
		// Son CCCmD
		boolean isNewDetail = true;
		final List<MInvoiceLine> lstCCCmD = MEXMEInvoiceLine.getLinesCCCmD(getCtx(), getC_Invoice_ID(), get_TrxName());
		for (final MInvoiceLine mInvLine : lstCCCmD) {
			if(mInvLine.getDetailTax()==null || mInvLine.getDetailTax().length()==0){
				isNewDetail = false;
				break;
			}
		}
		return isNewDetail;
	}
	
	/** Impuestos de coaseguros y deducibles */
	private BigDecimal incluirImpuestoCCCmD(){
		BigDecimal totalTaxAmt =Env.ZERO;
		final List<MInvoiceLine> lstCCCmD = MEXMEInvoiceLine.getLinesCCCmD(getCtx(), getC_Invoice_ID(), get_TrxName());
		// 
		if(!lstCCCmD.isEmpty()){
			
			final java.lang.reflect.Type listType = new TypeToken<List<TaxConcepts>>() {}.getType();
			final Gson gson = new Gson();
			
			// Iterar las lineas de coaseguros/deducibles sin tomar la tasa de impuesto que tiene el registro 
			for (final MInvoiceLine fact: lstCCCmD) {
				/*
				fact.getLineNetAmt(); //421.86
				fact.getTaxAmt(); //26.298000000000
				fact.getPriceActual();//421.860000*/
				BigDecimal factor = null;
				if(fact.getDiscountTaxAmt()!=null && fact.getDiscountTaxAmt().compareTo(Env.ZERO)!=0){
					// Factor porcentaje (DESCUENTO SOBRE CCCmD)
					factor = (fact.getDiscountTaxAmt().multiply(Env.ONEHUNDRED)//2.922000000000
							.divide(fact.getDiscountTaxAmt().add(fact.getTaxAmt()), 6, BigDecimal.ROUND_HALF_UP)	//26.298000000000
							).divide(Env.ONEHUNDRED, 6, BigDecimal.ROUND_HALF_UP);
				}
				
				
				final String json = fact.getDetailTax();
				final List<TaxConcepts> target2 = gson.fromJson(json, listType);
				
				// Prorrateo entre las tasas por cada coaseguro/deducible
				for (final TaxConcepts imp: target2) {

					final MInvoiceTax  mInvTax = getInvoiceTax(imp.getTasa());

					if(factor==null || factor.compareTo(Env.ZERO)==0){
						mInvTax.setTaxAmt(mInvTax.getTaxAmt().add(imp.getImpuesto())); // 
						mInvTax.setTaxBaseAmt(mInvTax.getTaxBaseAmt().add(imp.getBase()));

					} else {
						// En caso de que hubiera un descuento sobre CCCmD
						BigDecimal a = Env.ZERO;
						if(imp.getImpuesto().compareTo(Env.ZERO)!=0){
							a = imp.getImpuesto().subtract(imp.getImpuesto().multiply(factor));
						}

						BigDecimal b = imp.getBase().subtract(imp.getBase().multiply(factor));


						mInvTax.setTaxAmt(mInvTax.getTaxAmt().add(a)); // 
						mInvTax.setTaxBaseAmt(mInvTax.getTaxBaseAmt().add(b));

					} 


					if(!mInvTax.save(get_TrxName())){
						throw new MedsysException();
					}

					totalTaxAmt = totalTaxAmt.add(imp.getImpuesto());
				}
			}
		}
		return totalTaxAmt;
	}
	
	/** Impuestos de la factura */
	private MInvoiceTax getInvoiceTax(int C_tax_ID ){
		MInvoiceTax mInvoiceTax = new Query(getCtx(), X_C_InvoiceTax.Table_Name, " C_Tax_ID = ? AND C_Invoice_ID = ? ", get_TrxName())
		.setOnlyActiveRecords(true)
		.setApplyAccessFilter(true)
		.setParameters(C_tax_ID, getC_Invoice_ID())
		.first();
		
		if(mInvoiceTax == null){
			mInvoiceTax = new MInvoiceTax(getCtx(), 0, get_TrxName());
			mInvoiceTax.setC_Invoice_ID(getC_Invoice_ID());
			mInvoiceTax.setC_Tax_ID(C_tax_ID);
		}
		
		return mInvoiceTax;
	}
	
//	/** Solo para facturas de clientes se deben considerar los pagos y notas de credito*/
//	public void setIsPaid (boolean IsPaid) {
//		if(X_C_DocType.DOCBASETYPE_ARInvoice.equals(getC_DocType().getDocBaseType())
//				&& X_C_DocType.DOCSUBTYPESO_SalesReceipt.equals(getC_DocType().getDocSubTypeSO())){
//			// Solo si es factura cliente
//			super.setIsPaid(getSaldo(true, false).compareTo(Env.ZERO)<=0);
//		} else {
//			super.setIsPaid(IsPaid);
//		}
//	}
	
//	private BigDecimal	paySelectCheckAmount;
//	private BigDecimal	paySelectAmountDue;
//	private BigDecimal	paySelectAmountPay;
//	
//	public BigDecimal getPaySelectAmountDue() {
//		return paySelectAmountDue;
//	}
//	public BigDecimal getPaySelectAmountPay() {
//		return paySelectAmountPay;
//	}
//	public BigDecimal getPaySelectCheckAmount() {
//		return paySelectCheckAmount;
//	}
//	public void setPaySelectAmountDue(BigDecimal paySelectAmountDue) {
//		this.paySelectAmountDue = paySelectAmountDue;
//	}
//	public void setPaySelectAmountPay(BigDecimal paySelectAmountPay) {
//		this.paySelectAmountPay = paySelectAmountPay;
//	}
//	public void setPaySelectCheckAmount(BigDecimal paySelectCheckAmount) {
//		this.paySelectCheckAmount = paySelectCheckAmount;
//	}



	private String ISOCode = null;
	private int daysToExpire = 0;
	private String dateToExpire = null;
	private String docTypeName = null;
	
	public String getISOCode() {
		return ISOCode;
	}

	public void setISOCode(String string) {
		ISOCode =  string;
	}

	public int getDaysToExpire() {
		if (daysToExpire <= 0 && !isPaid()) {
			final Date today = new Date();
			final MPaymentTerm paymentTerm = new MPaymentTerm(getCtx(), getC_PaymentTerm_ID(), null);
			final Date dateCreated = getCreated();
			final Calendar calendarToExpire = Calendar.getInstance();
			calendarToExpire.setTime(dateCreated);
			calendarToExpire.add(Calendar.DAY_OF_MONTH, paymentTerm.getNetDays());
			final Calendar calendardocDate = Calendar.getInstance();
			calendardocDate.setTime(today);
			daysToExpire = (int) ((calendarToExpire.getTime().getTime() - calendardocDate.getTime().getTime()) / (1000 * 60 * 60 * 24));
		}
		return daysToExpire;
	}

	public String getDateToExpire() {
		if (dateToExpire == null && !isPaid()) {
			final MPaymentTerm paymentTerm = new MPaymentTerm(getCtx(), getC_PaymentTerm_ID(), null);
			final Calendar calendarToExpire = Calendar.getInstance();
			calendarToExpire.setTime(getCreated());
			calendarToExpire.add(Calendar.DAY_OF_MONTH, paymentTerm.getNetDays());
			final SimpleDateFormat format = Constantes.getShortDate(getCtx());
			dateToExpire = format.format(calendarToExpire.getTime());
		}
		return dateToExpire;
	}

	/**
	 * Obtiene el nombre del tipo de Documento
	 */
	public String getDocTypeName() {
		if (docTypeName == null) {
			docTypeName = getDocTypeName(getCtx(), getC_DocTypeTarget_ID());
//			final String filter = "AND C_DocType.C_DocType_Id IN('" + getC_DocTypeTarget_ID() + "') ";
//			try {
//				List<KeyNamePair> lst = MEXMEDocType.getTipoDoc(getCtx(), filter, null);
//				if (lst.isEmpty()) {
//					docTypeName = "";
//				} else {
//					docTypeName = lst.get(0).getName();
//				}
//			} catch (Exception e) {
//				log.log(Level.SEVERE, e.getMessage(), e);
//			}
		}
		return docTypeName;
	}
	
	//FIXME Revisar metodo
	public static String getDocTypeName(Properties ctx, int doctypeId) {
		String docTypeName = null;
		final String filter = "AND C_DocType.C_DocType_Id IN('" + doctypeId + "') ";
		try {
			List<KeyNamePair> lst = MEXMEDocType.getTipoDoc(ctx, filter, null);
			if (lst.isEmpty()) {
				docTypeName = "";
			} else {
				docTypeName = lst.get(0).getName();
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, e.getMessage(), e);
		}
		return docTypeName;
	}

	/** Nombre que lleva el xml a partir del objeto creado */
	public String getXMLName(){
		return getDocumentNo()+ "_" + Env.getAD_Org_ID(getCtx()) + "_" + getDocType().getDocBaseType();
	}
	

	/**
	 * Revisar si ya existe una factura con las mismas caracteristicas
	 * @param ctx
	 * @param documentNo
	 * @param docTypeID
	 * @param partnerId
	 * @param trxName
	 * @return
	 */
	public static boolean isUsed(Properties ctx, String documentNo,int docTypeID, int partnerId, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  c_invoice_id ");
		sql.append("FROM ");
		sql.append("  c_invoice ");
		sql.append("WHERE ");
		sql.append("  documentNo = ? AND ");
		sql.append("  c_doctype_id = ? AND ");
		sql.append("  c_bpartner_id = ? ");
		return DB.getSQLValue(trxName, sql.toString(), documentNo, docTypeID, partnerId) > 0;
	}
	
	/**
	 * Obtener el lo que falta de pagar
	 */
	public BigDecimal getInvoiceOpenAmt() {
		ResultSet rs = null;
		BigDecimal value;
		CallableStatement cstmt = null;
		try {
			cstmt = DB.prepareCall("SELECT invoiceopen(?, ?);", 0, get_TrxName());
			cstmt.setInt(1, getC_Invoice_ID());
			cstmt.setInt(2, 0);
			rs = cstmt.executeQuery();

			if (rs.next()) {
				value = rs.getBigDecimal(1);
			} else {
				value = BigDecimal.ZERO;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			value = BigDecimal.ZERO;
		} finally {
			DB.close(rs, cstmt);
		}
		return value;
	}
	
	/**
	 * Obtener el lo que falta de pagar
	 */
	public BigDecimal getInvoiceOpenAmount() {
		ResultSet rs = null;
		BigDecimal value;
		CallableStatement cstmt = null;
		try {
			cstmt = DB.prepareCall("SELECT invoiceopenInvoice(?, ?);", 0, get_TrxName());
			cstmt.setInt(1, getC_Invoice_ID());
			cstmt.setInt(2, 0);
			rs = cstmt.executeQuery();

			if (rs.next()) {
				value = rs.getBigDecimal(1);
			} else {
				value = BigDecimal.ZERO;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			value = BigDecimal.ZERO;
		} finally {
			DB.close(rs, cstmt);
		}
		return value;
	}
	
	public static List<CollectionReportBean> getVendorInvoice(Properties ctx, Timestamp startDate, Timestamp endDate, String trxName) {
		final List<CollectionReportBean> list = new ArrayList<CollectionReportBean>();
		final String AD_Language = Env.getAD_Language(ctx);
		final boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, Table_Name);
		
		final List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  iv.DocumentNo ");
		sql.append("  AS Numero_de_documento, ");
		if (!isBaseLanguage) {
			sql.append(" doctrl.Name   AS Tipo_de_documento,");
		} else {
			sql.append("  dt.Name ");
			sql.append("  AS Tipo_de_documento, ");
		}
		sql.append("  iv.DateInvoiced ");
		sql.append("  AS Fecha_del_documento, ");
		sql.append("  so.Value ||' '|| so.Name ");
		sql.append("  AS Proveedor_Cliente, ");
		sql.append("  iv.GrandTotal ");
		sql.append("  AS Monto ");
		sql.append("FROM ");
		sql.append("  C_Invoice iv ");
		sql.append("  INNER JOIN c_doctype dt ");
		sql.append("  ON dt.c_doctype_id = iv.c_doctype_id AND ");
		sql.append("  dt.DOCBASETYPE IN ('API', "); // Facturas Proveedor
		sql.append(" 'APC', "); // Notas de crédito proveedor
		sql.append(" 'APD' )"); // Notas de cargo
		if (!isBaseLanguage) {
			sql.append("INNER JOIN C_DocType_Trl doctrl ON doctrl.c_doctype_id = iv.c_doctype_id    AND doctrl.ad_Language = ?");
			params.add(AD_Language);
		}
		sql.append("  INNER JOIN c_Bpartner so ");
		sql.append("  ON so.c_Bpartner_id = iv.c_Bpartner_id ");
		sql.append("WHERE ");
		sql.append("  iv.IsActive = 'Y' ");
		sql.append("  and iv.DateInvoiced between ? and ? ");
		params.add(startDate);
		params.add(endDate);
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx,Constantes.SPACE, Table_Name, "iv"));
		sql.append("  Order by iv.DateInvoiced ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CollectionReportBean collectionReportBean = new CollectionReportBean();
				collectionReportBean.setDocumentNo(rs.getString("Numero_de_documento"));
				collectionReportBean.setDocumentType(rs.getString("Tipo_de_documento"));
				collectionReportBean.setDocumentDate(rs.getTimestamp("Fecha_del_documento"));
				collectionReportBean.setName(rs.getString("Proveedor_Cliente"));
				collectionReportBean.setAmount(rs.getBigDecimal("Monto"));

				list.add(collectionReportBean);
			}
			
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
			
		return list;
	}
	
	//avance card:1483 convertion amount
	private BigDecimal convertAmt =  Env.ZERO;
	public BigDecimal getConvertAmt() {
		return convertAmt;
	}

	public void setAmtConvert(BigDecimal convAmt) {
		convertAmt =  convAmt;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param cBPartnerId
	 * @param dateInvoice
	 * @param payRule
	 * @param currencyId
	 * @param trxName
	 * @return
	 */
	public static List<BeanPaySelect> getInvoiceCustomerSelection(Properties ctx, int cBPartnerId, Timestamp dateInvoice
			,BigDecimal rate, String payRule, int currencyId, String trxName){
		List<BeanPaySelect> list = new ArrayList<BeanPaySelect>();
		List<Object> params = new ArrayList<Object>();

		params.add(currencyId);
		params.add(dateInvoice==null?Env.getCurrentDate(): dateInvoice);
		params.add(rate);
		params.add(X_C_DocType.DOCBASETYPE_ARInvoice);
		
		params.add(currencyId==Env.getC_Currency_ID(ctx)?100:currencyId);
		params.add(Env.getC_Currency_ID(ctx));
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append(" i.C_Invoice_ID, i.DateInvoiced, i.C_Currency_ID, i.GrandTotal, i.DocumentNo as documentNo, i.Rate " );
		sql.append(" , bp.Name, i.description ");
		sql.append(" , c.ISO_Code as isoCode ");  
		sql.append(" , invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID) AS openInvoice     ");
		sql.append(" , currencyConvertInvoice(invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID)    ");
		sql.append(" , i.C_Currency_ID, ?, ?, i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID ");
		sql.append(" , ?  ) as openInvoiceConv ");//#1 #2 #2.1
		
		sql.append(" FROM c_invoice_v i ");
		sql.append(" INNER JOIN C_DocType    dt ON (i.C_DocType_ID    = dt.C_DocType_ID ");
		sql.append("                              AND dt.docbasetype  = ?               ");//#3
		sql.append("                              AND dt.DOCSUBTYPESO = 'SR'            ");// SOLO FACTURAS
		sql.append("                              AND dt.AD_Org_ID=i.AD_Org_ID)         ");
		sql.append(" INNER JOIN C_BPartner   bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)   ");
		sql.append(" INNER JOIN C_Currency    c ON (i.C_Currency_ID=c.C_Currency_ID)    ");
		sql.append(" INNER JOIN C_PaymentTerm p ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID) ");
		
		sql.append(" WHERE i.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "i"));
		sql.append(" AND i.IsSOTrx='Y' ");
		sql.append(" AND i.IsPaid='N' ");
		sql.append(" AND i.DocStatus IN ('CO','CL') ");
		sql.append(" AND invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID) > 0 " );  
		sql.append(" AND i.C_Currency_ID IN (?,?) ");
		if (cBPartnerId > 0){
			params.add(cBPartnerId);
			sql.append(" AND bp.c_bpartner_id = ? ");//#4
		}
		
		sql.append(" ORDER BY i.DateInvoiced asc");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				BeanPaySelect paySelect = new BeanPaySelect();
				paySelect.setInvoiceId(rs.getInt("c_invoice_id"));
				paySelect.setDateInvoiced(rs.getTimestamp("DateInvoiced"));
				paySelect.setClient(rs.getString("name"));
				
				paySelect.setCurrency(rs.getString("isoCode"));
				paySelect.setCurrencyId(rs.getInt("C_Currency_ID"));
				
				paySelect.setDocumentNo(rs.getString("documentNo"));
				
				paySelect.setTotal(rs.getBigDecimal("grandTotal"));
				paySelect.setOpenInvoice(rs.getBigDecimal("openInvoice"));
								
				paySelect.setConversionAmt(rs.getBigDecimal("openInvoiceConv"));
				paySelect.setBad(Env.ZERO);
				paySelect.setNotApplied(Env.ZERO);
				paySelect.setPayOff(false);
				paySelect.setApplied(Env.ZERO);
				paySelect.setDescription(rs.getString("description"));
				paySelect.setRateInvoice(rs.getBigDecimal("Rate"));
				
				list.add(paySelect);
			}
		} catch (Exception e) {
			sLog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	/**
	 * Get Allocated Imp in Invoice Currency
	 *
	 * @return pos/neg amount or null
	 */
	public BigDecimal getAllocatedImp() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT (i.GRANDTOTAL - invoiceOpen(i.C_Invoice_ID, i.C_InvoicePaySchedule_ID)) as Asignado ");
		sql.append(" FROM c_invoice_v i ");
		sql.append(" WHERE i.C_Invoice_ID= ?  ");
		
		BigDecimal retValue = DB.getSQLValueBD(get_TrxName(), sql.toString(), getC_Invoice_ID());
		
		return retValue;//58058.00
	}
	
} // MInvoice