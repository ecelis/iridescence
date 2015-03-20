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

/** Generated Model for EXME_VistaElegibilidad
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_VistaElegibilidad extends PO implements I_EXME_VistaElegibilidad, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_VistaElegibilidad (Properties ctx, int EXME_VistaElegibilidad_ID, String trxName)
    {
      super (ctx, EXME_VistaElegibilidad_ID, trxName);
      /** if (EXME_VistaElegibilidad_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setEXME_VistaElegibilidad_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_VistaElegibilidad (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_VistaElegibilidad[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create Beneficiary.
		@param CreateBeneficiary Create Beneficiary	  */
	public void setCreateBeneficiary (String CreateBeneficiary)
	{
		set_Value (COLUMNNAME_CreateBeneficiary, CreateBeneficiary);
	}

	/** Get Create Beneficiary.
		@return Create Beneficiary	  */
	public String getCreateBeneficiary () 
	{
		return (String)get_Value(COLUMNNAME_CreateBeneficiary);
	}

	/** Set View Report.
		@param CreateReport 
		View Report of patient data
	  */
	public void setCreateReport (String CreateReport)
	{
		set_Value (COLUMNNAME_CreateReport, CreateReport);
	}

	/** Get View Report.
		@return View Report of patient data
	  */
	public String getCreateReport () 
	{
		return (String)get_Value(COLUMNNAME_CreateReport);
	}

	/** Set Days.
		@param Dias Days	  */
	public void setDias (int Dias)
	{
		set_Value (COLUMNNAME_Dias, Integer.valueOf(Dias));
	}

	/** Get Days.
		@return Days	  */
	public int getDias () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Dias);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Drug Eligibility Application.
		@param DrugEligibilityApplication Drug Eligibility Application	  */
	public void setDrugEligibilityApplication (String DrugEligibilityApplication)
	{
		set_Value (COLUMNNAME_DrugEligibilityApplication, DrugEligibilityApplication);
	}

	/** Get Drug Eligibility Application.
		@return Drug Eligibility Application	  */
	public String getDrugEligibilityApplication () 
	{
		return (String)get_Value(COLUMNNAME_DrugEligibilityApplication);
	}

	/** Set Eligibility Application.
		@param EligibilityApplication Eligibility Application	  */
	public void setEligibilityApplication (String EligibilityApplication)
	{
		set_Value (COLUMNNAME_EligibilityApplication, EligibilityApplication);
	}

	/** Get Eligibility Application.
		@return Eligibility Application	  */
	public String getEligibilityApplication () 
	{
		return (String)get_Value(COLUMNNAME_EligibilityApplication);
	}

	/** Set Entity_IdCode.
		@param Entity_IdCode Entity_IdCode	  */
	public void setEntity_IdCode (String Entity_IdCode)
	{
		set_Value (COLUMNNAME_Entity_IdCode, Entity_IdCode);
	}

	/** Get Entity_IdCode.
		@return Entity_IdCode	  */
	public String getEntity_IdCode () 
	{
		return (String)get_Value(COLUMNNAME_Entity_IdCode);
	}

	/** Set Entity_TypeQual.
		@param Entity_TypeQual Entity_TypeQual	  */
	public void setEntity_TypeQual (String Entity_TypeQual)
	{
		set_Value (COLUMNNAME_Entity_TypeQual, Entity_TypeQual);
	}

	/** Get Entity_TypeQual.
		@return Entity_TypeQual	  */
	public String getEntity_TypeQual () 
	{
		return (String)get_Value(COLUMNNAME_Entity_TypeQual);
	}

	public I_EXME_BeneficiosH getEXME_BeneficiosH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_BeneficiosH.Table_Name);
        I_EXME_BeneficiosH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_BeneficiosH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_BeneficiosH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_BeneficiosH_ID.
		@param EXME_BeneficiosH_ID EXME_BeneficiosH_ID	  */
	public void setEXME_BeneficiosH_ID (int EXME_BeneficiosH_ID)
	{
		if (EXME_BeneficiosH_ID < 1) 
			set_Value (COLUMNNAME_EXME_BeneficiosH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_BeneficiosH_ID, Integer.valueOf(EXME_BeneficiosH_ID));
	}

	/** Get EXME_BeneficiosH_ID.
		@return EXME_BeneficiosH_ID	  */
	public int getEXME_BeneficiosH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BeneficiosH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
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
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
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

	/** Set EXME_Paciente_Dep_ID.
		@param EXME_Paciente_Dep_ID EXME_Paciente_Dep_ID	  */
	public void setEXME_Paciente_Dep_ID (int EXME_Paciente_Dep_ID)
	{
		if (EXME_Paciente_Dep_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente_Dep_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_Dep_ID, Integer.valueOf(EXME_Paciente_Dep_ID));
	}

	/** Get EXME_Paciente_Dep_ID.
		@return EXME_Paciente_Dep_ID	  */
	public int getEXME_Paciente_Dep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_Dep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	/** Set EXME_Paciente_Sus_ID.
		@param EXME_Paciente_Sus_ID EXME_Paciente_Sus_ID	  */
	public void setEXME_Paciente_Sus_ID (int EXME_Paciente_Sus_ID)
	{
		if (EXME_Paciente_Sus_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente_Sus_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_Sus_ID, Integer.valueOf(EXME_Paciente_Sus_ID));
	}

	/** Get EXME_Paciente_Sus_ID.
		@return EXME_Paciente_Sus_ID	  */
	public int getEXME_Paciente_Sus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_Sus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_VistaElegibilidad_ID.
		@param EXME_VistaElegibilidad_ID EXME_VistaElegibilidad_ID	  */
	public void setEXME_VistaElegibilidad_ID (int EXME_VistaElegibilidad_ID)
	{
		if (EXME_VistaElegibilidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_VistaElegibilidad_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_VistaElegibilidad_ID, Integer.valueOf(EXME_VistaElegibilidad_ID));
	}

	/** Get EXME_VistaElegibilidad_ID.
		@return EXME_VistaElegibilidad_ID	  */
	public int getEXME_VistaElegibilidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VistaElegibilidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Expiration Date.
		@param Fecha_Expira Expiration Date	  */
	public void setFecha_Expira (Timestamp Fecha_Expira)
	{
		set_Value (COLUMNNAME_Fecha_Expira, Fecha_Expira);
	}

	/** Get Expiration Date.
		@return Expiration Date	  */
	public Timestamp getFecha_Expira () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Expira);
	}

	/** Set Requested Date.
		@param Fecha_Solicitud Requested Date	  */
	public void setFecha_Solicitud (Timestamp Fecha_Solicitud)
	{
		set_Value (COLUMNNAME_Fecha_Solicitud, Fecha_Solicitud);
	}

	/** Get Requested Date.
		@return Requested Date	  */
	public Timestamp getFecha_Solicitud () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Solicitud);
	}

	/** Set Hour.
		@param Horas Hour	  */
	public void setHoras (int Horas)
	{
		set_Value (COLUMNNAME_Horas, Integer.valueOf(Horas));
	}

	/** Get Hour.
		@return Hour	  */
	public int getHoras () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Horas);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IsExpired.
		@param IsExpired IsExpired	  */
	public void setIsExpired (boolean IsExpired)
	{
		set_Value (COLUMNNAME_IsExpired, Boolean.valueOf(IsExpired));
	}

	/** Get IsExpired.
		@return IsExpired	  */
	public boolean isExpired () 
	{
		Object oo = get_Value(COLUMNNAME_IsExpired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set LastName.
		@param LastName LastName	  */
	public void setLastName (String LastName)
	{
		set_Value (COLUMNNAME_LastName, LastName);
	}

	/** Get LastName.
		@return LastName	  */
	public String getLastName () 
	{
		return (String)get_Value(COLUMNNAME_LastName);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
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

	/** Set NPI.
		@param NPI NPI	  */
	public void setNPI (String NPI)
	{
		set_Value (COLUMNNAME_NPI, NPI);
	}

	/** Get NPI.
		@return NPI	  */
	public String getNPI () 
	{
		return (String)get_Value(COLUMNNAME_NPI);
	}

	/** Set Insurance Policy.
		@param Poliza 
		Insurance Policy
	  */
	public void setPoliza (String Poliza)
	{
		set_Value (COLUMNNAME_Poliza, Poliza);
	}

	/** Get Insurance Policy.
		@return Insurance Policy
	  */
	public String getPoliza () 
	{
		return (String)get_Value(COLUMNNAME_Poliza);
	}
}