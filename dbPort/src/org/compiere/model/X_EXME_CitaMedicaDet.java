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

/** Generated Model for EXME_CitaMedicaDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CitaMedicaDet extends PO implements I_EXME_CitaMedicaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CitaMedicaDet (Properties ctx, int EXME_CitaMedicaDet_ID, String trxName)
    {
      super (ctx, EXME_CitaMedicaDet_ID, trxName);
      /** if (EXME_CitaMedicaDet_ID == 0)
        {
			setAplicar (false);
// N
			setEstatus (null);
			setEXME_CitaMedicaDet_ID (0);
			setEXME_CitaMedica_ID (0);
			setEXME_Medico_ID (0);
			setISCC (false);
			setIsInstruction (false);
			setIsService (false);
// N
			setSurtir (false);
// N
			setTomadoCasa (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_CitaMedicaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CitaMedicaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Target Organization.
		@param AD_Org_Dest_ID 
		The organization to refer to
	  */
	public void setAD_Org_Dest_ID (int AD_Org_Dest_ID)
	{
		if (AD_Org_Dest_ID < 1) 
			set_Value (COLUMNNAME_AD_Org_Dest_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Org_Dest_ID, Integer.valueOf(AD_Org_Dest_ID));
	}

	/** Get Target Organization.
		@return The organization to refer to
	  */
	public int getAD_Org_Dest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Org_Dest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Apply Service.
		@param Aplicar Apply Service	  */
	public void setAplicar (boolean Aplicar)
	{
		set_Value (COLUMNNAME_Aplicar, Boolean.valueOf(Aplicar));
	}

	/** Get Apply Service.
		@return Apply Service	  */
	public boolean isAplicar () 
	{
		Object oo = get_Value(COLUMNNAME_Aplicar);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Billable.
		@param Billable Billable	  */
	public void setBillable (boolean Billable)
	{
		set_Value (COLUMNNAME_Billable, Boolean.valueOf(Billable));
	}

	/** Get Billable.
		@return Billable	  */
	public boolean isBillable () 
	{
		Object oo = get_Value(COLUMNNAME_Billable);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Quantity dosis.
		@param CantTomar Quantity dosis	  */
	public void setCantTomar (BigDecimal CantTomar)
	{
		set_Value (COLUMNNAME_CantTomar, CantTomar);
	}

	/** Get Quantity dosis.
		@return Quantity dosis	  */
	public BigDecimal getCantTomar () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CantTomar);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Consulting Provider.
		@param ConsultingProvider 
		Consulting Provider / Facility
	  */
	public void setConsultingProvider (String ConsultingProvider)
	{
		set_Value (COLUMNNAME_ConsultingProvider, ConsultingProvider);
	}

	/** Get Consulting Provider.
		@return Consulting Provider / Facility
	  */
	public String getConsultingProvider () 
	{
		return (String)get_Value(COLUMNNAME_ConsultingProvider);
	}

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Promised.
		@param DatePromised 
		Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised)
	{
		set_Value (COLUMNNAME_DatePromised, DatePromised);
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
		set_Value (COLUMNNAME_Description, Description);
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

	/** Estatus AD_Reference_ID=131 */
	public static final int ESTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String ESTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String ESTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String ESTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String ESTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String ESTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String ESTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String ESTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String ESTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String ESTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String ESTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String ESTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String ESTATUS_WaitingConfirmation = "WC";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		if (Estatus == null) throw new IllegalArgumentException ("Estatus is mandatory");
		if (Estatus.equals("DR") || Estatus.equals("CO") || Estatus.equals("AP") || Estatus.equals("NA") || Estatus.equals("VO") || Estatus.equals("IN") || Estatus.equals("RE") || Estatus.equals("CL") || Estatus.equals("??") || Estatus.equals("IP") || Estatus.equals("WP") || Estatus.equals("WC")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** estatusDiag AD_Reference_ID=1200405 */
	public static final int ESTATUSDIAG_AD_Reference_ID=1200405;
	/** Active = A */
	public static final String ESTATUSDIAG_Active = "A";
	/** Resolved = R */
	public static final String ESTATUSDIAG_Resolved = "R";
	/** Inactive = I */
	public static final String ESTATUSDIAG_Inactive = "I";
	/** Set Diagnosis Status.
		@param estatusDiag 
		Diagnosis Status
	  */
	public void setestatusDiag (String estatusDiag)
	{

		if (estatusDiag == null || estatusDiag.equals("A") || estatusDiag.equals("R") || estatusDiag.equals("I")); else throw new IllegalArgumentException ("estatusDiag Invalid value - " + estatusDiag + " - Reference_ID=1200405 - A - R - I");		set_Value (COLUMNNAME_estatusDiag, estatusDiag);
	}

	/** Get Diagnosis Status.
		@return Diagnosis Status
	  */
	public String getestatusDiag () 
	{
		return (String)get_Value(COLUMNNAME_estatusDiag);
	}

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteIndH.Table_Name);
        I_EXME_ActPacienteIndH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteIndH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteIndH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Indication.
		@param EXME_ActPacienteIndH_ID 
		Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID)
	{
		if (EXME_ActPacienteIndH_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, Integer.valueOf(EXME_ActPacienteIndH_ID));
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

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteInd.Table_Name);
        I_EXME_ActPacienteInd result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteInd)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteInd_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Indication's detail.
		@param EXME_ActPacienteInd_ID 
		Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID)
	{
		if (EXME_ActPacienteInd_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, Integer.valueOf(EXME_ActPacienteInd_ID));
	}

	/** Get Indication's detail.
		@return Indication's detail
	  */
	public int getEXME_ActPacienteInd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteInd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Appointment's execution detail.
		@param EXME_CitaMedicaDet_ID Appointment's execution detail	  */
	public void setEXME_CitaMedicaDet_ID (int EXME_CitaMedicaDet_ID)
	{
		if (EXME_CitaMedicaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_CitaMedicaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CitaMedicaDet_ID, Integer.valueOf(EXME_CitaMedicaDet_ID));
	}

	/** Get Appointment's execution detail.
		@return Appointment's execution detail	  */
	public int getEXME_CitaMedicaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CitaMedicaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CitaMedica.Table_Name);
        I_EXME_CitaMedica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CitaMedica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CitaMedica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Appointment.
		@param EXME_CitaMedica_ID 
		Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID)
	{
		if (EXME_CitaMedica_ID < 1)
			 throw new IllegalArgumentException ("EXME_CitaMedica_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CitaMedica_ID, Integer.valueOf(EXME_CitaMedica_ID));
	}

	/** Get Medical Appointment.
		@return Medical appointment
	  */
	public int getEXME_CitaMedica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CitaMedica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Second Diagnostic.
		@param EXME_Diagnostico2_ID 
		Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID)
	{
		if (EXME_Diagnostico2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, Integer.valueOf(EXME_Diagnostico2_ID));
	}

	/** Get Second Diagnostic.
		@return Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Third Diagnostic.
		@param EXME_Diagnostico3_ID 
		Third Diagnostic
	  */
	public void setEXME_Diagnostico3_ID (int EXME_Diagnostico3_ID)
	{
		if (EXME_Diagnostico3_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, Integer.valueOf(EXME_Diagnostico3_ID));
	}

	/** Get Third Diagnostic.
		@return Third Diagnostic
	  */
	public int getEXME_Diagnostico3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico3_ID);
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

	public I_EXME_Dosis getEXME_Dosis() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Dosis.Table_Name);
        I_EXME_Dosis result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Dosis)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Dosis_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dose.
		@param EXME_Dosis_ID Dose	  */
	public void setEXME_Dosis_ID (int EXME_Dosis_ID)
	{
		if (EXME_Dosis_ID < 1) 
			set_Value (COLUMNNAME_EXME_Dosis_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Dosis_ID, Integer.valueOf(EXME_Dosis_ID));
	}

	/** Get Dose.
		@return Dose	  */
	public int getEXME_Dosis_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dosis_ID);
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

	public I_EXME_Instructions getEXME_Instructions() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Instructions.Table_Name);
        I_EXME_Instructions result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Instructions)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Instructions_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Instructions.
		@param EXME_Instructions_ID 
		General instructions
	  */
	public void setEXME_Instructions_ID (int EXME_Instructions_ID)
	{
		if (EXME_Instructions_ID < 1) 
			set_Value (COLUMNNAME_EXME_Instructions_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Instructions_ID, Integer.valueOf(EXME_Instructions_ID));
	}

	/** Get Instructions.
		@return General instructions
	  */
	public int getEXME_Instructions_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Instructions_ID);
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

	public I_EXME_Modifiers getEXME_Modifiers() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Modifiers.Table_Name);
        I_EXME_Modifiers result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Modifiers)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Modifiers_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_Modifiers_ID.
		@param EXME_Modifiers_ID EXME_Modifiers_ID	  */
	public void setEXME_Modifiers_ID (int EXME_Modifiers_ID)
	{
		if (EXME_Modifiers_ID < 1) 
			set_Value (COLUMNNAME_EXME_Modifiers_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Modifiers_ID, Integer.valueOf(EXME_Modifiers_ID));
	}

	/** Get EXME_Modifiers_ID.
		@return EXME_Modifiers_ID	  */
	public int getEXME_Modifiers_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Modifiers_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_OrderSet.Table_Name);
        I_EXME_OrderSet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_OrderSet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_OrderSet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Order Set.
		@param EXME_OrderSet_ID Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID)
	{
		if (EXME_OrderSet_ID < 1) 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, Integer.valueOf(EXME_OrderSet_ID));
	}

	/** Get Order Set.
		@return Order Set	  */
	public int getEXME_OrderSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderSet_ID);
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

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase_Version.Table_Name);
        I_EXME_PaqBase_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ViasAdministracion.Table_Name);
        I_EXME_ViasAdministracion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ViasAdministracion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ViasAdministracion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Route of Administration.
		@param EXME_ViasAdministracion_ID Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID)
	{
		if (EXME_ViasAdministracion_ID < 1) 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, Integer.valueOf(EXME_ViasAdministracion_ID));
	}

	/** Get Route of Administration.
		@return Route of Administration	  */
	public int getEXME_ViasAdministracion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ViasAdministracion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ISCC.
		@param ISCC ISCC	  */
	public void setISCC (boolean ISCC)
	{
		set_Value (COLUMNNAME_ISCC, Boolean.valueOf(ISCC));
	}

	/** Get ISCC.
		@return ISCC	  */
	public boolean isCC () 
	{
		Object oo = get_Value(COLUMNNAME_ISCC);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set External.
		@param IsExternal 
		External
	  */
	public void setIsExternal (boolean IsExternal)
	{
		set_Value (COLUMNNAME_IsExternal, Boolean.valueOf(IsExternal));
	}

	/** Get External.
		@return External
	  */
	public boolean isExternal () 
	{
		Object oo = get_Value(COLUMNNAME_IsExternal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Instruction.
		@param IsInstruction 
		Indicates if is an instruction
	  */
	public void setIsInstruction (boolean IsInstruction)
	{
		set_Value (COLUMNNAME_IsInstruction, Boolean.valueOf(IsInstruction));
	}

	/** Get Is Instruction.
		@return Indicates if is an instruction
	  */
	public boolean isInstruction () 
	{
		Object oo = get_Value(COLUMNNAME_IsInstruction);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsProblem.
		@param IsProblem 
		Is Problem
	  */
	public void setIsProblem (boolean IsProblem)
	{
		set_Value (COLUMNNAME_IsProblem, Boolean.valueOf(IsProblem));
	}

	/** Get IsProblem.
		@return Is Problem
	  */
	public boolean isProblem () 
	{
		Object oo = get_Value(COLUMNNAME_IsProblem);
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

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
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

	/** Set Number of days.
		@param NumDias Number of days	  */
	public void setNumDias (int NumDias)
	{
		set_Value (COLUMNNAME_NumDias, Integer.valueOf(NumDias));
	}

	/** Get Number of days.
		@return Number of days	  */
	public int getNumDias () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumDias);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Other Instructions.
		@param OtherInstructions 
		Other Instructions
	  */
	public void setOtherInstructions (String OtherInstructions)
	{
		set_Value (COLUMNNAME_OtherInstructions, OtherInstructions);
	}

	/** Get Other Instructions.
		@return Other Instructions
	  */
	public String getOtherInstructions () 
	{
		return (String)get_Value(COLUMNNAME_OtherInstructions);
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

	/** ProductType AD_Reference_ID=1200511 */
	public static final int PRODUCTTYPE_AD_Reference_ID=1200511;
	/** Procedure = PR */
	public static final String PRODUCTTYPE_Procedure = "PR";
	/** Studie = ST */
	public static final String PRODUCTTYPE_Studie = "ST";
	/** Item = IT */
	public static final String PRODUCTTYPE_Item = "IT";
	/** Other = OT */
	public static final String PRODUCTTYPE_Other = "OT";
	/** TodayService = TS */
	public static final String PRODUCTTYPE_TodayService = "TS";
	/** Set Product Type.
		@param ProductType 
		Type of product
	  */
	public void setProductType (String ProductType)
	{

		if (ProductType == null || ProductType.equals("PR") || ProductType.equals("ST") || ProductType.equals("IT") || ProductType.equals("OT") || ProductType.equals("TS")); else throw new IllegalArgumentException ("ProductType Invalid value - " + ProductType + " - Reference_ID=1200511 - PR - ST - IT - OT - TS");		set_Value (COLUMNNAME_ProductType, ProductType);
	}

	/** Get Product Type.
		@return Type of product
	  */
	public String getProductType () 
	{
		return (String)get_Value(COLUMNNAME_ProductType);
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

	/** Set Supplier.
		@param Proveedor Supplier	  */
	public void setProveedor (String Proveedor)
	{
		set_Value (COLUMNNAME_Proveedor, Proveedor);
	}

	/** Get Supplier.
		@return Supplier	  */
	public String getProveedor () 
	{
		return (String)get_Value(COLUMNNAME_Proveedor);
	}

	/** Set Ordered Quantity.
		@param QtyOrdered 
		Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		set_Value (COLUMNNAME_QtyOrdered, QtyOrdered);
	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Refills.
		@param Resurtidos Refills	  */
	public void setResurtidos (BigDecimal Resurtidos)
	{
		set_Value (COLUMNNAME_Resurtidos, Resurtidos);
	}

	/** Get Refills.
		@return Refills	  */
	public BigDecimal getResurtidos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Resurtidos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Deliver.
		@param Surtir 
		If the product or service will deliver internally
	  */
	public void setSurtir (boolean Surtir)
	{
		set_Value (COLUMNNAME_Surtir, Boolean.valueOf(Surtir));
	}

	/** Get Deliver.
		@return If the product or service will deliver internally
	  */
	public boolean isSurtir () 
	{
		Object oo = get_Value(COLUMNNAME_Surtir);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** TipoSurtido AD_Reference_ID=1200369 */
	public static final int TIPOSURTIDO_AD_Reference_ID=1200369;
	/** No product selection indicated = 0 */
	public static final String TIPOSURTIDO_NoProductSelectionIndicated = "0";
	/** Substitution Not Allowed by Prescriber = 1 */
	public static final String TIPOSURTIDO_SubstitutionNotAllowedByPrescriber = "1";
	/** Substitution Allowed?Patient Requested Product Dispensed = 2 */
	public static final String TIPOSURTIDO_SubstitutionAllowedPatientRequestedProductDispensed = "2";
	/** Substitution Allowed?Pharmacist Selected Product Dispensed = 3 */
	public static final String TIPOSURTIDO_SubstitutionAllowedPharmacistSelectedProductDispensed = "3";
	/** Substitution Allowed?Generic Drug Not in Stock = 4 */
	public static final String TIPOSURTIDO_SubstitutionAllowedGenericDrugNotInStock = "4";
	/** Substitution Allowed?Brand Drug Dispensed as a Generic = 5 */
	public static final String TIPOSURTIDO_SubstitutionAllowedBrandDrugDispensedAsAGeneric = "5";
	/** Override = 6 */
	public static final String TIPOSURTIDO_Override = "6";
	/** Substitution Not Allowed?Brand Drug Mandated by Law = 7 */
	public static final String TIPOSURTIDO_SubstitutionNotAllowedBrandDrugMandatedByLaw = "7";
	/** Substitution Allowed?Generic Drug Not Available in Marketpla = 8 */
	public static final String TIPOSURTIDO_SubstitutionAllowedGenericDrugNotAvailableInMarketpla = "8";
	/** Other = 9 */
	public static final String TIPOSURTIDO_Other = "9";
	/** Set Dispense as written.
		@param TipoSurtido 
		Dispense as written  (DAW)
	  */
	public void setTipoSurtido (String TipoSurtido)
	{

		if (TipoSurtido == null || TipoSurtido.equals("0") || TipoSurtido.equals("1") || TipoSurtido.equals("2") || TipoSurtido.equals("3") || TipoSurtido.equals("4") || TipoSurtido.equals("5") || TipoSurtido.equals("6") || TipoSurtido.equals("7") || TipoSurtido.equals("8") || TipoSurtido.equals("9")); else throw new IllegalArgumentException ("TipoSurtido Invalid value - " + TipoSurtido + " - Reference_ID=1200369 - 0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9");		set_Value (COLUMNNAME_TipoSurtido, TipoSurtido);
	}

	/** Get Dispense as written.
		@return Dispense as written  (DAW)
	  */
	public String getTipoSurtido () 
	{
		return (String)get_Value(COLUMNNAME_TipoSurtido);
	}

	/** Set Taken at home.
		@param TomadoCasa Taken at home	  */
	public void setTomadoCasa (boolean TomadoCasa)
	{
		set_Value (COLUMNNAME_TomadoCasa, Boolean.valueOf(TomadoCasa));
	}

	/** Get Taken at home.
		@return Taken at home	  */
	public boolean isTomadoCasa () 
	{
		Object oo = get_Value(COLUMNNAME_TomadoCasa);
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

	/** Set Day Timing.
		@param VecesDia Day Timing	  */
	public void setVecesDia (int VecesDia)
	{
		set_Value (COLUMNNAME_VecesDia, Integer.valueOf(VecesDia));
	}

	/** Get Day Timing.
		@return Day Timing	  */
	public int getVecesDia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_VecesDia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}