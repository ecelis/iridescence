/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TeamMember
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_TeamMember 
{

    /** TableName=EXME_TeamMember */
    public static final String Table_Name = "EXME_TeamMember";

    /** AD_Table_ID=1200324 */
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

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name EXME_TeamMember_ID */
    public static final String COLUMNNAME_EXME_TeamMember_ID = "EXME_TeamMember_ID";

	/** Set Team Member.
	  * Work Team Member
	  */
	public void setEXME_TeamMember_ID (int EXME_TeamMember_ID);

	/** Get Team Member.
	  * Work Team Member
	  */
	public int getEXME_TeamMember_ID();

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

    /** Column name Puesto */
    public static final String COLUMNNAME_Puesto = "Puesto";

	/** Set Position	  */
	public void setPuesto (String Puesto);

	/** Get Position	  */
	public String getPuesto();

    /** Column name Telefono */
    public static final String COLUMNNAME_Telefono = "Telefono";

	/** Set Telephone.
	  * friend telephone
	  */
	public void setTelefono (String Telefono);

	/** Get Telephone.
	  * friend telephone
	  */
	public String getTelefono();

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