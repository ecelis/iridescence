/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for I_EXME_Egresos
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Egresos extends PO implements I_I_EXME_Egresos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Egresos (Properties ctx, int I_EXME_Egresos_ID, String trxName)
    {
      super (ctx, I_EXME_Egresos_ID, trxName);
      /** if (I_EXME_Egresos_ID == 0)
        {
			setI_EXME_Egresos_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Egresos (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Egresos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Consecutive.
		@param Consecutivo 
		Consecutive
	  */
	public void setConsecutivo (BigDecimal Consecutivo)
	{
		set_Value (COLUMNNAME_Consecutivo, Consecutivo);
	}

	/** Get Consecutive.
		@return Consecutive
	  */
	public BigDecimal getConsecutivo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Consecutivo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set DocumentoNo.
		@param DocumentoNo 
		DocumentoNo
	  */
	public void setDocumentoNo (String DocumentoNo)
	{
		set_Value (COLUMNNAME_DocumentoNo, DocumentoNo);
	}

	/** Get DocumentoNo.
		@return DocumentoNo
	  */
	public String getDocumentoNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentoNo);
	}

	public I_EXME_Cama getEXME_Cama() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cama.Table_Name);
        I_EXME_Cama result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cama)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cama_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Bed Value.
		@param EXME_Cama_Value Bed Value	  */
	public void setEXME_Cama_Value (String EXME_Cama_Value)
	{
		set_Value (COLUMNNAME_EXME_Cama_Value, EXME_Cama_Value);
	}

	/** Get Bed Value.
		@return Bed Value	  */
	public String getEXME_Cama_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Cama_Value);
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

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Diagnostico.Table_Name);
        I_EXME_Diagnostico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Diagnostico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Diagnostico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Diagnostic.
		@param EXME_Diagnostico_Value 
		Diagnostic
	  */
	public void setEXME_Diagnostico_Value (String EXME_Diagnostico_Value)
	{
		set_Value (COLUMNNAME_EXME_Diagnostico_Value, EXME_Diagnostico_Value);
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public String getEXME_Diagnostico_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Diagnostico_Value);
	}

	public I_EXME_Egresos getEXME_Egresos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Egresos.Table_Name);
        I_EXME_Egresos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Egresos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Egresos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Discharges.
		@param EXME_Egresos_ID 
		Discharges
	  */
	public void setEXME_Egresos_ID (int EXME_Egresos_ID)
	{
		if (EXME_Egresos_ID < 1) 
			set_Value (COLUMNNAME_EXME_Egresos_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Egresos_ID, Integer.valueOf(EXME_Egresos_ID));
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

	/** Set EXME_EstServ_Value.
		@param EXME_EstServ_Value 
		Service Station
	  */
	public void setEXME_EstServ_Value (String EXME_EstServ_Value)
	{
		set_Value (COLUMNNAME_EXME_EstServ_Value, EXME_EstServ_Value);
	}

	/** Get EXME_EstServ_Value.
		@return Service Station
	  */
	public String getEXME_EstServ_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_EstServ_Value);
	}

	public I_EXME_Hist_Exp getEXME_Hist_Exp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Hist_Exp.Table_Name);
        I_EXME_Hist_Exp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Hist_Exp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Hist_Exp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	public I_EXME_MotivoEgreso getEXME_MotivoEgreso() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoEgreso.Table_Name);
        I_EXME_MotivoEgreso result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoEgreso)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoEgreso_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Chekout Motive_Name.
		@param EXME_MotivoEgreso_Name 
		Chekout Motive
	  */
	public void setEXME_MotivoEgreso_Name (String EXME_MotivoEgreso_Name)
	{
		set_Value (COLUMNNAME_EXME_MotivoEgreso_Name, EXME_MotivoEgreso_Name);
	}

	/** Get Chekout Motive_Name.
		@return Chekout Motive
	  */
	public String getEXME_MotivoEgreso_Name () 
	{
		return (String)get_Value(COLUMNNAME_EXME_MotivoEgreso_Name);
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
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
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

	/** Set Patient History Number.
		@param EXME_Paciente_Value 
		Patient History Number
	  */
	public void setEXME_Paciente_Value (String EXME_Paciente_Value)
	{
		set_Value (COLUMNNAME_EXME_Paciente_Value, EXME_Paciente_Value);
	}

	/** Get Patient History Number.
		@return Patient History Number
	  */
	public String getEXME_Paciente_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Paciente_Value);
	}

	/** Set Medical Record.
		@param Expediente 
		Medical Record
	  */
	public void setExpediente (String Expediente)
	{
		set_Value (COLUMNNAME_Expediente, Expediente);
	}

	/** Get Medical Record.
		@return Medical Record
	  */
	public String getExpediente () 
	{
		return (String)get_Value(COLUMNNAME_Expediente);
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
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

	/** Set I_EXME_Egresos_ID.
		@param I_EXME_Egresos_ID I_EXME_Egresos_ID	  */
	public void setI_EXME_Egresos_ID (int I_EXME_Egresos_ID)
	{
		if (I_EXME_Egresos_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Egresos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Egresos_ID, Integer.valueOf(I_EXME_Egresos_ID));
	}

	/** Get I_EXME_Egresos_ID.
		@return I_EXME_Egresos_ID	  */
	public int getI_EXME_Egresos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Egresos_ID);
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

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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