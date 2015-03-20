/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_RecursoEducativo
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_RecursoEducativo extends PO implements I_EXME_RecursoEducativo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RecursoEducativo (Properties ctx, int EXME_RecursoEducativo_ID, String trxName)
    {
      super (ctx, EXME_RecursoEducativo_ID, trxName);
      /** if (EXME_RecursoEducativo_ID == 0)
        {
			setEXME_RecursoEducativo_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_RecursoEducativo (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_RecursoEducativo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Education Resource.
		@param EXME_RecursoEducativo_ID Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID)
	{
		if (EXME_RecursoEducativo_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RecursoEducativo_ID, Integer.valueOf(EXME_RecursoEducativo_ID));
	}

	/** Get Education Resource.
		@return Education Resource	  */
	public int getEXME_RecursoEducativo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Patient Resource.
		@param FechaRecursoPac Date Patient Resource	  */
	public void setFechaRecursoPac (Timestamp FechaRecursoPac)
	{
		set_Value (COLUMNNAME_FechaRecursoPac, FechaRecursoPac);
	}

	/** Get Date Patient Resource.
		@return Date Patient Resource	  */
	public Timestamp getFechaRecursoPac () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaRecursoPac);
	}

	/** TipoRecurso AD_Reference_ID=1200446 */
	public static final int TIPORECURSO_AD_Reference_ID=1200446;
	/** URL = URL */
	public static final String TIPORECURSO_URL = "URL";
	/** File = FIL */
	public static final String TIPORECURSO_File = "FIL";
	/** Set Resource Type.
		@param TipoRecurso Resource Type	  */
	public void setTipoRecurso (String TipoRecurso)
	{

		if (TipoRecurso == null || TipoRecurso.equals("URL") || TipoRecurso.equals("FIL")); else throw new IllegalArgumentException ("TipoRecurso Invalid value - " + TipoRecurso + " - Reference_ID=1200446 - URL - FIL");		set_Value (COLUMNNAME_TipoRecurso, TipoRecurso);
	}

	/** Get Resource Type.
		@return Resource Type	  */
	public String getTipoRecurso () 
	{
		return (String)get_Value(COLUMNNAME_TipoRecurso);
	}

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL)
	{
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
	}
}