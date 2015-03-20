/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_UserAut
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_UserAut extends PO implements I_EXME_UserAut, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_UserAut (Properties ctx, int EXME_UserAut_ID, String trxName)
    {
      super (ctx, EXME_UserAut_ID, trxName);
      /** if (EXME_UserAut_ID == 0)
        {
			setAD_UserAut_ID (0);
			setAD_User_ID (0);
			setC_DocType_ID (0);
			setEXME_UserAut_ID (0);
			setImporteMax (0);
			setImporteMin (0);
			setLine (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_UserAut (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_UserAut[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User Authorizes.
		@param AD_UserAut_ID 
		User that Authorizes
	  */
	public void setAD_UserAut_ID (int AD_UserAut_ID)
	{
		if (AD_UserAut_ID < 1)
			 throw new IllegalArgumentException ("AD_UserAut_ID is mandatory.");
		set_Value (COLUMNNAME_AD_UserAut_ID, Integer.valueOf(AD_UserAut_ID));
	}

	/** Get User Authorizes.
		@return User that Authorizes
	  */
	public int getAD_UserAut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_UserAut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_C_DocType getC_DocType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_DocType.Table_Name);
        I_C_DocType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DocType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DocType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0)
			 throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
		set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User to Authorize.
		@param EXME_UserAut_ID 
		User that are going away to authorize
	  */
	public void setEXME_UserAut_ID (int EXME_UserAut_ID)
	{
		if (EXME_UserAut_ID < 1)
			 throw new IllegalArgumentException ("EXME_UserAut_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_UserAut_ID, Integer.valueOf(EXME_UserAut_ID));
	}

	/** Get User to Authorize.
		@return User that are going away to authorize
	  */
	public int getEXME_UserAut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_UserAut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Max Amount.
		@param ImporteMax Max Amount	  */
	public void setImporteMax (int ImporteMax)
	{
		set_Value (COLUMNNAME_ImporteMax, Integer.valueOf(ImporteMax));
	}

	/** Get Max Amount.
		@return Max Amount	  */
	public int getImporteMax () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ImporteMax);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Min Amount.
		@param ImporteMin Min Amount	  */
	public void setImporteMin (int ImporteMin)
	{
		set_Value (COLUMNNAME_ImporteMin, Integer.valueOf(ImporteMin));
	}

	/** Get Min Amount.
		@return Min Amount	  */
	public int getImporteMin () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ImporteMin);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}