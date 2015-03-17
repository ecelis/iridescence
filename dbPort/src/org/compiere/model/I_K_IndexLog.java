/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for K_IndexLog
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_K_IndexLog 
{

    /** TableName=K_IndexLog */
    public static final String Table_Name = "K_IndexLog";

    /** AD_Table_ID=899 */
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

    /** Column name IndexQuery */
    public static final String COLUMNNAME_IndexQuery = "IndexQuery";

	/** Set Index Query.
	  * Text Search Query 
	  */
	public void setIndexQuery (String IndexQuery);

	/** Get Index Query.
	  * Text Search Query 
	  */
	public String getIndexQuery();

    /** Column name IndexQueryResult */
    public static final String COLUMNNAME_IndexQueryResult = "IndexQueryResult";

	/** Set Query Result.
	  * Result of the text query
	  */
	public void setIndexQueryResult (int IndexQueryResult);

	/** Get Query Result.
	  * Result of the text query
	  */
	public int getIndexQueryResult();

    /** Column name K_IndexLog_ID */
    public static final String COLUMNNAME_K_IndexLog_ID = "K_IndexLog_ID";

	/** Set Index Log.
	  * Text search log
	  */
	public void setK_IndexLog_ID (int K_IndexLog_ID);

	/** Get Index Log.
	  * Text search log
	  */
	public int getK_IndexLog_ID();

    /** Column name QuerySource */
    public static final String COLUMNNAME_QuerySource = "QuerySource";

	/** Set Query Source.
	  * Source of the Query
	  */
	public void setQuerySource (String QuerySource);

	/** Get Query Source.
	  * Source of the Query
	  */
	public String getQuerySource();
}
