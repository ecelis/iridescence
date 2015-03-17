/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ConfigDieta
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_ConfigDieta extends PO implements I_EXME_ConfigDieta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigDieta (Properties ctx, int EXME_ConfigDieta_ID, String trxName)
    {
      super (ctx, EXME_ConfigDieta_ID, trxName);
      /** if (EXME_ConfigDieta_ID == 0)
        {
			setDescription (null);
			setEXME_ConfigDieta_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigDieta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigDieta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Diet Configuration.
		@param EXME_ConfigDieta_ID Diet Configuration	  */
	public void setEXME_ConfigDieta_ID (int EXME_ConfigDieta_ID)
	{
		if (EXME_ConfigDieta_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigDieta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigDieta_ID, Integer.valueOf(EXME_ConfigDieta_ID));
	}

	/** Get Diet Configuration.
		@return Diet Configuration	  */
	public int getEXME_ConfigDieta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigDieta_ID);
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

	/** Tipo AD_Reference_ID=1200142 */
	public static final int TIPO_AD_Reference_ID=1200142;
	/** Calories = CA */
	public static final String TIPO_Calories = "CA";
	/** Percentage of Nutrients = PN */
	public static final String TIPO_PercentageOfNutrients = "PN";
	/** Grams of Nutrients = GM */
	public static final String TIPO_GramsOfNutrients = "GM";
	/** Liquid Quantity = ML */
	public static final String TIPO_LiquidQuantity = "ML";
	/** Division = FR */
	public static final String TIPO_Division = "FR";
	/** Consistency = CO */
	public static final String TIPO_Consistency = "CO";
	/** Set Type.
		@param Tipo Type	  */
	public void setTipo (String Tipo)
	{

		if (Tipo == null || Tipo.equals("CA") || Tipo.equals("PN") || Tipo.equals("GM") || Tipo.equals("ML") || Tipo.equals("FR") || Tipo.equals("CO")); else throw new IllegalArgumentException ("Tipo Invalid value - " + Tipo + " - Reference_ID=1200142 - CA - PN - GM - ML - FR - CO");		set_Value (COLUMNNAME_Tipo, Tipo);
	}

	/** Get Type.
		@return Type	  */
	public String getTipo () 
	{
		return (String)get_Value(COLUMNNAME_Tipo);
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