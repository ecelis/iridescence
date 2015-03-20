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

/** Generated Model for I_EXME_Traslado
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Traslado extends PO implements I_I_EXME_Traslado, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Traslado (Properties ctx, int I_EXME_Traslado_ID, String trxName)
    {
      super (ctx, I_EXME_Traslado_ID, trxName);
      /** if (I_EXME_Traslado_ID == 0)
        {
			setI_EXME_Traslado_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Traslado (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Traslado[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Diagnostic.
		@param Diagnostico 
		Diagnostic
	  */
	public void setDiagnostico (String Diagnostico)
	{
		set_Value (COLUMNNAME_Diagnostico, Diagnostico);
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public String getDiagnostico () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico);
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

	public I_EXME_Habitacion getEXME_Habitacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Habitacion.Table_Name);
        I_EXME_Habitacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Habitacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Habitacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Room.
		@param EXME_Habitacion_ID 
		Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID)
	{
		if (EXME_Habitacion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Habitacion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Habitacion_ID, Integer.valueOf(EXME_Habitacion_ID));
	}

	/** Get Room.
		@return Room
	  */
	public int getEXME_Habitacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Habitacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_Habitacion_Value.
		@param EXME_Habitacion_Value 
		Room
	  */
	public void setEXME_Habitacion_Value (String EXME_Habitacion_Value)
	{
		set_Value (COLUMNNAME_EXME_Habitacion_Value, EXME_Habitacion_Value);
	}

	/** Get EXME_Habitacion_Value.
		@return Room
	  */
	public String getEXME_Habitacion_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Habitacion_Value);
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

	public I_EXME_Traslado getEXME_Traslado() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Traslado.Table_Name);
        I_EXME_Traslado result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Traslado)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Traslado_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Transfer.
		@param EXME_Traslado_ID 
		Transfer
	  */
	public void setEXME_Traslado_ID (int EXME_Traslado_ID)
	{
		if (EXME_Traslado_ID < 1) 
			set_Value (COLUMNNAME_EXME_Traslado_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Traslado_ID, Integer.valueOf(EXME_Traslado_ID));
	}

	/** Get Transfer.
		@return Transfer
	  */
	public int getEXME_Traslado_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Traslado_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Transfer.
		@param I_EXME_Traslado_ID 
		Transfer
	  */
	public void setI_EXME_Traslado_ID (int I_EXME_Traslado_ID)
	{
		if (I_EXME_Traslado_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Traslado_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Traslado_ID, Integer.valueOf(I_EXME_Traslado_ID));
	}

	/** Get Transfer.
		@return Transfer
	  */
	public int getI_EXME_Traslado_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Traslado_ID);
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

	/** Set Transfer Place.
		@param Lugar_Tras 
		Transfer Place
	  */
	public void setLugar_Tras (String Lugar_Tras)
	{
		set_Value (COLUMNNAME_Lugar_Tras, Lugar_Tras);
	}

	/** Get Transfer Place.
		@return Transfer Place
	  */
	public String getLugar_Tras () 
	{
		return (String)get_Value(COLUMNNAME_Lugar_Tras);
	}

	/** Set Medical Manager.
		@param Medico_Resp 
		Medical Manager
	  */
	public void setMedico_Resp (String Medico_Resp)
	{
		set_Value (COLUMNNAME_Medico_Resp, Medico_Resp);
	}

	/** Get Medical Manager.
		@return Medical Manager
	  */
	public String getMedico_Resp () 
	{
		return (String)get_Value(COLUMNNAME_Medico_Resp);
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

	/** Set User T.S..
		@param User_T_S 
		User T.S.
	  */
	public void setUser_T_S (int User_T_S)
	{
		set_Value (COLUMNNAME_User_T_S, Integer.valueOf(User_T_S));
	}

	/** Get User T.S..
		@return User T.S.
	  */
	public int getUser_T_S () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User_T_S);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}