/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Paciente_TS
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Paciente_TS 
{

    /** TableName=I_EXME_Paciente_TS */
    public static final String Table_Name = "I_EXME_Paciente_TS";

    /** AD_Table_ID=1200078 */
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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name Apellido1_Resp */
    public static final String COLUMNNAME_Apellido1_Resp = "Apellido1_Resp";

	/** Set Responsible Person's Last Name	  */
	public void setApellido1_Resp (String Apellido1_Resp);

	/** Get Responsible Person's Last Name	  */
	public String getApellido1_Resp();

    /** Column name Apellido2_Resp */
    public static final String COLUMNNAME_Apellido2_Resp = "Apellido2_Resp";

	/** Set Responsible Person's Mother's Maiden Name	  */
	public void setApellido2_Resp (String Apellido2_Resp);

	/** Get Responsible Person's Mother's Maiden Name	  */
	public String getApellido2_Resp();

    /** Column name Calle_Dom_Resp_Value */
    public static final String COLUMNNAME_Calle_Dom_Resp_Value = "Calle_Dom_Resp_Value";

	/** Set Adress Street of Responsible Person Value.
	  * Adress Street of Responsible Person Value
	  */
	public void setCalle_Dom_Resp_Value (String Calle_Dom_Resp_Value);

	/** Get Adress Street of Responsible Person Value.
	  * Adress Street of Responsible Person Value
	  */
	public String getCalle_Dom_Resp_Value();

    /** Column name Calle_Pac_Prov_Value */
    public static final String COLUMNNAME_Calle_Pac_Prov_Value = "Calle_Pac_Prov_Value";

	/** Set Provisional Work Adress Street of Patient Value.
	  * Provisional Work Adress Street of Patient Value
	  */
	public void setCalle_Pac_Prov_Value (String Calle_Pac_Prov_Value);

	/** Get Provisional Work Adress Street of Patient Value.
	  * Provisional Work Adress Street of Patient Value
	  */
	public String getCalle_Pac_Prov_Value();

    /** Column name Calle_Pac_Trab_Value */
    public static final String COLUMNNAME_Calle_Pac_Trab_Value = "Calle_Pac_Trab_Value";

	/** Set Work Adress Street of Patient Value.
	  * Work Adress Street of Patient Value
	  */
	public void setCalle_Pac_Trab_Value (String Calle_Pac_Trab_Value);

	/** Get Work Adress Street of Patient Value.
	  * Work Adress Street of Patient Value
	  */
	public String getCalle_Pac_Trab_Value();

    /** Column name Calle_Perm_Value */
    public static final String COLUMNNAME_Calle_Perm_Value = "Calle_Perm_Value";

	/** Set Permanent Adress Street of Patient Value.
	  * Permanent Adress Street of Patient Value
	  */
	public void setCalle_Perm_Value (String Calle_Perm_Value);

	/** Get Permanent Adress Street of Patient Value.
	  * Permanent Adress Street of Patient Value
	  */
	public String getCalle_Perm_Value();

    /** Column name Calle_Trab_Resp_Value */
    public static final String COLUMNNAME_Calle_Trab_Resp_Value = "Calle_Trab_Resp_Value";

	/** Set Work Adress Street of Patient's Responsible Person Value.
	  * Work Adress Street of Patient's Responsible Person Value
	  */
	public void setCalle_Trab_Resp_Value (String Calle_Trab_Resp_Value);

	/** Get Work Adress Street of Patient's Responsible Person Value.
	  * Work Adress Street of Patient's Responsible Person Value
	  */
	public String getCalle_Trab_Resp_Value();

    /** Column name C_Location_D_Resp_ID */
    public static final String COLUMNNAME_C_Location_D_Resp_ID = "C_Location_D_Resp_ID";

	/** Set Responsable Address.
	  * Responsable Address
	  */
	public void setC_Location_D_Resp_ID (int C_Location_D_Resp_ID);

	/** Get Responsable Address.
	  * Responsable Address
	  */
	public int getC_Location_D_Resp_ID();

    /** Column name C_Location_Perm_ID */
    public static final String COLUMNNAME_C_Location_Perm_ID = "C_Location_Perm_ID";

	/** Set Permanent Address.
	  * Permanent Address
	  */
	public void setC_Location_Perm_ID (int C_Location_Perm_ID);

	/** Get Permanent Address.
	  * Permanent Address
	  */
	public int getC_Location_Perm_ID();

    /** Column name C_Location_P_Prov_ID */
    public static final String COLUMNNAME_C_Location_P_Prov_ID = "C_Location_P_Prov_ID";

	/** Set Prov. Patient's Address.
	  * Prov. Patient's Address
	  */
	public void setC_Location_P_Prov_ID (int C_Location_P_Prov_ID);

	/** Get Prov. Patient's Address.
	  * Prov. Patient's Address
	  */
	public int getC_Location_P_Prov_ID();

    /** Column name C_Location_P_Trab_ID */
    public static final String COLUMNNAME_C_Location_P_Trab_ID = "C_Location_P_Trab_ID";

	/** Set Patient Work's Address.
	  * Patient Work's Address
	  */
	public void setC_Location_P_Trab_ID (int C_Location_P_Trab_ID);

	/** Get Patient Work's Address.
	  * Patient Work's Address
	  */
	public int getC_Location_P_Trab_ID();

    /** Column name C_Location_T_Resp_ID */
    public static final String COLUMNNAME_C_Location_T_Resp_ID = "C_Location_T_Resp_ID";

	/** Set Responsable Work's Address.
	  * Responsable Work's Address
	  */
	public void setC_Location_T_Resp_ID (int C_Location_T_Resp_ID);

	/** Get Responsable Work's Address.
	  * Responsable Work's Address
	  */
	public int getC_Location_T_Resp_ID();

    /** Column name Col_Dom_Resp_Value */
    public static final String COLUMNNAME_Col_Dom_Resp_Value = "Col_Dom_Resp_Value";

	/** Set Address Colony Of Responsible Person Value.
	  * Address Colony Of Responsible Person Value
	  */
	public void setCol_Dom_Resp_Value (String Col_Dom_Resp_Value);

	/** Get Address Colony Of Responsible Person Value.
	  * Address Colony Of Responsible Person Value
	  */
	public String getCol_Dom_Resp_Value();

    /** Column name Col_Pac_Prov_Value */
    public static final String COLUMNNAME_Col_Pac_Prov_Value = "Col_Pac_Prov_Value";

	/** Set Address Colony of the Patient Value.
	  * Address Colony of the Patient Value
	  */
	public void setCol_Pac_Prov_Value (String Col_Pac_Prov_Value);

	/** Get Address Colony of the Patient Value.
	  * Address Colony of the Patient Value
	  */
	public String getCol_Pac_Prov_Value();

    /** Column name Col_Pac_Trab_Value */
    public static final String COLUMNNAME_Col_Pac_Trab_Value = "Col_Pac_Trab_Value";

	/** Set Work Address Colony of the Patient Value.
	  * Work Address Colony of the Patient Value
	  */
	public void setCol_Pac_Trab_Value (String Col_Pac_Trab_Value);

	/** Get Work Address Colony of the Patient Value.
	  * Work Address Colony of the Patient Value
	  */
	public String getCol_Pac_Trab_Value();

    /** Column name Col_Perm_Value */
    public static final String COLUMNNAME_Col_Perm_Value = "Col_Perm_Value";

	/** Set Permanent Address Colony Value.
	  * Permanent Address Colony Value
	  */
	public void setCol_Perm_Value (String Col_Perm_Value);

	/** Get Permanent Address Colony Value.
	  * Permanent Address Colony Value
	  */
	public String getCol_Perm_Value();

    /** Column name Col_Trab_Resp_Value */
    public static final String COLUMNNAME_Col_Trab_Resp_Value = "Col_Trab_Resp_Value";

	/** Set Work address colony of the Responsible person Value.
	  * Work address colony of the Responsible person Value
	  */
	public void setCol_Trab_Resp_Value (String Col_Trab_Resp_Value);

	/** Get Work address colony of the Responsible person Value.
	  * Work address colony of the Responsible person Value
	  */
	public String getCol_Trab_Resp_Value();

    /** Column name Conyuge_Desco */
    public static final String COLUMNNAME_Conyuge_Desco = "Conyuge_Desco";

	/** Set Unknown Spouse.
	  * Unknown Spouse
	  */
	public void setConyuge_Desco (boolean Conyuge_Desco);

	/** Get Unknown Spouse.
	  * Unknown Spouse
	  */
	public boolean isConyuge_Desco();

    /** Column name Conyuge_Vive */
    public static final String COLUMNNAME_Conyuge_Vive = "Conyuge_Vive";

	/** Set Alive Spouse.
	  * Alive Spouse
	  */
	public void setConyuge_Vive (boolean Conyuge_Vive);

	/** Get Alive Spouse.
	  * Alive Spouse
	  */
	public boolean isConyuge_Vive();

    /** Column name CopyAddPerm */
    public static final String COLUMNNAME_CopyAddPerm = "CopyAddPerm";

	/** Set CopyAddPerm.
	  * CopyAddPerm
	  */
	public void setCopyAddPerm (String CopyAddPerm);

	/** Get CopyAddPerm.
	  * CopyAddPerm
	  */
	public String getCopyAddPerm();

    /** Column name CopyAddr */
    public static final String COLUMNNAME_CopyAddr = "CopyAddr";

	/** Set Copy Address to Responsible.
	  * Copy Address to Responsible
	  */
	public void setCopyAddr (boolean CopyAddr);

	/** Get Copy Address to Responsible.
	  * Copy Address to Responsible
	  */
	public boolean isCopyAddr();

    /** Column name CP_Dom_Resp_Value */
    public static final String COLUMNNAME_CP_Dom_Resp_Value = "CP_Dom_Resp_Value";

	/** Set Responsible person's postal code of home address_Value.
	  * Responsible person's postal code of home address_Value
	  */
	public void setCP_Dom_Resp_Value (String CP_Dom_Resp_Value);

	/** Get Responsible person's postal code of home address_Value.
	  * Responsible person's postal code of home address_Value
	  */
	public String getCP_Dom_Resp_Value();

    /** Column name CP_Pac_Prov_Value */
    public static final String COLUMNNAME_CP_Pac_Prov_Value = "CP_Pac_Prov_Value";

	/** Set Patient's Provisional Postal Code.
	  * Patient's Provisional Postal Code
	  */
	public void setCP_Pac_Prov_Value (String CP_Pac_Prov_Value);

	/** Get Patient's Provisional Postal Code.
	  * Patient's Provisional Postal Code
	  */
	public String getCP_Pac_Prov_Value();

    /** Column name CP_Pac_Trab_Value */
    public static final String COLUMNNAME_CP_Pac_Trab_Value = "CP_Pac_Trab_Value";

	/** Set Patient's work postal code_Value.
	  * Patient's work postal code_Value
	  */
	public void setCP_Pac_Trab_Value (String CP_Pac_Trab_Value);

	/** Get Patient's work postal code_Value.
	  * Patient's work postal code_Value
	  */
	public String getCP_Pac_Trab_Value();

    /** Column name CP_Procedencia_Value */
    public static final String COLUMNNAME_CP_Procedencia_Value = "CP_Procedencia_Value";

	/** Set CP_Procedencia_Value.
	  * Provenance Postal Code_Value
	  */
	public void setCP_Procedencia_Value (String CP_Procedencia_Value);

	/** Get CP_Procedencia_Value.
	  * Provenance Postal Code_Value
	  */
	public String getCP_Procedencia_Value();

    /** Column name CP_Trab_Resp_Value */
    public static final String COLUMNNAME_CP_Trab_Resp_Value = "CP_Trab_Resp_Value";

	/** Set Work's postal code of responsible person_Value.
	  * Work's postal code of responsible person_Value
	  */
	public void setCP_Trab_Resp_Value (String CP_Trab_Resp_Value);

	/** Get Work's postal code of responsible person_Value.
	  * Work's postal code of responsible person_Value
	  */
	public String getCP_Trab_Resp_Value();

    /** Column name Diagnostico_Egr */
    public static final String COLUMNNAME_Diagnostico_Egr = "Diagnostico_Egr";

	/** Set Final Diagnostic.
	  * Final Diagnostic
	  */
	public void setDiagnostico_Egr (String Diagnostico_Egr);

	/** Get Final Diagnostic.
	  * Final Diagnostic
	  */
	public String getDiagnostico_Egr();

    /** Column name Diagnostico_Ing */
    public static final String COLUMNNAME_Diagnostico_Ing = "Diagnostico_Ing";

	/** Set Initial Diagnostic.
	  * Initial Diagnostic
	  */
	public void setDiagnostico_Ing (String Diagnostico_Ing);

	/** Get Initial Diagnostic.
	  * Initial Diagnostic
	  */
	public String getDiagnostico_Ing();

    /** Column name Edo_Dom_Resp_Value */
    public static final String COLUMNNAME_Edo_Dom_Resp_Value = "Edo_Dom_Resp_Value";

	/** Set State of Responsible Person_Value.
	  * State of Responsible Person_Value
	  */
	public void setEdo_Dom_Resp_Value (String Edo_Dom_Resp_Value);

	/** Get State of Responsible Person_Value.
	  * State of Responsible Person_Value
	  */
	public String getEdo_Dom_Resp_Value();

    /** Column name Edo_Pac_Prov_Value */
    public static final String COLUMNNAME_Edo_Pac_Prov_Value = "Edo_Pac_Prov_Value";

	/** Set Patient Province_Value.
	  * Patient Province_Value
	  */
	public void setEdo_Pac_Prov_Value (String Edo_Pac_Prov_Value);

	/** Get Patient Province_Value.
	  * Patient Province_Value
	  */
	public String getEdo_Pac_Prov_Value();

    /** Column name Edo_Pac_Trab_Value */
    public static final String COLUMNNAME_Edo_Pac_Trab_Value = "Edo_Pac_Trab_Value";

	/** Set Patient work's State.
	  * Patient work's State
	  */
	public void setEdo_Pac_Trab_Value (String Edo_Pac_Trab_Value);

	/** Get Patient work's State.
	  * Patient work's State
	  */
	public String getEdo_Pac_Trab_Value();

    /** Column name Edo_Perm_Value */
    public static final String COLUMNNAME_Edo_Perm_Value = "Edo_Perm_Value";

	/** Set Permanent State_Value.
	  * Permanent State_Value
	  */
	public void setEdo_Perm_Value (String Edo_Perm_Value);

	/** Get Permanent State_Value.
	  * Permanent State_Value
	  */
	public String getEdo_Perm_Value();

    /** Column name Edo_Trab_Resp_Value */
    public static final String COLUMNNAME_Edo_Trab_Resp_Value = "Edo_Trab_Resp_Value";

	/** Set Responsible Person work's State_Value.
	  * Responsible Person work's State_Value
	  */
	public void setEdo_Trab_Resp_Value (String Edo_Trab_Resp_Value);

	/** Get Responsible Person work's State_Value.
	  * Responsible Person work's State_Value
	  */
	public String getEdo_Trab_Resp_Value();

    /** Column name EXME_Delegacion_D_Resp_ID */
    public static final String COLUMNNAME_EXME_Delegacion_D_Resp_ID = "EXME_Delegacion_D_Resp_ID";

	/** Set Responsible Address Area.
	  * Responsible Address Area
	  */
	public void setEXME_Delegacion_D_Resp_ID (int EXME_Delegacion_D_Resp_ID);

	/** Get Responsible Address Area.
	  * Responsible Address Area
	  */
	public int getEXME_Delegacion_D_Resp_ID();

    /** Column name EXME_Delegacion_D_Resp_Value */
    public static final String COLUMNNAME_EXME_Delegacion_D_Resp_Value = "EXME_Delegacion_D_Resp_Value";

	/** Set EXME_Delegacion_D_Resp_Value.
	  * EXME_Delegacion_D_Resp_Value
	  */
	public void setEXME_Delegacion_D_Resp_Value (String EXME_Delegacion_D_Resp_Value);

	/** Get EXME_Delegacion_D_Resp_Value.
	  * EXME_Delegacion_D_Resp_Value
	  */
	public String getEXME_Delegacion_D_Resp_Value();

    /** Column name EXME_Delegacion_Perm_ID */
    public static final String COLUMNNAME_EXME_Delegacion_Perm_ID = "EXME_Delegacion_Perm_ID";

	/** Set Permanent Zone (Delegation).
	  * Permanent Zone (Delegation)
	  */
	public void setEXME_Delegacion_Perm_ID (int EXME_Delegacion_Perm_ID);

	/** Get Permanent Zone (Delegation).
	  * Permanent Zone (Delegation)
	  */
	public int getEXME_Delegacion_Perm_ID();

    /** Column name EXME_Delegacion_Perm_Value */
    public static final String COLUMNNAME_EXME_Delegacion_Perm_Value = "EXME_Delegacion_Perm_Value";

	/** Set EXME_Delegacion_Perm_Value	  */
	public void setEXME_Delegacion_Perm_Value (String EXME_Delegacion_Perm_Value);

	/** Get EXME_Delegacion_Perm_Value	  */
	public String getEXME_Delegacion_Perm_Value();

    /** Column name EXME_Delegacion_P_Prov_ID */
    public static final String COLUMNNAME_EXME_Delegacion_P_Prov_ID = "EXME_Delegacion_P_Prov_ID";

	/** Set Office Branch Prov Add..
	  * Office Branch prov Add.
	  */
	public void setEXME_Delegacion_P_Prov_ID (int EXME_Delegacion_P_Prov_ID);

	/** Get Office Branch Prov Add..
	  * Office Branch prov Add.
	  */
	public int getEXME_Delegacion_P_Prov_ID();

    /** Column name EXME_Delegacion_P_Prov_Value */
    public static final String COLUMNNAME_EXME_Delegacion_P_Prov_Value = "EXME_Delegacion_P_Prov_Value";

	/** Set EXME_Delegacion_P_Prov_Value.
	  * EXME_Delegacion_P_Prov_Value
	  */
	public void setEXME_Delegacion_P_Prov_Value (String EXME_Delegacion_P_Prov_Value);

	/** Get EXME_Delegacion_P_Prov_Value.
	  * EXME_Delegacion_P_Prov_Value
	  */
	public String getEXME_Delegacion_P_Prov_Value();

    /** Column name EXME_Delegacion_P_Trab_ID */
    public static final String COLUMNNAME_EXME_Delegacion_P_Trab_ID = "EXME_Delegacion_P_Trab_ID";

	/** Set Worker Add Off Branch..
	  * Worker Add Off Branch
	  */
	public void setEXME_Delegacion_P_Trab_ID (int EXME_Delegacion_P_Trab_ID);

	/** Get Worker Add Off Branch..
	  * Worker Add Off Branch
	  */
	public int getEXME_Delegacion_P_Trab_ID();

    /** Column name EXME_Delegacion_P_Trab_Value */
    public static final String COLUMNNAME_EXME_Delegacion_P_Trab_Value = "EXME_Delegacion_P_Trab_Value";

	/** Set EXME_Delegacion_P_Trab_Value	  */
	public void setEXME_Delegacion_P_Trab_Value (String EXME_Delegacion_P_Trab_Value);

	/** Get EXME_Delegacion_P_Trab_Value	  */
	public String getEXME_Delegacion_P_Trab_Value();

    /** Column name EXME_Delegacion_T_Resp_ID */
    public static final String COLUMNNAME_EXME_Delegacion_T_Resp_ID = "EXME_Delegacion_T_Resp_ID";

	/** Set Resp Worker Add Off Branch..
	  * Resp Worker Add off Branch.
	  */
	public void setEXME_Delegacion_T_Resp_ID (int EXME_Delegacion_T_Resp_ID);

	/** Get Resp Worker Add Off Branch..
	  * Resp Worker Add off Branch.
	  */
	public int getEXME_Delegacion_T_Resp_ID();

    /** Column name EXME_Delegacion_T_Resp_Value */
    public static final String COLUMNNAME_EXME_Delegacion_T_Resp_Value = "EXME_Delegacion_T_Resp_Value";

	/** Set EXME_Delegacion_T_Resp_Value.
	  * EXME_Delegacion_T_Resp_Value
	  */
	public void setEXME_Delegacion_T_Resp_Value (String EXME_Delegacion_T_Resp_Value);

	/** Get EXME_Delegacion_T_Resp_Value.
	  * EXME_Delegacion_T_Resp_Value
	  */
	public String getEXME_Delegacion_T_Resp_Value();

    /** Column name EXME_Escolaridad_ID */
    public static final String COLUMNNAME_EXME_Escolaridad_ID = "EXME_Escolaridad_ID";

	/** Set Schooling.
	  * Schooling
	  */
	public void setEXME_Escolaridad_ID (int EXME_Escolaridad_ID);

	/** Get Schooling.
	  * Schooling
	  */
	public int getEXME_Escolaridad_ID();

	public I_EXME_Escolaridad getEXME_Escolaridad() throws RuntimeException;

    /** Column name EXME_Escolaridad_Value */
    public static final String COLUMNNAME_EXME_Escolaridad_Value = "EXME_Escolaridad_Value";

	/** Set Schooling.
	  * Schooling
	  */
	public void setEXME_Escolaridad_Value (String EXME_Escolaridad_Value);

	/** Get Schooling.
	  * Schooling
	  */
	public String getEXME_Escolaridad_Value();

    /** Column name EXME_Especialidad */
    public static final String COLUMNNAME_EXME_Especialidad = "EXME_Especialidad";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad (String EXME_Especialidad);

	/** Get Specialty.
	  * Specialty
	  */
	public String getEXME_Especialidad();

    /** Column name EXME_Especialidad_TS_ID */
    public static final String COLUMNNAME_EXME_Especialidad_TS_ID = "EXME_Especialidad_TS_ID";

	/** Set Social Work Specialty.
	  * Social Work Specialty
	  */
	public void setEXME_Especialidad_TS_ID (int EXME_Especialidad_TS_ID);

	/** Get Social Work Specialty.
	  * Social Work Specialty
	  */
	public int getEXME_Especialidad_TS_ID();

	public I_EXME_Especialidad_TS getEXME_Especialidad_TS() throws RuntimeException;

    /** Column name EXME_Especialidad_Value */
    public static final String COLUMNNAME_EXME_Especialidad_Value = "EXME_Especialidad_Value";

	/** Set Speciality Value	  */
	public void setEXME_Especialidad_Value (String EXME_Especialidad_Value);

	/** Get Speciality Value	  */
	public String getEXME_Especialidad_Value();

    /** Column name EXME_Institucion_ID */
    public static final String COLUMNNAME_EXME_Institucion_ID = "EXME_Institucion_ID";

	/** Set Institution.
	  * Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Institution.
	  * Institution
	  */
	public int getEXME_Institucion_ID();

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException;

    /** Column name EXME_Institucion_Value */
    public static final String COLUMNNAME_EXME_Institucion_Value = "EXME_Institucion_Value";

	/** Set Institution Value.
	  * Institution
	  */
	public void setEXME_Institucion_Value (String EXME_Institucion_Value);

	/** Get Institution Value.
	  * Institution
	  */
	public String getEXME_Institucion_Value();

    /** Column name EXME_Nacionalidad_ID */
    public static final String COLUMNNAME_EXME_Nacionalidad_ID = "EXME_Nacionalidad_ID";

	/** Set Nationality.
	  * Nationality
	  */
	public void setEXME_Nacionalidad_ID (int EXME_Nacionalidad_ID);

	/** Get Nationality.
	  * Nationality
	  */
	public int getEXME_Nacionalidad_ID();

	public I_EXME_Nacionalidad getEXME_Nacionalidad() throws RuntimeException;

    /** Column name EXME_Nacionalidad_Value */
    public static final String COLUMNNAME_EXME_Nacionalidad_Value = "EXME_Nacionalidad_Value";

	/** Set Nationality value.
	  * Nationality value
	  */
	public void setEXME_Nacionalidad_Value (String EXME_Nacionalidad_Value);

	/** Get Nationality value.
	  * Nationality value
	  */
	public String getEXME_Nacionalidad_Value();

    /** Column name EXME_Ocupacion_Resp_ID */
    public static final String COLUMNNAME_EXME_Ocupacion_Resp_ID = "EXME_Ocupacion_Resp_ID";

	/** Set Ocupation.
	  * Ocupation
	  */
	public void setEXME_Ocupacion_Resp_ID (int EXME_Ocupacion_Resp_ID);

	/** Get Ocupation.
	  * Ocupation
	  */
	public int getEXME_Ocupacion_Resp_ID();

    /** Column name EXME_Ocupacion_Resp_Value */
    public static final String COLUMNNAME_EXME_Ocupacion_Resp_Value = "EXME_Ocupacion_Resp_Value";

	/** Set Responsible person's ocupation_Value.
	  * Responsible person's ocupation_Value
	  */
	public void setEXME_Ocupacion_Resp_Value (String EXME_Ocupacion_Resp_Value);

	/** Get Responsible person's ocupation_Value.
	  * Responsible person's ocupation_Value
	  */
	public String getEXME_Ocupacion_Resp_Value();

    /** Column name EXME_Otra_Inst */
    public static final String COLUMNNAME_EXME_Otra_Inst = "EXME_Otra_Inst";

	/** Set Other Institution.
	  * Other Institution
	  */
	public void setEXME_Otra_Inst (String EXME_Otra_Inst);

	/** Get Other Institution.
	  * Other Institution
	  */
	public String getEXME_Otra_Inst();

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

    /** Column name EXME_Paciente_TS_ID */
    public static final String COLUMNNAME_EXME_Paciente_TS_ID = "EXME_Paciente_TS_ID";

	/** Set Patient (social work).
	  * Social Work's Patient
	  */
	public void setEXME_Paciente_TS_ID (int EXME_Paciente_TS_ID);

	/** Get Patient (social work).
	  * Social Work's Patient
	  */
	public int getEXME_Paciente_TS_ID();

	public I_EXME_Paciente_TS getEXME_Paciente_TS() throws RuntimeException;

    /** Column name EXME_Parentesco_Resp_ID */
    public static final String COLUMNNAME_EXME_Parentesco_Resp_ID = "EXME_Parentesco_Resp_ID";

	/** Set Responsible Person kinship.
	  * Responsible Person kinship
	  */
	public void setEXME_Parentesco_Resp_ID (int EXME_Parentesco_Resp_ID);

	/** Get Responsible Person kinship.
	  * Responsible Person kinship
	  */
	public int getEXME_Parentesco_Resp_ID();

    /** Column name EXME_Parentesco_Resp_Value */
    public static final String COLUMNNAME_EXME_Parentesco_Resp_Value = "EXME_Parentesco_Resp_Value";

	/** Set Responsible Person kinship_Value	  */
	public void setEXME_Parentesco_Resp_Value (String EXME_Parentesco_Resp_Value);

	/** Get Responsible Person kinship_Value	  */
	public String getEXME_Parentesco_Resp_Value();

    /** Column name EXME_Referencia_ID */
    public static final String COLUMNNAME_EXME_Referencia_ID = "EXME_Referencia_ID";

	/** Set Patient Reference.
	  * Reference to which the patient belongs.
	  */
	public void setEXME_Referencia_ID (int EXME_Referencia_ID);

	/** Get Patient Reference.
	  * Reference to which the patient belongs.
	  */
	public int getEXME_Referencia_ID();

	public I_EXME_Referencia getEXME_Referencia() throws RuntimeException;

    /** Column name EXME_Referencia_Int_ID */
    public static final String COLUMNNAME_EXME_Referencia_Int_ID = "EXME_Referencia_Int_ID";

	/** Set Internal Reference.
	  * Patient's Internal Reference
	  */
	public void setEXME_Referencia_Int_ID (int EXME_Referencia_Int_ID);

	/** Get Internal Reference.
	  * Patient's Internal Reference
	  */
	public int getEXME_Referencia_Int_ID();

	public I_EXME_Referencia_Int getEXME_Referencia_Int() throws RuntimeException;

    /** Column name EXME_Referencia_Int_Value */
    public static final String COLUMNNAME_EXME_Referencia_Int_Value = "EXME_Referencia_Int_Value";

	/** Set Internal Reference_Value.
	  * Patient's Internal Reference_Value
	  */
	public void setEXME_Referencia_Int_Value (String EXME_Referencia_Int_Value);

	/** Get Internal Reference_Value.
	  * Patient's Internal Reference_Value
	  */
	public String getEXME_Referencia_Int_Value();

    /** Column name EXME_Referencia_Value */
    public static final String COLUMNNAME_EXME_Referencia_Value = "EXME_Referencia_Value";

	/** Set Reference_Value	  */
	public void setEXME_Referencia_Value (String EXME_Referencia_Value);

	/** Get Reference_Value	  */
	public String getEXME_Referencia_Value();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_Paciente_TS_ID */
    public static final String COLUMNNAME_I_EXME_Paciente_TS_ID = "I_EXME_Paciente_TS_ID";

	/** Set I_EXME_Paciente_TS_ID	  */
	public void setI_EXME_Paciente_TS_ID (int I_EXME_Paciente_TS_ID);

	/** Get I_EXME_Paciente_TS_ID	  */
	public int getI_EXME_Paciente_TS_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name ListaInstitucion */
    public static final String COLUMNNAME_ListaInstitucion = "ListaInstitucion";

	/** Set ListaInstitucion	  */
	public void setListaInstitucion (String ListaInstitucion);

	/** Get ListaInstitucion	  */
	public String getListaInstitucion();

    /** Column name Madre_Desco */
    public static final String COLUMNNAME_Madre_Desco = "Madre_Desco";

	/** Set Unknown mother.
	  * Unknown mother
	  */
	public void setMadre_Desco (boolean Madre_Desco);

	/** Get Unknown mother.
	  * Unknown mother
	  */
	public boolean isMadre_Desco();

    /** Column name Madre_Vive */
    public static final String COLUMNNAME_Madre_Vive = "Madre_Vive";

	/** Set Mother Lives.
	  * Mother Lives
	  */
	public void setMadre_Vive (boolean Madre_Vive);

	/** Get Mother Lives.
	  * Mother Lives
	  */
	public boolean isMadre_Vive();

    /** Column name Mpio_Dom_Resp_Value */
    public static final String COLUMNNAME_Mpio_Dom_Resp_Value = "Mpio_Dom_Resp_Value";

	/** Set Responsible Person's Hometown.
	  * Responsible Person's Hometown
	  */
	public void setMpio_Dom_Resp_Value (String Mpio_Dom_Resp_Value);

	/** Get Responsible Person's Hometown.
	  * Responsible Person's Hometown
	  */
	public String getMpio_Dom_Resp_Value();

    /** Column name Mpio_Pac_Prov_Value */
    public static final String COLUMNNAME_Mpio_Pac_Prov_Value = "Mpio_Pac_Prov_Value";

	/** Set Patient's provisional municipality.
	  * Patient's provisional municipality
	  */
	public void setMpio_Pac_Prov_Value (String Mpio_Pac_Prov_Value);

	/** Get Patient's provisional municipality.
	  * Patient's provisional municipality
	  */
	public String getMpio_Pac_Prov_Value();

    /** Column name Mpio_Pac_Trab_Value */
    public static final String COLUMNNAME_Mpio_Pac_Trab_Value = "Mpio_Pac_Trab_Value";

	/** Set Patient's work municipality.
	  * Patient's work municipality
	  */
	public void setMpio_Pac_Trab_Value (String Mpio_Pac_Trab_Value);

	/** Get Patient's work municipality.
	  * Patient's work municipality
	  */
	public String getMpio_Pac_Trab_Value();

    /** Column name Mpio_Perm_Value */
    public static final String COLUMNNAME_Mpio_Perm_Value = "Mpio_Perm_Value";

	/** Set Patient's Permanent municipality..
	  * Patient's Permanent municipality.
	  */
	public void setMpio_Perm_Value (String Mpio_Perm_Value);

	/** Get Patient's Permanent municipality..
	  * Patient's Permanent municipality.
	  */
	public String getMpio_Perm_Value();

    /** Column name Mpio_Trab_Resp_Value */
    public static final String COLUMNNAME_Mpio_Trab_Resp_Value = "Mpio_Trab_Resp_Value";

	/** Set Responsible Person's Work Municipality..
	  * Responsible Person's Work Municipality.
	  */
	public void setMpio_Trab_Resp_Value (String Mpio_Trab_Resp_Value);

	/** Get Responsible Person's Work Municipality..
	  * Responsible Person's Work Municipality.
	  */
	public String getMpio_Trab_Resp_Value();

    /** Column name Nombre_Resp */
    public static final String COLUMNNAME_Nombre_Resp = "Nombre_Resp";

	/** Set Name of Responsible	  */
	public void setNombre_Resp (String Nombre_Resp);

	/** Get Name of Responsible	  */
	public String getNombre_Resp();

    /** Column name Nom_Conyuge */
    public static final String COLUMNNAME_Nom_Conyuge = "Nom_Conyuge";

	/** Set Spouse Name.
	  * Spouse Name
	  */
	public void setNom_Conyuge (String Nom_Conyuge);

	/** Get Spouse Name.
	  * Spouse Name
	  */
	public String getNom_Conyuge();

    /** Column name Nom_Madre */
    public static final String COLUMNNAME_Nom_Madre = "Nom_Madre";

	/** Set Mother's Name.
	  * Mother's Name
	  */
	public void setNom_Madre (String Nom_Madre);

	/** Get Mother's Name.
	  * Mother's Name
	  */
	public String getNom_Madre();

    /** Column name Nom_Padre */
    public static final String COLUMNNAME_Nom_Padre = "Nom_Padre";

	/** Set Father's Name.
	  * Father's Name
	  */
	public void setNom_Padre (String Nom_Padre);

	/** Get Father's Name.
	  * Father's Name
	  */
	public String getNom_Padre();

    /** Column name NumHist */
    public static final String COLUMNNAME_NumHist = "NumHist";

	/** Set History Number	  */
	public void setNumHist (String NumHist);

	/** Get History Number	  */
	public String getNumHist();

    /** Column name Padre_Desco */
    public static final String COLUMNNAME_Padre_Desco = "Padre_Desco";

	/** Set Unknown Fatner.
	  * Unknown Father
	  */
	public void setPadre_Desco (boolean Padre_Desco);

	/** Get Unknown Fatner.
	  * Unknown Father
	  */
	public boolean isPadre_Desco();

    /** Column name Padre_Vive */
    public static final String COLUMNNAME_Padre_Vive = "Padre_Vive";

	/** Set Alive Father.
	  * Alive Father
	  */
	public void setPadre_Vive (boolean Padre_Vive);

	/** Get Alive Father.
	  * Alive Father
	  */
	public boolean isPadre_Vive();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Tel_Dom_Resp */
    public static final String COLUMNNAME_Tel_Dom_Resp = "Tel_Dom_Resp";

	/** Set Tel. Dom. Resp..
	  * Tel. Dom. Resp.
	  */
	public void setTel_Dom_Resp (String Tel_Dom_Resp);

	/** Get Tel. Dom. Resp..
	  * Tel. Dom. Resp.
	  */
	public String getTel_Dom_Resp();

    /** Column name Tel_Pac_Prov */
    public static final String COLUMNNAME_Tel_Pac_Prov = "Tel_Pac_Prov";

	/** Set Pat  Phone Prov..
	  * Pat  Phone Prov
	  */
	public void setTel_Pac_Prov (String Tel_Pac_Prov);

	/** Get Pat  Phone Prov..
	  * Pat  Phone Prov
	  */
	public String getTel_Pac_Prov();

    /** Column name Tel_Pac_Trab */
    public static final String COLUMNNAME_Tel_Pac_Trab = "Tel_Pac_Trab";

	/** Set Work Pat  Phone.
	  * Work Pat  Phone
	  */
	public void setTel_Pac_Trab (String Tel_Pac_Trab);

	/** Get Work Pat  Phone.
	  * Work Pat  Phone
	  */
	public String getTel_Pac_Trab();

    /** Column name Tel_Perm */
    public static final String COLUMNNAME_Tel_Perm = "Tel_Perm";

	/** Set Permanent Phone Number	  */
	public void setTel_Perm (String Tel_Perm);

	/** Get Permanent Phone Number	  */
	public String getTel_Perm();

    /** Column name Tel_Trab_Resp */
    public static final String COLUMNNAME_Tel_Trab_Resp = "Tel_Trab_Resp";

	/** Set Resp. Work Phone.
	  * Resp. Work Phone
	  */
	public void setTel_Trab_Resp (String Tel_Trab_Resp);

	/** Get Resp. Work Phone.
	  * Resp. Work Phone
	  */
	public String getTel_Trab_Resp();
}
