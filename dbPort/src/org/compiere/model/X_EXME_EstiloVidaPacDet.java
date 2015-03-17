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

/** Generated Model for EXME_EstiloVidaPacDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EstiloVidaPacDet extends PO implements I_EXME_EstiloVidaPacDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EstiloVidaPacDet (Properties ctx, int EXME_EstiloVidaPacDet_ID, String trxName)
    {
      super (ctx, EXME_EstiloVidaPacDet_ID, trxName);
      /** if (EXME_EstiloVidaPacDet_ID == 0)
        {
			setEXME_EstiloVidaPacDet_ID (0);
			setEXME_EstiloVidaPaciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EstiloVidaPacDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EstiloVidaPacDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_ActPacienteDiag getEXME_ActPacienteDiag() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteDiag.Table_Name);
        I_EXME_ActPacienteDiag result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteDiag)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteDiag_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Diagnostic.
		@param EXME_ActPacienteDiag_ID 
		Patient's Diagnostic
	  */
	public void setEXME_ActPacienteDiag_ID (int EXME_ActPacienteDiag_ID)
	{
		if (EXME_ActPacienteDiag_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteDiag_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteDiag_ID, Integer.valueOf(EXME_ActPacienteDiag_ID));
	}

	/** Get Patient's Diagnostic.
		@return Patient's Diagnostic
	  */
	public int getEXME_ActPacienteDiag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteDiag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Life Style History.
		@param EXME_EstiloVidaPacDet_ID Patient Life Style History	  */
	public void setEXME_EstiloVidaPacDet_ID (int EXME_EstiloVidaPacDet_ID)
	{
		if (EXME_EstiloVidaPacDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstiloVidaPacDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EstiloVidaPacDet_ID, Integer.valueOf(EXME_EstiloVidaPacDet_ID));
	}

	/** Get Patient Life Style History.
		@return Patient Life Style History	  */
	public int getEXME_EstiloVidaPacDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstiloVidaPacDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EstiloVidaPaciente getEXME_EstiloVidaPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstiloVidaPaciente.Table_Name);
        I_EXME_EstiloVidaPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstiloVidaPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstiloVidaPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Life Style.
		@param EXME_EstiloVidaPaciente_ID Patient's Life Style	  */
	public void setEXME_EstiloVidaPaciente_ID (int EXME_EstiloVidaPaciente_ID)
	{
		if (EXME_EstiloVidaPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstiloVidaPaciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EstiloVidaPaciente_ID, Integer.valueOf(EXME_EstiloVidaPaciente_ID));
	}

	/** Get Patient's Life Style.
		@return Patient's Life Style	  */
	public int getEXME_EstiloVidaPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstiloVidaPaciente_ID);
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
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
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

	/** Set Monitoring.
		@param Seguimiento Monitoring	  */
	public void setSeguimiento (String Seguimiento)
	{
		set_Value (COLUMNNAME_Seguimiento, Seguimiento);
	}

	/** Get Monitoring.
		@return Monitoring	  */
	public String getSeguimiento () 
	{
		return (String)get_Value(COLUMNNAME_Seguimiento);
	}
}