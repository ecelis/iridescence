/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_MedicoEsp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_MedicoEsp extends PO implements I_I_EXME_MedicoEsp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_MedicoEsp (Properties ctx, int I_EXME_MedicoEsp_ID, String trxName)
    {
      super (ctx, I_EXME_MedicoEsp_ID, trxName);
      /** if (I_EXME_MedicoEsp_ID == 0)
        {
			setI_EXME_MedicoEsp_ID (0);
			setI_IsImported (false);
// N
        } */
    }

    /** Load Constructor */
    public X_I_EXME_MedicoEsp (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_MedicoEsp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Specialty Value.
		@param Especialidad_Value 
		Specialty search value
	  */
	public void setEspecialidad_Value (String Especialidad_Value)
	{
		set_Value (COLUMNNAME_Especialidad_Value, Especialidad_Value);
	}

	/** Get Specialty Value.
		@return Specialty search value
	  */
	public String getEspecialidad_Value () 
	{
		return (String)get_Value(COLUMNNAME_Especialidad_Value);
	}

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MedicoEsp getEXME_MedicoEsp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MedicoEsp.Table_Name);
        I_EXME_MedicoEsp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MedicoEsp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MedicoEsp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Specialty Doctor.
		@param EXME_MedicoEsp_ID 
		Specialty Doctor
	  */
	public void setEXME_MedicoEsp_ID (int EXME_MedicoEsp_ID)
	{
		if (EXME_MedicoEsp_ID < 1) 
			set_Value (COLUMNNAME_EXME_MedicoEsp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MedicoEsp_ID, Integer.valueOf(EXME_MedicoEsp_ID));
	}

	/** Get Specialty Doctor.
		@return Specialty Doctor
	  */
	public int getEXME_MedicoEsp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoEsp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico_Sust getEXME_Medico_Sust() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico_Sust.Table_Name);
        I_EXME_Medico_Sust result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico_Sust)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_Sust_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Substitute Doctor.
		@param EXME_Medico_Sust_ID 
		Substitute doctor
	  */
	public void setEXME_Medico_Sust_ID (int EXME_Medico_Sust_ID)
	{
		if (EXME_Medico_Sust_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_Sust_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_Sust_ID, Integer.valueOf(EXME_Medico_Sust_ID));
	}

	/** Get Substitute Doctor.
		@return Substitute doctor
	  */
	public int getEXME_Medico_Sust_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_Sust_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Guards.
		@param HaceGuardia Guards	  */
	public void setHaceGuardia (boolean HaceGuardia)
	{
		set_Value (COLUMNNAME_HaceGuardia, Boolean.valueOf(HaceGuardia));
	}

	/** Get Guards.
		@return Guards	  */
	public boolean isHaceGuardia () 
	{
		Object oo = get_Value(COLUMNNAME_HaceGuardia);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported Specialty-Doctor.
		@param I_EXME_MedicoEsp_ID 
		Imorted Specialty Doctor
	  */
	public void setI_EXME_MedicoEsp_ID (int I_EXME_MedicoEsp_ID)
	{
		if (I_EXME_MedicoEsp_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_MedicoEsp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_MedicoEsp_ID, Integer.valueOf(I_EXME_MedicoEsp_ID));
	}

	/** Get Imported Specialty-Doctor.
		@return Imorted Specialty Doctor
	  */
	public int getI_EXME_MedicoEsp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_MedicoEsp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Substituted Doctor Code.
		@param MedicoSust_Value 
		Substituted Doctor search code
	  */
	public void setMedicoSust_Value (String MedicoSust_Value)
	{
		set_Value (COLUMNNAME_MedicoSust_Value, MedicoSust_Value);
	}

	/** Get Substituted Doctor Code.
		@return Substituted Doctor search code
	  */
	public String getMedicoSust_Value () 
	{
		return (String)get_Value(COLUMNNAME_MedicoSust_Value);
	}

	/** Set Doctor Code.
		@param Medico_Value 
		Doctor search code
	  */
	public void setMedico_Value (String Medico_Value)
	{
		set_Value (COLUMNNAME_Medico_Value, Medico_Value);
	}

	/** Get Doctor Code.
		@return Doctor search code
	  */
	public String getMedico_Value () 
	{
		return (String)get_Value(COLUMNNAME_Medico_Value);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}