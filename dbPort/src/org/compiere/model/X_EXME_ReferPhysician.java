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

/** Generated Model for EXME_ReferPhysician
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ReferPhysician extends PO implements I_EXME_ReferPhysician, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ReferPhysician (Properties ctx, int EXME_ReferPhysician_ID, String trxName)
    {
      super (ctx, EXME_ReferPhysician_ID, trxName);
      /** if (EXME_ReferPhysician_ID == 0)
        {
			setEXME_CitaMedica_ID (0);
			setEXME_ReferPhysician_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ReferPhysician (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ReferPhysician[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Target Organization.
		@param AD_Org_Dest_ID 
		The organization to refer to
	  */
	public void setAD_Org_Dest_ID (int AD_Org_Dest_ID)
	{
		if (AD_Org_Dest_ID < 1) 
			set_Value (COLUMNNAME_AD_Org_Dest_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Org_Dest_ID, Integer.valueOf(AD_Org_Dest_ID));
	}

	/** Get Target Organization.
		@return The organization to refer to
	  */
	public int getAD_Org_Dest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Org_Dest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Notes.
		@param Comentarios Notes	  */
	public void setComentarios (String Comentarios)
	{
		set_Value (COLUMNNAME_Comentarios, Comentarios);
	}

	/** Get Notes.
		@return Notes	  */
	public String getComentarios () 
	{
		return (String)get_Value(COLUMNNAME_Comentarios);
	}

	/** Set Target Office Visit.
		@param EXME_CitaMedDest_ID 
		The generated office visit from the referral
	  */
	public void setEXME_CitaMedDest_ID (int EXME_CitaMedDest_ID)
	{
		if (EXME_CitaMedDest_ID < 1) 
			set_Value (COLUMNNAME_EXME_CitaMedDest_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CitaMedDest_ID, Integer.valueOf(EXME_CitaMedDest_ID));
	}

	/** Get Target Office Visit.
		@return The generated office visit from the referral
	  */
	public int getEXME_CitaMedDest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CitaMedDest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CitaMedica.Table_Name);
        I_EXME_CitaMedica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CitaMedica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CitaMedica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Appointment.
		@param EXME_CitaMedica_ID 
		Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID)
	{
		if (EXME_CitaMedica_ID < 1)
			 throw new IllegalArgumentException ("EXME_CitaMedica_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CitaMedica_ID, Integer.valueOf(EXME_CitaMedica_ID));
	}

	/** Get Medical Appointment.
		@return Medical appointment
	  */
	public int getEXME_CitaMedica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CitaMedica_ID);
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad.Table_Name);
        I_EXME_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Institucion.Table_Name);
        I_EXME_Institucion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Institucion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Institucion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Institution.
		@param EXME_Institucion_ID 
		Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID)
	{
		if (EXME_Institucion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Institucion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Institucion_ID, Integer.valueOf(EXME_Institucion_ID));
	}

	/** Get Institution.
		@return Institution
	  */
	public int getEXME_Institucion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Institucion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Target Physician.
		@param EXME_MedicoDest_ID 
		The physician to refer to
	  */
	public void setEXME_MedicoDest_ID (int EXME_MedicoDest_ID)
	{
		if (EXME_MedicoDest_ID < 1) 
			set_Value (COLUMNNAME_EXME_MedicoDest_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MedicoDest_ID, Integer.valueOf(EXME_MedicoDest_ID));
	}

	/** Get Target Physician.
		@return The physician to refer to
	  */
	public int getEXME_MedicoDest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoDest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reference Physician.
		@param EXME_ReferPhysician_ID 
		Reference Physician
	  */
	public void setEXME_ReferPhysician_ID (int EXME_ReferPhysician_ID)
	{
		if (EXME_ReferPhysician_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReferPhysician_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ReferPhysician_ID, Integer.valueOf(EXME_ReferPhysician_ID));
	}

	/** Get Reference Physician.
		@return Reference Physician
	  */
	public int getEXME_ReferPhysician_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReferPhysician_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Facility OID.
		@param FacilityOID Facility OID	  */
	public void setFacilityOID (String FacilityOID)
	{
		set_Value (COLUMNNAME_FacilityOID, FacilityOID);
	}

	/** Get Facility OID.
		@return Facility OID	  */
	public String getFacilityOID () 
	{
		return (String)get_Value(COLUMNNAME_FacilityOID);
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

	/** Set Medical Name.
		@param Nombre_Med 
		Medical complete name
	  */
	public void setNombre_Med (String Nombre_Med)
	{
		set_Value (COLUMNNAME_Nombre_Med, Nombre_Med);
	}

	/** Get Medical Name.
		@return Medical complete name
	  */
	public String getNombre_Med () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Med);
	}

	/** Set Number of Treatments.
		@param NumTratamiento 
		Number of Treatments
	  */
	public void setNumTratamiento (int NumTratamiento)
	{
		set_Value (COLUMNNAME_NumTratamiento, Integer.valueOf(NumTratamiento));
	}

	/** Get Number of Treatments.
		@return Number of Treatments
	  */
	public int getNumTratamiento () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumTratamiento);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Others.
		@param Otros Others	  */
	public void setOtros (String Otros)
	{
		set_Value (COLUMNNAME_Otros, Otros);
	}

	/** Get Others.
		@return Others	  */
	public String getOtros () 
	{
		return (String)get_Value(COLUMNNAME_Otros);
	}

	/** Set Supplier.
		@param Proveedor Supplier	  */
	public void setProveedor (String Proveedor)
	{
		set_Value (COLUMNNAME_Proveedor, Proveedor);
	}

	/** Get Supplier.
		@return Supplier	  */
	public String getProveedor () 
	{
		return (String)get_Value(COLUMNNAME_Proveedor);
	}

	/** Status AD_Reference_ID=1200519 */
	public static final int STATUS_AD_Reference_ID=1200519;
	/** Inactive = IN */
	public static final String STATUS_Inactive = "IN";
	/** Physician = PH */
	public static final String STATUS_Physician = "PH";
	/** Assistant = AS */
	public static final String STATUS_Assistant = "AS";
	/** CN = CN */
	public static final String STATUS_CN = "CN";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		if (Status == null || Status.equals("IN") || Status.equals("PH") || Status.equals("AS") || Status.equals("CN")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=1200519 - IN - PH - AS - CN");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** TipoConsulta AD_Reference_ID=1200510 */
	public static final int TIPOCONSULTA_AD_Reference_ID=1200510;
	/** Evaluation Only = EO */
	public static final String TIPOCONSULTA_EvaluationOnly = "EO";
	/** Evaluation and Single Treatment = ES */
	public static final String TIPOCONSULTA_EvaluationAndSingleTreatment = "ES";
	/** Evaluation and Treatment = ET */
	public static final String TIPOCONSULTA_EvaluationAndTreatment = "ET";
	/** Other (Specify) = O */
	public static final String TIPOCONSULTA_OtherSpecify = "O";
	/** Set Type of Consultation.
		@param TipoConsulta 
		To the consultant
	  */
	public void setTipoConsulta (String TipoConsulta)
	{

		if (TipoConsulta == null || TipoConsulta.equals("EO") || TipoConsulta.equals("ES") || TipoConsulta.equals("ET") || TipoConsulta.equals("O")); else throw new IllegalArgumentException ("TipoConsulta Invalid value - " + TipoConsulta + " - Reference_ID=1200510 - EO - ES - ET - O");		set_Value (COLUMNNAME_TipoConsulta, TipoConsulta);
	}

	/** Get Type of Consultation.
		@return To the consultant
	  */
	public String getTipoConsulta () 
	{
		return (String)get_Value(COLUMNNAME_TipoConsulta);
	}
}