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

/** Generated Model for EXME_CtaPacChanges
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_CtaPacChanges extends PO implements I_EXME_CtaPacChanges, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacChanges (Properties ctx, int EXME_CtaPacChanges_ID, String trxName)
    {
      super (ctx, EXME_CtaPacChanges_ID, trxName);
      /** if (EXME_CtaPacChanges_ID == 0)
        {
			setEXME_CtaPacChanges_ID (0);
			setEXME_CtaPac_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacChanges (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacChanges[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Current Admit Date.
		@param AdmitDateAct 
		Current Admit Date
	  */
	public void setAdmitDateAct (Timestamp AdmitDateAct)
	{
		set_Value (COLUMNNAME_AdmitDateAct, AdmitDateAct);
	}

	/** Get Current Admit Date.
		@return Current Admit Date
	  */
	public Timestamp getAdmitDateAct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AdmitDateAct);
	}

	/** Set Previous Admit Date.
		@param AdmitDateAnt 
		Previous Admit Date
	  */
	public void setAdmitDateAnt (Timestamp AdmitDateAnt)
	{
		set_Value (COLUMNNAME_AdmitDateAnt, AdmitDateAnt);
	}

	/** Get Previous Admit Date.
		@return Previous Admit Date
	  */
	public Timestamp getAdmitDateAnt () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AdmitDateAnt);
	}

	/** Set Authenticated By.
		@param AuthenticatedBy Authenticated By	  */
	public void setAuthenticatedBy (int AuthenticatedBy)
	{
		set_Value (COLUMNNAME_AuthenticatedBy, Integer.valueOf(AuthenticatedBy));
	}

	/** Get Authenticated By.
		@return Authenticated By	  */
	public int getAuthenticatedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AuthenticatedBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Authentication Date.
		@param Authenticated_Date Authentication Date	  */
	public void setAuthenticated_Date (Timestamp Authenticated_Date)
	{
		set_Value (COLUMNNAME_Authenticated_Date, Authenticated_Date);
	}

	/** Get Authentication Date.
		@return Authentication Date	  */
	public Timestamp getAuthenticated_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Authenticated_Date);
	}

	/** Set DepartureDate.
		@param DepartureDate DepartureDate	  */
	public void setDepartureDate (Timestamp DepartureDate)
	{
		set_Value (COLUMNNAME_DepartureDate, DepartureDate);
	}

	/** Get DepartureDate.
		@return DepartureDate	  */
	public Timestamp getDepartureDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DepartureDate);
	}

	/** Estatus AD_Reference_ID=1200506 */
	public static final int ESTATUS_AD_Reference_ID=1200506;
	/** By LIP = BL */
	public static final String ESTATUS_ByLIP = "BL";
	/** Verbal Order = VO */
	public static final String ESTATUS_VerbalOrder = "VO";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{

		if (Estatus == null || Estatus.equals("BL") || Estatus.equals("VO")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200506 - BL - VO");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set Current Area.
		@param EXME_AreaAct_ID Current Area	  */
	public void setEXME_AreaAct_ID (int EXME_AreaAct_ID)
	{
		if (EXME_AreaAct_ID < 1) 
			set_Value (COLUMNNAME_EXME_AreaAct_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AreaAct_ID, Integer.valueOf(EXME_AreaAct_ID));
	}

	/** Get Current Area.
		@return Current Area	  */
	public int getEXME_AreaAct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AreaAct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Previous Area.
		@param EXME_AreaAnt_ID Previous Area	  */
	public void setEXME_AreaAnt_ID (int EXME_AreaAnt_ID)
	{
		if (EXME_AreaAnt_ID < 1) 
			set_Value (COLUMNNAME_EXME_AreaAnt_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AreaAnt_ID, Integer.valueOf(EXME_AreaAnt_ID));
	}

	/** Get Previous Area.
		@return Previous Area	  */
	public int getEXME_AreaAnt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AreaAnt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encounter Log.
		@param EXME_CtaPacChanges_ID Encounter Log	  */
	public void setEXME_CtaPacChanges_ID (int EXME_CtaPacChanges_ID)
	{
		if (EXME_CtaPacChanges_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacChanges_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPacChanges_ID, Integer.valueOf(EXME_CtaPacChanges_ID));
	}

	/** Get Encounter Log.
		@return Encounter Log	  */
	public int getEXME_CtaPacChanges_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacChanges_ID);
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

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Enfermeria.Table_Name);
        I_EXME_Enfermeria result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Enfermeria)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Enfermeria_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary staff.
		@param EXME_Enfermeria_ID 
		Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID)
	{
		if (EXME_Enfermeria_ID < 1) 
			set_Value (COLUMNNAME_EXME_Enfermeria_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Enfermeria_ID, Integer.valueOf(EXME_Enfermeria_ID));
	}

	/** Get Infirmary staff.
		@return Infirmary staff
	  */
	public int getEXME_Enfermeria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_ID);
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

	/** Set Current Patient Type.
		@param EXME_TipoPacAct_ID Current Patient Type	  */
	public void setEXME_TipoPacAct_ID (int EXME_TipoPacAct_ID)
	{
		if (EXME_TipoPacAct_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoPacAct_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoPacAct_ID, Integer.valueOf(EXME_TipoPacAct_ID));
	}

	/** Get Current Patient Type.
		@return Current Patient Type	  */
	public int getEXME_TipoPacAct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPacAct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Previous Patient Type.
		@param EXME_TipoPacAnt_ID Previous Patient Type	  */
	public void setEXME_TipoPacAnt_ID (int EXME_TipoPacAnt_ID)
	{
		if (EXME_TipoPacAnt_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoPacAnt_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoPacAnt_ID, Integer.valueOf(EXME_TipoPacAnt_ID));
	}

	/** Get Previous Patient Type.
		@return Previous Patient Type	  */
	public int getEXME_TipoPacAnt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPacAnt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** OrderType AD_Reference_ID=1200543 */
	public static final int ORDERTYPE_AD_Reference_ID=1200543;
	/** Verbal Order = VO */
	public static final String ORDERTYPE_VerbalOrder = "VO";
	/** Written Order = WO */
	public static final String ORDERTYPE_WrittenOrder = "WO";
	/** Telephone Order = TO */
	public static final String ORDERTYPE_TelephoneOrder = "TO";
	/** Set OrderType.
		@param OrderType OrderType	  */
	public void setOrderType (String OrderType)
	{

		if (OrderType == null || OrderType.equals("VO") || OrderType.equals("WO") || OrderType.equals("TO")); else throw new IllegalArgumentException ("OrderType Invalid value - " + OrderType + " - Reference_ID=1200543 - VO - WO - TO");		set_Value (COLUMNNAME_OrderType, OrderType);
	}

	/** Get OrderType.
		@return OrderType	  */
	public String getOrderType () 
	{
		return (String)get_Value(COLUMNNAME_OrderType);
	}

	/** Set Read Back.
		@param ReadBack 
		Read Back
	  */
	public void setReadBack (boolean ReadBack)
	{
		set_Value (COLUMNNAME_ReadBack, Boolean.valueOf(ReadBack));
	}

	/** Get Read Back.
		@return Read Back
	  */
	public boolean isReadBack () 
	{
		Object oo = get_Value(COLUMNNAME_ReadBack);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** ResStatusAct AD_Reference_ID=1200502 */
	public static final int RESSTATUSACT_AD_Reference_ID=1200502;
	/** Limited Support = LS */
	public static final String RESSTATUSACT_LimitedSupport = "LS";
	/** Do not resuscitate = NR */
	public static final String RESSTATUSACT_DoNotResuscitate = "NR";
	/** Do not intubate = NI */
	public static final String RESSTATUSACT_DoNotIntubate = "NI";
	/** Full code = 1F */
	public static final String RESSTATUSACT_FullCode = "1F";
	/** Comfort care only = CO */
	public static final String RESSTATUSACT_ComfortCareOnly = "CO";
	/** Set Current Resuscitative Status.
		@param ResStatusAct Current Resuscitative Status	  */
	public void setResStatusAct (String ResStatusAct)
	{

		if (ResStatusAct == null || ResStatusAct.equals("LS") || ResStatusAct.equals("NR") || ResStatusAct.equals("NI") || ResStatusAct.equals("1F") || ResStatusAct.equals("CO")); else throw new IllegalArgumentException ("ResStatusAct Invalid value - " + ResStatusAct + " - Reference_ID=1200502 - LS - NR - NI - 1F - CO");		set_Value (COLUMNNAME_ResStatusAct, ResStatusAct);
	}

	/** Get Current Resuscitative Status.
		@return Current Resuscitative Status	  */
	public String getResStatusAct () 
	{
		return (String)get_Value(COLUMNNAME_ResStatusAct);
	}

	/** ResStatusAnt AD_Reference_ID=1200502 */
	public static final int RESSTATUSANT_AD_Reference_ID=1200502;
	/** Limited Support = LS */
	public static final String RESSTATUSANT_LimitedSupport = "LS";
	/** Do not resuscitate = NR */
	public static final String RESSTATUSANT_DoNotResuscitate = "NR";
	/** Do not intubate = NI */
	public static final String RESSTATUSANT_DoNotIntubate = "NI";
	/** Full code = 1F */
	public static final String RESSTATUSANT_FullCode = "1F";
	/** Comfort care only = CO */
	public static final String RESSTATUSANT_ComfortCareOnly = "CO";
	/** Set Previous Resuscitative Status.
		@param ResStatusAnt Previous Resuscitative Status	  */
	public void setResStatusAnt (String ResStatusAnt)
	{

		if (ResStatusAnt == null || ResStatusAnt.equals("LS") || ResStatusAnt.equals("NR") || ResStatusAnt.equals("NI") || ResStatusAnt.equals("1F") || ResStatusAnt.equals("CO")); else throw new IllegalArgumentException ("ResStatusAnt Invalid value - " + ResStatusAnt + " - Reference_ID=1200502 - LS - NR - NI - 1F - CO");		set_Value (COLUMNNAME_ResStatusAnt, ResStatusAnt);
	}

	/** Get Previous Resuscitative Status.
		@return Previous Resuscitative Status	  */
	public String getResStatusAnt () 
	{
		return (String)get_Value(COLUMNNAME_ResStatusAnt);
	}
}