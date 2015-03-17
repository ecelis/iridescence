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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.X_I_BPartner;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.adempiere.exceptions.DBException;

/**
 *	Import BPartners from I_BPartner
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ImportBPartner.java,v 1.6 2006/10/11 17:12:08 vgarcia Exp $
 */
public class ImportBPartner extends SvrProcess {

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;

	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Effective						*/
	private Timestamp		m_DateValue = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();

			if (name.equals("AD_Client_ID")) {
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			} else if (name.equals("DeleteOldImported")) {
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			} else {
				log.log(Level.SEVERE, "ImportBPartner.prepare - Unknown Parameter: " + name);
			}
		}

		if (m_DateValue == null) {
			m_DateValue = DB.getTSForOrg(getCtx());
		}

	}	//	prepare

	/**
	 *  Perrform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception {

		log.log(Level.CONFIG, "Starting BPartner import ....");

		StringBuilder sql = null;

		int no = 0;

		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported) {
			sql = new StringBuilder ("DELETE I_BPartner ");
		    sql.append("WHERE I_IsImported='Y'").append(clientCheck);

			no = DB.executeUpdate(sql.toString(), null);

			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder ("UPDATE I_BPartner ");
		sql.append("SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),");
		sql.append(" AD_Org_ID = COALESCE (AD_Org_ID, 0),");
		sql.append(" IsActive = COALESCE (IsActive, 'Y'),");
		sql.append(" Created = COALESCE (Created, SysDate),");
		sql.append(" CreatedBy = COALESCE (CreatedBy, 0),");
		sql.append(" Updated = COALESCE (Updated, SysDate),");
		sql.append(" UpdatedBy = COALESCE (UpdatedBy, 0),");
		sql.append(" I_ErrorMsg = NULL,");
		sql.append(" I_IsImported = 'N' ");
		sql.append(" WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Reset=" + no);


		//	Existing BPartner ? Match Value
		sql = new StringBuilder ("UPDATE I_BPartner i ");
		sql.append("SET C_BPartner_ID=(SELECT C_BPartner_ID FROM C_BPartner p");
		sql.append(" WHERE trim(i.Value)=trim(p.Value) AND p.AD_Client_ID IN (i.AD_Client_ID,0)) "); //expert
		sql.append(" WHERE Value IS NOT NULL");
		sql.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Found BPartner=" + no);

		sql = new StringBuilder ("UPDATE I_BPartner ");
		sql.append("SET C_BP_Group_ID=(SELECT C_BP_Group_ID FROM C_BP_Group g");
		sql.append(" WHERE trim(I_BPartner.GroupValue)=trim(g.Value) AND g.AD_Client_ID in (I_BPartner.AD_Client_ID,0)) "); //expert
		sql.append(" WHERE I_BPartner.GroupValue IS NOT NULL");
		sql.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Set Group=" + no);

		sql = new StringBuilder ("UPDATE I_BPartner ");
		sql.append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') || 'ERR=Invalid Group'  "); //expert
		sql.append("WHERE C_BP_Group_ID IS NULL");
		sql.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.config("Invalid Group=" + no);
		//

		//El campo Isdefault no existe en la tabla C_Country
		sql = new StringBuilder ("UPDATE I_BPartner ");
		sql.append("SET CountryCode='US' ");
		sql.append("WHERE CountryCode IS NULL AND C_Country_ID IS NULL");
		sql.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Set Country Default=" + no);

		//

		sql = new StringBuilder ("UPDATE I_BPartner ");
		sql.append("SET C_Country_ID=(SELECT C_Country_ID FROM C_Country c ");
		sql.append(" WHERE trim(I_BPartner.CountryCode)=trim(c.CountryCode) AND c.AD_Client_ID IN (0, I_BPartner.AD_Client_ID)) "); //expert
		sql.append(" WHERE I_BPartner.CountryCode IS NOT NULL ");
		sql.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Set Country=" + no);

		//

		sql = new StringBuilder ("UPDATE I_BPartner ");
		sql.append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') || 'ERR=Invalid Country' "); //expert
		sql.append("WHERE C_Country_ID IS NULL");
		sql.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.config("Invalid Country=" + no);

		//	Set Region
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("Set RegionName=(SELECT Name FROM C_Region r")
				.append(" WHERE r.IsDefault='Y' AND r.C_Country_ID=I_BPartner.C_Country_ID")
				.append(" AND r.AD_Client_ID IN (0, I_BPartner.AD_Client_ID) ");
		
		if (DB.isOracle()) {
			sql.append("AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			sql.append(") ");
		}

		sql.append(" WHERE RegionName IS NULL AND C_Region_ID IS NULL");
		sql.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Set Region Default=" + no);

		//
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("Set C_Region_ID=(SELECT C_Region_ID FROM C_Region r")
				.append(" WHERE r.Name=I_BPartner.RegionName AND r.C_Country_ID=I_BPartner.C_Country_ID")
				.append(" AND r.AD_Client_ID IN (0, I_BPartner.AD_Client_ID)) ")
				.append("WHERE C_Region_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Set Region=" + no);

		//
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') || 'ERR=Invalid Region' ")  //expert
				.append("WHERE C_Region_ID IS NULL ")
				.append(" AND EXISTS (SELECT * FROM C_Country c")
				.append(" WHERE c.C_Country_ID=I_BPartner.C_Country_ID AND c.HasRegion='Y')")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.config("Invalid Region=" + no);

		//	Set Greeting
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET C_Greeting_ID=(SELECT C_Greeting_ID FROM C_Greeting g")
				.append(" WHERE I_BPartner.BPContactGreeting=g.Name AND g.AD_Client_ID IN (0, I_BPartner.AD_Client_ID)) ")
				.append(" WHERE C_Greeting_ID IS NULL AND BPContactGreeting IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Set Greeting=" + no);

		//
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append(" SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') || 'ERR=Invalid Greeting' ") //expert
				.append(" WHERE C_Greeting_ID IS NULL AND BPContactGreeting IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.config("Invalid Greeting=" + no);


		//	Esquema de Descuento M_DiscountSchema 
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append(" SET M_DiscountSchema_ID=(SELECT M_DiscountSchema_ID FROM M_DiscountSchema ds")
				.append(" WHERE trim(I_BPartner.M_DiscountSchema_Name)=trim(ds.Name) AND ds.AD_Client_ID in (I_BPartner.AD_Client_ID,0)) ") //expert
				.append(" WHERE I_BPartner.M_DiscountSchema_Name IS NOT NULL ")
				.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Found Contact=" + no);

		//	Lista de Precios M_PriceList 

		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append(" SET M_PriceList_ID=(SELECT M_PriceList_ID FROM M_PriceList pl")
				.append(" WHERE trim(I_BPartner.M_PriceList_Name)=trim(pl.Name) AND  pl.AD_Client_ID in( I_BPartner.AD_Client_ID,0)) ") //expert
				.append(" WHERE I_BPartner.M_PriceList_Name IS NOT NULL  ")
				.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Found Contact=" + no);

		//	 Termino de Pago   C_PaymentTerm 
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET C_PaymentTerm_ID=(SELECT C_PaymentTerm_ID FROM C_PaymentTerm pt")
				.append(" WHERE trim(I_BPartner.C_PaymentTerm_Name)=trim(pt.Value) AND pt.AD_Client_ID IN (I_BPartner.AD_Client_ID,0) ");
		
		if (DB.isOracle()) {
			sql.append("AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			sql.append(") ");
		}
		
		sql.append("WHERE I_BPartner.C_PaymentTerm_Name IS NOT NULL  ");
		sql.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Found Contact=" + no);		

		// Condiciones de Pago   PO_DiscountSchema
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET PO_DiscountSchema_ID=(SELECT M_DiscountSchema_ID FROM M_DiscountSchema ds")
				.append(" WHERE trim(I_BPartner.PO_DiscountSchema_Name)=trim(ds.Name) AND ds.AD_Client_ID IN (I_BPartner.AD_Client_ID,0) ");
		
		if (DB.isOracle()) {
			sql.append("AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			sql.append(") ");
		}
		
		sql.append(" WHERE I_BPartner.PO_DiscountSchema_Name IS NOT NULL  ");
		sql.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Found Contact=" + no);		

		//	Lista de Precios Proveedor M_PriceList 
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET PO_PriceList_ID=(SELECT M_PriceList_ID FROM M_PriceList pl")
				.append(" WHERE trim(I_BPartner.PO_PriceList_Name)=trim(pl.Name) AND pl.AD_Client_ID IN (I_BPartner.AD_Client_ID,0) ");

		if (DB.isOracle()) {
			sql.append("AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			sql.append(") ");
		}
		
		sql.append(" WHERE I_BPartner.PO_PriceList_Name IS NOT NULL  ");
		sql.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Found Contact=" + no);


		//	 Termino de Pago Proveedor  C_PaymentTerm 
		sql = new StringBuilder ("UPDATE I_BPartner ");
		
		sql.append("SET PO_PaymentTerm_ID=(SELECT C_PaymentTerm_ID FROM C_PaymentTerm pt");
		sql.append(" WHERE trim(I_BPartner.PO_PaymentTerm_Name)=trim(pt.Value) AND pt.AD_Client_ID in  (I_BPartner.AD_Client_ID,0) ");
		
		if (DB.isOracle()) {			
		    sql.append(" AND ROWNUM=1) "); //expert
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			sql.append(") ");
		}
				
		sql.append("WHERE I_BPartner.PO_PaymentTerm_Name IS NOT NULL ");
		
		if (DB.isOracle()) {
			sql.append(" AND ROWNUM=1");
		}
		
		sql.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Found Contact=" + no);		


		//	Existing Contact ? Match Name
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append(" SET AD_User_ID=(SELECT AD_User_ID FROM AD_User c")
				.append(" WHERE I_BPartner.ContactName=c.Name AND I_BPartner.C_BPartner_ID=c.C_BPartner_ID AND c.AD_Client_ID in (I_BPartner.AD_Client_ID,0)) ")
				.append(" WHERE C_BPartner_ID IS NOT NULL AND AD_User_ID IS NULL AND ContactName IS NOT NULL")
				.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Found Contact=" + no);



		//	Existing Location ? Exact Match

		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET C_BPartner_Location_ID=(SELECT C_BPartner_Location_ID")
				.append(" FROM C_BPartner_Location bpl INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)")
				.append(" WHERE I_BPartner.C_BPartner_ID=bpl.C_BPartner_ID AND bpl.AD_Client_ID=I_BPartner.AD_Client_ID")
				.append(" AND trim(I_BPartner.Address1)=trim(l.Address1) AND trim(I_BPartner.Address2)=trim(l.Address2)") //expert
				.append(" AND trim(I_BPartner.City)=trim(l.City) AND trim(I_BPartner.Postal)=trim(l.Postal) AND trim(I_BPartner.Postal_Add)=trim(l.Postal_Add)") //expert
				.append(" AND I_BPartner.C_Region_ID=l.C_Region_ID AND I_BPartner.C_Country_ID=l.C_Country_ID) ")
				.append("WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL")
				.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());

		log.fine("Found Location=" + no);
		
		// TownCouncil
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET EXME_TOWNCounCil_id=(SELECT EXME_TOWNCounCil_id FROM EXME_TOWNCounCil ds")
				.append(" WHERE trim(I_BPartner.city)=trim(ds.Name) AND ds.AD_Client_ID IN (I_BPartner.AD_Client_ID,0) ");
		
		if (DB.isOracle()) {
			sql.append("AND ROWNUM=1) ");
		} else if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			sql.append(") ");
		}
		
		sql.append(" WHERE I_BPartner.city IS NOT NULL  ");
		sql.append(" AND I_IsImported='N'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(), get_TrxName());
		

		log.fine("Found TownCouncil=" + no);	

		//	-------------------------------------------------------------------
		int noInsert = 0;
		int noUpdate = 0;
		
		ResultSet rs = null;
		Statement pstmt = null;
		PreparedStatement pstmt_insertLocation = null;
		PreparedStatement pstmt_insertBPLocation = null;
		PreparedStatement pstmt_insertBPContact = null;
		PreparedStatement pstmt_setImported = null;

		//	Go through Records
		sql = new StringBuilder ("SELECT I_BPartner_ID, C_BPartner_ID,")
				.append("C_BPartner_Location_ID,COALESCE (Address1,Address2,City),")
				.append("AD_User_ID,ContactName ")
				.append("FROM I_BPartner ")
				.append("WHERE I_IsImported='N' ").append(clientCheck);

		//Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);

		try {
		
			/**	Insert BPartner
			PreparedStatement pstmt_insertBPartner = conn.prepareStatement
				("INSERT INTO C_BPartner (C_BPartner_ID,"
				+ "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
				+ "Value,Name,Name2,Description,DUNS,TaxID,NAICS,C_BP_Group_ID,IsSummary,NoExterior,NoInterior,C_Region_ID) "
				+ "SELECT ?,"
				+ "AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,"
				+ "Value,Name,Name2,Description,DUNS,TaxID,NAICS,C_BP_Group_ID,'N',NoExterior,NoInterior,C_Region_ID "
				+ "FROM I_BPartner "
				+ "WHERE I_BPartner_ID=?");
			 */	

			//	Update BPartner
			StringBuilder s_pstmt_updateBPartner = new StringBuilder(" UPDATE C_BPartner ")
				.append("SET (Value,Name,Name2,Description,DUNS,TaxID,NAICS,C_BP_Group_ID,Updated,UpdatedBy,paymentRule,InvoiceRule,IsVendor,IsCustomer") 
				.append(",PaymentRulePO, M_DiscountSchema_ID, PO_DiscountSchema_ID, M_PriceList_ID, PO_PriceList_ID, C_PaymentTerm_ID,PO_PaymentTerm_ID,SO_CreditLimit,IsNational)=")
				.append("(SELECT Value,Name,NVL(I_BPartner.Name2,''),Description,DUNS,TaxID,NAICS,C_BP_Group_ID,SysDate,UpdatedBy,PaymentRule,InvoiceRule,IsVendor,IsCustomer")
				.append(",PaymentRulePO, M_DiscountSchema_ID, PO_DiscountSchema_ID, M_PriceList_ID, PO_PriceList_ID, C_PaymentTerm_ID,PO_PaymentTerm_ID,SO_CreditLimit,IsNational")
				.append(" FROM I_BPartner")
				.append(" WHERE I_BPartner_ID=?) ")
				.append("WHERE C_BPartner_ID=?");
			
//			Update BPartner_CTE
			StringBuilder s_pstmt_updateBPartnerCte = new StringBuilder(" UPDATE C_BPartner_Cte ")
			.append("SET (Updated,UpdatedBy,paymentRule,InvoiceRule,M_DiscountSchema_ID,") 
			.append("flatdiscount, M_PriceList_ID, C_PaymentTerm_ID,SO_CreditLimit)=")
			.append("(SELECT SysDate,UpdatedBy,PaymentRule,InvoiceRule,M_DiscountSchema_ID,")
			.append("flatdiscount, M_PriceList_ID, C_PaymentTerm_ID,SO_CreditLimit")
			.append(" FROM I_BPartner")
			.append(" WHERE I_BPartner_ID=?) ")
			.append("WHERE C_BPartner_ID=?");

			//	Insert Location
			StringBuilder s_pstmt_insertLocation = new StringBuilder("INSERT INTO C_Location (C_Location_ID,")
				.append("AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,")
				.append("Address2,Address3,City,Postal,Postal_Add,C_Country_ID,C_Region_ID, ")
				.append("NumExt, NumIn, EXME_TOWNCounCil_id) ")
				.append("SELECT ?,")
				.append("AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,")
				.append("Address1,Address2,City,Postal,Postal_Add,C_Country_ID,C_Region_ID, ")
				.append("NoExterior, NoInterior, EXME_TOWNCounCil_id ")
				.append("FROM I_BPartner ")
				.append("WHERE I_BPartner_ID = ? ");

			//	PreparedStatement pstmt_updateLocation = conn.prepareStatement

			//		("");


			//	Insert BP Location
			StringBuilder s_pstmt_insertBPLocation = new StringBuilder("INSERT INTO C_BPartner_Location (C_BPartner_Location_ID,")
				.append("AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,")
				.append("Name,IsBillTo,IsShipTo,IsPayFrom,IsRemitTo,")
				.append("Phone,Phone2,Fax, C_BPartner_ID,C_Location_ID,NoExterior,C_Region_ID) ")
				.append("SELECT ?,")//insertando C_BPartner_Location_ID
				.append("AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,")
				.append("City,'Y','Y','Y','N',")
				.append("Phone,Phone2,Fax, ?,? ,NoExterior,C_Region_ID ")
				.append("FROM I_BPartner ")
				.append("WHERE I_BPartner_ID = ? ");

			//	PreparedStatement pstmt_updateBPLocation = conn.prepareStatement

			//		("");


			//	Insert Contact
			StringBuilder s_pstmt_insertBPContact = new StringBuilder("INSERT INTO AD_User (AD_User_ID,")
				.append("AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,")
				.append("C_BPartner_ID,C_BPartner_Location_ID,C_Greeting_ID,")
				.append("Name,Title,Description,Comments,Phone,Phone2,Fax,EMail,Birthday) ")
				.append("SELECT ?,")
				.append("AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,")
				.append("?,?,C_Greeting_ID,")
				.append("ContactName,Title,ContactDescription,Comments,Phone,Phone2,Fax,EMail,Birthday ")
				.append("FROM I_BPartner ")
				.append("WHERE I_BPartner_ID=?");


			//	Update Contact
			StringBuilder s_pstmt_updateBPContact = new StringBuilder("UPDATE AD_User ")
				.append("SET (C_Greeting_ID,")
				.append("Name,Title,Description,Comments,Phone,Phone2,Fax,EMail,Birthday,Updated,UpdatedBy)=")
				.append("(SELECT C_Greeting_ID,")
				.append("ContactName,Title,ContactDescription,Comments,Phone,Phone2,Fax,EMail,Birthday,SysDate,UpdatedBy")
				.append(" FROM I_BPartner WHERE I_BPartner_ID = ?) ")
				.append("WHERE AD_User_ID = ? ");

			//	Set Imported = Y
			StringBuilder s_pstmt_setImported = new StringBuilder("UPDATE I_BPartner SET I_IsImported='Y',")
				.append(" C_BPartner_ID=?, C_BPartner_Location_ID=?, AD_User_ID = ?, ")
				.append(" Updated=SysDate, Processed='Y' WHERE I_BPartner_ID = ? ");

			//
			pstmt = DB.createStatement();//, get_TrxName());

			rs = pstmt.executeQuery(sql.toString());
			DB.commit(true, get_TrxName());

			while (rs.next()) {
				int I_BPartner_ID = rs.getInt(1);
				int C_BPartner_ID = rs.getInt(2);
				boolean newBPartner = C_BPartner_ID == 0;
				int C_BPartner_Location_ID = rs.getInt(3);
				boolean newLocation = rs.getString(4) != null;
				int AD_User_ID = rs.getInt(5);
				boolean newContact =  rs.getString(6) != null;

				log.fine( "I_BPartner_ID=" + I_BPartner_ID
						+ ", C_BPartner_ID=" + C_BPartner_ID
						+ ", C_BPartner_Location_ID=" + C_BPartner_Location_ID + " create=" + newLocation
						+ ", AD_User_ID=" + AD_User_ID + " create=" + newContact);

				//	****	Create/Update BPartner
				if (newBPartner) {	//	Insert new BPartner
				
					try {
						X_I_BPartner iBP = new X_I_BPartner(getCtx(), I_BPartner_ID, get_TrxName()); //expert

						MBPartner bp = new MBPartner(iBP);
						//Se setean campos faltantes en ticket 05803. Jesus Cantu.
						bp.setSO_CreditLimit(iBP.getSO_CreditLimit());
						bp.setPaymentRule(iBP.getPaymentRule());
						bp.setC_PaymentTerm_ID(iBP.getC_PaymentTerm_ID());
						bp.setAD_Language(Env.getAD_Language(getCtx()));
						bp.setM_PriceList_ID(iBP.getM_PriceList_ID());
						bp.setM_DiscountSchema_ID(iBP.getM_DiscountSchema_ID());
						bp.setNumberEmployees(0);
						bp.setFlatDiscount(new BigDecimal(iBP.getFlatDiscount()));
						bp.setDocumentCopies(0);
						
						//Si es proveedor
						bp.setPO_PriceList_ID(iBP.getPO_PriceList_ID()); //Lista de Precios de Proovedor
						bp.setPO_PaymentTerm_ID(iBP.getPO_PaymentTerm_ID()); //Condiciones de Pago
						bp.setPO_DiscountSchema_ID(iBP.getPO_DiscountSchema_ID()); //Esquema de Descuentos de Proveedor
						bp.setPaymentRulePO(iBP.getPaymentRulePO()); //Reglas de Pago de Proveedor
                        bp.setIsNational(iBP.isNational());
						
						if (bp.save(get_TrxName())) {
							C_BPartner_ID = bp.getC_BPartner_ID();
							log.finest("Insert BPartner");
							noInsert++;
						}
						
						//Salvar el Discount_id y el PriceListId que por alguna razon no lo salva
						//el motor. Siempre y cuando sean mayor a cero.
						
						StringBuilder s_pstmt_updateBPartnerPL = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
						StringBuilder s_pstmt_updateBPartnerPLCte = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
						boolean blEjecutar = true;
						
						if (iBP.getM_PriceList_ID() > 0 && iBP.getM_DiscountSchema_ID() > 0) {
							s_pstmt_updateBPartnerPL.append("UPDATE C_BPartner set M_PriceList_ID = ");
							s_pstmt_updateBPartnerPL.append(iBP.getM_PriceList_ID());
							s_pstmt_updateBPartnerPL.append(", M_DiscountSchema_ID = ");
							s_pstmt_updateBPartnerPL.append(iBP.getM_DiscountSchema_ID());
							s_pstmt_updateBPartnerPL.append(" WHERE C_BPartner_ID=?");
							
							//Actualizar ProductCte
							s_pstmt_updateBPartnerPLCte.append("UPDATE C_BPartner_Cte set M_PriceList_ID = ");
							s_pstmt_updateBPartnerPLCte.append(iBP.getM_PriceList_ID());
							s_pstmt_updateBPartnerPLCte.append(", M_DiscountSchema_ID = ");
							s_pstmt_updateBPartnerPLCte.append(iBP.getM_DiscountSchema_ID());
							s_pstmt_updateBPartnerPLCte.append(" WHERE C_BPartner_ID=?");
							
						} else if (iBP.getM_PriceList_ID() > 0 && iBP.getM_DiscountSchema_ID() < 1) {
							s_pstmt_updateBPartnerPL.append("UPDATE C_BPartner set M_PriceList_ID = ");
							s_pstmt_updateBPartnerPL.append(iBP.getM_PriceList_ID());
							s_pstmt_updateBPartnerPL.append(" WHERE C_BPartner_ID=?");
							
							//Actualizar ProductCte
							s_pstmt_updateBPartnerPLCte.append("UPDATE C_BPartner_Cte set M_PriceList_ID = ");
							s_pstmt_updateBPartnerPLCte.append(iBP.getM_PriceList_ID());
							s_pstmt_updateBPartnerPLCte.append(" WHERE C_BPartner_ID=?");
							
						} else if (iBP.getM_DiscountSchema_ID() > 0 && iBP.getM_PriceList_ID() < 1) {
							s_pstmt_updateBPartnerPL.append("UPDATE C_BPartner set M_DiscountSchema_ID = ");
							s_pstmt_updateBPartnerPL.append(iBP.getM_DiscountSchema_ID());
							s_pstmt_updateBPartnerPL.append(" WHERE C_BPartner_ID=?");
							
							//Actualizar ProductCte
							s_pstmt_updateBPartnerPLCte.append("UPDATE C_BPartner_Cte set M_DiscountSchema_ID = ");
							s_pstmt_updateBPartnerPLCte.append(iBP.getM_DiscountSchema_ID());
							s_pstmt_updateBPartnerPLCte.append(" WHERE C_BPartner_ID=?");
							
							//Si es proveedor
						} else if (iBP.getPO_PriceList_ID() > 0 && iBP.getPO_DiscountSchema_ID() > 0) {
							s_pstmt_updateBPartnerPL.append("UPDATE C_BPartner set PO_PriceList_ID = ");
							s_pstmt_updateBPartnerPL.append(iBP.getPO_PriceList_ID());
							s_pstmt_updateBPartnerPL.append(", PO_DiscountSchema_ID = ");
							s_pstmt_updateBPartnerPL.append(iBP.getPO_DiscountSchema_ID());
							s_pstmt_updateBPartnerPL.append(" WHERE C_BPartner_ID=?");
							
							//Actualizar ProductCte
							s_pstmt_updateBPartnerPLCte.append("UPDATE C_BPartner_Cte set M_PriceList_ID = ");
							s_pstmt_updateBPartnerPLCte.append(iBP.getPO_PriceList_ID());
							s_pstmt_updateBPartnerPLCte.append(", M_DiscountSchema_ID = ");
							s_pstmt_updateBPartnerPLCte.append(iBP.getPO_DiscountSchema_ID());
							s_pstmt_updateBPartnerPLCte.append(" WHERE C_BPartner_ID=?");
							
						} else if (iBP.getPO_PriceList_ID() > 0 && iBP.getPO_DiscountSchema_ID() < 1) {
							s_pstmt_updateBPartnerPL.append("UPDATE C_BPartner set PO_PriceList_ID = ");
							s_pstmt_updateBPartnerPL.append(iBP.getPO_PriceList_ID());
							s_pstmt_updateBPartnerPL.append(" WHERE C_BPartner_ID=?");
							
							//Actualizar ProductCte
							s_pstmt_updateBPartnerPLCte.append("UPDATE C_BPartner_Cte set M_PriceList_ID = ");
							s_pstmt_updateBPartnerPLCte.append(iBP.getPO_PriceList_ID());
							s_pstmt_updateBPartnerPLCte.append(" WHERE C_BPartner_ID=?");
							
						} else if (iBP.getPO_DiscountSchema_ID() > 0 && iBP.getPO_PriceList_ID() < 1) {
							s_pstmt_updateBPartnerPL.append("UPDATE C_BPartner set PO_DiscountSchema_ID = ");
							s_pstmt_updateBPartnerPL.append(iBP.getPO_DiscountSchema_ID());
							s_pstmt_updateBPartnerPL.append(" WHERE C_BPartner_ID=?");
							
							//Actualizar ProductCte
							s_pstmt_updateBPartnerPLCte.append("UPDATE C_BPartner_Cte set M_DiscountSchema_ID = ");
							s_pstmt_updateBPartnerPLCte.append(iBP.getPO_DiscountSchema_ID());
							s_pstmt_updateBPartnerPLCte.append(" WHERE C_BPartner_ID=?");
							
						} else {
							//Los dos vienen vacios, no ejecutar consulta.
							blEjecutar = false;
						}
						
						
						if (blEjecutar) {
						
							no =
									DB.executeUpdate(
											s_pstmt_updateBPartnerPL.toString(),
												new Object[]{C_BPartner_ID},
												get_TrxName()
										);
								
							log.finest( "Update BPartner PriceList and DiscountSchema = " + no);
							
							
							no =
									DB.executeUpdate(
											s_pstmt_updateBPartnerPLCte.toString(),
												new Object[]{C_BPartner_ID},
												get_TrxName()
										);
								
							log.finest( "Update BPartnerCte PriceList and DiscountSchema = " + no);	
						}
					

					} catch(Exception ex) {
						log.log(Level.SEVERE, "Error: creating BPartner: ", ex);
						sql = new StringBuilder ("UPDATE I_BPartner i ")
						.append("SET I_IsImported='E', I_ErrorMsg = COALESCE (I_ErrorMsg, '') ||  ':  ").append(ex.getMessage().toString())
						.append("'  WHERE I_BPartner_ID=").append(I_BPartner_ID);

						DB.executeUpdate(sql.toString(), get_TrxName());

						continue;

					}
				} else {				//	Update existing BPartner
				
						no =
							DB.executeUpdate(
									s_pstmt_updateBPartner.toString(),
										new Object[]{I_BPartner_ID, C_BPartner_ID},
										get_TrxName()
								);
						
						log.finest( "Update BPartner = " + no);
						
						//Si hay error abortamos.
						if (no <= 0) {
							DB.rollback(true, get_TrxName());
							log.log(Level.SEVERE, "Error: Update BPartner - ");
							sql = new StringBuilder ("UPDATE I_BPartner i ")
								.append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') || ").append(DB.TO_STRING("BPartner not new. Error Updating BPartner: " + C_BPartner_ID ))
								.append(" WHERE I_BPartner_ID=").append(I_BPartner_ID);

							DB.executeUpdate(sql.toString(), get_TrxName());
							noUpdate--;
							continue;
							
						}
						
						//update updateBPartnerCte - Actualizamos el registro en C_BPartner_Cte
						no = DB.executeUpdate(
								s_pstmt_updateBPartnerCte.toString(),
								new Object[]{I_BPartner_ID, C_BPartner_ID},
								get_TrxName()
						);
						
						noUpdate++;

				}

				//	****	Create/Update BPartner Location
				//if (C_BPartner_Location_ID != 0) {		    //	Update Location
				
				//} else 
				if (newLocation && C_BPartner_Location_ID == 0) {					//	New Location
				
					int C_Location_ID = 0;

					try {
						C_Location_ID = DB.getNextID(m_AD_Client_ID, "C_Location", get_TrxName());
						if (C_Location_ID <= 0) {
							throw new DBException("No NextID (" + C_Location_ID + ")");
						}
						
						pstmt_insertLocation = DB.prepareStatement(s_pstmt_insertLocation.toString(), get_TrxName());
						pstmt_insertLocation.setInt(1, C_Location_ID);
						pstmt_insertLocation.setInt(2, I_BPartner_ID);

						//
						no = pstmt_insertLocation.executeUpdate();

						log.finest( "Insert Location = " + no);

					} catch (SQLException ex) {
						log.log(Level.SEVERE, "Error Inserting Location - ", ex);

						DB.rollback(true, get_TrxName());

						noInsert--;

						sql = new StringBuilder ("UPDATE I_BPartner i ")
						        .append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') ||").append(DB.TO_STRING("Insert Location: " + ex.toString()))
								.append(" WHERE I_BPartner_ID=").append(I_BPartner_ID);

						DB.executeUpdate(sql.toString(), get_TrxName());
						continue;
					}
					//
					try {
						C_BPartner_Location_ID = DB.getNextID(m_AD_Client_ID, "C_BPartner_Location", get_TrxName()); //expert

						if (C_BPartner_Location_ID <= 0) {
							throw new DBException("No NextID (" + C_BPartner_Location_ID + ")");
						}

						pstmt_insertBPLocation = DB.prepareStatement(s_pstmt_insertBPLocation.toString(), get_TrxName());
						pstmt_insertBPLocation.setInt(1, C_BPartner_Location_ID);
						pstmt_insertBPLocation.setInt(2, C_BPartner_ID);
						pstmt_insertBPLocation.setInt(3, C_Location_ID);
						pstmt_insertBPLocation.setInt(4, I_BPartner_ID);

						//
						no = pstmt_insertBPLocation.executeUpdate();

						log.finest( "Insert BP Location = " + no);

					} catch (Exception ex) {
						log.log(Level.SEVERE, "Error Inserting BPLocation - ", ex);

						DB.rollback(true, get_TrxName());

						noInsert--;

						sql = new StringBuilder ("UPDATE I_BPartner i ")
								.append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') ||").append(DB.TO_STRING("Insert BPLocation: " + ex.toString()))
								.append(" WHERE I_BPartner_ID=").append(I_BPartner_ID);

						DB.executeUpdate(sql.toString(), get_TrxName());
						continue;
					}
				}

				//	****	Create/Update Contact
				if (AD_User_ID != 0) {

					no =
							DB.executeUpdate(
									s_pstmt_updateBPContact.toString(),
										new Object[]{I_BPartner_ID, AD_User_ID},
										get_TrxName()
								);

					log.finest( "Update BP Contact = " + no);
					
					if (no <= 0) {
						DB.rollback(true, get_TrxName());
						log.log(Level.SEVERE, "Error Updating BP Contact - ");
						sql = new StringBuilder ("UPDATE I_BPartner i ")
							.append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') || ").append(DB.TO_STRING("Error Updating BPartner Contact: " + AD_User_ID))
							.append(" WHERE I_BPartner_ID=").append(I_BPartner_ID);

						DB.executeUpdate(sql.toString(), get_TrxName());
						//noUpdate--;
					}
						
				} else if (newContact) {				//	New Contact
				
					try {

						AD_User_ID = DB.getNextID(m_AD_Client_ID, "AD_User", get_TrxName()); //expert

						if (AD_User_ID <= 0) {
							throw new DBException("No NextID (" + AD_User_ID + ")");
						}

						pstmt_insertBPContact = DB.prepareStatement(s_pstmt_insertBPContact.toString(), get_TrxName());
						pstmt_insertBPContact.setInt(1, AD_User_ID);
						pstmt_insertBPContact.setInt(2, C_BPartner_ID);

						if (C_BPartner_Location_ID == 0) {
							pstmt_insertBPContact.setNull(3, Types.NUMERIC);
						} else {
							pstmt_insertBPContact.setInt(3, C_BPartner_Location_ID);
						}

						pstmt_insertBPContact.setInt(4, I_BPartner_ID);

						//
						no = pstmt_insertBPContact.executeUpdate();

						log.finest( "Insert BP Contact = " + no);

					} catch (Exception ex) {
						log.log(Level.SEVERE, "Error: Inserting BPContact", ex);

						DB.rollback(true, get_TrxName());

						noInsert--;

						sql = new StringBuilder ("UPDATE I_BPartner i ")
								.append("SET I_IsImported='E', I_ErrorMsg=COALESCE (I_ErrorMsg, '') || ").append(DB.TO_STRING("Update BP Contact: " + ex.toString()))
								.append(" WHERE I_BPartner_ID=").append(I_BPartner_ID);

						DB.executeUpdate(sql.toString(), get_TrxName());
 
						continue;
					}
				}

				//	Update I_Product
				pstmt_setImported = DB.prepareStatement(s_pstmt_setImported.toString(), get_TrxName());

				pstmt_setImported.setInt(1, C_BPartner_ID);

				if (C_BPartner_Location_ID == 0) {
					pstmt_setImported.setNull(2, Types.NUMERIC);
				} else {
					pstmt_setImported.setInt(2, C_BPartner_Location_ID);
				}

				if (AD_User_ID == 0) {
					pstmt_setImported.setNull(3, Types.NUMERIC);
				} else { 
					pstmt_setImported.setInt(3, AD_User_ID);
				}

				pstmt_setImported.setInt(4, I_BPartner_ID);

				no = pstmt_setImported.executeUpdate();

				DB.commit(true, get_TrxName());
			}	//	while


		} catch (SQLException e) {
			throw new Exception ("ImportBPartner.doIt", e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}

			if (pstmt_insertLocation != null) {
				pstmt_insertLocation.close();
			}

			if (pstmt_insertBPLocation != null) {
				pstmt_insertBPLocation.close();
			}

			if (pstmt_insertBPContact != null) {
				pstmt_insertBPContact.close();
			}

			if (pstmt_setImported != null) {
				pstmt_setImported.close();
			}
		}

		//	Set Error to indicator to not imported
		sql = new StringBuilder ("UPDATE I_BPartner ")
				.append("SET I_IsImported='N', Updated=SysDate ")
				.append("WHERE I_IsImported<>'Y'").append(clientCheck);

		no = DB.executeUpdate(sql.toString(),get_TrxName());

		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@C_BPartner_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@C_BPartner_ID@: @Updated@");

		return "";
	}	//	doIt
}	//	ImportBPartner