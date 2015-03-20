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

/** Generated Model for EXME_ManejoDolor
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ManejoDolor extends PO implements I_EXME_ManejoDolor, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ManejoDolor (Properties ctx, int EXME_ManejoDolor_ID, String trxName)
    {
      super (ctx, EXME_ManejoDolor_ID, trxName);
      /** if (EXME_ManejoDolor_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_Enfermeria_ID (0);
			setEXME_ManejoDolor_ID (0);
			setEXME_ParteCorporal_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_ManejoDolor (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ManejoDolor[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Duracion AD_Reference_ID=1200190 */
	public static final int DURACION_AD_Reference_ID=1200190;
	/** Continuos (1) = 1 */
	public static final String DURACION_Continuos1 = "1";
	/** Intermittent (2) = 2 */
	public static final String DURACION_Intermittent2 = "2";
	/** Set Duration.
		@param Duracion 
		Duration
	  */
	public void setDuracion (String Duracion)
	{

		if (Duracion == null || Duracion.equals("1") || Duracion.equals("2")); else throw new IllegalArgumentException ("Duracion Invalid value - " + Duracion + " - Reference_ID=1200190 - 1 - 2");		set_Value (COLUMNNAME_Duracion, Duracion);
	}

	/** Get Duration.
		@return Duration
	  */
	public String getDuracion () 
	{
		return (String)get_Value(COLUMNNAME_Duracion);
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_DiarioEnf getEXME_DiarioEnf() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_DiarioEnf.Table_Name);
        I_EXME_DiarioEnf result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_DiarioEnf)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_DiarioEnf_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary Diary.
		@param EXME_DiarioEnf_ID Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID)
	{
		if (EXME_DiarioEnf_ID < 1) 
			set_Value (COLUMNNAME_EXME_DiarioEnf_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DiarioEnf_ID, Integer.valueOf(EXME_DiarioEnf_ID));
	}

	/** Get Infirmary Diary.
		@return Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiarioEnf_ID);
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
			 throw new IllegalArgumentException ("EXME_Enfermeria_ID is mandatory.");
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

	/** Set Paint Control.
		@param EXME_ManejoDolor_ID Paint Control	  */
	public void setEXME_ManejoDolor_ID (int EXME_ManejoDolor_ID)
	{
		if (EXME_ManejoDolor_ID < 1)
			 throw new IllegalArgumentException ("EXME_ManejoDolor_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ManejoDolor_ID, Integer.valueOf(EXME_ManejoDolor_ID));
	}

	/** Get Paint Control.
		@return Paint Control	  */
	public int getEXME_ManejoDolor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ManejoDolor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ParteCorporal getEXME_ParteCorporal() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ParteCorporal.Table_Name);
        I_EXME_ParteCorporal result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ParteCorporal)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ParteCorporal_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Body part.
		@param EXME_ParteCorporal_ID Body part	  */
	public void setEXME_ParteCorporal_ID (int EXME_ParteCorporal_ID)
	{
		if (EXME_ParteCorporal_ID < 1)
			 throw new IllegalArgumentException ("EXME_ParteCorporal_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ParteCorporal_ID, Integer.valueOf(EXME_ParteCorporal_ID));
	}

	/** Get Body part.
		@return Body part	  */
	public int getEXME_ParteCorporal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ParteCorporal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Intensity.
		@param Intensidad Intensity	  */
	public void setIntensidad (String Intensidad)
	{
		set_Value (COLUMNNAME_Intensidad, Intensidad);
	}

	/** Get Intensity.
		@return Intensity	  */
	public String getIntensidad () 
	{
		return (String)get_Value(COLUMNNAME_Intensidad);
	}

	/** Set Intensity Graph.
		@param IntensidadGrafica Intensity Graph	  */
	public void setIntensidadGrafica (String IntensidadGrafica)
	{
		set_Value (COLUMNNAME_IntensidadGrafica, IntensidadGrafica);
	}

	/** Get Intensity Graph.
		@return Intensity Graph	  */
	public String getIntensidadGrafica () 
	{
		return (String)get_Value(COLUMNNAME_IntensidadGrafica);
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

	/** Tipo_Dolor AD_Reference_ID=1200189 */
	public static final int TIPO_DOLOR_AD_Reference_ID=1200189;
	/** Stinging (1) = 1 */
	public static final String TIPO_DOLOR_Stinging1 = "1";
	/** Oppressive (2) = 2 */
	public static final String TIPO_DOLOR_Oppressive2 = "2";
	/** Burning (3) = 3 */
	public static final String TIPO_DOLOR_Burning3 = "3";
	/** Deaf (4) = 4 */
	public static final String TIPO_DOLOR_Deaf4 = "4";
	/** Set Pain Type.
		@param Tipo_Dolor Pain Type	  */
	public void setTipo_Dolor (String Tipo_Dolor)
	{

		if (Tipo_Dolor == null || Tipo_Dolor.equals("1") || Tipo_Dolor.equals("2") || Tipo_Dolor.equals("3") || Tipo_Dolor.equals("4")); else throw new IllegalArgumentException ("Tipo_Dolor Invalid value - " + Tipo_Dolor + " - Reference_ID=1200189 - 1 - 2 - 3 - 4");		set_Value (COLUMNNAME_Tipo_Dolor, Tipo_Dolor);
	}

	/** Get Pain Type.
		@return Pain Type	  */
	public String getTipo_Dolor () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Dolor);
	}
}