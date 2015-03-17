/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_BlockTime
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_BlockTime 
{

    /** TableName=EXME_BlockTime */
    public static final String Table_Name = "EXME_BlockTime";

    /** AD_Table_ID=1201312 */
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

    /** Column name EXME_BlockTime_ID */
    public static final String COLUMNNAME_EXME_BlockTime_ID = "EXME_BlockTime_ID";

	/** Set BlockTime	  */
	public void setEXME_BlockTime_ID (int EXME_BlockTime_ID);

	/** Get BlockTime	  */
	public int getEXME_BlockTime_ID();

    /** Column name FechaHrFin */
    public static final String COLUMNNAME_FechaHrFin = "FechaHrFin";

	/** Set Finish Hr and Date	  */
	public void setFechaHrFin (Timestamp FechaHrFin);

	/** Get Finish Hr and Date	  */
	public Timestamp getFechaHrFin();

    /** Column name FechaHrIni */
    public static final String COLUMNNAME_FechaHrIni = "FechaHrIni";

	/** Set Initial Hr and Date	  */
	public void setFechaHrIni (Timestamp FechaHrIni);

	/** Get Initial Hr and Date	  */
	public Timestamp getFechaHrIni();

    /** Column name IsDaily */
    public static final String COLUMNNAME_IsDaily = "IsDaily";

	/** Set Daily	  */
	public void setIsDaily (boolean IsDaily);

	/** Get Daily	  */
	public boolean isDaily();

    /** Column name IsRepeat */
    public static final String COLUMNNAME_IsRepeat = "IsRepeat";

	/** Set Repeat.
	  * Repeat
	  */
	public void setIsRepeat (boolean IsRepeat);

	/** Get Repeat.
	  * Repeat
	  */
	public boolean isRepeat();

    /** Column name IsWeekly */
    public static final String COLUMNNAME_IsWeekly = "IsWeekly";

	/** Set Weekly	  */
	public void setIsWeekly (boolean IsWeekly);

	/** Get Weekly	  */
	public boolean isWeekly();

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

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Universally Unique Identifier	  */
	public void setUUID (String UUID);

	/** Get Universally Unique Identifier	  */
	public String getUUID();
}
