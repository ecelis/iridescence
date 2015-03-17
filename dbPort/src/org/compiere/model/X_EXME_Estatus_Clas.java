/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Estatus_Clas
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Estatus_Clas extends PO implements I_EXME_Estatus_Clas, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Estatus_Clas (Properties ctx, int EXME_Estatus_Clas_ID, String trxName)
    {
      super (ctx, EXME_Estatus_Clas_ID, trxName);
      /** if (EXME_Estatus_Clas_ID == 0)
        {
			setEstatus (null);
			setEXME_Estatus_Clas_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Estatus_Clas (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Estatus_Clas[")
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

	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		if (Estatus == null)
			throw new IllegalArgumentException ("Estatus is mandatory.");
		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set Status.
		@param EXME_Estatus_Clas_ID 
		Status
	  */
	public void setEXME_Estatus_Clas_ID (int EXME_Estatus_Clas_ID)
	{
		if (EXME_Estatus_Clas_ID < 1)
			 throw new IllegalArgumentException ("EXME_Estatus_Clas_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Estatus_Clas_ID, Integer.valueOf(EXME_Estatus_Clas_ID));
	}

	/** Get Status.
		@return Status
	  */
	public int getEXME_Estatus_Clas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Estatus_Clas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}