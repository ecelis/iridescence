/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_PaqBaseAtributo
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_PaqBaseAtributo extends PO implements I_EXME_PaqBaseAtributo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PaqBaseAtributo (Properties ctx, int EXME_PaqBaseAtributo_ID, String trxName)
    {
      super (ctx, EXME_PaqBaseAtributo_ID, trxName);
      /** if (EXME_PaqBaseAtributo_ID == 0)
        {
			setAD_Table_ID (0);
			setEXME_PaqBaseAtributo_ID (0);
			setEXME_PaqBaseDet_ID (0);
			setLine (Env.ZERO);
// 1
			setRecord_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PaqBaseAtributo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PaqBaseAtributo[")
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

	/** Set Package attributes.
		@param EXME_PaqBaseAtributo_ID Package attributes	  */
	public void setEXME_PaqBaseAtributo_ID (int EXME_PaqBaseAtributo_ID)
	{
		if (EXME_PaqBaseAtributo_ID < 1)
			 throw new IllegalArgumentException ("EXME_PaqBaseAtributo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PaqBaseAtributo_ID, Integer.valueOf(EXME_PaqBaseAtributo_ID));
	}

	/** Get Package attributes.
		@return Package attributes	  */
	public int getEXME_PaqBaseAtributo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBaseAtributo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PaqBaseDet getEXME_PaqBaseDet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBaseDet.Table_Name);
        I_EXME_PaqBaseDet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBaseDet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBaseDet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Detail of Base Package.
		@param EXME_PaqBaseDet_ID 
		Detail of Base Package
	  */
	public void setEXME_PaqBaseDet_ID (int EXME_PaqBaseDet_ID)
	{
		if (EXME_PaqBaseDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PaqBaseDet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PaqBaseDet_ID, Integer.valueOf(EXME_PaqBaseDet_ID));
	}

	/** Get Detail of Base Package.
		@return Detail of Base Package
	  */
	public int getEXME_PaqBaseDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBaseDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (BigDecimal Line)
	{
		if (Line == null)
			throw new IllegalArgumentException ("Line is mandatory.");
		set_Value (COLUMNNAME_Line, Line);
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public BigDecimal getLine () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Line);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0)
			 throw new IllegalArgumentException ("Record_ID is mandatory.");
		set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}