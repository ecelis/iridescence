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

/** Generated Model for EXME_CtaPacDieta
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CtaPacDieta extends PO implements I_EXME_CtaPacDieta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacDieta (Properties ctx, int EXME_CtaPacDieta_ID, String trxName)
    {
      super (ctx, EXME_CtaPacDieta_ID, trxName);
      /** if (EXME_CtaPacDieta_ID == 0)
        {
			setEXME_CtaPacDieta_ID (0);
			setEXME_CtaPac_ID (0);
			setEXME_Dieta_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacDieta (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacDieta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Encounter Diet .
		@param EXME_CtaPacDieta_ID Encounter Diet 	  */
	public void setEXME_CtaPacDieta_ID (int EXME_CtaPacDieta_ID)
	{
		if (EXME_CtaPacDieta_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacDieta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPacDieta_ID, Integer.valueOf(EXME_CtaPacDieta_ID));
	}

	/** Get Encounter Diet .
		@return Encounter Diet 	  */
	public int getEXME_CtaPacDieta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacDieta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_Dieta getEXME_Dieta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Dieta.Table_Name);
        I_EXME_Dieta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Dieta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Dieta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diet.
		@param EXME_Dieta_ID Diet	  */
	public void setEXME_Dieta_ID (int EXME_Dieta_ID)
	{
		if (EXME_Dieta_ID < 1)
			 throw new IllegalArgumentException ("EXME_Dieta_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Dieta_ID, Integer.valueOf(EXME_Dieta_ID));
	}

	/** Get Diet.
		@return Diet	  */
	public int getEXME_Dieta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dieta_ID);
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