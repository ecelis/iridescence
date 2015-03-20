/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Incapacidad_Pac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Incapacidad_Pac 
{

    /** TableName=EXME_Incapacidad_Pac */
    public static final String Table_Name = "EXME_Incapacidad_Pac";

    /** AD_Table_ID=1200528 */
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

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException;

    /** Column name EXME_Especialidad2_ID */
    public static final String COLUMNNAME_EXME_Especialidad2_ID = "EXME_Especialidad2_ID";

	/** Set Specialty	  */
	public void setEXME_Especialidad2_ID (int EXME_Especialidad2_ID);

	/** Get Specialty	  */
	public int getEXME_Especialidad2_ID();

    /** Column name EXME_Incapacidad_ID */
    public static final String COLUMNNAME_EXME_Incapacidad_ID = "EXME_Incapacidad_ID";

	/** Set Disability	  */
	public void setEXME_Incapacidad_ID (int EXME_Incapacidad_ID);

	/** Get Disability	  */
	public int getEXME_Incapacidad_ID();

	public I_EXME_Incapacidad getEXME_Incapacidad() throws RuntimeException;

    /** Column name EXME_Incapacidad_Pac_ID */
    public static final String COLUMNNAME_EXME_Incapacidad_Pac_ID = "EXME_Incapacidad_Pac_ID";

	/** Set Patient Disability	  */
	public void setEXME_Incapacidad_Pac_ID (int EXME_Incapacidad_Pac_ID);

	/** Get Patient Disability	  */
	public int getEXME_Incapacidad_Pac_ID();

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_Medico2_ID */
    public static final String COLUMNNAME_EXME_Medico2_ID = "EXME_Medico2_ID";

	/** Set Doctor 2	  */
	public void setEXME_Medico2_ID (int EXME_Medico2_ID);

	/** Get Doctor 2	  */
	public int getEXME_Medico2_ID();

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

    /** Column name Fecha_Registro */
    public static final String COLUMNNAME_Fecha_Registro = "Fecha_Registro";

	/** Set Date of Record.
	  * Date of Record
	  */
	public void setFecha_Registro (Timestamp Fecha_Registro);

	/** Get Date of Record.
	  * Date of Record
	  */
	public Timestamp getFecha_Registro();

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
