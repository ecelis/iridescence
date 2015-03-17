/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_SectionBody_TRL
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_SectionBody_TRL extends PO implements I_EXME_SectionBody_TRL, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_SectionBody_TRL (Properties ctx, int EXME_SectionBody_TRL_ID, String trxName)
    {
      super (ctx, EXME_SectionBody_TRL_ID, trxName);
      /** if (EXME_SectionBody_TRL_ID == 0)
        {
			setAD_Language (null);
			setEXME_SectionBody_ID (0);
			setIsTranslated (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_SectionBody_TRL (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_SectionBody_TRL[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Language.
		@param AD_Language 
		Language for this entity
	  */
	public void setAD_Language (String AD_Language)
	{
		if (AD_Language == null)
			throw new IllegalArgumentException ("AD_Language is mandatory.");
		set_Value (COLUMNNAME_AD_Language, AD_Language);
	}

	/** Get Language.
		@return Language for this entity
	  */
	public String getAD_Language () 
	{
		return (String)get_Value(COLUMNNAME_AD_Language);
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

	public I_EXME_SectionBody getEXME_SectionBody() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SectionBody.Table_Name);
        I_EXME_SectionBody result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SectionBody)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SectionBody_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Section Body.
		@param EXME_SectionBody_ID Section Body	  */
	public void setEXME_SectionBody_ID (int EXME_SectionBody_ID)
	{
		if (EXME_SectionBody_ID < 1)
			 throw new IllegalArgumentException ("EXME_SectionBody_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SectionBody_ID, Integer.valueOf(EXME_SectionBody_ID));
	}

	/** Get Section Body.
		@return Section Body	  */
	public int getEXME_SectionBody_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SectionBody_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Translated.
		@param IsTranslated 
		This column is translated
	  */
	public void setIsTranslated (boolean IsTranslated)
	{
		set_Value (COLUMNNAME_IsTranslated, Boolean.valueOf(IsTranslated));
	}

	/** Get Translated.
		@return This column is translated
	  */
	public boolean isTranslated () 
	{
		Object oo = get_Value(COLUMNNAME_IsTranslated);
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