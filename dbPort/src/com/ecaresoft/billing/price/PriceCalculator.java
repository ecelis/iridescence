package com.ecaresoft.billing.price;

import static org.compiere.model.X_EXME_TipoPaciente.TIPOAREA_Ambulatory;
import static org.compiere.model.X_EXME_TipoPaciente.TIPOAREA_Emergency;
import static org.compiere.model.X_EXME_TipoPaciente.TIPOAREA_MedicalConsultation;
import static org.compiere.model.X_EXME_TipoPaciente.TIPOAREA_Other;
import static org.compiere.model.X_M_Product.PRODUCTCLASS_Drug;
import static org.compiere.model.X_M_Product.PRODUCTCLASS_MedicalSupplies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerCte;
import org.compiere.model.MConfigEC;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEConfigPre;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEEsqDesLine;
import org.compiere.model.MEXMETipoPaciente;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.EcsException;
import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Calcula el precio de un producto, deben llenarse los par&aacutemetros
 * necesarios y despues llamar el m&eacutetodo {@link #calculate()}, seguido de
 * revisar si {@link #errorList} est&aacute vac&iacutea
 * 
 * @author odelarosa
 * 
 */
public class PriceCalculator {
	private static CLogger LOGGER = CLogger.getCLogger(PriceCalculator.class);
	private MBPartner cbPartner;
	private MEXMECtaPac ctaPac;
	private Properties ctx;
	private Date date;
	private BigDecimal discount;
	private BigDecimal discountPercent;
	private ErrorList errorList = new ErrorList();
	private boolean minimumUom = false;
	private BigDecimal price = null;
	private int priceListId = -1;
	private int priceListVersionId = -1;
	private MProduct product;
	private boolean recalculate = false;
	private MUOM uom;

	/**
	 * Constructor por defecto
	 * 
	 * @param ctx
	 *            Contexto de la Aplicaci&oacuten
	 */
	public PriceCalculator(Properties ctx) {
		this.ctx = ctx;
	}

	/**
	 * Método que calcula el precio del producto, debe revisarse si
	 * {@link #errorList} est&aacute vac&iacutea, si no lo est&aacute hay que
	 * mostrar los errores
	 */
	public void calculate() {
		try {
			clean();

			if (validate()) {
				MPriceList priceList = getPriceList();

				if (price == null && priceList != null) {
					price = getPrice(priceList);
				}

				int cbPartnerId = cbPartner != null ? cbPartner.getC_BPartner_ID() : -1;
				int cBPGroupId = cbPartner != null ? cbPartner.getC_BP_Group_ID() : -1;
				int productId = product.getM_Product_ID();
				int productCategoryId = product.getM_Product_Category_ID();

				// Si el precio es nulo no hay descuento por convenio
				if (price == null) {
					discount = BigDecimal.ZERO;
					discountPercent = BigDecimal.ZERO;
				} else {
					// Se busca el convenio
					MEXMEEsqDesLine agreement = MEXMEEsqDesLine.getAgreement(ctx, cbPartnerId, cBPGroupId, Env.getTipoArea(ctx), productId, productCategoryId, date);

					// Si no hay convenio no hay descuento
					if (agreement == null) {
						discount = BigDecimal.ZERO;
						discountPercent = BigDecimal.ZERO;
					} else {
						// Si el descuento es cero, revisar el monto fijo
						if (BigDecimal.ZERO.compareTo(agreement.getList_Discount()) == 0) {
							discount = agreement.getList_AddAmt();

							discountPercent = BigDecimal.ZERO;
						} else {
							// Se revisa el porcentaje del convenio y se obtiene
							// el descuento
							discount = price.multiply(agreement.getList_Discount().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
							discountPercent = agreement.getList_Discount();
						}
					}
				}
			}
		} catch (Exception ex) {
			if (!(ex instanceof EcsException)) {
				errorList.add(Error.UNKNOWN_ERROR, ex.getLocalizedMessage());
				LOGGER.log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * Reinicia los campos por si se reutiliza el objeto
	 */
	private void clean() {
		price = null;
		discount = null;
		priceListVersionId = -1;
		priceListId = -1;
	}

	/**
	 * Lista de precios por Tipo de &Aacuterea
	 * 
	 * @return Lista de Precios
	 */
	private MPriceList getAreaList() {
		MPriceList priceList = null;

		String tipoArea = getTypeArea();
		String productClass = product.getProductClass();

		if ((TIPOAREA_MedicalConsultation.equals(tipoArea) || TIPOAREA_Other.equals(tipoArea) || tipoArea == null) 
				&& (PRODUCTCLASS_Drug.equals(productClass) || PRODUCTCLASS_MedicalSupplies.equals(productClass))) {
			MConfigEC configEC = MConfigEC.find(ctx, null);

			if (configEC != null && configEC.getM_PriceList_Reg_ID() > 0) {
				priceList = new MPriceList(ctx, configEC.getM_PriceList_Reg_ID(), null);
			}
		} else if ((TIPOAREA_MedicalConsultation.equals(tipoArea) || TIPOAREA_Other.equals(tipoArea) || TIPOAREA_Ambulatory.equals(tipoArea) 
				|| TIPOAREA_Emergency.equals(tipoArea) || tipoArea == null) && !(PRODUCTCLASS_Drug.equals(productClass) 
						|| PRODUCTCLASS_MedicalSupplies.equals(productClass))) {
			priceList = getConfigECList();
		} else if (!TIPOAREA_MedicalConsultation.equals(tipoArea) && (TIPOAREA_Other.equals(tipoArea) || tipoArea == null)) {
			priceList = getConfigPriceList();
		}

		return priceList;
	}

	/**
	 * Socio de negocios
	 * 
	 * @return Socio de Negocios
	 */
	public MBPartner getCbPartner() {
		return cbPartner;
	}

	/**
	 * Lista de precios de Configuraci&oacuten de Expediente Cl&iacutenico
	 * 
	 * @return Lista de precios correspondiente
	 */
	private MPriceList getConfigECList() {
		MPriceList priceList = null;

		MConfigEC configEC = MConfigEC.find(ctx, null);

		if (configEC != null && configEC.getM_PriceList_ID() > 0) {
			priceList = new MPriceList(ctx, configEC.getM_PriceList_ID(), null);
		}

		if (priceList != null) {
			price = getPrice(priceList);
		}

		if (price == null || BigDecimal.ZERO.compareTo(price) == 0) {
			price = null;
			priceList = getConfigPriceList();
		}

		return priceList;
	}

	/**
	 * Obtiene la lista de precios de la Configuraci&oacuten de Precios
	 * 
	 * @return
	 */
	private MPriceList getConfigPriceList() {
		int id = getPriceConfig().getM_PriceList_ID();

		if (id > 0) {
			return new MPriceList(ctx, id, null);
		} else {
			errorList.add(Error.CONFIGURATION_ERROR, Utilerias.getAppMsg(ctx, "msj.error.cp.sinlista"));
			throw new EcsException();
		}
	}

	/**
	 * Cuenta paciente
	 * 
	 * @return Cuenta paciente
	 */
	public MEXMECtaPac getCtaPac() {
		return ctaPac;
	}

	/**
	 * Fecha base
	 * 
	 * @return Fecha Base
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Descuento por convenio
	 * 
	 * @return Descuento
	 */
	public BigDecimal getDiscount() {
		return discount;
	}

	/**
	 * Porcentaje de descuento por convenio
	 * 
	 * @return Porcentaje
	 */
	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}

	/**
	 * Listado de errores
	 * 
	 * @return Errores
	 */
	public ErrorList getErrorList() {
		return errorList;
	}

	/**
	 * Lista de precios del socio de negocios, Primero revisa
	 * {@link MBPartnerCte#getM_PriceList_ID()}, despu&eacutes
	 * {@link MBPartner#getM_PriceList_ID()}, por &uacuteltimo
	 * {@link MBPGroup#getM_PriceList_ID()}
	 * 
	 * @return Lista de precios o nulo si no se cumplen las condiciones
	 */
	private MPriceList getPartnerList() {
		MPriceList priceList = null;
		if (cbPartner != null) {
			MBPartnerCte partnerCte = MBPartnerCte.get(ctx, cbPartner.getC_BPartner_ID(), null);

			MBPGroup group = cbPartner.getC_BP_Group_ID() > 0 ? new MBPGroup(ctx, cbPartner.getC_BP_Group_ID(), null) : null;

			// Si la pestaña de Información Adicional Cliente tiene lista de
			// precio
			if (partnerCte != null && partnerCte.getM_PriceList_ID() > 0) {
				priceList = new MPriceList(ctx, partnerCte.getM_PriceList_ID(), null);
			} else if (cbPartner.getM_PriceList_ID() > 0) {
				// Si la pestaña de Cliente tiene lista de precio
				priceList = new MPriceList(ctx, cbPartner.getM_PriceList_ID(), null);
			} else if (group != null && group.getM_PriceList_ID() > 0) {
				// Si el grupo de socios de negocio tiene lista
				priceList = new MPriceList(ctx, group.getM_PriceList_ID(), null);
			}
		}
		return priceList;
	}

	/**
	 * Antes de llamar este m&eacute deben llamar {@link #calculate()}
	 * 
	 * @return Regresa el precio del producto, <b>puede ser nulo</b> (si no es
	 *         encontrado en la lista)
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Obtiene el precio basado en la lista de precios
	 * 
	 * @param priceList
	 *            Lista de precios a revisar
	 * @return El precio del producto o nulo en caso de no estar presente en la
	 *         lista
	 */
	private BigDecimal getPrice(MPriceList priceList) {
		priceListId = -1;
		priceListVersionId = -1;

		BigDecimal price = null;

		StringBuilder sql = new StringBuilder(" SELECT ");

		if (isMinimumUom()) {
			sql.append(" pp.PRICELIST_VOL AS PriceList, ");
		} else {
			sql.append(" bomPriceList(p.M_Product_ID,pv.M_PriceList_Version_ID) AS PriceList, ");
		}

		sql.append(" pv.ValidFrom, pl.M_PriceList_ID, pv.M_PriceList_Version_ID ");
		sql.append(" FROM M_Product p ");
		sql.append(" INNER JOIN M_ProductPrice      pp ON p.M_Product_ID = pp.M_Product_ID AND pp.IsActive='Y' ");
		sql.append(" INNER JOIN M_PriceList_Version pv ON pp.M_PriceList_Version_ID=pv.M_PriceList_Version_ID  ");
		sql.append(" INNER JOIN M_Pricelist pl ON (pv.M_PriceList_ID=pl.M_PriceList_ID) ");
		sql.append(" WHERE pv.IsActive='Y' ");
		sql.append(" AND p.M_Product_ID = ? ");
		sql.append(" AND pv.M_PriceList_ID = ? ");

		if (isMinimumUom()) {
			sql.append(" AND pp.C_UOMVolume_ID = ? ");
		} else {
			sql.append(" AND pp.C_UOM_ID = ? ");
		}

		sql.append(" ORDER BY pv.ValidFrom DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, product.getM_Product_ID());
			pstmt.setInt(2, priceList.getM_PriceList_ID());
			pstmt.setInt(3, uom.getC_UOM_ID());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Timestamp plDate = rs.getTimestamp(2);

				if (plDate == null || !date.before(plDate)) {
					price = rs.getBigDecimal(1);
					if (rs.wasNull()) {
						price = Env.ZERO;
					}

					priceListId = rs.getInt(3);
					priceListVersionId = rs.getInt(4);

					break;
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return price;
	}

	/**
	 * Configuraci&oacuten de precios
	 * 
	 * @return Configuraci&oacuten de precios
	 * @throws EcsException
	 *             En caso de no tener configuraci&oacuten de precios
	 */
	private MEXMEConfigPre getPriceConfig() throws EcsException {
		MEXMEConfigPre configPre = MEXMEConfigPre.get(ctx, null);

		if (configPre == null) {
			errorList.add(Error.CONFIGURATION_ERROR, Utilerias.getAppMsg(ctx, "error.configPre"));
			throw new EcsException();
		}

		return configPre;
	}

	/**
	 * Busca la lista de precios adecuada
	 * 
	 * @return Lista de precios
	 */
	private MPriceList getPriceList() {
		MPriceList priceList = null;

		// Si tiene cuenta paciente
		if (ctaPac != null) {
			// Si no debe recalcular precios
			if (!mustRecalculate()) {
				// Obtenemos el último precio
				BigDecimal lastPrice = MCtaPacDet.getLastPrice(ctx, ctaPac.getEXME_CtaPac_ID(), product.getM_Product_ID(), null);

				// Si tiene precio previo
				if (lastPrice != null) {
					price = lastPrice;
				}
			}
		}

		// Si no se encontró el precio se busca en el socio de negocios
		if (price == null) {
			priceList = getPartnerList();

			// Si no tiene lista del socio de negocios se revisa la
			// configuraci&oacuten de expdiente cl&iacutenico
			if (priceList == null) {
				priceList = getAreaList();
			} else {
				BigDecimal tmp = getPrice(priceList);

				// Si tiene lista de socio pero no tiene precio
				if (tmp == null || BigDecimal.ZERO.compareTo(tmp) == 0) {
					String tipoArea = getTypeArea();

					if (!TIPOAREA_MedicalConsultation.equals(tipoArea) && (TIPOAREA_Other.equals(tipoArea) || tipoArea == null)) {
						priceList = getConfigPriceList();
					}
				} else {
					price = tmp;
				}
			}
		}

		return priceList;
	}

	/**
	 * Lista de precios donde se encontr&oacute el producto
	 * 
	 * @return Lista de precios o -1 si no se encontr&oacute
	 */
	public int getPriceListId() {
		return priceListId;
	}

	/**
	 * Versi&oacuten de lista de precios donde se encontr&oacute el producto
	 * 
	 * @return Versi&oacuten de lista de precios o -1 si no se encontr&oacute
	 */
	public int getPriceListVersionId() {
		return priceListVersionId;
	}

	/**
	 * Producto
	 * 
	 * @return Producto
	 */
	public MProduct getProduct() {
		return product;
	}

	/**
	 * Obtiene el tipo de &aacuterea de la cuenta en caso de tener
	 * 
	 * @return Tipo de &aacuterea de la cuenta o nulo en caso de no tener
	 */
	public String getTypeArea() {
		String tipoArea = null;
		if (ctaPac != null && ctaPac.getEXME_TipoPaciente_ID() > 0) {
			MEXMETipoPaciente tipoPaciente = ctaPac.getTipoPaciente();

			tipoArea = tipoPaciente.getTipoArea();
		}

		return tipoArea;
	}

	public boolean isMinimumUom() {
		return minimumUom;
	}

	/**
	 * Revisa si tiene o no que recalcular el precio
	 * 
	 * @return Si/No tiene que recalcular
	 */
	private boolean mustRecalculate() {
		return recalculate || getPriceConfig().isRecalculaPre();
	}

	public void setCbPartner(MBPartner cbPartner) {
		this.cbPartner = cbPartner;
	}

	public void setCtaPac(MEXMECtaPac ctaPac) {
		this.ctaPac = ctaPac;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setMinimumUom(boolean minimumUom) {
		this.minimumUom = minimumUom;
	}

	public void setProduct(MProduct product) {
		this.product = product;
	}

	public void setRecalculate(boolean recalculate) {
		this.recalculate = recalculate;
	}

	public void setUom(MUOM uom) {
		this.uom = uom;
	}

	/**
	 * Valida que el producto, unidad de medida, fecha y cliente sean asignados
	 * 
	 * @return Si/No es v&aacutelida la informaci&oacuten
	 */
	private boolean validate() {
		boolean valid = true;
		if (product == null) {
			valid = false;
			errorList.add(Error.EXCEPTION_ERROR, Utilerias.getAppMsg(ctx, "msj.error.productoRequerido"));
		} else if (uom == null) {
			valid = false;
			errorList.add(Error.EXCEPTION_ERROR, Utilerias.getAppMsg(ctx, "msj.error.uomRequerida"));
		} else if (date == null) {
			valid = false;
			errorList.add(Error.EXCEPTION_ERROR, Utilerias.getAppMsg(ctx, "enfermeria.proc.fecha"));
		} else if (cbPartner == null) {
			valid = false;
			errorList.add(Error.EXCEPTION_ERROR, Utilerias.getAppMsg(ctx, "msj.error.ClientRequerido"));
		}

		return valid;
	}
}