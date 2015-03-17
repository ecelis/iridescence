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

/** Generated Model for I_EXME_INP_AdmisionHosp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_INP_AdmisionHosp extends PO implements I_I_EXME_INP_AdmisionHosp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_INP_AdmisionHosp (Properties ctx, int I_EXME_INP_AdmisionHosp_ID, String trxName)
    {
      super (ctx, I_EXME_INP_AdmisionHosp_ID, trxName);
      /** if (I_EXME_INP_AdmisionHosp_ID == 0)
        {
			setI_EXME_INP_AdmisionHosp_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_INP_AdmisionHosp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_INP_AdmisionHosp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Last Name.
		@param Apellido1 
		Last Name
	  */
	public void setApellido1 (String Apellido1)
	{
		set_Value (COLUMNNAME_Apellido1, Apellido1);
	}

	/** Get Last Name.
		@return Last Name
	  */
	public String getApellido1 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido1);
	}

	/** Set Mother's Maiden Name.
		@param Apellido2 
		Mother's Maiden Name
	  */
	public void setApellido2 (String Apellido2)
	{
		set_Value (COLUMNNAME_Apellido2, Apellido2);
	}

	/** Get Mother's Maiden Name.
		@return Mother's Maiden Name
	  */
	public String getApellido2 () 
	{
		return (String)get_Value(COLUMNNAME_Apellido2);
	}

	/** Set Classification_Value.
		@param Clasificacion_Value Classification_Value	  */
	public void setClasificacion_Value (String Clasificacion_Value)
	{
		set_Value (COLUMNNAME_Clasificacion_Value, Clasificacion_Value);
	}

	/** Get Classification_Value.
		@return Classification_Value	  */
	public String getClasificacion_Value () 
	{
		return (String)get_Value(COLUMNNAME_Clasificacion_Value);
	}

	/** Set Condi.
		@param Condi Condi	  */
	public void setCondi (String Condi)
	{
		set_Value (COLUMNNAME_Condi, Condi);
	}

	/** Get Condi.
		@return Condi	  */
	public String getCondi () 
	{
		return (String)get_Value(COLUMNNAME_Condi);
	}

	public I_EXME_ClasCliente getEXME_ClasCliente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClasCliente.Table_Name);
        I_EXME_ClasCliente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClasCliente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClasCliente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Classification and Customer.
		@param EXME_ClasCliente_ID Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID)
	{
		if (EXME_ClasCliente_ID < 1) 
			set_Value (COLUMNNAME_EXME_ClasCliente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ClasCliente_ID, Integer.valueOf(EXME_ClasCliente_ID));
	}

	/** Get Classification and Customer.
		@return Classification and Customer	  */
	public int getEXME_ClasCliente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasCliente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_INP_AdmisionHosp getEXME_INP_AdmisionHosp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_INP_AdmisionHosp.Table_Name);
        I_EXME_INP_AdmisionHosp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_INP_AdmisionHosp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_INP_AdmisionHosp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_INP_AdmisionHosp_ID.
		@param EXME_INP_AdmisionHosp_ID EXME_INP_AdmisionHosp_ID	  */
	public void setEXME_INP_AdmisionHosp_ID (int EXME_INP_AdmisionHosp_ID)
	{
		if (EXME_INP_AdmisionHosp_ID < 1) 
			set_Value (COLUMNNAME_EXME_INP_AdmisionHosp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_INP_AdmisionHosp_ID, Integer.valueOf(EXME_INP_AdmisionHosp_ID));
	}

	/** Get EXME_INP_AdmisionHosp_ID.
		@return EXME_INP_AdmisionHosp_ID	  */
	public int getEXME_INP_AdmisionHosp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_INP_AdmisionHosp_ID);
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

	/** Set Medical Record_Value.
		@param Expediente_Value Medical Record_Value	  */
	public void setExpediente_Value (String Expediente_Value)
	{
		set_Value (COLUMNNAME_Expediente_Value, Expediente_Value);
	}

	/** Get Medical Record_Value.
		@return Medical Record_Value	  */
	public String getExpediente_Value () 
	{
		return (String)get_Value(COLUMNNAME_Expediente_Value);
	}

	/** Set Fecha_Apertura.
		@param Fecha_Apertura Fecha_Apertura	  */
	public void setFecha_Apertura (Timestamp Fecha_Apertura)
	{
		set_Value (COLUMNNAME_Fecha_Apertura, Fecha_Apertura);
	}

	/** Get Fecha_Apertura.
		@return Fecha_Apertura	  */
	public Timestamp getFecha_Apertura () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Apertura);
	}

	/** Set Fecha_Defuncion.
		@param Fecha_Defuncion Fecha_Defuncion	  */
	public void setFecha_Defuncion (Timestamp Fecha_Defuncion)
	{
		set_Value (COLUMNNAME_Fecha_Defuncion, Fecha_Defuncion);
	}

	/** Get Fecha_Defuncion.
		@return Fecha_Defuncion	  */
	public Timestamp getFecha_Defuncion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Defuncion);
	}

	/** Set Fecha_GuiaVerde.
		@param Fecha_GuiaVerde Fecha_GuiaVerde	  */
	public void setFecha_GuiaVerde (Timestamp Fecha_GuiaVerde)
	{
		set_Value (COLUMNNAME_Fecha_GuiaVerde, Fecha_GuiaVerde);
	}

	/** Get Fecha_GuiaVerde.
		@return Fecha_GuiaVerde	  */
	public Timestamp getFecha_GuiaVerde () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_GuiaVerde);
	}

	/** Set Fecha_Microfilmacion.
		@param Fecha_Microfilmacion Fecha_Microfilmacion	  */
	public void setFecha_Microfilmacion (Timestamp Fecha_Microfilmacion)
	{
		set_Value (COLUMNNAME_Fecha_Microfilmacion, Fecha_Microfilmacion);
	}

	/** Get Fecha_Microfilmacion.
		@return Fecha_Microfilmacion	  */
	public Timestamp getFecha_Microfilmacion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Microfilmacion);
	}

	/** Set Birth Date.
		@param FechaNac 
		Birth Date
	  */
	public void setFechaNac (Timestamp FechaNac)
	{
		set_Value (COLUMNNAME_FechaNac, FechaNac);
	}

	/** Get Birth Date.
		@return Birth Date
	  */
	public Timestamp getFechaNac () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaNac);
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

	/** Set I_EXME_INP_AdmisionHosp_ID.
		@param I_EXME_INP_AdmisionHosp_ID I_EXME_INP_AdmisionHosp_ID	  */
	public void setI_EXME_INP_AdmisionHosp_ID (int I_EXME_INP_AdmisionHosp_ID)
	{
		if (I_EXME_INP_AdmisionHosp_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_INP_AdmisionHosp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_INP_AdmisionHosp_ID, Integer.valueOf(I_EXME_INP_AdmisionHosp_ID));
	}

	/** Get I_EXME_INP_AdmisionHosp_ID.
		@return I_EXME_INP_AdmisionHosp_ID	  */
	public int getI_EXME_INP_AdmisionHosp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_INP_AdmisionHosp_ID);
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

	/** Set Microrrollo.
		@param Microrrollo Microrrollo	  */
	public void setMicrorrollo (String Microrrollo)
	{
		set_Value (COLUMNNAME_Microrrollo, Microrrollo);
	}

	/** Get Microrrollo.
		@return Microrrollo	  */
	public String getMicrorrollo () 
	{
		return (String)get_Value(COLUMNNAME_Microrrollo);
	}

	/** Set Name.
		@param Nombre 
		Name of friend
	  */
	public void setNombre (String Nombre)
	{
		set_Value (COLUMNNAME_Nombre, Nombre);
	}

	/** Get Name.
		@return Name of friend
	  */
	public String getNombre () 
	{
		return (String)get_Value(COLUMNNAME_Nombre);
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

	/** Set Sex.
		@param Sexo 
		Sex
	  */
	public void setSexo (boolean Sexo)
	{
		set_Value (COLUMNNAME_Sexo, Boolean.valueOf(Sexo));
	}

	/** Get Sex.
		@return Sex
	  */
	public boolean isSexo () 
	{
		Object oo = get_Value(COLUMNNAME_Sexo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}