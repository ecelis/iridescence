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

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Validacion;

/**
 *	Business Partner Model
 *
 *  @author Jorg Janke
 *  @version $Id: MBPartner.java,v 1.5 2006/11/02 21:17:08 taniap Exp $
 */
public class MBPartner extends X_C_BPartner {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5143684428229756247L;
	/** Users							*/
	private MUser[]					m_contacts = null;
	/** Addressed						*/
	private MBPartnerLocation[]		m_locations = null;
	/** BP Bank Accounts				*/
	private MBPBankAccount[]		m_accounts = null;
	/** Prim Address					*/
	private Integer					m_primaryC_BPartner_Location_ID = null;
	/** Prim User						*/
	private Integer					m_primaryAD_User_ID = null;
	/** Credit Limit recently calcualted		*/
	private boolean 				m_TotalOpenBalanceSet = false;
	/** BP Group						*/
	private MBPGroup				m_group = null;
	/** B Partner Cte 					*/
	private MBPartnerCte 			m_partnerCte = null;
	
	/**
	 * 	Get Empty Template Business Partner
	 * 	@param ctx context
	 * 	@param AD_Client_ID client
	 * 	@return Template Business Partner or null
	 */
	public static MBPartner getTemplate(Properties ctx, int AD_Client_ID) {
		MBPartner template = getBPartnerCashTrx(ctx, AD_Client_ID);
		if (template == null)
			template = new MBPartner(ctx, 0, null);
		// Reset
		if (template != null) {
			template.set_ValueNoCheck(COLUMNNAME_C_BPartner_ID, new Integer(0));
			template.setValue("");
			template.setName("");
			template.setName2(null);
			template.setDUNS("");
			template.setFirstSale(null);
			//
			template.setSO_CreditLimit(Env.ZERO);
			template.setSO_CreditUsed(Env.ZERO);
			template.setTotalOpenBalance(Env.ZERO);
			// s_template.setRating(null);
			//
			template.setActualLifeTimeValue(Env.ZERO);
			template.setPotentialLifeTimeValue(Env.ZERO);
			template.setAcqusitionCost(Env.ZERO);
			template.setShareOfCustomer(0);
			template.setSalesVolume(0);
		}
		return template;
	} //	getTemplate

	/**
	 * 	Get Cash Trx Business Partner
	 * 	@param ctx context
	 * 	@param AD_Client_ID client
	 * 	@return Cash Trx Business Partner or null
	 */
	public static MBPartner getBPartnerCashTrx(Properties ctx, int AD_Client_ID) {
		MBPartner retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT C_BPartner.* FROM C_BPartner ");
		sql.append("INNER JOIN AD_ClientInfo ON (C_BPartner.C_BPartner_ID = AD_ClientInfo.C_BPartnerCashTrx_ID ");
		sql.append(" AND AD_ClientInfo.AD_Client_ID = ? )");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MBPartner(ctx, rs, null);
			else
				s_log.log(Level.SEVERE, "Not found for AD_Client_ID=" + AD_Client_ID);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	} //	getBPartnerCashTrx

	/**
	 * 	Get BPartner with Value
	 *	@param ctx context 
	 *	@param Value value
	 *	@param saTrxName Transaction name
	 *	@return BPartner or null
	 */
	public static MBPartner get(Properties ctx, String Value, String saTrxName) {
		if (Value == null || Value.length() == 0)
			return null;
		// Merge ADempiere trunk_rev14653
		final String whereClause = "Value=? AND AD_Client_ID=?";
		MBPartner retValue = new Query(ctx, I_C_BPartner.Table_Name, whereClause, saTrxName)
		.setParameters(Value,Env.getAD_Client_ID(ctx))
		.firstOnly();
		return retValue;
	} // get
	
	/**
	 * 	Get BPartner with Value
	 *	@param ctx context 
	 *	@param Value value
	 *	@return BPartner or null
	 */
	public static MBPartner get(Properties ctx, String Value) {
		return get(ctx, Value, null);
	}
	
	/**
	 * 	Get BPartner with Value
	 *	@param ctx context 
	 *	@param Value value
	 *	@return BPartner or null
	 */
	public static MBPartner get (Properties ctx, int C_BPartner_ID)
	{
		// Merge ADempiere trunk_rev14653
		final String whereClause = "C_BPartner_ID=?";
		MBPartner retValue = new Query(ctx,I_C_BPartner.Table_Name,whereClause,null)
		.setParameters(C_BPartner_ID)
		.firstOnly();
		return retValue;
	}	//	get

	/**
	 * 	Get BPartner with Value
	 *	@param ctx context 
	 *	@param Value value
	 *	@return BPartner or null
	 */
	public static MBPartner getForClaim(Properties ctx, String payerId) {
		if (payerId == null || payerId.length() == 0)
			return null;
		// Merge ADempiere trunk_rev14653
		final String whereClause = "Claim_ID_Recp=? ";
		MBPartner retValue = new Query(ctx, I_C_BPartner.Table_Name, whereClause, null)
		.setParameters(payerId)
		.firstOnly();
		return retValue;
	} // get
	
	/**
	 * 	Get BPartner with Value
	 *	@param ctx context 
	 *	@param Value value
	 *	@return BPartner or null
	 */
	public static MBPartner getForClaimByName(Properties ctx, String namePayer) {
		if (namePayer == null || namePayer.length() == 0)
			return null;
		// Merge ADempiere trunk_rev14653
		final String whereClause = "Name=? ";
		MBPartner retValue = new Query(ctx, I_C_BPartner.Table_Name, whereClause, null)
		.setParameters(namePayer)
		.firstOnly();
		return retValue;
	} // get
	
	/**
	 * 	Get Not Invoiced Shipment Value
	 *	@param C_BPartner_ID partner
	 *	@return value in accounting currency
	 */
	public static BigDecimal getNotInvoicedAmt(int C_BPartner_ID) {
		BigDecimal retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append( "SELECT SUM(COALESCE("
			+ "currencyBase((ol.QtyDelivered-ol.QtyInvoiced)*ol.PriceActual,o.C_Currency_ID,o.DateOrdered, o.AD_Client_ID,o.AD_Org_ID) ,0)) "
			+ "FROM C_OrderLine ol"
			+ " INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) "
			+ "WHERE o.IsSOTrx='Y' AND Bill_BPartner_ID=?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_BPartner_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getBigDecimal(1);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;

	} // getNotInvoicedAmt

	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MBPartner.class);
	
	/**************************************************************************
	 * 	Constructor for new BPartner from Template
	 * 	@param ctx context
	 */
	public MBPartner(Properties ctx) {
		this(ctx, -1, null);
	}	//	MBPartner

	/**
	 * 	Default Constructor
	 * 	@param ctx context
	 * 	@param rs ResultSet to load from
	 * 	@param trxName transaction
	 */
	public MBPartner(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);

		// setRfc(impBP.getRfc());
		// setLlaveDeBusqueda(impBP.getLlaveDeBusqueda());
		// setImpresionDe(impBP.getImpresionDe());
		// setImpresiones(impBP.getImpresiones());
		// setEnviaEMail(impBP.getEnviaEMail());
		// setEMail(impBP.getEMail());
	} //	MBPartner

	/**
	 * 	Default Constructor
	 * 	@param ctx context
	 * 	@param C_BPartner_ID partner or 0 or -1 (load from template)
	 * 	@param trxName transaction
	 */
	public MBPartner(Properties ctx, int C_BPartner_ID, String trxName) {
		super(ctx, C_BPartner_ID, trxName);
		//
		if (C_BPartner_ID == -1) {
			initTemplate(Env.getAD_Client_ID(ctx));
			C_BPartner_ID = 0;
		}
		if (C_BPartner_ID == 0) {
			// setValue ("");
			// setName ("");
			// setName2 (null);
			// setDUNS("");
			
			setIsCustomer(true);
			setIsProspect(true);
			//
			setSendEMail(false);
			setIsOneTime(false);
			setIsVendor(false);
			setIsSummary(false);
			setIsEmployee(false);
			setIsSalesRep(false);
			setIsTaxExempt(false);
			setIsDiscountPrinted(false);
			//
			setSO_CreditLimit(Env.ZERO);
			setSO_CreditUsed(Env.ZERO);
			setTotalOpenBalance(Env.ZERO);
			setSOCreditStatus(SOCREDITSTATUS_NoCreditCheck);
			//
			setFirstSale(null);
			setActualLifeTimeValue(Env.ZERO);
			setPotentialLifeTimeValue(Env.ZERO);
			setAcqusitionCost(Env.ZERO);
			setShareOfCustomer(0);
			setSalesVolume(0);

			// setRfc(impBP.getRfc());
			// setLlaveDeBusqueda(impBP.getLlaveDeBusqueda());
			// setImpresionDe(impBP.getImpresionDe());
			// setImpresiones(impBP.getImpresiones());
			// setEnviaEMail(impBP.getEnviaEMail());
			// setEMail(impBP.getEMail());
		}
		log.fine(toString());
	} // MBPartner

	/**
	 * 	Import Contstructor
	 *	@param impBP import
	 */
	public MBPartner(X_I_BPartner impBP) {
		this(impBP.getCtx(), 0, impBP.get_TrxName());
		setClientOrg(impBP);
		setUpdatedBy(impBP.getUpdatedBy());
		//
		String value = impBP.getValue();
		if (value == null || value.length() == 0)
			value = impBP.getEMail();
		if (value == null || value.length() == 0)
			value = impBP.getContactName();
		setValue(value);
		String name = impBP.getName();
		if (name == null || name.length() == 0)
			name = impBP.getContactName();
		if (name == null || name.length() == 0)
			name = impBP.getEMail();
		setName(name);
		setName2(impBP.getName2());
		setDescription(impBP.getDescription());
		// setHelp(impBP.getHelp());
		setDUNS(impBP.getDUNS());
		setTaxID(impBP.getTaxID());
		setNAICS(impBP.getNAICS());
		setC_BP_Group_ID(impBP.getC_BP_Group_ID());
		setIsVendor(impBP.isVendor()); // expert : miguel rojas
		setIsCustomer(impBP.isCustomer()); // expert : miguel rojas
		setInvoiceRule(impBP.getInvoiceRule());
		setPaymentRule(getPaymentRule());
		setPaymentRulePO(getPaymentRulePO());
		setM_DiscountSchema_ID(getM_DiscountSchema_ID());
		setPO_DiscountSchema_ID(getPO_DiscountSchema_ID());
		setM_PriceList_ID(getM_PriceList_ID());
		setPO_PriceList_ID(getPO_PriceList_ID());
		setC_PaymentTerm_ID(getC_PaymentTerm_ID());
		setPO_PaymentTerm_ID(getPO_PaymentTerm_ID());

		// setRfc(impBP.getRfc());
		// setLlaveDeBusqueda(impBP.getLlaveDeBusqueda());
		// setImpresionDe(impBP.getImpresionDe());
		// setImpresiones(impBP.getImpresiones());
		// setEnviaEMail(impBP.getEnviaEMail());
		// setEMail(impBP.getEMail());

		setRfc(impBP.getRfc());
		setLlaveDeBusqueda(impBP.getLlaveDeBusqueda());
		setImpresionDe(impBP.getImpresionDe());
		setImpresiones(impBP.getImpresiones());
		setEnviaEMail(impBP.getEnviaEMail());
		setEMail(impBP.getEMail());

	} // MBPartner
	
	/**
	 * 	Load Default BPartner
	 * 	@param AD_Client_ID client
	 * 	@return true if loaded
	 */
	private boolean initTemplate(int AD_Client_ID) {
		if (AD_Client_ID == 0)
			throw new IllegalArgumentException("Client_ID=0");

		boolean success = true;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_BPartner.* FROM C_BPartner ");
		sql.append("INNER JOIN AD_ClientInfo ON (C_BPartner.C_BPartner_ID = AD_ClientInfo.C_BPartnerCashTrx_ID ");
		sql.append(" AND AD_ClientInfo.AD_Client_ID = ? )");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
				success = load(rs);
			else {
				load(0, null);
				success = false;
				log.severe("None found");
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		setStandardDefaults();
		// Reset
		set_ValueNoCheck("C_BPartner_ID", I_ZERO);
		setValue("");
		setName("");
		setName2(null);
		return success;
	} // getTemplate

	/**
	 * 	Get All Contacts
	 * 	@param reload if true users will be requeried
	 *	@return contacts
	 */
	public MUser[] getContacts(boolean reload) {
		if (reload || m_contacts == null || m_contacts.length == 0)
			;
		else
			return m_contacts;
		//
		ArrayList<MUser> list = new ArrayList<MUser>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM AD_User WHERE C_BPartner_ID = ?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MUser(getCtx(), rs, null));

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		m_contacts = new MUser[list.size()];
		list.toArray(m_contacts);
		return m_contacts;
	} // getContacts

	/**
	 * 	Get All Contacts
	 * 	@param reload if true users will be requeried
	 *	@return contacts
	 */
	public ArrayList<KeyNamePair> getContacts() {
		
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT AD_User_ID, name, description FROM AD_User WHERE C_BPartner_ID = ?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();			
			while (rs.next()){
				String n = rs.getString(2);
				String d = rs.getString(3);
				StringBuilder nd = new StringBuilder();
				if(n != null && !n.isEmpty()){
					nd.append(n);
				}
				if(d != null && !d.isEmpty()){
					if(!nd.toString().isEmpty()){
						nd.append("-").append(d);
					}else{
						nd.append(d);
					}
				}
				list.add(new KeyNamePair(rs.getInt(1), nd.toString()));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	} // getContacts
	
	/**
	 * 	Get specified or first Contact
	 * 	@param AD_User_ID optional user
	 *	@return contact or null
	 */
	public MUser getContact(int AD_User_ID) {
		MUser[] users = getContacts(false);
		if (users.length == 0)
			return null;
		for (int i = 0; AD_User_ID != 0 && i < users.length; i++) {
			if (users[i].getAD_User_ID() == AD_User_ID)
				return users[i];
		}
		return users[0];
	} // getContact

	/**
	 * 	Get All Locations
	 * 	@param reload if true locations will be requeried
	 *	@return locations
	 */
	public MBPartnerLocation[] getLocations(boolean reload) {
		if (reload || m_locations == null || m_locations.length == 0)
			;
		else
			return m_locations;
		//
		ArrayList<MBPartnerLocation> list = new ArrayList<MBPartnerLocation>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append( "SELECT * FROM C_BPartner_Location WHERE C_BPartner_ID = ? ORDER BY Created DESC");
		// Expert Twry.. Que sea de la direccion mas actual a la mas antigua
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MBPartnerLocation(getCtx(), rs, get_TrxName()));
			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		m_locations = new MBPartnerLocation[list.size()];
		list.toArray(m_locations);
		return m_locations;
	} // getLocations

	/**
	 * 	Get explicit or first bill Location
	 * 	@param C_BPartner_Location_ID optional explicit location
	 *	@return location or null
	 */
	public MBPartnerLocation getLocation(int C_BPartner_Location_ID) {
		MBPartnerLocation[] locations = getLocations(false);
		if (locations.length == 0)
			return null;
		MBPartnerLocation retValue = null;
		for (int i = 0; i < locations.length; i++) {
			if (locations[i].getC_BPartner_Location_ID() == C_BPartner_Location_ID)
				return locations[i];
			if (retValue == null && locations[i].isBillTo())
				retValue = locations[i];
		}
		if (retValue == null)
			return locations[0];
		return retValue;
	}	//	getLocation
	
	/**
	 * 	Get Bank Accounts
	 * 	@param requery requery
	 *	@return Bank Accounts
	 */
	public MBPBankAccount[] getBankAccounts(boolean requery) {
		if (m_accounts != null && m_accounts.length >= 0 && !requery) // re-load
			return m_accounts;
		//
		ArrayList<MBPBankAccount> list = new ArrayList<MBPBankAccount>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM C_BP_BankAccount WHERE C_BPartner_ID = ? ");
		sql.append(" and isactive = 'Y'  order by isach desc");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MBPBankAccount(getCtx(), rs, get_TrxName()));
			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		m_accounts = new MBPBankAccount[list.size()];
		list.toArray(m_accounts);
		return m_accounts;
	} // getBankAccounts

	/**
	 *	String Representation
	 * 	@return info
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer ("MBPartner[ID=")
			.append(get_ID())
			.append(",Value=").append(getValue())
			.append(",Name=").append(getName())
			.append(",Open=").append(getTotalOpenBalance())
			.append ("]");
		return sb.toString();
	} // toString

//	/**
//	 * 	Set Client/Org
//	 *	@param AD_Client_ID client
//	 *	@param AD_Org_ID org
//	 */
//	@Override
//	public void setClientOrg(int AD_Client_ID, int AD_Org_ID) {
//		super.setClientOrg(AD_Client_ID, AD_Org_ID);
//	} // setClientOrg

	/**
	 * 	Set Linked Organization.
	 * 	(is Button)
	 *	@param AD_OrgBP_ID 
	 */
	public void setAD_OrgBP_ID(int AD_OrgBP_ID) {
		if (AD_OrgBP_ID == 0)
			super.setAD_OrgBP_ID(null);
		else
			super.setAD_OrgBP_ID(String.valueOf(AD_OrgBP_ID));
	} //	setAD_OrgBP_ID
	
	/** 
	 * 	Get Linked Organization.
	 * 	(is Button)
	 * 	The Business Partner is another Organization 
	 * 	for explicit Inter-Org transactions 
	 * 	@return AD_Org_ID if BP
	 */
	public int getAD_OrgBP_ID_Int() {
		String org = super.getAD_OrgBP_ID();
		if (org == null)
			return 0;
		int AD_OrgBP_ID = 0;
		try {
			AD_OrgBP_ID = Integer.parseInt(org);
		} catch (Exception ex) {
			log.log(Level.SEVERE, org, ex);
		}
		return AD_OrgBP_ID;
	} // getAD_OrgBP_ID_Int

	/**
	 * 	Get Primary C_BPartner_Location_ID
	 *	@return C_BPartner_Location_ID
	 */
	public int getPrimaryC_BPartner_Location_ID() {
		if (m_primaryC_BPartner_Location_ID == null) {
			MBPartnerLocation[] locs = getLocations(false);
			for (int i = 0; m_primaryC_BPartner_Location_ID == null && i < locs.length; i++) {
				if (locs[i].isBillTo()) {
					setPrimaryC_BPartner_Location_ID(locs[i].getC_BPartner_Location_ID());
					break;
				}
			}
			// get first
			if (m_primaryC_BPartner_Location_ID == null && locs.length > 0) {
				setPrimaryC_BPartner_Location_ID(locs[0].getC_BPartner_Location_ID());
			}
		}
		if (m_primaryC_BPartner_Location_ID == null) {
			return 0;
		}
		return m_primaryC_BPartner_Location_ID.intValue();
	} // getPrimaryC_BPartner_Location_ID
	
	/**
	 * 	Get Primary C_BPartner_Location_ID
	 *	@return C_BPartner_Location_ID
	 */
	public MBPartnerLocation getPrimaryPartnerLocation(String trxName) {
		return new MBPartnerLocation(Env.getCtx(), getPrimaryC_BPartner_Location_ID(), trxName);
	} // getPrimaryC_BPartner_Location_ID
	
	/**
	 * 	Get Primary AD_User_ID
	 *	@return AD_User_ID
	 */
	public int getPrimaryAD_User_ID() {
		if (m_primaryAD_User_ID == null) {
			MUser[] users = getContacts(false);
			// for (int i = 0; i < users.length; i++)
			// {
			// }
			if (m_primaryAD_User_ID == null && users.length > 0) {
				setPrimaryAD_User_ID(users[0].getAD_User_ID());
			}
		}
		if (m_primaryAD_User_ID == null) {
			return 0;
		}
		return m_primaryAD_User_ID.intValue();
	} // getPrimaryAD_User_ID

	/**
	 * 	Set Primary C_BPartner_Location_ID
	 *	@param C_BPartner_Location_ID id
	 */
	public void setPrimaryC_BPartner_Location_ID(int C_BPartner_Location_ID) {
		m_primaryC_BPartner_Location_ID = new Integer(C_BPartner_Location_ID);
	} //	setPrimaryC_BPartner_Location_ID
	
	/**
	 * 	Set Primary AD_User_ID
	 *	@param AD_User_ID id
	 */
	public void setPrimaryAD_User_ID(int AD_User_ID) {
		m_primaryAD_User_ID = new Integer(AD_User_ID);
	} // setPrimaryAD_User_ID
	
	/**
	 * 	Calculate Total Open Balance and SO_CreditUsed.
	 *  (includes drafted invoices)
	 */
	public void setTotalOpenBalance() {
		BigDecimal SO_CreditUsed = null;
		BigDecimal TotalOpenBalance = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT ");
		// SO Credit Used
		sql.append(" COALESCE( ")//
				.append("  ( SELECT SUM( ")//
				.append("              currencyBase( ")//
				.append("                          invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID), ")//
				.append("                          i.C_Currency_ID,i.DateOrdered, i.AD_Client_ID,i.AD_Org_ID ")//
				.append("              ) ")//
				.append("            ) ")//
				.append("    FROM C_Invoice_v i ")//
				.append("    WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsSOTrx='Y' AND i.IsPaid='N' ")//
				.append("  )")//
			.append(" , 0) as SOCreditUsed, ");
		
		// Balance (incl. unallocated payments));
		sql.append(" COALESCE( ")//
				.append("  ( SELECT SUM( ")//
				.append("              currencyBase( ")//
				.append("              				invoiceOpen(i.C_Invoice_ID ,i.C_InvoicePaySchedule_ID), ")//
				.append("                           i.C_Currency_ID,i.DateOrdered, i.AD_Client_ID,i.AD_Org_ID ")//
				.append("              ) * i.MultiplierAP ")//
				.append("            ) ")//
				.append("    FROM C_Invoice_v i ")//
				.append("    WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsPaid='N'")//
				.append("  )")//
			.append(" , 0) ");
		
		sql.append(" - ");
				
		sql.append(" COALESCE(" )//
				.append("  ( SELECT SUM( ")//
				.append("              currencyBase(p.PayAmt,p.C_Currency_ID,p.DateTrx,p.AD_Client_ID,p.AD_Org_ID)" )//
				.append("              ) ")//
				.append("    FROM C_Payment_v p ")//
				.append("    WHERE p.C_BPartner_ID=bp.C_BPartner_ID AND p.IsAllocated='N'")//
				.append("    AND p.C_Charge_ID IS NULL ")//
				.append("    AND NOT EXISTS ")//
				.append("              ( SELECT * ")//
				.append("                FROM C_AllocationLine al ")//
				.append("                WHERE p.C_Payment_ID=al.C_Payment_ID ")//
				.append("              )")//
				.append("  )")//
			.append(" , 0) as TotalOpenBalance ");
				
		sql.append(" FROM C_BPartner bp ");
		sql.append(" WHERE C_BPartner_ID = ? ");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				SO_CreditUsed = rs.getBigDecimal(1);
				TotalOpenBalance = rs.getBigDecimal(2);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		m_TotalOpenBalanceSet = true;
		if (SO_CreditUsed != null) {
			this.setSO_CreditUsed(SO_CreditUsed);
		}
		if (TotalOpenBalance != null) {
			this.setTotalOpenBalance(TotalOpenBalance);
		}
		setSOCreditStatus();
	} // setTotalOpenBalance

	/**
	 * 	Set Actual Life Time Value from DB
	 */
	public void setActualLifeTimeValue() {
		BigDecimal ActualLifeTimeValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT ");
		sql.append(" COALESCE (")//
				.append("   (  SELECT SUM(")//
				.append("                 currencyBase(i.GrandTotal,i.C_Currency_ID,i.DateOrdered,")//
				.append("                              i.AD_Client_ID,i.AD_Org_ID)")//
				.append("                 ) ")//
				.append("      FROM C_Invoice_v i ")//
				.append("      WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsSOTrx='Y'")//
				.append("    )")//
			.append(" , 0 ) as ActualLifeTimeValue ");
		sql.append(" FROM C_BPartner bp ");
		sql.append(" WHERE C_BPartner_ID = ?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				ActualLifeTimeValue = rs.getBigDecimal(1);

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		if (ActualLifeTimeValue != null) {
			this.setActualLifeTimeValue(ActualLifeTimeValue);
		}
	} // setActualLifeTimeValue
	
	/**
	 * 	Get Total Open Balance
	 * 	@param calculate if null calculate it
	 *	@return Open Balance
	 */
	public BigDecimal getTotalOpenBalance(boolean calculate) {
		if (getTotalOpenBalance().signum() == 0 && calculate)
			setTotalOpenBalance();
		return getTotalOpenBalance();
		// return super.getTotalOpenBalance ();
	} // getTotalOpenBalance

	/**
	 * Set Credit Status
	 */
	public void setSOCreditStatus() {
		BigDecimal creditLimit = getSO_CreditLimit();
		// Nothing to do
		if (SOCREDITSTATUS_NoCreditCheck.equals(getSOCreditStatus()) || //
				SOCREDITSTATUS_CreditStop.equals(getSOCreditStatus()) || //
				Env.ZERO.compareTo(creditLimit) == 0)
			return;

		// Above Credit Limit
		if (creditLimit.compareTo(getTotalOpenBalance(!m_TotalOpenBalanceSet)) < 0) {
			setSOCreditStatus(SOCREDITSTATUS_CreditHold);
		} else {
			// Above Watch Limit
			BigDecimal watchAmt = creditLimit.multiply(getCreditWatchRatio());
			if (watchAmt.compareTo(getTotalOpenBalance()) < 0)
				setSOCreditStatus(SOCREDITSTATUS_CreditWatch);
			else
				// is OK
				setSOCreditStatus(SOCREDITSTATUS_CreditOK);
		}
	} // setSOCreditStatus
	
	/**
	 * 	Get SO CreditStatus with additional amount
	 * 	@param additionalAmt additional amount in Accounting Currency
	 *	@return sinulated credit status
	 */
	public String getSOCreditStatus(BigDecimal additionalAmt) {
		if (additionalAmt == null || additionalAmt.signum() == 0)
			return getSOCreditStatus();
		//
		BigDecimal creditLimit = getSO_CreditLimit();
		// Nothing to do
		if (SOCREDITSTATUS_NoCreditCheck.equals(getSOCreditStatus()) || SOCREDITSTATUS_CreditStop.equals(getSOCreditStatus())
				|| Env.ZERO.compareTo(creditLimit) == 0)
			return getSOCreditStatus();

		// Above (reduced) Credit Limit
		creditLimit = creditLimit.subtract(additionalAmt);
		if (creditLimit.compareTo(getTotalOpenBalance(!m_TotalOpenBalanceSet)) < 0)
			return SOCREDITSTATUS_CreditHold;

		// Above Watch Limit
		BigDecimal watchAmt = creditLimit.multiply(getCreditWatchRatio());
		if (watchAmt.compareTo(getTotalOpenBalance()) < 0)
			return SOCREDITSTATUS_CreditWatch;

		// is OK
		return SOCREDITSTATUS_CreditOK;
	} // getSOCreditStatus

	/**
	 * 	Get Credit Watch Ratio
	 *	@return BP Group ratio or 0.9
	 */
	public BigDecimal getCreditWatchRatio() {
		return getBPGroup().getCreditWatchRatio();
	} //	getCreditWatchRatio
		
	/**
	 * 	Credit Status is Stop or Hold.
	 *	@return true if Stop/Hold
	 */
	public boolean isCreditStopHold() {
		String status = getSOCreditStatus();
		return SOCREDITSTATUS_CreditStop.equals(status) || SOCREDITSTATUS_CreditHold.equals(status);
	} //	isCreditStopHold
	
	/**
	 * Set Total Open Balance
	 * 
	 * @param TotalOpenBalance
	 */
	@Override
	public void setTotalOpenBalance(BigDecimal TotalOpenBalance) {
		m_TotalOpenBalanceSet = false;
		// Lama - Partner_Cte
		if(getPartnerCte(false) != null){
			m_partnerCte.setTotalOpenBalance(TotalOpenBalance);
		}
		super.setTotalOpenBalance(TotalOpenBalance);
	} // setTotalOpenBalance

	/**
	 * Get BP Group
	 * 
	 * @return group
	 */
	public MBPGroup getBPGroup() {
		if (m_group == null)
			m_group = MBPGroup.get(getCtx(), getC_BP_Group_ID(), get_TrxName());
		return m_group;
	} // getBPGroup

	/**
	 * Get BP Group
	 * 
	 * @param group
	 *            group
	 */
	public void setBPGroup(MBPGroup group) {
		m_group = group;
		if (m_group == null)
			return;
		// Lama - BP_Group_Cte
		X_C_BP_Group_Cte cte = null;
		int C_BP_Group_Cte_ID = DB.getSQLValue(
				null, //
				" SELECT C_BP_Group_Cte_ID FROM C_BP_Group_Cte WHERE C_BP_Group_ID = ? AND AD_Client_ID = ?", group.getC_BP_Group_ID(),
				Env.getAD_Client_ID(getCtx()));
		if (C_BP_Group_Cte_ID > 0) {
			cte = new X_C_BP_Group_Cte(getCtx(), C_BP_Group_Cte_ID, null);
		}
		setC_BP_Group_ID(m_group.getC_BP_Group_ID());
		// if (m_group.getC_Dunning_ID() != 0)
		setC_Dunning_ID(cte == null ? m_group.getC_Dunning_ID() : cte.getC_Dunning_ID());
		// if (m_group.getM_PriceList_ID() != 0)
		setM_PriceList_ID(cte == null ? m_group.getM_PriceList_ID() : cte.getM_PriceList_ID());
		// if (m_group.getPO_PriceList_ID() != 0)
		setPO_PriceList_ID(cte == null ? m_group.getPO_PriceList_ID() : cte.getPO_PriceList_ID());
		// if (m_group.getM_DiscountSchema_ID() != 0)
		setM_DiscountSchema_ID(cte == null ? m_group.getM_DiscountSchema_ID() : cte.getM_DiscountSchema_ID());
		// if (m_group.getPO_DiscountSchema_ID() != 0)
		setPO_DiscountSchema_ID(cte == null ? m_group.getPO_DiscountSchema_ID() : cte.getPO_DiscountSchema_ID());
	}	//	setBPGroup

	/**
	 * Get PriceList
	 * 
	 * @return price List
	 */
	@Override
	public int getM_PriceList_ID() {
		int ii = 0;
		if (getPartnerCte(false) != null) {
			ii = m_partnerCte.getM_PriceList_ID();
		}
		if (ii == 0) {
			ii = super.getM_PriceList_ID();
		}
		if (ii == 0) {
			if (getBPGroup()!=null){
				ii = getBPGroup().getM_PriceList_ID();
			}
		}
		return ii;
	} // getM_PriceList_ID

	/**
	 * Get PO PriceList
	 * 
	 * @return price list
	 */
	@Override
	public int getPO_PriceList_ID() {
		int ii = super.getPO_PriceList_ID();
		if (ii == 0)
			ii = getBPGroup().getPO_PriceList_ID();
		return ii;
	} //

	/**
	 * Get DiscountSchema
	 * 
	 * @return Discount Schema
	 */
	@Override
	public int getM_DiscountSchema_ID() {
		int ii = 0;
		if (getPartnerCte(false) != null) {
			ii = m_partnerCte.getM_DiscountSchema_ID();
		}
		if (ii == 0) {
			ii = super.getM_DiscountSchema_ID();
		}
		if (ii == 0) {
			ii = getBPGroup().getM_DiscountSchema_ID();
		}
		return ii;
	} // getM_DiscountSchema_ID

	/**
	 * Get PO DiscountSchema
	 * 
	 * @return po discount
	 */
	@Override
	public int getPO_DiscountSchema_ID() {
		int ii = super.getPO_DiscountSchema_ID();
		if (ii == 0) {
			ii = getBPGroup().getPO_DiscountSchema_ID();
		}
		return ii;
	} // getPO_DiscountSchema_ID

//	// TODO Documentar Metodo
//	/**
//	 * Method to obtain bill bussiness partner
//	 * 
//	 * @param c_bPartnerID
//	 *            id
//	 * @return Vector<OptionItem>
//	 */
//	public static Vector<OptionItem> getBillBPartner(int c_bPartnerID) {
//		Vector<OptionItem> billBPVector = null;
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		
//		sql.append("SELECT C_BPARTNER_ID, NAME FROM C_BPARTNER ")
//			.append("where C_BPartner.IsSummary='N'  AND (C_BPartner.C_BPartner_ID= ? OR 'N'= 'N' OR EXISTS (SELECT * FROM C_BP_Relation r ")
//			.append("WHERE C_BPartner.C_BPartner_ID=r.C_BPartnerRelation_ID AND r.C_BPartner_ID=? AND r.IsBillTo='Y'))");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.clearParameters();
//			pstmt.setInt(1, c_bPartnerID);
//			pstmt.setInt(2, c_bPartnerID);
//			rs = pstmt.executeQuery();
//			billBPVector = new Vector<OptionItem>();
//
//			while (rs.next()) {
//				billBPVector.add(new OptionItem(rs.getString(1), rs.getString(2)));
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return billBPVector;
//	}
	
//	/**
//	 * Method to obtain bill location	 * 
//	 * @param billBpartner
//	 * @return Vector<OptionItem>
//	 */
//	public static Vector<OptionItem> getBillLocations(int billBpartner) {
//		Vector<OptionItem> billLocation = null;
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append("SELECT C_Bpartner_Location_id, Name ");
//		sql.append("FROM C_Bpartner_Location ");
//		sql.append("WHERE C_BPARTNER_LOCATION.c_bpartner_id=? AND C_BPARTNER_LOCATION.isbillto='Y' AND C_BPARTNER_LOCATION.ISACTIVE='Y'");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.clearParameters();
//			pstmt.setInt(1, billBpartner);
//
//			rs = pstmt.executeQuery();
//			billLocation = new Vector<OptionItem>();
//
//			while (rs.next()) {
//				billLocation.add(new OptionItem(rs.getString(1), rs.getString(2)));
//			}
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return billLocation;
//	}
	
	private boolean copy = false;

	public boolean isCopy() {
		return copy;
	}

	public void setCopy(boolean copy) {
		this.copy = copy;
	}

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (is_ValueChanged("C_BP_Group_ID")) {
			setBPGroup(getBPGroup()); // setDefaults
		}
		
		//Si no es copia
		if(!isCopy()){
			//si se modific� el RFC o es nuevo
			if (is_ValueChanged("TaxID") || getC_BPartner_ID() <= 0) {
				// si est� configurado que es facturaci�n electr�nica
				MEXMEConfigFE cfe = MEXMEConfigFE.get(getCtx(), null);
				if (cfe != null) {

					// validamos que no sea nulo
					if (getTaxID() == null || getTaxID().trim().equals("")) {
						log.saveError(" ", Msg.getMsg(Env.getCtx(), "RFC.error.blank"));
						return false;
					}
					
					if (getConfirmPanel() != null) {
						// validamos la longitud y que no contenga caracteres no validos
						if (!Validacion.validacionRfc(getTaxID(), getCtx(), true)) {
							boolean confirm = getConfirmPanel().confirm(Msg.getMsg(Env.getCtx(), "RFC.confirm"));
							// si desea elegir un rfc generico
							if (confirm) {
								StringBuilder msj = new StringBuilder(Msg.getMsg(Env.getCtx(), "RFC.questionYes"));
								msj.append(cfe.getRFC_Nacional() + Msg.getMsg(Env.getCtx(), "RFC.questionNo"));
								msj.append(cfe.getRFC_Extranjero());
								msj.append(Msg.getMsg(Env.getCtx(), "RFC.questionCancel"));
								confirm = getConfirmPanel().confirm(msj.toString());
								// si elige RFC nacional
								if (confirm) {
									setTaxID(cfe.getRFC_Nacional());
									return true;
									// si elige RFC extranjero
								} else {
									setTaxID(cfe.getRFC_Extranjero());
									return true;
								}
							}
							// si se dejo el capturado validamos que no tenga caracteres no v�lidos
							if (!Validacion.validacionRfc(getTaxID(), getCtx(), false)) {
								log.saveError(" ", Msg.getMsg(Env.getCtx(), "RFC.error.invalidChar"));
								return false;
							}
							log.saveError(" ", Msg.getMsg(Env.getCtx(), "RFC.alert.invalid"));
							return false;
						}
					}
				}
			}
		}

		return true;
	} // beforeSave
	
	/**************************************************************************
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (newRecord & success) {
			// Trees
			insert_Tree(X_AD_Tree.TREETYPE_BPartner);
			// Accounting
			insert_Accounting("C_BP_Customer_Acct", "C_BP_Group_Acct", "p.C_BP_Group_ID=" + getC_BP_Group_ID());
			insert_Accounting("C_BP_Vendor_Acct", "C_BP_Group_Acct", "p.C_BP_Group_ID=" + getC_BP_Group_ID());
			insert_Accounting("C_BP_Employee_Acct", "C_AcctSchema_Default", null);
			insert_Accounting(X_C_BP_Creditor_Acct.Table_Name, "C_BP_Group_Acct", "p.C_BP_Group_ID=" + getC_BP_Group_ID());
		}

		// Value/Name change
		if (success && !newRecord && (is_ValueChanged("Value") || is_ValueChanged("Name"))) {
			MAccount.updateValueDescription(getCtx(), "C_BPartner_ID=" + getC_BPartner_ID(), get_TrxName());
		}

		if (success) {
			if (newRecord || getPartnerCte(false) == null) {
				success = createBPGroupCte();
			}
		}
		
		if (success){
		// Se regenera la vista EXME_Paciente_Cta_V solo para los campos que se ocupan.
			if (!newRecord &&
				(is_ValueChanged(COLUMNNAME_Value)
				|| is_ValueChanged(COLUMNNAME_Name)
				|| is_ValueChanged(COLUMNNAME_Elig_ID_Send))){
				//Si se cambia el nombre o el PayerID es necesario actualizar PacienteAseg.
				MEXMEPacienteCtaV.updateSearchBPartner(p_ctx, getC_BPartner_ID(), getAD_Org_ID(), get_TrxName());			
				List<MEXMEPacienteAseg> pacAsegLst =  MEXMEPacienteAseg.
						getAllByPartner(p_ctx, getC_BPartner_ID(), get_TrxName());
				if (is_ValueChanged(COLUMNNAME_Name)
						|| is_ValueChanged(COLUMNNAME_Elig_ID_Send)){
					for (MEXMEPacienteAseg pacAseg : pacAsegLst){
						pacAseg.setInsuranceName(getName());
						pacAseg.setInsuranceTaxID(getElig_ID_Send());
						pacAseg.save();
					}
				}
				
			}
		}
		
		return success;
	}	//	afterSave

	/**
	 * 	Before Delete
	 *	@return true
	 */
	@Override
	protected boolean beforeDelete() {
		return delete_Accounting("C_BP_Customer_Acct") //
				&& delete_Accounting("C_BP_Vendor_Acct") //
				&& delete_Accounting("C_BP_Employee_Acct")
		        && delete_Accounting(X_C_BP_Creditor_Acct.Table_Name);
	} //	beforeDelete

	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	@Override
	protected boolean afterDelete(boolean success) {
		
		if (success) {
			delete_Tree(X_AD_Tree.TREETYPE_BPartner);
		}
		
		return success;
	}	//	afterDelete
	
	// expert --- twry
	public int getId() {

		if (getC_BPartner_ID() > 0)
			return getC_BPartner_ID();
		else
			return 0;
	}
	
//	/**
//	 * Obtiene lista de socio de negocios pendientes de pago
//	 * @param ctx Contexto
//	 * @return Lista tipo OptionItem
//	 */
//	public static List<OptionItem> getListBPartnerDebit(Properties ctx) {
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(  "SELECT bp.C_BPartner_ID, bp.Name "
//					+ "FROM C_BPartner bp "
//					+ "WHERE "
//					+ "EXISTS (SELECT * "
//					+ "FROM C_Invoice i WHERE bp.C_BPartner_ID=i.C_BPartner_ID "
//				    + "AND i.IsSOTrx='N' AND i.IsPaid<>'Y') ");
//
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", I_C_BPartner.Table_Name, "bp"));
//
//		List<OptionItem> ls = new ArrayList<OptionItem>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				OptionItem oi = new OptionItem(rs.getString(1), rs.getString(2));
//				ls.add(oi);
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return ls;
//	}
	
//	//TODO: Documentar Metodo.
//	/**
//	 * Metodo que retorna Lista de CBPartners
//	 * 
//	 */
//	public Vector<OptionItem> getCBPartnerList(){
//		Vector<OptionItem> cbPartners = null;
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(  "select c_bpartner_id,name from c_bpartner where C_BPartner.IsActive='Y' AND C_BPartner.IsSummary='N'");
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			rs = pstmt.executeQuery();
//			cbPartners = new Vector<OptionItem>();
//			while (rs.next())
//				cbPartners.add(new OptionItem(rs.getString(1), rs.getString(2)));
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getCBPartnerList", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return cbPartners;
//	}
	
	/**
	 * 	Obtiene una lista de socios de negocio (name y value)
	 *	@param ctx
	 *	@return lst
	 **/
	public static List<KeyNamePair> getCBPartnerList(Properties ctx, String where, List<Object> params){

		List<KeyNamePair> lst = new ArrayList<KeyNamePair>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT upper(name) as name, c_bpartner_id FROM C_BPartner where isActive = 'Y' ");

		if (StringUtils.isNotBlank(where)) {
			sql.append(where);
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name)).append(" order by name ");
		pstmt = null;
		rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			if (params != null && params.size() > 0) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				KeyNamePair partner = new KeyNamePair(rs.getInt("c_bpartner_id"), rs.getString("name"));
				lst.add(partner);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCBPartnerList", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}
	
//	/**
//	 * Regresa el socio default
//	 * @return 
//	 */
//	public static MBPartner getDefault(Properties ctx, String trxName){
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MBPartner partner = null;
//
//		try {
//			sql.append("SELECT C_BPartner_ID FROM C_BPartner WHERE UPPER(NAME) = 'STANDARD' AND ");//FIXME Por definir el socio default
//			sql.append(" AD_CLIENT_ID = ? AND AD_ORG_ID = ? ");
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			
//			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
//			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
//			
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				partner = new MBPartner(ctx, rs.getInt(1), trxName);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return partner;
//	}
//	/**
//	 * Una lista de todas los socios de negocio
//	 * @param ctx
//	 * @param onlyInsurances si es true regresa solo los socios que sean aseguradoras
//	 * @param trxName
//	 * @return
//	 */
//	public static List<MBPartner> getAll(Properties ctx, boolean onlyInsurances, String trxName){
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<MBPartner> lst = new ArrayList<MBPartner>();
//
//		try {
//			sql.append(" SELECT C_BPARTNER.* ")
//				.append(" FROM C_BPARTNER ")
//				.append(" WHERE C_BPARTNER.ISACTIVE='Y' ");
//			if (onlyInsurances) {
////				sql.append(" AND C_BPARTNER.FACTURARASEG = 'Y' ");
//				sql.append(" AND Trim(C_BPARTNER.BP_Class)=? ");
//			}
//
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//			sql.append(" ORDER BY C_BPARTNER.NAME ");
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			if (onlyInsurances) {
//				pstmt.setString(1, BP_CLASS_I);
//			}
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				lst.add(new MBPartner(ctx, rs, trxName));
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lst; 
//	}
	// fin
	
	/**
	 * 
	 */
	public boolean createBPGroupCte(){
		m_partnerCte = new MBPartnerCte(getCtx(), 0, get_TrxName());
		setBPartnerCte();
		return m_partnerCte.save();
	}
	
	public void setBPartnerCte(){
		copyValues(this, getPartnerCte(false));

		//Se comenta por bloqueo generado en la BD MX el 25 Abril 2013.
		//Jesus Cantu con asesoria y apoyo de Twry perez y Mrojas a Solicitud de GC.
		//setSOCreditStatus();
		//setActualLifeTimeValue();
		//setTotalOpenBalance();
		
		m_partnerCte.setAcqusitionCost(super.getAcqusitionCost());
		// m_partnerCte.setActualLifeTimeValue(super.getActualLifeTimeValue());
		m_partnerCte.setC_BPartner_ID(super.getC_BPartner_ID());
		m_partnerCte.setC_Dunning_ID(super.getC_Dunning_ID());
		m_partnerCte.setC_InvoiceSchedule_ID(super.getC_InvoiceSchedule_ID());
		m_partnerCte.setC_PaymentTerm_ID(super.getC_PaymentTerm_ID());
		m_partnerCte.setDeliveryRule(super.getDeliveryRule());
		m_partnerCte.setDeliveryViaRule(super.getDeliveryViaRule());
		m_partnerCte.setFlatDiscount(super.getFlatDiscount());
		m_partnerCte.setInvoice_PrintFormat_ID(super.getInvoice_PrintFormat_ID());
		m_partnerCte.setInvoiceRule(super.getInvoiceRule());
		m_partnerCte.setIsDiscountPrinted(super.isDiscountPrinted());
		m_partnerCte.setM_DiscountSchema_ID(super.getM_DiscountSchema_ID());
		m_partnerCte.setM_PriceList_ID(super.getM_PriceList_ID());
		m_partnerCte.setPaymentRule(super.getPaymentRule());
		m_partnerCte.setPOReference(super.getPOReference());
		m_partnerCte.setPotentialLifeTimeValue(super.getPotentialLifeTimeValue());
		m_partnerCte.setSalesRep_ID(super.getSalesRep_ID());
		m_partnerCte.setShareOfCustomer(super.getShareOfCustomer());
		m_partnerCte.setShelfLifeMinPct(super.getShelfLifeMinPct());
		m_partnerCte.setSO_CreditLimit(super.getSO_CreditLimit());
		// m_partnerCte.setSO_CreditUsed(super.getSO_CreditUsed());
		m_partnerCte.setSO_Description(super.getSO_Description());
		// m_partnerCte.setSOCreditStatus(super.getSOCreditStatus());
		// m_partnerCte.setTotalOpenBalance(super.getTotalOpenBalance());
	}
	
	/**
	 * 
	 */
	public MBPartnerCte getPartnerCte(boolean reload) {
		if (reload || m_partnerCte == null || Env.getAD_Client_ID(getCtx()) != m_partnerCte.getAD_Client_ID()) {
			m_partnerCte = MBPartnerCte.get(getCtx(), super.getC_BPartner_ID(), get_TrxName());
		}
		return m_partnerCte;
	}

	/**
	 * Get Acquisition Cost.
	 * 
	 * @return The cost of gaining the prospect as a customer
	 */
	@Override
	public BigDecimal getAcqusitionCost() {
		if (getPartnerCte(false) != null && m_partnerCte.getAcqusitionCost() != null) {
			return m_partnerCte.getAcqusitionCost();
		}
		return super.getAcqusitionCost();
	}

	/**
	 * Get Actual Life Time Value.
	 * 
	 * @return Actual Life Time Revenue
	 */
	@Override
	public BigDecimal getActualLifeTimeValue() {
		if (getPartnerCte(false) != null && m_partnerCte.getTotalOpenBalance() != null) {
			return m_partnerCte.getTotalOpenBalance();
		}
		return super.getTotalOpenBalance();
	}

	/**
	 * Set Actual Life Time Value.
	 * 
	 * @param ActualLifeTimeValue
	 *            Actual Life Time Revenue
	 */
	@Override
	public void setActualLifeTimeValue(BigDecimal ActualLifeTimeValue) {
		// Lama - Partner_Cte
		if (getPartnerCte(false) != null) {
			m_partnerCte.setActualLifeTimeValue(ActualLifeTimeValue);
		}
		super.setActualLifeTimeValue(ActualLifeTimeValue);
	}
	
	/**
	 * Get Dunning.
	 * 
	 * @return Dunning Rules for overdue invoices
	 */
	@Override
	public int getC_Dunning_ID() {
		if (getPartnerCte(false) != null && m_partnerCte.getC_Dunning_ID() > 0) {
			return m_partnerCte.getC_Dunning_ID();
		}
		return super.getC_Dunning_ID();
	}

	/**
	 * Get Invoice Schedule.
	 * 
	 * @return Schedule for generating Invoices
	 */
	@Override
	public int getC_InvoiceSchedule_ID() {
		if (getPartnerCte(false) != null && m_partnerCte.getC_InvoiceSchedule_ID() > 0) {
			return m_partnerCte.getC_InvoiceSchedule_ID();
		}
		return super.getC_InvoiceSchedule_ID();
	}

	/**
	 * Get Payment Term.
	 * 
	 * @return The terms of Payment (timing, discount)
	 */
	@Override
	public int getC_PaymentTerm_ID() {
		if (getPartnerCte(false) != null&& m_partnerCte.getC_PaymentTerm_ID() > 0) {
			return m_partnerCte.getC_PaymentTerm_ID();
		}
		return super.getC_PaymentTerm_ID();
	}

	/**
	 * Get Delivery Rule.
	 * 
	 * @return Defines the timing ofgetPartnerCte Delivery
	 */
	@Override
	public String getDeliveryRule() {
		if (getPartnerCte(false) != null&& StringUtils.isNotBlank(m_partnerCte.getDeliveryRule())) {
			return m_partnerCte.getDeliveryRule();
		}
		return super.getDeliveryRule();
	}

	/**
	 * Get Delivery Via.
	 * 
	 * @return How the order will be delivered
	 */
	@Override
	public String getDeliveryViaRule() {
		if (getPartnerCte(false) != null&& StringUtils.isNotBlank(m_partnerCte.getDeliveryViaRule())) {
			return m_partnerCte.getDeliveryViaRule();
		}
		return super.getDeliveryViaRule();
	}

	/**
	 * Get Flat Discount %.
	 * 
	 * @return Flat discount percentage
	 */
	@Override
	public BigDecimal getFlatDiscount() {
		if (getPartnerCte(false) != null&& m_partnerCte.getFlatDiscount() != null) {
			return m_partnerCte.getFlatDiscount();
		}
		return super.getFlatDiscount();
	}

	/**
	 * Get Invoice Print Format.
	 * 
	 * @return Print Format for printing Invoices
	 */
	@Override
	public int getInvoice_PrintFormat_ID() {
		if (getPartnerCte(false) != null&& m_partnerCte.getInvoice_PrintFormat_ID() > 0) {
			return m_partnerCte.getInvoice_PrintFormat_ID();
		}
		return super.getInvoice_PrintFormat_ID();
	}

	/**
	 * Get Invoice Rule.
	 * 
	 * @return Frequency and method of invoicing
	 */
	@Override
	public String getInvoiceRule() {
		if (getPartnerCte(false) != null&& StringUtils.isNotBlank(m_partnerCte.getInvoiceRule())) {
			return m_partnerCte.getInvoiceRule();
		}
		return super.getInvoiceRule();
	}

	/**
	 * Get Discount Printed.
	 * 
	 * @return Print Discount on Invoice and Order
	 */
	@Override
	public boolean isDiscountPrinted() {
		if (getPartnerCte(false) != null && m_partnerCte.get_Value(COLUMNNAME_IsDiscountPrinted) != null) {
			return m_partnerCte.isDiscountPrinted();
		}
		return super.isDiscountPrinted();
	}

	/**
	 * Get Payment Rule.
	 * 
	 * @return How you pay the invoice
	 */
	@Override
	public String getPaymentRule() {
		if (getPartnerCte(false) != null&& StringUtils.isNotBlank(m_partnerCte.getPaymentRule())) {
			return m_partnerCte.getPaymentRule();
		}
		return super.getPaymentRule();
	}

	/**
	 * Get Order Reference.
	 * 
	 * @return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	 */
	@Override
	public String getPOReference() {
		if (getPartnerCte(false) != null&& StringUtils.isNotBlank(m_partnerCte.getPOReference())) {
			return m_partnerCte.getPOReference();
		}
		return super.getPOReference();
	}

	/**
	 * Get Potential Life Time Value.
	 * 
	 * @return Total Revenue expected
	 */
	@Override
	public BigDecimal getPotentialLifeTimeValue() {
		if (getPartnerCte(false) != null&& m_partnerCte.getPotentialLifeTimeValue() != null) {
			return m_partnerCte.getPotentialLifeTimeValue();
		}
		return super.getPotentialLifeTimeValue();
	}

	/**
	 * Get Sales Representative.
	 * 
	 * @return Sales Representative or Company Agent
	 */
	@Override
	public int getSalesRep_ID() {
		if (getPartnerCte(false) != null&& m_partnerCte.getSalesRep_ID() > 0) {
			return m_partnerCte.getSalesRep_ID();
		}
		return super.getSalesRep_ID();
	}

	/**
	 * Get Share.
	 * 
	 * @return Share of Customer's business as a percentage
	 */
	@Override
	public int getShareOfCustomer() {
		if (getPartnerCte(false) != null && m_partnerCte.getShareOfCustomer() > 0) {
			return m_partnerCte.getShareOfCustomer();
		}
		return super.getShareOfCustomer();
	}

	/**
	 * Get Min Shelf Life %.
	 * 
	 * @return Minimum Shelf Life in percent based on Product Instance Guarantee Date
	 */
	@Override
	public int getShelfLifeMinPct() {
		if (getPartnerCte(false) != null && m_partnerCte.getShelfLifeMinPct() > 0) {
			return m_partnerCte.getShelfLifeMinPct();
		}
		return super.getShelfLifeMinPct();
	}

	/**
	 * Get Credit Limit.
	 * 
	 * @return Total outstanding invoice amounts allowed
	 */
	@Override
	public BigDecimal getSO_CreditLimit() {
		if (getPartnerCte(false) != null && m_partnerCte.getSO_CreditLimit() != null) {
			return m_partnerCte.getSO_CreditLimit();
		}
		return super.getSO_CreditLimit();
	}

	/**
	 * Get Credit Status.
	 * 
	 * @return Business Partner Credit Status
	 */
	@Override
	public String getSOCreditStatus() {
		if (getPartnerCte(false) != null && StringUtils.isNotBlank(m_partnerCte.getSOCreditStatus())) {
			return m_partnerCte.getSOCreditStatus();
		}
		return super.getSOCreditStatus();
	}

	@Override
	public void setSOCreditStatus(String SOCreditStatus) {
		// Lama - Partner_Cte
		if(getPartnerCte(false) != null){
			m_partnerCte.setSOCreditStatus(SOCreditStatus);
		}
		super.setSOCreditStatus(SOCreditStatus);
	}

	/**
	 * Get Credit Used.
	 * 
	 * @return Current open balance
	 */
	@Override
	public BigDecimal getSO_CreditUsed() {
		if (getPartnerCte(false) != null&& m_partnerCte.getSO_CreditUsed() != null) {
			return m_partnerCte.getSO_CreditUsed();
		}
		return super.getSO_CreditUsed();
	}

	/**
	 * Set Credit Used.
	 * 
	 * @param SO_CreditUsed
	 *            Current open balance
	 */
	@Override
	public void setSO_CreditUsed(BigDecimal SO_CreditUsed) {
		// Lama - Partner_Cte
		if (getPartnerCte(false) != null) {
			m_partnerCte.setSO_CreditUsed(SO_CreditUsed);
		}
		super.setSO_CreditUsed(SO_CreditUsed);
	}

	/**
	 * Get Order Description.
	 * 
	 * @return Description to be used on orders
	 */
	@Override
	public String getSO_Description() {
		if (getPartnerCte(false) != null&& m_partnerCte.getSO_Description() != null) {
			return m_partnerCte.getSO_Description();
		}
		return super.getSO_Description();
	}

	/**
	 * Get Open Balance.
	 * 
	 * @return Total Open Balance Amount in primary Accounting Currency
	 */
	@Override
	public BigDecimal getTotalOpenBalance() {
		if (getPartnerCte(false) != null&& m_partnerCte.getTotalOpenBalance() != null) {
			return m_partnerCte.getTotalOpenBalance();
		}
		return super.getTotalOpenBalance();
	}
	
	public static boolean isMiscellaneous(Properties ctx, int C_BPartner_ID){
		if(C_BPartner_ID > 0){
			MBPartner partner = new MBPartner(ctx, C_BPartner_ID, null);
			return partner.isMiscellaneous(ctx);
		}else{
			return false;
		}
	}
	
	/**
	 * is Miscellaneous
	 * @param ctx Context
	 * @return <true> isMiscellaneous() (Financial o Payer)
	 */
	public boolean isMiscellaneous(Properties ctx){
		if(getC_BPartner_ID() > 0){
			if (getEXME_PayerClass_ID() > 0){
				MEXMEPayerClass pclass = new MEXMEPayerClass(ctx, getEXME_PayerClass_ID(), null);	
				return pclass.isMiscellaneous();
			}else{
				return false;
			} 
		}else{
			return false;
		}
	}
	
	/**
	 * Obtenemos las ID de cuenta paciente para el paciente especificado
	 * @param ctx El contexto de la aplicacion
	 * @param EXME_Paciente_ID El identificador del paciente
	 * @param trxName El nombre de la transaccion
	 * @param AD_Org_ID Organizacion por la que se desea filtrar
	 * @return ctaPac
	 */
	public static Integer[] getCtaPacByBPartner(Properties ctx, int C_BPartner_ID, int AD_Org_ID, String trxName) {
		
		//FIXME: Esto debe corregirse porque actualiza los registros de los indices de lucene
		//pero solo podra hacerlo para la organizacion que  
		
		List<Integer> lista = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		
		try {

			sql.append("SELECT DISTINCT PA.EXME_CtaPac_ID ") 
			   .append("FROM EXME_PacienteAseg PA ") 
			   .append("INNER JOIN EXME_CtaPac ON EXME_CtaPac.EXME_CtaPac_ID = PA.EXME_CtaPac_ID ") 
			   .append("WHERE PA.C_BPartner_ID = ? ") 
			   .append("AND PA.IsActive = 'Y' ") 
			   .append("AND EXME_CtaPac.AD_Org_ID = ? ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, C_BPartner_ID);
			pstmt.setInt(2, AD_Org_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(rs.getInt("EXME_CtaPac_ID"));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		Integer[] retValue = new Integer[lista.size()];
		lista.toArray(retValue);
		return retValue;
	}

//	/**
//	 * Busca un impuesto
//	 * @param ctx
//	 * @param taxID
//	 * @param trxName
//	 * @return
//	 */
//	public static MBPartner getTaxIDSearch(Properties ctx, String taxID, String trxName) {
//		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		where.append(" UPPER(TAXID) LIKE UPPER('%");
//		where.append(taxID);
//		where.append("%')");
//		return new Query(ctx, Table_Name, where.toString(), trxName)//
//			.setParameters(taxID)
//			.setOnlyActiveRecords(true)
//			.addAccessLevelSQL(true)
//			.first();
//	}
	
	/**
	 * Obtenemos el ID de la aseguradora segun la funcion fnc_getBPartnerTree
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param C_BPartner_ID
	 *            El identificador de la aseguradora
	 * @param trxName
	 *            El nombre de la transaccion
	 * @return Ids de los socios relacionados, separados por OR
	 */
	public static String getPartnerTree(Properties ctx, int C_BPartner_ID, String trxName) {
		List<Integer> ids = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {

			if (DB.isOracle()) {
				sql.append("SELECT ID FROM TABLE(fnc_getBPartnerTree(?))");
			} else {
				sql.append("SELECT ID FROM fnc_getBPartnerTree(?) ");
			}

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, C_BPartner_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ids.add( rs.getInt("ID"));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return StringUtils.join(ids, " OR ");
	}

	public boolean insertAcctClientEmpl() {
	     return insert_Accounting_Client("C_BP_Employee_Acct", "C_AcctSchema_Default", null);
	}


	public boolean insertAcctClientCust() {
	    return insert_Accounting_Client("C_BP_Customer_Acct", "C_AcctSchema_Default", null);
	}
	
	public boolean insertAcctClientVndr() {
	    return insert_Accounting_Client("C_BP_Vendor_Acct", "C_AcctSchema_Default", null);
	}
	
	public String getSupportBillingStr() {
		return MRefList.getListName(getCtx(), X_C_BPartner.SUPPORTBILLING_AD_Reference_ID, super.getSupportBilling());
	}
	
	/**
	 * 	Get Cash Trx Business Partner
	 * 	@param ctx context
	 * 	@param AD_Client_ID client
	 * 	@return Cash Trx Business Partner or null
	 */
	public static int getBPartnerSystem() {
		int retValue = 0;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT C_BPartner_ID    ");
		sql.append(" FROM   C_BPartner       ");
		sql.append(" WHERE  IsActive = 'Y'   ");
		sql.append(" AND    AD_Client_ID = 0 ");
		sql.append(" AND    AD_Org_ID = 0 ");
		sql.append(" ORDER BY IsDefault DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			if (rs.next()){
				retValue = rs.getInt("C_BPartner_ID");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	} //	getBPartnerCashTrx
	
	/**
	 *Obtener un proveedor apartir de una lista de precios
	 */
	public static MBPartner getParterByPriceLst(int piceLstId){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_Bpartner_Id FROM C_Bpartner WHERE M_PriceList_ID=? AND IsFactEspec='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", I_C_BPartner.Table_Name));
		int partnerId = DB.getSQLValue(null, sql.toString(), piceLstId);
		return new MBPartner(Env.getCtx(), partnerId, null);
	}
	
	/**
	 * 	Get Locations for BPartner
	 *	@param ctx context
	 *	@param C_BPartner_ID bp
	 *	@return array of locations
	 */
	public List<MBPartnerLocation> getForBPartner (){
		return new Query(getCtx(), I_C_BPartner_Location.Table_Name, " C_BPartner_ID = ? ", get_TrxName())//
		.setOrderBy(" Created DESC ")
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setParameters(getC_BPartner_ID())
		.list();
	}
	
	/**
	 * 	Get Locations for BPartner,
	 *  only the most current
	 */
	public int getLastAddressForBPartner(){
		final List<MBPartnerLocation> list = getForBPartner();
		return list.isEmpty()?0:list.get(0).getC_BPartner_Location_ID();
	}

	//CARD #1299
	/** Cuentas bancarias del socio */
	public List<KeyNamePair> getBankAccount(){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT a.C_BP_BankAccount_ID")
		.append(" , COALESCE(b.Name, ' ') || a.AccountNo AS Acct ")
		.append(" FROM C_BP_BankAccount a, C_Bank b ")
		.append(" WHERE C_BPartner_ID = ? ")
		.append(" AND a.IsActive='Y'      ");
		
		final List<KeyNamePair> tAccountCombo = new ArrayList<KeyNamePair>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, getC_BPartner_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				KeyNamePair pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				tAccountCombo.add(pp);
			}
		} catch (Exception eac) {
			log.log(Level.SEVERE, sql.toString(), eac);
		} finally {
			DB.close(rs,pstmt);
		}
		return tAccountCombo;
	}
	
	/**
	 * Obtiene el primero socio con el rfc (TAX_ID)
	 * 
	 * @param ctx
	 *            Contexto
	 * @param rfc
	 *            RFC
	 * @param trxName
	 *            Trx
	 * @return Id o -1 si no encontró el socio
	 */
	public static int findByRFC(Properties ctx, String rfc, String trxName) {
		int partnerId = -1;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  c_bpartner_id ");
		sql.append("FROM ");
		sql.append("  c_bpartner ");
		sql.append("WHERE ");
		sql.append("  taxId = ? AND ");
		sql.append("  isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));

		partnerId = DB.getSQLValue(trxName, sql.toString(), rfc);

		return partnerId;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param rfc
	 * @param trxName
	 * @return
	 */
	public static MBPartner getPartnerByName(Properties ctx, String name, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPPER(name) like UPPER(?) AND isactive = 'Y' ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		return new Query(Env.getCtx(), Table_Name, sql.toString(), trxName).setParameters(name).first();
	}
	
} // MBPartner
