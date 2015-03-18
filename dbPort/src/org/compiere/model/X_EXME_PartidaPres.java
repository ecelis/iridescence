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

/** Generated Model for EXME_PartidaPres
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PartidaPres extends PO implements I_EXME_PartidaPres, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PartidaPres (Properties ctx, int EXME_PartidaPres_ID, String trxName)
    {
      super (ctx, EXME_PartidaPres_ID, trxName);
      /** if (EXME_PartidaPres_ID == 0)
        {
			setEXME_ConceptoGasto_ID (0);
			setEXME_PartidaPres_ID (0);
			setEXME_TipoGasto_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PartidaPres (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PartidaPres[")
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

	public I_EXME_ConceptoGasto getEXME_ConceptoGasto() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ConceptoGasto.Table_Name);
        I_EXME_ConceptoGasto result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ConceptoGasto)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ConceptoGasto_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Concept of household.
		@param EXME_ConceptoGasto_ID Concept of household	  */
	public void setEXME_ConceptoGasto_ID (int EXME_ConceptoGasto_ID)
	{
		if (EXME_ConceptoGasto_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConceptoGasto_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ConceptoGasto_ID, Integer.valueOf(EXME_ConceptoGasto_ID));
	}

	/** Get Concept of household.
		@return Concept of household	  */
	public int getEXME_ConceptoGasto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConceptoGasto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Budget Item.
		@param EXME_PartidaPres_ID 
		Budget Item
	  */
	public void setEXME_PartidaPres_ID (int EXME_PartidaPres_ID)
	{
		if (EXME_PartidaPres_ID < 1)
			 throw new IllegalArgumentException ("EXME_PartidaPres_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PartidaPres_ID, Integer.valueOf(EXME_PartidaPres_ID));
	}

	/** Get Budget Item.
		@return Budget Item
	  */
	public int getEXME_PartidaPres_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PartidaPres_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoGasto getEXME_TipoGasto() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoGasto.Table_Name);
        I_EXME_TipoGasto result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoGasto)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoGasto_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Type of expenditure.
		@param EXME_TipoGasto_ID 
		Type of expenditure
	  */
	public void setEXME_TipoGasto_ID (int EXME_TipoGasto_ID)
	{
		if (EXME_TipoGasto_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoGasto_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoGasto_ID, Integer.valueOf(EXME_TipoGasto_ID));
	}

	/** Get Type of expenditure.
		@return Type of expenditure
	  */
	public int getEXME_TipoGasto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoGasto_ID);
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