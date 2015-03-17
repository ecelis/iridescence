/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Manifiesto
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Manifiesto extends PO implements I_EXME_Manifiesto, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Manifiesto (Properties ctx, int EXME_Manifiesto_ID, String trxName)
    {
      super (ctx, EXME_Manifiesto_ID, trxName);
      /** if (EXME_Manifiesto_ID == 0)
        {
			setEXME_Manifiesto_ID (0);
			setFechaRec (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setNoManifiesto (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Manifiesto (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Manifiesto[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Manifest.
		@param EXME_Manifiesto_ID 
		It contains the vouchers issued by the garbage collector
	  */
	public void setEXME_Manifiesto_ID (int EXME_Manifiesto_ID)
	{
		if (EXME_Manifiesto_ID < 1)
			 throw new IllegalArgumentException ("EXME_Manifiesto_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Manifiesto_ID, Integer.valueOf(EXME_Manifiesto_ID));
	}

	/** Get Manifest.
		@return It contains the vouchers issued by the garbage collector
	  */
	public int getEXME_Manifiesto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Manifiesto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reception Date.
		@param FechaRec 
		Contains the date on which it carries out waste collection
	  */
	public void setFechaRec (Timestamp FechaRec)
	{
		if (FechaRec == null)
			throw new IllegalArgumentException ("FechaRec is mandatory.");
		set_Value (COLUMNNAME_FechaRec, FechaRec);
	}

	/** Get Reception Date.
		@return Contains the date on which it carries out waste collection
	  */
	public Timestamp getFechaRec () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaRec);
	}

	/** Set Manifesto Number.
		@param NoManifiesto 
		Specifies the folio number of the voucher issued by the garbage collector on the part of the service provider
	  */
	public void setNoManifiesto (String NoManifiesto)
	{
		if (NoManifiesto == null)
			throw new IllegalArgumentException ("NoManifiesto is mandatory.");
		set_Value (COLUMNNAME_NoManifiesto, NoManifiesto);
	}

	/** Get Manifesto Number.
		@return Specifies the folio number of the voucher issued by the garbage collector on the part of the service provider
	  */
	public String getNoManifiesto () 
	{
		return (String)get_Value(COLUMNNAME_NoManifiesto);
	}
}