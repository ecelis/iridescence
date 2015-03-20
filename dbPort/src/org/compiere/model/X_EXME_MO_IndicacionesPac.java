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

/** Generated Model for EXME_MO_IndicacionesPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_IndicacionesPac extends PO implements I_EXME_MO_IndicacionesPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_IndicacionesPac (Properties ctx, int EXME_MO_IndicacionesPac_ID, String trxName)
    {
      super (ctx, EXME_MO_IndicacionesPac_ID, trxName);
      /** if (EXME_MO_IndicacionesPac_ID == 0)
        {
			setEsOdontograma (false);
			setEXME_MO_IndicacionesPac_ID (0);
			setEXME_Paciente_ID (0);
			setFecha_Indicacion (new Timestamp( System.currentTimeMillis() ));
			setTipoIndicacion (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_IndicacionesPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_IndicacionesPac[")
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

	/** Set Patient Indication.
		@param EXME_MO_IndicacionesPac_ID 
		Patient Indication
	  */
	public void setEXME_MO_IndicacionesPac_ID (int EXME_MO_IndicacionesPac_ID)
	{
		if (EXME_MO_IndicacionesPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_IndicacionesPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_IndicacionesPac_ID, Integer.valueOf(EXME_MO_IndicacionesPac_ID));
	}

	/** Get Patient Indication.
		@return Patient Indication
	  */
	public int getEXME_MO_IndicacionesPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_IndicacionesPac_ID);
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
			set_Value (COLUMNNAME_EXME_MO_PiezaDental_ID, null);
		else 
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

	/** Set Indication Date.
		@param Fecha_Indicacion 
		Indication Date
	  */
	public void setFecha_Indicacion (Timestamp Fecha_Indicacion)
	{
		if (Fecha_Indicacion == null)
			throw new IllegalArgumentException ("Fecha_Indicacion is mandatory.");
		set_Value (COLUMNNAME_Fecha_Indicacion, Fecha_Indicacion);
	}

	/** Get Indication Date.
		@return Indication Date
	  */
	public Timestamp getFecha_Indicacion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Indicacion);
	}

	/** TipoIndicacion AD_Reference_ID=1200135 */
	public static final int TIPOINDICACION_AD_Reference_ID=1200135;
	/** Preoperative indications = PRE */
	public static final String TIPOINDICACION_PreoperativeIndications = "PRE";
	/** Postoperative indications = POST */
	public static final String TIPOINDICACION_PostoperativeIndications = "POST";
	/** Evolution Notes = EVO */
	public static final String TIPOINDICACION_EvolutionNotes = "EVO";
	/** Extraoral Tests = EXA */
	public static final String TIPOINDICACION_ExtraoralTests = "EXA";
	/** buccal hygiene = HIG */
	public static final String TIPOINDICACION_BuccalHygiene = "HIG";
	/** Set Indication Type.
		@param TipoIndicacion 
		Indication Type
	  */
	public void setTipoIndicacion (String TipoIndicacion)
	{
		if (TipoIndicacion == null) throw new IllegalArgumentException ("TipoIndicacion is mandatory");
		if (TipoIndicacion.equals("PRE") || TipoIndicacion.equals("POST") || TipoIndicacion.equals("EVO") || TipoIndicacion.equals("EXA") || TipoIndicacion.equals("HIG")); else throw new IllegalArgumentException ("TipoIndicacion Invalid value - " + TipoIndicacion + " - Reference_ID=1200135 - PRE - POST - EVO - EXA - HIG");		set_Value (COLUMNNAME_TipoIndicacion, TipoIndicacion);
	}

	/** Get Indication Type.
		@return Indication Type
	  */
	public String getTipoIndicacion () 
	{
		return (String)get_Value(COLUMNNAME_TipoIndicacion);
	}
}