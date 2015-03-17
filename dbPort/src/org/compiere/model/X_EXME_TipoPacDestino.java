/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_TipoPacDestino
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_TipoPacDestino extends PO implements I_EXME_TipoPacDestino, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TipoPacDestino (Properties ctx, int EXME_TipoPacDestino_ID, String trxName)
    {
      super (ctx, EXME_TipoPacDestino_ID, trxName);
      /** if (EXME_TipoPacDestino_ID == 0)
        {
			setChangeDate (false);
			setEXME_TipoPacDestino_ID (0);
			setEXME_TipoPaciente_ID (0);
			setEXME_TipoPacienteTo_ID (0);
			setRequireAuthentication (false);
// N
        } */
    }

    /** Load Constructor */
    public X_EXME_TipoPacDestino (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TipoPacDestino[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Alert Message.
		@param AlertMessage 
		Alert Message
	  */
	public void setAlertMessage (String AlertMessage)
	{
		set_Value (COLUMNNAME_AlertMessage, AlertMessage);
	}

	/** Get Alert Message.
		@return Alert Message
	  */
	public String getAlertMessage () 
	{
		return (String)get_Value(COLUMNNAME_AlertMessage);
	}

	/** Set Change Date.
		@param ChangeDate Change Date	  */
	public void setChangeDate (boolean ChangeDate)
	{
		set_Value (COLUMNNAME_ChangeDate, Boolean.valueOf(ChangeDate));
	}

	/** Get Change Date.
		@return Change Date	  */
	public boolean isChangeDate () 
	{
		Object oo = get_Value(COLUMNNAME_ChangeDate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_EXME_AdmitSource getEXME_AdmitSource() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_AdmitSource.Table_Name);
        I_EXME_AdmitSource result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_AdmitSource)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_AdmitSource_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Admit Source.
		@param EXME_AdmitSource_ID 
		Admit Source of the patient account
	  */
	public void setEXME_AdmitSource_ID (int EXME_AdmitSource_ID)
	{
		if (EXME_AdmitSource_ID < 1) 
			set_Value (COLUMNNAME_EXME_AdmitSource_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AdmitSource_ID, Integer.valueOf(EXME_AdmitSource_ID));
	}

	/** Get Admit Source.
		@return Admit Source of the patient account
	  */
	public int getEXME_AdmitSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AdmitSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_AdmitType getEXME_AdmitType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_AdmitType.Table_Name);
        I_EXME_AdmitType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_AdmitType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_AdmitType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Admit Type.
		@param EXME_AdmitType_ID 
		Admit Type of the Patient account
	  */
	public void setEXME_AdmitType_ID (int EXME_AdmitType_ID)
	{
		if (EXME_AdmitType_ID < 1) 
			set_Value (COLUMNNAME_EXME_AdmitType_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AdmitType_ID, Integer.valueOf(EXME_AdmitType_ID));
	}

	/** Get Admit Type.
		@return Admit Type of the Patient account
	  */
	public int getEXME_AdmitType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AdmitType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set To Patient Type.
		@param EXME_TipoPacDestino_ID To Patient Type	  */
	public void setEXME_TipoPacDestino_ID (int EXME_TipoPacDestino_ID)
	{
		if (EXME_TipoPacDestino_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoPacDestino_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TipoPacDestino_ID, Integer.valueOf(EXME_TipoPacDestino_ID));
	}

	/** Get To Patient Type.
		@return To Patient Type	  */
	public int getEXME_TipoPacDestino_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPacDestino_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type of Patient.
		@param EXME_TipoPaciente_ID 
		Type of Patient
	  */
	public void setEXME_TipoPaciente_ID (int EXME_TipoPaciente_ID)
	{
		if (EXME_TipoPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoPaciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TipoPaciente_ID, Integer.valueOf(EXME_TipoPaciente_ID));
	}

	/** Get Type of Patient.
		@return Type of Patient
	  */
	public int getEXME_TipoPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set To Patient Type.
		@param EXME_TipoPacienteTo_ID To Patient Type	  */
	public void setEXME_TipoPacienteTo_ID (int EXME_TipoPacienteTo_ID)
	{
		if (EXME_TipoPacienteTo_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoPacienteTo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoPacienteTo_ID, Integer.valueOf(EXME_TipoPacienteTo_ID));
	}

	/** Get To Patient Type.
		@return To Patient Type	  */
	public int getEXME_TipoPacienteTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPacienteTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Require Authentication.
		@param RequireAuthentication 
		Indicates that this patient type change will require authentication
	  */
	public void setRequireAuthentication (boolean RequireAuthentication)
	{
		set_Value (COLUMNNAME_RequireAuthentication, Boolean.valueOf(RequireAuthentication));
	}

	/** Get Require Authentication.
		@return Indicates that this patient type change will require authentication
	  */
	public boolean isRequireAuthentication () 
	{
		Object oo = get_Value(COLUMNNAME_RequireAuthentication);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}