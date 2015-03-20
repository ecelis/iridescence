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

/** Generated Model for EXME_ProgQuiro
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ProgQuiro extends PO implements I_EXME_ProgQuiro, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProgQuiro (Properties ctx, int EXME_ProgQuiro_ID, String trxName)
    {
      super (ctx, EXME_ProgQuiro_ID, trxName);
      /** if (EXME_ProgQuiro_ID == 0)
        {
			setC_DocType_ID (0);
			setC_DocTypeTarget_ID (0);
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDocAction (null);
			setDocStatus (null);
			setDocumentNo (null);
			setEXME_Medico_ID (0);
			setEXME_ProgQuiro_ID (0);
			setEXME_Quirofano_ID (0);
			setEXME_Warehouse_ID (0);
			setFechaProg (new Timestamp( System.currentTimeMillis() ));
			setHoraCierre (Env.ZERO);
			setHoraFin (Env.ZERO);
			setHoraInicio (Env.ZERO);
			setIsApproved (false);
			setIsCreditApproved (false);
			setIsInvoiced (false);
			setIsPrinted (false);
			setPriorityRule (null);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProgQuiro (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProgQuiro[")
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

	/** Set Nurse.
		@param AD_User_Enfermera_ID Nurse	  */
	public void setAD_User_Enfermera_ID (int AD_User_Enfermera_ID)
	{
		if (AD_User_Enfermera_ID < 1) 
			set_Value (COLUMNNAME_AD_User_Enfermera_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_Enfermera_ID, Integer.valueOf(AD_User_Enfermera_ID));
	}

	/** Get Nurse.
		@return Nurse	  */
	public int getAD_User_Enfermera_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_Enfermera_ID);
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

	/** Set Comment.
		@param Comentario Comment	  */
	public void setComentario (String Comentario)
	{
		set_Value (COLUMNNAME_Comentario, Comentario);
	}

	/** Get Comment.
		@return Comment	  */
	public String getComentario () 
	{
		return (String)get_Value(COLUMNNAME_Comentario);
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

	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
		if (DocAction == null)
			throw new IllegalArgumentException ("DocAction is mandatory.");
		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=1200103 */
	public static final int DOCSTATUS_AD_Reference_ID=1200103;
	/** Pending = 1 */
	public static final String DOCSTATUS_Pending = "1";
	/** Immediate Next = 2 */
	public static final String DOCSTATUS_ImmediateNext = "2";
	/** Active and Non Close = 3 */
	public static final String DOCSTATUS_ActiveAndNonClose = "3";
	/** Closed = 4 */
	public static final String DOCSTATUS_Closed = "4";
	/** Scheduled unconfirmed = 5 */
	public static final String DOCSTATUS_ScheduledUnconfirmed = "5";
	/** Available = 6 */
	public static final String DOCSTATUS_Available = "6";
	/** Without regard patient = 7 */
	public static final String DOCSTATUS_WithoutRegardPatient = "7";
	/** List = 8 */
	public static final String DOCSTATUS_List = "8";
	/** Canceled = 9 */
	public static final String DOCSTATUS_Canceled = "9";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
		if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
		if (DocStatus.equals("1") || DocStatus.equals("2") || DocStatus.equals("3") || DocStatus.equals("4") || DocStatus.equals("5") || DocStatus.equals("6") || DocStatus.equals("7") || DocStatus.equals("8") || DocStatus.equals("9")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=1200103 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9");		set_Value (COLUMNNAME_DocStatus, DocStatus);
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

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Enfermeria.Table_Name);
        I_EXME_Enfermeria result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Enfermeria)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Enfermeria_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary staff.
		@param EXME_Enfermeria_ID 
		Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID)
	{
		if (EXME_Enfermeria_ID < 1) 
			set_Value (COLUMNNAME_EXME_Enfermeria_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Enfermeria_ID, Integer.valueOf(EXME_Enfermeria_ID));
	}

	/** Get Infirmary staff.
		@return Infirmary staff
	  */
	public int getEXME_Enfermeria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Scrub Nurse.
		@param EXME_EnfermeriaInst_ID Scrub Nurse	  */
	public void setEXME_EnfermeriaInst_ID (int EXME_EnfermeriaInst_ID)
	{
		if (EXME_EnfermeriaInst_ID < 1) 
			set_Value (COLUMNNAME_EXME_EnfermeriaInst_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EnfermeriaInst_ID, Integer.valueOf(EXME_EnfermeriaInst_ID));
	}

	/** Get Scrub Nurse.
		@return Scrub Nurse	  */
	public int getEXME_EnfermeriaInst_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfermeriaInst_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Surgery Performed.
		@param EXME_IntervencionReal_ID Surgery Performed	  */
	public void setEXME_IntervencionReal_ID (int EXME_IntervencionReal_ID)
	{
		if (EXME_IntervencionReal_ID < 1) 
			set_Value (COLUMNNAME_EXME_IntervencionReal_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_IntervencionReal_ID, Integer.valueOf(EXME_IntervencionReal_ID));
	}

	/** Get Surgery Performed.
		@return Surgery Performed	  */
	public int getEXME_IntervencionReal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_IntervencionReal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Doctor 3.
		@param EXME_Medico3_ID Doctor 3	  */
	public void setEXME_Medico3_ID (int EXME_Medico3_ID)
	{
		if (EXME_Medico3_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico3_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico3_ID, Integer.valueOf(EXME_Medico3_ID));
	}

	/** Get Doctor 3.
		@return Doctor 3	  */
	public int getEXME_Medico3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor 4.
		@param EXME_Medico4_ID Doctor 4	  */
	public void setEXME_Medico4_ID (int EXME_Medico4_ID)
	{
		if (EXME_Medico4_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico4_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico4_ID, Integer.valueOf(EXME_Medico4_ID));
	}

	/** Get Doctor 4.
		@return Doctor 4	  */
	public int getEXME_Medico4_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico4_ID);
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

	public I_EXME_PreferenceCard getEXME_PreferenceCard() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PreferenceCard.Table_Name);
        I_EXME_PreferenceCard result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PreferenceCard)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PreferenceCard_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Preference Card.
		@param EXME_PreferenceCard_ID Preference Card	  */
	public void setEXME_PreferenceCard_ID (int EXME_PreferenceCard_ID)
	{
		if (EXME_PreferenceCard_ID < 1) 
			set_Value (COLUMNNAME_EXME_PreferenceCard_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PreferenceCard_ID, Integer.valueOf(EXME_PreferenceCard_ID));
	}

	/** Get Preference Card.
		@return Preference Card	  */
	public int getEXME_PreferenceCard_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreferenceCard_ID);
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

	/** Set Schedule of Surgery Room.
		@param EXME_ProgQuiro_ID 
		Schedule of Surgery Room
	  */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID)
	{
		if (EXME_ProgQuiro_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProgQuiro_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProgQuiro_ID, Integer.valueOf(EXME_ProgQuiro_ID));
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

	/** Set Surgery Room.
		@param EXME_Quirofano_ID 
		Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID)
	{
		if (EXME_Quirofano_ID < 1)
			 throw new IllegalArgumentException ("EXME_Quirofano_ID is mandatory.");
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

	/** Set Warehouse.
		@param EXME_Warehouse_ID Warehouse	  */
	public void setEXME_Warehouse_ID (int EXME_Warehouse_ID)
	{
		if (EXME_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("EXME_Warehouse_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Warehouse_ID, Integer.valueOf(EXME_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Warehouse	  */
	public int getEXME_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Anesthesia End Time.
		@param FechaAnestesiaFin 
		Anesthesia End Time
	  */
	public void setFechaAnestesiaFin (Timestamp FechaAnestesiaFin)
	{
		set_Value (COLUMNNAME_FechaAnestesiaFin, FechaAnestesiaFin);
	}

	/** Get Anesthesia End Time.
		@return Anesthesia End Time
	  */
	public Timestamp getFechaAnestesiaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAnestesiaFin);
	}

	/** Set Anesthesia Start Time.
		@param FechaAnestesiaInicio 
		Anesthesia Start Time
	  */
	public void setFechaAnestesiaInicio (Timestamp FechaAnestesiaInicio)
	{
		set_Value (COLUMNNAME_FechaAnestesiaInicio, FechaAnestesiaInicio);
	}

	/** Get Anesthesia Start Time.
		@return Anesthesia Start Time
	  */
	public Timestamp getFechaAnestesiaInicio () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAnestesiaInicio);
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

	/** Set Ending Date.
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
	}

	/** Set Beginning Date.
		@param FechaInicio Beginning Date	  */
	public void setFechaInicio (Timestamp FechaInicio)
	{
		set_Value (COLUMNNAME_FechaInicio, FechaInicio);
	}

	/** Get Beginning Date.
		@return Beginning Date	  */
	public Timestamp getFechaInicio () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaInicio);
	}

	/** Set Scheduled Date.
		@param FechaProg Scheduled Date	  */
	public void setFechaProg (Timestamp FechaProg)
	{
		if (FechaProg == null)
			throw new IllegalArgumentException ("FechaProg is mandatory.");
		set_Value (COLUMNNAME_FechaProg, FechaProg);
	}

	/** Get Scheduled Date.
		@return Scheduled Date	  */
	public Timestamp getFechaProg () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaProg);
	}

	/** Set Closing Hour.
		@param HoraCierre Closing Hour	  */
	public void setHoraCierre (BigDecimal HoraCierre)
	{
		if (HoraCierre == null)
			throw new IllegalArgumentException ("HoraCierre is mandatory.");
		set_Value (COLUMNNAME_HoraCierre, HoraCierre);
	}

	/** Get Closing Hour.
		@return Closing Hour	  */
	public BigDecimal getHoraCierre () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HoraCierre);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set End Hour.
		@param HoraFin End Hour	  */
	public void setHoraFin (BigDecimal HoraFin)
	{
		if (HoraFin == null)
			throw new IllegalArgumentException ("HoraFin is mandatory.");
		set_Value (COLUMNNAME_HoraFin, HoraFin);
	}

	/** Get End Hour.
		@return End Hour	  */
	public BigDecimal getHoraFin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HoraFin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Start Hour.
		@param HoraInicio Start Hour	  */
	public void setHoraInicio (BigDecimal HoraInicio)
	{
		if (HoraInicio == null)
			throw new IllegalArgumentException ("HoraInicio is mandatory.");
		set_Value (COLUMNNAME_HoraInicio, HoraInicio);
	}

	/** Get Start Hour.
		@return Start Hour	  */
	public BigDecimal getHoraInicio () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HoraInicio);
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

	/** Set Comment of the reason for suspension.
		@param MotivoCancelacion 
		Comment of the reason for suspension
	  */
	public void setMotivoCancelacion (String MotivoCancelacion)
	{
		set_Value (COLUMNNAME_MotivoCancelacion, MotivoCancelacion);
	}

	/** Get Comment of the reason for suspension.
		@return Comment of the reason for suspension
	  */
	public String getMotivoCancelacion () 
	{
		return (String)get_Value(COLUMNNAME_MotivoCancelacion);
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

	/** Set Patient Name.
		@param NombrePac Patient Name	  */
	public void setNombrePac (String NombrePac)
	{
		set_Value (COLUMNNAME_NombrePac, NombrePac);
	}

	/** Get Patient Name.
		@return Patient Name	  */
	public String getNombrePac () 
	{
		return (String)get_Value(COLUMNNAME_NombrePac);
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

	/** Set User Close.
		@param UserCierre User Close	  */
	public void setUserCierre (int UserCierre)
	{
		set_Value (COLUMNNAME_UserCierre, Integer.valueOf(UserCierre));
	}

	/** Get User Close.
		@return User Close	  */
	public int getUserCierre () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UserCierre);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}