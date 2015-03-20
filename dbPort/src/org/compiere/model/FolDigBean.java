package org.compiere.model;

import java.util.ArrayList;
import java.util.List;

public class FolDigBean {
	private String client = null;
	private int totTimbres = 0;
	private boolean isactive = false;
	private int folDig_ID = 0;
	private List<MEXMEFolDigDet> lstFolDet = new ArrayList<MEXMEFolDigDet>();
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public int getTotTimbres() {
		return totTimbres;
	}
	public void setTotTimbres(int totTimbres) {
		this.totTimbres = totTimbres;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public int getFolDig_ID() {
		return folDig_ID;
	}
	public void setFolDig_ID(int folDig_ID) {
		this.folDig_ID = folDig_ID;
	}
	public List<MEXMEFolDigDet> getLstFolDet() {
		return lstFolDet;
	}
	public void setLstFolDet(List<MEXMEFolDigDet> lstFolDet) {
		this.lstFolDet = lstFolDet;
	}
	
}
