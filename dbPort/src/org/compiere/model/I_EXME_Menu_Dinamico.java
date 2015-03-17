/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Menu_Dinamico
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Menu_Dinamico 
{

    /** TableName=EXME_Menu_Dinamico */
    public static final String Table_Name = "EXME_Menu_Dinamico";

    /** AD_Table_ID=1200972 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_FormChild_ID */
    public static final String COLUMNNAME_AD_FormChild_ID = "AD_FormChild_ID";

	/** Set Child Form.
	  * Child Form
	  */
	public void setAD_FormChild_ID (int AD_FormChild_ID);

	/** Get Child Form.
	  * Child Form
	  */
	public int getAD_FormChild_ID();

    /** Column name AD_FormParent_ID */
    public static final String COLUMNNAME_AD_FormParent_ID = "AD_FormParent_ID";

	/** Set Parent Form.
	  * Parent Form
	  */
	public void setAD_FormParent_ID (int AD_FormParent_ID);

	/** Get Parent Form.
	  * Parent Form
	  */
	public int getAD_FormParent_ID();

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

    /** Column name AD_ProcessChild_ID */
    public static final String COLUMNNAME_AD_ProcessChild_ID = "AD_ProcessChild_ID";

	/** Set Child Process.
	  * Child Process
	  */
	public void setAD_ProcessChild_ID (int AD_ProcessChild_ID);

	/** Get Child Process.
	  * Child Process
	  */
	public int getAD_ProcessChild_ID();

    /** Column name AD_WindowChild_ID */
    public static final String COLUMNNAME_AD_WindowChild_ID = "AD_WindowChild_ID";

	/** Set Child Window.
	  * Child Window
	  */
	public void setAD_WindowChild_ID (int AD_WindowChild_ID);

	/** Get Child Window.
	  * Child Window
	  */
	public int getAD_WindowChild_ID();

    /** Column name ChildType */
    public static final String COLUMNNAME_ChildType = "ChildType";

	/** Set Child Type.
	  * Child Type
	  */
	public void setChildType (String ChildType);

	/** Get Child Type.
	  * Child Type
	  */
	public String getChildType();

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

    /** Column name DisplayLogic */
    public static final String COLUMNNAME_DisplayLogic = "DisplayLogic";

	/** Set Display Logic.
	  * If the Field is displayed, the result determines if the field is actually displayed
	  */
	public void setDisplayLogic (String DisplayLogic);

	/** Get Display Logic.
	  * If the Field is displayed, the result determines if the field is actually displayed
	  */
	public String getDisplayLogic();

    /** Column name EXME_Cuestionario_ID */
    public static final String COLUMNNAME_EXME_Cuestionario_ID = "EXME_Cuestionario_ID";

	/** Set Questionnaire.
	  * Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID);

	/** Get Questionnaire.
	  * Questionnaire
	  */
	public int getEXME_Cuestionario_ID();

    /** Column name EXME_FormSectionHeader_ID */
    public static final String COLUMNNAME_EXME_FormSectionHeader_ID = "EXME_FormSectionHeader_ID";

	/** Set Form Section's Header	  */
	public void setEXME_FormSectionHeader_ID (int EXME_FormSectionHeader_ID);

	/** Get Form Section's Header	  */
	public int getEXME_FormSectionHeader_ID();

	public I_EXME_FormSectionHeader getEXME_FormSectionHeader() throws RuntimeException;

    /** Column name EXME_Menu_Dinamico_ID */
    public static final String COLUMNNAME_EXME_Menu_Dinamico_ID = "EXME_Menu_Dinamico_ID";

	/** Set Dynamic Menu.
	  * Dynamic Menu
	  */
	public void setEXME_Menu_Dinamico_ID (int EXME_Menu_Dinamico_ID);

	/** Get Dynamic Menu.
	  * Dynamic Menu
	  */
	public int getEXME_Menu_Dinamico_ID();

    /** Column name Help_URI */
    public static final String COLUMNNAME_Help_URI = "Help_URI";

	/** Set Help URI.
	  * The URI with the help document for the current window.
	  */
	public void setHelp_URI (String Help_URI);

	/** Get Help URI.
	  * The URI with the help document for the current window.
	  */
	public String getHelp_URI();

    /** Column name Image */
    public static final String COLUMNNAME_Image = "Image";

	/** Set Image	  */
	public void setImage (String Image);

	/** Get Image	  */
	public String getImage();

    /** Column name ImageURL */
    public static final String COLUMNNAME_ImageURL = "ImageURL";

	/** Set Image URL.
	  * URL of  image
	  */
	public void setImageURL (String ImageURL);

	/** Get Image URL.
	  * URL of  image
	  */
	public String getImageURL();

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

    /** Column name Params */
    public static final String COLUMNNAME_Params = "Params";

	/** Set Parameters	  */
	public void setParams (String Params);

	/** Get Parameters	  */
	public String getParams();

    /** Column name ParentType */
    public static final String COLUMNNAME_ParentType = "ParentType";

	/** Set Parent Type.
	  * Parent Type
	  */
	public void setParentType (String ParentType);

	/** Get Parent Type.
	  * Parent Type
	  */
	public String getParentType();

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (BigDecimal Sequence);

	/** Get Sequence	  */
	public BigDecimal getSequence();

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
