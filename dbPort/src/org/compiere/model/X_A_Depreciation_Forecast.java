/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Depreciation_Forecast
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_A_Depreciation_Forecast extends PO implements I_A_Depreciation_Forecast, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Depreciation_Forecast (Properties ctx, int A_Depreciation_Forecast_ID, String trxName)
    {
      super (ctx, A_Depreciation_Forecast_ID, trxName);
      /** if (A_Depreciation_Forecast_ID == 0)
        {
			setA_Depreciation_Forecast_ID (0);
			setA_End_Asset_ID (0);
			setA_Start_Asset_ID (0);
			setDateDoc (new Timestamp( System.currentTimeMillis() ));
			setPostingType (null);
        } */
    }

    /** Load Constructor */
    public X_A_Depreciation_Forecast (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_A_Depreciation_Forecast[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Depreciation Forecast ID.
		@param A_Depreciation_Forecast_ID Depreciation Forecast ID	  */
	public void setA_Depreciation_Forecast_ID (int A_Depreciation_Forecast_ID)
	{
		if (A_Depreciation_Forecast_ID < 1)
			 throw new IllegalArgumentException ("A_Depreciation_Forecast_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Depreciation_Forecast_ID, Integer.valueOf(A_Depreciation_Forecast_ID));
	}

	/** Get Depreciation Forecast ID.
		@return Depreciation Forecast ID	  */
	public int getA_Depreciation_Forecast_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Forecast_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Depreciation_Forecast_ID()));
    }

	/** Set End Asset ID.
		@param A_End_Asset_ID End Asset ID	  */
	public void setA_End_Asset_ID (int A_End_Asset_ID)
	{
		if (A_End_Asset_ID < 1)
			 throw new IllegalArgumentException ("A_End_Asset_ID is mandatory.");
		set_Value (COLUMNNAME_A_End_Asset_ID, Integer.valueOf(A_End_Asset_ID));
	}

	/** Get End Asset ID.
		@return End Asset ID	  */
	public int getA_End_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_End_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Start Asset ID.
		@param A_Start_Asset_ID Start Asset ID	  */
	public void setA_Start_Asset_ID (int A_Start_Asset_ID)
	{
		if (A_Start_Asset_ID < 1)
			 throw new IllegalArgumentException ("A_Start_Asset_ID is mandatory.");
		set_Value (COLUMNNAME_A_Start_Asset_ID, Integer.valueOf(A_Start_Asset_ID));
	}

	/** Get Start Asset ID.
		@return Start Asset ID	  */
	public int getA_Start_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Start_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		if (DateDoc == null)
			throw new IllegalArgumentException ("DateDoc is mandatory.");
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
	}

	/** PostingType AD_Reference_ID=125 */
	public static final int POSTINGTYPE_AD_Reference_ID=125;
	/** Actual = A */
	public static final String POSTINGTYPE_Actual = "A";
	/** Budget = B */
	public static final String POSTINGTYPE_Budget = "B";
	/** Commitment = E */
	public static final String POSTINGTYPE_Commitment = "E";
	/** Statistical = S */
	public static final String POSTINGTYPE_Statistical = "S";
	/** Reservation = R */
	public static final String POSTINGTYPE_Reservation = "R";
	/** Set Posting Type.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{
		if (PostingType == null) throw new IllegalArgumentException ("PostingType is mandatory");
		if (PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("S") || PostingType.equals("R")); else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - S - R");		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get Posting Type.
		@return The type of posted amount for the transaction
	  */
	public String getPostingType () 
	{
		return (String)get_Value(COLUMNNAME_PostingType);
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
}