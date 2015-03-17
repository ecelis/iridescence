/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Institucion
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Institucion extends PO implements I_EXME_Institucion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Institucion (Properties ctx, int EXME_Institucion_ID, String trxName)
    {
      super (ctx, EXME_Institucion_ID, trxName);
      /** if (EXME_Institucion_ID == 0)
        {
			setC_Location_ID (0);
			setC_LocationPhys_ID (0);
			setContactName (null);
			setEXME_Institucion_ID (0);
			setLastName (null);
			setName (null);
// --
			setPhone (null);
			setTaxID (null);
			setTaxonomyID (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Institucion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Institucion[")
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

	/** Set Physical Address.
		@param C_LocationPhys_ID Physical Address	  */
	public void setC_LocationPhys_ID (int C_LocationPhys_ID)
	{
		if (C_LocationPhys_ID < 1)
			 throw new IllegalArgumentException ("C_LocationPhys_ID is mandatory.");
		set_Value (COLUMNNAME_C_LocationPhys_ID, Integer.valueOf(C_LocationPhys_ID));
	}

	/** Get Physical Address.
		@return Physical Address	  */
	public int getC_LocationPhys_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocationPhys_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Contact Name.
		@param ContactName 
		Business Partner Contact Name
	  */
	public void setContactName (String ContactName)
	{
		if (ContactName == null)
			throw new IllegalArgumentException ("ContactName is mandatory.");
		set_Value (COLUMNNAME_ContactName, ContactName);
	}

	/** Get Contact Name.
		@return Business Partner Contact Name
	  */
	public String getContactName () 
	{
		return (String)get_Value(COLUMNNAME_ContactName);
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

	/** Set Service Facility.
		@param EXME_Institucion_ID 
		Service Facility
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID)
	{
		if (EXME_Institucion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Institucion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Institucion_ID, Integer.valueOf(EXME_Institucion_ID));
	}

	/** Get Service Facility.
		@return Service Facility
	  */
	public int getEXME_Institucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Institucion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fax Number.
		@param FaxNumber 
		Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public void setFaxNumber (String FaxNumber)
	{
		set_Value (COLUMNNAME_FaxNumber, FaxNumber);
	}

	/** Get Fax Number.
		@return Enter 12 digit fax number CountryCode+AreaCode+Number
	  */
	public String getFaxNumber () 
	{
		return (String)get_Value(COLUMNNAME_FaxNumber);
	}

	/** Set LastName.
		@param LastName LastName	  */
	public void setLastName (String LastName)
	{
		if (LastName == null)
			throw new IllegalArgumentException ("LastName is mandatory.");
		set_Value (COLUMNNAME_LastName, LastName);
	}

	/** Get LastName.
		@return LastName	  */
	public String getLastName () 
	{
		return (String)get_Value(COLUMNNAME_LastName);
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

	/** Set Main Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		if (Phone == null)
			throw new IllegalArgumentException ("Phone is mandatory.");
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Main Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set Tax ID.
		@param TaxID 
		Tax Identification
	  */
	public void setTaxID (String TaxID)
	{
		if (TaxID == null)
			throw new IllegalArgumentException ("TaxID is mandatory.");
		set_Value (COLUMNNAME_TaxID, TaxID);
	}

	/** Get Tax ID.
		@return Tax Identification
	  */
	public String getTaxID () 
	{
		return (String)get_Value(COLUMNNAME_TaxID);
	}

	/** Set TaxonomyID.
		@param TaxonomyID TaxonomyID	  */
	public void setTaxonomyID (String TaxonomyID)
	{
		if (TaxonomyID == null)
			throw new IllegalArgumentException ("TaxonomyID is mandatory.");
		set_Value (COLUMNNAME_TaxonomyID, TaxonomyID);
	}

	/** Get TaxonomyID.
		@return TaxonomyID	  */
	public String getTaxonomyID () 
	{
		return (String)get_Value(COLUMNNAME_TaxonomyID);
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