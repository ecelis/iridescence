/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_FactorPrePCategory
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_FactorPrePCategory extends PO implements I_EXME_FactorPrePCategory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FactorPrePCategory (Properties ctx, int EXME_FactorPrePCategory_ID, String trxName)
    {
      super (ctx, EXME_FactorPrePCategory_ID, trxName);
      /** if (EXME_FactorPrePCategory_ID == 0)
        {
			setEXME_FactorPre_ID (0);
			setEXME_FactorPrePCategory_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_FactorPrePCategory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FactorPrePCategory[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_FactorPre getEXME_FactorPre() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FactorPre.Table_Name);
        I_EXME_FactorPre result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FactorPre)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FactorPre_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Price Factor.
		@param EXME_FactorPre_ID 
		Sales Price Factor
	  */
	public void setEXME_FactorPre_ID (int EXME_FactorPre_ID)
	{
		if (EXME_FactorPre_ID < 1)
			 throw new IllegalArgumentException ("EXME_FactorPre_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FactorPre_ID, Integer.valueOf(EXME_FactorPre_ID));
	}

	/** Get Price Factor.
		@return Sales Price Factor
	  */
	public int getEXME_FactorPre_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FactorPre_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price increase rules – category.
		@param EXME_FactorPrePCategory_ID 
		Price increase rules – category
	  */
	public void setEXME_FactorPrePCategory_ID (int EXME_FactorPrePCategory_ID)
	{
		if (EXME_FactorPrePCategory_ID < 1)
			 throw new IllegalArgumentException ("EXME_FactorPrePCategory_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FactorPrePCategory_ID, Integer.valueOf(EXME_FactorPrePCategory_ID));
	}

	/** Get Price increase rules – category.
		@return Price increase rules – category
	  */
	public int getEXME_FactorPrePCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FactorPrePCategory_ID);
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
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
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

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}