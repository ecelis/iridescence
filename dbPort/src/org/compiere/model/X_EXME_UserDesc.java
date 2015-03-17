/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_UserDesc
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_UserDesc extends PO implements I_EXME_UserDesc, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_UserDesc (Properties ctx, int EXME_UserDesc_ID, String trxName)
    {
      super (ctx, EXME_UserDesc_ID, trxName);
      /** if (EXME_UserDesc_ID == 0)
        {
			setAD_User_ID (0);
			setEXME_Descuentos_ID (0);
			setEXME_UserDesc_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_UserDesc (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_UserDesc[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Descuentos getEXME_Descuentos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Descuentos.Table_Name);
        I_EXME_Descuentos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Descuentos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Descuentos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Discount.
		@param EXME_Descuentos_ID Discount	  */
	public void setEXME_Descuentos_ID (int EXME_Descuentos_ID)
	{
		if (EXME_Descuentos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Descuentos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Descuentos_ID, Integer.valueOf(EXME_Descuentos_ID));
	}

	/** Get Discount.
		@return Discount	  */
	public int getEXME_Descuentos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Descuentos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Discount .
		@param EXME_UserDesc_ID User Discount 	  */
	public void setEXME_UserDesc_ID (int EXME_UserDesc_ID)
	{
		if (EXME_UserDesc_ID < 1)
			 throw new IllegalArgumentException ("EXME_UserDesc_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_UserDesc_ID, Integer.valueOf(EXME_UserDesc_ID));
	}

	/** Get User Discount .
		@return User Discount 	  */
	public int getEXME_UserDesc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_UserDesc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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