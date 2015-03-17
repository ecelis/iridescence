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

/** Generated Model for EXME_ProcedPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProcedPac extends PO implements I_EXME_ProcedPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProcedPac (Properties ctx, int EXME_ProcedPac_ID, String trxName)
    {
      super (ctx, EXME_ProcedPac_ID, trxName);
      /** if (EXME_ProcedPac_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_Medico_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_ProcedPac_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setHojaConsentimiento (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProcedPac (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProcedPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Agree.
		@param Acepto 
		Agree
	  */
	public void setAcepto (boolean Acepto)
	{
		set_Value (COLUMNNAME_Acepto, Boolean.valueOf(Acepto));
	}

	/** Get Agree.
		@return Agree
	  */
	public boolean isAcepto () 
	{
		Object oo = get_Value(COLUMNNAME_Acepto);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Age.
		@param Edad 
		Age
	  */
	public void setEdad (String Edad)
	{
		set_Value (COLUMNNAME_Edad, Edad);
	}

	/** Get Age.
		@return Age
	  */
	public String getEdad () 
	{
		return (String)get_Value(COLUMNNAME_Edad);
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
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
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
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

	/** Set Second Diagnostic.
		@param EXME_Diagnostico2_ID 
		Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID)
	{
		if (EXME_Diagnostico2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, Integer.valueOf(EXME_Diagnostico2_ID));
	}

	/** Get Second Diagnostic.
		@return Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Third Diagnostic.
		@param EXME_Diagnostico3_ID 
		Third Diagnostic
	  */
	public void setEXME_Diagnostico3_ID (int EXME_Diagnostico3_ID)
	{
		if (EXME_Diagnostico3_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, Integer.valueOf(EXME_Diagnostico3_ID));
	}

	/** Get Third Diagnostic.
		@return Third Diagnostic
	  */
	public int getEXME_Diagnostico3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Kinship.
		@param EXME_Parentesco_ID 
		Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID)
	{
		if (EXME_Parentesco_ID < 1) 
			set_Value (COLUMNNAME_EXME_Parentesco_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Parentesco_ID, Integer.valueOf(EXME_Parentesco_ID));
	}

	/** Get Kinship.
		@return Kinship
	  */
	public int getEXME_Parentesco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Parentesco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Procedures applied to the patient.
		@param EXME_ProcedPac_ID Procedures applied to the patient	  */
	public void setEXME_ProcedPac_ID (int EXME_ProcedPac_ID)
	{
		if (EXME_ProcedPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProcedPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProcedPac_ID, Integer.valueOf(EXME_ProcedPac_ID));
	}

	/** Get Procedures applied to the patient.
		@return Procedures applied to the patient	  */
	public int getEXME_ProcedPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcedPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Consent form.
		@param HojaConsentimiento Consent form	  */
	public void setHojaConsentimiento (boolean HojaConsentimiento)
	{
		set_Value (COLUMNNAME_HojaConsentimiento, Boolean.valueOf(HojaConsentimiento));
	}

	/** Get Consent form.
		@return Consent form	  */
	public boolean isHojaConsentimiento () 
	{
		Object oo = get_Value(COLUMNNAME_HojaConsentimiento);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name of person receiving the information.
		@param NombreRecibeInfo Name of person receiving the information	  */
	public void setNombreRecibeInfo (String NombreRecibeInfo)
	{
		set_Value (COLUMNNAME_NombreRecibeInfo, NombreRecibeInfo);
	}

	/** Get Name of person receiving the information.
		@return Name of person receiving the information	  */
	public String getNombreRecibeInfo () 
	{
		return (String)get_Value(COLUMNNAME_NombreRecibeInfo);
	}

	/** TipoRelacion AD_Reference_ID=1200229 */
	public static final int TIPORELACION_AD_Reference_ID=1200229;
	/** Patient = P */
	public static final String TIPORELACION_Patient = "P";
	/** Legal representative = L */
	public static final String TIPORELACION_LegalRepresentative = "L";
	/** Family = F */
	public static final String TIPORELACION_Family = "F";
	/** Set Type of relationship with the patient.
		@param TipoRelacion Type of relationship with the patient	  */
	public void setTipoRelacion (String TipoRelacion)
	{

		if (TipoRelacion == null || TipoRelacion.equals("P") || TipoRelacion.equals("L") || TipoRelacion.equals("F")); else throw new IllegalArgumentException ("TipoRelacion Invalid value - " + TipoRelacion + " - Reference_ID=1200229 - P - L - F");		set_Value (COLUMNNAME_TipoRelacion, TipoRelacion);
	}

	/** Get Type of relationship with the patient.
		@return Type of relationship with the patient	  */
	public String getTipoRelacion () 
	{
		return (String)get_Value(COLUMNNAME_TipoRelacion);
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