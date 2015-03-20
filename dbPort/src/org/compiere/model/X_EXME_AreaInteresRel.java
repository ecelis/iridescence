/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_AreaInteresRel
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_AreaInteresRel extends PO implements I_EXME_AreaInteresRel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AreaInteresRel (Properties ctx, int EXME_AreaInteresRel_ID, String trxName)
    {
      super (ctx, EXME_AreaInteresRel_ID, trxName);
      /** if (EXME_AreaInteresRel_ID == 0)
        {
			setEXME_AreaInteres_ID (0);
			setEXME_AreaInteresRel_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_AreaInteresRel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AreaInteresRel[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_AreaInteres getEXME_AreaInteres() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_AreaInteres.Table_Name);
        I_EXME_AreaInteres result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_AreaInteres)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_AreaInteres_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Area of interest.
		@param EXME_AreaInteres_ID Area of interest	  */
	public void setEXME_AreaInteres_ID (int EXME_AreaInteres_ID)
	{
		if (EXME_AreaInteres_ID < 1)
			 throw new IllegalArgumentException ("EXME_AreaInteres_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_AreaInteres_ID, Integer.valueOf(EXME_AreaInteres_ID));
	}

	/** Get Area of interest.
		@return Area of interest	  */
	public int getEXME_AreaInteres_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AreaInteres_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interest Area Relation.
		@param EXME_AreaInteresRel_ID Interest Area Relation	  */
	public void setEXME_AreaInteresRel_ID (int EXME_AreaInteresRel_ID)
	{
		if (EXME_AreaInteresRel_ID < 1)
			 throw new IllegalArgumentException ("EXME_AreaInteresRel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AreaInteresRel_ID, Integer.valueOf(EXME_AreaInteresRel_ID));
	}

	/** Get Interest Area Relation.
		@return Interest Area Relation	  */
	public int getEXME_AreaInteresRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AreaInteresRel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProgramaInv getEXME_ProgramaInv() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProgramaInv.Table_Name);
        I_EXME_ProgramaInv result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProgramaInv)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProgramaInv_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Research Program.
		@param EXME_ProgramaInv_ID Research Program	  */
	public void setEXME_ProgramaInv_ID (int EXME_ProgramaInv_ID)
	{
		if (EXME_ProgramaInv_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProgramaInv_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProgramaInv_ID, Integer.valueOf(EXME_ProgramaInv_ID));
	}

	/** Get Research Program.
		@return Research Program	  */
	public int getEXME_ProgramaInv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgramaInv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}