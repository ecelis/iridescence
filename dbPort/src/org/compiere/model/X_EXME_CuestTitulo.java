/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_CuestTitulo
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_CuestTitulo extends PO implements I_EXME_CuestTitulo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CuestTitulo (Properties ctx, int EXME_CuestTitulo_ID, String trxName)
    {
      super (ctx, EXME_CuestTitulo_ID, trxName);
      /** if (EXME_CuestTitulo_ID == 0)
        {
			setEXME_Cuestionario_ID (0);
			setEXME_CuestTitulo_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_CuestTitulo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CuestTitulo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cuestionario_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public int getEXME_Cuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Title.
		@param EXME_CuestTitulo_ID Title	  */
	public void setEXME_CuestTitulo_ID (int EXME_CuestTitulo_ID)
	{
		if (EXME_CuestTitulo_ID < 1)
			 throw new IllegalArgumentException ("EXME_CuestTitulo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CuestTitulo_ID, Integer.valueOf(EXME_CuestTitulo_ID));
	}

	/** Get Title.
		@return Title	  */
	public int getEXME_CuestTitulo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CuestTitulo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is OBX Reviewed.
		@param IsOBXReviewed 
		Is OBX Reviewed
	  */
	public void setIsOBXReviewed (boolean IsOBXReviewed)
	{
		set_Value (COLUMNNAME_IsOBXReviewed, Boolean.valueOf(IsOBXReviewed));
	}

	/** Get Is OBX Reviewed.
		@return Is OBX Reviewed
	  */
	public boolean isOBXReviewed () 
	{
		Object oo = get_Value(COLUMNNAME_IsOBXReviewed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
}