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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.commons.lang.StringUtils;
import org.compiere.db.CConnection;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;
import org.compiere.util.UtilsDbPort;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.XMPPException;

import com.ecaresoft.apps.secure.CryptoUtils;
import com.ecaresoft.chat.ChatConnection;
import com.ecaresoft.chat.beans.ChatMessage;
import com.ecaresoft.util.PasswordHandler;

/**
 * User Model
 *
 * @author Jorg Janke
 * @version $Id: MUser.java,v 1.2 2006/08/23 01:32:38 mrojas Exp $
 */
public class MUser extends X_AD_User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Get active User/Contact
	 *
	 * @param ctx
	 *            context
	 * @param C_BPartner_ID
	 *            id
	 * @return array of users
	 */
	public static MUser[] getContactUser(Properties ctx) {
		final List<MUser> list = new Query(ctx, Table_Name, //
			" EXISTS (SELECT * FROM C_BPartner bp WHERE AD_User.C_BPartner_ID=bp.C_BPartner_ID AND (bp.IsEmployee='Y' OR bp.IsSalesRep='Y'))", null)//
		.addAccessLevelSQL(true)//
		.setOrderBy("NAME ASC")
		.list();

		final MUser[] retValue = new MUser[list.size()];
		list.toArray(retValue);
		return retValue;
	}


	/**
	 * Get active Users of BPartner
	 *
	 * @param ctx
	 *            context
	 * @param C_BPartner_ID
	 *            id
	 * @return array of users
	 */
	public static MUser[] getOfBPartner(Properties ctx, int C_BPartner_ID) {
		final List<MUser> list = new Query(ctx, Table_Name, " C_BPartner_ID=?", null)//
			.setParameters(C_BPartner_ID)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy("NAME ASC").list();

		MUser[] retValue = new MUser[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfBPartner

	/**
	 * Get Users with Role
	 *
	 * @param role
	 *            role
	 * @return array of users
	 */
	public static MUser[] getWithRole(MRole role) {

		final List<MUser> list = new Query(role.getCtx(), Table_Name, //
			" EXISTS (SELECT * FROM AD_User_Roles ur WHERE ur.AD_User_ID=AD_User.AD_User_ID AND ur.AD_Role_ID=? AND ur.IsActive='Y')", null)//
			.setParameters(role.getAD_Role_ID())//
			.setOnlyActiveRecords(true)//
			.list();

		MUser[] retValue = new MUser[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getWithRole

	/**
	 * Get User (cached) Also loads Admninistrator (0)
	 *
	 * @param ctx
	 *            context
	 * @param AD_User_ID
	 *            id
	 * @return user
	 */
	public static MUser get(Properties ctx, int AD_User_ID) {
		Integer key = new Integer(AD_User_ID);
		MUser retValue = (MUser) s_cache.get(key);
		if (retValue == null
		// Expert : Lama (verificar la sesion, para usar o no el objeto del cache)
			|| (Env.getAD_Session_ID(ctx) != Env.getAD_Session_ID(retValue.getCtx()))) // lama
		{
			retValue = new MUser(ctx, AD_User_ID, null);
			if (AD_User_ID == 0) {
				String trxName = null;
				retValue.load(trxName); // load System Record
			}
			s_cache.put(key, retValue);
		}
		return retValue;
	} // get

	/**
	 * Get Current User (cached)
	 *
	 * @param ctx
	 *            context
	 * @return user
	 */
	public static MUser get(Properties ctx) {
		return get(ctx, Env.getAD_User_ID(ctx));
	} // get

	/**
	 * Get User
	 *
	 * @param ctx
	 *            context
	 * @param name
	 *            name
	 * @param password
	 *            password
	 * @return user or null
	 */
	public static MUser get(Properties ctx, String name, String password) {
		if (name == null || name.length() == 0 || password == null
				|| password.length() == 0) {
			s_log.warning("Invalid Name/Password = " + name + "/" + password);
			return null;
		}

		MUser retValue = null;
		String sql = "SELECT AD_User.* FROM AD_User WHERE AD_User.Name=? AND AD_User.Password=? AND AD_User.IsActive='Y' ";// expert
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "AD_User");
		// expert  .- se  agrego nivel de acceso
		PreparedStatement pstmt = null;
		ResultSet rs = null;// Expert
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, name.toUpperCase());
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MUser(ctx, rs, null);
				if (rs.next()) {
					s_log.warning("More than one user with Name/Password = " + name);
				}
			} else {
				s_log.fine("No record");
			}
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	} // get

	/**
	 * Get Name of AD_User
	 *
	 * @param AD_User_ID
	 *            System User
	 * @return Name of user or ?
	 */
	public static String getNameOfUser(int AD_User_ID) {
		return DB.getSQLValueString(null, "SELECT Name FROM AD_User WHERE AD_User_ID=?", AD_User_ID);
	} // getNameOfUser

	/**
	 * User is SalesRep
	 *
	 * @param AD_User_ID
	 *            user
	 * @return true if sales rep
	 */
	public static boolean isSalesRep(int AD_User_ID) {
		if (AD_User_ID == 0)
			return false;
		String sql = "SELECT MAX(AD_User_ID) FROM AD_User u"
				+ " INNER JOIN C_BPartner bp ON (u.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE bp.IsSalesRep='Y' AND AD_User_ID=?";
		int no = DB.getSQLValue(null, sql, AD_User_ID);
		return no == AD_User_ID;
	} // isSalesRep

	/** Cache */
	static private CCache<Integer, MUser> s_cache = new CCache<Integer, MUser>(
			"AD_User", 30, 60);
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MUser.class);

	/**************************************************************************
	 * Default Constructor
	 *
	 * @param ctx
	 *            context
	 * @param AD_User_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MUser(Properties ctx, int AD_User_ID, String trxName) {
		super(ctx, AD_User_ID, trxName); // 0 is also System
		if (AD_User_ID == 0) {
			setIsFullBPAccess(true);
			setNotificationType(NOTIFICATIONTYPE_EMail);
		}
	} // MUser

	/**
	 * Parent Constructor
	 *
	 * @param partner
	 *            partner
	 */
	public MUser(X_C_BPartner partner) {
		this(partner.getCtx(), 0, partner.get_TrxName());
		setClientOrg(partner);
		setC_BPartner_ID(partner.getC_BPartner_ID());
		setName(partner.getName());
	} // MUser

	/**
	 * Load Constructor
	 *
	 * @param ctx
	 *            context
	 * @param rs
	 *            current row of result set to be loaded
	 * @param trxName
	 *            transaction
	 */
	public MUser(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MUser

	/** Roles of User with Org */
	private MRole[] m_roles = null;
	/** Roles of User with Org */
	private int m_rolesAD_Org_ID = -1;
	/** Is Administrator */
	private Boolean m_isAdministrator = null;
	/** User Access Rights */
	private X_AD_UserBPAccess[] m_bpAccess = null;

	/**
	 * Add to Description
	 *
	 * @param description
	 *            description to be added
	 */
	public void addDescription(String description) {
		if (description == null || description.length() == 0)
			return;
		String descr = getDescription();
		if (descr == null || descr.length() == 0)
			setDescription(description);
		else
			setDescription(descr + " - " + description);
	} // addDescription

	/**
	 * String Representation
	 *
	 * @return Info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("MUser[").append(get_ID()).append(
				",Name=").append(getName()).append(",EMailUserID=").append(
				getEMailUser()).append("]");
		return sb.toString();
	} // toString

	/**
	 * Is it an Online Access User
	 *
	 * @return true if it has an email and password
	 */
	public boolean isOnline() {
		if (getEMail() == null || getPassword() == null)
			return false;
		return true;
	} // isOnline

	/**
	 * Convert EMail
	 *
	 * @return Valid Internet Address
	 */
	public InternetAddress getInternetAddress() {
		String email = getEMail();
		if (email == null || email.length() == 0)
			return null;
		try {
			InternetAddress ia = new InternetAddress(email, true);
			if (ia != null)
				ia.validate(); // throws AddressException
			return ia;
		} catch (AddressException ex) {
			log.warning(email + " - " + ex.getLocalizedMessage());
		}
		return null;
	} // getInternetAddress

	/**
	 * Validate Email (does not work). Check DNS MX record
	 *
	 * @param ia
	 *            email
	 * @return error message or ""
	 */
	@SuppressWarnings("unused")
	private String validateEmail(InternetAddress ia) {
		if (ia == null)
			return "NoEmail";
		if (true)
			return null;

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.dns.DnsContextFactory");
		// env.put(Context.PROVIDER_URL, "dns://admin.compiere.org");
		try {
			DirContext ctx = new InitialDirContext(env);
			// Attributes atts = ctx.getAttributes("admin");
			Attributes atts = ctx.getAttributes("dns://admin.compiere.org",
					new String[] { "MX" });
			NamingEnumeration<?> en = atts.getAll();
			// NamingEnumeration en = ctx.list("compiere.org");
			while (en.hasMore()) {
				System.out.println(en.next());
			}
			/**/
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage());
			return e.getLocalizedMessage();
		}
		return null;
	} // validateEmail

	/**
	 * Is the email valid
	 *
	 * @return return true if email is valid (artificial check)
	 */
	public boolean isEMailValid() {
		return validateEmail(getInternetAddress()) == null;
	} // isEMailValid

	/**
	 * Could we send an email
	 *
	 * @return true if EMail Uwer/PW exists
	 */
	public boolean isCanSendEMail() {
		String s = getEMailUser();
		if (s == null || s.length() == 0)
			return false;
		s = getEMailUserPW();
		return s != null && s.length() > 0;
	} // isCanSendEMail

	/**
	 * Get EMail Validation Code
	 *
	 * @return code
	 */
	public String getEMailVerifyCode() {
		long code = getAD_User_ID() + getName().hashCode();
		return "C" + String.valueOf(Math.abs(code)) + "C";
	} // getEMailValidationCode

	/**
	 * Check & Set EMail Validation Code.
	 *
	 * @param code
	 *            code
	 * @param info
	 *            info
	 * @return true if valid
	 */
	public boolean setEMailVerifyCode(String code, String info) {
		boolean ok = code != null && code.equals(getEMailVerifyCode());
		if (ok)
			setEMailVerifyDate(Env.getCurrentDate());
		else
			setEMailVerifyDate(null);
		setEMailVerify(info);
		return ok;
	} // setEMailValidationCode

	/**
	 * Is EMail Verified by response
	 *
	 * @return true if verified
	 */
	public boolean isEMailVerified() {
		// UPDATE AD_User SET EMailVerifyDate=SysDate, EMailVerify='Direct'
		// WHERE AD_User_ID=1
		return getEMailVerifyDate() != null && getEMailVerify() != null
				&& getEMailVerify().length() > 0;
	} // isEMailVerified

	/**
	 * Get Notification via EMail
	 *
	 * @return true if email
	 */
	public boolean isNotificationEMail() {
		String s = getNotificationType();
		return s == null || NOTIFICATIONTYPE_EMail.equals(s);
	} // isNotificationEMail

	/**
	 * Get Notification via Note
	 *
	 * @return true if note
	 */
	public boolean isNotificationNote() {
		String s = getNotificationType();
		return s != null && NOTIFICATIONTYPE_Notice.equals(s);
	} // isNotificationNote

	/**************************************************************************
	 * Get User Roles for Org
	 *
	 * @param AD_Org_ID
	 *            org
	 * @return array of roles
	 */
	public MRole[] getRoles(int AD_Org_ID) {
		if (m_roles != null && m_rolesAD_Org_ID == AD_Org_ID)
			return m_roles;

		ArrayList<MRole> list = new ArrayList<MRole>();
		String sql = "SELECT * FROM AD_Role r "
				+ "WHERE r.IsActive='Y'"
				+ " AND EXISTS (SELECT * FROM AD_Role_OrgAccess ro"
				+ " WHERE r.AD_Role_ID=ro.AD_Role_ID AND ro.IsActive='Y' AND ro.AD_Org_ID=?)"
				+ " AND EXISTS (SELECT * FROM AD_User_Roles ur"
				+ " WHERE r.AD_Role_ID=ur.AD_Role_ID AND ur.IsActive='Y' AND ur.AD_User_ID=?) "
				+ "ORDER BY AD_Role_ID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;// Expert
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, AD_Org_ID);
			pstmt.setInt(2, getAD_User_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MRole(getCtx(), rs, get_TrxName()));

		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		m_rolesAD_Org_ID = AD_Org_ID;
		m_roles = new MRole[list.size()];
		list.toArray(m_roles);
		return m_roles;
	} // getRoles

	/**
	 * Is User an Administrator?
	 *
	 * @return true id Admin
	 */
	public boolean isAdministrator() {
		if (m_isAdministrator == null) {
			m_isAdministrator = Boolean.FALSE;
			MRole[] roles = getRoles(0);
			for (int i = 0; i < roles.length; i++) {
				if (roles[i].getAD_Role_ID() == 0) {
					m_isAdministrator = Boolean.TRUE;
					break;
				}
			}
		}
		return m_isAdministrator.booleanValue();
	} // isAdministrator

	/**
	 * Has the user Access to BP info and resources
	 *
	 * @param BPAccessType
	 *            access type
	 * @param params
	 *            opt parameter
	 * @return true if access
	 */
	public boolean hasBPAccess(String BPAccessType, Object[] params) {
		if (isFullBPAccess())
			return true;
		getBPAccess(false);
		for (int i = 0; i < m_bpAccess.length; i++) {
			if (m_bpAccess[i].getBPAccessType().equals(BPAccessType)) {
				return true;
			}
		}
		return false;
	} // hasBPAccess

	/**
	 * Get active BP Access records
	 *
	 * @param requery
	 *            requery
	 * @return access list
	 */
	public X_AD_UserBPAccess[] getBPAccess(boolean requery) {
		if (m_bpAccess != null && !requery)
			return m_bpAccess;
		String sql = "SELECT * FROM AD_UserBPAccess WHERE AD_User_ID=? AND IsActive='Y'";
		ArrayList<X_AD_UserBPAccess> list = new ArrayList<X_AD_UserBPAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;// Expert
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getAD_User_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new X_AD_UserBPAccess(getCtx(), rs, null));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		m_bpAccess = new X_AD_UserBPAccess[list.size()];
		list.toArray(m_bpAccess);
		return m_bpAccess;
	} // getBPAccess

	/**
	 * Before Save
	 *
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave(boolean newRecord) {

		boolean retVal = true;

		if(!newRecord && //
			(getAD_User_ID() == 100 || getAD_User_ID() == 0) //
			  && Env.getAD_Client_ID(getCtx()) > 100 //
			  && Env.getAD_User_ID(getCtx()) != getAD_User_ID()) {
			// can not update SuperUser or System from a client > 100
			retVal = false;
		} else {
			// Si es nuevo asignamos una fecha de expiracion
			if (newRecord) {
				MEXMEConfigSeguridad conf =
						MEXMEConfigSeguridad.get(getCtx(), null);
				int dias = 60;
				if (conf != null) {
					dias = conf.getDias();

				}
				Calendar cal = new GregorianCalendar();
				cal.add(Calendar.DATE, dias);
				setDateTo(new Timestamp(cal.getTimeInMillis()));
			}
			// New Address invalidates verification
			if (!newRecord && is_ValueChanged("EMail")) {
				setEMailVerifyDate(null);
			}

			boolean mustCheckPassword = true;

			if(getPassword() == null) {
				PasswordHandler passwordChecker = new PasswordHandler(getCtx());

				setPassword(UtilsDbPort.encrypt(passwordChecker.generatePassword()));
				setFechaCambiaPwd(Env.getCurrentDate());

				mustCheckPassword = false;
			}


			if (is_ValueChanged("Password") && mustCheckPassword) {

				// validate password according to the rules
				PasswordHandler passwordChecker = new PasswordHandler(getCtx());
				String msg = passwordChecker.checkPassword(getPassword(), getName());

				if(StringUtils.isEmpty(msg)) {
					setPassword(CryptoUtils.encrypt(getPassword()));
					setFechaCambiaPwd(Env.getCurrentDate());
				} else {
					log.saveError("Error", msg);

					retVal = false;
				}
			}


			setName(getName().toUpperCase());
		}

		return retVal;
	} // beforeSave

	/**
	 * Obtiene el usuario segun su nombre
	 *
	 * @param ctx
	 *            Contexto
	 * @param userName
	 *            Nombre de Usuario
	 * @param trxName
	 *            Transaccion
	 * @return Usuario o null
	 */
	public static MUser getUserName(Properties ctx, String userName, String trxName) {
		return getUserName(ctx, userName, true, trxName);
//		return new Query(ctx, Table_Name, " name=? ", trxName)//
//			.setParameters(userName.toUpperCase())//
//			.setOnlyActiveRecords(true)//
//			.setOrderBy(" created desc ").first();

//		String sql = "";
//		MUser usuario = null;
//
//		if (userName != null)
//			sql = "SELECT * FROM AD_User WHERE name = '"
//					+ userName.toUpperCase() + "' AND isActive = 'Y' order by created desc";
//
//		PreparedStatement pstmt = null;// Expert
//		ResultSet rs = null;// Expert
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			rs = pstmt.executeQuery();
//			if (rs.next())
//				usuario = new MUser(ctx, rs, trxName);
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, sql, e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return usuario;
	} // getNameOfUser

	/**
	 * Obtiene el usuario segun su nombre
	 *
	 * @param ctx
	 *            Contexto
	 * @param userName
	 *            Nombre de Usuario
	 * @param trxName
	 *            Transaccion
	 * @return Usuario o null
	 */
	public static MUser getUserName(Properties ctx, String userName, boolean onlyActive, String trxName) {

		return new Query(ctx, Table_Name, " name=? ", trxName)//
			.setParameters(userName.toUpperCase())//
			.setOnlyActiveRecords(onlyActive)//
			.setOrderBy(" created desc ").first();
	} // getNameOfUser


	/**
	 * Comprueba si existe o no el usuario con el password provisional
	 *
	 * @param userName
	 *            nombre de Usuario
	 * @param passwordprov
	 *            Password provisional
	 * @return
	 */
	public static boolean getUserByNamePassProv(String userName, String passwordprov) {
		return DB.getSQLValue(null, // trx
			"SELECT AD_User_ID FROM AD_User WHERE name=? AND passwordProv=?", // sql
			userName.toUpperCase(), passwordprov // params
			) > 0;// true if ID > 0
	} // getNameOfUser

	/**
	 * Obtener si existe o no el usuario y esta inactivado por seguridad
	 *
	 * @param userName
	 *            Usuario a buscar
	 * @return Si existe o no
	 */
	public static boolean getInactiveUser(String userName) {
		return DB.getSQLValue(null, "SELECT AD_User_ID FROM AD_User WHERE name=? AND passwordProv is not NULL", userName.toUpperCase()) > 0;
	}

	/**
	 * Reactiva al usuario asignando el nuevo password
	 *
	 * @param userName
	 *            nombre de usuario
	 * @param newPassword
	 *            Nuevo password
	 * @return
	 */
	public static boolean reactivateUser(String userName, String newPassword) {
		return DB.executeUpdateEx(//
			"update ad_user set password=?, passwordprov=null, isactive='Y' where name=?", // sql
			new Object[] { newPassword, userName.toUpperCase() }, // params
			null // trx
			) > 0;// true if number of record > 0
	}

	public static MUser get(Properties ctx, int ID, String trxName) {
		return get(ctx, ID);
	}

	/**
	 * Busca el usuario apartir del ID (Método reutilizado en caso de que el
	 * usuario id sea 0)
	 *
	 * @param userName
	 * @return
	 */
	public static MUser getUserID(Properties ctx, int userID, String trxName) {
		return new Query(ctx, Table_Name, " AD_User_ID=? ", trxName)//
		.setParameters(userID)//
		.first();
	} // getnameofuser

	// expert

	/**
	 * Test
	 *
	 * @param args
	 *            ignored
	 *
	 *            public static void main (String[] args) { try {
	 *            validateEmail(new InternetAddress("jjanke@compiere.org")); }
	 *            catch (Exception e) { e.printStackTrace(); }
	 *
	 *            // org.compiere.Compiere.startupClient(); //
	 *            System.out.println ( MUser.get(Env.getCtx(), "SuperUser",
	 *            "22") ); } // main /*
	 */


	/**
	 * Verifica Autoirzacion mediante un usuario, un importe y un tipo de
	 * documento
	 *
	 * @param user1
	 *            , importe, docType
	 * @return true,false
	 * @deprecated
	 */
	public boolean verificarAutorizacion(String user1, int importe, int docType) {

		boolean user = false;

		String sql = "select ad_user_id, importemax, importemin, c_doctype_id from exme_useraut";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				if (rs.getString(1).equals(user1) && rs.getInt(2) <= importe
						&& rs.getInt(3) <= importe && rs.getInt(4) == docType) {

					user = true;
				}
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "getLines", e);
		} finally {
			DB.close(rs, pstmt);
		}
		//System.out.println(user);
		return user;
	}

	/**
	 * Envio de correos a usuario
	 *
	 * @param ctx
	 *            Contexto
	 * @param subject
	 *            Asunto
	 * @param message
	 *            Mensaje
	 * @return Exitoso o No
	 */
	public boolean sendMail(String subject, String message) {
		boolean exitoso = false;
		if (getEMail() != null && getEMail().trim().length() > 0) {
			exitoso = UtilsDbPort.sendMail(getCtx(), message, true, subject,
					getEMail());
		}
		return exitoso;
	}

	public boolean sendMailNoClient(String subject, String message) {
		boolean exitoso = false;
		if (getEMail() != null && getEMail().trim().length() > 0) {
			exitoso = UtilsDbPort.sendMailNoClient(getCtx(), getAD_Client_ID(),
					message, true, subject, getEMail());
		}
		return exitoso;
	}

	/**
	 * Verifica si el usuario esta o no vigente
	 *
	 * @return Vigente o No
	 */
	public boolean isVigente() {
		boolean retValue = true;
		Date now = new Date();
		if (getDateTo() != null) {
			if (now.after(getDateTo())) {
				retValue = false;
			}
		}
		return retValue;
	}

	/**
	 * rsolorzano 28/10/2009 Regresa el usuario tipo enfermera o medico
	 *
	 * @param ctx
	 *            Propiedades
	 * @param trxName
	 *            Nombre de la transaccion
	 *
	 *
	public static MUser getUsuariosCateter(Properties ctx, String clave,
			String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(100);
		MUser user = null;

		try {

			sql.append("SELECT * FROM AD_USER ");
			sql.append(" WHERE UPPER(NAME) = UPPER(?) ");
			sql
					.append(" AND  ( AD_USER_ID IN (SELECT AD_USER_ID FROM EXME_ENFERMERIA WHERE ISACTIVE = 'Y') ");
			sql
					.append(" OR AD_USER_ID IN (SELECT AD_USER_ID FROM EXME_MEDICO WHERE ISACTIVE = 'Y') )");
			sql.append(" AND ISACTIVE = 'Y' ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, clave);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new MUser(ctx, rs, null);
			}
			pstmt.close();
			rs.close();

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}

		return user;
	}*/

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		/**
		 * Si es nuevo usuario enviamos la informacion de la cuenta
		 */
		if (success) {
			if (newRecord) {
				sendAccountInfo();
			} else {
				if (is_ValueChanged(COLUMNNAME_Password)) {
					try {
						ChatConnection chatConnection = new ChatConnection() {

							@Override
							public List<ChatMessage> getHistory(String arg0, String arg1) {

								return new ArrayList<ChatMessage>();
							}

							@Override
							public void storeMessage(String arg0, String arg1, String arg2) {

							}

						};

						chatConnection.connect();
						chatConnection.login(getName(), (String) get_ValueOld(COLUMNNAME_Password));

						try {
							new AccountManager(chatConnection.getConnection()).changePassword(getPassword());
						} catch (XMPPException e) {
							s_log.log(Level.SEVERE, null, e);
						}

						chatConnection.getConnection().disconnect();
					} catch (Exception ex) {
						s_log.log(Level.SEVERE, null, ex);
					}
				}
			}
		}
		return success;
	}

	/**
	 * Envia la informacion de la cuenta
	 *
	 * @return
	 */
	public boolean sendAccountInfo() {
		boolean retValue = false;
		try {
			String mensaje = Utilerias.getAppMsg(getCtx(), "msj.bodyCreation");
			mensaje = StringUtils.replace(mensaje, "{2}", Env.getAppUrl(getCtx()));
			mensaje = StringUtils.replace(mensaje, "{0}", getName());
			mensaje =
					StringUtils.replace(
							mensaje,
							"{4}",
							CryptoUtils.decrypt(getPassword())
					);
			String fecha = new String();
			if (getDateTo() != null) {
				fecha = Constantes.getSDFDateTime(getCtx()).format(getDateTo());
			}
			mensaje = StringUtils.replace(mensaje, "{3}", fecha);

			retValue = sendMail(Msg.translate(getCtx(), "msj.creacionCuenta"), mensaje);
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "Activacion cuenta", ex.getMessage());
		}
		return retValue;
	}

	/**
	 * Bloquea al usuario
	 *
	 * @param passProv
	 */
	public void blockUser(String passProv) {
		if (!isAdministrator()) {
			setPasswordProv(passProv);
		}
		setIsActive(false);
	}

	/**
	 * Envia mail de activacion
	 *
	 * @return
	 */
	public boolean sendActivationMail() {
		boolean exito = false;
		if (!isAdministrator()) {
			try {
				String mensaje = Msg.translate(getCtx(), "msj.bodyActivation");
				CConnection conn = CConnection.get();
				String puerto = Utilerias.getPropertiesFromEnv("COMPIERE_WEB_PORT");
				String liga = Env.getContext(getCtx(), Env.REQUEST_SCHEMA) + "://" + conn.getAppsHost() + ":" + puerto + "/eCareSoftWeb/activacion.do?param=init&userName=" + getName();
				mensaje = StringUtils.replace(mensaje, "{0}", liga);
				mensaje = StringUtils.replace(mensaje, "{1}", getPasswordProv());
				liga = Env.getContext(getCtx(), Env.REQUEST_SCHEMA) + "://" + conn.getAppsHost() + ":" + puerto + "/eCareSoftWeb";
				mensaje = StringUtils.replace(mensaje, "{2}", liga);
				String subject = Msg.translate(getCtx(), "msj.subjectActivation");
				exito = sendMail(subject, mensaje);
			} catch (Exception ex) {
				s_log.log(Level.SEVERE, "Activacion cuenta", ex.getMessage());
			}
		} else {
			exito = true;
		}
		return exito;
	}

	/**
	 *
	 * @return
	 */
	public boolean sendMailRecuperarPSSWRD(String schema) {
		boolean exito = false;
		if (!isAdministrator()) {
			try {
				String mensaje = Utilerias.getMsg(getCtx(), "msj.bodyActivation");
				String liga = Env.getAppUrl(getCtx()) + "/recover.zul?userId=" + getAD_User_ID();
				mensaje = StringUtils.replace(mensaje, "{0}", liga);
				mensaje = StringUtils.replace(mensaje, "{1}", getPasswordProv());
				mensaje = StringUtils.replace(mensaje, "{2}", Env.getAppUrl(getCtx()));
				String subject = Utilerias.getMsg(getCtx(), "msj.subjectActivation");
				exito = sendMailNoClient(subject, mensaje);
			} catch (Exception ex) {
				s_log.log(Level.SEVERE, "Activacion cuenta", ex.getMessage());
			}
		} else {
			exito = true;
		}
		return exito;
	}

	/**
	 *
	 * @param ctx
	 * @param AD_User_ID
	 * @return
	 */
	public static boolean isPatient(Properties ctx, int AD_User_ID) {
		return DB.getSQLValue(null, // trx
			"SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE AD_User_ID=?",// sql
			AD_User_ID) > 0;// true if > 0
	}

	/**
	 *
	 * @return
	 */
	public boolean isInEmergencyMode() {
		boolean inEmergencyMode = MEXMEEmergencia.isEmergencyState(getCtx())//
			&& MEXMEEmergenciaUsuario.isEmergencyUser(getCtx(), getAD_User_ID(), get_TrxName()) //
			&& MEXMEEmergenciaRol.isEmergencyRol(getCtx(), Env.getAD_Role_ID(getCtx()), get_TrxName());
		return inEmergencyMode;
	}

	/**
	 * rsolorzano 09/03/2010 obtiene el sistema de medicion contrario al del
	 * usuario
	 *
	 * @param ctx
	 * @return
	 */
	public static String getSistMedicionBD(Properties ctx) {
		MUser user = new MUser(ctx, Env.getAD_User_ID(ctx), null);
		String sistemaMedicionTo = null;
		if (StringUtils.isNotBlank(user.getSistemaMedicion())) {
			sistemaMedicionTo = MUser.SISTEMAMEDICION_MetricSystem;
		}
		return sistemaMedicionTo;
	}

	/**
	 * rsolorzano 09/03/2010 obtiene el sistema de medicion del usuario
	 *
	 * @param ctx
	 * @return
	 */
	public static String getSistMedicionUsuario(Properties ctx) {
		return new MUser(ctx, Env.getAD_User_ID(ctx), null).getSistemaMedicion();
	}

	/**
	 * rsolorzano 10/03/2010 revisamos el sistema de medicion de preferencia
	 * del usuario en caso que sea necesaria una conversion
	 *
	 * @param ctx
	 * @return
	 */
	public static boolean convertirUnidades(Properties ctx) {
		return MUser.SISTEMAMEDICION_EnglishSystem.equals(MUser.getSistMedicionUsuario(ctx));
	}

	public static MUser getFromName(Properties ctx, String userName) {
		return new Query(ctx, Table_Name, "name=?", null)//
			.setParameters(userName.toUpperCase())//
			.first();
	}

	public static String getFromID(String column, int userID) {
		String sql = "SELECT " + column + " as retVal FROM AD_User WHERE AD_User_ID=?";
		return DB.getSQLValueString(null, sql, userID);
	}

	/**
	 *
	 * @param saTrxName el nombre de la transaccion actual.
	 * @param iaAd_Client_id el id del cliente
	 * @return un entero que representa el AD_User_ID encontrado.
	 * @author jcantu Creado el 19 de Junio del 2012.
	 */
	public static int getAdminUserId(String saTrxName, int iaAd_Client_id) {
		String sql = "Select AD_User_ID from AD_User where AD_Client_id = ? and upper(name) like '%ADMIN%'";
		return DB.getSQLValue(saTrxName, sql, iaAd_Client_id);

	}

	/**
	 * User's name.<br>
	 * If the user is a doctor then returns Doctor's name,<br>
	 * in case the user is a nurse then it will be the nurse's name
	 * @param ctx
	 * @param userId
	 * @return
	 */
	public static String getUserName(Properties ctx, int userId) {
		String value;
		if (userId < 0) {
			value = StringUtils.EMPTY;
		} else {
			// if is doctor
			value = MEXMEMedico.getNameByUserId(ctx, userId);
			if (value == null) {
				// if is nurse
				value = MEXMEEnfermeria.getNameByUserId(ctx, userId);
			}
			// else
			if (value == null) {
				value = DB.getSQLValueString(null, "SELECT COALESCE(Description,Name) FROM AD_User WHERE AD_User_ID=?", userId);
			}
		}
		return value;
	}

	public static String getUserPIN(int userId) {
		final StringBuilder stCol = new StringBuilder();
		stCol.append("COALESCE(");
		stCol.append(I_AD_User.COLUMNNAME_UserPIN).append(",");
		stCol.append(I_AD_User.COLUMNNAME_Name);
		stCol.append(")");
		return MUser.getFromID(stCol.toString(), userId);
	}

	/**
	 * Regresa query para una busqueda de usuarios en la misma organización
	 *
	 * @param ctx
	 * @return Query de usuarios
	 */
	public static StringBuilder getUserOrgSearch(Properties ctx, boolean addWhere) {
		StringBuilder str = new StringBuilder();

		if (addWhere) {
			str.append(" Where ");
		}

		str.append(" (AD_USER_ID IN ( SELECT AD_USER_ID FROM AD_USER_ORGACCESS WHERE AD_CLIENT_ID = ");
		str.append(Env.getAD_Client_ID(ctx)).append(") OR ");
		str.append("AD_USER_ID IN ( SELECT AD_USER_ID FROM AD_USER_ROLES WHERE ");
		str.append("AD_ROLE_ID IN ( SELECT AD_ROLE_ID FROM AD_Role_OrgAccess WHERE AD_CLIENT_ID = ");
		str.append(Env.getAD_Client_ID(ctx));
		str.append(" AND AD_Org_ID = ").append(Env.getAD_Org_ID(ctx));
		str.append(")))");

		return str;
	}

} // MUser
