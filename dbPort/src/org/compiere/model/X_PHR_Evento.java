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

/** Generated Model for PHR_Evento
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_Evento extends PO implements I_PHR_Evento, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_Evento (Properties ctx, int PHR_Evento_ID, String trxName)
    {
      super (ctx, PHR_Evento_ID, trxName);
      /** if (PHR_Evento_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setPHR_Evento_ID (0);
			setPHR_MedicoPac_ID (0);
			setTipoEvento (null);
        } */
    }

    /** Load Constructor */
    public X_PHR_Evento (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_Evento[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BinaryData.
		@param BinaryData 
		Binary Data
	  */
	public void setBinaryData (byte[] BinaryData)
	{
		set_Value (COLUMNNAME_BinaryData, BinaryData);
	}

	/** Get BinaryData.
		@return Binary Data
	  */
	public byte[] getBinaryData () 
	{
		return (byte[])get_Value(COLUMNNAME_BinaryData);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** EventSection AD_Reference_ID=1200442 */
	public static final int EVENTSECTION_AD_Reference_ID=1200442;
	/** Medical Conditions = M */
	public static final String EVENTSECTION_MedicalConditions = "M";
	/** Medications = m */
	public static final String EVENTSECTION_Medications = "m";
	/** Medical Procedures & Surgeries = S */
	public static final String EVENTSECTION_MedicalProceduresSurgeries = "S";
	/** Vital Signs = V */
	public static final String EVENTSECTION_VitalSigns = "V";
	/** Vaccinations = v */
	public static final String EVENTSECTION_Vaccinations = "v";
	/** Set Event Section.
		@param EventSection 
		Event Section
	  */
	public void setEventSection (String EventSection)
	{

		if (EventSection == null || EventSection.equals("M") || EventSection.equals("m") || EventSection.equals("S") || EventSection.equals("V") || EventSection.equals("v")); else throw new IllegalArgumentException ("EventSection Invalid value - " + EventSection + " - Reference_ID=1200442 - M - m - S - V - v");		set_Value (COLUMNNAME_EventSection, EventSection);
	}

	/** Get Event Section.
		@return Event Section
	  */
	public String getEventSection () 
	{
		return (String)get_Value(COLUMNNAME_EventSection);
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

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		set_Value (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}

	/** Set Patient Event.
		@param PHR_Evento_ID Patient Event	  */
	public void setPHR_Evento_ID (int PHR_Evento_ID)
	{
		if (PHR_Evento_ID < 1)
			 throw new IllegalArgumentException ("PHR_Evento_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_Evento_ID, Integer.valueOf(PHR_Evento_ID));
	}

	/** Get Patient Event.
		@return Patient Event	  */
	public int getPHR_Evento_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_Evento_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PHR_MedicoPac getPHR_MedicoPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_MedicoPac.Table_Name);
        I_PHR_MedicoPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_MedicoPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_MedicoPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient medical.
		@param PHR_MedicoPac_ID 
		Patient medical
	  */
	public void setPHR_MedicoPac_ID (int PHR_MedicoPac_ID)
	{
		if (PHR_MedicoPac_ID < 1)
			 throw new IllegalArgumentException ("PHR_MedicoPac_ID is mandatory.");
		set_Value (COLUMNNAME_PHR_MedicoPac_ID, Integer.valueOf(PHR_MedicoPac_ID));
	}

	/** Get Patient medical.
		@return Patient medical
	  */
	public int getPHR_MedicoPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_MedicoPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoEvento AD_Reference_ID=1200433 */
	public static final int TIPOEVENTO_AD_Reference_ID=1200433;
	/** Consultation = 1 */
	public static final String TIPOEVENTO_Consultation = "1";
	/** Ambulatory = 2 */
	public static final String TIPOEVENTO_Ambulatory = "2";
	/** Outpatient surgery = 3 */
	public static final String TIPOEVENTO_OutpatientSurgery = "3";
	/** Hospitalization = 4 */
	public static final String TIPOEVENTO_Hospitalization = "4";
	/** Others = 5 */
	public static final String TIPOEVENTO_Others = "5";
	/** Set Event Type.
		@param TipoEvento Event Type	  */
	public void setTipoEvento (String TipoEvento)
	{
		if (TipoEvento == null) throw new IllegalArgumentException ("TipoEvento is mandatory");
		if (TipoEvento.equals("1") || TipoEvento.equals("2") || TipoEvento.equals("3") || TipoEvento.equals("4") || TipoEvento.equals("5")); else throw new IllegalArgumentException ("TipoEvento Invalid value - " + TipoEvento + " - Reference_ID=1200433 - 1 - 2 - 3 - 4 - 5");		set_Value (COLUMNNAME_TipoEvento, TipoEvento);
	}

	/** Get Event Type.
		@return Event Type	  */
	public String getTipoEvento () 
	{
		return (String)get_Value(COLUMNNAME_TipoEvento);
	}
}