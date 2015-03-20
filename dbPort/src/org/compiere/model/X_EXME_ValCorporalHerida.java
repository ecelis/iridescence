/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ValCorporalHerida
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ValCorporalHerida extends PO implements I_EXME_ValCorporalHerida, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ValCorporalHerida (Properties ctx, int EXME_ValCorporalHerida_ID, String trxName)
    {
      super (ctx, EXME_ValCorporalHerida_ID, trxName);
      /** if (EXME_ValCorporalHerida_ID == 0)
        {
			setEXME_ParteCorporal_ID (0);
			setEXME_ValCorporalHerida_ID (0);
			setEXME_ValCorporal_ID (0);
			setHerida (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ValCorporalHerida (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ValCorporalHerida[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_HeridaQCA getEXME_HeridaQCA() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_HeridaQCA.Table_Name);
        I_EXME_HeridaQCA result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_HeridaQCA)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_HeridaQCA_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Surgical wound.
		@param EXME_HeridaQCA_ID Surgical wound	  */
	public void setEXME_HeridaQCA_ID (int EXME_HeridaQCA_ID)
	{
		if (EXME_HeridaQCA_ID < 1) 
			set_Value (COLUMNNAME_EXME_HeridaQCA_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_HeridaQCA_ID, Integer.valueOf(EXME_HeridaQCA_ID));
	}

	/** Get Surgical wound.
		@return Surgical wound	  */
	public int getEXME_HeridaQCA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_HeridaQCA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ParteCorporal getEXME_ParteCorporal() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ParteCorporal.Table_Name);
        I_EXME_ParteCorporal result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ParteCorporal)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ParteCorporal_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Body part.
		@param EXME_ParteCorporal_ID Body part	  */
	public void setEXME_ParteCorporal_ID (int EXME_ParteCorporal_ID)
	{
		if (EXME_ParteCorporal_ID < 1)
			 throw new IllegalArgumentException ("EXME_ParteCorporal_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ParteCorporal_ID, Integer.valueOf(EXME_ParteCorporal_ID));
	}

	/** Get Body part.
		@return Body part	  */
	public int getEXME_ParteCorporal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ParteCorporal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Injury Body Valuation.
		@param EXME_ValCorporalHerida_ID Injury Body Valuation	  */
	public void setEXME_ValCorporalHerida_ID (int EXME_ValCorporalHerida_ID)
	{
		if (EXME_ValCorporalHerida_ID < 1)
			 throw new IllegalArgumentException ("EXME_ValCorporalHerida_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ValCorporalHerida_ID, Integer.valueOf(EXME_ValCorporalHerida_ID));
	}

	/** Get Injury Body Valuation.
		@return Injury Body Valuation	  */
	public int getEXME_ValCorporalHerida_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ValCorporalHerida_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ValCorporal getEXME_ValCorporal() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ValCorporal.Table_Name);
        I_EXME_ValCorporal result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ValCorporal)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ValCorporal_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Body Valuation.
		@param EXME_ValCorporal_ID Body Valuation	  */
	public void setEXME_ValCorporal_ID (int EXME_ValCorporal_ID)
	{
		if (EXME_ValCorporal_ID < 1)
			 throw new IllegalArgumentException ("EXME_ValCorporal_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ValCorporal_ID, Integer.valueOf(EXME_ValCorporal_ID));
	}

	/** Get Body Valuation.
		@return Body Valuation	  */
	public int getEXME_ValCorporal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ValCorporal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Wound.
		@param Herida Wound	  */
	public void setHerida (String Herida)
	{
		if (Herida == null)
			throw new IllegalArgumentException ("Herida is mandatory.");
		set_Value (COLUMNNAME_Herida, Herida);
	}

	/** Get Wound.
		@return Wound	  */
	public String getHerida () 
	{
		return (String)get_Value(COLUMNNAME_Herida);
	}
}