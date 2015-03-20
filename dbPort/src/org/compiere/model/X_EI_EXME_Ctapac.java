/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EI_EXME_Ctapac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EI_EXME_Ctapac extends PO implements I_EI_EXME_Ctapac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EI_EXME_Ctapac (Properties ctx, int EI_EXME_Ctapac_ID, String trxName)
    {
      super (ctx, EI_EXME_Ctapac_ID, trxName);
      /** if (EI_EXME_Ctapac_ID == 0)
        {
			setDocumentNo (null);
			setEI_EXME_Ctapac_ID (0);
			setErrorDescription (null);
			setErrorStatus (false);
			setEXME_CtaPac_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EI_EXME_Ctapac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EI_EXME_Ctapac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Patient Account Key.
		@param EI_EXME_Ctapac_ID 
		Patient Account Key
	  */
	public void setEI_EXME_Ctapac_ID (int EI_EXME_Ctapac_ID)
	{
		if (EI_EXME_Ctapac_ID < 1)
			 throw new IllegalArgumentException ("EI_EXME_Ctapac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EI_EXME_Ctapac_ID, Integer.valueOf(EI_EXME_Ctapac_ID));
	}

	/** Get Patient Account Key.
		@return Patient Account Key
	  */
	public int getEI_EXME_Ctapac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EI_EXME_Ctapac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ErrorDescription.
		@param ErrorDescription 
		ErrorDescription
	  */
	public void setErrorDescription (String ErrorDescription)
	{
		if (ErrorDescription == null)
			throw new IllegalArgumentException ("ErrorDescription is mandatory.");
		set_Value (COLUMNNAME_ErrorDescription, ErrorDescription);
	}

	/** Get ErrorDescription.
		@return ErrorDescription
	  */
	public String getErrorDescription () 
	{
		return (String)get_Value(COLUMNNAME_ErrorDescription);
	}

	/** Set ErrorStatus.
		@param ErrorStatus 
		ErrorStatus
	  */
	public void setErrorStatus (boolean ErrorStatus)
	{
		set_Value (COLUMNNAME_ErrorStatus, Boolean.valueOf(ErrorStatus));
	}

	/** Get ErrorStatus.
		@return ErrorStatus
	  */
	public boolean isErrorStatus () 
	{
		Object oo = get_Value(COLUMNNAME_ErrorStatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}