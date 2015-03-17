/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for K_Synonym
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_K_Synonym 
{

    /** TableName=K_Synonym */
    public static final String Table_Name = "K_Synonym";

    /** AD_Table_ID=608 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Language */
    public static final String COLUMNNAME_AD_Language = "AD_Language";

	/** Set Language.
	  * Language for this entity
	  */
	public void setAD_Language (String AD_Language);

	/** Get Language.
	  * Language for this entity
	  */
	public String getAD_Language();

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

    /** Column name K_Synonym_ID */
    public static final String COLUMNNAME_K_Synonym_ID = "K_Synonym_ID";

	/** Set Knowledge Synonym.
	  * Knowlege Keyword Synonym
	  */
	public void setK_Synonym_ID (int K_Synonym_ID);

	/** Get Knowledge Synonym.
	  * Knowlege Keyword Synonym
	  */
	public int getK_Synonym_ID();

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

    /** Column name SynonymName */
    public static final String COLUMNNAME_SynonymName = "SynonymName";

	/** Set Synonym Name.
	  * The synonym for the name
	  */
	public void setSynonymName (String SynonymName);

	/** Get Synonym Name.
	  * The synonym for the name
	  */
	public String getSynonymName();
}
