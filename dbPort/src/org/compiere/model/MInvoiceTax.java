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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Invoice Tax Model
 *
 * @author Jorg Janke
 * @version $Id: MInvoiceTax.java,v 1.11 2006/08/11 02:26:22 mrojas Exp $
 */
public class MInvoiceTax extends X_C_InvoiceTax {
	/** serialVersionUID */
	private static final long serialVersionUID = 5082849263695678591L;
	/** Invoice */
	private MInvoice m_invoice = null;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MInvoiceTax.class);
	/** Tax */
	private MTax m_tax = null;
	/** Cached Precision */
	private Integer m_precision = null;
	/**************************************************************************
	 * Persistency Constructor
	 *
	 * @param ctx
	 *            context
	 * @param ignored
	 *            ignored
	 */
	public MInvoiceTax(Properties ctx, int ignored, String trxName) {
		super(ctx, 0, trxName);
		if (ignored != 0){
			throw new IllegalArgumentException("Multi-Key");
		}
		setTaxAmt(Env.ZERO);
		setTaxBaseAmt(Env.ZERO);
		setIsTaxIncluded(false);
	} // MInvoiceTax

	/**
	 * Load Constructor. Set Precision and TaxIncluded for tax calculations!
	 *
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 */
	public MInvoiceTax(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MInvoiceTax

	/**
	 * Get Tax Line for Invoice Line
	 *
	 * @param line
	 *            invoice line
	 * @param precision
	 *            currency precision
	 * @param oldTax
	 *            if true old tax is returned
	 * @param trxName
	 *            transaction name
	 * @return existing or new tax
	 */
	public static MInvoiceTax get(final MInvoiceLine line, final int precision,
			final boolean oldTax, final String trxName) {
		MInvoiceTax retValue = null;
		if (line == null || line.getC_Invoice_ID() == 0){
			return null;
		}

		// Tasa de impuesto
		int cTaxID = line.getC_Tax_ID();

		// Sí ha cambiado la tasa de impuesto se elije al tasa anterior
		if (oldTax && line.is_ValueChanged("C_Tax_ID")) {
			Object old = line.get_ValueOld("C_Tax_ID");
			if (old == null){
				return null;
			}
			cTaxID = ((Integer) old).intValue();
		}

		// si esta vacio o es cero regresa vacio
		if (cTaxID == 0) {
			s_log.warning("get C_Tax_ID=0");
			return null;
		}

		// Busca la linea creada con la tasa de imp de la linea
		String sql = "SELECT * FROM C_InvoiceTax WHERE C_Invoice_ID=? AND C_Tax_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, line.getC_Invoice_ID());
			pstmt.setInt(2, cTaxID);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				retValue = new MInvoiceTax(line.getCtx(), rset, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rset, pstmt);
		}

		if (retValue != null) {
			retValue.set_TrxName(trxName);
			retValue.setPrecision(precision);
			s_log.fine("get (old=" + oldTax + ") " + retValue);
			return retValue;
		}

		// Create New
		retValue = new MInvoiceTax(line.getCtx(), 0, trxName);
		retValue.set_TrxName(trxName);
		retValue.setClientOrg(line);
		retValue.setC_Invoice_ID(line.getC_Invoice_ID());
		retValue.setC_Tax_ID(line.getC_Tax_ID());
		retValue.setPrecision(precision);
		retValue.setIsTaxIncluded(line.isTaxIncluded());

		s_log.fine("get (new) " + retValue);
		return retValue;
	} // get

	/**
	 * Get Tax Line for Invoice Line
	 *
	 * @param line
	 *            invoice line
	 * @param precision
	 *            currency precision
	 * @param oldTax
	 *            if true old tax is returned
	 * @param trxName
	 *            transaction name
	 * @return existing or new tax
	 */
	public static MInvoiceTax get(final MInvoiceLine line, final int cTaxID , final int precision,
			final boolean oldTax, final String trxName) {
		MInvoiceTax retValue = null;
		if (line == null || line.getC_Invoice_ID() == 0){
			return null;
		}

		// si esta vacio o es cero regresa vacio
		if (cTaxID == 0) {
			s_log.warning("get C_Tax_ID=0");
			return null;
		}

		// Busca la linea creada con la tasa de imp de la linea
		String sql = "SELECT * FROM C_InvoiceTax WHERE C_Invoice_ID=? AND C_Tax_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, line.getC_Invoice_ID());
			pstmt.setInt(2, cTaxID);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				retValue = new MInvoiceTax(line.getCtx(), rset, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rset, pstmt);
		}

		if (retValue != null) {
			retValue.set_TrxName(trxName);
			retValue.setPrecision(precision);
			s_log.fine("get (old=" + oldTax + ") " + retValue);
			return retValue;
		}

		// Create New
		retValue = new MInvoiceTax(line.getCtx(), 0, trxName);
		retValue.set_TrxName(trxName);
		retValue.setClientOrg(line);
		retValue.setC_Invoice_ID(line.getC_Invoice_ID());
		retValue.setC_Tax_ID(cTaxID);
		retValue.setPrecision(precision);
		retValue.setIsTaxIncluded(line.isTaxIncluded());

		s_log.fine("get (new) " + retValue);
		return retValue;
	} // get

	/**
	 * Get Precision
	 *
	 * @return Returns the precision or 2
	 */
	private int getPrecision() {
		if (m_precision == null){
			return 2;
		}
		return m_precision.intValue();
	} // getPrecision

	/**
	 * Set Precision
	 *
	 * @param precision
	 *            The precision to set.
	 */
	protected void setPrecision(final int precision) {
		m_precision = new Integer(precision);
	} // setPrecision

	/**
	 * Get Tax
	 *
	 * @return tax
	 */
	public MTax getTax() {
		if (m_tax == null){
			m_tax = MTax.get(getCtx(), getC_Tax_ID());
		}
		return m_tax;
	} // getTax

	/**************************************************************************
	 * Calculate/Set Tax Base Amt from Invoice Lines
	 */

	public boolean calculateTaxFromLines(boolean processCharge) {
		BigDecimal taxBaseAmt = Env.ZERO; // Expert:hassan - le agregamos la precision de decimales
		BigDecimal taxAmt = Env.ZERO; // Expert:hassan - le agregamos la precision de decimales

		// Expert:Hassan - Inicio - Servicios de Coaseguro y Deducible. no se
		// recalcula el Tax.
		// IMPORTANTE: Los productos de Coaseguro y Deducible deben pertenecer a
		// una categoria de impuestos exclusiva para cada uno.
		// esto para que no se entre mesclen con otros impuestos.
		boolean wasFromCoaergOrDedu = false; // Indica si la las lineas incluian
												// Coaseguro o Deducible.
		int coaseguro_ID = 0;
		int deducible_ID = 0;
		int coaseguroMed_ID = 0;
		int copago_ID = 0;
		final MConfigPre configPre = MConfigPre.get(getCtx(), get_TrxName());
		if (configPre != null) {
			coaseguro_ID = configPre.getCoaseguro_ID();
			deducible_ID = configPre.getDeducible_ID();
			coaseguroMed_ID = configPre.getCoaseguroMed_ID();
			copago_ID = configPre.getCoPago_ID();
		}
		// Expert:Hassan - Fin

		//
		final boolean documentLevel = getTax().isDocumentLevel();
		final MTax tax = getTax();
		//
		String sql = " SELECT * FROM C_InvoiceLine "
				+ " WHERE C_Invoice_ID=? AND C_Tax_ID=? ";// Expert:Hassan

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_Invoice_ID());
			pstmt.setInt(2, getC_Tax_ID());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				wasFromCoaergOrDedu = false;
				MInvoiceLine mInvoiceLine = new MInvoiceLine(getCtx(), rset, get_TrxName());

//				LineNetAmt, COALESCE(TaxAmt,0), M_Product_ID
				BigDecimal baseAmt = mInvoiceLine.getLineNetAmt();//rset.getBigDecimal(1);
				BigDecimal amt = mInvoiceLine.getTaxAmt();//rset.getBigDecimal(2);

				// Expert:Hassan - Obtenemos el producto.
				int m_product_ID = mInvoiceLine.getM_Product_ID();//rset.getInt(3);
				if (m_product_ID == coaseguro_ID
						|| m_product_ID == deducible_ID
						|| m_product_ID == coaseguroMed_ID
						|| m_product_ID == copago_ID
						|| mInvoiceLine.getC_Charge_ID()>0) {
					wasFromCoaergOrDedu = true;
				}
				// Expert:Hassan - Fin.
				if (amt == null){
					amt = Env.ZERO;
				}
				//

				if(mInvoiceLine.getmInvoice()!=null && !mInvoiceLine.getmInvoice().isSOTrx()){
					wasFromCoaergOrDedu = false;
				}

				if ((!documentLevel && amt.signum() == 0 && !wasFromCoaergOrDedu) || processCharge){
					// Expert:Hassan calculate line tax.
					// Pero no para el Coaseguro ni Deducible.
					amt = tax.calculateTax(baseAmt, isTaxIncluded(), getPrecision());
				}

				if(!wasFromCoaergOrDedu || processCharge){
					taxBaseAmt = taxBaseAmt.add(baseAmt);
					taxAmt = taxAmt.add(amt);
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "setTaxBaseAmt", e);
			taxBaseAmt = null;
		} finally {
			DB.close(rset, pstmt);
		}


		if (taxBaseAmt == null){
			return false;
		}

		// Calculate Tax
		if (taxAmt.signum() == 0 && !wasFromCoaergOrDedu){
			// Expert:Hassan - Que no provenga de Coaseguro o Deducible.
			taxAmt = tax.calculateTax(taxBaseAmt, isTaxIncluded(),
					getPrecision());
		}

		setTaxAmt(taxAmt.setScale(getPrecision(),
				BigDecimal.ROUND_HALF_UP));//

		// Set Base
		if (isTaxIncluded()){
			setTaxBaseAmt(
					(taxBaseAmt.subtract(taxAmt)).setScale(
							getPrecision(),
							BigDecimal.ROUND_HALF_UP
					)
			);
		} else {
			setTaxBaseAmt(
					taxBaseAmt.setScale(getPrecision(), BigDecimal.ROUND_HALF_UP)
			);
		}
		return true;
	}


	public boolean calculateTaxFromLines() {
		return calculateTaxFromLines(false);
	} // calculateTaxFromLines

	/**
	 * String Representation
	 *
	 * @return info
	 */
	public String toString() {
		final StringBuffer sb = new StringBuffer("MInvoiceTax[");
		sb.append("C_Invoice_ID=").append(getC_Invoice_ID())
				.append(",C_Tax_ID=").append(getC_Tax_ID()).append(", Base=")
				.append(getTaxBaseAmt()).append(",Tax=").append(getTaxAmt())
				.append("]");
		return sb.toString();
	} // toString

	/**
	 * Get Invoice
	 *
	 * @return invoice
	 */
	public MInvoice getInvoice() {
		if (m_invoice == null && getC_Invoice_ID() != 0){
			m_invoice = new MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
		}
		return m_invoice;
	} // getInvoice

	/**
	 * Tasas de impuesto por factura a partir de un cargo
	 * de la cuenta paciente
	 * @param ctx Conexto
	 * @param ctaPacDetId Cargo de la cuenta paciente
	 * @param trxName Nombre de transaccion
	 * @return List<MInvoiceTax>
	 */
	public static List<MInvoiceTax> tasasImpFactura(final Properties ctx, final int ctaPacDetId, final String trxName){

		final List<MInvoiceTax> lst = new ArrayList<MInvoiceTax>();
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT it.* ")
		.append(" FROM  C_InvoiceTax it ")
		.append(" INNER JOIN c_invoice      iv ON it.c_invoice_Id = iv.c_invoice_Id ")
		.append(" INNER JOIN EXME_CtaPacExt ce ON iv.c_invoice_Id = ce.c_invoice_Id ")// no se hace C_invoice.EXME_CtaPacExt_Id por que la relación es de unoa mucho
		.append(" INNER JOIN EXME_CtaPacDet cp ON ce.EXME_CtaPacExt_ID = cp.EXME_CtaPacExt_Id ")
		.append(" INNER JOIN EXME_CtaPacDet ot ON cp.EXME_CtaPacDet_ID = ot.Ref_CtaPacDet_Id  ")
		.append(" WHERE it.isActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_InvoiceTax.Table_Name, "it"))
		.append(" AND   ot.EXME_CtaPacDet_ID = ? ");

		//
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacDetId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lst.add(new MInvoiceTax(ctx, rset, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "tasasImpFactura", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return lst;
	}

	/**
	 * Tasas de impuesto por factura a partir de un cargo
	 * de la cuenta paciente
	 * @param ctx Conexto
	 * @param ctaPacDetId Cargo de la cuenta paciente
	 * @param trxName Nombre de transaccion
	 * @return List<MInvoiceTax>
	 */
	public static BigDecimal vtaFactura(final Properties ctx, final int ctaPacDetId, final String trxName){
		final MConfigPre configPre = MConfigPre.get(ctx,trxName);
		BigDecimal vta = Env.ZERO;
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT SUM(it.LineNetAmt) as vta ")
		.append(" FROM  C_Invoice it ")
		.append(" INNER JOIN EXME_CtaPacExt ce ON it.c_invoice_Id = ce.c_invoice_Id ")// no se hace C_invoice.EXME_CtaPacExt_Id por que la relación es de unoa mucho
		.append(" INNER JOIN EXME_CtaPacDet cp ON ce.EXME_CtaPacExt_ID = cp.EXME_CtaPacExt_Id ")
		.append(" INNER JOIN EXME_CtaPacDet ot ON cp.EXME_CtaPacDet_ID = ot.Ref_CtaPacDet_Id  ")
		.append(" WHERE it.isActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Invoice.Table_Name, "it"))
		.append(" AND   ot.EXME_CtaPacDet_ID = ? ")
		.append(" AND   it.M_Product_ID >0       ")
		.append(" AND   it.M_Product_ID NOT in ( ")
		.append(configPre.getCoaseguro_ID()).append(", ")
		.append(configPre.getDeducible_ID()).append(", ")
		.append(configPre.getCoaseguroMed_ID()).append(", ")
		.append(configPre.getCoPago_ID()).append(") ");

		//
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacDetId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				vta =  rset.getBigDecimal("vta");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "tasasImpFactura", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return vta;
	}
	/**
	 * Tasas de impuesto por factura a partir de un cargo
	 * de la cuenta paciente
	 * @param ctx Conexto
	 * @param ctaPacDetId Cargo de la cuenta paciente
	 * @param trxName Nombre de transaccion
	 * @return List<MInvoiceTax>
	 */
	public static List<MInvoiceTax> tasasImpCargos(final Properties ctx, final int ctaPacDetId, final String trxName){
		final MConfigPre configPre = MConfigPre.get(ctx, trxName);
		final List<MInvoiceTax> lst = new ArrayList<MInvoiceTax>();
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT cp.C_Tax_ID, SUM(cp.TaxAmt) as taxAmts, SUM(cp.LineNetAmt) as lineNetAmts ")
		.append(" FROM  EXME_CtaPacDet cp  ")
		.append(" WHERE cp.isActive = 'Y'  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_CtaPacDet.Table_Name, "cp"))
		.append(" AND  cp.EXME_CtaPacExt_ID IN (       ")
		.append("      SELECT cph.EXME_CtaPacExt_ID    ")
		.append("      FROM   EXME_CtaPacDet cph       ")
		.append("      INNER  JOIN EXME_CtaPacDet ot ON cph.EXME_CtaPacDet_ID = ot.Ref_CtaPacDet_Id ")
		.append("      WHERE  ot.EXME_CtaPacDet_ID = ? ")
		.append(" ) ")
		.append(" AND  cp.M_Product_ID > 0             ")// sin descuento
		.append(" AND  cp.M_Product_ID NOT IN (        ")// sin CCCmD
				.append(configPre.getCoaseguro_ID()).append(", ")
				.append(configPre.getDeducible_ID()).append(", ")
				.append(configPre.getCoaseguroMed_ID()).append(", ")
				.append(configPre.getCoPago_ID()).append(") ")
		.append(" GROUP BY cp.C_Tax_ID ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacDetId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				MInvoiceTax invTax = new MInvoiceTax(ctx, 0, trxName);
				invTax.setTaxAmt(rset.getBigDecimal("taxAmts"));
				invTax.setTaxBaseAmt(rset.getBigDecimal("lineNetAmts"));
				invTax.setC_Tax_ID(rset.getInt("C_Tax_ID"));
				lst.add(invTax);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "tasasImpCargos", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return lst;
	}

	/**
	 * Tasas de impuesto por factura a partir de un cargo
	 * de la cuenta paciente
	 * @param ctx Conexto
	 * @param ctaPacDetId Cargo de la cuenta paciente
	 * @param trxName Nombre de transaccion
	 * @return List<MCtaPacDet>
	 */
	public static List<MCtaPacDet> tasasImpCargosCCCmD(final Properties ctx, final int ctaPacDetId, final String trxName){
		final MConfigPre configPre = MConfigPre.get(ctx, trxName);
		final List<MCtaPacDet> lst = new ArrayList<MCtaPacDet>();
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT cp.* ")
		.append(" FROM  EXME_CtaPacDet cp  ")
		.append(" WHERE cp.isActive = 'Y'  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_CtaPacDet.Table_Name, "cp"))
		.append(" AND  cp.EXME_CtaPacExt_ID IN (       ")
		.append("      SELECT cph.EXME_CtaPacExt_ID    ")
		.append("      FROM   EXME_CtaPacDet cph       ")
		.append("      INNER  JOIN EXME_CtaPacDet ot ON cph.EXME_CtaPacDet_ID = ot.Ref_CtaPacDet_Id ")
		.append("      WHERE  ot.EXME_CtaPacDet_ID = ? ")
		.append(" ) ")
		.append(" AND ( cp.C_Charge_ID  > 0             ")// con descuento
		.append(" OR   cp.M_Product_ID IN (            ")// con CCCmD
				.append(configPre.getCoaseguro_ID()).append(", ")
				.append(configPre.getDeducible_ID()).append(", ")
				.append(configPre.getCoaseguroMed_ID()).append(", ")
				.append(configPre.getCoPago_ID()).append(") ) ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacDetId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				MCtaPacDet invTax = new MCtaPacDet(ctx, rset, trxName);
				lst.add(invTax);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "tasasImpCargosCCCmD", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return lst;
	}

	/**
	 * Tasas de impuesto por factura a partir de un cargo
	 * de la cuenta paciente
	 * @param ctx Conexto
	 * @param ctaPacDetId Cargo de la cuenta paciente
	 * @param trxName Nombre de transaccion
	 * @return List<MInvoiceTax>
	 */
	public static BigDecimal vtaCargos(final Properties ctx, final int ctaPacDetId, final String trxName){

		BigDecimal vta = Env.ZERO;
		final MConfigPre configPre = MConfigPre.get(ctx,trxName);
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT SUM(cp.LineNetAmt) as vta     ")
		.append(" FROM  EXME_CtaPacDet cp              ")
		.append(" WHERE cp.isActive = 'Y'              ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_CtaPacDet.Table_Name, "cp"))
		.append(" AND  cp.EXME_CtaPacExt_ID IN (       ")
		.append("      SELECT cph.EXME_CtaPacExt_ID    ")
		.append("      FROM   EXME_CtaPacDet cph       ")
		.append("      INNER  JOIN EXME_CtaPacDet ot ON cph.EXME_CtaPacDet_ID = ot.Ref_CtaPacDet_Id ")
		.append("      WHERE  ot.EXME_CtaPacDet_ID = ? ")
		.append(" )                                    ")
		.append(" AND   cp.M_Product_ID >0             ")
		.append(" AND   cp.M_Product_ID NOT in (       ")
		.append(configPre.getCoaseguro_ID()).append(", ")
		.append(configPre.getDeducible_ID()).append(", ")
		.append(configPre.getCoaseguroMed_ID()).append(", ")
		.append(configPre.getCoPago_ID()).append(") ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacDetId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				vta = rset.getBigDecimal("vta");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "tasasImpFactura", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return vta;
	}

	/**
	 * Get Taxes
	 *
	 * @param requery
	 *            requery
	 * @return array of taxes
	 */
	public static List<MInvoiceTax> getTaxes(final Properties ctx, final String where, final int invoiceId, final String trxName) {
		String sql = "SELECT * FROM C_InvoiceTax WHERE IsActive = 'Y' AND C_Invoice_ID=? "+where;

		final ArrayList<MInvoiceTax> list = new ArrayList<MInvoiceTax>();
		PreparedStatement pstmt = null;
		ResultSet rsset = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, invoiceId);
			rsset = pstmt.executeQuery();
			while (rsset.next()) {
				list.add(new MInvoiceTax(ctx, rsset, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getTaxes", e);
		} finally {
			DB.close(rsset, pstmt);
		}
		return list;
	} // getTaxes



	/**
	 * Get Tax Line for Invoice Line
	 *
	 * @param line
	 *            invoice line
	 * @param precision
	 *            currency precision
	 * @param oldTax
	 *            if true old tax is returned
	 * @param trxName
	 *            transaction name
	 * @return existing or new tax
	 */
	public static MInvoiceTax getInvoiceTax(final Properties ctx, final int cTaxID , final int cInvoiceID,
			final String trxName) {
		MInvoiceTax retValue = null;

		// Busca la linea creada con la tasa de imp de la linea
		String sql = "SELECT * FROM C_InvoiceTax WHERE IsActive = 'Y' AND C_Invoice_ID=? AND C_Tax_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, cInvoiceID);
			pstmt.setInt(2, cTaxID);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				retValue = new MInvoiceTax(ctx, rset, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return retValue;
	} // get
} // MInvoiceTax
