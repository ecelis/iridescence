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

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.ecs.AlignType;
import org.apache.ecs.Element;
import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.b;
import org.apache.ecs.xhtml.body;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.h1;
import org.apache.ecs.xhtml.head;
import org.apache.ecs.xhtml.html;
import org.apache.ecs.xhtml.img;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.link;
import org.apache.ecs.xhtml.meta;
import org.apache.ecs.xhtml.script;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.title;
import org.apache.ecs.xhtml.tr;
import org.compiere.Compiere;


/**
 *  XHTML Document.
 *
 *  @author Jorg Janke
 *  @version  $Id: WebDoc.java,v 1.5 2006/08/23 00:54:39 mrojas Exp $
 */
public class WebDoc
{
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (WebDoc.class);
	/**
	 *  Create styled Document with Title
	 *  @param plain if true adds standard.css and standard.js
	 *  @param title optional header title and h1 
	 *  @param javaClient true if Java Client - browser otherwise
	 *  @return Document
	 */
	public static WebDoc create (Properties ctx, boolean plain, String title, boolean javaClient)
	{
		WebDoc doc = new WebDoc();
		doc.setUp (ctx, plain, javaClient, title);
		return doc;
	}   //  create
	
	/**
	 *  Create Document
	 *  @param plain if true adds stylesheet and standard js
	 *  @return Document
	 */
	public static WebDoc create (Properties ctx, boolean plain)
	{
		return create (ctx, plain, null, false);
	}   //  create
	
	/**
	 *  Create styled popup Document with Title
	 *  @param title header title and h1 
	 *  @return Document
	 */
	/*public static WebDoc createPopup (String title)
	{
		WebDoc doc = create (title);
		doc.getHead().addElement(new script((Element)null, "window.js"));
		doc.getHead().addElement(new link("popup.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.setClasses ("popupTable", "popupHeader");
		doc.getTable().setCellSpacing(5);
		return doc;
	}   //  createPopup
	*/
	
	/**
	 *  Create styled popup Document with Title
	 *  @param title header title and h1 
	 *  @return Document
	 */
	public static WebDoc createPopup (Properties ctx, String title)
	{
		WebDoc doc = create (ctx, title);
		doc.getHead().addElement(new script((Element)null, "/compiere/js/window.js"));
		//doc.getHead().addElement(new script((Element)null, "/adempiere/js/Calendar-setup.js"));
		doc.getHead().addElement(new script((Element)null, "/compiere/js/calendar.js"));
		doc.getHead().addElement(new script((Element)null, "/compiere/js/table.js"));
		doc.getHead().addElement(new script((Element)null, "/compiere/lang/calendar-en.js"));
		doc.getHead().addElement(new link("/compiere/css/window.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.getHead().addElement(new link("/compiere/css/popup.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.getHead().addElement(new link("/compiere/css/table.css", link.REL_STYLESHEET, link.TYPE_CSS));		
		doc.getHead().addElement(new link("/compiere/css/calendar-blue.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.setClasses ("popupTable", "popupHeader");
		doc.getTable().setCellSpacing(0);
		
		return doc;
	}   //  createPopup

	/**
	 *  Create styled window Document with Title
	 *  @param title header title and h1 
	 *  @return Document
	 */
	/*public static WebDoc createWindow (String title)
	{
		WebDoc doc = create (title);
		doc.getHead().addElement(new link("window.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.getHead().addElement(new script((Element)null, "window.js"));
		//expert: hassan : agregamos el script de busqueda
		doc.getHead().addElement(new script((Element)null, "buscar.js"));
		//expert: hassan : agregamos el script de busqueda
		doc.setClasses ("windowTable", "windowHeader");
		doc.getTable().setCellSpacing(5);
		return doc;
	}   //  createWindow
	*/
	
	/**
	 *  Create styled window Document with Title
	 *  @param title header title and h1 
	 *  @return Document
	 */
	public static WebDoc createWindow (Properties ctx, String title)
	{
		WebDoc doc = create (ctx, title);
		
		doc.getHead().addElement(new script((Element)null, "/" + Compiere.CONTEXT_NAME + "/js/window.js"));
		//doc.getHead().addElement(new script((Element)null, "/adempiere/js/Calendar-setup.js"));
		doc.getHead().addElement(new script((Element)null, "/" + Compiere.CONTEXT_NAME + "/js/calendar.js"));
		doc.getHead().addElement(new script((Element)null, "/" + Compiere.CONTEXT_NAME + "/js/table.js"));
		doc.getHead().addElement(new script((Element)null, "/" + Compiere.CONTEXT_NAME + "/lang/calendar-en.js"));
		doc.getHead().addElement(new link("/" + Compiere.CONTEXT_NAME + "/css/window.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.getHead().addElement(new link("/" + Compiere.CONTEXT_NAME + "/css/calendar-blue.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.getHead().addElement(new link("/" + Compiere.CONTEXT_NAME + "/css/table.css", link.REL_STYLESHEET, link.TYPE_CSS));		
		doc.setClasses ("windowTable", "windowHeader");
		doc.getTable().setCellSpacing(0);
		return doc;
	}   //  createWindow

	/**
	 *  Create styled web Document with Title
	 *  @param title optional header title and h1 
	 *  @return Document
	 */
	public static WebDoc create (Properties ctx, String title)
	{
		return create (ctx, false, title, false);
	}   //  create

	/** Non brealing Space					*/
	public static final String	NBSP	= "&nbsp;";
	
	
	/**************************************************************************
	 *  Create new XHTML Document structure
	 */
	private WebDoc ()
	{
	}   //  WDoc

	private html    m_html = new html();
	private head    m_head = new head();
	private td	m_topMenu = null;//Expert:Hassan - paral el boton de control del menu.
	private body    m_body = new body();
	private table	m_table = null;
	private tr		m_topRow = null;
	private td		m_topRight = null;
	private td		m_topLeft = null;

	/**
	 *  Set up Document
	 *  @param plain if true adds stylesheet and standard js
	 *  @param javaClient true if Java Client - browser otherwise
	 *  @param title header title and h1
	 */
	private void setUp (Properties ctx, boolean plain, boolean javaClient, String title)
	{
		m_html.addElement(m_head);
		m_html.addElement(m_body);
		m_body.addElement(new a().setName("top"));
		if (title != null)
			m_head.addElement(new title(title));
		if (plain)
			return;
		
		//	css, js
		if (javaClient)
			m_head.addElement(new link("/standard.css", link.REL_STYLESHEET, link.TYPE_CSS));
		else
		{
			m_head.addElement(new link(WebEnv.getStylesheetURL(), link.REL_STYLESHEET, link.TYPE_CSS));
			m_head.addElement(new script((Element)null, WebEnv.getBaseDirectory("standard.js")));
		}
		m_head.addElement(new meta().setHttpEquiv("Content-Type", "text/html; charset=UTF-8"));
		m_head.addElement(new meta().setName("description", "eCareSoft"));

		m_table = new table("0", "2", "0", "100%", null);	//	spacing 2
		//Expert:Hassan - Boton para expandir y recoger el menu.
		/*m_topMenu = new td();
		m_topMenu.setAlign("left");
		m_topMenu.setWidth(20);
		
		img m_imgMenu = new img("/compiere/images/showMenu.png");
		m_imgMenu.setID("menuImg");
		
		Locale locale = Utilerias.getLocale(ctx); //EXPERT:Lama.- idioma
		
		m_imgMenu.setTitle(Utilerias.getMessage(ctx, locale,"imag.showMenu"));//EXPERT:Lama.- etiqueta segun el idioma
		m_imgMenu.setAlt(Utilerias.getMessage(ctx, locale,"imag.showMenu"));//EXPERT:Lama.- etiqueta segun el idioma
		m_imgMenu.setOnClick("toggle(globalAction);");
		m_imgMenu.setBorder(0);
		
		m_topMenu.addElement(m_imgMenu);*/
		
		//Expert:Hassan - fin.
		m_topRow = new tr();
		//	Title
		m_topLeft = new td();
		if (title == null)
			m_topLeft.addElement(NBSP);
		else
			m_topLeft.addElement(new h1(title));
		if(title != null)//Expert:Hassan - Boton de control de menu.
			m_topRow.addElement(m_topMenu);//Expert:Hassan - Boton de control de menu.
		m_topRow.addElement(m_topLeft);
		//	Logo
		m_topRight = new td().setAlign("right");
		/** Removing/modifying the Compiere logo is a violation of the license	*/
		/*if (javaClient)
			m_topRight.addElement(new img("http://www.compiere.org/images/Compiere64x32.png")
				//	Changing the copyright notice in any way violates the license 
				//	and you'll be held liable for any damage claims
				.setAlign(AlignType.RIGHT).setAlt("&copy; Jorg Janke/Compiere"));
		else
			m_topRight.addElement(WebEnv.getLogo());*/
		m_topRow.addElement(m_topRight);
		m_table.addElement(m_topRow);
		//
		m_body.addElement(m_table);
	}   //  setUp

	/**
	 * 	Set css Classes
	 *	@param tableClass optional class for table
	 *	@param tdClass optional class for left/right td
	 */
	public void setClasses (String tableClass, String tdClass)
	{
		if (m_table != null && tableClass != null)
			m_table.setClass(tableClass);
		if (m_topLeft != null && tdClass != null)
			m_topLeft.setClass(tdClass);
		if (m_topRight != null && tdClass != null)
			m_topRight.setClass(tdClass);
	}	//	setClasses

	
	/**
	 *  Get Body
	 *  @return Body
	 */
	public body getBody()
	{
		return m_body;
	}   //  getBody

	/**
	 *  Get Head
	 *  @return Header
	 */
	public head getHead()
	{
		return m_head;
	}   //  getHead

	/**
	 * 	Get Table (no class set)
	 *	@return table
	 */
	public table getTable()
	{
		return m_table;
	}	//	getTable

	/**
	 * 	Get Table Row (no class set)
	 *	@return table row
	 */
	public tr getTopRow()
	{
		return m_topRow;
	}	//	getTopRow
	/**
	 * 	Get Table Data Left (no class set)
	 *	@return table data
	 */
	public td getTopLeft()
	{
		return m_topLeft;
	}	//	getTopLeft
	
	/**
	 * 	Get Table Data Right (no class set)
	 *	@return table data
	 */
	public td getTopRight()
	{
		return m_topRight;
	}	//	getTopRight
	
	/**
	 *  String representation
	 *  @return String
	 */
	public String toString()
	{
		return m_html.toString();
	}   //  toString

	/**
	 *  Output Document
	 *  @param out out
	 */
	public void output (OutputStream out)
	{
		m_html.output(out);
	}   //  output

	/**
	 *  Output Document
	 *  @param out out
	 */
	public void output (PrintWriter out)
	{
		m_html.output(out);
	}   //  output

	/**
	 * 	Add Popup Center
	 * 	@param nowrap set nowrap in td
	 *	@return null or center single td
	 */
	public td addPopupCenter(boolean nowrap)
	{
		if (m_table == null)
			return null;
		//
		td center = new td ("popupCenter", AlignType.CENTER, AlignType.MIDDLE, nowrap);
		center.setColSpan(2);
		m_table.addElement(new tr()
			.addElement(center));
		return center;
	}	//	addPopupCenter

	/**
	 * 	Add Popup Close Footer
	 *	@return null or array with left/right td
	 */
	public td[] addPopupClose()
	{
		input button = WebUtilParent.createClosePopupButton(); 
		if (m_table == null)
		{
			m_body.addElement(button);
			return null;
		}
		//
		td left = new td("popupFooter", AlignType.LEFT, AlignType.MIDDLE, false, null);
		td right = new td("popupFooter", AlignType.RIGHT, AlignType.MIDDLE, false, button); 
		m_table.addElement(new tr()
			.addElement(left)
			.addElement(right));
		return new td[] {left, right};
	}	//	addPopupClose
	
	/**
	 * 	Add Popup Close Footer
	 *	@return null or array with left/right td
	 */
	public td[] addPopupClose(Properties ctx)
	{
		input button = WebUtilParent.createClosePopupButton(ctx); 
		if (m_table == null)
		{
			m_body.addElement(button);
			return null;
		}
		//
		td left = new td("popupFooter", AlignType.LEFT, AlignType.MIDDLE, false, null);
		td right = new td("popupFooter", AlignType.RIGHT, AlignType.MIDDLE, false, button); 
		m_table.addElement(new tr()
			.addElement(left)
			.addElement(right));
		return new td[] {left, right};
	}	//	addPopupClose
	

	/**
	 * 	Add Window Center
	 * 	@param nowrap set nowrap in td
	 *	@return empty single center td
	 */
	public td addWindowCenter(boolean nowrap)
	{
		if (m_table == null)
			return null;
		//
		td center = new td ("windowCenter", AlignType.CENTER, AlignType.MIDDLE, nowrap);
		center.setColSpan(2);
		m_table.addElement(new tr()
			.addElement(center));
		return center;
	}	//	addWindowCenter

	/**
	 * 	Add Window Footer
	 *	@return null or array with empty left/right td
	 */
	public td[] addWindowFooters()
	{
		if (m_table == null)
			return null;
		//
		td left = new td("windowFooter", AlignType.LEFT, AlignType.MIDDLE, false);
		td right = new td("windowFooter", AlignType.RIGHT, AlignType.MIDDLE, false); 
		m_table.addElement(new tr()
			.addElement(left)
			.addElement(right));
		return new td[] {left, right};
	}	//	addWindowFooters

	/**
	 * 	Add Window Footer
	 *	@return empty single center td
	 */
	public td addWindowFooter()
	{
		if (m_table == null)
			return null;
		//
		td center = new td("windowFooter", AlignType.CENTER, AlignType.MIDDLE, false);
		m_table.addElement(new tr()
			.addElement(center));
		return center;
	}	//	addWindowFooter
	
	/**************************************************************************
	 *  Test Class
	 *  @param args args
	 */
	public static void main (String[] args)
	{
		WebDoc doc = WebDoc.create(new Properties(),"Test");
		doc.getBody().addElement(new b("111 <<< >>> &&& \\\\ \u0100 �"));
		form f = new form("myaction");
		f.addElement(new input());
		doc.getBody().addElement(f);
		s_log.log(Level.SEVERE,doc.toString());
		//System.out.println(doc.toString());
		s_log.log(Level.INFO,"---------");
		//System.out.println("---------");
		doc.output(System.out);
		s_log.log(Level.INFO,"---------");
		//System.out.println("---------");
	}   //  main
}   //  WDoc
