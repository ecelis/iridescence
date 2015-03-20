package org.compiere.process.interfases;

import java.math.BigDecimal;
import java.sql.Date;

public class TIInvoiceModel {
	
	private String interface_line_context = null;
	private String interface_line_attribute1 = null;
	private String interface_line_attribute2 = null;
	private int interface_line_attribute3 = 0; 
	private String interface_line_attribute4 = null;
	private String interface_line_attribute5 = null; 
	private String batch_source_name = null;
	private String line_type = null;
	private String description = null;
	private String currency_code = null;
	private String uom_code = null;
	private int quantity = 0; 
	private BigDecimal unit_selling_price = null; 
	private BigDecimal amount = null;
	private String cust_trx_type_name = null;
	private int id_unico_de_direccion = 0;
	private String numero_cliente = null;
	private String conversion_type = null;
	private Date conversion_date= null;
	private int conversion_rate = 0;
	private Date trx_date = null;
	private Date gl_date = null;
	private String trx_number = null;
	private int line_number = 0; 
	private int qtyordered = 0;
	private String reason_code = null;
	private String tax_code = null;
	private String primarysalesrep_number = null;
	private String mtl_system_items_seg1 = null;
	private String mtl_system_items_seg2 = null; 
	private String mtl_system_items_seg3 = null;
	private Date creation_date = null;
	private Date last_update_date = null;
	private String unidad_operativa	= null;
	private String header_attribute1 = null;
	private String header_attribute2 = null;
	private String header_attribute3 = null;
	private String header_attribute4 = null;
	private String header_attribute5 = null;
	private String header_attribute6 = null;
	private String header_attribute7 = null;
	private String header_attribute8 = null;
	private String header_attribute9 = null;
	private String header_attribute10 = null;
	private String header_attribute11 = null;
	private String header_attribute12 = null;
	private String header_attribute13 = null;
	private Date header_attribute14 = null;
	private String header_attribute15 = null;
	private String attribute1 = null;
	private String attribute2 = null;
	private String attribute3 = null;
	private String attribute4 = null;
	private String attribute5 = null;
	private Date attribute6 = null;
	private Date attribute7 = null;
	private String attribute8 = null;
	private String attribute9 = null;
	private String attribute10 = null;
	private String attribute11 = null;
	private String attribute12 = null;
	private String attribute13 = null;
	private String attribute14 = null;
	private String attribute15 = null;
	private String link_to_line_context = null;
	private String link_to_line_attribute1 = null;
	private String link_to_line_attribute2 = null;
	private int link_to_line_attribute3	= 0;
	private String link_to_line_attribute4 = null;
	private String link_to_line_attribute5 = null;
	private String reference_line_context = null;
	private String reference_line_attribute1 = null;
	private String reference_line_attribute2 = null;
	private int reference_line_attribute3 = 0;
	private String reference_line_attribute4 = null;
	private String reference_line_attribute5 = null;
	private int sales_order_line = 0;
	private String line_gdf_attribute1 = null;
	private String purchase_order = null;
	private int c_invoice_id = 0;
	private int ad_client_id = 0;
	private int ad_org_id = 0;
	
	public int getAd_client_id() {
		return ad_client_id;
	}
	public void setAd_client_id(int ad_client_id) {
		this.ad_client_id = ad_client_id;
	}
	public int getAd_org_id() {
		return ad_org_id;
	}
	public void setAd_org_id(int ad_org_id) {
		this.ad_org_id = ad_org_id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute10() {
		return attribute10;
	}
	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}
	public String getAttribute11() {
		return attribute11;
	}
	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}
	public String getAttribute12() {
		return attribute12;
	}
	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}
	public String getAttribute13() {
		return attribute13;
	}
	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}
	public String getAttribute14() {
		return attribute14;
	}
	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}
	public String getAttribute15() {
		return attribute15;
	}
	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	public String getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	public Date getAttribute6() {
		return attribute6;
	}
	public void setAttribute6(Date attribute6) {
		this.attribute6 = attribute6;
	}
	public Date getAttribute7() {
		return attribute7;
	}
	public void setAttribute7(Date attribute7) {
		this.attribute7 = attribute7;
	}
	public String getAttribute8() {
		return attribute8;
	}
	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}
	public String getAttribute9() {
		return attribute9;
	}
	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}
	public String getBatch_source_name() {
		return batch_source_name;
	}
	public void setBatch_source_name(String batch_source_name) {
		this.batch_source_name = batch_source_name;
	}
	public Date getConversion_date() {
		return conversion_date;
	}
	public void setConversion_date(Date conversion_date) {
		this.conversion_date = conversion_date;
	}
	public int getConversion_rate() {
		return conversion_rate;
	}
	public void setConversion_rate(int conversion_rate) {
		this.conversion_rate = conversion_rate;
	}
	public String getConversion_type() {
		return conversion_type;
	}
	public void setConversion_type(String conversion_type) {
		this.conversion_type = conversion_type;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getCust_trx_type_name() {
		return cust_trx_type_name;
	}
	public void setCust_trx_type_name(String cust_trx_type_name) {
		this.cust_trx_type_name = cust_trx_type_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getGl_date() {
		return gl_date;
	}
	public void setGl_date(Date gl_date) {
		this.gl_date = gl_date;
	}
	public String getHeader_attribute1() {
		return header_attribute1;
	}
	public void setHeader_attribute1(String header_attribute1) {
		this.header_attribute1 = header_attribute1;
	}
	public String getHeader_attribute10() {
		return header_attribute10;
	}
	public void setHeader_attribute10(String header_attribute10) {
		this.header_attribute10 = header_attribute10;
	}
	public String getHeader_attribute11() {
		return header_attribute11;
	}
	public void setHeader_attribute11(String header_attribute11) {
		this.header_attribute11 = header_attribute11;
	}
	public String getHeader_attribute12() {
		return header_attribute12;
	}
	public void setHeader_attribute12(String header_attribute12) {
		this.header_attribute12 = header_attribute12;
	}
	public String getHeader_attribute13() {
		return header_attribute13;
	}
	public void setHeader_attribute13(String header_attribute13) {
		this.header_attribute13 = header_attribute13;
	}
	public Date getHeader_attribute14() {
		return header_attribute14;
	}
	public void setHeader_attribute14(Date header_attribute14) {
		this.header_attribute14 = header_attribute14;
	}
	public String getHeader_attribute15() {
		return header_attribute15;
	}
	public void setHeader_attribute15(String header_attribute15) {
		this.header_attribute15 = header_attribute15;
	}
	public String getHeader_attribute2() {
		return header_attribute2;
	}
	public void setHeader_attribute2(String header_attribute2) {
		this.header_attribute2 = header_attribute2;
	}
	public String getHeader_attribute3() {
		return header_attribute3;
	}
	public void setHeader_attribute3(String header_attribute3) {
		this.header_attribute3 = header_attribute3;
	}
	public String getHeader_attribute4() {
		return header_attribute4;
	}
	public void setHeader_attribute4(String header_attribute4) {
		this.header_attribute4 = header_attribute4;
	}
	public String getHeader_attribute5() {
		return header_attribute5;
	}
	public void setHeader_attribute5(String header_attribute5) {
		this.header_attribute5 = header_attribute5;
	}
	public String getHeader_attribute6() {
		return header_attribute6;
	}
	public void setHeader_attribute6(String header_attribute6) {
		this.header_attribute6 = header_attribute6;
	}
	public String getHeader_attribute7() {
		return header_attribute7;
	}
	public void setHeader_attribute7(String header_attribute7) {
		this.header_attribute7 = header_attribute7;
	}
	public String getHeader_attribute8() {
		return header_attribute8;
	}
	public void setHeader_attribute8(String header_attribute8) {
		this.header_attribute8 = header_attribute8;
	}
	public String getHeader_attribute9() {
		return header_attribute9;
	}
	public void setHeader_attribute9(String header_attribute9) {
		this.header_attribute9 = header_attribute9;
	}
	public int getId_unico_de_direccion() {
		return id_unico_de_direccion;
	}
	public void setId_unico_de_direccion(int id_unico_de_direccion) {
		this.id_unico_de_direccion = id_unico_de_direccion;
	}
	public String getInterface_line_attribute1() {
		return interface_line_attribute1;
	}
	public void setInterface_line_attribute1(String interface_line_attribute1) {
		this.interface_line_attribute1 = interface_line_attribute1;
	}
	public String getInterface_line_attribute2() {
		return interface_line_attribute2;
	}
	public void setInterface_line_attribute2(String interface_line_attribute2) {
		this.interface_line_attribute2 = interface_line_attribute2;
	}
	public int getInterface_line_attribute3() {
		return interface_line_attribute3;
	}
	public void setInterface_line_attribute3(int interface_line_attribute3) {
		this.interface_line_attribute3 = interface_line_attribute3;
	}
	public String getInterface_line_attribute4() {
		return interface_line_attribute4;
	}
	public void setInterface_line_attribute4(String interface_line_attribute4) {
		this.interface_line_attribute4 = interface_line_attribute4;
	}
	public String getInterface_line_attribute5() {
		return interface_line_attribute5;
	}
	public void setInterface_line_attribute5(String interface_line_attribute5) {
		this.interface_line_attribute5 = interface_line_attribute5;
	}
	public String getInterface_line_context() {
		return interface_line_context;
	}
	public void setInterface_line_context(String interface_line_context) {
		this.interface_line_context = interface_line_context;
	}
	public Date getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getLine_gdf_attribute1() {
		return line_gdf_attribute1;
	}
	public void setLine_gdf_attribute1(String line_gdf_attribute1) {
		this.line_gdf_attribute1 = line_gdf_attribute1;
	}
	public int getLine_number() {
		return line_number;
	}
	public void setLine_number(int line_number) {
		this.line_number = line_number;
	}
	public String getLine_type() {
		return line_type;
	}
	public void setLine_type(String line_type) {
		this.line_type = line_type;
	}
	public String getLink_to_line_attribute1() {
		return link_to_line_attribute1;
	}
	public void setLink_to_line_attribute1(String link_to_line_attribute1) {
		this.link_to_line_attribute1 = link_to_line_attribute1;
	}
	public String getLink_to_line_attribute2() {
		return link_to_line_attribute2;
	}
	public void setLink_to_line_attribute2(String link_to_line_attribute2) {
		this.link_to_line_attribute2 = link_to_line_attribute2;
	}
	public int getLink_to_line_attribute3() {
		return link_to_line_attribute3;
	}
	public void setLink_to_line_attribute3(int link_to_line_attribute3) {
		this.link_to_line_attribute3 = link_to_line_attribute3;
	}
	public String getLink_to_line_attribute4() {
		return link_to_line_attribute4;
	}
	public void setLink_to_line_attribute4(String link_to_line_attribute4) {
		this.link_to_line_attribute4 = link_to_line_attribute4;
	}
	public String getLink_to_line_attribute5() {
		return link_to_line_attribute5;
	}
	public void setLink_to_line_attribute5(String link_to_line_attribute5) {
		this.link_to_line_attribute5 = link_to_line_attribute5;
	}
	public String getLink_to_line_context() {
		return link_to_line_context;
	}
	public void setLink_to_line_context(String link_to_line_context) {
		this.link_to_line_context = link_to_line_context;
	}
	public String getMtl_system_items_seg1() {
		return mtl_system_items_seg1;
	}
	public void setMtl_system_items_seg1(String mtl_system_items_seg1) {
		this.mtl_system_items_seg1 = mtl_system_items_seg1;
	}
	public String getMtl_system_items_seg2() {
		return mtl_system_items_seg2;
	}
	public void setMtl_system_items_seg2(String mtl_system_items_seg2) {
		this.mtl_system_items_seg2 = mtl_system_items_seg2;
	}
	public String getMtl_system_items_seg3() {
		return mtl_system_items_seg3;
	}
	public void setMtl_system_items_seg3(String mtl_system_items_seg3) {
		this.mtl_system_items_seg3 = mtl_system_items_seg3;
	}
	public String getNumero_cliente() {
		return numero_cliente;
	}
	public void setNumero_cliente(String numero_cliente) {
		this.numero_cliente = numero_cliente;
	}
	public String getPrimarysalesrep_number() {
		return primarysalesrep_number;
	}
	public void setPrimarysalesrep_number(String primarysalesrep_number) {
		this.primarysalesrep_number = primarysalesrep_number;
	}
	public String getPurchase_order() {
		return purchase_order;
	}
	public void setPurchase_order(String purchase_order) {
		this.purchase_order = purchase_order;
	}
	public int getQtyordered() {
		return qtyordered;
	}
	public void setQtyordered(int qtyordered) {
		this.qtyordered = qtyordered;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getReason_code() {
		return reason_code;
	}
	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}
	public String getReference_line_attribute1() {
		return reference_line_attribute1;
	}
	public void setReference_line_attribute1(String reference_line_attribute1) {
		this.reference_line_attribute1 = reference_line_attribute1;
	}
	public String getReference_line_attribute2() {
		return reference_line_attribute2;
	}
	public void setReference_line_attribute2(String reference_line_attribute2) {
		this.reference_line_attribute2 = reference_line_attribute2;
	}
	public int getReference_line_attribute3() {
		return reference_line_attribute3;
	}
	public void setReference_line_attribute3(int reference_line_attribute3) {
		this.reference_line_attribute3 = reference_line_attribute3;
	}
	public String getReference_line_attribute4() {
		return reference_line_attribute4;
	}
	public void setReference_line_attribute4(String reference_line_attribute4) {
		this.reference_line_attribute4 = reference_line_attribute4;
	}
	public String getReference_line_attribute5() {
		return reference_line_attribute5;
	}
	public void setReference_line_attribute5(String reference_line_attribute5) {
		this.reference_line_attribute5 = reference_line_attribute5;
	}
	public String getReference_line_context() {
		return reference_line_context;
	}
	public void setReference_line_context(String reference_line_context) {
		this.reference_line_context = reference_line_context;
	}
	public int getSales_order_line() {
		return sales_order_line;
	}
	public void setSales_order_line(int sales_order_line) {
		this.sales_order_line = sales_order_line;
	}
	public String getTax_code() {
		return tax_code;
	}
	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}
	public Date getTrx_date() {
		return trx_date;
	}
	public void setTrx_date(Date trx_date) {
		this.trx_date = trx_date;
	}
	public String getTrx_number() {
		return trx_number;
	}
	public void setTrx_number(String trx_number) {
		this.trx_number = trx_number;
	}
	public String getUnidad_operativa() {
		return unidad_operativa;
	}
	public void setUnidad_operativa(String unidad_operativa) {
		this.unidad_operativa = unidad_operativa;
	}
	public BigDecimal getUnit_selling_price() {
		return unit_selling_price;
	}
	public void setUnit_selling_price(BigDecimal unit_selling_price) {
		this.unit_selling_price = unit_selling_price;
	}
	public String getUom_code() {
		return uom_code;
	}
	public void setUom_code(String uom_code) {
		this.uom_code = uom_code;
	}

	public int getC_invoice_id() {
		return c_invoice_id;
	}

	public void setC_invoice_id(int c_invoice_id) {
		this.c_invoice_id = c_invoice_id;
	}

}
