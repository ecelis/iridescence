/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RoleAut
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RoleAut 
{

    /** TableName=EXME_RoleAut */
    public static final String Table_Name = "EXME_RoleAut";

    /** AD_Table_ID=1200527 */
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

    /** Column name AD_Role_ID */
    public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

	/** Set Role.
	  * Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID);

	/** Get Role.
	  * Responsibility Role
	  */
	public int getAD_Role_ID();

	public I_AD_Role getAD_Role() throws RuntimeException;

    /** Column name AD_Role_Supervisor1_ID */
    public static final String COLUMNNAME_AD_Role_Supervisor1_ID = "AD_Role_Supervisor1_ID";

	/** Set First Supervisor	  */
	public void setAD_Role_Supervisor1_ID (int AD_Role_Supervisor1_ID);

	/** Get First Supervisor	  */
	public int getAD_Role_Supervisor1_ID();

    /** Column name AD_Role_Supervisor2_ID */
    public static final String COLUMNNAME_AD_Role_Supervisor2_ID = "AD_Role_Supervisor2_ID";

	/** Set Second Supervisor	  */
	public void setAD_Role_Supervisor2_ID (int AD_Role_Supervisor2_ID);

	/** Get Second Supervisor	  */
	public int getAD_Role_Supervisor2_ID();

    /** Column name AD_Role_Supervisor3_ID */
    public static final String COLUMNNAME_AD_Role_Supervisor3_ID = "AD_Role_Supervisor3_ID";

	/** Set Third Supervisor	  */
	public void setAD_Role_Supervisor3_ID (int AD_Role_Supervisor3_ID);

	/** Get Third Supervisor	  */
	public int getAD_Role_Supervisor3_ID();

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

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

    /** Column name EXME_RoleAut_ID */
    public static final String COLUMNNAME_EXME_RoleAut_ID = "EXME_RoleAut_ID";

	/** Set Authorization by Profile	  */
	public void setEXME_RoleAut_ID (int EXME_RoleAut_ID);

	/** Get Authorization by Profile	  */
	public int getEXME_RoleAut_ID();

    /** Column name Fecha_Fin */
    public static final String COLUMNNAME_Fecha_Fin = "Fecha_Fin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFecha_Fin();

    /** Column name Fecha_Ini */
    public static final String COLUMNNAME_Fecha_Ini = "Fecha_Ini";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFecha_Ini();

    /** Column name Importe */
    public static final String COLUMNNAME_Importe = "Importe";

	/** Set Amount	  */
	public void setImporte (BigDecimal Importe);

	/** Get Amount	  */
	public BigDecimal getImporte();

    /** Column name Importe1 */
    public static final String COLUMNNAME_Importe1 = "Importe1";

	/** Set Amount to authorize	  */
	public void setImporte1 (BigDecimal Importe1);

	/** Get Amount to authorize	  */
	public BigDecimal getImporte1();

    /** Column name Importe2 */
    public static final String COLUMNNAME_Importe2 = "Importe2";

	/** Set Amount to authorize	  */
	public void setImporte2 (BigDecimal Importe2);

	/** Get Amount to authorize	  */
	public BigDecimal getImporte2();

    /** Column name Importe3 */
    public static final String COLUMNNAME_Importe3 = "Importe3";

	/** Set Amount to authorize	  */
	public void setImporte3 (BigDecimal Importe3);

	/** Get Amount to authorize	  */
	public BigDecimal getImporte3();

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
