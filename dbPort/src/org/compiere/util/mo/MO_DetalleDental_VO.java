package org.compiere.util.mo;

public class MO_DetalleDental_VO {
	
	private String id = null;
	private Integer idDB = null;
	private String lingual = "Y";
	private String idimg = "/compiere/images/odontologia/";
	private String eimg = "/compiere/images/odontologia/icono-nino.png";
	private String aimg = "/compiere/images/odontologia/";
	private String pimg = "/compiere/images/odontologia/";
	
	private String e = "false";
	private String a = "false";
	private String p = "false";
	private String visibleD = "none";
	private String visibleE = "none";
	private String visibleA = "none";
	private String visibleP = "none";
	
	private String ruta = "D1.png";
	private String mapaA = "";
	private String mapaI = "";
	
	public MO_DetalleDental_VO(){}
	
	public void reset(){
		  this.idimg = "/compiere/images/odontologia/";
		  this.eimg = "/compiere/images/odontologia/icono-nino.png";
		  this.aimg = "/compiere/images/odontologia/";
		  this.pimg = "/compiere/images/odontologia/";
		  this.e = "false";
		  this.a = "false";
		  this.p = "false";
		  this.visibleD = "none";
		  this.visibleE = "none";
		  this.visibleA = "none";
		  this.visibleP = "none";
		  
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEimg() {
		
		if(this.e.equals("true")){
			this.eimg = "/compiere/images/odontologia/icono-adulto.png";
		}else{
			
			this.eimg = "/compiere/images/odontologia/icono-nino.png";
		}
		return eimg;
	}

	public void setEimg(String eimg) {
		this.eimg = eimg;
	}

	public String getAimg() {
		String aux = this.ruta;
		return aimg+aux.replaceFirst(".png", "-A.png");
	}

	public void setAimg(String aimg) {
		this.aimg = aimg;
	}

	public String getPimg() {
		String aux = this.ruta;
		return pimg+aux.replaceFirst(".png", "-P.png");
	}

	public void setPimg(String pimg) {
		this.pimg = pimg;
	}

	public String getIdimg() {
		return idimg+this.ruta;
	}

	public void setIdimg(String idimg) {
		this.idimg = idimg;
	}

	public Integer getIdDB() {
		return idDB;
	}

	public void setIdDB(Integer idDB) {
		this.idDB = idDB;
	}

	public String getVisibleD() {
		return visibleD;
	}

	public void setVisibleD(String visibleD) {
		this.visibleD = visibleD;
	}

	public String getVisibleE() {
		return visibleE;
	}

	public void setVisibleE(String visibleE) {
		this.visibleE = visibleE;
	}

	public String getVisibleA() {
		return visibleA;
	}

	public void setVisibleA(String visibleA) {
		this.visibleA = visibleA;
	}

	public String getVisibleP() {
		return visibleP;
	}

	public void setVisibleP(String visibleP) {
		this.visibleP = visibleP;
	}

	public String getLingual() {
		return lingual;
	}

	public void setLingual(String lingual) {
		this.lingual = lingual;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getMapaA() {
		return mapaA;
	}

	public void setMapaA(String mapaA) {
		this.mapaA = mapaA;
	}

	public String getMapaI() {
		return mapaI;
	}

	public void setMapaI(String mapaI) {
		this.mapaI = mapaI;
	}

	
	
	
}
