package org.compiere.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StorageVO {
	private int M_Product_ID;	
	private String value;
	private String name;
	private String descripcion;
	private String unidadMedida;
	private int C_Uom_ID;
	private int M_AttributeSetInstance_ID;
	private String lot;
	private String guaranteeDate;
	private BigDecimal qtyOnHand;
	private BigDecimal qtyReserved;
	private BigDecimal qtyRequested;
	private BigDecimal qtyOriginal;
	private boolean seleccionado;
	private int M_Locator_ID;
	private int M_Warehouse_ID;
	private String almacenInfo;
	private int fila;
	private int renglon;
	
	public BigDecimal getQtyOriginal() {
		return qtyOriginal;
	}
	public void setQtyOriginal(BigDecimal qtyOriginal) {
		this.qtyOriginal = qtyOriginal;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getRenglon() {
		return renglon;
	}
	public void setRenglon(int renglon) {
		this.renglon = renglon;
	}
	public int getM_Warehouse_ID() {
		return M_Warehouse_ID;
	}
	public void setM_Warehouse_ID(int warehouse_ID) {
		M_Warehouse_ID = warehouse_ID;
	}
	public String getAlmacenInfo() {
		return almacenInfo;
	}
	public void setAlmacenInfo(String almacenInfo) {
		this.almacenInfo = almacenInfo;
	}
	public int getM_Locator_ID() {
		return M_Locator_ID;
	}
	public void setM_Locator_ID(int locator_ID) {
		M_Locator_ID = locator_ID;
	}
	public boolean getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public int getM_Product_ID() {
		return M_Product_ID;
	}
	public void setM_Product_ID(int product_ID) {
		M_Product_ID = product_ID;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public int getC_Uom_ID() {
		return C_Uom_ID;
	}
	public void setC_Uom_ID(int uom_ID) {
		C_Uom_ID = uom_ID;
	}
	public int getM_AttributeSetInstance_ID() {
		return M_AttributeSetInstance_ID;
	}
	public void setM_AttributeSetInstance_ID(int attributeSetInstance_ID) {
		M_AttributeSetInstance_ID = attributeSetInstance_ID;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getGuaranteeDate() {
		return guaranteeDate;
	}
	public void setGuaranteeDate(String guaranteeDate) {
		this.guaranteeDate = guaranteeDate;
	}
	public BigDecimal getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(BigDecimal qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}
	public BigDecimal getQtyReserved() {
		return qtyReserved;
	}
	public void setQtyReserved(BigDecimal qtyReserved) {
		this.qtyReserved = qtyReserved;
	}
	public BigDecimal getQtyAvailable() {
		return qtyOnHand.subtract(qtyReserved);
	}
	public BigDecimal getQtyRequested() {
		return qtyRequested;
	}
	public void setQtyRequested(BigDecimal qtyRequested) {
		this.qtyRequested = qtyRequested;
	}
	
	public static List <CustomError> validaValores(List<StorageVO> listado) throws Exception {
        
		List<CustomError> errores = new ArrayList<CustomError>();
		
        for(int i = 0; i < listado.size(); i++){
        	StorageVO stock = listado.get(i);
        	if(stock.getSeleccionado()){
		        if(stock.getQtyOnHand().compareTo(stock.getQtyRequested()) < 0){
		        	Object [] param = {stock.getName()};
		        	CustomError error = new CustomError("error.encCtaPac.noExistenProd", param);
		        	errores.add(error);
		        }
        	}
        }
        
        return errores;
	}
}
