/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * Bean para tener los precios de los productos
 * 
 * @author Expert
 * 
 */
public class MPrecios {

	private Properties ctx = null;
	private String mProcessMsg = null;
	private BigDecimal priceList = Env.ZERO;
	private BigDecimal priceStd = Env.ZERO;
	private BigDecimal priceLimit = Env.ZERO;
	private BigDecimal costo = Env.ZERO;
	private BigDecimal sobrePrecio = Env.ZERO;
	private boolean usoFactor = false;
	private int mPriceListID = 0;
	private int cBPartnerID = 0;
	private int esqDesLineID = 0;
	private int cCurrencyId = 0;

	private boolean isTaxIncluded = false;
	private int cTaxCategoryID = 0;
	private String productDesc = null;
	private String productValue = null;
	private int mProductID = 0;
	private int cUOMId = 0;

	private BigDecimal discountAmt = Env.ZERO;
	private BigDecimal discountPorc = Env.ZERO;
	private BigDecimal precioOriginal = Env.ZERO;

	private Timestamp fecha = null;
	private boolean seObtuvoPrecio = false;

	/**
	 * El precio puede ser cero
	 * @return
	 */
	public boolean isSeObtuvoPrecio() {
		return seObtuvoPrecio;
	}

	/**
	 * El precio puede ser cero
	 * @param seObtuvoPrecio
	 */
	public void setSeObtuvoPrecio(final boolean seObtuvoPrecio) {
		this.seObtuvoPrecio = seObtuvoPrecio;
	}

	/**
	 * Precio limite, el impuesto puede estar 
	 * incluido por lo que se descuenta
	 * @return
	 */
	public BigDecimal getPriceLimit() {
		if (this.priceLimit == null) {
			this.priceLimit = Env.ZERO;
		}

		if (isTaxIncluded() && priceLimit.compareTo(Env.ZERO) > 0) {
			final MTax tax = getTax(this.priceLimit);
			if (tax != null){
				this.priceLimit = this.priceLimit.divide(Env.ONE.add(tax
						.getRate().divide(Env.ONEHUNDRED)));
			}
		}
		return this.priceLimit;
	}

	/**
	 * Precio de lista, el impuesto puede estar 
	 * incluido por lo que se descuenta
	 * @return
	 */
	public BigDecimal getPriceList() {

		if (this.priceList == null) {
			this.priceList = Env.ZERO;
		}

		if (isTaxIncluded() && priceList.compareTo(Env.ZERO) > 0) {
			final MTax tax = getTax(this.priceList);
			if (tax != null){
				this.priceList = this.priceList.divide(Env.ONE.add(tax
						.getRate().divide(Env.ONEHUNDRED)));
			}
		}

		return this.priceList;
	}

	/**
	 * Precio de Estandar o Actual, el impuesto puede estar 
	 * incluido por lo que se descuenta
	 * @return
	 */
	public BigDecimal getPriceStd() {
		if (this.priceStd == null) {
			this.priceStd = Env.ZERO;
		}

		if (isTaxIncluded() && priceStd.compareTo(Env.ZERO) > 0) {
			final MTax tax = getTax(this.priceStd);
			if (tax != null){
				this.priceStd = this.priceStd.divide(Env.ONE.add(tax.getRate()
						.divide(Env.ONEHUNDRED)));
			}
		}

		return this.priceStd;
	}

	/**
	 * Obj de impuesto del producto
	 * @return
	 */
	public MTax getTax() {
		return MEXMETax.getImpuestoProducto(getCtx(),getM_Product_ID(), null);
	}

	/**
	 * Obj de impuesto del producto
	 * @param monto 
	 * @return
	 */
	public MTax getTax(final BigDecimal monto) {
		return MEXMETax.getImpuestoProducto(getCtx(), getM_Product_ID(), null, monto);
	}

	/**
	 * Contexto
	 * @param ctx
	 */
	public void setCtx(final Properties ctx) {
		this.ctx = null;
		this.ctx = ctx;
	}

	/***************************************************/
	public Timestamp getFecha() {
		return this.fecha;
	}

//	public void setFecha(final Timestamp fecha) {
//		this.fecha = fecha;
//	}

	public int getC_currency_id() {
		return this.cCurrencyId;
	}

	public void setC_currency_id(final int cCurrencyId) {
		this.cCurrencyId = cCurrencyId;
	}

	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(final BigDecimal costo) {
		this.costo = costo;
	}

	public String getProcessMsg() {
		return this.mProcessMsg;
	}

	public void setProcessMsg(final String msg) {
		this.mProcessMsg = msg;
	}

	public Properties getCtx() {
		return this.ctx;
	}

	public void setPriceLimit(final BigDecimal priceLimit) {
		this.priceLimit = priceLimit;
	}

	public void setPriceList(final BigDecimal priceList) {
		this.priceList = priceList;
	}

	public void setPriceStd(final BigDecimal priceStd) {
		this.priceStd = priceStd;
	}

	public BigDecimal getSobrePrecio() {
		return this.sobrePrecio;
	}

	public void setSobrePrecio(final BigDecimal sobrePrecio) {
		this.sobrePrecio = sobrePrecio;
	}

	public boolean isUsoFactor() {
		return this.usoFactor;
	}

	public void setUsoFactor(final boolean usoFactor) {
		this.usoFactor = usoFactor;
	}

	public int getC_BPartner_ID() {
		return this.cBPartnerID;
	}

	public void setC_BPartner_ID(final int partner_ID) {
		this.cBPartnerID = partner_ID;
	}
//
	public int getM_PriceList_ID() {
		return this.mPriceListID;
	}

	public void setM_PriceList_ID(final int priceList_ID) {
		this.mPriceListID = priceList_ID;
	}
//
	public int getEXME_EsqDesLine_ID() {
		return this.esqDesLineID;
	}

	public void setEXME_EsqDesLine_ID(final int esqDesLine_ID) {
		this.esqDesLineID = esqDesLine_ID;
	}

	public boolean isTaxIncluded() {
		return this.isTaxIncluded;
	}

	public void setTaxIncluded(final boolean isTaxIncluded) {
		this.isTaxIncluded = isTaxIncluded;
	}

	public int getC_TaxCategory_ID() {
		return this.cTaxCategoryID;
	}

	public void setC_TaxCategory_ID(final int taxCategory_ID) {
		this.cTaxCategoryID = taxCategory_ID;
	}

	public String getDescripcion() {
		return this.productDesc;
	}

	public void setDescripcion(final String descripcion) {
		this.productDesc = descripcion;
	}

	public String getProductValue() {
		return this.productValue;
	}
	
	public void setProductValue(final String value) {
		this.productValue = value;
	}

	public int getM_Product_ID() {
		return this.mProductID;
	}

	public void setM_Product_ID(final int product_ID) {
		this.mProductID = product_ID;
	}

	public BigDecimal getDiscountAmt() {
		return this.discountAmt;
	}

	public void setDiscountAmt(final BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}

	public BigDecimal getDiscountPorc() {
		return this.discountPorc;
	}

	public void setDiscountPorc(final BigDecimal discountPorc) {
		this.discountPorc = discountPorc;
	}

	public BigDecimal getPrecioOriginal() {
		return this.precioOriginal;
	}

	public void setPrecioOriginal(final BigDecimal precioOriginal) {
		this.precioOriginal = precioOriginal;
	}

	public int getC_uom_id() {
		return this.cUOMId;
	}

	public void setC_uom_id(final int cUomId) {
		this.cUOMId = cUomId;
	}
	/***********************************************/
	/**
	 * Llena los datos de precios
	 * 
	 * @param ctaPacDet
	 * @return
	 */
	public MCtaPacDet preciosActual(final MCtaPacDet ctaPacDet) {

		if(!isSeObtuvoPrecio()){
			return ctaPacDet;
		}
		final int scale = MCurrency.getStdPrecision(ctaPacDet.getCtx(),
				Env.getC_Currency_ID(ctaPacDet.getCtx())); // decimas.

		if (getCosto() != null){
			ctaPacDet.setCosto(this.costo.setScale(scale,
					BigDecimal.ROUND_HALF_UP));
		}
		if (getPriceList() != null) {
			ctaPacDet.setPriceList(getPriceList().setScale(scale,
					BigDecimal.ROUND_HALF_UP));
		}
		if (getPriceLimit() != null) {
			ctaPacDet.setPriceLimit(getPriceLimit().setScale(scale,
					BigDecimal.ROUND_HALF_UP));
		}
		if (getPriceStd() != null) {
			ctaPacDet.setPriceActual(getPriceStd().setScale(scale,
					BigDecimal.ROUND_HALF_UP));
		}
		ctaPacDet.setEXME_EsqDesLine_ID(getEXME_EsqDesLine_ID());
		ctaPacDet.setUsarFactor(this.usoFactor);
		ctaPacDet.setDiscount(this.discountPorc.setScale(scale,BigDecimal.ROUND_HALF_UP));
		ctaPacDet.setDiscountAmt(this.discountAmt.setScale(scale,BigDecimal.ROUND_HALF_UP));
		ctaPacDet.setPricesInv();
		ctaPacDet.setLineNetAmt();
		ctaPacDet.setProductValue(this.productValue);// Datos de socio (C_BPartner.IsFactEspec)
		ctaPacDet.setProductDescription(this.productDesc);
		ctaPacDet.setChargeAmt(this.precioOriginal.setScale(scale,BigDecimal.ROUND_HALF_UP));
		ctaPacDet.setMTaxAmt(getTax(ctaPacDet.getLineNetAmt()));
		return ctaPacDet;
	}

	/**
	 * Llena los datos de precios
	 * 
	 * @param actPacienteInd
	 * @return
	 */
	public MEXMEActPacienteInd updatePrices(final MEXMEActPacienteInd actPacienteInd) {

		if(!isSeObtuvoPrecio()){
			return actPacienteInd;
		}
		
		final int scale = MCurrency.getStdPrecision(actPacienteInd.getCtx(),
				Env.getC_Currency_ID(actPacienteInd.getCtx())); // decimas.
		
		if (actPacienteInd.getQtyDelivered().compareTo(Env.ZERO) <= 0) {
			actPacienteInd.setQtyDelivered(Env.ZERO);// ONE);
			// JGaray: Para establecer cero al surtir
		}

		if (actPacienteInd.getQtyDelivered().compareTo(Env.ZERO) <= 0){
			actPacienteInd.setQtyDelivered(Env.ONE);
		}
		
		actPacienteInd.setEXME_EsqDesLine_ID(esqDesLineID);
		actPacienteInd.setCosto(costo.setScale(scale,
				BigDecimal.ROUND_HALF_UP));
		actPacienteInd.setPriceList(getPriceList().setScale(scale,
				BigDecimal.ROUND_HALF_UP));
		actPacienteInd.setPriceLimit(getPriceLimit().setScale(scale,
				BigDecimal.ROUND_HALF_UP));
		actPacienteInd.setPriceActual(getPriceStd().setScale(scale,
				BigDecimal.ROUND_HALF_UP));
		actPacienteInd.setDiscount(discountAmt.setScale(scale,
				BigDecimal.ROUND_HALF_UP));
//		actPacienteInd.setLineNetAmt(getPriceStd().multiply(actPacienteInd.getQtyDelivered()).setScale(scale,BigDecimal.ROUND_HALF_UP));

		actPacienteInd.setChargeAmt(precioOriginal.setScale(scale,
				BigDecimal.ROUND_HALF_UP));
		
		actPacienteInd.setTaxAmt(getTax(actPacienteInd.getLineNetAmt()));
		
//		final MEXMETax tax = getTax(actPacienteInd.getLineNetAmt());
//		BigDecimal taxAmt = Env.ZERO;
//		if (tax != null) {
//			actPacienteInd.setC_Tax_ID(tax.getC_Tax_ID());
//			taxAmt = tax.getAmount();
//		}
//
//		actPacienteInd.setTotalImp(taxAmt.setScale(scale,
//				BigDecimal.ROUND_HALF_UP));
		return actPacienteInd;
	}

	/**
	 * A partir del objeto del cargo de la cuenta paciente
	 * @param ctaPacDet Obj. con precios
	 * @return MPrecios con los precios del cargo
	 */
	public MPrecios setValues(final MCtaPacDet ctaPacDet) {

		costo=ctaPacDet.getCosto();
		usoFactor=ctaPacDet.isUsarFactor();
		priceList=ctaPacDet.getPriceList();
		
		priceLimit=ctaPacDet.getPriceLimit();
		priceStd=ctaPacDet.getPriceActual();
		discountPorc=ctaPacDet.getDiscount();// Se cambia a monto por porcentaje Expert:aaranda 15092010
		productValue = ctaPacDet.getProductValue();
		precioOriginal = ctaPacDet.getChargeAmt();
		return this;
	}

	/**
	 * Values Default
	 * Pone los valores minimos y por defecto para obtener un objeto MPrecios
	 * @param ctx Contexto
	 * @param precio Precio para el producto
	 * @param productID Product
	 * @param uomID Unidad de medida que corresponde con el precio
	 */
	public void setValuesDefault(final Properties ctx, final BigDecimal precio, final int productID, final int uomID ){
		setCtx(ctx);
		
		priceList=precio;
		priceStd=precio;
		priceLimit=precio;
		seObtuvoPrecio=true;
		productValue = null;
		productDesc= null;
		isTaxIncluded=false;
		cCurrencyId = Env.getC_Currency_ID(ctx);
		
		// Tasa de impuestos
		final MEXMEProductoOrg prodorg = MEXMEProductoOrg.getProductoOrg(ctx, productID, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), null);
		if (prodorg != null) {
			this.cTaxCategoryID = prodorg.getC_TaxCategory_ID();
		}
		
		// Datos del producto
		this.mProductID = productID;
		this.cUOMId = uomID;
		this.mPriceListID = 0;
		this.cBPartnerID = 0;
	}
	
}
