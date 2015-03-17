package org.compiere.model;

import java.util.ArrayList;
import java.util.List;

import org.compiere.util.Env;
import org.compiere.util.Utilerias;

public class ModelKardex {
	private String prodValue = null;
	private String product = null;
	private String uomName = null;
	private String date;
	private String typeMov = null;
	private String documentNo = null;
	private String warehouse = null;
	private String locator = null;
	private String cantIni = null;
	private String cantTrx = null;
	private String existence = null;
	private String priceUnit = null;;
	private String priceTrx = null;;
	private String priceExistence = null;;
	private String user = null;
	private String purchasePrice = null;
	
	private String movDate = null;
	private String qtyBook = null;
	

	public String getMovDate() {
		return movDate;
	}
	public void setMovDate(String movDate) {
		this.movDate = movDate;
	}
	public String getQtyBook() {
		return qtyBook;
	}
	public void setQtyBook(String qtyBook) {
		this.qtyBook = qtyBook;
	}
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getProdValue() {
		return prodValue;
	}
	public void setProdValue(String prodValue) {
		this.prodValue = prodValue;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTypeMov() {
		return typeMov;
	}
	public void setTypeMov(String typeMov) {
		this.typeMov = typeMov;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getLocator() {
		return locator;
	}
	public void setLocator(String locator) {
		this.locator = locator;
	}
	public String getCantIni() {
		return cantIni;
	}
	public void setCantIni(String cantIni) {
		this.cantIni = cantIni;
	}
	public String getCantTrx() {
		return cantTrx;
	}
	public void setCantTrx(String cantTrx) {
		this.cantTrx = cantTrx;
	}
	public String getExistence() {
		return existence;
	}
	public void setExistence(String existence) {
		this.existence = existence;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public String getPriceTrx() {
		return priceTrx;
	}
	public void setPriceTrx(String priceTrx) {
		this.priceTrx = priceTrx;
	}
	public String getPriceExistence() {
		return priceExistence;
	}
	public void setPriceExistence(String priceExistence) {
		this.priceExistence = priceExistence;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public static List<String> getColumns() {
		List<String> columns = new ArrayList<String>();

		columns.add("prodValue");
		columns.add("product");
		columns.add("uomName");
		columns.add("date");
		columns.add("typeMov");
		columns.add("documentNo");
		columns.add("warehouse");
		columns.add("locator");
		columns.add("cantIni");
		columns.add("cantTrx");
		columns.add("existence");
		columns.add("priceUnit");
		columns.add("purchasePrice");
		columns.add("priceTrx");
		columns.add("priceExistence");
		columns.add("user");
		columns.add("movDate");
		columns.add("qtyBook");

		return columns;
	}
	public static List<String> getTitles(){
		List<String> titles = new ArrayList<String>();

		titles.add(Utilerias.getMsg(Env.getCtx(), "msg.loteProdDet.codigo"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msg.loteProdDet.description"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "cDiarios.uom"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msg.odontologia.expediente.prescripcion.fecha"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "reportes.tipoMovimiento"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "factExtension.msg.documentNo"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "login.estServ"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msj.beeper"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msg.cant.ini"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msg.cant.trx"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msg.existencia"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msj.costoUnit"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msj.precioCompra"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msg.cost.trx"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msg.cost.exist"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msj.usuario"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "egresos.rm.fecha.movimiento"));
		titles.add(Utilerias.getMsg(Env.getCtx(), "msj.cantTotal"));
		return titles;
	}

}
