/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_HomeHealthBilling
 *  @author Generated Class 
 *  @version Release 1.2.0 - $Id$ */
public class X_EXME_HomeHealthBilling extends PO implements I_EXME_HomeHealthBilling, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_HomeHealthBilling (Properties ctx, int EXME_HomeHealthBilling_ID, String trxName)
    {
      super (ctx, EXME_HomeHealthBilling_ID, trxName);
      /** if (EXME_HomeHealthBilling_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_HomeHealthBilling_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_HomeHealthBilling (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_HomeHealthBilling[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Home Health Billing.
		@param EXME_HomeHealthBilling_ID Home Health Billing	  */
	public void setEXME_HomeHealthBilling_ID (int EXME_HomeHealthBilling_ID)
	{
		if (EXME_HomeHealthBilling_ID < 1)
			 throw new IllegalArgumentException ("EXME_HomeHealthBilling_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_HomeHealthBilling_ID, Integer.valueOf(EXME_HomeHealthBilling_ID));
	}

	/** Get Home Health Billing.
		@return Home Health Billing	  */
	public int getEXME_HomeHealthBilling_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_HomeHealthBilling_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}