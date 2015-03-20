/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_PacSignoVitalDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PacSignoVitalDet extends PO implements I_EXME_PacSignoVitalDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PacSignoVitalDet (Properties ctx, int EXME_PacSignoVitalDet_ID, String trxName)
    {
      super (ctx, EXME_PacSignoVitalDet_ID, trxName);
      /** if (EXME_PacSignoVitalDet_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setEXME_PacSignoVitalDet_ID (0);
			setEXME_SignoVital_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setFolio (0);
			setValor (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_PacSignoVitalDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PacSignoVitalDet[")
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

	/** Set Patient Vital Sings detail.
		@param EXME_PacSignoVitalDet_ID 
		Patient Vital Sings detail
	  */
	public void setEXME_PacSignoVitalDet_ID (int EXME_PacSignoVitalDet_ID)
	{
		if (EXME_PacSignoVitalDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacSignoVitalDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PacSignoVitalDet_ID, Integer.valueOf(EXME_PacSignoVitalDet_ID));
	}

	/** Get Patient Vital Sings detail.
		@return Patient Vital Sings detail
	  */
	public int getEXME_PacSignoVitalDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacSignoVitalDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_SignoVital getEXME_SignoVital() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SignoVital.Table_Name);
        I_EXME_SignoVital result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SignoVital)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SignoVital_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vital Sign.
		@param EXME_SignoVital_ID Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID)
	{
		if (EXME_SignoVital_ID < 1)
			 throw new IllegalArgumentException ("EXME_SignoVital_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_SignoVital_ID, Integer.valueOf(EXME_SignoVital_ID));
	}

	/** Get Vital Sign.
		@return Vital Sign	  */
	public int getEXME_SignoVital_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SignoVital_ID);
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

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (int Folio)
	{
		set_Value (COLUMNNAME_Folio, Integer.valueOf(Folio));
	}

	/** Get Folio.
		@return Folio	  */
	public int getFolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Folio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value.
		@param Valor Value	  */
	public void setValor (BigDecimal Valor)
	{
		if (Valor == null)
			throw new IllegalArgumentException ("Valor is mandatory.");
		set_Value (COLUMNNAME_Valor, Valor);
	}

	/** Get Value.
		@return Value	  */
	public BigDecimal getValor () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Valor);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}