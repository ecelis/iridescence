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
package org.compiere.apps.search;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *  Find/Search Records.
 *	Based on AD_Find for persistency, query is build to restrict info
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: Find.java,v 1.3 2006/07/30 00:51:27 jjanke Exp $
 */
public final class Find extends CDialog
		implements ActionListener, ChangeListener, DataStatusListener
{
	private static final long serialVersionUID = 6414604433732835410L;
	
	private int m_AD_Tab_ID;

	/**
	 *	Find Constructor
	 *	@param owner Frame Dialog Onwer
	 *  @param targetWindowNo WindowNo of target window
	 *	@param title 
	 *	@param AD_Table_ID
	 *	@param tableName
	 *	@param whereExtended
	 *	@param findFields
	 *  @param minRecords number of minimum records
	 */
	public Find (Frame owner, int targetWindowNo, String title, int AD_Tab_ID,
		int AD_Table_ID, String tableName, String whereExtended,
		GridField[] findFields, int minRecords)
	{
		super(owner, Msg.getMsg(Env.getCtx(), "Find") + ": " + title, true);
		log.info(title);
		//
		m_targetWindowNo = targetWindowNo;
		m_AD_Tab_ID = AD_Tab_ID; //red1 new field for UserQuery [ 1798539 ]
		m_AD_Table_ID = AD_Table_ID;
		m_tableName = tableName;
		m_whereExtended = whereExtended;
		m_findFields = findFields;
		//
		m_query = new MQuery (tableName);
		m_query.addRestriction(whereExtended);
		//	Required for Column Validation
		Env.setContext(Env.getCtx(), m_targetWindowNo, "Find_Table_ID", m_AD_Table_ID);
		//  Context for Advanced Search Grid is WINDOW_FIND
		Env.setContext(Env.getCtx(), Env.WINDOW_FIND, "Find_Table_ID", m_AD_Table_ID);
		//
		try
		{
			jbInit();
			initFind();
			if (m_total < minRecords)
			{
				dispose();
				return;
			}
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "Find", e);
		}
		//
		this.getRootPane().setDefaultButton(confirmPanelS.getOKButton());
		AEnv.showCenterWindow(owner, this);
	}	//	Find

	/** Target Window No            */
	private int				m_targetWindowNo;
	/**	Table ID					*/
	private int				m_AD_Table_ID;
	/** Table Name					*/
	private String			m_tableName;
	/** Where						*/
	private String			m_whereExtended;
	/** Search Fields          		*/
	private GridField[]		m_findFields;
	/** Resulting query             */
	private MQuery			m_query = null;
	/** Is cancel ?					*/
	private boolean			m_isCancel = false; // teo_sarca [ 1708717 ]
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(Find.class);
	
	/** Number of records			*/
	private int				m_total;
	private PreparedStatement	m_pstmt;
	//
	private	boolean			hasValue = false;
	private boolean			hasDocNo = false;
	private	boolean			hasName = false;
	private	boolean			hasDescription = false;
	/**	Line in Simple Content		*/
	private int				m_sLine = 6;
	
	/**	List of VEditors			*/
	private ArrayList<VEditor>			m_sEditors = new ArrayList<VEditor>();
	/** Target Fields with AD_Column_ID as key  */
	private Hashtable<Integer,GridField>	m_targetFields = new Hashtable<Integer,GridField>();

	/**	For Grid Controller			*/
	public static final int		TABNO = 99;
	/** Length of Fields on first tab	*/
	public static final int		FIELDLENGTH = 20;
	/** Reference ID for Yes/No	*/
	public static final int		AD_REFERENCE_ID_YESNO = 319;
	
	//
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	private StatusBar statusBar = new StatusBar();
	private CTabbedPane tabbedPane = new CTabbedPane();
	private CPanel advancedPanel = new CPanel();
	private BorderLayout advancedLayout = new BorderLayout();
	private ConfirmPanel confirmPanelA = new ConfirmPanel(true, true, false, false, false, false, true);
	private CButton bIgnore = new CButton();
	private JToolBar toolBar = new JToolBar();
	private CComboBox fQueryName = new CComboBox();
	private CButton bSave = new CButton();
	private CButton bNew = new CButton();
	private CButton bDelete = new CButton();
	private ConfirmPanel confirmPanelS = new ConfirmPanel(true);
	private BorderLayout simpleLayout = new BorderLayout();
	private CPanel scontentPanel = new CPanel();
	private GridBagLayout scontentLayout = new GridBagLayout();
	private CPanel simplePanel = new CPanel();
	private CLabel valueLabel = new CLabel();
	private CLabel nameLabel = new CLabel();
	private CLabel descriptionLabel = new CLabel();
	private CTextField valueField = new CTextField();
	private CTextField nameField = new CTextField();
	private CTextField descriptionField = new CTextField();
	private CLabel docNoLabel = new CLabel();
	private CTextField docNoField = new CTextField();
	private Component spaceE;
	private Component spaceN;
	private Component spaceW;
	private Component spaceS;
	private JScrollPane advancedScrollPane = new JScrollPane();
	private CTable advancedTable = new CTable() {

		private static final long serialVersionUID = -6201749159307529032L;

		public boolean isCellEditable(int row, int column)
		{
			boolean editable = ( column == INDEX_COLUMNNAME
					|| column == INDEX_OPERATOR );
			if (!editable && row >= 0)
			{
				String columnName = null;
				Object value =
					getModel().getValueAt(row, INDEX_COLUMNNAME);
				if (value != null) 
				{
					if (value instanceof ValueNamePair)
						columnName = ((ValueNamePair)value).getValue();
					else
						columnName = value.toString();
				}
			    
				//  Create Editor
				editable = getTargetMField(columnName) != null;
			}
			return editable;
		}
	};

	/** Index ColumnName = 0		*/
	public static final int		INDEX_COLUMNNAME = 0;
	/** Index Operator = 1			*/
	public static final int		INDEX_OPERATOR = 1;
	/** Index Value = 2				*/
	public static final int		INDEX_VALUE = 2;
	/** Index Value2 = 3			*/
	public static final int		INDEX_VALUE2 = 3;

	/**	Advanced Search Column 		*/
	public CComboBox 	columns = null;
	/**	Advanced Search Operators 	*/
	public CComboBox 	operators = null;
	private MUserQuery[] userQueries;
	private ValueNamePair[] columnValueNamePairs;
	
	private static final String FIELD_SEPARATOR = "<^>";
	private static final String SEGMENT_SEPARATOR = "<~>";

	/**
	 *	Static Init.
	 *  <pre>
	 *  tabbedPane
	 *      simplePanel
	 *          scontentPanel
	 *          confirmPanelS
	 *      advancedPanel
	 *          toolBar
	 *          GC
	 *          confirmPanelA
	 *  southPanel
	 *      statusBar
	 *  </pre>
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		spaceE = Box.createHorizontalStrut(8);
		spaceN = Box.createVerticalStrut(8);
		spaceW = Box.createHorizontalStrut(8);
		spaceS = Box.createVerticalStrut(8);
		bIgnore.setIcon(new ImageIcon(org.compiere.Compiere.class.getResource("images/24x24/Ignore_24.png")));
		bIgnore.setMargin(new Insets(2, 2, 2, 2));
		bIgnore.setToolTipText(Msg.getMsg(Env.getCtx(),"Ignore"));
		bIgnore.addActionListener(this);
		fQueryName.setToolTipText (Msg.getMsg(Env.getCtx(),"QueryName"));
		fQueryName.setEditable(true);
		fQueryName.addActionListener(this);
		bSave.setIcon(new ImageIcon(org.compiere.Compiere.class.getResource("images/24x24/Save_24.png")));
		bSave.setMargin(new Insets(2, 2, 2, 2));
		bSave.setToolTipText(Msg.getMsg(Env.getCtx(),"Save"));
		bSave.addActionListener(this);
		bNew.setIcon(new ImageIcon(org.compiere.Compiere.class.getResource("images/24x24/New_24.png")));
		bNew.setMargin(new Insets(2, 2, 2, 2));
		bNew.setToolTipText(Msg.getMsg(Env.getCtx(),"New"));
		bNew.addActionListener(this);
		bDelete.setIcon(new ImageIcon(org.compiere.Compiere.class.getResource("images/24x24/Delete_24.png")));
		bDelete.setMargin(new Insets(2, 2, 2, 2));
		bDelete.setToolTipText(Msg.getMsg(Env.getCtx(),"Delete"));
		bDelete.addActionListener(this);
		//
		southPanel.setLayout(southLayout);
		valueLabel.setLabelFor(valueField);
		valueLabel.setText(Msg.translate(Env.getCtx(),"Value"));
		nameLabel.setLabelFor(nameField);
		nameLabel.setText(Msg.translate(Env.getCtx(),"Name"));
		descriptionLabel.setLabelFor(descriptionField);
		descriptionLabel.setText(Msg.translate(Env.getCtx(),"Description"));
		valueField.setText("%");
		valueField.setColumns(FIELDLENGTH);
		nameField.setText("%");
		nameField.setColumns(FIELDLENGTH);
		descriptionField.setText("%");
		descriptionField.setColumns(FIELDLENGTH);
		scontentPanel.setToolTipText(Msg.getMsg(Env.getCtx(),"FindTip"));
		docNoLabel.setLabelFor(docNoField);
		docNoLabel.setText(Msg.translate(Env.getCtx(),"DocumentNo"));
		docNoField.setText("%");
		docNoField.setColumns(FIELDLENGTH);
		advancedScrollPane.setPreferredSize(new Dimension(450, 150));
		southPanel.add(statusBar, BorderLayout.SOUTH);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);
		//
		scontentPanel.setLayout(scontentLayout);
		simplePanel.setLayout(simpleLayout);
		simplePanel.add(confirmPanelS, BorderLayout.SOUTH);
		simplePanel.add(scontentPanel, BorderLayout.CENTER);
		scontentPanel.add(valueLabel,      new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(7, 5, 0, 5), 0, 0));
		scontentPanel.add(nameLabel,    new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(7, 5, 0, 5), 0, 0));
		scontentPanel.add(descriptionLabel,    new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(7, 5, 5, 5), 0, 0));
		scontentPanel.add(valueField,    new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		scontentPanel.add(descriptionField,    new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
		scontentPanel.add(docNoLabel,    new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(7, 5, 0, 5), 0, 0));
		scontentPanel.add(nameField,    new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		scontentPanel.add(docNoField,    new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		//
		scontentPanel.add(spaceE,    new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		scontentPanel.add(spaceN,   new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		scontentPanel.add(spaceW,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		scontentPanel.add(spaceS,  new GridBagConstraints(2, 15, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		//
	//	tabbedPane.add(simplePanel, Msg.getMsg(Env.getCtx(),"Find"));
		tabbedPane.add(simplePanel, Msg.getMsg(Env.getCtx(),"Find"));
		//
		toolBar.add(bIgnore, null);
		toolBar.addSeparator();
		toolBar.add(bNew, null);
		toolBar.add(bDelete, null);
		toolBar.add(fQueryName, null);
		toolBar.add(bSave, null);		
		advancedPanel.setLayout(advancedLayout);
		advancedPanel.add(toolBar, BorderLayout.NORTH);
		advancedPanel.add(confirmPanelA, BorderLayout.SOUTH);
		advancedPanel.add(advancedScrollPane, BorderLayout.CENTER);
		advancedScrollPane.getViewport().add(advancedTable, null);
	//	tabbedPane.add(advancedPanel, Msg.getMsg(Env.getCtx(),"Advanced"));
		tabbedPane.add(advancedPanel, Msg.getMsg(Env.getCtx(),"Advanced"));
		//
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		//
		confirmPanelA.addActionListener(this);
		confirmPanelS.addActionListener(this);
		//
		JButton b = ConfirmPanel.createNewButton(true);
		confirmPanelS.addComponent (b);
		b.addActionListener(this);
		// teo_sarca, [ 1670847 ] Find dialog: closing and canceling need same functionality
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cmd_cancel();
			}
		});
		
	}	//	jbInit

	/**
	 *	Dynamic Init.6
	 *  Set up GridController
	 */
	private void initFind()
	{
		log.config("");

		//	Get Info from target Tab
		for (int i = 0; i < m_findFields.length; i++)
		{
			GridField mField = m_findFields[i];
			String columnName = mField.getColumnName();
			
			// Make Yes-No searchable as list
			if (mField.getVO().displayType == DisplayType.YesNo)
			{
				GridFieldVO vo = mField.getVO();
				GridFieldVO ynvo = vo.clone(vo.ctx, vo.WindowNo, vo.TabNo, vo.AD_Window_ID, vo.AD_Tab_ID, vo.tabReadOnly);
				ynvo.IsDisplayed = true;
				ynvo.displayType = DisplayType.List;
				ynvo.AD_Reference_Value_ID = AD_REFERENCE_ID_YESNO;

				ynvo.lookupInfo = MLookupFactory.getLookupInfo (ynvo.ctx, ynvo.WindowNo, ynvo.AD_Column_ID, ynvo.displayType,
						Env.getLanguage(ynvo.ctx), ynvo.ColumnName, ynvo.AD_Reference_Value_ID,
						ynvo.IsParent, ynvo.ValidationCode);
				ynvo.lookupInfo.InfoFactoryClass = ynvo.InfoFactoryClass;
				
				GridField ynfield = new GridField(ynvo);

				// replace the original field by the YN List field
				m_findFields[i] = ynfield;
				mField = ynfield;
			}

			if (columnName.equals("Value"))
				hasValue = true;
			else if (columnName.equals("Name"))
				hasName = true;
			else if (columnName.equals("DocumentNo"))
				hasDocNo = true;
			else if (columnName.equals("Description"))
				hasDescription = true;
			else if (mField.isSelectionColumn())
				addSelectionColumn (mField);
			else if (columnName.indexOf("Name") != -1)
				addSelectionColumn (mField);

			//  TargetFields
			m_targetFields.put (new Integer(mField.getAD_Column_ID()), mField);
		}   //  for all target tab fields

		//	Disable simple query fields
		valueLabel.setVisible(hasValue);
		valueField.setVisible(hasValue);
		if (hasValue)
			valueField.addActionListener(this);
		docNoLabel.setVisible(hasDocNo);
		docNoField.setVisible(hasDocNo);
		if (hasDocNo)
			docNoField.addActionListener(this);
		nameLabel.setVisible(hasName);
		nameField.setVisible(hasName);
		if (hasName)
			nameField.addActionListener(this);
		descriptionLabel.setVisible(hasDescription);
		descriptionField.setVisible(hasDescription);
		if (hasDescription)
			descriptionField.addActionListener(this);

		//	Get Total
		m_total = getNoOfRecords(null, false);
		setStatusDB (m_total);
		statusBar.setStatusLine("");

		tabbedPane.addChangeListener(this);

		//	Better Labels for OK/Cancel
		confirmPanelA.getOKButton().setToolTipText(Msg.getMsg(Env.getCtx(),"QueryEnter"));
		confirmPanelA.getCancelButton().setToolTipText(Msg.getMsg(Env.getCtx(),"QueryCancel"));
		confirmPanelS.getOKButton().setToolTipText(Msg.getMsg(Env.getCtx(),"QueryEnter"));
		confirmPanelS.getCancelButton().setToolTipText(Msg.getMsg(Env.getCtx(),"QueryCancel"));
	}	//	initFind

	/**
	 * 	Add Selection Column to first Tab
	 * 	@param mField field
	 */
	private void addSelectionColumn (GridField mField)
	{
		log.config(mField.getHeader());
		int displayLength = mField.getDisplayLength();
		if (displayLength > FIELDLENGTH)
			mField.setDisplayLength(FIELDLENGTH);
		else
			displayLength = 0;
		
		//	Editor
		VEditor editor = null;
		if (mField.isLookup())
		{
			VLookup vl = new VLookup(mField.getColumnName(), false, false, true,
				mField.getLookup());
			//setting mField to avoid NPE
			vl.setField(mField);

			vl.setName(mField.getColumnName());
			editor = vl;
		}
		else
		{
			editor = VEditorFactory.getEditor(mField, false);
			editor.setMandatory(false);
			editor.setReadWrite(true);
		}
		// Add action listener to custom text fields - teo_sarca [ 1709292 ]
		if (editor instanceof CTextField) {
			((CTextField)editor).addActionListener(this);
		}
		CLabel label = VEditorFactory.getLabel(mField);
		//
		if (displayLength > 0)		//	set it back
			mField.setDisplayLength(displayLength);
		//
		m_sLine++;
		if (label != null)	//	may be null for Y/N
			scontentPanel.add(label,   new GridBagConstraints(1, m_sLine, 1, 1, 0.0, 0.0
				,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(7, 5, 5, 5), 0, 0));
		scontentPanel.add((Component)editor,   new GridBagConstraints(2, m_sLine, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
		m_sEditors.add(editor);
	}	//	addSelectionColumn


	/**
	 *  Init Find GridController
	 */
	private void initFindAdvanced()
	{
		log.config("");
		advancedTable.setModel(new DefaultTableModel(0, 4));
		advancedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		advancedTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		advancedTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		TableCellRenderer renderer = new ProxyRenderer(advancedTable.getDefaultRenderer(Object.class));
		advancedTable.setDefaultRenderer(Object.class, renderer);
		
		InputMap im = advancedTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
		final Action tabAction = advancedTable.getActionMap().get(im.get(tab));
		Action tabActionWrapper = new AbstractAction()
        {
			private static final long serialVersionUID = -6868476640719619801L;

            public void actionPerformed(ActionEvent e)
            {
            	tabAction.actionPerformed(e);
            	
                JTable table = (JTable)e.getSource();
                table.requestFocusInWindow();
            }
        };
        advancedTable.getActionMap().put(im.get(tab), tabActionWrapper);
        KeyStroke shiftTab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK);
        final Action shiftTabAction = advancedTable.getActionMap().get(im.get(shiftTab));
		Action shiftTabActionWrapper = new AbstractAction()
        {
			private static final long serialVersionUID = 5493691483070046620L;

            public void actionPerformed(ActionEvent e)
            {
            	shiftTabAction.actionPerformed(e);
            	
                JTable table = (JTable)e.getSource();
                table.requestFocusInWindow();
            }
        };
        advancedTable.getActionMap().put(im.get(shiftTab), shiftTabActionWrapper);
		
		//	0 = Columns
		ArrayList<ValueNamePair> items = new ArrayList<ValueNamePair>();
		for (int c = 0; c < m_findFields.length; c++)
		{
			GridField field = m_findFields[c];
			String columnName = field.getColumnName();
			String header = field.getHeader();
			if (header == null || header.length() == 0)
			{
				header = Msg.translate(Env.getCtx(), columnName);
				if (header == null || header.length() == 0)
					continue;
			}
			if (field.isKey())
				header += (" (ID)");
			ValueNamePair pp = new ValueNamePair(columnName, header);
		//	System.out.println(pp + " = " + field);
			items.add(pp);
		}
		columnValueNamePairs = new ValueNamePair[items.size()];
		items.toArray(columnValueNamePairs);
		Arrays.sort(columnValueNamePairs);		//	sort alpha
		columns = new CComboBox(columnValueNamePairs);
		columns.addActionListener(this);		
		TableColumn tc = advancedTable.getColumnModel().getColumn(INDEX_COLUMNNAME);
		tc.setPreferredWidth(150);
		FindCellEditor dce = new FindCellEditor(columns); 

		dce.addCellEditorListener(new CellEditorListener()
		{
			public void editingCanceled(ChangeEvent ce)
			{
			}
		 
			public void editingStopped(ChangeEvent ce)
			{
				int col = advancedTable.getSelectedColumn();
				int row = advancedTable.getSelectedRow();
				if (col == INDEX_COLUMNNAME && row >= 0)
				{
					advancedTable.setValueAt(null, row, INDEX_VALUE);
					advancedTable.setValueAt(null, row, INDEX_VALUE2);
				}
			}
		});
		tc.setCellEditor(dce);
		tc.setHeaderValue(Msg.translate(Env.getCtx(), "AD_Column_ID"));

		//	1 = Operators
		operators = new CComboBox(MQuery.OPERATORS);
		tc = advancedTable.getColumnModel().getColumn(INDEX_OPERATOR);
		tc.setPreferredWidth(40);
		dce = new FindCellEditor(operators);
		tc.setCellEditor(dce);
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "Operator"));

		// 	2 = QueryValue
		tc = advancedTable.getColumnModel().getColumn(INDEX_VALUE);
		FindValueEditor fve = new FindValueEditor(this, false);		
		tc.setCellEditor(fve);
		tc.setCellRenderer(new ProxyRenderer(new FindValueRenderer(this, false)));
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "QueryValue"));

		// 	3 = QueryValue2
		tc = advancedTable.getColumnModel().getColumn(INDEX_VALUE2);
		tc.setPreferredWidth(50);
		fve = new FindValueEditor(this, false);
		tc.setCellEditor(fve);
		tc.setCellRenderer(new ProxyRenderer(new FindValueRenderer(this, false)));
		tc.setHeaderValue(Msg.getMsg(Env.getCtx(), "QueryValue2"));
		
		AutoCompletion.enable(columns);
		AutoCompletion.enable(operators);
		
		//user query
		userQueries = MUserQuery.get(Env.getCtx(), m_AD_Tab_ID);
		String[] queries = new String[userQueries.length];
		for (int i = 0; i < userQueries.length; i++)
			queries[i] = userQueries[i].getName();
		fQueryName.setModel(new DefaultComboBoxModel(queries));
		fQueryName.setValue("");

		//	No Row - Create one
		cmd_new();
	}   //  initFindAdvanced

	/**
	 *	Dispose window
	 */
	public void dispose()
	{
		log.config("");

		//  Find SQL
		if (m_pstmt != null)
		{
			try {
				m_pstmt.close();
			} catch (SQLException e)	{}
		}
		m_pstmt = null;

		// Remove action listener from custom fields - teo_sarca [ 1709292 ]
		for (VEditor editor : m_sEditors) {
			if (editor instanceof CTextField)
				((CTextField)editor).removeActionListener(this);
		}
		//  TargetFields
		if (m_targetFields != null)
			m_targetFields.clear();
		m_targetFields = null;
		//
		removeAll();
		super.dispose();
	}	//	dispose

	
	/**************************************************************************
	 *	Action Listener
	 *  @param e ActionEvent
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.info(e.getActionCommand());
		//
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			cmd_cancel();
		else if (e.getActionCommand().equals(ConfirmPanel.A_REFRESH))
			cmd_refresh();
		//
		else if (e.getActionCommand().equals(ConfirmPanel.A_NEW))
		{
			m_query = MQuery.getNoRecordQuery(m_tableName, true);
			m_total = 0;
			dispose();
		}
		//
		else if (e.getSource() == bIgnore)
			cmd_ignore();
		else if (e.getSource() == bNew)
			cmd_new();
		else if (e.getSource() == bSave)
			cmd_save(true);
		else if (e.getSource() == bDelete)
			cmd_delete();
		//
		else if (e.getSource() == columns)
		{
			String columnName = null;
			Object selected = columns.getSelectedItem();
			if (selected != null) 
			{
				if (selected instanceof ValueNamePair) 
				{
					ValueNamePair column = (ValueNamePair)selected;
					columnName = column.getValue();
				}
				else
				{
					columnName = selected.toString();
				}
			}
			
			if (columnName != null)
			{
				log.config("Column: " + columnName);
				if (columnName.endsWith("_ID") || columnName.endsWith("_Acct"))
					operators.setModel(new DefaultComboBoxModel(MQuery.OPERATORS_ID));
				else if (columnName.startsWith("Is"))
					operators.setModel(new DefaultComboBoxModel(MQuery.OPERATORS_YN));
				else
					operators.setModel(new DefaultComboBoxModel(MQuery.OPERATORS));
			}
		}
		else if (e.getSource() == fQueryName) 
		{
			Object o = fQueryName.getSelectedItem();
			if (userQueries != null && o != null)
			{
				String selected = o.toString();
				for (int i = 0; i < userQueries.length; i++) 
				{
					if (userQueries[i].getName().equals(selected))
					{
						parseUserQuery(userQueries[i]);
						return;
					}
				}
			}
		}
		else    // ConfirmPanel.A_OK and enter in fields
		{
			if (e.getSource() == confirmPanelA.getOKButton())
				cmd_ok_Advanced();
			else if (e.getSource() == confirmPanelS.getOKButton())
				cmd_ok_Simple();
			else if (e.getSource() instanceof JTextField &&
					tabbedPane.getSelectedIndex() == 0)
				cmd_ok_Simple();
		}
	}	//	actionPerformed

	private void parseUserQuery(MUserQuery userQuery) {
		String code = userQuery.getCode();
		String[] segments = code.split(Pattern.quote(SEGMENT_SEPARATOR));
		advancedTable.stopEditor(true);
		DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
		int cnt = model.getRowCount();
		for (int i = cnt - 1; i >=0; i--)
			model.removeRow(i);
		
		for (int i = 0; i < segments.length; i++)
		{
			String[] fields = segments[i].split(Pattern.quote(FIELD_SEPARATOR));
			model.addRow(new Object[] {null, MQuery.OPERATORS[MQuery.EQUAL_INDEX], null, null});
			String columnName = null;
			for (int j = 0; j < fields.length; j++)
			{
				if (j == INDEX_COLUMNNAME)
				{
					for (ValueNamePair vnp : columnValueNamePairs)
					{
						if (vnp.getValue().equals(fields[j]))
						{
							model.setValueAt(vnp, i, j);
							columnName = fields[j];
							break;
						}
					}
				}
				else if (j == INDEX_OPERATOR)
				{
					for (ValueNamePair vnp : MQuery.OPERATORS)
					{
						if (vnp.getValue().equals(fields[j]))
						{
							model.setValueAt(vnp, i, j);
							break;
						}
					}
				}
				else
				{
					GridField field = getTargetMField(columnName);
					Object value = parseString(field, fields[j]);
					model.setValueAt(value, i, j);
				}
			}
		}
		advancedTable.invalidate();
	}

	/**
	 *  Change Listener (tab change)
	 *  @param e ChangeEbent
	 */
	public void stateChanged(ChangeEvent e)
	{
	//	log.info( "Find.stateChanged");
		if (tabbedPane.getSelectedIndex() == 0)
			this.getRootPane().setDefaultButton(confirmPanelS.getOKButton());
		else
		{
			initFindAdvanced();
			this.getRootPane().setDefaultButton(confirmPanelA.getOKButton());
			advancedTable.requestFocusInWindow();
		}
	}	//  stateChanged

	/**
	 *	Simple OK Button pressed
	 */
	private void cmd_ok_Simple()
	{
		//	Create Query String
		m_query = new MQuery(m_tableName);
		if (hasValue && !valueField.getText().equals("%") && valueField.getText().length() != 0)
		{
			String value = valueField.getText().toUpperCase();
			
			if (!value.endsWith("%"))
				value += "%";
			m_query.addRestriction("UPPER(Value)", MQuery.LIKE, value, valueLabel.getText(), value);
		}
		//
		if (hasDocNo && !docNoField.getText().equals("%") && docNoField.getText().length() != 0)
		{
			String value = docNoField.getText().toUpperCase();
			
			if (!value.endsWith("%"))
				value += "%";
			m_query.addRestriction("UPPER(DocumentNo)", MQuery.LIKE, value, docNoLabel.getText(), value);
		}
		//
		if ((hasName) && !nameField.getText().equals("%") && nameField.getText().length() != 0)
		{
			String value = nameField.getText().toUpperCase();
			
			if (!value.endsWith("%"))
				value += "%";
			m_query.addRestriction("UPPER(Name)", MQuery.LIKE, value, nameLabel.getText(), value);
		}
		//
		if (hasDescription && !descriptionField.getText().equals("%") && descriptionField.getText().length() != 0)
		{
			String value = descriptionField.getText().toUpperCase();
			if (!value.endsWith("%"))
				value += "%";
			m_query.addRestriction("UPPER(Description)", MQuery.LIKE, value, descriptionLabel.getText(), value);
		}
		//	Special Editors
		for (int i = 0; i < m_sEditors.size(); i++)
		{
			VEditor ved = (VEditor)m_sEditors.get(i);
			Object value = ved.getValue();
			if (value != null && value.toString().length() > 0)
			{
				String ColumnName = ((Component)ved).getName ();
				log.fine(ColumnName + "=" + value);
				
				// globalqss - Carlos Ruiz - 20060711
				// fix a bug with virtualColumn + isSelectionColumn not yielding results
				GridField field = getTargetMField(ColumnName);
				boolean isProductCategoryField = isProductCategoryField(field.getAD_Column_ID());
				String ColumnSQL = field.getColumnSQL(false);
				if (value.toString().indexOf('%') != -1)
					m_query.addRestriction(ColumnSQL, MQuery.LIKE, value, ColumnName, ved.getDisplay());
				else if (isProductCategoryField && value instanceof Integer) 
					m_query.addRestriction(getSubCategoryWhereClause(((Integer) value).intValue()));
				else
					m_query.addRestriction(ColumnSQL, MQuery.EQUAL, value, ColumnName, ved.getDisplay());
				/*
				if (value.toString().indexOf('%') != -1)
					m_query.addRestriction(ColumnName, MQuery.LIKE, value, ColumnName, ved.getDisplay());
				else
					m_query.addRestriction(ColumnName, MQuery.EQUAL, value, ColumnName, ved.getDisplay());
				*/
				// end globalqss patch
			}
		}	//	editors

		m_isCancel = false; // teo_sarca [ 1708717 ]
		//	Test for no records
		if (getNoOfRecords(m_query, true) != 0)
			dispose();
	}	//	cmd_ok_Simple

	
	/**
	 *	Advanced OK Button pressed
	 */
	private void cmd_ok_Advanced()
	{
		m_isCancel = false; // teo_sarca [ 1708717 ]
		//	save pending
		if (bSave.isEnabled())
			cmd_save(false);
		if (getNoOfRecords(m_query, true) != 0)
			dispose();
	}	//	cmd_ok_Advanced

	/**
	 *	Cancel Button pressed
	 */
	private void cmd_cancel()
	{
		advancedTable.stopEditor(false);
		log.info("");
		m_query = null;
		m_total = 999999;
		m_isCancel = true; // teo_sarca [ 1708717 ]
		dispose();
	}	//	cmd_ok

	/**
	 *	Ignore
	 */
	private void cmd_ignore()
	{
		log.info("");
	}	//	cmd_ignore

	/**
	 *	New record
	 */
	private void cmd_new()
	{
		advancedTable.stopEditor(true);
		DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
		model.addRow(new Object[] {null, MQuery.OPERATORS[MQuery.EQUAL_INDEX], null, null});
		advancedTable.requestFocusInWindow();
	}	//	cmd_new

	/**
	 *	Save (Advanced)
	 */
	private void cmd_save(boolean saveQuery)
	{
		advancedTable.stopEditor(true);
		//
		m_query = new MQuery(m_tableName);
		StringBuffer code = new StringBuffer();
		for (int row = 0; row < advancedTable.getRowCount(); row++)
		{
			//	Column
			Object column = advancedTable.getValueAt(row, INDEX_COLUMNNAME);
			if (column == null)
				continue;
			String ColumnName = column instanceof ValueNamePair ? 
					((ValueNamePair)column).getValue() : column.toString();
			String infoName = column.toString();
			//
			GridField field = getTargetMField(ColumnName);
			if (field == null)
				continue;
			boolean isProductCategoryField = isProductCategoryField(field.getAD_Column_ID());
			String ColumnSQL = field.getColumnSQL(false);
			//	Op
			Object op = advancedTable.getValueAt(row, INDEX_OPERATOR);
			if (op == null)
				continue;
			String Operator = ((ValueNamePair)op).getValue();
			
			//	Value	******
			Object value = advancedTable.getValueAt(row, INDEX_VALUE);
			if (value == null)
				continue;
			Object parsedValue = parseValue(field, value);
			if (parsedValue == null)
				continue;
			String infoDisplay = value.toString();
			if (field.isLookup())
				infoDisplay = field.getLookup().getDisplay(value);
			else if (field.getDisplayType() == DisplayType.YesNo)
				infoDisplay = Msg.getMsg(Env.getCtx(), infoDisplay);
			//	Value2	******
			Object value2 = null;
			if (MQuery.OPERATORS[MQuery.BETWEEN_INDEX].equals(op))
			{
				value2 = advancedTable.getValueAt(row, INDEX_VALUE2);
				if (value2 == null)
					continue;
				Object parsedValue2 = parseValue(field, value2);
				String infoDisplay_to = value2.toString();
				if (parsedValue2 == null)
					continue;
				m_query.addRangeRestriction(ColumnSQL, parsedValue, parsedValue2,
					infoName, infoDisplay, infoDisplay_to);
			}
			else if (isProductCategoryField && MQuery.OPERATORS[MQuery.EQUAL_INDEX].equals(op)) {
				if (!(parsedValue instanceof Integer)) {
					continue;
				}
				m_query

				.addRestriction(getSubCategoryWhereClause(((Integer) parsedValue).intValue()));
			}
			else
				m_query.addRestriction(ColumnSQL, Operator, parsedValue,
					infoName, infoDisplay);
			
			if (code.length() > 0)
				code.append(SEGMENT_SEPARATOR);
			code.append(ColumnName)
				.append(FIELD_SEPARATOR)
				.append(Operator)
				.append(FIELD_SEPARATOR)
				.append(value.toString())
				.append(FIELD_SEPARATOR)
				.append(value2 != null ? value2.toString() : "");
		}
		Object selected = fQueryName.getSelectedItem();
		if (selected != null && saveQuery) {
			String name = selected.toString();
			MUserQuery uq = MUserQuery.get(Env.getCtx(), m_AD_Tab_ID, name);
			if (uq == null && code.length() > 0)
			{				
				uq = new MUserQuery (Env.getCtx(), 0, null);
				uq.setName (name);
				uq.setAD_Tab_ID(m_AD_Tab_ID); //red1 UserQuery [ 1798539 ] taking in new field from Compiere
				uq.setAD_User_ID(Env.getAD_User_ID(Env.getCtx())); //red1 - [ 1798539 ] missing in Compiere delayed source :-)
			}			
			else if (uq != null && code.length() == 0) 
			{
				if (uq.delete(true))
				{
					ADialog.info (m_targetWindowNo, this, "Deleted", name);
					refreshUserQueries();
				}
				else
					ADialog.warn (m_targetWindowNo, this, "DeleteError", name);
				return;
			}
			else
				return;
			uq.setCode (code.toString());
			uq.setAD_Table_ID (m_AD_Table_ID);
			//
			if (uq.save())
			{
				ADialog.info (m_targetWindowNo, this, "Saved", name);
				refreshUserQueries();
			}
			else
				ADialog.warn (m_targetWindowNo, this, "SaveError", name);
		}
	}	//	cmd_save

	private void refreshUserQueries() 
	{
		Object selected = fQueryName.getSelectedItem();
		userQueries = MUserQuery.get(Env.getCtx(), m_AD_Tab_ID);
		String[] queries = new String[userQueries.length];
		for (int i = 0; i < userQueries.length; i++)
			queries[i] = userQueries[i].getName();
		fQueryName.setModel(new DefaultComboBoxModel(queries));
		fQueryName.setSelectedItem(selected);
		if (fQueryName.getSelectedIndex() < 0)
			fQueryName.setValue("");
	}
	
	/**
	 * Checks the given column.
	 * @param columnId
	 * @return true if the column is a product category column
	 */
	private boolean isProductCategoryField(int columnId) {
		X_AD_Column col = new X_AD_Column(Env.getCtx(), columnId, null);
		if (col.get_ID() == 0) {
			return false; // column not found...
		}
		// expert : mrojas : integracion ZK, necesario aplicar cambios a tabla de productos
		return "M_Product_Category_ID".equals(col.getColumnName());
	}

	/**
	 * Returns a sql where string with the given category id and all of its subcategory ids.
	 * It is used as restriction in MQuery.
	 * @param productCategoryId
	 * @return
	 */
	private String getSubCategoryWhereClause(int productCategoryId) {
		//if a node with this id is found later in the search we have a loop in the tree
		int subTreeRootParentId = 0;
		String retString = " M_Product_Category_ID IN (";
		String sql = " SELECT M_Product_Category_ID, M_Product_Category_Parent_ID FROM M_Product_Category";
		final Vector<SimpleTreeNode> categories = new Vector<SimpleTreeNode>(100);
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if(rs.getInt(1)==productCategoryId) {
					subTreeRootParentId = rs.getInt(2);
				}
				categories.add(new SimpleTreeNode(rs.getInt(1), rs.getInt(2)));
			}
			retString += getSubCategoriesString(productCategoryId, categories, subTreeRootParentId);
			retString += ") ";
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
			retString = "";
		} catch (CompiereSystemError e) {
			log.log(Level.SEVERE, sql, e);
			retString = "";
		}
		finally {
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}
		return retString;
	}

	/**
	 * Recursive search for subcategories with loop detection.
	 * @param productCategoryId
	 * @param categories
	 * @param loopIndicatorId
	 * @return comma seperated list of category ids
	 * @throws AdempiereSystemError if a loop is detected
	 */
	private String getSubCategoriesString(int productCategoryId, Vector<SimpleTreeNode> categories, int loopIndicatorId) throws CompiereSystemError {
		String ret = "";
		final Iterator<SimpleTreeNode> iter = categories.iterator();
		while (iter.hasNext()) {
			SimpleTreeNode node = (SimpleTreeNode) iter.next();
			if (node.getParentId() == productCategoryId) {
				if (node.getNodeId() == loopIndicatorId) {
					throw new CompiereSystemError("The product category tree contains a loop on categoryId: " + loopIndicatorId);
				}
				ret = ret + getSubCategoriesString(node.getNodeId(), categories, loopIndicatorId) + ",";
			}
		}
		log.fine(ret);
		return ret + productCategoryId;
	}

	/**
	 * Simple tree node class for product category tree search.
	 * @author Karsten Thiemann, kthiemann@adempiere.org
	 *
	 */
	private class SimpleTreeNode {

		private int nodeId;

		private int parentId;

		public SimpleTreeNode(int nodeId, int parentId) {
			this.nodeId = nodeId;
			this.parentId = parentId;
		}

		public int getNodeId() {
			return nodeId;
		}

		public int getParentId() {
			return parentId;
		}
	}


	/**
	 * 	Parse Value
	 * 	@param field column
	 * 	@param in value
	 * 	@return data type corected value
	 */
	private Object parseValue (GridField field, Object in)
	{
		if (in == null)
			return null;
		int dt = field.getDisplayType();
		try
		{
			//	Return Integer
			if (dt == DisplayType.Integer
				|| (DisplayType.isID(dt) && field.getColumnName().endsWith("_ID")))
			{
				if (in instanceof Integer)
					return in;
				int i = Integer.parseInt(in.toString());
				return new Integer(i);
			}
			//	Return BigDecimal
			else if (DisplayType.isNumeric(dt))
			{
				if (in instanceof BigDecimal)
					return in;
				return DisplayType.getNumberFormat(dt).parse(in.toString());
			}
			//	Return Timestamp
			else if (DisplayType.isDate(dt))
			{
				if (in instanceof Timestamp)
					return in;
				long time = 0;
				try
				{
					time = DisplayType.getDateFormat_JDBC().parse(in.toString()).getTime();
					return new Timestamp(time);
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, in + "(" + in.getClass() + ")" + e);
					time = DisplayType.getDateFormat(dt).parse(in.toString()).getTime();
				}
				return new Timestamp(time);
			}
			//	Return Y/N for Boolean
			else if (in instanceof Boolean)
				return ((Boolean)in).booleanValue() ? "Y" : "N";
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "Object=" + in, ex);
			String error = ex.getLocalizedMessage();
			if (error == null || error.length() == 0)
				error = ex.toString();
			StringBuffer errMsg = new StringBuffer();
			errMsg.append(field.getColumnName()).append(" = ").append(in).append(" - ").append(error);
			//
			ADialog.error(0, this, "ValidationError", errMsg.toString());
			return null;
		}

		return in;
	}	//	parseValue

	/**
	 * 	Parse String
	 * 	@param field column
	 * 	@param in value
	 * 	@return data type corected value
	 */
	private Object parseString(GridField field, String in)
	{
		if (in == null)
			return null;
		int dt = field.getDisplayType();
		try
		{
			//	Return Integer
			if (dt == DisplayType.Integer
				|| (DisplayType.isID(dt) && field.getColumnName().endsWith("_ID")))
			{
				int i = Integer.parseInt(in);
				return new Integer(i);
			}
			//	Return BigDecimal
			else if (DisplayType.isNumeric(dt))
			{
				return DisplayType.getNumberFormat(dt).parse(in);
			}
			//	Return Timestamp
			else if (DisplayType.isDate(dt))
			{
				long time = 0;
				try
				{
					time = DisplayType.getDateFormat_JDBC().parse(in).getTime();
					return new Timestamp(time);
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, in + "(" + in.getClass() + ")" + e);
					time = DisplayType.getDateFormat(dt).parse(in).getTime();
				}
				return new Timestamp(time);
			}
			else if (dt == DisplayType.YesNo)
				return Boolean.valueOf(in);
			else
				return in;
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "Object=" + in, ex);
			return null;
		}

	}	//	parseValue
	/**
	 *	Delete
	 */
	private void cmd_delete()
	{
		advancedTable.stopEditor(false);
		DefaultTableModel model = (DefaultTableModel)advancedTable.getModel();
		int row = advancedTable.getSelectedRow();
		if (row >= 0)
			model.removeRow(row);
		cmd_refresh();		
		advancedTable.requestFocusInWindow();
	}	//	cmd_delete

	/**
	 *	Refresh
	 */
	private void cmd_refresh()
	{
		advancedTable.stopEditor(false);
		int records = getNoOfRecords(m_query, true);
		setStatusDB (records);
		statusBar.setStatusLine("");
	}	//	cmd_refresh

	
	/**************************************************************************
	 *	Get Query - Retrieve result
	 *  @return String representation of query
	 */
	public MQuery getQuery()
	{
		MRole role = MRole.getDefault();
		if (role.isQueryMax(getTotalRecords()) && !m_isCancel)
		{
			m_query = MQuery.getNoRecordQuery (m_tableName, false);
			m_total = 0;
			log.warning("Query - over max");
		}
		else
			log.info("Query=" + m_query);
		return m_query;
	}	//	getQuery

	/**
	 * 	Get Total Records
	 *	@return no of records
	 */
	public int getTotalRecords()
	{
		return m_total;
	}	//	getTotalRecords
	
	/**
	 *	Get the number of records of target tab
	 *  @param query where clause for target tab
	 * 	@param alertZeroRecords show dialog if there are no records
	 *  @return number of selected records;
	 *          if the results are more then allowed this method will return 0
	 */
	private int getNoOfRecords (MQuery query, boolean alertZeroRecords)
	{
		log.config("" + query);
		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM ");
		sql.append(m_tableName);
		boolean hasWhere = false;
		if (m_whereExtended != null && m_whereExtended.length() > 0)
		{
			sql.append(" WHERE ").append(m_whereExtended);
			hasWhere = true;
		}
		if (query != null && query.isActive())
		{
			if (hasWhere)
				sql.append(" AND ");
			else
				sql.append(" WHERE ");
			sql.append(query.getWhereClause());
		}
		//	Add Access
		String finalSQL = MRole.getDefault().addAccessSQL(sql.toString(), 
			m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		finalSQL = Env.parseContext(Env.getCtx(), m_targetWindowNo, finalSQL, false);
		Env.setContext(Env.getCtx(), m_targetWindowNo, TABNO, "FindSQL", finalSQL);

		//  Execute Qusery
		m_total = 999999;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = DB.createStatement();
			rs = stmt.executeQuery(finalSQL);
			if (rs.next())
				m_total = rs.getInt(1);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, finalSQL, e);
		}
		finally {
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}
		MRole role = MRole.getDefault(); 
		//	No Records
		if (m_total == 0 && alertZeroRecords)
			ADialog.info(m_targetWindowNo, this, "FindZeroRecords");
		//	More then allowed
		else if (query != null && role.isQueryMax(m_total))
		{
			ADialog.error(m_targetWindowNo, this, "FindOverMax", 
				m_total + " > " + role.getMaxQueryRecords());
			m_total = 0; // return 0 if more then allowed - teo_sarca [ 1708717 ]
		}
		else
			log.config("#" + m_total);
		//
		if (query != null)
			statusBar.setStatusToolTip (query.getWhereClause());
		return m_total;
	}	//	getNoOfRecords

	/**
	 *	Display current count
	 *  @param currentCount String representation of current/total
	 */
	private void setStatusDB (int currentCount)
	{
		String text = " " + currentCount + " / " + m_total + " ";
		statusBar.setStatusDB(text);
	}	//	setDtatusDB

	
	/**************************************************************************
	 *	Grid Status Changed.
	 *  @param e DataStatueEvent
	 */
	public void dataStatusChanged (DataStatusEvent e)
	{
		log.config(e.getMessage());
		//	Action control
		boolean changed = e.isChanged();
		bIgnore.setEnabled(changed);
		bNew.setEnabled(!changed);
		bSave.setEnabled(changed);
		bDelete.setEnabled(!changed);
	}	//	statusChanged

	/**
	 * 	Get Target MField
	 * 	@param columnName column name
	 * 	@return MField
	 */
	public GridField getTargetMField (String columnName)
	{
		if (columnName == null)
			return null;
		for (int c = 0; c < m_findFields.length; c++)
		{
			GridField field = m_findFields[c];
			if (columnName.equals(field.getColumnName()))
				return field;
		}
		return null;
	}	//	getTargetMField
	
	private class ProxyRenderer implements TableCellRenderer
	{
		/**
		 * Creates a Find.ProxyRenderer.
		 */
		public ProxyRenderer(TableCellRenderer renderer)
		{
			this.m_renderer = renderer;
		}
	        
		/** The renderer. */
		private TableCellRenderer m_renderer;
	       
		/**
		 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		public Component getTableCellRendererComponent(final JTable table,
				Object value, boolean isSelected, boolean hasFocus, final int row, final int col)
		{
			Component comp = m_renderer.getTableCellRendererComponent(table,
					value, isSelected, hasFocus, row, col);
			if (hasFocus && table.isCellEditable(row, col))
				table.editCellAt(row, col);
			return comp;
		}
	}	// ProxyRenderer

}	//	Find