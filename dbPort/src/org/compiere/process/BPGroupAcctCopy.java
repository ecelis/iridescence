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
package org.compiere.process;

import java.math.*;
import java.util.Properties;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 * Copy BP Group default Accounts
 *
 * @author Jorg Janke
 * @version $Id: BPGroupAcctCopy.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class BPGroupAcctCopy extends SvrProcess {
	/** BP Group */
	private int p_C_BP_Group_ID = 0;
	/** Acct Schema */
	private int p_C_AcctSchema_ID = 0;
	/** contexto */
	/** Copy & Overwrite */
	private boolean p_CopyOverwriteAcct = false;
	/** Copy Of */
	private boolean CopyOf = false;

	private Properties ctx = null;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ctx = getCtx();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_BP_Group_ID")) {
				p_C_BP_Group_ID = para[i].getParameterAsInt();
			} else if (name.equals("C_AcctSchema_ID")) {
				p_C_AcctSchema_ID = para[i].getParameterAsInt();
			} else if ("CopyOverwriteAcct".equals(name)) {
				p_CopyOverwriteAcct = "Y".equals(para[i].getParameter());
			} else if ("CopyOf".equals(name)) {
				CopyOf = "Y".equals(para[i].getParameter());
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}
	} // prepare

	public String doIt(Properties pctx, int pCBPGroupID, int pCAcctSchemaID) throws Exception {
		p_C_BP_Group_ID = pCBPGroupID;
		p_C_AcctSchema_ID = pCAcctSchemaID;
		ctx = pctx;
		return doIt();
	}

	/**
	 * Process
	 *
	 * @return message
	 * @throws Exception
	 */
	protected String doIt() throws Exception {
		log.info("C_AcctSchema_ID=" + p_C_AcctSchema_ID);
		if (p_C_AcctSchema_ID == 0)
			throw new CompiereSystemError("C_AcctSchema_ID=0");
		MAcctSchema as = MAcctSchema.get(ctx, p_C_AcctSchema_ID);
		if (as.get_ID() == 0)
			throw new CompiereSystemError("Not Found - C_AcctSchema_ID=" + p_C_AcctSchema_ID);
		//
		String sql = null;
		int updated = 0;
		int created = 0;
		int updatedTotal = 0;
		int createdTotal = 0;

		// Update all accounts
		if (p_CopyOverwriteAcct) {
			// Update existing Customers
			sql = "UPDATE C_BP_Customer_Acct ca "
					+ "SET (C_Receivable_Acct,C_Receivable_Services_Acct,C_PrePayment_Acct,C_ReceivableFgn_Acct,C_PrepaymentFgn_Acct)="
					+ " (SELECT C_Receivable_Acct,C_Receivable_Services_Acct,C_PrePayment_Acct,C_ReceivableFgn_Acct,C_PrepaymentFgn_Acct "
					+ " FROM C_BP_Group_Acct" + " WHERE C_BP_Group_ID=" + p_C_BP_Group_ID + " AND C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + "), Updated=SysDate, UpdatedBy=0 " + "WHERE ca.C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + " AND EXISTS (SELECT * FROM C_BPartner p "
					+ "WHERE p.C_BPartner_ID=ca.C_BPartner_ID" + " AND p.C_BP_Group_ID=" + p_C_BP_Group_ID + ")";
			updated = DB.executeUpdate(sql, get_TrxName());
			//addLog(0, null, new BigDecimal(updated), "@Updated@ @C_BPartner_ID@ @IsCustomer@");
			updatedTotal += updated;
		}
		// Update only Foreign clients account (C_ReceivableFgn_Acct)
		if (CopyOf) {
			sql = "UPDATE C_BP_Customer_Acct ca " + "SET (C_ReceivableFgn_Acct,C_PrepaymentFgn_Acct)=" + " (SELECT C_ReceivableFgn_Acct,C_PrepaymentFgn_Acct "
					+ " FROM C_BP_Group_Acct" + " WHERE C_BP_Group_ID=" + p_C_BP_Group_ID + " AND C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + "), Updated=SysDate, UpdatedBy=0 " + "WHERE ca.C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + " AND EXISTS (SELECT * FROM C_BPartner p "
					+ "WHERE p.C_BPartner_ID=ca.C_BPartner_ID" + " AND p.C_BP_Group_ID=" + p_C_BP_Group_ID + ")";
			updated = DB.executeUpdate(sql, get_TrxName());
			
			updatedTotal += updated;
		}
		addLog(0, null, new BigDecimal(updated), "@Updated@ @C_BPartner_ID@ @IsCustomer@");

		// Insert new Customer
		sql = "INSERT INTO C_BP_Customer_Acct "
				+ "(C_BPartner_ID, C_AcctSchema_ID,"
				+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
				+ " C_Receivable_Acct, C_Receivable_Services_Acct, C_PrePayment_Acct, C_ReceivableFgn_Acct,C_PrepaymentFgn_Acct) "
				+ "SELECT p.C_BPartner_ID, acct.C_AcctSchema_ID,"
				// +
				// " p.AD_Client_ID, p.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
				+ " acct.AD_Client_ID, acct.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
				+ " acct.C_Receivable_Acct, acct.C_Receivable_Services_Acct, acct.C_PrePayment_Acct, acct.C_ReceivableFgn_Acct, acct.C_PrepaymentFgn_Acct "
				+ "FROM C_BPartner p" + " INNER JOIN C_BP_Group_Acct acct ON (acct.C_BP_Group_ID=p.C_BP_Group_ID)"
				+ "WHERE acct.C_AcctSchema_ID="
				+ p_C_AcctSchema_ID // #
				+ " AND p.C_BP_Group_ID=" + p_C_BP_Group_ID + " AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct ca "
				+ "WHERE ca.C_BPartner_ID=p.C_BPartner_ID" + " AND ca.C_AcctSchema_ID=acct.C_AcctSchema_ID)";
		created = DB.executeUpdate(sql, get_TrxName());
		addLog(0, null, new BigDecimal(created), "@Created@ @C_BPartner_ID@ @IsCustomer@");
		createdTotal += created;

		// Update all accounts
		if (p_CopyOverwriteAcct) {

			// Update existing Vendors
			sql = "UPDATE C_BP_Vendor_Acct va "
					+ "SET (V_Liability_Acct,V_Liability_Services_Acct,V_PrePayment_Acct,V_LiabilityFgn_Acct,V_PrepaymentFgn_Acct)="
					+ " (SELECT V_Liability_Acct,V_Liability_Services_Acct,V_PrePayment_Acct,V_LiabilityFgn_Acct,V_PrepaymentFgn_Acct "
					+ " FROM C_BP_Group_Acct" + " WHERE C_BP_Group_ID=" + p_C_BP_Group_ID + " AND C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + "), Updated=SysDate, UpdatedBy=0 " + "WHERE va.C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + " AND EXISTS (SELECT * FROM C_BPartner p "
					+ "WHERE p.C_BPartner_ID=va.C_BPartner_ID" + " AND p.C_BP_Group_ID=" + p_C_BP_Group_ID + ")";
			updated = DB.executeUpdate(sql, get_TrxName());
			addLog(0, null, new BigDecimal(updated), "@Updated@ @C_BPartner_ID@ @IsVendor@");
			updatedTotal += updated;
		}

		// Update only Foreign Vendors account (V_LiabilityFgn_Acct)
		if (CopyOf) {

			// Update existing Vendors
			sql = "UPDATE C_BP_Vendor_Acct va " + "SET (V_LiabilityFgn_Acct,V_PrepaymentFgn_Acct)=" + " (SELECT V_LiabilityFgn_Acct,V_PrepaymentFgn_Acct "
					+ " FROM C_BP_Group_Acct" + " WHERE C_BP_Group_ID=" + p_C_BP_Group_ID + " AND C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + "), Updated=SysDate, UpdatedBy=0 " + "WHERE va.C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + " AND EXISTS (SELECT * FROM C_BPartner p "
					+ "WHERE p.C_BPartner_ID=va.C_BPartner_ID" + " AND p.C_BP_Group_ID=" + p_C_BP_Group_ID + ")";
			updated = DB.executeUpdate(sql, get_TrxName());
			addLog(0, null, new BigDecimal(updated), "@Updated@ @C_BPartner_ID@ @IsVendor@");
			updatedTotal += updated;
		}

		// Insert new Vendors
		sql = "INSERT INTO C_BP_Vendor_Acct "
				+ "(C_BPartner_ID, C_AcctSchema_ID,"
				+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
				+ " V_Liability_Acct, V_Liability_Services_Acct, V_PrePayment_Acct, V_LiabilityFgn_Acct,V_PrepaymentFgn_Acct) "
				+ "SELECT p.C_BPartner_ID, acct.C_AcctSchema_ID,"
				// +
				// " p.AD_Client_ID, p.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
				+ " acct.AD_Client_ID, acct.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
				+ " acct.V_Liability_Acct, acct.V_Liability_Services_Acct, acct.V_PrePayment_Acct, acct.V_LiabilityFgn_Acct, acct.V_PrepaymentFgn_Acct "
				+ "FROM C_BPartner p" + " INNER JOIN C_BP_Group_Acct acct ON (acct.C_BP_Group_ID=p.C_BP_Group_ID)"
				+ "WHERE acct.C_AcctSchema_ID="
				+ p_C_AcctSchema_ID // #
				+ " AND p.C_BP_Group_ID=" + p_C_BP_Group_ID + " AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct va "
				+ "WHERE va.C_BPartner_ID=p.C_BPartner_ID AND va.C_AcctSchema_ID=acct.C_AcctSchema_ID)";
		created = DB.executeUpdate(sql, get_TrxName());
		addLog(0, null, new BigDecimal(created), "@Created@ @C_BPartner_ID@ @IsVendor@");
		createdTotal += created;
		
		// Update all accounts
		if (p_CopyOverwriteAcct) {

			// Update existing Creditor
			sql = "UPDATE C_BP_Creditor_Acct cr "
					+ "SET (A_Creditors_Acct,A_CreditorsFgn_Acct)="
					+ " (SELECT A_Creditors_Acct,A_CreditorsFgn_Acct "
					+ " FROM C_BP_Group_Acct" + " WHERE C_BP_Group_ID=" + p_C_BP_Group_ID + " AND C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + "), Updated=SysDate, UpdatedBy=0 " + "WHERE cr.C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + " AND EXISTS (SELECT * FROM C_BPartner p "
					+ "WHERE p.C_BPartner_ID=cr.C_BPartner_ID" + " AND p.C_BP_Group_ID=" + p_C_BP_Group_ID + ")";
			
			updated = DB.executeUpdate(sql, get_TrxName());
			addLog(0, null, new BigDecimal(updated), "@Updated@ @C_BPartner_ID@ @IsCreditor@");
			
			updatedTotal += updated;
		}
		
		// Update only new accounts
		if (CopyOf) {
			// Update existing Creditor
			sql = "UPDATE C_BP_Creditor_Acct cr "
					+ "SET (A_Creditors_Acct,A_CreditorsFgn_Acct)="
					+ " (SELECT A_Creditors_Acct,A_CreditorsFgn_Acct "
					+ " FROM C_BP_Group_Acct" + " WHERE C_BP_Group_ID=" + p_C_BP_Group_ID + " AND C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + "), Updated=SysDate, UpdatedBy=0 " + "WHERE cr.C_AcctSchema_ID="
					+ p_C_AcctSchema_ID + " AND EXISTS (SELECT * FROM C_BPartner p "
					+ "WHERE p.C_BPartner_ID=cr.C_BPartner_ID" + " AND p.C_BP_Group_ID=" + p_C_BP_Group_ID + ")";
			
			updated = DB.executeUpdate(sql, get_TrxName());
			addLog(0, null, new BigDecimal(updated), "@Updated@ @C_BPartner_ID@ @IsCreditor@");
			
			updatedTotal += updated;	
		}
		
		// Insert new Creditor
		sql = "INSERT INTO C_BP_Creditor_Acct "
					+ "(C_BPartner_ID, C_AcctSchema_ID,"
					+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
					+ " A_Creditors_Acct,A_CreditorsFgn_Acct) "
					+ "SELECT p.C_BPartner_ID, acct.C_AcctSchema_ID,"
					+ " acct.AD_Client_ID, acct.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
					+ " acct.A_Creditors_Acct, acct.A_CreditorsFgn_Acct "
					+ "FROM C_BPartner p" + " INNER JOIN C_BP_Group_Acct acct ON (acct.C_BP_Group_ID=p.C_BP_Group_ID)"
					+ "WHERE acct.C_AcctSchema_ID="
					+ p_C_AcctSchema_ID // #
					+ " AND p.C_BP_Group_ID=" + p_C_BP_Group_ID + " AND NOT EXISTS (SELECT * FROM C_BP_Creditor_Acct cr "
					+ "WHERE cr.C_BPartner_ID=p.C_BPartner_ID AND cr.C_AcctSchema_ID=acct.C_AcctSchema_ID)";
		
			created = DB.executeUpdate(sql, get_TrxName());
			addLog(0, null, new BigDecimal(created), "@Created@ @C_BPartner_ID@ @IsVendor@");
			createdTotal += created;

		return "@Created@=" + createdTotal + ", @Updated@=" + updatedTotal;
		
		
	} // doIt
	
} // BPGroupAcctCopy
