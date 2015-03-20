/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProcedureDiag
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_ProcedureDiag extends PO implements I_EXME_ProcedureDiag, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProcedureDiag (Properties ctx, int EXME_ProcedureDiag_ID, String trxName)
    {
      super (ctx, EXME_ProcedureDiag_ID, trxName);
      /** if (EXME_ProcedureDiag_ID == 0)
        {
			setEXME_ActPacienteDiag_ID (0);
			setEXME_ActPacienteInd_ID (0);
			setEXME_ProcedureDiag_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProcedureDiag (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProcedureDiag[")
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

	/** Set Diagnostic.
		@param EXME_ActPacienteDiag_ID 
		Diagnostic
	  */
	public void setEXME_ActPacienteDiag_ID (int EXME_ActPacienteDiag_ID)
	{
		if (EXME_ActPacienteDiag_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteDiag_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ActPacienteDiag_ID, Integer.valueOf(EXME_ActPacienteDiag_ID));
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public int getEXME_ActPacienteDiag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteDiag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteInd.Table_Name);
        I_EXME_ActPacienteInd result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteInd)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteInd_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Indications Patient Activity.
		@param EXME_ActPacienteInd_ID 
		Indications Patient Activity
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID)
	{
		if (EXME_ActPacienteInd_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteInd_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, Integer.valueOf(EXME_ActPacienteInd_ID));
	}

	/** Get Indications Patient Activity.
		@return Indications Patient Activity
	  */
	public int getEXME_ActPacienteInd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteInd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Procedure/Diagnosis.
		@param EXME_ProcedureDiag_ID Procedure/Diagnosis	  */
	public void setEXME_ProcedureDiag_ID (int EXME_ProcedureDiag_ID)
	{
		if (EXME_ProcedureDiag_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProcedureDiag_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProcedureDiag_ID, Integer.valueOf(EXME_ProcedureDiag_ID));
	}

	/** Get Procedure/Diagnosis.
		@return Procedure/Diagnosis	  */
	public int getEXME_ProcedureDiag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcedureDiag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}