/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Asistente
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Asistente extends PO implements I_EXME_Asistente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Asistente (Properties ctx, int EXME_Asistente_ID, String trxName)
    {
      super (ctx, EXME_Asistente_ID, trxName);
      /** if (EXME_Asistente_ID == 0)
        {
			setAccessLevel (false);
			setAD_User_ID (0);
			setEXME_Asistente_ID (0);
			setEXME_CentroMedico_ID (0);
			setName (null);
			setStartAppointment (false);
			setValue (null);
			setViewInstructions (false);
// N
			setViewNextAppt (false);
			setViewService (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_Asistente (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Asistente[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Data Access Level.
		@param AccessLevel 
		Access Level required
	  */
	public void setAccessLevel (boolean AccessLevel)
	{
		set_Value (COLUMNNAME_AccessLevel, Boolean.valueOf(AccessLevel));
	}

	/** Get Data Access Level.
		@return Access Level required
	  */
	public boolean isAccessLevel () 
	{
		Object oo = get_Value(COLUMNNAME_AccessLevel);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Confirm imaging orders.
		@param ConfirmImaging 
		Check to enable the assistant to confirm imaging order
	  */
	public void setConfirmImaging (boolean ConfirmImaging)
	{
		set_Value (COLUMNNAME_ConfirmImaging, Boolean.valueOf(ConfirmImaging));
	}

	/** Get Confirm imaging orders.
		@return Check to enable the assistant to confirm imaging order
	  */
	public boolean isConfirmImaging () 
	{
		Object oo = get_Value(COLUMNNAME_ConfirmImaging);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Confirm Lab Orders.
		@param ConfirmLabOrders 
		Check to enable assistant to confirm lab orders
	  */
	public void setConfirmLabOrders (boolean ConfirmLabOrders)
	{
		set_Value (COLUMNNAME_ConfirmLabOrders, Boolean.valueOf(ConfirmLabOrders));
	}

	/** Get Confirm Lab Orders.
		@return Check to enable assistant to confirm lab orders
	  */
	public boolean isConfirmLabOrders () 
	{
		Object oo = get_Value(COLUMNNAME_ConfirmLabOrders);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Confirm Referrals.
		@param ConfirmReferrals 
		Check to enable assistant to confirm medical referrals
	  */
	public void setConfirmReferrals (boolean ConfirmReferrals)
	{
		set_Value (COLUMNNAME_ConfirmReferrals, Boolean.valueOf(ConfirmReferrals));
	}

	/** Get Confirm Referrals.
		@return Check to enable assistant to confirm medical referrals
	  */
	public boolean isConfirmReferrals () 
	{
		Object oo = get_Value(COLUMNNAME_ConfirmReferrals);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Create Prescription.
		@param CreatePrescription 
		Check to enable assistant to create prescriptions
	  */
	public void setCreatePrescription (boolean CreatePrescription)
	{
		set_Value (COLUMNNAME_CreatePrescription, Boolean.valueOf(CreatePrescription));
	}

	/** Get Create Prescription.
		@return Check to enable assistant to create prescriptions
	  */
	public boolean isCreatePrescription () 
	{
		Object oo = get_Value(COLUMNNAME_CreatePrescription);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Assistant.
		@param EXME_Asistente_ID 
		Assistant
	  */
	public void setEXME_Asistente_ID (int EXME_Asistente_ID)
	{
		if (EXME_Asistente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Asistente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Asistente_ID, Integer.valueOf(EXME_Asistente_ID));
	}

	/** Get Assistant.
		@return Assistant
	  */
	public int getEXME_Asistente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Asistente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CentroMedico getEXME_CentroMedico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CentroMedico.Table_Name);
        I_EXME_CentroMedico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CentroMedico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CentroMedico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Center.
		@param EXME_CentroMedico_ID 
		medical Center
	  */
	public void setEXME_CentroMedico_ID (int EXME_CentroMedico_ID)
	{
		if (EXME_CentroMedico_ID < 1)
			 throw new IllegalArgumentException ("EXME_CentroMedico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CentroMedico_ID, Integer.valueOf(EXME_CentroMedico_ID));
	}

	/** Get Medical Center.
		@return medical Center
	  */
	public int getEXME_CentroMedico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CentroMedico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Finish Appointment.
		@param FinishAppointment Finish Appointment	  */
	public void setFinishAppointment (boolean FinishAppointment)
	{
		set_Value (COLUMNNAME_FinishAppointment, Boolean.valueOf(FinishAppointment));
	}

	/** Get Finish Appointment.
		@return Finish Appointment	  */
	public boolean isFinishAppointment () 
	{
		Object oo = get_Value(COLUMNNAME_FinishAppointment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Start Office Visit.
		@param StartAppointment Start Office Visit	  */
	public void setStartAppointment (boolean StartAppointment)
	{
		set_Value (COLUMNNAME_StartAppointment, Boolean.valueOf(StartAppointment));
	}

	/** Get Start Office Visit.
		@return Start Office Visit	  */
	public boolean isStartAppointment () 
	{
		Object oo = get_Value(COLUMNNAME_StartAppointment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Can review allergies.
		@param ViewAllergies 
		Can review allergies
	  */
	public void setViewAllergies (boolean ViewAllergies)
	{
		set_Value (COLUMNNAME_ViewAllergies, Boolean.valueOf(ViewAllergies));
	}

	/** Get Can review allergies.
		@return Can review allergies
	  */
	public boolean isViewAllergies () 
	{
		Object oo = get_Value(COLUMNNAME_ViewAllergies);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Can review history.
		@param ViewHistory Can review history	  */
	public void setViewHistory (boolean ViewHistory)
	{
		set_Value (COLUMNNAME_ViewHistory, Boolean.valueOf(ViewHistory));
	}

	/** Get Can review history.
		@return Can review history	  */
	public boolean isViewHistory () 
	{
		Object oo = get_Value(COLUMNNAME_ViewHistory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Can review home medication.
		@param ViewHMedication 
		Can review home medication
	  */
	public void setViewHMedication (boolean ViewHMedication)
	{
		set_Value (COLUMNNAME_ViewHMedication, Boolean.valueOf(ViewHMedication));
	}

	/** Get Can review home medication.
		@return Can review home medication
	  */
	public boolean isViewHMedication () 
	{
		Object oo = get_Value(COLUMNNAME_ViewHMedication);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set View Instructions.
		@param ViewInstructions 
		View Instructions
	  */
	public void setViewInstructions (boolean ViewInstructions)
	{
		set_Value (COLUMNNAME_ViewInstructions, Boolean.valueOf(ViewInstructions));
	}

	/** Get View Instructions.
		@return View Instructions
	  */
	public boolean isViewInstructions () 
	{
		Object oo = get_Value(COLUMNNAME_ViewInstructions);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set View Next Appt.
		@param ViewNextAppt 
		View Next Appt
	  */
	public void setViewNextAppt (boolean ViewNextAppt)
	{
		set_Value (COLUMNNAME_ViewNextAppt, Boolean.valueOf(ViewNextAppt));
	}

	/** Get View Next Appt.
		@return View Next Appt
	  */
	public boolean isViewNextAppt () 
	{
		Object oo = get_Value(COLUMNNAME_ViewNextAppt);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set View Service.
		@param ViewService 
		View Service
	  */
	public void setViewService (boolean ViewService)
	{
		set_Value (COLUMNNAME_ViewService, Boolean.valueOf(ViewService));
	}

	/** Get View Service.
		@return View Service
	  */
	public boolean isViewService () 
	{
		Object oo = get_Value(COLUMNNAME_ViewService);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Can review vitals.
		@param ViewVitals 
		Can review vitals
	  */
	public void setViewVitals (boolean ViewVitals)
	{
		set_Value (COLUMNNAME_ViewVitals, Boolean.valueOf(ViewVitals));
	}

	/** Get Can review vitals.
		@return Can review vitals
	  */
	public boolean isViewVitals () 
	{
		Object oo = get_Value(COLUMNNAME_ViewVitals);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}