/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_MotivoEgreso
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MotivoEgreso extends PO implements I_EXME_MotivoEgreso, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MotivoEgreso (Properties ctx, int EXME_MotivoEgreso_ID, String trxName)
    {
      super (ctx, EXME_MotivoEgreso_ID, trxName);
      /** if (EXME_MotivoEgreso_ID == 0)
        {
			setEXME_EstServ_ID (0);
// @#EXME_EstServ_ID@
			setEXME_MotivoEgreso_ID (0);
			setIsDead (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MotivoEgreso (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MotivoEgreso[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServ_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discharge Reason.
		@param EXME_MotivoEgreso_ID Discharge Reason	  */
	public void setEXME_MotivoEgreso_ID (int EXME_MotivoEgreso_ID)
	{
		if (EXME_MotivoEgreso_ID < 1)
			 throw new IllegalArgumentException ("EXME_MotivoEgreso_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MotivoEgreso_ID, Integer.valueOf(EXME_MotivoEgreso_ID));
	}

	/** Get Discharge Reason.
		@return Discharge Reason	  */
	public int getEXME_MotivoEgreso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoEgreso_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set It is a diagnosis of death?.
		@param IsDead 
		It is a diagnosis of death?
	  */
	public void setIsDead (boolean IsDead)
	{
		set_Value (COLUMNNAME_IsDead, Boolean.valueOf(IsDead));
	}

	/** Get It is a diagnosis of death?.
		@return It is a diagnosis of death?
	  */
	public boolean isDead () 
	{
		Object oo = get_Value(COLUMNNAME_IsDead);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}