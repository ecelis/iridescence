/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Reporte_Access
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Reporte_Access extends PO implements I_EXME_Reporte_Access, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Reporte_Access (Properties ctx, int EXME_Reporte_Access_ID, String trxName)
    {
      super (ctx, EXME_Reporte_Access_ID, trxName);
      /** if (EXME_Reporte_Access_ID == 0)
        {
			setAD_Role_ID (0);
			setEXME_Reporte_Access_ID (0);
			setEXME_Reporte_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Reporte_Access (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Reporte_Access[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Role getAD_Role() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Role.Table_Name);
        I_AD_Role result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Role)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Role_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0)
			 throw new IllegalArgumentException ("AD_Role_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Report Access.
		@param EXME_Reporte_Access_ID Report Access	  */
	public void setEXME_Reporte_Access_ID (int EXME_Reporte_Access_ID)
	{
		if (EXME_Reporte_Access_ID < 1)
			 throw new IllegalArgumentException ("EXME_Reporte_Access_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Reporte_Access_ID, Integer.valueOf(EXME_Reporte_Access_ID));
	}

	/** Get Report Access.
		@return Report Access	  */
	public int getEXME_Reporte_Access_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Reporte_Access_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Reporte getEXME_Reporte() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Reporte.Table_Name);
        I_EXME_Reporte result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Reporte)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Reporte_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Report.
		@param EXME_Reporte_ID Report	  */
	public void setEXME_Reporte_ID (int EXME_Reporte_ID)
	{
		if (EXME_Reporte_ID < 1)
			 throw new IllegalArgumentException ("EXME_Reporte_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Reporte_ID, Integer.valueOf(EXME_Reporte_ID));
	}

	/** Get Report.
		@return Report	  */
	public int getEXME_Reporte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Reporte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}