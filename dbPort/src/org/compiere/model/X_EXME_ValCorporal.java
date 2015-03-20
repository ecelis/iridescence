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

/** Generated Model for EXME_ValCorporal
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ValCorporal extends PO implements I_EXME_ValCorporal, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ValCorporal (Properties ctx, int EXME_ValCorporal_ID, String trxName)
    {
      super (ctx, EXME_ValCorporal_ID, trxName);
      /** if (EXME_ValCorporal_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_Enfermeria_ID (0);
			setEXME_ValCorporal_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ValCorporal (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ValCorporal[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Deshidratacion AD_Reference_ID=1200183 */
	public static final int DESHIDRATACION_AD_Reference_ID=1200183;
	/** Severe (3) = 3 */
	public static final String DESHIDRATACION_Severe3 = "3";
	/** Weak (1) = 1 */
	public static final String DESHIDRATACION_Weak1 = "1";
	/** Moderate (2) = 2 */
	public static final String DESHIDRATACION_Moderate2 = "2";
	/** None (0) = 0 */
	public static final String DESHIDRATACION_None0 = "0";
	/** Set Dehydration.
		@param Deshidratacion Dehydration	  */
	public void setDeshidratacion (String Deshidratacion)
	{

		if (Deshidratacion == null || Deshidratacion.equals("3") || Deshidratacion.equals("1") || Deshidratacion.equals("2") || Deshidratacion.equals("0")); else throw new IllegalArgumentException ("Deshidratacion Invalid value - " + Deshidratacion + " - Reference_ID=1200183 - 3 - 1 - 2 - 0");		set_Value (COLUMNNAME_Deshidratacion, Deshidratacion);
	}

	/** Get Dehydration.
		@return Dehydration	  */
	public String getDeshidratacion () 
	{
		return (String)get_Value(COLUMNNAME_Deshidratacion);
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

	/** Set Body Valuation.
		@param EXME_ValCorporal_ID Body Valuation	  */
	public void setEXME_ValCorporal_ID (int EXME_ValCorporal_ID)
	{
		if (EXME_ValCorporal_ID < 1)
			 throw new IllegalArgumentException ("EXME_ValCorporal_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ValCorporal_ID, Integer.valueOf(EXME_ValCorporal_ID));
	}

	/** Get Body Valuation.
		@return Body Valuation	  */
	public int getEXME_ValCorporal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ValCorporal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Edema AD_Reference_ID=1200183 */
	public static final int EDEMA_AD_Reference_ID=1200183;
	/** Severe (3) = 3 */
	public static final String EDEMA_Severe3 = "3";
	/** Weak (1) = 1 */
	public static final String EDEMA_Weak1 = "1";
	/** Moderate (2) = 2 */
	public static final String EDEMA_Moderate2 = "2";
	/** None (0) = 0 */
	public static final String EDEMA_None0 = "0";
	/** Set Edema.
		@param Edema 
		Edema
	  */
	public void setEdema (String Edema)
	{

		if (Edema == null || Edema.equals("3") || Edema.equals("1") || Edema.equals("2") || Edema.equals("0")); else throw new IllegalArgumentException ("Edema Invalid value - " + Edema + " - Reference_ID=1200183 - 3 - 1 - 2 - 0");		set_Value (COLUMNNAME_Edema, Edema);
	}

	/** Get Edema.
		@return Edema
	  */
	public String getEdema () 
	{
		return (String)get_Value(COLUMNNAME_Edema);
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Humedad AD_Reference_ID=1200183 */
	public static final int HUMEDAD_AD_Reference_ID=1200183;
	/** Severe (3) = 3 */
	public static final String HUMEDAD_Severe3 = "3";
	/** Weak (1) = 1 */
	public static final String HUMEDAD_Weak1 = "1";
	/** Moderate (2) = 2 */
	public static final String HUMEDAD_Moderate2 = "2";
	/** None (0) = 0 */
	public static final String HUMEDAD_None0 = "0";
	/** Set Humidity.
		@param Humedad Humidity	  */
	public void setHumedad (String Humedad)
	{

		if (Humedad == null || Humedad.equals("3") || Humedad.equals("1") || Humedad.equals("2") || Humedad.equals("0")); else throw new IllegalArgumentException ("Humedad Invalid value - " + Humedad + " - Reference_ID=1200183 - 3 - 1 - 2 - 0");		set_Value (COLUMNNAME_Humedad, Humedad);
	}

	/** Get Humidity.
		@return Humidity	  */
	public String getHumedad () 
	{
		return (String)get_Value(COLUMNNAME_Humedad);
	}

	/** Llen_Capilar AD_Reference_ID=1200184 */
	public static final int LLEN_CAPILAR_AD_Reference_ID=1200184;
	/** More than 5 seconds (2) = 2 */
	public static final String LLEN_CAPILAR_MoreThan5Seconds2 = "2";
	/** Under 5 seconds(1) = 1 */
	public static final String LLEN_CAPILAR_Under5Seconds1 = "1";
	/** Set Capillary refill time.
		@param Llen_Capilar Capillary refill time	  */
	public void setLlen_Capilar (String Llen_Capilar)
	{

		if (Llen_Capilar == null || Llen_Capilar.equals("2") || Llen_Capilar.equals("1")); else throw new IllegalArgumentException ("Llen_Capilar Invalid value - " + Llen_Capilar + " - Reference_ID=1200184 - 2 - 1");		set_Value (COLUMNNAME_Llen_Capilar, Llen_Capilar);
	}

	/** Get Capillary refill time.
		@return Capillary refill time	  */
	public String getLlen_Capilar () 
	{
		return (String)get_Value(COLUMNNAME_Llen_Capilar);
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
}