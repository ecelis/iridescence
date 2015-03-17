/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

/**
 * Modelo para mostrar los datos del indicaciones
 * <p>
 * Modificado por:  $Author: gisela $<p>
 * Fecha:  $Date: 2006/08/25 16:05:41 $<p>
 *
 * @author Twry Tania
 * @version $Revision: 1.1 $
 */
public class ActPacienteIndH {
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
     * XXCtaPacID property.
     */
    protected long xXCtaPacID = 0;

    /**
     * Get xXCtaPacID property.
     * 
     *@return XXCtaPacID property.
     */
    public long getXXCtaPacID() {
        return this.xXCtaPacID;
    }

    /**
     * Set xXCtaPacID property.
     * 
     *@param xXCtaPacID New xXCtaPacID property.
     */
    public void setXXCtaPacID(long xXCtaPacID) {
        this.xXCtaPacID = xXCtaPacID;
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
    protected java.util.Date datePromised = null;

    /**
     * Get DatePromised property.
     * 
     *@return DatePromised property.
     */
    public java.util.Date getDatePromised() {
        return this.datePromised;
    }

    /**
     * Set DatePromised property.
     * 
     *@param DatePromised New DatePromised property.
     */
    public void setDatePromised(java.util.Date datePromised) {
        this.datePromised = datePromised;
    }

    /**
     * DateDelivered property.
     */
    protected java.sql.Date dateDelivered = null;

    /**
     * Get DateDelivered property.
     * 
     *@return DateDelivered property.
     */
    public java.sql.Date getDateDelivered() {
        return this.dateDelivered;
    }

    /**
     * Set DateDelivered property.
     * 
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
     * CChargeID property.
     */
    protected long cChargeID = 0;

    /**
     * Get cChargeID property.
     * 
     *@return CChargeID property.
     */
    public long getCChargeID() {
        return this.cChargeID;
    }

    /**
     * Set cChargeID property.
     * 
     *@param cChargeID New cChargeID property.
     */
    public void setCChargeID(long cChargeID) {
        this.cChargeID = cChargeID;
    }

    /**
     * Propiedad Chargeamt.
     */
    protected double chargeAmt = 0.0;

    /**
     * Devuelve el valor de la propiedad chargeAmt .
     * 
     *@return  El valor actual de ChargeAmt
     */
    public double getChargeAmt() {
        return this.chargeAmt;
    }

    /**
     * Establece el nuevo valor para la propiedad chargeAmt .
     * 
     *@param chargeAmt El nuevo valor para chargeAmt .
     */
    public void setChargeAmt(double chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    /**
     * Propiedad aDOrgTrxID.
     */
    protected long aDOrgTrxID = 0;

    /**
     * Devuelve el valor de la propiedad aDOrgTrxID .
     * 
     *@return  El valor actual de aDOrgTrxID
     */
    public long getADOrgTrxID() {
        return this.aDOrgTrxID;
    }

    /**
     * Establece el nuevo valor para la propiedad aDOrgTrxID .
     * 
     *@param aDOrgTrxID El nuevo valor para aDOrgTrxID .
     */
    public void setADOrgTrxID(long aDOrgTrxID) {
        this.aDOrgTrxID = aDOrgTrxID;
    }

    /**
     * Propiedad RefActPacienteIndHID.
     */
    protected long refActPacienteIndHID = 0;

    /**
     * Devuelve el valor de la propiedad refActPacienteIndHID .
     * 
     *@return  El valor actual de RefActPacienteIndHID
     */
    public long getRefActPacienteIndHID() {
        return this.refActPacienteIndHID;
    }

    /**
     * Establece el nuevo valor para la propiedad refActPacienteIndHID .
     * 
     *@param refActPacienteIndHID El nuevo valor para refActPacienteIndHID .
     */
    public void setRefActPacienteIndHID(long refActPacienteIndHID) {
        this.refActPacienteIndHID = refActPacienteIndHID;
    }

    /**
     * Propiedad CBPartnerID.
     */
    protected long cBPartnerID = 0;

    /**
     * Devuelve el valor de la propiedad cBPartnerID .
     * 
     *@return  El valor actual de CBPartnerID
     */
    public long getCBPartnerID() {
        return this.cBPartnerID;
    }

    /**
     * Establece el nuevo valor para la propiedad cBPartnerID .
     * 
     *@param cBPartnerID El nuevo valor para cBPartnerID .
     */
    public void setCBPartnerID(long cBPartnerID) {
        this.cBPartnerID = cBPartnerID;
    }

    /**
     * Propiedad cLocationID.
     */
    protected long cLocationID = 0;

    /**
     * Devuelve el valor de la propiedad cLocationID .
     * 
     *@return  El valor actual de CLocationID
     */
    public long getCLocationID() {
        return this.cLocationID;
    }

    /**
     * Establece el nuevo valor para la propiedad cLocationID .
     * 
     *@param cLocationID El nuevo valor para cLocationID .
     */
    public void setCLocationID(long cLocationID) {
        this.cLocationID = cLocationID;
    }

    /**
     * Propiedad MPriceListID.
     */
    protected long mPriceListID = 0;

    /**
     * Devuelve el valor de la propiedad mPriceListID .
     * 
     *@return  El valor actual de MPriceListID
     */
    public long getMPriceListID() {
        return this.mPriceListID;
    }

    /**
     * Establece el nuevo valor para la propiedad mPriceListID .
     * 
     *@param mPriceListID El nuevo valor para mPriceListID .
     */
    public void setMPriceListID(long mPriceListID) {
        this.mPriceListID = mPriceListID;
    }

    /**
     * Propiedad isInvoiced.
     */
    protected String isInvoiced = null;

    /**
     * Devuelve el valor de la propiedad isInvoiced .
     * 
     *@return  El valor actual de isInvoiced
     */
    public String getIsInvoiced() {
        return this.isInvoiced;
    }

    /**
     * Establece el nuevo valor para la propiedad isInvoiced .
     * 
     *@param isInvoinced El nuevo valor para isInvoiced .
     */
    public void setIsInvoiced(String isInvoiced) {
        this.isInvoiced = isInvoiced;
    }

    /**
     * Propiedad DocumentNo.
     */
    protected String documentNo = null;

    /**
     * Devuelve el valor de la propiedad DocumentNo .
     * 
     *@return  El valor actual de DocumentNo
     */
    public String getDocumentNo() {
        return this.documentNo;
    }

    /**
     * Establece el nuevo valor para la propiedad DocumentNo .
     * 
     *@param DocumentNo El nuevo valor para DocumentNo .
     */
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    /**
     * Propiedad DocStatus.
     */
    protected String docStatus = null;

    /**
     * Devuelve el valor de la propiedad docStatus .
     * 
     *@return  El valor actual de DocStatus
     */
    public String getDocStatus() {
        return this.docStatus;
    }

    /**
     * Establece el nuevo valor para la propiedad docStatus .
     * 
     *@param docStatus El nuevo valor para docStatus .
     */
    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    /**
     * Propiedad DocAction.
     */
    protected String docAction = null;

    /**
     * Devuelve el valor de la propiedad docAction .
     * 
     *@return  El valor actual de DocAction
     */
    public String getDocAction() {
        return this.docAction;
    }

    /**
     * Establece el nuevo valor para la propiedad docAction .
     * 
     *@param docAction El nuevo valor para docAction .
     */
    public void setDocAction(String docAction) {
        this.docAction = docAction;
    }

    /**
     * Propiedad Processing.
     */
    protected String processing = null;

    /**
     * Devuelve el valor de la propiedad processing .
     * 
     *@return  El valor actual de Processing
     */
    public String getProcessing() {
        return this.processing;
    }

    /**
     * Establece el nuevo valor para la propiedad processing .
     * 
     *@param processing El nuevo valor para processing .
     */
    public void setProcessing(String processing) {
        this.processing = processing;
    }

    /**
     * Propiedad Processed.
     */
    protected String processed = null;

    /**
     * Devuelve el valor de la propiedad processed .
     * 
     *@return  El valor actual de Processed
     */
    public String getProcessed() {
        return this.processed;
    }

    /**
     * Establece el nuevo valor para la propiedad processed .
     * 
     *@param processed El nuevo valor para processed .
     */
    public void setProcessed(String processed) {
        this.processed = processed;
    }

    /**
     * Propiedad IsApproved.
     */
    protected String isApproved = null;

    /**
     * Devuelve el valor de la propiedad isApproved .
     * 
     *@return  El valor actual de IsApproved
     */
    public String getIsApproved() {
        return this.isApproved;
    }

    /**
     * Establece el nuevo valor para la propiedad isApproved .
     * 
     *@param isApproved El nuevo valor para isApproved .
     */
    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    /**
     * Propiedad XXActPacienteID.
     */
    protected long xXActPacienteID = 0;

    /**
     * Devuelve el valor de la propiedad xXActPacienteID .
     * 
     *@return  El valor actual de XXActPacienteID
     */
    public long getXXActPacienteID() {
        return this.xXActPacienteID;
    }

    /**
     * Establece el nuevo valor para la propiedad xXActPacienteID .
     * 
     *@param xXActPacienteID El nuevo valor para xXActPacienteID .
     */
    public void setXXActPacienteID(long xXActPacienteID) {
        this.xXActPacienteID = xXActPacienteID;
    }

    /**
     * Propiedad DateAcct.
     */
    protected  java.sql.Date dateAcct = null;

    /**
     * Devuelve el valor de la propiedad DateAcct .
     * 
     *@return  El valor actual de DateAcct
     */
    public  java.sql.Date getDateAcct() {
        return this.dateAcct;
    }

    /**
     * Establece el nuevo valor para la propiedad DateAcct .
     * 
     *@param DateAcct El nuevo valor para DateAcct .
     */
    public void setDateAcct( java.sql.Date dateAcct) {
        this.dateAcct = dateAcct;
    }

    /**
     * Propiedad IsDiscountPrinted.
     */
    protected String isDiscountPrinted = null;

    /**
     * Devuelve el valor de la propiedad isDiscountPrinted .
     * 
     *@return  El valor actual de IsDiscountPrinted
     */
    public String getIsDiscountPrinted() {
        return this.isDiscountPrinted;
    }

    /**
     * Establece el nuevo valor para la propiedad isDiscountPrinted .
     * 
     *@param isDiscountPrinted El nuevo valor para isDiscountPrinted .
     */
    public void setIsDiscountPrinted(String isDiscountPrinted) {
        this.isDiscountPrinted = isDiscountPrinted;
    }

    /**
     * Propiedad TotalLines.
     */
    protected double totalLines = 0.0;

    /**
     * Devuelve el valor de la propiedad totalLines .
     * 
     *@return  El valor actual de TotalLines
     */
    public double getTotalLines() {
        return this.totalLines;
    }

    /**
     * Establece el nuevo valor para la propiedad totalLines .
     * 
     *@param totalLines El nuevo valor para totalLines .
     */
    public void setTotalLines(double totalLines) {
        this.totalLines = totalLines;
    }

    /**
     * Propiedad GrandTotal.
     */
    protected double grandTotal = 0.0;

    /**
     * Devuelve el valor de la propiedad grandTotal .
     * 
     *@return  El valor actual de GrandTotal
     */
    public double getGrandTotal() {
        return this.grandTotal;
    }

    /**
     * Establece el nuevo valor para la propiedad grandTotal .
     * 
     *@param grandTotal El nuevo valor para grandTotal .
     */
    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * Propiedad IsTaxIncluded.
     */
    protected String isTaxIncluded = null;

    /**
     * Devuelve el valor de la propiedad isTaxIncluded .
     * 
     *@return  El valor actual de IsTaxIncluded
     */
    public String getIsTaxIncluded() {
        return this.isTaxIncluded;
    }

    /**
     * Establece el nuevo valor para la propiedad isTaxIncluded .
     * 
     *@param isTaxIncluded El nuevo valor para isTaxIncluded .
     */
    public void setIsTaxIncluded(String isTaxIncluded) {
        this.isTaxIncluded = isTaxIncluded;
    }

    /**
     * Propiedad Posted.
     */
    protected String posted = null;

    /**
     * Devuelve el valor de la propiedad Posted .
     * 
     *@return  El valor actual de Posted
     */
    public String getPosted() {
        return this.posted;
    }

    /**
     * Establece el nuevo valor para la propiedad Posted .
     * 
     *@param Posted El nuevo valor para Posted .
     */
    public void setPosted(String posted) {
        this.posted = posted;
    }

    /**
     * Propiedad IsDelivered.
     */
    protected String isDelivered = null;

    /**
     * Devuelve el valor de la propiedad isDelivered .
     * 
     *@return  El valor actual de IsDelivered
     */
    public String getIsDelivered() {
        return this.isDelivered;
    }

    /**
     * Establece el nuevo valor para la propiedad isDelivered .
     * 
     *@param isDelivered El nuevo valor para isDelivered .
     */
    public void setIsDelivered(String isDelivered) {
        this.isDelivered = isDelivered;
    }

    /**
     * Propiedad CDocTypeID.
     */
    protected long cDocTypeID = 0;

    /**
     * Devuelve el valor de la propiedad cDocTypeID .
     * 
     *@return  El valor actual de CDocTypeID
     */
    public long getCDocTypeID() {
        return this.cDocTypeID;
    }

    /**
     * Establece el nuevo valor para la propiedad cDocTypeID .
     * 
     *@param cDocTypeID El nuevo valor para cDocTypeID .
     */
    public void setCDocTypeID(long cDocTypeID) {
        this.cDocTypeID = cDocTypeID;
    }

    /**
     * Propiedad CDocTypeTargetID.
     */
    protected long cDocTypeTargetID = 0;

    /**
     * Devuelve el valor de la propiedad cDocTypeTargetID .
     * 
     *@return  El valor actual de CDocTypeTargetID
     */
    public long getCDocTypeTargetID() {
        return this.cDocTypeTargetID;
    }

    /**
     * Establece el nuevo valor para la propiedad cDocTypeTargetID .
     * 
     *@param cDocTypeTargetID El nuevo valor para cDocTypeTargetID .
     */
    public void setCDocTypeTargetID(long cDocTypeTargetID) {
        this.cDocTypeTargetID = cDocTypeTargetID;
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
     * IsService property.
     */
    private String isService = null;

    /**
     * Get isService property.
     * 
     *@return IsService property.
     */
    public String getIsService() {
        return this.isService;
    }

    /**
     * Set isService property.
     * 
     *@param isService New isService property.
     */
    public void setIsService(String isService) {
        this.isService = isService;
    }


    /**
     * Propiedad PriorityRule.
     */
    private String priorityRule = "5";

    /**
     * Obtener la propiedad priorityRule.
     * 
     *@return La propiedad PriorityRule.
     */
    public String getPriorityRule() {
        return this.priorityRule;
    }

    /**
     * Establecer la propiedad priorityRule.
     * 
     *@param priorityRule Nueva propiedad priorityRule.
     */
    public void setPriorityRule(String priorityRule) {
        this.priorityRule = priorityRule;
    }

}