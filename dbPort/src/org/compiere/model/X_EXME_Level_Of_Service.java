/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Level_Of_Service
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Level_Of_Service extends PO implements I_EXME_Level_Of_Service, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Level_Of_Service (Properties ctx, int EXME_Level_Of_Service_ID, String trxName)
    {
      super (ctx, EXME_Level_Of_Service_ID, trxName);
      /** if (EXME_Level_Of_Service_ID == 0)
        {
			setEXME_Intervencion_ID (0);
			setEXME_Level_Of_Service_ID (0);
			setPatient_Type (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Level_Of_Service (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Level_Of_Service[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Intervencion.Table_Name);
        I_EXME_Intervencion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Intervencion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Intervencion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Intervention.
		@param EXME_Intervencion_ID 
		Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Intervencion_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get Intervention.
		@return Intervention
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Level of service.
		@param EXME_Level_Of_Service_ID 
		level of service
	  */
	public void setEXME_Level_Of_Service_ID (int EXME_Level_Of_Service_ID)
	{
		if (EXME_Level_Of_Service_ID < 1)
			 throw new IllegalArgumentException ("EXME_Level_Of_Service_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Level_Of_Service_ID, Integer.valueOf(EXME_Level_Of_Service_ID));
	}

	/** Get Level of service.
		@return level of service
	  */
	public int getEXME_Level_Of_Service_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Level_Of_Service_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Patient_Type AD_Reference_ID=1200642 */
	public static final int PATIENT_TYPE_AD_Reference_ID=1200642;
	/** New = N */
	public static final String PATIENT_TYPE_New = "N";
	/** Established = E */
	public static final String PATIENT_TYPE_Established = "E";
	/** Set Patient Type.
		@param Patient_Type Patient Type	  */
	public void setPatient_Type (String Patient_Type)
	{
		if (Patient_Type == null) throw new IllegalArgumentException ("Patient_Type is mandatory");
		if (Patient_Type.equals("N") || Patient_Type.equals("E")); else throw new IllegalArgumentException ("Patient_Type Invalid value - " + Patient_Type + " - Reference_ID=1200642 - N - E");		set_Value (COLUMNNAME_Patient_Type, Patient_Type);
	}

	/** Get Patient Type.
		@return Patient Type	  */
	public String getPatient_Type () 
	{
		return (String)get_Value(COLUMNNAME_Patient_Type);
	}
}