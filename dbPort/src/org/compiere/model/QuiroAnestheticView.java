package org.compiere.model;

import java.sql.Timestamp;

import org.compiere.util.Env;

public class QuiroAnestheticView {

	int							intervencion_ID;
	int							EXME_QuiroAnesthetic_ID;
	Timestamp					fechaIni;
	Timestamp					fechaFin;
	String						name;
	String						interName;// Lama Card #618
	boolean						deleteDB;

	public QuiroAnestheticView() {}

	public QuiroAnestheticView(MEXMEQuiroAnesthetic anest) {
		this.intervencion_ID = anest.getEXME_Intervencion_ID();
		this.name = anest.getName();
		this.fechaIni = anest.getFechaIni();
		this.fechaFin = anest.getFechaFin();
		this.EXME_QuiroAnesthetic_ID = anest.getEXME_QuiroAnesthetic_ID();
	}

	public QuiroAnestheticView(MEXMEProductInterv mProdInter) {
		if (mProdInter != null) {
			this.intervencion_ID = mProdInter.getEXME_Intervencion_ID();
			this.name = mProdInter.getProductName();
			this.interName = mProdInter.getInterValue() +" "+ mProdInter.getIntervencion();
		}
	}

	public MEXMEQuiroAnesthetic newAnesthetic(String trxName) {
		MEXMEQuiroAnesthetic anesthetic = new MEXMEQuiroAnesthetic(Env.getCtx(), 0, trxName);
		anesthetic.setEXME_Intervencion_ID(intervencion_ID);
		anesthetic.setName(name);
		anesthetic.setFechaIni(fechaIni);
		anesthetic.setFechaFin(fechaFin);

		return anesthetic;
	}

	public boolean isDeleteDB() {
		return deleteDB;
	}
	public void setDeleteDB(boolean deleteDB) {
		this.deleteDB = deleteDB;
	}
	public int getIntervencion_ID() {
		return intervencion_ID;
	}
	public void setIntervencion_ID(int intervencion_ID) {
		this.intervencion_ID = intervencion_ID;
	}
	public Timestamp getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Timestamp fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Timestamp getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEXME_QuiroAnesthetic_ID() {
		return EXME_QuiroAnesthetic_ID;
	}
	public void setEXME_QuiroAnesthetic_ID(int eXME_QuiroAnesthetic_ID) {
		EXME_QuiroAnesthetic_ID = eXME_QuiroAnesthetic_ID;
	}
	
	public String getInterName() {
		if(interName == null && intervencion_ID > 0){
			final MIntervencion inter = new MIntervencion(Env.getCtx(), intervencion_ID, null);
			interName = inter.getValue() +" "+ inter.getName();
		}
		return interName;
	}

}
