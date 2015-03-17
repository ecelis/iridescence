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

/** Generated Model for EXME_MO_DiagnosticosPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_DiagnosticosPac extends PO implements I_EXME_MO_DiagnosticosPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_DiagnosticosPac (Properties ctx, int EXME_MO_DiagnosticosPac_ID, String trxName)
    {
      super (ctx, EXME_MO_DiagnosticosPac_ID, trxName);
      /** if (EXME_MO_DiagnosticosPac_ID == 0)
        {
			setDescription (null);
			setEsOdontograma (false);
			setEXME_CitaMedica_ID (0);
			setEXME_Diagnostico_ID (0);
			setEXME_MO_DiagnosticosPac_ID (0);
			setEXME_MO_PiezaDental_ID (0);
			setEXME_Paciente_ID (0);
			setFecha_Diagnostico (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_DiagnosticosPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_DiagnosticosPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Is Odontogram.
		@param EsOdontograma 
		Is Odontogram
	  */
	public void setEsOdontograma (boolean EsOdontograma)
	{
		set_Value (COLUMNNAME_EsOdontograma, Boolean.valueOf(EsOdontograma));
	}

	/** Get Is Odontogram.
		@return Is Odontogram
	  */
	public boolean isEsOdontograma () 
	{
		Object oo = get_Value(COLUMNNAME_EsOdontograma);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
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

	/** Set Patient Diagnosis.
		@param EXME_MO_DiagnosticosPac_ID Patient Diagnosis	  */
	public void setEXME_MO_DiagnosticosPac_ID (int EXME_MO_DiagnosticosPac_ID)
	{
		if (EXME_MO_DiagnosticosPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_DiagnosticosPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_DiagnosticosPac_ID, Integer.valueOf(EXME_MO_DiagnosticosPac_ID));
	}

	/** Get Patient Diagnosis.
		@return Patient Diagnosis	  */
	public int getEXME_MO_DiagnosticosPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_DiagnosticosPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MO_PiezaDental getEXME_MO_PiezaDental() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MO_PiezaDental.Table_Name);
        I_EXME_MO_PiezaDental result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MO_PiezaDental)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MO_PiezaDental_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dental Piece.
		@param EXME_MO_PiezaDental_ID Dental Piece	  */
	public void setEXME_MO_PiezaDental_ID (int EXME_MO_PiezaDental_ID)
	{
		if (EXME_MO_PiezaDental_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_PiezaDental_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_MO_PiezaDental_ID, Integer.valueOf(EXME_MO_PiezaDental_ID));
	}

	/** Get Dental Piece.
		@return Dental Piece	  */
	public int getEXME_MO_PiezaDental_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_PiezaDental_ID);
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
}