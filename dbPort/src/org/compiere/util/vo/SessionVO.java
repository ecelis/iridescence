package org.compiere.util.vo;

import java.sql.Timestamp;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.GridItem;

public class SessionVO implements GridItem {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private IDColumn idColumn;
	private String userName;
	private String remoteAdress;
	private Timestamp created;
	private String description;

	public IDColumn getIdColumn() {
		return idColumn;
	}

	public void setIdColumn(IDColumn idColumn) {
		this.idColumn = idColumn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemoteAdress() {
		return remoteAdress;
	}

	public void setRemoteAdress(String remoteAdress) {
		this.remoteAdress = remoteAdress;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String[] getColumns() {
		return new String[] { "idColumn", "userName", "remoteAdress", "created", "description" };
	}
}
