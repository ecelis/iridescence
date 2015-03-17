package com.ecaresoft.log;

/**
 * @author odelarosa
 * 
 */
public class Column {

	private int		id;
	private String	name;
	private String	printName;

	public Column() {}

	public Column(final int id, final String name, final String printName) {
		super();
		this.id = id;
		this.name = name;
		this.printName = printName;
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

	public void setId(final int id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPrintName(final String printName) {
		this.printName = printName;
	}

}
