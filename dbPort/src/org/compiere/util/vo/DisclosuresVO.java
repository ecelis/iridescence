package org.compiere.util.vo;

import java.sql.Timestamp;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.GridItem;

/**
 * 
 * @author Omar de la Rosa
 *
 */
public class DisclosuresVO implements GridItem {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String value;
	private String description;
	private String userName;
	private Timestamp fecha;
	private IDColumn idColumn;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public void setIdColumn(IDColumn idColumn) {
		this.idColumn = idColumn;
	}

	@Override
	public String[] getColumns() {
		return new String[] { "fecha", "userName", "value", "description" };
	}

	@Override
	public IDColumn getIdColumn() {
		return idColumn;
	}

}
