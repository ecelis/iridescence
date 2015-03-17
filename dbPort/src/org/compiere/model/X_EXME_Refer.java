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

/** Generated Model for EXME_Refer
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Refer extends PO implements I_EXME_Refer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Refer (Properties ctx, int EXME_Refer_ID, String trxName)
    {
      super (ctx, EXME_Refer_ID, trxName);
      /** if (EXME_Refer_ID == 0)
        {
			setAD_OrgEnv_ID (0);
			setAprovadoEnv (false);
			setC_DocType_ID (0);
			setC_DocTypeTarget_ID (0);
			setDocAction (null);
			setDocStatus (null);
			setDocumentNo (null);
			setEstatus (null);
			setEXME_Especialidad_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Refer_ID (0);
			setEXME_ReferMot_ID (0);
			setFechaEnv (new Timestamp( System.currentTimeMillis() ));
			setProcessed (false);
			setTipoAreaEnv (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Refer (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Refer[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Sending Organization.
		@param AD_OrgEnv_ID Sending Organization	  */
	public void setAD_OrgEnv_ID (int AD_OrgEnv_ID)
	{
		if (AD_OrgEnv_ID < 1)
			 throw new IllegalArgumentException ("AD_OrgEnv_ID is mandatory.");
		set_Value (COLUMNNAME_AD_OrgEnv_ID, Integer.valueOf(AD_OrgEnv_ID));
	}

	/** Get Sending Organization.
		@return Sending Organization	  */
	public int getAD_OrgEnv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgEnv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Receiving Organization.
		@param AD_OrgRec_ID 
		Receiving Organization for Referenced Patient
	  */
	public void setAD_OrgRec_ID (int AD_OrgRec_ID)
	{
		if (AD_OrgRec_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgRec_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgRec_ID, Integer.valueOf(AD_OrgRec_ID));
	}

	/** Get Receiving Organization.
		@return Receiving Organization for Referenced Patient
	  */
	public int getAD_OrgRec_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgRec_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approved for shipping.
		@param AprovadoEnv 
		Approved for shipping
	  */
	public void setAprovadoEnv (boolean AprovadoEnv)
	{
		set_Value (COLUMNNAME_AprovadoEnv, Boolean.valueOf(AprovadoEnv));
	}

	/** Get Approved for shipping.
		@return Approved for shipping
	  */
	public boolean isAprovadoEnv () 
	{
		Object oo = get_Value(COLUMNNAME_AprovadoEnv);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_C_DocType getC_DocType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_DocType.Table_Name);
        I_C_DocType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DocType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DocType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Estatus AD_Reference_ID=1200108 */
	public static final int ESTATUS_AD_Reference_ID=1200108;
	/** Reversed = RE */
	public static final String ESTATUS_Reversed = "RE";
	/** Completed Reversed = CO */
	public static final String ESTATUS_CompletedReversed = "CO";
	/** Pending Confirmation = DR */
	public static final String ESTATUS_PendingConfirmation = "DR";
	/** Confirmed (Medical Consultation) = IP */
	public static final String ESTATUS_ConfirmedMedicalConsultation = "IP";
	/** Approved Admission = AP */
	public static final String ESTATUS_ApprovedAdmission = "AP";
	/** Canceled External Patient = CL */
	public static final String ESTATUS_CanceledExternalPatient = "CL";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		if (Estatus == null) throw new IllegalArgumentException ("Estatus is mandatory");
		if (Estatus.equals("RE") || Estatus.equals("CO") || Estatus.equals("DR") || Estatus.equals("IP") || Estatus.equals("AP") || Estatus.equals("CL")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200108 - RE - CO - DR - IP - AP - CL");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	public I_EXME_ActPaciente getEXME_ActPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPaciente.Table_Name);
        I_EXME_ActPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Activity.
		@param EXME_ActPaciente_ID 
		Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID)
	{
		if (EXME_ActPaciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPaciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPaciente_ID, Integer.valueOf(EXME_ActPaciente_ID));
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

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Diagnostico.Table_Name);
        I_EXME_Diagnostico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Diagnostico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Diagnostico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Patient's External Admission.
		@param EXME_Refer_ID Patient's External Admission	  */
	public void setEXME_Refer_ID (int EXME_Refer_ID)
	{
		if (EXME_Refer_ID < 1)
			 throw new IllegalArgumentException ("EXME_Refer_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Refer_ID, Integer.valueOf(EXME_Refer_ID));
	}

	/** Get Patient's External Admission.
		@return Patient's External Admission	  */
	public int getEXME_Refer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Refer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ReferMot getEXME_ReferMot() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ReferMot.Table_Name);
        I_EXME_ReferMot result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ReferMot)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ReferMot_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Reference Motive.
		@param EXME_ReferMot_ID 
		Reference Motive
	  */
	public void setEXME_ReferMot_ID (int EXME_ReferMot_ID)
	{
		if (EXME_ReferMot_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReferMot_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ReferMot_ID, Integer.valueOf(EXME_ReferMot_ID));
	}

	/** Get Reference Motive.
		@return Reference Motive
	  */
	public int getEXME_ReferMot_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReferMot_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Sending Date.
		@param FechaEnv Sending Date	  */
	public void setFechaEnv (Timestamp FechaEnv)
	{
		if (FechaEnv == null)
			throw new IllegalArgumentException ("FechaEnv is mandatory.");
		set_Value (COLUMNNAME_FechaEnv, FechaEnv);
	}

	/** Get Sending Date.
		@return Sending Date	  */
	public Timestamp getFechaEnv () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaEnv);
	}

	/** Set Reception Date.
		@param FechaRec 
		Contains the date on which it carries out waste collection
	  */
	public void setFechaRec (Timestamp FechaRec)
	{
		set_Value (COLUMNNAME_FechaRec, FechaRec);
	}

	/** Get Reception Date.
		@return Contains the date on which it carries out waste collection
	  */
	public Timestamp getFechaRec () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaRec);
	}

	/** Set Motive.
		@param Motivo 
		Motive / Reason
	  */
	public void setMotivo (String Motivo)
	{
		set_Value (COLUMNNAME_Motivo, Motivo);
	}

	/** Get Motive.
		@return Motive / Reason
	  */
	public String getMotivo () 
	{
		return (String)get_Value(COLUMNNAME_Motivo);
	}

	/** Set Sending Responsible.
		@param PersonaEnv Sending Responsible	  */
	public void setPersonaEnv (String PersonaEnv)
	{
		set_Value (COLUMNNAME_PersonaEnv, PersonaEnv);
	}

	/** Get Sending Responsible.
		@return Sending Responsible	  */
	public String getPersonaEnv () 
	{
		return (String)get_Value(COLUMNNAME_PersonaEnv);
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

	/** Set Ref_EXME_Refer_ID.
		@param Ref_EXME_Refer_ID Ref_EXME_Refer_ID	  */
	public void setRef_EXME_Refer_ID (int Ref_EXME_Refer_ID)
	{
		if (Ref_EXME_Refer_ID < 1) 
			set_Value (COLUMNNAME_Ref_EXME_Refer_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_EXME_Refer_ID, Integer.valueOf(Ref_EXME_Refer_ID));
	}

	/** Get Ref_EXME_Refer_ID.
		@return Ref_EXME_Refer_ID	  */
	public int getRef_EXME_Refer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_EXME_Refer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoAreaEnv AD_Reference_ID=1000039 */
	public static final int TIPOAREAENV_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREAENV_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREAENV_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREAENV_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREAENV_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREAENV_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREAENV_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREAENV_Other = "O";
	/** External = E */
	public static final String TIPOAREAENV_External = "E";
	/** Admission = D */
	public static final String TIPOAREAENV_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREAENV_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREAENV_SocialComunication = "S";
	/** Set Sending Area Type.
		@param TipoAreaEnv Sending Area Type	  */
	public void setTipoAreaEnv (String TipoAreaEnv)
	{
		if (TipoAreaEnv == null) throw new IllegalArgumentException ("TipoAreaEnv is mandatory");
		if (TipoAreaEnv.equals("H") || TipoAreaEnv.equals("U") || TipoAreaEnv.equals("A") || TipoAreaEnv.equals("C") || TipoAreaEnv.equals("P") || TipoAreaEnv.equals("R") || TipoAreaEnv.equals("O") || TipoAreaEnv.equals("E") || TipoAreaEnv.equals("D") || TipoAreaEnv.equals("T") || TipoAreaEnv.equals("S")); else throw new IllegalArgumentException ("TipoAreaEnv Invalid value - " + TipoAreaEnv + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoAreaEnv, TipoAreaEnv);
	}

	/** Get Sending Area Type.
		@return Sending Area Type	  */
	public String getTipoAreaEnv () 
	{
		return (String)get_Value(COLUMNNAME_TipoAreaEnv);
	}

	/** TipoAreaRec AD_Reference_ID=1000039 */
	public static final int TIPOAREAREC_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREAREC_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREAREC_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREAREC_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREAREC_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREAREC_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREAREC_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREAREC_Other = "O";
	/** External = E */
	public static final String TIPOAREAREC_External = "E";
	/** Admission = D */
	public static final String TIPOAREAREC_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREAREC_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREAREC_SocialComunication = "S";
	/** Set Receiving Area.
		@param TipoAreaRec 
		Type of receiving area
	  */
	public void setTipoAreaRec (String TipoAreaRec)
	{

		if (TipoAreaRec == null || TipoAreaRec.equals("H") || TipoAreaRec.equals("U") || TipoAreaRec.equals("A") || TipoAreaRec.equals("C") || TipoAreaRec.equals("P") || TipoAreaRec.equals("R") || TipoAreaRec.equals("O") || TipoAreaRec.equals("E") || TipoAreaRec.equals("D") || TipoAreaRec.equals("T") || TipoAreaRec.equals("S")); else throw new IllegalArgumentException ("TipoAreaRec Invalid value - " + TipoAreaRec + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoAreaRec, TipoAreaRec);
	}

	/** Get Receiving Area.
		@return Type of receiving area
	  */
	public String getTipoAreaRec () 
	{
		return (String)get_Value(COLUMNNAME_TipoAreaRec);
	}
}