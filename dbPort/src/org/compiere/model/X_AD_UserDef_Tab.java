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

/** Generated Model for AD_UserDef_Tab
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_UserDef_Tab extends PO implements I_AD_UserDef_Tab, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_UserDef_Tab (Properties ctx, int AD_UserDef_Tab_ID, String trxName)
    {
      super (ctx, AD_UserDef_Tab_ID, trxName);
      /** if (AD_UserDef_Tab_ID == 0)
        {
			setAD_Tab_ID (0);
			setAD_UserDef_Tab_ID (0);
			setAD_UserDef_Win_ID (0);
			setIsMultiRowOnly (false);
			setIsReadOnly (false);
			setIsSingleRow (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_UserDef_Tab (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_UserDef_Tab[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Tab getAD_Tab() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Tab.Table_Name);
        I_AD_Tab result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Tab)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Tab_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Tab.
		@param AD_Tab_ID 
		Tab within a Window
	  */
	public void setAD_Tab_ID (int AD_Tab_ID)
	{
		if (AD_Tab_ID < 1)
			 throw new IllegalArgumentException ("AD_Tab_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Tab_ID, Integer.valueOf(AD_Tab_ID));
	}

	/** Get Tab.
		@return Tab within a Window
	  */
	public int getAD_Tab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User defined Tab.
		@param AD_UserDef_Tab_ID User defined Tab	  */
	public void setAD_UserDef_Tab_ID (int AD_UserDef_Tab_ID)
	{
		if (AD_UserDef_Tab_ID < 1)
			 throw new IllegalArgumentException ("AD_UserDef_Tab_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_UserDef_Tab_ID, Integer.valueOf(AD_UserDef_Tab_ID));
	}

	/** Get User defined Tab.
		@return User defined Tab	  */
	public int getAD_UserDef_Tab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_UserDef_Tab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_UserDef_Win getAD_UserDef_Win() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_UserDef_Win.Table_Name);
        I_AD_UserDef_Win result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_UserDef_Win)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_UserDef_Win_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User defined Window.
		@param AD_UserDef_Win_ID User defined Window	  */
	public void setAD_UserDef_Win_ID (int AD_UserDef_Win_ID)
	{
		if (AD_UserDef_Win_ID < 1)
			 throw new IllegalArgumentException ("AD_UserDef_Win_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_UserDef_Win_ID, Integer.valueOf(AD_UserDef_Win_ID));
	}

	/** Get User defined Window.
		@return User defined Window	  */
	public int getAD_UserDef_Win_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_UserDef_Win_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Multi Row Only.
		@param IsMultiRowOnly 
		This applies to Multi-Row view only
	  */
	public void setIsMultiRowOnly (boolean IsMultiRowOnly)
	{
		set_Value (COLUMNNAME_IsMultiRowOnly, Boolean.valueOf(IsMultiRowOnly));
	}

	/** Get Multi Row Only.
		@return This applies to Multi-Row view only
	  */
	public boolean isMultiRowOnly () 
	{
		Object oo = get_Value(COLUMNNAME_IsMultiRowOnly);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Read Only.
		@param IsReadOnly 
		Field is read only
	  */
	public void setIsReadOnly (boolean IsReadOnly)
	{
		set_Value (COLUMNNAME_IsReadOnly, Boolean.valueOf(IsReadOnly));
	}

	/** Get Read Only.
		@return Field is read only
	  */
	public boolean isReadOnly () 
	{
		Object oo = get_Value(COLUMNNAME_IsReadOnly);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Single Row Layout.
		@param IsSingleRow 
		Default for toggle between Single- and Multi-Row (Grid) Layout
	  */
	public void setIsSingleRow (boolean IsSingleRow)
	{
		set_Value (COLUMNNAME_IsSingleRow, Boolean.valueOf(IsSingleRow));
	}

	/** Get Single Row Layout.
		@return Default for toggle between Single- and Multi-Row (Grid) Layout
	  */
	public boolean isSingleRow () 
	{
		Object oo = get_Value(COLUMNNAME_IsSingleRow);
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
}