/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_T_GenProd_Trade
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_T_GenProd_Trade extends PO implements I_EXME_T_GenProd_Trade, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_T_GenProd_Trade (Properties ctx, int EXME_T_GenProd_Trade_ID, String trxName)
    {
      super (ctx, EXME_T_GenProd_Trade_ID, trxName);
      /** if (EXME_T_GenProd_Trade_ID == 0)
        {
			setEXME_GenProduct_ID (0);
			setEXME_T_GenProd_Trade_ID (0);
			setGeneric_Product_Name (null);
			setIsFormulary (false);
			setIsPrefer (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_T_GenProd_Trade (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_T_GenProd_Trade[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GenProduct.Table_Name);
        I_EXME_GenProduct result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GenProduct)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GenProduct_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Generic Product.
		@param EXME_GenProduct_ID Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID)
	{
		if (EXME_GenProduct_ID < 1)
			 throw new IllegalArgumentException ("EXME_GenProduct_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_GenProduct_ID, Integer.valueOf(EXME_GenProduct_ID));
	}

	/** Get Generic Product.
		@return Generic Product	  */
	public int getEXME_GenProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generic Product And Trade Name Id.
		@param EXME_T_GenProd_Trade_ID Generic Product And Trade Name Id	  */
	public void setEXME_T_GenProd_Trade_ID (int EXME_T_GenProd_Trade_ID)
	{
		if (EXME_T_GenProd_Trade_ID < 1)
			 throw new IllegalArgumentException ("EXME_T_GenProd_Trade_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_T_GenProd_Trade_ID, Integer.valueOf(EXME_T_GenProd_Trade_ID));
	}

	/** Get Generic Product And Trade Name Id.
		@return Generic Product And Trade Name Id	  */
	public int getEXME_T_GenProd_Trade_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_T_GenProd_Trade_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generic Product Name.
		@param Generic_Product_Name Generic Product Name	  */
	public void setGeneric_Product_Name (String Generic_Product_Name)
	{
		if (Generic_Product_Name == null)
			throw new IllegalArgumentException ("Generic_Product_Name is mandatory.");
		set_Value (COLUMNNAME_Generic_Product_Name, Generic_Product_Name);
	}

	/** Get Generic Product Name.
		@return Generic Product Name	  */
	public String getGeneric_Product_Name () 
	{
		return (String)get_Value(COLUMNNAME_Generic_Product_Name);
	}

	/** Set In formulary.
		@param IsFormulary 
		In formulary
	  */
	public void setIsFormulary (boolean IsFormulary)
	{
		set_Value (COLUMNNAME_IsFormulary, Boolean.valueOf(IsFormulary));
	}

	/** Get In formulary.
		@return In formulary
	  */
	public boolean isFormulary () 
	{
		Object oo = get_Value(COLUMNNAME_IsFormulary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Prefered Medication.
		@param IsPrefer 
		Is Prefered Medication
	  */
	public void setIsPrefer (boolean IsPrefer)
	{
		set_Value (COLUMNNAME_IsPrefer, Boolean.valueOf(IsPrefer));
	}

	/** Get Is Prefered Medication.
		@return Is Prefered Medication
	  */
	public boolean isPrefer () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrefer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Trade Name.
		@param Trade_Name Trade Name	  */
	public void setTrade_Name (String Trade_Name)
	{
		set_Value (COLUMNNAME_Trade_Name, Trade_Name);
	}

	/** Get Trade Name.
		@return Trade Name	  */
	public String getTrade_Name () 
	{
		return (String)get_Value(COLUMNNAME_Trade_Name);
	}
}