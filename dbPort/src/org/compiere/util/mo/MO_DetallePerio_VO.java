package org.compiere.util.mo;

public class MO_DetallePerio_VO {
	
	private String lingual = "Y";
	
	private String displayP = "none";
	private String displayC = "none";
	private String displayB = "none";
	private String displayS = "none";
	
	private Integer dienteId = null;
	private Integer idDB = null;
	private String valor = null;
	private String nombre = null;
	private String desc = null;
	
	private String p = "false";
	private String c = "false";
	private String b = "false";
	private String s = "false";
	
	public MO_DetallePerio_VO(){}
	
	public void reset(){
		
		this.displayP = "none";
		this.displayC = "none";
		this.displayB = "none";
		this.displayS = "none";
		this.p = "false";
		this.c = "false";
		this.b = "false";
		this.s = "false";
		
		
	}
	
	public String getDisplayP() {
		return displayP;
	}
	public void setDisplayP(String displayP) {
		this.displayP = displayP;
	}
	public String getDisplayC() {
		return displayC;
	}
	public void setDisplayC(String displayC) {
		this.displayC = displayC;
	}
	public String getDisplayB() {
		return displayB;
	}
	public void setDisplayB(String displayB) {
		this.displayB = displayB;
	}
	public String getDisplayS() {
		return displayS;
	}
	public void setDisplayS(String displayS) {
		this.displayS = displayS;
	}
	public Integer getDienteId() {
		return dienteId;
	}
	public void setDienteId(Integer dienteId) {
		this.dienteId = dienteId;
	}
	public Integer getIdDB() {
		return idDB;
	}
	public void setIdDB(Integer idDB) {
		this.idDB = idDB;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}

	public String getLingual() {
		return lingual;
	}

	public void setLingual(String lingual) {
		this.lingual = lingual;
	}
	

}
