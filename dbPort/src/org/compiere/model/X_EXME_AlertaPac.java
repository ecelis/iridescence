/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_AlertaPac
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_AlertaPac extends PO implements I_EXME_AlertaPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AlertaPac (Properties ctx, int EXME_AlertaPac_ID, String trxName)
    {
      super (ctx, EXME_AlertaPac_ID, trxName);
      /** if (EXME_AlertaPac_ID == 0)
        {
			setEXME_Alerta_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_AlertaPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AlertaPac[")
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

	public I_EXME_Alerta getEXME_Alerta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Alerta.Table_Name);
        I_EXME_Alerta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Alerta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Alerta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_Alerta_ID.
		@param EXME_Alerta_ID EXME_Alerta_ID	  */
	public void setEXME_Alerta_ID (int EXME_Alerta_ID)
	{
		if (EXME_Alerta_ID < 1)
			 throw new IllegalArgumentException ("EXME_Alerta_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Alerta_ID, Integer.valueOf(EXME_Alerta_ID));
	}

	/** Get EXME_Alerta_ID.
		@return EXME_Alerta_ID	  */
	public int getEXME_Alerta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Alerta_ID);
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

	/** Set Frequency.
		@param Frequency 
		Frequency of events
	  */
	public void setFrequency (BigDecimal Frequency)
	{
		set_Value (COLUMNNAME_Frequency, Frequency);
	}

	/** Get Frequency.
		@return Frequency of events
	  */
	public BigDecimal getFrequency () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Frequency);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}