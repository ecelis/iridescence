/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for I_EXME_SActiva
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_SActiva extends PO implements I_I_EXME_SActiva, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_SActiva (Properties ctx, int I_EXME_SActiva_ID, String trxName)
    {
      super (ctx, I_EXME_SActiva_ID, trxName);
      /** if (I_EXME_SActiva_ID == 0)
        {
			setI_EXME_SActiva_ID (0);
			setI_IsImported (false);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_SActiva (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_SActiva[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Concepttype.
		@param ConceptType Concepttype	  */
	public void setConceptType (String ConceptType)
	{
		set_Value (COLUMNNAME_ConceptType, ConceptType);
	}

	/** Get Concepttype.
		@return Concepttype	  */
	public String getConceptType () 
	{
		return (String)get_Value(COLUMNNAME_ConceptType);
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
			set_Value (COLUMNNAME_EXME_SActiva_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_SActiva_ID, Integer.valueOf(EXME_SActiva_ID));
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

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported Active Substance.
		@param I_EXME_SActiva_ID 
		Imported Active Substance
	  */
	public void setI_EXME_SActiva_ID (int I_EXME_SActiva_ID)
	{
		if (I_EXME_SActiva_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_SActiva_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_SActiva_ID, Integer.valueOf(I_EXME_SActiva_ID));
	}

	/** Get Imported Active Substance.
		@return Imported Active Substance
	  */
	public int getI_EXME_SActiva_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_SActiva_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
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