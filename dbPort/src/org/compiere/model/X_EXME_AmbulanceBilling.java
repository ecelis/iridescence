/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_AmbulanceBilling
 *  @author Generated Class 
 *  @version Release 1.2.0 - $Id$ */
public class X_EXME_AmbulanceBilling extends PO implements I_EXME_AmbulanceBilling, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AmbulanceBilling (Properties ctx, int EXME_AmbulanceBilling_ID, String trxName)
    {
      super (ctx, EXME_AmbulanceBilling_ID, trxName);
      /** if (EXME_AmbulanceBilling_ID == 0)
        {
			setEXME_AmbulanceBilling_ID (0);
			setEXME_CtaPac_ID (0);
			setLand (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_AmbulanceBilling (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AmbulanceBilling[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Destination.
		@param Destination Destination	  */
	public void setDestination (String Destination)
	{
		set_Value (COLUMNNAME_Destination, Destination);
	}

	/** Get Destination.
		@return Destination	  */
	public String getDestination () 
	{
		return (String)get_Value(COLUMNNAME_Destination);
	}

	/** Set Ambulance Billing.
		@param EXME_AmbulanceBilling_ID Ambulance Billing	  */
	public void setEXME_AmbulanceBilling_ID (int EXME_AmbulanceBilling_ID)
	{
		if (EXME_AmbulanceBilling_ID < 1)
			 throw new IllegalArgumentException ("EXME_AmbulanceBilling_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AmbulanceBilling_ID, Integer.valueOf(EXME_AmbulanceBilling_ID));
	}

	/** Get Ambulance Billing.
		@return Ambulance Billing	  */
	public int getEXME_AmbulanceBilling_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AmbulanceBilling_ID);
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

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Diagnostico.Table_Name);
        I_EXME_Diagnostico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Diagnostico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Diagnostico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Second Diagnostic.
		@param EXME_Diagnostico2_ID 
		Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID)
	{
		if (EXME_Diagnostico2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, Integer.valueOf(EXME_Diagnostico2_ID));
	}

	/** Get Second Diagnostic.
		@return Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Third Diagnostic.
		@param EXME_Diagnostico3_ID 
		Third Diagnostic
	  */
	public void setEXME_Diagnostico3_ID (int EXME_Diagnostico3_ID)
	{
		if (EXME_Diagnostico3_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, Integer.valueOf(EXME_Diagnostico3_ID));
	}

	/** Get Third Diagnostic.
		@return Third Diagnostic
	  */
	public int getEXME_Diagnostico3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fourth Diagnosis.
		@param EXME_Diagnostico4_ID Fourth Diagnosis	  */
	public void setEXME_Diagnostico4_ID (int EXME_Diagnostico4_ID)
	{
		if (EXME_Diagnostico4_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico4_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico4_ID, Integer.valueOf(EXME_Diagnostico4_ID));
	}

	/** Get Fourth Diagnosis.
		@return Fourth Diagnosis	  */
	public int getEXME_Diagnostico4_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico4_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Land.
		@param Land Land	  */
	public void setLand (boolean Land)
	{
		set_Value (COLUMNNAME_Land, Boolean.valueOf(Land));
	}

	/** Get Land.
		@return Land	  */
	public boolean isLand () 
	{
		Object oo = get_Value(COLUMNNAME_Land);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Medical Condition Validation.
		@param MedicalCondition Medical Condition Validation	  */
	public void setMedicalCondition (String MedicalCondition)
	{
		set_Value (COLUMNNAME_MedicalCondition, MedicalCondition);
	}

	/** Get Medical Condition Validation.
		@return Medical Condition Validation	  */
	public String getMedicalCondition () 
	{
		return (String)get_Value(COLUMNNAME_MedicalCondition);
	}

	/** Set Origin.
		@param Origin Origin	  */
	public void setOrigin (String Origin)
	{
		set_Value (COLUMNNAME_Origin, Origin);
	}

	/** Get Origin.
		@return Origin	  */
	public String getOrigin () 
	{
		return (String)get_Value(COLUMNNAME_Origin);
	}

	/** Set ZIP.
		@param Postal 
		Postal code
	  */
	public void setPostal (String Postal)
	{
		set_Value (COLUMNNAME_Postal, Postal);
	}

	/** Get ZIP.
		@return Postal code
	  */
	public String getPostal () 
	{
		return (String)get_Value(COLUMNNAME_Postal);
	}
}