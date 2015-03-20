/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_RecursoEducativoDiag
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RecursoEducativoDiag extends PO implements I_EXME_RecursoEducativoDiag, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RecursoEducativoDiag (Properties ctx, int EXME_RecursoEducativoDiag_ID, String trxName)
    {
      super (ctx, EXME_RecursoEducativoDiag_ID, trxName);
      /** if (EXME_RecursoEducativoDiag_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_RecursoEducativoDiag_ID (0);
			setEXME_RecursoEducativo_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_RecursoEducativoDiag (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_RecursoEducativoDiag[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Diagnostico.Table_Name);
        I_EXME_Diagnostico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Diagnostico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Diagnostico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnostic Education Resource.
		@param EXME_RecursoEducativoDiag_ID Diagnostic Education Resource	  */
	public void setEXME_RecursoEducativoDiag_ID (int EXME_RecursoEducativoDiag_ID)
	{
		if (EXME_RecursoEducativoDiag_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativoDiag_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RecursoEducativoDiag_ID, Integer.valueOf(EXME_RecursoEducativoDiag_ID));
	}

	/** Get Diagnostic Education Resource.
		@return Diagnostic Education Resource	  */
	public int getEXME_RecursoEducativoDiag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativoDiag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RecursoEducativo.Table_Name);
        I_EXME_RecursoEducativo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RecursoEducativo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RecursoEducativo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Education Resource.
		@param EXME_RecursoEducativo_ID Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID)
	{
		if (EXME_RecursoEducativo_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_RecursoEducativo_ID, Integer.valueOf(EXME_RecursoEducativo_ID));
	}

	/** Get Education Resource.
		@return Education Resource	  */
	public int getEXME_RecursoEducativo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}