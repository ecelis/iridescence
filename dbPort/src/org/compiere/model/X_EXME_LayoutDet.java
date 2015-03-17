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

/** Generated Model for EXME_LayoutDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_LayoutDet extends PO implements I_EXME_LayoutDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_LayoutDet (Properties ctx, int EXME_LayoutDet_ID, String trxName)
    {
      super (ctx, EXME_LayoutDet_ID, trxName);
      /** if (EXME_LayoutDet_ID == 0)
        {
			setEXME_LayoutDet_ID (0);
			setEXME_Layout_ID (0);
			setLine (0);
			setM_Product_Category_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_LayoutDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_LayoutDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Layout Detail.
		@param EXME_LayoutDet_ID 
		Layout Detail
	  */
	public void setEXME_LayoutDet_ID (int EXME_LayoutDet_ID)
	{
		if (EXME_LayoutDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_LayoutDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_LayoutDet_ID, Integer.valueOf(EXME_LayoutDet_ID));
	}

	/** Get Layout Detail.
		@return Layout Detail
	  */
	public int getEXME_LayoutDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LayoutDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Layout getEXME_Layout() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Layout.Table_Name);
        I_EXME_Layout result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Layout)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Layout_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Layout.
		@param EXME_Layout_ID 
		Layout
	  */
	public void setEXME_Layout_ID (int EXME_Layout_ID)
	{
		if (EXME_Layout_ID < 1)
			 throw new IllegalArgumentException ("EXME_Layout_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Layout_ID, Integer.valueOf(EXME_Layout_ID));
	}

	/** Get Layout.
		@return Layout
	  */
	public int getEXME_Layout_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Layout_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product_Category getM_Product_Category() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product_Category.Table_Name);
        I_M_Product_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1)
			 throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
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