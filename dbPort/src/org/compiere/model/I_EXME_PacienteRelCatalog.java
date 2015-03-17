/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacienteRelCatalog
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PacienteRelCatalog 
{

    /** TableName=EXME_PacienteRelCatalog */
    public static final String Table_Name = "EXME_PacienteRelCatalog";

    /** AD_Table_ID=1201297 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

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

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name EXME_PacienteRelCatalog_ID */
    public static final String COLUMNNAME_EXME_PacienteRelCatalog_ID = "EXME_PacienteRelCatalog_ID";

	/** Set Patient Relation Catalog	  */
	public void setEXME_PacienteRelCatalog_ID (int EXME_PacienteRelCatalog_ID);

	/** Get Patient Relation Catalog	  */
	public int getEXME_PacienteRelCatalog_ID();

    /** Column name EXME_PacienteRel_ID */
    public static final String COLUMNNAME_EXME_PacienteRel_ID = "EXME_PacienteRel_ID";

	/** Set Patient Relations.
	  * Patient Relations
	  */
	public void setEXME_PacienteRel_ID (int EXME_PacienteRel_ID);

	/** Get Patient Relations.
	  * Patient Relations
	  */
	public int getEXME_PacienteRel_ID();

	public I_EXME_PacienteRel getEXME_PacienteRel() throws RuntimeException;

    /** Column name EXME_Parentesco_ID */
    public static final String COLUMNNAME_EXME_Parentesco_ID = "EXME_Parentesco_ID";

	/** Set Kinship.
	  * Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID);

	/** Get Kinship.
	  * Kinship
	  */
	public int getEXME_Parentesco_ID();

	public I_EXME_Parentesco getEXME_Parentesco() throws RuntimeException;

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsPatient */
    public static final String COLUMNNAME_IsPatient = "IsPatient";

	/** Set Patient.
	  * Is patient
	  */
	public void setIsPatient (boolean IsPatient);

	/** Get Patient.
	  * Is patient
	  */
	public boolean isPatient();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation
	  */
	public String getType();
}
