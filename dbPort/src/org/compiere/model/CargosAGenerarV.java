package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.util.Env;

/**
 * Bean de la vista de EXME_CargosAGenerar_V para obtener los productos que
 * aun no han sido procesados
 * 
 * @author Expert
 * 
 */
public class CargosAGenerarV {
	public CargosAGenerarV(int EXME_ActPacienteInd_ID, int M_Warehouse_ID,
			int EXME_Area_ID, int C_Tax_ID, int C_UOM_ID, int M_Product_ID,
			int EXME_CtaPac_ID, int EXME_ActPacienteIndH_ID,
			int C_Currency_ID, int EXME_CtaPacExt_ID,
			BigDecimal QtyOrdered, BigDecimal QtyDelivered,
			BigDecimal Costo, BigDecimal PriceActual,
			Timestamp DateOrdered, String TipoArea, int C_TaxCategory_ID,
			String Documentno, String Nombre_pac) {

		this.EXME_ActPacienteInd_ID = EXME_ActPacienteInd_ID;
		this.M_Warehouse_ID = M_Warehouse_ID;
		this.EXME_Area_ID = EXME_Area_ID;
		this.C_Tax_ID = C_Tax_ID;
		this.C_UOM_ID = C_UOM_ID;
		this.M_Product_ID = M_Product_ID;
		this.EXME_CtaPac_ID = EXME_CtaPac_ID;
		this.EXME_ActPacienteIndH_ID = EXME_ActPacienteIndH_ID;
		this.C_Currency_ID = C_Currency_ID;
		this.EXME_CtaPacExt_ID = EXME_CtaPacExt_ID;
		this.QtyOrdered = QtyOrdered;
		this.QtyDelivered = QtyDelivered;
		this.Costo = Costo;
		this.PriceActual = PriceActual;
		this.DateOrdered = DateOrdered;
		this.TipoArea = TipoArea;
		this.C_TaxCategory_ID = C_TaxCategory_ID;
		this.Documentno = Documentno;
		this.Nombre_pac = Nombre_pac;

	}

	private int EXME_ActPacienteInd_ID = 0;
	private int M_Warehouse_ID = 0;
	private int EXME_Area_ID = 0;
	private int C_Tax_ID = 0;
	private int C_UOM_ID = 0;
	private int M_Product_ID = 0;
	private int EXME_CtaPac_ID = 0;
	private int EXME_ActPacienteIndH_ID = 0;
	private int C_Currency_ID = 0;
	private int EXME_CtaPacExt_ID = 0;
	private BigDecimal QtyOrdered = Env.ZERO;
	private BigDecimal QtyDelivered = Env.ZERO;
	private BigDecimal Costo = Env.ZERO;
	private BigDecimal PriceActual = Env.ZERO;
	private Timestamp DateOrdered = null;
	private String TipoArea = null;
	private int C_TaxCategory_ID = 0;
	private String Documentno = null;
	private String Nombre_pac = null;

	public int getC_TaxCategory_ID() {
		return C_TaxCategory_ID;
	}

	public void setC_TaxCategory_ID(int cTaxCategoryID) {
		C_TaxCategory_ID = cTaxCategoryID;
	}

	public String getDocumentno() {
		return Documentno;
	}

	public void setDocumentno(String documentno) {
		Documentno = documentno;
	}

	public String getNombre_pac() {
		return Nombre_pac;
	}

	public void setNombre_pac(String nombrePac) {
		Nombre_pac = nombrePac;
	}

	public int getEXME_CtaPacExt_ID() {
		return EXME_CtaPacExt_ID;
	}

	public void setEXME_CtaPacExt_ID(int eXMECtaPacExtID) {
		EXME_CtaPacExt_ID = eXMECtaPacExtID;
	}

	public String getTipoArea() {
		return TipoArea;
	}

	public void setTipoArea(String tipoArea) {
		TipoArea = tipoArea;
	}

	public int getEXME_ActPacienteInd_ID() {
		return EXME_ActPacienteInd_ID;
	}

	public void setEXME_ActPacienteInd_ID(int eXMEActPacienteIndID) {
		EXME_ActPacienteInd_ID = eXMEActPacienteIndID;
	}

	public int getM_Warehouse_ID() {
		return M_Warehouse_ID;
	}

	public void setM_Warehouse_ID(int mWarehouseID) {
		M_Warehouse_ID = mWarehouseID;
	}

	public int getEXME_Area_ID() {
		return EXME_Area_ID;
	}

	public void setEXME_Area_ID(int eXMEAreaID) {
		EXME_Area_ID = eXMEAreaID;
	}

	public int getC_Tax_ID() {
		return C_Tax_ID;
	}

	public void setC_Tax_ID(int cTaxID) {
		C_Tax_ID = cTaxID;
	}

	public int getC_UOM_ID() {
		return C_UOM_ID;
	}

	public void setC_UOM_ID(int cUOMID) {
		C_UOM_ID = cUOMID;
	}

	public int getM_Product_ID() {
		return M_Product_ID;
	}

	public void setM_Product_ID(int mProductID) {
		M_Product_ID = mProductID;
	}

	public int getEXME_CtaPac_ID() {
		return EXME_CtaPac_ID;
	}

	public void setEXME_CtaPac_ID(int eXMECtaPacID) {
		EXME_CtaPac_ID = eXMECtaPacID;
	}

	public int getEXME_ActPacienteIndH_ID() {
		return EXME_ActPacienteIndH_ID;
	}

	public void setEXME_ActPacienteIndH_ID(int eXMEActPacienteIndHID) {
		EXME_ActPacienteIndH_ID = eXMEActPacienteIndHID;
	}

	public int getC_Currency_ID() {
		return C_Currency_ID;
	}

	public void setC_Currency_ID(int cCurrencyID) {
		C_Currency_ID = cCurrencyID;
	}

	public BigDecimal getQtyOrdered() {
		return QtyOrdered;
	}

	public void setQtyOrdered(BigDecimal qtyOrdered) {
		QtyOrdered = qtyOrdered;
	}

	public BigDecimal getQtyDelivered() {
		return QtyDelivered;
	}

	public void setQtyDelivered(BigDecimal qtyDelivered) {
		QtyDelivered = qtyDelivered;
	}

	public BigDecimal getCosto() {
		return Costo;
	}

	public void setCosto(BigDecimal costo) {
		Costo = costo;
	}

	public BigDecimal getPriceActual() {
		return PriceActual;
	}

	public void setPriceActual(BigDecimal priceActual) {
		PriceActual = priceActual;
	}

	public Timestamp getDateOrdered() {
		return DateOrdered;
	}

	public void setDateOrdered(Timestamp dateOrdered) {
		DateOrdered = dateOrdered;
	}

}
