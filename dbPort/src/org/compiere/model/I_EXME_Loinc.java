/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Loinc
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Loinc 
{

    /** TableName=EXME_Loinc */
    public static final String Table_Name = "EXME_Loinc";

    /** AD_Table_ID=1200864 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name ACSSYM */
    public static final String COLUMNNAME_ACSSYM = "ACSSYM";

	/** Set ACSSYM	  */
	public void setACSSYM (String ACSSYM);

	/** Get ACSSYM	  */
	public String getACSSYM();

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

    /** Column name AnswerList */
    public static final String COLUMNNAME_AnswerList = "AnswerList";

	/** Set Answer List	  */
	public void setAnswerList (String AnswerList);

	/** Get Answer List	  */
	public String getAnswerList();

    /** Column name Base_Name */
    public static final String COLUMNNAME_Base_Name = "Base_Name";

	/** Set Base Name	  */
	public void setBase_Name (String Base_Name);

	/** Get Base Name	  */
	public String getBase_Name();

    /** Column name CDisc_Common_Tests */
    public static final String COLUMNNAME_CDisc_Common_Tests = "CDisc_Common_Tests";

	/** Set CDisc Common Tests	  */
	public void setCDisc_Common_Tests (String CDisc_Common_Tests);

	/** Get CDisc Common Tests	  */
	public String getCDisc_Common_Tests();

    /** Column name CLASS */
    public static final String COLUMNNAME_CLASS = "CLASS";

	/** Set Class	  */
	public void setCLASS (String CLASS);

	/** Get Class	  */
	public String getCLASS();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name Chng_Type_Comments */
    public static final String COLUMNNAME_Chng_Type_Comments = "Chng_Type_Comments";

	/** Set Change Type Comments	  */
	public void setChng_Type_Comments (String Chng_Type_Comments);

	/** Get Change Type Comments	  */
	public String getChng_Type_Comments();

    /** Column name ClassType */
    public static final String COLUMNNAME_ClassType = "ClassType";

	/** Set Class Type	  */
	public void setClassType (String ClassType);

	/** Get Class Type	  */
	public String getClassType();

    /** Column name Code_Table */
    public static final String COLUMNNAME_Code_Table = "Code_Table";

	/** Set Code Table	  */
	public void setCode_Table (String Code_Table);

	/** Get Code Table	  */
	public String getCode_Table();

    /** Column name Consumer_Name */
    public static final String COLUMNNAME_Consumer_Name = "Consumer_Name";

	/** Set Consumer Name	  */
	public void setConsumer_Name (String Consumer_Name);

	/** Get Consumer Name	  */
	public String getConsumer_Name();

    /** Column name Curated_Range_And_Units */
    public static final String COLUMNNAME_Curated_Range_And_Units = "Curated_Range_And_Units";

	/** Set Curated Range and Units	  */
	public void setCurated_Range_And_Units (String Curated_Range_And_Units);

	/** Get Curated Range and Units	  */
	public String getCurated_Range_And_Units();

    /** Column name DT_LAST_CH */
    public static final String COLUMNNAME_DT_LAST_CH = "DT_LAST_CH";

	/** Set DT_LAST_CH	  */
	public void setDT_LAST_CH (String DT_LAST_CH);

	/** Get DT_LAST_CH	  */
	public String getDT_LAST_CH();

    /** Column name Definition_Description_Help */
    public static final String COLUMNNAME_Definition_Description_Help = "Definition_Description_Help";

	/** Set Definition Description Help	  */
	public void setDefinition_Description_Help (String Definition_Description_Help);

	/** Get Definition Description Help	  */
	public String getDefinition_Description_Help();

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

    /** Column name Document_Section */
    public static final String COLUMNNAME_Document_Section = "Document_Section";

	/** Set Document Section	  */
	public void setDocument_Section (String Document_Section);

	/** Get Document Section	  */
	public String getDocument_Section();

    /** Column name EXACT_CMP_SY */
    public static final String COLUMNNAME_EXACT_CMP_SY = "EXACT_CMP_SY";

	/** Set EXACT_CMP_SY	  */
	public void setEXACT_CMP_SY (String EXACT_CMP_SY);

	/** Get EXACT_CMP_SY	  */
	public String getEXACT_CMP_SY();

    /** Column name EXME_Loinc_ID */
    public static final String COLUMNNAME_EXME_Loinc_ID = "EXME_Loinc_ID";

	/** Set LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID);

	/** Get LOINC Code	  */
	public int getEXME_Loinc_ID();

    /** Column name Example_UCUM_Units */
    public static final String COLUMNNAME_Example_UCUM_Units = "Example_UCUM_Units";

	/** Set Example UCUM Units	  */
	public void setExample_UCUM_Units (String Example_UCUM_Units);

	/** Get Example UCUM Units	  */
	public String getExample_UCUM_Units();

    /** Column name Example_Units */
    public static final String COLUMNNAME_Example_Units = "Example_Units";

	/** Set Example units	  */
	public void setExample_Units (String Example_Units);

	/** Get Example units	  */
	public String getExample_Units();

    /** Column name Exmpl_Answers */
    public static final String COLUMNNAME_Exmpl_Answers = "Exmpl_Answers";

	/** Set Example Answers	  */
	public void setExmpl_Answers (String Exmpl_Answers);

	/** Get Example Answers	  */
	public String getExmpl_Answers();

    /** Column name External_Copyright_Notice */
    public static final String COLUMNNAME_External_Copyright_Notice = "External_Copyright_Notice";

	/** Set External Copyright Notice	  */
	public void setExternal_Copyright_Notice (String External_Copyright_Notice);

	/** Get External Copyright Notice	  */
	public String getExternal_Copyright_Notice();

    /** Column name Final */
    public static final String COLUMNNAME_Final = "Final";

	/** Set Final	  */
	public void setFinal (String Final);

	/** Get Final	  */
	public String getFinal();

    /** Column name Formula */
    public static final String COLUMNNAME_Formula = "Formula";

	/** Set Formule.
	  * Substance Formule
	  */
	public void setFormula (String Formula);

	/** Get Formule.
	  * Substance Formule
	  */
	public String getFormula();

    /** Column name HL7_Field_Subfield_ID */
    public static final String COLUMNNAME_HL7_Field_Subfield_ID = "HL7_Field_Subfield_ID";

	/** Set HL7 Field Subfield	  */
	public void setHL7_Field_Subfield_ID (String HL7_Field_Subfield_ID);

	/** Get HL7 Field Subfield	  */
	public String getHL7_Field_Subfield_ID();

    /** Column name HL7_V2_DataType */
    public static final String COLUMNNAME_HL7_V2_DataType = "HL7_V2_DataType";

	/** Set HL7 V2 DataType.
	  * HL7 V2 DataType
	  */
	public void setHL7_V2_DataType (String HL7_V2_DataType);

	/** Get HL7 V2 DataType.
	  * HL7 V2 DataType
	  */
	public String getHL7_V2_DataType();

    /** Column name HL7_V3_DataType */
    public static final String COLUMNNAME_HL7_V3_DataType = "HL7_V3_DataType";

	/** Set HL7 V3 DataType.
	  * HL7 V3 DataType
	  */
	public void setHL7_V3_DataType (String HL7_V3_DataType);

	/** Get HL7 V3 DataType.
	  * HL7 V3 DataType
	  */
	public String getHL7_V3_DataType();

    /** Column name INPC_Percentage */
    public static final String COLUMNNAME_INPC_Percentage = "INPC_Percentage";

	/** Set INPC Percentage	  */
	public void setINPC_Percentage (String INPC_Percentage);

	/** Get INPC Percentage	  */
	public String getINPC_Percentage();

    /** Column name IPCC_Units */
    public static final String COLUMNNAME_IPCC_Units = "IPCC_Units";

	/** Set IPCC Units	  */
	public void setIPCC_Units (String IPCC_Units);

	/** Get IPCC Units	  */
	public String getIPCC_Units();

    /** Column name Long_Common_Name */
    public static final String COLUMNNAME_Long_Common_Name = "Long_Common_Name";

	/** Set Long Common Name.
	  * Long Common Name
	  */
	public void setLong_Common_Name (String Long_Common_Name);

	/** Get Long Common Name.
	  * Long Common Name
	  */
	public String getLong_Common_Name();

    /** Column name MAP_TO */
    public static final String COLUMNNAME_MAP_TO = "MAP_TO";

	/** Set MAP_TO	  */
	public void setMAP_TO (String MAP_TO);

	/** Get MAP_TO	  */
	public String getMAP_TO();

    /** Column name MOLAR_MASS */
    public static final String COLUMNNAME_MOLAR_MASS = "MOLAR_MASS";

	/** Set MOLAR_MASS	  */
	public void setMOLAR_MASS (String MOLAR_MASS);

	/** Get MOLAR_MASS	  */
	public String getMOLAR_MASS();

    /** Column name Method_TYP */
    public static final String COLUMNNAME_Method_TYP = "Method_TYP";

	/** Set Method Typ	  */
	public void setMethod_TYP (String Method_TYP);

	/** Get Method Typ	  */
	public String getMethod_TYP();

    /** Column name NAACCR_ID */
    public static final String COLUMNNAME_NAACCR_ID = "NAACCR_ID";

	/** Set NAACCR_ID	  */
	public void setNAACCR_ID (String NAACCR_ID);

	/** Get NAACCR_ID	  */
	public String getNAACCR_ID();

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

    /** Column name ORDER_OBS */
    public static final String COLUMNNAME_ORDER_OBS = "ORDER_OBS";

	/** Set ORDER_OBS	  */
	public void setORDER_OBS (String ORDER_OBS);

	/** Get ORDER_OBS	  */
	public String getORDER_OBS();

    /** Column name PanelElements */
    public static final String COLUMNNAME_PanelElements = "PanelElements";

	/** Set Panel Elements	  */
	public void setPanelElements (String PanelElements);

	/** Get Panel Elements	  */
	public String getPanelElements();

    /** Column name Property */
    public static final String COLUMNNAME_Property = "Property";

	/** Set Property	  */
	public void setProperty (String Property);

	/** Get Property	  */
	public String getProperty();

    /** Column name Reference */
    public static final String COLUMNNAME_Reference = "Reference";

	/** Set Reference.
	  * Reference for this record
	  */
	public void setReference (String Reference);

	/** Get Reference.
	  * Reference for this record
	  */
	public String getReference();

    /** Column name Relat_Nms */
    public static final String COLUMNNAME_Relat_Nms = "Relat_Nms";

	/** Set Retative Names	  */
	public void setRelat_Nms (String Relat_Nms);

	/** Get Retative Names	  */
	public String getRelat_Nms();

    /** Column name RelatedNames2 */
    public static final String COLUMNNAME_RelatedNames2 = "RelatedNames2";

	/** Set Related Names (2)	  */
	public void setRelatedNames2 (String RelatedNames2);

	/** Get Related Names (2)	  */
	public String getRelatedNames2();

    /** Column name Scale_Type */
    public static final String COLUMNNAME_Scale_Type = "Scale_Type";

	/** Set Scale Type	  */
	public void setScale_Type (String Scale_Type);

	/** Get Scale Type	  */
	public String getScale_Type();

    /** Column name Scope */
    public static final String COLUMNNAME_Scope = "Scope";

	/** Set Scope	  */
	public void setScope (String Scope);

	/** Get Scope	  */
	public String getScope();

    /** Column name SetRoot */
    public static final String COLUMNNAME_SetRoot = "SetRoot";

	/** Set Set Root	  */
	public void setSetRoot (String SetRoot);

	/** Get Set Root	  */
	public String getSetRoot();

    /** Column name ShortName */
    public static final String COLUMNNAME_ShortName = "ShortName";

	/** Set Short Name	  */
	public void setShortName (String ShortName);

	/** Get Short Name	  */
	public String getShortName();

    /** Column name Source */
    public static final String COLUMNNAME_Source = "Source";

	/** Set Source.
	  * Record the source of the vaccine given
	  */
	public void setSource (String Source);

	/** Get Source.
	  * Record the source of the vaccine given
	  */
	public String getSource();

    /** Column name Species */
    public static final String COLUMNNAME_Species = "Species";

	/** Set Species	  */
	public void setSpecies (String Species);

	/** Get Species	  */
	public String getSpecies();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

    /** Column name Submitted_Units */
    public static final String COLUMNNAME_Submitted_Units = "Submitted_Units";

	/** Set Submitted Units	  */
	public void setSubmitted_Units (String Submitted_Units);

	/** Get Submitted Units	  */
	public String getSubmitted_Units();

    /** Column name Survey_Quest_Src */
    public static final String COLUMNNAME_Survey_Quest_Src = "Survey_Quest_Src";

	/** Set Survey Quest Src	  */
	public void setSurvey_Quest_Src (String Survey_Quest_Src);

	/** Get Survey Quest Src	  */
	public String getSurvey_Quest_Src();

    /** Column name Survey_Quest_Text */
    public static final String COLUMNNAME_Survey_Quest_Text = "Survey_Quest_Text";

	/** Set Survey Quest Text	  */
	public void setSurvey_Quest_Text (String Survey_Quest_Text);

	/** Get Survey Quest Text	  */
	public String getSurvey_Quest_Text();

    /** Column name System */
    public static final String COLUMNNAME_System = "System";

	/** Set System	  */
	public void setSystem (String System);

	/** Get System	  */
	public String getSystem();

    /** Column name Time_Aspct */
    public static final String COLUMNNAME_Time_Aspct = "Time_Aspct";

	/** Set Time Aspect	  */
	public void setTime_Aspct (String Time_Aspct);

	/** Get Time Aspect	  */
	public String getTime_Aspct();

    /** Column name UnitsRequired */
    public static final String COLUMNNAME_UnitsRequired = "UnitsRequired";

	/** Set Units Required	  */
	public void setUnitsRequired (String UnitsRequired);

	/** Get Units Required	  */
	public String getUnitsRequired();

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
