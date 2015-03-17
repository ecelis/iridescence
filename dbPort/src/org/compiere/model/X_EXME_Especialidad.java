/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_Especialidad
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha) - $Id$ */
public class X_EXME_Especialidad extends PO implements I_EXME_Especialidad, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Especialidad (Properties ctx, int EXME_Especialidad_ID, String trxName)
    {
      super (ctx, EXME_Especialidad_ID, trxName);
      /** if (EXME_Especialidad_ID == 0)
        {
			setEXME_Especialidad_ID (0);
			setIsMed (false);
			setName (null);
			setSpecType (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Especialidad (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Especialidad[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Color getAD_Color() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Color.Table_Name);
        I_AD_Color result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Color)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Color_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set System Color.
		@param AD_Color_ID 
		Color for backgrounds or indicators
	  */
	public void setAD_Color_ID (int AD_Color_ID)
	{
		if (AD_Color_ID < 1) 
			set_Value (COLUMNNAME_AD_Color_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Color_ID, Integer.valueOf(AD_Color_ID));
	}

	/** Get System Color.
		@return Color for backgrounds or indicators
	  */
	public int getAD_Color_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Color_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Consult Interval.
		@param IntervaloConsulta Consult Interval	  */
	public void setIntervaloConsulta (BigDecimal IntervaloConsulta)
	{
		set_Value (COLUMNNAME_IntervaloConsulta, IntervaloConsulta);
	}

	/** Get Consult Interval.
		@return Consult Interval	  */
	public BigDecimal getIntervaloConsulta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_IntervaloConsulta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Nurse.
		@param IsEnf Is Nurse	  */
	public void setIsEnf (boolean IsEnf)
	{
		set_Value (COLUMNNAME_IsEnf, Boolean.valueOf(IsEnf));
	}

	/** Get Is Nurse.
		@return Is Nurse	  */
	public boolean isEnf () 
	{
		Object oo = get_Value(COLUMNNAME_IsEnf);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Medical Specialty.
		@param IsMed Is Medical Specialty	  */
	public void setIsMed (boolean IsMed)
	{
		set_Value (COLUMNNAME_IsMed, Boolean.valueOf(IsMed));
	}

	/** Get Is Medical Specialty.
		@return Is Medical Specialty	  */
	public boolean isMed () 
	{
		Object oo = get_Value(COLUMNNAME_IsMed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Specialty Reference.
		@param Ref_EXME_Especialidad_ID Specialty Reference	  */
	public void setRef_EXME_Especialidad_ID (int Ref_EXME_Especialidad_ID)
	{
		if (Ref_EXME_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_Ref_EXME_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_EXME_Especialidad_ID, Integer.valueOf(Ref_EXME_Especialidad_ID));
	}

	/** Get Specialty Reference.
		@return Specialty Reference	  */
	public int getRef_EXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** SpecType AD_Reference_ID=1200449 */
	public static final int SPECTYPE_AD_Reference_ID=1200449;
	/** Medical Specialty = M */
	public static final String SPECTYPE_MedicalSpecialty = "M";
	/** Nursing Specialty = N */
	public static final String SPECTYPE_NursingSpecialty = "N";
	/** Specialties for Technicians = T */
	public static final String SPECTYPE_SpecialtiesForTechnicians = "T";
	/** Institutional Specialty = I */
	public static final String SPECTYPE_InstitutionalSpecialty = "I";
	/** Other = O */
	public static final String SPECTYPE_Other = "O";
	/** Office Visit = V */
	public static final String SPECTYPE_OfficeVisit = "V";
	/** Super Specialty = S */
	public static final String SPECTYPE_SuperSpecialty = "S";
	/** Set Specialty Type.
		@param SpecType 
		The Specialty Type
	  */
	public void setSpecType (String SpecType)
	{
		if (SpecType == null) throw new IllegalArgumentException ("SpecType is mandatory");
		if (SpecType.equals("M") || SpecType.equals("N") || SpecType.equals("T") || SpecType.equals("I") || SpecType.equals("O") || SpecType.equals("V") || SpecType.equals("S")); else throw new IllegalArgumentException ("SpecType Invalid value - " + SpecType + " - Reference_ID=1200449 - M - N - T - I - O - V - S");		set_Value (COLUMNNAME_SpecType, SpecType);
	}

	/** Get Specialty Type.
		@return The Specialty Type
	  */
	public String getSpecType () 
	{
		return (String)get_Value(COLUMNNAME_SpecType);
	}

	/** Set Waiting Time.
		@param TiempoEspera 
		Waiting Time (in months)
	  */
	public void setTiempoEspera (BigDecimal TiempoEspera)
	{
		set_Value (COLUMNNAME_TiempoEspera, TiempoEspera);
	}

	/** Get Waiting Time.
		@return Waiting Time (in months)
	  */
	public BigDecimal getTiempoEspera () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TiempoEspera);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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