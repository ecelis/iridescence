package org.compiere.model.bean;

import static org.compiere.util.Env.getCtx;
import static org.compiere.util.Utilerias.getAppMsg;

import java.math.BigDecimal;

import org.compiere.model.I_M_MovementLine;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MEXMEConfigPre;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MMovementLineConfirm;
import org.compiere.model.MPrecios;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MWarehouse;
import org.compiere.model.X_M_Movement;
import org.compiere.model.X_M_Product;
import org.compiere.model.bpm.GetPrice;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.POConfirmPanel;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * 
 * @author odelarosa Modificado por Lorena Lama - Confirmacion
 */
public class MovementLine implements Cloneable {

	/**
	 * Llena los datos de MovementLine con la informacion de MMovementLine
	 * 
	 * @param line
	 *            MovementLine
	 * @param po
	 *            MMovementLine
	 */
	public static void fillFrom(final MovementLine line, final MMovementLine po) {
		line.setId(po.getM_MovementLine_ID());
		line.setMovementId(po.getM_Movement_ID());
		line.setProduct(po.getProduct());
		line.setOriginalQty(po.getOriginalQty());
		line.setOriginalVolQty(po.getOriginalQty_Vol());
		line.setTargetQty(po.getTargetQty());
		line.setTargetVolQty(po.getTargetQty_Vol());
		line.setConfirmedQty(po.getConfirmedQty());
		line.setConfirmedVolQty(po.getConfirmedQty_Vol());
		line.setScrappedQty(po.getScrappedQty());
		line.setScrappedVolQty(po.getScrappedQty_Vol());
		line.setUomId(po.getC_UOM_ID());
		line.setUomVolId(po.getC_UOMVolume_ID());
		line.setSelectedUomId(po.getOp_Uom());
		line.setLocatorFromId(po.getM_Locator_ID());
		line.setLocatorToId(po.getM_LocatorTo_ID());
		line.setCtaPac(po.getCtaPac());
		line.setDescription(po.getDescription());
		line.setLotId(po.getM_AttributeSetInstance_ID());
	}

	/**
	 * Llena los datos de MMovementLine con la informacion de MovementLine
	 * 
	 * @param line
	 *            MovementLine
	 * @param po
	 *            MMovementLine
	 */
	public static void fillRequest(final MovementLine line, final MMovementLine po) {
		po.setM_Movement_ID(line.getMovementId());
		po.setM_Product_ID(line.getProduct().getM_Product_ID());

		if (line.getProduct().getC_UOM_ID() != line.getProduct().getC_UOMVolume_ID() && line.getProduct().getC_UOMVolume_ID() == line.getSelectedUomId()) {
			BigDecimal qtyVol = line.getOriginalQty();
			qtyVol = qtyVol.setScale(0, BigDecimal.ROUND_FLOOR);

			po.setOriginalQty_Vol(qtyVol);
			line.setOriginalVolQty(qtyVol);

			final BigDecimal qty = MEXMEUOMConversion.convertProductFrom(getCtx(), line.getProduct().getM_Product_ID(), line.getProduct().getC_UOMVolume_ID(), qtyVol, null, true);

			po.setOriginalQty(qty);
			line.setOriginalQty(qty);
		} else {
			line.setOriginalVolQty(BigDecimal.ZERO);
			po.setOriginalQty(line.getOriginalQty());
			po.setOriginalQty_Vol(BigDecimal.ZERO);
		}

		po.setC_UOM_ID(line.getProduct().getC_UOM_ID());
		line.setUomId(po.getC_UOM_ID());

		po.setC_UOMVolume_ID(line.getProduct().getC_UOMVolume_ID());
		line.setUomVolId(po.getC_UOMVolume_ID());

		po.setOp_Uom(line.getSelectedUomId());

		po.setM_Locator_ID(line.getLocatorFromId());
		po.setM_LocatorTo_ID(line.getLocatorToId());

		po.setDescription(line.getDescription());

		if (line.getCtaPac() != null) {
			po.setEXME_CtaPac_ID(line.getCtaPac().getEXME_CtaPac_ID());
		}
		po.setM_AttributeSetInstance_ID(line.getLotId());
	}

	private BigDecimal chargeQty;
	private BigDecimal chargeVolQty;
	private BigDecimal confirmedQty;
	private BigDecimal confirmedVolQty;
	private POConfirmPanel confirmPanel;
	private MEXMECtaPac ctaPac;
	private String description;
	private int id;
	private int locatorFromId;
	private String locatorFromName;
	private int locatorToId;
	private String locatorToName;
	private int lotId;
	private String lotName;
	private int movementId;
	private MovementLine movLineRef;
	private BigDecimal originalQty;
	private BigDecimal originalVolQty;
	private MProduct product;
	private BigDecimal returnQty;
	private BigDecimal returnVolQty;
	private BigDecimal scrappedQty;
	private BigDecimal scrappedVolQty;
	private int selectedUomId;
	private String selectedUomName;
	private BigDecimal sumReturnQty;
	private BigDecimal sumReturnVolQty;
	private BigDecimal targetQty;
	private BigDecimal targetVolQty;
	private int uomId;

	private int uomVolId;

	public MovementLine() {

	}

	/** Quitar la referencia al pedido */
	public void cleanRefMovementLine() {
		movLineRef = new MovementLine();
	}

	@Override
	public MovementLine clone() {
		MovementLine obj = null;
		try {
			obj = (MovementLine) super.clone();
		} catch (final CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof MovementLine) {
			return ((MovementLine) o).toString().equals(this.toString());
		} else
			return false;
	}

	/** Cantidad a considerar para generar el cargo a cuenta paciente */
	public BigDecimal getChargeQty() {
		if (chargeQty == null) {
			chargeQty = Env.ZERO;
		}
		return chargeQty;
	}

	/** Cantidad a considerar para generar el cargo a cuenta paciente */
	public BigDecimal getChargeVolQty() {
		if (chargeVolQty == null) {
			chargeVolQty = Env.ZERO;
		}
		return chargeVolQty;
	}

	public BigDecimal getConfirmedQty() {
		if (confirmedQty == null) {
			confirmedQty = Env.ZERO;
		}
		return confirmedQty;
	}

	public BigDecimal getConfirmedVolQty() {
		if (confirmedVolQty == null) {
			confirmedVolQty = Env.ZERO;
		}
		return confirmedVolQty;
	}

	public POConfirmPanel getConfirmPanel() {
		return confirmPanel;
	}

	public MEXMECtaPac getCtaPac() {
		return ctaPac;
	}

	public String getDescription() {
		return description;
	}

	/** si la diferencia es entre lo solicitado y lo surtido (Checar UOM Min / Vol) */
	public BigDecimal getDifferenceQty() {
		final BigDecimal difference;
		if (confirmedQty == null && targetQty == null //
				|| confirmedVolQty == null && targetVolQty == null) {
			difference = BigDecimal.ZERO;
		} else if (isVolumeSelected()) {
			if (originalVolQty.compareTo(targetVolQty) != 0) {
				difference = originalVolQty.subtract(targetVolQty);
			} else {
				difference = BigDecimal.ZERO;
			}
		} else {
			if (originalQty.compareTo(targetQty) != 0) {
				difference = getOriginalQty().subtract(targetQty);
			} else {
				difference = BigDecimal.ZERO;
			}
		}
		return difference;
	}

	public int getId() {
		return id;
	}

	public int getLocatorFromId() {
		return locatorFromId;
	}

	public String getLocatorFromName() {
		if (locatorFromName == null) {
			locatorFromName = new MLocator(getCtx(), locatorFromId, null).getName();
		}
		return locatorFromName;
	}

	public int getLocatorToId() {
		return locatorToId;
	}

	public String getLocatorToName() {
		if (locatorToName == null) {
			locatorToName = new MLocator(getCtx(), locatorToId, null).getName();
		}
		return locatorToName;
	}

	public int getLotId() {
		return lotId;
	}

	public String getLotName() {
		if (lotId > 0 && lotName == null) {
			lotName = new MAttributeSetInstance(getCtx(), getLotId(), null).getName();
		}
		return lotName;
	}

	public int getMovementId() {
		return movementId;
	}

	public BigDecimal getOriginalQty() {
		return originalQty;
	}

	public BigDecimal getOriginalVolQty() {
		return originalVolQty;
	}

	public MPrecios getPrice() {
		return GetPrice.getPriceMov(getCtx(), getProduct().getM_Product_ID(), getCtaPac().getExtCero().getC_BPartner_ID(), getSelectedUomId(), Env.getCurrentDate(), getCtaPac().getEXME_CtaPac_ID(), getCtaPac().getTipoPaciente().getTipoArea(), null);
	}

	public MProduct getProduct() {
		return product;
	}

	/**
	 * Obtener la linea del pedido de consigna que hace referencia al pedido original Siempre y cuando sea virtual
	 */
	public MovementLine getRefMovementLine(String trxName) {
		if (movLineRef == null && getUserReturnQty().compareTo(Env.ZERO) > 0) {
			movLineRef = MMovementLine.getParentMovementConsigna(Env.getCtx(), getId(), getLocatorFromId(), trxName);// TODO: Validar localizador
			if (movLineRef == null) {
				cleanRefMovementLine();
			} else {
				// Como caso de prueba puede ser que el almacen de consigna surta una parte y el almacen propio otra
				final BigDecimal qty = movLineRef.getConfirmedQty().add(movLineRef.getScrappedQty());
				if (getReturnQty().compareTo(qty) >= 0) {
					movLineRef.setReturnQty(qty);
				} else {
					movLineRef.setReturnQty(getReturnQty());
				}

				final BigDecimal qtyVol = movLineRef.getConfirmedVolQty().add(movLineRef.getScrappedVolQty());
				if (getReturnVolQty().compareTo(qtyVol) >= 0) {
					movLineRef.setReturnVolQty(qtyVol);
				} else {
					movLineRef.setReturnVolQty(getReturnVolQty());
				}
			}
		}
		return movLineRef;
	}

	public BigDecimal getReturnQty() {
		if (returnQty == null) {
			returnQty = Env.ZERO;
		}
		return returnQty;
	}

	public BigDecimal getReturnVolQty() {
		if (returnVolQty == null) {
			returnVolQty = Env.ZERO;
		}
		return returnVolQty;
	}

	/** Cantidad surtida menos cantidad confirmada por el usuario */
	public BigDecimal getScrappedQty() {
		if (scrappedQty == null) {
			scrappedQty = Env.ZERO;
		}
		return scrappedQty;
	}

	/** Cantidad surtida menos cantidad confirmada por el usuario */
	public BigDecimal getScrappedVolQty() {
		if (scrappedVolQty == null) {
			scrappedVolQty = Env.ZERO;
		}
		return scrappedVolQty;
	}

	public int getSelectedUomId() {
		return selectedUomId;
	}

	public String getSelectedUomName() {
		if (selectedUomName == null) {
			selectedUomName = new MUOM(getCtx(), getSelectedUomId(), null).getTrlName();
		}
		return selectedUomName;
	}

	public BigDecimal getTargetQty() {
		return targetQty;
	}

	public BigDecimal getTargetVolQty() {
		return targetVolQty;
	}

	public int getUomId() {
		return uomId;
	}

	public int getUomVolId() {
		return uomVolId;
	}

	public BigDecimal getUserConfirmQty() {
		BigDecimal cantidadConfirmada;
		if (isVolumeSelected()) {
			cantidadConfirmada = confirmedVolQty;
		} else {
			cantidadConfirmada = confirmedQty;
		}
		return cantidadConfirmada;
	}

	public BigDecimal getUserOriginalQty() {
		BigDecimal cantidad;

		if (isVolumeSelected()) {
			cantidad = originalVolQty;
		} else {
			cantidad = originalQty;
		}

		return cantidad;
	}

	public BigDecimal getUserReturnQty() {
		if (isVolumeSelected()) {
			return returnVolQty;
		} else {
			return returnQty;
		}
	}

	/** Cantidad previamente devuelta */
	public BigDecimal getUserSumReturnQty() {
		if (isVolumeSelected()) {
			if (sumReturnVolQty == null || sumReturnVolQty.compareTo(Env.ZERO) == 0) {
				sumReturnVolQty = MEXMEUOMConversion.convertProductTo(getCtx(), //
						this.getProduct().getM_Product_ID(), //
						this.getProduct().getC_UOMVolume_ID(), //
						MMovementLine.getSumQtyDevol(Env.getCtx(), id), null, true);// Convertir a minima

			}
			return sumReturnVolQty == null ? Env.ZERO : sumReturnVolQty;
		} else {
			if (sumReturnQty == null || sumReturnQty.compareTo(Env.ZERO) == 0) {
				sumReturnQty = MMovementLine.getSumQtyDevol(Env.getCtx(), id);
			}
			return sumReturnQty == null ? Env.ZERO : sumReturnQty;
		}
	}

	public BigDecimal getUserTargetQty() {
		BigDecimal cantidadSurtida;
		if (isVolumeSelected()) {
			cantidadSurtida = targetVolQty;
		} else {
			cantidadSurtida = targetQty;
		}
		return cantidadSurtida;
	}

	public boolean isVolumeSelected() {
		boolean retValue = false;

		if (product.getC_UOM_ID() != product.getC_UOMVolume_ID() && product.getC_UOMVolume_ID() == getSelectedUomId()) {
			retValue = true;
		}

		return retValue;
	}

	/** Cantidad a considerar para generar el cargo a cuenta paciente */
	public void setChargeQty(BigDecimal chargeQty) {
		this.chargeQty = chargeQty;
	}

	/** Cantidad a considerar para generar el cargo a cuenta paciente */
	public void setChargeVolQty(BigDecimal chargeVolQty) {
		this.chargeVolQty = chargeVolQty;
	}

	public void setConfirmedQty(final BigDecimal confirmedQty) {
		this.confirmedQty = confirmedQty;
	}

	public void setConfirmedVolQty(final BigDecimal confirmedVolQty) {
		this.confirmedVolQty = confirmedVolQty;
	}

	public void setConfirmPanel(POConfirmPanel confirmPanel) {
		this.confirmPanel = confirmPanel;
	}

	public void setCtaPac(final MEXMECtaPac ctaPac) {
		this.ctaPac = ctaPac;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setLocatorFromId(final int locatorFromId) {
		this.locatorFromId = locatorFromId;
	}

	public void setLocatorToId(final int locatorToId) {
		this.locatorToId = locatorToId;
	}

	public void setLotId(final int lotId) {
		this.lotId = lotId;
	}

	public void setMovementId(final int movementId) {
		this.movementId = movementId;
	}

	public void setOriginalQty(final BigDecimal originalQty) {
		this.originalQty = originalQty;
	}

	public void setOriginalVolQty(final BigDecimal originalVolQty) {
		this.originalVolQty = originalVolQty;
	}

	public void setProduct(final MProduct product) {
		this.product = product;
	}

	public void setReturnQty(BigDecimal returnQty) {
		this.returnQty = returnQty;
	}

	public void setReturnVolQty(BigDecimal returnVolQty) {
		this.returnVolQty = returnVolQty;
	}

	/** Cantidad surtida menos cantidad confirmada por el usuario: M_MovementLine.ScrappedQty */
	public void setScrappedQty(BigDecimal scrappedQty) {
		this.scrappedQty = scrappedQty;
	}

	/** Cantidad surtida menos cantidad confirmada por el usuario: M_MovementLine.ScrappedQty_Vol */
	public void setScrappedVolQty(BigDecimal scrappedVolQty) {
		this.scrappedVolQty = scrappedVolQty;
	}

	public void setSelectedUomId(final int selectedUomId) {
		this.selectedUomId = selectedUomId;
	}

	public void setTargetQty(final BigDecimal targetQty) {
		this.targetQty = targetQty;
	}

	public void setTargetVolQty(final BigDecimal targetVolQty) {
		this.targetVolQty = targetVolQty;
	}

	public void setUomId(final int uomId) {
		this.uomId = uomId;
	}

	public void setUomVolId(final int uomVolId) {
		this.uomVolId = uomVolId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(I_M_MovementLine.COLUMNNAME_M_Product_ID).append(product.getM_Product_ID()).append(I_M_MovementLine.COLUMNNAME_M_LocatorTo_ID).append(locatorToId).append(I_M_MovementLine.COLUMNNAME_M_Locator_ID).append(locatorFromId).append(I_M_MovementLine.COLUMNNAME_M_AttributeSetInstance_ID).append(lotId).append(I_M_MovementLine.COLUMNNAME_C_UOM_ID)
				.append(uomId).append(I_M_MovementLine.COLUMNNAME_C_UOMVolume_ID).append(uomVolId).append(I_M_MovementLine.COLUMNNAME_M_MovementLine_ID).append(id).append(I_M_MovementLine.COLUMNNAME_M_Movement_ID).append(movementId);
		return sb.toString();
	}

	/**
	 * Valida las cantidades de las lineas a confirmar
	 * 
	 * @return Listado de errores
	 */
	public ErrorList validateConfirm(BigDecimal confirmedQty) {
		final ErrorList errorList = new ErrorList();

		BigDecimal qtyTarget = null;
		if (isVolumeSelected()) {
			confirmedQty = confirmedQty.setScale(0, BigDecimal.ROUND_FLOOR);

			setConfirmedVolQty(confirmedQty);
			setConfirmedQty(MEXMEUOMConversion.convertProductFrom(getCtx(), //
					getProduct().getM_Product_ID(), //
					getProduct().getC_UOMVolume_ID(), //
					confirmedQty, null, true));

			qtyTarget = getTargetVolQty();
		} else {
			setConfirmedQty(confirmedQty);
			setConfirmedVolQty(BigDecimal.ZERO);
			qtyTarget = getTargetQty();
		}

		// validamos q la cant. confirmada no sea null
		if (confirmedQty == null) {
			errorList.add(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "msj.cantInvalidaParam", getProduct().getName()));
		} else if (confirmedQty.compareTo(BigDecimal.ZERO) < 0) { // linea.getConfirmedQty()<0
			// validamos q la cant. confirmada no sea negativa
			errorList.add(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.confCero", getProduct().getName()));
		} else if (confirmedQty.compareTo(qtyTarget) > 0) {
			// validamos q la cant. confirmada sea menor o igual a la surtida(target)
			errorList.add(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.traspasos.confSurt", getProduct().getName()));
		} else if (qtyTarget.compareTo(BigDecimal.ZERO) > 0 && MMovementLineConfirm.getFromMovLineId(getCtx(), getId(), null) <= 0) {
			// validamos que exista una linea de confirmacion relacionada
			String docNo = DB.getSQLValueString(null, "select documentno from m_movement where m_movement_id = ? ", getMovementId());	
			errorList.add(new Error(Error.EXCEPTION_ERROR, getAppMsg(getCtx(), "error.traspasos.noMovementConfirm", docNo)));
		}
		return errorList;
	}

	public ErrorList validateDelivery(final BigDecimal targetQty) {
		final ErrorList errorList = new ErrorList();

		final MMovement movement = new MMovement(getCtx(), movementId, null);

		if (isVolumeSelected()) {
			final BigDecimal qtyVol = targetQty.setScale(0, BigDecimal.ROUND_FLOOR);

			setTargetVolQty(qtyVol);

			final BigDecimal qty = MEXMEUOMConversion.convertProductFrom(getCtx(), getProduct().getM_Product_ID(), getProduct().getC_UOMVolume_ID(), qtyVol, null, true);

			setTargetQty(qty);
		} else {
			setTargetQty(targetQty);
			setTargetVolQty(BigDecimal.ZERO);
		}

		if (X_M_Movement.DOCSTATUS_InProgress.equalsIgnoreCase(movement.getDocStatus())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "error.traspasos.isSurtido2", movement.getDocumentNo()));
		} else {
			if (errorList.isEmpty()) {
				if (isVolumeSelected()) {
					if (getTargetVolQty().compareTo(getOriginalVolQty()) == 1) {
						errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "error.traspasos.surtSolic", "(" + getTargetVolQty() + ")"));
					}
				} else {
					if (getTargetQty().compareTo(getOriginalQty()) == 1) {
						errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "error.traspasos.surtSolic", "(" + getTargetQty() + ")"));
					}
				}

				if (errorList.isEmpty()) {
					// TODO revisar si maneja o no stock
				}
			}
		}
		return errorList;
	}

	public ErrorList validateRequest() {
		final ErrorList errorList = new ErrorList();

		BigDecimal qty = null;

		if (isVolumeSelected()) {
			qty = getOriginalVolQty();
		} else {
			qty = getOriginalQty();
		}

		if (qty == null || BigDecimal.ZERO.compareTo(qty) == 0) {
			errorList.add(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "msj.cantInvalidaParam", getProduct().getName()));
		}

		if (getCtaPac() != null) {
			final MPrecios precios = getPrice();

			if (!MEXMEConfigPre.aplicarCargoSinPrecio(precios.getPriceList())) {
				errorList.add(Error.CONFIGURATION_ERROR, getAppMsg(getCtx(), "error.servicios.noPrice", getProduct().getName()));
			}

			if (!(getProduct().getProductClass().equals(X_M_Product.PRODUCTCLASS_Drug) || getProduct().getProductClass().equals(X_M_Product.PRODUCTCLASS_MedicalSupplies))) {
				errorList.add(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.servicios.onlyDrugs", getProduct().getName()));
			}
		}

		return errorList;
	}

	/**
	 * Valida las cantidades de las lineas a confirmar
	 * 
	 * @return Listado de errores
	 */
	public ErrorList validateReturn(final boolean isNew, BigDecimal qty, final boolean validateStock) {
		final ErrorList errorList = new ErrorList();
		BigDecimal qtyDevol;

		// validacion de conversiones de UDM
		if (this.getProduct().getC_UOM_ID() != this.getProduct().getC_UOMVolume_ID()) {
			final MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), getProduct().getM_Product_ID(), getProduct().getC_UOMVolume_ID(), null);
			if (rates == null) {
				errorList.add(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.udm.noExisteConversion", getProduct().getName()));
			}

		}

		if (isNew) {
			if (this.isVolumeSelected() && errorList.isEmpty() && qty.compareTo(BigDecimal.ZERO) > 0) {
				qtyDevol = MEXMEUOMConversion.convertProductFrom(getCtx(), //
						this.getProduct().getM_Product_ID(), //
						this.getProduct().getC_UOMVolume_ID(), //
						qty, null, true);// Convertir a minima
			} else {
				qtyDevol = qty.setScale(0, BigDecimal.ROUND_FLOOR);
			}
		} else {
			qtyDevol = this.getReturnQty();
		}

		// Validamos que la cantidad no sea negativa
		if (qtyDevol == null || qtyDevol.compareTo(BigDecimal.ZERO) < 0) {
			errorList.add(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "msj.error.cantDevolverNeg"));

		} else {
			// min previamente devuelta
			BigDecimal sumqty = MMovementLine.getSumQtyDevol(Env.getCtx(), id);
			sumqty = qtyDevol.add(sumqty == null ? Env.ZERO : sumqty);

			BigDecimal qtyConf = this.getConfirmedQty().add(this.getScrappedQty());

			if (qtyConf.compareTo(sumqty) < 0) {
				// validamos q la cant. confirmada sea menor o igual a la surtida(target)
				errorList.add(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.encCtaPac.devolInsuficiente", this.getProduct().getName()));

			} else {

				// Validar existencias
				if (qtyDevol.abs().compareTo(Env.ZERO) > 0 && MEXMEMejoras.inventories(getCtx()) && MWarehouse.getFromLocator(getCtx(), this.getLocatorToId(), null).isControlExistencias()) {
					if (validateStock && !MStorage.canSupply(Env.getCtx(), this.getProduct().getM_Product_ID(), this.getLocatorToId()// Almacen que surte que podria ser piso de enfermeras por ejemplo por que es devolucion
							, this.getLotId(), qtyDevol.abs(), null)) {
						errorList.add(Error.VALIDATION_ERROR, getAppMsg(getCtx(), "error.encCtaPac.noExistenProd", this.getProduct().getName()));
					}
				}
			}
		}

		return errorList;
	}
}