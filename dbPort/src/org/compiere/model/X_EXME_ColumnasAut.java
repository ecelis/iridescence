/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ColumnasAut
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ColumnasAut extends PO implements I_EXME_ColumnasAut, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ColumnasAut (Properties ctx, int EXME_ColumnasAut_ID, String trxName)
    {
      super (ctx, EXME_ColumnasAut_ID, trxName);
      /** if (EXME_ColumnasAut_ID == 0)
        {
			setAD_Column_ID (0);
			setEXME_ColumnasAut_ID (0);
			setEXME_TablasAut_ID (0);
			setShow (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ColumnasAut (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ColumnasAut[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Column getAD_Column() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Column.Table_Name);
        I_AD_Column result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Column)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Column_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1)
			 throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Columns to Authorize.
		@param EXME_ColumnasAut_ID 
		Columns that are going away to authorize
	  */
	public void setEXME_ColumnasAut_ID (int EXME_ColumnasAut_ID)
	{
		if (EXME_ColumnasAut_ID < 1)
			 throw new IllegalArgumentException ("EXME_ColumnasAut_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ColumnasAut_ID, Integer.valueOf(EXME_ColumnasAut_ID));
	}

	/** Get Columns to Authorize.
		@return Columns that are going away to authorize
	  */
	public int getEXME_ColumnasAut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ColumnasAut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TablasAut getEXME_TablasAut() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TablasAut.Table_Name);
        I_EXME_TablasAut result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TablasAut)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TablasAut_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Tables to Authorize.
		@param EXME_TablasAut_ID 
		Tables that are going away to authorize
	  */
	public void setEXME_TablasAut_ID (int EXME_TablasAut_ID)
	{
		if (EXME_TablasAut_ID < 1)
			 throw new IllegalArgumentException ("EXME_TablasAut_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TablasAut_ID, Integer.valueOf(EXME_TablasAut_ID));
	}

	/** Get Tables to Authorize.
		@return Tables that are going away to authorize
	  */
	public int getEXME_TablasAut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TablasAut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shown.
		@param Show 
		Shown
	  */
	public void setShow (boolean Show)
	{
		set_Value (COLUMNNAME_Show, Boolean.valueOf(Show));
	}

	/** Get Shown.
		@return Shown
	  */
	public boolean isShow () 
	{
		Object oo = get_Value(COLUMNNAME_Show);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}