/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_TablasAut
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_TablasAut extends PO implements I_EXME_TablasAut, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TablasAut (Properties ctx, int EXME_TablasAut_ID, String trxName)
    {
      super (ctx, EXME_TablasAut_ID, trxName);
      /** if (EXME_TablasAut_ID == 0)
        {
			setAD_Table_ID (0);
			setEXME_TablasAut_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_TablasAut (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TablasAut[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}