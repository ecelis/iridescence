/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_VacunaProduct
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_VacunaProduct extends PO implements I_EXME_VacunaProduct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_VacunaProduct (Properties ctx, int EXME_VacunaProduct_ID, String trxName)
    {
      super (ctx, EXME_VacunaProduct_ID, trxName);
      /** if (EXME_VacunaProduct_ID == 0)
        {
			setC_UOM_ID (0);
			setEXME_Vacuna_ID (0);
			setEXME_VacunaProduct_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_VacunaProduct (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - All 
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
      StringBuffer sb = new StringBuffer ("X_EXME_VacunaProduct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
		set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Vacuna getEXME_Vacuna() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Vacuna.Table_Name);
        I_EXME_Vacuna result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Vacuna)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Vacuna_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vaccine.
		@param EXME_Vacuna_ID 
		Vaccine
	  */
	public void setEXME_Vacuna_ID (int EXME_Vacuna_ID)
	{
		if (EXME_Vacuna_ID < 1)
			 throw new IllegalArgumentException ("EXME_Vacuna_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Vacuna_ID, Integer.valueOf(EXME_Vacuna_ID));
	}

	/** Get Vaccine.
		@return Vaccine
	  */
	public int getEXME_Vacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Vacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vaccine-Product Relationship.
		@param EXME_VacunaProduct_ID 
		Describes the Vaccine-Product relationship
	  */
	public void setEXME_VacunaProduct_ID (int EXME_VacunaProduct_ID)
	{
		if (EXME_VacunaProduct_ID < 1)
			 throw new IllegalArgumentException ("EXME_VacunaProduct_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_VacunaProduct_ID, Integer.valueOf(EXME_VacunaProduct_ID));
	}

	/** Get Vaccine-Product Relationship.
		@return Describes the Vaccine-Product relationship
	  */
	public int getEXME_VacunaProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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
}