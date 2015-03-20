/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_EnfControladaDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EnfControladaDet extends PO implements I_EXME_EnfControladaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EnfControladaDet (Properties ctx, int EXME_EnfControladaDet_ID, String trxName)
    {
      super (ctx, EXME_EnfControladaDet_ID, trxName);
      /** if (EXME_EnfControladaDet_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_EnfControladaDet_ID (0);
			setEXME_EnfControlada_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EnfControladaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EnfControladaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Controlled Illness Detail.
		@param EXME_EnfControladaDet_ID 
		Controlled Illness Detail
	  */
	public void setEXME_EnfControladaDet_ID (int EXME_EnfControladaDet_ID)
	{
		if (EXME_EnfControladaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfControladaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnfControladaDet_ID, Integer.valueOf(EXME_EnfControladaDet_ID));
	}

	/** Get Controlled Illness Detail.
		@return Controlled Illness Detail
	  */
	public int getEXME_EnfControladaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfControladaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Controlled Illness.
		@param EXME_EnfControlada_ID 
		Controlled Illness
	  */
	public void setEXME_EnfControlada_ID (int EXME_EnfControlada_ID)
	{
		if (EXME_EnfControlada_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfControlada_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnfControlada_ID, Integer.valueOf(EXME_EnfControlada_ID));
	}

	/** Get Controlled Illness.
		@return Controlled Illness
	  */
	public int getEXME_EnfControlada_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfControlada_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}