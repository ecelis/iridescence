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

/** Generated Model for EXME_PatientNotice
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PatientNotice extends PO implements I_EXME_PatientNotice, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PatientNotice (Properties ctx, int EXME_PatientNotice_ID, String trxName)
    {
      super (ctx, EXME_PatientNotice_ID, trxName);
      /** if (EXME_PatientNotice_ID == 0)
        {
			setEXME_Alerta_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_PatientNotice_ID (0);
			setValid_To (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_PatientNotice (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PatientNotice[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Alerta getEXME_Alerta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Alerta.Table_Name);
        I_EXME_Alerta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Alerta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Alerta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_Alerta_ID.
		@param EXME_Alerta_ID EXME_Alerta_ID	  */
	public void setEXME_Alerta_ID (int EXME_Alerta_ID)
	{
		if (EXME_Alerta_ID < 1)
			 throw new IllegalArgumentException ("EXME_Alerta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Alerta_ID, Integer.valueOf(EXME_Alerta_ID));
	}

	/** Get EXME_Alerta_ID.
		@return EXME_Alerta_ID	  */
	public int getEXME_Alerta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Alerta_ID);
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
			set_ValueNoCheck (COLUMNNAME_EXME_CitaMedica_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_CitaMedica_ID, Integer.valueOf(EXME_CitaMedica_ID));
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

	/** Set Patient Notice.
		@param EXME_PatientNotice_ID Patient Notice	  */
	public void setEXME_PatientNotice_ID (int EXME_PatientNotice_ID)
	{
		if (EXME_PatientNotice_ID < 1)
			 throw new IllegalArgumentException ("EXME_PatientNotice_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PatientNotice_ID, Integer.valueOf(EXME_PatientNotice_ID));
	}

	/** Get Patient Notice.
		@return Patient Notice	  */
	public int getEXME_PatientNotice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PatientNotice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valid to.
		@param Valid_To Valid to	  */
	public void setValid_To (Timestamp Valid_To)
	{
		if (Valid_To == null)
			throw new IllegalArgumentException ("Valid_To is mandatory.");
		set_Value (COLUMNNAME_Valid_To, Valid_To);
	}

	/** Get Valid to.
		@return Valid to	  */
	public Timestamp getValid_To () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Valid_To);
	}
}