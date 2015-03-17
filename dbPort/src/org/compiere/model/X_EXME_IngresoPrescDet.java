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

/** Generated Model for EXME_IngresoPrescDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_IngresoPrescDet extends PO implements I_EXME_IngresoPrescDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_IngresoPrescDet (Properties ctx, int EXME_IngresoPrescDet_ID, String trxName)
    {
      super (ctx, EXME_IngresoPrescDet_ID, trxName);
      /** if (EXME_IngresoPrescDet_ID == 0)
        {
			setEXME_IngresoPrescDet_ID (0);
			setEXME_IngresoPresc_ID (0);
			setFechaProg (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_IngresoPrescDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_IngresoPrescDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Aplicacion AD_Reference_ID=1200211 */
	public static final int APLICACION_AD_Reference_ID=1200211;
	/** Peripheral = P */
	public static final String APLICACION_Peripheral = "P";
	/** Central = C */
	public static final String APLICACION_Central = "C";
	/** - = - */
	public static final String APLICACION__ = "-";
	/** Set Application.
		@param Aplicacion Application	  */
	public void setAplicacion (String Aplicacion)
	{

		if (Aplicacion == null || Aplicacion.equals("P") || Aplicacion.equals("C") || Aplicacion.equals("-")); else throw new IllegalArgumentException ("Aplicacion Invalid value - " + Aplicacion + " - Reference_ID=1200211 - P - C - -");		set_Value (COLUMNNAME_Aplicacion, Aplicacion);
	}

	/** Get Application.
		@return Application	  */
	public String getAplicacion () 
	{
		return (String)get_Value(COLUMNNAME_Aplicacion);
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

	/** Set Entry detail.
		@param EXME_IngresoPrescDet_ID Entry detail	  */
	public void setEXME_IngresoPrescDet_ID (int EXME_IngresoPrescDet_ID)
	{
		if (EXME_IngresoPrescDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_IngresoPrescDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_IngresoPrescDet_ID, Integer.valueOf(EXME_IngresoPrescDet_ID));
	}

	/** Get Entry detail.
		@return Entry detail	  */
	public int getEXME_IngresoPrescDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_IngresoPrescDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_IngresoPresc getEXME_IngresoPresc() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_IngresoPresc.Table_Name);
        I_EXME_IngresoPresc result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_IngresoPresc)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_IngresoPresc_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Entry.
		@param EXME_IngresoPresc_ID Entry	  */
	public void setEXME_IngresoPresc_ID (int EXME_IngresoPresc_ID)
	{
		if (EXME_IngresoPresc_ID < 1)
			 throw new IllegalArgumentException ("EXME_IngresoPresc_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_IngresoPresc_ID, Integer.valueOf(EXME_IngresoPresc_ID));
	}

	/** Get Entry.
		@return Entry	  */
	public int getEXME_IngresoPresc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_IngresoPresc_ID);
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

	/** Set Scheduled Date.
		@param FechaProg Scheduled Date	  */
	public void setFechaProg (Timestamp FechaProg)
	{
		if (FechaProg == null)
			throw new IllegalArgumentException ("FechaProg is mandatory.");
		set_Value (COLUMNNAME_FechaProg, FechaProg);
	}

	/** Get Scheduled Date.
		@return Scheduled Date	  */
	public Timestamp getFechaProg () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaProg);
	}
}