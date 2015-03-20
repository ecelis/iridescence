package org.compiere.apps.form;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Env;

/**
 * Bean de pagos a facturas (anticipos y pagos proveedor)
 * Bean de cobros a facturas (anticipos y cobros a facturas)
 * @author andres
 *         Modificado por Lorena Lama
 *         Modificado por Twry
 */
public class BeanPaySelect {
	private int invoiceId;
	private Timestamp dateInvoiced;
	private Timestamp dateDue;
	private Integer daysDue;
	private String client;
	private String documentNo;
	private int typeDoc;
	private String currency;
	private BigDecimal total;//GrandTotal
	private BigDecimal discount;
	private String dateDiscount;
	private BigDecimal dueAmt;
	private BigDecimal payAmt;
	private List<BeanPaySelect> payLst;
	private BigDecimal imp;
	private int countInvoice;
	private int vencidas;
	private String partner;
	private int partnerId;
	private int currencyId;
	private BigDecimal conversionAmt;//En moneda del cliente

	private BigDecimal openInvoice;//Saldo en moneda factura
	private BigDecimal applied;// Aplicado
	private BigDecimal bad;//Incobrable
	private BigDecimal notApplied;//Cobro no aplicado
	private boolean    payOff;// Cubierto
	private BigDecimal newOpenInvoiced;// Nuevo saldo de la factura
	private BigDecimal rateInvoice;// Conversion de la moneda de la factura a la moneda default
	private BigDecimal rateAllocation;
	private String     description;

	public BigDecimal getRateAllocation() {
		return rateAllocation==null?Env.ONE:rateAllocation;
	}

	public void setRateAllocation(BigDecimal rateAllocation) {
		this.rateAllocation = rateAllocation;
	}

	public BigDecimal getRateInvoice() {
		if(rateInvoice==null)
			rateInvoice =  Env.ONE;
		return rateInvoice;
	}

	public void setRateInvoice(BigDecimal rateInvoice) {
		this.rateInvoice = rateInvoice;
	}

	public BigDecimal getNewOpenInvoiced() {
		return newOpenInvoiced==null?Env.ZERO:newOpenInvoiced;
	}

	public void setNewOpenInvoiced(BigDecimal newOpenInvoiced) {
		this.newOpenInvoiced = newOpenInvoiced;
	}

	public BigDecimal getNotApplied() {
		return notApplied==null?Env.ZERO:notApplied;
	}

	public void setNotApplied(BigDecimal notApplied) {
		this.notApplied = notApplied;
	}

	public boolean isPayOff() {
		return payOff;
	}

	public void setPayOff(boolean payOff) {
		this.payOff = payOff;
	}

	public BigDecimal getConversionAmt() {
		return conversionAmt;
	}

	public void setConversionAmt(BigDecimal conversionAmt) {
		this.conversionAmt = conversionAmt;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public int getCountInvoice() {
		return countInvoice;
	}

	public void setCountInvoice(int countInvoice) {
		this.countInvoice = countInvoice;
	}
	
	public int getVencidas() {
		return vencidas;
	}

	public void setVencidas(int vencidas) {
		this.vencidas = vencidas;
	}
	
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public int getInvoiceId() {
		return invoiceId;
	}
	
	public BigDecimal getImp() {
		return imp;
	}

	public void setImp(BigDecimal imp) {
		this.imp = imp;
	}
	
	public Integer getDaysDue() {
		return daysDue;
	}

	public void setDaysDue(Integer daysDue) {
		this.daysDue = daysDue;
	}	

	public BeanPaySelect() {
		super();
	}

	public List<BeanPaySelect> getPayLst() {
		return payLst;
	}

	public void setPayLst(List<BeanPaySelect> payLst) {
		this.payLst = payLst;
	}

	public Timestamp getDateDue() {
		return dateDue;
	}

	public void setDateDue(Timestamp dateDue) {
		this.dateDue = dateDue;
	}
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getDateDiscount() {
		return dateDiscount;
	}

	public void setDateDiscount(String dateDiscount) {
		this.dateDiscount = dateDiscount;
	}

	public BigDecimal getDueAmt() {
		return dueAmt;
	}

	public void setDueAmt(BigDecimal dueAmt) {
		this.dueAmt = dueAmt;
	}

	public BigDecimal getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	
	public Timestamp getDateInvoiced() {
		return dateInvoiced;
	}
	
	public void setDateInvoiced(Timestamp dateInvoiced) {
		this.dateInvoiced = dateInvoiced;
	}
	
	public int getTypeDoc() {
		return typeDoc;
	}

	public void setTypeDoc(int typeDoc) {
		this.typeDoc = typeDoc;
	}

	public BigDecimal getOpenInvoice() {
		return openInvoice;
	}

	public void setOpenInvoice(BigDecimal openInvoice) {
		this.openInvoice = openInvoice;
	}

	public BigDecimal getBad() {
		return bad==null?Env.ZERO:bad;
	}

	public void setBad(BigDecimal bad) {
		this.bad = bad;
	}

	public BigDecimal getApplied() {
		return applied==null?Env.ZERO:applied;
	}

	public void setApplied(BigDecimal applied) {
		this.applied = applied;
	}
	
	public String getDescription() {
		return description==null?StringUtils.EMPTY:description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
