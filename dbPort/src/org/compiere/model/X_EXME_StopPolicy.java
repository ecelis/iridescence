/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_StopPolicy
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_StopPolicy extends PO implements I_EXME_StopPolicy, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_StopPolicy (Properties ctx, int EXME_StopPolicy_ID, String trxName)
    {
      super (ctx, EXME_StopPolicy_ID, trxName);
      /** if (EXME_StopPolicy_ID == 0)
        {
			setC_UOM_ID (0);
			setEXME_ProductFam_ID (0);
			setEXME_StopPolicy_ID (0);
			setMaxAutoDuration (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_StopPolicy (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_StopPolicy[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
		set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProductFam getEXME_ProductFam() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProductFam.Table_Name);
        I_EXME_ProductFam result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProductFam)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProductFam_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Family Products.
		@param EXME_ProductFam_ID 
		Family Products
	  */
	public void setEXME_ProductFam_ID (int EXME_ProductFam_ID)
	{
		if (EXME_ProductFam_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductFam_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ProductFam_ID, Integer.valueOf(EXME_ProductFam_ID));
	}

	/** Get Family Products.
		@return Family Products
	  */
	public int getEXME_ProductFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductFam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Stop Policy ID.
		@param EXME_StopPolicy_ID Stop Policy ID	  */
	public void setEXME_StopPolicy_ID (int EXME_StopPolicy_ID)
	{
		if (EXME_StopPolicy_ID < 1)
			 throw new IllegalArgumentException ("EXME_StopPolicy_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_StopPolicy_ID, Integer.valueOf(EXME_StopPolicy_ID));
	}

	/** Get Stop Policy ID.
		@return Stop Policy ID	  */
	public int getEXME_StopPolicy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_StopPolicy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Max Automatic Duration.
		@param MaxAutoDuration Max Automatic Duration	  */
	public void setMaxAutoDuration (int MaxAutoDuration)
	{
		set_Value (COLUMNNAME_MaxAutoDuration, Integer.valueOf(MaxAutoDuration));
	}

	/** Get Max Automatic Duration.
		@return Max Automatic Duration	  */
	public int getMaxAutoDuration () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxAutoDuration);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}