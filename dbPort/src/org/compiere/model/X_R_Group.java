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

/** Generated Model for R_Group
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_R_Group extends PO implements I_R_Group, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_R_Group (Properties ctx, int R_Group_ID, String trxName)
    {
      super (ctx, R_Group_ID, trxName);
      /** if (R_Group_ID == 0)
        {
			setName (null);
			setR_Group_ID (0);
        } */
    }

    /** Load Constructor */
    public X_R_Group (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_R_Group[")
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

	public I_M_BOM getM_BOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_BOM.Table_Name);
        I_M_BOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_BOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_BOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set BOM.
		@param M_BOM_ID 
		Bill of Material
	  */
	public void setM_BOM_ID (int M_BOM_ID)
	{
		if (M_BOM_ID < 1) 
			set_Value (COLUMNNAME_M_BOM_ID, null);
		else 
			set_Value (COLUMNNAME_M_BOM_ID, Integer.valueOf(M_BOM_ID));
	}

	/** Get BOM.
		@return Bill of Material
	  */
	public int getM_BOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_BOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_ChangeNotice getM_ChangeNotice() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_ChangeNotice.Table_Name);
        I_M_ChangeNotice result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_ChangeNotice)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_ChangeNotice_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Change Notice.
		@param M_ChangeNotice_ID 
		Bill of Materials (Engineering) Change Notice (Version)
	  */
	public void setM_ChangeNotice_ID (int M_ChangeNotice_ID)
	{
		if (M_ChangeNotice_ID < 1) 
			set_Value (COLUMNNAME_M_ChangeNotice_ID, null);
		else 
			set_Value (COLUMNNAME_M_ChangeNotice_ID, Integer.valueOf(M_ChangeNotice_ID));
	}

	/** Get Change Notice.
		@return Bill of Materials (Engineering) Change Notice (Version)
	  */
	public int getM_ChangeNotice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ChangeNotice_ID);
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

	public I_PP_Product_BOM getPP_Product_BOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PP_Product_BOM.Table_Name);
        I_PP_Product_BOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PP_Product_BOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getPP_Product_BOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set BOM & Formula.
		@param PP_Product_BOM_ID 
		BOM & Formula
	  */
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID)
	{
		if (PP_Product_BOM_ID < 1) 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, Integer.valueOf(PP_Product_BOM_ID));
	}

	/** Get BOM & Formula.
		@return BOM & Formula
	  */
	public int getPP_Product_BOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Product_BOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Group.
		@param R_Group_ID 
		Request Group
	  */
	public void setR_Group_ID (int R_Group_ID)
	{
		if (R_Group_ID < 1)
			 throw new IllegalArgumentException ("R_Group_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_Group_ID, Integer.valueOf(R_Group_ID));
	}

	/** Get Group.
		@return Request Group
	  */
	public int getR_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}