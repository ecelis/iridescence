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

/** Generated Model for I_EXME_EnviosPago
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_EnviosPago extends PO implements I_I_EXME_EnviosPago, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_EnviosPago (Properties ctx, int I_EXME_EnviosPago_ID, String trxName)
    {
      super (ctx, I_EXME_EnviosPago_ID, trxName);
      /** if (I_EXME_EnviosPago_ID == 0)
        {
			setI_EXME_EnviosPago_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_EnviosPago (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_EnviosPago[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Classification.
		@param Clasificacion 
		Classification
	  */
	public void setClasificacion (String Clasificacion)
	{
		set_Value (COLUMNNAME_Clasificacion, Clasificacion);
	}

	/** Get Classification.
		@return Classification
	  */
	public String getClasificacion () 
	{
		return (String)get_Value(COLUMNNAME_Clasificacion);
	}

	/** Set Payment Concept.
		@param ConcPago 
		Payment Concept
	  */
	public void setConcPago (String ConcPago)
	{
		set_Value (COLUMNNAME_ConcPago, ConcPago);
	}

	/** Get Payment Concept.
		@return Payment Concept
	  */
	public String getConcPago () 
	{
		return (String)get_Value(COLUMNNAME_ConcPago);
	}

	/** Set Debit.
		@param Debe 
		Debit
	  */
	public void setDebe (BigDecimal Debe)
	{
		set_Value (COLUMNNAME_Debe, Debe);
	}

	/** Get Debit.
		@return Debit
	  */
	public BigDecimal getDebe () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Debe);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_EnviosPago getEXME_EnviosPago() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EnviosPago.Table_Name);
        I_EXME_EnviosPago result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EnviosPago)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EnviosPago_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Shipping Payment.
		@param EXME_EnviosPago_ID 
		Shipping Payment
	  */
	public void setEXME_EnviosPago_ID (int EXME_EnviosPago_ID)
	{
		if (EXME_EnviosPago_ID < 1) 
			set_Value (COLUMNNAME_EXME_EnviosPago_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EnviosPago_ID, Integer.valueOf(EXME_EnviosPago_ID));
	}

	/** Get Shipping Payment.
		@return Shipping Payment
	  */
	public int getEXME_EnviosPago_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnviosPago_ID);
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

	/** Set I_EXME_EnviosPago_ID.
		@param I_EXME_EnviosPago_ID I_EXME_EnviosPago_ID	  */
	public void setI_EXME_EnviosPago_ID (int I_EXME_EnviosPago_ID)
	{
		if (I_EXME_EnviosPago_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_EnviosPago_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_EnviosPago_ID, Integer.valueOf(I_EXME_EnviosPago_ID));
	}

	/** Get I_EXME_EnviosPago_ID.
		@return I_EXME_EnviosPago_ID	  */
	public int getI_EXME_EnviosPago_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_EnviosPago_ID);
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

	/** Set Pay.
		@param Paga 
		Pay
	  */
	public void setPaga (BigDecimal Paga)
	{
		set_Value (COLUMNNAME_Paga, Paga);
	}

	/** Get Pay.
		@return Pay
	  */
	public BigDecimal getPaga () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Paga);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Paid.
		@param Pagado Paid	  */
	public void setPagado (boolean Pagado)
	{
		set_Value (COLUMNNAME_Pagado, Boolean.valueOf(Pagado));
	}

	/** Get Paid.
		@return Paid	  */
	public boolean isPagado () 
	{
		Object oo = get_Value(COLUMNNAME_Pagado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
}