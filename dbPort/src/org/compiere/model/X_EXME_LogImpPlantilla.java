/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_LogImpPlantilla
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_LogImpPlantilla extends PO implements I_EXME_LogImpPlantilla, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_LogImpPlantilla (Properties ctx, int EXME_LogImpPlantilla_ID, String trxName)
    {
      super (ctx, EXME_LogImpPlantilla_ID, trxName);
      /** if (EXME_LogImpPlantilla_ID == 0)
        {
			setEXME_LogImpPlantilla_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Plantilla_ID (0);
			setImpresionExitosa (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_LogImpPlantilla (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_LogImpPlantilla[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set EXME_LogImpPlantilla_ID.
		@param EXME_LogImpPlantilla_ID EXME_LogImpPlantilla_ID	  */
	public void setEXME_LogImpPlantilla_ID (int EXME_LogImpPlantilla_ID)
	{
		if (EXME_LogImpPlantilla_ID < 1)
			 throw new IllegalArgumentException ("EXME_LogImpPlantilla_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_LogImpPlantilla_ID, Integer.valueOf(EXME_LogImpPlantilla_ID));
	}

	/** Get EXME_LogImpPlantilla_ID.
		@return EXME_LogImpPlantilla_ID	  */
	public int getEXME_LogImpPlantilla_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LogImpPlantilla_ID);
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

	public I_EXME_Plantilla getEXME_Plantilla() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Plantilla.Table_Name);
        I_EXME_Plantilla result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Plantilla)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Plantilla_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Template.
		@param EXME_Plantilla_ID Template	  */
	public void setEXME_Plantilla_ID (int EXME_Plantilla_ID)
	{
		if (EXME_Plantilla_ID < 1)
			 throw new IllegalArgumentException ("EXME_Plantilla_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Plantilla_ID, Integer.valueOf(EXME_Plantilla_ID));
	}

	/** Get Template.
		@return Template	  */
	public int getEXME_Plantilla_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Plantilla_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Successful Printing.
		@param ImpresionExitosa 
		Successful Printing
	  */
	public void setImpresionExitosa (boolean ImpresionExitosa)
	{
		set_Value (COLUMNNAME_ImpresionExitosa, Boolean.valueOf(ImpresionExitosa));
	}

	/** Get Successful Printing.
		@return Successful Printing
	  */
	public boolean isImpresionExitosa () 
	{
		Object oo = get_Value(COLUMNNAME_ImpresionExitosa);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}