package org.compiere.util.mo;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class MO_PiezaDental_VO {
	
	private String requestContext = "eCareSoftWeb";//TODO: agregar como parametro?
	
	//tabla exme_mo_piezadental
	private Integer dienteId = null;
	private String tipo = null;
	private String deAdulto = null;
	private String ruta = null;
	private String mapaA = null;
	private String mapaI = null;
	private String nombre = null;
	private String descripcion = null;
	private String value = null;
	//tabla exme_mo_piezadental
	
	//tabla exme_mo_expediente
	private Integer expediente = null;
	private Integer pacId = null;
	//private Integer dienteId = null;
	private String sangrado = null;
	private String ausente = "N";
	private Boolean isAdulto = null;
	private String placa = null;
	private String calculo = null;
	private String supuracion = null;
	private String adulto = null;
	private String lingual = null;
	//Es odontograma
	private Boolean isLingual = null;
	private Boolean isSangrado = null;
	private Boolean isAusente = null;
	private Boolean isPlaca = null;
	private Boolean isCalculo = null;
	private Boolean isSupuracion = null;
	
	private Date creado = null;
	
	
	private ArrayList<MO_ProblemaDental_VO> problemas = new ArrayList<MO_ProblemaDental_VO>();
	
	private Integer cara1 = new Integer(0);
	private Integer cara2 = new Integer(0);
	private Integer cara3 = new Integer(0);
	private Integer cara4 = new Integer(0);
	private Integer cara5 = new Integer(0);
	private Integer cara6 = new Integer(0);
	private Integer cara7 = new Integer(0);
	private Integer cara8 = new Integer(0);
	private Integer cara9 = new Integer(0);
	
	private String cara1P = "";	
	private String cara2P = "";
	private String cara3P = "";
	private String cara4P = "";
	private String cara5P = "";
	private String cara6P = "";
	private String cara7P = "";
	private String cara8P = "";
	private String cara9P = "";
	
	private String cara1C = "FFFFFF";
	private String cara2C = "FFFFFF";
	private String cara3C = "FFFFFF";
	private String cara4C = "FFFFFF";
	private String cara5C = "FFFFFF";
	private String cara6C = "FFFFFF";
	private String cara7C = "FFFFFF";
	private String cara8C = "FFFFFF";
	private String cara9C = "FFFFFF";
	private String cara1B = "FFFFFF";
	private String cara2B = "FFFFFF";
	private String cara3B = "FFFFFF";
	private String cara4B = "FFFFFF";
	private String cara5B = "FFFFFF";
	private String cara6B = "FFFFFF";
	private String cara7B = "FFFFFF";
	private String cara8B = "FFFFFF";
	private String cara9B = "FFFFFF";
	private Integer probImgAct = new Integer(0);
	private Integer problemaActual = new Integer(0);
	private String probImgActIM = "images/odontologia/trans.png";
	private String cara1D = "block";
	private String cara2D = "block";
	private String cara3D = "block";
	private String cara4D = "block";
	private String cara5D = "block";
	private String cara6D = "block";
	private String cara7D = "block";
	private String cara8D = "block";
	private String cara9D = "block";
	
	private String tablaDienteC3 = "000000";
	private Integer tablaDienteC3Act = new Integer(0);
	
	private String probImgActIMD = "none";
	
	public MO_PiezaDental_VO(){}
	
	public MO_PiezaDental_VO(String requestContext){
		this.requestContext = requestContext;
	}

	public MO_PiezaDental_VO(ArrayList<MO_ProblemaDental_VO> respuestas) {
		super();
		this.problemas = respuestas;
	}
	
	public String get(String param){ 
		if(requestContext != null && (param != null && param.contains(requestContext)))
			return param.replace(requestContext, "");
		else
			return param;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDeAdulto() {
		return deAdulto;
	}
	public void setDeAdulto(String deAdulto) {
		this.deAdulto = deAdulto;
	}
	
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	


	public Integer getDienteId() {
		return dienteId;
	}


	public void setDienteId(Integer dienteId) {
		this.dienteId = dienteId;
	}


	public Integer getPacId() {
		return pacId;
	}


	public void setPacId(Integer pacId) {
		this.pacId = pacId;
	}


	public Integer getExpediente() {
		return expediente;
	}


	public void setExpediente(Integer expediente) {
		this.expediente = expediente;
	}


	public String getSangrado() {
		return sangrado;
	}


	public void setSangrado(String sangrado) {
		this.sangrado = sangrado;
	}


	public String getAusente() {
		return ausente;
	}


	public void setAusente(String ausente) {
		this.ausente = ausente;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public Date getCreado() {
		return creado;
	}


	public void setCreado(Date creado) {
		this.creado = creado;
	}


	public Boolean getIsAdulto() {
		return isAdulto;
	}


	public void setIsAdulto(Boolean isAdulto) {
		this.isAdulto = isAdulto;
	}


	public Boolean getIsSangrado() {
		return isSangrado;
	}


	public void setIsSangrado(Boolean isSangrado) {
		this.isSangrado = isSangrado;
	}


	public Boolean getIsAusente() {
		return isAusente;
	}


	public void setIsAusente(Boolean isAusente) {
		this.isAusente = isAusente;
	}


	public Boolean getIsPlaca() {
		return isPlaca;
	}


	public void setIsPlaca(Boolean isPlaca) {
		this.isPlaca = isPlaca;
	}


	public Boolean getIsCalculo() {
		return isCalculo;
	}


	public void setIsCalculo(Boolean isCalculo) {
		this.isCalculo = isCalculo;
	}


	public Boolean getIsSupuracion() {
		return isSupuracion;
	}


	public void setIsSupuracion(Boolean isSupuracion) {
		this.isSupuracion = isSupuracion;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getCalculo() {
		return calculo;
	}


	public void setCalculo(String calculo) {
		this.calculo = calculo;
	}


	public String getSupuracion() {
		return supuracion;
	}


	public void setSupuracion(String supuracion) {
		this.supuracion = supuracion;
	}


	public String getAdulto() {
		return adulto;
	}


	public void setAdulto(String adulto) {
		this.adulto = adulto;
	}


	public String getLingual() {
		return lingual;
	}


	public void setLingual(String lingual) {
		this.lingual = lingual;
	}


	public Boolean getIsLingual() {
		return isLingual;
	}


	public void setIsLingual(Boolean isLingual) {
		this.isLingual = isLingual;
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


	


	public Integer getCara1() {
		return cara1;
	}


	public void setCara1(Integer cara1) {
		this.cara1 = cara1;
	}


	public Integer getCara2() {
		return cara2;
	}


	public void setCara2(Integer cara2) {
		this.cara2 = cara2;
	}


	public Integer getCara3() {
		return cara3;
	}


	public void setCara3(Integer cara3) {
		this.cara3 = cara3;
	}


	public Integer getCara4() {
		return cara4;
	}


	public void setCara4(Integer cara4) {
		this.cara4 = cara4;
	}


	public Integer getCara5() {
		return cara5;
	}


	public void setCara5(Integer cara5) {
		this.cara5 = cara5;
	}


	public ArrayList<MO_ProblemaDental_VO> getProblemas() {
		return problemas;
	}


	public void setProblemas(ArrayList<MO_ProblemaDental_VO> problemas) {
		this.problemas = problemas;
	}


	public Integer getProbImgAct() {
		return probImgAct;
	}


	public void setProbImgAct(Integer probImgAct) {
		this.probImgAct = probImgAct;
	}


	public String getCara1C() {
		return get(cara1C);
	}


	public void setCara1C(String cara1C) {
		this.cara1C = get(cara1C);
	}


	public String getCara2C() {
		return get(cara2C);
	}


	public void setCara2C(String cara2C) {
		this.cara2C = get(cara2C);
	}


	public String getCara3C() {
		return get(cara3C);
	}


	public void setCara3C(String cara3C) {
		this.cara3C = get(cara3C);
	}


	public String getCara4C() {
		return get(cara4C);
	}


	public void setCara4C(String cara4C) {
		this.cara4C = get(cara4C);
	}


	public String getCara5C() {
		return get(cara5C);
	}


	public void setCara5C(String cara5C) {
		this.cara5C = get(cara5C);
	}


	public String getCara1B() {
		return get(cara1B);
	}


	public void setCara1B(String cara1B) {
		this.cara1B = get(cara1B);
	}


	public String getCara2B(){ 
		return get(cara2B);
	}


	public void setCara2B(String cara2B) {
		this.cara2B = get(cara2B);
	}


	public String getCara3B() {
		return get(cara3B);
	}


	public void setCara3B(String cara3B) {
		this.cara3B = get(cara3B);
	}


	public String getCara4B() {
		return get(cara4B);
	}


	public void setCara4B(String cara4B) {
		this.cara4B = get(cara4B);
	}


	public String getCara5B() {
		return get(cara5B);
	}


	public void setCara5B(String cara5B) {
		this.cara5B = get(cara5B);
	}


	public String getProbImgActIM() {
		return probImgActIM;
	}


	public void setProbImgActIM(String probImgActIM) {
		if (!StringUtils.isEmpty(probImgActIM))
			this.probImgActIM = probImgActIM;
	}


	public String getCara1D() {
		return get(cara1D);
	}


	public void setCara1D(String cara1D) {
		this.cara1D = get(cara1D);
	}


	public String getCara2D() {
		return get(cara2D);
	}


	public void setCara2D(String cara2D) {
		this.cara2D = get(cara2D);
	}


	public String getCara3D() {
		return get(cara3D);
	}


	public void setCara3D(String cara3D) {
		this.cara3D = get(cara3D);
	}


	public String getCara4D() {
		return get(cara4D);
	}


	public void setCara4D(String cara4D) {
		this.cara4D = get(cara4D);
	}


	public String getCara5D() {
		return get(cara5D);
	}


	public void setCara5D(String cara5D) {
		this.cara5D = get(cara5D);
	}


	public String getProbImgActIMD() {
		return probImgActIMD;
	}


	public void setProbImgActIMD(String probImgActIMD) {
		this.probImgActIMD = probImgActIMD;
	}


	public String getTablaDienteC3() {
		return tablaDienteC3;
	}


	public void setTablaDienteC3(String tablaDienteC3) {
		this.tablaDienteC3 = tablaDienteC3;
	}


	public Integer getTablaDienteC3Act() {
		return tablaDienteC3Act;
	}


	public void setTablaDienteC3Act(Integer tablaDienteC3Act) {
		this.tablaDienteC3Act = tablaDienteC3Act;
	}


	public Integer getCara6() {
		return cara6;
	}


	public void setCara6(Integer cara6) {
		this.cara6 = cara6;
	}


	public Integer getCara7() {
		return cara7;
	}


	public void setCara7(Integer cara7) {
		this.cara7 = cara7;
	}


	public Integer getCara8() {
		return cara8;
	}


	public void setCara8(Integer cara8) {
		this.cara8 = cara8;
	}


	public Integer getCara9() {
		return cara9;
	}


	public void setCara9(Integer cara9) {
		this.cara9 = cara9;
	}


	public String getCara6C() {
		return cara6C;
	}


	public void setCara6C(String cara6C) {
		this.cara6C = cara6C;
	}


	public String getCara7C() {
		return cara7C;
	}


	public void setCara7C(String cara7C) {
		this.cara7C = cara7C;
	}


	public String getCara8C() {
		return cara8C;
	}


	public void setCara8C(String cara8C) {
		this.cara8C = cara8C;
	}


	public String getCara9C() {
		return cara9C;
	}


	public void setCara9C(String cara9C) {
		this.cara9C = cara9C;
	}


	public String getCara6B() {
		return cara6B;
	}


	public void setCara6B(String cara6B) {
		this.cara6B = cara6B;
	}


	public String getCara7B() {
		return cara7B;
	}


	public void setCara7B(String cara7B) {
		this.cara7B = cara7B;
	}


	public String getCara8B() {
		return cara8B;
	}


	public void setCara8B(String cara8B) {
		this.cara8B = cara8B;
	}


	public String getCara9B() {
		return cara9B;
	}


	public void setCara9B(String cara9B) {
		this.cara9B = cara9B;
	}


	public String getCara6D() {
		return cara6D;
	}


	public void setCara6D(String cara6D) {
		this.cara6D = cara6D;
	}


	public String getCara7D() {
		return cara7D;
	}


	public void setCara7D(String cara7D) {
		this.cara7D = cara7D;
	}


	public String getCara8D() {
		return cara8D;
	}


	public void setCara8D(String cara8D) {
		this.cara8D = cara8D;
	}


	public String getCara9D() {
		return cara9D;
	}


	public void setCara9D(String cara9D) {
		this.cara9D = cara9D;
	}


	public String getCara1P() {
		return cara1P;
	}


	public void setCara1P(String cara1P) {
		this.cara1P = cara1P;
	}


	public String getCara2P() {
		return cara2P;
	}


	public void setCara2P(String cara2P) {
		this.cara2P = cara2P;
	}


	public String getCara3P() {
		return cara3P;
	}


	public void setCara3P(String cara3P) {
		this.cara3P = cara3P;
	}


	public String getCara4P() {
		return cara4P;
	}


	public void setCara4P(String cara4P) {
		this.cara4P = cara4P;
	}


	public String getCara5P() {
		return cara5P;
	}


	public void setCara5P(String cara5P) {
		this.cara5P = cara5P;
	}


	public String getCara6P() {
		return cara6P;
	}


	public void setCara6P(String cara6P) {
		this.cara6P = cara6P;
	}


	public String getCara7P() {
		return cara7P;
	}


	public void setCara7P(String cara7P) {
		this.cara7P = cara7P;
	}


	public String getCara8P() {
		return cara8P;
	}


	public void setCara8P(String cara8P) {
		this.cara8P = cara8P;
	}


	public String getCara9P() {
		return cara9P;
	}


	public void setCara9P(String cara9P) {
		this.cara9P = cara9P;
	}


	public Integer getProblemaActual() {
		return problemaActual;
	}


	public void setProblemaActual(Integer problemaActual) {
		this.problemaActual = problemaActual;
	}

	
}
