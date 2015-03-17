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

/** Generated Model for EXME_TrasladoPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_TrasladoPac extends PO implements I_EXME_TrasladoPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TrasladoPac (Properties ctx, int EXME_TrasladoPac_ID, String trxName)
    {
      super (ctx, EXME_TrasladoPac_ID, trxName);
      /** if (EXME_TrasladoPac_ID == 0)
        {
			setEXME_Institucion_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_TrasladoPac_ID (0);
			setFechaTraslado (new Timestamp( System.currentTimeMillis() ));
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_TrasladoPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TrasladoPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Institucion.Table_Name);
        I_EXME_Institucion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Institucion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Institucion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Institution.
		@param EXME_Institucion_ID 
		Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID)
	{
		if (EXME_Institucion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Institucion_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Institucion_ID, Integer.valueOf(EXME_Institucion_ID));
	}

	/** Get Institution.
		@return Institution
	  */
	public int getEXME_Institucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Institucion_ID);
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

	/** Set Patient Transfer.
		@param EXME_TrasladoPac_ID 
		Patient Transfer
	  */
	public void setEXME_TrasladoPac_ID (int EXME_TrasladoPac_ID)
	{
		if (EXME_TrasladoPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_TrasladoPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TrasladoPac_ID, Integer.valueOf(EXME_TrasladoPac_ID));
	}

	/** Get Patient Transfer.
		@return Patient Transfer
	  */
	public int getEXME_TrasladoPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TrasladoPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Transfer Date.
		@param FechaTraslado 
		Transfer Date
	  */
	public void setFechaTraslado (Timestamp FechaTraslado)
	{
		if (FechaTraslado == null)
			throw new IllegalArgumentException ("FechaTraslado is mandatory.");
		set_Value (COLUMNNAME_FechaTraslado, FechaTraslado);
	}

	/** Get Transfer Date.
		@return Transfer Date
	  */
	public Timestamp getFechaTraslado () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaTraslado);
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