package org.compiere.util.mo;

public class MO_Prescripcion_VO {
	
	private boolean existDifwk = true;
	
	private int id=0;
	private int prescripcionID = 0;
	private int mproductID = 0;
	private int cantidad = 0;
	private String indicaciones = null;
	private String otrasIndicaciones = null;
	private String fechaIndicacion = null;
	private String productName = null;
	private String mproductValue = null;
	private String mproductPMID = null;
	private String documentNO = null;
	
	private String udmTx=null;
	private int udmId = 0;
	private int dias=0;
	private int cantidadTomar=0;
	private int vecesDia=0;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getPrescripcionID() {
		return prescripcionID;
	}

	public void setPrescripcionID(int prescripcionID) {
		this.prescripcionID = prescripcionID;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getIndicaciones() {
		return indicaciones;
	}

	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}

	public String getOtrasIndicaciones() {
		return otrasIndicaciones;
	}

	public void setOtrasIndicaciones(String otrasIndicaciones) {
		this.otrasIndicaciones = otrasIndicaciones;
	}

	public String getFechaIndicacion() {
		return fechaIndicacion;
	}

	public void setFechaIndicacion(String fechaIndicacion) {
		this.fechaIndicacion = fechaIndicacion;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
		
	public String getDocumentNO() {
		return documentNO;
	}

	public void setDocumentNO(String documentNO) {
		this.documentNO = documentNO;
	}

	public MO_Prescripcion_VO copiar(){
		MO_Prescripcion_VO nuevo = new MO_Prescripcion_VO();
		
		nuevo.setId(id);
		nuevo.setMproductID(mproductID);
		nuevo.setProductName(productName);
		nuevo.setMproductValue(mproductValue);
		nuevo.setMproductPMID(mproductPMID);
		nuevo.setFechaIndicacion(fechaIndicacion);
		nuevo.setCantidad(cantidad);
		nuevo.setIndicaciones(indicaciones);
		nuevo.setOtrasIndicaciones(otrasIndicaciones);
		nuevo.setDocumentNO(documentNO);
		
		nuevo.setUdmId(udmId);
		nuevo.setUdmTx(udmTx);
		nuevo.setCantidadTomar(cantidadTomar);
		nuevo.setDias(dias);
		nuevo.setVecesDia(vecesDia);
		
		
		
		return nuevo;
	}

	public String getUdmTx() {
		return udmTx;
	}

	public void setUdmTx(String udmTx) {
		this.udmTx = udmTx;
	}

	public int getUdmId() {
		return udmId;
	}

	public void setUdmId(int udmId) {
		this.udmId = udmId;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public int getCantidadTomar() {
		return cantidadTomar;
	}

	public void setCantidadTomar(int cantidadTomar) {
		this.cantidadTomar = cantidadTomar;
	}

	public int getVecesDia() {
		return vecesDia;
	}

	public void setVecesDia(int vecesDia) {
		this.vecesDia = vecesDia;
	}

	public boolean isExistDifwk() {
		return existDifwk;
	}

	public void setExistDifwk(boolean existDifwk) {
		this.existDifwk = existDifwk;
	}

	public int getMproductID() {
		return mproductID;
	}

	public void setMproductID(int mproductID) {
		this.mproductID = mproductID;
	}

	public String getMproductValue() {
		return mproductValue;
	}

	public void setMproductValue(String mproductValue) {
		this.mproductValue = mproductValue;
	}

	public String getMproductPMID() {
		return mproductPMID;
	}

	public void setMproductPMID(String mproductPMID) {
		this.mproductPMID = mproductPMID;
	}
	
	
	


	

	

	
	
	
	
	
	
	
	

}
