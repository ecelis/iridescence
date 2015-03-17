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

/** Generated Model for EXME_EvalPiel
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EvalPiel extends PO implements I_EXME_EvalPiel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EvalPiel (Properties ctx, int EXME_EvalPiel_ID, String trxName)
    {
      super (ctx, EXME_EvalPiel_ID, trxName);
      /** if (EXME_EvalPiel_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_Enfermeria_ID (0);
			setEXME_EvalPiel_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_EvalPiel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EvalPiel[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Act_Motriz AD_Reference_ID=1200180 */
	public static final int ACT_MOTRIZ_AD_Reference_ID=1200180;
	/** On bed (1) = 1 */
	public static final String ACT_MOTRIZ_OnBed1 = "1";
	/** Wheelchair (2) = 2 */
	public static final String ACT_MOTRIZ_Wheelchair2 = "2";
	/** Walks with help (3) = 3 */
	public static final String ACT_MOTRIZ_WalksWithHelp3 = "3";
	/** Walk by itself (4) = 4 */
	public static final String ACT_MOTRIZ_WalkByItself4 = "4";
	/** Set Motor Activity.
		@param Act_Motriz Motor Activity	  */
	public void setAct_Motriz (String Act_Motriz)
	{

		if (Act_Motriz == null || Act_Motriz.equals("1") || Act_Motriz.equals("2") || Act_Motriz.equals("3") || Act_Motriz.equals("4")); else throw new IllegalArgumentException ("Act_Motriz Invalid value - " + Act_Motriz + " - Reference_ID=1200180 - 1 - 2 - 3 - 4");		set_Value (COLUMNNAME_Act_Motriz, Act_Motriz);
	}

	/** Get Motor Activity.
		@return Motor Activity	  */
	public String getAct_Motriz () 
	{
		return (String)get_Value(COLUMNNAME_Act_Motriz);
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

	public I_EXME_EdoConciencia getEXME_EdoConciencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EdoConciencia.Table_Name);
        I_EXME_EdoConciencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EdoConciencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EdoConciencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Conscience.
		@param EXME_EdoConciencia_ID Conscience	  */
	public void setEXME_EdoConciencia_ID (int EXME_EdoConciencia_ID)
	{
		if (EXME_EdoConciencia_ID < 1) 
			set_Value (COLUMNNAME_EXME_EdoConciencia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EdoConciencia_ID, Integer.valueOf(EXME_EdoConciencia_ID));
	}

	/** Get Conscience.
		@return Conscience	  */
	public int getEXME_EdoConciencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EdoConciencia_ID);
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

	/** Set Skin Evaluation.
		@param EXME_EvalPiel_ID Skin Evaluation	  */
	public void setEXME_EvalPiel_ID (int EXME_EvalPiel_ID)
	{
		if (EXME_EvalPiel_ID < 1)
			 throw new IllegalArgumentException ("EXME_EvalPiel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EvalPiel_ID, Integer.valueOf(EXME_EvalPiel_ID));
	}

	/** Get Skin Evaluation.
		@return Skin Evaluation	  */
	public int getEXME_EvalPiel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EvalPiel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** EdoNutricion AD_Reference_ID=1200179 */
	public static final int EDONUTRICION_AD_Reference_ID=1200179;
	/** Bad (1) = 1 */
	public static final String EDONUTRICION_Bad1 = "1";
	/** Regular (2) = 2 */
	public static final String EDONUTRICION_Regular2 = "2";
	/** Weak (3) = 3 */
	public static final String EDONUTRICION_Weak3 = "3";
	/** Good (4) = 4 */
	public static final String EDONUTRICION_Good4 = "4";
	/** Set Nutricional Level.
		@param EdoNutricion Nutricional Level	  */
	public void setEdoNutricion (String EdoNutricion)
	{

		if (EdoNutricion == null || EdoNutricion.equals("1") || EdoNutricion.equals("2") || EdoNutricion.equals("3") || EdoNutricion.equals("4")); else throw new IllegalArgumentException ("EdoNutricion Invalid value - " + EdoNutricion + " - Reference_ID=1200179 - 1 - 2 - 3 - 4");		set_Value (COLUMNNAME_EdoNutricion, EdoNutricion);
	}

	/** Get Nutricional Level.
		@return Nutricional Level	  */
	public String getEdoNutricion () 
	{
		return (String)get_Value(COLUMNNAME_EdoNutricion);
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

	/** Incontinencia AD_Reference_ID=1200182 */
	public static final int INCONTINENCIA_AD_Reference_ID=1200182;
	/** Double Incontinence (1) = 1 */
	public static final String INCONTINENCIA_DoubleIncontinence1 = "1";
	/** Urinary or Fecal Incontinence (2) = 2 */
	public static final String INCONTINENCIA_UrinaryOrFecalIncontinence2 = "2";
	/** Occasional (3) = 3 */
	public static final String INCONTINENCIA_Occasional3 = "3";
	/** Continence (4) = 4 */
	public static final String INCONTINENCIA_Continence4 = "4";
	/** Set Incontinence.
		@param Incontinencia Incontinence	  */
	public void setIncontinencia (String Incontinencia)
	{

		if (Incontinencia == null || Incontinencia.equals("1") || Incontinencia.equals("2") || Incontinencia.equals("3") || Incontinencia.equals("4")); else throw new IllegalArgumentException ("Incontinencia Invalid value - " + Incontinencia + " - Reference_ID=1200182 - 1 - 2 - 3 - 4");		set_Value (COLUMNNAME_Incontinencia, Incontinencia);
	}

	/** Get Incontinence.
		@return Incontinence	  */
	public String getIncontinencia () 
	{
		return (String)get_Value(COLUMNNAME_Incontinencia);
	}

	/** Movilidad AD_Reference_ID=1200181 */
	public static final int MOVILIDAD_AD_Reference_ID=1200181;
	/** Inmobility (1) = 1 */
	public static final String MOVILIDAD_Inmobility1 = "1";
	/** Very Limited (2) = 2 */
	public static final String MOVILIDAD_VeryLimited2 = "2";
	/** Slightly Limited (3) = 3 */
	public static final String MOVILIDAD_SlightlyLimited3 = "3";
	/** Total (4) = 4 */
	public static final String MOVILIDAD_Total4 = "4";
	/** Set Mobility.
		@param Movilidad Mobility	  */
	public void setMovilidad (String Movilidad)
	{

		if (Movilidad == null || Movilidad.equals("1") || Movilidad.equals("2") || Movilidad.equals("3") || Movilidad.equals("4")); else throw new IllegalArgumentException ("Movilidad Invalid value - " + Movilidad + " - Reference_ID=1200181 - 1 - 2 - 3 - 4");		set_Value (COLUMNNAME_Movilidad, Movilidad);
	}

	/** Get Mobility.
		@return Mobility	  */
	public String getMovilidad () 
	{
		return (String)get_Value(COLUMNNAME_Movilidad);
	}
}