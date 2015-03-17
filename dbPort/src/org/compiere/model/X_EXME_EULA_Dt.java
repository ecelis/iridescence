/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_EULA_Dt
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_EULA_Dt extends PO implements I_EXME_EULA_Dt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EULA_Dt (Properties ctx, int EXME_EULA_Dt_ID, String trxName)
    {
      super (ctx, EXME_EULA_Dt_ID, trxName);
      /** if (EXME_EULA_Dt_ID == 0)
        {
			setEXME_EULA_Dt_ID (0);
			setEXME_EULA_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_EULA_Dt (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EULA_Dt[")
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

	/** Set EULA Detail.
		@param EXME_EULA_Dt_ID 
		End User License Agreement Detail
	  */
	public void setEXME_EULA_Dt_ID (int EXME_EULA_Dt_ID)
	{
		if (EXME_EULA_Dt_ID < 1)
			 throw new IllegalArgumentException ("EXME_EULA_Dt_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EULA_Dt_ID, Integer.valueOf(EXME_EULA_Dt_ID));
	}

	/** Get EULA Detail.
		@return End User License Agreement Detail
	  */
	public int getEXME_EULA_Dt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EULA_Dt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EULA getEXME_EULA() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EULA.Table_Name);
        I_EXME_EULA result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EULA)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EULA_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set End User Agreement License.
		@param EXME_EULA_ID 
		The End User Agreement License
	  */
	public void setEXME_EULA_ID (int EXME_EULA_ID)
	{
		if (EXME_EULA_ID < 1)
			 throw new IllegalArgumentException ("EXME_EULA_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EULA_ID, Integer.valueOf(EXME_EULA_ID));
	}

	/** Get End User Agreement License.
		@return The End User Agreement License
	  */
	public int getEXME_EULA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EULA_ID);
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

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}
}