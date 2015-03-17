/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PerformingLab
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PerformingLab extends PO implements I_EXME_PerformingLab, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PerformingLab (Properties ctx, int EXME_PerformingLab_ID, String trxName)
    {
      super (ctx, EXME_PerformingLab_ID, trxName);
      /** if (EXME_PerformingLab_ID == 0)
        {
			setC_Location_ID (0);
			setEXME_PerformingLab_ID (0);
			setIsDefault (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PerformingLab (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PerformingLab[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address .
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1)
			 throw new IllegalArgumentException ("C_Location_ID is mandatory.");
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

	/** Set Director_Medico.
		@param Director_Medico 
		Director_Medico
	  */
	public void setDirector_Medico (String Director_Medico)
	{
		set_Value (COLUMNNAME_Director_Medico, Director_Medico);
	}

	/** Get Director_Medico.
		@return Director_Medico
	  */
	public String getDirector_Medico () 
	{
		return (String)get_Value(COLUMNNAME_Director_Medico);
	}

	/** Set Performing Lab.
		@param EXME_PerformingLab_ID Performing Lab	  */
	public void setEXME_PerformingLab_ID (int EXME_PerformingLab_ID)
	{
		if (EXME_PerformingLab_ID < 1)
			 throw new IllegalArgumentException ("EXME_PerformingLab_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PerformingLab_ID, Integer.valueOf(EXME_PerformingLab_ID));
	}

	/** Get Performing Lab.
		@return Performing Lab	  */
	public int getEXME_PerformingLab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PerformingLab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set License.
		@param License License	  */
	public void setLicense (String License)
	{
		set_Value (COLUMNNAME_License, License);
	}

	/** Get License.
		@return License	  */
	public String getLicense () 
	{
		return (String)get_Value(COLUMNNAME_License);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}