/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CM_WebProject
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_CM_WebProject 
{

    /** TableName=CM_WebProject */
    public static final String Table_Name = "CM_WebProject";

    /** AD_Table_ID=853 */
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

    /** Column name AD_TreeCMC_ID */
    public static final String COLUMNNAME_AD_TreeCMC_ID = "AD_TreeCMC_ID";

	/** Set Container Tree.
	  * Container Tree
	  */
	public void setAD_TreeCMC_ID (int AD_TreeCMC_ID);

	/** Get Container Tree.
	  * Container Tree
	  */
	public int getAD_TreeCMC_ID();

    /** Column name AD_TreeCMM_ID */
    public static final String COLUMNNAME_AD_TreeCMM_ID = "AD_TreeCMM_ID";

	/** Set Media Tree.
	  * Media Tree
	  */
	public void setAD_TreeCMM_ID (int AD_TreeCMM_ID);

	/** Get Media Tree.
	  * Media Tree
	  */
	public int getAD_TreeCMM_ID();

    /** Column name AD_TreeCMS_ID */
    public static final String COLUMNNAME_AD_TreeCMS_ID = "AD_TreeCMS_ID";

	/** Set Stage Tree.
	  * Stage Tree
	  */
	public void setAD_TreeCMS_ID (int AD_TreeCMS_ID);

	/** Get Stage Tree.
	  * Stage Tree
	  */
	public int getAD_TreeCMS_ID();

    /** Column name AD_TreeCMT_ID */
    public static final String COLUMNNAME_AD_TreeCMT_ID = "AD_TreeCMT_ID";

	/** Set Template Tree.
	  * Template Tree
	  */
	public void setAD_TreeCMT_ID (int AD_TreeCMT_ID);

	/** Get Template Tree.
	  * Template Tree
	  */
	public int getAD_TreeCMT_ID();

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

    /** Column name Meta_Author */
    public static final String COLUMNNAME_Meta_Author = "Meta_Author";

	/** Set Meta Author.
	  * Author of the content
	  */
	public void setMeta_Author (String Meta_Author);

	/** Get Meta Author.
	  * Author of the content
	  */
	public String getMeta_Author();

    /** Column name Meta_Content */
    public static final String COLUMNNAME_Meta_Content = "Meta_Content";

	/** Set Meta Content Type.
	  * Defines the type of content i.e. "text/html;
 charset=UTF-8"
	  */
	public void setMeta_Content (String Meta_Content);

	/** Get Meta Content Type.
	  * Defines the type of content i.e. "text/html;
 charset=UTF-8"
	  */
	public String getMeta_Content();

    /** Column name Meta_Copyright */
    public static final String COLUMNNAME_Meta_Copyright = "Meta_Copyright";

	/** Set Meta Copyright.
	  * Contains Copyright information for the content
	  */
	public void setMeta_Copyright (String Meta_Copyright);

	/** Get Meta Copyright.
	  * Contains Copyright information for the content
	  */
	public String getMeta_Copyright();

    /** Column name Meta_Publisher */
    public static final String COLUMNNAME_Meta_Publisher = "Meta_Publisher";

	/** Set Meta Publisher.
	  * Meta Publisher defines the publisher of the content
	  */
	public void setMeta_Publisher (String Meta_Publisher);

	/** Get Meta Publisher.
	  * Meta Publisher defines the publisher of the content
	  */
	public String getMeta_Publisher();

    /** Column name Meta_RobotsTag */
    public static final String COLUMNNAME_Meta_RobotsTag = "Meta_RobotsTag";

	/** Set Meta RobotsTag.
	  * RobotsTag defines how search robots should handle this content
	  */
	public void setMeta_RobotsTag (String Meta_RobotsTag);

	/** Get Meta RobotsTag.
	  * RobotsTag defines how search robots should handle this content
	  */
	public String getMeta_RobotsTag();

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
}