/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_MergeLexi
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_MergeLexi extends PO implements I_EXME_MergeLexi, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MergeLexi (Properties ctx, int EXME_MergeLexi_ID, String trxName)
    {
      super (ctx, EXME_MergeLexi_ID, trxName);
      /** if (EXME_MergeLexi_ID == 0)
        {
			setEXME_MergeLexi_ID (0);
			setM_Product_ID (0);
			setM_Product_Lexi_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MergeLexi (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_MergeLexi[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set EXME_MergeLexi_ID.
		@param EXME_MergeLexi_ID EXME_MergeLexi_ID	  */
	public void setEXME_MergeLexi_ID (int EXME_MergeLexi_ID)
	{
		if (EXME_MergeLexi_ID < 1)
			 throw new IllegalArgumentException ("EXME_MergeLexi_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MergeLexi_ID, Integer.valueOf(EXME_MergeLexi_ID));
	}

	/** Get EXME_MergeLexi_ID.
		@return EXME_MergeLexi_ID	  */
	public int getEXME_MergeLexi_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MergeLexi_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Merged AD_Reference_ID=1200658 */
	public static final int MERGED_AD_Reference_ID=1200658;
	/** MergeLexiPending = P */
	public static final String MERGED_MergeLexiPending = "P";
	/** MergeLexiMerged = M */
	public static final String MERGED_MergeLexiMerged = "M";
	/** Set Merged.
		@param Merged Merged	  */
	public void setMerged (String Merged)
	{

		if (Merged == null || Merged.equals("P") || Merged.equals("M")); else throw new IllegalArgumentException ("Merged Invalid value - " + Merged + " - Reference_ID=1200658 - P - M");		set_Value (COLUMNNAME_Merged, Merged);
	}

	/** Get Merged.
		@return Merged	  */
	public String getMerged () 
	{
		return (String)get_Value(COLUMNNAME_Merged);
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_Product_Lexi_ID.
		@param M_Product_Lexi_ID M_Product_Lexi_ID	  */
	public void setM_Product_Lexi_ID (int M_Product_Lexi_ID)
	{
		if (M_Product_Lexi_ID < 1)
			 throw new IllegalArgumentException ("M_Product_Lexi_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_Lexi_ID, Integer.valueOf(M_Product_Lexi_ID));
	}

	/** Get M_Product_Lexi_ID.
		@return M_Product_Lexi_ID	  */
	public int getM_Product_Lexi_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Lexi_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}