/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ConfigRecordatorio
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ConfigRecordatorio extends PO implements I_EXME_ConfigRecordatorio, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigRecordatorio (Properties ctx, int EXME_ConfigRecordatorio_ID, String trxName)
    {
      super (ctx, EXME_ConfigRecordatorio_ID, trxName);
      /** if (EXME_ConfigRecordatorio_ID == 0)
        {
			setEXME_ConfigRecordatorio_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigRecordatorio (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigRecordatorio[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Reminders Configuration.
		@param EXME_ConfigRecordatorio_ID Reminders Configuration	  */
	public void setEXME_ConfigRecordatorio_ID (int EXME_ConfigRecordatorio_ID)
	{
		if (EXME_ConfigRecordatorio_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigRecordatorio_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigRecordatorio_ID, Integer.valueOf(EXME_ConfigRecordatorio_ID));
	}

	/** Get Reminders Configuration.
		@return Reminders Configuration	  */
	public int getEXME_ConfigRecordatorio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigRecordatorio_ID);
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
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	/** Set Is Postal Mail.
		@param IsCorreo Is Postal Mail	  */
	public void setIsCorreo (boolean IsCorreo)
	{
		set_Value (COLUMNNAME_IsCorreo, Boolean.valueOf(IsCorreo));
	}

	/** Get Is Postal Mail.
		@return Is Postal Mail	  */
	public boolean isCorreo () 
	{
		Object oo = get_Value(COLUMNNAME_IsCorreo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is EMail.
		@param IsEMail Is EMail	  */
	public void setIsEMail (boolean IsEMail)
	{
		set_Value (COLUMNNAME_IsEMail, Boolean.valueOf(IsEMail));
	}

	/** Get Is EMail.
		@return Is EMail	  */
	public boolean isEMail () 
	{
		Object oo = get_Value(COLUMNNAME_IsEMail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set isPhone.
		@param isPhone 
		isPhone
	  */
	public void setisPhone (boolean isPhone)
	{
		set_Value (COLUMNNAME_isPhone, Boolean.valueOf(isPhone));
	}

	/** Get isPhone.
		@return isPhone
	  */
	public boolean isPhone () 
	{
		Object oo = get_Value(COLUMNNAME_isPhone);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is SMS.
		@param IsSMS Is SMS	  */
	public void setIsSMS (boolean IsSMS)
	{
		set_Value (COLUMNNAME_IsSMS, Boolean.valueOf(IsSMS));
	}

	/** Get Is SMS.
		@return Is SMS	  */
	public boolean isSMS () 
	{
		Object oo = get_Value(COLUMNNAME_IsSMS);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}