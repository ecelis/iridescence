/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Emergencia_Usuario
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Emergencia_Usuario extends PO implements I_EXME_Emergencia_Usuario, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Emergencia_Usuario (Properties ctx, int EXME_Emergencia_Usuario_ID, String trxName)
    {
      super (ctx, EXME_Emergencia_Usuario_ID, trxName);
      /** if (EXME_Emergencia_Usuario_ID == 0)
        {
			setAD_User_ID (0);
			setEXME_Emergencia_Usuario_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Emergencia_Usuario (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Emergencia_Usuario[")
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

	/** Set Emergency User.
		@param EXME_Emergencia_Usuario_ID Emergency User	  */
	public void setEXME_Emergencia_Usuario_ID (int EXME_Emergencia_Usuario_ID)
	{
		if (EXME_Emergencia_Usuario_ID < 1)
			 throw new IllegalArgumentException ("EXME_Emergencia_Usuario_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Emergencia_Usuario_ID, Integer.valueOf(EXME_Emergencia_Usuario_ID));
	}

	/** Get Emergency User.
		@return Emergency User	  */
	public int getEXME_Emergencia_Usuario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Emergencia_Usuario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}