package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class QuirEquiposView extends MEqQuirofano {

	public QuirEquiposView(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public QuirEquiposView(Properties ctx, int EXME_EqQuirofano_ID,
			String trxName) {
		super(ctx, EXME_EqQuirofano_ID, trxName);
	}
	
	private boolean selected = false;
	
	private String equipoName = null;
	
	private long equipoID = 0L;

	public String getEquipoName() {
		return equipoName;
	}

	public void setEquipoName(String equipoName) {
		this.equipoName = equipoName;
	}

	public long getEquipoID() {
		return equipoID;
	}

	public void setEquipoID(long equipoID) {
		this.equipoID = equipoID;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	private String equipoValue = "";

	public String getEquipoValue() {
		return equipoValue;
	}

	public void setEquipoValue(String equipoValue) {
		this.equipoValue = equipoValue;
	}

}
