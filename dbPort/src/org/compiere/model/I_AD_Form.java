/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Form
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_AD_Form 
{

    /** TableName=AD_Form */
    public static final String Table_Name = "AD_Form";

    /** AD_Table_ID=376 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AccessLevel */
    public static final String COLUMNNAME_AccessLevel = "AccessLevel";

	/** Set Data Access Level.
	  * Access Level required
	  */
	public void setAccessLevel (String AccessLevel);

	/** Get Data Access Level.
	  * Access Level required
	  */
	public String getAccessLevel();

    /** Column name AD_Form_ID */
    public static final String COLUMNNAME_AD_Form_ID = "AD_Form_ID";

	/** Set Special Form.
	  * Special Form
	  */
	public void setAD_Form_ID (int AD_Form_ID);

	/** Get Special Form.
	  * Special Form
	  */
	public int getAD_Form_ID();

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

    /** Column name Classname */
    public static final String COLUMNNAME_Classname = "Classname";

	/** Set Classname.
	  * Java Classname
	  */
	public void setClassname (String Classname);

	/** Get Classname.
	  * Java Classname
	  */
	public String getClassname();

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

    /** Column name EntityType */
    public static final String COLUMNNAME_EntityType = "EntityType";

	/** Set Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType);

	/** Get Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public String getEntityType();

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

    /** Column name IsBetaFunctionality */
    public static final String COLUMNNAME_IsBetaFunctionality = "IsBetaFunctionality";

	/** Set Beta Functionality.
	  * This functionality is considered Beta
	  */
	public void setIsBetaFunctionality (boolean IsBetaFunctionality);

	/** Get Beta Functionality.
	  * This functionality is considered Beta
	  */
	public boolean isBetaFunctionality();

    /** Column name IsForPatientAccess */
    public static final String COLUMNNAME_IsForPatientAccess = "IsForPatientAccess";

	/** Set IsForPatientAccess	  */
	public void setIsForPatientAccess (boolean IsForPatientAccess);

	/** Get IsForPatientAccess	  */
	public boolean isForPatientAccess();

    /** Column name IsSection */
    public static final String COLUMNNAME_IsSection = "IsSection";

	/** Set IsSection	  */
	public void setIsSection (boolean IsSection);

	/** Get IsSection	  */
	public boolean isSection();

    /** Column name JSPURL */
    public static final String COLUMNNAME_JSPURL = "JSPURL";

	/** Set jsp URL.
	  * Web URL of the jsp function
	  */
	public void setJSPURL (String JSPURL);

	/** Get jsp URL.
	  * Web URL of the jsp function
	  */
	public String getJSPURL();

    /** Column name MultiAccess */
    public static final String COLUMNNAME_MultiAccess = "MultiAccess";

	/** Set Multi Access.
	  * Multi Access
	  */
	public void setMultiAccess (boolean MultiAccess);

	/** Get Multi Access.
	  * Multi Access
	  */
	public boolean isMultiAccess();

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
