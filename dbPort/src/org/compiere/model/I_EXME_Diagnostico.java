/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Diagnostico
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha)
 */
public interface I_EXME_Diagnostico 
{

    /** TableName=EXME_Diagnostico */
    public static final String Table_Name = "EXME_Diagnostico";

    /** AD_Table_ID=1000008 */
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

    /** Column name CodGrd */
    public static final String COLUMNNAME_CodGrd = "CodGrd";

	/** Set Code GRD.
	  * Code GRD
	  */
	public void setCodGrd (String CodGrd);

	/** Get Code GRD.
	  * Code GRD
	  */
	public String getCodGrd();

    /** Column name CodInegi */
    public static final String COLUMNNAME_CodInegi = "CodInegi";

	/** Set Code INEGI.
	  * Code INEGI
	  */
	public void setCodInegi (String CodInegi);

	/** Get Code INEGI.
	  * Code INEGI
	  */
	public String getCodInegi();

    /** Column name CodOms */
    public static final String COLUMNNAME_CodOms = "CodOms";

	/** Set World Organization Health Code.
	  * World Organization Health Code
	  */
	public void setCodOms (String CodOms);

	/** Get World Organization Health Code.
	  * World Organization Health Code
	  */
	public String getCodOms();

    /** Column name CodSnomed */
    public static final String COLUMNNAME_CodSnomed = "CodSnomed";

	/** Set Snomed Code.
	  * Snomed Code
	  */
	public void setCodSnomed (String CodSnomed);

	/** Get Snomed Code.
	  * Snomed Code
	  */
	public String getCodSnomed();

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

    /** Column name DiseaseType */
    public static final String COLUMNNAME_DiseaseType = "DiseaseType";

	/** Set Diseases Type.
	  * Diseases Type
	  */
	public void setDiseaseType (String DiseaseType);

	/** Get Diseases Type.
	  * Diseases Type
	  */
	public String getDiseaseType();

    /** Column name EsEpidemiologico */
    public static final String COLUMNNAME_EsEpidemiologico = "EsEpidemiologico";

	/** Set Is Epidemiological	  */
	public void setEsEpidemiologico (boolean EsEpidemiologico);

	/** Get Is Epidemiological	  */
	public boolean isEsEpidemiologico();

    /** Column name EXME_DiagnosticoHdr_ID */
    public static final String COLUMNNAME_EXME_DiagnosticoHdr_ID = "EXME_DiagnosticoHdr_ID";

	/** Set ICD.
	  * International Classification of Diseases
	  */
	public void setEXME_DiagnosticoHdr_ID (int EXME_DiagnosticoHdr_ID);

	/** Get ICD.
	  * International Classification of Diseases
	  */
	public int getEXME_DiagnosticoHdr_ID();

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

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

    /** Column name Stroke */
    public static final String COLUMNNAME_Stroke = "Stroke";

	/** Set Stroke	  */
	public void setStroke (String Stroke);

	/** Get Stroke	  */
	public String getStroke();

    /** Column name Valid_From */
    public static final String COLUMNNAME_Valid_From = "Valid_From";

	/** Set Valid from	  */
	public void setValid_From (Timestamp Valid_From);

	/** Get Valid from	  */
	public Timestamp getValid_From();

    /** Column name Valid_To */
    public static final String COLUMNNAME_Valid_To = "Valid_To";

	/** Set Valid to	  */
	public void setValid_To (Timestamp Valid_To);

	/** Get Valid to	  */
	public Timestamp getValid_To();

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
