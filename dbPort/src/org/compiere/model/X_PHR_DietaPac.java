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

/** Generated Model for PHR_DietaPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_DietaPac extends PO implements I_PHR_DietaPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_DietaPac (Properties ctx, int PHR_DietaPac_ID, String trxName)
    {
      super (ctx, PHR_DietaPac_ID, trxName);
      /** if (PHR_DietaPac_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setFechaIni (new Timestamp( System.currentTimeMillis() ));
			setNombre (null);
			setPHR_DietaPac_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_DietaPac (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_PHR_DietaPac[")
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
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
	}

	/** Set Initial Date.
		@param FechaIni 
		Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni)
	{
		if (FechaIni == null)
			throw new IllegalArgumentException ("FechaIni is mandatory.");
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
	}

	/** Set Name.
		@param Nombre 
		Name of friend
	  */
	public void setNombre (String Nombre)
	{
		if (Nombre == null)
			throw new IllegalArgumentException ("Nombre is mandatory.");
		set_Value (COLUMNNAME_Nombre, Nombre);
	}

	/** Get Name.
		@return Name of friend
	  */
	public String getNombre () 
	{
		return (String)get_Value(COLUMNNAME_Nombre);
	}

	/** Set Patient Diet.
		@param PHR_DietaPac_ID Patient Diet	  */
	public void setPHR_DietaPac_ID (int PHR_DietaPac_ID)
	{
		if (PHR_DietaPac_ID < 1)
			 throw new IllegalArgumentException ("PHR_DietaPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_DietaPac_ID, Integer.valueOf(PHR_DietaPac_ID));
	}

	/** Get Patient Diet.
		@return Patient Diet	  */
	public int getPHR_DietaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_DietaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}