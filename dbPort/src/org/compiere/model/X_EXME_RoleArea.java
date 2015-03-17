/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_RoleArea
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_RoleArea extends PO implements I_EXME_RoleArea, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RoleArea (Properties ctx, int EXME_RoleArea_ID, String trxName)
    {
      super (ctx, EXME_RoleArea_ID, trxName);
      /** if (EXME_RoleArea_ID == 0)
        {
			setAD_Role_ID (0);
			setEXME_Area_ID (0);
			setEXME_RoleArea_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_RoleArea (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_RoleArea[")
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

	/** Set Area.
		@param EXME_Area_ID 
		Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1)
			 throw new IllegalArgumentException ("EXME_Area_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Area.
		@return Area
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Role Area.
		@param EXME_RoleArea_ID Role Area	  */
	public void setEXME_RoleArea_ID (int EXME_RoleArea_ID)
	{
		if (EXME_RoleArea_ID < 1)
			 throw new IllegalArgumentException ("EXME_RoleArea_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RoleArea_ID, Integer.valueOf(EXME_RoleArea_ID));
	}

	/** Get Role Area.
		@return Role Area	  */
	public int getEXME_RoleArea_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RoleArea_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}