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
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Hist_Vacuna_Temp
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Hist_Vacuna_Temp extends PO implements I_EXME_Hist_Vacuna_Temp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Hist_Vacuna_Temp (Properties ctx, int EXME_Hist_Vacuna_Temp_ID, String trxName)
    {
      super (ctx, EXME_Hist_Vacuna_Temp_ID, trxName);
      /** if (EXME_Hist_Vacuna_Temp_ID == 0)
        {
			setEXME_Hist_Vacuna_Temp_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Hist_Vacuna_Temp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Hist_Vacuna_Temp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (BigDecimal Cantidad)
	{
		set_Value (COLUMNNAME_Cantidad, Cantidad);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getCantidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cantidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_Campaign getC_Campaign() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Campaign.Table_Name);
        I_C_Campaign result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Campaign)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Campaign_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Program.
		@param C_Campaign_ID 
		Program
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Program.
		@return Program
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Registered Address.
		@param C_LocationReg_ID Registered Address	  */
	public void setC_LocationReg_ID (int C_LocationReg_ID)
	{
		if (C_LocationReg_ID < 1) 
			set_Value (COLUMNNAME_C_LocationReg_ID, null);
		else 
			set_Value (COLUMNNAME_C_LocationReg_ID, Integer.valueOf(C_LocationReg_ID));
	}

	/** Get Registered Address.
		@return Registered Address	  */
	public int getC_LocationReg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocationReg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM of Height.
		@param C_UOMHeight_ID 
		Unit of Measure of Height
	  */
	public void setC_UOMHeight_ID (int C_UOMHeight_ID)
	{
		if (C_UOMHeight_ID < 1) 
			set_Value (COLUMNNAME_C_UOMHeight_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOMHeight_ID, Integer.valueOf(C_UOMHeight_ID));
	}

	/** Get UOM of Height.
		@return Unit of Measure of Height
	  */
	public int getC_UOMHeight_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOMHeight_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set UOM Interval.
		@param C_UOM_Inter_ID 
		Unit of measurement interval
	  */
	public void setC_UOM_Inter_ID (int C_UOM_Inter_ID)
	{
		if (C_UOM_Inter_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Inter_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Inter_ID, Integer.valueOf(C_UOM_Inter_ID));
	}

	/** Get UOM Interval.
		@return Unit of measurement interval
	  */
	public int getC_UOM_Inter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Inter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM of Corporal Mass.
		@param C_UOMMasaCorp_ID 
		Unit of Measure of Corporal Mass
	  */
	public void setC_UOMMasaCorp_ID (int C_UOMMasaCorp_ID)
	{
		if (C_UOMMasaCorp_ID < 1) 
			set_Value (COLUMNNAME_C_UOMMasaCorp_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOMMasaCorp_ID, Integer.valueOf(C_UOMMasaCorp_ID));
	}

	/** Get UOM of Corporal Mass.
		@return Unit of Measure of Corporal Mass
	  */
	public int getC_UOMMasaCorp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOMMasaCorp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM of Weight.
		@param C_UOMWeight_ID 
		UOM of Weight
	  */
	public void setC_UOMWeight_ID (int C_UOMWeight_ID)
	{
		if (C_UOMWeight_ID < 1) 
			set_Value (COLUMNNAME_C_UOMWeight_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOMWeight_ID, Integer.valueOf(C_UOMWeight_ID));
	}

	/** Get UOM of Weight.
		@return UOM of Weight
	  */
	public int getC_UOMWeight_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOMWeight_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Age.
		@param Edad 
		Age
	  */
	public void setEdad (BigDecimal Edad)
	{
		set_Value (COLUMNNAME_Edad, Edad);
	}

	/** Get Age.
		@return Age
	  */
	public BigDecimal getEdad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Edad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Is Local.
		@param EsLocal 
		Indicates if the vaccine is applied locally or not
	  */
	public void setEsLocal (boolean EsLocal)
	{
		set_Value (COLUMNNAME_EsLocal, Boolean.valueOf(EsLocal));
	}

	/** Get Is Local.
		@return Indicates if the vaccine is applied locally or not
	  */
	public boolean isEsLocal () 
	{
		Object oo = get_Value(COLUMNNAME_EsLocal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_EXME_ActPaciente getEXME_ActPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPaciente.Table_Name);
        I_EXME_ActPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Activity.
		@param EXME_ActPaciente_ID 
		Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID)
	{
		if (EXME_ActPaciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPaciente_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_CitaMedica_ID, null);
		else 
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

	/** Set Temporary Immunization History.
		@param EXME_Hist_Vacuna_Temp_ID 
		Temporary Immunization History
	  */
	public void setEXME_Hist_Vacuna_Temp_ID (int EXME_Hist_Vacuna_Temp_ID)
	{
		if (EXME_Hist_Vacuna_Temp_ID < 1)
			 throw new IllegalArgumentException ("EXME_Hist_Vacuna_Temp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Hist_Vacuna_Temp_ID, Integer.valueOf(EXME_Hist_Vacuna_Temp_ID));
	}

	/** Get Temporary Immunization History.
		@return Temporary Immunization History
	  */
	public int getEXME_Hist_Vacuna_Temp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hist_Vacuna_Temp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Labeler getEXME_Labeler() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Labeler.Table_Name);
        I_EXME_Labeler result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Labeler)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Labeler_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Labeler.
		@param EXME_Labeler_ID 
		Labeler
	  */
	public void setEXME_Labeler_ID (int EXME_Labeler_ID)
	{
		if (EXME_Labeler_ID < 1) 
			set_Value (COLUMNNAME_EXME_Labeler_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Labeler_ID, Integer.valueOf(EXME_Labeler_ID));
	}

	/** Get Labeler.
		@return Labeler
	  */
	public int getEXME_Labeler_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Labeler_ID);
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
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
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

	public I_EXME_VacunaDet getEXME_VacunaDet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_VacunaDet.Table_Name);
        I_EXME_VacunaDet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_VacunaDet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_VacunaDet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Detail immunization.
		@param EXME_VacunaDet_ID 
		Detail immunization
	  */
	public void setEXME_VacunaDet_ID (int EXME_VacunaDet_ID)
	{
		if (EXME_VacunaDet_ID < 1) 
			set_Value (COLUMNNAME_EXME_VacunaDet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_VacunaDet_ID, Integer.valueOf(EXME_VacunaDet_ID));
	}

	/** Get Detail immunization.
		@return Detail immunization
	  */
	public int getEXME_VacunaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Vacuna getEXME_Vacuna() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Vacuna.Table_Name);
        I_EXME_Vacuna result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Vacuna)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Vacuna_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vaccine.
		@param EXME_Vacuna_ID 
		Vaccine
	  */
	public void setEXME_Vacuna_ID (int EXME_Vacuna_ID)
	{
		if (EXME_Vacuna_ID < 1) 
			set_Value (COLUMNNAME_EXME_Vacuna_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Vacuna_ID, Integer.valueOf(EXME_Vacuna_ID));
	}

	/** Get Vaccine.
		@return Vaccine
	  */
	public int getEXME_Vacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Vacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Date of Application.
		@param FechaAplica 
		Date of Application
	  */
	public void setFechaAplica (Timestamp FechaAplica)
	{
		set_Value (COLUMNNAME_FechaAplica, FechaAplica);
	}

	/** Get Date of Application.
		@return Date of Application
	  */
	public Timestamp getFechaAplica () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAplica);
	}

	/** Set Registration Date.
		@param FechaRegistro 
		Registration Date
	  */
	public void setFechaRegistro (Timestamp FechaRegistro)
	{
		set_Value (COLUMNNAME_FechaRegistro, FechaRegistro);
	}

	/** Get Registration Date.
		@return Registration Date
	  */
	public Timestamp getFechaRegistro () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaRegistro);
	}

	/** Set Interval.
		@param Intervalo 
		Interval
	  */
	public void setIntervalo (BigDecimal Intervalo)
	{
		set_Value (COLUMNNAME_Intervalo, Intervalo);
	}

	/** Get Interval.
		@return Interval
	  */
	public BigDecimal getIntervalo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Intervalo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Location.
		@param Location Location	  */
	public void setLocation (String Location)
	{
		set_Value (COLUMNNAME_Location, Location);
	}

	/** Get Location.
		@return Location	  */
	public String getLocation () 
	{
		return (String)get_Value(COLUMNNAME_Location);
	}

	/** Set Lot No.
		@param Lot 
		Lot number (alphanumeric)
	  */
	public void setLot (String Lot)
	{
		set_Value (COLUMNNAME_Lot, Lot);
	}

	/** Get Lot No.
		@return Lot number (alphanumeric)
	  */
	public String getLot () 
	{
		return (String)get_Value(COLUMNNAME_Lot);
	}

	/** Set Manufacturer.
		@param Manufacturer 
		Manufacturer of the Product
	  */
	public void setManufacturer (String Manufacturer)
	{
		set_Value (COLUMNNAME_Manufacturer, Manufacturer);
	}

	/** Get Manufacturer.
		@return Manufacturer of the Product
	  */
	public String getManufacturer () 
	{
		return (String)get_Value(COLUMNNAME_Manufacturer);
	}

	/** Set Corporal Mass.
		@param MasaCorporal 
		Corporal Mass of the patient
	  */
	public void setMasaCorporal (BigDecimal MasaCorporal)
	{
		set_Value (COLUMNNAME_MasaCorporal, MasaCorporal);
	}

	/** Get Corporal Mass.
		@return Corporal Mass of the patient
	  */
	public BigDecimal getMasaCorporal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MasaCorporal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 1) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rejection Reason.
		@param MotivoRechazo 
		Reason for Immunization Rejection
	  */
	public void setMotivoRechazo (String MotivoRechazo)
	{
		set_Value (COLUMNNAME_MotivoRechazo, MotivoRechazo);
	}

	/** Get Rejection Reason.
		@return Reason for Immunization Rejection
	  */
	public String getMotivoRechazo () 
	{
		return (String)get_Value(COLUMNNAME_MotivoRechazo);
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Notes.
		@param Notes Notes	  */
	public void setNotes (String Notes)
	{
		set_Value (COLUMNNAME_Notes, Notes);
	}

	/** Get Notes.
		@return Notes	  */
	public String getNotes () 
	{
		return (String)get_Value(COLUMNNAME_Notes);
	}

	/** Set Weight.
		@param Peso 
		Weight
	  */
	public void setPeso (BigDecimal Peso)
	{
		set_Value (COLUMNNAME_Peso, Peso);
	}

	/** Get Weight.
		@return Weight
	  */
	public BigDecimal getPeso () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Peso);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Rejected.
		@param Rejected 
		Indicates if the vaccine is Rejected or not
	  */
	public void setRejected (boolean Rejected)
	{
		set_Value (COLUMNNAME_Rejected, Boolean.valueOf(Rejected));
	}

	/** Get Rejected.
		@return Indicates if the vaccine is Rejected or not
	  */
	public boolean isRejected () 
	{
		Object oo = get_Value(COLUMNNAME_Rejected);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia)
	{
		set_Value (COLUMNNAME_Secuencia, Secuencia);
	}

	/** Get Sequence.
		@return Sequence
	  */
	public BigDecimal getSecuencia () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Secuencia);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence Number.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Serial No.
		@param SerNo 
		Product Serial Number 
	  */
	public void setSerNo (String SerNo)
	{
		set_Value (COLUMNNAME_SerNo, SerNo);
	}

	/** Get Serial No.
		@return Product Serial Number 
	  */
	public String getSerNo () 
	{
		return (String)get_Value(COLUMNNAME_SerNo);
	}

	/** Set Site.
		@param Site 
		Record the site where the vaccine was administered
	  */
	public void setSite (String Site)
	{
		set_Value (COLUMNNAME_Site, Site);
	}

	/** Get Site.
		@return Record the site where the vaccine was administered
	  */
	public String getSite () 
	{
		return (String)get_Value(COLUMNNAME_Site);
	}

	/** Set Source.
		@param Source 
		Record the source of the vaccine given
	  */
	public void setSource (String Source)
	{
		set_Value (COLUMNNAME_Source, Source);
	}

	/** Get Source.
		@return Record the source of the vaccine given
	  */
	public String getSource () 
	{
		return (String)get_Value(COLUMNNAME_Source);
	}

	/** Set Height.
		@param Talla 
		Height
	  */
	public void setTalla (BigDecimal Talla)
	{
		set_Value (COLUMNNAME_Talla, Talla);
	}

	/** Get Height.
		@return Height
	  */
	public BigDecimal getTalla () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Talla);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
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

	/** Set Route of Administration.
		@param Via 
		Route of Administration
	  */
	public void setVia (String Via)
	{
		set_Value (COLUMNNAME_Via, Via);
	}

	/** Get Route of Administration.
		@return Route of Administration
	  */
	public String getVia () 
	{
		return (String)get_Value(COLUMNNAME_Via);
	}

	/** Set Date on VIS.
		@param VIS_Date 
		Vaccine Information Statement date
	  */
	public void setVIS_Date (Timestamp VIS_Date)
	{
		set_Value (COLUMNNAME_VIS_Date, VIS_Date);
	}

	/** Get Date on VIS.
		@return Vaccine Information Statement date
	  */
	public Timestamp getVIS_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_VIS_Date);
	}

	/** Set VIS Date Given.
		@param VIS_DateGiven 
		Date when the Vaccine Information Statement was given
	  */
	public void setVIS_DateGiven (Timestamp VIS_DateGiven)
	{
		set_Value (COLUMNNAME_VIS_DateGiven, VIS_DateGiven);
	}

	/** Get VIS Date Given.
		@return Date when the Vaccine Information Statement was given
	  */
	public Timestamp getVIS_DateGiven () 
	{
		return (Timestamp)get_Value(COLUMNNAME_VIS_DateGiven);
	}
}