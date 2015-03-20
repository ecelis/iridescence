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

/** Generated Model for EXME_ClaimLog
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ClaimLog extends PO implements I_EXME_ClaimLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ClaimLog (Properties ctx, int EXME_ClaimLog_ID, String trxName)
    {
      super (ctx, EXME_ClaimLog_ID, trxName);
      /** if (EXME_ClaimLog_ID == 0)
        {
			setC_BPartner_ID (0);
			setDate_Submitted (new Timestamp( System.currentTimeMillis() ));
			setEXME_ClaimLog_ID (0);
			setEXME_CtaPac_ID (0);
			setHL7_Dashboard_ID (0);
			setStatus (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ClaimLog (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ClaimLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Attachment getAD_Attachment() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Attachment.Table_Name);
        I_AD_Attachment result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Attachment)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Attachment_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Attachment.
		@param AD_Attachment_ID 
		Attachment for the document
	  */
	public void setAD_Attachment_ID (int AD_Attachment_ID)
	{
		if (AD_Attachment_ID < 1) 
			set_Value (COLUMNNAME_AD_Attachment_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Attachment_ID, Integer.valueOf(AD_Attachment_ID));
	}

	/** Get Attachment.
		@return Attachment for the document
	  */
	public int getAD_Attachment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Attachment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
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
	/** Inpatient Claim = N */
	public static final String CONFTYPE_InpatientClaim = "N";
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

	/** Set Date Submitted.
		@param Date_Submitted 
		Date the claim message was submitted
	  */
	public void setDate_Submitted (Timestamp Date_Submitted)
	{
		if (Date_Submitted == null)
			throw new IllegalArgumentException ("Date_Submitted is mandatory.");
		set_Value (COLUMNNAME_Date_Submitted, Date_Submitted);
	}

	/** Get Date Submitted.
		@return Date the claim message was submitted
	  */
	public Timestamp getDate_Submitted () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date_Submitted);
	}

	/** Set EXME_CLAIMLOG_ID.
		@param EXME_ClaimLog_ID EXME_CLAIMLOG_ID	  */
	public void setEXME_ClaimLog_ID (int EXME_ClaimLog_ID)
	{
		if (EXME_ClaimLog_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClaimLog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ClaimLog_ID, Integer.valueOf(EXME_ClaimLog_ID));
	}

	/** Get EXME_CLAIMLOG_ID.
		@return EXME_CLAIMLOG_ID	  */
	public int getEXME_ClaimLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPacExt.Table_Name);
        I_EXME_CtaPacExt result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPacExt)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPacExt_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter Extension.
		@param EXME_CtaPacExt_ID 
		Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID)
	{
		if (EXME_CtaPacExt_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPacExt_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPacExt_ID, Integer.valueOf(EXME_CtaPacExt_ID));
	}

	/** Get Encounter Extension.
		@return Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacExt_ID);
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
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
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
			 throw new IllegalArgumentException ("HL7_Dashboard_ID is mandatory.");
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

	/** Status AD_Reference_ID=1200567 */
	public static final int STATUS_AD_Reference_ID=1200567;
	/** A = A */
	public static final String STATUS_A = "A";
	/** E = E */
	public static final String STATUS_E = "E";
	/** M = M */
	public static final String STATUS_M = "M";
	/** R = R */
	public static final String STATUS_R = "R";
	/** W = W */
	public static final String STATUS_W = "W";
	/** X = X */
	public static final String STATUS_X = "X";
	/** P = P */
	public static final String STATUS_P = "P";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		if (Status == null) throw new IllegalArgumentException ("Status is mandatory");
		if (Status.equals("A") || Status.equals("E") || Status.equals("M") || Status.equals("R") || Status.equals("W") || Status.equals("X") || Status.equals("P")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=1200567 - A - E - M - R - W - X - P");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}
}