/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_MO_PlaticasPaciente
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_PlaticasPaciente extends PO implements I_EXME_MO_PlaticasPaciente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_PlaticasPaciente (Properties ctx, int EXME_MO_PlaticasPaciente_ID, String trxName)
    {
      super (ctx, EXME_MO_PlaticasPaciente_ID, trxName);
      /** if (EXME_MO_PlaticasPaciente_ID == 0)
        {
			setEXME_CitaMedica_ID (0);
			setEXME_MO_Platicas_ID (0);
			setEXME_MO_PlaticasPaciente_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_PlaticasPaciente (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_PlaticasPaciente[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_EXME_MO_Platicas getEXME_MO_Platicas() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MO_Platicas.Table_Name);
        I_EXME_MO_Platicas result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MO_Platicas)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MO_Platicas_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Talk.
		@param EXME_MO_Platicas_ID 
		Talk  by Speciality
	  */
	public void setEXME_MO_Platicas_ID (int EXME_MO_Platicas_ID)
	{
		if (EXME_MO_Platicas_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_Platicas_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_MO_Platicas_ID, Integer.valueOf(EXME_MO_Platicas_ID));
	}

	/** Get Talk.
		@return Talk  by Speciality
	  */
	public int getEXME_MO_Platicas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_Platicas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Talk.
		@param EXME_MO_PlaticasPaciente_ID 
		Patient Talk
	  */
	public void setEXME_MO_PlaticasPaciente_ID (int EXME_MO_PlaticasPaciente_ID)
	{
		if (EXME_MO_PlaticasPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_PlaticasPaciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_PlaticasPaciente_ID, Integer.valueOf(EXME_MO_PlaticasPaciente_ID));
	}

	/** Get Patient Talk.
		@return Patient Talk
	  */
	public int getEXME_MO_PlaticasPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_PlaticasPaciente_ID);
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
}