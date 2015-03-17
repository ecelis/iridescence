/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_EstServResp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EstServResp extends PO implements I_EXME_EstServResp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EstServResp (Properties ctx, int EXME_EstServResp_ID, String trxName)
    {
      super (ctx, EXME_EstServResp_ID, trxName);
      /** if (EXME_EstServResp_ID == 0)
        {
			setEXME_Emp_ID (0);
			setEXME_EstServ_ID (0);
			setEXME_EstServResp_ID (0);
			setFechaIni (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_EstServResp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EstServResp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Employee.
		@param EXME_Emp_ID 
		Employee
	  */
	public void setEXME_Emp_ID (int EXME_Emp_ID)
	{
		if (EXME_Emp_ID < 1)
			 throw new IllegalArgumentException ("EXME_Emp_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Emp_ID, Integer.valueOf(EXME_Emp_ID));
	}

	/** Get Employee.
		@return Employee
	  */
	public int getEXME_Emp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Emp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServ_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Responsible Station.
		@param EXME_EstServResp_ID 
		Contains responsible for service station
	  */
	public void setEXME_EstServResp_ID (int EXME_EstServResp_ID)
	{
		if (EXME_EstServResp_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServResp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EstServResp_ID, Integer.valueOf(EXME_EstServResp_ID));
	}

	/** Get Responsible Station.
		@return Contains responsible for service station
	  */
	public int getEXME_EstServResp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServResp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ending Date.
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
	}

	/** Set Initial Date.
		@param FechaIni 
		Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni)
	{
		if (FechaIni == null)
			throw new IllegalArgumentException ("FechaIni is mandatory.");
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
	}

	/** IsPrinted AD_Reference_ID=1200187 */
	public static final int ISPRINTED_AD_Reference_ID=1200187;
	/** Print = N */
	public static final String ISPRINTED_Print = "N";
	/** Printed = Y */
	public static final String ISPRINTED_Printed = "Y";
	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted)
	{

		if (IsPrinted == null || IsPrinted.equals("N") || IsPrinted.equals("Y")); else throw new IllegalArgumentException ("IsPrinted Invalid value - " + IsPrinted + " - Reference_ID=1200187 - N - Y");		set_Value (COLUMNNAME_IsPrinted, IsPrinted);
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public String getIsPrinted () 
	{
		return (String)get_Value(COLUMNNAME_IsPrinted);
	}

	/** IsTransfered AD_Reference_ID=1200174 */
	public static final int ISTRANSFERED_AD_Reference_ID=1200174;
	/** Set = N */
	public static final String ISTRANSFERED_Set = "N";
	/** Responsible = Y */
	public static final String ISTRANSFERED_Responsible = "Y";
	/** Set Is Transfered.
		@param IsTransfered 
		Sets whether this transfer registration 
	  */
	public void setIsTransfered (String IsTransfered)
	{

		if (IsTransfered == null || IsTransfered.equals("N") || IsTransfered.equals("Y")); else throw new IllegalArgumentException ("IsTransfered Invalid value - " + IsTransfered + " - Reference_ID=1200174 - N - Y");		set_Value (COLUMNNAME_IsTransfered, IsTransfered);
	}

	/** Get Is Transfered.
		@return Sets whether this transfer registration 
	  */
	public String getIsTransfered () 
	{
		return (String)get_Value(COLUMNNAME_IsTransfered);
	}
}