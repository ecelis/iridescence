/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for U_WebMenu
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_U_WebMenu 
{

    /** TableName=U_WebMenu */
    public static final String Table_Name = "U_WebMenu";

    /** AD_Table_ID=1200811 */
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

    /** Column name Category */
    public static final String COLUMNNAME_Category = "Category";

	/** Set Category	  */
	public void setCategory (String Category);

	/** Get Category	  */
	public String getCategory();

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

    /** Column name HasSubMenu */
    public static final String COLUMNNAME_HasSubMenu = "HasSubMenu";

	/** Set Has SubMenu	  */
	public void setHasSubMenu (boolean HasSubMenu);

	/** Get Has SubMenu	  */
	public boolean isHasSubMenu();

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

    /** Column name ImageLink */
    public static final String COLUMNNAME_ImageLink = "ImageLink";

	/** Set Image Link	  */
	public void setImageLink (String ImageLink);

	/** Get Image Link	  */
	public String getImageLink();

    /** Column name MenuLink */
    public static final String COLUMNNAME_MenuLink = "MenuLink";

	/** Set Menu Link	  */
	public void setMenuLink (String MenuLink);

	/** Get Menu Link	  */
	public String getMenuLink();

    /** Column name Module */
    public static final String COLUMNNAME_Module = "Module";

	/** Set Module	  */
	public void setModule (String Module);

	/** Get Module	  */
	public String getModule();

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

    /** Column name ParentMenu_ID */
    public static final String COLUMNNAME_ParentMenu_ID = "ParentMenu_ID";

	/** Set Parent Menu	  */
	public void setParentMenu_ID (int ParentMenu_ID);

	/** Get Parent Menu	  */
	public int getParentMenu_ID();

    /** Column name Position */
    public static final String COLUMNNAME_Position = "Position";

	/** Set Position	  */
	public void setPosition (String Position);

	/** Get Position	  */
	public String getPosition();

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (BigDecimal Sequence);

	/** Get Sequence	  */
	public BigDecimal getSequence();

    /** Column name U_WebMenu_ID */
    public static final String COLUMNNAME_U_WebMenu_ID = "U_WebMenu_ID";

	/** Set Web Menu	  */
	public void setU_WebMenu_ID (int U_WebMenu_ID);

	/** Get Web Menu	  */
	public int getU_WebMenu_ID();
}
