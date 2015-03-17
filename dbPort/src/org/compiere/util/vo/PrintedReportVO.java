package org.compiere.util.vo;

import java.sql.Timestamp;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.GridItem;

/**
 * 
 * @author Omar de la Rosa
 *
 */
public class PrintedReportVO implements GridItem {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Timestamp fecha;
	private String name;
	private String userName;
	private String param;
	private IDColumn idColumn;
	private String sql;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setIdColumn(IDColumn idColumn) {
		this.idColumn = idColumn;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String[] getColumns() {
		return new String[] { "fecha", "userName", "name", "sql", "param" };
	}

	@Override
	public IDColumn getIdColumn() {
		return idColumn;
	}

}
