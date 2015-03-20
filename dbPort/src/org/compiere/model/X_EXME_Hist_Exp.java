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

/** Generated Model for EXME_Hist_Exp
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_Hist_Exp extends PO implements I_EXME_Hist_Exp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Hist_Exp (Properties ctx, int EXME_Hist_Exp_ID, String trxName)
    {
      super (ctx, EXME_Hist_Exp_ID, trxName);
      /** if (EXME_Hist_Exp_ID == 0)
        {
			setCancelado (false);
			setC_DocType_ID (0);
			setDocumentNo (null);
			setEXME_Hist_Exp_ID (0);
			setEXME_Paciente_ID (0);
			setFecha_Exp (new Timestamp( System.currentTimeMillis() ));
			setIsPrestado (false);
// N
        } */
    }

    /** Load Constructor */
    public X_EXME_Hist_Exp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Hist_Exp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Cancelled.
		@param Cancelado 
		Cancels Patient's Medical Record Use
	  */
	public void setCancelado (boolean Cancelado)
	{
		set_Value (COLUMNNAME_Cancelado, Boolean.valueOf(Cancelado));
	}

	/** Get Cancelled.
		@return Cancels Patient's Medical Record Use
	  */
	public boolean isCancelado () 
	{
		Object oo = get_Value(COLUMNNAME_Cancelado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_C_DocType getC_DocType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_DocType.Table_Name);
        I_C_DocType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DocType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DocType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0)
			 throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
		set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set Medical File.
		@param EXME_Hist_Exp_ID 
		Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID)
	{
		if (EXME_Hist_Exp_ID < 1)
			 throw new IllegalArgumentException ("EXME_Hist_Exp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Hist_Exp_ID, Integer.valueOf(EXME_Hist_Exp_ID));
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

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	/** Set Dates Creation of Clinical Process.
		@param Fecha_Exp 
		Dates Creation of Clinical Process
	  */
	public void setFecha_Exp (Timestamp Fecha_Exp)
	{
		if (Fecha_Exp == null)
			throw new IllegalArgumentException ("Fecha_Exp is mandatory.");
		set_Value (COLUMNNAME_Fecha_Exp, Fecha_Exp);
	}

	/** Get Dates Creation of Clinical Process.
		@return Dates Creation of Clinical Process
	  */
	public Timestamp getFecha_Exp () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Exp);
	}

	/** Set Is On Loan.
		@param IsPrestado 
		Is On Loan
	  */
	public void setIsPrestado (boolean IsPrestado)
	{
		set_Value (COLUMNNAME_IsPrestado, Boolean.valueOf(IsPrestado));
	}

	/** Get Is On Loan.
		@return Is On Loan
	  */
	public boolean isPrestado () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrestado);
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
		throw new IllegalArgumentException ("Nombre_Pac is virtual column");	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pac);
	}
}