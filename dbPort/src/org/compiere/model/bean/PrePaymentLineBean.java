package org.compiere.model.bean;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.I_EXME_PaqBase_Version;
import org.compiere.model.MDHPatientInfo;
import org.compiere.model.MDHPrePaymentLine;
import org.compiere.model.MEXMEConfigEC;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPaqBaseVersion;
import org.compiere.model.MEXMETax;
import org.compiere.model.MPrecios;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.model.X_M_Product;
import org.compiere.model.bpm.GetPrice;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Bean para la creacion de pagos paquete/servicio
 * 
 * @author erodiguez
 *         Modificado por Lama: revision de calculo de precios e impuestos
 *                            : card 1618
 */
public class PrePaymentLineBean implements Cloneable {

	/**
	 * 
	 * @param bean
	 * @param payment
	 */
	public static void fillFrom(final PrePaymentLineBean bean, final MDHPrePaymentLine payment) {
		payment.setM_Product_ID(bean.getProductId());
		payment.setEXME_PaqBase_Version_ID(bean.getPqtId());
		payment.setPriceList(bean.getPriceList());
		payment.setQtyEntered(bean.getQty());
		payment.setTaxAmt(bean.getTax());
		payment.setLineTotalAmt(bean.getTotal());
	}

	private String		value;

	private int			id;
	private int			prepaymentId;
	private int			productId;
	private String		productName;
	private BigDecimal	priceList;
	private int			qty;
	private BigDecimal	subtotal;
	private BigDecimal	tax;
	private BigDecimal	total;
	private int			pqtId;
	private MTax		mTax;

	public PrePaymentLineBean() {}

	/**
	 * 
	 * @param ctx
	 * @param productId
	 * @param qty
	 * @param patient
	 */
	public PrePaymentLineBean(final Properties ctx, final int productId, final int qty, final MEXMEPaciente patient) {
		if (productId <= 0) {
			throw new MedsysException(Utilerias.getMsg(ctx, "msj.error.productoRequerido"));
		} else if (qty <= 0) {
			throw new MedsysException(Utilerias.getMsg(ctx, "error.captMiniPack.cantidadCero"));
		}

		final MProduct prod = new MProduct(ctx, productId, null);
		if (prod.is_new()) {
			throw new MedsysException(Utilerias.getMsg(ctx, "error.citas.noExisteProd"));
		}

		final MDHPatientInfo retValue = patient == null ? null : MDHPatientInfo.getFrom(ctx, patient.getEXME_Paciente_ID(), null);

		if (X_M_Product.PRODUCTTYPE_Package.equals(prod.getProductType())) {
			if (retValue != null && MDHPatientInfo.SOCREDITSTATUS_Overdue.equals(retValue.getSOCreditStatus())) {
				throw new MedsysException(Utilerias.getMsg(ctx, "msj.error.credit.addPackage"));
			}
			final MEXMEPaqBaseVersion paq = new Query(ctx, I_EXME_PaqBase_Version.Table_Name, " M_Product_Id=? ", null)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setParameters(prod.getM_Product_ID())//
				.first();
			if (paq != null) {
				if (paq.getPaqBase().isUniversalComp()) {
					throw new MedsysException(Utilerias.getMsg(ctx, "msj.error.addUniversalComp"));
				} else {
					setPqtId(paq.getEXME_PaqBase_Version_ID());
				}
			}
		}
		setPrices(prod, patient, retValue);
		calculateTotals(qty);
	}

	/**
	 * 
	 * @param prod
	 * @param patient
	 * @param dhInfo
	 */
	private void setPrices(final MProduct product, final MEXMEPaciente patient, final MDHPatientInfo dhInfo) {
		setProductId(product.getM_Product_ID());
		setValue(product.getValue());
		setProduct(product.getName());
		
		// Si el paciente es un cliente de Pro Mujer, entonces de la Configuración de Precios
		// Toma el socio de negocios: 'Compañía' si el paciente ha adquirido un paquete prepago,
		// si no, toma el socio de negocios: 'Ventas de mostrador'
		// Si no, toma el socio de negocios: 'Compañía' de la configuración de Expediente Clínico
		int bPartnerId;// patient.getPatientBPartner().get_ID()
		if (patient == null || dhInfo == null) {
			bPartnerId = MEXMEConfigEC.get(product.getCtx()).getC_BPartner_ID();
		} else {
			bPartnerId = dhInfo.getBPartnerId();
		}

		final MPrecios precio = GetPrice.getPriceVta(product.getCtx(), product.getM_Product_ID(), bPartnerId,//
			product.getC_UOM_ID(), Env.getCurrentDate(), Env.getTipoArea(product.getCtx()));

		mTax = MEXMETax.getImpuestoProducto(product.getCtx(), product.getM_Product_ID(), null);

		if (precio == null || precio.getPriceList() == null) {
			setPriceList(BigDecimal.ZERO);
		} else {
			setPriceList(precio.getPriceList());
		}
	}
	
	/**
	 * Calcula el impuesto , subototal y total en base a la cantidad seleccionada
	 * 
	 * @param qtyEntered  cantidad seleccionada
	 */
	public void calculateTotals(int qtyEntered) {
		setQty(qtyEntered);
		final BigDecimal qtyBD = BigDecimal.valueOf(qty);
		setSubtotal(getPriceList().multiply(qtyBD));
		if (mTax == null) {
			setTax(BigDecimal.ZERO);
		} else {
			setTax(mTax.calculateTax(getSubtotal(), false, 2));
		}
		setTotal(getTax().add(getSubtotal()));
	}
	
	@Override
	public PrePaymentLineBean clone() {
		PrePaymentLineBean obj = null;
		try {
			obj = (PrePaymentLineBean) super.clone();
		} catch (final CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	@Override
	public boolean equals(final Object o) {
		if (o != null && o instanceof PrePaymentLineBean) {
			return ((PrePaymentLineBean) o).toString().equals(this.toString());
		} else {
			return false;
		}
	}

	public int getId() {
		return id;
	}

	public int getPqtId() {
		return pqtId;
	}

	public int getPrepaymentId() {
		return prepaymentId;
	}

	public BigDecimal getPriceList() {
		return priceList;
	}

	public String getProduct() {
		return productName;
	}

	public int getProductId() {
		return productId;
	}

	public int getQty() {
		return qty;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public String getValue() {
		return value;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setPqtId(final int pqtId) {
		this.pqtId = pqtId;
	}

	public void setPrepaymentId(final int prepaymentId) {
		this.prepaymentId = prepaymentId;
	}

	public void setPriceList(final BigDecimal priceList) {
		this.priceList = priceList;
	}

	public void setProduct(final String product) {
		this.productName = product;
	}

	public void setProductId(final int productId) {
		this.productId = productId;
	}

	public void setQty(final int qty) {
		this.qty = qty;
	}

	public void setSubtotal(final BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public void setTax(final BigDecimal tax) {
		this.tax = tax;
	}

	public void setTotal(final BigDecimal total) {
		this.total = total;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * @return Lista de errores
	 */
	public ErrorList validate() {
		final ErrorList errorList = new ErrorList();
		final Properties ctx = Env.getCtx();
		if (getProductId() == 0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "msj.error.productoRequerido"));
		}
		if (getQty() <= 0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "error.captMiniPack.cantidadCeroo"));
		}
		return errorList;
	}

}
