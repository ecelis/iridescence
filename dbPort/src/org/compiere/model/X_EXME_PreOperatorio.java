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

/** Generated Model for EXME_PreOperatorio
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PreOperatorio extends PO implements I_EXME_PreOperatorio, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PreOperatorio (Properties ctx, int EXME_PreOperatorio_ID, String trxName)
    {
      super (ctx, EXME_PreOperatorio_ID, trxName);
      /** if (EXME_PreOperatorio_ID == 0)
        {
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDocAction (null);
			setDocStatus (null);
			setEXME_Especialidad_ID (0);
			setEXME_Medico_ID (0);
			setEXME_PreOperatorio_ID (0);
			setForaneo (false);
// N
			setIsApproved (true);
// Y
			setM_Warehouse_Sol_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PreOperatorio (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PreOperatorio[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Duration.
		@param Duracion 
		Duration
	  */
	public void setDuracion (BigDecimal Duracion)
	{
		set_Value (COLUMNNAME_Duracion, Duracion);
	}

	/** Get Duration.
		@return Duration
	  */
	public BigDecimal getDuracion () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Duracion);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Age.
		@param Edad 
		Age
	  */
	public void setEdad (String Edad)
	{
		set_Value (COLUMNNAME_Edad, Edad);
	}

	/** Get Age.
		@return Age
	  */
	public String getEdad () 
	{
		return (String)get_Value(COLUMNNAME_Edad);
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

	/** Set Diagnosis Post Surgery.
		@param EXME_Diagnostico_Post_ID Diagnosis Post Surgery	  */
	public void setEXME_Diagnostico_Post_ID (int EXME_Diagnostico_Post_ID)
	{
		if (EXME_Diagnostico_Post_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico_Post_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_Post_ID, Integer.valueOf(EXME_Diagnostico_Post_ID));
	}

	/** Get Diagnosis Post Surgery.
		@return Diagnosis Post Surgery	  */
	public int getEXME_Diagnostico_Post_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_Post_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad.Table_Name);
        I_EXME_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Intervencion.Table_Name);
        I_EXME_Intervencion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Intervencion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Intervencion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Intervention.
		@param EXME_Intervencion_ID 
		Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get Intervention.
		@return Intervention
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Anesthesiologists.
		@param EXME_Medico_Anest_ID Anesthesiologists	  */
	public void setEXME_Medico_Anest_ID (int EXME_Medico_Anest_ID)
	{
		if (EXME_Medico_Anest_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_Anest_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_Anest_ID, Integer.valueOf(EXME_Medico_Anest_ID));
	}

	/** Get Anesthesiologists.
		@return Anesthesiologists	  */
	public int getEXME_Medico_Anest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_Anest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M.D. Approbation.
		@param EXME_Medico_Aprob_ID M.D. Approbation	  */
	public void setEXME_Medico_Aprob_ID (int EXME_Medico_Aprob_ID)
	{
		if (EXME_Medico_Aprob_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_Aprob_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_Aprob_ID, Integer.valueOf(EXME_Medico_Aprob_ID));
	}

	/** Get M.D. Approbation.
		@return M.D. Approbation	  */
	public int getEXME_Medico_Aprob_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_Aprob_ID);
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

	public I_EXME_MotivoReagenda getEXME_MotivoReagenda() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoReagenda.Table_Name);
        I_EXME_MotivoReagenda result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoReagenda)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoReagenda_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Reason for Reschedule.
		@param EXME_MotivoReagenda_ID 
		Reason for Reschedule
	  */
	public void setEXME_MotivoReagenda_ID (int EXME_MotivoReagenda_ID)
	{
		if (EXME_MotivoReagenda_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoReagenda_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoReagenda_ID, Integer.valueOf(EXME_MotivoReagenda_ID));
	}

	/** Get Reason for Reschedule.
		@return Reason for Reschedule
	  */
	public int getEXME_MotivoReagenda_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoReagenda_ID);
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

	public I_EXME_PatientClass getEXME_PatientClass() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PatientClass.Table_Name);
        I_EXME_PatientClass result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PatientClass)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PatientClass_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Classification.
		@param EXME_PatientClass_ID Patient Classification	  */
	public void setEXME_PatientClass_ID (int EXME_PatientClass_ID)
	{
		if (EXME_PatientClass_ID < 1) 
			set_Value (COLUMNNAME_EXME_PatientClass_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PatientClass_ID, Integer.valueOf(EXME_PatientClass_ID));
	}

	/** Get Patient Classification.
		@return Patient Classification	  */
	public int getEXME_PatientClass_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PatientClass_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Pre Surgery.
		@param EXME_PreOperatorio_ID 
		Pre Surgery
	  */
	public void setEXME_PreOperatorio_ID (int EXME_PreOperatorio_ID)
	{
		if (EXME_PreOperatorio_ID < 1)
			 throw new IllegalArgumentException ("EXME_PreOperatorio_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PreOperatorio_ID, Integer.valueOf(EXME_PreOperatorio_ID));
	}

	/** Get Pre Surgery.
		@return Pre Surgery
	  */
	public int getEXME_PreOperatorio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreOperatorio_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reference to Application of Surgery.
		@param EXME_PreOperatorioReagen_ID 
		Reference to Application of Surgery
	  */
	public void setEXME_PreOperatorioReagen_ID (int EXME_PreOperatorioReagen_ID)
	{
		if (EXME_PreOperatorioReagen_ID < 1) 
			set_Value (COLUMNNAME_EXME_PreOperatorioReagen_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PreOperatorioReagen_ID, Integer.valueOf(EXME_PreOperatorioReagen_ID));
	}

	/** Get Reference to Application of Surgery.
		@return Reference to Application of Surgery
	  */
	public int getEXME_PreOperatorioReagen_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreOperatorioReagen_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProcAnestesico getEXME_ProcAnestesico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProcAnestesico.Table_Name);
        I_EXME_ProcAnestesico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProcAnestesico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProcAnestesico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Anesthetic Procedure.
		@param EXME_ProcAnestesico_ID 
		Anesthetic Procedure
	  */
	public void setEXME_ProcAnestesico_ID (int EXME_ProcAnestesico_ID)
	{
		if (EXME_ProcAnestesico_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProcAnestesico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProcAnestesico_ID, Integer.valueOf(EXME_ProcAnestesico_ID));
	}

	/** Get Anesthetic Procedure.
		@return Anesthetic Procedure
	  */
	public int getEXME_ProcAnestesico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcAnestesico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProgQuiro getEXME_ProgQuiro() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProgQuiro.Table_Name);
        I_EXME_ProgQuiro result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProgQuiro)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProgQuiro_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Schedule of Surgery Room.
		@param EXME_ProgQuiro_ID 
		Schedule of Surgery Room
	  */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID)
	{
		if (EXME_ProgQuiro_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProgQuiro_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProgQuiro_ID, Integer.valueOf(EXME_ProgQuiro_ID));
	}

	/** Get Schedule of Surgery Room.
		@return Schedule of Surgery Room
	  */
	public int getEXME_ProgQuiro_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgQuiro_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Quirofano getEXME_Quirofano() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Quirofano.Table_Name);
        I_EXME_Quirofano result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Quirofano)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Quirofano_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Surgery Room.
		@param EXME_Quirofano_ID 
		Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID)
	{
		if (EXME_Quirofano_ID < 1) 
			set_Value (COLUMNNAME_EXME_Quirofano_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Quirofano_ID, Integer.valueOf(EXME_Quirofano_ID));
	}

	/** Get Surgery Room.
		@return Surgey Room
	  */
	public int getEXME_Quirofano_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Quirofano_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Date of approval of anesthesia.
		@param FechaAnest Date of approval of anesthesia	  */
	public void setFechaAnest (Timestamp FechaAnest)
	{
		set_Value (COLUMNNAME_FechaAnest, FechaAnest);
	}

	/** Get Date of approval of anesthesia.
		@return Date of approval of anesthesia	  */
	public Timestamp getFechaAnest () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAnest);
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

	/** Set Scheduled Date.
		@param FechaProg Scheduled Date	  */
	public void setFechaProg (Timestamp FechaProg)
	{
		set_Value (COLUMNNAME_FechaProg, FechaProg);
	}

	/** Get Scheduled Date.
		@return Scheduled Date	  */
	public Timestamp getFechaProg () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaProg);
	}

	/** Set Foreign.
		@param Foraneo Foreign	  */
	public void setForaneo (boolean Foraneo)
	{
		set_Value (COLUMNNAME_Foraneo, Boolean.valueOf(Foraneo));
	}

	/** Get Foreign.
		@return Foreign	  */
	public boolean isForaneo () 
	{
		Object oo = get_Value(COLUMNNAME_Foraneo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set IsApprovedAnest.
		@param IsApprovedAnest IsApprovedAnest	  */
	public void setIsApprovedAnest (boolean IsApprovedAnest)
	{
		set_Value (COLUMNNAME_IsApprovedAnest, Boolean.valueOf(IsApprovedAnest));
	}

	/** Get IsApprovedAnest.
		@return IsApprovedAnest	  */
	public boolean isApprovedAnest () 
	{
		Object oo = get_Value(COLUMNNAME_IsApprovedAnest);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Anesthetic Products.
		@param M_ProdAnestesico_ID Anesthetic Products	  */
	public void setM_ProdAnestesico_ID (int M_ProdAnestesico_ID)
	{
		if (M_ProdAnestesico_ID < 1) 
			set_Value (COLUMNNAME_M_ProdAnestesico_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProdAnestesico_ID, Integer.valueOf(M_ProdAnestesico_ID));
	}

	/** Get Anesthetic Products.
		@return Anesthetic Products	  */
	public int getM_ProdAnestesico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProdAnestesico_ID);
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
			 throw new IllegalArgumentException ("M_Warehouse_Sol_ID is mandatory.");
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

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		set_Value (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pac);
	}

	/** Set Num. Reschedule.
		@param NoReagenda Num. Reschedule	  */
	public void setNoReagenda (BigDecimal NoReagenda)
	{
		set_Value (COLUMNNAME_NoReagenda, NoReagenda);
	}

	/** Get Num. Reschedule.
		@return Num. Reschedule	  */
	public BigDecimal getNoReagenda () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NoReagenda);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Notes Reschedule.
		@param NotasReagenda 
		Notes Reschedule
	  */
	public void setNotasReagenda (String NotasReagenda)
	{
		set_Value (COLUMNNAME_NotasReagenda, NotasReagenda);
	}

	/** Get Notes Reschedule.
		@return Notes Reschedule
	  */
	public String getNotasReagenda () 
	{
		return (String)get_Value(COLUMNNAME_NotasReagenda);
	}

	/** PriorityRule AD_Reference_ID=1200171 */
	public static final int PRIORITYRULE_AD_Reference_ID=1200171;
	/** Emergency = U */
	public static final String PRIORITYRULE_Emergency = "U";
	/** Scheduled = P */
	public static final String PRIORITYRULE_Scheduled = "P";
	/** Opportunity = O */
	public static final String PRIORITYRULE_Opportunity = "O";
	/** Set Priority.
		@param PriorityRule 
		Priority of a document
	  */
	public void setPriorityRule (String PriorityRule)
	{

		if (PriorityRule == null || PriorityRule.equals("U") || PriorityRule.equals("P") || PriorityRule.equals("O")); else throw new IllegalArgumentException ("PriorityRule Invalid value - " + PriorityRule + " - Reference_ID=1200171 - U - P - O");		set_Value (COLUMNNAME_PriorityRule, PriorityRule);
	}

	/** Get Priority.
		@return Priority of a document
	  */
	public String getPriorityRule () 
	{
		return (String)get_Value(COLUMNNAME_PriorityRule);
	}

	/** Set Scheduled.
		@param Programado Scheduled	  */
	public void setProgramado (boolean Programado)
	{
		set_Value (COLUMNNAME_Programado, Boolean.valueOf(Programado));
	}

	/** Get Scheduled.
		@return Scheduled	  */
	public boolean isProgramado () 
	{
		Object oo = get_Value(COLUMNNAME_Programado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Type of anesthesia.
		@param TipoAnestesia Type of anesthesia	  */
	public void setTipoAnestesia (String TipoAnestesia)
	{
		set_Value (COLUMNNAME_TipoAnestesia, TipoAnestesia);
	}

	/** Get Type of anesthesia.
		@return Type of anesthesia	  */
	public String getTipoAnestesia () 
	{
		return (String)get_Value(COLUMNNAME_TipoAnestesia);
	}

	/** TipoCirugia AD_Reference_ID=1200175 */
	public static final int TIPOCIRUGIA_AD_Reference_ID=1200175;
	/** Hospitalization = H */
	public static final String TIPOCIRUGIA_Hospitalization = "H";
	/** Ambulatory = A */
	public static final String TIPOCIRUGIA_Ambulatory = "A";
	/** Set Surgery Type.
		@param TipoCirugia Surgery Type	  */
	public void setTipoCirugia (String TipoCirugia)
	{

		if (TipoCirugia == null || TipoCirugia.equals("H") || TipoCirugia.equals("A")); else throw new IllegalArgumentException ("TipoCirugia Invalid value - " + TipoCirugia + " - Reference_ID=1200175 - H - A");		set_Value (COLUMNNAME_TipoCirugia, TipoCirugia);
	}

	/** Get Surgery Type.
		@return Surgery Type	  */
	public String getTipoCirugia () 
	{
		return (String)get_Value(COLUMNNAME_TipoCirugia);
	}

	/** Set Transplant.
		@param Trasplante Transplant	  */
	public void setTrasplante (boolean Trasplante)
	{
		set_Value (COLUMNNAME_Trasplante, Boolean.valueOf(Trasplante));
	}

	/** Get Transplant.
		@return Transplant	  */
	public boolean isTrasplante () 
	{
		Object oo = get_Value(COLUMNNAME_Trasplante);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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