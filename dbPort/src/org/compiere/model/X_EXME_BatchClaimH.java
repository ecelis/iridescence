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

/** Generated Model for EXME_BatchClaimH
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_BatchClaimH extends PO implements I_EXME_BatchClaimH, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_BatchClaimH (Properties ctx, int EXME_BatchClaimH_ID, String trxName)
    {
      super (ctx, EXME_BatchClaimH_ID, trxName);
      /** if (EXME_BatchClaimH_ID == 0)
        {
			setEXME_BatchClaimH_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_BatchClaimH (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_BatchClaimH[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AckMsg.
		@param AckMsg AckMsg	  */
	public void setAckMsg (String AckMsg)
	{
		set_Value (COLUMNNAME_AckMsg, AckMsg);
	}

	/** Get AckMsg.
		@return AckMsg	  */
	public String getAckMsg () 
	{
		return (String)get_Value(COLUMNNAME_AckMsg);
	}

	/** ConfType AD_Reference_ID=1200425 */
	public static final int CONFTYPE_AD_Reference_ID=1200425;
	/** Surveillance Report = W */
	public static final String CONFTYPE_SurveillanceReport = "W";
	/** Clinical Record CDA = C */
	public static final String CONFTYPE_ClinicalRecordCDA = "C";
	/** Clinical Record CCR = R */
	public static final String CONFTYPE_ClinicalRecordCCR = "R";
	/** Elegibility = E */
	public static final String CONFTYPE_Elegibility = "E";
	/** Reportable Lab Results = L */
	public static final String CONFTYPE_ReportableLabResults = "L";
	/** Inmunization Messaging = I */
	public static final String CONFTYPE_InmunizationMessaging = "I";
	/** RX Script = S */
	public static final String CONFTYPE_RXScript = "S";
	/** LabDAQ = Q */
	public static final String CONFTYPE_LabDAQ = "Q";
	/** Professional Claim = P */
	public static final String CONFTYPE_ProfessionalClaim = "P";
	/** Institutional Claim = T */
	public static final String CONFTYPE_InstitutionalClaim = "T";
	/** PIStatement = A */
	public static final String CONFTYPE_PIStatement = "A";
	/** Both Claim Types = B */
	public static final String CONFTYPE_BothClaimTypes = "B";
	/** Outpatient Claim = N */
	public static final String CONFTYPE_OutpatientClaim = "N";
	/** Outpatient Institutional Claim = O */
	public static final String CONFTYPE_OutpatientInstitutionalClaim = "O";
	/** Outpatient Professional Claim = F */
	public static final String CONFTYPE_OutpatientProfessionalClaim = "F";
	/** Set Configuration Type.
		@param ConfType Configuration Type	  */
	public void setConfType (String ConfType)
	{

		if (ConfType == null || ConfType.equals("W") || ConfType.equals("C") || ConfType.equals("R") || ConfType.equals("E") || ConfType.equals("L") || ConfType.equals("I") || ConfType.equals("S") || ConfType.equals("Q") || ConfType.equals("P") || ConfType.equals("T") || ConfType.equals("A") || ConfType.equals("B") || ConfType.equals("N") || ConfType.equals("O") || ConfType.equals("F")); else throw new IllegalArgumentException ("ConfType Invalid value - " + ConfType + " - Reference_ID=1200425 - W - C - R - E - L - I - S - Q - P - T - A - B - N - O - F");		set_Value (COLUMNNAME_ConfType, ConfType);
	}

	/** Get Configuration Type.
		@return Configuration Type	  */
	public String getConfType () 
	{
		return (String)get_Value(COLUMNNAME_ConfType);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set End Date.
		@param EndDate 
		End Date of Claim Report
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return End Date of Claim Report
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set EXME_BatchClaimH_ID.
		@param EXME_BatchClaimH_ID EXME_BatchClaimH_ID	  */
	public void setEXME_BatchClaimH_ID (int EXME_BatchClaimH_ID)
	{
		if (EXME_BatchClaimH_ID < 1)
			 throw new IllegalArgumentException ("EXME_BatchClaimH_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_BatchClaimH_ID, Integer.valueOf(EXME_BatchClaimH_ID));
	}

	/** Get EXME_BatchClaimH_ID.
		@return EXME_BatchClaimH_ID	  */
	public int getEXME_BatchClaimH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BatchClaimH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HL7_Dashboard getHL7_Dashboard() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_Dashboard.Table_Name);
        I_HL7_Dashboard result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_Dashboard)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_Dashboard_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Dashboard.
		@param HL7_Dashboard_ID HL7 Dashboard	  */
	public void setHL7_Dashboard_ID (int HL7_Dashboard_ID)
	{
		if (HL7_Dashboard_ID < 1) 
			set_Value (COLUMNNAME_HL7_Dashboard_ID, null);
		else 
			set_Value (COLUMNNAME_HL7_Dashboard_ID, Integer.valueOf(HL7_Dashboard_ID));
	}

	/** Get HL7 Dashboard.
		@return HL7 Dashboard	  */
	public int getHL7_Dashboard_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Dashboard_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generated.
		@param IsGenerated 
		This Line is generated
	  */
	public void setIsGenerated (boolean IsGenerated)
	{
		set_Value (COLUMNNAME_IsGenerated, Boolean.valueOf(IsGenerated));
	}

	/** Get Generated.
		@return This Line is generated
	  */
	public boolean isGenerated () 
	{
		Object oo = get_Value(COLUMNNAME_IsGenerated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Start Date.
		@param StartDate 
		Start Date of Claim Report
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return Start Date of Claim Report
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Status AD_Reference_ID=1200598 */
	public static final int STATUS_AD_Reference_ID=1200598;
	/** Accepted = A */
	public static final String STATUS_Accepted = "A";
	/** Accepted Errors = E */
	public static final String STATUS_AcceptedErrors = "E";
	/** Rejected MAC = M */
	public static final String STATUS_RejectedMAC = "M";
	/** Rejected = R */
	public static final String STATUS_Rejected = "R";
	/** Rejected Assurance = W */
	public static final String STATUS_RejectedAssurance = "W";
	/** Rejected Analyzed = X */
	public static final String STATUS_RejectedAnalyzed = "X";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		if (Status == null || Status.equals("A") || Status.equals("E") || Status.equals("M") || Status.equals("R") || Status.equals("W") || Status.equals("X")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=1200598 - A - E - M - R - W - X");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}
}