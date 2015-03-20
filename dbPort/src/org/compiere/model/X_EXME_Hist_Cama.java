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

/** Generated Model for EXME_Hist_Cama
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Hist_Cama extends PO implements I_EXME_Hist_Cama, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Hist_Cama (Properties ctx, int EXME_Hist_Cama_ID, String trxName)
    {
      super (ctx, EXME_Hist_Cama_ID, trxName);
      /** if (EXME_Hist_Cama_ID == 0)
        {
			setAD_User_ID (0);
			setEXME_CtaPac_ID (0);
			setEXME_Hist_Cama_ID (0);
			setFecha_Cambio (new Timestamp( System.currentTimeMillis() ));
			setFecha_Cambio_Ant (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_Hist_Cama (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Hist_Cama[")
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

	/** Set Actual Bed.
		@param EXME_Cama_Act_ID 
		Actual Bed
	  */
	public void setEXME_Cama_Act_ID (int EXME_Cama_Act_ID)
	{
		if (EXME_Cama_Act_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cama_Act_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cama_Act_ID, Integer.valueOf(EXME_Cama_Act_ID));
	}

	/** Get Actual Bed.
		@return Actual Bed
	  */
	public int getEXME_Cama_Act_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_Act_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Previous Bed.
		@param EXME_Cama_Ant_ID Previous Bed	  */
	public void setEXME_Cama_Ant_ID (int EXME_Cama_Ant_ID)
	{
		if (EXME_Cama_Ant_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cama_Ant_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cama_Ant_ID, Integer.valueOf(EXME_Cama_Ant_ID));
	}

	/** Get Previous Bed.
		@return Previous Bed	  */
	public int getEXME_Cama_Ant_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_Ant_ID);
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

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Actual Service Station.
		@param EXME_EstServ_Act_ID 
		Actual Service Station
	  */
	public void setEXME_EstServ_Act_ID (int EXME_EstServ_Act_ID)
	{
		if (EXME_EstServ_Act_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_Act_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_Act_ID, Integer.valueOf(EXME_EstServ_Act_ID));
	}

	/** Get Actual Service Station.
		@return Actual Service Station
	  */
	public int getEXME_EstServ_Act_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_Act_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Previous Service Station.
		@param EXME_EstServ_Ant_ID 
		Previous Service Station
	  */
	public void setEXME_EstServ_Ant_ID (int EXME_EstServ_Ant_ID)
	{
		if (EXME_EstServ_Ant_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_Ant_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_Ant_ID, Integer.valueOf(EXME_EstServ_Ant_ID));
	}

	/** Get Previous Service Station.
		@return Previous Service Station
	  */
	public int getEXME_EstServ_Ant_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_Ant_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bed History.
		@param EXME_Hist_Cama_ID 
		Bed History
	  */
	public void setEXME_Hist_Cama_ID (int EXME_Hist_Cama_ID)
	{
		if (EXME_Hist_Cama_ID < 1)
			 throw new IllegalArgumentException ("EXME_Hist_Cama_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Hist_Cama_ID, Integer.valueOf(EXME_Hist_Cama_ID));
	}

	/** Get Bed History.
		@return Bed History
	  */
	public int getEXME_Hist_Cama_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hist_Cama_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date of Change.
		@param Fecha_Cambio Date of Change	  */
	public void setFecha_Cambio (Timestamp Fecha_Cambio)
	{
		if (Fecha_Cambio == null)
			throw new IllegalArgumentException ("Fecha_Cambio is mandatory.");
		set_Value (COLUMNNAME_Fecha_Cambio, Fecha_Cambio);
	}

	/** Get Date of Change.
		@return Date of Change	  */
	public Timestamp getFecha_Cambio () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Cambio);
	}

	/** Set Date for Previous Bed.
		@param Fecha_Cambio_Ant Date for Previous Bed	  */
	public void setFecha_Cambio_Ant (Timestamp Fecha_Cambio_Ant)
	{
		if (Fecha_Cambio_Ant == null)
			throw new IllegalArgumentException ("Fecha_Cambio_Ant is mandatory.");
		set_Value (COLUMNNAME_Fecha_Cambio_Ant, Fecha_Cambio_Ant);
	}

	/** Get Date for Previous Bed.
		@return Date for Previous Bed	  */
	public Timestamp getFecha_Cambio_Ant () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Cambio_Ant);
	}
}