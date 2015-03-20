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

/** Generated Model for PHR_SurgeriesDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_SurgeriesDet extends PO implements I_PHR_SurgeriesDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_SurgeriesDet (Properties ctx, int PHR_SurgeriesDet_ID, String trxName)
    {
      super (ctx, PHR_SurgeriesDet_ID, trxName);
      /** if (PHR_SurgeriesDet_ID == 0)
        {
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setPHR_SurgeriesDet_ID (0);
			setPHR_Surgeries_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_SurgeriesDet (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_PHR_SurgeriesDet[")
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

	/** Set Detail of Medical Procedures & Surgeries.
		@param PHR_SurgeriesDet_ID Detail of Medical Procedures & Surgeries	  */
	public void setPHR_SurgeriesDet_ID (int PHR_SurgeriesDet_ID)
	{
		if (PHR_SurgeriesDet_ID < 1)
			 throw new IllegalArgumentException ("PHR_SurgeriesDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_SurgeriesDet_ID, Integer.valueOf(PHR_SurgeriesDet_ID));
	}

	/** Get Detail of Medical Procedures & Surgeries.
		@return Detail of Medical Procedures & Surgeries	  */
	public int getPHR_SurgeriesDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_SurgeriesDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PHR_Surgeries getPHR_Surgeries() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_Surgeries.Table_Name);
        I_PHR_Surgeries result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_Surgeries)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_Surgeries_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Procedures & Surgeries.
		@param PHR_Surgeries_ID Medical Procedures & Surgeries	  */
	public void setPHR_Surgeries_ID (int PHR_Surgeries_ID)
	{
		if (PHR_Surgeries_ID < 1)
			 throw new IllegalArgumentException ("PHR_Surgeries_ID is mandatory.");
		set_Value (COLUMNNAME_PHR_Surgeries_ID, Integer.valueOf(PHR_Surgeries_ID));
	}

	/** Get Medical Procedures & Surgeries.
		@return Medical Procedures & Surgeries	  */
	public int getPHR_Surgeries_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_Surgeries_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}