/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Group_Diag
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Group_Diag extends PO implements I_EXME_Group_Diag, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Group_Diag (Properties ctx, int EXME_Group_Diag_ID, String trxName)
    {
      super (ctx, EXME_Group_Diag_ID, trxName);
      /** if (EXME_Group_Diag_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_Group_Diag_ID (0);
			setStroke (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Group_Diag (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Group_Diag[")
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

	/** Set Medical diagnostic group.
		@param EXME_Group_Diag_ID Medical diagnostic group	  */
	public void setEXME_Group_Diag_ID (int EXME_Group_Diag_ID)
	{
		if (EXME_Group_Diag_ID < 1)
			 throw new IllegalArgumentException ("EXME_Group_Diag_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Group_Diag_ID, Integer.valueOf(EXME_Group_Diag_ID));
	}

	/** Get Medical diagnostic group.
		@return Medical diagnostic group	  */
	public int getEXME_Group_Diag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Group_Diag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Stroke AD_Reference_ID=1200474 */
	public static final int STROKE_AD_Reference_ID=1200474;
	/** Ischemic Stroke = IS */
	public static final String STROKE_IschemicStroke = "IS";
	/** Hemorragic Stroke = HS */
	public static final String STROKE_HemorragicStroke = "HS";
	/** Venous Thomboembolic = VT */
	public static final String STROKE_VenousThomboembolic = "VT";
	/** Set Stroke.
		@param Stroke Stroke	  */
	public void setStroke (String Stroke)
	{
		if (Stroke == null) throw new IllegalArgumentException ("Stroke is mandatory");
		if (Stroke.equals("IS") || Stroke.equals("HS") || Stroke.equals("VT")); else throw new IllegalArgumentException ("Stroke Invalid value - " + Stroke + " - Reference_ID=1200474 - IS - HS - VT");		set_Value (COLUMNNAME_Stroke, Stroke);
	}

	/** Get Stroke.
		@return Stroke	  */
	public String getStroke () 
	{
		return (String)get_Value(COLUMNNAME_Stroke);
	}
}