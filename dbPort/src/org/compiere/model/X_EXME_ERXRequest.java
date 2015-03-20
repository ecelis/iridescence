/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ERXRequest
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ERXRequest extends PO implements I_EXME_ERXRequest, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ERXRequest (Properties ctx, int EXME_ERXRequest_ID, String trxName)
    {
      super (ctx, EXME_ERXRequest_ID, trxName);
      /** if (EXME_ERXRequest_ID == 0)
        {
			setEXME_ERXRequest_ID (0);
			setEXME_PrescRXDet_ID (0);
			setRequestType (null);
			setStatus (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ERXRequest (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ERXRequest[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AckMsg AD_Reference_ID=1200591 */
	public static final int ACKMSG_AD_Reference_ID=1200591;
	/** Not Sent = 0 */
	public static final String ACKMSG_NotSent = "0";
	/** Error = 1 */
	public static final String ACKMSG_Error = "1";
	/** Sent = 2 */
	public static final String ACKMSG_Sent = "2";
	/** Refill Request = 3 */
	public static final String ACKMSG_RefillRequest = "3";
	/** Change Request = 4 */
	public static final String ACKMSG_ChangeRequest = "4";
	/** Prescription to Follow = 5 */
	public static final String ACKMSG_PrescriptionToFollow = "5";
	/** Free Standing Error = 6 */
	public static final String ACKMSG_FreeStandingError = "6";
	/** Verify = 7 */
	public static final String ACKMSG_Verify = "7";
	/** Set AckMsg.
		@param AckMsg AckMsg	  */
	public void setAckMsg (String AckMsg)
	{

		if (AckMsg == null || AckMsg.equals("0") || AckMsg.equals("1") || AckMsg.equals("2") || AckMsg.equals("3") || AckMsg.equals("4") || AckMsg.equals("5") || AckMsg.equals("6") || AckMsg.equals("7")); else throw new IllegalArgumentException ("AckMsg Invalid value - " + AckMsg + " - Reference_ID=1200591 - 0 - 1 - 2 - 3 - 4 - 5 - 6 - 7");		set_Value (COLUMNNAME_AckMsg, AckMsg);
	}

	/** Get AckMsg.
		@return AckMsg	  */
	public String getAckMsg () 
	{
		return (String)get_Value(COLUMNNAME_AckMsg);
	}

	/** Set ePrescribing Request Message.
		@param EXME_ERXRequest_ID ePrescribing Request Message	  */
	public void setEXME_ERXRequest_ID (int EXME_ERXRequest_ID)
	{
		if (EXME_ERXRequest_ID < 1)
			 throw new IllegalArgumentException ("EXME_ERXRequest_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ERXRequest_ID, Integer.valueOf(EXME_ERXRequest_ID));
	}

	/** Get ePrescribing Request Message.
		@return ePrescribing Request Message	  */
	public int getEXME_ERXRequest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ERXRequest_ID);
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

	public I_EXME_PrescRXDet getEXME_PrescRXDet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PrescRXDet.Table_Name);
        I_EXME_PrescRXDet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PrescRXDet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PrescRXDet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set RXNorm Prescription Detail.
		@param EXME_PrescRXDet_ID 
		RXNorm Prescription Detail
	  */
	public void setEXME_PrescRXDet_ID (int EXME_PrescRXDet_ID)
	{
		if (EXME_PrescRXDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescRXDet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PrescRXDet_ID, Integer.valueOf(EXME_PrescRXDet_ID));
	}

	/** Get RXNorm Prescription Detail.
		@return RXNorm Prescription Detail
	  */
	public int getEXME_PrescRXDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescRXDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Message.
		@param Message 
		EMail Message
	  */
	public void setMessage (String Message)
	{
		set_Value (COLUMNNAME_Message, Message);
	}

	/** Get Message.
		@return EMail Message
	  */
	public String getMessage () 
	{
		return (String)get_Value(COLUMNNAME_Message);
	}

	/** Set Notes.
		@param Notes Notes	  */
	public void setNotes (String Notes)
	{
		set_Value (COLUMNNAME_Notes, Notes);
	}

	/** Get Notes.
		@return Notes	  */
	public String getNotes () 
	{
		return (String)get_Value(COLUMNNAME_Notes);
	}

	/** ReasonCode AD_Reference_ID=1200607 */
	public static final int REASONCODE_AD_Reference_ID=1200607;
	/** Patient unknown to the provider = AA */
	public static final String REASONCODE_PatientUnknownToTheProvider = "AA";
	/** Patient never under provider care = AB */
	public static final String REASONCODE_PatientNeverUnderProviderCare = "AB";
	/** Patient no longer under provider care = AC */
	public static final String REASONCODE_PatientNoLongerUnderProviderCare = "AC";
	/** Refill too soon = AD */
	public static final String REASONCODE_RefillTooSoon = "AD";
	/** Medication never prescribed for patient = AE */
	public static final String REASONCODE_MedicationNeverPrescribedForPatient = "AE";
	/** Patient should contact provider = AF */
	public static final String REASONCODE_PatientShouldContactProvider = "AF";
	/** Refill not appropriate = AG */
	public static final String REASONCODE_RefillNotAppropriate = "AG";
	/** Patient has picked up prescription = AH */
	public static final String REASONCODE_PatientHasPickedUpPrescription = "AH";
	/** Patient has picked up partial fill of prescription = AJ */
	public static final String REASONCODE_PatientHasPickedUpPartialFillOfPrescription = "AJ";
	/** Patient has not picked up prescription, drug returned t = AK */
	public static final String REASONCODE_PatientHasNotPickedUpPrescriptionDrugReturnedT = "AK";
	/** Change not appropriate = AL */
	public static final String REASONCODE_ChangeNotAppropriate = "AL";
	/** Patient needs appointment = AM */
	public static final String REASONCODE_PatientNeedsAppointment = "AM";
	/** Prescriber not associated with this practice or locatio = AN */
	public static final String REASONCODE_PrescriberNotAssociatedWithThisPracticeOrLocatio = "AN";
	/** No attempt will be made to obtain Prior Authorization = AO */
	public static final String REASONCODE_NoAttemptWillBeMadeToObtainPriorAuthorization = "AO";
	/** Request already responded = AP */
	public static final String REASONCODE_RequestAlreadyResponded = "AP";
	/** Set Reason Code.
		@param ReasonCode Reason Code	  */
	public void setReasonCode (String ReasonCode)
	{

		if (ReasonCode == null || ReasonCode.equals("AA") || ReasonCode.equals("AB") || ReasonCode.equals("AC") || ReasonCode.equals("AD") || ReasonCode.equals("AE") || ReasonCode.equals("AF") || ReasonCode.equals("AG") || ReasonCode.equals("AH") || ReasonCode.equals("AJ") || ReasonCode.equals("AK") || ReasonCode.equals("AL") || ReasonCode.equals("AM") || ReasonCode.equals("AN") || ReasonCode.equals("AO") || ReasonCode.equals("AP")); else throw new IllegalArgumentException ("ReasonCode Invalid value - " + ReasonCode + " - Reference_ID=1200607 - AA - AB - AC - AD - AE - AF - AG - AH - AJ - AK - AL - AM - AN - AO - AP");		set_Value (COLUMNNAME_ReasonCode, ReasonCode);
	}

	/** Get Reason Code.
		@return Reason Code	  */
	public String getReasonCode () 
	{
		return (String)get_Value(COLUMNNAME_ReasonCode);
	}

	/** Set RefillDisp.
		@param RefillDisp RefillDisp	  */
	public void setRefillDisp (int RefillDisp)
	{
		set_Value (COLUMNNAME_RefillDisp, Integer.valueOf(RefillDisp));
	}

	/** Get RefillDisp.
		@return RefillDisp	  */
	public int getRefillDisp () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RefillDisp);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set RefillPresc.
		@param RefillPresc RefillPresc	  */
	public void setRefillPresc (int RefillPresc)
	{
		set_Value (COLUMNNAME_RefillPresc, Integer.valueOf(RefillPresc));
	}

	/** Get RefillPresc.
		@return RefillPresc	  */
	public int getRefillPresc () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RefillPresc);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** RequestType AD_Reference_ID=1200526 */
	public static final int REQUESTTYPE_AD_Reference_ID=1200526;
	/** Cancel = 3 */
	public static final String REQUESTTYPE_Cancel = "3";
	/** Refill = 1 */
	public static final String REQUESTTYPE_Refill = "1";
	/** Change = 2 */
	public static final String REQUESTTYPE_Change = "2";
	/** None = 0 */
	public static final String REQUESTTYPE_None = "0";
	/** Error = 4 */
	public static final String REQUESTTYPE_Error = "4";
	/** Verify = 5 */
	public static final String REQUESTTYPE_Verify = "5";
	/** FreeStandingError = 6 */
	public static final String REQUESTTYPE_FreeStandingError = "6";
	/** Set Request Type.
		@param RequestType Request Type	  */
	public void setRequestType (String RequestType)
	{
		if (RequestType == null) throw new IllegalArgumentException ("RequestType is mandatory");
		if (RequestType.equals("3") || RequestType.equals("1") || RequestType.equals("2") || RequestType.equals("0") || RequestType.equals("4") || RequestType.equals("5") || RequestType.equals("6")); else throw new IllegalArgumentException ("RequestType Invalid value - " + RequestType + " - Reference_ID=1200526 - 3 - 1 - 2 - 0 - 4 - 5 - 6");		set_Value (COLUMNNAME_RequestType, RequestType);
	}

	/** Get Request Type.
		@return Request Type	  */
	public String getRequestType () 
	{
		return (String)get_Value(COLUMNNAME_RequestType);
	}

	/** Status AD_Reference_ID=1200608 */
	public static final int STATUS_AD_Reference_ID=1200608;
	/** Approved = A */
	public static final String STATUS_Approved = "A";
	/** ApprovedWithChanges = C */
	public static final String STATUS_ApprovedWithChanges = "C";
	/** Denied = D */
	public static final String STATUS_Denied = "D";
	/** DeniedNewRxToFollow = N */
	public static final String STATUS_DeniedNewRxToFollow = "N";
	/** Pending = P */
	public static final String STATUS_Pending = "P";
	/** Free Standing Error = E */
	public static final String STATUS_FreeStandingError = "E";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		if (Status == null) throw new IllegalArgumentException ("Status is mandatory");
		if (Status.equals("A") || Status.equals("C") || Status.equals("D") || Status.equals("N") || Status.equals("P") || Status.equals("E")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=1200608 - A - C - D - N - P - E");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}