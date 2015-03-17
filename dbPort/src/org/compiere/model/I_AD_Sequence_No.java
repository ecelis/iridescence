/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Sequence_No
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Sequence_No 
{

    /** TableName=AD_Sequence_No */
    public static final String Table_Name = "AD_Sequence_No";

    /** AD_Table_ID=122 */
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

    /** Column name AD_Sequence_ID */
    public static final String COLUMNNAME_AD_Sequence_ID = "AD_Sequence_ID";

	/** Set Sequence.
	  * Document Sequence
	  */
	public void setAD_Sequence_ID (int AD_Sequence_ID);

	/** Get Sequence.
	  * Document Sequence
	  */
	public int getAD_Sequence_ID();

	public I_AD_Sequence getAD_Sequence() throws RuntimeException;

    /** Column name CalendarYear */
    public static final String COLUMNNAME_CalendarYear = "CalendarYear";

	/** Set Year.
	  * Calendar Year
	  */
	public void setCalendarYear (String CalendarYear);

	/** Get Year.
	  * Calendar Year
	  */
	public String getCalendarYear();

    /** Column name CurrentNext */
    public static final String COLUMNNAME_CurrentNext = "CurrentNext";

	/** Set Current Next.
	  * The next number to be used
	  */
	public void setCurrentNext (int CurrentNext);

	/** Get Current Next.
	  * The next number to be used
	  */
	public int getCurrentNext();

    /** Column name Year */
    public static final String COLUMNNAME_Year = "Year";

	/** Set Year.
	  * Calendar Year
	  */
	public void setYear (String Year);

	/** Get Year.
	  * Calendar Year
	  */
	public String getYear();
}
