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

/** Generated Model for EXME_EstiloVidaPaciente
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EstiloVidaPaciente extends PO implements I_EXME_EstiloVidaPaciente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EstiloVidaPaciente (Properties ctx, int EXME_EstiloVidaPaciente_ID, String trxName)
    {
      super (ctx, EXME_EstiloVidaPaciente_ID, trxName);
      /** if (EXME_EstiloVidaPaciente_ID == 0)
        {
			setEXME_EstiloVidaPaciente_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EstiloVidaPaciente (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EstiloVidaPaciente[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_ActPacienteDiag getEXME_ActPacienteDiag() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteDiag.Table_Name);
        I_EXME_ActPacienteDiag result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteDiag)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteDiag_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Diagnostic.
		@param EXME_ActPacienteDiag_ID 
		Patient's Diagnostic
	  */
	public void setEXME_ActPacienteDiag_ID (int EXME_ActPacienteDiag_ID)
	{
		if (EXME_ActPacienteDiag_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteDiag_ID, null);
		else 
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
			set_ValueNoCheck (COLUMNNAME_EXME_EncounterForms_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_EncounterForms_ID, Integer.valueOf(EXME_EncounterForms_ID));
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

	/** Set Patient's Life Style.
		@param EXME_EstiloVidaPaciente_ID Patient's Life Style	  */
	public void setEXME_EstiloVidaPaciente_ID (int EXME_EstiloVidaPaciente_ID)
	{
		if (EXME_EstiloVidaPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstiloVidaPaciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EstiloVidaPaciente_ID, Integer.valueOf(EXME_EstiloVidaPaciente_ID));
	}

	/** Get Patient's Life Style.
		@return Patient's Life Style	  */
	public int getEXME_EstiloVidaPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstiloVidaPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EstiloVida getEXME_EstiloVida() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstiloVida.Table_Name);
        I_EXME_EstiloVida result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstiloVida)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstiloVida_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Life Style.
		@param EXME_EstiloVida_ID Life Style	  */
	public void setEXME_EstiloVida_ID (int EXME_EstiloVida_ID)
	{
		if (EXME_EstiloVida_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstiloVida_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstiloVida_ID, Integer.valueOf(EXME_EstiloVida_ID));
	}

	/** Get Life Style.
		@return Life Style	  */
	public int getEXME_EstiloVida_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstiloVida_ID);
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

	/** Set Initial Date.
		@param FechaIni 
		Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni)
	{
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
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

	/** Set Monitoring.
		@param Seguimiento Monitoring	  */
	public void setSeguimiento (String Seguimiento)
	{
		set_Value (COLUMNNAME_Seguimiento, Seguimiento);
	}

	/** Get Monitoring.
		@return Monitoring	  */
	public String getSeguimiento () 
	{
		return (String)get_Value(COLUMNNAME_Seguimiento);
	}
}