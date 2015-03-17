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

/** Generated Model for I_EXME_Hoja_Reclasificacion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Hoja_Reclasificacion extends PO implements I_I_EXME_Hoja_Reclasificacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Hoja_Reclasificacion (Properties ctx, int I_EXME_Hoja_Reclasificacion_ID, String trxName)
    {
      super (ctx, I_EXME_Hoja_Reclasificacion_ID, trxName);
      /** if (I_EXME_Hoja_Reclasificacion_ID == 0)
        {
			setI_EXME_Hoja_Reclasificacion_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Hoja_Reclasificacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Hoja_Reclasificacion[")
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

	/** Set User Name.
		@param AD_User_Name 
		User Name
	  */
	public void setAD_User_Name (String AD_User_Name)
	{
		set_Value (COLUMNNAME_AD_User_Name, AD_User_Name);
	}

	/** Get User Name.
		@return User Name
	  */
	public String getAD_User_Name () 
	{
		return (String)get_Value(COLUMNNAME_AD_User_Name);
	}

	/** Set Approved.
		@param Aprobada 
		Approved
	  */
	public void setAprobada (boolean Aprobada)
	{
		set_Value (COLUMNNAME_Aprobada, Boolean.valueOf(Aprobada));
	}

	/** Get Approved.
		@return Approved
	  */
	public boolean isAprobada () 
	{
		Object oo = get_Value(COLUMNNAME_Aprobada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Authorized.
		@param Autorizo 
		Authorized
	  */
	public void setAutorizo (String Autorizo)
	{
		set_Value (COLUMNNAME_Autorizo, Autorizo);
	}

	/** Get Authorized.
		@return Authorized
	  */
	public String getAutorizo () 
	{
		return (String)get_Value(COLUMNNAME_Autorizo);
	}

	/** Set Cancelled.
		@param Cancelada 
		Cancelled
	  */
	public void setCancelada (boolean Cancelada)
	{
		set_Value (COLUMNNAME_Cancelada, Boolean.valueOf(Cancelada));
	}

	/** Get Cancelled.
		@return Cancelled
	  */
	public boolean isCancelada () 
	{
		Object oo = get_Value(COLUMNNAME_Cancelada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Elaborated.
		@param Elaboro 
		Elaborated
	  */
	public void setElaboro (String Elaboro)
	{
		set_Value (COLUMNNAME_Elaboro, Elaboro);
	}

	/** Get Elaborated.
		@return Elaborated
	  */
	public String getElaboro () 
	{
		return (String)get_Value(COLUMNNAME_Elaboro);
	}

	/** Set Target Classification.
		@param EXME_Clas_Destino_ID 
		Target Classification
	  */
	public void setEXME_Clas_Destino_ID (int EXME_Clas_Destino_ID)
	{
		if (EXME_Clas_Destino_ID < 1) 
			set_Value (COLUMNNAME_EXME_Clas_Destino_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Clas_Destino_ID, Integer.valueOf(EXME_Clas_Destino_ID));
	}

	/** Get Target Classification.
		@return Target Classification
	  */
	public int getEXME_Clas_Destino_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Clas_Destino_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Destination Clasification Value.
		@param EXME_Clas_Destino_Value Destination Clasification Value	  */
	public void setEXME_Clas_Destino_Value (String EXME_Clas_Destino_Value)
	{
		set_Value (COLUMNNAME_EXME_Clas_Destino_Value, EXME_Clas_Destino_Value);
	}

	/** Get Destination Clasification Value.
		@return Destination Clasification Value	  */
	public String getEXME_Clas_Destino_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Clas_Destino_Value);
	}

	/** Set Origin Classification.
		@param EXME_Clas_Origen_ID 
		Origin Classification
	  */
	public void setEXME_Clas_Origen_ID (int EXME_Clas_Origen_ID)
	{
		if (EXME_Clas_Origen_ID < 1) 
			set_Value (COLUMNNAME_EXME_Clas_Origen_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Clas_Origen_ID, Integer.valueOf(EXME_Clas_Origen_ID));
	}

	/** Get Origin Classification.
		@return Origin Classification
	  */
	public int getEXME_Clas_Origen_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Clas_Origen_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Origin Clasification Value.
		@param EXME_Clas_Origen_Value Origin Clasification Value	  */
	public void setEXME_Clas_Origen_Value (String EXME_Clas_Origen_Value)
	{
		set_Value (COLUMNNAME_EXME_Clas_Origen_Value, EXME_Clas_Origen_Value);
	}

	/** Get Origin Clasification Value.
		@return Origin Clasification Value	  */
	public String getEXME_Clas_Origen_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Clas_Origen_Value);
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

	public I_EXME_Hoja_Reclasificacion getEXME_Hoja_Reclasificacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Hoja_Reclasificacion.Table_Name);
        I_EXME_Hoja_Reclasificacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Hoja_Reclasificacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Hoja_Reclasificacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Reclassification Sheet.
		@param EXME_Hoja_Reclasificacion_ID 
		Reclassification Sheet
	  */
	public void setEXME_Hoja_Reclasificacion_ID (int EXME_Hoja_Reclasificacion_ID)
	{
		if (EXME_Hoja_Reclasificacion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Hoja_Reclasificacion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Hoja_Reclasificacion_ID, Integer.valueOf(EXME_Hoja_Reclasificacion_ID));
	}

	/** Get Reclassification Sheet.
		@return Reclassification Sheet
	  */
	public int getEXME_Hoja_Reclasificacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hoja_Reclasificacion_ID);
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

	/** Set Creation Date.
		@param Fecha_Alta 
		Creation Date
	  */
	public void setFecha_Alta (Timestamp Fecha_Alta)
	{
		set_Value (COLUMNNAME_Fecha_Alta, Fecha_Alta);
	}

	/** Get Creation Date.
		@return Creation Date
	  */
	public Timestamp getFecha_Alta () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Alta);
	}

	/** Set Inpatient Date.
		@param Fecha_Hosp 
		Inpatient Date
	  */
	public void setFecha_Hosp (Timestamp Fecha_Hosp)
	{
		set_Value (COLUMNNAME_Fecha_Hosp, Fecha_Hosp);
	}

	/** Get Inpatient Date.
		@return Inpatient Date
	  */
	public Timestamp getFecha_Hosp () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Hosp);
	}

	/** Set Printing Date.
		@param Fecha_Impresion 
		Printing Date
	  */
	public void setFecha_Impresion (Timestamp Fecha_Impresion)
	{
		set_Value (COLUMNNAME_Fecha_Impresion, Fecha_Impresion);
	}

	/** Get Printing Date.
		@return Printing Date
	  */
	public Timestamp getFecha_Impresion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Impresion);
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

	/** Set Reclasification Sheet.
		@param I_EXME_Hoja_Reclasificacion_ID 
		Reclasification Sheet
	  */
	public void setI_EXME_Hoja_Reclasificacion_ID (int I_EXME_Hoja_Reclasificacion_ID)
	{
		if (I_EXME_Hoja_Reclasificacion_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Hoja_Reclasificacion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Hoja_Reclasificacion_ID, Integer.valueOf(I_EXME_Hoja_Reclasificacion_ID));
	}

	/** Get Reclasification Sheet.
		@return Reclasification Sheet
	  */
	public int getI_EXME_Hoja_Reclasificacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Hoja_Reclasificacion_ID);
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

	/** Set Classification Reason.
		@param Motivo_Rclasif 
		Classification Reason
	  */
	public void setMotivo_Rclasif (String Motivo_Rclasif)
	{
		set_Value (COLUMNNAME_Motivo_Rclasif, Motivo_Rclasif);
	}

	/** Get Classification Reason.
		@return Classification Reason
	  */
	public String getMotivo_Rclasif () 
	{
		return (String)get_Value(COLUMNNAME_Motivo_Rclasif);
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

	/** Set Supervised.
		@param Superviso 
		Supervised
	  */
	public void setSuperviso (String Superviso)
	{
		set_Value (COLUMNNAME_Superviso, Superviso);
	}

	/** Get Supervised.
		@return Supervised
	  */
	public String getSuperviso () 
	{
		return (String)get_Value(COLUMNNAME_Superviso);
	}

	/** Set Reclassification Type.
		@param Tipo_Rclas 
		Reclassification Type
	  */
	public void setTipo_Rclas (String Tipo_Rclas)
	{
		set_Value (COLUMNNAME_Tipo_Rclas, Tipo_Rclas);
	}

	/** Get Reclassification Type.
		@return Reclassification Type
	  */
	public String getTipo_Rclas () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Rclas);
	}

	/** Set OK.
		@param VoBo 
		OK
	  */
	public void setVoBo (String VoBo)
	{
		set_Value (COLUMNNAME_VoBo, VoBo);
	}

	/** Get OK.
		@return OK
	  */
	public String getVoBo () 
	{
		return (String)get_Value(COLUMNNAME_VoBo);
	}
}