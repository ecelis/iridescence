/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FormSectionConf
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_FormSectionConf 
{

    /** TableName=EXME_FormSectionConf */
    public static final String Table_Name = "EXME_FormSectionConf";

    /** AD_Table_ID=1201290 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name EXME_FormSectionConf_ID */
    public static final String COLUMNNAME_EXME_FormSectionConf_ID = "EXME_FormSectionConf_ID";

	/** Set Form Section Configuration	  */
	public void setEXME_FormSectionConf_ID (int EXME_FormSectionConf_ID);

	/** Get Form Section Configuration	  */
	public int getEXME_FormSectionConf_ID();

    /** Column name EXME_FormSectionHeader_ID */
    public static final String COLUMNNAME_EXME_FormSectionHeader_ID = "EXME_FormSectionHeader_ID";

	/** Set Form Section's Header	  */
	public void setEXME_FormSectionHeader_ID (int EXME_FormSectionHeader_ID);

	/** Get Form Section's Header	  */
	public int getEXME_FormSectionHeader_ID();

	public I_EXME_FormSectionHeader getEXME_FormSectionHeader() throws RuntimeException;

    /** Column name EXME_GrupoCuestionario_ID */
    public static final String COLUMNNAME_EXME_GrupoCuestionario_ID = "EXME_GrupoCuestionario_ID";

	/** Set Form Group.
	  * Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID);

	/** Get Form Group.
	  * Form Group
	  */
	public int getEXME_GrupoCuestionario_ID();

	public I_EXME_GrupoCuestionario getEXME_GrupoCuestionario() throws RuntimeException;

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

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (int Sequence);

	/** Get Sequence	  */
	public int getSequence();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

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
