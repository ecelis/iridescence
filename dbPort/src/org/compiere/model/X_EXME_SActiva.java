/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_SActiva
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_SActiva extends PO implements I_EXME_SActiva, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_SActiva (Properties ctx, int EXME_SActiva_ID, String trxName)
    {
      super (ctx, EXME_SActiva_ID, trxName);
      /** if (EXME_SActiva_ID == 0)
        {
			setEXME_SActiva_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_SActiva (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_SActiva[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Base Ingredient.
		@param BaseIngredientId Base Ingredient	  */
	public void setBaseIngredientId (String BaseIngredientId)
	{
		set_Value (COLUMNNAME_BaseIngredientId, BaseIngredientId);
	}

	/** Get Base Ingredient.
		@return Base Ingredient	  */
	public String getBaseIngredientId () 
	{
		return (String)get_Value(COLUMNNAME_BaseIngredientId);
	}

	/** Set Concept.
		@param ConceptId 
		Concept
	  */
	public void setConceptId (int ConceptId)
	{
		set_Value (COLUMNNAME_ConceptId, Integer.valueOf(ConceptId));
	}

	/** Get Concept.
		@return Concept
	  */
	public int getConceptId () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ConceptId);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Concept Type.
		@param ConceptType Concept Type	  */
	public void setConceptType (int ConceptType)
	{
		set_Value (COLUMNNAME_ConceptType, Integer.valueOf(ConceptType));
	}

	/** Get Concept Type.
		@return Concept Type	  */
	public int getConceptType () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ConceptType);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Active Substance.
		@param EXME_SActiva_ID 
		Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID)
	{
		if (EXME_SActiva_ID < 1)
			 throw new IllegalArgumentException ("EXME_SActiva_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SActiva_ID, Integer.valueOf(EXME_SActiva_ID));
	}

	/** Get Active Substance.
		@return Active Substance
	  */
	public int getEXME_SActiva_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SActiva_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Formule.
		@param Formula 
		Substance Formule
	  */
	public void setFormula (String Formula)
	{
		set_Value (COLUMNNAME_Formula, Formula);
	}

	/** Get Formule.
		@return Substance Formule
	  */
	public String getFormula () 
	{
		return (String)get_Value(COLUMNNAME_Formula);
	}

	/** Set Sequence number.
		@param HicSeqNo Sequence number	  */
	public void setHicSeqNo (String HicSeqNo)
	{
		set_Value (COLUMNNAME_HicSeqNo, HicSeqNo);
	}

	/** Get Sequence number.
		@return Sequence number	  */
	public String getHicSeqNo () 
	{
		return (String)get_Value(COLUMNNAME_HicSeqNo);
	}

	/** Set Is Group.
		@param IsGroup 
		Marks this segment as a group
	  */
	public void setIsGroup (boolean IsGroup)
	{
		set_Value (COLUMNNAME_IsGroup, Boolean.valueOf(IsGroup));
	}

	/** Get Is Group.
		@return Marks this segment as a group
	  */
	public boolean isGroup () 
	{
		Object oo = get_Value(COLUMNNAME_IsGroup);
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