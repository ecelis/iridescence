package org.compiere.util.vo;

import java.sql.Timestamp;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.GridItem;

public class ChangeLogVO implements GridItem {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String tableName;
	private String columnName;
	private Timestamp created;
	private IDColumn idColumn;
	private int recordId;
	private int columnId;
	private String oldValue;
	private String newValue;
	private String type;
	private String paciente;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public void setIdColumn(IDColumn idColumn) {
		this.idColumn = idColumn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	@Override
	public String[] getColumns() {
		return new String[] { "tableName", "columnName", "created", "recordId", "paciente", "oldValue", "newValue", "type" };
	}

	@Override
	public IDColumn getIdColumn() {
		return idColumn;
	}

}
