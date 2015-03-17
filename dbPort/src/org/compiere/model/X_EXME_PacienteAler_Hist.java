/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PacienteAler_Hist
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PacienteAler_Hist extends PO implements I_EXME_PacienteAler_Hist, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PacienteAler_Hist (Properties ctx, int EXME_PacienteAler_Hist_ID, String trxName)
    {
      super (ctx, EXME_PacienteAler_Hist_ID, trxName);
      /** if (EXME_PacienteAler_Hist_ID == 0)
        {
			setEXME_PacienteAler_Hist_ID (0);
			setEXME_PacienteAler_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PacienteAler_Hist (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PacienteAler_Hist[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Estatus AD_Reference_ID=1200351 */
	public static final int ESTATUS_AD_Reference_ID=1200351;
	/** Confirmed or verified = C */
	public static final String ESTATUS_ConfirmedOrVerified = "C";
	/** Doubt raised = D */
	public static final String ESTATUS_DoubtRaised = "D";
	/** Erroneous = E */
	public static final String ESTATUS_Erroneous = "E";
	/** Pending = P */
	public static final String ESTATUS_Pending = "P";
	/** Suspect = S */
	public static final String ESTATUS_Suspect = "S";
	/** Inactive = U */
	public static final String ESTATUS_Inactive = "U";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{

		if (Estatus == null || Estatus.equals("C") || Estatus.equals("D") || Estatus.equals("E") || Estatus.equals("P") || Estatus.equals("S") || Estatus.equals("U")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200351 - C - D - E - P - S - U");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set History Patient Allergy.
		@param EXME_PacienteAler_Hist_ID 
		History Patient Allergy Identifier
	  */
	public void setEXME_PacienteAler_Hist_ID (int EXME_PacienteAler_Hist_ID)
	{
		if (EXME_PacienteAler_Hist_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteAler_Hist_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PacienteAler_Hist_ID, Integer.valueOf(EXME_PacienteAler_Hist_ID));
	}

	/** Get History Patient Allergy.
		@return History Patient Allergy Identifier
	  */
	public int getEXME_PacienteAler_Hist_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteAler_Hist_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PacienteAler getEXME_PacienteAler() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PacienteAler.Table_Name);
        I_EXME_PacienteAler result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PacienteAler)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PacienteAler_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Allergies Patient.
		@param EXME_PacienteAler_ID 
		Allergies Patient
	  */
	public void setEXME_PacienteAler_ID (int EXME_PacienteAler_ID)
	{
		if (EXME_PacienteAler_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteAler_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PacienteAler_ID, Integer.valueOf(EXME_PacienteAler_ID));
	}

	/** Get Allergies Patient.
		@return Allergies Patient
	  */
	public int getEXME_PacienteAler_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteAler_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reaction.
		@param Reaccion Reaction	  */
	public void setReaccion (String Reaccion)
	{
		set_Value (COLUMNNAME_Reaccion, Reaccion);
	}

	/** Get Reaction.
		@return Reaction	  */
	public String getReaccion () 
	{
		return (String)get_Value(COLUMNNAME_Reaccion);
	}

	/** Severidad AD_Reference_ID=1200350 */
	public static final int SEVERIDAD_AD_Reference_ID=1200350;
	/** Mild = MI */
	public static final String SEVERIDAD_Mild = "MI";
	/** Moderate = MO */
	public static final String SEVERIDAD_Moderate = "MO";
	/** Severe = SV */
	public static final String SEVERIDAD_Severe = "SV";
	/** Unknow = U */
	public static final String SEVERIDAD_Unknow = "U";
	/** Set Severity.
		@param Severidad Severity	  */
	public void setSeveridad (String Severidad)
	{

		if (Severidad == null || Severidad.equals("MI") || Severidad.equals("MO") || Severidad.equals("SV") || Severidad.equals("U")); else throw new IllegalArgumentException ("Severidad Invalid value - " + Severidad + " - Reference_ID=1200350 - MI - MO - SV - U");		set_Value (COLUMNNAME_Severidad, Severidad);
	}

	/** Get Severity.
		@return Severity	  */
	public String getSeveridad () 
	{
		return (String)get_Value(COLUMNNAME_Severidad);
	}
}