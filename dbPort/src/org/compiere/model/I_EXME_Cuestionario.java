/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Cuestionario
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Cuestionario 
{

    /** TableName=EXME_Cuestionario */
    public static final String Table_Name = "EXME_Cuestionario";

    /** AD_Table_ID=1000009 */
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

    /** Column name EsDefault */
    public static final String COLUMNNAME_EsDefault = "EsDefault";

	/** Set EsDefault	  */
	public void setEsDefault (boolean EsDefault);

	/** Get EsDefault	  */
	public boolean isEsDefault();

    /** Column name EXME_Cuestionario_ID */
    public static final String COLUMNNAME_EXME_Cuestionario_ID = "EXME_Cuestionario_ID";

	/** Set Questionnaire.
	  * Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID);

	/** Get Questionnaire.
	  * Questionnaire
	  */
	public int getEXME_Cuestionario_ID();

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

    /** Column name EXME_Reporte_ID */
    public static final String COLUMNNAME_EXME_Reporte_ID = "EXME_Reporte_ID";

	/** Set Report	  */
	public void setEXME_Reporte_ID (int EXME_Reporte_ID);

	/** Get Report	  */
	public int getEXME_Reporte_ID();

	public I_EXME_Reporte getEXME_Reporte() throws RuntimeException;

    /** Column name ISGLOBAL */
    public static final String COLUMNNAME_ISGLOBAL = "ISGLOBAL";

	/** Set ISGLOBAL	  */
	public void setISGLOBAL (boolean ISGLOBAL);

	/** Get ISGLOBAL	  */
	public boolean isGLOBAL();

    /** Column name IsRequired */
    public static final String COLUMNNAME_IsRequired = "IsRequired";

	/** Set Required.
	  * Required
	  */
	public void setIsRequired (boolean IsRequired);

	/** Get Required.
	  * Required
	  */
	public boolean isRequired();

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

    /** Column name Nivel_Acc */
    public static final String COLUMNNAME_Nivel_Acc = "Nivel_Acc";

	/** Set Level Access	  */
	public void setNivel_Acc (String Nivel_Acc);

	/** Get Level Access	  */
	public String getNivel_Acc();

    /** Column name OnlyAnswer */
    public static final String COLUMNNAME_OnlyAnswer = "OnlyAnswer";

	/** Set Only Answer	  */
	public void setOnlyAnswer (boolean OnlyAnswer);

	/** Get Only Answer	  */
	public boolean isOnlyAnswer();

    /** Column name ProcessDef */
    public static final String COLUMNNAME_ProcessDef = "ProcessDef";

	/** Set Default Process.
	  * Default Process
	  */
	public void setProcessDef (String ProcessDef);

	/** Get Default Process.
	  * Default Process
	  */
	public String getProcessDef();

    /** Column name ProcessReq */
    public static final String COLUMNNAME_ProcessReq = "ProcessReq";

	/** Set Required Process.
	  * Required Process
	  */
	public void setProcessReq (String ProcessReq);

	/** Get Required Process.
	  * Required Process
	  */
	public String getProcessReq();

    /** Column name Score */
    public static final String COLUMNNAME_Score = "Score";

	/** Set Score	  */
	public void setScore (int Score);

	/** Get Score	  */
	public int getScore();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();

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
