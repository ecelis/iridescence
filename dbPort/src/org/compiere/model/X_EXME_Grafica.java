/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Grafica
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_Grafica extends PO implements I_EXME_Grafica, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Grafica (Properties ctx, int EXME_Grafica_ID, String trxName)
    {
      super (ctx, EXME_Grafica_ID, trxName);
      /** if (EXME_Grafica_ID == 0)
        {
			setEXME_Grafica_ID (0);
			setEdadFinal (0);
			setEdadInicial (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Grafica (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Grafica[")
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

	/** Set Graphic.
		@param EXME_Grafica_ID Graphic	  */
	public void setEXME_Grafica_ID (int EXME_Grafica_ID)
	{
		if (EXME_Grafica_ID < 1)
			 throw new IllegalArgumentException ("EXME_Grafica_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Grafica_ID, Integer.valueOf(EXME_Grafica_ID));
	}

	/** Get Graphic.
		@return Graphic	  */
	public int getEXME_Grafica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Grafica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Year Maximum.
		@param EdadFinal Year Maximum	  */
	public void setEdadFinal (int EdadFinal)
	{
		set_Value (COLUMNNAME_EdadFinal, Integer.valueOf(EdadFinal));
	}

	/** Get Year Maximum.
		@return Year Maximum	  */
	public int getEdadFinal () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EdadFinal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Year Minimum.
		@param EdadInicial Year Minimum	  */
	public void setEdadInicial (int EdadInicial)
	{
		set_Value (COLUMNNAME_EdadInicial, Integer.valueOf(EdadInicial));
	}

	/** Get Year Minimum.
		@return Year Minimum	  */
	public int getEdadInicial () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EdadInicial);
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

	/** Sexo AD_Reference_ID=1000018 */
	public static final int SEXO_AD_Reference_ID=1000018;
	/** Female = F */
	public static final String SEXO_Female = "F";
	/** Male = M */
	public static final String SEXO_Male = "M";
	/** Unassigned = U */
	public static final String SEXO_Unassigned = "U";
	/** Ambiguous = A */
	public static final String SEXO_Ambiguous = "A";
	/** Not Applicable = N */
	public static final String SEXO_NotApplicable = "N";
	/** Other = O */
	public static final String SEXO_Other = "O";
	/** Set Sex.
		@param Sexo 
		Sex
	  */
	public void setSexo (String Sexo)
	{

		if (Sexo == null || Sexo.equals("F") || Sexo.equals("M") || Sexo.equals("U") || Sexo.equals("A") || Sexo.equals("N") || Sexo.equals("O")); else throw new IllegalArgumentException ("Sexo Invalid value - " + Sexo + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_Sexo, Sexo);
	}

	/** Get Sex.
		@return Sex
	  */
	public String getSexo () 
	{
		return (String)get_Value(COLUMNNAME_Sexo);
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