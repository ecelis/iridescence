/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_RegistrationValue
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_RegistrationValue 
{

    /** TableName=A_RegistrationValue */
    public static final String Table_Name = "A_RegistrationValue";

    /** AD_Table_ID=653 */
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

    /** Column name A_RegistrationAttribute_ID */
    public static final String COLUMNNAME_A_RegistrationAttribute_ID = "A_RegistrationAttribute_ID";

	/** Set Registration Attribute.
	  * Asset Registration Attribute
	  */
	public void setA_RegistrationAttribute_ID (int A_RegistrationAttribute_ID);

	/** Get Registration Attribute.
	  * Asset Registration Attribute
	  */
	public int getA_RegistrationAttribute_ID();

	public I_A_RegistrationAttribute getA_RegistrationAttribute() throws RuntimeException;

    /** Column name A_Registration_ID */
    public static final String COLUMNNAME_A_Registration_ID = "A_Registration_ID";

	/** Set Registration.
	  * User Asset Registration
	  */
	public void setA_Registration_ID (int A_Registration_ID);

	/** Get Registration.
	  * User Asset Registration
	  */
	public int getA_Registration_ID();

	public I_A_Registration getA_Registration() throws RuntimeException;

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
