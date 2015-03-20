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

/** Generated Model for I_EXME_Notas_Trabajador
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Notas_Trabajador extends PO implements I_I_EXME_Notas_Trabajador, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Notas_Trabajador (Properties ctx, int I_EXME_Notas_Trabajador_ID, String trxName)
    {
      super (ctx, I_EXME_Notas_Trabajador_ID, trxName);
      /** if (I_EXME_Notas_Trabajador_ID == 0)
        {
			setI_EXME_Notas_Trabajador_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Notas_Trabajador (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Notas_Trabajador[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Notas_Trabajador getEXME_Notas_Trabajador() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Notas_Trabajador.Table_Name);
        I_EXME_Notas_Trabajador result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Notas_Trabajador)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Notas_Trabajador_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Notes of the Social Worker.
		@param EXME_Notas_Trabajador_ID 
		Notes of the Social Worker
	  */
	public void setEXME_Notas_Trabajador_ID (int EXME_Notas_Trabajador_ID)
	{
		if (EXME_Notas_Trabajador_ID < 1) 
			set_Value (COLUMNNAME_EXME_Notas_Trabajador_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Notas_Trabajador_ID, Integer.valueOf(EXME_Notas_Trabajador_ID));
	}

	/** Get Notes of the Social Worker.
		@return Notes of the Social Worker
	  */
	public int getEXME_Notas_Trabajador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Notas_Trabajador_ID);
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

	/** Set Note Date.
		@param Fecha_Nota 
		Note Date
	  */
	public void setFecha_Nota (Timestamp Fecha_Nota)
	{
		set_Value (COLUMNNAME_Fecha_Nota, Fecha_Nota);
	}

	/** Get Note Date.
		@return Note Date
	  */
	public Timestamp getFecha_Nota () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Nota);
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

	/** Set Social Worker Notes.
		@param I_EXME_Notas_Trabajador_ID Social Worker Notes	  */
	public void setI_EXME_Notas_Trabajador_ID (int I_EXME_Notas_Trabajador_ID)
	{
		if (I_EXME_Notas_Trabajador_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Notas_Trabajador_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Notas_Trabajador_ID, Integer.valueOf(I_EXME_Notas_Trabajador_ID));
	}

	/** Get Social Worker Notes.
		@return Social Worker Notes	  */
	public int getI_EXME_Notas_Trabajador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Notas_Trabajador_ID);
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

	/** Set Note.
		@param Nota 
		Note
	  */
	public void setNota (String Nota)
	{
		set_Value (COLUMNNAME_Nota, Nota);
	}

	/** Get Note.
		@return Note
	  */
	public String getNota () 
	{
		return (String)get_Value(COLUMNNAME_Nota);
	}

	/** Set Note2.
		@param Nota2 Note2	  */
	public void setNota2 (String Nota2)
	{
		set_Value (COLUMNNAME_Nota2, Nota2);
	}

	/** Get Note2.
		@return Note2	  */
	public String getNota2 () 
	{
		return (String)get_Value(COLUMNNAME_Nota2);
	}

	/** Set Nota3.
		@param Nota3 Nota3	  */
	public void setNota3 (String Nota3)
	{
		set_Value (COLUMNNAME_Nota3, Nota3);
	}

	/** Get Nota3.
		@return Nota3	  */
	public String getNota3 () 
	{
		return (String)get_Value(COLUMNNAME_Nota3);
	}

	/** Set History Number.
		@param NumHist History Number	  */
	public void setNumHist (String NumHist)
	{
		set_Value (COLUMNNAME_NumHist, NumHist);
	}

	/** Get History Number.
		@return History Number	  */
	public String getNumHist () 
	{
		return (String)get_Value(COLUMNNAME_NumHist);
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
}