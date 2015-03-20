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

/** Generated Model for EXME_EnfermeriaRol
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EnfermeriaRol extends PO implements I_EXME_EnfermeriaRol, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EnfermeriaRol (Properties ctx, int EXME_EnfermeriaRol_ID, String trxName)
    {
      super (ctx, EXME_EnfermeriaRol_ID, trxName);
      /** if (EXME_EnfermeriaRol_ID == 0)
        {
			setEXME_Enfermeria_ID (0);
			setEXME_EnfermeriaRol_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setRol (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_EnfermeriaRol (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EnfermeriaRol[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Nursing roles.
		@param EXME_EnfermeriaRol_ID Nursing roles	  */
	public void setEXME_EnfermeriaRol_ID (int EXME_EnfermeriaRol_ID)
	{
		if (EXME_EnfermeriaRol_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfermeriaRol_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnfermeriaRol_ID, Integer.valueOf(EXME_EnfermeriaRol_ID));
	}

	/** Get Nursing roles.
		@return Nursing roles	  */
	public int getEXME_EnfermeriaRol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfermeriaRol_ID);
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

	/** Rol AD_Reference_ID=1200225 */
	public static final int ROL_AD_Reference_ID=1200225;
	/** Guard = G  */
	public static final String ROL_Guard = "G ";
	/** Vacation = V  */
	public static final String ROL_Vacation = "V ";
	/** Emergency group = GE */
	public static final String ROL_EmergencyGroup = "GE";
	/** Labor Sunday = L  */
	public static final String ROL_LaborSunday = "L ";
	/** Rest Day = D  */
	public static final String ROL_RestDay = "D ";
	/** Set Role.
		@param Rol Role	  */
	public void setRol (String Rol)
	{
		if (Rol == null) throw new IllegalArgumentException ("Rol is mandatory");
		if (Rol.equals("G ") || Rol.equals("V ") || Rol.equals("GE") || Rol.equals("L ") || Rol.equals("D ")); else throw new IllegalArgumentException ("Rol Invalid value - " + Rol + " - Reference_ID=1200225 - G  - V  - GE - L  - D ");		set_Value (COLUMNNAME_Rol, Rol);
	}

	/** Get Role.
		@return Role	  */
	public String getRol () 
	{
		return (String)get_Value(COLUMNNAME_Rol);
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