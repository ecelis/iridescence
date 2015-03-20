/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ClaimCodes
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ClaimCodes 
{

    /** TableName=EXME_ClaimCodes */
    public static final String Table_Name = "EXME_ClaimCodes";

    /** AD_Table_ID=1201157 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name AD_TableOrig_ID */
    public static final String COLUMNNAME_AD_TableOrig_ID = "AD_TableOrig_ID";

	/** Set Table Origin	  */
	public void setAD_TableOrig_ID (int AD_TableOrig_ID);

	/** Get Table Origin	  */
	public int getAD_TableOrig_ID();

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (int Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public int getAmount();

    /** Column name ConfType */
    public static final String COLUMNNAME_ConfType = "ConfType";

	/** Set Configuration Type	  */
	public void setConfType (String ConfType);

	/** Get Configuration Type	  */
	public String getConfType();

    /** Column name DateFrom */
    public static final String COLUMNNAME_DateFrom = "DateFrom";

	/** Set Date From.
	  * Starting date for a range
	  */
	public void setDateFrom (Timestamp DateFrom);

	/** Get Date From.
	  * Starting date for a range
	  */
	public Timestamp getDateFrom();

    /** Column name DateThrough */
    public static final String COLUMNNAME_DateThrough = "DateThrough";

	/** Set Date Through	  */
	public void setDateThrough (Timestamp DateThrough);

	/** Get Date Through	  */
	public Timestamp getDateThrough();

    /** Column name EXME_ClaimCodes_ID */
    public static final String COLUMNNAME_EXME_ClaimCodes_ID = "EXME_ClaimCodes_ID";

	/** Set EXME_ClaimCodes_ID	  */
	public void setEXME_ClaimCodes_ID (int EXME_ClaimCodes_ID);

	/** Get EXME_ClaimCodes_ID	  */
	public int getEXME_ClaimCodes_ID();

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name RecordOrig_ID */
    public static final String COLUMNNAME_RecordOrig_ID = "RecordOrig_ID";

	/** Set Record Origin	  */
	public void setRecordOrig_ID (int RecordOrig_ID);

	/** Get Record Origin	  */
	public int getRecordOrig_ID();

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (int Sequence);

	/** Get Sequence	  */
	public int getSequence();
}
