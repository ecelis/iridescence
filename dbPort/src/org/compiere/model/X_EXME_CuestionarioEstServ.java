/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_CuestionarioEstServ
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CuestionarioEstServ extends PO implements I_EXME_CuestionarioEstServ, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CuestionarioEstServ (Properties ctx, int EXME_CuestionarioEstServ_ID, String trxName)
    {
      super (ctx, EXME_CuestionarioEstServ_ID, trxName);
      /** if (EXME_CuestionarioEstServ_ID == 0)
        {
			setEXME_CuestionarioEstServ_ID (0);
			setEXME_Cuestionario_ID (0);
			setEXME_EstServ_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CuestionarioEstServ (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_CuestionarioEstServ[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** DocStatus AD_Reference_ID=1200577 */
	public static final int DOCSTATUS_AD_Reference_ID=1200577;
	/** None = 0 */
	public static final String DOCSTATUS_None = "0";
	/** Pending = 1 */
	public static final String DOCSTATUS_Pending = "1";
	/** Active = 3 */
	public static final String DOCSTATUS_Active = "3";
	/** Closed = 4 */
	public static final String DOCSTATUS_Closed = "4";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		if (DocStatus == null || DocStatus.equals("0") || DocStatus.equals("1") || DocStatus.equals("3") || DocStatus.equals("4")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=1200577 - 0 - 1 - 3 - 4");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Questionnaire Service Unit.
		@param EXME_CuestionarioEstServ_ID Questionnaire Service Unit	  */
	public void setEXME_CuestionarioEstServ_ID (int EXME_CuestionarioEstServ_ID)
	{
		if (EXME_CuestionarioEstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_CuestionarioEstServ_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CuestionarioEstServ_ID, Integer.valueOf(EXME_CuestionarioEstServ_ID));
	}

	/** Get Questionnaire Service Unit.
		@return Questionnaire Service Unit	  */
	public int getEXME_CuestionarioEstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CuestionarioEstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Service Unit.
		@param EXME_EstServ_ID 
		Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServ_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Unit.
		@return Service Unit
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}