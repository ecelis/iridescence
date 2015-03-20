/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ChequeCancelado
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ChequeCancelado extends PO implements I_EXME_ChequeCancelado, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ChequeCancelado (Properties ctx, int EXME_ChequeCancelado_ID, String trxName)
    {
      super (ctx, EXME_ChequeCancelado_ID, trxName);
      /** if (EXME_ChequeCancelado_ID == 0)
        {
			setC_Payment_ID (0);
			setEXME_ChequeCancelado_ID (0);
			setIsCancelled (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ChequeCancelado (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ChequeCancelado[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1)
			 throw new IllegalArgumentException ("C_Payment_ID is mandatory.");
		set_Value (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Cancelled check.
		@param EXME_ChequeCancelado_ID 
		Contains chqueques have been canceled
	  */
	public void setEXME_ChequeCancelado_ID (int EXME_ChequeCancelado_ID)
	{
		if (EXME_ChequeCancelado_ID < 1)
			 throw new IllegalArgumentException ("EXME_ChequeCancelado_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ChequeCancelado_ID, Integer.valueOf(EXME_ChequeCancelado_ID));
	}

	/** Get Cancelled check.
		@return Contains chqueques have been canceled
	  */
	public int getEXME_ChequeCancelado_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ChequeCancelado_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cancelled.
		@param IsCancelled 
		The transaction was cancelled
	  */
	public void setIsCancelled (String IsCancelled)
	{
		if (IsCancelled == null)
			throw new IllegalArgumentException ("IsCancelled is mandatory.");
		set_Value (COLUMNNAME_IsCancelled, IsCancelled);
	}

	/** Get Cancelled.
		@return The transaction was cancelled
	  */
	public String getIsCancelled () 
	{
		return (String)get_Value(COLUMNNAME_IsCancelled);
	}
}