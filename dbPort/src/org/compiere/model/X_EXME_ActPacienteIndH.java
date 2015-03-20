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

/** Generated Model for EXME_ActPacienteIndH
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ActPacienteIndH extends PO implements I_EXME_ActPacienteIndH, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ActPacienteIndH (Properties ctx, int EXME_ActPacienteIndH_ID, String trxName)
    {
      super (ctx, EXME_ActPacienteIndH_ID, trxName);
      /** if (EXME_ActPacienteIndH_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_DocType_ID (0);
			setC_DocTypeTarget_ID (0);
			setChargeAmt (Env.ZERO);
			setC_Location_ID (0);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDocAction (null);
			setDocStatus (null);
			setDocumentNo (null);
			setEXME_ActPaciente_ID (0);
			setEXME_ActPacienteIndH_ID (0);
			setGrandTotal (Env.ZERO);
			setIsApproved (false);
			setIsDelivered (false);
			setIsDiscountPrinted (false);
			setIsInvoiced (false);
			setIsService (false);
			setIsTaxIncluded (false);
			setPreAlta (false);
			setProcessed (false);
			setSendOrder (0);
// 0
			setTotalLines (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_ActPacienteIndH (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ActPacienteIndH[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
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

	/** Set ApprovalUser.
		@param ApprovalUser ApprovalUser	  */
	public void setApprovalUser (int ApprovalUser)
	{
		throw new IllegalArgumentException ("ApprovalUser is virtual column");	}

	/** Get ApprovalUser.
		@return ApprovalUser	  */
	public int getApprovalUser () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ApprovalUser);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Authenticated By.
		@param AuthenticatedBy Authenticated By	  */
	public void setAuthenticatedBy (int AuthenticatedBy)
	{
		set_Value (COLUMNNAME_AuthenticatedBy, Integer.valueOf(AuthenticatedBy));
	}

	/** Get Authenticated By.
		@return Authenticated By	  */
	public int getAuthenticatedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AuthenticatedBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Canceled By.
		@param CanceledBy 
		Canceled By
	  */
	public void setCanceledBy (int CanceledBy)
	{
		set_Value (COLUMNNAME_CanceledBy, Integer.valueOf(CanceledBy));
	}

	/** Get Canceled By.
		@return Canceled By
	  */
	public int getCanceledBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CanceledBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Charge_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
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
			set_ValueNoCheck (COLUMNNAME_C_Currency_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
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

	/** Set Charge amount.
		@param ChargeAmt 
		Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt)
	{
		set_ValueNoCheck (COLUMNNAME_ChargeAmt, ChargeAmt);
	}

	/** Get Charge amount.
		@return Charge Amount
	  */
	public BigDecimal getChargeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ChargeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Address .
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1)
			 throw new IllegalArgumentException ("C_Location_ID is mandatory.");
		set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address .
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
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
		set_ValueNoCheck (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Date Approved.
		@param DateApproved Date Approved	  */
	public void setDateApproved (Timestamp DateApproved)
	{
		set_Value (COLUMNNAME_DateApproved, DateApproved);
	}

	/** Get Date Approved.
		@return Date Approved	  */
	public Timestamp getDateApproved () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateApproved);
	}

	/** Set Date Canceled.
		@param DateCanceled Date Canceled	  */
	public void setDateCanceled (Timestamp DateCanceled)
	{
		set_Value (COLUMNNAME_DateCanceled, DateCanceled);
	}

	/** Get Date Canceled.
		@return Date Canceled	  */
	public Timestamp getDateCanceled () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateCanceled);
	}

	/** Set Date Delivered.
		@param DateDelivered 
		Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered)
	{
		set_ValueNoCheck (COLUMNNAME_DateDelivered, DateDelivered);
	}

	/** Get Date Delivered.
		@return Date when the product was delivered
	  */
	public Timestamp getDateDelivered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDelivered);
	}

	/** Set Date Invoiced.
		@param DateInvoiced 
		Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced)
	{
		set_ValueNoCheck (COLUMNNAME_DateInvoiced, DateInvoiced);
	}

	/** Get Date Invoiced.
		@return Date printed on Invoice
	  */
	public Timestamp getDateInvoiced () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateInvoiced);
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		if (DateOrdered == null)
			throw new IllegalArgumentException ("DateOrdered is mandatory.");
		set_ValueNoCheck (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Date Promised.
		@param DatePromised 
		Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised)
	{
		set_ValueNoCheck (COLUMNNAME_DatePromised, DatePromised);
	}

	/** Get Date Promised.
		@return Date Order was promised
	  */
	public Timestamp getDatePromised () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePromised);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_ValueNoCheck (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Diagnostic.
		@param Diagnostico 
		Diagnostic
	  */
	public void setDiagnostico (String Diagnostico)
	{
		set_Value (COLUMNNAME_Diagnostico, Diagnostico);
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public String getDiagnostico () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico);
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
		if (DocAction.equals("CO") || DocAction.equals("AP") || DocAction.equals("RJ") || DocAction.equals("PO") || DocAction.equals("VO") || DocAction.equals("CL") || DocAction.equals("RC") || DocAction.equals("RA") || DocAction.equals("IN") || DocAction.equals("RE") || DocAction.equals("--") || DocAction.equals("PR") || DocAction.equals("XL") || DocAction.equals("WC")); else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - CO - AP - RJ - PO - VO - CL - RC - RA - IN - RE - -- - PR - XL - WC");		set_ValueNoCheck (COLUMNNAME_DocAction, DocAction);
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
		if (DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("IP") || DocStatus.equals("WP") || DocStatus.equals("WC")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_ValueNoCheck (COLUMNNAME_DocStatus, DocStatus);
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
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Estatus AD_Reference_ID=1200112 */
	public static final int ESTATUS_AD_Reference_ID=1200112;
	/** Orders of Service Requested = S */
	public static final String ESTATUS_OrdersOfServiceRequested = "S";
	/** Scheduled Service Orders = P */
	public static final String ESTATUS_ScheduledServiceOrders = "P";
	/** Service Orders Completed = C */
	public static final String ESTATUS_ServiceOrdersCompleted = "C";
	/** Orders Canceled Service (Withdrawn) = A */
	public static final String ESTATUS_OrdersCanceledServiceWithdrawn = "A";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{

		if (Estatus == null || Estatus.equals("S") || Estatus.equals("P") || Estatus.equals("C") || Estatus.equals("A")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200112 - S - P - C - A");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set Patient Activity.
		@param EXME_ActPaciente_ID 
		Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID)
	{
		if (EXME_ActPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPaciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ActPaciente_ID, Integer.valueOf(EXME_ActPaciente_ID));
	}

	/** Get Patient Activity.
		@return Patient Activity
	  */
	public int getEXME_ActPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient's Indication.
		@param EXME_ActPacienteIndH_ID 
		Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID)
	{
		if (EXME_ActPacienteIndH_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteIndH_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ActPacienteIndH_ID, Integer.valueOf(EXME_ActPacienteIndH_ID));
	}

	/** Get Patient's Indication.
		@return Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteIndH_ID);
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
			set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
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

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EncounterForms.Table_Name);
        I_EXME_EncounterForms result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EncounterForms)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EncounterForms_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter Forms.
		@param EXME_EncounterForms_ID 
		Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID)
	{
		if (EXME_EncounterForms_ID < 1) 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, Integer.valueOf(EXME_EncounterForms_ID));
	}

	/** Get Encounter Forms.
		@return Encounter Forms
	  */
	public int getEXME_EncounterForms_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EncounterForms_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Specialty Applicant.
		@param EXME_EspecialidadSol_ID 
		Specialty requesting the service
	  */
	public void setEXME_EspecialidadSol_ID (int EXME_EspecialidadSol_ID)
	{
		if (EXME_EspecialidadSol_ID < 1) 
			set_Value (COLUMNNAME_EXME_EspecialidadSol_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EspecialidadSol_ID, Integer.valueOf(EXME_EspecialidadSol_ID));
	}

	/** Get Specialty Applicant.
		@return Specialty requesting the service
	  */
	public int getEXME_EspecialidadSol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EspecialidadSol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EspecimenCondicion getEXME_EspecimenCondicion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EspecimenCondicion.Table_Name);
        I_EXME_EspecimenCondicion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EspecimenCondicion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EspecimenCondicion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Condition/Disposition of Specimen.
		@param EXME_EspecimenCondicion_ID 
		Condition/Disposition of Specimen
	  */
	public void setEXME_EspecimenCondicion_ID (int EXME_EspecimenCondicion_ID)
	{
		if (EXME_EspecimenCondicion_ID < 1) 
			set_Value (COLUMNNAME_EXME_EspecimenCondicion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EspecimenCondicion_ID, Integer.valueOf(EXME_EspecimenCondicion_ID));
	}

	/** Get Condition/Disposition of Specimen.
		@return Condition/Disposition of Specimen
	  */
	public int getEXME_EspecimenCondicion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EspecimenCondicion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Especimen getEXME_Especimen() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especimen.Table_Name);
        I_EXME_Especimen result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especimen)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especimen_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Test Specimen.
		@param EXME_Especimen_ID 
		Test Specimen
	  */
	public void setEXME_Especimen_ID (int EXME_Especimen_ID)
	{
		if (EXME_Especimen_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especimen_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especimen_ID, Integer.valueOf(EXME_Especimen_ID));
	}

	/** Get Test Specimen.
		@return Test Specimen
	  */
	public int getEXME_Especimen_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especimen_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Farmacia.Table_Name);
        I_EXME_Farmacia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Farmacia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Farmacia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pharmacy.
		@param EXME_Farmacia_ID Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID)
	{
		if (EXME_Farmacia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Farmacia_ID, Integer.valueOf(EXME_Farmacia_ID));
	}

	/** Get Pharmacy.
		@return Pharmacy	  */
	public int getEXME_Farmacia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Farmacia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Impresora getEXME_Impresora() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Impresora.Table_Name);
        I_EXME_Impresora result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Impresora)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Impresora_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set printer.
		@param EXME_Impresora_ID 
		printer
	  */
	public void setEXME_Impresora_ID (int EXME_Impresora_ID)
	{
		if (EXME_Impresora_ID < 1) 
			set_Value (COLUMNNAME_EXME_Impresora_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Impresora_ID, Integer.valueOf(EXME_Impresora_ID));
	}

	/** Get printer.
		@return printer
	  */
	public int getEXME_Impresora_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Impresora_ID);
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

	/** Set Medical Applicants.
		@param EXME_MedicoSol_ID 
		Physician requesting the service
	  */
	public void setEXME_MedicoSol_ID (int EXME_MedicoSol_ID)
	{
		if (EXME_MedicoSol_ID < 1) 
			set_Value (COLUMNNAME_EXME_MedicoSol_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MedicoSol_ID, Integer.valueOf(EXME_MedicoSol_ID));
	}

	/** Get Medical Applicants.
		@return Physician requesting the service
	  */
	public int getEXME_MedicoSol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoSol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MotivoCancel getEXME_MotivoCancel() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoCancel.Table_Name);
        I_EXME_MotivoCancel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoCancel)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoCancel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cancel Reason.
		@param EXME_MotivoCancel_ID 
		Cancel Reason
	  */
	public void setEXME_MotivoCancel_ID (int EXME_MotivoCancel_ID)
	{
		if (EXME_MotivoCancel_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, Integer.valueOf(EXME_MotivoCancel_ID));
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public int getEXME_MotivoCancel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCancel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Tratamientos getEXME_Tratamientos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos.Table_Name);
        I_EXME_Tratamientos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatments.
		@param EXME_Tratamientos_ID 
		Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID)
	{
		if (EXME_Tratamientos_ID < 1) 
			set_Value (COLUMNNAME_EXME_Tratamientos_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Tratamientos_ID, Integer.valueOf(EXME_Tratamientos_ID));
	}

	/** Get Treatments.
		@return Treatments
	  */
	public int getEXME_Tratamientos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos_Sesion.Table_Name);
        I_EXME_Tratamientos_Sesion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos_Sesion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_Sesion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatment sessions.
		@param EXME_Tratamientos_Sesion_ID Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID)
	{
		if (EXME_Tratamientos_Sesion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Tratamientos_Sesion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Tratamientos_Sesion_ID, Integer.valueOf(EXME_Tratamientos_Sesion_ID));
	}

	/** Get Treatment sessions.
		@return Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Sesion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ending Date.
		@param Fecha_Fin 
		Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin)
	{
		set_Value (COLUMNNAME_Fecha_Fin, Fecha_Fin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFecha_Fin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin);
	}

	/** Set Grand Total.
		@param GrandTotal 
		Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal)
	{
		if (GrandTotal == null)
			throw new IllegalArgumentException ("GrandTotal is mandatory.");
		set_ValueNoCheck (COLUMNNAME_GrandTotal, GrandTotal);
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

	/** Set Interval.
		@param Intervalo 
		Interval
	  */
	public void setIntervalo (BigDecimal Intervalo)
	{
		set_Value (COLUMNNAME_Intervalo, Intervalo);
	}

	/** Get Interval.
		@return Interval
	  */
	public BigDecimal getIntervalo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Intervalo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_ValueNoCheck (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
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

	/** Set Delivered.
		@param IsDelivered Delivered	  */
	public void setIsDelivered (boolean IsDelivered)
	{
		set_ValueNoCheck (COLUMNNAME_IsDelivered, Boolean.valueOf(IsDelivered));
	}

	/** Get Delivered.
		@return Delivered	  */
	public boolean isDelivered () 
	{
		Object oo = get_Value(COLUMNNAME_IsDelivered);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Discount Printed.
		@param IsDiscountPrinted 
		Print Discount on Invoice and Order
	  */
	public void setIsDiscountPrinted (boolean IsDiscountPrinted)
	{
		set_ValueNoCheck (COLUMNNAME_IsDiscountPrinted, Boolean.valueOf(IsDiscountPrinted));
	}

	/** Get Discount Printed.
		@return Print Discount on Invoice and Order
	  */
	public boolean isDiscountPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsDiscountPrinted);
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

	/** Set Invoiced.
		@param IsInvoiced 
		Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced)
	{
		set_ValueNoCheck (COLUMNNAME_IsInvoiced, Boolean.valueOf(IsInvoiced));
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

	/** Set Is Service.
		@param IsService 
		Is Service
	  */
	public void setIsService (boolean IsService)
	{
		set_Value (COLUMNNAME_IsService, Boolean.valueOf(IsService));
	}

	/** Get Is Service.
		@return Is Service
	  */
	public boolean isService () 
	{
		Object oo = get_Value(COLUMNNAME_IsService);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Price includes Tax.
		@param IsTaxIncluded 
		Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded)
	{
		set_ValueNoCheck (COLUMNNAME_IsTaxIncluded, Boolean.valueOf(IsTaxIncluded));
	}

	/** Get Price includes Tax.
		@return Tax is included in the price 
	  */
	public boolean isTaxIncluded () 
	{
		Object oo = get_Value(COLUMNNAME_IsTaxIncluded);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set M. D. Name.
		@param MedicoNombre 
		M. D. Name
	  */
	public void setMedicoNombre (String MedicoNombre)
	{
		set_Value (COLUMNNAME_MedicoNombre, MedicoNombre);
	}

	/** Get M. D. Name.
		@return M. D. Name
	  */
	public String getMedicoNombre () 
	{
		return (String)get_Value(COLUMNNAME_MedicoNombre);
	}

	/** Set Cancel Reason.
		@param MotivoCancel 
		Cancel Reason
	  */
	public void setMotivoCancel (String MotivoCancel)
	{
		set_Value (COLUMNNAME_MotivoCancel, MotivoCancel);
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public String getMotivoCancel () 
	{
		return (String)get_Value(COLUMNNAME_MotivoCancel);
	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_PriceList_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
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

	public I_M_Warehouse getM_Warehouse() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Requesting Warehouse.
		@param M_Warehouse_Sol_ID 
		Requesting Warehouse
	  */
	public void setM_Warehouse_Sol_ID (int M_Warehouse_Sol_ID)
	{
		if (M_Warehouse_Sol_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_Sol_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_Sol_ID, Integer.valueOf(M_Warehouse_Sol_ID));
	}

	/** Get Requesting Warehouse.
		@return Requesting Warehouse
	  */
	public int getM_Warehouse_Sol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_Sol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Noted By.
		@param NotedBy Noted By	  */
	public void setNotedBy (int NotedBy)
	{
		set_Value (COLUMNNAME_NotedBy, Integer.valueOf(NotedBy));
	}

	/** Get Noted By.
		@return Noted By	  */
	public int getNotedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NotedBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Noted Date.
		@param NotedDate Noted Date	  */
	public void setNotedDate (Timestamp NotedDate)
	{
		set_Value (COLUMNNAME_NotedDate, NotedDate);
	}

	/** Get Noted Date.
		@return Noted Date	  */
	public Timestamp getNotedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_NotedDate);
	}

	/** OrderType AD_Reference_ID=1200543 */
	public static final int ORDERTYPE_AD_Reference_ID=1200543;
	/** Verbal Order = VO */
	public static final String ORDERTYPE_VerbalOrder = "VO";
	/** Written Order = WO */
	public static final String ORDERTYPE_WrittenOrder = "WO";
	/** Telephone Order = TO */
	public static final String ORDERTYPE_TelephoneOrder = "TO";
	/** Set OrderType.
		@param OrderType OrderType	  */
	public void setOrderType (String OrderType)
	{

		if (OrderType == null || OrderType.equals("VO") || OrderType.equals("WO") || OrderType.equals("TO")); else throw new IllegalArgumentException ("OrderType Invalid value - " + OrderType + " - Reference_ID=1200543 - VO - WO - TO");		set_Value (COLUMNNAME_OrderType, OrderType);
	}

	/** Get OrderType.
		@return OrderType	  */
	public String getOrderType () 
	{
		return (String)get_Value(COLUMNNAME_OrderType);
	}

	/** Set Parent.
		@param Parent_ID 
		Parent of Entity
	  */
	public void setParent_ID (int Parent_ID)
	{
		if (Parent_ID < 1) 
			set_Value (COLUMNNAME_Parent_ID, null);
		else 
			set_Value (COLUMNNAME_Parent_ID, Integer.valueOf(Parent_ID));
	}

	/** Get Parent.
		@return Parent of Entity
	  */
	public int getParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Parent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Predischarge.
		@param PreAlta Predischarge	  */
	public void setPreAlta (boolean PreAlta)
	{
		set_Value (COLUMNNAME_PreAlta, Boolean.valueOf(PreAlta));
	}

	/** Get Predischarge.
		@return Predischarge	  */
	public boolean isPreAlta () 
	{
		Object oo = get_Value(COLUMNNAME_PreAlta);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** PriorityRule AD_Reference_ID=1200573 */
	public static final int PRIORITYRULE_AD_Reference_ID=1200573;
	/** STAT = 1 */
	public static final String PRIORITYRULE_STAT = "1";
	/** CALL BACK/FAX = 11 */
	public static final String PRIORITYRULE_CALLBACKFAX = "11";
	/** NURSE COLLECT = 13 */
	public static final String PRIORITYRULE_NURSECOLLECT = "13";
	/** ASAP = 3 */
	public static final String PRIORITYRULE_ASAP = "3";
	/** TIMED = 5 */
	public static final String PRIORITYRULE_TIMED = "5";
	/** ROUTINE = 7 */
	public static final String PRIORITYRULE_ROUTINE = "7";
	/** EARLY AM = 9 */
	public static final String PRIORITYRULE_EARLYAM = "9";
	/** 0 = 0 */
	public static final String PRIORITYRULE_0 = "0";
	/** Set Priority.
		@param PriorityRule 
		Priority of a document
	  */
	public void setPriorityRule (String PriorityRule)
	{

		if (PriorityRule == null || PriorityRule.equals("1") || PriorityRule.equals("11") || PriorityRule.equals("13") || PriorityRule.equals("3") || PriorityRule.equals("5") || PriorityRule.equals("7") || PriorityRule.equals("9") || PriorityRule.equals("0")); else throw new IllegalArgumentException ("PriorityRule Invalid value - " + PriorityRule + " - Reference_ID=1200573 - 1 - 11 - 13 - 3 - 5 - 7 - 9 - 0");		set_Value (COLUMNNAME_PriorityRule, PriorityRule);
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
		set_ValueNoCheck (COLUMNNAME_Processed, Boolean.valueOf(Processed));
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
		set_ValueNoCheck (COLUMNNAME_Processing, Boolean.valueOf(Processing));
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

	/** Set Protocol.
		@param Protocolo 
		Protocol
	  */
	public void setProtocolo (String Protocolo)
	{
		set_Value (COLUMNNAME_Protocolo, Protocolo);
	}

	/** Get Protocol.
		@return Protocol
	  */
	public String getProtocolo () 
	{
		return (String)get_Value(COLUMNNAME_Protocolo);
	}

	/** ReadBack AD_Reference_ID=319 */
	public static final int READBACK_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String READBACK_Yes = "Y";
	/** No = N */
	public static final String READBACK_No = "N";
	/** Set Read Back.
		@param ReadBack 
		Read Back
	  */
	public void setReadBack (String ReadBack)
	{

		if (ReadBack == null || ReadBack.equals("Y") || ReadBack.equals("N")); else throw new IllegalArgumentException ("ReadBack Invalid value - " + ReadBack + " - Reference_ID=319 - Y - N");		set_Value (COLUMNNAME_ReadBack, ReadBack);
	}

	/** Get Read Back.
		@return Read Back
	  */
	public String getReadBack () 
	{
		return (String)get_Value(COLUMNNAME_ReadBack);
	}

	/** Set Indication Ref..
		@param Ref_ActPacienteIndH_ID 
		Indication Reference
	  */
	public void setRef_ActPacienteIndH_ID (int Ref_ActPacienteIndH_ID)
	{
		if (Ref_ActPacienteIndH_ID < 1) 
			set_Value (COLUMNNAME_Ref_ActPacienteIndH_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_ActPacienteIndH_ID, Integer.valueOf(Ref_ActPacienteIndH_ID));
	}

	/** Get Indication Ref..
		@return Indication Reference
	  */
	public int getRef_ActPacienteIndH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_ActPacienteIndH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Responsible.
		@param Responsable 
		Responsible
	  */
	public void setResponsable (String Responsable)
	{
		set_Value (COLUMNNAME_Responsable, Responsable);
	}

	/** Get Responsible.
		@return Responsible
	  */
	public String getResponsable () 
	{
		return (String)get_Value(COLUMNNAME_Responsable);
	}

	/** Set Send Order.
		@param SendOrder Send Order	  */
	public void setSendOrder (int SendOrder)
	{
		set_Value (COLUMNNAME_SendOrder, Integer.valueOf(SendOrder));
	}

	/** Get Send Order.
		@return Send Order	  */
	public int getSendOrder () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SendOrder);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Status AD_Reference_ID=1200529 */
	public static final int STATUS_AD_Reference_ID=1200529;
	/** Pending = DR */
	public static final String STATUS_Pending = "DR";
	/** Final Result = CO */
	public static final String STATUS_FinalResult = "CO";
	/** Cancelation = VO */
	public static final String STATUS_Cancelation = "VO";
	/** Corrected Results = IN */
	public static final String STATUS_CorrectedResults = "IN";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		if (Status == null || Status.equals("DR") || Status.equals("CO") || Status.equals("VO") || Status.equals("IN")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=1200529 - DR - CO - VO - IN");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Total Lines.
		@param TotalLines 
		Total of all document lines
	  */
	public void setTotalLines (BigDecimal TotalLines)
	{
		if (TotalLines == null)
			throw new IllegalArgumentException ("TotalLines is mandatory.");
		set_ValueNoCheck (COLUMNNAME_TotalLines, TotalLines);
	}

	/** Get Total Lines.
		@return Total of all document lines
	  */
	public BigDecimal getTotalLines () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalLines);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}
}