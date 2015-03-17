/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_CtaPacExt
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CtaPacExt extends PO implements I_EXME_CtaPacExt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacExt (Properties ctx, int EXME_CtaPacExt_ID, String trxName)
    {
      super (ctx, EXME_CtaPacExt_ID, trxName);
      /** if (EXME_CtaPacExt_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_DocType_ID (0);
			setC_DocTypeTarget_ID (0);
			setCoaseguro (Env.ZERO);
// 0
			setCoaseguroAmt (Env.ZERO);
// 0
			setCoaseguroMedAmt (Env.ZERO);
// 0
			setCopagoAmt (Env.ZERO);
// 0
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDeducible (Env.ZERO);
// 0
			setDeducibleAmt (Env.ZERO);
// 0
			setDesctoGlobal (Env.ZERO);
// 0
			setDiscountTaxAmt (Env.ZERO);
			setDocAction (null);
			setDocStatus (null);
			setDocumentNo (null);
			setEXME_CtaPacExt_ID (0);
			setEXME_CtaPac_ID (0);
			setExtensionNo (0);
			setGrandTotal (Env.ZERO);
// 0
			setIsApproved (false);
			setIsCoasegPercent (false);
// N
			setIsCoasMedPercent (false);
// N
			setIsCopPercent (false);
// N
			setIsDeducPercent (false);
// N
			setIsDiscPercent (false);
// N
			setIsOrderFacLineCategory (false);
			setIsPrinted (false);
// N
			setProcessed (false);
			setTaxAmt (Env.ZERO);
// 0
			setTotalLines (Env.ZERO);
// 0
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacExt (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacExt[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Advance payment.
		@param Anticipo Advance payment	  */
	public void setAnticipo (BigDecimal Anticipo)
	{
		set_Value (COLUMNNAME_Anticipo, Anticipo);
	}

	/** Get Advance payment.
		@return Advance payment	  */
	public BigDecimal getAnticipo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Anticipo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Company Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Company
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Company Location.
		@return Identifies the (ship to) address for this Company
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0)
			 throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
		set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Target Document Type.
		@param C_DocTypeTarget_ID 
		Target document type for conversing documents
	  */
	public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID)
	{
		if (C_DocTypeTarget_ID < 1)
			 throw new IllegalArgumentException ("C_DocTypeTarget_ID is mandatory.");
		set_Value (COLUMNNAME_C_DocTypeTarget_ID, Integer.valueOf(C_DocTypeTarget_ID));
	}

	/** Get Target Document Type.
		@return Target document type for conversing documents
	  */
	public int getC_DocTypeTarget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypeTarget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Charge amount.
		@param ChargeAmt 
		Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt)
	{
		set_Value (COLUMNNAME_ChargeAmt, ChargeAmt);
	}

	/** Get Charge amount.
		@return Charge Amount
	  */
	public BigDecimal getChargeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ChargeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** ChargeStatus AD_Reference_ID=1200648 */
	public static final int CHARGESTATUS_AD_Reference_ID=1200648;
	/** Billed = B */
	public static final String CHARGESTATUS_Billed = "B";
	/** Complete = C */
	public static final String CHARGESTATUS_Complete = "C";
	/** Incomplete = D */
	public static final String CHARGESTATUS_Incomplete = "D";
	/** Rebilled = E */
	public static final String CHARGESTATUS_Rebilled = "E";
	/** ForBill = F */
	public static final String CHARGESTATUS_ForBill = "F";
	/** LateCharge = L */
	public static final String CHARGESTATUS_LateCharge = "L";
	/** ForRebill = R */
	public static final String CHARGESTATUS_ForRebill = "R";
	/** Set Charge Status.
		@param ChargeStatus Charge Status	  */
	public void setChargeStatus (String ChargeStatus)
	{

		if (ChargeStatus == null || ChargeStatus.equals("B") || ChargeStatus.equals("C") || ChargeStatus.equals("D") || ChargeStatus.equals("E") || ChargeStatus.equals("F") || ChargeStatus.equals("L") || ChargeStatus.equals("R")); else throw new IllegalArgumentException ("ChargeStatus Invalid value - " + ChargeStatus + " - Reference_ID=1200648 - B - C - D - E - F - L - R");		set_Value (COLUMNNAME_ChargeStatus, ChargeStatus);
	}

	/** Get Charge Status.
		@return Charge Status	  */
	public String getChargeStatus () 
	{
		return (String)get_Value(COLUMNNAME_ChargeStatus);
	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Address .
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address .
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Coinsurance.
		@param Coaseguro 
		Coinsurance
	  */
	public void setCoaseguro (BigDecimal Coaseguro)
	{
		if (Coaseguro == null)
			throw new IllegalArgumentException ("Coaseguro is mandatory.");
		set_Value (COLUMNNAME_Coaseguro, Coaseguro);
	}

	/** Get Coinsurance.
		@return Coinsurance
	  */
	public BigDecimal getCoaseguro () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Coaseguro);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Coinsurance.
		@param CoaseguroAmt Coinsurance	  */
	public void setCoaseguroAmt (BigDecimal CoaseguroAmt)
	{
		if (CoaseguroAmt == null)
			throw new IllegalArgumentException ("CoaseguroAmt is mandatory.");
		set_Value (COLUMNNAME_CoaseguroAmt, CoaseguroAmt);
	}

	/** Get Coinsurance.
		@return Coinsurance	  */
	public BigDecimal getCoaseguroAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CoaseguroAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Coinsurance Professional.
		@param CoaseguroMed 
		Coinsurance Professional
	  */
	public void setCoaseguroMed (BigDecimal CoaseguroMed)
	{
		set_Value (COLUMNNAME_CoaseguroMed, CoaseguroMed);
	}

	/** Get Coinsurance Professional.
		@return Coinsurance Professional
	  */
	public BigDecimal getCoaseguroMed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CoaseguroMed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Professional coinsurance.
		@param CoaseguroMedAmt Professional coinsurance	  */
	public void setCoaseguroMedAmt (BigDecimal CoaseguroMedAmt)
	{
		if (CoaseguroMedAmt == null)
			throw new IllegalArgumentException ("CoaseguroMedAmt is mandatory.");
		set_Value (COLUMNNAME_CoaseguroMedAmt, CoaseguroMedAmt);
	}

	/** Get Professional coinsurance.
		@return Professional coinsurance	  */
	public BigDecimal getCoaseguroMedAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CoaseguroMedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** ConfType AD_Reference_ID=1200425 */
	public static final int CONFTYPE_AD_Reference_ID=1200425;
	/** Surveillance Report = W */
	public static final String CONFTYPE_SurveillanceReport = "W";
	/** Clinical Record CDA = C */
	public static final String CONFTYPE_ClinicalRecordCDA = "C";
	/** Clinical Record CCR = R */
	public static final String CONFTYPE_ClinicalRecordCCR = "R";
	/** Elegibility = E */
	public static final String CONFTYPE_Elegibility = "E";
	/** Reportable Lab Results = L */
	public static final String CONFTYPE_ReportableLabResults = "L";
	/** Inmunization Messaging = I */
	public static final String CONFTYPE_InmunizationMessaging = "I";
	/** RX Script = S */
	public static final String CONFTYPE_RXScript = "S";
	/** LabDAQ = Q */
	public static final String CONFTYPE_LabDAQ = "Q";
	/** Professional Claim = P */
	public static final String CONFTYPE_ProfessionalClaim = "P";
	/** Institutional Claim = T */
	public static final String CONFTYPE_InstitutionalClaim = "T";
	/** PIStatement = A */
	public static final String CONFTYPE_PIStatement = "A";
	/** Both Claim Types = B */
	public static final String CONFTYPE_BothClaimTypes = "B";
	/** Inpatient Claim = N */
	public static final String CONFTYPE_InpatientClaim = "N";
	/** Outpatient Institutional Claim = O */
	public static final String CONFTYPE_OutpatientInstitutionalClaim = "O";
	/** Outpatient Professional Claim = F */
	public static final String CONFTYPE_OutpatientProfessionalClaim = "F";
	/** Radiology Messaging = D */
	public static final String CONFTYPE_RadiologyMessaging = "D";
	/** Set Configuration Type.
		@param ConfType Configuration Type	  */
	public void setConfType (String ConfType)
	{

		if (ConfType == null || ConfType.equals("W") || ConfType.equals("C") || ConfType.equals("R") || ConfType.equals("E") || ConfType.equals("L") || ConfType.equals("I") || ConfType.equals("S") || ConfType.equals("Q") || ConfType.equals("P") || ConfType.equals("T") || ConfType.equals("A") || ConfType.equals("B") || ConfType.equals("N") || ConfType.equals("O") || ConfType.equals("F") || ConfType.equals("D")); else throw new IllegalArgumentException ("ConfType Invalid value - " + ConfType + " - Reference_ID=1200425 - W - C - R - E - L - I - S - Q - P - T - A - B - N - O - F - D");		set_ValueNoCheck (COLUMNNAME_ConfType, ConfType);
	}

	/** Get Configuration Type.
		@return Configuration Type	  */
	public String getConfType () 
	{
		return (String)get_Value(COLUMNNAME_ConfType);
	}

	/** Set CoPay.
		@param Copago CoPay	  */
	public void setCopago (BigDecimal Copago)
	{
		set_Value (COLUMNNAME_Copago, Copago);
	}

	/** Get CoPay.
		@return CoPay	  */
	public BigDecimal getCopago () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Copago);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Copayment.
		@param CopagoAmt Copayment	  */
	public void setCopagoAmt (BigDecimal CopagoAmt)
	{
		if (CopagoAmt == null)
			throw new IllegalArgumentException ("CopagoAmt is mandatory.");
		set_Value (COLUMNNAME_CopagoAmt, CopagoAmt);
	}

	/** Get Copayment.
		@return Copayment	  */
	public BigDecimal getCopagoAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CopagoAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		if (DateAcct == null)
			throw new IllegalArgumentException ("DateAcct is mandatory.");
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		if (DateOrdered == null)
			throw new IllegalArgumentException ("DateOrdered is mandatory.");
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Deductible.
		@param Deducible 
		Deductible
	  */
	public void setDeducible (BigDecimal Deducible)
	{
		if (Deducible == null)
			throw new IllegalArgumentException ("Deducible is mandatory.");
		set_Value (COLUMNNAME_Deducible, Deducible);
	}

	/** Get Deductible.
		@return Deductible
	  */
	public BigDecimal getDeducible () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Deducible);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Deductible.
		@param DeducibleAmt Deductible	  */
	public void setDeducibleAmt (BigDecimal DeducibleAmt)
	{
		if (DeducibleAmt == null)
			throw new IllegalArgumentException ("DeducibleAmt is mandatory.");
		set_Value (COLUMNNAME_DeducibleAmt, DeducibleAmt);
	}

	/** Get Deductible.
		@return Deductible	  */
	public BigDecimal getDeducibleAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeducibleAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Global Discount.
		@param DesctoGlobal 
		Global Discount
	  */
	public void setDesctoGlobal (BigDecimal DesctoGlobal)
	{
		if (DesctoGlobal == null)
			throw new IllegalArgumentException ("DesctoGlobal is mandatory.");
		set_Value (COLUMNNAME_DesctoGlobal, DesctoGlobal);
	}

	/** Get Global Discount.
		@return Global Discount
	  */
	public BigDecimal getDesctoGlobal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DesctoGlobal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Global Disct $.
		@param DesctoGlobalAmt 
		Amount of Global Discount
	  */
	public void setDesctoGlobalAmt (BigDecimal DesctoGlobalAmt)
	{
		set_Value (COLUMNNAME_DesctoGlobalAmt, DesctoGlobalAmt);
	}

	/** Get Global Disct $.
		@return Amount of Global Discount
	  */
	public BigDecimal getDesctoGlobalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DesctoGlobalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Discount tax amout.
		@param DiscountTaxAmt Discount tax amout	  */
	public void setDiscountTaxAmt (BigDecimal DiscountTaxAmt)
	{
		if (DiscountTaxAmt == null)
			throw new IllegalArgumentException ("DiscountTaxAmt is mandatory.");
		set_Value (COLUMNNAME_DiscountTaxAmt, DiscountTaxAmt);
	}

	/** Get Discount tax amout.
		@return Discount tax amout	  */
	public BigDecimal getDiscountTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountTaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
		if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");
		if (DocAction.equals("CO") || DocAction.equals("AP") || DocAction.equals("RJ") || DocAction.equals("PO") || DocAction.equals("VO") || DocAction.equals("CL") || DocAction.equals("RC") || DocAction.equals("RA") || DocAction.equals("IN") || DocAction.equals("RE") || DocAction.equals("--") || DocAction.equals("PR") || DocAction.equals("XL") || DocAction.equals("WC")); else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - CO - AP - RJ - PO - VO - CL - RC - RA - IN - RE - -- - PR - XL - WC");		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
		if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
		if (DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("IP") || DocStatus.equals("WP") || DocStatus.equals("WC")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Encounter Extension.
		@param EXME_CtaPacExt_ID 
		Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID)
	{
		if (EXME_CtaPacExt_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacExt_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPacExt_ID, Integer.valueOf(EXME_CtaPacExt_ID));
	}

	/** Get Encounter Extension.
		@return Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacExt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Extension Number.
		@param ExtensionNo 
		Extension Number
	  */
	public void setExtensionNo (int ExtensionNo)
	{
		set_Value (COLUMNNAME_ExtensionNo, Integer.valueOf(ExtensionNo));
	}

	/** Get Extension Number.
		@return Extension Number
	  */
	public int getExtensionNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ExtensionNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Grand Total.
		@param GrandTotal 
		Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal)
	{
		if (GrandTotal == null)
			throw new IllegalArgumentException ("GrandTotal is mandatory.");
		set_Value (COLUMNNAME_GrandTotal, GrandTotal);
	}

	/** Get Grand Total.
		@return Total amount of document
	  */
	public BigDecimal getGrandTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GrandTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** InstitutionalStatus AD_Reference_ID=1200581 */
	public static final int INSTITUTIONALSTATUS_AD_Reference_ID=1200581;
	/** Coding Complete = 4 */
	public static final String INSTITUTIONALSTATUS_CodingComplete = "4";
	/** Coding Incomplete = 3 */
	public static final String INSTITUTIONALSTATUS_CodingIncomplete = "3";
	/** Error, Message 997 = 11 */
	public static final String INSTITUTIONALSTATUS_ErrorMessage997 = "11";
	/** Error, Mandatory Fields = 7 */
	public static final String INSTITUTIONALSTATUS_ErrorMandatoryFields = "7";
	/** Error RSP File = 12 */
	public static final String INSTITUTIONALSTATUS_ErrorRSPFile = "12";
	/** OK, Message 997 = 13 */
	public static final String INSTITUTIONALSTATUS_OKMessage997 = "13";
	/** OK, Mandatory Fields = 10 */
	public static final String INSTITUTIONALSTATUS_OKMandatoryFields = "10";
	/** OK, RSP File = 14 */
	public static final String INSTITUTIONALSTATUS_OKRSPFile = "14";
	/** 835 Received = 18 */
	public static final String INSTITUTIONALSTATUS_835Received = "18";
	/** 835 Received Second Insurance = RSI */
	public static final String INSTITUTIONALSTATUS_835ReceivedSecondInsurance = "RSI";
	/** Returned to Abstracting = 16 */
	public static final String INSTITUTIONALSTATUS_ReturnedToAbstracting = "16";
	/** 835 Received Third Insurance = RTI */
	public static final String INSTITUTIONALSTATUS_835ReceivedThirdInsurance = "RTI";
	/** Waiting Insurance Payments = 15 */
	public static final String INSTITUTIONALSTATUS_WaitingInsurancePayments = "15";
	/** Waiting Guarantor Payments = 25 */
	public static final String INSTITUTIONALSTATUS_WaitingGuarantorPayments = "25";
	/** Waiting 835 Second Insurance = WSI */
	public static final String INSTITUTIONALSTATUS_Waiting835SecondInsurance = "WSI";
	/** Waiting 835 Third Insurance = WTI */
	public static final String INSTITUTIONALSTATUS_Waiting835ThirdInsurance = "WTI";
	/** Not Applicable = 2 */
	public static final String INSTITUTIONALSTATUS_NotApplicable = "2";
	/** Not Billed = 1 */
	public static final String INSTITUTIONALSTATUS_NotBilled = "1";
	/** Error, Orders Incomplete = 5 */
	public static final String INSTITUTIONALSTATUS_ErrorOrdersIncomplete = "5";
	/** Error, Prices in Zero = 6 */
	public static final String INSTITUTIONALSTATUS_ErrorPricesInZero = "6";
	/** OK, Orders Complete = 8 */
	public static final String INSTITUTIONALSTATUS_OKOrdersComplete = "8";
	/** OK, Prices in Zero = 9 */
	public static final String INSTITUTIONALSTATUS_OKPricesInZero = "9";
	/** Returned to Coding = 17 */
	public static final String INSTITUTIONALSTATUS_ReturnedToCoding = "17";
	/** Payment Plan = 23 */
	public static final String INSTITUTIONALSTATUS_PaymentPlan = "23";
	/** Self Pay = 19 */
	public static final String INSTITUTIONALSTATUS_SelfPay = "19";
	/** Bad Debt Collection = 21 */
	public static final String INSTITUTIONALSTATUS_BadDebtCollection = "21";
	/** Early Out = 20 */
	public static final String INSTITUTIONALSTATUS_EarlyOut = "20";
	/** Credit Balance = 22 */
	public static final String INSTITUTIONALSTATUS_CreditBalance = "22";
	/** Zero Balance = 24 */
	public static final String INSTITUTIONALSTATUS_ZeroBalance = "24";
	/** Set Institutional Status.
		@param InstitutionalStatus 
		Institutional Status
	  */
	public void setInstitutionalStatus (String InstitutionalStatus)
	{

		if (InstitutionalStatus == null || InstitutionalStatus.equals("4") || InstitutionalStatus.equals("3") || InstitutionalStatus.equals("11") || InstitutionalStatus.equals("7") || InstitutionalStatus.equals("12") || InstitutionalStatus.equals("13") || InstitutionalStatus.equals("10") || InstitutionalStatus.equals("14") || InstitutionalStatus.equals("18") || InstitutionalStatus.equals("RSI") || InstitutionalStatus.equals("16") || InstitutionalStatus.equals("RTI") || InstitutionalStatus.equals("15") || InstitutionalStatus.equals("25") || InstitutionalStatus.equals("WSI") || InstitutionalStatus.equals("WTI") || InstitutionalStatus.equals("2") || InstitutionalStatus.equals("1") || InstitutionalStatus.equals("5") || InstitutionalStatus.equals("6") || InstitutionalStatus.equals("8") || InstitutionalStatus.equals("9") || InstitutionalStatus.equals("17") || InstitutionalStatus.equals("23") || InstitutionalStatus.equals("19") || InstitutionalStatus.equals("21") || InstitutionalStatus.equals("20") || InstitutionalStatus.equals("22") || InstitutionalStatus.equals("24")); else throw new IllegalArgumentException ("InstitutionalStatus Invalid value - " + InstitutionalStatus + " - Reference_ID=1200581 - 4 - 3 - 11 - 7 - 12 - 13 - 10 - 14 - 18 - RSI - 16 - RTI - 15 - 25 - WSI - WTI - 2 - 1 - 5 - 6 - 8 - 9 - 17 - 23 - 19 - 21 - 20 - 22 - 24");		set_ValueNoCheck (COLUMNNAME_InstitutionalStatus, InstitutionalStatus);
	}

	/** Get Institutional Status.
		@return Institutional Status
	  */
	public String getInstitutionalStatus () 
	{
		return (String)get_Value(COLUMNNAME_InstitutionalStatus);
	}

	/** InstitutionalStep AD_Reference_ID=1200595 */
	public static final int INSTITUTIONALSTEP_AD_Reference_ID=1200595;
	/** Default = D */
	public static final String INSTITUTIONALSTEP_Default = "D";
	/** First Insurance = F */
	public static final String INSTITUTIONALSTEP_FirstInsurance = "F";
	/** Second Insurance = S */
	public static final String INSTITUTIONALSTEP_SecondInsurance = "S";
	/** Third Insurance = T */
	public static final String INSTITUTIONALSTEP_ThirdInsurance = "T";
	/** Other Insurance = O */
	public static final String INSTITUTIONALSTEP_OtherInsurance = "O";
	/** Selft Pay = P */
	public static final String INSTITUTIONALSTEP_SelftPay = "P";
	/** Set Institutional Step.
		@param InstitutionalStep 
		Insurance that has the liability for institutional charges
	  */
	public void setInstitutionalStep (String InstitutionalStep)
	{

		if (InstitutionalStep == null || InstitutionalStep.equals("D") || InstitutionalStep.equals("F") || InstitutionalStep.equals("S") || InstitutionalStep.equals("T") || InstitutionalStep.equals("O") || InstitutionalStep.equals("P")); else throw new IllegalArgumentException ("InstitutionalStep Invalid value - " + InstitutionalStep + " - Reference_ID=1200595 - D - F - S - T - O - P");		set_ValueNoCheck (COLUMNNAME_InstitutionalStep, InstitutionalStep);
	}

	/** Get Institutional Step.
		@return Insurance that has the liability for institutional charges
	  */
	public String getInstitutionalStep () 
	{
		return (String)get_Value(COLUMNNAME_InstitutionalStep);
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percentage Coinsurance.
		@param IsCoasegPercent 
		Percentage Coinsurance, otherwise is an amount
	  */
	public void setIsCoasegPercent (boolean IsCoasegPercent)
	{
		set_Value (COLUMNNAME_IsCoasegPercent, Boolean.valueOf(IsCoasegPercent));
	}

	/** Get Percentage Coinsurance.
		@return Percentage Coinsurance, otherwise is an amount
	  */
	public boolean isCoasegPercent () 
	{
		Object oo = get_Value(COLUMNNAME_IsCoasegPercent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percentage.
		@param IsCoasMedPercent Percentage	  */
	public void setIsCoasMedPercent (boolean IsCoasMedPercent)
	{
		set_Value (COLUMNNAME_IsCoasMedPercent, Boolean.valueOf(IsCoasMedPercent));
	}

	/** Get Percentage.
		@return Percentage	  */
	public boolean isCoasMedPercent () 
	{
		Object oo = get_Value(COLUMNNAME_IsCoasMedPercent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percentage.
		@param IsCopPercent Percentage	  */
	public void setIsCopPercent (boolean IsCopPercent)
	{
		set_Value (COLUMNNAME_IsCopPercent, Boolean.valueOf(IsCopPercent));
	}

	/** Get Percentage.
		@return Percentage	  */
	public boolean isCopPercent () 
	{
		Object oo = get_Value(COLUMNNAME_IsCopPercent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Including deductibles in the coinsurance.
		@param IsDeducInCoaseg 
		Including deductibles in the coinsurance
	  */
	public void setIsDeducInCoaseg (boolean IsDeducInCoaseg)
	{
		set_Value (COLUMNNAME_IsDeducInCoaseg, Boolean.valueOf(IsDeducInCoaseg));
	}

	/** Get Including deductibles in the coinsurance.
		@return Including deductibles in the coinsurance
	  */
	public boolean isDeducInCoaseg () 
	{
		Object oo = get_Value(COLUMNNAME_IsDeducInCoaseg);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percent.
		@param IsDeducPercent Percent	  */
	public void setIsDeducPercent (boolean IsDeducPercent)
	{
		set_Value (COLUMNNAME_IsDeducPercent, Boolean.valueOf(IsDeducPercent));
	}

	/** Get Percent.
		@return Percent	  */
	public boolean isDeducPercent () 
	{
		Object oo = get_Value(COLUMNNAME_IsDeducPercent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percent.
		@param IsDiscPercent Percent	  */
	public void setIsDiscPercent (boolean IsDiscPercent)
	{
		set_Value (COLUMNNAME_IsDiscPercent, Boolean.valueOf(IsDiscPercent));
	}

	/** Get Percent.
		@return Percent	  */
	public boolean isDiscPercent () 
	{
		Object oo = get_Value(COLUMNNAME_IsDiscPercent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sort by Category invoice line.
		@param IsOrderFacLineCategory Sort by Category invoice line	  */
	public void setIsOrderFacLineCategory (boolean IsOrderFacLineCategory)
	{
		set_Value (COLUMNNAME_IsOrderFacLineCategory, Boolean.valueOf(IsOrderFacLineCategory));
	}

	/** Get Sort by Category invoice line.
		@return Sort by Category invoice line	  */
	public boolean isOrderFacLineCategory () 
	{
		Object oo = get_Value(COLUMNNAME_IsOrderFacLineCategory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Observation.
		@param Observacion 
		Observation
	  */
	public void setObservacion (String Observacion)
	{
		set_Value (COLUMNNAME_Observacion, Observacion);
	}

	/** Get Observation.
		@return Observation
	  */
	public String getObservacion () 
	{
		return (String)get_Value(COLUMNNAME_Observacion);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** ProfessionalStatus AD_Reference_ID=1200581 */
	public static final int PROFESSIONALSTATUS_AD_Reference_ID=1200581;
	/** Coding Complete = 4 */
	public static final String PROFESSIONALSTATUS_CodingComplete = "4";
	/** Coding Incomplete = 3 */
	public static final String PROFESSIONALSTATUS_CodingIncomplete = "3";
	/** Error, Message 997 = 11 */
	public static final String PROFESSIONALSTATUS_ErrorMessage997 = "11";
	/** Error, Mandatory Fields = 7 */
	public static final String PROFESSIONALSTATUS_ErrorMandatoryFields = "7";
	/** Error RSP File = 12 */
	public static final String PROFESSIONALSTATUS_ErrorRSPFile = "12";
	/** OK, Message 997 = 13 */
	public static final String PROFESSIONALSTATUS_OKMessage997 = "13";
	/** OK, Mandatory Fields = 10 */
	public static final String PROFESSIONALSTATUS_OKMandatoryFields = "10";
	/** OK, RSP File = 14 */
	public static final String PROFESSIONALSTATUS_OKRSPFile = "14";
	/** 835 Received = 18 */
	public static final String PROFESSIONALSTATUS_835Received = "18";
	/** 835 Received Second Insurance = RSI */
	public static final String PROFESSIONALSTATUS_835ReceivedSecondInsurance = "RSI";
	/** Returned to Abstracting = 16 */
	public static final String PROFESSIONALSTATUS_ReturnedToAbstracting = "16";
	/** 835 Received Third Insurance = RTI */
	public static final String PROFESSIONALSTATUS_835ReceivedThirdInsurance = "RTI";
	/** Waiting Insurance Payments = 15 */
	public static final String PROFESSIONALSTATUS_WaitingInsurancePayments = "15";
	/** Waiting Guarantor Payments = 25 */
	public static final String PROFESSIONALSTATUS_WaitingGuarantorPayments = "25";
	/** Waiting 835 Second Insurance = WSI */
	public static final String PROFESSIONALSTATUS_Waiting835SecondInsurance = "WSI";
	/** Waiting 835 Third Insurance = WTI */
	public static final String PROFESSIONALSTATUS_Waiting835ThirdInsurance = "WTI";
	/** Not Applicable = 2 */
	public static final String PROFESSIONALSTATUS_NotApplicable = "2";
	/** Not Billed = 1 */
	public static final String PROFESSIONALSTATUS_NotBilled = "1";
	/** Error, Orders Incomplete = 5 */
	public static final String PROFESSIONALSTATUS_ErrorOrdersIncomplete = "5";
	/** Error, Prices in Zero = 6 */
	public static final String PROFESSIONALSTATUS_ErrorPricesInZero = "6";
	/** OK, Orders Complete = 8 */
	public static final String PROFESSIONALSTATUS_OKOrdersComplete = "8";
	/** OK, Prices in Zero = 9 */
	public static final String PROFESSIONALSTATUS_OKPricesInZero = "9";
	/** Returned to Coding = 17 */
	public static final String PROFESSIONALSTATUS_ReturnedToCoding = "17";
	/** Payment Plan = 23 */
	public static final String PROFESSIONALSTATUS_PaymentPlan = "23";
	/** Self Pay = 19 */
	public static final String PROFESSIONALSTATUS_SelfPay = "19";
	/** Bad Debt Collection = 21 */
	public static final String PROFESSIONALSTATUS_BadDebtCollection = "21";
	/** Early Out = 20 */
	public static final String PROFESSIONALSTATUS_EarlyOut = "20";
	/** Credit Balance = 22 */
	public static final String PROFESSIONALSTATUS_CreditBalance = "22";
	/** Zero Balance = 24 */
	public static final String PROFESSIONALSTATUS_ZeroBalance = "24";
	/** Set Professional Status.
		@param ProfessionalStatus 
		Professional Status
	  */
	public void setProfessionalStatus (String ProfessionalStatus)
	{

		if (ProfessionalStatus == null || ProfessionalStatus.equals("4") || ProfessionalStatus.equals("3") || ProfessionalStatus.equals("11") || ProfessionalStatus.equals("7") || ProfessionalStatus.equals("12") || ProfessionalStatus.equals("13") || ProfessionalStatus.equals("10") || ProfessionalStatus.equals("14") || ProfessionalStatus.equals("18") || ProfessionalStatus.equals("RSI") || ProfessionalStatus.equals("16") || ProfessionalStatus.equals("RTI") || ProfessionalStatus.equals("15") || ProfessionalStatus.equals("25") || ProfessionalStatus.equals("WSI") || ProfessionalStatus.equals("WTI") || ProfessionalStatus.equals("2") || ProfessionalStatus.equals("1") || ProfessionalStatus.equals("5") || ProfessionalStatus.equals("6") || ProfessionalStatus.equals("8") || ProfessionalStatus.equals("9") || ProfessionalStatus.equals("17") || ProfessionalStatus.equals("23") || ProfessionalStatus.equals("19") || ProfessionalStatus.equals("21") || ProfessionalStatus.equals("20") || ProfessionalStatus.equals("22") || ProfessionalStatus.equals("24")); else throw new IllegalArgumentException ("ProfessionalStatus Invalid value - " + ProfessionalStatus + " - Reference_ID=1200581 - 4 - 3 - 11 - 7 - 12 - 13 - 10 - 14 - 18 - RSI - 16 - RTI - 15 - 25 - WSI - WTI - 2 - 1 - 5 - 6 - 8 - 9 - 17 - 23 - 19 - 21 - 20 - 22 - 24");		set_ValueNoCheck (COLUMNNAME_ProfessionalStatus, ProfessionalStatus);
	}

	/** Get Professional Status.
		@return Professional Status
	  */
	public String getProfessionalStatus () 
	{
		return (String)get_Value(COLUMNNAME_ProfessionalStatus);
	}

	/** ProfessionalStep AD_Reference_ID=1200595 */
	public static final int PROFESSIONALSTEP_AD_Reference_ID=1200595;
	/** Default = D */
	public static final String PROFESSIONALSTEP_Default = "D";
	/** First Insurance = F */
	public static final String PROFESSIONALSTEP_FirstInsurance = "F";
	/** Second Insurance = S */
	public static final String PROFESSIONALSTEP_SecondInsurance = "S";
	/** Third Insurance = T */
	public static final String PROFESSIONALSTEP_ThirdInsurance = "T";
	/** Other Insurance = O */
	public static final String PROFESSIONALSTEP_OtherInsurance = "O";
	/** Selft Pay = P */
	public static final String PROFESSIONALSTEP_SelftPay = "P";
	/** Set Professional Step.
		@param ProfessionalStep 
		Insurance that has the liability for professional charges
	  */
	public void setProfessionalStep (String ProfessionalStep)
	{

		if (ProfessionalStep == null || ProfessionalStep.equals("D") || ProfessionalStep.equals("F") || ProfessionalStep.equals("S") || ProfessionalStep.equals("T") || ProfessionalStep.equals("O") || ProfessionalStep.equals("P")); else throw new IllegalArgumentException ("ProfessionalStep Invalid value - " + ProfessionalStep + " - Reference_ID=1200595 - D - F - S - T - O - P");		set_ValueNoCheck (COLUMNNAME_ProfessionalStep, ProfessionalStep);
	}

	/** Get Professional Step.
		@return Insurance that has the liability for professional charges
	  */
	public String getProfessionalStep () 
	{
		return (String)get_Value(COLUMNNAME_ProfessionalStep);
	}

	/** Set Extension Encounter.
		@param Ref_CtaPacExt_ID 
		Extension Encounter
	  */
	public void setRef_CtaPacExt_ID (int Ref_CtaPacExt_ID)
	{
		if (Ref_CtaPacExt_ID < 1) 
			set_Value (COLUMNNAME_Ref_CtaPacExt_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_CtaPacExt_ID, Integer.valueOf(Ref_CtaPacExt_ID));
	}

	/** Get Extension Encounter.
		@return Extension Encounter
	  */
	public int getRef_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_CtaPacExt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Taxpayer Identification Number.
		@param Rfc 
		Taxpayer Identification Number
	  */
	public void setRfc (String Rfc)
	{
		set_Value (COLUMNNAME_Rfc, Rfc);
	}

	/** Get Taxpayer Identification Number.
		@return Taxpayer Identification Number
	  */
	public String getRfc () 
	{
		return (String)get_Value(COLUMNNAME_Rfc);
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		if (TaxAmt == null)
			throw new IllegalArgumentException ("TaxAmt is mandatory.");
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Lines.
		@param TotalLines 
		Total of all document lines
	  */
	public void setTotalLines (BigDecimal TotalLines)
	{
		if (TotalLines == null)
			throw new IllegalArgumentException ("TotalLines is mandatory.");
		set_Value (COLUMNNAME_TotalLines, TotalLines);
	}

	/** Get Total Lines.
		@return Total of all document lines
	  */
	public BigDecimal getTotalLines () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalLines);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}