/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Tratamientos
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Tratamientos extends PO implements I_EXME_Tratamientos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Tratamientos (Properties ctx, int EXME_Tratamientos_ID, String trxName)
    {
      super (ctx, EXME_Tratamientos_ID, trxName);
      /** if (EXME_Tratamientos_ID == 0)
        {
			setCan_Duracion (0);
			setCode (null);
			setDuration (null);
			setEsq_Precio (null);
			setEXME_Especialidad_ID (0);
			setEXME_Tratamientos_ID (0);
			setName (null);
			setSesion (0);
			setTipo_Trat (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Tratamientos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Tratamientos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Duration Amount.
		@param Can_Duracion Duration Amount	  */
	public void setCan_Duracion (int Can_Duracion)
	{
		set_Value (COLUMNNAME_Can_Duracion, Integer.valueOf(Can_Duracion));
	}

	/** Get Duration Amount.
		@return Duration Amount	  */
	public int getCan_Duracion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Can_Duracion);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (String Code)
	{
		if (Code == null)
			throw new IllegalArgumentException ("Code is mandatory.");
		set_Value (COLUMNNAME_Code, Code);
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public String getCode () 
	{
		return (String)get_Value(COLUMNNAME_Code);
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

	/** Duration AD_Reference_ID=1200485 */
	public static final int DURATION_AD_Reference_ID=1200485;
	/** Years = YR */
	public static final String DURATION_Years = "YR";
	/** Months = MT */
	public static final String DURATION_Months = "MT";
	/** Weeks = WK */
	public static final String DURATION_Weeks = "WK";
	/** Days = DY */
	public static final String DURATION_Days = "DY";
	/** Hours = HR */
	public static final String DURATION_Hours = "HR";
	/** Not Specified = SE */
	public static final String DURATION_NotSpecified = "SE";
	/** Set Duration.
		@param Duration 
		Normal Duration in Duration Unit
	  */
	public void setDuration (String Duration)
	{
		if (Duration == null) throw new IllegalArgumentException ("Duration is mandatory");
		if (Duration.equals("YR") || Duration.equals("MT") || Duration.equals("WK") || Duration.equals("DY") || Duration.equals("HR") || Duration.equals("SE")); else throw new IllegalArgumentException ("Duration Invalid value - " + Duration + " - Reference_ID=1200485 - YR - MT - WK - DY - HR - SE");		set_Value (COLUMNNAME_Duration, Duration);
	}

	/** Get Duration.
		@return Normal Duration in Duration Unit
	  */
	public String getDuration () 
	{
		return (String)get_Value(COLUMNNAME_Duration);
	}

	/** Esq_Precio AD_Reference_ID=1200484 */
	public static final int ESQ_PRECIO_AD_Reference_ID=1200484;
	/** Standard for Treatment = ET */
	public static final String ESQ_PRECIO_StandardForTreatment = "ET";
	/** Standard for Session = ES */
	public static final String ESQ_PRECIO_StandardForSession = "ES";
	/** Real per Treatment = RT */
	public static final String ESQ_PRECIO_RealPerTreatment = "RT";
	/** Real per Session = RS */
	public static final String ESQ_PRECIO_RealPerSession = "RS";
	/** Set Price Scheme.
		@param Esq_Precio Price Scheme	  */
	public void setEsq_Precio (String Esq_Precio)
	{
		if (Esq_Precio == null) throw new IllegalArgumentException ("Esq_Precio is mandatory");
		if (Esq_Precio.equals("ET") || Esq_Precio.equals("ES") || Esq_Precio.equals("RT") || Esq_Precio.equals("RS")); else throw new IllegalArgumentException ("Esq_Precio Invalid value - " + Esq_Precio + " - Reference_ID=1200484 - ET - ES - RT - RS");		set_Value (COLUMNNAME_Esq_Precio, Esq_Precio);
	}

	/** Get Price Scheme.
		@return Price Scheme	  */
	public String getEsq_Precio () 
	{
		return (String)get_Value(COLUMNNAME_Esq_Precio);
	}

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
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

	public I_EXME_PaqBase getEXME_PaqBase() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase.Table_Name);
        I_EXME_PaqBase result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Base Package.
		@param EXME_PaqBase_ID 
		Base Package
	  */
	public void setEXME_PaqBase_ID (int EXME_PaqBase_ID)
	{
		if (EXME_PaqBase_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_ID, Integer.valueOf(EXME_PaqBase_ID));
	}

	/** Get Base Package.
		@return Base Package
	  */
	public int getEXME_PaqBase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatments.
		@param EXME_Tratamientos_ID 
		Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID)
	{
		if (EXME_Tratamientos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Tratamientos_ID, Integer.valueOf(EXME_Tratamientos_ID));
	}

	/** Get Treatments.
		@return Treatments
	  */
	public int getEXME_Tratamientos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Drop Date.
		@param FechaBaja 
		Drop Date
	  */
	public void setFechaBaja (Timestamp FechaBaja)
	{
		set_Value (COLUMNNAME_FechaBaja, FechaBaja);
	}

	/** Get Drop Date.
		@return Drop Date
	  */
	public Timestamp getFechaBaja () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaBaja);
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Medical Reminder.
		@param Rec_Medico Medical Reminder	  */
	public void setRec_Medico (boolean Rec_Medico)
	{
		set_Value (COLUMNNAME_Rec_Medico, Boolean.valueOf(Rec_Medico));
	}

	/** Get Medical Reminder.
		@return Medical Reminder	  */
	public boolean isRec_Medico () 
	{
		Object oo = get_Value(COLUMNNAME_Rec_Medico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Patient Reminder.
		@param Rec_Paciente Patient Reminder	  */
	public void setRec_Paciente (boolean Rec_Paciente)
	{
		set_Value (COLUMNNAME_Rec_Paciente, Boolean.valueOf(Rec_Paciente));
	}

	/** Get Patient Reminder.
		@return Patient Reminder	  */
	public boolean isRec_Paciente () 
	{
		Object oo = get_Value(COLUMNNAME_Rec_Paciente);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Next Treatment.
		@param Ref_Tratamientos_ID Next Treatment	  */
	public void setRef_Tratamientos_ID (int Ref_Tratamientos_ID)
	{
		if (Ref_Tratamientos_ID < 1) 
			set_Value (COLUMNNAME_Ref_Tratamientos_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_Tratamientos_ID, Integer.valueOf(Ref_Tratamientos_ID));
	}

	/** Get Next Treatment.
		@return Next Treatment	  */
	public int getRef_Tratamientos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_Tratamientos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Session.
		@param Sesion 
		Session
	  */
	public void setSesion (int Sesion)
	{
		set_Value (COLUMNNAME_Sesion, Integer.valueOf(Sesion));
	}

	/** Get Session.
		@return Session
	  */
	public int getSesion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sesion);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Tipo_Trat AD_Reference_ID=1200483 */
	public static final int TIPO_TRAT_AD_Reference_ID=1200483;
	/** Outpatient = CE */
	public static final String TIPO_TRAT_Outpatient = "CE";
	/** Ambulatory = AB */
	public static final String TIPO_TRAT_Ambulatory = "AB";
	/** Inpatient = HS */
	public static final String TIPO_TRAT_Inpatient = "HS";
	/** Home = HM */
	public static final String TIPO_TRAT_Home = "HM";
	/** Set Treatment Type.
		@param Tipo_Trat Treatment Type	  */
	public void setTipo_Trat (String Tipo_Trat)
	{
		if (Tipo_Trat == null) throw new IllegalArgumentException ("Tipo_Trat is mandatory");
		if (Tipo_Trat.equals("CE") || Tipo_Trat.equals("AB") || Tipo_Trat.equals("HS") || Tipo_Trat.equals("HM")); else throw new IllegalArgumentException ("Tipo_Trat Invalid value - " + Tipo_Trat + " - Reference_ID=1200483 - CE - AB - HS - HM");		set_Value (COLUMNNAME_Tipo_Trat, Tipo_Trat);
	}

	/** Get Treatment Type.
		@return Treatment Type	  */
	public String getTipo_Trat () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Trat);
	}

	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (boolean Type)
	{
		set_Value (COLUMNNAME_Type, Boolean.valueOf(Type));
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public boolean isType () 
	{
		Object oo = get_Value(COLUMNNAME_Type);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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