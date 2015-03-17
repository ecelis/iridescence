/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PatientRel
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PatientRel extends PO implements I_EXME_PatientRel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PatientRel (Properties ctx, int EXME_PatientRel_ID, String trxName)
    {
      super (ctx, EXME_PatientRel_ID, trxName);
      /** if (EXME_PatientRel_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setEXME_Parentesco_ID (0);
			setEXME_PatientRel_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PatientRel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PatientRel[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			 throw new IllegalArgumentException ("EXME_Parentesco_ID is mandatory.");
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

	/** Set Patient's Relative.
		@param EXME_PatientRel_ID Patient's Relative	  */
	public void setEXME_PatientRel_ID (int EXME_PatientRel_ID)
	{
		if (EXME_PatientRel_ID < 1)
			 throw new IllegalArgumentException ("EXME_PatientRel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PatientRel_ID, Integer.valueOf(EXME_PatientRel_ID));
	}

	/** Get Patient's Relative.
		@return Patient's Relative	  */
	public int getEXME_PatientRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PatientRel_ID);
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
}