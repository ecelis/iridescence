/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Tratamientos_Cues
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Tratamientos_Cues extends PO implements I_EXME_Tratamientos_Cues, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Tratamientos_Cues (Properties ctx, int EXME_Tratamientos_Cues_ID, String trxName)
    {
      super (ctx, EXME_Tratamientos_Cues_ID, trxName);
      /** if (EXME_Tratamientos_Cues_ID == 0)
        {
			setEXME_Cuestionario_ID (0);
			setEXME_Tratamientos_Cues_ID (0);
			setEXME_Tratamientos_Detalle_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Tratamientos_Cues (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Tratamientos_Cues[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Cuestionario getEXME_Cuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cuestionario.Table_Name);
        I_EXME_Cuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cuestionario_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public int getEXME_Cuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatment Questionnaire.
		@param EXME_Tratamientos_Cues_ID Treatment Questionnaire	  */
	public void setEXME_Tratamientos_Cues_ID (int EXME_Tratamientos_Cues_ID)
	{
		if (EXME_Tratamientos_Cues_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Cues_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Tratamientos_Cues_ID, Integer.valueOf(EXME_Tratamientos_Cues_ID));
	}

	/** Get Treatment Questionnaire.
		@return Treatment Questionnaire	  */
	public int getEXME_Tratamientos_Cues_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Cues_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Tratamientos_Detalle getEXME_Tratamientos_Detalle() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos_Detalle.Table_Name);
        I_EXME_Tratamientos_Detalle result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos_Detalle)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_Detalle_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatments Detail.
		@param EXME_Tratamientos_Detalle_ID Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID)
	{
		if (EXME_Tratamientos_Detalle_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Detalle_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Tratamientos_Detalle_ID, Integer.valueOf(EXME_Tratamientos_Detalle_ID));
	}

	/** Get Treatments Detail.
		@return Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Detalle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Session Number.
		@param SessionNo 
		Session Number of a treatment
	  */
	public void setSessionNo (int SessionNo)
	{
		set_Value (COLUMNNAME_SessionNo, Integer.valueOf(SessionNo));
	}

	/** Get Session Number.
		@return Session Number of a treatment
	  */
	public int getSessionNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SessionNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}