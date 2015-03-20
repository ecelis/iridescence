/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_CDS
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CDS extends PO implements I_EXME_CDS, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CDS (Properties ctx, int EXME_CDS_ID, String trxName)
    {
      super (ctx, EXME_CDS_ID, trxName);
      /** if (EXME_CDS_ID == 0)
        {
			setEXME_CDS_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CDS (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CDS[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Alert Message.
		@param AlertMessage 
		Alert Message
	  */
	public void setAlertMessage (String AlertMessage)
	{
		set_Value (COLUMNNAME_AlertMessage, AlertMessage);
	}

	/** Get Alert Message.
		@return Alert Message
	  */
	public String getAlertMessage () 
	{
		return (String)get_Value(COLUMNNAME_AlertMessage);
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

	/** Set Clinical Decision Support.
		@param EXME_CDS_ID 
		Clinical Decision Support
	  */
	public void setEXME_CDS_ID (int EXME_CDS_ID)
	{
		if (EXME_CDS_ID < 1)
			 throw new IllegalArgumentException ("EXME_CDS_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CDS_ID, Integer.valueOf(EXME_CDS_ID));
	}

	/** Get Clinical Decision Support.
		@return Clinical Decision Support
	  */
	public int getEXME_CDS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CDS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Type AD_Reference_ID=1200463 */
	public static final int TYPE_AD_Reference_ID=1200463;
	/** Alert = A */
	public static final String TYPE_Alert = "A";
	/** Reminder = R */
	public static final String TYPE_Reminder = "R";
	/** Both = B */
	public static final String TYPE_Both = "B";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("A") || Type.equals("R") || Type.equals("B")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200463 - A - R - B");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}