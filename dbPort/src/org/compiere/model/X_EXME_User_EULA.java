/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_User_EULA
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_User_EULA extends PO implements I_EXME_User_EULA, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_User_EULA (Properties ctx, int EXME_User_EULA_ID, String trxName)
    {
      super (ctx, EXME_User_EULA_ID, trxName);
      /** if (EXME_User_EULA_ID == 0)
        {
			setAD_User_ID (0);
			setEXME_EULA_Dt_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_User_EULA (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_User_EULA[")
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

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Accepted.
		@param DateAccepted 
		The date on which the user accepted the license.
	  */
	public void setDateAccepted (Timestamp DateAccepted)
	{
		set_Value (COLUMNNAME_DateAccepted, DateAccepted);
	}

	/** Get Date Accepted.
		@return The date on which the user accepted the license.
	  */
	public Timestamp getDateAccepted () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAccepted);
	}

	public I_EXME_EULA_Dt getEXME_EULA_Dt() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EULA_Dt.Table_Name);
        I_EXME_EULA_Dt result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EULA_Dt)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EULA_Dt_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EULA Detail.
		@param EXME_EULA_Dt_ID 
		End User License Agreement Detail
	  */
	public void setEXME_EULA_Dt_ID (int EXME_EULA_Dt_ID)
	{
		if (EXME_EULA_Dt_ID < 1)
			 throw new IllegalArgumentException ("EXME_EULA_Dt_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EULA_Dt_ID, Integer.valueOf(EXME_EULA_Dt_ID));
	}

	/** Get EULA Detail.
		@return End User License Agreement Detail
	  */
	public int getEXME_EULA_Dt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EULA_Dt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}