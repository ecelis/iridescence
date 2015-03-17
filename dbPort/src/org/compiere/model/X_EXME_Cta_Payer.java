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

/** Generated Model for EXME_Cta_Payer
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Cta_Payer extends PO implements I_EXME_Cta_Payer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Cta_Payer (Properties ctx, int EXME_Cta_Payer_ID, String trxName)
    {
      super (ctx, EXME_Cta_Payer_ID, trxName);
      /** if (EXME_Cta_Payer_ID == 0)
        {
			setC_BPartner_ID (0);
			setEXME_Cta_Payer_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Cta_Payer (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Cta_Payer[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Admit Date.
		@param AdmitDate 
		Admit Date
	  */
	public void setAdmitDate (Timestamp AdmitDate)
	{
		set_Value (COLUMNNAME_AdmitDate, AdmitDate);
	}

	/** Get Admit Date.
		@return Admit Date
	  */
	public Timestamp getAdmitDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AdmitDate);
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
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
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

	/** Set Encounter and Payer View ID.
		@param EXME_Cta_Payer_ID 
		Encounter and Payer View ID
	  */
	public void setEXME_Cta_Payer_ID (int EXME_Cta_Payer_ID)
	{
		if (EXME_Cta_Payer_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cta_Payer_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Cta_Payer_ID, Integer.valueOf(EXME_Cta_Payer_ID));
	}

	/** Get Encounter and Payer View ID.
		@return Encounter and Payer View ID
	  */
	public int getEXME_Cta_Payer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cta_Payer_ID);
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

	/** Set Support Billing.
		@param SupportBilling Support Billing	  */
	public void setSupportBilling (String SupportBilling)
	{
		set_Value (COLUMNNAME_SupportBilling, SupportBilling);
	}

	/** Get Support Billing.
		@return Support Billing	  */
	public String getSupportBilling () 
	{
		return (String)get_Value(COLUMNNAME_SupportBilling);
	}
}