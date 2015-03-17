/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_RD_User_Access
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_RD_User_Access extends PO implements I_EXME_RD_User_Access, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RD_User_Access (Properties ctx, int EXME_RD_User_Access_ID, String trxName)
    {
      super (ctx, EXME_RD_User_Access_ID, trxName);
      /** if (EXME_RD_User_Access_ID == 0)
        {
			setAD_User_ID (0);
			setEXME_ReportDesigner_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_RD_User_Access (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RD_User_Access[")
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

	public I_EXME_ReportDesigner getEXME_ReportDesigner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ReportDesigner.Table_Name);
        I_EXME_ReportDesigner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ReportDesigner)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ReportDesigner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Report Designer ID.
		@param EXME_ReportDesigner_ID 
		Report Designer ID
	  */
	public void setEXME_ReportDesigner_ID (int EXME_ReportDesigner_ID)
	{
		if (EXME_ReportDesigner_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReportDesigner_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ReportDesigner_ID, Integer.valueOf(EXME_ReportDesigner_ID));
	}

	/** Get Report Designer ID.
		@return Report Designer ID
	  */
	public int getEXME_ReportDesigner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReportDesigner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}