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
package org.compiere.apps;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import java.util.logging.*;

import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Help and HTML Window
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: Help.java,v 1.3 2006/07/30 00:51:27 jjanke Exp $
 */
public class Help extends CDialog
	implements ActionListener
{
	/**
	 *	Help System for Window Help
	 *
	 * @param frame Parent
	 * @param title Title
	 * @param mWindow Window Model
	 */
	public Help (Frame frame, String title, GridWindow mWindow)
	{
		super(frame, title, false);
		try
		{
			jbInit();
			loadInfo(mWindow);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		AEnv.positionCenterWindow(frame, this);
	}	//	Help

	/**
	 *	Help System
	 *
	 * @param frame Parent
	 * @param title Window
	 * @param url   URL to display
	 */
	public Help (Frame frame, String title, URL url)
	{
		super(frame, title, false);
		try
		{
			jbInit();
			info.setPage(url);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		AEnv.positionCenterWindow(frame, this);
	}	//	Help

	/**
	 *	Help System
	 *
	 * @param frame Parent
	 * @param title Window
	 * @param helpHtml Helptext
	 */
	public Help (Frame frame, String title, String helpHtml)
	{
		super(frame, title, false);
		try
		{
			jbInit();
			info.setContentType("text/html");
			info.setEditable(false);
			info.setBackground(CompierePLAF.getFieldBackground_Inactive());
			info.setText(helpHtml);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "Help", ex);
		}
		AEnv.positionCenterWindow(frame, this);
	}	//	Help

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(Help.class);
	
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private OnlineHelp info = new OnlineHelp();
	private JScrollPane infoPane = new JScrollPane();
	private ConfirmPanel confirmPanel = new ConfirmPanel();

	/**
	 *	Static Init
	 *
	 * @throws Exception
	 */
	void jbInit() throws Exception
	{
		mainPanel.setLayout(mainLayout);
		mainLayout.setHgap(2);
		mainLayout.setVgap(2);
		infoPane.setBorder(BorderFactory.createLoweredBevelBorder());
		infoPane.setPreferredSize(new Dimension(500, 400));
		getContentPane().add(mainPanel);
		mainPanel.add(infoPane, BorderLayout.CENTER);
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		infoPane.getViewport().add(info, null);
		confirmPanel.addActionListener(this);
	}	//	jbInit

	
	/*************************************************************************
	 *	Load Info - Windows Help
	 *  @param mWindow window model
	 */
	private void loadInfo(GridWindow mWindow)
	{
		WebDoc doc = mWindow.getHelpDoc(true);
		info.setText(doc.toString());
	}	//	loadInfo

	
	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
	}	//	actionPerformed

}	//	Help

