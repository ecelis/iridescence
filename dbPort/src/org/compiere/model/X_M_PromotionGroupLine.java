/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for M_PromotionGroupLine
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_M_PromotionGroupLine extends PO implements I_M_PromotionGroupLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_PromotionGroupLine (Properties ctx, int M_PromotionGroupLine_ID, String trxName)
    {
      super (ctx, M_PromotionGroupLine_ID, trxName);
      /** if (M_PromotionGroupLine_ID == 0)
        {
			setM_Product_ID (0);
			setM_PromotionGroup_ID (0);
			setM_PromotionGroupLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_PromotionGroupLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_PromotionGroupLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
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

	public I_M_PromotionGroup getM_PromotionGroup() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_PromotionGroup.Table_Name);
        I_M_PromotionGroup result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_PromotionGroup)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_PromotionGroup_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Promotion Group.
		@param M_PromotionGroup_ID Promotion Group	  */
	public void setM_PromotionGroup_ID (int M_PromotionGroup_ID)
	{
		if (M_PromotionGroup_ID < 1)
			 throw new IllegalArgumentException ("M_PromotionGroup_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_PromotionGroup_ID, Integer.valueOf(M_PromotionGroup_ID));
	}

	/** Get Promotion Group.
		@return Promotion Group	  */
	public int getM_PromotionGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PromotionGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Promotion Group Line.
		@param M_PromotionGroupLine_ID Promotion Group Line	  */
	public void setM_PromotionGroupLine_ID (int M_PromotionGroupLine_ID)
	{
		if (M_PromotionGroupLine_ID < 1)
			 throw new IllegalArgumentException ("M_PromotionGroupLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_PromotionGroupLine_ID, Integer.valueOf(M_PromotionGroupLine_ID));
	}

	/** Get Promotion Group Line.
		@return Promotion Group Line	  */
	public int getM_PromotionGroupLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PromotionGroupLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}