/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_CtaPac
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CtaPac extends PO implements I_EXME_CtaPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPac (Properties ctx, int EXME_CtaPac_ID, String trxName)
    {
      super (ctx, EXME_CtaPac_ID, trxName);
      /** if (EXME_CtaPac_ID == 0)
        {
			setC_Currency_ID (0);
			setC_DocType_ID (0);
			setC_DocTypeTarget_ID (0);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setEstatus (null);
// A
			setEXME_CtaPac_ID (0);
			setEXME_Especialidad_ID (0);
			setEXME_Medico_ID (0);
			setEXME_Paciente_ID (0);
			setGrandTotal (Env.ZERO);
// 0
			setImpBrazalete (null);
			setImpConsent (null);
			setImpContrato (null);
			setImpDatos (null);
			setIsApproved (false);
// N
			setIsCreditApproved (false);
// N
			setIsFactEspec (false);
			setIsInvoiced (false);
// N
			setIsPrinted (false);
// N
			setM_PriceList_ID (0);
			setPriorityRule (null);
// 5
			setProcessed (false);
// N
			setTipoArea (null);
			setValidOnQueue (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPac (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Updated by release.
		@param ActualizadoAlta 
		Who updated the status of patient account
	  */
	public void setActualizadoAlta (int ActualizadoAlta)
	{
		set_Value (COLUMNNAME_ActualizadoAlta, Integer.valueOf(ActualizadoAlta));
	}

	/** Get Updated by release.
		@return Who updated the status of patient account
	  */
	public int getActualizadoAlta () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ActualizadoAlta);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Predischarge Update.
		@param ActualizadoPrealta Predischarge Update	  */
	public void setActualizadoPrealta (int ActualizadoPrealta)
	{
		set_Value (COLUMNNAME_ActualizadoPrealta, Integer.valueOf(ActualizadoPrealta));
	}

	/** Get Predischarge Update.
		@return Predischarge Update	  */
	public int getActualizadoPrealta () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ActualizadoPrealta);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Print Format.
		@param AD_PrintFormat_ID 
		Data Print Format
	  */
	public void setAD_PrintFormat_ID (int AD_PrintFormat_ID)
	{
		if (AD_PrintFormat_ID < 1) 
			set_Value (COLUMNNAME_AD_PrintFormat_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PrintFormat_ID, Integer.valueOf(AD_PrintFormat_ID));
	}

	/** Get Print Format.
		@return Data Print Format
	  */
	public int getAD_PrintFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set File.
		@param Archivo File	  */
	public void setArchivo (byte[] Archivo)
	{
		set_Value (COLUMNNAME_Archivo, Archivo);
	}

	/** Get File.
		@return File	  */
	public byte[] getArchivo () 
	{
		return (byte[])get_Value(COLUMNNAME_Archivo);
	}

	/** Set Bill Date.
		@param BillDate 
		Date when the encounter has been billed
	  */
	public void setBillDate (Timestamp BillDate)
	{
		set_Value (COLUMNNAME_BillDate, BillDate);
	}

	/** Get Bill Date.
		@return Date when the encounter has been billed
	  */
	public Timestamp getBillDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_BillDate);
	}

	/** BillingStatus AD_Reference_ID=1200534 */
	public static final int BILLINGSTATUS_AD_Reference_ID=1200534;
	/** Ready to Bill = RE */
	public static final String BILLINGSTATUS_ReadyToBill = "RE";
	/** Draft = DR */
	public static final String BILLINGSTATUS_Draft = "DR";
	/** Billed = BI */
	public static final String BILLINGSTATUS_Billed = "BI";
	/** Close = CL */
	public static final String BILLINGSTATUS_Close = "CL";
	/** Bill To First Insurance = BI1 */
	public static final String BILLINGSTATUS_BillToFirstInsurance = "BI1";
	/** Bill To Second Insurance = BI2 */
	public static final String BILLINGSTATUS_BillToSecondInsurance = "BI2";
	/** Bill To Third Insurance = BI3 */
	public static final String BILLINGSTATUS_BillToThirdInsurance = "BI3";
	/** Bill To Patient = BIP */
	public static final String BILLINGSTATUS_BillToPatient = "BIP";
	/** Bill To Responsible = BIR */
	public static final String BILLINGSTATUS_BillToResponsible = "BIR";
	/** Message 835 = 8 */
	public static final String BILLINGSTATUS_Message835 = "8";
	/** Mandatory Fields = M */
	public static final String BILLINGSTATUS_MandatoryFields = "M";
	/** Set BillingStatus.
		@param BillingStatus BillingStatus	  */
	public void setBillingStatus (String BillingStatus)
	{

		if (BillingStatus == null || BillingStatus.equals("RE") || BillingStatus.equals("DR") || BillingStatus.equals("BI") || BillingStatus.equals("CL") || BillingStatus.equals("BI1") || BillingStatus.equals("BI2") || BillingStatus.equals("BI3") || BillingStatus.equals("BIP") || BillingStatus.equals("BIR") || BillingStatus.equals("8") || BillingStatus.equals("M")); else throw new IllegalArgumentException ("BillingStatus Invalid value - " + BillingStatus + " - Reference_ID=1200534 - RE - DR - BI - CL - BI1 - BI2 - BI3 - BIP - BIR - 8 - M");		set_Value (COLUMNNAME_BillingStatus, BillingStatus);
	}

	/** Get BillingStatus.
		@return BillingStatus	  */
	public String getBillingStatus () 
	{
		return (String)get_Value(COLUMNNAME_BillingStatus);
	}

	/** BillingType AD_Reference_ID=1200639 */
	public static final int BILLINGTYPE_AD_Reference_ID=1200639;
	/** Internal = I */
	public static final String BILLINGTYPE_Internal = "I";
	/** External = E */
	public static final String BILLINGTYPE_External = "E";
	/** Set Billing type.
		@param BillingType Billing type	  */
	public void setBillingType (String BillingType)
	{

		if (BillingType == null || BillingType.equals("I") || BillingType.equals("E")); else throw new IllegalArgumentException ("BillingType Invalid value - " + BillingType + " - Reference_ID=1200639 - I - E");		set_Value (COLUMNNAME_BillingType, BillingType);
	}

	/** Get Billing type.
		@return Billing type	  */
	public String getBillingType () 
	{
		return (String)get_Value(COLUMNNAME_BillingType);
	}

	/** Set Responsible Units.
		@param C_Activity_ID 
		Responsible Units
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Responsible Units.
		@return Responsible Units
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Cases.
		@param CasosMedicos Medical Cases	  */
	public void setCasosMedicos (boolean CasosMedicos)
	{
		set_Value (COLUMNNAME_CasosMedicos, Boolean.valueOf(CasosMedicos));
	}

	/** Get Medical Cases.
		@return Medical Cases	  */
	public boolean isCasosMedicos () 
	{
		Object oo = get_Value(COLUMNNAME_CasosMedicos);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Program.
		@param C_Campaign_ID 
		Program
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Program.
		@return Program
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1)
			 throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
		set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0)
			 throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
		set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Target Document Type.
		@param C_DocTypeTarget_ID 
		Target document type for conversing documents
	  */
	public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID)
	{
		if (C_DocTypeTarget_ID < 1)
			 throw new IllegalArgumentException ("C_DocTypeTarget_ID is mandatory.");
		set_Value (COLUMNNAME_C_DocTypeTarget_ID, Integer.valueOf(C_DocTypeTarget_ID));
	}

	/** Get Target Document Type.
		@return Target document type for conversing documents
	  */
	public int getC_DocTypeTarget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypeTarget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Coded.
		@param Coded Coded	  */
	public void setCoded (boolean Coded)
	{
		set_Value (COLUMNNAME_Coded, Boolean.valueOf(Coded));
	}

	/** Get Coded.
		@return Coded	  */
	public boolean isCoded () 
	{
		Object oo = get_Value(COLUMNNAME_Coded);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Professional Coded.
		@param CodedProf Professional Coded	  */
	public void setCodedProf (boolean CodedProf)
	{
		set_Value (COLUMNNAME_CodedProf, Boolean.valueOf(CodedProf));
	}

	/** Get Professional Coded.
		@return Professional Coded	  */
	public boolean isCodedProf () 
	{
		Object oo = get_Value(COLUMNNAME_CodedProf);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Coding Date.
		@param CodingDate Coding Date	  */
	public void setCodingDate (Timestamp CodingDate)
	{
		set_Value (COLUMNNAME_CodingDate, CodingDate);
	}

	/** Get Coding Date.
		@return Coding Date	  */
	public Timestamp getCodingDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CodingDate);
	}

	/** Set Professional Coding Date.
		@param CodingDateProf Professional Coding Date	  */
	public void setCodingDateProf (Timestamp CodingDateProf)
	{
		set_Value (COLUMNNAME_CodingDateProf, CodingDateProf);
	}

	/** Get Professional Coding Date.
		@return Professional Coding Date	  */
	public Timestamp getCodingDateProf () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CodingDateProf);
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		if (DateAcct == null)
			throw new IllegalArgumentException ("DateAcct is mandatory.");
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		if (DateOrdered == null)
			throw new IllegalArgumentException ("DateOrdered is mandatory.");
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Date printed.
		@param DatePrinted 
		Date the document was printed.
	  */
	public void setDatePrinted (Timestamp DatePrinted)
	{
		set_Value (COLUMNNAME_DatePrinted, DatePrinted);
	}

	/** Get Date printed.
		@return Date the document was printed.
	  */
	public Timestamp getDatePrinted () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePrinted);
	}

	/** Set DepartureDate.
		@param DepartureDate DepartureDate	  */
	public void setDepartureDate (Timestamp DepartureDate)
	{
		set_Value (COLUMNNAME_DepartureDate, DepartureDate);
	}

	/** Get DepartureDate.
		@return DepartureDate	  */
	public Timestamp getDepartureDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DepartureDate);
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

	/** Set Diagnostico_Ingreso.
		@param Diagnostico_Ingreso Diagnostico_Ingreso	  */
	public void setDiagnostico_Ingreso (String Diagnostico_Ingreso)
	{
		set_Value (COLUMNNAME_Diagnostico_Ingreso, Diagnostico_Ingreso);
	}

	/** Get Diagnostico_Ingreso.
		@return Diagnostico_Ingreso	  */
	public String getDiagnostico_Ingreso () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico_Ingreso);
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
		if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");
		if (DocAction.equals("CO") || DocAction.equals("AP") || DocAction.equals("RJ") || DocAction.equals("PO") || DocAction.equals("VO") || DocAction.equals("CL") || DocAction.equals("RC") || DocAction.equals("RA") || DocAction.equals("IN") || DocAction.equals("RE") || DocAction.equals("--") || DocAction.equals("PR") || DocAction.equals("XL") || DocAction.equals("WC")); else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - CO - AP - RJ - PO - VO - CL - RC - RA - IN - RE - -- - PR - XL - WC");		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
		if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
		if (DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("IP") || DocStatus.equals("WP") || DocStatus.equals("WC")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set DRG.
		@param DRG DRG	  */
	public void setDRG (BigDecimal DRG)
	{
		set_Value (COLUMNNAME_DRG, DRG);
	}

	/** Get DRG.
		@return DRG	  */
	public BigDecimal getDRG () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DRG);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** EncounterStatus AD_Reference_ID=1200542 */
	public static final int ENCOUNTERSTATUS_AD_Reference_ID=1200542;
	/** Pre Admission = A */
	public static final String ENCOUNTERSTATUS_PreAdmission = "A";
	/** Admission = I */
	public static final String ENCOUNTERSTATUS_Admission = "I";
	/** Predischarge = P */
	public static final String ENCOUNTERSTATUS_Predischarge = "P";
	/** Discharge = D */
	public static final String ENCOUNTERSTATUS_Discharge = "D";
	/** Abstracting = B */
	public static final String ENCOUNTERSTATUS_Abstracting = "B";
	/** Ready To Bill = R */
	public static final String ENCOUNTERSTATUS_ReadyToBill = "R";
	/** Billing = F */
	public static final String ENCOUNTERSTATUS_Billing = "F";
	/** Waiting Message 835 = W */
	public static final String ENCOUNTERSTATUS_WaitingMessage835 = "W";
	/** Error, Mandatory Fields = M */
	public static final String ENCOUNTERSTATUS_ErrorMandatoryFields = "M";
	/** OK, Mandatory Fields = C */
	public static final String ENCOUNTERSTATUS_OKMandatoryFields = "C";
	/** Error, Message 997 = 9 */
	public static final String ENCOUNTERSTATUS_ErrorMessage997 = "9";
	/** Ok, Message 997 = O */
	public static final String ENCOUNTERSTATUS_OkMessage997 = "O";
	/** Error, RSP File = E */
	public static final String ENCOUNTERSTATUS_ErrorRSPFile = "E";
	/** Ok, RSP File = K */
	public static final String ENCOUNTERSTATUS_OkRSPFile = "K";
	/** Waiting 835, First Insurance = X */
	public static final String ENCOUNTERSTATUS_Waiting835FirstInsurance = "X";
	/** Waiting 835, Second Insurance = Y */
	public static final String ENCOUNTERSTATUS_Waiting835SecondInsurance = "Y";
	/** Waiting 835, Third Insurance = Z */
	public static final String ENCOUNTERSTATUS_Waiting835ThirdInsurance = "Z";
	/** 835 Received, First Insurance = 1 */
	public static final String ENCOUNTERSTATUS_835ReceivedFirstInsurance = "1";
	/** 835 Received, Second Insurance = 2 */
	public static final String ENCOUNTERSTATUS_835ReceivedSecondInsurance = "2";
	/** 835 Received, Third Insurance = 3 */
	public static final String ENCOUNTERSTATUS_835ReceivedThirdInsurance = "3";
	/** Waiting Patient Payments = G */
	public static final String ENCOUNTERSTATUS_WaitingPatientPayments = "G";
	/** Close = L */
	public static final String ENCOUNTERSTATUS_Close = "L";
	/** Returned to Abstracting = N */
	public static final String ENCOUNTERSTATUS_ReturnedToAbstracting = "N";
	/** Balanced = Q */
	public static final String ENCOUNTERSTATUS_Balanced = "Q";
	/** Set Encounter Status.
		@param EncounterStatus Encounter Status	  */
	public void setEncounterStatus (String EncounterStatus)
	{

		if (EncounterStatus == null || EncounterStatus.equals("A") || EncounterStatus.equals("I") || EncounterStatus.equals("P") || EncounterStatus.equals("D") || EncounterStatus.equals("B") || EncounterStatus.equals("R") || EncounterStatus.equals("F") || EncounterStatus.equals("W") || EncounterStatus.equals("M") || EncounterStatus.equals("C") || EncounterStatus.equals("9") || EncounterStatus.equals("O") || EncounterStatus.equals("E") || EncounterStatus.equals("K") || EncounterStatus.equals("X") || EncounterStatus.equals("Y") || EncounterStatus.equals("Z") || EncounterStatus.equals("1") || EncounterStatus.equals("2") || EncounterStatus.equals("3") || EncounterStatus.equals("G") || EncounterStatus.equals("L") || EncounterStatus.equals("N") || EncounterStatus.equals("Q")); else throw new IllegalArgumentException ("EncounterStatus Invalid value - " + EncounterStatus + " - Reference_ID=1200542 - A - I - P - D - B - R - F - W - M - C - 9 - O - E - K - X - Y - Z - 1 - 2 - 3 - G - L - N - Q");		set_Value (COLUMNNAME_EncounterStatus, EncounterStatus);
	}

	/** Get Encounter Status.
		@return Encounter Status	  */
	public String getEncounterStatus () 
	{
		return (String)get_Value(COLUMNNAME_EncounterStatus);
	}

	/** Estatus AD_Reference_ID=1000099 */
	public static final int ESTATUS_AD_Reference_ID=1000099;
	/** Reserved = R */
	public static final String ESTATUS_Reserved = "R";
	/** Open = A */
	public static final String ESTATUS_Open = "A";
	/** Closed = C */
	public static final String ESTATUS_Closed = "C";
	/** Invoiced = F */
	public static final String ESTATUS_Invoiced = "F";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		if (Estatus == null) throw new IllegalArgumentException ("Estatus is mandatory");
		if (Estatus.equals("R") || Estatus.equals("A") || Estatus.equals("C") || Estatus.equals("F")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1000099 - R - A - C - F");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	public I_EXME_AccompaniedBy getEXME_AccompaniedBy() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_AccompaniedBy.Table_Name);
        I_EXME_AccompaniedBy result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_AccompaniedBy)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_AccompaniedBy_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Accompanied by.
		@param EXME_AccompaniedBy_ID Accompanied by	  */
	public void setEXME_AccompaniedBy_ID (int EXME_AccompaniedBy_ID)
	{
		if (EXME_AccompaniedBy_ID < 1) 
			set_Value (COLUMNNAME_EXME_AccompaniedBy_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AccompaniedBy_ID, Integer.valueOf(EXME_AccompaniedBy_ID));
	}

	/** Get Accompanied by.
		@return Accompanied by	  */
	public int getEXME_AccompaniedBy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AccompaniedBy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_AdmitSource getEXME_AdmitSource() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_AdmitSource.Table_Name);
        I_EXME_AdmitSource result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_AdmitSource)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_AdmitSource_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Admit Source.
		@param EXME_AdmitSource_ID 
		Admit Source of the patient account
	  */
	public void setEXME_AdmitSource_ID (int EXME_AdmitSource_ID)
	{
		if (EXME_AdmitSource_ID < 1) 
			set_Value (COLUMNNAME_EXME_AdmitSource_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AdmitSource_ID, Integer.valueOf(EXME_AdmitSource_ID));
	}

	/** Get Admit Source.
		@return Admit Source of the patient account
	  */
	public int getEXME_AdmitSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AdmitSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_AdmitType getEXME_AdmitType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_AdmitType.Table_Name);
        I_EXME_AdmitType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_AdmitType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_AdmitType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Admit Type.
		@param EXME_AdmitType_ID 
		Admit Type of the Patient account
	  */
	public void setEXME_AdmitType_ID (int EXME_AdmitType_ID)
	{
		if (EXME_AdmitType_ID < 1) 
			set_Value (COLUMNNAME_EXME_AdmitType_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AdmitType_ID, Integer.valueOf(EXME_AdmitType_ID));
	}

	/** Get Admit Type.
		@return Admit Type of the Patient account
	  */
	public int getEXME_AdmitType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AdmitType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Area getEXME_Area() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Area.Table_Name);
        I_EXME_Area result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Area)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Area_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service.
		@param EXME_Area_ID 
		Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1) 
			set_Value (COLUMNNAME_EXME_Area_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Service.
		@return Service
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ArrivalMode getEXME_ArrivalMode() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ArrivalMode.Table_Name);
        I_EXME_ArrivalMode result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ArrivalMode)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ArrivalMode_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Arrival Mode.
		@param EXME_ArrivalMode_ID 
		Arrival Mode of the patient account
	  */
	public void setEXME_ArrivalMode_ID (int EXME_ArrivalMode_ID)
	{
		if (EXME_ArrivalMode_ID < 1) 
			set_Value (COLUMNNAME_EXME_ArrivalMode_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ArrivalMode_ID, Integer.valueOf(EXME_ArrivalMode_ID));
	}

	/** Get Arrival Mode.
		@return Arrival Mode of the patient account
	  */
	public int getEXME_ArrivalMode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ArrivalMode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_BeneficiosH getEXME_BeneficiosH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_BeneficiosH.Table_Name);
        I_EXME_BeneficiosH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_BeneficiosH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_BeneficiosH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_BeneficiosH_ID.
		@param EXME_BeneficiosH_ID EXME_BeneficiosH_ID	  */
	public void setEXME_BeneficiosH_ID (int EXME_BeneficiosH_ID)
	{
		if (EXME_BeneficiosH_ID < 1) 
			set_Value (COLUMNNAME_EXME_BeneficiosH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_BeneficiosH_ID, Integer.valueOf(EXME_BeneficiosH_ID));
	}

	/** Get EXME_BeneficiosH_ID.
		@return EXME_BeneficiosH_ID	  */
	public int getEXME_BeneficiosH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BeneficiosH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bed.
		@param EXME_Cama_ID 
		Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID)
	{
		if (EXME_Cama_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_Cama_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_Cama_ID, Integer.valueOf(EXME_Cama_ID));
	}

	/** Get Bed.
		@return Bed
	  */
	public int getEXME_Cama_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient's Charge Bed.
		@param EXME_CamaIng_ID Patient's Charge Bed	  */
	public void setEXME_CamaIng_ID (int EXME_CamaIng_ID)
	{
		if (EXME_CamaIng_ID < 1) 
			set_Value (COLUMNNAME_EXME_CamaIng_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CamaIng_ID, Integer.valueOf(EXME_CamaIng_ID));
	}

	/** Get Patient's Charge Bed.
		@return Patient's Charge Bed	  */
	public int getEXME_CamaIng_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CamaIng_ID);
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
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

	/** Set Referring Encounter.
		@param EXME_CtaPacRefer_ID Referring Encounter	  */
	public void setEXME_CtaPacRefer_ID (int EXME_CtaPacRefer_ID)
	{
		if (EXME_CtaPacRefer_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPacRefer_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPacRefer_ID, Integer.valueOf(EXME_CtaPacRefer_ID));
	}

	/** Get Referring Encounter.
		@return Referring Encounter	  */
	public int getEXME_CtaPacRefer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacRefer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_DescPrecioFijo getEXME_DescPrecioFijo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_DescPrecioFijo.Table_Name);
        I_EXME_DescPrecioFijo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_DescPrecioFijo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_DescPrecioFijo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Discount for a fixed billing Price.
		@param EXME_DescPrecioFijo_ID 
		Discount for a fixed billing Price
	  */
	public void setEXME_DescPrecioFijo_ID (int EXME_DescPrecioFijo_ID)
	{
		if (EXME_DescPrecioFijo_ID < 1) 
			set_Value (COLUMNNAME_EXME_DescPrecioFijo_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DescPrecioFijo_ID, Integer.valueOf(EXME_DescPrecioFijo_ID));
	}

	/** Get Discount for a fixed billing Price.
		@return Discount for a fixed billing Price
	  */
	public int getEXME_DescPrecioFijo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DescPrecioFijo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Discharged_Via getEXME_Discharged_Via() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Discharged_Via.Table_Name);
        I_EXME_Discharged_Via result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Discharged_Via)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Discharged_Via_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Discharged Via.
		@param EXME_Discharged_Via_ID Discharged Via	  */
	public void setEXME_Discharged_Via_ID (int EXME_Discharged_Via_ID)
	{
		if (EXME_Discharged_Via_ID < 1) 
			set_Value (COLUMNNAME_EXME_Discharged_Via_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Discharged_Via_ID, Integer.valueOf(EXME_Discharged_Via_ID));
	}

	/** Get Discharged Via.
		@return Discharged Via	  */
	public int getEXME_Discharged_Via_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Discharged_Via_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_DischargeStatus getEXME_DischargeStatus() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_DischargeStatus.Table_Name);
        I_EXME_DischargeStatus result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_DischargeStatus)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_DischargeStatus_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Disposition.
		@param EXME_DischargeStatus_ID 
		Discharge Status
	  */
	public void setEXME_DischargeStatus_ID (int EXME_DischargeStatus_ID)
	{
		if (EXME_DischargeStatus_ID < 1) 
			set_Value (COLUMNNAME_EXME_DischargeStatus_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DischargeStatus_ID, Integer.valueOf(EXME_DischargeStatus_ID));
	}

	/** Get Disposition.
		@return Discharge Status
	  */
	public int getEXME_DischargeStatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DischargeStatus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_DRG getEXME_DRG() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_DRG.Table_Name);
        I_EXME_DRG result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_DRG)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_DRG_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set DRG Code ID.
		@param EXME_DRG_ID DRG Code ID	  */
	public void setEXME_DRG_ID (int EXME_DRG_ID)
	{
		if (EXME_DRG_ID < 1) 
			set_Value (COLUMNNAME_EXME_DRG_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DRG_ID, Integer.valueOf(EXME_DRG_ID));
	}

	/** Get DRG Code ID.
		@return DRG Code ID	  */
	public int getEXME_DRG_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DRG_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Specialty.
		@param EXME_Especialidad2_ID Specialty	  */
	public void setEXME_Especialidad2_ID (int EXME_Especialidad2_ID)
	{
		if (EXME_Especialidad2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad2_ID, Integer.valueOf(EXME_Especialidad2_ID));
	}

	/** Get Specialty.
		@return Specialty	  */
	public int getEXME_Especialidad2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Specialty Reference.
		@param EXME_EspecialidadRefer_ID Specialty Reference	  */
	public void setEXME_EspecialidadRefer_ID (int EXME_EspecialidadRefer_ID)
	{
		if (EXME_EspecialidadRefer_ID < 1) 
			set_Value (COLUMNNAME_EXME_EspecialidadRefer_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EspecialidadRefer_ID, Integer.valueOf(EXME_EspecialidadRefer_ID));
	}

	/** Get Specialty Reference.
		@return Specialty Reference	  */
	public int getEXME_EspecialidadRefer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EspecialidadRefer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service Unit.
		@param EXME_EstServ_ID 
		Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Unit.
		@return Service Unit
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Revenue Service Station .
		@param EXME_EstServIng_ID Revenue Service Station 	  */
	public void setEXME_EstServIng_ID (int EXME_EstServIng_ID)
	{
		if (EXME_EstServIng_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServIng_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServIng_ID, Integer.valueOf(EXME_EstServIng_ID));
	}

	/** Get Revenue Service Station .
		@return Revenue Service Station 	  */
	public int getEXME_EstServIng_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServIng_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interim Service Station.
		@param EXME_EstServProv_ID Interim Service Station	  */
	public void setEXME_EstServProv_ID (int EXME_EstServProv_ID)
	{
		if (EXME_EstServProv_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServProv_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServProv_ID, Integer.valueOf(EXME_EstServProv_ID));
	}

	/** Get Interim Service Station.
		@return Interim Service Station	  */
	public int getEXME_EstServProv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServProv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Institucion.Table_Name);
        I_EXME_Institucion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Institucion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Institucion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Facility.
		@param EXME_Institucion_ID 
		Service Facility
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID)
	{
		if (EXME_Institucion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Institucion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Institucion_ID, Integer.valueOf(EXME_Institucion_ID));
	}

	/** Get Service Facility.
		@return Service Facility
	  */
	public int getEXME_Institucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Institucion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor 2.
		@param EXME_Medico2_ID Doctor 2	  */
	public void setEXME_Medico2_ID (int EXME_Medico2_ID)
	{
		if (EXME_Medico2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico2_ID, Integer.valueOf(EXME_Medico2_ID));
	}

	/** Get Doctor 2.
		@return Doctor 2	  */
	public int getEXME_Medico2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico2_ID);
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
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
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

	/** Set EXME_MedicoRefer_ID.
		@param EXME_MedicoRefer_ID EXME_MedicoRefer_ID	  */
	public void setEXME_MedicoRefer_ID (int EXME_MedicoRefer_ID)
	{
		if (EXME_MedicoRefer_ID < 1) 
			set_Value (COLUMNNAME_EXME_MedicoRefer_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MedicoRefer_ID, Integer.valueOf(EXME_MedicoRefer_ID));
	}

	/** Get EXME_MedicoRefer_ID.
		@return EXME_MedicoRefer_ID	  */
	public int getEXME_MedicoRefer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoRefer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Motive of appointment.
		@param EXME_MotivoCita_ID 
		Motive of appointment
	  */
	public void setEXME_MotivoCita_ID (int EXME_MotivoCita_ID)
	{
		if (EXME_MotivoCita_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoCita_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoCita_ID, Integer.valueOf(EXME_MotivoCita_ID));
	}

	/** Get Motive of appointment.
		@return Motive of appointment
	  */
	public int getEXME_MotivoCita_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCita_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MotivoEgreso getEXME_MotivoEgreso() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoEgreso.Table_Name);
        I_EXME_MotivoEgreso result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoEgreso)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoEgreso_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Discharge Reason.
		@param EXME_MotivoEgreso_ID Discharge Reason	  */
	public void setEXME_MotivoEgreso_ID (int EXME_MotivoEgreso_ID)
	{
		if (EXME_MotivoEgreso_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoEgreso_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoEgreso_ID, Integer.valueOf(EXME_MotivoEgreso_ID));
	}

	/** Get Discharge Reason.
		@return Discharge Reason	  */
	public int getEXME_MotivoEgreso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoEgreso_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	/** Set Package Version.
		@param EXME_PaqBase_Version_ID 
		Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID)
	{
		if (EXME_PaqBase_Version_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, Integer.valueOf(EXME_PaqBase_Version_ID));
	}

	/** Get Package Version.
		@return Package Version
	  */
	public int getEXME_PaqBase_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_POS getEXME_POS() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_POS.Table_Name);
        I_EXME_POS result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_POS)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_POS_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Place of Service.
		@param EXME_POS_ID 
		Place of Service
	  */
	public void setEXME_POS_ID (int EXME_POS_ID)
	{
		if (EXME_POS_ID < 1) 
			set_Value (COLUMNNAME_EXME_POS_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_POS_ID, Integer.valueOf(EXME_POS_ID));
	}

	/** Get Place of Service.
		@return Place of Service
	  */
	public int getEXME_POS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_POS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Procedencia getEXME_Procedencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Procedencia.Table_Name);
        I_EXME_Procedencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Procedencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Procedencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Provenance.
		@param EXME_Procedencia_ID 
		Provenance
	  */
	public void setEXME_Procedencia_ID (int EXME_Procedencia_ID)
	{
		if (EXME_Procedencia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Procedencia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Procedencia_ID, Integer.valueOf(EXME_Procedencia_ID));
	}

	/** Get Provenance.
		@return Provenance
	  */
	public int getEXME_Procedencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Procedencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoPaciente getEXME_TipoPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoPaciente.Table_Name);
        I_EXME_TipoPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Type of Patient.
		@param EXME_TipoPaciente_ID 
		Type of Patient
	  */
	public void setEXME_TipoPaciente_ID (int EXME_TipoPaciente_ID)
	{
		if (EXME_TipoPaciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoPaciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoPaciente_ID, Integer.valueOf(EXME_TipoPaciente_ID));
	}

	/** Get Type of Patient.
		@return Type of Patient
	  */
	public int getEXME_TipoPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoProd getEXME_TipoProd() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoProd.Table_Name);
        I_EXME_TipoProd result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoProd)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoProd_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product Subtype.
		@param EXME_TipoProd_ID 
		Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID)
	{
		if (EXME_TipoProd_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoProd_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoProd_ID, Integer.valueOf(EXME_TipoProd_ID));
	}

	/** Get Product Subtype.
		@return Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoProd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoTrasplante getEXME_TipoTrasplante() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoTrasplante.Table_Name);
        I_EXME_TipoTrasplante result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoTrasplante)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoTrasplante_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Transplant Type.
		@param EXME_TipoTrasplante_ID Transplant Type	  */
	public void setEXME_TipoTrasplante_ID (int EXME_TipoTrasplante_ID)
	{
		if (EXME_TipoTrasplante_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoTrasplante_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoTrasplante_ID, Integer.valueOf(EXME_TipoTrasplante_ID));
	}

	/** Get Transplant Type.
		@return Transplant Type	  */
	public int getEXME_TipoTrasplante_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoTrasplante_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Transport_Mode getEXME_Transport_Mode() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Transport_Mode.Table_Name);
        I_EXME_Transport_Mode result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Transport_Mode)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Transport_Mode_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_Transport_Mode_ID.
		@param EXME_Transport_Mode_ID EXME_Transport_Mode_ID	  */
	public void setEXME_Transport_Mode_ID (int EXME_Transport_Mode_ID)
	{
		if (EXME_Transport_Mode_ID < 1) 
			set_Value (COLUMNNAME_EXME_Transport_Mode_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Transport_Mode_ID, Integer.valueOf(EXME_Transport_Mode_ID));
	}

	/** Get EXME_Transport_Mode_ID.
		@return EXME_Transport_Mode_ID	  */
	public int getEXME_Transport_Mode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Transport_Mode_ID);
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

	/** Set Cancel date.
		@param FechaCancel 
		Cancel date
	  */
	public void setFechaCancel (Timestamp FechaCancel)
	{
		set_Value (COLUMNNAME_FechaCancel, FechaCancel);
	}

	/** Get Cancel date.
		@return Cancel date
	  */
	public Timestamp getFechaCancel () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCancel);
	}

	/** Set Date Daily Charge.
		@param FechaCargoDiario 
		Date when applied the charge
	  */
	public void setFechaCargoDiario (Timestamp FechaCargoDiario)
	{
		set_Value (COLUMNNAME_FechaCargoDiario, FechaCargoDiario);
	}

	/** Get Date Daily Charge.
		@return Date when applied the charge
	  */
	public Timestamp getFechaCargoDiario () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCargoDiario);
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

	/** Set PreDischarge Date.
		@param FechaPrealta 
		PreDischarge Date
	  */
	public void setFechaPrealta (Timestamp FechaPrealta)
	{
		set_Value (COLUMNNAME_FechaPrealta, FechaPrealta);
	}

	/** Get PreDischarge Date.
		@return PreDischarge Date
	  */
	public Timestamp getFechaPrealta () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaPrealta);
	}

	/** Set Transfer Date.
		@param FechaTraslado 
		Transfer Date
	  */
	public void setFechaTraslado (Timestamp FechaTraslado)
	{
		set_Value (COLUMNNAME_FechaTraslado, FechaTraslado);
	}

	/** Get Transfer Date.
		@return Transfer Date
	  */
	public Timestamp getFechaTraslado () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaTraslado);
	}

	/** Set File Format.
		@param FormatoArchivo 
		File Format
	  */
	public void setFormatoArchivo (String FormatoArchivo)
	{
		set_Value (COLUMNNAME_FormatoArchivo, FormatoArchivo);
	}

	/** Get File Format.
		@return File Format
	  */
	public String getFormatoArchivo () 
	{
		return (String)get_Value(COLUMNNAME_FormatoArchivo);
	}

	/** Set Grand Total.
		@param GrandTotal 
		Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal)
	{
		if (GrandTotal == null)
			throw new IllegalArgumentException ("GrandTotal is mandatory.");
		set_Value (COLUMNNAME_GrandTotal, GrandTotal);
	}

	/** Get Grand Total.
		@return Total amount of document
	  */
	public BigDecimal getGrandTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GrandTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Print Bracelet.
		@param ImpBrazalete 
		Print Bracelet
	  */
	public void setImpBrazalete (String ImpBrazalete)
	{
		if (ImpBrazalete == null)
			throw new IllegalArgumentException ("ImpBrazalete is mandatory.");
		set_Value (COLUMNNAME_ImpBrazalete, ImpBrazalete);
	}

	/** Get Print Bracelet.
		@return Print Bracelet
	  */
	public String getImpBrazalete () 
	{
		return (String)get_Value(COLUMNNAME_ImpBrazalete);
	}

	/** Set Print Consent.
		@param ImpConsent 
		Print Consent
	  */
	public void setImpConsent (String ImpConsent)
	{
		if (ImpConsent == null)
			throw new IllegalArgumentException ("ImpConsent is mandatory.");
		set_Value (COLUMNNAME_ImpConsent, ImpConsent);
	}

	/** Get Print Consent.
		@return Print Consent
	  */
	public String getImpConsent () 
	{
		return (String)get_Value(COLUMNNAME_ImpConsent);
	}

	/** Set Print Contract.
		@param ImpContrato 
		Print contract
	  */
	public void setImpContrato (String ImpContrato)
	{
		if (ImpContrato == null)
			throw new IllegalArgumentException ("ImpContrato is mandatory.");
		set_Value (COLUMNNAME_ImpContrato, ImpContrato);
	}

	/** Get Print Contract.
		@return Print contract
	  */
	public String getImpContrato () 
	{
		return (String)get_Value(COLUMNNAME_ImpContrato);
	}

	/** Set Print data.
		@param ImpDatos 
		Print General Data
	  */
	public void setImpDatos (String ImpDatos)
	{
		if (ImpDatos == null)
			throw new IllegalArgumentException ("ImpDatos is mandatory.");
		set_Value (COLUMNNAME_ImpDatos, ImpDatos);
	}

	/** Get Print data.
		@return Print General Data
	  */
	public String getImpDatos () 
	{
		return (String)get_Value(COLUMNNAME_ImpDatos);
	}

	/** Set Discharge Report.
		@param InformeAlta Discharge Report	  */
	public void setInformeAlta (String InformeAlta)
	{
		set_Value (COLUMNNAME_InformeAlta, InformeAlta);
	}

	/** Get Discharge Report.
		@return Discharge Report	  */
	public String getInformeAlta () 
	{
		return (String)get_Value(COLUMNNAME_InformeAlta);
	}

	/** InstitutionalStatus AD_Reference_ID=1200581 */
	public static final int INSTITUTIONALSTATUS_AD_Reference_ID=1200581;
	/** Coding Complete = 4 */
	public static final String INSTITUTIONALSTATUS_CodingComplete = "4";
	/** Coding Incomplete = 3 */
	public static final String INSTITUTIONALSTATUS_CodingIncomplete = "3";
	/** Error, Message 997 = 11 */
	public static final String INSTITUTIONALSTATUS_ErrorMessage997 = "11";
	/** Error, Mandatory Fields = 7 */
	public static final String INSTITUTIONALSTATUS_ErrorMandatoryFields = "7";
	/** Error RSP File = 12 */
	public static final String INSTITUTIONALSTATUS_ErrorRSPFile = "12";
	/** OK, Message 997 = 13 */
	public static final String INSTITUTIONALSTATUS_OKMessage997 = "13";
	/** OK, Mandatory Fields = 10 */
	public static final String INSTITUTIONALSTATUS_OKMandatoryFields = "10";
	/** OK, RSP File = 14 */
	public static final String INSTITUTIONALSTATUS_OKRSPFile = "14";
	/** 835 Received = 18 */
	public static final String INSTITUTIONALSTATUS_835Received = "18";
	/** 835 Received Second Insurance = RSI */
	public static final String INSTITUTIONALSTATUS_835ReceivedSecondInsurance = "RSI";
	/** Returned to Abstracting = 16 */
	public static final String INSTITUTIONALSTATUS_ReturnedToAbstracting = "16";
	/** 835 Received Third Insurance = RTI */
	public static final String INSTITUTIONALSTATUS_835ReceivedThirdInsurance = "RTI";
	/** Waiting Insurance Payments = 15 */
	public static final String INSTITUTIONALSTATUS_WaitingInsurancePayments = "15";
	/** Waiting Guarantor Payments = 25 */
	public static final String INSTITUTIONALSTATUS_WaitingGuarantorPayments = "25";
	/** Waiting 835 Second Insurance = WSI */
	public static final String INSTITUTIONALSTATUS_Waiting835SecondInsurance = "WSI";
	/** Waiting 835 Third Insurance = WTI */
	public static final String INSTITUTIONALSTATUS_Waiting835ThirdInsurance = "WTI";
	/** Not Applicable = 2 */
	public static final String INSTITUTIONALSTATUS_NotApplicable = "2";
	/** Not Billed = 1 */
	public static final String INSTITUTIONALSTATUS_NotBilled = "1";
	/** Error, Orders Incomplete = 5 */
	public static final String INSTITUTIONALSTATUS_ErrorOrdersIncomplete = "5";
	/** Error, Prices in Zero = 6 */
	public static final String INSTITUTIONALSTATUS_ErrorPricesInZero = "6";
	/** OK, Orders Complete = 8 */
	public static final String INSTITUTIONALSTATUS_OKOrdersComplete = "8";
	/** OK, Prices in Zero = 9 */
	public static final String INSTITUTIONALSTATUS_OKPricesInZero = "9";
	/** Returned to Coding = 17 */
	public static final String INSTITUTIONALSTATUS_ReturnedToCoding = "17";
	/** Payment Plan = 23 */
	public static final String INSTITUTIONALSTATUS_PaymentPlan = "23";
	/** Self Pay = 19 */
	public static final String INSTITUTIONALSTATUS_SelfPay = "19";
	/** Bad Debt Collection = 21 */
	public static final String INSTITUTIONALSTATUS_BadDebtCollection = "21";
	/** Early Out = 20 */
	public static final String INSTITUTIONALSTATUS_EarlyOut = "20";
	/** Credit Balance = 22 */
	public static final String INSTITUTIONALSTATUS_CreditBalance = "22";
	/** Zero Balance = 24 */
	public static final String INSTITUTIONALSTATUS_ZeroBalance = "24";
	/** Set Institutional Status.
		@param InstitutionalStatus 
		Institutional Status
	  */
	public void setInstitutionalStatus (String InstitutionalStatus)
	{

		if (InstitutionalStatus == null || InstitutionalStatus.equals("4") || InstitutionalStatus.equals("3") || InstitutionalStatus.equals("11") || InstitutionalStatus.equals("7") || InstitutionalStatus.equals("12") || InstitutionalStatus.equals("13") || InstitutionalStatus.equals("10") || InstitutionalStatus.equals("14") || InstitutionalStatus.equals("18") || InstitutionalStatus.equals("RSI") || InstitutionalStatus.equals("16") || InstitutionalStatus.equals("RTI") || InstitutionalStatus.equals("15") || InstitutionalStatus.equals("25") || InstitutionalStatus.equals("WSI") || InstitutionalStatus.equals("WTI") || InstitutionalStatus.equals("2") || InstitutionalStatus.equals("1") || InstitutionalStatus.equals("5") || InstitutionalStatus.equals("6") || InstitutionalStatus.equals("8") || InstitutionalStatus.equals("9") || InstitutionalStatus.equals("17") || InstitutionalStatus.equals("23") || InstitutionalStatus.equals("19") || InstitutionalStatus.equals("21") || InstitutionalStatus.equals("20") || InstitutionalStatus.equals("22") || InstitutionalStatus.equals("24")); else throw new IllegalArgumentException ("InstitutionalStatus Invalid value - " + InstitutionalStatus + " - Reference_ID=1200581 - 4 - 3 - 11 - 7 - 12 - 13 - 10 - 14 - 18 - RSI - 16 - RTI - 15 - 25 - WSI - WTI - 2 - 1 - 5 - 6 - 8 - 9 - 17 - 23 - 19 - 21 - 20 - 22 - 24");		set_Value (COLUMNNAME_InstitutionalStatus, InstitutionalStatus);
	}

	/** Get Institutional Status.
		@return Institutional Status
	  */
	public String getInstitutionalStatus () 
	{
		return (String)get_Value(COLUMNNAME_InstitutionalStatus);
	}

	/** InstitutionalStep AD_Reference_ID=1200595 */
	public static final int INSTITUTIONALSTEP_AD_Reference_ID=1200595;
	/** Default = D */
	public static final String INSTITUTIONALSTEP_Default = "D";
	/** First Insurance = F */
	public static final String INSTITUTIONALSTEP_FirstInsurance = "F";
	/** Second Insurance = S */
	public static final String INSTITUTIONALSTEP_SecondInsurance = "S";
	/** Third Insurance = T */
	public static final String INSTITUTIONALSTEP_ThirdInsurance = "T";
	/** Other Insurance = O */
	public static final String INSTITUTIONALSTEP_OtherInsurance = "O";
	/** Selft Pay = P */
	public static final String INSTITUTIONALSTEP_SelftPay = "P";
	/** Set Institutional Step.
		@param InstitutionalStep 
		Insurance that has the liability for institutional charges
	  */
	public void setInstitutionalStep (String InstitutionalStep)
	{

		if (InstitutionalStep == null || InstitutionalStep.equals("D") || InstitutionalStep.equals("F") || InstitutionalStep.equals("S") || InstitutionalStep.equals("T") || InstitutionalStep.equals("O") || InstitutionalStep.equals("P")); else throw new IllegalArgumentException ("InstitutionalStep Invalid value - " + InstitutionalStep + " - Reference_ID=1200595 - D - F - S - T - O - P");		set_Value (COLUMNNAME_InstitutionalStep, InstitutionalStep);
	}

	/** Get Institutional Step.
		@return Insurance that has the liability for institutional charges
	  */
	public String getInstitutionalStep () 
	{
		return (String)get_Value(COLUMNNAME_InstitutionalStep);
	}

	/** Set Release Instructions.
		@param InstruccionAlta Release Instructions	  */
	public void setInstruccionAlta (String InstruccionAlta)
	{
		set_Value (COLUMNNAME_InstruccionAlta, InstruccionAlta);
	}

	/** Get Release Instructions.
		@return Release Instructions	  */
	public String getInstruccionAlta () 
	{
		return (String)get_Value(COLUMNNAME_InstruccionAlta);
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Authorized.
		@param IsAutorizada Is Authorized	  */
	public void setIsAutorizada (boolean IsAutorizada)
	{
		set_Value (COLUMNNAME_IsAutorizada, Boolean.valueOf(IsAutorizada));
	}

	/** Get Is Authorized.
		@return Is Authorized	  */
	public boolean isAutorizada () 
	{
		Object oo = get_Value(COLUMNNAME_IsAutorizada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Locked.
		@param IsBloqueada Is Locked	  */
	public void setIsBloqueada (boolean IsBloqueada)
	{
		set_Value (COLUMNNAME_IsBloqueada, Boolean.valueOf(IsBloqueada));
	}

	/** Get Is Locked.
		@return Is Locked	  */
	public boolean isBloqueada () 
	{
		Object oo = get_Value(COLUMNNAME_IsBloqueada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Credit Approved.
		@param IsCreditApproved 
		Credit  has been approved
	  */
	public void setIsCreditApproved (boolean IsCreditApproved)
	{
		set_Value (COLUMNNAME_IsCreditApproved, Boolean.valueOf(IsCreditApproved));
	}

	/** Get Credit Approved.
		@return Credit  has been approved
	  */
	public boolean isCreditApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsCreditApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Billing multiple.
		@param IsFactEspec Billing multiple	  */
	public void setIsFactEspec (boolean IsFactEspec)
	{
		set_Value (COLUMNNAME_IsFactEspec, Boolean.valueOf(IsFactEspec));
	}

	/** Get Billing multiple.
		@return Billing multiple	  */
	public boolean isFactEspec () 
	{
		Object oo = get_Value(COLUMNNAME_IsFactEspec);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Identifier.
		@param IsIdentifier 
		This column is part of the record identifier
	  */
	public void setIsIdentifier (boolean IsIdentifier)
	{
		set_Value (COLUMNNAME_IsIdentifier, Boolean.valueOf(IsIdentifier));
	}

	/** Get Identifier.
		@return This column is part of the record identifier
	  */
	public boolean isIdentifier () 
	{
		Object oo = get_Value(COLUMNNAME_IsIdentifier);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Invoiced.
		@param IsInvoiced 
		Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced)
	{
		set_Value (COLUMNNAME_IsInvoiced, Boolean.valueOf(IsInvoiced));
	}

	/** Get Invoiced.
		@return Is this invoiced?
	  */
	public boolean isInvoiced () 
	{
		Object oo = get_Value(COLUMNNAME_IsInvoiced);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is New Born.
		@param IsNewBorn 
		Is New Born
	  */
	public void setIsNewBorn (boolean IsNewBorn)
	{
		set_Value (COLUMNNAME_IsNewBorn, Boolean.valueOf(IsNewBorn));
	}

	/** Get Is New Born.
		@return Is New Born
	  */
	public boolean isNewBorn () 
	{
		Object oo = get_Value(COLUMNNAME_IsNewBorn);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is in Use.
		@param IsUso Is in Use	  */
	public void setIsUso (boolean IsUso)
	{
		set_Value (COLUMNNAME_IsUso, Boolean.valueOf(IsUso));
	}

	/** Get Is in Use.
		@return Is in Use	  */
	public boolean isUso () 
	{
		Object oo = get_Value(COLUMNNAME_IsUso);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1)
			 throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
		set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set NoInsuranceCoverage.
		@param NoInsuranceCoverage NoInsuranceCoverage	  */
	public void setNoInsuranceCoverage (boolean NoInsuranceCoverage)
	{
		set_Value (COLUMNNAME_NoInsuranceCoverage, Boolean.valueOf(NoInsuranceCoverage));
	}

	/** Get NoInsuranceCoverage.
		@return NoInsuranceCoverage	  */
	public boolean isNoInsuranceCoverage () 
	{
		Object oo = get_Value(COLUMNNAME_NoInsuranceCoverage);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set File Name.
		@param NombreArchivo 
		File Name
	  */
	public void setNombreArchivo (String NombreArchivo)
	{
		set_Value (COLUMNNAME_NombreArchivo, NombreArchivo);
	}

	/** Get File Name.
		@return File Name
	  */
	public String getNombreArchivo () 
	{
		return (String)get_Value(COLUMNNAME_NombreArchivo);
	}

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		throw new IllegalArgumentException ("Nombre_Pac is virtual column");	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pac);
	}

	/** Set NoStatementAge.
		@param NoStatementAge NoStatementAge	  */
	public void setNoStatementAge (boolean NoStatementAge)
	{
		set_Value (COLUMNNAME_NoStatementAge, Boolean.valueOf(NoStatementAge));
	}

	/** Get NoStatementAge.
		@return NoStatementAge	  */
	public boolean isNoStatementAge () 
	{
		Object oo = get_Value(COLUMNNAME_NoStatementAge);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Priority.
		@param PriorityRule 
		Priority of a document
	  */
	public void setPriorityRule (String PriorityRule)
	{
		if (PriorityRule == null)
			throw new IllegalArgumentException ("PriorityRule is mandatory.");
		set_Value (COLUMNNAME_PriorityRule, PriorityRule);
	}

	/** Get Priority.
		@return Priority of a document
	  */
	public String getPriorityRule () 
	{
		return (String)get_Value(COLUMNNAME_PriorityRule);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** ProfessionalStatus AD_Reference_ID=1200581 */
	public static final int PROFESSIONALSTATUS_AD_Reference_ID=1200581;
	/** Coding Complete = 4 */
	public static final String PROFESSIONALSTATUS_CodingComplete = "4";
	/** Coding Incomplete = 3 */
	public static final String PROFESSIONALSTATUS_CodingIncomplete = "3";
	/** Error, Message 997 = 11 */
	public static final String PROFESSIONALSTATUS_ErrorMessage997 = "11";
	/** Error, Mandatory Fields = 7 */
	public static final String PROFESSIONALSTATUS_ErrorMandatoryFields = "7";
	/** Error RSP File = 12 */
	public static final String PROFESSIONALSTATUS_ErrorRSPFile = "12";
	/** OK, Message 997 = 13 */
	public static final String PROFESSIONALSTATUS_OKMessage997 = "13";
	/** OK, Mandatory Fields = 10 */
	public static final String PROFESSIONALSTATUS_OKMandatoryFields = "10";
	/** OK, RSP File = 14 */
	public static final String PROFESSIONALSTATUS_OKRSPFile = "14";
	/** 835 Received = 18 */
	public static final String PROFESSIONALSTATUS_835Received = "18";
	/** 835 Received Second Insurance = RSI */
	public static final String PROFESSIONALSTATUS_835ReceivedSecondInsurance = "RSI";
	/** Returned to Abstracting = 16 */
	public static final String PROFESSIONALSTATUS_ReturnedToAbstracting = "16";
	/** 835 Received Third Insurance = RTI */
	public static final String PROFESSIONALSTATUS_835ReceivedThirdInsurance = "RTI";
	/** Waiting Insurance Payments = 15 */
	public static final String PROFESSIONALSTATUS_WaitingInsurancePayments = "15";
	/** Waiting Guarantor Payments = 25 */
	public static final String PROFESSIONALSTATUS_WaitingGuarantorPayments = "25";
	/** Waiting 835 Second Insurance = WSI */
	public static final String PROFESSIONALSTATUS_Waiting835SecondInsurance = "WSI";
	/** Waiting 835 Third Insurance = WTI */
	public static final String PROFESSIONALSTATUS_Waiting835ThirdInsurance = "WTI";
	/** Not Applicable = 2 */
	public static final String PROFESSIONALSTATUS_NotApplicable = "2";
	/** Not Billed = 1 */
	public static final String PROFESSIONALSTATUS_NotBilled = "1";
	/** Error, Orders Incomplete = 5 */
	public static final String PROFESSIONALSTATUS_ErrorOrdersIncomplete = "5";
	/** Error, Prices in Zero = 6 */
	public static final String PROFESSIONALSTATUS_ErrorPricesInZero = "6";
	/** OK, Orders Complete = 8 */
	public static final String PROFESSIONALSTATUS_OKOrdersComplete = "8";
	/** OK, Prices in Zero = 9 */
	public static final String PROFESSIONALSTATUS_OKPricesInZero = "9";
	/** Returned to Coding = 17 */
	public static final String PROFESSIONALSTATUS_ReturnedToCoding = "17";
	/** Payment Plan = 23 */
	public static final String PROFESSIONALSTATUS_PaymentPlan = "23";
	/** Self Pay = 19 */
	public static final String PROFESSIONALSTATUS_SelfPay = "19";
	/** Bad Debt Collection = 21 */
	public static final String PROFESSIONALSTATUS_BadDebtCollection = "21";
	/** Early Out = 20 */
	public static final String PROFESSIONALSTATUS_EarlyOut = "20";
	/** Credit Balance = 22 */
	public static final String PROFESSIONALSTATUS_CreditBalance = "22";
	/** Zero Balance = 24 */
	public static final String PROFESSIONALSTATUS_ZeroBalance = "24";
	/** Set Professional Status.
		@param ProfessionalStatus 
		Professional Status
	  */
	public void setProfessionalStatus (String ProfessionalStatus)
	{

		if (ProfessionalStatus == null || ProfessionalStatus.equals("4") || ProfessionalStatus.equals("3") || ProfessionalStatus.equals("11") || ProfessionalStatus.equals("7") || ProfessionalStatus.equals("12") || ProfessionalStatus.equals("13") || ProfessionalStatus.equals("10") || ProfessionalStatus.equals("14") || ProfessionalStatus.equals("18") || ProfessionalStatus.equals("RSI") || ProfessionalStatus.equals("16") || ProfessionalStatus.equals("RTI") || ProfessionalStatus.equals("15") || ProfessionalStatus.equals("25") || ProfessionalStatus.equals("WSI") || ProfessionalStatus.equals("WTI") || ProfessionalStatus.equals("2") || ProfessionalStatus.equals("1") || ProfessionalStatus.equals("5") || ProfessionalStatus.equals("6") || ProfessionalStatus.equals("8") || ProfessionalStatus.equals("9") || ProfessionalStatus.equals("17") || ProfessionalStatus.equals("23") || ProfessionalStatus.equals("19") || ProfessionalStatus.equals("21") || ProfessionalStatus.equals("20") || ProfessionalStatus.equals("22") || ProfessionalStatus.equals("24")); else throw new IllegalArgumentException ("ProfessionalStatus Invalid value - " + ProfessionalStatus + " - Reference_ID=1200581 - 4 - 3 - 11 - 7 - 12 - 13 - 10 - 14 - 18 - RSI - 16 - RTI - 15 - 25 - WSI - WTI - 2 - 1 - 5 - 6 - 8 - 9 - 17 - 23 - 19 - 21 - 20 - 22 - 24");		set_Value (COLUMNNAME_ProfessionalStatus, ProfessionalStatus);
	}

	/** Get Professional Status.
		@return Professional Status
	  */
	public String getProfessionalStatus () 
	{
		return (String)get_Value(COLUMNNAME_ProfessionalStatus);
	}

	/** ProfessionalStep AD_Reference_ID=1200595 */
	public static final int PROFESSIONALSTEP_AD_Reference_ID=1200595;
	/** Default = D */
	public static final String PROFESSIONALSTEP_Default = "D";
	/** First Insurance = F */
	public static final String PROFESSIONALSTEP_FirstInsurance = "F";
	/** Second Insurance = S */
	public static final String PROFESSIONALSTEP_SecondInsurance = "S";
	/** Third Insurance = T */
	public static final String PROFESSIONALSTEP_ThirdInsurance = "T";
	/** Other Insurance = O */
	public static final String PROFESSIONALSTEP_OtherInsurance = "O";
	/** Selft Pay = P */
	public static final String PROFESSIONALSTEP_SelftPay = "P";
	/** Set Professional Step.
		@param ProfessionalStep 
		Insurance that has the liability for professional charges
	  */
	public void setProfessionalStep (String ProfessionalStep)
	{

		if (ProfessionalStep == null || ProfessionalStep.equals("D") || ProfessionalStep.equals("F") || ProfessionalStep.equals("S") || ProfessionalStep.equals("T") || ProfessionalStep.equals("O") || ProfessionalStep.equals("P")); else throw new IllegalArgumentException ("ProfessionalStep Invalid value - " + ProfessionalStep + " - Reference_ID=1200595 - D - F - S - T - O - P");		set_Value (COLUMNNAME_ProfessionalStep, ProfessionalStep);
	}

	/** Get Professional Step.
		@return Insurance that has the liability for professional charges
	  */
	public String getProfessionalStep () 
	{
		return (String)get_Value(COLUMNNAME_ProfessionalStep);
	}

	/** Set Reference Familiar Encounter.
		@param Ref_CtaPacFam_ID Reference Familiar Encounter	  */
	public void setRef_CtaPacFam_ID (int Ref_CtaPacFam_ID)
	{
		if (Ref_CtaPacFam_ID < 1) 
			set_Value (COLUMNNAME_Ref_CtaPacFam_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_CtaPacFam_ID, Integer.valueOf(Ref_CtaPacFam_ID));
	}

	/** Get Reference Familiar Encounter.
		@return Reference Familiar Encounter	  */
	public int getRef_CtaPacFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_CtaPacFam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reference Encounter.
		@param Ref_CtaPac_ID 
		Reference Encounter
	  */
	public void setRef_CtaPac_ID (int Ref_CtaPac_ID)
	{
		if (Ref_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_Ref_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_CtaPac_ID, Integer.valueOf(Ref_CtaPac_ID));
	}

	/** Get Reference Encounter.
		@return Reference Encounter
	  */
	public int getRef_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ref. unit.
		@param Ref_Unit_ID 
		Refers to the unit
	  */
	public void setRef_Unit_ID (int Ref_Unit_ID)
	{
		if (Ref_Unit_ID < 1) 
			set_Value (COLUMNNAME_Ref_Unit_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_Unit_ID, Integer.valueOf(Ref_Unit_ID));
	}

	/** Get Ref. unit.
		@return Refers to the unit
	  */
	public int getRef_Unit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_Unit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Requester.
		@param Requester Requester	  */
	public void setRequester (String Requester)
	{
		set_Value (COLUMNNAME_Requester, Requester);
	}

	/** Get Requester.
		@return Requester	  */
	public String getRequester () 
	{
		return (String)get_Value(COLUMNNAME_Requester);
	}

	/** ResStatus AD_Reference_ID=1200502 */
	public static final int RESSTATUS_AD_Reference_ID=1200502;
	/** Limited Support = LS */
	public static final String RESSTATUS_LimitedSupport = "LS";
	/** Do not resuscitate = NR */
	public static final String RESSTATUS_DoNotResuscitate = "NR";
	/** Do not intubate = NI */
	public static final String RESSTATUS_DoNotIntubate = "NI";
	/** Full code = 1F */
	public static final String RESSTATUS_FullCode = "1F";
	/** Comfort care only = CO */
	public static final String RESSTATUS_ComfortCareOnly = "CO";
	/** Set Resuscitative Status.
		@param ResStatus Resuscitative Status	  */
	public void setResStatus (String ResStatus)
	{

		if (ResStatus == null || ResStatus.equals("LS") || ResStatus.equals("NR") || ResStatus.equals("NI") || ResStatus.equals("1F") || ResStatus.equals("CO")); else throw new IllegalArgumentException ("ResStatus Invalid value - " + ResStatus + " - Reference_ID=1200502 - LS - NR - NI - 1F - CO");		set_Value (COLUMNNAME_ResStatus, ResStatus);
	}

	/** Get Resuscitative Status.
		@return Resuscitative Status	  */
	public String getResStatus () 
	{
		return (String)get_Value(COLUMNNAME_ResStatus);
	}

	/** StatusAlta AD_Reference_ID=1200065 */
	public static final int STATUSALTA_AD_Reference_ID=1200065;
	/** Patient Discharge = A */
	public static final String STATUSALTA_PatientDischarge = "A";
	/** Normal = N */
	public static final String STATUSALTA_Normal = "N";
	/** Patient Pre-Discharge = P */
	public static final String STATUSALTA_PatientPre_Discharge = "P";
	/** Pre-Admission = R */
	public static final String STATUSALTA_Pre_Admission = "R";
	/** Active = C */
	public static final String STATUSALTA_Active = "C";
	/** Complete (Abstracting / Coder) = B */
	public static final String STATUSALTA_CompleteAbstractingCoder = "B";
	/** Billing = L */
	public static final String STATUSALTA_Billing = "L";
	/** Inactive = I */
	public static final String STATUSALTA_Inactive = "I";
	/** Set StatusAlta.
		@param StatusAlta 
		Status for the patient account
	  */
	public void setStatusAlta (String StatusAlta)
	{

		if (StatusAlta == null || StatusAlta.equals("A") || StatusAlta.equals("N") || StatusAlta.equals("P") || StatusAlta.equals("R") || StatusAlta.equals("C") || StatusAlta.equals("B") || StatusAlta.equals("L") || StatusAlta.equals("I")); else throw new IllegalArgumentException ("StatusAlta Invalid value - " + StatusAlta + " - Reference_ID=1200065 - A - N - P - R - C - B - L - I");		set_Value (COLUMNNAME_StatusAlta, StatusAlta);
	}

	/** Get StatusAlta.
		@return Status for the patient account
	  */
	public String getStatusAlta () 
	{
		return (String)get_Value(COLUMNNAME_StatusAlta);
	}

	/** Set StatusEleg.
		@param StatusEleg StatusEleg	  */
	public void setStatusEleg (boolean StatusEleg)
	{
		set_Value (COLUMNNAME_StatusEleg, Boolean.valueOf(StatusEleg));
	}

	/** Get StatusEleg.
		@return StatusEleg	  */
	public boolean isStatusEleg () 
	{
		Object oo = get_Value(COLUMNNAME_StatusEleg);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** TipoArea AD_Reference_ID=1000039 */
	public static final int TIPOAREA_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREA_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREA_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREA_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREA_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREA_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREA_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREA_Other = "O";
	/** External = E */
	public static final String TIPOAREA_External = "E";
	/** Admission = D */
	public static final String TIPOAREA_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREA_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREA_SocialComunication = "S";
	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null) throw new IllegalArgumentException ("TipoArea is mandatory");
		if (TipoArea.equals("H") || TipoArea.equals("U") || TipoArea.equals("A") || TipoArea.equals("C") || TipoArea.equals("P") || TipoArea.equals("R") || TipoArea.equals("O") || TipoArea.equals("E") || TipoArea.equals("D") || TipoArea.equals("T") || TipoArea.equals("S")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}

	/** Set User List 1.
		@param User1_ID 
		User defined list element #1
	  */
	public void setUser1_ID (int User1_ID)
	{
		if (User1_ID < 1) 
			set_Value (COLUMNNAME_User1_ID, null);
		else 
			set_Value (COLUMNNAME_User1_ID, Integer.valueOf(User1_ID));
	}

	/** Get User List 1.
		@return User defined list element #1
	  */
	public int getUser1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User List 2.
		@param User2_ID 
		User defined list element #2
	  */
	public void setUser2_ID (int User2_ID)
	{
		if (User2_ID < 1) 
			set_Value (COLUMNNAME_User2_ID, null);
		else 
			set_Value (COLUMNNAME_User2_ID, Integer.valueOf(User2_ID));
	}

	/** Get User List 2.
		@return User defined list element #2
	  */
	public int getUser2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valid On Queue.
		@param ValidOnQueue Valid On Queue	  */
	public void setValidOnQueue (boolean ValidOnQueue)
	{
		set_Value (COLUMNNAME_ValidOnQueue, Boolean.valueOf(ValidOnQueue));
	}

	/** Get Valid On Queue.
		@return Valid On Queue	  */
	public boolean isValidOnQueue () 
	{
		Object oo = get_Value(COLUMNNAME_ValidOnQueue);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}