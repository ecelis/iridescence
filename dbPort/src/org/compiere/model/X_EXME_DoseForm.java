/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_DoseForm
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_DoseForm extends PO implements I_EXME_DoseForm, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DoseForm (Properties ctx, int EXME_DoseForm_ID, String trxName)
    {
      super (ctx, EXME_DoseForm_ID, trxName);
      /** if (EXME_DoseForm_ID == 0)
        {
			setEXME_DoseForm_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_DoseForm (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_DoseForm[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Abbreviation.
		@param Abrev 
		Abbreviation
	  */
	public void setAbrev (String Abrev)
	{
		set_Value (COLUMNNAME_Abrev, Abrev);
	}

	/** Get Abbreviation.
		@return Abbreviation
	  */
	public String getAbrev () 
	{
		return (String)get_Value(COLUMNNAME_Abrev);
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

	/** Set DfID.
		@param DfID DfID	  */
	public void setDfID (int DfID)
	{
		set_Value (COLUMNNAME_DfID, Integer.valueOf(DfID));
	}

	/** Get DfID.
		@return DfID	  */
	public int getDfID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DfID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DoseForm.
		@param EXME_DoseForm_ID 
		DoseForm
	  */
	public void setEXME_DoseForm_ID (int EXME_DoseForm_ID)
	{
		if (EXME_DoseForm_ID < 1)
			 throw new IllegalArgumentException ("EXME_DoseForm_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DoseForm_ID, Integer.valueOf(EXME_DoseForm_ID));
	}

	/** Get DoseForm.
		@return DoseForm
	  */
	public int getEXME_DoseForm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DoseForm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set NCITCode.
		@param NCITCode 
		National Cancer Institute thesaurus Code
	  */
	public void setNCITCode (String NCITCode)
	{
		set_Value (COLUMNNAME_NCITCode, NCITCode);
	}

	/** Get NCITCode.
		@return National Cancer Institute thesaurus Code
	  */
	public String getNCITCode () 
	{
		return (String)get_Value(COLUMNNAME_NCITCode);
	}
}