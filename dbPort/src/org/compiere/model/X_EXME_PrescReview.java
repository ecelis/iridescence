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

/** Generated Model for EXME_PrescReview
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_PrescReview extends PO implements I_EXME_PrescReview, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PrescReview (Properties ctx, int EXME_PrescReview_ID, String trxName)
    {
      super (ctx, EXME_PrescReview_ID, trxName);
      /** if (EXME_PrescReview_ID == 0)
        {
			setEXME_PrescReview_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_PrescReview (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PrescReview[")
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

	public I_EXME_Pharmacist getEXME_Pharmacist() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Pharmacist.Table_Name);
        I_EXME_Pharmacist result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Pharmacist)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Pharmacist_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pharmacist.
		@param EXME_Pharmacist_ID Pharmacist	  */
	public void setEXME_Pharmacist_ID (int EXME_Pharmacist_ID)
	{
		if (EXME_Pharmacist_ID < 1) 
			set_Value (COLUMNNAME_EXME_Pharmacist_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Pharmacist_ID, Integer.valueOf(EXME_Pharmacist_ID));
	}

	/** Get Pharmacist.
		@return Pharmacist	  */
	public int getEXME_Pharmacist_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pharmacist_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PlanMed getEXME_PlanMed() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PlanMed.Table_Name);
        I_EXME_PlanMed result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PlanMed)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PlanMed_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Plan.
		@param EXME_PlanMed_ID Medical Plan	  */
	public void setEXME_PlanMed_ID (int EXME_PlanMed_ID)
	{
		if (EXME_PlanMed_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlanMed_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlanMed_ID, Integer.valueOf(EXME_PlanMed_ID));
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

	/** Set Review of Prescription ID.
		@param EXME_PrescReview_ID Review of Prescription ID	  */
	public void setEXME_PrescReview_ID (int EXME_PrescReview_ID)
	{
		if (EXME_PrescReview_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescReview_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PrescReview_ID, Integer.valueOf(EXME_PrescReview_ID));
	}

	/** Get Review of Prescription ID.
		@return Review of Prescription ID	  */
	public int getEXME_PrescReview_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescReview_ID);
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
}