/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Diagnostico
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha) - $Id$ */
public class X_EXME_Diagnostico extends PO implements I_EXME_Diagnostico, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Diagnostico (Properties ctx, int EXME_Diagnostico_ID, String trxName)
    {
      super (ctx, EXME_Diagnostico_ID, trxName);
      /** if (EXME_Diagnostico_ID == 0)
        {
			setCodGrd (null);
			setCodOms (null);
			setCodSnomed (null);
			setEXME_DiagnosticoHdr_ID (0);
			setEXME_Diagnostico_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Diagnostico (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Diagnostico[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Code GRD.
		@param CodGrd 
		Code GRD
	  */
	public void setCodGrd (String CodGrd)
	{
		if (CodGrd == null)
			throw new IllegalArgumentException ("CodGrd is mandatory.");
		set_Value (COLUMNNAME_CodGrd, CodGrd);
	}

	/** Get Code GRD.
		@return Code GRD
	  */
	public String getCodGrd () 
	{
		return (String)get_Value(COLUMNNAME_CodGrd);
	}

	/** Set Code INEGI.
		@param CodInegi 
		Code INEGI
	  */
	public void setCodInegi (String CodInegi)
	{
		set_Value (COLUMNNAME_CodInegi, CodInegi);
	}

	/** Get Code INEGI.
		@return Code INEGI
	  */
	public String getCodInegi () 
	{
		return (String)get_Value(COLUMNNAME_CodInegi);
	}

	/** Set World Organization Health Code.
		@param CodOms 
		World Organization Health Code
	  */
	public void setCodOms (String CodOms)
	{
		if (CodOms == null)
			throw new IllegalArgumentException ("CodOms is mandatory.");
		set_Value (COLUMNNAME_CodOms, CodOms);
	}

	/** Get World Organization Health Code.
		@return World Organization Health Code
	  */
	public String getCodOms () 
	{
		return (String)get_Value(COLUMNNAME_CodOms);
	}

	/** Set Snomed Code.
		@param CodSnomed 
		Snomed Code
	  */
	public void setCodSnomed (String CodSnomed)
	{
		if (CodSnomed == null)
			throw new IllegalArgumentException ("CodSnomed is mandatory.");
		set_Value (COLUMNNAME_CodSnomed, CodSnomed);
	}

	/** Get Snomed Code.
		@return Snomed Code
	  */
	public String getCodSnomed () 
	{
		return (String)get_Value(COLUMNNAME_CodSnomed);
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

	/** DiseaseType AD_Reference_ID=1200399 */
	public static final int DISEASETYPE_AD_Reference_ID=1200399;
	/** Chronic = CR */
	public static final String DISEASETYPE_Chronic = "CR";
	/** Acute = AG */
	public static final String DISEASETYPE_Acute = "AG";
	/** Both = AM */
	public static final String DISEASETYPE_Both = "AM";
	/** Normal = NO */
	public static final String DISEASETYPE_Normal = "NO";
	/** Set Diseases Type.
		@param DiseaseType 
		Diseases Type
	  */
	public void setDiseaseType (String DiseaseType)
	{

		if (DiseaseType == null || DiseaseType.equals("CR") || DiseaseType.equals("AG") || DiseaseType.equals("AM") || DiseaseType.equals("NO")); else throw new IllegalArgumentException ("DiseaseType Invalid value - " + DiseaseType + " - Reference_ID=1200399 - CR - AG - AM - NO");		set_Value (COLUMNNAME_DiseaseType, DiseaseType);
	}

	/** Get Diseases Type.
		@return Diseases Type
	  */
	public String getDiseaseType () 
	{
		return (String)get_Value(COLUMNNAME_DiseaseType);
	}

	/** Set Is Epidemiological.
		@param EsEpidemiologico Is Epidemiological	  */
	public void setEsEpidemiologico (boolean EsEpidemiologico)
	{
		set_Value (COLUMNNAME_EsEpidemiologico, Boolean.valueOf(EsEpidemiologico));
	}

	/** Get Is Epidemiological.
		@return Is Epidemiological	  */
	public boolean isEsEpidemiologico () 
	{
		Object oo = get_Value(COLUMNNAME_EsEpidemiologico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ICD.
		@param EXME_DiagnosticoHdr_ID 
		International Classification of Diseases
	  */
	public void setEXME_DiagnosticoHdr_ID (int EXME_DiagnosticoHdr_ID)
	{
		if (EXME_DiagnosticoHdr_ID < 1)
			 throw new IllegalArgumentException ("EXME_DiagnosticoHdr_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DiagnosticoHdr_ID, Integer.valueOf(EXME_DiagnosticoHdr_ID));
	}

	/** Get ICD.
		@return International Classification of Diseases
	  */
	public int getEXME_DiagnosticoHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosticoHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Stroke AD_Reference_ID=1200474 */
	public static final int STROKE_AD_Reference_ID=1200474;
	/** Ischemic Stroke = IS */
	public static final String STROKE_IschemicStroke = "IS";
	/** Hemorragic Stroke = HS */
	public static final String STROKE_HemorragicStroke = "HS";
	/** Venous Thomboembolic = VT */
	public static final String STROKE_VenousThomboembolic = "VT";
	/** Set Stroke.
		@param Stroke Stroke	  */
	public void setStroke (String Stroke)
	{

		if (Stroke == null || Stroke.equals("IS") || Stroke.equals("HS") || Stroke.equals("VT")); else throw new IllegalArgumentException ("Stroke Invalid value - " + Stroke + " - Reference_ID=1200474 - IS - HS - VT");		set_Value (COLUMNNAME_Stroke, Stroke);
	}

	/** Get Stroke.
		@return Stroke	  */
	public String getStroke () 
	{
		return (String)get_Value(COLUMNNAME_Stroke);
	}

	/** Set Valid from.
		@param Valid_From Valid from	  */
	public void setValid_From (Timestamp Valid_From)
	{
		set_Value (COLUMNNAME_Valid_From, Valid_From);
	}

	/** Get Valid from.
		@return Valid from	  */
	public Timestamp getValid_From () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Valid_From);
	}

	/** Set Valid to.
		@param Valid_To Valid to	  */
	public void setValid_To (Timestamp Valid_To)
	{
		set_Value (COLUMNNAME_Valid_To, Valid_To);
	}

	/** Get Valid to.
		@return Valid to	  */
	public Timestamp getValid_To () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Valid_To);
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