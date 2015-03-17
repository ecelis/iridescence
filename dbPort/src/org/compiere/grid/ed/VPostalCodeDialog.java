package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.*;
import javax.swing.event.*;
import org.compiere.apps.*;
import org.compiere.apps.search.*;
import org.compiere.minigrid.*;
import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 * Ventana de busqueda del Cat�logo de Colonias / Codigos Postales
 * Regresa un objeto(MEXMEColonia) del registro seleccionado
 * 
 * @author Lorena Lama
 *
 */
public class VPostalCodeDialog extends CDialog implements ListSelectionListener {

	/** Logger */
	private static CLogger log = CLogger.getCLogger(VPostalCodeDialog.class);

	/** Master (owning) Window  */
	protected int				p_WindowNo;
	/** Initial WHERE Clause    */
	protected String			p_whereClause = "";
	/** Table                   */
	protected MiniTable         p_table = new MiniTable();
	/** Model Index of Key Column   */
	private int                 m_keyColumnIndex = -1;
	/** OK pressed                  */
	private boolean			    m_ok = false;
	/** Cancel pressed - need to differentiate between OK - Cancel - Exit	*/
	private boolean			    m_cancel = false;
	/** Result IDs              */
	private MEXMEColonia		m_result = null;
	/** Layout of Grid          */
	protected Info_Column[]     p_layout;
	/** Main SQL Statement      */
	private String              m_sqlMain;
	/** Count SQL Statement		*/
	private String              m_sqlCount;
	/** Loading success indicator       */
	protected boolean	        p_loadedOK = false;

	/** Worker                  */
	private Worker              m_worker = null;

	/** Static Layout           */
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	ConfirmPanel confirmPanel = new ConfirmPanel(true, true, true, true, true, true, true);
	protected StatusBar statusBar = new StatusBar();
	protected CPanel parameterPanel = new CPanel();
	private JScrollPane scrollPane = new JScrollPane();
	
	private CLabel label1 = new CLabel();
	private CTextField textField1 = new CTextField(10);
	private CLabel label2 = new CLabel();
	private CTextField textField2 = new CTextField(10);
	private CLabel label3 = new CLabel();
	private CTextField textField3 = new CTextField(10);
	private CLabel label4 = new CLabel();
	private CTextField textField4 = new CTextField(10);
	private CLabel label5 = new CLabel();
	private CTextField textField5 = new CTextField(10);
	
	/** list of query columns           */
	private ArrayList<Info_Column> displayCols = new ArrayList<Info_Column>();
	private ArrayList<String> columns = new ArrayList<String>();

	protected String p_tableName = X_EXME_Colonia.Table_Name;
	/** Key Column Name         */
	protected String p_keyColumn = coloniaID;

	/** nombres de las columnas de la busqueda */
	public static final String coloniaID="EXME_Colonia_ID";
	public static final String colonia="Colonia";
	public static final String CP="Codigo_Postal";
	public static final String townName="Name";
	public static final String townLbl="Municipio";
	public static final String estado="Description";
	public static final String estadoLbl="Region";	
	public static final String ciudad="Ciudad";
	
	/**  String Array of Column Info    */
	private Info_Column[] m_generalLayout;
	
	public Object getSelectedKey() {
		if (!m_ok || m_result == null)
			return null;
		return m_result;
	}
	
	/**
	 * 
	 * @param frame
	 * @param title
	 * @param location
	 */
	public VPostalCodeDialog(Frame frame, String title) {
		super(frame, title, true);
		try {
			jbInit();
			statInit();
			p_loadedOK = initInfo();

		} catch (Exception ex) {
			log.log(Level.SEVERE, ex.getMessage());
		}
		setTitle(title);
		AEnv.positionCenterWindow(frame, this);
	}

	/**
	 * 
	 * @throws Exception
	 */
	protected void jbInit() throws Exception {

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		southPanel.setLayout(southLayout);
		southPanel.add(confirmPanel, BorderLayout.CENTER);
		southPanel.add(statusBar, BorderLayout.SOUTH);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		this.getContentPane().add(parameterPanel, BorderLayout.NORTH);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(p_table, null);
		confirmPanel.addActionListener(this);
		p_table.getSelectionModel().addListSelectionListener(this);
		confirmPanel.getOKButton().setEnabled(true);
	}
	
	/**
	 * Parametros de busqueda
	 */
	private void statInit() {
		
		setParameters(label1, textField1);
		setParameters(label2, textField2);
		setParameters(label3, textField3);
		setParameters(label4, textField4);
		setParameters(label5, textField5);

		parameterPanel.setLayout(new ALayout());
		parameterPanel.add(label1, new ALayoutConstraint(0,0));
		parameterPanel.add(label2, null);
		parameterPanel.add(label3, null);
		parameterPanel.add(label4, null);
		parameterPanel.add(label5, null);

		parameterPanel.add(textField1, new ALayoutConstraint(1,0));
		parameterPanel.add(textField2, null);
		parameterPanel.add(textField3, null);
		parameterPanel.add(textField4, null);
		parameterPanel.add(textField5, null);
	}
	
	public void setParameters(CLabel label, CTextField textField){
		label.setLabelFor(textField);
		label.setHorizontalAlignment(JLabel.LEADING);
		textField.setBackground(CompierePLAF.getInfoBackground());
		textField.addActionListener(this);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean initInfo() {
		if (!initInfoTable())
			return false;

		//la tabla de los resultados de la busqueda
		prepareTable(m_generalLayout, p_tableName, null, null);

		//titulos de los parametros
		label1.setText(((Info_Column)displayCols.get(1)).getColHeader());//codigoPostal
		label2.setText(((Info_Column)displayCols.get(2)).getColHeader());//colonia
		label3.setText(((Info_Column)displayCols.get(3)).getColHeader());//towncouncil/municipio
		label4.setText(((Info_Column)displayCols.get(4)).getColHeader());//ciudad
		label5.setText(((Info_Column)displayCols.get(5)).getColHeader());//estado/region
		
		return true;
	}
	
	/**
	 * Las columnas a desplegar en la tabla de resultados
	 */
	public void setColumns(){
		
		addColumns(p_tableName,X_EXME_Colonia.Table_ID,coloniaID); //0
		addColumns(p_tableName,X_EXME_Colonia.Table_ID,colonia); //1
		addColumns(p_tableName,X_EXME_Colonia.Table_ID,CP); //2
		addColumns(X_EXME_TownCouncil.Table_Name,X_EXME_TownCouncil.Table_ID,townName);//3
		addColumns(p_tableName,X_EXME_Colonia.Table_ID,ciudad); //4
		addColumns(X_C_Region.Table_Name,X_C_Region.Table_ID,estado); //5
	}
	
	/**
	 * @param tableName .- Nombre de la tabla a la que pertenece la columna de busqueda
	 * @param table_ID  .- ID de la tabla a la que pertenece la columna de busqueda
	 * @param columnName.- Nombre de la Columna de busqueda
	 */
	public void addColumns(String tableName, int table_ID, String columnName) {
		columns.add(tableName+"."+columnName); //para el query
		displayCols.add(new Info_Column(String.valueOf(table_ID),columnName,null));//columnas a desplegar
	}
	
	/**
	 *	Init info with Table.
	 *	- find QueryColumns (Value, Name, ..)
	 *	- build gridController & columsn
	 *  @return true if success
	 */
	private boolean initInfoTable() {
		
		//llena las columnas a desplegar
		setColumns();
		
		// Se asigna el T�tulo a partir de la tabla principal, 
		// busca primero el nombre para la Llave primaria, si no, el de la tabla
		String title = Msg.translate(Env.getCtx(), p_tableName + "_ID");  //  best bet
		if (title.endsWith("_ID"))
			title = Msg.translate(Env.getCtx(), p_tableName);            //  second best bet
		setTitle(getTitle() + " " + title);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		//busca el nombre y tipo de dato para las columnas de la busqueda
		try	{
			sql = "SELECT c.AD_Reference_ID, c.IsKey "
					+ " FROM AD_Column c INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID)"
					+ " WHERE t.AD_Table_ID = ? AND c.ColumnName = ? ORDER BY c.IsKey DESC ";
			
			for (int i = 0; i < displayCols.size(); i++) {
				pstmt = DB.prepareStatement(sql, null);
				Info_Column obj = (Info_Column)displayCols.get(i);
				pstmt.setInt(1, Integer.parseInt(obj.getColHeader()));
				pstmt.setString(2, obj.getColSQL());
				
				rs = pstmt.executeQuery();
				
				while (rs.next()){
					int displayType = rs.getInt(1);
					boolean isKey = rs.getString(2).equals("Y");

					Class colClass = null;

					if (isKey)
						colClass = IDColumn.class;
					else if (displayType == DisplayType.Number || displayType == DisplayType.Quantity)
						colClass = Double.class;
					else if (displayType == DisplayType.Integer)
						colClass = Integer.class;
					else if (displayType == DisplayType.String || displayType == DisplayType.Text || displayType == DisplayType.Memo)
						colClass = String.class;
					else 
						colClass = String.class;
					if (colClass != null) {
						displayCols.get(i).setColClass(colClass);
						//asignamos el titulo de las columnas
						if(obj.getColSQL().equals(estado)){
							displayCols.get(i).setColHeader(Msg.translate(Env.getCtx(), estadoLbl)); // estado
						} else if (obj.getColSQL().equals(townName)) { 
							displayCols.get(i).setColHeader(Msg.translate(Env.getCtx(), townLbl)); // municipio
						} else {
							displayCols.get(i).setColHeader(Msg.translate(Env.getCtx(), obj.getColSQL()));
						}
					}
				}
			}			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
			return false;
		}
		if (displayCols.size() == 0) {
			return false;
		}
		
		// Convert ArrayList to Array
		m_generalLayout = new Info_Column[displayCols.size()];
		displayCols.toArray(m_generalLayout);
		return true;
	} 
	
	/**************************************************************************
	 *  Prepara la Tabla de resultados, se arma el select y tamaño de la Ventana
	 *  @param layout layout array
	 *  @param from from clause
	 *  @param staticWhere where clause
	 *  @param orderBy order by clause
	 */
	protected void prepareTable(Info_Column[] layout, String from, String staticWhere,String orderBy) {
		p_layout = layout;
		StringBuffer sql = new StringBuffer("SELECT ");
		// add columns & sql
		for (int i = 0; i < layout.length; i++) {
			if (i > 0)
				sql.append(", ");
			sql.append(columns.get(i));

			// add to model
			p_table.addColumn(layout[i].getColHeader());
			if (layout[i].isColorColumn())
				p_table.setColorColumn(i);
			if (layout[i].getColClass() == IDColumn.class)
				m_keyColumnIndex = i;
		}
		
		// set editors
		for (int i = 0; i < layout.length; i++)
			p_table.setColumnClass(i, layout[i].getColClass(), layout[i].isReadOnly(), layout[i].getColHeader());

		m_sqlMain = sql.toString();
		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE isActive = 'Y' ";

		// Table Selection
		p_table.setRowSelectionAllowed(true);
		p_table.addMouseListener(this);

		// Window Sizing
		parameterPanel.setPreferredSize(new Dimension(800,45));
		scrollPane.setPreferredSize(new Dimension(800, 400));
	}
	
	public void setStatusLine(String text, boolean error) {
		statusBar.setStatusLine(text, error);
		Thread.yield();
	}
	
	/**
	 * Acci�n de los botones OK/Cancel
	 */
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		if (cmd.equals(ConfirmPanel.A_OK)) {
			dispose(true);
		} else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			m_cancel = true;
			dispose(false);
		} else {
			executeQuery();
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		// Double click with selected row => exit
		if (e.getClickCount() > 1 && p_table.getSelectedRow() != -1) {
			dispose(true); // double_click same as OK
		}
	} 
	
	/**
	 * @param ok
	 */
	public void dispose(boolean ok) {
		m_ok = ok;
		if (m_worker != null) {
			if (m_worker.isAlive())
				m_worker.interrupt();
		}
		m_worker = null;

		saveSelection();
		removeAll();
		super.dispose();
	}
	
	/**
	 * 
	 */
	protected void saveSelection() {
		// Already disposed
		if (p_table == null)
			return;

		if (!m_ok) {
			m_result = null;
			p_table.removeAll();
			p_table = null;
			return;
		}

		//Envia el objeto para el registro seleccionado
		Integer data = getSelectedRowKey();
		if (data != null && data.intValue() > 0)
			m_result = new MEXMEColonia(Env.getCtx(),data.intValue(),null);

		// Clean-up
		p_table.removeAll();
		p_table = null;
	} 
	
	public boolean isCancelled() {
		return m_cancel;
	}
	
	/**
	 * Get the key of currently selected row
	 * 
	 * @return selected key
	 */
	protected Integer getSelectedRowKey() {
		int row = p_table.getSelectedRow();
		if (row != -1 && m_keyColumnIndex != -1) {
			Object data = p_table.getModel().getValueAt(row, m_keyColumnIndex);
			if (data instanceof IDColumn)
				data = ((IDColumn) data).getRecord_ID();
			if (data instanceof Integer)
				return (Integer) data;
		}
		return null;
	} 
	
	/**
	 * Ejecuta la Busqueda
	 */
	void executeQuery() {
		if (m_worker != null && m_worker.isAlive())
			return;
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
		
		if(!testCount())
			return;
		
		m_worker = new Worker();
		m_worker.start();

	}
	
	/**
	 * Clausulas Where
	 * @return
	 */
	String getSQLWhere() {
		StringBuilder sql = new StringBuilder();
		
		addSQLWhere(sql, 1, textField1.getText().toUpperCase());
		addSQLWhere(sql, 2, textField2.getText().toUpperCase());
		addSQLWhere(sql, 3, textField3.getText().toUpperCase());
		addSQLWhere(sql, 4, textField4.getText().toUpperCase());
		addSQLWhere(sql, 5, textField5.getText().toUpperCase());
		return sql.toString();
	}
	
	/**
	 * Arma la condici�n SQL a partir de los parametros
	 * @param sql
	 * @param index
	 * @param value
	 */
	public void addSQLWhere(StringBuilder sql, int index, String value) {
		if (!(value.equals("") || value.equals("%")) && columns.size() > 0) {
			sql.append(" AND UPPER(").append(columns.get(index).toString()).append(") LIKE '");
			sql.append(value);
			if (value.endsWith("%"))
				sql.append("'");
			else
				sql.append("%'");
		}
	}
	
	/**
	 * Revisa que el resultado de la busqueda no supere los 1000 registros
	 * @return
	 */
	private boolean testCount() {

		String dynWhere = getSQLWhere();
		StringBuffer sql = new StringBuffer(m_sqlCount);
		if (dynWhere.length() > 0)
			sql.append(dynWhere);
		int no = -1;
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			//setParameters(pstmt, true);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				no = rs.getInt(1);
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			no = -2;
		}
		if (no > 1000)
			return ADialog.ask(p_WindowNo, this, "InfoHighRecordCount", String.valueOf(no));
		return true;
	} // testCount
	
	/** *************************************************************************** */
	
	/**
	 * Ejecuta la busqueda y cargam en la tabla con los resultados
	 */
	class Worker extends Thread {
		/**
		 *  Do Work (load data)
		 */
		public void run(){

			p_table.setRowCount(0);
			String whereClause = getSQLWhere();
			StringBuilder sql = new StringBuilder ();

			sql.append(m_sqlMain)
				.append(" FROM ").append(p_tableName)
				// Municipio / Towncouncil
				.append(" INNER JOIN ").append(X_EXME_TownCouncil.Table_Name)
					.append(" ON (").append(p_tableName).append(".").append(X_EXME_TownCouncil.Table_Name).append("_ID = ")
					.append(X_EXME_TownCouncil.Table_Name).append(".").append(X_EXME_TownCouncil.Table_Name).append("_ID ")
					.append(" AND ").append(X_EXME_TownCouncil.Table_Name).append(".isActive = 'Y' ) ")
				// Estado / Region
				.append(" INNER JOIN ").append(X_C_Region.Table_Name)
					.append(" ON (").append(p_tableName).append(".").append(X_C_Region.Table_Name).append("_ID = ")
					.append(X_C_Region.Table_Name).append(".").append(X_C_Region.Table_Name).append("_ID ")
					.append(" AND ").append(X_C_Region.Table_Name).append(".isActive = 'Y' ) ")
				
				.append(" WHERE ").append(p_tableName).append(".isActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx()," ",p_tableName)) //Nivel de Acceso
				.append(whereClause);

			try{
				PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
				ResultSet rs = pstmt.executeQuery();
				
				while (!isInterrupted() & rs.next()) {
					int row = p_table.getRowCount();
					p_table.setRowCount(row+1);
					int colOffset = 1;  //  columns start with 1
					for (int col = 0; col < p_layout.length; col++){
						Object data = null;
						Class c = p_layout[col].getColClass();
						int colIndex = col + colOffset;
						if (c == IDColumn.class)
							data = new IDColumn(rs.getInt(colIndex));
						else if (c == Boolean.class)
							data = new Boolean("Y".equals(rs.getString(colIndex)));
						else if (c == Timestamp.class)
							data = rs.getTimestamp(colIndex);
						else if (c == BigDecimal.class)
							data = rs.getBigDecimal(colIndex);
						else if (c == Double.class)
							data = new Double(rs.getDouble(colIndex));
						else if (c == Integer.class)
							data = new Integer(rs.getInt(colIndex));
						else if (c == KeyNamePair.class) {
							String display = rs.getString(colIndex);
							int key = rs.getInt(colIndex + 1);
							data = new KeyNamePair(key, display);
							colOffset++;
						} else
							data = rs.getString(colIndex);
						p_table.setValueAt(data, row, col);
					}
				} 
	
				if (isInterrupted())
					log.finer("Interrupted");
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				log.severe(sql.toString() + " - " + e);
			}
			
			int no = p_table.getRowCount();
			p_table.autoSize();

			setCursor(Cursor.getDefaultCursor());
			setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
			statusBar.setStatusDB(Integer.toString(no));
			if (no == 0)
				log.fine(sql.toString());
			else {
				p_table.getSelectionModel().setSelectionInterval(0, 0);
				p_table.requestFocus();
			}
		}
	}

	public void valueChanged(ListSelectionEvent e) {}
}
