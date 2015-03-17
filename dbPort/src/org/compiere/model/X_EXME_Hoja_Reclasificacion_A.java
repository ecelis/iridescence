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

/** Generated Model for EXME_Hoja_Reclasificacion_A
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Hoja_Reclasificacion_A extends PO implements I_EXME_Hoja_Reclasificacion_A, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Hoja_Reclasificacion_A (Properties ctx, int EXME_Hoja_Reclasificacion_A_ID, String trxName)
    {
      super (ctx, EXME_Hoja_Reclasificacion_A_ID, trxName);
      /** if (EXME_Hoja_Reclasificacion_A_ID == 0)
        {
			setAD_User_ID (0);
			setAprobada (false);
			setCancelada (false);
			setEXME_Hoja_Reclasificacion_A_ID (0);
			setEXME_Paciente_ID (0);
			setFecha_Alta (new Timestamp( System.currentTimeMillis() ));
			setFecha_Hosp (new Timestamp( System.currentTimeMillis() ));
			setFecha_Impresion (new Timestamp( System.currentTimeMillis() ));
			setVersion (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Hoja_Reclasificacion_A (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Hoja_Reclasificacion_A[")
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

	/** Set Audit Reclassification Sheet.
		@param EXME_Hoja_Reclasificacion_A_ID 
		Audit Reclassification Sheet
	  */
	public void setEXME_Hoja_Reclasificacion_A_ID (int EXME_Hoja_Reclasificacion_A_ID)
	{
		if (EXME_Hoja_Reclasificacion_A_ID < 1)
			 throw new IllegalArgumentException ("EXME_Hoja_Reclasificacion_A_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Hoja_Reclasificacion_A_ID, Integer.valueOf(EXME_Hoja_Reclasificacion_A_ID));
	}

	/** Get Audit Reclassification Sheet.
		@return Audit Reclassification Sheet
	  */
	public int getEXME_Hoja_Reclasificacion_A_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hoja_Reclasificacion_A_ID);
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

		if (Tipo_Rclas == null || Tipo_Rclas.equals("D") || Tipo_Rclas.equals("T")); else throw new IllegalArgumentException ("Tipo_Rclas Invalid value - " + Tipo_Rclas + " - Reference_ID=1200021 - D - T");		set_Value (COLUMNNAME_Tipo_Rclas, Tipo_Rclas);
	}

	/** Get Reclassification Type.
		@return Reclassification Type
	  */
	public String getTipo_Rclas () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Rclas);
	}

	/** Set Version.
		@param Version 
		Version of the table definition
	  */
	public void setVersion (int Version)
	{
		set_Value (COLUMNNAME_Version, Integer.valueOf(Version));
	}

	/** Get Version.
		@return Version of the table definition
	  */
	public int getVersion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Version);
		if (ii == null)
			 return 0;
		return ii.intValue();
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