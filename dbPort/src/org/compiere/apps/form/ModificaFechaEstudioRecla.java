package org.compiere.apps.form;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;
import java.util.logging.*;

import javax.swing.*;



import org.compiere.apps.*;
import org.compiere.grid.ed.*;

import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.process.*;
import org.compiere.swing.*;
import org.compiere.util.*;



/**
 *  
 */
public class ModificaFechaEstudioRecla extends CPanel
implements FormPanel, ActionListener
{


	private MEXMEHojaReclasificacion hj=null;
	private MEXMEClasificacion clas=null;
	private MEXMEPaciente pac=null;
	private String trxName=null;

	public ModificaFechaEstudioRecla()
	{
	}   //  VPaySelect

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		log.log(Level.INFO, "VModificaFechaEstudioRecla.init");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try
		{
			jbInit();

			frame.getContentPane().add(commandPanel, BorderLayout.SOUTH);
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "VPaySelect.init", e);
		}
	}	//	init

	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;


	private boolean         m_isLocked = false;
	/** Payment Selection		*/
	private MPaySelection	m_ps = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger( ModificaFechaEstudioRecla.class);

	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();

	private CLabel labelHistoria = new CLabel();
	private CLabel labelNombrePaciente= new CLabel(" ",CLabel.LEFT);
	private CLabel labelExpediente = new CLabel();
	private CLabel labelFechaEstudio = new CLabel();
	private CLabel labelFechaImpresion = new CLabel();

	private CTextField fieldHistoria = new CTextField();
	private CTextField fieldExpediente = new CTextField();
	private VDate fieldFechaEstudio = new VDate();
	private VDate fieldFechaImpresion = new VDate();


	private CPanel commandPanel = new CPanel();
	private JButton bCancel = ConfirmPanel.createCancelButton(true);
	private JButton bGuardar = ConfirmPanel.createOKButton(true);
	private JButton bBorrar = ConfirmPanel.createRefreshButton(true);


	private GridBagLayout parameterLayout = new GridBagLayout();

	private FlowLayout commandLayout = new FlowLayout();


	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(this);
		mainPanel.setLayout(mainLayout);
		parameterPanel.setLayout(parameterLayout);
		labelHistoria.setText("Historia");		
		labelExpediente.setText("Expediente");				
		labelFechaEstudio.setText("Fecha Estudio");
		String aux="Fecha Impresi\u00F3n";
		labelFechaImpresion.setText(aux);

		bGuardar.addActionListener(this);

		bBorrar.addActionListener(this);
		bCancel.addActionListener(this);
		fieldHistoria.addActionListener(this);
		fieldExpediente.addActionListener(this);

		//
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		parameterPanel.add(labelHistoria,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldHistoria,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));


		parameterPanel.add(labelNombrePaciente,   new GridBagConstraints(2, 0, 2, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));


		parameterPanel.add(labelExpediente,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldExpediente,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		parameterPanel.add(labelFechaEstudio,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldFechaEstudio,   new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(labelFechaImpresion,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldFechaImpresion,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		commandPanel.setLayout(commandLayout);
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandLayout.setHgap(10);

		commandPanel.add(bGuardar, null);
		commandPanel.add(bBorrar, null);
		commandPanel.add(bCancel, null);
		bGuardar.setVisible(false);
		labelNombrePaciente.setVisible(false);


	}   //  jbInit

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose


	/**************************************************************************
	 *  ActionListener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{

		//  Generate PaySelection
		if (e.getSource() == bGuardar)
		{
			actualizaFecha();
			cambiaEstatusCampo(true);

		}

		else if (e.getSource() == bCancel)
		{    this.hj=null;
		this.clas=null;		   
		dispose();
		}
		else if(e.getSource()==fieldHistoria)
			buscaHistoria();
		else if(e.getSource()==fieldExpediente)
			buscaExpediente();

		else if(e.getSource()==bBorrar)
			cambiaEstatusCampo(true);	

	}
	//  actionPerformed

	/**
	 *  Lock User Interface
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI (ProcessInfo pi)
	{
		this.setEnabled(false);
		m_isLocked = true;
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi process info
	 */
	public void unlockUI (ProcessInfo pi)
	{
		//	this.setEnabled(true);
		//	m_isLocked = false;
		//  Ask to Print it
		if (!ADialog.ask(m_WindowNo, this, "ModificaFechas?", "(" + pi.getSummary() + ")"))
			return;

		//  Start PayPrint
		int AD_Form_ID = 106;	//	Payment Print/Export
		FormFrame ff = new FormFrame();
		ff.openForm (AD_Form_ID);
		//	Set Parameter
		if (m_ps != null)
		{
			VPayPrint pp = (VPayPrint)ff.getFormPanel();
			pp.setPaySelection(m_ps.getC_PaySelection_ID());
		}
		//
		ff.pack();
		this.setVisible(false);
		AEnv.showCenterScreen(ff);
		this.dispose();
	}   //  unlockUI



	private void buscaHistoria()
	{ 

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);	
		trxName = DB.createTrxName("ModificaFechas", Env.getCtx());

		try	{   				
			sql.append("SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE IsActive='Y' AND Value=");
			sql.append(fieldHistoria.getText());
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), sql.toString(), MEXMEPaciente.Table_Name));
			
			int historiaID = obtenID(sql.toString()); 

			pac = new MEXMEPaciente(Env.getCtx(), historiaID, trxName);
			if (pac == null) {
				JOptionPane.showMessageDialog(this, "Historia no registrada");
			} else {
			/////Datos
				
				//Revisar si la BD es Oracle o Postgresql
				sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sql.append("SELECT Expediente FROM EXME_Hist_Exp WHERE IsActive='Y' AND EXME_Paciente_ID=");
				sql.append(historiaID);
				
				if (DB.isOracle()) {
					 sql.append("AND ROWNUM = 1");
				} else if (DB.isPostgreSQL()) {
					sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
				}

				fieldExpediente.setText(String.valueOf(obtenID(sql.toString())));		            

				clas= MEXMEClasificacion.getByPaciente(Env.getCtx(),historiaID,trxName);

				if(clas!=null)
				{
					hj= 	MEXMEHojaReclasificacion.getByIndice(Env.getCtx(),historiaID,trxName);
					if(hj!=null)
					{  labelNombrePaciente.setText(pac.getFullName());
					fieldFechaEstudio.setValue(clas.getDateOrdered());
					fieldFechaImpresion.setValue(hj.getFecha_Impresion());

					cambiaEstatusCampo(false);

					}
					else
						JOptionPane.showMessageDialog(this, "No existe hoja de reclasificaci\u00F3n");		            			     
				}
				else
					JOptionPane.showMessageDialog(this, "No existe clasificaci\u00F3n");


			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Historia no valida");	
		}

	}


	private void buscaExpediente()
	{ 

		String sql="";	
		trxName = DB.createTrxName("ModificaFechas", Env.getCtx());

		try
		{   					
			sql="SELECT EXME_Paciente_ID FROM EXME_Hist_Exp WHERE IsActive='Y' AND Expediente=" + fieldExpediente.getText();

			int historiaID=obtenID(sql); 

			if(historiaID!=0)
			{				
				pac= new MEXMEPaciente(Env.getCtx(),historiaID,trxName);

				if(pac==null)
					JOptionPane.showMessageDialog(this, "Historia no registrada");
				else
				{


					clas= MEXMEClasificacion.getByPaciente(Env.getCtx(),historiaID,trxName);

					if(clas!=null)
					{
						hj= 	MEXMEHojaReclasificacion.getByIndice(Env.getCtx(),historiaID,trxName);
						if(hj!=null)
						{  		            	
							fieldHistoria.setText(pac.getValue());
							labelNombrePaciente.setText(pac.getFullName());		            	
							fieldFechaEstudio.setValue(clas.getDateOrdered());
							fieldFechaImpresion.setValue(hj.getFecha_Impresion());

							cambiaEstatusCampo(false);

						}
						else
							JOptionPane.showMessageDialog(this, "No existe hoja de reclasificaci\u00F3n");		     		     
					}
					else
						JOptionPane.showMessageDialog(this, "No existe clasificaci\u00F3n");		     		    	    
				}

			}
			else
			{
				JOptionPane.showMessageDialog(this, "El Expediente no existe");
			}

		}
		catch(Exception e)
		{

		}

	}


	private boolean validaCampos()
	{
		boolean resp=true;
		//java.util.Date fechaIni=new java.util.Date();

		if(fieldFechaEstudio.getDisplay().length()==0)
		{
			JOptionPane.showMessageDialog(this, "Fecha de Estudio no valido");
			resp=false;
		}	

		if(fieldFechaImpresion.getDisplay().length()==0)
		{
			JOptionPane.showMessageDialog(this, "Fecha de Impresiu\00EDn no valido");
			resp=false;
		}

		return resp;
	}


	private void actualizaFecha()
	{
		boolean bandera=true;

		if(validaCampos())
		{
			if(hj!=null && clas!=null)
			{
				Env.getCtx().setProperty("isModificaFechas", "Y");

				hj.setFecha_Impresion(fieldFechaImpresion.getTimestamp());
				clas.setDateOrdered(fieldFechaEstudio.getTimestamp());
				try
				{
					if(hj.save(trxName) && clas.save(trxName))
					{
						DB.commit(true, trxName);
						JOptionPane.showMessageDialog(this, "Se actualizo correctamente las fechas de estudio e impresi\u00F3n");
						bandera=false;

					}
					else
						DB.rollback(false, trxName);            	        
				}
				catch(Exception e)
				{
					try
					{
						DB.rollback(false, trxName);
					}
					catch(Exception ex){}
				}

			}

			if(bandera)
				JOptionPane.showMessageDialog(this, "Error al actualizar las fechas de estudio e impresi\u00F3n en la base de datos");


		}


	}



	private int obtenID(String sql)
	{
		Integer exp=0;

		try
		{

			Statement state= DB.createStatement();          
			ResultSet registro= state.executeQuery(sql);

			if(registro.next())
			{
				exp=new Integer(registro.getInt(1));

			}

			registro.close();
			state.close();

		}
		catch(Exception ex)
		{

		}

		return exp;

	}

	private void cambiaEstatusCampo(boolean bandera)
	{
		bGuardar.setVisible(!bandera);
		fieldHistoria.setEditable(bandera);
		fieldExpediente.setEditable(bandera);

		if(bandera)
		{
			this.fieldExpediente.setText("");
			this.fieldHistoria.setText("");
			this.labelNombrePaciente.setText("");
			this.fieldFechaEstudio.setValue(new java.util.Date());
			this.fieldFechaImpresion.setValue(new java.util.Date());
			this.hj=null;
			this.clas=null;
		}

	}



}