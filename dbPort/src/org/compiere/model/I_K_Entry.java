/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for K_Entry
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_K_Entry 
{

    /** TableName=K_Entry */
    public static final String Table_Name = "K_Entry";

    /** AD_Table_ID=612 */
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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

    /** Column name DescriptionURL */
    public static final String COLUMNNAME_DescriptionURL = "DescriptionURL";

	/** Set Description URL.
	  * URL for the description
	  */
	public void setDescriptionURL (String DescriptionURL);

	/** Get Description URL.
	  * URL for the description
	  */
	public String getDescriptionURL();

    /** Column name IsPublic */
    public static final String COLUMNNAME_IsPublic = "IsPublic";

	/** Set Public.
	  * Public can read entry
	  */
	public void setIsPublic (boolean IsPublic);

	/** Get Public.
	  * Public can read entry
	  */
	public boolean isPublic();

    /** Column name K_Entry_ID */
    public static final String COLUMNNAME_K_Entry_ID = "K_Entry_ID";

	/** Set Entry.
	  * Knowledge Entry
	  */
	public void setK_Entry_ID (int K_Entry_ID);

	/** Get Entry.
	  * Knowledge Entry
	  */
	public int getK_Entry_ID();

    /** Column name Keywords */
    public static final String COLUMNNAME_Keywords = "Keywords";

	/** Set Keywords.
	  * List of Keywords - separated by space, comma or semicolon
	  */
	public void setKeywords (String Keywords);

	/** Get Keywords.
	  * List of Keywords - separated by space, comma or semicolon
	  */
	public String getKeywords();

    /** Column name K_Source_ID */
    public static final String COLUMNNAME_K_Source_ID = "K_Source_ID";

	/** Set Knowledge Source.
	  * Source of a Knowledge Entry
	  */
	public void setK_Source_ID (int K_Source_ID);

	/** Get Knowledge Source.
	  * Source of a Knowledge Entry
	  */
	public int getK_Source_ID();

	public I_K_Source getK_Source() throws RuntimeException;

    /** Column name K_Topic_ID */
    public static final String COLUMNNAME_K_Topic_ID = "K_Topic_ID";

	/** Set Knowledge Topic.
	  * Knowledge Topic
	  */
	public void setK_Topic_ID (int K_Topic_ID);

	/** Get Knowledge Topic.
	  * Knowledge Topic
	  */
	public int getK_Topic_ID();

	public I_K_Topic getK_Topic() throws RuntimeException;

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

    /** Column name Rating */
    public static final String COLUMNNAME_Rating = "Rating";

	/** Set Rating.
	  * Classification or Importance
	  */
	public void setRating (int Rating);

	/** Get Rating.
	  * Classification or Importance
	  */
	public int getRating();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();
}
