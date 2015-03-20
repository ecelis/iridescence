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

/** Generated Model for PHR_PacienteCondMed
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_PacienteCondMed extends PO implements I_PHR_PacienteCondMed, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_PacienteCondMed (Properties ctx, int PHR_PacienteCondMed_ID, String trxName)
    {
      super (ctx, PHR_PacienteCondMed_ID, trxName);
      /** if (PHR_PacienteCondMed_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_Paciente_ID (0);
			setPHR_PacienteCondMed_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_PacienteCondMed (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_PHR_PacienteCondMed[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Date of Diagnosis.
		@param FechaDiagnostico 
		Date of Diagnosis
	  */
	public void setFechaDiagnostico (Timestamp FechaDiagnostico)
	{
		set_Value (COLUMNNAME_FechaDiagnostico, FechaDiagnostico);
	}

	/** Get Date of Diagnosis.
		@return Date of Diagnosis
	  */
	public Timestamp getFechaDiagnostico () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaDiagnostico);
	}

	public I_PHR_Evento getPHR_Evento() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_Evento.Table_Name);
        I_PHR_Evento result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_Evento)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_Evento_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Event.
		@param PHR_Evento_ID Patient Event	  */
	public void setPHR_Evento_ID (int PHR_Evento_ID)
	{
		if (PHR_Evento_ID < 1) 
			set_Value (COLUMNNAME_PHR_Evento_ID, null);
		else 
			set_Value (COLUMNNAME_PHR_Evento_ID, Integer.valueOf(PHR_Evento_ID));
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

	/** Set Patient Medical Condition.
		@param PHR_PacienteCondMed_ID Patient Medical Condition	  */
	public void setPHR_PacienteCondMed_ID (int PHR_PacienteCondMed_ID)
	{
		if (PHR_PacienteCondMed_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacienteCondMed_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_PacienteCondMed_ID, Integer.valueOf(PHR_PacienteCondMed_ID));
	}

	/** Get Patient Medical Condition.
		@return Patient Medical Condition	  */
	public int getPHR_PacienteCondMed_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacienteCondMed_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}