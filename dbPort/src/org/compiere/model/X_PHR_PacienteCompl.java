/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for PHR_PacienteCompl
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_PHR_PacienteCompl extends PO implements I_PHR_PacienteCompl, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_PacienteCompl (Properties ctx, int PHR_PacienteCompl_ID, String trxName)
    {
      super (ctx, PHR_PacienteCompl_ID, trxName);
      /** if (PHR_PacienteCompl_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setPHR_PacienteCompl_ID (0);
			setTipoSangre (null);
        } */
    }

    /** Load Constructor */
    public X_PHR_PacienteCompl (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_PacienteCompl[")
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

	/** Set Family History.
		@param HistoriaFamiliar 
		Family History
	  */
	public void setHistoriaFamiliar (String HistoriaFamiliar)
	{
		set_Value (COLUMNNAME_HistoriaFamiliar, HistoriaFamiliar);
	}

	/** Get Family History.
		@return Family History
	  */
	public String getHistoriaFamiliar () 
	{
		return (String)get_Value(COLUMNNAME_HistoriaFamiliar);
	}

	/** Set Social History.
		@param HistoriaSocial 
		Social History
	  */
	public void setHistoriaSocial (String HistoriaSocial)
	{
		set_Value (COLUMNNAME_HistoriaSocial, HistoriaSocial);
	}

	/** Get Social History.
		@return Social History
	  */
	public String getHistoriaSocial () 
	{
		return (String)get_Value(COLUMNNAME_HistoriaSocial);
	}

	/** Set Additional information of the patient.
		@param PHR_PacienteCompl_ID 
		Additional information of the patient
	  */
	public void setPHR_PacienteCompl_ID (int PHR_PacienteCompl_ID)
	{
		if (PHR_PacienteCompl_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacienteCompl_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_PacienteCompl_ID, Integer.valueOf(PHR_PacienteCompl_ID));
	}

	/** Get Additional information of the patient.
		@return Additional information of the patient
	  */
	public int getPHR_PacienteCompl_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacienteCompl_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoSangre AD_Reference_ID=1200172 */
	public static final int TIPOSANGRE_AD_Reference_ID=1200172;
	/** AB+ = AB+ */
	public static final String TIPOSANGRE_ABPlus = "AB+";
	/** AB- = AB- */
	public static final String TIPOSANGRE_AB_ = "AB-";
	/** A+ = A+  */
	public static final String TIPOSANGRE_APlus = "A+ ";
	/** A- = A-  */
	public static final String TIPOSANGRE_A_ = "A- ";
	/** B+ = B+  */
	public static final String TIPOSANGRE_BPlus = "B+ ";
	/** B- = B-  */
	public static final String TIPOSANGRE_B_ = "B- ";
	/** O+ = O+  */
	public static final String TIPOSANGRE_OPlus = "O+ ";
	/** O- = O-  */
	public static final String TIPOSANGRE_O_ = "O- ";
	/** Unknown = UK  */
	public static final String TIPOSANGRE_Unknown = "UK ";
	/** Set Blood Type.
		@param TipoSangre Blood Type	  */
	public void setTipoSangre (String TipoSangre)
	{
		if (TipoSangre == null) throw new IllegalArgumentException ("TipoSangre is mandatory");
		if (TipoSangre.equals("AB+") || TipoSangre.equals("AB-") || TipoSangre.equals("A+ ") || TipoSangre.equals("A- ") || TipoSangre.equals("B+ ") || TipoSangre.equals("B- ") || TipoSangre.equals("O+ ") || TipoSangre.equals("O- ") || TipoSangre.equals("UK ")); else throw new IllegalArgumentException ("TipoSangre Invalid value - " + TipoSangre + " - Reference_ID=1200172 - AB+ - AB- - A+  - A-  - B+  - B-  - O+  - O-  - UK ");		set_Value (COLUMNNAME_TipoSangre, TipoSangre);
	}

	/** Get Blood Type.
		@return Blood Type	  */
	public String getTipoSangre () 
	{
		return (String)get_Value(COLUMNNAME_TipoSangre);
	}
}