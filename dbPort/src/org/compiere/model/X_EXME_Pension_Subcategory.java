/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Pension_Subcategory
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Pension_Subcategory extends PO implements I_EXME_Pension_Subcategory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Pension_Subcategory (Properties ctx, int EXME_Pension_Subcategory_ID, String trxName)
    {
      super (ctx, EXME_Pension_Subcategory_ID, trxName);
      /** if (EXME_Pension_Subcategory_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_Pension_Subcategory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Pension_Subcategory[")
        .append(get_ID()).append("]");
      return sb.toString();
    }
}