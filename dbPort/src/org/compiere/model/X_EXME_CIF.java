/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_CIF
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CIF extends PO implements I_EXME_CIF, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CIF (Properties ctx, int EXME_CIF_ID, String trxName)
    {
      super (ctx, EXME_CIF_ID, trxName);
      /** if (EXME_CIF_ID == 0)
        {
			setEXME_CIFHdr_ID (0);
			setEXME_CIF_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_CIF (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CIF[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Code GRD.
		@param CodGrd 
		Code GRD
	  */
	public void setCodGrd (String CodGrd)
	{
		set_Value (COLUMNNAME_CodGrd, CodGrd);
	}

	/** Get Code GRD.
		@return Code GRD
	  */
	public String getCodGrd () 
	{
		return (String)get_Value(COLUMNNAME_CodGrd);
	}

	/** Set Code INEGI.
		@param CodInegi 
		Code INEGI
	  */
	public void setCodInegi (String CodInegi)
	{
		set_Value (COLUMNNAME_CodInegi, CodInegi);
	}

	/** Get Code INEGI.
		@return Code INEGI
	  */
	public String getCodInegi () 
	{
		return (String)get_Value(COLUMNNAME_CodInegi);
	}

	/** Set World Organization Health Code.
		@param CodOms 
		World Organization Health Code
	  */
	public void setCodOms (String CodOms)
	{
		set_Value (COLUMNNAME_CodOms, CodOms);
	}

	/** Get World Organization Health Code.
		@return World Organization Health Code
	  */
	public String getCodOms () 
	{
		return (String)get_Value(COLUMNNAME_CodOms);
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

	public I_EXME_CIFHdr getEXME_CIFHdr() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CIFHdr.Table_Name);
        I_EXME_CIFHdr result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CIFHdr)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CIFHdr_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set ICF Version.
		@param EXME_CIFHdr_ID ICF Version	  */
	public void setEXME_CIFHdr_ID (int EXME_CIFHdr_ID)
	{
		if (EXME_CIFHdr_ID < 1)
			 throw new IllegalArgumentException ("EXME_CIFHdr_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CIFHdr_ID, Integer.valueOf(EXME_CIFHdr_ID));
	}

	/** Get ICF Version.
		@return ICF Version	  */
	public int getEXME_CIFHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CIFHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ICF.
		@param EXME_CIF_ID 
		International Classification of Functioning, Disability and Health
	  */
	public void setEXME_CIF_ID (int EXME_CIF_ID)
	{
		if (EXME_CIF_ID < 1)
			 throw new IllegalArgumentException ("EXME_CIF_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CIF_ID, Integer.valueOf(EXME_CIF_ID));
	}

	/** Get ICF.
		@return International Classification of Functioning, Disability and Health
	  */
	public int getEXME_CIF_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CIF_ID);
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