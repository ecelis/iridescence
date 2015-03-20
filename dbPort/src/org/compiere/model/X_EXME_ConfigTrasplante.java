/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ConfigTrasplante
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ConfigTrasplante extends PO implements I_EXME_ConfigTrasplante, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigTrasplante (Properties ctx, int EXME_ConfigTrasplante_ID, String trxName)
    {
      super (ctx, EXME_ConfigTrasplante_ID, trxName);
      /** if (EXME_ConfigTrasplante_ID == 0)
        {
			setEXME_ConfigTrasplante_ID (0);
			setTiempoEspera (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigTrasplante (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigTrasplante[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Transplant Configuration.
		@param EXME_ConfigTrasplante_ID Transplant Configuration	  */
	public void setEXME_ConfigTrasplante_ID (int EXME_ConfigTrasplante_ID)
	{
		if (EXME_ConfigTrasplante_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigTrasplante_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigTrasplante_ID, Integer.valueOf(EXME_ConfigTrasplante_ID));
	}

	/** Get Transplant Configuration.
		@return Transplant Configuration	  */
	public int getEXME_ConfigTrasplante_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigTrasplante_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Waiting Time.
		@param TiempoEspera 
		Waiting Time (in months)
	  */
	public void setTiempoEspera (int TiempoEspera)
	{
		set_Value (COLUMNNAME_TiempoEspera, Integer.valueOf(TiempoEspera));
	}

	/** Get Waiting Time.
		@return Waiting Time (in months)
	  */
	public int getTiempoEspera () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TiempoEspera);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}