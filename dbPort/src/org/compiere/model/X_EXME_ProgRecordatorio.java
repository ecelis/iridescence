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

/** Generated Model for EXME_ProgRecordatorio
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProgRecordatorio extends PO implements I_EXME_ProgRecordatorio, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProgRecordatorio (Properties ctx, int EXME_ProgRecordatorio_ID, String trxName)
    {
      super (ctx, EXME_ProgRecordatorio_ID, trxName);
      /** if (EXME_ProgRecordatorio_ID == 0)
        {
			setEXME_ProgRecordatorio_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProgRecordatorio (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProgRecordatorio[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reminder Programation.
		@param EXME_ProgRecordatorio_ID Reminder Programation	  */
	public void setEXME_ProgRecordatorio_ID (int EXME_ProgRecordatorio_ID)
	{
		if (EXME_ProgRecordatorio_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProgRecordatorio_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProgRecordatorio_ID, Integer.valueOf(EXME_ProgRecordatorio_ID));
	}

	/** Get Reminder Programation.
		@return Reminder Programation	  */
	public int getEXME_ProgRecordatorio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgRecordatorio_ID);
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

	/** Set Frequency.
		@param Frequency 
		Frequency of events
	  */
	public void setFrequency (int Frequency)
	{
		set_Value (COLUMNNAME_Frequency, Integer.valueOf(Frequency));
	}

	/** Get Frequency.
		@return Frequency of events
	  */
	public int getFrequency () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Frequency);
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

	/** Set Manual.
		@param IsManual 
		This is a manual process
	  */
	public void setIsManual (boolean IsManual)
	{
		set_Value (COLUMNNAME_IsManual, Boolean.valueOf(IsManual));
	}

	/** Get Manual.
		@return This is a manual process
	  */
	public boolean isManual () 
	{
		Object oo = get_Value(COLUMNNAME_IsManual);
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

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 1) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoRecordatorio AD_Reference_ID=1200376 */
	public static final int TIPORECORDATORIO_AD_Reference_ID=1200376;
	/** EXME_CitaMedica = CM */
	public static final String TIPORECORDATORIO_EXME_CitaMedica = "CM";
	/** EXME_ActPacienteIndH = SS */
	public static final String TIPORECORDATORIO_EXME_ActPacienteIndH = "SS";
	/** EXME_PreOperatorio = SQ */
	public static final String TIPORECORDATORIO_EXME_PreOperatorio = "SQ";
	/** EXME_Interconsulta = SE */
	public static final String TIPORECORDATORIO_EXME_Interconsulta = "SE";
	/** Clinical Decison = CD */
	public static final String TIPORECORDATORIO_ClinicalDecison = "CD";
	/** Others = OT */
	public static final String TIPORECORDATORIO_Others = "OT";
	/** Set Reminder Type.
		@param TipoRecordatorio Reminder Type	  */
	public void setTipoRecordatorio (String TipoRecordatorio)
	{

		if (TipoRecordatorio == null || TipoRecordatorio.equals("CM") || TipoRecordatorio.equals("SS") || TipoRecordatorio.equals("SQ") || TipoRecordatorio.equals("SE") || TipoRecordatorio.equals("CD") || TipoRecordatorio.equals("OT")); else throw new IllegalArgumentException ("TipoRecordatorio Invalid value - " + TipoRecordatorio + " - Reference_ID=1200376 - CM - SS - SQ - SE - CD - OT");		set_Value (COLUMNNAME_TipoRecordatorio, TipoRecordatorio);
	}

	/** Get Reminder Type.
		@return Reminder Type	  */
	public String getTipoRecordatorio () 
	{
		return (String)get_Value(COLUMNNAME_TipoRecordatorio);
	}

	/** Set To Doctor.
		@param ToMedico To Doctor	  */
	public void setToMedico (boolean ToMedico)
	{
		set_Value (COLUMNNAME_ToMedico, Boolean.valueOf(ToMedico));
	}

	/** Get To Doctor.
		@return To Doctor	  */
	public boolean isToMedico () 
	{
		Object oo = get_Value(COLUMNNAME_ToMedico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Unidad AD_Reference_ID=221 */
	public static final int UNIDAD_AD_Reference_ID=221;
	/** Minute = M */
	public static final String UNIDAD_Minute = "M";
	/** Hour = H */
	public static final String UNIDAD_Hour = "H";
	/** Day = D */
	public static final String UNIDAD_Day = "D";
	/** Set Unity.
		@param Unidad Unity	  */
	public void setUnidad (String Unidad)
	{

		if (Unidad == null || Unidad.equals("M") || Unidad.equals("H") || Unidad.equals("D")); else throw new IllegalArgumentException ("Unidad Invalid value - " + Unidad + " - Reference_ID=221 - M - H - D");		set_Value (COLUMNNAME_Unidad, Unidad);
	}

	/** Get Unity.
		@return Unity	  */
	public String getUnidad () 
	{
		return (String)get_Value(COLUMNNAME_Unidad);
	}
}