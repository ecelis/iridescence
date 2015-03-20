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

/** Generated Model for DH_PatientInfo
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_DH_PatientInfo extends PO implements I_DH_PatientInfo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_DH_PatientInfo (Properties ctx, int DH_PatientInfo_ID, String trxName)
    {
      super (ctx, DH_PatientInfo_ID, trxName);
      /** if (DH_PatientInfo_ID == 0)
        {
			setDH_PatientInfo_ID (0);
			setDocType (null);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DH_PatientInfo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DH_PatientInfo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Community Bank.
		@param BancaComunal Community Bank	  */
	public void setBancaComunal (String BancaComunal)
	{
		set_Value (COLUMNNAME_BancaComunal, BancaComunal);
	}

	/** Get Community Bank.
		@return Community Bank	  */
	public String getBancaComunal () 
	{
		return (String)get_Value(COLUMNNAME_BancaComunal);
	}

	/** Set Cycle.
		@param Ciclo Cycle	  */
	public void setCiclo (String Ciclo)
	{
		set_Value (COLUMNNAME_Ciclo, Ciclo);
	}

	/** Get Cycle.
		@return Cycle	  */
	public String getCiclo () 
	{
		return (String)get_Value(COLUMNNAME_Ciclo);
	}

	/** CreditType AD_Reference_ID=1200678 */
	public static final int CREDITTYPE_AD_Reference_ID=1200678;
	/** BancaComunal = 0 */
	public static final String CREDITTYPE_BancaComunal = "0";
	/** CreditoIndividual = 1 */
	public static final String CREDITTYPE_CreditoIndividual = "1";
	/** Set Credit Type.
		@param CreditType Credit Type	  */
	public void setCreditType (String CreditType)
	{

		if (CreditType == null || CreditType.equals("0") || CreditType.equals("1")); else throw new IllegalArgumentException ("CreditType Invalid value - " + CreditType + " - Reference_ID=1200678 - 0 - 1");		set_Value (COLUMNNAME_CreditType, CreditType);
	}

	/** Get Credit Type.
		@return Credit Type	  */
	public String getCreditType () 
	{
		return (String)get_Value(COLUMNNAME_CreditType);
	}

	/** Set Patient Info (right holder).
		@param DH_PatientInfo_ID Patient Info (right holder)	  */
	public void setDH_PatientInfo_ID (int DH_PatientInfo_ID)
	{
		if (DH_PatientInfo_ID < 1)
			 throw new IllegalArgumentException ("DH_PatientInfo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_DH_PatientInfo_ID, Integer.valueOf(DH_PatientInfo_ID));
	}

	/** Get Patient Info (right holder).
		@return Patient Info (right holder)	  */
	public int getDH_PatientInfo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DH_PatientInfo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DocType AD_Reference_ID=1200679 */
	public static final int DOCTYPE_AD_Reference_ID=1200679;
	/** National_ID = DNI */
	public static final String DOCTYPE_National_ID = "DNI";
	/** Passport = PAS */
	public static final String DOCTYPE_Passport = "PAS";
	/** Set Document Type.
		@param DocType Document Type	  */
	public void setDocType (String DocType)
	{
		if (DocType == null) throw new IllegalArgumentException ("DocType is mandatory");
		if (DocType.equals("DNI") || DocType.equals("PAS")); else throw new IllegalArgumentException ("DocType Invalid value - " + DocType + " - Reference_ID=1200679 - DNI - PAS");		set_Value (COLUMNNAME_DocType, DocType);
	}

	/** Get Document Type.
		@return Document Type	  */
	public String getDocType () 
	{
		return (String)get_Value(COLUMNNAME_DocType);
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

	public I_EXME_PacienteRel getEXME_PacienteRel() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PacienteRel.Table_Name);
        I_EXME_PacienteRel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PacienteRel)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PacienteRel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Relations.
		@param EXME_PacienteRel_ID 
		Patient Relations
	  */
	public void setEXME_PacienteRel_ID (int EXME_PacienteRel_ID)
	{
		if (EXME_PacienteRel_ID < 1) 
			set_Value (COLUMNNAME_EXME_PacienteRel_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PacienteRel_ID, Integer.valueOf(EXME_PacienteRel_ID));
	}

	/** Get Patient Relations.
		@return Patient Relations
	  */
	public int getEXME_PacienteRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteRel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Disbursement date.
		@param FechaDesem Disbursement date	  */
	public void setFechaDesem (Timestamp FechaDesem)
	{
		set_Value (COLUMNNAME_FechaDesem, FechaDesem);
	}

	/** Get Disbursement date.
		@return Disbursement date	  */
	public Timestamp getFechaDesem () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaDesem);
	}

	/** Set Date of last payment.
		@param FechaUltPago Date of last payment	  */
	public void setFechaUltPago (Timestamp FechaUltPago)
	{
		set_Value (COLUMNNAME_FechaUltPago, FechaUltPago);
	}

	/** Get Date of last payment.
		@return Date of last payment	  */
	public Timestamp getFechaUltPago () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaUltPago);
	}

	/** Set Advisor name.
		@param NombreAsesor Advisor name	  */
	public void setNombreAsesor (String NombreAsesor)
	{
		set_Value (COLUMNNAME_NombreAsesor, NombreAsesor);
	}

	/** Get Advisor name.
		@return Advisor name	  */
	public String getNombreAsesor () 
	{
		return (String)get_Value(COLUMNNAME_NombreAsesor);
	}

	/** SOCreditStatus AD_Reference_ID=1200680 */
	public static final int SOCREDITSTATUS_AD_Reference_ID=1200680;
	/** Overdue = CM */
	public static final String SOCREDITSTATUS_Overdue = "CM";
	/** NoCredit = CA */
	public static final String SOCREDITSTATUS_NoCredit = "CA";
	/** ActiveCredit = CV */
	public static final String SOCREDITSTATUS_ActiveCredit = "CV";
	/** Set Credit Status.
		@param SOCreditStatus 
		Business Partner Credit Status
	  */
	public void setSOCreditStatus (String SOCreditStatus)
	{

		if (SOCreditStatus == null || SOCreditStatus.equals("CM") || SOCreditStatus.equals("CA") || SOCreditStatus.equals("CV")); else throw new IllegalArgumentException ("SOCreditStatus Invalid value - " + SOCreditStatus + " - Reference_ID=1200680 - CM - CA - CV");		set_Value (COLUMNNAME_SOCreditStatus, SOCreditStatus);
	}

	/** Get Credit Status.
		@return Business Partner Credit Status
	  */
	public String getSOCreditStatus () 
	{
		return (String)get_Value(COLUMNNAME_SOCreditStatus);
	}
}