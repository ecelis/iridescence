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

/** Generated Model for EXME_PlanMed
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_PlanMed extends PO implements I_EXME_PlanMed, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PlanMed (Properties ctx, int EXME_PlanMed_ID, String trxName)
    {
      super (ctx, EXME_PlanMed_ID, trxName);
      /** if (EXME_PlanMed_ID == 0)
        {
			setC_UOM_ID (0);
			setDocStatus (null);
			setDuracion (0);
			setEndDate (new Timestamp( System.currentTimeMillis() ));
			setEXME_CtaPac_ID (0);
			setEXME_Especialidad_ID (0);
			setEXME_Medico_ID (0);
			setEXME_PlanMed_ID (0);
			setIntervalo (0);
			setM_Product_ID (0);
			setM_Warehouse_ID (0);
			setName (null);
			setQtyPlanned (Env.ZERO);
			setQtyTotAplied (Env.ZERO);
			setQtyTotPlanned (Env.ZERO);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
			setSurtir (false);
			setUOMDuracion (null);
			setUOMIntervalo (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PlanMed (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PlanMed[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
		set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
		if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
		if (DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("IP") || DocStatus.equals("WP") || DocStatus.equals("WC")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Dose AD_Reference_ID=1200501 */
	public static final int DOSE_AD_Reference_ID=1200501;
	/** Auto = 1 */
	public static final String DOSE_Auto = "1";
	/** 2 Doses = 2 */
	public static final String DOSE_2Doses = "2";
	/** 3 Doses = 3 */
	public static final String DOSE_3Doses = "3";
	/** 4 Doses = 4 */
	public static final String DOSE_4Doses = "4";
	/** 5 Doses = 5 */
	public static final String DOSE_5Doses = "5";
	/** 6 Doses = 6 */
	public static final String DOSE_6Doses = "6";
	/** 7 Doses = 7 */
	public static final String DOSE_7Doses = "7";
	/** 8 Doses = 8 */
	public static final String DOSE_8Doses = "8";
	/** Set Medication Doses.
		@param Dose 
		Medication Doses
	  */
	public void setDose (String Dose)
	{

		if (Dose == null || Dose.equals("1") || Dose.equals("2") || Dose.equals("3") || Dose.equals("4") || Dose.equals("5") || Dose.equals("6") || Dose.equals("7") || Dose.equals("8")); else throw new IllegalArgumentException ("Dose Invalid value - " + Dose + " - Reference_ID=1200501 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8");		set_Value (COLUMNNAME_Dose, Dose);
	}

	/** Get Medication Doses.
		@return Medication Doses
	  */
	public String getDose () 
	{
		return (String)get_Value(COLUMNNAME_Dose);
	}

	/** Set Duration.
		@param Duracion 
		Duration
	  */
	public void setDuracion (int Duracion)
	{
		set_Value (COLUMNNAME_Duracion, Integer.valueOf(Duracion));
	}

	/** Get Duration.
		@return Duration
	  */
	public int getDuracion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Duracion);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		if (EndDate == null)
			throw new IllegalArgumentException ("EndDate is mandatory.");
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
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

	public I_EXME_Dosis getEXME_Dosis() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Dosis.Table_Name);
        I_EXME_Dosis result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Dosis)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Dosis_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dose.
		@param EXME_Dosis_ID Dose	  */
	public void setEXME_Dosis_ID (int EXME_Dosis_ID)
	{
		if (EXME_Dosis_ID < 1) 
			set_Value (COLUMNNAME_EXME_Dosis_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Dosis_ID, Integer.valueOf(EXME_Dosis_ID));
	}

	/** Get Dose.
		@return Dose	  */
	public int getEXME_Dosis_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dosis_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_ID is mandatory.");
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

	public I_EXME_EsqInsulina getEXME_EsqInsulina() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EsqInsulina.Table_Name);
        I_EXME_EsqInsulina result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EsqInsulina)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EsqInsulina_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Insulin Scheme.
		@param EXME_EsqInsulina_ID Insulin Scheme	  */
	public void setEXME_EsqInsulina_ID (int EXME_EsqInsulina_ID)
	{
		if (EXME_EsqInsulina_ID < 1) 
			set_Value (COLUMNNAME_EXME_EsqInsulina_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EsqInsulina_ID, Integer.valueOf(EXME_EsqInsulina_ID));
	}

	/** Get Insulin Scheme.
		@return Insulin Scheme	  */
	public int getEXME_EsqInsulina_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EsqInsulina_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
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

	public I_EXME_MotivoCancel getEXME_MotivoCancel() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoCancel.Table_Name);
        I_EXME_MotivoCancel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoCancel)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoCancel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cancel Reason.
		@param EXME_MotivoCancel_ID 
		Cancel Reason
	  */
	public void setEXME_MotivoCancel_ID (int EXME_MotivoCancel_ID)
	{
		if (EXME_MotivoCancel_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, Integer.valueOf(EXME_MotivoCancel_ID));
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public int getEXME_MotivoCancel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCancel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase_Version.Table_Name);
        I_EXME_PaqBase_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Package Version.
		@param EXME_PaqBase_Version_ID 
		Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID)
	{
		if (EXME_PaqBase_Version_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, Integer.valueOf(EXME_PaqBase_Version_ID));
	}

	/** Get Package Version.
		@return Package Version
	  */
	public int getEXME_PaqBase_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Plan.
		@param EXME_PlanMed_ID Medical Plan	  */
	public void setEXME_PlanMed_ID (int EXME_PlanMed_ID)
	{
		if (EXME_PlanMed_ID < 1)
			 throw new IllegalArgumentException ("EXME_PlanMed_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PlanMed_ID, Integer.valueOf(EXME_PlanMed_ID));
	}

	/** Get Medical Plan.
		@return Medical Plan	  */
	public int getEXME_PlanMed_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlanMed_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Prescription getEXME_Prescription() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Prescription.Table_Name);
        I_EXME_Prescription result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Prescription)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Prescription_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Prescription.
		@param EXME_Prescription_ID 
		Prescription
	  */
	public void setEXME_Prescription_ID (int EXME_Prescription_ID)
	{
		if (EXME_Prescription_ID < 1) 
			set_Value (COLUMNNAME_EXME_Prescription_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Prescription_ID, Integer.valueOf(EXME_Prescription_ID));
	}

	/** Get Prescription.
		@return Prescription
	  */
	public int getEXME_Prescription_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Prescription_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PrescRXDet getEXME_PrescRXDet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PrescRXDet.Table_Name);
        I_EXME_PrescRXDet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PrescRXDet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PrescRXDet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set RXNorm Prescription Detail.
		@param EXME_PrescRXDet_ID 
		RXNorm Prescription Detail
	  */
	public void setEXME_PrescRXDet_ID (int EXME_PrescRXDet_ID)
	{
		if (EXME_PrescRXDet_ID < 1) 
			set_Value (COLUMNNAME_EXME_PrescRXDet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PrescRXDet_ID, Integer.valueOf(EXME_PrescRXDet_ID));
	}

	/** Get RXNorm Prescription Detail.
		@return RXNorm Prescription Detail
	  */
	public int getEXME_PrescRXDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescRXDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos_Sesion.Table_Name);
        I_EXME_Tratamientos_Sesion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos_Sesion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_Sesion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatment sessions.
		@param EXME_Tratamientos_Sesion_ID Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID)
	{
		if (EXME_Tratamientos_Sesion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Tratamientos_Sesion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Tratamientos_Sesion_ID, Integer.valueOf(EXME_Tratamientos_Sesion_ID));
	}

	/** Get Treatment sessions.
		@return Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Sesion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ViasAdministracion.Table_Name);
        I_EXME_ViasAdministracion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ViasAdministracion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ViasAdministracion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Route of Administration.
		@param EXME_ViasAdministracion_ID Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID)
	{
		if (EXME_ViasAdministracion_ID < 1) 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, Integer.valueOf(EXME_ViasAdministracion_ID));
	}

	/** Get Route of Administration.
		@return Route of Administration	  */
	public int getEXME_ViasAdministracion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ViasAdministracion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interval.
		@param Intervalo 
		Interval
	  */
	public void setIntervalo (int Intervalo)
	{
		set_Value (COLUMNNAME_Intervalo, Integer.valueOf(Intervalo));
	}

	/** Get Interval.
		@return Interval
	  */
	public int getIntervalo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Intervalo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
		set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Planned Quantity.
		@param QtyPlanned Planned Quantity	  */
	public void setQtyPlanned (BigDecimal QtyPlanned)
	{
		if (QtyPlanned == null)
			throw new IllegalArgumentException ("QtyPlanned is mandatory.");
		set_Value (COLUMNNAME_QtyPlanned, QtyPlanned);
	}

	/** Get Planned Quantity.
		@return Planned Quantity	  */
	public BigDecimal getQtyPlanned () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPlanned);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Applied Quantity.
		@param QtyTotAplied Total Applied Quantity	  */
	public void setQtyTotAplied (BigDecimal QtyTotAplied)
	{
		if (QtyTotAplied == null)
			throw new IllegalArgumentException ("QtyTotAplied is mandatory.");
		set_Value (COLUMNNAME_QtyTotAplied, QtyTotAplied);
	}

	/** Get Total Applied Quantity.
		@return Total Applied Quantity	  */
	public BigDecimal getQtyTotAplied () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyTotAplied);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Planned Total Quantity.
		@param QtyTotPlanned Planned Total Quantity	  */
	public void setQtyTotPlanned (BigDecimal QtyTotPlanned)
	{
		if (QtyTotPlanned == null)
			throw new IllegalArgumentException ("QtyTotPlanned is mandatory.");
		set_Value (COLUMNNAME_QtyTotPlanned, QtyTotPlanned);
	}

	/** Get Planned Total Quantity.
		@return Planned Total Quantity	  */
	public BigDecimal getQtyTotPlanned () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyTotPlanned);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		if (StartDate == null)
			throw new IllegalArgumentException ("StartDate is mandatory.");
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Deliver.
		@param Surtir 
		If the product or service will deliver internally
	  */
	public void setSurtir (boolean Surtir)
	{
		set_Value (COLUMNNAME_Surtir, Boolean.valueOf(Surtir));
	}

	/** Get Deliver.
		@return If the product or service will deliver internally
	  */
	public boolean isSurtir () 
	{
		Object oo = get_Value(COLUMNNAME_Surtir);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Tipo AD_Reference_ID=1200185 */
	public static final int TIPO_AD_Reference_ID=1200185;
	/** Fast Action = AR */
	public static final String TIPO_FastAction = "AR";
	/** Short Action = AC */
	public static final String TIPO_ShortAction = "AC";
	/** Intermediate-acting NPH = AI */
	public static final String TIPO_Intermediate_ActingNPH = "AI";
	/** Prolonged action = AP */
	public static final String TIPO_ProlongedAction = "AP";
	/** Set Type.
		@param Tipo 
		GL
	  */
	public void setTipo (String Tipo)
	{

		if (Tipo == null || Tipo.equals("AR") || Tipo.equals("AC") || Tipo.equals("AI") || Tipo.equals("AP")); else throw new IllegalArgumentException ("Tipo Invalid value - " + Tipo + " - Reference_ID=1200185 - AR - AC - AI - AP");		set_Value (COLUMNNAME_Tipo, Tipo);
	}

	/** Get Type.
		@return GL
	  */
	public String getTipo () 
	{
		return (String)get_Value(COLUMNNAME_Tipo);
	}

	/** Set Taken at home.
		@param TomadoCasa Taken at home	  */
	public void setTomadoCasa (boolean TomadoCasa)
	{
		set_Value (COLUMNNAME_TomadoCasa, Boolean.valueOf(TomadoCasa));
	}

	/** Get Taken at home.
		@return Taken at home	  */
	public boolean isTomadoCasa () 
	{
		Object oo = get_Value(COLUMNNAME_TomadoCasa);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** UOMDuracion AD_Reference_ID=1200386 */
	public static final int UOMDURACION_AD_Reference_ID=1200386;
	/** Hours = HR */
	public static final String UOMDURACION_Hours = "HR";
	/** Days = DI */
	public static final String UOMDURACION_Days = "DI";
	/** Weeks = SE */
	public static final String UOMDURACION_Weeks = "SE";
	/** Set Duration UOM.
		@param UOMDuracion Duration UOM	  */
	public void setUOMDuracion (String UOMDuracion)
	{
		if (UOMDuracion == null) throw new IllegalArgumentException ("UOMDuracion is mandatory");
		if (UOMDuracion.equals("HR") || UOMDuracion.equals("DI") || UOMDuracion.equals("SE")); else throw new IllegalArgumentException ("UOMDuracion Invalid value - " + UOMDuracion + " - Reference_ID=1200386 - HR - DI - SE");		set_Value (COLUMNNAME_UOMDuracion, UOMDuracion);
	}

	/** Get Duration UOM.
		@return Duration UOM	  */
	public String getUOMDuracion () 
	{
		return (String)get_Value(COLUMNNAME_UOMDuracion);
	}

	/** UOMIntervalo AD_Reference_ID=1200387 */
	public static final int UOMINTERVALO_AD_Reference_ID=1200387;
	/** Minutes = MI */
	public static final String UOMINTERVALO_Minutes = "MI";
	/** Hours = HR */
	public static final String UOMINTERVALO_Hours = "HR";
	/** Days = DI */
	public static final String UOMINTERVALO_Days = "DI";
	/** Set Interval UOM.
		@param UOMIntervalo 
		Interval UOM
	  */
	public void setUOMIntervalo (String UOMIntervalo)
	{
		if (UOMIntervalo == null) throw new IllegalArgumentException ("UOMIntervalo is mandatory");
		if (UOMIntervalo.equals("MI") || UOMIntervalo.equals("HR") || UOMIntervalo.equals("DI")); else throw new IllegalArgumentException ("UOMIntervalo Invalid value - " + UOMIntervalo + " - Reference_ID=1200387 - MI - HR - DI");		set_Value (COLUMNNAME_UOMIntervalo, UOMIntervalo);
	}

	/** Get Interval UOM.
		@return Interval UOM
	  */
	public String getUOMIntervalo () 
	{
		return (String)get_Value(COLUMNNAME_UOMIntervalo);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}