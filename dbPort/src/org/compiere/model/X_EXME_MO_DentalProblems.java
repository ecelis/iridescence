/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_MO_DentalProblems
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_DentalProblems extends PO implements I_EXME_MO_DentalProblems, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_DentalProblems (Properties ctx, int EXME_MO_DentalProblems_ID, String trxName)
    {
      super (ctx, EXME_MO_DentalProblems_ID, trxName);
      /** if (EXME_MO_DentalProblems_ID == 0)
        {
			setEXME_MO_DentalProblems_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_DentalProblems (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_DentalProblems[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Color 1.
		@param Color1 Color 1	  */
	public void setColor1 (String Color1)
	{
		set_Value (COLUMNNAME_Color1, Color1);
	}

	/** Get Color 1.
		@return Color 1	  */
	public String getColor1 () 
	{
		return (String)get_Value(COLUMNNAME_Color1);
	}

	/** Set Color 2.
		@param Color2 Color 2	  */
	public void setColor2 (String Color2)
	{
		set_Value (COLUMNNAME_Color2, Color2);
	}

	/** Get Color 2.
		@return Color 2	  */
	public String getColor2 () 
	{
		return (String)get_Value(COLUMNNAME_Color2);
	}

	/** Set Color 3.
		@param Color3 Color 3	  */
	public void setColor3 (String Color3)
	{
		set_Value (COLUMNNAME_Color3, Color3);
	}

	/** Get Color 3.
		@return Color 3	  */
	public String getColor3 () 
	{
		return (String)get_Value(COLUMNNAME_Color3);
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

	/** Set Diagram.
		@param Diagrama Diagram	  */
	public void setDiagrama (boolean Diagrama)
	{
		set_Value (COLUMNNAME_Diagrama, Boolean.valueOf(Diagrama));
	}

	/** Get Diagram.
		@return Diagram	  */
	public boolean isDiagrama () 
	{
		Object oo = get_Value(COLUMNNAME_Diagrama);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Odontogram.
		@param EsOdontograma 
		Is Odontogram
	  */
	public void setEsOdontograma (boolean EsOdontograma)
	{
		set_Value (COLUMNNAME_EsOdontograma, Boolean.valueOf(EsOdontograma));
	}

	/** Get Is Odontogram.
		@return Is Odontogram
	  */
	public boolean isEsOdontograma () 
	{
		Object oo = get_Value(COLUMNNAME_EsOdontograma);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Dental Problem.
		@param EXME_MO_DentalProblems_ID Dental Problem	  */
	public void setEXME_MO_DentalProblems_ID (int EXME_MO_DentalProblems_ID)
	{
		if (EXME_MO_DentalProblems_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_DentalProblems_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_DentalProblems_ID, Integer.valueOf(EXME_MO_DentalProblems_ID));
	}

	/** Get Dental Problem.
		@return Dental Problem	  */
	public int getEXME_MO_DentalProblems_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_DentalProblems_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Image.
		@param Imagen 
		Name of stored image
	  */
	public void setImagen (String Imagen)
	{
		set_Value (COLUMNNAME_Imagen, Imagen);
	}

	/** Get Image.
		@return Name of stored image
	  */
	public String getImagen () 
	{
		return (String)get_Value(COLUMNNAME_Imagen);
	}

	/** Set IsNormal.
		@param IsNormal IsNormal	  */
	public void setIsNormal (boolean IsNormal)
	{
		set_Value (COLUMNNAME_IsNormal, Boolean.valueOf(IsNormal));
	}

	/** Get IsNormal.
		@return IsNormal	  */
	public boolean isNormal () 
	{
		Object oo = get_Value(COLUMNNAME_IsNormal);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}