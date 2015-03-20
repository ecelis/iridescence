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

/** Generated Model for EXME_FechaFact
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_FechaFact extends PO implements I_EXME_FechaFact, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FechaFact (Properties ctx, int EXME_FechaFact_ID, String trxName)
    {
      super (ctx, EXME_FechaFact_ID, trxName);
      /** if (EXME_FechaFact_ID == 0)
        {
			setC_Invoice_ID (0);
			setEstatusCtaPac (null);
// Encounter Status Invoice
			setEstatusFact (null);
			setEXME_FechaFact_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_FechaFact (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FechaFact[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Invoice getC_Invoice() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Invoice.Table_Name);
        I_C_Invoice result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Invoice)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Invoice_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1)
			 throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
		set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encounter Description.
		@param CtaPacDescription 
		Encounter Description
	  */
	public void setCtaPacDescription (String CtaPacDescription)
	{
		set_Value (COLUMNNAME_CtaPacDescription, CtaPacDescription);
	}

	/** Get Encounter Description.
		@return Encounter Description
	  */
	public String getCtaPacDescription () 
	{
		return (String)get_Value(COLUMNNAME_CtaPacDescription);
	}

	/** EstatusCtaPac AD_Reference_ID=1200611 */
	public static final int ESTATUSCTAPAC_AD_Reference_ID=1200611;
	/** Rejected = R */
	public static final String ESTATUSCTAPAC_Rejected = "R";
	/** Authorize Pending = P */
	public static final String ESTATUSCTAPAC_AuthorizePending = "P";
	/** Missing Signature of Doctor = F */
	public static final String ESTATUSCTAPAC_MissingSignatureOfDoctor = "F";
	/** Missing Documentation = D */
	public static final String ESTATUSCTAPAC_MissingDocumentation = "D";
	/** Authorized = A */
	public static final String ESTATUSCTAPAC_Authorized = "A";
	/** In Process = E */
	public static final String ESTATUSCTAPAC_InProcess = "E";
	/** Closed = C */
	public static final String ESTATUSCTAPAC_Closed = "C";
	/** Set Encounter Estatus.
		@param EstatusCtaPac 
		Encounter Estatus
	  */
	public void setEstatusCtaPac (String EstatusCtaPac)
	{
		if (EstatusCtaPac == null) throw new IllegalArgumentException ("EstatusCtaPac is mandatory");
		if (EstatusCtaPac.equals("R") || EstatusCtaPac.equals("P") || EstatusCtaPac.equals("F") || EstatusCtaPac.equals("D") || EstatusCtaPac.equals("A") || EstatusCtaPac.equals("E") || EstatusCtaPac.equals("C")); else throw new IllegalArgumentException ("EstatusCtaPac Invalid value - " + EstatusCtaPac + " - Reference_ID=1200611 - R - P - F - D - A - E - C");		set_Value (COLUMNNAME_EstatusCtaPac, EstatusCtaPac);
	}

	/** Get Encounter Estatus.
		@return Encounter Estatus
	  */
	public String getEstatusCtaPac () 
	{
		return (String)get_Value(COLUMNNAME_EstatusCtaPac);
	}

	/** Set Description Status.
		@param EstatusDescription 
		Description Status
	  */
	public void setEstatusDescription (String EstatusDescription)
	{
		set_Value (COLUMNNAME_EstatusDescription, EstatusDescription);
	}

	/** Get Description Status.
		@return Description Status
	  */
	public String getEstatusDescription () 
	{
		return (String)get_Value(COLUMNNAME_EstatusDescription);
	}

	/** EstatusFact AD_Reference_ID=1200612 */
	public static final int ESTATUSFACT_AD_Reference_ID=1200612;
	/** Laboratory = L */
	public static final String ESTATUSFACT_Laboratory = "L";
	/** RX = R */
	public static final String ESTATUSFACT_RX = "R";
	/** Coordinator = C */
	public static final String ESTATUSFACT_Coordinator = "C";
	/** Medical Emergency = U */
	public static final String ESTATUSFACT_MedicalEmergency = "U";
	/** Medical Specialty = E */
	public static final String ESTATUSFACT_MedicalSpecialty = "E";
	/** To Change = M */
	public static final String ESTATUSFACT_ToChange = "M";
	/** Emergency Medical and Specialty = A */
	public static final String ESTATUSFACT_EmergencyMedicalAndSpecialty = "A";
	/** List to Send = S */
	public static final String ESTATUSFACT_ListToSend = "S";
	/** Set Invoice Status.
		@param EstatusFact 
		Invoice Status
	  */
	public void setEstatusFact (String EstatusFact)
	{
		if (EstatusFact == null) throw new IllegalArgumentException ("EstatusFact is mandatory");
		if (EstatusFact.equals("L") || EstatusFact.equals("R") || EstatusFact.equals("C") || EstatusFact.equals("U") || EstatusFact.equals("E") || EstatusFact.equals("M") || EstatusFact.equals("A") || EstatusFact.equals("S")); else throw new IllegalArgumentException ("EstatusFact Invalid value - " + EstatusFact + " - Reference_ID=1200612 - L - R - C - U - E - M - A - S");		set_Value (COLUMNNAME_EstatusFact, EstatusFact);
	}

	/** Get Invoice Status.
		@return Invoice Status
	  */
	public String getEstatusFact () 
	{
		return (String)get_Value(COLUMNNAME_EstatusFact);
	}

	/** Set Date Invoice.
		@param EXME_FechaFact_ID 
		Date Invoice
	  */
	public void setEXME_FechaFact_ID (int EXME_FechaFact_ID)
	{
		if (EXME_FechaFact_ID < 1)
			 throw new IllegalArgumentException ("EXME_FechaFact_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FechaFact_ID, Integer.valueOf(EXME_FechaFact_ID));
	}

	/** Get Date Invoice.
		@return Date Invoice
	  */
	public int getEXME_FechaFact_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FechaFact_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Approved.
		@param FechaAprob 
		Date Approved
	  */
	public void setFechaAprob (Timestamp FechaAprob)
	{
		set_Value (COLUMNNAME_FechaAprob, FechaAprob);
	}

	/** Get Date Approved.
		@return Date Approved
	  */
	public Timestamp getFechaAprob () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAprob);
	}

	/** Set Date Encounter.
		@param FechaCtaPac 
		Date Encounter
	  */
	public void setFechaCtaPac (Timestamp FechaCtaPac)
	{
		set_Value (COLUMNNAME_FechaCtaPac, FechaCtaPac);
	}

	/** Get Date Encounter.
		@return Date Encounter
	  */
	public Timestamp getFechaCtaPac () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCtaPac);
	}

	/** Set Sending Date.
		@param FechaEnv Sending Date	  */
	public void setFechaEnv (Timestamp FechaEnv)
	{
		set_Value (COLUMNNAME_FechaEnv, FechaEnv);
	}

	/** Get Sending Date.
		@return Sending Date	  */
	public Timestamp getFechaEnv () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaEnv);
	}

	/** Set Status Date.
		@param FechaEstatus 
		Status Date
	  */
	public void setFechaEstatus (Timestamp FechaEstatus)
	{
		set_Value (COLUMNNAME_FechaEstatus, FechaEstatus);
	}

	/** Get Status Date.
		@return Status Date
	  */
	public Timestamp getFechaEstatus () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaEstatus);
	}

	/** Set Termination Date.
		@param FechaVencimiento Termination Date	  */
	public void setFechaVencimiento (Timestamp FechaVencimiento)
	{
		set_Value (COLUMNNAME_FechaVencimiento, FechaVencimiento);
	}

	/** Get Termination Date.
		@return Termination Date	  */
	public Timestamp getFechaVencimiento () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaVencimiento);
	}
}