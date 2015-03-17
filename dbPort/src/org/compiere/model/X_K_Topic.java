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

/** Generated Model for K_Topic
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_K_Topic extends PO implements I_K_Topic, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_K_Topic (Properties ctx, int K_Topic_ID, String trxName)
    {
      super (ctx, K_Topic_ID, trxName);
      /** if (K_Topic_ID == 0)
        {
			setIsPublic (true);
// Y
			setIsPublicWrite (true);
// Y
			setK_Topic_ID (0);
			setK_Type_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_K_Topic (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_K_Topic[")
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Public.
		@param IsPublic 
		Public can read entry
	  */
	public void setIsPublic (boolean IsPublic)
	{
		set_Value (COLUMNNAME_IsPublic, Boolean.valueOf(IsPublic));
	}

	/** Get Public.
		@return Public can read entry
	  */
	public boolean isPublic () 
	{
		Object oo = get_Value(COLUMNNAME_IsPublic);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Public Write.
		@param IsPublicWrite 
		Public can write entries
	  */
	public void setIsPublicWrite (boolean IsPublicWrite)
	{
		set_Value (COLUMNNAME_IsPublicWrite, Boolean.valueOf(IsPublicWrite));
	}

	/** Get Public Write.
		@return Public can write entries
	  */
	public boolean isPublicWrite () 
	{
		Object oo = get_Value(COLUMNNAME_IsPublicWrite);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Knowledge Topic.
		@param K_Topic_ID 
		Knowledge Topic
	  */
	public void setK_Topic_ID (int K_Topic_ID)
	{
		if (K_Topic_ID < 1)
			 throw new IllegalArgumentException ("K_Topic_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_K_Topic_ID, Integer.valueOf(K_Topic_ID));
	}

	/** Get Knowledge Topic.
		@return Knowledge Topic
	  */
	public int getK_Topic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_K_Topic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_K_Type getK_Type() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_K_Type.Table_Name);
        I_K_Type result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_K_Type)constructor.newInstance(new Object[] {getCtx(), new Integer(getK_Type_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Knowldge Type.
		@param K_Type_ID 
		Knowledge Type
	  */
	public void setK_Type_ID (int K_Type_ID)
	{
		if (K_Type_ID < 1)
			 throw new IllegalArgumentException ("K_Type_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_K_Type_ID, Integer.valueOf(K_Type_ID));
	}

	/** Get Knowldge Type.
		@return Knowledge Type
	  */
	public int getK_Type_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_K_Type_ID);
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
}