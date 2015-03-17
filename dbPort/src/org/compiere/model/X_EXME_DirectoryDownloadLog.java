/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_DirectoryDownloadLog
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha) - $Id$ */
public class X_EXME_DirectoryDownloadLog extends PO implements I_EXME_DirectoryDownloadLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DirectoryDownloadLog (Properties ctx, int EXME_DirectoryDownloadLog_ID, String trxName)
    {
      super (ctx, EXME_DirectoryDownloadLog_ID, trxName);
      /** if (EXME_DirectoryDownloadLog_ID == 0)
        {
			setEXME_DirectoryDownloadLog_ID (0);
			setFecha_Carga (new Timestamp( System.currentTimeMillis() ));
			setFileType (null);
			setR_RespMsg (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_DirectoryDownloadLog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_DirectoryDownloadLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Directory Download Log.
		@param EXME_DirectoryDownloadLog_ID 
		Directory Download Log
	  */
	public void setEXME_DirectoryDownloadLog_ID (int EXME_DirectoryDownloadLog_ID)
	{
		if (EXME_DirectoryDownloadLog_ID < 1)
			 throw new IllegalArgumentException ("EXME_DirectoryDownloadLog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DirectoryDownloadLog_ID, Integer.valueOf(EXME_DirectoryDownloadLog_ID));
	}

	/** Get Directory Download Log.
		@return Directory Download Log
	  */
	public int getEXME_DirectoryDownloadLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DirectoryDownloadLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Load Date.
		@param Fecha_Carga Load Date	  */
	public void setFecha_Carga (Timestamp Fecha_Carga)
	{
		if (Fecha_Carga == null)
			throw new IllegalArgumentException ("Fecha_Carga is mandatory.");
		set_Value (COLUMNNAME_Fecha_Carga, Fecha_Carga);
	}

	/** Get Load Date.
		@return Load Date	  */
	public Timestamp getFecha_Carga () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Carga);
	}

	/** Set File Type.
		@param FileType File Type	  */
	public void setFileType (String FileType)
	{
		if (FileType == null)
			throw new IllegalArgumentException ("FileType is mandatory.");
		set_Value (COLUMNNAME_FileType, FileType);
	}

	/** Get File Type.
		@return File Type	  */
	public String getFileType () 
	{
		return (String)get_Value(COLUMNNAME_FileType);
	}

	/** Set Response Message.
		@param R_RespMsg 
		Response message
	  */
	public void setR_RespMsg (String R_RespMsg)
	{
		if (R_RespMsg == null)
			throw new IllegalArgumentException ("R_RespMsg is mandatory.");
		set_Value (COLUMNNAME_R_RespMsg, R_RespMsg);
	}

	/** Get Response Message.
		@return Response message
	  */
	public String getR_RespMsg () 
	{
		return (String)get_Value(COLUMNNAME_R_RespMsg);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}