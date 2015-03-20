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

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import javax.print.attribute.standard.MediaSize;

import org.apache.commons.lang.StringUtils;
import org.compiere.Compiere;
import org.compiere.model.MElement;
import org.compiere.model.MLanguage;

/**
 *	Reads all Messages and stores them in a HashMap
 *
 *  @author     Jorg Janke
 *  @version    $Id: Msg.java,v 1.9 2006/08/23 01:35:36 mrojas Exp $
 *  PMD revision
 */
public final class Msg
{
	/** Initial size of HashMap     */
	private static final int 		MAP_SIZE = 750;
	/**  Separator between Msg and optional Tip     */
	private static final String     SEPARATOR = Env.NL + Env.NL;

	/**	Singleton						*/
	private static	Msg				s_msg = null;

	/**	Logger							*/
	private static CLogger			s_log = CLogger.getCLogger (Msg.class);

	/**
	 * 	Get Message Object
	 *	@return Mag
	 */
	private static Msg get()
	{
		if (s_msg == null)
			s_msg = new Msg();
		return s_msg;
	}	//	get

	
	/**************************************************************************
	 *	Constructor
	 */
	private Msg()
	{
	}	//	Mag

	/**  The Map                    */
	private CCache<String,CCache<String,String>> m_languages 
		= new CCache<String,CCache<String,String>>("msg_lang", 2, 0);

	/**
	 *  Get Language specific Message Map
	 *  @param ad_language Language Key
	 *  @return HashMap of Language
	 */
	private CCache<String,String> getMsgMap (String AD_Language)
	{
		if (StringUtils.isEmpty(AD_Language)){
			AD_Language = Language.getBaseAD_Language();
		}
		//  Do we have the language ?
		CCache<String,String> retValue = (CCache<String,String>)m_languages.get(AD_Language);
		if (retValue != null && !retValue.isEmpty()){
			return retValue;
		}
		//  Load Language
		retValue = initMsg(AD_Language);
		if (retValue != null) {
			m_languages.put(AD_Language, retValue);
			return retValue;
		}
		return retValue;
	}   //  getMsgMap


	/**
	 *	Init message HashMap.
	 *	The initial call is from ALogin (ConfirmPanel init).
	 *	The second from Env.verifyLanguage.
	 *  @param AD_Language Language
	 *  @return Cache HashMap
	 */
	private CCache<String,String> initMsg (String AD_Language)
	{
		if (StringUtils.isEmpty(AD_Language)){
			AD_Language = Language.getBaseAD_Language();
		}
		CCache<String,String> msg = new CCache<String,String>("AD_Message", MAP_SIZE, 0);
		//
		if (!DB.isConnected())
		{
			s_log.log(Level.SEVERE, "No DB Connection");
			return null;
		}
        PreparedStatement pstmt = null; //Expert
        ResultSet rs = null; //Expert
		try
		{
			//PreparedStatement pstmt = null;
			if (Env.isBaseLanguage(AD_Language, MLanguage.Table_Name))
				pstmt = DB.prepareStatement("SELECT Value, MsgText, MsgTip FROM AD_Message",  null);
			else
			{
				pstmt = DB.prepareStatement("SELECT m.Value, t.MsgText, t.MsgTip "
					+ "FROM AD_Message_Trl t, AD_Message m "
					+ "WHERE m.AD_Message_ID=t.AD_Message_ID"
					+ " AND t.AD_Language=?", null);
				pstmt.setString(1, AD_Language);
			}
			rs = pstmt.executeQuery();

			//	get values
			while (rs.next())
			{
				StringBuffer msgText = new StringBuffer();
				msgText.append(rs.getString(2));
				//
				if (rs.getString(3) != null)	{		//	messageTip on next line, if exists
					msgText.append(" ").append(SEPARATOR).append(rs.getString(3));
				}
				msg.put(rs.getString(1), msgText.toString());
			}

		}
		catch (SQLException e) {
			s_log.log(Level.SEVERE, "initMsg", e);
			return null;
		}
		finally { // Expert
			DB.close(rs, pstmt);
		}
		//
		if (msg.size() < 100) {
			s_log.log(Level.SEVERE, "Too few (" + msg.size() + ") Records found for " + AD_Language);
			return null;
		}
		s_log.info("Records=" + msg.size() + " - " + AD_Language);
		return msg;
	}	//	initMsg

	/**
	 *  Reset Message cache
	 */
	public void reset()
	{
		if (m_languages == null){
			return;
		}

		//  clear all languages
		Iterator<CCache<String, String>> iterator = m_languages.values().iterator();
		while (iterator.hasNext())
		{
			CCache<String, String> hm = (CCache<String, String>)iterator.next();
			hm.clear();
		}
		m_languages.clear();
	}   //  reset

	/**
	 *  Return an array of the installed Languages
	 *  @return Array of loaded Languages or null
	 */
	public String[] getLanguages()
	{
		if (m_languages == null)
			return null;
		String[] retValue = new String[m_languages.size()];
		m_languages.keySet().toArray(retValue);
		return retValue;
	}   //  getLanguages

	/**
	 *  Check if Language is loaded
	 *  @param language Language code
	 *  @return true, if language is loaded
	 */
	public boolean isLoaded (String language)
	{
		return m_languages != null && m_languages.containsKey(language);
	}   //  isLoaded

	/**
	 *  Lookup term
	 *  @param AD_Language language
	 *  @param text text
	 *  @return translated term or null
	 */
	private String lookup(final String AD_Language, final String text)
	{
		String retValue;
		if (text == null) {
			retValue = null;
		}
		else if (StringUtils.isEmpty(AD_Language)) {
			retValue = text;
		}
		// hardcoded trl
		else if ("/".equals(text) || "\\".equals(text)) {
			retValue = File.separator;
		}
		else if (";".equals(text) || ":".equals(text)) {
			retValue = File.pathSeparator;
		}
		else if ("COMPIERE_HOME".equals(text)) {
			retValue = Compiere.getCompiereHome();
		}
		else if ("bat".equals(text) || "sh".equals(text)) {
			if (System.getProperty("os.name").startsWith("Win")) {
				retValue = "bat";
			}
			else {
				retValue = "sh";
			}
		}
		else if ("CopyRight".equals(text)) {
			retValue = Compiere.COPYRIGHT;
		}
		else {
			//
			CCache<String, String> langMap = getMsgMap(AD_Language);
			if (langMap == null) {
				retValue = null;
			}
			else {
				retValue = (String) langMap.get(text);
			}
		}
		return retValue;
	}   //  lookup

	
	/**************************************************************************
	 *	Get translated text for AD_Message
	 *  @param  ad_language - Language
	 *  @param	AD_Message - Message Key
	 *  @return translated text
	 */
	public static String getMsg (String AD_Language, final String AD_Message)
	{
		String retValue;
		if (StringUtils.isNotEmpty(AD_Message)) {
			//
			if (StringUtils.isEmpty(AD_Language)) {
				AD_Language = Language.getBaseAD_Language();
			}
			//
			String retStr = get().lookup(AD_Language, AD_Message);
			//
			if (StringUtils.isEmpty(retStr)) {
				s_log.warning("NOT found: " + AD_Message);
				retValue = AD_Message;
			} else {
				retValue = retStr;
			}
		} else {
			retValue = "";
		}
		return retValue;
	}	//	getMsg

	/**
	 *  Get translated text message for AD_Message
	 *  @param  ctx Context to retrieve language
	 *  @param	AD_Message - Message Key
	 *  @return translated text
	 */
	public static String getMsg (final Properties ctx, final String AD_Message)
	{
		return getMsg (Env.getAD_Language(ctx), AD_Message);
	}   //  getMeg

	/**
	 *  Get translated text message for AD_Message
	 *  @param  language Language
	 *  @param	AD_Message - Message Key
	 *  @return translated text
	 */
	public static String getMsg (final Language language, final String AD_Message)
	{
		return getMsg (language.getAD_Language(), AD_Message);
	}   //  getMeg

	/**
	 *  Get translated text message for AD_Message
	 *  @param  ad_language - Language
	 *  @param	AD_Message - Message Key
	 *  @param  getText if true only return Text, if false only return Tip
	 *  @return translated text
	 */
	public static String getMsg (final String ad_language, final String AD_Message, final boolean getText)
	{
		String retStr = getMsg (ad_language, AD_Message);
		int pos = retStr.indexOf(SEPARATOR);
		//  No Tip
		if (pos == -1)
		{
			if (getText)
				return retStr;
			else
				return "";
		}
		else    //  with Tip
		{
			if (getText)
				retStr = retStr.substring (0, pos);
			else
			{
				int start = pos + SEPARATOR.length();
//				int end = retStr.length();
				retStr = retStr.substring (start);
			}
		}
		return retStr;
	}	//	getMsg

	/**
	 *  Get translated text message for AD_Message
	 *  @param  ctx Context to retrieve language
	 *  @param	AD_Message  Message Key
	 *  @param  getText if true only return Text, if false only return Tip
	 *  @return translated text
	 */
	public static String getMsg (final Properties ctx, final String AD_Message, final boolean getText)
	{
		return getMsg (Env.getAD_Language(ctx), AD_Message, getText);
	}   //  getMsg

	/**
	 *  Get translated text message for AD_Message
	 *  @param  language Language
	 *  @param	AD_Message  Message Key
	 *  @param  getText if true only return Text, if false only return Tip
	 *  @return translated text
	 */
	public static String getMsg (final Language language, final String AD_Message, final boolean getText)
	{
		return getMsg (language.getAD_Language(), AD_Message, getText);
	}   //  getMsg

	/**
	 *	Get clear text for AD_Message with parameters
	 *  @param  ctx Context to retrieve language
	 *  @param AD_Message   Message yey
	 *  @param args         MessageFormat arguments
	 *  @return translated text
	 *  @see java.text.MessageFormat for formatting options
	 */
	public static String getMsg(final Properties ctx, final String AD_Message, final Object[] args)
	{
		return getMsg (Env.getAD_Language(ctx), AD_Message, args);
	}	//	getMsg

	/**
	 *	Get clear text for AD_Message with parameters
	 *  @param  language Language
	 *  @param AD_Message   Message yey
	 *  @param args         MessageFormat arguments
	 *  @return translated text
	 *  @see java.text.MessageFormat for formatting options
	 */
	public static String getMsg(final Language language, final String AD_Message, final Object[] args)
	{
		return getMsg (language.getAD_Language(), AD_Message, args);
	}	//	getMsg

	/**
	 *	Get clear text for AD_Message with parameters
	 *  @param ad_language  Language
	 *  @param AD_Message   Message yey
	 *  @param args         MessageFormat arguments
	 *  @return translated text
	 *  @see java.text.MessageFormat for formatting options
	 */
	public static String getMsg (final String ad_language, final String AD_Message, final Object[] args)
	{
		String msg = getMsg(ad_language, AD_Message);
		String retStr = msg;
		try
		{
			retStr = MessageFormat.format(msg, args);	//	format string
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, msg, e);
		}
		return retStr;
	}	//	getMsg

	//EXPERT: obtenemos la cantidad en letra en la moneda recibida como parametro
	/**************************************************************************
	 * 	Get Amount in Words
	 * 	@param C_Currency_ID La moneda 
	 * 	@param amount numeric amount (352.80)
	 * 	@return amount in words (three*five*two 80/100)
	 */
	public static String getAmtInWords (final int C_Currency_ID, final String amount) {
		String nomMoneda = "";
		String isoCode   = "";
	
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
		String sql = "SELECT Description, ISO_Code FROM C_Currency " +
		             "WHERE C_Currency_ID = ?";
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_Currency_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				nomMoneda = rs.getString(1);
				isoCode   = rs.getString(2);
			} else {
				s_log.info("Msg.getAmtInWords- No existe la moneda");
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "Msg.getAmtInWords", (Exception)e);
        }finally {
        	DB.close(rs, pstmt);
            pstmt=null;
            rs=null;
		}
	
		Env.setContext(Env.getCtx(), "#NombreMoneda", nomMoneda);
		Env.setContext(Env.getCtx(), "#CodISO", isoCode);
		
		return getAmtInWords(Language.getLoginLanguage(), amount);
	}

	//EXPERT: obtenemos el nombre de la moneda en la que se hace el pago
	
	
	public static void main(String[] args) {
		Language language = new Language("English", Language.AD_Language_en_US, Locale.US, null, null,MediaSize.NA.LETTER);
		
		String test = getAmtInWords(language, "23.20");
		
		System.out.println(test);
	}

	/**************************************************************************
	 * 	Get Amount in Words
	 * 	@param language language
	 * 	@param amount numeric amount (352.80)
	 * 	@return amount in words (three*five*two 80/100)
	 */
	public static String getAmtInWords (final Language language, final String amount)
	{
        //EXPERT .- RMontemayor
		/*if (amount.indexOf(",") > -1) {currency.getISO_Code(
			amount=amount.replace(",",".");
		}*/
        //EXPERT .- RMontemayor
		if (language == null || StringUtils.isEmpty(amount))
			return amount;
		//	Try to find Class
		String className = "org.compiere.util.AmtInWords_";
		try
		{
			className += language.getLanguageCode().toUpperCase();
			Class<?> clazz = Class.forName(className);
			AmtInWords aiw = (AmtInWords)clazz.newInstance();
			return aiw.getAmtInWords(amount);
		}
		catch (ClassNotFoundException e)
		{
			s_log.log(Level.FINER, "Class not found: " + className);
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, className, e);
		}
		
		//	Fallback
		StringBuffer sb = new StringBuffer();
		/* Expert: miguel rojas
		int pos = amount.lastIndexOf('.');
		int pos2 = amount.lastIndexOf(',');
		if (pos2 > pos)
			pos = pos2;
		for (int i = 0; i < amount.length(); i++)
		{
			if (pos == i)	//	we are done
			{
				String cents = amount.substring(i+1);
				sb.append(' ').append(cents).append("/100");
				break;
			}
			else
			{
				char c = amount.charAt(i);
				if (c == ',' || c == '.')	//	skip thousand separator
					continue;
				if (sb.length() > 0)
					sb.append("*");
				sb.append(getMsg(language, String.valueOf(c)));
			}
		}
		return sb.toString(); Expert: miguel rojas */
		
		//Expert ..
		int entero = (int)Math.abs(Double.parseDouble(amount.trim().replaceAll(",", "")));
		double decimal = Double.parseDouble(amount.trim().replaceAll(",", "")) - entero;
		//String amountAbs = String.valueOf(Math.abs(Double.parseDouble(monto.toString())));
		
		String nombreMoneda = Env.getContext(Env.getCtx(), "#NombreMoneda");
		String codISO = Env.getContext(Env.getCtx(), "#CodISO"); 
		
		if (codISO.equalsIgnoreCase("MXN")) {
			nombreMoneda = "PESOS";
			codISO = "M. N.";
		}
		
		if (codISO.equalsIgnoreCase("USD")) {
			nombreMoneda = "DOLARES";
		}
		
		sb.append(n2t.convertirLetras(entero));
		sb.append(" ");
		sb.append(nombreMoneda);
		sb.append(" ");
		String d = Integer.toString((int)(decimal * 100));
		sb.append(d.length() == 1 ? "0" : StringUtils.EMPTY);
		sb.append(d);
		sb.append("/100");
		sb.append(" ");
		sb.append(codISO);
		
		String retValue =  null;
		
		if(Double.parseDouble(amount.toString())>0) { //la cantidad es positiva
		    retValue = "***( "+sb.toString().toUpperCase()+" )***"; //expert: miguel angel rojas;
		} else if(Double.parseDouble(amount.toString())<0) { //la cantidad es negativa
		    retValue = "***( - "+sb.toString().toUpperCase()+" )***"; //expert: miguel angel rojas;
		} else { //la cantidad es cero
		    retValue = "***( CERO "+sb.toString().toUpperCase()+" )***"; //expert: miguel angel rojas;
		}
		
		return retValue;
		//expert
	}	//	getAmtInWords


	/**************************************************************************
	 *  Get Translation for Element
	 *  @param ad_language language
	 *  @param columnName column name
	 *  @param isSOTrx if false PO terminology is used (if exists)
	 *  @return Name of the Column or "" if not found
	 */
	public static String getElement(String AD_Language, String columnName, boolean isSOTrx) {
		if (StringUtils.isEmpty(columnName)) {
			return "";
		}
		if (StringUtils.isEmpty(AD_Language)) {
			AD_Language = Language.getBaseAD_Language();
		}

		// Check AD_Element
		String retStr = "";
		PreparedStatement pstmt = null; // Expert
		ResultSet rs = null;// Expert
		try {
			try {
				if (Env.isBaseLanguage(AD_Language, MElement.Table_Name)) {
					pstmt = DB.prepareStatement("SELECT Name, PO_Name FROM AD_Element WHERE UPPER(ColumnName)=?", null);
				}
				else {
					pstmt =
						DB.prepareStatement("SELECT t.Name, t.PO_Name FROM AD_Element_Trl t, AD_Element e "
							+ "WHERE t.AD_Element_ID=e.AD_Element_ID AND UPPER(e.ColumnName)=? "
							+ "AND t.AD_Language=?", null);
					pstmt.setString(2, AD_Language);
				}
			}
			catch (Exception e) {
				return columnName;
			}
			pstmt.setString(1, columnName.toUpperCase());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retStr = rs.getString(1);
				if (!isSOTrx && StringUtils.isNotEmpty(rs.getString(2))) {
					retStr = rs.getString(2);
				}
			}
		}
		catch (SQLException e) {
			s_log.log(Level.SEVERE, "getElement", e);
			retStr = "";
		}
		finally {// Expert: Lama
			DB.close(rs, pstmt);
		}
		if (retStr != null) {
			return retStr.trim();
		}
		return retStr;
	} // getElement

	/**
	 *  Get Translation for Element using Sales terminology
	 *  @param ctx context
	 *  @param ColumnName column name
	 *  @return Name of the Column or "" if not found
	 */
	public static String getElement (Properties ctx, String columnName)
	{
		return getElement (Env.getAD_Language(ctx), columnName, true);
	}   //  getElement

	/**
	 *  Get Translation for Element
	 *  @param ctx context
	 *  @param ColumnName column name
	 *  @param isSOTrx sales transaction
	 *  @return Name of the Column or "" if not found
	 */
	public static String getElement (Properties ctx, String ColumnName, boolean isSOTrx)
	{
		return getElement (Env.getAD_Language(ctx), ColumnName, isSOTrx);
	}   //  getElement


	/**************************************************************************
	 *	"Translate" text.
	 *  <pre>
	 *		- Check AD_Message.AD_Message 	->	MsgText
	 *		- Check AD_Element.ColumnName	->	Name
	 *  </pre>
	 *  If checking AD_Element, the SO terminology is used.
	 *  @param ad_language  Language
	 *  @param isSOTrx sales order context
	 *  @param text	Text - MsgText or Element Name
	 *  @return translated text or original text if not found
	 */
	public static String translate(String AD_Language, boolean isSOTrx, String text)
	{
		if (StringUtils.isEmpty(text)) {
			return "";
		}
		if (StringUtils.isEmpty(AD_Language)) {
			AD_Language = Language.getBaseAD_Language();
		}
		// Check AD_Message
		String retStr = get().lookup(AD_Language, text);
		if (StringUtils.isNotEmpty(retStr)) {
			return retStr.trim();
		}
		// Check AD_Element
		retStr = getElement(AD_Language, text, isSOTrx);
		if (StringUtils.isNotEmpty(retStr)) {
			return retStr.trim();
		}
		// Nothing found
		if (!text.startsWith(Constantes.MANDATORY)) {
			s_log.warning("NOT found: " + text);
		}
		return text;
	}	//	translate

	/***
	 *	"Translate" text (SO Context).
	 *  <pre>
	 *		- Check AD_Message.AD_Message 	->	MsgText
	 *		- Check AD_Element.ColumnName	->	Name
	 *  </pre>
	 *  If checking AD_Element, the SO terminology is used.
	 *  @param ad_language  Language
	 *  @param text	Text - MsgText or Element Name
	 *  @return translated text or original text if not found
	 */
	public static String translate(String ad_language, String text)
	{
		return translate (ad_language, true, text);
	}	//	translate

	/**
	 *	"Translate" text.
	 *  <pre>
	 *		- Check AD_Message.AD_Message 	->	MsgText
	 *		- Check AD_Element.ColumnName	->	Name
	 *  </pre>
	 *  @param ctx  Context
	 *  @param text	Text - MsgText or Element Name
	 *  @return translated text or original text if not found
	 */
	public static String translate(Properties ctx, String text)
	{
		if (ctx == null || StringUtils.isEmpty(text)) {
			return text;
		}
		final String s = (String) ctx.get(text);
		if (StringUtils.isNotEmpty(s)) {
			return s;
		}
		return translate (Env.getAD_Language(ctx), Env.isSOTrx(ctx), text);
	}   //  translate

	/**
	 *	"Translate" text.
	 *  <pre>
	 *		- Check AD_Message.AD_Message 	->	MsgText
	 *		- Check AD_Element.ColumnName	->	Name
	 *  </pre>
	 *  @param language Language
	 *  @param text     Text
	 *  @return translated text or original text if not found
	 */
	public static String translate(Language language, String text)
	{
		return translate (language.getAD_Language(), false, text);
	}   //  translate

	/**
	 *	Translate elements enclosed in "@" (at sign)
	 *  @param ctx      Context
	 *  @param text     Text
	 *  @return translated text or original text if not found
	 */
	public static String parseTranslation(Properties ctx, String text)
	{
		if (StringUtils.isEmpty(text)){
			return text;
		}

		String inStr = text;
		String token;
		StringBuffer outStr = new StringBuffer();

		int i = inStr.indexOf("@");
		boolean properties = inStr.indexOf("@")<0;
		while (i != -1)
		{
			outStr.append(inStr.substring(0, i));			// up to @
			inStr = inStr.substring(i+1, inStr.length());	// from first @

			int j = inStr.indexOf("@");						// next @
			if (j < 0)										// no second tag
			{
				inStr = "@" + inStr;
				break;
			}

			token = inStr.substring(0, j);
			outStr.append(translate(ctx, token));			// replace context

			inStr = inStr.substring(j+1, inStr.length());	// from second @
			i = inStr.indexOf("@");
		} 
		if(properties) {
			inStr = translate(ctx, text);
			properties = inStr.equals(text);
		}
		if(properties){
			if (inStr.indexOf("#_") > -1) {
				inStr = Utilerias.getLabel(text);
			} else if (!StringUtils.contains(text, " ") && StringUtils.contains(text, ".")) {
				inStr = Utilerias.getMsg(ctx, text);
			}
		}
		outStr.append(inStr);           					//	add remainder
		return outStr.toString();
	}   //  parseTranslation
	
	
	/**
	 *	Get clear text for ApplicationResourfces with parameters
	 *  @param  locale 		Locale
	 *  @param key   		String
	 *  @param args         MessageFormat arguments
	 *  @return String  String
	 */
	public static String getMsg(Properties ctx, Locale locale, String key, Object[] args)
	{
		String msg = null;
		
		try {
			MessageFormat formatter = new MessageFormat("");
			formatter.setLocale(locale);

			formatter.applyPattern(Utilerias.getMessage(ctx, locale, key));
			msg = formatter.format(args);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "While getting/formatting " + key, e);
			msg = "*** " + key + " ***";
		}
		
		return msg;
		
	}


}	//	Msg
