/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CM_Template
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_CM_Template 
{

    /** TableName=CM_Template */
    public static final String Table_Name = "CM_Template";

    /** AD_Table_ID=854 */
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

    /** Column name CM_Template_ID */
    public static final String COLUMNNAME_CM_Template_ID = "CM_Template_ID";

	/** Set Template.
	  * Template defines how content is displayed
	  */
	public void setCM_Template_ID (int CM_Template_ID);

	/** Get Template.
	  * Template defines how content is displayed
	  */
	public int getCM_Template_ID();

    /** Column name CM_WebProject_ID */
    public static final String COLUMNNAME_CM_WebProject_ID = "CM_WebProject_ID";

	/** Set Web Project.
	  * A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public void setCM_WebProject_ID (int CM_WebProject_ID);

	/** Get Web Project.
	  * A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public int getCM_WebProject_ID();

	public I_CM_WebProject getCM_WebProject() throws RuntimeException;

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

    /** Column name Elements */
    public static final String COLUMNNAME_Elements = "Elements";

	/** Set Elements.
	  * Contains list of elements seperated by CR
	  */
	public void setElements (String Elements);

	/** Get Elements.
	  * Contains list of elements seperated by CR
	  */
	public String getElements();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsInclude */
    public static final String COLUMNNAME_IsInclude = "IsInclude";

	/** Set Included.
	  * Defines whether this content / template is included into another one
	  */
	public void setIsInclude (boolean IsInclude);

	/** Get Included.
	  * Defines whether this content / template is included into another one
	  */
	public boolean isInclude();

    /** Column name IsNews */
    public static final String COLUMNNAME_IsNews = "IsNews";

	/** Set Uses News.
	  * Template or container uses news channels
	  */
	public void setIsNews (boolean IsNews);

	/** Get Uses News.
	  * Template or container uses news channels
	  */
	public boolean isNews();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

    /** Column name IsUseAd */
    public static final String COLUMNNAME_IsUseAd = "IsUseAd";

	/** Set Use Ad.
	  * Whether or not this templates uses Ad's
	  */
	public void setIsUseAd (boolean IsUseAd);

	/** Get Use Ad.
	  * Whether or not this templates uses Ad's
	  */
	public boolean isUseAd();

    /** Column name IsValid */
    public static final String COLUMNNAME_IsValid = "IsValid";

	/** Set Valid.
	  * Element is valid
	  */
	public void setIsValid (boolean IsValid);

	/** Get Valid.
	  * Element is valid
	  */
	public boolean isValid();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name TemplateXST */
    public static final String COLUMNNAME_TemplateXST = "TemplateXST";

	/** Set TemplateXST.
	  * Contains the template code itself
	  */
	public void setTemplateXST (String TemplateXST);

	/** Get TemplateXST.
	  * Contains the template code itself
	  */
	public String getTemplateXST();

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