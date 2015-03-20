/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProductFDB
 *  @author Generated Class 
 *  @version Release 6.0 - $Id$ */
public class X_EXME_ProductFDB extends PO implements I_EXME_ProductFDB, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductFDB (Properties ctx, int EXME_ProductFDB_ID, String trxName)
    {
      super (ctx, EXME_ProductFDB_ID, trxName);
      /** if (EXME_ProductFDB_ID == 0)
        {
			setEXME_DoseForm_ID (0);
			setEXME_Labeler_ID (0);
			setEXME_Productfdb_ID (0);
			setEXME_Route_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductFDB (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductFDB[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set DoseForm.
		@param EXME_DoseForm_ID 
		DoseForm
	  */
	public void setEXME_DoseForm_ID (int EXME_DoseForm_ID)
	{
		if (EXME_DoseForm_ID < 1)
			 throw new IllegalArgumentException ("EXME_DoseForm_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DoseForm_ID, Integer.valueOf(EXME_DoseForm_ID));
	}

	/** Get DoseForm.
		@return DoseForm
	  */
	public int getEXME_DoseForm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DoseForm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Labeler.
		@param EXME_Labeler_ID 
		Labeler
	  */
	public void setEXME_Labeler_ID (int EXME_Labeler_ID)
	{
		if (EXME_Labeler_ID < 1)
			 throw new IllegalArgumentException ("EXME_Labeler_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Labeler_ID, Integer.valueOf(EXME_Labeler_ID));
	}

	/** Get Labeler.
		@return Labeler
	  */
	public int getEXME_Labeler_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Labeler_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_Productfdb_ID.
		@param EXME_Productfdb_ID EXME_Productfdb_ID	  */
	public void setEXME_Productfdb_ID (int EXME_Productfdb_ID)
	{
		if (EXME_Productfdb_ID < 1)
			 throw new IllegalArgumentException ("EXME_Productfdb_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Productfdb_ID, Integer.valueOf(EXME_Productfdb_ID));
	}

	/** Get EXME_Productfdb_ID.
		@return EXME_Productfdb_ID	  */
	public int getEXME_Productfdb_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Productfdb_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Route.
		@param EXME_Route_ID 
		Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID)
	{
		if (EXME_Route_ID < 1)
			 throw new IllegalArgumentException ("EXME_Route_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Route_ID, Integer.valueOf(EXME_Route_ID));
	}

	/** Get Route.
		@return Route
	  */
	public int getEXME_Route_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Route_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Strength.
		@param Strength 
		Strength
	  */
	public void setStrength (int Strength)
	{
		set_Value (COLUMNNAME_Strength, Integer.valueOf(Strength));
	}

	/** Get Strength.
		@return Strength
	  */
	public int getStrength () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Strength);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Strengthunits.
		@param Strengthunits 
		Strengthunits 
	  */
	public void setStrengthunits (int Strengthunits)
	{
		set_Value (COLUMNNAME_Strengthunits, Integer.valueOf(Strengthunits));
	}

	/** Get Strengthunits.
		@return Strengthunits 
	  */
	public int getStrengthunits () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Strengthunits);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}