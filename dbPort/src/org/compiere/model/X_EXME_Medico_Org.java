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

/** Generated Model for EXME_Medico_Org
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Medico_Org extends PO implements I_EXME_Medico_Org, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Medico_Org (Properties ctx, int EXME_Medico_Org_ID, String trxName)
    {
      super (ctx, EXME_Medico_Org_ID, trxName);
      /** if (EXME_Medico_Org_ID == 0)
        {
			setEXME_Medico_ID (0);
			setEXME_Medico_Org_ID (0);
			setIsServiceProvider (false);
			setOverlapAppointments (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_Medico_Org (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Medico_Org[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AddToNetwork AD_Reference_ID=1200585 */
	public static final int ADDTONETWORK_AD_Reference_ID=1200585;
	/** Add to Network = A */
	public static final String ADDTONETWORK_AddToNetwork = "A";
	/** Update Prescriber = U */
	public static final String ADDTONETWORK_UpdatePrescriber = "U";
	/** Set Add prescriber to network.
		@param AddToNetwork 
		Add prescriber to network
	  */
	public void setAddToNetwork (String AddToNetwork)
	{

		if (AddToNetwork == null || AddToNetwork.equals("A") || AddToNetwork.equals("U")); else throw new IllegalArgumentException ("AddToNetwork Invalid value - " + AddToNetwork + " - Reference_ID=1200585 - A - U");		set_Value (COLUMNNAME_AddToNetwork, AddToNetwork);
	}

	/** Get Add prescriber to network.
		@return Add prescriber to network
	  */
	public String getAddToNetwork () 
	{
		return (String)get_Value(COLUMNNAME_AddToNetwork);
	}

	/** Set User/Contact .
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

	/** Set Cancel Rx capable.
		@param CanRx 
		Cancel Rx capable
	  */
	public void setCanRx (boolean CanRx)
	{
		set_Value (COLUMNNAME_CanRx, Boolean.valueOf(CanRx));
	}

	/** Get Cancel Rx capable.
		@return Cancel Rx capable
	  */
	public boolean isCanRx () 
	{
		Object oo = get_Value(COLUMNNAME_CanRx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set CellPhone.
		@param CellPhone CellPhone	  */
	public void setCellPhone (String CellPhone)
	{
		set_Value (COLUMNNAME_CellPhone, CellPhone);
	}

	/** Get CellPhone.
		@return CellPhone	  */
	public String getCellPhone () 
	{
		return (String)get_Value(COLUMNNAME_CellPhone);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
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
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
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

	/** Set Doctor's Information for Organization.
		@param EXME_Medico_Org_ID Doctor's Information for Organization	  */
	public void setEXME_Medico_Org_ID (int EXME_Medico_Org_ID)
	{
		if (EXME_Medico_Org_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_Org_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Medico_Org_ID, Integer.valueOf(EXME_Medico_Org_ID));
	}

	/** Get Doctor's Information for Organization.
		@return Doctor's Information for Organization	  */
	public int getEXME_Medico_Org_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shift.
		@param EXME_Turnos_ID 
		Shift
	  */
	public void setEXME_Turnos_ID (int EXME_Turnos_ID)
	{
		if (EXME_Turnos_ID < 1) 
			set_Value (COLUMNNAME_EXME_Turnos_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Turnos_ID, Integer.valueOf(EXME_Turnos_ID));
	}

	/** Get Shift.
		@return Shift
	  */
	public int getEXME_Turnos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Turnos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Consult Interval.
		@param IntervaloConsulta Consult Interval	  */
	public void setIntervaloConsulta (int IntervaloConsulta)
	{
		set_Value (COLUMNNAME_IntervaloConsulta, Integer.valueOf(IntervaloConsulta));
	}

	/** Get Consult Interval.
		@return Consult Interval	  */
	public int getIntervaloConsulta () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IntervaloConsulta);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor bills by himself.
		@param IsServiceProvider Doctor bills by himself	  */
	public void setIsServiceProvider (boolean IsServiceProvider)
	{
		set_Value (COLUMNNAME_IsServiceProvider, Boolean.valueOf(IsServiceProvider));
	}

	/** Get Doctor bills by himself.
		@return Doctor bills by himself	  */
	public boolean isServiceProvider () 
	{
		Object oo = get_Value(COLUMNNAME_IsServiceProvider);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Max Appointments per Days.
		@param MaxCitas Max Appointments per Days	  */
	public void setMaxCitas (BigDecimal MaxCitas)
	{
		set_Value (COLUMNNAME_MaxCitas, MaxCitas);
	}

	/** Get Max Appointments per Days.
		@return Max Appointments per Days	  */
	public BigDecimal getMaxCitas () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxCitas);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
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

	/** Set NewRx Capabale.
		@param NewRx 
		NewRx Capabale
	  */
	public void setNewRx (boolean NewRx)
	{
		set_Value (COLUMNNAME_NewRx, Boolean.valueOf(NewRx));
	}

	/** Get NewRx Capabale.
		@return NewRx Capabale
	  */
	public boolean isNewRx () 
	{
		Object oo = get_Value(COLUMNNAME_NewRx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Overlap Appointments.
		@param OverlapAppointments Overlap Appointments	  */
	public void setOverlapAppointments (boolean OverlapAppointments)
	{
		set_Value (COLUMNNAME_OverlapAppointments, Boolean.valueOf(OverlapAppointments));
	}

	/** Get Overlap Appointments.
		@return Overlap Appointments	  */
	public boolean isOverlapAppointments () 
	{
		Object oo = get_Value(COLUMNNAME_OverlapAppointments);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Refill Request capable.
		@param RefReq 
		Refill Request capable
	  */
	public void setRefReq (boolean RefReq)
	{
		set_Value (COLUMNNAME_RefReq, Boolean.valueOf(RefReq));
	}

	/** Get Refill Request capable.
		@return Refill Request capable
	  */
	public boolean isRefReq () 
	{
		Object oo = get_Value(COLUMNNAME_RefReq);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Rx Change Response capable.
		@param RxChange 
		Rx Change Response capable
	  */
	public void setRxChange (boolean RxChange)
	{
		set_Value (COLUMNNAME_RxChange, Boolean.valueOf(RxChange));
	}

	/** Get Rx Change Response capable.
		@return Rx Change Response capable
	  */
	public boolean isRxChange () 
	{
		Object oo = get_Value(COLUMNNAME_RxChange);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pixels - Minute Scale.
		@param ScaleMin 
		Pixels - Minute Scale for Daily Medical Agenda
	  */
	public void setScaleMin (int ScaleMin)
	{
		set_Value (COLUMNNAME_ScaleMin, Integer.valueOf(ScaleMin));
	}

	/** Get Pixels - Minute Scale.
		@return Pixels - Minute Scale for Daily Medical Agenda
	  */
	public int getScaleMin () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ScaleMin);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Surescripts provider ID.
		@param SPI Surescripts provider ID	  */
	public void setSPI (String SPI)
	{
		set_Value (COLUMNNAME_SPI, SPI);
	}

	/** Get Surescripts provider ID.
		@return Surescripts provider ID	  */
	public String getSPI () 
	{
		return (String)get_Value(COLUMNNAME_SPI);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Waiting Time.
		@param TiempoEspera 
		Waiting Time (in months)
	  */
	public void setTiempoEspera (int TiempoEspera)
	{
		set_Value (COLUMNNAME_TiempoEspera, Integer.valueOf(TiempoEspera));
	}

	/** Get Waiting Time.
		@return Waiting Time (in months)
	  */
	public int getTiempoEspera () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TiempoEspera);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Registered EMail.
		@param UserName 
		Email of the responsible for the System
	  */
	public void setUserName (String UserName)
	{
		set_Value (COLUMNNAME_UserName, UserName);
	}

	/** Get Registered EMail.
		@return Email of the responsible for the System
	  */
	public String getUserName () 
	{
		return (String)get_Value(COLUMNNAME_UserName);
	}
}