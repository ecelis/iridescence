/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

/**
 * Modelo para mostrar los datos del detalle de indicaciones
 * <p>
 * Modificado por:  $Author: gisela $<p>
 * Fecha:  $Date: 2006/08/25 16:05:41 $<p>
 *
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.1 $
 */
public class ActPacienteInd {
    /**
     * xXActPacienteIndID property.
     */
    protected long xXActPacienteIndID = 0;

    /**
     * Get xXActPacienteIndID property.
     * 
     *@return xXActPacienteIndID property.
     */
    public long getXXActPacienteIndID() {
        return this.xXActPacienteIndID;
    }

    /**
     * Set xXActPacienteIndID property.
     * 
     *@param xXActPacienteIndID New xXActPacienteIndID property.
     */
    public void setXXActPacienteIndID(long xXActPacienteIndID) {
        this.xXActPacienteIndID = xXActPacienteIndID;
    }

    /**
     * ADClientID property.
     */
    protected long aDClientID = 0;

    /**
     * Get aDClientID property.
     * 
     *@return ADClientID property.
     */
    public long getADClientID() {
        return this.aDClientID;
    }

    /**
     * Set aDClientID property.
     * 
     *@param aDClientID New aDClientID property.
     */
    public void setADClientID( long aDClientID) {
        this.aDClientID = aDClientID;
    }

    /**
     * ADOrgID property.
     */
    protected long aDOrgID = 0;

    /**
     * Get aDOrgID property.
     * 
     *@return ADOrgID property.
     */
    public long getADOrgID() {
        return this.aDOrgID;
    }

    /**
     * Set aDOrgID property.
     * 
     *@param aDOrgID New aDOrgID property.
     */
    public void setADOrgID( long aDOrgID) {
        this.aDOrgID = aDOrgID;
    }

    /**
     * IsActive property.
     */
    protected String isActive = null;

    /**
     * Get isActive property.
     * 
     *@return IsActive property.
     */
    public String getIsActive() {
        return this.isActive;
    }

    /**
     * Set isActive property.
     * 
     *@param isActive New isActive property.
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    /**
     * Created property.
     */
    protected java.sql.Timestamp created = null;

    /**
     * Get created property.
     * 
     *@return Created property.
     */
    public java.sql.Timestamp getCreated() {
        return this.created;
    }

    /**
     * Set created property.
     * 
     *@param created New created property.
     */
    public void setCreated(java.sql.Timestamp created) {
        this.created = created;
    }

    /**
     * CreatedBy property.
     */
    protected long createdBy = 0;

    /**
     * Get createdBy property.
     * 
     *@return CreatedBy property.
     */
    public long getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Set createdBy property.
     * 
     *@param createdBy New createdBy property.
     */
    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Updated property.
     */
    protected java.sql.Timestamp updated = null;

    /**
     * Get Updated property.
     * 
     *@return Updated property.
     */
    public java.sql.Timestamp getUpdated() {
        return this.updated;
    }

    /**
     * Set Updated property.
     * 
     *@param Updated New Updated property.
     */
    public void setUpDated(java.sql.Timestamp updated) {
        this.updated = updated;
    }

    /**
     * UpdatedBy property.
     */
    protected long updatedBy = 0;

    /**
     * Get UpdatedBy property.
     * 
     *@return UpdatedBy property.
     */
    public long getUpdatedBy() {
        return this.updatedBy;
    }

    /**
     * Set UpdatedBy property.
     * 
     *@param UpdatedBy New updatedBy property.
     */
    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Propiedad Line.
     */
    protected long line = 0;

    /**
     * Obtener la propiedad line.
     * 
     *@return La propiedad Line.
     */
    public long getLine() {
        return this.line;
    }

    /**
     * Establecer la propiedad line.
     * 
     *@param line Nueva propiedad line.
     */
    public void setLine(long line) {
        this.line = line;
    }

    /**
     * DateOrdered property.
     */
    protected java.sql.Date dateOrdered = null;

    /**
     * Get DateOrdered property.
     * 
     *@return DateOrdered property.
     */
    public java.sql.Date getDateOrdered() {
        return this.dateOrdered;
    }

    /**
     * Set DateOrdered property.
     * 
     *@param DateOrdered New DateOrdered property.
     */
    public void setDateOrdered(java.sql.Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    /**
     * DatePromised property.
     */
    protected java.sql.Date datePromised = null;

    /**
     * Get DatePromised property.
     * 
     *@return DatePromised property.
     */
    public java.sql.Date getDatePromised() {
        return this.datePromised;
    }

    /**
     * Set DatePromised property.
     * 
     *@param DatePromised New DatePromised property.
     */
    public void setDatePromised(java.sql.Date datePromised) {
        this.datePromised = datePromised;
    }

    /**
     * DateDelivered property.@deprecated
     */
    protected java.sql.Date dateDelivered = null;

    /**
     * Get DateDelivered property.
     * @deprecated
     *@return DateDelivered property.
     */
    public java.sql.Date getDateDelivered() {
        return this.dateDelivered;
    }

    /**
     * Set DateDelivered property.
     * @deprecated
     *@param DateDelivered New DateDelivered property.
     */
    public void setDateDelivered(java.sql.Date dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    /**
     * DateInvoiced property.
     */
    protected java.sql.Date dateInvoiced = null;

    /**
     * Get DateInvoiced property.
     * 
     *@return DateInvoiced property.
     */
    public java.sql.Date getDateInvoiced() {
        return this.dateInvoiced;
    }

    /**
     * Set DateInvoiced property.
     * 
     *@param DateInvoiced New DateInvoiced property.
     */
    public void setDateInvoiced(java.sql.Date dateInvoiced) {
        this.dateInvoiced = dateInvoiced;
    }

    /**
     * Description property.
     */
    protected String description = null;

    /**
     * Get description property.
     * 
     *@return Description property.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set description property.
     * 
     *@param description New description property.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Propiedad MProductID.
     */
    protected long mProductID = 0;

    /**
     * Obtener la propiedad mProductID.
     * 
     *@return La propiedad MProductID.	  	  @deprecated
     */
    public long getMProductID() {
        return this.mProductID;
    }

    /**
     * Establecer la propiedad mProductID.
     * @deprecated
     *@param mProductID Nueva propiedad mProductID
     */
    public void setMProductID(long mProductID) {
        this.mProductID = mProductID;
    }

    /**
     * Propiedad MWarehouseID.
     */
    protected long mWarehouseID = 0;

    /**
     * Obtener la propiedad mWarehouseID.
     * 
     *@return La propiedad MWarehouseID.
     */
    public long getMWarehouseID() {
        return this.mWarehouseID;
    }

    /**
     * Establecer la propiedad mWarehouseID.
     * 
     *@param mWarehouseID Nueva propiedad mWarehouseID.
     */
    public void setMWarehouseID(long mWarehouseID) {
        this.mWarehouseID = mWarehouseID;
    }

    /**
     * Propiedad CUomID.
     */
    protected long cUomID = 0;

    /**
     * Obtener la propiedad cUomID.
     * 
     *@return La propiedad CUomID.
     */
    public long getCUomID() {
        return this.cUomID;
    }

    /**
     * Establecer la propiedad cUomID.
     * 
     *@param cUomID Nueva propiedad cUomID.
     */
    public void setCUomID(long cUomID) {
        this.cUomID = cUomID;
    }

    /**
     * Propiedad QtyOrdered.
     */
    protected int qtyOrdered = 0;

    /**
     * Obtener la propiedad qtyOrdered.
     * 
     *@return La propiedad QtyOrdered.
     */
    public int getQtyOrdered() {
        return this.qtyOrdered;
    }

    /**
     * Establecer la propiedad qtyOrdered.
     * 
     *@param qtyOrdered Nueva propiedad qtyOrdered.
     */
    public void setQtyOrdered(int qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    /**
     * Propiedad QtyReserved.
     */
    protected int qtyReserved = 0;

    /**
     * Obtener la propiedad qtyReserved.
     * 
     *@return La propiedad QtyReserved.
     */
    public int getQtyReserved() {
        return this.qtyReserved;
    }

    /**
     * Establecer la propiedad qtyReserved.
     * 
     *@param qtyReserved Nueva propiedad qtyReserved.
     */
    public void setQtyReserved(int qtyReserved) {
        this.qtyReserved = qtyReserved;
    }

    /**
     * Propiedad QtyDelivered.
     */
    protected int qtyDelivered = 0;

    /**
     * Obtener la propiedad qtyDelivered.
     * 
     *@return La propiedad QtyDelivered.
     */
    public int getQtyDelivered() {
        return this.qtyDelivered;
    }

    /**
     * Establecer la propiedad qtyDelivered.
     * 
     *@param qtyDelivered Nueva propiedad qtyDelivered.
     */
    public void setQtyDelivered(int qtyDelivered) {
        this.qtyDelivered = qtyDelivered;
    }

    /**
     * Propiedad QtyInvoiced.
     */
    protected int qtyInvoiced = 0;

    /**
     * Obtener la propiedad qtyInvoiced.
     * 
     *@return La propiedad QtyInvoiced.
     */
    public int getQtyInvoiced() {
        return this.qtyInvoiced;
    }

    /**
     * Establecer la propiedad qtyInvoiced.
     * 
     *@param qtyInvoiced Nueva propiedad qtyInvoiced.
     */
    public void setQtyInvoiced(int qtyInvoiced) {
        this.qtyInvoiced = qtyInvoiced;
    }

    /**
     * Propiedad MShipperId.
     */
    protected long mShipperId = 0;

    /**
     * Obtener la propiedad mShipperId.
     * 
     *@return La propiedad MShipperId.
     */
    public long getMShipperId() {
        return this.mShipperId;
    }

    /**
     * Establecer la propiedad mShipperId.
     * 
     *@param mShipperId Nueva propiedad mShipperId.
     */
    public void setMShipperId(long mShipperId) {
        this.mShipperId = mShipperId;
    }

    /**
     * CCurrencyID property.
     */
    protected long cCurrencyID = 0;

    /**
     * Get cCurrencyID property.
     * 
     *@return CCurrencyID property.
     */
    public long getCCurrencyID() {
        return this.cCurrencyID;
    }

    /**
     * Set cCurrencyID property.
     * 
     *@param cCurrencyID New cCurrencyID property.
     */
    public void setCCurrencyID(long cCurrencyID) {
        this.cCurrencyID = cCurrencyID;
    }

    /**
     * Propiedad Costo.
     */
    protected double costo = 0.0;

    /**
     * Obtener la propiedad costo.
     * 
     *@return La propiedad Costo.
     */
    public double getCosto() {
        return this.costo;
    }

    /**
     * Establecer la propiedad costo.
     * 
     *@param costo Nueva propiedad costo.
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * Propiedad PriceList.
     */
    protected double priceList = 0.0;

    /**
     * Obtener la propiedad priceList.
     * 
     *@return La propiedad PriceList.
     */
    public double getPriceList() {
        return this.priceList;
    }

    /**
     * Establecer la propiedad priceList.
     * 
     *@param priceList Nueva propiedad priceList.
     */
    public void setPriceList(double priceList) {
        this.priceList = priceList;
    }

    /**
     * Propiedad PriceActual.
     */
    protected double priceActual = 0.0;

    /**
     * Obtener la propiedad priceActual.
     * 
     *@return La propiedad PriceActual.
     */
    public double getPriceActual() {
        return this.priceActual;
    }

    /**
     * Establecer la propiedad priceActual.
     * 
     *@param priceActual Nueva propiedad priceActual.
     */
    public void setPriceActual(double priceActual) {
        this.priceActual = priceActual;
    }

    /**
     * Propiedad PriceLimit.
     */
    protected double priceLimit = 0.0;

    /**
     * Obtener la propiedad priceLimit.
     * 
     *@return La propiedad PriceLimit.
     */
    public double getPriceLimit() {
        return this.priceLimit;
    }

    /**
     * Establecer la propiedad priceLimit.
     * 
     *@param priceLimit Nueva propiedad priceLimit.
     */
    public void setPriceLimit(double priceLimit) {
        this.priceLimit = priceLimit;
    }

    /**
     * Propiedad LineNetAmt.
     */
    protected double LineNetAmt = 0.0;

    /**
     * Obtener la propiedad LineNetAmt.
     * 
     *@return La propiedad LineNetAmt.
     */
    public double getLineNetAmt() {
        return this.LineNetAmt;
    }

    /**
     * Establecer la propiedad LineNetAmt.
     * 
     *@param LineNetAmt Nueva propiedad LineNetAmt.
     */
    public void setLineNetAmt(double LineNetAmt) {
        this.LineNetAmt = LineNetAmt;
    }

    /**
     * Propiedad Discount.
     */
    protected double discount = 0.0;

    /**
     * Obtener la propiedad discount.
     * 
     *@return La propiedad Discount.
     */
    public double getDiscount() {
        return this.discount;
    }

    /**
     * Establecer la propiedad discount.
     * 
     *@param discount Nueva propiedad discount.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Propiedad FreightAmt.
     */
    protected double freightAmt = 0.0;

    /**
     * Obtener la propiedad freightAmt.
     * 
     *@return La propiedad FreightAmt.
     */
    public double getFreightAmt() {
        return this.freightAmt;
    }

    /**
     * Establecer la propiedad freightAmt.
     * 
     *@param freightAmt Nueva propiedad freightAmt.
     */
    public void setFreightAmt(double freightAmt) {
        this.freightAmt = freightAmt;
    }

    /**
     * Propiedad CChargeId.
     */
    protected long cChargeId = 0;

    /**
     * Obtener la propiedad cChargeId.
     * 
     *@return La propiedad CChargeId.
     */
    public long getCChargeId() {
        return this.cChargeId;
    }

    /**
     * Establecer la propiedad cChargeId.
     * 
     *@param cChargeId Nueva propiedad cChargeId.
     */
    public void setCChargeId(long cChargeId) {
        this.cChargeId = cChargeId;
    }

    /**
     * Propiedad ChargeAmt.
     */
    protected double chargeAmt = 0.0;

    /**
     * Obtener la propiedad chargeAmt.
     * 
     *@return La propiedad ChargeAmt.
     */
    public double getChargeAmt() {
        return this.chargeAmt;
    }

    /**
     * Establecer la propiedad chargeAmt.
     * 
     *@param chargeAmt Nueva propiedad chargeAmt.
     */
    public void setChargeAmt(double chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    /**
     * Propiedad CTaxID.
     */
    protected long cTaxID = 0;

    /**
     * Obtener la propiedad cTaxID.
     * 
     *@return La propiedad CTaxID.
     */
    public long getCTaxID() {
        return this.cTaxID;
    }

    /**
     * Establecer la propiedad cTaxID.
     * 
     *@param cTaxID Nueva propiedad cTaxID.
     */
    public void setCTaxID(long cTaxID) {
        this.cTaxID = cTaxID;
    }

    /**
     * Propiedad IsDescription.
     */
    protected String isDescription = null;

    /**
     * Obtener la propiedad isDescription.
     * 
     *@return La propiedad IsDescription.
     */
    public String getIsDescription() {
        return this.isDescription;
    }

    /**
     * Establecer la propiedad isDescription.
     * 
     *@param isDescription Nueva propiedad isDescription.
     */
    public void setIsDescription(String isDescription) {
        this.isDescription = isDescription;
    }

    /**
     * Propiedad MInOutLineID.
     */
    protected long mInOutLineID = 0;

    /**
     * Obtener la propiedad mInOutLineID.
     * 
     *@return La propiedad MInOutLineID.
     */
    public long getMInOutLineID() {
        return this.mInOutLineID;
    }

    /**
     * Establecer la propiedad mInOutLineID.
     * 
     *@param mInOutLineID Nueva propiedad mInOutLineID.
     */
    public void setMInOutLineID(long mInOutLineID) {
        this.mInOutLineID = mInOutLineID;
    }

    /**
     * Propiedad ADOrgTrxID.
     */
    protected long aDOrgTrxID = 0;

    /**
     * Obtener la propiedad aDOrgTrxID.
     * 
     *@return La propiedad ADOrgTrxID.
     */
    public long getADOrgTrxID() {
        return this.aDOrgTrxID;
    }

    /**
     * Establecer la propiedad aDDrgTrxID.
     * 
     *@param aDOrgTrxID Nueva propiedad aDOrgTrxID.
     */
    public void setADOrgTrxID(long aDOrgTrxID) {
        this.aDOrgTrxID = aDOrgTrxID;
    }

    /**
     * Propiedad RefActPacienteIndID.
     */
    protected long refActPacienteIndID = 0;

    /**
     * Obtener la propiedad refActPacienteIndID.
     * 
     *@return La propiedad RefActPacienteIndID.
     */
    public long getRefActPacienteIndID() {
        return this.refActPacienteIndID;
    }

    /**
     * Establecer la propiedad refActPacienteIndID.
     * 
     *@param refActPacienteIndID Nueva propiedad refActPacienteIndID.
     */
    public void setRefActPacienteIndID(long refActPacienteIndID) {
        this.refActPacienteIndID = refActPacienteIndID;
    }

    /**
     * xXActPacienteIndHID property.
     */
    protected long xXActPacienteIndHID = 0;

    /**
     * Get xXActPacienteIndHID property.
     * 
     *@return xXActPacienteIndHID property.
     */
    public long getXXActPacienteIndHID() {
        return this.xXActPacienteIndHID;
    }

    /**
     * Set xXActPacienteIndHID property.
     * 
     *@param xXActPacienteIndHID New xXActPacienteIndHID property.
     */
    public void setXXActPacienteIndHID(long xXActPacienteIndHID) {
        this.xXActPacienteIndHID = xXActPacienteIndHID;
    }
    
    /**
     * Determinamos si se surte o no de farmacia
     */
    private boolean surtir;
    
    /**
     * @return Returns the surtir.
     */
    public boolean isSurtir() {
        return surtir;
    }
    /**
     * @param surtir The surtir to set.
     */
    public void setSurtir(boolean surtir) {
        this.surtir = surtir;
    }
    
    //diagnosticos presuntivos para los servicios
    private String codOMS1;
    private String codOMS2;
    private String codOMS3;

    public String getCodOMS1() {
        return codOMS1;
    }

    public void setCodOMS1(String diag1) {
        this.codOMS1 = diag1;
    }

    public String getCodOMS2() {
        return codOMS2;
    }

    public void setCodOMS2(String diag2) {
        this.codOMS2 = diag2;
    }

    public String getCodOMS3() {
        return codOMS3;
    }

    public void setCodOMS3(String diag3) {
        this.codOMS3 = diag3;
    }
}