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

/** Generated Model for EXME_Incapacidad
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Incapacidad extends PO implements I_EXME_Incapacidad, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Incapacidad (Properties ctx, int EXME_Incapacidad_ID, String trxName)
    {
      super (ctx, EXME_Incapacidad_ID, trxName);
      /** if (EXME_Incapacidad_ID == 0)
        {
			setEXME_Incapacidad_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Incapacidad (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Incapacidad[")
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

	public I_EXME_Articulo_Ley getEXME_Articulo_Ley() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Articulo_Ley.Table_Name);
        I_EXME_Articulo_Ley result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Articulo_Ley)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Articulo_Ley_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Rule Article.
		@param EXME_Articulo_Ley_ID Rule Article	  */
	public void setEXME_Articulo_Ley_ID (int EXME_Articulo_Ley_ID)
	{
		if (EXME_Articulo_Ley_ID < 1) 
			set_Value (COLUMNNAME_EXME_Articulo_Ley_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Articulo_Ley_ID, Integer.valueOf(EXME_Articulo_Ley_ID));
	}

	/** Get Rule Article.
		@return Rule Article	  */
	public int getEXME_Articulo_Ley_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Articulo_Ley_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Fraccion_Ley getEXME_Fraccion_Ley() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Fraccion_Ley.Table_Name);
        I_EXME_Fraccion_Ley result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Fraccion_Ley)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Fraccion_Ley_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Law Fraction.
		@param EXME_Fraccion_Ley_ID Law Fraction	  */
	public void setEXME_Fraccion_Ley_ID (int EXME_Fraccion_Ley_ID)
	{
		if (EXME_Fraccion_Ley_ID < 1) 
			set_Value (COLUMNNAME_EXME_Fraccion_Ley_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Fraccion_Ley_ID, Integer.valueOf(EXME_Fraccion_Ley_ID));
	}

	/** Get Law Fraction.
		@return Law Fraction	  */
	public int getEXME_Fraccion_Ley_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Fraccion_Ley_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Disability.
		@param EXME_Incapacidad_ID Disability	  */
	public void setEXME_Incapacidad_ID (int EXME_Incapacidad_ID)
	{
		if (EXME_Incapacidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Incapacidad_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Incapacidad_ID, Integer.valueOf(EXME_Incapacidad_ID));
	}

	/** Get Disability.
		@return Disability	  */
	public int getEXME_Incapacidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Incapacidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Motive.
		@param Motivo 
		Motive / Reason
	  */
	public void setMotivo (String Motivo)
	{
		set_Value (COLUMNNAME_Motivo, Motivo);
	}

	/** Get Motive.
		@return Motive / Reason
	  */
	public String getMotivo () 
	{
		return (String)get_Value(COLUMNNAME_Motivo);
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

	/** Set Condition.
		@param Padecimiento Condition	  */
	public void setPadecimiento (String Padecimiento)
	{
		set_Value (COLUMNNAME_Padecimiento, Padecimiento);
	}

	/** Get Condition.
		@return Condition	  */
	public String getPadecimiento () 
	{
		return (String)get_Value(COLUMNNAME_Padecimiento);
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