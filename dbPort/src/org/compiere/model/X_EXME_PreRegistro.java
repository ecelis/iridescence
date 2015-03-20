/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PreRegistro
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PreRegistro extends PO implements I_EXME_PreRegistro, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PreRegistro (Properties ctx, int EXME_PreRegistro_ID, String trxName)
    {
      super (ctx, EXME_PreRegistro_ID, trxName);
      /** if (EXME_PreRegistro_ID == 0)
        {
			setApellido1 (null);
			setEMail (null);
			setEMailVerify (false);
			setEXME_PreRegistro_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PreRegistro (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PreRegistro[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Last Name.
		@param Apellido1 
		Last Name
	  */
	public void setApellido1 (String Apellido1)
	{
		if (Apellido1 == null)
			throw new IllegalArgumentException ("Apellido1 is mandatory.");
		set_Value (COLUMNNAME_Apellido1, Apellido1);
	}

	/** Get Last Name.
		@return Last Name
	  */
	public String getApellido1 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido1);
	}

	/** Set Mother's Maiden Name.
		@param Apellido2 
		Mother's Maiden Name
	  */
	public void setApellido2 (String Apellido2)
	{
		set_Value (COLUMNNAME_Apellido2, Apellido2);
	}

	/** Get Mother's Maiden Name.
		@return Mother's Maiden Name
	  */
	public String getApellido2 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido2);
	}

	/** Description AD_Reference_ID=1200424 */
	public static final int DESCRIPTION_AD_Reference_ID=1200424;
	/** Website = W */
	public static final String DESCRIPTION_Website = "W";
	/** Friend / Family Member = F */
	public static final String DESCRIPTION_FriendFamilyMember = "F";
	/** Doctor or Nurse = D */
	public static final String DESCRIPTION_DoctorOrNurse = "D";
	/** TV or Radio = T */
	public static final String DESCRIPTION_TVOrRadio = "T";
	/** Other = O */
	public static final String DESCRIPTION_Other = "O";
	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{

		if (Description == null || Description.equals("W") || Description.equals("F") || Description.equals("D") || Description.equals("T") || Description.equals("O")); else throw new IllegalArgumentException ("Description Invalid value - " + Description + " - Reference_ID=1200424 - W - F - D - T - O");		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		if (EMail == null)
			throw new IllegalArgumentException ("EMail is mandatory.");
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Verification Info.
		@param EMailVerify 
		Verification information of EMail Address
	  */
	public void setEMailVerify (boolean EMailVerify)
	{
		set_Value (COLUMNNAME_EMailVerify, Boolean.valueOf(EMailVerify));
	}

	/** Get Verification Info.
		@return Verification information of EMail Address
	  */
	public boolean isEMailVerify () 
	{
		Object oo = get_Value(COLUMNNAME_EMailVerify);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pre-registration.
		@param EXME_PreRegistro_ID 
		Pre-registration
	  */
	public void setEXME_PreRegistro_ID (int EXME_PreRegistro_ID)
	{
		if (EXME_PreRegistro_ID < 1)
			 throw new IllegalArgumentException ("EXME_PreRegistro_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PreRegistro_ID, Integer.valueOf(EXME_PreRegistro_ID));
	}

	/** Get Pre-registration.
		@return Pre-registration
	  */
	public int getEXME_PreRegistro_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreRegistro_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Name 2.
		@param Name2 
		Additional Name
	  */
	public void setName2 (String Name2)
	{
		set_Value (COLUMNNAME_Name2, Name2);
	}

	/** Get Name 2.
		@return Additional Name
	  */
	public String getName2 () 
	{
		return (String)get_Value(COLUMNNAME_Name2);
	}
}