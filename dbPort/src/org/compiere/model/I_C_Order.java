/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Order
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_Order 
{

    /** TableName=C_Order */
    public static final String Table_Name = "C_Order";

    /** AD_Table_ID=259 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name AmountRefunded */
    public static final String COLUMNNAME_AmountRefunded = "AmountRefunded";

	/** Set AmountRefunded	  */
	public void setAmountRefunded (BigDecimal AmountRefunded);

	/** Get AmountRefunded	  */
	public BigDecimal getAmountRefunded();

    /** Column name AmountTendered */
    public static final String COLUMNNAME_AmountTendered = "AmountTendered";

	/** Set AmountTendered	  */
	public void setAmountTendered (BigDecimal AmountTendered);

	/** Get AmountTendered	  */
	public BigDecimal getAmountTendered();

    /** Column name Bill_BPartner_ID */
    public static final String COLUMNNAME_Bill_BPartner_ID = "Bill_BPartner_ID";

	/** Set Invoice Partner.
	  * Business Partner to be invoiced
	  */
	public void setBill_BPartner_ID (int Bill_BPartner_ID);

	/** Get Invoice Partner.
	  * Business Partner to be invoiced
	  */
	public int getBill_BPartner_ID();

    /** Column name Bill_Location_ID */
    public static final String COLUMNNAME_Bill_Location_ID = "Bill_Location_ID";

	/** Set Invoice Location.
	  * Business Partner Location for invoicing
	  */
	public void setBill_Location_ID (int Bill_Location_ID);

	/** Get Invoice Location.
	  * Business Partner Location for invoicing
	  */
	public int getBill_Location_ID();

    /** Column name Bill_User_ID */
    public static final String COLUMNNAME_Bill_User_ID = "Bill_User_ID";

	/** Set Invoice Contact.
	  * Business Partner Contact for invoicing
	  */
	public void setBill_User_ID (int Bill_User_ID);

	/** Get Invoice Contact.
	  * Business Partner Contact for invoicing
	  */
	public int getBill_User_ID();

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Responsible Units.
	  * Responsible Units
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Responsible Units.
	  * Responsible Units
	  */
	public int getC_Activity_ID();

	public I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Company Location.
	  * Identifies the (ship to) address for this Company
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Company Location.
	  * Identifies the (ship to) address for this Company
	  */
	public int getC_BPartner_Location_ID();

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Program.
	  * Program
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Program.
	  * Program
	  */
	public int getC_Campaign_ID();

	public I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_CashLine_ID */
    public static final String COLUMNNAME_C_CashLine_ID = "C_CashLine_ID";

	/** Set Cash Journal Line.
	  * Cash Journal Line
	  */
	public void setC_CashLine_ID (int C_CashLine_ID);

	/** Get Cash Journal Line.
	  * Cash Journal Line
	  */
	public int getC_CashLine_ID();

	public I_C_CashLine getC_CashLine() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

    /** Column name C_ConversionType_ID */
    public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";

	/** Set Currency Type.
	  * Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID);

	/** Get Currency Type.
	  * Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID();

	public I_C_ConversionType getC_ConversionType() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name C_DocTypeTarget_ID */
    public static final String COLUMNNAME_C_DocTypeTarget_ID = "C_DocTypeTarget_ID";

	/** Set Target Document Type.
	  * Target document type for conversing documents
	  */
	public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID);

	/** Get Target Document Type.
	  * Target document type for conversing documents
	  */
	public int getC_DocTypeTarget_ID();

    /** Column name ChargeAmt */
    public static final String COLUMNNAME_ChargeAmt = "ChargeAmt";

	/** Set Charge amount.
	  * Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt);

	/** Get Charge amount.
	  * Charge Amount
	  */
	public BigDecimal getChargeAmt();

    /** Column name CopyFrom */
    public static final String COLUMNNAME_CopyFrom = "CopyFrom";

	/** Set Copy From.
	  * Copy From Record
	  */
	public void setCopyFrom (String CopyFrom);

	/** Get Copy From.
	  * Copy From Record
	  */
	public String getCopyFrom();

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public I_C_Payment getC_Payment() throws RuntimeException;

    /** Column name C_PaymentTerm_ID */
    public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";

	/** Set Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID);

	/** Get Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID();

	public I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException;

    /** Column name C_POS_ID */
    public static final String COLUMNNAME_C_POS_ID = "C_POS_ID";

	/** Set POS Terminal.
	  * Point of Sales Terminal
	  */
	public void setC_POS_ID (int C_POS_ID);

	/** Get POS Terminal.
	  * Point of Sales Terminal
	  */
	public int getC_POS_ID();

	public I_C_POS getC_POS() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public I_C_Project getC_Project() throws RuntimeException;

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name DateCompleted */
    public static final String COLUMNNAME_DateCompleted = "DateCompleted";

	/** Set Date Completed.
	  * Date the document was Completed.
	  */
	public void setDateCompleted (Timestamp DateCompleted);

	/** Get Date Completed.
	  * Date the document was Completed.
	  */
	public Timestamp getDateCompleted();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name DatePrinted */
    public static final String COLUMNNAME_DatePrinted = "DatePrinted";

	/** Set Date printed.
	  * Date the document was printed.
	  */
	public void setDatePrinted (Timestamp DatePrinted);

	/** Get Date printed.
	  * Date the document was printed.
	  */
	public Timestamp getDatePrinted();

    /** Column name DatePromised */
    public static final String COLUMNNAME_DatePromised = "DatePromised";

	/** Set Date Promised.
	  * Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised);

	/** Get Date Promised.
	  * Date Order was promised
	  */
	public Timestamp getDatePromised();

    /** Column name DeliveryRule */
    public static final String COLUMNNAME_DeliveryRule = "DeliveryRule";

	/** Set Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public void setDeliveryRule (String DeliveryRule);

	/** Get Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public String getDeliveryRule();

    /** Column name DeliveryViaRule */
    public static final String COLUMNNAME_DeliveryViaRule = "DeliveryViaRule";

	/** Set Delivery Via.
	  * How the order will be delivered
	  */
	public void setDeliveryViaRule (String DeliveryViaRule);

	/** Get Delivery Via.
	  * How the order will be delivered
	  */
	public String getDeliveryViaRule();

    /** Column name DescFromAseg */
    public static final String COLUMNNAME_DescFromAseg = "DescFromAseg";

	/** Set Insurance Co Discount.
	  * Discount for Insurance Company
	  */
	public void setDescFromAseg (boolean DescFromAseg);

	/** Get Insurance Co Discount.
	  * Discount for Insurance Company
	  */
	public boolean isDescFromAseg();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Descuento1 */
    public static final String COLUMNNAME_Descuento1 = "Descuento1";

	/** Set Discount 1	  */
	public void setDescuento1 (BigDecimal Descuento1);

	/** Get Discount 1	  */
	public BigDecimal getDescuento1();

    /** Column name Descuento2 */
    public static final String COLUMNNAME_Descuento2 = "Descuento2";

	/** Set Discount 2	  */
	public void setDescuento2 (BigDecimal Descuento2);

	/** Get Discount 2	  */
	public BigDecimal getDescuento2();

    /** Column name Descuento3 */
    public static final String COLUMNNAME_Descuento3 = "Descuento3";

	/** Set Discount 3	  */
	public void setDescuento3 (BigDecimal Descuento3);

	/** Get Discount 3	  */
	public BigDecimal getDescuento3();

    /** Column name DescuentoGlobal */
    public static final String COLUMNNAME_DescuentoGlobal = "DescuentoGlobal";

	/** Set Global Discount	  */
	public void setDescuentoGlobal (BigDecimal DescuentoGlobal);

	/** Get Global Discount	  */
	public BigDecimal getDescuentoGlobal();

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name DropShip_BPartner_ID */
    public static final String COLUMNNAME_DropShip_BPartner_ID = "DropShip_BPartner_ID";

	/** Set Drop Shipment Partner.
	  * Business Partner to ship to
	  */
	public void setDropShip_BPartner_ID (int DropShip_BPartner_ID);

	/** Get Drop Shipment Partner.
	  * Business Partner to ship to
	  */
	public int getDropShip_BPartner_ID();

    /** Column name DropShip_Location_ID */
    public static final String COLUMNNAME_DropShip_Location_ID = "DropShip_Location_ID";

	/** Set Drop Shipment Location.
	  * Business Partner Location for shipping to
	  */
	public void setDropShip_Location_ID (int DropShip_Location_ID);

	/** Get Drop Shipment Location.
	  * Business Partner Location for shipping to
	  */
	public int getDropShip_Location_ID();

    /** Column name DropShip_User_ID */
    public static final String COLUMNNAME_DropShip_User_ID = "DropShip_User_ID";

	/** Set Drop Shipment Contact.
	  * Business Partner Contact for drop shipment
	  */
	public void setDropShip_User_ID (int DropShip_User_ID);

	/** Get Drop Shipment Contact.
	  * Business Partner Contact for drop shipment
	  */
	public int getDropShip_User_ID();

    /** Column name FreightAmt */
    public static final String COLUMNNAME_FreightAmt = "FreightAmt";

	/** Set Freight Amount.
	  * Freight Amount 
	  */
	public void setFreightAmt (BigDecimal FreightAmt);

	/** Get Freight Amount.
	  * Freight Amount 
	  */
	public BigDecimal getFreightAmt();

    /** Column name FreightCostRule */
    public static final String COLUMNNAME_FreightCostRule = "FreightCostRule";

	/** Set Freight Cost Rule.
	  * Method for charging Freight
	  */
	public void setFreightCostRule (String FreightCostRule);

	/** Get Freight Cost Rule.
	  * Method for charging Freight
	  */
	public String getFreightCostRule();

    /** Column name GrandTotal */
    public static final String COLUMNNAME_GrandTotal = "GrandTotal";

	/** Set Grand Total.
	  * Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal);

	/** Get Grand Total.
	  * Total amount of document
	  */
	public BigDecimal getGrandTotal();

    /** Column name InvoiceRule */
    public static final String COLUMNNAME_InvoiceRule = "InvoiceRule";

	/** Set Invoice Rule.
	  * Frequency and method of invoicing 
	  */
	public void setInvoiceRule (String InvoiceRule);

	/** Get Invoice Rule.
	  * Frequency and method of invoicing 
	  */
	public String getInvoiceRule();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name isBeingUsedInOut */
    public static final String COLUMNNAME_isBeingUsedInOut = "isBeingUsedInOut";

	/** Set isBeingUsedInOut	  */
	public void setisBeingUsedInOut (boolean isBeingUsedInOut);

	/** Get isBeingUsedInOut	  */
	public boolean isBeingUsedInOut();

    /** Column name isConsigna */
    public static final String COLUMNNAME_isConsigna = "isConsigna";

	/** Set Consigna	  */
	public void setisConsigna (boolean isConsigna);

	/** Get Consigna	  */
	public boolean isConsigna();

    /** Column name IsCreditApproved */
    public static final String COLUMNNAME_IsCreditApproved = "IsCreditApproved";

	/** Set Credit Approved.
	  * Credit  has been approved
	  */
	public void setIsCreditApproved (boolean IsCreditApproved);

	/** Get Credit Approved.
	  * Credit  has been approved
	  */
	public boolean isCreditApproved();

    /** Column name IsDelivered */
    public static final String COLUMNNAME_IsDelivered = "IsDelivered";

	/** Set Delivered	  */
	public void setIsDelivered (boolean IsDelivered);

	/** Get Delivered	  */
	public boolean isDelivered();

    /** Column name IsDiscountPrinted */
    public static final String COLUMNNAME_IsDiscountPrinted = "IsDiscountPrinted";

	/** Set Discount Printed.
	  * Print Discount on Invoice and Order
	  */
	public void setIsDiscountPrinted (boolean IsDiscountPrinted);

	/** Get Discount Printed.
	  * Print Discount on Invoice and Order
	  */
	public boolean isDiscountPrinted();

    /** Column name IsDropShip */
    public static final String COLUMNNAME_IsDropShip = "IsDropShip";

	/** Set Drop Shipment.
	  * Drop Shipments are sent from the Vendor directly to the Customer
	  */
	public void setIsDropShip (boolean IsDropShip);

	/** Get Drop Shipment.
	  * Drop Shipments are sent from the Vendor directly to the Customer
	  */
	public boolean isDropShip();

    /** Column name IsInvoiced */
    public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";

	/** Set Invoiced.
	  * Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced);

	/** Get Invoiced.
	  * Is this invoiced?
	  */
	public boolean isInvoiced();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name IsSelected */
    public static final String COLUMNNAME_IsSelected = "IsSelected";

	/** Set Selected	  */
	public void setIsSelected (boolean IsSelected);

	/** Get Selected	  */
	public boolean isSelected();

    /** Column name IsSelfService */
    public static final String COLUMNNAME_IsSelfService = "IsSelfService";

	/** Set Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService);

	/** Get Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService();

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

    /** Column name IsTaxIncluded */
    public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";

	/** Set Price includes Tax.
	  * Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded);

	/** Get Price includes Tax.
	  * Tax is included in the price 
	  */
	public boolean isTaxIncluded();

    /** Column name IsTransferred */
    public static final String COLUMNNAME_IsTransferred = "IsTransferred";

	/** Set Transferred.
	  * Transferred to General Ledger (i.e. accounted)
	  */
	public void setIsTransferred (String IsTransferred);

	/** Get Transferred.
	  * Transferred to General Ledger (i.e. accounted)
	  */
	public String getIsTransferred();

    /** Column name Link_Order_ID */
    public static final String COLUMNNAME_Link_Order_ID = "Link_Order_ID";

	/** Set Linked Order.
	  * This field links a sales order to the purchase order that is generated from it.
	  */
	public void setLink_Order_ID (int Link_Order_ID);

	/** Get Linked Order.
	  * This field links a sales order to the purchase order that is generated from it.
	  */
	public int getLink_Order_ID();

    /** Column name M_FreightCategory_ID */
    public static final String COLUMNNAME_M_FreightCategory_ID = "M_FreightCategory_ID";

	/** Set Freight Category.
	  * Category of the Freight
	  */
	public void setM_FreightCategory_ID (int M_FreightCategory_ID);

	/** Get Freight Category.
	  * Category of the Freight
	  */
	public int getM_FreightCategory_ID();

	public I_M_FreightCategory getM_FreightCategory() throws RuntimeException;

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_Requisition_ID */
    public static final String COLUMNNAME_M_Requisition_ID = "M_Requisition_ID";

	/** Set Requisition.
	  * Material Requisition
	  */
	public void setM_Requisition_ID (int M_Requisition_ID);

	/** Get Requisition.
	  * Material Requisition
	  */
	public int getM_Requisition_ID();

	public I_M_Requisition getM_Requisition() throws RuntimeException;

    /** Column name M_Shipper_ID */
    public static final String COLUMNNAME_M_Shipper_ID = "M_Shipper_ID";

	/** Set Shipper.
	  * Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID);

	/** Get Shipper.
	  * Method or manner of product delivery
	  */
	public int getM_Shipper_ID();

	public I_M_Shipper getM_Shipper() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name Observation */
    public static final String COLUMNNAME_Observation = "Observation";

	/** Set Observation.
	  * Optional short description of the record
	  */
	public void setObservation (String Observation);

	/** Get Observation.
	  * Optional short description of the record
	  */
	public String getObservation();

    /** Column name OrderType */
    public static final String COLUMNNAME_OrderType = "OrderType";

	/** Set OrderType	  */
	public void setOrderType (String OrderType);

	/** Get OrderType	  */
	public String getOrderType();

    /** Column name Pay_BPartner_ID */
    public static final String COLUMNNAME_Pay_BPartner_ID = "Pay_BPartner_ID";

	/** Set Payment BPartner.
	  * Business Partner responsible for the payment
	  */
	public void setPay_BPartner_ID (int Pay_BPartner_ID);

	/** Get Payment BPartner.
	  * Business Partner responsible for the payment
	  */
	public int getPay_BPartner_ID();

    /** Column name Pay_Location_ID */
    public static final String COLUMNNAME_Pay_Location_ID = "Pay_Location_ID";

	/** Set Payment Location.
	  * Location of the Business Partner responsible for the payment
	  */
	public void setPay_Location_ID (int Pay_Location_ID);

	/** Get Payment Location.
	  * Location of the Business Partner responsible for the payment
	  */
	public int getPay_Location_ID();

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

    /** Column name POReference */
    public static final String COLUMNNAME_POReference = "POReference";

	/** Set Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public void setPOReference (String POReference);

	/** Get Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference();

    /** Column name Posted */
    public static final String COLUMNNAME_Posted = "Posted";

	/** Set Posted.
	  * Posting status
	  */
	public void setPosted (boolean Posted);

	/** Get Posted.
	  * Posting status
	  */
	public boolean isPosted();

    /** Column name PrecFromAseg */
    public static final String COLUMNNAME_PrecFromAseg = "PrecFromAseg";

	/** Set Insurance Price	  */
	public void setPrecFromAseg (boolean PrecFromAseg);

	/** Get Insurance Price	  */
	public boolean isPrecFromAseg();

    /** Column name PriorityRule */
    public static final String COLUMNNAME_PriorityRule = "PriorityRule";

	/** Set Priority.
	  * Priority of a document
	  */
	public void setPriorityRule (String PriorityRule);

	/** Get Priority.
	  * Priority of a document
	  */
	public String getPriorityRule();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name PromotionCode */
    public static final String COLUMNNAME_PromotionCode = "PromotionCode";

	/** Set Promotion Code.
	  * User entered promotion code at sales time
	  */
	public void setPromotionCode (String PromotionCode);

	/** Get Promotion Code.
	  * User entered promotion code at sales time
	  */
	public String getPromotionCode();

    /** Column name Ref_Order_ID */
    public static final String COLUMNNAME_Ref_Order_ID = "Ref_Order_ID";

	/** Set Referenced Order.
	  * Reference to corresponding Sales/Purchase Order
	  */
	public void setRef_Order_ID (int Ref_Order_ID);

	/** Get Referenced Order.
	  * Reference to corresponding Sales/Purchase Order
	  */
	public int getRef_Order_ID();

    /** Column name SalesRep_ID */
    public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";

	/** Set Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID);

	/** Get Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public int getSalesRep_ID();

    /** Column name SendEMail */
    public static final String COLUMNNAME_SendEMail = "SendEMail";

	/** Set Send EMail.
	  * Enable sending Document EMail
	  */
	public void setSendEMail (boolean SendEMail);

	/** Get Send EMail.
	  * Enable sending Document EMail
	  */
	public boolean isSendEMail();

    /** Column name TotalLines */
    public static final String COLUMNNAME_TotalLines = "TotalLines";

	/** Set Total Lines.
	  * Total of all document lines
	  */
	public void setTotalLines (BigDecimal TotalLines);

	/** Get Total Lines.
	  * Total of all document lines
	  */
	public BigDecimal getTotalLines();

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();

    /** Column name VerPoliza */
    public static final String COLUMNNAME_VerPoliza = "VerPoliza";

	/** Set Policy	  */
	public void setVerPoliza (String VerPoliza);

	/** Get Policy	  */
	public String getVerPoliza();

    /** Column name Volume */
    public static final String COLUMNNAME_Volume = "Volume";

	/** Set Volume.
	  * Volume of a product
	  */
	public void setVolume (BigDecimal Volume);

	/** Get Volume.
	  * Volume of a product
	  */
	public BigDecimal getVolume();

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (BigDecimal Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public BigDecimal getWeight();
}
