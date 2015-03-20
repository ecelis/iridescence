/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProdPensCatego
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProdPensCatego extends PO implements I_EXME_ProdPensCatego, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProdPensCatego (Properties ctx, int EXME_ProdPensCatego_ID, String trxName)
    {
      super (ctx, EXME_ProdPensCatego_ID, trxName);
      /** if (EXME_ProdPensCatego_ID == 0)
        {
			setEXME_Pension_Category_ID (0);
			setEXME_ProdPensCatego_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProdPensCatego (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProdPensCatego[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Pension_Category getEXME_Pension_Category() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Pension_Category.Table_Name);
        I_EXME_Pension_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Pension_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Pension_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pension Category.
		@param EXME_Pension_Category_ID Pension Category	  */
	public void setEXME_Pension_Category_ID (int EXME_Pension_Category_ID)
	{
		if (EXME_Pension_Category_ID < 1)
			 throw new IllegalArgumentException ("EXME_Pension_Category_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Pension_Category_ID, Integer.valueOf(EXME_Pension_Category_ID));
	}

	/** Get Pension Category.
		@return Pension Category	  */
	public int getEXME_Pension_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pension_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Pension Category.
		@param EXME_ProdPensCatego_ID Product Pension Category	  */
	public void setEXME_ProdPensCatego_ID (int EXME_ProdPensCatego_ID)
	{
		if (EXME_ProdPensCatego_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProdPensCatego_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProdPensCatego_ID, Integer.valueOf(EXME_ProdPensCatego_ID));
	}

	/** Get Product Pension Category.
		@return Product Pension Category	  */
	public int getEXME_ProdPensCatego_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProdPensCatego_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}