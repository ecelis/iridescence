/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_EstiloVida
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_EstiloVida extends PO implements I_EXME_EstiloVida, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EstiloVida (Properties ctx, int EXME_EstiloVida_ID, String trxName)
    {
      super (ctx, EXME_EstiloVida_ID, trxName);
      /** if (EXME_EstiloVida_ID == 0)
        {
			setEXME_EstiloVida_ID (0);
			setName (null);
			setTipoEstilo (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_EstiloVida (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EstiloVida[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Snomed Code.
		@param CodSnomed 
		Snomed Code
	  */
	public void setCodSnomed (String CodSnomed)
	{
		set_Value (COLUMNNAME_CodSnomed, CodSnomed);
	}

	/** Get Snomed Code.
		@return Snomed Code
	  */
	public String getCodSnomed () 
	{
		return (String)get_Value(COLUMNNAME_CodSnomed);
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

	/** Set Life Style.
		@param EXME_EstiloVida_ID Life Style	  */
	public void setEXME_EstiloVida_ID (int EXME_EstiloVida_ID)
	{
		if (EXME_EstiloVida_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstiloVida_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EstiloVida_ID, Integer.valueOf(EXME_EstiloVida_ID));
	}

	/** Get Life Style.
		@return Life Style	  */
	public int getEXME_EstiloVida_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstiloVida_ID);
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

	/** TipoEstilo AD_Reference_ID=1200397 */
	public static final int TIPOESTILO_AD_Reference_ID=1200397;
	/** Smoker Status = SM */
	public static final String TIPOESTILO_SmokerStatus = "SM";
	/** Alcoholism Status = AS */
	public static final String TIPOESTILO_AlcoholismStatus = "AS";
	/** Drug Status = DR */
	public static final String TIPOESTILO_DrugStatus = "DR";
	/** Functional Status = FS */
	public static final String TIPOESTILO_FunctionalStatus = "FS";
	/** Set Lifestyle Type.
		@param TipoEstilo Lifestyle Type	  */
	public void setTipoEstilo (String TipoEstilo)
	{
		if (TipoEstilo == null) throw new IllegalArgumentException ("TipoEstilo is mandatory");
		if (TipoEstilo.equals("SM") || TipoEstilo.equals("AS") || TipoEstilo.equals("DR") || TipoEstilo.equals("FS")); else throw new IllegalArgumentException ("TipoEstilo Invalid value - " + TipoEstilo + " - Reference_ID=1200397 - SM - AS - DR - FS");		set_Value (COLUMNNAME_TipoEstilo, TipoEstilo);
	}

	/** Get Lifestyle Type.
		@return Lifestyle Type	  */
	public String getTipoEstilo () 
	{
		return (String)get_Value(COLUMNNAME_TipoEstilo);
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