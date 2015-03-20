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
package org.compiere.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;
import org.compiere.Compiere;
import org.compiere.db.CConnection;
import org.compiere.model.MClient;
import org.compiere.model.MEXMEI18N;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MLookupCache;
import org.compiere.model.MRole;
import org.compiere.model.MSession;
import org.compiere.model.PO;
import org.compiere.swing.CFrame;
import org.joda.time.DateTimeZone;

/**
 *  System Environment and static variables
 *
 *  @author     Jorg Janke
 *  @version    $Id: Env.java,v 1.9 2006/08/23 01:35:36 mrojas Exp $
 */
public final class Env {
	/** Logging */
	private static CLogger s_log = CLogger.getCLogger(Env.class);

	private static ContextProvider contextProvider = new DefaultContextProvider();
	
	public static void setContextProvider(ContextProvider provider)
	{
		contextProvider = provider;
		getCtx().put(LANGUAGE, Language.getBaseAD_Language());
	}
	
	/**
	 * Exit System
	 * @param status System exit status (usually 0 for no error)
	 */
	public static void exitEnv(int status) {
		// End Session
		MSession session = MSession.get(Env.getCtx(), false); // finish
		if (session != null)
			session.logout();

		
		reset(true); // final cache reset
		s_log.info("");
		//
		CLogMgt.shutdown();
		//
		if (Ini.isClient())
			System.exit(status);
	} // close

	/**
	 * Logout from the system
	 */
	public static void logout()
	{
		//	End Session
		MSession session = MSession.get(Env.getCtx(), false);	//	finish
		if (session != null)
			session.logout();
		//
		reset(true);	// final cache reset
		//
		
		//CConnection.get().setAppServerCredential(null, null);
	}
	
	/**
	 * 	Reset Cache
	 * 	@param finalCall everything otherwise login data remains
	 */
	public static void reset (boolean finalCall)
	{
		s_log.info("finalCall=" + finalCall);
		if (Ini.isClient())
		{
			closeWindows();
			
			//	Dismantle windows
			/**
			for (int i = 0; i < s_windows.size(); i++)
			{
				Container win = (Container)s_windows.get(i);
				if (win.getClass().getName().endsWith("AMenu")) // Null pointer
					;
				else if (win instanceof Window)
					((Window)win).dispose();
				else
					win.removeAll();
			}
			**/
			//bug [ 1574630 ]
			if (s_windows.size() > 0) {
				if (!finalCall) {
					Container c = s_windows.get(0);
					s_windows.clear();
					createWindowNo(c);
				} else {
					s_windows.clear();
				}
			}
		}

		//	Clear all Context
		if (finalCall)
			getCtx().clear();
		else	//	clear window context only
		{
			Object[] keys = getCtx().keySet().toArray();
			for (int i = 0; i < keys.length; i++)
			{
				String tag = keys[i].toString();
				if (Character.isDigit(tag.charAt(0)))
					getCtx().remove(keys[i]);
			}
		}

		//	Cache
		CacheMgt.get().reset();
		if (Ini.isClient())
			DB.closeTarget();
		//	Reset Role Access
		if (!finalCall)
		{
			if (Ini.isClient())
				DB.setDBTarget(CConnection.get());
			MRole defaultRole = MRole.getDefault(getCtx(), false);
			if (defaultRole != null)
				defaultRole.loadAccess(true);	//	Reload
		}
	}	//	resetAll


	/**************************************************************************
	 *  Application Context
	 */
	//private static Properties   s_ctx = new Properties();
	/** WindowNo for Find           */
	public static final int     WINDOW_FIND = 1110;
	/** WinowNo for MLookup         */
	public static final int	    WINDOW_MLOOKUP = 1111;
	/** WindowNo for PrintCustomize */
	public static final int     WINDOW_CUSTOMIZE = 1112;
	/** WindowNo for PrintCustomize */
	public static final int     WINDOW_INFO = 1113;

	/** Tab for Info                */
	public static final int     TAB_INFO = 1113;

	/**
	 *  Get Context
	 *  @return Properties
	 */
	public static final Properties getCtx()
	{
		//return s_ctx;
		//expert : mrojas : el contexto esta en el contextProvider
		return contextProvider.getContext();
	} // getCtx

	/**
	 * Set Context
	 * @param ctx context
	 */
	public static void setCtx(Properties ctx) {
		if (ctx == null)
			throw new IllegalArgumentException("Require Context");
		getCtx().clear();
		//s_ctx = ctx;
		//expert : mrojas : el contexto esta en el contextProvider
		getCtx().putAll(ctx);
	} // setCtx

	/**
	 *	Set Global Context to Value
	 *  @param ctx context
	 *  @param context context key
	 *  @param value context value
	 */
	public static void setContext(Properties ctx, String context, String value) {
		if (ctx == null || context == null)
			return;
		s_log.finer("Context " + context + "==" + value);
		//
		if (value == null || value.length() == 0)
			ctx.remove(context);
		else
			ctx.setProperty(context, value);
	} // setContext

	/**
	 *	Set Global Context to Value
	 *  @param ctx context
	 *  @param context context key
	 *  @param value context value
	 */
	public static void setContext(Properties ctx, String context, Timestamp value) {
		if (ctx == null || context == null)
			return;
		if (value == null) {
			ctx.remove(context);
			s_log.finer("Context " + context + "==" + value);
		} else { // JDBC Format 2005-05-09 00:00:00.0
			String stringValue = value.toString();
			// Chop off .0
			//stringValue = stringValue.substring(0, stringValue.length() - 2); //EXPERT : mrojas : por integracion ZK
			stringValue = stringValue.substring(0, stringValue.indexOf("."));
			ctx.setProperty(context, stringValue);
			s_log.finer("Context " + context + "==" + stringValue);
		}
	} // setContext

	/**
	 *	Set Global Context to (int) Value
	 *  @param ctx context
	 *  @param context context key
	 *  @param value context value
	 */
	public static void setContext(Properties ctx, String context, int value) {
		if (ctx == null || context == null)
			return;
		s_log.finer("Context " + context + "==" + value);
		//
		ctx.setProperty(context, String.valueOf(value));
	} // setContext

	/**
	 * Set Global Context to Y/N Value
	 * @param ctx context
	 * @param context context key
	 * @param value context value
	 */
	public static void setContext(Properties ctx, String context, boolean value) {
		setContext(ctx, context, value ? "Y" : "N");
	} // setContext

	/**
	 * Set Context for Window to Value
	 * @param ctx context
	 * @param WindowNo window no
	 * @param context context key
	 * @param value context value
	 */
	public static void setContext(Properties ctx, int WindowNo, String context, String value) {
		if (ctx == null || context == null)
			return;
		if (WindowNo != WINDOW_FIND && WindowNo != WINDOW_MLOOKUP)
			s_log.finer("Context(" + WindowNo + ") " + context + "==" + value);
		//
		if (value == null || value.equals(""))
			ctx.remove(WindowNo + "|" + context);
		else
			ctx.setProperty(WindowNo + "|" + context, value);
	} // setContext

	/**
	 * Set Context for Window to Value
	 * @param ctx context
	 * @param WindowNo window no
	 * @param context context key
	 * @param value context value
	 */
	public static void setContext(Properties ctx, int WindowNo, String context, Timestamp value) {
		if (ctx == null || context == null)
			return;
		// boolean logit = WindowNo != WINDOW_FIND && WindowNo != WINDOW_MLOOKUP;
		if (value == null) {
			ctx.remove(WindowNo + "|" + context);
			s_log.finer("Context(" + WindowNo + ") " + context + "==" + value);
		} else { // JDBC Format 2005-05-09 00:00:00.0
			String stringValue = value.toString();
			// Chop off .0
			//stringValue = stringValue.substring(0, stringValue.length() - 2);	//EXPERT : mrojas : por integracion ZK
			stringValue = stringValue.substring(0, stringValue.indexOf("."));
			ctx.setProperty(WindowNo + "|" + context, stringValue);
			s_log.finer("Context(" + WindowNo + ") " + context + "==" + stringValue);
		}
	} // setContext

	/**
	 * Set Context for Window to int Value
	 * @param ctx context
	 * @param WindowNo window no
	 * @param context context key
	 * @param value context value
	 */
	public static void setContext(Properties ctx, int WindowNo, String context, int value) {
		if (ctx == null || context == null)
			return;
		if (WindowNo != WINDOW_FIND && WindowNo != WINDOW_MLOOKUP)
			s_log.finer("Context(" + WindowNo + ") " + context + "==" + value);
		//
		ctx.setProperty(WindowNo + "|" + context, String.valueOf(value));
	} // setContext

	/**
	 * Set Context for Window to Y/N Value
	 * @param ctx context
	 * @param WindowNo window no
	 * @param context context key
	 * @param value context value
	 */
	public static void setContext(Properties ctx, int WindowNo, String context, boolean value) {
		setContext(ctx, WindowNo, context, value ? "Y" : "N");
	} // setContext

	/**
	 * Set Context for Window & Tab to Value
	 * @param ctx context
	 * @param WindowNo window no
	 * @param TabNo tab no
	 * @param context context key
	 * @param value context value
	 * */
	public static void setContext(Properties ctx, int WindowNo, int TabNo, String context, String value) {
		if (ctx == null || context == null)
			return;
		if (WindowNo != WINDOW_FIND && WindowNo != WINDOW_MLOOKUP)
			s_log.finest("Context(" + WindowNo + "," + TabNo + ") " + context + "==" + value);
		//
		if (value == null || value.equals(""))
			ctx.remove(WindowNo + "|" + TabNo + "|" + context);
		else
			ctx.setProperty(WindowNo + "|" + TabNo + "|" + context, value);
	} // setContext

	/**
	 * Set Auto Commit
	 * @param ctx context
	 * @param autoCommit auto commit (save)
	 */
	public static void setAutoCommit(Properties ctx, boolean autoCommit) {
		if (ctx == null)
			return;
		ctx.setProperty("AutoCommit", autoCommit ? "Y" : "N");
	} // setAutoCommit

	/**
	 * Set Auto Commit for Window
	 * 
	 * @param ctx context
	 * @param WindowNo window no
	 * @param autoCommit auto commit (save)
	 */
	public static void setAutoCommit(Properties ctx, int WindowNo, boolean autoCommit) {
		if (ctx == null)
			return;
		ctx.setProperty(WindowNo + "|AutoCommit", autoCommit ? "Y" : "N");
	} // setAutoCommit

	/**
	 * Set Auto New Record
	 * @param ctx context
	 * @param autoNew auto new record
	 */
	public static void setAutoNew(Properties ctx, boolean autoNew) {
		if (ctx == null)
			return;
		ctx.setProperty("AutoNew", autoNew ? "Y" : "N");
	} // setAutoNew

	/**
	 * Set Auto New Record for Window
	 * @param ctx context
	 * @param WindowNo window no
	 * @param autoNew auto new record
	 */
	public static void setAutoNew(Properties ctx, int WindowNo, boolean autoNew) {
		if (ctx == null)
			return;
		ctx.setProperty(WindowNo + "|AutoNew", autoNew ? "Y" : "N");
	} // setAutoNew

	/**
	 * Set SO Trx
	 * @param ctx context
	 * @param isSOTrx SO Context
	 */
	public static void setSOTrx(Properties ctx, boolean isSOTrx) {
		if (ctx == null)
			return;
		ctx.setProperty("IsSOTrx", isSOTrx ? "Y" : "N");
	} // setSOTrx

	/**
	 * Get global Value of Context
	 * @param ctx context
	 * @param context context key
	 * @return value or ""
	 */
	public static String getContext(Properties ctx, String context) {
		if (ctx == null || context == null)
			throw new IllegalArgumentException("Require Context");
		String retValue;
		if (ctx.get(context) != null && ctx.get(context) instanceof Integer) {
			retValue = String.valueOf(ctx.get(context));
		} else {
			retValue = ctx.getProperty(context, "");
		}
		return retValue;
	} // getContext

	/**
	 * Get Value of Context for Window.
	 * if not found global context if available and enabled
	 * @param ctx context
	 * @param WindowNo window
	 * @param context context key
	 * @param onlyWindow if true, no defaults are used unless explicitly asked for
	 * @return value or ""
	 */
	public static String getContext(Properties ctx, int WindowNo, String context, boolean onlyWindow) {
		if (ctx == null)
			throw new IllegalArgumentException("No Ctx");
		if (context == null)
			throw new IllegalArgumentException("Require Context");
		String s = ctx.getProperty(WindowNo + "|" + context);
		if (s == null) {
			// Explicit Base Values
			if (context.startsWith("#") || context.startsWith("$"))
				return getContext(ctx, context);
			if (onlyWindow) // no Default values
				return "";
			return getContext(ctx, "#" + context);
		}
		return s;
	} // getContext

	/**
	 * Get Value of Context for Window.
	 * if not found global context if available
	 * @param ctx context
	 * @param WindowNo window
	 * @param context context key
	 * @return value or ""
	 */
	public static String getContext(Properties ctx, int WindowNo, String context) {
		return getContext(ctx, WindowNo, context, false);
	} // getContext

	/**
	 * Get Value of Context for Window & Tab,
	 * if not found global context if available
	 * @param ctx context
	 * @param WindowNo window no
	 * @param TabNo tab no
	 * @param context context key
	 * @return value or ""
	 */
	public static String getContext(Properties ctx, int WindowNo, int TabNo, String context) {
		if (ctx == null || context == null)
			throw new IllegalArgumentException("Require Context");
		String s = ctx.getProperty(WindowNo + "|" + TabNo + "|" + context);
		// If TAB_INFO, don't check Window and Global context - teo_sarca BF [ 2017987 ]
		if (TAB_INFO == TabNo)
			return s != null ? s : "";
		//
		if (s == null)
			return getContext(ctx, WindowNo, context, false);
		return s;
	} // getContext

	/**
	 * Get Context and convert it to an integer (0 if error)
	 * @param ctx context
	 * @param context context key
	 * @return value
	 */
	public static int getContextAsInt(Properties ctx, String context) {
		if (ctx == null || context == null)
			throw new IllegalArgumentException("Require Context");
		String s = getContext(ctx, context);
		if (s.length() == 0)
			s = getContext(ctx, 0, context, false); // search 0 and defaults
		if (s.length() == 0)
			return -1;
		//
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			s_log.log(Level.SEVERE, "(" + context + ") = " + s, e);
		}
		return -1;
	} // getContextAsInt
	
	public static boolean getContextAsBooelan(Properties ctx, String context) {
		if (ctx == null || context == null)
			throw new IllegalArgumentException("Require Context");
		String s = getContext(ctx, context);
		if (s.length() == 0)
			s = getContext(ctx, 0, context, false); // search 0 and defaults
		if (s.length() == 0)
			return false;

		return s.toUpperCase().equals("Y") ? true : false;
	}

	/**
	 * Get Context and convert it to an integer (0 if error)
	 * @param ctx context
	 * @param WindowNo window no
	 * @param context context key
	 * @return value or 0
	 */
	public static int getContextAsInt(Properties ctx, int WindowNo, String context) {
		String s = getContext(ctx, WindowNo, context, false);
		if (s.length() == 0)
			return 0;
		//
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			s_log.log(Level.SEVERE, "(" + context + ") = " + s, e);
		}
		return 0;
	} // getContextAsInt

	/**
	 *	Get Context and convert it to an integer (0 if error)
	 *  @param ctx context
	 *  @param WindowNo window no
	 *  @param context context key
	 *  @param onlyWindow  if true, no defaults are used unless explicitly asked for
	 *  @return value or 0
	 */
	public static int getContextAsInt(Properties ctx, int WindowNo, String context, boolean onlyWindow) {
		String s = getContext(ctx, WindowNo, context, onlyWindow);
		if (s.length() == 0)
			return 0;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "(" + context + ") = " + s, e);
		}
		return 0;
	}	//	getContextAsInt
	
	/**
	 * Get Context and convert it to an integer (0 if error)
	 * @param ctx context
	 * @param WindowNo window no
	 * @param TabNo tab no
	 * @param context context key
	 * @return value or 0
	 */
	public static int getContextAsInt(Properties ctx, int WindowNo, int TabNo, String context) {
		String s = getContext(ctx, WindowNo, TabNo, context);
		if (s.length() == 0)
			return 0;
		//
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			s_log.log(Level.SEVERE, "(" + context + ") = " + s, e);
		}
		return 0;
	} // getContextAsInt

	/**
	 * Is AutoCommit
	 * @param ctx context
	 * @return true if auto commit
	 */
	public static boolean isAutoCommit(Properties ctx) {
		if (ctx == null)
			throw new IllegalArgumentException("Require Context");
		String s = getContext(ctx, "AutoCommit");
		if (s != null && s.equals("Y"))
			return true;
		return false;
	} // isAutoCommit

	/**
	 * Is Window AutoCommit (if not set use default)
	 * @param ctx context
	 * @param WindowNo window no
	 * @return true if auto commit
	 */
	public static boolean isAutoCommit(Properties ctx, int WindowNo) {
		if (ctx == null)
			throw new IllegalArgumentException("Require Context");
		String s = getContext(ctx, WindowNo, "AutoCommit", false);
		if (s != null) {
			if (s.equals("Y"))
				return true;
			else
				return false;
		}
		return isAutoCommit(ctx);
	} // isAutoCommit

	/**
	 * Is Auto New Record
	 * @param ctx context
	 * @return true if auto new
	 */
	public static boolean isAutoNew(Properties ctx) {
		if (ctx == null)
			throw new IllegalArgumentException("Require Context");
		String s = getContext(ctx, "AutoNew");
		if (s != null && s.equals("Y"))
			return true;
		return false;
	} // isAutoNew

	/**
	 * Is Window Auto New Record (if not set use default)
	 * @param ctx context
	 * @param WindowNo window no
	 * @return true if auto new record
	 */
	public static boolean isAutoNew(Properties ctx, int WindowNo) {
		if (ctx == null)
			throw new IllegalArgumentException("Require Context");
		String s = getContext(ctx, WindowNo, "AutoNew", false);
		if (s != null) {
			if (s.equals("Y"))
				return true;
			else
				return false;
		}
		return isAutoNew(ctx);
	} // isAutoNew

	/**
	 * Is Sales Order Trx
	 * @param ctx context
	 * @return true if SO (default)
	 */
	public static boolean isSOTrx(Properties ctx) {
		String s = getContext(ctx, "IsSOTrx");
		if (s != null && s.equals("N"))
			return false;
		return true;
	} // isSOTrx

	/**
	 * Is Sales Order Trx
	 * @param ctx context
	 * @param WindowNo window no
	 * @return true if SO (default)
	 */
	public static boolean isSOTrx(Properties ctx, int WindowNo) {
		String s = getContext(ctx, WindowNo, "IsSOTrx", true);
		if (s != null && s.equals("N"))
			return false;
		return true;
	} // isSOTrx

	/**
	 * Get Context and convert it to a Timestamp
	 * if error return today's date
	 * @param ctx context
	 * @param context context key
	 * @return Timestamp
	 */
	public static Timestamp getContextAsDate(Properties ctx, String context) {
		return getContextAsDate(ctx, 0, context);
	} // getContextAsDate

	/**
	 * Get Context and convert it to a Timestamp
	 * if error return today's date
	 * @param ctx context
	 * @param WindowNo window no
	 * @param context context key
	 * @return Timestamp
	 */
	public static Timestamp getContextAsDate(Properties ctx, int WindowNo, String context) {
		if (ctx == null || context == null)
			throw new IllegalArgumentException("Require Context");
		String s = getContext(ctx, WindowNo, context, false);
		// JDBC Format YYYY-MM-DD example 2000-09-11 00:00:00.0
		if (s == null || s.equals("")) {
			s_log.log(Level.SEVERE, "No value for: " + context);
			return new Timestamp(System.currentTimeMillis());
		}

		// timestamp requires time
		if (s.trim().length() == 10)
			s = s.trim() + " 00:00:00.0";
		else if (s.indexOf('.') == -1)
			s = s.trim() + ".0";

		// expert, validamos por si la cadena tiene solo un punto al final
		if (s.trim().length() == s.indexOf('.') + 1)
			s += "0";
		// fin expert, eruiz

		return Timestamp.valueOf(s);
	} // getContextAsDate

	/**
	 * Get Login AD_Client_ID
	 * @param ctx context
	 * @return login AD_Client_ID
	 */
	public static int getAD_Client_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#AD_Client_ID");
	} // getAD_Client_ID

	public static boolean isEmergencyMode(Properties ctx) {
		return Env.getContextAsBooelan(ctx, Env.EMERGENCY_MODE);
	}
	
	/**
	 * Get Login AD_Org_ID
	 * @param ctx context
	 * @return login AD_Org_ID
	 */
	public static int getAD_Org_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#AD_Org_ID");
	} // getAD_Client_ID

	/**
	 * Get Login AD_User_ID
	 * @param ctx context
	 * @return login AD_User_ID
	 */
	public static int getAD_User_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#AD_User_ID");
	} // getAD_User_ID

	/**
	 * Get Login AD_Role_ID
	 * @param ctx context
	 * @return login AD_Role_ID
	 */
	public static int getAD_Role_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#AD_Role_ID");
	} // getAD_Role_ID
	
	/**************************************************************************
	 *	Get Preference.
	 *  <pre>
	 *		0)	Current Setting
	 *		1) 	Window Preference
	 *		2) 	Global Preference
	 *		3)	Login settings
	 *		4)	Accounting settings
	 *  </pre>
	 *  @param  ctx context
	 *	@param	AD_Window_ID window no
	 *	@param	context		Entity to search
	 *	@param	system		System level preferences (vs. user defined)
	 *  @return preference value
	 */
	public static String getPreference(Properties ctx, int AD_Window_ID, String context, boolean system) {
		if (ctx == null || context == null)
			throw new IllegalArgumentException("Require Context");
		String retValue = null;
		//
		if (!system) // User Preferences
		{
			retValue = ctx.getProperty("P" + AD_Window_ID + "|" + context);// Window Pref
			if (retValue == null)
				retValue = ctx.getProperty("P|" + context); // Global Pref
		}
		else // System Preferences
		{
			retValue = ctx.getProperty("#" + context); // Login setting
			if (retValue == null)
				retValue = ctx.getProperty("$" + context); // Accounting setting
		}
		//
		return (retValue == null ? "" : retValue);
	} // getPreference

	/**************************************************************************
	 *  Language issues
	 */

	/** Context Language identifier */
	static public final String      LANGUAGE = "#AD_Language";
	
	public static final String PHR_ACCESS_ID = "#PHR_Access_ID";
	
	static public final String EMERGENCY_MODE = "#Emergency_Mode";

	/**
	 *  Check Base Language
	 *  @param ctx context
	 * 	@param tableName table to be translated
	 * 	@return true if base language and table not translated
	 */
	public static boolean isBaseLanguage(Properties ctx, String tableName) {
		/**
		if (isBaseTranslation(tableName))
			return Language.isBaseLanguage (getAD_Language(ctx));
		else	//	No AD Table
			if (!isMultiLingualDocument(ctx))
				return true;		//	access base table
		**/
		return Language.isBaseLanguage (getAD_Language(ctx));
	}	//	isBaseLanguage

	/**
	 *	Check Base Language
	 * 	@param AD_Language language
	 * 	@param tableName table to be translated
	 * 	@return true if base language and table not translated
	 */
	public static boolean isBaseLanguage(String AD_Language, String tableName) {
		/**
		if (isBaseTranslation(tableName))
			return Language.isBaseLanguage (AD_Language);
		else	//	No AD Table
			if (!isMultiLingualDocument(s_ctx))				//	Base Context
				return true;		//	access base table
		**/
		return Language.isBaseLanguage (AD_Language);
	}	//	isBaseLanguage

	/**
	 *	Check Base Language
	 * 	@param language language
	 * 	@param tableName table to be translated
	 * 	@return true if base language and table not translated
	 */
	public static boolean isBaseLanguage(Language language, String tableName) {
		/**
		if (isBaseTranslation(tableName))
			return language.isBaseLanguage();
		else	//	No AD Table
			if (!isMultiLingualDocument(s_ctx))				//	Base Context
				return true;		//	access base table
		**/
		return language.isBaseLanguage();
	}	//	isBaseLanguage

	/**
	 * 	Table is in Base Translation (AD)
	 *	@param tableName table
	 *	@return true if base trl
	 */
	public static boolean isBaseTranslation(String tableName) {
		if (tableName.startsWith("AD") || tableName.startsWith("EXME") || tableName.equals("C_Country_Trl"))
			return true;
		return false;
	} // isBaseTranslation

	/**
	 * 	Do we have Multi-Lingual Documents.
	 *  Set in DB.loadOrgs
	 * 	@param ctx context
	 * 	@return true if multi lingual documents
	 */
	public static boolean isMultiLingualDocument(Properties ctx) {
		return MClient.get(ctx).isMultiLingualDocument();
	} // isMultiLingualDocument

	/**
	 * Get System AD_Language
	 * @param ctx context
	 * @return AD_Language eg. en_US
	 */
	public static String getAD_Language(Properties ctx) {
		if (ctx != null) {
			String lang = getContext(ctx, LANGUAGE);
			if (lang != null && lang.length() > 0)
				return lang;
		}
		return Language.getBaseAD_Language();
	} // getAD_Language

	/**
	 *  Get System Language
	 *  @param ctx context
	 *	@return Language
	 */
	public static Language getLanguage(Properties ctx) {
		if (ctx != null) {
			String lang = getContext(ctx, LANGUAGE);
			if (lang != null && lang.length() > 0)
				return Language.getLanguage(lang);
		}
		return Language.getBaseLanguage();
	} // getLanguage

	/**
	 *  Get Login Language
	 *  @param ctx context
	 *	@return Language
	 */
	public static Language getLoginLanguage(Properties ctx) {
		return Language.getLoginLanguage();
	} // getLanguage

	/**
	 * Verify Language.
	 * Check that language is supported by the system
	 * @param ctx might be updated with new AD_Language
	 * @param language language
	 */
	public static void verifyLanguage(Properties ctx, Language language) {
		if (language.isBaseLanguage())
			return;

		boolean isSystemLanguage = false;
		ArrayList<String> AD_Languages = new ArrayList<String>();
		String sql = "SELECT DISTINCT AD_Language FROM AD_Message_Trl";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String AD_Language = rs.getString(1);
				if (AD_Language.equals(language.getAD_Language())) {
					isSystemLanguage = true;
					break;
				}
				AD_Languages.add(AD_Language);
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "verifyLanguage", e);
		} finally {
			DB.close(rs, pstmt);
		}
		// Found it
		if (isSystemLanguage)
			return;
		// No Language - set to System
		if (AD_Languages.size() == 0) {
			s_log.warning("NO System Language - Set to Base " + Language.getBaseAD_Language());
			language.setAD_Language(Language.getBaseAD_Language());
			return;
		}

		for (int i = 0; i < AD_Languages.size(); i++) {
			String AD_Language = (String) AD_Languages.get(i); // en_US
			String lang = AD_Language.substring(0, 2); // en
			//
			String langCompare = language.getAD_Language().substring(0, 2);
			if (lang.equals(langCompare)) {
				s_log.fine("Found similar Language " + AD_Language);
				language.setAD_Language(AD_Language);
				return;
			}
		}

		// We found same language
		// if (!"0".equals(Msg.getMsg(AD_Language, "0")))

		s_log.warning("Not System Language=" + language + " - Set to Base Language " + Language.getBaseAD_Language());
		language.setAD_Language(Language.getBaseAD_Language());
	} // verifyLanguage

	/**************************************************************************
	 *	Get Context as String array with format: key == value
	 *  @param ctx context
	 *  @return context string
	 */
	public static String[] getEntireContext(Properties ctx) {
		if (ctx == null)
			throw new IllegalArgumentException("Require Context");
		Iterator<?> keyIterator = ctx.keySet().iterator();
		String[] sList = new String[ctx.size()];
		int i = 0;
		while (keyIterator.hasNext()) {
			Object key = keyIterator.next();
			sList[i++] = key.toString() + " == " + ctx.get(key).toString();
		}

		return sList;
	} // getEntireContext

	/**
	 * Get Header info (connection, org, user)
	 * @param ctx context
	 * @param WindowNo window
	 * @return Header String
	 */
	public static String getHeader(Properties ctx, int WindowNo)
	{
		StringBuffer sb = new StringBuffer();
		if (WindowNo > 0){
			sb.append(getContext(ctx, WindowNo, "WindowName", false)).append("  ");
			final String documentNo = getContext(ctx, WindowNo, "DocumentNo", false);
			final String value = getContext(ctx, WindowNo, "Value", false);
			final String name = getContext(ctx, WindowNo, "Name", false);
			if(!"".equals(documentNo)) {
				sb.append(documentNo).append("  ");
			}
			if(!"".equals(value)) {
				sb.append(value).append("  ");
			}
			if(!"".equals(name)) {
				sb.append(name).append("  ");
			}
		}
		sb.append(getAD_User_Name(Env.getCtx())).append("@")
			.append(getAD_Client_Name(Env.getCtx())).append(".")
			.append(getAD_Org_Name(Env.getCtx()))
			.append(" [").append(CConnection.get().toString()).append("]");
		return sb.toString();
	}	//	getHeader

	/**
	 * Clean up context for Window (i.e. delete it)
	 * @param ctx context
	 * @param WindowNo window
	 */
	public static void clearWinContext(Properties ctx, int WindowNo) {
		if (ctx == null)
			throw new IllegalArgumentException("Require Context");
		//
		Object[] keys = ctx.keySet().toArray();
		for (int i = 0; i < keys.length; i++) {
			String tag = keys[i].toString();
			if (tag.startsWith(WindowNo + "|"))
				ctx.remove(keys[i]);
		}
		// Clear Lookup Cache
		MLookupCache.cacheReset(WindowNo);
		// MLocator.cacheReset(WindowNo);
		//expert : mrojas : integracion ZK
		if (Ini.isClient())
			removeWindow(WindowNo);
	} // clearWinContext

	/**
	 * Clean up all context (i.e. delete it)
	 * @param ctx context
	 */
	public static void clearContext(Properties ctx) {
		if (ctx == null)
			throw new IllegalArgumentException("Require Context");
		ctx.clear();
	} // clearContext

	/**
	 * Parse Context replaces global or Window context @tag@ with actual value.
	 * 
	 * @tag@ are ignored otherwise "" is returned
	 * @param ctx context
	 * @param WindowNo Number of Window
	 * @param value Message to be parsed
	 * @param onlyWindow if true, no defaults are used
	 * @param ignoreUnparsable if true, unsuccessful @return parsed String or "" if not successful and ignoreUnparsable
	 * @return parsed context
	 */
	public static String parseContext(Properties ctx, int WindowNo, String value, boolean onlyWindow, boolean ignoreUnparsable) {
		if (value == null || value.length() == 0)
			return "";

		String token;
		String inStr = new String(value);
		StringBuffer outStr = new StringBuffer();

		int i = inStr.indexOf('@');
		while (i != -1) {
			outStr.append(inStr.substring(0, i)); // up to @
			inStr = inStr.substring(i + 1, inStr.length()); // from first @

			int j = inStr.indexOf('@'); // next @
			if (j < 0) {
				s_log.log(Level.SEVERE, "No second tag: " + inStr);
				return ""; // no second tag
			}

			token = inStr.substring(0, j);

			String ctxInfo = getContext(ctx, WindowNo, token, onlyWindow); // get context
			if (ctxInfo.length() == 0 && (token.startsWith("#") || token.startsWith("$")))
				ctxInfo = getContext(ctx, token); // get global context
			if (ctxInfo.length() == 0) {
				s_log.config("No Context Win=" + WindowNo + " for: " + token);
				if (!ignoreUnparsable)
					return ""; // token not found
			}
			else
				outStr.append(ctxInfo); // replace context with Context

			inStr = inStr.substring(j + 1, inStr.length()); // from second @
			i = inStr.indexOf('@');
		}
		outStr.append(inStr); // add the rest of the string

		return outStr.toString();
	} // parseContext

	/**
	 * Parse Context replaces global or Window context @tag@ with actual value.
	 * 
	 * @param ctx context
	 * @param WindowNo Number of Window
	 * @param value Message to be parsed
	 * @param onlyWindow if true, no defaults are used
	 * @return parsed String or "" if not successful
	 */
	public static String parseContext(Properties ctx, int WindowNo, String value, boolean onlyWindow) {
		return parseContext(ctx, WindowNo, value, onlyWindow, false);
	} // parseContext

	/**
	 * Parse expression, replaces global or PO properties @tag@ with actual value. 
	 * @param expression
	 * @param po
	 * @param trxName
	 * @return String
	 */
	public static String parseVariable(String expression, PO po, String trxName, boolean keepUnparseable) {
		if (expression == null || expression.length() == 0)
			return "";

		String token;
		String inStr = new String(expression);
		StringBuffer outStr = new StringBuffer();

		int i = inStr.indexOf('@');
		while (i != -1)
		{
			outStr.append(inStr.substring(0, i));			// up to @
			inStr = inStr.substring(i+1, inStr.length());	// from first @

			int j = inStr.indexOf('@');						// next @
			if (j < 0)
			{
				s_log.log(Level.SEVERE, "No second tag: " + inStr);
				return "";						//	no second tag
			}

			token = inStr.substring(0, j);
			
			//format string
			String format = "";
			int f = token.indexOf('<');
			if (f > 0 && token.endsWith(">")) {
				format = token.substring(f+1, token.length()-1);
				token = token.substring(0, f);
			}
			
			if (token.startsWith("#") || token.startsWith("$")) {
				//take from context
				Properties ctx = po != null ? po.getCtx() : Env.getCtx();
				String v = Env.getContext(ctx, token);
				if (v != null && v.length() > 0)
					outStr.append(v);
				else if (keepUnparseable)
					outStr.append("@"+token+"@");
			} else if (po != null) {
				//take from po
				Object v = po.get_Value(token);
				if (v != null) {
					if (format != null && format.length() > 0) {
						if (v instanceof Integer && token.endsWith("_ID")) {
							int tblIndex = format.indexOf(".");
							String table = tblIndex > 0 ? format.substring(0, tblIndex) : token.substring(0, token.length() - 3);
							String column = tblIndex > 0 ? format.substring(tblIndex + 1) : format;
							outStr.append(DB.getSQLValueString(trxName, 
									"select " + column + " from  " + table + " where " + table + "_id = ?", (Integer)v));
						} else if (v instanceof Date) {
							SimpleDateFormat df = new SimpleDateFormat(format);
							outStr.append(df.format((Date)v));
						} else if (v instanceof Number) {
							DecimalFormat df = new DecimalFormat(format);
							outStr.append(df.format(((Number)v).doubleValue()));
						} else {
							MessageFormat mf = new MessageFormat(format);
							outStr.append(mf.format(v));
						}
					} else {
						outStr.append(v.toString());
					}
				}
				else if (keepUnparseable) {
					outStr.append("@"+token+"@");
				}
			}

			inStr = inStr.substring(j+1, inStr.length());	// from second @
			i = inStr.indexOf('@');
		}
		outStr.append(inStr);						// add the rest of the string

		return outStr.toString();
	}

	/*************************************************************************/

	// Array of active Windows
	private static ArrayList<Container> s_windows = new ArrayList<Container>(20);

	/**
	 * Add Container and return WindowNo.
	 * The container is a APanel, AWindow or JFrame/JDialog
	 * @param win window
	 * @return WindowNo used for context
	 */
	public static int createWindowNo(Container win) {
		int retValue = s_windows.size();
		s_windows.add(win);
		return retValue;
	} // createWindowNo

	/**
	 * Search Window by comparing the Frames
	 * @param container container
	 * @return WindowNo of container or 0
	 */
	public static int getWindowNo(Container container) {
		if (container == null)
			return 0;
		JFrame winFrame = getFrame(container);
		if (winFrame == null)
			return 0;

		// loop through windows
		for (int i = 0; i < s_windows.size(); i++) {
			Container cmp = (Container) s_windows.get(i);
			if (cmp != null) {
				JFrame cmpFrame = getFrame(cmp);
				if (winFrame.equals(cmpFrame))
					return i;
			}
		}
		return 0;
	} // getWindowNo

	/**
	 * Return the JFrame pointer of WindowNo - or null
	 * @param WindowNo window
	 * @return JFrame of WindowNo
	 */
	public static JFrame getWindow(int WindowNo) {
		JFrame retValue = null;
		try {
			retValue = getFrame((Container) s_windows.get(WindowNo));
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.toString());
		}
		return retValue;
	} // getWindow

	/**
	 * Remove window from active list
	 * @param WindowNo window
	 */
	private static void removeWindow(int WindowNo) {
		if (WindowNo < s_windows.size())
			s_windows.set(WindowNo, null);
	} // removeWindow

	/**
	 * Clean up context for Window (i.e. delete it)
	 * @param WindowNo window
	 */
	public static void clearWinContext(int WindowNo) {
		//expert : mrojas : el contexto esta en el contextProvider
		clearWinContext (getCtx(), WindowNo);
	} // clearWinContext

	/**
	 * Clean up all context (i.e. delete it)
	 */
	public static void clearContext() {
		//expert : mrojas : el contexto esta en el contextProvider
		getCtx().clear();
	} // clearContext

	
	/**************************************************************************
	 * Get Frame of Window
	 * @param container Container
	 * @return JFrame of container or null
	 */
	public static JFrame getFrame(Container container) {
		Container element = container;
		while (element != null) {
			if (element instanceof JFrame)
				return (JFrame) element;
			element = element.getParent();
		}
		return null;
	} // getFrame

	/**
	 * Get Graphics of container or its parent.
	 * The element may not have a Graphic if not displayed yet,
	 * but the parent might have.
	 * @param container Container
	 * @return Graphics of container or null
	 */
	public static Graphics getGraphics(Container container) {
		Container element = container;
		while (element != null) {
			Graphics g = element.getGraphics();
			if (g != null)
				return g;
			element = element.getParent();
		}
		return null;
	} // getFrame

	/**
	 * Return JDialog or JFrame Parent
	 * @param container Container
	 * @return JDialog or JFrame of container
	 */
	public static Window getParent(Container container) {
		Container element = container;
		while (element != null) {
			if (element instanceof JDialog || element instanceof JFrame)
				return (Window) element;
			if (element instanceof Window)
				return (Window) element;
			element = element.getParent();
		}
		return null;
	} // getParent

	/**************************************************************************
	 * Get Image with File name
	 * 
	 * @param fileNameInImageDir full file name in imgaes folder (e.g. Bean16.gif)
	 * @return image
	 */
	public static Image getImage(String fileNameInImageDir) {
		URL url = Compiere.class.getResource("images/" + fileNameInImageDir);
		if (url == null) {
			s_log.log(Level.SEVERE, "Not found: " + fileNameInImageDir);
			return null;
		}
		Toolkit tk = Toolkit.getDefaultToolkit();
		return tk.getImage(url);
	} // getImage

	/**
	 * Get ImageIcon.
	 * 
	 * @param fileNameInImageDir full file name in imgaes folder (e.g. Bean16.gif)
	 * @return image
	 */
	public static ImageIcon getImageIcon(String fileNameInImageDir) {
		URL url = Compiere.class.getResource("images/" + fileNameInImageDir);
		if (url == null) {
			s_log.log(Level.SEVERE, "Not found: " + fileNameInImageDir);
			return null;
		}
		return new ImageIcon(url);
	} // getImageIcon

	/**
	 *  Get ImageIcon. This method different from getImageIcon
	 *  where the fileName parameter is without extension. The
	 *  method will first try .gif and then .png if .gif does not
	 *  exists.
	 *
	 *  @param fileName file name in imgaes folder without the extension(e.g. Bean16)
	 *  @return image
	 */
	public static ImageIcon getImageIcon2 (String fileName)
	{
		URL url = Compiere.class.getResource("images/" + fileName+".gif");
		if (url == null)
			url = Compiere.class.getResource("images/" + fileName+".png");
		if (url == null)
		{
			s_log.log(Level.INFO, "GIF/PNG Not found: " + fileName);
			return null;
		}
		return new ImageIcon(url);
	}   //  getImageIcon2
	

	/***************************************************************************
	 * Start Browser
	 * @param url url
	 */
	public static void startBrowser(String url) {
		s_log.info(url);
		contextProvider.showURL(url);
	} // startBrowser

	/**
	 * Do we run on Apple
	 * @return true if Mac
	 */
	public static boolean isMac() {
		String osName = System.getProperty("os.name");
		osName = osName.toLowerCase();
		return osName.indexOf("mac") != -1;
	} // isMac

	/**
	 * Do we run on Windows
	 * @return true if windows
	 */
	public static boolean isWindows() {
		String osName = System.getProperty("os.name");
		osName = osName.toLowerCase();
		return osName.indexOf("windows") != -1;
	} // isWindows

   	
	/** Array of hidden Windows				*/
	private static ArrayList<CFrame>	s_hiddenWindows = new ArrayList<CFrame>();
	/** Closing Window Indicator			*/
	private static boolean 				s_closingWindows = false;
	
	/**
	 * Hide Window
	 * @param window window
	 * @return true if window is hidden, otherwise close it
	 */
	static public boolean hideWindow(CFrame window) {
		if (!Ini.isCacheWindow() || s_closingWindows)
			return false;
		for (int i = 0; i < s_hiddenWindows.size(); i++) {
			CFrame hidden = s_hiddenWindows.get(i);
			s_log.info(i + ": " + hidden);
			if (hidden.getAD_Window_ID() == window.getAD_Window_ID())
				return false; // already there
		}
		if (window.getAD_Window_ID() != 0) // workbench
		{
			if (s_hiddenWindows.add(window)) {
				window.setVisible(false);
				s_log.info(window.toString());
				// window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_ICONIFIED));
				if (s_hiddenWindows.size() > 10) {
					CFrame toClose = s_hiddenWindows.remove(0);		//	sort of lru
					try {
						s_closingWindows = true;
						toClose.dispose();
					} finally {
						s_closingWindows = false;
					}
				}
				return true;
			}
		}
		return false;
	} // hideWindow

	/**
	 * Show Window
	 * @param AD_Window_ID window
	 * @return true if window re-displayed
	 */
	static public CFrame showWindow(int AD_Window_ID) {
		for (int i = 0; i < s_hiddenWindows.size(); i++) {
			CFrame hidden = s_hiddenWindows.get(i);
			if (hidden.getAD_Window_ID() == AD_Window_ID) {
				s_hiddenWindows.remove(i);
				s_log.info(hidden.toString());
				hidden.setVisible(true);
				// De-iconify window - teo_sarca [ 1707221 ]
				int state = hidden.getExtendedState();
				if ((state & CFrame.ICONIFIED) > 0)
					hidden.setExtendedState(state & ~CFrame.ICONIFIED);
				//
				hidden.toFront();
				return hidden;
			}
		}
		return null;
	} // showWindow

	/**
	 * Clode Windows
	 */
	static void closeWindows() {
		s_closingWindows = true;
		for (int i = 0; i < s_hiddenWindows.size(); i++) {
			CFrame hidden = s_hiddenWindows.get(i);
			hidden.dispose();
		}
		s_hiddenWindows.clear();
		s_closingWindows = false;
	} // closeWindows

	/**
	 * Sleep
	 * @param sec seconds
	 */
	public static void sleep(int sec) {
		s_log.info("Start - Seconds=" + sec);
		try {
			Thread.sleep(sec * 1000);
		} catch (Exception e) {
			s_log.log(Level.WARNING, "", e);
		}
		s_log.info("End");
	} // sleep

	/**
	 * Update all windows after look and feel changes.
	 * @since 2006-11-27 
	 */
	public static Set<Window>updateUI() 
	{
		Set<Window> updated = new HashSet<Window>();
		for (Container c : s_windows)
		{
			Window w = getFrame(c);
			if (w == null) continue;
			if (updated.contains(w)) continue;
			SwingUtilities.updateComponentTreeUI(w);
			w.validate();
			RepaintManager mgr = RepaintManager.currentManager(w);
			Component childs[] = w.getComponents();
			for (Component child : childs) {
				if (child instanceof JComponent)
					mgr.markCompletelyDirty((JComponent)child);
			}
			w.repaint();
			updated.add(w);
		}
		for (Window w : s_hiddenWindows)
		{
			if (updated.contains(w)) continue;
			SwingUtilities.updateComponentTreeUI(w);
			w.validate();
			RepaintManager mgr = RepaintManager.currentManager(w);
			Component childs[] = w.getComponents();
			for (Component child : childs) {
				if (child instanceof JComponent)
					mgr.markCompletelyDirty((JComponent)child);
			}
			w.repaint();
			updated.add(w);
		}
		return updated;
	}
	
	/**
	 * Prepare the context for calling remote server (for e.g, ejb), 
	 * only default and global variables are pass over.
	 * It is too expensive and also can have serialization issue if 
	 * every remote call to server is passing the whole client context.
	 * @param ctx
	 * @return Properties
	 */
	public static Properties getRemoteCallCtx(Properties ctx) {
		Properties p = new Properties();
		Set<Object> keys = ctx.keySet();
		for (Object key : keys) 
		{
			String s = key.toString();
			if (s.startsWith("#") || s.startsWith("$"))
			{
				p.put(key, ctx.get(key));
			}
		}
		
		return p;
	}
	
	/**
	 * Get Login EXME_Enfermera_ID
	 * @param ctx context
	 * @return login EXME_Enfermera_ID
	 */
	public static int getEXME_Enfermeria_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#EXME_Enfermeria_ID");
	}

	/**
	 * Get Login EXME_Medico_ID
	 * @param ctx Context
	 * @return login EXME_Medico_ID
	 */
	public static int getEXME_Medico_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#EXME_Medico_ID");
	}

	/**
	 * Get Login EXME_Medico_ID
	 * @param ctx Context
	 * @return login EXME_Medico_ID
	 */
	public static String getEXME_Medico_Name(Properties ctx) {
		return MEXMEMedico.nombreMedico(ctx, Env.getEXME_Medico_ID(ctx));
	}

	
	/**
	 * Get Login EXME_Asistente_ID
	 * @param ctx Context
	 * @return login EXME_Asistente_ID
	 */
	public static int getEXME_Asistente_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#EXME_Asistente_ID");
	}

	/**
	 * Get Login Substitute_ID
	 * @param ctx Context
	 * @return login Substitute_ID
	 */
	public static int getSubstitute_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#Substitute_ID");
	}

	/**
	 * Get Login Client Name
	 * 
	 * @param ctx
	 * @return
	 */
	public static String getAD_Client_Name(Properties ctx) {
		return Env.getContext(ctx, "#AD_Client_Name");
	}

	/**
	 * Get Login Org Name 
	 * @param ctx  Context
	 * @return
	 */
	public static String getAD_Org_Name(Properties ctx) {
		return Env.getContext(ctx, "#AD_Org_Name");
	}

	/**************************************************************************
	 * Static Variables
	 */

	/** Big Decimal 0 */
	static final public BigDecimal ZERO = new BigDecimal(0.0);
	/** Big Decimal 1 */
	static final public BigDecimal ONE = new BigDecimal(1.0);
	/** Big Decimal 100 */
	static final public BigDecimal ONEHUNDRED = new BigDecimal(100.0);

	/** New Line */
	public static final String NL = System.getProperty("line.separator");

	public static final String CONTEXT_PATH = "#ContextPath";
	public static final String REQUEST_SCHEMA = "#RequestScheme";
	public static final String APP_URL = "#AppUrl";
	public static final String APP_HOST = "#AppHost";


	/** The time zone for the current organization  */
	public static final String TIMEZONE = "#TimeZone";
	

	
	/**
	 * Static initializer
	 */
	static {
		// Set English as default Language
		getCtx().put(LANGUAGE, Language.getBaseAD_Language()); //expert : mrojas : el contexto esta en el contextProvider
	} // static

	/******************************************/
	
	/**
	 * Get Login EXME_EstServ_ID
	 * 
	 * @param ctx Context
	 * @return login EXME_EstServ_ID
	 */
	public static int getEXME_EstServ_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#EXME_EstServ_ID");
	}

	/**
	 * Get Login M_Warehouse_ID
	 * 
	 * @param ctx Context
	 * @return login M_Warehouse_ID
	 */
	public static int getM_Warehouse_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#M_Warehouse_ID");
	}
	
	/**
	 * Get Login M_Warehouse_Name
	 * 
	 * @param ctx
	 * @return M_Warehouse_Name
	 */
	public static String getM_Warehouse_Name(Properties ctx) {
		return Env.getContext(ctx, "#M_Warehouse_Name");
	}
	
	/**
	 * Get Login User_Patient_ID
	 * 
	 * @param ctx Context
	 * @return login User Patient ID
	 */
	public static int getUserPatientID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#UserPatient_ID");
	}
	
	public static int getAccessId(Properties ctx) {
		return Env.getContextAsInt(ctx, PHR_ACCESS_ID);
	}
	
	/**
	 * Get Login AD_OrgTrx_ID
	 * 
	 * @param ctx
	 * @return AD_OrgTrx_ID
	 */
	public static int getAD_OrgTrx_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#AD_OrgTrx_ID");
	}

	/**
	 * Get Login AD_Role_Name
	 * 
	 * @param ctx
	 * @return AD_Role_Name
	 */
	public static String getAD_Role_Name(Properties ctx) {
		return Env.getContext(ctx, "#AD_Role_Name");
	}

	/**
	 * Get Login AD_Session_ID
	 * 
	 * @param ctx
	 * @return AD_Session_ID
	 */
	public static int getAD_Session_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#AD_Session_ID");
	}

	/**
	 * Get Login AD_User_Name
	 * 
	 * @param ctx
	 * @return AD_User_Name
	 */
	public static String getAD_User_Name(Properties ctx) {
		return Env.getContext(ctx, "#AD_User_Name");
	}

	/**
	 * Get Login C_AcctSchema_ID
	 * 
	 * @param ctx
	 * @return C_AcctSchema_ID
	 */
	public static int getC_AcctSchema_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "$C_AcctSchema_ID");
	}

	/**
	 * Get Login C_BP_Group_ID
	 * 
	 * @param ctx
	 * @return C_BP_Group_ID
	 */
	public static int getC_BP_Group_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#C_BP_Group_ID");
	}

	/**
	 * Get Login C_Country_ID
	 * 
	 * @param ctx
	 * @return C_Country_ID
	 */
	public static int getC_Country_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#C_Country_ID");
	}

	/**
	 * Get Login C_Country_ID
	 * 
	 * @param ctx
	 * @return C_Country_ID
	 */
	public static String getC_Country_Code(Properties ctx) {
		return Env.getContext(ctx, "#C_Country_Code");
	}

	/**
	 * Get Login C_Currency_ID
	 * 
	 * @param ctx
	 * @return C_Currency_ID
	 */
	public static int getC_Currency_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "$C_Currency_ID");
	}

	/**
	 * Get Login C_Region_ID
	 * 
	 * @param ctx
	 * @return C_Region_ID
	 */
	public static int getC_Region_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#C_Region_ID");
	}

	/**
	 * Get Login EXME_EstServ_Name
	 * 
	 * @param ctx
	 * @return EXME_EstServ_Name
	 */
	public static String getEXME_EstServ_Name(Properties ctx) {
		return Env.getContext(ctx, "#EXME_EstServ_Name");
	}

	/**
	 * Get Login EXME_EstServAlm_ID
	 * 
	 * @param ctx
	 * @return EXME_EstServAlm_ID
	 */
	public static int getEXME_EstServAlm_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#EXME_EstServAlm_ID");
	}

//	/**
//	 * Get Login M_Locator_ID
//	 * 
//	 * @param ctx
//	 * @return M_Locator_ID
//	 */
//	public static int getM_Locator_ID(Properties ctx) {
//		return Env.getContextAsInt(ctx, "#M_Locator_ID");
//	}

	/**
	 * Get Login M_PriceList_ID
	 * 
	 * @param ctx
	 * @return M_PriceList_ID
	 */
	public static int getM_PriceList_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#M_PriceList_ID");
	}

	/**
	 * Get Login M_PriceListSales_ID
	 * drugs
	 * @param ctx
	 * @return M_PriceList_ID 
	 */
	public static int getM_PriceListSO_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#M_PriceListSO_ID");
	}
	

	/**
	 * Get Login M_PriceListSales_ID
	 * SERVICES
	 * @param ctx
	 * @return M_PriceList_ID 
	 */
	public static int getM_PriceListSE_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#M_PriceListSE_ID");
	}
	
	/**
	 * Get Login M_PriceListSales_ID
	 * SECOFI
	 * @param ctx
	 * @return M_PriceList_ID 
	 */
	public static int getM_PriceList_Reg_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#M_PriceListReg_ID");
	}
	
	/**
	 * Get Login SalesRep_ID
	 * 
	 * @param ctx
	 * @return SalesRep_ID
	 */
	public static int getSalesRep_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#SalesRep_ID");
	}

	/**
	 * Get Login TipoArea
	 * 
	 * @param ctx
	 * @return TipoArea
	 */
	public static String getTipoArea(Properties ctx) {
		return Env.getContext(ctx, "#TipoArea");
	}

	/**
	 * Get Login User_Level
	 * 
	 * @param ctx
	 * @return User_Level
	 */
	public static String getUser_Level(Properties ctx) {
		return Env.getContext(ctx, "#User_Level");
	}

	/**
	 * Get Login Pharmacist
	 * 
	 * @param ctx
	 * @return EXME_Pharmacist_ID
	 */
	public static int getEXME_Pharmacist_ID(Properties ctx) {
		return Env.getContextAsInt(ctx, "#EXME_Pharmacist_ID");
	}
	public static String getEXME_PharmacistType(Properties ctx) {
		return Env.getContext(ctx, "#EXME_Pharmacist_Type");
	}
	public static boolean getEULAAccepted(Properties ctx) {
		return Env.getContextAsBooelan(ctx, "#EULA_OK");
	}
	
	
	/**
	 * Get Login EXME_Enfermeria_ID
	 * @param ctx Context
	 * @param notMultipleRoles 
	 * true: returns -1 if the user is a Doctor
	 * false: return {@link #getEXME_Enfermeria_ID(Properties)}
	 * @return login EXME_Pharmacist_ID
	 */
	public static int getEXME_Enfermeria_ID(Properties ctx, boolean notMultipleRoles) {
		int value;
		if(notMultipleRoles && Env.getEXME_Medico_ID(ctx) > 0){
			value = -1;
		} else {
			value = getEXME_Enfermeria_ID(ctx);	
		}
		return value;
	}

	/**
	 * Get Login EXME_Pharmacist_ID
	 * @param ctx Context
	 * @param notMultipleRoles 
	 * true: returns -1 if the user is a Doctor or Nurse
	 * false: return {@link #getEXME_Pharmacist_ID(Properties)}
	 * @return login EXME_Pharmacist_ID
	 */
	public static int getEXME_Pharmacist_ID(Properties ctx, boolean notMultipleRoles) {
		int value;
		if (notMultipleRoles && //
			(Env.getEXME_Medico_ID(ctx) > 0 || Env.getEXME_Enfermeria_ID(ctx) > 0)) {
			value = -1;
		}
		else {
			value = getEXME_Pharmacist_ID(ctx);
		}
		return value;
	}


	/**
	 * Returns the time zone for the current organization
	 * @param ctx The application context
	 * @return The Time Zone name
	 */
	public static String getTimezone(Properties ctx) {
		String tz = Env.getContext(ctx, TIMEZONE);
		
		if(StringUtils.isEmpty(tz)) {
			tz = Env.getSystemTimezone();
		}
		
		return tz;
	}
	
	/**
	 * Returns the time zone for the current organization
	 * 
	 * @param ctx The application context
	 * @return The Time Zone
	 */
	public static TimeZone getTimeZone(Properties ctx) {
		return DateTimeZone.forID(getTimezone(ctx)).toTimeZone();
	}
	
	/**
	 * Returns the application server time zone
	 * @return The application server time zone name
	 */
	public static String getSystemTimezone() {
		return System.getProperty("user.timezone");
	}
	
	/**
	 * Returns a String with the time zone offset in hours from UTC, example : GMT+8, GMT-6
	 * @param tzName The JodaTime time zone name
	 * @return A String with UTC plus the time zone offset in hours
	 */
	public static String getTimeZoneOffset(String tzName) {
		return "GMT+" + (DateTimeZone.forID(tzName).getOffset(0)/1000/60/60);
	}
	
//	public static String getTheme(Properties ctx) {
//		return getContext(ctx, "theme");
//	} // Expert: Lama

//	public static String getThemeUrl(Properties ctx) {
//		final String theme = getTheme(ctx);
//		if (StringUtils.isBlank(theme)) {
//			return StringUtils.EMPTY;
//		}
//		return "themes/" + theme + "/";
//	} // Expert: Lama

//	public static String getThemeFontColor(Properties ctx) {
//		final String fontColor = Utilerias.getPropertiesFromXPT("ecsThemeFont");
//		if (StringUtils.isNotBlank(fontColor)) {
//			return "color:" + fontColor + ";";
//		}
//		return StringUtils.EMPTY;
//	} // Expert: Lama
	
	
	public static Timestamp getCurrentDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getAppUrl(Properties ctx) {
		return Env.getContext(ctx, Env.APP_URL);
	}

	/**
	 * Comprobar si esta ingresado como System
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean isSystem(Properties ctx) {
		return Env.getAD_Client_ID(ctx) == 0 ? true : false;
	}
	
	
	public static boolean isSpanishDefaultLanguage(Properties ctx) {
		// Configuracion del pais de defecto
		final MEXMEI18N inter = MEXMEI18N.getFromCountry(ctx, 247, null);
		// Si la configuracion esta marcada para utilizar el espanol como idioma por defecto.
		return inter != null && inter.isspanishDefaultLanguage();
	}
	
	/**
	 * @param ctx Contexto
	 * @return TRUE si se van a mostrar los iconos de la ruta /images/menu/[24x24,16x16]
	 *         FALSE se toman los iconos originales /images/[24x24,16x16]
	 */
	public static boolean isNewIcons(Properties ctx) {
		String context = "NewIcons";
		if (ctx.getProperty(context) == null) {
			ctx.setProperty(context, StringUtils.defaultIfEmpty(Utilerias.getPropertiesFromXPT(context), "n"));
		}
		return DB.TO_BOOLEAN(Env.getContext(ctx, context));
	}
	
} // Env
