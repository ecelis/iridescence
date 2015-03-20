/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_EntityType
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_EntityType 
{

    /** TableName=AD_EntityType */
    public static final String Table_Name = "AD_EntityType";

    /** AD_Table_ID=882 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_EntityType_ID */
    public static final String COLUMNNAME_AD_EntityType_ID = "AD_EntityType_ID";

	/** Set Entity Type.
	  * System Entity Type
	  */
	public void setAD_EntityType_ID (int AD_EntityType_ID);

	/** Get Entity Type.
	  * System Entity Type
	  */
	public int getAD_EntityType_ID();

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

    /** Column name Classpath */
    public static final String COLUMNNAME_Classpath = "Classpath";

	/** Set Classpath.
	  * Extension Classpath
	  */
	public void setClasspath (String Classpath);

	/** Get Classpath.
	  * Extension Classpath
	  */
	public String getClasspath();

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

    /** Column name ModelPackage */
    public static final String COLUMNNAME_ModelPackage = "ModelPackage";

	/** Set Model Package.
	  * Java Package of the model classes
	  */
	public void setModelPackage (String ModelPackage);

	/** Get Model Package.
	  * Java Package of the model classes
	  */
	public String getModelPackage();

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

    /** Column name Version */
    public static final String COLUMNNAME_Version = "Version";

	/** Set Version.
	  * Version of the table definition
	  */
	public void setVersion (String Version);

	/** Get Version.
	  * Version of the table definition
	  */
	public String getVersion();
}
