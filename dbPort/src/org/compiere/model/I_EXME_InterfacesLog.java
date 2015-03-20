/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_InterfacesLog
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_InterfacesLog 
{

    /** TableName=EXME_InterfacesLog */
    public static final String Table_Name = "EXME_InterfacesLog";

    /** AD_Table_ID=1200345 */
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

    /** Column name EXME_INTERFACESLOG_ID */
    public static final String COLUMNNAME_EXME_INTERFACESLOG_ID = "EXME_INTERFACESLOG_ID";

	/** Set Interface Log	  */
	public void setEXME_INTERFACESLOG_ID (int EXME_INTERFACESLOG_ID);

	/** Get Interface Log	  */
	public int getEXME_INTERFACESLOG_ID();

    /** Column name FileName */
    public static final String COLUMNNAME_FileName = "FileName";

	/** Set File Name.
	  * Name of the local file or URL
	  */
	public void setFileName (String FileName);

	/** Get File Name.
	  * Name of the local file or URL
	  */
	public String getFileName();

    /** Column name Interfase */
    public static final String COLUMNNAME_Interfase = "Interfase";

	/** Set Interface	  */
	public void setInterfase (String Interfase);

	/** Get Interface	  */
	public String getInterfase();

    /** Column name Message */
    public static final String COLUMNNAME_Message = "Message";

	/** Set Message.
	  * EMail Message
	  */
	public void setMessage (String Message);

	/** Get Message.
	  * EMail Message
	  */
	public String getMessage();

    /** Column name Operation */
    public static final String COLUMNNAME_Operation = "Operation";

	/** Set Operation.
	  * Compare Operation
	  */
	public void setOperation (String Operation);

	/** Get Operation.
	  * Compare Operation
	  */
	public String getOperation();

    /** Column name ProcessingDate */
    public static final String COLUMNNAME_ProcessingDate = "ProcessingDate";

	/** Set Processing date	  */
	public void setProcessingDate (Timestamp ProcessingDate);

	/** Get Processing date	  */
	public Timestamp getProcessingDate();

    /** Column name ReferenceNo */
    public static final String COLUMNNAME_ReferenceNo = "ReferenceNo";

	/** Set Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo);

	/** Get Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo();
}
