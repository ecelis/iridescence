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

/** Generated Model for EXME_CuidadosPac
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CuidadosPac extends PO implements I_EXME_CuidadosPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CuidadosPac (Properties ctx, int EXME_CuidadosPac_ID, String trxName)
    {
      super (ctx, EXME_CuidadosPac_ID, trxName);
      /** if (EXME_CuidadosPac_ID == 0)
        {
			setDescription (null);
			setEXME_CtaPac_ID (0);
			setEXME_CuidadosPac_ID (0);
			setEXME_Medico_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CuidadosPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CuidadosPac[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Discontinued by.
		@param DiscontinuedBy 
		Discontinued By
	  */
	public void setDiscontinuedBy (int DiscontinuedBy)
	{
		set_Value (COLUMNNAME_DiscontinuedBy, Integer.valueOf(DiscontinuedBy));
	}

	/** Get Discontinued by.
		@return Discontinued By
	  */
	public int getDiscontinuedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DiscontinuedBy);
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

	/** Set Patient's Care.
		@param EXME_CuidadosPac_ID Patient's Care	  */
	public void setEXME_CuidadosPac_ID (int EXME_CuidadosPac_ID)
	{
		if (EXME_CuidadosPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CuidadosPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CuidadosPac_ID, Integer.valueOf(EXME_CuidadosPac_ID));
	}

	/** Get Patient's Care.
		@return Patient's Care	  */
	public int getEXME_CuidadosPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CuidadosPac_ID);
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

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (int Folio)
	{
		set_Value (COLUMNNAME_Folio, Integer.valueOf(Folio));
	}

	/** Get Folio.
		@return Folio	  */
	public int getFolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Folio);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}