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

import java.io.*;
import java.math.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.mail.internet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.ecs.*;
import org.apache.ecs.xhtml.*;
import org.compiere.model.*;

/**
 *  Servlet Utilities
 *
 *  @author Jorg Janke
 *  @version  $Id: WebUtil.java,v 1.5 2006/08/23 00:54:39 mrojas Exp $
 */
public class WebUtilParent
{
	/**	Static Logger	*/
	private static CLogger		log	= CLogger.getCLogger (WebUtilParent.class);
	
	/**
	 *  Create Exit Page "Log-off".
	 *  <p>
	 *  - End Session
	 *  - Go to start page (e.g. /compiere/index.html)
	 *
	 *  @param request request
	 *  @param response response
	 *  @param servlet servlet
	 *  @param ctx context
	 *  @param AD_Message messahe
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public static void createLoginPage (HttpServletRequest request, HttpServletResponse response,
		HttpServlet servlet, Properties ctx, String AD_Message) throws ServletException, IOException
	{
		request.getSession().invalidate();
		String url = WebEnv.getBaseDirectory("index.html");
		//
		WebDoc doc = null;
		if (ctx != null && AD_Message != null && !AD_Message.equals(""))
			doc = WebDoc.create (ctx, Msg.getMsg(ctx, AD_Message));
		else if (AD_Message != null)
			doc = WebDoc.create (ctx, AD_Message);
		else
			doc = WebDoc.create (ctx, false);
		script script = new script("window.top.location.replace('" + url + "');");
		doc.getBody().addElement(script);
		//
		createResponse (request, response, servlet, null, doc, false);
	}   //  createLoginPage

	/**
	 *  Create Login Button - replace Window
	 *
	 *  @param ctx context
	 *  @return Button
	 */
	public static button getLoginButton (Properties ctx)
	{
		String text = "Login";
		if (ctx != null)
			text = Msg.getMsg (ctx, "Login");
		button button = new button();
		button.setType("button").setName("Login").addElement(text);
		StringBuffer cmd = new StringBuffer ("window.top.location.replace('");
		cmd.append(WebEnv.getBaseDirectory("index.html"));
		cmd.append("');");
		button.setOnClick(cmd.toString());
		return button;
	}   //  getLoginButton

	
	/**************************************************************************
	 *  Get Cookie Properties
	 *
	 *  @param request request
	 *  @return Properties
	 */
	public static Properties getCookieProprties(HttpServletRequest request)
	{
		//  Get Properties
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				if (cookies[i].getName().equals(WebEnv.COOKIE_INFO))
					return propertiesDecode(cookies[i].getValue());
			}
		}
		return new Properties();
	}   //  getProperties

	
	/**
	 *  Get String Parameter.
	 *
	 *  @param request request
	 *  @param parameter parameter
	 *  @return string or null
	 */
	public static String getParameter (HttpServletRequest request, String parameter)
	{
		if (request == null || parameter == null)
			return null;
		String enc = request.getCharacterEncoding();
		try
		{
			if (enc == null)
			{
				request.setCharacterEncoding(WebEnv.ENCODING);
				enc = request.getCharacterEncoding();
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Set CharacterEncoding=" + WebEnv.ENCODING, e);
			enc = request.getCharacterEncoding();
		}
		String data = request.getParameter(parameter);
		if (data == null || data.length() == 0)
			return data;
		
		//	Convert
		if (enc != null && !WebEnv.ENCODING.equals(enc))
		{
			try
			{
				String dataEnc = new String(data.getBytes(enc), WebEnv.ENCODING);
				log.log(Level.FINER, "Convert " + data + " (" + enc + ")-> " 
					+ dataEnc + " (" + WebEnv.ENCODING + ")");
				data = dataEnc;
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Convert " + data + " (" + enc + ")->" + WebEnv.ENCODING);
			}
		}
		
		//	Convert &#000; to character (JSTL input)
		String inStr = data;
		StringBuffer outStr = new StringBuffer();
		int i = inStr.indexOf("&#");
		while (i != -1)
		{
			outStr.append(inStr.substring(0, i));			// up to &#
			inStr = inStr.substring(i+2, inStr.length());	// from &#

			int j = inStr.indexOf(";");						// next ;
			if (j < 0)										// no second tag
			{
				inStr = "&#" + inStr;
				break;
			}

			String token = inStr.substring(0, j);
			try
			{
				int intToken = Integer.parseInt(token);
				outStr.append((char)intToken);				// replace context
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Token=" + token, e);
				outStr.append("&#").append(token).append(";");
			}
			inStr = inStr.substring(j+1, inStr.length());	// from ;
			i = inStr.indexOf("&#");
		}

		outStr.append(inStr);           					//	add remainder
		String retValue = outStr.toString();
		/**
		StringBuffer debug = new StringBuffer();
		char[] cc = data.toCharArray();
		for (int j = 0; j < cc.length; j++)
		{
			debug.append(cc[j]);
			int iii = (int)cc[j];
			debug.append("[").append(iii).append("]");
		}
		log.finest(parameter + "=" + data + " -> " + retValue + " == " + debug);
		**/
		log.finest(parameter + "=" + data + " -> " + retValue);
		return retValue;
	}   //  getParameter

	/**
	 *  Get integer Parameter - 0 if not defined.
	 *
	 *  @param request request
	 *  @param parameter parameter
	 *  @return int result or 0
	 */
	public static int getParameterAsInt (HttpServletRequest request, String parameter)
	{
		if (request == null || parameter == null)
			return 0;
		String data = getParameter(request, parameter);
		if (data == null || data.length() == 0)
			return 0;
		try
		{
			return Integer.parseInt(data);
		}
		catch (Exception e)
		{
			log.warning (parameter + "=" + data + " - " + e);
		}
		return 0;
	}   //  getParameterAsInt

	/**
	 *  Get numeric Parameter - 0 if not defined
	 *
	 *  @param request request
	 *  @param parameter parameter
	 *  @return big decimal result or 0
	 */
	public static BigDecimal getParameterAsBD (HttpServletRequest request, String parameter)
	{
		if (request == null || parameter == null)
			return Env.ZERO;
		String data = getParameter(request, parameter);
		if (data == null || data.length() == 0)
			return Env.ZERO;
		try
		{
			return new BigDecimal (data);
		}
		catch (Exception e)
		{
		}
		try
		{
			DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Number);
			Object oo = format.parseObject(data);
			if (oo instanceof BigDecimal)
				return (BigDecimal)oo;
			else if (oo instanceof Number)
				return new BigDecimal (((Number)oo).doubleValue());
			return new BigDecimal (oo.toString());
		}
		catch (Exception e)
		{
			log.fine(parameter + "=" + data + " - " + e);
		}
		return Env.ZERO;
	}   //  getParameterAsBD

	/**
	 *  Get date Parameter - null if not defined.
	 *	Date portion only
	 *  @param request request
	 *  @param parameter parameter
	 *  @return timestamp result or null
	 */
	public static Timestamp getParameterAsDate (HttpServletRequest request, 
		String parameter)
	{
		return getParameterAsDate (request, parameter, null);
	}	//	getParameterAsDate
	
	/**
	 *  Get date Parameter - null if not defined.
	 *	Date portion only
	 *  @param request request
	 *  @param parameter parameter
	 *  @param language optional language
	 *  @return timestamp result or null
	 */
	public static Timestamp getParameterAsDate (HttpServletRequest request, 
		String parameter, Language language)
	{
		if (request == null || parameter == null)
			return null;
		String data = getParameter(request, parameter);
		if (data == null || data.length() == 0)
			return null;
		
		//	Language Date Format
		if (language != null)
		{
			try
			{
				DateFormat format = DisplayType.getDateFormat(DisplayType.Date, language);
				java.util.Date date = format.parse(data);
				if (date != null)
					return new Timestamp (date.getTime());
			}
			catch (Exception e)
			{
			}
		}
		
		//	Default Simple Date Format
		try
		{
			SimpleDateFormat format = DisplayType.getDateFormat(DisplayType.Date);
			java.util.Date date = format.parse(data);
			if (date != null)
				return new Timestamp (date.getTime());
		}
		catch (Exception e)
		{
		}
		
		//	JDBC Format
		try 
		{
			return Timestamp.valueOf(data);
		}
		catch (Exception e) 
		{
		}
		
		log.warning(parameter + " - cannot parse: " + data);
		return null;
	}   //  getParameterAsDate

	/**
	 *  Get boolean Parameter.
	 *  @param request request
	 *  @param parameter parameter
	 *  @return true if found
	 */
	public static boolean getParameterAsBoolean (HttpServletRequest request, 
		String parameter)
	{
		return getParameterAsBoolean(request, parameter, null);
	}	//	getParameterAsBoolean
	
	/**
	 *  Get boolean Parameter.
	 *  @param request request
	 *  @param parameter parameter
	 *  @param expected optional expected value
	 *  @return true if found and if optional value matches
	 */
	public static boolean getParameterAsBoolean (HttpServletRequest request, 
		String parameter, String expected)
	{
		if (request == null || parameter == null)
			return false;
		String data = getParameter(request, parameter);
		if (data == null || data.length() == 0)
			return false;
		//	Ignore actual value
		if (expected == null)
			return true;
		//
		return expected.equalsIgnoreCase(data);
	}   //  getParameterAsBoolean
	
	
	/**************************************************************************
	 *  Create Standard Response Header with optional Cookie and print document.
	 *  D:\j2sdk1.4.0\docs\guide\intl\encoding.doc.html
	 *
	 *  @param request request
	 *  @param response response
	 *  @param servlet servlet
	 *  @param cookieProperties cookie properties
	 *  @param doc doc
	 *  @param debug debug
	 *  @throws IOException
	 */
	public static void createResponse (HttpServletRequest request, HttpServletResponse response,
		HttpServlet servlet, Properties cookieProperties, WebDoc doc, boolean debug) throws IOException
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html; charset=UTF-8");

		//
		//  Update Cookie - overwrite
		if (cookieProperties != null)
		{
			Cookie cookie = new Cookie (WebEnv.COOKIE_INFO, propertiesEncode(cookieProperties));
			cookie.setComment("(c) Medsys - Expert Sistemas Computacionales, S. A. de C. V. - Jorg Janke"); //EXPERT: mrojas
			cookie.setSecure(false);
			cookie.setPath("/");
			if (cookieProperties.size() == 0)
				cookie.setMaxAge(0);            //  delete cookie
			else
				cookie.setMaxAge(2592000);      //  30 days in seconds   60*60*24*30
			response.addCookie(cookie);
		}
		//  add diagnostics
		if (debug && WebEnv.DEBUG)
		{
		//	doc.output(System.out);
			WebEnv.addFooter(request, response, servlet, doc.getBody());
		//	doc.output(System.out);
		}
	//	String content = doc.toString();
	//  response.setContentLength(content.length());    //  causes problems at the end of the output

		//  print document
		PrintWriter out = response.getWriter();     //  with character encoding support
		doc.output(out);
		out.flush();
		if (out.checkError())
			log.log(Level.SEVERE, "error writing");
		//  binary output (is faster but does not do character set conversion)
	//	OutputStream out = response.getOutputStream();
	//	byte[] data = doc.toString().getBytes();
	//	response.setContentLength(data.length);
	//	out.write(doc.toString().getBytes());
		//
		out.close();
	}   //  createResponse

	
	/**************************************************************************
	 *  Create Java Script to clear Target frame
	 *
	 *  @param targetFrame target frame
	 *  @return Clear Frame Script
	 */
	public static script getClearFrame (String targetFrame)
	{
		StringBuffer cmd = new StringBuffer();
		cmd.append("<!-- clear frame\n")
			.append("var d = parent.").append(targetFrame).append(".document;\n")
			.append("d.open();\n")
			.append("d.write('<link href=\"").append(WebEnv.getStylesheetURL()).append("\" rel=\"stylesheet\">');\n");
			if (targetFrame.equalsIgnoreCase(WebEnv.TARGET_WINDOW)){ 
			    cmd.append("d.write('<html><head></head><body bgColor=\"#FFFFFF\">');\n")//EXPERT..twry
			    .append("d.write('<br><br><br><br><br><br><br><br><br><br><div ALIGN=\"center\">');\n")//EXPERT..twry
			    .append("d.write('<table width=\"100%\"><tr> <td width=\"100%\" align=\"center\">');\n")//EXPERT..twry
			    //.append("d.write('<IMG SRC=\"/compiere/Background.gif\" WIDTH=\"310\" HEIGHT=\"300\" aling = \"center\" display=\"block\">');\n")//EXPERT..twry
			    .append("d.write('</td></tr></table></div>');\n")//EXPERT..twry
			    .append("d.write('</body></html>');\n");//EXPERT..twry
			}
			cmd.append("d.close();\n")
			.append("// -- clear frame -->");
		//
		return new script(cmd.toString());
	}   //  getClearFrame


	/**
	 * 	Return a link and script with new location.
	 * 	@param url forward url
	 * 	@param delaySec delay in seconds (default 3)
	 * 	@return html
	 */
	public static HtmlCode getForward (String url, int delaySec)
	{
		if (delaySec <= 0)
			delaySec = 3;
		HtmlCode retValue = new HtmlCode();
		//	Link
		a a = new a(url);
		a.addElement(url);
		retValue.addElement(a);
		//	Java Script	- document.location - 
		script script = new script("setTimeout(\"window.top.location.replace('" + url 
			+ "')\"," + (delaySec+1000) + ");");
		retValue.addElement(script);
		//
		return retValue;
	}	//	getForward

	/**
	 * 	Create Forward Page
	 * 	@param response response
	 * 	@param title page title
	 * 	@param forwardURL url
	 * 	@param delaySec delay in seconds (default 3)
	 * 	@throws ServletException
	 * 	@throws IOException
	 */
	public static void createForwardPage (HttpServletResponse response, Properties ctx,
		String title, String forwardURL, int delaySec) throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8");
		WebDoc doc = WebDoc.create(ctx, title);
		body b = doc.getBody();
		b.addElement(getForward(forwardURL, delaySec));
		PrintWriter out = response.getWriter();
		doc.output(out);
		out.flush();
		if (out.checkError())
			log.log(Level.SEVERE, "Error writing");
		out.close();
		log.fine(forwardURL + " - " + title);
	}	//	createForwardPage


	/**
	 * 	Does Test exist
	 *	@param test string
	 *	@return true if String with data
	 */
	public static boolean exists (String test)
	{
		if (test == null)
			return false;
		return test.length() > 0;
	}	//	exists

	/**
	 * 	Does Parameter exist
	 * 	@param request request
	 *	@param parameter string
	 *	@return true if String with data
	 */
	public static boolean exists (HttpServletRequest request, String parameter)
	{
		if (request == null || parameter == null)
			return false;
		try
		{
			String enc = request.getCharacterEncoding();
			if (enc == null)
				request.setCharacterEncoding(WebEnv.ENCODING);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Set CharacterEncoding=" + WebEnv.ENCODING, e);
		}
		return exists (request.getParameter(parameter));
	}	//	exists


	/**
	 *	Is EMail address valid
	 * 	@param email mail address
	 * 	@return true if valid
	 */
	public static boolean isEmailValid (String email)
	{
		if (email == null || email.length () == 0)
			return false;
		try
		{
			InternetAddress ia = new InternetAddress (email, true);
			return true;
		}
		catch (AddressException ex)
		{
			log.warning (email + " - "
				+ ex.getLocalizedMessage ());
		}
		return false;
	}	//	isEmailValid


	/**************************************************************************
	 *  Decode Properties into String (URL encoded)
	 *
	 *  @param pp properties
	 *  @return Encoded String
	 */
	public static String propertiesEncode (Properties pp)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try
		{
			pp.store(bos, "Compiere");   //  Header
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, "store", e);
		}
		String result = new String (bos.toByteArray());
	//	System.out.println("String=" + result);
		try
		{
			result = URLEncoder.encode(result, WebEnv.ENCODING);
		}
		catch (UnsupportedEncodingException e)
		{
			log.log(Level.SEVERE, "encode" + WebEnv.ENCODING, e);
			String enc = System.getProperty("file.encoding");      //  Windows default is Cp1252
			try
			{
				result = URLEncoder.encode(result, enc);
				log.info("encode: " + enc);
			}
			catch (Exception ex)
			{
				log.log(Level.SEVERE, "encode", ex);
			}
		}
	//	System.out.println("String-Encoded=" + result);
		return result;
	}   //  propertiesEncode

	/**
	 *  Decode data String (URL encoded) into Properties
	 *
	 *  @param data data
	 *  @return Properties
	 */
	public static Properties propertiesDecode (String data)
	{
		String result = null;
	//	System.out.println("String=" + data);
		try
		{
			result = URLDecoder.decode(data, WebEnv.ENCODING);
		}
		catch (UnsupportedEncodingException e)
		{
			log.log(Level.SEVERE, "decode" + WebEnv.ENCODING, e);
			String enc = System.getProperty("file.encoding");      //  Windows default is Cp1252
			try
			{
				result = URLEncoder.encode(data, enc);
				log.log(Level.SEVERE, "decode: " + enc);
			}
			catch (Exception ex)
			{
				log.log(Level.SEVERE, "decode", ex);
			}
		}
	//	System.out.println("String-Decoded=" + result);

		ByteArrayInputStream bis = new ByteArrayInputStream(result.getBytes());
		Properties pp = new Properties();
		try
		{
			pp.load(bis);
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, "load", e);
		}
		return pp;
	}   //  propertiesDecode

	
	/**************************************************************************
	 *  Convert Array of NamePair to HTTP Option Array.
	 *  <p>
	 *  If the ArrayList does not contain NamePairs, the String value is used
	 *  @see org.compiere.util.NamePair
	 *  @param  list    ArrayList containing NamePair values
	 *  @param  default_ID  Sets the default if the key/ID value is found.
	 *      If the value is null or empty, the first value is selected
	 *  @return Option Array
	 */
	public static option[] convertToOption (NamePair[] list, String default_ID)
	{
		int size = list.length;
		option[] retValue = new option[size];
		for (int i = 0; i < size; i++)
		{
			boolean selected = false;
			//  select first entry
			if (i == 0 && (default_ID == null || default_ID.length() == 0))
				selected = true;

			//  Create option
			String name = Util.maskHTML(list[i].getName());
			retValue[i] = new option(list[i].getID()).addElement(name);

			//  Select if ID/Key is same as default ID
			if (default_ID != null && default_ID.equals(list[i].getID()))
				selected = true;
			retValue[i].setSelected(selected);
		}
		return retValue;
	}   //  convertToOption

	
	/**
	 *  Create label/field table row
	 *
	 *  @param line - null for new line (table row)
	 *  @param FORMNAME form name
	 *  @param PARAMETER parameter name
	 *  @param labelText label
	 *  @param inputType HTML input type
	 *  @param value data value
	 *  @param sizeDisplay display size
	 *  @param size data size
	 *  @param longField field spanning two columns
	 *  @param mandatory mark as mandatory
	 *  @param onChange onChange call
	 *  @param script script
	 *  @return tr table row
	 */
	static public tr createField (tr line, String FORMNAME, String PARAMETER,
		String labelText, String inputType, Object value,
		int sizeDisplay, int size, boolean longField, 
		boolean mandatory, String onChange, StringBuffer script)
	{
		if (line == null)
			line = new tr();
		String labelInfo = labelText;
		if (mandatory)
		{
			labelInfo += "&nbsp;<font color=\"red\">*</font>";
			String fName = "document." + FORMNAME + "." + PARAMETER;
			script.append(fName).append(".required=true; ");
		}

		label llabel = new label().setFor(PARAMETER).addElement(labelInfo);
		llabel.setID("ID_" + PARAMETER + "_Label");
	//	label.setTitle(description);
		line.addElement(new td().addElement(llabel).setAlign(AlignType.RIGHT));
		input iinput = new input(inputType, PARAMETER, value == null ? "" : value.toString());
		iinput.setSize(sizeDisplay).setMaxlength(size);
		iinput.setID("ID_" + PARAMETER);
		if (onChange != null && onChange.length() > 0)
			iinput.setOnChange(onChange);
		iinput.setTitle(labelText);
		td field = new td().addElement(iinput).setAlign(AlignType.LEFT);
		if (longField)
			field.setColSpan(3);
		line.addElement(field);
		return line;
	}   //  addField

	/**
	 * 	Get Close PopUp Buton
	 *	@return button
	 */
	public static input createClosePopupButton()
	{
	    //Expert.- Lama
        String AD_Language = Env.getAD_Language(Env.getCtx());
        boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, "AD_Ref_List");

        //Expert .-Lama
        String name = "";
        if (isBaseLanguage){
           name = "Close";
        }else if (AD_Language.equalsIgnoreCase("es_MX")){
           name = "Cerrar";
        }//Expert .-Lama
        
        input close = null;
        
        close = new input (input.TYPE_BUTTON, "closePopup", name);
    	close.setTitle ("Close PopUp");	//	Help
               
        close.setOnClick ("closePopup();return false;");
		return close;
	}	//	getClosePopupButton
	
	
	/**
	 * 	Stream Attachment Entry
	 *	@param response response
	 *	@param attachment attachment
	 *	@param attachmentIndex logical index
	 *	@return error message or null
	 */
	public static String streamAttachment (HttpServletResponse response, 
		MAttachment attachment, int attachmentIndex)
	{
		if (attachment == null)
			return "No Attachment";
		
		int realIndex = -1;
		MAttachmentEntry[] entries = attachment.getEntries();
		for (int i = 0; i < entries.length; i++)
		{
			MAttachmentEntry entry = entries[i];
			if (entry.getIndex() == attachmentIndex)
			{
				realIndex = i;
				break;
			}
		}
		if (realIndex < 0)
		{
			log.fine("No Attachment Entry for Index=" 
				+ attachmentIndex + " - " + attachment);
			return "Attachment Entry not found";
		}
		
		MAttachmentEntry entry = entries[realIndex];
		if (entry.getData() == null)
		{
			log.fine("Empty Attachment Entry for Index=" 
				+ attachmentIndex + " - " + attachment);
			return "Attachment Entry empty";
		}
		
		//	Stream Attachment Entry
		try
		{
			int bufferSize = 2048; //	2k Buffer
			int fileLength = entry.getData().length;
			//
			response.setContentType(entry.getContentType());
			response.setBufferSize(bufferSize);
			response.setContentLength(fileLength);
			//
			log.fine(entry.toString());
			long time = System.currentTimeMillis();		//	timer start
			//
			ServletOutputStream out = response.getOutputStream ();
			out.write (entry.getData());
			out.flush();
			out.close();
			//
			time = System.currentTimeMillis() - time;
			double speed = (fileLength/1024) / ((double)time/1000);
			log.info("Length=" 
				+ fileLength + " - " 
				+ time + " ms - " 
				+ speed + " kB/sec - " + entry.getContentType());
		}
		catch (IOException ex)
		{
			log.log(Level.SEVERE, ex.toString());
			return "Streaming error - " + ex;
		}
		return null;
	}	//	streamAttachment

	
	/**
	 * 	Stream File
	 *	@param response response
	 *	@param file file to stream
	 *	@return error message or null
	 */
	public static String streamFile (HttpServletResponse response, File file)
	{
		if (file == null)
			return "No File";
		if (!file.exists())
			return "File not found: " + file.getAbsolutePath();
		
		MimeType mimeType = MimeType.get(file.getAbsolutePath());
		//	Stream File
		try
		{
			int bufferSize = 2048; //	2k Buffer
			int fileLength = (int)file.length();
			//
			response.setContentType(mimeType.getMimeType());
			response.setBufferSize(bufferSize);
			response.setContentLength(fileLength);
			//
			log.fine(file.toString());
			long time = System.currentTimeMillis();		//	timer start
			//	Get Data
			FileInputStream in = new FileInputStream(file);
			ServletOutputStream out = response.getOutputStream ();
			int c = 0;
			while ((c = in.read()) != -1)
				out.write(c);
			//
			out.flush();
			out.close();
			in.close();
			//
			time = System.currentTimeMillis() - time;
			double speed = (fileLength/1024) / ((double)time/1000);
			log.info("Length=" 
				+ fileLength + " - " 
				+ time + " ms - " 
				+ speed + " kB/sec - " + mimeType);
		}
		catch (IOException ex)
		{
			log.log(Level.SEVERE, ex.toString());
			return "Streaming error - " + ex;
		}
		return null;
	}	//	streamFile
	
	/**
	 *  Create Error Message
	 *
	 *  @param request request
	 *  @param response response
	 *  @param servlet servlet
	 *  @param message message
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public static void createErrorPage (HttpServletRequest request, HttpServletResponse response, Properties ctx,
		HttpServlet servlet, String message) 
		throws ServletException, IOException
	{
		log.info( message);
	  	WebSessionCtx wsc = WebSessionCtx.get(request);
		String windowTitle = "Error";
		if (wsc != null)
			windowTitle = Msg.getMsg(wsc.ctx, "Error");
		if (message != null)
			windowTitle += ": " + message;

		WebDoc doc = WebDoc.create (ctx, windowTitle);

		//	Body
		body b = doc.getBody();

		b.addElement(new p(servlet.getServletName(), AlignType.CENTER));
		b.addElement(new br());

		//	fini
		createResponse (request, response, servlet, null, doc, true);
	}   //  createErrorPage
	
    /**
     * 	reload
     *	@param logMessage
     *	@param jsp
     *	@param session
     *	@param request
     *	@param response
     *	@param thisContext
     *	@throws ServletException
     *	@throws IOException
     */
    public static void reload(String logMessage, String jsp, HttpSession session, HttpServletRequest request, HttpServletResponse response, ServletContext thisContext)
            throws ServletException, IOException
    {
        session.setAttribute(WebSessionCtx.HDR_MESSAGE, logMessage);
        log.warning(" - " + logMessage + " - update not confirmed");
        thisContext.getRequestDispatcher(jsp).forward(request, response);
    }
    
	/**
	 *  Create Timeout Message
	 *
	 *  @param request request
	 *  @param response response
	 *  @param servlet servlet
	 *  @param message - optional message
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public static void createTimeoutPage (HttpServletRequest request, HttpServletResponse response, Properties ctx,
		HttpServlet servlet, String message) throws ServletException, IOException
	{
		log.info(message);
	  	WebSessionCtx wsc = WebSessionCtx.get(request);
		String windowTitle = "Timeout";
		if (wsc != null)
			windowTitle = Msg.getMsg(wsc.ctx, "Timeout");

		WebDoc doc = WebDoc.create (ctx, windowTitle);

		//	Body
		body body = doc.getBody();
		//  optional message
		if (message != null && message.length() > 0)
			body.addElement(new p(message, AlignType.CENTER));

		//  login button
		body.addElement(getLoginButton(wsc == null ? null : wsc.ctx));

		//
		body.addElement(new hr());
		body.addElement(new small(servlet.getClass().getName()));
		//	fini
		createResponse (request, response, servlet, null, doc, false);
	}   //  createTimeoutPage
	
	/**
	 * 	Get Close PopUp Buton
	 *	@return button
	 */
	public static input createClosePopupButton(Properties ctx)
	{
		String text = "Close";
		if (ctx != null)
			text = Msg.getMsg (ctx, "Close");
		
		input close = new input("button", text, "  "+text);		
		close.setID(text);
		close.setClass("closebtn");		
		close.setTitle ("Close PopUp");	//	Help
		//close.setOnClick ("closePopup();return false;");
		close.setOnClick ("self.close();return false;");
		return close;
	}	//	getClosePopupButton
	
}   //  WUtil
