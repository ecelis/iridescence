/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_EsqInsulinaLine
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_EsqInsulinaLine extends PO implements I_EXME_EsqInsulinaLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EsqInsulinaLine (Properties ctx, int EXME_EsqInsulinaLine_ID, String trxName)
    {
      super (ctx, EXME_EsqInsulinaLine_ID, trxName);
      /** if (EXME_EsqInsulinaLine_ID == 0)
        {
			setEXME_EsqInsulinaLine_ID (0);
			setEXME_EsqInsulina_ID (0);
			setLim_Inferior (0);
			setLim_Superior (0);
			setUnidad (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EsqInsulinaLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_EsqInsulinaLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Sliding Scale detail.
		@param EXME_EsqInsulinaLine_ID Sliding Scale detail	  */
	public void setEXME_EsqInsulinaLine_ID (int EXME_EsqInsulinaLine_ID)
	{
		if (EXME_EsqInsulinaLine_ID < 1)
			 throw new IllegalArgumentException ("EXME_EsqInsulinaLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EsqInsulinaLine_ID, Integer.valueOf(EXME_EsqInsulinaLine_ID));
	}

	/** Get Sliding Scale detail.
		@return Sliding Scale detail	  */
	public int getEXME_EsqInsulinaLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EsqInsulinaLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EsqInsulina getEXME_EsqInsulina() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EsqInsulina.Table_Name);
        I_EXME_EsqInsulina result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EsqInsulina)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EsqInsulina_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Insulin Scheme.
		@param EXME_EsqInsulina_ID Insulin Scheme	  */
	public void setEXME_EsqInsulina_ID (int EXME_EsqInsulina_ID)
	{
		if (EXME_EsqInsulina_ID < 1)
			 throw new IllegalArgumentException ("EXME_EsqInsulina_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EsqInsulina_ID, Integer.valueOf(EXME_EsqInsulina_ID));
	}

	/** Get Insulin Scheme.
		@return Insulin Scheme	  */
	public int getEXME_EsqInsulina_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EsqInsulina_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_EXME_GenProduct_ID, null);
		else 
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

	/** Set Instructions.
		@param Instructions Instructions	  */
	public void setInstructions (String Instructions)
	{
		set_Value (COLUMNNAME_Instructions, Instructions);
	}

	/** Get Instructions.
		@return Instructions	  */
	public String getInstructions () 
	{
		return (String)get_Value(COLUMNNAME_Instructions);
	}

	/** Set Minimum Level.
		@param Lim_Inferior Minimum Level	  */
	public void setLim_Inferior (int Lim_Inferior)
	{
		set_Value (COLUMNNAME_Lim_Inferior, Integer.valueOf(Lim_Inferior));
	}

	/** Get Minimum Level.
		@return Minimum Level	  */
	public int getLim_Inferior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Lim_Inferior);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximum Level.
		@param Lim_Superior Maximum Level	  */
	public void setLim_Superior (int Lim_Superior)
	{
		set_Value (COLUMNNAME_Lim_Superior, Integer.valueOf(Lim_Superior));
	}

	/** Get Maximum Level.
		@return Maximum Level	  */
	public int getLim_Superior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Lim_Superior);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unity.
		@param Unidad Unity	  */
	public void setUnidad (int Unidad)
	{
		set_Value (COLUMNNAME_Unidad, Integer.valueOf(Unidad));
	}

	/** Get Unity.
		@return Unity	  */
	public int getUnidad () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Unidad);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}