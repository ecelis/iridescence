/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_MotivoAut
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MotivoAut extends PO implements I_EXME_MotivoAut, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MotivoAut (Properties ctx, int EXME_MotivoAut_ID, String trxName)
    {
      super (ctx, EXME_MotivoAut_ID, trxName);
      /** if (EXME_MotivoAut_ID == 0)
        {
			setAD_User_ID (0);
			setEXME_MotivoAut_ID (0);
			setEXME_MotivoSol_ID (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MotivoAut (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MotivoAut[")
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
		set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	/** Set Motive Authorization Personnel.
		@param EXME_MotivoAut_ID 
		Personnel that authorizes motive of loan request from the physical file
	  */
	public void setEXME_MotivoAut_ID (int EXME_MotivoAut_ID)
	{
		if (EXME_MotivoAut_ID < 1)
			 throw new IllegalArgumentException ("EXME_MotivoAut_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MotivoAut_ID, Integer.valueOf(EXME_MotivoAut_ID));
	}

	/** Get Motive Authorization Personnel.
		@return Personnel that authorizes motive of loan request from the physical file
	  */
	public int getEXME_MotivoAut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoAut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** EXME_MotivoSol_ID AD_Reference_ID=1000081 */
	public static final int EXME_MOTIVOSOL_ID_AD_Reference_ID=1000081;
	/** Set Motive of Request.
		@param EXME_MotivoSol_ID 
		Motive of loan request from the physical file
	  */
	public void setEXME_MotivoSol_ID (String EXME_MotivoSol_ID)
	{
		set_ValueNoCheck (COLUMNNAME_EXME_MotivoSol_ID, EXME_MotivoSol_ID);
	}

	/** Get Motive of Request.
		@return Motive of loan request from the physical file
	  */
	public String getEXME_MotivoSol_ID () 
	{
		return (String)get_Value(COLUMNNAME_EXME_MotivoSol_ID);
	}
}