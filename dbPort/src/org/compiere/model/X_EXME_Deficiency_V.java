/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Deficiency_V
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Deficiency_V extends PO implements I_EXME_Deficiency_V, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Deficiency_V (Properties ctx, int EXME_Deficiency_V_ID, String trxName)
    {
      super (ctx, EXME_Deficiency_V_ID, trxName);
      /** if (EXME_Deficiency_V_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_Deficiency_V (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Deficiency_V[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Assigned To.
		@param AssignedTo Assigned To	  */
	public void setAssignedTo (String AssignedTo)
	{
		set_Value (COLUMNNAME_AssignedTo, AssignedTo);
	}

	/** Get Assigned To.
		@return Assigned To	  */
	public String getAssignedTo () 
	{
		return (String)get_Value(COLUMNNAME_AssignedTo);
	}

	/** Set Days Deficient.
		@param DaysDeficient Days Deficient	  */
	public void setDaysDeficient (String DaysDeficient)
	{
		set_Value (COLUMNNAME_DaysDeficient, DaysDeficient);
	}

	/** Get Days Deficient.
		@return Days Deficient	  */
	public String getDaysDeficient () 
	{
		return (String)get_Value(COLUMNNAME_DaysDeficient);
	}

	/** DefGroup AD_Reference_ID=1200662 */
	public static final int DEFGROUP_AD_Reference_ID=1200662;
	/** Authentification = A */
	public static final String DEFGROUP_Authentification = "A";
	/** Discontinue = D */
	public static final String DEFGROUP_Discontinue = "D";
	/** Review = R */
	public static final String DEFGROUP_Review = "R";
	/** Set DefGroup.
		@param DefGroup DefGroup	  */
	public void setDefGroup (String DefGroup)
	{

		if (DefGroup == null || DefGroup.equals("A") || DefGroup.equals("D") || DefGroup.equals("R")); else throw new IllegalArgumentException ("DefGroup Invalid value - " + DefGroup + " - Reference_ID=1200662 - A - D - R");		set_Value (COLUMNNAME_DefGroup, DefGroup);
	}

	/** Get DefGroup.
		@return DefGroup	  */
	public String getDefGroup () 
	{
		return (String)get_Value(COLUMNNAME_DefGroup);
	}

	/** Set Deficiency.
		@param Deficiency Deficiency	  */
	public void setDeficiency (String Deficiency)
	{
		set_Value (COLUMNNAME_Deficiency, Deficiency);
	}

	/** Get Deficiency.
		@return Deficiency	  */
	public String getDeficiency () 
	{
		return (String)get_Value(COLUMNNAME_Deficiency);
	}

	/** Set Document No.
		@param Encounter 
		Document No
	  */
	public void setEncounter (String Encounter)
	{
		set_Value (COLUMNNAME_Encounter, Encounter);
	}

	/** Get Document No.
		@return Document No
	  */
	public String getEncounter () 
	{
		return (String)get_Value(COLUMNNAME_Encounter);
	}

	/** Set EXME_Deficiency_V_ID.
		@param EXME_Deficiency_V_ID EXME_Deficiency_V_ID	  */
	public void setEXME_Deficiency_V_ID (int EXME_Deficiency_V_ID)
	{
		if (EXME_Deficiency_V_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_Deficiency_V_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_Deficiency_V_ID, Integer.valueOf(EXME_Deficiency_V_ID));
	}

	/** Get EXME_Deficiency_V_ID.
		@return EXME_Deficiency_V_ID	  */
	public int getEXME_Deficiency_V_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Deficiency_V_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
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

	/** Set Medical Record Number.
		@param MRN 
		Medical Record Number
	  */
	public void setMRN (String MRN)
	{
		set_Value (COLUMNNAME_MRN, MRN);
	}

	/** Get Medical Record Number.
		@return Medical Record Number
	  */
	public String getMRN () 
	{
		return (String)get_Value(COLUMNNAME_MRN);
	}

	/** Set PatientName.
		@param PatientName PatientName	  */
	public void setPatientName (String PatientName)
	{
		set_Value (COLUMNNAME_PatientName, PatientName);
	}

	/** Get PatientName.
		@return PatientName	  */
	public String getPatientName () 
	{
		return (String)get_Value(COLUMNNAME_PatientName);
	}

	/** Type AD_Reference_ID=1200663 */
	public static final int TYPE_AD_Reference_ID=1200663;
	/** Laboratory Results = LR */
	public static final String TYPE_LaboratoryResults = "LR";
	/** Auto Stopped Medications = AS */
	public static final String TYPE_AutoStoppedMedications = "AS";
	/** Medications = ME */
	public static final String TYPE_Medications = "ME";
	/** About to expire Diets = AD */
	public static final String TYPE_AboutToExpireDiets = "AD";
	/** Admithing = AT */
	public static final String TYPE_Admithing = "AT";
	/** About to expire Medications = AM */
	public static final String TYPE_AboutToExpireMedications = "AM";
	/** Services = SE */
	public static final String TYPE_Services = "SE";
	/** Diets = DI */
	public static final String TYPE_Diets = "DI";
	/** Others = OT */
	public static final String TYPE_Others = "OT";
	/** Forms = FO */
	public static final String TYPE_Forms = "FO";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("LR") || Type.equals("AS") || Type.equals("ME") || Type.equals("AD") || Type.equals("AT") || Type.equals("AM") || Type.equals("SE") || Type.equals("DI") || Type.equals("OT") || Type.equals("FO")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200663 - LR - AS - ME - AD - AT - AM - SE - DI - OT - FO");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}