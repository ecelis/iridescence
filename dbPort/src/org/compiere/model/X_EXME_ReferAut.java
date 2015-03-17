/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ReferAut
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ReferAut extends PO implements I_EXME_ReferAut, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ReferAut (Properties ctx, int EXME_ReferAut_ID, String trxName)
    {
      super (ctx, EXME_ReferAut_ID, trxName);
      /** if (EXME_ReferAut_ID == 0)
        {
			setAD_User_ID (0);
			setEXME_ReferAut_ID (0);
			setEXME_ReferMot_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ReferAut (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ReferAut[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Reference.
		@param EXME_ReferAut_ID 
		Reference
	  */
	public void setEXME_ReferAut_ID (int EXME_ReferAut_ID)
	{
		if (EXME_ReferAut_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReferAut_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ReferAut_ID, Integer.valueOf(EXME_ReferAut_ID));
	}

	/** Get Reference.
		@return Reference
	  */
	public int getEXME_ReferAut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReferAut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reference Motive.
		@param EXME_ReferMot_ID 
		Reference Motive
	  */
	public void setEXME_ReferMot_ID (int EXME_ReferMot_ID)
	{
		if (EXME_ReferMot_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReferMot_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ReferMot_ID, Integer.valueOf(EXME_ReferMot_ID));
	}

	/** Get Reference Motive.
		@return Reference Motive
	  */
	public int getEXME_ReferMot_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReferMot_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}