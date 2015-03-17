/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_TipoPacArea
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_TipoPacArea extends PO implements I_EXME_TipoPacArea, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TipoPacArea (Properties ctx, int EXME_TipoPacArea_ID, String trxName)
    {
      super (ctx, EXME_TipoPacArea_ID, trxName);
      /** if (EXME_TipoPacArea_ID == 0)
        {
			setEXME_Area_ID (0);
			setEXME_TipoPacArea_ID (0);
			setEXME_TipoPaciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_TipoPacArea (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_TipoPacArea[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Area getEXME_Area() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Area.Table_Name);
        I_EXME_Area result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Area)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Area_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service.
		@param EXME_Area_ID 
		Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1)
			 throw new IllegalArgumentException ("EXME_Area_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Service.
		@return Service
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_TipoPacArea_ID.
		@param EXME_TipoPacArea_ID EXME_TipoPacArea_ID	  */
	public void setEXME_TipoPacArea_ID (int EXME_TipoPacArea_ID)
	{
		if (EXME_TipoPacArea_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoPacArea_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TipoPacArea_ID, Integer.valueOf(EXME_TipoPacArea_ID));
	}

	/** Get EXME_TipoPacArea_ID.
		@return EXME_TipoPacArea_ID	  */
	public int getEXME_TipoPacArea_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPacArea_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoPaciente getEXME_TipoPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoPaciente.Table_Name);
        I_EXME_TipoPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Type of Patient.
		@param EXME_TipoPaciente_ID 
		Type of Patient
	  */
	public void setEXME_TipoPaciente_ID (int EXME_TipoPaciente_ID)
	{
		if (EXME_TipoPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoPaciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoPaciente_ID, Integer.valueOf(EXME_TipoPaciente_ID));
	}

	/** Get Type of Patient.
		@return Type of Patient
	  */
	public int getEXME_TipoPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}