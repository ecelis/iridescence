/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Ruta_Interfases
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Ruta_Interfases 
{

    /** TableName=EXME_Ruta_Interfases */
    public static final String Table_Name = "EXME_Ruta_Interfases";

    /** AD_Table_ID=1200186 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name BatchDocinput */
    public static final String COLUMNNAME_BatchDocinput = "BatchDocinput";

	/** Set Input Documents of the Batches.
	  * Input Documents of the Batches
	  */
	public void setBatchDocinput (String BatchDocinput);

	/** Get Input Documents of the Batches.
	  * Input Documents of the Batches
	  */
	public String getBatchDocinput();

    /** Column name BatchDocOutput */
    public static final String COLUMNNAME_BatchDocOutput = "BatchDocOutput";

	/** Set Output Documents of the Batches	  */
	public void setBatchDocOutput (String BatchDocOutput);

	/** Get Output Documents of the Batches	  */
	public String getBatchDocOutput();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EBSChargeLog */
    public static final String COLUMNNAME_EBSChargeLog = "EBSChargeLog";

	/** Set Log de Carga a EBS	  */
	public void setEBSChargeLog (String EBSChargeLog);

	/** Get Log de Carga a EBS	  */
	public String getEBSChargeLog();

    /** Column name EBSUserName */
    public static final String COLUMNNAME_EBSUserName = "EBSUserName";

	/** Set EBS User Name	  */
	public void setEBSUserName (String EBSUserName);

	/** Get EBS User Name	  */
	public String getEBSUserName();

    /** Column name EBSUserPassword */
    public static final String COLUMNNAME_EBSUserPassword = "EBSUserPassword";

	/** Set EBS User Password	  */
	public void setEBSUserPassword (String EBSUserPassword);

	/** Get EBS User Password	  */
	public String getEBSUserPassword();

    /** Column name EXME_Ruta_Interfases_ID */
    public static final String COLUMNNAME_EXME_Ruta_Interfases_ID = "EXME_Ruta_Interfases_ID";

	/** Set Interfaces Path	  */
	public void setEXME_Ruta_Interfases_ID (int EXME_Ruta_Interfases_ID);

	/** Get Interfaces Path	  */
	public int getEXME_Ruta_Interfases_ID();

    /** Column name InitChargeFiles */
    public static final String COLUMNNAME_InitChargeFiles = "InitChargeFiles";

	/** Set Init Charge Files	  */
	public void setInitChargeFiles (String InitChargeFiles);

	/** Get Init Charge Files	  */
	public String getInitChargeFiles();

    /** Column name InitChargeLog */
    public static final String COLUMNNAME_InitChargeLog = "InitChargeLog";

	/** Set Init Charge Log	  */
	public void setInitChargeLog (String InitChargeLog);

	/** Get Init Charge Log	  */
	public String getInitChargeLog();

    /** Column name ManualDocOutput */
    public static final String COLUMNNAME_ManualDocOutput = "ManualDocOutput";

	/** Set Manual Document Output	  */
	public void setManualDocOutput (String ManualDocOutput);

	/** Get Manual Document Output	  */
	public String getManualDocOutput();

    /** Column name MedsysChargeLog */
    public static final String COLUMNNAME_MedsysChargeLog = "MedsysChargeLog";

	/** Set Medsys Charge Log	  */
	public void setMedsysChargeLog (String MedsysChargeLog);

	/** Get Medsys Charge Log	  */
	public String getMedsysChargeLog();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name UnixTranFile */
    public static final String COLUMNNAME_UnixTranFile = "UnixTranFile";

	/** Set Unix Transit File	  */
	public void setUnixTranFile (String UnixTranFile);

	/** Get Unix Transit File	  */
	public String getUnixTranFile();

    /** Column name WindowsTranFile */
    public static final String COLUMNNAME_WindowsTranFile = "WindowsTranFile";

	/** Set Windows Transitory File	  */
	public void setWindowsTranFile (String WindowsTranFile);

	/** Get Windows Transitory File	  */
	public String getWindowsTranFile();
}
