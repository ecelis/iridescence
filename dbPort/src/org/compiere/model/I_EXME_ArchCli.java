/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ArchCli
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ArchCli 
{

    /** TableName=EXME_ArchCli */
    public static final String Table_Name = "EXME_ArchCli";

    /** AD_Table_ID=1000142 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name Codigo */
    public static final String COLUMNNAME_Codigo = "Codigo";

	/** Set Code	  */
	public void setCodigo (String Codigo);

	/** Get Code	  */
	public String getCodigo();

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

    /** Column name EXME_ArchCli_ID */
    public static final String COLUMNNAME_EXME_ArchCli_ID = "EXME_ArchCli_ID";

	/** Set Clinical Archive.
	  * Clinical Archive
	  */
	public void setEXME_ArchCli_ID (int EXME_ArchCli_ID);

	/** Get Clinical Archive.
	  * Clinical Archive
	  */
	public int getEXME_ArchCli_ID();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

    /** Column name EXME_TipoExped_ID */
    public static final String COLUMNNAME_EXME_TipoExped_ID = "EXME_TipoExped_ID";

	/** Set Type of Medical Record.
	  * Type of Medical Record
	  */
	public void setEXME_TipoExped_ID (int EXME_TipoExped_ID);

	/** Get Type of Medical Record.
	  * Type of Medical Record
	  */
	public int getEXME_TipoExped_ID();

    /** Column name Fecha_Creacion */
    public static final String COLUMNNAME_Fecha_Creacion = "Fecha_Creacion";

	/** Set Creation Date.
	  * creation Date
	  */
	public void setFecha_Creacion (Timestamp Fecha_Creacion);

	/** Get Creation Date.
	  * creation Date
	  */
	public Timestamp getFecha_Creacion();

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

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

    /** Column name Ubicacion */
    public static final String COLUMNNAME_Ubicacion = "Ubicacion";

	/** Set Location of Element.
	  * Location of Element
	  */
	public void setUbicacion (String Ubicacion);

	/** Get Location of Element.
	  * Location of Element
	  */
	public String getUbicacion();

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
