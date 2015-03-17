package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.compiere.util.Env;

public class InfoInvoice {
	private int C_BPartner_ID = 0;
	private String description = null;
	private String pOReference = null;
	private String address2 = null;
	private String address3 = null;
	private String numExt = null;
	private String numIn = null;
	private String city = null;
	private String postal = null;
	private int EXME_TownCouncil_ID = 0;
	private int C_Country_ID = 0;
	private int C_Region_ID = 0;
	private String observation = null;
	private int C_BPartner_Location_ID = 0;
	private boolean isOrderFacLineCategory = false;
	private String sortBy= null;
	private BigDecimal discountAmt = Env.ZERO;
	private BigDecimal discountPorcent = Env.ZERO;
	
	public String getpOReference() {
		return pOReference;
	}
	public void setpOReference(String pOReference) {
		this.pOReference = pOReference;
	}
	public boolean isOrderFacLineCategory() {
		return isOrderFacLineCategory;
	}
	public void setIsOrderFacLineCategory(boolean isOrderFacLineCategory) {
		this.isOrderFacLineCategory = isOrderFacLineCategory;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}
	public BigDecimal getDiscountPorcent() {
		return discountPorcent;
	}
	public void setDiscountPorcent(BigDecimal discountPorcent) {
		this.discountPorcent = discountPorcent;
	}
	public int getC_BPartner_ID() {
		return C_BPartner_ID;
	}
	public void setC_BPartner_ID(int c_BPartner_ID) {
		C_BPartner_ID = c_BPartner_ID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPOReference() {
		return pOReference;
	}
	public void setPOReference(String pOReference) {
		this.pOReference = pOReference;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getNumExt() {
		return numExt;
	}
	public void setNumExt(String numExt) {
		this.numExt = numExt;
	}
	public String getNumIn() {
		return numIn;
	}
	public void setNumIn(String numIn) {
		this.numIn = numIn;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public int getEXME_TownCouncil_ID() {
		return EXME_TownCouncil_ID;
	}
	public void setEXME_TownCouncil_ID(int eXME_TownCouncil_ID) {
		EXME_TownCouncil_ID = eXME_TownCouncil_ID;
	}
	public int getC_Country_ID() {
		return C_Country_ID;
	}
	public void setC_Country_ID(int c_Country_ID) {
		C_Country_ID = c_Country_ID;
	}
	public int getC_Region_ID() {
		return C_Region_ID;
	}
	public void setC_Region_ID(int c_Region_ID) {
		C_Region_ID = c_Region_ID;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public int getC_BPartner_Location_ID() {
		return C_BPartner_Location_ID;
	}
	public void setC_BPartner_Location_ID(int c_BPartner_Location_ID) {
		C_BPartner_Location_ID = c_BPartner_Location_ID;
	}
	
	
	
	
	private Map<Integer, BigDecimal> basesGravAseg = null;
	private MCash mCash = null;
	private List<MCtaPacDet> lines = new ArrayList<MCtaPacDet>();
	private  MEXMECtaPacExt mExtension = null;
		
	
	public List<MCtaPacDet> getLines() {
		return lines;
	}
//	public void setLines(List<MCtaPacDet> lines) {
//		this.lines = lines;
//	}
	public MEXMECtaPacExt getmExtension() {
		return mExtension;
	}
	public void setmExtension(MEXMECtaPacExt mExtension) {
		this.mExtension = mExtension;
	}
	public Map<Integer, BigDecimal> getBasesGravAseg() {
		return basesGravAseg;
	}
	public void setBasesGravAseg(Map<Integer, BigDecimal> basesGravAseg) {
		this.basesGravAseg = basesGravAseg;
	}
	public MCash getmCash() {
		return mCash;
	}
	public void setmCash(MCash mCash) {
		this.mCash = mCash;
	}
//	private String observation = null ;
	private String pddress1 = null ;
//	private String postal = null ;
//	private String pOReference = null ;
//	private String city = null ;
	private String partner = null ;
	private String invoicePhone = null ;
	private String nombre_Medico = null ;
	private String documentNo = null ;
	private String documentNoExt = null ;
	private String nombre_Paciente = null ;
//	private String description = null ;
//	private String address2 = null ;
//	private String numExt = null ;
	private String estServ = null ;
//	private String sortBy = null ;
	private String confType = null ;
//	private String address3 = null ;
//	private String numIn = null ;
	private int updatedByCancel = 0 ;
	private BigDecimal grandTotal = Env.ZERO ;
//	private BigDecimal discountAmt = Env.ZERO ;
	private BigDecimal discountTaxAmt = Env.ZERO ;
	private BigDecimal totalLines = Env.ZERO ;
	private BigDecimal taxAmt = Env.ZERO ;
	private BigDecimal prePayment = Env.ZERO ;
	private BigDecimal chargeAmt = Env.ZERO ;
	private BigDecimal coaseguroAmt = Env.ZERO ;
	private BigDecimal coaseguroMedAmt = Env.ZERO ;
	private BigDecimal copagoAmt = Env.ZERO ;
	private BigDecimal deducibleAmt = Env.ZERO ;
	private int c_Invoice_ID = 0 ;
	private String sELLO = null ;
	private String motivoCancel = null ;
	private String uUID = null ;
	private String addenda = null ;
	private Timestamp dunningGrace = null ;
	private Timestamp dateOrdered = null ;
	private Timestamp datePrinted = null ;
	private Timestamp dateAcct = null ;
	private Timestamp  fechaNac = null ;
	private Timestamp  created = null ;
	private Timestamp  updated = null ;
	private Timestamp  dateInvoiced = null ;
	private String sexo = null ;
	private String docStatus = null ;
	private String isTransferred = null ;
	private String invoiceCollectionType = null ;
	private String trxType = null ;
	private String tipoOperacion = null ;
	private int c_CashBook_ID = 0 ;
	private int aD_Org_ID = 0 ;
	private int aD_OrgTrx_ID = 0 ;
	private int canceledBy = 0 ;
	private int c_Cash_ID = 0 ;
	private int c_Charge_ID = 0 ;
	private int c_Country_ID = 0 ;
	private int c_DocTypeTarget_ID = 0 ;
	private int createdBy = 0 ;
	private int c_Region_ID = 0 ;
	private int EXME_CtaPacExt_ID = 0 ;
	private int EXME_CtaPac_ID = 0 ;
	private int EXME_EstServ_ID = 0 ;
	private int EXME_MotivoCancel_ID = 0 ;
	private int ref_Invoice_Sales_ID = 0 ;
	private int reversal_ID = 0 ;
	private int updatedBy = 0 ;
	private int user1_ID = 0 ;
	private int user2_ID = 0 ;
	private int c_PaymentTerm_ID = 0 ;
	private int aD_User_ID = 0 ;
	private int M_PriceList_ID = 0 ;
	private int M_RMA_ID = 0 ;
	private int multiple_ID = 0 ;
	private int c_Activity_ID = 0 ;
	private int EXME_ClaimPayment_ID = 0 ;
	private int aD_Client_ID = 0 ;
	private int EXME_Paciente_ID = 0 ;
	private int EXME_Medico_ID = 0 ;
	private int c_DunningLevel_ID = 0 ;
//	private int EXME_TownCouncil_ID = 0 ;
	private int c_DocType_ID = 0 ;
	private int c_Currency_ID = 0 ;
	private int c_BPartner_Location_ID = 0 ;
	private int c_Project_ID = 0 ;
	private int c_ConversionType_ID = 0 ;
	private int c_Campaign_ID = 0 ;
	private boolean isTaxIncluded = false ;
	private boolean backoffice = false ;
	private boolean descFromAseg = false ;
	private boolean precFromAseg = false ;
	private boolean processed = false ;
	private boolean rebate = false ;
	private boolean afecta_Caja = false ;
	private boolean sendEMail = false ;
	private boolean isActive = false ;
	private boolean isApproved = false ;
	private boolean isDiscountPrinted = false ;
	private boolean isGenerated = false ;
	private boolean isInDispute = false ;
	private boolean isInvoiced = false ;
	private boolean isMultiple = false ;
//	private boolean isOrderFacLineCategory = false ;
	private boolean isPaid = false ;
	private boolean isPayScheduleValid = false ;
	private boolean isPrinted = false ;
	private boolean isPrintedPre = false ;
	private boolean isSelfService = false ;
	private boolean isSOTrx = false ;
//	private BigDecimal discountPorcent = Env.ZERO ;
	private BigDecimal globalDiscount = Env.ZERO ;
	private BigDecimal deducible = Env.ZERO ;
	private BigDecimal copago = Env.ZERO ;
	private BigDecimal coaseguroMed = Env.ZERO ;
	private BigDecimal coaseguro = Env.ZERO ;
	private String generateTo = null ;
	private String processing = null ;
	private String posted = null ;
	private String docAction = null ;
	private String paymentRule = null ;
	private String copyFrom = null ;
	private String createFrom = null ;
	private String verPoliza = null ;
	private int c_Payment_ID = 0 ;
	private int ref_Invoice_ID = 0 ;
	private int c_CashLine_ID = 0 ;
	private int salesRep_ID = 0 ;
	private int c_BPartner_ID = 0 ;
	private int c_Order_ID = 0 ;
	private String cadena = null ;
	
//	public String getObservation() {
//		return observation;
//	}
//	public void setObservation(String observation) {
//		this.observation = observation;
//	}
	public String getPddress1() {
		return pddress1;
	}
	public void setPddress1(String pddress1) {
		this.pddress1 = pddress1;
	}
//	public String getPostal() {
//		return postal;
//	}
//	public void setPostal(String postal) {
//		this.postal = postal;
//	}
//	public String getpOReference() {
//		return pOReference;
//	}
//	public void setpOReference(String pOReference) {
//		this.pOReference = pOReference;
//	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getInvoicePhone() {
		return invoicePhone;
	}
	public void setInvoicePhone(String invoicePhone) {
		this.invoicePhone = invoicePhone;
	}
	public String getNombre_Medico() {
		return nombre_Medico;
	}
	public void setNombre_Medico(String nombre_Medico) {
		this.nombre_Medico = nombre_Medico;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getDocumentNoExt() {
		return documentNoExt;
	}
	public void setDocumentNoExt(String documentNoExt) {
		this.documentNoExt = documentNoExt;
	}
	public String getNombre_Paciente() {
		return nombre_Paciente;
	}
	public void setNombre_Paciente(String nombre_Paciente) {
		this.nombre_Paciente = nombre_Paciente;
	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getAddress2() {
//		return address2;
//	}
//	public void setAddress2(String address2) {
//		this.address2 = address2;
//	}
//	public String getNumExt() {
//		return numExt;
//	}
//	public void setNumExt(String numExt) {
//		this.numExt = numExt;
//	}
	public String getEstServ() {
		return estServ;
	}
	public void setEstServ(String estServ) {
		this.estServ = estServ;
	}
//	public String getSortBy() {
//		return sortBy;
//	}
//	public void setSortBy(String sortBy) {
//		this.sortBy = sortBy;
//	}
	public String getConfType() {
		return confType;
	}
	public void setConfType(String confType) {
		this.confType = confType;
	}
//	public String getAddress3() {
//		return address3;
//	}
//	public void setAddress3(String address3) {
//		this.address3 = address3;
//	}
//	public String getNumIn() {
//		return numIn;
//	}
//	public void setNumIn(String numIn) {
//		this.numIn = numIn;
//	}
	public int getUpdatedByCancel() {
		return updatedByCancel;
	}
	public void setUpdatedByCancel(int updatedByCancel) {
		this.updatedByCancel = updatedByCancel;
	}
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
//	public BigDecimal getDiscountAmt() {
//		return discountAmt;
//	}
//	public void setDiscountAmt(BigDecimal discountAmt) {
//		this.discountAmt = discountAmt;
//	}
	public BigDecimal getDiscountTaxAmt() {
		return discountTaxAmt;
	}
	public void setDiscountTaxAmt(BigDecimal discountTaxAmt) {
		this.discountTaxAmt = discountTaxAmt;
	}
	public BigDecimal getTotalLines() {
		return totalLines;
	}
	public void setTotalLines(BigDecimal totalLines) {
		this.totalLines = totalLines;
	}
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}
	public BigDecimal getPrePayment() {
		return prePayment;
	}
	public void setPrePayment(BigDecimal prePayment) {
		this.prePayment = prePayment;
	}
	public BigDecimal getChargeAmt() {
		return chargeAmt;
	}
	public void setChargeAmt(BigDecimal chargeAmt) {
		this.chargeAmt = chargeAmt;
	}
	public BigDecimal getCoaseguroAmt() {
		return coaseguroAmt;
	}
	public void setCoaseguroAmt(BigDecimal coaseguroAmt) {
		this.coaseguroAmt = coaseguroAmt;
	}
	public BigDecimal getCoaseguroMedAmt() {
		return coaseguroMedAmt;
	}
	public void setCoaseguroMedAmt(BigDecimal coaseguroMedAmt) {
		this.coaseguroMedAmt = coaseguroMedAmt;
	}
	public BigDecimal getCopagoAmt() {
		return copagoAmt;
	}
	public void setCopagoAmt(BigDecimal copagoAmt) {
		this.copagoAmt = copagoAmt;
	}
	public BigDecimal getDeducibleAmt() {
		return deducibleAmt;
	}
	public void setDeducibleAmt(BigDecimal deducibleAmt) {
		this.deducibleAmt = deducibleAmt;
	}
	public int getC_Invoice_ID() {
		return c_Invoice_ID;
	}
	public void setC_Invoice_ID(int c_Invoice_ID) {
		this.c_Invoice_ID = c_Invoice_ID;
	}
	public String getsELLO() {
		return sELLO;
	}
	public void setsELLO(String sELLO) {
		this.sELLO = sELLO;
	}
	public String getMotivoCancel() {
		return motivoCancel;
	}
	public void setMotivoCancel(String motivoCancel) {
		this.motivoCancel = motivoCancel;
	}
	public String getuUID() {
		return uUID;
	}
	public void setuUID(String uUID) {
		this.uUID = uUID;
	}
	public String getAddenda() {
		return addenda;
	}
	public void setAddenda(String addenda) {
		this.addenda = addenda;
	}
	public Timestamp getDunningGrace() {
		return dunningGrace;
	}
	public void setDunningGrace(Timestamp dunningGrace) {
		this.dunningGrace = dunningGrace;
	}
	public Timestamp getDateOrdered() {
		return dateOrdered;
	}
	public void setDateOrdered(Timestamp dateOrdered) {
		this.dateOrdered = dateOrdered;
	}
	public Timestamp getDatePrinted() {
		return datePrinted;
	}
	public void setDatePrinted(Timestamp datePrinted) {
		this.datePrinted = datePrinted;
	}
	public Timestamp getDateAcct() {
		return dateAcct;
	}
	public void setDateAcct(Timestamp dateAcct) {
		this.dateAcct = dateAcct;
	}
	public Timestamp getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Timestamp fechaNac) {
		this.fechaNac = fechaNac;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public Timestamp getDateInvoiced() {
		return dateInvoiced;
	}
	public void setDateInvoiced(Timestamp dateInvoiced) {
		this.dateInvoiced = dateInvoiced;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	public String getIsTransferred() {
		return isTransferred;
	}
	public void setIsTransferred(String isTransferred) {
		this.isTransferred = isTransferred;
	}
	public String getInvoiceCollectionType() {
		return invoiceCollectionType;
	}
	public void setInvoiceCollectionType(String invoiceCollectionType) {
		this.invoiceCollectionType = invoiceCollectionType;
	}
	public String getTrxType() {
		return trxType;
	}
	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public int getC_CashBook_ID() {
		return c_CashBook_ID;
	}
	public void setC_CashBook_ID(int c_CashBook_ID) {
		this.c_CashBook_ID = c_CashBook_ID;
	}
	public int getaD_Org_ID() {
		return aD_Org_ID;
	}
	public void setaD_Org_ID(int aD_Org_ID) {
		this.aD_Org_ID = aD_Org_ID;
	}
	public int getaD_OrgTrx_ID() {
		return aD_OrgTrx_ID;
	}
	public void setaD_OrgTrx_ID(int aD_OrgTrx_ID) {
		this.aD_OrgTrx_ID = aD_OrgTrx_ID;
	}
	public int getCanceledBy() {
		return canceledBy;
	}
	public void setCanceledBy(int canceledBy) {
		this.canceledBy = canceledBy;
	}
	public int getC_Cash_ID() {
		return c_Cash_ID;
	}
	public void setC_Cash_ID(int c_Cash_ID) {
		this.c_Cash_ID = c_Cash_ID;
	}
	public int getC_Charge_ID() {
		return c_Charge_ID;
	}
	public void setC_Charge_ID(int c_Charge_ID) {
		this.c_Charge_ID = c_Charge_ID;
	}
//	public int getC_Country_ID() {
//		return c_Country_ID;
//	}
//	public void setC_Country_ID(int c_Country_ID) {
//		this.c_Country_ID = c_Country_ID;
//	}
	public int getC_DocTypeTarget_ID() {
		return c_DocTypeTarget_ID;
	}
	public void setC_DocTypeTarget_ID(int c_DocTypeTarget_ID) {
		this.c_DocTypeTarget_ID = c_DocTypeTarget_ID;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
//	public int getC_Region_ID() {
//		return c_Region_ID;
//	}
//	public void setC_Region_ID(int c_Region_ID) {
//		this.c_Region_ID = c_Region_ID;
//	}
	public int getEXME_CtaPacExt_ID() {
		return EXME_CtaPacExt_ID;
	}
	public void setEXME_CtaPacExt_ID(int eXME_CtaPacExt_ID) {
		EXME_CtaPacExt_ID = eXME_CtaPacExt_ID;
	}
	public int getEXME_CtaPac_ID() {
		return EXME_CtaPac_ID;
	}
	public void setEXME_CtaPac_ID(int eXME_CtaPac_ID) {
		EXME_CtaPac_ID = eXME_CtaPac_ID;
	}
	public int getEXME_EstServ_ID() {
		return EXME_EstServ_ID;
	}
	public void setEXME_EstServ_ID(int eXME_EstServ_ID) {
		EXME_EstServ_ID = eXME_EstServ_ID;
	}
	public int getEXME_MotivoCancel_ID() {
		return EXME_MotivoCancel_ID;
	}
	public void setEXME_MotivoCancel_ID(int eXME_MotivoCancel_ID) {
		EXME_MotivoCancel_ID = eXME_MotivoCancel_ID;
	}
	public int getRef_Invoice_Sales_ID() {
		return ref_Invoice_Sales_ID;
	}
	public void setRef_Invoice_Sales_ID(int ref_Invoice_Sales_ID) {
		this.ref_Invoice_Sales_ID = ref_Invoice_Sales_ID;
	}
	public int getReversal_ID() {
		return reversal_ID;
	}
	public void setReversal_ID(int reversal_ID) {
		this.reversal_ID = reversal_ID;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getUser1_ID() {
		return user1_ID;
	}
	public void setUser1_ID(int user1_ID) {
		this.user1_ID = user1_ID;
	}
	public int getUser2_ID() {
		return user2_ID;
	}
	public void setUser2_ID(int user2_ID) {
		this.user2_ID = user2_ID;
	}
	public int getC_PaymentTerm_ID() {
		return c_PaymentTerm_ID;
	}
	public void setC_PaymentTerm_ID(int c_PaymentTerm_ID) {
		this.c_PaymentTerm_ID = c_PaymentTerm_ID;
	}
	public int getaD_User_ID() {
		return aD_User_ID;
	}
	public void setaD_User_ID(int aD_User_ID) {
		this.aD_User_ID = aD_User_ID;
	}
	public int getM_PriceList_ID() {
		return M_PriceList_ID;
	}
	public void setM_PriceList_ID(int m_PriceList_ID) {
		M_PriceList_ID = m_PriceList_ID;
	}
	public int getM_RMA_ID() {
		return M_RMA_ID;
	}
	public void setM_RMA_ID(int m_RMA_ID) {
		M_RMA_ID = m_RMA_ID;
	}
	public int getMultiple_ID() {
		return multiple_ID;
	}
	public void setMultiple_ID(int multiple_ID) {
		this.multiple_ID = multiple_ID;
	}
	public int getC_Activity_ID() {
		return c_Activity_ID;
	}
	public void setC_Activity_ID(int c_Activity_ID) {
		this.c_Activity_ID = c_Activity_ID;
	}
	public int getEXME_ClaimPayment_ID() {
		return EXME_ClaimPayment_ID;
	}
	public void setEXME_ClaimPayment_ID(int eXME_ClaimPayment_ID) {
		EXME_ClaimPayment_ID = eXME_ClaimPayment_ID;
	}
	public int getaD_Client_ID() {
		return aD_Client_ID;
	}
	public void setaD_Client_ID(int aD_Client_ID) {
		this.aD_Client_ID = aD_Client_ID;
	}
	public int getEXME_Paciente_ID() {
		return EXME_Paciente_ID;
	}
	public void setEXME_Paciente_ID(int eXME_Paciente_ID) {
		EXME_Paciente_ID = eXME_Paciente_ID;
	}
	public int getEXME_Medico_ID() {
		return EXME_Medico_ID;
	}
	public void setEXME_Medico_ID(int eXME_Medico_ID) {
		EXME_Medico_ID = eXME_Medico_ID;
	}
	public int getC_DunningLevel_ID() {
		return c_DunningLevel_ID;
	}
	public void setC_DunningLevel_ID(int c_DunningLevel_ID) {
		this.c_DunningLevel_ID = c_DunningLevel_ID;
	}
//	public int getEXME_TownCouncil_ID() {
//		return EXME_TownCouncil_ID;
//	}
//	public void setEXME_TownCouncil_ID(int eXME_TownCouncil_ID) {
//		EXME_TownCouncil_ID = eXME_TownCouncil_ID;
//	}
	public int getC_DocType_ID() {
		return c_DocType_ID;
	}
	public void setC_DocType_ID(int c_DocType_ID) {
		this.c_DocType_ID = c_DocType_ID;
	}
	public int getC_Currency_ID() {
		return c_Currency_ID;
	}
	public void setC_Currency_ID(int c_Currency_ID) {
		this.c_Currency_ID = c_Currency_ID;
	}
//	public int getC_BPartner_Location_ID() {
//		return c_BPartner_Location_ID;
//	}
//	public void setC_BPartner_Location_ID(int c_BPartner_Location_ID) {
//		this.c_BPartner_Location_ID = c_BPartner_Location_ID;
//	}
	public int getC_Project_ID() {
		return c_Project_ID;
	}
	public void setC_Project_ID(int c_Project_ID) {
		this.c_Project_ID = c_Project_ID;
	}
	public int getC_ConversionType_ID() {
		return c_ConversionType_ID;
	}
	public void setC_ConversionType_ID(int c_ConversionType_ID) {
		this.c_ConversionType_ID = c_ConversionType_ID;
	}
	public int getC_Campaign_ID() {
		return c_Campaign_ID;
	}
	public void setC_Campaign_ID(int c_Campaign_ID) {
		this.c_Campaign_ID = c_Campaign_ID;
	}
	public boolean isTaxIncluded() {
		return isTaxIncluded;
	}
	public void setTaxIncluded(boolean isTaxIncluded) {
		this.isTaxIncluded = isTaxIncluded;
	}
	public boolean isBackoffice() {
		return backoffice;
	}
	public void setBackoffice(boolean backoffice) {
		this.backoffice = backoffice;
	}
	public boolean isDescFromAseg() {
		return descFromAseg;
	}
	public void setDescFromAseg(boolean descFromAseg) {
		this.descFromAseg = descFromAseg;
	}
	public boolean isPrecFromAseg() {
		return precFromAseg;
	}
	public void setPrecFromAseg(boolean precFromAseg) {
		this.precFromAseg = precFromAseg;
	}
	public boolean isProcessed() {
		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	public boolean isRebate() {
		return rebate;
	}
	public void setRebate(boolean rebate) {
		this.rebate = rebate;
	}
	public boolean isAfecta_Caja() {
		return afecta_Caja;
	}
	public void setAfecta_Caja(boolean afecta_Caja) {
		this.afecta_Caja = afecta_Caja;
	}
	public boolean isSendEMail() {
		return sendEMail;
	}
	public void setSendEMail(boolean sendEMail) {
		this.sendEMail = sendEMail;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public boolean isDiscountPrinted() {
		return isDiscountPrinted;
	}
	public void setDiscountPrinted(boolean isDiscountPrinted) {
		this.isDiscountPrinted = isDiscountPrinted;
	}
	public boolean isGenerated() {
		return isGenerated;
	}
	public void setGenerated(boolean isGenerated) {
		this.isGenerated = isGenerated;
	}
	public boolean isInDispute() {
		return isInDispute;
	}
	public void setInDispute(boolean isInDispute) {
		this.isInDispute = isInDispute;
	}
	public boolean isInvoiced() {
		return isInvoiced;
	}
	public void setInvoiced(boolean isInvoiced) {
		this.isInvoiced = isInvoiced;
	}
	public boolean isMultiple() {
		return isMultiple;
	}
	public void setMultiple(boolean isMultiple) {
		this.isMultiple = isMultiple;
	}
//	public boolean isOrderFacLineCategory() {
//		return isOrderFacLineCategory;
//	}
//	public void setOrderFacLineCategory(boolean isOrderFacLineCategory) {
//		this.isOrderFacLineCategory = isOrderFacLineCategory;
//	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public boolean isPayScheduleValid() {
		return isPayScheduleValid;
	}
	public void setPayScheduleValid(boolean isPayScheduleValid) {
		this.isPayScheduleValid = isPayScheduleValid;
	}
	public boolean isPrinted() {
		return isPrinted;
	}
	public void setPrinted(boolean isPrinted) {
		this.isPrinted = isPrinted;
	}
	public boolean isPrintedPre() {
		return isPrintedPre;
	}
	public void setPrintedPre(boolean isPrintedPre) {
		this.isPrintedPre = isPrintedPre;
	}
	public boolean isSelfService() {
		return isSelfService;
	}
	public void setSelfService(boolean isSelfService) {
		this.isSelfService = isSelfService;
	}
	public boolean isSOTrx() {
		return isSOTrx;
	}
	public void setSOTrx(boolean isSOTrx) {
		this.isSOTrx = isSOTrx;
	}
//	public BigDecimal getDiscountPorcent() {
//		return discountPorcent;
//	}
//	public void setDiscountPorcent(BigDecimal discountPorcent) {
//		this.discountPorcent = discountPorcent;
//	}
	public BigDecimal getGlobalDiscount() {
		return globalDiscount;
	}
	public void setGlobalDiscount(BigDecimal globalDiscount) {
		this.globalDiscount = globalDiscount;
	}
	public BigDecimal getDeducible() {
		return deducible;
	}
	public void setDeducible(BigDecimal deducible) {
		this.deducible = deducible;
	}
	public BigDecimal getCopago() {
		return copago;
	}
	public void setCopago(BigDecimal copago) {
		this.copago = copago;
	}
	public BigDecimal getCoaseguroMed() {
		return coaseguroMed;
	}
	public void setCoaseguroMed(BigDecimal coaseguroMed) {
		this.coaseguroMed = coaseguroMed;
	}
	public BigDecimal getCoaseguro() {
		return coaseguro;
	}
	public void setCoaseguro(BigDecimal coaseguro) {
		this.coaseguro = coaseguro;
	}
	public String getGenerateTo() {
		return generateTo;
	}
	public void setGenerateTo(String generateTo) {
		this.generateTo = generateTo;
	}
	public String getProcessing() {
		return processing;
	}
	public void setProcessing(String processing) {
		this.processing = processing;
	}
	public String getPosted() {
		return posted;
	}
	public void setPosted(String posted) {
		this.posted = posted;
	}
	public String getDocAction() {
		return docAction;
	}
	public void setDocAction(String docAction) {
		this.docAction = docAction;
	}
	public String getPaymentRule() {
		return paymentRule;
	}
	public void setPaymentRule(String paymentRule) {
		this.paymentRule = paymentRule;
	}
	public String getCopyFrom() {
		return copyFrom;
	}
	public void setCopyFrom(String copyFrom) {
		this.copyFrom = copyFrom;
	}
	public String getCreateFrom() {
		return createFrom;
	}
	public void setCreateFrom(String createFrom) {
		this.createFrom = createFrom;
	}
	public String getVerPoliza() {
		return verPoliza;
	}
	public void setVerPoliza(String verPoliza) {
		this.verPoliza = verPoliza;
	}
	public int getC_Payment_ID() {
		return c_Payment_ID;
	}
	public void setC_Payment_ID(int c_Payment_ID) {
		this.c_Payment_ID = c_Payment_ID;
	}
	public int getRef_Invoice_ID() {
		return ref_Invoice_ID;
	}
	public void setRef_Invoice_ID(int ref_Invoice_ID) {
		this.ref_Invoice_ID = ref_Invoice_ID;
	}
	public int getC_CashLine_ID() {
		return c_CashLine_ID;
	}
	public void setC_CashLine_ID(int c_CashLine_ID) {
		this.c_CashLine_ID = c_CashLine_ID;
	}
	public int getSalesRep_ID() {
		return salesRep_ID;
	}
	public void setSalesRep_ID(int salesRep_ID) {
		this.salesRep_ID = salesRep_ID;
	}
//	public int getC_BPartner_ID() {
//		return c_BPartner_ID;
//	}
//	public void setC_BPartner_ID(int c_BPartner_ID) {
//		this.c_BPartner_ID = c_BPartner_ID;
//	}
	public int getC_Order_ID() {
		return c_Order_ID;
	}
	public void setC_Order_ID(int c_Order_ID) {
		this.c_Order_ID = c_Order_ID;
	}
	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
}
