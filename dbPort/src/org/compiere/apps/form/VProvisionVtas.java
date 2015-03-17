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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBPGroupAcct;
import org.compiere.model.MBPartner;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEJournalBatch;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEProvisionVenta;
import org.compiere.model.MEXMETTProvision;
import org.compiere.model.MElementValue;
import org.compiere.model.MOrg;
import org.compiere.model.MPeriod;
import org.compiere.model.MYear;
import org.compiere.plaf.CompiereColor;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
/**
 *  Create Manual Payments From (AP) Invoices or (AR) Credit Memos.
 *  Allows user to select Invoices for payment.
 *  When Processed, PaySelection is created
 *  and optionally posted/generated and printed
 *
 *  @author Jorg Janke
 *  @version $Id: VProvisionVtas.java,v 1.8 2006/08/23 00:33:36 mrojas Exp $
 */
public class VProvisionVtas extends CPanel
	implements FormPanel, ActionListener, TableModelListener, ASyncProcess
{
	private static final long serialVersionUID = 1L;
	/** @todo withholding */
	/** Window No */
	//private int         	m_WindowNo = 0;
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init(int WindowNo, FormFrame frame) {
		log.info("");
		//this.m_WindowNo = WindowNo;
		m_frame = frame;
		try {
			jbInit();
			dynInit();
			frame.getContentPane().add(commandPanel, BorderLayout.SOUTH);
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	} // init


	/**	FormFrame			*/
	private FormFrame 		m_frame;

	/** Format                  */
	//private DecimalFormat   m_format = DisplayType.getNumberFormat(DisplayType.Amount);
	/** Bank Balance            */
	//private BigDecimal      m_bankBalance = new BigDecimal(0.0);
	/** SQL for Query           */
	//private String          m_sql;
	/** Number of selected rows */
	//private int             m_noSelected = 0;
	/** Client ID               */
	//private int             m_AD_Client_ID = 0;
	/**/
	private boolean         m_isLocked = false;
	/** Payment Selection		*/
	//private MPaySelection	m_ps = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VProvisionVtas.class);

	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	//*************************LABEL********************************
	private CLabel lblPeriodoProvisionar = new CLabel();
	private CLabel lblFechaInicial = new CLabel();
	private CLabel lblFechaFinal = new CLabel();
	private CLabel lblCtaPacInicial = new CLabel();
	private CLabel lblCtaPacFinal = new CLabel();
	private CLabel lblDatosPoliza = new CLabel();
	private CLabel lblAnio = new CLabel();
	private CLabel lblPeriodo = new CLabel();
	private CLabel lblFechaPoliza = new CLabel();
	private CLabel lblNoPoliza = new CLabel();
	private CLabel lblError = new CLabel();
	//***************************************************************
	
	//*************************TEXTFIELD*****************************
	private JTextField txtFechaInicial = new JTextField();
	private JTextField txtFechaFinal = new JTextField();
	private JTextField txtCtaPacInicial = new JTextField();
	private JTextField txtCtaPacFinal = new JTextField();
	private JTextField txtAnio = new JTextField();
	private JTextField txtPeriodo = new JTextField();
	private JTextField txtFechaPoliza = new JTextField();
	private JTextField txtNoPoliza = new JTextField();
	//***************************************************************
	
	//*************************CheckBox******************************
	private VCheckBox chkSimulacion = new VCheckBox();
	//***************************************************************
	
	//*************************Button********************************
	private JButton bCancel = ConfirmPanel.createCancelButton(true);
	private JButton bGenerate = ConfirmPanel.createProcessButton(true);
	//***************************************************************
	
	private GridBagLayout parameterLayout = new GridBagLayout();
	private FlowLayout commandLayout = new FlowLayout();
	private CPanel commandPanel = new CPanel();
	private JLabel dataStatus = new JLabel();
	private JScrollPane dataPane = new JScrollPane();
	
	/**
	 *  Static Init
	 *  @throws Exception
	 */
	
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(this);
		//
		mainPanel.setLayout(mainLayout);
		parameterPanel.setLayout(parameterLayout);
		//
		
		//********************Configuracion de LABEL****************************
		lblPeriodoProvisionar.setText("Periodo a Provisionar");
		lblFechaInicial.setText("Fecha Inicial");
		lblFechaFinal.setText("Fecha Final");
		lblCtaPacInicial.setText("Desde Cuenta Paciente");
		lblCtaPacFinal.setText("Hasta Cuenta Paciente");
		lblDatosPoliza.setText("Datos de la P�liza");
		lblAnio.setText("A�o");
		lblPeriodo.setText("Periodo");
		lblFechaPoliza .setText("Fecha P�liza");
		lblNoPoliza.setText("No. P�liza");
		lblError.setText("");
		//**********************************************************************
		
		//********************Configuracion de TEXTFIELD************************
		java.util.Date dFechaActual = new java.util.Date();
		java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
		
		String strFechaHoy = formato.format(dFechaActual);
		
		txtFechaInicial.setText("");
		txtFechaFinal.setText("");
		txtCtaPacInicial.setText("");
		txtCtaPacFinal.setText("");
		txtAnio.setText(strFechaHoy.substring(6, 10));
		txtPeriodo.setText(strFechaHoy.substring(3, 5));
		txtFechaPoliza.setText(strFechaHoy);
		txtNoPoliza.setText("");
		
		txtAnio.setEnabled(false);
		txtPeriodo.setEnabled(false);
		txtFechaPoliza.setEnabled(true);
		txtNoPoliza.setEnabled(true);
		
		txtFechaPoliza.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                calcula(evt);
            }
        });
    	
        txtFechaFinal.setText(String.valueOf(strFechaHoy)); 
        
        dFechaActual.setYear(dFechaActual.getYear() - 1);
        txtFechaInicial.setText(formato.format(dFechaActual));
        
        txtCtaPacFinal.setText("9999999");
        txtCtaPacInicial.setText("");
		//**********************************************************************
		
		//*********************Configuracion de Checkbox************************
		chkSimulacion.setText("Simulaci�n");
		//**********************************************************************

		//
		bGenerate.addActionListener(this);
		bCancel.addActionListener(this);
		//
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		
		//*****************Agregar los LABEL, TEXTFIELD, CHECKBOX al PANEL********************
		//------------------------------------------------------------------------------------
		parameterPanel.add(lblPeriodoProvisionar,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//------------------------------------------------------------------------------------
		parameterPanel.add(lblFechaInicial,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(txtFechaInicial,   new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(lblFechaFinal,  new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(txtFechaFinal,   new GridBagConstraints(3, 1, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//------------------------------------------------------------------------------------
		parameterPanel.add(lblCtaPacInicial,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(txtCtaPacInicial,   new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(lblCtaPacFinal,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(txtCtaPacFinal,   new GridBagConstraints(3, 2, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//------------------------------------------------------------------------------------
		parameterPanel.add(chkSimulacion,  new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//------------------------------------------------------------------------------------
		parameterPanel.add(lblDatosPoliza,  new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//------------------------------------------------------------------------------------
		parameterPanel.add(lblAnio,  new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(txtAnio,   new GridBagConstraints(1, 5, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(lblPeriodo,  new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(txtPeriodo,   new GridBagConstraints(3, 5, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//------------------------------------------------------------------------------------
		parameterPanel.add(lblFechaPoliza,  new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(txtFechaPoliza,   new GridBagConstraints(1, 6, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(lblNoPoliza,  new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		parameterPanel.add(txtNoPoliza,   new GridBagConstraints(3, 6, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//------------------------------------------------------------------------------------
//		------------------------------------------------------------------------------------
		parameterPanel.add(lblError,  new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//------------------------------------------------------------------------------------
		//************************************************************************************
		
		mainPanel.add(dataStatus, BorderLayout.SOUTH);
		mainPanel.add(dataPane, BorderLayout.CENTER);
		//dataPane.getViewport().add(miniTable, null);
		//
		commandPanel.setLayout(commandLayout);
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandLayout.setHgap(10);
		commandPanel.add(bCancel, null);
		commandPanel.add(bGenerate, null);
	}   //  jbInit

	/**
	 *  Dynamic Init.
	 *  - Load Bank Info
	 *  - Load BPartner
	 *  - Init Table
	 */
	private void dynInit() {
	} // dynInit


	/**
	 * Dispose
	 */
	public void dispose() {
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	} // dispose

	/***********************************************************************************************
	 * ActionListener
	 * @param e event
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bGenerate) {
			if(txtCtaPacInicial.getText().toString().trim().length() == 0){
				txtCtaPacInicial.setText(" ");
			}
			if(txtCtaPacFinal.getText().toString().trim().length() == 0){
				txtCtaPacFinal.setText("9999999");
			}

			if (txtCtaPacInicial.getText().toString().compareTo(txtCtaPacFinal.getText().toString()) < 0 || txtCtaPacInicial.getText().toString().compareTo(txtCtaPacFinal.getText().toString()) == 0) {
				try{
					String sDocumentNo = txtNoPoliza.getText().trim();
					if(sDocumentNo.length() > 0){
						@SuppressWarnings("unused")
						int poliza = Integer.parseInt(sDocumentNo);						
					}
					generarReporte();
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "El Numero de poliza debe ser entero", "Error",JOptionPane.CLOSED_OPTION);
				}				
			} else {
				JOptionPane.showMessageDialog(this, "La cuenta paciente inicial debe ser menor a la cuenta paciente final", "Error",JOptionPane.CLOSED_OPTION);
			}

			// dispose();
		} else if (e.getSource() == bCancel)
			dispose();
	} // actionPerformed

	/**
	 * Table Model Listener
	 * @param e event
	 */
	public void tableChanged(TableModelEvent e) {
		if (e.getColumn() == 0)
			calculateSelection();
	} // valueChanged

	/**
	 * Calculate selected rows. - add up selected rows
	 */
	public void calculateSelection() {
	} // calculateSelection

	public static boolean validaFechas(String fechaIni, String fechaFin) {
		Date ini = null;
		Date fin = null;
		try {
			ini = Constantes.getSdfFecha().parse(fechaIni);
			fin = Constantes.getSdfFecha().parse(fechaFin);
			if (ini.after(fin)) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	public static boolean fechaValida(String fecha) {
		try {
			// la fecha no debe tener ni mas ni menos de 10 caracteres
			if (fecha.trim().length() != 10) {
				// errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.formato.fecha"));
				return false;
			}

			int dia = Integer.parseInt(fecha.substring(0, 2));
			int mes = Integer.parseInt(fecha.substring(3, 5));
			int anio = Integer.parseInt(fecha.substring(6, 10));

			// debe ser un dia, mes y a�o v�lido
			if (dia <= 0 || mes <= 0 || anio <= 0) {
				// errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("msj.error.fecha"));
				return false;
			}
			// el mes debe estar entre 1 y 12
			if (mes > 12) {
				// errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.mes"));
				return false;
			}

			// los dias deben ser v�lidos segun el mes
			int totalDias = 0;

			switch (mes) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: {
				totalDias = 31;
				break;
			}

			case 4:
			case 6:
			case 9:
			case 11: {
				totalDias = 30;
				break;
			}

			case 2: {// dias segun si es a�o bisiesto
				GregorianCalendar gregCal = new GregorianCalendar();
				if (gregCal.isLeapYear(anio))
					totalDias = 29;
				else
					totalDias = 28;
				break;
			}
			}
			// si el dia ingresado es mayor al Numero de dias de c/mes
			if (dia > totalDias) {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Generacion del reporte
	 *  Cambios .- Lorena Lama (Agosto 2008)
	 */
	/**
	 * 
	 */
	private void generarReporte() {
		Trx m_trx = null;
		Trx m_trxDelete = null;
		try {
			String fechaInicial = null;
			String fechaFinal = null;
			String ctaPacInicial = null;
			String ctaPacFinal = null;

			// PARAMETROS (Obtener los valores ingresados desde la pantalla)
			fechaInicial = txtFechaInicial.getText().trim();
			fechaFinal = txtFechaFinal.getText().trim();

			ctaPacInicial = txtCtaPacInicial.getText();
			ctaPacFinal = txtCtaPacFinal.getText();

			// validamos los datos ingresados desde la pantalla
			String msgError = null;
			msgError = validar(fechaInicial, fechaFinal, ctaPacInicial, ctaPacFinal);
			if (msgError != null) {
				JOptionPane.showMessageDialog(this, msgError, "Error", JOptionPane.CLOSED_OPTION);
				return;
			}

			String sDocumentNo = txtNoPoliza.getText().trim();
			if(sDocumentNo.length() > 0){
				int org = Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID");
				boolean existe = MEXMEJournalBatch.docNoExist(Env.getCtx(), org, getPeriodo(), sDocumentNo, null);
				if(existe){
					JOptionPane.showMessageDialog(this, "El Numero de p�liza ya ha sido utilizado", "Numero de p�liza duplicado", JOptionPane.OK_OPTION);
					return;
				}
			}
			//Llenamos la tabla de trabajo por sesion Expert: Omar de la Rosa
			m_trx = Trx.get(Trx.createTrxName("TT_Prov"), true);
			m_trxDelete = Trx.get(Trx.createTrxName("TT_ProvDel"), true);
			
			MEXMETTProvision.delete(Env.getCtx(), Env.getContextAsInt(Env.getCtx(), "#AD_Session_ID"), m_trxDelete.getTrxName());
			m_trxDelete.commit();
								  
			MEXMECtaPac[] listaPac = MEXMECtaPac.getCtaPacProv(Env.getCtx(), fechaInicial, fechaFinal, ctaPacInicial, ctaPacFinal, null);			
			
			for(int i = 0; i < listaPac.length; i++){			
				MEXMECtaPac ctaPac = listaPac[i];
				MEXMETTProvision provHeader = new MEXMETTProvision(Env.getCtx(), 0, m_trx.getTrxName());
				MEXMEPaciente paciente = new MEXMEPaciente(Env.getCtx(), ctaPac.getEXME_Paciente_ID(), null);
				MBPartner part = paciente.getPatientBPartner();
				MBPartner partner = null;
				String nombreCliente = null;
				if(part != null){ 
					partner = new MBPartner(Env.getCtx(),part.getC_BPartner_ID(), null);//ASEGURADORA
					nombreCliente = partner.getName();
				}else{
					partner = new MBPartner(Env.getCtx(),ctaPac.getC_BPartner_ID(), null);//PARTICULAR
					nombreCliente = paciente.getRespFullNamexName();
				}
				
				String ctacont = null;
				int validID;

				validID = MBPGroupAcct.getValidCombinationID(Env.getCtx(),partner.getC_BP_Group_ID(), null);
				MAccount cuenta = new MAccount(Env.getCtx(), validID, null);
				MElementValue value = new MElementValue(Env.getCtx(), cuenta.getAccount_ID(), null);
				ctacont = value.getValue();
				
				MAcctSchema esquemaContable = new MAcctSchema(Env.getCtx(), Env.getContextAsInt(Env.getCtx(), "$C_AcctSchema_ID"), null);
				provHeader.setCliente(nombreCliente);
				provHeader.setAD_Session_ID(Env.getContextAsInt(Env.getCtx(), "#AD_Session_ID"));
				provHeader.setCredito(BigDecimal.ZERO);
				provHeader.setDebito(BigDecimal.ZERO);
				provHeader.setDocumentNo(ctaPac.getDocumentNo());
				provHeader.setFecha(ctaPac.getCreated());
				MOrg orga = new MOrg(Env.getCtx(), ctaPac.getAD_Org_ID(), null);
				provHeader.setCentro_Costo(orga.getValue());
				provHeader.setCta_Cont(ctacont);
				provHeader.setCta_Cont_ID(validID);
				provHeader.setC_Currency_ID(esquemaContable.getC_Currency_ID());
				
				if(validID<=0){
					JOptionPane.showMessageDialog(null, "No cuenta con configuraci�n de cuentas contables para grupo de socio de negocios");
					return;
				}
				
				if(!provHeader.save()){
					return;
				}
				
				BigDecimal totalLineas = MCtaPacDet.generateProv(Env.getCtx(), ctaPac, m_trx.getTrxName());
				if(totalLineas.compareTo(BigDecimal.ZERO) == 0){
					if(provHeader.delete(true)){}
				}else{
					provHeader.setDebito(totalLineas);
					if(!provHeader.save()){return;}
				}

			}
			
			if (m_trx != null) {
				m_trx.commit();
			}
			
			String poliza = null;
			if (!chkSimulacion.isSelected()) {
				//Periodo
				int periodo = getPeriodo();
				String fecha = txtFechaPoliza.getText();
				poliza = MEXMEProvisionVenta.poliza(Env.getCtx(),periodo, fecha, txtNoPoliza.getText(), m_trx.getTrxName());
				if(poliza!=null){
					txtNoPoliza.setText(poliza);
				} else {
					return;
				}
				
				if (m_trx != null) {
					m_trx.commit();
				}
			}

//			RepProvisionVtas rep = new RepProvisionVtas();
			String strGenerado = null;//rep.prepara(poliza, "provision");
			txtNoPoliza.setText(poliza==null?"":poliza);
			JOptionPane.showMessageDialog(this, strGenerado, "Mensaje", JOptionPane.CLOSED_OPTION);
			
			MEXMETTProvision.delete(Env.getCtx(), Env.getContextAsInt(Env.getCtx(), "#AD_Session_ID"), m_trxDelete.getTrxName());
			m_trxDelete.commit();
			
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e.getMessage());
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
			}
			JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar", "Error", JOptionPane.CLOSED_OPTION);
		} finally {
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
			}
		}

	} // generarReporte



	/**
	 * Lock User Interface Called from the Worker before processing
	 * @param pi process info
	 */
	public void lockUI(ProcessInfo pi) {
		this.setEnabled(false);
		m_isLocked = true;
	} // lockUI

	/**
	 * Unlock User Interface. Called from the Worker when processing is done
	 * @param pi process info
	 */
	public void unlockUI(ProcessInfo pi) {
	} // unlockUI

	/**
	 * Is the UI locked (Internal method)
	 * @return true, if UI is locked
	 */
	public boolean isUILocked() {
		return m_isLocked;
	} //  isLoacked

	/**
	 *  Method to be executed async.
	 *  Called from the ASyncProcess worker
	 *  @param pi process info
	 */
	public void executeASync(ProcessInfo pi) {
		log.config("-");
	} //  executeASync

	/**************************************************************************
	 *  Bank Account Info
	 */
	public class BankInfo {
		/**
		 * 	BankInfo
		 *	@param newC_BankAccount_ID
		 *	@param newC_Currency_ID
		 *	@param newName
		 *	@param newCurrency
		 *	@param newBalance
		 *	@param newTransfers
		 */
		public BankInfo(int newC_BankAccount_ID, int newC_Currency_ID,
				String newName, String newCurrency, BigDecimal newBalance,
				boolean newTransfers) {

		}
		int C_BankAccount_ID;
		int C_Currency_ID;
		String Name;
		String Currency;
		BigDecimal Balance;
		boolean Transfers;

		/**
		 * 	to String
		 *	@return info
		 */
		public String toString() {
			return Name;
		}
	} //  BankInfo

	/**
	 * Validacion de datos de entrada
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param ctaPacInicial
	 * @param ctaPacFinal
	 */
	private String validar(String fechaInicial, String fechaFinal, String ctaPacInicial, String ctaPacFinal) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String strFechaHoy = null;
		strFechaHoy = formato.format(DB.getDateForOrg(Env.getCtx()));
		String mensaje = null;

		if (ctaPacInicial == "" || ctaPacFinal == "" || fechaInicial == "" || fechaFinal == "") {
			mensaje = "Favor de llenar todos los campos";
			return mensaje;
		}

		if (!fechaValida(fechaInicial) || !fechaValida(fechaFinal)) {
			mensaje = "Formato de Fecha no v�lido";
			return mensaje;
		}

		if (!validaFechas(fechaInicial, fechaFinal) || !validaFechas(fechaInicial, strFechaHoy)) {
			mensaje = "La Fecha Inicial no puede ser mayor a la Fecha Final o a la Fecha Actual";
			return mensaje;
		}

		return mensaje;
	}

	//private BigDecimal bdTotalDr = Env.ZERO;
	//private BigDecimal bdTotalCr = Env.ZERO;
	
	
	
	/**
	 * Obtener el ID del A�o
	 * @return
	 */
	public int getYear(){
		
		int year_id = 0;
		StringBuilder sql = new StringBuilder(100);
		
		if(txtAnio.getText()== ""){
			return year_id;
		}

		sql.append("SELECT c_year_id FROM c_year WHERE YEAR= '")
			.append(txtAnio.getText()).append("' ")
			.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", MYear.Table_Name));
		
		year_id = Integer.parseInt(get(sql.toString()).toString());	
		sql = null;

		return year_id;
	}
	
	/**
	 * Obtener el ID del Perido
	 * 
	 * @return
	 */
	public int getPeriodo(){

		int strPeriodoID = 0;
		StringBuilder sql = new StringBuilder(100);

		int year = getYear();
		if (year > 0 && !txtPeriodo.getText().equals("")) {
			sql.append("SELECT c_period_id FROM c_period WHERE c_year_id= ")
				.append(year).append(" and PeriodNo = ")
				.append(txtPeriodo.getText())
				.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", MPeriod.Table_Name));

			strPeriodoID = Integer.parseInt(get(sql.toString()).toString());
			sql = null;
		}

		return strPeriodoID;
	}
	
	
	
	/**
	 * Execute generico
	 * @param sql
	 * @return
	 */
	public Object get(String sql){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Object retValue = null;

		try {
			if(sql == null)
				return retValue;
			
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				retValue=rs.getObject(1);
			}
			
		} catch (Exception e) {
			retValue = 0;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {}
			rs = null;
			pstmt = null;
			sql = null;
		}

		return retValue;
	}
	
	public void calcula(java.awt.event.FocusEvent evt){
		String contenido = txtFechaPoliza.getText();
		boolean valido = fechaValida(contenido);
		if(valido){
			@SuppressWarnings("unused")
			int dia = Integer.parseInt(contenido.substring(0, 2));
			int mes = Integer.parseInt(contenido.substring(3, 5));
			int anio = Integer.parseInt(contenido.substring(6, 10));
			
			txtPeriodo.setText(String.valueOf(mes));
			txtAnio.setText(String.valueOf(anio));
			
		}else{
			JOptionPane.showMessageDialog(this,"Formato de fecha no v�lido", "Error de validaci�n", JOptionPane.ERROR_MESSAGE);
		}
	}
} //  VProvisionVtas
