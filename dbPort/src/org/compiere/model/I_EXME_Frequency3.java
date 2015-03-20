/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Frequency3
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Frequency3 
{

    /** TableName=EXME_Frequency3 */
    public static final String Table_Name = "EXME_Frequency3";

    /** AD_Table_ID=1201132 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name EXME_Frequency1_ID */
    public static final String COLUMNNAME_EXME_Frequency1_ID = "EXME_Frequency1_ID";

	/** Set Frequency 1.
	  * Frequency Header ID
	  */
	public void setEXME_Frequency1_ID (int EXME_Frequency1_ID);

	/** Get Frequency 1.
	  * Frequency Header ID
	  */
	public int getEXME_Frequency1_ID();

	public I_EXME_Frequency1 getEXME_Frequency1() throws RuntimeException;

    /** Column name EXME_Frequency2_ID */
    public static final String COLUMNNAME_EXME_Frequency2_ID = "EXME_Frequency2_ID";

	/** Set Frequency 2.
	  * Frequency First Detail ID
	  */
	public void setEXME_Frequency2_ID (int EXME_Frequency2_ID);

	/** Get Frequency 2.
	  * Frequency First Detail ID
	  */
	public int getEXME_Frequency2_ID();

	public I_EXME_Frequency2 getEXME_Frequency2() throws RuntimeException;

    /** Column name EXME_Frequency3_ID */
    public static final String COLUMNNAME_EXME_Frequency3_ID = "EXME_Frequency3_ID";

	/** Set Frequency 3.
	  * Frequency Second Detail ID
	  */
	public void setEXME_Frequency3_ID (int EXME_Frequency3_ID);

	/** Get Frequency 3.
	  * Frequency Second Detail ID
	  */
	public int getEXME_Frequency3_ID();

    /** Column name Hour */
    public static final String COLUMNNAME_Hour = "Hour";

	/** Set Hour	  */
	public void setHour (Timestamp Hour);

	/** Get Hour	  */
	public Timestamp getHour();

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
