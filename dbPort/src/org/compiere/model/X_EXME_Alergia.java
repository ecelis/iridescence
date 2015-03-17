/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Alergia
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_Alergia extends PO implements I_EXME_Alergia, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Alergia (Properties ctx, int EXME_Alergia_ID, String trxName)
    {
      super (ctx, EXME_Alergia_ID, trxName);
      /** if (EXME_Alergia_ID == 0)
        {
			setEXME_Alergia_ID (0);
			setName (null);
			setTipoAlergia (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Alergia (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Alergia[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Allergenic.
		@param Alergenico 
		Allergenic
	  */
	public void setAlergenico (String Alergenico)
	{
		set_Value (COLUMNNAME_Alergenico, Alergenico);
	}

	/** Get Allergenic.
		@return Allergenic
	  */
	public String getAlergenico () 
	{
		return (String)get_Value(COLUMNNAME_Alergenico);
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

	/** Set Allergy.
		@param EXME_Alergia_ID 
		Allergy
	  */
	public void setEXME_Alergia_ID (int EXME_Alergia_ID)
	{
		if (EXME_Alergia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Alergia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Alergia_ID, Integer.valueOf(EXME_Alergia_ID));
	}

	/** Get Allergy.
		@return Allergy
	  */
	public int getEXME_Alergia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Alergia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
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

	/** TipoAlergia AD_Reference_ID=394 */
	public static final int TIPOALERGIA_AD_Reference_ID=394;
	/** Animal Allergy = AA */
	public static final String TIPOALERGIA_AnimalAllergy = "AA";
	/** Drug Allergy = DA */
	public static final String TIPOALERGIA_DrugAllergy = "DA";
	/** Environmental Allergy = EA */
	public static final String TIPOALERGIA_EnvironmentalAllergy = "EA";
	/** Food Allergy = FA */
	public static final String TIPOALERGIA_FoodAllergy = "FA";
	/** Pollen Allergy = LA */
	public static final String TIPOALERGIA_PollenAllergy = "LA";
	/** Miscellaneous Allergy = MA */
	public static final String TIPOALERGIA_MiscellaneousAllergy = "MA";
	/** Miscellaneous Contraindications = MC */
	public static final String TIPOALERGIA_MiscellaneousContraindications = "MC";
	/** Plant Allergy = PA */
	public static final String TIPOALERGIA_PlantAllergy = "PA";
	/** Set Alergy Type.
		@param TipoAlergia 
		Alergy Type
	  */
	public void setTipoAlergia (String TipoAlergia)
	{
		if (TipoAlergia == null) throw new IllegalArgumentException ("TipoAlergia is mandatory");
		if (TipoAlergia.equals("AA") || TipoAlergia.equals("DA") || TipoAlergia.equals("EA") || TipoAlergia.equals("FA") || TipoAlergia.equals("LA") || TipoAlergia.equals("MA") || TipoAlergia.equals("MC") || TipoAlergia.equals("PA")); else throw new IllegalArgumentException ("TipoAlergia Invalid value - " + TipoAlergia + " - Reference_ID=394 - AA - DA - EA - FA - LA - MA - MC - PA");		set_Value (COLUMNNAME_TipoAlergia, TipoAlergia);
	}

	/** Get Alergy Type.
		@return Alergy Type
	  */
	public String getTipoAlergia () 
	{
		return (String)get_Value(COLUMNNAME_TipoAlergia);
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