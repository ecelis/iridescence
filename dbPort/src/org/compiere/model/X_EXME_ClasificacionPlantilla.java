/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ClasificacionPlantilla
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ClasificacionPlantilla extends PO implements I_EXME_ClasificacionPlantilla, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ClasificacionPlantilla (Properties ctx, int EXME_ClasificacionPlantilla_ID, String trxName)
    {
      super (ctx, EXME_ClasificacionPlantilla_ID, trxName);
      /** if (EXME_ClasificacionPlantilla_ID == 0)
        {
			setEXME_ClasificacionPlantilla_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ClasificacionPlantilla (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ClasificacionPlantilla[")
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

	/** Set Classification Template.
		@param EXME_ClasificacionPlantilla_ID Classification Template	  */
	public void setEXME_ClasificacionPlantilla_ID (int EXME_ClasificacionPlantilla_ID)
	{
		if (EXME_ClasificacionPlantilla_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClasificacionPlantilla_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ClasificacionPlantilla_ID, Integer.valueOf(EXME_ClasificacionPlantilla_ID));
	}

	/** Get Classification Template.
		@return Classification Template	  */
	public int getEXME_ClasificacionPlantilla_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasificacionPlantilla_ID);
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
}