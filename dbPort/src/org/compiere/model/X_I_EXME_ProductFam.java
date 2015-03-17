/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for I_EXME_ProductFam
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_ProductFam extends PO implements I_I_EXME_ProductFam, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_ProductFam (Properties ctx, int I_EXME_ProductFam_ID, String trxName)
    {
      super (ctx, I_EXME_ProductFam_ID, trxName);
      /** if (I_EXME_ProductFam_ID == 0)
        {
			setI_EXME_ProductFam_ID (0);
			setI_IsImported (false);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_ProductFam (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_ProductFam[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Family Products.
		@param EXME_ProductFam_ID 
		Family Products
	  */
	public void setEXME_ProductFam_ID (int EXME_ProductFam_ID)
	{
		if (EXME_ProductFam_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProductFam_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProductFam_ID, Integer.valueOf(EXME_ProductFam_ID));
	}

	/** Get Family Products.
		@return Family Products
	  */
	public int getEXME_ProductFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductFam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported Product Familly.
		@param I_EXME_ProductFam_ID 
		Imported Product Family
	  */
	public void setI_EXME_ProductFam_ID (int I_EXME_ProductFam_ID)
	{
		if (I_EXME_ProductFam_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_ProductFam_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_ProductFam_ID, Integer.valueOf(I_EXME_ProductFam_ID));
	}

	/** Get Imported Product Familly.
		@return Imported Product Family
	  */
	public int getI_EXME_ProductFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_ProductFam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
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