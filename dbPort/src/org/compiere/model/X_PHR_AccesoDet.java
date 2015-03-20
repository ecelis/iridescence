/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for PHR_AccesoDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_AccesoDet extends PO implements I_PHR_AccesoDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_AccesoDet (Properties ctx, int PHR_AccesoDet_ID, String trxName)
    {
      super (ctx, PHR_AccesoDet_ID, trxName);
      /** if (PHR_AccesoDet_ID == 0)
        {
			setAccessLevel (null);
			setPHR_AccesoDet_ID (0);
			setPHR_Acceso_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_AccesoDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_AccesoDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AccessLevel AD_Reference_ID=1200441 */
	public static final int ACCESSLEVEL_AD_Reference_ID=1200441;
	/** Insurance = I */
	public static final String ACCESSLEVEL_Insurance = "I";
	/** Events = E */
	public static final String ACCESSLEVEL_Events = "E";
	/** Emergency Contacts = C */
	public static final String ACCESSLEVEL_EmergencyContacts = "C";
	/** Doctors = D */
	public static final String ACCESSLEVEL_Doctors = "D";
	/** Hospitals = H */
	public static final String ACCESSLEVEL_Hospitals = "H";
	/** Medical Conditions = M */
	public static final String ACCESSLEVEL_MedicalConditions = "M";
	/** Allergies = A */
	public static final String ACCESSLEVEL_Allergies = "A";
	/** Medical Procedures & Surgeries = S */
	public static final String ACCESSLEVEL_MedicalProceduresSurgeries = "S";
	/** Vital Signs = V */
	public static final String ACCESSLEVEL_VitalSigns = "V";
	/** Remiders = R */
	public static final String ACCESSLEVEL_Remiders = "R";
	/** Questionaires = Q */
	public static final String ACCESSLEVEL_Questionaires = "Q";
	/** Medications = m */
	public static final String ACCESSLEVEL_Medications = "m";
	/** Vaccinations = v */
	public static final String ACCESSLEVEL_Vaccinations = "v";
	/** File Documentation = F */
	public static final String ACCESSLEVEL_FileDocumentation = "F";
	/** Set Data Access Level.
		@param AccessLevel 
		Access Level required
	  */
	public void setAccessLevel (String AccessLevel)
	{
		if (AccessLevel == null) throw new IllegalArgumentException ("AccessLevel is mandatory");
		if (AccessLevel.equals("I") || AccessLevel.equals("E") || AccessLevel.equals("C") || AccessLevel.equals("D") || AccessLevel.equals("H") || AccessLevel.equals("M") || AccessLevel.equals("A") || AccessLevel.equals("S") || AccessLevel.equals("V") || AccessLevel.equals("R") || AccessLevel.equals("Q") || AccessLevel.equals("m") || AccessLevel.equals("v") || AccessLevel.equals("F")); else throw new IllegalArgumentException ("AccessLevel Invalid value - " + AccessLevel + " - Reference_ID=1200441 - I - E - C - D - H - M - A - S - V - R - Q - m - v - F");		set_Value (COLUMNNAME_AccessLevel, AccessLevel);
	}

	/** Get Data Access Level.
		@return Access Level required
	  */
	public String getAccessLevel () 
	{
		return (String)get_Value(COLUMNNAME_AccessLevel);
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

	/** Set Access Detail to PHR.
		@param PHR_AccesoDet_ID Access Detail to PHR	  */
	public void setPHR_AccesoDet_ID (int PHR_AccesoDet_ID)
	{
		if (PHR_AccesoDet_ID < 1)
			 throw new IllegalArgumentException ("PHR_AccesoDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_AccesoDet_ID, Integer.valueOf(PHR_AccesoDet_ID));
	}

	/** Get Access Detail to PHR.
		@return Access Detail to PHR	  */
	public int getPHR_AccesoDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_AccesoDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PHR_Acceso getPHR_Acceso() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_Acceso.Table_Name);
        I_PHR_Acceso result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_Acceso)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_Acceso_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Access to PHR.
		@param PHR_Acceso_ID Access to PHR	  */
	public void setPHR_Acceso_ID (int PHR_Acceso_ID)
	{
		if (PHR_Acceso_ID < 1)
			 throw new IllegalArgumentException ("PHR_Acceso_ID is mandatory.");
		set_Value (COLUMNNAME_PHR_Acceso_ID, Integer.valueOf(PHR_Acceso_ID));
	}

	/** Get Access to PHR.
		@return Access to PHR	  */
	public int getPHR_Acceso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_Acceso_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}