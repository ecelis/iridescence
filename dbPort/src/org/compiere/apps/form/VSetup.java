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
package org.compiere.apps.form;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;

import org.apache.commons.io.FileUtils;
import org.compiere.apps.*;
import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.print.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Setup System
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VSetup.java,v 1.4 2006/08/23 00:33:36 mrojas Exp $
 *  @deprecated Swing client no used
 */
public class VSetup extends CPanel
	implements FormPanel, ActionListener, Runnable
{
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		log.info( "VSetup.init");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try
		{
			jbInit();
			dynInit();
			frame.getContentPane().add(centerPane, BorderLayout.CENTER);
			frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "VSetup.init", e);
		}
	}	//	init

	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;

	/*  Natural Account file    */
	private File        m_file = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VSetup.class);
	
	//
	private JScrollPane centerPane = new JScrollPane();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private CPanel centerPanel = new CPanel();
	private GridBagLayout centerLayout = new GridBagLayout();
	private JLabel lClientName = new JLabel();
	private JTextField fClientName = new JTextField();
	private JLabel lOrgName = new JLabel();
	private JTextField fOrgName = new JTextField();
	private JLabel lCurrency = new JLabel();
	private JComboBox fCurrency = new JComboBox();
	private JLabel lUserClient = new JLabel();
	private JTextField fUserClient = new JTextField();
	private JLabel lUserOrg = new JLabel();
	private JTextField fUserOrg = new JTextField();
	private JCheckBox fProject = new JCheckBox();
	private JCheckBox fProduct = new JCheckBox();
	private JCheckBox fBPartner = new JCheckBox();
	private JLabel lAccountSeg = new JLabel();
	private JCheckBox fMCampaign = new JCheckBox();
	private JCheckBox fSRegion = new JCheckBox();
	private JButton buttonLoadAcct = new JButton();
	private JLabel lCountry = new JLabel();
	private JLabel lCity = new JLabel();
	private JComboBox fCountry = new JComboBox();
	private JTextField fCity = new JTextField();
	private JLabel lRegion = new JLabel();
	private JComboBox fRegion = new JComboBox();

	//Expert : Lama // ask for area type - create initial service station
	private JLabel lAreaType = new JLabel();
	private JComboBox fAreaType = new JComboBox();
	
	/**
	 *	Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(this);
		centerPanel.setLayout(centerLayout);
		String optional = Msg.translate(Env.getCtx(), "Optional");
		//
		lClientName.setLabelFor(fClientName);
		lClientName.setText(Msg.translate(Env.getCtx(), "AD_Client_ID"));
	//	lClientName.setToolTipText("");
		fClientName.setText("empresa"); //EXPERT: mrojas
		fClientName.setColumns(20);
		//
		lOrgName.setLabelFor(fOrgName);
		lOrgName.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		fOrgName.setText("organizacion"); //EXPERT: mrojas
		fOrgName.setColumns(20);
		//
		lCurrency.setLabelFor(fCurrency);
		lCurrency.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
	//	lCurrency.setToolTipText("");
		//
		lUserClient.setLabelFor(fUserClient);
		lUserClient.setText(Msg.parseTranslation(Env.getCtx(), "@AD_User_ID@ @AD_Client_ID@"));
	//	lUserClient.setToolTipText("User name for client level access");
		fUserClient.setText("adminEmpresa"); //EXPERT: mrojas
		fUserClient.setColumns(20);
		//
		lUserOrg.setLabelFor(fUserOrg);
		lUserOrg.setText(Msg.parseTranslation(Env.getCtx(), "@AD_User_ID@ @AD_Org_ID@"));
	//	lUserOrg.setToolTipText("");
		fUserOrg.setText("usuarioEmpresa"); //EXPERT: mrojas
		fUserOrg.setColumns(20);
		//
		lCountry.setLabelFor(fCountry);
		lCountry.setText(Msg.translate(Env.getCtx(), "C_Country_ID"));
		lCity.setLabelFor(fCity);
		lCity.setText(Msg.translate(Env.getCtx(), "C_City_ID"));
		fCity.setText("ciudad"); //EXPERT: mrojas
		fCity.setColumns(20);
		lRegion.setLabelFor(fRegion);
		lRegion.setText(Msg.translate(Env.getCtx(), "C_Region_ID"));
		lRegion.setToolTipText(optional);
		//
		lAccountSeg.setText(optional);
		fBPartner.setSelected(true);
		fBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fProduct.setSelected(true);
		fProduct.setText(Msg.translate(Env.getCtx(), "M_Product_ID"));
		fProject.setText(Msg.translate(Env.getCtx(), "C_Project_ID"));
		fMCampaign.setText(Msg.translate(Env.getCtx(), "C_Campaign_ID"));
		fSRegion.setText(Msg.translate(Env.getCtx(), "C_SalesRegion_ID"));
		
		//areatype  - lama
		lAreaType.setLabelFor(fOrgName);
		lAreaType.setText(Msg.translate(Env.getCtx(), MEXMEArea.COLUMNNAME_TipoArea));
		//
		
		//
		buttonLoadAcct.setText(Msg.getMsg(Env.getCtx(), "LoadAccountingValues"));

		centerPane.getViewport().add(centerPanel, null);
		centerPanel.add(lClientName,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		centerPanel.add(fClientName,   new GridBagConstraints(1, 0, 4, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		centerPanel.add(lOrgName,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		centerPanel.add(fOrgName,   new GridBagConstraints(1, 1, 4, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(lUserClient,   new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		centerPanel.add(fUserClient,   new GridBagConstraints(1, 2, 4, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(lUserOrg,   new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		centerPanel.add(fUserOrg,    new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(lAccountSeg,   new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		centerPanel.add(fProject,   new GridBagConstraints(1, 9, 3, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(fBPartner,   new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(fMCampaign,   new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(fCurrency,    new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(lCurrency,   new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		centerPanel.add(buttonLoadAcct,   new GridBagConstraints(1, 11, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		centerPanel.add(lCountry,   new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		centerPanel.add(lCity,    new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		centerPanel.add(fCountry,   new GridBagConstraints(1, 5, 3, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(fCity,    new GridBagConstraints(1, 6, 3, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(fProduct,   new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
		centerPanel.add(fSRegion,   new GridBagConstraints(2, 10, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		centerPanel.add(lRegion,    new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		centerPanel.add(fRegion,  new GridBagConstraints(1, 7, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		// Lama
		centerPanel.add(lAreaType, new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));
		centerPanel.add(fAreaType, new GridBagConstraints(1, 12, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
			
	}	//	jbInit

	/**
	 *	Dynamic Init
	 */
	private void dynInit()
	{
		int paisDefault = 0; //EXPERT: mrojas
		
		//	Currency
		String sql = "SELECT C_Currency_ID, Description FROM C_Currency ORDER BY 1";	//	USD first
		try
		{
			Statement stmt = DB.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				fCurrency.addItem(new KeyNamePair(rs.getInt(1) , rs.getString(2)));
			rs.close();
			stmt.close();
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
		}
		fCurrency.setSelectedIndex(0);

		//	Country
		sql = "SELECT C_Country_ID, Name FROM C_Country ORDER BY 1";     //  US first
		try
		{
			Statement stmt = DB.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			/*while (rs.next()) //expert comentado en migracion
				fCountry.addItem(new KeyNamePair(rs.getInt(1) , rs.getString(2)));
			*/
			/* EXPERT: mrojas : pais de default mexico */
			int idCountry = 0;
			while (rs.next()) {
				idCountry = rs.getInt(1);
				KeyNamePair kp = new KeyNamePair(idCountry , rs.getString(2));
				fCountry.addItem(kp);
				if (Env.getContext(Env.getCtx(), "#C_Country_ID").equalsIgnoreCase(String.valueOf(idCountry))){
					fCountry.setSelectedItem(kp);
					paisDefault = idCountry;
				}
			}
			/* EXPERT: mrojas : pais de default mexico */
			rs.close();
			stmt.close();
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "VSetup.dynInit -country", e1);
		}
		//fCountry.setSelectedIndex(0);//EXPERT: mrojas

		//	Region (optional)
		//EXPERT: mrojas
		sql = "SELECT C_Region_ID, Name FROM C_Region " +
			"WHERE C_Country_ID = " + paisDefault + " ORDER BY C_Country_ID, Name  ";
		//EXPERT: mrojas
		//sql = "SELECT C_Region_ID, Name FROM C_Region ORDER BY C_Country_ID, Name";//expert comentado en migracion
		try
		{
			fRegion.addItem(new KeyNamePair(0, " "));
			Statement stmt = DB.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				fRegion.addItem(new KeyNamePair(rs.getInt(1) , rs.getString(2)));
			rs.close();
			stmt.close();
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "VSetup.dynInit -region", e1);
		}
		fRegion.setSelectedIndex(0);
		
		// Expert: Lama
		// Area Type
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String  AD_Language =  Env.getAD_Language(Env.getCtx());
			int refID = MEXMEArea.TIPOAREA_AD_Reference_ID;
			boolean isBaseLanguage  = Env.isBaseLanguage(AD_Language, MRefList.Table_Name);
			sql = isBaseLanguage ? "SELECT r.Value, r.Name FROM AD_Ref_List r WHERE r.AD_Reference_ID= "+refID
					: "SELECT r.Value, t.Name FROM AD_Ref_List_Trl t INNER JOIN AD_Ref_List r ON (r.AD_Ref_List_ID=t.AD_Ref_List_ID) "
							+ "WHERE r.AD_Reference_ID="+refID+" AND t.AD_Language= "+DB.TO_STRING(AD_Language);
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery(sql);
			while (rs.next())
				fAreaType.addItem(new ValueNamePair(rs.getString(1), rs.getString(2)));
			rs.close();
			pstmt.close();
		} catch (SQLException e1) {
			log.log(Level.SEVERE, "AreaType", e1);
			DB.close(rs, pstmt);
		}
		if(fAreaType.getItemCount() > 0)
			fAreaType.setSelectedIndex(0);

		//  General Listeners
		confirmPanel.addActionListener(this);
		buttonLoadAcct.addActionListener(this);
		confirmPanel.getOKButton().setEnabled(false);
	}	//	dynInit

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		//  load file
		if (e.getSource().equals(buttonLoadAcct))
			m_file = getFile();
		//  OK
		else if (e.getActionCommand().equals(ConfirmPanel.A_OK) && m_file != null)
		{
			confirmPanel.getCancelButton().setEnabled(false);
			confirmPanel.getOKButton().setEnabled(false);
			if (createSetup())
				m_frame.startBatch(this);
			else
			{
				confirmPanel.getCancelButton().setEnabled(true);
				confirmPanel.getOKButton().setEnabled(true);
			}
		}
		//  Cancel
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();
	}	//	actionPerformed

	/**
	 *  Get File for CoA
	 *  @return File
	 */
	private File getFile()
	{
		File file = null;
		String dirName = org.compiere.Compiere.getCompiereHome() + File.separator + "data" + File.separator + "import";
		log.config(dirName);
		JFileChooser chooser = new JFileChooser(dirName);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setDialogTitle(Msg.translate(Env.getCtx(), "LoadAccountingValues"));
		chooser.addChoosableFileFilter(new ExtensionFileFilter("csv", Msg.getMsg(Env.getCtx(), "FileCSV")));
		//  Try selecting file
		file = new File(dirName + File.pathSeparator + "AccountingUS.csv");
		if (file.exists())
			chooser.setSelectedFile(file);

		//  Show it
		if (chooser.showOpenDialog(this.getParent()) == JFileChooser.APPROVE_OPTION)
			file = chooser.getSelectedFile();
		else
			file = null;
		chooser = null;

		if (file == null)
			buttonLoadAcct.setText(Msg.translate(Env.getCtx(), "LoadAccountingValues"));
		else
			buttonLoadAcct.setText(file.getAbsolutePath());
		confirmPanel.getOKButton().setEnabled(file != null);
		m_frame.pack();
		return file;
	}   //  getFile

	
	/**************************************************************************
	 *	Create Setup
	 * 	@return true if created
	 */
	private boolean createSetup()
	{
		//	Change critical characters ' => "  \ => /
		fClientName.setText(fClientName.getText().replace('\'','"'));
		fClientName.setText(fClientName.getText().replace('\\','/'));
		fOrgName.setText(fOrgName.getText().replace('\'','"'));
		fOrgName.setText(fOrgName.getText().replace('\\','/'));
		fUserClient.setText(fUserClient.getText().replace('\'','"'));
		fUserClient.setText(fUserClient.getText().replace('\\','/'));
		fUserOrg.setText(fUserOrg.getText().replace('\'','"'));
		fUserOrg.setText(fUserOrg.getText().replace('\\','/'));

		//	Unique Client Name
		String SQL = "UPDATE AD_CLient SET CreatedBy=0 WHERE Name='" + fClientName.getText() + "'";
		if (DB.executeUpdate(SQL, null) != 0)
		{
			fClientName.setBackground(CompierePLAF.getFieldBackground_Error());
			ADialog.error(m_WindowNo, this, "NotUnique", lClientName.getText());
			fClientName.requestFocus();
			return false;
		}
		fClientName.setBackground(CompierePLAF.getFieldBackground_Normal());

/**
		//	Unique Org Name
		SQL = "UPDATE AD_Org SET CreatedBy=0 WHERE Value='" + fOrgName.getText() + "'";
		if (DB.executeUpdate(SQL) != 0)
		{
			fOrgName.setBackground(CompierePLAF.getFieldBackground_Error());
			ADialog.error(m_WindowNo, this, "NotUnique", lOrgName.getText());
			return false;
		}
		fOrgName.setBackground(CompierePLAF.getFieldBackground_Normal());
**/

		//	Unique User Name
		SQL = "UPDATE AD_User SET CreatedBy=0 WHERE Name='" + fUserClient.getText() + "'";
		if (DB.executeUpdate(SQL, null) != 0)
		{
			fUserClient.setBackground(CompierePLAF.getFieldBackground_Error());
			ADialog.error(m_WindowNo, this, "NotUnique", lUserClient.getText());
			fUserClient.requestFocus();
			return false;
		}
		fUserClient.setBackground(CompierePLAF.getFieldBackground_Normal());
		SQL = "UPDATE AD_User SET CreatedBy=0 WHERE Name='" + fUserOrg.getText() + "'";
		if (DB.executeUpdate(SQL, null) != 0 || fUserClient.getText().equals(fUserOrg.getText()))
		{
			fUserOrg.setBackground(CompierePLAF.getFieldBackground_Error());
			ADialog.error(m_WindowNo, this, "NotUnique", lUserOrg.getText());
			fUserOrg.requestFocus();
			return false;
		}
		fUserOrg.setBackground(CompierePLAF.getFieldBackground_Normal());

		return true;
	}	//	createSetup

	/**
	 * 	Create Setup Batch Part.
	 * 	if done - disposes window
	 */
	public void run()
	{
		String info = null;

		if(Env.getAD_Language(Env.getCtx()).equals("en_US")) {


			MSetup ms = new MSetup(Env.getCtx(), false, m_WindowNo);
			m_frame.setBusyTimer(45);

			// areatype
			String areaType = ((ValueNamePair)fAreaType.getSelectedItem()).getValue();

			//  Step 1
			boolean ok = ms.createClient(fClientName.getText(), null, fOrgName.getText(), null, fUserClient.getText(), fUserOrg.getText(), areaType, null);
			info = ms.getInfo();

			if (ok)
			{
				//  Generate Accounting
				KeyNamePair currency = (KeyNamePair)fCurrency.getSelectedItem();
				FileInputStream is = null;
				try {
					is = FileUtils.openInputStream(m_file);
				} catch (IOException e) {
					log.log(Level.SEVERE, "VSetup.run", e);
					ADialog.error(m_WindowNo, this, "AccountSetupError");
					dispose();
				}
				if (!ms.createAccounting(currency, fProduct.isSelected(), fBPartner.isSelected(), fProject.isSelected(), fMCampaign.isSelected(), fSRegion.isSelected(), false,is)) {
					ADialog.error(m_WindowNo, this, "AccountSetupError");
					dispose();
				}
				//  Generate Entities
				KeyNamePair p = (KeyNamePair)fCountry.getSelectedItem();
				int C_Country_ID = p.getKey();
				p = (KeyNamePair)fRegion.getSelectedItem();
				int C_Region_ID = p.getKey();
				ms.createEntities(C_Country_ID, fCity.getText(), C_Region_ID, currency.getKey(), 0.0, null);
				info += ms.getInfo();
				//	Create Print Documents
				PrintUtil.setupPrintForm(ms.getAD_Client_ID());
			}

		} else {
			info = Msg.translate(Env.getAD_Language(Env.getCtx()), "MustLoginBaseLanguage");
		}

		ADialog.info(m_WindowNo, this, "VSetup", info);
		dispose();
	}	//	run
	
}	//	VSetup
