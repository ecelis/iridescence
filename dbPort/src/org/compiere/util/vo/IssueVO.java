package org.compiere.util.vo;

import java.sql.Timestamp;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.GridItem;

public class IssueVO implements GridItem{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private IDColumn idColumn;
	private Timestamp created;
	private String userN;
	private String localHost;
	private String issueSummary;
	private String stackTrace;
	
	public void setIdColumn(IDColumn idColumn) {
		this.idColumn = idColumn;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getUserN() {
		return userN;
	}

	public void setUserN(String userN) {
		this.userN = userN;
	}

	public String getLocalHost() {
		return localHost;
	}

	public void setLocalHost(String localHost) {
		this.localHost = localHost;
	}

	public String getIssueSummary() {
		return issueSummary;
	}

	public void setIssueSummary(String issueSummary) {
		this.issueSummary = issueSummary;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	@Override
	public String[] getColumns() {
		return new String[] {"created", "userN", "localHost", "issueSummary", "stackTrace"};
	}

	@Override
	public IDColumn getIdColumn() {
		return idColumn;
	}

}
