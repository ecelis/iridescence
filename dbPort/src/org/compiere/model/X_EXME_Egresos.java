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

/** Generated Model for EXME_Egresos
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Egresos extends PO implements I_EXME_Egresos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Egresos (Properties ctx, int EXME_Egresos_ID, String trxName)
    {
      super (ctx, EXME_Egresos_ID, trxName);
      /** if (EXME_Egresos_ID == 0)
        {
			setConsecutivo (0);
			setEXME_Egresos_ID (0);
			setEXME_Paciente_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
// @Date@
			setTipoArea (null);
// @#TipoArea@
        } */
    }

    /** Load Constructor */
    public X_EXME_Egresos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Egresos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Consecutive.
		@param Consecutivo 
		Consecutive
	  */
	public void setConsecutivo (int Consecutivo)
	{
		set_Value (COLUMNNAME_Consecutivo, Integer.valueOf(Consecutivo));
	}

	/** Get Consecutive.
		@return Consecutive
	  */
	public int getConsecutivo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Consecutivo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Current Account.
		@param CtasCor 
		Current Account Responsible of Authorize
	  */
	public void setCtasCor (String CtasCor)
	{
		set_Value (COLUMNNAME_CtasCor, CtasCor);
	}

	/** Get Current Account.
		@return Current Account Responsible of Authorize
	  */
	public String getCtasCor () 
	{
		return (String)get_Value(COLUMNNAME_CtasCor);
	}

	/** Set Diagnostic 1.
		@param Diagnostico1 Diagnostic 1	  */
	public void setDiagnostico1 (String Diagnostico1)
	{
		set_Value (COLUMNNAME_Diagnostico1, Diagnostico1);
	}

	/** Get Diagnostic 1.
		@return Diagnostic 1	  */
	public String getDiagnostico1 () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico1);
	}

	/** Set Diagnostic 2.
		@param Diagnostico2 Diagnostic 2	  */
	public void setDiagnostico2 (String Diagnostico2)
	{
		set_Value (COLUMNNAME_Diagnostico2, Diagnostico2);
	}

	/** Get Diagnostic 2.
		@return Diagnostic 2	  */
	public String getDiagnostico2 () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico2);
	}

	/** Set Diagnostic 3.
		@param Diagnostico3 Diagnostic 3	  */
	public void setDiagnostico3 (String Diagnostico3)
	{
		set_Value (COLUMNNAME_Diagnostico3, Diagnostico3);
	}

	/** Get Diagnostic 3.
		@return Diagnostic 3	  */
	public String getDiagnostico3 () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico3);
	}

	/** Set Diagnostic 4.
		@param Diagnostico4 Diagnostic 4	  */
	public void setDiagnostico4 (String Diagnostico4)
	{
		set_Value (COLUMNNAME_Diagnostico4, Diagnostico4);
	}

	/** Get Diagnostic 4.
		@return Diagnostic 4	  */
	public String getDiagnostico4 () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico4);
	}

	/** Set Bed.
		@param EXME_Cama_ID 
		Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID)
	{
		if (EXME_Cama_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cama_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cama_ID, Integer.valueOf(EXME_Cama_ID));
	}

	/** Get Bed.
		@return Bed
	  */
	public int getEXME_Cama_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
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
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
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

	/** Set Discharges.
		@param EXME_Egresos_ID 
		Discharges
	  */
	public void setEXME_Egresos_ID (int EXME_Egresos_ID)
	{
		if (EXME_Egresos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Egresos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Egresos_ID, Integer.valueOf(EXME_Egresos_ID));
	}

	/** Get Discharges.
		@return Discharges
	  */
	public int getEXME_Egresos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Egresos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical File.
		@param EXME_Hist_Exp_ID 
		Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID)
	{
		if (EXME_Hist_Exp_ID < 1) 
			set_Value (COLUMNNAME_EXME_Hist_Exp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Hist_Exp_ID, Integer.valueOf(EXME_Hist_Exp_ID));
	}

	/** Get Medical File.
		@return Medical File
	  */
	public int getEXME_Hist_Exp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hist_Exp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discharge Reason.
		@param EXME_MotivoEgreso_ID Discharge Reason	  */
	public void setEXME_MotivoEgreso_ID (int EXME_MotivoEgreso_ID)
	{
		if (EXME_MotivoEgreso_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoEgreso_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoEgreso_ID, Integer.valueOf(EXME_MotivoEgreso_ID));
	}

	/** Get Discharge Reason.
		@return Discharge Reason	  */
	public int getEXME_MotivoEgreso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoEgreso_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, IsPrinted);
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public String getIsPrinted () 
	{
		return (String)get_Value(COLUMNNAME_IsPrinted);
	}

	/** Set Motive.
		@param Motivo 
		Motive / Reason
	  */
	public void setMotivo (String Motivo)
	{
		set_Value (COLUMNNAME_Motivo, Motivo);
	}

	/** Get Motive.
		@return Motive / Reason
	  */
	public String getMotivo () 
	{
		return (String)get_Value(COLUMNNAME_Motivo);
	}

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		set_Value (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pac);
	}

	/** Set Servicios Clinicos.
		@param ServClin 
		Servicios Clinicos
	  */
	public void setServClin (String ServClin)
	{
		set_Value (COLUMNNAME_ServClin, ServClin);
	}

	/** Get Servicios Clinicos.
		@return Servicios Clinicos
	  */
	public String getServClin () 
	{
		return (String)get_Value(COLUMNNAME_ServClin);
	}

	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null)
			throw new IllegalArgumentException ("TipoArea is mandatory.");
		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}

	/** Set Social Worker.
		@param TS 
		Social Worker
	  */
	public void setTS (String TS)
	{
		set_Value (COLUMNNAME_TS, TS);
	}

	/** Get Social Worker.
		@return Social Worker
	  */
	public String getTS () 
	{
		return (String)get_Value(COLUMNNAME_TS);
	}

	/** Set OK.
		@param VisBueno OK	  */
	public void setVisBueno (String VisBueno)
	{
		set_Value (COLUMNNAME_VisBueno, VisBueno);
	}

	/** Get OK.
		@return OK	  */
	public String getVisBueno () 
	{
		return (String)get_Value(COLUMNNAME_VisBueno);
	}
}