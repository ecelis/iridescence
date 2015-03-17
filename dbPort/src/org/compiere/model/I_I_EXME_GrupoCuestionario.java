/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_GrupoCuestionario
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_I_EXME_GrupoCuestionario 
{

    /** TableName=I_EXME_GrupoCuestionario */
    public static final String Table_Name = "I_EXME_GrupoCuestionario";

    /** AD_Table_ID=1201287 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name DataType */
    public static final String COLUMNNAME_DataType = "DataType";

	/** Set Data Type.
	  * Type of data
	  */
	public void setDataType (String DataType);

	/** Get Data Type.
	  * Type of data
	  */
	public String getDataType();

    /** Column name EXME_CuestionarioDt_ID */
    public static final String COLUMNNAME_EXME_CuestionarioDt_ID = "EXME_CuestionarioDt_ID";

	/** Set DT Questinnaire.
	  * DT Questionnaire
	  */
	public void setEXME_CuestionarioDt_ID (int EXME_CuestionarioDt_ID);

	/** Get DT Questinnaire.
	  * DT Questionnaire
	  */
	public int getEXME_CuestionarioDt_ID();

	public I_EXME_CuestionarioDt getEXME_CuestionarioDt() throws RuntimeException;

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

	public I_EXME_Cuestionario getEXME_Cuestionario() throws RuntimeException;

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

    /** Column name EXME_GrupoCuestionarioDet_ID */
    public static final String COLUMNNAME_EXME_GrupoCuestionarioDet_ID = "EXME_GrupoCuestionarioDet_ID";

	/** Set Form Group Detail.
	  * Form Group Detail
	  */
	public void setEXME_GrupoCuestionarioDet_ID (int EXME_GrupoCuestionarioDet_ID);

	/** Get Form Group Detail.
	  * Form Group Detail
	  */
	public int getEXME_GrupoCuestionarioDet_ID();

	public I_EXME_GrupoCuestionarioDet getEXME_GrupoCuestionarioDet() throws RuntimeException;

    /** Column name EXME_GrupoCuestionario_ID */
    public static final String COLUMNNAME_EXME_GrupoCuestionario_ID = "EXME_GrupoCuestionario_ID";

	/** Set Form Group.
	  * Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID);

	/** Get Form Group.
	  * Form Group
	  */
	public int getEXME_GrupoCuestionario_ID();

	public I_EXME_GrupoCuestionario getEXME_GrupoCuestionario() throws RuntimeException;

    /** Column name EXME_Pregunta_ID */
    public static final String COLUMNNAME_EXME_Pregunta_ID = "EXME_Pregunta_ID";

	/** Set Question.
	  * Question
	  */
	public void setEXME_Pregunta_ID (int EXME_Pregunta_ID);

	/** Get Question.
	  * Question
	  */
	public int getEXME_Pregunta_ID();

	public I_EXME_Pregunta getEXME_Pregunta() throws RuntimeException;

    /** Column name EXME_Pregunta_Lista_ID */
    public static final String COLUMNNAME_EXME_Pregunta_Lista_ID = "EXME_Pregunta_Lista_ID";

	/** Set Fixed Answer.
	  * Fixed answer for the question in the clinic questionnaire
	  */
	public void setEXME_Pregunta_Lista_ID (int EXME_Pregunta_Lista_ID);

	/** Get Fixed Answer.
	  * Fixed answer for the question in the clinic questionnaire
	  */
	public int getEXME_Pregunta_Lista_ID();

	public I_EXME_Pregunta_Lista getEXME_Pregunta_Lista() throws RuntimeException;

    /** Column name EXME_TipoPregunta_ID */
    public static final String COLUMNNAME_EXME_TipoPregunta_ID = "EXME_TipoPregunta_ID";

	/** Set Type of Question.
	  * Type of Question
	  */
	public void setEXME_TipoPregunta_ID (int EXME_TipoPregunta_ID);

	/** Get Type of Question.
	  * Type of Question
	  */
	public int getEXME_TipoPregunta_ID();

	public I_EXME_TipoPregunta getEXME_TipoPregunta() throws RuntimeException;

    /** Column name FormDescription */
    public static final String COLUMNNAME_FormDescription = "FormDescription";

	/** Set Form Description	  */
	public void setFormDescription (String FormDescription);

	/** Get Form Description	  */
	public String getFormDescription();

    /** Column name FormDetSeq */
    public static final String COLUMNNAME_FormDetSeq = "FormDetSeq";

	/** Set Form Detail Sequence.
	  * Form Detail Sequence
	  */
	public void setFormDetSeq (int FormDetSeq);

	/** Get Form Detail Sequence.
	  * Form Detail Sequence
	  */
	public int getFormDetSeq();

    /** Column name FormGroupDetSeq */
    public static final String COLUMNNAME_FormGroupDetSeq = "FormGroupDetSeq";

	/** Set Form Group Detail Sequence.
	  * Form Group Detail Sequence
	  */
	public void setFormGroupDetSeq (int FormGroupDetSeq);

	/** Get Form Group Detail Sequence.
	  * Form Group Detail Sequence
	  */
	public int getFormGroupDetSeq();

    /** Column name FormGroupName */
    public static final String COLUMNNAME_FormGroupName = "FormGroupName";

	/** Set Form Group Name	  */
	public void setFormGroupName (String FormGroupName);

	/** Get Form Group Name	  */
	public String getFormGroupName();

    /** Column name FormName */
    public static final String COLUMNNAME_FormName = "FormName";

	/** Set Form Name	  */
	public void setFormName (String FormName);

	/** Get Form Name	  */
	public String getFormName();

    /** Column name FormValue */
    public static final String COLUMNNAME_FormValue = "FormValue";

	/** Set Form Value	  */
	public void setFormValue (String FormValue);

	/** Get Form Value	  */
	public String getFormValue();

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

    /** Column name I_EXME_GrupoCuestionario_ID */
    public static final String COLUMNNAME_I_EXME_GrupoCuestionario_ID = "I_EXME_GrupoCuestionario_ID";

	/** Set Form Group Import.
	  * Form Group Import
	  */
	public void setI_EXME_GrupoCuestionario_ID (int I_EXME_GrupoCuestionario_ID);

	/** Get Form Group Import.
	  * Form Group Import
	  */
	public int getI_EXME_GrupoCuestionario_ID();

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

    /** Column name isMultiSys */
    public static final String COLUMNNAME_isMultiSys = "isMultiSys";

	/** Set Multi System.
	  * Indicates whether or not is multi system
	  */
	public void setisMultiSys (boolean isMultiSys);

	/** Get Multi System.
	  * Indicates whether or not is multi system
	  */
	public boolean isMultiSys();

    /** Column name IsSelected */
    public static final String COLUMNNAME_IsSelected = "IsSelected";

	/** Set Selected	  */
	public void setIsSelected (boolean IsSelected);

	/** Get Selected	  */
	public boolean isSelected();

    /** Column name ListDesc */
    public static final String COLUMNNAME_ListDesc = "ListDesc";

	/** Set List Value Description.
	  * List Value Description
	  */
	public void setListDesc (String ListDesc);

	/** Get List Value Description.
	  * List Value Description
	  */
	public String getListDesc();

    /** Column name ListName */
    public static final String COLUMNNAME_ListName = "ListName";

	/** Set List Value Name.
	  * List Value Name
	  */
	public void setListName (String ListName);

	/** Get List Value Name.
	  * List Value Name
	  */
	public String getListName();

    /** Column name ListValue */
    public static final String COLUMNNAME_ListValue = "ListValue";

	/** Set List Value Key.
	  * List Value Key
	  */
	public void setListValue (String ListValue);

	/** Get List Value Key.
	  * List Value Key
	  */
	public String getListValue();

    /** Column name OnlyAnswer */
    public static final String COLUMNNAME_OnlyAnswer = "OnlyAnswer";

	/** Set Only Answer	  */
	public void setOnlyAnswer (boolean OnlyAnswer);

	/** Get Only Answer	  */
	public boolean isOnlyAnswer();

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

    /** Column name QuestionDesc */
    public static final String COLUMNNAME_QuestionDesc = "QuestionDesc";

	/** Set Question Description.
	  * Question Description
	  */
	public void setQuestionDesc (String QuestionDesc);

	/** Get Question Description.
	  * Question Description
	  */
	public String getQuestionDesc();

    /** Column name QuestionEsCore */
    public static final String COLUMNNAME_QuestionEsCore = "QuestionEsCore";

	/** Set Public Domain.
	  * Public Domain
	  */
	public void setQuestionEsCore (boolean QuestionEsCore);

	/** Get Public Domain.
	  * Public Domain
	  */
	public boolean isQuestionEsCore();

    /** Column name QuestionMandatory */
    public static final String COLUMNNAME_QuestionMandatory = "QuestionMandatory";

	/** Set Mandatory Question.
	  * Mandatory Question
	  */
	public void setQuestionMandatory (boolean QuestionMandatory);

	/** Get Mandatory Question.
	  * Mandatory Question
	  */
	public boolean isQuestionMandatory();

    /** Column name QuestionName */
    public static final String COLUMNNAME_QuestionName = "QuestionName";

	/** Set Question Name.
	  * Question Name
	  */
	public void setQuestionName (String QuestionName);

	/** Get Question Name.
	  * Question Name
	  */
	public String getQuestionName();

    /** Column name QuestionSpecDesc */
    public static final String COLUMNNAME_QuestionSpecDesc = "QuestionSpecDesc";

	/** Set Question Specialty Description.
	  * Question Specialty Description
	  */
	public void setQuestionSpecDesc (String QuestionSpecDesc);

	/** Get Question Specialty Description.
	  * Question Specialty Description
	  */
	public String getQuestionSpecDesc();

    /** Column name QuestionSpecName */
    public static final String COLUMNNAME_QuestionSpecName = "QuestionSpecName";

	/** Set Question Specialty Name.
	  * Question Specialty Name
	  */
	public void setQuestionSpecName (String QuestionSpecName);

	/** Get Question Specialty Name.
	  * Question Specialty Name
	  */
	public String getQuestionSpecName();

    /** Column name QuestionSpecValue */
    public static final String COLUMNNAME_QuestionSpecValue = "QuestionSpecValue";

	/** Set Question Specialty Key.
	  * Question Specialty Key
	  */
	public void setQuestionSpecValue (String QuestionSpecValue);

	/** Get Question Specialty Key.
	  * Question Specialty Key
	  */
	public String getQuestionSpecValue();

    /** Column name QuestionValue */
    public static final String COLUMNNAME_QuestionValue = "QuestionValue";

	/** Set Question Key.
	  * Question Key
	  */
	public void setQuestionValue (String QuestionValue);

	/** Get Question Key.
	  * Question Key
	  */
	public String getQuestionValue();

    /** Column name QuestTypeDesc */
    public static final String COLUMNNAME_QuestTypeDesc = "QuestTypeDesc";

	/** Set Question Type Description.
	  * Question Type Description
	  */
	public void setQuestTypeDesc (String QuestTypeDesc);

	/** Get Question Type Description.
	  * Question Type Description
	  */
	public String getQuestTypeDesc();

    /** Column name QuestTypeName */
    public static final String COLUMNNAME_QuestTypeName = "QuestTypeName";

	/** Set Question Type Name.
	  * Question Type Name
	  */
	public void setQuestTypeName (String QuestTypeName);

	/** Get Question Type Name.
	  * Question Type Name
	  */
	public String getQuestTypeName();

    /** Column name QuestTypeValue */
    public static final String COLUMNNAME_QuestTypeValue = "QuestTypeValue";

	/** Set Question Type Key.
	  * Question Type Key
	  */
	public void setQuestTypeValue (String QuestTypeValue);

	/** Get Question Type Key.
	  * Question Type Key
	  */
	public String getQuestTypeValue();

    /** Column name Score */
    public static final String COLUMNNAME_Score = "Score";

	/** Set Score	  */
	public void setScore (int Score);

	/** Get Score	  */
	public int getScore();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name SingleSysEsp */
    public static final String COLUMNNAME_SingleSysEsp = "SingleSysEsp";

	/** Set  Single System Specialities.
	  * Specialities for Single System
	  */
	public void setSingleSysEsp (String SingleSysEsp);

	/** Get  Single System Specialities.
	  * Specialities for Single System
	  */
	public String getSingleSysEsp();

    /** Column name SpecialtyDesc */
    public static final String COLUMNNAME_SpecialtyDesc = "SpecialtyDesc";

	/** Set Specialty Description.
	  * Specialty Description
	  */
	public void setSpecialtyDesc (String SpecialtyDesc);

	/** Get Specialty Description.
	  * Specialty Description
	  */
	public String getSpecialtyDesc();

    /** Column name SpecialtyName */
    public static final String COLUMNNAME_SpecialtyName = "SpecialtyName";

	/** Set Specialty Name.
	  * Specialty Name
	  */
	public void setSpecialtyName (String SpecialtyName);

	/** Get Specialty Name.
	  * Specialty Name
	  */
	public String getSpecialtyName();

    /** Column name SpecialtyValue */
    public static final String COLUMNNAME_SpecialtyValue = "SpecialtyValue";

	/** Set Specialty Key.
	  * Specialty Key
	  */
	public void setSpecialtyValue (String SpecialtyValue);

	/** Get Specialty Key.
	  * Specialty Key
	  */
	public String getSpecialtyValue();

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
}
