/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_CtaPac_V
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CtaPac_V extends PO implements I_EXME_CtaPac_V, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPac_V (Properties ctx, int EXME_CtaPac_V_ID, String trxName)
    {
      super (ctx, EXME_CtaPac_V_ID, trxName);
      /** if (EXME_CtaPac_V_ID == 0)
        {
			setAdmitDate (new Timestamp( System.currentTimeMillis() ));
			setEncounter (null);
			setEXME_CtaPac_V_ID (0);
			setFechaNac (new Timestamp( System.currentTimeMillis() ));
			setMRN (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPac_V (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPac_V[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Admit Date.
		@param AdmitDate 
		Admit Date
	  */
	public void setAdmitDate (Timestamp AdmitDate)
	{
		if (AdmitDate == null)
			throw new IllegalArgumentException ("AdmitDate is mandatory.");
		set_Value (COLUMNNAME_AdmitDate, AdmitDate);
	}

	/** Get Admit Date.
		@return Admit Date
	  */
	public Timestamp getAdmitDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AdmitDate);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Document No.
		@param Encounter 
		Document No
	  */
	public void setEncounter (String Encounter)
	{
		if (Encounter == null)
			throw new IllegalArgumentException ("Encounter is mandatory.");
		set_Value (COLUMNNAME_Encounter, Encounter);
	}

	/** Get Document No.
		@return Document No
	  */
	public String getEncounter () 
	{
		return (String)get_Value(COLUMNNAME_Encounter);
	}

	/** Set Encounter Status.
		@param EncounterStatus Encounter Status	  */
	public void setEncounterStatus (boolean EncounterStatus)
	{
		set_Value (COLUMNNAME_EncounterStatus, Boolean.valueOf(EncounterStatus));
	}

	/** Get Encounter Status.
		@return Encounter Status	  */
	public boolean isEncounterStatus () 
	{
		Object oo = get_Value(COLUMNNAME_EncounterStatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Encounter View.
		@param EXME_CtaPac_V_ID Encounter View	  */
	public void setEXME_CtaPac_V_ID (int EXME_CtaPac_V_ID)
	{
		if (EXME_CtaPac_V_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_V_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_V_ID, Integer.valueOf(EXME_CtaPac_V_ID));
	}

	/** Get Encounter View.
		@return Encounter View	  */
	public int getEXME_CtaPac_V_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_V_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discharge Date.
		@param FechaAlta 
		Discharge Date
	  */
	public void setFechaAlta (Timestamp FechaAlta)
	{
		set_Value (COLUMNNAME_FechaAlta, FechaAlta);
	}

	/** Get Discharge Date.
		@return Discharge Date
	  */
	public Timestamp getFechaAlta () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAlta);
	}

	/** Set Closing Date.
		@param FechaCierre 
		Date of Intervention Closing
	  */
	public void setFechaCierre (Timestamp FechaCierre)
	{
		set_Value (COLUMNNAME_FechaCierre, FechaCierre);
	}

	/** Get Closing Date.
		@return Date of Intervention Closing
	  */
	public Timestamp getFechaCierre () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCierre);
	}

	/** Set Birth Date.
		@param FechaNac 
		Birth Date
	  */
	public void setFechaNac (Timestamp FechaNac)
	{
		if (FechaNac == null)
			throw new IllegalArgumentException ("FechaNac is mandatory.");
		set_Value (COLUMNNAME_FechaNac, FechaNac);
	}

	/** Get Birth Date.
		@return Birth Date
	  */
	public Timestamp getFechaNac () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaNac);
	}

	/** Set Medical Record Number.
		@param MRN 
		Medical Record Number
	  */
	public void setMRN (String MRN)
	{
		if (MRN == null)
			throw new IllegalArgumentException ("MRN is mandatory.");
		set_Value (COLUMNNAME_MRN, MRN);
	}

	/** Get Medical Record Number.
		@return Medical Record Number
	  */
	public String getMRN () 
	{
		return (String)get_Value(COLUMNNAME_MRN);
	}

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		set_ValueE (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_ValueE(COLUMNNAME_Nombre_Pac);
	}

	/** Set Social Security Number.
		@param NSS Social Security Number	  */
	public void setNSS (String NSS)
	{
		set_Value (COLUMNNAME_NSS, NSS);
	}

	/** Get Social Security Number.
		@return Social Security Number	  */
	public String getNSS () 
	{
		return (String)get_Value(COLUMNNAME_NSS);
	}
}