/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for IX_Provision
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_IX_Provision extends PO implements I_IX_Provision, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_IX_Provision (Properties ctx, int IX_Provision_ID, String trxName)
    {
      super (ctx, IX_Provision_ID, trxName);
      /** if (IX_Provision_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_IX_Provision (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_IX_Provision[")
        .append(get_ID()).append("]");
      return sb.toString();
    }
}