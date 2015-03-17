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

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.hospital.utils.Datos;
import org.compiere.process.DocumentTypeVerify;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;
import org.compiere.util.UtilsDbPort;


/**
 * Initial Setup Model
 *
 * @author Jorg Janke
 * @version $Id: MSetup.java,v 1.4 2006/08/11 02:26:22 mrojas Exp $
 */
public final class MSetup
{
	/**
	 *  Constructor
	 *  @param ctx context
	 *  @param WindowNo window
	 */
	public MSetup(Properties ctx, boolean isParentOrg, int WindowNo) {
		m_ctx = new Properties(ctx); // copy
		m_lang = Env.getAD_Language(m_ctx);
		m_WindowNo = WindowNo;
		this.isParentOrg = isParentOrg;
	}   //  MSetup

	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());

	private Trx				m_trx = Trx.get(Trx.createTrxName("Setup"), true);
	private Properties      m_ctx;
	private String          m_lang;
	private int             m_WindowNo;
	private StringBuffer    m_info;
	//
	private String          m_clientName;
//	private String          m_orgName;
	//
	private String          m_stdColumns = "AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy";
	private String          m_stdValues;
	private String          m_stdValuesOrg;
	//
	private NaturalAccountMap<String,MElementValue> m_nap = null;
	//
	private MClient			m_client;
	private MOrg			m_org;
	private MAcctSchema		m_as;
	//
	private int     		AD_User_ID;
	private String  		AD_User_Name;
	private int     		AD_User_U_ID;
	private String  		AD_User_U_Name;
	private MCalendar		m_calendar;
	private int     		m_AD_Tree_Account_ID;
	private int     		C_Cycle_ID;
	//
	private boolean         m_hasProject = false;
	private boolean         m_hasMCampaign = false;
	private boolean         m_hasSRegion = false;
	private boolean         m_hasProductClass = false;
	/** Account Creation OK		*/
	private boolean m_accountsOK = false;
	private int M_PriceList_ID = 0;
	private int M_PriceList_Version_ID = 0;
	
	private Properties customProps;
	private int C_BPartner_ID = 0;
	private double taxRate = 0;
	private boolean isParentOrg = false;
	private int ctaPacDocId = 0;
	private boolean elecAcct = false;
	
	/**
	 *  Create Client Info.
	 *  - Client, Trees, Org, Role, User, User_Role
	 *  @param clientName client name
	 *  @param orgName org name
	 *  @param userClient user id client
	 *  @param userOrg user id org
	 *  @return true if created
	 */
	public boolean createClient (String clientName, String clientValue, String orgName, String orgValue,
		String userClient, String userOrg, String areaType, String tipoCliente)//Expert: Lama
	{
		log.info(clientName);
		m_trx.start();
		
		//  info header
		m_info = new StringBuffer();
		//  Standarc columns
		String name = null;
		String sql = null;
		int no = 0;
		
		if (StringUtils.isEmpty(clientValue)) {
			clientValue = clientName;
		}

		if (StringUtils.isEmpty(orgValue)) {
			orgValue = orgName;
		}

		/**
		 *  Create Client
		 */
		name = clientName;
		if (name == null || name.length() == 0)
			name = "newClient";
		m_clientName = name;
		m_client = new MClient(m_ctx, 0, true, m_trx.getTrxName());
		m_client.setValue(clientValue);
		m_client.setName(m_clientName);
		m_client.setDescription(m_clientName);
		
		if (tipoCliente != null) {
			m_client.setTipoCliente(tipoCliente);
		}
		if (!m_client.save())
		{
			String err = "Client NOT created";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		int AD_Client_ID = m_client.getAD_Client_ID();
		Env.setContext(m_ctx, m_WindowNo, "AD_Client_ID", AD_Client_ID);
		Env.setContext(m_ctx, "#AD_Client_ID", AD_Client_ID);

		//	Standard Values
		m_stdValues = String.valueOf(AD_Client_ID) + ",0,'Y',"
				+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",0,"
				+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",0";
		//  Info - Client
		m_info.append(Msg.translate(m_lang, "AD_Client_ID")).append("=").append(name).append("\n");
		
		/*
		 * Las secuencias se crean a nivel de organizacion
		 */
//		//	Setup Sequences
//		if (!MSequence.checkClientSequences (m_ctx, AD_Client_ID, m_trx.getTrxName()))
//		{
//			String err = "Sequences NOT created";
//			log.log(Level.SEVERE, err);
//			m_info.append(err);
//			m_trx.rollback();
//			m_trx.close();
//			return false;
//		}
		
		//  Trees and Client Info
		if (!m_client.setupClientInfo(m_lang))
		{
			String err = "Client Info NOT created";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		m_AD_Tree_Account_ID = m_client.getSetup_AD_Tree_Account_ID();

		/**
		 *  Create Org
		 */
		name = orgName;
		if (name == null || name.length() == 0)
			name = "newOrg";
		m_org = new MOrg (m_client, name); 
		
		//Seteamos la descripcion
		m_org.setDescription(name);
		
		if (!m_org.save())
		{
			String err = "Organization NOT created";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		Env.setContext(m_ctx, m_WindowNo, "AD_Org_ID", getAD_Org_ID());
		Env.setContext(m_ctx, "#AD_Org_ID", getAD_Org_ID());
		m_stdValuesOrg = AD_Client_ID + "," + getAD_Org_ID() + ",'Y',"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",0,"+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + ",0";
		//  Info
		m_info.append(Msg.translate(m_lang, "AD_Org_ID")).append("=").append(name).append("\n");

		/**
		 *  Create Roles
		 *  - Admin
		 *  - User
		 */
		name = m_clientName + " Admin";
		MRole admin = new MRole(m_ctx, 0, m_trx.getTrxName());
		admin.setClientOrg(m_client);
		admin.setName(name);
		admin.setUserLevel(MRole.USERLEVEL_ClientPlusOrganization);
		admin.setPreferenceType(MRole.PREFERENCETYPE_Client);
		admin.setIsShowAcct(true);
		if (!admin.save())
		{
			String err = "Admin Role A NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		
		//	OrgAccess x, 0
		MRoleOrgAccess adminClientAccess = new MRoleOrgAccess (admin, 0);
		if (!adminClientAccess.save())
			log.log(Level.SEVERE, "Admin Role_OrgAccess 0 NOT created");
		//  OrgAccess x,y
		MRoleOrgAccess adminOrgAccess = new MRoleOrgAccess (admin, m_org.getAD_Org_ID());
		if (!adminOrgAccess.save())
			log.log(Level.SEVERE, "Admin Role_OrgAccess NOT created");
		
		//  Info - Admin Role
		m_info.append(Msg.translate(m_lang, "AD_Role_ID")).append("=").append(name).append("\n");

		//
		name = m_clientName + " User";
		MRole user = new MRole (m_ctx, 0, m_trx.getTrxName());
		user.setClientOrg(m_client);
		user.setName(name);
		if (!user.save())
		{
			String err = "User Role A NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  OrgAccess x,y
		MRoleOrgAccess userOrgAccess = new MRoleOrgAccess (user, m_org.getAD_Org_ID());
		if (!userOrgAccess.save())
			log.log(Level.SEVERE, "User Role_OrgAccess NOT created");
		
		//  Info - Client Role
		m_info.append(Msg.translate(m_lang, "AD_Role_ID")).append("=").append(name).append("\n");

		
		//Funcionalidad Innecesaria, se comenta por Jesus Cantu
		//30 Enero 2014. 
		/**
		 *  Create Users
		 *  - Client
		 *  - Org
		 */
		/*name = userClient;
		if (name == null || name.length() == 0)
			name = m_clientName + "Client";
		AD_User_ID = getNextID(AD_Client_ID, "AD_User");
		AD_User_Name = name;
		name = DB.TO_STRING(name);
		sql = "INSERT INTO AD_User(" + m_stdColumns + ",AD_User_ID,"
			+ "Name,Description,Password)"
			+ " VALUES (" + m_stdValues + "," + AD_User_ID + ","
			+ name + "," + name + "," + DB.TO_STRING(UtilsDbPort.encrypt(AD_User_Name)) + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
		{
			String err = "Admin User NOT inserted - " + AD_User_Name;
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  Info
		m_info.append(Msg.translate(m_lang, "AD_User_ID")).append("=").append(AD_User_Name).append("/").append(AD_User_Name).append("\n");

		name = userOrg;
		if (name == null || name.length() == 0)
			name = m_clientName + "Org";
		AD_User_U_ID = getNextID(AD_Client_ID, "AD_User");
		AD_User_U_Name = name;
		name = DB.TO_STRING(name);
		sql = "INSERT INTO AD_User(" + m_stdColumns + ",AD_User_ID,"
			+ "Name,Description,Password)"
			+ " VALUES (" + m_stdValues + "," + AD_User_U_ID + ","
			+ name + "," + name + "," + DB.TO_STRING(UtilsDbPort.encrypt(AD_User_U_Name)) + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
		{
			String err = "Org User NOT inserted - " + AD_User_U_Name;
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  Info
		m_info.append(Msg.translate(m_lang, "AD_User_ID")).append("=").append(AD_User_U_Name).append("/").append(AD_User_U_Name).append("\n");

		
		 //  Create User-Role
		
		//  ClientUser          - Admin & User
		sql = "INSERT INTO AD_User_Roles(" + m_stdColumns + ",AD_User_ID,AD_Role_ID)"
			+ " VALUES (" + m_stdValues + "," + AD_User_ID + "," + admin.getAD_Role_ID() + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "UserRole ClientUser+Admin NOT inserted");
		sql = "INSERT INTO AD_User_Roles(" + m_stdColumns + ",AD_User_ID,AD_Role_ID)"
			+ " VALUES (" + m_stdValues + "," + AD_User_ID + "," + user.getAD_Role_ID() + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "UserRole ClientUser+User NOT inserted");
		//  OrgUser             - User
		sql = "INSERT INTO AD_User_Roles(" + m_stdColumns + ",AD_User_ID,AD_Role_ID)"
			+ " VALUES (" + m_stdValues + "," + AD_User_U_ID + "," + user.getAD_Role_ID() + ")";
		no = DB.executeUpdate(sql, m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "UserRole OrgUser+Org NOT inserted");

		//	Processors
		MAcctProcessor ap = new MAcctProcessor(m_client, AD_User_ID);
		ap.setIsActive(false);
		ap.save();
		
		MRequestProcessor rp = new MRequestProcessor (m_client, AD_User_ID);
		rp.setIsActive(false);
		rp.save();*/
		
		// Expert : Lama .-Creates initial area and the service stations associated to administrator role
		
		// 13 de Junio del 2012. Jcantu. se evita crear el area y estacion de servicio con el mismo 
		// nombre del cliente y organizacion, requerimiento para los wizards revisado con Helio Gutierrez.
		
		/*if(!createXPT(AD_Client_ID, admin.getAD_Role_ID(), orgName, areaType)){
			m_trx.rollback();
			m_trx.close();
			return false;
		}*/
		
		log.info("fini");
		return true;
	}   //  createClient



	/**************************************************************************
	 *  Create Accounting elements.
	 *  - Calendar
	 *  - Account Trees
	 *  - Account Values
	 *  - Accounting Schema
	 *  - Default Accounts
	 *
	 *  @param currency currency
	 *  @param hasProduct has product segment
	 *  @param hasBPartner has bp segment
	 *  @param hasProject has project segment
	 *  @param hasMCampaign has campaign segment
	 *  @param hasSRegion has sales region segment
	 *  @param AccountingFile file name of accounting file
	 *  @return true if created
	 */
	public boolean createAccounting(KeyNamePair currency,
		boolean hasProduct, boolean hasBPartner, boolean hasProject,
		boolean hasMCampaign, boolean hasSRegion, boolean hasProductClass,
		InputStream AccountingFile)
	{
		log.info(m_client.toString());
		//
		m_hasProject = hasProject;
		m_hasMCampaign = hasMCampaign;
		m_hasSRegion = hasSRegion;
		m_hasProductClass = hasProductClass;

		//  Standard variables
		m_info = new StringBuffer();
		String name = null;
		StringBuffer sqlCmd = null;
		int no = 0;

		/**
		 *  Create Calendar
		 */
		m_calendar = new MCalendar(m_client);
		if (!m_calendar.save())
		{
			String err = "Calendar NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  Info
		m_info.append(Msg.translate(m_lang, "C_Calendar_ID")).append("=").append(m_calendar.getName()).append("\n");

		if (m_calendar.createYear(m_client.getLocale()) == null)
			log.log(Level.SEVERE, "Year NOT inserted");

		//	Create Account Elements
		name = m_clientName + " " + Msg.translate(m_lang, "Account_ID");
		MElement element = new MElement (m_client, name, 
			MElement.ELEMENTTYPE_Account, m_AD_Tree_Account_ID);
		if (!element.save())
		{
			String err = "Acct Element NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		int C_Element_ID = element.getC_Element_ID();
		m_info.append(Msg.translate(m_lang, "C_Element_ID")).append("=").append(name).append("\n");

		//	Create Account Values
		m_nap = new NaturalAccountMap<String,MElementValue>(m_ctx, m_trx.getTrxName());
		String errMsg = m_nap.parseFile(AccountingFile);
		if (errMsg.length() != 0)
		{
			log.log(Level.SEVERE, errMsg);
			m_info.append(errMsg);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		if (m_nap.saveAccounts(getAD_Client_ID(), getAD_Org_ID(), C_Element_ID))
			m_info.append(Msg.translate(m_lang, "C_ElementValue_ID")).append(" # ").append(m_nap.size()).append("\n");
		else
		{
			String err = "Acct Element Values NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}

		int C_ElementValue_ID = m_nap.getC_ElementValue_ID("DEFAULT_ACCT");
		log.fine("C_ElementValue_ID=" + C_ElementValue_ID);

		/**
		 *  Create AccountingSchema
		 */
		m_as = new MAcctSchema (m_client, currency);
		//Save, if the client will use Electronic Accounting. //mguerrero
		m_as.setElectronicAccounting(isElecAcct());
		if (!m_as.save())
		{
			String err = "AcctSchema NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  Info
		m_info.append(Msg.translate(m_lang, "C_AcctSchema_ID")).append("=").append(m_as.getName()).append("\n");
		
		//En este momento ya se creo un M_CostElement con el Metodo De Costeo del Esquema Contable (A).
		//Ahora hay que crear el Estandar. Jesus Cantu 22 Nov 2013.
		
		MCostElement costeoEstandar = new MCostElement (m_ctx, 0, m_trx.getTrxName());
		
		costeoEstandar.setClientOrg(getAD_Client_ID(), 0);
		String name2 = MRefList.getListName(m_ctx, MCostElement.COSTINGMETHOD_AD_Reference_ID, MCostElement.COSTINGMETHOD_StandardCosting);
		if (name2 == null || name2.length() == 0)
			name2 = "Costeo Estándar";
		costeoEstandar.setName(name2);
		costeoEstandar.setCostElementType(MCostElement.COSTELEMENTTYPE_Material);
		costeoEstandar.setCostingMethod(MCostElement.COSTINGMETHOD_StandardCosting);
		
		if (costeoEstandar.save()) {
			m_info.append("MCosElelemnt Standard created").append("=").append(costeoEstandar.getName()).append("\n");
		} else {
			m_info.append("Error creating MCosElelemnt Standard").append("=").append(m_as.getName()).append("\n");
			log.log(Level.SEVERE, "Error creating MCosElelemnt Standard");
			m_info.append("Error creating MCosElelemnt Standard");
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		
		//Termina creacion de Costeo Estandar.

		/**
		 *  Create AccountingSchema Elements (Structure)
		 */
		String sql2 = null;
		if (Env.isBaseLanguage(m_lang, "AD_Reference"))	//	Get ElementTypes & Name
			sql2 = "SELECT Value, Name FROM AD_Ref_List WHERE AD_Reference_ID=181";
		else
			sql2 = "SELECT l.Value, t.Name FROM AD_Ref_List l, AD_Ref_List_Trl t "
				+ "WHERE l.AD_Reference_ID=181 AND l.AD_Ref_List_ID=t.AD_Ref_List_ID";
		//
		@SuppressWarnings("unused")
		int Element_OO=0, Element_AC=0, Element_PR=0, Element_BP=0, Element_PJ=0,
			Element_MC=0, Element_SR=0, Element_PC=0;
		try
		{
			int AD_Client_ID = m_client.getAD_Client_ID();
			PreparedStatement stmt = DB.prepareStatement(sql2, m_trx.getTrxName());
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				String ElementType = rs.getString(1);
				name = rs.getString(2);
				//
				String IsMandatory = null;
				String IsBalanced = "N";
				int SeqNo = 0;
				int C_AcctSchema_Element_ID = 0;

				if (ElementType.equals("OO"))
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					Element_OO = C_AcctSchema_Element_ID;
					IsMandatory = "Y";
					IsBalanced = "Y";
					SeqNo = 10;
				}
				else if (ElementType.equals("AC"))
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					Element_AC = C_AcctSchema_Element_ID;
					IsMandatory = "Y";
					SeqNo = 20;
				}
				else if (ElementType.equals("PR") && hasProduct)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					Element_PR = C_AcctSchema_Element_ID;
					IsMandatory = "N";
					SeqNo = 30;
				}
				else if (ElementType.equals("BP") && hasBPartner)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					Element_BP = C_AcctSchema_Element_ID;
					IsMandatory = "N";
					SeqNo = 40;
				}
				else if (ElementType.equals("PJ") && hasProject)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					Element_PJ = C_AcctSchema_Element_ID;
					IsMandatory = "N";
					SeqNo = 50;
				}
				else if (ElementType.equals("MC") && hasMCampaign)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					Element_MC = C_AcctSchema_Element_ID;
					IsMandatory = "N";
					SeqNo = 60;
				}
				else if (ElementType.equals("SR") && hasSRegion)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					Element_SR = C_AcctSchema_Element_ID;
					IsMandatory = "N";
					SeqNo = 70;
				}
				else if (ElementType.equals("PC") && hasProductClass)
				{
					C_AcctSchema_Element_ID = getNextID(AD_Client_ID, "C_AcctSchema_Element");
					Element_SR = C_AcctSchema_Element_ID;
					IsMandatory = "N";
					SeqNo = 80;
				}
				//	Not OT, LF, LT, U1, U2, AY

				if (IsMandatory != null)
				{
					sqlCmd = new StringBuffer ("INSERT INTO C_AcctSchema_Element(");
					sqlCmd.append(m_stdColumns).append(",C_AcctSchema_Element_ID,C_AcctSchema_ID,")
						.append("ElementType,Name,SeqNo,IsMandatory,IsBalanced) VALUES (");
					sqlCmd.append(m_stdValues).append(",").append(C_AcctSchema_Element_ID).append(",").append(m_as.getC_AcctSchema_ID()).append(",")
						.append("'").append(ElementType).append("','").append(name).append("',").append(SeqNo).append(",'")
						.append(IsMandatory).append("','").append(IsBalanced).append("')");
					no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
					if (no == 1)
						m_info.append(Msg.translate(m_lang, "C_AcctSchema_Element_ID")).append("=").append(name).append("\n");

					/** Default value for mandatory elements: OO and AC */
					if (ElementType.equals("OO"))
					{
						sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET Org_ID=");
						sqlCmd.append(getAD_Org_ID()).append(" WHERE C_AcctSchema_Element_ID=").append(C_AcctSchema_Element_ID);
						no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
						if (no != 1)
							log.log(Level.SEVERE, "Default Org in AcctSchamaElement NOT updated");
					}
					if (ElementType.equals("AC"))
					{
						sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET C_ElementValue_ID=");
						sqlCmd.append(C_ElementValue_ID).append(", C_Element_ID=").append(C_Element_ID);
						sqlCmd.append(" WHERE C_AcctSchema_Element_ID=").append(C_AcctSchema_Element_ID);
						no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
						if (no != 1)
							log.log(Level.SEVERE, "Default Account in AcctSchamaElement NOT updated");
					}
				}
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "Elements", e1);
			m_info.append(e1.getMessage());
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		//  Create AcctSchema


		//  Create GL Accounts
		m_accountsOK = true;
		sqlCmd = new StringBuffer ("INSERT INTO C_AcctSCHEMA_GL (");
		sqlCmd.append(m_stdColumns).append(",C_AcctSCHEMA_ID,"
			+ "USESUSPENSEBALANCING,SUSPENSEBALANCING_Acct,"
			+ "USESUSPENSEERROR,SUSPENSEERROR_Acct,"
			+ "USECURRENCYBALANCING,CURRENCYBALANCING_Acct,"
			+ "RETAINEDEARNING_Acct,INCOMESUMMARY_Acct,"
			+ "INTERCOMPANYDUETO_Acct,INTERCOMPANYDUEFROM_Acct,"
			+ "PPVOFFSET_Acct, CommitmentOffset_Acct) VALUES (");
		sqlCmd.append(m_stdValues).append(",").append(m_as.getC_AcctSchema_ID()).append(",")
			.append("'Y',").append(getAcct("SUSPENSEBALANCING_Acct")).append(",")
			.append("'Y',").append(getAcct("SUSPENSEERROR_Acct")).append(",")
			.append("'Y',").append(getAcct("CURRENCYBALANCING_Acct")).append(",");
		//  RETAINEDEARNING_Acct,INCOMESUMMARY_Acct,
		sqlCmd.append(getAcct("RETAINEDEARNING_Acct")).append(",")
			.append(getAcct("INCOMESUMMARY_Acct")).append(",")
		//  INTERCOMPANYDUETO_Acct,INTERCOMPANYDUEFROM_Acct)
			.append(getAcct("INTERCOMPANYDUETO_Acct")).append(",")
			.append(getAcct("INTERCOMPANYDUEFROM_Acct")).append(",")
			.append(getAcct("PPVOFFSET_Acct")).append(",")
			.append(getAcct("CommitmentOffset_Acct"))
			.append(")");
		if (m_accountsOK)
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		else
			no = -1;
		if (no != 1)
		{
			String err = "GL Accounts NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}

		//	Create Std Accounts
		sqlCmd = new StringBuffer ("INSERT INTO C_AcctSchema_Default (");
		sqlCmd.append(m_stdColumns).append(",C_AcctSchema_ID,"
			+ "W_INVENTORY_Acct,W_DIFFERENCES_Acct,W_REVALUATION_Acct,W_INVACTUALADJUST_Acct, "
			+ "P_REVENUE_Acct,P_EXPENSE_Acct,P_CostAdjustment_Acct,P_InventoryClearing_Acct,P_ASSET_Acct,P_COGS_Acct, "
			+ "P_PURCHASEPRICEVARIANCE_Acct,P_INVOICEPRICEVARIANCE_Acct,P_TRADEDISCOUNTREC_Acct,P_TRADEDISCOUNTGRANT_Acct, "
			+ "C_RECEIVABLE_Acct,C_Receivable_Services_Acct,C_PREPAYMENT_Acct, "
			+ "V_LIABILITY_Acct,V_LIABILITY_SERVICES_Acct,V_PREPAYMENT_Acct, "
			+ "PAYDISCOUNT_EXP_Acct,PAYDISCOUNT_REV_Acct,WRITEOFF_Acct, "
			+ "UNREALIZEDGAIN_Acct,UNREALIZEDLOSS_Acct,REALIZEDGAIN_Acct,REALIZEDLOSS_Acct, "
			+ "WITHHOLDING_Acct,E_PREPAYMENT_Acct,E_EXPENSE_Acct, "
			+ "PJ_ASSET_Acct,PJ_WIP_Acct, "
			+ "T_EXPENSE_Acct,T_LIABILITY_Acct,T_RECEIVABLES_Acct,T_DUE_Acct,T_CREDIT_Acct, "
			+ "B_INTRANSIT_Acct,B_ASSET_Acct,B_EXPENSE_Acct,B_INTERESTREV_Acct,B_INTERESTEXP_Acct, "
			+ "B_UNIDENTIFIED_Acct,B_SETTLEMENTGAIN_Acct,B_SETTLEMENTLOSS_Acct, "
			+ "B_REVALUATIONGAIN_Acct,B_REVALUATIONLOSS_Acct,B_PAYMENTSELECT_Acct,B_UNALLOCATEDCASH_Acct, "
			+ "CH_EXPENSE_Acct,CH_REVENUE_Acct, "
			+ "UNEARNEDREVENUE_Acct,NOTINVOICEDRECEIVABLES_Acct,NOTINVOICEDREVENUE_Acct,NOTINVOICEDRECEIPTS_Acct, "
			+ "CB_ASSET_Acct,CB_CASHTRANSFER_Acct,CB_DIFFERENCES_Acct,CB_EXPENSE_Acct,CB_RECEIPT_Acct, "
			+ "B_AssetFgn_Acct,B_InTransitFgn_Acct,C_ReceivableFgn_Acct,V_LiabilityFgn_Acct, "
			+ "P_RevenueFgn_Acct, T_HoldRec_Acct, T_HoldLiab_Acct, V_PrepaymentFgn_Acct,A_Creditors_Acct, "
			+ "A_CreditorsFgn_Acct, C_PrepaymentFgn_Acct, P_InventoryClearingFgn_Acct, P_PurchasesReturns_Acct, "
			+ "P_SalesReturns_Acct");
			if(isElecAcct()){
				sqlCmd.append(", P_ExpenseFgn_Acct,P_RevenueTGCash_Acct,P_RevenueTGCredit_Acct,P_RevenueTZCash_Acct, "
			+ "P_RevenueTZCredit_Acct,P_RevenueTECash_Acct,P_RevenueTECredit_Acct, "
			+ "P_TradeDiscountGrantG_Acct,P_TradeDiscountGrantZ_Acct,P_TradeDiscountGrantE_Acct, P_SalesReturnsG_Acct, "
			+ "P_SalesReturnsZ_Acct, P_SalesReturnsE_Acct");
			}
			sqlCmd.append(") VALUES (");
		sqlCmd.append(m_stdValues).append(",").append(m_as.getC_AcctSchema_ID()).append(",");
		//  W_INVENTORY_Acct,W_DIFFERENCES_Acct,W_REVALUATION_Acct,W_INVACTUALADJUST_Acct
		sqlCmd.append(getAcct("W_INVENTORY_Acct")).append(",");
		sqlCmd.append(getAcct("W_DIFFERENCES_Acct")).append(",");
		sqlCmd.append(getAcct("W_REVALUATION_Acct")).append(",");
		sqlCmd.append(getAcct("W_INVACTUALADJUST_Acct")).append(", ");
		//  P_REVENUE_Acct,P_EXPENSE_Acct,P_ASSET_Acct,P_COGS_Acct,
		sqlCmd.append(getAcct("P_REVENUE_Acct")).append(",");
		sqlCmd.append(getAcct("P_EXPENSE_Acct")).append(",");
		sqlCmd.append(getAcct("P_CostAdjustment_Acct")).append(",");
		sqlCmd.append(getAcct("P_InventoryClearing_Acct")).append(",");
		sqlCmd.append(getAcct("P_ASSET_Acct")).append(",");
		sqlCmd.append(getAcct("P_COGS_Acct")).append(", ");
		//  P_PURCHASEPRICEVARIANCE_Acct,P_INVOICEPRICEVARIANCE_Acct,P_TRADEDISCOUNTREC_Acct,P_TRADEDISCOUNTGRANT_Acct,
		sqlCmd.append(getAcct("P_PURCHASEPRICEVARIANCE_Acct")).append(",");
		sqlCmd.append(getAcct("P_INVOICEPRICEVARIANCE_Acct")).append(",");
		sqlCmd.append(getAcct("P_TRADEDISCOUNTREC_Acct")).append(",");
		sqlCmd.append(getAcct("P_TRADEDISCOUNTGRANT_Acct")).append(", ");
		//  C_RECEIVABLE_Acct,C_Receivable_Services_Acct,C_PREPAYMENT_Acct,
		sqlCmd.append(getAcct("C_RECEIVABLE_Acct")).append(",");
		sqlCmd.append(getAcct("C_RECEIVABLE_SERVICES_Acct")).append(",");
		sqlCmd.append(getAcct("C_PREPAYMENT_Acct")).append(", ");
		//  V_LIABILITY_Acct,V_LIABILITY_SERVICES_Acct,V_PREPAYMENT_Acct,
		sqlCmd.append(getAcct("V_LIABILITY_Acct")).append(",");
		sqlCmd.append(getAcct("V_LIABILITY_SERVICES_Acct")).append(",");
		sqlCmd.append(getAcct("V_PREPAYMENT_Acct")).append(", ");
		//  PAYDISCOUNT_EXP_Acct,PAYDISCOUNT_REV_Acct,WRITEOFF_Acct,
		sqlCmd.append(getAcct("PAYDISCOUNT_EXP_Acct")).append(",");
		sqlCmd.append(getAcct("PAYDISCOUNT_REV_Acct")).append(",");
		sqlCmd.append(getAcct("WRITEOFF_Acct")).append(", ");
		//  UNREALIZEDGAIN_Acct,UNREALIZEDLOSS_Acct,REALIZEDGAIN_Acct,REALIZEDLOSS_Acct,
		sqlCmd.append(getAcct("UNREALIZEDGAIN_Acct")).append(",");
		sqlCmd.append(getAcct("UNREALIZEDLOSS_Acct")).append(",");
		sqlCmd.append(getAcct("REALIZEDGAIN_Acct")).append(",");
		sqlCmd.append(getAcct("REALIZEDLOSS_Acct")).append(", ");
		//  WITHHOLDING_Acct,E_PREPAYMENT_Acct,E_EXPENSE_Acct,
		sqlCmd.append(getAcct("WITHHOLDING_Acct")).append(",");
		sqlCmd.append(getAcct("E_PREPAYMENT_Acct")).append(",");
		sqlCmd.append(getAcct("E_EXPENSE_Acct")).append(", ");
		//  PJ_ASSET_Acct,PJ_WIP_Acct,
		sqlCmd.append(getAcct("PJ_ASSET_Acct")).append(",");
		sqlCmd.append(getAcct("PJ_WIP_Acct")).append(", ");
		//  T_EXPENSE_Acct,T_LIABILITY_Acct,T_RECEIVABLES_Acct,T_DUE_Acct,T_CREDIT_Acct,
		sqlCmd.append(getAcct("T_EXPENSE_Acct")).append(",");
		sqlCmd.append(getAcct("T_LIABILITY_Acct")).append(",");
		sqlCmd.append(getAcct("T_RECEIVABLES_Acct")).append(",");
		sqlCmd.append(getAcct("T_DUE_Acct")).append(",");
		sqlCmd.append(getAcct("T_CREDIT_Acct")).append(", ");
		//  B_INTRANSIT_Acct,B_ASSET_Acct,B_EXPENSE_Acct,B_INTERESTREV_Acct,B_INTERESTEXP_Acct,
		sqlCmd.append(getAcct("B_INTRANSIT_Acct")).append(",");
		sqlCmd.append(getAcct("B_ASSET_Acct")).append(",");
		sqlCmd.append(getAcct("B_EXPENSE_Acct")).append(",");
		sqlCmd.append(getAcct("B_INTERESTREV_Acct")).append(",");
		sqlCmd.append(getAcct("B_INTERESTEXP_Acct")).append(", ");
		//  B_UNIDENTIFIED_Acct,B_SETTLEMENTGAIN_Acct,B_SETTLEMENTLOSS_Acct,
		sqlCmd.append(getAcct("B_UNIDENTIFIED_Acct")).append(",");
		sqlCmd.append(getAcct("B_SETTLEMENTGAIN_Acct")).append(",");
		sqlCmd.append(getAcct("B_SETTLEMENTLOSS_Acct")).append(", ");
		//  B_REVALUATIONGAIN_Acct,B_REVALUATIONLOSS_Acct,B_PAYMENTSELECT_Acct,B_UNALLOCATEDCASH_Acct,
		sqlCmd.append(getAcct("B_REVALUATIONGAIN_Acct")).append(",");
		sqlCmd.append(getAcct("B_REVALUATIONLOSS_Acct")).append(",");
		sqlCmd.append(getAcct("B_PAYMENTSELECT_Acct")).append(",");
		sqlCmd.append(getAcct("B_UNALLOCATEDCASH_Acct")).append(", ");
		//  CH_EXPENSE_Acct,CH_REVENUE_Acct,
		sqlCmd.append(getAcct("CH_EXPENSE_Acct")).append(",");
		sqlCmd.append(getAcct("CH_REVENUE_Acct")).append(", ");
		//  UNEARNEDREVENUE_Acct,NOTINVOICEDRECEIVABLES_Acct,NOTINVOICEDREVENUE_Acct,NOTINVOICEDRECEIPTS_Acct,
		sqlCmd.append(getAcct("UNEARNEDREVENUE_Acct")).append(",");
		sqlCmd.append(getAcct("NOTINVOICEDRECEIVABLES_Acct")).append(",");
		sqlCmd.append(getAcct("NOTINVOICEDREVENUE_Acct")).append(",");
		sqlCmd.append(getAcct("NOTINVOICEDRECEIPTS_Acct")).append(", ");
		//  CB_ASSET_Acct,CB_CASHTRANSFER_Acct,CB_DIFFERENCES_Acct,CB_EXPENSE_Acct,CB_RECEIPT_Acct)
		sqlCmd.append(getAcct("CB_ASSET_Acct")).append(",");
		sqlCmd.append(getAcct("CB_CASHTRANSFER_Acct")).append(",");
		sqlCmd.append(getAcct("CB_DIFFERENCES_Acct")).append(",");
		sqlCmd.append(getAcct("CB_EXPENSE_Acct")).append(",");
		sqlCmd.append(getAcct("CB_RECEIPT_Acct")).append(", ");
		// B_AssetFgn_Acct,B_InTransitFgn_Acct,C_ReceivableFgn_Acct,V_LiabilityFgn_Acct
		sqlCmd.append(getAcct("B_AssetFgn_Acct")).append(",");
		sqlCmd.append(getAcct("B_InTransitFgn_Acct")).append(",");
		sqlCmd.append(getAcct("C_ReceivableFgn_Acct")).append(",");
		sqlCmd.append(getAcct("V_LiabilityFgn_Acct")).append(",");
		//P_RevenueFgn_Acct, T_HoldRec_Acct, T_HoldLiab_Acct, V_PrepaymentFgn_Acct,A_Creditors_Acct
		sqlCmd.append(getAcct("P_RevenueFgn_Acct")).append(",");
		sqlCmd.append(getAcct("T_HoldRec_Acct")).append(",");
		sqlCmd.append(getAcct("T_HoldLiab_Acct")).append(",");
		sqlCmd.append(getAcct("V_PrepaymentFgn_Acct")).append(",");
		sqlCmd.append(getAcct("A_Creditors_Acct")).append(",");
		//A_CreditorsFgn_Acct, C_PrepaymentFgn_Acct, P_InventoryClearingFgn_Acct
		sqlCmd.append(getAcct("A_CreditorsFgn_Acct")).append(",");
		sqlCmd.append(getAcct("C_PrepaymentFgn_Acct")).append(",");
		sqlCmd.append(getAcct("P_InventoryClearingFgn_Acct")).append(",");
		//P_PurchasesReturns_Acct, P_SalesReturns_Acct --adominguez
		sqlCmd.append(getAcct("P_PurchasesReturns_Acct")).append(",");
		sqlCmd.append(getAcct("P_SalesReturns_Acct"));
		//Only if used Electronic Accounting. //mguerrero
		if(isElecAcct()){
			sqlCmd.append(", ");
			// P_ExpenseFgn_Acct,P_RevenueTGCash_Acct,P_RevenueTGCredit_Acct,P_RevenueTZCash_Acct
			sqlCmd.append(getAcct("P_ExpenseFgn_Acct")).append(",");
			sqlCmd.append(getAcct("P_RevenueTGCash_Acct")).append(",");
			sqlCmd.append(getAcct("P_RevenueTGCredit_Acct")).append(",");
			sqlCmd.append(getAcct("P_RevenueTZCash_Acct")).append(", ");
			// P_RevenueTZCredit_Acct,P_RevenueTECash_Acct,P_RevenueTECredit_Acct
			sqlCmd.append(getAcct("P_RevenueTZCredit_Acct")).append(",");
			sqlCmd.append(getAcct("P_RevenueTECash_Acct")).append(",");
			sqlCmd.append(getAcct("P_RevenueTECredit_Acct")).append(", ");
			//P_TradeDiscountGrantG_Acct,P_TradeDiscountGrantZ_Acct,P_TradeDiscountGrantE_Acct
			sqlCmd.append(getAcct("P_TradeDiscountGrantG_Acct")).append(",");
			sqlCmd.append(getAcct("P_TradeDiscountGrantZ_Acct")).append(",");
			sqlCmd.append(getAcct("P_TradeDiscountGrantE_Acct")).append(",");
			//P_SalesReturnsG_Acct, P_SalesReturnsZ_Acct, P_SalesReturnsE_Acct --adominguez
			sqlCmd.append(getAcct("P_SalesReturnsG_Acct")).append(",");
			sqlCmd.append(getAcct("P_SalesReturnsZ_Acct")).append(",");
			sqlCmd.append(getAcct("P_SalesReturnsE_Acct"));
		}
		sqlCmd.append(")");
		if (m_accountsOK)
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		else
			no = -1;
		if (no != 1)
		{
			String err = "Default Accounts NOT inserted";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}

		//  GL Categories
		createGLCategory("Standard", MGLCategory.CATEGORYTYPE_Manual, true);
		int GL_None = createGLCategory("None", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_GL = createGLCategory("Manual", MGLCategory.CATEGORYTYPE_Manual, false);
		int GL_ARI = createGLCategory("AR Invoice", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_ARR = createGLCategory("AR Receipt", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_MM = createGLCategory("Material Management", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_API = createGLCategory("AP Invoice", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_APP = createGLCategory("AP Payment", MGLCategory.CATEGORYTYPE_Document, false);
		int GL_CASH = createGLCategory("Cash/Payments", MGLCategory.CATEGORYTYPE_Document, false);

		//	Base DocumentTypes
		int ii = createDocType("GL Journal", Msg.getElement(m_ctx, "GL_Journal_ID"), 
			MDocType.DOCBASETYPE_GLJournal, MDocType.DOCSUBTYPESO_Jorunal, 0, 0, 1000, GL_GL);
		if (ii == 0)
		{
			String err = "Document Type not created";
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		createDocType("GL Journal Batch", Msg.getElement(m_ctx, "GL_JournalBatch_ID"), 
			MDocType.DOCBASETYPE_GLJournal, MDocType.DOCSUBTYPESO_Batch, 0, 0, 100, GL_GL);
		//	MDocType.DOCBASETYPE_GLDocument
		//
		int DT_I = createDocType("AR Invoice", Msg.getElement(m_ctx, "C_Invoice_ID", true), 
			MDocType.DOCBASETYPE_ARInvoice, null, 0, 0, 100000, GL_ARI);
		int DT_II = createDocType("AR Invoice Indirect", Msg.getElement(m_ctx, "C_Invoice_ID", true), 
			MDocType.DOCBASETYPE_ARInvoice, MDocType.DOCSUBTYPESO_SalesReceipt, 0, 0, 150000, GL_ARI);
		int DT_IC = createDocType("AR Credit Memo", Msg.getMsg(m_ctx, "CreditMemo"), 
			MDocType.DOCBASETYPE_ARCreditMemo, null, 0, 0, 170000, GL_ARI);
		//	MDocType.DOCBASETYPE_ARProFormaInvoice
		
		createDocType("AP Invoice", Msg.getElement(m_ctx, "C_Invoice_ID", false), 
			MDocType.DOCBASETYPE_APInvoice, null, 0, 0, 0, GL_API);
		createDocType("AP CreditMemo", Msg.getMsg(m_ctx, "CreditMemo"), 
			MDocType.DOCBASETYPE_APCreditMemo, null, 0, 0, 0, GL_API);
		createDocType("Match Invoice", Msg.getElement(m_ctx, "M_MatchInv_ID", false), 
			MDocType.DOCBASETYPE_MatchInvoice, null, 0, 0, 390000, GL_API);
		
		createDocType("AR Receipt", Msg.getElement(m_ctx, "C_Payment_ID", true), 
			MDocType.DOCBASETYPE_ARReceipt, null, 0, 0, 0, GL_ARR);
		createDocType("AP Payment", Msg.getElement(m_ctx, "C_Payment_ID", false), 
			MDocType.DOCBASETYPE_APPayment, null, 0, 0, 0, GL_APP);
		createDocType("Allocation", "Allocation", 
			MDocType.DOCBASETYPE_PaymentAllocation, null, 0, 0, 490000, GL_CASH);

		int DT_S  = createDocType("MM Shipment", "Delivery Note", 
			MDocType.DOCBASETYPE_MaterialDelivery, null, 0, 0, 500000, GL_MM);
		int DT_SI = createDocType("MM Shipment Indirect", "Delivery Note", 
			MDocType.DOCBASETYPE_MaterialDelivery, null, 0, 0, 550000, GL_MM);
		
		createDocType("MM Receipt", "Vendor Delivery", 
			MDocType.DOCBASETYPE_MaterialReceipt, null, 0, 0, 0, GL_MM);
		int DT_RM = createDocType("MM Returns", "Customer Returns", 
			MDocType.DOCBASETYPE_MaterialReceipt, null, 0, 0, 570000, GL_MM);
		
		createDocType("Purchase Order", Msg.getElement(m_ctx, "C_Order_ID", false), 
			MDocType.DOCBASETYPE_PurchaseOrder, null, 0, 0, 800000, GL_None);
		createDocType("Match PO", Msg.getElement(m_ctx, "M_MatchPO_ID", false), 
			MDocType.DOCBASETYPE_MatchPO, null, 0, 0, 890000, GL_None);
		createDocType("Purchase Requisition", Msg.getElement(m_ctx, "M_Requisition_ID", false), 
			MDocType.DOCBASETYPE_PurchaseRequisition, null, 0, 0, 900000, GL_None);

		createDocType("Bank Statement", Msg.getElement(m_ctx, "C_BankStatemet_ID", true), 
			MDocType.DOCBASETYPE_BankStatement, null, 0, 0, 700000, GL_CASH);
		createDocType("Cash Journal", Msg.getElement(m_ctx, "C_Cash_ID", true),
			MDocType.DOCBASETYPE_CashJournal, null, 0, 0, 750000, GL_CASH);
		
		createDocType("Material Movement", Msg.getElement(m_ctx, "M_Movement_ID", false),
			MDocType.DOCBASETYPE_MaterialMovement, null, 0, 0, 610000, GL_MM);
		createDocType("Physical Inventory", Msg.getElement(m_ctx, "M_Inventory_ID", false), 
			MDocType.DOCBASETYPE_MaterialPhysicalInventory, null, 0, 0, 620000, GL_MM);
		createDocType("Material Production", Msg.getElement(m_ctx, "M_Production_ID", false), 
			MDocType.DOCBASETYPE_MaterialProduction, null, 0, 0, 630000, GL_MM);
		createDocType("Project Issue", Msg.getElement(m_ctx, "C_ProjectIssue_ID", false), 
			MDocType.DOCBASETYPE_ProjectIssue, null, 0, 0, 640000, GL_MM);

		//  Order Entry
		createDocType("Binding offer", "Quotation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_Quotation, 
			0, 0, 10000, GL_None);
		createDocType("Non binding offer", "Proposal", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_Proposal, 
			0, 0, 20000, GL_None);
		createDocType("Prepay Order", "Prepay Order", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_PrepayOrder, 
			DT_S, DT_I, 30000, GL_None);
		createDocType("Return Material", "Return Material Authorization", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_ReturnMaterial, 
			DT_RM, DT_IC, 30000, GL_None);
		createDocType("Standard Order", "Order Confirmation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_StandardOrder, 
			DT_S, DT_I, 50000, GL_None);
		createDocType("Credit Order", "Order Confirmation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_OnCreditOrder, 
			DT_SI, DT_I, 60000, GL_None);   //  RE
		createDocType("Warehouse Order", "Order Confirmation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_WarehouseOrder, 
			DT_S, DT_I,	70000, GL_None);    //  LS
		int DT = createDocType("POS Order", "Order Confirmation", 
			MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_POSOrder, 
			DT_SI, DT_II, 80000, GL_None);    // Bar
		//	POS As Default for window SO
		createPreference("C_DocTypeTarget_ID", String.valueOf(DT), 143);

		//expert : miguel rojas : tipos de documento especificos de medsys
		createDocType("Prog. Qfano.", "Programaci\u00F3n de Quir\u00F3fano", 
				MDocType.DOCBASETYPE_Medsys, null, 
				0, 0, 1000000, GL_None);    //  Programacion de Quirofanos
		
		ctaPacDocId = createDocType("Cargo Cta. Pac.", "Cargo a Cuenta Paciente", 
				MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_CgosACtaPac, 
				0, 0, 1000000, GL_None);    //  Cargos a cuenta paciente
		
		createDocType("Cargo Cta. Pac. Ext.", "Cargo a Cuenta Paciente Ext.", 
				MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_CgosACtaPac, 
				0, 0, 1000000, GL_None);    //  Cargos a cuenta paciente por extensiones
		
		createDocType("Referencias", "Referencia/Contrareferencia", 
				MDocType.DOCBASETYPE_Medsys, null, 
				0, 0, 1000000, GL_None);    //  Referencia / Contrareferencia
		
		createDocType("Pedido Base", "Pedido Base", 
				MDocType.DOCBASETYPE_MaterialMovement, null, 
				0, 0, 1000000, GL_GL);    //  Referencia / Contrareferencia
		
		createDocType("Receta M\u00E9dica", "Receta M\u00E9dica", 
				MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_StandardOrder, 
				DT_S, DT_I, 1000000, GL_None);    //  Receta medica
		
		createDocType("Solicitud de Servicios", "Solicitud de Servicios", 
				MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_StandardOrder, 
				DT_S, DT_I, 1000000, GL_None);    //  Receta medica
		// expert : miguel rojas : tipos de documento especificos de medsys
		//expert .. Twry //Expert. TTPR # Posteo Notas de Debito
		@SuppressWarnings("unused")
		int DT_ID = createDocType("AR Debit Memo", Msg.getMsg(m_ctx, "DebitMemo"), 
				MDocType.DOCBASETYPE_ARDebitMemo, null, 0, 0, 100000, GL_ARI);
		
		createDocType("AP Debit Memo", Msg.getMsg(m_ctx, "DebitMemo"), 
				MDocType.DOCBASETYPE_APDebitMemo, null, 0, 0, 0, GL_API);
		//expert .. Twry Fin
		
		//freyes...
		createDocType("Medical Record", "Medical Record", 
				MDocType.DOCBASETYPE_SalesOrder, MDocType.DOCSUBTYPESO_CgosACtaPac, 
				0, 0, 1000000, GL_None);    //  DocType para expediente fisico
		//termina freyes.
		
		//  Update ClientInfo
		sqlCmd = new StringBuffer ("UPDATE AD_ClientInfo SET ");
		sqlCmd.append("C_AcctSchema1_ID=").append(m_as.getC_AcctSchema_ID())
			.append(", C_Calendar_ID=").append(m_calendar.getC_Calendar_ID())
			.append(" WHERE AD_Client_ID=").append(m_client.getAD_Client_ID());
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
		{
			String err = "ClientInfo not updated";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			m_trx.rollback();
			m_trx.close();
			return false;
		}

		//	Validate Completeness
		DocumentTypeVerify.createDocumentTypes(m_ctx, getAD_Client_ID(), null, m_trx.getTrxName());
		DocumentTypeVerify.createPeriodControls(m_ctx, getAD_Client_ID(), null, m_trx.getTrxName());
		//
		log.info("fini");
		return true;
	}   //  createAccounting

	/**
	 *  Get Account ID for key
	 *  @param key key
	 *  @return C_ValidCombination_ID
	 */
	private int getAcct (String key)
	{
		log.fine(key);
		//  Element
		int C_ElementValue_ID = m_nap.getC_ElementValue_ID(key.toUpperCase());
		if (C_ElementValue_ID == 0)
		{
			log.severe("Account not defined: " + key);
			m_accountsOK = false;
			return 0;
		}

		MAccount vc = MAccount.getDefault(m_as, true);	//	optional null
		vc.setAD_Org_ID(0);		//	will be overwritten
		vc.setAccount_ID(C_ElementValue_ID);
		if (!vc.save())
		{
			log.severe("Not Saved - Key=" + key + ", C_ElementValue_ID=" + C_ElementValue_ID);
			m_accountsOK = false;
			return 0;
		}
		int C_ValidCombination_ID = vc.getC_ValidCombination_ID();
		if (C_ValidCombination_ID == 0)
		{
			log.severe("No account - Key=" + key + ", C_ElementValue_ID=" + C_ElementValue_ID);
			m_accountsOK = false;
		}
		return C_ValidCombination_ID;
	}   //  getAcct

	/**
	 *  Create GL Category
	 *  @param Name name
	 *  @param CategoryType category type MGLCategory.CATEGORYTYPE_*
	 *  @param isDefault is default value
	 *  @return GL_Category_ID
	 */
	private int createGLCategory (String Name, String CategoryType, boolean isDefault)
	{
		MGLCategory cat = new MGLCategory (m_ctx, 0, m_trx.getTrxName());
		cat.setName(Name);
		cat.setCategoryType(CategoryType);
		cat.setIsDefault(isDefault);
		if (!cat.save())
		{
			log.log(Level.SEVERE, "GL Category NOT created - " + Name);
			return 0;
		}
		//
		return cat.getGL_Category_ID();
	}   //  createGLCategory

	/**
	 *  Create Document Types with Sequence
	 *  @param Name name
	 *  @param PrintName print name
	 *  @param DocBaseType document base type
	 *  @param DocSubTypeSO sales order sub type
	 *  @param C_DocTypeShipment_ID shipment doc
	 *  @param C_DocTypeInvoice_ID invoice doc
	 *  @param StartNo start doc no
	 *  @param GL_Category_ID gl category
	 *  @return C_DocType_ID doc type or 0 for error
	 */
	private int createDocType (String Name, String PrintName,
		String DocBaseType, String DocSubTypeSO,
		int C_DocTypeShipment_ID, int C_DocTypeInvoice_ID,
		int StartNo, int GL_Category_ID)
	{
		MSequence sequence = null;
		if (StartNo != 0)
		{
			sequence = new MSequence(m_ctx, getAD_Client_ID(), Name, StartNo, m_trx.getTrxName());
			if (!sequence.save())
			{
				log.log(Level.SEVERE, "Sequence NOT created - " + Name);
				return 0;
			}
		}
		
		MDocType dt = new MDocType (m_ctx, DocBaseType, Name, m_trx.getTrxName());
		if (PrintName != null && PrintName.length() > 0)
			dt.setPrintName(PrintName);	//	Defaults to Name
		if (DocSubTypeSO != null)
			dt.setDocSubTypeSO(DocSubTypeSO);
		if (C_DocTypeShipment_ID != 0)
			dt.setC_DocTypeShipment_ID(C_DocTypeShipment_ID);
		if (C_DocTypeInvoice_ID != 0)
			dt.setC_DocTypeInvoice_ID(C_DocTypeInvoice_ID);
		if (GL_Category_ID != 0)
			dt.setGL_Category_ID(GL_Category_ID);
		if (sequence == null)
			dt.setIsDocNoControlled(false);
		else
		{
			dt.setIsDocNoControlled(true);
			dt.setDocNoSequence_ID(sequence.getAD_Sequence_ID());
		}
		dt.setIsSOTrx();
		dt.setAD_Org_ID(getAD_Org_ID());
		if(Name.equals("Material Movement")){
			dt.setIsInTransit(true);
		}
		
		if (!dt.save())
		{
			log.log(Level.SEVERE, "DocType NOT created - " + Name);
			return 0;
		}
		//
		return dt.getC_DocType_ID();
	}   //  createDocType

	
	/**************************************************************************
	 *  Create Default main entities.
	 *  - Dimensions & BPGroup, Prod Category)
	 *  - Location, Locator, Warehouse
	 *  - PriceList
	 *  - Cashbook, PaymentTerm
	 *  @param C_Country_ID country
	 *  @param City city
	 *  @param C_Region_ID region
	 *  @param taxRate La tasa de impuesto
	 *  @param locale El objeto Locale.
	 *  @author jcantu Modificado el 4 de Sept del 2012.
	 *  @return true if created
	 */
	public boolean createEntities (int C_Country_ID, String City, int C_Region_ID, 
			int C_Currency_ID, Double taxRate, Locale locale) {
		
		StringBuilder msjExito = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		StringBuilder msjError = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		msjExito.append("\n");
		msjExito.append(Utilerias.getMessage(m_ctx, null, "msj.exito.reglaCuestionario.registroCorrecto"));
		msjExito.append(": ");
		
		msjError.append("\n");
		msjError.append(Utilerias.getMessage(m_ctx, null, "msj.errorsavereg"));
		msjError.append(": ");
		
		if (m_as == null) {
			log.severe ("No AcctountingSChema");
			m_trx.rollback();
			m_trx.close();
			return false;
		}
		
		log.info("C_Country_ID=" + C_Country_ID 
			+ ", City=" + City + ", C_Region_ID=" + C_Region_ID);
		
		m_info.append("\n--createEntities--\n");
		//
		String defaultName = Msg.translate(m_lang, "Standard");
		
		//Se agrega descripcion igual al name pero concatenando el ID de la Organizacion.
		String defaultDescription = getAD_Org_ID() + " " + defaultName;
		
		String defaultEntry = "'" + defaultName + "',";
		
		StringBuffer sqlCmd = null;
		int no = 0;

		//	Create Marketing Channel/Campaign
		int C_Channel_ID = getNextID(getAD_Client_ID(), "C_Channel");
		sqlCmd = new StringBuffer("INSERT INTO C_Channel ");
		sqlCmd.append("(C_Channel_ID,Name,");
		sqlCmd.append(m_stdColumns).append(") VALUES (");
		sqlCmd.append(C_Channel_ID).append(",").append(defaultEntry);
		sqlCmd.append(m_stdValues).append(")");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no != 1) {
			log.log(Level.SEVERE, "Channel NOT inserted");
			m_info.append(msjError).append("Channel Not inserted: C_Channel_Id: ")
			.append(C_Channel_ID).append(" OrgName: ").append(defaultDescription);
		}
		
		m_info.append(msjExito).append("C_Channel_Id: ")
		.append(C_Channel_ID).append(" OrgName: ").append(defaultDescription);
		
		int C_Campaign_ID = getNextID(getAD_Client_ID(), "C_Campaign");
		sqlCmd = new StringBuffer("INSERT INTO C_Campaign ");
		sqlCmd.append("(C_Campaign_ID,C_Channel_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Value,Name,Costs) VALUES (");
		sqlCmd.append(C_Campaign_ID).append(",").append(C_Channel_ID).append(",").append(m_stdValues).append(",");
		sqlCmd.append(defaultEntry).append(defaultEntry).append("0)");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no == 1) {
			m_info.append(msjExito).append("C_Campaign_ID: ").append(C_Campaign_ID)
			.append(" OrgName: ").append(defaultDescription);
		} else {
			log.log(Level.SEVERE, "Campaign NOT inserted");
			m_info.append(msjError).append("Campaign Not inserted: C_Campaign_ID: ")
			.append(C_Campaign_ID).append(" OrgName: ").append(defaultDescription);
		}
		
		if (m_hasMCampaign) {
			//  Default
			sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
			sqlCmd.append("C_Campaign_ID=").append(C_Campaign_ID);
			sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
			sqlCmd.append(" AND ElementType='MC'");
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
			
			if (no != 1) {
				log.log(Level.SEVERE, "AcctSchema ELement Campaign NOT updated");
				m_info.append(msjError).append("AcctSchema ELement Campaign Not updated: C_Campaign_ID: ")
				.append(C_Campaign_ID).append(" OrgName: ").append(defaultDescription);
			}
			
			m_info.append(msjExito).append("AcctSchema ELement Campaign Successfully updated: ").append(C_Campaign_ID)
			.append(" OrgName: ").append(defaultDescription);
			
		}

		//	Create Sales Region
		int C_SalesRegion_ID = getNextID(getAD_Client_ID(), "C_SalesRegion");
		sqlCmd = new StringBuffer ("INSERT INTO C_SalesRegion ");
		sqlCmd.append("(C_SalesRegion_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Value,Name,IsSummary) VALUES (");
		sqlCmd.append(C_SalesRegion_ID).append(",").append(m_stdValues).append(", ");
		sqlCmd.append(defaultEntry).append(defaultEntry).append("'N')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no == 1) {
			m_info.append(msjExito).append("SalesRegion ID: ").append(C_SalesRegion_ID)
			.append("OrgName = ").append(defaultDescription);
		} else {
			log.log(Level.SEVERE, "SalesRegion NOT inserted");
			m_info.append(msjError).append("SalesRegion Not inserted: SalesRegion_ID: ")
			.append(C_SalesRegion_ID).append(" OrgName: ").append(defaultDescription);
		}
		
		if (m_hasSRegion) {
			//  Default
			sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
			sqlCmd.append("C_SalesRegion_ID=").append(C_SalesRegion_ID);
			sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
			sqlCmd.append(" AND ElementType='SR'");
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
			
			if (no != 1) {
				log.log(Level.SEVERE, "AcctSchema ELement SalesRegion NOT updated");
				
				m_info.append(msjError).append("AcctSchema ELement SalesRegion Not updated: SalesRegion_ID: ")
				.append(C_SalesRegion_ID).append(" OrgName: ").append(defaultDescription);
			} 
			
			m_info.append(msjExito).append("AcctSchema ELement SalesRegion Successfully updated: ").append(C_SalesRegion_ID)
			.append(" OrgName: ").append(defaultDescription);
		}
		

		/**
		 *  Product
		 */
		//  Create Product Category
		MProductCategory pc = new MProductCategory(m_ctx, 0, null);
		pc.setValue(defaultName);
		pc.setName(defaultName);
		pc.setDescription(defaultDescription);
		pc.setIsDefault(true);
		
		if (pc.save(m_trx.getTrxName())) {
			m_info.append(msjExito).append("Product Category: ").append(defaultDescription);
			
		} else {
			log.log(Level.SEVERE, "Product Category NOT inserted");
			m_info.append(msjError).append("Product Category Not inserted: ").append("OrgName: ").append(defaultDescription);
		}

		//  UOM (EA)
		int C_UOM_ID = 100;
		
		//TaxCategory
		/*int C_TaxCategory_ID = getNextID(getAD_Client_ID(), "C_TaxCategory");
		sqlCmd = new StringBuffer ("INSERT INTO C_TaxCategory ");
		sqlCmd.append("(C_TaxCategory_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Name, Description, IsDefault) VALUES (");
		sqlCmd.append(C_TaxCategory_ID).append(",").append(m_stdValues).append(", ");
		sqlCmd.append(defaultEntry).append(defaultEntry).append("'Y')");
		
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1) {
			log.log(Level.SEVERE, "TaxCategory NOT inserted");
			m_info.append(msjError).append("TaxCategory Not inserted: ").append("OrgName: ").append(defaultDescription);
		}
		
		m_info.append(msjExito).append("TaxCategory: ").append(defaultEntry)
		.append(" ID: ").append(C_TaxCategory_ID)
		.append("OrgName: ").append(defaultDescription);*/
		
		//Se crea la Categoria de Impuestos del 16 %
		String sltasa16 = Utilerias.getMessage(m_ctx, locale, "msj.tasa16");
		MTaxCategory mtaxRate16 = new MTaxCategory(m_ctx, 0, null);
		mtaxRate16.setName(sltasa16);
		mtaxRate16.setDescription(sltasa16);
		mtaxRate16.setIsDefault(true);
		mtaxRate16.setAD_Org_ID(0);
		
		
		if (mtaxRate16.save(m_trx.getTrxName())) {
			m_info.append(msjExito).append("Tax Category: ").append(sltasa16);
			
		} else {
			log.log(Level.SEVERE, "Tax Category " + sltasa16 + " NOT inserted");
			m_info.append(msjError).append("Tax Category Not inserted: ").append(sltasa16).append(": OrgName: ").append(defaultDescription);
		}

		// Se crea impuesto 16% estandar.
		// Card 1123
		MTax tax = new MTax (m_ctx, sltasa16, new BigDecimal(16), mtaxRate16.getC_TaxCategory_ID(), null);
		tax.setIsDefault(true);
		tax.setDescription(sltasa16);
		tax.setAD_Org_ID(0);
		
		if (tax.save(m_trx.getTrxName())) {
			m_info.append(msjExito).append("Tax: ").append(sltasa16).append(": ").append(sltasa16);
		} else {
			log.log(Level.SEVERE, "Tax NOT inserted");
			m_info.append(msjError).append("Tax Not inserted: ").append(sltasa16).append(": ").append("OrgName: ").append(defaultDescription);
		}

		//	Create Standard Product
		MProduct product = new MProduct (m_ctx, 0, null);
		product.setValue(defaultName);
		product.setName(defaultName);
		product.setDescription(defaultDescription);
		product.setC_UOM_ID(C_UOM_ID);
		
		//Se setea la Unidad de Volume igual a la unidad de medida.
		product.setC_UOMVolume_ID(C_UOM_ID);
		product.setM_Product_Category_ID(pc.getM_Product_Category_ID());
		product.setC_TaxCategory_ID(mtaxRate16.getC_TaxCategory_ID());
		product.setProductClass(MProduct.PRODUCTCLASS_Others);//Set Product Class Others
		
		if (product.save(m_trx.getTrxName())) {
			m_info.append(msjExito).append(Msg.translate(m_lang, "M_Product_ID")).append("=").append(defaultName);
		} else {
			log.log(Level.SEVERE, "Product Estandar NOT inserted");
			m_info.append(msjError).append("Product NOT Inserted: ").append(defaultName);
		}
		
		//  Default
		sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
		sqlCmd.append("M_Product_ID=").append(product.getM_Product_ID());
		sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
		sqlCmd.append(" AND ElementType='PR'");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no != 1) {
			log.log(Level.SEVERE, "AcctSchema Element Product NOT updated");
			m_info.append(msjError).append("AcctSchema Element Product NOT updated: ").append(defaultName);
		}
		
		m_info.append(msjExito).append("AcctSchema Element Product successfully updated: ").append(defaultName);
		
		
		/**
		 *  Other
		 */
		//  PriceList
		MPriceList pl = new MPriceList(m_ctx, 0, null);
		pl.setName(defaultName);
		pl.setDescription(defaultDescription);
		pl.setC_Currency_ID(C_Currency_ID);
		pl.setIsDefault(true);
		
		//Lista de precios de Venta debe estar palomeado. Jesus Cantu. 19 Junio 2012.
		//Revisado co Helio Gutierrez en requerimientos de wizards.
		pl.setIsSOPriceList(true);
		
		if (!pl.save(m_trx.getTrxName())) {
			log.log(Level.SEVERE, "PriceList NOT inserted");
			m_info.append(msjError).append("PriceList NOT inserted: ").append(defaultName);
		}
		
		M_PriceList_ID = pl.getM_PriceList_ID();
		
		m_info.append(msjExito).append("PriceList: ").append(defaultName).append(" ID: ").append(M_PriceList_ID);
		
		//  Price List
		MDiscountSchema ds = new MDiscountSchema(m_ctx, 0, null);
		ds.setName(defaultName);
		ds.setDescription(defaultDescription);
		ds.setDiscountType(MDiscountSchema.DISCOUNTTYPE_Pricelist);
		
		if (!ds.save(m_trx.getTrxName())) {
			log.log(Level.SEVERE, "DiscountSchema NOT inserted");
			m_info.append(msjError).append("DiscountSchema NOT inserted: ").append(defaultName);
		}
		
		m_info.append(msjExito).append("DiscountSchema: ").append(defaultName).append(" ID: ").append(ds.getM_DiscountSchema_ID());
		
		//  PriceList Version
		MPriceListVersion plv = new MPriceListVersion(pl);
		plv.setName();
		plv.setDescription(defaultDescription);
		plv.setM_DiscountSchema_ID(ds.getM_DiscountSchema_ID());
		
		if (!plv.save()) {
			log.log(Level.SEVERE, "PriceList_Version NOT inserted");
			m_info.append(msjError).append("PriceList_Version NOT inserted: ").append(defaultName);
		}
		
		M_PriceList_Version_ID = plv.getM_PriceList_Version_ID();
		
		m_info.append(msjExito).append("PriceList_Version: ").append(defaultName).append(" ID: ").append(M_PriceList_Version_ID);
		
		//  ProductPrice
		MProductPrice pp = new MProductPrice(plv, product.getM_Product_ID(), 
			Env.ONE, Env.ONE, Env.ONE);
		
		if (!pp.save()) {
			log.log(Level.SEVERE, "ProductPrice NOT inserted");
			m_info.append(msjError).append("ProductPrice NOT inserted: ").append(defaultName);
		}
		
		m_info.append(msjExito).append("ProductPrice: ").append(defaultName);

		/**
		 *  Business Partner
		 */
		//  Create BP Group
		MBPGroup bpg = new MBPGroup (m_ctx, 0, null);
		bpg.setValue(defaultName);
		bpg.setName(defaultName);
		
		//Se agrega la descripcion
		bpg.setDescription(defaultDescription);
		
		bpg.setIsDefault(true);
		
		//Se coloca el esquema recien creado.
		bpg.setM_DiscountSchema_ID(ds.getM_DiscountSchema_ID());
		
		//Se agrega la Lista de Precios Creada para la Organizacion
		bpg.setM_PriceList_ID(M_PriceList_ID);
		
		if (bpg.save(m_trx.getTrxName())) {
			m_info.append(msjExito).append(Msg.translate(m_lang, "C_BP_Group_ID")).append("=").append(defaultName);
		} else {
			log.log(Level.SEVERE, "BP Group NOT inserted");
			m_info.append(msjError).append("BP Group NOT inserted: ").append(defaultName);
		}
		
		//	Create BPartner
		MBPartner bp = new MBPartner (m_ctx, 0, null);
		bp.setValue(defaultName);
		bp.setDescription(defaultDescription);
		
		// Se coloca el nombre en duro ya que en Office Visit o ejecucion de Cita busca un C_BPartner asi en duro
		// Esto a la hora de cerrar la cita, referencia, RQM 1667: Defecto "Educación/ Instrucciones: Null".
		//Jesus Cantu
		bp.setName("STANDARD");
		
		bp.setBPGroup(bpg);
		
		//Se coloca el TAXID Default para el BPartner Standard debido a cambios realizados en BPartner.
		//Jesus Cantu el 14 Agosto 2013.
		bp.setTaxID("XAXX010101000");
		
		if (bp.save(m_trx.getTrxName())) {
			m_info.append(msjExito).append(Msg.translate(m_lang, "C_BPartner_ID")).append("=").append(defaultName);
			// Location for Standard BP
			MLocation bpLoc = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, null);
			bpLoc.save(m_trx.getTrxName());
			MBPartnerLocation bpl = new MBPartnerLocation(bp);
			bpl.setC_Location_ID(bpLoc.getC_Location_ID());
			
			// Adecuacion al wizard para Nimbo, de despalomear la opcion de Direccion a remitir en la ubicacion
			// del Socio de Negocios STANDARD. Jesus Cantu. 12 de Junio 2012.
			bpl.setIsRemitTo(false);
			
			if (!bpl.save()) {
				log.log(Level.SEVERE, "BP_Location (Standard) NOT inserted");
				m_info.append(msjError).append("BP_Location (Standard) successfully inserted: ").append(bpLoc.getC_Location_ID());
			}
			
			m_info.append(msjExito).append("BP_Location (Standard)").append("=").append(defaultName);
			
		} else {
			log.log(Level.SEVERE, "BPartner NOT inserted");
			m_info.append(msjError).append("BPartner NOT inserted");
			bp = MBPartner.get(m_ctx, defaultName);
		}
		
		C_BPartner_ID = bp.getC_BPartner_ID();
		
		//  Default
		sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
		sqlCmd.append("C_BPartner_ID=").append(C_BPartner_ID);
		sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
		sqlCmd.append(" AND ElementType='BP'");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no != 1) {
			log.log(Level.SEVERE, "AcctSchema Element BPartner NOT updated");
			m_info.append(msjError).append("AcctSchema Element BPartner NOT updated: ").append(C_BPartner_ID);
		}
		createPreference("C_BPartner_ID", String.valueOf(bp.getC_BPartner_ID()), 143);
		
		m_info.append(msjExito).append("AcctSchema Element BPartner updated and pŕeference Created: ").append(C_BPartner_ID);

		
		
		/* 
		 * Se comenta ya que mas abajo (en método clientConfiguration) se crea un sales tax, 
		 * entonces que cree el Estandar aqui, y abajo el Sales Tax y el Tax Free.
		 * Se reviso con Helio. Jesus Cantu.
		 * 
		 */
		/*if (C_Country_ID == 100)    // US
			sqlCmd.append("'Sales Tax','Y')");
		else
			sqlCmd.append(defaultEntry).append("'Y')");*/
		
		//EXPERT: Lama .- Crear Charge Default
		//22 Noviembre 2013, se comenta creacion de cargo de Ventas. Jesus Cantu
		/*int C_Charge_ID = getNextID(getAD_Client_ID(), "C_Charge");
		sqlCmd = new StringBuffer ("INSERT INTO C_Charge ");
		sqlCmd.append("(C_Charge_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Name, value, C_TaxCategory_ID) VALUES (");
		sqlCmd.append(C_Charge_ID).append(",").append(m_stdValues).append(", ");
		sqlCmd.append(defaultEntry).append(defaultEntry).append(C_TaxCategory_ID).append(")");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no != 1) {
			log.log(Level.SEVERE, "C_Charge NOT inserted");
			m_info.append(msjError).append("C_Charge NOT inserted: C_Charge_ID: ").append(C_Charge_ID);
		}
		
		m_info.append(msjExito).append("C_Charge: ").append(defaultName);*/
		
		/**
		 *  Location, Warehouse, Locator
		 */
		//  Location (Company)
		MLocation loc = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, null);
		loc.save(m_trx.getTrxName());
		
		sqlCmd = new StringBuffer ("UPDATE AD_OrgInfo SET C_Location_ID=");
		sqlCmd.append(loc.getC_Location_ID()).append(" WHERE AD_Org_ID=").append(getAD_Org_ID());
		
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no != 1) {
			log.log(Level.SEVERE, "Location NOT inserted");
			m_info.append(msjError).append("Location NOT inserted: Location_ID: ").append(loc.getC_Location_ID());
		}
		
		createPreference("C_Country_ID", String.valueOf(C_Country_ID), 0);
		
		m_info.append(msjExito).append("Location Inserted: ").append(loc.getC_Location_ID());

		//  Default Warehouse
		MWarehouse wh = new MWarehouse(m_ctx, 0, null);
		wh.setValue(defaultName);
		wh.setName(defaultName);
		wh.setDescription(defaultDescription);
		wh.setC_Location_ID(loc.getC_Location_ID());
		
		if (!wh.save(m_trx.getTrxName())) {
			log.log(Level.SEVERE, "Warehouse NOT inserted");
			m_info.append(msjError).append("Warehouse NOT inserted: ").append(defaultName);
		}
		
		m_info.append(msjExito).append("Warehouse: ").append(defaultName);

		//   Locator
		MLocator locator = new MLocator(wh, defaultName);
		locator.setIsDefault(true);
		
		if (!locator.save()) {
			log.log(Level.SEVERE, "Locator NOT inserted");
			m_info.append(msjError).append("Locator NOT inserted: ").append(defaultName);
		}
		
		m_info.append(msjExito).append("Locator: ").append(defaultName);

		//  Update ClientInfo
		sqlCmd = new StringBuffer ("UPDATE AD_ClientInfo SET ");
		sqlCmd.append("C_BPartnerCashTrx_ID=").append(bp.getC_BPartner_ID());
		sqlCmd.append(",M_ProductFreight_ID=").append(product.getM_Product_ID());
//		sqlCmd.append("C_UOM_Volume_ID=");
//		sqlCmd.append(",C_UOM_Weight_ID=");
//		sqlCmd.append(",C_UOM_Length_ID=");
//		sqlCmd.append(",C_UOM_Time_ID=");
		sqlCmd.append(" WHERE AD_Client_ID=").append(getAD_Client_ID());
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no != 1) {
			String err = "ClientInfo not updated: ";
			m_info.append(msjError).append(err).append(defaultName);
			
			log.log(Level.SEVERE, err);
			return false;
		}
		
		m_info.append(msjExito).append("ClientInfo updated: Partner and Product: ").append(defaultName);

		//	Create Sales Rep for Client-User
		// Ya no es necesario crear este BPartner. 
		// Jesus Cantu - ticket 06355 — Socios de Negocios a nivel system y cliente 
		
		/* MBPartner bpCU = new MBPartner (m_ctx, 0, null);
		bpCU.setValue(AD_User_U_Name);
		bpCU.setName(AD_User_U_Name);
		bpCU.setDescription(AD_User_U_Name);
		bpCU.setBPGroup(bpg);
		bpCU.setIsEmployee(true);
		bpCU.setIsSalesRep(true);
		
		//Se coloca el TAXID Default para el BPartner Standard debido a cambios realizados en BPartner.
		//Jesus Cantu el 14 Agosto 2013.
		bpCU.setTaxID("XAXX010101000");
		
		if (bpCU.save(m_trx.getTrxName())) {
			m_info.append(msjExito).append(Msg.translate(m_lang, "SalesRep_ID")).append("=").append(AD_User_U_Name);
		} else {
			log.log(Level.SEVERE, "SalesRep (User) NOT inserted");
			m_info.append(msjError).append("SalesRep (User) NOT inserted: ").append(AD_User_U_Name);
		}
		
		//  Location for Client-User
		MLocation bpLocCU = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, null);
		bpLocCU.save(m_trx.getTrxName());
		MBPartnerLocation bplCU = new MBPartnerLocation(bpCU);
		bplCU.setC_Location_ID(bpLocCU.getC_Location_ID());
		
		if (!bplCU.save()) {
			log.log(Level.SEVERE, "BP_Location (User) NOT inserted");
		}
		
		//  Update User
		sqlCmd = new StringBuffer ("UPDATE AD_User SET C_BPartner_ID=");
		sqlCmd.append(bpCU.getC_BPartner_ID()).append(" WHERE AD_User_ID=").append(AD_User_U_ID);
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no != 1) {
			log.log(Level.SEVERE, "User of SalesRep (User) NOT updated");
		}
		
		m_info.append(msjExito).append("User of SalesRep (User) updated: ").append(AD_User_U_ID);*/


		//	Create Sales Rep for Client-Admin

		// Ya no es necesario crear este BPartner. 
		// Jesus Cantu - ticket 06355 — Socios de Negocios a nivel system y cliente 
		
		/*MBPartner bpCA = new MBPartner (m_ctx, 0, null);
		bpCA.setValue(AD_User_Name);
		bpCA.setName(AD_User_Name);
		bpCA.setDescription(AD_User_Name);
		bpCA.setBPGroup(bpg);
		bpCA.setIsEmployee(true);
		bpCA.setIsSalesRep(true);
		
		//Se coloca el TAXID Default para el BPartner Standard debido a cambios realizados en BPartner.
		//Jesus Cantu el 14 Agosto 2013.
		bpCA.setTaxID("XAXX010101000");
		
		if (bpCA.save(m_trx.getTrxName())) {
			m_info.append(msjExito).append(Msg.translate(m_lang, "SalesRep_ID")).append("=").append(AD_User_Name);
		} else {
			log.log(Level.SEVERE, "SalesRep (Admin) NOT inserted");
			m_info.append(msjError).append("SalesRep (Admin) NOT inserted: ").append(AD_User_Name);
		}
		
		//  Location for Client-Admin
		MLocation bpLocCA = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, null);
		bpLocCA.save(m_trx.getTrxName());
		
		MBPartnerLocation bplCA = new MBPartnerLocation(bpCA);
		bplCA.setC_Location_ID(bpLocCA.getC_Location_ID());
		
		if (!bplCA.save()) {
			log.log(Level.SEVERE, "BP_Location (Admin) NOT inserted");
		}
		
		//  Update User
		sqlCmd = new StringBuffer ("UPDATE AD_User SET C_BPartner_ID=");
		sqlCmd.append(bpCA.getC_BPartner_ID()).append(" WHERE AD_User_ID=").append(AD_User_ID);
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no != 1) {
			log.log(Level.SEVERE, "User of SalesRep (Admin) NOT updated");
		}

		m_info.append(msjExito).append("User of SalesRep (Admin) updated: ").append(AD_User_ID);*/
		
		//  Payment Term
		/* Se comenta codigo ya que se crea abajo en el metodo de clientConfiguration
		 * el payment term de immediate para cada client Jesus Cantu
		 */
		
		/*int C_PaymentTerm_ID = getNextID(getAD_Client_ID(), "C_PaymentTerm");
		sqlCmd = new StringBuffer ("INSERT INTO C_PaymentTerm ");
		sqlCmd.append("(C_PaymentTerm_ID,").append(m_stdColumns).append(",");
		sqlCmd.append("Value,Name,NetDays,GraceDays,DiscountDays,Discount,DiscountDays2,Discount2,IsDefault) VALUES (");
		sqlCmd.append(C_PaymentTerm_ID).append(",").append(m_stdValues).append(",");
		sqlCmd.append("'Immediate','Immediate',0,0,0,0,0,0,'Y')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "PaymentTerm NOT inserted");*/

		//  Project Cycle
		C_Cycle_ID = getNextID(getAD_Client_ID(), "C_Cycle");
		sqlCmd = new StringBuffer ("INSERT INTO C_Cycle ");
		sqlCmd.append("(C_Cycle_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Name,C_Currency_ID) VALUES (");
		sqlCmd.append(C_Cycle_ID).append(",").append(m_stdValues).append(", ");
		sqlCmd.append(defaultEntry).append(C_Currency_ID).append(")");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no != 1) {
			log.log(Level.SEVERE, "Cycle NOT inserted");
			m_info.append(msjError).append("Cycle NOT inserted: ").append(defaultName);
		}
		
		m_info.append(msjExito).append("Cycle_ID = ").append(C_Cycle_ID).
		append(" OrgName: ").append(defaultDescription);

		/**
		 *  Organization level data	===========================================
		 */

		//	Create Default Project
		int C_Project_ID = getNextID(getAD_Client_ID(), "C_Project");
		sqlCmd = new StringBuffer ("INSERT INTO C_Project ");
		sqlCmd.append("(C_Project_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Value,Name,C_Currency_ID,IsSummary) VALUES (");
		sqlCmd.append(C_Project_ID).append(",").append(m_stdValuesOrg).append(", ");
		sqlCmd.append(defaultEntry).append(defaultEntry).append(C_Currency_ID).append(",'N')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		
		if (no == 1) {
			m_info.append(msjExito).append(Msg.translate(m_lang, "C_Project_ID")).append("=").append(defaultName);
		} else {
			log.log(Level.SEVERE, "Project NOT inserted");
			m_info.append(msjError).append("Project NOT inserted: ").append(defaultName);
		}
		
		//  Default Project
		if (m_hasProject) {
			sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
			sqlCmd.append("C_Project_ID=").append(C_Project_ID);
			sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
			sqlCmd.append(" AND ElementType='PJ'");
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
			
			if (no != 1) {
				log.log(Level.SEVERE, "AcctSchema ELement Project NOT updated");
				m_info.append(msjError).append("AcctSchema ELement Project NOT updated: ").append(defaultName);
			}
			
			m_info.append(msjError).append("AcctSchema ELement Project updated: C_Project_ID = ").append(C_Project_ID);
		}

		//  CashBook
		MCashBook cb = new MCashBook(m_ctx, 0, null);
		cb.setName(defaultName);
		cb.setDescription(defaultDescription);
		cb.setC_Currency_ID(C_Currency_ID);
		
		if (cb.save(m_trx.getTrxName())) {
			m_info.append(msjExito).append(Msg.translate(m_lang, "C_CashBook_ID")).append("=").append(defaultName);
		} else {
			log.log(Level.SEVERE, "CashBook NOT inserted");
			m_info.append(msjError).append("CashBook NOT inserted: ").append(defaultName);
		}
		
		//
		m_trx.commit();
		m_trx.close();
		log.info("finish createEntities");
		return true;
	}   //  createEntities

	/**
	 *  Create Preference
	 *  @param Attribute attribute
	 *  @param Value value
	 *  @param AD_Window_ID window
	 */
	private void createPreference (String Attribute, String Value, int AD_Window_ID)
	{
		int AD_Preference_ID = getNextID(getAD_Client_ID(), "AD_Preference");
		StringBuffer sqlCmd = new StringBuffer ("INSERT INTO AD_Preference ");
		sqlCmd.append("(AD_Preference_ID,").append(m_stdColumns).append(",");
		sqlCmd.append("Attribute,Value,AD_Window_ID) VALUES (");
		sqlCmd.append(AD_Preference_ID).append(",").append(m_stdValues).append(",");
		sqlCmd.append("'").append(Attribute).append("','").append(Value).append("',");
		if (AD_Window_ID == 0)
			sqlCmd.append("NULL)");
		else
			sqlCmd.append(AD_Window_ID).append(")");
		int no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "Preference NOT inserted - " + Attribute);
	}   //  createPreference

	
	/**************************************************************************
	 * 	Get Next ID
	 * 	@param AD_Client_ID client
	 * 	@param TableName table name
	 */
	private int getNextID (int AD_Client_ID, String TableName)
	{
		//	TODO: Exception 
		return DB.getNextID (AD_Client_ID, TableName, m_trx.getTrxName());
	}	//	getNextID

	/**
	 *  Get Client
	 *  @return AD_Client_ID
	 */
	public int getAD_Client_ID()
	{
		return m_client.getAD_Client_ID();
	}
	public int getAD_Org_ID()
	{
		return m_org.getAD_Org_ID();
	}
	public int getAD_User_ID()
	{
		return AD_User_ID;
	}
	public String getInfo()
	{
		return m_info.toString();
	}
	
	/**
	 * Creates initial area and the service stations associated to administrator role
	 * @param AD_Client_ID
	 * @param AD_Role_ID
	 * @param orgName
	 * @param areaType
	 * @author lorena lama : Expert
	 */
	private boolean createXPT(int AD_Client_ID, int AD_Role_ID, String orgName, String areaType) {
		log.log(Level.INFO, "Creating area, service station and access for admin role");
		StringBuffer sql = new StringBuffer();
		int no = 0;
		areaType = DB.TO_STRING(areaType);
		String name = DB.TO_STRING(orgName);
		if (name == null || name.length() == 0)
			name = DB.TO_STRING("newArea");
		
		//Create area
		int EXME_Area_ID = getNextID(AD_Client_ID, MEXMEArea.Table_Name);
		sql.append("INSERT INTO EXME_Area(").append(m_stdColumns) // standar columns
				.append(",").append(MEXMEArea.COLUMNNAME_EXME_Area_ID) //
				.append(",").append(MEXMEArea.COLUMNNAME_Value) //
				.append(",").append(MEXMEArea.COLUMNNAME_Name) //
				.append(",").append(MEXMEArea.COLUMNNAME_Description) //
				.append(",").append(MEXMEArea.COLUMNNAME_TipoArea)//
				.append(")  VALUES ( ").append(m_stdValuesOrg)//
				.append(",").append(EXME_Area_ID)//
				.append(",").append(name)//
				.append(",").append(name)//
				.append(",").append(name)//
				.append(",").append(areaType)//
				.append(")");

		no = DB.executeUpdate(sql.toString(), m_trx.getTrxName());
		if (no != 1){
			log.log(Level.SEVERE, "Org Area NOT inserted");
			m_info.append("Org Area NOT inserted");
			return false;
		}
		// Info - Admin Role
		m_info.append(Msg.translate(m_lang, "EXME_Area_ID")).append("=").append(name).append("\n");
		//Create service station
		sql = new StringBuffer();
		int EXME_EstServ_ID = getNextID(AD_Client_ID, MEXMEEstServ.Table_Name);
		sql.append("INSERT INTO EXME_EstServ(").append(m_stdColumns) // standar columns
				.append(",").append(MEXMEEstServ.COLUMNNAME_EXME_EstServ_ID) //
				.append(",").append(MEXMEEstServ.COLUMNNAME_EXME_Area_ID) //
				.append(",").append(MEXMEEstServ.COLUMNNAME_AD_OrgTrx_ID) //
				.append(",").append(MEXMEEstServ.COLUMNNAME_Value) //
				.append(",").append(MEXMEEstServ.COLUMNNAME_Name) //
				.append(",").append(MEXMEEstServ.COLUMNNAME_Description) //
				.append(",").append(MEXMEEstServ.COLUMNNAME_TipoArea)//
				.append(",").append(MEXMEEstServ.COLUMNNAME_UsaLPCECOFI)//
				.append(",").append(MEXMEEstServ.COLUMNNAME_IsBancoOjos)//
				.append(")  VALUES ( ").append(m_stdValuesOrg)//
				.append(",").append(EXME_EstServ_ID)//
				.append(",").append(EXME_Area_ID)//
				.append(",").append(m_org.getAD_Org_ID())//
				.append(",").append(name)//
				.append(",").append(name)//
				.append(",").append(name)//
				.append(",").append(areaType)//
				.append(",").append(DB.TO_STRING("N"))//
				.append(",").append(DB.TO_STRING("N")).append(")");

		no = DB.executeUpdate(sql.toString(), m_trx.getTrxName());
		if (no != 1){
			log.log(Level.SEVERE, "Org Service Station NOT inserted");
			m_info.append("Org Service Station NOT inserted");
			return false;
		}
		// Info - Admin Role
		m_info.append(Msg.translate(m_lang, "EXME_EstServ_ID")).append("=").append(name).append("\n");
		// Create Role - Service Station
		sql = new StringBuffer();
		sql.append("INSERT INTO EXME_RoleEstServ(").append(m_stdColumns) // standar columns
				.append(",").append(X_EXME_RoleEstServ.COLUMNNAME_EXME_EstServ_ID) //
				.append(",").append(X_EXME_RoleEstServ.COLUMNNAME_AD_Role_ID) //
				.append(")  VALUES ( ").append(m_stdValuesOrg)//
				.append(",").append(EXME_EstServ_ID)//
				.append(",").append(AD_Role_ID)//
				.append(")");

		no = DB.executeUpdate(sql.toString(), m_trx.getTrxName());
		if (no != 1){
			log.log(Level.SEVERE, "Role - Service Station NOT inserted");
			m_info.append("Role - Service Station NOT inserted");
			return false;
		}
		return true;
	}
	
	/**
	 * Crear la informacion inicial del cliente
	 * @param ctx el contexto de la aplicación
	 * @param locale el objeto locale que contiene la regionalización
	 * @param trxName la transacción actual
	 * @author Modificado por Jesus Cantu
	 */
	public void createInitialInfo(Properties ctx, Locale locale, String trxName) {
		
		List<MEXMEArea> areaLst= new ArrayList<MEXMEArea>();
	
		List<MEXMEArea> templates = MEXMEArea.getTemplates(ctx, (String)ctx.get("#TipoCliente"), null);
		for(MEXMEArea area : templates){
			MEXMEArea newrole = new MEXMEArea(ctx, 0, trxName);
			PO.copyValues(area, newrole, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
			newrole.setIsTemplate(false);
			newrole.setIsAutoGenerated(true);
			areaLst.add(newrole);
		}
		
		ctx.put("#ListDefaultAreas", areaLst);
		
		// Tipos de Habitacion
		String outpatientRoom = Utilerias.getMessage(ctx, locale, "tipoHab.outpatientRoom");
		
		MEXMETipoHabitacion tipoHabitacion = new MEXMETipoHabitacion(Env.getCtx(), 0 , trxName); 

		tipoHabitacion.setValue(outpatientRoom);
		tipoHabitacion.setName(outpatientRoom);
		tipoHabitacion.setDescription(Utilerias.getMessage(ctx, locale, "tipoHab.outpatientRoomAmbBeds"));

		MEXMETipoHabitacion tipoHabitacion2 = new MEXMETipoHabitacion(Env.getCtx(), 0 , trxName); 
				
		String emergencyRoom = Utilerias.getMessage(ctx, locale, "tipoHab.emergencyRoom");
		
		tipoHabitacion2.setValue(emergencyRoom);
		tipoHabitacion2.setName(emergencyRoom);
		tipoHabitacion2.setDescription(Utilerias.getMessage(ctx, locale, "tipoHab.emergencyRoomBeds"));
		
		MEXMETipoHabitacion tipoHabitacion3 = new MEXMETipoHabitacion(Env.getCtx(), 0 , trxName); 

		String semiPrivRoom = Utilerias.getMessage(ctx, locale, "tipoHab.semiPrivRoom");
		
		tipoHabitacion3.setValue(semiPrivRoom);
		tipoHabitacion3.setName(semiPrivRoom);
		tipoHabitacion3.setDescription(Utilerias.getMessage(ctx, locale, "tipoHab.semiPrivRoomTwoBeds"));


		MEXMETipoHabitacion tipoHabitacion4 = new MEXMETipoHabitacion(Env.getCtx(), 0 , trxName); 
		
		String standardWard = Utilerias.getMessage(ctx, locale, "tipoHab.standardWard");
		
		tipoHabitacion4.setValue(standardWard);
		tipoHabitacion4.setName(standardWard);
		tipoHabitacion4.setDescription(Utilerias.getMessage(ctx, locale, "tipoHab.standardWardThreeBeds"));

		MEXMETipoHabitacion tipoHabitacion5 = new MEXMETipoHabitacion(Env.getCtx(), 0 , trxName); 
		
		String privRoom = Utilerias.getMessage(ctx, locale, "tipoHab.privRoom");
		
		tipoHabitacion5.setValue(privRoom);
		tipoHabitacion5.setName(privRoom);
		tipoHabitacion5.setDescription(privRoom);

		MEXMETipoHabitacion tipoHabitacion6 = new MEXMETipoHabitacion(Env.getCtx(), 0 , trxName); 
		
		String vipA = Utilerias.getMessage(ctx, locale, "tipoHab.vipA");
		
		tipoHabitacion6.setValue(vipA);
		tipoHabitacion6.setName(vipA);
		tipoHabitacion6.setDescription(vipA);
		
		MEXMETipoHabitacion tipoHabitacion7 = new MEXMETipoHabitacion(Env.getCtx(), 0 , trxName); 
		
		String vipB = Utilerias.getMessage(ctx, locale, "tipoHab.vipB");
		
		tipoHabitacion7.setValue(vipB);
		tipoHabitacion7.setName(vipB);
		tipoHabitacion7.setDescription(vipB);


		MEXMETipoHabitacion tipoHabitacion8 = new MEXMETipoHabitacion(Env.getCtx(), 0 , trxName); 
		
		String presidential = Utilerias.getMessage(ctx, locale, "tipoHab.presidential");
		
		tipoHabitacion8.setValue(presidential);
		tipoHabitacion8.setName(presidential);
		tipoHabitacion8.setDescription(presidential);

		MEXMETipoHabitacion tipoHabitacion9 = new MEXMETipoHabitacion(Env.getCtx(), 0 , trxName); 
		
		String pediatric = Utilerias.getMessage(ctx, locale, "tipoHab.pediatric");
		
		tipoHabitacion9.setValue(pediatric);
		tipoHabitacion9.setName(pediatric);
		tipoHabitacion9.setDescription(pediatric);

		List<MEXMETipoHabitacion> roomTypeLst= new ArrayList<MEXMETipoHabitacion>();
		roomTypeLst.add(tipoHabitacion);
		roomTypeLst.add(tipoHabitacion2);
		roomTypeLst.add(tipoHabitacion3);
		roomTypeLst.add(tipoHabitacion4);
		roomTypeLst.add(tipoHabitacion5);
		roomTypeLst.add(tipoHabitacion6);
		roomTypeLst.add(tipoHabitacion7);
		roomTypeLst.add(tipoHabitacion8);
		roomTypeLst.add(tipoHabitacion9);
		
		ctx.put("#ListDefaultRoomTypes", roomTypeLst);
		
	}
	
	/**
	 * Guardar la información Inicial del cliente
	 * 
	 * @param ctx contextop de la aplicación
	 * @param taxRate la tasa de impuestos
	 * @param locale El objeto locale que contendrá la regionalización
	 * @param trxName la transacción actual
	 * @return true si la información fue guardada exitosamente, de lo contrario false.
	 * @author Modificado por Jesus Cantu
	 */
	@SuppressWarnings("unchecked")
	public boolean saveInitialInfo(Properties ctx, double taxRate, Locale locale, String trxName) {

		boolean retValue = true;
		this.customProps = ctx;
		this.taxRate = taxRate;
		int estServID = 0;
		int warehouseID = 0;
		int warehouseAdmission = 0;
		String slClientName = null;
		int ilAdminUserId = 100; //Colocar SuperUser ya que se elimino la creacion de usuario admin de cada cliente.
		
		if (isParentOrg) {
			m_org = (MOrg) customProps.get("#AD_Org");
			m_client = new MClient(ctx, Env.getAD_Client_ID(m_ctx), null);
			customProps.put("#AD_Client", m_client);
			customProps.put("#AD_Client_ID", m_client.getAD_Client_ID());
			
			//Nombre del cliente para relacionar despues el perfil Admin a todas las 
			//Estaciones de Servicio. Jesus Cantu.
			slClientName = m_client.getName();
			
			//Si es Organizacion padre, entonces no tenemos el ID del Usuario Admin para
			//Los recordatorios de AD_Scheduler, entonces hay que obtenerlo.
			//Se comenta, ya no es necesario
			//ilAdminUserId = MUser.getAdminUserId(trxName, m_client.getAD_Client_ID());
			
		} else {
			customProps.put("#AD_Client_ID", String.valueOf(getAD_Client_ID()));
			customProps.put("#AD_Org_ID", String.valueOf(getAD_Org_ID()));
			
			// Obtenemos del contexto el nombre del Cliente cuando viene de SBasicOrganization.
			slClientName = customProps.getProperty("#AD_Client_Name");
			
			//Colocar SuperUser - Jesus Cantu - Ya que se elimino la creacion de los usuarios
			//Admin para cada cliente, 4 Febrero 2014.
			//ilAdminUserId = 100;

		}

		m_info = new StringBuffer();
		m_info.append("\n----\n");
		String msjExito = "\n" + Utilerias.getMessage(m_ctx, null, "msj.exito.reglaCuestionario.registroCorrecto") + ": ";
		String msjError = "\n" + Utilerias.getMessage(m_ctx, null, "msj.errorsavereg") + ": ";

		ctx.setProperty("#AD_Client_ID", String.valueOf(getAD_Client_ID()));
		ctx.setProperty("#AD_Org_ID", String.valueOf(getAD_Org_ID()));
		
		//Obtenemos del contexto el timezone para guardarlo en ad_orginfo.
		TimeZone tz = (TimeZone) customProps.get(Env.TIMEZONE);
		
		if (isParentOrg) {
			
			String defaultName = Msg.translate(m_lang, "Standard");
			
			// En el metodo createEntitiesParentOrg se crearon los datos concatenados para evitar los unique constraints
			String defaultNameOrg = getAD_Org_ID() + " " + defaultName;
			
			// TODO debe ser de la tabla de configuraciones, verificar como funciona cuando se cree una nueva org. Jesus
			// Se le manda en mayusculas ya que el metodo lo busca asi. Jesus Cantu
			MEXMETurnos2 turnos2 = MEXMETurnos2.getFullDay(Utilerias.getMessage(ctx, locale, "msj.fullDaySchd").toUpperCase(), ctx);
			customProps.put("#FULLDAY", turnos2);
			
			// Se le manda en mayusculas ya que el metodo lo busca asi. Jesus Cantu
			MCharge charge = MCharge.getCharge(Utilerias.getMessage(ctx, locale, "msj.cargoVentas").toUpperCase(), ctx);
			customProps.put("#C_Charge", charge.getC_Charge_ID());
			
			//Se le manda concatenado el id de la Org, ya que asi se creo en el metodo createEntitiesParentOrg.
			MEXMEBPGroup bpg = MEXMEBPGroup.getIDFromValue(ctx, "%" + defaultNameOrg, trxName);
			customProps.put("#BP_Group", bpg.getC_BP_Group_ID());
			
			MProduct deducible = null;
			try {
				//Se le manda el contexto de logueo ya que ocupamos el producto de la Organizacion de Logueo
				//No de la que acabo de crear, pq no existe.
				//Jesus Cantu el 17 Abril 2013
				deducible = MEXMEProduct.getProducto(Env.getCtx(), "%" + Utilerias.getMessage(ctx, locale, "msj.deductible"), trxName);
			} catch (Exception e) {
				return false;
			}
			customProps.put("#MProductDeducible", deducible.getM_Product_ID());
			
			MProduct coaseguro = null;
			
			try {
				//Se le manda el contexto de logueo ya que ocupamos el producto de la Organizacion de Logueo
				//No de la que acabo de crear, pq no existe.
				//Jesus Cantu el 17 Abril 2013				
				coaseguro = MEXMEProduct.getProducto(Env.getCtx(), "%" + Utilerias.getMessage(ctx, locale, "msj.coinsurance"), trxName);
			} catch (Exception e) {
				return false;
			}
			customProps.put("#MProductCoaseguro", coaseguro.getM_Product_ID());
			
			//TODO revisar valor
			customProps.put("#M_ProductCategory", coaseguro.getM_Product_Category_ID());
			customProps.put("#C_TaxCategory", coaseguro.getC_TaxCategory_ID());
			
			//No es necesario llamarlo ya que el C_BPartner_ID tiene el valor del partner creado en createEntitiesParentOrg.
			//MBPartner partner = MBPartner.get(ctx, defaultNameOrg, trxName);
			//C_BPartner_ID = partner.getC_BPartner_ID();
			
			//No es necesario llamarlo, ya que en el metodo createEntitiesParentOrg
			//Se guarda el id del MPriceList creado
			//MPriceList priceList = new MPriceList(ctx, MPriceList.get(ctx, defaultNameOrg, trxName), trxName);
			
			//M_PriceList_ID = priceList.getM_PriceList_ID();
			
			// Se coloca en contexto el ID de la Version Lista de Precios Standar para la nueva 
			// org creado en el metodo createEntitiesParentOrg
			//Ya no es necesario llamar este metodo ya que el M_PriceList_Version_ID esta actualizado por el metodo
			//createEntitiesParentOrg, solo es agregarlo al contexto.
			//customProps.put("#PLVStandard", MPriceListVersion.getIdbyPriceList(Env.getCtx(), M_PriceList_ID, trxName));
			
			customProps.put("#PLVStandard", M_PriceList_Version_ID);
			
			// Se coloca en contexto el ID de la Lista de Precios Standar para la nueva 
			// org creado en el metodo createEntitiesParentOrg
			customProps.put("#PLStandard", M_PriceList_ID);
			
			/*
			 * Traer la familia de productos de servicios propios creada para este cliente 
			 * para ser utilizada en los tipos de habitacion cuando esta tiene un costo 
			 * y vaya a crearse un producto del tipo de habitacion. Despues de obtenerla
			 * colocarla en contexto para que sea utilizada en SRoomType.doIt. Jesus Cantu. 
			 */
			
			//Se regionaliza Servicios Propios. Jesus Cantu.
			String slOwnServices = Utilerias.getMessage(ctx, locale, "msj.ownServices");
			
			//Se obtiene la familia de productos de Servicios Propios de System.
			//no es necesaria la transaccion.			
			MEXMEProductFam ilEXME_ProdFam_OwnServ = MEXMEProductFam.get(ctx, slOwnServices, 0, null);
			
			//Se coloca en Contexto.
			customProps.put("#MProductFam", ilEXME_ProdFam_OwnServ);
			
			
		} else {
			if(!clientConfiguration(ctx, msjError, msjExito, locale, trxName)){
				return false;
			}
		}

		if (ctx.get("#ListDefaultAreas") != null) {
			List<MEXMEArea> lstArea = (ArrayList<MEXMEArea>) ctx.get("#ListDefaultAreas");
			List<MWarehouse> listWarehouse = new ArrayList<MWarehouse>();
			List<MEXMEEstServ> listEstServ = new ArrayList<MEXMEEstServ>();
			List<MEstServAlm> listEstServAlm = new ArrayList<MEstServAlm>();
			List<MLocator> listMLocator = new ArrayList<MLocator>();
			String name = "";
			
			//Traer el perfil Admin para relacionarlo a las estaciones de servicio.
			if (slClientName != null) {
				slClientName = slClientName.concat(" Admin");
			}
			
			MRole objPerfilAdmin = MRole.getPerfilAdmin(ctx, getAD_Client_ID(), slClientName, trxName);
			
			//Colocar el perfil admin en contexto para agregarlo a las areas que se requieren crear manualmente.
			//Jesus Cantu. SArea.doit()
			customProps.put("#ADMIN_ROLE", objPerfilAdmin);
			
			// A partir de las areas creamos tmb las estaciones y organizaciones
			for (int i = 0; i < lstArea.size(); i++){
				
				MEXMEArea area = (MEXMEArea) lstArea.get(i); 
				name = area.getName();
				
				/* El indice es ad_client - value por lo que si se crea una nueva organizacion con
				 * el wizard, este no funcionaria si no se le concatena el ID.
				 */
				
				area.setValue(m_org.getAD_Org_ID() + " " + area.getValue());
				
				//ya no es necesario concatenar name  y desc
				
				/*
				area.setName(abc(m_org.getName()) + " " + area.getName());
				area.setDescription(abc(m_org.getName()) + " " + area.getName());*/
								
				area.setName(name);
				area.setDescription(name);
				
				//area.setTipoArea(MEXMEArea.TIPOAREA_Other);
				
				if (!save(area, true, trxName)) {
					m_info.append(msjError).append(name);
					return false;
				}
				m_info.append(msjExito).append(name);
				
				//Creamos la organizacion
				MOrg org = addOrg(area.getValue(), trxName);
				//Seteamos nombre y organizacion igual que el area
				org.setName(name);
				org.setDescription(name);
				
				if (!save(org, false, trxName)) {
					m_info.append(msjError).append("Org: ").append(area.getValue());
					return false;
				}
				
				m_info.append(msjExito).append("Org: ").append(area.getValue());

				//Se crea el registro en orginfo
				MOrgInfo info = setOrgTrx(org, trxName);
				
				//Guardamos la Zona Horaria seleccionada.
				info.setTimeZone(tz.getID());
				
				if (!info.save(trxName)) {
					m_info.append(msjError).append("Org Info");
					return false;
				}
				
				m_info.append(msjExito).append("Org Info");
				
				//Creamos el Almacen
				MEXMETurnos2 turnos2 = (MEXMETurnos2)ctx.get("#FULLDAY");
				MWarehouse warehouse = addWarehouse(turnos2 != null ? turnos2.getEXME_Turnos2_ID() : 0, name, trxName);
				
				//Setear tipo de almacen
				
				//Si el Almacen es LABORATORIO
				if (name != null && name.equalsIgnoreCase(Utilerias.getMessage(ctx, locale, "msj.labWarehouse"))) { 
					warehouse.setType(MWarehouse.TYPE_Laboratory);
				//Si es Imagenologia	
				} else if (name != null && name.equalsIgnoreCase(Utilerias.getMessage(ctx, locale, "msj.imagWarehouse"))) { 
					warehouse.setType(MWarehouse.TYPE_Radiology);
				//Si es CEYE	
				} else if (name != null && name.equalsIgnoreCase(Utilerias.getMessage(ctx, locale, "msj.ceyeWarehouse"))) {
					warehouse.setType(MWarehouse.TYPE_Sterilization);
				// Si no es ninguno de los anteriores
				} else { 
					warehouse.setType(MWarehouse.TYPE_Others);
				}
				
				
				if (!save(warehouse, true, trxName)) {
					m_info.append(msjError).append(name);
					return false;
				}
				
				m_info.append(msjExito).append(name);
				listWarehouse.add(warehouse);
				
				if (name.toUpperCase().equals(Utilerias.getMessage(ctx, locale, "msj.farmacia").toUpperCase())){
					//Si es farmacia obtenemos su id del almacen, para esto debe estar configurada el template
					//de farmacia en exme_areaconfigurador
					warehouseID = warehouse.getM_Warehouse_ID();
				}
				
				//Creamos un registro en MLocator
				MLocator locator = addLocator(name, trxName, warehouse.getM_Warehouse_ID());
				
				if (!save(locator, true, trxName)) {
					m_info.append(msjError).append("Locator: ").append(name);
					return false;
				}
				
				listMLocator.add(locator);
				m_info.append(msjExito).append("Locator: ").append(name);
				
				//Creamos la estacion de servicio
				MEXMEEstServ estServ = addEstServ(area.getEXME_Area_ID(), org.getAD_Org_ID(), name, trxName);
				if (!save(estServ, true, trxName)) {
					m_info.append(msjError).append("SS: ").append(name);
					return false;
				}
				
				listEstServ.add(estServ);
				m_info.append(msjExito).append("SS: ").append(name);
				
				//Relacionar la Estacion de Servicio Creada al Perfil Admin. Requerimiento de los wizards
				//Revisado con Helio el 13 de Junio del 2012. Todas las estaciones de servicio se relacionaran
				//Al perfil Admin.
				
				if (objPerfilAdmin != null && objPerfilAdmin.getAD_Role_ID() > 0) { 

					MEXMERoleEstServ objRoleEstServ = new MEXMERoleEstServ(ctx, 0, trxName);
					objRoleEstServ.setAD_Client_ID(getAD_Client_ID());
					objRoleEstServ.setAD_Org_ID(getAD_Org_ID());
					objRoleEstServ.setEXME_EstServ_ID(estServ.getEXME_EstServ_ID());
					objRoleEstServ.setAD_Role_ID(objPerfilAdmin.getAD_Role_ID());
					
					if (!objRoleEstServ.save()) {
						m_info.append(msjError).append("Relacionando la estacion: ").append(estServ.getName());
						m_info.append("Al perfil Admin: ").append(objPerfilAdmin.getName());
						return false;
					}
					
					m_info.append(msjExito).append("SS Agregada al Perfil Admin: ").append(name);
					m_info.append(msjExito).append(" Perfil Admin: ").append(objPerfilAdmin.getName());

				}
				// Termina la relacion de las estaciones de servicio al perfil admin
				
				//Si la estacion es Admision
				if (name.toUpperCase().equals(Utilerias.getMessage(ctx, locale, "msj.admission").toUpperCase())) {
					estServID = estServ.getEXME_EstServ_ID();
					ctx.put("#WHPhysician", warehouse.getM_Warehouse_ID());
					
					//Obtenemos el id del almacen de admision para guardarlo en el registro de exmeMejoras
					warehouseAdmission = warehouse.getM_Warehouse_ID();
				}
				
				//Si la estacio es Consulta
				if (name.toUpperCase().equals(Utilerias.getMessage(ctx, locale, "msj.physicianOffice").toUpperCase())) {
					ctx.put("#SS-PO", estServ);
					ctx.put("#WHPhysician", warehouse.getM_Warehouse_ID());
				}
				
				//Se hace la relacion de estacion de servicio con almacen
				MEstServAlm alm = addEstServAlm(trxName, warehouse.getM_Warehouse_ID(), estServ.getEXME_EstServ_ID());
				if (!save(alm, true, trxName)) {
					m_info.append(msjError).append("ALM: ").append(name);
					return false;
				}
				
				listEstServAlm.add(alm);
				m_info.append(msjExito).append("ALM: ").append(name);
				
				//TODO: revisar porque no siempre es farmacia, sobre todo cuando el template de farmaia no existe
				if (warehouseID == 0) {
					warehouseID = warehouse.getM_Warehouse_ID();
				}
			}
			ctx.put("#ListDefaultMLocator", listMLocator);
			ctx.put("#ListDefaultWarehouse", listWarehouse);
			ctx.put("#ListDefaultEstServ", listEstServ);
			ctx.put("#ListDefaultEstServAlm", listEstServAlm);			
		}	
		
		//TODO: No siempre es farmacia, revisar.
		MEXMEConfigEC ConfigEC = addConfigEC(Utilerias.getMessage(ctx, locale, "msj.mrSetup"), warehouseID, trxName); //warehouse = PHARMACY
		if (!save(ConfigEC, true, trxName)) {
			return false;
		}

		MEXMEConfigEspecial configEspecial = addConfig(trxName);
		if (!save(configEspecial, true, trxName)) {
			return false;
		}

		/*
		 * Se comenta bloque de codigo, se tiene que crear el almacen de Farmacia dentro del for
		 * para esto asegurarse que en EXME_AREACONFIGURADOR se tenga el registro Farmacia como template.
		 * Jesus Cantu
		 */
		/*MEXMETurnos2 turnos2 = (MEXMETurnos2)ctx.get("#FULLDAY");
		MWarehouse warehouse = addWarehouse(turnos2 != null ? turnos2.getEXME_Turnos2_ID() : 0, "Pharmacy", trxName);
		if (!save(warehouse, true, trxName)) {
			m_info.append(msjError + warehouse.getName());
			return false;
		}*/
		
		MEXMEMejoras mejoras = addConfigMejoras(estServID, warehouseAdmission, trxName); //estServ  = Admission
		if (!save(mejoras, true, trxName)) {
			return false;
		}
		
		MEXMEStopPolicy.createConfig(ctx, trxName);

		//Creamos la configuracion de precios
		String priceSettings = Utilerias.getMessage(ctx, locale, "msj.pricesSettings");
		MEXMEConfigPre confPre = addPriceSettings(priceSettings, trxName);
		
		if (!save(confPre, true, trxName)) {
			m_info.append(msjError).append(priceSettings);
			return false;
		}
		
		m_info.append(msjExito).append(priceSettings);
		
		String tipoCliente = (String) ctx.get("#TipoCliente");
		
		//Se crea la configuracion de enfermeria
		if (tipoCliente.equals(MEXMETipoConfigurador.TIPOAREA_AMBULATORYSURGERYCENTER) || tipoCliente.equals(MEXMETipoConfigurador.TIPOAREA_HOSPITALCENTER) 
				|| tipoCliente.equals(MEXMETipoConfigurador.TIPOAREA_OUTPATIENTCARECENTER)) {
			
			MEXMEConfigEnf confEnf = addConfigEnfermeria(ctx, trxName);
			
			if (!save(confEnf, true, trxName)) {
				m_info.append(msjError).append("addConfigEnfermeria");
				return false;
			}
			
			m_info.append(msjExito).append("addConfigEnfermeria");
		}
		
		MFormato facDir = addFormato(trxName, "FacDir");
		if (!save(facDir, true, trxName)) {
			m_info.append(msjError).append("Billing");
			return false;
		}
		
		MDocMedsys pfd = addDocMedsys(trxName, MDocMedsys.PREFACTURACE, facDir.getEXME_Formato_ID());
		if (!save(pfd, true, trxName)) {
			m_info.append(msjError).append("Billing");
			return false;
		}
		
		MDocMedsys fd = addDocMedsys(trxName, MDocMedsys.FACTURACE, facDir.getEXME_Formato_ID());
		if (!save(fd, true, trxName)) {
			m_info.append(msjError).append("Billing");
			return false;
		}
		
		MFormato facExt = addFormato(trxName, "facturaExt");
		if (!save(facExt, true, trxName)) {
			m_info.append(msjError).append("Billing");
			return false;
		}
		
		MDocMedsys pfe = addDocMedsys(trxName, MDocMedsys.PREFACTURAEXT, facExt.getEXME_Formato_ID());
		if (!save(pfe, true, trxName)) {
			m_info.append(msjError + "Billing");
			return false;
		}
		
		MDocMedsys fe = addDocMedsys(trxName, MDocMedsys.FACTURAEXT, facExt.getEXME_Formato_ID());
		if (!save(fe, true, trxName)) {
			m_info.append(msjError + "Billing");
			return false;
		}
		
		if (ctaPacDocId > 0) {
			MEXMEConfigDocType docCta = addDocTypeCtaPac(trxName);
			if (!save(docCta, true, trxName)) {
				m_info.append(msjError).append("CtaPac DocType");
				return false;
			}
		}
		
		/*
		 * Crear el proceso para recordatorios por cada Organizacion.
		 * Se crea un registro en AD_Scheduler por Organizacion, y 2 
		 * registros en AD_Scheduler_Para relacionados a el registro
		 * padre. Jesus Cantu. 18 de Junio 2012. Requerimiento revisado 
		 * con Helio.
		 */
		
		
		MScheduler objlHorario = new MScheduler(ctx, 0, trxName);
		objlHorario.setAD_Client_ID(getAD_Client_ID());
		objlHorario.setAD_Org_ID(getAD_Org_ID());
		objlHorario.setName("Patient Reminder");
		objlHorario.setDescription("Patient Reminder");
		objlHorario.setAD_Process_ID(1200243); //Ad_Process de Patient Reminder.
		objlHorario.setFrequencyType(MScheduler.FREQUENCYTYPE_Minute); //Minutos
		objlHorario.setFrequency(5); //5 Minutos
		objlHorario.setSupervisor_ID(ilAdminUserId); // Supervisor del Usuario Admin Recien Creado.
		objlHorario.setKeepLogDays(7); //7 dias de log.
		objlHorario.setProcessing(false);
		objlHorario.setScheduleType(MScheduler.SCHEDULETYPE_Frequency);
		objlHorario.setMonthDay(0);
		//Lo creamos inactivo ya que se tiene que desactivar siempre que se crea.
		//Jesus Cantu.  4 Feb 2014.
		objlHorario.setIsActive(false);
		
		if (!objlHorario.save()) {
			m_info.append(msjError).append("Error creando el recordatorio por Organizacion: ");
			m_info.append("Cliente: ").append(getAD_Client_ID());
			m_info.append("Organizacion: ").append(getAD_Org_ID());
			return false;
		}
		
		//Crear ahora el detalle del registro en ad_scheduler_para
		//Registro para Cliente
		
		MSchedulerPara objDetClient = new MSchedulerPara(ctx, 0, trxName);
		objDetClient.setAD_Scheduler_ID(objlHorario.getAD_Scheduler_ID());
		objDetClient.setAD_Process_Para_ID(1200443); //Proceso de Cliente
		objDetClient.setAD_Client_ID(getAD_Client_ID());
		objDetClient.setAD_Org_ID(0);

		// Se coloca el registro del cliente
		objDetClient.setParameterDefault(String.valueOf(getAD_Client_ID()));
		objDetClient.setDescription(Utilerias.getMessage(ctx, locale, "msg.client"));
		
		if (!objDetClient.save()) {
			m_info.append(msjError).append("Error creando el detalle del recordatorio del cliente: ");
			m_info.append("Recordatorio ID: ").append(objlHorario.getAD_Scheduler_ID());
			m_info.append("Cliente: ").append(getAD_Client_ID());
			return false;
		}
		
		//Registro para Organizacion
		MSchedulerPara objDetOrg = new MSchedulerPara(ctx, 0, trxName);
		objDetOrg.setAD_Scheduler_ID(objlHorario.getAD_Scheduler_ID());
		objDetOrg.setAD_Process_Para_ID(1200444); //Proceso de Organizacion
		objDetOrg.setAD_Client_ID(getAD_Client_ID());
		objDetOrg.setAD_Org_ID(0);

		// Se coloca el registro del cliente
		objDetOrg.setParameterDefault(String.valueOf(getAD_Org_ID()));
		objDetOrg.setDescription(Utilerias.getMessage(ctx, locale, "msg.org"));
		
		if (!objDetOrg.save()) {
			m_info.append(msjError).append("Error creando el detalle del recordatorio de la Organizacion: ");
			m_info.append("Recordatorio ID: ").append(objlHorario.getAD_Scheduler_ID());
			m_info.append("Organizacion: ").append(getAD_Org_ID());
			return false;
		}
		
		// Si se creo todo bien			
		m_info.append(msjExito).append("Proceso de Recordatorio por Organizacion exitoso: ");
		m_info.append("Cliente: ").append(getAD_Client_ID());
		m_info.append(" Organización: ").append(getAD_Org_ID());
		
		//Termina proceso de recordatorio por Organizacion
		
		//Creacion de Registro de facturacion Electronica: EXME_CONFIGFE
		//Registro por Cliente-Organizacion. Jesus Cantu. 27 Agosto 2012.
		
		MEXMEConfigFE objlConfigFE = new MEXMEConfigFE(ctx, 0, trxName);
		objlConfigFE.setRFC_Nacional(Constantes.RFC_NACIONAL);
		objlConfigFE.setRFC_Extranjero(Constantes.RFC_EXTRANJERO);
		objlConfigFE.setCaracPermitidos(Constantes.CARAC_PERMITIDOS);
		objlConfigFE.setSerieDocFac("1000000");
		objlConfigFE.setSerieDocND("3000000");
		objlConfigFE.setSerieDocNC("2000000");
		
		if (!objlConfigFE.save()) {
			m_info.append(msjError).append("Error creando el registro de Configuración Electrónica: ");
			m_info.append("Cliente: ").append(getAD_Client_ID());
			m_info.append("Organizacion: ").append(getAD_Org_ID());
			return false;
		}
		
		// Si se creo todo bien			
		m_info.append(msjExito).append("Se ha creado el registro de Configuración de Facturación Electrónica correctamente: ");
		m_info.append("Cliente: ").append(getAD_Client_ID());
		m_info.append(" Organización: ").append(getAD_Org_ID());
		m_info.append(" ConfigFE ID: ").append(objlConfigFE.getEXME_ConfigFE_ID());
		//Termina Creacion de registro en EXME_CONFIGFE.
		
		return retValue;
	}

	public boolean save(PO po, boolean clientOrg, String trxName) {
		if (clientOrg) {
			po.setClientOrg(Env.getAD_Client_ID(customProps), Env.getAD_Org_ID(customProps));
		} else {
			po.setAD_Client_ID(Env.getAD_Client_ID(customProps));

			// Se regresa a como estaba a que setee 0. Jesus Cantu. Se reviso con Helio
			po.setAD_Org_ID(0);
		}
		return po.save(trxName);
	}
	
	/**
	 * Salva la información de los productos iniciales del cliente.
	 * 
	 * @param po el objeto a guardar, en este caso productos
	 * @param clientOrg si es cliente - organización el acceso.
	 * @param trxName el nombre de transacción actual.
	 * @return true si la info se guardó correctamente, de lo contrario false.
	 * @author Creado por Jesus Cantu.
	 */
	public boolean saveProductos(PO po, boolean clientOrg, String trxName) {
		if (clientOrg) {
			po.setClientOrg(Env.getAD_Client_ID(customProps), Env.getAD_Org_ID(customProps));
		} else {
			po.setAD_Client_ID(Env.getAD_Client_ID(customProps));
			
			// Los productos no pueden ser AD_Client > 0 Org 0 sino
			//AD_Client > 0 AD_ORG > 0. Jesus Cantu.
			po.setAD_Org_ID(m_org.getAD_Org_ID());
		}
		return po.save(trxName);
	}

	public MOrgInfo setOrgTrx(MOrg org, String trxName) {
		MOrgInfo info = MOrgInfo.get(customProps, org.getAD_Org_ID(), trxName);
		info.setParent_Org_ID(getAD_Org_ID());
		MOrgInfo infoP = MOrgInfo.get(customProps, getAD_Org_ID(), trxName);
		info.setC_Location_ID(infoP.getC_Location_ID());
		return info;
	}
	
	private MEXMEConfigDocType addDocTypeCtaPac(String trxName) {
		MEXMEConfigDocType doc = new MEXMEConfigDocType(m_ctx, 0, trxName);
		doc.setC_DocTypeVacuna_ID(ctaPacDocId);
		return doc;
	}

	public MTaxCategory addTaxCategory(String name, String trxName) {
		MTaxCategory taxCategory = new MTaxCategory(Env.getCtx(), 0, trxName);
		taxCategory.setAD_Client_ID(getAD_Client_ID());
		taxCategory.setAD_Org_ID(getAD_Org_ID());
		//taxCategory.setName(m_org.getValue() + " " + name);
		// Al ser cliente el acceso no se requiere concatenar el nombre de la Organizacion, Jesus Cantu
		taxCategory.setName(name);
		taxCategory.setDescription(name);
		taxCategory.setCommodityCode(null);
		
		//Solo el 16% debe estar por default. Jesus Cantu. 5 Sep 2012. Revisado con Helio.
		taxCategory.setIsDefault(false);
		return taxCategory;
	}

	public MCharge addCharge(int C_TaxCategory_ID, String chargeName, String trxName) {
		MCharge charge = new MCharge(m_ctx, 0, trxName);
		charge.setAD_Client_ID(getAD_Client_ID());
		charge.setAD_Org_ID(getAD_Org_ID());
		// Al ser cliente el acceso no se requiere concatenar el nombre de la Organizacion, Jesus Cantu
		charge.setName(chargeName);
		charge.setChargeAmt(BigDecimal.ZERO);
		charge.setIsSameTax(false);
		charge.setIsSameCurrency(false);
		charge.setC_TaxCategory_ID(C_TaxCategory_ID);
		charge.setIsTaxIncluded(false);
		return charge;
	}

	public MTax addTax(int C_TaxCategory_ID, double taxRate, String taxName, String trxName) {
		
		//Se comenta a solicitud de Helio. 5 Sept 2012. Jesus Cantu
		/*MOrgInfo info = m_org.getInfo();
		MLocation location = new MLocation(Env.getCtx(), info.getC_Location_ID(), null);*/

		MTax mTax = new MTax(m_ctx, 0, trxName);
		mTax.setAD_Client_ID(getAD_Client_ID());
		mTax.setAD_Org_ID(getAD_Org_ID());
		//mTax.setName(m_org.getValue() + " " + "Sales Tax");
		// Al ser cliente el acceso no se requiere concatenar el nombre de la Organizacion, Jesus Cantu
		mTax.setName(taxName);
		mTax.setIsDocumentLevel(true);
		mTax.setValidFrom(DB.getTimestampForOrg(Env.getCtx()));
		mTax.setIsSummary(false);
		mTax.setRequiresTaxCertificate(false);
		mTax.setRate(BigDecimal.valueOf(taxRate));
		
		//Se comenta a solicitud de Helio. 5 Sept 2012. Jesus Cantu
		/*mTax.setC_Country_ID(location.getC_Country_ID());
		mTax.setC_Region_ID(location.getC_Region_ID());
		mTax.setTo_Country_ID(location.getC_Country_ID());
		mTax.setTo_Region_ID(location.getC_Region_ID());*/
		
		mTax.setC_TaxCategory_ID(C_TaxCategory_ID);
		
		//Se colocan true para el impuesto exento. Jesus Cantu 4 Sep 2012
		mTax.setIsDefault(false);
		mTax.setIsTaxExempt(true);
		//Impuesto de Ventas por default que venga desmarcado. Jesus Cantu.
		mTax.setIsSalesTax(false);
		
		mTax.setSOPOType(MTax.SOPOTYPE_All);

		return mTax;
	}

	public MProductCategory addProductCategory(int C_Charge_ID, String name, String trxName) {
		MProductCategory productCategory = new MProductCategory(m_ctx, 0, trxName);
		productCategory.setAD_Client_ID(getAD_Client_ID());
		productCategory.setAD_Org_ID(m_org.getAD_Org_ID());
		//productCategory.setValue(m_org.getAD_Org_ID() + " " + name);
		// Al ser cliente el acceso no se requiere concatenar el nombre de la Organizacion, Jesus Cantu
		productCategory.setName(name);
		productCategory.setDescription(name);
		productCategory.setIsDefault(false);
		productCategory.setPlannedMargin(BigDecimal.ZERO);
		productCategory.setIsSelfService(true);
		productCategory.setMMPolicy(MProductCategory.MMPOLICY_FiFo);
		//productCategory.setC_Charge_ID(C_Charge_ID);
		//productCategory.setC_Charge_DesGlob_ID(C_Charge_ID);

		return productCategory;
	}
	
	
	private MEXMEProductFam addProductFam(int C_Charge_ID, String name, String trxName) {
		MEXMEProductFam prodFam = new MEXMEProductFam(m_ctx, 0, trxName);
		
		prodFam.setAD_Org_ID(m_org.getAD_Org_ID());
		prodFam.setC_Charge_ID(C_Charge_ID);
		prodFam.setDescription(name);
		prodFam.setName(name);
		//prodFam.setValue(name);
		
		return prodFam;
	}
	

	public MEXMETurnos2 addFullDay(String schedName, String trxName) {
		MEXMETurnos2 fullDay = new MEXMETurnos2(m_ctx, 0, trxName);
		fullDay.setAD_Client_ID(getAD_Client_ID());
		fullDay.setAD_Org_ID(0);
		//fullDay.setValue(m_org.getAD_Org_ID() + " " + schedName);
		//Al solo crearse por cliente y no por organizacion, no se requiere concatenar el nombre de la org
		fullDay.setValue(schedName);
		Date inicio = null;
		Date fin = null;
		try {
			inicio = Constantes.getSdfHora24().parse("00:00");
			fin = Constantes.getSdfHora24().parse("23:59");
		} catch (ParseException e) {
			log.log(Level.SEVERE, "addFullDay", e);
		}
		fullDay.setLun_Entrada(new Timestamp(inicio.getTime()));
		fullDay.setLun_Salida(new Timestamp(fin.getTime()));
		fullDay.setMar_Entrada(new Timestamp(inicio.getTime()));
		fullDay.setMar_Salida(new Timestamp(fin.getTime()));
		fullDay.setMie_Entrada(new Timestamp(inicio.getTime()));
		fullDay.setMie_Salida(new Timestamp(fin.getTime()));
		fullDay.setJue_Entrada(new Timestamp(inicio.getTime()));
		fullDay.setJue_Salida(new Timestamp(fin.getTime()));
		fullDay.setVie_Entrada(new Timestamp(inicio.getTime()));
		fullDay.setVie_Salida(new Timestamp(fin.getTime()));
		fullDay.setSab_Entrada(new Timestamp(inicio.getTime()));
		fullDay.setSab_Salida(new Timestamp(fin.getTime()));
		fullDay.setDom_Entrada(new Timestamp(inicio.getTime()));
		fullDay.setDom_Salida(new Timestamp(fin.getTime()));

		return fullDay;
	}

	public MEXMEBPGroup addBPGroup(String trxName, String name) {
		MEXMEBPGroup boGroup = new MEXMEBPGroup(m_ctx, 0, trxName);
		boGroup.setAD_Client_ID(getAD_Client_ID());
		boGroup.setAD_Org_ID(0);
		//boGroup.setName(m_org.getValue() + " " +name);
		//boGroup.setValue(m_org.getAD_Org_ID() + " " +name);
		//Al solo crearse por cliente y no por organizacion, no se requiere concatenar el nombre de la org
		boGroup.setName(name);
		boGroup.setValue(name);
		boGroup.setDescription(null);
		boGroup.setIsDefault(false);
		boGroup.setIsConfidentialInfo(false);
		boGroup.setPriorityBase(null);
		boGroup.setCreditWatchPercent(null);
		boGroup.setM_PriceList_ID(M_PriceList_ID);
		boGroup.setPriceMatchTolerance(BigDecimal.ZERO);

		return boGroup;
	}

	public MEXMEArea addArea(String name, String trxName) {
		MEXMEArea area = new MEXMEArea(m_ctx, 0, trxName);
		area.setValue(m_org.getAD_Org_ID() + " " + name);
		area.setName(m_org.getValue() + " " + name);
		area.setDescription(m_org.getValue() + " " + name);
		area.setTipoArea(MEXMEArea.TIPOAREA_Other);

		return area;
	}

	public MOrg addOrg(String value, String trxName) {
		//value =  StringUtils.substring(m_org.getValue(), 0, 10)+ value + " OrgTrx" ;
		value = value + " OrgTrx" ;
		MOrg org = new MOrg(m_client, value);
		org.setValue(value);
		//org.setDescription(value);
		org.setIsSummary(false);

		return org;
	}

	public MWarehouse addWarehouse(int EXME_Turnos2_ID, String name, String trxName) {
		MOrgInfo info = MOrgInfo.get(m_ctx, getAD_Org_ID(), trxName);
		MWarehouse warehouse = new MWarehouse(m_ctx, 0, trxName);
		warehouse.setAD_Client_ID(getAD_Client_ID());
		warehouse.setAD_Org_ID(getAD_Org_ID());
		//warehouse.setValue(m_org.getAD_Org_ID() + " " + name); 
		/*warehouse.setName(m_org.getValue() + " " + name);
		warehouse.setDescription(m_org.getValue() + " " + name);*/
		warehouse.setValue(name);
		warehouse.setName(name);
		warehouse.setDescription(name);
		warehouse.setC_Location_ID(info.getC_Location_ID());
		warehouse.setSeparator("*");
		warehouse.setGenera_HL7(false);
		warehouse.setConsigna(false);
		warehouse.setEXME_Turnos2_ID(EXME_Turnos2_ID);
		warehouse.setIntervalo(60);
		warehouse.setHL7ORM(false);
		warehouse.setVirtual(false);
		//warehouse.setControlExistencias(false);
		warehouse.setIsInTransit(false);
		
		return warehouse;
	}

	public MEXMEEstServ addEstServ(int EXME_Area_ID, int AD_Org_ID, String name, String trxName) {
		MEXMEEstServ estServ = new MEXMEEstServ(m_ctx, 0, trxName);
		estServ.setAD_Client_ID(getAD_Client_ID());
		estServ.setAD_Org_ID(getAD_Org_ID());
		estServ.setValue(m_org.getAD_Org_ID() + " " + name); 
		/* el indice unico es por cliente y value por lo que al crear una organizacion adicional debe de
		* identificarse por organizacion. Jesus
		*/
		estServ.setName(name);
		estServ.setDescription(name);
		estServ.setEXME_Area_ID(EXME_Area_ID);
		estServ.setAD_OrgTrx_ID(AD_Org_ID);
		//estServ.setTipoArea(MEstServ.TIPOAREA_Other); // Se quita la columna tipo de area
		estServ.setUsaLPCECOFI(false);
		estServ.setIsBancoOjos(false);
		estServ.setPiso("0");

		return estServ;
	}

	public MProduct addProduct(int M_Product_Category_ID, int EXME_ProductFam_ID, 
			int C_TaxCategory_ID, int C_Charge_ID, String name, String trxName, String productClass) {
		
		MEXMEProduct product = new MEXMEProduct(m_ctx, 0, trxName);
		product.setAD_Client_ID(getAD_Client_ID());
		//Los productos no pueden ya crearse con AD_Org 0 por lo que se cambia a que traiga la organizacion que se acaba de crear.
		product.setAD_Org_ID(m_org.getAD_Org_ID());
		product.setValue(name);
		product.setName(name);
		product.setDescription(name);
		product.setHelp(name);
		
		MUOM uom = MUOM.get(Env.getCtx(), "SERV", null);
		
		product.setC_UOM_ID(uom.getC_UOM_ID());
		
		//Se setea la Unidad de Medida de Volumen igual a la Unidad de Medida.
		product.setC_UOMVolume_ID(uom.getC_UOM_ID());
		
		
		/*
		 * Peticion de Helio el 15 Nov 2012. Se cambia a false. Jesus Cantu
		 * Esto causa problemas dado que el copago, 
		 * coaseguro, etc no funcionan correctamente mientras esten con issummary=Y.
		 */
		product.setIsSummary(false);
		
		product.setIsStocked(false);
		product.setIsSold(true);
		product.setIsBOM(false);
		product.setIsInvoicePrintDetails(false);
		product.setIsPickListPrintDetails(false);
		product.setIsVerified(false);
		product.setC_RevenueRecognition_ID(0);
		product.setM_Product_Category_ID(M_Product_Category_ID);
		product.setVolume(BigDecimal.ZERO);
		product.setWeight(BigDecimal.ZERO);
		product.setC_TaxCategory_ID(C_TaxCategory_ID);
		product.setDiscontinued(false);
		product.setProcessing(false);
		product.setProductType(MProduct.PRODUCTTYPE_Service);
		product.setProductClass(productClass);
		product.setIsWebStoreFeatured(false);
		product.setIsSelfService(true);
		product.setIsDropShip(false);
		
		MTipoProd tipoProd = MTipoProd.get(Env.getCtx(), "SERVICE", trxName);
		
		product.setEXME_TipoProd_ID(tipoProd.getEXME_TipoProd_ID());
		product.setEXME_ProductFam_ID(EXME_ProductFam_ID);
		//product.setIsEstudio(false);
		product.setIsExcludeAutoDelivery(false);
		product.setRequiereContraste(false);
		product.setIsGI(false);
		product.setGenerico(false);
		product.setCambiaPrecio(false);
		product.setIsNumSerie(false);
		//product.setC_Charge_ID(C_Charge_ID);
		product.setEsVacuna(false);
		product.setIsSangre(false);
//		product.setIsLot(false);//Lama .- Product_Cte
		product.setUnitsPerPack(1);
		product.setLowLevel(0);

		return product;
	}

	public MPaymentTerm addPaymentTerm(String name, String trxName) {
		MPaymentTerm term = new MPaymentTerm(m_ctx, 0, trxName);
		term.setAD_Client_ID(getAD_Client_ID());
		term.setAD_Org_ID(0);
		/*term.setValue(m_org.getAD_Org_ID() + " " + name);
		term.setName(m_org.getValue() + " " + name);*/
		//Al solo crearse por cliente y no por organizacion, no se requiere concatenar el nombre de la org
		term.setValue(name);
		term.setName(name);
		term.setDescription(name);
		term.setAfterDelivery(false);
		term.setIsDueFixed(false);
		term.setNetDays(0);
		term.setGraceDays(0);
		term.setFixMonthCutoff(0);
		term.setFixMonthDay(0);
		term.setFixMonthOffset(0);
		term.setDiscountDays(0);
		term.setDiscount(BigDecimal.ZERO);
		term.setDiscountDays2(0);
		term.setDiscount2(BigDecimal.ZERO);
		term.setIsNextBusinessDay(true);
		term.setIsDefault(false);
		term.setIsValid(true);
		term.setProcessing(false);

		return term;
	}

	public MFormaPago addFormaPago(String name, String payment, int C_PaymentTerm_ID, 
			int Ref_FormaPago_ID, boolean isDevol, String trxName) {
		MFormaPago account = new MFormaPago(m_ctx, 0, trxName);
		account.setAD_Client_ID(getAD_Client_ID());
		account.setAD_Org_ID(getAD_Org_ID());
		/*account.setValue(m_org.getAD_Org_ID() + " " + name);
		account.setName(m_org.getValue() + " " + name);
		account.setDescription(m_org.getValue() + " " + name);*/
		//Al solo crearse por cliente y no por organizacion, no se requiere concatenar el nombre de la org
		account.setValue(name);
		account.setName(name);
		account.setDescription(name);
		account.setPaymentRule(payment);
		account.setPosteaCaja(true);
		account.setEsDevol(isDevol);
		account.setRef_FormaPago_ID(Ref_FormaPago_ID);
		account.setC_PaymentTerm_ID(C_PaymentTerm_ID);

		return account;
	}

	public MBPartner addMBPartner(String name, int C_BPGroup_ID, int C_PaymentTerm_ID, String trxName) {
		MBPartner mbPartner = new MBPartner(m_ctx, 0, trxName);
		mbPartner.setAD_Client_ID(getAD_Client_ID());
		mbPartner.setAD_Org_ID(getAD_Org_ID());
		/*mbPartner.setValue(m_client.getAD_Client_ID() + " " + name);
		mbPartner.setName(m_client.getValue() + " " + name);
		mbPartner.setName2(m_client.getName() + " " + name);
		mbPartner.setDescription(m_client.getName() + " " + name);*/
		// No se requiere concatenar la info ya que es por cliente y value
		mbPartner.setValue(name);
		mbPartner.setName(name);
		mbPartner.setName2(name);
		mbPartner.setDescription(name);
		mbPartner.setIsSummary(false);
		mbPartner.setC_BP_Group_ID(C_BPGroup_ID);
		mbPartner.setIsOneTime(false);
		mbPartner.setIsProspect(false);
		mbPartner.setIsVendor(false);
		mbPartner.setIsCustomer(true);
		mbPartner.setIsEmployee(false);
		mbPartner.setIsSalesRep(false);
		mbPartner.setAD_Language("en_US");
		mbPartner.setTaxID(name);
		mbPartner.setIsTaxExempt(false);
		mbPartner.setC_PaymentTerm_ID(C_PaymentTerm_ID);
		mbPartner.setM_PriceList_ID(M_PriceList_ID);
		mbPartner.setIsDiscountPrinted(false);
		mbPartner.setSendEMail(false);
		mbPartner.setSOCreditStatus(MBPartner.SOCREDITSTATUS_CreditOK);
		mbPartner.setModificaEnFactura(false);
		mbPartner.setIsMiniPack(false);
		mbPartner.setIsBanorte(false);
		mbPartner.setIsGI(false);
		mbPartner.setIsFactEspec(false);
		mbPartner.setIsNotaCargo(false);
		mbPartner.setFacturarAseg(true);
		mbPartner.setIdentificador(MBPartner.IDENTIFICADOR_Ambos);
		mbPartner.setIsPension(false);
		mbPartner.setImprimeFactura(false);
		mbPartner.setFacturaEmail(false);
		mbPartner.setTipoProveedor(MBPartner.TIPOPROVEEDOR_National);
		mbPartner.setisExento(true);
		mbPartner.setIsPOTaxExempt(false);

		return mbPartner;
	}

	public MEXMEConfigEC addConfigEC(String name, int M_Warehouse_ID, String trxName) {
		MEXMEConfigEC conf = new MEXMEConfigEC(m_ctx, 0, trxName);
		conf.setAD_Client_ID(getAD_Client_ID());
		conf.setAD_Org_ID(getAD_Org_ID());
		/*conf.setName(m_org.getValue() + " " + "MR SETUP");
		conf.setDescription(m_org.getValue() + " " + "MR SETUP");*/
		conf.setName(name);
		conf.setDescription(name);
		conf.setMultipleUDM(false);
		conf.setPermCamEst(true);
		conf.setM_Warehouse_ID(M_Warehouse_ID);
		conf.setPermCamAlm(false);
		conf.setC_BPartner_ID(C_BPartner_ID);
		conf.setM_PriceList_ID(M_PriceList_ID);
		conf.setCargaDiferAlm(false);
		conf.setImpRecetaMed(false);
		conf.setImpNotaEntMed(false);
		conf.setImpValeEntMed(false);
		conf.setImpValeParcial(false);
		conf.setGeneraBO(false);
		conf.setAplicaPedCtaPac(false);
		conf.setProductoXEsp(false);
		// TODO-conf.setEXME_Cuestionario_ID(0);
		conf.setInterfaz_HL7(false);
		conf.setNo_Usuarios_HL7(0);
		conf.setPrivado(true);
		conf.setReqFactCE(false);
		conf.setMostrarImpresora(false);
		conf.setSurtir(false);
		conf.setPreReqCita(false);
		conf.setMostrarPacRefer(false);
		conf.setIsReqConfirm(false);
		conf.setValidaCita(false);
		conf.setvalidaDiagCita(false);
		conf.setDurationMin(5);
		conf.setLigaWinlab(false);
		conf.setWINLAB_R(null);
		conf.setInterfaseLaserFiche(false);
		conf.setReqFactZero(false);
		conf.setScaleMin(1);
		conf.setBancoDeSangre(false);
		conf.setNotasMedicas(false);
		conf.setLIGAKODAK(false);
		conf.setClasCliente(false);
		conf.setReqFactCE(false);
		//#07272 — Correo enviado al admitir paciente.
		//Jesus, se seteaa false la propiedad para q no envie correos.
		conf.setCreateUserPatient(false);
		
		return conf;
	}

	public MEXMEConfigEspecial addConfig(String trxName) {
		MEXMEConfigEspecial configEspecial = new MEXMEConfigEspecial(m_ctx, 0, trxName);
		configEspecial.setAD_Client_ID(getAD_Client_ID());
		configEspecial.setAD_Org_ID(getAD_Org_ID());
		configEspecial.setCaptDatosLab(true);
		configEspecial.setPrintPreFac(true);
		configEspecial.setRutasServ(true);
		configEspecial.setPrintRecibo(true);
		configEspecial.setLoginFact(true);
		configEspecial.setFactAlmOrg(true);
		configEspecial.setCheckVoucher(true);
		configEspecial.setTipoCliente(true);
		configEspecial.setAutorizaAseg(true);
		configEspecial.setIsPension(true);
		configEspecial.setIsDonativo(true);
		configEspecial.setVerInfMilitar(true);
		configEspecial.setEditValuePac(true);

		return configEspecial;
	}

	public MEXMEMejoras addConfigMejoras(int EXME_EstServ_ID, int mWarehouseId, String trxName) {
		MEXMEMejoras mejora = new MEXMEMejoras(m_ctx, 0, trxName);
		mejora.setAD_Client_ID(getAD_Client_ID());
		mejora.setAD_Org_ID(getAD_Org_ID());
		mejora.setisDiagEnCuest(false);
		mejora.setVistaCitas(false);
		mejora.setExpAuto(false);
		mejora.setisOcultarPass(false);
		mejora.setIsEditarClienteFD(false);
		mejora.setIsEditarClienteFE(false);
		mejora.setIsNombrePorApellido(false);
		mejora.setProgramarCita(true);
		mejora.setCURPMandatory(false);
		mejora.setIsAgregaLote(true);
		mejora.setIsModificaLotes(true);
		mejora.setIsProductRequestByGrouping(false);
		mejora.setEXME_EstServ_ID(EXME_EstServ_ID);
		mejora.setCurpHist(false);
		mejora.setAgregarPreguntas(false);
		mejora.setCalcImpFact(false);
		mejora.setEditPastSchedules(false);
		mejora.setEditProgOperatingTime(false);
		mejora.setMaxQueryRecords(1000);
		//Se coloca falso para los nuevos clientes por intentar conectarse a Lexi.
		//Jesus Cantu el 30 Abril 14.
		mejora.setIsMDS(false);
		//mejora.setTemporaryNDC(MProduct.getIDByValue("TemporaryNDC"));
		//mejora.setNotAvailableNDC(MProduct.getIDByValue("NotAvailable"));
		mejora.setM_Warehouse_ID(mWarehouseId);
		
		/*Se colocan valores en duro ya que los registros estan inactivos
		y cambio el metodo getIDByValue para que considere solo los activos.
		Tickets 05176 y 05152 de Mexico.*/
		mejora.setTemporaryNDC(10001008);
		mejora.setNotAvailableNDC(10001007);
		//mejora.setEXME_DiagnosticoHdr_ID(MEXMEDiagnosticoHdr.get(Env.getCtx(), "ICD9").getEXME_DiagnosticoHdr_ID());
		//mejora.setEXME_IntervencionHdr_ID(MEXMEIntervencionHdr.get(Env.getCtx(), "ICD9-CM VOL3", trxName).getEXME_IntervencionHdr_ID());
		return mejora;
	}

	public MEXMEConfigPre addPriceSettings(String name, String trxName) {
		MEXMEConfigPre ps = new MEXMEConfigPre(m_ctx, 0, trxName);
		ps.setAD_Client_ID(getAD_Client_ID());
		ps.setAD_Org_ID(getAD_Org_ID());
		/*
		 * El indice es Ad_Client - Value por lo que es necesario distinguir por organizacion
		 */
		ps.setValue(m_org.getAD_Org_ID() + " " + name);
		
		/*ps.setName(m_org.getValue() + " " + name);
		ps.setDescription(m_org.getValue() + " " + name);*/
		//Para name y descripcion no es necesario
		ps.setName(name);
		ps.setDescription(name);
		
		ps.setCalculoAut(false);
		ps.setBajarPrecioVta(false);
		ps.setIncluirDesctos(false);
		ps.setM_PriceList_ID(M_PriceList_ID);
		ps.setUsarFactor(false);
		ps.setCoaseguro_ID((Integer) customProps.get("#MProductCoaseguro"));
		ps.setDeducible_ID((Integer) customProps.get("#MProductDeducible"));
		
		if (customProps.get("#MProductCopay") != null) {
			ps.setCoPago_ID((Integer) customProps.get("#MProductCopay"));	
		}
		
		MBank bank = createBank(trxName);
		
		ps.setC_BankAccount_ID(bank.getAccount(trxName).getC_BankAccount_ID());
		ps.setMaxLinFactura(45);
		ps.setCreaActividad(false);
		ps.setTab_SC_C_BP_Group_ID((Integer) customProps.get("#BP_Group"));
		ps.setConvenios_C_BP_Group_ID((Integer) customProps.get("#BP_Group"));
		ps.setUrgencias_C_BP_Group_ID((Integer) customProps.get("#BP_Group"));
		ps.setCom_SC_C_BP_Group_ID((Integer) customProps.get("#BP_Group"));
		ps.setCons_Ext_C_BP_Group_ID((Integer) customProps.get("#BP_Group"));
		ps.setHospitalizacion_C_BP_Group_ID((Integer) customProps.get("#BP_Group"));
		ps.setC_BP_Group_ID((Integer) customProps.get("#BP_Group"));
		ps.setC_Charge_ID((Integer) customProps.get("#C_Charge"));
		ps.setMaxDiscount(new BigDecimal(0));
		ps.setMaxDiscountAmt(new BigDecimal(0));
		ps.setRecalculaPre(false);
		
		//Si es cliente creado para Mexico colocar la configuracion
		//El precio del producto deberá existir en:* a Lista Precios, 
		//de lo contrario se queda por default a PP (asi esta en la tabla). Jesus Cantu el 3 Dic 2013.
		if (("es_MX").equals(m_lang)) {
			ps.setBusquedaPrecio("LP");
		}

		return ps;
	}
	
	/**
	 * Crear valores por defecto de cuenta bancaria
	 * 
	 * @param trxName
	 * @return
	 */
	public MBank createBank(String trxName) {
		MBank bank = new MBank(m_ctx, 0, null);
		bank.setName(Utilerias.getMsg(m_ctx, "caja.bank"));
		bank.setRoutingNo("123456");

		if (bank.save(trxName)) {
			MBankAccount bankAccount = new MBankAccount(m_ctx, 0, null);

			bankAccount.setAccountNo("123456");
			bankAccount.setC_Currency_ID(MAcctSchema.getCurrencyFromSchema(null, m_client.getAD_Client_ID(), 0));
			bankAccount.setBankAccountType(MBankAccount.BANKACCOUNTTYPE_Checking);
			bankAccount.setC_Bank_ID(bank.getC_Bank_ID());

			if (bankAccount.save(trxName)) {
				X_C_BankAccountDoc bankAccountDoc = new X_C_BankAccountDoc(m_ctx, 0, null);
				bankAccountDoc.setName(MRefList.getListName(m_ctx, X_C_BankAccountDoc.PAYMENTRULE_AD_Reference_ID, X_C_BankAccountDoc.PAYMENTRULE_Check));
				bankAccountDoc.setPaymentRule(X_C_BankAccountDoc.PAYMENTRULE_Check);
				bankAccountDoc.setClassname("PolizaCheque");
				bankAccountDoc.setC_BankAccount_ID(bankAccount.getC_BankAccount_ID());
				bankAccountDoc.setCurrentNext(1);

				bankAccountDoc.save(trxName);
			}
		}

		return bank;
	}

	public MEXMETipoHabitacion addTipoHabitacion(String name, String description, String trxName) {
		MEXMETipoHabitacion tipoHabitacion = new MEXMETipoHabitacion(m_ctx, 0, trxName);
		tipoHabitacion.setValue(name);
		tipoHabitacion.setName(name);
		tipoHabitacion.setDescription(description);
		return tipoHabitacion;
	}
	
	public MLocator addLocator(String value, String trxName, int warehouseID){
		MLocator locator = new MLocator(m_ctx, 0, trxName);
		locator.setValue(value);
		locator.setM_Warehouse_ID(warehouseID);
		locator.setPriorityNo(50);
		locator.setIsDefault(true);
		locator.setX("0");
		locator.setY("0");
		locator.setZ("0");
		
		return locator;
	}
	
	public MEstServAlm addEstServAlm(String trxName, int warehouseID, int estServID){
		MEstServAlm alm = new MEstServAlm(m_ctx,0,trxName);
		alm.setEXME_EstServ_ID(estServID);
		alm.setM_Warehouse_ID(warehouseID);
		
		return alm;
	}
	
	public MFormato addFormato(String trxName, String name){
		MFormato formato = new MFormato(m_ctx, 0, trxName);
		formato.setName(name);
		formato.setValue(name);
		
		return formato;
	}
	
	public MDocMedsys addDocMedsys(String trxName, String name, int EXME_Formato_ID) {
		MDocMedsys docMedsys = new MDocMedsys(m_ctx, 0, trxName);
		docMedsys.setName(name);
		docMedsys.setEXME_Formato_ID(EXME_Formato_ID);
		return docMedsys;
	}
	
	/**
	 * Se crea la configuración inicial del cliente.
	 * 
	 * @param ctx el contexto de la aplicación
	 * @param msjError el String con los mensajes de error.
	 * @param msjExito el String con los mensajes de éxito.
	 * @param locale el objeto locale con la regionalización.
	 * @param trxName la transacción actual.
	 * 
	 * @return true si la información se guarda con éxito, de lo contrario false.
	 * @author Modificado por Jesus Cantu.
	 */
	@SuppressWarnings("unchecked")
	public boolean clientConfiguration(Properties ctx, String msjError, String msjExito, 
			Locale locale, String trxName) { // Se agrega parametro locale. Jesus Cantu
		
		//Se crean las tasas de impuestos 
		//MTaxCategory taxCategory = addTaxCategory("Sales Tax", trxName);
		
		/* Se elimina el taxcategory de impuesto de Ventas. Revisado con Helio
		 * El 4 de Sept del 2012. 
		 */
		
		//String salesTax = Utilerias.getMessage(ctx, locale, "msj.impSalesTax");
		//MTaxCategory taxCategory = addTaxCategory(salesTax, trxName);
		
		/*if (!save(taxCategory, false, trxName)) {
			m_info.append(msjError).append(salesTax);
			return false;
		}
		m_info.append(msjExito).append(salesTax);*/
		
		//TaxCategory Estandar no el de ventas, revisado con Helio.
		
		//int C_TaxCategoryStandard = Integer.parseInt(customProps.getProperty("#C_TaxCategory"));
		
		//Traer el C_TaxCategory_ID Estandar creado cuando se creo la organizacion.
		//Jesus Cantu. Esto porque se va a usar en lugar del de ventas que se elimino a solicitud de Helio.
		//22 Nov 2013, ahora este tax es el del 16% ya que el estandar se cambio por este. Jesus Cantu.
		int C_TaxCategory16 = MTaxCategory.getTaxCategoryId(null, 
				getAD_Client_ID(), 0,  Utilerias.getMessage(ctx, locale, "msj.tasa16"));
		
		
		customProps.put("#C_TaxCategory", C_TaxCategory16);

		
		//Crear la categoria Exento
		String taxFree = Utilerias.getMessage(ctx, locale, "msj.impTaxFree"); //Exento
		MTaxCategory taxExento = addTaxCategory(taxFree, trxName);
		
		if (save(taxExento, false, trxName)) {
			m_info.append(msjExito).append(taxFree);
		} else {
			m_info.append(msjError).append(taxFree);
			return false;			
		}
		
		
		
		//Se crea el Cargo de Ventas
		String chargeName = Utilerias.getMessage(ctx, locale, "msj.cargoVentas");
		
		//Se le coloca al cargo de Ventas la categoria del 16% Jesus Cantu 22 Nov 2013.
		MCharge charge = addCharge(C_TaxCategory16, chargeName, trxName);
		if (save(charge, false, trxName)) {
			m_info.append(msjExito).append(chargeName);
		} else {
			m_info.append(msjError).append(chargeName);
			return false;
		}
		
		
		customProps.put("#C_Charge", charge.getC_Charge_ID());
		
		//Poner taxcategory estandar, revisado con Helio. 4 Sep 2012.
		customProps.put("#C_Charge_ID", C_TaxCategory16);
		
		
		//Creamos el C_Tax Exento, se elimino el de SalexTax, revisado con Helio el 4 de Sept 2012
		//Poner cero en rate.
		//Se manda el taxcategory 2 que es el de Exento. Jesus Cantu
		MTax tax = addTax(taxExento.getC_TaxCategory_ID(), 0, taxFree, trxName);
		if (save(tax, false, trxName)) {
			m_info.append(msjExito).append("CTax: ").append(taxFree);
		} else {
			m_info.append(msjError).append("CTax: ").append(taxFree);
			return false;
		}
		
		
		
		//Card 1123: Jesus Cantu: 22 Noviembre 2013. Se crean Categorias 0% y 11% Faltantes
		
		//Crear la categoria 11%
		/*String tax11 = Utilerias.getMessage(ctx, locale, "msj.tasa11"); //Tasa 11%
		MTaxCategory taxCat11 = addTaxCategory(tax11, trxName);
		
		if (save(taxCat11, false, trxName)) {
			m_info.append(msjExito).append(tax11);
		} else {
			m_info.append(msjError).append(tax11);
			return false;
		}*/
		
		
		
		//Creamos el Tax 11 %
		/*MTax mtax11 = addTax(taxCat11.getC_TaxCategory_ID(), 11, tax11, trxName);
		//Remover el Exento de Impuestos.
		mtax11.setIsTaxExempt(false);
		if (save(mtax11, false, trxName)) {
			m_info.append(msjExito).append("CTax: ").append(tax11);
		} else {
			m_info.append(msjError).append("CTax: ").append(tax11);
			return false;
		}*/
		
		
		//Crear la categoria 0%
		String tax0 = Utilerias.getMessage(ctx, locale, "msj.tasa0"); //Tasa 11%
		MTaxCategory taxCat0 = addTaxCategory(tax0, trxName);
		
		if (save(taxCat0, false, trxName)) {
			m_info.append(msjExito).append(tax0);
		} else {
			m_info.append(msjError).append(tax0);
			return false;
		}
		
		
		//Creamos el Tax 0%
		MTax mtax0 = addTax(taxCat0.getC_TaxCategory_ID(), 0, tax0, trxName);
		if (save(mtax0, false, trxName)) {
			m_info.append(msjExito).append("CTax: ").append(tax0);
		} else {
			m_info.append(msjError).append("CTax: ").append(tax0);
			return false;
		}

		//Creamos las categorias de Productos
		
		boolean blAccounting = false;
		
		//Instrumental Quirurgico
		String surgeryInstruments = Utilerias.getMessage(ctx, locale, "msj.surgeryInstruments");
		
		MProductCategory productCategory = 
			addProductCategory(charge.getC_Charge_ID(), surgeryInstruments, trxName);
		if (!save(productCategory, false, trxName)) {
			m_info.append(msjError).append(surgeryInstruments);
			return false;
		}
		
		//M_Product_Category_Acct act = new M_Product_Category_Acct()
		m_info.append(msjExito).append(surgeryInstruments);

		// Material Descartable
		String medicalSupplies = Utilerias.getMessage(ctx, locale, "msj.medicalSupplies");
		
		MProductCategory productCategory2 = 
			addProductCategory(charge.getC_Charge_ID(), medicalSupplies, trxName);
		if (!save(productCategory2, false, trxName)) {
			m_info.append(msjError).append(medicalSupplies);
			return false;
		}
		m_info.append(msjExito).append(medicalSupplies);
		
		
		//Servicios Propios
		String ownServices = Utilerias.getMessage(ctx, locale, "msj.ownServices");

		MProductCategory productCategory3 = 
			addProductCategory(charge.getC_Charge_ID(), ownServices, trxName);
		if (!save(productCategory3, false, trxName)) {
			m_info.append(msjError).append(ownServices);
			return false;
		}
		m_info.append(msjExito).append(ownServices);
		
		
		//Actualizamos el ProductAccounting.
		blAccounting = productCategory3.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				productCategory3.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(ownServices).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(ownServices).append(" - Costing Method Accounting ");;
		}
		
		
		// Servicios No Hospitalarios
		String noHospitalServices = Utilerias.getMessage(ctx, locale, "msj.noHospitalServices");

		MProductCategory productCategory4 = 
			addProductCategory(charge.getC_Charge_ID(), noHospitalServices, trxName);
		if (!save(productCategory4, false, trxName)) {
			m_info.append(msjError).append(noHospitalServices);
			return false;
		}
		m_info.append(msjExito).append(noHospitalServices);
		
		//Actualizamos el ProductAccounting.
		blAccounting = productCategory4.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				productCategory4.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(noHospitalServices).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(noHospitalServices).append(" - Costing Method Accounting ");;
		}
		
		//TODO : Por que sube a conexto el de servicios no hospitalarios?
		customProps.put("#M_ProductCategory", productCategory4.getM_Product_Category_ID());

		
		// COASEGURO
		String coinsurance = Utilerias.getMessage(ctx, locale, "msj.coinsurance");
		
		MProductCategory productCategory5 = 
			addProductCategory(charge.getC_Charge_ID(), coinsurance, trxName);
		if (!save(productCategory5, false, trxName)) {
			m_info.append(msjError).append(coinsurance);
			return false;
		}
		m_info.append(msjExito).append(coinsurance);

		
		//Deducible
		String deductible = Utilerias.getMessage(ctx, locale, "msj.deductible");
		MProductCategory productCategory6 = 
			addProductCategory(charge.getC_Charge_ID(), deductible, trxName);
		if (!save(productCategory6, false, trxName)) {
			m_info.append(msjError).append(deductible);
			return false;
		}
		m_info.append(msjExito).append(deductible);
		
		
		//Equipo de Oficina
		String officeEq = Utilerias.getMessage(ctx, locale, "msj.officeEq");
		MProductCategory productCategory7 = 
			addProductCategory(charge.getC_Charge_ID(), officeEq, trxName);
		if (!save(productCategory7, false, trxName)) {
			m_info.append(msjError).append(officeEq);
			return false;
		}
		m_info.append(msjExito).append(officeEq);

		
		//Equipo Medico
		String medicalEq = Utilerias.getMessage(ctx, locale, "msj.medicalEq");
		MProductCategory productCategory8 = 
			addProductCategory(charge.getC_Charge_ID(), medicalEq, trxName);
		if (!save(productCategory8, false, trxName)) {
			m_info.append(msjError).append(medicalEq);
			return false;
		}
		m_info.append(msjExito).append(medicalEq);
	
		
		// Copago
		String copay = Utilerias.getMessage(ctx, locale, "msj.copayUpper");
		MProductCategory productCategory9 = 
			addProductCategory(charge.getC_Charge_ID(), copay, trxName);
		if (!save(productCategory9, false, trxName)) {
			m_info.append(msjError).append(copay);
			return false;
		}
		m_info.append(msjExito).append(copay);
		
		
		// Procedimientos
		String cptProcedure = Utilerias.getMessage(ctx, locale, "msj.cptProcedure");
		MProductCategory productCategory10 = 
			addProductCategory(charge.getC_Charge_ID(), cptProcedure, trxName);
		if (!save(productCategory10, false, trxName)) {
			m_info.append(msjError).append(cptProcedure);
			return false;
		}
		m_info.append(msjExito).append(cptProcedure);
		
		customProps.put("#M_ProductCategoryCPT", productCategory10.getM_Product_Category_ID());
		
		//Actualizamos el ProductAccounting.
		blAccounting = productCategory10.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				productCategory10.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(cptProcedure).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(cptProcedure).append(" - Costing Method Accounting ");
		}
		
		
		// Banco de Sangre
		String bancoSangre = Utilerias.getMessage(ctx, locale, "msj.bancoSangre");
		MProductCategory prodCatBancoSangre = 
			addProductCategory(charge.getC_Charge_ID(), bancoSangre, trxName);
		if (!save(prodCatBancoSangre, false, trxName)) {
			m_info.append(msjError).append(bancoSangre);
			return false;
		}
		m_info.append(msjExito).append(bancoSangre);
		
		
		//Actualizamos el ProductAccounting.
		blAccounting = prodCatBancoSangre.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				prodCatBancoSangre.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(bancoSangre).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(bancoSangre).append(" - Costing Method Accounting ");
		}
		
		
		// Banco de Sangre
		String habitaciones = Utilerias.getMessage(ctx, locale, "msj.habitaciones");
		MProductCategory prodCatHab = 
			addProductCategory(charge.getC_Charge_ID(), habitaciones.toUpperCase(), trxName);
		if (!save(prodCatHab, false, trxName)) {
			m_info.append(msjError).append(habitaciones.toUpperCase());
			return false;
		}
		m_info.append(msjExito).append(habitaciones.toUpperCase());
		
		
		//Actualizamos el ProductAccounting.
		blAccounting = prodCatHab.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				prodCatHab.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(habitaciones.toUpperCase()).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(habitaciones.toUpperCase()).append(" - Costing Method Accounting ");
		}
		
		
		// Imagenologia
		String imagenologia = Utilerias.getMessage(ctx, locale, "msj.imagWarehouse");
		MProductCategory prodCatImage = 
			addProductCategory(charge.getC_Charge_ID(), imagenologia.toUpperCase(), trxName);
		if (!save(prodCatImage, false, trxName)) {
			m_info.append(msjError).append(imagenologia.toUpperCase());
			return false;
		}
		m_info.append(msjExito).append(imagenologia.toUpperCase());
		
		
		//Actualizamos el ProductAccounting.
		blAccounting = prodCatImage.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				prodCatImage.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(imagenologia.toUpperCase()).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(imagenologia.toUpperCase()).append(" - Costing Method Accounting ");
		}
		
		
		// Laboratorio
		String laboratorio = Utilerias.getMessage(ctx, locale, "msj.labWarehouse");
		MProductCategory prodCatLab = 
			addProductCategory(charge.getC_Charge_ID(), laboratorio.toUpperCase(), trxName);
		if (!save(prodCatLab, false, trxName)) {
			m_info.append(msjError).append(laboratorio.toUpperCase());
			return false;
		}
		m_info.append(msjExito).append(laboratorio.toUpperCase());
		
		
		//Actualizamos el ProductAccounting.
		blAccounting = prodCatLab.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				prodCatLab.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(laboratorio.toUpperCase()).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(laboratorio.toUpperCase()).append(" - Costing Method Accounting ");
		}
		
		
		// Material de Curacion
		String matCuracion = Utilerias.getMessage(ctx, locale, "msj.materialCuracion");
		MProductCategory prodCatMatCuracion = 
			addProductCategory(charge.getC_Charge_ID(), matCuracion.toUpperCase(), trxName);
		if (!save(prodCatMatCuracion, false, trxName)) {
			m_info.append(msjError).append(matCuracion.toUpperCase());
			return false;
		}
		m_info.append(msjExito).append(matCuracion.toUpperCase());
		
		
		// Patologia
		String patologia = Utilerias.getMessage(ctx, locale, "msj.patologia");
		MProductCategory prodCatPatologia = 
			addProductCategory(charge.getC_Charge_ID(), patologia.toUpperCase(), trxName);
		if (!save(prodCatPatologia, false, trxName)) {
			m_info.append(msjError).append(patologia.toUpperCase());
			return false;
		}
		m_info.append(msjExito).append(patologia.toUpperCase());
		
		
		//Actualizamos el ProductAccounting.
		blAccounting = prodCatPatologia.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				prodCatPatologia.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(patologia.toUpperCase()).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(patologia.toUpperCase()).append(" - Costing Method Accounting ");
		}
		
		
		// Usos de Equipo
		String usosDeEquipo = Utilerias.getMessage(ctx, locale, "msj.usosDeEquipo");
		MProductCategory prodCatUsosDeEquipo = 
			addProductCategory(charge.getC_Charge_ID(), usosDeEquipo.toUpperCase(), trxName);
		if (!save(prodCatUsosDeEquipo, false, trxName)) {
			m_info.append(msjError).append(usosDeEquipo.toUpperCase());
			return false;
		}
		m_info.append(msjExito).append(usosDeEquipo.toUpperCase());
		
		
		//Actualizamos el ProductAccounting.
		blAccounting = prodCatUsosDeEquipo.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				prodCatUsosDeEquipo.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(usosDeEquipo.toUpperCase()).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(usosDeEquipo.toUpperCase()).append(" - Costing Method Accounting ");
		}
		

		// Usos Y Aplicaciones
		String usosAplicaciones = Utilerias.getMessage(ctx, locale, "msj.usosYAplicaciones");
		MProductCategory prodCatUsosAplicaciones = 
			addProductCategory(charge.getC_Charge_ID(), usosAplicaciones.toUpperCase(), trxName);
		if (!save(prodCatUsosAplicaciones, false, trxName)) {
			m_info.append(msjError).append(usosAplicaciones.toUpperCase());
			return false;
		}
		m_info.append(msjExito).append(usosAplicaciones.toUpperCase());
		
		
		//Actualizamos el ProductAccounting.
		blAccounting = prodCatUsosAplicaciones.updateAccountingCostMethod(MCostElement.COSTINGMETHOD_StandardCosting, 
				prodCatUsosAplicaciones.getM_Product_Category_ID(), trxName);
		
		if (blAccounting) {
			m_info.append(msjExito).append(usosAplicaciones.toUpperCase()).append(" - Costing Method Accounting ");
		} else {
			m_info.append(msjError).append(usosAplicaciones.toUpperCase()).append(" - Costing Method Accounting ");
		}
		
		// Medicamentos. Revisado con Helio el 6 de Sept 2012.
		/* Se comenta ya que no se requiere crear una categoria de Medicamentos
		 * a nivel cliente, por el proyecto de Medicamentos a nivel System.
		 * Jesus Cantu el 16 Abril 2013.
		 */
		
		/*String slMedicamentos = Utilerias.getMessage(ctx, locale, "msj.meds");
		MProductCategory productCategory11 = 
			addProductCategory(charge.getC_Charge_ID(), slMedicamentos, trxName);
		if (!save(productCategory11, false, trxName)) {
			m_info.append(msjError).append(slMedicamentos);
			return false;
		}
		m_info.append(msjExito).append("Prod Category: ").append(slMedicamentos);*/
		
		// Insertamos en M_Product_Category_Acct y C_AcctSchema_Default para medicamentos (value 2100) 
		String drugsMessage = Utilerias.getMessage(ctx, locale, "msj.internacion.farmacos");
		MProductCategory drugs = MProductCategory.get(customProps, "2100", trxName);
		if (!drugs.insertAccounting()) {
			m_info.append(msjError).append(drugsMessage);
			return false;
		}
		m_info.append(msjExito).append(drugsMessage);
		
		//Creamos la familia de productos servicios propios
		// Se comenta, no se requiere crear una familia de Servicios Propios por cliente, 
		// ya que se subieron a nivel System. Jesus Cantu el 16 Abril 2013.
		
		/*MEXMEProductFam prodFam = addProductFam(charge.getC_Charge_ID(), ownServices, trxName);
		
		if(!save(prodFam, false, trxName)) {
			m_info.append(msjError).append("Fam: ").append(ownServices);
			return false;
		}
		
		m_info.append(msjExito).append("Fam: ").append(ownServices);
		
		customProps.put("#MProductFam", prodFam);*/
		
		
		//Creamos la familia de productos Medicamentos
		//Revisado con Helio el 6 de Sept 2012
		// Se comenta, no se requiere crear una familia de Medicamentos por cliente, 
		// ya que se subieron a nivel System. Jesus Cantu el 16 Abril 2013.
		
		/*MEXMEProductFam prodFamMed = addProductFam(charge.getC_Charge_ID(), slMedicamentos, trxName);
		
		if(!save(prodFamMed, false, trxName)) {
			m_info.append(msjError).append("Fam: ").append(slMedicamentos);
			return false;
		}
		
		m_info.append(msjExito).append("Fam: ").append(slMedicamentos);*/
		
		//Se sube a contexto.
		//customProps.put("#MProductFamMed", prodFamMed);
		
		
		//16 Abril 2013
		//Obtenemos la familia de Servicios Propios de System para usarla luego en 
		//SPhysicianInfo.java y en SRoomType.java
		
		MEXMEProductFam prodFam = MEXMEProductFam.get(ctx, ownServices, 0, null);
		
		customProps.put("#MProductFam", prodFam);
		
		
		//Creamos el turno completo en EXME_Turnos2
		String schedName = Utilerias.getMessage(ctx, locale, "msj.fullDaySchd");
		
		MEXMETurnos2 turnos2 = addFullDay(schedName,trxName);
		if (!save(turnos2, false, trxName)) {
			m_info.append(msjError).append(schedName);
			return false;
		} else {
			customProps.put("#FULLDAY", turnos2);
		}
		m_info.append(msjExito).append(schedName);

		
		//Creamos los registros de C_BP_Group
		//ASEGURADORA
		String insurance = Utilerias.getMessage(ctx, locale, "msj.insuranceUpper");
		MEXMEBPGroup bpGroup = addBPGroup(trxName, insurance);
		if (!save(bpGroup, false, trxName)) {
			m_info.append(msjError).append("BP Group: ").append(insurance);
			return false;
		}
		m_info.append(msjExito).append("BP Group: ").append(insurance);
		
		customProps.put("#BP_Group", bpGroup.getC_BP_Group_ID());
		
		//Paciente
		String patientBG = Utilerias.getMessage(ctx, locale, "msg.paciente");
		MEXMEBPGroup bpGroup1 = addBPGroup(trxName, patientBG.toUpperCase());
		if (!save(bpGroup1, false, trxName)) {
			m_info.append(msjError).append("PATIENT Group");
			return false;
		}
		m_info.append(msjExito).append("PATIENT Group");
		
		/* Creamos los productos por default con Organizacion diferente de cero. Jesus
		 * Para esto se crea nuevo metodo saveProductos.
		 */
		
		// Deducible
		MProduct product = 
			addProduct(
					productCategory5.getM_Product_Category_ID(), 
					prodFam.getEXME_ProductFam_ID(), 
					taxExento.getC_TaxCategory_ID(), ////Poner taxcategory exento, Card 1123 22 Nov 2013 Jesus Cantu.
					charge.getC_Charge_ID(), 
					coinsurance, 
					trxName,
					MProduct.PRODUCTCLASS_Others// Otros servicios es solo USA
			);
		if (!saveProductos(product, false, trxName)) {
			m_info.append(msjError).append("Prod: ").append(coinsurance);
			return false;
		}
		m_info.append(msjExito).append("Prod: ").append(coinsurance);
		
		customProps.put("#MProductCoaseguro", product.getM_Product_ID());

		// Coaseguro
		MProduct product2 = 
			addProduct(
					productCategory6.getM_Product_Category_ID(), 
					prodFam.getEXME_ProductFam_ID(), 
					taxExento.getC_TaxCategory_ID(), ////Poner taxcategory exento, Card 1123 22 Nov 2013 Jesus Cantu.
					charge.getC_Charge_ID(), 
					deductible, 
					trxName,
					MProduct.PRODUCTCLASS_OtherService
			);
		if (!saveProductos(product2, false, trxName)) {
			m_info.append(msjError).append("Prod: ").append(deductible);
			return false;
		}
		m_info.append(msjExito).append("Prod: ").append(deductible);
		
		customProps.put("#MProductDeducible", product2.getM_Product_ID());

		//Outpatient Visit
		String outpatientVisit = Utilerias.getMessage(ctx, locale, "msj.outpatientVisit");
		
		MProduct product3 = 
			addProduct(
					productCategory4.getM_Product_Category_ID(), 
					prodFam.getEXME_ProductFam_ID(), 
					taxExento.getC_TaxCategory_ID(), ////Poner taxcategory exento, Card 1123 22 Nov 2013 Jesus Cantu.
					charge.getC_Charge_ID(), 
					outpatientVisit, 
					trxName,
					MProduct.PRODUCTCLASS_PhysicianServices
			);
		
		if (!saveProductos(product3, false, trxName)) {
			m_info.append(msjError).append("Prod: ").append(outpatientVisit);
			return false;
		}
		m_info.append(msjExito).append("Prod: ").append(outpatientVisit);
		
		
		// Copago
		MProduct product4 = 
			addProduct(
					productCategory9.getM_Product_Category_ID(), 
					prodFam.getEXME_ProductFam_ID(), 
					taxExento.getC_TaxCategory_ID(), //Poner taxcategory estandar, revisado con Helio. 4 Sep 2012.
					charge.getC_Charge_ID(), 
					copay, 
					trxName,
					MProduct.PRODUCTCLASS_OtherService
			);
		
		if (!saveProductos(product4, false, trxName)) {
			m_info.append(msjError).append("Prod: ").append(copay);
			return false;
		}
		m_info.append(msjExito).append("Prod: ").append(copay);
		
		customProps.put("#MProductCopay", product4.getM_Product_ID());

		// Se crean los C_PaymentTerm
		
		//Cash
		String cash = Utilerias.getMessage(ctx, locale, "msj.cash");
		
		MPaymentTerm term = addPaymentTerm(cash, trxName);
		if (!save(term, false, trxName)) {
			m_info.append(msjError).append(cash);
			return false;
		}
		m_info.append(msjExito).append(cash);
		
		//Credit
		String credit = Utilerias.getMessage(ctx, locale, "msj.credit");

		MPaymentTerm term2 = addPaymentTerm(credit, trxName);
		if (!save(term2, false, trxName)) {
			m_info.append(msjError).append(credit);
			return false;
		}
		m_info.append(msjExito).append(credit);

		//Immediate
		String immediate = Utilerias.getMessage(ctx, locale, "msj.immediate");
		
		MPaymentTerm term3 = addPaymentTerm(immediate, trxName);
		if (!save(term3, false, trxName)) {
			m_info.append(msjError).append(immediate);
			return false;
		}
		
		m_info.append(msjExito).append(immediate);

		
		// Se crean las formas de pago.
		//Account Receivable - Cuentas Por Cobrar
		
		String accountReceivable = Utilerias.getMessage(ctx, locale, "msj.accountReceivable");
		
		MFormaPago pagoCtasCobrar = 
			addFormaPago(
					accountReceivable, 
					MFormaPago.PAYMENTRULE_OnCredit, 
					term2.getC_PaymentTerm_ID(),
					0,
					false, 
					trxName
			);
		if (!save(pagoCtasCobrar, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(accountReceivable);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(accountReceivable);
		}

		//Cash
		MFormaPago pagoCash = 
			addFormaPago(cash, MFormaPago.PAYMENTRULE_Cash, term3.getC_PaymentTerm_ID(), 
					0, false, trxName);
		if (!save(pagoCash, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(cash);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(cash);
		}

		//Check
		String check = Utilerias.getMessage(ctx, locale, "msj.check");
		
		MFormaPago pagoCheck = 
			addFormaPago(check, MFormaPago.PAYMENTRULE_Check, term.getC_PaymentTerm_ID(), 
					0, false, trxName);
		if (!save(pagoCheck, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(check);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(check);
		}

		//Debit Card
		String debitCard = Utilerias.getMessage(ctx, locale, "msj.debitCard");
		
		MFormaPago pagoDebitCard = 
			addFormaPago(debitCard, MFormaPago.PAYMENTRULE_CreditCard, term.getC_PaymentTerm_ID(), 
					0, false, trxName);
		if (!save(pagoDebitCard, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(debitCard);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(debitCard);
		}

		//Credit card - Tarjeta de Credito
		String creditCard = Utilerias.getMessage(ctx, locale, "msj.creditCard");
		
		MFormaPago pagoCreditCard = 
			addFormaPago(creditCard, MFormaPago.PAYMENTRULE_CreditCard, term2.getC_PaymentTerm_ID(), 
					0, false, trxName);
		
		if (!save(pagoCreditCard, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(creditCard);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(creditCard);
		}

		//Cash Refund - Devolucion en Efectivo
		String cashRefund = Utilerias.getMessage(ctx, locale, "msj.cashRefund");
		
		MFormaPago pagoCashRefund = 
			addFormaPago(cashRefund, MFormaPago.PAYMENTRULE_Cash, term.getC_PaymentTerm_ID(), 
					pagoCash.getEXME_FormaPago_ID(), true, trxName);
		
		if (!save(pagoCashRefund, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(cashRefund);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(cashRefund);
		}

		
		//Check Refund - Devolucion en Cheque
		String checkRefund = Utilerias.getMessage(ctx, locale, "msj.checkRefund");
		
		MFormaPago pagoDevCheque = 
			addFormaPago(checkRefund, MFormaPago.PAYMENTRULE_Check, term.getC_PaymentTerm_ID(), 
					pagoCheck.getEXME_FormaPago_ID(), true, trxName);
		
		if (!save(pagoDevCheque, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(checkRefund);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(checkRefund);
		}

		
		//Credit Card Refund - Devolucion de Tarjeta de Credito
		String creditCardRefund = Utilerias.getMessage(ctx, locale, "msj.creditCardRefund");
		MFormaPago pagoCredCardRefund = 
			addFormaPago(creditCardRefund, MFormaPago.PAYMENTRULE_CreditCard, term.getC_PaymentTerm_ID(), 
					pagoCreditCard.getEXME_FormaPago_ID(), true, trxName);
		
		if (!save(pagoCredCardRefund, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(creditCardRefund);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(creditCardRefund);
		}

		
		//Debit Card Refund - Devolucion en Tarjeta de Debito
		String debitCardRefund = Utilerias.getMessage(ctx, locale, "msj.debitCardRefund");
		MFormaPago objlDebitCardRefund = 
			addFormaPago(debitCardRefund, MFormaPago.PAYMENTRULE_CreditCard, term.getC_PaymentTerm_ID(), 
					pagoDebitCard.getEXME_FormaPago_ID(), true, trxName);
		
		if (!save(objlDebitCardRefund, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(debitCardRefund);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(debitCardRefund);
		}
		
		
		//Returning Accounts Receivable - Devolucion de Cuentas Por Cobrar
		String returningAccountsReceivable = Utilerias.getMessage(ctx, locale, "msj.returningAccountsReceivable");
		MFormaPago objlretAccReceivable = 
			addFormaPago(returningAccountsReceivable, MFormaPago.PAYMENTRULE_OnCredit, term2.getC_PaymentTerm_ID(), 
					pagoCtasCobrar.getEXME_FormaPago_ID(), true, trxName);
		
		if (!save(objlretAccReceivable, false, trxName)) {
			m_info.append(msjError).append("Pay: ").append(returningAccountsReceivable);
			return false;
		} else {
			m_info.append(msjExito).append("Pay: ").append(returningAccountsReceivable);
		}

		// Se crea el C_BPartner de Insurance
		//Ya no se utiliza, Jesus Cantu, 4 Febrero 2014
		/*MBPartner partner = 
			addMBPartner(insurance, bpGroup.getC_BP_Group_ID(), term2.getC_PaymentTerm_ID(), trxName);
		
		if (!save(partner, false, trxName)) {
			m_info.append(msjError).append(insurance);
			return false;
		}
		m_info.append(msjExito).append(insurance);*/
		
		
		customProps.put("#PLVStandard", M_PriceList_Version_ID);
		customProps.put("#PLStandard", M_PriceList_ID);
		
		if (ctx.get("#ListDefaultRoomTypes")!= null) {
			
			List<MEXMETipoHabitacion> lstRoomsTemp = 
				(ArrayList<MEXMETipoHabitacion>) ctx.get("#ListDefaultRoomTypes");
			
			for (MEXMETipoHabitacion roomTypeTmp: lstRoomsTemp) {
				
				if (!save(roomTypeTmp, false, trxName)) {
					m_info.append(msjError).append(roomTypeTmp.getName());
					return false;
				}
				if (roomTypeTmp.getName().endsWith(Utilerias.getMessage(ctx, locale, "tipoHab.outpatientRoom")))
					customProps.put("#TipoOutpatientRoom", roomTypeTmp);
				m_info.append(msjExito).append(roomTypeTmp.getName());
			}
		}
		
		return true;
	}
	
	
	/*  agrega la configuracion de enfermeria
     *  
     *  @param trxName String
     *  @param ctx Properties
     *  @return MEXMEConfigEnf
     *  
     *  @author rsolorzano
     */  
	public MEXMEConfigEnf addConfigEnfermeria(Properties ctx,String trxName) {
		
		MEXMEConfigEnf enfermeria = new MEXMEConfigEnf(m_ctx, 0, trxName);
		String tipoCliente = (String) ctx.get("#TipoCliente");
		
		long liquidID = 0;
		long catheterID = 0;
		long drugID = 0;
		long diabeticID = 0;
		int notesID = 0;
		int nurseID = 0;
		long foleyID = 0;
		long ngID = 0;
		
		try {
			liquidID =  Datos.getIDFromValue(Env.getCtx(), "EXME_ProductFam", "LIQUIDS");
			catheterID = Datos.getIDFromValue(Env.getCtx(), "EXME_ProductFam", "CATHETER");
			drugID = Datos.getIDFromValue(Env.getCtx(), "EXME_ProductFam", "ETHICAL DRUGS");
			diabeticID = Datos.getIDFromValue(Env.getCtx(), "EXME_ProductFam", "DIABETIC");
			foleyID = Datos.getIDFromValue(Env.getCtx(), "EXME_TipoProd", "FOLEY");
			ngID =  Datos.getIDFromValue(Env.getCtx(), "EXME_TipoProd", "NASOGASTRIC");
		} catch (Exception e) {
			log.log(Level.SEVERE, "addConfigEnfermeria");
		}
		
		if(tipoCliente.equals(MEXMETipoConfigurador.TIPOAREA_AMBULATORYSURGERYCENTER) || tipoCliente.equals(MEXMETipoConfigurador.TIPOAREA_OUTPATIENTCARECENTER)){
			MCuestionario cuestInd = MCuestionario.getByAreaType(Env.getCtx(), "Outpatient Notes", MCuestionario.TIPOAREA_Ambulatory);
			MCuestionario cuestNurse = MCuestionario.getByAreaType(Env.getCtx(), "Outpatient Nurse", MCuestionario.TIPOAREA_Ambulatory);
			
			if(cuestInd != null){
				notesID = cuestInd.getEXME_Cuestionario_ID();
			}
			
			if(cuestNurse!=null){
				nurseID = cuestNurse.getEXME_Cuestionario_ID();
			}
		}
		
		if(tipoCliente.equals(MEXMETipoConfigurador.TIPOAREA_HOSPITALCENTER)){
			MCuestionario cuestInd = MCuestionario.getByAreaType(Env.getCtx(), "Inpatient Notes", MCuestionario.TIPOAREA_Hospitalization);
			MCuestionario cuestNurse = MCuestionario.getByAreaType(Env.getCtx(), "Inpatient Nurse", MCuestionario.TIPOAREA_Hospitalization);
			
			if(cuestInd != null){
				notesID = cuestInd.getEXME_Cuestionario_ID();
			}
			
			if(cuestNurse!=null){
				nurseID = cuestNurse.getEXME_Cuestionario_ID();
			}
		}
		
		
		enfermeria.setAD_Client_ID(getAD_Client_ID());
		enfermeria.setAD_Org_ID(getAD_Org_ID());
		enfermeria.setTipoDieta(MEXMEViasAdministracion.TIPO_Diet);
		enfermeria.setTipoMedicamento(MEXMEViasAdministracion.TIPO_Medicine);
		enfermeria.setFam_Soluciones_ID(Integer.parseInt(String.valueOf(liquidID)));
		enfermeria.setFam_Medicamentos_ID(Integer.parseInt(String.valueOf(drugID)));
		enfermeria.setFam_Cateteres_ID(Integer.parseInt(String.valueOf(catheterID)));
		enfermeria.setFam_Insulinas_ID(Integer.parseInt(String.valueOf(diabeticID)));
		enfermeria.setEXME_IndicaCuest_ID(notesID);
		enfermeria.setEXME_EnfeCuest_ID(nurseID);
		enfermeria.setEXME_TipoProdFoley_ID(Integer.parseInt(String.valueOf(foleyID)));
		enfermeria.setEXME_TipoProdSng_ID(Integer.parseInt(String.valueOf(ngID)));
		enfermeria.setTouchScr(true);
		enfermeria.setUniDosis(true);
		enfermeria.setIsActive(true);
		
		return enfermeria;
	}
	
	public static String abc(String name) {
		List<String> letters = new ArrayList<String>();
		if (name != null && !name.equals("*")) {
			String[] words = StringUtils.split(name, ' ');
			for (String word : words) {
				letters.add(StringUtils.substring(word, 0, 1));
			}
			letters.add(" ");
		}
		name = StringUtils.join(letters, "");
		return name;
	}
	
	public static void main(String[] args){
		System.out.println(abc("Com Gen Hos"));
	}
	
	/**
	 *  Create Default main entities for a Parten Organization.
	 *  - Dimensions & BPGroup, Prod Category)
	 *  - Location, Locator, Warehouse
	 *  - PriceList
	 *  - Cashbook
	 *  @param objaCxt The app context
	 *  @param objaLoc The Location object
	 *  @param iaC_Currency_ID currency_id
	 *  @param iaAD_Client_Id Ad Client ID
	 *  @param locale El objeto Locale para regionalizar.
	 *  @param saTrxName El Nombre de Transaccion Actual.
	 *  @return true if created
	 *  @author jcantu creado el 20 de Junio del 2012, modificado el 22 de Nov del 2013.
	 */
	public boolean createEntitiesParentOrg(Properties objaCxt, MLocation objaLoc, 
			int iaC_Currency_ID, int iaAD_Client_Id, Locale locale, String saTrxName) { 
	
		StringBuilder msjExito = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		StringBuilder msjError = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		int ilC_Country_ID = objaLoc.getC_Country_ID();
		int ilC_Region_ID = objaLoc.getC_Region_ID();
		String slCity = objaLoc.getCity();
		
		
		//Si es Nulo crear uno nuevo
		if (m_info == null) {
			m_info = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		}
		
		msjExito.append("\n");
		msjExito.append(Utilerias.getMessage(m_ctx, null, "msj.exito.reglaCuestionario.registroCorrecto"));
		msjExito.append(": ");
		
		msjError.append("\n");
		msjError.append(Utilerias.getMessage(m_ctx, null, "msj.errorsavereg"));
		msjError.append(": ");
		
		
		// Obtenemos del contecto el id de la Organizacion recien creada.
		String slAD_Org_ID = objaCxt.getProperty("#AD_Org_ID");
		
		log.info("createEntitiesParentOrg = C_Country_ID = " + ilC_Country_ID 
			+ ", City=" + slCity + ", C_Region_ID=" + ilC_Region_ID);
		
		m_info.append("\n---createEntitiesParentOrg---\n");
		
		String defaultName = Msg.translate(m_lang, "Standard");
		
		// La descripcion sera utilizada para las restricciones en el name y value.
		// Ya que los indices de las tablas no permiten tener registros con el mismo nombre
		// o value seguns ea el caso. Se le concatena el ID de la Organizacion.
		String defaultDescription = slAD_Org_ID + " " + defaultName;
		
		//	Create Marketing Channel/Campaign
		/*int C_Channel_ID = getNextID(getAD_Client_ID(), "C_Channel");
		sqlCmd = new StringBuffer("INSERT INTO C_Channel ");
		sqlCmd.append("(C_Channel_ID,Name,");
		sqlCmd.append(m_stdColumns).append(") VALUES (");
		sqlCmd.append(C_Channel_ID).append(",").append(defaultEntry);
		sqlCmd.append(m_stdValues).append(")");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "Channel NOT inserted");
		
		
		int C_Campaign_ID = getNextID(getAD_Client_ID(), "C_Campaign");
		sqlCmd = new StringBuffer("INSERT INTO C_Campaign ");
		sqlCmd.append("(C_Campaign_ID,C_Channel_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Value,Name,Costs) VALUES (");
		sqlCmd.append(C_Campaign_ID).append(",").append(C_Channel_ID).append(",").append(m_stdValues).append(",");
		sqlCmd.append(defaultEntry).append(defaultEntry).append("0)");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no == 1)
			m_info.append(Msg.translate(m_lang, "C_Campaign_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "Campaign NOT inserted");
		if (m_hasMCampaign)
		{
			//  Default
			sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
			sqlCmd.append("C_Campaign_ID=").append(C_Campaign_ID);
			sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
			sqlCmd.append(" AND ElementType='MC'");
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
			if (no != 1)
				log.log(Level.SEVERE, "AcctSchema ELement Campaign NOT updated");
		} 

		//	Create Sales Region
		int C_SalesRegion_ID = getNextID(getAD_Client_ID(), "C_SalesRegion");
		sqlCmd = new StringBuffer ("INSERT INTO C_SalesRegion ");
		sqlCmd.append("(C_SalesRegion_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Value,Name,IsSummary) VALUES (");
		sqlCmd.append(C_SalesRegion_ID).append(",").append(m_stdValues).append(", ");
		sqlCmd.append(defaultEntry).append(defaultEntry).append("'N')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no == 1)
			m_info.append(Msg.translate(m_lang, "C_SalesRegion_ID")).append("=").append(defaultName).append("\n");
		else
			log.log(Level.SEVERE, "SalesRegion NOT inserted");
		if (m_hasSRegion)
		{
			//  Default
			sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
			sqlCmd.append("C_SalesRegion_ID=").append(C_SalesRegion_ID);
			sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
			sqlCmd.append(" AND ElementType='SR'");
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
			if (no != 1)
				log.log(Level.SEVERE, "AcctSchema ELement SalesRegion NOT updated");
		}*/
		

		// Creamos la Lista de Precios
		// Indice Cliente - Name, por lo que hay que agregarle 
		// la Organizacion al Name para que lo guarde correctamente, eso esta en defaultDescription.
		MPriceList pl = new MPriceList(objaCxt, 0, null);
		pl.setName(defaultDescription);
		pl.setDescription(defaultDescription);
		pl.setC_Currency_ID(iaC_Currency_ID);
		pl.setIsDefault(true);
		
		//Lista de precios de Venta debe estar palomeado. Jesus Cantu. 19 Junio 2012.
		//Revisado co Helio Gutierrez en requerimientos de wizards.
		pl.setIsSOPriceList(true);
		
		if (!pl.save(saTrxName)) {
			log.log(Level.SEVERE, "PriceList NOT inserted");
			m_info.append(msjError).append("PriceList Not inserted: ").append(defaultDescription);
		}
		
		m_info.append(msjExito).append("PriceList: ").append(defaultDescription);
		
		M_PriceList_ID = pl.getM_PriceList_ID();

		// Esquema de Descuentos
		// No hay problema con el name en los indices de la tabla de MDiscountSchema.
		MDiscountSchema ds = new MDiscountSchema(objaCxt, 0, null);
		ds.setName(defaultName);
		ds.setDescription(defaultDescription);
		ds.setDiscountType(MDiscountSchema.DISCOUNTTYPE_Pricelist);
		
		if (!ds.save(saTrxName)) {
			log.log(Level.SEVERE, "DiscountSchema NOT inserted");
			m_info.append(msjError).append("DiscountSchema NOT inserted: ").append(defaultDescription);
		}
		
		m_info.append(msjExito).append("DiscountSchema: ").append(defaultDescription);
		
		//  PriceList Version
		MPriceListVersion plv = new MPriceListVersion(pl);
		plv.setName();
		plv.setDescription(defaultDescription);
		plv.setM_DiscountSchema_ID(ds.getM_DiscountSchema_ID());
		
		if (!plv.save()) {
			log.log(Level.SEVERE, "PriceList_Version NOT inserted");
			m_info.append(msjError).append("PriceList_Version NOT inserted: ").append(defaultDescription);
		}
		
		m_info.append(msjExito).append("PriceList_Version: ").append(defaultDescription);
		
		M_PriceList_Version_ID = plv.getM_PriceList_Version_ID();

		/**
		 *  Business Partner
		 */
		
		//  Create BP Group

		//Indice Cliente - Value, por lo que hay que agregarle 
		//la Organizacion al Value para que lo guarde correctamente esto lo traemos en defaultDescription.
		MBPGroup bpg = new MBPGroup (objaCxt, 0, null);
		bpg.setValue(defaultDescription);
		bpg.setName(defaultName);
		bpg.setDescription(defaultDescription);
		bpg.setIsDefault(true);
		
		//Se coloca el esquema recien creado.
		bpg.setM_DiscountSchema_ID(ds.getM_DiscountSchema_ID());
		
		//Se agrega la Lista de Precios Creada para la Organizacion
		bpg.setM_PriceList_ID(M_PriceList_ID);
		
		if (bpg.save(saTrxName)) {
			m_info.append(msjExito).append("BP Group: ").append(defaultDescription);
		} else {
			log.log(Level.SEVERE, "BP Group NOT inserted");
			m_info.append(msjError).append("BP Group NOT inserted: ").append(defaultDescription);
		}
		
		//	Create BPartner
		// Indice Client - Value por lo que es necesario colocar el id de la organizacion
		//Esto lo trae la variable defaultDescription
		MBPartner bp = new MBPartner (objaCxt, 0, null);
		bp.setValue(defaultDescription);
		bp.setDescription(defaultDescription);
		
		// Se coloca el nombre en duro ya que en Office Visit o ejecucion de Cita busca un C_BPartner asi en duro
		// Esto a la hora de cerrar la cita, referencia, RQM 1667: Defecto "Educación/ Instrucciones: Null".
		//Jesus Cantu
		bp.setName("STANDARD");
		
		//Se coloca el TAXID default por cambios en BPartner.
		//Jesus Cantu 14 Agosto 2013.
		bp.setTaxID("XAXX010101000");
		
		bp.setBPGroup(bpg);
		if (bp.save(saTrxName)) {
			m_info.append(msjExito).append("BPartner ID: ").append(bp.getC_BPartner_ID()). append(" Name=STANDARD").
			append(" value = ").append(defaultDescription);

			// Location for Standard BP
			MLocation bpLoc = new MLocation(objaCxt, ilC_Country_ID, 
					ilC_Region_ID, slCity, null);
			bpLoc.save(saTrxName);
			MBPartnerLocation bpl = new MBPartnerLocation(bp);
			bpl.setC_Location_ID(bpLoc.getC_Location_ID());
			
			// Adecuacion al wizard para Nimbo, de despalomear la opcion de Direccion a remitir en la ubicacion
			// del Socio de Negocios STANDARD. Jesus Cantu. 12 de Junio 2012.
			bpl.setIsRemitTo(false);
			
			if (!bpl.save()) {
				log.log(Level.SEVERE, "BP_Location (Standard) NOT inserted");
				m_info.append(msjError).append("BP_Location (Standard) NOT inserted: ").append(defaultDescription);
			}
			
			m_info.append(msjExito).append("BP_Location: ").append(defaultDescription);
			
		} else {
			log.log(Level.SEVERE, "BPartner NOT inserted");
			m_info.append(msjError).append("BP_Location (Standard) NOT inserted: ").append(defaultDescription);
			bp = MBPartner.get(objaCxt, defaultName);
		}
		C_BPartner_ID = bp.getC_BPartner_ID();
		
		/*Jesus Cantu. Solo se crea un esquema C_AcctSchema_Element asignado a la primer organizacion
		que se creo, revisar con Helio, se comenta codigo de actualizacion de C_AcctSchema_Element.
		
		//  Default
		sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
		sqlCmd.append("C_BPartner_ID=").append(bp.getC_BPartner_ID());
		sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
		sqlCmd.append(" AND ElementType='BP'");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "AcctSchema Element BPartner NOT updated");
		createPreference("C_BPartner_ID", String.valueOf(bp.getC_BPartner_ID()), 143);*/

		
		//TaxCategory
		//Ya no se necesita crear otro tax category. Jesus
		/*int C_TaxCategory_ID = getNextID(getAD_Client_ID(), "C_TaxCategory");
		sqlCmd = new StringBuffer ("INSERT INTO C_TaxCategory ");
		sqlCmd.append("(C_TaxCategory_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Name,IsDefault) VALUES (");
		sqlCmd.append(C_TaxCategory_ID).append(",").append(m_stdValues).append(", ");
		
		
	
		sqlCmd.append(defaultEntry).append("'Y')");
		
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "TaxCategory NOT inserted"); */
		
		//EXPERT: Lama .- Crear Charge Default
		//No se requiere el Charege. Jesus
		/*int C_Charge_ID = getNextID(getAD_Client_ID(), "C_Charge");
		sqlCmd = new StringBuffer ("INSERT INTO C_Charge ");
		sqlCmd.append("(C_Charge_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Name, value, C_TaxCategory_ID) VALUES (");
		sqlCmd.append(C_Charge_ID).append(",").append(m_stdValues).append(", ");
		sqlCmd.append(defaultEntry).append(defaultEntry).append(C_TaxCategory_ID).append(")");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "C_Charge NOT inserted");*/
		
		/**
		 *  Product
		 */
		//  Create Product Category
		//Indice Cliente - Value, por lo que hay que agregarle 
		//la Organizacion al Value para que lo guarde correctamente esto lo traemos en defaultDescription.
		MProductCategory pc = new MProductCategory(objaCxt, 0, null);
		pc.setValue(defaultDescription);
		pc.setName(defaultName);
		pc.setDescription(defaultDescription);
		pc.setIsDefault(true);
		
		if (pc.save(saTrxName)) {
			m_info.append(msjExito).append("Product Category: ").append(defaultDescription);
		} else {
			log.log(Level.SEVERE, "Product Category NOT inserted");
			m_info.append(msjError).append("Product Category NOT inserted: ").append(defaultDescription);
		}

		//  UOM (EA)
		int C_UOM_ID = 100;

		//26 Nov 2013. No se necesita un Tax Estandar para la Parent Organization. Revisado con Rodrigo.
		//Traer el C_TaxCategory_ID del 16% creado cuando se creo la primer organizacion.
		//Jesus Cantu. Esto porque no debe crearse otro.
		/*int C_TaxCategory_ID = MTaxCategory.getTaxCategoryId(null, 
				iaAD_Client_Id, 0, Utilerias.getMessage(m_ctx, locale, "msj.tasa16"));*/

		//  Tax - Zero Rate

		//Indice Cliente - Name, por lo que hay que agregarle la Organizacion al Name
		//para que lo guarde correctamente esto lo traemos en defaultDescription.
		//Se coloca harcodeado el 16 como impuesto al la tasa Estandar. Jesus Cantu 5 Sept 2012
		/*MTax tax = new MTax (objaCxt, defaultDescription, new BigDecimal(16), C_TaxCategory_ID, null);
		tax.setIsDefault(true);
		tax.setDescription(defaultDescription);
		
		if (tax.save(saTrxName)) {
			m_info.append(msjExito).append("Tax: ").append(defaultDescription);
		} else {
			log.log(Level.SEVERE, "Tax NOT inserted");
			m_info.append(msjError).append("Tax NOT inserted: ").append(defaultDescription);
		}*/

		//	Create Product
		//22 Nov 2013, no se necesita un Producto Estandar.
		/*MProduct product = new MProduct (objaCxt, 0, null);
		product.setValue(defaultName);
		product.setName(defaultName);
		product.setDescription(defaultDescription);
		product.setC_UOM_ID(C_UOM_ID);
		product.setM_Product_Category_ID(pc.getM_Product_Category_ID());
		product.setC_TaxCategory_ID(C_TaxCategory_ID);
		product.setProductClass(MProduct.PRODUCTCLASS_Others);//Set Product Class Others
		
		if (product.save(saTrxName)) {
			m_info.append(msjExito).append("M_Product: ").append(defaultName);
		} else {
			log.log(Level.SEVERE, "Product NOT inserted");
			m_info.append(msjError).append("Product NOT inserted: ").append(defaultName);
		}*/
		
		/*Jesus Cantu. Solo se crea un esquema C_AcctSchema_Element asignado a la primer organizacion
		que se creo, revisar con Helio, se comenta codigo de actualizacion de C_AcctSchema_Element.
		//  Default
		sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
		sqlCmd.append("M_Product_ID=").append(product.getM_Product_ID());
		sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
		sqlCmd.append(" AND ElementType='PR'");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "AcctSchema Element Product NOT updated");*/
		
		
		//  ProductPrice
		//22 Nov 2013. No se necesita un producto Estandar.
		/*MProductPrice pp = new MProductPrice(plv, product.getM_Product_ID(), 
			Env.ONE, Env.ONE, Env.ONE);
		if (!pp.save()) {
			log.log(Level.SEVERE, "ProductPrice NOT inserted");
			m_info.append(msjError).append("ProductPrice NOT inserted: ").append(defaultName);
		}
		
		m_info.append(msjExito).append("ProductPrice: ").append(defaultName);*/

		/**
		 *  Location, Warehouse, Locator
		 */
		//  Location (Company)
		//Mismo Location creado en Parent Organization, no se necesita crear ni
		//tampoco actualizar la tabla de ad_orginfo pq ya lo hace en parentorganization.
		//Jesus Cantu
		
		/*MLocation loc = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, m_trx.getTrxName());
		loc.save();
		sqlCmd = new StringBuffer ("UPDATE AD_OrgInfo SET C_Location_ID=");
		sqlCmd.append(loc.getC_Location_ID()).append(" WHERE AD_Org_ID=").append(getAD_Org_ID());
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "Location NOT inserted");
		createPreference("C_Country_ID", String.valueOf(C_Country_ID), 0);*/

		//  Default Warehouse
		MWarehouse wh = new MWarehouse(objaCxt, 0, null);
		wh.setValue(defaultName);
		wh.setName(defaultName);
		wh.setDescription(defaultDescription);
		wh.setC_Location_ID(objaLoc.getC_Location_ID());
		if (!wh.save(saTrxName)) {
			log.log(Level.SEVERE, "Warehouse NOT inserted");
			m_info.append(msjError).append("Warehouse NOT inserted: ").append(defaultName);
		}
		
		m_info.append(msjExito).append("Warehouse: ").append(defaultName);

		//   Locator
		MLocator locator = new MLocator(wh, defaultName);
		locator.setIsDefault(true);
		if (!locator.save()) {
			log.log(Level.SEVERE, "Locator NOT inserted");
			m_info.append(msjError).append("Locator NOT inserted: ").append(defaultName);
		}
		
		m_info.append(msjExito).append("Locator: ").append(defaultName);

		//  Update ClientInfo
		
		/*Solo se crea un registro en AD_ClientInfo asignado a la organizacion 0,
		 revisar con Helio, se comenta codigo de actualizacion de AD_ClientInfo.
		
		sqlCmd = new StringBuffer ("UPDATE AD_ClientInfo SET ");
		sqlCmd.append("C_BPartnerCashTrx_ID=").append(bp.getC_BPartner_ID());
		sqlCmd.append(",M_ProductFreight_ID=").append(product.getM_Product_ID());
//		sqlCmd.append("C_UOM_Volume_ID=");
//		sqlCmd.append(",C_UOM_Weight_ID=");
//		sqlCmd.append(",C_UOM_Length_ID=");
//		sqlCmd.append(",C_UOM_Time_ID=");
		sqlCmd.append(" WHERE AD_Client_ID=").append(getAD_Client_ID());
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
		{
			String err = "ClientInfo not updated";
			log.log(Level.SEVERE, err);
			m_info.append(err);
			return false;
		}*/

/*      Se comenta la creacion de los BPartner para Admin y User. Revisado con Helio.
 
		//	Create Sales Rep for Client-User
		MBPartner bpCU = new MBPartner (m_ctx, 0, m_trx.getTrxName());
		bpCU.setValue(AD_User_U_Name);
		bpCU.setName(AD_User_U_Name);
		bpCU.setBPGroup(bpg);
		bpCU.setIsEmployee(true);
		bpCU.setIsSalesRep(true);
		if (bpCU.save())
			m_info.append(Msg.translate(m_lang, "SalesRep_ID")).append("=").append(AD_User_U_Name);
		else
			log.log(Level.SEVERE, "SalesRep (User) NOT inserted");
		//  Location for Client-User
		MLocation bpLocCU = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, m_trx.getTrxName());
		bpLocCU.save();
		MBPartnerLocation bplCU = new MBPartnerLocation(bpCU);
		bplCU.setC_Location_ID(bpLocCU.getC_Location_ID());
		if (!bplCU.save())
			log.log(Level.SEVERE, "BP_Location (User) NOT inserted");
		//  Update User
		sqlCmd = new StringBuffer ("UPDATE AD_User SET C_BPartner_ID=");
		sqlCmd.append(bpCU.getC_BPartner_ID()).append(" WHERE AD_User_ID=").append(AD_User_U_ID);
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "User of SalesRep (User) NOT updated");


		//	Create Sales Rep for Client-Admin
		MBPartner bpCA = new MBPartner (m_ctx, 0, m_trx.getTrxName());
		bpCA.setValue(AD_User_Name);
		bpCA.setName(AD_User_Name);
		bpCA.setBPGroup(bpg);
		bpCA.setIsEmployee(true);
		bpCA.setIsSalesRep(true);
		if (bpCA.save())
			m_info.append(Msg.translate(m_lang, "SalesRep_ID")).append("=").append(AD_User_Name);
		else
			log.log(Level.SEVERE, "SalesRep (Admin) NOT inserted");
		//  Location for Client-Admin
		MLocation bpLocCA = new MLocation(m_ctx, C_Country_ID, C_Region_ID, City, m_trx.getTrxName());
		bpLocCA.save();
		MBPartnerLocation bplCA = new MBPartnerLocation(bpCA);
		bplCA.setC_Location_ID(bpLocCA.getC_Location_ID());
		if (!bplCA.save())
			log.log(Level.SEVERE, "BP_Location (Admin) NOT inserted");
		//  Update User
		sqlCmd = new StringBuffer ("UPDATE AD_User SET C_BPartner_ID=");
		sqlCmd.append(bpCA.getC_BPartner_ID()).append(" WHERE AD_User_ID=").append(AD_User_ID);
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "User of SalesRep (Admin) NOT updated");


		*/
		
		//  Payment Term
		/* Se comenta codigo ya que se crea abajo en el metodo de clientConfiguration
		 * el payment term de immediate para cada client Jesus Cantu
		 */
		
		/*int C_PaymentTerm_ID = getNextID(getAD_Client_ID(), "C_PaymentTerm");
		sqlCmd = new StringBuffer ("INSERT INTO C_PaymentTerm ");
		sqlCmd.append("(C_PaymentTerm_ID,").append(m_stdColumns).append(",");
		sqlCmd.append("Value,Name,NetDays,GraceDays,DiscountDays,Discount,DiscountDays2,Discount2,IsDefault) VALUES (");
		sqlCmd.append(C_PaymentTerm_ID).append(",").append(m_stdValues).append(",");
		sqlCmd.append("'Immediate','Immediate',0,0,0,0,0,0,'Y')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "PaymentTerm NOT inserted");*/

		//  Project Cycle
		/*C_Cycle_ID = getNextID(getAD_Client_ID(), "C_Cycle");
		sqlCmd = new StringBuffer ("INSERT INTO C_Cycle ");
		sqlCmd.append("(C_Cycle_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Name,C_Currency_ID) VALUES (");
		sqlCmd.append(C_Cycle_ID).append(",").append(m_stdValues).append(", ");
		sqlCmd.append(defaultEntry).append(iaC_Currency_ID).append(")");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no != 1)
			log.log(Level.SEVERE, "Cycle NOT inserted");*/

		/**
		 *  Organization level data	===========================================
		 */

		//	Create Default Project
		
		/*Jesus Cantu. Solo se crea un esquema C_AcctSchema_Element asignado a la primer organizacion
		que se creo, revisar con Helio, se comenta codigo de C_Project.
		int C_Project_ID = getNextID(getAD_Client_ID(), "C_Project");
		sqlCmd = new StringBuffer ("INSERT INTO C_Project ");
		sqlCmd.append("(C_Project_ID,").append(m_stdColumns).append(",");
		sqlCmd.append(" Value,Name,C_Currency_ID,IsSummary) VALUES (");
		sqlCmd.append(C_Project_ID).append(",").append(m_stdValuesOrg).append(", ");
		sqlCmd.append(defaultEntry).append(defaultEntry).append(iaC_Currency_ID).append(",'N')");
		no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
		if (no == 1)
			m_info.append(Msg.translate(m_lang, "C_Project_ID")).append("=").append(defaultName);
		else
			log.log(Level.SEVERE, "Project NOT inserted");
		//  Default Project
		if (m_hasProject)
		{
			sqlCmd = new StringBuffer ("UPDATE C_AcctSchema_Element SET ");
			sqlCmd.append("C_Project_ID=").append(C_Project_ID);
			sqlCmd.append(" WHERE C_AcctSchema_ID=").append(m_as.getC_AcctSchema_ID());
			sqlCmd.append(" AND ElementType='PJ'");
			no = DB.executeUpdate(sqlCmd.toString(), m_trx.getTrxName());
			if (no != 1)
				log.log(Level.SEVERE, "AcctSchema ELement Project NOT updated");
		}*/

		//  CashBook
		MCashBook cb = new MCashBook(objaCxt, 0, null);
		cb.setName(defaultName);
		cb.setDescription(defaultDescription);
		cb.setC_Currency_ID(iaC_Currency_ID);
		
		if (cb.save(saTrxName)) {
			m_info.append(Msg.translate(m_lang, "C_CashBook_ID")).append("=").append(defaultName);
		} else {
			log.log(Level.SEVERE, "CashBook NOT inserted");
			m_info.append(msjError).append("CashBook NOT inserted: ").append(defaultName);
		}
		
		
		//
		//m_trx.commit();
		//m_trx.close();
		log.info("finish createEntitiesParentOrg");
		return true;
	}   //  createEntitiesParentOrg



	public boolean isElecAcct() {
		return elecAcct;
	}



	public void setElecAcct(boolean elecAcct) {
		this.elecAcct = elecAcct;
	}
	
}   //  MSetup
