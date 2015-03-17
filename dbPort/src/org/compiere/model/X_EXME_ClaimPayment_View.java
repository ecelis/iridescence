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

/** Generated Model for EXME_ClaimPayment_View
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ClaimPayment_View extends PO implements I_EXME_ClaimPayment_View, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ClaimPayment_View (Properties ctx, int EXME_ClaimPayment_View_ID, String trxName)
    {
      super (ctx, EXME_ClaimPayment_View_ID, trxName);
      /** if (EXME_ClaimPayment_View_ID == 0)
        {
			setClaim_Type (null);
			setDate_Created (new Timestamp( System.currentTimeMillis() ));
			setEXME_ClaimPayment_View_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ClaimPayment_View (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ClaimPayment_View[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Claim_Type.
		@param Claim_Type Claim_Type	  */
	public void setClaim_Type (String Claim_Type)
	{
		if (Claim_Type == null)
			throw new IllegalArgumentException ("Claim_Type is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Claim_Type, Claim_Type);
	}

	/** Get Claim_Type.
		@return Claim_Type	  */
	public String getClaim_Type () 
	{
		return (String)get_Value(COLUMNNAME_Claim_Type);
	}

	/** Set Date_Created.
		@param Date_Created Date_Created	  */
	public void setDate_Created (Timestamp Date_Created)
	{
		if (Date_Created == null)
			throw new IllegalArgumentException ("Date_Created is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Date_Created, Date_Created);
	}

	/** Get Date_Created.
		@return Date_Created	  */
	public Timestamp getDate_Created () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Created);
	}

	/** Set Date_Received.
		@param Date_Received Date_Received	  */
	public void setDate_Received (Timestamp Date_Received)
	{
		set_ValueNoCheck (COLUMNNAME_Date_Received, Date_Received);
	}

	/** Get Date_Received.
		@return Date_Received	  */
	public Timestamp getDate_Received () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Received);
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set EXME_ClaimPayment_View_ID.
		@param EXME_ClaimPayment_View_ID EXME_ClaimPayment_View_ID	  */
	public void setEXME_ClaimPayment_View_ID (int EXME_ClaimPayment_View_ID)
	{
		if (EXME_ClaimPayment_View_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClaimPayment_View_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ClaimPayment_View_ID, Integer.valueOf(EXME_ClaimPayment_View_ID));
	}

	/** Get EXME_ClaimPayment_View_ID.
		@return EXME_ClaimPayment_View_ID	  */
	public int getEXME_ClaimPayment_View_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimPayment_View_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Insurance.
		@param Insurance Insurance	  */
	public void setInsurance (String Insurance)
	{
		set_ValueNoCheck (COLUMNNAME_Insurance, Insurance);
	}

	/** Get Insurance.
		@return Insurance	  */
	public String getInsurance () 
	{
		return (String)get_Value(COLUMNNAME_Insurance);
	}

	/** Set Payment amount.
		@param PayAmt 
		Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt)
	{
		set_Value (COLUMNNAME_PayAmt, PayAmt);
	}

	/** Get Payment amount.
		@return Amount being paid
	  */
	public BigDecimal getPayAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PayAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Tot_Encounters.
		@param Tot_Encounters Tot_Encounters	  */
	public void setTot_Encounters (BigDecimal Tot_Encounters)
	{
		set_ValueNoCheck (COLUMNNAME_Tot_Encounters, Tot_Encounters);
	}

	/** Get Tot_Encounters.
		@return Tot_Encounters	  */
	public BigDecimal getTot_Encounters () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Tot_Encounters);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}