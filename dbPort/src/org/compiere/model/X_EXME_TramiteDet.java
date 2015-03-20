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

/** Generated Model for EXME_TramiteDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_TramiteDet extends PO implements I_EXME_TramiteDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TramiteDet (Properties ctx, int EXME_TramiteDet_ID, String trxName)
    {
      super (ctx, EXME_TramiteDet_ID, trxName);
      /** if (EXME_TramiteDet_ID == 0)
        {
			setEXME_TramiteDet_ID (0);
			setEXME_TramiteHdr_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_TramiteDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TramiteDet[")
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

	/** Set Procedure Detail.
		@param EXME_TramiteDet_ID Procedure Detail	  */
	public void setEXME_TramiteDet_ID (int EXME_TramiteDet_ID)
	{
		if (EXME_TramiteDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_TramiteDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TramiteDet_ID, Integer.valueOf(EXME_TramiteDet_ID));
	}

	/** Get Procedure Detail.
		@return Procedure Detail	  */
	public int getEXME_TramiteDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TramiteDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TramiteHdr getEXME_TramiteHdr() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TramiteHdr.Table_Name);
        I_EXME_TramiteHdr result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TramiteHdr)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TramiteHdr_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Procedures.
		@param EXME_TramiteHdr_ID Procedures	  */
	public void setEXME_TramiteHdr_ID (int EXME_TramiteHdr_ID)
	{
		if (EXME_TramiteHdr_ID < 1)
			 throw new IllegalArgumentException ("EXME_TramiteHdr_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TramiteHdr_ID, Integer.valueOf(EXME_TramiteHdr_ID));
	}

	/** Get Procedures.
		@return Procedures	  */
	public int getEXME_TramiteHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TramiteHdr_ID);
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