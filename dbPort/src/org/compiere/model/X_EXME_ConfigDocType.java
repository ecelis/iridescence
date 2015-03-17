/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ConfigDocType
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ConfigDocType extends PO implements I_EXME_ConfigDocType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigDocType (Properties ctx, int EXME_ConfigDocType_ID, String trxName)
    {
      super (ctx, EXME_ConfigDocType_ID, trxName);
      /** if (EXME_ConfigDocType_ID == 0)
        {
			setC_DocTypeVacuna_ID (0);
			setEXME_ConfigDocType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigDocType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigDocType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Document Type for Vaccine Application.
		@param C_DocTypeVacuna_ID Document Type for Vaccine Application	  */
	public void setC_DocTypeVacuna_ID (int C_DocTypeVacuna_ID)
	{
		if (C_DocTypeVacuna_ID < 1)
			 throw new IllegalArgumentException ("C_DocTypeVacuna_ID is mandatory.");
		set_Value (COLUMNNAME_C_DocTypeVacuna_ID, Integer.valueOf(C_DocTypeVacuna_ID));
	}

	/** Get Document Type for Vaccine Application.
		@return Document Type for Vaccine Application	  */
	public int getC_DocTypeVacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypeVacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Type Configuration.
		@param EXME_ConfigDocType_ID Document Type Configuration	  */
	public void setEXME_ConfigDocType_ID (int EXME_ConfigDocType_ID)
	{
		if (EXME_ConfigDocType_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigDocType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigDocType_ID, Integer.valueOf(EXME_ConfigDocType_ID));
	}

	/** Get Document Type Configuration.
		@return Document Type Configuration	  */
	public int getEXME_ConfigDocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigDocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}