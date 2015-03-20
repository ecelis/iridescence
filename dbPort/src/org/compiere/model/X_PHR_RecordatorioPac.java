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

/** Generated Model for PHR_RecordatorioPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_RecordatorioPac extends PO implements I_PHR_RecordatorioPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_RecordatorioPac (Properties ctx, int PHR_RecordatorioPac_ID, String trxName)
    {
      super (ctx, PHR_RecordatorioPac_ID, trxName);
      /** if (PHR_RecordatorioPac_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setFechaIni (new Timestamp( System.currentTimeMillis() ));
			setItervaloMins (0);
			setNombre (null);
			setPHR_RecordatorioPac_ID (0);
			setTipoRecordatorio (null);
        } */
    }

    /** Load Constructor */
    public X_PHR_RecordatorioPac (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_PHR_RecordatorioPac[")
        .append(get_ID()).append("]");
      return sb.toString();
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
		if (FechaIni == null)
			throw new IllegalArgumentException ("FechaIni is mandatory.");
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
	}

	/** Set Start Hour.
		@param HoraInicial Start Hour	  */
	public void setHoraInicial (Timestamp HoraInicial)
	{
		set_Value (COLUMNNAME_HoraInicial, HoraInicial);
	}

	/** Get Start Hour.
		@return Start Hour	  */
	public Timestamp getHoraInicial () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HoraInicial);
	}

	/** Set ItervaloHrs.
		@param ItervaloHrs ItervaloHrs	  */
	public void setItervaloHrs (int ItervaloHrs)
	{
		set_Value (COLUMNNAME_ItervaloHrs, Integer.valueOf(ItervaloHrs));
	}

	/** Get ItervaloHrs.
		@return ItervaloHrs	  */
	public int getItervaloHrs () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ItervaloHrs);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ItervaloMins.
		@param ItervaloMins ItervaloMins	  */
	public void setItervaloMins (int ItervaloMins)
	{
		set_Value (COLUMNNAME_ItervaloMins, Integer.valueOf(ItervaloMins));
	}

	/** Get ItervaloMins.
		@return ItervaloMins	  */
	public int getItervaloMins () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ItervaloMins);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Nombre 
		Name of friend
	  */
	public void setNombre (String Nombre)
	{
		if (Nombre == null)
			throw new IllegalArgumentException ("Nombre is mandatory.");
		set_Value (COLUMNNAME_Nombre, Nombre);
	}

	/** Get Name.
		@return Name of friend
	  */
	public String getNombre () 
	{
		return (String)get_Value(COLUMNNAME_Nombre);
	}

	/** Set Patient Reminder.
		@param PHR_RecordatorioPac_ID 
		Patient Reminder
	  */
	public void setPHR_RecordatorioPac_ID (int PHR_RecordatorioPac_ID)
	{
		if (PHR_RecordatorioPac_ID < 1)
			 throw new IllegalArgumentException ("PHR_RecordatorioPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_RecordatorioPac_ID, Integer.valueOf(PHR_RecordatorioPac_ID));
	}

	/** Get Patient Reminder.
		@return Patient Reminder
	  */
	public int getPHR_RecordatorioPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_RecordatorioPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Telephone.
		@param Telefono 
		friend telephone
	  */
	public void setTelefono (String Telefono)
	{
		set_Value (COLUMNNAME_Telefono, Telefono);
	}

	/** Get Telephone.
		@return friend telephone
	  */
	public String getTelefono () 
	{
		return (String)get_Value(COLUMNNAME_Telefono);
	}

	/** TipoRecordatorio AD_Reference_ID=1200443 */
	public static final int TIPORECORDATORIO_AD_Reference_ID=1200443;
	/** SMS = SM */
	public static final String TIPORECORDATORIO_SMS = "SM";
	/** E-mail = CE */
	public static final String TIPORECORDATORIO_E_Mail = "CE";
	/** Set Reminder Type.
		@param TipoRecordatorio Reminder Type	  */
	public void setTipoRecordatorio (String TipoRecordatorio)
	{
		if (TipoRecordatorio == null) throw new IllegalArgumentException ("TipoRecordatorio is mandatory");
		if (TipoRecordatorio.equals("SM") || TipoRecordatorio.equals("CE")); else throw new IllegalArgumentException ("TipoRecordatorio Invalid value - " + TipoRecordatorio + " - Reference_ID=1200443 - SM - CE");		set_Value (COLUMNNAME_TipoRecordatorio, TipoRecordatorio);
	}

	/** Get Reminder Type.
		@return Reminder Type	  */
	public String getTipoRecordatorio () 
	{
		return (String)get_Value(COLUMNNAME_TipoRecordatorio);
	}

	/** Set Time Zone.
		@param ZonaHoraria 
		Time Zone
	  */
	public void setZonaHoraria (String ZonaHoraria)
	{
		set_Value (COLUMNNAME_ZonaHoraria, ZonaHoraria);
	}

	/** Get Time Zone.
		@return Time Zone
	  */
	public String getZonaHoraria () 
	{
		return (String)get_Value(COLUMNNAME_ZonaHoraria);
	}
}