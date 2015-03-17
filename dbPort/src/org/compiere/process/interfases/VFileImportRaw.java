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
package org.compiere.process.interfases;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.compiere.impexp.ImpFormat;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *	Fixed length file import
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VFileImport.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VFileImportRaw 
{

	/**	Window No			*/
	private static int         		m_WindowNo = 0;
	/**	FormFrame			*/

	
	private static ArrayList<String>	m_data = new ArrayList<String>();
	private static ImpFormat 			m_format;
	private static int					m_record = -1;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VFileImportRaw.class);
	//
	private static final String s_none = "----";	//	no format indicator
	//
	private CPanel northPanel = new CPanel();
	private JButton bFile = new JButton();
	private JComboBox pickFormat = new JComboBox();
	private CPanel centerPanel = new CPanel();
	private BorderLayout centerLayout = new BorderLayout();
	private JScrollPane rawDataPane = new JScrollPane();
	private static JTextArea rawData = new JTextArea();
	private JScrollPane previewPane = new JScrollPane();
	private CPanel previewPanel = new CPanel();
	private JLabel info = new JLabel();
	private JLabel labelFormat = new JLabel();
	private GridBagLayout previewLayout = new GridBagLayout();
	private JButton bNext = new JButton();
	private JButton bPrevious = new JButton();
	private static JLabel record = new JLabel();

	

	/**************************************************************************
	 *	Load File
	 */
	public static  void cmd_loadFile(String filename)
	{
		String directory = filename;
		log.config(directory);
		
		try
		{
			//  see NaturalAccountMap
			BufferedReader in = new BufferedReader(new FileReader(filename), 10240);
			//	not safe see p108 Network pgm
			String s = null;
			while ((s = in.readLine()) != null)
			{
				m_data.add(s);
		}
			}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		int index = 1;	//	second line as first may be heading
		if (m_data.size() == 1)
			index = 0;
		int length = 0;
		if (m_data.size() > 0)
			length = m_data.get(index).toString().length();

		log.config("Records=" + m_data.size() 
			+ ", Length=" + length);
	}	//	cmd_loadFile

	/**
	 *	Load Format
	 */
		public static void cmd_loadFormat(String theFormat)
	{
		//
		String formatName = theFormat;
		if (formatName.equals(s_none))
			return;
		m_format = ImpFormat.load (theFormat);
		if (m_format == null)
		{
			log.info("no hay formato asociado");
			return;
		}

		//	pointers
		//int size = 
			m_format.getRowCount();

		m_record = -1;
		record.setText("-");
	}	//	cmd_format

	/**
	 *	Apply Current Pattern
	 *  @param next next
	 */
	private void cmd_applyFormat (boolean next)
	{
		if (m_format == null)
			return;

		//	set position
		if (next)
			m_record++;
		else
			m_record--;
		if (m_record < 0)
			m_record = 0;
		else if (m_record >= m_data.size())
			m_record = m_data.size() - 1;
		record.setText(" " + String.valueOf(m_record+1) + " ");
		//	Line Info
		String[] lInfo = m_format.parseLine(m_data.get(m_record).toString(), false, true, false);	//	no label, trace, no ignore
		int size = m_format.getRowCount();
		if (lInfo.length != size)
			log.log(Level.SEVERE, "FormatElements=" + size + " != Fields=" + lInfo.length);

	}	//	cmd_applyFormat

	
	/**************************************************************************
	 *	Process File
	 */
	public static  void cmd_process()
	{
		if (m_format == null)
		{
			log.info(m_WindowNo +" "+"FileImportNoFormat");
			return;
		}
		log.config(m_format.getName());

		//	For all rows - update/insert DB table
		int row = 0;
		int imported = 0;
		for (row = 0; row < m_data.size(); row++)
			if (m_format.updateDB(Env.getCtx(), m_data.get(row).toString(), null))
				imported++;
		//
		log.info(m_WindowNo +" "+"FileImportR/I"+" "+ row + " / " + imported + "#");
	}	//	cmd_process

}	//	FileImport
