/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Table_ScriptValidator
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Table_ScriptValidator 
{

    /** TableName=AD_Table_ScriptValidator */
    public static final String Table_Name = "AD_Table_ScriptValidator";

    /** AD_Table_ID=1200696 */
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

    /** Column name AD_Rule_ID */
    public static final String COLUMNNAME_AD_Rule_ID = "AD_Rule_ID";

	/** Set Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID);

	/** Get Rule	  */
	public int getAD_Rule_ID();

	public I_AD_Rule getAD_Rule() throws RuntimeException;

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

    /** Column name AD_Table_ScriptValidator_ID */
    public static final String COLUMNNAME_AD_Table_ScriptValidator_ID = "AD_Table_ScriptValidator_ID";

	/** Set Table Script Validator	  */
	public void setAD_Table_ScriptValidator_ID (int AD_Table_ScriptValidator_ID);

	/** Get Table Script Validator	  */
	public int getAD_Table_ScriptValidator_ID();

    /** Column name EventModelValidator */
    public static final String COLUMNNAME_EventModelValidator = "EventModelValidator";

	/** Set Event Model Validator	  */
	public void setEventModelValidator (String EventModelValidator);

	/** Get Event Model Validator	  */
	public String getEventModelValidator();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
