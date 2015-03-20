/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_ClaimPayment_S
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ClaimPayment_S extends PO implements I_EXME_ClaimPayment_S, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ClaimPayment_S (Properties ctx, int EXME_ClaimPayment_S_ID, String trxName)
    {
      super (ctx, EXME_ClaimPayment_S_ID, trxName);
      /** if (EXME_ClaimPayment_S_ID == 0)
        {
			setEXME_ClaimPayment_S_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ClaimPayment_S (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ClaimPayment_S[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Total Quantity.
		@param CantTotal 
		Total Quantity
	  */
	public void setCantTotal (BigDecimal CantTotal)
	{
		set_Value (COLUMNNAME_CantTotal, CantTotal);
	}

	/** Get Total Quantity.
		@return Total Quantity
	  */
	public BigDecimal getCantTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CantTotal);
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

	/** Set Claim Payment.
		@param EXME_ClaimPayment_S_ID 
		ID to store Claim Payments from EXME_ClaimPaymentH
	  */
	public void setEXME_ClaimPayment_S_ID (int EXME_ClaimPayment_S_ID)
	{
		if (EXME_ClaimPayment_S_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClaimPayment_S_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ClaimPayment_S_ID, Integer.valueOf(EXME_ClaimPayment_S_ID));
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
}