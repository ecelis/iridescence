package org.compiere.model.accessdb;

/**
 * Class Bean
 * 
 * table_Nick, // table alias table_Name, // table id column_Nick, // column
 * alias is not repeated column_Name, // column id column_Type, // type column
 * column_Value // value column
 * 
 * @author Expert
 */
public class MetaData {

	private String label = null;
	private String name = null;
	private String type = null;
	private Object value = null;
	private String tableName = null;
	private String tableLabel = null;

	public MetaData(String tableLabel, String tableName, String label,
			String name, String type, Object value) {
		this.tableName = tableName;
		this.tableLabel = tableLabel;
		this.name = name;
		this.label = label;
		this.type = type;
		this.value = value;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getTableLabel() {
		return tableLabel;
	}

	public void setTableLabel(String tableLabel) {
		this.tableLabel = tableLabel;
	}
}
