/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_RoleType
 *  @author Generated Class 
 *  @version Release 6.0 - $Id$ */
public class X_EXME_RoleType extends PO implements I_EXME_RoleType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RoleType (Properties ctx, int EXME_RoleType_ID, String trxName)
    {
      super (ctx, EXME_RoleType_ID, trxName);
      /** if (EXME_RoleType_ID == 0)
        {
			setEXME_RoleType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_RoleType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RoleType[")
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

	/** Set Role Type.
		@param EXME_RoleType_ID Role Type	  */
	public void setEXME_RoleType_ID (int EXME_RoleType_ID)
	{
		if (EXME_RoleType_ID < 1)
			 throw new IllegalArgumentException ("EXME_RoleType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RoleType_ID, Integer.valueOf(EXME_RoleType_ID));
	}

	/** Get Role Type.
		@return Role Type	  */
	public int getEXME_RoleType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RoleType_ID);
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