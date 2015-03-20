/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Emergencia_Rol
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Emergencia_Rol extends PO implements I_EXME_Emergencia_Rol, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Emergencia_Rol (Properties ctx, int EXME_Emergencia_Rol_ID, String trxName)
    {
      super (ctx, EXME_Emergencia_Rol_ID, trxName);
      /** if (EXME_Emergencia_Rol_ID == 0)
        {
			setAD_Role_ID (0);
			setEXME_Emergencia_Rol_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Emergencia_Rol (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Emergencia_Rol[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0)
			 throw new IllegalArgumentException ("AD_Role_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Emergency Role.
		@param EXME_Emergencia_Rol_ID Emergency Role	  */
	public void setEXME_Emergencia_Rol_ID (int EXME_Emergencia_Rol_ID)
	{
		if (EXME_Emergencia_Rol_ID < 1)
			 throw new IllegalArgumentException ("EXME_Emergencia_Rol_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Emergencia_Rol_ID, Integer.valueOf(EXME_Emergencia_Rol_ID));
	}

	/** Get Emergency Role.
		@return Emergency Role	  */
	public int getEXME_Emergencia_Rol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Emergencia_Rol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}