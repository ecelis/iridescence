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

/** Generated Model for K_EntryRelated
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_K_EntryRelated extends PO implements I_K_EntryRelated, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_K_EntryRelated (Properties ctx, int K_EntryRelated_ID, String trxName)
    {
      super (ctx, K_EntryRelated_ID, trxName);
      /** if (K_EntryRelated_ID == 0)
        {
			setK_Entry_ID (0);
			setK_EntryRelated_ID (0);
        } */
    }

    /** Load Constructor */
    public X_K_EntryRelated (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_K_EntryRelated[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_K_Entry getK_Entry() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_K_Entry.Table_Name);
        I_K_Entry result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_K_Entry)constructor.newInstance(new Object[] {getCtx(), new Integer(getK_Entry_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Entry.
		@param K_Entry_ID 
		Knowledge Entry
	  */
	public void setK_Entry_ID (int K_Entry_ID)
	{
		if (K_Entry_ID < 1)
			 throw new IllegalArgumentException ("K_Entry_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_K_Entry_ID, Integer.valueOf(K_Entry_ID));
	}

	/** Get Entry.
		@return Knowledge Entry
	  */
	public int getK_Entry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_K_Entry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Related Entry.
		@param K_EntryRelated_ID 
		Related Entry for this Enntry
	  */
	public void setK_EntryRelated_ID (int K_EntryRelated_ID)
	{
		if (K_EntryRelated_ID < 1)
			 throw new IllegalArgumentException ("K_EntryRelated_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_K_EntryRelated_ID, Integer.valueOf(K_EntryRelated_ID));
	}

	/** Get Related Entry.
		@return Related Entry for this Enntry
	  */
	public int getK_EntryRelated_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_K_EntryRelated_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getK_EntryRelated_ID()));
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
}