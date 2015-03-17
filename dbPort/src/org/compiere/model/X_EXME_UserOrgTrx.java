/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_UserOrgTrx
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_UserOrgTrx extends PO implements I_EXME_UserOrgTrx, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_UserOrgTrx (Properties ctx, int EXME_UserOrgTrx_ID, String trxName)
    {
      super (ctx, EXME_UserOrgTrx_ID, trxName);
      /** if (EXME_UserOrgTrx_ID == 0)
        {
			setAD_OrgTrx_ID (0);
			setAD_User_ID (0);
			setEXME_UserOrgTrx_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_UserOrgTrx (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_UserOrgTrx[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1)
			 throw new IllegalArgumentException ("AD_OrgTrx_ID is mandatory.");
		set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		throw new IllegalArgumentException ("EMail is virtual column");	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set User Trx Organization.
		@param EXME_UserOrgTrx_ID 
		User Trx Organization
	  */
	public void setEXME_UserOrgTrx_ID (int EXME_UserOrgTrx_ID)
	{
		if (EXME_UserOrgTrx_ID < 1)
			 throw new IllegalArgumentException ("EXME_UserOrgTrx_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_UserOrgTrx_ID, Integer.valueOf(EXME_UserOrgTrx_ID));
	}

	/** Get User Trx Organization.
		@return User Trx Organization
	  */
	public int getEXME_UserOrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_UserOrgTrx_ID);
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
		throw new IllegalArgumentException ("Name is virtual column");	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}
}