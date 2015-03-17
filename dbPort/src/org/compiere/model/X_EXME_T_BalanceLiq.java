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

/** Generated Model for EXME_T_BalanceLiq
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_T_BalanceLiq extends PO implements I_EXME_T_BalanceLiq, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_T_BalanceLiq (Properties ctx, int EXME_T_BalanceLiq_ID, String trxName)
    {
      super (ctx, EXME_T_BalanceLiq_ID, trxName);
      /** if (EXME_T_BalanceLiq_ID == 0)
        {
			setAD_Session_ID (0);
			setDia (0);
			setEXME_T_BalanceLiq_ID (0);
			setFechaFin (new Timestamp( System.currentTimeMillis() ));
			setFechaInicio (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_T_BalanceLiq (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_T_BalanceLiq[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Session getAD_Session() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Session.Table_Name);
        I_AD_Session result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Session)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Session_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Session.
		@param AD_Session_ID 
		User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID)
	{
		if (AD_Session_ID < 1)
			 throw new IllegalArgumentException ("AD_Session_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Session_ID, Integer.valueOf(AD_Session_ID));
	}

	/** Get Session.
		@return User Session Online or Web
	  */
	public int getAD_Session_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Session_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Day.
		@param Dia Day	  */
	public void setDia (int Dia)
	{
		set_Value (COLUMNNAME_Dia, Integer.valueOf(Dia));
	}

	/** Get Day.
		@return Day	  */
	public int getDia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Dia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fluid Balance.
		@param EXME_T_BalanceLiq_ID Fluid Balance	  */
	public void setEXME_T_BalanceLiq_ID (int EXME_T_BalanceLiq_ID)
	{
		if (EXME_T_BalanceLiq_ID < 1)
			 throw new IllegalArgumentException ("EXME_T_BalanceLiq_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_T_BalanceLiq_ID, Integer.valueOf(EXME_T_BalanceLiq_ID));
	}

	/** Get Fluid Balance.
		@return Fluid Balance	  */
	public int getEXME_T_BalanceLiq_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_T_BalanceLiq_ID);
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
		if (FechaFin == null)
			throw new IllegalArgumentException ("FechaFin is mandatory.");
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
}