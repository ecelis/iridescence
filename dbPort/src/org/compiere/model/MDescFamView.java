package org.compiere.model;

public class MDescFamView {

	
	private MCtaPacDet ttCtaPacDet = null;
	
	private MCtaPacFam ctaPacFam = null;

	public MCtaPacFam getCtaPacFam() {
		return ctaPacFam;
	}

	public void setCtaPacFam(MCtaPacFam ctaPacFam) {
		this.ctaPacFam = ctaPacFam;
	}

	public MCtaPacDet getTtCtaPacDet() {
		return ttCtaPacDet;
	}

	public void setTtCtaPacDet(MCtaPacDet ttCtaPacDet) {
		this.ttCtaPacDet = ttCtaPacDet;
	}
}
