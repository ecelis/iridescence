/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Ruta_Interfases
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Ruta_Interfases extends PO implements I_EXME_Ruta_Interfases, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Ruta_Interfases (Properties ctx, int EXME_Ruta_Interfases_ID, String trxName)
    {
      super (ctx, EXME_Ruta_Interfases_ID, trxName);
      /** if (EXME_Ruta_Interfases_ID == 0)
        {
			setEXME_Ruta_Interfases_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Ruta_Interfases (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Ruta_Interfases[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Input Documents of the Batches.
		@param BatchDocinput 
		Input Documents of the Batches
	  */
	public void setBatchDocinput (String BatchDocinput)
	{
		set_Value (COLUMNNAME_BatchDocinput, BatchDocinput);
	}

	/** Get Input Documents of the Batches.
		@return Input Documents of the Batches
	  */
	public String getBatchDocinput () 
	{
		return (String)get_Value(COLUMNNAME_BatchDocinput);
	}

	/** Set Output Documents of the Batches.
		@param BatchDocOutput Output Documents of the Batches	  */
	public void setBatchDocOutput (String BatchDocOutput)
	{
		set_Value (COLUMNNAME_BatchDocOutput, BatchDocOutput);
	}

	/** Get Output Documents of the Batches.
		@return Output Documents of the Batches	  */
	public String getBatchDocOutput () 
	{
		return (String)get_Value(COLUMNNAME_BatchDocOutput);
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

	/** Set Log de Carga a EBS.
		@param EBSChargeLog Log de Carga a EBS	  */
	public void setEBSChargeLog (String EBSChargeLog)
	{
		set_Value (COLUMNNAME_EBSChargeLog, EBSChargeLog);
	}

	/** Get Log de Carga a EBS.
		@return Log de Carga a EBS	  */
	public String getEBSChargeLog () 
	{
		return (String)get_Value(COLUMNNAME_EBSChargeLog);
	}

	/** Set EBS User Name.
		@param EBSUserName EBS User Name	  */
	public void setEBSUserName (String EBSUserName)
	{
		set_Value (COLUMNNAME_EBSUserName, EBSUserName);
	}

	/** Get EBS User Name.
		@return EBS User Name	  */
	public String getEBSUserName () 
	{
		return (String)get_Value(COLUMNNAME_EBSUserName);
	}

	/** Set EBS User Password.
		@param EBSUserPassword EBS User Password	  */
	public void setEBSUserPassword (String EBSUserPassword)
	{
		set_Value (COLUMNNAME_EBSUserPassword, EBSUserPassword);
	}

	/** Get EBS User Password.
		@return EBS User Password	  */
	public String getEBSUserPassword () 
	{
		return (String)get_Value(COLUMNNAME_EBSUserPassword);
	}

	/** Set Interfaces Path.
		@param EXME_Ruta_Interfases_ID Interfaces Path	  */
	public void setEXME_Ruta_Interfases_ID (int EXME_Ruta_Interfases_ID)
	{
		if (EXME_Ruta_Interfases_ID < 1)
			 throw new IllegalArgumentException ("EXME_Ruta_Interfases_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Ruta_Interfases_ID, Integer.valueOf(EXME_Ruta_Interfases_ID));
	}

	/** Get Interfaces Path.
		@return Interfaces Path	  */
	public int getEXME_Ruta_Interfases_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ruta_Interfases_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Init Charge Files.
		@param InitChargeFiles Init Charge Files	  */
	public void setInitChargeFiles (String InitChargeFiles)
	{
		set_Value (COLUMNNAME_InitChargeFiles, InitChargeFiles);
	}

	/** Get Init Charge Files.
		@return Init Charge Files	  */
	public String getInitChargeFiles () 
	{
		return (String)get_Value(COLUMNNAME_InitChargeFiles);
	}

	/** Set Init Charge Log.
		@param InitChargeLog Init Charge Log	  */
	public void setInitChargeLog (String InitChargeLog)
	{
		set_Value (COLUMNNAME_InitChargeLog, InitChargeLog);
	}

	/** Get Init Charge Log.
		@return Init Charge Log	  */
	public String getInitChargeLog () 
	{
		return (String)get_Value(COLUMNNAME_InitChargeLog);
	}

	/** Set Manual Document Output.
		@param ManualDocOutput Manual Document Output	  */
	public void setManualDocOutput (String ManualDocOutput)
	{
		set_Value (COLUMNNAME_ManualDocOutput, ManualDocOutput);
	}

	/** Get Manual Document Output.
		@return Manual Document Output	  */
	public String getManualDocOutput () 
	{
		return (String)get_Value(COLUMNNAME_ManualDocOutput);
	}

	/** Set Medsys Charge Log.
		@param MedsysChargeLog Medsys Charge Log	  */
	public void setMedsysChargeLog (String MedsysChargeLog)
	{
		set_Value (COLUMNNAME_MedsysChargeLog, MedsysChargeLog);
	}

	/** Get Medsys Charge Log.
		@return Medsys Charge Log	  */
	public String getMedsysChargeLog () 
	{
		return (String)get_Value(COLUMNNAME_MedsysChargeLog);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Unix Transit File.
		@param UnixTranFile Unix Transit File	  */
	public void setUnixTranFile (String UnixTranFile)
	{
		set_Value (COLUMNNAME_UnixTranFile, UnixTranFile);
	}

	/** Get Unix Transit File.
		@return Unix Transit File	  */
	public String getUnixTranFile () 
	{
		return (String)get_Value(COLUMNNAME_UnixTranFile);
	}

	/** Set Windows Transitory File.
		@param WindowsTranFile Windows Transitory File	  */
	public void setWindowsTranFile (String WindowsTranFile)
	{
		set_Value (COLUMNNAME_WindowsTranFile, WindowsTranFile);
	}

	/** Get Windows Transitory File.
		@return Windows Transitory File	  */
	public String getWindowsTranFile () 
	{
		return (String)get_Value(COLUMNNAME_WindowsTranFile);
	}
}