/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DirectoryDownloadLog
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha)
 */
public interface I_EXME_DirectoryDownloadLog 
{

    /** TableName=EXME_DirectoryDownloadLog */
    public static final String Table_Name = "EXME_DirectoryDownloadLog";

    /** AD_Table_ID=1201223 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name EXME_DirectoryDownloadLog_ID */
    public static final String COLUMNNAME_EXME_DirectoryDownloadLog_ID = "EXME_DirectoryDownloadLog_ID";

	/** Set Directory Download Log.
	  * Directory Download Log
	  */
	public void setEXME_DirectoryDownloadLog_ID (int EXME_DirectoryDownloadLog_ID);

	/** Get Directory Download Log.
	  * Directory Download Log
	  */
	public int getEXME_DirectoryDownloadLog_ID();

    /** Column name Fecha_Carga */
    public static final String COLUMNNAME_Fecha_Carga = "Fecha_Carga";

	/** Set Load Date	  */
	public void setFecha_Carga (Timestamp Fecha_Carga);

	/** Get Load Date	  */
	public Timestamp getFecha_Carga();

    /** Column name FileType */
    public static final String COLUMNNAME_FileType = "FileType";

	/** Set File Type	  */
	public void setFileType (String FileType);

	/** Get File Type	  */
	public String getFileType();

    /** Column name R_RespMsg */
    public static final String COLUMNNAME_R_RespMsg = "R_RespMsg";

	/** Set Response Message.
	  * Response message
	  */
	public void setR_RespMsg (String R_RespMsg);

	/** Get Response Message.
	  * Response message
	  */
	public String getR_RespMsg();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
