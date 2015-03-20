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

/** Generated Model for EXME_ActPacienteDiag
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ActPacienteDiag extends PO implements I_EXME_ActPacienteDiag, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ActPacienteDiag (Properties ctx, int EXME_ActPacienteDiag_ID, String trxName)
    {
      super (ctx, EXME_ActPacienteDiag_ID, trxName);
      /** if (EXME_ActPacienteDiag_ID == 0)
        {
			setEstatus (null);
// A
			setEXME_ActPacienteDiag_ID (0);
			setEXME_ActPaciente_ID (0);
			setFecha_Diagnostico (new Timestamp( System.currentTimeMillis() ));
			setISCC (false);
			setIsNosocomial (false);
// N
			setPadecimiento (false);
// N
        } */
    }

    /** Load Constructor */
    public X_EXME_ActPacienteDiag (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ActPacienteDiag[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Admitting AD_Reference_ID=1200552 */
	public static final int ADMITTING_AD_Reference_ID=1200552;
	/** Yes = Y */
	public static final String ADMITTING_Yes = "Y";
	/** No = N */
	public static final String ADMITTING_No = "N";
	/** Exempt = E */
	public static final String ADMITTING_Exempt = "E";
	/** Unknown = U */
	public static final String ADMITTING_Unknown = "U";
	/** Set Admitting Diagnostic.
		@param Admitting Admitting Diagnostic	  */
	public void setAdmitting (String Admitting)
	{

		if (Admitting == null || Admitting.equals("Y") || Admitting.equals("N") || Admitting.equals("E") || Admitting.equals("U")); else throw new IllegalArgumentException ("Admitting Invalid value - " + Admitting + " - Reference_ID=1200552 - Y - N - E - U");		set_Value (COLUMNNAME_Admitting, Admitting);
	}

	/** Get Admitting Diagnostic.
		@return Admitting Diagnostic	  */
	public String getAdmitting () 
	{
		return (String)get_Value(COLUMNNAME_Admitting);
	}

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DB Column Name.
		@param ColumnName 
		Name of the column in the database
	  */
	public void setColumnName (String ColumnName)
	{
		set_Value (COLUMNNAME_ColumnName, ColumnName);
	}

	/** Get DB Column Name.
		@return Name of the column in the database
	  */
	public String getColumnName () 
	{
		return (String)get_Value(COLUMNNAME_ColumnName);
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

	/** Set Diagnosis.
		@param Diagnosis 
		Diagnosis
	  */
	public void setDiagnosis (String Diagnosis)
	{
		set_Value (COLUMNNAME_Diagnosis, Diagnosis);
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public String getDiagnosis () 
	{
		return (String)get_Value(COLUMNNAME_Diagnosis);
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

	/** Estatus AD_Reference_ID=1200405 */
	public static final int ESTATUS_AD_Reference_ID=1200405;
	/** Active = A */
	public static final String ESTATUS_Active = "A";
	/** Resolved = R */
	public static final String ESTATUS_Resolved = "R";
	/** Inactive = I */
	public static final String ESTATUS_Inactive = "I";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		if (Estatus == null) throw new IllegalArgumentException ("Estatus is mandatory");
		if (Estatus.equals("A") || Estatus.equals("R") || Estatus.equals("I")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200405 - A - R - I");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set Patient's Diagnostic.
		@param EXME_ActPacienteDiag_ID 
		Patient's Diagnostic
	  */
	public void setEXME_ActPacienteDiag_ID (int EXME_ActPacienteDiag_ID)
	{
		if (EXME_ActPacienteDiag_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteDiag_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ActPacienteDiag_ID, Integer.valueOf(EXME_ActPacienteDiag_ID));
	}

	/** Get Patient's Diagnostic.
		@return Patient's Diagnostic
	  */
	public int getEXME_ActPacienteDiag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteDiag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Activity.
		@param EXME_ActPaciente_ID 
		Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID)
	{
		if (EXME_ActPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPaciente_ID is mandatory.");
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

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Diagnostic Date.
		@param Fecha_Diagnostico 
		Diagnostic Date
	  */
	public void setFecha_Diagnostico (Timestamp Fecha_Diagnostico)
	{
		if (Fecha_Diagnostico == null)
			throw new IllegalArgumentException ("Fecha_Diagnostico is mandatory.");
		set_Value (COLUMNNAME_Fecha_Diagnostico, Fecha_Diagnostico);
	}

	/** Get Diagnostic Date.
		@return Diagnostic Date
	  */
	public Timestamp getFecha_Diagnostico () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Diagnostico);
	}

	/** Set Fecha_Estatus.
		@param Fecha_Estatus Fecha_Estatus	  */
	public void setFecha_Estatus (Timestamp Fecha_Estatus)
	{
		set_Value (COLUMNNAME_Fecha_Estatus, Fecha_Estatus);
	}

	/** Get Fecha_Estatus.
		@return Fecha_Estatus	  */
	public Timestamp getFecha_Estatus () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Estatus);
	}

	/** Set Final Execution Date Transplantation.
		@param Fecha_Final 
		Final Execution Date Transplantation
	  */
	public void setFecha_Final (Timestamp Fecha_Final)
	{
		set_Value (COLUMNNAME_Fecha_Final, Fecha_Final);
	}

	/** Get Final Execution Date Transplantation.
		@return Final Execution Date Transplantation
	  */
	public Timestamp getFecha_Final () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Final);
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

	/** Set Is Nosocomial.
		@param IsNosocomial Is Nosocomial	  */
	public void setIsNosocomial (boolean IsNosocomial)
	{
		set_Value (COLUMNNAME_IsNosocomial, Boolean.valueOf(IsNosocomial));
	}

	/** Get Is Nosocomial.
		@return Is Nosocomial	  */
	public boolean isNosocomial () 
	{
		Object oo = get_Value(COLUMNNAME_IsNosocomial);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
	}

	/** Set Condition.
		@param Padecimiento Condition	  */
	public void setPadecimiento (boolean Padecimiento)
	{
		set_Value (COLUMNNAME_Padecimiento, Boolean.valueOf(Padecimiento));
	}

	/** Get Condition.
		@return Condition	  */
	public boolean isPadecimiento () 
	{
		Object oo = get_Value(COLUMNNAME_Padecimiento);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 1) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence of Diagnostic.
		@param SequenceDiag Sequence of Diagnostic	  */
	public void setSequenceDiag (int SequenceDiag)
	{
		set_Value (COLUMNNAME_SequenceDiag, Integer.valueOf(SequenceDiag));
	}

	/** Get Sequence of Diagnostic.
		@return Sequence of Diagnostic	  */
	public int getSequenceDiag () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SequenceDiag);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Type AD_Reference_ID=1200513 */
	public static final int TYPE_AD_Reference_ID=1200513;
	/** Medical Initial  = MI */
	public static final String TYPE_MedicalInitial = "MI";
	/** Medical Final = MF */
	public static final String TYPE_MedicalFinal = "MF";
	/** Coder Initial = CI */
	public static final String TYPE_CoderInitial = "CI";
	/** Coder Final = CF */
	public static final String TYPE_CoderFinal = "CF";
	/** Other = OT */
	public static final String TYPE_Other = "OT";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("MI") || Type.equals("MF") || Type.equals("CI") || Type.equals("CF") || Type.equals("OT")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200513 - MI - MF - CI - CF - OT");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}