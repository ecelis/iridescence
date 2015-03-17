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

/** Generated Model for PHR_VacunaPacDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_VacunaPacDet extends PO implements I_PHR_VacunaPacDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_VacunaPacDet (Properties ctx, int PHR_VacunaPacDet_ID, String trxName)
    {
      super (ctx, PHR_VacunaPacDet_ID, trxName);
      /** if (PHR_VacunaPacDet_ID == 0)
        {
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setPHR_VacunaPacDet_ID (0);
			setPHR_VacunaPac_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_VacunaPacDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_VacunaPacDet[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Vaccine Detail.
		@param PHR_VacunaPacDet_ID Vaccine Detail	  */
	public void setPHR_VacunaPacDet_ID (int PHR_VacunaPacDet_ID)
	{
		if (PHR_VacunaPacDet_ID < 1)
			 throw new IllegalArgumentException ("PHR_VacunaPacDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_VacunaPacDet_ID, Integer.valueOf(PHR_VacunaPacDet_ID));
	}

	/** Get Vaccine Detail.
		@return Vaccine Detail	  */
	public int getPHR_VacunaPacDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_VacunaPacDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PHR_VacunaPac getPHR_VacunaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_VacunaPac.Table_Name);
        I_PHR_VacunaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_VacunaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_VacunaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vaccines received.
		@param PHR_VacunaPac_ID Vaccines received	  */
	public void setPHR_VacunaPac_ID (int PHR_VacunaPac_ID)
	{
		if (PHR_VacunaPac_ID < 1)
			 throw new IllegalArgumentException ("PHR_VacunaPac_ID is mandatory.");
		set_Value (COLUMNNAME_PHR_VacunaPac_ID, Integer.valueOf(PHR_VacunaPac_ID));
	}

	/** Get Vaccines received.
		@return Vaccines received	  */
	public int getPHR_VacunaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_VacunaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}