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

/** Generated Model for EXME_ClaimPaymentH
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ClaimPaymentH extends PO implements I_EXME_ClaimPaymentH, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ClaimPaymentH (Properties ctx, int EXME_ClaimPaymentH_ID, String trxName)
    {
      super (ctx, EXME_ClaimPaymentH_ID, trxName);
      /** if (EXME_ClaimPaymentH_ID == 0)
        {
			setEXME_ClaimPaymentH_ID (0);
			setIsManual (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ClaimPaymentH (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ClaimPaymentH[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** BillingType AD_Reference_ID=1200579 */
	public static final int BILLINGTYPE_AD_Reference_ID=1200579;
	/** Technical = T */
	public static final String BILLINGTYPE_Technical = "T";
	/** Professional = P */
	public static final String BILLINGTYPE_Professional = "P";
	/** Set Billing type.
		@param BillingType Billing type	  */
	public void setBillingType (String BillingType)
	{

		if (BillingType == null || BillingType.equals("T") || BillingType.equals("P")); else throw new IllegalArgumentException ("BillingType Invalid value - " + BillingType + " - Reference_ID=1200579 - T - P");		set_Value (COLUMNNAME_BillingType, BillingType);
	}

	/** Get Billing type.
		@return Billing type	  */
	public String getBillingType () 
	{
		return (String)get_Value(COLUMNNAME_BillingType);
	}

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
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

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_Value (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Work Start.
		@param DateWorkStart 
		Date when work is (planned to be) started
	  */
	public void setDateWorkStart (Timestamp DateWorkStart)
	{
		set_Value (COLUMNNAME_DateWorkStart, DateWorkStart);
	}

	/** Get Work Start.
		@return Date when work is (planned to be) started
	  */
	public Timestamp getDateWorkStart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateWorkStart);
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set EFT Check No.
		@param EftCheckNo 
		Electronic Funds Transfer Check No
	  */
	public void setEftCheckNo (String EftCheckNo)
	{
		set_Value (COLUMNNAME_EftCheckNo, EftCheckNo);
	}

	/** Get EFT Check No.
		@return Electronic Funds Transfer Check No
	  */
	public String getEftCheckNo () 
	{
		return (String)get_Value(COLUMNNAME_EftCheckNo);
	}

	/** Set Payments Claim Header.
		@param EXME_ClaimPaymentH_ID 
		Payments Claim Header
	  */
	public void setEXME_ClaimPaymentH_ID (int EXME_ClaimPaymentH_ID)
	{
		if (EXME_ClaimPaymentH_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClaimPaymentH_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ClaimPaymentH_ID, Integer.valueOf(EXME_ClaimPaymentH_ID));
	}

	/** Get Payments Claim Header.
		@return Payments Claim Header
	  */
	public int getEXME_ClaimPaymentH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimPaymentH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ClaimPayment_S getEXME_ClaimPayment_S() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClaimPayment_S.Table_Name);
        I_EXME_ClaimPayment_S result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClaimPayment_S)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClaimPayment_S_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Claim Payment.
		@param EXME_ClaimPayment_S_ID 
		ID to store Claim Payments from EXME_ClaimPaymentH
	  */
	public void setEXME_ClaimPayment_S_ID (int EXME_ClaimPayment_S_ID)
	{
		if (EXME_ClaimPayment_S_ID < 1) 
			set_Value (COLUMNNAME_EXME_ClaimPayment_S_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ClaimPayment_S_ID, Integer.valueOf(EXME_ClaimPayment_S_ID));
	}

	/** Get Claim Payment.
		@return ID to store Claim Payments from EXME_ClaimPaymentH
	  */
	public int getEXME_ClaimPayment_S_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimPayment_S_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set isInterface.
		@param isInterface isInterface	  */
	public void setisInterface (boolean isInterface)
	{
		set_Value (COLUMNNAME_isInterface, Boolean.valueOf(isInterface));
	}

	/** Get isInterface.
		@return isInterface	  */
	public boolean isInterface () 
	{
		Object oo = get_Value(COLUMNNAME_isInterface);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Manual.
		@param IsManual 
		This is a manual process
	  */
	public void setIsManual (boolean IsManual)
	{
		set_Value (COLUMNNAME_IsManual, Boolean.valueOf(IsManual));
	}

	/** Get Manual.
		@return This is a manual process
	  */
	public boolean isManual () 
	{
		Object oo = get_Value(COLUMNNAME_IsManual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Paid Amount.
		@param PaidAmt Paid Amount	  */
	public void setPaidAmt (BigDecimal PaidAmt)
	{
		set_Value (COLUMNNAME_PaidAmt, PaidAmt);
	}

	/** Get Paid Amount.
		@return Paid Amount	  */
	public BigDecimal getPaidAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PaidAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Payment date.
		@param PaymentDate Payment date	  */
	public void setPaymentDate (Timestamp PaymentDate)
	{
		set_Value (COLUMNNAME_PaymentDate, PaymentDate);
	}

	/** Get Payment date.
		@return Payment date	  */
	public Timestamp getPaymentDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PaymentDate);
	}

	/** PaymentType AD_Reference_ID=1200586 */
	public static final int PAYMENTTYPE_AD_Reference_ID=1200586;
	/** Automated Clearing House = ACH */
	public static final String PAYMENTTYPE_AutomatedClearingHouse = "ACH";
	/** Financial Institution Option = BOP */
	public static final String PAYMENTTYPE_FinancialInstitutionOption = "BOP";
	/** Check = CHK */
	public static final String PAYMENTTYPE_Check = "CHK";
	/** Federal Wire Transfer = FWT */
	public static final String PAYMENTTYPE_FederalWireTransfer = "FWT";
	/** Non Payment Data = NON */
	public static final String PAYMENTTYPE_NonPaymentData = "NON";
	/** Set PaymentType.
		@param PaymentType 
		Billing Payment
	  */
	public void setPaymentType (String PaymentType)
	{

		if (PaymentType == null || PaymentType.equals("ACH") || PaymentType.equals("BOP") || PaymentType.equals("CHK") || PaymentType.equals("FWT") || PaymentType.equals("NON")); else throw new IllegalArgumentException ("PaymentType Invalid value - " + PaymentType + " - Reference_ID=1200586 - ACH - BOP - CHK - FWT - NON");		set_Value (COLUMNNAME_PaymentType, PaymentType);
	}

	/** Get PaymentType.
		@return Billing Payment
	  */
	public String getPaymentType () 
	{
		return (String)get_Value(COLUMNNAME_PaymentType);
	}

	/** Set Post date.
		@param PostedDate 
		Post date
	  */
	public void setPostedDate (Timestamp PostedDate)
	{
		set_Value (COLUMNNAME_PostedDate, PostedDate);
	}

	/** Get Post date.
		@return Post date
	  */
	public Timestamp getPostedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PostedDate);
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

	/** Set Reference No.
		@param ReferenceNo 
		Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo)
	{
		set_Value (COLUMNNAME_ReferenceNo, ReferenceNo);
	}

	/** Get Reference No.
		@return Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo () 
	{
		return (String)get_Value(COLUMNNAME_ReferenceNo);
	}

	/** Status AD_Reference_ID=131 */
	public static final int STATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String STATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String STATUS_Completed = "CO";
	/** Approved = AP */
	public static final String STATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String STATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String STATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String STATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String STATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String STATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String STATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String STATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String STATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String STATUS_WaitingConfirmation = "WC";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		if (Status == null || Status.equals("DR") || Status.equals("CO") || Status.equals("AP") || Status.equals("NA") || Status.equals("VO") || Status.equals("IN") || Status.equals("RE") || Status.equals("CL") || Status.equals("??") || Status.equals("IP") || Status.equals("WP") || Status.equals("WC")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Total Amount.
		@param TotalAmt 
		Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt)
	{
		set_Value (COLUMNNAME_TotalAmt, TotalAmt);
	}

	/** Get Total Amount.
		@return Total Amount
	  */
	public BigDecimal getTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}