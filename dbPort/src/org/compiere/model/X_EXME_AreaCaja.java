/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_AreaCaja
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_AreaCaja extends PO implements I_EXME_AreaCaja, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AreaCaja (Properties ctx, int EXME_AreaCaja_ID, String trxName)
    {
      super (ctx, EXME_AreaCaja_ID, trxName);
      /** if (EXME_AreaCaja_ID == 0)
        {
			setDocNoNotaCar (null);
			setDocNoNotaCre (null);
			setDocNoRecibo (null);
			setDocumentNoExt (null);
			setEXME_AreaCaja_ID (0);
			setName (null);
			setReinicio_Anual (false);
			setReinicio_AnualNotaCar (false);
			setReinicio_AnualNotaCre (false);
			setReinicio_AnualRecibo (false);
			setStartNo (0);
			setStartNoNotaCar (0);
			setStartNoNotaCre (0);
			setStartNoRecibo (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_AreaCaja (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AreaCaja[")
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

	/** Set Note of Charge of Document No..
		@param DocNoNotaCar 
		Note of Charge of Document No.
	  */
	public void setDocNoNotaCar (String DocNoNotaCar)
	{
		if (DocNoNotaCar == null)
			throw new IllegalArgumentException ("DocNoNotaCar is mandatory.");
		set_Value (COLUMNNAME_DocNoNotaCar, DocNoNotaCar);
	}

	/** Get Note of Charge of Document No..
		@return Note of Charge of Document No.
	  */
	public String getDocNoNotaCar () 
	{
		return (String)get_Value(COLUMNNAME_DocNoNotaCar);
	}

	/** Set Note of Credit of Document No..
		@param DocNoNotaCre 
		Note of Credit of Document No.
	  */
	public void setDocNoNotaCre (String DocNoNotaCre)
	{
		if (DocNoNotaCre == null)
			throw new IllegalArgumentException ("DocNoNotaCre is mandatory.");
		set_Value (COLUMNNAME_DocNoNotaCre, DocNoNotaCre);
	}

	/** Get Note of Credit of Document No..
		@return Note of Credit of Document No.
	  */
	public String getDocNoNotaCre () 
	{
		return (String)get_Value(COLUMNNAME_DocNoNotaCre);
	}

	/** Set Receipt Document No..
		@param DocNoRecibo 
		Receipt Document No.
	  */
	public void setDocNoRecibo (String DocNoRecibo)
	{
		if (DocNoRecibo == null)
			throw new IllegalArgumentException ("DocNoRecibo is mandatory.");
		set_Value (COLUMNNAME_DocNoRecibo, DocNoRecibo);
	}

	/** Get Receipt Document No..
		@return Receipt Document No.
	  */
	public String getDocNoRecibo () 
	{
		return (String)get_Value(COLUMNNAME_DocNoRecibo);
	}

	/** Set Exterior Document No..
		@param DocumentNoExt 
		Exterior Document No.
	  */
	public void setDocumentNoExt (String DocumentNoExt)
	{
		if (DocumentNoExt == null)
			throw new IllegalArgumentException ("DocumentNoExt is mandatory.");
		set_Value (COLUMNNAME_DocumentNoExt, DocumentNoExt);
	}

	/** Get Exterior Document No..
		@return Exterior Document No.
	  */
	public String getDocumentNoExt () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNoExt);
	}

	/** Set Cash book Area.
		@param EXME_AreaCaja_ID 
		Cash book Area
	  */
	public void setEXME_AreaCaja_ID (int EXME_AreaCaja_ID)
	{
		if (EXME_AreaCaja_ID < 1)
			 throw new IllegalArgumentException ("EXME_AreaCaja_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AreaCaja_ID, Integer.valueOf(EXME_AreaCaja_ID));
	}

	/** Get Cash book Area.
		@return Cash book Area
	  */
	public int getEXME_AreaCaja_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AreaCaja_ID);
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

	/** Set Prefix.
		@param Prefix 
		Prefix before the sequence number
	  */
	public void setPrefix (String Prefix)
	{
		set_Value (COLUMNNAME_Prefix, Prefix);
	}

	/** Get Prefix.
		@return Prefix before the sequence number
	  */
	public String getPrefix () 
	{
		return (String)get_Value(COLUMNNAME_Prefix);
	}

	/** Set Prefix Charge Note.
		@param PrefixNotaCar 
		Prefix Charge Note
	  */
	public void setPrefixNotaCar (String PrefixNotaCar)
	{
		set_Value (COLUMNNAME_PrefixNotaCar, PrefixNotaCar);
	}

	/** Get Prefix Charge Note.
		@return Prefix Charge Note
	  */
	public String getPrefixNotaCar () 
	{
		return (String)get_Value(COLUMNNAME_PrefixNotaCar);
	}

	/** Set Prefix Credit Note.
		@param PrefixNotaCre 
		Prefix credit Note
	  */
	public void setPrefixNotaCre (String PrefixNotaCre)
	{
		set_Value (COLUMNNAME_PrefixNotaCre, PrefixNotaCre);
	}

	/** Get Prefix Credit Note.
		@return Prefix credit Note
	  */
	public String getPrefixNotaCre () 
	{
		return (String)get_Value(COLUMNNAME_PrefixNotaCre);
	}

	/** Set Prefix Receipt.
		@param PrefixRecibo 
		Prefix receipt
	  */
	public void setPrefixRecibo (String PrefixRecibo)
	{
		set_Value (COLUMNNAME_PrefixRecibo, PrefixRecibo);
	}

	/** Get Prefix Receipt.
		@return Prefix receipt
	  */
	public String getPrefixRecibo () 
	{
		return (String)get_Value(COLUMNNAME_PrefixRecibo);
	}

	/** Set Annual Restart.
		@param Reinicio_Anual 
		Annual restart
	  */
	public void setReinicio_Anual (boolean Reinicio_Anual)
	{
		set_Value (COLUMNNAME_Reinicio_Anual, Boolean.valueOf(Reinicio_Anual));
	}

	/** Get Annual Restart.
		@return Annual restart
	  */
	public boolean isReinicio_Anual () 
	{
		Object oo = get_Value(COLUMNNAME_Reinicio_Anual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Restart Annual Charge Note.
		@param Reinicio_AnualNotaCar 
		Restart annual Charge note
	  */
	public void setReinicio_AnualNotaCar (boolean Reinicio_AnualNotaCar)
	{
		set_Value (COLUMNNAME_Reinicio_AnualNotaCar, Boolean.valueOf(Reinicio_AnualNotaCar));
	}

	/** Get Restart Annual Charge Note.
		@return Restart annual Charge note
	  */
	public boolean isReinicio_AnualNotaCar () 
	{
		Object oo = get_Value(COLUMNNAME_Reinicio_AnualNotaCar);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Restart Annual Credit Note.
		@param Reinicio_AnualNotaCre 
		Restart Annual Credit Note
	  */
	public void setReinicio_AnualNotaCre (boolean Reinicio_AnualNotaCre)
	{
		set_Value (COLUMNNAME_Reinicio_AnualNotaCre, Boolean.valueOf(Reinicio_AnualNotaCre));
	}

	/** Get Restart Annual Credit Note.
		@return Restart Annual Credit Note
	  */
	public boolean isReinicio_AnualNotaCre () 
	{
		Object oo = get_Value(COLUMNNAME_Reinicio_AnualNotaCre);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Restart Annual Payment Form Receipt.
		@param Reinicio_AnualRecibo 
		Restart annual payment form receipt
	  */
	public void setReinicio_AnualRecibo (boolean Reinicio_AnualRecibo)
	{
		set_Value (COLUMNNAME_Reinicio_AnualRecibo, Boolean.valueOf(Reinicio_AnualRecibo));
	}

	/** Get Restart Annual Payment Form Receipt.
		@return Restart annual payment form receipt
	  */
	public boolean isReinicio_AnualRecibo () 
	{
		Object oo = get_Value(COLUMNNAME_Reinicio_AnualRecibo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Start No.
		@param StartNo 
		Starting number/position
	  */
	public void setStartNo (int StartNo)
	{
		set_Value (COLUMNNAME_StartNo, Integer.valueOf(StartNo));
	}

	/** Get Start No.
		@return Starting number/position
	  */
	public int getStartNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StartNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initiate Charge Note No..
		@param StartNoNotaCar 
		initiate Charge Note No.
	  */
	public void setStartNoNotaCar (int StartNoNotaCar)
	{
		set_Value (COLUMNNAME_StartNoNotaCar, Integer.valueOf(StartNoNotaCar));
	}

	/** Get Initiate Charge Note No..
		@return initiate Charge Note No.
	  */
	public int getStartNoNotaCar () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StartNoNotaCar);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initiate Credit Note No..
		@param StartNoNotaCre 
		initiate credit note no.
	  */
	public void setStartNoNotaCre (int StartNoNotaCre)
	{
		set_Value (COLUMNNAME_StartNoNotaCre, Integer.valueOf(StartNoNotaCre));
	}

	/** Get Initiate Credit Note No..
		@return initiate credit note no.
	  */
	public int getStartNoNotaCre () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StartNoNotaCre);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initiate Payment Receipt No..
		@param StartNoRecibo 
		initiate Payment receipt No.
	  */
	public void setStartNoRecibo (int StartNoRecibo)
	{
		set_Value (COLUMNNAME_StartNoRecibo, Integer.valueOf(StartNoRecibo));
	}

	/** Get Initiate Payment Receipt No..
		@return initiate Payment receipt No.
	  */
	public int getStartNoRecibo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StartNoRecibo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Suffix.
		@param Suffix 
		Suffix after the number
	  */
	public void setSuffix (String Suffix)
	{
		set_Value (COLUMNNAME_Suffix, Suffix);
	}

	/** Get Suffix.
		@return Suffix after the number
	  */
	public String getSuffix () 
	{
		return (String)get_Value(COLUMNNAME_Suffix);
	}

	/** Set Sufix Charge Note.
		@param SuffixNotaCar 
		Sufix Charge Note
	  */
	public void setSuffixNotaCar (String SuffixNotaCar)
	{
		set_Value (COLUMNNAME_SuffixNotaCar, SuffixNotaCar);
	}

	/** Get Sufix Charge Note.
		@return Sufix Charge Note
	  */
	public String getSuffixNotaCar () 
	{
		return (String)get_Value(COLUMNNAME_SuffixNotaCar);
	}

	/** Set Sufix Credit Note.
		@param SuffixNotaCre 
		Sufix credit Note
	  */
	public void setSuffixNotaCre (String SuffixNotaCre)
	{
		set_Value (COLUMNNAME_SuffixNotaCre, SuffixNotaCre);
	}

	/** Get Sufix Credit Note.
		@return Sufix credit Note
	  */
	public String getSuffixNotaCre () 
	{
		return (String)get_Value(COLUMNNAME_SuffixNotaCre);
	}

	/** Set Sufix Payment Receipt.
		@param SuffixRecibo 
		Sufix Payment receipt
	  */
	public void setSuffixRecibo (String SuffixRecibo)
	{
		set_Value (COLUMNNAME_SuffixRecibo, SuffixRecibo);
	}

	/** Get Sufix Payment Receipt.
		@return Sufix Payment receipt
	  */
	public String getSuffixRecibo () 
	{
		return (String)get_Value(COLUMNNAME_SuffixRecibo);
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