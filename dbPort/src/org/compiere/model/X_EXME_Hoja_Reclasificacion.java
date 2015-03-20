/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Hoja_Reclasificacion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Hoja_Reclasificacion extends PO implements I_EXME_Hoja_Reclasificacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Hoja_Reclasificacion (Properties ctx, int EXME_Hoja_Reclasificacion_ID, String trxName)
    {
      super (ctx, EXME_Hoja_Reclasificacion_ID, trxName);
      /** if (EXME_Hoja_Reclasificacion_ID == 0)
        {
			setAD_User_ID (0);
// @#AD_User_ID@
			setAprobada (false);
			setCancelada (false);
			setEXME_Clas_Destino_ID (0);
			setEXME_Clas_Origen_ID (0);
			setEXME_Hoja_Reclasificacion_ID (0);
			setEXME_Paciente_ID (0);
			setFecha_Alta (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setFecha_Hosp (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setFecha_Impresion (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setTipo_Rclas (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Hoja_Reclasificacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Hoja_Reclasificacion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (int DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, Integer.valueOf(DocumentNo));
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public int getDocumentNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DocumentNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			 throw new IllegalArgumentException ("EXME_Clas_Destino_ID is mandatory.");
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

	/** Set Origin Classification.
		@param EXME_Clas_Origen_ID 
		Origin Classification
	  */
	public void setEXME_Clas_Origen_ID (int EXME_Clas_Origen_ID)
	{
		if (EXME_Clas_Origen_ID < 1)
			 throw new IllegalArgumentException ("EXME_Clas_Origen_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Clas_Origen_ID, Integer.valueOf(EXME_Clas_Origen_ID));
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

	/** Set Reclassification Sheet.
		@param EXME_Hoja_Reclasificacion_ID 
		Reclassification Sheet
	  */
	public void setEXME_Hoja_Reclasificacion_ID (int EXME_Hoja_Reclasificacion_ID)
	{
		if (EXME_Hoja_Reclasificacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Hoja_Reclasificacion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Hoja_Reclasificacion_ID, Integer.valueOf(EXME_Hoja_Reclasificacion_ID));
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

	/** Set Creation Date.
		@param Fecha_Alta 
		Creation Date
	  */
	public void setFecha_Alta (Timestamp Fecha_Alta)
	{
		if (Fecha_Alta == null)
			throw new IllegalArgumentException ("Fecha_Alta is mandatory.");
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
		if (Fecha_Hosp == null)
			throw new IllegalArgumentException ("Fecha_Hosp is mandatory.");
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
		if (Fecha_Impresion == null)
			throw new IllegalArgumentException ("Fecha_Impresion is mandatory.");
		set_Value (COLUMNNAME_Fecha_Impresion, Fecha_Impresion);
	}

	/** Get Printing Date.
		@return Printing Date
	  */
	public Timestamp getFecha_Impresion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Impresion);
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

	/** Tipo_Rclas AD_Reference_ID=1200021 */
	public static final int TIPO_RCLAS_AD_Reference_ID=1200021;
	/** Definitive = D */
	public static final String TIPO_RCLAS_Definitive = "D";
	/** Temporary = T */
	public static final String TIPO_RCLAS_Temporary = "T";
	/** Set Reclassification Type.
		@param Tipo_Rclas 
		Reclassification Type
	  */
	public void setTipo_Rclas (String Tipo_Rclas)
	{
		if (Tipo_Rclas == null) throw new IllegalArgumentException ("Tipo_Rclas is mandatory");
		if (Tipo_Rclas.equals("D") || Tipo_Rclas.equals("T")); else throw new IllegalArgumentException ("Tipo_Rclas Invalid value - " + Tipo_Rclas + " - Reference_ID=1200021 - D - T");		set_Value (COLUMNNAME_Tipo_Rclas, Tipo_Rclas);
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