/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Paciente_TS
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Paciente_TS 
{

    /** TableName=EXME_Paciente_TS */
    public static final String Table_Name = "EXME_Paciente_TS";

    /** AD_Table_ID=1200028 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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
	public void setCopyAddr (String CopyAddr);

	/** Get Copy Address to Responsible.
	  * Copy Address to Responsible
	  */
	public String getCopyAddr();

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

    /** Column name Edad_Madre */
    public static final String COLUMNNAME_Edad_Madre = "Edad_Madre";

	/** Set Age of mother.
	  * Age of mother
	  */
	public void setEdad_Madre (int Edad_Madre);

	/** Get Age of mother.
	  * Age of mother
	  */
	public int getEdad_Madre();

    /** Column name Edad_Padre */
    public static final String COLUMNNAME_Edad_Padre = "Edad_Padre";

	/** Set Age of father.
	  * Age of father
	  */
	public void setEdad_Padre (int Edad_Padre);

	/** Get Age of father.
	  * Age of father
	  */
	public int getEdad_Padre();

    /** Column name EdoCivil_Madre */
    public static final String COLUMNNAME_EdoCivil_Madre = "EdoCivil_Madre";

	/** Set Marital status of mother.
	  * Marital status of mother
	  */
	public void setEdoCivil_Madre (String EdoCivil_Madre);

	/** Get Marital status of mother.
	  * Marital status of mother
	  */
	public String getEdoCivil_Madre();

    /** Column name EdoCivil_Padre */
    public static final String COLUMNNAME_EdoCivil_Padre = "EdoCivil_Padre";

	/** Set Marital status of father.
	  * Marital status of father
	  */
	public void setEdoCivil_Padre (String EdoCivil_Padre);

	/** Get Marital status of father.
	  * Marital status of father
	  */
	public String getEdoCivil_Padre();

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

    /** Column name EXME_Escolaridad_Madre_ID */
    public static final String COLUMNNAME_EXME_Escolaridad_Madre_ID = "EXME_Escolaridad_Madre_ID";

	/** Set Schooling of Mother.
	  * Schooling of Mother
	  */
	public void setEXME_Escolaridad_Madre_ID (int EXME_Escolaridad_Madre_ID);

	/** Get Schooling of Mother.
	  * Schooling of Mother
	  */
	public int getEXME_Escolaridad_Madre_ID();

    /** Column name EXME_Escolaridad_Padre_ID */
    public static final String COLUMNNAME_EXME_Escolaridad_Padre_ID = "EXME_Escolaridad_Padre_ID";

	/** Set Father's Schooling.
	  * Father's Schooling
	  */
	public void setEXME_Escolaridad_Padre_ID (int EXME_Escolaridad_Padre_ID);

	/** Get Father's Schooling.
	  * Father's Schooling
	  */
	public int getEXME_Escolaridad_Padre_ID();

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

    /** Column name EXME_Medico_Aper_ID */
    public static final String COLUMNNAME_EXME_Medico_Aper_ID = "EXME_Medico_Aper_ID";

	/** Set Doctor	  */
	public void setEXME_Medico_Aper_ID (int EXME_Medico_Aper_ID);

	/** Get Doctor	  */
	public int getEXME_Medico_Aper_ID();

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

    /** Column name Institucion */
    public static final String COLUMNNAME_Institucion = "Institucion";

	/** Set Institution	  */
	public void setInstitucion (String Institucion);

	/** Get Institution	  */
	public String getInstitucion();

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
