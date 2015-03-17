/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Loinc
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Loinc extends PO implements I_EXME_Loinc, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Loinc (Properties ctx, int EXME_Loinc_ID, String trxName)
    {
      super (ctx, EXME_Loinc_ID, trxName);
      /** if (EXME_Loinc_ID == 0)
        {
			setEXME_Loinc_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Loinc (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_EXME_Loinc[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ACSSYM.
		@param ACSSYM ACSSYM	  */
	public void setACSSYM (String ACSSYM)
	{
		set_Value (COLUMNNAME_ACSSYM, ACSSYM);
	}

	/** Get ACSSYM.
		@return ACSSYM	  */
	public String getACSSYM () 
	{
		return (String)get_Value(COLUMNNAME_ACSSYM);
	}

	/** Set Answer List.
		@param AnswerList Answer List	  */
	public void setAnswerList (String AnswerList)
	{
		set_Value (COLUMNNAME_AnswerList, AnswerList);
	}

	/** Get Answer List.
		@return Answer List	  */
	public String getAnswerList () 
	{
		return (String)get_Value(COLUMNNAME_AnswerList);
	}

	/** Set Base Name.
		@param Base_Name Base Name	  */
	public void setBase_Name (String Base_Name)
	{
		set_Value (COLUMNNAME_Base_Name, Base_Name);
	}

	/** Get Base Name.
		@return Base Name	  */
	public String getBase_Name () 
	{
		return (String)get_Value(COLUMNNAME_Base_Name);
	}

	/** Set CDisc Common Tests.
		@param CDisc_Common_Tests CDisc Common Tests	  */
	public void setCDisc_Common_Tests (String CDisc_Common_Tests)
	{
		set_Value (COLUMNNAME_CDisc_Common_Tests, CDisc_Common_Tests);
	}

	/** Get CDisc Common Tests.
		@return CDisc Common Tests	  */
	public String getCDisc_Common_Tests () 
	{
		return (String)get_Value(COLUMNNAME_CDisc_Common_Tests);
	}

	/** Set Class.
		@param CLASS Class	  */
	public void setCLASS (String CLASS)
	{
		set_Value (COLUMNNAME_CLASS, CLASS);
	}

	/** Get Class.
		@return Class	  */
	public String getCLASS () 
	{
		return (String)get_Value(COLUMNNAME_CLASS);
	}

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Change Type Comments.
		@param Chng_Type_Comments Change Type Comments	  */
	public void setChng_Type_Comments (String Chng_Type_Comments)
	{
		set_Value (COLUMNNAME_Chng_Type_Comments, Chng_Type_Comments);
	}

	/** Get Change Type Comments.
		@return Change Type Comments	  */
	public String getChng_Type_Comments () 
	{
		return (String)get_Value(COLUMNNAME_Chng_Type_Comments);
	}

	/** Set Class Type.
		@param ClassType Class Type	  */
	public void setClassType (String ClassType)
	{
		set_Value (COLUMNNAME_ClassType, ClassType);
	}

	/** Get Class Type.
		@return Class Type	  */
	public String getClassType () 
	{
		return (String)get_Value(COLUMNNAME_ClassType);
	}

	/** Set Code Table.
		@param Code_Table Code Table	  */
	public void setCode_Table (String Code_Table)
	{
		set_Value (COLUMNNAME_Code_Table, Code_Table);
	}

	/** Get Code Table.
		@return Code Table	  */
	public String getCode_Table () 
	{
		return (String)get_Value(COLUMNNAME_Code_Table);
	}

	/** Set Consumer Name.
		@param Consumer_Name Consumer Name	  */
	public void setConsumer_Name (String Consumer_Name)
	{
		set_Value (COLUMNNAME_Consumer_Name, Consumer_Name);
	}

	/** Get Consumer Name.
		@return Consumer Name	  */
	public String getConsumer_Name () 
	{
		return (String)get_Value(COLUMNNAME_Consumer_Name);
	}

	/** Set Curated Range and Units.
		@param Curated_Range_And_Units Curated Range and Units	  */
	public void setCurated_Range_And_Units (String Curated_Range_And_Units)
	{
		set_Value (COLUMNNAME_Curated_Range_And_Units, Curated_Range_And_Units);
	}

	/** Get Curated Range and Units.
		@return Curated Range and Units	  */
	public String getCurated_Range_And_Units () 
	{
		return (String)get_Value(COLUMNNAME_Curated_Range_And_Units);
	}

	/** Set DT_LAST_CH.
		@param DT_LAST_CH DT_LAST_CH	  */
	public void setDT_LAST_CH (String DT_LAST_CH)
	{
		set_Value (COLUMNNAME_DT_LAST_CH, DT_LAST_CH);
	}

	/** Get DT_LAST_CH.
		@return DT_LAST_CH	  */
	public String getDT_LAST_CH () 
	{
		return (String)get_Value(COLUMNNAME_DT_LAST_CH);
	}

	/** Set Definition Description Help.
		@param Definition_Description_Help Definition Description Help	  */
	public void setDefinition_Description_Help (String Definition_Description_Help)
	{
		set_Value (COLUMNNAME_Definition_Description_Help, Definition_Description_Help);
	}

	/** Get Definition Description Help.
		@return Definition Description Help	  */
	public String getDefinition_Description_Help () 
	{
		return (String)get_Value(COLUMNNAME_Definition_Description_Help);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Document Section.
		@param Document_Section Document Section	  */
	public void setDocument_Section (String Document_Section)
	{
		set_Value (COLUMNNAME_Document_Section, Document_Section);
	}

	/** Get Document Section.
		@return Document Section	  */
	public String getDocument_Section () 
	{
		return (String)get_Value(COLUMNNAME_Document_Section);
	}

	/** Set EXACT_CMP_SY.
		@param EXACT_CMP_SY EXACT_CMP_SY	  */
	public void setEXACT_CMP_SY (String EXACT_CMP_SY)
	{
		set_Value (COLUMNNAME_EXACT_CMP_SY, EXACT_CMP_SY);
	}

	/** Get EXACT_CMP_SY.
		@return EXACT_CMP_SY	  */
	public String getEXACT_CMP_SY () 
	{
		return (String)get_Value(COLUMNNAME_EXACT_CMP_SY);
	}

	/** Set LOINC Code.
		@param EXME_Loinc_ID LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID)
	{
		if (EXME_Loinc_ID < 1)
			 throw new IllegalArgumentException ("EXME_Loinc_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Loinc_ID, Integer.valueOf(EXME_Loinc_ID));
	}

	/** Get LOINC Code.
		@return LOINC Code	  */
	public int getEXME_Loinc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Loinc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Example UCUM Units.
		@param Example_UCUM_Units Example UCUM Units	  */
	public void setExample_UCUM_Units (String Example_UCUM_Units)
	{
		set_Value (COLUMNNAME_Example_UCUM_Units, Example_UCUM_Units);
	}

	/** Get Example UCUM Units.
		@return Example UCUM Units	  */
	public String getExample_UCUM_Units () 
	{
		return (String)get_Value(COLUMNNAME_Example_UCUM_Units);
	}

	/** Set Example units.
		@param Example_Units Example units	  */
	public void setExample_Units (String Example_Units)
	{
		set_Value (COLUMNNAME_Example_Units, Example_Units);
	}

	/** Get Example units.
		@return Example units	  */
	public String getExample_Units () 
	{
		return (String)get_Value(COLUMNNAME_Example_Units);
	}

	/** Set Example Answers.
		@param Exmpl_Answers Example Answers	  */
	public void setExmpl_Answers (String Exmpl_Answers)
	{
		set_Value (COLUMNNAME_Exmpl_Answers, Exmpl_Answers);
	}

	/** Get Example Answers.
		@return Example Answers	  */
	public String getExmpl_Answers () 
	{
		return (String)get_Value(COLUMNNAME_Exmpl_Answers);
	}

	/** Set External Copyright Notice.
		@param External_Copyright_Notice External Copyright Notice	  */
	public void setExternal_Copyright_Notice (String External_Copyright_Notice)
	{
		set_Value (COLUMNNAME_External_Copyright_Notice, External_Copyright_Notice);
	}

	/** Get External Copyright Notice.
		@return External Copyright Notice	  */
	public String getExternal_Copyright_Notice () 
	{
		return (String)get_Value(COLUMNNAME_External_Copyright_Notice);
	}

	/** Set Final.
		@param Final Final	  */
	public void setFinal (String Final)
	{
		set_Value (COLUMNNAME_Final, Final);
	}

	/** Get Final.
		@return Final	  */
	public String getFinal () 
	{
		return (String)get_Value(COLUMNNAME_Final);
	}

	/** Set Formule.
		@param Formula 
		Substance Formule
	  */
	public void setFormula (String Formula)
	{
		set_Value (COLUMNNAME_Formula, Formula);
	}

	/** Get Formule.
		@return Substance Formule
	  */
	public String getFormula () 
	{
		return (String)get_Value(COLUMNNAME_Formula);
	}

	/** Set HL7 Field Subfield.
		@param HL7_Field_Subfield_ID HL7 Field Subfield	  */
	public void setHL7_Field_Subfield_ID (String HL7_Field_Subfield_ID)
	{
		set_Value (COLUMNNAME_HL7_Field_Subfield_ID, HL7_Field_Subfield_ID);
	}

	/** Get HL7 Field Subfield.
		@return HL7 Field Subfield	  */
	public String getHL7_Field_Subfield_ID () 
	{
		return (String)get_Value(COLUMNNAME_HL7_Field_Subfield_ID);
	}

	/** Set HL7 V2 DataType.
		@param HL7_V2_DataType 
		HL7 V2 DataType
	  */
	public void setHL7_V2_DataType (String HL7_V2_DataType)
	{
		set_Value (COLUMNNAME_HL7_V2_DataType, HL7_V2_DataType);
	}

	/** Get HL7 V2 DataType.
		@return HL7 V2 DataType
	  */
	public String getHL7_V2_DataType () 
	{
		return (String)get_Value(COLUMNNAME_HL7_V2_DataType);
	}

	/** Set HL7 V3 DataType.
		@param HL7_V3_DataType 
		HL7 V3 DataType
	  */
	public void setHL7_V3_DataType (String HL7_V3_DataType)
	{
		set_Value (COLUMNNAME_HL7_V3_DataType, HL7_V3_DataType);
	}

	/** Get HL7 V3 DataType.
		@return HL7 V3 DataType
	  */
	public String getHL7_V3_DataType () 
	{
		return (String)get_Value(COLUMNNAME_HL7_V3_DataType);
	}

	/** Set INPC Percentage.
		@param INPC_Percentage INPC Percentage	  */
	public void setINPC_Percentage (String INPC_Percentage)
	{
		set_Value (COLUMNNAME_INPC_Percentage, INPC_Percentage);
	}

	/** Get INPC Percentage.
		@return INPC Percentage	  */
	public String getINPC_Percentage () 
	{
		return (String)get_Value(COLUMNNAME_INPC_Percentage);
	}

	/** Set IPCC Units.
		@param IPCC_Units IPCC Units	  */
	public void setIPCC_Units (String IPCC_Units)
	{
		set_Value (COLUMNNAME_IPCC_Units, IPCC_Units);
	}

	/** Get IPCC Units.
		@return IPCC Units	  */
	public String getIPCC_Units () 
	{
		return (String)get_Value(COLUMNNAME_IPCC_Units);
	}

	/** Set Long Common Name.
		@param Long_Common_Name 
		Long Common Name
	  */
	public void setLong_Common_Name (String Long_Common_Name)
	{
		set_Value (COLUMNNAME_Long_Common_Name, Long_Common_Name);
	}

	/** Get Long Common Name.
		@return Long Common Name
	  */
	public String getLong_Common_Name () 
	{
		return (String)get_Value(COLUMNNAME_Long_Common_Name);
	}

	/** Set MAP_TO.
		@param MAP_TO MAP_TO	  */
	public void setMAP_TO (String MAP_TO)
	{
		set_Value (COLUMNNAME_MAP_TO, MAP_TO);
	}

	/** Get MAP_TO.
		@return MAP_TO	  */
	public String getMAP_TO () 
	{
		return (String)get_Value(COLUMNNAME_MAP_TO);
	}

	/** Set MOLAR_MASS.
		@param MOLAR_MASS MOLAR_MASS	  */
	public void setMOLAR_MASS (String MOLAR_MASS)
	{
		set_Value (COLUMNNAME_MOLAR_MASS, MOLAR_MASS);
	}

	/** Get MOLAR_MASS.
		@return MOLAR_MASS	  */
	public String getMOLAR_MASS () 
	{
		return (String)get_Value(COLUMNNAME_MOLAR_MASS);
	}

	/** Set Method Typ.
		@param Method_TYP Method Typ	  */
	public void setMethod_TYP (String Method_TYP)
	{
		set_Value (COLUMNNAME_Method_TYP, Method_TYP);
	}

	/** Get Method Typ.
		@return Method Typ	  */
	public String getMethod_TYP () 
	{
		return (String)get_Value(COLUMNNAME_Method_TYP);
	}

	/** Set NAACCR_ID.
		@param NAACCR_ID NAACCR_ID	  */
	public void setNAACCR_ID (String NAACCR_ID)
	{
		set_Value (COLUMNNAME_NAACCR_ID, NAACCR_ID);
	}

	/** Get NAACCR_ID.
		@return NAACCR_ID	  */
	public String getNAACCR_ID () 
	{
		return (String)get_Value(COLUMNNAME_NAACCR_ID);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set ORDER_OBS.
		@param ORDER_OBS ORDER_OBS	  */
	public void setORDER_OBS (String ORDER_OBS)
	{
		set_Value (COLUMNNAME_ORDER_OBS, ORDER_OBS);
	}

	/** Get ORDER_OBS.
		@return ORDER_OBS	  */
	public String getORDER_OBS () 
	{
		return (String)get_Value(COLUMNNAME_ORDER_OBS);
	}

	/** Set Panel Elements.
		@param PanelElements Panel Elements	  */
	public void setPanelElements (String PanelElements)
	{
		set_Value (COLUMNNAME_PanelElements, PanelElements);
	}

	/** Get Panel Elements.
		@return Panel Elements	  */
	public String getPanelElements () 
	{
		return (String)get_Value(COLUMNNAME_PanelElements);
	}

	/** Set Property.
		@param Property Property	  */
	public void setProperty (String Property)
	{
		set_Value (COLUMNNAME_Property, Property);
	}

	/** Get Property.
		@return Property	  */
	public String getProperty () 
	{
		return (String)get_Value(COLUMNNAME_Property);
	}

	/** Set Reference.
		@param Reference 
		Reference for this record
	  */
	public void setReference (String Reference)
	{
		set_Value (COLUMNNAME_Reference, Reference);
	}

	/** Get Reference.
		@return Reference for this record
	  */
	public String getReference () 
	{
		return (String)get_Value(COLUMNNAME_Reference);
	}

	/** Set Retative Names.
		@param Relat_Nms Retative Names	  */
	public void setRelat_Nms (String Relat_Nms)
	{
		set_Value (COLUMNNAME_Relat_Nms, Relat_Nms);
	}

	/** Get Retative Names.
		@return Retative Names	  */
	public String getRelat_Nms () 
	{
		return (String)get_Value(COLUMNNAME_Relat_Nms);
	}

	/** Set Related Names (2).
		@param RelatedNames2 Related Names (2)	  */
	public void setRelatedNames2 (String RelatedNames2)
	{
		set_Value (COLUMNNAME_RelatedNames2, RelatedNames2);
	}

	/** Get Related Names (2).
		@return Related Names (2)	  */
	public String getRelatedNames2 () 
	{
		return (String)get_Value(COLUMNNAME_RelatedNames2);
	}

	/** Set Scale Type.
		@param Scale_Type Scale Type	  */
	public void setScale_Type (String Scale_Type)
	{
		set_Value (COLUMNNAME_Scale_Type, Scale_Type);
	}

	/** Get Scale Type.
		@return Scale Type	  */
	public String getScale_Type () 
	{
		return (String)get_Value(COLUMNNAME_Scale_Type);
	}

	/** Set Scope.
		@param Scope Scope	  */
	public void setScope (String Scope)
	{
		set_Value (COLUMNNAME_Scope, Scope);
	}

	/** Get Scope.
		@return Scope	  */
	public String getScope () 
	{
		return (String)get_Value(COLUMNNAME_Scope);
	}

	/** Set Set Root.
		@param SetRoot Set Root	  */
	public void setSetRoot (String SetRoot)
	{
		set_Value (COLUMNNAME_SetRoot, SetRoot);
	}

	/** Get Set Root.
		@return Set Root	  */
	public String getSetRoot () 
	{
		return (String)get_Value(COLUMNNAME_SetRoot);
	}

	/** Set Short Name.
		@param ShortName Short Name	  */
	public void setShortName (String ShortName)
	{
		set_Value (COLUMNNAME_ShortName, ShortName);
	}

	/** Get Short Name.
		@return Short Name	  */
	public String getShortName () 
	{
		return (String)get_Value(COLUMNNAME_ShortName);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getShortName());
    }

	/** Set Source.
		@param Source 
		Record the source of the vaccine given
	  */
	public void setSource (String Source)
	{
		set_Value (COLUMNNAME_Source, Source);
	}

	/** Get Source.
		@return Record the source of the vaccine given
	  */
	public String getSource () 
	{
		return (String)get_Value(COLUMNNAME_Source);
	}

	/** Set Species.
		@param Species Species	  */
	public void setSpecies (String Species)
	{
		set_Value (COLUMNNAME_Species, Species);
	}

	/** Get Species.
		@return Species	  */
	public String getSpecies () 
	{
		return (String)get_Value(COLUMNNAME_Species);
	}

	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Submitted Units.
		@param Submitted_Units Submitted Units	  */
	public void setSubmitted_Units (String Submitted_Units)
	{
		set_Value (COLUMNNAME_Submitted_Units, Submitted_Units);
	}

	/** Get Submitted Units.
		@return Submitted Units	  */
	public String getSubmitted_Units () 
	{
		return (String)get_Value(COLUMNNAME_Submitted_Units);
	}

	/** Set Survey Quest Src.
		@param Survey_Quest_Src Survey Quest Src	  */
	public void setSurvey_Quest_Src (String Survey_Quest_Src)
	{
		set_Value (COLUMNNAME_Survey_Quest_Src, Survey_Quest_Src);
	}

	/** Get Survey Quest Src.
		@return Survey Quest Src	  */
	public String getSurvey_Quest_Src () 
	{
		return (String)get_Value(COLUMNNAME_Survey_Quest_Src);
	}

	/** Set Survey Quest Text.
		@param Survey_Quest_Text Survey Quest Text	  */
	public void setSurvey_Quest_Text (String Survey_Quest_Text)
	{
		set_Value (COLUMNNAME_Survey_Quest_Text, Survey_Quest_Text);
	}

	/** Get Survey Quest Text.
		@return Survey Quest Text	  */
	public String getSurvey_Quest_Text () 
	{
		return (String)get_Value(COLUMNNAME_Survey_Quest_Text);
	}

	/** Set System.
		@param System System	  */
	public void setSystem (String System)
	{
		set_Value (COLUMNNAME_System, System);
	}

	/** Get System.
		@return System	  */
	public String getSystem () 
	{
		return (String)get_Value(COLUMNNAME_System);
	}

	/** Set Time Aspect.
		@param Time_Aspct Time Aspect	  */
	public void setTime_Aspct (String Time_Aspct)
	{
		set_Value (COLUMNNAME_Time_Aspct, Time_Aspct);
	}

	/** Get Time Aspect.
		@return Time Aspect	  */
	public String getTime_Aspct () 
	{
		return (String)get_Value(COLUMNNAME_Time_Aspct);
	}

	/** Set Units Required.
		@param UnitsRequired Units Required	  */
	public void setUnitsRequired (String UnitsRequired)
	{
		set_Value (COLUMNNAME_UnitsRequired, UnitsRequired);
	}

	/** Get Units Required.
		@return Units Required	  */
	public String getUnitsRequired () 
	{
		return (String)get_Value(COLUMNNAME_UnitsRequired);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}