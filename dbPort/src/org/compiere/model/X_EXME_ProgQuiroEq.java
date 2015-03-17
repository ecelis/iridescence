/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProgQuiroEq
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProgQuiroEq extends PO implements I_EXME_ProgQuiroEq, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProgQuiroEq (Properties ctx, int EXME_ProgQuiroEq_ID, String trxName)
    {
      super (ctx, EXME_ProgQuiroEq_ID, trxName);
      /** if (EXME_ProgQuiroEq_ID == 0)
        {
			setEXME_Equipo_ID (0);
			setEXME_ProgQuiro_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProgQuiroEq (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProgQuiroEq[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Equipo getEXME_Equipo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Equipo.Table_Name);
        I_EXME_Equipo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Equipo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Equipo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Equipment.
		@param EXME_Equipo_ID 
		Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID)
	{
		if (EXME_Equipo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Equipo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Equipo_ID, Integer.valueOf(EXME_Equipo_ID));
	}

	/** Get Equipment.
		@return Equipment
	  */
	public int getEXME_Equipo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Equipo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProgQuiro getEXME_ProgQuiro() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProgQuiro.Table_Name);
        I_EXME_ProgQuiro result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProgQuiro)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProgQuiro_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Schedule of Surgery Room.
		@param EXME_ProgQuiro_ID 
		Schedule of Surgery Room
	  */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID)
	{
		if (EXME_ProgQuiro_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProgQuiro_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProgQuiro_ID, Integer.valueOf(EXME_ProgQuiro_ID));
	}

	/** Get Schedule of Surgery Room.
		@return Schedule of Surgery Room
	  */
	public int getEXME_ProgQuiro_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgQuiro_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}