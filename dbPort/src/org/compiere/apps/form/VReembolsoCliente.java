/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is                  Compiere  ERP & CRM  Business Solution
 * The Initial Developer of the Original Code is Jorg Janke  and ComPiere, Inc.
 * Portions created by Jorg Janke are Copyright (C) 1999-2001 Jorg Janke, parts
 * created by ComPiere are Copyright (C) ComPiere, Inc.;   All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.apps.form;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.*;
import javax.swing.event.*;

import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.minigrid.*;
import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.process.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *  Create Manual Payments From (AP) Invoices or (AR) Credit Memos.
 *  Allows user to select Invoices for payment.
 *  When Processed, PaySelection is created
 *  and optionally posted/generated and printed
 *
 *  @author Jorg Janke
 *  @version $Id: VReembolsoCliente.java,v 1.4 2005/06/23 18:31:54 mrojas Exp $
 */
public class VReembolsoCliente extends CPanel
	implements FormPanel, ActionListener, TableModelListener, ASyncProcess
{
	/** @todo withholding */

	/**
	 *  VPaySelect Constructor
	 */
	public VReembolsoCliente()
	{
	}   //  VPaySelect

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		log.log(Level.INFO, "VPaySelect.init");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try
		{
			jbInit();
			dynInit();
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

	/** Format                  */
	private DecimalFormat   m_format = DisplayType.getNumberFormat(DisplayType.Amount);
	/** Bank Balance            */
	private BigDecimal      m_bankBalance = new BigDecimal(0.0);
	/** SQL for Query           */
	private String          m_sql;
	/** Number of selected rows */
	private int             m_noSelected = 0;
	/** Client ID               */
	private int             m_AD_Client_ID = 0;
	/**/
	private boolean         m_isLocked = false;
	/** Payment Selection		*/
	private MPaySelection	m_ps = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VReembolsoCliente.class);

	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	private CLabel labelBankAccount = new CLabel();
	private VComboBox fieldBankAccount = new VComboBox();
	private GridBagLayout parameterLayout = new GridBagLayout();
	private CLabel labelBankBalance = new CLabel();
	private CLabel labelCurrency = new CLabel();
	private CLabel labelBalance = new CLabel();
	private VCheckBox onlyDue = new VCheckBox();
	private CLabel labelBPartner = new CLabel();
	private VComboBox fieldBPartner = new VComboBox();
	private JLabel dataStatus = new JLabel();
	private JScrollPane dataPane = new JScrollPane();
	private MiniTable miniTable = new MiniTable();
	private CPanel commandPanel = new CPanel();
	private JButton bCancel = ConfirmPanel.createCancelButton(true);
	private JButton bGenerate = ConfirmPanel.createProcessButton(true);
	private FlowLayout commandLayout = new FlowLayout();
	private JButton bRefresh = ConfirmPanel.createRefreshButton(true);
	private CLabel labelPayDate = new CLabel();
	private VDate fieldPayDate = new VDate();
	private CLabel labelPaymentRule = new CLabel();
	private VComboBox fieldPaymentRule = new VComboBox();

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
		labelBankAccount.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		fieldBankAccount.addActionListener(this);
		labelBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fieldBPartner.addActionListener(this);
		bRefresh.addActionListener(this);
		labelPayDate.setText(Msg.translate(Env.getCtx(), "PayDate"));
		labelPaymentRule.setText(Msg.translate(Env.getCtx(), "PaymentRule"));
		//
		labelBankBalance.setText(Msg.translate(Env.getCtx(), "CurrentBalance"));
		labelBalance.setText("0");
		onlyDue.setText(Msg.getMsg(Env.getCtx(), "OnlyDue"));
		dataStatus.setText(" ");
		//
		bGenerate.addActionListener(this);
		bCancel.addActionListener(this);
		//
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		parameterPanel.add(labelBankAccount,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldBankAccount,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(labelBankBalance,  new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(labelCurrency,  new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		parameterPanel.add(labelBalance,   new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(labelBPartner,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldBPartner,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(bRefresh,    new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(labelPayDate,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldPayDate,   new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(labelPaymentRule,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		parameterPanel.add(fieldPaymentRule,  new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//parameterPanel.add(onlyDue,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
			//,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		mainPanel.add(dataStatus, BorderLayout.SOUTH);
		mainPanel.add(dataPane, BorderLayout.CENTER);
		dataPane.getViewport().add(miniTable, null);
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
	private void dynInit()
	{
		Properties ctx = Env.getCtx();

		//  Bank Account Info
		String sql = MRole.getDefault().addAccessSQL(
			"SELECT ba.C_BankAccount_ID,"                       //  1
			+ "b.Name || ' ' || ba.AccountNo AS Name,"          //  2
			+ "ba.C_Currency_ID, c.ISO_Code,"                   //  3..4
			+ "ba.CurrentBalance "                              //  5
			+ "FROM C_Bank b, C_BankAccount ba, C_Currency c "
			+ "WHERE b.C_Bank_ID=ba.C_Bank_ID"
			+ " AND ba.C_Currency_ID=c.C_Currency_ID "
			+ " AND EXISTS (SELECT * FROM C_BankAccountDoc d WHERE d.C_BankAccount_ID=ba.C_BankAccount_ID) "
			+ "ORDER BY 2",
			"b", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RW);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				boolean transfers = false;
				BankInfo bi = new BankInfo (rs.getInt(1), rs.getInt(3),
					rs.getString(2), rs.getString(4),
					rs.getBigDecimal(5), transfers);
				fieldBankAccount.addItem(bi);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log (Level.SEVERE, "VPaySelect.dynInit - BA", e);
		}
		if (fieldBankAccount.getItemCount() == 0)
			ADialog.error(m_WindowNo, this, "VPaySelectNoBank");
		else
			fieldBankAccount.setSelectedIndex(0);

		//  Optional BusinessPartner with unpaid AP Invoices
		KeyNamePair pp = new KeyNamePair(0, "");
		fieldBPartner.addItem(pp);
		/*sql = MRole.getDefault().addAccessSQL(
			"SELECT bp.C_BPartner_ID, bp.Name FROM C_BPartner bp", "bp", 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO)
			+ " AND EXISTS (SELECT * FROM C_Invoice i WHERE bp.C_BPartner_ID=i.C_BPartner_ID"
			  + " AND i.IsSOTrx='Y' AND i.IsPaid<>'Y' AND C_) "//IsSOTrx = 'N' es para proveedores IsSOTrx = 'Y' es para clientes
			+ "ORDER BY 2";*/
			
			
			sql = MRole.getDefault().addAccessSQL(
			"SELECT bp.C_BPartner_ID, bp.Name FROM C_BPartner bp", "bp", 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO)
			+ " AND EXISTS (select * from C_Payment p  where p.isreceipt = 'Y'"
			+ " and bp.C_BPartner_ID=p.C_BPartner_ID" 
			+ " and p.isactive = 'Y' and p.docstatus = 'CO' and p.payamt > 0"
			+ " and p.isallocated = 'N')" 
			//+ " and not exists (Select * from C_allocationline a where a.c_payment_id = p.c_payment_id))"  			 
			
			//+ " " 
			+ " ORDER BY 2";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				fieldBPartner.addItem(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log (Level.SEVERE, "VPaySelect.dynInit - BP", e);
		}
		fieldBPartner.setSelectedIndex(0);

		/**  prepare MiniTable
		 *
		SELECT i.C_Invoice_ID, i.DateInvoiced+p.NetDays AS DateDue,
		bp.Name, i.DocumentNo, c.ISO_Code, i.GrandTotal,
		C_PaymentTerm_Discount(i.GrandTotal, i.C_PaymentTerm_ID, i.DateInvoiced, SysDate) AS Discount,
		SysDate-C_PaymentTerm_DueDays(i.C_PaymentTerm_ID,i.DateInvoiced) AS DiscountDate,
		i.GrandTotal-C_PaymentTerm_Discount(i.GrandTotal,i.C_PaymentTerm_ID,i.DateInvoiced,SysDate) AS DueAmount,
		C_Currency_Convert(i.GrandTotal-C_PaymentTerm_Discount(i.GrandTotal,i.C_PaymentTerm_ID,i.DateInvoiced,SysDate,null),
			i.C_Currency_ID,xx100,SysDate) AS PayAmt
		FROM C_Invoice i, C_BPartner bp, C_Currency c, C_PaymentTerm p
		WHERE i.IsSOTrx='N'
		AND i.C_BPartner_ID=bp.C_BPartner_ID
		AND i.C_Currency_ID=c.C_Currency_ID
		AND i.C_PaymentTerm_ID=p.C_PaymentTerm_ID
		AND i.DocStatus IN ('CO','CL')
		ORDER BY 2,3
		 */

		/*m_sql = miniTable.prepareTable(new ColumnInfo[] {
			//  0..4
			new ColumnInfo(" ", "i.C_Invoice_ID", IDColumn.class, false, false, null),
			//EXPERT: gisela lee : mensaje para la fecha de vencimiento traducido
			new ColumnInfo(Msg.translate(ctx, "DueDate"), "i.DateInvoiced+p.NetDays AS DateDue", Timestamp.class, true, true, null),
			new ColumnInfo(Msg.translate(ctx, "C_BPartner_ID"), "bp.Name", KeyNamePair.class, true, false, "i.C_BPartner_ID"),
			new ColumnInfo(Msg.translate(ctx, "DocumentNo"), "i.DocumentNo", String.class),
			new ColumnInfo(Msg.translate(ctx, "C_Currency_ID"), "c.ISO_Code", KeyNamePair.class, true, false, "i.C_Currency_ID"),
			// 5..9
			new ColumnInfo(Msg.translate(ctx, "GrandTotal"), "i.GrandTotal", BigDecimal.class),
			new ColumnInfo(Msg.translate(ctx, "DiscountAmt"), "C_PaymentTerm_Discount(i.GrandTotal,i.C_PaymentTerm_ID,i.DateInvoiced, ?)", BigDecimal.class),
			new ColumnInfo(Msg.getMsg(ctx, "DiscountDate"), "SysDate-C_PaymentTerm_DueDays(i.C_PaymentTerm_ID,i.DateInvoiced,SysDate)", Timestamp.class),
			new ColumnInfo(Msg.getMsg(ctx, "AmountDue"), "C_Currency_Convert(C_Invoice_Open(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID),i.C_Currency_ID, ?,?,i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID)", BigDecimal.class),
			new ColumnInfo(Msg.getMsg(ctx, "AmountPay"), "C_Currency_Convert(C_Invoice_Open(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID)-C_PaymentTerm_Discount(i.GrandTotal,i.C_PaymentTerm_ID,i.DateInvoiced, ?),i.C_Currency_ID, ?,?,i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID)", BigDecimal.class)
			},
			//	FROM
			"C_Invoice_v i"
			+ " INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)"
			+ " INNER JOIN C_Currency c ON (i.C_Currency_ID=c.C_Currency_ID)"
			+ " INNER JOIN C_PaymentTerm p ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID)",
			//	WHERE
			"i.IsSOTrx='N' AND IsPaid='N'"
			+ " AND NOT EXISTS (SELECT * FROM C_PaySelectionLine psl"
				+ " WHERE i.C_Invoice_ID=psl.C_Invoice_ID AND psl.C_PaySelectionCheck_ID IS NOT NULL)"
			+ " AND i.DocStatus IN ('CO','CL')"
			+ " AND i.AD_Client_ID=?",	//	additional where & order in loadTableInfo() 
			true, "i");
			*/
		//
		m_sql = miniTable.prepareTable(new ColumnInfo[] {
		new ColumnInfo(" ", "p.C_Payment_ID", IDColumn.class, false, false, null),			
		new ColumnInfo(Msg.translate(ctx, "C_BPartner_ID"), "bp.Name", KeyNamePair.class, true, false, "p.C_BPartner_ID"),
		new ColumnInfo(Msg.translate(ctx, "PayDate"), "p.dateacct", Timestamp.class, true, true, null),
		new ColumnInfo(Msg.translate(ctx, "DocumentNo"), "p.DocumentNo", String.class),
		new ColumnInfo(Msg.translate(ctx, "C_Currency_ID"), "c.ISO_Code", KeyNamePair.class, true, false, "p.C_Currency_ID"),
		new ColumnInfo(Msg.translate(ctx, "PayAmt"), "p.payamt", BigDecimal.class),
		new ColumnInfo(Msg.getMsg(ctx, "A Favor"), "C_Payment_Available(C_Payment_ID)", BigDecimal.class)		
		},
		//	FROM
		"C_Payment p"
		+ " INNER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID)"
		+ " INNER JOIN C_Currency c ON (p.C_Currency_ID=c.C_Currency_ID)",
		//	WHERE
		"p.isreceipt = 'Y'"
		+ " and bp.C_BPartner_ID=p.C_BPartner_ID" 
		+ " and p.isactive = 'Y' and p.docstatus = 'CO' and p.docaction = 'CL' and p.payamt > 0"
		+ " and p.isallocated = 'N'",
		//+ " and not exists (Select * from C_allocationline a where a.c_payment_id = p.c_payment_id)",
		true, "p");
		
		miniTable.getModel().addTableModelListener(this);
		//
		fieldPayDate.setMandatory(true);
		fieldPayDate.setValue(new Timestamp(System.currentTimeMillis()));
		//
		m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	}   //  dynInit

	/**
	 *  Load Bank Info - Load Info from Bank Account and valid Documents (PaymentRule)
	 */
	private void loadBankInfo()
	{
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem();
		if (bi == null)
			return;
		labelCurrency.setText(bi.Currency);
		labelBalance.setText(m_format.format(bi.Balance));
		m_bankBalance = bi.Balance;

		//  PaymentRule
		fieldPaymentRule.removeAllItems();
		int AD_Reference_ID = 195;  //  MLookupInfo.getAD_Reference_ID("All_Payment Rule");
		Language language = Env.getLanguage(Env.getCtx());
		MLookupInfo info = MLookupFactory.getLookup_List(language, AD_Reference_ID);
		String sql = info.Query.substring(0, info.Query.indexOf(" ORDER BY"))
			+ " AND " + info.KeyColumn
			+ " IN (SELECT PaymentRule FROM C_BankAccountDoc WHERE C_BankAccount_ID=?) "
			+ info.Query.substring(info.Query.indexOf(" ORDER BY"));
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql);
			pstmt.setInt(1, bi.C_BankAccount_ID);
			ResultSet rs = pstmt.executeQuery();
			ValueNamePair vp = null;
			while (rs.next())
			{
				vp = new ValueNamePair(rs.getString(2), rs.getString(3));   //  returns also not active
				fieldPaymentRule.addItem(vp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log (Level.SEVERE, "VPaySelect.loadBankInfo - SQL=" + sql, e);
		}
		fieldPaymentRule.setSelectedIndex(0);

	}   //  loadBankInfo

	/**
	 *  Query and create TableInfo
	 */
	private void loadTableInfo()
	{
		log.log(Level.INFO, "VPaySelect.loadTableInfo");
		//  not yet initialized
		if (m_sql == null)
			return;

		String sql = m_sql;
		//  Parameters
		Timestamp payDate = (Timestamp)fieldPayDate.getValue();
		miniTable.setColorCompare(payDate);
		log.log(Level.INFO, "PayDate", payDate.toString());
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem();
		//
		//if (onlyDue.isSelected())
		//	sql += " AND i.DateInvoiced+p.NetDays <= ?";
		//
		KeyNamePair pp = (KeyNamePair)fieldBPartner.getSelectedItem();
		//System.out.println("Aqui.... "+ pp.getKey());
		int C_BPartner_ID = pp.getKey();
		if (C_BPartner_ID != 0)
			sql += " AND p.C_BPartner_ID=?";//sql += " AND i.C_BPartner_ID=?"; original
		sql += " ORDER BY 2,3";

		//  Get Open Invoices
		try
		{
			int index = 1;
			PreparedStatement pstmt = DB.prepareStatement(sql);
			//pstmt.setTimestamp(index++, payDate);		//	DiscountAmt
			//pstmt.setInt(index++, bi.C_Currency_ID);	//	DueAmt
			//pstmt.setTimestamp(index++, payDate);		
			//pstmt.setTimestamp(index++, payDate);		//	PayAmt
			//pstmt.setInt(index++, bi.C_Currency_ID);
			//pstmt.setTimestamp(index++, payDate);
			//pstmt.setInt(index++, m_AD_Client_ID);		//	
			//if (onlyDue.isSelected())
				//pstmt.setTimestamp(index++, payDate);
			if (C_BPartner_ID != 0)
				pstmt.setInt(index++, C_BPartner_ID);
			//
			//Log.error ("Checando sql- " + sql);//Juan
			ResultSet rs = pstmt.executeQuery();
			miniTable.loadTable(rs);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log (Level.SEVERE, "VPaySelect.loadTableInfo - " + sql, e);
		}
		calculateSelection();
	}   //  loadTableInfo

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
		//  Update Bank Info
		if (e.getSource() == fieldBankAccount)
			loadBankInfo();

		//  Generate PaySelection
		else if (e.getSource() == bGenerate)
		{
			cmd_generate();
			dispose();
		}

		else if (e.getSource() == bCancel)
			dispose();

		//  Update Open Invoices
		else if (e.getSource() == fieldBPartner || e.getSource() == bRefresh)
			loadTableInfo();

	}   //  actionPerformed

	/**
	 *  Table Model Listener
	 *  @param e event
	 */
	public void tableChanged(TableModelEvent e)
	{
		if (e.getColumn() == 0)
			calculateSelection(); 
	}   //  valueChanged

	/**
	 *  Calculate selected rows.
	 *  - add up selected rows
	 */
	public void calculateSelection()
	{
		m_noSelected = 0;
		BigDecimal invoiceAmt = new BigDecimal(0.0);

		int rows = miniTable.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)miniTable.getModel().getValueAt(i, 0);
			if (id.isSelected())
			{
				BigDecimal amt = (BigDecimal)miniTable.getModel().getValueAt(i, 6);// era 5 Juan
				invoiceAmt = invoiceAmt.add(amt);
				m_noSelected++;
			}
		}

		//  Information
		BigDecimal remaining = m_bankBalance.subtract(invoiceAmt);
		StringBuffer info = new StringBuffer();
		info.append(m_noSelected).append(" ").append(Msg.getMsg(Env.getCtx(), "Selected")).append(" - ");
		info.append(m_format.format(invoiceAmt)).append(", ");
		info.append(Msg.getMsg(Env.getCtx(), "Remaining")).append(" ").append(m_format.format(remaining));
		dataStatus.setText(info.toString());
		//
		bGenerate.setEnabled(m_noSelected != 0);
	}   //  calculateSelection

	/**
	 *  Generate PaySelection
	 */
	private void cmd_generate()
	{
		log.log(Level.INFO, "VPaySelect.cmd_generate");
		miniTable.stopEditor(true);
		if (miniTable.getRowCount() == 0)
			return;
		miniTable.setRowSelectionInterval(0,0);
		calculateSelection();
		if (m_noSelected == 0)
			return;

		String PaymentRule = ((ValueNamePair)fieldPaymentRule.getSelectedItem()).getValue();

		//  Create Header
		m_ps = new MPaySelection(Env.getCtx(), 0, null);
		m_ps.setName (Msg.getMsg(Env.getCtx(), "VPaySelect") + " - " + fieldPayDate.getTimestamp());
		m_ps.setPayDate (fieldPayDate.getTimestamp());
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem();
		m_ps.setC_BankAccount_ID(bi.C_BankAccount_ID);
		m_ps.setIsApproved(true);
		//EXPERT//Juan
		int C_AcctSchema_ID = Env.getContextAsInt(Env.getCtx(), "$C_AcctSchema_ID");
		m_ps.setC_AcctSchema_ID(C_AcctSchema_ID);
		//EXPERT//Juan
		if (!m_ps.save())
		{
			ADialog.error(m_WindowNo, this, "SaveError", Msg.translate(Env.getCtx(), "C_PaySelection_ID"));
			m_ps = null;
			return;
		}
		log.log(Level.INFO, "PaySelect.cmd_generate", m_ps);

		//  Create Lines
		int rows = miniTable.getRowCount();
		int line = 0;
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)miniTable.getModel().getValueAt(i, 0);
			if (id.isSelected())
			{
				line += 10;
				MPaySelectionLine psl = new MPaySelectionLine (m_ps, line, PaymentRule);
				//int C_Invoice_ID = id.getRecord_ID().intValue();
				int C_Payment_ID = id.getRecord_ID().intValue();
				//BigDecimal OpenAmt = (BigDecimal)miniTable.getModel().getValueAt(i, 8);
				//BigDecimal PayAmt = (BigDecimal)miniTable.getModel().getValueAt(i, 9);
				BigDecimal OpenAmt = (BigDecimal)miniTable.getModel().getValueAt(i, 6);
				BigDecimal PayAmt = (BigDecimal)miniTable.getModel().getValueAt(i, 6);
				boolean isSOTrx = false;   //Juan era false
				//
				//psl.setInvoice(C_Invoice_ID, isSOTrx, 
					//OpenAmt, PayAmt, OpenAmt.subtract(PayAmt));
				psl.setPayment(C_Payment_ID, isSOTrx, 
					OpenAmt, PayAmt, OpenAmt.subtract(PayAmt));
				if (!psl.save())
				{
					ADialog.error(m_WindowNo, this, "SaveError", Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID"));
					return;
				}
				log.log(Level.INFO, "PaySelect.cmd_generate", "C_Payment_ID=" + C_Payment_ID + ", PayAmt=" + PayAmt);
			}
		}   //  for all rows in table


		//  Ask to Post it
		if (!ADialog.ask(m_WindowNo, this, "VPaySelectGenerate?", "(" + m_ps.getName() + ")"))
			return;

		//  Run Process PaySelectionCreateCheck
		ProcessInfo pi = new ProcessInfo (m_frame.getTitle(), 155,
			MPaySelection.Table_ID, m_ps.getC_PaySelection_ID());
		pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		ProcessCtl.process(this, m_WindowNo, pi, null);     //  calls lockUI/..
	}   //  cmd_generate

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
		if (!ADialog.ask(m_WindowNo, this, "VPaySelectPrint?", "(" + pi.getSummary() + ")"))
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

	/**
	 *  Is the UI locked (Internal method)
	 *  @return true, if UI is locked
	 */
	public boolean isUILocked()
	{
		return m_isLocked;
	}   //  isLoacked

	/**
	 *  Method to be executed async.
	 *  Called from the ASyncProcess worker
	 *  @param pi process info
	 */
	public void executeASync (ProcessInfo pi)
	{
		log.log(Level.INFO, "VPaySelect.executeASync");
	}   //  executeASync

	
	/**************************************************************************
	 *  Bank Account Info
	 */
	public class BankInfo
	{
		public BankInfo (int newC_BankAccount_ID, int newC_Currency_ID,
			String newName, String newCurrency, BigDecimal newBalance, boolean newTransfers)
		{
			C_BankAccount_ID = newC_BankAccount_ID;
			C_Currency_ID = newC_Currency_ID;
			Name = newName;
			Currency = newCurrency;
			Balance = newBalance;
		}
		int C_BankAccount_ID;
		int C_Currency_ID;
		String Name;
		String Currency;
		BigDecimal Balance;
		boolean Transfers;

		public String toString()
		{
			return Name;
		}
	}   //  BankInfo

}   //  VPaySelect
