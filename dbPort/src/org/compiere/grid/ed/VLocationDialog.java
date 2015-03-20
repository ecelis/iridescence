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
package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import org.compiere.apps.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Dialog to enter Location Info (Address)
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VLocationDialog.java,v 1.1 2006/08/23 00:37:56 mrojas Exp $
 */
public class VLocationDialog extends CDialog implements ActionListener {
	/**
	 *	Constructor
	 *
	 * @param frame parent
	 * @param title title (field name)
	 * @param location Model Location
	 */
	public VLocationDialog(Frame frame, String title, MLocation location) {
		super(frame, title, true);
		try {
			jbInit();
		} catch (Exception ex) {
			log.log(Level.SEVERE, ex.getMessage());
		}
		m_location = location;
		if (m_location == null)
			m_location = new MLocation (Env.getCtx(), 0, null);
		//	Overwrite title	
		if (m_location.getC_Location_ID() == 0)
			setTitle(Msg.getMsg(Env.getCtx(), "LocationNew"));
		else
			setTitle(Msg.getMsg(Env.getCtx(), "LocationUpdate"));
		
		//	Current Country
		MCountry.setDisplayLanguage(Env.getAD_Language(Env.getCtx()));
		fCountry = new CComboBox(MCountry.getCountries(Env.getCtx()));
		fCountry.setSelectedItem(m_location.getCountry());
		m_origCountry_ID = m_location.getC_Country_ID();
		//	Current Region
		fRegion = new CComboBox(MRegion.getRegions(Env.getCtx(), m_origCountry_ID));
		if (m_location.getCountry().isHasRegion())
			lRegion.setText(m_location.getCountry().getRegionName());	//	name for region
		fRegion.setSelectedItem(m_location.getRegion());
		m_origRegion_ID = m_location.getC_Region_ID();
		// Para los municipios 
		//lTownCouncil.setText(m_location.getRegion().getTownCouncilName());
		fTownCouncil = new CComboBox(MTownCouncil.getTownCouncil(Env.getCtx(),m_origRegion_ID));
		fTownCouncil.setSelectedItem(m_location.getTownCouncil());
		//
		initLocation();
		fCountry.addActionListener(this);
		fRegion.addActionListener(this);
		bPostalCode.addActionListener(this);// Expert (Lama)
		AEnv.positionCenterWindow(frame, this);
	}	//	VLocationDialog

	private boolean 	m_change = false;
	private MLocation	m_location;
	private int			m_origCountry_ID;
	private int			s_oldCountry_ID = 0;
	
	// Se agregaron para trabajar con los municipios (vgarcia)
	private int			m_origRegion_ID;
	private int			s_oldRegion_ID = 0;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VLocationDialog.class);

	private CPanel panel = new CPanel();
	private CPanel mainPanel = new CPanel();
	private CPanel southPanel = new CPanel();
	private BorderLayout panelLayout = new BorderLayout();
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private BorderLayout southLayout = new BorderLayout();
	//
	private CLabel		lAddress1   = new CLabel(Msg.getMsg(Env.getCtx(), "Street")+ " 1"); //expert
	private CLabel		lAddress2   = new CLabel(Msg.getMsg(Env.getCtx(), "Colony")); //expert:colonia
	private CLabel		lAddress3   = new CLabel(Msg.getMsg(Env.getCtx(), "Street")+ " 2"); //expert
	//private CLabel		lAddress4   = new CLabel(Msg.getMsg(Env.getCtx(), "County")); //expert
	private CLabel		lCity       = new CLabel(Msg.getMsg(Env.getCtx(), "City"));
	private CLabel		lCountry    = new CLabel(Msg.getMsg(Env.getCtx(), "Country"));
	private CLabel		lRegion     = new CLabel(Msg.getMsg(Env.getCtx(), "Region"));
	private CLabel		lTownCouncil     = new CLabel(Msg.getMsg(Env.getCtx(), "Municipio")); 	//(vgarcia)
	private CLabel		lPostal     = new CLabel(Msg.getMsg(Env.getCtx(), "Postal"));
	private CLabel		lPostalAdd  = new CLabel(Msg.getMsg(Env.getCtx(), "PostalAdd"));
	private CTextField	fAddress1 = new CTextField(20);		//	length=60
	private CTextField	fAddress2 = new CTextField(20);		//	length=60
	private CTextField	fAddress3 = new CTextField(20);		//	length=60
	//private CTextField	fAddress4 = new CTextField(20);		//	length=60
	private CTextField	fCity  = new CTextField(15);		//	length=60
	private CComboBox	fCountry;
	private CComboBox	fRegion;
	private CComboBox	fTownCouncil;	//(vgarcia)
	private CTextField	fPostal = new CTextField(5);		//	length=10
	private CTextField	fPostalAdd = new CTextField(5);		//	length=10
	// Expert : Numero exterior,interior y boton de busqueda 
	// de Colonias / Codigos Postales  (Lama)
	private CLabel 		lNumExt = new CLabel(Msg.translate(Env.getCtx(), "NumExt"));
	private CLabel 		lNumInt = new CLabel(Msg.translate(Env.getCtx(), "NumIn"));
	private CTextField 	fNumExt = new CTextField(10);
	private CTextField 	fNumInt = new CTextField(10);
	private CPanel 		middlePanel = new CPanel();	//Panel para el boton
	private CButton 	bPostalCode = new CButton();
	private MEXMEColonia m_colonia = null;
	//
	private GridBagConstraints gbc = new GridBagConstraints();
	private Insets labelInsets = new Insets(2,15,2,0);		// 	top,left,bottom,right
	private Insets fieldInsets = new Insets(2,5,2,10);

	/**
	 *	Static component init
	 *  @throws Exception
	 */
	void jbInit() throws Exception {
		panel.setLayout(panelLayout);
		southPanel.setLayout(southLayout);
		mainPanel.setLayout(gridBagLayout);
		panelLayout.setHgap(5);
		panelLayout.setVgap(10);
		getContentPane().add(panel);
		panel.add(mainPanel, BorderLayout.CENTER);
		panel.add(middlePanel, BorderLayout.AFTER_LAST_LINE);//Expert (Lama)
		
		panel.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(confirmPanel, BorderLayout.NORTH);
		//
		confirmPanel.addActionListener(this);
	} // jbInit

	/**
	 *	Dynanmic Init & fill fields - Called when Country changes!
	 */
	private void initLocation() {
		MCountry country = m_location.getCountry();
		MRegion region = m_location.getRegion();
		
		log.fine(country.getName() + ", Region=" + country.isHasRegion() + " " + country.getDisplaySequence()
			+ ", C_Location_ID=" + m_location.getC_Location_ID());
		//	new Region
		if (m_location.getC_Country_ID() != s_oldCountry_ID && country.isHasRegion()) {
			fRegion = new CComboBox(MRegion.getRegions(Env.getCtx(), country.getC_Country_ID()));
			if (m_location.getRegion() != null)
				fRegion.setSelectedItem(m_location.getRegion());
			lRegion.setText(country.getRegionName());
			s_oldCountry_ID = m_location.getC_Country_ID();
		}
		
		if (m_location.getC_Region_ID() != s_oldRegion_ID && region != null && region.isHasTownCouncil()) {
			fTownCouncil = new CComboBox(MTownCouncil.getTownCouncil(Env.getCtx(), region.getC_Region_ID()));
			if (m_location.getTownCouncil() != null)
				fTownCouncil.setSelectedItem(m_location.getTownCouncil());
			//lTownCouncil.setText(region.getTownCouncilName());
			s_oldRegion_ID = m_location.getC_Region_ID();
		}

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridy = 0;			//	line
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.insets = fieldInsets;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;

		mainPanel.add(Box.createVerticalStrut(5), gbc);    	//	top gap

		int line = 1;
		addLine(line++, lAddress1, fAddress1);
		addLine(line++, lNumExt, fNumExt); //expert
		addLine(line++, lNumInt, fNumInt); //expert
		addLine(line++, lAddress3, fAddress3);
		addLine(line++, lAddress2, fAddress2); //colonia (segun como se muestra en Facturaci�n)
		//addLine(line++, lAddress4, fAddress4);

		//  sequence of City Postal Region - @P@ @C@ - @C@, @R@ @P@
		String ds = country.getDisplaySequence();
		if (ds == null || ds.length() == 0) {
			log.log(Level.SEVERE, "DisplaySequence empty - " + country);
			ds = ""; // @C@, @P@
		}
		StringTokenizer st = new StringTokenizer(ds, "@", false);
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s.startsWith("C"))
				addLine(line++, lCity, fCity);
			else if (s.startsWith("P"))
				//addLine(line++, lPostal, fPostal);
				addLine(line++, lPostal, getPanelCP());//Expert (Lama)
			else if (s.startsWith("A"))
				addLine(line++, lPostalAdd, fPostalAdd);
			else if (s.startsWith("T") && ( m_location.getRegion() != null && m_location.getRegion().isHasTownCouncil()))
				addLine(line++, lTownCouncil, fTownCouncil);
			else if (s.startsWith("R") && m_location.getCountry().isHasRegion())
				addLine(line++, lRegion, fRegion);
		}

		// Country Last
		addLine(line++, lCountry, fCountry);

		//	Fill it
		if (m_location.getC_Location_ID() != 0 || m_colonia != null) {
			fAddress1.setText(m_location.getAddress1());
			fAddress2.setText(m_location.getAddress2());
			fAddress3.setText(m_location.getAddress3());
			//fAddress4.setText(m_location.getAddress4());
			fNumExt.setText(m_location.getNumExt()); //Expert
			fNumInt.setText(m_location.getNumIn()); //Expert
			fCity.setText(m_location.getCity());
			fPostal.setText(m_location.getPostal());
			fPostalAdd.setText(m_location.getPostal_Add());
			if (m_location.getCountry().isHasRegion()) {
				lRegion.setText(m_location.getCountry().getRegionName());
				fRegion.setSelectedItem(m_location.getRegion());

				if (m_location.getRegion() != null && m_location.getRegion().isHasTownCouncil()) {
					// lTownCouncil.setText(m_location.getRegion().getTownCouncilName());
					fTownCouncil.setSelectedItem(m_location.getTownCouncil());
				}
			}
			fCountry.setSelectedItem(country);
		}
		//	Update UI
		pack();
	}	//	initLocation

	/**
	 *	Add Line to screen
	 *
	 *  @param line line number (zero based)
	 *  @param label label
	 *  @param field field
	 */
	private void addLine(int line, JLabel label, JComponent field) {
		gbc.gridy = line;
		//	label
		gbc.insets = labelInsets;
		gbc.gridx = 0;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		mainPanel.add(label, gbc);
		//	Field
		gbc.insets = fieldInsets;
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.NONE;
		mainPanel.add(field, gbc);
	}	//	addLine


	/**
	 *	ActionListener
	 *  @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(ConfirmPanel.A_OK)) {
			action_OK();
			m_change = true;
			dispose();
		} else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			m_change = false;
			dispose();
		}
		// Country Changed - display in new Format
		else if (e.getSource() == fCountry) {
			setInitLocation();
			// Modifier for Mouse selection is 16 - for any key selection 0
			MCountry c = (MCountry) fCountry.getSelectedItem();
			m_location.setCountry(c);
			// refresh
			mainPanel.removeAll();
			initLocation();
			fCountry.requestFocus(); // allows to use Keybord selection
		}
		//Country Changed - display in new Format
		else if (e.getSource() == fRegion) {
			setInitLocation();
			// Modifier for Mouse selection is 16 - for any key selection 0
			MRegion r = (MRegion) fRegion.getSelectedItem();
			m_location.setRegion(r);
			// refresh
			mainPanel.removeAll();
			initLocation();
			fRegion.requestFocus(); // allows to use Keybord selection
		}
		//Expert .- Ventana de busqueda: Colonia / Codigo Postal (Lama)
		else if (e.getSource() == bPostalCode){
			findPostalCode();
		}
	}	//	actionPerformed

	/**
	 * 	OK - check for changes (save them) & Exit
	 */
	private void action_OK() {
		m_location.setAddress1(fAddress1.getText());
		m_location.setAddress2(fAddress2.getText());
		m_location.setAddress3(fAddress3.getText());
		//m_location.setAddress4(fAddress4.getText());
		m_location.setCity(fCity.getText());
		m_location.setPostal(fPostal.getText());
		m_location.setPostal_Add(fPostalAdd.getText());
		//Expert .- Campos: NumInt / NumExt (Lama)
		m_location.setNumExt(fNumExt.getText());
		m_location.setNumIn(fNumInt.getText());
		
		//  Country/Region
		MCountry c = (MCountry)fCountry.getSelectedItem();
		m_location.setCountry(c);
		if (m_location.getCountry().isHasRegion()) {
			MRegion r = (MRegion) fRegion.getSelectedItem();
			m_location.setRegion(r);
			
			if (m_location.getRegion() != null && m_location.getRegion().isHasTownCouncil()) {
				MTownCouncil t = (MTownCouncil) fTownCouncil.getSelectedItem();
				m_location.setTownCouncil(t);
			}

		} else {
			m_location.setC_Region_ID(0);
			m_location.setEXME_TownCouncil_ID(0);
		}
		// Save chnages
		m_location.save();
	}	//	actionOK

	/**
	 *	Get result
	 *  @return true, if changed
	 */
	public boolean isChanged() {
		return m_change;
	} // getChange

	/**
	 * 	Get edited Value (MLocation)
	 *	@return location
	 */
	public MLocation getValue() {
		return m_location;
	} // getValue
	
	
	/**
	 * Panel para el codigo postal y el boton de busqueda (Lama)
	 * @return
	 */
	private JComponent getPanelCP(){

		Dimension size = fPostal.getPreferredSize();
		Dimension bSize = new Dimension(size.height, size.height);
		bPostalCode.setPreferredSize (bSize);
		bPostalCode.setIcon(Env.getImageIcon("Find16.gif"));
		//
		middlePanel.add(fPostal);
		middlePanel.add(bPostalCode);
		
		return middlePanel;
	}
	
	/**
	 * Ventana de busqueda de Colonias / Codigos Postales (Lama)
	 */
	private void findPostalCode() {
		VPostalCodeDialog ld = new VPostalCodeDialog(Env.getFrame(this),Msg.translate(Env.getCtx(), "Search"));
		ld.setVisible(true);
	
		Object result = null;
		result = ld.getSelectedKey();

		if (result != null) {
			//llenamos los datos segun la colonia elegida
			m_colonia = (MEXMEColonia)result;
			setLocation();
		}

		ld = null;
		m_colonia = null;
	}

	/**
	 * Asignamos los valores de la busqueda (Lama)
	 */
	private void setLocation() {
		setInitLocation();
		m_location.setCountry(m_colonia.getCountry());
		m_location.setRegion(m_colonia.getRegion());
		m_location.setTownCouncil(m_colonia.getTownCouncil());
		m_location.setAddress2(m_colonia.getColonia());
		m_location.setCity(m_colonia.getCiudad());
		m_location.setPostal(String.valueOf(m_colonia.getCodigo_Postal()));

		mainPanel.removeAll();
		initLocation();
	}
	
	/**
	 * Asignamos los valores de la busqueda (Lama)
	 */
	private void setInitLocation() {
		m_location.setAddress1(fAddress1.getText());
		m_location.setAddress2(fAddress2.getText());
		m_location.setAddress3(fAddress3.getText());
		//m_location.setAddress4(fAddress4.getText());
		m_location.setCity(fCity.getText());
		m_location.setPostal(fPostal.getText());
		m_location.setNumExt(fNumExt.getText());
		m_location.setNumIn(fNumInt.getText());
	}
	
}	//	VLocationDialog
