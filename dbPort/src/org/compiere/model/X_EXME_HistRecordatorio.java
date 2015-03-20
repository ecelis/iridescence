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

/** Generated Model for EXME_HistRecordatorio
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_HistRecordatorio extends PO implements I_EXME_HistRecordatorio, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_HistRecordatorio (Properties ctx, int EXME_HistRecordatorio_ID, String trxName)
    {
      super (ctx, EXME_HistRecordatorio_ID, trxName);
      /** if (EXME_HistRecordatorio_ID == 0)
        {
			setEXME_HistRecordatorio_ID (0);
			setEXME_ProgRecordatorio_ID (0);
			setFechaProg (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_HistRecordatorio (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_HistRecordatorio[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Reminder History.
		@param EXME_HistRecordatorio_ID Reminder History	  */
	public void setEXME_HistRecordatorio_ID (int EXME_HistRecordatorio_ID)
	{
		if (EXME_HistRecordatorio_ID < 1)
			 throw new IllegalArgumentException ("EXME_HistRecordatorio_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_HistRecordatorio_ID, Integer.valueOf(EXME_HistRecordatorio_ID));
	}

	/** Get Reminder History.
		@return Reminder History	  */
	public int getEXME_HistRecordatorio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_HistRecordatorio_ID);
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

	public I_EXME_ProgRecordatorio getEXME_ProgRecordatorio() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProgRecordatorio.Table_Name);
        I_EXME_ProgRecordatorio result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProgRecordatorio)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProgRecordatorio_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Reminder Programation.
		@param EXME_ProgRecordatorio_ID Reminder Programation	  */
	public void setEXME_ProgRecordatorio_ID (int EXME_ProgRecordatorio_ID)
	{
		if (EXME_ProgRecordatorio_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProgRecordatorio_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ProgRecordatorio_ID, Integer.valueOf(EXME_ProgRecordatorio_ID));
	}

	/** Get Reminder Programation.
		@return Reminder Programation	  */
	public int getEXME_ProgRecordatorio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgRecordatorio_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Is EMail.
		@param IsEMail Is EMail	  */
	public void setIsEMail (boolean IsEMail)
	{
		set_Value (COLUMNNAME_IsEMail, Boolean.valueOf(IsEMail));
	}

	/** Get Is EMail.
		@return Is EMail	  */
	public boolean isEMail () 
	{
		Object oo = get_Value(COLUMNNAME_IsEMail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is SMS.
		@param IsSMS Is SMS	  */
	public void setIsSMS (boolean IsSMS)
	{
		set_Value (COLUMNNAME_IsSMS, Boolean.valueOf(IsSMS));
	}

	/** Get Is SMS.
		@return Is SMS	  */
	public boolean isSMS () 
	{
		Object oo = get_Value(COLUMNNAME_IsSMS);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set To Doctor.
		@param ToMedico To Doctor	  */
	public void setToMedico (boolean ToMedico)
	{
		set_Value (COLUMNNAME_ToMedico, Boolean.valueOf(ToMedico));
	}

	/** Get To Doctor.
		@return To Doctor	  */
	public boolean isToMedico () 
	{
		Object oo = get_Value(COLUMNNAME_ToMedico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}