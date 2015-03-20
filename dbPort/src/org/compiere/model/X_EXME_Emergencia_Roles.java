/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Emergencia_Roles
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Emergencia_Roles extends PO implements I_EXME_Emergencia_Roles, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Emergencia_Roles (Properties ctx, int EXME_Emergencia_Roles_ID, String trxName)
    {
      super (ctx, EXME_Emergencia_Roles_ID, trxName);
      /** if (EXME_Emergencia_Roles_ID == 0)
        {
			setAD_Role_ID (0);
			setEXME_Emergencia_ID (0);
			setEXME_Emergencia_Roles_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Emergencia_Roles (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Emergencia_Roles[")
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

	public I_EXME_Emergencia getEXME_Emergencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Emergencia.Table_Name);
        I_EXME_Emergencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Emergencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Emergencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Emergency.
		@param EXME_Emergencia_ID Emergency	  */
	public void setEXME_Emergencia_ID (int EXME_Emergencia_ID)
	{
		if (EXME_Emergencia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Emergencia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Emergencia_ID, Integer.valueOf(EXME_Emergencia_ID));
	}

	/** Get Emergency.
		@return Emergency	  */
	public int getEXME_Emergencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Emergencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Emergency Roles.
		@param EXME_Emergencia_Roles_ID Emergency Roles	  */
	public void setEXME_Emergencia_Roles_ID (int EXME_Emergencia_Roles_ID)
	{
		if (EXME_Emergencia_Roles_ID < 1)
			 throw new IllegalArgumentException ("EXME_Emergencia_Roles_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Emergencia_Roles_ID, Integer.valueOf(EXME_Emergencia_Roles_ID));
	}

	/** Get Emergency Roles.
		@return Emergency Roles	  */
	public int getEXME_Emergencia_Roles_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Emergencia_Roles_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}