/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_MCBGI
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MCBGI extends PO implements I_EXME_MCBGI, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MCBGI (Properties ctx, int EXME_MCBGI_ID, String trxName)
    {
      super (ctx, EXME_MCBGI_ID, trxName);
      /** if (EXME_MCBGI_ID == 0)
        {
			setC_BPartner_ID (0);
			setEXME_MCBGI_ID (0);
			setM_ProductGI_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MCBGI (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MCBGI[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_MCBGI_ID.
		@param EXME_MCBGI_ID 
		Generic Medicine Maintenance
	  */
	public void setEXME_MCBGI_ID (int EXME_MCBGI_ID)
	{
		if (EXME_MCBGI_ID < 1)
			 throw new IllegalArgumentException ("EXME_MCBGI_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MCBGI_ID, Integer.valueOf(EXME_MCBGI_ID));
	}

	/** Get EXME_MCBGI_ID.
		@return Generic Medicine Maintenance
	  */
	public int getEXME_MCBGI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MCBGI_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Basic Medicine.
		@param M_ProductGI_ID 
		Basic Medicine
	  */
	public void setM_ProductGI_ID (int M_ProductGI_ID)
	{
		if (M_ProductGI_ID < 1)
			 throw new IllegalArgumentException ("M_ProductGI_ID is mandatory.");
		set_Value (COLUMNNAME_M_ProductGI_ID, Integer.valueOf(M_ProductGI_ID));
	}

	/** Get Basic Medicine.
		@return Basic Medicine
	  */
	public int getM_ProductGI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductGI_ID);
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