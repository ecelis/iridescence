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

/** Generated Model for EXME_PrescDieta
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PrescDieta extends PO implements I_EXME_PrescDieta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PrescDieta (Properties ctx, int EXME_PrescDieta_ID, String trxName)
    {
      super (ctx, EXME_PrescDieta_ID, trxName);
      /** if (EXME_PrescDieta_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_Medico_ID (0);
			setEXME_PrescDieta_ID (0);
			setFechaFin (new Timestamp( System.currentTimeMillis() ));
			setFechaInicio (new Timestamp( System.currentTimeMillis() ));
			setVigente (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_PrescDieta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PrescDieta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ADAT.
		@param ADAT 
		Advance Diet As Tolerated
	  */
	public void setADAT (boolean ADAT)
	{
		set_Value (COLUMNNAME_ADAT, Boolean.valueOf(ADAT));
	}

	/** Get ADAT.
		@return Advance Diet As Tolerated
	  */
	public boolean isADAT () 
	{
		Object oo = get_Value(COLUMNNAME_ADAT);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Authenticated.
		@param Authenticated Authenticated	  */
	public void setAuthenticated (boolean Authenticated)
	{
		set_Value (COLUMNNAME_Authenticated, Boolean.valueOf(Authenticated));
	}

	/** Get Authenticated.
		@return Authenticated	  */
	public boolean isAuthenticated () 
	{
		Object oo = get_Value(COLUMNNAME_Authenticated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Canceled By.
		@param CanceledBy 
		Canceled By
	  */
	public void setCanceledBy (int CanceledBy)
	{
		set_Value (COLUMNNAME_CanceledBy, Integer.valueOf(CanceledBy));
	}

	/** Get Canceled By.
		@return Canceled By
	  */
	public int getCanceledBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CanceledBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discontinued Date.
		@param DiscontinuedDate Discontinued Date	  */
	public void setDiscontinuedDate (Timestamp DiscontinuedDate)
	{
		set_Value (COLUMNNAME_DiscontinuedDate, DiscontinuedDate);
	}

	/** Get Discontinued Date.
		@return Discontinued Date	  */
	public Timestamp getDiscontinuedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DiscontinuedDate);
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

	public I_EXME_DiarioEnf getEXME_DiarioEnf() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_DiarioEnf.Table_Name);
        I_EXME_DiarioEnf result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_DiarioEnf)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_DiarioEnf_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary Diary.
		@param EXME_DiarioEnf_ID Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID)
	{
		if (EXME_DiarioEnf_ID < 1) 
			set_Value (COLUMNNAME_EXME_DiarioEnf_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DiarioEnf_ID, Integer.valueOf(EXME_DiarioEnf_ID));
	}

	/** Get Infirmary Diary.
		@return Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiarioEnf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EncounterForms.Table_Name);
        I_EXME_EncounterForms result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EncounterForms)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EncounterForms_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter Forms.
		@param EXME_EncounterForms_ID 
		Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID)
	{
		if (EXME_EncounterForms_ID < 1) 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, Integer.valueOf(EXME_EncounterForms_ID));
	}

	/** Get Encounter Forms.
		@return Encounter Forms
	  */
	public int getEXME_EncounterForms_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EncounterForms_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Unit.
		@param EXME_EstServ_ID 
		Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Unit.
		@return Service Unit
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
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

	/** Set Diet Prescription.
		@param EXME_PrescDieta_ID 
		Diet Prescription
	  */
	public void setEXME_PrescDieta_ID (int EXME_PrescDieta_ID)
	{
		if (EXME_PrescDieta_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescDieta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PrescDieta_ID, Integer.valueOf(EXME_PrescDieta_ID));
	}

	/** Get Diet Prescription.
		@return Diet Prescription
	  */
	public int getEXME_PrescDieta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescDieta_ID);
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

	/** Set Ending Date.
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
	}

	/** Set Beginning Date.
		@param FechaInicio Beginning Date	  */
	public void setFechaInicio (Timestamp FechaInicio)
	{
		if (FechaInicio == null)
			throw new IllegalArgumentException ("FechaInicio is mandatory.");
		set_Value (COLUMNNAME_FechaInicio, FechaInicio);
	}

	/** Get Beginning Date.
		@return Beginning Date	  */
	public Timestamp getFechaInicio () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaInicio);
	}

	/** Set Cancel Reason.
		@param MotivoCancel 
		Cancel Reason
	  */
	public void setMotivoCancel (String MotivoCancel)
	{
		set_Value (COLUMNNAME_MotivoCancel, MotivoCancel);
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public String getMotivoCancel () 
	{
		return (String)get_Value(COLUMNNAME_MotivoCancel);
	}

	/** Set Noted By.
		@param NotedBy Noted By	  */
	public void setNotedBy (int NotedBy)
	{
		set_Value (COLUMNNAME_NotedBy, Integer.valueOf(NotedBy));
	}

	/** Get Noted By.
		@return Noted By	  */
	public int getNotedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NotedBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Noted Date.
		@param NotedDate Noted Date	  */
	public void setNotedDate (Timestamp NotedDate)
	{
		set_Value (COLUMNNAME_NotedDate, NotedDate);
	}

	/** Get Noted Date.
		@return Noted Date	  */
	public Timestamp getNotedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_NotedDate);
	}

	/** Set Observation.
		@param Observacion 
		Observation
	  */
	public void setObservacion (String Observacion)
	{
		set_Value (COLUMNNAME_Observacion, Observacion);
	}

	/** Get Observation.
		@return Observation
	  */
	public String getObservacion () 
	{
		return (String)get_Value(COLUMNNAME_Observacion);
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

	/** ReadBack AD_Reference_ID=319 */
	public static final int READBACK_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String READBACK_Yes = "Y";
	/** No = N */
	public static final String READBACK_No = "N";
	/** Set Read Back.
		@param ReadBack 
		Read Back
	  */
	public void setReadBack (String ReadBack)
	{

		if (ReadBack == null || ReadBack.equals("Y") || ReadBack.equals("N")); else throw new IllegalArgumentException ("ReadBack Invalid value - " + ReadBack + " - Reference_ID=319 - Y - N");		set_Value (COLUMNNAME_ReadBack, ReadBack);
	}

	/** Get Read Back.
		@return Read Back
	  */
	public String getReadBack () 
	{
		return (String)get_Value(COLUMNNAME_ReadBack);
	}

	/** Set Valid.
		@param Vigente Valid	  */
	public void setVigente (boolean Vigente)
	{
		set_Value (COLUMNNAME_Vigente, Boolean.valueOf(Vigente));
	}

	/** Get Valid.
		@return Valid	  */
	public boolean isVigente () 
	{
		Object oo = get_Value(COLUMNNAME_Vigente);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}