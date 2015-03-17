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

/** Generated Model for EXME_Incapacidad_Pac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Incapacidad_Pac extends PO implements I_EXME_Incapacidad_Pac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Incapacidad_Pac (Properties ctx, int EXME_Incapacidad_Pac_ID, String trxName)
    {
      super (ctx, EXME_Incapacidad_Pac_ID, trxName);
      /** if (EXME_Incapacidad_Pac_ID == 0)
        {
			setEXME_Incapacidad_ID (0);
			setEXME_Incapacidad_Pac_ID (0);
			setEXME_Medico_ID (0);
			setEXME_Paciente_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Incapacidad_Pac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Incapacidad_Pac[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
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
			set_Value (COLUMNNAME_EXME_Especialidad_ID, null);
		else 
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

	/** Set Specialty.
		@param EXME_Especialidad2_ID Specialty	  */
	public void setEXME_Especialidad2_ID (int EXME_Especialidad2_ID)
	{
		if (EXME_Especialidad2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad2_ID, Integer.valueOf(EXME_Especialidad2_ID));
	}

	/** Get Specialty.
		@return Specialty	  */
	public int getEXME_Especialidad2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Incapacidad getEXME_Incapacidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Incapacidad.Table_Name);
        I_EXME_Incapacidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Incapacidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Incapacidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Disability.
		@param EXME_Incapacidad_ID Disability	  */
	public void setEXME_Incapacidad_ID (int EXME_Incapacidad_ID)
	{
		if (EXME_Incapacidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Incapacidad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Incapacidad_ID, Integer.valueOf(EXME_Incapacidad_ID));
	}

	/** Get Disability.
		@return Disability	  */
	public int getEXME_Incapacidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Incapacidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Disability.
		@param EXME_Incapacidad_Pac_ID Patient Disability	  */
	public void setEXME_Incapacidad_Pac_ID (int EXME_Incapacidad_Pac_ID)
	{
		if (EXME_Incapacidad_Pac_ID < 1)
			 throw new IllegalArgumentException ("EXME_Incapacidad_Pac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Incapacidad_Pac_ID, Integer.valueOf(EXME_Incapacidad_Pac_ID));
	}

	/** Get Patient Disability.
		@return Patient Disability	  */
	public int getEXME_Incapacidad_Pac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Incapacidad_Pac_ID);
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
		@param Fecha_Fin 
		Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin)
	{
		set_Value (COLUMNNAME_Fecha_Fin, Fecha_Fin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFecha_Fin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin);
	}

	/** Set Initial Date.
		@param Fecha_Ini 
		Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini)
	{
		set_Value (COLUMNNAME_Fecha_Ini, Fecha_Ini);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFecha_Ini () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini);
	}

	/** Set Date of Record.
		@param Fecha_Registro 
		Date of Record
	  */
	public void setFecha_Registro (Timestamp Fecha_Registro)
	{
		set_Value (COLUMNNAME_Fecha_Registro, Fecha_Registro);
	}

	/** Get Date of Record.
		@return Date of Record
	  */
	public Timestamp getFecha_Registro () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Registro);
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