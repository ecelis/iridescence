package com.ecaresoft.log;

import org.compiere.model.MTable;

/**
 * @author odelarosa
 * 
 */
public class Table {

	private int		id;
	private String	name;
	private String	printName;

	public Table() {}

	public Table(final int id, final String name, final String printName) {
		this.id = id;
		this.name = name;
		this.printName = printName;
	}

	public Table(final MTable table) {
		if (table != null) {
			this.id = table.getAD_Table_ID();
			this.name = table.getTableName();
			this.printName = table.getName();
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Table other = (Table) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPrintName() {
		return printName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPrintName(final String printName) {
		this.printName = printName;
	}

	@Override
	public String toString() {
		return "Table [id=" + id + ", name=" + name + ", printName=" + printName + "]";
	}

}
