/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_RecursoEducativoProd
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RecursoEducativoProd extends PO implements I_EXME_RecursoEducativoProd, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RecursoEducativoProd (Properties ctx, int EXME_RecursoEducativoProd_ID, String trxName)
    {
      super (ctx, EXME_RecursoEducativoProd_ID, trxName);
      /** if (EXME_RecursoEducativoProd_ID == 0)
        {
			setEXME_RecursoEducativo_ID (0);
			setEXME_RecursoEducativoProd_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_RecursoEducativoProd (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RecursoEducativoProd[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RecursoEducativo.Table_Name);
        I_EXME_RecursoEducativo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RecursoEducativo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RecursoEducativo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Education Resource.
		@param EXME_RecursoEducativo_ID Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID)
	{
		if (EXME_RecursoEducativo_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_RecursoEducativo_ID, Integer.valueOf(EXME_RecursoEducativo_ID));
	}

	/** Get Education Resource.
		@return Education Resource	  */
	public int getEXME_RecursoEducativo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medication Education Resource.
		@param EXME_RecursoEducativoProd_ID Medication Education Resource	  */
	public void setEXME_RecursoEducativoProd_ID (int EXME_RecursoEducativoProd_ID)
	{
		if (EXME_RecursoEducativoProd_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativoProd_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RecursoEducativoProd_ID, Integer.valueOf(EXME_RecursoEducativoProd_ID));
	}

	/** Get Medication Education Resource.
		@return Medication Education Resource	  */
	public int getEXME_RecursoEducativoProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativoProd_ID);
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