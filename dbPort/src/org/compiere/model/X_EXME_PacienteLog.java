/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PacienteLog
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PacienteLog extends PO implements I_EXME_PacienteLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PacienteLog (Properties ctx, int EXME_PacienteLog_ID, String trxName)
    {
      super (ctx, EXME_PacienteLog_ID, trxName);
      /** if (EXME_PacienteLog_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setEXME_PacienteLog_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PacienteLog (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PacienteLog[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Patient Log.
		@param EXME_PacienteLog_ID 
		Patient Log
	  */
	public void setEXME_PacienteLog_ID (int EXME_PacienteLog_ID)
	{
		if (EXME_PacienteLog_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteLog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PacienteLog_ID, Integer.valueOf(EXME_PacienteLog_ID));
	}

	/** Get Patient Log.
		@return Patient Log
	  */
	public int getEXME_PacienteLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set New Name.
		@param NewName New Name	  */
	public void setNewName (String NewName)
	{
		set_Value (COLUMNNAME_NewName, NewName);
	}

	/** Get New Name.
		@return New Name	  */
	public String getNewName () 
	{
		return (String)get_Value(COLUMNNAME_NewName);
	}

	/** Set Old Name.
		@param OldName Old Name	  */
	public void setOldName (String OldName)
	{
		set_Value (COLUMNNAME_OldName, OldName);
	}

	/** Get Old Name.
		@return Old Name	  */
	public String getOldName () 
	{
		return (String)get_Value(COLUMNNAME_OldName);
	}
}