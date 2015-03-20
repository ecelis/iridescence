package org.compiere.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.compiere.util.Env;

/**
 * Clase para llenar los registros del reporte de ordenes de compra
 * @author vperez
 **/
public class ProductPrice {
	private String value = null;
	private String producto = null;
	private String proveedr = null;
	private String categoria = null;
	private Integer linea = 0;
	private BigDecimal costo = null;
	private BigDecimal cantidad = null;
	private BigDecimal cantidadPorEmp = null;
	private BigDecimal costoPorEmp = null;
	private BigDecimal ivaEmp = null;
	private BigDecimal costoIVAEmp = null;
	private BigDecimal pVenta = null;
	private BigDecimal vntIVA = null;
	private BigDecimal precVntIVA = null;
	private String uomEmpaque = null;
	private String uomMinima = null;
	private Integer ordenCompra = 0;
	private String fechaOC = null;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getProveedr() {
		return proveedr;
	}
	public void setProveedr(String proveedr) {
		this.proveedr = proveedr;
	}
	public Integer getLinea() {
		return linea;
	}
	public void setLinea(Integer linea) {
		this.linea = linea;
	}
	public BigDecimal getCosto() {
		return costo == null ? Env.ZERO : costo.setScale(2, RoundingMode.HALF_UP);
	}
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
	public BigDecimal getCantidad() {
		return cantidad == null ? Env.ZERO : cantidad.setScale(2, RoundingMode.HALF_UP);
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getCantidadPorEmp() {
		return cantidadPorEmp == null ? Env.ZERO : cantidadPorEmp.setScale(2, RoundingMode.HALF_UP);
	}
	public void setCantidadPorEmp(BigDecimal cantidadPorEmp) {
		this.cantidadPorEmp = cantidadPorEmp;
	}
	public BigDecimal getCostoPorEmp() {
		return costoPorEmp == null ? Env.ZERO : costoPorEmp.setScale(2, RoundingMode.HALF_UP);
	}
	public void setCostoPorEmp(BigDecimal costoPorEmp) {
		this.costoPorEmp = costoPorEmp;
	}
	public String getUomEmpaque() {
		return uomEmpaque;
	}
	public void setUomEmpaque(String uomEmpaque) {
		this.uomEmpaque = uomEmpaque;
	}
	public String getUomMinima() {
		return uomMinima;
	}
	public void setUomMinima(String uomMinima) {
		this.uomMinima = uomMinima;
	}
	public Integer getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(Integer ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	public String getFechaOC() {
		return fechaOC;
	}
	public void setFechaOC(String fechaOC) {
		this.fechaOC = fechaOC;
	}
	public BigDecimal getIvaEmp() {
		return ivaEmp == null ? Env.ZERO : ivaEmp.setScale(2, RoundingMode.HALF_UP);
	}
	public void setIvaEmp(BigDecimal ivaEmp) {
		this.ivaEmp = ivaEmp;
	}
	public BigDecimal getCostoIVAEmp() {
		return costoIVAEmp == null ? Env.ZERO : costoIVAEmp.setScale(2, RoundingMode.HALF_UP);
	}
	public void setCostoIVAEmp(BigDecimal costoIVAEmp) {
		this.costoIVAEmp = costoIVAEmp;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public BigDecimal getpVenta() {
		return pVenta == null ? Env.ZERO : pVenta.setScale(2, RoundingMode.HALF_UP);
	}
	public void setpVenta(BigDecimal pVenta) {
		this.pVenta = pVenta;
	}
	public BigDecimal getVntIVA() {
		return vntIVA == null ? Env.ZERO : vntIVA.setScale(2, RoundingMode.HALF_UP);
	}
	public void setVntIVA(BigDecimal vntIVA) {
		this.vntIVA = vntIVA;
	}
	public BigDecimal getPrecVntIVA() {
		return precVntIVA == null ? Env.ZERO : precVntIVA.setScale(2, RoundingMode.HALF_UP);
	}
	public void setPrecVntIVA(BigDecimal precVntIVA) {
		this.precVntIVA = precVntIVA;
	}
}