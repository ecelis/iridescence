/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_CuestReport
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CuestReport extends PO implements I_EXME_CuestReport, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CuestReport (Properties ctx, int EXME_CuestReport_ID, String trxName)
    {
      super (ctx, EXME_CuestReport_ID, trxName);
      /** if (EXME_CuestReport_ID == 0)
        {
			setEXME_Cuestionario_ID (0);
			setEXME_Cuest_Report_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CuestReport (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CuestReport[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cuestionario_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public int getEXME_Cuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Report Questionnaire.
		@param EXME_Cuest_Report_ID Report Questionnaire	  */
	public void setEXME_Cuest_Report_ID (int EXME_Cuest_Report_ID)
	{
		if (EXME_Cuest_Report_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cuest_Report_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Cuest_Report_ID, Integer.valueOf(EXME_Cuest_Report_ID));
	}

	/** Get Report Questionnaire.
		@return Report Questionnaire	  */
	public int getEXME_Cuest_Report_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuest_Report_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Report.
		@param Report Report	  */
	public void setReport (String Report)
	{
		set_Value (COLUMNNAME_Report, Report);
	}

	/** Get Report.
		@return Report	  */
	public String getReport () 
	{
		return (String)get_Value(COLUMNNAME_Report);
	}
}